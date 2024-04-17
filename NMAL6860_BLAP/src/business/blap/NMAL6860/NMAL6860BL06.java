/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6860;

import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_CMN_SUBMIT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0013E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0803E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NZZM0002I;
import static business.blap.NMAL6860.constant.NMAL6860Constant.TAB_NM_DETAIL;
import static business.blap.NMAL6860.constant.NMAL6860Constant.TAB_NM_GENERAL;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6860.common.NMAL6860CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NMAL6860 Supplier Entry.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 2016/12/15   CITS            R.Shimamoto     Update          QC#15816
 * 2019/12/23   Fujitsu         R.Nakamura      Update          QC#54971-1
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 *</pre>
 */
public class NMAL6860BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String scrnAplId = cMsg.getScreenAplID();

            NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;
            NMAL6860SMsg glblMsg = (NMAL6860SMsg) sMsg;

            if (EVENT_NM_CMN_SUBMIT.equals(scrnAplId)) {
                doProcessSubmit(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("Illegal Screen Application Id. : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Runs the onclick event of "Submit" button.
     * </p>
     * @param bizMsg CMsg
     * @param glblMsg SMsg
     */
    private void doProcessSubmit(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // START 2021/03/01 G.Delgado [QC#56057,ADD]
        // Supplier Type Check
        if (!ZYPCommonFunc.hasValue(bizMsg.prntVndTpDescTxt)) {
            bizMsg.prntVndTpCd.clear();
        } else {
            String prntVndTpCd = (String) NMAL6860Query.getInstance().getPrntVndTpCdforDescTxt(bizMsg.glblCmpyCd.getValue(), bizMsg.prntVndTpDescTxt.getValue()).getResultObject();
            if (!ZYPCommonFunc.hasValue(prntVndTpCd)) {
                bizMsg.setMessageInfo(NMAM0013E, new String[] {"Supplier Type" });
                bizMsg.prntVndTpDescTxt.setErrorInfo(1, NMAM0013E, new String[] {"Supplier Type" });
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd, prntVndTpCd);
            }
        }
        // END 2021/03/01 G.Delgado [QC#56057,ADD]

        if (TAB_NM_GENERAL.equals(bizMsg.xxDplyTab.getValue())) {

            if (!NMAL6860CommonLogic.checkGeneralTab(bizMsg, glblMsg, glblCmpyCd)) {
                return;
            }

            // Account Check
            boolean acctErrorFlg = false;
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                // Copy to SMsg
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxComnScrFirstValTxt_AL, bizMsg.A.no(i).xxComnScrFirstValTxt_AL);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxComnScrFirstValTxt_AP, bizMsg.A.no(i).xxComnScrFirstValTxt_AP);
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxComnScrFirstValTxt_AV, bizMsg.A.no(i).xxComnScrFirstValTxt_AV);
                if (!NMAL6860CommonLogic.validateSegmentStringForLiabilityAccount(bizMsg.glblCmpyCd.getValue(), bizMsg, true, i)) {
                    acctErrorFlg = true;
                }
            }
            if(acctErrorFlg){
                return;
            }

        } else if (TAB_NM_DETAIL.equals(bizMsg.xxDplyTab.getValue())) {

            if (!NMAL6860CommonLogic.checkDetailTab(bizMsg, glblMsg, glblCmpyCd)) {
                return;
            }

            // Add Start 2019/12/23 QC#54971-1
            NMAL6860CommonLogic.getUpdateLine(bizMsg, glblMsg);

            if (NMAL6860CommonLogic.validatePastDate(bizMsg)) {
                bizMsg.setMessageInfo(NMAM0803E);
                return;
            }
            // Add End 2019/12/23 QC#54971-1

            // Account Check
            boolean acctErrorFlg = false;
            if (!NMAL6860CommonLogic.validateSegmentStringForPrePayAccount(bizMsg.glblCmpyCd.getValue(), bizMsg, true, 0)) {
                acctErrorFlg = true;
            }
            if (!NMAL6860CommonLogic.validateSegmentStringForVendorReturnAccount(bizMsg.glblCmpyCd.getValue(), bizMsg, true, 0)) {
                acctErrorFlg = true;
            }
            if(acctErrorFlg){
                return;
            }

            // Copy to SMsg
            NMAL6860CommonLogic.copyDetailToGeneral(bizMsg, glblMsg);

        }

        // Update Supplier, Supplier Sites and Contact Info
        if (!NMAL6860CommonLogic.executeUpdateSupplier(bizMsg, glblMsg)) {
            return;
        }

        bizMsg.setMessageInfo(NZZM0002I);
    }
}
