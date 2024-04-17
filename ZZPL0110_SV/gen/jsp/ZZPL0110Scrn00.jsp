<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20140624012259 --%>
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

		<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<%-- include S21BusinessProcessTAB --%>
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

					<div class="pTab_BODY">
						<table>
							<col width="50">
							<col width="106">
							<col width="91">
							<col width="5">
							<col width="97">
							<col>
							<col width="5">
							<col width="82">
							<col width="101">
							<col width="5">
							<col>
							<col>
							<col>

							<tr>
								<td/>
								<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company Code</ezf:anchor></td>
								<td class><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="ABR" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
								<td/>
								<td class="stab">Report Process Key</td>
								<td><ezf:inputText name="eipRptProcLogPk" ezfName="eipRptProcLogPk" otherAttr=" size=\"28\" maxlength=\"28\""/></td>
								<td/>
								<td class="stab">Subsystem Code</td>
								<td><ezf:inputText name="subSysCd" ezfName="subSysCd" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
								<td/>
								<td class="stab">Report Job ID</td>
								<td><ezf:inputText name="rptJobId" ezfName="rptJobId" otherAttr=" size=\"11\" maxlength=\"18\""/></td>
								<td/>
							</tr>
						</table>

						<table>
							<col width="50">
							<col width="106">
							<col width="91">
							<col width="5">
							<col width="97">
							<col width="203">
							<col width="5">
							<col width="82">
							<col width="">
							<col width="5">
							<col width="64">
							<col width="85">
							<col>

							<tr>
								<td/>
								<td class="stab">Status</td>
								<td>
									<ezf:select name="rptJobStsTxt" ezfName="rptJobStsTxt" otherAttr=" style=\"width:91px;\"">
										<ezf:option value="ALL" >ALL</ezf:option>
										<ezf:option value="WAIT" >WAIT</ezf:option>
										<ezf:option value="RUNNING" >RUNNING</ezf:option>
										<ezf:option value="SUCCESS" >SUCCESS</ezf:option>
										<ezf:option value="FAIL" >FAIL</ezf:option>
										<ezf:option value="CANCELED" >CANCELED</ezf:option>
										<ezf:option value="SUSPENDED" >SUSPENDED</ezf:option>
									</ezf:select>
								</td>
								<td/>
								<td class="stab">Start Date</td>
								<td>
									<ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="08/12/2013" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
								</td>
								<td/>
								<td class="stab">End Date</td>
								<td>
									<ezf:inputText name="xxToDt" ezfName="xxToDt" value="08/12/2013" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt', 4);" >
								</td>
								<td/>
								<td/>
								<td/>
								<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
							</tr>
						</table>

						<hr noshade>

						<%-- Pagenation --%>
						<table width="1068">
							<tr align="right">
								<td>
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

						<%-- Detail --%>
						<table cellspacing="0" cellpadding="1" border="1" width="1098" align="center">
							<col width="24">
							<col width="204">
							<col width="84">
							<col width="126">
							<col width="84">
							<col width="96">
							<col width="120">
							<col width="120">
							<col width="84">
							<col width="96">
							
							<tr align="center">
								<td class="pClothBs">No</td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'eipRptProcLogPk_A')">Report Process Key<img id="sortIMG.eipRptProcLogPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'subSysCd_A')">Subsystem Code<img id="sortIMG.subSysCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rptJobId_A')">Report Job ID<img id="sortIMG.rptJobId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'rptJobStsTxt_A')">Report Job Status<img id="sortIMG.rptJobStsTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'eipErrCd_A')">EIP Error Code<img id="sortIMG.eipErrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_FR')">Job Start Time<img id="sortIMG.xxDtTm_FR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxDtTm_TO')">Job End Time<img id="sortIMG.xxDtTm_TO" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxRptProcJobAot_A')">Processed Time(min)<img id="sortIMG.xxRptProcJobAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
								<td class="pClothBs">Change Status</td>
							</tr>
						</table>

						<div style="OVERFLOW-Y:scroll; OVERFLOW-X:none; width:1133; height:415;">
							<table cellspacing="0" cellpadding="1" border="1" width="1098" id="A" align="right">
							<col width="24" align="right">
							<col width="204" align="right">
							<col width="84" align="center">
							<col width="126">
							<col width="84">
							<col width="96">
							<col width="120">
							<col width="120">
							<col width="84" align="right">
							<col width="96" align="center">

								<tbody>
									<ezf:row ezfHyo="A">
										<tr>
											<td><ezf:label name="xxRowNum_A" ezfName="xxRowNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="eipRptProcLogPk_A" ezfName="eipRptProcLogPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="subSysCd_A" ezfName="subSysCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="rptJobId_A" ezfName="rptJobId_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="rptJobStsTxt_A" ezfName="rptJobStsTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="eipErrCd_A" ezfName="eipErrCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxDtTm_FR" ezfName="xxDtTm_FR" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxDtTm_TO" ezfName="xxDtTm_TO" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxRptProcJobAot_A" ezfName="xxRptProcJobAot_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td>
												<ezf:select name="rptJobStsTxt_CS" ezfName="rptJobStsTxt_CS" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"width:96;\"">
													<ezf:option value="" ></ezf:option>
													<ezf:option value="CANCELED" >CANCELED</ezf:option>
													<ezf:option value="SUSPENDED" >SUSPENDED</ezf:option>
													<ezf:option value="FAIL" >FAIL</ezf:option>
												</ezf:select>
											</td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr>
											<td><label>2</label></td>
											<td><label>15243262</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:32</label></td>
											<td><label>2013/07/01 11:02</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>3</label></td>
											<td><label>15243263</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:33</label></td>
											<td><label>2013/07/01 11:03</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>4</label></td>
											<td><label>15243264</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>FAIL</label></td>
											<td><label>C200801000002</label></td>
											<td><label>2013/07/01 10:34</label></td>
											<td><label>2013/07/01 11:04</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>5</label></td>
											<td><label>15243265</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td/></td>
											<td><label>2013/07/01 10:35</label></td>
											<td><label>2013/07/01 11:05</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>6</label></td>
											<td><label>15243266</label></td>
											<td><label>AWC</label></td>
											<td><label>ATAJ7160020</label></td>
											<td><label>SUCCESS</label></td>
											<td/></td>
											<td><label>2013/07/01 10:36</label></td>
											<td><label>2013/07/01 11:06</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>7</label></td>
											<td><label>15243267</label></td>
											<td><label>AWC</label></td>
											<td><label>ATAJ7160020</label></td>
											<td><label>SUCCESS</label></td>
											<td/></td>
											<td><label>2013/07/01 10:37</label></td>
											<td><label>2013/07/01 11:07</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>8</label></td>
											<td><label>15243268</label></td>
											<td><label>AWC</label></td>
											<td><label>ATAJ7160020</label></td>
											<td><label>SUCCESS</label></td>
											<td/></td>
											<td><label>2013/07/01 10:38</label></td>
											<td><label>2013/07/01 11:08</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>9</label></td>
											<td><label>15243269</label></td>
											<td><label>AWC</label></td>
											<td><label>ATAJ7160020</label></td>
											<td><label>SUCCESS</label></td>
											<td/></td>
											<td><label>2013/07/01 10:39</label></td>
											<td><label>2013/07/01 11:09</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>10</label></td>
											<td><label>15243270</label></td>
											<td><label>AWC</label></td>
											<td><label>ATAJ7160020</label></td>
											<td><label>SUCCESS</label></td>
											<td/></td>
											<td><label>2013/07/01 10:40</label></td>
											<td><label>2013/07/01 11:10</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>11</label></td>
											<td><label>15243271</label></td>
											<td><label>AWC</label></td>
											<td><label>ATAJ7160020</label></td>
											<td><label>SUCCESS</label></td>
											<td/></td>
											<td><label>2013/07/01 10:41</label></td>
											<td><label>2013/07/01 11:11</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>12</label></td>
											<td><label>15243272</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:42</label></td>
											<td><label>2013/07/01 11:12</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>13</label></td>
											<td><label>15243273</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:43</label></td>
											<td><label>2013/07/01 11:13</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>14</label></td>
											<td><label>15243274</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:44</label></td>
											<td><label>2013/07/01 11:14</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>15</label></td>
											<td><label>15243275</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:45</label></td>
											<td><label>2013/07/01 11:15</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>16</label></td>
											<td><label>15243276</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:46</label></td>
											<td><label>2013/07/01 11:16</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>17</label></td>
											<td><label>15243277</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:47</label></td>
											<td><label>2013/07/01 11:17</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>18</label></td>
											<td><label>15243278</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:48</label></td>
											<td><label>2013/07/01 11:18</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>19</label></td>
											<td><label>15243279</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:49</label></td>
											<td><label>2013/07/01 11:19</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
										<tr>
											<td><label>20</label></td>
											<td><label>15243280</label></td>
											<td><label>AWC</label></td>
											<td><label>AWBJ0020010</label></td>
											<td><label>SUCCESS</label></td>
											<td></td>
											<td><label>2013/07/01 10:50</label></td>
											<td><label>2013/07/01 11:20</label></td>
											<td><label>30.0</label></td>
											<td>
												<select style="width:96;">
													<option value="" selected></option>
													<option value="CANCELED">CANCELED</option>
													<option value="FAIL">FAIL</option>
												</select>
											</td>
										</tr>
									</ezf:skip>
								</tbody>
							</table>
						</div>

					</div>
				</td>
			</tr>
		</table>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZPL0110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Report Process Log Viewer">

<%-- **** Task specific area ends here **** --%>
