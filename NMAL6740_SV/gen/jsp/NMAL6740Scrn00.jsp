<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180817085754 --%>
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
			<input type="hidden" name="pageID" value="NMAL6740Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Ship To Details">
			
			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Location Info" class="pTab_ON"><a href="#">Ship To Dtl</a></li>
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
				</ezf:skip>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table width="98%" border="0" align="center">
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;">
									<col width="120">
									<col width="400">
									<col width="50">
									<col width="120">
									<col width="400">
									<tr>
										<td class="stab">Parent Account Name</td>
										<td><ezf:label name="dsAcctNm" ezfName="dsAcctNm" /></td>
										<td><br /></td>
										<td class="stab">Location Number</td>
										<td><ezf:label name="locNum" ezfName="locNum" /></td>
									</tr>
									<tr>
										<td class="stab">Address</td>
										<td><ezf:label name="xxAllLineAddr" ezfName="xxAllLineAddr" /></td>
										<td><br /></td>
										<td class="stab">Ship To Code</td>
										<td><ezf:label name="shipToCustCd" ezfName="shipToCustCd" /></td>
									</tr>
									<tr>
										<td class="stab">City</td>
										<td><ezf:label name="ctyAddr" ezfName="ctyAddr" /></td>
										<td><br /></td>
									</tr>
									<tr>
										<td class="stab">State</td>
										<td><ezf:label name="stCd" ezfName="stCd" /></td>
										<td><br /></td>
										<td class="stab">Big Deal#</td>
										<td><ezf:inputText name="bigDealNum" ezfName="bigDealNum" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Postal Code</td>
										<td><ezf:label name="postCd" ezfName="postCd" /></td>
										<td><br /></td>
									</tr>
									<tr>
										<td colspan="3"><br /></td>
										<td class="stab"><ezf:anchor name="coaChCd" ezfName="coaChCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Coa" >ExpenseAccount</ezf:anchor></td>
										<td><ezf:inputText name="xxScrItem130Txt" ezfName="xxScrItem130Txt" otherAttr=" size=\"52\" maxlength=\"130\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>

					<table width="100%">
						<col valign="top">
						<tr>
							<td>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON" id="Taxing" title="Taxing"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Taxing" >Taxing</ezf:anchor></li>
								</ul>
							</div>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Taxing'}">
								<script type="text/javascript">
									document.getElementById( "Taxing" ).className = "pTab_ON";
								</script>
								<div class="pTab_BODY_In">
									<table border="0" cellpadding="1" cellspacing="0" height="250" style="table-layout:fixed;">
									<br/>
										<col width="10" valign="top">
										<col width="130" valign="top">
										<col width="350" valign="top">
										<tr>
											<td></td>
											<td class="stab"> Vertex Group Exemption</td>
											<td><ezf:select name="dsTaxGrpExemCd" ezfName="dsTaxGrpExemCd" ezfBlank="1" ezfCodeName="dsTaxGrpExemCd_L" ezfDispName="dsTaxGrpExemNm_L" />
											</td>
										</tr>
									</table>
								</div>
							</c:when>
							</c:choose>
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
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
	}

	function onBlurEvent(item){
		if(item.value.length === 1){
			item.value = '0' + item.value.charAt(0) + ':00';
		}else if(item.value.length === 2){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
		}else if(item.value.length === 3){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
		}else if(item.value.length === 4){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}else if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
