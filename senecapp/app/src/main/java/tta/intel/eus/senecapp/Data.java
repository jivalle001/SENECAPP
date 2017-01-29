package tta.intel.eus.senecapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 18/01/17.
 */
public class Data {

    public Data() {
    }

    public Usuario getUsuario(String usuario, RestClient restClient){
        try {
            Usuario usuario1 = new Usuario();
            JSONObject json = restClient.getJson(String.format("requestUsuario?usuario=%s",usuario));
            usuario1.setApellido1(json.getString("apellido1"));
            usuario1.setApellido2(json.getString("apellido2"));
            usuario1.setAvatar(json.getString("avatar"));
            usuario1.setContraseña(json.getString("contrasena"));
            usuario1.setNombre(json.getString("nombre"));
            usuario1.setUsuario(json.getString("usuario"));
            return usuario1;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject putUsuario(Usuario usuario) {
        try{
            JSONObject json = new JSONObject();
            json.put("apellido1",usuario.getApellido1());
            json.put("apellido2",usuario.getApellido2());
            json.put("avatar",usuario.getAvatar());
            json.put("contrasena",usuario.getContraseña());
            json.put("nombre",usuario.getNombre());
            json.put("usuario",usuario.getUsuario());
            JSONArray array = new JSONArray();
            return json;
            /*for (Test.Choice choice : test.getChoices()){
                JSONObject item = new JSONObject();
                item.put("id",choice.getId());
                item.put("wording",choice.getAnswer());
                item.put("correct",choice.isCorrect());
                item.put("advise",choice.getAdvise());
                item.put("mime",choice.getMime());
                array.put(item);
            }
            json.put("choices",array);*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Parejas getParejas(RestClient restClient){
        Parejas.Pareja pareja;
        try {
            Parejas parejas = new Parejas();
            JSONObject json = restClient.getJson("requestParejas");
            parejas.setTotal(json.getInt("total"));
            JSONArray board = json.getJSONArray("board");
            for(int i = 0 ; i < board.length() ; i++){
                JSONObject item = board.getJSONObject(i);
                JSONArray jsonArray1 = item.getJSONArray("array");
                JSONArray jsonArray2 = item.getJSONArray("pair1");
                JSONArray jsonArray3 = item.getJSONArray("pair2");
                JSONArray jsonArray4 = item.getJSONArray("pair3");
                List<String> array = new ArrayList<String>();
                List<String> pair1 = new ArrayList<String>();
                List<String> pair2 = new ArrayList<String>();
                List<String> pair3 = new ArrayList<String>();
                for(int j=0;j<jsonArray1.length();j++){
                    array.add(jsonArray1.getString(j));
                }
                for(int j=0;j<jsonArray2.length();j++){
                    pair1.add(jsonArray2.getString(j));
                    pair2.add(jsonArray3.getString(j));
                    pair3.add(jsonArray4.getString(j));
                }
                pareja = new Parejas.Pareja();
                pareja.setArray(array);
                pareja.setPair1(pair1);
                pareja.setPair2(pair2);
                pareja.setPair3(pair3);
                parejas.getBoard().add(pareja);//SALTA UNA EXCEPCIÓN!!!!
            }
            return parejas;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Tests getTests(RestClient restClient){
        try {
            Tests.Test test;
            Tests tests = new Tests();
            List<String> lista;
            JSONObject json = restClient.getJson("requestTests");
            tests.setTotal(json.getInt("total"));
            JSONArray array = json.getJSONArray("test");
            for (int i=0;i<array.length();i++){
                test = new Tests.Test();
                JSONObject item = array.getJSONObject(i);
                test.setCorrect(item.getInt("correct"));
                test.setQuestion(item.getString("question"));
                JSONArray array2 = item.getJSONArray("resp");
                lista = new ArrayList<String>();
                for (int j=0;j<array2.length();j++){
                    lista.add(String.valueOf(array2.getString(j)));
                }
                test.setResp(lista);
                tests.getTest().add(test);
            }
            return tests;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
