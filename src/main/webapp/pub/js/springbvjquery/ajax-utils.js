// This prevent jquery to add square brackets [] at the end of param name when they have multiple values,
// which is breaking spring binding.
$.ajaxSettings.traditional = true;
/* Declaring global ajax settings for $.ajax() calls */
$.ajaxSetup({
    // We put cache = false to ensure IE XHR cache is not used !
    cache: false
});

/**
 * Overriding default behaviour for ajax errors : looking at jquery XMLHttpRequest's status
 * and, if it is the special 412 (Precondition failed) status _and_ response is a JSON representation,
 * then call $.displaySpringErrors()
 */
$(document).ajaxError(function(event, jqXHR, settings, exception){
    if(jqXHR.status === 412 && isJSON(jqXHR.responseText)){
        var springBindingResults = $.parseJSON(jqXHR.responseText);
        $.displaySpringErrors(springBindingResults);
    }
});
