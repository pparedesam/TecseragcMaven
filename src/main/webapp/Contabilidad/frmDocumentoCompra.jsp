<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>

<div class="row">
    <div class="col-md-12" style="text-align: left">
        <legend class="text-success"><b>DOCUMENTO COMPRA</b></legend>
    </div>
</div>

<div class="row" >
    <div class="col-md-102 well" style="background-color: #FFF;">
        <ul class="nav nav-tabs" id="tabFacturas">
            <li role="presentation" class="active"><a href="#Busqueda">B&Uacute;SQUEDA DE DOCUMENTOS</a></li>
            <li role="presentation"><a href="#nuevaFactura">NUEVO DOCUMENTO</a></li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="Busqueda">
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-2">
                            <select class='simple-select form-control' data-style='btn-default' name='cboBusqueda' id='cboBusqueda'>
                                <option value="1">NRO DOCUMENTO</option>
                                <option value="2">FECHA</option>
                            </select>                
                        </div>
                        <div class="col-md-8" id="busquedaNroDocumento">
                            <input type="text" class="form-control text-uppercase" name="txtBusquedaNroDocumento" id="txtBusquedaNroDocumento" autocomplete="off" maxlength="50" style="display: block;"/>
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
                            <button class="btn btn-success col-md-12" name="btnBuscarFactura" id="btnBuscarFactura" title="Buscar facturas"><i class="fa fa-search" aria-hidden="true" ></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblDocumento" name="tblFactura">
                            <thead>
                                <tr>
                                    <th>Tipo</th>
                                    <th>Nro Documento</th>
                                    <th>Emisor</th>
                                    <th>Fecha Documento</th>
                                    <th>Total Neto</th>
                                    <th>Total I.G.V.</th>
                                    <th>Total </th>
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

            <div role="tabpanel" class="tab-pane" id="nuevaFactura">
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
                                            <input type="text" class="form-control text-uppercase" name="txtEmisorRUC" id="txtEmisorRUC" autocomplete="off" disabled />
                                        </div>
                                        <label class="col-md-1 control-label" for="txtEmisorRazonSocial" style="text-align: right;">Razon Social: </label>
                                        <div class="col-md-6">
                                            <input type="text" class="form-control text-uppercase" name="txtEmisorRazonSocial" id="txtEmisorRazonSocial" autocomplete="off" disabled />
                                        </div>
                                        <div class="col-md-2 pull-right">
                                            <button class="btn btn-success col-md-12" name="btnEmisor" id="btnEmisor" title="Agregar/modificar emisor" data-target="#modalEmisor" data-toggle="modal"><i class="fa fa-edit" aria-hidden="true" ></i>&nbsp; Emisor</button> 
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <label class="col-md-1 control-label" for="txtEmisorDireccion" style="text-align: right;">Direcci&oacute;n:</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control text-uppercase" name="txtEmisorDireccion" id="txtEmisorDireccion" autocomplete="off" disabled />
                                        </div>
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
                                <h3 class="panel-title"><label>DETALLE COMPRA</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label class="col-md-1 control-label" for="cboTipoComprobante" style="text-align: right;">Tipo Comprobante:</label>
                                        <div class="col-md-2">
                                            <select class='simple-select form-control' data-style='btn-default' name='cboTipoComprobante' id='cboTipoComprobante'>
                                                
                                            </select>                
                                        </div>
                                        <label class="col-md-1 control-label" for="txtNroComprobante" style="text-align: right;">Nro comprobante:</label>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control text-uppercase" name="txtNroComprobante" id="txtNroComprobante" autocomplete="off" />
                                        </div>
                                        <label class="col-md-1 control-label" for="txtFechaDoc" style="text-align: right;">Fecha: </label>
                                        <div class="col-md-2" style="padding-right:15px">
                                            <div class="input-group date">
                                                <input type="text" class="form-control" id="txtFechaDoc" name="txtFechaDoc" readonly>
                                                <div class="input-group-addon">
                                                    <span class="glyphicon glyphicon-th"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <label class="col-md-1 control-label" for="cboMoneda" style="text-align: right;">Moneda:</label>
                                        <div class="col-md-2">
                                            <select class='simple-select form-control' data-style='btn-default' name='cboMoneda' id='cboMoneda'>
                                                <option value="1">SOLES</option>
                                                <option value="2">DOLARES</option>
                                            </select>                
                                        </div>
                                    </div>
                                </div>
                                <br>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-2 pull-right">
                                            <button class="btn btn-success col-md-12 " name="btnAgregarItem" id="btnAgregarItem" title="Agregar item" data-target="#modalDetalle" data-toggle="modal"><i class="fa fa-plus" aria-hidden="true" ></i>&nbsp; Agregar item</button> 
                                        </div>
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
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-2 col-md-offset-5">
                                            <button class="btn btn-success col-md-12" name="btnRegistrarFactura" id="btnRegistrarFactura" title="Registrar factura"><i class="fa fa-save" aria-hidden="true" ></i>&nbsp; Registrar factura</button> 
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</div>

<%--MODAL EMISOR--%>
<div class="modal fade" id="modalEmisor" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR CLIENTE</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-3">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalClienteBusqueda' id='cboModalClienteBusqueda'>
                                <option value="1">RUC</option>
                                <option value="2">RAZON SOCIAL</option>
                            </select>                
                        </div>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-uppercase" name="txtModalClienteCriterio" id="txtModalClienteCriterio" autocomplete="off" maxlength="50" />
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success col-md-12" name="btnModalClienteBuscar" id="btnModalClienteBuscar" title="Buscar cliente"><i class="fa fa-search" aria-hidden="true" ></i>&nbsp; Buscar</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalEmisor" name="tblModalEmisor">
                            <thead>
                                <tr>
                                    <th class='text-center'>RUC</th>
                                    <th class='text-center'>RAZON SOCIAL</th>
                                    <th class='text-center'>DIRECCI&Oacute;N</th>
                                    <th class='text-center'>ACCIONES</th>
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
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleCant" id="txtModalDetalleCant" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format(this);" value='1'/>
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
                                <label class="col-md-2 control-label" for="txtModalDetalleIGV" style="text-align: right;">I.G.V:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleIGV" id="txtModalDetalleIGV" autocomplete="off" value='0.00' disabled/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row" >
                            <div class="col-md-12">
                                <label class="col-md-2 control-label" for="txtModalDetalleImporte" style="text-align: right;">Importe:</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control text-uppercase text-right" name="txtModalDetalleImporte" id="txtModalDetalleImporte" autocomplete="off" value='0.00' disabled/>
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

<!-- ---------end js -------------->


<script src="<c:url value="/resources/js/jsContabilidad/jsDocCompra/jsDocCompra.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsContabilidad/jsDocCompra/jsDocCompraTable.js"/>" type="text/javascript"></script>


<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
