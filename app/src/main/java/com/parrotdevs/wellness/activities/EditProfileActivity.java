package com.parrotdevs.wellness.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.parrotdevs.wellness.R;
import com.parrotdevs.wellness.model.User;

import java.util.Objects;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    CircleImageView profilePic;
    ImageView btnAddPicture;
    TextInputLayout inputName, inputDescription, inputNewPassword, inputConfirmPassword;
    Button btnConfirm;

    FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private FirebaseStorage storage;
    User currentUser;
    private StorageReference reference;

    public Uri profilePicUri;
    ActivityResultLauncher<Intent> getProfileImageResult;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profilePic = findViewById(R.id.profile_image);
        btnAddPicture = findViewById(R.id.btnAddPic);

        inputName = findViewById(R.id.inputNameEditProfile);
        inputDescription = findViewById(R.id.inputDescriptionEditProfile);
        inputNewPassword = findViewById(R.id.inputNewPasswordEditProfile);
        inputConfirmPassword = findViewById(R.id.inputConfirmPasswordEditProfile);
        btnConfirm = findViewById(R.id.btnConfirmEditProfile);

        storage = FirebaseStorage.getInstance();
        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        context = this;

        loadUser();

        getProfileImageResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();
                        profilePicUri = data.getData();
                        profilePic.setImageURI(profilePicUri);
                        UploadPicture();
                    }
                });

        reference = storage.getReference();

        btnAddPicture.setOnClickListener(v -> {
            ChoseProfilePic();
        });

        btnConfirm.setOnClickListener(v -> {
            changeUserInfo();
        });
    }

    private void changeUserInfo() {

        if(validateChangeName() || validateChangeDescription()){

            currentUser.setDescription(inputDescription.getEditText().getText().toString());
            currentUser.setName(inputName.getEditText().getText().toString());

            db.getReference("users").child(currentUser.getId()).setValue(currentUser).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    Toast.makeText(this, "Información actualizada", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(validateChangePassword()){
            firebaseUser.updatePassword(inputNewPassword.getEditText().getText().toString()).addOnCompleteListener(task -> {

                if(task.isSuccessful()){
                    Toast.makeText(this, "Se cambio la contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        }

    }

    private boolean validateChangePassword() {
        if(!inputNewPassword.getEditText().getText().toString().equals("") && !inputConfirmPassword.getEditText().getText().toString().equals("")
                && inputConfirmPassword.getEditText().getText().toString().equals(inputNewPassword.getEditText().getText().toString())){
            return true;
        }
        return false;
    }

    private boolean validateChangeDescription() {
        if (!inputDescription.getEditText().getText().toString().equals(currentUser.getDescription())){
            return true;
        }
        return false;
    }

    private boolean validateChangeName() {
        if (!inputName.getEditText().getText().toString().equals(currentUser.getName())){
            return true;
        }

        return false;
    }


    private void loadUser() {
        db.getReference("users").child(Objects.requireNonNull(auth.getUid())).addValueEventListener(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        currentUser = snapshot.getValue(User.class);
                        inputName.getEditText().setText(currentUser.getName());
                        inputDescription.getEditText().setText(currentUser.getDescription());
                        Glide.with(getApplicationContext()).load(currentUser.getUserPhoto()).placeholder(R.drawable.ic_profile).into(profilePic);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );
    }

    private void ChoseProfilePic() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getProfileImageResult.launch(intent);

    }

    public void UploadPicture() {

        String imageId = String.valueOf(UUID.randomUUID());
        StorageReference profilePicRef = reference.child("userPhotos/" + imageId);
        profilePicRef.putFile(profilePicUri).addOnSuccessListener(taskSnapshot -> {

            Toast.makeText(this, "ImagenSubida", Toast.LENGTH_SHORT).show();

            profilePicRef.getDownloadUrl().addOnSuccessListener(uri -> {

                currentUser.setUserPhoto(uri.toString());
                db.getReference("users").child(currentUser.getId()).setValue(currentUser).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        Toast.makeText(this, "link actualizado", Toast.LENGTH_SHORT).show();
                    }
                });

            });


        });

    }
}