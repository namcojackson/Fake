/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.NWAL2160_PRM_NUM;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.POP_UP_TIER_PRICING_CONFIG;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.PROC_MD_REF;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.PROC_MD_UPD;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2180Scrn00_OpenWin_TierPricingByConfig
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/07   Fujitsu         M.Yamada        Create          N/A
 * 2017/05/31   Fujitsu         S.Ohki          Update          RS#8233
 *</pre>
 */
public class NWAL2180Scrn00_OpenWin_TierPricingByConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        scrnMsg.xxNum_Z.setValue(getButtonSelectNumber());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        int ixU = scrnMsg.xxNum_Z.getValueInt();
        NWAL2180_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
        NWAL2180_RBMsg rScrnMsg = getRscrnMsg(scrnMsg, scrnMsg.U.no(ixU).mdlId_U.getValue(), scrnMsg.U.no(ixU).cpoSvcConfigRefPk_U.getValue());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, PROC_MD_REF);

        // Set Parameters
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, POP_UP_TIER_PRICING_CONFIG);
        int ixP = 0;
        Object[] params = new Object[NWAL2160_PRM_NUM];
        params[ixP++] = scrnMsg.xxPopPrm_P1;
        params[ixP++] = rScrnMsg.cpoSvcPrcPk_R;
        params[ixP++] = scrnMsg.cpoSvcDtlPk;
        params[ixP++] = rScrnMsg.mdlId_R;
        params[ixP++] = uScrnMsg.bllgMtrLbCd_U;
        params[ixP++] = scrnMsg.dsContrCatgCd;
        //
        params[ixP++] = "Q";
        params[ixP++] = getTierInfo(//
                scrnMsg, rScrnMsg.mdlId_R.getValue(), uScrnMsg.cpoSvcConfigRefPk_U.getValue(), uScrnMsg.bllgMtrLbCd_U.getValue());

        setArgForSubScreen(params);
    }

    private NWAL2180_QBMsgArray getTierInfo(//
            NWAL2180BMsg scrnMsg, BigDecimal mdlId, BigDecimal cpoSvcConfigRefPk, String bllgMtrLbCd) {
        ZYPTableUtil.clear(scrnMsg.Q);
        NWAL2180_QBMsgArray qScrnMsgAry = scrnMsg.Q;

        int ixQ = 0;
        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
            NWAL2180_VBMsg vScrnMsg = scrnMsg.V.no(i);
            if (!ZYPCommonFunc.hasValue(vScrnMsg.mdlId_V)) {
                continue;
            }
            if (mdlId.compareTo(vScrnMsg.mdlId_V.getValue()) != 0 //
                    || cpoSvcConfigRefPk.compareTo(vScrnMsg.cpoSvcConfigRefPk_V.getValue()) != 0) {
                continue;
            }
            if (!bllgMtrLbCd.equals(vScrnMsg.bllgMtrLbCd_V.getValue())) {
                continue;
            }

            EZDMsg.copy(vScrnMsg, "V", qScrnMsgAry.no(ixQ), "Q");
            ixQ++;
        }
        qScrnMsgAry.setValidCount(ixQ);
        return qScrnMsgAry;
    }

    private NWAL2180_RBMsg getRscrnMsg(NWAL2180BMsg scrnMsg, BigDecimal mdlId, BigDecimal cpoSvcConfigRefPk) {
        NWAL2180_RBMsg rScrnMsg = new NWAL2180_RBMsg();
        if (!ZYPCommonFunc.hasValue(mdlId) || !ZYPCommonFunc.hasValue(cpoSvcConfigRefPk)) {
            return rScrnMsg;
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            rScrnMsg = scrnMsg.R.no(i);
            if (!ZYPCommonFunc.hasValue(rScrnMsg.mdlId_R)) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(rScrnMsg.cpoSvcConfigRefPk_R)) {
                continue;
            }
            if (mdlId.compareTo(rScrnMsg.mdlId_R.getValue()) == 0 //
                    && cpoSvcConfigRefPk.compareTo(rScrnMsg.cpoSvcConfigRefPk_R.getValue()) == 0) {
                return rScrnMsg;
            }
        }
        return rScrnMsg;
    }
}
