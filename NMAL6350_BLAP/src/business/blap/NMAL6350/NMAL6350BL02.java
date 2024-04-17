/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6350BL02
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 * 09/04/2018   Fujitsu         T.Noguchi       Update          QC#28019
 *</pre>
 */
package business.blap.NMAL6350;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6350.common.NMAL6350CommonLogic;
import business.blap.NMAL6350.constant.NMAL6350Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM_CLS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;

public class NMAL6350BL02 extends S21BusinessHandler implements NMAL6350Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6350_INIT".equals(screenAplID)) {
                doProcess_NMAL6350_INIT((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else if ("NMAL6350Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6350_CMN_Submit((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else if ("NMAL6350Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL6350_CMN_Reset((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else if ("NMAL6350Scrn00_Insert_Row".equals(screenAplID)) {
                doProcess_NMAL6350_Insert_Row((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else if ("NMAL6350Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_NMAL6350_Delete_Row((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else if ("NMAL6350Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6350Scrn00_PageNext((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else if ("NMAL6350Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6350Scrn00_PagePrev((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else if ("NMAL6350Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL6350Scrn00_TBLColumnSort((NMAL6350CMsg) cMsg, (NMAL6350SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL6350_INIT(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350_INIT START ----- ", this);
        
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.xxBtnFlg.setValue(ZYPConstant.FLG_OFF_N);

        getCodeTableData(bizMsg, globalMsg);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350_INIT END ----- ", this);
        
    }
    
    private void doProcess_NMAL6350Scrn00_TBLColumnSort(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_TBLColumnSort START ----- ", this);
        
        NMAL6350CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt.getValue();
        
        if( "A".equals(sortTblNm ) ) {
            
            // execute column sort function
            NMAL6350CommonLogic.sortNMAL6350ASMsgArray(globalMsg.A, sortItemNm, sortOrdBy, globalMsg.A.no(0).getBaseContents(), false);
            
            int i = 0;
            for( ; i < globalMsg.A.getValidCount(); i++) {
                if( i == bizMsg.A.length() ) {
                    break;
                }
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);
            
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_TBLColumnSort END ----- ", this);
    }
    
    private void doProcess_NMAL6350_Insert_Row(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_InsertRow START ----- ", this);
        
        int maxCnt = globalMsg.A.length();
        if (maxCnt == globalMsg.A.getValidCount()) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0050E.toString(), new String[]{Integer.toString(maxCnt)});
            return;
        }
        
        NMAL6350CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        BigDecimal newSortNum = numberingSortNum(globalMsg);
        int nextIdx = globalMsg.A.getValidCount();
        globalMsg.A.setValidCount(nextIdx + 1);
        globalMsg.A.no(nextIdx).pkgUomSortNum_A1.setValue(newSortNum);
        globalMsg.A.no(nextIdx).pkgUomStdFlg_A1.setValue(FLG_OFF_N);
        globalMsg.A.no(nextIdx).pkgUomMndFlg_A1.setValue(FLG_OFF_N);
        
        NMAL6350CommonLogic.loadOnePageToCMsg(bizMsg, globalMsg, nextIdx);
        
        EZDDebugOutput.println(1, "----- doProcess_InsertRow END ----- ", this);
    }
    
    private void doProcess_NMAL6350_Delete_Row(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row START ----- ", this);
        
        NMAL6350CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(globalMsg.A, "xxChkBox_A1", CHKBOX_ON_Y);
        if(selectedRows.isEmpty()) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (!hasValue(bizMsg.A.no(i).ezUpTime_A1)) {
                    bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, MESSAGE_ID.NMAM8054E.toString());
                }
            }
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM8054E.toString());
            return;
        } else {
            ZYPTableUtil.deleteRows(globalMsg.A, selectedRows);
        }
        
        int fromIdx = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int beforePageNum = NMAL6350CommonLogic.getPageNum(bizMsg.xxPageShowFromNum.getValueInt());
        int afterMaxPageNum = NMAL6350CommonLogic.getPageNum(globalMsg.A.getValidCount());
        if (afterMaxPageNum < beforePageNum) {
            fromIdx =  MAX_DISPALY_ROWS * (afterMaxPageNum - 1);
        }
        
        NMAL6350CommonLogic.loadOnePageToCMsg(bizMsg, globalMsg, fromIdx);
        
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row END ----- ", this);
    }
    
    private void doProcess_NMAL6350Scrn00_PageNext(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_PageNext START ----- ", this);
        
        NMAL6350CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
//        if (!NMAL6350CommonLogic.inputCheck(bizMsg, globalMsg)) {
//            NMAL6350CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowFromNum.getValueInt() - 1);
//            return;
//        }
        
        ZYPTableUtil.clear(bizMsg.A);
        NMAL6350CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowToNum.getValueInt());
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_PageNext END ----- ", this);
    }
    
    private void doProcess_NMAL6350Scrn00_PagePrev(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_PagePrev START ----- ", this);
        
        NMAL6350CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
//        if (!NMAL6350CommonLogic.inputCheck(bizMsg, globalMsg)) {
//            NMAL6350CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowFromNum.getValueInt() - 1);
//            return;
//        }
        
        ZYPTableUtil.clear(bizMsg.A);
        NMAL6350CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);
                
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_PagePrev END ----- ", this);
    }

    private void doProcess_NMAL6350_CMN_Reset(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        
        getCodeTableData(bizMsg, globalMsg);
    }

    private void doProcess_NMAL6350_CMN_Submit(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        
        getCodeTableData(bizMsg, globalMsg);
    }

    private void getCodeTableData(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {

        EZDDebugOutput.println(1, "----- getCodeTableData START ----- ", this);
        
        NMAL6350_ACMsgArray bizMsgArray = bizMsg.A;
        NMAL6350_ASMsgArray globalMsgArray = globalMsg.A;
        
        ZYPTableUtil.clear(bizMsgArray);
        ZYPTableUtil.clear(globalMsgArray);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxSortItemNm.clear();
        bizMsg.xxSortOrdByTxt.clear();
        bizMsg.xxSortTblNm.clear();

        // 2018/09/04 QC#28019 Add Start
        createPullDown(bizMsg);
        // 2018/09/04 QC#28019 Add End

        S21SsmEZDResult ssmResult = searchCodeTableData(bizMsg, globalMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            int maxCnt = globalMsgArray.length();
            
            if (queryResCnt > maxCnt) {
                queryResCnt = maxCnt;
                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0098I.toString(), new String[]{Integer.toString(maxCnt)});
            }
            EZDMsg.copy(globalMsgArray, null, bizMsgArray, null);
            EZDMsg.copy(globalMsgArray, "A1", globalMsg.B, "B1");
            
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
            bizMsg.xxPageShowToNum.setValue(bizMsgArray.getValidCount());
            
        } else if (FLG_OFF_N.equals(bizMsg.xxBtnFlg.getValue())) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0007I.toString());
            return;
        }
        
        bizMsg.xxBtnFlg.clear();
        
        EZDDebugOutput.println(1, "----- getCodeTableData END ----- ", this);
        
    }
    
    @SuppressWarnings("unchecked")
    private S21SsmEZDResult searchCodeTableData(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
        return getQuery().getCodeTableData(bizMsg, globalMsg);
    }

    private static BigDecimal numberingSortNum(NMAL6350SMsg globalMsg) {
        
        NMAL6350SMsg wrkMsg = (NMAL6350SMsg) globalMsg.clone();
        NMAL6350CommonLogic.sortNMAL6350ASMsgArray(wrkMsg.A, "pkgUomSortNum_A1", S21SortKey.DESC, wrkMsg.A.no(0).getBaseContents(), true);
        
        int sortNum = wrkMsg.A.no(0).pkgUomSortNum_A1.getValueInt();
        if (sortNum < MAX_RECORD_COUNT) {
            sortNum++;
        }
        return new BigDecimal(sortNum);
    }
    
    private static NMAL6350Query getQuery() {
        return NMAL6350Query.getInstance();
    }

    // 2018/09/04 QC#28019 Add Start
    private void createPullDown(NMAL6350CMsg bizMsg) {

        bizMsg.pkgUomClsCd_L1.clear();
        bizMsg.pkgUomClsNm_L1.clear();
        ZYPCodeDataUtil.createPulldownList(PKG_UOM_CLS.class, bizMsg.pkgUomClsCd_L1, bizMsg.pkgUomClsNm_L1);
    }
    // 2018/09/04 QC#28019 Add End
}
