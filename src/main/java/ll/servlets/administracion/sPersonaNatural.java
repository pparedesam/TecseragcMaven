package ll.servlets.administracion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.PersonaNatural;
import ll.entidades.administracion.Usuario;
import ll.negocio.administracion.PersonaNaturalNEG;

public class sPersonaNatural extends HttpServlet {

    private HttpServletRequest varRequest = null;
    private HttpServletResponse varResponse = null;

    Map<String, String> data = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");
        try {

            if (action.equalsIgnoreCase("registrar")) {
                registrarPersonaNatural();
            }
            if (action.equalsIgnoreCase("buscarPersonaNaturalModal")) {
                buscarPersonaNaturalModal();
            }
            if (action.equals("ObtenerPersonaNatural")) {
                buscarPersonaNatural();
            }
            if (action.equalsIgnoreCase("BuscaPersonaNoSocioNoEmpleado")) {
                buscarPersonaNoSocioNoEmpleado();
            }
            if (action.equalsIgnoreCase("BuscaPersonaNoEmpleado")) {
                buscarPersonaNoEmpleado();
            }
            if (action.equalsIgnoreCase("PersonaNaturalText")) {

                dniText();

            }
        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            String json = gson.toJson(data);
            response.getWriter().write(json);
        }

        if (action.equalsIgnoreCase("BuscaPersonaNoSocio")) {
            try {

                String NroDocIden = request.getParameter("txtNroDocIden");

                PersonaNatural objPersonaNatural = PersonaNaturalNEG.Instancia().ObtenerPersonaSocio(NroDocIden);
                if (objPersonaNatural.getIdPersona() != null) {

                    Map<String, Object> dataObject = new HashMap<>();
                    dataObject.put("Result", "OK");
                    dataObject.put("PersonaNatural", objPersonaNatural);
                    String json = gson.toJson(dataObject);
                    response.getWriter().write(json);

                } else {

                    data.put("Result", "error");
                    data.put("Message", "El numero de Identificacion ingresado no pertenece a una Persona que no este Asociada");
                    String json = gson.toJson(data);
                    varResponse.getWriter().write(json);
                }

            } catch (Exception ex) {

                data.put("Result", "error");
                data.put("Message", ex.getMessage());

                String json = gson.toJson(data);
                varResponse.getWriter().write(json);
            }

        }

        if (action.equalsIgnoreCase("BuscaPersonaGeneralNoEmpleado")) {
            try {

                String NroDocIden = request.getParameter("txtNroDocIden");

                PersonaNatural objPersonaNatural = PersonaNaturalNEG.Instancia().ObtenerPersonaGeneralNoEmpleado(NroDocIden);
                if (objPersonaNatural.getIdPersona() != null) {

                    Map<String, Object> dataObject = new HashMap<>();
                    dataObject.put("Result", "OK");
                    dataObject.put("PersonaNatural", objPersonaNatural);

                    String json = gson.toJson(dataObject);
                    response.getWriter().write(json);

                } else {

                    data.put("Result", "error");
                    data.put("Message", "El Numero de Dni ingresado es invalido o pertenece a un empleado activo de la empresa");
                    String json = gson.toJson(data);
                    response.getWriter().write(json);
                }

            } catch (Exception ex) {

                data.put("Result", "error");
                data.put("Message", ex.getMessage());
                String json = gson.toJson(data);
                response.getWriter().write(json);
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="Registrar Persona Natural">
    private void registrarPersonaNatural() throws Exception {
        PersonaNatural objPersonaNatural = new PersonaNatural();
        String Pais, Dept, Prov, Dist;

        objPersonaNatural.setIdPersona(varRequest.getParameter("txtIdPersona"));
        objPersonaNatural.getObjTipoDocIdentidad().setTipoPersona("N");
        objPersonaNatural.getObjTipoDocIdentidad().setIdTipoDocIdentidad(varRequest.getParameter("cboTipDocumento"));
        objPersonaNatural.setNroDocumento(varRequest.getParameter("txtNumDocumento"));
        objPersonaNatural.setApPaterno(varRequest.getParameter("txtApePaterno"));
        objPersonaNatural.setApMaterno(varRequest.getParameter("txtApeMaterno"));
        objPersonaNatural.setNombres(varRequest.getParameter("txtNombres"));
        objPersonaNatural.setFechaNacimiento((varRequest.getParameter("txtFechaNac")));
        objPersonaNatural.setSexo(varRequest.getParameter("cboSexPersona"));
        objPersonaNatural.setEstadoCivil(varRequest.getParameter("cboEstPersona"));
        objPersonaNatural.setGradoInstruccion(varRequest.getParameter("cboGradoInstruccion"));
        objPersonaNatural.getObjProfesion().setIdProfesion((varRequest.getParameter("cboProfesion")));
        objPersonaNatural.setTelefono(varRequest.getParameter("txtTelefono"));
        objPersonaNatural.setCelular(varRequest.getParameter("txtCelular"));
        objPersonaNatural.setCorreo(varRequest.getParameter("txtEmail"));
        Pais = varRequest.getParameter("idpaises");
        Dept = varRequest.getParameter("iddepartamento");
        Prov = varRequest.getParameter("idprovincias");
        Dist = varRequest.getParameter("iddistritos");
        objPersonaNatural.getObjUbigeoNacionalidad().comprobarUbigeo(Pais, Dept, Prov, Dist);
        objPersonaNatural.getObjUrbanizacion().setCodigo(varRequest.getParameter("cboUrbanizacion"));
        objPersonaNatural.setDireccion(varRequest.getParameter("txtDireccion"));
        objPersonaNatural.setReferenciaDireccion(varRequest.getParameter("txtReferencia"));
        objPersonaNatural.setProveedor(varRequest.getParameter("proveedor"));
        objPersonaNatural.setIdRubro(varRequest.getParameter("idRubro"));
        objPersonaNatural.setRus(varRequest.getParameter("rus"));

        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        objPersonaNatural.setIdUsuario(objUsuario.getIdUsuario());

        String result = PersonaNaturalNEG.Instancia().registrarPersonaNatural(objPersonaNatural);

        data.put("Result", "OK");
        data.put("Message", result);

        String json = gson.toJson(data);
        varResponse.getWriter().write(json);

    }
    // </editor-fold>

    private void buscarPersonaNatural() throws Exception {

        String idPersona = varRequest.getParameter("idPersona");
        PersonaNatural objPersonaNatural;
        objPersonaNatural = PersonaNaturalNEG.Instancia().ObtenerPersonaNatural(idPersona);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("PersonaNatural", objPersonaNatural);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    private void buscarPersonaNaturalModal() throws Exception {

        Integer criterio = Integer.parseInt(varRequest.getParameter("TipoBusqueda"));
        String valor = varRequest.getParameter("txtPersonaNatural");

        List<PersonaNatural> listaPersonaNaturals = PersonaNaturalNEG.Instancia().listarPersona(criterio, valor);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("lista", listaPersonaNaturals);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    /*
      Busqueda de Personas naturales que no son socios ni empleados  
      Se utiliza para busqueda que se realiza en el registro de socio natural
     */
    private void buscarPersonaNoSocioNoEmpleado() throws Exception {
        dataObject.put("Result", "OK");
        dataObject.put("Persona", PersonaNaturalNEG.Instancia().ObtenerPersonaNoSocioNoEmpleado(
                varRequest.getParameter("txtNroDocumentoIdentidad"),
                varRequest.getParameter("cboTipoPersona")));
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    //busqueda de Personas que no son empleados, es decir personas naturales y juridicas que pueden estar registrados o no como socios
    private void buscarPersonaNoEmpleado() throws Exception {
        Map<String, Object> dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("Persona", PersonaNaturalNEG.Instancia().ObtenerPersonaNoEmpleado(varRequest.getParameter("txtNroDocIden"), varRequest.getParameter("optTipo")));
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
    }

    private void dniText() throws Exception {

        String query = varRequest.getParameter("txtnrodocConductor");
        query = query.toUpperCase();

        List<PersonaNatural> listaPersonaNatural = PersonaNaturalNEG.Instancia().obtenerPersonaNaturalTEXT(query);

        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("lista", listaPersonaNatural);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
// <editor-fold defaultstate="collapsed" desc="Registrar Persona Natural">
       // </editor-fold>
