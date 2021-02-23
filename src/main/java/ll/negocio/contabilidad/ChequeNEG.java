/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.List;
import ll.accesodatos.contabilidad.ChequeDAO;
import ll.entidades.administracion.Usuario;
import ll.entidades.contabilidad.Cheque;

/**
 *
 * @author RenRio
 */
public class ChequeNEG {

    private static ChequeNEG _Instancia;

    private ChequeNEG() {
    }

    public static ChequeNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new ChequeNEG();
        }
        return _Instancia;
    }

    public String registrarCheque(Cheque objCheque, Usuario objUsuario) throws Exception {
        return ChequeDAO.Instancia().registrarCheque(objCheque, objUsuario);
    }

    public List<Cheque> listarCheque(int criterio, String valor) throws Exception {
        return ChequeDAO.Instancia().listarCheque(criterio, valor);
    }
    
    public List<Cheque> listarRptCheque(String valor) throws Exception {
        return ChequeDAO.Instancia().listarRptCheque( valor);
    }
     
    public List<Cheque> listarChequesDelDia() throws Exception {
        return ChequeDAO.Instancia().listarChequesDelDia();
    }
     
    public List<Cheque> buscarCheques(String nroCheque, String fecIni, String fecFin) throws Exception {
        return ChequeDAO.Instancia().buscarCheques(nroCheque,fecIni,fecFin);
    }

    public Boolean anularCheque(Cheque objCheque,Usuario objUsuario, String motivo) throws Exception {
        return ChequeDAO.Instancia().anularCheque(objCheque, objUsuario, motivo);
    }
}
