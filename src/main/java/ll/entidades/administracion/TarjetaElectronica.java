package ll.entidades.administracion;

public class TarjetaElectronica {

    private int codigo;
    private Persona objPersona;
    private Oficina objOficina;
    private String nroTarjeta;
    private String clave;
    private String estado;
    private String idUsuario;
    private String estadoBloqueo;

    public String getEstadoBloqueo() {
        return estadoBloqueo;
    }

    public void setEstadoBloqueo(String estadoBloqueo) {
        this.estadoBloqueo = estadoBloqueo;
    }

    public TarjetaElectronica() {
        objPersona = new Persona();
        objOficina = new Oficina();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
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

}
