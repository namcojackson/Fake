/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAL1370_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_TIER_PRICING_CONFIG;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.PROC_MD_REF;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.PROC_MD_UPD;

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
 * NSAL1330Scrn00_OpenWin_TierPricingByConfig
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/21   Hitachi         N.Arai          Update          QC#19368
 * 2017/07/05   Hitachi         Y.Takeno        Update          QC#19479
 * 2017/10/26   Hitachi         Y.Takeno        Update          QC#21556
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_TierPricingByConfig extends S21CommonHandler {

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

        int ixU = scrnMsg.xxNum_Z.getValueInt();
        NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
        NSAL1330_RBMsg rScrnMsg = getRscrnMsg(scrnMsg, scrnMsg.U.no(ixU).mdlId_U.getValue(), scrnMsg.U.no(ixU).dsContrDtlPk_U.getValue());

        if (ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP) //
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.manContrOvrdFlg.getValue()) //
                // START 2017/10/26 [QC#21556, DEL]
                // && ZYPConstant.FLG_OFF_N.equals(scrnMsg.contrAvalFlg.getValue()) //
                // END   2017/10/26 [QC#21556, DEL]
                && ZYPConstant.FLG_ON_Y.equals(rScrnMsg.scrEntAvalFlg_R.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, PROC_MD_UPD);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, PROC_MD_REF);
        }

        // Set Parameters
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, POP_UP_TIER_PRICING_CONFIG);
        int ixP = 0;
        Object[] params = new Object[NSAL1370_PRM_NUM];
        params[ixP++] = scrnMsg.xxPopPrm_P1;
        params[ixP++] = uScrnMsg.dsContrDtlPk_U;
        params[ixP++] = uScrnMsg.dsContrBllgMtrPk_U;
        params[ixP++] = rScrnMsg.mdlId_R;
        params[ixP++] = uScrnMsg.bllgMtrLbCd_U;
        params[ixP++] = scrnMsg.dsContrCatgCd;
        //
        params[ixP++] = "Q";
        params[ixP++] = getTierInfo(//
                scrnMsg, rScrnMsg.mdlId_R.getValue(), uScrnMsg.dsOrdPosnNum_U.getValue(), uScrnMsg.bllgMtrLbCd_U.getValue());
        // START 2017/10/26 [QC#21556, ADD]
        params[ixP++] = scrnMsg.dsContrPk_AD;
        // END   2017/10/26 [QC#21556, ADD]

        setArgForSubScreen(params);
    }

    private NSAL1330_QBMsgArray getTierInfo(//
            NSAL1330BMsg scrnMsg, BigDecimal mdlId, String dsOrdPosnNum, String bllgMtrLbCd) {
        ZYPTableUtil.clear(scrnMsg.Q);
        NSAL1330_QBMsgArray qScrnMsgAry = scrnMsg.Q;
        // START 2017/07/05 [QC#19479, DEL]
        // START 2017/06/21 N.Arai [QC#19368, MOD]
        // BigDecimal preContrXsCopPK = BigDecimal.ZERO;
        // END   2017/07/05 [QC#19479, DEL]

        int ixQ = 0;
        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
            NSAL1330_VBMsg vScrnMsg = scrnMsg.V.no(i);
            if (!ZYPCommonFunc.hasValue(vScrnMsg.mdlId_V)) {
                continue;
            }
            if (mdlId.compareTo(vScrnMsg.mdlId_V.getValue()) != 0 //
                    || dsOrdPosnNum.compareTo(vScrnMsg.dsOrdPosnNum_V.getValue()) != 0) {
                continue;
            }
            if (!bllgMtrLbCd.equals(vScrnMsg.bllgMtrLbCd_V.getValue())) {
                continue;
            }
            // START 2017/07/05 [QC#19479, DEL]
            // if (preContrXsCopPK.compareTo(vScrnMsg.contrXsCopyPk_V.getValue()) == 0) {
            //     continue;
            // }
            // END   2017/07/05 [QC#19479, DEL]

            EZDMsg.copy(vScrnMsg, "V", qScrnMsgAry.no(ixQ), "Q");
            ixQ++;
            // START 2017/07/05 [QC#19479, DEL]
            // preContrXsCopPK = vScrnMsg.contrXsCopyPk_V.getValue();
            // END   2017/07/05 [QC#19479, DEL]
        }
        // END 2017/06/21 N.Arai [QC#19368, MOD]
        qScrnMsgAry.setValidCount(ixQ);
        return qScrnMsgAry;
    }

    private NSAL1330_RBMsg getRscrnMsg(NSAL1330BMsg scrnMsg, BigDecimal mdlId, BigDecimal dsContrDtlPk) {
        NSAL1330_RBMsg rScrnMsg = new NSAL1330_RBMsg();
        if (!ZYPCommonFunc.hasValue(mdlId) || !ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return rScrnMsg;
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            rScrnMsg = scrnMsg.R.no(i);
            if (!ZYPCommonFunc.hasValue(rScrnMsg.mdlId_R)) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(rScrnMsg.dsContrDtlPk_R)) {
                continue;
            }
            if (mdlId.compareTo(rScrnMsg.mdlId_R.getValue()) == 0 //
                    && dsContrDtlPk.compareTo(rScrnMsg.dsContrDtlPk_R.getValue()) == 0) {
                return rScrnMsg;
            }
        }
        return rScrnMsg;
    }
}
