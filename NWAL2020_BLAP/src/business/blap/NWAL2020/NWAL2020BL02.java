/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2020;

import static business.blap.NWAL2020.constant.NWAL2020Constant.TCEPPS_KEY_NM;
import static business.blap.NWAL2020.constant.NWAL2020Constant.TCEPPS_TOKEN_EXPR_INTVL;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2020.common.NWAL2020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2020BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Update          QC#63757
 *</pre>
 */
public class NWAL2020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2020CMsg bizMsg = (NWAL2020CMsg) cMsg;
            NWAL2020SMsg glblMsg = (NWAL2020SMsg) sMsg;

            if ("NWAL2020_INIT".equals(screenAplID)) {
                doProcess_NWAL2020_INIT(bizMsg, glblMsg);

            } else if ("NWAL2020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL2020Scrn00_CMN_Clear((NWAL2020CMsg) cMsg, (NWAL2020SMsg) sMsg);

            } else if ("NWAL2020Scrn00_InsertNewCard".equals(screenAplID)) {
                doProcess_NWAL2020Scrn00_InsertNewCard(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2020_INIT(NWAL2020CMsg bizMsg, NWAL2020SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrTsTxt_T, ZYPCodeDataUtil.getVarCharConstValue(TCEPPS_TOKEN_EXPR_INTVL, getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(bizMsg.appNm_T, ZYPCodeDataUtil.getVarCharConstValue(TCEPPS_KEY_NM, getGlobalCompanyCode()));

        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return;
        }
    }

    /**
     * CLEAR Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2020Scrn00_CMN_Clear(NWAL2020CMsg cMsg, NWAL2020SMsg sMsg) {
        // Provisional state
    }

    /**
     * InsertNewCard Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2020Scrn00_InsertNewCard(NWAL2020CMsg bizMsg, NWAL2020SMsg glblMsg) {

        if (NWAL2020CommonLogic.callCrCardValidApi(bizMsg, glblMsg, getGlobalCompanyCode(), null, null)) {
            NWAL2020CommonLogic.addCrCardInfo(bizMsg, glblMsg, getGlobalCompanyCode());
        }
    }

}
