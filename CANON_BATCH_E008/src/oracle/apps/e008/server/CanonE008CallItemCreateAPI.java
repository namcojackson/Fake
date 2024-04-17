package oracle.apps.e008.server;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;  
import java.util.Date;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import business.parts.NMZC004001PMsg;
import canon.apps.common.CanonConstants;
import com.canon.cusa.s21.api.NMZ.NMZC004001.NMZC004001;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import parts.common.EZDPStringItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import oracle.apps.e008.server.CanonE008ItemRec;

public class CanonE008CallItemCreateAPI {

	public static final String GET_PROJECT_ITEMS_DETAILS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROJECT_ITEMS_DETAILS(?,?,?,?)}";
	public static final String UPDATE_PROJECT_ITEMS_STATUS= "{call CANON_E008_ITEM_WORKBENCH_PKG.UPDATE_PROJECT_ITEMS_STATUS(?,?,?,?,?)}";
	public static final String GET_BOM_ITEMS_DETAILS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_COMP_ITEMS_DETAILS(?,?,?,?,?)}";
	public static final String GET_ASL_NAME= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_ASL_NAMES(?,?,?,?,?,?)}";
	public static final String GET_PRICELIST_NAME= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PRICELIST_NAMES(?,?,?,?)}";
	
	
	
	private static final CanonE008CallItemCreateAPI CanonE008ItemProcessUtil = null;
  

	/*public String checkNull(HttpServletRequest req, String str) {
		String s = "";
    
		String tmp = req.getParameter(str); 
		if (tmp != null) {
			logMsg(str + ": " + tmp);
			if (tmp.trim().length() > 0) {
				s = tmp.trim();
			}
    
		}  
		return s;
	}

	public BigDecimal checkNull(HttpServletRequest req, BigDecimal str) {
		BigDecimal s = null;

		if (req.getParameter(str.toString()) != null) {
			BigDecimal tmp = new BigDecimal(req.getParameter(str.toString()));
			s = tmp;
		} else
			s = BigDecimal.ZERO;
		return s;
  
	}*/
	
	public void logMsg(String str) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println("[E008: Item Create API ] ["
				+ sdf.format(Calendar.getInstance().getTime()) + "] : " + str);
	}
	
	private void logMsg(EZDPStringItem mdseCd) {
		
		System.out.println("Error Code:"+mdseCd);
		// TODO Auto-generated method stub
		
	}
	
	public void createServicerequest(BigDecimal p_project_number) {
		
		SimpleDateFormat format = new SimpleDateFormat("z");		
		SimpleDateFormat lsDateFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
		String timezone = format.format(new Date());
		String invokeTimestamp = lsDateFmt.format(new Date()); 
		//String loginUser= "Q07107"; //CanonS21SessionValidate.getUserName();
        
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String currentDt = dateFormat.format(new Date());
		String processStr="";
		String aslNameStr="";
		
		Object result[]= null;
		Object resultbom[]=null;
		Object resultPriceList[]= null;
		List itemList = null;
		List bomitemlist = null;
		List priceList = null;
		int cntrecord;
		int cntbomrecord;
		System.out.println("getProjectItemsDetails - Before ");
		result = getProjectItemsDetails(new BigDecimal("1"));  //(p_project_number);  //;
		System.out.println("getProjectItemsDetails - after ");
		if (result != null && result.length > 0) {
				
				//CanonE008ItemProcessDAO.CanonE008ProjectRec p = (CanonE008ItemProcessDAO.CanonE008ProjectRec) CanonE008ItemProcessUtil.first(result);

				 System.out.println("createServicerequest - getting the Item records ");
				
				itemList = (List)CanonE008ItemProcessUtil.first(result);
				cntrecord=itemList.size();
				System.out.println("createServicerequest - cntrecord-" + cntrecord);
				
				for(int j=0;j<itemList.size();j++) 
				{
					System.out.println("createServicerequest - j-" + j);
					System.out.println("createServicerequest - jj-" + j);
					
					CanonE008ItemRec item = (CanonE008ItemRec) itemList.get(j);
    				//CanonE008ItemProcessDAO.ItemInfoRec item=(CanonE008ItemProcessDAO.ItemInfoRec) itemList.get(j);

					EZDDBCICarrier.initOnline(item.getCreatedby(), invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
					EZDDBCICarrier.setProgID("S21EXTN_E008");
					
				//Initialize S21DB-Carrier (It should be done. It leads NullPointer exception when didn't initialize.)
				//These contents are used as a default data in S21-Standard table. (Update user, time, time-zone, program id, company code)
				/////EZDDBCICarrier.initOnline(loginUser, invokeTimestamp, timezone, CanonConstants.CSA_COMPANY_CODE);
				/////EZDDBCICarrier.setProgID("S21EXTN_E008");
				System.out.println("createServicerequest -	1 " + j);
					
				String[] resArr = new String[4];
					
				NMZC004001 s21Api=new NMZC004001();
				System.out.println("createServicerequest -	2 " + j);
				//NMZC004001PMsg pmsg = getMsg(request);
				NMZC004001PMsg  pmsg = new NMZC004001PMsg();	
				
				System.out.println("createServicerequest -	3 " + j);
				pmsg.glblCmpyCd.setValue(CanonConstants.CSA_COMPANY_CODE);
				
				System.out.println("createServicerequest -	currentDt - pmsg.slsDt - " + currentDt);
				pmsg.slsDt.setValue(currentDt);  //"20160226"
				  
				//System.out.println("createServicerequest -	4 " + j);
				pmsg.NMZC004002PMsg.setValidCount(1);
				 
				if ((item.getItemnumber() !=null) && (item.getItemnumber()!="") && (!"null".equals(item.getItemnumber().toLowerCase())))
				{
					System.out.println("createServicerequest - getItemnumber() - NMZC004002PMsg.no(0).mdseCd_M - " + item.getItemnumber());
					pmsg.NMZC004002PMsg.no(0).mdseCd_M.setValue(item.getItemnumber());
				}
				 
				if ((item.getItemdescription() !=null) && (item.getItemdescription()!="") && (!"null".equals(item.getItemdescription().toLowerCase())))
				{
					System.out.println("createServicerequest - getItemdescription() - NMZC004002PMsg.no(0).mdseNm_M - Before "  );
					//String shortitem = item.getItemdescription().substring(1, 30);
					//System.out.println("createServicerequest - getItemdescription() - NMZC004002PMsg.no(0).mdseNm_M - " + shortitem );
					System.out.println("createServicerequest - getItemdescription() - NMZC004002PMsg.no(0).mdseDescShortTxt_D - " + item.getItemdescription());
					//pmsg.NMZC004002PMsg.no(0).mdseNm_M.setValue(item.getItemdescription().substring(1, 30));
					pmsg.NMZC004002PMsg.no(0).mdseDescShortTxt_D.setValue(item.getItemdescription());
				}
				if ((item.getManufacturer() !=null) && (item.getManufacturer()!="") && (!"null".equals(item.getManufacturer().toLowerCase())))
				{
					System.out.println("createServicerequest - getManufacturer() - NMZC004002PMsg.no(0).mdseItemMnfCd_D - " + item.getManufacturer());
					pmsg.NMZC004002PMsg.no(0).mdseItemMnfCd_D.setValue(item.getManufacturer());
				}
				
				if ((item.getManufactureritemnumber() !=null) && (item.getManufactureritemnumber()!="") && (!"null".equals(item.getManufactureritemnumber().toLowerCase())))
				{
					System.out.println("createServicerequest - getManufactureritemnumber() - NMZC004002PMsg.no(0).mnfItemCd_D - " + item.getManufactureritemnumber());
					pmsg.NMZC004002PMsg.no(0).mnfItemCd_D.setValue(item.getManufactureritemnumber());
				}

				if ((item.getUpccode() !=null) && (item.getUpccode()!="") && (!"null".equals(item.getUpccode().toLowerCase())))
				{
					System.out.println("createServicerequest - getUpccode() - NMZC004002PMsg.no(0).upcCd_M - " + item.getUpccode());
					pmsg.NMZC004002PMsg.no(0).upcCd_M.setValue(item.getUpccode());
				}
				
				if ((item.getLongdescription() !=null) && (item.getLongdescription()!="") && (!"null".equals(item.getLongdescription().toLowerCase())))
				{
					System.out.println("createServicerequest - getLongdescription() - NMZC004002PMsg.no(0).mdseFmlNm_M - " + item.getLongdescription());
					pmsg.NMZC004002PMsg.no(0).mdseFmlNm_M.setValue(item.getLongdescription());
					pmsg.NMZC004002PMsg.no(0).mdseDescLongTxt_D.setValue(item.getLongdescription()); 
					//pmsg.NMZC004002PMsg.no(0).mdseDescShortTxt_D.setValue(item.getLongdescription());
				}	

				if ((item.getItemtype() !=null) && (item.getItemtype()!="") && (!"null".equals(item.getItemtype().toLowerCase())))
				{
					System.out.println("createServicerequest -	getItemtype() - NMZC004002PMsg.no(0).mdseItemTpCd_D - " + item.getItemtype());
					pmsg.NMZC004002PMsg.no(0).mdseItemTpCd_D.setValue(item.getItemtype()); // Get the Item type name
				}
				
				if ((item.getMerchandisetype() !=null) && (item.getMerchandisetype()!="") && (!"null".equals(item.getMerchandisetype().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getMerchandisetype() - NMZC004002PMsg.no(0).coaMdseTpCd_D - " + item.getMerchandisetype());
					pmsg.NMZC004002PMsg.no(0).coaMdseTpCd_D.setValue(item.getMerchandisetype()); //10
					System.out.println("createServicerequest -	getMerchandisetype() - NMZC004002PMsg.no(0).coaCcCd_D - " + item.getMerchandisetype());
					pmsg.NMZC004002PMsg.no(0).coaCcCd_D.setValue(item.getMerchandisetype() ); //"000000"
				}
				
				if ((item.getItemclassfication() !=null) && (item.getItemclassfication()!="") && (!"null".equals(item.getItemclassfication().toLowerCase())))
				{
					System.out.println("createServicerequest -	getItemclassfication() - NMZC004002PMsg.no(0).mdseItemClsTpCd_D - " + item.getItemclassfication());
					pmsg.NMZC004002PMsg.no(0).mdseItemClsTpCd_D.setValue(item.getItemclassfication());
				}
				
				if ((item.getProductcode() !=null) && (item.getProductcode()!="") && (!"null".equals(item.getProductcode().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductcode() - NMZC004002PMsg.no(0).coaProdCd_M - " + item.getProductcode());
					pmsg.NMZC004002PMsg.no(0).coaProdCd_M.setValue(item.getProductcode());
				}
				if ((item.getMarketingmodel()!=null) && (item.getMarketingmodel()!="") && (!"null".equals(item.getMarketingmodel().toLowerCase())))
				{	
					System.out.println("createServicerequest - getMarketingmodel() - NMZC004002PMsg.no(0).mktMdlCd_D - " + item.getMarketingmodel());
					pmsg.NMZC004002PMsg.no(0).mktMdlCd_D.setValue(item.getMarketingmodel());   //("IPC6000");
				}
				
				if ((item.getMarketingsegment()!=null) && (item.getMarketingsegment()!="") && (!"null".equals(item.getMarketingsegment().toLowerCase())))
				{	
					System.out.println("createServicerequest - getMarketingsegment() - NMZC004002PMsg.no(0).mktMdlSegCd_D - " + item.getMarketingsegment());
					pmsg.NMZC004002PMsg.no(0).mktMdlSegCd_D.setValue(item.getMarketingsegment());
				}	
				if ((item.getUomclass() !=null) && (item.getUomclass()!="") && (!"null".equals(item.getUomclass().toLowerCase())))
				{
					System.out.println("createServicerequest -	getUomclass() - NMZC004002PMsg.no(0).pkgUomClsCd_D - " + item.getUomclass());
					pmsg.NMZC004002PMsg.no(0).pkgUomClsCd_D.setValue(item.getUomclass()); //"QTY"
				}
				/*if ((item.getUom() !=null) && (item.getUom()!="") && (!"null".equals(item.getUom().toLowerCase())))
				{
					System.out.println("createServicerequest -	getUom() - NMZC004002PMsg.no(0).xxStoreList.no(0).pkgUomCd_S " + item.getUom());
					pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).pkgUomCd_S.setValue(item.getUom()); //"EA"
				}*/	

				if ((item.getProductlevel1() !=null) && (item.getProductlevel1()!="") && (!"null".equals(item.getProductlevel1().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevel1 - NMZC004002PMsg.no(0).zerothProdCtrlCd_M - " + item.getProductlevel1().substring(0, item.getProductlevel1().indexOf("|")));
					pmsg.NMZC004002PMsg.no(0).zerothProdCtrlCd_M.setValue(item.getProductlevel1().substring(0, item.getProductlevel1().indexOf("|"))); 
				}
				
				if ((item.getProductlevel2() !=null) && (item.getProductlevel2()!="") && (!"null".equals(item.getProductlevel2().toLowerCase())))
				{
					System.out.println("createServicerequest -  getProductlevel2 - NMZC004002PMsg.no(0).firstProdCtrlCd_M - " + item.getProductlevel2().substring(0, item.getProductlevel2().indexOf("|")));
					pmsg.NMZC004002PMsg.no(0).firstProdCtrlCd_M.setValue(item.getProductlevel2().substring(0, item.getProductlevel2().indexOf("|")));
				}
				
				if ((item.getProductlevel3() !=null) && (item.getProductlevel3()!="") && (!"null".equals(item.getProductlevel3().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevel3 - NMZC004002PMsg.no(0).scdProdCtrlCd_M - " + item.getProductlevel3().substring(0, item.getProductlevel3().indexOf("|")));
					pmsg.NMZC004002PMsg.no(0).scdProdCtrlCd_M.setValue(item.getProductlevel3().substring(0, item.getProductlevel3().indexOf("|")));
				}
				
				if ((item.getProductlevel4() !=null) && (item.getProductlevel4()!="") && (!"null".equals(item.getProductlevel4().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevel4 - NMZC004002PMsg.no(0).thirdProdCtrlCd_M - " + item.getProductlevel4().substring(0, item.getProductlevel4().indexOf("|")));
					pmsg.NMZC004002PMsg.no(0).thirdProdCtrlCd_M.setValue(item.getProductlevel4().substring(0, item.getProductlevel4().indexOf("|")));
				}

				if ((item.getProductlevel5() !=null) && (item.getProductlevel5()!="") && (!"null".equals(item.getProductlevel5().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevel5 - NMZC004002PMsg.no(0).frthProdCtrlCd_M - " + item.getProductlevel5().substring(0, item.getProductlevel5().indexOf("|")));
					pmsg.NMZC004002PMsg.no(0).frthProdCtrlCd_M.setValue(item.getProductlevel5().substring(0, item.getProductlevel5().indexOf("|")));
				}
				
				if ((item.getProductlevel1() !=null) && (item.getProductlevel1()!="") && (!"null".equals(item.getProductlevel1().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevelName1 - NMZC004002PMsg.no(0).zerothProdCtrlNm_M - " + item.getProductlevel1().substring(item.getProductlevel1().indexOf("|")+1, item.getProductlevel1().length()));
					pmsg.NMZC004002PMsg.no(0).zerothProdCtrlNm_M.setValue(item.getProductlevel1().substring(item.getProductlevel1().indexOf("|")+1, item.getProductlevel1().length()));
				}
				
				if ((item.getProductlevel2() !=null) && (item.getProductlevel2()!="") && (!"null".equals(item.getProductlevel2().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevelName2 - NMZC004002PMsg.no(0).firstProdCtrlNm_M - " + item.getProductlevel2().substring(item.getProductlevel2().indexOf("|")+1, item.getProductlevel2().length()));
					pmsg.NMZC004002PMsg.no(0).firstProdCtrlNm_M.setValue(item.getProductlevel2().substring(item.getProductlevel2().indexOf("|")+1, item.getProductlevel2().length()));
				}
				
				if ((item.getProductlevel3() !=null) && (item.getProductlevel3()!="") && (!"null".equals(item.getProductlevel3().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevelName3 - NMZC004002PMsg.no(0).scdProdCtrlNm_M - " + item.getProductlevel3().substring(item.getProductlevel3().indexOf("|")+1, item.getProductlevel3().length()));
					pmsg.NMZC004002PMsg.no(0).scdProdCtrlNm_M.setValue(item.getProductlevel3().substring(item.getProductlevel3().indexOf("|")+1, item.getProductlevel3().length()));
				}

				if ((item.getProductlevel4() !=null) && (item.getProductlevel4()!="") && (!"null".equals(item.getProductlevel4().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevelName4 - NMZC004002PMsg.no(0).thirdProdCtrlNm_M - " + item.getProductlevel4().substring(item.getProductlevel4().indexOf("|")+1, item.getProductlevel4().length()));
					pmsg.NMZC004002PMsg.no(0).thirdProdCtrlNm_M.setValue(item.getProductlevel4().substring(item.getProductlevel4().indexOf("|")+1, item.getProductlevel4().length()));
				}
				
				if ((item.getProductlevel5() !=null) && (item.getProductlevel5()!="") && (!"null".equals(item.getProductlevel5().toLowerCase())))
				{
					System.out.println("createServicerequest -	getProductlevelName5 - NMZC004002PMsg.no(0).frthProdCtrlNm_M - " + item.getProductlevel5().substring(item.getProductlevel5().indexOf("|")+1, item.getProductlevel5().length()));
					pmsg.NMZC004002PMsg.no(0).frthProdCtrlNm_M.setValue(item.getProductlevel5().substring(item.getProductlevel5().indexOf("|")+1, item.getProductlevel5().length()));
				}

				if ((item.getMaterialgroup1() !=null) && (item.getMaterialgroup1()!="") && (!"null".equals(item.getMaterialgroup1().toLowerCase())))
				{
					System.out.println("createServicerequest -	getMaterialgroup1 - NMZC004002PMsg.no(0).slsMatGrpCd_D1 - " + item.getMaterialgroup1());
					pmsg.NMZC004002PMsg.no(0).slsMatGrpCd_D1.setValue(item.getMaterialgroup1());
				}
				if ((item.getMaterialgroup2() !=null) && (item.getMaterialgroup2()!="") && (!"null".equals(item.getMaterialgroup2().toLowerCase())))
				{
					System.out.println("createServicerequest -	getMaterialgroup2 - NMZC004002PMsg.no(0).slsMatGrpCd_D2 - " + item.getMaterialgroup2());
					pmsg.NMZC004002PMsg.no(0).slsMatGrpCd_D2.setValue(item.getMaterialgroup2());
				}
				if ((item.getMaterialgroup3() !=null) && (item.getMaterialgroup3()!="") && (!"null".equals(item.getMaterialgroup3().toLowerCase())))
				{
					System.out.println("createServicerequest -	getMaterialgroup3 - NMZC004002PMsg.no(0).slsMatGrpCd_D3 - " + item.getMaterialgroup3());
					pmsg.NMZC004002PMsg.no(0).slsMatGrpCd_D3.setValue(item.getMaterialgroup3());
				}
				
				if ((item.getStandardcost() !=null) && (item.getStandardcost()!="") && (!"null".equals(item.getStandardcost().toLowerCase())))
				{
					System.out.println("createServicerequest -	getStandardcost - NMZC004002PMsg.no(0).thisMthTotStdCostAmt_M - " + item.getStandardcost());
					pmsg.NMZC004002PMsg.no(0).thisMthTotStdCostAmt_M.setValue(new BigDecimal(item.getStandardcost())); 
				}
				if ((item.getInventorytrackable() !=null) && (item.getInventorytrackable()!="") && (!"null".equals(item.getInventorytrackable().toLowerCase())))
				{
					System.out.println("createServicerequest -	getInventorytrackable - NMZC004002PMsg.no(0).invtyCtrlFlg_M - " + item.getInventorytrackable());
					pmsg.NMZC004002PMsg.no(0).invtyCtrlFlg_M.setValue(item.getInventorytrackable());
				}
				if ((item.getInternalItem() !=null) && (item.getInternalItem()!="") && (!"null".equals(item.getInternalItem().toLowerCase())))
				{
					System.out.println("createServicerequest -	getInternalItem - NMZC004002PMsg.no(0).itrlItemFlg - " + item.getInternalItem());
					pmsg.NMZC004002PMsg.no(0).itrlItemFlg.setValue(item.getInternalItem());
				}

				//System.out.println("createServicerequest -	getReturncontrolled - NMZC004002PMsg.no(0).rtrnReqPrtFlg_D11 - " + item.getReturncontrolled());
				
				if ((item.getReturncontrolled() !=null) && (item.getReturncontrolled()!="") && (item.getReturncontrolled() !="-1") && (!"null".equals(item.getReturncontrolled().toLowerCase())))
				{
					System.out.println("createServicerequest -	getReturncontrolled - NMZC004002PMsg.no(0).rtrnReqPrtFlg_D - " + item.getReturncontrolled());
					pmsg.NMZC004002PMsg.no(0).rtrnReqPrtFlg_D.setValue(item.getReturncontrolled());
				}       
				if ((item.getReturncontroltype() !=null) && (item.getReturncontroltype()!="") && (item.getReturncontroltype()!="-1") && (!"null".equals(item.getReturncontroltype().toLowerCase())))
				{
					System.out.println("createServicerequest -	getReturncontroltype - NMZC004002PMsg.no(0).rtrnCtrlTpCd_D - " + item.getReturncontroltype());
					pmsg.NMZC004002PMsg.no(0).rtrnCtrlTpCd_D.setValue(item.getReturncontroltype());
				}
				if ((item.getServicemodel() !=null) && (item.getServicemodel()!="") && (!"null".equals(item.getServicemodel().toLowerCase())))
				{
					System.out.println("createServicerequest -	getServicemodel - NMZC004002PMsg.no(0).svcMdlNm - " + item.getServicemodel());
					pmsg.NMZC004002PMsg.no(0).svcMdlNm.setValue(item.getServicemodel());
				} 
				
				if ((item.getRevenue() !=null) && (item.getRevenue()!="") && (!"null".equals(item.getRevenue().toLowerCase())))
				{
					System.out.println("createServicerequest -	getRevenue - NMZC004002PMsg.no(0).revCoaAcctCd_D - " + item.getRevenue());
					pmsg.NMZC004002PMsg.no(0).revCoaAcctCd_D.setValue(item.getRevenue());
				}
				
				if ((item.getCostofgoods() !=null) && (item.getCostofgoods()!="") && (!"null".equals(item.getCostofgoods().toLowerCase())))
				{
					System.out.println("createServicerequest -	getCostofgoods - NMZC004002PMsg.no(0).cogsCoaAcctCd_D - " + item.getCostofgoods());
					pmsg.NMZC004002PMsg.no(0).cogsCoaAcctCd_D.setValue(item.getCostofgoods());
				}
				
				if ((item.getExpense() !=null) && (item.getExpense()!="") && (!"null".equals(item.getExpense().toLowerCase())))
				{
					System.out.println("createServicerequest -	getExpense - NMZC004002PMsg.no(0).expCoaAcctCd_D - " + item.getExpense());
					pmsg.NMZC004002PMsg.no(0).expCoaAcctCd_D.setValue(item.getExpense());
				}
				

				/*if ((item.getAccrual()!=null) && (item.getAccrual()!=""))
				{	
					System.out.println("createServicerequest -	getAccrual-" + item.getAccrual());
					pmsg.NMZC004002PMsg.no(0).acrlCoaAcctCd_D.setValue(item.getAccrual());
				}*/
					
				
				if ((item.getOwningdivision() !=null) && (item.getOwningdivision()!="") && (!"null".equals(item.getOwningdivision().toLowerCase())))
				{
					System.out.println("createServicerequest -	getOwningdivision() - NMZC004002PMsg.no(0).lineBizTpCd_D - " + item.getOwningdivision());
					pmsg.NMZC004002PMsg.no(0).lineBizTpCd_D.setValue(item.getOwningdivision());  //"ESS"
				}
				
				if ((item.getCoveragebasetype() !=null) && (item.getCoveragebasetype()!="") && (!"null".equals(item.getCoveragebasetype().toLowerCase())))
				{
					System.out.println("createServicerequest -	item.getCoveragebasetype() - NMZC004002PMsg.no(0).svcCovBaseTpCd_D - " + item.getCoveragebasetype());
					pmsg.NMZC004002PMsg.no(0).svcCovBaseTpCd_D.setValue(item.getCoveragebasetype()); 
				}
				if ((item.getCoveragetype() !=null) && (item.getCoveragetype()!="") && (!"null".equals(item.getCoveragetype().toLowerCase())))
				{
					System.out.println("createServicerequest -	item.getCoveragetype() - NMZC004002PMsg.no(0).svcCovTmplTpCd_D- " + item.getCoveragetype());
					pmsg.NMZC004002PMsg.no(0).svcCovTmplTpCd_D.setValue(item.getCoveragetype()); 
				}
				if ((item.getServiceallocationtype() !=null) && (item.getServiceallocationtype()!="") && (!"null".equals(item.getServiceallocationtype().toLowerCase())))
				{
					System.out.println("createServicerequest -	item.getServiceallocationtype() - NMZC004002PMsg.no(0).svcAllocTpCd_D -  " + item.getServiceallocationtype());
					pmsg.NMZC004002PMsg.no(0).svcAllocTpCd_D.setValue(item.getServiceallocationtype()); 
				}
				
				
				
				
				if ((item.getParttype() !=null) && (item.getParttype()!="") && (!"null".equals(item.getParttype().toLowerCase())))
				{
					System.out.println("createServicerequest -	getParttype - NMZC004002PMsg.no(0).prtItemTpCd_D - " + item.getParttype());
					pmsg.NMZC004002PMsg.no(0).prtItemTpCd_D.setValue(item.getParttype());
				}	
				if ((item.getPartsdropshipdisabled() !=null) && (item.getPartsdropshipdisabled()!="") && (!"null".equals(item.getPartsdropshipdisabled().toLowerCase())))
				{
					System.out.println("createServicerequest -	getPartsdropshipdisabled -NMZC004002PMsg.no(0).prtDropShipDsblFlg_D - " + item.getPartsdropshipdisabled());
					pmsg.NMZC004002PMsg.no(0).prtDropShipDsblFlg_D.setValue(item.getPartsdropshipdisabled());
				}	
				
				if ((item.getPartyieldoutputs() !=null) && (item.getPartyieldoutputs()!="") && (!"null".equals(item.getPartyieldoutputs().toLowerCase())))
				{
					System.out.println("createServicerequest -	getPartyieldoutputs - NMZC004002PMsg.no(0).prtYieldOtptQty_D - " + item.getPartyieldoutputs());
					pmsg.NMZC004002PMsg.no(0).prtYieldOtptQty_D.setValue(new BigDecimal(item.getPartyieldoutputs()));
				}	
				if ((item.getPartyielddays() !=null) && (item.getPartyielddays()!="") && (!"null".equals(item.getPartyielddays().toLowerCase())))
				{
					System.out.println("createServicerequest -	getPartyielddays - NMZC004002PMsg.no(0).prtYieldDaysAot_D - " + item.getPartyielddays());
					pmsg.NMZC004002PMsg.no(0).prtYieldDaysAot_D.setValue(new BigDecimal(item.getPartyielddays()));
				}	
				if ((item.getPartssurveyrequired() !=null) && (item.getPartssurveyrequired()!="") && ( !"null".equals(item.getPartssurveyrequired().toLowerCase())))
				{
					System.out.println("createServicerequest -	getPartssurveyrequired - NMZC004002PMsg.no(0).prtSrvyReqFlg_D11 - " + item.getPartssurveyrequired());
					pmsg.NMZC004002PMsg.no(0).prtSrvyReqFlg_D.setValue(item.getPartssurveyrequired());
				}	

				
				if ((item.getWarranty() !=null) && (item.getWarranty()!="") && (!"null".equals(item.getWarranty().toLowerCase())))
				{
					System.out.println("createServicerequest -	getWarranty -NMZC004002PMsg.no(0).svcWtyTpCd_D - " + item.getWarranty());
					pmsg.NMZC004002PMsg.no(0).svcWtyTpCd_D.setValue(item.getWarranty());
				}	
				if ((item.getWarrantyperiod() !=null) && (item.getWarrantyperiod()!="") && (!"null".equals(item.getWarrantyperiod().toLowerCase())))
				{
					System.out.println("createServicerequest -	getWarrantyperiod - NMZC004002PMsg.no(0).wtyDaysAot_D - " + item.getWarrantyperiod());
					pmsg.NMZC004002PMsg.no(0).wtyDaysAot_D.setValue(new BigDecimal(item.getWarrantyperiod()));
				}	
				if ((item.getImagewareremoteenabled() !=null) && (item.getImagewareremoteenabled()!="") && (!"null".equals(item.getImagewareremoteenabled().toLowerCase())))
				{
					System.out.println("createServicerequest -	getImagewareremoteenabled - NMZC004002PMsg.no(0).iwrEnblFlg_D - " + item.getImagewareremoteenabled());
					pmsg.NMZC004002PMsg.no(0).iwrEnblFlg_D.setValue(item.getImagewareremoteenabled());
				}	
				if ((item.getImagewareremotemodel() !=null) && (item.getImagewareremotemodel()!="") && (!"null".equals(item.getImagewareremotemodel().toLowerCase())))
				{
					System.out.println("createServicerequest -	getImagewareremotemodel - NMZC004002PMsg.no(0).iwrMdlCd_D - " + item.getImagewareremotemodel());
					pmsg.NMZC004002PMsg.no(0).iwrMdlCd_D.setValue(item.getImagewareremotemodel());
				}	
				if ((item.getImagewareremoteitem() !=null) && (item.getImagewareremoteitem()!="") && (!"null".equals(item.getImagewareremoteitem().toLowerCase())))
				{
					System.out.println("createServicerequest -	getImagewareremoteitem -NMZC004002PMsg.no(0).iwrMdseCd_D - " + item.getImagewareremoteitem());
					pmsg.NMZC004002PMsg.no(0).iwrMdseCd_D.setValue(item.getImagewareremoteitem());
				}	
				if ((item.getMeteredmachine() !=null) && (item.getMeteredmachine()!="") && (!"null".equals(item.getMeteredmachine().toLowerCase())))
				{
					System.out.println("createServicerequest -	getMeteredmachine() - NMZC004002PMsg.no(0).mtrMachFlg_D - " + item.getMeteredmachine());
					pmsg.NMZC004002PMsg.no(0).mtrMachFlg_D.setValue(item.getMeteredmachine());
				}	
				if ((item.getInstallbasecontrolled() !=null) && (item.getInstallbasecontrolled()!="") && (!"null".equals(item.getInstallbasecontrolled().toLowerCase())))
				{
					System.out.println("createServicerequest -	getInstallbasecontrolled() - NMZC004002PMsg.no(0).instlBaseCtrlFlg_D - " + item.getInstallbasecontrolled());
					pmsg.NMZC004002PMsg.no(0).instlBaseCtrlFlg_D.setValue(item.getInstallbasecontrolled());
				}	
				if ((item.getServicecallenabled() !=null) && (item.getServicecallenabled()!="") && (!"null".equals(item.getServicecallenabled().toLowerCase())))
				{
					System.out.println("createServicerequest -	getServicecallenabled() - NMZC004002PMsg.no(0).svcCallEnblFlg_D - " + item.getServicecallenabled());
					pmsg.NMZC004002PMsg.no(0).svcCallEnblFlg_D.setValue(item.getServicecallenabled());
				}	
				
				if ((item.getSoftwarecategory() !=null) && (item.getSoftwarecategory()!="") && (!"null".equals(item.getSoftwarecategory().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSoftwarecategory() - NMZC004002PMsg.no(0).swCatgCd_D - " + item.getSoftwarecategory());
					pmsg.NMZC004002PMsg.no(0).swCatgCd_D.setValue(item.getSoftwarecategory());
				}	
				if ((item.getSoftwareversion() !=null) && (item.getSoftwareversion()!="") && (!"null".equals(item.getSoftwareversion().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSoftwareversion() - NMZC004002PMsg.no(0).swVrsnTxt_D - " + item.getSoftwareversion());
					pmsg.NMZC004002PMsg.no(0).swVrsnTxt_D.setValue(item.getSoftwareversion());
				}	
				if ((item.getSoftwareproductcategory() !=null) && (item.getSoftwareproductcategory()!="") && (!"null".equals(item.getSoftwareproductcategory().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSoftwareproductcategory() - NMZC004002PMsg.no(0).swProdCatgCd_D - " + item.getSoftwareproductcategory());
					pmsg.NMZC004002PMsg.no(0).swProdCatgCd_D.setValue(item.getSoftwareproductcategory());
				}	
				if ((item.getLicensecontrol() !=null) && (item.getLicensecontrol()!="") && (!"null".equals(item.getLicensecontrol().toLowerCase())))
				{
					System.out.println("createServicerequest -	getLicensecontrol() - NMZC004002PMsg.no(0).swLicCtrlTpCd_D - " + item.getLicensecontrol());
					pmsg.NMZC004002PMsg.no(0).swLicCtrlTpCd_D.setValue(item.getLicensecontrol());
				}	
				if ((item.getElancontrolled() !=null) && (item.getElancontrolled()!="") && (!"null".equals(item.getElancontrolled().toLowerCase())))
				{
					System.out.println("createServicerequest -	getElancontrolled() - NMZC004002PMsg.no(0).intntConnSwCtrlFlg_D - " + item.getElancontrolled());
					pmsg.NMZC004002PMsg.no(0).intntConnSwCtrlFlg_D.setValue(item.getElancontrolled());
				}	
				if ((item.getSeatsfrom() !=null) && (item.getSeatsfrom()!="") && (!"null".equals(item.getSeatsfrom().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSeatsfrom() - NMZC004002PMsg.no(0).swLicSeatFromQty_D - " + item.getSeatsfrom());
					pmsg.NMZC004002PMsg.no(0).swLicSeatFromQty_D.setValue(new BigDecimal(item.getSeatsfrom()));
				}	
				if ((item.getSeatsto() !=null) && (item.getSeatsto()!="")  && (!"null".equals(item.getSeatsto().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSeatsto() - NMZC004002PMsg.no(0).swLicSeatToQty_D -  " + item.getSeatsto());
					pmsg.NMZC004002PMsg.no(0).swLicSeatToQty_D.setValue(new BigDecimal(item.getSeatsto()));
				}	
				if ((item.getFixednoofseats() !=null) && (item.getFixednoofseats()!="") && (!"null".equals(item.getFixednoofseats().toLowerCase())))
				{
					System.out.println("createServicerequest -	getFixednoofseats() - NMZC004002PMsg.no(0).swLicSeatFixQty_D - " + item.getFixednoofseats());
					pmsg.NMZC004002PMsg.no(0).swLicSeatFixQty_D.setValue(new BigDecimal(item.getFixednoofseats()));
				}	
				if ((item.getSoftwaremaintenancecontrol() !=null) && (item.getSoftwaremaintenancecontrol()!="") && (!"null".equals(item.getSoftwaremaintenancecontrol().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSoftwaremaintenancecontrol() - NMZC004002PMsg.no(0).swMaintCtrlTpCd_D - " + item.getSoftwaremaintenancecontrol());
					pmsg.NMZC004002PMsg.no(0).swMaintCtrlTpCd_D.setValue(item.getSoftwaremaintenancecontrol());
				}	
				if ((item.getAssurancepointsperlicense() !=null) && (item.getAssurancepointsperlicense()!="") && (!"null".equals(item.getAssurancepointsperlicense().toLowerCase())))
				{
					System.out.println("createServicerequest -	getAssurancepointsperlicense() - NMZC004002PMsg.no(0).asrnPntPerLicQty_D - " + item.getAssurancepointsperlicense());
					pmsg.NMZC004002PMsg.no(0).asrnPntPerLicQty_D.setValue(new BigDecimal(item.getAssurancepointsperlicense()));
				}	
				if ((item.getBundledmaintenanceitem() !=null) && (item.getBundledmaintenanceitem()!="") && (!"null".equals(item.getBundledmaintenanceitem().toLowerCase())))
				{
					System.out.println("createServicerequest -	getBundledmaintenanceitem() - NMZC004002PMsg.no(0).bdlMaintMdseCd_D - " + item.getBundledmaintenanceitem());
					pmsg.NMZC004002PMsg.no(0).bdlMaintMdseCd_D.setValue(item.getBundledmaintenanceitem());
				}	
				if ((item.getMaintenancepopavaliable() !=null) && (item.getMaintenancepopavaliable()!="") && (!"null".equals(item.getMaintenancepopavaliable().toLowerCase())))
				{
					System.out.println("createServicerequest -	getMaintenancepopavaliable() - NMZC004002PMsg.no(0).maintPopAvalFlg_D - " + item.getMaintenancepopavaliable());
					pmsg.NMZC004002PMsg.no(0).maintPopAvalFlg_D.setValue(item.getMaintenancepopavaliable());
				}	
				if ((item.getExtendedmaintpopavailable() !=null) && (item.getExtendedmaintpopavailable()!="") && (!"null".equals(item.getExtendedmaintpopavailable().toLowerCase())))
				{
					System.out.println("createServicerequest -	getExtendedmaintpopavailable() - NMZC004002PMsg.no(0).extMaintPopAvalFlg_D - " + item.getExtendedmaintpopavailable());
					pmsg.NMZC004002PMsg.no(0).extMaintPopAvalFlg_D.setValue(item.getExtendedmaintpopavailable());
				}	
				

				if ((item.getMaintenanceitemtype() !=null) && (item.getMaintenanceitemtype()!="") && (!"null".equals(item.getMaintenanceitemtype().toLowerCase())))
				{
					System.out.println("createServicerequest -	getMaintenanceitemtype() - NMZC004002PMsg.no(0).maintItemTpCd_D - " + item.getMaintenanceitemtype());
					pmsg.NMZC004002PMsg.no(0).maintItemTpCd_D.setValue(item.getMaintenanceitemtype());
				}	
				if ((item.getMaintenanceitemterm() !=null) && (item.getMaintenanceitemterm()!="") && (!"null".equals(item.getMaintenanceitemterm().toLowerCase())))
				{
					System.out.println("createServicerequest -	getMaintenanceitemterm() - NMZC004002PMsg.no(0).maintItemTermMthNum_D - " + item.getMaintenanceitemterm());
					pmsg.NMZC004002PMsg.no(0).maintItemTermMthNum_D.setValue(new BigDecimal(item.getMaintenanceitemterm()));
				}	
				if ((item.getAssurancepointmin() !=null) && (item.getAssurancepointmin()!="") && (!"null".equals(item.getAssurancepointmin().toLowerCase())))
				{
					System.out.println("createServicerequest -	getAssurancepointmin() - NMZC004002PMsg.no(0).asrnPntMinQty_D - " + item.getAssurancepointmin());
					pmsg.NMZC004002PMsg.no(0).asrnPntMinQty_D.setValue(new BigDecimal(item.getAssurancepointmin()));
				}	
				if ((item.getAssurancepointsmax() !=null) && (item.getAssurancepointsmax()!="")  && (!"null".equals(item.getAssurancepointsmax().toLowerCase())))
				{
					System.out.println("createServicerequest -	getAssurancepointsmax() - NMZC004002PMsg.no(0).asrnPntMaxQty_D - " + item.getAssurancepointsmax());
					pmsg.NMZC004002PMsg.no(0).asrnPntMaxQty_D.setValue(new BigDecimal(item.getAssurancepointsmax()));
				}	
				if ((item.getAssuranceptsfixedperordqty() !=null) && (item.getAssuranceptsfixedperordqty()!="") && (!"null".equals(item.getAssuranceptsfixedperordqty().toLowerCase())))
				{
					System.out.println("createServicerequest -	getAssuranceptsfixedperordqty() NMZC004002PMsg.no(0).asrnPntFixPerOrdQty_D - " + item.getAssuranceptsfixedperordqty());
					pmsg.NMZC004002PMsg.no(0).asrnPntFixPerOrdQty_D.setValue(new BigDecimal(item.getAssuranceptsfixedperordqty()));
				}	
				

				if ((item.getSupplyoemmanufactuer() !=null) && (item.getSupplyoemmanufactuer()!="") && (!"null".equals(item.getSupplyoemmanufactuer().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSupplyoemmanufactuer() - NMZC004002PMsg.no(0).imgSplyOemMnfCd_D - " + item.getSupplyoemmanufactuer());
					pmsg.NMZC004002PMsg.no(0).imgSplyOemMnfCd_D.setValue(item.getSupplyoemmanufactuer());
				}	
				if ((item.getSupplyoemcode() !=null) && (item.getSupplyoemcode()!="") && (!"null".equals(item.getSupplyoemcode().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSupplyoemcode() - NMZC004002PMsg.no(0).imgSplyOemCd_D - " + item.getSupplyoemcode());
					pmsg.NMZC004002PMsg.no(0).imgSplyOemCd_D.setValue(item.getSupplyoemcode());
				}	
				if ((item.getSupplytype() !=null) && (item.getSupplytype()!="") && (!"null".equals(item.getSupplytype().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSupplytype() - NMZC004002PMsg.no(0).imgSplyTpCd_D - " + item.getSupplytype());
					pmsg.NMZC004002PMsg.no(0).imgSplyTpCd_D.setValue(item.getSupplytype());
				}	
				if ((item.getSupplycolor() !=null) && (item.getSupplycolor()!="") && (!"null".equals(item.getSupplycolor().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSupplycolor() - NMZC004002PMsg.no(0).imgSplyColorTpCd_D - " + item.getSupplycolor());
					pmsg.NMZC004002PMsg.no(0).imgSplyColorTpCd_D.setValue(item.getSupplycolor());
				}	
				if ((item.getSupplyyield() !=null) && (item.getSupplyyield()!="")  && (!"null".equals(item.getSupplyyield().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSupplyyield() - NMZC004002PMsg.no(0).imgSplyYieldCnt_D - " + item.getSupplyyield());
					pmsg.NMZC004002PMsg.no(0).imgSplyYieldCnt_D.setValue(new BigDecimal(item.getSupplyyield()));
				}	
				if ((item.getSupplyyielduom() !=null) && (item.getSupplyyielduom()!="")  && (!"null".equals(item.getSupplyyielduom().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSupplyyielduom() - NMZC004002PMsg.no(0).imgSplyYieldUomCd_D - " + item.getSupplyyielduom());
					pmsg.NMZC004002PMsg.no(0).imgSplyYieldUomCd_D.setValue(item.getSupplyyielduom());
				}	
				if ((item.getSupplyyieldtype() !=null) && (item.getSupplyyieldtype()!="") && (!"null".equals(item.getSupplyyieldtype().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSupplyyieldtype() - NMZC004002PMsg.no(0).imgSplyYieldTpCd_D - " + item.getSupplyyieldtype());
					pmsg.NMZC004002PMsg.no(0).imgSplyYieldTpCd_D.setValue(item.getSupplyyieldtype());
				}	
				if ((item.getPrivatelabelflag() !=null) && (item.getPrivatelabelflag()!="") && (!"null".equals(item.getPrivatelabelflag().toLowerCase())))
				{
					System.out.println("createServicerequest -	getPrivatelabelflag() - NMZC004002PMsg.no(0).imgSplyPvtLbTpCd_D - " + item.getPrivatelabelflag());
					pmsg.NMZC004002PMsg.no(0).imgSplyPvtLbTpCd_D.setValue(item.getPrivatelabelflag());
				}	
				

				if ((item.getPlanninggroup() !=null) && (item.getPlanninggroup()!="") && (!"null".equals(item.getPlanninggroup().toLowerCase())))
				{
					System.out.println("createServicerequest -	getPlanninggroup() " + item.getPlanninggroup());
					pmsg.NMZC004002PMsg.no(0).prchGrpCd_D.setValue(item.getPlanninggroup());
				}	
				if ((item.getThirdparty() !=null) && (item.getThirdparty()!="") && (!"null".equals(item.getThirdparty().toLowerCase())))
				{
					System.out.println("createServicerequest -	getThirdparty() - NMZC004002PMsg.no(0).thirdPtyItemFlg_D - " + item.getThirdparty());
					pmsg.NMZC004002PMsg.no(0).thirdPtyItemFlg_D.setValue(item.getThirdparty());
				}	
				
				
				
				if ((item.getTariffcode() !=null) && (item.getTariffcode()!="") && (!"null".equals(item.getTariffcode().toLowerCase())))
				{
					System.out.println("createServicerequest -	getTariffcode() - NMZC004002PMsg.no(0).trfCd_M - " + item.getTariffcode());
					pmsg.NMZC004002PMsg.no(0).trfCd_M.setValue(item.getTariffcode());
				}	
				if ((item.getFreightclasscode() !=null) && (item.getFreightclasscode()!="") && (!"null".equals(item.getFreightclasscode().toLowerCase())))
				{
					System.out.println("createServicerequest -	getFreightclasscode() - NMZC004002PMsg.no(0).frtClsCd_M - " + item.getFreightclasscode());
					pmsg.NMZC004002PMsg.no(0).frtClsCd_M.setValue(item.getFreightclasscode());
				}	
				
				
				if ((item.getInvoiceable() !=null) && (item.getInvoiceable()!="") && (!"null".equals(item.getInvoiceable().toLowerCase())))
				{
					System.out.println("createServicerequest -	getInvoiceable() - NMZC004002PMsg.no(0).invPsblFlg_D - " + item.getInvoiceable());
					pmsg.NMZC004002PMsg.no(0).invPsblFlg_D.setValue(item.getInvoiceable());
				}	
				if ((item.getAccountingrules() !=null) && (item.getAccountingrules()!="") && (!"null".equals(item.getAccountingrules().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getAccountingrules - NMZC004002PMsg.no(0).dfrdAcctgRuleCd_D - " + item.getAccountingrules());
					pmsg.NMZC004002PMsg.no(0).dfrdAcctgRuleCd_D.setValue(item.getAccountingrules()); //"IM"
				}	
				if ((item.getInvoicingrules() !=null) && (item.getInvoicingrules()!="")  && (!"null".equals(item.getInvoicingrules().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getInvoicingrules - NMZC004002PMsg.no(0).dfrdInvRuleCd_D - " + item.getInvoicingrules());
					pmsg.NMZC004002PMsg.no(0).dfrdInvRuleCd_D.setValue(item.getInvoicingrules()); //"AR"
				}
				if ((item.getTaxcode() !=null) && (item.getTaxcode()!="") && (!"null".equals(item.getTaxcode().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getTaxcode - NMZC004002PMsg.no(0).taxExemTpCd_D - " + item.getTaxcode());
					pmsg.NMZC004002PMsg.no(0).taxExemTpCd_D.setValue(item.getTaxcode()); //"AR"
				}

				
				if ((item.getSafetyhazerdousmaterial() !=null) && (item.getSafetyhazerdousmaterial()!="") && (!"null".equals(item.getSafetyhazerdousmaterial().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getSafetyhazerdousmaterial - NMZC004002PMsg.no(0).hazMatFlg_S - " + item.getSafetyhazerdousmaterial());
					pmsg.NMZC004002PMsg.no(0).hazMatFlg_S.setValue(item.getSafetyhazerdousmaterial()); //"AR"
				}
				if ((item.getSafetyhazerdousnumber() !=null) && (item.getSafetyhazerdousnumber()!="") && (!"null".equals(item.getSafetyhazerdousnumber().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getSafetyhazerdousnumber - NMZC004002PMsg.no(0).hazMatCd_S - " + item.getSafetyhazerdousnumber());
					pmsg.NMZC004002PMsg.no(0).hazMatCd_S.setValue(item.getSafetyhazerdousnumber()); //"AR"
				}
				if ((item.getManufacturedcountry() !=null) && (item.getManufacturedcountry()!="") && (!"null".equals(item.getManufacturedcountry().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getManufacturedcountry - NMZC004002PMsg.no(0).madeInCtryCd_S - " + item.getManufacturedcountry());
					pmsg.NMZC004002PMsg.no(0).madeInCtryCd_S.setValue(item.getManufacturedcountry()); //"AR"
				}
				if ((item.getAssembledcountry() !=null) && (item.getAssembledcountry()!="") && (!"null".equals(item.getAssembledcountry().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getAssembledcountry - NMZC004002PMsg.no(0).asmInCtryCd_S - " + item.getAssembledcountry());
					pmsg.NMZC004002PMsg.no(0).asmInCtryCd_S.setValue(item.getAssembledcountry()); //"AR"
				}
				if ((item.getNfmcclass() !=null) && (item.getNfmcclass()!="") && (!"null".equals(item.getNfmcclass().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getNfmcclass - NMZC004002PMsg.no(0).nmfcClsTpCd_D - " + item.getNfmcclass());
					pmsg.NMZC004002PMsg.no(0).nmfcClsTpCd_D.setValue(item.getNfmcclass());
				}
				if ((item.getRmaallowed() !=null) && (item.getRmaallowed()!="") && (!"null".equals(item.getRmaallowed().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getRmaallowed - NMZC004002PMsg.no(0).rmaReqTpCd_M - " + item.getRmaallowed());
					pmsg.NMZC004002PMsg.no(0).rmaReqTpCd_M.setValue(item.getRmaallowed()); //"AR"
				}
				if ((item.getRmainspectionrequired() !=null) && (item.getRmainspectionrequired()!="") && (!"null".equals(item.getRmainspectionrequired().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getRmainspectionrequired - NMZC004002PMsg.no(0).rmaInspReqFlg_D - " + item.getRmainspectionrequired());
					pmsg.NMZC004002PMsg.no(0).rmaInspReqFlg_D.setValue(item.getRmainspectionrequired()); //"AR"
				}
				if ((item.getDefaultsourcewarehouse() !=null) && (item.getDefaultsourcewarehouse()!="") && (!"null".equals(item.getDefaultsourcewarehouse().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getDefaultsourcewarehouse - NMZC004002PMsg.no(0).defSrcWhCd_D - " + item.getDefaultsourcewarehouse());
					pmsg.NMZC004002PMsg.no(0).defSrcWhCd_D.setValue(item.getDefaultsourcewarehouse()); //"AR"
				}
				if ((item.getDefaultsourcesubwarehouse() !=null) && (item.getDefaultsourcesubwarehouse()!="") && (!"null".equals(item.getDefaultsourcesubwarehouse().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getDefaultsourcesubwarehouse - NMZC004002PMsg.no(0).defSrcSubWhCd_D - " + item.getDefaultsourcesubwarehouse());
					pmsg.NMZC004002PMsg.no(0).defSrcSubWhCd_D.setValue(item.getDefaultsourcesubwarehouse()); //"AR"
				}
				if ((item.getSerialcontrol() !=null) && (!"null".equals(item.getSerialcontrol())) && (item.getSerialcontrol().equals("ONRECEIPTANDSHIPMENT")))
				{	
					System.out.println("createServicerequest -	ReveivingSerialFlag - NMZC004002PMsg.no(0).rcvSerTakeFlg_M - " + "Y");					
					pmsg.NMZC004002PMsg.no(0).rcvSerTakeFlg_M.setValue("Y");
					pmsg.NMZC004002PMsg.no(0).shpgSerTakeFlg_M.setValue("Y");
				}
				if ((item.getSerialcontrol() !=null) && (!"null".equals(item.getSerialcontrol())) && (item.getSerialcontrol().equals("ONSHIPMENT")))
				{	
					System.out.println("createServicerequest -	ReveivingSerialFlag - NMZC004002PMsg.no(0).shpgSerTakeFlg_M - " + "Y");
					pmsg.NMZC004002PMsg.no(0).shpgSerTakeFlg_M.setValue("Y");
					pmsg.NMZC004002PMsg.no(0).rcvSerTakeFlg_M.setValue("N");
				}	
				if ((item.getSerialcontrol() !=null) && (!"null".equals(item.getSerialcontrol())) && (item.getSerialcontrol().equals("NOTCONTROLLED")))
				{	
					System.out.println("createServicerequest -	ReveivingSerialFlag - NMZC004002PMsg.no(0).shpgSerTakeFlg_M - " + "Y");
					pmsg.NMZC004002PMsg.no(0).shpgSerTakeFlg_M.setValue("N");
					pmsg.NMZC004002PMsg.no(0).rcvSerTakeFlg_M.setValue("N");
				}	
				
				if ((item.getSerialfrom() !=null) && (item.getSerialfrom()!="") && (!"null".equals(item.getSerialfrom().toLowerCase()))) 
				{	
					System.out.println("createServicerequest -	getSerialfrom() - NMZC004002PMsg.no(0).xxSerNumList.no(0).mdseCd - " + item.getItemnumber());
					System.out.println("createServicerequest -	getSerialfrom() - NMZC004002PMsg.no(0).xxSerNumList.no(0).fromSerNum - " + item.getSerialfrom());
					System.out.println("createServicerequest -	getSerialfrom() - NMZC004002PMsg.no(0).xxSerNumList.no(0).thruSerNum - " + item.getSerialto());
					pmsg.NMZC004002PMsg.no(0).xxSerNumList.no(0).mdseCd.setValue(item.getItemnumber());
					pmsg.NMZC004002PMsg.no(0).xxSerNumList.no(0).fromSerNum.setValue(item.getSerialfrom());
					if ((item.getSerialto() !=null) && (item.getSerialto()!="")) 
					{
						pmsg.NMZC004002PMsg.no(0).xxSerNumList.no(0).thruSerNum.setValue(item.getSerialto());
					}
					pmsg.NMZC004002PMsg.no(0).xxSerNumList.setValidCount(1);
				}	
				
				
				if ((item.getMainengine() !=null) && (item.getMainengine()!="") && (!"null".equals(item.getMainengine().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getMainengine - NMZC004002PMsg.no(0).mainMachFlg_D - " + item.getMainengine());
					pmsg.NMZC004002PMsg.no(0).mainMachFlg_D.setValue(item.getMainengine()); 
				}
				if ((item.getCriticality() !=null) && (item.getCriticality()!="") && (!"null".equals(item.getCriticality().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getCriticality - NMZC004002PMsg.no(0).backOrdImpctTpCd_D - " + item.getCriticality());
					pmsg.NMZC004002PMsg.no(0).backOrdImpctTpCd_D.setValue(item.getCriticality()); 
				}
				if ((item.getRemanavailable() !=null) && (item.getRemanavailable()!="") && (!"null".equals(item.getRemanavailable().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getRemanavailable - NMZC004002PMsg.no(0).reMnfAvalFlg_D - " + item.getRemanavailable());
					pmsg.NMZC004002PMsg.no(0).reMnfAvalFlg_D.setValue(item.getRemanavailable()); 
				}
				if ((item.getSowrequired() !=null) && (item.getSowrequired()!="") && (!"null".equals(item.getSowrequired().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getSowrequired - NMZC004002PMsg.no(0).sowReqFlg_D - " + item.getSowrequired());
					pmsg.NMZC004002PMsg.no(0).sowReqFlg_D.setValue(item.getSowrequired()); 
				}

				if ((item.getCustomerordereable() !=null) && (item.getCustomerordereable()!="") && (!"null".equals(item.getCustomerordereable().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getCustomerordereable - NMZC004002PMsg.no(0).custOrdEnblFlg_D - " + item.getCustomerordereable());
					pmsg.NMZC004002PMsg.no(0).custOrdEnblFlg_D.setValue(item.getCustomerordereable()); 
				}				
				if ((item.getMinimumorderquantity() !=null) && (item.getMinimumorderquantity()!="") && (!"null".equals(item.getMinimumorderquantity().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getMinimumorderquantity - NMZC004002PMsg.no(0).cpoMinOrdQty_M - " + item.getMinimumorderquantity());
					pmsg.NMZC004002PMsg.no(0).cpoMinOrdQty_M.setValue(new BigDecimal(item.getMinimumorderquantity())); 
				}				
				if ((item.getMaximumorderquantity() !=null) && (item.getMaximumorderquantity()!="") && (!"null".equals(item.getMaximumorderquantity().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getMaximumorderquantity - NMZC004002PMsg.no(0).cpoMaxOrdQty_M - " + item.getMaximumorderquantity());
					pmsg.NMZC004002PMsg.no(0).cpoMaxOrdQty_M.setValue(new BigDecimal(item.getMaximumorderquantity())); 
				}				
				if ((item.getOrderincrements() !=null) && (item.getOrderincrements()!="") && (!"null".equals(item.getOrderincrements().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getOrderincrements - NMZC004002PMsg.no(0).cpoIncrOrdQty_M - " + item.getOrderincrements());
					pmsg.NMZC004002PMsg.no(0).cpoIncrOrdQty_M.setValue(new BigDecimal(item.getOrderincrements())); 
				}				
				if ((item.getRemanufactureditemexists() !=null) && (item.getRemanufactureditemexists()!="") && (!"null".equals(item.getRemanufactureditemexists().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getRemanufactureditemexists - NMZC004002PMsg.no(0).reMnfItemExstFlg_D - " + item.getRemanufactureditemexists());
					pmsg.NMZC004002PMsg.no(0).reMnfItemExstFlg_D.setValue(item.getRemanufactureditemexists()); 
				}				
		
				if ((item.getAreaofpaper() !=null) && (item.getAreaofpaper()!="") && (!"null".equals(item.getAreaofpaper().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getAreaofpaper - NMZC004002PMsg.no(0).areaOfPaperNum_D - " + item.getAreaofpaper());
					pmsg.NMZC004002PMsg.no(0).areaOfPaperNum_D.setValue(new BigDecimal(item.getAreaofpaper())); 
				}				

				if ((item.getItembillingtype() !=null) && (item.getItembillingtype()!="") && (!"null".equals(item.getItembillingtype().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getItembillingtype - NMZC004002PMsg.no(0).mdseItemBillTpCd_D - " + item.getItembillingtype());
					pmsg.NMZC004002PMsg.no(0).mdseItemBillTpCd_D.setValue(item.getItembillingtype()); 
				}				
				if ((item.getConfigurationflag() !=null) && (item.getConfigurationflag()!="") && (!"null".equals(item.getConfigurationflag().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getConfigurationflag - NMZC004002PMsg.no(0).configFlg_D - " + item.getConfigurationflag());
					pmsg.NMZC004002PMsg.no(0).configFlg_D.setValue(item.getConfigurationflag());  
				}				
				if ((item.getReturnvendor() !=null) && (item.getReturnvendor()!="") && (!"null".equals(item.getReturnvendor().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getReturnvendor - NMZC004002PMsg.no(0).rtrnToPrntVndCd_D - " + item.getReturnvendor());
					pmsg.NMZC004002PMsg.no(0).rtrnToPrntVndCd_D.setValue(item.getReturnvendor()); 
				}				
				if ((item.getReturnvendorsite() !=null) && (item.getReturnvendorsite()!="") && (!"null".equals(item.getReturnvendorsite().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getReturnvendorsite - NMZC004002PMsg.no(0).rtrnToVndCd_D - " + item.getReturnvendorsite());
					pmsg.NMZC004002PMsg.no(0).rtrnToVndCd_D.setValue(item.getReturnvendorsite()); 
				}				

				//System.out.println("createServicerequest -	getReturnwarehouse - NMZC004002PMsg.no(0).rtrnToVndCd_D - " + item.getReturnwarehouse());				
				if ((item.getReturnwarehouse() !=null) && (item.getReturnwarehouse()!="") && (!"null".equals(item.getReturnwarehouse().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getReturnwarehouse - NMZC004002PMsg.no(0).rtrnToVndCd_D - " + item.getReturnwarehouse());
					pmsg.NMZC004002PMsg.no(0).rtrnWhCd_D.setValue(item.getReturnwarehouse()); 
				}				  
				
				if ((item.getReturnsubwarehouse() !=null) && (item.getReturnsubwarehouse()!="") && (!"null".equals(item.getReturnsubwarehouse().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getReturnsubwarehouse - NMZC004002PMsg.no(0).rtrnDsplTpCd_D - " + item.getReturnsubwarehouse());
					pmsg.NMZC004002PMsg.no(0).rtrnDsplTpCd_D.setValue(item.getReturnsubwarehouse()); 
				}				  

				if ((item.getHardallocationtype() !=null) && (item.getHardallocationtype()!="") && (!"null".equals(item.getHardallocationtype().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getHardallocationtype - NMZC004002PMsg.no(0).invtyHardAllocTpCd_M - " + item.getHardallocationtype());
					pmsg.NMZC004002PMsg.no(0).invtyHardAllocTpCd_M.setValue(item.getHardallocationtype()); 
				}				

				if ((item.getDefaultsourcetype() !=null) && (item.getDefaultsourcetype()!="") && (!"null".equals(item.getDefaultsourcetype().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getDefaultsourcetype - NMZC004002PMsg.no(0).defSrcProcrTpCd_D - " + item.getDefaultsourcetype());
					pmsg.NMZC004002PMsg.no(0).defSrcProcrTpCd_D.setValue(item.getDefaultsourcetype()); 
				}				

				if ((item.getEasypacki() !=null) && (item.getEasypacki()!="")  && (!"null".equals(item.getEasypacki().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getEasypacki - NMZC004002PMsg.no(0).easyPackTpCd_D - " + item.getEasypacki());
					pmsg.NMZC004002PMsg.no(0).easyPackTpCd_D.setValue(item.getEasypacki()); 
				}				

				if ((item.getAssetrecoverycost() !=null) && (item.getAssetrecoverycost()!="") && (!"null".equals(item.getAssetrecoverycost().toLowerCase())))
				{	
					System.out.println("createServicerequest -	getAssetrecoverycost() - NMZC004002PMsg.no(0).assetRecovCostAmt_D - " + item.getAssetrecoverycost());
					pmsg.NMZC004002PMsg.no(0).assetRecovCostAmt_D.setValue(new BigDecimal(item.getAssetrecoverycost())); 
				}				
				
			
				if ((item.getStandardcost() !=null) && (item.getStandardcost()!="") && (item.getOwningdivision() !=null) && (item.getOwningdivision() !=""))
				{
					System.out.println("createServicerequest -	purchasePrice() - NMZC004002PMsg.no(0).origFobAmt_D - " + item.getStandardcost());
					pmsg.NMZC004002PMsg.no(0).origFobAmt_D.setValue(new BigDecimal(item.getStandardcost()));
					String priceListName1="";
					String priceListName2="";
					resultPriceList = getPriceListName(item.getOwningdivision());
					if (resultPriceList != null && resultPriceList.length > 0) {
						System.out.println("createServicerequest - getting the Price List records ");
						
						priceList = (List)CanonE008ItemProcessUtil.first(resultPriceList);
						
						for(int jj=0;jj<priceList.size();jj++) 
						{
							if(jj==0)
								priceListName1 = (String) priceList.get(jj);
							
							if(jj==1)
								priceListName2 = (String) priceList.get(jj);
							
						}
						
					}
					
					if ((priceListName1 != null) && (item.getMsrp() !=null) && (item.getMsrp()!="")) {
						System.out.println("createServicerequest -	MSRPPriceListName() - NMZC004002PMsg.no(0).prcCatgCd_P1 - " + priceListName1);  //"0000003"
						System.out.println("createServicerequest -	MSRPPrice() - NMZC004002PMsg.no(0).prcListEquipPrcAmt_P1 - " + item.getMsrp());
						pmsg.NMZC004002PMsg.no(0).prcCatgCd_P1.setValue(priceListName1);
						pmsg.NMZC004002PMsg.no(0).prcListEquipPrcAmt_P1.setValue(new BigDecimal(item.getMsrp()));
					}
					
					//if ((priceListName2 != null)  && (item.getStandardcost() !=null) && (item.getStandardcost()!="")) {
					if ((priceListName2 != null)  && (item.getStandardcost() !=null) && (item.getStandardcost()!="")) {	
						System.out.println("createServicerequest -	FloorPriceListName() - NMZC004002PMsg.no(0).prcCatgCd_P2 - " + priceListName2 ); //"1001151"
						System.out.println("createServicerequest -	FloorPrice() - NMZC004002PMsg.no(0).prcListEquipPrcAmt_P2 - " + item.getStandardcost());
						pmsg.NMZC004002PMsg.no(0).prcCatgCd_P2.setValue(priceListName2);
						pmsg.NMZC004002PMsg.no(0).prcListEquipPrcAmt_P2.setValue(new BigDecimal(item.getStandardcost()));
					}
					
					
				}	

				if ((item.getSupplier() !=null) && (item.getSupplier()!="") && (item.getSupplierSite() !=null) && (item.getSupplierSite()!="") 
						&& (item.getStandardcost() !=null) && (item.getStandardcost()!="")) 
				{
					aslNameStr = getASlName(item.getMerchandisetype(),item.getSupplier(),item.getSupplierSite());
					
					System.out.println("createServicerequest -	getSupplier() - NMZC004002PMsg.no(0).xxAslList.no(0).prntVndCd - " + item.getSupplier());					
					System.out.println("createServicerequest -	getASLName() - NMZC004002PMsg.no(0).xxAslList.no(0).aslNm - " + aslNameStr);
					System.out.println("createServicerequest -	getASLName() - NMZC004002PMsg.no(0).xxAslList.no(0).vndLtDaysNum - " + item.getLeadTime());
					System.out.println("createServicerequest -	getItemnumber() - NMZC004002PMsg.no(0).xxAslList.no(0).mdseCd - " + item.getItemnumber());
					System.out.println("createServicerequest -	getSupplierSite() - NMZC004002PMsg.no(0).xxAslList.no(0).vndCd - " + item.getSupplierSite());
					System.out.println("createServicerequest -	getSupplierItem() - NMZC004002PMsg.no(0).xxAslList.no(0).splyItemNum - " + item.getSupplierItem());
					System.out.println("createServicerequest -	getStandardcost() - NMZC004002PMsg.no(0).xxAslList.no(0).unitPrcAmt - " + item.getStandardcost());
					
					if (aslNameStr != null) {
						pmsg.NMZC004002PMsg.no(0).xxAslList.no(0).prntVndCd.setValue(item.getSupplier());   //item.getSupplier()
						pmsg.NMZC004002PMsg.no(0).xxAslList.no(0).aslNm.setValue(aslNameStr);
						if ((item.getLeadTime() !=null) && (item.getLeadTime()!=""))
						{
							pmsg.NMZC004002PMsg.no(0).xxAslList.no(0).vndLtDaysNum.setValue(new BigDecimal(item.getLeadTime()));
						}
						
						pmsg.NMZC004002PMsg.no(0).xxAslList.no(0).mdseCd.setValue(item.getItemnumber());    //item.getItemnumber()
						pmsg.NMZC004002PMsg.no(0).xxAslList.no(0).vndCd.setValue(item.getSupplierSite());  //item.getSupplierSite()
						if ((item.getSupplierItem() !=null) && (item.getSupplierItem()!=""))
						{
							pmsg.NMZC004002PMsg.no(0).xxAslList.no(0).splyItemNum.setValue(item.getSupplierItem());  //item.getSupplierItem()
						}
						
						pmsg.NMZC004002PMsg.no(0).xxAslList.no(0).unitPrcAmt.setValue(new BigDecimal(item.getStandardcost()));
						pmsg.NMZC004002PMsg.no(0).xxAslList.setValidCount(1);
					}
					
				}
				
				if ((item.getSupersededBy() !=null) && (item.getSupersededBy()!="") && (!"null".equals(item.getSupersededBy().toLowerCase())))
				{
					System.out.println("createServicerequest -	getSuperSeededItemnumber() - NMZC004002PMsg.no(0).xxSupdList.no(0).mdseCd - " + item.getItemnumber());
					System.out.println("createServicerequest -	getSupperseededTo() - NMZC004002PMsg.no(0).xxSupdList.no(0).supdFromMdseCd - " + item.getItemnumber());
					System.out.println("createServicerequest -	getSupperseededTo() - NMZC004002PMsg.no(0).xxSupdList.no(0).supdToMdseCd - " + item.getSupersededBy());

					pmsg.NMZC004002PMsg.no(0).xxSupdList.no(0).mdseCd.setValue(item.getItemnumber());
					pmsg.NMZC004002PMsg.no(0).xxSupdList.no(0).supdFromMdseCd.setValue(item.getItemnumber());
					pmsg.NMZC004002PMsg.no(0).xxSupdList.no(0).supdToMdseCd.setValue(item.getSupersededBy());
					pmsg.NMZC004002PMsg.no(0).xxSupdList.setValidCount(1); 
				}	
					
				/////////////=============================*********///////////////////////////////////////	
				
				System.out.println("createServicerequest -	mdseCstUpdEffFromDt_D - NMZC004002PMsg.no(0).mdseCstUpdEffFromDt_D - " + currentDt);
				pmsg.NMZC004002PMsg.no(0).mdseCstUpdEffFromDt_D.setValue(currentDt);  
				
				if ((item.getItemnumber() !=null) && (item.getItemnumber()!=""))
				{
					System.out.println("createServicerequest - getItemnumber() - NMZC004002PMsg.no(0).mdseCd_S - " + item.getItemnumber());
					System.out.println("createServicerequest - getItemnumber() - NMZC004002PMsg.no(0).mdseCd_D - " + item.getItemnumber());
					pmsg.NMZC004002PMsg.no(0).mdseCd_S.setValue(item.getItemnumber());   //--> (B6).  Please add this value for Safety Info.
					pmsg.NMZC004002PMsg.no(0).mdseCd_D.setValue(item.getItemnumber());
				}

				
				if ((item.getItemnumber() !=null) && (item.getItemnumber()!=""))
				{
					System.out.println("createServicerequest -	xxStoreList getItemnumber - NMZC004002PMsg.no(0).xxStoreList.no(0).mdseCd_S - " + item.getItemnumber());
					System.out.println("createServicerequest -	xxStoreList getItemnumber - NMZC004002PMsg.no(0).xxStoreList.no(0).inEachQty_S - " + "1");
					System.out.println("createServicerequest -	xxStoreList getItemnumber - NMZC004002PMsg.no(0).xxStoreList.no(0).primPkgUomFlg_DS - " + "Y");
					
					pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).mdseCd_S.setValue(item.getItemnumber());
					pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inEachQty_S.setValue(new BigDecimal(1));
					pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).primPkgUomFlg_DS.setValue("Y");
					
					//if ((item.getItemlength() !=null) && (item.getItemlength()!="") && (!"null".equals(item.getItemlength().toLowerCase())))
					//{
						System.out.println("createServicerequest -	getItemlength - NMZC004002PMsg.no(0).xxStoreList.no(0).inInchLg_S -  " + item.getItemlength());
						System.out.println("createServicerequest -	getItemlength - NMZC004002PMsg.no(0).xxStoreList.no(0).pkgUomCd_S - " + item.getUom());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).pkgUomCd_S.setValue(item.getUom()); //item.getUom()
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inInchLg_S.setValue(new BigDecimal(item.getItemlength()));
						//pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inInchLgUomCd_S.setValue("IN");
			             
					//}

					
					//if ((item.getHeight() !=null) && (item.getHeight()!="") && (!"null".equals(item.getHeight().toLowerCase())))
					//{
						System.out.println("createServicerequest -	getHeight - NMZC004002PMsg.no(0).xxStoreList.no(0).inInchHgt_S - " + item.getHeight());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inInchHgt_S.setValue(new BigDecimal(item.getHeight()));
						//pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inInchHgtUomCd_S.setValue("IN");
					//}
					
					//if ((item.getItemdepth() !=null) && (item.getItemdepth()!="") && (!"null".equals(item.getItemdepth().toLowerCase())))
					//{
						System.out.println("createServicerequest -	getItemdepth - NMZC004002PMsg.no(0).xxStoreList.no(0).inInchWdt_S - " + item.getItemdepth());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inInchWdt_S.setValue(new BigDecimal(item.getItemdepth()));
						//pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inInchWdtUomCd_S.setValue("IN");
					//}	
					
					//if ((item.getWeight() !=null) && (item.getWeight()!="") && (!"null".equals(item.getWeight().toLowerCase())))
					//{
						
						System.out.println("createServicerequest -	getWeight - NMZC004002PMsg.no(0).xxStoreList.no(0).inPoundWt_S - " + item.getWeight());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inPoundWt_S.setValue(new BigDecimal(item.getWeight()));
						//pmsg.NMZC004002PMsg.no(0).xxStoreList.no(0).inPoundWtUomCd_S.setValue("LB");
					//}
					
					
					//System.out.println("createServicerequest -	getUnboxedlength - NMZC004002PMsg.no(0).xxStoreList.no(0).inInchLg_S -  " + item.getUnboxedlength());
					/*----Added--fo--Unboxed--Items--------------------------------------*/
					//if ((item.getUnboxedlength() !=null) && (item.getUnboxedlength()!="") && (!"null".equals(item.getUnboxedlength().toLowerCase())))
					//{
						System.out.println("createServicerequest -	getUnboxedlength - NMZC004002PMsg.no(0).xxStoreList.no(1).pkgUomCd_S - UB " );
						System.out.println("createServicerequest -	getUnboxedlength - NMZC004002PMsg.no(0).xxStoreList.no(1).inInchLg_S -  " + item.getUnboxedlength());
						System.out.println("createServicerequest -	getUnboxedlength - NMZC004002PMsg.no(0).xxStoreList.no(1).inInchLgUomCd_S -  " + item.getUnboxedlengthuom());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).mdseCd_S.setValue(item.getItemnumber());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inEachQty_S.setValue(new BigDecimal(1));
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).pkgUomCd_S.setValue("UB");
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inInchLg_S.setValue(new BigDecimal(item.getUnboxedlength()));
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inInchLgUomCd_S.setValue(item.getUnboxedlengthuom());
			             
					//}
					//if ((item.getUnboxedheight() !=null) && (item.getUnboxedheight()!="") && (!"null".equals(item.getUnboxedheight().toLowerCase())))
					//{
						System.out.println("createServicerequest -	getUnboxedheight - NMZC004002PMsg.no(0).xxStoreList.no(1).inInchHgt_S - " + item.getUnboxedheight());
						System.out.println("createServicerequest -	getUnboxedheight - NMZC004002PMsg.no(0).xxStoreList.no(1).inInchHgtUomCd_S - " + item.getUnboxedheightuom());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inInchHgt_S.setValue(new BigDecimal(item.getUnboxedheight()));
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inInchHgtUomCd_S.setValue(item.getUnboxedheightuom());
					//}
					//if ((item.getUnboxedwidth() !=null) && (item.getUnboxedwidth()!="") && (!"null".equals(item.getUnboxedwidth().toLowerCase())))
					//{
						System.out.println("createServicerequest -	getUnboxedwidth - NMZC004002PMsg.no(0).xxStoreList.no(1).inInchWdt_S - " + item.getUnboxedwidth());
						System.out.println("createServicerequest -	getUnboxedwidth - NMZC004002PMsg.no(0).xxStoreList.no(1).inInchWdtUomCd_S - " + item.getUnboxedwidthuom());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inInchWdt_S.setValue(new BigDecimal(item.getUnboxedwidth()));
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inInchWdtUomCd_S.setValue(item.getUnboxedwidthuom());
					//}	
					//if ((item.getUnboxedweight() !=null) && (item.getUnboxedweight()!="") && (!"null".equals(item.getUnboxedweight().toLowerCase())))
					//{
						System.out.println("createServicerequest -	getUnboxedweight - NMZC004002PMsg.no(0).xxStoreList.no(1).inPoundWt_S - " + item.getUnboxedweight());
						System.out.println("createServicerequest -	getUnboxedweight - NMZC004002PMsg.no(0).xxStoreList.no(1).inPoundWtUomCd_S - " + item.getUnboxedweightuom());
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inPoundWt_S.setValue(new BigDecimal(item.getUnboxedweight()));
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).inPoundWtUomCd_S.setValue(item.getUnboxedweightuom());
						
						pmsg.NMZC004002PMsg.no(0).xxStoreList.no(1).primPkgUomFlg_DS.setValue("Y");
					//}	
					/*------------------------------------------*/					
					
					
					pmsg.NMZC004002PMsg.no(0).xxStoreList.setValidCount(2);
				}	
				
				
				if ((item.getItemtype().equals("10")) || (item.getItemtype().equals("11"))) 
				{ 
					System.out.println("createServicerequest - SET/KIT Item " + item.getProjitemid());
					resultbom = getbomItemsDetails(item.getProjectid(),item.getProjitemid());
					bomitemlist = (List)CanonE008ItemProcessUtil.first(resultbom);
					cntbomrecord=bomitemlist.size();
					System.out.println("createServicerequest - cntbomrecord-" + cntbomrecord);
					
					for(int p=0;p<bomitemlist.size();p++) 
					{
						CanonE008CallItemCreateAPI.bomInfo bomitem = (CanonE008CallItemCreateAPI.bomInfo) bomitemlist.get(p);
						System.out.println("createServicerequest - bomitem.getParentItem() - NMZC004002PMsg.no(0).xxBomList.no(p).prntMdseCd_C - " + bomitem.getParentItem());
						System.out.println("createServicerequest - bomitem.getCodeOrgLevel() - NMZC004002PMsg.no(0).xxBomList.no(p).mdseCmpsnTpCd_C - " + bomitem.getCodeOrgLevel());
						System.out.println("createServicerequest - bomitem.getCompItem() - NMZC004002PMsg.no(0).xxBomList.no(p).childMdseCd_C - " + bomitem.getCompItem());
						System.out.println("createServicerequest - bomitem.getQty() - NMZC004002PMsg.no(0).xxBomList.no(p).childMdseQty_C - " + bomitem.getQty());
						System.out.println("createServicerequest - bomitem.currentDt - NMZC004002PMsg.no(0).xxBomList.no(p).effFromDt_C - " + currentDt);
						System.out.println("createServicerequest - bomitem.getUom() - NMZC004002PMsg.no(0).xxBomList.no(p).pkgUomCd_DC - " + bomitem.getUom());
						
						pmsg.NMZC004002PMsg.no(0).xxBomList.no(p).prntMdseCd_C.setValue(bomitem.getParentItem());
						pmsg.NMZC004002PMsg.no(0).xxBomList.no(p).mdseCmpsnTpCd_C.setValue(bomitem.getCodeOrgLevel());
						pmsg.NMZC004002PMsg.no(0).xxBomList.no(p).childMdseCd_C.setValue(bomitem.getCompItem());
						//pmsg.NMZC004002PMsg.no(0).xxBomList.no(0).childOrdTakeMdseCd_C.setValue(val);
						pmsg.NMZC004002PMsg.no(0).xxBomList.no(p).childMdseQty_C.setValue(new BigDecimal(bomitem.getQty()));
						pmsg.NMZC004002PMsg.no(0).xxBomList.no(p).effFromDt_C.setValue(currentDt);
						pmsg.NMZC004002PMsg.no(0).xxBomList.no(p).pkgUomCd_DC.setValue(bomitem.getUom());
						
					}	
					if (bomitemlist.size()!=0)
						pmsg.NMZC004002PMsg.no(0).xxBomList.setValidCount(bomitemlist.size());
				}
				
				
				
				pmsg.NMZC004002PMsg.no(0).cstmReqFlg_M.setValue("N");
				
				logMsg("Execution Starts  ");
				
				try {
			        System.out.println("####################### Item Create API called #############");
					s21Api.execute(pmsg,ONBATCH_TYPE.BATCH); // execute API
					if (!S21ApiUtil.isXxMsgId(pmsg)) {
						// There is no message id, so can do commit the transaction.
						logMsg(" Project No " + item.getProjectid());
						logMsg(" Item Id " + item.getProjitemid());
						logMsg(" No error, Successfull Created."); 
						EZDConnectionMgr.getInstance().commit(); 
						// commit	
						//logMsg("After commit");
						processStr = UpdateItemStatus(item.getProjectid(),item.getProjitemid(),null,null);	
						logMsg("Update Item Status " + processStr);
											
						resArr[0] = "Success";					
					}
					else {
						StringBuffer sb = new StringBuffer("");
						// Error Case - S21API set some error message id when got any
						// error while S21API's function.
						List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
						for (String msg : msgList) {
							logMsg("ERROR MESSAGE : "
									+ S21MessageFunc.clspGetMessage(msg));					
							sb.append("ERROR : " + "\n");
						}
						//List<String> errList= S21ApiUtil.getXxMsgIdList(pmsg.xxErrItemList.no(0));
						logMsg(" EXCEP Project No " + item.getProjectid());
						logMsg(" EXCEP Item Id " + item.getProjitemid());
						System.out.println("ERROR CODES 2:"+pmsg.xxErrItemList.no(0).mdseCd.getValue());
						System.out.println("ERROR CODES 1:"+pmsg.xxErrItemList.no(0).appMsgTxt.getValue());				

						resArr[0] = "ERROR : ";
						resArr[1] = sb.toString();
						// If S21API got error, roll-back the transaction.
						EZDConnectionMgr.getInstance().rollback();
						
						processStr = UpdateItemStatus(item.getProjectid(),item.getProjitemid(),pmsg.xxErrItemList.no(0).mdseCd.getValue() ,pmsg.xxErrItemList.no(0).appMsgTxt.getValue() );
						logMsg("Update Item Status " + processStr);	
						
						// roll-back
						// throw new
						// S21AbendException("S21AbendException is thrown...");
					}	
					
					 
				} catch (Exception e) {
					logMsg("ERROR MESSAGE : " + e.getMessage());
					e.printStackTrace();
				} finally { // Release DB resource (Close DB Connection)
					//EZDConnectionMgr.getInstance().releaseResource();
					
				}
				
				//return resArr;	
				
				} 
				
				EZDConnectionMgr.getInstance().releaseResource();
		}
		
	}

     
    public static interface RowMapper {
        Object mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

    private static List cursorToList(ResultSet cursor, oracle.apps.e008.server.CanonE008ItemRec.RowMapper rowMapper) {
        List list = new ArrayList();
        try {
            while (cursor.next()) {
                list.add(rowMapper.mapRow(cursor, 0));
            }
        } catch (SQLException ex) {
            //saveException(ex);
            ex.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    private static List cursorTobomList(ResultSet cursor, RowMapper rowMapper) {
        List list = new ArrayList();
        try {
            while (cursor.next()) {
                list.add(rowMapper.mapRow(cursor, 0));
            }
        } catch (SQLException ex) {
            //saveException(ex);
            ex.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    
    //public  void submitProjectCreationExtract(String p_project_no){
   public static Object[] getProjectItemsDetails(BigDecimal p_project_no){    
    	System.out.println("in getProjectItemsDetails ");
        CallableStatement statement = null;
        Connection connection = null;
        //System.out.println("in getProjectItemsDetails 2");
        try {
        	//System.out.println("in getProjectItemsDetails 3");
        	connection = TransactionScope.getConnection();
        	//connection = parts.dbcommon.EZDConnectionMgr.getInstance().getConnection();
            //System.out.println("connection:"+connection);
            if (connection != null) {
            	
            	System.out.println("Inside connection");
            	statement = (CallableStatement) connection.prepareCall(GET_PROJECT_ITEMS_DETAILS);
                if (statement != null) {
             	    //System.out.println("in getProjectItemsDetails 1 "+p_project_no);
                    statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                    //System.out.println("in getProjectItemsDetails 2 "+p_project_no);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    //System.out.println("in getProjectItemsDetails 3 "+p_project_no);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    //System.out.println("in getProjectItemsDetails 4 "+p_project_no);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    //System.out.println("in getProjectItemsDetails 5 "+p_project_no);
                    statement.execute();
                    System.out.println("Executed getProjectItemsDetails");
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),CanonE008ItemRec.getRowMapper())
 				                       ,statement.getObject(3)
 				                       ,statement.getObject(4)};
                    
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    TransactionScope.releaseConnection(connection);
                } catch (Exception ex) {
                    return null; 
                    //ex.printStackTrace();
                }
            }

        } return null;
   }
    

   public static Object[] getbomItemsDetails(BigDecimal p_project_no,BigDecimal p_set_kit_itemid){    
   	System.out.println("in getbomItemsDetails Project"+p_project_no);
   	System.out.println("in getbomItemsDetails SetKit"+p_set_kit_itemid);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           System.out.println("connection:"+connection);
           if (connection != null) {
           	
           	
           	statement = (CallableStatement) connection.prepareCall(GET_BOM_ITEMS_DETAILS);
               if (statement != null) {
            	   //System.out.println("in getbomItemsDetails 1 "+p_project_no);
                   statement.setObject(1, p_project_no, OracleTypes.NUMBER);
            	   //System.out.println("in getbomItemsDetails 1 "+p_project_no);
                   statement.setObject(2, p_set_kit_itemid, OracleTypes.NUMBER);
                   //System.out.println("in getbomItemsDetails 2 "+p_project_no);
                   statement.registerOutParameter(3, OracleTypes.CURSOR);
                   //System.out.println("in getbomItemsDetails 3 "+p_project_no);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   //System.out.println("in getbomItemsDetails 4 "+p_project_no);
                   statement.registerOutParameter(5, OracleTypes.VARCHAR);
                   //System.out.println("in getbomItemsDetails 5 "+p_project_no);
                   statement.execute();
                   System.out.println("Executed getbomItemsDetails 6 "+p_project_no);
                   return new Object[]{cursorTobomList((ResultSet)statement.getObject(3),bomInfo.getRowMapper())
				                       ,statement.getObject(4)
				                       ,statement.getObject(5)};
                   
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       } finally {
           if (statement != null) {
               try {
                   statement.close();
                   statement = null;
               } catch (Exception exp) {
                   exp.printStackTrace();
               }
           }
           if (connection != null) {
               try {
                   TransactionScope.releaseConnection(connection);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }

       } return null;
  }

   
   public static String UpdateItemStatus(BigDecimal p_project_no, BigDecimal p_item_id, String p_error_code, String p_error_mess){
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
           	
               statement = (CallableStatement) connection.prepareCall(UPDATE_PROJECT_ITEMS_STATUS);
               if (statement != null) {
                   statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                   statement.setObject(2, p_item_id, OracleTypes.NUMBER);
                   statement.setObject(3, p_error_code, OracleTypes.VARCHAR);
                   statement.setObject(4, p_error_mess, OracleTypes.VARCHAR);
                   statement.registerOutParameter(5,OracleTypes.VARCHAR);   
                   statement.execute();        
                   String getprocessmsg=statement.getString(5);
                   return getprocessmsg;

               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       } finally {
           if (statement != null) {
               try {
                   statement.close();
                   statement = null;
               } catch (Exception exp) {
                   exp.printStackTrace();
               }
           }
           if (connection != null) {
               try {
                   TransactionScope.releaseConnection(connection);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }

       } return null;
  }   
   
   public static String getASlName(String p_merch_type,String p_supplier_code,String p_supplier_site_code){
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
           	
               statement = (CallableStatement) connection.prepareCall(GET_ASL_NAME);
               if (statement != null) {
                   statement.setObject(1, p_merch_type, OracleTypes.VARCHAR);
                   statement.setObject(2, p_supplier_code, OracleTypes.VARCHAR);
                   statement.setObject(3, p_supplier_site_code, OracleTypes.VARCHAR);
                   statement.registerOutParameter(4,OracleTypes.VARCHAR);  
                   statement.registerOutParameter(5,OracleTypes.VARCHAR);  
                   statement.registerOutParameter(6,OracleTypes.VARCHAR);   
                   statement.execute();        
                   String getaslName=statement.getString(4);
                   return getaslName;

               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       } finally {
           if (statement != null) {
               try {
                   statement.close();
                   statement = null;
               } catch (Exception exp) {
                   exp.printStackTrace();
               }
           }
           if (connection != null) {
               try {
                   TransactionScope.releaseConnection(connection);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }

       } return null;
  }   

   public static Object[] getPriceListName(String p_owner){    
	       CallableStatement statement = null;
	       Connection connection = null;
	       try {
	           connection = TransactionScope.getConnection();
	           //System.out.println("connection:"+connection);
	           if (connection != null) {
	           	
	           	
	           	statement = (CallableStatement) connection.prepareCall(GET_PRICELIST_NAME);
	               if (statement != null) {
	            	   statement.setObject(1, p_owner, OracleTypes.VARCHAR);
	            	   statement.registerOutParameter(2, OracleTypes.CURSOR);
	                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
	                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
	                   statement.execute();
	                   return new Object[]{cursorTobomList((ResultSet)statement.getObject(2),stringRowMapper())
					                       ,statement.getObject(3)
					                       ,statement.getObject(4)};
	                   
	               } else {
	                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	               }
	           } else {
	               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	           }
	       } catch (Exception ex) {
	           ex.printStackTrace();
	       } finally {
	           if (statement != null) {
	               try {
	                   statement.close();
	                   statement = null;
	               } catch (Exception exp) {
	                   exp.printStackTrace();
	               }
	           }
	           if (connection != null) {
	               try {
	                   TransactionScope.releaseConnection(connection);
	               } catch (Exception ex) {
	                   ex.printStackTrace();
	               }
	           }

	       } return null;
	  }


   public static RowMapper stringRowMapper() {
       return new RowMapper() {
           public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
               return rs.getString(1);
           }
       };
   }
   
   public static Object first(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 1 ? null : objs[0];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 1 ? null : l.get(0);
        }
        return null;
    }

    public static Object second(Object obj) {
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length < 2 ? null : objs[1];
        } else if (obj instanceof List) {
            List l = (List) obj;
            return l.size() < 2 ? null : l.get(1);
        }
        return null;
    }

    
    public static class bomInfo {
        private String parentItem;
        private String codeOrgLevel;
        private String compItem;
        private String qty;
        private String uom;

         public bomInfo(){
         }

         public bomInfo(String parentItem, String codeOrgLevel,
				String compItem, String qty, String uom) {
			super();
			this.parentItem = parentItem;
			this.codeOrgLevel = codeOrgLevel;
			this.compItem = compItem;
			this.qty = qty;
			this.uom = uom;
		}

        public String getParentItem() {
			return parentItem;
		}

		public void setParentItem(String parentItem) {
			this.parentItem = parentItem;
		}

		public String getCodeOrgLevel() {
			return codeOrgLevel;
		}

		public void setCodeOrgLevel(String codeOrgLevel) {
			this.codeOrgLevel = codeOrgLevel;
		}

		public String getCompItem() {
			return compItem;
		}

		public void setCompItem(String compItem) {
			this.compItem = compItem;
		}

		public String getQty() {
			return qty;
		}

		public void setQty(String qty) {
			this.qty = qty;
		}

		public String getUom() {
			return uom;
		}

		public void setUom(String uom) {
			this.uom = uom;
		}

		public static RowMapper getRowMapper(){
             return new RowMapper() {
                 public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                     return new bomInfo(
                         rs.getString("PARENT_ITEM"),
                         rs.getString("CODE_ORGANIZATION_LEVEL"),
                         rs.getString("COMPONENT_ITEM"),
                         rs.getString("QTY"),
                         rs.getString("UOM")
                     );
                 }
             };
         }

    }
    
       
    public static void main(String args[]) throws Exception {
    	String projectNumber = ""; 
    	String inputParam1=System.getenv("VAR_INPUT_PARAM1");
    	if(inputParam1==null || inputParam1.trim().length()==0)
    	inputParam1="No Value Provided";
    	else
    		projectNumber=inputParam1;
    	
    	System.out.println("OrderNumber:"+projectNumber);
    	CanonE008CallItemCreateAPI obj=new CanonE008CallItemCreateAPI();
    	obj.createServicerequest(new BigDecimal(projectNumber));
    	System.out.println("CanonE008CallItemCreateAPI called:");
    	
    }

    
}
