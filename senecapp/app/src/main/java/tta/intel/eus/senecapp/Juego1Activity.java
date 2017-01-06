package tta.intel.eus.senecapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Juego1Activity extends AppCompatActivity {

    String[] words = {"chico","chica","chico"};
    String[] images = {"chico1","chica1","chico1"};
    int[] imageViews = {R.id.imageView,R.id.imageView2,R.id.imageView3};
    int[] buttons = {R.id.button,R.id.button2,R.id.button3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego1);

        for(int i = 0;i<words.length;i++){
            ImageView imageView = (ImageView)findViewById(imageViews[i]);
            Button button = (Button)findViewById(buttons[i]);
            int id = getResources().getIdentifier("tta.intel.eus.senecapp:drawable/" + images[i], null, null);
            imageView.setImageResource(id);
            button.setText(words[i]);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pairs();
                }
            });
        }
    }

    public void pairs(){

    }

}


