package tta.intel.eus.senecapp;

import android.content.Intent;
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
import java.util.ArrayList;
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
    int turns=0;
    int juego = 0;
    Parejas parejas;

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juego1);

        setTitle(R.string.juego1Title);

        Integer[] arrayWords = shuffleArray();
        Integer[] arrayImages = shuffleArray();

        loadInfo(arrayImages,arrayWords);
    }

    public Integer[] shuffleArray(){ //Para desordenar los elementos del juego
        Integer[] array = new Integer[3];
        for(int i=0;i<array.length;i++) {
            array[i] = i;
        }
        Collections.shuffle(Arrays.asList(array));
        return array;
    }

    private void pairs(Object tag, ImageView imageView, Button button) {
        if(imageView==null)
        {
            for(int i=0;i<imageViews.length;i++)
            {
                if(clicked==0){
                    Button button1 = (Button)findViewById(buttons[i]);
                    button1.setEnabled(false);
                }
                ImageView imageView1 = (ImageView)findViewById(imageViews[i]);
                imageView1.setEnabled(true);
            }
        }
        else
        {
            for(int i=0;i<buttons.length;i++)
            {
                if(clicked==0){
                    ImageView imageView1 = (ImageView)findViewById(imageViews[i]);
                    imageView1.setEnabled(false);
                }
                Button button1 = (Button)findViewById(buttons[i]);
                button1.setEnabled(true);
            }
        }
        String etiqueta = (String)tag;
        if(clicked==1){ //SI YA SE HA CLICKADO OTRO ELEMENTO DE LA PANTALLA ANTERIORMENTE
            if(imageView!=null){ //Si ha sido una imagen aumentamos opacidad y lo añadimos a la lista de imágenes clickadas
                imageView.setAlpha((float) 0.5);
                clickedImagesId.add(imageView.getId());
            }else { //Si ha sido un botón aumentamos la opacidad y lo añadimos a la lista de botones clickados
                button.setAlpha((float)0.5);
                clickedButtonsId.add(button.getId());
            }
            clickedButtonsTags.add(etiqueta); //Se añade el tag del elemento clickado
            clicked = 0;

            //SI EL BOTÓN O IMAGEVIEW CLICKADO CONTIENE ALGUNO DE LOS ELEMENTO GUARDADOS EN LA PAREJA 1
                if(etiqueta.equals(pair1.get(0)) || etiqueta.equals(pair1.get(1))) {
                    if (etiqueta.equals(pair1.get(0))) { //Si coincide con el primer elemento de la pareja, el otro elemento clickado tiene que ser igual al segundo elemento de la pareja
                        if (!clickedButtonsTags.get(0).equals(pair1.get(1))) {//Si no coincide, tenemos que devolver la opacidad a 1
                            if (imageView != null) {
                                imageView.setAlpha((float) 1);
                                Button button1 = (Button) findViewById(clickedButtonsId.get(0));
                                button1.setAlpha((float) 1);
                            } else {
                                button.setAlpha((float) 1);
                                ImageView imageView1 = (ImageView)findViewById(clickedImagesId.get(0));
                                imageView1.setAlpha((float)1);
                            }
                        } else {//Si coincide, hacemos invisibles los dos elementos
                            if (imageView != null) {
                                imageView.setVisibility(View.INVISIBLE);//Eliminar imageView y el otro botón (como se consigue el otro botón?)
                                Button b = (Button) findViewById(clickedButtonsId.get(0));
                                b.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            } else {
                                button.setVisibility(View.INVISIBLE);//Eliminar button y el otro imageView (como se consigue el otro button?)
                                ImageView iv = (ImageView) findViewById(clickedImagesId.get(0));
                                iv.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            }
                        }
                    } else {
                        if (!clickedButtonsTags.get(0).equals(pair1.get(0))) {//Si no coincide, tenemos que devolver la opacidad a 1
                            if (imageView != null) {
                                imageView.setAlpha((float) 1);
                                Button button1 = (Button) findViewById(clickedButtonsId.get(0));
                                button1.setAlpha((float) 1);
                            } else {
                                button.setAlpha((float) 1);
                                ImageView imageView1 = (ImageView)findViewById(clickedImagesId.get(0));
                                imageView1.setAlpha((float)1);
                            }
                        } else {//Si coincide, hacemos invisibles los dos elementos
                            if (imageView != null) {
                                imageView.setVisibility(View.INVISIBLE);//Eliminar imageView y el otro botón (como se consigue el otro botón?)
                                Button b = (Button) findViewById(clickedButtonsId.get(0));
                                b.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            } else {
                                button.setVisibility(View.INVISIBLE);//Eliminar button y el otro imageView (como se consigue el otro button?)
                                ImageView iv = (ImageView) findViewById(clickedImagesId.get(0));
                                iv.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            }
                        }
                    }
                }
            //SI EL BOTÓN O IMAGEVIEW CLICKADO CONTIENE ALGUNO DE LOS ELEMENTO GUARDADOS EN LA PAREJA 2
            else if(etiqueta.equals(pair2.get(0)) || etiqueta.equals(pair2.get(1))) {
                    if (etiqueta.equals(pair2.get(0))) { //Si coincide con el primer elemento de la pareja, el otro elemento clickado tiene que ser igual al segundo elemento de la pareja
                        if (!clickedButtonsTags.get(0).matches(pair2.get(1))) {//Si no coincide, tenemos que devolver la opacidad a 1
                            if (imageView != null) {
                                imageView.setAlpha((float) 1);
                                Button button1 = (Button) findViewById(clickedButtonsId.get(0));
                                button1.setAlpha((float) 1);
                            } else {
                                button.setAlpha((float) 1);
                                ImageView imageView1 = (ImageView)findViewById(clickedImagesId.get(0));
                                imageView1.setAlpha((float)1);
                            }
                        } else {//Si coincide, hacemos invisibles los dos elementos
                            if (imageView != null) {
                                imageView.setVisibility(View.INVISIBLE);//Eliminar imageView y el otro botón (como se consigue el otro botón?)
                                Button b = (Button) findViewById(clickedButtonsId.get(0));
                                b.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            } else {
                                button.setVisibility(View.INVISIBLE);//Eliminar button y el otro imageView (como se consigue el otro button?)
                                ImageView iv = (ImageView) findViewById(clickedImagesId.get(0));
                                iv.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            }
                        }
                    } else {
                        if (!clickedButtonsTags.get(0).equals(pair2.get(0))) {//Si no coincide, tenemos que devolver la opacidad a 1
                            if (imageView != null) {
                                imageView.setAlpha((float) 1);
                                Button button1 = (Button) findViewById(clickedButtonsId.get(0));
                                button1.setAlpha((float) 1);
                            } else {
                                button.setAlpha((float) 1);
                                ImageView imageView1 = (ImageView)findViewById(clickedImagesId.get(0));
                                imageView1.setAlpha((float)1);
                            }
                        } else {//Si coincide, hacemos invisibles los dos elementos
                            if (imageView != null) {
                                imageView.setVisibility(View.INVISIBLE);//Eliminar imageView y el otro botón (como se consigue el otro botón?)
                                Button b = (Button) findViewById(clickedButtonsId.get(0));
                                b.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            } else {
                                button.setVisibility(View.INVISIBLE);//Eliminar button y el otro imageView (como se consigue el otro button?)
                                ImageView iv = (ImageView) findViewById(clickedImagesId.get(0));
                                iv.setVisibility(View.INVISIBLE);
                                turns=turns+2;
                            }
                        }
                    }
                }
            //SI EL BOTÓN O IMAGEVIEW CLICKADO CONTIENE ALGUNO DE LOS ELEMENTO GUARDADOS EN LA PAREJA 3
            else if(etiqueta.equals(pair3.get(0)) || etiqueta.equals(pair3.get(1))) {
                if (etiqueta.equals(pair3.get(0))) { //Si coincide con el primer elemento de la pareja, el otro elemento clickado tiene que ser igual al segundo elemento de la pareja
                    if (!clickedButtonsTags.get(0).equals(pair3.get(1))) {//Si no coincide, tenemos que devolver la opacidad a 1
                        if (imageView != null) {
                            imageView.setAlpha((float) 1);
                            Button button1 = (Button) findViewById(clickedButtonsId.get(0));
                            button1.setAlpha((float) 1);
                        } else {
                            button.setAlpha((float) 1);
                            ImageView imageView1 = (ImageView)findViewById(clickedImagesId.get(0));
                            imageView1.setAlpha((float)1);
                        }
                    } else {//Si coincide, hacemos invisibles los dos elementos
                        if (imageView != null) {
                            imageView.setVisibility(View.INVISIBLE);//Eliminar imageView y el otro botón (como se consigue el otro botón?)
                            Button b = (Button) findViewById(clickedButtonsId.get(0));
                            b.setVisibility(View.INVISIBLE);
                            turns=turns+2;
                        } else {
                            button.setVisibility(View.INVISIBLE);//Eliminar button y el otro imageView (como se consigue el otro button?)
                            ImageView iv = (ImageView) findViewById(clickedImagesId.get(0));
                            iv.setVisibility(View.INVISIBLE);
                            turns=turns+2;
                        }
                    }
                } else {
                    if (!clickedButtonsTags.get(0).equals(pair3.get(0))) {//Si no coincide, tenemos que devolver la opacidad a 1
                        if (imageView != null) {
                            imageView.setAlpha((float) 1);
                            Button button1 = (Button) findViewById(clickedButtonsId.get(0));
                            button1.setAlpha((float) 1);
                        } else {
                            button.setAlpha((float) 1);
                            ImageView imageView1 = (ImageView)findViewById(clickedImagesId.get(0));
                            imageView1.setAlpha((float)1);
                        }
                    } else {//Si coincide, hacemos invisibles los dos elementos
                        if (imageView != null) {
                            imageView.setVisibility(View.INVISIBLE);//Eliminar imageView y el otro botón (como se consigue el otro botón?)
                            Button b = (Button) findViewById(clickedButtonsId.get(0));
                            b.setVisibility(View.INVISIBLE);
                            turns=turns+2;
                        } else {
                            button.setVisibility(View.INVISIBLE);//Eliminar button y el otro imageView (como se consigue el otro button?)
                            ImageView iv = (ImageView) findViewById(clickedImagesId.get(0));
                            iv.setVisibility(View.INVISIBLE);
                            turns=turns+2;
                        }
                    }
                }
            }
            if(turns == 6) {
                //Aparece el botón de siguiente, se pasa al siguiente test y se inicializan todos los parámetros
                Button button1 = (Button)findViewById(R.id.buttonSiguiente);
                button1.setVisibility(View.VISIBLE);
                juego++;
                if(juego == parejas.getTotal()){
                    button1.setText(R.string.fin);
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }
        else { //SI NO SE HA CLICKADO OTRO ELEMENTO DE LA PANTALLA ANTERIORMENTE
            clickedButtonsId = new ArrayList<Integer>();
            clickedImagesId = new ArrayList<Integer>();
            if (imageView != null) { //Si ha sido una imagen aumentamos opacidad y lo añadimos a la lista de imágenes clickadas
                imageView.setAlpha((float) 0.5);
                clickedImagesId.add(imageView.getId());
            } else { //Si ha sido un botón aumentamos la opacidad y lo añadimos a la lista de botones clickados
                button.setAlpha((float) 0.5);
                clickedButtonsId.add(button.getId());
            }
            clickedButtonsTags = new ArrayList<String>();//Se inicializa
            clickedButtonsTags.add(etiqueta); //Se añade el tag del elemento clickado
            clicked = 1;
        }
    }

    public void cargarActividad(Integer[] arrayImages,Integer[] arrayWords){
        for(int i = 0; i<words.size(); i++){
            final ImageView imageView = (ImageView)findViewById(imageViews[arrayImages[i]]);
            imageView.setTag(images.get(i));
            final Button button = (Button)findViewById(buttons[arrayWords[i]]);
            button.setTag(words.get(i));
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

    public void loadInfo(final Integer[] arrayImages, final Integer[] arrayWords) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                data = new Data();
                parejas = data.getParejas(restClient);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                loadPictures(juego);
                loadWords(juego);
                cargarActividad(arrayImages,arrayWords);
            }
        }.execute();
    }


    public void loadPictures(final int juego){
        final List<String> pictures = new ArrayList<String>();
        for (int i=0;i<3;i++) {
            pictures.add((String) parejas.getBoard().get(juego).getArray().get(i));
        }
        pair1 = new ArrayList<String>();
        pair2 = new ArrayList<String>();
        pair3 = new ArrayList<String>();
        pair1.add((String) parejas.getBoard().get(juego).getArray().get(0));
        pair2.add((String) parejas.getBoard().get(juego).getArray().get(1));
        pair3.add((String) parejas.getBoard().get(juego).getArray().get(2));

        images=pictures;
    }

    public void loadWords(final int juego){
        final List<String> words1 = new ArrayList<String>();
        for (int i=0;i<3;i++) {
            words1.add((String) parejas.getBoard().get(juego).getArray().get(i+3));
        }
        pair1.add((String) parejas.getBoard().get(juego).getArray().get(3));
        pair2.add((String) parejas.getBoard().get(juego).getArray().get(4));
        pair3.add((String) parejas.getBoard().get(juego).getArray().get(5));
        words=words1;
    }

    public void siguienteJuego(View view) {
        words = new ArrayList<String>();
        images = new ArrayList<String>();
        pair1 = new ArrayList<String>();
        pair2 = new ArrayList<String>();
        pair3 = new ArrayList<String>();
        clicked = 0;
        clickedButtonsId = new ArrayList<Integer>();
        clickedImagesId = new ArrayList<Integer>();
        clickedButtonsTags = new ArrayList<String>();
        turns=0;

        setContentView(R.layout.activity_juego1);

        setTitle(R.string.juego1Title);

        Integer[] arrayWords = shuffleArray();
        Integer[] arrayImages = shuffleArray();

        loadInfo(arrayImages,arrayWords);
    }
}


