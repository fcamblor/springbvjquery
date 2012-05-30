(function( $ ){

    $.fn.inputsToJSON = function(){
        var jsonObject = {};
        this.each(function(){
            $(this).find(':input').each(function() {
                var name = $(this).attr('name');
                // If name is empty, we shouldn't set anything here ...
                if(!name){
                    return;
                }

                var value = null;
                switch(this.type) {
                    case 'select-multiple':
                    case 'select-one':
                    case 'password':
                    case 'text':
                    case 'textarea':
                    case 'radio':
                        value = $(this).val();
                        break;
                    case 'checkbox':
                        if($(field).val() === "on"){
                            value = this.checked;
                        } else {
                            value = this.checked?$(this).val():null;
                        }
                        break;
                }

                // Empty string will be considered as null value
                if(value === ""){
                    value = null;
                }

                var pathChunks = name.split(".");
                var currentNode = jsonObject;
                $.each(pathChunks, function(i, pathChunk){
                    // Leaf
                    if(i === pathChunks.length-1){
                        if(value !== null){
                            currentNode[pathChunk] = value;
                        }
                    // Node
                    } else {
                        if(!currentNode[pathChunk]){
                            currentNode[pathChunk] = {};
                        } /* else : pathChunk already created by a previous field, let's traverse it ! */
                        currentNode = currentNode[pathChunk];
                    }
                });
            });
        });
        return jsonObject;
    };

})( jQuery );