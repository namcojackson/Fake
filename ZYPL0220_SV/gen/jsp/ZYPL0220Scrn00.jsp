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
			<input type="hidden" name="pageID" value="ZYPL0220Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Upload Configuration">
			
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
								<td><ezf:inputText name="upldCsvId" ezfName="upldCsvId" otherAttr=" size=\"18\" maxlength=\"10\""/></td>	
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
								<td><ezf:inputText name="upldCsvNm" ezfName="upldCsvNm" otherAttr=" size=\"64\" maxlength=\"64\""/></td>	
							</tr>		
									
							<tr>		
								<td>File ID:</td>	
								<td><ezf:inputText name="upldCsvFileId" ezfName="upldCsvFileId" otherAttr=" size=\"30\" maxlength=\"11\""/></td>	
							</tr>		

							<tr>		
								<td>Table ID:</td>	
								<td><ezf:inputText name="upldCsvTempTblId" ezfName="upldCsvTempTblId" otherAttr=" size=\"30\" maxlength=\"30\""/></td>	
							</tr>		
									
							<tr>		

								<td>Loading Batch ID:</td>	
								<td><ezf:inputText name="ezReqBusinessID" ezfName="ezReqBusinessID" otherAttr=" size=\"30\" maxlength=\"9\""/></td>	
							</tr>		

						</table>
						
						<hr size="1" noshade>

						<table width="970" align="center" border="0">
							<col width="150" align="left">
							<col width="157" align="center">
							<col width="663" align="left">

							<!-- Target Table Name -->
							<tr height="30">
								<td>Template Header:</td>	
								<td><ezf:inputButton name="LoadNames" value="Load Names" htmlClass="pBtn11" /></td>	
								<td><br></td>	
							</tr>		
									
						</table>
												
						<table border="1" cellpadding="1" cellspacing="0" width="970">
							<col width="26" align="center">
							<col width="251" align="center">
							<col width="216"  align="center">
							<col width="162"  align="center">
							<col width="271" align="center">

							<tr>
								<td class="pClothBs">No</td>
								<td class="pClothBs">Data Item Name</td>
								<td class="pClothBs">Data Type</td>
								<td class="pClothBs">Length</td>
								<td class="pClothBs">Name</td>

							</tr>
						</table>
						
						<div style="height:282; overflow-y:scroll;width:988;">
							<table border="1" cellpadding="1" cellspacing="0" width="970" id="A">
								<col width="26" align="center">
								<col width="251" align="left">

								<col width="216"  align="left">
								<col width="162"  align="center">
								<col width="271" align="left">

								<tbody>
									<ezf:row ezfHyo="A">
										<tr  height="28">
											<td><ezf:label name="upldCsvHdrNum" ezfName="upldCsvHdrNum" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="upldCsvHdrDefNm" ezfName="upldCsvHdrDefNm" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="upldCsvHdrDataTpNm" ezfName="upldCsvHdrDataTpNm" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="upldCsvHdrDataLg" ezfName="upldCsvHdrDataLg" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="upldCsvHdrNm" ezfName="upldCsvHdrNm" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"100\""/></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>
										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>
										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>
										<tr height="28">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>

										<tr height="28" class="pEvenNumberBGcolor">
											<td><label ezfout>123</label></td>
											<td><label ezfout>123456789012345678901234567890</label></td>
											<td><label ezfout>Alphabet Numeric Character</label></td>
											<td><label ezfout>123456789,123456789</label></td>
											<td><input type="text" size="32" maxlength="100" value="123456789012345678901234567890" class="pHsu"></td>
										</tr>
									</ezf:skip>

								</tbody>
							</table>
						</div>
						

						<table width="970" align="center" border="0">
							<tr height="40">		
								<td width="147"><br></td>
								<td width="670" align="right"><ezf:inputButton name="Add" value="Add" htmlClass="pBtn11" /></td>
								<td width="134" align="center"><ezf:inputButton name="Update" value="Update" htmlClass="pBtn11" /></td>	
							</tr>		
						</table>

					

					</td>
				</tr>
			</table>

<%-- **** Task specific area ends here **** --%>
