package tta.intel.eus.senecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActividadesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
    }

    public void juego1(View view){
        Intent intent = new Intent(this,Juego1Activity.class);
        startActivity(intent);
    }
}
