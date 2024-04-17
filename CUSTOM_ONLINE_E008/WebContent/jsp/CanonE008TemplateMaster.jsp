<%!    final String PAGE_NAME = "canonE008TemplateMaster.jsp";
%>
<%
    //CanonE590ItemProcessUtil.checkPageAccess(pageContext, request, response, PAGE_NAME);
%>

<!DOCTYPE html>
<html>
	<HEAD>
	
		<TITLE>Template Workbench</TITLE>
        <%@include file="CanonE008ItemProcessCommon.jsp"%>
	</HEAD>

	<body>
		<div class="logo-img">
		<div class="utility-nav">
			<div>
			<a href="<%=ctxPath%>/e008/jsp/CanonE008TemplateMaster.jsp">Home</a>
			</div>
		</div>
		<div class="logo">
		<a href="#">
		<img width="210" height="98" title="Canon Solutons America" alt="Canon Solutons America" src="<%=ctxPath%>/common/images/csa_logo.jpg">
		</a>
		</div>
	</div>   
	<!-- 	<script src="<%=ctxPath%>/common/jquery/jquery.scrolltable.js" type="text/javascript"></script>	 -->
        <div id = "divCanonE008TemplateMain">
	    	<jsp:include page="CanonE008TemplateSearch.jsp"/>
        </div>
		<!-- <div id="validatation-DataDiv"></div> -->

    </body>
    
</html>