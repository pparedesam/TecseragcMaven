package ll.entidades.administracion;

public class Usuario {

    private String idUsuario;
    private String clave;
    private String estado;
    private Empleado objEmpleado;
    private String foto;
    private String imei;

    public Usuario() {
        objEmpleado = new Empleado();
    }

    public Usuario(String idUsuario, String clave, String estado, Empleado objEmpleado) {
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.estado = estado;
        this.objEmpleado = objEmpleado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Usuario(Object attribute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getObjEmpleado() {
        return objEmpleado;
    }

    public void setObjEmpleado(Empleado objEmpleado) {
        this.objEmpleado = objEmpleado;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

}
