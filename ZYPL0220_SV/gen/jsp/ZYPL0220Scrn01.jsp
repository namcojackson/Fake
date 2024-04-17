<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20090528172656 --%>
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
			<input type="hidden" name="pageID" value="ZYPL0220Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Upload Restriction">
			
						<table width="970" align="center" border="0">
				<tr>
					<td>
						<table width="970" align="center" border="0">
							<col width="150" align="left">
							<col width="157" align="left">
							<col width="157" align="center">
							<col width="216" align="left">
							<col width="130" align="center">
							<col width="130" align="left">

							<!-- Target Table Name -->
							<tr>
								<td>Upload CSV ID:</td>	
								<td><ezf:inputText name="upldCsvId_1H" ezfName="upldCsvId_1H" otherAttr=" size=\"18\" maxlength=\"10\""/></td>	
								<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn11" /></td>
								<td><br></td>	
								<td><ezf:inputButton name="Top" value="Top" htmlClass="pBtn10" /></td>
								<td><ezf:inputButton name="Restriction" value="Restriction" htmlClass="pBtn10" /></td>
							</tr>		
									
						</table>
						
						<hr size="1" noshade>
						
						<table width="970" align="center" border="0">
							<col width="150" align="left">
							<col width="820" align="left">
							
							<tr>		
								<td>Upload Name:</td>	
								<td><ezf:inputText name="upldCsvNm_1H" ezfName="upldCsvNm_1H" otherAttr=" size=\"64\" maxlength=\"64\""/></td>	
							</tr>		
									
							<tr>		
								<td>File ID:</td>	
								<td><ezf:inputText name="upldCsvFileId_1H" ezfName="upldCsvFileId_1H" otherAttr=" size=\"30\" maxlength=\"11\""/></td>	
							</tr>		

							<tr>		
								<td>Table ID:</td>	
								<td><ezf:inputText name="upldCsvTempTblId_1H" ezfName="upldCsvTempTblId_1H" otherAttr=" size=\"30\" maxlength=\"30\""/></td>	
							</tr>		
									
							<tr>		

								<td>Loading Batch ID:</td>	
								<td><ezf:inputText name="ezReqBusinessID_1H" ezfName="ezReqBusinessID_1H" otherAttr=" size=\"30\" maxlength=\"9\""/></td>	
							</tr>		

						</table>
						
						<hr size="1" noshade>

						<table width="970" align="center" border="0">
							<col width="250" align="left">
							<col width="57" align="center">
							<col width="663" align="left">

							<!-- Target Table Name -->
							<tr height="30">
								<td>Restriction:</td>	
								<td><br></td>	
								<td><br></td>	
							</tr>		
									
						</table>

						<table border="0" cellpadding="1" cellspacing="0" width="970">
							<tr>
								<td width="485">
									<table border="1" cellpadding="1" cellspacing="0" width="430">
										<col width="27" align="center">
										<col width="400" align="center">

										<tr>
											<td class="pClothBs">No</td>
											<td class="pClothBs">Business Process Name</td>

										</tr>
									</table>
						
									<div style="height:282; overflow-y:scroll;width:448;">
										<table border="1" cellpadding="1" cellspacing="0" width="430" id="B">
											<col width="27" align="center">
											<col width="400" align="left">

											<tbody>
												<ezf:row ezfHyo="B">
													<tr  height="28">
														<td><ezf:label name="upldCsvHdrNum_1B" ezfName="upldCsvHdrNum_1B" ezfHyo="B" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="upldCsvRstProcNm" ezfName="upldCsvRstProcNm" value="123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"100\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>
													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>
													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>
													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="100" value="123456789012345678901234567890"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40"	 maxlength="100" value="123456789012345678901234567890"></td>
													</tr>
												</ezf:skip>

											</tbody>
										</table>
									</div>
						
								</td>

								<td width ="485">
									<table border="1" cellpadding="1" cellspacing="0" width="430">
										<col width="27" align="center">
										<col width="400" align="center">

										<tr>
											<td class="pClothBs">No</td>
											<td class="pClothBs">Business Application ID</td>

										</tr>
									</table>
						
									<div style="height:282; overflow-y:scroll;width:448;">
										<table border="1" cellpadding="1" cellspacing="0" width="430" id="C">
											<col width="27" align="center">
											<col width="400" align="left">

											<tbody>
												<ezf:row ezfHyo="C">
													<tr  height="28">
														<td><ezf:label name="upldCsvHdrNum_1C" ezfName="upldCsvHdrNum_1C" ezfHyo="C" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="upldCsvRstBizAppId" ezfName="upldCsvRstBizAppId" value="12345678" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"8\""/></td>
													</tr>
												</ezf:row>
												<ezf:skip>
													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>
													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>
													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>
													<tr height="28">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40" maxlength="8" value="12345678"></td>
													</tr>

													<tr height="28" class="pEvenNumberBGcolor">
														<td><label ezfout>123</label></td>
														<td><input type="text" size="40"	 maxlength="10" value="12345678"></td>
													</tr>
												</ezf:skip>

											</tbody>
										</table>
									</div>

								
								</td>

							</tr>
						</table>

						<table width="970" align="center" border="0">
							<tr height="40">		
								<td width="147"><br></td>
								<td width="670"><br></td>
								<td width="134" align="center"><br></td>	
							</tr>		
						</table>
					

					</td>
				</tr>
			</table>

<%-- **** Task specific area ends here **** --%>
