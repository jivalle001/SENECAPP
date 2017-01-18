package tta.intel.eus.senecapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class Expresiones1Activity extends AppCompatActivity {

    TextView tv1;
    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;
    Button b1, b2, b3;
    private Uri archivoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expresiones1);

        tv1 = (TextView) this.findViewById(R.id.expresionText);
        b1 = (Button) findViewById(R.id.grabarButton);
        b2 = (Button) findViewById(R.id.pararButton);
        b3 = (Button) findViewById(R.id.reproducirButton);
    }

    public void grabar(View v) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        try {
           File archivo = File.createTempFile("temporal", ".3gp", path);
            archivoUri = Uri.fromFile(archivo);
            recorder.setOutputFile(String.valueOf(archivoUri));
        } catch (IOException e) {
        }
        try{
            recorder.start();
        }catch (IllegalStateException e){

        }

        tv1.setText("Grabando");
        b1.setEnabled(false);
        b2.setEnabled(true);
    }

    public void detener(View v) {
        try{
            recorder.stop();
        }catch (IllegalStateException e){

        }
        recorder.reset();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);
                tv1.setText("Listo");
            }
        });
        try {
            player.setDataSource(String.valueOf(archivoUri));
        } catch (IOException e) {
        }
        try {
            player.prepare();
        } catch (IllegalStateException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        b1.setEnabled(true);
        b2.setEnabled(false);
        b3.setEnabled(true);
        tv1.setText("Listo para reproducir");
    }

    public void reproducir(View v) {
        try{
            player.start();
        }catch (IllegalStateException e){

        }
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        tv1.setText("Reproduciendo");
    }
}
