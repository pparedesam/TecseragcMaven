package ll.entidades.administracion;

public class Ubigeo {

    private String codigo;
    private String descripcion;
    private String nivel;
    private String Ubigeo;

    public Ubigeo() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    public void comprobarUbigeo(String Pais,String Departamento,String Provincia,String Distrito)
    {
       setCodigo(Pais + ((Departamento.trim().isEmpty())? Departamento="00" : Departamento)+((Provincia.trim().isEmpty())? Provincia="00" : Provincia)+((Distrito.trim().isEmpty())? Distrito="00" : Distrito));
       
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getUbigeo() {
        return Ubigeo;
    }

    public void setUbigeo(String Ubigeo) {
        this.Ubigeo = Ubigeo;
    }
    
    public String NombreNivel() {

        String nombre;
        switch (Integer.parseInt(getNivel())) {

            case 0:
                nombre = "Pais";
                break;

            case 1:
                nombre = "Departamento";
                break;

            case 2:
                nombre = "Provincia";
                break;

            default:
                nombre = "Distrito";
                break;

        }
        return nombre;

    }
            
    
    
}
