package tta.intel.eus.senecapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        String username = ((EditText)findViewById(R.id.loginUser)).getText().toString();
        String password = ((EditText)findViewById(R.id.loginPassword)).getText().toString();
    }
}
