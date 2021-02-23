package ll.entidades.administracion;

public class MiembroFamiliar {

    private PersonaNatural objPersonaAsociada;
    private PersonaNatural objPersonaMiembroF;
    private Parentesco ObjParentesco;
    private String idUsuario;
    private String Apoderado;

    public MiembroFamiliar() {

        objPersonaAsociada = new PersonaNatural();
        objPersonaMiembroF = new PersonaNatural();
        ObjParentesco = new Parentesco();

    }

    public PersonaNatural getObjPersonaAsociada() {
        return objPersonaAsociada;
    }

    public void setObjPersonaAsociada(PersonaNatural objPersonaAsociada) {
        this.objPersonaAsociada = objPersonaAsociada;
    }

    public PersonaNatural getObjPersonaMiembroF() {
        return objPersonaMiembroF;
    }

    public void setObjPersonaMiembroF(PersonaNatural objPersonaMiembroF) {
        this.objPersonaMiembroF = objPersonaMiembroF;
    }

    public Parentesco getObjParentesco() {
        return ObjParentesco;
    }

    public void setObjParentesco(Parentesco objParentesco) {
        ObjParentesco = objParentesco;
    }


    public String getApoderado() {
        return Apoderado;
    }

    public void setApoderado(String apoderado) {
        Apoderado = apoderado;
    }
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
