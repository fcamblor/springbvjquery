<%@ include file="common/taglibs.jspf" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/pub/css/libs/twbootstrap/bootstrap-2.0.3.css" rel="stylesheet">
    <link href="/pub/css/libs/twbootstrap/bootstrap-responsive-2.0.3.css" rel="stylesheet">
    <link href="/pub/css/springbvjquery/springbvjquery.css" rel="stylesheet">

    <script src="/pub/js/libs/jquery/jquery-1.7.2.js"></script>
    <script src="/pub/js/libs/twbootstrap/bootstrap-2.0.3.js"></script>
    <script src="/pub/js/libs/json/json2.js"></script>
    <script src="/pub/js/springbvjquery/js-utils.js"></script>
    <script src="/pub/js/springbvjquery/spring-validationerrors.js"></script>
    <script src="/pub/js/springbvjquery/ajax-utils.js"></script>
    <script>
        $(function(){
            $("#okButton").live("click", function(){
                var userInfos = $("form").inputsToJSON();
                $.ajax({
                    'type': "POST",
                    'url': "/users",
                    'cache': false,
                    'contentType': 'application/json',
                    'data': JSON.stringify(userInfos),
                    'dataType': 'json',
                    'success': function(){
                        alert("creation ok");
                    },
                    'error': function(jqXHR, textStatus, errorThrown){
                        alert("creation error");
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h1><fmt:message key="welcome.heading" /></h1>
    <ul class="global errors"></ul>
    <form class="form-horizontal">
        <fieldset>
            <legend><fmt:message key="welcome.forms.user.legend" /></legend>
            <div class="control-group">
                <label class="control-label" for="userFirstName"><fmt:message key="welcome.forms.user.fields.firstname.label" /></label>
                <div class="controls">
                    <input id="userFirstName" type="text" name="firstName" value="foo" />
                    <p class="help-inline"></p>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="userLastName"><fmt:message key="welcome.forms.user.fields.lastname.label" /></label>
                <div class="controls">
                    <input id="userLastName" type="text" name="lastName" value="bar" />
                    <p class="help-inline"></p>
                </div>
            </div>
        </fieldset>
        <input type="text" name="credentials.login" value="foo@bar.com" />
        <input type="password" name="credentials.password" value="hello" />
        <button type="button" id="okButton">Ok</button>
    </form>
</body>
</html>