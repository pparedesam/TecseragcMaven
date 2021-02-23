/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll.negocio.administracion;

import java.util.List;
import ll.accesodatos.administracion.UbigeoDAO;
import ll.entidades.administracion.Departamento;
import ll.entidades.administracion.Distrito;
import ll.entidades.administracion.Pais;
import ll.entidades.administracion.Provincia;
import ll.entidades.administracion.Ubigeo;

/**
 *
 * @author paupar
 */
public class UbigeoNEG {

    private static UbigeoNEG _Instancia;

    private UbigeoNEG() {
    }

    public static UbigeoNEG Instancia() {
        if (_Instancia == null) {
            _Instancia = new UbigeoNEG();
        }
        return _Instancia;
    }

    public List<Ubigeo> listarUbigeo() throws Exception {

        return UbigeoDAO.Instancia().listarUbigeo();
    }

    public List<Pais> lstPais() throws Exception {
        return UbigeoDAO.Instancia().listarPaises();
    }

    public List<Departamento> lstDepartamento(String idPais) throws Exception {
        return UbigeoDAO.Instancia().listarDepartamentos(idPais);
    }

    public List<Provincia> lstProvincia(String idPais, String idDepartamento) throws Exception {
        return UbigeoDAO.Instancia().listarProvincias(idPais, idDepartamento);
    }

    public List<Distrito> lstDistrito(String idPais, String idDepartamento, String idProvincia) throws Exception {
        return UbigeoDAO.Instancia().listarDistritos(idPais, idDepartamento, idProvincia);
    }

    public String editarUbigeo(Ubigeo objUbigeo, String LastUbigeo) throws Exception {

        objUbigeo.setUbigeo(objUbigeo.getUbigeo().replace(LastUbigeo, objUbigeo.getDescripcion()));
        return UbigeoDAO.Instancia().registrarUbigeo(objUbigeo, "up");
    }

    public String registrarUbigeo(Ubigeo objUbigeo, String Accion) throws Exception {

        if (Accion.equals("agregar")) {
            objUbigeo.setNivel(String.valueOf(Integer.parseInt(objUbigeo.getNivel()) + 1));
            objUbigeo.setUbigeo(objUbigeo.getDescripcion() + " - " + objUbigeo.getUbigeo());
        }

        if (Accion.equals("registrar")) {
            objUbigeo.setCodigo("00000000");
            objUbigeo.setNivel("0");
            objUbigeo.setUbigeo(objUbigeo.getDescripcion());
        }

        return UbigeoDAO.Instancia().registrarUbigeo(objUbigeo, "in");

    }

    public List<Pais> listarPaisesGR() throws Exception {
        return UbigeoDAO.Instancia().listarPaisesGR();
    }

    public List<Departamento> listarDepartamento() throws Exception {
        return UbigeoDAO.Instancia().listarDepartamentos();
    }

    public List<Provincia> listarPronvincias() throws Exception {
        return UbigeoDAO.Instancia().listarPronvincias();
    }

    public List<Distrito> listarDistritos() throws Exception {
        return UbigeoDAO.Instancia().listarDistritos();
    }

}
