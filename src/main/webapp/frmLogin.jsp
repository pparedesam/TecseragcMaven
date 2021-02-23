<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>TECSERAGC</title>

        <link href="<c:url value="/resources/inspinia-master/css/bootstrap.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/font-awesome/css/font-awesome.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/animate.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/botones.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/inspinia-master/css/style.css" />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/resources/icon/favicon.ico"/>" rel="shortcut icon" type="image/gif" >
        <link href="<c:url value="/resources/icon/favicon.ico"/>" rel="icon" type="image/gif" >


    </head>

    <body class="gray-bg">

        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <div>
                <div>
                    <h1 class="logo-name"> <img src="resources/imagenes/logo_empresa.png" style="width:100%"></h1>
                </div>

                <h3>TECSERAGC</h3>
                <p>Ingreso al Sistema</p>
                <form class="form-horizontal" id="form-login" name="form-login" method="post" autocomplete="off">
                    <div class="m-t">

                        <div class="form-group">
                            <input type="text" name="txtUsuario" id="txtUsuario" class="form-control" placeholder="Username"
                                   required="" value=""autocomplete="off">
                        </div>
                        <div class="form-group">
                            <input type="password" name="txtClave" id="txtClave" class="form-control" value=""
                                   placeholder="Password" required="" autocomplete="off">
                        </div>

                        <button type="submit" name="btnIngresar" id="btnIngresar" class="btn btn-OceanBoatBlue block full-width m-b">
                            Login
                        </button>
                    </div>
                    <div  id="tabla" class="table-responsive text-navy">
                    </div>
                </form>

                <p class="m-t">
                    <small>© 2021 Alfacorp Peru</small>
                </p>
                <p class="m-t">
                    <small>Todos los derechos reservados.</small>
                </p>

            </div>
        </div>
    </body>

    <script src="<c:url value="/resources/inspinia-master/js/jquery-2.2.4.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/inspinia-master/js/bootstrap.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/servicios.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jsUsuario.js" />" type="text/javascript"></script>
</html>

