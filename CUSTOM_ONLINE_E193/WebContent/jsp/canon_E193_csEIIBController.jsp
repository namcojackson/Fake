<!-- $Header: canon_E193_csEIIBController.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csEIIBController.jsp - incorrect billing issue Controller
 |   
 | DESCRIPTION
 |   For a given criteria records and forwards it to respective jsp page.
 |
 | AUTHOR
 |  Subba 
 |
 | CREATION DATE
 |  10/04/2005
 |
 | HISTORY
 | DATE         WHO                 WHY
 | 18-Dec-2006    Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 | 17-Sep-2009    Naveen Khandelwal  MW Project Changes
 | 29-Jan-2016    Mangala Shenoy	 Modified for S21 changes
 |
 +=======================================================================--%>
<%@ page import="com.sun.xml.internal.ws.util.StringUtils"%>
<%@page language="java" %> 
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_OKSBillingDtlsObj" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_BillOutListObj" %>
<%@ page import ="java.util.*" %>
<%@ page import="com.canon.oracle.custapp.custinq.beans.Canon_E193_AcctInfoObj" %>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_Invoice" id="objInvDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_BillingIssue" id="objBillDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.dao.Canon_E193_IssueList" id="objIssueDao" scope="page"/>
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIBList" scope="request" />
<jsp:useBean class="com.canon.oracle.custapp.custinq.beans.Canon_E193_SessionObj" id="objIssueList" scope="request" />
<jsp:setProperty name="objIBList" property="*" />
<jsp:setProperty name="objIssueList" property="*" />




<%
    try {
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>
<%

        String nextPage = "canon_E193_csErrorPage.jsp";
		
		
	//System.out.println(" 	request.getParameterNames()========"+request.getParameterNames());
	
		//Start changes Anvesh to capture multiple frequency values
		/*for(int m=1; m<=20; m++){
			String freqen = request.getParameter("frequency"+m);
			
			if(!(freqen == "") ){
				System.out.println("frequencyparams["+m+"]"+freqen);
			}
			
						
		}*/
	
		
		//Canon_E193_BillOutListObj listObj = new Canon_E193_BillOutListObj();
        String strPageFrom = request.getParameter("hPageFrom");
		//Canon_E193_OKSBillingDtlsObj objOks_ = new Canon_E193_OKSBillingDtlsObj();
        Canon_E193_AcctInfoObj objSessionAcctInfo = (Canon_E193_AcctInfoObj)session.getAttribute("objSessionAcctInfo");
        
        String strAcctName = objSessionAcctInfo.getAcctName();
        String strAcctNo = objSessionAcctInfo.getAcctNum();
        String strContactName = objSessionAcctInfo.getContactName();
        String strContactNo = objSessionAcctInfo.getContactNum();
        int iCustAcctId = objSessionAcctInfo.getAcctId();
        System.out.println("::Hello::" + strAcctName);
        //Get Org ID
        int iOrgId = objCiSession.getOrgId();
        
        // get userId
        String iUserId = objCiSession.getUserId();
        boolean isConsolidate = false;
        // get resource id
        //Start changes for S21 by Mangala
        //int iResourceId = objCiSession.getResourceId();
        String iResourceId = objCiSession.getResourceId();
      //End changes for S21 by Mangala		
        // get ticket no
        int iTicketId = 0;
        int iLineId = 0;
        System.out.println("iUserId is " + iUserId + "iResourceId" + iResourceId);
        String strTicketId = request.getParameter("iTicketId")==null?"":request.getParameter("iTicketId");
        if(!"".equals(strTicketId)) iTicketId = Integer.parseInt(strTicketId);      
        String strLineId = request.getParameter("iLineId")==null?"":request.getParameter("iLineId");
        if(!"".equals(strLineId)) iLineId = Integer.parseInt(strLineId);
        String strInvSource = request.getParameter("InvSource")==null?"":request.getParameter("InvSource");
        String strInvNo = request.getParameter("invoiceNumber")==null?"":request.getParameter("invoiceNumber");
        String strContractId = request.getParameter("contractId")==null?"":request.getParameter("contractId");
        String strContractNo = request.getParameter("contractNo")==null?"":request.getParameter("contractNo");
        int isvcInvLinePkUsage=0;
        int isvcInvLinePkBase=0;
        String strsvcInvLinePkUsage = request.getParameter("svcInvLinePkUsage")==null?"":request.getParameter("svcInvLinePkUsage");
        if(!"".equals(strsvcInvLinePkUsage))
        	isvcInvLinePkUsage = Integer.parseInt(strsvcInvLinePkUsage);  
        String strsvcInvLinePkBase = request.getParameter("svcInvLinePkBase")==null?"":request.getParameter("svcInvLinePkBase");
        if(!"".equals(strsvcInvLinePkBase))
        	isvcInvLinePkBase = Integer.parseInt(strsvcInvLinePkBase);
        int iCatId = 0;
        String strCatId = request.getParameter("hCatId")==null?"":request.getParameter("hCatId");
	    if(!"".equalsIgnoreCase(strCatId) && !"null".equalsIgnoreCase(strCatId)) 
            iCatId = Integer.parseInt(strCatId);
        //MW Project Changes - Starts
        ArrayList ibListRead = null, ibListPrice = null, ibListBRead = null;
        //MW Project Changes - Ends
        ArrayList ibList = new ArrayList();
        ArrayList alIssueList = new ArrayList();        
        ArrayList aggregateList = new ArrayList();
                             
         ArrayList mLinePkList = null;
         /* alIssueListPK = request.getParameter("listOfInvLinePk")==null?"":(ArrayList)request.getParameter("listOfInvLinePk");
        */ /* ITG# 73987 : Begin */    
      //  String strRegionCode = (String)objCiSession.getRegionCode();    
        String strRegionCode = (String)request.getAttribute("strRegionCode");
        
        
        /* ITG# 73987 : End */
         System.out.println("StrsvcInvLinePkUsage = " + strsvcInvLinePkUsage + " strInvSource " + strInvSource + " strContractId= " + strContractId);
        if("CONTRACTS".equalsIgnoreCase(strInvSource)) {
            if(strContractId == null || ("".equalsIgnoreCase(strContractId)) || "null".equalsIgnoreCase(strContractId)) {
            	System.out.println("in if before getOksBillingDtls = " + strInvNo + " iOrgId = " +iOrgId);
            	
            	ibList = objInvDao.getOksBillingDtls(iOrgId, strInvNo);
            	objIBList.setContactFlag(true);
            	//System.out.println("ibList:: Before csEIContLineDetails call=  " + ibList); // Getting Correct data/
                nextPage = "canon_E193_csEIContLineDetails.jsp";
            } 
            else {
            	
            	System.out.println("!!!!!!!!!!!E193_csEIIBController Line Update in else");
            	
            	// Newly Added.
            	
            	//int count =0;
            	HashMap<String,ArrayList<Integer>> hMap = new HashMap<String,ArrayList<Integer>>();
                  mLinePkList = objInvDao.getOksBillingDtls(iOrgId, strInvNo);
    	          ArrayList<Integer> usageList = new ArrayList<Integer>();
    	          ArrayList<Integer> baseList  = new ArrayList<Integer>();
    	          ArrayList consolidateList    = new ArrayList();
    	          ArrayList commonListOfUsage_Base = new ArrayList();
    	          HashMap<Integer,String> baseSerialNum_InvLinePKMap = new HashMap<Integer,String>();
    	         HashMap<Integer,String> usageSerialNum_InvLinePKMap = new HashMap<Integer,String>();
    	          ArrayList<String> clickedSerial_InvLinePk = new ArrayList<String>();
    	          ArrayList<String> clickedSerial_InvLinePkForPrice = new ArrayList<String>();
    	          boolean isUsageInv = false;
    	          boolean isBaseInv = false;
    	          String usageInvoiceType = "";
    	          String baseInvoiceType = "";
    	          int b=0;
    	          int z=0;
    	          int p=0;
               	int LinePkListSize =mLinePkList.size();
               	
               	ArrayList<Integer> selectedLineReadList = new ArrayList<Integer>();
               	ArrayList<Integer> selectedLinePriceList = new ArrayList<Integer>();
               	
               	String[] strRSerialNo = request.getParameterValues("rSerialNo"); 
                String[] strPSerialNo = request.getParameterValues("pSerialNo");
                
                if(strRSerialNo != null)
                {
	                for (String readSerialNo : strRSerialNo)
	                {
	                	String tempArray[] = readSerialNo.split("~");                	
	                	selectedLineReadList.add(Integer.valueOf(tempArray[(tempArray.length - 1)]) );
	                }
                }
                
                if(strPSerialNo != null)
                {
	                for (String priceSerialNo : strPSerialNo)
	                {
	                	String tempArray[] = priceSerialNo.split("~");                	
	                	selectedLinePriceList.add(Integer.valueOf(tempArray[(tempArray.length - 1)]));
	                }
                }
                
                //System.out.println("!!!!!!!!!!!E193_csEIIBController selectedLineReadList" + selectedLineReadList);
                //System.out.println("!!!!!!!!!!!E193_csEIIBController selectedLinePriceList" + selectedLinePriceList);
              
                //MW Project Changes - Starts
                String[] strBSerialNo = request.getParameterValues("bSerialNo");
                
                ArrayList<Integer> selectedLineBaseList = new ArrayList<Integer>();
                
                if(strBSerialNo != null)
                {
	                for (String baseSerialNo : strBSerialNo)
	                {
	                	String tempArray[] = baseSerialNo.split("~");                	
	                	selectedLineBaseList.add(Integer.valueOf(tempArray[(tempArray.length - 1)]) );
	                }
                }
                
                //System.out.println("!!!!!!!!!!!E193_csEIIBController selectedLineBaseList" + selectedLineBaseList);
                
               	String serialNumberBase = "";
               	String serialNumberUsage = "";
            	for(int j=0;j<LinePkListSize;j++) {
            		Canon_E193_OKSBillingDtlsObj objBill1 = (Canon_E193_OKSBillingDtlsObj)mLinePkList.get(j);
            		//System.out.println("!!!!!!!!!!!E193_csEIIBController isvcInvLinePk::: "+objBill1.getSvcinvlinePk() + " " + objBill1);
            		
            		if("USAGE".equalsIgnoreCase(objBill1.getInvoiceType()) ){
            				isvcInvLinePkUsage=(Integer)objBill1.getSvcinvlinePk();
            				//System.out.println("isvcInvLinePk_Usage::: "+objBill1.getSvcinvlinePk());
            				usageList.add(b,isvcInvLinePkUsage);
            				//hMap.put("USAGE", usageList);
            				serialNumberUsage = objBill1.getSerialNumber();
            				usageSerialNum_InvLinePKMap.put(isvcInvLinePkUsage, serialNumberUsage);
            				usageInvoiceType = objBill1.getInvoiceType();
            				b++;
            				isUsageInv = true;
                      }
            		
            		if("BASE".equalsIgnoreCase(objBill1.getInvoiceType())){
            			isvcInvLinePkBase=(Integer)objBill1.getSvcinvlinePk();
            			//System.out.println("isvcInvLinePk_base::: "+isvcInvLinePkBase);
            			baseList.add(z,isvcInvLinePkBase);
            			//System.out.println("isvcInvLinePk_baseList::: "+"Base_0!! "+baseList);
            			//hMap.put("BASE", baseList);
            			serialNumberBase = objBill1.getSerialNumber();
            			baseSerialNum_InvLinePKMap.put(isvcInvLinePkBase, serialNumberBase);
            			baseInvoiceType = objBill1.getInvoiceType();
            			z++;
            			isBaseInv = true;
            		}
            		//System.out.println("InvoiceType:::: "+objBill1.getInvoiceType());
            		//Only One Time. Populate HashMap.
            		 if(j==LinePkListSize-1) {
            			 //System.out.println("J!!!"+j+ " (LinePkListSize-1)!!! "+(LinePkListSize-1));
            			 if(isUsageInv){
            				hMap.put("USAGE", usageList);
            				
            			}
            			 if(isBaseInv){
            				hMap.put("BASE", baseList);
            				
            				//System.out.println("Here Last Call"+hMap.toString());
            			} 
            			//hMap.put("USAGE", usageList);
            			//hMap.put("BASE", baseList);
            			
            		} 
            	} // End of For Loop.
            	
                //MW Project Changes - Ends
            
                String strFleetCont = request.getParameter("fleetContract");
                String strInvoiceType = request.getParameter("strInvoiceType");
                //System.out.println(" strInvoiceType data = "+ strInvoiceType);
                //System.out.println("!!!!!!!!!!!E193_csEIIBController strRSerialNo!!!! ---- " + Arrays.toString(strRSerialNo)  + "strPSerialNo !!! " + Arrays.toString(strPSerialNo) + " strBSerialNo !!!!" + Arrays.toString(strBSerialNo));
                
                //MW Project Changes - Starts
                //Adding RSerial Number
                objIBList.setRSerialNumbers(strRSerialNo);
                /* if(strRSerialNo!=null)
                System.out.println("GetRSerialNumbers= "+ Arrays.toString(objIBList.getRSerialNumbers())); */
                //objIBList.setmPSerialNumbers(strPSerialNo);
             
               //Added BSerialNo
                objIBList.setBSerialNumbers(strBSerialNo);
                /* if(strBSerialNo!=null)
                System.out.println("GetBSerialNumbers= "+ Arrays.toString(objIBList.getBSerialNumbers())); */
              // Newly Added
                String strBaseType = "";
                String strUsageType = "";
                if(strBSerialNo!=null) {
                	strBaseType = "BASE";
                	//System.out.println(" strBaseType:= " + strBaseType);
                }
                if(strRSerialNo!=null || strPSerialNo!=null) {
                	strUsageType = "USAGE";
                	//System.out.println(" strUsageType:= " + strUsageType);
                }
                
              //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!!!!!!! usageInvoiceType " + usageInvoiceType + " strInvoiceType " + strInvoiceType);
                
                if("CONSOLIDATED".equalsIgnoreCase(strInvoiceType)||(strInvoiceType != null && strInvoiceType.toUpperCase().contains("CONSOLIDATED"))) {
                	//System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!!!!!!! Inside CONSOLIDATED");
                	ArrayList lUsageList_ = hMap.get("USAGE");
                	ArrayList lBaseList_ = hMap.get("BASE");
                	if(lUsageList_ != null && lUsageList_.size() > 0 )
                	{
	                	for(int c=0;c<lUsageList_.size();c++){
	                		int isvcInvLinePkUsage_  = (Integer)lUsageList_.get(c);
	                		if(selectedLineReadList.contains(isvcInvLinePkUsage_))
	                		{
	                			ibListRead = objInvDao.getOksBillingSerialNoDtls(iOrgId, strFleetCont, strUsageType, "READ", strInvNo, strRSerialNo, strContractId, strContractNo,isvcInvLinePkUsage_);
	                		}
	                		else 
	                		{
	                			ibListRead = new ArrayList();
	                		}
	                        ibList.add(ibListRead);
	                        
	                        if(selectedLinePriceList.contains(isvcInvLinePkUsage_))
	                		{
	                        	ibListPrice = objInvDao.getOksBillingSerialNoDtls(iOrgId, strFleetCont, strUsageType, "PRICE", strInvNo, strPSerialNo, strContractId, strContractNo,isvcInvLinePkUsage_);
	                		}
	                		else 
	                		{
	                			ibListPrice = new ArrayList();
	                		}
	                        objIBList.setIsUsageFlag(true);
	                        ibList.add(ibListPrice);
	                	}
                	}
                	if(lBaseList_ != null && lBaseList_.size() > 0)
                	{
	                	for(int e=0;e<lBaseList_.size();e++){
	                		int isvcInvLinePkBase_  = (Integer)lBaseList_.get(e);
	                		//System.out.println(" CONSOLIDATED BaseLinePK!!! "+isvcInvLinePkBase_ );
	                        ibListBRead = objInvDao.getOksBillingSerialNoDtls(iOrgId, strFleetCont, strBaseType, "READ", strInvNo, strBSerialNo, strContractId, strContractNo,isvcInvLinePkBase_);
	                        objIBList.setIsBaseFalge(true);
	                        ibList.add(ibListBRead);
	                       
	                	}
                	}
                	isConsolidate = true;
                	 System.out.println("!!!!!Line Update CONSOLIDATED:::");
                } // End Of Consolidate.
                
                //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!End CONSOLIDATED:::");
                //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!Start USAGE:::");
                
                if(strUsageType!="" && !isConsolidate)
                 if("USAGE".equalsIgnoreCase(strUsageType)|| (strUsageType != null && strUsageType.toUpperCase().contains("USAGE"))) {
                	ArrayList lUsageList = hMap.get("USAGE");
                	//System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!lUsageList!!!"+lUsageList.toString());
                	
                	if(lUsageList != null && lUsageList.size() > 0)
                	{
	                	for(int c=0;c<lUsageList.size();c++)
	                	{
	                		int isvcInvLinePkUsage_  = (Integer)lUsageList.get(c);
	                		//System.out.println("USAGE::isvcInvLinePkUsage_::" +isvcInvLinePkUsage_);
	                		if(selectedLineReadList.contains(isvcInvLinePkUsage_))
	                		{
	                			ibListRead = objInvDao.getOksBillingSerialNoDtls(iOrgId, strFleetCont, "USAGE", "READ", strInvNo, strRSerialNo, strContractId, strContractNo,isvcInvLinePkUsage_);
	                		}
	                		else 
	                		{
	                			ibListRead = new ArrayList();
	                		}
	                		//System.out.println(":::IbListRead:::: EIIB Controller "+ ibListRead);
	                        ibList.add(ibListRead);
	                        if(selectedLinePriceList.contains(isvcInvLinePkUsage_))
	                		{
	                        	ibListPrice = objInvDao.getOksBillingSerialNoDtls(iOrgId, strFleetCont, "USAGE", "PRICE", strInvNo, strPSerialNo, strContractId, strContractNo,isvcInvLinePkUsage_);
	                		}
	                		else 
	                		{
	                			ibListPrice = new ArrayList();
	                		}
	                        //System.out.println(":::ibListRead:::: EIIB Controller" + ibListRead);
	                        //System.out.println(":::ibListPrice:::: EIIB Controller" + ibListPrice);
	                        ibList.add(ibListPrice);
	                        
	                        //System.out.println(":::USAGE:::" + ibList.toString());
	                	} 
	                	
	                	objIBList.setIsUsageFlag(true);
                	}
                	 //System.out.println(":::USAGE::: EIIB Controller" + ibList.toString());
                	 //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!Line Update :::USAGE::: EIIB Controller" + ibList.size()); 	
                }
                
                //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!END USAGE:::");
                //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!Start BASE:::");
                if( strBaseType!="" && !isConsolidate )
                 if("BASE".equalsIgnoreCase(strBaseType)||strBaseType.toUpperCase().contains("BASE")) {
                	ArrayList lBaseList = hMap.get("BASE");
                	//System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!BASE lBaseList!!!" + lBaseList.toString());
                	
                	if(lBaseList != null && lBaseList.size() > 0)
                	{
	                	for(int c=0;c<lBaseList.size();c++){
	                		int isvcInvLinePkBase__ = (Integer)lBaseList.get(c);
	                		//System.out.println("key: " + key + " value: " + serialString); 
	                		//System.out.println("isvcInvLinePkBase__= "+ isvcInvLinePkBase__ + " serialString= "+serialNumString + " strSerial= "+ strSerial);
	                		if(selectedLineBaseList.contains(isvcInvLinePkBase__))
	                		{
	                			 //System.out.println("key: " + key + " value: " + serialNumString); 
	                			 //System.out.println(" Before getOksBillingSerialNoDtls Method Call!! ");
	                		     ibListBRead = objInvDao.getOksBillingSerialNoDtls(iOrgId, strFleetCont, strBaseType, "READ", strInvNo, strBSerialNo, strContractId, strContractNo,isvcInvLinePkBase__);
	                   		     //System.out.println(":::IbListBRead:::: "+ ibListBRead);
	                   		     ibList.add(ibListBRead);
	                		}
	                	}
                	}

                	
                	 objIBList.setIsBaseFalge(true);
                	 //System.out.println(":::BASE:::"+ibList.toString());
                }
                
                //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!END BASE:::");
                
                if(selectedLinePriceList != null && selectedLinePriceList.size() > 0 )
                {
                	aggregateList = objInvDao.getOksAggregatePricingDtls(strInvNo, strUsageType);
                	//System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!Line Update aggregateList =  " + aggregateList);
                }
                
                //MW  Project Changes - Ends
                //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!Start issue" );
              
                alIssueList = objIssueDao.getIssueList(iOrgId,strRegionCode, null, iCatId);
                //System.out.println("!!!!!!!!!!!E193_csEIIBController ITicketID: "+iTicketId + " ILineId: " + iLineId);
                //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!Start set htattribute" );
                if(iTicketId > 0 && iLineId > 0) {
                    ArrayList subList = objBillDao.getBillSubLine(iOrgId, iTicketId, iLineId);
                    //System.out.println("!!!!!!!!!!!E193_csEIIBController CsEIIBController: "+ subList.toString() );
                    Canon_E193_OKSBillingDtlsObj objOKS = null;
                    Canon_E193_TicketSubLinesObj objSL = null;
                    String strOKSerialNo = "", strOKSLineId = "", strSLSerialNo = "", strSLObjectType = "", strModel = "", strOKSFleetSerialNo = "", strOKSInvoiceType = "", strFleetContract = "";
                    for(int i=0; i<ibList.size(); i++) {
                        ArrayList list = (ArrayList)ibList.get(i);
                        for(int k=0; k<list.size(); k++) {
                            objOKS = (Canon_E193_OKSBillingDtlsObj)list.get(k);
                            strModel = objOKS.getModel()==null?"":objOKS.getModel();
                            strOKSerialNo = objOKS.getSerialNumber()==null?strModel:objOKS.getSerialNumber();
                            strOKSFleetSerialNo = objOKS.getFleetSeriaNumber()==null?"":objOKS.getFleetSeriaNumber();
                            strOKSInvoiceType = objOKS.getInvoiceType()==null?"":objOKS.getInvoiceType();
                            strFleetContract = objOKS.getFleetContract()==null?"":objOKS.getFleetContract();
                            strOKSLineId = objOKS.getContractLineId();
                            //System.out.println("StrModel :"+strModel+ "StrOKSerialNo: "+strOKSerialNo +"StrOKSFleetSerialNo: "+strOKSFleetSerialNo+ "StrOKSInvoiceType:"+ 
                            //	strOKSInvoiceType+"StrFleetContract: "+strFleetContract+"StrOKSLineId: "+strOKSLineId + " Lse id : " + objOKS.getLseId() );
                            Hashtable htValues = new Hashtable();
                            for(int j = 0; j<subList.size(); j++) {
                                objSL = (Canon_E193_TicketSubLinesObj)subList.get(j);
                                strSLSerialNo = objSL.getSerialNo()==null?"":objSL.getSerialNo();
                                strSLObjectType = objSL.getObjectValue()==null?"":objSL.getObjectValue();
                                //MW Project Changes
								String counterName = objSL.getAttribute3(); // spothuri -- added counter name to hashtable mapping
								if(counterName == null )
									counterName = "";
								String physMtrLbCd = objSL.getAttribute4(); // spothuri -- added counter name to hashtable mapping -- Q11607 changed to physical meter label code
								if(physMtrLbCd == null )
									physMtrLbCd = "";
                                if(("Y".equalsIgnoreCase(strFleetContract) && "BASE".equalsIgnoreCase(strOKSInvoiceType))
                                    || ("Y".equalsIgnoreCase(strFleetContract) && "USAGE".equalsIgnoreCase(strOKSInvoiceType) && objOKS.getLseId() == 12))
                                {

                                    String tempSerialNo = null;

                                    if(strOKSFleetSerialNo == null || strOKSFleetSerialNo.equals("") || strOKSFleetSerialNo.equalsIgnoreCase("null"))
                                        tempSerialNo = strSLSerialNo;
                                    else
                                        tempSerialNo = strOKSFleetSerialNo;  

                                    if(tempSerialNo.equalsIgnoreCase(strSLSerialNo) && 
                                        strOKSLineId.equalsIgnoreCase(strSLObjectType))
                                        htValues.put((tempSerialNo+strOKSLineId+counterName+physMtrLbCd+objSL.getCatId()), (objSL.getNewValue()==null?"":objSL.getNewValue()));
                                }
                                else
                                {
                                    if(strOKSerialNo.equalsIgnoreCase(strSLSerialNo) && 
                                        strOKSLineId.equalsIgnoreCase(strSLObjectType))
                                        htValues.put((strOKSerialNo+strOKSLineId+counterName+physMtrLbCd+objSL.getCatId()), (objSL.getNewValue()==null?"":objSL.getNewValue()));
                                }
                            }
                            objOKS.setHtAttribute(htValues);
                            //System.out.println("!!!!!!!!!!!E193_csEIIBController SetValues: ");
                        }
                    }
                    
                    if(aggregateList != null && !aggregateList.isEmpty())
                    {
                    	for(int i = 0; i < aggregateList.size(); i++)
                    	{
                    		 objOKS = (Canon_E193_OKSBillingDtlsObj)aggregateList.get(i);
                    		 Hashtable htValues = new Hashtable();
                    		 
                    		 strModel = objOKS.getModel()==null?"":objOKS.getModel();
                             strOKSerialNo = objOKS.getSerialNumber()==null?strModel:objOKS.getSerialNumber();
                             strOKSInvoiceType = objOKS.getInvoiceType()==null?"":objOKS.getInvoiceType();
                             strOKSLineId = objOKS.getContractLineId();
                    		 String billingCounterName = objOKS.getBillingCounterName();
                    		 //System.out.println("!!!!!!!!!!!E193_csEIIBController StrModel :"+strModel+ " StrOKSerialNo: "+strOKSerialNo + " StrOKSInvoiceType: "+ 
                                 //	strOKSInvoiceType+" StrOKSLineId: "+strOKSLineId + " Lse id : " + objOKS.getLseId() 
                                 //	+ " Billing Counter Name " + billingCounterName);
                    		 
                    		 for(int j = 0; j<subList.size(); j++) 
                    		 {
                                 objSL = (Canon_E193_TicketSubLinesObj)subList.get(j);
                                 strSLSerialNo = objSL.getSerialNo()==null?"":objSL.getSerialNo();
                                 strSLObjectType = objSL.getObjectValue()==null?"":objSL.getObjectValue();
                                 //MW Project Changes
 								 String counterName = objSL.getAttribute3(); // spothuri -- added counter name to hashtable mapping
 								 if(counterName == null )
 									 counterName = "";
 								 String physMtrLbCd = objSL.getAttribute4(); 
 								 if(physMtrLbCd == null )
 									physMtrLbCd = "";
 								
                                 if(strSLSerialNo.equals("Aggregate") && "USAGE".equalsIgnoreCase(strOKSInvoiceType) &&  
                                		 strOKSerialNo.equalsIgnoreCase(strSLSerialNo) &&  billingCounterName.equalsIgnoreCase(objSL.getAttribute3())) 
                                 {
                                         htValues.put((strOKSerialNo+strOKSLineId+counterName+physMtrLbCd+objSL.getCatId()), (objSL.getNewValue()==null?"":objSL.getNewValue()));
                                 }
                             }
                    		 objOKS.setHtAttribute(htValues);
                    	}
                    }
                    //System.out.println("!!!!!!!!!!!E193_csEIIBController !!!!!End set htattribute" );
               }  
                System.out.println("!!!!!!!!!!!E193_csEIIBController SetValues_nextPage: ");
                nextPage = "canon_E193_csEIContLineUpdate.jsp";
                
            }
        }else{
            ibList = objInvDao.getInvoiceLines(iOrgId, strInvNo);
            ibList.remove(0);
            System.out.println("Outer ELSE: " );
            alIssueList = objIssueDao.getIssueList(iOrgId, strRegionCode, null, iCatId);
            if("SUPPLY".equalsIgnoreCase(strInvSource)) nextPage = "canon_E193_csEISupplyLineDetails.jsp";
            else if("SERVICE".equalsIgnoreCase(strInvSource)) nextPage = "canon_E193_csEIServiceLineDetails.jsp";
            else nextPage = "canon_E193_csEIOtherLineDetails.jsp";
        }
        objIBList.setArrayList(ibList);
        objIBList.setAggregateList(aggregateList);
        objIssueList.setArrayList(alIssueList);
        
        //System.out.println("At the End objIBList " + ibList);
        
%>
        <form name="IbControlForm" method="post">
            <jsp:forward page="<%=nextPage%>">
                <jsp:param name="hPageFrom" value="EIIBController" />
                <jsp:param name="nextPage" value="" />
            </jsp:forward>
        </form>

<%
} 
catch(com.canon.oracle.custapp.util.CanonCustAppExceptionUtil eCustExp) {
       
       String strErr = "-- Exception : " + eCustExp.getStrErrorDesc() + " -- Exception Location :" + eCustExp.getStrErrorLocation();

       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + strErr);
}
catch (Exception eExp) {

       response.sendRedirect("canon_E193_csErrorPage.jsp?err=" + eExp.toString());
}
%>
