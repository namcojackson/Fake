<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100202034624 --%>
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
			<input type="hidden" name="pageID" value="ZZML0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail Configuration Console">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<!-- include S21BusinessProcessTAB -->
	<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
	<!--
	<div class="pTab_HEAD">
		<ul>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="96%">
						<div>
							<li title="Order Entry"				 class="pTab_ON" ><a href="#">Order Entry</a></li>
							<li title="Order Upload"			 class="pTab_OFF"><a href="#">Order Upload</a></li>
							<li title="Trial/Loan Request Entry" class="pTab_OFF"><a href="#">Trial/Loan Request Entry</a></li>
							<li title="Asset Management"		 class="pTab_OFF"><a href="#">Asset Management</a></li>
							<li title="Hard Allocation"			 class="pTab_OFF"><a href="#">Hard Allocation</a></li>
							<li title="Hold Release"			 class="pTab_OFF"><a href="#">Hold Release</a></li>
							<li title="Credit Order Release"	 class="pTab_OFF"><a href="#">Credit Order Release</a></li>
							<li title="TOP STOP Release" 		 class="pTab_OFF"><a href="#">TOP STOP Release</a></li>
							<li title="Export Lisence Entry"	 class="pTab_OFF"><a href="#">Export Lisence Entry</a></li>
							<li title="Disposition Order"		 class="pTab_OFF"><a href="#">Disposition Order</a></li>
						</div>
					</td>
					<td valign="bottom" align="center">
 						<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
					</td>
					<td valign="bottom" align="center">
						<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
					</td>
				</tr>
			</table>
		</ul>
	</div>
	-->
		<div class="pTab_BODY">
			<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
				<tr align="left">
					<col width="100">
					<col width="180" align="left">
					<col width="200" align="left">
					<col width="40">
					<col width="180">
					<col width="200">
					<!-- Global Company Code -->
					<td>&nbsp;</td>
					<td class="stab">
						<ezf:anchor name="" ezfName="glblCmpyCd_S" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company Code</ezf:anchor>
					</td>
					<td>
						<ezf:inputText name="glblCmpyCd_S" ezfName="glblCmpyCd_S" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
					</td>
					<td>&nbsp;</td>
					<td>
						<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
					</td>
				</tr>
				<tr>
					<td colspan="6"><hr></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="stab">SMTP Server Name</td>
					<td><ezf:inputText name="mlSmtpHostNm" ezfName="mlSmtpHostNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"40\" maxlength=\"100\""/></td>
					<td>&nbsp;</td>
					<td class="stab">SMTP Server Port</td>
					<td><ezf:inputText name="mlSmtpPortNum" ezfName="mlSmtpPortNum" value="XXXXX" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
				</tr>
				<tr align="left">
					<td>&nbsp;</td>
					<td class="stab">SMTP User Name</td>
					<td><ezf:inputText name="mlSmtpUsrNm" ezfName="mlSmtpUsrNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"20\" maxlength=\"100\""/></td>
					<td>&nbsp;</td>
					<td class="stab">SMTP User Password</td>
					<td><ezf:inputText name="mlSmtpPassTxt" ezfName="mlSmtpPassTxt" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
				</tr>
				<tr align="left">
					<td colspan="6" height="20">&nbsp;</td>
				</tr>
				<tr align="left">
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default From Address</td>
						<td><ezf:inputText name="mlDefFromNm" ezfName="mlDefFromNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"40\" maxlength=\"240\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default ReplyTo Address 1</td>
						<td><ezf:inputText name="mlDefRpyToNm_01" ezfName="mlDefRpyToNm_01" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"40\" maxlength=\"240\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default ReplyTo Address 2</td>
						<td><ezf:inputText name="mlDefRpyToNm_02" ezfName="mlDefRpyToNm_02" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"40\" maxlength=\"240\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default ReplyTo Address 3</td>
						<td><ezf:inputText name="mlDefRpyToNm_03" ezfName="mlDefRpyToNm_03" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"40\" maxlength=\"240\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default ReplyTo Address 4</td>
						<td><ezf:inputText name="mlDefRpyToNm_04" ezfName="mlDefRpyToNm_04" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"40\" maxlength=\"240\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default ReplyTo Address 5</td>
						<td><ezf:inputText name="mlDefRpyToNm_05" ezfName="mlDefRpyToNm_05" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"40\" maxlength=\"240\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default Charaset</td>
						<td><ezf:inputText name="mlCharSetNm" ezfName="mlCharSetNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default  Retry Count</td>
						<td><ezf:inputText name="mlRtryNum" ezfName="mlRtryNum" value="XX" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default Locale</td>
						<td>
							<ezf:select name="mlLocId" ezfName="mlLocId" ezfCodeName="mlLocId_L1" ezfDispName="langNm_L1" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default Send Max Count</td>
						<td><ezf:inputText name="mlSendMaxNum" ezfName="mlSendMaxNum" value="XXXXX" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default Date Format</td>
						<td><ezf:inputText name="mlDefDtFmtTxt" ezfName="mlDefDtFmtTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td class="stab">Default  Number Format</td>
						<td><ezf:inputText name="mlDefNumFmtTxt" ezfName="mlDefNumFmtTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
					</tr>
				</tr>
				<tr>
					<td colspan="5" height="20">&nbsp;</td>
				</tr>
				<tr align="left">
					<td>&nbsp;</td>
					<td class="stab">Mail Send Stop Flag</td>
					<td>
						<ezf:select name="mlSysStopFlg" ezfName="mlSysStopFlg" >
							<ezf:option value="N" >START</ezf:option>
							<ezf:option value="Y" >STOP</ezf:option>
						</ezf:select>
					</td>
				</tr>
			</table>
		</div>
	</table>
</center>

<ezf:inputHidden name="glblCmpyCd" ezfName="glblCmpyCd" />
<ezf:inputHidden name="mlSysConfigRecId" ezfName="mlSysConfigRecId" />
<ezf:inputHidden name="ezUpTime" ezfName="ezUpTime" />
<ezf:inputHidden name="ezUpTimeZone" ezfName="ezUpTimeZone" />

<%-- ###SCRIPT --%>
<script type="text/javascript">
  function synchroScroll_fromRightTblAction() {
    var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
    var rightTBL    = this.document.getElementById( 'rightTBL'     );
    var leftTBL     = this.document.getElementById( 'leftTBL' );

    // synchronize scroll - Y
    leftTBL.scrollTop = rightTBL.scrollTop;
    // synchronize scroll - X
    rightTopTBL.scrollLeft = rightTBL.scrollLeft;
  }

  function synchroScroll_fromLeftTblAction() {
    var leftTBL  = this.document.getElementById( 'leftTBL'  );
    var rightTBL = this.document.getElementById( 'rightTBL' );

    // synchronize scroll - Y
    rightTBL.scrollTop = leftTBL.scrollTop;
  }
</script>

<%-- **** Task specific area ends here **** --%>
