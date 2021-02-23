
<%@page import="ll.negocio.administracion.PersonaJuridicaNEG"%>
<%@page import="ll.entidades.administracion.PersonaJuridica"%>
<%@page import="ll.entidades.administracion.Usuario"%>
<%@page import="ll.entidades.administracion.TipoMoneda"%>
<%@page import="ll.negocio.administracion.OficinaNEG"%>
<%@page import="ll.entidades.administracion.Oficina"%>
<%@page import="ll.negocio.contabilidad.*" %>
<%@page import="ll.entidades.contabilidad.*" %>
<%@page import="ll.negocio.logistica.*" %>
<%@page import="ll.entidades.logistica.*" %>
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
    <div class="col-md-12">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row">
                    <legend class="text-success"><b>Factura Electr&oacute;nica</b></legend>

                    <div class="tabs-container" id="tabs" name="tabs">
                        <ul id="tabsBien-navigation" class="nav nav-tabs">
                            <li class="active"><a href="#tabRegistrarBien" data-toggle="tab"><i
                                        class="glyphicon glyphicon-user"></i>Datos de la Factura Electr&oacute;nica</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-BuscarPerJur"><i 
                                        class="glyphicon fa fa-binoculars"></i> Buscar Factura Electr&oacute;nica</a></li>
                        </ul>
                        <div id="tabBien-content" class="tab-content">
                            <div class="tab-pane fade in active" id="tabRegistrarBien" name="tabRegistrarBien">
                                <form class="form-horizontal" id="form-registrarFactura" method="post"
                                      action="#tabRegistrarBien">
                                    <legend class="subtitulos"> Datos del Emisor</legend>

                                    <div class="row"> 
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">

                                                <label class="col-sm-4 control-label m-r"  for="txtRUCEmisor">RUC:</label>
                                                <div class="col-sm-5">

                                                    <input class="form-control input-sm autocomplete" disabled="" data-mask="99999999999" name="txtRUCEmisor" id=disa"txtRUCEmisor"  placeholder="Ingrese RUC"  type="text" value='20315295573'>
                                                    <%-- <div class="checkbox m-r-xs">
                                                        <input id="checkboxEmisor" name="checkboxEmisor" type="checkbox">
                                                        <label for="checkboxEmisor">
                                                            Cambiar Emisor
                                                        </label>
                                                    </div> --%>
                                                    <input class="form-control" name="txtidPersonaEmisor" id="txtidPersonaEmisor" value='0000001' type="hidden">              

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
                                                           id="txtRazonSocialEmisor" disabled=""  type="text" value='COMUNIDAD CAMPESINA LLACUABAMBA'></div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r" for="txtDireccionEmisor">Direccion:</label>

                                                <div class="col-sm-5">

                                                    <input class="form-control input-sm" name="txtDireccionEmisor"
                                                           id="txtDireccionEmisor" disabled=""  type="text" value='JR. AZUCENA NRO. 04 ANEXO LLACUABAMBA PARCOY - PATAZ - LA LIBERTAD'></div>

                                            </div>
                                        </div>
                                    </div>

                                    <legend class="subtitulos"> Datos del Cliente</legend>
                                    <div class="row"> 
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">

                                                <label class="col-sm-4 control-label m-r"  for="txtRUC">RUC:</label>
                                                <div class="col-sm-5">

                                                    <input name="txtRUC" id="txtRUC" class="form-control input-sm autocomplete"  disabled="" data-mask="99999999999"  placeholder="Ingrese RUC" value='20132367800'  type="text">
                                                    <div class="checkbox m-r-xs">
                                                        <input id="checkboxCliente" name="checkboxCliente" type="checkbox">
                                                        <label for="checkboxCliente">
                                                            Cambiar Cliente
                                                        </label>
                                                    </div>
                                                    <input class="form-control" name="txtidPersonaCliente" id="txtidPersonaCliente" value="0000002"  type="hidden">              

                                                </div>    
                                            </div> 
                                        </div>
                                    </div>

                                    <div class="row">

                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r" for="txtRazonSocial">Señor (es):</label>

                                                <div class="col-sm-5">

                                                    <input class="form-control input-sm" name="txtRazonSocial" value="MINERA AURIFERA RETAMAS S.A."
                                                           id="txtRazonSocial" disabled=""  type="text"></div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r" for="txtDireccion">Direccion:</label>

                                                <div class="col-sm-5">

                                                    <input class="form-control input-sm" name="txtDireccion" value="CAMPAMENTO SAN ANDRES - PARCOY - PATAZ - LA LIBERTAD"
                                                           id="txtDireccion" disabled=""  type="text"></div>

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

                                                <div class="col-sm-3 ">
                                                    <button class='btn btn-OceanBoatBlue' type='button' 
                                                            name='btnAgregarGR'
                                                            data-target="#ModalMF" data-toggle="modal"
                                                            id='btnAgregarGR'>Agregar Guia
                                                    </button>
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
                                                <label class="col-sm-4 control-label m-r"
                                                       for="txtFecAdq">Fecha:</label>
                                                <div class="col-sm-5">
                                                    <div class="input-group date">
                                                        <input type="text" class="form-control" placeholder="DD/MM/YYYY"
                                                               name="txtFecAdq"  disabled="" id="txtFecAdq" autocomplete="off">
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
                                        <div class="content  col-sm-1 " align="center">
                                        </div>
                                        <div class="content  col-sm-10"  style="background-color:#FCFDFE;" >
                                            <table class=" table table-striped table-bordered table-hover " id="tblCarBien" style="background-color:#e7e7e7;" >                
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
                                        <div class="content  col-sm-1 " align="center">
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="content  col-sm-1 " align="center">
                                        </div>
                                        <div class="content  col-sm-1 " align="center">
                                            <button class='btn btn-OceanBoatBlue' type='button' 
                                                    name='btnAgregarCaracteristica'
                                                    data-target="#ModalMF" data-toggle="modal"
                                                    id='btnAgregarCaracteristica'>Agregar Detalle
                                            </button>
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
                                                    <label class="col-sm-2 control-label m-r" fo="" r="txtobservacion">Observaciones:</label>

                                                    <div class="col-sm-9 ">

                                                        <textarea class="form-control text-uppercase" name="txtobservacion" id="txtobservacion" style="resize: none" onkeyup="Textarea_Sin_Enter(event.keyCode, event.which, 'txtobservacion');"></textarea>
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

                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                            <div id="tab-BuscarPerJur" class="tab-pane ">
                                <div class="panel-body">
                                    <form class="form-horizontal" id="form-buscar-PerJur"  name="form-buscar-PerJur" method="post">
                                        <div class="row" >
                                            <div class=" col-sm-1" >
                                            </div>
                                            <div class="well col-sm-10" >
                                                <div class="col-sm-2">
                                                </div>
                                                <div class="col-sm-3"> TIPO DE B&Uacute;QUEDA POR:
                                                    <br>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="optradio" id="optradio" value="2" checked="" onclick="toggle(this)">N&Uacute;MERO DE FACTURA
                                                    </label>
                                                    <br>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="optradio" id="optradio" value="1"  onclick="toggle(this)">RANGO FECHAS
                                                    </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group"  id="nroDocu" name="nroDocu">
                                                        <label class="col-sm-4 control-label m-r" for="txtBuscarNroDoc">N&uacute;mero Factura:</label>
                                                        <div class="col-sm-5" >
                                                            <input class="form-control m-t-sm input"  name="txtBuscarNroDoc" id="txtBuscarNroDoc" type="text" data-mask="F999-99999999" >
                                                        </div>
                                                    </div>
                                                    <div class="form-group" id="fechaDesde" name="fechaDesde">
                                                        <label class="col-sm-4 control-label m-r"
                                                               for="txtFechaDesde">Fecha Desde:</label>
                                                        <div class="col-sm-5">
                                                            <div class="input-group date">
                                                                <input type="text" class="form-control" placeholder="DD/MM/YYYY" disabled=""
                                                                       name="txtFechaDesde" id="txtFechaDesde" autocomplete="off">
                                                                <span class="input-group-addon"><i
                                                                        class="fa fa-calendar"></i></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group"  id="fechaHasta" name="fechaHasta">
                                                        <label class="col-sm-4 control-label m-r"
                                                               for="txtFechaHasta">Fecha Hasta:</label>
                                                        <div class="col-sm-5">
                                                            <div class="input-group date">
                                                                <input type="text" class="form-control" placeholder="DD/MM/YYYY" disabled=""
                                                                       name="txtFechaHasta" id="txtFechaHasta" autocomplete="off">
                                                                <span class="input-group-addon"><i
                                                                        class="fa fa-calendar"></i></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2" >
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
                                                        <th>Numero Documento</th>
                                                        <th>Ejecutor</th>
                                                        <th>Fecha Documento</th>
                                                        <th>Total Venta</th>
                                                        <th>Estado</th>
                                                        <th>Imprimir</th>
                                                        <th>Informaci&oacute;n</th>
                                                        <th>XML</th>
                                                        <th>CDR</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        if (request.getParameter("btnBuscarDocumento") != null) {
                                                            int criterio = Integer.parseInt(request.getParameter("optradio"));
                                                            String valor = null;
                                                            if (criterio == 1) {
                                                                valor = request.getParameter("txtFechaDesde") + "&" + request.getParameter("txtFechaHasta");
                                                            } else {
                                                                valor = request.getParameter("txtBuscarNroDoc");
                                                            }
                                                            List<ListaDocumentoCompraVenta> listaDetDocCompraVenta = DocumentoCompraVentaNEG.Instancia().listarDocVenta(criterio, valor);
                                                            for (int i = 0; i < listaDetDocCompraVenta.size(); i++) {
                                                                out.println("<tr>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaDetDocCompraVenta.get(i).getDocumentoNro() + "</td>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaDetDocCompraVenta.get(i).getEjecutor() + "</td>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaDetDocCompraVenta.get(i).getFecha() + "</td>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaDetDocCompraVenta.get(i).getTotal() + "</td>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaDetDocCompraVenta.get(i).getEstado() + "</td>");
                                                    %> 
                                                <td class="text-center" data-id="<%=listaDetDocCompraVenta.get(i).getIdOficina() + "&" + listaDetDocCompraVenta.get(i).getIdDoc() + "&"
                                                            + listaDetDocCompraVenta.get(i).getTipMoneda() + "&" + listaDetDocCompraVenta.get(i).getNroDoc()%>" >
                                                    <% if (listaDetDocCompraVenta.get(i).getEstado().equals("REGISTRADO")) {%> 
                                                    <a  href = "javascript:;" class="btn btn-Lapislazuli btn-circle remove" title = "Ver" > <i 
                                                            class="fa fa-print "></i > </a>   
                                                        <% }
                                                        %> 
                                                </td>

                                                <td class="text-center" data-idFac="<%=listaDetDocCompraVenta.get(i).getDocumentoNro()%>" >
                                                    <% if (listaDetDocCompraVenta.get(i).getEstado().equals("REGISTRADO")) {%> 
                                                    <a  href = "javascript:;" class="btn btn-Lapislazuli btn-circle info" title = "Info" > <i 
                                                            class="fa fa-info-circle"></i > </a>   
                                                        <% }
                                                        %> 
                                                </td>


                                                <td class="text-center" >
                                                    <% if (listaDetDocCompraVenta.get(i).getEstado().equals("REGISTRADO")) {%> 
                                                    <a  href = "../sDownload?idFact=<%="01-" + listaDetDocCompraVenta.get(i).getDocumentoNro()%>" class="btn btn-Lapislazuli btn-circle" title = "Descargar XML" > <i 
                                                            class="fa fa-cloud-download"></i > </a>   
                                                        <% }
                                                        %> 
                                                </td>

                                                <td class="text-center" >
                                                    <% if (listaDetDocCompraVenta.get(i).getEstado().equals("REGISTRADO")) {%> 
                                                    <a  href = "../sDownloadCDR?idFactCDR=<%="01-" + listaDetDocCompraVenta.get(i).getDocumentoNro()%>" class="btn btn-Lapislazuli btn-circle" title = "Descargar CDR" > <i 
                                                            class="fa fa-cloud-download"></i > </a>   
                                                        <% }
                                                        %> 
                                                </td>


                                                <%
                                                        }
                                                        out.print("<input type='hidden' name='variable' id='txtVariable' value='tab-BuscarPerJur'>");
                                                    }
                                                %>
                                                </tbody>  
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal -->
                <div class="modal inmodal fade" id="modalBuscar" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                        </div> <!-- /.modal-content -->
                    </div> <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

                <!-- Modal -->
                <div class="modal inmodal fade" id="modal-infoFactura" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <i class="fa fa-laptop modal-icon"></i>
                                <h4 class="modal-title">Informacion Factura</h4>
                            </div>
                            <form class="form-horizontal" id="frmCaractBien1" name="frmCaractBien1" method="Post">
                                <div class="modal-body" id="modalCaractBienbody" name="modalCaractBienbody">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Respuesta de SUNAT:</label>
                                                <div class="col-sm-9">
                                                    <textarea  readonly="readonly" class="form-control text-uppercase" name="txtResponse" id="txtResponse"style="resize: none" ></textarea>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- /.modal-dialog -->
                            </form >
                        </div>     
                    </div>
                </div>
                <!-- /.modal -->
                <!--CARACTERISTICAS-->
                <div class="modal inmodal" id="modalCaracteristicas"  role="dialog" aria-hidden="true" style="overflow:hidden;">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <i class="fa fa-laptop modal-icon"></i>
                                <h4 class="modal-title">Detalle de Factura</h4>
                            </div>
                            <form class="form-horizontal" id="frmCaractBien" name="frmCaractBien" method="Post">
                                <div class="modal-body" id="modalCaractBienbody" name="modalCaractBienbody">

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Tipo de Servicio:</label>
                                                <div class="col-sm-9">
                                                    <%  out.println("<select class='select2-selection__rendered form-control' style='font-size:9pt' data-style='btn-default' name='cboTipoServicio' id='cboTipoServicio'>");
                                                        for (TipoServicio tipServ : TipoServicioNEG.Instancia().listar()) {
                                                            out.println("<option value='" + tipServ.getIdTipoServicio() + "'>" + tipServ.getDescripcion() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Unidad de Medida:</label>
                                                <div class="col-sm-9">
                                                    <%  out.println("<select class='select2-selection__rendered form-control' style='font-size:9pt' data-style='btn-default' name='cboUnidadMedida' id='cboUnidadMedida'>");
                                                        for (UnidadMedida tipUniMed : GuiaRemisionNEG.Instancia().listarUnidadMedida()) {
                                                            out.println("<option value='" + tipUniMed.getCodigoMedida() + "'>" + tipUniMed.getDescripcion() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %>

                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">


                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" fo    r="txtDetFact">Detalle:</label>

                                                <div class="col-sm-9 ">

                                                    <textarea class="form-control text-uppercase" name="txtDetFact" id="txtDetFact"style="resize: none" ></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" for="txtValUnit">Valor Unitario</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtValUnit"
                                                           id="txtValUnit" type="text" autocomplete="off" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" for="txtPreUniFact">Precio Unitario</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtPreUniFact"
                                                           id="txtPreUniFact" type="text" autocomplete="off" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" for="txtCantFact">Cantidad</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtCantFact"
                                                           id="txtCantFact" type="text" autocomplete="off" value="1">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" for="txtImpFact">Importe:</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtImpFact"
                                                           id="txtImpFact" type="text" autocomplete="off">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" for="txtIGVDet">IGV:</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtIGVDet"
                                                           id="txtIGVDet" type="text">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-white" data-dismiss="modal">Cancelar</button>
                                    <button class="btn btn-success" type="submit" name="btnRegistrarCar" id="btnRegistrarCar">Agregar</button>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>

                <div class="modal inmodal" id="modalModCaracteristicas"  role="dialog" aria-hidden="true" style="overflow:hidden;">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <i class="fa fa-laptop modal-icon"></i>
                                <h4 class="modal-title">Detalle de Factura</h4>
                            </div>
                            <form class="form-horizontal" id="frmCaractBienEdit" name="frmCaractBienEdit" method="Post">
                                <div class="modal-body" id="modalCaractBienbodyEdit" name="modalCaractBienbodyEdit">

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Descripcion:</label>
                                                <div class="col-sm-9">
                                                    <textarea class="form-control input-sm text-right"   style="resize: none" disabled="" name="txtTipoServicioEdit" id="txtTipoServicioEdit" type="text" autocomplete="off" ></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Unidad de Medida:</label>
                                                <div class="col-sm-9">

                                                    <input class="form-control input-sm text-right" disabled="" name="txtUnidadMedidaEdit" id="txtUnidadMedidaEdit" type="text" autocomplete="off" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Valor Unitario</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtValUnitEdit"
                                                           id="txtValUnitEdit" type="text" autocomplete="off" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Precio Unitario</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtPreUniFactEdit"
                                                           id="txtPreUniFactEdit" type="text" autocomplete="off" >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Cantidad</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtCantFactEdit"
                                                           id="txtCantFactEdit" type="text" autocomplete="off" value="1">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" >Importe:</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtImpFactEdit"
                                                           id="txtImpFactEdit" type="text" autocomplete="off">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" for="txtIGVDetEdit">IGV:</label>

                                                <div class="col-sm-9 ">

                                                    <input class="form-control input-sm text-right" name="txtIGVDetEdit"
                                                           id="txtIGVDetEdit" type="text">
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <input name="txtDescAnt" id="txtDescAnt" type="hidden">

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-white" data-dismiss="modal">Cancelar</button>
                                    <button class="btn btn-success" type="submit" name="btnRegistrarEdit" id="btnRegistrarEdit" >Modificar</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
                <!--GUIAREMISION-->
                <div class="modal inmodal" id="modalGuiaRemision"  role="dialog" aria-hidden="true" style="overflow:hidden;">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <i class="fa fa-laptop modal-icon"></i>
                                <h4 class="modal-title">Agregar Gu&iacute;a de Remisi&oacute;n</h4>
                            </div>
                            <form class="form-horizontal" id="frmGuiaRemision" name="frmGuiaRemision" method="Post">
                                <div class="modal-body" id="modalGuiaRemisionbody" name="modalGuiaRemisionbody">

                                    <div class="row">
                                        <div class="col-lg-12">


                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r">Tipo de Guia</label>
                                                <div class="col-sm-5">
                                                    <select class='simple-select form-control' name="cboTipGuia"id="cboTipGuia">
                                                        <option value="09">GUIA REMISION EMISOR</option>      
                                                        <option value="31">GUIA REMISION TRANSPORTISTA</option>                                                   
                                                    </select>
                                                </div>
                                            </div> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-2 control-label m-r" for="txtNroGuia">Nro de Guia:</label>

                                                <div class="col-sm-3 ">
                                                    <input class="form-control m-t-sm input"  name="txtNroGuia" id="txtNroGuia" type="text" data-mask="9999-99999999"  autocomplete="off">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div clss="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label m-r" for="txtFechaGuia">Fecha:</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group date">
                                                        <input type="text" class="form-control" placeholder="DD/MM/YYYY" 
                                                               name="txtFechaGuia" id="txtFechaGuia" autocomplete="off">
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-calendar"></i></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-white" data-dismiss="modal">Cancelar</button>
                                    <button class="btn btn-success" type="submit" name="btnRegistrarGR" id="btnRegistrarGR">Agregar</button>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<!-- ---------end js -------------->


<script src="<c:url value="/resources/js/jsContabilidad/jsDocVenta/jsDocVenta.js"/>"
type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsContabilidad/jsDocVenta/jsDocVentaDetalle.js"/>"
type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsContabilidad/jsDocVenta/jsDocVentaGR.js"/>"
type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionContabilidad/jsValidacionFacturaServicios.js"/>"
type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionMetodos.js"/>"
type="text/javascript"></script>



<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
