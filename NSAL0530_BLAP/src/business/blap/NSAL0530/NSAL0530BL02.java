/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0530;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Solution Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 *</pre>
 */
public class NSAL0530BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0530CMsg cMsg = (NSAL0530CMsg) arg0;
        NSAL0530SMsg sMsg = (NSAL0530SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);
        
        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0530_INIT".equals(screenAplID)) {
                doProcess_NSAL0530_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NSAL0530Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0530Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL0530Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0530Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0530Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0530Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0530Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0530Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL0530Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0530Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NSAL0530Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0530Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL0530_NSAL0540".equals(screenAplID)) {
                doProcess_NSAL0530_NSAL0540(cMsg, sMsg);
            }
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0530_INIT(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {

        NSAL0530CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0530CommonLogic.createPullDown(cMsg);

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    private void doProcess_NSAL0530Scrn00_Search(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {

        if (NSAL0530CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        NSAL0530CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0530CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSAL0530CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0530CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0530Scrn00_PagePrev(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {

        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        setValue(cMsg.xxPageShowFromNum, cMsg.xxPageShowFromNum.getValue().subtract(new BigDecimal(cMsg.A.length())));
        NSAL0530CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0530CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0530Scrn00_PageNext(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {

        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        setValue(cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum.getValue().add(BigDecimal.ONE));
        NSAL0530CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0530CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0530Scrn00_TBLColumnSort(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {

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

    private void doProcess_NSAL0530Scrn00_CMN_Download(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {

        if (NSAL0530CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }
        NSAL0530CommonLogic.download(cMsg, sMsg);
    }

    private void doProcess_NSAL0530Scrn00_CMN_Clear(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {
        NSAL0530CommonLogic.clearMsg(cMsg, sMsg);
    }

    private void doProcess_NSAL0530_NSAL0540(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {
        doProcess_NSAL0530Scrn00_Search(cMsg, sMsg);
    }
}
