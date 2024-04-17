<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100127093518 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Integration Interface Maintenance">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<!--
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<li title="Order Entry"				 class="pTab_ON" ><a href="#">Order Entry</a></li>
									<li title="Order Upload"			 class="pTab_OFF"><a href="#">Order Upload</a></li>
									<li title="Trial/Loan Request Entry" class="pTab_OFF"><a href="#">Trial/Loan Request Entry</a></li>
									<li title="Asset Management"		 class="pTab_OFF"><a href="#">Asset Management</a></li>
									<li title="Hard Allocation"			 class="pTab_OFF"><a href="#">Hard Allocation</a></li>
									<li title="Hold Release"			 class="pTab_OFF"><a href="#">Hold Release</a></li>
									<li title="Credit Order Release"	 class="pTab_OFF"><a href="#">Credit Order Release</a></li>
									<li title="TOP STOP Release" 		 class="pTab_OFF"><a href="#">TOP STOP Release</a></li>
									<li title="Export Lisence Entry"	 class="pTab_OFF"><a href="#">Export Lisence Entry</a></li>
									<li title="Disposition Order"		 class="pTab_OFF"><a href="#">Disposition Order</a></li>
								</div>
							</td>
							<td valign="bottom" align="center">
		 						<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
							</td>
							<td valign="bottom" align="center">
								<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
							</td>
						</tr>
					</table>
				</ul>
			</div>
			-->
			<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
				<table border="0" cellpadding="1" cellspacing="0" align="center">
					<tr>
						<td>
							<table align="left" border="0" cellpadding="1" cellspacing="0">
								<col width="100">
								<col width="240">
								<col width="40">
								<col width="80">

								<tr>
									<td class="stab">Table Name</td>
									<td>
										<ezf:select name="xxTblNm" ezfName="xxTblNm" ezfBlank="1" ezfCodeName="xxTblNm_C" ezfDispName="xxTblNm_D" />
									</td>
									<td/>
									<td><ezf:inputButton name="Download" value="Download" htmlClass="pBtn6" /></td>
								</tr>
								<tr>
									<td class="stab">Upload Request</td>
									<td><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherEvent1=" onchange=\"return fileNmClr();\"" otherAttr=" size=\"76\" maxlength=\"9999\""/></td>
									<td/>
									<td/>
								</tr>
								<tr>
									<td/>
									<td><ezf:inputText name="xxUpldFileNm" ezfName="xxUpldFileNm" value="1234567890" otherAttr=" size=\"76\" maxlength=\"256\""/></td>
									<td/>
									<td><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<!-- **************************************** HEADER **************************************** -->
				<hr>
<!-- **************************************** DETAIL **************************************** -->
				<%-- Pagenation --%>
				<table border="0" cellpadding="0" cellspacing="0" width="960" align="center">
					<col width="300">
					<col width="690" >
					<tr align="right">
						<td align="left">Upload Error Information</td>
						<td width="900">
<!--
							<table border="1" cellpadding="1" cellspacing="0">
								<col width="54"  align="center">
								<col width="40"  align="right">
								<col width="16"  align="center">
								<col width="40"  align="right">
								<col width="16"  align="center">
								<col width="40"  align="right">
								<col width="10">
								<col>
								<col width="1">
								<col>
								<tr>
									<td class="stab">Showing</td>
									<td class="pOut">1</td>
									<td class="stab">to</td>
									<td class="pOut">40</td>
									<td class="stab">of</td>
									<td class="pOut">200</td>
									<td>&nbsp;</td>
									<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
									<td></td>
									<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
								</tr>
							</table>
-->
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="X" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							</jsp:include>
						</td>
					</tr>
				</table>
<!-- **************************************** DETAIL **************************************** -->
				<!-- view -->
				<table border="0" cellpadding="0" cellspacing="0" width="960" align="center">
					<tr>
						<td>
							<%-- TABLE(TOP) --%>
							<table border="1" cellpadding="1" cellspacing="0">
								<col width="33"  align="center">
								<col width="87"  align="center">
								<col width="800" align="center">
								
								<tr>
									<td class="pClothBs">No</td>
									<td class="pClothBs">Row Number</td>
									<td class="pClothBs">Error Message</td>
								</tr>
							</table>

							<%-- TABLE(MID) --%>
							<div style="overflow-y:scroll; height:383; overflow-x:hidden; width:950;">
								<table border="1" cellpadding="1" cellspacing="0" width="938" id="A_TBL">
									<col width="33" align="right">
									<col width="87" align="right">
									<col width="800">
									
									<tbody>
										<ezf:row ezfHyo="X">
										<tr>
											<td><ezf:label name="xxNum_X" ezfName="xxNum_X" ezfHyo="X" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxRowNum_X" ezfName="xxRowNum_X" ezfHyo="X" ezfArrayNo="0" />&nbsp;&nbsp;</td>
											<td><ezf:label name="upldCsvMsgTxt_X" ezfName="upldCsvMsgTxt_X" ezfHyo="X" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>

									<ezf:skip>
									</ezf:skip>

									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
				</div>

			</td>
		</tr>
	</table>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function fileNmClr() {
		document.getElementById ("xxUpldFileNm").value = "";
	}
</script>

<%-- **** Task specific area ends here **** --%>
