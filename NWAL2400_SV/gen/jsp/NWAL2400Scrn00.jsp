<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171004132121 --%>
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
			<input type="hidden" name="pageID" value="NWAL2400Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CUSA Retail Dealer Maintenance">

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
                            <tr title="CUSA Retail Dealer Maintenance">
                                <td width="107px" align="center" class="same">ROSS DLR</td>
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

          <style type="text/css">
              .header-line {
                  /*height: 28px;*/
				  margin-left: 10px;
				  margin-top: 0;
				  border: none;
				  width: 1113px;
              }
              .header-line .pBtn0 {
                  margin-bottom: -1px;
              }
			  select.w20 {
				  width: 166px;
				  /*width: auto;*/
			  }
			  select.w10 {
				  width: 96px;
			  }
          </style>

		  <!-- Line1 -->
		  <table class="header-line" style="margin-top: 8px;" border="0" cellpadding="0" cellspacing="0">
			  <colgroup>
				  <!-- Dealer Code -->
				  <col width="100" /><!-- col1 header -->
				  <col width="140" /><!-- col1 header -->
				  <col width="210" />
				  <!-- Division -->
				  <col width="100" /><!-- col2 header -->
				  <col width="400" />
				  <col width="" />
			  </colgroup>
			  <tbody>
              <tr height="30">
				<td valign="top"><h5>Search</h5></td>
				  <td><label>Dealer Code(*) :</label></td>
				  <td>
					  <ezf:inputText name="rtlDlrCd" ezfName="rtlDlrCd" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
				  </td>
				  <td><label>Division :</label></td>
				  <td>
                      <ezf:select name="rtlDivCd_SL" ezfName="rtlDivCd_SL" ezfBlank="1" ezfCodeName="rtlDivCd_PC" ezfDispName="rtlDivCd_PD" otherAttr=" style=\"width: 150px;\""/>
				  </td>
				  <td>
					<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
				  </td>
			  </tr>
			  </tbody>
		  </table>
		<hr />
		  <table class="header-line" border="0" cellpadding="0" cellspacing="0">
				  <!-- Dealer Code -->
				  <col width="100" /><!-- col1 header -->
				  <col width="350" /><!-- col1 header -->
				  <!-- Division -->
				  <col width="100" /><!-- col2 header -->
				  <col width="400" /><!-- col2 header -->
				  <col width="" /><!-- col2 header -->
			  <tbody>
              <tr height="50">
				<td valign="top"><h5>Upload</h5></td>
				<td>
					<ezf:inputRadio name="xxSelRadioBtnObj" ezfName="xxSelRadioBtnObj" value="Append" otherAttr=" size=\"40\""/>Append<br />
					<ezf:inputRadio name="xxSelRadioBtnObj" ezfName="xxSelRadioBtnObj" value="Replace" otherAttr=" size=\"40\""/>Replace
				</td>
				<td><label>File Uploaded</label></td>
				<td class="stab">
					<ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"40\" maxlength=\"9999\" ezftoupper=\"\""/>
				</td>
				<td class="stab">
					<ezf:inputButton name="OnClick_Upload" value="Upload" htmlClass="pBtn6" />
				</td>
			  </tr>
			  </tbody>
		  </table>

        <div class="pTab_BODY">
<!-- ################################################## Search Criteria - [START] ################################################## -->
		  <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 5px; MARGIN-BOTTOM: 5px; " border="0" cellpadding="1" cellspacing="1">
			  <colgroup>
				  <col width="" align="left">
				  <col width="" align="left">
				  <col width="1100" align="right">
			  </colgroup>
			  <tbody>
			  <tr>
				<td><ezf:inputButton name="OnClick_AddNewLine" value="Add Line" htmlClass="pBtn6" /></td>
				<td><ezf:inputButton name="OnClick_DeleteLine" value="Delete Line" htmlClass="pBtn6" /></td>
				<td>
                    <ezf:skip>
                        <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 30px;">
                            <colgroup>
                                <col align="center" width="54">
                                <col align="right" width="40">
                                <col align="center" width="16">
                                <col align="right" width="40">
                                <col align="center" width="16">
                                <col align="right" width="40">
                                <col width="10">
                                <col>
                                <col width="1">
                                <col>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td class="stab"><label>Showing</label></td>
                                    <td class="pOut"><label ezfout name="xxPageShowFromNum" ezfname="xxPageShowFromNum">1</label></td>
                                    <td class="stab"><label>to</label></td>
                                    <td class="pOut"><label ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">100</label></td>
                                    <td class="stab"><label>of</label></td>
                                    <td class="pOut"><label ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">2000</label></td>
                                    <td>&nbsp;</td>
                                    <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                    <td></td>
                                    <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
                                </tr>
                            </tbody>
                        </table>
                    </ezf:skip>
                    <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
                        <tbody>
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
                        </tbody>
                    </table>
				</td>
			  </tr>
			  </tbody>
		  </table>

 <!-- ################################################## Search Result   - [START] ################################################## -->
            <div id="topRightTBL" style="MARGIN-LEFT: 5px; overflow-y:none; overflow-x:hidden; width:1100px;">
			  <table cellSpacing="0" cellPadding="1" border="1" width="">
				  <colgroup>
	                  <col align="center" width="30"><!-- check box -->
					  <col align="center" width="125" /><!-- Branch Code -->
					  <col align="center" width="77" /><!-- Dealer Code -->
					  <col align="center" width="55" /><!-- Division -->
					  <col align="center" width="115" /><!-- Order Category -->
					  <col align="center" width="115" /><!-- Sub Reason Code -->
					  <col align="center" width="127" /><!-- Warehouse -->
					  <col align="center" width="115" /><!-- Bill To Location -->
					  <col align="center" width="90" /><!-- Contact Group -->
					  <col align="center" width="100" /><!-- Start Date -->
					  <col align="center" width="100" /><!-- End Date -->
				  </colgroup>
				  <tbody>
				  <tr height="28">
					  <td class="pClothBs">&nbsp;</td>
					  <td class="pClothBs">Contract Branch</td>
					  <td class="pClothBs">Dealer Code</td>
					  <td class="pClothBs">Division</td>
					  <td class="pClothBs">Order Category</td>
					  <td class="pClothBs">Reason</td>
					  <td class="pClothBs">Warehouse</td>
						<!-- Mod Start 2017/10/04 H.Sugawara QC#19922 -->
					  <!-- <td class="pClothBs">Bill To Location</td> -->
					  <td class="pClothBs">Bill To Code</td>
						<!-- Mod End   2017/10/04 H.Sugawara QC#19922 -->
					  <td class="pClothBs">Contact Group</td>
					  <td class="pClothBs">Start Date</td>
					  <td class="pClothBs">End Date</td>
				  </tr>
				  </tbody>
			  </table>
		  </div>

        <div id="RightTBL" style="MARGIN-LEFT: 5px; overflow-y:scroll; height:400; overflow-x:none; width:1117px;" 
            onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
			  <table cellSpacing="0" cellPadding="1" border="1" width="" id="A">
				  <colgroup>
	                  <col align="center" width="30"><!-- check box -->
					  <col align="center" width="125" /><!-- Branch Code -->
					  <col align="center" width="77" /><!-- Dealer Code -->
					  <col align="center" width="55" /><!-- Division -->
					  <col align="center" width="115" /><!-- Order Category -->
					  <col align="center" width="115" /><!-- Sub Reason Code -->
					  <col align="center" width="127" /><!-- Warehouse -->
					  <col align="center" width="115" /><!-- Bill To Location -->
					  <col align="center" width="90" /><!-- Contact Group -->
					  <col align="center" width="100" /><!-- Start Date -->
					  <col align="center" width="100" /><!-- End Date -->
				  </colgroup>
				  <tbody>
					<ezf:row ezfHyo="A">
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
						  <td class="stab"><!-- Branch -->
							  <ezf:inputText name="coaBrDescTxt_A" ezfName="coaBrDescTxt_A" value="211" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/>
							  <ezf:anchor name="" ezfName="xxLinkAncr_ABr" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaBr" otherAttr=" id=\"OpenWin_CoaBr{EZF_ROW_NUMBER}\"">Br</ezf:anchor>
						  </td>
						  <td><!-- Dealer Code -->
							  <ezf:inputText name="rtlDlrCd_A" ezfName="rtlDlrCd_A" value="D114" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
						  </td>
						  <td><!-- Division -->
		                      <ezf:select name="rtlDivCd_A" ezfName="rtlDivCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="rtlDivCd_PC" ezfDispName="rtlDivCd_PD" otherAttr=" style=\"width: 50px\""/>
						  </td>
						  <td class="stab"><!-- Order Category -->
								<ezf:inputText name="dsOrdCatgDescTxt_A" ezfName="dsOrdCatgDescTxt_A" value="GMD USA, CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/>
								<ezf:anchor name="" ezfName="xxLinkAncr_AC" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" otherAttr=" id=\"OpenWin_OrderCategory{EZF_ROW_NUMBER}\"">C</ezf:anchor>
						  </td>
						  <td class="stab"><!-- Sub Reason Code -->
								<ezf:inputText name="dsOrdTpDescTxt_A" ezfName="dsOrdTpDescTxt_A" value="GMD-1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/>
								<ezf:anchor name="" ezfName="xxLinkAncr_AR" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderReason" otherAttr=" id=\"OpenWin_OrderReason{EZF_ROW_NUMBER}\"">R</ezf:anchor>
						  </td>
						  <td class="stab"><!-- Warehouse -->
							  <ezf:inputText name="rtlWhNm_A" ezfName="rtlWhNm_A" value="MONROE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
								<ezf:anchor name="" ezfName="xxLinkAncr_AW" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" id=\"OpenWin_Warehouse{EZF_ROW_NUMBER}\"">W</ezf:anchor>
						  </td>
						  <td class="stab"><!-- Bill To Location -->
							  <ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="1002826" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/>
								<ezf:anchor name="" ezfName="xxLinkAncr_AB" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_BillTo" otherAttr=" id=\"OpenWin_BillTo{EZF_ROW_NUMBER}\"">B</ezf:anchor>
						  </td>
						  <td class="stab"><!-- Contact Group -->
							  <ezf:inputText name="contrGrpCd_A" ezfName="contrGrpCd_A" value="298" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"3\" ezftoupper=\"\""/>
						  </td>
						  <td class="stab"><!-- Start Date -->
							  <ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="02/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');" >
						  </td>
						  <td class="stab"><!-- End Date -->
							  <ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
							<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');" >
							</td>
						</tr>
					</ezf:row>
				  </tbody>
			  </table>
		  </div>

		  <!-- footer buttons -->

        <!-- ################################################## Search Result   - [E N D] ################################################## -->
        </DIV></TD></TR></TBODY></TABLE></CENTER>



<%-- **** Task specific area ends here **** --%>
