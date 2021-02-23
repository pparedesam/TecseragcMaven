/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.rrhh;

/**
 *
 * @author RenRio
 */
public class GrupoHorario {

    private int idGrupo;
    private String nombreGrupo;
    private String descGrupo;
    private String diaEntrada;
    private String diaSalida;
    private String tardeEntrada;
    private String tardeSalida;
    private boolean estado;
    private String diaHorario;

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }

    public String getDiaEntrada() {
        return diaEntrada;
    }

    public void setDiaEntrada(String diaEntrada) {
        this.diaEntrada = diaEntrada;
    }

    public String getDiaSalida() {
        return diaSalida;
    }

    public void setDiaSalida(String diaSalida) {
        this.diaSalida = diaSalida;
    }

    public String getTardeEntrada() {
        return tardeEntrada;
    }

    public void setTardeEntrada(String tardeEntrada) {
        this.tardeEntrada = tardeEntrada;
    }

    public String getTardeSalida() {
        return tardeSalida;
    }

    public void setTardeSalida(String tardeSalida) {
        this.tardeSalida = tardeSalida;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(String diaHorario) {
        this.diaHorario = diaHorario;
    }

}
