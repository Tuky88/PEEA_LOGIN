package com.example.dibujo.peea_login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private SharedPreferences pref;
    private EditText editTextMail;
    private EditText editTextPass;
    private Switch switchRecordar;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref=getSharedPreferences("Preferences_PEEA", Context.MODE_PRIVATE);
        bindUI();
    }
    private void bindUI()
    {
        editTextMail=findViewById(R.id.editText_mail);
        editTextPass=findViewById(R.id.editText_password);
        switchRecordar=findViewById(R.id.switch_recordar);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new Login());
    }
    private void saveOnPreference(String email,String pass)
    {
        if(switchRecordar.isChecked())
        {
            SharedPreferences.Editor editor=pref.edit();
            editor.putString("email",email);
            editor.putString("pass",pass);
            editor.commit();
            editor.apply();
        }
    }
    private void goToMain()
    {
        Intent intent =new Intent(login.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private boolean isEmailValid(String email)
    {
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Correo inválido",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private  boolean isPasswordValid(String pass)
    {
        if (!pass.isEmpty() && pass.length()>4)
        {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Password inválido",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    private class Login implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {

            String email,pass;
            email=editTextMail.getText().toString();
            pass=editTextPass.getText().toString();
            if(isEmailValid(email) && isPasswordValid(pass))
            {
                saveOnPreference(email,pass);
                goToMain();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Se denegó el acceso.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
