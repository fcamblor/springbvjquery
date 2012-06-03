jQuery.extend({
    /**
     * Main method allowing to display springBindingResults error messages
     */
    displaySpringErrors: function(springBindingResults, selector) {
        $.each(springBindingResults, function(index, bindingError){
            $.displaySpringErrorMessage(selector, bindingError.field, bindingError.defaultMessage);
        });
    },

    /**
     * Method will display springErrorMessage related to fieldName
     * It will first resolve error message emplacement (see resolveErrorMessageContainerInfos function)
     * then display error message (with, eventually, field label appended to it, see resolveFieldLabel function).
     */
    displaySpringErrorMessage: function(selector, fieldName, springErrorMessage){
        var errorMessageContainerInfos = $.resolveErrorMessageContainerInfos(selector, fieldName);

        // Building error message...
        var errorMessage = "";
        if(errorMessageContainerInfos.fieldLabelNeeded){
            var fieldLabel = $.resolveFieldLabel(selector, fieldName);
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
    resolveErrorMessageContainerInfos: function(selector, fieldName) {
        var errorMessageContainerInfos = {
            // Will say if label will be needed in error message (in particular cases, such as when twitter bootstrap's
            // help block is present, we don't need to append the label to the error message)
            fieldLabelNeeded: true,
            // Error field container where the error message will be appended
            container: null
        };

        // Trying to resolve input field
        var inputField = $(":input[name='"+fieldName+"']", selector);
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
    resolveFieldLabel: function(selector, fieldName){
        var labelElement = $("label[for='"+fieldName+"']", selector);

        if(labelElement.length !== 0){ return labelElement.html(); }
        else { return "["+fieldName+"]"; }
    }

});
