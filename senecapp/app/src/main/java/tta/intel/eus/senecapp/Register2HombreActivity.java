package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Register2HombreActivity extends AppCompatActivity {

    ImageView b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2_hombre);

        b1 = (ImageView)findViewById(R.id.imagechico1);
        b2 = (ImageView)findViewById(R.id.imageChico2);
        b3 = (ImageView)findViewById(R.id.imageChico3);
        b4 = (ImageView)findViewById(R.id.imageChico4);

        b1.setOnClickListener(myhandler1);
        b2.setOnClickListener(myhandler2);
        b3.setOnClickListener(myhandler3);
        b4.setOnClickListener(myhandler4);

    }

    View.OnClickListener myhandler1 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chico1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            boolean cambio = sharedPreferences.getBoolean("cambio",false);
            if(cambio!=true) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        }
    };

    View.OnClickListener myhandler2 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chico1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            boolean cambio = sharedPreferences.getBoolean("cambio",false);
            if(cambio!=true) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        }
    };

    View.OnClickListener myhandler3 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chico1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            boolean cambio = sharedPreferences.getBoolean("cambio",false);
            if(cambio!=true) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        }
    };

    View.OnClickListener myhandler4 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chico1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            boolean cambio = sharedPreferences.getBoolean("cambio",false);
            if(cambio!=true) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        }
    };

}
