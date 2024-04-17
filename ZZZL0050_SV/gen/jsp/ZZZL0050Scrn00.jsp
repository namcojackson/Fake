<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20101110032332 --%>
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
			<input type="hidden" name="pageID" value="ZZZL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Online Process Configuration">
			
<center>
	<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER START ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<!--<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td width="1100px" height="28px" valign="bottom">
								<div>
									<table border="0" cellpadding="0" cellspacing="0">
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
												<tr title='Order Entry'>
													<td width="107px" align="center" class="same">
														Onl Proc View
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Hold Release'>
													<td width="90px" align="center" class="disabled">
														Onl Proc Rank
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_OFF">
												<tr title='Soft Allocation'>
													<td width="90px" align="center" class="disabled">
														Onl Proc Config
													</td>
													<td width="17px" align="center">
														<img src="./img/tab/multiscreendesabled.png" style="border-style:none;">
													</td>
												</tr>
											</table>
										</td>
										<td>
									</table>
								</div>
						</td>
						<td valign="bottom" align="center">
								<a href="#" id="PrevPageTabIndex"><img id="PrevPageBtn" src="./img/tab/tabbackbutton.png" alt="Prev" border="0"></a>
						</td>
						<td valign="bottom" align="center">
							<a href="#" id="NextPageTabIndex"><img id="NextPageBtn" src="./img/tab/tabnextbutton.png" alt="Next" border="0"></a>
						</td>
					</tr>
				</table>-->
				
				<div class="pTab_BODY">
				<BR><BR>
				<table width="800px" align="center" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<col width="900">
							<col width="60">
							<col width="100">
							<col width="100">
							<!-- <col width="100"> -->
							<td class="stab" align="right">
								<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD:</ezf:anchor>
							</td>
							<td align="right">
								<ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/>
							</td>
							<td class="stab" align="right">JVM:
							</td>
							<td align="right">
								<ezf:select name="jvmNm_S" ezfName="jvmNm_S" ezfCodeName="jvmNm_C" ezfDispName="jvmNm_D" otherAttr=" onchange=\"sendServer('View')\""/>
							</td>
							<!-- <td align="right">
								<ezf:inputButton name="View" value="View" htmlClass="cBtn" />
							</td> -->
						</tr>
					</tbody>
				</table>
				<hr width="90%">
				</div>
				<%-- ######################################## HEADER END ######################################## --%>
  				<BR>
  				<BR>
  				<table align="center" border="0" cellspacing="0" cellpadding="0" width="500">
  					<tr>
  						<col width="180">
						<col width="80">
						<col width="240">
  						<td class="stab" align="left">Number of current pooled queue: 
  						</td>
  						<td align="left">
  							<ezf:inputText name="curPldQueueNum" ezfName="curPldQueueNum" value="XXXXXXXXXX" otherAttr=" size=\"10\""/>
  						</td>
  						<td>
  						</td>
  					</tr>
  					<tr>
  						<td class="stab" align="left">Pooling interval time (sec):
  						</td>
  						 <td align="left">
  							<ezf:inputText name="plngIntvlScd" ezfName="plngIntvlScd" value="XXX" otherAttr=" size=\"3\""/>
  						</td>
  						<td class="stab" align="left">(Set interval time to check pooled queue)
  						</td>
  					</tr>
  					<tr>
  						<td class="stab" align="left">Number of start thread:
  						</td>
  						 <td align="left">
  							<ezf:inputText name="startThrdNum" ezfName="startThrdNum" value="XXX" otherAttr=" size=\"3\""/>
  						</td>
  						<td class="stab" align="left">(Set number of thread when WAS started)
  						</td>
  					</tr>
  					<tr>
  						<td class="stab" align="left">Max number of Queue:
  						</td>
  						 <td align="left">
  							<ezf:inputText name="maxQueueNum" ezfName="maxQueueNum" value="XXXXXXXXXX" otherAttr=" size=\"10\""/>
  						</td>
  						<td>
  						</td>
  					</tr>
  					<tr>
  						<td class="stab" align="left">Active flag:
  						</td>
  						 <td align="left">
							<ezf:select name="onlProcActvFlg_S" ezfName="onlProcActvFlg_S" ezfCodeName="onlProcActvFlg_C" ezfDispName="onlProcActvFlg_D" />
  						</td>
  						<td>
  						</td>
  					</tr>
  				</table>
				</div>
			</td>
		</tr>
	</table>
</center>			

<%-- **** Task specific area ends here **** --%>
