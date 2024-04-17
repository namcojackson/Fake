<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170712163034 --%>
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
            <input type="hidden" name="pageID" value="NMAL6880Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="TPC09 WH Mapping Maintenance">

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
                            <tr title="TPC09 WH Mapping Maintenance">
                                <td width="107px" align="center" class="same">TPC09 WH</td>
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
                  <!-- From Zone -->
                  <col width="110" /><!-- col1 header -->
                  <col width="167" />
                  <col width="44" /><!-- col1-2 space -->
                  <!-- From Warehouse -->
                  <col width="120" /><!-- col2 header -->
                  <col width="380" />
                  <col width="" />
              </colgroup>
              <tbody>
              <tr height="30">
                  <td class="stab"><label>ROSS Location (*)</label></td>
                  <td>
                      <ezf:inputText name="vndShipToCustCd" ezfName="vndShipToCustCd" otherAttr=" size=\"30\" maxlength=\"20\" ezftoupper=\"\""/>
                  </td>
                  <td></td>
                  <td class="stab">
                      <ezf:anchor name="xxLinkAncr_H" ezfName="xxLinkAncr_H" ezfEmulateBtn="1" ezfGuard="OpenWinFromWarehouseH" >
                          <label>CSA Warehouse (*)</label>
                      </ezf:anchor>
                  </td>
                  <td class="stab">
                      <ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="1Z1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                      <ezf:inputButton name="SearchWH1" value=">>" htmlClass="pBtn0" />
                      <ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="NORCROSS WH - CSA" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
                  </td>
                  <td>
                    <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
                  </td>
              </tr>
              <tr height="10">
				  <td>&nbsp;</td>
			  </tr>
			  </tbody>
		  </table>
		<hr />
        <div>
<!-- ################################################## Search Criteria - [START] ################################################## -->
                    <table border="0" style="table-layout:fixed;width=1082px;margin-left:20;">
                        <col width="500">
                        <col width="500">
                        <tr>
                            <td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
                            <!-- Pagination & Navigation--START-->
                            <td>
                                <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                    <jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
                                    <jsp:param name="TableNm"           value="A" />
                                    <jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
                                    <jsp:param name="ShowingTo"         value="xxPageShowToNum" />
                                    <jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
                                    <jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
                                    <jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
                                    <jsp:param name="ViewMode"          value="FULL" />
                                </jsp:include>
                                <ezf:skip>
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <col width="54"  align="center">
                                    <col width="40"  align="center">
                                    <col width="16"  align="center">
                                    <col width="40"  align="center">
                                    <col width="16"  align="center">
                                    <col width="10">
                                    <col>
                                    <col width="20">
                                    <col>
                                    <col width="1">
                                    <col>
                                    <tr>
                                        <td class="stab">Showing</td>
                                        <td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" ></td>
                                        <td class="stab">/</td>
                                        <td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="3" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
                                        <td class="stab">page</td>
                                        <td>&nbsp;</td>
                                        <td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')" ></td>
                                        <td></td>
                                        <td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
                                        <td></td>
                                        <td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" ></td>
                                    </tr>
                                </table>
                                </ezf:skip>
                            </td>
                            <!-- Pagination & Navigation--END-->
                        </tr>
                    </table>

 <!-- ################################################## Search Result   - [START] ################################################## -->
            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="left" valign="top">
                        <div id="parentBoxA">
                            <div style="float:left; display:block"><!-- left table -->
                                <div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
                                <div id="leftTbl" style="float:left; display:block; height:383px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
                            </div><!-- left table -->
                            <div style="float:left"><!-- right table -->
                                <div id="rightTblHead" style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                    <table style="table-layout:fixed; margin-right:20px" id="AHEAD" width="1100px" border="1" cellpadding="1" cellspacing="0">
                                        <colgroup>
                                            <col align="center" width="35"><!-- check box -->
                                            <col align="center" width="100" /><!-- ROSS Location -->
                                            <col align="center" width="150" /><!-- CSA Warehouse -->
                                            <col align="center" width="155" /><!-- WH Name -->
                                            <col align="center" width="280" /><!-- Location Address -->
                                            <col align="center" width="130" /><!-- Sub Warehouse -->
                                            <col align="center" width="120" /><!-- Start Date -->
                                            <col align="center" width="120 /><!-- End Date -->
                                        </colgroup>
                                        <tbody>
                                            <tr height="28">
                                                <td class="pClothBs" id="AH0">&nbsp;</td>
                                                <td class="pClothBs" id="AH1">ROSS Location</td>
                                                <td class="pClothBs" id="AH2">CSA Warehouse</td>
                                                <td class="pClothBs" id="AH3">Warehouse Location Name</td>
                                                <td class="pClothBs" id="AH4">Warehouse Location Address</td>
                                                <td class="pClothBs" id="AH5">Sub Warehouse</td>
                                                <td class="pClothBs" id="AH6">Start Date</td>
                                                <td class="pClothBs" id="AH7">End Date</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="rightTbl" style="width:1117px; height:410px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                    <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1100px">
                                        <colgroup>
                                            <col align="center" width="35"><!-- check box -->
                                            <col align="left" width="100" /><!-- ROSS Location -->
                                            <col align="left" width="150" /><!-- CSA Warehouse -->
                                            <col align="left" width="155" /><!-- WH Name -->
                                            <col align="left" width="280" /><!-- Location Address -->
                                            <col align="left" width="130" /><!-- Sub Warehouse -->
                                            <col align="left" width="120" /><!-- Start Date -->
                                            <col align="left" width="120" /><!-- End Date -->
                                        </colgroup>
                                        <tbody>
                                          <ezf:row ezfHyo="A">
                                              <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                                                  <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/></td>
                                                  <td><!-- ROSS Location -->
                                                      <ezf:inputText name="vndShipToCustCd_A1" ezfName="vndShipToCustCd_A1" value="AET80" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
                                                  </td>
                                                  <td class="stab"><!-- CSA Warehouse -->
                                                      <ezf:inputButton name="OpenWinFromWarehouseD" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                      <ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="1Z1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\" ezftoupper=\"\""/>
                                                      <ezf:inputButton name="SearchWH2" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                  </td>
                                                  <td><!-- WH Name -->
                                                      <ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="Monro Dock JBURG" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
                                                  </td>
                                                  <td><!-- Location Address -->
                                                      <ezf:inputText name="xxAllLineAddr_A1" ezfName="xxAllLineAddr_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"400\" ezftoupper=\"\""/>
                                                  </td>
                                                  <td class="stab"><!-- SWH -->
                                                      <ezf:inputButton name="OpenWinFromSubWarehouseD" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                      <ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\" ezftoupper=\"\""/>
                                                  </td>
                                                  <td class="stab"><!-- Start Date -->
                                                      <ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/>
                                                    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, {EZF_ROW_NUMBER});"/> 
                                                  </td>
                                                  <td class="stab"><!-- End Date -->
                                                      <ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/>
                                                    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, {EZF_ROW_NUMBER});"/> 
                                                  </td>
                                              </tr>
                                          </ezf:row>
                                      </tbody>
                                  </table>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
            <!-- footer buttons -->
            <table style="margin: 6px 0 0 10px" border="0" cellpadding="1" cellspacing="1">
                <colgroup>
                    <col width="" align="left">
                    <col width="" align="left">
                    <col width="945" align="right">
                </colgroup>
                <tbody>
                    <tr>
                        <td><ezf:inputButton name="AddDetailLine" value="Add Line" htmlClass="pBtn6" /></td>
                        <td><ezf:inputButton name="CancelDetailLine" value="Delete Line" htmlClass="pBtn6" /></td>
                    </tr>
                </tbody>
            </table>

        <!-- ################################################## Search Result   - [E N D] ################################################## -->
        </DIV></TD></TR></TBODY></TABLE></CENTER>
        <script type="text/javascript" defer>
            S21TableUI.initialize("parentBoxA", "AHEAD", "A", , true, true);
        </script>


<%-- **** Task specific area ends here **** --%>
