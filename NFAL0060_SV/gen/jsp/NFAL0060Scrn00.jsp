<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160325004022 --%>
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
			<input type="hidden" name="pageID" value="NFAL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Automatic Journal Entry Indicator Type Maintenance">
                        <!-- Upper Tab Start -->
                        <!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<!-- Application Parts Start -->
			<div class="pTab_BODY">
			<table width="96%" height="94%" align="center" border="0" cellspacing="0">
				<tr valign="top">
					<td>
						<br>
						<table width="1090" align="center" border="0" cellspacing="0">
								<col width="120">
								<col width="55">
								<col width="297">
								<col width="100">
								<col width="100">
								<col width="297">
							<tr height="33px">
								<td align="left" colspan="3" class="stab">
									<ezf:inputButton name="SearchAllBtn" value="Search All" htmlClass="pBtn5" otherAttr=" style=\"width:120px;height:22px\""/>
								</td>
							</tr>
							<tr height="33px">
								<td align="left" class="stab">Indicator Type</td>
								<td align="left" class="stab">
									<ezf:select name="ajePtrnIndTpCd_3" ezfName="ajePtrnIndTpCd_3" ezfBlank="1" ezfCodeName="ajePtrnIndTpCd_1" ezfDispName="ajePtrnIndTpCd_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_PTRN_IND_TP_CD')\"" otherAttr=" style=\"width:52px;\""/>
								</td>
								<td align="left"class="stab">
									<ezf:inputText name="ajePtrnIndTpNm" ezfName="ajePtrnIndTpNm" value="A/R RECEIPT TRANSACTION TYPE" otherAttr=" style=\"height:22px;width:270px;font-size:9pt;\""/>
								</td>
								<td align="left" class="stab">Actual Code</td>
								<td align="left" class="stab">
									<ezf:select name="ajePtrnActlCd_3" ezfName="ajePtrnActlCd_3" ezfBlank="1" ezfCodeName="ajePtrnActlCd_1" ezfDispName="ajePtrnActlCd_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_PTRN_ACTL_CD')\"" otherAttr=" style=\"width:70px;\""/>
								</td>
								<td align="left"class="stab">
									<ezf:inputText name="ajePtrnActlNm" ezfName="ajePtrnActlNm" value="MISSNG/INCOMPLET DOCUMENTATION" otherAttr=" style=\"height:20px;width:270px;font-size:9pt;\""/>
								</td>
							</tr>
						</table>
						<table width="1090" align="center" border="0" cellspacing="0">
							<tr height="5px">
								<td><hr></hr></td>
							</tr>
						</table>
						<table width="1090" align="center" border="0" cellspacing="0">
								<col width="112">
								<col width="285">
								<col width="282">
								<col width="300">
							<tr height="28px">
								<td align="left" class="stab">Indicator Type</td>
								<td align="left" class="stab">
									<ezf:inputText name="ajePtrnIndTpCd_NW" ezfName="ajePtrnIndTpCd_NW" value="004" otherAttr=" style=\"height:22px;width:70px;font-size:9pt;\" ezftoupper=\"\""/>
								</td>
								<td align="left" class="stab">Indicator Type Desc</td>
								<td align="left" class="stab">
									<ezf:inputText name="ajePtrnIndTpNm_NW" ezfName="ajePtrnIndTpNm_NW" value="A/R RECEIPT TRANSACTION TYPE" otherAttr=" style=\"height:22px;width:280px;font-size:9pt;\" ezftoupper=\"\""/>
								</td>
							</tr>
							<tr height="28px">
								<td align="left" class="stab">Actual Code Table</td>
								<td align="left" class="stab">
									<ezf:select name="ajeCdTblListCd_3" ezfName="ajeCdTblListCd_3" ezfBlank="1" ezfCodeName="ajeCdTblListCd_1" ezfDispName="ajeCdTblListNm_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_CD_TBL_LIST_CD')\"" otherAttr=" style=\"width:273px;\""/>
								</td>
								<td align="left" class="stab">Interface Column Name</td>
								<td align="left" class="stab">
									<ezf:inputText name="ajeIntfcColTxt_IN" ezfName="ajeIntfcColTxt_IN" value="XXXX" otherAttr=" style=\"height:22px;width:280px;font-size:9pt;\" ezftoupper=\"\""/>
								</td>
							</tr>
						</table>
						<table width="1090" border="0" cellspacing="0">
							<tr height="50px">
								<td align="right" class="stab">
									<ezf:inputButton name="AddRow" value="Add Row" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
								</td>
							</tr>
						</table>
						
						<table width="1090" border="1" cellspacing="0">
							<col width="35">
							<col width="90">
							<col width="300">
							<col width="90">
							<col width="300">
							<col width="">
							
							<tr>
								<td align="center" class="pClothBs">&nbsp;</td>
								<td align="center" class="pClothBs">Indicator Type</td>
								<td align="center" class="pClothBs">Indicator Type Description</td>
								<td align="center" class="pClothBs">Actual Code</td>
								<td align="center" class="pClothBs">Actual Code Description</td>
								<td align="center" class="pClothBs">Interface Column</td>
							</tr>
						</table>
						<div id="rightTBL" style="overflow-y:scroll; height:300; overflow-x:hidden; width:1107;" onscroll="synchroRightScroll();">
						<table width="1090" border="1" cellspacing="0" id="A">
							<col width="35">
							<col width="90">
							<col width="300">
							<col width="90">
							<col width="300">
							<col width="">
							<tbody>
								<ezf:row ezfHyo="A">
									<tr height="20px">
										<td class="stab" align="center">
											<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_XX_CHK_BOX_A')" />
										</td>
										<td align="left" class="stab"><ezf:label name="ajePtrnIndTpCd_A" ezfName="ajePtrnIndTpCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="center" class="stab">
											<ezf:inputText name="ajePtrnIndTpNm_A" ezfName="ajePtrnIndTpNm_A" value="AFFILIATE HAVING AJE PTRN" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" ezftoupper=\"\""/>
										</td>
										<td align="left" class="stab"><ezf:label name="ajePtrnActlCd_A" ezfName="ajePtrnActlCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="center" class="stab">
											<ezf:inputText name="ajePtrnActlNm_A" ezfName="ajePtrnActlNm_A" value="BRANCH OR INTERNAL TRANSFER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" ezftoupper=\"\""/>
										</td>
										<td align="center" class="stab">
											<ezf:inputText name="ajeIntfcColTxt_A" ezfName="ajeIntfcColTxt_A" value="TRIAL_MACH_IND_TP_CD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" ezftoupper=\"\""/>
										</td>
									</tr>
								</ezf:row>
								<ezf:skip>
								</ezf:skip>
							</tbody>
						</table>
						</div>
						<table width="1090" border="0" cellspacing="0">
							<tr height="5px"><td></td></tr>
							<tr height="20px">
								<td align="right">
									<ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>

<%-- **** Task specific area ends here **** --%>
