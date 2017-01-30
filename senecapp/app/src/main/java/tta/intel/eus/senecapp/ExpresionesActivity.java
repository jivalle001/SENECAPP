package tta.intel.eus.senecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExpresionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expresiones);

        setTitle(R.string.expresionesTitle);
    }

    public void expresiones1(View view){
        Intent intent = new Intent(this,Expresiones1Activity.class);
        startActivity(intent);
    }

    public void expresiones2(View view){
        Intent intent = new Intent(this,Expresiones2Activity.class);
        startActivity(intent);
    }

    public void expresiones3(View view){
        Intent intent = new Intent(this,Expresiones3Activity.class);
        startActivity(intent);
    }
}
