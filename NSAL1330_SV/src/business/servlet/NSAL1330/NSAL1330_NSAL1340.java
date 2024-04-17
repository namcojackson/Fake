/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.FLEET;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_BAND_LIST;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_BAND_LIST_CONFIG;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330_NSAL1340 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = new NSAL1330CMsg();

        // IN Parameter Get
        Serializable arg = getArgForSubScreen();
        int ix = scrnMsg.xxNum_Z.getValueInt();
        if (POP_UP_BAND_LIST.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            if (arg instanceof Object[]) {
                setRequestDataBandList(scrnMsg, ix);
            }

        } else if (POP_UP_BAND_LIST_CONFIG.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            if (arg instanceof Object[]) {
                setRequestDataBandListConfig(scrnMsg, ix);
            }

        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (POP_UP_BAND_LIST.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                //            NSAL1330CommonLogic.setBandButton(this, scrnMsg.Z.no(i).usgMdseCd_Z.getValue(), i);
                NSAL1330CommonLogic.setBandButton(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.Z.no(i)), i);

            }
            NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
            NSAL1330CommonLogic.addCheckItems(scrnMsg);

            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
            NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(scrnMsg.xxNum_Z.getValueInt());
            if (!ZYPCommonFunc.hasValue(zScrnMsg.prcListBandDescTxt_Z)) {
                scrnMsg.setFocusItem(zScrnMsg.prcListBandDescTxt_Z);

            } else if (!ZYPCommonFunc.hasValue(zScrnMsg.prcBookMdseCd_Z)) {
                scrnMsg.setFocusItem(zScrnMsg.prcBookMdseCd_Z);

            } else if (!ZYPCommonFunc.hasValue(zScrnMsg.mlyCopyInclPrcQty_Z)) {
                scrnMsg.setFocusItem(zScrnMsg.mlyCopyInclPrcQty_Z);

            } else if (!ZYPCommonFunc.hasValue(zScrnMsg.xsMtrAmtRate_Z)) {
                scrnMsg.setFocusItem(zScrnMsg.xsMtrAmtRate_Z);

            } else {
                scrnMsg.setFocusItem(zScrnMsg.mlyCopyInclPrcQty_Z);
            }
        } else if (POP_UP_BAND_LIST_CONFIG.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
                NSAL1330CommonLogic.setBandButtonConfig(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.U.no(i)), i);

            }
            NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
            NSAL1330CommonLogic.addCheckItems(scrnMsg);

            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(scrnMsg.xxNum_Z.getValueInt());
            if (!ZYPCommonFunc.hasValue(uScrnMsg.prcListBandDescTxt_U)) {
                scrnMsg.setFocusItem(uScrnMsg.prcListBandDescTxt_U);

            } else if (!ZYPCommonFunc.hasValue(uScrnMsg.prcBookMdseCd_U)) {
                scrnMsg.setFocusItem(uScrnMsg.prcBookMdseCd_U);

            } else if (!ZYPCommonFunc.hasValue(uScrnMsg.mlyCopyInclPrcQty_U)) {
                scrnMsg.setFocusItem(uScrnMsg.mlyCopyInclPrcQty_U);

            } else if (!ZYPCommonFunc.hasValue(uScrnMsg.xsMtrAmtRate_U)) {
                scrnMsg.setFocusItem(uScrnMsg.xsMtrAmtRate_U);

            } else {
                scrnMsg.setFocusItem(uScrnMsg.mlyCopyInclPrcQty_U);
            }
        }
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param ixZ int
     */
    private void setRequestDataBandList(NSAL1330BMsg scrnMsg, int ixZ) {
        NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(ixZ);
        ZYPEZDItemValueSetter.setValue(zScrnMsg.prcListBandDescTxt_Z, scrnMsg.prcListBandDescTxt_P);
        ZYPEZDItemValueSetter.setValue(zScrnMsg.prcBookMdseCd_Z, scrnMsg.mdseCd_P);

        BigDecimal zModelId = zScrnMsg.mdlId_Z.getValue();
        String zModelNm = zScrnMsg.t_MdlNm_Z.getValue();

        for (int i = ixZ + 1; i < scrnMsg.Z.getValidCount(); i++) {
            if (!zModelNm.equals(scrnMsg.Z.no(i).t_MdlNm_Z.getValue()) || //
                    !scrnMsg.Z.no(i).bllgMtrLbCd_Z.getValue().equals(zScrnMsg.bllgMtrLbCd_Z.getValue())) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(i).prcListBandDescTxt_Z, scrnMsg.prcListBandDescTxt_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(i).prcBookMdseCd_Z, scrnMsg.mdseCd_P);
        }

        boolean isFleet = false;
        int ixA = 0;
        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(ixA);
        for (; ixA < scrnMsg.A.getValidCount(); ixA++) {
            aScrnMsg = scrnMsg.A.no(ixA);
            if (FLEET.equals(zModelNm) && FLEET.equals(aScrnMsg.t_MdlNm_A.getValue())) {
                isFleet = true;
                break;
            }
            if (zModelId.compareTo(aScrnMsg.mdlId_A.getValue()) == 0) {
                break;
            }
        }
        BigDecimal qty = aScrnMsg.xxTotQtyCnt_A.getValue();
        if (!ZYPCommonFunc.hasValue(scrnMsg.baseAmt_P)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.baseAmt_P, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(aScrnMsg.xxTotPrcAmt_PB, scrnMsg.baseAmt_P);
        if (isFleet) {
            ZYPEZDItemValueSetter.setValue(aScrnMsg.xxTotPrcAmt_EB, scrnMsg.baseAmt_P.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(aScrnMsg.xxTotPrcAmt_EB, qty.multiply(scrnMsg.baseAmt_P.getValue()));
        }

        // Non-Tier
        if (!PRC_LIST_TP.SERVICE_TIERS.equals(aScrnMsg.prcListTpCd_A.getValue()) //
                && !ZYPCommonFunc.hasValue(aScrnMsg.prcTierSvcOfferCd_A)) {
            // 20160308 mod
            NSAL1330CommonLogic.setCopyInclPrcQty(scrnMsg, ixZ);

            ZYPEZDItemValueSetter.setValue(zScrnMsg.mlyCopyInclPrcQty_BK, zScrnMsg.mlyCopyInclPrcQty_Z);
            ZYPEZDItemValueSetter.setValue(zScrnMsg.xsMtrAmtRate_Z, scrnMsg.cpcAmtRate_P);
            ZYPEZDItemValueSetter.setValue(zScrnMsg.xsMtrAmtRate_BK, scrnMsg.cpcAmtRate_P);
        }
        scrnMsg.xxNum_A.setValue(ixA);
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param ixU int
     */
    private void setRequestDataBandListConfig(NSAL1330BMsg scrnMsg, int ixU) {
        NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
        ZYPEZDItemValueSetter.setValue(uScrnMsg.prcListBandDescTxt_U, scrnMsg.prcListBandDescTxt_P);
        ZYPEZDItemValueSetter.setValue(uScrnMsg.prcBookMdseCd_U, scrnMsg.mdseCd_P);

        BigDecimal uModelId = uScrnMsg.mdlId_U.getValue();
//        BigDecimal uCpoSvcConfigRefPk = uScrnMsg.cpoSvcConfigRefPk_U.getValue();
        BigDecimal uDsContrDtlPk = uScrnMsg.dsContrDtlPk_U.getValue();
        String uModelNm = uScrnMsg.t_MdlNm_U.getValue();

        for (int i = ixU + 1; i < scrnMsg.U.getValidCount(); i++) {
            if (!uModelNm.equals(scrnMsg.U.no(i).t_MdlNm_U.getValue()) //
//                    || !uCpoSvcConfigRefPk.equals(scrnMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) //
                    || !uDsContrDtlPk.equals(scrnMsg.U.no(i).dsContrDtlPk_U.getValue()) //
                    || !scrnMsg.U.no(i).bllgMtrLbCd_U.getValue().equals(uScrnMsg.bllgMtrLbCd_U.getValue())) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(i).prcListBandDescTxt_U, scrnMsg.prcListBandDescTxt_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(i).prcBookMdseCd_U, scrnMsg.mdseCd_P);
        }

        int ixR = 0;
//        for (; ixR < scrnMsg.R.getValidCount(); ixR++) {
//            if (uModelId.compareTo(scrnMsg.R.no(ixR).mdlId_R.getValue()) == 0 //
//                    && uCpoSvcConfigRefPk.compareTo(scrnMsg.R.no(ixR).cpoSvcConfigRefPk_R.getValue()) == 0) {
//                break;
//            }
//        }
        for (; ixR < scrnMsg.R.getValidCount(); ixR++) {
            if (uModelId.compareTo(scrnMsg.R.no(ixR).mdlId_R.getValue()) == 0 //
                    && uDsContrDtlPk.compareTo(scrnMsg.R.no(ixR).dsContrDtlPk_R.getValue()) == 0) {
                break;
            }
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.baseAmt_P)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.baseAmt_P, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(ixR).xxTotPrcAmt_PR, scrnMsg.baseAmt_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(ixR).xxTotPrcAmt_ER, scrnMsg.baseAmt_P);

        // Non-Tier
        if (!PRC_LIST_TP.SERVICE_TIERS.equals(scrnMsg.R.no(ixR).prcListTpCd_R.getValue()) //
                && !ZYPCommonFunc.hasValue(scrnMsg.R.no(ixR).prcTierSvcOfferCd_R)) {
            NSAL1330CommonLogic.setCopyInclPrcQtyConfig(scrnMsg, ixU);

            ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(ixU).xsMtrAmtRate_U, scrnMsg.cpcAmtRate_P);
        }

        scrnMsg.xxNum_A.setValue(ixR);
    }
}
