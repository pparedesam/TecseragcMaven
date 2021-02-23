
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>
<!-- ---------end js -------------->

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align: left">
        <legend class="text-success"><b>GU&Iacute;A DE REMISI&Oacute;N ELECTR&Oacute;NICA</b></legend>
    </div>
</div>

<div class="row" >
    <div class="col-md-10 col-md-offset-1 well" style="background-color: #FFF;">
        <ul class="nav nav-tabs" id="tabGuia">
            <li role="presentation" class="active"><a href="#Busqueda">B&Uacute;SQUEDA DE GU&Iacute;A</a>
            </li>
            <li role="presentation"><a href="#DetalleGuia">GU&Iacute;A DE REMISI&Oacute;N</a>


            </li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="Busqueda">
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-2">
                            <select class='simple-select form-control' data-style='btn-default' name='cboBusqueda' id='cboBusqueda'>
                                <option value="1">NRO GU&Iacute;A</option>
                                <option value="2">FECHA</option>
                            </select>                
                        </div>
                        <div class="col-md-8" id="busquedaNroGuia">
                            <input type="text" class="form-control text-uppercase" name="txtNroGuia" id="txtNroGuia" autocomplete="off" maxlength="50" style="display: block;"/>
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
                            <button type="submit" class="btn btn-w-m btn-success" name="btnBuscarGuia" id="btnBuscarGuia"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblGuiaRemision" name="tblGuiaRemision">
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
            <div role="tabpanel" class="tab-pane" id="DetalleGuia">
              
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
                                                <button class="btn btn-success col-md-12" name="btnEmisor" id="btnEmisor" title="Agregar/modificar emisor" data-target="#modalEmisor" data-toggle="modal"><i class="fa fa-file-text" aria-hidden="true" ></i>&nbsp; Emisor</button> 
                                            </div>
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h3 class="panel-title"><label>Detalle de partida:</label></h3>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-1 control-label" for="cboPartidaPais" style="text-align: right;">Pa&iacute;s: </label>
                                            <div class="col-md-2">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboPartidaPais' id='cboPartidaPais'>
                                                </select>                
                                            </div>
                                            <label class="col-md-2 control-label" for="cboPartidaDepartamento" style="text-align: right;">Departamento: </label>
                                            <div class="col-md-3">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboPartidaDepartamento' id='cboPartidaDepartamento'>
                                                </select>                
                                            </div>
                                            <label class="col-md-1 control-label" for="cboPartidaProvincia" style="text-align: right;">Provincia: </label>
                                            <div class="col-md-3">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboPartidaProvincia' id='cboPartidaProvincia'>
                                                </select>                
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-1 control-label" for="cboPartidaDistrito" style="text-align: right;">Distrito: </label>
                                            <div class="col-md-2">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboPartidaDistrito' id='cboPartidaDistrito'>
                                                </select>                
                                            </div>
                                            <label class="col-md-2 control-label" for="txtPuntoPartida" style="text-align: right;">Punto de partida: </label>
                                            <div class="col-md-7">
                                                <input type="text" class="form-control text-uppercase" name="txtPuntoPartida" id="txtPuntoPartida" autocomplete="off" maxlength="50" style="display: block;"/>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" id="rowDestinatario">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><label>DESTINATARIO</label></h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 ">
                                            <label class="col-md-1 control-label" for="txtDestinatarioRUC" style="text-align: right;">RUC: </label>
                                            <div class="col-md-2">
                                                <input type="text" class="form-control text-uppercase" name="txtDestinatarioRUC" id="txtDestinatarioRUC" autocomplete="off" disabled />
                                            </div>
                                            <label class="col-md-1 control-label" for="txtDestinatarioNombres" style="text-align: right;">Señor(es): </label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control text-uppercase" name="txtDestinatarioNombres" id="txtDestinatarioNombres" autocomplete="off" disabled />
                                            </div>
                                            <div class="col-md-2 pull-right">
                                                <button class="btn btn-success col-md-12" name="btnDestinatario" id="btnDestinatario" title="Agregar/modificar destinatario" data-target="#modalDestinatario" data-toggle="modal"><i class="fa fa-file-text" aria-hidden="true" ></i>&nbsp; Destinatario</button> 
                                            </div>
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h3 class="panel-title"><label>Detalle de llegada:</label></h3>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-1 control-label" for="cboLlegadaPais" style="text-align: right;">Pa&iacute;s: </label>
                                            <div class="col-md-2">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboLlegadaPais' id='cboLlegadaPais'>
                                                </select>                
                                            </div>
                                            <label class="col-md-2 control-label" for="cboLlegadaDepartamento" style="text-align: right;">Departamento: </label>
                                            <div class="col-md-3">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboLlegadaDepartamento' id='cboLlegadaDepartamento'>
                                                </select>                
                                            </div>
                                            <label class="col-md-1 control-label" for="cboLlegadaProvincia" style="text-align: right;">Provincia: </label>
                                            <div class="col-md-3">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboLlegadaProvincia' id='cboLlegadaProvincia'>
                                                </select>                
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-1 control-label" for="cboLlegadaDistrito" style="text-align: right;">Distrito: </label>
                                            <div class="col-md-2">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboLlegadaDistrito' id='cboLlegadaDistrito'>
                                                </select>                
                                            </div>
                                            <label class="col-md-2 control-label" for="txtPuntoLlegada" style="text-align: right;">Punto de llegada: </label>
                                            <div class="col-md-7">
                                                <input type="text" class="form-control text-uppercase" name="txtPuntoLlegada" id="txtPuntoLlegada" autocomplete="off" maxlength="50" style="display: block;"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" id="rowTransporteConductor">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><label>UNIDAD DE TRANSPORTE Y CONDUCTOR</label></h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-1 control-label" for="cboModalidad" style="text-align: right;">Modalidad: </label>
                                            <div class="col-md-4">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboModalidad' id='cboModalidad'>
                                                </select>                
                                            </div>
                                            <label class="col-md-1 control-label" for="txtMarcaNroPlaca" style="text-align: right;">Marca - Placa: </label>
                                            <div class="col-md-6">
                                                <input type="text" class="form-control text-uppercase" name="txtMarcaNroPlaca" id="txtMarcaNroPlaca" autocomplete="off"/>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row"id="transporte2">
                                        <div class="col-md-12">
                                            <div class="col-md-1">
                                                <h3 class="panel-title"><label>Conductor:</label></h3>
                                            </div>
                                            <label class="col-md-2 control-label" for="txtConductorNroDoc" style="text-align: right;">Nro Documento: </label>
                                            <div class="col-md-2">
                                                <input type="text" class="form-control text-uppercase" name="txtConductorNroDoc" id="txtConductorNroDoc" autocomplete="off" disabled/>
                                            </div>
                                            <label class="col-md-1 control-label" for="txtConductorNombres" style="text-align: right;">Nombres: </label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control text-uppercase" name="txtConductorNombres" id="txtConductorNombres" autocomplete="off" disabled/>

                                            </div>
                                            <div class="col-md-2 pull-right">
                                                <button class="btn btn-success col-md-12" name="btnConductor" id="btnConductor" title="Agregar/modificar conductor" data-target="#modalConductor" data-toggle="modal"><i class="fa fa-file-text" aria-hidden="true" ></i>&nbsp; Conductor</button> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" id="transporte1" >
                                        <div class="col-md-12">
                                            <div class="col-md-1">
                                                <h3 class="panel-title"><label>Transportista:</label></h3>
                                            </div>
                                            <label class="col-md-2 control-label" for="txtTransportistaNroDoc" style="text-align: right;" >Nro Documento: </label>
                                            <div class="col-md-2">
                                                <input type="text" class="form-control text-uppercase" name="txtTransportistaNroDoc" id="txtTransportistaNroDoc" autocomplete="off" disabled/>
                                            </div>
                                            <label class="col-md-1 control-label" for="txtTransportistaNombres" style="text-align: right;">Señor(es): </label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control text-uppercase" name="txtTransportistaNombres" id="txtTransportistaNombres" autocomplete="off" disabled/>

                                            </div>
                                            <div class="col-md-2 pull-right">
                                                <button class="btn btn-success col-md-12" name="btnTransportista" id="btnTransportista" title="Agregar/modificar transportista" data-target="#modalTransportista" data-toggle="modal"><i class="fa fa-file-text" aria-hidden="true" ></i>&nbsp; Transportista</button> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" id="rowDatosTraslado">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><label>DATOS TRASLADO</label></h3>
                                </div>
                                <div class="panel-body">

                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-2 control-label" for="txtTrasladoFecha" style="text-align: right;">Fecha: </label>
                                            <div class="col-md-4" style="padding-right:15px">
                                                <div class="input-group date">
                                                    <input type="text" class="form-control" id="txtTrasladoFecha" name="txtTrasladoFecha" readonly>
                                                    <div class="input-group-addon">
                                                        <span class="glyphicon glyphicon-th"></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <label class="col-md-2 control-label" for="txtTrasladoFechaTraslado" style="text-align: right;">Fecha traslado: </label>
                                            <div class="col-md-4" style="padding-right:15px">
                                                <div class="input-group date">
                                                    <input type="text" class="form-control" id="txtTrasladoFechaTraslado" name="txtTrasladoFechaTraslado" readonly>
                                                    <div class="input-group-addon">
                                                        <span class="glyphicon glyphicon-th"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div id="divTablaEmisor" class="col-md-12"> 
                                                <table class="table table-striped table-bordered table-hover" id="tblDetalle" name="tblDetalle">
                                                    <thead>
                                                        <tr>
                                                            <th class='text-center'>DESCRIPCI&Oacute;N</th>
                                                            <th class='text-center'>CANTIDAD</th>
                                                            <th class='text-center'>UNIDAD DE MEDIAD</th>
                                                            <th class='text-center'>ACCIONES</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>  
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12 ">
                                            <div class="col-md-2 pull-right">
                                                <button class="btn btn-success col-md-12" name="btnAgregarDetalle" id="btnAgregarDetalle" title="Agregar detalle" data-target="#modalDetalle" data-toggle="modal"><i class="fa fa-file-text" aria-hidden="true" ></i>&nbsp; Detalle</button> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" id="rowMotivoTraslado">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><label>MOTIVO TRASLADO</label></h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-2 control-label" for="cboTrasladoMotivo" style="text-align: right;">Motivo: </label>
                                            <div class="col-md-4">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboTrasladoMotivo' id='cboTrasladoMotivo'>
                                                </select>                
                                            </div>

                                            <label class="col-md-2 control-label" for="txtTrasladoFechaEntregaTransportista" style="text-align: right;">Fecha de entrega al transportista: </label>
                                            <div class="col-md-4" style="padding-right:15px">
                                                <div class="input-group date">
                                                    <input type="text" class="form-control" id="txtTrasladoFechaEntregaTransportista" name="txtTrasladoFechaEntregaTransportista" readonly>
                                                    <div class="input-group-addon">
                                                        <span class="glyphicon glyphicon-th"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-2 control-label" for="cboTrasladoUnidadMedida" style="text-align: right;">Unidad de medida: </label>
                                            <div class="col-md-4">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboTrasladoUnidadMedida' id='cboTrasladoUnidadMedida'>
                                                </select>                
                                            </div>

                                            <label class="col-md-2 control-label" for="txtTrasladoPesoBruto" style="text-align: right;">Peso bruto: </label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control text-uppercase" name="txtTrasladoPesoBruto" id="txtTrasladoPesoBruto" autocomplete="off"/>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="col-md-2 control-label" for="txtObservacion" style="text-align: right;">Observaciones: </label>
                                            <div class="col-md-10">
                                                <textarea class="form-control text-uppercase" id="txtObservacion"  name="txtObservacion" rows="7" style="resize: none;" maxlength="1000"></textarea>
                                            </div>
                                        </div>
                                    </div> 
                                </div>
                            </div>

                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-success col-md-2 col-md-offset-5" name="btnRegistrarGuia" id="btnRegistrarGuia" title="Registrar gu&iacute;a"><i class="fa fa-save" aria-hidden="true" ></i>&nbsp; Registrar</button> 
                        </div>
                    </div>
               
            </div>

        </div>
    </div>
</div>
</div>

<%--MODAL EMISOR--%>
<div class="modal fade" id="modalEmisor" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR EMISOR</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalEmisorBusqueda' id='cboModalEmisorBusqueda'>
                                <option value="1">RUC</option>
                                <option value="2">RAZON SOCIAL</option>
                            </select>                
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control text-uppercase" name="txtModalEmisorCriterio" id="txtModalEmisorCriterio" autocomplete="off" maxlength="50" />
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-w-m btn-success" name="btnEmisorBuscar" id="btnEmisorBuscar"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalEmisor" name="tblModalEmisor">
                            <thead>
                                <tr>
                                    <th>RUC</th>
                                    <th>RAZON SOCIAL</th>
                                    <th>ACCIONES</th>
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

<%--MODAL DESTINATARIO--%>
<div class="modal fade" id="modalDestinatario" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR DESTINATARIO</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalDestinatarioBusqueda' id='cboModalDestinatarioBusqueda'>
                                <option value="1">RUC</option>
                                <option value="2">RAZON SOCIAL</option>
                            </select>                
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control text-uppercase" name="txtModalDestinatarioCriterio" id="txtModalDestinatarioCriterio" autocomplete="off" maxlength="50" />
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-w-m btn-success" name="btnModalDestinatarioBuscar" id="btnModalDestinatarioBuscar"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalDestinatario" name="tblModalDestinatario">
                            <thead>
                                <tr>
                                    <th>RUC</th>
                                    <th>RAZON SOCIAL</th>
                                    <th>ACCIONES</th>
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

<%--MODAL CONDUCTOR--%>
<div class="modal fade" id="modalConductor" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR CONDUCTOR</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalConductorBusqueda' id='cboModalConductorBusqueda'>
                                <option value="1">DNI</option>
                                <option value="2">NOMBRES</option>
                            </select>                
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control text-uppercase" name="txtModalConductorCriterio" id="txtModalConductorCriterio" autocomplete="off" maxlength="50" />
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-w-m btn-success" name="btnBuscarConductor" id="btnBuscarConductor"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalConductor" name="tblModalConductor">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>NOMBRES</th>
                                    <th>ACCIONES</th>
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

<%--MODAL TRANSPORTISTA--%>
<div class="modal fade" id="modalTransportista" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR TRANSPORTISTA</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalTransportistaBusqueda' id='cboModalTransportistaBusqueda'>
                                <option value="1">DNI/RUC</option>
                                <option value="2">NOMBRES</option>
                            </select>                
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control text-uppercase" name="txtModalTransportistaCriterio" id="txtModalTransportistaCriterio" autocomplete="off" maxlength="50" />
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-w-m btn-success" name="btnBuscarTransportista" id="btnBuscarTransportista"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalTransportista" name="tblModalTransportista">
                            <thead>
                                <tr>
                                    <th>DNI/RUC</th>
                                    <th>NOMBRES</th>
                                    <th>ACCIONES</th>
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

<%--MODAL DETALLE GUIA--%>
<div class="modal fade" id="modalDetalle" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">DETALLE GU&Iacute;A</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-2 control-label" for="cboModalDetalleUnidadMedida" style="text-align: right;">Unidad de medida:</label>
                        <div class="col-md-4">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalDetalleUnidadMedida' id='cboModalDetalleUnidadMedida'>
                            </select>                
                        </div>
                        <label class="col-md-2 control-label" for="txtModalDetalleCantidad" style="text-align: right;">Cantidad:</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control text-uppercase" name="txtModalDetalleCantidad" id="txtModalDetalleCantidad" autocomplete="off" maxlength="50" />
                        </div>
                    </div>
                </div>
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-2 control-label" for="txtModalDetalleDescripcion" style="text-align: right;">Descripci&oacute;n:</label>
                        <div class="col-md-10">
                            <textarea class="form-control text-uppercase" id="txtModalDetalleDescripcion"  name="txtModalDetalleDescripcion" rows="2" style="resize: none;" maxlength="1000"></textarea>
                        </div>
                    </div>
                </div>

            </div>

            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-6 col-md-offset-6" style="text-align:right;"> 
                        <button class="btn btn-w-m btn-success col-md-7" name="btnModalAgregarDetalle" id="btnModalAgregarDetalle" title='Agregar detalle'><i class="fa fa-floppy-o" aria-hidden="true" fa-3x></i>&nbsp; Agregar detalle</button> 

                        <button type="button" class="btn btn-success col-md-4 pull-right" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<script src="<c:url value="/resources/js/jsLogistica/jsGuiaRemision/jsGuiaRemision.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsLogistica/jsGuiaRemision/jsGuiaRemisionTable.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionLogistica/jsValidacionGuiaRemision.js"/>" type="text/javascript"></script>

<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
