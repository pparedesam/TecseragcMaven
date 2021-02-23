<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Buscar Persona</h4>
        <div class="row">
            <form class="form-horizontal" id="form-buscar"  name="form-buscar" method="post">
                <div class="row">
                    <div class="well col-md-11" style="margin-left: 40px; margin-right: 20px">
                        <div class="col-md-5">
                            <div class="form-group">
                                <label class="col-md-4 control-label " for="txtPersonaNatural">Datos Personales</label>
                                <div class="col-md-8 no-padding" >
                                    <input class="form-control m-t-sm input"  name="txtPersonaNatural" id="txtPersonaNatural" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 m-t-sm ">
                            <label class="radio-inline">
                                <input type="radio" name="optradio" id="optradio" value="1">DNI
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optradio" id="optradio" value="2" checked="">Nombres
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optradio" id="optradio" value="3">Nro. Tarjeta Elec.
                            </label>
                        </div>
                        <div class="col-md-2 m-t-sm" >
                            <button class="btn btn-primary" type="button" id="btnBuscarPersona" name="btnBuscarPersona" >Buscar</button>
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




