<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530190840 --%>
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
			<input type="hidden" name="pageID" value="NSBL0340Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Task Summary">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Fleet Rollup Detail" class="pTab_ON"><a href="#">Rlup Dtl</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
				<table border="0" width="99%" align="center">
					<tr align="right">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
								<col width="200">
								<col width="555">
								<col width="">
								<tr height='25px'>
									<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
									<td>&nbsp;</td>
									<td class="stab">
										<ezf:skip>
											<table border="0" cellpadding="1" cellspacing="0">
												<col >
												<col width="40" align="right">
												<col width="16" align="center">
												<col width="40" align="right">
												<col width="16" align="center">
												<col width="40" align="right">
												<col width="10">
												<col width="0">
												<col width="1">
												<col width="0">
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut">1</td>
													<td class="stab">to</td>
													<td class="pOut">3</td>
													<td class="stab">of</td>
													<td class="pOut">1000</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
												</tr>
											</table>
										</ezf:skip>
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
						</td>
					</tr>
				</table>
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NSBL0340.NSBL0340BMsg" %>
<%  NSBL0340BMsg bMsg = (NSBL0340BMsg)databean.getEZDBMsg(); %>
<%@ include file="../common/common.jsp" %>

				<table border="0" width="99%" align="center">
					<tr>
						<td>
							<div id ="parentBoxA">
								<div style="float:left; display:block"> <!-- left table -->
									<div id='leftTblHead' style="display:block;"></div>
									<div id='leftTbl' style="float:left; display:block; height:477px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
								</div><!-- left table -->
								<div style="float:left;"><!-- right table -->
									<div id='rightTblHead' style="width:1088px; display:block; overflow:hidden; margin:0px; padding:0px;">
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="3040px" style="margin-right:20px">
											<col width="80"  align="center">	<!-- SR# -->
											<col width="80"  align="center">	<!-- Task# -->
											<col width="100" align="center">	<!-- Vendor SR# -->
											<col width="100" align="center">	<!-- SR Status -->
											<col width="100" align="center">	<!-- Task Status -->
											<col width="100" align="center">	<!-- Serial# -->
											<col width="100" align="center">	<!-- Model -->
											<col width="160" align="center">	<!-- Customer -->
											<col width="160" align="center">	<!-- Address -->
											<col width="100" align="center">	<!-- Phone -->
											<col width="160" align="center">	<!-- Available -->
											<col width="140" align="center">	<!-- Receive Date -->
											<col width="160" align="center">	<!-- Assignee -->
											<col width="160" align="center">	<!-- SR Type -->
											<col width="160" align="center">	<!-- Bill Code -->
											<col width="160" align="center">	<!-- Summary -->
											<col width="140" align="center">	<!-- Complete -->
											<col width="70"  align="center">	<!-- Response Time -->
											<col width="160" align="center">	<!-- Problem -->
											<col width="160" align="center">	<!-- Location -->
											<col width="160" align="center">	<!-- Resolution -->
											<col width="160" align="center">	<!-- Correction -->
											<col width="160" align="center">	<!-- Collector -->
											<tr height="35">
												<td id="AH0"  class="pClothBs colFix">SR#</td>
												<td id="AH1"  class="pClothBs colFix">Task#</td>
												<td id="AH2"  class="pClothBs">Vendor SR#</td>
												<td id="AH3"  class="pClothBs">SR Status</td>
												<td id="AH4"  class="pClothBs">Task Status</td>
												<td id="AH5"  class="pClothBs">Serial#</td>
												<td id="AH6"  class="pClothBs">Model</td>
												<td id="AH7"  class="pClothBs">Customer</td>
												<td id="AH8"  class="pClothBs">Address</td>
												<td id="AH9"  class="pClothBs">Phone</td>
												<td id="AH10" class="pClothBs">Available</td>
												<td id="AH11" class="pClothBs">Receive Date</td>
												<td id="AH12" class="pClothBs">Assignee</td>
												<td id="AH13" class="pClothBs">SR Type</td>
												<td id="AH14" class="pClothBs">Bill Code</td>
												<td id="AH15" class="pClothBs">Summary</td>
												<td id="AH16" class="pClothBs">Complete</td>
												<td id="AH17" class="pClothBs">Response<br>Time</td>
												<td id="AH18" class="pClothBs">Problem</td>
												<td id="AH19" class="pClothBs">Location</td>
												<td id="AH20" class="pClothBs">Resolution</td>
												<td id="AH21" class="pClothBs">Correction</td>
												<td id="AH22" class="pClothBs">Collector</td>
											</tr>
										</table>
									</div>
									<div id="rightTbl" style="width:1105px; height:494px; display:block; overflow:scroll; margin:0px; padding:0px;">
										<table style="table-layout:fixed;"  width="3040px" border="1" cellpadding="1" cellspacing="0" id="A">
											<col width="80"  align="center">	<!-- SR# -->
											<col width="80"  align="center">	<!-- Task# -->
											<col width="100" align="center">	<!-- Vendor SR# -->
											<col width="100" align="center">	<!-- SR Status -->
											<col width="100" align="center">	<!-- Task Status -->
											<col width="100" align="center">	<!-- Serial# -->
											<col width="100" align="center">	<!-- Model -->
											<col width="160" align="center">	<!-- Customer -->
											<col width="160" align="center">	<!-- Address -->
											<col width="100" align="center">	<!-- Phone -->
											<col width="160" align="center">	<!-- Available -->
											<col width="140" align="center">	<!-- Receive Date -->
											<col width="160" align="center">	<!-- Assignee -->
											<col width="160" align="center">	<!-- SR Type -->
											<col width="160" align="center">	<!-- Bill Code -->
											<col width="160" align="center">	<!-- Summary -->
											<col width="140" align="center">	<!-- Complete -->
											<col width="70"  align="center">	<!-- Response Time -->
											<col width="160" align="center">	<!-- Problem -->
											<col width="160" align="center">	<!-- Location -->
											<col width="160" align="center">	<!-- Resolution -->
											<col width="160" align="center">	<!-- Correction -->
											<col width="160" align="center">	<!-- Collector -->
											<% int idx = 0; %>
											<ezf:row ezfHyo="A">
											<tr height="25">
												<td>
													<a href="<%= getCustomAppURL("EXTNE307T020") %>?taskNum=<%=bMsg.A.no(idx).svcTaskNum.getValue()%>" target="A" border="0">
														<ezf:label name="fsrNum" ezfName="fsrNum" ezfHyo="A" ezfArrayNo="0" />
													</a>													
												</td>
												<td>
													<a href="<%= getCustomAppURL("EXTNE307T020") %>?taskNum=<%=bMsg.A.no(idx).svcTaskNum.getValue()%>" target="A" border="0">
														<ezf:label name="svcTaskNum" ezfName="svcTaskNum" ezfHyo="A" ezfArrayNo="0" />
													</a>													
												</td>
												<% idx++; %>
												<td><ezf:inputText name="ittNum" ezfName="ittNum" value="WWWWWWWWW1WWWWWWWWW2WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcTaskStsDescTxt_S" ezfName="svcTaskStsDescTxt_S" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcTaskStsDescTxt_V" ezfName="svcTaskStsDescTxt_V" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="serNum" ezfName="serNum" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="shipToFullAddr" ezfName="shipToFullAddr" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="custTelNum" ezfName="custTelNum" value="WWWWWWWWW1WWWWWWWWW2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxScrItem30Txt" ezfName="xxScrItem30Txt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="rqstDtTmTsTxt_A" ezfName="rqstDtTmTsTxt_A" value="03/03/2016 10:10:10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="techNm" ezfName="techNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxDsRtrnRsnNm_A" ezfName="xxDsRtrnRsnNm_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxDsRtrnRsnNm_B" ezfName="xxDsRtrnRsnNm_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="svcCmntTxt" ezfName="svcCmntTxt" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="rqstDtTmTsTxt_B" ezfName="rqstDtTmTsTxt_B" value="03/03/2016 10:10:10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:label name="xxRcvTm" ezfName="xxRcvTm" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="xxScrItem54Txt_A" ezfName="xxScrItem54Txt_A" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxScrItem54Txt_B" ezfName="xxScrItem54Txt_B" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxDsRtrnRsnNm_C" ezfName="xxDsRtrnRsnNm_C" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="xxDsRtrnRsnNm_D" ezfName="xxDsRtrnRsnNm_D" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" tabindex=\"-1\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
											</tr>
											<ezf:skip>
											</ezf:skip>
											</ezf:row>
										</table>
									</div>
								</div><!-- right table -->
							</div><!-- parent box -->
						</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<style TYPE="text/css">
<!--
	tr.checkLineBGcolor{background-color:yellow;}
	a img{border-style:none;}
-->
</style>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, true);
</script>


<%-- **** Task specific area ends here **** --%>
