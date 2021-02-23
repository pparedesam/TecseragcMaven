package ll.entidades.administracion;

public class Documento {
	private String idDocumento;
	private String nombreDoc;
	private String abrev;
        
	public Documento(String idDocumento, String nombreDoc, String abrev,
			String parametro) {
		super();
		this.idDocumento = idDocumento;
		this.nombreDoc = nombreDoc;
		this.abrev = abrev;
		this.parametro = parametro;
	}
	public Documento() {

	}
	private String parametro;
	public String getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getNombreDoc() {
		return nombreDoc;
	}
	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}
	public String getAbrev() {
		return abrev;
	}
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
}
