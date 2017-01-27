package tta.intel.eus.senecapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Juego1Activity extends AppCompatActivity {

    List<String> words;
    List<String> images;
    int[] imageViews = {R.id.imageView,R.id.imageView2,R.id.imageView3};
    int[] buttons = {R.id.button,R.id.button2,R.id.button3};
    Data data;
    List<String> pair1;
    List<String> pair2;
    List<String> pair3;
    int clicked = 0;
    List<Integer> clickedButtonsId;
    List<Integer> clickedImagesId;
    List<String> clickedButtonsTags;

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juego1);

        Integer[] arrayWords = new Integer[3];
        Integer[] arrayImages = new Integer[3];
        for(int i=0;i<arrayWords.length;i++){
            arrayWords[i]=i;
            arrayImages[i]=i;
        }

        Collections.shuffle(Arrays.asList(arrayWords));
        Collections.shuffle(Arrays.asList(arrayImages));

        loadPictures(0);
        loadWords(0,arrayImages,arrayWords);

    }


    private void pairs(Object tag, ImageView imageView, Button button) {
        String etiqueta = (String)tag;
        if(clicked==1){
            if(imageView!=null){
                imageView.setAlpha((float) 0.5);
                clickedImagesId.add(imageView.getId());
            }else {
                button.setAlpha((float)0.5);
                clickedButtonsId.add(button.getId());
            }
            clickedButtonsTags.add(etiqueta);
            clicked = 0;
            if(etiqueta.matches(pair1.get(0)) || etiqueta.matches(pair1.get(1))) {
                if(etiqueta.matches(pair1.get(0))) {
                    if(!clickedButtonsTags.get(0).matches(pair1.get(1))) {
                        if(imageView!=null){
                            imageView.setAlpha((float) 1);
                        }else {
                            button.setAlpha((float) 1);
                        }
                    }
                    else {
                        if(imageView!=null){
                            imageView.setVisibility(View.INVISIBLE);//Eliminar imageView y el otro botón (como se consigue el otro botón?)
                            Button b = (Button)findViewById(clickedButtonsId.get(0));
                            b.setVisibility(View.INVISIBLE);
                        }else {
                            //Eliminar button y el otro imageView (como se consigue el otro button?)

                        }
                    }
                }
            }
        }
    }

    public void cargarActividad(Integer[] arrayImages,Integer[] arrayWords){
        for(int i = 0; i<words.size(); i++){
            final ImageView imageView = (ImageView)findViewById(imageViews[arrayImages[i]]);
            imageView.setTag(images.get(arrayImages[i]));
            final Button button = (Button)findViewById(buttons[arrayWords[i]]);
            button.setTag(words.get(arrayWords[i]));
            button.setText(words.get(i));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pairs(imageView.getTag(),imageView,null);
                }
            });

            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    pairs(button.getTag(),null,button);
                }
            });

            new AsyncTask<Integer,Void,Void>() {
                URL url;
                Bitmap bitmap;

                @Override
                protected Void doInBackground(Integer... integers) {
                    try {
                        url = new URL(images.get(integers[0]));
                        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    imageView.setImageBitmap(bitmap);
                }
            }.execute(i);
        }
    }


    public void loadPictures(final int juego){
        final List<String> pictures = null;

        new AsyncTask<Void,Void,Void>() {
            Parejas parejas;
            @Override
            protected Void doInBackground(Void... voids) {
                data = new Data();
                parejas = data.getParejas(restClient);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                for (int i=0;i<3;i++) {
                    pictures.add((String) parejas.getBoard().get(juego).getArray().get(i));
                }
                pair1.add((String) parejas.getBoard().get(juego).getArray().get(0));
                pair2.add((String) parejas.getBoard().get(juego).getArray().get(1));
                pair3.add((String) parejas.getBoard().get(juego).getArray().get(2));

                images=pictures;
            }
        }.execute();
    }

    public void loadWords(final int juego, final Integer[] arrayImages, final Integer[] arrayWords){
        final List<String> words1 = null;
        new AsyncTask<Void,Void,Void>() {
            Parejas parejas;
            @Override
            protected Void doInBackground(Void... voids) {
                parejas = data.getParejas(restClient);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                for (int i=0;i<3;i++) {
                    words1.add((String) parejas.getBoard().get(juego).getArray().get(i+3));
                }
                pair1.add((String) parejas.getBoard().get(juego).getArray().get(3));
                pair2.add((String) parejas.getBoard().get(juego).getArray().get(4));
                pair3.add((String) parejas.getBoard().get(juego).getArray().get(5));

                words=words1;
                cargarActividad(arrayImages,arrayWords);
            }
        }.execute();
    }
}


