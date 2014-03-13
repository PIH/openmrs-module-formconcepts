<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<form method="post" action="getPackage.form">
<textarea id="conceptIds" name="conceptIds" rows="4" cols="50">

</textarea>
<br/>
<input type="submit" value="Generate metadata package"/>
</form>
<script>
$j(document).ready(function(){
	var concepts = "${formConcepts}";
	 $j("#conceptIds").val(concepts);
	});
</script>
<%@ include file="/WEB-INF/template/footer.jsp"%>