package com.example.practical8;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FirstLoginFragment2 extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_login2, container, false);

        Button BtnSubmit = view.findViewById(R.id.BtnSubmit);
        Context context = view.getContext();
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ETUsername = view.findViewById(R.id.ETusername);
                String filename = "user_file";
                String fileContents = ETUsername.getText().toString();
                try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
                    fos.write(fileContents.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }
}