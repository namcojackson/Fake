package business.blap.NMAL0160;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0160.common.NMAL0160CommonLogic;
import business.blap.NMAL0160.constant.NMAL0160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
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
 * 11/09/2016   Fujistu         R.Nakamura      Update          QC#2872
 * 2020/10/13   CITS            K.Ogino         Update          QC#57843
 * 2021/10/01   CITS            F.Deola         Update          QC#59242
 *</pre>
 */
public class NMAL0160BL02 extends S21BusinessHandler implements NMAL0160Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
		NMAL0160CMsg bizMsg = (NMAL0160CMsg) cMsg;
        //cMsg.setCommitSMsg(true);
		String scrnAplID = bizMsg.getScreenAplID();
        try {
        	if ("NMAL0160_INIT".equals(scrnAplID)) {
        		doProcess_NMAL0160_INIT(bizMsg);
        	} else if ("NMAL0160Scrn00_Search".equals(scrnAplID)) {
        		doProcess_NMAL0160_Search(bizMsg, (NMAL0160SMsg)sMsg);
        	} else if ("NMAL0160Scrn00_Apply".equals(scrnAplID)) {
        		doProcess_NMAL0160_Apply(bizMsg, (NMAL0160SMsg)sMsg);	        		
        	} else if ("NMAL0160Scrn00_PageJump".equals(scrnAplID)) {
        		doProcess_NMAL0160_PageJump(bizMsg, (NMAL0160SMsg)sMsg);	        		
        	} else if ("NMAL0160Scrn00_PagePrev".equals(scrnAplID)) {
        		doProcess_NMAL0160_PagePrev(bizMsg, (NMAL0160SMsg)sMsg);
        	} else if ("NMAL0160Scrn00_PageNext".equals(scrnAplID)) {
        		doProcess_NMAL0160_PageNext(bizMsg, (NMAL0160SMsg)sMsg);
        	} else if ("NMAL0160Scrn00_CMN_Clear".equals(scrnAplID)) {
        		doProcess_NMAL0160_CLEAR(bizMsg, (NMAL0160SMsg)sMsg);
        	} else if ("NMAL0160Scrn00_CMN_Reset".equals(scrnAplID)) {
        		doProcess_NMAL0160_RESET(bizMsg, (NMAL0160SMsg)sMsg);
            } else if ("NMAL0160Scrn00_TBLColumnSort".equals(scrnAplID)) {
            	doProcess_NMAL0160_TBLColumnSort(bizMsg);
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
	
	private void doProcess_NMAL0160_INIT(NMAL0160CMsg bizMsg) {

		ZYPTableUtil.clear(bizMsg.A);

    	//Cost Type
        bizMsg.mdseCstUpdTpCd_H1.clear();
        bizMsg.mdseCstUpdTpCd_L1.clear();
        bizMsg.mdseCstUpdTpNm_L1.clear();
        NMAL0160CommonLogic.setInitialCostTypePulldown(bizMsg, getGlobalCompanyCode());
        
        //Cost Status
        bizMsg.mdseCstUpdStsCd_AL.clear();
        bizMsg.mdseCstUpdStsNm_AL.clear();
        NMAL0160CommonLogic.setInitialCostStatusPulldown(bizMsg, getGlobalCompanyCode());
        
	}
	
	private void doProcess_NMAL0160_Search(NMAL0160CMsg bizMsg, NMAL0160SMsg sMsg) {

	    // QC#57843 Add
	    String invtySnapShotDt = NMAL0160CommonLogic.getMaxSnapShotDt(bizMsg, getGlobalCompanyCode());
	    NMAL0160CommonLogic.search(bizMsg, sMsg, getGlobalCompanyCode(), invtySnapShotDt);
		
	}
	
	private void doProcess_NMAL0160_Apply(NMAL0160CMsg bizMsg, NMAL0160SMsg sMsg) {
		
		if (bizMsg.A.getValidCount() > 0) {
			for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
				ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseCstUpdStsCd_A1, bizMsg.mdseCstUpdStsCd_H1);
			}
		}
		if (sMsg.A.getValidCount() > 0) {
			for (int i = 0; i < sMsg.A.getValidCount(); i++) {
				ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCstUpdStsCd_A1, bizMsg.mdseCstUpdStsCd_H1);
			}
		}
		
	}
	
    private void doProcess_NMAL0160_TBLColumnSort(NMAL0160CMsg cMsg) {
        String sortTblNm  = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_01.getValue();
        
        //Table:A
        if( "A".equals(sortTblNm ) ) {
			
    		// execute column sort function
    		S21SortTarget sortTarget = new S21SortTarget( cMsg.A, cMsg.A.no(0).getBaseContents() );
    		S21SortKey sortKey = new S21SortKey();
    		sortKey.add( sortItemNm, sortOrdBy );
    		S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, cMsg.A.getValidCount() );
    	}
    }
	
    private void doProcess_NMAL0160_CLEAR(NMAL0160CMsg cMsg, NMAL0160SMsg sMsg) {
    	ZYPTableUtil.clear(cMsg.A);
    	ZYPTableUtil.clear(sMsg.A);
    	ZYPTableUtil.clear(sMsg.B);
    	cMsg.mdseCstUpdTpCd_H1.clear();
    	cMsg.mdseCstUpdGrpTxt_H1.clear();
    	cMsg.invtyQty_H1.clear();
    	cMsg.xxEstScTotCostAmt_H1.clear();
    	cMsg.mdseCstUpdStsCd_H1.clear();
    	cMsg.xxPageShowTotNum_H1.clear();
    	// Add Start 2016/11/09 QC#2872
    	cMsg.xxRecHistCratTs.clear();
    	cMsg.xxRecHistCratByNm.clear();
    	cMsg.xxRecHistUpdTs.clear();
    	cMsg.xxRecHistUpdByNm.clear();
    	cMsg.xxRecHistTblNm.clear();
        // Add End 2016/11/09 QC#2872
    }
    
    private void doProcess_NMAL0160_RESET(NMAL0160CMsg cMsg, NMAL0160SMsg sMsg) {
    	doProcess_NMAL0160_CLEAR(cMsg, sMsg);
    }
	
	private void doProcess_NMAL0160_PageNext(NMAL0160CMsg cMsg, NMAL0160SMsg sMsg) {

		//Save from cMsg to sMsg
		NMAL0160CommonLogic.copyFromSMsgToCMsg(cMsg, sMsg, true);

        //copy data from SMsg onto CMsg
        // START 2021/10/01 F.Deola [QC#59242, MOD]
        cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowToNum_10.getValueInt() + 1);
        //int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        // END 2021/10/01 F.Deola [QC#59242, MOD]
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                // Add Start 2016/11/09 QC#2872
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                // Add End 2016/11/09 QC#2872
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
	
	private void doProcess_NMAL0160_PagePrev(NMAL0160CMsg cMsg, NMAL0160SMsg sMsg) {

		//Save from cMsg to sMsg
		NMAL0160CommonLogic.copyFromSMsgToCMsg(cMsg, sMsg, true);

        //copy data from SMsg to CMsg
        // START 2021/10/01 F.Deola [QC#59242, MOD]
        cMsg.xxPageShowFromNum_10.setValue(cMsg.xxPageShowFromNum_10.getValueInt() - cMsg.A.length());
        //int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        // END 2021/10/01 F.Deola [QC#59242, MOD]
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                // Add Start 2016/11/09 QC#2872
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                // Add End 2016/11/09 QC#2872
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
	private void doProcess_NMAL0160_PageJump(NMAL0160CMsg cMsg, NMAL0160SMsg sMsg) {

		//Save from cMsg to sMsg
		NMAL0160CommonLogic.copyFromSMsgToCMsg(cMsg, sMsg, true);

        //copy data from SMsg onto CMsg
        // START 2021/10/01 F.Deola [QC#59242, MOD]
        //int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt();
        cMsg.xxPageShowFromNum_10.setValue((cMsg.A.length() * (cMsg.xxPageShowCurNum_10.getValueInt() - 1)) + 1);
        int pagenationFrom = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        // END 2021/10/01 F.Deola [QC#59242, MOD]
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                // Add Start 2016/11/09 QC#2872
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(0).xxRecHistCratByNm_B1.getValue()));
                // Add End 2016/11/09 QC#2872
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
	

}
