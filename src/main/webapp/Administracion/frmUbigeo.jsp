<%@page import="ll.entidades.administracion.Ubigeo"%>
<%@page import="ll.negocio.administracion.UbigeoNEG"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />

<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="ibox float-e-margins">           
            <div class="ibox-content">
                <div class="row">
                    <legend class="text-success">Mantenedor de Ubigeos </legend>


                    <div class="tabs-container" id="tabs">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-BuscarUbigeo"><i class="glyphicon fa fa-binoculars"></i> LISTA UBIGEOS</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-RegistrarUbigeo"><i class="glyphicon fa fa-edit "></i>REGISTRAR UBIEGO</a></li>
                        </ul> 
                        <div class="tab-content">
                            <div id="tab-BuscarUbigeo" class="tab-pane active">
                                <div class="panel-body">
                                    <div class="row">

                                        <div class="col-lg-10 col-md-offset-1" >
                                            <table class="table table-striped table-bordered table-hover" id="tbl_Ubigeo" align='center'  >

                                                <thead>
                                                    <tr>
                                                        <th>DESCRIPCION</th>
                                                        <th>NIVEL</th>
                                                        <th>UBIGEO</th>
                                                        <th>Acciones</th>
                                                    </tr>
                                                </thead>

                                                <tbody>

                                                    <%

                                                        List<Ubigeo> listUbigeo = UbigeoNEG.Instancia().listarUbigeo();

                                                        for (int i = 0; i < listUbigeo.size(); i++) {
                                                            int nivel = Integer.parseInt(listUbigeo.get(i).getNivel());
                                                            out.println("<tr>");
                                                            out.println("<td class='text-left' style='width: 25%'>" + listUbigeo.get(i).getDescripcion() + "</td>");
                                                            out.println("<td class='text-left' style='width: 10%' data-nivel='" + nivel + "'>" + listUbigeo.get(i).NombreNivel() + "</td>");
                                                            out.println("<td class='text-left' style='width: 50%'>" + listUbigeo.get(i).getUbigeo() + "</td>");


                                                    %> 
                                                <td class="text-center" data-id="<%=listUbigeo.get(i).getCodigo()%>" >


                                                    <% if (Integer.parseInt(listUbigeo.get(i).getNivel()) != 3) {%>                                          
                                                    <a href="javascript:;" class="btn btn-HippieGreen btn-circle add" title="Agregar"><i class="fa fa-plus"></i></a>                      
                                                        <%}%>
                                                    <a href="javascript:;"  class="btn btn-Lapislazuli btn-circle edit" title="Editar"><i class="fa fa-edit"></i></a>   

                                                </td>

                                                <%
                                                        out.println("</tr>");
                                                    }

                                                %>

                                                </tbody>  
                                                <tfoot>
                                                <th>DESCRIPCION</th>
                                                <th>NIVEL</th>
                                                <th>UBIGEO</th>
                                                <th>Acciones</th>

                                                </tfoot>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="tab-RegistrarUbigeo" class="tab-pane ">
                                <div class="panel-body">
                                    <form class="form-horizontal" id="form-registrar"  name="form-registrar" method="post">
                                        <div class="row col-lg-8 col-md-offset-2">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label " for="txtUbigeo">Ubigeo</label>
                                                <div class="col-md-8 no-padding" >
                                                    <input class="form-control m-t-sm input"  name="txtAccion"  id="txtAccion" value="registrar" type="hidden">
                                                    <input class="form-control m-t-sm input"  name="txtUbigeo"  id="txtUbigeo" value="" disabled="" type="text">                                        
                                                    <input class="form-control m-t-sm input"  name="txtCodigo"  id="txtCodigo" value=""  type="hidden">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label " for="txtNivel">Nivel</label>
                                                <div class="col-md-8 no-padding" >
                                                    <input class="form-control m-t-sm input"  name="txtNivel"   id="txtNivel" value="PAIS" disabled="" type="text">
                                                    <input class="form-control m-t-sm input"  name="txtIdNivel"   id="txtIdNivel"  value="0"  type="hidden">
                                                </div>
                                            </div>




                                            <div class="form-group">
                                                <label class="col-md-3 control-label " for="txtDescripcion">Descripcion</label>
                                                <div class="col-md-8 no-padding" >
                                                    <input class="form-control m-t-sm input"  name="txtDescripcion" id="txtDescripcion" type="text">
                                                </div>
                                            </div>
                                            <div class="col-md-12 col-md-offset-4">
                                                <div class="form-group">
                                                    <button class="btn btn-OceanBoatBlue" type="submit" id="btnRegistrar" name="btnRegistrar" >Registrar</button>
                                                    <button class="btn btn-white" type="button" onclick="btnlimpiar()">Cancelar</button>
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
</div>
<script src="<c:url value="/resources/js/jsAfiliaciones/jsUbigeo/jsUbigeoTable.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jsAfiliaciones/jsUbigeo/jsUbigeo.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/jsvalidaciones/jsValidacionAfiliaciones/jsValidacionUbigeo.js"/>" type="text/javascript"></script>
<jsp:include page="/WEB-INF/structure/footer.jsp" />
