<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20091006074612 --%>
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
			<input type="hidden" name="pageID" value="ZZOL0052Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Lookup Screen">
			
<center>
	<table>
		<tr>
			<td>
				
<%-- ######################################## HEADER ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="0">
					<col width="160">
					<col width="5">
					<col width="420">
					<col width="5">
					<col width="70">
					
					<tr>
						<td class="stab">Business Process Name(*)</td>
						<td></td>
						<td><ezf:inputText name="upldCsvRstProcNm" ezfName="upldCsvRstProcNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"50\" maxlength=\"100\""/></td>
						<td></td>
						<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" /></td>
					</tr>

				</table>

				<hr>
				
<%-- ######################################## DETAIL ######################################## --%>
				<table border="1" cellpadding="1" cellspacing="0">
					<col width="30"  align="center">
					<col width="80"  align="center">
					<col width="180" align="center">
					<col width="420" align="center">
					
					<tr>
						<td class="pClothBs">No</td>
						<td class="pClothBs"></td>
						<td class="pClothBs">Business Process Id</td>
						<td class="pClothBs">Business Process Name</td>
					</tr>
				</table>
				
				<div style="overflow:auto; height:482;">
					<table border="1" cellpadding="1" cellspacing="0" id="A_TBL">
						<col width="30"  align="center">
						<col width="80"  align="center">
						<col width="180" align="center">
						<col width="420" align="left">
						
						<tbody>
							<ezf:row ezfHyo="A">
							<tr>
								<td><ezf:label name="xxNum_A" ezfName="xxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
								<td><ezf:inputButton name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
								<td><ezf:label name="menuProcId_A" ezfName="menuProcId_A" ezfHyo="A" ezfArrayNo="0" /></td>
								<td style="word-break:break-all;"><ezf:label name="menuProcNm_A" ezfName="menuProcNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
							</tr>
							</ezf:row>

							<ezf:skip>
								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>2</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>3</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>4</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>5</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>6</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>7</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>8</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>9</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>10</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>11</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>12</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>13</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>14</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>15</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>16</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>17</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>18</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>19</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>20</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>

								<tr class="pEvenNumberBGcolor">
									<td><label ezfout>21</label></td>
									<td><input type="button" class="pBtn6" name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><label ezfout>XXXXXXXXX2XXXXXXXX</label></td>
									<td><label ezfout>XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5</label></td>
								</tr>
							</ezf:skip>

						</tbody>
					</table>
				</div>
				
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
