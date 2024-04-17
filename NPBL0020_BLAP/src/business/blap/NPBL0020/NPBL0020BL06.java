/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPBL0020;

import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_COL_CLEAR;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_COL_SAVE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_SAVE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_CMN_SUBMIT;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_HEADER_CANCEL;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_HEADER_CLOSE;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_LINE_CANCEL;
import static business.blap.NPBL0020.constant.NPBL0020Constant.EVENT_NM_NPBL0020_LINE_CLOSE;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/09/2016   CITS            Makoto Okigami  Create          N/A
 * 06/14/2016   CSAI            D.Fukaya        Update          QC#9044
 * 01/10/2017   CITS            K.Ogino         Update          QC#16296
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 *</pre>
 */
public class NPBL0020BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NPBL0020_CMN_SAVE.equals(screenAplID)) {
                doProcess_CMN_Save((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_HEADER_CANCEL.equals(screenAplID)) {
                doProcess_HeaderCancel((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_HEADER_CLOSE.equals(screenAplID)) {
                doProcess_HeaderClose((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_LINE_CANCEL.equals(screenAplID)) {
                // QC#56867 Mod
                doProcess_LineCancelOrClose((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg, true);
            } else if (EVENT_NM_NPBL0020_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            } else if (EVENT_NM_NPBL0020_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg);
            // QC#56867 Add
            } else if (EVENT_NM_NPBL0020_LINE_CLOSE.equals(screenAplID)) {
                doProcess_LineCancelOrClose((NPBL0020CMsg) cMsg, (NPBL0020SMsg) sMsg, false);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * CMN_Save
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_CMN_Save(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.save(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * CMN_Submit
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_CMN_Submit(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.submit(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Header Cancel
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     */
    private void doProcess_HeaderCancel(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.headerCancel(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Line Cancel. QC#56867 Mod
     * @param cMsg NPBL0020CMsg
     * @param sMsg NPBL0020SMsg
     * @param isLineCancel boolean
     */
    private void doProcess_LineCancelOrClose(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg, boolean isLineCancel) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.lineCancelOrClose(cMsg, sMsg, glblCmpyCd, isLineCancel);

    }

    /**
     * <pre>
     * HeaderClose Event
     * </pre>
     * @param cMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_HeaderClose(NPBL0020CMsg cMsg, NPBL0020SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NPBL0020CommonLogic.headerClose(cMsg, sMsg, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_OFF_N);

    }

}
