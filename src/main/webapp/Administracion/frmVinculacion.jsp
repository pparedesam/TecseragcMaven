<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align: left">
        <legend class="text-success"><b>VINCULACION</b></legend>
    </div>
</div>
<!--
<div class="row">
    <div class="col-md-12" >
        <div class="row">
            <div class="col-md-12" style="text-align: right;">
                <button class="btn btn-success col-md-2 col-md-offset-1" type="button" name="btnBuscarSocio" id="btnBuscarSocio" title='Buscar Socio'><i class="fa fa-search" aria-hidden="true"></i>&nbsp; Buscar Socio</button>
                <button class="btn btn-success col-md-2 col-md-offset-1" type="button" name="btnCancelar" id="btnCancelar" title='Cancelar Busqueda'style="display: none;"><i class="fa fa-times" aria-hidden="true"></i>&nbsp; Cancelar</button>
            </div>
        </div>
    </div>    

</div>
-->
<br>

<div class="well col-md-10 col-md-offset-1" style="background-color: #FFF" id="divBuscarSocio">
    <div class="row">
        <div class="col-md-12 " style="text-align: left">
            <legend class="subtitulos">BUSQUEDA PERSONAL</legend>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12"  style="text-align: left">
            <form class="form-horizontal" id="frmBuscarPersonal"  name="frmBuscarPersonal" method="post">
                <div class="col-md-2">   
                    <select class="form-control" name="cboTipoBusqueda" id="cboTipoBusqueda">
                        <option value="1" option selected="selected">Nro Documento</option>                                           
                        <option value="2">Nombre</option> 
                    </select>                                   
                </div>   
                <div class="col-md-6" >
                    <input class="form-control text-uppercase"  name="txtSocioNaturalJuridico" id="txtSocioNaturalJuridico" type="text" autocomplete="off">
                </div>
                <div class="col-md-2" >
                    <button class="btn btn-OceanBoatBlue block full-width m-b" type="button" id="btnBuscarSocioNaturalJuridico" name="btnBuscarSocioNaturalJuridico" >Buscar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<br>

<div class="well col-md-10 col-md-offset-1" style="background-color: #FFF" id="divtblSocio" hidden="">
    <div class="row" >
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="tbl_SocioJN" align='center'>
                <thead>
                    <tr>
                        <th>Nro Documento</th>
                        <th>Persona</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>  
            </table>
        </div>
    </div>
</div>

<div class="well col-md-10 col-md-offset-1" style="background-color: #FFF" id="divInfoSocio" hidden="" >
    <div class="row" id="estadoSocio" style="display: none;">
        <div class="panel panel-primary" id="panelEstadoSocio">
            <div class="panel-heading"><h2><b><label id="lblTitEstado"></b></h2></div>
            <div class="panel-body"><h3><label id="lblDetEstado"></label></h3></div>
        </div>
    </div>
    <div class="row">
        <div class="panel panel-primary" id="panelDatosSocio" >
            <div class="panel-body" style="margin: 10px;">
                <div class="row" id="pnlDatoSocio">
                    <div class="panel panel-primary">
                        <div class="panel-heading" style="padding-bottom: 0;">  
                            <h3>Datos del Socio</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row" id="divFondoM" name="divFondoM" >
                                        <label class="control-label col-md-2 " for="txtNombres">Nombres:</label>
                                        <div class="col-md-8">
                                            <input class="form-control" name="txtNombres" id="txtNombres" disabled="" type="text">
                                            <input type="hidden" name="txtidPersona" id="txtidPersona"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="control-label col-md-2 ">Area:</label>
                                        <div class="col-md-3">
                                            <input disabled='' type="text" class="form-control" name="txtArea"  id="txtArea" value="" />
                                        </div>
                                        <label class="control-label col-md-2">Puesto:</label>
                                        <div class="col-md-3">
                                            <input disabled='' type="text" class="form-control" name="txtPsto"  id="txtPsto" value="" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="control-label col-md-2 ">Usuario Marcacion:</label>
                                        <div class="col-md-3">
                                            <input disabled='' type="text" class="form-control" name="txtUsumar"  id="txtUsumar" value="" />
                                            <input type="hidden" name="txtisUsuMar" id="txtisUsuMar"/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-12">
                                    <button class="btn btn-success col-md-2 col-md-offset-5" type="button" name="btnVincular" id="btnVincular" disabled=""><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp; VINCULAR</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<div class="well col-md-10 col-md-offset-1" style="background-color: #FFF" id="divbuscarUsuario" hidden="">
    <div class="row">
        <div class="col-md-12 " style="text-align: left">
            <legend class="subtitulos">BUSQUEDA USUARIO MARCACION</legend>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 "  style="text-align: left">
            <form class="form-horizontal" id="frmBuscarUsuario"  name="frmBuscarUsuario" method="post"> 
                <div class="col-md-2">   
                    <select class="form-control" name="cboTipoBusquedaUsuario" id="cboTipoBusquedaUsuario">
                        <option value="1" option selected="selected">Nro Documento</option>                                           
                        <option value="2">Nombre</option> 
                    </select>                                   
                </div> 
                <div class="col-md-6" >
                    <input class="form-control text-uppercase"  name="txtBuscarUsuario" id="txtBuscarUsuario" type="text" autocomplete="off">
                </div>
                <div class="col-md-2" >
                    <button class="btn btn-OceanBoatBlue block full-width m-b" type="button" id="btnBuscarUsuario" name="btnBuscarUsuario" >Buscar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<br>

<div class="well col-md-10 col-md-offset-1" style="background-color: #FFF" id="divtblUsuario" hidden="">
    <div class="row" >
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="tbl_Usuario" align='center'>
                <thead>
                    <tr>
                        <th>Nro Documento</th>
                        <th>Usuario</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>  
            </table>
        </div>
    </div>
</div>


<div class="row">

</div>

<script src="<c:url value="/resources/js/jsAfiliaciones/jsVinculacion/jsVinculacion.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionAfiliaciones/jsValidacionVinculacion/jsValidacionVinculacion.js" />"type="text/javascript"></script>

<jsp:include page="/WEB-INF/structure/footer.jsp" />
