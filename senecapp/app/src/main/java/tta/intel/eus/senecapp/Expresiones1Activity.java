package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

public class Expresiones1Activity extends AppCompatActivity {

    private final static int AUDIO_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expresiones1);

        String audio = "http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4";

        try {
            showAudio(audio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAudio(String advise) throws IOException {
        View view = new View(this);
        AudioPlayer audio = new AudioPlayer(view);
        audio.setAudioUri(Uri.parse(advise));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        ViewGroup layout = (ViewGroup)findViewById(R.id.expresiones_layout);
        layout.addView(view);
        audio.start();
    }

    public void grabarAudio(View view) {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
            Toast.makeText(this,"No hay microfono", Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent,AUDIO_REQUEST_CODE);
            else
                Toast.makeText(this,"No hay app", Toast.LENGTH_SHORT).show();
        }
    }
}
