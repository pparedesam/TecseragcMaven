var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tbl_Ubigeo");

    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });
    oTable.on("click", ".add", function () {
        fn_Add(this);
    });
    oTable.on("click", ".cancel", function () {
        fn_Cancel(this);
    });
    oTable.on("click", ".save", function () {
        fn_save(this);
    });


});

var DescripcionAnterior;
var fn_save = function (elem) {

    var par = $(elem).parent().parent();
    var tdDescripcion = par.children("td:nth-child(1)");
    var tdNivel = par.children("td:nth-child(2)");
    var id_nivel = tdNivel.attr('data-nivel');
    console.log(id_nivel);
    var tdUbigeo = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var result = validar(tdDescripcion);

    if (result === true)
    {
        var data = {'codigo': id_codigo, 'descripcion':$.trim(tdDescripcion.children("input[type=text]").val()),'nivel' : id_nivel,
                    'LastUbigeo' : $.trim(DescripcionAnterior),'ubigeo' :$.trim(tdUbigeo.html())};

        var success = function (res) {

            if (res.Result === 'ok')
            {
                tdDescripcion.html(tdDescripcion.children("input[type=text]").val());
                tdbuttons.html('<a class="btn btn-primary btn-circle edit"><i class="fa fa-edit"></i></a>');
                tdbuttons.attr("data-id", res.codigo);
                toast('success',res.Message,'Exito!');
                setTimeout(function () {
                    window.location.reload(1);
                }, 1000);

            }
            else 
            {
                toast('error', res.Message, 'Error');
            }
        };
        var error = function (res) {
            toast('error', res.Message, 'Error');
        };

        fn_callmethod("../sUbigeo?action=editar", data, success, error);

    } else
    {
        toast('error', 'El nombre del ubigeo debe tener un mínimo 4 caracteres', 'ERROR');
    }
};

function fn_Add(elem) {

    var par = $(elem).parent().parent();
    var tdDescripcion = par.children("td:nth-child(1)");
    var tdNivel = par.children("td:nth-child(2)");
    var tdUbigeo = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var id_nivel =  tdNivel.attr('data-nivel') === undefined ? 0 : tdNivel.attr('data-nivel');
    var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    
    $("#txtAccion").val("agregar");
    $("#txtCodigo").val(id_codigo);
    $("#txtUbigeo").val($.trim(tdUbigeo.html()));
    $("#txtIdNivel").val(id_nivel);
    
    
    switch (parseInt(id_nivel)) {
        case 0:
            $("#txtNivel").val("Departamento");
            break;
        case 1:
            $("#txtNivel").val("Provincia");
            break;
        case 2:
            $("#txtNivel").val("Distrito");
            
    }

    activaTab('tab-RegistrarUbigeo');

}
;

function fn_Edit(elem) {
    
    var par = $(elem).parent().parent();
    var tdDescripcion = par.children("td:nth-child(1)");
    var tdNivel = par.children("td:nth-child(2)");
    var id_nivel =  tdNivel.attr('data-nivel') === undefined ? 0 : tdNivel.attr('data-nivel');
    var tdUbigeo = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
   
   DescripcionAnterior =tdDescripcion.html();
   tdDescripcion.html("<input type='text' id='txtDescripcion' class='form-control input-sm' style='width: 100%' value='" +$.trim(tdDescripcion.html()) + "'/>");
   //tdUbigeo.html("<input type='text' id='txtfactor' class='form-control input-sm' style='width: 100%' value='" + tdUbigeo.html() + "'/>");
   tdbuttons.html('<a class="btn btn-primary btn-circle save"><i class="fa fa-save " title="Guardar"></i></a><a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');           
   
};
var fn_Cancel = function (elem) {


    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    var id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
       
        var par = $(elem).parent().parent();
        var tdDescripcion = par.children("td:nth-child(1)");
//        var tdNivel = par.children("td:nth-child(2)");
//        var tdUbigeo = par.children("td:nth-child(3)");
        var tdbuttons = par.children("td:nth-child(4)");

        tdDescripcion.html(tdDescripcion.children("input[type=text]").val());  
     //   tdUbigeo.html(tdUbigeo.children("input[type=text]").val());
        tdbuttons.html('<a class="btn btn-primary btn-circle edit"><i class="fa fa-edit"></i></a>');              
     
};

function activaTab(tab){
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
};


function validar(descripcion)
{
    var valor;
    var data =  descripcion.children("input[type=text]").val().length;
    
    if (data >=4 )
    {
         valor=true;
    }
    else valor=false;
    
    return valor;
}
