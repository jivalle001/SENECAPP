package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Register2MujerActivity extends AppCompatActivity {

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");

    String apellido1;
    String apellido2;
    String password;
    String name;
    String username;

    URL url1,url2,url3,url4;
    Bitmap bmp1,bmp2,bmp3,bmp4;
    boolean cambio;
    String un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2_mujer);

        setTitle(R.string.registro2MenuTitle);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        cambio = sharedPreferences.getBoolean("cambio",false);
        un = sharedPreferences.getString("username",null);

        final ImageView b1 = (ImageView)findViewById(R.id.imageChica1);
        final ImageView b2 = (ImageView)findViewById(R.id.imageChica2);
        final ImageView b3 = (ImageView)findViewById(R.id.imageChica3);
        final ImageView b4 = (ImageView)findViewById(R.id.imageChica4);

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    url1 = new URL("https://dl.dropboxusercontent.com/s/47ch7grtygzttul/chica1.jpg?dl=0");
                    bmp1 = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
                    url2 = new URL("https://dl.dropboxusercontent.com/s/iv8ikr8felr03sg/chica2.jpg?dl=0");
                    bmp2 = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
                    url3 = new URL("https://dl.dropboxusercontent.com/s/rbcd363wheajxt7/chica3.jpg?dl=0");
                    bmp3 = BitmapFactory.decodeStream(url3.openConnection().getInputStream());
                    url4 = new URL("https://dl.dropboxusercontent.com/s/gzzkeswsi74awgr/chica4.jpg?dl=0");
                    bmp4 = BitmapFactory.decodeStream(url4.openConnection().getInputStream());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                b1.setImageBitmap(bmp1);
                b2.setImageBitmap(bmp2);
                b3.setImageBitmap(bmp3);
                b4.setImageBitmap(bmp4);

                b1.setOnClickListener(myhandler1);
                b2.setOnClickListener(myhandler2);
                b3.setOnClickListener(myhandler3);
                b4.setOnClickListener(myhandler4);

                Intent intent = getIntent();
                if(cambio!=true)
                {
                    Bundle extras = intent.getExtras();
                    apellido1 = extras.getString("apellido1");
                    apellido2 = extras.getString("apellido2");
                    password = extras.getString("pass");
                    name = extras.getString("name");
                    username = extras.getString("username");
                }
            }
        }.execute();

    }

    View.OnClickListener myhandler1 = new View.OnClickListener(){
        public void onClick(View view){

            Data data = new Data();
            Usuario usuario = new Usuario(apellido1,apellido2,url1.toString(),password,name,username);

            final JSONObject json = data.putUsuario(usuario);

            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        if(cambio!=true){
                            restClient.postJson(json,"addUsuario");
                        }
                        else
                        {
                            restClient.getJson(String.format("actualizarUsuario?usuario=%s&&avatar=%s",un,url1.toString()));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Intent intent = getIntent();
                    if(cambio!=true) {
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
            }.execute();
        }
    };

    View.OnClickListener myhandler2 = new View.OnClickListener(){
        public void onClick(View view){
            Data data = new Data();
            int imageId = R.drawable.chico1;
            Usuario usuario = new Usuario(apellido1,apellido2,url2.toString(),password,name,username);
            final JSONObject json = data.putUsuario(usuario);

            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        if(cambio!=true){
                            restClient.postJson(json,"addUsuario");
                        }
                        else
                        {
                            restClient.getJson(String.format("actualizarUsuario?usuario=%s&&avatar=%s",un,url2.toString()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Intent intent = getIntent();
                    if(cambio!=true) {
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
            }.execute();
        }
    };

    View.OnClickListener myhandler3 = new View.OnClickListener(){
        public void onClick(View view){
            Data data = new Data();
            int imageId = R.drawable.chico1;
            Usuario usuario = new Usuario(apellido1,apellido2,url3.toString(),password,name,username);
            final JSONObject json = data.putUsuario(usuario);

            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        if(cambio!=true){
                            restClient.postJson(json,"addUsuario");
                        }
                        else
                        {
                            restClient.getJson(String.format("actualizarUsuario?usuario=%s&&avatar=%s",un,url3.toString()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Intent intent = getIntent();
                    if(cambio!=true) {
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
            }.execute();
        }
    };

    View.OnClickListener myhandler4 = new View.OnClickListener(){
        public void onClick(View view){
            Data data = new Data();
            int imageId = R.drawable.chico1;
            Usuario usuario = new Usuario(apellido1,apellido2,url4.toString(),password,name,username);
            final JSONObject json = data.putUsuario(usuario);

            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        if(cambio!=true){
                            restClient.postJson(json,"addUsuario");
                        }
                        else
                        {
                            restClient.getJson(String.format("actualizarUsuario?usuario=%s&&avatar=%s",un,url4.toString()));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Intent intent = getIntent();
                    if(cambio!=true) {
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
            }.execute();
        }
    };

}
