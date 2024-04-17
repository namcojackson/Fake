/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0600;

import static business.blap.NLCL0600.constant.NLCL0600Constant.CONST_VALUE_KEY_WH_PI_LOC_TP_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.CONST_VALUE_KEY_WH_PI_WH_OWNR_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_ADD_ALL_SUBWH;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_ADD_SPECIFIC_SUBWH;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_CMN_CLEAR;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_CMN_RESET;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_CMN_SUBMIT;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_INIT;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_SEARCH_RETAIL_SUB_WH_INFO;
import static business.blap.NLCL0600.constant.NLCL0600Constant.EVENT_NM_NLCL0600_SEARCH_WH_INFO;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0600.common.NLCL0600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS         Makoto Okigami     Create          N/A
 *</pre>
 */
public class NLCL0600BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0600_INIT.equals(screenAplID)
                    || EVENT_NM_NLCL0600_CMN_RESET.equals(screenAplID)
                    || EVENT_NM_NLCL0600_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NLCL0600_INIT((NLCL0600CMsg) cMsg, (NLCL0600SMsg) sMsg);
            } else if (EVENT_NM_NLCL0600_ADD_SPECIFIC_SUBWH.equals(screenAplID)) {
                doProcess_Add_SpecificSubWH((NLCL0600CMsg) cMsg, (NLCL0600SMsg) sMsg);
            } else if (EVENT_NM_NLCL0600_ADD_ALL_SUBWH.equals(screenAplID)) {
                doProcess_Add_AllSubWH((NLCL0600CMsg) cMsg, (NLCL0600SMsg) sMsg);
            } else if (EVENT_NM_NLCL0600_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NLCL0600CMsg) cMsg, (NLCL0600SMsg) sMsg);
            } else if (EVENT_NM_NLCL0600_SEARCH_WH_INFO.equals(screenAplID)) {
                doProcess_SearchWHInfo((NLCL0600CMsg) cMsg, (NLCL0600SMsg) sMsg);
            } else if (EVENT_NM_NLCL0600_SEARCH_RETAIL_SUB_WH_INFO.equals(screenAplID)) {
                doProcess_SearchRetailSubWHInfo((NLCL0600CMsg) cMsg, (NLCL0600SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     */
    private void doProcess_NLCL0600_INIT(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_01, ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_WH_PI_LOC_TP_CD, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(cMsg.varCharConstVal_02, ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_WH_PI_WH_OWNR_CD, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.varCharConstVal_01, ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_WH_PI_LOC_TP_CD, glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sMsg.varCharConstVal_02, ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_WH_PI_WH_OWNR_CD, glblCmpyCd));

        NLCL0600CommonLogic.getPhysicalInventoryStatusName(cMsg, sMsg, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.physInvtyNum)) {
            NLCL0600CommonLogic.search(cMsg, sMsg, glblCmpyCd);
        }

    }

    /**
     * Init
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     */
    private void doProcess_Add_SpecificSubWH(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0600CommonLogic.addSpecificSubWH(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Init
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     */
    private void doProcess_Add_AllSubWH(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0600CommonLogic.addAllSubWH(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Submit
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     */
    private void doProcess_CMN_Submit(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0600CommonLogic.search(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Search WH Info
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     */
    private void doProcess_SearchWHInfo(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0600CommonLogic.searchWHInfo(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Search Retail Sub WH Info
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     */
    private void doProcess_SearchRetailSubWHInfo(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        NLCL0600CommonLogic.searchRetailSubWHInfo(cMsg, sMsg, glblCmpyCd);

    }

}
