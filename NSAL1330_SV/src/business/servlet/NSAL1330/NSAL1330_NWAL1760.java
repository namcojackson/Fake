/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.FLEET;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_AC;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_AC_P;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_AD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_RE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_RE_P;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_SP;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_SPC;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/04   Hitachi         Y.Takeno        Update          QC#20443
 *</pre>
 */
public class NSAL1330_NWAL1760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        int index = scrnMsg.xxNum_A.getValueInt();

        if (POP_UP_SVC_PRC_SP.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            NSAL1330CommonLogic.clearSvcPrcListInfo(scrnMsg, index);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcCatgCd_A, scrnMsg.P.no(9).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcCatgNm_A, scrnMsg.P.no(10).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcListTpCd_A, scrnMsg.P.no(11).xxPopPrm_P);

            for (int j = 0; j < scrnMsg.Z.getValidCount(); j++) {
                if (FLEET.equals(scrnMsg.A.no(index).t_MdlNm_A.getValue()) //
                        || scrnMsg.A.no(index).mdlId_A.getValue().compareTo(scrnMsg.Z.no(j).mdlId_Z.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(j).prcListTpCd_Z, scrnMsg.A.no(index).prcListTpCd_A);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(j).prcCatgCd_Z, scrnMsg.A.no(index).prcCatgCd_A);
                }
            }

            scrnMsg.A.no(index).prcMtrPkgPk_A.clear();

            NSAL1330CMsg bizMsg = new NSAL1330CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else if (POP_UP_SVC_PRC_SPC.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            NSAL1330CommonLogic.clearSvcPrcListInfoForConfig(scrnMsg, index);
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(index).prcCatgCd_R, scrnMsg.P.no(9).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(index).prcCatgNm_R, scrnMsg.P.no(10).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(index).prcListTpCd_R, scrnMsg.P.no(11).xxPopPrm_P);

            for (int j = 0; j < scrnMsg.U.getValidCount(); j++) {
                if (FLEET.equals(scrnMsg.R.no(index).t_MdlNm_R.getValue()) //
                        || (scrnMsg.R.no(index).mdlId_R.getValue().compareTo(scrnMsg.U.no(j).mdlId_U.getValue()) == 0 //
//                        && scrnMsg.R.no(index).cpoSvcConfigRefPk_R.getValue().compareTo(scrnMsg.U.no(j).cpoSvcConfigRefPk_U.getValue()) == 0)) {
                        && scrnMsg.R.no(index).dsContrDtlPk_R.getValue().compareTo(scrnMsg.U.no(j).dsContrDtlPk_U.getValue()) == 0)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(j).prcListTpCd_U, scrnMsg.R.no(index).prcListTpCd_R);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(j).prcCatgCd_U, scrnMsg.R.no(index).prcCatgCd_R);
                }
            }

            scrnMsg.R.no(index).prcMtrPkgPk_R.clear();

            NSAL1330CMsg bizMsg = new NSAL1330CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else if (POP_UP_SVC_PRC_AC_P.equals(scrnMsg.xxPopPrm_P1.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(index).addlBasePrcCatgCd_J, scrnMsg.P.no(9).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(index).prcCatgNm_J, scrnMsg.P.no(10).xxPopPrm_P);

            NSAL1330CMsg bizMsg = new NSAL1330CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        // START 2017/08/04 [QC#20443, ADD]
        } else if (POP_UP_SVC_PRC_AC.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_HJ, scrnMsg.P.no(10).xxPopPrm_P);

            NSAL1330CMsg bizMsg = new NSAL1330CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        // END   2017/08/04 [QC#20443, ADD]

        } else if (POP_UP_SVC_PRC_RE_P.equals(scrnMsg.xxPopPrm_P1.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).addlBasePrcCatgCd_B, scrnMsg.P.no(9).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(index).prcCatgNm_B, scrnMsg.P.no(10).xxPopPrm_P);

            NSAL1330CMsg bizMsg = new NSAL1330CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else if (POP_UP_SVC_PRC_RE.equals(scrnMsg.xxPopPrm_P1.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_HB, scrnMsg.P.no(9).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_HB, scrnMsg.P.no(10).xxPopPrm_P);

            NSAL1330CMsg bizMsg = new NSAL1330CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        }
        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        if (POP_UP_SVC_PRC_SP.equals(scrnMsg.xxPopPrm_P1.getValue())) { // Model Service Price List

            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(i);
                NSAL1330CommonLogic.setSupplyButton(this, aScrnMsg, i, scrnMsg);
                if (!ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                    continue;
                }
                //                if (ZYPCommonFunc.hasValue(aScrnMsg.prcTierSvcOfferCd_A)) {
                //                    aScrnMsg.prcTierSvcOfferCd_A.setInputProtected(true);
                //                    aScrnMsg.prcTierSvcOfferCd_AB.setInputProtected(true);
                //                } else {
                //                    aScrnMsg.prcTierSvcOfferCd_A.setInputProtected(false);
                //                    aScrnMsg.prcTierSvcOfferCd_AB.setInputProtected(false);
                //                }
            }
            NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);
            int index = scrnMsg.xxNum_A.getValueInt();
            if (ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(index).prcMtrPkgPk_A);
            } else {
                scrnMsg.setFocusItem(scrnMsg.A.no(index).xxTotPrcAmt_PB);
            }
            NSAL1330CommonLogic.overrideProtected(this, scrnMsg);

        } else if (POP_UP_SVC_PRC_SPC.equals(scrnMsg.xxPopPrm_P1.getValue())) { // Model Service Price List Config

            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(i);
                NSAL1330CommonLogic.setSupplyButton(this, rScrnMsg, i, scrnMsg);
                if (!ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                    continue;
                }
            }
            int index = scrnMsg.xxNum_A.getValueInt();
            if (ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                scrnMsg.setFocusItem(scrnMsg.R.no(index).prcMtrPkgPk_R);
            } else {
                scrnMsg.setFocusItem(scrnMsg.R.no(index).xxTotPrcAmt_PR);
            }
            NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
            NSAL1330CommonLogic.setUsgPrcAreaCtrlConfig(scrnMsg, this);

        } else if (POP_UP_SVC_PRC_RE_P.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(scrnMsg.xxNum_A.getValueInt()).addlBasePrcDealAmt_B)) {
                scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxNum_A.getValueInt()).prcCatgNm_B);
            } else {
                scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxNum_A.getValueInt()).addlBasePrcDealAmt_B);
            }

        } else if (POP_UP_SVC_PRC_RE.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            //            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_HB, scrnMsg.P.no(9).xxPopPrm_P);
            //            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_HB, scrnMsg.P.no(10).xxPopPrm_P);
            //            if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd_HB)) {
            //                return;
            //            }
            //            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            //                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).addlBasePrcCatgCd_B)) {
            //                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).addlBasePrcCatgCd_B, scrnMsg.prcCatgCd_HB);
            //                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).prcCatgNm_B, scrnMsg.prcCatgNm_HB);
            //                }
            //            }
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm_HB);

        } else if (POP_UP_SVC_PRC_AC_P.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            if (ZYPCommonFunc.hasValue(scrnMsg.J.no(scrnMsg.xxNum_A.getValueInt()).addlBasePrcDealAmt_J)) {
                scrnMsg.setFocusItem(scrnMsg.J.no(scrnMsg.xxNum_A.getValueInt()).prcCatgNm_J);
            } else {
                scrnMsg.setFocusItem(scrnMsg.J.no(scrnMsg.xxNum_A.getValueInt()).addlBasePrcDealAmt_J);
            }

        } else if (POP_UP_SVC_PRC_AC.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            // START 2017/08/04 [QC#20443, MOD]
            // ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_HJ, scrnMsg.P.no(9).xxPopPrm_P);
            // ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_HJ, scrnMsg.P.no(10).xxPopPrm_P);
            // if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd_HJ)) {
            //     return;
            // }
            // for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            //     if (!ZYPCommonFunc.hasValue(scrnMsg.J.no(i).addlBasePrcCatgCd_J)) {
            //         ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(i).addlBasePrcCatgCd_J, scrnMsg.prcCatgCd_HJ);
            //         ZYPEZDItemValueSetter.setValue(scrnMsg.J.no(i).prcCatgNm_J, scrnMsg.prcCatgNm_HJ);
            //     }
            // }
            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            // END   2017/08/04 [QC#20443, MOD]
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm_HJ);

        } else if (POP_UP_SVC_PRC_AD.equals(scrnMsg.xxPopPrm_P1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_C, scrnMsg.P.no(9).xxPopPrm_P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_C, scrnMsg.P.no(10).xxPopPrm_P);
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm_C);
        }
    }
}
