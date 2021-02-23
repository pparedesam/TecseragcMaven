/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.contabilidad;

import java.util.HashMap;
import ll.accesodatos.contabilidad.DocumentoBajaDAO;
import ll.accesodatos.contabilidad.DocumentoCompraVentaDAO;
import ll.entidades.administracion.Usuario;

import ll.entidades.operaciones.DocumentoGenerado;

/**
 *
 * @author PauPar
 */
public class DocumentoBajaNEG {

    private static DocumentoBajaNEG _Instancia;

    private DocumentoBajaNEG() {
    }

    public static DocumentoBajaNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new DocumentoBajaNEG();
        }
        return _Instancia;
    }

    public HashMap<String, String> registrarDocumentoBaja(DocumentoGenerado objDocumentoGenerado, Usuario objUsuario)
            throws Exception {

        HashMap<String, String> lista = new HashMap<>();

        try {

            objDocumentoGenerado.setNroDoc(DocumentoBajaDAO.Instancia().registrarDocBaja(objDocumentoGenerado, objUsuario));

            lista.put("nroDoc", objDocumentoGenerado.getNroDoc());
            lista.put("tipMoneda", objDocumentoGenerado.getObjTipoMoneda().getId());
            lista.put("Message", "OK");
        } catch (Exception e) {
            throw e;
        }

        return lista;
    }
}
