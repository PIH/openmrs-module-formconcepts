<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

   <c:forEach var="concept" items="${formConcepts}">
		
         ${concept.conceptId},

	</c:forEach>

<%@ include file="/WEB-INF/template/footer.jsp"%>