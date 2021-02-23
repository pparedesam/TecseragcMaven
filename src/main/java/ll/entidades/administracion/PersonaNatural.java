package ll.entidades.administracion;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class PersonaNatural extends Persona {

    private String apPaterno;
    private String apMaterno;   
    private String sexo;
    
    private String estadoCivil;
    private String gradoInstruccion;
    private Profesion objProfesion;
    private Ocupacion objOcupacion;
    private String valido;
  
    private int edad;


    public PersonaNatural() {
      
       
        objProfesion = new Profesion();
        objOcupacion = new Ocupacion();
       

    }  

   
    public void calcularEdad()
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(this.getFechaNacimiento(), fmt);
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNac, ahora);        
        this.edad =  periodo.getYears();    
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGradoInstruccion() {
        return gradoInstruccion;
    }

    public void setGradoInstruccion(String gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
    }

    public Profesion getObjProfesion() {
        return objProfesion;
    }

    public void setObjProfesion(Profesion objProfesion) {
        this.objProfesion = objProfesion;
    }

    public Ocupacion getObjOcupacion() {
        return objOcupacion;
    }

    public void setObjOcupacion(Ocupacion objOcupacion) {
        this.objOcupacion = objOcupacion;
    }

    public String getValido() {
        return valido;
    }

    public void setValido(String valido) {
        this.valido = valido;
    }

   

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
  
 

}
