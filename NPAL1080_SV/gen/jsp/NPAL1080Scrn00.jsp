<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230330150109 --%>
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

<!--<%-- ====================================================================================
IMPORTANT: After generating JSP, replace followings:
    1. ezfArrayNo="\d+?"                                         ====> ezfArrayNo="<%= i %>"
 ==================================================================================== --%>-->
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NPAL1080Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tech Request Entry">

<CENTER>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <TBODY>
  <TR>
    <TD>
      <%-- ********************** --%>
      <%-- *** Upper Tab Area *** --%>
      <%-- ********************** --%>
      <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

      <DIV class="pTab_BODY">
      <!-- ################################################## Search Criteria - [START] ################################################## -->

        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;">
            <col width="360"  align="left">
            <col width="350"  align="left">
            <col width=""  align="left">
            <tr valign="top">
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" >
                        <!-- header column 1 -->
                        <colgroup>
                          <col align="left" width="120">
                          <col align="left" width="30">
                          <col align="center" width="20">
                          <col align="left" width="20">
                          <col align="left" width="5">
                          <col align="left" width="60">
                          <col align="left" width="">
                        </colgroup>
                        <tbody>
                            <tr height="20">
                              <TD class="stab"><LABEL>Tech Request#</LABEL></TD>
                              <TD colspan="3">
                                <ezf:inputText name="prchReqNum_H1" ezfName="prchReqNum_H1" value="12345678" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/>
                              </TD>
                              <td style="text-align:right;" colspan="3"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                            </tr>
                            <tr height="20">
                              <TD class="stab">Tech Request Type</TD>
                              <TD colspan="3">
                                <ezf:select name="prchReqTpCd_SE" ezfName="prchReqTpCd_SE" ezfBlank="1" ezfCodeName="prchReqTpCd_CD" ezfDispName="prchReqTpDescTxt_DI" otherEvent1=" onchange=\"sendServer('OnChange_RequestType')\"" otherAttr=" style=\"width:145px;\""/>
                              </TD>
                              <TD></TD>
                              <TD style="text-align: right;">Tool</TD>
                              <TD><ezf:inputCheckBox name="techExpRqstFlg" ezfName="techExpRqstFlg" value="Y" /></TD>
                            </tr>
                            <tr height="20">
                              <TD class="stab">Header Status</TD>
                              <TD colspan="3"><ezf:inputText name="prchReqStsDescTxt_H1" ezfName="prchReqStsDescTxt_H1" value="Open" otherAttr=" size=\"20\""/></TD>
                              <TD></TD>
                              <TD style="text-align: right;">Hazmat</TD>
                              <TD><ezf:inputCheckBox name="hazMatFlg" ezfName="hazMatFlg" value="Y" /></TD>
                            </tr>
                            <tr height="20">
                              <TD class="stab">Approval Status</TD>
                              <TD colspan="3"><ezf:inputText name="prchReqApvlStsDescTxt_H1" ezfName="prchReqApvlStsDescTxt_H1" value="Approved" otherAttr=" size=\"20\""/></TD>
                              <TD></TD>
                              <TD></TD>
                              <TD></TD>
                            </tr>
                            <tr height="20">
                              <TD class="stab"><LABEL>Date Created</LABEL></TD>
                              <TD colspan="6"><ezf:inputText name="prchReqCratDt_H1" ezfName="prchReqCratDt_H1" value="06/12/2015" otherAttr=" size=\"10\""/>
                                  <ezf:inputText name="xxDtNm_H1" ezfName="xxDtNm_H1" value="03:00 PM" otherAttr=" size=\"8\""/>
                              </TD>
                            </tr>
                            <tr height="20">
                              <TD class="stab"><LABEL>Date & Time Needed</LABEL></TD>
                              <%@ page import="business.servlet.NPAL1080.NPAL1080BMsg" %>
                              <%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL" %>
                              <%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP" %>
                              <%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
                              <% NPAL1080BMsg scrMsg = (NPAL1080BMsg)databean.getEZDBMsg(); %>
                              <% String shpgSvcLvlCd = scrMsg.shpgSvcLvlCd_SE.getValue(); %>
                              <% String prchReqTp = scrMsg.prchReqTpCd_SE.getValue(); %>
                              <% if (ZYPCommonFunc.hasValue(prchReqTp) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && PRCH_REQ_TP.PREMIUM_RUSH.equals(prchReqTp) && SHPG_SVC_LVL.SCHD_DELIVERY.equals(shpgSvcLvlCd)) { %>
                                <TD class="stab" colspan="5">
                                  <ezf:inputText name="rqstRcvDt_H1" ezfName="rqstRcvDt_H1" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                  <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                  <ezf:inputText name="xxDtTm_H1" ezfName="xxDtTm_H1" value="13:00" otherAttr=" maxlength=\"5\" size=\"5\""/>
                                  <ezf:select name="rqstRcvDtTxt_SL" ezfName="rqstRcvDtTxt_SL" ezfCodeName="rqstRcvDtTxt_CD" ezfDispName="rqstRcvDtTxt_NM" htmlClass="stab" otherAttr=" style=\"width:50\""/>
                                </TD>
                              <% } else { %>
                                <TD class="stab" colspan="2">
                                  <ezf:inputText name="rqstRcvDt_H1" ezfName="rqstRcvDt_H1" value="06/12/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                  <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                </TD>
                                <TD class="stab" colspan="3" align="right">
                                  <ezf:select name="dsCondConstCd_SE" ezfName="dsCondConstCd_SE" ezfCodeName="dsCondConstCd_CD" ezfDispName="rqstRcvDtTxt_DI" otherEvent1=" onchange=\"sendServer('OnChange_RequestTime')\"" htmlClass="stab" otherAttr=" style=\"width:100px;\""/>
                                </TD>
                              <% } %>
                            </tr>
                        </tbody>
                    </table>
                </td>
                <td>
                    <!-- header column 2 -->
                    <table border="0" cellpadding="0" cellspacing="0" >
                        <colgroup>
                          <col align="left" width="130">
                          <col align="left" width="">
                          <col align="left" width="15">
                        </colgroup>
                        <tbody>
                            <tr height="20">
                              <TD class="stab"><LABEL>Document Source Type</LABEL></TD>
                              <TD colspan="6"><ezf:inputText name="prchReqSrcTpDescTxt_H1" ezfName="prchReqSrcTpDescTxt_H1" value="Phone" otherAttr=" size=\"27\""/></TD>
                            </tr>
                              <tr height="20">
                                  <TD class="stab"><LABEL>Requester</LABEL></TD>
                                  <TD colspan="2"><ezf:inputText name="fullPsnNm_H1" ezfName="fullPsnNm_H1" value="----+----1----+----2" otherAttr=" size=\"27\" ezftoupper=\"\""/></TD>
                              </tr>
                              <tr height="20">
                                  <TD class="stab"><LABEL>Service Request#</LABEL></TD>
                                  <TD colspan="2"><ezf:inputText name="fsrNum_H1" ezfName="fsrNum_H1" value="----+----1----+----2" otherAttr=" size=\"27\" maxlength=\"10\" ezftoupper=\"\""/></TD>
                              </tr>
                              <tr height="20">
                                  <TD class="stab"><LABEL>Service Request Task#</LABEL></TD>
                                  <TD colspan="2"><ezf:inputText name="svcTaskNum_H1" ezfName="svcTaskNum_H1" value="----+----1----+----2" otherAttr=" size=\"27\" maxlength=\"10\" ezftoupper=\"\""/></TD>
                              </tr>
                              <tr height="20">
                                  <TD class="stab"><LABEL>Service Request Serial#</LABEL></TD>
                                  <TD colspan="2"><ezf:inputText name="svcMachSerNum_H1" ezfName="svcMachSerNum_H1" value="----+----1----+----2" otherAttr=" size=\"27\" maxlength=\"30\" ezftoupper=\"\""/></TD>
                              </tr>
                              <TD class="stab">Attention To</TD>
                                  <TD colspan="2"><ezf:inputText name="ctacPsnNm_H1" ezfName="ctacPsnNm_H1" value="----+----1----+----2" otherAttr=" size=\"27\" maxlength=\"90\" ezftoupper=\"\""/></TD>
                            </tr>
                            </tr>
                        </tbody>
                    </table>
                </td>
                <td>
                    <!-- header column 3 -->
                    <table border="0" cellpadding="0" cellspacing="0" >
                        <colgroup>
                          <col width="130" align="left" />
                          <col width="80" align="left" />
                          <col width="30" align="left" />
                          <col width="" align="left" />
                        </colgroup>
                        <tbody>
                            <tr height="20">
                              <td class="stab"><ezf:anchor name="rqstTechTocCd_AC" ezfName="rqstTechTocCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Technician" >Technician Code / Name</ezf:anchor></td>
                              <td colspan="3">
                                  <ezf:inputText name="rqstTechTocCd_H1" ezfName="rqstTechTocCd_H1" value="Q99999" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                                  <ezf:inputButton name="SearchTechnician" value=">>" htmlClass="pBtn1" />
                                  <ezf:inputText name="techNm_H1" ezfName="techNm_H1" value="ED PETNER" otherAttr=" size=\"20\" maxlength=\"45\" ezftoupper=\"\""/>
                              </td>
                            </tr>
                            <tr height="20">
                              <td class="stab"><ezf:anchor name="rtlWhCd_AC" ezfName="rtlWhCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_TechWhSwh" >Tech WH / SWH</ezf:anchor></td>
                              <td colspan="3">
                                      <ezf:inputText name="destRtlWhCd_H1" ezfName="destRtlWhCd_H1" value="11331" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                      <ezf:inputText name="destRtlSwhCd_H1" ezfName="destRtlSwhCd_H1" value="G" otherAttr=" size=\"5\" maxlength=\"20\" ezftoupper=\"\""/>
                                      <ezf:inputButton name="GetTechLocInfo" value=">>" htmlClass="pBtn1" />
                              </td>
                            </tr>
                            <tr height="20">
                              <td class="stab"><ezf:anchor name="shipToCustCd_AC" ezfName="shipToCustCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" >Ship To Customer</ezf:anchor></td>
                              <td colspan="3">
                                    <ezf:inputText name="shipToCustCd_H1" ezfName="shipToCustCd_H1" value="38479346" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                      <ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="JOE GRAPHICS INC" otherAttr=" size=\"24\" ezftoupper=\"\""/></td>
                            </tr>
                            <tr height="20">
                              <td class="stab">Req Service Level</td>
                              <td colspan="3">
                                <ezf:select name="shpgSvcLvlCd_SE" ezfName="shpgSvcLvlCd_SE" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_DI" otherEvent1=" onchange=\"sendServer('OnChange_RequestServiceLevel')\"" otherAttr=" style=\"width:257px;\""/>
                              </td>
                            </tr>
                            <tr height="20">
                              <td class="stab"><ezf:anchor name="carrCd_AC" ezfName="carrCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" >Requested Carrier</ezf:anchor></td>
                              <td colspan="3"><ezf:inputText name="carrNm_H1" ezfName="carrNm_H1" value="UPS" otherAttr=" size=\"36\" maxlength=\"60\" ezftoupper=\"\""/></td>
                            </tr>
                            <tr  height="20">
                              <td class="stab">Description</td>
                              <td colspan="3"><ezf:inputText name="prchReqCmntTxt_H1" ezfName="prchReqCmntTxt_H1" value="Split Tect Request #00000000" otherAttr=" size=\"36\" maxlength=\"960\""/></td>
                            </tr>
                            <tr height="20">
                              <TD style="text-align:right;" colspan="4">
                                <ezf:inputButton name="Copy" value="Copy" htmlClass="pBtn6" />
                                <ezf:inputButton name="HeaderCancel" value="Cancel" htmlClass="pBtn6" />
                                <ezf:inputButton name="HeaderClose" value="Close" htmlClass="pBtn6" />
                                <ezf:inputButton name="OpenWin_ApproveHistory" value="Apprvl Hist" htmlClass="pBtn6" />
                                <ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn6" />
                                <ezf:inputHidden name="xxBtnFlg_H1" ezfName="xxBtnFlg_H1" />
                              </TD>
                              <td class="pAuditInfo">
                                <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs_H1" />
                                <ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm_H1" />
                                <ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs_H1" />
                                <ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm_H1" />
                                <ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm_H1" />
                              </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>

        <hr>

        <!-- ################################################## Detail   - [START] ################################################## -->
        <%-- ###TAB - HEAD --%>
        <div class="pTab_HEAD">
            <ul>
                <table width="900" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="96%">
                            <div>
                                <li title="Detail" id="Detail" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Detail" >Detail</ezf:anchor></li>
                                <li title="Header" id="Header" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Header" >Header</ezf:anchor></li>
                            </div>
                        </td>
                    </tr>
                </table>
            </ul>
        </div>

        <div class="pTab_BODY_In">
            <div style="height: 370px;" >
                <c:choose>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab == 'Header'}">
                        <div id="TabContent-Header">
                            <script type="text/javascript">
                                document.getElementById("Header").className = "pTab_ON";
                                document.getElementById("Detail").className = "pTab_OFF";
                            </script>
                            <table border="0" cellpadding="0" cellspacing="0" style="margin-top:3px;margin-left:10px;">
                                <col width="360">
                                <col width="10">
                                <col width="360">
                                <col width="10">
                                <col width="360">
                                <tr>
                                    <td style="vertical-align: top;">
                                        <!-- Ship To Customer -->
                                        <fieldset>
                                            <legend><font color="black">Ship To Location</font></legend>
                                            <table border="0" cellpadding="0" cellspacing="0" >
                                                <col width="85">
                                                <col width="255">
                                                <!-- 1 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">Code</td>
                                                    <td ><ezf:inputText name="shipToCustCd_H2" ezfName="shipToCustCd_H2" value="11331G" otherAttr=" size=\"35\""/></td>
                                                </tr>
                                                <tr style="height: 24px;">
                                                    <td class="stab">Name</td>
                                                    <td><ezf:inputText name="locNm_H2" ezfName="locNm_H2" value="Yasuto Nishiwaki" otherAttr=" size=\"35\""/></td>
                                                </tr>
                                                <!-- 1 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">Additional Name</td>
                                                    <td><ezf:inputText name="shipToAddlLocNm_H2" ezfName="shipToAddlLocNm_H2" otherAttr=" size=\"35\""/></td>
                                                </tr>
                                                <!-- 2 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">Address</td>
                                                    <td>
                                                        <ezf:textArea name="xxPopPrm_H2" ezfName="xxPopPrm_H2" otherAttr=" cols=\"33\" rows=\"2\""/>
                                                    </td>
                                                </tr>
                                                <!-- 3 -->
                                                <!-- 4 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">Post Code</td>
                                                    <td><ezf:inputText name="shipToPostCd_H2" ezfName="shipToPostCd_H2" value="06042-8933" otherAttr=" size=\"10\""/></td>
                                                </tr>
                                                <!-- 5 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">City</td>
                                                    <td><ezf:inputText name="shipToCtyAddr_H2" ezfName="shipToCtyAddr_H2" value="MANCHESTER" otherAttr=" size=\"35\""/></td>
                                                </tr>
                                                <!-- 6 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">County</td>
                                                    <td><ezf:inputText name="shipToCntyNm_H2" ezfName="shipToCntyNm_H2" otherAttr=" size=\"35\""/></td>
                                                </tr>
                                                <!-- 7 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">State</td>
                                                    <td><ezf:inputText name="shipToStCd_H2" ezfName="shipToStCd_H2" value="CT" otherAttr=" size=\"10\""/></td>
                                                </tr>
                                                <!-- 8 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">Province</td>
                                                    <td><ezf:inputText name="shipToProvNm_H2" ezfName="shipToProvNm_H2" otherAttr=" size=\"35\""/></td>
                                                </tr>
                                                <!-- 9 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">Country</td>
                                                    <td class="stab">
                                                        <ezf:inputText name="ctryNm_H2" ezfName="ctryNm_H2" value="UNITED STATES AMERICA" otherAttr=" size=\"35\""/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td style="vertical-align: top;">
                                        <fieldset>
                                            <legend><font color="black">Header Notes</font></legend>
                                            <table border="0" cellpadding="0" cellspacing="0" >
                                                <col width="85">
                                                <col width="255">
                                                <!-- 1 -->
                                                <tr style="height: 24px;">
                                                    <td class="stab">Shipping Instructions</td>
                                                    <td>
                                                        <ezf:textArea name="delyAddlCmntTxt_H2" ezfName="delyAddlCmntTxt_H2" otherAttr=" cols=\"33\" rows=\"2\""/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                    </c:when>

                    <c:when test="${pageScope._ezddatabean.xxDplyTab == 'Detail'}">
                        <div id="TabContent-Detail"><!-- Added -->
                            <script type="text/javascript">
                                document.getElementById( "Header"       ).className = "pTab_OFF";
                                document.getElementById( "Detail"       ).className = "pTab_ON";
                            </script>

                            <table border="0" cellpadding="0" cellspacing="0" style="margin-top:3px;margin-left:10px;">
                              <colgroup>
                                <col width="750" align="left">
                                <col width="150"   align="center">
                                <col align="right">
                              </colgroup>
                              <tr height="22">
                                 <td>
                                    <ezf:inputButton name="AddLine" value="Add New Line (F11)" htmlClass="pBtn10" />
                                </td>
                                <td class="stab"><label>Total Cost</label></td>
                                <td><ezf:inputText name="thisMthTotStdCostAmt_D2" ezfName="thisMthTotStdCostAmt_D2" value="1,234,567,890,123,456,789.12" otherAttr=" size=\"28\" maxlength=\"28\" tabindex=\"-1\""/></td>
                              </tr>
                            </table>

                     <!-- ################################################## Search Result   - [START] ################################################## -->
                            <TABLE cellSpacing="0" cellPadding="0" border="0" style="margin-left:6px; z-index: 1; position: relative;">
                              <TBODY>
                              <TR style="VERTICAL-ALIGN: top;">
                                <TD><!-- Left TBL Header -->
                                  <TABLE cellSpacing="0" cellPadding="1" border="1">
                                    <COLGROUP>
                                      <COL align="center" width="30"><!-- Checkbox -->
                                      <COL align="center" width="42"><!-- Line# -->
                                      <COL align="center" width="110"><!-- Line Type -->
                                      <COL align="center" width="195"><!-- Mdse Code -->
                                    </COLGROUP>
                                    <TBODY>
                                    <TR height="37">
                                      <TD class="pClothBs">&nbsp;</TD>
                                      <TD class="pClothBs">Line#</TD>
                                      <TD class="pClothBs">Line Type</TD>
                                      <TD class="pClothBs">Item Number</TD>
                                    </TR>
                                    </TBODY>
                                  </TABLE>

                                  <DIV id="leftTBL" style="OVERFLOW-Y: hidden; OVERFLOW-X: hidden; HEIGHT: 240px;" onscroll="synchroScrollTop(this.id, new Array('rightTBL'));">
                                    <!-- Left TBL Main -->
                                    <TABLE id="A1" cellSpacing="0" cellPadding="1" border="1">
                                      <COLGROUP>
                                        <COL align="center" width="30">
                                        <COL align="center" width="42">
                                        <COL align="left"   width="110">
                                        <COL align="left"   width="195">
                                      </COLGROUP>
                                      <TBODY>
                                      <!-- <%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%> -->
                                      <%@ page import="business.servlet.NPAL1080.NPAL1080BMsg" %>
                                      <% NPAL1080BMsg bMsg = (NPAL1080BMsg)databean.getEZDBMsg(); %>
                                      <% String prchReqLineNum_Pre = ""; %>
                                      <% String prchReqLineNum = ""; %>
                                      <% for (int i = 0; i < bMsg.A.getValidCount(); i++) { %>
                                      <% prchReqLineNum = bMsg.A.no(i).prchReqLineNum_D1.getValue(); %>
                                      <% if (!prchReqLineNum_Pre.equals(prchReqLineNum)) { %>

                                        <!-- Parent -->
                                        <TR id="id_row<%= i %>" height="29">
                                          <TD><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" id=\"xxChkBox_D1#{EZF_ROW_NUMBER}\""/></TD>
                                          <!--<TD style="text-align: left;"><LABEL name="prchReqLineNum_D1" ezfname="prchReqLineNum_D1" ezfhyo="A" ezfout ezfArrayNo="<%= i %>">----+-</LABEL></TD>&lt;!&ndash; Line # &ndash;&gt;-->
                                          <TD style="text-align: left;"><!-- Line # -->
                                            <ezf:label name="prchReqLineNum_D1" ezfName="prchReqLineNum_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />.<ezf:label name="prchReqLineSubNum_D1" ezfName="prchReqLineSubNum_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD style="text-align: left;"><!-- Line Type -->
                                            <div id="<%= i %>">
                                            <ezf:select name="prchReqLineTpCd_SE" ezfName="prchReqLineTpCd_SE" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="prchReqLineTpCd_CD" ezfDispName="prchReqLineTpNm_DI" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_RequestLineType', this.parentNode.id)\"" otherAttr=" style=\"width:110px;\""/>
                                            </div>
                                          </TD>
                                            <td>
                                                <ezf:inputButton name="OpenWin_Item" value="…" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                                <ezf:inputText name="mdseCd_D1" ezfName="mdseCd_D1" value="----+----1----+-" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\" id=\"mdseCd_D1#{EZF_ROW_NUMBER}\""/>
                                                <ezf:inputButton name="GetItemName" value=">>" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                            </td>
                                          <td class="pAuditInfo">
                                            <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </td>
                                        </TR>
                                      <% } else { %>

                                        <!-- Child -->
                                        <TR id="id_row<%= i %>" height="29">
                                          <TD><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"display: none;\" id=\"xxChkBox_D1#{EZF_ROW_NUMBER}\""/>&nbsp;</TD>
                                          <!--<TD style="text-align: right;"><LABEL name="prchReqLineNum_D1" ezfname="prchReqLineNum_D1" ezfhyo="A" ezfout ezfArrayNo="<%= i %>">----+-</LABEL></TD>&lt;!&ndash; Line # &ndash;&gt;-->
                                          <TD style="text-align: right;"><!-- Line # -->
                                            <ezf:label name="prchReqLineNum_D1" ezfName="prchReqLineNum_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />.<ezf:label name="prchReqLineSubNum_D1" ezfName="prchReqLineSubNum_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD style="text-align: left;"><!-- Line Type -->
                                            <div id="<%= i %>">
                                            <ezf:select name="prchReqLineTpCd_SE" ezfName="prchReqLineTpCd_SE" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="prchReqLineTpCd_CD" ezfDispName="prchReqLineTpNm_DI" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('OnChange_RequestLineType', this.parentNode.id)\"" otherAttr=" style=\"width:110px;\""/>
                                            </div>
                                          </TD>
                                            <td><ezf:inputText name="mdseCd_D1" ezfName="mdseCd_D1" value="----+----1----+-" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"16\" maxlength=\"16\" style=\"margin-left:36px;\" ezftoupper=\"\" id=\"mdseCd_D1#{EZF_ROW_NUMBER}\""/>
                                            </td>
                                          <td class="pAuditInfo">
                                            <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </td>
                                        </TR>

                                      <% } %>
                                      <% prchReqLineNum_Pre = prchReqLineNum; %>
                                      <% } %>
                                      <!-- <%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%> -->
                                      </TBODY>
                                    </TABLE>
                                  </DIV>
                                </TD>

                                <TD><!-- Right TBL Header -->
                                  <DIV id="rightTopTBL" style="OVERFLOW-X: hidden; WIDTH: 710px;" onscroll="synchroScrollLeft(this.id, new Array('rightTBL'));">
                                    <TABLE cellSpacing="0" cellPadding="1" border="1" style="table-layout:fixed;"><!-- body + 17 + 2 -->

                                      <COLGROUP>
                                        <COL align="center" width="165"><!-- Mdse Name 150 -->
                                        <COL align="center" width="113"><!-- Req QTY 105 -->
                                        <COL align="center" width="108"><!-- Procurement Type -->
                                        <COL align="center" width="255" /><!-- WH /Supplier 100 -->
                                        <COL align="center" width="120" /><!-- SWH 40 -->
                                        <COL align="center" width="150" /><!-- Line Status 120 -->
                                        <COL align="center" width="100" /><!-- Order Qty -->
                                        <COL align="center" width="114" /><!-- SO Status 120 -->
                                        <COL align="center" width="114" /><!-- RWS Status 120 -->
                                        <COL align="center" width="102" /><!-- Fulfilled Qty 105 -->
                                        <COL align="center" width="102" /><!-- Received Qty 105 -->
                                        <COL align="center" width="102"><!-- Cancel QTY 98 -->
                                        <COL align="center" width="80" /><!-- PO# 70 -->
                                        <COL align="center" width="80" /><!-- PR# 70 -->
                                        <COL align="center" width="80" /><!-- SO# 70 -->
                                        <COL align="center" width="135" /><!-- Alt SO# 135 -->
                                        <COL align="center" width="135" /><!-- Ship Detail 135 -->
                                        <COL align="center" width="80" /><!-- RWS# 70 -->
                                        <COL align="center" width="102" /><!-- Insourced Qty 105 -->
                                        <COL align="center" width="70" /><!-- Insourced Flag 70 -->
                                        <COL align="center" width="70" /><!-- PO Flag 50 -->
                                        <COL align="center" width="70" /><!-- Freeze Flag 50 -->
                                        <COL align="center" width="220" /><!-- Line Comment (Freeze / Cancel Reason) 220 -->
                                        <COL align="center" width="53" /><!-- Ref# 50 -->
                                        <COL align="center" width="150" /><!-- Req Service Level 145 -->
                                        <COL align="center" width="80" /><!-- Tracking Number 220 -->
                                        <COL align="center" width="17"><!-- SCROLL 17 -->
                                      </COLGROUP>
                                      <TBODY>
                                      <TR height="37">
                                        <TD class="pClothBs">Item Description</TD>
                                        <TD class="pClothBs">Req Qty</TD>
                                        <TD class="pClothBs">Source Type</TD>
                                        <TD class="pClothBs">WH / Supplier</TD>
                                        <TD class="pClothBs">SWH / Site</TD>
                                        <TD class="pClothBs">Line Status</TD>
                                        <TD class="pClothBs">Order Qty</TD>
                                        <TD class="pClothBs">SO Status</TD>
                                        <TD class="pClothBs">RWS Status</TD>
                                        <TD class="pClothBs">Fulfilled Qty</TD>
                                        <TD class="pClothBs">Received Qty</TD>
                                        <TD class="pClothBs">Cancel Qty</TD>
                                        <TD class="pClothBs">PO#</TD>
                                        <TD class="pClothBs">PO Req#</TD>
                                        <TD class="pClothBs">SO#</TD>
                                        <TD class="pClothBs">Alt SO#</TD>
                                        <TD class="pClothBs">Ship Detail</TD>
                                        <TD class="pClothBs">RWS#</TD>
                                        <TD class="pClothBs">Insourced Qty</TD>
                                        <TD class="pClothBs">Insourced<br />Flag</TD>
                                        <TD class="pClothBs">PO<br />Flag</TD>
                                        <TD class="pClothBs">Freeze<br />Flag</TD>
                                        <TD class="pClothBs">Line Comment<br />(Freeze / Cancel Reason)</TD>
                                        <TD class="pClothBs">Ref#</TD>
                                        <TD class="pClothBs">Req Service Level</TD>
                                        <TD class="pClothBs">Tracking Number</TD>
                                        <TD>&nbsp;</TD>
                                      </TBODY>
                                    </TABLE>
                                  </DIV>

                                  <DIV id="rightTBL" style="OVERFLOW: scroll; WIDTH: 727px; HEIGHT: 260px; z-index: 1;" onscroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('rightTopTBL'));">
                                    <!-- Right TBL Main -->
                                    <TABLE id="A2" cellSpacing="0" cellPadding="1" border="1" style="table-layout:fixed;">
                                      <COLGROUP>
                                        <COL align="left"   width="165" /><!-- Mdse Name -->
                                        <COL align="left"  width="113" /><!-- Req QTY -->
                                        <COL align="left" width="108" /><!-- Procurement Type -->
                                        <COL align="left" width="255" /><!-- WH /Supplier -->
                                        <COL align="left" width="120" /><!-- SWH -->
                                        <COL align="left" width="150" /><!-- Line Status -->
                                        <COL align="left" width="100" /><!-- Order Qty -->
                                        <COL align="left" width="114" /><!-- SO Status -->
                                        <COL align="left" width="114" /><!-- RWS Status -->
                                        <COL align="right" width="102" /><!-- Fulfilled Qty -->
                                        <COL align="right" width="102" /><!-- Received Qty -->
                                        <COL align="right" width="102" /><!-- Cancel QTY -->
                                        <COL align="left" width="80" /><!-- PO# -->
                                        <COL align="left" width="80" /><!-- PR# -->
                                        <COL align="left" width="80" /><!-- SO# -->
                                        <COL align="left" width="135" /><!-- Alt SO# -->
                                        <COL align="left" width="135" /><!-- Ship Detail -->
                                        <COL align="left" width="80" /><!-- RWS# -->
                                        <COL align="right" width="102" /><!-- Insourced Qty -->
                                        <COL align="center" width="70" /><!-- Insourced Flag -->
                                        <COL align="center" width="70" /><!-- PO Flag -->
                                        <COL align="center" width="70" /><!-- Freeze Flag -->
                                        <COL align="center" width="220" /><!-- Line Comment (Freeze / Cancel Reason) -->
                                        <COL align="left" width="53" /><!-- Ref# -->
                                        <COL align="left" width="150" /><!-- Req Service Level -->
                                        <COL align="left" width="80" /><!-- Tracking Number -->
                                      </COLGROUP>
                                      <TBODY>
                                      <!-- <%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%> -->
                                      <% bMsg = (NPAL1080BMsg)databean.getEZDBMsg(); %>
                                      <% prchReqLineNum_Pre = ""; %>
                                      <% prchReqLineNum = ""; %>
                                      <% for (int i = 0; i < bMsg.A.getValidCount(); i++) { %>
                                      <% prchReqLineNum = bMsg.A.no(i).prchReqLineNum_D1.getValue(); %>
                                      <% if (!prchReqLineNum_Pre.equals(prchReqLineNum)) { %>

                                        <!-- Parent -->
                                        <TR id="id_row<%= i %>" height="29">
                                          <TD><!-- Mdse Name-->
                                            <ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"22\" tabindex=\"-1\" id=\"mdseDescShortTxt_D1#{EZF_ROW_NUMBER}\""/>
                                          </TD>
                                          <TD><!-- Request Qty -->
                                            <ezf:inputText name="prchReqQty_D1" ezfName="prchReqQty_D1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"13\" maxlength=\"13\" style=\"text-align: right;\""/>
                                          </TD>
                                          <TD><!-- Procurement Type -->
                                            <ezf:select name="procrTpCd_SE" ezfName="procrTpCd_SE" ezfHyo="A" ezfArrayNo="<%= i %>" ezfCodeName="procrTpCd_CD" ezfDispName="procrTpNm_DI" ezfCodeDispHyo="A" otherAttr=" style=\"width:105px;\""/>
                                          </TD>
                                          <TD><!-- WH /Supplier -->
                                            <ezf:inputButton name="OpenWin_Wh_Supplier" value="…" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                            <ezf:inputText name="prntVndCd_D1" ezfName="prntVndCd_D1" value="----+--" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"7\" maxlength=\"30\" ezftoupper=\"\""/>
                                            <ezf:inputButton name="GetWhOrSupplierName" value=">>" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                            <ezf:inputText name="prntVndNm_D1" ezfName="prntVndNm_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" maxlength=\"240\" ezftoupper=\"\""/>
                                          </TD>
                                          <TD><!-- SWH -->
                                            <ezf:inputButton name="OpenWin_Wh_Supplier" value="…" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                            <ezf:inputText name="locNm_D1" ezfName="locNm_D1" value="NEW" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\""/>
                                          </TD>
                                          <TD><!-- Line Status -->
                                            <ezf:inputText name="prchReqLineStsDescTxt_D1" ezfName="prchReqLineStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD>
                                            <ezf:label name="ordQty_D1" ezfName="ordQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- SO Status -->
                                            <ezf:inputText name="dsSoLineStsDescTxt_D1" ezfName="dsSoLineStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD><!-- RWS Status -->
                                            <ezf:inputText name="rwsStsDescTxt_D1" ezfName="rwsStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD><!-- Fulfilled Qty -->
                                            <ezf:label name="shipQty_D1" ezfName="shipQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Received Qty -->
                                            <ezf:label name="rwsPutAwayQty_D1" ezfName="rwsPutAwayQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Cancel Qty -->
                                            <ezf:label name="prchReqCancQty_D1" ezfName="prchReqCancQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- PO# -->
                                            <ezf:anchor name="poOrdNum_AC" ezfName="poOrdNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_PO" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="poOrdNum_AC" ezfName="poOrdNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- PR# -->
                                            <ezf:anchor name="prchReqNum_AC" ezfName="prchReqNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_POReq" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="prchReqNum_AC" ezfName="prchReqNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- SO# -->
                                            <ezf:anchor name="vndSoNum_AC" ezfName="vndSoNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_SO" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="vndSoNum_AC" ezfName="vndSoNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- Alt SO# -->
                                            <ezf:label name="rwsRefNum_AC" ezfName="rwsRefNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Ship Detail -->
                                            <ezf:anchor name="xxScrItem20Txt_AC" ezfName="xxScrItem20Txt_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipDetail" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="xxScrItem20Txt_AC" ezfName="xxScrItem20Txt_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- RWS# -->
                                            <ezf:anchor name="rwsNum_AC" ezfName="rwsNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_RWS" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="rwsNum_AC" ezfName="rwsNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- Insourced Qty -->
                                            <ezf:label name="prchReqInsrcQty_D1" ezfName="prchReqInsrcQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Incsourced Flag -->
                                            <ezf:label name="insrcFlg_D1" ezfName="insrcFlg_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- PO Flag -->
                                            <ezf:label name="poCratFlg_D1" ezfName="poCratFlg_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Freeze Flag -->
                                            <ezf:label name="prchReqFrzFlg_D1" ezfName="prchReqFrzFlg_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Line Comment (Freeze / Cancel Reason) -->
                                            <ezf:inputText name="prchReqLineCmntTxt_D1" ezfName="prchReqLineCmntTxt_D1" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"30\" maxlength=\"240\""/>
                                          </TD>
                                          <TD><!-- Ref# -->
                                            &nbsp;<!--<LABEL ezfout ezfhyo="A" name="prchReqLineNum_D1" ezfname="prchReqLineNum_D1">&nbsp;</LABEL>-->
                                          </TD>
                                          <TD><!-- Requested Service Level -->
                                            <ezf:inputText name="shpgSvcLvlDescTxt_D1" ezfName="shpgSvcLvlDescTxt_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD align="center"><!-- Tracking Number -->
                                            <% if (bMsg.A.no(i).vndSoNum_AC.getValue() != null && bMsg.A.no(i).vndSoNum_AC.getValue() != "") { %>
                                            <ezf:inputButton name="OpenWin_Tracking" ezfName="OpenWin_Tracking" value="Tracking#" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn5" otherAttr=" id=\"OpenWin_Tracking{EZF_ROW_NUMBER}\""/>
                                            <% } %>
                                          </TD>
                                          <td class="pAuditInfo">
                                            <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </td>
                                        </TR>

                                      <% } else { %>

                                        <!-- Child -->
                                        <TR id="id_row<%= i %>" height="29">
                                          <TD><!-- Mdse Name-->
                                            <ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"22\" tabindex=\"-1\" id=\"mdseDescShortTxt_D1#{EZF_ROW_NUMBER}\""/>
                                          </TD>
                                          <TD><!-- Request Qty -->
                                            <ezf:inputText name="prchReqQty_D1" ezfName="prchReqQty_D1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"13\" maxlength=\"13\" tabindex=\"-1\" style=\"text-align: right;\""/>
                                          </TD>
                                          <TD><!-- Procurement Type -->
                                            <ezf:select name="procrTpCd_SE" ezfName="procrTpCd_SE" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="procrTpCd_CD" ezfDispName="procrTpNm_DI" ezfCodeDispHyo="A" otherAttr=" style=\"width:105px;\""/>
                                          </TD>
                                          <TD><!-- WH /Supplier -->
                                            <ezf:inputText name="prntVndCd_D1" ezfName="prntVndCd_D1" value="----+--" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"7\" maxlength=\"30\" style=\"margin-left:36px; margin-right:37px;\""/>
                                            <ezf:inputText name="prntVndNm_D1" ezfName="prntVndNm_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" maxlength=\"240\" ezftoupper=\"\""/>
                                          </TD>
                                          <TD><!-- SWH -->
                                            <ezf:inputText name="locNm_D1" ezfName="locNm_D1" value="NEW" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"10\" maxlength=\"60\" style=\"margin-left:36px;\" ezftoupper=\"\""/>
                                          </TD>
                                          <TD><!-- Line Status -->
                                            <ezf:inputText name="prchReqLineStsDescTxt_D1" ezfName="prchReqLineStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD>
                                            <ezf:label name="ordQty_D1" ezfName="ordQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- SO Status -->
                                            <ezf:inputText name="dsSoLineStsDescTxt_D1" ezfName="dsSoLineStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD><!-- RWS Status -->
                                            <ezf:inputText name="rwsStsDescTxt_D1" ezfName="rwsStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD><!-- Fulfilled Qty -->
                                            <ezf:label name="shipQty_D1" ezfName="shipQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Received Qty -->
                                            <ezf:label name="rwsPutAwayQty_D1" ezfName="rwsPutAwayQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Cancel Qty -->
                                            <ezf:label name="prchReqCancQty_D1" ezfName="prchReqCancQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- PO# -->
                                            <ezf:anchor name="poOrdNum_AC" ezfName="poOrdNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_PO" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="poOrdNum_AC" ezfName="poOrdNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- PR# -->
                                            <ezf:anchor name="prchReqNum_AC" ezfName="prchReqNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_POReq" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="prchReqNum_AC" ezfName="prchReqNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- SO# -->
                                            <ezf:anchor name="vndSoNum_AC" ezfName="vndSoNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_SO" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="vndSoNum_AC" ezfName="vndSoNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- Alt SO# -->
                                            <ezf:label name="rwsRefNum_AC" ezfName="rwsRefNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Ship Detail -->
                                            <ezf:anchor name="xxScrItem20Txt_AC" ezfName="xxScrItem20Txt_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipDetail" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="xxScrItem20Txt_AC" ezfName="xxScrItem20Txt_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- RWS# -->
                                            <ezf:anchor name="rwsNum_AC" ezfName="rwsNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_RWS" otherAttr=" ezfanchortext=\"\"">
                                              <ezf:label name="rwsNum_AC" ezfName="rwsNum_AC" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" style=\"cursor: pointer;\""/>
                                            </ezf:anchor>
                                          </TD>
                                          <TD><!-- Insourced Qty -->
                                            <ezf:label name="prchReqInsrcQty_D1" ezfName="prchReqInsrcQty_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Incsourced Flag -->
                                            <ezf:label name="insrcFlg_D1" ezfName="insrcFlg_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- PO Flag -->
                                            <ezf:label name="poCratFlg_D1" ezfName="poCratFlg_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Freeze Flag -->
                                            <ezf:label name="prchReqFrzFlg_D1" ezfName="prchReqFrzFlg_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </TD>
                                          <TD><!-- Line Comment (Freeze / Cancel Reason) -->
                                            <ezf:inputText name="prchReqLineCmntTxt_D1" ezfName="prchReqLineCmntTxt_D1" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"30\" maxlength=\"240\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD><!-- Ref# -->
                                            <ezf:label name="xxPopPrm_D1" ezfName="xxPopPrm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />.0
                                          </TD>
                                          <TD><!-- Requested Service Level -->
                                            <ezf:inputText name="shpgSvcLvlDescTxt_D1" ezfName="shpgSvcLvlDescTxt_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" tabindex=\"-1\""/>
                                          </TD>
                                          <TD align="center"><!-- Tracking Number -->
                                            <% if (bMsg.A.no(i).vndSoNum_AC.getValue() != null && bMsg.A.no(i).vndSoNum_AC.getValue() != "") { %>
                                            <ezf:inputButton name="OpenWin_Tracking" ezfName="OpenWin_Tracking" value="Tracking#" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn5" otherAttr=" id=\"OpenWin_Tracking{EZF_ROW_NUMBER}\""/>
                                            <% } %>
                                          </TD>
                                          <td class="pAuditInfo">
                                            <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            <ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm_D1" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                          </td>
                                        </TR>

                                      <% } %>
                                      <% prchReqLineNum_Pre = prchReqLineNum; %>
                                      <% } %>
                                      <!-- <%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%> -->


                                      </TBODY>
                                    </TABLE>
                                  </DIV>
                                </TD>
                              </TR>
                              </TBODY>
                            </TABLE>

                            <!-- footer buttons -->
                            <TABLE style="margin-left: 5px; margin-top: 0px;" border="0" cellpadding="1" cellspacing="1" align="left">
                              <colgroup>
                                <col width="" align="left">
                                <col width="" align="left">
                              </colgroup>
                              <tr>
                                <td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
                                <td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn6" /></td>
                                <!-- START 2023/03/15 T.Kuroda [QC#61204, MOD] -->
                                <!-- 
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                 -->
                                <td class="stab"><ezf:anchor name="xxLinkAncr_IL" ezfName="xxLinkAncr_IL" ezfEmulateBtn="1" ezfGuard="OnClick_TemplateDownload" >Import File Link</ezf:anchor></td>
                                <td colspan="3"><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"47\" maxlength=\"9999\""/></td>
                                <td><ezf:inputButton name="Upload" value="Import" htmlClass="pBtn6" /></td>
                                <!-- END   2023/03/15 T.Kuroda [QC#61204, MOD] -->
                              </tr>
                              <tr>
                                <td><ezf:inputButton name="OpenWin_EventHistory" value="History" htmlClass="pBtn6" /></td>
                                <td><ezf:inputButton name="OpenWin_OnHandInv" value="On Hand Inv" htmlClass="pBtn6" /></td>
                                <td><ezf:inputButton name="PRFreeze" value="Freeze/UnF" htmlClass="pBtn6" /></td>
                                <td><ezf:inputButton name="LineCancel" value="Line Cancel" htmlClass="pBtn6" /></td>
                                <td class="stab"><label>Freeze / Cancel Reason</label></td>
                                <td><ezf:inputText name="prchReqLineCmntTxt_D2" ezfName="prchReqLineCmntTxt_D2" value="----+----1----+----2----+----3----+----4----+----5----+----6----+----7----+----8----+----9" otherAttr=" size=\"30\" maxlength=\"240\""/></td>
                              </tr>
                            </TABLE>

                            <TABLE style="margin-top: 6px; float: right;" border="0" cellpadding="1" cellspacing="1" align="left">
                                <colgroup>
                                    <col width="">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td>
                                            <TABLE cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
                                                <TBODY>
                                                    <tr align="right">
                                                        <td>
                                                            <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                                                <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                                                <jsp:param name="TableNm"     value="A" />
                                                                <jsp:param name="ShowingFrom" value="xxPageShowFromNum_D2" />
                                                                <jsp:param name="ShowingTo"   value="xxPageShowToNum_D2" />
                                                                <jsp:param name="ShowingOf"   value="xxPageShowOfNum_D2" />
                                                            </jsp:include>
                                                        </td>
                                                    </tr>
                                                </TBODY>
                                            </TABLE>
                                        </td>
                                    </tr>
                                </tbody>
                            </TABLE>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
      </DIV>
    </TD>
  </TR>
  </TBODY>
</TABLE>
</CENTER>
<div style="display:none;">
    <ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>
<%-- ###SCRIPT --%>
<script type="text/javascript">

    setKeyDownHandler();

	function setKeyDownHandler() {

		if( window.addEventListener ) {
		    window.addEventListener("keyup", emulateFuncKeyDown, false);
		} else if( document.attachEvent ) {
		    document.attachEvent("onkeyup", emulateFuncKeyDown);
		} else {
		    document.onkeyup = emulateFuncKeyDown;
		}
	}

	function emulateFuncKeyDown() {

		var code = event.keyCode;
		//alert("keyCode:["+event.keyCode+"]");

		if(event.keyCode == 122 ) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Add");
			emulateOnClickEvent("btn11");
			break;
		default:
			break;
		}
		return;
	}

	function emulateOnClickEvent(elementId) {

		var elem = document.getElementById(elementId);
		if( /*@cc_on ! @*/ false ) {
			elem.fireEvent("onclick");
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, true);
			elem.dispatchEvent(evt);
		}
	}

</script>
<!-- Application Parts End -->


<%-- **** Task specific area ends here **** --%>
