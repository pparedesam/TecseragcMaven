<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">BUSCAR PERSONA NATURAL</h4>
        <div class="row">
            <form class="form-horizontal" id="frmModalBuscarPersonaNatural"  name="form-buscar" method="post">
                <div class="row">
                    <div class="well col-md-11" style="margin-left: 40px; margin-right: 20px">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-4 control-label " for="txtPersonaNatural">Datos Personales</label>
                                <div class="col-md-8" >
                                    <input class="form-control"  name="txtPersonaNatural" id="txtPersonaNatural" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label class="radio-inline">
                                <input type="radio" name="optradio" id="optradio" value="1">DNI
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optradio" id="optradio" value="2" checked="">Nombres
                            </label>                            
                        </div>
                        <div class="col-md-2" >
                            <button class="btn btn-w-m btn-OceanBoatBlue" type="button" id="btnBuscarPersonaModal" name="btnBuscarPersonaModal" >Buscar</button>
                        </div>                                        
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="modal-body">

        <div class="row">
            <div class="col-lg-8 col-md-offset-2" >
                <table class="table table-striped table-bordered table-hover" id="tbl_PersonaNatural" align='center'>
                    <thead>
                        <tr>
                            <th>Nro Documento</th>
                            <th>Persona</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>  
                    <tfoot>
                        <tr>
                            <th>Nro Documento</th>
                            <th>Persona</th>
                            <th>Acciones</th>
                        </tr>

                    </tfoot>
                </table>
            </div>
        </div>

    </div>
    <div class="modal-footer">

    </div>
</div>
<script src="<c:url value="/resources/js/jsfrmBusquedas/jsBuscarPersonaNatural.js" />" type="text/javascript"></script>




