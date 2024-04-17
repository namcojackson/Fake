/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.NWAL2160_PRM_NUM;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.POP_UP_TIER_PRICING;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.PROC_MD_REF;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.PROC_MD_UPD;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2180Scrn00_OpenWin_TierPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         M.Yamada        Create          N/A
 * 2016/08/08   Fujitsu         M.Yamada        Update          QC#11721
 * 2017/05/31   Fujitsu         S.Ohki          Update          RS#8233
 *</pre>
 */
public class NWAL2180Scrn00_OpenWin_TierPricing extends S21CommonHandler {

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

        int ixZ = scrnMsg.xxNum_Z.getValueInt();
        NWAL2180_ZBMsg zScrnMsg = scrnMsg.Z.no(ixZ);
        NWAL2180_ABMsg aScrnMsg = getAscrnMsg(scrnMsg, scrnMsg.Z.no(ixZ).mdlId_Z.getValue());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, PROC_MD_REF);

        // Set Parameters
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, POP_UP_TIER_PRICING);
        int ixP = 0;
        Object[] params = new Object[NWAL2160_PRM_NUM];
        params[ixP++] = scrnMsg.xxPopPrm_P1;
        params[ixP++] = aScrnMsg.cpoSvcPrcPk_A;
        params[ixP++] = scrnMsg.cpoSvcDtlPk;
        params[ixP++] = aScrnMsg.mdlId_A;
        params[ixP++] = zScrnMsg.bllgMtrLbCd_Z;
        params[ixP++] = scrnMsg.dsContrCatgCd;
        //
        params[ixP++] = "Q";
        params[ixP++] = getTierInfo(scrnMsg, aScrnMsg.mdlId_A.getValue(), zScrnMsg.bllgMtrLbCd_Z.getValue());

        setArgForSubScreen(params);
    }

    private NWAL2180_QBMsgArray getTierInfo(NWAL2180BMsg scrnMsg, BigDecimal mdlId, String bllgMtrLbCd) {
        ZYPTableUtil.clear(scrnMsg.Q);
        NWAL2180_QBMsgArray qScrnMsgAry = scrnMsg.Q;

        int ixQ = 0;
        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            NWAL2180_XBMsg xScrnMsg = scrnMsg.X.no(i);
            if (!NWAL2180CommonLogic.isFleet(scrnMsg) //
                    && !ZYPCommonFunc.hasValue(xScrnMsg.mdlId_X)) {
                continue;
            }
            if (!NWAL2180CommonLogic.isFleet(scrnMsg) //
                    && mdlId.compareTo(xScrnMsg.mdlId_X.getValue()) != 0) {
                continue;
            }
            if (!bllgMtrLbCd.equals(xScrnMsg.bllgMtrLbCd_X.getValue())) {
                continue;
            }

            EZDMsg.copy(xScrnMsg, "X", qScrnMsgAry.no(ixQ), "Q");
            ixQ++;
        }
        qScrnMsgAry.setValidCount(ixQ);
        return qScrnMsgAry;
    }

    private NWAL2180_ABMsg getAscrnMsg(NWAL2180BMsg scrnMsg, BigDecimal mdlId) {
        if (NWAL2180CommonLogic.isFleet(scrnMsg)) {
            return scrnMsg.A.no(0);
        }
        NWAL2180_ABMsg aScrnMsg = new NWAL2180_ABMsg();
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return aScrnMsg;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            aScrnMsg = scrnMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aScrnMsg.mdlId_A)) {
                continue;
            }
            if (mdlId.compareTo(aScrnMsg.mdlId_A.getValue()) == 0) {
                return aScrnMsg;
            }
        }
        return aScrnMsg;
    }
}
