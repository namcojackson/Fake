<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161109144523 --%>
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
			<input type="hidden" name="pageID" value="ZZVL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Role List">
			

<br>
<table>
	<col width="63">
	<col width="5">
	<col width="147">
	<col width="32">
	<col width="2">
	<col width="423">
	<tr align="left">
		<td class="stub">Role list</td>
		<td></td>
		<td class="stub">[Global Company Code:</td>
		<td>
			<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="ADB" otherAttr=" size=\"4\" maxlength=\"4\""/>
		</td>
		<td class="stub">]</td>
		<td></td>
	</tr>
</table>
<hr>
<center>
	<table>
		<tr>
			<td>
				<table>
					<col width="70">
					<col width="160">
					<col width="5">
					<col width="80">
					<col width="160">
					<col width="5">
					<col width="72">
					<tr align="center">
						<td class="stub">Role Name:</td>
						<td>
							<ezf:inputText name="roleNm_1" ezfName="roleNm_1" otherAttr=" size=\"20\" maxlength=\"24\""/>
						</td>
						<td></td>
						<td class="stub">Description:</td>
						<td>
							<ezf:inputText name="roleDescTxt_1" ezfName="roleDescTxt_1" otherAttr=" size=\"20\" maxlength=\"100\""/>
						</td>
						<td></td>
						<td>
							<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
						</td>
					</tr>
				</table>
				<hr>
				<%-- Pagenation --%>
				<table width="100%">
					<tr align="right">
						<td>
							<!--<table border="0" cellpadding="1" cellspacing="0">
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
									<td class="pOut">10</td>
									<td class="stab">of</td>
									<td class="pOut">200</td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
									<td></td>
									<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
								</tr>
							</table>
							-->
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							</jsp:include>
						</td>
					</tr>
				</table>
				<center>
				<table border="1" cellpadding="1" cellspacing="0">
					<col width="80" align="center"> 
					<col width="100" align="center">
					<col width="492" align="center">
					<tr> 
						<td class="pClothBs">&nbsp;</td>
						<td class="pClothBs">Role Name</td>
						<td class="pClothBs">Description</td>
					</tr> 
				</table>
				
				<div style="overflow:auto; height:410; width=682">
					<table border="1" cellpadding="1" cellspacing="0" id="A"> 
						<col width="80" align="center"> 
						<col width="100" align="center"> 
						<col width="492" align="center"> 
						<tbody>
						<ezf:row ezfHyo="A">
						<tr id="id_row{EZF_ROW_NUMBER}">
							<td align="center">
							<ezf:inputButton name="SelectRoleNm" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn" />
							<ezf:inputHidden name="roleNm_2" ezfName="roleNm_2" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left"><ezf:label name="roleNm_2" ezfName="roleNm_2" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"><ezf:label name="roleDescTxt_2" ezfName="roleDescTxt_2" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
						</ezf:row>
						<ezf:skip>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="1" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="1" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="2" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="2" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="3" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="3" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="4" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="4" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="5" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="5" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="6" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="6" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="7" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="7" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="8" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="8" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="9" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="9" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="10" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="10" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="11" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="11" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="12" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="12" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="13" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="13" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="14" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="14" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="15" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="15" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="16" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="16" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="17" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="17" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="18" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="18" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="19" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="19" /></td>
						</tr>
						<tr>
							<td align="center"><ezf:inputButton name="SelectRoleNm" value="Select" htmlClass="pBtn3" /></td>
							<td align="left"><ezf:label name="roleNm" ezfName="roleNm" ezfHyo="A" ezfArrayNo="20" /></td>
							<td align="left"><ezf:label name="roleDescTxt" ezfName="roleDescTxt" ezfHyo="A" ezfArrayNo="20" /></td>
						</tr>
						</ezf:skip>
					</table>
				 </div>
				 </center>
<!--			
				<table border="1" cellpadding="1" cellspacing="0" width="80%">
					<col width="20">
					<col width="">
					<tr>
						<td class="pClothBs"></td>
						<td class="pClothBs">ROLE LIST</td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0000</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0001</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0002</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0003</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
					<tr>
						<td align="right"><input type="button" class="pBtn3" value="Select" name="SelectRole" onclick="sendServer(this)"></td>
						<td><label ezfout name="roleNm_01" ezfname="roleNm_01" ezfhyo="A">R0004</label></td>
					</tr>
				</table>


-->
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
