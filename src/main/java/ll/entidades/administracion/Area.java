/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.entidades.administracion;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author paupar
 */

public class Area {

	private String codigo;
	private String descripcion;
	private String tipoArea; // A, D
	private List<Puesto> lstPuesto;
	
	public Area(){
		this.lstPuesto = new ArrayList<Puesto>();
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

	public String getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}

	public List<Puesto> getLstPuesto() {
		return lstPuesto;
	}

	public void setLstPuesto(List<Puesto> lstPuesto) {
		this.lstPuesto = lstPuesto;
	}
}
