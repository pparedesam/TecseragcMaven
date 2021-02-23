package ll.entidades.agentes;

import ll.entidades.administracion.Oficina;

public class Parametro {
	
	private String idParam;
	private String parametro;
	private Oficina objOficina;
	private String descripcion;
	private String valor;

	public Parametro(String idParam, String parametro, Oficina objOficina,
			String descripcion, String valor) {
		super();
		this.idParam = idParam;
		this.parametro = parametro;
		this.objOficina = objOficina;
		this.descripcion = descripcion;
		this.valor = valor;
		
	}

	public String getIdParam() {
		return idParam;
	}

	public void setIdParam(String idParam) {
		this.idParam = idParam;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public Oficina getObjOficina() {
		return objOficina;
	}

	public void setObjOficina(Oficina objOficina) {
		this.objOficina = objOficina;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Parametro(){
}
}
