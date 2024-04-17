<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170123165712 --%>
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
			<input type="hidden" name="pageID" value="NLCL0320Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Adjustment Account Allias">
<%-- ######################################## Search Criteria - [START] ######################################## --%>
<center>
    <table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
        <tbody>
            <tr>
                <td>
                    <%-- ********************** --%>
                    <%-- *** Upper Tab Area *** --%>
                    <%-- ********************** --%>
                    <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                    <ezf:skip>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                            <tr>
                                <td width="1070px" height="28px" valign="bottom">
                                    <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                                        <tr title="Adjustment Account Allias">
                                            <td width="107px" align="center" class="same">Adj Acct Alias</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                <div class="pTab_BODY" style="WIDTH: 1133px; word-break:kepp-all">
									<table width="815" height="35" border = "0">
										<col align="left" width="30">
										<col align="left" width="100">
										<col align="left" width="500">
										<col align="left" width="200">
										<col align="left" width="15">
										<tr>
											<td class="stab">&nbsp;</td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Click_Link_RetailWh" >Warehouse</ezf:anchor></td>
											<td><ezf:inputText name="rtlWhCd_H" ezfName="rtlWhCd_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"12\" maxlength=\"20\" tabindex=\"1\" ezftoupper=\"\""/>
				     						  <ezf:inputButton name="Click_Btn_RetailWh" value=">>" htmlClass="pBtn0" otherAttr=" tabindex=\"1\""/>
											    <ezf:inputText name="rtlWhNm_H" ezfName="rtlWhNm_H" value="XXXX1XXXX2XXX" otherAttr=" size=\"33\" maxlength=\"13\" tabindex=\"1\" ezftoupper=\"\""/>
											</td>
											<td><ezf:inputCheckBox name="xxChkBox_H" ezfName="xxChkBox_H" value="Y" otherAttr=" tabindex=\"1\""/>Active Records Only</td>
											<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/></td>
										</tr>
									</table>
                </div>
<%-- ######################################## Search Result - [START] ######################################## --%>
								<hr>

                <%@ page import="business.servlet.NLCL0320.*" %>
                <%@ include file="../common/common.jsp" %>
                <% NLCL0320BMsg scrnMsg = (NLCL0320BMsg)databean.getEZDBMsg(); %>

            		<table border="0" cellpadding="1" cellspacing="0" width="1100">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="100%" border="1">
												<col width="5">
												<col width="">
												<col width="500" align="left">
												<tr>
                          <td>&nbsp;</td>
												  <td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										      </td>
													<td  align="right">
														<table>
															<tr>
																<td>
																<ezf:skip>
																								<table border="0" cellpadding="0" cellspacing="0">
																									<col width="54"  align="center">
																									<col width="40"  align="right">
																									<col width="16"  align="center">
																									<col width="40"  align="right">
																									<col width="16"  align="center">
																									<col width="40"  align="right">
																									<col width="10">
																									<col>
																									<col width="1">
																									<col>
																									<tr>
																										<td class="stab">Showing</td>
																										<td class="pOut">1</td>
																										<td class="stab">to</td>
																										<td class="pOut">6</td>
																										<td class="stab">of</td>
																										<td class="pOut">6</td>
																										<td>&nbsp;</td>
																										<td><input type="button" class="pBtn3" value="Prev"  id="btnPrev" name="PagePrev" onclick="sendServer(this)" ></td>
																										<td></td>
																										<td><input type="button" class="pBtn3" value="Next"  id="btnNext" name="PageNext" onclick="sendServer(this)" ></td>
																									</tr>
																								</table>
																</ezf:skip>
																<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="A" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
																</jsp:include>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									</table>
					<table border="0" cellpadding="1" cellspacing="0">
						<col width="5">
						<col width="">
						<tr>
								<td>&nbsp;</td>
								<td>
									<div id="parentBoxA">
										<div style="float:left; display:block"><!-- left table -->
											<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
											<div id="leftTbl" style="float:left; display:block; height:420px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
										</div><!-- left table -->
										<div style="float:left"><!-- right table -->
											<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1050px" style="margin-right:20px">
													<col width="30" align="center">	
													<col width="200" align="center">	
													<col width="250"  align="center">
													<col width="400"  align="center">
													<col width="110"  align="center">
													<col width="110"  align="center">
													<tr height="32">
														<td class="pClothBs " id="A3H0">&nbsp;</td>
														<td class="pClothBs " id="A3H1">Alias</td>
														<td class="pClothBs " id="A3H2">Description</td>
														<td class="pClothBs " id="A3H3">Account</td>
														<td class="pClothBs " id="A3H4">Eff From</td>
														<td class="pClothBs " id="A3H5">Eff To</td>
													</tr>
													</table>
													</div>
   												<div id="rightTbl" style="width:1118px; height:420px; display:block; overflow:scroll; margin:0px; padding:0px;" >
      											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1050px">
															<col width="30" align="center">	
															<col width="200" align="center">	
															<col width="250"  align="center">
															<col width="400"  align="center">
															<col width="110"  align="center">
															<col width="110"  align="center">
                              <% int i = 0; %>
               								<ezf:row ezfHyo="A">                 
															<tr height="25px" id="leftTBL_A_tr_${EZF_ROW_NUMBER}">
																<td class=""><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"2\""/></td>
																<td class=""><ezf:inputText name="adjAcctAliasNm_A" ezfName="adjAcctAliasNm_A" value="VISA USA,INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"adjAcctAliasNm_A#{EZF_ROW_NUMBER}\" size=\"27\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\" tabindex=\"2\""/></td>
																<td class=""><ezf:inputText name="adjAcctAliasDescTxt_A" ezfName="adjAcctAliasDescTxt_A" value="VISA USA,INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"adjAcctAliasDescTxt_A#{EZF_ROW_NUMBER}\" size=\"34\" maxlength=\"50\" tabindex=\"-1\" tabindex=\"2\""/></td>
																<td class=""><ezf:inputButton name="Click_Btn_Account" value="Account" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" otherAttr=" tabindex=\"2\""/>
                                             <ezf:inputText name="xxScrItem130Txt_A" ezfName="xxScrItem130Txt_A" value="VISA USA,INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem130Txt_A#{EZF_ROW_NUMBER}\" size=\"45\" tabindex=\"-1\""/>
                                </td>
																<td class="stab">
																	<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="10/10/09" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"2\""/>
																	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"  tabindex="2">
																</td>	
																<td class="stab">
																	<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="10/10/09" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"2\""/>
																	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"  tabindex="2">
																</td>	

															</tr>
                   				</ezf:row>
												</table>
											</div>
									</div>
								</tr>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
										<table border="0" cellpadding="0" cellspacing="0" height="30px">
											<col width="10">
											<col width="81">
											<col width="10">
											<col width="81">
											<tr>
												<td align="left">&nbsp;</td>
												<td align="left"><ezf:inputButton name="Click_Btn_InsertRow" value="Insert Row" htmlClass="pBtn7" otherAttr=" tabindex=\"3\""/></td>
												<td align="left"><ezf:inputButton name="Click_Btn_DeleteRow" value="Delete Row" htmlClass="pBtn7" otherAttr=" tabindex=\"3\""/></td>
											</tr>
                    </table>
                </td>
            </tr>
        </tbody>
    </table>
</center>
<script type="text/javascript" defer>
  S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  false, true);
</script>

<%-- **** Task specific area ends here **** --%>
