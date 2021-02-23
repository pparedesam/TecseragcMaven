
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align: left">
        <legend class="text-success"><b>RECIBOS DE CAJA</b></legend>
    </div>
</div>

<div class="row">
    <div class="col-md-10 col-md-offset-1 well tabs-container" style="background-color: #FFF;">
        <ul class="nav nav-tabs" id="tabRecibos">
            <li role="presentation" class="active"><a href="#BusquedaRecibo" id="tabBusqueda">BUSQUEDA RECIBO</a>
            </li>
            <li role="presentation"><a href="#ReciboIngresoEgreso" id="tabNuevoRecibo">NUEVO RECIBO INGRESO/EGRESO</a>
            </li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="BusquedaRecibo">
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-2">
                            <select class='simple-select form-control' data-style='btn-default' name='cboBusqueda' id='cboBusqueda'>
                                <option value="2">NRO RECIBO</option>
                                <option value="1">FECHA</option>
                            </select>                
                        </div>
                        <div class="col-md-8" id="busquedaNroFactura">
                            <input type="text" class="form-control text-uppercase" name="txtBusquedaNroRecibo" id="txtBusquedaNroRecibo" autocomplete="off" maxlength="50" style="display: block;"/>
                        </div>
                        <div class="col-md-8" id="busquedaFechas" style="display: none;">
                            <label class="col-md-2 control-label" for="txtFecInicio" style="text-align: right;">Fecha Inicio:</label>
                            <div class="col-md-4" style="padding-right:15px">
                                <div class="input-group date" id="txtFechaInicio" name="txtFechaInicio">
                                    <input type="text" class="form-control text-center fechaInicio" readonly>
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>
                            </div>

                            <label class="col-md-2 control-label" for="txtFecFin" style="text-align: right;">Fecha Fin:</label>
                            <div class="col-md-4" style="padding-right:15px">
                                <div class="input-group date" id="txtFechaFin" name="txtFechaFin">
                                    <input type="text" class="form-control text-center fechaFin"  readonly>
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success col-md-12" name="btnBuscarRecibo" id="btnBuscarRecibo" title="Buscar recibo"><i class="fa fa-search" aria-hidden="true" ></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-md-12"> 
                        
                            <div class="col-md-6">
                                <h2 id="saldoCaja">Saldo caja: 0.00</h2>
                            </div>
                        
                        <table class="table table-striped table-bordered table-hover" id="tblRecibos" name="tblRecibos">
                            <thead>
                                <tr>
                                    <th>Tipo</th>
                                    <th>Nro Recibo</th>
                                    <th>Ejecutor</th>
                                    <th>Fecha Documento</th>
                                    <th>Monto</th>
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
            <div role="tabpanel" class="tab-pane" id="ReciboIngresoEgreso">
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-2 control-label" for="cboTipoOperacion" style="text-align: right;">Tipo Recibo:</label>
                        <div class="col-md-3">
                            <select class='simple-select form-control' name="cboTipoOperacion" id="cboTipoOperacion" >
                            </select>                                                     
                        </div>

                        <label class="col-md-1 control-label col-md-offset-3" for="txtFecCierre" style="text-align: right;">Fecha:</label>
                        <div class="col-md-3" style="padding-right:15px">
                            <div class="input-group date" id="txtFecha" name="txtFecha">
                                <input type="text" class="form-control text-center fechaReporte" id="fechaReporte" name="fechaReporte" readonly>
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-2 control-label" for="txtReciboPersona" style="text-align: right;">YO:</label>
                        <div class="col-md-8">
                            <input class="form-control input-sm  " name="txtReciboPersona" id="txtReciboPersona" autocomplete="off" disabled>                                                        
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-success col-md-12" name="btnModalPersona" id="btnModalPersona" title="Buscar persona"><i class="fa fa-search" aria-hidden="true" ></i>&nbsp; Buscar</button> 
                        </div>
                    </div>
                </div>
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-2 control-label" for="txtReciboDNI" style="text-align: right;">DNI N°:</label>
                        <div class="col-md-4">
                            <input class="form-control input-sm  " name="txtReciboDNI" id="txtReciboDNI" autocomplete="off" disabled >                                                        
                        </div>
                        <label class="col-md-6 control-label" id="lblEgreso" style="text-align: left;">recib&iacute; de la COMUNIDAD CAMPESINA LLACUABAMBA.</label>
                    </div>
                </div>
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-2 control-label" for="txtReciboConcepto" style="text-align: right;">Por concepto de:</label>
                        <div class="col-md-10">
                            <input class="form-control input-sm  text-uppercase" name="txtReciboConcepto" id="txtReciboConcepto" autocomplete="off" >                                                        
                        </div>
                    </div>
                </div>
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <label class="col-md-2 control-label" for="txtReciboSuma" style="text-align: right;">La suma de:</label>
                        <div class="col-md-4">
                            <input class="form-control input-sm  " name="txtReciboSuma" id="txtReciboSuma" autocomplete="off" onkeypress="return filterFloat(event, this);" >                                                        
                        </div>
                        <label class="col-md-6 control-label" id="lblSumaLetras" style="text-align: left;"></label>
                    </div>
                </div>
                <br>
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-5 col-md-offset-2">
                            <div class="row" >
                                <div class="col-md-10" id="signatureDiv">
                                    <img id="signatureImageVB" src=" " style="width: 100%" />
                                </div>
                            </div>
                            <br>
                            <div class="row" >
                                <div class="col-md-10" id="signatureDiv">
                                    <input type="button" class="col-md-12 btn-success" id="signButtonVB" value="V.B." onClick="tabletDemo()" />
                                </div>
                            </div>
                        </div>

                        <div class="col-md-5 ">
                            <div class="row" >
                                <div class="col-md-10" id="signatureDiv">
                                    <img id="signatureImageRC" src=" " style="width: 100%" />
                                </div>
                            </div>
                            <br>
                            <div class="row" >
                                <div class="col-md-10" id="signatureDiv">
                                    <input type="button" class="col-md-12 btn-success" id="signButtonRC" value="RECIBI CONFORME" onClick="tabletDemo()" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row" >
                    <div class="col-md-12">
                        <button class="btn btn-primary col-md-2 col-md-offset-5" id="btnRegistrar">REGISTRAR</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<%--MODAL PERSONA--%>
<div class="modal fade" id="modalPersona" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">BUSCAR PERSONA</h2>
                    </div>
                </div>

            </div>
            <div class="modal-body">
                <div class="row" >
                    <div class="col-md-12">
                        <div class="col-md-4">
                            <select class='simple-select form-control' data-style='btn-default' name='cboModalPersonaBusqueda' id='cboModalPersonaBusqueda'>
                                <option value="1">NRO DOCUMENTO</option>
                                <option value="2">NOMBRE</option>
                            </select>                
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control text-uppercase" name="txtModalPersonaCriterio" id="txtModalPersonaCriterio" autocomplete="off" maxlength="50" />
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-w-m btn-success" name="btnModalPersonaBuscar" id="btnModalPersonaBuscar"><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button> 
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div id="divTablaBienes" class="col-md-12"> 
                        <table class="table table-striped table-bordered table-hover" id="tblModalPersona" name="tblModalPersona">
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

<%--ANULAR RECIBO--%>
<div class="modal fade" id="modalReciboAnular" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
                <div class="row">
                    <div class="col-md-12">
                        <h2 class="modal-title" id="modalLabel">ANULAR RECIBO</h2>
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
                        <button type="button" class="btn btn-danger col-md-4 " name="btnAnularRecibo" id="btnAnularRecibo" title="Anular recibo"><i class="fa fa-save" aria-hidden="true" ></i>&nbsp; Anular</button> 
                   
                        <button type="button" class="btn btn-success col-md-4 pull-right" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/structure/footer.jsp" />
<script src="<c:url value="/resources/js/jsNumerosALetras/jsNumerosLetras.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsTabletWacom/BigInt.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsTabletWacom/demoButtons_encryption.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsTabletWacom/q.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsTabletWacom/wgssStuSdk.js" />"type="text/javascript"></script>

<script src="<c:url value="/resources/js/jsAdministracion/jsReciboCaja/jsReciboCaja.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsAdministracion/jsReciboCaja/jsReciboCajaTable.js" />"type="text/javascript"></script>
