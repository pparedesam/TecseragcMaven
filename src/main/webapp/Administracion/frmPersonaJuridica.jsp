<%@page import="ll.negocio.administracion.RubroNEG"%>
<%@page import="ll.entidades.administracion.Rubro"%>
<%@page import="ll.negocio.administracion.TipoDocIdentidadNEG"%>
<%@page import="ll.entidades.administracion.TipoDocIdentidad"%>
<%@page import="ll.negocio.administracion.UrbanizacionNEG"%>
<%@page import="ll.negocio.administracion.PersonaJuridicaNEG"%>
<%@page import="ll.negocio.administracion.ActividadEconomicaNEG"%>
<%@page import="ll.entidades.administracion.PersonaJuridica"%>
<%@page import="ll.entidades.administracion.ActividadEconomica"%>
<%@page import="ll.entidades.administracion.Urbanizacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.AbstractList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />


<div class="row">
    <div class="col-md-12">
        <div class="ibox float-e-margins">           
            <div class="ibox-content">
                <div class="row">
                    <legend class="text-success">Mantenedor de Persona Jur&iacute;dica</legend>
                    <div class="tabs-container" id="tabs">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-RegistrarPerJur"><i class="glyphicon fa fa-edit "></i>REGISTRAR PERSONA JURIDICA</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-BuscarPerJur"><i class="glyphicon fa fa-binoculars"></i> LISTA PERSONA JURIDICA</a></li>
                        </ul> 
                        <div class="tab-content">
                            <div id="tab-RegistrarPerJur" class="tab-pane active">
                                <div class="panel-body">
                                    <div class="row center-block">
                                        <form id="form-registrarPerjur"  name="form-registrarPerjur" class="form-horizontal"  method="post" action="#">
                                            <legend class="subtitulos"> Datos de la Empresa</legend>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Tipo Doc.</label>
                                                        <div class="col-sm-7 ">
                                                            <% List<TipoDocIdentidad> listTipoDocIdentidad = TipoDocIdentidadNEG.Instancia().listarTipoDocIdentidad("J");
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
                                                        <label class="col-sm-4 control-label m-r" for="txtNroRuc" >RUC</label>

                                                        <div class="col-md-7 ">
                                                            <input class="form-control input-sm" name="txtNroRuc" id="txtNroRuc"type="text"></div>
                                                    </div>
                                                </div>
                                            </div> 
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r" for="txtRazonSocial">Razon Social</label>

                                                        <div class="col-md-7 ">
                                                            <input class="form-control input-sm" name="txtRazonSocial" id="txtRazonSocial" type="text"></div>
                                                        <input type="hidden" name="txtIdPersona" id="txtIdPersona" value="">
                                                        <input type="hidden" name="codDep" id="codDep" value="t">
                                                        <input type="hidden" name="codProv" id="codProv" value="t">
                                                        <input type="hidden" name="codDist" id="codDist" value="t">
                                                    </div>
                                                </div>   
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Giro del Negocio</label>
                                                        <div class="col-sm-7 ">
                                                            <%
                                                                List<ActividadEconomica> objActividadEconomicas = ActividadEconomicaNEG.Instancia().listarActividadEconomica();
                                                                out.println("<select name='cboGiroNegocio' id='cboGiroNegocio' data-placeholder='Seleccione un Giro del Negocio' class='simple-select form-control'>");
                                                                out.println("<option value='default'>Seleccione una opcion</option>");
                                                                for (int i = 0; i < objActividadEconomicas.size(); i++) {
                                                                    out.println("<option value='" + objActividadEconomicas.get(i).getIdActEcon() + "'>" + objActividadEconomicas.get(i).getDescripcion() + "</option>");
                                                                }
                                                                out.println("</select>");
                                                            %> 
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per" id="data_3">
                                                        <label class="col-sm-4 control-label m-r">Fecha de Constitucion</label>

                                                        <div class="col-sm-7">
                                                            <div class="input-group date">                                                                
                                                                <input type="text" class="form-control"  placeholder="DD/MM/YYYY"  name="txtFechaCons" id="txtFechaCons">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                            </div>                    

                                            <!-- <div class="row">
 
 
                                                 <div class="col-lg-6">
                                                     <div class="form-group reg_per">
                                                         <label class="col-sm-4 control-label m-r" for="txtIngreso">Ingreso Mensual</label>
 
                                                         <div class="col-sm-7">
                                                             <input class="form-control input-sm" name="txtIngreso" id="txtIngreso" type="text"></div>
                                                     </div>
                                                 </div>
 
                                             </div>-->

                                            <legend class="subtitulos"> Datos Domiciliario</legend>

                                            <div class="row">

                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Pais</label>
                                                        <div class="col-sm-7 ">
                                                            <Select class="simple-select form-control" name="idpaises" id="idpaises"  data-placeholder="[Seleccione un Pais]">                               
                                                                <option value="" ></option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Departamento</label>
                                                        <div class="col-sm-7">
                                                            <select  class="simple-select form-control" name="iddepartamento" id="iddepartamento" data-placeholder="[Seleccione un Departamento]">
                                                                <option value=""></option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div> 

                                            </div>  

                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per" >
                                                        <label class="col-sm-4 control-label m-r">Provincia</label>
                                                        <div class="col-sm-7 ">
                                                            <select data-placeholder="Seleccione una Provincia" class="simple-select form-control" name="idprovincias" id="idprovincias">
                                                                <option value=""></option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Distrito</label>
                                                        <div class="col-sm-7 ">
                                                            <select data-placeholder="Seleccione una Distrito" class="simple-select form-control"  name="iddistritos" id="iddistritos">
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
                                                            <input class="form-control input-sm" name="txtDireccion"  id="txtDireccion" type="text">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Urbanizacion</label>
                                                        <div class="col-sm-7 ">                                  
                                                            <%
                                                                List<Urbanizacion> listUrbanizacion = UrbanizacionNEG.Instancia().listar();
                                                                out.println("<select data-placeholder='SELECCIONE UNA URBANIZACI&Oacute;N '   id='cboUrbanizacion' name='cboUrbanizacion' class='simple-select2 form-control'>");
                                                                out.println("<option value='default'></option>");
                                                                for (int i = 0; i < listUrbanizacion.size(); i++) {
                                                                    out.println("<option value='" + listUrbanizacion.get(i).getCodigo() + "'>" + listUrbanizacion.get(i).getDescripcion() + "</option>");
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
                                                            <input class="form-control input-sm" name="txtReferencia" id="txtReferencia" type="text"></div>
                                                    </div>
                                                </div>
                                            </div> 

                                            <legend class="subtitulos"> Datos de Contacto</legend>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Telefono</label>
                                                        <div class="col-sm-7 ">
                                                            <input class="form-control input-sm" name="txtTelefono" id="txtTelefono" type="text">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Celular</label>
                                                        <div class="col-sm-7 ">
                                                            <input class="form-control input-sm " name="txtCelular" id="txtCelular" type="text">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> 

                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Email</label>
                                                        <div class="col-sm-7 ">
                                                            <input class="form-control input-sm " type="text" name="txtEmail" id="txtEmail"  ></div>
                                                    </div>
                                                </div>
                                            </div> 
                                            <legend class="subtitulos"> Datos Proveedor</legend>

                                            <div class="row" >
                                                <div class="col-lg-6">
                                                    <div class="form-group reg_per">
                                                        <label class="col-sm-4 control-label m-r">Proveedor</label>
                                                        <div class="col-sm-7 ">
                                                            <select class='simple-select form-control calcular' name="cboProveedor"
                                                                    id="cboProveedor">
                                                                <option value="0">NO</option>
                                                                <option value="1">SI</option>
                                                            </select>  
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="col-lg-6" id="divRubroProv" hidden="">
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
                                                <div class="form-group ">
                                                    <div class="col-sm-10 col-sm-offset-5">
                                                        <button class="btn btn-white" type="button" onclick="btnlimpiar()">Limpiar</button>
                                                        <button class="btn btn-OceanBoatBlue" type="button" name="btnRegistrarPersona" id="btnRegistrarPersona">Registrar</button>

                                                    </div>
                                                </div>                                        
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>

                            <div id="tab-BuscarPerJur" class="tab-pane ">
                                <div class="panel-body">
                                    <form class="form-horizontal" id="form-buscar-PerJur"  name="form-buscar-PerJur" method="post">
                                        <div class="row">
                                            <div class="well col-md-11" style="margin-left: 40px; margin-right: 20px">
                                                <div class="col-md-5">
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label " for="txtBuscarPersona">Datos Personales</label>
                                                        <div class="col-md-8 no-padding" >
                                                            <input class="form-control m-t-sm input"  name="txtBuscarPersona" id="txtBuscarPersona" type="text">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-5 m-t-sm ">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="optradio" id="optradio" value="1">RUC
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="optradio" id="optradio" value="2" checked="">Razon Social
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="optradio" id="optradio" value="3">Nro. Tarjeta Elec.
                                                    </label>
                                                </div>
                                                <div class="col-md-2 m-t-sm" >
                                                    <button class="btn btn-OceanBoatBlue" type="submit" id="btnBuscarPersona" name="btnBuscarPersona" >Buscar</button>
                                                </div>                                        
                                            </div>
                                        </div>
                                    </form>
                                    <div class="row">

                                        <div class="col-lg-8 col-md-offset-2" >
                                            <table class="table table-striped table-bordered table-hover" id="tbl_PersonaJuridica" align='center'  >

                                                <thead>
                                                    <tr>
                                                        <th>Ruc</th>
                                                        <th>Razon Social</th>
                                                        <th>Proveedor</th>
                                                        <th>Acciones</th>
                                                    </tr>
                                                </thead>

                                                <tbody>

                                                    <%
                                                        if (request.getParameter("btnBuscarPersona") != null) {
                                                            int criterio = Integer.parseInt(request.getParameter("optradio"));
                                                            String valor = request.getParameter("txtBuscarPersona");

                                                            List<PersonaJuridica> listaPersonaJuridicas = PersonaJuridicaNEG.Instancia().listarPersona(criterio, valor);

                                                            for (int i = 0; i < listaPersonaJuridicas.size(); i++) {

                                                                out.println("<tr>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaPersonaJuridicas.get(i).getNroDocumento() + "</td>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaPersonaJuridicas.get(i).getNombres() + "</td>");
                                                                out.println("<td class='text-left' style='width: 50%'>" + listaPersonaJuridicas.get(i).getProveedor() + "</td>");

                                                    %> 
                                                <td class="text-center" data-id="<%=listaPersonaJuridicas.get(i).getIdPersona()%>" >
                                                    <a href="javascript:;" class="btn btn-Lapislazuli btn-circle edit" title="Editar"><i class="fa fa-edit"></i></a>                      
                                                </td>

                                                <%
                                                        }

                                                        out.print("<input type='hidden' name='variable' id='txtVariable' value='tab-BuscarPerJur'>");

                                                    }

                                                %>

                                                </tbody>  
                                                <tfoot>
                                                <th>Ruc</th>
                                                <th>Razon Social</th>
                                                <th>Proveedor</th>
                                                <th>Acciones</th>
                                                </tfoot>

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
    <script src="<c:url value="/resources/jsvalidaciones/jsValidacionAfiliaciones/jsValidacionPersonaJuridica.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jsAfiliaciones/jsPersonaJuridica/jsPersonaJuridicaMetodos.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jsAfiliaciones/jsPersonaJuridica/jsPersonaJuridica.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jsAfiliaciones/jsUbigeo/jsFuncionesUbigeo.js" />" type="text/javascript"></script>
    <jsp:include page="/WEB-INF/structure/footer.jsp" />
