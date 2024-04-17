<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161122053721 --%>
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

<%@ page import="business.servlet.ZYPL0310.ZYPL0310Bean" %>
<%@ page import="business.servlet.ZYPL0310.ZYPL0310BMsg" %>
<%@ page import="business.servlet.ZYPL0310.ZYPL0310_ABMsg" %>
<%@ page import="business.servlet.ZYPL0310.constant.ZYPL0310Constant" %>
<% ZYPL0310BMsg bMsg = (ZYPL0310BMsg)databean.getEZDBMsg(); %>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZYPL0310Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Attachments">

<center>
	<table width="100%">
		<tr>
			<td>
				
<%-- +++++ Header : START ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				<table align="center" border="0">
					<tr>
						<td>
							<table>
								<col width="66">
								<col width="">
								<col width="10">
								<col width="66">
								<col width="">

								<tr valign="">
									<td class="stab">Function</td>
									<td><ezf:inputText name="bizAppNm" ezfName="bizAppNm" otherAttr=" size=\"55\""/></td>
									<td></td>
									<td class="stab">Primary Key</td>
									<td><ezf:inputText name="attDataKeyTxt" ezfName="attDataKeyTxt" otherAttr=" size=\"55\""/></td>
								</tr>
							</table>
<!--							<table>
								<col width="66">
								<col width="">
								
								<tr valign="">
									<td class="stab">Primary Key</td>
									<td><input type="text" size="75" name="attFileKeyTxt" ezfname="attFileKeyTxt" class="pPro"></td>
								</tr>
							</table>-->
							<table>
								<col width="60">
								<col width="66">
								<col width="">
								<col width="20">
								<col width="86">
								<col width="">
								<col width="20">
								<col width="90">
								<col width="56">
								
								<tr valign="">
									<td></td>
									<td class="stab">Data Type</td>
									<td>
										<ezf:select name="attDataTpCd" ezfName="attDataTpCd" ezfCodeName="attDataTpCd_OT" ezfDispName="xxAttDataTpNm_OT" otherAttr=" style=\"width:160px;\""/>
									</td>
									<td></td>
									<td class="stab">Document Type</td>
									<td>
										<ezf:select name="attDocTpCd" ezfName="attDocTpCd" ezfCodeName="attDocTpCd_OT" ezfDispName="xxAttDocTpNm_OT" otherAttr=" style=\"width:200px;\""/>
									</td>
									<td></td>
									<td class="stab">Therefore Doc ID</td>
									<td><ezf:inputText name="docMgtDocId" ezfName="docMgtDocId" otherAttr=" size=\"20\" maxlength=\"10\""/></td>
								</tr>
							</table>

							<table>
								<col width="60">
								<col width="66">
								<col width="">
								
								<tr valign="">
									<td></td>
									<td class="stab">File Path</td>
									<td><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"75\" id=\"filePath\""/></td>
								</tr>
							</table>
							
							<table>
								<col width="60">
								<col width="66">
								<col width="">
								
								<tr valign="">
									<td></td>
									<td class="stab">URL</td>
									<td><ezf:inputText name="othSysUrl" ezfName="othSysUrl" otherAttr=" size=\"75\" id=\"webUrl\""/></td>
								</tr>
							</table>

							<table>
								<col width="60">
								<col width="66">
								<col width="">
								<col width="">
								
								<tr valign="">
									<td></td>
									<td class="stab" valign="top">Description<br/>/Note</td>
									<td><ezf:textArea name="xxAttDataCmntTxt_I" ezfName="xxAttDataCmntTxt_I" otherAttr=" rows=\"3\" cols=\"73\""/></td>
									<td valign="bottom"><ezf:inputButton name="Upload" value="Upload" htmlClass="cBtn" /></td>
							</table>
						</td>
					</tr>
				</table>
<%-- +++++ Header : END   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				
				<hr>
				
<%-- +++++ Body : START ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
   					<col valign="top" align="right">
   					<col valign="top" align="left">
					<tr>
						<td>
				<%-- @Left Table Header --%>
				<table border="1" cellpadding="1" cellspacing="0" width="735">
					<col width="20"  align="center">
					<col width="30"  align="center">
					<col width="115"  align="center">
					<col width="150"  align="center">
					<col width="420" align="center">
					
					<tr>
						<td class="pClothBs">&nbsp;</td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxNum' )">No.<img id="sortIMG.xxNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'attDataTpCd_AI' )">Data Type<img id="sortIMG.attDataTpCd_AI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'attDocTpCd_AI' )">Document Type<img id="sortIMG.attDocTpCd_AI" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'othSysUrl_BK' )">Name<img id="sortIMG.othSysUrl_BK" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
					</tr>
				</table>
				
				<%-- @Left Table Body --%>
				<div style="overflow-y:hidden; height:242; overflow-x:hidden; width:735" id="LeftTBL" onScroll="synchroScrollTop(this.id, new Array( 'RightTBL' ));">
					<table border="1" cellpadding="1" cellspacing="0" id="A_LeftTBL">
						<col width="20"  align="center">
						<col width="36"  align="right">
						<col width="110"  align="left">
						<col width="150"  align="left">
						<col width="414" align="left">
						<% int tblIdx = 0; %>
						<% String tempTitle = ""; %>
						<tbody>
							<ezf:row ezfHyo="A">
								<tr>
									<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxNum" ezfName="xxNum" ezfHyo="A" ezfArrayNo="0" /></td>
									<td>
										<ezf:select name="attDataTpCd_AI" ezfName="attDataTpCd_AI" ezfHyo="A" ezfArrayNo="0" ezfCodeName="attDataTpCd_AO" ezfDispName="xxAttDataTpNm_AO" ezfCodeDispHyo="A" otherAttr=" style=\"width:110px;\""/>
									</td>
									<td>
										<ezf:select name="attDocTpCd_AI" ezfName="attDocTpCd_AI" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="attDocTpCd_AO" ezfDispName="xxAttDocTpNm_AO" ezfCodeDispHyo="A" otherAttr=" style=\"width:150px;\""/>
									</td>
									<% tempTitle = bMsg.A.no(tblIdx).attDataNm.getValue(); %>
									<td style="word-break: break-all;" title='<%=tempTitle%>'>
										<% if(bMsg.A.no(tblIdx).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_FILE) && bMsg.A.no(tblIdx).attDataNm != null && !bMsg.A.no(tblIdx).attDataNm.getValue().equals("")){ %>
											<ezf:anchor name="attDataNm" ezfName="attDataNm" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Download" otherAttr=" ezfanchortext=\"\" style=\"text-overflow:ellipsis; white-space:nowrap; overflow:hidden; width:394px;\"">
												<ezf:label name="attDataNm" ezfName="attDataNm" ezfHyo="A" ezfArrayNo="0" />
											</ezf:anchor>
										<% } else if(bMsg.A.no(tblIdx).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_URL) && bMsg.A.no(tblIdx).othSysUrl_AO != null && !bMsg.A.no(tblIdx).othSysUrl_AO.getValue().equals("")){ %>
											<% tempTitle = bMsg.A.no(tblIdx).othSysUrl_AO.getValue(); %>
											<a href="<%=bMsg.A.no(tblIdx).othSysUrl_AO.getValue() %>" target="_blank" title='<%=tempTitle%>' style="text-overflow:ellipsis; white-space:nowrap; overflow:hidden; width:394px;">
												<ezf:label name="othSysUrl_AO" ezfName="othSysUrl_AO" ezfHyo="A" ezfArrayNo="0" />
											</a>
										<% } else if(bMsg.A.no(tblIdx).attDataTpCd_AI.getValue().equals(ZYPL0310Constant.ATT_DATA_TYPE_CODE_THEREFORE) && bMsg.A.no(tblIdx).attDataNm != null && !bMsg.A.no(tblIdx).attDataNm.getValue().equals("")){ %>
											<%-- [START] -MOD- Change to use Therefore Desktop client viewer 2018.06.01 --%>
											<% if(EZDSystemEnv.getBoolean("S21.therefore.desktop.client.viewer.usage.flag",true)){ %>
											<ezf:anchor name="attDataNm" ezfName="attDataNm" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Download" otherAttr=" ezfanchortext=\"\" style=\"text-overflow:ellipsis; white-space:nowrap; overflow:hidden; width:394px;\"">
												<ezf:label name="attDataNm" ezfName="attDataNm" ezfHyo="A" ezfArrayNo="0" />
											</ezf:anchor>
											<% } else { %>
											<a href="<%=bMsg.A.no(tblIdx).othSysUrl_AO.getValue() %>" target="therefore" style="text-overflow:ellipsis; white-space:nowrap; overflow:hidden; width:394px;">
												<%= bMsg.A.no(tblIdx).attDataNm.getValue() %>
											</a>
											<% } %>
											<%-- [END] -MOD- Change to use Therefore Desktop client viewer 2018.06.01 --%>
										<% } %>
									</td>
									<% tblIdx = tblIdx+1; %>
								</tr>
							</ezf:row>
							<ezf:skip>
<!--								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>2</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_2.pdf</label></a></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>3</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_3.pdf</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>4</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_4.pdf</label></a></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>5</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_5.pdf</label></a></td>
								</tr>						
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>6</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_6.pdf</label></a></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>7</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_7.pdf</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>8</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_8.pdf</label></a></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>9</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_9.pdf</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>10</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_10.pdf</label></a></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>11</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_11.pdf</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>12</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_12.pdf</label></a></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>13</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_13.pdf</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor">
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>14</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_14.pdf</label></a></td>
								</tr>
								<tr>
									<td><input type="checkBox" value="Y"></td>
									<td><label ezfout>15</label></td>
									<td><a href="#"><label ezfout>1234567890abcdefzhijklmnopqrstuvwxyz_15.pdf</label></a></td>
								</tr>
-->							</ezf:skip>
						</tbody>
					</table>
				</div>

						</td>
						<td>

				<%-- @Right Table Header --%>
				<div style="overflow-y:hidden; height:; overflow-x:hidden; width:243" id="RightTBL_TOP" onScroll="synchroScrollLeft(this.id, new Array( 'RightTBL' ));">
					<table border="1" cellpadding="1" cellspacing="0" width="380">
						<col width="50"  align="center">
						<col width="164"  align="center">
						<col width="84"  align="center">
						<col width="64"  align="center">
						
						<tr>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'attDataDescTxt_FG' )">Desc<img id="sortIMG.attDataDescTxt_FG" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezInTime' )">Uploaded Date<img id="sortIMG.ezInTime" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezInUserID' )">Attached By<img id="sortIMG.ezInUserID" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
							<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'attDataVol' )">Size<img id="sortIMG.attDataVol" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
						</tr>
					</table>
				</div>
				
				<%-- @Right Table Body --%>
				<div style="overflow-y:scroll; height:259; overflow-x:scroll; width:260" id="RightTBL" onScroll="synchroScrollTop(this.id, new Array( 'LeftTBL' ));synchroScrollLeft(this.id, new Array( 'RightTBL_Top' ));">
					<table border="1" cellpadding="1" cellspacing="0" id="A_RightTBL" width="380">
						<col width="50"  align="center">
						<col width="164" align="center">
						<col width="84"  align="center">
						<col width="64"  align="center">
						
						<tbody>
							<ezf:row ezfHyo="A">
								<tr height="24">
									<td><ezf:anchor name="attDataDescTxt_FG" ezfName="attDataDescTxt_FG" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="ShowDescription" otherAttr=" ezfanchortext=\"\""><ezf:label name="attDataDescTxt_FG" ezfName="attDataDescTxt_FG" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
									<td><ezf:label name="ezInTime_DT" ezfName="ezInTime_DT" ezfHyo="A" ezfArrayNo="0" />&nbsp;<ezf:label name="ezInTime_TM" ezfName="ezInTime_TM" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="ezInUserID" ezfName="ezInUserID" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="attDataVol_O" ezfName="attDataVol_O" ezfHyo="A" ezfArrayNo="0" /><ezf:label name="xxFileVolUnit" ezfName="xxFileVolUnit" ezfHyo="A" ezfArrayNo="0" /></td>
								</tr>
							</ezf:row>
							<ezf:skip>
<!--								<tr class="pEvenNumberBGcolor" height="24">
									<td><label ezfout>04/02/2009</label>&nbsp;<label ezfout>02:02:02</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr height="24">
									<td><label ezfout>04/03/2009</label>&nbsp;<label ezfout>03:03:03</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor" height="24">
									<td><label ezfout>04/04/2009</label>&nbsp;<label ezfout>04:04:04</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr height="24">
									<td><label ezfout>04/05/2009</label>&nbsp;<label ezfout>05:05:05</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>						
								<tr class="pEvenNumberBGcolor" height="24">
									<td><label ezfout>04/06/2009</label>&nbsp;<label ezfout>06:06:06</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr height="24">
									<td><label ezfout>04/07/2009</label>&nbsp;<label ezfout>07:07:07</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor" height="24">
									<td><label ezfout>04/08/2009</label>&nbsp;<label ezfout>08:08:08</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr height="24">
									<td><label ezfout>04/09/2009</label>&nbsp;<label ezfout>09:09:09</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor" height="24">
									<td><label ezfout>04/10/2009</label>&nbsp;<label ezfout>10:10:10</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr height="24">
									<td><label ezfout>04/11/2009</label>&nbsp;<label ezfout>11:11:11</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor" height="24">
									<td><label ezfout>04/12/2009</label>&nbsp;<label ezfout>12:12:12</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr height="24">
									<td><label ezfout>04/13/2009</label>&nbsp;<label ezfout>13:13:13</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
								<tr class="pEvenNumberBGcolor" height="24">
									<td><label ezfout>04/14/2009</label>&nbsp;<label ezfout>14:14:14</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><label ezfout>&nbsp;</label></td>
								</tr>
								<tr height="24">
									<td><label ezfout>04/15/2009</label>&nbsp;<label ezfout>15:15:15</label></td>
									<td><label ezfout>Q99999</label></td>
									<td><label ezfout>1</label><label ezfout>MB</label></td>
									<td><a href="#"><label ezfout>Y</label></a></td>
								</tr>
-->							</ezf:skip>
						</tbody>
					</table>
				</div>
						</td>
					</tr>
				</table>
				
				<table border="0" height="40">
					<col width="">
					<col width="">
					<col width="736">
					<col width="">
					<tr valign="bottom">
						<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="cBtn" /></td>
						<td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="cBtn" /></td>
						<td>&nbsp;</td>
						<td><ezf:inputButton name="Delete" value="Delete" htmlClass="cBtn" /></td>
					</tr>
				</table>
<%-- +++++ Body : END   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				
				<hr>
				
<%-- +++++ Footer : START ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
				<table align="center">
					<col width="">
					<tr>
						<td><ezf:textArea name="xxAttDataCmntTxt_O" ezfName="xxAttDataCmntTxt_O" otherAttr=" rows=\"4\" cols=\"90\""/></td>
					</tr>
				</table>
<%-- +++++ Footer : END   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ --%>

			</td>
		</tr>
	</table>
</center>

<script type="text/javascript">
<!--
  function check(selectValue){
    var vl = selectValue.value;
    var frm = selectValue.form;
    fpEnableFlg = (vl=='FL') ? false:true;
    urlEnableFlg = (vl=='WP') ? false:true;
    frm.elements['filePath'].disabled = fpEnableFlg;
    frm.elements['webUrl'].disabled = urlEnableFlg;
  }
-->
</script>

<%-- **** Task specific area ends here **** --%>
