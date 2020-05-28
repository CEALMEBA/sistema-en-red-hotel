package com.example.appjorge;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Menu extends AppCompatActivity {
    int valorluces=0;
    int valortemperatura=0;
    int valorllaves=0;

    String pluces;
    String ptemperatura;
    String pllaves;
    ImageView luces,temperatura,llave;
    TextView peticionluces,peticiontemp,peticionllaves,tvluz,tvtemp,tvllave;
    int status;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        luces = (ImageView)findViewById(R.id.imgluces);
        temperatura = (ImageView)findViewById(R.id.imgtemperatura);
        llave = (ImageView)findViewById(R.id.imgllaves);

        peticionluces = (TextView)findViewById(R.id.peticionluz);
        peticiontemp = (TextView)findViewById(R.id.peticiontemp);
        peticionllaves = (TextView)findViewById(R.id.peticionllaves);

        tvluz = (TextView)findViewById(R.id.tvluz);
        tvtemp = (TextView)findViewById(R.id.tvtemp);
        tvllave = (TextView)findViewById(R.id.tvllave);


        //valores de luces, temperatura y llaves de la HABITACION
        //startactivity mandar parametros

        servicios("https://hoteluv.000webhostapp.com/consultarestado.php?hab=1");
        peticiones("https://hoteluv.000webhostapp.com/consultarpeticion.php?hab=1");


        luces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent luces = new Intent(Menu.this, Luces.class);
                luces.putExtra("eluces",valorluces);
                startActivity(luces);
                finish();
            }
        });

        temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent temperatura = new Intent(Menu.this, Temperatura.class);
                temperatura.putExtra("etemperatura",valortemperatura);
                startActivity(temperatura);
                finish();
            }
        });

        llave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent llaves = new Intent(Menu.this, Llaves.class);
                llaves.putExtra("ellaves",valorllaves);
                startActivity(llaves);
                finish();
            }
        });

    }

    private void peticiones(String URL) {
        final ProgressDialog mDialog = new ProgressDialog(Menu.this);
        mDialog.setMessage("Espere porfavor...");
        mDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                mDialog.dismiss();
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);

                                    status = Integer.parseInt(jsonObject.getString("estado"));
                                    pluces = jsonObject.getString("pluces");
                                    ptemperatura = jsonObject.getString("ptemperatura");
                                    pllaves = jsonObject.getString("pllaves");

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                }

                if(status == 1){

                    if(pluces.equals("")){
                        peticionluces.setText("");
                    }else if(Integer.parseInt(pluces) == 1){
                        peticionluces.setText("Se ha solicitado prender las luces");
                    }else if(Integer.parseInt(pluces) == 0){
                        peticionluces.setText("Se ha solicitado apagar las luces");
                    }

                    if(ptemperatura.equals("")){
                        peticiontemp.setText("");
                    }else if(Integer.parseInt(ptemperatura) == 0){
                        peticiontemp.setText("Se ha solicitado apagar la temperatura");
                    }else if(Integer.parseInt(ptemperatura) > 0){
                        peticiontemp.setText("Se ha solicitado colocar la temperatura a: " + ptemperatura);
                    }

                    if(pllaves.equals("")){
                        peticionllaves.setText("");
                    }else if(Integer.parseInt(pllaves) == 1){
                        peticionllaves.setText("Se ha solicitado abrir la puerta");
                    }else if(Integer.parseInt(pllaves) == 0){
                        peticionllaves.setText("Se ha solicitado cerrar la puerta");
                    }

                }else{
                    peticionluces.setText("");
                    peticiontemp.setText("");
                    peticionllaves.setText("");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private void servicios(String URL) {
        final ProgressDialog mDialog = new ProgressDialog(Menu.this);
        mDialog.setMessage("Espere porfavor...");
        mDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                mDialog.dismiss();
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        valorluces = Integer.parseInt(jsonObject.getString("luces"));
                        valortemperatura = Integer.parseInt(jsonObject.getString("temperatura"));
                        valorllaves = Integer.parseInt(jsonObject.getString("llaves"));

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                    }
                }

                if(valorluces==1){
                    luces.setImageResource(R.drawable.prendido);
                    tvluz.setText("Luz Encendida");
                }else{
                    luces.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                    tvluz.setText("Luz Apagada");
                }

                if(valortemperatura>0){
                    temperatura.setImageResource(R.drawable.temperatura);
                    tvtemp.setText("Temperatura a: "+valortemperatura+"°");
                }else{
                    temperatura.setImageResource(R.drawable.ic_toys_black_24dp);
                    tvtemp.setText("Temperatura ambiente");
                }

                if(valorllaves == 1){
                    llave.setImageResource(R.drawable.llave);
                    tvllave.setText("Puerta abierta");
                }else{
                    llave.setImageResource(R.drawable.ic_vpn_key_black_24dp);
                    tvllave.setText("Puerta cerrada");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

}
