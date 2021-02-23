package ll.entidades.administracion;

/**
 *
 * @author MarVer
 */
public class ActividadEconomica {

    private String IdActEcon;
    private String Descripcion;
    private String Nivel;
    private String Estado;

    public ActividadEconomica() {
    }

    public ActividadEconomica(String idActEcon, String descripcion,
            String nivel, String estado) {
        super();
        IdActEcon = idActEcon;
        Descripcion = descripcion;
        Nivel = nivel;
        Estado = estado;
    }

    public String getIdActEcon() {
        return IdActEcon;
    }

    public void setIdActEcon(String idActEcon) {
        IdActEcon = idActEcon;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String nivel) {
        Nivel = nivel;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

}
