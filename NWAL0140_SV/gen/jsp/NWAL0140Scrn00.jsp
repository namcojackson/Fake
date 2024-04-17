<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170317140002 --%>
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
			<input type="hidden" name="pageID" value="NWAL0140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Ship To Change Pop Up">
			
<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="0" align="center">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="24">
								<col width="29">
								<col width="54">
								<col width="5">
								<col width="55">
								<col width="8">
								
								<tr>
									<td class="stab">Line Number</td>
									<td class="pOut"><ezf:label name="xxDtlLineNum" ezfName="xxDtlLineNum" /></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Ship_To_Edit" value="Edit" htmlClass="pBtn4" /></td>
									<td>&nbsp;</td>
									<td class="stab">Drop Ship</td>
									<td class="pOut"><ezf:label name="dropShipFlg" ezfName="dropShipFlg" /></td>
								</tr>
							</table>

							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="60">
								<tr>
									<td class="stab">Ship To</td>
									<td class="pOut"><ezf:label name="shipToCustCd" ezfName="shipToCustCd" /></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="71">
								<tr>
									<td class="stab">Loc Nm</td>
									<td><ezf:inputText name="locNm" ezfName="locNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"60\" maxlength=\"35\" ezftoupper=\"\""/></td>
								</tr>
							</table>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="71">
								<tr>
									<td class="stab">Add Loc Nm</td>
									<td><ezf:inputText name="addlLocNm" ezfName="addlLocNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"60\" maxlength=\"35\" ezftoupper=\"\""/></td>
								</tr>
							</table>
<!-- QC#16375 del Start -->
<!-- 							<table border="0" cellpadding="1" cellspacing="0"> -->
<!-- 								<col width="80"> -->
<!-- 								<col width="488"> -->
								
<!-- 								<tr> -->
								<!-- **** Mod Start 2013/11/19 WDS Defect#2852 **** -->
								<!-- 	<td class="stab">Ship To Txt1 </td> -->
<!-- 									<td class="stab">Ship To Txt1</td> -->
								<!-- **** Mod End 2013/11/19 WDS Defect#2852 **** -->
<!-- 									<td><input type="text" size="60" maxlength="60"  -->
<!-- 										value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" name="firstRefCmntTxt" ezfname="firstRefCmntTxt" ezftoupper> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
								<!-- **** Mod Start 2013/11/19 WDS Defect#2852 **** -->
								<!-- 	<td class="stab">Ship To Txt2 </td> -->
<!-- 									<td class="stab">Ship To Txt2</td> -->
								<!-- **** Mod End 2013/11/19 WDS Defect#2852 **** -->
<!-- 									<td><input type="text" size="60" maxlength="60"  -->
<!-- 										value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" name="scdRefCmntTxt" ezfname="scdRefCmntTxt" ezftoupper> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 							</table> -->
<!-- QC#16375 del End -->
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="488">
								
								<tr>
									<td class="stab">Addr Ln1</td>
									<td><ezf:inputText name="firstLineAddr" ezfName="firstLineAddr" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXX" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Addr Ln2</td>
									<td><ezf:inputText name="scdLineAddr" ezfName="scdLineAddr" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXX" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Addr Ln3</td>
									<td><ezf:inputText name="thirdLineAddr" ezfName="thirdLineAddr" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXX" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Addr Ln4</td>
									<td><ezf:inputText name="frthLineAddr" ezfName="frthLineAddr" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXX" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/>
									</td>
								</tr>
							</table>
							
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								
								<!-- Post Code -->
								<tr>
									<td class="stab"><ezf:anchor name="postCd" ezfName="postCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Post" >Post Code</ezf:anchor></td>
									<td><ezf:inputText name="postCd" ezfName="postCd" value="XXXXXXXXX1XXXXX" otherAttr=" size=\"15\" maxlength=\"10\" ezftoupper=\"\""/><ezf:inputButton name="GetAddress" value="Get" htmlClass="pBtn4" otherAttr=" style=\"margin-left:5px;\""/></td>
								</tr>
								
								<!-- City -->
								<tr>
									<td class="stab"><ezf:anchor name="ctyAddr" ezfName="ctyAddr" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
									<td><ezf:inputText name="ctyAddr" ezfName="ctyAddr" value="XXXXXXXXX1XXXXXXXXX2XXXXX" otherAttr=" size=\"25\" maxlength=\"20\" ezftoupper=\"\""/></td>
								</tr>
								
								<!-- County -->
								<tr>
									<td class="stab"><ezf:anchor name="cntyNm" ezfName="cntyNm" ezfEmulateBtn="1" ezfGuard="OpenWin_Cnty" >County</ezf:anchor></td>
									<td><ezf:inputText name="cntyNm" ezfName="cntyNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
								</tr>
							</table>
								
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<tr>
									<!-- State -->
									<td class="stab"><ezf:anchor name="stCd_H1" ezfName="stCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToState" >State</ezf:anchor></td>
									<td><ezf:inputText name="stCd" ezfName="stCd" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
									
									<td width="15"></td>
									
									<!-- Province -->
									<td class="stab">Province</td>
									<td><ezf:inputText name="provNm" ezfName="provNm" value="XX" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
								</tr>
							</table>
							
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="80">
								<col width="24">
								<col width="90">
								<tr>
									<td class="stab"><ezf:anchor name="ctryCd_H1" ezfName="ctryCd_H1" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCountry" >Country</ezf:anchor></td>
									<td><ezf:inputText name="ctryCd" ezfName="ctryCd" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
									<td class="pOut"><ezf:label name="ctryNm" ezfName="ctryNm" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
