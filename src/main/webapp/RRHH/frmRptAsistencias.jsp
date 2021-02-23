
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
    <div class="col-md-10 col-md-offset-1" style="text-align: left">
        <legend class="text-success"><b>REPORTE ASISTENCIAS</b></legend>
    </div>
</div>

<div class="well col-md-10 col-md-offset-1" style="background-color: #FFF">
    <div class="row">
        <div class="col-md-12 " style="text-align: left">
            <legend class="subtitulos">FECHAS</legend>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12"  style="text-align: left">

            <div class="form-group reg_per">
                <label class="col-sm-1 control-label m-r" for="cboTipoFecha">Tipo:</label>
                <div class="col-sm-3">
                    <select class="simple-select form-control m-r-n" name="cboTipoFecha" id="cboTipoFecha">                                                                    
                        <option value="1">FECHA UNICA</option>
                        <option value="2">MES Y AÑO</option>
                        <option value="3">RANGO DE FECHAS</option>
                    </select>
                </div>
                <div class="row" id="divFecUnica" >
                    <div class="col-sm-3">
                        <div class="input-group date">
                            <input type="text" class="form-control"   placeholder="DD/MM/YYYY" name="txtFechaUnica" id="txtFechaUnica" autocomplete="off">
                            <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row" id="divMesAño" hidden="">
                    <div class="col-sm-3">
                        <div class="input-group date">
                            <input type="text" class="form-control"   placeholder="MM/YYYY" name="txtfechaMesAnio" id="txtfechaMesAnio" autocomplete="off">
                            <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row" id="divRangoFec" hidden="">
                    <div class="col-sm-3">
                        <div class="input-group date">
                            <input type="text" class="form-control"   placeholder="DD/MM/YYYY" name="txtFechaInicio" id="txtFechaInicio" autocomplete="off">
                            <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group date">
                            <input type="text" class="form-control"   placeholder="DD/MM/YYYY" name="txtFechaFin" id="txtFechaFin" autocomplete="off">
                            <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>
                <!--<div class="row">
                    <div class="col-sm-3">
                        <button class='btn btn-OceanBoatBlue' type='button' 
                                name='btnGenerarRptResCar'id='btnGenerarRptResCar'>Generar 
                        </button>
                    </div>
                </div>-->
            </div>                                

        </div>
    </div>
    <div class="row">
        <div class="col-md-12 " style="text-align: left">
            <legend class="subtitulos">TRABAJADOR</legend>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12"  style="text-align: left">
            <div class="form-group reg_per">
                <label class="col-sm-1 control-label m-r" for="cboOficina">Oficina:</label>
                <div class="col-sm-3">
                    <select class="simple-select form-control"  name="cboOficina" id="cboOficina">                                                                    

                    </select>
                </div>
            </div>
        </div>
        <div class="col-md-12"  style="text-align: left">
            <div class="form-group reg_per">
                <label class="col-sm-1 control-label m-r" for="cboDpto">Area: </label>
                <div class="col-sm-3">
                    <select class="simple-select form-control m-r-n" name="cboDpto" id="cboDpto">                                                                    


                    </select>
                </div>
            </div>
        </div>
        <div class="col-md-12"  style="text-align: left">
            <div class="form-group reg_per">
                <label class="col-sm-1 control-label m-r" for="cboPersonal">Trabajador: </label>
                <div class="col-sm-3">
                    <select class="simple-select form-control m-r-n" name="cboPersonal" id="cboPersonal">                                                                    


                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 " style="text-align: left">
            <button class='btn btn-OceanBoatBlue' type='button' 
                    name='btnGenerarRptResCar'id='btnGenerarRptResCar'>Generar 
            </button>
        </div>
    </div>
</div>

<div class="row">

</div>


<script src="<c:url value="/resources/js/jsRRHH/jsAsistencia/jsRptAsistencia.js"/>" type="text/javascript"></script>

<jsp:include page="/WEB-INF/structure/footer.jsp" />
