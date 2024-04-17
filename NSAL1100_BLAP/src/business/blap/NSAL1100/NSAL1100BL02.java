/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1100;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1100.common.NSAL1100CommonLogic;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 * 2017/10/19   Hitachi         K.Kojima        Update          QC#21260
 * 2018/06/12   Fujitsu         T.Ogura         Update          QC#22382
 *</pre>
 */
public class NSAL1100BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1100CMsg cMsg = (NSAL1100CMsg) arg0;
        NSAL1100SMsg sMsg = (NSAL1100SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1100_INIT".equals(screenAplID)) {
                doProcess_NSAL1100_INIT(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1100Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1100Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1100Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1100Scrn00_TBLColumnSort(cMsg, sMsg);
            // START 2017/10/19 K.Kojima [QC#21260,ADD]
            } else if ("NSAL1100Scrn00_CancelCI".equals(screenAplID)) {
                doProcess_NSAL1100_INIT(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_Continue".equals(screenAplID)) {
                doProcess_NSAL1100_INIT(cMsg, sMsg);
            } else if ("NSAL1100Scrn00_OverrideApprove".equals(screenAplID)) {
                doProcess_NSAL1100_INIT(cMsg, sMsg);
            // END 2017/10/19 K.Kojima [QC#21260,ADD]
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1100_INIT(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        // START 2018/06/12 T.Ogura [QC#22382,DEL]
//        NSAL1100CommonLogic.createPullDown(cMsg);
        // END   2018/06/12 T.Ogura [QC#22382,DEL]

        NSAL1100CommonLogic.searchApprovalInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1100Scrn00_CMN_Reset(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        NSAL1100CommonLogic.searchApprovalInfo(cMsg, sMsg);
    }

    private void doProcess_NSAL1100Scrn00_PageNext(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1100CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL1100CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1100Scrn00_PagePrev(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1100CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL1100CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1100Scrn00_TBLColumnSort(NSAL1100CMsg cMsg, NSAL1100SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

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

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }
}
