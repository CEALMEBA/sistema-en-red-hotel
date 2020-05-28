package com.example.appjorge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  Button buton;
  EditText text;
  EditText text1;
  private final String usuario = "admin";
  private final String contraseña = "admin";
  private final String habi = "ha1";
  private final String contrahabi = "habi12";
  private static final String TAG = "LoginInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buton = (Button) findViewById(R.id.ingresar);
        text =(EditText)findViewById(R.id.usuario);
        text1 =(EditText)findViewById(R.id.contraseña);


            buton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (usuario.equals(text.getText().toString())&(contraseña.equals(text1.getText().toString()))||habi.equals(text.getText().toString())&(contrahabi.equals(text1.getText().toString()))){
                        SharedPreferences sharedpreferences = getSharedPreferences("Login_Info", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("USERNAME", usuario);
                        editor.putString("PASSWORD", contraseña);
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"usuario correcto", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Home.class));
                }else{
                        Toast.makeText(getApplicationContext(),"usuario invalido", Toast.LENGTH_SHORT).show();
                    }


        }

    });
}
}
