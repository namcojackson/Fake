<!-- $Header: canon_E193_csBottomInc.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csBottomInc.jsp - Bottom Menu
 |   
 | DESCRIPTION
 |   Contains bottom menu and closes the Body tag
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	07/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>		
<%
}
catch (Exception eExp) {
      // response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
      eExp.printStackTrace();
}
%>
<script type="text/javascript">
$(document).ready(function() {
	var detectBrowser=msieversion();
	if("otherbrowser"!=detectBrowser)
		{
		$('#nav1').css({"float":"right","width": "1030px"});
		$('#nav2').css({"float":"right","width": "1030px"});
		}	
});
</script>

<!-- canon_E193_csBottom.jsp end -->
