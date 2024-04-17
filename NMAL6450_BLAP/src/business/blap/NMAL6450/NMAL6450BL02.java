/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6450BL02
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 07/02/2010   Fujitsu         N.Sakamoto      Create          N/A                                                       
 *</pre>
 */

package business.blap.NMAL6450;

import static business.blap.NMAL6450.common.NMAL6450CommonLogic.getQuery;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6450.common.NMAL6450CommonLogic;
import business.blap.NMAL6450.constant.NMAL6450Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;

public class NMAL6450BL02 extends S21BusinessHandler implements NMAL6450Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6450_INIT".equals(screenAplID)) {
                doProcess_NMAL6450_INIT(cMsg, sMsg);
            } else if ("NMAL6450Scrn00_CMN_Submit".equals(screenAplID)) {
                getCodeTableInfo((NMAL6450CMsg) cMsg, (NMAL6450SMsg) sMsg);
            } else if ("NMAL6450Scrn00_CMN_Reset".equals(screenAplID)) {
                getCodeTableInfo((NMAL6450CMsg) cMsg, (NMAL6450SMsg) sMsg);
            } else if ("NMAL6450Scrn00_Insert_Row".equals(screenAplID)) {
                doProcess_Insert_Row((NMAL6450CMsg) cMsg, (NMAL6450SMsg) sMsg);
            } else if ("NMAL6450Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_Delete_Row((NMAL6450CMsg) cMsg, (NMAL6450SMsg) sMsg);
            } else if ("NMAL6450Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6450Scrn00_PageNext((NMAL6450CMsg) cMsg, (NMAL6450SMsg) sMsg);
            } else if ("NMAL6450Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6450Scrn00_PagePrev((NMAL6450CMsg) cMsg, (NMAL6450SMsg) sMsg);
            } else if ("NMAL6450Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL6450Scrn00_TBLColumnSort(cMsg, sMsg);
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
    private void doProcess_NMAL6450_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NMAL6450_INIT START ----- ", this);
        
        NMAL6450CMsg bizMsg = (NMAL6450CMsg) cMsg;
        NMAL6450SMsg globalMsg = (NMAL6450SMsg) sMsg;
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.xxBtnFlg.setValue(FLG_OFF_N);
        
        getCodeTableInfo(bizMsg, globalMsg);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450_INIT END ----- ", this);
        
    }
    
    private void doProcess_NMAL6450Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_TBLColumnSort START ----- ", this);
        
        NMAL6450CMsg bizMsg = (NMAL6450CMsg) cMsg;
        NMAL6450SMsg globalMsg = (NMAL6450SMsg) sMsg;
        
        NMAL6450CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt.getValue();
        
        if( "A".equals(sortTblNm ) ) {
            
            // execute column sort function
            NMAL6450CommonLogic.sortSMsgArray(globalMsg.A, sortItemNm, sortOrdBy, globalMsg.A.no(0).getBaseContents(), false);
            
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
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_TBLColumnSort END ----- ", this);
    }
    
    private void doProcess_Insert_Row(NMAL6450CMsg bizMsg, NMAL6450SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_InsertRow START ----- ", this);
        
        int maxCnt = globalMsg.A.length();
        if (maxCnt == globalMsg.A.getValidCount()) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0050E.toString(), new String[]{Integer.toString(maxCnt)});
            return;
        }
        
        NMAL6450CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        BigDecimal newSortNum = numberingSortNum(globalMsg);
        int nextIdx = globalMsg.A.getValidCount();
        globalMsg.A.setValidCount(nextIdx + 1);
        globalMsg.A.no(nextIdx).pmtTermSortNum_A1.setValue(newSortNum);
        NMAL6450CommonLogic.loadOnePageToCMsg(bizMsg, globalMsg, nextIdx);
        
        EZDDebugOutput.println(1, "----- doProcess_InsertRow END ----- ", this);
    }
    
    private void doProcess_Delete_Row(NMAL6450CMsg bizMsg, NMAL6450SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row START ----- ", this);
        
        NMAL6450CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
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
        int beforePageNum = NMAL6450CommonLogic.getPageNum(bizMsg.xxPageShowFromNum.getValueInt());
        int afterMaxPageNum = NMAL6450CommonLogic.getPageNum(globalMsg.A.getValidCount());
        if (afterMaxPageNum < beforePageNum) {
            fromIdx =  MAX_DISPALY_ROWS * (afterMaxPageNum - 1);
        }
        
        NMAL6450CommonLogic.loadOnePageToCMsg(bizMsg, globalMsg, fromIdx);
        
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row END ----- ", this);
    }
    
    private void doProcess_NMAL6450Scrn00_PageNext(NMAL6450CMsg bizMsg, NMAL6450SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_PageNext START ----- ", this);
        
        NMAL6450CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        ZYPTableUtil.clear(bizMsg.A);
        NMAL6450CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowToNum.getValueInt());
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_PageNext END ----- ", this);
    }
    
    private void doProcess_NMAL6450Scrn00_PagePrev(NMAL6450CMsg bizMsg, NMAL6450SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_PagePrev START ----- ", this);
        
        NMAL6450CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        ZYPTableUtil.clear(bizMsg.A);
        NMAL6450CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);
                
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_PagePrev END ----- ", this);
    }
    
    private void getCodeTableInfo(NMAL6450CMsg bizMsg, NMAL6450SMsg globalMsg) {

        EZDDebugOutput.println(1, "----- getCodeTableInfo START ----- ", this);
        
        NMAL6450_ACMsgArray bizMsgArray = bizMsg.A;
        NMAL6450_ASMsgArray globalMsgArray = globalMsg.A;
        
        ZYPTableUtil.clear(bizMsgArray);
        ZYPTableUtil.clear(globalMsgArray);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxSortItemNm.clear();
        bizMsg.xxSortOrdByTxt.clear();
        bizMsg.xxSortTblNm.clear();
        
        S21SsmEZDResult ssmResult = getQuery().getCodeTable(bizMsg, globalMsg);

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
        
        EZDDebugOutput.println(1, "----- getCodeTableInfo END ----- ", this);
        
    }
    
    private static BigDecimal numberingSortNum(NMAL6450SMsg globalMsg) {
        
        NMAL6450SMsg wrkMsg = (NMAL6450SMsg) globalMsg.clone();
        NMAL6450CommonLogic.sortSMsgArray(wrkMsg.A, "pmtTermSortNum_A1", S21SortKey.DESC, wrkMsg.A.no(0).getBaseContents(), true);
        
        int sortNum = wrkMsg.A.no(0).pmtTermSortNum_A1.getValueInt();
        if (sortNum < globalMsg.A.length()) {
            sortNum++;
        }
        return new BigDecimal(sortNum);
        
    }
    
}
