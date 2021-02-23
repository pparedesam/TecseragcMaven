<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>


<div class="row">
    <div class="col-md-12">
        <h2> <legend class="text-success"><b>REPORTE DE CONTROL DE CAJA</b></legend></h2>
    </div>
</div>

<div class="row" >
    <div class="col-md-12 well" style="background-color: #FFF;">
        <div class="row" >
            <div class="col-md-12">
                <label class="col-md-1 control-label" for="txtFechaInicio" style="text-align: right;">Fecha inicio:</label>
                <div class="col-md-3" style="padding-right:15px">
                    <div class="input-group date" id="txtFechaInicio" name="txtFechaInicio">
                        <input type="text" class="form-control text-center fechaInicio" readonly>
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
                
                <label class="col-md-1 control-label" for="txtFechaFin" style="text-align: right;">Fecha fin:</label>
                <div class="col-md-3" style="padding-right:15px">
                    <div class="input-group date" id="txtFechaFin" name="txtFechaFin">
                        <input type="text" class="form-control text-center fechaFin" readonly>
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
                
                <button class='btn btn-OceanBoatBlue col-md-2 pull-right' type='button' name='btnGenerarReporte'id='btnGenerarReporte'><i class="fa fa-file-excel-o" aria-hidden="true" fa-3x></i>&nbsp;REPORTE</button>

            </div>
        </div>
    </div>
</div>


<!-- ---------end js -------------->
<script src="<c:url value="/resources/js/jsAdministracion/jsReciboCaja/jsRptReciboCaja.js" />"type="text/javascript"></script>

<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
