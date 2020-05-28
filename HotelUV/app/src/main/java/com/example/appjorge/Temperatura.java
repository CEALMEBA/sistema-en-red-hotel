package com.example.appjorge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Temperatura extends AppCompatActivity {

    ImageView imgtemp;
    Switch miswitcht;
    EditText tvtemperatura;
    int valortemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        Intent temperatura = getIntent();
        valortemperatura = temperatura.getIntExtra("etemperatura",0);

        tvtemperatura = (EditText)findViewById(R.id.tvtemp);
        imgtemp = (ImageView)findViewById(R.id.imgtemp);
        miswitcht = (Switch)findViewById(R.id.switchtempe);

        if(valortemperatura>0){
            imgtemp.setImageResource(R.drawable.temperatura);
        }else{
            imgtemp.setImageResource(R.drawable.ic_toys_black_24dp);
        }

        miswitcht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()==R.id.switchtempe) {
                    if (miswitcht.isChecked()) {
                        if(tvtemperatura.getText().toString().equals("")){
                            miswitcht.setChecked(false);
                            Toast.makeText(getApplicationContext(), "Coloque una valor en C°", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplicationContext(), "Temperatura Encendida", Toast.LENGTH_SHORT).show();
                            imgtemp.setImageResource(R.drawable.temperatura);
                            ejecutarservicio("https://hoteluv.000webhostapp.com/peticiones.php",tvtemperatura.getText().toString(),"1");
                            tvtemperatura.setEnabled(false);
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Temperatura Apagada", Toast.LENGTH_SHORT).show();
                        imgtemp.setImageResource(R.drawable.ic_toys_black_24dp);
                        ejecutarservicio("https://hoteluv.000webhostapp.com/peticiones.php","0","1");
                        tvtemperatura.setEnabled(true);
                        tvtemperatura.setText("");
                    }
                }
            }
        });


        estadoinicial();
    }

    private void estadoinicial() {

        if(valortemperatura>0){
            miswitcht.setChecked(true);
            tvtemperatura.setText(Integer.toString(valortemperatura));

        }else{
            miswitcht.setChecked(false);
            tvtemperatura.setText("");
        }
    }

    private void ejecutarservicio(String URL,final String valor, final String habitacion){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("temperatura",valor);
                parametros.put("hab",habitacion);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Menu.class));
        finish();
    }
}
