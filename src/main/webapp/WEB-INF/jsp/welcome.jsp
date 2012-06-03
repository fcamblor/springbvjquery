<%@ include file="common/taglibs.jspf" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/pub/css/libs/twbootstrap/bootstrap-2.0.3.css" rel="stylesheet">
    <link href="/pub/css/libs/twbootstrap/bootstrap-responsive-2.0.3.css" rel="stylesheet">
    <link href="/pub/css/libs/jquery-ui-bootstrap/jquery-ui-1.8.16.custom.css" rel="stylesheet">
    <link href="/pub/css/springbvjquery/springbvjquery.css" rel="stylesheet">

    <script src="/pub/js/libs/jquery/jquery-1.7.2.js"></script>
    <script src="/pub/js/libs/twbootstrap/bootstrap-2.0.3.js"></script>
    <script src="/pub/js/libs/jquery-ui/jquery-ui-1.8.16.custom.min.js"></script>
    <script src="/pub/js/libs/jquery-ui/i18n/jquery.ui.datepicker-fr.js"></script>
    <script src="/pub/js/libs/json/json2.js"></script>
    <script src="/pub/js/springbvjquery/js-utils.js"></script>
    <script src="/pub/js/springbvjquery/spring-validationerrors.js"></script>
    <script src="/pub/js/springbvjquery/ajax-utils.js"></script>
    <script>
        $(function(){
            $("#okButton").live("click", function(){
                var userInfos = $("form").cleanSpringErrors().inputsToJSON();
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
                    }
                });
            });

            $(".datepicker").datepicker();
        });
    </script>
</head>
<body>
    <h1><fmt:message key="welcome.heading" /></h1>
    <ul class="global errors"></ul>
    <form class="form-horizontal">
        <fieldset>
            <legend><fmt:message key="welcome.forms.user.legend" /></legend>
            <custom:input name="firstName" labelKey="welcome.forms.user.fields.firstname.label" id="userFirstName" />
            <custom:input name="lastName" labelKey="welcome.forms.user.fields.lastname.label" id="userLastName" />
            <custom:input name="birthDate" labelKey="welcome.forms.user.fields.birthdate.label" id="userBirthDate" class="datepicker" />
        </fieldset>

        <input type="text" name="credentials.login" value="foo@bar.com" />
        <input type="password" name="credentials.password" value="hello" />
        <button type="button" id="okButton"><fmt:message key="actions.ok" /></button>
    </form>
    <div class="modal hide fade in" id="creationOk">
      <div class="modal-header">
        <button class="close" data-dismiss="modal">×</button>
        <h3><fmt:message key="welcome.forms.creation.congratulations.header" /></h3>
      </div>
      <div class="modal-body">
        <p><fmt:message key="welcome.forms.creation.congratulations.message" /></p>
      </div>
      <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal"><fmt:message key="actions.close" /></a>
      </div>
    </div>
</body>
</html>