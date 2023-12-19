package com.example.practical8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String USER_FILE_NAME = "user_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        File userFile = new File(context.getFilesDir(), USER_FILE_NAME);

        if (userFile.exists()) {
            // Hide the fragment within the FragmentContainerView
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.FCVCreateUser, new FirstLoginFragment2()) // Replace with your actual fragment class
                    .commit();

            String username = readUserName();
            TextView TVWelcome = findViewById(R.id.TVWelcome);
            TVWelcome.setText("Welcome Mr " + username);
        }
    }

    protected String readUserName() {
        String fileContent = "";
        FileInputStream fis = null;
        try {
            fis = getApplicationContext().openFileInput(USER_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append("\n");
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fileContent = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContent;
    }

    public void BtnGalleryOnClick(View v) {
        Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
        startActivity(intent);
    }

    public void BtnSettingsOnClick(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}
