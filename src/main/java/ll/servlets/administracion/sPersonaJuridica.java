package ll.servlets.administracion;

import ll.entidades.administracion.Usuario;
import ll.entidades.administracion.PersonaJuridica;
import ll.negocio.administracion.PersonaJuridicaNEG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ll.entidades.administracion.Persona;

public class sPersonaJuridica extends HttpServlet {

    private HttpServletRequest varRequest;
    private HttpServletResponse varResponse;
    boolean resultado = false;

    Map<String, String> dataString = new HashMap<>();
    Map<String, Object> dataObject = new HashMap<>();
    String json;

    Map<String, String> data = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sPersonaJuridica</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sPersonaJuridica at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        varRequest = request;
        varResponse = response;
        varResponse.setContentType("application/json");
        varResponse.setHeader("Cache-Control", "no-cache");

        try {

            if (action.equalsIgnoreCase("registrar")) {
                registrarPersonaJuridica();
            }

            if (action.equals("ObtenerPersonaJuridica")) {
                buscarPersonaJuridica();
            }
            if (action.equalsIgnoreCase("BuscaPersonaNoSocioNoEmpleado")) {
                buscarPersonaNoSocioNoEmpleado();
            }
            if (action.equalsIgnoreCase("PersonaJuridicaText")) {

                RUCtext();

            }
            if (action.equalsIgnoreCase("PersonaNaturalJuridicaText")) {

                nroDoctxt();

            }
            if (action.equalsIgnoreCase("buscarPersonaJN")) {
                buscarPersonaJN();
            }
            if (action.equalsIgnoreCase("listarPersona")) {
                listarPersona();
            }
            if (action.equalsIgnoreCase("buscarPersonaJuridicaModal")) {
                buscarPersonaJuridicaModal();
            }
            if (action.equalsIgnoreCase("BuscarPersonaJuridicaNoBanco")) {
                BuscarPersonaJuridicaNoBanco();
            }

        } catch (Exception ex) {

            data.put("Result", "error");
            data.put("Message", ex.getMessage());
            String json = gson.toJson(data);
            response.getWriter().write(json);
        }

    }

    private void registrarPersonaJuridica() throws Exception {
        PersonaJuridica objJuridica = new PersonaJuridica();
        String Pais, Dept, Prov, Dist;

        String Accion;

        objJuridica.getObjTipoDocIdentidad().setIdTipoDocIdentidad(varRequest.getParameter("cboTipDocumento"));
        objJuridica.setIdPersona(varRequest.getParameter("txtIdPersona"));
        objJuridica.setNroDocumento(varRequest.getParameter("txtNroRuc"));
        objJuridica.setNombres(varRequest.getParameter("txtRazonSocial"));
        objJuridica.getObjActividadEconomica().setIdActEcon((varRequest.getParameter("cboGiroNegocio")));
        //objJuridica.setTipoVivienda(varRequest.getParameter("cboLocal"));
        objJuridica.setFechaNacimiento(varRequest.getParameter("txtFechaCons"));
        //objJuridica.setIngresoMensual(Float.parseFloat(varRequest.getParameter("txtIngreso")));
        objJuridica.getObjUrbanizacion().setCodigo(varRequest.getParameter("cboUrbanizacion"));
        objJuridica.setDireccion(varRequest.getParameter("txtDireccion"));
        objJuridica.setTelefono(varRequest.getParameter("txtTelefono"));
        objJuridica.setCelular(varRequest.getParameter("txtCelular"));
        objJuridica.setCorreo(varRequest.getParameter("txtEmail"));
        objJuridica.setReferenciaDireccion(varRequest.getParameter("txtReferencia"));
        objJuridica.setProveedor(varRequest.getParameter("proveedor"));
        objJuridica.setIdRubro(varRequest.getParameter("rubro"));
        Pais = varRequest.getParameter("idpaises");
        Dept = varRequest.getParameter("iddepartamento");
        Prov = varRequest.getParameter("idprovincias");
        Dist = varRequest.getParameter("iddistritos");
        objJuridica.getObjUbigeoNacionalidad().setCodigo(Pais + "000000");
        objJuridica.getObjUbigeo().comprobarUbigeo(Pais, Dept, Prov, Dist);
        if (objJuridica.getIdPersona().equals("")) {
            Accion = "insert";
        } else {
            Accion = "update";
        }
        HttpSession sesion = varRequest.getSession();
        Usuario objUsuario = (Usuario) sesion.getAttribute("user");
        objJuridica.setIdUsuario(objUsuario.getIdUsuario());

        boolean registro = PersonaJuridicaNEG.Instancia().registrarPersonaJuridica(objJuridica, Accion);

        if (registro == true) {
            data.put("Result", "OK");
            data.put("Message", "Se Registro Correctamente");
        } else {
            data.put("Result", "error");
            data.put("Message", "Error al insertar");
        }

        String json = gson.toJson(data);
        varResponse.getWriter().write(json);
    }

    private void buscarPersonaJuridica() throws Exception {
        String idPersona = varRequest.getParameter("idPersona");

        PersonaJuridica objJuridica = PersonaJuridicaNEG.Instancia().obtenerPersonaJuridica(idPersona);
        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("PersonaJuridica", objJuridica);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);
    }

    /*
      Busqueda de Personas naturales que no son socios ni empleados  
      Se utiliza para busqueda que se realiza en el registro de socio juridico
     */
    private void buscarPersonaNoSocioNoEmpleado() throws Exception {
        Map<String, Object> dataObject = new HashMap<>();
        dataObject.put("Result", "OK");
        dataObject.put("Persona", PersonaJuridicaNEG.Instancia().ObtenerPersonaNoSocioNoEmpleado(
                varRequest.getParameter("txtNroDocumentoIdentidad"),
                varRequest.getParameter("cboTipoPersona")));
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);

    }

    private void RUCtext() throws Exception {

        String query = varRequest.getParameter("txtruc");
        query = query.toUpperCase();

        List<PersonaJuridica> listaPJuridica = PersonaJuridicaNEG.Instancia().obtenerPersonaJuridicaTEXT(query);

        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("lista", listaPJuridica);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);

    }

    private void nroDoctxt() throws Exception {

        String query = varRequest.getParameter("txtruc");
        query = query.toUpperCase();
        List<Persona> lista = new ArrayList<>();
        lista = PersonaJuridicaNEG.Instancia().obtenerPersonaNaturalJuridicaText(query);

        Map<String, Object> datos = new HashMap<>();
        datos.put("Result", "OK");
        datos.put("lista", lista);
        String json = gson.toJson(datos);
        varResponse.getWriter().write(json);

    }

    private void buscarPersonaJN() throws Exception {

        Map<String, Object> dataObject = new HashMap<>();
        String optradio, cboTipoPersona, txtCriterioBusqueda = null;

        optradio = varRequest.getParameter("optradiobus");
        cboTipoPersona = varRequest.getParameter("cboTipoPersona");
        txtCriterioBusqueda = varRequest.getParameter("txtCriterioBusqueda");

        List<PersonaJuridica> listaPersonaJN = PersonaJuridicaNEG.Instancia().obtenerPersonaNaturalJuridica(txtCriterioBusqueda, optradio, cboTipoPersona);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listaPersonaJN", listaPersonaJN);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    private void listarPersona() throws Exception {

        Map<String, Object> dataObject = new HashMap<>();
        String txtCriterioBusqueda = null;
        int optradio;
        optradio = Integer.parseInt(varRequest.getParameter("optradiobco"));
        txtCriterioBusqueda = varRequest.getParameter("txtCriterioBusqueda");

        List<PersonaJuridica> listaBanco = PersonaJuridicaNEG.Instancia().listarPersona(optradio, txtCriterioBusqueda);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listaBanco", listaBanco);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    private void buscarPersonaJuridicaModal() throws Exception {

        Integer criterio = Integer.parseInt(varRequest.getParameter("TipoBusqueda"));
        String valor = varRequest.getParameter("txtPersonaJuridica");

        List<PersonaJuridica> listaPersonaJuridicas = PersonaJuridicaNEG.Instancia().listarPersona(criterio, valor);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listaJ", listaPersonaJuridicas);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();

    }

    private void BuscarPersonaJuridicaNoBanco() throws Exception {

        Integer criterio = Integer.parseInt(varRequest.getParameter("cboTipoBusqueda"));
        String valor = varRequest.getParameter("txtNroDoc");

        List<PersonaJuridica> listaPersonaJuridica = PersonaJuridicaNEG.Instancia().listarPersonaJuridicaNoBancos(criterio, valor);
        dataObject.clear();
        dataObject.put("Result", "OK");
        dataObject.put("listaPersonaJuridica", listaPersonaJuridica);
        String json = gson.toJson(dataObject);
        varResponse.getWriter().write(json);
        dataObject.clear();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
