
<%@page import="ll.negocio.administracion.PersonaJuridicaNEG"%>
<%@page import="ll.entidades.administracion.PersonaJuridica"%>
<%@page import="ll.entidades.administracion.Usuario"%>
<%@page import="ll.entidades.administracion.TipoMoneda"%>
<%@page import="ll.negocio.administracion.OficinaNEG"%>
<%@page import="ll.entidades.administracion.Oficina"%>
<%@page import="ll.negocio.contabilidad.*" %>
<%@page import="ll.entidades.contabilidad.*" %>
<%@page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>
<%
    Usuario objUsuario = new Usuario();
    HttpSession sesion = request.getSession();
    objUsuario = (Usuario) sesion.getAttribute("user");
%>
<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title"> <h4>REPORTE DE CHEQUES:</h4> </div>


            <form class="form-horizontal" id="form-buscar-PerJur"  name="form-buscar-PerJur" method="post">
                <div class="row" >
                    <div class=" col-sm-1" >
                    </div>
                    <div class="well col-sm-10" >

                        <div class="col-sm-10">


                            <div class="form-group" id="fechaDesde" name="fechaDesde">
                                <label class="col-sm-4 control-label m-r"
                                       for="txtFechaDesde">Fecha Desde:</label>
                                <div class="col-sm-3">
                                    <div class="input-group date">
                                        <input type="text" class="form-control" placeholder="DD/MM/YYYY" 
                                               name="txtFechaDesde" id="txtFechaDesde">
                                        <span class="input-group-addon"><i
                                                class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group"  id="fechaHasta" name="fechaHasta">
                                <label class="col-sm-4 control-label m-r"
                                       for="txtFechaHasta">Fecha Hasta:</label>
                                <div class="col-sm-3">
                                    <div class="input-group date">
                                        <input type="text" class="form-control" placeholder="DD/MM/YYYY" 
                                               name="txtFechaHasta" id="txtFechaHasta">
                                        <span class="input-group-addon"><i
                                                class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label m-r">Moneda: </label>
                                <div class="col-sm-3 ">
                                    <%  List<TipoMoneda> objTipoMoneda = TipoMonedaNEG.Instancia().listar(objUsuario.getObjEmpleado().getObjOficina());
                                        out.println("<select name='cboTipoMoneda' id='cboTipoMoneda' class='simple-select form-control'>");

                                        for (int i = 0; i < objTipoMoneda.size(); i++) {
                                            out.println("<option value='" + objTipoMoneda.get(i).getId() + "'>" + objTipoMoneda.get(i).getDescripcion() + "</option>");
                                        }
                                        out.println("</select>");
                                    %> 
                                </div>
                            </div>


                        </div>


                        <div class="col-sm-1" >
                            <button class="btn btn-OceanBoatBlue" type="submit" id="btnBuscarDocumento" name="btnBuscarDocumento" >Buscar</button>
                        </div>                                        
                    </div>
                </div>
            </form>
            <div class="row">

                <div class="col-lg-8 col-md-offset-2" >
                    <table class="table table-striped table-bordered table-hover" id="tbl_PersonaJuridica" align='center'  >
                        <thead>
                            <tr>
                                <th>Numero Cheque</th>
                                <th>Destinado</th>
                                <th>Fecha Documento</th>
                                <th>Monto</th>
                                <th>Estado</th>
                            </tr>
                        </thead>

                        <tbody>

                            <%   String valor = null;
                                if (request.getParameter("btnBuscarDocumento") != null) {

                                    valor = request.getParameter("txtFechaDesde") + "&" + request.getParameter("txtFechaHasta") + "&" + request.getParameter("cboTipoMoneda");
                                    List<Cheque> listaCheque = ChequeNEG.Instancia().listarRptCheque(valor);
                                    for (int i = 0; i < listaCheque.size(); i++) {
                                        out.println("<tr>");
                                        out.println("<td class='text-left'>" + listaCheque.get(i).getNrocheque() + "</td>");
                                        out.println("<td class='text-left'>" + listaCheque.get(i).getObjPersona().getNombres() + "</td>");
                                        out.println("<td class='text-left'>" + listaCheque.get(i).getObjDocGenerado().getFechaDocumento() + "</td>");
                                        out.println("<td class='text-right'>" + listaCheque.get(i).getMonto() + "</td>");
                                        out.println("<td class='text-left'>" + listaCheque.get(i).getEstado() + "</td>");
                                        out.println("</tr>");
                                    }

                                }
                            %>

                        </tbody>  

                    </table>

                </div>
            </div>
            <%
                if (request.getParameter("btnBuscarDocumento") != null) {

            %>

            <div class="row">
                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-5">

                        <button class="btn btn-OceanBoatBlue" type="button" name="btnGenerarRpt" 
                                id="btnGenerarRpt" data-id="<%=valor%>" >Generar
                        </button> 
                        <input type="hidden" id="fechas" name="fechas" value="<%=valor%>">

                    </div>
                </div>
            </div> 
            <%                }
            %>

        </div>
    </div>
</div>

<!-- --------end body---------------------- -->

<script src="<c:url value="/resources/js/jsContabilidad/jsRptCheque.js"/>"
type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionContabilidad/jaValidacionRptLibros.js"/>"
type="text/javascript"></script>
<jsp:include page="/WEB-INF/structure/footer.jsp" />

