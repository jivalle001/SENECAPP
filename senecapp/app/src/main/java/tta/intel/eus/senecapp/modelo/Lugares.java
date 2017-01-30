package tta.intel.eus.senecapp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 29/01/17.
 */
public class Lugares {
    private int total;
    private List<Lugar> lugar = new ArrayList<Lugar>();

    public Lugares() {
    }

    public Lugares(int total, List<Lugar> lugar) {
        this.total = total;
        this.lugar = lugar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Lugar> getLugar() {
        return lugar;
    }

    public void setLugar(List<Lugar> lugar) {
        this.lugar = lugar;
    }

    public static class Lugar{
        private float latitud;
        private float longitud;
        private String nombre;
        private String categoria;
        private String provincia;

        public Lugar() {
        }

        public Lugar(float latitud, float longitud, String nombre, String categoria, String provincia) {
            this.latitud = latitud;
            this.longitud = longitud;
            this.nombre = nombre;
            this.categoria = categoria;
            this.provincia = provincia;
        }

        public float getLatitud() {
            return latitud;
        }

        public void setLatitud(float latitud) {
            this.latitud = latitud;
        }

        public float getLongitud() {
            return longitud;
        }

        public void setLongitud(float longitud) {
            this.longitud = longitud;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }
    }
}
