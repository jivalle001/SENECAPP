package tta.intel.eus.senecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);
    }

    public void toLoginPage(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void toRegisterPage(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
