
<%@page import="ll.negocio.administracion.OcupacionNEG"%>
<%@page import="ll.entidades.administracion.Ocupacion"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/structure/template.jsp" />
<jsp:include page="/WEB-INF/structure/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- --------begin body---------------------- -->

<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <!-- <div class="ibox-title"></div>-->
            <div class="ibox-content">
                <legend class="text-success">MANTENEDOR DE OCUPACIONES</legend>

                <table class="table table-striped table-bordered table-hover" id="tblOcupacion">
                    <button type="button" class="btn btn-OceanBoatBlue" name="btnAdd" id="btnAdd"> Agregar</button>                            
                    <thead>
                        <tr>                                    
                            <th>Ocupacion</th>                                    
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>

                        <%
                            List<Ocupacion> lstOcupaciones = OcupacionNEG.Instancia().listar();
                            for (int i = 0; i < lstOcupaciones.size(); i++) {

                                out.println("<tr>");
                                out.println("<td class='text-left'>" + lstOcupaciones.get(i).getDescripcion() + "</td>");
                                if (lstOcupaciones.get(i).getEstado().equalsIgnoreCase("1")) {
                                    out.println("<td class='text-center'><i class='fa fa-check-square-o'></i></td>");
                                } else {
                                    out.println("<td class='text-center'><i class='fa fa-square-o'></i></td>");
                                }
                        %> 
                    <td class="text-center" data-id="<%=lstOcupaciones.get(i).getCodigo()%>" >
                        <a href="javascript:;" class="btn btn-Lapislazuli btn-circle edit" title="Editar"><i class="fa fa-edit"></i></a>                       

                    </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>  

                </table>
            </div>
        </div>
    </div>
</div>



<!-- --------end body---------------------- -->
<script src="<c:url value="/resources/js/jsOcupacion.js"/>" type="text/javascript"></script>
<jsp:include page="/WEB-INF/structure/footer.jsp" />
