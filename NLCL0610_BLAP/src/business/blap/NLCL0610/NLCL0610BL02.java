/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0610;

import static business.blap.NLCL0610.constant.NLCL0610Constant.CONST_VALUE_KEY_WH_PI_LOC_TP_CD;
import static business.blap.NLCL0610.constant.NLCL0610Constant.CONST_VALUE_KEY_WH_PI_WH_OWNR_CD;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_CMN_RESET;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_INIT;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_ONCLICK_CANCEL;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_ONCLICK_EDIT;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_ONCLICK_SEARCH;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_ONCLICK_SET_WH_NM;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_PAGE_NEXT;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_PAGE_PREV;
import static business.blap.NLCL0610.constant.NLCL0610Constant.NLCM0179E;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0610.common.NLCL0610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 * 2018/08/03   CITS            S.Katsuma       Update          QC#27048
 *</pre>
 */
public class NLCL0610BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0610_INIT.equals(screenAplID)) {
                doProcess_NLCL0610_INIT((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            } else if (EVENT_NM_NLCL0610_ONCLICK_SET_WH_NM.equals(screenAplID)) {
                doProcess_NLCL0610Scrn00_OnClick_SetWhNm((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            } else if (EVENT_NM_NLCL0610_ONCLICK_SEARCH.equals(screenAplID)) {
                doProcess_NLCL0610Scrn00_OnClick_Search((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            } else if (EVENT_NM_NLCL0610_PAGE_PREV.equals(screenAplID)) {
                doProcess_NLCL0610Scrn00_PagePrev((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            } else if (EVENT_NM_NLCL0610_PAGE_NEXT.equals(screenAplID)) {
                doProcess_NLCL0610Scrn00_PageNext((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            } else if (EVENT_NM_NLCL0610_ONCLICK_CANCEL.equals(screenAplID)) {
                doProcess_NLCL0610Scrn00_OnClick_Search((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            } else if (EVENT_NM_NLCL0610_ONCLICK_EDIT.equals(screenAplID)) {
                doProcess_NLCL0610Scrn00_OnClick_Edit((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            } else if (EVENT_NM_NLCL0610_CMN_RESET.equals(screenAplID)) {
                doProcess_NLCL0610_INIT((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Set Warehouse Name.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0610Scrn00_OnClick_SetWhNm(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        NLCL0610CommonLogic.setWarehouseName(cMsg, sMsg);
    }

    /**
     * <p>
     * On Click Edit.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0610Scrn00_OnClick_Edit(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String physInvtyNum = cMsg.A.no(cMsg.xxRadioBtn_A.getValueInt()).physInvtyNum_A.getValue();

        S21SsmEZDResult result = NLCL0610Query.getInstance().countPiForEdit(glblCmpyCd, physInvtyNum);
        int cnt = 0;
        if (result.isCodeNormal()) {
            cnt =  (Integer) result.getResultObject();
        }

        if (cnt == 0) {
            cMsg.setMessageInfo(NLCM0179E, new String[]{cMsg.A.no(cMsg.xxRadioBtn_A.getValueInt()).physInvtyCtrlNm_A.getValue()});
        }

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    /**
     * <p>
     * Initialization.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0610_INIT(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);
        cMsg.A.clear();
        sMsg.A.clear();

        cMsg.xxPageShowFromNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowToNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowOfNum.setValue(BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        // Set Var Char Const
        ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_LT,
                ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_WH_PI_LOC_TP_CD, cMsg.glblCmpyCd.getValue()));

        ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_WO,
                ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_WH_PI_WH_OWNR_CD, cMsg.glblCmpyCd.getValue()));

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    /**
     * <p>
     * Search.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0610Scrn00_OnClick_Search(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        cMsg.A.setValidCount(0);
        sMsg.A.setValidCount(0);
        cMsg.A.clear();
        sMsg.A.clear();

        cMsg.xxPageShowFromNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowToNum.setValue(BigDecimal.ZERO);
        cMsg.xxPageShowOfNum.setValue(BigDecimal.ZERO);

        // Set Warehouse Name to header
        if (!ZYPCommonFunc.hasValue(cMsg.rtlWhNm) && ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            NLCL0610CommonLogic.setWarehouseName(cMsg, sMsg);

            // Clear Error Message. This message is not search
            // function error.
            if (cMsg.rtlWhCd.isError()) {
                cMsg.setMessageInfo(null);
                cMsg.clearErrorInfo();
                // START 2018/08/03 S.Katsuma [QC#27048,ADD]
                cMsg.setCommitSMsg(true);
                // END 2018/08/03 S.Katsuma [QC#27048,ADD]
            }
        }

        NLCL0610CommonLogic.search(cMsg, sMsg);
    }

    /**
     * <p>
     * Page Previous.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0610Scrn00_PagePrev(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * <p>
     * Page Next.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0610Scrn00_PageNext(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }
}
