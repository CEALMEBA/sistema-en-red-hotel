package com.example.appjorge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Luces extends AppCompatActivity {
    ImageView imgluz;
    Switch miswitch;
    int valorluces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luces);
        Intent luces = getIntent();
        valorluces = luces.getIntExtra("eluces",0);
        imgluz = (ImageView)findViewById(R.id.imgluces);
        miswitch =(Switch) findViewById(R.id.switchluz);

        if(valorluces==1){
            imgluz.setImageResource(R.drawable.prendido);
        }else{
            imgluz.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
        }


        estadoinicial();

        miswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()==R.id.switchluz) {
                    if (miswitch.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Luz prendida", Toast.LENGTH_SHORT).show();
                        imgluz.setImageResource(R.drawable.prendido);
                        ejecutarservicio("https://hoteluv.000webhostapp.com/peticiones.php","1","1");

                    } else {
                        Toast.makeText(getApplicationContext(), "Luz apagada", Toast.LENGTH_SHORT).show();
                        imgluz.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                        ejecutarservicio("https://hoteluv.000webhostapp.com/peticiones.php","0","1");
                    }
                }
            }
        });
    }

    private void estadoinicial() {
        if(valorluces == 1){
            miswitch.setChecked(true);
        }else{
            miswitch.setChecked(false);
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
                parametros.put("luces",valor);
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
