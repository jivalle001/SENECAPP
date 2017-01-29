package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuActivity extends AppCompatActivity {

    Usuario usuario;
    Bitmap bitmap;
    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String un = sharedPreferences.getString("username",null);

        final ImageView avatar = (ImageView)findViewById(R.id.avatar);

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Data data = new Data();
                usuario = data.getUsuario(un,restClient);
                try {
                    URL url = new URL(usuario.getAvatar());
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
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
                avatar.setImageBitmap(bitmap);
            }
        }.execute();

    }

    public void actividades(View view){
        Intent intent = new Intent(this,ActividadesActivity.class);
        startActivity(intent);
    }

    public void expresiones(View view){
        Intent intent = new Intent(this,ExpresionesActivity.class);
        startActivity(intent);
    }

    public void mapas(View view){
        Intent intent = new Intent(this,MapasActivity.class);
        startActivity(intent);
    }

    public void ajustes(View view){
        Intent intent = new Intent(this,AjustesActivity.class);
        startActivity(intent);
    }

    public void cerrarSesion(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
