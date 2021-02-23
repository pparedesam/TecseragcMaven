function comboParentesco(id) {    
    
    var success = function (data) {  
            var option = "<option value='default'  option selected='selected'>Seleccione un Parentesco</option>";
            var tam = data.ListaParentesco.length;
            $("#" + id).append(option);
             
            for (var i = 0; i < tam; i++){    
                var option = $(document.createElement('option'));
                option.text(data.ListaParentesco[i].Parentesco);
                option.val(data.ListaParentesco[i].IdParentesco);
                $("#" + id).append(option);
            }
        
    };
    var error = function (data, status, er){        
    };
    fn_callmethod("../sParentesco?action=obtenercboParentesco",'', success, error); 
};