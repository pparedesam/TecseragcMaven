package ll.entidades.administracion;

import java.sql.Date;



public class Socio {

    private String idSocio;
    private PersonaNatural objPersonaNatural;
    private PersonaJuridica objPersonaJuridica;
    private String socio;
    private EstadoSocio objEstadoSocio;
    private Date fechaEstado;
    private Date fechaIngreso;
    private Oficina objOficina;
    private TipoTrabajador objTipoTrabajador;
    private Persona objPersona;
    private TipoDocumento objTipodocumento;
    private String tipoPersona;
    private Float fondoMortuorio;
    private Float derechoInscripcion;
    private Float derechoSistema;
    private Float AporteInicial;
    private String motivoSalida;
    private String horaIngreso;
    private Date fechaProceso;
    private String motivoIngreso;
    private String idUsuario;
    private String categoria;
    private String pIC;    
    private String observaciones;
    private TipoPersona objTipoPersona;
    private String foto;
    private String firma;
    private String nroDoc;
    private String convenio;

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }
    
    
    

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    
    
    public Socio() {

        objPersona = new Persona();
        objPersonaNatural = new PersonaNatural();
        objPersonaJuridica = new PersonaJuridica();
        objOficina = new Oficina();
        objTipodocumento = new TipoDocumento();
        objEstadoSocio = new EstadoSocio();
        objTipoTrabajador = new TipoTrabajador();
        objTipoPersona = new TipoPersona();

    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
        if (tipoPersona.equals("N")) {
            this.tipoPersona = "PERSONA NATURAL";
        }
        if (tipoPersona.equals("J")) {
            this.tipoPersona = "PERSONA JURIDICA";
        }

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public PersonaNatural getObjPersonaNatural() {
        return objPersonaNatural;
    }

    public void setObjPersonaNatural(PersonaNatural objPersonaNatural) {
        this.objPersonaNatural = objPersonaNatural;
    }

    public TipoTrabajador getObjTipoTrabajador() {
        return objTipoTrabajador;
    }

    public void setObjTipoTrabajador(TipoTrabajador objTipoTrabajador) {
        this.objTipoTrabajador = objTipoTrabajador;
    }

    public PersonaJuridica getObjPersonaJuridica() {
        return objPersonaJuridica;
    }

    public void setObjPersonaJuridica(PersonaJuridica objPersonaJuridica) {
        this.objPersonaJuridica = objPersonaJuridica;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public EstadoSocio getObjEstadoSocio() {
        return objEstadoSocio;
    }

    public void setObjEstadoSocio(EstadoSocio objEstadoSocio) {
        this.objEstadoSocio = objEstadoSocio;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Oficina getObjOficina() {
        return objOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    public TipoDocumento getObjTipodocumento() {
        return objTipodocumento;
    }

    public void setObjTipodocumento(TipoDocumento objTipodocumento) {
        this.objTipodocumento = objTipodocumento;
    }

    public Float getFondoMortuorio() {
        return fondoMortuorio;
    }

    public void setFondoMortuorio(Float fondoMortuorio) {
        this.fondoMortuorio = fondoMortuorio;
    }

    public Float getDerechoInscripcion() {
        return derechoInscripcion;
    }

    public void setDerechoInscripcion(Float derechoInscripcion) {
        this.derechoInscripcion = derechoInscripcion;
    }

    public Float getDerechoSistema() {
        return derechoSistema;
    }

    public void setDerechoSistema(Float derechoSistema) {
        this.derechoSistema = derechoSistema;
    }

    public Float getAporteInicial() {
        return AporteInicial;
    }

    public void setAporteInicial(Float AporteInicial) {
        this.AporteInicial = AporteInicial;
    }

    public String getMotivoSalida() {
        return motivoSalida;
    }

    public void setMotivoSalida(String motivoSalida) {
        this.motivoSalida = motivoSalida;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getpIC() {
        return pIC;
    }

    public void setpIC(String pIC) {
        this.pIC = pIC;
    }



    public String getOservaciones() {
        return observaciones;
    }

    public void setOservaciones(String oservaciones) {
        this.observaciones = oservaciones;
    }

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoPersona getObjTipoPersona() {
        return objTipoPersona;
    }

    public void setObjTipoPersona(TipoPersona objTipoPersona) {
        this.objTipoPersona = objTipoPersona;
    }

}
