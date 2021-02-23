var canvasf = $("#canvasfoto"),
        contextf = canvasf.get(0).getContext("2d"),
        $resultf = $('#resultfoto'),
        canvas = $("#canvasfirma"),
        context = canvas.get(0).getContext("2d"),
        $result = $('#resultfirma');

var cropperBandera = -1;
var croppedImageDataURLFoto;
var croppedImageDataURLFirma;

$('#fileInputFoto').on('change', function () {

    if (this.files && this.files[0]) {
        if (this.files[0].type.match(/^image\//)) {
            var readerf = new FileReader();
            readerf.onload = function (evt) {
                var imgf = new Image();
                imgf.onload = function () {

                    contextf.canvas.height = 400;
                    contextf.canvas.width = 400;

                    contextf.drawImage(imgf, 0, 0, imgf.width, imgf.height, 0, 0, 300, 400);

                    canvasf.cropper('destroy');
                    cropperBandera = 1;
                    var cropperf = canvasf.cropper({
                        aspectRatio: 16 / 9

                    });
                    $('#btnCortarFoto').click(function () {
                        // Get a string base 64 data url
                        croppedImageDataURLFoto = canvasf.cropper('getCroppedCanvas').toDataURL("image/png");
                        $resultf.empty();
                        $resultf.append($('<img class="img-thumbnail">').attr('src', croppedImageDataURLFoto));



                    });

                };
                imgf.src = evt.target.result;
            };
            readerf.readAsDataURL(this.files[0]);
        } else {
            alert("Invalid file type! Please select an image file.");
        }
    } else {


        canvasf.cropper('destroy');
        canvasf.clearRect(0, 0, contextf.canvas.width, contextf.canvas.height);
        $resultf.empty();
    }
});



$('#fileInputFirma').on('change', function () {

    if (this.files && this.files[0]) {
        if (this.files[0].type.match(/^image\//)) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                var img = new Image();
                img.onload = function () {
                    context.canvas.height = 400;
                    context.canvas.width = 400;


                    context.drawImage(img, 0, 0, img.width, img.height, 0, 0, 300, 400);

                    canvas.cropper('destroy');
                    cropperBandera = 2;
                    var cropper = canvas.cropper({
                        aspectRatio: 16 / 9

                    });
                    $('#btnCortarFirma').click(function () {
                        // Get a string base 64 data url
                        croppedImageDataURLFirma = canvas.cropper('getCroppedCanvas').toDataURL("image/png");

                        $result.empty();
                        $result.append($('<img class="img-thumbnail">').attr('src', croppedImageDataURLFirma));

                    });

                };
                img.src = evt.target.result;
            };
            reader.readAsDataURL(this.files[0]);
        } else {
            alert("Invalid file type! Please select an image file.");
        }
    } else {
        alert('No file(s) selected.');
    }
});




