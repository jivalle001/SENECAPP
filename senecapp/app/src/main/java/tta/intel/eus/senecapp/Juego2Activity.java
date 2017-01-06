package tta.intel.eus.senecapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Juego2Activity extends AppCompatActivity {

    String question = "¿Cuál de las siguientes opciones NO se indica en el fichero de manifiesto de la app?";
    String[] answers = {"Versión de la aplicación","Listado de los componentes de la aplicación","Opciones del menú de ajustes","Nivel mínimo de la API Android requerida","Nombre del paquete java de la aplicación"};
    boolean[] corrects = {false,false,true,false,false};
    String[] advise = {"https://www.google.es/","<html><body>The manifest describes the <b>components of the application</b>: " +
            "the activities, services, broadcast receivers, and content providers that...",null,"http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4","http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"};
    String[] adviseType = {"text/html","text/html",null,"video/mp4","audio/mp4"};
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego2);

        RadioGroup group = (RadioGroup)findViewById(R.id.test_choices);
        TextView test_text = (TextView)findViewById(R.id.test_text);
        test_text.setText(question);
        for(int i=0;i<answers.length;i++) {
            RadioButton rb = new RadioButton(this);
            group.addView(rb);
            rb.setText(answers[i]);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
                }
            });
        }
    }
    public void send (View view) {
        int correct=0;
        RadioGroup group = (RadioGroup)findViewById(R.id.test_choices);
        int choices = group.getChildCount();
        for(int i=0;i<choices;i++)
            group.getChildAt(i).setEnabled(false);

        for(int i=0;i<corrects.length;i++) {
            if(corrects[i]==true)
                correct=i;
        }

        ViewGroup layout = (ViewGroup)findViewById(R.id.test_layout);
        layout.removeView(findViewById(R.id.button_send_test));

        group.getChildAt(correct).setBackgroundColor(Color.GREEN);

        selected = group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
        if(selected!=correct) {
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),"¡Has fallado!",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(),"¡Correcto!",Toast.LENGTH_SHORT).show();
    }

}
