<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171207140134 --%>
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
			<input type="hidden" name="pageID" value="NWAL2350Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Profitability">

<div style="margin-left:10px; padding:10;">
<!--<fieldset width"990">-->
<fieldset>
<legend>Profitability Preview</legend>
	<table>
		<col width="550" />
		<col width="200" />
		<col width="200" />
		<tr>
			<td align="left" valign="top">
				<table>
					<tr>
						<td align="left" valign="top">
						<fieldset>
						<table>
							<col width="160" />
							<col width="80" />
							<col width="1" />
							<col width="170" />
							<col width="80" />
							<tr height="30">
								<td class="stab">Negotiated Deal</td>
								<td class="stab">
									<ezf:inputText name="negoDealAmt" ezfName="negoDealAmt" value="1234567.12" otherAttr=" id=\"negoDealAmt\" size=\"10\" maxlength=\"19\""/>
								</td>
								<td>&nbsp;</td>
								<td class="stab">Gross Profit</td>
								<td class="stab">
									<ezf:inputText name="somMkupAmt" ezfName="somMkupAmt" value="1234567.12" otherAttr=" id=\"somMkupAmt\" size=\"10\" maxlength=\"19\""/>
								</td>
							</tr>
							<tr height="30">
								<td class="stab">Incentive Comp Eligible Amount</td>
								<td class="stab">
									<ezf:inputText name="totRepRevAmt" ezfName="totRepRevAmt" value="1234567.12" otherAttr=" id=\"totRepRevAmt\" size=\"10\" maxlength=\"19\""/>
								</td>
								<td>&nbsp;</td>
								<td class="stab">GP Without Cost Transfer %</td>
								<td class="stab">
									<ezf:inputText name="gpWotCostTrnsfRate" ezfName="gpWotCostTrnsfRate" value="1234567.12" otherAttr=" id=\"gpWotCostTrnsfRate\" size=\"10\" maxlength=\"19\""/>
								</td>
							</tr>
							<tr height="30">
								<td class="stab">Final Floor Amount</td>
								<td class="stab">
									<ezf:inputText name="somFinalFlAmt" ezfName="somFinalFlAmt" value="1234567.12" otherAttr=" id=\"somFinalFlAmt\" size=\"10\" maxlength=\"19\""/>
								</td>
								<td>&nbsp;</td>
								<td class="stab">GP With Cost Transfer %</td>
								<td class="stab">
									<ezf:inputText name="gpCostPctTrnsfRate" ezfName="gpCostPctTrnsfRate" value="1234567.12" otherAttr=" id=\"gpCostPctTrnsfRate\" size=\"10\" maxlength=\"19\""/>
								</td>
							</tr>
							<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
						</table>
						</fieldset>
						</td>
					</tr>
					<tr>
						<td align="left" valign="top">
						<fieldset>
						<legend>LEASE SUMMARY</legend>
						<table>
							<col width="160" />
							<col width="80" />
							<col width="1" />
							<col width="170" />
							<col width="80" />
							<tr height="30">
								<!-- Lease Company -->
								<td class="stab">Lease Company</td>
								<td class="stab">
									<ezf:inputText name="leaseCmpyNm" ezfName="leaseCmpyNm" value="1234567.12" otherAttr=" id=\"leaseCmpyNm\" size=\"10\" maxlength=\"19\""/>
								</td>
								<td>&nbsp;</td>
								<!-- Total Financed -->
								<td class="stab">Total Financed</td>
								<td class="stab">
									<ezf:inputText name="somTotFinAmt" ezfName="somTotFinAmt" value="1234567.12" otherAttr=" id=\"somTotFinAmt\" size=\"10\" maxlength=\"19\""/>
								</td>
							</tr>
							<tr height="30">
								<!-- Lease Payment -->
								<td class="stab">Lease Payment</td>
								<td class="stab">
									<ezf:inputText name="leasePmtAmt" ezfName="leasePmtAmt" value="1234567.12" otherAttr=" id=\"leasePmtAmt\" size=\"10\" maxlength=\"19\""/>
								</td>
								<td>&nbsp;</td>
								<!-- Finance Option -->
								<td class="stab">Financed Option</td>
								<td class="stab">
									<ezf:inputText name="leaseTpTxt" ezfName="leaseTpTxt" value="1234567.12" otherAttr=" id=\"leaseTpTxt\" size=\"10\" maxlength=\"19\""/>
								</td>
							</tr>
							<tr height="30">
								<!-- Lease Term (MTH) -->
								<td class="stab">Lease Term (MTH)</td>
								<td class="stab">
									<ezf:inputText name="leaseTermMthAot" ezfName="leaseTermMthAot" value="1234567.12" otherAttr=" id=\"leaseTermMthAot\" size=\"10\" maxlength=\"19\""/>
								</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
						</table>
						</fieldset>
						</td>
					</tr>
				</table>
			</td>
			<td align="left" valign="top">
				<fieldset>
				<table>
					<col width="110" />
					<col width="80" />
					<!-- MSRP -->
					<tr height="30">
						<td class="stab">MSRP</td>
						<td class="stab">
							<ezf:inputText name="msrpListPrcAmt" ezfName="msrpListPrcAmt" value="1234567.12" otherAttr=" id=\"msrpListPrcAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Standard Floor -->
					<tr height="30">
						<td class="stab">Standard Floor</td>
						<td class="stab">
							<ezf:inputText name="extFlPrcAmt" ezfName="extFlPrcAmt" value="1234567.12" otherAttr=" id=\"extFlPrcAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Floor Adjustments -->
					<tr height="30">
						<td class="stab">Floor Adjustments</td>
						<td class="stab">
							<ezf:inputText name="flAdjAmt" ezfName="flAdjAmt" value="1234567.12" otherAttr=" id=\"flAdjAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- CSMP Flag -->
					<tr height="30">
						<td class="stab">CSMP Flag</td>
						<td class="stab">
							<ezf:inputText name="csmpIndSomTxt" ezfName="csmpIndSomTxt" value="1234567.12" otherAttr=" id=\"csmpIndSomTxt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- CSMP Credits -->
					<tr height="30">
						<td class="stab">CSMP Credits</td>
						<td class="stab">
							<ezf:inputText name="csmpCrAmt" ezfName="csmpCrAmt" value="1234567.12" otherAttr=" id=\"csmpCrAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- CSMP Contract# -->
					<tr height="30">
						<td class="stab">CSMP Contract#</td>
						<td class="stab">
							<ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" value="1234567.12" otherAttr=" id=\"csmpContrNum\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- CSMP Price List -->
					<tr height="30">
						<td class="stab">CSMP Price List</td>
						<td class="stab">
							<ezf:inputText name="prcCatgCd" ezfName="prcCatgCd" value="1234567.12" otherAttr=" id=\"prcCatgCd\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
					<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
				</table>
				</fieldset>
			</td>
			<td align="left" valign="top">
				<fieldset>
				<table>
					<col width="110" />
					<col width="80" />
					<!-- Buyouts -->
					<tr height="30">
						<td class="stab">Buyouts</td>
						<td class="stab">
							<ezf:inputText name="somByotFinAmt" ezfName="somByotFinAmt" value="1234567.12" otherAttr=" id=\"somByotFinAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- SRT -->
					<tr height="30">
						<td class="stab">SRT</td>
						<td class="stab">
							<ezf:inputText name="somSvcRevTrnsfAmt" ezfName="somSvcRevTrnsfAmt" value="1234567.12" otherAttr=" id=\"somSvcRevTrnsfAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Service CT -->
					<tr height="30">
						<td class="stab">Service CT</td>
						<td class="stab">
							<ezf:inputText name="svcCostTrnsfAmt" ezfName="svcCostTrnsfAmt" value="1234567.12" otherAttr=" id=\"svcCostTrnsfAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Prof Svcs -->
					<tr height="30">
						<td class="stab">Prof Svcs</td>
						<td class="stab">
							<ezf:inputText name="sbscrSvcAmt" ezfName="sbscrSvcAmt" value="1234567.12" otherAttr=" id=\"sbscrSvcAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Service -->
					<tr height="30">
						<td class="stab">Service</td>
						<td class="stab">
							<ezf:inputText name="somSvcFinAmt" ezfName="somSvcFinAmt" value="1234567.12" otherAttr=" id=\"somSvcFinAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Supplies -->
					<tr height="30">
						<td class="stab">Supplies</td>
						<td class="stab">
							<ezf:inputText name="somSplyFinAmt" ezfName="somSplyFinAmt" value="1234567.12" otherAttr=" id=\"somSplyFinAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Promotions -->
					<tr height="30">
						<td class="stab">Promotions</td>
						<td class="stab">
							<ezf:inputText name="somPrmoAmt" ezfName="somPrmoAmt" value="1234567.12" otherAttr=" id=\"somPrmoAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Total Cost Transfers -->
					<tr height="30">
						<td class="stab">Total Cost Transfers</td>
						<td class="stab">
							<ezf:inputText name="somTotCostTrnsfAmt" ezfName="somTotCostTrnsfAmt" value="1234567.12" otherAttr=" id=\"somTotCostTrnsfAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<!-- Subscription Support -->
					<tr height="30">
						<td class="stab">Subscription Support</td>
						<td class="stab">
							<ezf:inputText name="proSvcAmt" ezfName="proSvcAmt" value="1234567.12" otherAttr=" id=\"proSvcAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
				</table>
				</fieldset>
			</td>
		</tr>
	</table>
	<table>
		<col width="370">
		<col width="600">
		<tr>
			<td align="left" valign="top">
				<fieldset>
				<legend>Invoice Reconciliations</legend>
				<table>
					<col width="110" />
					<col width="80" />
					<col width="1" />
					<col width="70" />
					<col width="80" />
					<tr height="30">
						<!-- CSA Invoice Amount -->
						<td class="stab">CSA Invoice Amount</td>
						<td class="stab">
							<ezf:inputText name="somCbsInvAmt" ezfName="somCbsInvAmt" value="1234567.12" otherAttr=" id=\"somCbsInvAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr height="30">
						<!-- Taxable Amount -->
						<td class="stab">Taxable Amount</td>
						<td class="stab">
							<ezf:inputText name="somTaxAbleAmt" ezfName="somTaxAbleAmt" value="1234567.12" otherAttr=" id=\"somTaxAbleAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
						<td>&nbsp;</td>
						<!-- Tax Amount -->
						<td class="stab">Tax Amount</td>
						<td class="stab">
							<ezf:inputText name="somTaxAmt" ezfName="somTaxAmt" value="1234567.12" otherAttr=" id=\"somTaxAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
					</tr>
					<tr height="30">
						<!-- Non Taxable Amount -->
						<td class="stab">Non Taxable Amount</td>
						<td class="stab">
							<ezf:inputText name="nonTaxAbleAmt" ezfName="nonTaxAbleAmt" value="1234567.12" otherAttr=" id=\"nonTaxAbleAmt\" size=\"10\" maxlength=\"19\""/>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</td>
			<td align="left" valign="top">
				<fieldset>
				<legend>Commissions</legend>
				<table>
					<col width="190">
					<col width="210">
					<col width="190">
					<tr>
						<td>
							<legend>Revenue</legend>
							<table>
								<col width="90" />
								<col width="80" />
								<!-- GP Equipment -->
								<tr height="30">
									<td class="stab">GP Equipment</td>
									<td class="stab">
										<ezf:inputText name="gpEquipRevAmt" ezfName="gpEquipRevAmt" value="1234567.12" otherAttr=" id=\"gpEquipRevAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- Inter-Regional -->
								<tr height="30">
									<td class="stab">Inter-Regional</td>
									<td class="stab">
										<ezf:inputText name="interRgRevAmt" ezfName="interRgRevAmt" value="1234567.12" otherAttr=" id=\"interRgRevAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- SPIFF -->
								<tr height="30">
									<td class="stab">SPIFF</td>
									<td class="stab">
										<ezf:inputText name="spiffRevAmt" ezfName="spiffRevAmt" value="1234567.12" otherAttr=" id=\"spiffRevAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- Inter-Territorial -->
								<tr height="30">
									<td class="stab">Inter-Territorial</td>
									<td class="stab">
										<ezf:inputText name="interTrtyRevAmt" ezfName="interTrtyRevAmt" value="1234567.12" otherAttr=" id=\"interTrtyRevAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- Units Sold -->
								<tr height="30">
									<td class="stab">Units Sold</td>
									<td class="stab">
										<ezf:inputText name="unitSldRevAmt" ezfName="unitSldRevAmt" value="1234567.12" otherAttr=" id=\"unitSldRevAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<legend>Earnings</legend>
							<table>
								<col width="180" />
								<col width="80" />
								<!-- GP Equipment Earnings -->
								<tr height="30">
									<td class="stab">GP Equipment Earnings</td>
									<td class="stab">
										<ezf:inputText name="gpEquipEarnAmt" ezfName="gpEquipEarnAmt" value="1234567.12" otherAttr=" id=\"gpEquipEarnAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- Inter-Regional Earnings -->
								<tr height="30">
									<td class="stab">Inter-Regional Earnings</td>
									<td class="stab">
										<ezf:inputText name="interRgEarnAmt" ezfName="interRgEarnAmt" value="1234567.12" otherAttr=" id=\"interRgEarnAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- SPIFF Earnings -->
								<tr height="30">
									<td class="stab">SPIFF Earnings</td>
									<td class="stab">
										<ezf:inputText name="spiffEarnAmt" ezfName="spiffEarnAmt" value="1234567.12" otherAttr=" id=\"spiffEarnAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- Inter-Territorial Earnings -->
								<tr height="30">
									<td class="stab">Inter-Territorial Earnings</td>
									<td class="stab">
										<ezf:inputText name="interTrtyEarnAmt" ezfName="interTrtyEarnAmt" value="1234567.12" otherAttr=" id=\"interTrtyEarnAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
								<!-- Units Sold Earnings -->
								<tr height="30">
									<td class="stab">Units Sold Earnings</td>
									<td class="stab">
										<ezf:inputText name="unitSldEarnAmt" ezfName="unitSldEarnAmt" value="1234567.12" otherAttr=" id=\"unitSldEarnAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<col width="80" />
								<col width="80" />
								<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
								<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
								<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
								<tr height="30"><td>&nbsp;</td><td>&nbsp;</td></tr>
								<!-- Total Earnings -->
								<tr height="30">
									<td class="stab">Total Earnings</td>
									<td class="stab">
										<ezf:inputText name="somTotEarnAmt" ezfName="somTotEarnAmt" value="1234567.12" otherAttr=" id=\"somTotEarnAmt\" size=\"10\" maxlength=\"19\""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</fieldset>
			</td>
		</tr>
	</table>
</fieldset>
</div>

<%-- **** Task specific area ends here **** --%>
