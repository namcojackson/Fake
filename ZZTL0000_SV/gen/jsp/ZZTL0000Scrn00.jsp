<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160920164228 --%>
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
			<input type="hidden" name="pageID" value="ZZTL0000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Validation Method">
			
			<b>S21Common Validation Screen</b>
			<hr>
			<br>
			
			<!-- Online -->
			<table align="center">
				<tr>
					<td class="stab" align="center" colspan="2">
						<fieldset>
							<legend>Online</legend>
							
							
							<table align="center" border="0" cellpadding="5">
								<tr>
									<td><strike>BO Online Report validation</strike></td>
									<td><ezf:inputButton name="BO_Validation" value="button1" htmlClass="pBtn6" /></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>EIP Online Report validation</td>
									<td><ezf:inputButton name="EIP_Validation" value="button2" htmlClass="pBtn6" /></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>Vertex Online validation</td>
									<td><ezf:inputButton name="Vertex_Validation" value="button3" htmlClass="pBtn6" /></td>
									<td>Tax Rate:</td>
									<td class="pOut"><ezf:label name="acctTaxAmt" ezfName="acctTaxAmt" /></td>
									<td>%</td>
								</tr>
								<tr>
									<td><strike>Kewill(ECS) validation</strike></td>
									<td><ezf:inputButton name="Kewill_Validation" value="button5" htmlClass="pBtn6" /></td>
									<td>Result:</td>
									<td class="pOut"><ezf:label name="xxRtrnCd" ezfName="xxRtrnCd" /></td>
									<td></td>
								</tr>
							</table>
							
							
						</fieldset>
					</td>
				</tr>
			</table>
			
			<br>
			
			
			<table align="center">
				<tr>
					<td class="stab" align="center" colspan="2">
						<fieldset>
							<legend>Batch</legend>
							
							<table>
								 <tr>
									<td rowspan=8 valign="top">[Job Name]</td>
									<td><strike>BO Printng validation</strike></td>
									<td><strike>ZZTJ0010010</strike></td>
									<td rowspan=8 valign="bottom">Run All Job</td>
									<td rowspan=8 valign="bottom"><ezf:inputButton name="Run_All_Job" value="button4" htmlClass="pBtn6" /></td>
								</tr>
								<tr>
									<td><strike>BO e-mail validation</strike></td>
									<td><strike>ZZTJ0010020</strike></td>
								</tr>
								<tr>
									<td><strike>BO Fax validation</strike></td>
									<td><strike>ZZTJ0010030</strike></td>
								</tr>
								<tr>
									<td>EIP Printng validation</td>
									<td>ZZTJ0020010</td>
								</tr>
								<tr>
									<td>EIP e-mail validation</td>
									<td>ZZTJ0020020</td>
								</tr>
								<tr>
									<td><strike>EIP Fax validation</strike></td>
									<td><strike>ZZTJ0020030</strike></td>
								</tr>
								<tr>
									<td>Vertex validation</td>
									<td>ZZIJ1020010</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			</table>
			
			 

<%-- **** Task specific area ends here **** --%>
