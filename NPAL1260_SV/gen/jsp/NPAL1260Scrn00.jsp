<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240214095732 --%>
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
			<input type="hidden" name="pageID" value="NPAL1260Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tech Parts Request Inquiry">

<CENTER>
<TABLE height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
  <TBODY>
  <TR>
    <TD>
      <%-- ********************** --%>
      <%-- *** Upper Tab Area *** --%>
      <%-- ********************** --%>
      <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
        <ezf:skip>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                <tr>
                    <td width="1070px" height="28px" valign="bottom">
                        <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                            <tr title="Tech Parts Request Inquiry">
                                <td width="107px" align="center" class="same">Tech Req Inq</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </ezf:skip>

      <DIV class="pTab_BODY">
                        <!-- ################################################## Search Criteria - [START] ################################################## -->
                        <table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
                            <col width="152">
                            <col width="345">
                            <col width="110">
                            <col width="300">
                            <col width="">
                            <tr>
                                <td class="stab">Saved Search Options</td>
                                <td>
                                    <ezf:select name="srchOptPk_SE" ezfName="srchOptPk_SE" ezfBlank="1" ezfCodeName="srchOptPk_CD" ezfDispName="srchOptNm_DI" otherEvent1=" onchange=\"sendServer('OnChange_SearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm_TX" ezfName="srchOptNm_TX" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                <td>
                                    <ezf:inputButton name="SaveSearchOption" value="Save Search" htmlClass="pBtn8" />
                                    <ezf:inputButton name="DeleteSearchOption" value="Delete Search" htmlClass="pBtn8" />
                                </td>
                            </tr>
                        </table>
            <hr>

            <!-- header -->
            <table style=" MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                <col width=""  align="left">
                <col width=""  align="left">
                <col width=""  align="left">
                <col width="15"  align="left">
                <tr>
                    <td valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" >
                            <col width="120" align="left">
                            <col width="" align="left">
                            <col width="" align="center">
                            <col width="" align="right">
                            <col width="15" align="left">
                            <!-- 1 -->
                            <tr height="20">
                                <td class="stab">Tech Request#</td>
                                <td colspan="3"><ezf:inputText name="prchReqNum_H1" ezfName="prchReqNum_H1" value="12345678" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 2 -->
                            <tr height="20">
                                <td class="stab">Tech Request Type</td>
                                <td colspan="3">
                                    <ezf:select name="prchReqTpCd_SE" ezfName="prchReqTpCd_SE" ezfBlank="1" ezfCodeName="prchReqTpCd_CD" ezfDispName="prchReqTpDescTxt_DI" otherEvent1=" onchange=\"sendServer('OnChange_TechRequestType')\"" otherAttr=" style=\"width:215px;\""/>
                                </td>
                                <td></td>
                            </tr>
                            <!-- 3 -->
                            <tr height="20">
                                <td class="stab"><ezf:anchor name="mdseCd_AC" ezfName="mdseCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" >Item Number</ezf:anchor></td>
                                <td colspan="3"><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="WWWWWWWW" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 4 -->
                            <tr height="20">
                                <td class="stab">Line Type</td>
                                <td colspan="3">
                                    <ezf:select name="prchReqLineTpCd_SE" ezfName="prchReqLineTpCd_SE" ezfBlank="1" ezfCodeName="prchReqLineTpCd_CD" ezfDispName="prchReqLineTpDescTxt_DI" otherAttr=" style=\"width:215px;\""/>
                                </td>
                                <td></td>
                            </tr>
                            <!-- 5 -->
                            <tr height="20">
                                <td class="stab">Line Status</td>
                                <td colspan="3">
                                    <ezf:select name="prchReqLineStsCd_SE" ezfName="prchReqLineStsCd_SE" ezfBlank="1" ezfCodeName="prchReqLineStsCd_CD" ezfDispName="prchReqLineStsDescTxt_DI" otherAttr=" style=\"width:215px;\""/>
                                </td>
                                <td></td>
                            </tr>
                            <!-- 6 -->
                            <tr height="20">
                                <td class="stab">Date Created</td>
                                <td colspan="3">
                                    <ezf:inputText name="prchReqCratDt_HF" ezfName="prchReqCratDt_HF" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'prchReqCratDt_HF', 4 );">
                                    -
                                    <ezf:inputText name="prchReqCratDt_HT" ezfName="prchReqCratDt_HT" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'prchReqCratDt_HT', 4 );">
                                </td>
                                <td></td>
                            </tr>
                            <!-- 7 -->
                            <tr height="20">
                                <td class="stab">Date Needed</td>
                                <td colspan="3">
                                    <ezf:inputText name="rqstRcvDt_HF" ezfName="rqstRcvDt_HF" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rqstRcvDt_HF', 4 );">
                                    -
                                    <ezf:inputText name="rqstRcvDt_HT" ezfName="rqstRcvDt_HT" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'rqstRcvDt_HT', 4 );">
                                </td>
                                <td></td>
                            </tr>
                            <!-- 8 -->
                            <tr height="20">
                                <td class="stab">Ship Date</td>
                                <td colspan="3">
                                    <ezf:inputText name="shipDt_HF" ezfName="shipDt_HF" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'shipDt_HF', 4 );">
                                    -
                                    <ezf:inputText name="shipDt_HT" ezfName="shipDt_HT" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'shipDt_HT', 4 );">
                                </td>
                                <td></td>
                            </tr>
                            <!-- 9 -->
                            <tr height="20">
                                <td class="stab">Shipment Line View</td>
                                <td>
                                    <ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />
                                </td>
                                <td class="stab">Freeze Flag</td>
                                <td>
                                    <ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />
                                </td>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" >
                            <col width="120" align="left">
                            <col width="" align="left">
                            <col width="" align="center">
                            <col width="" align="right">
                            <col width="15" align="left">
                            <!-- 1 -->
                            <tr height="20">
                                <td class="stab">Tech Territory</td>
                                <td colspan="3">
                                    <ezf:select name="lineBizTpCd_SE" ezfName="lineBizTpCd_SE" ezfBlank="1" ezfCodeName="lineBizTpCd_CD" ezfDispName="lineBizTpDescTxt_DI" otherAttr=" style=\"width:215px;\""/>
                                </td>
                                <td></td>
                            </tr>
                            <!-- 2 -->
                            <tr height="20">
                                <td class="stab">Document Source Type</td>
                                <td colspan="3">
                                    <ezf:select name="prchReqSrcTpCd_SE" ezfName="prchReqSrcTpCd_SE" ezfBlank="1" ezfCodeName="prchReqSrcTpCd_CD" ezfDispName="prchReqSrcTpDescTxt_DI" otherAttr=" style=\"width:215px;\""/>
                                </td>
                                <td></td>
                            </tr>
                            <!-- 3 -->
                            <tr height="20">
                                <td class="stab">Service Request#</td>
                                <td colspan="3"><ezf:inputText name="fsrNum_H1" ezfName="fsrNum_H1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 4 -->
                            <tr height="20">
                                <td class="stab">Service Request Task#</td>
                                <td colspan="3"><ezf:inputText name="svcTaskNum_H1" ezfName="svcTaskNum_H1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 5 -->
                            <tr height="20">
                                <td class="stab">Service Request Serial#</td>
                                <td colspan="3"><ezf:inputText name="svcMachSerNum_H1" ezfName="svcMachSerNum_H1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 6 -->
                            <tr height="20">
                                <td class="stab">Purchase Order#</td>
                                <td colspan="3"><ezf:inputText name="poOrdNum_H1" ezfName="poOrdNum_H1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 7 -->
                            <tr height="20">
                                <td class="stab">Shipping Order#</td>
                                <td colspan="3"><ezf:inputText name="rwsRefNum_H1" ezfName="rwsRefNum_H1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 8 -->
                            <tr height="20">
                                <td class="stab">Requisition#</td>
                                <td colspan="3"><ezf:inputText name="prchReqNum_H2" ezfName="prchReqNum_H2" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td></td>
                            </tr>
                            <!-- 9 -->
                            <tr height="20">
                                <td class="stab">Insourced Flag</td>
                                <td>
                                    <ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" />
                                </td>
                                <td class="stab">Purchased Flag</td>
                                <td>
                                    <ezf:inputCheckBox name="xxChkBox_H4" ezfName="xxChkBox_H4" value="Y" />
                                </td>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" >
                            <col width="135" align="left">
                            <col width="" align="left">
                            <col width="30" align="center">
                            <col width="" align="left">
                            <!-- 1 -->
                            <tr height="20">
                                <td class="stab"><ezf:anchor name="rtlWhCd_AC" ezfName="rtlWhCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_SrcWhSwh" >Source WH / SWH</ezf:anchor></TD>
                                <td colspan="3">
                                  <ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="A02" otherAttr=" maxlength=\"20\" size=\"15\" ezftoupper=\"\""/>
                                  <ezf:inputText name="rtlSwhCd_H1" ezfName="rtlSwhCd_H1" value="NEW" otherAttr=" maxlength=\"20\" size=\"18\" ezftoupper=\"\""/>
                                </td>
                            </tr>
                            <!-- 2 -->
                            <tr height="20">
                                <td class="stab"><ezf:anchor name="prntVndCd_AC" ezfName="prntVndCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_SupplierAndSite" >PO Supplier / Site Name (*)</ezf:anchor></TD>
                                <td colspan="3">
                                  <ezf:inputText name="prntVndNm_H1" ezfName="prntVndNm_H1" value="%CANON%" otherAttr=" maxlength=\"20\" size=\"15\" ezftoupper=\"\""/>
                                  <ezf:inputText name="locNm_H1" ezfName="locNm_H1" value="%CAN%" otherAttr=" maxlength=\"20\" size=\"18\" ezftoupper=\"\""/>
                                </td>
                            </tr>
                            <!-- 3 -->
                            <tr height="20">
                                <td class="stab"><ezf:anchor name="destRtlWhCd_AC" ezfName="destRtlWhCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_DestWhSwh" >Destination WH / SWH</ezf:anchor></TD>
                                <td colspan="3">
                                  <ezf:inputText name="destRtlWhCd_H1" ezfName="destRtlWhCd_H1" value="TECH00001" otherAttr=" maxlength=\"20\" size=\"15\" ezftoupper=\"\""/>
                                  <ezf:inputText name="destRtlSwhCd_H1" ezfName="destRtlSwhCd_H1" value="G" otherAttr=" maxlength=\"20\" size=\"18\" ezftoupper=\"\""/>
                                </td>
                            </tr>
                            <!-- 4 -->
                            <tr height="20">
                                <td class="stab"><ezf:anchor name="psnCd_AC" ezfName="psnCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Tech" >Technician Name (*)</ezf:anchor></td>
                                <td colspan="3"><ezf:inputText name="techNm_H1" ezfName="techNm_H1" value="Tech Taro" otherAttr=" size=\"35\" maxlength=\"30\""/></td>
                            </tr>
                            <!-- 5 -->
                            <tr height="20">
                                <td class="stab"><ezf:anchor name="shipToCustCd_AC" ezfName="shipToCustCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" >Customer Name (*)</ezf:anchor></td>
                                <td colspan="3"><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="Name" otherAttr=" size=\"35\" maxlength=\"30\" ezftoupper=\"\""/></td>
                            </tr>
                            <!-- 6 -->
                            <tr height="20">
                                <td class="stab"><ezf:anchor name="carrCd_AC" ezfName="carrCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" >Requested Carrier (*)</ezf:anchor></td>
                                <td colspan="3"><ezf:inputText name="carrNm_H1" ezfName="carrNm_H1" value="UPS" otherAttr=" size=\"35\" maxlength=\"30\" ezftoupper=\"\""/></td>
                            </tr>
                            <!-- 7 -->
                            <tr height="20">
                                <td class="stab">Actual Service Level</td>
                                <td colspan="3">
                                    <ezf:select name="shpgSvcLvlCd_SE" ezfName="shpgSvcLvlCd_SE" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_DI" otherAttr=" style=\"width:250px;\""/>
                                </td>
                            </tr>
                            <!-- 8 -->
                            <tr height="20">
                                <td class="stab">Tracking Number</td>
                                <td colspan="3"><ezf:inputText name="proNum_H1" ezfName="proNum_H1" value="Technician" otherAttr=" size=\"35\" maxlength=\"30\" ezftoupper=\"\""/></td>
                            </tr>
                            <!-- 9 -->
                            <tr height="20">
                                <td class="stab">Back Order Flag</td>
                                <td>
                                    <ezf:inputCheckBox name="xxChkBox_H5" ezfName="xxChkBox_H5" value="Y" />
                                </td>
                                <td colspan="2" align="right">
                                    <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
                                    <ezf:inputButton name="New_PR_Entry" value="New" htmlClass="pBtn6" />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
                    
            <hr>

            <%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
<TABLE style="margin-left: 11px; width: 100%;" >
	<COLGROUP>
		<COL align="left" width="">
		<COL align="right" width="">
	</COLGROUP>
	<TBODY>
		<TR>
			<TD>
				<TABLE cellSpacing="0" cellPadding="1" border="0">
					<COLGROUP>
						<COL width="">
					</COLGROUP>
					<TBODY>
						<TR>
							<TD>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
			</TD>
			<TD>
				<TABLE cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
					<TBODY>
						<tr align="right">
							<td>
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								</jsp:include>
							</td>
						</tr>
					</TBODY>
				</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

     <!-- ################################################## Search Result   - [START] ################################################## -->
<div id="parentBoxA">
	<TABLE cellSpacing="0" cellPadding="0" border="0" style="margin-left: 11px;">
		<tr>
			<td>
				<div style="float:left; display:block"> <!-- left table -->
					<div id='leftTblHead' style="display:block;">
					</div>
					<div id='leftTbl' style="float:left; display:block; height:233px; overflow:hidden; margin-left: 0px; padding:0px; padding-bottom: 20px">
					</div>
				</div>  <!-- left table -->
				<div style="float:left"> <!-- right table -->
					<div id='rightTblHead' style="width:1093px; display:block; overflow:hidden; margin:0px;padding:0px;">
						<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" height="28" id="AHEAD" width="5636px">
							<col width="88px" align="center"><%-- Tech Request # --%>
							<col width="60"  align="center"><!-- Line # -->
							<col width="150" align="center"><!-- Tech Request Type -->
							<col width="120" align="center"><!-- Line Type -->
							<col width="130" align="center"><!-- Item Number -->
							<col width="150" align="center"><!-- Item Description -->
							<col width="100" align="center"><!-- Req Qty -->
							<col width="100"  align="center"><!-- Source Type -->
							<col width="88"  align="center"><!-- Source WH -->
							<col width="120" align="center"><!-- Source WH Name -->
							<col width="88"  align="center"><!-- Source SWH -->
							<col width="120" align="center"><!-- Source SWH Name -->
							<col width="88"  align="center"><!-- Destination WH -->
							<col width="120" align="center"><!-- Destination WH Name -->
							<col width="88" align="center"><!-- Destination SWH -->
							<col width="120" align="center"><!-- Destination SWH Name -->
							<col width="88"  align="center"><!-- Line Status -->
							<col width="100" align="center"><!-- Order Qty -->
							<col width="100" align="center"><!-- Fulfilled Qty -->
							<col width="100" align="center"><!-- Received Qty -->
							<col width="100" align="center"><!-- PO B/O Qty -->
							<col width="76"  align="center"><!-- Freeze Flag -->
							<col width="76"  align="center"><!-- Insourced Flag -->
							<col width="76"  align="center"><!-- Purchased Flag -->
							<col width="76"  align="center"><!-- Cancelled Flag -->
							<col width="50"  align="center"><!-- Ref# -->
							<col width="170" align="center"><!-- Date Created -->
							<col width="170" align="center"><!-- Date Needed -->
							<col width="170" align="center"><!-- Ship Date -->
							<col width="170" align="center"><!-- Date Received -->
							<col width="170" align="center"><!-- Ack Date B/O -->
							<col width="100" align="center"><!-- Tech Territory -->
							<col width="150" align="center"><!-- Document Source Type -->
							<col width="150" align="center"><!-- Service Req # -->
							<col width="150" align="center"><!-- Service Req Task # -->
							<col width="150" align="center"><!-- Service Req Serial # -->
							<col width="88"  align="center"><!-- Purchase Order# -->
							<col width="88"  align="center"><!-- Shipping Order# -->
							<col width="150" align="center"><!-- Alternate Shipping Order# -->
							<col width="88"  align="center"><!-- Requisition# -->
							<col width="170" align="center"><!-- ETA -->
							<col width="170" align="center"><!-- Delivery Date -->
							<col width="70"  align="center"><!-- Supplier -->
							<col width="150" align="center"><!-- Supplier Name -->
							<col width="100"  align="center"><!-- Site -->
							<col width="150" align="center"><!-- Site Name -->
							<col width="120" align="center"><!-- Ship To Customer Code -->
							<col width="150" align="center"><!-- Ship To Customer Name -->
							<col width="90" align="center"><!-- Technician Code -->
							<col width="150" align="center"><!-- Technician Name -->
							<col width="150" align="center"><!-- Requested Carrier Name -->
							<col width="120" align="center"><!-- Actual Service Level -->
							<col width="80" align="center"><!-- Tracking Number -->
							<tr height="37">
								<td id="AH0" class="pClothBs">Tech Request #</td>
								<td id="AH1" class="pClothBs">Line #</td>
								<td id="AH2" class="pClothBs">Tech Request Type</td>
								<td id="AH3" class="pClothBs">Line Type</td>
								<td id="AH4" class="pClothBs">Item Number</td>
								<td id="AH5" class="pClothBs">Item Description</td>
								<td id="AH6" class="pClothBs">Req Qty</td>
								<td id="AH7" class="pClothBs">Source Type</td>
								<td id="AH8" class="pClothBs">Source<br/>WH</td>
								<td id="AH9" class="pClothBs">Source<br/>WH Name</td>
								<td id="AH10" class="pClothBs">Source<br/>SWH</td>
								<td id="AH11" class="pClothBs">Source<br/>SWH Name</td>
								<td id="AH12" class="pClothBs">Destination<br/>WH</td>
								<td id="AH13" class="pClothBs">Destination<br/>WH Name</td>
								<td id="AH14" class="pClothBs">Destination<br/>SWH</td>
								<td id="AH15" class="pClothBs">Destination<br/>SWH Name</td>
								<td id="AH16" class="pClothBs">Line Status</td>
								<td id="AH17" class="pClothBs">Order Qty</td>
								<td id="AH18" class="pClothBs">Fulfilled Qty</td>
								<td id="AH19" class="pClothBs">Received Qty</td>
								<td id="AH20" class="pClothBs">PO B/O Qty</td>
								<td id="AH21" class="pClothBs">Freeze<br>Flag</td>
								<td id="AH22" class="pClothBs">Insourced<br>Flag</td>
								<td id="AH23" class="pClothBs">Purchased<br>Flag</td>
								<td id="AH24" class="pClothBs">Cancelled<br>Flag</td>
								<td id="AH25" class="pClothBs">Ref#</td>
								<td id="AH26" class="pClothBs">Date Created</td>
								<td id="AH27" class="pClothBs">Date & Time Needed</td>
								<td id="AH28" class="pClothBs">Ship Date</td>
								<td id="AH29" class="pClothBs">Date Received</td>
								<td id="AH30" class="pClothBs">Ack Date B/O</td>
								<td id="AH31" class="pClothBs">Tech Territory</td>
								<td id="AH32" class="pClothBs">Document Source Type</td>
								<td id="AH33" class="pClothBs">Service Request#</td>
								<td id="AH34" class="pClothBs">Service Request Task#</td>
								<td id="AH35" class="pClothBs">Service Request Serial#</td>
								<td id="AH36" class="pClothBs">Purchase Order#</td>
								<td id="AH37" class="pClothBs">Shipping Order#</td>
								<td id="AH38" class="pClothBs">Alternate Shipping Order#</td>
								<td id="AH39" class="pClothBs">Requisition#</td>
								<td id="AH40" class="pClothBs">ETA</td>
								<td id="AH41" class="pClothBs">Delivery Date</td>
								<td id="AH42" class="pClothBs">Supplier</td>
								<td id="AH43" class="pClothBs">Supplier Name</td>
								<td id="AH44" class="pClothBs">Site</td>
								<td id="AH45" class="pClothBs">Site Name</td>
								<td id="AH46" class="pClothBs">Ship To Customer</td>
								<td id="AH47" class="pClothBs">Ship To Customer Name</td>
								<td id="AH48" class="pClothBs">Technician </td>
								<td id="AH49" class="pClothBs">Technician Name</td>
								<td id="AH50" class="pClothBs">Requested Carrier</td>
								<td id="AH51" class="pClothBs">Actual Service Level</td>
								<td id="AH52" class="pClothBs">Tracking#</td>
							</tr>
						</table>
					</div> <!-- rightTblHead -->
					<div  id="rightTbl" style="width:1110px; height:250px; display:block; overflow:scroll; margin:0px; padding:0px;" >
						<!-- Right TBL Main -->
						<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="5655px">
							<col width="88px" align="left"><%-- Tech Request # --%>
							<col width="60"  align="left"><!-- Line # -->
							<col width="150" align="left"><!-- Tech Request Type -->
							<col width="120" align="left"><!-- Line Type -->
							<col width="130" align="left"><!-- Item Number -->
							<col width="150" align="center"><!-- Item Description -->
							<col width="100" align="right"><!-- Req Qty -->
							<col width="100"  align="left"><!-- Source Type -->
							<col width="88"  align="left"><!-- Source WH -->
							<col width="120" align="center"><!-- Source WH Name -->
							<col width="88"  align="left"><!-- Source SWH -->
							<col width="120" align="center"><!-- Source SWH Name -->
							<col width="88"  align="left"><!-- Tech WH -->
							<col width="120" align="center"><!-- Tech WH Name -->
							<col width="88"  align="left"><!-- Tech SWH -->
							<col width="120" align="center"><!-- Tech SWH Name -->
							<col width="88"  align="left"><!-- Line Status -->
							<col width="100" align="right"><!-- Order Qty -->
							<col width="100" align="right"><!-- Fulfilled Qty -->
							<col width="100" align="right"><!-- Received Qty -->
							<col width="100" align="right"><!-- PO B/O Qty -->
							<col width="76"  align="center"><!-- Freeze Flag -->
							<col width="76"  align="center"><!-- Insourced Flag -->
							<col width="76"  align="center"><!-- Purchased Flag -->
							<col width="76"  align="center"><!-- Cancelled Flag -->
							<col width="50"  align="left"><!-- Ref# -->
							<col width="170" align="left"><!-- Date Created -->
							<col width="170" align="left"><!-- Date Needed -->
							<col width="170" align="left"><!-- Ship Date -->
							<col width="170" align="left"><!-- Date Received -->
							<col width="170" align="left"><!-- Ack Date B/O -->
							<col width="100" align="left"><!-- Tech Territory -->
							<col width="150" align="center"><!-- Document Source Type -->
							<col width="150" align="left"><!-- Service Req # -->
							<col width="150" align="left"><!-- Service Req Task # -->
							<col width="150" align="left"><!-- Service Req Serial # -->
							<col width="88"  align="left"><!-- Purchase Order# -->
							<col width="88"  align="left"><!-- Shipping Order# -->
							<col width="150" align="left"><!-- Alternate Shipping Order# -->
							<col width="88" align="left"><!-- Requisition# -->
							<col width="170" align="left"><!-- ETA -->
							<col width="170" align="left"><!-- Delivery Date -->
							<col width="70"  align="left"><!-- Supplier Code -->
							<col width="150" align="center"><!-- Supplier Name -->
							<col width="100"  align="left"><!-- Site Code -->
							<col width="150" align="center"><!-- Site Name -->
							<col width="120" align="left"><!-- Ship To Customer Code -->
							<col width="150" align="center"><!-- Ship To Customer Name -->
							<col width="90" align="left"><!-- Technician Code -->
							<col width="150" align="center"><!-- Technician Name -->
							<col width="150" align="center"><!-- Requested Carrier Name -->
							<col width="120" align="center"><!-- Actual Service Level -->
							<col width="80" align="left"><!-- Tracking Number -->
							<ezf:row ezfHyo="A">
								<TR height="21" id="id_row${EZF_ROW_NUMBER}">
									<td><ezf:anchor name="prchReqNum_D1" ezfName="prchReqNum_D1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Move_PR_Entry" otherAttr=" id=\"prchReqNum_{EZF_ROW_NUMBER}\""><ezf:label name="prchReqNum_D1" ezfName="prchReqNum_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"cursor: pointer;\""/></ezf:anchor></td>
									<td><ezf:label name="prchReqLineNum_D1" ezfName="prchReqLineNum_D1" ezfHyo="A" ezfArrayNo="0" />.<ezf:label name="prchReqLineSubNum_D1" ezfName="prchReqLineSubNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="prchReqTpDescTxt_D1" ezfName="prchReqTpDescTxt_D1" value="Rush" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
									<td><ezf:inputText name="prchReqLineTpDescTxt_D1" ezfName="prchReqLineTpDescTxt_D1" value="Local" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/></td>
									<td><ezf:label name="mdseCd_D1" ezfName="mdseCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\""/></td>
									<td><ezf:label name="prchReqDispQty_D1" ezfName="prchReqDispQty_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="procrTpDescTxt_D1" ezfName="procrTpDescTxt_D1" value="Warehouse" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"60\""/></td>
									<td><ezf:label name="srcRtlWhCd_D1" ezfName="srcRtlWhCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="rtlWhNm_D1" ezfName="rtlWhNm_D1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
									<td><ezf:label name="srcRtlSwhCd_D1" ezfName="srcRtlSwhCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="rtlSwhNm_D1" ezfName="rtlSwhNm_D1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
									<td><ezf:label name="destRtlWhCd_D1" ezfName="destRtlWhCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="rtlWhNm_D2" ezfName="rtlWhNm_D2" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
									<td><ezf:label name="destRtlSwhCd_D1" ezfName="destRtlSwhCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="rtlSwhNm_D2" ezfName="rtlSwhNm_D2" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
									<td><ezf:inputText name="prchReqLineStsDescTxt_D1" ezfName="prchReqLineStsDescTxt_D1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
									<td><ezf:label name="ordQty_D1" ezfName="ordQty_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="shipQty_D1" ezfName="shipQty_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="rwsPutAwayQty_D1" ezfName="rwsPutAwayQty_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="ordQty_D2" ezfName="ordQty_D2" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="prchReqFrzFlg_D1" ezfName="prchReqFrzFlg_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="insrcFlg_D1" ezfName="insrcFlg_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="poCratFlg_D1" ezfName="poCratFlg_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="znFlg_D1" ezfName="znFlg_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="origPrchReqLineNum_D1" ezfName="origPrchReqLineNum_D1" ezfHyo="A" ezfArrayNo="0" /><ezf:label name="xxAddInvNumTxt_D1" ezfName="xxAddInvNumTxt_D1" ezfHyo="A" ezfArrayNo="0" /><ezf:label name="origPrchReqLineSubNum_D1" ezfName="origPrchReqLineSubNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPopPrm_D1" ezfName="xxPopPrm_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPopPrm_D2" ezfName="xxPopPrm_D2" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPopPrm_D3" ezfName="xxPopPrm_D3" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPopPrm_D4" ezfName="xxPopPrm_D4" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPopPrm_D5" ezfName="xxPopPrm_D5" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="lineBizTpDescTxt_D1" ezfName="lineBizTpDescTxt_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="prchReqSrcTpDescTxt_D1" ezfName="prchReqSrcTpDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									<td><ezf:label name="fsrNum_D1" ezfName="fsrNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="svcTaskNum_D1" ezfName="svcTaskNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="svcMachSerNum_D1" ezfName="svcMachSerNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td>
										<ezf:anchor name="poOrdNum_D1" ezfName="poOrdNum_D1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenPoEntry" otherAttr=" id=\"poOrdNum_D1\" ezfanchortext=\"\"">
											<ezf:label name="poOrdNum_D1" ezfName="poOrdNum_D1" ezfHyo="A" ezfArrayNo="0" />
										</ezf:anchor>
									</td>
									<td>
										<ezf:anchor name="vndSoNum_D1" ezfName="vndSoNum_D1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SO" otherAttr=" ezfanchortext=\"\"">
                                        	<ezf:label name="vndSoNum_D1" ezfName="vndSoNum_D1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"cursor: pointer\""/>
                                        </ezf:anchor>
									</td>
									<td><ezf:label name="rwsRefNum_D1" ezfName="rwsRefNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="prchReqNum_D2" ezfName="prchReqNum_D2" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPopPrm_D7" ezfName="xxPopPrm_D7" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxPopPrm_D6" ezfName="xxPopPrm_D6" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="prntVndCd_D1" ezfName="prntVndCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="prntVndNm_D1" ezfName="prntVndNm_D1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									<td><ezf:label name="vndCd_D1" ezfName="vndCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="locNm_D1" ezfName="locNm_D1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									<td><ezf:label name="shipToCustCd_D1" ezfName="shipToCustCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="dsAcctNm_D1" ezfName="dsAcctNm_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									<td><ezf:label name="techTocCd_D1" ezfName="techTocCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:inputText name="techNm_D1" ezfName="techNm_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="carrNm_D1" ezfName="carrNm_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
									<td><ezf:inputText name="shpgSvcLvlNm_D1" ezfName="shpgSvcLvlNm_D1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
									<td align="center">
										<ezf:inputButton name="OpenWin_Tracking" value="Tracking#" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn5" otherAttr=" id=\"OpenWin_Tracking{EZF_ROW_NUMBER}\""/>
									</td>
								</tr>
							</ezf:row>
						</TABLE>
					</div>
				</div><!-- right table -->
			</TD>
		</TR>
	</TABLE>
</div><!-- parentBoxA -->
</div><!-- pTab_BODY -->
        <!-- ################################################## Search Result   - [E N D] ################################################## -->
      </TD>
    </TR>
    </TBODY>
  </TABLE>
</CENTER>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
</script>


<%-- **** Task specific area ends here **** --%>
