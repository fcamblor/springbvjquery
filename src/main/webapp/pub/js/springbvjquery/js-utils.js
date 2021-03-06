(function( $ ){

    /**
     * Goal of this method is to retrieve every input fields located inside current selector(s),
     * retrieve their values and create a JSON object with them
     * Note that if input field name has "dots" (example : "foo.bar.baz"), it will be transformed into
     * a hierarchical JSON representation ({ foo: { bar: { baz: "value" } } })
     * If several fields has the same name, a value array will be created
     * jsfiddle allowing to test it : http://jsfiddle.net/fcamblor/MLf7c/#base
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
                    case 'text':
                        // Special case handling jquery datepickers
                        if($(this).hasClass("datepicker")){
                            value = $(this).datepicker("getDate");
                            break;
                        }
                    case 'select-multiple':
                    case 'select-one':
                    case 'password':
                    case 'textarea':
                        value = $(this).val();
                        break;
                    case 'radio':
                        // Adding the value only if the radio is checked
                        if($(this).is(":checked")){
                            value = $(this).val();
                        } else {
                            // Otherwise (if radio not checked), we should return
                            // and not populate the object
                            return;
                        }
                        break;
                    case 'checkbox':
                        var checkedCheckbox = $(this).is(":checked");
                        // Handling special case where checkbox doesn't have any value attribute
                        if($(this).val() === "on"){
                            value = checkedCheckbox;
                        } else {
                            if(checkedCheckbox){
                                value = $(this).val();
                            } else {
                                // If there is a value set and checkbox is not checked, we shouldn't add
                                // it to the result
                                return;
                            }
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

//Test if a str is a JSON
function isJSON(str) {
    if (blank(str)) return false;
    str = str.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@');
    str = str.replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']');
    str = str.replace(/(?:^|:|,)(?:\s*\[)+/g, '');
    return (/^[\],:{}\s]*$/).test(str);
};

function blank(str) {
    return /^\s*$/.test(this);
};
