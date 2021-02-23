<%-- 
    Document   : frmContratos
    Created on : 09-sep-2019, 9:23:21
    Author     : AndZuñ
--%>

<%@page import="ll.negocio.administracion.OficinaNEG"%>
<%@page import="ll.entidades.administracion.Oficina"%>
<%@page import="ll.entidades.administracion.Empleado"%>
<%@page import="ll.negocio.administracion.EmpleadoNEG"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="ll.negocio.agente.ImagenesNEG"%>

<%@page import="ll.entidades.rrhh.Contrato"%>

<%@page import="ll.negocio.agente.AccesoOpexUsuarioNEG"%>
<%@page import="ll.entidades.agentes.AccesoOpexUsuario"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />



<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <center><legend class="text-success">CONTRATOS DE TRABAJADOR</legend></center>   
    </div>
</div>

<div class="well col-md-8 col-md-offset-2">
    <form class="form-horizontal" id="frmBuscarEmpleado" name="frmBuscarEmpleado" method="post">
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <div class="col-md-3">
                        <select class="form-control simple-select" name="cboTipoBusqueda" id="cboTipoBusqueda">
                            <option value="1" option selected="selected">Nro Documento</option>                                           
                            <option value="2">Nombre</option> 
                        </select>
                    </div>
                    <div class="col-md-6">
                        <input class="form-control input-sm"  name="txtBuscarEmpleado" id="txtBuscarEmpleado" type="text" autocomplete="off">
                    </div>
                    <div class="col-md-3">
                        <button class="btn btn-OceanBoatBlue btn-sm col-md-12" type="submit" id="btnBuscarEmpleado" name="btnBuscarEmpleado" title='Buscar'><i class="fa fa-search" aria-hidden="true" fa-3x></i>&nbsp; BUSCAR</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="row">
    <div id="divTablaEmpleados">
        <div class="col-lg-8 col-md-offset-2">
            <table class="table table-striped table-bordered table-hover" id="tblListaEmpleados" name="tblListaEmpleados">
                <thead>
                    <tr>
                        <th class='text-center'>Nro Documento</th>
                        <th class='text-center'>Empleado</th>
                        <th class='text-center'>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="datosEmpleado" style="display: none">          
    <div class="row"  style="border-style : none;" id='datosContratos'>
        <div class="col-md-12" id="divContratoEmpleado">
            <div class="col-md-10 col-md-offset-1">
                <div class="row">
                    <div class="form-group col-md-12">
                        <input class="form-control input-sm " name="txtIdpersona" id="txtIdpersona" readonly="" type="text" style="display: none;" autocomplete="off">
                        <label class="col-sm-1 control-label"  for="txtNombreEmpleado">Nombre:</label> 
                        <div class="col-md-7" style="padding-right:60px">
                            <input class="form-control input-sm " name="txtNombreEmpleado" id="txtNombreEmpleado" readonly="" type="text" autocomplete="off">                                                        
                        </div>
                        <label class="col-sm-1 control-label"  for="txtNroDoc">DNI:</label> 
                        <div class="col-md-3" style="padding-right:60px">
                            <input class="form-control input-sm " name="txtNroDoc" id="txtNroDoc" readonly="" type="text" autocomplete="off">                                                        
                        </div>
                    </div>                                                
                </div>
                <br>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-7">
                            <div class="col-md-12">
                                <label class="col-md-1 control-label" style="padding-right:60px" for="txtCorreo">Correo:</label> 
                                <div class="col-md-5">
                                    <input class="form-control input-sm col-md-8" name="txtCorreo" id="txtCorreo" readonly="" type="text" autocomplete="off">                                                        
                                </div>
                                <label class="col-md-1 control-label" style="padding-right:60px" for="txtUsuario">Usuario:</label> 
                                <div class="col-md-3">
                                    <input class="form-control input-sm " name="txtUsuario" id="txtUsuario" readonly="" type="text" autocomplete="off">                                                        
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="col-sm-3" style="margin-right: 10px" id="NuevoContrato">
                                <button class="btn btn-success" type="button" name="btnNuevoContrato" id="btnNuevoContrato" title='Agregar Contrato'><i class="fa fa-plus" aria-hidden="true"></i>&nbsp; Nuevo</button>
                            </div>
                            <div class="col-sm-4" style="margin-right: 10px" id="NuevoEmpleado" style="display: none;" >
                                <button class="btn btn-success" type="button" name="btnNuevoEmpleado" id="btnNuevoEmpleado" title='Nuevo Trabajador'><i class="fa fa-plus" aria-hidden="true"></i>&nbsp; Nuevo Trabajador</button>
                            </div>

                            <div class="col-md-3" style="margin-right: 10px" id="ModificarContrato">
                                <button class="btn btn-success" type="button" name="btnModificarContrato" id="btnModificarContrato" title='Editar Contrato'><i class="fa fa-pencil" aria-hidden="true"></i>&nbsp; Editar</button>
                            </div>
                            <div class="col-md-3" style="margin-right: 10px" id="CancelarContrato">
                                <button class="btn btn-success" name="btnModalCancelarContrato" id="btnModalCancelarContrato" title='Finalizar Contrato Actual' data-toggle="modal" data-target="#miModal">Fin Contrato</button>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="panel panel-primary" id="panelEmpleadoSinContrato" style="display: none;">
                        <div class="panel-heading"><h2><b>Datos Trabajador - Contrato Actual</b></h2></div>
                        <div class="panel-body"><legend class="text-success">No cuenta con un contrato vigente.</legend></div>
                    </div>
                    <div class="panel panel-primary" id="panelDatosEmpleado">
                        <div class="panel-heading"><h2><b>Datos Trabajador - Contrato Actual</b></h2></div>
                        <div class="panel-body" style="margin: 10px;">
                            <div class="row">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">  
                                        <h3>Datos del Trabajador</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div  id="divDatosEmpleado" name="divDatosEmpleado" style="margin: auto;">
                                            <form class="form-horizontal"  id="frmContratoActual" name="frmDatosEmpleado" method="post" style="padding: 10px">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-sm-1 control-label"  for="cboBanco">Banco:</label> 
                                                        <div class="col-md-5" >
                                                            <select class='simple-select form-control' data-style='btn-default' name='cboBanco' id='cboBanco' disabled>
                                                            </select>                                           
                                                        </div>
                                                        <label class="col-sm-2 control-label"  for="txtNroCuenta">Nro Cuenta:</label> 
                                                        <div class="col-md-4" style="padding-right:30px">
                                                            <input type="text" class="form-control input-sm " name="txtNroCuenta" id="txtNroCuenta" disabled type="text" autocomplete="off">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-sm-1 control-label"  for="cboAFP">Sitema Pensiones:</label> 
                                                        <div class="col-md-5" >
                                                            <select class='simple-select form-control' data-style='btn-default' name='cboAFP' id='cboAFP' >
                                                            </select>                                                        
                                                        </div>
                                                        <label class="col-sm-2 control-label"  for="txtCodigoAFP">Codigo AFP:</label> 
                                                        <div class="col-md-4" style="padding-right:30px">
                                                            <input type="text" class="form-control input-sm " name="txtCodigoAFP" id="txtCodigoAFP" maxlength="12" disabled type="text" autocomplete="off">                                                        
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-sm-1  control-label"  for="cboTipoComision">Comision Mixta:</label> 
                                                        <div class="col-md-2" >
                                                            <select class='simple-select form-control' data-style='btn-default' name='cboTipoComision' id='cboTipoComision' disabled>
                                                            </select>                                                        
                                                        </div>                                              
                                                        <label class="col-sm-1 control-label"  for="txtNroHijos">Nro Hijos:</label> 
                                                        <div class="col-md-2" >
                                                            <input type="number" class="form-control input-sm " name="txtNroHijos" id="txtNroHijos" disabled type="text" min="0" onkeypress="return SoloNumero(event);">                                                        
                                                        </div>
                                                        <label class="col-sm-2 control-label"  for="txtESSALUD">ESSALUD:</label> 
                                                        <div class="col-md-4" style="padding-right:30px">
                                                            <input type="text" class="form-control input-sm " name="txtESSALUD" id="txtESSALUD" disabled type="text">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-sm-1 control-label"  for="cboEPS">EPS:</label> 
                                                        <div class="col-md-2">
                                                            <select class='simple-select form-control' data-style='btn-default' name='cboEPS' id='cboEPS'>
                                                            </select>                                           
                                                        </div>
                                                        <div name='TipoPlan' id='TipoPlan'>
                                                            <label class="col-sm-1 control-label"  for="cboTipoEPS">Plan EPS:</label> 
                                                            <div class="col-md-2">
                                                                <select class='simple-select form-control' data-style='btn-default' name='cboTipoEPS' id='cboTipoEPS'>
                                                                </select>                                           
                                                            </div>
                                                            <label class="col-sm-2 control-label"  for="cboTipoPlan">Tipo Plan:</label> 
                                                            <div class="col-md-4" style="padding-right:30px">
                                                                <select class='simple-select form-control' data-style='btn-default' name='cboTipoPlan' id='cboTipoPlan'>        
                                                                </select>                                           
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-4 col-md-offset-1" >
                                                            <input type="checkbox" id="chbxFiscalizado" name="chbxFiscalizado">
                                                            <label class="control-label"  for="chbxFiscalizado">Horario Fiscalizado.</label>
                                                        </div>
                                                    </div>
                                                </div>

                                            </form>
                                        </div>
                                        <div  id="divfotoEmpleado" name="divFotoEmpleado">
                                            <row>
                                                <div class="col-md-12"style="margin: 15px">
                                                    <img class="col-md-12" id="fotoEmpleado" name="fotoEmpleado">
                                                </div>
                                                <canvas id="canvasfoto" name="canvasfoto" style="display: none">
                                                </canvas>
                                            </row>
                                            <row>
                                                <div class="col-md-8 col-md-offset-2">
                                                    <div class="custom-file">
                                                        <span class="input-group-addon btn btn-default btn-file">
                                                            <span class="fileinput-new">Seleccione Foto</span>
                                                            <input type="file" name="btnFotoEmpleado" id="btnFotoEmpleado"/>
                                                        </span>
                                                    </div> 
                                                </div>
                                            </row>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">  
                                        <h3>Detalle de Contrato</h3>
                                    </div>
                                    <div class="panel-body">
                                        <form class="form-horizontal" id="frmContratoActual" name="frmContratoActual" method="post">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-sm-1 col-md-offset-1 control-label"  for="cboOficina">Oficina:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <select class='simple-select form-control' data-style='btn-default' name='cboOficina' id='cboOficina' disabled>
                                                        </select>                                           
                                                    </div>
                                                    <label class="col-sm-1 col-md-offset-2 control-label"  for="txtFecInicio">Fecha Inicio:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <div class="input-group date">
                                                            <input type="text" class="form-control" id="txtFecInicio" name="txtFecInicio" readonly>
                                                            <div class="input-group-addon">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-sm-1 col-md-offset-1 control-label"  for="cboDepartamento">Departamento:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <select class='simple-select form-control' data-style='btn-default' name='cboDepartamento' id='cboDepartamento' disabled>
                                                        </select>                                                        
                                                    </div>
                                                    <label class="col-sm-1 col-md-offset-2 control-label"  for="txtSalInicial">Salario Inicial:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <input class="form-control input-sm " name="txtSalInicial" id="txtSalInicial" disabled type="number">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-sm-1 col-md-offset-1 control-label"  for="cboPuesto">Puesto:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <select class='simple-select form-control' data-style='btn-default' name='cboPuesto' id='cboPuesto' disabled>
                                                        </select>                                                        
                                                    </div>
                                                    <label class="col-sm-1 col-md-offset-2 control-label"  for="txtFecFinal">Fecha Final:</label>
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <div class="input-group date" id="divFecFinal" name="divFecFinal">
                                                            <input type="text" class="form-control input-sm" id="txtFecFinal" name="txtFecFinal" readonly>
                                                            <div class="input-group-addon" id="icoFecFinal" name="icoFecFinal">
                                                                <span class="glyphicon glyphicon-th"></span>
                                                            </div>
                                                        </div>
                                                        <input type="TEXT" class="form-control input-sm " name="txtIndeterminado" value="INDETERMINADO" id="txtIndeterminado" disabled type="text" style="display: none;">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <!--<label class="col-sm-1 col-md-offset-1 control-label"  for="cboCargo">Cargo:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <select id="cboCargo" name="cboCargo" class="simple-select form-control" data-style='btn-default' disabled>
                                                        </select>                                                        
                                                    </div>-->
                                                    <div class="col-md-4 col-md-offset-1 " style="padding-right:30px">
                                                                                                              
                                                    </div>
                                                    <label class="col-sm-1 col-md-offset-2 control-label"  for="txtSalFinal">Salario Actual:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <input type="number" class="form-control input-sm " name="txtSalFinal" id="txtSalFinal" disabled type="text" onkeypress="return salSoloNumero(event);">
                                                        <div id="motSalida" name="motSalida" style="display: none;">
                                                            <select id="cboMotSalida" name="cboMotSalida" class="simple-select form-control" data-style='btn-default'>
                                                                <option value="DESPIDO">DESPIDO</option>
                                                                <option value="RENUNCIA">RENUNCIA</option>
                                                                <option value="FINCONTRATO">TERMINO DE CONTRATO</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>  
                                            <div class="row">
                                                <div class="form-group">
                                                    <label class="col-sm-1 col-md-offset-1 control-label"  for="cboTipoContrato">Tipo Contrato:</label> 
                                                    <div class="col-md-3" style="padding-right:30px">
                                                        <select id="cboTipoContrato" name="cboTipoContrato" class="simple-select form-control" data-style='btn-default' disabled>
                                                        </select>                                                        
                                                    </div>
                                                </div>
                                            </div>        
                                            <br>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">  
                                        <h3>Horario</h3>
                                    </div>
                                    <div class="panel-body">
                                        <hr/>
                                        <div class="row">
                                            <label class="col-sm-2 control-label"  for="cboHorario" style="text-align: right">Horario:</label> 
                                            <div class="col-md-9">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboHorario' id='cboHorario'>
                                                </select>                                           
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <label class="col-sm-2 control-label"  for="txtHoraIngreso" style="text-align: right">Hora ingreso:</label> 
                                            <div class="col-md-3">
                                                <input type="text" class="form-control input-sm " name="txtHoraIngreso" id="txtHoraIngreso" disabled type="text">
                                            </div>
                                            <label class="col-sm-2 col-md-offset-1 control-label"  for="txtHoraSalida" style="text-align: right">Hora salida:</label> 
                                            <div class="col-md-3">
                                                <input type="text" class="form-control input-sm " name="txtHoraSalida" id="txtHoraSalida" disabled type="text">
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row" id="horaRefrigerio" name="horaRefrigerio">
                                            <label class="col-sm-2 control-label"  for="txtRefrigerioInicio" style="text-align: right">Inicio refrigerio:</label> 
                                            <div class="col-md-3">
                                                <input type="text" class="form-control input-sm " name="txtRefrigerioIngreso" id="txtRefrigerioIngreso" disabled type="text">
                                            </div>
                                            <label class="col-sm-2 col-md-offset-1 control-label"  for="txtRefrigerioFin" style="text-align: right">Fin refrigerio:</label> 
                                            <div class="col-md-3">
                                                <input type="text" class="form-control input-sm " name="txtRefrigerioFin" id="txtRefrigerioFin" disabled type="text">
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <label class="col-sm-2 control-label"  for="cboHorarioSabado" style="text-align: right">Horario sabado:</label> 
                                            <div class="col-md-2">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboHorarioSabado' id='cboHorarioSabado'>
                                                    <option value="NO">NO</option>
                                                    <option value="SI">SI</option>
                                                </select>                                           
                                            </div>
                                            <div class="col-md-7" name='TipoHorarioSabado' id='TipoHorarioSabado' style="display: none">
                                                <select class='simple-select form-control' data-style='btn-default' name='cboTipoHorarioSabado' id='cboTipoHorarioSabado' >
                                                </select>                                           
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row" name="horarioSabado" id="horarioSabado">
                                            <label class="col-sm-2 control-label"  for="txtIngresoSabado" style="text-align: right">Hora ingreso:</label> 
                                            <div class="col-md-3">
                                                <input type="text" class="form-control input-sm " name="txtIngresoSabado" id="txtIngresoSabado" disabled type="text">
                                            </div>
                                            <label class="col-sm-2 col-md-offset-1 control-label"  for="txtSalidaSabado" style="text-align: right">Hora salida:</label> 
                                            <div class="col-md-3">
                                                <input type="text" class="form-control input-sm " name="txtSalidaSabado" id="txtSalidaSabado" disabled type="text">
                                            </div>
                                        </div>
                                    </div>     
                                </div>
                            </div> 
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <div class="col-md-6 col-md-offset-3" style="padding-right:30px">
                                        <button class="btn btn-success col-md-4 col-md-offset-2" type="button" name="btnRegistrarEmpleado" id="btnRegistrarEmpleado" style="display: none;" title='Registrar Nuevo Empleado'><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp; Registrar</button>
                                        <button class="btn btn-success col-md-4 col-md-offset-2" type="button" name="btnRegistrarContrato" id="btnRegistrarContrato" style="display: none;" title='Registrar Nuevo Contrato'><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp; Registrar</button>
                                        <button class="btn btn-success col-md-4 col-md-offset-2" type="button" name="btnActualizarContrato" id="btnActualizarContrato" style="display: none;" title='Actualizar Contrato'><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp; Actualizar</button>
                                        <button class="btn btn-success col-md-4 col-md-offset-2" type="button" name="btnCancelar" id="btnCancelar" style="display: none;" title='Cancelar'><i class="fa fa-times" aria-hidden="true"></i>&nbsp; Cancelar</button>

                                    </div>
                                    <div class="col-md-6 col-md-offset-6" style="padding-right:30px">
                                        <button class="btn btn-success col-md-5" type="button" name="btnExportarReporte" id="btnExportarReporte" style="display: block;" title='Exportar reporte'><i class="fa fa-send" aria-hidden="true"></i>&nbsp; Exportar reporte</button>
                                        <button class="btn btn-success col-md-5 col-md-offset-1" type="button" name="btnImprimirContrato" id="btnImprimirContrato" style="display: block;" title='Imprimir contrato'><i class="fa fa-print" aria-hidden="true"></i>&nbsp; Imprimir contrato</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container" style="margin-top: 60px;">
                    <div class="modal fade" id="miModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header panel-title">
                                    <button type=button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4>Finalizar Contrato de Empleado</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-sm-1 col-md-offset-1 control-label"  for="txtFecFinContrato">Fecha Final:</label>
                                            <div class="col-md-3" style="padding-right:30px">
                                                <div class="input-group date" id="divFecFinContrato" name="divFecFinContrato">
                                                    <input type="text" class="form-control input-sm" id="txtFecFinContrato" name="txtFecFinContrato" readonly>
                                                    <div class="input-group-addon" id="icoFecFinal" name="icoFecFinal">
                                                        <span class="glyphicon glyphicon-th"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 col-md-offset-1 control-label"  for="cboMotSalida">Motivo Finalizacion:</label> 
                                            <div class="col-md-4" style="padding-right:30px">
                                                <div id="motSalida" name="motSalida">
                                                    <select id="cboMotSalida" name="cboMotSalida" class="simple-select form-control" data-style='btn-default'>
                                                        <option value="DESPIDO">DESPIDO</option>
                                                        <option value="RENUNCIA">RENUNCIA</option>
                                                        <option value="FINCONTRATO">TERMINO DE CONTRATO</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-success" type="button" data-dismiss="modal">Cerrar</button>
                                    <button class="btn btn-success" type="button" name="btnFinContrato" id="btnFinContrato" title='Finalizar Contrato'><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp; Finalizar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="panel-group" id="accordion" role="tablist">
                        <div class="panel panel-primary">
                            <div class="panel-heading" role="tab" id="heading1">
                                <h4 class="panel-title float-right">
                                    <a href="#collapse1" data-toggle="collapse" data-parent="#accordion" id="expandirContrato" name="expandirContrato" style="display: block">
                                        <i class="fa fa-expand" aria-hidden="true"></i>&nbsp; Mostrar - Historial Contratos
                                    </a> 
                                    <a href="#collapse1" data-toggle="collapse" data-parent="#accordion" id="contraerContrato" name="contraerContrato" style="display: none">
                                        <i class="fa fa-compress" aria-hidden="true"></i>&nbsp; Ocultar - Historial Contratos
                                    </a>
                                </h4> 
                            </div>
                            <div id="collapse1" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div id="divTablaEmpleados col-md-10 col-md-offset-1">
                                        <div class="table-responsive col-md-12">
                                            <table class="display nowrap table table-striped " id="tblHistorialContratos" name="tblHistorialContratos" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th class='text-center'>Cargo</th>
                                                        <th class='text-center'>Fecha Inicial</th>
                                                        <th class='text-center'>Salario Inicial</th>
                                                        <th class='text-center'>Fecha Final</th>
                                                        <th class='text-center'>Salario Final</th>
                                                        <th class='text-center'>Departamento</th>
                                                        <th class='text-center'>Oficina</th>
                                                        <th class='text-center'>Tipo Contrato</th>
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
    </div>
</div>

<script src="<c:url value="/resources/js/jsRRHH/jsContratos/jsContratos.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsRRHH/jsContratos/jsEmpleados.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/js/jsRRHH/jsContratos/jsEmpleadosTable.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionRRHH/jsValidacionContratos.js" />" type="text/javascript"></script>

<jsp:include page="/WEB-INF/structure/footer.jsp" />

