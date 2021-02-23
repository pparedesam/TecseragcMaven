package ll.entidades.administracion;

public class Cuenta {

    private Oficina ObjOficina;
    private TipoCuenta ObjTipoCuenta;
    private PlazoFijo objPlazoFijo;
    private String NumCuenta;
    private EstadoCuenta objEstadoCuenta;
    private String IdEstadoCta;
    private String EstadoCuenta;
    private String FechApertura;
    private TipoApertura objTipoApertura;
    private Double Saldo;
    private String FechaEstado;
    private String Usuario;
    private String Disponibilidad;
    private Boolean DiferenciarDepositos;
    private Boolean NoReportarSaldos;
    private TipoMoneda objTipoMoneda;
    private String Saldos;

    public String getSaldos() {
        return Saldos;
    }

    public void setSaldos(String Saldos) {
        this.Saldos = Saldos;
    }
    
  

    public Cuenta() {
        ObjOficina = new Oficina();
        ObjTipoCuenta = new TipoCuenta();
        objTipoApertura = new TipoApertura();
        objPlazoFijo = new PlazoFijo();
        objEstadoCuenta = new EstadoCuenta();
        objTipoMoneda = new TipoMoneda();
    }

    public PlazoFijo getObjPlazoFijo() {
        return objPlazoFijo;
    }

    public void setObjPlazoFijo(PlazoFijo objPlazoFijo) {
        this.objPlazoFijo = objPlazoFijo;
    }

    public TipoApertura getObjTipoApertura() {
        return objTipoApertura;
    }

    public void setObjTipoApertura(TipoApertura objTipoApertura) {
        this.objTipoApertura = objTipoApertura;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public Oficina getObjOficina() {
        return ObjOficina;
    }

    public void setObjOficina(Oficina objOficina) {
        ObjOficina = objOficina;
    }

    public TipoCuenta getObjTipoCuenta() {
        return ObjTipoCuenta;
    }

    public void setObjTipoCuenta(TipoCuenta objTipoCuenta) {
        ObjTipoCuenta = objTipoCuenta;
    }

    public String getNumCuenta() {
        return NumCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        NumCuenta = numCuenta;
    }

    public String getIdEstadoCta() {
        return IdEstadoCta;
    }

    public void setIdEstadoCta(String idEstadoCta) {
        IdEstadoCta = idEstadoCta;
    }

    public String getFechApertura() {
        return FechApertura;
    }

    public void setFechApertura(String fechApertura) {
        FechApertura = fechApertura;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }

    public String getFechaEstado() {
        return FechaEstado;
    }

    public void setFechaEstado(String fechaEstado) {
        FechaEstado = fechaEstado;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        Disponibilidad = disponibilidad;
    }

    public Boolean getDiferenciarDepositos() {
        return DiferenciarDepositos;
    }

    public void setDiferenciarDepositos(Boolean diferenciarDepositos) {
        DiferenciarDepositos = diferenciarDepositos;
    }

    public Boolean getNoReportarSaldos() {
        return NoReportarSaldos;
    }

    public void setNoReportarSaldos(Boolean noReportarSaldos) {
        NoReportarSaldos = noReportarSaldos;
    }

    public String getEstadoCuenta() {
        return EstadoCuenta;
    }

    public void setEstadoCuenta(String EstadoCuenta) {
        this.EstadoCuenta = EstadoCuenta;
   }   

    public EstadoCuenta getObjEstadoCuenta() {
        return objEstadoCuenta;
    }

    public void setObjEstadoCuenta(EstadoCuenta objEstadoCuenta) {
        this.objEstadoCuenta = objEstadoCuenta;
    }

    public TipoMoneda getObjTipoMoneda() {
        return objTipoMoneda;
    }

    public void setObjTipoMoneda(TipoMoneda objTipoMoneda) {
        this.objTipoMoneda = objTipoMoneda;
    }
}
