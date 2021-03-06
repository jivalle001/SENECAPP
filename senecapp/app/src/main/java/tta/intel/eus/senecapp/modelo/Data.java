package tta.intel.eus.senecapp.modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tta.intel.eus.senecapp.modelo.Expresiones;
import tta.intel.eus.senecapp.modelo.Lugares;
import tta.intel.eus.senecapp.modelo.Parejas;
import tta.intel.eus.senecapp.modelo.RestClient;
import tta.intel.eus.senecapp.modelo.Tests;
import tta.intel.eus.senecapp.modelo.Usuario;

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
                parejas.getBoard().add(pareja);
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

    public Expresiones getExpresiones(RestClient restClient){
        try {
            Expresiones.Expresion expresion;
            Expresiones expresiones = new Expresiones();
            JSONObject json = restClient.getJson("requestExpresiones");
            expresiones.setTotalConversacion(json.getInt("totalConversacion"));
            expresiones.setTotalUniversidad(json.getInt("totalUniversidad"));
            expresiones.setTotalCompras(json.getInt("totalCompras"));
            JSONArray expresionConversacion = json.getJSONArray("expresionConversacion");
            for (int i=0;i<expresionConversacion.length();i++){
                expresion = new Expresiones.Expresion();
                JSONObject item = expresionConversacion.getJSONObject(i);
                expresion.setAudio(item.getString("audio"));
                expresion.setFrase1(item.getString("frase1"));
                expresion.setFrase2(item.getString("frase2"));
                expresion.setCategoria(item.getString("categoria"));
                expresiones.getExpresionConversacion().add(expresion);
            }
            JSONArray expresionUniversidad = json.getJSONArray("expresionUniversidad");
            for (int i=0;i<expresionUniversidad.length();i++){
                expresion = new Expresiones.Expresion();
                JSONObject item = expresionUniversidad.getJSONObject(i);
                expresion.setAudio(item.getString("audio"));
                expresion.setFrase1(item.getString("frase1"));
                expresion.setFrase2(item.getString("frase2"));
                expresion.setCategoria(item.getString("categoria"));
                expresiones.getExpresionUniversidad().add(expresion);
            }
            JSONArray expresionCompras = json.getJSONArray("expresionCompras");
            for (int i=0;i<expresionCompras.length();i++){
                expresion = new Expresiones.Expresion();
                JSONObject item = expresionCompras.getJSONObject(i);
                expresion.setAudio(item.getString("audio"));
                expresion.setFrase1(item.getString("frase1"));
                expresion.setFrase2(item.getString("frase2"));
                expresion.setCategoria(item.getString("categoria"));
                expresiones.getExpresionCompras().add(expresion);
            }
            return expresiones;
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

    public Lugares getLugares(RestClient restClient){
        try {
            Lugares.Lugar lugar;
            Lugares lugares = new Lugares();
            JSONObject json = restClient.getJson("requestLugares");
            lugares.setTotal(json.getInt("total"));
            JSONArray array = json.getJSONArray("lugar");
            for (int i=0;i<array.length();i++){
                lugar = new Lugares.Lugar();
                JSONObject item = array.getJSONObject(i);
                lugar.setLatitud((float) item.getDouble("latitud"));
                lugar.setLongitud((float) item.getDouble("longitud"));
                lugar.setNombre(item.getString("nombre"));
                lugar.setCategoria(item.getString("categoria"));
                lugar.setProvincia(item.getString("provincia"));
                lugares.getLugar().add(lugar);
            }
            return lugares;
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
