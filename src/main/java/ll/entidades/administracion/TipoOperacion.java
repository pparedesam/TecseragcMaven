package ll.entidades.administracion;

public class TipoOperacion {

    private String idTipOpe;
    private String nombreTOperacion;
    private String ccg;

    public TipoOperacion() {
    }

    ;
	public String getIdTipOpe() {
        return idTipOpe;
    }

    public void setIdTipOpe(String idTipOpe) {
        this.idTipOpe = idTipOpe;
    }

    public String getNombreTOperacion() {
        return nombreTOperacion;
    }

    public void setNombreTOperacion(String nombreTOperacion) {
        this.nombreTOperacion = nombreTOperacion;
    }

    public String getCcg() {
        return ccg;
    }

    public void setCcg(String ccg) {
        this.ccg = ccg;
    }
}
