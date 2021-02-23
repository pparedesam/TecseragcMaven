
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>
<!-- ---------end js -------------->

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align: left">
        <legend class="text-success"><b>CHEQUES</b></legend>
    </div>
</div>

<div class="row" >
    <div class="col-md-10 col-md-offset-1 well" style="background-color: #FFF;">
        <ul class="nav nav-tabs" id="tabCheques">
            <li role="presentation" class="active"><a href="#Busqueda">B&Uacute;SQUEDA DE CHEQUES</a>
            </li>
            <li role="presentation"><a href="#NuevoCheque">NUEVO CHEQUE</a>


            </li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="Busqueda">
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-2">
                            <select class='simple-select form-control' data-style='btn-default' name='cboBusqueda' id='cboBusqueda'>
                                <option value="1">NRO CHEQUE</option>
                                <option value="2">FECHA</option>
                            </select>                
                        </div>
                        <div class="col-md-8" id="busquedaNroCheque" style="display: block;">
                            <input type="text" class="form-control text-uppercase" name="txtBusNroCheque" id="txtBusNroCheque" autocomplete="off" maxlength="50" />
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
                            <button type="submit" class="btn btn-w-m btn-success col-md-12" name="btnBuscarCheques" id="btnBuscarCheques"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaCheques" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblCheques" name="tblCheques">
                            <thead>
                                <tr>
                                    <th class='text-center' data-priority="1">Nro Cheque</th>
                                    <th class='text-center' data-priority="1">Fecha</th>
                                    <th class='text-center' data-priority="1">Nombres</th>
                                    <th class='text-center' data-priority="1">Moneda</th>
                                    <th class='text-center' data-priority="1">Monto</th>
                                    <th class='text-center' data-priority="2">Detalle</th>
                                    <th class='text-center' data-priority="1">Estado</th>
                                    <th class='text-center' data-priority="1">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="NuevoCheque">

                <br>
                <div class="row" id="rowCheque">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><label>DATOS DEL CHEQUE</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <h3 class="panel-title"><label>Titular:</label></h3>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <label class="col-md-1 control-label" for="txtTitNroDoc" style="text-align: right;">Nro documento: </label>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control text-uppercase" name="txtTitNroDoc" id="txtTitNroDoc" autocomplete="off" disabled />
                                        </div>
                                        <label class="col-md-1 control-label" for="txtTitNombres" style="text-align: right;">Señor (es): </label>
                                        <div class="col-md-6">
                                            <input type="text" class="form-control text-uppercase" name="txtTitNombres" id="txtTitNombres" autocomplete="off" disabled />
                                        </div>
                                        <div class="col-md-2 pull-right">
                                            <button class="btn btn-success col-md-12" name="btnTitular" id="btnTitular" title="Agregar/modificar titular" data-target="#modalTitular" data-toggle="modal"><i class="fa fa-file-text" aria-hidden="true" ></i>&nbsp; Titular</button> 
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-md-12">
                                        <h3 class="panel-title"><label>Detalle de cheque:</label></h3>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label class="col-md-1 control-label" for="txtChequeFecha" style="text-align: right;">Fecha: </label>
                                        <div class="col-md-2" style="padding-right:15px">
                                            <div class="input-group date">
                                                <input type="text" class="form-control" id="txtChequeFecha" name="txtChequeFecha" readonly>
                                                <div class="input-group-addon">
                                                    <span class="glyphicon glyphicon-th"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <label class="col-md-1 control-label" for="cboChequeMoneda" style="text-align: right;">Moneda:</label>
                                        <div class="col-md-4">
                                            <select class='simple-select form-control' data-style='btn-default' name='cboChequeMoneda' id='cboChequeMoneda'>
                                            </select>                
                                        </div>
                                        <label class="col-md-1 control-label" for="txtChequeNro" style="text-align: right;">Nro Cheque: </label>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control text-uppercase" name="txtChequeNro" id="txtChequeNro" autocomplete="off" onkeypress="return soloNumero(event);" onblur="soloNumero(this);"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><label>CUENTAS BANCARIAS:</label></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <label class="col-md-1 control-label" for="cboChequeBanco" style="text-align: right;">Banco:</label>
                                        <div class="col-md-2">
                                            <select class='simple-select form-control' data-style='btn-default' name='cboChequeBanco' id='cboChequeBanco'>
                                            </select>                
                                        </div>
                                        <label class="col-md-1 control-label" for="cboChequeCuenta" style="text-align: right;">Cuenta:</label>
                                        <div class="col-md-4">
                                            <select class='simple-select form-control' data-style='btn-default' name='cboChequeCuenta' id='cboChequeCuenta'>
                                            </select>                
                                        </div>
                                        <label class="col-md-1 control-label" for="txtChequeMonto" style="text-align: right;">Monto: </label>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control text-uppercase" name="txtChequeMonto" id="txtChequeMonto" autocomplete="off" onkeypress="return numeroDosDecimales(event, this);" onblur="format(this);" />
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-md-12 ">

                                        <label class="col-md-1 control-label" for="txtChequeDetalle" style="text-align: right;">Detalle: </label>
                                        <div class="col-md-11">
                                            <textarea class="form-control text-uppercase" id="txtChequeDetalle"  name="txtChequeDetalle" rows="7" style="resize: none;" maxlength="1000"></textarea>
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
                        <button type="submit" class="btn btn-success col-md-2 col-md-offset-5" name="btnRegistrarCheque" id="btnRegistrarCheque" title="Registrar cheque"><i class="fa fa-save" aria-hidden="true" ></i>&nbsp; Registrar</button> 
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
</div>

<%--MODAL ANUL--%>
<div class="modal fade" id="modalChequeAnular" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">ANULAR CHEQUE</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-1 control-label" for="txtModalAnularMotivo" style="text-align: right;">Motivo: </label>
                        <div class="col-md-11">
                            <input type="text" class="form-control text-uppercase" name="txtModalAnularMotivo" id="txtModalAnularMotivo" autocomplete="off" maxlength="50" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-6 col-md-offset-6" style="text-align:right;"> 
                        <button type="button" class="btn btn-danger col-md-4 " name="btnAnularCheque" id="btnAnularCheque" title="Anular cheque"><i class="fa fa-save" aria-hidden="true" ></i>&nbsp; Anular</button> 
                   
                        <button type="button" class="btn btn-success col-md-4 pull-right" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--MODAL TITULAR--%>
<div class="modal fade" id="modalTitular" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR TITULAR</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalEmisorBusqueda' id='cboModalEmisorBusqueda'>
                                <option value="1">NRO DOCUMENTO</option>
                                <option value="2">NOMBRE</option>
                            </select>                
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control text-uppercase" name="txtModalTitularCriterio" id="txtModalTitularCriterio" autocomplete="off" maxlength="50" />
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-w-m btn-success" name="btnTitularBuscar" id="btnTitularBuscar"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalTitular" name="tblModalTitular">
                            <thead>
                                <tr>
                                    <th>NRO DOCUMENTO</th>
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


<%--MODAL IMPRMIR--%>
<div class="modal fade" id="modalImprimir" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">IMPRIMIR CHEQUE</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="col-md-12">
                        <select class='simple-select form-control' data-style='btn-default' name='cboImpresoras' id='cboImpresoras'></select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="100%" height="100%" viewBox="0 0 1819 862">
                        <defs>
                        <style>
                            .cls-1, .cls-10, .cls-4, .cls-5, .cls-6, .cls-8, .cls-9 {
                                fill: #170847;
                            }

                            .cls-2 {
                                fill: #fff;
                            }

                            .cls-10, .cls-2, .cls-9 {
                                fill-rule: evenodd;
                            }

                            .cls-3, .cls-4 {
                                font-size: 27.083px;
                            }

                            .cls-11, .cls-4, .cls-5, .cls-6, .cls-8 {
                                font-family: Arial;
                            }

                            .cls-5 {
                                font-size: 50px;
                                font-weight: 800;
                            }

                            .cls-6 {
                                font-size: 25px;
                            }

                            .cls-7 {
                                font-size: 20.833px;
                            }

                            .cls-8 {
                                font-size: 29.167px;
                            }

                            .cls-9 {
                                opacity: 0.3;
                            }

                            .cls-11 {
                                font-size: 25px;
                            }
                        </style>

                        </defs>
                        <image id="Fondo" width="1819" height="862" xlink:href="data:img/png;base64,iVBORw0KGgoAAAANSUhEUgAABxsAAANeCAYAAADKkmCUAAAfL0lEQVR4nOzZMQHAMADDsHX8OacY6luC4Ndn2z4AAAAAAACAR79gAAAAAAAAQGE2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAAAAkJiNAAAAAAAAQGI2AgAAAAAAAInZCAAAAAAAACRmIwAAAAAAAJCYjQAAAAAAAEBiNgIAAAAAAACJ2QgAAAAAAAAkZiMAAAAAAACQmI0AAAAAAABAYjYCAAAAAAAAidkIAAAAAAAAJGYjAAAAAMBtz44JAABgGAbVv+ppWG6wAQCQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAAIJGNAAAAAAAAQCIbAQAAAAAAgEQ2AgAAAAAAAIlsBAAAAAAAABLZCAAAAAAAACSyEQAAAAAAAEhkIwAAAAAAAJDIRgAAAAAAACCRjQAAAAAAAEAiGwEAAAAAAIBENgIAAAAAAACJbAQAAAAAAAAS2QgAAAAAAAAkshEAAAAAAABIZCMAAAAAAACQyEYAAAAAAAAgkY0AAAAAAABAIhsBAAAAAACARDYCAAAAAAAAiWwEAAAAAAAAEtkIAAAAAAAAJLIRAAAAAAAASGQjAAAAAAAAkMhGAAAAAAAA4G/bAXH8Crge1/z0AAAAAElFTkSuQmCC"/>
                        <rect id="Rectángulo_1" data-name="Rectángulo 1" class="cls-1" x="1257" y="172" width="505" height="63"/>
                        <path id="Forma_3_copia" data-name="Forma 3 copia" class="cls-2" d="M1299.34,161H1306v88h-6.66V161Zm14-1H1320v88h-6.66V160Zm23,0H1343v88h-6.66V160Zm13,0H1356v88h-6.66V160Zm238,0H1594v88h-6.66V160Zm14,1H1608v88h-6.66V161Zm14,0H1622v88h-6.66V161Zm-254-1h4.32v88h-4.32V160Zm10,0h4.32v88h-4.32V160Zm15,1h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm15,0h4.32v88h-4.32V161Zm10,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm12,0h4.32v88h-4.32V161Zm10,0h4.32v88h-4.32V161Zm15,0h4.32v88h-4.32V161Zm11,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm11,0h4.32v88h-4.32V161Zm15,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm89,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm11,0h4.32v88h-4.32V161Zm11,0h4.32v88h-4.32V161Zm10,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm14,0h4.32v88h-4.32V161Zm11,0h4.32v88h-4.32V161Zm11,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm9,0h4.32v88h-4.32V161Zm10,0h4.32v88h-4.32V161Zm12,0h4.32v88h-4.32V161Zm-206,0h4.32v88h-4.32V161Zm12-1h4.32v88h-4.32V160Zm15,0h4.32v88h-4.32V160Zm-316-13h4.32v88h-4.32V147Zm65,13h4.32v88h-4.32V160Zm-42,0H1289v88h-6.66V160Zm-14,0H1275v88h-6.66V160Z"/>
                        <text class="cls-3"><textPath xlink:href="#text-path"><tspan class="cls-4">no escribir ni firmar debajo de esta línea</tspan></textPath></text>
                        <text id="XXXXXXXX_XXX_XXX_XXXXXXXXXX_" data-name="XXXXXXXX    XXX  XXX    XXXXXXXXXX " class="cls-5" x="107.274" y="799.733">XXXXXXXX    XXX  XXX    XXXXXXXXXX </text>
                        <text id="pague_a_" data-name="pague a " class="cls-6" x="60.503" y="311.123">pague a<tspan class="cls-7"> </tspan></text>
                        <text id="la_orden_de:" data-name="la orden de:" class="cls-8" x="58.121" y="344.771">la orden de:</text>
                        <text id="COMUNIDAD_CAMPESINA" data-name="COMUNIDAD CAMPESINA" class="cls-8" x="82.891" y="512.166">COMUNIDAD CAMPESINA</text>
                        <text id="RUC_20315295573" data-name="RUC   20315295573" class="cls-8" x="641.627" y="512.075">RUC   20315295573</text>
                        <text id="LLACUABAMBA_CC" data-name="LLACUABAMBA CC" class="cls-8" x="84.389" y="540.737">LLACUABAMBA CC</text>
                        <text id="Soles" class="cls-8" x="1695.232" y="445.162">Soles</text>
                        <text id="Día" class="cls-6" x="849.478" y="140.294">Día</text>
                        <text id="Mes" class="cls-6" x="924.479" y="140.294">Mes</text>
                        <text id="Año" class="cls-6" x="1004.479" y="140.294">Año</text>
                        <image id="Forma_2" data-name="Forma 2" y="45" width="1819" height="663" xlink:href="data:img/png;base64,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RGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAiv9peAAAAi9JREFUIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACARGwEAAAAAAIBEbAQAAAAAAAASsREAAAAAAABIxEYAAAAAAAAgERsBAAAAAACARGwEAAAAAAAAErERAAAAAAAASMRGAAAAAAAAIBEbAQAAAAAAgERsBAAAAAAAABKxEQAAAAAAAEjERgAAAAAAACBZHh9eno0OAAAAAAAA2GsdY7ybGgAAAAAAALCXZ1QBAAAAAACARGwEAAAAAAAAErERAAAAAAAASP7+bPw2OgAAAAAAAGCXMcYvXbqC9SHiHzwAAAAASUVORK5CYII="/>
                        <path id="Rectángulo_2" data-name="Rectángulo 2" class="cls-9" d="M1257,45h505v94.27H1257V45Zm0,0h505v94.27H1257V45Zm0,0h505v94.27H1257V45Z"/>
                        <path id="Forma_3" data-name="Forma 3" class="cls-10" d="M915,602.424v-7.032h841.19v7.032H915Z"/>
                        <path id="Rectángulo_2-2" data-name="Rectángulo 2" class="cls-9" d="M910,520h852v89.439H910V520Zm0,0h852v89.428H910V520Zm0,0h852v89.428H910V520Z"/>
                        <text id="svgDia" data-name="19" class="cls-11" x="851.39" y="96.57">19</text>
                        <text id="svgMes" data-name="12" class="cls-11" x="927.392" y="96.57">12</text>
                        <text id="svgAnio" data-name="2020" class="cls-11" x="1007.391" y="96.57">2020</text>
                        <text id="svgMonto" data-name="36,000.00" class="cls-11" x="1357.391" y="96.57">36,000.00</text>
                        <text id="svgPersona" class="cls-11" x="304.542" y="333.941" >PERSONA</text>
                        <text id="svgMontoLetras" data-name="MONTO LETRAS" class="cls-11" x="307.542" y="434.942">MONTO LETRAS</text>
                        </svg>

                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-6 col-md-offset-6" style="text-align:right;"> 
                        <button class="btn btn-w-m btn-success col-md-7" name="btnModalImprimir" id="btnModalImprimir" title='Imprimir cheque' onclick="print();"><i class="fa fa-print" aria-hidden="true" fa-3x></i>&nbsp; Imprimir</button> 

                        <button type="button" class="btn btn-success col-md-4 pull-right" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<script src="<c:url value="/resources/js/jsPrint/zip.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsPrint/zip-ext.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsPrint/deflate.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsPrint/JSPrintManager.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/js/jsPrint/bluebird.min.js"/>" type="text/javascript"></script>


<script src="<c:url value="/resources/js/jsContabilidad/jsCheques/jsCheques.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsContabilidad/jsCheques/jsChequesTable.js"/>" type="text/javascript"></script>

<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
