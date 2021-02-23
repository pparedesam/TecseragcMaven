<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>var nomgrupo = "";</script>

<div class="ibox float-e-margins">           
    <div class="ibox-content">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <legend class="text-success">MANTENEDOR DE HORARIOS</legend>
                <div id="divtblGrupoHorario" class="col-lg-8 col-md-offset-2">
                    <div class="col-sm-3 ">
                        <button class='btn btn-OceanBoatBlue' type='button' 
                                name='btnAgregarHorario'
                                data-target="#ModalMF" data-toggle="modal"
                                id='btnAgregarHorario'>Agregar Horario
                        </button>
                        <div></div>
                    </div><legend class="text-success">HORARIOS</legend>

                    <div>

                        <table class="table table-striped table-bordered table-hover" id="tblGrupoHorario">

                            <thead>
                                <tr>
                                    <th class='text-left' style='width: 5%'>Grupo</th>    
                                    <th class='text-left' style='width: 5%'>Descripcion</th>    
                                    <th class='text-left' style='width: 5%'>Horario Mañana</th>    
                                    <th class='text-left' style='width: 5%'>Horario Tarde</th>    
                                    <th class='text-left' style='width: 3%'>Accion</th>       
                                </tr>
                            </thead>

                            <tbody>

                            </tbody>  

                        </table>


                    </div>

                </div>
                <div id="divtblGrupoPersonal" class="col-lg-8 col-md-offset-2" >
                    <div class="col-sm-3 ">
                        <button class='btn btn-OceanBoatBlue' type='button' 
                                name='btnAgregarHorarioPersonal'
                                data-target="#ModalMF" data-toggle="modal"
                                id='btnAgregarHorarioPersonal'>Agregar Personal
                        </button>
                    </div> <div name="dvnomgrup" id="dvnomgrup"> </div>

                    <div>
                        <table class="table table-striped table-bordered table-hover" id="tblGrupoPersonal">

                            <thead>
                                <tr>
                                    <th class='text-left' style='width: 5%'>Personal</th>    
                                    <th class='text-left' style='width: 5%'>Oficina</th>  
                                    <th class='text-left' style='width: 5%'>Area</th>  
                                    <th class='text-left' style='width: 5%'>Puesto</th>  
                                    <th class='text-left' style='width: 5%'>Accion</th>       
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
    <div class="modal inmodal" id="modalGrupoHoras"  role="dialog" aria-hidden="true" style="overflow:hidden;">
        <div class="modal-dialog">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <i class="fa fa-laptop modal-icon"></i>
                    <h4 class="modal-title">Registro de Horarios</h4>
                </div>
                <form class="form-horizontal" id="frmGrupoHoras" name="frmGrupoHoras" method="Post">
                    <div class="modal-body" id="modalGrupoHorasbody" name="modalGrupoHorasbody">
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Grupo:</label>
                                    <div class="col-sm-6 ">
                                        <input name="txtidGrupo" id="txtidGrupo"  data-tip="" type="hidden" disabled="" value="0" >
                                        <input class="form-control" name="txtGrupo" id="txtGrupo" autocomplete="off">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Descripcion:</label>
                                    <div class="col-sm-6 ">

                                        <input class="form-control" name="txtDesGrupo" id="txtDesGrupo" autocomplete="off">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Mañana Entrada:</label>
                                    <div class="col-sm-6 ">
                                        <div class="input-group clockpicker">
                                            <input type="text" class="form-control"  name="txtME" id="txtME" autocomplete="off">
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-time"></span>
                                            </span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-8">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Mañana Salida:</label>
                                    <div class="col-sm-6 ">
                                        <div class="input-group clockpicker" data-autoclose="true">
                                            <input type="text" class="form-control" value="" name="txtMS" id="txtMS" autocomplete="off">
                                            <span class="input-group-addon">
                                                <span class="fa fa-clock-o"></span>
                                            </span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                             <div class="col-lg-8">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Tarde Entrada:</label>
                                    <div class="col-sm-6 ">
                                        <div class="input-group clockpicker" data-autoclose="true">
                                            <input type="text" class="form-control" value="" name="txtTE" id="txtTE" autocomplete="off">
                                            <span class="input-group-addon">
                                                <span class="fa fa-clock-o"></span>
                                            </span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="form-group reg_per">
                                    <label class="col-sm-4 control-label m-r">Tarde Salida:</label>
                                    <div class="col-sm-6 ">
                                        <div class="input-group clockpicker" data-autoclose="true">
                                            <input type="text" class="form-control" value="" name="txtTS" id="txtTS" autocomplete="off">
                                            <span class="input-group-addon">
                                                <span class="fa fa-clock-o"></span>
                                            </span>
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">Cancelar</button>
                        <button class="btn btn-success" type="submit" name="btnRegGrupo" id="btnRegGrupo">Agregar</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <div class="modal inmodal " id="modalPersonal"  role="dialog" aria-hidden="true" style="overflow:hidden;">
        <div class="modal-dialog">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Buscar Trabajador</h4>

                </div>

                <div class="modal-body" id="modalPersonalbody" name="modalPersonalbody">
                    <div class="row">
                        <form class="form-horizontal" id="frmPersonal" name="frmPersonal" method="Post">
                            <div class="row">

                                <div class="col-lg-6">
                                    <div class="form-group reg_per">
                                        <label class="col-sm-4 control-label m-r" for="txtNomPersonal">Nombre:</label>

                                        <div class="col-sm-7">

                                            <input class="form-control input-sm" name="txtNomPersonal"
                                                   id="txtNomPersonal" type="text"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-8 col-md-offset-2">
                                    <div class="form-group reg_per">
                                        <button class="btn btn-OceanBoatBlue" type="button" name="btnBuscarPersonal"
                                                id="btnBuscarPersonal">Buscar
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <div class="col-lg-10 col-md-offset-1">
                            <table class="table table-striped table-bordered table-hover"
                                   id="tbl_PersonalHorario" align='center'>
                                <thead>
                                    <tr>
                                        <th>Nombres</th>
                                        <th>Oficina</th>
                                        <th>Area</th>
                                        <th>Acción</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                                <tfoot>
                                <th>Nombres</th>
                                <th>Oficina</th>
                                <th>Area</th>
                                <th>Acción</th>
                                </tfoot>

                            </table>
                        </div>
                    </div>
                </div>


            </div>
        </div>

    </div>

</div>
<!-- ---------end js -------------->


<script src="<c:url value="/resources/js/jsRRHH/jsHorario/jsHorario.js"/>"
type="text/javascript"></script>



<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
