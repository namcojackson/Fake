/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0640;

import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_CMN_RESET;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_CMN_SUBMIT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_INIT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.EVENT_NM_NLCL0640_NLCL0650;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0145E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.MAX_VARIANCE_ITEM_COUNT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0245W;

import java.util.Arrays;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import business.blap.NLCL0640.common.NLCL0640CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/10/2016   CITS         Makoto Okigami     Create          N/A
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 * 10/26/2018   CITS            T.Wada          Update          QC#26948-2
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 * 12/21/2018   Fujitsu         T.Ogura         Update          QC#29730
 * 05/08/2019   CITS            T.Tokutomi      Update          QC#50029
 * 05/11/2023   Hitachi         TZ.Win          Update          QC#61427
 *</pre>
 */
public class NLCL0640BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0640_INIT.equals(screenAplID)) {
                doProcess_NLCL0640_INIT((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_CMN_RESET.equals(screenAplID)) {
                doProcess_CMN_Reset((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            } else if (EVENT_NM_NLCL0640_NLCL0650.equals(screenAplID)) {
                doProcess_NLCL0650((NLCL0640CMsg) cMsg, (NLCL0640SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Init
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_NLCL0640_INIT(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        cMsg.techLocCd_SL.clear();
        cMsg.mdseCd.clear();
        cMsg.mdseDescShortTxt.clear();
        cMsg.rcvSerTakeFlg.clear();
        cMsg.cntInpQty.clear();
        cMsg.serNum.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.clear();

        // START 2018/12/03 T.Ogura [QC#27978,DEL]
//        NLCL0640CommonLogic.getPhysicalInventoryStatusName(cMsg, sMsg, glblCmpyCd);
        // END   2018/12/03 T.Ogura [QC#27978,DEL]
        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);

        if (PHYS_INVTY_CNT_STS.SCHEDULED.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // START 2018/12/03 T.Ogura [QC#27978,DEL]
//            if (!NLCL0640CommonLogic.updateCountStatus(cMsg, sMsg, glblCmpyCd, PHYS_INVTY_CNT_STS.COUNTING)) {
//                return;
//            }
//            if (!NLCL0640CommonLogic.setPIStartTime(cMsg, sMsg, glblCmpyCd)) {
//                return;
//            }
            // END   2018/12/03 T.Ogura [QC#27978,DEL]

        } else if (PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // There is no processing.
            EZDDebugOutput.println(1, "NLCL0640BL06#doProcess_NLCL0640_INIT [status:COUNTING]", null);

        } else if (PHYS_INVTY_CNT_STS.WAITRECOUNT.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // There is no processing.
            EZDDebugOutput.println(1, "NLCL0640BL06#doProcess_NLCL0640_INIT [status:WAITRECOUNT]", null);

        } else if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // There is no processing.
            EZDDebugOutput.println(1, "NLCL0640BL06#doProcess_NLCL0640_INIT [status:RECOUNTING]", null);

        } else if (PHYS_INVTY_CNT_STS.WAITAPPROVAL.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // There is no processing.
            EZDDebugOutput.println(1, "NLCL0640BL06#doProcess_NLCL0640_INIT [status:WAITAPPROVAL]", null);

        } else {

            cMsg.setMessageInfo(NLCM0145E, new String[] {cMsg.physInvtyCntStsCd.getValue()});

        }
    }

    /**
     * Submit
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_CMN_Submit(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);

        // Error unless COUNTING or RECOUNTING
        if (!Arrays.asList(new String[] {PHYS_INVTY_CNT_STS.COUNTING, PHYS_INVTY_CNT_STS.RECOUNTING }).contains(cMsg.physInvtyCntStsCd.getValue())) {
            cMsg.setMessageInfo(NLCM0145E, new String[] {cMsg.physInvtyCntStsCd.getValue() });
            return;
        }

        if (PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
            // COUNTING

            if (!NLCL0640CommonLogic.saveCountItems(cMsg, sMsg, glblCmpyCd)) {
                return;
            }

            if (!NLCL0640CommonLogic.calculateDiscrepancyForNonSerializedItemInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!NLCL0640CommonLogic.calculateDiscrepancyForNonSerializedItemNotInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!NLCL0640CommonLogic.calculateDiscrepancyForSerializedItemInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!NLCL0640CommonLogic.calculateDiscrepancyForSerializedItemNotInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!NLCL0640CommonLogic.sumUpInputtedQtyByCounting(cMsg, sMsg, glblCmpyCd)) {
                return;
            }

            // START 2023/05/11 TZ.Win [QC#61427, MOD]
            int varianceCnt = NLCL0640CommonLogic.hasVarianceItems(cMsg, sMsg, glblCmpyCd);
            if (varianceCnt > MAX_VARIANCE_ITEM_COUNT) {
                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_A.getValue())) {
                    cMsg.setMessageInfo(NLCM0245W);
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
            // END 2023/05/11 TZ.Win [QC#61427, MOD]

            if (varianceCnt != 0) {
//            if (NLCL0640CommonLogic.hasVarianceItems(cMsg, sMsg, glblCmpyCd) != 0) {

                // START 2018/12/21 T.Ogura [QC#29730,DEL]
//                if (!NLCL0640CommonLogic.addAdditionalItems(cMsg, sMsg, glblCmpyCd)) {
//                    return;
//                }
                // END   2018/12/21 T.Ogura [QC#29730,DEL]
                if (!NLCL0640CommonLogic.updateCountStatus(cMsg, sMsg, glblCmpyCd, PHYS_INVTY_CNT_STS.WAITRECOUNT)) {
                    return;
                }

                NLCL0640CommonLogic.getTechLocInfoFromPICountHeader(cMsg, sMsg, glblCmpyCd);
                NLCL0640CommonLogic.prepreReCount(cMsg, sMsg, glblCmpyCd);
                NLCL0640CommonLogic.updateCountStatus(cMsg, sMsg, glblCmpyCd, PHYS_INVTY_CNT_STS.RECOUNTING);
            } else {

                if (!NLCL0640CommonLogic.updateCountStatus(cMsg, sMsg, glblCmpyCd, PHYS_INVTY_CNT_STS.PI_COMPLETED)) {
                    return;
                }
                NLCL0640CommonLogic.closeTechPI(cMsg, sMsg, glblCmpyCd);
            }

        } else if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
            // RECOUNTING

            if (!NLCL0640CommonLogic.updateCountItems(cMsg, sMsg, glblCmpyCd)) {
                return;
            }

            if (!NLCL0640CommonLogic.calculateDiscrepancyForNonSerializedItemInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!NLCL0640CommonLogic.calculateDiscrepancyForNonSerializedItemNotInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!NLCL0640CommonLogic.calculateDiscrepancyForSerializedItemInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!NLCL0640CommonLogic.calculateDiscrepancyForSerializedItemNotInput(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            // START 2018/12/11 T.Ogura [QC#28755,ADD]
            if (!NLCL0640CommonLogic.updateReCountNotInputCntDtl(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            // END   2018/12/11 T.Ogura [QC#28755,ADD]
            if (!NLCL0640CommonLogic.sumUpInputtedQtyByCounting(cMsg, sMsg, glblCmpyCd)) {
                return;
            }

            if (NLCL0640CommonLogic.hasVarianceItems(cMsg, sMsg, glblCmpyCd) != 0) {

                if (!NLCL0640CommonLogic.updateCountStatus(cMsg, sMsg, glblCmpyCd, PHYS_INVTY_CNT_STS.WAITAPPROVAL)) {
                    return;
                }
                // QC#50029 Update.
                NLCL0640CommonLogic.updateAdjustment(cMsg,  sMsg, glblCmpyCd);
            } else {

                if (!NLCL0640CommonLogic.updateCountStatus(cMsg, sMsg, glblCmpyCd, PHYS_INVTY_CNT_STS.PI_COMPLETED)) {
                    return;
                }
                NLCL0640CommonLogic.closeTechPI(cMsg, sMsg, glblCmpyCd);
            }
        }
    }

    /**
     * doProcess_CMN_Reset
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_CMN_Reset(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {
        doProcess_NLCL0640_INIT(cMsg, sMsg);
    }

    /**
     * NLCL0650
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    private void doProcess_NLCL0650(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0640CommonLogic.getCountStatus(cMsg, sMsg, glblCmpyCd);

        if (PHYS_INVTY_CNT_STS.WAITRECOUNT.equals(cMsg.physInvtyCntStsCd.getValue())) {

            // There is no processing.
            EZDDebugOutput.println(1, "NLCL0640BL06#doProcess_NLCL0650 [status:WAITRECOUNT]", null);

        }
    }

}
