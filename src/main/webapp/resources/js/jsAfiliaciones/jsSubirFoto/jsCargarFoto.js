          function readFoto(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#foto')
                        .attr('src', e.target.result)
                        .width(300)
                        .height(200);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
     
     
     function readFirma(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#firma')
                        .attr('src', e.target.result)
                        .width(300)
                        .height(200);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }