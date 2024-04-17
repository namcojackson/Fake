/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL1040;

import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_ANALYZE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_COLCLEAR;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_COLSAVE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_CMN_SUBMIT;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_DELETE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.EVENT_NM_NLCL1040_SAVE;
import static business.blap.NLCL1040.constant.NLCL1040Constant.ZZZM9003I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLCL1040.common.NLCL1040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040BL06
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 * 11/26/2018   Fujitsu         T.Ogura         Update          QC#29124
 *</pre>
 */
public class NLCL1040BL06 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL1040_SAVE.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_Save((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_ANALYZE.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_Analyze((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_DELETE.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_Delete((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_CMN_SUBMIT.equals(screenAplID)) {

                doProcess_NLCL1040Scrn00_CMN_Submit((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_CMN_COLCLEAR.equals(screenAplID)) {

                ZYPGUITableColumn.clearColData((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else if (EVENT_NM_NLCL1040_CMN_COLSAVE.equals(screenAplID)) {

                ZYPGUITableColumn.setColData((NLCL1040CMsg) cMsg, (NLCL1040SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

            return;

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLCL1040Scrn00_Save
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_Save(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // Historical check
        NLCL1040CommonLogic.historicalCheck(cMsg, sMsg);

        // error check
        if (ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            return;
        }

        // master check
        NLCL1040CommonLogic.masterCheck(cMsg, sMsg);

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue()) && !ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {

            // ABC Assignment Create or Update
            boolean result = NLCL1040CommonLogic.createOrUpdateAbc(cMsg, sMsg);

            if (result) {
                // Message ; The process [@] has been successfully
                // completed.
                cMsg.clearErrorInfo();
                cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save" });
                ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            } else {
                // Error
                return;
            }
        } else {

            return;

        }

    }

    /**
     * doProcess_NLCL1040Scrn00_CMN_Submit
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_CMN_Submit(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // copy message c -> S
        NLCL1040CommonLogic.copyMessage(cMsg, sMsg);

        // duplicate check
        if (!NLCL1040CommonLogic.checkDuplicate(cMsg, sMsg)) {
            return;
        }

        // Create Or Update Analysis Request
        NLCL1040CommonLogic.createOrUpdateAnalysisResult(cMsg, sMsg);

    }

    /**
     * doProcess_NLCL1040Scrn00_Analyze
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_Analyze(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // header compareWithBakup
        if (NLCL1040CommonLogic.compareWithBakup(cMsg, sMsg)) {
            return;
        }

        // Create Or Update Analysis Request
        NLCL1040CommonLogic.createOrUpdateAnalysisRequest(cMsg, sMsg);

    }

    /**
     * doProcess_NLCL1040Scrn00_Delete
     * @param cMsg NLCL1040CMsg
     * @param sMsg NLCL1040SMsg
     */
    private void doProcess_NLCL1040Scrn00_Delete(NLCL1040CMsg cMsg, NLCL1040SMsg sMsg) {

        // ABC Assignment existence check
        // ************************************
        // START 2018/11/26 T.Ogura [QC#29124,MOD]
//        NLCL1040CommonLogic.getAbcName(cMsg, sMsg);
        NLCL1040CommonLogic.isExistAbcAsg(cMsg);
        // END   2018/11/26 T.Ogura [QC#29124,MOD]

        // Request Status check
        NLCL1040CommonLogic.requestStatusCheck(cMsg, sMsg);

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue()) && !ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {

            // logical remove ABC Assignment
            NLCL1040CommonLogic.logicalRemoveAbsAsg(cMsg, sMsg);

            // Message ; The process [@] has been successfully
            // completed.
            cMsg.clear();
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete" });
            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);

        } else {

            return;

        }

    }

}
