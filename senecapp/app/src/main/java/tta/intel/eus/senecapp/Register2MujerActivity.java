package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Register2MujerActivity extends AppCompatActivity {

    ImageView b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2_mujer);

        b1 = (ImageView)findViewById(R.id.imageChica1);
        b2 = (ImageView)findViewById(R.id.imageChica2);
        b3 = (ImageView)findViewById(R.id.imageChica3);
        b4 = (ImageView)findViewById(R.id.imageChica4);

        b1.setOnClickListener(myhandler1);
        b2.setOnClickListener(myhandler2);
        b3.setOnClickListener(myhandler3);
        b4.setOnClickListener(myhandler4);

    }

    View.OnClickListener myhandler1 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chica1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener myhandler2 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chica1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener myhandler3 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chica1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener myhandler4 = new View.OnClickListener(){
        public void onClick(View view){
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            ImageView imageView = (ImageView) view;
            int imageId = R.drawable.chica1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("avatar",imageId);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
            startActivity(intent);
        }
    };
}
