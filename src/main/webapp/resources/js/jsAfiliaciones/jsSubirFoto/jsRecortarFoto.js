var canvas  = $("#canvas"),
    context = canvas.get(0).getContext("2d"),
    $result = $('#result');

$('#fileInput').on( 'change', function(){

    if (this.files && this.files[0]) {
      if ( this.files[0].type.match(/^image\//) ) {
        var reader = new FileReader();
        reader.onload = function(evt) {
           var img = new Image();
           img.onload = function() {
             context.canvas.height = img.height;
             context.canvas.width  = img.width;
             context.drawImage(img, 0, 0);
               canvas.cropper('destroy');
             var cropper = canvas.cropper({
               aspectRatio: 4 / 3
             });
             $('#btnCrop').click(function() {
                // Get a string base 64 data url
                var croppedImageDataURL =  canvas.cropper('getCroppedCanvas').toDataURL("image/png"); 
                $result.empty();
                $result.append( $('<img>').attr('src', croppedImageDataURL) );
             });
             $('#btnRestore').click(function() {
             
                  
             });
           };
           img.src = evt.target.result;
				};
            reader.readAsDataURL(this.files[0]);
      }
      else {
        alert("Invalid file type! Please select an image file.");
      }
    }
    else {
      alert('No file(s) selected.');
    }
});

