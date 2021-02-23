$(".Logout").click(function () {
   
    var success = function (responseText) {

    };
    var error = function (data, status, er)
    {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sUsuario?action=logout", data, success, error);

});