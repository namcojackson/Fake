/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0420;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0420.common.NSAL0420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0420BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0420_INIT".equals(screenAplId)) {
                doProcess_NSAL0420_INIT((NSAL0420CMsg) cMsg, (NSAL0420SMsg) sMsg);
                NSAL0420CMsg nsal0420CMsg = (NSAL0420CMsg) cMsg;
                if (hasValue(nsal0420CMsg.svcRgNm_H)
                        || hasValue(nsal0420CMsg.lineBizTpDescTxt_H)
                        || hasValue(nsal0420CMsg.svcContrBrCd_H)
                        || hasValue(nsal0420CMsg.svcContrBrDescTxt_H)
                        || hasValue(nsal0420CMsg.xxGenlFldAreaTxt_H)) {
                    doProcess_NSAL0420_Search_Representative((NSAL0420CMsg) cMsg, (NSAL0420SMsg) sMsg);
                }
                // events of NSAL0420Scrn00.
            } else if (screenAplId.startsWith("NSAL0420Scrn00")) {

                if (screenAplId.endsWith("Search_Representative")) {
                    doProcess_NSAL0420_Search_Representative((NSAL0420CMsg) cMsg, (NSAL0420SMsg) sMsg);

                } else if (screenAplId.endsWith("_TBLColumnSort")) {
                    doProcess_NSAL0420_TBLColumnSort((NSAL0420CMsg) cMsg, (NSAL0420SMsg) sMsg);

                } else if (screenAplId.endsWith("_PagePrev")) {
                    doProcess_NSAL0420_PagePrev((NSAL0420CMsg) cMsg, (NSAL0420SMsg) sMsg);

                } else if (screenAplId.endsWith("_PageNext")) {
                    doProcess_NSAL0420_PageNext((NSAL0420CMsg) cMsg, (NSAL0420SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Clear")) {
                    doProcess_NSAL0420_CMN_Clear((NSAL0420CMsg) cMsg, (NSAL0420SMsg) sMsg);

                }
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Table sort
     * @param glblCmpyCd Global Company Code
     * @param cMsg NSAL0030CMsg
     */
    private void doProcess_NSAL0420_TBLColumnSort(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

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
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }

    }

     private void doProcess_NSAL0420_CMN_Clear(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {
        sMsg.A.clear();

        cMsg.svcRgNm_H.clear();
        cMsg.lineBizTpDescTxt_H.clear();
        cMsg.svcContrBrCd_H.clear();
        cMsg.svcContrBrDescTxt_H.clear();
        cMsg.xxGenlFldAreaTxt_H.clear();

        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    private void doProcess_NSAL0420_Search_Representative(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {

        NSAL0420CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0420CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSAL0420CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL0420CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0420_PageNext(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValue().add(BigDecimal.ONE));
        NSAL0420CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0420CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0420_PagePrev(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValue().subtract(new BigDecimal(cMsg.A.length())));
        NSAL0420CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0420CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    /**
     * doProcess_NSAL0420_INIT
     * @param cMsg NSAL0420CMsg
     * @param sMsg NSAL0420SMsg
     */
    private void doProcess_NSAL0420_INIT(NSAL0420CMsg cMsg, NSAL0420SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

    }
}
