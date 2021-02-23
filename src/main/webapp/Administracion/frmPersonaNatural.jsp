
<%@page import="ll.negocio.administracion.RubroNEG"%>
<%@page import="ll.entidades.administracion.Rubro"%>
<%@page import="ll.negocio.administracion.TipoDocIdentidadNEG"%>
<%@page import="ll.entidades.administracion.TipoDocIdentidad"%>
<%@page import="ll.negocio.administracion.ProfesionNEG"%>
<%@page import="ll.negocio.administracion.PersonaNaturalNEG"%>
<%@page import="ll.negocio.administracion.OcupacionNEG"%>
<%@page import="ll.negocio.administracion.UrbanizacionNEG"%>
<%@page import="ll.entidades.administracion.Urbanizacion"%>
<%@page import="ll.entidades.administracion.Profesion"%>
<%@page import="ll.entidades.administracion.Ocupacion"%>
<%@page import="ll.entidades.administracion.PersonaNatural"%>
<%@page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/structure/template.jsp"/>
<jsp:include page="/WEB-INF/structure/header.jsp"/>
<div class="row">
    <div class="col-md-12">
        <div class="ibox float-e-margins">
            <div class="ibox-content">
                <div class="row">
                    <legend class="text-success"><b>MANTENEDOR DE PERSONA NATURAL</b></legend>


                    <div class="tabs-container" id="tabPersonaNatural" name="tabPersonaNatural">
                        <ul id="tabsPersonaNatural-navigation" class="nav nav-tabs">
                            <li class="active"><a href="#tabRegistrarPersona" data-toggle="tab"><i
                                        class="glyphicon glyphicon-user"></i>Datos Personales</a></li>
                            <li class=""><a href="#tab_BuscarPersona" data-toggle="tab"><i class="fa fa-search"></i>
                                    Busqueda de Personas</a></li>
                        </ul>
                        <div id="tabPersonaNatural-content" class="tab-content">
                            <div class="tab-pane fade " id="tab_BuscarPersona" name="tab_BuscarPersona">
                                <form class="form-horizontal" id="form-buscar" name="form-buscar" method="post">
                                    <div class="row">
                                        <div class="well col-md-11" style="margin-left: 40px; margin-right: 20px">
                                            <div class="col-md-5">
                                                <div class="form-group">
                                                    <label class="col-md-4 control-label " for="txtBuscarPersona">Datos
                                                        Personales</label>
                                                    <div class="col-md-8 no-padding">
                                                        <input class="form-control m-t-sm input" name="txtBuscarPersona"
                                                               id="txtBuscarPersona" type="text">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-5 m-t-sm ">
                                                <label class="radio-inline">
                                                    <input type="radio" name="optradio" id="optradio" value="1">DNI
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="optradio" id="optradio" value="2"
                                                           checked="">Nombres y Apellidos
                                                </label>

                                            </div>
                                            <div class="col-md-2 m-t-sm">
                                                <button class="btn btn-OceanBoatBlue" type="submit" id="btnBuscarPersona"
                                                        name="btnBuscarPersona">Buscar
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">

                                    <div class="col-lg-8 col-md-offset-2">
                                        <table class="table table-striped table-bordered table-hover"
                                               id="tbl_PersonaNatural" align='center'>

                                            <thead>
                                                <tr>
                                                    <th>Nro Documento</th>
                                                    <th>Persona</th>
                                                    <th>Proveedor</th>
                                                    <th>Acciones</th>
                                                </tr>
                                            </thead>

                                            <tbody>

                                                <%
                                                    if (request.getParameter("btnBuscarPersona") != null) {
                                                        int criterio = Integer.parseInt(request.getParameter("optradio"));
                                                        String valor = request.getParameter("txtBuscarPersona");

                                                        for (PersonaNatural objPersonaNatural : PersonaNaturalNEG.Instancia().listarPersona(criterio, valor)) {
                                                            out.println("<tr>");
                                                            out.println("<td class='text-left' style='width: 50%'>" + objPersonaNatural.getNroDocumento() + "</td>");
                                                            out.println("<td class='text-left' style='width: 50%'>" + objPersonaNatural.getNombres() + "</td>");
                                                            out.println("<td class='text-left' style='width: 50%'>" + objPersonaNatural.getProveedor() + "</td>");
                                                %>
                                            <td class="text-center"
                                                data-id="<%=objPersonaNatural.getIdPersona()%>">
                                                <a href="javascript:;" class="btn btn-Lapislazuli btn-circle edit"
                                                   title="Editar"><i class="fa fa-edit"></i></a>
                                            </td>

                                            <%
                                                    }

                                                    out.print("<input type='hidden' name='variable' id='txtVariable' value='tab_BuscarPersona'>");

                                                }

                                            %>

                                            </tbody>
                                            <tfoot>
                                            <th>Nro Documento</th>
                                            <th>Persona</th>
                                             <th>Proveedor</th>
                                            <th>Acciones</th>

                                            </tfoot>

                                        </table>
                                    </div>


                                </div>
                            </div>
                            <div class="tab-pane fade in active" id="tabRegistrarPersona" name="tabRegistrarPersona">
                                <form class="form-horizontal" id="form-registrar" method="post"
                                      action="#tabRegistrarPersona">
                                    <legend class="subtitulos"> Datos Personales</legend>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Tipo Doc.</label>
                                                <div class="col-sm-7 ">
                                                    <% List<TipoDocIdentidad> listTipoDocIdentidad = TipoDocIdentidadNEG.Instancia().listarTipoDocIdentidad("N");
                                                        out.println("<select name='cboTipDocumento' id='cboTipDocumento'  data-placeholder='SELECCIONE UNA DOCUMENTO DE IDENTIDAD' class='simple-select2 form-control'>");
                                                        out.println("<option value=''></option>");
                                                        for (int i = 0; i < listTipoDocIdentidad.size(); i++) {
                                                            out.println("<option value='" + listTipoDocIdentidad.get(i).getIdTipoDocIdentidad() + "'>" + listTipoDocIdentidad.get(i).getDescripcion() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r" for="txtNumDocumento">Nro
                                                    Doc</label>

                                                <div class="col-sm-7">
                                                    <input type="hidden" name="txtIdPersona" id="txtIdPersona"
                                                           value="xxxxxxxx">
                                                    <input type="hidden" name="codDep" id="codDep" value="t">
                                                    <input type="hidden" name="codProv" id="codProv" value="t">
                                                    <input type="hidden" name="codDist" id="codDist" value="t">
                                                    <input class="form-control input-sm" name="txtNumDocumento"
                                                           id="txtNumDocumento" type="text"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r" for="txtApePaterno">Ape.
                                                    Paterno</label>

                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm" name="txtApePaterno"
                                                           id="txtApePaterno" type="text"></div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r" for="txtApeMaterno">Ape.
                                                    Materno</label>

                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm" name="txtApeMaterno"
                                                           id="txtApeMaterno" type="text"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r"
                                                       for="txtNombres">Nombres</label>

                                                <div class="col-sm-7">
                                                    <input class="form-control input-sm" id="txtNombres"
                                                           name="txtNombres" type="text"></div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per" id="data_3">
                                                <label class="col-sm-4 control-label m-r">Fecha Nac.</label>

                                                <div class="col-sm-7">
                                                    <div class="input-group date">
                                                        <input type="text" class="form-control" placeholder="DD/MM/YYYY"
                                                               name="txtFechaNac" id="txtFechaNac">
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-calendar"></i></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Sexo</label>

                                                <div class="col-sm-7 ">
                                                    <select class='simple-select form-control' name="cboSexPersona"
                                                            id="cboSexPersona">
                                                        <option value="default" option selected="selected">Seleccione un Tipo de Genero
                                                        </option>
                                                        <option value="M">MASCULINO</option>
                                                        <option value="F">FEMENINO</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Estado Civil</label>
                                                <div class="col-sm-7 ">
                                                    <select class='simple-select form-control' name="cboEstPersona"
                                                            id="cboEstPersona">
                                                        <option value="default" option selected="selected">Seleccione un Estado Civil </option>
                                                        <option value="0401">SOLTERO (A)</option>
                                                        <option value="0402">CASADO (A)</option>
                                                        <option value="0403">CONVIVIENTE</option>
                                                        <option value="0404">DIVORCIADO (A)</option>
                                                        <option value="0405">VIUDO(A)</option>
                                                        <option value="0406">SEPARADO</option>
                                                        <option value="0407">NO INDICA</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <legend class="subtitulos"> Informaci&oacuten del Contacto</legend>


                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Grado
                                                    Instrucci&oacuten</label>
                                                <div class="col-sm-7 ">
                                                    <select class="simple-select2 form-control"
                                                            name="cboGradoInstruccion" id="cboGradoInstruccion"
                                                            data-placeholder="SELECCIONE GRADO INSTRUCCI&Oacute;N">
                                                        <option value=""></option>
                                                        <option value="0501">SUPERIOR</option>
                                                        <option value="0502">PRIMARIA</option>
                                                        <option value="0503">SECUNDARIA</option>
                                                        <option value="0504">TECNICO</option>
                                                        <option value="0505">ILETRADO</option>
                                                        <option value="0506">NO INDICA</option>
                                                        <option value="0507">INICIAL</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Profesi&oacuten</label>
                                                <div class="col-sm-7 ">
                                                    <% List<Profesion> listProfesiones = ProfesionNEG.Instancia().listar();
                                                        out.println("<select name='cboProfesion' id='cboProfesion'  data-placeholder='SELECCIONE UNA PROFESI&Oacute;N' class='simple-select2 form-control'>");
                                                        out.println("<option value=''></option>");
                                                        for (int i = 0; i < listProfesiones.size(); i++) {
                                                            out.println("<option value='" + listProfesiones.get(i).getIdProfesion() + "'>" + listProfesiones.get(i).getProfesion() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %>

                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <legend class="subtitulos"> Datos del Contacto</legend>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Telefono</label>
                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm" name="txtTelefono"
                                                           id="txtTelefono" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Celular</label>
                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm " name="txtCelular"
                                                           id="txtCelular" type="text">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Email</label>
                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm " type="text" name="txtEmail"
                                                           id="txtEmail"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <legend class="subtitulos"> Datos Domiciliario</legend>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Pais</label>
                                                <div class="col-sm-7 ">
                                                    <Select class="simple-select2 form-control" name="idpaises"
                                                            id="idpaises" data-placeholder="SELECCIONE UN PA&Iacute;S">
                                                        <option value=""></option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Departamento</label>
                                                <div class="col-sm-7 ">
                                                    <select data-placeholder="SELECCIONE UN DEPARTAMENTO"
                                                            class="simple-select2 form-control" name="iddepartamento"
                                                            id="iddepartamento">
                                                        <option value=""></option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Provincia</label>
                                                <div class="col-sm-7 ">
                                                    <select data-placeholder="SELECCIONE UNA PROVINCIA"
                                                            class="simple-select2 form-control" name="idprovincias"
                                                            id="idprovincias">
                                                        <option value=""></option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Distrito</label>
                                                <div class="col-sm-7 ">
                                                    <select data-placeholder="SELECCIONE UNA DISTRITO"
                                                            class="simple-select2 form-control" name="iddistritos"
                                                            id="iddistritos">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Direccion</label>
                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm" name="txtDireccion"
                                                           id="txtDireccion" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Urbanizaci&oacuten</label>
                                                <div class="col-sm-7 ">
                                                    <%
                                                        out.println("<select data-placeholder='SELECCIONE UNA URBANIZACI&Oacute;N '   id='cboUrbanizacion' name='cboUrbanizacion' class='simple-select2 form-control'>");
                                                        out.println("<option value=''></option>");
                                                        for (Urbanizacion objUrbanizacion : UrbanizacionNEG.Instancia().listar()) {
                                                            out.println("<option value='" + objUrbanizacion.getCodigo() + "'>" + objUrbanizacion.getDescripcion() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row">

                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Referencia</label>
                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm" name="txtReferencia"
                                                           id="txtReferencia" type="text"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <legend class="subtitulos" > Datos Proveedor</legend>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Proveedor</label>
                                                <div class="col-sm-7 ">
                                                    <select class='simple-select form-control' name="cboProveedor"
                                                            id="cboProveedor">
                                                        <option value="0">NO</option>
                                                        <option value="1">SI</option>
                                                    </select>  
                                                </div>
                                            </div> 
                                        </div>
                                    </div>


                                    <div class="row" id="divProveedor" hidden="">

                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">RUS:</label>
                                                <div class="col-sm-7 ">
                                                    <input class="form-control input-sm" type="text" autocomplete="off" maxlength="11" 
                                                           name="txtRUS" id="txtRUS" onkeypress="return soloNumero(event);" >
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group reg_per">
                                                <label class="col-sm-4 control-label m-r">Rubro:</label>
                                                <div class="col-sm-7 ">
                                                    <%  List<Rubro> objRubro = RubroNEG.Instancia().obtenerRubro();
                                                        out.println("<select name='cboRubro' id='cboRubro' data-placeholder='Seleccione un Rubro' class='simple-select form-control'>");
                                                        for (int i = 0; i < objRubro.size(); i++) {
                                                            out.println("<option value='" + objRubro.get(i).getIdRubro() + "'>" + objRubro.get(i).getDescripcion() + "</option>");
                                                        }
                                                        out.println("</select>");
                                                    %> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br>

                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-sm-10 col-sm-offset-5">
                                                <button class="btn btn-white" type="button" onclick="btnlimpiar()">
                                                    Limpiar
                                                </button>
                                                <button class="btn btn-OceanBoatBlue" type="button" name="btnRegistrarPersona"
                                                        id="btnRegistrarPersona">Registrar
                                                </button>

                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- ---------end js -------------->
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionAfiliaciones/jsValidacionPersonaNatural.js"/>"
type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsAfiliaciones/jsPersonaNatural/jsPersonaNatural.js"/>"
type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsAfiliaciones/jsUbigeo/jsFuncionesUbigeo.js" />"
type="text/javascript"></script>

<!-- --------end body---------------------- -->
<jsp:include page="/WEB-INF/structure/footer.jsp"/>
