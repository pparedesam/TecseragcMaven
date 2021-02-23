<%@page import="ll.entidades.agentes.MainMenu"%>
<%@page import="ll.negocio.administracion.*"%>
<%@page import="ll.entidades.administracion.*"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />
<%
    HttpSession sesion1 = request.getSession();
    List<MainMenu> lstMenu = (List<MainMenu>) sesion1.getAttribute("menu");

    int access = -1;
    String web = request.getRequestURL().toString();
    String lastone = web.split("/")[(web.split("/")).length - 1];
    for (int i = 0; i < lstMenu.size(); i++) {
        if (lstMenu.get(i).getUrl() != null) {

            String pages = lstMenu.get(i).getUrl().toString();

            pages = pages.split("/")[(pages.split("/")).length - 1];
            if (lastone.equals(pages)) {
                access = 1;
            }
        }
    }
%>
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="ibox float-e-margins">           
            <div class="ibox-content">
                <div class="row">
                    <legend class="text-success">Mantenedor de Menu </legend>

                    <%
                        if (access == 1) {

                    %>

                    <div class="col-lg-10 col-md-offset-1" >
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Oficina</label>
                                    <div class="col-sm-7 ">
                                        <Select class="simple-select2 form-control" name="cboOficina"
                                                id="cboOficina" data-placeholder="SELECCIONE UNA OFICINA">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Area</label>
                                    <div class="col-sm-7 ">
                                        <select data-placeholder="SELECCIONE UNA AREA"
                                                class="simple-select2 form-control" name="cboDpto"
                                                id="cboDpto">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Puesto</label>
                                    <div class="col-sm-7 ">
                                        <select data-placeholder="SELECCIONE UNA PUESTO"
                                                class="simple-select2 form-control" name="cboPsto"
                                                id="cboPsto">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-lg-10 col-md-offset-1" >
                                <div><br><br></div>
                                <button class="btn btn-OceanBoatBlue" type="button" name="btnActualizarMenu"
                                        id="btnActualizarMenu">Actualizar
                                </button>
                            </div>
                        </div>
                        <div class="col-lg-10 col-md-offset-1" >
                            <div><br><br></div>
                            <table class="table table-striped table-bordered table-hover" id="tblMenu">

                                <thead>
                                    <tr> 
                                        <th>N1</th>   
                                        <th>N2</th>                                    
                                        <th>N3</th>                                    
                                        <th>ESTADO</th>     
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody> 
                            </table>
                        </div>
                    </div>       
                    <% } else {
                    %>

                    <div class="middle-box text-center animated fadeInRightBig">
                        <h2 class="font-bold">NO TIENE ACCESO AL CONTENIDO DE ESTE FORMULARIO</h2>
                        <div class="error-desc">
                            Usted no tiene acceso a este formulario por favor solicite a su jefatura inmediata para poder visualizar el contenido
                            <br><br><br><br><br><br><br><br><br><br><br>
                        </div>
                    </div>

                    <%}
                    %>

                </div>               
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jsSistemas/jsAccesoMenu.js" />"
type="text/javascript"></script>

<jsp:include page="/WEB-INF/structure/footer.jsp" />
