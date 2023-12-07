package org.techtown.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PetsettingsActivity extends AppCompatActivity {

    private TextView petNameTextView, genderTextView, ageTextView, breedTextView, guardianNameTextView;
    private ImageButton petProfileImageButton;
    private Button logoutButton, withdrawalButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_settings);

        // Initialize TextViews
        petNameTextView = findViewById(R.id.textView22);
        genderTextView = findViewById(R.id.textView24);
        ageTextView = findViewById(R.id.textView25);
        breedTextView = findViewById(R.id.textView26);
        guardianNameTextView = findViewById(R.id.textView29);

        // Initialize ImageButton
        petProfileImageButton = findViewById(R.id.imageButton4);

        // Initialize Buttons
        logoutButton = findViewById(R.id.button9);
        withdrawalButton = findViewById(R.id.button10);

        // Set click listener for pet profile image button
        petProfileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the pet profile image button
                // Add your logic here
            }
        });

        // Set click listener for logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the logout button
                // Add your logic here
            }
        });

        // Set click listener for withdrawal button
        withdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for the withdrawal button
                // Add your logic here
            }
        });
    }
}
