package tta.intel.eus.senecapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Juego2Activity extends AppCompatActivity {

    /*String question = "¿Cuál de las siguientes opciones NO se indica en el fichero de manifiesto de la app?";
    String[] answers = {"Versión de la aplicación","Listado de los componentes de la aplicación","Opciones del menú de ajustes","Nivel mínimo de la API Android requerida","Nombre del paquete java de la aplicación"};
    boolean[] corrects = {false,false,true,false,false};
    String[] advise = {"https://www.google.es/","<html><body>The manifest describes the <b>components of the application</b>: " +
            "the activities, services, broadcast receivers, and content providers that...",null,"http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4","http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"};
    String[] adviseType = {"text/html","text/html",null,"video/mp4","audio/mp4"};*/
    int selected;
    Data data;
    Tests tests;
    int num = 0;

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego2);

        loadInfo();
    }

    public void loadInfo() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                data = new Data();
                tests = data.getTests(restClient);
                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                super.onPostExecute(aVoid);
                RadioGroup group = (RadioGroup)findViewById(R.id.test_choices);
                TextView test_text = (TextView)findViewById(R.id.test_text);
                test_text.setText(tests.getTest().get(num).getQuestion());
                for(int i=0;i<tests.getTest().get(num).getResp().size();i++) {
                    final RadioButton rb = new RadioButton(getApplicationContext());
                    group.addView(rb);

                    new AsyncTask<Integer,Void,Integer>() {
                        URL url;
                        Bitmap bitmap;

                        @Override
                        protected Integer doInBackground(Integer... integers) {
                            try {
                                url = new URL(tests.getTest().get(num).getResp().get(integers[0]));
                                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Integer integer) {
                            //super.onPostExecute(aVoid);
                            Drawable drawable = new BitmapDrawable(getResources(),Bitmap.createScaledBitmap(bitmap,350,250,true));
                            rb.setButtonDrawable(drawable);
                            //rb.setHeight(500);
                            //rb.setWidth(500);
                            //imageView.setImageBitmap(bitmap);
                            //rb.setText(tests.getTest().get(num).getResp().get(integer));
                            rb.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    send(v);
                                }
                            });
                        }
                    }.execute(i);
                }
            }
        }.execute();
    }

    public void send (View view) {
        int correct=tests.getTest().get(num).getCorrect();
        RadioGroup group = (RadioGroup)findViewById(R.id.test_choices);
        int choices = group.getChildCount();
        for(int i=0;i<choices;i++)
            group.getChildAt(i).setEnabled(false);

        ViewGroup layout = (ViewGroup)findViewById(R.id.test_layout);
        layout.removeView(findViewById(R.id.button_send_test));

        group.getChildAt(correct).setBackgroundColor(Color.GREEN);

        selected = group.indexOfChild(group.findViewById(group.getCheckedRadioButtonId()));
        if(selected!=correct) {
            num++;
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),"¡Has fallado!",Toast.LENGTH_SHORT).show();
            findViewById(R.id.button_next_test).setVisibility(View.VISIBLE);
            if(num==tests.getTotal())
            {
                Button button = (Button) findViewById(R.id.button_next_test);
                button.setText("FIN");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                    }
                });
            }

        }
        else {
            num++;
            Toast.makeText(getApplicationContext(),"¡Correcto!",Toast.LENGTH_SHORT).show();
            findViewById(R.id.button_next_test).setVisibility(View.VISIBLE);
            if(num==tests.getTotal())
            {
                Button button = (Button) findViewById(R.id.button_next_test);
                button.setText("FIN");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public void nextTest(View view){
        setContentView(R.layout.activity_juego2);
        loadInfo();
    }

}
