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
            <div class="ibox-title"> <h4>COMUNICACI&Oacute;N DE BAJA</h4> 
                <form class="form-horizontal" id="form-registrarFactura" method="post"
                      action="#tabRegistrarBien">
                    <legend class="subtitulos">Buscar Documento</legend>
                    <div class="row"> 
                        <div class="col-lg-5">
                            <div class="form-group reg_per">
                                <label class="col-sm-4 control-label m-r">Tipo Documento:</label>
                                <div class="col-sm-5 ">
                                    <%  List<TipoComprobantePago> objTipoComprobantePago = TipoComprobantePagoNEG.Instancia().listar();
                                        out.println("<select name='cboDocumento' id='cboDocumento'  data-placeholder='SELECCIONE DOCUMENTO' class='simple-select2 form-control'>");
                                        out.println("<option value='' disabled='false'></option>");
                                        for (int i = 0; i < objTipoComprobantePago.size(); i++) {
                                            out.println("<option value='" + objTipoComprobantePago.get(i).getIdDoc() + "'>" + objTipoComprobantePago.get(i).getDescripcion() + "</option>");
                                        }
                                        out.println("</select>");
                                    %> 
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group reg_per">
                                <label class="col-sm-4 control-label m-r"  for="txtFactura">Factura:</label>
                                <div class="col-sm-6">
                                    <input class="form-control input-sm" data-mask="F999-99999999" name="txtFactura" id="txtFactura"  placeholder="N&uacute;mero Factura"  type="text">
                                    <input class="form-control" name="txtDocumento" id="txtDocumento"  type="hidden">              
                                </div>    
                            </div> 
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group reg_per">
                                <button class='btn btn-OceanBoatBlue' type='button' 
                                        name='btnBuscarFactura'
                                        id='btnBuscarFactura'>Buscar</button>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="row" id="tabFactura" name="tabFactura" hidden="">
                    <form class="form-horizontal" id="form-registrarFactura" method="post" action="#tabRegistrarBien">
                        <legend class="subtitulos"> Datos del Emisor</legend>

                        <div class="row"> 
                            <div class="col-lg-6">
                                <div class="form-group reg_per">

                                    <label class="col-sm-4 control-label m-r"  for="txtRUCEmisor">RUC:</label>
                                    <div class="col-sm-5">

                                        <input class="form-control input-sm autocomplete" disabled="" data-mask="99999999999" name="txtRUCEmisor" id="txtRUCEmisor"  placeholder="Ingrese RUC"  type="text" >

                                        <input class="form-control" name="txtidPersonaEmisor" id="txtidPersonaEmisor"  type="hidden">              

                                    </div>    
                                </div> 
                            </div>
                        </div>
                        <div class="row">

                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r" for="txtRazonSocialEmisor">Señor (es):</label>

                                    <div class="col-sm-5">

                                        <input class="form-control input-sm" name="txtRazonSocialEmisor"
                                               id="txtRazonSocialEmisor" disabled=""  type="text" ></div>
                                </div>
                            </div>

                        </div>

                        <legend class="subtitulos"> Datos del Cliente</legend>
                        <div class="row"> 
                            <div class="col-lg-6">
                                <div class="form-group reg_per">

                                    <label class="col-sm-4 control-label m-r"  for="txtRUC">RUC:</label>
                                    <div class="col-sm-5">

                                        <input name="txtRUC" id="txtRUC" class="form-control input-sm autocomplete"  disabled="" data-mask="99999999999"  placeholder="Ingrese RUC"  type="text">

                                        <input class="form-control" name="txtidPersonaCliente" id="txtidPersonaCliente"   type="hidden">              

                                    </div>    
                                </div> 
                            </div>
                        </div>

                        <div class="row">

                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r" for="txtRazonSocial">Señor (es):</label>

                                    <div class="col-sm-5">

                                        <input class="form-control input-sm" name="txtRazonSocial" 
                                               id="txtRazonSocial" disabled=""  type="text"></div>
                                </div>
                            </div>

                        </div>

                        <legend class="subtitulos"> Datos de Guia Remisi&oacute;n</legend>
                        <br>
                        <br>
                        <div class="row">
                            <div class="col-lg-15">
                                <div class="form-group reg_per">
                                    <div class="col-sm-3 ">
                                    </div>
                                    <div class="col-sm-5 ">
                                        <div class="content">
                                            <table class="table table-striped table-bordered table-hover" id="tblGuiaRemision">                
                                                <thead>
                                                    <tr> 
                                                        <th>TIPO GUIA</th>
                                                        <th>NRO GUIA REMISI&Oacute;N</th>    
                                                        <th>FECHA</th>
                                                        <th>ACCIONES</th>
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
                        <br>
                        <br>

                        <legend class="subtitulos"> Datos de la Venta</legend>
                        <div class="row">

                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r " for="txtNumFactura">N&uacute;mero Documento</label>

                                    <div class="col-sm-5">

                                        <input class="form-control input-sm" data-mask="F999-99999999" name="txtNumFactura"
                                               id="txtNumFactura" type="text" disabled=""></div>
                                </div>
                            </div>


                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Tipo Comprobante</label>
                                    <div class="col-sm-5 ">

                                        <input class="form-control input-sm"  name="txtTipoComprobante"
                                               id="txtTipoComprobante" type="text" disabled="">
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row"> 
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r"
                                           for="txtFecFac">Fecha Factura:</label>
                                    <div class="col-sm-5">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" placeholder="DD/MM/YYYY"
                                                   name="txtFecFac" id="txtFecFac" autocomplete="off" disabled="">
                                            <span class="input-group-addon"><i
                                                    class="fa fa-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Moneda</label>
                                    <div class="col-sm-5 ">

                                        <input class="form-control input-sm"  name="txtTipoMoneda"
                                               id="txtTipoMoneda" type="text" disabled="">
                                    </div>
                                </div>
                            </div>


                        </div>

                        <div class="row"> 
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r"
                                           for="txtFecAdq">Fecha Nota de Cr&eacute;dito:</label>
                                    <div class="col-sm-5">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" placeholder="DD/MM/YYYY"
                                                   name="txtFecAdq" id="txtFecAdq" autocomplete="off" disabled="">
                                            <span class="input-group-addon"><i
                                                    class="fa fa-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="row">
                            <div class="content  col-sm-2 " align="center">
                            </div>
                            <div class="content  col-sm-7"  style="background-color:#FCFDFE;" >
                                <table class=" table table-striped table-bordered table-hover " id="tblCarBien" style="background-color:#e7e7e7; "  >                
                                    <thead >
                                        <tr> 
                                            <th>CANTIDAD</th>
                                            <th>UNIDAD DE MEDIDA</th>
                                            <th>DESCRIPCIÓN</th>
                                            <th>VALOR UNITARIO</th>
                                            <th>IMPORTE</th>    
                                            <th>IGV</th>
                                            <th>ACCIONES</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody> 

                                </table>
                            </div>

                        </div>

                        <br>
                        <br>


                        <div class="row">
                            <div class="col-lg-6">

                            </div>
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-6 control-label m-r ">Sub Total</label>
                                    <div class="col-sm-3 ">
                                        <input class="form-control input-sm text-right" name="txtSubTotal"
                                               id="txtSubTotal" disabled="" type="text" value="0.00">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">

                            </div>
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-6 control-label m-r ">I.G.V.:</label>
                                    <div class="col-sm-3">
                                        <input class="form-control input-sm text-right" name="txtIGV"
                                               id="txtIGV" disabled="" type="text" value="0.00">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6">

                            </div>
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-6 control-label m-r">Total:</label>
                                    <div class="col-sm-3 ">
                                        <input class="form-control input-sm text-right" name="txtTotal" 
                                               id="txtTotal" disabled="" type="text" value="0.00" >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <div class="form-group reg_per">
                                        <label class="col-sm-2 control-label m-r" for="txtobservacion">Observaciones:</label>

                                        <div class="col-sm-9 ">

                                            <textarea class="form-control text-uppercase" name="txtobservacion" id="txtobservacion" style="resize: none" disabled=""></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <div class="form-group reg_per">
                                        <label class="col-sm-2 control-label m-r" fo    r="txtDetalleMotivo">Descripci&oacute;n del Motivo:</label>

                                        <div class="col-sm-9 ">

                                            <textarea class="form-control text-uppercase" name="txtDetalleMotivo" id="txtDetalleMotivo" style="resize: none"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r"
                                           for="txtFecha">Fecha del Documento: </label>
                                    <div class="col-sm-5">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" placeholder="DD/MM/YYYY"
                                                   name="txtFecha"id="txtFecha" autocomplete="off">
                                            <span class="input-group-addon"><i
                                                    class="fa fa-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-5">

                                    <button class="btn btn-OceanBoatBlue" type="button" name="btnRegistrarFacturaServ"
                                            id="btnRegistrarFacturaServ">Registrar
                                    </button>
                                    <button class="btn btn-danger" type="button" name="btnLimpiarNotaCredito"
                                            id="btnLimpiarNotaCredito">Cancelar
                                    </button>

                                </div>
                            </div>
                        </div>
                    </form>
                </div>


            </div>
        </div>


        <!-- ---------end js -------------->


        <script src="<c:url value="/resources/js/jsContabilidad/jsAnulacion/jsAnulacion.js"/>"type="text/javascript"></script>
        <script src="<c:url value="/resources/jsvalidaciones/jsValidacionMetodos.js"/>"type="text/javascript"></script>
        <!-- --------end body---------------------- -->
        <jsp:include page="/WEB-INF/structure/footer.jsp"/>
