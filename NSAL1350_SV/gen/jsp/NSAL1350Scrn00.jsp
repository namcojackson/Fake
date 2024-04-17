<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180704113430 --%>
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
			<input type="hidden" name="pageID" value="NSAL1350Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Shell Configuration Selection">

<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table width="980" border="0" cellpadding="1" cellspacing="0">
					<tr height="30">
						&nbsp;
					</tr>
					<tr>
						<td class="stab" width="50">&nbsp;
						</td>
						<td class="stab" width="90">
							Item Classification
						</td>
						<td width="120">
							<ezf:select name="mdseItemTpCd" ezfName="mdseItemTpCd" ezfBlank="1" ezfCodeName="mdseItemTpCd_LC" ezfDispName="mdseItemTpNm_LD" otherAttr=" style=\"width:110px\""/>
						</td>

						<td class="stab" width="32">
							Model
						</td>
						<td width="120">
							<ezf:select name="mdlId" ezfName="mdlId" ezfBlank="1" ezfCodeName="mdlId_LC" ezfDispName="t_MdlNm_LD" otherAttr=" style=\"width:110px\""/>
						</td>

						<td class="stab" width="60">
							Configuration
						</td>
						<td width="080">
							<ezf:inputText name="dsOrdPosnNum" ezfName="dsOrdPosnNum" value="123456" otherAttr=" size=\"6\" maxlength=\"6\""/>
						</td>

						<td class="stab" width="60">
							<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CmnBigShellItem" >
							Item Code(*)
							</ezf:anchor>
						</td>
						<td width="150">
							<ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234567890" otherAttr=" size=\"16\" maxlength=\"16\""/>
						</td>

						<td>
							&nbsp;
						</td>

					<tr>
						<td class="stab" width="30">&nbsp;
						</td>
						<td class="stab" colspan="4">
							<label><ezf:inputRadio name="addAsryFlg" ezfName="addAsryFlg" value="N" otherAttr=" id=\"addAsryFlgN\""/>Add Configration</label>
							&nbsp;
							<label><ezf:inputRadio name="addAsryFlg" ezfName="addAsryFlg" value="Y" otherAttr=" id=\"addAsryFlgY\""/>Add Accessory</label>
						</td>

						<td class="stab">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>

						<td class="stab">
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>

						<td>
							<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
						</td>

					</tr>
				</table>

			</td>
		</tr>

		<tr>
			<td>
				<hr>
			</td>
		</tr>

		<tr>
<%-- ######################################## BODY ######################################## --%>
			<table width="900">
				<tr>
					<td>
					<table>
						<tr>
							<td>
								<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" />
							</td>
							<td>
								<ezf:inputButton name="UnSelectAll" value="Unselect All" htmlClass="pBtn6" />
							</td>
							<td width="800">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr align="left">
					<td>
					<div style="width:892px;">
					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="875">
						<col align="center" width="025">
						<col align="center" width="050">
						<col align="center" width="170">
						<col align="center" width="070">
						<col align="center" width="120">
						<col align="center" width="170">
						<col align="center" width="100">
						<col align="center" width="170">
						<tr height="32">
							<td class="pClothBs">&nbsp;</td>
							<td class="pClothBs">Config#</td>
							<td class="pClothBs">Model</td>
							<td class="pClothBs">Line#</td>
							<td class="pClothBs">Item Code</td>
							<td class="pClothBs">Description</td>
							<td class="pClothBs">Item Classification</td>
							<td class="pClothBs">Ship to</td>
						</tr>
					</table>
					</div>
					<div style="align:left; width:892px; height:402px; overflow-y:scroll;">
					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="875">
						<col align="center" width="025">
						<col align="center" width="050">
						<col align="center" width="170">
						<col align="center" width="070">
						<col align="center" width="120">
						<col align="center" width="170">
						<col align="center" width="100">
						<col align="center" width="170">
						<ezf:row ezfHyo="A">
							<tr height="25" id="id_row{EZF_ROW_NUMBER}">
								<td>
									<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"\""/>
								</td>
								<td>
									<ezf:inputText name="dsOrdPosnNum_A" ezfName="dsOrdPosnNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" style=\"border:none;background-color:transparent;padding:0px;\""/>
								</td>
								<td>
									<ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="IRC5050" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/>
								</td>
								<td>
									<ezf:inputText name="dplyLineNum_A" ezfName="dplyLineNum_A" value="1.1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/>
								</td>
								<td>
									<ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/>
								</td>
								<td>
									<ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"250\" style=\"border:none;background-color:transparent;padding:0px;\""/>
								</td>
								<td>
									<ezf:inputText name="mdseItemTpNm_A" ezfName="mdseItemTpNm_A" value="XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"40\" style=\"border:none;background-color:transparent;padding:0px;\""/>
								</td>
								<td>
									<ezf:inputText name="xxGenlFldAreaTxt_A" ezfName="xxGenlFldAreaTxt_A" value="XXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"1000\" style=\"border:none;background-color:transparent;padding:0px;\""/>
								</td>
							</tr>
						</ezf:row>
						<ezf:skip>
						</ezf:skip>
					</table>
					</div>
					</td>
				</tr>
			</table>
		</tr>

	</table>
</center>


<%-- **** Task specific area ends here **** --%>
