package tta.intel.eus.senecapp.presentador;

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

import tta.intel.eus.senecapp.R;

public class Register2MenuActivity extends AppCompatActivity {

    URL url1;
    URL url2;
    Bitmap bmp1;
    Bitmap bmp2;

    boolean cambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2_menu);

        setTitle(R.string.registro2MenuTitle);

        final ImageView imageView1 = (ImageView)findViewById(R.id.hombre);
        final ImageView imageView2 = (ImageView)findViewById(R.id.mujer);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        cambio = sharedPreferences.getBoolean("cambio",false);

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    url1 = new URL("https://dl.dropboxusercontent.com/s/mpe7n1ay606uhzg/chico1.jpg?dl=0");
                    bmp1 = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
                    url2 = new URL("https://dl.dropboxusercontent.com/s/47ch7grtygzttul/chica1.jpg?dl=0");
                    bmp2 = BitmapFactory.decodeStream(url2.openConnection().getInputStream());

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
                imageView1.setImageBitmap(bmp1);
                imageView2.setImageBitmap(bmp2);
            }
        }.execute();

    }

    public void hombre(View view){
        Intent intent = getIntent();
        Bundle bundle;
        Intent intent1 = new Intent(getApplicationContext(),Register2HombreActivity.class);
        if(cambio!=true){
            bundle = intent.getExtras();
            bundle.putString("apellido1",bundle.getString("apellido1"));
            bundle.putString("apellido2",bundle.getString("apellido2"));
            bundle.putString("pass",bundle.getString("pass"));
            bundle.putString("name",bundle.getString("name"));
            bundle.putString("username",bundle.getString("username"));
            intent1.putExtras(bundle);
        }
        startActivity(intent1);
    }

    public void mujer(View view){
        Intent intent = getIntent();
        Bundle bundle;
        Intent intent1 = new Intent(getApplicationContext(),Register2MujerActivity.class);
        if(cambio!=true) {
            bundle = intent.getExtras();
            bundle.putString("apellido1",bundle.getString("apellido1"));
            bundle.putString("apellido2",bundle.getString("apellido2"));
            bundle.putString("pass",bundle.getString("pass"));
            bundle.putString("name",bundle.getString("name"));
            bundle.putString("username",bundle.getString("username"));
            intent1.putExtras(bundle);
        }
        startActivity(intent1);
    }

}
