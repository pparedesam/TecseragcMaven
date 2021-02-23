package ll.entidades.administracion;

public class EstadoCuenta {

    private String IdEstadoCuenta;
    private String Estado;
    private String Nivel;

    public EstadoCuenta() {

    }

    public String getIdEstadoCuenta() {
        return IdEstadoCuenta;
    }

    public void setIdEstadoCuenta(String idEstadoCuenta) {
        IdEstadoCuenta = idEstadoCuenta;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

}
