<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171005093605 --%>
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
			<input type="hidden" name="pageID" value="NWCL0140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="CFS Lease Package Maintenance">

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
                            <tr title="CFS Lease Pac Maintenance">
                                <td width="107px" align="center" class="same">CFS Lease Pac Mnt</td>
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
        <div>
<!-- ################################################## Search Criteria - [START] ################################################## -->
			<fieldset>
				<legend class="stab"><font color="black">Eligible Account and Bill To Locations</font></legend>
				<!-- Line1 -->
				<table class="header-line" style="margin-top: 8px;" border="0" cellpadding="0" cellspacing="0">
			  		<colgroup>
						  <!-- Dealer Code -->
						  <col width="30" /><!-- col1 header -->
						  <col width="80" /><!-- col1 header -->
						<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
						  <!-- <col width="50" /> --><!-- col1 header -->
						  <col width="80" /><!-- col1 header -->
						<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						  <col width="50" />
						  <!-- Division -->
						<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
						  <!-- <col width="600" /> -->
						  <col width="570" />
						<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						<col width="" />
			  		</colgroup>
			  		<tbody>
			            <tr height="30">
							<td>
								<ezf:anchor name="xxLinkAncr_AH" ezfName="xxLinkAncr_AH" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctH" >Account</ezf:anchor>
							</td>
							<td>
								<ezf:inputText name="billToCustAcctCd" ezfName="billToCustAcctCd" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
							</td>
							<td>
								<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
								<!-- <a href="#" name="xxLinkAncr_LH" ezfname="xxLinkAncr_LH" onclick="sendServer('OpenWin_LocH')" >Location</a> -->
								<ezf:anchor name="xxLinkAncr_LH" ezfName="xxLinkAncr_LH" ezfEmulateBtn="1" ezfGuard="OpenWin_LocH" >Bill To Code</ezf:anchor>
								<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
							</td>
							<td>
								<ezf:inputText name="billToCustCd" ezfName="billToCustCd" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
							</td>
							<td>
								<ezf:inputButton name="SearchEligAcct" value="Search" htmlClass="pBtn6" />
							</td>
			  			</tr>
			  		</tbody>
				</table>
				<table border="0" style="table-layout:fixed;width=500px;margin-left:20;">
					<col width="80">
					<col width="390">
					<col width="610">
					<col width="10">
					<tr>
						<td>
							<ezf:inputButton name="AddLineEligAcct" value="Add" htmlClass="pBtn6" />
						</td>
						<td>
							<ezf:inputButton name="DeleteLineEligAcct" value="Delete" htmlClass="pBtn6" />
						</td>
						<!-- Pagination & Navigation--START-->
						<td>
							<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
								<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"           value="A" />
								<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_EL" />
								<jsp:param name="ShowingTo"         value="xxPageShowToNum_EL" />
								<jsp:param name="ShowingOf"         value="xxPageShowOfNum_EL" />
								<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_EL" />
								<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_EL" />
								<jsp:param name="ViewMode"          value="FULL" />
							</jsp:include>
							<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
													<td>Results 1 - 40 of 1000</td>
													</tr>
											</table>
										</td>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="center">
												<col width="16"  align="center">
												<col width="40"  align="center">
												<col width="26"  align="center">
												<col width="10">
												<col>
												<col width="20">
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="25" class="pPro" style="text-align:right" readOnly></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</ezf:skip>
						</td>
						<!-- Pagination & Navigation--END-->
					</tr>
				</table>
			<div id="topRightTBL" style="MARGIN-LEFT: 5px; overflow-y:none; overflow-x:hidden; width:1100px;">
			  <table cellSpacing="0" cellPadding="1" border="1" width="">
				  <colgroup>
	                  <col align="center" width="35"><!-- check box -->
					  <col align="center" width="180" /><!-- Account Number -->
					  <col align="center" width="180" /><!-- Location Number -->
					  <col align="center" width="690" /><!-- Account Name -->
				  </colgroup>
				  <tbody>
				  <tr height="28">
					  <td class="pClothBs">&nbsp;</td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctCd_EL' )">Account Number<img id="sortIMG.billToCustAcctCd_EL" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
					  <!-- <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_EL' )">Location Number<img id="sortIMG.billToCustCd_EL" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td> -->
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_EL' )">Bill To Code<img id="sortIMG.billToCustCd_EL" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_EL' )">Account Name<img id="sortIMG.dsAcctNm_EL" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
				  </tr>
				  </tbody>
			  </table>
		  </div>

        <div id="RightTBL" style="MARGIN-LEFT: 5px; overflow-y:scroll; height:135px; overflow-x:none; width:1117px;">
			  <table cellSpacing="0" cellPadding="1" border="1" width="" id="A">
				  <colgroup>
	                  <col align="center" width="35"><!-- check box -->
					  <col align="left" width="180" /><!-- Account Number -->
					  <col align="left" width="180" /><!-- Location Number -->
					  <col align="left" width="690" /><!-- Account Name -->
				  </colgroup>
				  <tbody>
				  <ezf:row ezfHyo="A">
					  <tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<ezf:inputCheckBox name="xxChkBox_EL" ezfName="xxChkBox_EL" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_EL{EZF_ROW_NUMBER}\""/>
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <ezf:inputText name="billToCustAcctCd_EL" ezfName="billToCustAcctCd_EL" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"20\" ezftoupper=\"\""/>
							  <ezf:anchor name="xxLinkAncr_AD" ezfName="xxLinkAncr_AD" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctD', '{EZF_ROW_NUMBER}" >
							  	<label>A</label>
							  </ezf:anchor>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <ezf:inputText name="billToCustCd_EL" ezfName="billToCustCd_EL" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"20\" ezftoupper=\"\""/>
                              <ezf:anchor name="xxLinkAncr_LD" ezfName="xxLinkAncr_LD" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_LocD', '{EZF_ROW_NUMBER}" >
								<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
                              	<!-- <label>L</label> -->
                              	<label>B</label>
								<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
                              </ezf:anchor>
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <ezf:inputText name="dsAcctNm_EL" ezfName="dsAcctNm_EL" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"95\" maxlength=\"360\" ezftoupper=\"\""/>
						  </td>
					  </tr>
				  </ezf:row>
				  <ezf:skip>
				  		<tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
								<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
                              <!-- <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a> -->
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >B</a>
								<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
								<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
                              <!-- <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a> -->
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >B</a>
								<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
								<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
                              <!-- <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a> -->
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >B</a>
								<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
								<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
                              <!-- <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a> -->
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >B</a>
								<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a>
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a>
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a>
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a>
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a>
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EL" value="Y" ezfname="xxChkBox_EL" ezfhyo="A" id="xxChkBox_EL{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Account Number -->
							  <input type="text" class="pHsu" size="22" maxlength="20" name="billToCustAcctCd_E1" ezfname="billToCustAcctCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_AcctD')" >A</a>
						  </td>
						  <td class="stab"><!-- Location Number -->
							  <input type="text" size="22" maxlength="20" name="billToCustCd_E1" ezfname="billToCustCd_E1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="whMgrPsnCd_H2" ezfname="whMgrPsnCd_H2" onclick="sendServer('OpenWin_LocD')" >L</a>
						  </td>
						  <td class="stab"><!-- Account Name -->
							  <input type="text" size="95" maxlength="360" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" ezfhyo="A" ezfout value="----+----1----+----2" class="pPro" ezftoupper />
						  </td>
					  </tr>
				  </ezf:skip>
				  </tbody>
			  </table>
		  </div>
		</fieldset>
		<table>
			<tbody>
			<tr height="50">
				<td class="stab"><label>Invoiced Threshold(%)</label></td>
				<td>
					<ezf:inputText name="attrbValNum" ezfName="attrbValNum" value="99.5" otherAttr=" size=\"20\" maxlength=\"33\" style=\"text-align:right\""/>
				</td>
			</tr>
			</tbody>
		</table>
		<fieldset>
				<legend class="stab"><font color="black">Exclusion/Inclusion Order Category</font></legend>
				<!-- Line1 -->
				<table class="header-line" style="margin-top: 8px;" border="0" cellpadding="0" cellspacing="0">
			  		<colgroup>
						  <!-- Dealer Code -->
						  <col width="30" /><!-- col1 header -->
						  <col width="80" /><!-- col1 header -->
						  <col width="50" /><!-- col1 header -->
						  <col width="50" />
						  <!-- Division -->
						  <col width="600" />
						<col width="" />
			  		</colgroup>
			  		<tbody>
			            <tr height="30">
							<td>
								<ezf:anchor name="xxLinkAncr_OH" ezfName="xxLinkAncr_OH" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategoryH" >Category</ezf:anchor>
							</td>
							<td>
								<ezf:inputText name="dsOrdCatgDescTxt" ezfName="dsOrdCatgDescTxt" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
							</td>
							<td>
								<ezf:anchor name="xxLinkAncr_RH" ezfName="xxLinkAncr_RH" ezfEmulateBtn="1" ezfGuard="OpenWin_ReasonH" >Reason</ezf:anchor>
							</td>
							<td>
								<ezf:inputText name="dsOrdTpDescTxt" ezfName="dsOrdTpDescTxt" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
							</td>
							<td>
								<ezf:inputButton name="SearchEligOrdCatg" value="Search" htmlClass="pBtn6" />
							</td>
			  			</tr>
			  		</tbody>
				</table>
				<table border="0" style="table-layout:fixed;width=500px;margin-left:20;">
					<col width="80">
					<col width="100">
					<col width="600">
					<col width="100">
					<tr>
						<td>
							<ezf:inputButton name="AddLineEligOrdCatg" value="Add" htmlClass="pBtn6" />
						</td>
						<td>
							<ezf:inputButton name="DeleteLineEligOrdCatg" value="Delete" htmlClass="pBtn6" />
						</td>
						<!-- Pagination & Navigation--START-->
						<td>
							<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
								<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"           value="B" />
								<jsp:param name="ShowingFrom"       value="xxPageShowFromNum_EX" />
								<jsp:param name="ShowingTo"         value="xxPageShowToNum_EX" />
								<jsp:param name="ShowingOf"         value="xxPageShowOfNum_EX" />
								<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum_EX" />
								<jsp:param name="ShowingTotal"      value="xxPageShowTotNum_EX" />
								<jsp:param name="ViewMode"          value="FULL" />
							</jsp:include>
							<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
													<td>Results 1 - 40 of 1000</td>
													</tr>
											</table>
										</td>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="center">
												<col width="16"  align="center">
												<col width="40"  align="center">
												<col width="26"  align="center">
												<col width="10">
												<col>
												<col width="20">
												<col>
												<col width="1">
												<col>
												<tr>
													<td class="stab">Showing</td>
													<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right"></td>
													<td class="stab">/</td>
													<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="25" class="pPro" style="text-align:right" readOnly></td>
													<td class="stab">page</td>
													<td>&nbsp;</td>
													<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</ezf:skip>
						</td>
						<!-- Pagination & Navigation--END-->
					</tr>
				</table>
			<div id="topRightTBLEX" style="MARGIN-LEFT: 5px; overflow-y:none; overflow-x:hidden; width:800px;">
			  <table cellSpacing="0" cellPadding="1" border="1" width="">
				  <colgroup>
	                  <col align="center" width="35"><!-- check box -->
					  <col align="center" width="355" /><!-- Order Category -->
					  <col align="center" width="310" /><!-- Reason -->
					  <col align="center" width="80" /><!-- Include -->
				  </colgroup>
				  <tbody>
				  <tr height="28">
					  <td class="pClothBs">&nbsp;</td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsOrdCatgDescTxt_EX' )">Order Category<img id="sortIMG.dsOrdCatgDescTxt_EX" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsOrdTpDescTxt_EX' )">Reason<img id="sortIMG.dsOrdTpDescTxt_EX" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'ordCatgInclFlg_EX' )">Include<img id="sortIMG.ordCatgInclFlg_EX" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
				  </tr>
				  </tbody>
			  </table>
		  </div>

        <div id="RightTBLEX" style="MARGIN-LEFT: 5px; overflow-y:scroll; height:135px; overflow-x:none; width:817px;">
			  <table cellSpacing="0" cellPadding="1" border="1" width="" id="B">
				  <colgroup>
	                  <col align="center" width="35"><!-- check box -->
					  <col align="left" width="355" /><!-- Order Category -->
					  <col align="left" width="310" /><!-- Reason -->
					  <col align="center" width="80" /><!-- Include -->
				  </colgroup>
				  <tbody>
				  <ezf:row ezfHyo="B">
					  <tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<ezf:inputCheckBox name="xxChkBox_EX" ezfName="xxChkBox_EX" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_EX{EZF_ROW_NUMBER}\""/>
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <ezf:inputText name="dsOrdCatgDescTxt_EX" ezfName="dsOrdCatgDescTxt_EX" value="----+----1----+----2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"46\" maxlength=\"50\" ezftoupper=\"\""/>
							  <ezf:anchor name="xxLinkAncr_OD" ezfName="xxLinkAncr_OD" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategoryD', '{EZF_ROW_NUMBER}" >
							  	<label>C</label>
							  </ezf:anchor>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <ezf:inputText name="dsOrdTpDescTxt_EX" ezfName="dsOrdTpDescTxt_EX" value="----+----1----+----2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/>
                              <ezf:anchor name="xxLinkAncr_RD" ezfName="xxLinkAncr_RD" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ReasonD', '{EZF_ROW_NUMBER}" >
                              	<label>R</label>
                              </ezf:anchor>
						  </td>
						  <td class="stab"><!-- Include -->
							  <ezf:inputCheckBox name="ordCatgInclFlg_EX" ezfName="ordCatgInclFlg_EX" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"ordCatgInclFlg_EX{EZF_ROW_NUMBER}\""/>
						  </td>
					  </tr>
				  </ezf:row>
				  <ezf:skip>
				  	<tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EX" value="Y" ezfname="xxChkBox_EX" ezfhyo="B" id="xxChkBox_EX{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <input type="text" class="pHsu" size="46" maxlength="50" name="dsOrdCatgDescTxt_EX" ezfname="dsOrdCatgDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="xxLinkAncr_OD" ezfname="xxLinkAncr_OD" onclick="sendServer('OpenWin_OrderCategoryD', '{EZF_ROW_NUMBER}')" >C</a>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <input type="text" class="pHsu" size="40" maxlength="50" name="dsOrdTpDescTxt_EX" ezfname="dsOrdTpDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="xxLinkAncr_RD" ezfname="xxLinkAncr_RD" onclick="sendServer('OpenWin_ReasonD', '{EZF_ROW_NUMBER}')" >R</a>
						  </td>
						  <td class="stab"><!-- Include -->
							  <input type="checkbox" name="ordCatgInclFlg_EX" value="Y" ezfname="ordCatgInclFlg_EX" ezfhyo="B" id="ordCatgInclFlg_EX{EZF_ROW_NUMBER}">
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EX" value="Y" ezfname="xxChkBox_EX" ezfhyo="B" id="xxChkBox_EX{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <input type="text" class="pHsu" size="46" maxlength="50" name="dsOrdCatgDescTxt_EX" ezfname="dsOrdCatgDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="xxLinkAncr_OD" ezfname="xxLinkAncr_OD" onclick="sendServer('OpenWin_OrderCategoryD')" >C</a>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <input type="text" class="pHsu" size="40" maxlength="50" name="dsOrdTpDescTxt_EX" ezfname="dsOrdTpDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="xxLinkAncr_RD" ezfname="xxLinkAncr_RD" onclick="sendServer('OpenWin_ReasonD')" >R</a>
						  </td>
						  <td class="stab"><!-- Include -->
							  <input type="checkbox" name="ordCatgInclFlg_EX" value="Y" ezfname="ordCatgInclFlg_EX" ezfhyo="B" id="ordCatgInclFlg_EX{EZF_ROW_NUMBER}">
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EX" value="Y" ezfname="xxChkBox_EX" ezfhyo="B" id="xxChkBox_EX{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <input type="text" class="pHsu" size="46" maxlength="50" name="dsOrdCatgDescTxt_EX" ezfname="dsOrdCatgDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="xxLinkAncr_OD" ezfname="xxLinkAncr_OD" onclick="sendServer('OpenWin_OrderCategoryD')" >C</a>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <input type="text" class="pHsu" size="40" maxlength="50" name="dsOrdTpDescTxt_EX" ezfname="dsOrdTpDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="xxLinkAncr_RD" ezfname="xxLinkAncr_RD" onclick="sendServer('OpenWin_ReasonD')" >R</a>
						  </td>
						  <td class="stab"><!-- Include -->
							  <input type="checkbox" name="ordCatgInclFlg_EX" value="Y" ezfname="ordCatgInclFlg_EX" ezfhyo="B" id="ordCatgInclFlg_EX{EZF_ROW_NUMBER}">
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EX" value="Y" ezfname="xxChkBox_EX" ezfhyo="B" id="xxChkBox_EX{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <input type="text" class="pHsu" size="46" maxlength="50" name="dsOrdCatgDescTxt_EX" ezfname="dsOrdCatgDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="xxLinkAncr_OD" ezfname="xxLinkAncr_OD" onclick="sendServer('OpenWin_OrderCategoryD')" >C</a>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <input type="text" class="pHsu" size="40" maxlength="50" name="dsOrdTpDescTxt_EX" ezfname="dsOrdTpDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="xxLinkAncr_RD" ezfname="xxLinkAncr_RD" onclick="sendServer('OpenWin_ReasonD')" >R</a>
						  </td>
						  <td class="stab"><!-- Include -->
							  <input type="checkbox" name="ordCatgInclFlg_EX" value="Y" ezfname="ordCatgInclFlg_EX" ezfhyo="B" id="ordCatgInclFlg_EX{EZF_ROW_NUMBER}">
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EX" value="Y" ezfname="xxChkBox_EX" ezfhyo="B" id="xxChkBox_EX{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <input type="text" class="pHsu" size="46" maxlength="50" name="dsOrdCatgDescTxt_EX" ezfname="dsOrdCatgDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="xxLinkAncr_OD" ezfname="xxLinkAncr_OD" onclick="sendServer('OpenWin_OrderCategoryD')" >C</a>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <input type="text" class="pHsu" size="40" maxlength="50" name="dsOrdTpDescTxt_EX" ezfname="dsOrdTpDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="xxLinkAncr_RD" ezfname="xxLinkAncr_RD" onclick="sendServer('OpenWin_ReasonD')" >R</a>
						  </td>
						  <td class="stab"><!-- Include -->
							  <input type="checkbox" name="ordCatgInclFlg_EX" value="Y" ezfname="ordCatgInclFlg_EX" ezfhyo="B" id="ordCatgInclFlg_EX{EZF_ROW_NUMBER}">
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EX" value="Y" ezfname="xxChkBox_EX" ezfhyo="B" id="xxChkBox_EX{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <input type="text" class="pHsu" size="46" maxlength="50" name="dsOrdCatgDescTxt_EX" ezfname="dsOrdCatgDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="xxLinkAncr_OD" ezfname="xxLinkAncr_OD" onclick="sendServer('OpenWin_OrderCategoryD')" >C</a>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <input type="text" class="pHsu" size="40" maxlength="50" name="dsOrdTpDescTxt_EX" ezfname="dsOrdTpDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="xxLinkAncr_RD" ezfname="xxLinkAncr_RD" onclick="sendServer('OpenWin_ReasonD')" >R</a>
						  </td>
						  <td class="stab"><!-- Include -->
							  <input type="checkbox" name="ordCatgInclFlg_EX" value="Y" ezfname="ordCatgInclFlg_EX" ezfhyo="B" id="ordCatgInclFlg_EX{EZF_ROW_NUMBER}">
						  </td>
					  </tr>
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                          <td class="stab">
                          	<input type="checkbox" name="xxChkBox_EX" value="Y" ezfname="xxChkBox_EX" ezfhyo="B" id="xxChkBox_EX{EZF_ROW_NUMBER}">
                          </td>
						  <td class="stab"><!-- Order Category -->
							  <input type="text" class="pHsu" size="46" maxlength="50" name="dsOrdCatgDescTxt_EX" ezfname="dsOrdCatgDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
							  <a href="#" name="xxLinkAncr_OD" ezfname="xxLinkAncr_OD" onclick="sendServer('OpenWin_OrderCategoryD')" >C</a>
						  </td>
						  <td class="stab"><!-- Reason -->
							  <input type="text" class="pHsu" size="40" maxlength="50" name="dsOrdTpDescTxt_EX" ezfname="dsOrdTpDescTxt_EX" ezfhyo="B" ezfout value="----+----1----+----2" ezftoupper />
                              <a href="#" name="xxLinkAncr_RD" ezfname="xxLinkAncr_RD" onclick="sendServer('OpenWin_ReasonD')" >R</a>
						  </td>
						  <td class="stab"><!-- Include -->
							  <input type="checkbox" name="ordCatgInclFlg_EX" value="Y" ezfname="ordCatgInclFlg_EX" ezfhyo="B" id="ordCatgInclFlg_EX{EZF_ROW_NUMBER}">
						  </td>
					  </tr>
				  </ezf:skip>
				  </tbody>
			  </table>
		  </div>
		</fieldset>

        <!-- ################################################## Search Result   - [E N D] ################################################## -->
        </DIV></TD></TR></TBODY></TABLE></CENTER>



<%-- **** Task specific area ends here **** --%>
