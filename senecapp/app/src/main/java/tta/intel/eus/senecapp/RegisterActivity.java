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

public class RegisterActivity extends AppCompatActivity {

    RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/senecappServidor/rest/Senecapp");
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void seguir(View view){
        final String name = ((EditText)findViewById(R.id.name)).getText().toString();
        final String surname1 = ((EditText)findViewById(R.id.surname1)).getText().toString();
        final String surname2 = ((EditText)findViewById(R.id.surname2)).getText().toString();
        final String username = ((EditText)findViewById(R.id.username)).getText().toString();
        final String pass = ((EditText)findViewById(R.id.pass)).getText().toString();

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
                if(usuario.getUsuario().matches(username)){
                    Toast.makeText(getApplicationContext(), "Usuario ya registrado", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String un = sharedPreferences.getString("username",null);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("cambio",false);
                    editor.commit();

                    Bundle bundle = new Bundle();
                    bundle.putString("apellido1",surname1);
                    bundle.putString("apellido2",surname2);
                    bundle.putString("pass",pass);
                    bundle.putString("name",name);
                    bundle.putString("username",username);

                    Intent intent = new Intent(getApplicationContext(),Register2MenuActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }.execute();
    }
}
