package business.blap.ZZOL0130;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZOL0130.common.ZZOL0130CommonLogic;
import business.blap.ZZOL0130.constant.ZZOL0130Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class ZZOL0130BL02 extends S21BusinessHandler implements ZZOL0130Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("ZZOL0130_INIT".equals(screenAplID)) {
                doProcess_ZZOL0130_INIT((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0130_Search((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZOL0130_PageNext((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZOL0130_PagePrev((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_PageJump".equals(screenAplID)) {
                doProcess_ZZOL0130_PageJump((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_ZZOL0130_INIT((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0130Scrn00_TBLColumnSort((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }
    

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZOL0130_INIT(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        String sGlobalCpyCd = getGlobalCompanyCode();

        ZZOL0130CommonLogic.doClear(sGlobalCpyCd, "", cMsg, sMsg);
    }


    /**
     * doProcess_ZZOL0001_Search
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZOL0130_Search(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        String sDelTblId    = cMsg.delTblId_A0.getValue();

        ZZOL0130CommonLogic.doClear(sGlobalCpyCd, sDelTblId, cMsg, sMsg);

        if (ZZOL0130CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE });
            return;
        }

        ZZOL0130CommonLogic.getDeleteTableList(cMsg, sMsg);

    }


    private void doProcess_ZZOL0130_PageNext(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        ZZOL0130CommonLogic.pageForward(cMsg, sMsg);
    }

    private void doProcess_ZZOL0130_PagePrev(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        ZZOL0130CommonLogic.pageBack(cMsg, sMsg);
    }

    private void doProcess_ZZOL0130_PageJump(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        ZZOL0130CommonLogic.pageForward(cMsg, sMsg);
    }

    private void doProcess_ZZOL0130Scrn00_TBLColumnSort(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowFromNum_A0.setValue(1);
        }

    }

}
