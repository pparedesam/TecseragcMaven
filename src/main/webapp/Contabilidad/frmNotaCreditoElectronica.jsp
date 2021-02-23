<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>

<div class="row">
    <div class="col-md-12" style="text-align: left">
        <legend class="text-success"><b>NOTA DE CR&Eacute;DITO</b></legend>
    </div>
</div>

<div class="row" >
    <div class="col-md-102 well" style="background-color: #FFF;">
        <ul class="nav nav-tabs" id="tabNotaCredito">
            <li role="presentation" class="active"><a href="#Busqueda">B&Uacute;SQUEDA DE NOTAS</a></li>
            <li role="presentation"><a href="#nuevaNotaCredito">NUEVA NOTA CR&Eacute;DITO</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="Busqueda">
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-2">
                            <select class='simple-select form-control' data-style='btn-default' name='cboBusqueda' id='cboBusqueda'>
                                <option value="2">NOTA CR&Eacute;DITO</option>
                                <option value="1">FECHA</option>
                            </select>                
                        </div>
                        <div class="col-md-8" id="busquedaNroFactura">
                            <input type="text" class="form-control text-uppercase" name="txtBusquedaNroNotaCredito" id="txtBusquedaNroNotaCredito" autocomplete="off" maxlength="50" style="display: block;"/>
                        </div>
                        <div class="col-md-8" id="busquedaFechas" style="display: none;">
                            <label class="col-md-2 control-label" for="txtBusFecInicio" style="text-align: right;">Desde: </label>
                            <div class="col-md-4" style="padding-right:15px">
                                <div class="input-group date">
                                    <input type="text" class="form-control" id="txtBusFecInicio" name="txtBusFecInicio" readonly>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>

                            <label class="col-md-2 control-label" for="txtBusFecFin" style="text-align: right;">Hasta: </label>
                            <div class="col-md-4" style="padding-right:15px">
                                <div class="input-group date">
                                    <input type="text" class="form-control" id="txtBusFecFin" name="txtBusFecFin" readonly>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success col-md-12" name="btnBuscarNotaCredito" id="btnBuscarNotaCredito" title="Buscar nota credito"><i class="fa fa-search" aria-hidden="true" ></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblNotasCredito" name="tblNotasCredito">
                            <thead>
                                <tr>
                                    <th>Nro Documento</th>
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
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div role="tabpanel" class="tab-pane" id="nuevaNotaCredito">
                <br>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><label>B&Uacute;SQUEDA FACTURA</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <label class="col-md-1 control-label" for="txtNroFactura" style="text-align: right;">NRO FACTURA: </label>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control text-uppercase" name="txtNroFactura" id="txtNroFactura" autocomplete="off" disabled />
                                        </div>
                                        <label class="col-md-1 control-label" for="txtFechaEmision" style="text-align: right;">Fecha emisi&oacute;n: </label>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control text-uppercase" name="txtFechaEmision" id="txtFechaEmision" autocomplete="off" disabled />
                                        </div>

                                        <label class="col-md-1 control-label" for="cboTipo" style="text-align: right;">Tipo:</label>
                                        <div class="col-md-3">
                                            <select class='simple-select form-control' data-style='btn-default' name='cboTipo' id='cboTipo'>
                                            </select>                
                                        </div>

                                        <div class="col-md-2 pull-right">
                                            <button class="btn btn-success col-md-12" name="btnBuscarFactura" id="btnBuscarFactura" title="Buscar facturas" data-target="#modalFactura" data-toggle="modal"><i class="fa fa-search" aria-hidden="true" ></i>&nbsp; Buscar Factura</button> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row" id="rowEmisor">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><label>EMISOR</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <label class="col-md-1 control-label" for="txtEmisorRUC" style="text-align: right;">RUC: </label>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control text-uppercase" name="txtEmisorRUC" id="txtEmisorRUC" autocomplete="off" value='20315295573' disabled />
                                        </div>
                                        <label class="col-md-1 control-label" for="txtEmisorRazonSocial" style="text-align: right;">Razon Social: </label>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control text-uppercase" name="txtEmisorRazonSocial" id="txtEmisorRazonSocial" autocomplete="off" value="COMUNIDAD CAMPESINA LLACUABAMBA" disabled />
                                        </div>

                                        <label class="col-md-1 control-label" for="txtEmisorDireccion" style="text-align: right;">Direcci&oacute;n: </label>
                                        <div class="col-md-4">
                                            <input type="text" class="form-control text-uppercase" name="txtEmisorDireccion" id="txtEmisorDireccion" autocomplete="off" value="JR. AZUCENA NRO. 04 ANEXO LLACUABAMBA PARCOY - PATAZ - LA LIBERTAD" disabled />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row" id="rowCliente">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><label>CLIENTE</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <label class="col-md-1 control-label" for="txtClienterRUC" style="text-align: right;">RUC: </label>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control text-uppercase" name="txtClienterRUC" id="txtClienterRUC" autocomplete="off" disabled />
                                        </div>
                                        <label class="col-md-1 control-label" for="txtClienteRazonSocial" style="text-align: right;">Razon Social: </label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control text-uppercase" name="txtClienteRazonSocial" id="txtClienteRazonSocial" autocomplete="off" disabled />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <label class="col-md-1 control-label" for="txtClienteDireccion" style="text-align: right;">Direcci&oacute;n:</label>
                                        <div class="col-md-11">
                                            <input type="text" class="form-control text-uppercase" name="txtClienteDireccion" id="txtClienteDireccion" autocomplete="off" disabled />
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" id="rowGuiaRemision">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><label>GU&Iacute;A DE REMISI&Oacute;N</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div id="divTablaEmisor" class="col-md-12"> 
                                        <table class="table table-striped table-bordered table-hover" id="tblGuiaRemision" name="tblGuiaRemision">
                                            <thead>
                                                <tr>
                                                    <th class='text-center'>TIPO GU&Iacute;A</th>
                                                    <th class='text-center'>EMISOR</th>
                                                    <th class='text-center'>NRO GU&Iacute;A</th>
                                                    <th class='text-center'>FECHA</th>
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
                <div class="row" id="rowDetalleDocumento">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><label>DETALLE VENTA</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <label class="col-md-1 control-label" for="txtFechaDoc" style="text-align: right;">Fecha: </label>
                                    <div class="col-md-3" style="padding-right:15px">
                                        <div class="input-group date">
                                            <input type="text" class="form-control" id="txtFechaDoc" name="txtFechaDoc" readonly>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-th"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <label class="col-md-1 control-label" for="cboMoneda" style="text-align: right;">Moneda:</label>
                                    <div class="col-md-3">
                                        <select class='simple-select form-control' data-style='btn-default' name='cboMoneda' id='cboMoneda' disabled>
                                            <option value="1">SOLES</option>
                                            <option value="2">DOLARES</option>
                                        </select>                
                                    </div>
                                    <div class="col-md-2 pull-right">
                                        <button class="btn btn-success col-md-12 " name="btnAgregarItem" id="btnAgregarItem" title="Agregar item" data-target="#modalDetalle" data-toggle="modal"><i class="fa fa-plus" aria-hidden="true" ></i>&nbsp; Agregar item</button> 
                                    </div>

                                </div>
                                <br>
                                <div class="row">
                                    <div id="divTablaDetalle" class="col-md-12"> 
                                        <table class="table table-striped table-bordered table-hover" id="tblDetalle" name="tblDetalle">
                                            <thead>
                                                <tr>
                                                    <th class='text-center'>CANT.</th>
                                                    <th class='text-center'>UND. MEDIDA</th>
                                                    <th class='text-center'>DESCRIPCI&Oacute;N</th>
                                                    <th class='text-center'>V. UNIT.</th>
                                                    <th class='text-center'>I.G.V.</th>
                                                    <th class='text-center'>V. TOTAL</th>
                                                    <th class='text-center'>ACCIONES</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th colspan="5" style="text-align: right;">SubTotal:</label></th>
                                                    <th colspan="1" style="text-align: right;"><label id="lblSubTotal"></label></th>
                                                    <th colspan="1"></th>
                                                </tr>
                                                <tr>
                                                    <th colspan="5" style="text-align: right;">I.G.V.:</th>
                                                    <th colspan="1" style="text-align: right;"><label id="lblIGV"></label></th>
                                                    <th colspan="1"></th>
                                                </tr>
                                                <tr>
                                                    <th colspan="5" style="text-align: right;">Total: </th>
                                                    <th colspan="1" style="text-align: right;"><label id="lblTotal"></label></th>
                                                    <th colspan="1"></th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-12">

                                        <label class="col-md-1 control-label" for="txtObservacion" style="text-align: right;">Observaciones: </label>
                                        <div class="col-md-11">
                                            <textarea class="form-control text-uppercase" id="txtObservacion"  name="txtObservacion" rows="3" style="resize: none;" disabled></textarea>
                                        </div>
                                    </div>
                                </div> 
                                <br>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label class="col-md-12 control-label" for="txtObservacionCantCaract" id="txtObservacionCantCaract" style="text-align: right;">0 de 200 caracteres </label>
                                        <label class="col-md-1 control-label" for="txtMotivo" style="text-align: right;">Motivo: </label>
                                        <div class="col-md-11">
                                            <textarea class="form-control text-uppercase" id="txtMotivo"  name="txtMotivo" rows="3" style="resize: none;"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-2 col-md-offset-5">
                            <button class="btn btn-success col-md-12" name="btnRegistrarNotaCredito" id="btnRegistrarNotaCredito" title="Registrar Nota Cr&eacute;dito"><i class="fa fa-save" aria-hidden="true" ></i>&nbsp; Registrar Nota Cr&eacute;dito</button> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--MODAL BUSCAR FACTURA--%>
<div class="modal fade" id="modalFactura" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR FACTURA</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-2">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalBusquedaFactura' id='cboModalBusquedaFactura'>
                                <option value="2">NRO FACTURA</option>
                                <option value="1">FECHA</option>
                            </select>                
                        </div>
                        <div class="col-md-8" id="busquedaModalNroFactura">
                            <input type="text" class="form-control text-uppercase" name="txtModalBusquedaNroFactura" id="txtModalBusquedaNroFactura" autocomplete="off" maxlength="50" style="display: block;"/>
                        </div>
                        <div class="col-md-8" id="busquedaModalFechas" style="display: none;">
                            <label class="col-md-2 control-label" for="txtModalBusFecInicio" style="text-align: right;">Desde: </label>
                            <div class="col-md-4" style="padding-right:15px">
                                <div class="input-group date">
                                    <input type="text" class="form-control" id="txtModalBusFecInicio" name="txtModalBusFecInicio" readonly>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>

                            <label class="col-md-2 control-label" for="txtModalBusFecFin" style="text-align: right;">Hasta: </label>
                            <div class="col-md-4" style="padding-right:15px">
                                <div class="input-group date">
                                    <input type="text" class="form-control" id="txtModalBusFecFin" name="txtModalBusFecFin" readonly>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success col-md-12" name="btnModalBuscarFactura" id="btnModalBuscarFactura" title="Buscar facturas"><i class="fa fa-search" aria-hidden="true" ></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaModalFactura" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalFactura" name="tblModalFactura">
                            <thead>
                                <tr>
                                    <th>Nro Documento</th>
                                    <th>Ejecutor</th>
                                    <th>Fecha Documento</th>
                                    <th>Total Venta</th>
                                    <th>Estado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-6 col-md-offset-6" style="text-align:right;"> 
                        <button type="button" class="btn btn-success col-md-4 pull-right" data-dismiss="modal">Cerrar</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--MODAL DETALLE--%>
<div class="modal fade" id="modalDetalle" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">AGREGAR ITEM</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-1 control-label" for="cboModalDetalleTipoServicio" style="text-align: right;">Tipo servicio:</label>
                        <div class="col-md-11">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalDetalleTipoServicio' id='cboModalDetalleTipoServicio'>
                            </select>                
                        </div>
                    </div>
                </div>
                <br>
                <div class="row" >
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="cboModalDetalleUniMedida" style="text-align: right;">Uni. Medida:</label>
                                <div class="col-md-10">
                                    <select class='simple-select form-control' data-style='btn-default' name='cboModalDetalleUniMedida' id='cboModalDetalleUniMedida'>
                                    </select>                
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="cboModalDetalleDescripcion" style="text-align: right;">Descripci&oacute;n: </label>
                                <div class="col-md-10">
                                    <textarea class="form-control text-uppercase" id="cboModalDetalleDescripcion"  name="cboModalDetalleDescripcion" rows="10" style="resize: none;" maxlength="1000"></textarea>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="col-md-6">
                        <div class="row" >
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="txtModalDetalleCant" style="text-align: right;">Cant:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleCant" id="txtModalDetalleCant" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format4(this);" value='1.00'/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row" >
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="txtModalDetalleVUnit" style="text-align: right;">V. Unit:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleVUnit" id="txtModalDetalleVUnit" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format(this);" value='0.00'/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row" >
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="txtModalDetallePUnit" style="text-align: right;">P. Unit:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetallePUnit" id="txtModalDetallePUnit" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format(this);" value='0.00' />
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row" >
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="txtModalDetalleVTotal" style="text-align: right;">Sub Total:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleVTotal" id="txtModalDetalleVTotal" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format(this);" value='0.00' />
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row" >
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="txtModalDetalleIGV" style="text-align: right;">I.G.V:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleIGV" id="txtModalDetalleIGV" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format(this);" value='0.00' />
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row" >
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="txtModalDetalleImporte" style="text-align: right;">Total:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleImporte" id="txtModalDetalleImporte" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format(this);" value='0.00' disabled />
                                </div>
                            </div>
                        </div>



                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-6 col-md-offset-6" style="text-align:right;"> 
                        <button class="btn btn-w-m btn-success col-md-4 col-md-offset-2" name="btnModalDetalleAgregar" id="btnModalDetalleAgregar" title='Agregar item'><i class="fa fa-floppy-o" aria-hidden="true" fa-3x></i>&nbsp; Agregar item</button> 

                        <button type="button" class="btn btn-success col-md-4 pull-right" data-dismiss="modal">Cerrar</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--MODAL INFO FACTURA--%>
<div class="modal fade" id="modalInfoFactura" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">INFORMACI&O&Oacute;N FACTURA</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label m-r">Respuesta de SUNAT:</label>
                        <div class="col-md-9">
                            <textarea  readonly="readonly" class="form-control text-uppercase" name="txtResponse" id="txtResponse"style="resize: none" ></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-6 col-md-offset-6" style="text-align:right;"> 
                        <button type="button" class="btn btn-success col-md-4 pull-right" data-dismiss="modal">Cerrar</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ---------end js -------------->


<script src="<c:url value="/resources/js/jsContabilidad/jsNotaCredito/jsNotaCredito.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsContabilidad/jsNotaCredito/jsNotaCreditoTable.js"/>" type="text/javascript"></script>


<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
