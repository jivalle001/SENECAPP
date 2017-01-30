package tta.intel.eus.senecapp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 29/01/17.
 */
public class Expresiones {
    private int totalConversacion;
    private int totalUniversidad;
    private int totalCompras;
    private List<Expresion> expresionConversacion = new ArrayList<Expresion>();
    private List<Expresion> expresionUniversidad = new ArrayList<Expresion>();
    private List<Expresion> expresionCompras = new ArrayList<Expresion>();

    public Expresiones() {
    }

    public Expresiones(int totalConversacion, int totalUniversidad, int totalCompras, List<Expresion> expresionConversacion, List<Expresion> expresionUniversidad, List<Expresion> expresionCompras) {
        this.totalConversacion = totalConversacion;
        this.totalUniversidad = totalUniversidad;
        this.totalCompras = totalCompras;
        this.expresionConversacion = expresionConversacion;
        this.expresionUniversidad = expresionUniversidad;
        this.expresionCompras = expresionCompras;
    }

    public int getTotalConversacion() {
        return totalConversacion;
    }

    public void setTotalConversacion(int totalConversacion) {
        this.totalConversacion = totalConversacion;
    }

    public int getTotalUniversidad() {
        return totalUniversidad;
    }

    public void setTotalUniversidad(int totalUniversidad) {
        this.totalUniversidad = totalUniversidad;
    }

    public int getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(int totalCompras) {
        this.totalCompras = totalCompras;
    }

    public List<Expresion> getExpresionConversacion() {
        return expresionConversacion;
    }

    public void setExpresionConversacion(List<Expresion> expresionConversacion) {
        this.expresionConversacion = expresionConversacion;
    }

    public List<Expresion> getExpresionUniversidad() {
        return expresionUniversidad;
    }

    public void setExpresionUniversidad(List<Expresion> expresionUniversidad) {
        this.expresionUniversidad = expresionUniversidad;
    }

    public List<Expresion> getExpresionCompras() {
        return expresionCompras;
    }

    public void setExpresionCompras(List<Expresion> expresionCompras) {
        this.expresionCompras = expresionCompras;
    }

    public static class Expresion {
        private String audio;
        private String frase1;
        private String frase2;
        private String categoria;

        public Expresion() {
        }

        public Expresion(String audio, String frase1, String frase2, String categoria) {
            this.audio = audio;
            this.frase1 = frase1;
            this.frase2 = frase2;
            this.categoria = categoria;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getFrase1() {
            return frase1;
        }

        public void setFrase1(String frase1) {
            this.frase1 = frase1;
        }

        public String getFrase2() {
            return frase2;
        }

        public void setFrase2(String frase2) {
            this.frase2 = frase2;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
    }
}
