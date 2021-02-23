package ll.entidades.administracion;

public class TipoCuenta {

    private String IdTipCta;
    private String TipMoneda;
    private Producto objProducto;
    private String Descripcion;
    private Float TasaVigente;
    private Float MontoMinimo;
    private int Plazo;
    private Float TEAVigente;
    private Float TasaAdelantada;
    private Float TEAAdelantada;
    private String Abreviatura;
    private String TipoPersona;


    public TipoCuenta() {
        objProducto = new Producto();
    }

    public String getIdTipCta() {
        return IdTipCta;
    }

    public void setIdTipCta(String idTipCta) {
        IdTipCta = idTipCta;
    }

    public String getTipMoneda() {
        return TipMoneda;
    }

    public void setTipMoneda(String tipMoneda) {
        TipMoneda = tipMoneda;
    }

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Float getTasaVigente() {
        return TasaVigente;
    }

    public void setTasaVigente(Float tasaVigente) {
        TasaVigente = tasaVigente;
    }

    public Float getMontoMinimo() {
        return MontoMinimo;
    }

    public void setMontoMinimo(Float montoMinimo) {
        MontoMinimo = montoMinimo;
    }

    public int getPlazo() {
        return Plazo;
    }

    public void setPlazo(int plazo) {
        Plazo = plazo;
    }

    public Float getTEAVigente() {
        return TEAVigente;
    }

    public void setTEAVigente(Float tEAVigente) {
        TEAVigente = tEAVigente;
    }

    public Float getTasaAdelantada() {
        return TasaAdelantada;
    }

    public void setTasaAdelantada(Float tasaAdelantada) {
        TasaAdelantada = tasaAdelantada;
    }

    public Float getTEAAdelantada() {
        return TEAAdelantada;
    }

    public void setTEAAdelantada(Float tEAAdelantada) {
        TEAAdelantada = tEAAdelantada;
    }

    public String getAbreviatura() {
        return Abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        Abreviatura = abreviatura;
    }

    public String getTipoPersona() {
        return TipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        TipoPersona = tipoPersona;
    }

}
