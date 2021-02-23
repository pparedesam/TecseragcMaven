<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ICON -->
        <link  href="<c:url value="/resources/icon/favicon.ico"/>" rel="shortcut icon" type="image/gif">
        <link  href="<c:url value="/resources/icon/favicon.ico"/>" rel="icon" type="image/gif">

        <title> C.C. LLacuabamba | Intranet </title>        

        <!-- CSS -->
        <!-- BOOTSTRAP + INSPINIA  +FONT-AWESOME+PROPIOS-->

        <link href="<c:url value="/resources/inspinia-master/css/bootstrap.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/botones.css" />" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/resources/inspinia-master/css/estilos.css" />" rel="stylesheet" type="text/css"/>   
        <link href="<c:url value="/resources/inspinia-master/font-awesome/css/font-awesome.css" />" rel="stylesheet" type="text/css"/>        
        <link href="<c:url value="/resources/inspinia-master/css/animate.css" />" rel="stylesheet" type="text/css"/>        
        <link href="<c:url value="/resources/inspinia-master/css/build.css"/>" rel="stylesheet" type="text/css"/>  
        <link href="<c:url value="/resources/inspinia-master/css/style.css" />" rel="stylesheet" type="text/css"/> 
        <link href="<c:url value="/resources/inspinia-master/css/plugins/jasny/jasny-bootstrap.min.css" />" rel="stylesheet" type="text/css"/>       


        <!--BOOTSTRAP-SELECT--->
        <link href="<c:url value="/resources/inspinia-master/css/plugins/select2/select2.css"/>" rel="stylesheet" type="text/css"/>

        <!--CROPPER--->
        <link href="<c:url value="/resources/inspinia-master/css/cropper.css"/>" rel="stylesheet" type="text/css"/>



        <!--JQUERY-->  
        <link href="<c:url value="/resources/inspinia-master/css/jquery-ui.css"/>" rel="stylesheet" type="text/css"/>

        <!-- DATATABLES -->
        <link href="<c:url value="/resources/inspinia-master/css/plugins/dataTables/dataTables.bootstrap.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/plugins/dataTables/dataTables.responsive.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/plugins/dataTables/dataTables.tableTools.min.css" />" rel="stylesheet" type="text/css"/>

        <!--TOASTR-->    
        <link href="<c:url value="/resources/inspinia-master/css/plugins/toastr/toastr.min.css" />" rel="stylesheet" type="text/css"/>

        <!-- CHOSEN -->
        <link href="<c:url value="/resources/inspinia-master/css/plugins/chosen/chosen.css" />" rel="stylesheet" type="text/css"/>

        <!----ICHECK--------->
        <link href="<c:url value="/resources/inspinia-master/css/plugins/iCheck/custom.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/plugins/iCheck/skins/all.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/plugins/iCheck/normalize.css" />" rel="stylesheet" type="text/css"/>        


        <!-- STEPS -->
        <link href="<c:url value="/resources/inspinia-master/css/plugins/steps/jquery.steps.css" />" rel="stylesheet" type="text/css"/>

        <!--DATEPICKER3-->
        <link href="<c:url value="/resources/inspinia-master/css/plugins/datepicker/datepicker3.css" />" rel="stylesheet" type="text/css"/>




        <!-- JS -->

        <!--JQUERY-->          
        <script src="<c:url value="/resources/inspinia-master/js/jquery-3.3.1.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/jquery-2.2.4.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/jquery-ui.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/jquerysession.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/popper.min.js" />" type="text/javascript"></script>

        <!--CLOCK PICKER-->        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/clockpicker/clockpicker.js" />" type="text/javascript"></script>

        <!-- BOOTSTRAP + INSPINIA -->
        <script src="<c:url value="/resources/inspinia-master/js/bootstrap.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/inspinia.js" />" type="text/javascript"></script>

        <!-- DATATABLES -->        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/dataTables/jquery.dataTables.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/dataTables/dataTables.bootstrap.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/dataTables/dataTables.responsive.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/dataTables/dataTables.tableTools.min.js" />" type="text/javascript"></script>

        <!--CLOCK PICKER-->  
        <link href="<c:url value="/resources/inspinia-master/css/plugins/clockpicker/clockpicker.css" />" rel="stylesheet" type="text/css"/>

        <!--TOASTR-->             
        <script src="<c:url value="/resources/inspinia-master/js/plugins/toastr/toastr.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/toastr/jsToast.js"/>" type="text/javascript"></script>

        <!-- BOOTBOX -->        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/bootbox.min.js" />" type="text/javascript"></script>

        <!-- JQUERY VALIDATE -->
        <script src="<c:url value="/resources/inspinia-master/js/plugins/jquery-validation/jquery.validate.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/jquery-validation/additional-methods.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/jquery-validation/messages_es.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/jquery-validation/messages_es_PE.js" />" type="text/javascript"></script>

        <!-- CHOSEN -->        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/chosen/chosen.jquery.js" />" type="text/javascript"></script>

        <!-- MOMENT -->
        <script src="<c:url value="/resources/inspinia-master/js/plugins/moment.js" />" type="text/javascript"></script>


        <!----ICHECK--------->        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/iCheck/icheck.min.js" />" type="text/javascript"></script>

        <!-- STEPS -->        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/steps/jquery.steps.min.js" />" type="text/javascript"></script>

        <!--DATEPICKER3-->        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/datepicker/bootstrap-datepicker.js" />" type="text/javascript"></script>

        <!--METISMENU + SLIMSCROLL + PACE + JASNY -->   
        <script src="<c:url value="/resources/inspinia-master/js/plugins/metisMenu/jquery.metisMenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/jasny/jasny-bootstrap.min.js"/>" type="text/javascript"></script>        
        <script src="<c:url value="/resources/inspinia-master/js/plugins/slimscroll/jquery.slimscroll.min.js"/>" type="text/javascript"></script>      
        <script src="<c:url value="/resources/inspinia-master/js/plugins/pace/pace.min.js" />" type="text/javascript"></script>

        <!--BOOTSTRAP-SELECT-->   
        <script src="<c:url value="/resources/inspinia-master/js/plugins/select2/select2.full.min.js"/>" type="text/javascript"></script>       

        <!----Implementadas------->
        <script src="<c:url value="/resources/jsvalidaciones/jsFuncionesValidacion.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jsFuncionesGlobales.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/jsvalidaciones/jsValidacionMetodos.js" />" type="text/javascript"></script>       
        <script src="<c:url value="/resources/js/metodos.js" />" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jsBitacora/jsBitacora.js" />" type="text/javascript"></script>              
        <script src="<c:url value="/resources/js/servicios.js" />" type="text/javascript"></script>  

        <link href="<c:url value="/resources/inspinia-master/css/plugins/blueimp/css/blueimp-gallery.min.css"/>" rel="stylesheet" type="text/css"/>
        <script src="<c:url value="/resources/inspinia-master/js/plugins/blueimp/jquery.blueimp-gallery.min.js" />" type="text/javascript"></script>          

        <!----bootstrap-select-------> 

        <script type="text/javascript">
            var base_url = "<c:url value="" />";

            $(".simple-select").select2({minimumResultsForSearch: Infinity});
            $(".simple-select2").select2();

        </script>



    </head>
    <body>
        <div id="contenedor_carga" class="preloaderPersonalizado hidden">
            <div id="carga">
            </div>
        </div>
        <jsp:include page="/WEB-INF/structure/menu.jsp"/>
