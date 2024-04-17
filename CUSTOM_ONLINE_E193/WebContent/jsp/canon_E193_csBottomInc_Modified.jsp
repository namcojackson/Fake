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

	<footer>
		<div class="top-bg">
			<div class="top"><a href="canon_E193_csEIHome.jsp">Home</a><a href="canon_E193_csLogout.jsp">Logout</a><a href="canon_E193_csHelp.jsp">Help</a></div>
		</div>
		<div class="copy">Canon is a registered trademark of Canon Inc. in the United States and elsewhere.<br />
			All other referenced product names and marks are trademarks of their respective owners and are hereby acknowledged.<br />
			&copy; 2015 Canon Solutions America, Inc. All rights reserved.
		</div>
	</footer>
	<table width=<%=iBodyTabWidth%> align="center" cellpadding=0 cellspacing=0 border=0>
		<TR>
			<TD><!-- bottom bar -->
			<table width=900 cellpadding=0 cellspacing=0 border=0>
		
			</table>
			</td>
		</TR>
		<TR>
			<TD>
				<table width=900 cellpadding=0 cellspacing=0 border=0>
					<tr>
						<TD><IMG SRC="<%=ctxPath%>/e193/images/canon_E193_csShadowBtm.jpg" WIDTH=900 HEIGHT=20></TD>
					</tr>
				</table>
			</td>
		</TR>
		<TR>
			<TD>
				<table bgcolor="D8DCE4" summary=" " width="900" border="0" cellspacing="0" cellpadding="2">
					<tr>					
						<td nowrap class="prompt" align="center"> <font size="2"><%=sbHomeMenu%></font> </td>
					</tr>
				</table>  
			</TD>
		</TR>	
	</TABLE>
<%
}
//catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
 /*      String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();
       
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}*/
catch (Exception eExp) {
       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>
</body>
</html>
<!-- canon_E193_csBottom.jsp end -->