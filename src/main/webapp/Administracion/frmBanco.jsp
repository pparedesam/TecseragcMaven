<%@page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align: left">
        <legend class="text-success"><b>REGISTRO BANCOS</b></legend>
    </div>
</div>

<div class="row">
    <div class="col-md-10 col-md-offset-1 well tabs-container" style="background-color: #FFF;">
        <ul class="nav nav-tabs" id="tabBancos">
            <li role="presentation" class="active"><a href="#Bancos">BANCOS</a>
            </li>
            <li role="presentation"><a href="#RegBancos">REGISTRO BANCOS</a>
            </li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="Bancos">
                <br>
                <div class="row">
                    <div class="col-md-2 pull-right">
                        <button type="submit" class="btn btn-w-m btn-success col-md-12 pull-right" name="btnNuevoBanco" id="btnNuevoBanco" ><i class="fa fa-plus" aria-hidden="true" fa-3x></i>&nbsp; Nuevo</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <hr/>
                        <table class="table table-striped table-bordered table-hover" id="tblBancos" align='center'>
                            <thead>
                                <tr>
                                    <th style="width:50%">Nombre Banco</th>
                                    <th style="width:20%">Nombre Abreviado</th>
                                    <th style="width:20%">RUC</th>
                                    <th style="width:10%">Accion</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>  
                        </table>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="RegBancos">
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-12">

                                <h4 class="modal-title" >BANCO:</h4>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                                <label class="col-md-1 control-label" for="txtRUC" style="text-align: right;">RUC: </label>
                                <div class="col-md-2" >
                                    <input type="text" class="form-control" id="txtRUC" name="txtRUC" readonly>
                                </div>
                                <label class="col-md-1 control-label" for="txtRazonSocial" style="text-align: right;">Razón Social: </label>
                                <div class="col-md-3" >
                                    <input type="text" class="form-control" id="txtRazonSocial" name="txtRazonSocial" readonly>
                                </div>
                                <label class="col-md-1 control-label" for="txtAbreviatura" style="text-align: right;">Abreviatura: </label>
                                <div class="col-md-2" >
                                    <input type="text" class="form-control" id="txtAbreviatura" name="txtAbreviatura" readonly  style="text-transform: uppercase" autocomplete="off">
                                </div>
                                <div class="col-md-2 pull-right">
                                    <button type="submit" class="btn btn-w-m btn-success col-md-12 pull-right" name="btnBuscar" id="btnBuscar" data-target="#modalPJBuscar" data-toggle="modal"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; Buscar</button>
                                </div>
                            </div>
                        </div>
                        <hr/>
                        <div class="row">
                            <div class="col-md-12">
                                <h4 class="modal-title" >CUENTAS:</h4>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-11 col-md-offset-1">
                                <div class="col-md-6 ">
                                    <button type="submit" class="btn btn-w-m btn-success col-md-3 " name="btnAgregarCta" id="btnAgregarCta"  data-target="#modalCtasBanco" data-toggle="modal"><i class="fa fa-plus" aria-hidden="true" fa-3x></i>&nbsp; Agregar</button>
                                </div>
                                <table class="table table-striped table-bordered table-hover" id="tblCtaBancos" align='center'>
                                    <thead>
                                        <tr>
                                            <th style="width:15%">Tipo Cuenta</th>
                                            <th style="width:25%">Nro Cuenta Banco</th>
                                            <th style="width:15%">Moneda</th>
                                            <th style="width:15%">Fecha Apertura</th>
                                            <th style="width:10%">Acción</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>  
                                </table>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-md-6 pull-right">
                                <div class="col-md-5">
                                    <button type="submit" class="btn btn-w-m btn-success col-md-12" name="btnRegistrarBanco" id="btnRegistrarBanco"><i class="fa fa-pencil" aria-hidden="true" fa-3x></i>&nbsp; REGISTRAR</button> 
                                </div>
                                <div class="col-md-5 col-md-offset-2">
                                    <button type="submit" class="btn btn-w-m btn-danger col-md-12" name="btnCancelar" id="btnCancelar"><i class="fa fa-times" aria-hidden="true" fa-3x></i>&nbsp; CANCELAR</button> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade"  data-backdrop="static" data-keyboard="false"id="modalPJBuscar" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog" role="document" >
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">X</span>
                                </button>
                                <div class="row">
                                    <div class="col-md-12">
                                        <h2 class="modal-title" id="modalLabel">BUSCAR PERSONA JURICIDA</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <form class="form-horizontal" id="form-buscarPJ"  name="form-buscarPJ" method="post">
                                        <div class="panel-body">
                                            <div class="row" style="padding-left: 20px;padding-right: 20px;">
                                                <div class="well col-md-12" >
                                                    <div class="row">
                                                        <div class="col-md-2 col-md-offset-1">                                               
                                                            <div class="form-group">                                            
                                                                <select class="form-control simple-select" name="cboTipoBusquedaPJ" id="cboTipoBusquedaPJ">
                                                                    <option value="1" option selected="selected">RUC</option>                                           
                                                                    <option value="2">Razon Social</option> 
                                                                </select>                                   
                                                            </div>
                                                        </div>   
                                                        <div class="col-md-9 ">
                                                            <div class="form-group">
                                                                <div class="col-md-10 " >
                                                                    <input class="form-control input-sm"  name="txtNroDocPJ" id="txtNroDocPJ" type="text" autocomplete="off" style="text-transform: uppercase">
                                                                </div>
                                                                <div class="col-md-2" >
                                                                    <button class="btn btn-OceanBoatBlue btn-sm" type="submit" id="btnBuscarPJ" name="btnBuscarPJ" >Buscar</button>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="row">
                                        <br>
                                        <div class="col-md-12"> 
                                            <table class="table table-striped table-bordered table-hover" id="tblPJBuscar" name="tblPJBuscar">
                                                <thead>
                                                    <tr>
                                                        <th class='text-center' style="width:60%">Razón Social</th>
                                                        <th class='text-center'style="width:30%">RUC</th>
                                                        <th class='text-center'style="width:10%">ACCION</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="row">
                                    <div class="col-md-12">                   
                                        <button type="button" class="btn btn-success pull-right" data-dismiss="modal">Cerrar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade"  data-backdrop="static" data-keyboard="false"id="modalCtasBanco" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog modal-lg"  role="document" style="width:60%">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">X</span>
                                </button>
                                <div class="row">
                                    <div class="col-md-12">
                                        <h2 class="modal-title" id="modalLabel">CUENTAS BANCO</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="panel-body">

                                        <div class="row">
                                            <div class="col-md-12" >
                                                <label class="col-md-2 control-label" for="txtNroCtaBanco" style="text-align: right;">Nro Cuenta:</label>
                                                <div class="col-md-3">
                                                    <input class="form-control input-sm  " name="txtNroCtaBanco" id="txtNroCtaBanco" autocomplete="off" onkeypress="return soloCtas(event);" >                                                        
                                                </div>
                                                <label class="col-md-1 control-label" for="cboTipMoneda" style="text-align: right;">Moneda:</label>
                                                <div class="col-md-2">
                                                    <select class='simple-select form-control' name="cboTipMoneda" id="cboTipMoneda" >
                                                        <option value="1">SOLES</option>
                                                        <option value="2">DOLARES</option>
                                                    </select>                                                     
                                                </div>
                                                <label class="col-md-2 control-label" for="cboTipoCuenta" style="text-align: right;">Tipo Cuenta:</label>
                                                <div class="col-md-2">
                                                    <select class='simple-select form-control' name="cboTipoCuenta" id="cboTipoCuenta" >
                                                    </select>                                                     
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-md-12" >
                                                <label class="col-md-1 control-label" for="txtFechaApertura" style="text-align: right;">Fecha Apertura:</label>
                                                <div class="col-md-2" style="padding-right:15px">
                                                    <div class="input-group date">
                                                        <input type="text" class="form-control" id="txtFechaApertura" name="txtFechaApertura"  autocomplete="off">
                                                        <div class="input-group-addon">
                                                            <span class="glyphicon glyphicon-th"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="row">
                                    <div class="col-md-12">                   
                                        <button type="button" class="btn btn-success" id="btnAgregarCtaBanco">Agregar</button>
                                        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">Cerrar</button>
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

<jsp:include page="/WEB-INF/structure/footer.jsp" />
<script src="<c:url value="/resources/js/jsBanco/jsBancos.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsBanco/jsBancosTable.js" />"type="text/javascript"></script>
