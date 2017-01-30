package tta.intel.eus.senecapp.presentador;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import tta.intel.eus.senecapp.modelo.AudioPlayer;
import tta.intel.eus.senecapp.modelo.Data;
import tta.intel.eus.senecapp.R;
import tta.intel.eus.senecapp.modelo.RestClient;
import tta.intel.eus.senecapp.modelo.Expresiones;

public class Expresiones2Activity extends AppCompatActivity {

    Data data;
    Expresiones expresiones;
    int num = 0;
    int times = 0;

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expresiones2);

        setTitle(R.string.expresionesTitle);

        loadInfo();
    }

    public void loadInfo() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                data = new Data();
                expresiones = data.getExpresiones(restClient);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                loadActividad();
            }
        }.execute();
    }

    public void loadActividad(){
        TextView textView1 = (TextView)findViewById(R.id.expresionTextCastellano);
        textView1.setText(expresiones.getExpresionUniversidad().get(num).getFrase1());
        TextView textView2 = (TextView)findViewById(R.id.expresionTextEuskara);
        textView2.setText(expresiones.getExpresionUniversidad().get(num).getFrase2());
        try {
            showAudio(expresiones.getExpresionUniversidad().get(num).getAudio());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void grabarAudio(View view) {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
            Toast.makeText(this,R.string.noMicro, Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent, 0);
            else
                Toast.makeText(this,R.string.noApp, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        if(resultCode!= Activity.RESULT_OK)
            return;
        Uri url = data.getData();
        try {
            showAudio(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAudio(String advise) throws IOException {
        View view = new View(this);
        final AudioPlayer audio = new AudioPlayer(view);
        audio.setAudioUri(Uri.parse(advise));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(times == 0){
            params.height = 400;
            times++;
        }
        else
        {
            params.height = 900;
            Button button = (Button)findViewById(R.id.nextAudioButton);
            button.setVisibility(View.VISIBLE);
            if(num == expresiones.getTotalUniversidad()-1)
            {
                button.setText(R.string.fin);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }
        view.setLayoutParams(params);

        ViewGroup layout = (ViewGroup)findViewById(R.id.expresion2_layout);
        layout.addView(view);

        Button buttonNext = (Button)findViewById(R.id.nextAudioButton);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.ocultar();
                nextExpresion(view);
            }
        });

        audio.start();
    }

    public void nextExpresion(View view){
        num++;
        times = 0;
        setContentView(R.layout.activity_expresiones2);

        setTitle(R.string.expresionesTitle);

        loadInfo();

    }
}
