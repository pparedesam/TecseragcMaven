<%@page import="ll.entidades.administracion.TipoCtaBanco"%>
<%@page import="ll.negocio.administracion.BancosNEG"%>
<%@page import="ll.entidades.administracion.Bancos"%>
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
            <div class="ibox-title"> <h4>ASIGNAR CUENTA DE BANCO</h4> 
                <form class="form-horizontal" id="form-registrarCheque" method="post">
                    <legend class="subtitulos">BANCO</legend>
                    <div class="row"> 
                        <div class="col-lg-4">
                            <div class="form-group reg_per">
                                <label class="col-sm-4 control-label m-r" for="txtBanco">Banco:</label>
                                <div class="col-sm-5">
                                    <input class="form-control input-sm" name="txtBanco" 
                                           id="txtNombre" disabled=""  type="text">
                                    <input class="form-control" name="txtidPersonaBanco" id="txtidPersonaBanco" value="0"  type="hidden">       
                                </div>
                                <button class='btn btn-OceanBoatBlue' type='button' 
                                        name='btnAgregarbanco'
                                        data-target="#ModalMF" data-toggle="modal"
                                        id='btnAgregarBanco'>Agregar 
                                </button>
                            </div>
                        </div>
                    </div>
                    <legend class="subtitulos">CUENTA DEL BANCO</legend>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="form-group reg_per">
                                <label class="col-sm-4 control-label m-r" for="txtCta">Numero de Cuenta:</label>
                                <div class="col-sm-5">
                                    <input class="form-control input-sm" name="txtCta" 
                                           id="txtNombre"   type="text">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group reg_per">
                                <label class="col-sm-4 control-label m-r" for="cboctabco">Tipo de Cuenta:</label>
                                <div class="col-sm-5 ">
                                    <%  List<TipoCtaBanco> objTipoCtaBco = BancosNEG.Instancia().listarTipoCtaBancos();
                                        out.println("<select name='cboctabco' id='cboctabco' class='simple-select form-control'>");

                                        for (int i = 0; i < objTipoCtaBco.size(); i++) {
                                            out.println("<option value='" + objTipoCtaBco.get(i).getIdTipCtaBanco() + "'>" + objTipoCtaBco.get(i).getTipoCtaBanco() + "</option>");
                                        }
                                        out.println("</select>");
                                    %> 
                                </div>
                            </div>                                
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group reg_per">
                                <label class="col-sm-4 control-label m-r">Moneda:</label>
                                <div class="col-sm-4">
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
                    </div>
                    <br>
                    <br>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-5">
                                <button class="btn btn-OceanBoatBlue" type="button" name="btnRegistrarctabnco"
                                        id="btnRegistrarctabnco">Registrar
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!--CARACTERISTICAS-->
        <div class="modal inmodal" id="modalBuscarBco"  role="dialog" aria-hidden="true" style="overflow:hidden;">
            <div class="modal-dialog">
                <div class="modal-content animated bounceInRight">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <i class="fa fa-laptop modal-icon"></i>
                        <h4 class="modal-title">Buscar Titular</h4>
                    </div>
                    <div class="modal-body" id="modalBuscarBancobody" name="modalBuscarBancobody">
                        <div class="row">
                            <form class="form-horizontal" id="frmBuscarBanco" name="frmBuscarBanco" method="Post">
                                <div class="panel-body">
                                    <div class="row" style="padding-left: 20px;padding-right: 20px;">
                                        <div class="well col-md-12" >
                                            <div class="row">
                                                <div class="col-md-5 ">
                                                    <div class="col-md-4">
                                                        <label class="radio-inline">
                                                            <input type="radio" name="optradiobco" id="optradiobco" value="1" checked="">RUC
                                                        </label>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <label class="radio-inline">
                                                            <input type="radio" name="optradiobco" id="optradiobco" value="2" >RAZON SOCIAL
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12 ">
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label m-r" for="txtCriterioBusqueda">INGRESE CRITERIO DE BUSQUEDA</label>
                                                        <div class="col-md-5 " >
                                                            <input class="form-control input-sm"  name="txtCriterioBusqueda" id="txtCriterioBusqueda" type="text">
                                                        </div>
                                                        <div class="col-md-2" >
                                                            <button class="btn btn-OceanBoatBlue btn-sm" type="button" id="btnBuscarPJ" name="btnBuscarPJ" >Buscar</button>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="row">
                                <div class="col-lg-10 col-md-offset-1">
                                    <table class="table table-striped table-bordered table-hover"
                                           id="tbl_Banco" align='center'>
                                        <thead>
                                            <tr>
                                                <th>Numero de Documento</th>
                                                <th>Nombres</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ---------end js -------------->

<script src="<c:url value="/resources/js/jsBanco/jsBanco.js"/>"
type="text/javascript"></script>

<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
