package tta.intel.eus.senecapp.presentador;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import tta.intel.eus.senecapp.R;

public class AjustesActivity extends AppCompatActivity {

    Button button;
    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        setTitle(R.string.ajustesTitle);

        button = ((Button)findViewById(R.id.idiomaButton));

        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }});
    }

    private void showDialog(){
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
                Intent refresh = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }


    public void avatar(View view){
        Intent intent = new Intent(this,Register2MenuActivity.class);
        startActivity(intent);
    }


}
