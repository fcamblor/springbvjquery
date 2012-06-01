<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ tag body-content="scriptless" dynamic-attributes="inputAttrs" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="labelKey" required="true" %>
<%@ attribute name="id" required="false" %>
<%@ variable name-given="attrString" declare="false" %>
<c:forEach var="attr" items="${inputAttrs}">
	<c:if test="${!empty inputAttrs[attr.key]}">
		<c:set var="attrString" value="${attrString} ${attr.key}=\"${attr.value}\"" />
	</c:if>
</c:forEach>
<c:set var="labelForAttribute"><c:if test="${!empty(id)}">for="${id}"</c:if></c:set>
<c:set var="inputIdAttribute"><c:if test="${!empty(id)}">id="${id}"</c:if></c:set>
<div class="control-group">
    <label class="control-label" ${labelForAttribute}><fmt:message key="${labelKey}" /></label>
    <div class="controls">
        <input ${inputIdAttribute} type="text" name="${name}" ${attrString} />
        <p class="help-inline"></p>
    </div>
</div>
