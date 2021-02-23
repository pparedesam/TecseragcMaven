package ll.entidades.administracion;

public class Parentesco {
	
	private String IdParentesco ;
	private String Parentesco   ;
	private String Sexo         ;
	private int NroMax       ;
	private String Estado       ;
	private String TipParen     ;
	
	public Parentesco() {
		
	}

	public String getIdParentesco() {
		return IdParentesco;
	}
	public void setIdParentesco(String idParentesco) {
		IdParentesco = idParentesco;
	}
	public String getParentesco() {
		return Parentesco;
	}
	public void setParentesco(String parentesco) {
		Parentesco = parentesco;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	public int getNroMax() {
		return NroMax;
	}
	public void setNroMax(int nroMax) {
		NroMax = nroMax;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getTipParen() {
		return TipParen;
	}
	public void setTipParen(String tipParen) {
		TipParen = tipParen;
	}


}
