<%@page import="ll.negocio.contabilidad.TipoComprobantePagoNEG"%>
<%@page import="ll.entidades.contabilidad.TipoComprobantePago"%>
<%@page import="ll.entidades.administracion.Usuario"%>
<%@page import="ll.negocio.contabilidad.TipoMonedaNEG"%>
<%@page import="ll.entidades.administracion.TipoMoneda"%>
<%@page import="ll.negocio.administracion.TipoDocIdentidadNEG"%>
<%@page import="ll.entidades.administracion.TipoDocIdentidad"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />
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
                    <legend class="text-success">Documento de Retenci&oacute;n</legend>
                    <div class="panel-body">
                        <form class="form-horizontal" id="frmRetencion"  name="frmRetencion" method="post">
                            <div class="row"  style="border-style : none;">
                                <div class="col-md-10 col-md-offset-1">                                                   
                                    <div id="divopt" name="divopt">
                                        <div class="row">
                                            <div class="col-lg-12">  
                                                <legend class="subtitulos">Periodo Triburario</legend>
                                                <div class="row col-md-offset-2" id="Socio">
                                                    <div class="col-lg-6 m-b-xs">
                                                        <div class="form-group reg_per" id="data_1">
                                                            <label class="col-md-4 control-label">Fecha de emisi&oacute;n:</label>
                                                            <div class="col-md-7">
                                                                <div class="input-group date">
                                                                    <input type="text" class="form-control"   placeholder="DD/MM/YYYY" name="txtFecEmi" id="txtFecEmi" autocomplete="off" readonly="">
                                                                    <span class="input-group-addon">
                                                                        <i class="fa fa-calendar"></i>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row col-md-offset-2" id="Socio">
                                                    <div class="col-lg-6 m-b-xs">
                                                        <div class="form-group reg_per" id="data_1">
                                                            <label class="col-md-4 control-label">Periodo:</label>
                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" name="txtPeriodo" id="txtPeriodo"readonly="">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <legend class="subtitulos">Proveedor</legend>
                                                <div class="row col-md-offset-1" id="Socio">
                                                    <div class="col-lg-8">
                                                        <div class="form-group reg_per">
                                                            <label class="col-sm-2 control-label m-r" >Identificaci&oacute;n:</label>
                                                            <div class="col-sm-3">
                                                                <% List<TipoDocIdentidad> listTipoDocIdentidad = TipoDocIdentidadNEG.Instancia().listarTipoDocIdentidad("J");
                                                                    out.println("<select name='cboTipDocumento' id='cboTipDocumento'  class='simple-select form-control'>");
                                                                    for (int i = 0; i < listTipoDocIdentidad.size(); i++) {
                                                                        out.println("<option value='" + listTipoDocIdentidad.get(i).getIdTipoDocIdentidad() + "'>" + listTipoDocIdentidad.get(i).getDescripcion() + "</option>");
                                                                    }
                                                                    out.println("</select>");
                                                                %>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control" name="txtRUC" id="txtRUC" onkeypress="return inputPorcentajesoloNumero(event);" maxlength="11">
                                                            </div>
                                                        </div>                                
                                                    </div>                                                  
                                                </div>
                                                <div class="row col-md-offset-1" id="Socio">
                                                    <div class="col-lg-8">
                                                        <div class="form-group reg_per">
                                                            <label class="col-sm-2 control-label m-r" for="txtRazonSocial">Raz&oacute;n Social:</label>

                                                            <div class="col-sm-7">
                                                                <input type="text" class="form-control" name="txtRazonSocial" id="txtRazonSocial" readonly="">
                                                                <input type="text" class="form-control" name="txtidpersonaJN" id="txtidpersonaJN" hidden="">
                                                            </div>
                                                        </div>                                
                                                    </div>                                                  
                                                </div>
                                                <div class="row col-md-offset-3" id="Socio">
                                                    <div class="col-lg-8">
                                                        <div class="form-group reg_per">
                                                            <div class="checkbox m-r-xs">
                                                                <input id="checkboxPerInter" name="checkboxPerInter" type="checkbox" value="0">
                                                                <label for="checkboxPerInter">
                                                                    Es agente de percepción venta interna
                                                                </label>
                                                            </div>
                                                            <div class="checkbox m-r-xs">
                                                                <input id="checkboxPercombu" name="checkboxPercombu" type="checkbox" value="0">
                                                                <label for="checkboxPercombu">
                                                                    Es agente de percepción de combustible
                                                                </label>
                                                            </div>
                                                            <div class="checkbox m-r-xs">
                                                                <input id="checkboxreten" name="checkboxreten" type="checkbox" value="0">
                                                                <label for="checkboxreten">
                                                                    Es agente de retención
                                                                </label>
                                                            </div>
                                                            <div class="checkbox m-r-xs">
                                                                <input id="checkboxcontri" name="checkboxcontri" type="checkbox" value="0">
                                                                <label for="checkboxcontri">
                                                                    Es buen contribuyente
                                                                </label>
                                                            </div>
                                                        </div>                                
                                                    </div>                                                  
                                                </div>       
                                                <legend class="subtitulos">Retenci&oacute;n</legend>
                                                <div class="row">
                                                    <div class="row col-md-offset-1" id="Socio">
                                                        <div class="col-lg-8">
                                                            <div class="form-group reg_per">
                                                                <label class="col-sm-2 control-label m-r">R&eacute;gimen de Retenci&oacute;n:</label>
                                                                <div class="col-sm-7">
                                                                    <select class="simple-select form-control" name="cboRegimen" id="cboRegimen">                                                                    
                                                                        <option value="0.03">TASA 3%</option>

                                                                    </select>
                                                                </div>
                                                            </div>                                
                                                        </div>                                                  
                                                    </div>
                                                    <div class="row col-md-offset-1" id="Socio">
                                                        <div class="col-lg-8">
                                                            <div class="form-group reg_per">
                                                                <label class="col-sm-2 control-label m-r" >Observaciones:</label>
                                                                <div class="col-sm-7">
                                                                    <textarea class="form-control text-uppercase" name="txtobservacion" id="txtobservacion" style="resize: none" onkeyup="Textarea_Sin_Enter(event.keyCode, event.which, 'txtobservacion');"></textarea>
                                                                </div>
                                                            </div>      
                                                        </div>                                                  
                                                    </div>                                           
                                                </div> 
                                                <hr>
                                                <legend class="subtitulos">Relación de Documentos Relacionados</legend>
                                                <div class="row">
                                                    <div class="col-md-3 col-md-offset-11">
                                                        <button class="btn btn-OceanBoatBlue btn-sm" type="button" name="btnModalRetencion" id="btnModalRetencion">
                                                            <i class="fa fa-plus-square"></i>
                                                            Añadir
                                                        </button> 
                                                    </div>                                                    
                                                </div>
                                            </div> 
                                            <div class="col-lg-12">
                                                <div class="content  col-sm-12"  style="background-color:#FCFDFE;" >
                                                    <table class=" table table-striped table-bordered table-hover " id="tblDetRet" style="background-color:#e7e7e7;" >                
                                                        <thead >
                                                            <tr> 
                                                                <th>TIPO DOCUMENTO</th>
                                                                <th>SERIE</th>
                                                                <th>NUMERO</th>
                                                                <th>FECHA EMISION</th>
                                                                <th>TOTAL COMPROBANTE</th>    
                                                                <th>NRO. DE PAGO</th>
                                                                <th>IMPORTE DE PAGO</th>
                                                                <th>TASA %</th>
                                                                <th>RETENCION</th>
                                                                <th>IMPORTE NETO PAGADO</th>
                                                                <th>ACCIONES</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody> 

                                                    </table>
                                                </div>
                                            </div>    
                                            <div class="row col-md-offset-7">
                                                <div class="col-lg-11">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-3 control-label m-r" >Importes Totales:</label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control" name="txtTotalReten" id="txtTotalReten"readonly="">
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control" name="txtTotalImpNeto" id="txtTotalImpNeto"readonly="">
                                                        </div>
                                                    </div>      
                                                </div>                                                    
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-sm-10 col-sm-offset-5">

                                                        <button class="btn btn-OceanBoatBlue" type="button" name="btnRegistrarRetenciones"
                                                                id="btnRegistrarRetenciones">Registrar
                                                        </button>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form> 
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalRetencion" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  style="display: none;">
    <div class="modal-dialog  modal-lg">
        <div class="modal-content">
            <div class="modal-header panel-title">
                <button type=button" class="close" data-dismiss="modal" aria-hidden="true" id="cerrarModal">&times;</button>
                <div class="row">
                    <div class="col-md-12">
                        <label style="text-align: right; font-size: 150%; color: #3078BB">Datos del Comprobante Relacionado (Pago):</label>
                    </div>
                </div>
            </div>
            <form class="form-horizontal" id="frmFactReten" name="frmFactReten" method="Post">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12" style="text-align: left">
                            <legend class="subtitulos">Datos del Comprobante</legend>
                            <div class="row">
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r" >Tipo de Documento:</label>
                                            <div class="col-sm-7">
                                                <%  List<TipoComprobantePago> objTipoComprobantePago = TipoComprobantePagoNEG.Instancia().listar();
                                                    out.println("<select name='cboTipDocSUNAT' id='cboTipDocSUNAT' class='simple-select form-control m-r-n'>");
                                                    for (int i = 0; i < objTipoComprobantePago.size(); i++) {
                                                        out.println("<option value='" + objTipoComprobantePago.get(i).getIdTipoComprobante() + "'>" + objTipoComprobantePago.get(i).getDescripcion() + "</option>");
                                                    }
                                                    out.println("</select>");
                                                %> 
                                            </div>
                                        </div>                                
                                    </div>                                                  
                                </div>
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Nro. de Documento:</label>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control" name="txtSerieDoc" id="txtSerieDoc" maxlength="4" onkeypress="return inputPorcentajesoloNumero(event);">
                                            </div>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" name="txtNroDoc" id="txtNroDoc" maxlength="8" onkeypress="return inputPorcentajesoloNumero(event);">
                                            </div>
                                        </div>                                
                                    </div>                                                  
                                </div>  
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Fecha de emisi&oacute;n:</label>
                                            <div class="col-md-7">
                                                <div class="input-group date">
                                                    <input type="text" class="form-control"   placeholder="DD/MM/YYYY" name="txtFecEmi2" id="txtFecEmi2" readonly="" autocomplete="off">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>                               
                                    </div>                                                  
                                </div>
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Importe Total de Comprobante:</label>
                                            <div class="col-md-7">
                                                <input type="text" class="form-control" name="txtTotal" id="txtTotal" onkeypress="return inputdecimales(event);"onblur="validarMontoOperacion();">
                                            </div>
                                        </div>                               
                                    </div>                                                  
                                </div>
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Tipo de Moneda:</label>
                                            <div class="col-sm-7">
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
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Importe de la Operaci&oacute;n:</label>
                                            <div class="col-md-7" id="txtImporteOpe">

                                            </div>
                                        </div>                               
                                    </div>                                                  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="text-align: left">
                            <legend class="subtitulos">Datos del Pago</legend>
                            <div class="row">
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Fecha de Pago:</label>
                                            <div class="col-md-7">
                                                <div class="input-group date">
                                                    <input type="text" class="form-control" placeholder="DD/MM/YYYY" name="txtFecPago" id="txtFecPago" readonly="" autocomplete="off">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>                               
                                    </div>                                                  
                                </div>
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Tipo de Cambio:</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" name="txtTC" id="txtTC" value="1.00" readonly="" onkeypress="return inputdecimales(event);"onblur="calculoRetencion();" >
                                            </div>

                                        </div>                                
                                    </div>                                                  
                                </div>  
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Número Correlativo de Pago:</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" name="txtCorrelativo" id="txtCorrelativo"  onkeypress="return inputPorcentajesoloNumero(event);"> 
                                            </div>

                                        </div>                                
                                    </div>                                                  
                                </div>
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Importe de Pago(Sin aplicar retenci&oacute;n):</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" name="txtImporteSR" id="txtImporteSR" onkeypress="return inputdecimales(event);"onblur="calculoRetencion();">
                                            </div>

                                        </div>                                
                                    </div>                                                  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="text-align: left">
                            <legend class="subtitulos">Datos de la Retenci&oacute;n</legend>
                            <div class="row">
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Regimen de Retenci&oacute;n:</label>
                                            <div class="col-sm-7" id="divICSol">

                                            </div>
                                        </div>                               
                                    </div>                                                  
                                </div>
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Importe Retenido:</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" name="txtIR" id="txtIR" readonly=" ">
                                            </div>

                                        </div>                                
                                    </div>                                                  
                                </div>  
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Fecha de Retenci&oacute;n:</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" name="fechaReten" id="fechaReten" readonly="">
                                            </div>

                                        </div>                                
                                    </div>                                                  
                                </div>
                                <div class="row col-md-offset-1" id="Socio">
                                    <div class="col-lg-8">
                                        <div class="form-group reg_per">
                                            <label class="col-sm-4 control-label m-r">Importe de neto pagado (deducida la retenci&oacute;n):</label>
                                            <div class="col-sm-7">
                                                <input type="text" class="form-control" name="txtImporteNP" id="txtImporteNP" readonly="">
                                            </div>

                                        </div>                                
                                    </div>                                                  
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success" type="button" id="btn_Agregar" >Grabar</button>
                    <button class="btn btn-success" type="button" id="btn_Cerrar">Cerrar</button>

                </div>
            </form>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/jsContabilidad/jsComprobanteRentecion/jsComprobanteRentecion.js"/>"type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionContabilidad/jsValidacionRetencion.js"/>"type="text/javascript"></script>
<jsp:include page="/WEB-INF/structure/footer.jsp" />
