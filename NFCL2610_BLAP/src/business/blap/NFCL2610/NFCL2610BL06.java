/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2610;

import static business.blap.NFCL2610.constant.NFCL2610Constant.NZZM0002I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * AR Refund by Check Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#7250
 * 2016/05/13   Hitachi         K.Kojima        Update          QC#7796
 * 2017/01/13   Fujitsu         T.Murai         Update          QC#16941
 * 2021/09/09   CITS            G.Delgado       Update          QC#58793
 * 2022/07/27   Hitachi         A.Kohinata      Update          QC#57418
 *</pre>
 */
public class NFCL2610BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NFCL2610CMsg cMsg = (NFCL2610CMsg) arg0;
        NFCL2610SMsg sMsg = (NFCL2610SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NFCL2610Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_CMN_Submit(cMsg, sMsg);
            // add start 2022/07/27 QC#57418
            } else if ("NFCL2610Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_CMN_Save(cMsg, sMsg);
            // add end 2022/07/27 QC#57418
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // del start 2022/07/27 QC#57418
//    private void doProcess_NFCL2610Scrn00_CMN_Submit(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
//
//        // START 2021/09/09 G.Delgado [QC#58793, ADD]
//        // Update SMsg
//        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
//        NFCL2610CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
//        cMsg.setCommitSMsg(true);
//        // END 2021/09/09 G.Delgado [QC#58793, ADD]
//
//        if (!NFCL2610CommonLogic.checkMultipleRefundFlag(cMsg, sMsg)) {
//            return;
//        }
//
//        // START 2016/05/13 K.Kojima [QC#7796,ADD]
//        if (!NFCL2610CommonLogic.checkSupplier(cMsg, sMsg)) {
//            return;
//        }
//        // END 2016/05/13 K.Kojima [QC#7796,ADD]
//
//        // START 2017/01/12 [QC#16941,ADD]
//        // Check Already requested
//        // START 2021/09/09 G.Delgado [QC#58793, MOD]
//        // if (!NFCL2610CommonLogic.checkArRqst(cMsg)) {
//        if (!NFCL2610CommonLogic.checkArRqst(cMsg, sMsg)) {
//        // END 2021/09/09 G.Delgado [QC#58793, MOD]
//            return;
//        }
//        // END   2017/01/12 [QC#16941,ADD]
//
//        // Create AR Refund Request
//        NFCL2610CommonLogic.submitArRefundRequest(cMsg, sMsg);
//    }
    // del end 2022/07/27 QC#57418

    // add start 2022/07/27 QC#57418
    private void doProcess_NFCL2610Scrn00_CMN_Save(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        if (!NFCL2610CommonLogic.saveProcess(cMsg, sMsg, AR_RF_STS.SAVED)) {
            return;
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    private void doProcess_NFCL2610Scrn00_CMN_Submit(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.arRfRqstPk)) {
            NFCL2610CommonLogic.clearArRfStsCd(cMsg.glblCmpyCd.getValue(), cMsg.arRfRqstPk.getValue());
            if (!NFCL2610CommonLogic.workflowProcess(cMsg, sMsg)) {
                return;
            }
        } else {
            if (!NFCL2610CommonLogic.saveProcess(cMsg, sMsg, null)) {
                return;
            }
            if (!NFCL2610CommonLogic.workflowProcess(cMsg, sMsg)) {
                return;
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }
    // add end 2022/07/27 QC#57418
}
