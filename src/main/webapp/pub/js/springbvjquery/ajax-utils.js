// this prevent jquery to add square brackets [] at the end of param name when they have multiple values,
// which is breaking spring binding.
$.ajaxSettings.traditional = true;
/* Declaring global ajax settings for $.ajax() calls */
$.ajaxSetup({
    // We put cache = false to ensure IE XHR cache is not used !
    cache: false
});



$(document).ajaxError(function(event, jqXHR, settings, exception){
    if(jqXHR.status === 412){
        var springBindingResults = $.parseJSON(jqXHR.responseText);
        $.displaySpringErrors(springBindingResults);
    }
});
