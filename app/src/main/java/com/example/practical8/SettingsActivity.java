package com.example.practical8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    EditText ETBgColorCode;
    EditText ETImageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ETBgColorCode = findViewById(R.id.ETBgColorCode);
        ETImageNumber = findViewById(R.id.ETImageNumber); // Corrected line

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String SPBgColorCode = sharedPref.getString("BgColorCode", "");
        int SPImageNumber = sharedPref.getInt("ImageNumber", 3);

        ETBgColorCode.setText(SPBgColorCode);
        ETImageNumber.setText(String.valueOf(SPImageNumber));
    }

    public void BtnSubmitSettingsOnClick(View v) {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();

        String bgColorCode = ETBgColorCode.getText().toString();
        int imageNumber = Integer.parseInt(ETImageNumber.getText().toString());

        spEditor.putString("BgColorCode", bgColorCode);
        spEditor.putInt("ImageNumber", imageNumber);
        spEditor.commit();
    }
}
