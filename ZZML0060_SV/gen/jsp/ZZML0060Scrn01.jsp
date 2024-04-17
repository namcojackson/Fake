<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100205065328 --%>
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
			<input type="hidden" name="pageID" value="ZZML0060Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail Group Configuration Detail">

<table align="center" border="0" cellpadding="0" cellspacing="0">
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
		<tr>
			<td>
				<%-- head --%>
				<%-- item --%>
				<table width="990" align="center" border="0" cellpadding="1" cellspacing="0">
					<tr align="left">
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="170">
								<col width="820">
								<tr align="left">
									<td class="stab">
										<c:choose>
											<c:when test="${glblCmpyCd_01_InputProtect}">Global Company CD</c:when>
											<c:otherwise>Global Company CD</c:otherwise>
										</c:choose>
									</td>
									<td>
										<ezf:inputText name="glblCmpyCd_01" ezfName="glblCmpyCd_01" value="XXXX" otherAttr=" size=\"4\" ezftoupper=\"\""/>
									</td>
								</tr>

								<tr align="left">
									<td class="stab">Mail Group ID</td>
									<td>
										<ezf:inputText name="mlGrpId_01" ezfName="mlGrpId_01" value="XXXXXXXXX1XXXXXXXXX2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
									</td>
								</tr>

								<tr align="left">
									<td class="stab">Mail Group Name</td>
									<td>
										<ezf:inputText name="mlGrpNm_01" ezfName="mlGrpNm_01" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"80\" maxlength=\"100\""/>
									</td>
								</tr>

								<tr align="left">
									<td class="stab">Mail Key Name First</td>
									<td>
										<ezf:inputText name="mlKeyFirstNm_01" ezfName="mlKeyFirstNm_01" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"50\" maxlength=\"100\""/>
									</td>
								</tr>

								<tr align="left">
									<td class="stab">Mail Key Name Second</td>
									<td>
										<ezf:inputText name="mlKeyScdNm_01" ezfName="mlKeyScdNm_01" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"50\" maxlength=\"100\""/>
									</td>
								</tr>

								<tr align="left">
									<td class="stab">Mail Key Name Third</td>
									<td>
										<ezf:inputText name="mlKeyThirdNm_01" ezfName="mlKeyThirdNm_01" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6XXXXXXXXX7XXXXXXXXX8XXXXXXXXX9XXXXXXXXX0" otherAttr=" size=\"50\" maxlength=\"100\""/>
									</td>
								</tr>

								<tr align="left">
									<td valign="top" class="stab">Mail Group Describe</td>
									<td>
										<ezf:textArea name="mlGrpDescTxt_01" ezfName="mlGrpDescTxt_01" otherAttr=" style=\"width:665;height:70px;\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<%-- foot --%>
			</td>
		</tr>
	</div>
</table>

<ezf:inputHidden name="ezUpTime_01" ezfName="ezUpTime_01" />
<ezf:inputHidden name="ezUpTimeZone_01" ezfName="ezUpTimeZone_01" />

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

    function synchroScroll_fromRightTblAction2() {
        var rightTopTBL = this.document.getElementById( 'rightTopTBL2' );
        var rightTBL    = this.document.getElementById( 'rightTBL2'     );
        var leftTBL     = this.document.getElementById( 'leftTBL2' );

        // synchronize scroll - Y
        leftTBL.scrollTop = rightTBL.scrollTop;
        // synchronize scroll - X
        rightTopTBL.scrollLeft = rightTBL.scrollLeft;
    }

    function synchroScroll_fromLeftTblAction2() {
        var leftTBL  = this.document.getElementById( 'leftTBL2'  );
        var rightTBL = this.document.getElementById( 'rightTBL2' );

        // synchronize scroll - Y
        rightTBL.scrollTop = leftTBL.scrollTop;
    }
</script>


<%-- **** Task specific area ends here **** --%>
