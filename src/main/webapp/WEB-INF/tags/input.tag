<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ tag body-content="scriptless" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="labelKey" required="true" %>
<%@ attribute name="id" required="false" %>
<%@ attribute name="defaultValue" required="false" %>
<div class="control-group">
    <c:choose>
        <c:when test="${empty(id)}">
            <label class="control-label"><fmt:message key="${labelKey}" /></label>
        </c:when>
        <c:otherwise>
            <label class="control-label" for="${id}"><fmt:message key="${labelKey}" /></label>
        </c:otherwise>
    </c:choose>
    <div class="controls">
        <c:choose>
            <c:when test="${empty(id)}">
                <input type="text" name="${name}" value="${defaultValue}" />
            </c:when>
            <c:otherwise>
                <input id="${id}" type="text" name="${name}" value="${defaultValue}" />
            </c:otherwise>
        </c:choose>
        <p class="help-inline"></p>
    </div>
</div>
