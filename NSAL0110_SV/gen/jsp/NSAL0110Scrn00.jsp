<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170130114401 --%>
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
			<input type="hidden" name="pageID" value="NSAL0110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Popup">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## FROM (COMMON)HEADER ######################################## --%>
				<table align="center">
					<tr>
						<td align="left">






				<table style="margin-top: 5px;" cellSpacing="0" cellPadding="1" border="0">
					<col>
					<col>

					<tr>
						<td rowspan="2">
							<fieldset style="display: inline">
								<legend>Contract Header Criteria</legend>
									<table style="margin-top: 0px;" cellSpacing="0" cellPadding="1" border="0">
										<col width="90px">
										<col width="115px">
										<col width="80px">
										<col>

										<tr>
											<td class="stab">Contract # (*)</td>
											<td><ezf:inputText name="dsContrNum_SC" ezfName="dsContrNum_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2" otherAttr=" maxlength=\"30\" size=\"20\" ezftoupper=\"\""/></td>
											<td class="stab">Branch&nbsp;&nbsp;<ezf:inputText name="svcContrBrCd_SC" ezfName="svcContrBrCd_SC" value="123" otherAttr=" maxlength=\"3\" size=\"3\" ezftoupper=\"\""/></td>
											<td class="stab">Contract Category
												<ezf:select name="dsContrClsCd_SV" ezfName="dsContrClsCd_SV" ezfBlank="1" ezfCodeName="dsContrClsCd_SC" ezfDispName="dsContrClsDescTxt_SC" otherAttr=" style=\"width:50px\""/>
											</td>
										</tr>

										<tr>
											<td class="stab">Description (*)</td>
											<td colspan="3"><ezf:inputText name="dsContrNm_SC" ezfName="dsContrNm_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2" otherAttr=" maxlength=\"60\" size=\"20\""/></td>
										</tr>

										<tr>
											<td class="stab">Account Code (*)</td>
											<td><ezf:inputText name="sellToCustCd_SC" ezfName="sellToCustCd_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2" otherAttr=" maxlength=\"20\" size=\"20\" ezftoupper=\"\""/></td>
											<td class="stab">Contract Type</td>
											<td>
												<ezf:select name="dsContrCatgCd_SV" ezfName="dsContrCatgCd_SV" ezfBlank="1" ezfCodeName="dsContrCatgCd_SC" ezfDispName="dsContrCatgDescTxt_SC" otherAttr=" style=\"width:130px\""/>
											</td>
										</tr>

										<tr>
											<td class="stab">Contract Status</td>
											<td>
												<ezf:select name="dsContrCtrlStsCd_HV" ezfName="dsContrCtrlStsCd_HV" ezfBlank="1" ezfCodeName="dsContrCtrlStsCd_HC" ezfDispName="dsContrCtrlStsNm_HC" otherAttr=" style=\"width:110px\""/>
											</td>
										</tr>
									</table>
							</fieldset>
						</td>
						<td>
							<fieldset style="display: inline">
								<legend>Contract Detail Criteria</legend>
									<table style="margin-top: 0px;" cellSpacing="0" cellPadding="1" border="0">
										<col width="80px">
										<col width="155px">
										<col width="65px">
										<col>

										<tr>
											<td class="stab">Detail Type</td>
											<td>
												<ezf:select name="dsContrDtlTpCd_SV" ezfName="dsContrDtlTpCd_SV" ezfBlank="1" ezfCodeName="dsContrDtlTpCd_SC" ezfDispName="dsContrDtlTpDescTxt_SC" otherAttr=" style=\"width:145px\""/>
											</td>
											<td class="stab">Detail Status</td>
											<td>
												<ezf:select name="dsContrCtrlStsCd_DV" ezfName="dsContrCtrlStsCd_DV" ezfBlank="1" ezfCodeName="dsContrCtrlStsCd_DC" ezfDispName="dsContrCtrlStsNm_DC" otherAttr=" style=\"width:180px\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">Serial # (*)</td>
											<td><ezf:inputText name="serNum_SC" ezfName="serNum_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2" otherAttr=" maxlength=\"30\" size=\"20\" ezftoupper=\"\""/></td>
										</tr>
										<tr>
											<td class="stab">Model Name (*)</td>
											<td><ezf:inputText name="mdlNm_SC" ezfName="mdlNm_SC" value="ZZZZZZZZZ1ZZZZZZZZZ2" otherAttr=" maxlength=\"50\" size=\"20\" ezftoupper=\"\""/></td>
										</tr>
									</table>
							</fieldset>
						</td>
					</tr>
					<tr>
						<td>
							<table style="margin-top: 0px;" cellSpacing="0" cellPadding="1" border="0">
								<col width="85px">
								<col width="345px">
								<col>

								<tbody>
									<tr>
										<td class="stab">&nbsp;&nbsp;Display Mode</td>
										<td>
											<ezf:select name="xxScrItem10Txt_SV" ezfName="xxScrItem10Txt_SV" ezfCodeName="xxScrItem10Txt_CD" ezfDispName="xxScrItem10Txt_NM" />
										</td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								<tbody>
							</table>
						</td>
					</tr>
				</table>






						</td>
					</tr>
				</table>
<%-- ######################################## TO   (COMMON)HEADER ######################################## --%>
				<hr />
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
<ezf:skip>
				<table width="1000px">
					<tr>
						<td align="right">
							<TABLE cellSpacing="0" cellPadding="1" border="0">
								<COLGROUP>
									<COL align="left" width="80">
									<COL align="left" width="80">
									<COL align="left" width="700">
									<COL align="right" width="40">
									<COL align="middle" width="16">
									<COL align="right" width="40">
									<COL align="middle" width="16">
									<COL align="right" width="40">
									<COL width="10">
									<COL width="0">
									<COL width="1">
									<COL width="0">
								</COLGROUP>
								<TBODY>
									<TR>
										<TD></TD>
										<TD></TD>
										<TD>&nbsp;</TD>
										<TD class="stab">Showing</TD>
										<TD class="pOut">1</TD>
										<TD class="stab">to</TD>
										<TD class="pOut">50</TD>
										<TD class="stab">of</TD>
										<TD class="pOut">200</TD>
										<TD>&nbsp;</TD>
										<TD><INPUT class="pBtn3" disabled onclick="sendServer(this)" type="button" value="Prev" name="PagePrev"></TD>
										<TD></TD>
										<TD><INPUT class="pBtn3" onclick="sendServer(this)" type="button" value="Next" name="PageNext"></TD>
									</TR>
								</TBODY>
							</TABLE>
						</td>
					</tr>
				</table>
</ezf:skip>
				<table width="1000px">
					<tr>
						<td align="right">
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							</jsp:include>
						</td>
					</tr>
				</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## FROM (COMMON)DETAIL ######################################## --%>
				<table cellSpacing="0" cellPadding="0" border="0" style="margin-left: 5px; margin-right: 3px">
					<tr style="vertical-align: top">
						<!-- Left TBL -->
						<td align="right">
							<table cellSpacing="0" cellPadding="1" border="1">
								<colgroup>
									<col align="middle" width="22px">	<!-- Select Button -->
									<col align="middle" width="144px">	<!-- Contract # -->
									<col align="middle" width="152px">	<!-- Description -->
								</colgroup>
								<tbody>
									<tr height="37px">
										<td rowspan="2" class="pClothBs">&nbsp;</td>
										<td class="pClothBs">Contract #</td>
										<td class="pClothBs">Description</td>
									</tr>
								</tbody>
							</table>
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Summary'}">
							<div id="leftTBL" style="overflow-y:hidden; height:338px; overflow-x:hidden; width:332px;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>
<%-- Panama QC#655 Mod Start 2014/08/19 T.Yoshida --%>
							<%-- <div id="leftTBL" style="overflow-y:hidden; height:338px; overflow-x:hidden; width:332px;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));"> --%>
							<div id="leftTBL" style="overflow-y:hidden; height:298px; overflow-x:hidden; width:332px;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
<%-- Panama QC#655 Mod End 2014/08/19 T.Yoshida --%>
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
								<table border="1" cellpadding="1" cellspacing="0" id="L">
									<colgroup>
										<col align="middle" width="22px">	<!-- Select Button -->
										<col align="middle" width="144px">	<!-- Contract # -->
										<col align="middle" width="152px">	<!-- Description -->
									</colgroup>
									<tbody>
									<ezf:row ezfHyo="A">
										<tr height="28px">
											<td><ezf:inputButton name="SelectContract" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" otherAttr=" style=\"width=20px\" id=\"focusGrp_02#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsContrNum_RS" ezfName="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"40\" size=\"19\" id=\"focusGrp_02#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsContrNm_RS" ezfName="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"60\" size=\"20\" id=\"focusGrp_02#{EZF_ROW_NUMBER}\""/></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
										<tr height="28px">
											<td><input type="button" class="pBtn6" value="S" name="SelectContract" onclick="sendServer(this)" style="width=20px"></td>
											<td><input class="pPro" maxLength="40" size="19" name="dsContrNum_RS" ezfname="dsContrNum_RS" value="ZZZZZZZZZ1Z-ZZZ" ezfhyo="A" id="focusGrp_02#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" maxLength="60" size="20" name="dsContrNm_RS" ezfname="dsContrNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfhyo="A"></td>
										</tr>
									</ezf:skip>
									</tbody>
								</table>
							</div>
						</td>
						<!-- Right TBL -->
						<td>
							<div id="rightHeaderTBL" style="overflow-y:none; height:28px; overflow-x:hidden; width:643px;">
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Summary'}">
								<table border="1" cellpadding="1" cellspacing="0" id="D1" width="590px">
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>
<%-- Panama QC#655 Mod Start 2014/08/19 T.Yoshida --%>
								<%-- <table border="1" cellpadding="1" cellspacing="0" id="D1" width="1366px"> --%>
								<table border="1" cellpadding="1" cellspacing="0" id="D1" width="1230px">
<%-- Panama QC#655 Mod End 2014/08/19 T.Yoshida --%>
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
									<colgroup>
										<col align="middle" width="80px">	<!-- Contract Status -->
										<col align="middle" width="96px">	<!-- Account Code -->
										<col align="middle" width="152px">	<!-- Account Name -->
										<col align="middle" width="88px">	<!-- Type -->
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>
										<col align="middle" width="152px">	<!-- Detail Type -->
										<col align="middle" width="160px">	<!-- Detail Status -->
										<col align="middle" width="84px">	<!-- Effective From -->
										<col align="middle" width="84px">	<!-- Effective Thru -->
										<col align="middle" width="128px">	<!-- Serial # -->
										<col align="middle" width="144px">	<!-- Model Name -->
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
									</colgroup>
									<tbody>
										<tr height="37px">
											<td class="pClothBs">Status</td>
											<td class="pClothBs">Account Code</td>
											<td class="pClothBs">Account Name</td>
											<td class="pClothBs">Type</td>
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>
											<td class="pClothBs">Detail<br>Type</td>
											<td class="pClothBs">Detail<br>Status</td>
											<td class="pClothBs">Effective<br>From</td>
											<td class="pClothBs">Effective<br>Thru</td>
											<td class="pClothBs">Serial #</td>
											<td class="pClothBs">Model Name</td>
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
										</tr>
									</tbody>
								</table>
							</div>
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Summary'}">
							<div id="rightTBL" style="overflow-y:scroll; height:338px; overflow-x:none; width:607px;" onScroll="synchroScrollLeft(this.id, new Array('rightHeaderTBL')); synchroScrollTop(this.id, new Array('leftTBL'));">
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>
<%-- Panama QC#655 Mod Start 2014/08/19 T.Yoshida --%>
							<%--<div id="rightTBL" style="overflow-y:scroll; height:355px; overflow-x:scroll; width:660px;" onScroll="synchroScrollLeft(this.id, new Array('rightHeaderTBL')); synchroScrollTop(this.id, new Array('leftTBL'));"> --%>
							<div id="rightTBL" style="overflow-y:scroll; height:315px; overflow-x:scroll; width:660px;" onScroll="synchroScrollLeft(this.id, new Array('rightHeaderTBL')); synchroScrollTop(this.id, new Array('leftTBL'));">
<%-- Panama QC#655 Mod End 2014/08/19 T.Yoshida --%>
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Summary'}">
								<table border="1" cellpadding="1" cellspacing="0" id="R" width="590px">
								</c:when>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>
<%-- Panama QC#655 Mod Start 2014/08/19 T.Yoshida --%>
								<%-- <table border="1" cellpadding="1" cellspacing="0" id="R" width="1366px"> --%>
								<table border="1" cellpadding="1" cellspacing="0" id="R" width="1230px">
<%-- Panama QC#655 Mod End 2014/08/19 T.Yoshida --%>
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
									<colgroup>
										<col align="middle" width="80px">	<!-- Contract Status -->
										<col align="middle" width="96px">	<!-- Account Code -->
										<col align="middle" width="152px">	<!-- Account Name -->
										<col align="middle" width="88px">	<!-- Type -->
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>

										<col align="middle" width="152px">	<!-- Detail Type -->
										<col align="middle" width="160px">	<!-- Detail Status -->
										<col align="middle" width="84px">	<!-- Effective From -->
										<col align="middle" width="84px">	<!-- Effective To -->
										<col align="middle" width="128px">	<!-- Machine Serial # -->
										<col align="middle" width="144px">	<!-- Machine Model Name -->
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
									</colgroup>
									<tbody>
									<ezf:row ezfHyo="A">
										<tr height="28px">
											<td><ezf:inputText name="dsContrCtrlStsNm_HS" ezfName="dsContrCtrlStsNm_HS" value="Cancelled" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" id=\"focusGrp_03#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsAcctNum_RS" ezfName="dsAcctNum_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" id=\"focusGrp_03#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsAcctNm_RS" ezfName="dsAcctNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" id=\"focusGrp_03#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsContrCatgDescTxt_RS" ezfName="dsContrCatgDescTxt_RS" value="Super Fleet" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"50\" id=\"focusGrp_03#{EZF_ROW_NUMBER}\""/></td>
<ezf:skip>
<%--
</ezf:skip>
							<c:choose>
								<c:when test="${pageScope._ezddatabean.xxScrItem10Txt_SX=='Detail'}">
<ezf:skip>
--%>
</ezf:skip>
											<td><ezf:inputText name="dsContrDtlTpDescTxt_RS" ezfName="dsContrDtlTpDescTxt_RS" value="Supply Inclusive" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" id=\"focusGrp_03#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="dsContrCtrlStsNm_DS" ezfName="dsContrCtrlStsNm_DS" value="Waiting For Final Bill" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" id=\"focusGrp_03#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:label name="contrVrsnEffFromDt_RS" ezfName="contrVrsnEffFromDt_RS" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="contrVrsnEffThruDt_RS" ezfName="contrVrsnEffThruDt_RS" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="serNum_RS" ezfName="serNum_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\""/></td>
											<td><ezf:inputText name="mdlNm_RS" ezfName="mdlNm_RS" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"50\" id=\"focusGrp_04#{EZF_ROW_NUMBER}\""/></td>
<ezf:skip>
<%--
</ezf:skip>
								</c:when>
							</c:choose>
<ezf:skip>
--%>
</ezf:skip>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
										<tr height="28px">
											<td><input class="pPro" size="10" maxlength="50" name="dsContrCtrlStsNm_HS" ezfname="dsContrCtrlStsNm_HS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="12" maxlength="20" name="dsAcctNum_RS" ezfname="dsAcctNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="60" name="dsAcctNm_RS" ezfname="dsAcctNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="11" maxlength="50" name="dsContrCatgDescTxt_RS" ezfname="dsContrCatgDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="20" maxlength="50" name="dsContrDtlTpDescTxt_RS" ezfname="dsContrDtlTpDescTxt_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><input class="pPro" size="22" maxlength="50" name="dsContrCtrlStsNm_DS" ezfname="dsContrCtrlStsNm_DS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_03#{EZF_ROW_NUMBER}"></td>
											<td><label ezfout name="contrVrsnEffFromDt_RS" ezfname="contrVrsnEffFromDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><label ezfout name="contrVrsnEffThruDt_RS" ezfname="contrVrsnEffThruDt_RS" ezfhyo="A">MM/DD/YYYY</label></td>
											<td><input class="pPro" size="17" maxlength="30" name="serNum_RS" ezfname="serNum_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3"></td>
											<td><input class="pPro" size="19" maxlength="50" name="mdlNm_RS" ezfname="mdlNm_RS" ezfhyo="A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5" id="focusGrp_04#{EZF_ROW_NUMBER}"></td>
										</tr>
									</ezf:skip>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
<%-- ######################################## TO   (COMMON)DETAIL ######################################## --%>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
