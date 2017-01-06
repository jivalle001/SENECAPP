package tta.intel.eus.senecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void actividades(View view){
        Intent intent = new Intent(this,ActividadesActivity.class);
        startActivity(intent);
    }

    public void expresiones(View view){
        Intent intent = new Intent(this,ExpresionesActivity.class);
        startActivity(intent);
    }

    public void mapas(View view){
        Intent intent = new Intent(this,MapasActivity.class);
        startActivity(intent);
    }

    public void ajustes(View view){
        Intent intent = new Intent(this,AjustesActivity.class);
        startActivity(intent);
    }
}
