/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1130;

import static business.blap.NSAL1130.constant.NSAL1130Constant.NSAM0131E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1130.common.NSAL1130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 *
 * NSAL1130 Counter History Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         K.Kojima        Create          N/A
 * 2016/04/19   Hitachi         K.Yamada        Update          CSA QC#7233
 * 
 *</pre>
 */
public class NSAL1130BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            String gcc = getGlobalCompanyCode();

            if ("NSAL1130_INIT".equals(screenAplID)) {
                doProcess_NSAL1130_INIT((NSAL1130CMsg) cMsg, (NSAL1130SMsg) sMsg, gcc);
            } else if ("NSAL1130Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1130Scrn00_Search((NSAL1130CMsg) cMsg, (NSAL1130SMsg) sMsg, gcc);
            } else if ("NSAL1130Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1130Scrn00_PagePrev((NSAL1130CMsg) cMsg, (NSAL1130SMsg) sMsg, gcc);
            } else if ("NSAL1130Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1130Scrn00_PageNext((NSAL1130CMsg) cMsg, (NSAL1130SMsg) sMsg, gcc);
            } else if ("NSAL1130Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1130Scrn00_TBLColumnSort((NSAL1130CMsg) cMsg, (NSAL1130SMsg) sMsg, gcc);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL1130_INIT(NSAL1130CMsg cMsg, NSAL1130SMsg sMsg, String gcc) {
        initMachineStatusPulldown(this.getGlobalCompanyCode(), cMsg);
        if (hasValue(cMsg.svcMachMstrPk.getValue())) {
            NSAL1130CommonLogic.searchCounterHistoryList(cMsg, sMsg, gcc);
        } else {
            cMsg.setMessageInfo(NSAM0131E, new String[] {"SVC_MACH_MSTR_PK" });
        }
    }

    private void doProcess_NSAL1130Scrn00_Search(NSAL1130CMsg cMsg, NSAL1130SMsg sMsg, String gcc) {
        if (hasValue(cMsg.svcMachMstrPk.getValue())) {
            NSAL1130CommonLogic.searchCounterHistoryList(cMsg, sMsg, gcc);
        } else {
            cMsg.setMessageInfo(NSAM0131E, new String[] {"SVC_MACH_MSTR_PK" });
        }
    }

    private void doProcess_NSAL1130Scrn00_PagePrev(NSAL1130CMsg cMsg, NSAL1130SMsg sMsg, String gcc) {
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum.clear();

        NSAL1130CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL1130Scrn00_PageNext(NSAL1130CMsg cMsg, NSAL1130SMsg sMsg, String gcc) {
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        cMsg.xxPageShowToNum.clear();

        NSAL1130CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL1130Scrn00_TBLColumnSort(NSAL1130CMsg cMsg, NSAL1130SMsg sMsg, String gcc) {
        NSAL1130CommonLogic.clearPageNum(cMsg);

        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        NSAL1130CommonLogic.setPageNum(cMsg, 1, cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    /**
     * Initialize machine status pull down
     * @param glblCmpyCd Global Company Code
     * @param cMsg NSAL0030CMsg
     */
    private static void initMachineStatusPulldown(String glblCmpyCd, NSAL1130CMsg cMsg) {
        // mod start 2016/04/19 CSA Defect#7233
        NSAL1130CommonLogic.createMtrLbPulldown(cMsg, glblCmpyCd);
        // mod end 2016/04/19 CSA Defect#7233
    }

}
