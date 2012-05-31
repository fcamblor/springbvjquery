(function( $ ){

    /**
     * Goal of this method is to retrieve every input fields located inside current selector(s),
     * retrieve their values and create a JSON object with them
     * Note that if input field name has "dots" (example : "foo.bar.baz"), it will be transformed into
     * a hierarchical JSON representation ({ foo: { bar: { baz: "value" } } })
     * If several fields has the same name, a value array will be created
     */
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
                        // If leaf already exists...
                        if(currentNode[pathChunk]){
                            // .. and it's not yet an array, we should transform it into an array !
                            if(!$.isArray(currentNode[pathChunk])){
                                currentNode[pathChunk] = [ currentNode[pathChunk] ];
                            }
                            // then append current value to the array's values
                            currentNode[pathChunk].push(value);
                        } else {
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