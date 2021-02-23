package ll.entidades.administracion;

public class PersonaJuridica extends Persona {

    
    private Ubigeo objUbigeo;
    private ActividadEconomica objActividadEconomica;
    private String tomoRP;
    private String folioRP;
    private String celularEmpresa;
    private String nombreCorto;
    private Persona objPersonaR;

    public PersonaJuridica() {
        objUbigeo = new Ubigeo();
        objActividadEconomica = new ActividadEconomica();
        objPersonaR = new Persona();
    }

    public Ubigeo getObjUbigeo() {
        return objUbigeo;
    }

    public void setObjUbigeo(Ubigeo objUbigeo) {
        this.objUbigeo = objUbigeo;
    }

    public ActividadEconomica getObjActividadEconomica() {
        return objActividadEconomica;
    }

    public void setObjActividadEconomica(ActividadEconomica objActividadEconomica) {
        this.objActividadEconomica = objActividadEconomica;
    }

    public String getTomoRP() {
        return tomoRP;
    }

    public void setTomoRP(String tomoRP) {
        this.tomoRP = tomoRP;
    }

    public String getFolioRP() {
        return folioRP;
    }

    public void setFolioRP(String folioRP) {
        this.folioRP = folioRP;
    }

    public String getCelularEmpresa() {
        return celularEmpresa;
    }

    public void setCelularEmpresa(String celularEmpresa) {
        this.celularEmpresa = celularEmpresa;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Persona getObjPersonaR() {
        return objPersonaR;
    }

    public void setObjPersonaR(Persona personaR) {
        this.objPersonaR = personaR;
    }

}
