package tta.intel.eus.senecapp.modelo;

/**
 * Created by jose on 18/01/17.
 */
public class Usuario {
    private String apellido1;
    private String apellido2;
    private String avatar;
    private String contraseña;
    private String nombre;
    private String usuario;

    public Usuario() {
    }

    public Usuario(String apellido1, String apellido2, String avatar, String contraseña, String nombre, String usuario) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.avatar = avatar;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
