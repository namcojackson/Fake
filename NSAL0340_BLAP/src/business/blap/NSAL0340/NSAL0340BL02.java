/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0340;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0340.constant.NSAL0340Constant;
import business.db.DS_CONTR_SKIP_RECOVTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/20   Hitachi         T.Tomita        Update          N/A
 * 2017/09/13   Hitachi         K.Kim           Update          QC#19938
 *</pre>
 */
public class NSAL0340BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0340_INIT".equals(screenAplID)) {
                doProcess_NSAL0340_INIT((NSAL0340CMsg) cMsg, (NSAL0340SMsg) sMsg);
            } else if ("NSAL0340Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0340Scrn00_CMN_Submit((NSAL0340CMsg) cMsg, (NSAL0340SMsg) sMsg);
            } else if ("NSAL0340Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0340Scrn00_CMN_Reset((NSAL0340CMsg) cMsg, (NSAL0340SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0340CMsg
     * @param sMsg NSAL0340SMsg
     */
    private void doProcess_NSAL0340_INIT(NSAL0340CMsg cMsg, NSAL0340SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        if (!checkInparam(cMsg)) {
            return;
        }

        ZYPCodeDataUtil.createPulldownList(SKIP_RECOV_TP.class, cMsg.skipRecovTpCd_L0, cMsg.skipRecovTpDescTxt_L0);

        if (!getHdrData(cMsg)) {
            return;
        }

        getDtlData(cMsg, sMsg);

        cMsg.actvFlg.setValue(getActvFlg(cMsg));

        // START 2017/09/13 K.Kim [QC#19938, ADD]
        if (checkBillSchdStsClsInfo(cMsg)){
            cMsg.actvFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        // END 2017/09/13 K.Kim [QC#19938, ADD]
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL0340CMsg
     * @param sMsg NSAL0340SMsg
     */
    private void doProcess_NSAL0340Scrn00_CMN_Reset(NSAL0340CMsg cMsg, NSAL0340SMsg sMsg) {

        doProcess_NSAL0340_INIT(cMsg, sMsg);
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0340CMsg
     * @param sMsg NSAL0340SMsg
     */
    private void doProcess_NSAL0340Scrn00_CMN_Submit(NSAL0340CMsg cMsg, NSAL0340SMsg sMsg) {

        getDtlData(cMsg, sMsg);
        cMsg.setMessageInfo(NSAL0340Constant.ZZZM9003I, new String[] {NSAL0340Constant.SUBMIT });
    }

    /**
     * check Input Parameter
     * @param cMsg NSAL0340CMsg
     * @return No Error : true
     */
    private boolean checkInparam(NSAL0340CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk.getValue())) {
            cMsg.setMessageInfo(NSAL0340Constant.NSAM0219E, new String[] {NSAL0340Constant.NO_INPUT });
            return false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.svcInvChrgTpCd.getValue())) {
            if ((!NSAL0340Query.getInstance().isExsistSvcInvChrgTp(cMsg))) {
                cMsg.setMessageInfo(NSAL0340Constant.NSAM0219E, new String[] {NSAL0340Constant.INVALID_PARAM });
                return false;
            }
        }

        return true;
    }

    /**
     * get Header Data
     * @param cMsg NSAL0340CMsg
     * @return No Error : true
     */
    private boolean getHdrData(NSAL0340CMsg cMsg) {

        S21SsmEZDResult ssmHdrResult = NSAL0340Query.getInstance().getHdrData(cMsg);

        if (ssmHdrResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAL0340Constant.NSAM0219E, new String[] {NSAL0340Constant.NO_HDR_DATA });
            return false;
        }

        return true;
    }

    /**
     * get Detail Data
     * @param cMsg NSAL0340CMsg
     * @param sMsg NSAL0340SMsg
     */
    private void getDtlData(NSAL0340CMsg cMsg, NSAL0340SMsg sMsg) {

        NSAL0340Query.getInstance().getDtlData(cMsg);

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0340_ACMsg acMsg = cMsg.A.no(i);
            acMsg.xxDtlNm_A0.setValue(NSAL0340Constant.MONTH_LIST_MAP.get(acMsg.skipRecovMth_A0.getValue()));
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(i), null);
        }
    }

    /**
     * get Active Flag
     * @param cMsg NSAL0340CMsg
     * @return Active Flag
     */
    private String getActvFlg(NSAL0340CMsg cMsg) {
        // START 2015/10/20 T.Tomita [N/A, MOD]
//        String dsContrStsCd = cMsg.dsContrStsCd.getValue();
//        if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd)) {
//            return ZYPConstant.FLG_OFF_N;
//        } else if (DS_CONTR_DTL_STS.INACTIVE.equals(cMsg.dsContrDtlStsCd.getValue())) {
//            return ZYPConstant.FLG_OFF_N;
//        }
        if (NSAL0340Constant.REF_MODE.equals(cMsg.xxModeCd.getValue())) {
            return ZYPConstant.FLG_OFF_N;
        }
        // END 2015/10/20 T.Tomita [N/A, MOD]
        return ZYPConstant.FLG_ON_Y;
    }

    // START 2017/09/13 K.Kim [QC#19938, ADD]
    /**
     * check Billing Shedule Status Close DS_CONTR_BLLG_SCHD
     * @param cMsg NSAL0340CMsg
     * @return true : closed
     */
    private boolean checkBillSchdStsClsInfo(NSAL0340CMsg cMsg) {
        String billWithEquipFlg = NSAL0340Query.getInstance().getBillWithEquipmentFlg(cMsg);

        if (ZYPConstant.FLG_ON_Y.equals(billWithEquipFlg)) {
            String svcInvChrgTpCd = cMsg.svcInvChrgTpCd.getValue();
            if (!ZYPCommonFunc.hasValue(svcInvChrgTpCd)) {
                svcInvChrgTpCd = SVC_INV_CHRG_TP.BASE_CHARGE;
            }
            return NSAL0340Query.getInstance().isBillSchdStsClsInfo(cMsg, svcInvChrgTpCd);
        }
        return false;
    }
    // END 2017/09/13 K.Kim [QC#19938, ADD]
}
