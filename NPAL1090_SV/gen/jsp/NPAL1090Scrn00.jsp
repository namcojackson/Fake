<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161020090435 --%>
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
			<input type="hidden" name="pageID" value="NPAL1090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Tech Request List">

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
                            <tr title="PR Entry Screen">
                                <td width="107px" align="center" class="same">Tech Req List</td>
                            </tr>
                        </table>
                    </td>
                    <td width="10px" valign="bottom" align="center">
                        <a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0" onclick="prevTabPage()"></a>
                    </td>
                    <td width="10px" valign="bottom" align="center">
                        <a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0" onclick="nextTabPage()"></a>
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

          <!-- header column 1 -->
            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
                <col width="335"  align="left">
                <col width="360"  align="left">
                <col width=""  align="left">
                <tr>
                    <td valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" >
                          <colgroup>
                              <col align="left" width="115">
                              <col align="left" width="200">
                              <col align="left" width="">
                              <col align="left" width="">
                              <col align="left" width="">
                          </colgroup>
                          <tbody>
                          <tr height="20">
                              <TD class="stab"><LABEL>Tech Request#</LABEL></TD>
                              <TD colspan="3"><ezf:inputText name="prchReqNum_H1" ezfName="prchReqNum_H1" value="12345678" otherAttr=" size=\"28\" maxlength=\"10\" ezftoupper=\"\""/></TD>
                              <td></td>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Tech Request Type</LABEL></TD>
                              <TD colspan="3">
                                  <ezf:select name="prchReqTpCd_SE" ezfName="prchReqTpCd_SE" ezfBlank="1" ezfCodeName="prchReqTpCd_CD" ezfDispName="prchReqTpDescTxt_DI" otherAttr=" style=\"width:202px\""/>
                              </TD>
                              <td></td>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Header Status</LABEL></TD>
                              <td colspan="3">
                                  <ezf:select name="prchReqStsCd_SE" ezfName="prchReqStsCd_SE" ezfBlank="1" ezfCodeName="prchReqStsCd_CD" ezfDispName="prchReqStsDescTxt_DI" otherAttr=" style=\"width:202\""/>
                              </td>
                              <td></td>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Approval Status</LABEL></TD>
                              <td colspan="3">
                                  <ezf:select name="prchReqApvlStsCd_SE" ezfName="prchReqApvlStsCd_SE" ezfBlank="1" ezfCodeName="prchReqApvlStsCd_CD" ezfDispName="prchReqApvlStsDescTxt_DI" otherAttr=" style=\"width:202\""/>
                              </td>
                              <td></td>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Date Created</LABEL></TD>
                              <TD colspan="3" class="stab">
								<ezf:inputText name="prchReqCratDt_HF" ezfName="prchReqCratDt_HF" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
								<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('prchReqCratDt_HF', 4);"/> - 
								<ezf:inputText name="prchReqCratDt_HT" ezfName="prchReqCratDt_HT" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
								<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('prchReqCratDt_HT', 4);"/>
                              </TD>
                              <td></td>
                          </tr>
                          </tbody>
                      </table>
                    </td>
                    <td valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" >
                          <colgroup>
                              <col align="left" width="130">
                              <col align="left" width="150">
                          </colgroup>
                          <tbody>
                          <tr height="20">
                              <TD class="stab"><LABEL>Document Source Type</LABEL></TD>
                              <TD>
                                  <ezf:select name="prchReqSrcTpCd_SE" ezfName="prchReqSrcTpCd_SE" ezfBlank="1" ezfCodeName="prchReqSrcTpCd_CD" ezfDispName="prchReqSrcTpDescTxt_DI" otherAttr=" style=\"width:202\""/>
                              </TD>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Service Request#</LABEL></TD>
                              <TD colspan="3"><ezf:inputText name="fsrNum_H1" ezfName="fsrNum_H1" value="----+----1----+----2" otherAttr=" size=\"28\" maxlength=\"10\" ezftoupper=\"\""/></TD>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Service Request Task#</LABEL></TD>
                              <TD colspan="3"><ezf:inputText name="svcTaskNum_H1" ezfName="svcTaskNum_H1" value="----+----1----+----2" otherAttr=" size=\"28\" maxlength=\"10\" ezftoupper=\"\""/></TD>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Service Request Serial#</LABEL></TD>
                              <TD colspan="3"><ezf:inputText name="svcMachSerNum_H1" ezfName="svcMachSerNum_H1" value="----+----1----+----2" otherAttr=" size=\"28\" maxlength=\"10\" ezftoupper=\"\""/></TD>
                          </tr>
                          <tr height="20">
                              <TD class="stab"><LABEL>Date Needed</LABEL></TD>
                              <TD class="stab" colspan="3">
								<ezf:inputText name="rqstRcvDt_HF" ezfName="rqstRcvDt_HF" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
								<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_HF', 4);"/> - 
								<ezf:inputText name="rqstRcvDt_HT" ezfName="rqstRcvDt_HT" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
								<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_HT', 4);"/>
                              </TD>
                          </tr>
                          </tbody>
                      </table>
                    </td>
                    <td valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" >
                          <colgroup>
                            <col width="140" align="left" />
                            <col width="70" align="left" />
                            <col width="5" align="left" />
                            <col width="" align="left" />
                          </colgroup>
                          <tbody>
                            <tr height="20">
                              <td class="stab"><ezf:anchor name="rqstTechTocCd_AC" ezfName="rqstTechTocCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Technician" >Technician Name(*)</ezf:anchor></td>
                              <td colspan="3"><ezf:inputText name="techNm_H1" ezfName="techNm_H1" value="Mike Nagle" otherAttr=" size=\"35\" maxlength=\"55\""/></td>
                            </tr>
                            <tr height="20">
                              <td class="stab"><ezf:anchor name="rtlWhCd_AC" ezfName="rtlWhCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_TechWhSwh" >Tech WH / SWH(*)</ezf:anchor></td>
                              <td><ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="TECH0001" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                              <td style="text-align:center;"></td>
                              <td><ezf:inputText name="rtlSwhCd_H1" ezfName="rtlSwhCd_H1" value="G" otherAttr=" size=\"5\" maxlength=\"55\" ezftoupper=\"\""/></td>
                            </tr>
                            <tr height="20">
                              <td class="stab"><ezf:anchor name="shipToCustCd_AC" ezfName="shipToCustCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" >Ship to Customer(*)</ezf:anchor></td>
                              <td><ezf:inputText name="shipToCustCd_H1" ezfName="shipToCustCd_H1" value="----+----1" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                              <td style="text-align:center;"></td>
                              <td><ezf:inputText name="locNm_H1" ezfName="locNm_H1" value="Jamesburg Public Library" otherAttr=" size=\"24\" maxlength=\"70\" ezftoupper=\"\""/></td>
                            </tr>
	                        <tr height="20">
								<td class="stab">Req Service Level</td>
								<td colspan="3">
									<ezf:select name="shpgSvcLvlCd_SE" ezfName="shpgSvcLvlCd_SE" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_DI" otherAttr=" style=\"width:255\""/>
								</td>
	                        </tr>
	                        <tr height="20">
								<td class="stab"><ezf:anchor name="carrCd_AC" ezfName="carrCd_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" >Carrier(*)</ezf:anchor></td>
								<td colspan="2"><ezf:inputText name="carrNm_H1" ezfName="carrNm_H1" value="UPS" otherAttr=" size=\"10\" maxlength=\"45\" ezftoupper=\"\""/></td>
                             	<td colspan="2" style="text-align:right;">
	                                <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
	                                <ezf:inputButton name="New_TechReuestEntry" value="New" htmlClass="pBtn6" />
								</td>
                            </tr>
                          </tbody>
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
                          <ezf:skip>
                          <TABLE cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 43px;">
                              <COLGROUP>
                                  <COL align="center" width="54">
                                  <COL align="right" width="40">
                                  <COL align="center" width="16">
                                  <COL align="right" width="40">
                                  <COL align="center" width="16">
                                  <COL align="right" width="40">
                                  <COL width="10">
                                  <COL>
                                  <COL width="1">
                                  <COL>
                              </COLGROUP>
                              <TBODY>
                              <TR>
                                  <TD class="stab"><LABEL>Showing</LABEL></TD>
                                  <TD class="pOut"><LABEL ezfout name="xxPageShowFromNum" ezfname="xxPageShowFromNum">1</LABEL></TD>
                                  <TD class="stab"><LABEL>to</LABEL></TD>
                                  <TD class="pOut"><LABEL ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">20</LABEL></TD>
                                  <TD class="stab"><LABEL>of</LABEL></TD>
                                  <TD class="pOut"><LABEL ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">200</LABEL></TD>
                                  <TD>&nbsp;</TD>
                                  <TD><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></TD>
                                  <TD></TD>
                                  <TD><INPUT onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></TD>
                              </TR>
                              </TBODY>
                          </TABLE>
                          </ezf:skip>
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
					<div id='leftTbl' style="float:left; display:block; height:310px; overflow:hidden; margin-left: 0px; padding:0px; padding-bottom: 20px">
					</div>
				</div>  <!-- left table -->
				<div style="float:left"> <!-- right table -->
					<div id='rightTblHead' style="width:1093px; display:block; overflow:hidden; margin:0px;padding:0px;">
						<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" height="28" id="AHEAD" width="2620px">
							<COL align="left" width="100"><!-- Tech Request # -->
							<COL align="center" width="130"><!-- Tech Request Type -->
							<COL align="center" width="150"><!-- Header Status -->
							<COL align="center" width="150"><!-- Approval Status -->
							<COL align="center" width="170"><!-- Date Created -->
							<COL align="center" width="170"><!-- Date & Time needed -->
							<COL align="center" width="150"><!-- Document Source Type -->
							<COL align="center" width="120"><!-- Source Doc# -->
							<COL align="center" width="75"><!-- Requester Code -->
							<COL align="center" width="150"><!-- Requester Name -->
							<col width="150" align="center"><!-- Req Service Level -->
							<col width="88"  align="center"><!-- Carrier -->
							<col width="150" align="center"><!-- Carrier Name -->
							<COL align="center" width="70"><!-- Technician Code -->
							<COL align="center" width="150"><!-- Technician Name -->
							<COL align="center" width="70"><!-- Tech WH -->
							<COL align="center" width="150"><!-- Tech WH Name -->
							<COL align="center" width="70"><!-- Tech SWH -->
							<COL align="center" width="150"><!-- Tech SWH Name -->
							<COL align="center" width="120"><!-- Ship To Customer Code -->
							<COL align="center" width="150"><!-- Ship To Customer Name -->
							<COL align="center" width="150"><!-- Service Request# -->
							<COL align="center" width="150"><!-- Service Request Task# -->
							<COL align="center" width="150"><!-- Service Request Serial# -->
							<TR height="37">
								<TD id="AH0" class="pClothBs">Tech Request #</TD>
								<TD id="AH1" class="pClothBs">Tech Request Type</TD>
								<TD id="AH2" class="pClothBs">Header Status</TD>
								<TD id="AH3" class="pClothBs">Approval Status</TD>
								<TD id="AH4" class="pClothBs">Date Created</TD>
								<TD id="AH5" class="pClothBs">Date & Time Needed</TD>
								<TD id="AH6" class="pClothBs">Document Source Type</TD>
								<TD id="AH7" class="pClothBs">Source Doc#</TD>
								<TD id="AH8" class="pClothBs">Requester</TD>
								<TD id="AH9" class="pClothBs">Requester Name</TD>
								<TD id="AH10" class="pClothBs">Req Service Level</td>
								<TD id="AH11" class="pClothBs">Carrier</td>
								<TD id="AH12" class="pClothBs">Carrier Name</td>
								<TD id="AH13" class="pClothBs">Technician</TD>
								<TD id="AH14" class="pClothBs">Technician Name</TD>
								<TD id="AH15" class="pClothBs">Tech WH</TD>
								<TD id="AH16" class="pClothBs">Tech WH Name</TD>
								<TD id="AH17" class="pClothBs">Tech SWH</TD>
								<TD id="AH18" class="pClothBs">Tech SWH Name</TD>
								<TD id="AH19" class="pClothBs">Ship To Customer</TD>
								<TD id="AH20" class="pClothBs">Ship To Customer Name</TD>
								<TD id="AH21" class="pClothBs">Service Request#</TD>
								<TD id="AH22" class="pClothBs">Service Request Task#</TD>
								<TD id="AH23" class="pClothBs">Service Request Serial#</TD>
							</TR>
						</TABLE>
					</div> <!-- rightTblHead -->
					<div  id="rightTbl" style="width:1110px; height:327px; display:block; overflow:scroll; margin:0px; padding:0px;" >
						<!-- Right TBL Main -->
						<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="2620px">
							<COL align="left" width="100"><!-- Tech Request # -->
							<COL align="left" width="130"><!-- Tech Request Type -->
							<COL align="center" width="150"><!-- Header Status -->
							<COL align="center" width="150"><!-- Approval Status -->
							<COL align="left" width="170"><!-- Date Created -->
							<COL align="left" width="170"><!-- Date & Time needed -->
							<COL align="center" width="150"><!-- Document Source Type -->
							<COL align="left" width="120"><!-- Source Doc# -->
							<COL align="left" width="75"><!-- Requester Code -->
							<COL align="center" width="150"><!-- Requester Name -->
							<col width="150" align="center"><!-- Req Service Level -->
							<col width="88"  align="left"><!-- Carrier -->
							<col width="150" align="center"><!-- Carrier Name -->
							<COL align="left" width="70"><!-- Technician Code -->
							<COL align="center" width="150"><!-- Technician Name -->
							<COL align="left" width="70"><!-- Tech WH -->
							<COL align="center" width="150"><!-- Tech WH Name -->
							<COL align="left" width="70"><!-- Tech SWH -->
							<COL align="center" width="150"><!-- Tech SWH Name -->
							<COL align="left" width="120"><!-- Ship To Customer Code -->
							<COL align="center" width="150"><!-- Ship To Customer Name -->
							<COL align="left" width="150"><!-- Service Request# -->
							<COL align="left" width="150"><!-- Service Request Task# -->
							<COL align="left" width="150"><!-- Service Request Serial# -->
							<ezf:row ezfHyo="A">
								<TR height="21" id="id_row${EZF_ROW_NUMBER}">
									<TD><!-- Tech Request # -->
										<ezf:anchor name="prchReqNum_AC" ezfName="prchReqNum_AC" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Move_Tech_RequestEntry" otherAttr=" id=\"prchReqNum_{EZF_ROW_NUMBER}\"">
											<ezf:label name="prchReqNum_AC" ezfName="prchReqNum_AC" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"cursor: pointer;\""/>
										</ezf:anchor>
									</TD>
									<TD><!-- Request Type -->
										<ezf:label name="prchReqTpDescTxt_D1" ezfName="prchReqTpDescTxt_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Header Status -->
										<ezf:inputText name="prchReqStsDescTxt_D1" ezfName="prchReqStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Approval Status -->
										<ezf:inputText name="prchReqApvlStsDescTxt_D1" ezfName="prchReqApvlStsDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Date Created -->
										<ezf:label name="xxPopPrm_D1" ezfName="xxPopPrm_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Date & Time needed -->
										<ezf:label name="xxPopPrm_D2" ezfName="xxPopPrm_D2" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Document Source Type -->
										<ezf:inputText name="prchReqSrcTpDescTxt_D1" ezfName="prchReqSrcTpDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Source Doc# -->
										<ezf:label name="trxRefNum_D1" ezfName="trxRefNum_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Requester Code -->
										<ezf:label name="prchReqCratByPsnCd_D1" ezfName="prchReqCratByPsnCd_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Requester Name -->
										<ezf:inputText name="fullPsnNm_D1" ezfName="fullPsnNm_D1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Requested Service Level -->
										<ezf:inputText name="shpgSvcLvlDescTxt_D1" ezfName="shpgSvcLvlDescTxt_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Carrier Code -->
										<ezf:label name="carrCd_D1" ezfName="carrCd_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Carrier Name -->
										<ezf:inputText name="carrNm_D1" ezfName="carrNm_D1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Technician Code -->
										<ezf:label name="rqstTechTocCd_D1" ezfName="rqstTechTocCd_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Technician Name -->
										<ezf:inputText name="techNm_D1" ezfName="techNm_D1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Tech WH -->
										<ezf:label name="destRtlWhCd_D1" ezfName="destRtlWhCd_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Tech WH Name -->
										<ezf:inputText name="rtlWhNm_D1" ezfName="rtlWhNm_D1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Tech SWH -->
										<ezf:label name="destRtlSwhCd_D1" ezfName="destRtlSwhCd_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Tech SWH Name -->
										<ezf:inputText name="rtlSwhNm_D1" ezfName="rtlSwhNm_D1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Ship To Customer Code -->
										<ezf:label name="shipToCustCd_D1" ezfName="shipToCustCd_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Ship To Customer Name -->
										<ezf:inputText name="locNm_D1" ezfName="locNm_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
									</TD>
									<TD><!-- Service Request# -->
										<ezf:label name="fsrNum_D1" ezfName="fsrNum_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Service Request Task# -->
										<ezf:label name="svcTaskNum_D1" ezfName="svcTaskNum_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
									<TD><!-- Service Request Serial# -->
										<ezf:label name="svcMachSerNum_D1" ezfName="svcMachSerNum_D1" ezfHyo="A" ezfArrayNo="0" />
									</TD>
								</TR>
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
        </TD></TR></TBODY></TABLE></CENTER>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
</script>


<%-- **** Task specific area ends here **** --%>
