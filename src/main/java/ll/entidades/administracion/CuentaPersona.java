package ll.entidades.administracion;

public class CuentaPersona {

    private Cuenta objCuenta;
    private String titular;
    private String Usuario;

    public CuentaPersona() {
        objCuenta = new Cuenta();
    }

    public Cuenta getObjCuenta() {
        return objCuenta;
    }

    public void setObjCuenta(Cuenta objCuenta) {
        this.objCuenta = objCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

}
