package edu.upenn.cis573.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    private DataManager dataManager = new DataManager(new WebClient("10.0.2.2", 3001));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    public void onVerifyOldPasswordClick(View view) {
        EditText oldPasswordField = findViewById(R.id.old_passwordField);
        String oldPassword = oldPasswordField.getText().toString();
        Contributor contributor = MainActivity.contributor;

        if (!oldPassword.equals(contributor.getPassword())) {
            Toast.makeText(this, "Old password is incorrect", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);

        } else {
            findViewById(R.id.new_passwordField).setVisibility(View.VISIBLE);
            findViewById(R.id.confirm_new_passwordField).setVisibility(View.VISIBLE);
            findViewById(R.id.change_password_button).setVisibility(View.VISIBLE);
            findViewById(R.id.validate_button).setVisibility(View.GONE);
        }
    }

    public void onChangePasswordButtonClick(View view) {
        EditText newPasswordField = findViewById(R.id.new_passwordField);
        String newPassword = newPasswordField.getText().toString();

        EditText confirmPasswordField = findViewById(R.id.confirm_new_passwordField);
        String confirmPassword = confirmPasswordField.getText().toString();

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "New passwords do not match", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
            return;
        }

        Contributor contributor = MainActivity.contributor;
        String contributorId = contributor.getId();

        try {
            boolean success = dataManager.updatePassword(contributorId, newPassword);
            if (success) {
                contributor.setPassword(newPassword);
                Toast.makeText(this, "Password updated successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Failed to update password", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

