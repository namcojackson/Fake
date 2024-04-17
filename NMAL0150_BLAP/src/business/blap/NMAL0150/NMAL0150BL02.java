package business.blap.NMAL0150;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.blap.NMAL0150.common.NMAL0150CommonLogic;
import business.blap.NMAL0150.constant.NMAL0150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * 2022/05/30   Hitachi         A.Kohinata      Update          QC#55970
 *</pre>
 */
public class NMAL0150BL02 extends S21BusinessHandler implements NMAL0150Constant {

	@Override
	protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
		super.preDoProcess(cMsg, sMsg);
		NMAL0150CMsg bizMsg = (NMAL0150CMsg) cMsg;
        // add start 2022/05/30 QC#55970
        NMAL0150SMsg glblMsg = (NMAL0150SMsg) sMsg;
        // add end 2022/05/30 QC#55970
		String scrnAplID = bizMsg.getScreenAplID();
        try {
        	if ("NMAL0150_INIT".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
        		//doProcess_NMAL0150_INIT(bizMsg);
                doProcess_NMAL0150_INIT(bizMsg, glblMsg);
                // mod end 2022/05/30 QC#55970
        	} else if ("NMAL0150Scrn00_CMN_Clear".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
        		//doProcess_NMAL0150_CLEAR(bizMsg);
                doProcess_NMAL0150_CLEAR(bizMsg, glblMsg);
                // mod end 2022/05/30 QC#55970
        	} else if ("NMAL0150Scrn00_CMN_Reset".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
        		//doProcess_NMAL0150_RESET(bizMsg);
                doProcess_NMAL0150_RESET(bizMsg, glblMsg);
                // mod end 2022/05/30 QC#55970
            } else if ("NMAL0150Scrn00_TBLColumnSort".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
            	//doProcess_NMAL0150_TBLColumnSort(bizMsg);
                doProcess_NMAL0150_TBLColumnSort(bizMsg, glblMsg);
                // mod end 2022/05/30 QC#55970
            } else if ("NMAL0150_NMAL6030".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
                //doProcess_NMAL0150_NMAL6030(bizMsg);
                doProcess_NMAL0150_NMAL6030(bizMsg);
                // mod end 2022/05/30 QC#55970
            } else if ("NMAL0150_NMAL6800".equals(scrnAplID)) {
                // mod start 2022/05/30 QC#55970
                //doProcess_NMAL0150_NMAL6800(bizMsg);
                doProcess_NMAL0150_NMAL6800(bizMsg);
                // mod end 2022/05/30 QC#55970
            // add start 2022/05/30 QC#55970
            } else if ("NMAL0150Scrn00_Add".equals(scrnAplID)) {
                doProcess_NMAL0150_Add(bizMsg, glblMsg);
            } else if ("NMAL0150Scrn00_Del".equals(scrnAplID)) {
                doProcess_NMAL0150_Del(bizMsg, glblMsg);
            } else if ("NMAL0150Scrn00_PageJump".equals(scrnAplID)) {
                doProcess_NMAL0150_PageJump(bizMsg, glblMsg);
            } else if ("NMAL0150Scrn00_PagePrev".equals(scrnAplID)) {
                doProcess_NMAL0150_PagePrev(bizMsg, glblMsg);
            } else if ("NMAL0150Scrn00_PageNext".equals(scrnAplID)) {
                doProcess_NMAL0150_PageNext(bizMsg, glblMsg);
            } else if ("NMAL0150Scrn00_Click_SelAll_UnSelAll_Checkbox".equals(scrnAplID)) {
                doProcess_NMAL0150Scrn00_Click_SelAll_UnSelAll_Checkbox(bizMsg, glblMsg);
            // add end 2022/05/30 QC#55970
        	}
        } finally {
        	super.postDoProcess(cMsg, sMsg);
        }
	}
	
    // mod start 2022/05/30 QC#55970
	//private void doProcess_NMAL0150_INIT(NMAL0150CMsg bizMsg) {
    private void doProcess_NMAL0150_INIT(NMAL0150CMsg bizMsg, NMAL0150SMsg glblMsg) {
    // mod end 2022/05/30 QC#55970
		ZYPTableUtil.clear(bizMsg.A);
		ZYPTableUtil.clear(bizMsg.B);
        // add start 2022/05/30 QC#55970
        ZYPTableUtil.clear(glblMsg.A);
        bizMsg.setCommitSMsg(true);
        // add end 2022/05/30 QC#55970

    	//Cost Type
        bizMsg.mdseCstUpdTpCd_H1.clear();
        bizMsg.mdseCstUpdTpCd_L1.clear();
        bizMsg.mdseCstUpdTpNm_L1.clear();
        NMAL0150CommonLogic.setInitialCostTypePulldown(bizMsg, getGlobalCompanyCode());
        
//        //UOM
//        bizMsg.pkgUomCd_BL.clear();
//        bizMsg.pkgUomNm_BL.clear();
//        NMAL0150CommonLogic.setInitialPkgUomPulldown(bizMsg, getGlobalCompanyCode());
        
        //Search
        // mod start 2022/05/30 QC#55970
        //NMAL0150CommonLogic.search(bizMsg, getGlobalCompanyCode());
        NMAL0150CommonLogic.search(bizMsg, glblMsg, getGlobalCompanyCode());
        // mod end 2022/05/30 QC#55970
	}
	
    // mod start 2022/05/30 QC#55970
    //private void doProcess_NMAL0150_TBLColumnSort(NMAL0150CMsg cMsg) {
    private void doProcess_NMAL0150_TBLColumnSort(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
    // mod end 2022/05/30 QC#55970
    	
        // add start 2022/05/30 QC#55970
        NMAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        // add end 2022/05/30 QC#55970

        String sortTblNm  = cMsg.xxSortTblNm_01.getValue();
        String sortItemNm = cMsg.xxSortItemNm_01.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_01.getValue();
        
        //Table:A
        if( "A".equals(sortTblNm ) ) {
			
    		// execute column sort function
            // mod start 2022/05/30 QC#55970
    		//S21SortTarget sortTarget = new S21SortTarget( cMsg.A, cMsg.A.no(0).getBaseContents() );
    		//S21SortKey sortKey = new S21SortKey();
    		//sortKey.add( sortItemNm, sortOrdBy );
    		//S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, cMsg.A.getValidCount() );
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            NMAL0150CommonLogic.pagenation(cMsg, sMsg, 0);
            // mod end 2022/05/30 QC#55970
    	}
    }
	
    // mod start 2022/05/30 QC#55970
    //private void doProcess_NMAL0150_CLEAR(NMAL0150CMsg cMsg) {
    private void doProcess_NMAL0150_CLEAR(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
    // mod end 2022/05/30 QC#55970
    	if (ZYPCommonFunc.hasValue(cMsg.mdseCstUpdHistHdrPk_H1)) {
            //Search
            // mod start 2022/05/30 QC#55970
            //NMAL0150CommonLogic.search(cMsg, getGlobalCompanyCode());
            NMAL0150CommonLogic.search(cMsg, sMsg, getGlobalCompanyCode());
            // mod end 2022/05/30 QC#55970
    	} else {
        	ZYPTableUtil.clear(cMsg.A);
            // add start 2022/05/30 QC#55970
            ZYPTableUtil.clear(sMsg.A);
            // add end 2022/05/30 QC#55970
        	cMsg.mdseCstUpdTpCd_H1.clear();
        	cMsg.mdseCstUpdGrpTxt_H1.clear();
        	cMsg.mdseCstUpdDescTxt_H1.clear();    	
        	cMsg.mdseCstUpdRefTxt_H1.clear();
        	cMsg.mdseCstUpdRefId_H1.clear();
        	cMsg.xxFullNm_H1.clear();
        	cMsg.mdseCstUpdCratPsnCd_H1.clear();
        	cMsg.mdseCstUpdCratDt_H1.clear();
        	// Add Start 2016/11/09 QC#2872
            cMsg.xxRecHistCratTs.clear();
            cMsg.xxRecHistCratByNm.clear();
            cMsg.xxRecHistUpdTs.clear();
            cMsg.xxRecHistUpdByNm.clear();
            cMsg.xxRecHistTblNm.clear();
            // Add End 2016/11/09 QC#2872
    	}
    }
    
    // mod start 2022/05/30 QC#55970
    //private void doProcess_NMAL0150_RESET(NMAL0150CMsg cMsg) {
    //    doProcess_NMAL0150_CLEAR(cMsg);
    //}
    private void doProcess_NMAL0150_RESET(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        doProcess_NMAL0150_CLEAR(cMsg, sMsg);
    }
    // mod end 2022/05/30 QC#55970
	
   private void doProcess_NMAL0150_NMAL6030(NMAL0150CMsg bizMsg) {
    	
		String event = bizMsg.P.no(29).xxPopPrm.getValue();
		if ("NMAL0150Scrn00_LineItem".equals(event)) {
			
			int row = Integer.parseInt(bizMsg.P.no(28).xxPopPrm.getValue());
			NMAL0150CommonLogic.getMdseInfoForBomTable(bizMsg, getGlobalCompanyCode(), bizMsg.P.no(0).xxPopPrm.getValue(), row);
			
		}
    }

   private void doProcess_NMAL0150_NMAL6800(NMAL0150CMsg bizMsg) {
   	
		String event = bizMsg.P.no(29).xxPopPrm.getValue();
		if ("NMAL0150Scrn00_LineItem".equals(event)) {
			
			int row = Integer.parseInt(bizMsg.P.no(28).xxPopPrm.getValue());
			NMAL0150CommonLogic.getMdseInfoForBomTable(bizMsg, getGlobalCompanyCode(), bizMsg.P.no(0).xxPopPrm.getValue(), row);
			
		}
   }

   // add start 2022/05/30 QC#55970
    private void doProcess_NMAL0150_Add(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        NMAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo("NMAM0050E", new String[] {String.valueOf(sMsg.A.length()) });
            return;
        }

        int rowIndex = sMsg.A.getValidCount();
        sMsg.A.no(rowIndex).xxChkBox_A1.clear();
        sMsg.A.no(rowIndex).mdseCd_A1.clear();
        sMsg.A.no(rowIndex).mdseDescShortTxt_A1.clear();
        sMsg.A.no(rowIndex).mdseItemTpNm_A1.clear();
        sMsg.A.no(rowIndex).xxScrItem54Txt_A1.clear();
        sMsg.A.no(rowIndex).coaProdCd_A1.clear();
        sMsg.A.no(rowIndex).rqstTotStdCostAmt_A1.clear();
        sMsg.A.no(rowIndex).pkgUomCd_A1.clear();
        sMsg.A.no(rowIndex).mdseCstUpdEffFromDt_A1.clear();
        sMsg.A.no(rowIndex).mdseCstUpdStsNm_A1.clear();
        sMsg.A.setValidCount(rowIndex + 1);

        NMAL0150CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NMAL0150_Del(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        NMAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo("NMAM8054E");
            return;
        }
        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);

        int rowIndex = cMsg.xxPageShowFromNum_10.getValueInt() - 1;
        if (rowIndex >= sMsg.A.getValidCount()) {
            rowIndex = 0;
        }
        NMAL0150CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NMAL0150_PageJump(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        NMAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        int rowIndex = cMsg.xxPageShowFromNum_10.getValueInt();
        NMAL0150CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NMAL0150_PageNext(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        NMAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        int rowIndex = cMsg.xxPageShowFromNum_10.getValueInt() + cMsg.A.length() - 1;
        NMAL0150CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NMAL0150_PagePrev(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {
        NMAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        int rowIndex = cMsg.xxPageShowFromNum_10.getValueInt() - cMsg.A.length() - 1;
        NMAL0150CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NMAL0150Scrn00_Click_SelAll_UnSelAll_Checkbox(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if(ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_AL.getValue())){
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, ZYPConstant.FLG_ON_Y);
            } else {
                sMsg.A.no(i).xxChkBox_A1.clear();
                
            }
        }
        
    }
   // add end 2022/05/30 QC#55970
}
