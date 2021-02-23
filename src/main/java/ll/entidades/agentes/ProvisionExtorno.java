
package ll.entidades.agentes;


public class ProvisionExtorno {
    
    
    private Double montoProvi;
    private Double montoProviExt;
    private Double diferencia;
    private String cuota;

    public ProvisionExtorno(){}
    
    public Double getMontoProvi() {
        return montoProvi;
    }

    public void setMontoProvi(Double montoProvi) {
        this.montoProvi = montoProvi;
    }

    public Double getMontoProviExt() {
        return montoProviExt;
    }

    public void setMontoProviExt(Double MontopExt) {
        this.montoProviExt = MontopExt;
    }

    public Double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Double diferencia) {
        this.diferencia = diferencia;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }
    
}
