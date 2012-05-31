jQuery.extend({
    /**
     * Main method allowing to display springBindingResults error messages
     */
    displaySpringErrors: function(springBindingResults) {
        $.each(springBindingResults, function(index, bindingError){
            $.displaySpringErrorMessage(bindingError.field, bindingError.defaultMessage);
        });
    },

    /**
     * Method will display springErrorMessage related to fieldName
     * It will first resolve error message emplacement (see resolveErrorMessageContainerInfos function)
     * then display error message (with, eventually, field label appended to it, see resolveFieldLabel function).
     */
    displaySpringErrorMessage: function(fieldName, springErrorMessage){
        var errorMessageContainerInfos = $.resolveErrorMessageContainerInfos(fieldName);

        // Building error message...
        var errorMessage = "";
        if(errorMessageContainerInfos.fieldLabelNeeded){
            var fieldLabel = $.resolveFieldLabel(fieldName);
            if(fieldLabel !== null){
                errorMessage += fieldLabel + " : ";
            }
        }
        errorMessage += springErrorMessage;

        // Displaying error message
        errorMessageContainerInfos.container.text(errorMessage);
    },

    globalErrors: function(){ return $("ul.global.errors"); },

    /**
     * Current implementation is simple, it will either :
     * - resolve field name input field and, if found, will try to look for twitter bootstrap's help-inline or
     * help-block class around the input field
     * if this one is not found :
     * - a fallback will look for ul.global_errors, add a new <li> tag : this will be the error message container
     *
     * Method should return an object with following fields :
     * - container : the container element which will host error message
     * - labelCanBeAppended : flag which will say if field label should be appended (if found) to the error message
     * or not (will be true if container is a global error item, false otherwise (we succeeded to locate precisely
     * the field : no need to add its label)).
     */
    resolveErrorMessageContainerInfos: function(fieldName) {
        var errorMessageContainerInfos = {
            fieldLabelNeeded: true,
            container: null
        };

        // Trying to resolve input field
        var inputField = $(":input[name='"+fieldName+"']");
        if(inputField.length !== 0){
            // Trying to resolve twitter bootstrap help blocks around input field
            var twitterBootstrapHelp = inputField.siblings(".help-block, .help-inline");
            if(twitterBootstrapHelp.length !== 0){
                errorMessageContainerInfos.container = twitterBootstrapHelp;
                errorMessageContainerInfos.fieldLabelNeeded = false;

                twitterBootstrapHelp.parents(".control-group").addClass("error");

                return errorMessageContainerInfos;
            }
        }

        // Falling back to global errors listing
        errorMessageContainerInfos.container = $("<li></li>");
        errorMessageContainerInfos.container.appendTo($.globalErrors());
        return errorMessageContainerInfos;
    },

    /**
     * Current implementation is simple : it only tries to look for <label> tag for fieldName
     * It won't work on multiline input fields (such as phone numbers or addresses) represented with <table> and
     * where label is located in table's headings (will implement this one day...)
     * This method will never return null (at worst, if no label is found, techniqual field name will be displayed
     * with brackets)
     */
    resolveFieldLabel: function(fieldName){
        var labelElement = $("label[for='"+fieldName+"']");

        if(labelElement.length !== 0){ return labelElement.html(); }
        else { return "["+fieldName+"]"; }
    }

});
