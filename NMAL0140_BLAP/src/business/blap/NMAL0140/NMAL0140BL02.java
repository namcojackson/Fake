package business.blap.NMAL0140;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0140.NMAL0140CMsg;
import business.blap.NMAL0140.NMAL0140SMsg;
import business.blap.NMAL0140.common.NMAL0140CommonLogic;
import business.blap.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 *</pre>
 */
public class NMAL0140BL02 extends S21BusinessHandler implements NMAL0140Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
		NMAL0140CMsg bizMsg = (NMAL0140CMsg) cMsg;
		String scrnAplID = bizMsg.getScreenAplID();
        try {
        	if ("NMAL0140_INIT".equals(scrnAplID)) {
        		doProcess_NMAL0140_INIT(bizMsg, (NMAL0140SMsg)sMsg);
        	} else if ("NMAL0140Scrn00_CMN_Clear".equals(scrnAplID)) {
        		doProcess_NMAL0140_CLEAR(bizMsg, (NMAL0140SMsg)sMsg);
        	} else if ("NMAL0140Scrn00_CMN_Reset".equals(scrnAplID)) {
        		doProcess_NMAL0140_RESET(bizMsg, (NMAL0140SMsg)sMsg);
        	} else if ("NMAL0140Scrn00_Search".equals(scrnAplID)) {
        		doProcess_NMAL0140_Search(bizMsg, (NMAL0140SMsg)sMsg);
        	} else if ("NMAL0140Scrn00_PageJump".equals(scrnAplID)) {
        		doProcess_NMAL0140_PageJump(bizMsg, (NMAL0140SMsg)sMsg);	        		
        	} else if ("NMAL0140Scrn00_PagePrev".equals(scrnAplID)) {
        		doProcess_NMAL0140_PagePrev(bizMsg, (NMAL0140SMsg)sMsg);
        	} else if ("NMAL0140Scrn00_PageNext".equals(scrnAplID)) {
        		doProcess_NMAL0140_PageNext(bizMsg, (NMAL0140SMsg)sMsg);
            } else if ("NMAL0140Scrn00_TBLColumnSort".equals(scrnAplID)) {
            	doProcess_NMAL0140_TBLColumnSort(bizMsg, (NMAL0140SMsg)sMsg);
            } else if ("NMAL0140Scrn00_OnChangeSavedSearchOption".equals(scrnAplID)) {
                doProcess_NMAL0140_OnChangeSavedSearchOption(bizMsg, (NMAL0140SMsg) sMsg);
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
	
	private void doProcess_NMAL0140_INIT(NMAL0140CMsg bizMsg, NMAL0140SMsg sMsg) {

        //Setup select box 
        NMAL0140CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
        
		//Setup select box
		//Saved Search Options
    	bizMsg.xxLgcyOrdTpNm_H1.clear();
    	bizMsg.xxLgcyOrdTpNm_CD.clear();
    	bizMsg.xxLgcyOrdTpNm_NM.clear();
    	bizMsg.xxLgcyOrdTpNm_CD.no(0).setValue("1");
    	bizMsg.xxLgcyOrdTpNm_NM.no(0).setValue("Default");

    	//Cost Type
        bizMsg.mdseCstUpdTpCd_H1.clear();
        bizMsg.mdseCstUpdTpCd_L1.clear();
        bizMsg.mdseCstUpdTpNm_L1.clear();
        NMAL0140CommonLogic.setInitialCostTypePulldown(bizMsg, getGlobalCompanyCode());
    	
		//ITEM TYPE
        bizMsg.mdseItemTpCd_H1.clear();
        bizMsg.mdseItemTpCd_L1.clear();
        bizMsg.mdseItemTpNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, bizMsg.mdseItemTpCd_L1, bizMsg.mdseItemTpNm_L1);
		
		//MERCHANDISE TYPE
        bizMsg.coaMdseTpCd_H1.clear();
        bizMsg.coaProjCd_L1.clear();
        bizMsg.xxScrItem54Txt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, bizMsg.coaProjCd_L1, bizMsg.xxScrItem54Txt_L1, ":");
        
		//WH TYPE
        bizMsg.rtlWhCatgCd_H1.clear();
        bizMsg.rtlWhCatgCd_L1.clear();
        bizMsg.rtlWhCatgDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(RTL_WH_CATG.class, bizMsg.rtlWhCatgCd_L1, bizMsg.rtlWhCatgDescTxt_L1);

        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1)) {
        	NMAL0140CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());
        }

	}

	private void doProcess_NMAL0140_Search(NMAL0140CMsg bizMsg, NMAL0140SMsg sMsg) {

		NMAL0140CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode());

	}
	
	private void doProcess_NMAL0140_PageNext(NMAL0140CMsg cMsg, NMAL0140SMsg sMsg) {
		cMsg.xxRadioBtn_H1.clear();
    	//copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );
    
        //set value to page items
        cMsg.xxPageShowFromNum_10.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.A.getValidCount() );
	}
	
	private void doProcess_NMAL0140_PagePrev(NMAL0140CMsg cMsg, NMAL0140SMsg sMsg) {
		cMsg.xxRadioBtn_H1.clear();
    	//copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );
    
        // set value to page items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum_10.setValue( pagenationFrom );
        cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );
	}
	private void doProcess_NMAL0140_PageJump(NMAL0140CMsg cMsg, NMAL0140SMsg sMsg) {
		cMsg.xxRadioBtn_H1.clear();
    	//copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );
    
        //set value to page items
        cMsg.xxPageShowFromNum_10.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum_10.setValue( pagenationFrom + cMsg.A.getValidCount() );
	}
	
    private void doProcess_NMAL0140_TBLColumnSort(NMAL0140CMsg cMsg, NMAL0140SMsg sMsg) {
    	
        String sortTblNm  = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_01.getValue();
        
        //Table:A
        if( "A".equals(sortTblNm ) ) {
			
    		// execute column sort function
    		S21SortTarget sortTarget = new S21SortTarget( sMsg.A, sMsg.A.no(0).getBaseContents() );
    		S21SortKey sortKey = new S21SortKey();
    		sortKey.add( sortItemNm, sortOrdBy );
    		S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, sMsg.A.getValidCount() );
    			
        		// copy(SMsg -> CMsg)
        		int i = 0;
        		for( ; i < sMsg.A.getValidCount(); i++ ) {
        			if( i == cMsg.A.length() ) {
        				break;
        			}
        			EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i ), null );
        		}
        		cMsg.A.setValidCount( i );
        		
        		//set page no 
        		cMsg.xxPageShowFromNum_10.setValue( 1 );
        		cMsg.xxPageShowToNum_10.setValue( cMsg.A.getValidCount() );
    	}
    }
	
    private void doProcess_NMAL0140_CLEAR(NMAL0140CMsg cMsg, NMAL0140SMsg sMsg) {
    	ZYPTableUtil.clear(cMsg.A);
    	cMsg.xxLgcyOrdTpNm_H1.clear();
    	cMsg.mdseCstUpdTpCd_H1.clear();
    	cMsg.mdseCstUpdGrpTxt_H1.clear();    	
    	cMsg.mdseCstUpdRefTxt_H1.clear();
    	cMsg.mdseCstUpdRefId_H1.clear();
    	cMsg.mdseCd_H1.clear();
    	cMsg.mdseItemTpCd_H1.clear();
    	cMsg.coaMdseTpCd_H1.clear();
    	cMsg.coaProdCd_H1.clear();
    	cMsg.coaProdNm_H1.clear();
    	cMsg.rtlWhCd_H1.clear();
    	cMsg.rtlWhNm_H1.clear();
    	cMsg.rtlSwhCd_H1.clear();
    	cMsg.rtlSwhNm_H1.clear();
    	cMsg.mdseCstUpdEffFromDt_H1.clear();
    	cMsg.xxChkBox_CC.clear();
    }
    
    private void doProcess_NMAL0140_RESET(NMAL0140CMsg cMsg, NMAL0140SMsg sMsg) {
    	doProcess_NMAL0140_CLEAR(cMsg, sMsg);
    }
    
    private void doProcess_NMAL0140_OnChangeSavedSearchOption(NMAL0140CMsg bizMsg, NMAL0140SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
        	bizMsg.coaProdNm_H1.clear();
        	bizMsg.rtlWhNm_H1.clear();
        	bizMsg.rtlSwhNm_H1.clear();
            NMAL0140CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
        }
    }
}
