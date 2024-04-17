<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100127085254 --%>
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


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" >
<%--
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr align="left">
				<td colspan="5" height="24" class="pClothB">
					<b><font color="white"><ezf:label name="xxScrNm" ezfName="xxScrNm" />&nbsp;Lookup Screen</font></b>
				</td>
			</tr>
		</table>
--%>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<col width="30">
			<col width="130">
			<col width="125">
			<col width="30">
			<col width="130">
			<col width="280">
			<col width="*">
			<tr align="left">
				<td>&nbsp;</td>
				<td class="stab">Global Company Code</td>
				<td colspan="5"><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
			</tr>
			<tr align="left">
				<td>&nbsp;</td>
				<td class="stab">Mail Group ID(*)</td>
				<td><ezf:inputText name="mlGrpId" ezfName="mlGrpId" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"10\""/></td>
				<td>&nbsp;</td>
				<td class="stab">Mail Group Name(*)</td>
				<td><ezf:inputText name="mlGrpNm" ezfName="mlGrpNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\""/></td>
				<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
			</tr>
		</table>
		<hr>
		<%-- Pagenation --%>
		<table width="100%">
			<tr align="left">
				<td width="420">
				</td>
				<td>
                <%--
					<table border="0" cellpadding="1" cellspacing="0">
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
							<td class="pOut">40</td>
							<td class="stab">of</td>
							<td class="pOut">200</td>
							<td>&nbsp;</td>
							<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
							<td></td>
							<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
						</tr>
					</table>
                --%>
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
		<%-- List --%>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td valign="top">
					<%-- RIGHT-TABLE(TOP) --%>
					<div id="rightTopTBL" style="overflow-y:hidden; height:22; overflow-x:hidden; width:774px;">
						<table cellpadding="1" cellspacing="0" border="1" width="774">
							<col width="60">
							<col width="170">
							<col width="*">
							<tr style="text-align: center" height="20">
								<td class="pClothBs" nowrap>&nbsp;</th>
								<td class="pClothBs" nowrap>Mail Group ID</td>
								<td class="pClothBs" nowrap>Mail Group Name</td>
							</tr>
						</table>
					</div>
					<%-- RIGHT-TABLE(MID) --%>
					<div id="rightTBL" style="overflow-y:scroll; height:442; overflow-x:hidden; width:791px;" onscroll="synchroScroll_fromRightTblAction();">
						<table cellpadding="1" cellspacing="0" border="1" width="774" style="word-break:break-all;white-space:pre-wrap;">
							<col width="60">
							<col width="170">
							<col width="*">
							<tbody>
								<ezf:row ezfHyo="A">
								<tr style="text-align: left" height="40">
									<td align="center"><ezf:inputButton name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
									<td align="left"><ezf:label name="mlGrpId_A" ezfName="mlGrpId_A" ezfHyo="A" ezfArrayNo="0" /></td>
									<td align="left"><ezf:label name="mlGrpNm_A" ezfName="mlGrpNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
								</tr>
								</ezf:row>
								<ezf:skip>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								<tr style="text-align: left" height="40">
									<td align="center"><input type="button" name="Select" value="Select" class="pBtn3" onclick="sendServer(this)" ezfhyo="A"></td>
									<td align="left"><label ezfout name="mlGrpId_A" ezfname="mlGrpId_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2</label></td>
									<td align="left"><label ezfout name="mlGrpNm_A" ezfname="mlGrpNm_A" ezfhyo="A">XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6</label></td>
								</tr>
								</ezf:skip>
							</tbody>
						</table>
					</div>
				</td>
			<tr>
		</table>
<%--
		<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top:10px">
			<col width="660">
			<col width="60">
			<col width="*">
			<tr align="left">
				<td></td>
				<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
				<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
			</tr>
		</table>
--%>
</table>

<!-- Set Page ID  --> 
<input type="hidden" name="pageID"value="ZZML0080Scrn00"> 
<!-- Set Page Name --> 
<input type="hidden" name="pageName" value="Lookup Screen"> 

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
//		var leftTBL     = this.document.getElementById( 'leftTBL' );
		
		// synchronize scroll - Y
//		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}
</script>


<%-- **** Task specific area ends here **** --%>
