package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void seguir(View view){
        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.pass)).getText().toString();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String un = sharedPreferences.getString("username",null);
        if(un != username) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putBoolean("cambio",false);
            editor.commit();
            Intent intent = new Intent(this,Register2MenuActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();


    }
}
