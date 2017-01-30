package tta.intel.eus.senecapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MapasActivity extends AppCompatActivity {

    Button bizkaia,araba,gipuzkoa,ocio,gastronomia,arte,deporte,edificios,lugares;
    Data data;
    Lugares places;

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);

        setTitle(R.string.mapasTitle);

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                data = new Data();
                places = data.getLugares(restClient);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                bizkaia = (Button)findViewById(R.id.bizkaiaButton);
                bizkaia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadButtons("Bizkaia");
                    }
                });

                araba = (Button)findViewById(R.id.arabaButton);
                araba.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadButtons("Araba");
                    }
                });

                gipuzkoa = (Button)findViewById(R.id.gipuzkoaButton);
                gipuzkoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadButtons("Gipuzkoa");
                    }
                });

                ocio = (Button)findViewById(R.id.ocioButton);
                gastronomia = (Button)findViewById(R.id.gastronomiaButton);
                arte = (Button)findViewById(R.id.arteButton);
                deporte = (Button)findViewById(R.id.deporteButton);
                edificios = (Button)findViewById(R.id.edificiosButton);
                lugares = (Button)findViewById(R.id.lugaresButton);
            }
        }.execute();

    }

    public void loadButtons(final String provincia){
        bizkaia.setVisibility(View.INVISIBLE);
        araba.setVisibility(View.INVISIBLE);
        gipuzkoa.setVisibility(View.INVISIBLE);

        ocio.setVisibility(View.VISIBLE);
        ocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTable(provincia,"ocio");
            }
        });
        gastronomia.setVisibility(View.VISIBLE);
        gastronomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTable(provincia,"gastronomia");
            }
        });
        arte.setVisibility(View.VISIBLE);
        arte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTable(provincia,"Arte");
            }
        });
        deporte.setVisibility(View.VISIBLE);
        deporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTable(provincia,"Deporte");
            }
        });
        edificios.setVisibility(View.VISIBLE);
        edificios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTable(provincia,"Edificios Municipales");
            }
        });
        lugares.setVisibility(View.VISIBLE);
        lugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTable(provincia,"Lugares de interes");
            }
        });
    }

    public void loadTable(String provincia, String categoria){
        ocio.setVisibility(View.INVISIBLE);
        gastronomia.setVisibility(View.INVISIBLE);
        arte.setVisibility(View.INVISIBLE);
        deporte.setVisibility(View.INVISIBLE);
        edificios.setVisibility(View.INVISIBLE);
        lugares.setVisibility(View.INVISIBLE);

        final List<Lugares.Lugar> lista = new ArrayList<Lugares.Lugar>();

        for(int i=0;i<places.getTotal();i++){
            if(places.getLugar().get(i).getCategoria().equals(categoria)){
                if(places.getLugar().get(i).getProvincia().equals(provincia)){
                    lista.add(places.getLugar().get(i));
                }
            }
        }

        TableLayout tableLayout = (TableLayout)findViewById(R.id.table);

        TableRow tr_head = new TableRow(this);

        for (int i=0;i<lista.size();i++){
            int count = 0;
            final String nombre = lista.get(i).getNombre();
            Button button = new Button(this);
            button.setText(R.string.mostrarButton);
            final Float latitud = places.getLugar().get(i).getLatitud();
            final Float longitud = places.getLugar().get(i).getLongitud();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("geo:"+latitud+","+longitud+"?q="+latitud+","+longitud+"("+nombre+")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW,uri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if(mapIntent.resolveActivity(getPackageManager())!=null){
                        startActivity(mapIntent);
                    }
                    else{
                        Uri uri2 = Uri.parse("http://maps.google.com/maps?q=loc:"+latitud+","+longitud);
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri2);
                        startActivity(intent);
                    }
                }
            });
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView label_nombre = new TextView(this);
            label_nombre.setText(nombre);
            label_nombre.setPadding(5,5,5,5);
            tr.addView(label_nombre);
            tr.addView(button);

            tableLayout.addView(tr,new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            count++;

        }

    }
}
