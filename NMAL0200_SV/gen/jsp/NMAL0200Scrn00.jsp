<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170530085637 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NMAL0200Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

            <!-- Set Page ID  -->
            <input type="hidden" name="pageID" value="NMAL0200Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL0200Scrn00.title" bundle="${I18N_SCREEN_ID}">Product Categorization Maintenance</fmt:message>">

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
                                <td width="107px" align="center" class="same">Prod Catg Mnt</td>
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

				<table  class="header-line" style="table-layout:fixed;margin-left:20;">
					<col width="160">
					<col width="10">
					<col width="650">
					<col width="10">
					<col width="10">
					<tr>
					<td style="vertical-align: top;">
					<fieldset>
						<legend></legend>
						<table border="0" cellpadding="0" cellspacing="0">
							 <tr>
								<td class="stab">
									<ezf:inputCheckBox name="xxChkBox_BU" ezfName="xxChkBox_BU" value="Y" />
									<ezf:label name="mdseStruElmntTpDescTxt_BU" ezfName="mdseStruElmntTpDescTxt_BU" />
								</td>
								</tr>
								<tr>
									<td class="stab">
										<ezf:inputCheckBox name="xxChkBox_PG" ezfName="xxChkBox_PG" value="Y" />
										<ezf:label name="mdseStruElmntTpDescTxt_PG" ezfName="mdseStruElmntTpDescTxt_PG" />
									</td>
								</tr>
								<tr>
									<td class="stab">
										<ezf:inputCheckBox name="xxChkBox_PF" ezfName="xxChkBox_PF" value="Y" />
										<ezf:label name="mdseStruElmntTpDescTxt_PF" ezfName="mdseStruElmntTpDescTxt_PF" />
									</td>
								</tr>
								<tr>
									<td class="stab">
										<ezf:inputCheckBox name="xxChkBox_PL" ezfName="xxChkBox_PL" value="Y" />
										<ezf:label name="mdseStruElmntTpDescTxt_PL" ezfName="mdseStruElmntTpDescTxt_PL" />
									</td>
								</tr>
								<tr>
									<td class="stab">
										<ezf:inputCheckBox name="xxChkBox_PS" ezfName="xxChkBox_PS" value="Y" />
										<ezf:label name="mdseStruElmntTpDescTxt_PS" ezfName="mdseStruElmntTpDescTxt_PS" />
									</td>
					  			</tr>
					  		</tr>
						</table>
					</fieldset>
					</td>
					<td>&nbsp;</td>
					<td style="vertical-align: top;">
						<table border="0" cellpadding="0" cellspacing="0">
							<col width="120">
							<col width="225">
							<!-- 1 -->
							<tr style="height: 24px;">
								<td class="stab">
									<label><fmt:message key="i18n.NMAL0200Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Hierarchy Code(*)</fmt:message></label>
								</td>
								<td class="stab">
									<ezf:inputText name="prodCtrlCd" ezfName="prodCtrlCd" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
								</td>
							</tr>
							<tr style="height: 24px;">
								<td class="stab">
									<label><fmt:message key="i18n.NMAL0200Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Description(*)</fmt:message></label>
								</td>
								<td class="stab">
									<ezf:inputText name="prodCtrlNm" ezfName="prodCtrlNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"50\" maxlength=\"50\""/>
								</td>
							</tr>
							<tr style="height: 24px;">
								<td class="stab">
									<label><fmt:message key="i18n.NMAL0200Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Relationship Code(*)</fmt:message></label>
								</td>
								<td class="stab">
									<ezf:inputText name="scdProdHrchCd" ezfName="scdProdHrchCd" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
								</td>
							</tr>
							<tr style="height: 24px;">
								<td></td>
								<td class="stab">
									<ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" />
								</td>
							</tr>
						</table>
					</td>
					<td>&nbsp;</td>
					</tr>
				</table>
		<hr />
		
<!-- ################################################## Search Criteria - [START] ################################################## -->
        <table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
            <col width="80">
            <col width="400">
            <col width="630">
            <tr>
            	<td><ezf:inputButton name="AddLine" value="Insert Row" htmlClass="pBtn6" /></td>
            	<td><ezf:inputButton name="DeleteLine" value="Delete Row" htmlClass="pBtn6" /></td>
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

 <!-- ################################################## Search Result   - [START] ################################################## -->
		<table border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;margin-top:5px;">
		<%-- =============== TABLE HEADER =============== --%>
		<tr>
			<td>
	            <div id="topRightTBL" style="width:1118px; display:block; overflow-y:hidden; margin:0px;padding:0px;">
					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
					  <colgroup>
		                  <col align="center" width="35"><!-- Check Box -->
						  <col align="center" width="185" /><!--Level -->
						  <col align="center" width="100" /><!-- Hierarchy Code -->
						  <col align="center" width="332" /><!-- Description -->
						  <col align="center" width="130" /><!-- Relationship Code -->
						  <col align="center" width="327" /><!--Relationship Description -->
					  </colgroup>
					  <tbody>
					  <tr height="28">
						  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_A1' )"><img id="sortIMG.xxChkBox_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseStruElmntTpCd_SL' )"><fmt:message key="i18n.NMAL0200Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Level</fmt:message><img id="sortIMG.mdseStruElmntTpCd_SL" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prodCtrlCd_A1' )"><fmt:message key="i18n.NMAL0200Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Hierarchy Code</fmt:message><img id="sortIMG.prodCtrlCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prodCtrlNm_A1' )"><fmt:message key="i18n.NMAL0200Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Description</fmt:message><img id="sortIMG.prodCtrlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'scdProdHrchCd_A1' )"><fmt:message key="i18n.NMAL0200Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Relationship Code</fmt:message><img id="sortIMG.scdProdHrchCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						  <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDplyByCtrlItemCdNm_A1' )"><fmt:message key="i18n.NMAL0200Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Relationship Description</fmt:message><img id="sortIMG.xxDplyByCtrlItemCdNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					  </tr>
					  </tbody>
				  </table>
			  </div>
			</td>
		</tr>
		<tr>
        	<td style="vertical-align:top;">
				<div style="overflow-x:auto; overflow-y:scroll; height:370px;">
				<table id="A" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
				  <colgroup>
	                  <col align="center" width="35"><!-- Check Box -->
					  <col align="left" width="185" /><!--Level -->
					  <col align="left" width="100" /><!-- Hierarchy Code -->
					  <col align="left" width="332" /><!-- Description -->
					  <col align="left" width="130" /><!-- Relationship Code -->
					  <col align="left" width="327" /><!--Relationship Description -->
				  </colgroup>
				  <tbody>
				  <ezf:row ezfHyo="A">
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1{EZF_ROW_NUMBER}\""/>
						</td>
						<td class="stab">
							<ezf:select name="mdseStruElmntTpCd_A1" ezfName="mdseStruElmntTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="mdseStruElmntTpCd_PD" ezfDispName="xxScrItem130Txt_PD" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_StruElmntType', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:180px;\"">
							</ezf:select>
						</td>
						<td class="stab">
							<ezf:inputText name="prodCtrlCd_A1" ezfName="prodCtrlCd_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"8\" ezftoupper=\"\""/>
						</td>
						<td class="stab">
							<ezf:inputText name="prodCtrlNm_A1" ezfName="prodCtrlNm_A1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"46\" maxlength=\"50\""/>
						</td>
						<td class="stab">
							<ezf:inputText name="scdProdHrchCd_A1" ezfName="scdProdHrchCd_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"8\" ezftoupper=\"\""/>
							<ezf:anchor name="xxLinkAncr_A1" ezfName="xxLinkAncr_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_Relation', '{EZF_ROW_NUMBER}" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </ezf:anchor>
						</td>
						<td class="stab">
							<ezf:inputText name="xxDplyByCtrlItemCdNm_A1" ezfName="xxDplyByCtrlItemCdNm_A1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"45\" maxlength=\"50\" ezftoupper=\"\""/>
						</td>
					</tr>
				  </ezf:row>
				  <ezf:skip>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
					<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
						<td class="stab">
                        	<input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A" id="xxChkBox_A1{EZF_ROW_NUMBER}">
						</td>
						<td class="stab">
							<select class="pHsu" style="width:180px">
								<option></option>
								<option>Level1(Business Unit)</option>
								<option>Level2(Product Group)</option>
								<option>Level3(Product Family)</option>
								<option>Level4(Product Line)</option>
								<option>Level5(Product Series)</option>
							</select>
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="13" maxlength="8" name="prodCtrlCd_A1" ezfname="prodCtrlCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pHsu" size="46" maxlength="50" name="prodCtrlNm_A1" ezfname="prodCtrlNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="15" maxlength="8" name="scdProdHrchCd_A1" ezfname="scdProdHrchCd_A1" ezfhyo="A" ezfout value="----+----1" ezftoupper />
							<a href="#" name="xxLinkAncr_A1" ezfname="xxLinkAncr_A1" ezfhyo="A" onclick="sendServer('OpenWin_Relation', '{EZF_ROW_NUMBER}')" >
                              	<label><fmt:message key="i18n.NMAL0200Scrn00.label.10" bundle="${I18N_SCREEN_ID}">R</fmt:message></label>
                            </a>
						</td>
						<td class="stab">
							<input type="text" class="pPro" size="45" maxlength="50" name="xxDplyByCtrlItemCdNm_A1" ezfname="xxDplyByCtrlItemCdNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4----+----5" ezftoupper />
						</td>
					</tr>
				  </ezf:skip>
				  </tbody>
			  </table>
		  </div>
		  </tr>
		  </td>
		  </table>
        <!-- ################################################## Search Result   - [E N D] ################################################## -->
        </DIV></TD></TR></TBODY></TABLE></CENTER>


<%-- **** Task specific area ends here **** --%>
