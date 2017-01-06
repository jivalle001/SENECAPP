package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        String username = ((EditText)findViewById(R.id.loginUser)).getText().toString();
        String password = ((EditText)findViewById(R.id.loginPassword)).getText().toString();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String un = sharedPreferences.getString("username",null);
        String p = sharedPreferences.getString("password",null);
        if(un.matches(username) && p.matches(password)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("cambio",true);
            editor.commit();
            Intent intent = new Intent(this,MenuActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Login incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
