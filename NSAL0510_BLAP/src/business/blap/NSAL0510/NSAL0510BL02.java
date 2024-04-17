/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0510;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0510.common.NSAL0510CommonLogic;

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
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/08   Hitachi         T.Tsuchida      Update          QC#2844
 *</pre>
 */
public class NSAL0510BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0510_INIT".equals(screenAplId)) {
                doProcess_NSAL0510_INIT((NSAL0510CMsg) cMsg, (NSAL0510SMsg) sMsg);

                // events of NSAL0510Scrn00.
            } else if (screenAplId.startsWith("NSAL0510Scrn00")) {

                if (screenAplId.endsWith("_Search")) {
                    doProcess_NSAL0510_Search((NSAL0510CMsg) cMsg, (NSAL0510SMsg) sMsg);

                } else if (screenAplId.endsWith("_TBLColumnSort")) {
                    doProcess_NSAL0510_TBLColumnSort((NSAL0510CMsg) cMsg, (NSAL0510SMsg) sMsg);

                } else if (screenAplId.endsWith("_PagePrev")) {
                    doProcess_NSAL0510_PagePrev((NSAL0510CMsg) cMsg, (NSAL0510SMsg) sMsg);

                } else if (screenAplId.endsWith("_PageNext")) {
                    doProcess_NSAL0510_PageNext((NSAL0510CMsg) cMsg, (NSAL0510SMsg) sMsg);

                } else if (screenAplId.endsWith("_CMN_Clear")) {
                    doProcess_NSAL0510_CMN_Clear((NSAL0510CMsg) cMsg, (NSAL0510SMsg) sMsg);

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
    private void doProcess_NSAL0510_TBLColumnSort(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {
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

     private void doProcess_NSAL0510_CMN_Clear(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {
        sMsg.A.clear();

        cMsg.vndCd_H.clear();
        cMsg.dsAcctNum_H.clear();
        cMsg.dsContrNum_H.clear();
        cMsg.dsContrCtrlStsNm_H.clear();
        cMsg.dlrFleetNum_H.clear();
        cMsg.serNum_H.clear();
        cMsg.mdlActvFlg_H.clear();
        cMsg.t_MdlNm_H.clear();

        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    private void doProcess_NSAL0510_Search(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {

        NSAL0510CommonLogic.getSearchData(cMsg, sMsg);

        NSAL0510CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSAL0510CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL0510CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0510_PageNext(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValue().add(BigDecimal.ONE));
        NSAL0510CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0510CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0510_PagePrev(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValue().subtract(new BigDecimal(cMsg.A.length())));
        NSAL0510CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL0510CommonLogic.setPagenation(//
                cMsg.xxPageShowOfNum //
                , cMsg.xxPageShowToNum //
                , cMsg.xxPageShowFromNum.getValueInt() //
                , cMsg.A.getValidCount() //
                , sMsg.A.getValidCount());
    }

    /**
     * doProcess_NSAL0510_INIT
     * @param cMsg NSAL0510CMsg
     * @param sMsg NSAL0510SMsg
     */
    private void doProcess_NSAL0510_INIT(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        NSAL0510CommonLogic.createPullDown(cMsg, getContextUserInfo().getUserId());

    }
}
