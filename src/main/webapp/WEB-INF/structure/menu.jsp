<%@page import="ll.entidades.agentes.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ll.negocio.agente.MenuNEG"%>
<%@page import="java.util.List"%>
<%@page import="ll.negocio.agente.ImagenesNEG"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="wrapper">
    <%@page import="ll.entidades.administracion.Usuario"%>

    <%@page import="java.io.File"%>
    <%@page import="java.io.IOException"%>
    <%@page import="java.awt.image.BufferedImage"%>
    <%@page import="javax.imageio.ImageIO"%>
    <%@page import="java.io.ByteArrayOutputStream"%>

    <%@page import="java.math.BigInteger"%>
    <%@page import="javax.xml.bind.DatatypeConverter"%>
    <%@page import="java.awt.image.BufferedImage"%>

    <% String server = ""; %>

    <%
        List<Menu> lstMenu = new ArrayList<Menu>();

        Usuario objUsuario = new Usuario();
        HttpSession sesion = request.getSession();
        objUsuario = (Usuario) sesion.getAttribute("user");
        lstMenu = (List<Menu>) sesion.getAttribute("menu");

        String nombres = objUsuario.getObjEmpleado().getObjPersonaNatural().getNombres();
        String apellidos = objUsuario.getObjEmpleado().getObjPersonaNatural().getApPaterno() + " "
                + objUsuario.getObjEmpleado().getObjPersonaNatural().getApMaterno();
        String puesto = objUsuario.getObjEmpleado().getObjPuesto().getNombre();
        String area = objUsuario.getObjEmpleado().getObjArea().getDescripcion();
        String sexo = objUsuario.getObjEmpleado().getObjPersonaNatural().getSexo();

    %>


    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"> 
                        <center>
                            <span><%if (sexo.equals("M")) {
                                %>
                                <img alt="image" class="img-circle"  src="<c:url value="/resources/imagenes/hombre.png" />"/>  
                                <%} else {
                                %>
                                <img alt="image" class="img-circle"  src="<c:url value="/resources/imagenes/mujer.png" />"/>  
                                <%}
                                %>
                            </span>

                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs"> 
                                        <strong class="font-bold"><% out.println(nombres);%></strong><br>
                                        <strong class="font-bold"><% out.println(apellidos);%></strong>
                                    </span> <span class="text-muted text-xs block"><% out.println(puesto);%> <% out.println(area);%>
                                        <b class="caret"></b></span> 

                                </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li>
                                    <a id="cambioClave">Cambiar Clave</a>
                                    <!-- <a href="profile.html">Profile</a>-->

                                </li>
                                <li class="divider"></li>
                                <li><a href="<c:url value="/frmLogin.jsp" />">Logout</a></li>

                            </ul>

                        </center>
                    </div>
                    <div class="logo-element">
                        LL+
                    </div>
                </li>


                <li>
                    <a href="<c:url value="/frmPrincipal.jsp" />"><i class="fa fa-home"></i> <span class="nav-label">Inicio</span></a>
                </li>

                <%

                    for (int i = 0; i < lstMenu.size(); i++) {
                        if (lstMenu.get(i).getNivel() == 1) {
                            out.println("<li>");
                            out.println("<a href='#'><i class='fa " + lstMenu.get(i).getIcon().toString() + "'></i> <span class='nav-label'>" + lstMenu.get(i).getDescripcion().toString() + "</span><span class='fa arrow'></span></a>");
                            out.println("<ul class='nav nav-second-level collapse'>");
                            for (int j = 0; j < lstMenu.size(); j++) {

                                if (lstMenu.get(j).getNivel() == 2 && lstMenu.get(j).getDependiente() == lstMenu.get(i).getCodigo()) {

                                    out.println("<li>");
                                    out.println("<a href='#'>" + lstMenu.get(j).getDescripcion().toString() + "<span class='fa arrow'></span></a>");
                                    out.println("<ul class='nav nav-third-level'>");
                                    for (int k = 0; k < lstMenu.size(); k++) {

                                        if (lstMenu.get(k).getNivel() == 3 && lstMenu.get(k).getDependiente() == lstMenu.get(j).getCodigo()) {
                                            out.println("<li>");
                                            out.println("<a href='" + server + "/PresentacionLL" + lstMenu.get(k).getUrl().toString() + "'>" + lstMenu.get(k).getDescripcion().toString() + "</a>");
                                            out.println("</li>");
                                        }
                                    }
                                    out.println("</ul>");
                                    out.println("</li>");
                                }
                            }
                            out.println("</ul>");
                            out.println("</li>");
                        }

                    }
                %>
        </div>
    </nav>
    <!--Modal cambio clave-->
    <div class="modal inmodal" id="modalCambiarClave"  role="dialog" aria-hidden="true" style="overflow:hidden;">
        <div class="modal-dialog">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <i class="fa fa-laptop modal-icon"></i>
                    <h4 class="modal-title">Cambiar Clave</h4>
                </div>
                <form class="form-horizontal" id="frmCambiarClave" name="frmCambiarClave" method="Post">
                    <div class="modal-body" id="modalCambiarClavebody" name="modalCambiarClavebody">
                        <div class="row">

                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r"
                                           for="txtClaveAct">Clave Actual:</label>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control input-sm"  name="txtClaveAct" id="txtClaveAct"  type="text">   
                                    </div>
                                </div>
                            </div>  
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r"
                                           for="txtClaveNue">Clave Nueva:</label>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control input-sm"  name="txtClaveNue" id="txtClaveNue"  type="text">   
                                    </div>
                                </div>
                            </div>  
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r"
                                           for="txtClaveConfNue">Confirmar Clave Nueva:</label>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control input-sm"  name="txtClaveConfNue" id="txtClaveConfNue"  type="text">   
                                    </div>
                                </div>
                            </div>  
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">Cancelar</button>
                        <button class="btn btn-success" type="submit" name="btnActualizarClave" id="btnActualizarClave">Registrar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="<c:url value="/resources/js/jsMenu.js"/>"type="text/javascript"></script>