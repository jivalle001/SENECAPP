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

        setTitle(R.string.app_name);
    }

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
