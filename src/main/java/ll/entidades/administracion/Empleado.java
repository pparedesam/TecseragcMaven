package ll.entidades.administracion;

import ll.entidades.rrhh.GrupoHorario;
import ll.entidades.rrhh.SistemaPensiones;
import ll.entidades.rrhh.TipoPlanEPS;
import java.util.Date;

public class Empleado extends Persona {

    private Puesto objPuesto;
    private Oficina objOficina;
    private Area objArea;
    private String fechaIngreso;
    private String fechaSalida;
    private int nroHijos;
    private String codTra;
    private String estado;
    private String cargo;

    private String CodigoSistema;
    private String ComisionMixta;
    private String ESSALUD;
    private String EPS;
    private int EPSCant;
    private Banco Banco;
    private String CtaSueldo;
    private SistemaPensiones objSistemaPensiones;
    private String foto;
    private String correoEmp;
    private String usuarioEmp;
    private PersonaNatural objPersonaNatural;
    private String fiscalizado;
    private TipoPlanEPS objTipoPlanEPS;
    private GrupoHorario objHorarioLunVie;
    private GrupoHorario objHorarioSab;
    

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodTra() {
        return codTra;
    }

    public void setCodTra(String codTra) {
        this.codTra = codTra;
    }

    public Empleado() {

        objPuesto = new Puesto();
        objOficina = new Oficina();
        objArea = new Area();
        objPersonaNatural = new PersonaNatural();

    }

    /**
     * @return the objPuesto
     */
    public Puesto getObjPuesto() {
        return objPuesto;
    }

    /**
     * @param objPuesto the objPuesto to set
     */
    public void setObjPuesto(Puesto objPuesto) {
        this.objPuesto = objPuesto;
    }

    /**
     * @return the objOficina
     */
    public Oficina getObjOficina() {
        return objOficina;
    }

    /**
     * @param objOficina the objOficina to set
     */
    public void setObjOficina(Oficina objOficina) {
        this.objOficina = objOficina;
    }

    /**
     * @return the objArea
     */
    public Area getObjArea() {
        return objArea;
    }

    /**
     * @param objArea the objArea to set
     */
    public void setObjArea(Area objArea) {
        this.objArea = objArea;
    }

    /**
     * @return the fechaIngreso
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaSalida
     */
    public String getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the nroHijos
     */
    public int getNroHijos() {
        return nroHijos;
    }

    /**
     * @param nroHijos the nroHijos to set
     */
    public void setNroHijos(int nroHijos) {
        this.nroHijos = nroHijos;
    }

    /**
     * @return the objPersonaNatural
     */
    public PersonaNatural getObjPersonaNatural() {
        return objPersonaNatural;
    }

    /**
     * @param objPersonaNatural the objPersonaNatural to set
     */
    public void setObjPersonaNatural(PersonaNatural objPersonaNatural) {
        this.objPersonaNatural = objPersonaNatural;
    }

    public String getCodigoSistema() {
        return CodigoSistema;
    }

    public void setCodigoSistema(String CodigoSistema) {
        this.CodigoSistema = CodigoSistema;
    }

    public String getComisionMixta() {
        return ComisionMixta;
    }

    public void setComisionMixta(String ComisionMixta) {
        this.ComisionMixta = ComisionMixta;
    }

    public String getESSALUD() {
        return ESSALUD;
    }

    public void setESSALUD(String ESSALUD) {
        this.ESSALUD = ESSALUD;
    }

    public String getEPS() {
        return EPS;
    }

    public void setEPS(String EPS) {
        this.EPS = EPS;
    }

    public int getEPSCant() {
        return EPSCant;
    }

    public void setEPSCant(int EPSCant) {
        this.EPSCant = EPSCant;
    }

    public Banco getBanco() {
        return Banco;
    }

    public void setBanco(Banco Banco) {
        this.Banco = Banco;
    }

    public String getCtaSueldo() {
        return CtaSueldo;
    }

    public void setCtaSueldo(String CtaSueldo) {
        this.CtaSueldo = CtaSueldo;
    }

    public SistemaPensiones getObjSistemaPensiones() {
        return objSistemaPensiones;
    }

    public void setObjSistemaPensiones(SistemaPensiones objSistemaPensiones) {
        this.objSistemaPensiones = objSistemaPensiones;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCorreoEmp() {
        return correoEmp;
    }

    public void setCorreoEmp(String correoEmp) {
        this.correoEmp = correoEmp;
    }

    public String getUsuarioEmp() {
        return usuarioEmp;
    }

    public void setUsuarioEmp(String usuarioEmp) {
        this.usuarioEmp = usuarioEmp;
    }

    public String getFiscalizado() {
        return fiscalizado;
    }

    public void setFiscalizado(String fiscalizado) {
        this.fiscalizado = fiscalizado;
    }

    public TipoPlanEPS getObjTipoPlanEPS() {
        return objTipoPlanEPS;
    }

    public void setObjTipoPlanEPS(TipoPlanEPS objTipoPlanEPS) {
        this.objTipoPlanEPS = objTipoPlanEPS;
    }

    public GrupoHorario getObjHorarioLunVie() {
        return objHorarioLunVie;
    }

    public void setObjHorarioLunVie(GrupoHorario objHorarioLunVie) {
        this.objHorarioLunVie = objHorarioLunVie;
    }

    public GrupoHorario getObjHorarioSab() {
        return objHorarioSab;
    }

    public void setObjHorarioSab(GrupoHorario objHorarioSab) {
        this.objHorarioSab = objHorarioSab;
    }

}
