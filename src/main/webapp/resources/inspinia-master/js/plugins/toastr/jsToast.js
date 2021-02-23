var toast = function (shortCutFunction,mensaje,title) {
    toastr.options = {
    closeButton: true,
    debug: false,
    progressBar: true,
    preventDuplicates: true,
    positionClass: "toast-top-center",
    showDuration: "400",
    hideDuration: "1000",
    timeOut: "5000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
    };
    var $toast = toastr[shortCutFunction](mensaje, title);
};
