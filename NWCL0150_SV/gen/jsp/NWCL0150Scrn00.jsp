<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170921181120 --%>
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
			<input type="hidden" name="pageID" value="NWCL0150Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Lease Package Hold Release">

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
<!-- ################################################## Search Criteria - [START] ################################################## -->
			<!-- Line1 -->
		 	<table class="header-line" style="margin-top: 8px;" border="0" cellpadding="0" cellspacing="0">
			  <colgroup>
				  <col width="70" />
				  <col width="100" />
				  <col width="80" />
				  <col width="100" />
				  <col width="70" />
				  <col width="100" />
				  <col width="90" />
				  <col width="100" />
				  <col width="100" />
			  </colgroup>
			  <tbody>
              <tr height="30">
				  <td class="stab"><label>Order Number</label></td>
				  <td>
					  <ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
				  </td>
				  <td class="stab"><label>Invoice Number</label></td>
				  <td>
					  <ezf:inputText name="invNum" ezfName="invNum" otherAttr=" size=\"15\" maxlength=\"13\" ezftoupper=\"\""/>
				  </td>
				  <td class="stab"><label>Hold Status</label></td>
				  <td>
                      <ezf:select name="cfsLeasePkgHldFlg_SL" ezfName="cfsLeasePkgHldFlg_SL" ezfBlank="1" ezfCodeName="cfsLeasePkgHldFlg_HF" ezfDispName="xxFlgNm_HF" otherAttr=" style=\"width:80px\""/>
				  </td>
				  <td class="stab"><label>Package Created</label></td>
				  <td>
                      <ezf:select name="leasePkgCratFlg_SL" ezfName="leasePkgCratFlg_SL" ezfBlank="1" ezfCodeName="leasePkgCratFlg_CF" ezfDispName="xxFlgNm_CF" otherAttr=" style=\"width:80px\""/>
				  </td>
				  <td>
					<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
				  </td>
			  </tr>
			  </tbody>
		  </table>
		</div>
		<hr />
<!-- ################################################## Search Criteria - [START] ################################################## -->
		  <table border="0" style="table-layout:fixed;width=500px;margin-left:20;">
		  			<col width="80">
					<col width="450">
					<col width="550">
					<tr>
						<td class="stab"><label>Threshold</label></td>
						<td>
							<ezf:inputText name="attrbValNum" ezfName="attrbValNum" value="99.55" otherAttr=" size=\"15\" maxlength=\"33\""/>
						</td>
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
			<div id="topRightTBLEX" style="MARGIN-LEFT: 5px; overflow-y:none; overflow-x:hidden; width:1100px;">
			  <table cellSpacing="0" cellPadding="1" border="1" width="">
				  <colgroup>
					  <col align="center" width="80"><!-- CFS PO Number -->
	                  <col align="center" width="80" /><!-- Order Number -->
					  <col align="center" width="122" /><!--Account Number -->
					  <col align="center" width="119" /><!-- Account Name -->
					  <col align="center" width="115" /><!-- CFS PO Amount -->
					  <col align="center" width="114" /><!-- Percent Invoiced -->
					  <col align="center" width="60" /><!--Credit Rebill -->
					  <col align="center" width="60" /><!--Hold Status -->
					  <col align="center" width="60" /><!--Package Created -->
					  <col align="center" width="375" /><!--Manual Hold Action -->
				  </colgroup>
				  <tbody>
				  <tr height="28">
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsPoNum_A1' )">CFS PO Number<img id="sortIMG.cfsPoNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cpoOrdNum_A1' )">Order Number<img id="sortIMG.cpoOrdNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A1' )">Account Number<img id="sortIMG.dsAcctNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Account Name<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsPoAmt_A1' )">CFS PO Amount<img id="sortIMG.cfsPoAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invCpltAmtRate_A1' )">Percent Invoiced<img id="sortIMG.invCpltAmtRate_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'crRebilHldFlg_A1' )">Credit Rebill<img id="sortIMG.crRebilHldFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsLeasePkgHldFlg_A1' )">Hold Status<img id="sortIMG.cfsLeasePkgHldFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'leasePkgCratFlg_A1' )">Package Created<img id="sortIMG.leasePkgCratFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsManHldActTpCd_A1' )">Manual Hold Action<img id="sortIMG.cfsManHldActTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
				  </tr>
				  </tbody>
			  </table>
		  </div>

        <div id="RightTBLEX" style="MARGIN-LEFT: 5px; overflow-y:scroll; height:450px; overflow-x:none; width:1117px;">
			  <table cellSpacing="0" cellPadding="1" border="1" width="" id="A">
				  <colgroup>
  	                  <col align="center" width="74"><!-- CFS PO Number -->
	                  <col align="center" width="74" /><!-- Order Number -->
					  <col align="center" width="110" /><!--Account Number -->
					  <col align="center" width="106" /><!-- Account Name -->
					  <col align="center" width="85" /><!-- CFS PO Amount -->
					  <col align="center" width="100" /><!-- Percent Invoiced -->
					  <col align="center" width="65" /><!--Credit Rebill -->
					  <col align="center" width="63" /><!--Hold Status -->
					  <col align="center" width="65" /><!--Package Created -->
  					  <col align="left" width="375" /><!--Manual Hold Actionr -->
				  </colgroup>
				  <tbody>
				  <ezf:row ezfHyo="A">
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<ezf:inputText name="cfsPoNum_A1" ezfName="cfsPoNum_A1" value="-" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"8\" ezftoupper=\"\""/>
						  </td>
						  <td class="stab">
							<ezf:inputText name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" value="ABCDEFGH" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"8\" ezftoupper=\"\""/>
						  </td>
						  <td class="stab">
							  <ezf:inputText name="dsAcctNum_A1" ezfName="dsAcctNum_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"13\" ezftoupper=\"\" style=\"text-align:right;\""/>
						  </td>
						  <td class="stab">
							  <ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"20\" ezftoupper=\"\""/>
						  </td>
						  <td class="stab">
							  <ezf:inputText name="cfsPoAmt_A1" ezfName="cfsPoAmt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align:right;\""/>
						  </td>
						  <td class="stab">
							  <ezf:inputText name="invCpltAmtRate_A1" ezfName="invCpltAmtRate_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align:right;\""/>
						  </td>
						  <td class="stab">
							  <ezf:inputCheckBox name="crRebilHldFlg_A1" ezfName="crRebilHldFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"crRebilHldFlg_A1{EZF_ROW_NUMBER}\""/>
						  </td>
						  <td class="stab">
							  <ezf:inputCheckBox name="cfsLeasePkgHldFlg_A1" ezfName="cfsLeasePkgHldFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}\""/>
						  </td>
						  <td class="stab">
							  <ezf:inputCheckBox name="leasePkgCratFlg_A1" ezfName="leasePkgCratFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"leasePkgCratFlg_A1{EZF_ROW_NUMBER}\""/>
						  </td>
						  <td class="stab">
							<ezf:inputRadio name="cfsManHldActTpCd_A1" ezfName="cfsManHldActTpCd_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cfsManHldActTpCd_A1{EZF_ROW_NUMBER}\""/>Release Over Threshold
							<ezf:inputRadio name="cfsManHldActTpCd_A1" ezfName="cfsManHldActTpCd_A1" value="2" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cfsManHldActTpCd_A1{EZF_ROW_NUMBER}\""/>Hold
							<ezf:inputRadio name="cfsManHldActTpCd_A1" ezfName="cfsManHldActTpCd_A1" value="3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"cfsManHldActTpCd_A1{EZF_ROW_NUMBER}\""/>Immediate Release
						  </td>
					  </tr>
				  </ezf:row>
				  <ezf:skip>
				  	<tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="25" name="cfsPoNum_A1" ezfname="cfsPoNum_A1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
						  </td>
						  <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A" ezfout value="ABCDEFGH" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="15" maxlength="13" name="invNum_A1" ezfname="invNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="20" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="cfsPoAmt_A1" ezfname="cfsPoAmt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="invCpltAmtRate_A1" ezfname="invCpltAmtRate_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="crRebilHldFlg_A1" value="Y" ezfname="crRebilHldFlg_A1" ezfhyo="A" id="crRebilHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="cfsLeasePkgHldFlg_A1" value="Y" ezfname="cfsLeasePkgHldFlg_A1" ezfhyo="A" id="cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="leasePkgCratFlg_A1" value="Y" ezfname="leasePkgCratFlg_A1" ezfhyo="A" id="leasePkgCratFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							<input type="Radio" value="Release Over Threshold" name="radio" ezfname="radio">Release Over Threshold
							<input type="Radio" value="Hold" name="radio" ezfname="radio" >Hold
							<input type="Radio" value="Immediate Release" name="displradioayLevel" ezfname="radio" ezftoupper tabindex="34">Immediate Release
						  </td>
					  </tr>
					  <tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cfsPoNum_A1" ezfname="cfsPoNum_A1" ezfhyo="A" ezfout value="-" ezftoupper />
						  </td>
						  <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A" ezfout value="ABCDEFGH" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="15" maxlength="13" name="invNum_A1" ezfname="invNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="20" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="cfsPoAmt_A1" ezfname="cfsPoAmt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="invCpltAmtRate_A1" ezfname="invCpltAmtRate_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="crRebilHldFlg_A1" value="Y" ezfname="crRebilHldFlg_A1" ezfhyo="A" id="crRebilHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="cfsLeasePkgHldFlg_A1" value="Y" ezfname="cfsLeasePkgHldFlg_A1" ezfhyo="A" id="cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="leasePkgCratFlg_A1" value="Y" ezfname="leasePkgCratFlg_A1" ezfhyo="A" id="leasePkgCratFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							<input type="Radio" value="Release Over Threshold" name="radio" ezfname="radio">Release Over Threshold
							<input type="Radio" value="Hold" name="radio" ezfname="radio" >Hold
							<input type="Radio" value="Immediate Release" name="displradioayLevel" ezfname="radio" ezftoupper tabindex="34">Immediate Release
						  </td>
					  </tr>
					  <tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cfsPoNum_A1" ezfname="cfsPoNum_A1" ezfhyo="A" ezfout value="-" ezftoupper />
						  </td>
						  <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A" ezfout value="ABCDEFGH" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="15" maxlength="13" name="invNum_A1" ezfname="invNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="20" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="cfsPoAmt_A1" ezfname="cfsPoAmt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="invCpltAmtRate_A1" ezfname="invCpltAmtRate_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="crRebilHldFlg_A1" value="Y" ezfname="crRebilHldFlg_A1" ezfhyo="A" id="crRebilHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="cfsLeasePkgHldFlg_A1" value="Y" ezfname="cfsLeasePkgHldFlg_A1" ezfhyo="A" id="cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="leasePkgCratFlg_A1" value="Y" ezfname="leasePkgCratFlg_A1" ezfhyo="A" id="leasePkgCratFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							<input type="Radio" value="Release Over Threshold" name="radio" ezfname="radio">Release Over Threshold
							<input type="Radio" value="Hold" name="radio" ezfname="radio" >Hold
							<input type="Radio" value="Immediate Release" name="displradioayLevel" ezfname="radio" ezftoupper tabindex="34">Immediate Release
						  </td>
					  </tr>
					  <tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cfsPoNum_A1" ezfname="cfsPoNum_A1" ezfhyo="A" ezfout value="-" ezftoupper />
						  </td>
						  <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A" ezfout value="ABCDEFGH" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="15" maxlength="13" name="invNum_A1" ezfname="invNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="20" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="cfsPoAmt_A1" ezfname="cfsPoAmt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="invCpltAmtRate_A1" ezfname="invCpltAmtRate_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="crRebilHldFlg_A1" value="Y" ezfname="crRebilHldFlg_A1" ezfhyo="A" id="crRebilHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="cfsLeasePkgHldFlg_A1" value="Y" ezfname="cfsLeasePkgHldFlg_A1" ezfhyo="A" id="cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="leasePkgCratFlg_A1" value="Y" ezfname="leasePkgCratFlg_A1" ezfhyo="A" id="leasePkgCratFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							<input type="Radio" value="Release Over Threshold" name="radio" ezfname="radio">Release Over Threshold
							<input type="Radio" value="Hold" name="radio" ezfname="radio" >Hold
							<input type="Radio" value="Immediate Release" name="displradioayLevel" ezfname="radio" ezftoupper tabindex="34">Immediate Release
						  </td>
					  </tr>
					  <tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cfsPoNum_A1" ezfname="cfsPoNum_A1" ezfhyo="A" ezfout value="-" ezftoupper />
						  </td>
						  <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A" ezfout value="ABCDEFGH" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="15" maxlength="13" name="invNum_A1" ezfname="invNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="20" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="cfsPoAmt_A1" ezfname="cfsPoAmt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="invCpltAmtRate_A1" ezfname="invCpltAmtRate_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="crRebilHldFlg_A1" value="Y" ezfname="crRebilHldFlg_A1" ezfhyo="A" id="crRebilHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="cfsLeasePkgHldFlg_A1" value="Y" ezfname="cfsLeasePkgHldFlg_A1" ezfhyo="A" id="cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="leasePkgCratFlg_A1" value="Y" ezfname="leasePkgCratFlg_A1" ezfhyo="A" id="leasePkgCratFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							<input type="Radio" value="Release Over Threshold" name="radio" ezfname="radio">Release Over Threshold
							<input type="Radio" value="Hold" name="radio" ezfname="radio" >Hold
							<input type="Radio" value="Immediate Release" name="displradioayLevel" ezfname="radio" ezftoupper tabindex="34">Immediate Release
						  </td>
					  </tr>
					  <tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cfsPoNum_A1" ezfname="cfsPoNum_A1" ezfhyo="A" ezfout value="-" ezftoupper />
						  </td>
						  <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A" ezfout value="ABCDEFGH" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="15" maxlength="13" name="invNum_A1" ezfname="invNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="20" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="cfsPoAmt_A1" ezfname="cfsPoAmt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="invCpltAmtRate_A1" ezfname="invCpltAmtRate_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="crRebilHldFlg_A1" value="Y" ezfname="crRebilHldFlg_A1" ezfhyo="A" id="crRebilHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="cfsLeasePkgHldFlg_A1" value="Y" ezfname="cfsLeasePkgHldFlg_A1" ezfhyo="A" id="cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="leasePkgCratFlg_A1" value="Y" ezfname="leasePkgCratFlg_A1" ezfhyo="A" id="leasePkgCratFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							<input type="Radio" value="Release Over Threshold" name="radio" ezfname="radio">Release Over Threshold
							<input type="Radio" value="Hold" name="radio" ezfname="radio" >Hold
							<input type="Radio" value="Immediate Release" name="displradioayLevel" ezfname="radio" ezftoupper tabindex="34">Immediate Release
						  </td>
					  </tr>
					  <tr height="28" id="id_b_row{EZF_ROW_NUMBER}">
					      <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cfsPoNum_A1" ezfname="cfsPoNum_A1" ezfhyo="A" ezfout value="-" ezftoupper />
						  </td>
						  <td class="stab">
							<input type="text" class="pHsu" size="9" maxlength="8" name="cpoOrdNum_A1" ezfname="cpoOrdNum_A1" ezfhyo="A" ezfout value="ABCDEFGH" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="15" maxlength="13" name="invNum_A1" ezfname="invNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="20" name="dsAcctNum_A1" ezfname="dsAcctNum_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="cfsPoAmt_A1" ezfname="cfsPoAmt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="text" class="pHsu" size="14" maxlength="19" name="invCpltAmtRate_A1" ezfname="invCpltAmtRate_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper style="text-align:right;"/>
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="crRebilHldFlg_A1" value="Y" ezfname="crRebilHldFlg_A1" ezfhyo="A" id="crRebilHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="cfsLeasePkgHldFlg_A1" value="Y" ezfname="cfsLeasePkgHldFlg_A1" ezfhyo="A" id="cfsLeasePkgHldFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							  <input type="checkbox" name="leasePkgCratFlg_A1" value="Y" ezfname="leasePkgCratFlg_A1" ezfhyo="A" id="leasePkgCratFlg_A1{EZF_ROW_NUMBER}">
						  </td>
						  <td class="stab">
							<input type="Radio" value="Release Over Threshold" name="radio" ezfname="radio">Release Over Threshold
							<input type="Radio" value="Hold" name="radio" ezfname="radio" >Hold
							<input type="Radio" value="Immediate Release" name="displradioayLevel" ezfname="radio" ezftoupper tabindex="34">Immediate Release
						  </td>
					  </tr>
				  </ezf:skip>
				  </tbody>
			  </table>
		  </div>

        <!-- ################################################## Search Result   - [E N D] ################################################## -->
        </DIV></TD></TR></TBODY></TABLE></CENTER>



<%-- **** Task specific area ends here **** --%>
