package tta.intel.eus.senecapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(R.string.loginTitle);
        usuario = new Usuario();
    }

    public void login(View view){
        final String username = ((EditText)findViewById(R.id.loginUser)).getText().toString();
        final String password = ((EditText)findViewById(R.id.loginPassword)).getText().toString();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final Data data = new Data();

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                usuario = data.getUsuario(username,restClient);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(usuario != null && !username.isEmpty() && !password.isEmpty()){
                    if(username.matches(usuario.getUsuario()) && password.matches(usuario.getContraseña())){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("cambio",true);
                        editor.putString("username",username);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),R.string.loginIncorrecto, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //Porque no se ha introducido uno o los dos campos
                    if(username.isEmpty() || password.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), R.string.loginCompletar, Toast.LENGTH_SHORT).show();
                    }
                    //Porque el usuario introducido no está en la base de datos
                    else
                    {
                        Toast.makeText(getApplicationContext(),R.string.loginIncorrecto, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }.execute();
    }
}
