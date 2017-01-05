package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textview;
    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*button = ((Button)findViewById(R.id.button));

        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }});*/
    }

    /*private void showDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.str_button));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.idiomas);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which){
                    case 0:
                        locale = new Locale("es");
                        config.locale =locale;
                        break;
                    case 1:
                        locale = new Locale("eu");
                        config.locale =locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }*/

    public void castellano(View view){
        locale = new Locale("es");
        config.locale = locale;
        getResources().updateConfiguration(config, null);
        Intent intent = new Intent(this,LoginMenuActivity.class);
        startActivity(intent);
    }

    public void euskara(View view){
        locale = new Locale("eu");
        config.locale = locale;
        getResources().updateConfiguration(config, null);
        Intent intent = new Intent(this,LoginMenuActivity.class);
        startActivity(intent);
    }
}
