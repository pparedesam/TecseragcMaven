package ll.entidades.administracion;

public class Beneficiario {

    private TipoBeneficio objTipoBeneficio;
    private Float Porcentaje;
    private MiembroFamiliar objMiembroFamiliar;

    public Beneficiario() {

        objMiembroFamiliar = new MiembroFamiliar();
        objTipoBeneficio = new TipoBeneficio();
    }

    public TipoBeneficio getObjTipoBeneficio() {
        return objTipoBeneficio;
    }

    public void setObjTipoBeneficio(TipoBeneficio objTipoBeneficio) {
        this.objTipoBeneficio = objTipoBeneficio;
    }

    public Float getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        Porcentaje = porcentaje;
    }

    public MiembroFamiliar getObjMiembroFamiliar() {
        return objMiembroFamiliar;
    }

    public void setObjMiembroFamiliar(MiembroFamiliar objMiembroFamiliar) {
        this.objMiembroFamiliar = objMiembroFamiliar;
    }

}
