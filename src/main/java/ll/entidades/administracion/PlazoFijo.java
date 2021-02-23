package ll.entidades.administracion;

public class PlazoFijo {

private Oficina oficina;
private String idTipCta;
private TipoMoneda moneda;
private String numCuenta;
private Float tasaContrato;
private Float tasaAdelantada;
private Float tasaPreferenteSolicitada;
private Float tasaPreferenteAprobada;
private String idDoc;
private Oficina oficinaDoc;
private TipoMoneda MonedaDoc;
private String NroDoc;
private Float TEA;
private String TipoTasa;
private String EstadoInv;
private String TipoPagoInteres;
private Oficina OficinaTx;
private TipoMoneda MonedaTx;
private String IdTipCtaTx;
private String NumCuentaTx;

public PlazoFijo(){
    oficina= new Oficina();
    moneda = new TipoMoneda();
    oficinaDoc= new Oficina();
    MonedaDoc= new TipoMoneda();
    OficinaTx=new Oficina();
    MonedaTx = new TipoMoneda();    
    
}
        
        
    public PlazoFijo(String idoficina, String idTipCta, String idmoneda, String numCuenta, String TipoTasa) {
        this.oficina = new Oficina();
        this.moneda = new TipoMoneda();

        this.oficina.setIdOficina(idoficina);
        this.idTipCta = idTipCta;
        this.moneda.setId(idmoneda);
        this.numCuenta = numCuenta;
        this.TipoTasa = TipoTasa;
    }

      public PlazoFijo(String idoficina, String idTipCta, String idmoneda, String numCuenta) {
        this.oficina = new Oficina();
        this.moneda = new TipoMoneda();

        this.oficina.setIdOficina(idoficina);
        this.idTipCta = idTipCta;
        this.moneda.setId(idmoneda);
        this.numCuenta = numCuenta;
     
    }
      
    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public String getIdTipCta() {
        return idTipCta;
    }

    public void setIdTipCta(String idTipCta) {
        this.idTipCta = idTipCta;
    }

    public TipoMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Float getTasaContrato() {
        return tasaContrato;
    }

    public void setTasaContrato(Float tasaContrato) {
        this.tasaContrato = tasaContrato;
    }

    public Float getTasaAdelantada() {
        return tasaAdelantada;
    }

    public void setTasaAdelantada(Float tasaAdelantada) {
        this.tasaAdelantada = tasaAdelantada;
    }

    public Float getTasaPreferenteSolicitada() {
        return tasaPreferenteSolicitada;
    }

    public void setTasaPreferenteSolicitada(Float tasaPreferenteSolicitada) {
        this.tasaPreferenteSolicitada = tasaPreferenteSolicitada;
    }

    public Float getTasaPreferenteAprobada() {
        return tasaPreferenteAprobada;
    }

    public void setTasaPreferenteAprobada(Float tasaPreferenteAprobada) {
        this.tasaPreferenteAprobada = tasaPreferenteAprobada;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }

    public Oficina getOficinaDoc() {
        return oficinaDoc;
    }

    public void setOficinaDoc(Oficina oficinaDoc) {
        this.oficinaDoc = oficinaDoc;
    }

    public TipoMoneda getMonedaDoc() {
        return MonedaDoc;
    }

    public void setMonedaDoc(TipoMoneda MonedaDoc) {
        this.MonedaDoc = MonedaDoc;
    }

    public String getNroDoc() {
        return NroDoc;
    }

    public void setNroDoc(String NroDoc) {
        this.NroDoc = NroDoc;
    }

    public Float getTEA() {
        return TEA;
    }

    public void setTEA(Float TEA) {
        this.TEA = TEA;
    }

    public String getTipoTasa() {
        return TipoTasa;
    }

    public void setTipoTasa(String TipoTasa) {
        this.TipoTasa = TipoTasa;
    }

    public String getEstadoInv() {
        return EstadoInv;
    }

    public void setEstadoInv(String EstadoInv) {
        this.EstadoInv = EstadoInv;
    }

    public String getTipoPagoInteres() {
        return TipoPagoInteres;
    }

    public void setTipoPagoInteres(String TipoPagoInteres) {
        this.TipoPagoInteres = TipoPagoInteres;
    }

    public Oficina getOficinaTx() {
        return OficinaTx;
    }

    public void setOficinaTx(Oficina OficinaTx) {
        this.OficinaTx = OficinaTx;
    }

    public TipoMoneda getMonedaTx() {
        return MonedaTx;
    }

    public void setMonedaTx(TipoMoneda MonedaTx) {
        this.MonedaTx = MonedaTx;
    }

    public String getIdTipCtaTx() {
        return IdTipCtaTx;
    }

    public void setIdTipCtaTx(String IdTipCtaTx) {
        this.IdTipCtaTx = IdTipCtaTx;
    }

    public String getNumCuentaTx() {
        return NumCuentaTx;
    }

    public void setNumCuentaTx(String NumCuentaTx) {
        this.NumCuentaTx = NumCuentaTx;
    }
    



}
