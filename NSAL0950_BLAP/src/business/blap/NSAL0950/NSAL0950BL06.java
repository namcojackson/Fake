/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0950;

import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0454E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0456E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.ZZZM9003I;
import static business.blap.NSAL0950.constant.NSAL0950Constant.NSAM0035E;
import static business.blap.NSAL0950.constant.NSAL0950Constant.FIELD_NM_DS_CONTR_VLD_NM;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0950.common.NSAL0950CommonLogic;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract Validation Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/07/07   Hitachi         O.Okuma         Update          QC#10991
 *</pre>
 */
public class NSAL0950BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0950Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0950Scrn00_CMN_Submit((NSAL0950CMsg) cMsg, (NSAL0950SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

  /**
     * do process (Submit)
     * @param cMsg NSAL0950CMsg
     * @param sMsg NSAL0950SMsg
     */
    private void doProcess_NSAL0950Scrn00_CMN_Submit(NSAL0950CMsg cMsg, NSAL0950SMsg sMsg) {
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0456E);
            return;
        }
        int submitRow = cMsg.xxRadioBtn_A.getValueInt();
        if (submitRow < 0) {
            cMsg.setMessageInfo(NSAM0454E);
            return;
        }
        // START 2016/07/07 [QC#10991, ADD]
        if (!NSAL0950CommonLogic.cheakDsContrVldNm(cMsg, cMsg.A.no(submitRow).dsContrVldPk_A.getValue())) {
            cMsg.dsContrVldCatgCd_DS.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_DS_CONTR_VLD_NM });
            cMsg.dsContrVldNm_D.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_DS_CONTR_VLD_NM });
            cMsg.effFromDt_D.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_DS_CONTR_VLD_NM });
            cMsg.effToDt_D.setErrorInfo(1, NSAM0035E, new String[] {FIELD_NM_DS_CONTR_VLD_NM });
            return;
        }
        // END 2016/07/07 [QC#10991, ADD]

        NSAL0950CommonLogic.submitDsContrVld(cMsg);
        if (cMsg.A.getValidCount() > 0) {
            NSAL0950CommonLogic.setDetailDsContrVld(cMsg, sMsg, 0);
        }
        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
            return;
        }
    }
}
