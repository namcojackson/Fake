/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6550BL02
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.blap.NMAL6550;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6550.common.NMAL6550CommonLogic;
import business.blap.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;

public class NMAL6550BL02 extends S21BusinessHandler implements NMAL6550Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6550_INIT".equals(screenAplID)) {
                doProcess_NMAL6550_INIT((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6550_CMN_Submit((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL6550_CMN_Reset((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_Insert_Row".equals(screenAplID)) {
                doProcess_NMAL6550_Insert_Row((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_Delete_Row".equals(screenAplID)) {
                doProcess_NMAL6550_Delete_Row((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6550Scrn00_PageNext((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6550Scrn00_PagePrev((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL6550Scrn00_TBLColumnSort((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_Search_ACCT".equals(screenAplID)) {
                doProcess_NMAL6355Scrn00_Search_ACCT((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);

            } else if ("NMAL6550Scrn00_Search_COA_AFFL".equals(screenAplID)) {
                doProcess_NMAL6550Scrn00_Search_COA_AFFL((NMAL6550CMsg) cMsg, (NMAL6550SMsg) sMsg);


            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6355Scrn00_Search_ACCT(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_Search_COA_AFFL START ----- ", this);

        int idx = bizMsg.xxNum.getValueInt();
        NMAL6550CommonLogic.searchAcct(bizMsg, globalMsg, idx);

        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_Search_COA_AFFL END ----- ", this);
    }

    private void doProcess_NMAL6550Scrn00_Search_COA_AFFL(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_Search_COA_AFFL START ----- ", this);

        int idx = bizMsg.xxNum.getValueInt();
        NMAL6550CommonLogic.searchCoaAffl(bizMsg, globalMsg, idx);

        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_Search_COA_AFFL END ----- ", this);
    }

    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NMAL6550_INIT(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550_INIT START ----- ", this);
        
        bizMsg.xxBtnFlg.setValue(ZYPConstant.FLG_OFF_N);

        getCodeTableData(bizMsg, globalMsg);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550_INIT END ----- ", this);
        
    }
    
    private void doProcess_NMAL6550Scrn00_TBLColumnSort(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_TBLColumnSort START ----- ", this);
        
        NMAL6550CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt.getValue();
        
        if( "A".equals(sortTblNm ) ) {
            
            // execute column sort function
            NMAL6550CommonLogic.sortNMAL6550ASMsgArray(globalMsg.A, sortItemNm, sortOrdBy, globalMsg.A.no(0).getBaseContents(), false);
            
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
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_TBLColumnSort END ----- ", this);
    }
    
    private void doProcess_NMAL6550_Insert_Row(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_InsertRow START ----- ", this);
        
        int maxCnt = globalMsg.A.length();
        if (maxCnt == globalMsg.A.getValidCount()) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0050E.toString(), new String[]{Integer.toString(maxCnt)});
            return;
        }
        
        NMAL6550CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        BigDecimal newSortNum = numberingSortNum(globalMsg);
        int nextIdx = globalMsg.A.getValidCount();
        globalMsg.A.setValidCount(nextIdx + 1);
        globalMsg.A.no(nextIdx).glblCmpySortNum_A1.setValue(newSortNum);
        
        NMAL6550CommonLogic.loadOnePageToCMsg(bizMsg, globalMsg, nextIdx);
        
        EZDDebugOutput.println(1, "----- doProcess_InsertRow END ----- ", this);
    }
    
    private void doProcess_NMAL6550_Delete_Row(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row START ----- ", this);
        
        NMAL6550CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
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
        int beforePageNum = NMAL6550CommonLogic.getPageNum(bizMsg.xxPageShowFromNum.getValueInt());
        int afterMaxPageNum = NMAL6550CommonLogic.getPageNum(globalMsg.A.getValidCount());
        if (afterMaxPageNum < beforePageNum) {
            fromIdx =  MAX_DISPALY_ROWS * (afterMaxPageNum - 1);
        }
        
        NMAL6550CommonLogic.loadOnePageToCMsg(bizMsg, globalMsg, fromIdx);
        
        EZDDebugOutput.println(1, "----- doProcess_Delete_Row END ----- ", this);
    }
    
    private void doProcess_NMAL6550Scrn00_PageNext(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_PageNext START ----- ", this);

        NMAL6550CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);

        ZYPTableUtil.clear(bizMsg.A);
        NMAL6550CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowToNum.getValueInt());

        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_PageNext END ----- ", this);
    }
    
    private void doProcess_NMAL6550Scrn00_PagePrev(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_PagePrev START ----- ", this);

        NMAL6550CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);

        ZYPTableUtil.clear(bizMsg.A);
        NMAL6550CommonLogic.pagenation(bizMsg, globalMsg, bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);

        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_PagePrev END ----- ", this);
    }

    private void doProcess_NMAL6550_CMN_Reset(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {

        doProcess_NMAL6550_INIT(bizMsg, globalMsg);
    }

    private void doProcess_NMAL6550_CMN_Submit(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {

        doProcess_NMAL6550_INIT(bizMsg, globalMsg);
    }

    private void getCodeTableData(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {

        EZDDebugOutput.println(1, "----- getCodeTableData START ----- ", this);
        
        NMAL6550_ACMsgArray bizMsgArray = bizMsg.A;
        NMAL6550_ASMsgArray globalMsgArray = globalMsg.A;
        
        ZYPTableUtil.clear(bizMsgArray);
        ZYPTableUtil.clear(globalMsgArray);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxSortItemNm.clear();
        bizMsg.xxSortOrdByTxt.clear();
        bizMsg.xxSortTblNm.clear();
        
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.slsDt.setValue(ZYPDateUtil.getSalesDate());
        
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
    private S21SsmEZDResult searchCodeTableData(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        return getQuery().getCodeTableData(bizMsg, globalMsg);
    }

    private static BigDecimal numberingSortNum(NMAL6550SMsg globalMsg) {
        
        NMAL6550SMsg wrkMsg = (NMAL6550SMsg) globalMsg.clone();
        NMAL6550CommonLogic.sortNMAL6550ASMsgArray(wrkMsg.A, "glblCmpySortNum_A1", S21SortKey.DESC, wrkMsg.A.no(0).getBaseContents(), true);
        
        int sortNum = wrkMsg.A.no(0).glblCmpySortNum_A1.getValueInt();
        if (sortNum < MAX_RECORD_COUNT) {
            sortNum++;
        }
        return new BigDecimal(sortNum);
    }
    
    private static NMAL6550Query getQuery() {
        return NMAL6550Query.getInstance();
    }
}
