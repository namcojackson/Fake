<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220128132709 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NZZL0080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Deal Configurator Launcher Screen">
			<center>
				<table border="0" cellPadding="0" cellSpacing="0" height="95%" width="100%">
					<tr align="center">
						<td align="center">
							<%-- #################### UPPER TAB #################### --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-image: url(./img/tab/uppertabbackground.jpg);">
									<tr>
										<td height="28px" width="1070px" valign="bottom">
											<table class="pTab_UPPER_ON" border="0" cellpadding="0" cellspacing="0" >
												<tr title="Deal Configurator">
													<td align="center" class="same">Deal Configurator</td>
												</tr>
												<tr><td height="600">&nbsp;</td></tr>
											</table>
										</td>
									</tr>
								</table>
							</ezf:skip>
                            <div class="pTab_BODY">
                                <table border="0" cellPadding="0" cellSpacing="0" height="95%" width="100%">
                                    <tr align="center">
                                        <td valign="center" class="stab">
                                        	<ezf:anchor name="xxPopPrm" ezfName="xxPopPrm" onClick="window.open( this.href, '_blank', 'width=1000,height=700,scrollbars=yes,status=yes,resizable=yes' );return false;" >
                                        		<font size="7">Deal Configurator</font>
                                        	</ezf:anchor>
                                        </td>
                                    </tr>
                                </table>
                            </div>
						</td>
					</tr>
				</table>
			</center>
<script>
function f_open_window_max( aURL, aWinName )
{
   var wOpen;
   var sOptions;

   sOptions = 'status=yes,menubar=no,scrollbars=yes,resizable=yes,toolbar=no';
   sOptions = sOptions + ',width=' + (screen.availWidth - 17).toString();
   sOptions = sOptions + ',height=' + (screen.availHeight - 65).toString();
   sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

   wOpen = window.open( '', aWinName, sOptions );
   wOpen.location = aURL;
   wOpen.focus();
   wOpen.moveTo( 0, 0 );
   //wOpen.resizeTo( screen.availWidth, screen.availHeight  );
   return wOpen;
}
</script>

<%-- **** Task specific area ends here **** --%>
