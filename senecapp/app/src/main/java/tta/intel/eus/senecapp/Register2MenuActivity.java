package tta.intel.eus.senecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Register2MenuActivity extends AppCompatActivity {

    //String urlstrHombre = "https://dl.dropboxusercontent.com/s/mpe7n1ay606uhzg/chico1.jpg?dl=0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2_menu);

        /*Bitmap imgHombre;

        ImageButton ib = (ImageButton)findViewById(R.id.imageButtonHombre);
        imgHombre = getImage(urlstrHombre);
        if(imgHombre!=null)
            ib.setImageBitmap(imgHombre);*/
    }

    public void hombre(View view){
        Intent intent = new Intent(this,Register2HombreActivity.class);
        startActivity(intent);
    }

    public void mujer(View view){
        Intent intent = new Intent(this,Register2HombreActivity.class);
        startActivity(intent);
    }

    /*private Bitmap getImage(final String urlstr){
        Bitmap img = null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(urlstr);
                    HttpURLConnection c = (HttpURLConnection)url.openConnection();
                    c.setDoInput(true);
                    c.connect();
                    InputStream is = c.getInputStream();
                    img = BitmapFactory.decodeStream(is);
                    return img;
                }
                catch (MalformedURLException e){
                    Log.d("RemoteImageHandler","getImage passed invalid URL: "+urlstr);
                }
                catch (IOException e) {
                    Log.d("RemoteImageHandler","getImage IO Exception: "+e);
                }
                return img;
            }
        })


    }*/
}
