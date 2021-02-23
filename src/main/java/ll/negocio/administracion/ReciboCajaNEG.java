/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;


import java.util.List;
import ll.accesodatos.administracion.ReciboCajaDAO;

import ll.entidades.administracion.Usuario;
import ll.entidades.operaciones.DocumentoGenerado;


/**
 *
 * @author Andree
 */
public class ReciboCajaNEG {
     private static ReciboCajaNEG _Instancia;

    private ReciboCajaNEG() {
    }

    public static ReciboCajaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new ReciboCajaNEG();
        }
        return _Instancia;
    }

    public String registrarReciboCaja(DocumentoGenerado objDocGenerado,Float monto,String firmaVB,String firmaRC,Usuario objUsuario) throws Exception {
        return ReciboCajaDAO.Instancia().registrarReciboCaja(objDocGenerado,monto,firmaVB,firmaRC,objUsuario);
    }
    
    public List<DocumentoGenerado> buscarRecibosCaja( String nroDoc, String fecIni, String fecFin) throws Exception {
        return ReciboCajaDAO.Instancia().buscarRecibosCaja(nroDoc, fecIni, fecFin);
    }
    
    public Boolean anularRecibo(DocumentoGenerado objDocGenerado,Usuario objUsuario, String motivo) throws Exception {
        return ReciboCajaDAO.Instancia().anularRecibo(objDocGenerado, objUsuario, motivo);
    }
    
    public double obtenerSaldoCaja() throws Exception {
        return ReciboCajaDAO.Instancia().obtenerSaldoCaja();
    }
}
