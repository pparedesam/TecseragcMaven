package ll.entidades.administracion;

public class Persona {

    private String idPersona;
    private TipoDocIdentidad objTipoDocIdentidad;
    private Urbanizacion objUrbanizacion;
    private String direccion;
    private String fechaNacimiento;
    private String tipoVivienda;
    private String telefono;
    private Ubigeo objUbigeoDireccion;
    private Ubigeo objUbigeoNacionalidad;
    private float ingresoMensual;
    private String nombres;
    private String nroDocumento;
    private String correo;
    private String idUsuario;
    private String fechaRegistro;
    private String horaRegistro;
    private String referenciaDireccion;
    private String celular;
    private String proveedor;
    private String idRubro;
    private String rus;

    private TipoDocumento objDocumento;

    public Persona() {
        objUrbanizacion = new Urbanizacion();
        objUbigeoDireccion = new Ubigeo();
        objUbigeoNacionalidad = new Ubigeo();
        objDocumento = new TipoDocumento();
        objTipoDocIdentidad = new TipoDocIdentidad();

    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public TipoDocIdentidad getObjTipoDocIdentidad() {
        return objTipoDocIdentidad;
    }

    public void setObjTipoDocIdentidad(TipoDocIdentidad objTipoDocIdentidad) {
        this.objTipoDocIdentidad = objTipoDocIdentidad;
    }

    public Urbanizacion getObjUrbanizacion() {
        return objUrbanizacion;
    }

    public void setObjUrbanizacion(Urbanizacion objUrbanizacion) {
        this.objUrbanizacion = objUrbanizacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Ubigeo getObjUbigeoDireccion() {
        return objUbigeoDireccion;
    }

    public void setObjUbigeoDireccion(Ubigeo objUbigeoDireccion) {
        this.objUbigeoDireccion = objUbigeoDireccion;
    }

    public Ubigeo getObjUbigeoNacionalidad() {
        return objUbigeoNacionalidad;
    }

    public void setObjUbigeoNacionalidad(Ubigeo objUbigeoNacionalidad) {
        this.objUbigeoNacionalidad = objUbigeoNacionalidad;
    }

    public float getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(float ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(String horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public String getReferenciaDireccion() {
        return referenciaDireccion;
    }

    public void setReferenciaDireccion(String referenciaDireccion) {
        this.referenciaDireccion = referenciaDireccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public TipoDocumento getObjDocumento() {
        return objDocumento;
    }

    public void setObjDocumento(TipoDocumento objDocumento) {
        this.objDocumento = objDocumento;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(String idRubro) {
        this.idRubro = idRubro;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    
    
}
