/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAL1370_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_TIER_PRICING;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.PROC_MD_REF;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.PROC_MD_UPD;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OpenWin_TierPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/21   Hitachi         N.Arai          Update          QC#19368
 * 2017/07/05   Hitachi         Y.Takeno        Update          QC#19479
 * 2017/10/26   Hitachi         Y.Takeno        Update          QC#21556
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_TierPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.xxNum_Z.setValue(getButtonSelectNumber());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        int ixZ = scrnMsg.xxNum_Z.getValueInt();
        NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(ixZ);
        NSAL1330_ABMsg aScrnMsg = getAscrnMsg(scrnMsg, scrnMsg.Z.no(ixZ).mdlId_Z.getValue());

        if (ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP) //
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.manContrOvrdFlg.getValue()) //
                // START 2017/10/26 [QC#21556, DEL]
                // && ZYPConstant.FLG_OFF_N.equals(scrnMsg.contrAvalFlg.getValue()) //
                // END   2017/10/26 [QC#21556, DEL]
                && ZYPConstant.FLG_ON_Y.equals(aScrnMsg.scrEntAvalFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, PROC_MD_UPD);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, PROC_MD_REF);
        }

        // Set Parameters
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, POP_UP_TIER_PRICING);
        int ixP = 0;
        Object[] params = new Object[NSAL1370_PRM_NUM];
        params[ixP++] = scrnMsg.xxPopPrm_P1;
        params[ixP++] = zScrnMsg.dsContrDtlPk_Z;
        params[ixP++] = zScrnMsg.dsContrBllgMtrPk_Z;
        params[ixP++] = aScrnMsg.mdlId_A;
        params[ixP++] = zScrnMsg.bllgMtrLbCd_Z;
        params[ixP++] = scrnMsg.dsContrCatgCd;
        //
        params[ixP++] = "Q";
        params[ixP++] = getTierInfo(scrnMsg, aScrnMsg.mdlId_A.getValue(), zScrnMsg.bllgMtrLbCd_Z.getValue());
        // START 2017/10/26 [QC#21556, ADD]
        params[ixP++] = scrnMsg.dsContrPk_AD;
        // END   2017/10/26 [QC#21556, ADD]

        setArgForSubScreen(params);
    }

    private NSAL1330_QBMsgArray getTierInfo(NSAL1330BMsg scrnMsg, BigDecimal mdlId, String bllgMtrLbCd) {
        ZYPTableUtil.clear(scrnMsg.Q);
        NSAL1330_QBMsgArray qScrnMsgAry = scrnMsg.Q;
        // START 2017/07/05 [QC#19479, DEL]
        // START 2017/06/21 N.Arai [QC#19368, MOD]
        // BigDecimal preContrXsCopPK = BigDecimal.ZERO;
        // END   2017/07/05 [QC#19479, DEL]
        int ixQ = 0;
        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            NSAL1330_XBMsg xScrnMsg = scrnMsg.X.no(i);
            if (!NSAL1330CommonLogic.isFleet(scrnMsg) //
                    && !ZYPCommonFunc.hasValue(xScrnMsg.mdlId_X)) {
                continue;
            }
            if (!NSAL1330CommonLogic.isFleet(scrnMsg) //
                    && mdlId.compareTo(xScrnMsg.mdlId_X.getValue()) != 0) {
                continue;
            }
            if (!bllgMtrLbCd.equals(xScrnMsg.bllgMtrLbCd_X.getValue())) {
                continue;
            }
            // START 2017/07/05 [QC#19479, DEL]
            // if (preContrXsCopPK.compareTo(xScrnMsg.contrXsCopyPk_X.getValue()) == 0) {
            //     continue;
            // }
            // END   2017/07/05 [QC#19479, DEL]
            EZDMsg.copy(xScrnMsg, "X", qScrnMsgAry.no(ixQ), "Q");
            ixQ++;
            // START 2017/07/05 [QC#19479, DEL]
            // preContrXsCopPK = xScrnMsg.contrXsCopyPk_X.getValue();
            // END   2017/07/05 [QC#19479, DEL]
        }
        // END 2017/06/21 N.Arai [QC#19368, MOD]
        qScrnMsgAry.setValidCount(ixQ);
        return qScrnMsgAry;
    }

    private NSAL1330_ABMsg getAscrnMsg(NSAL1330BMsg scrnMsg, BigDecimal mdlId) {
        if (NSAL1330CommonLogic.isFleet(scrnMsg)) {
            return scrnMsg.A.no(0);
        }
        NSAL1330_ABMsg aScrnMsg = new NSAL1330_ABMsg();
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
