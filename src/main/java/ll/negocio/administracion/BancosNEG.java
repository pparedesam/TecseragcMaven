/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.BancosDAO;
import ll.entidades.administracion.Bancos;
import ll.entidades.administracion.CtaBanco;
import ll.entidades.administracion.TipoCtaBanco;
import ll.entidades.administracion.Usuario;

/**
 *
 * @author RenRio
 */
public class BancosNEG {

    private static BancosNEG _Instancia;

    private BancosNEG() {
    }

    public static BancosNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new BancosNEG();
        }
        return _Instancia;
    }

    public List<Bancos> listarBancos() throws Exception {
        return BancosDAO.Instancia().listarBancos();
    }

    public List<CtaBanco> listarCtaBancos(String IdPersonaBanco, String IdTipMoneda) throws Exception {
        return BancosDAO.Instancia().listarCtaBancos(IdPersonaBanco, IdTipMoneda);
    }

    public List<TipoCtaBanco> listarTipoCtaBancos() throws Exception {
        return BancosDAO.Instancia().listarTipoCtaBancos();
    }

    public List<Bancos> listarBancosBusqueda(int Criterio, String TipoBusqueda) throws Exception {
        return BancosDAO.Instancia().listarBancosBusqueda(Criterio, TipoBusqueda);
    }

    public Boolean registrarBanco(Bancos objBanco) throws Exception {
        return BancosDAO.Instancia().registrarBanco(objBanco);
    }

    public Boolean registrarCtaBanco(CtaBanco objCtaBanco, Usuario objUsuario) throws Exception {
        return BancosDAO.Instancia().registrarCtaBanco(objCtaBanco, objUsuario);
    }

    public List<CtaBanco> listarCta(String IdPersonaBanco) throws Exception {
        return BancosDAO.Instancia().listarCta(IdPersonaBanco);
    }

    //funciones nuevas
    public List<Bancos> listarBancosNEW() throws Exception {
        return BancosDAO.Instancia().listarBancosNEW();
    }

    public Bancos obtenerBanco(String idPersona) throws Exception {
        return BancosDAO.Instancia().obtenerBanco(idPersona);
    }

    public List<CtaBanco> listaCuentaBancos(String idPersonaBco) throws Exception {
        return BancosDAO.Instancia().listaCuentaBancos(idPersonaBco);
    }

    public String registrarBancoNEW(Bancos objBancos) throws Exception {

        return BancosDAO.Instancia().registrarBancoNEW(objBancos);

    }

    public String registrarCtaBancos(CtaBanco objCtaBanco, Usuario objUsuario) throws Exception {
        return BancosDAO.Instancia().registrarCtaBancos(objCtaBanco, objUsuario);
    }
}
