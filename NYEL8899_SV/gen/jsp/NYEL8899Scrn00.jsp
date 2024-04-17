<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160516135139 --%>
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
			<input type="hidden" name="pageID" value="NYEL8899Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Workflow Test Driver">

			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<!-- ######################################## HEADER ######################################## -->
					<tr>
						<td>
							<div class="pTab_BODY">
								<table border="0" cellpadding="3" cellspacing="1" >
									<col width="1180">
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Token Object</legend>
												Factory Class &nbsp; <ezf:inputText name="wfClsNm" ezfName="wfClsNm" otherAttr=" maxlength=\"128\" style=\"width: 400px;\""/>&nbsp;&nbsp; <br />
												Test Pattern &nbsp;&nbsp; <ezf:inputText name="wfTestPtrnNm" ezfName="wfTestPtrnNm" otherAttr=" maxlength=\"64\" style=\"width: 400px;\""/>&nbsp;&nbsp;
												<ezf:inputButton name="Init" value="Init" htmlClass="pBtn6" />
											</fieldset>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Workflow</legend>
												Process Name &nbsp;&nbsp; <ezf:inputText name="wfProcNm" ezfName="wfProcNm" otherAttr=" maxlength=\"40\" style=\"width: 400px;\""/>&nbsp;&nbsp; <br />
												Document Id &nbsp;&nbsp;&nbsp; <ezf:inputText name="wfProcDocId" ezfName="wfProcDocId" otherAttr=" maxlength=\"40\" style=\"width: 400px;\""/>&nbsp;&nbsp; <br />
												Data Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfDataNm" ezfName="wfDataNm" otherAttr=" maxlength=\"64\" style=\"width: 400px;\""/>&nbsp;&nbsp;
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
											</fieldset>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Attribute</legend>
												Attr01&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbTxt_01" ezfName="wfBizAttrbTxt_01" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr02&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbTxt_02" ezfName="wfBizAttrbTxt_02" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr03&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbTxt_03" ezfName="wfBizAttrbTxt_03" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr04&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbTxt_04" ezfName="wfBizAttrbTxt_04" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr05&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbTxt_05" ezfName="wfBizAttrbTxt_05" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
											</fieldset>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Attribute Label</legend>
												Attr01&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbLbTxt_01" ezfName="wfBizAttrbLbTxt_01" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr02&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbLbTxt_02" ezfName="wfBizAttrbLbTxt_02" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr03&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbLbTxt_03" ezfName="wfBizAttrbLbTxt_03" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr04&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbLbTxt_04" ezfName="wfBizAttrbLbTxt_04" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												Attr05&nbsp;&nbsp;&nbsp; <ezf:inputText name="wfBizAttrbLbTxt_05" ezfName="wfBizAttrbLbTxt_05" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
											</fieldset>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Condition String</legend>
												CondStr01 <ezf:inputText name="condValTxt_01" ezfName="condValTxt_01" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr02 <ezf:inputText name="condValTxt_02" ezfName="condValTxt_02" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr03 <ezf:inputText name="condValTxt_03" ezfName="condValTxt_03" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr04 <ezf:inputText name="condValTxt_04" ezfName="condValTxt_04" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr05 <ezf:inputText name="condValTxt_05" ezfName="condValTxt_05" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr06 <ezf:inputText name="condValTxt_06" ezfName="condValTxt_06" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr07 <ezf:inputText name="condValTxt_07" ezfName="condValTxt_07" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr08 <ezf:inputText name="condValTxt_08" ezfName="condValTxt_08" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr09 <ezf:inputText name="condValTxt_09" ezfName="condValTxt_09" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
												CondStr10 <ezf:inputText name="condValTxt_10" ezfName="condValTxt_10" otherAttr=" maxlength=\"32\" style=\"width: 120px;\""/>&nbsp;&nbsp;
											</fieldset>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Condition Number</legend>
												CondNum01 <ezf:inputText name="condValNum_01" ezfName="condValNum_01" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum02 <ezf:inputText name="condValNum_02" ezfName="condValNum_02" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum03 <ezf:inputText name="condValNum_03" ezfName="condValNum_03" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum04 <ezf:inputText name="condValNum_04" ezfName="condValNum_04" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum05 <ezf:inputText name="condValNum_05" ezfName="condValNum_05" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum06 <ezf:inputText name="condValNum_06" ezfName="condValNum_06" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum07 <ezf:inputText name="condValNum_07" ezfName="condValNum_07" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum08 <ezf:inputText name="condValNum_08" ezfName="condValNum_08" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum09 <ezf:inputText name="condValNum_09" ezfName="condValNum_09" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
												CondNum10 <ezf:inputText name="condValNum_10" ezfName="condValNum_10" otherAttr=" maxlength=\"32\" style=\"width: 120px;text-align: right;\""/>&nbsp;&nbsp;
											</fieldset>
										</td>
									</tr>
									<!-- ######################################## Comment ######################################## -->
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Comment</legend>
												<ezf:textArea name="xxWfAsgCmntTxt" ezfName="xxWfAsgCmntTxt" otherAttr=" rows=\"2\" cols=\"130\""/>
											</fieldset>
										</td>
									</tr>
									<tr>
										<td>
											<fieldset style="left 3px; width: 1060px;">
												<legend>Init Parameter</legend>
												 	<ezf:label name="xxFilePathTxt" ezfName="xxFilePathTxt" />
											</fieldset>
											
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
