/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.BUSINESS_ID;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.MSG_CR_PRFL_INFO;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.NAMM0016E;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.TAB_FINANCIAL;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.TAB_INV_BLLG;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6730.NMAL6730CMsg;
import business.servlet.NMAL6730.common.NMAL6730CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/02/23   Fujitsu         T.Murai         Update          QC#4332
 * 2016/02/24   Fujitsu         H.Ikeda         Update          QC#1785
 * 2016/04/27   SRAA            Y.Chen          Update          QC#6918
 * 2016/05/02   SRAA            Y.Chen          Update          QC#4324
 * 2016/05/04   SRAA            Y.Chen          Update          QC#5575
 * 2016/06/08   SRAA            Y.Chen          Update          QC#7781
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2016/12/12   Fujitsu         N.Sugiura       Update          QC#16521
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/08/01   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2023/02/02   Hitachi         S.Nakatani      Update          QC#60811
 *</pre>
 */
public class NMAL6730Scrn00_CMN_Submit extends S21CommonHandler  {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        errCheck(scrnMsg);
 
        // QC#5575
        NMAL6730CommonLogic.setBgColor(scrnMsg); //Add QC#16521 2016/12/12
        // NMAL6730CommonLogic.clearMandatoryError(scrnMsg);
        NMAL6730CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg = (NMAL6730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6730CommonLogic.setBgColor(scrnMsg); //Add QC#16521 2016/12/12

     //   if (TAB_FINANCIAL.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);
            // QC#9448
            // scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);

            scrnMsg.addCheckItem(scrnMsg.ccyCd_P1);
            scrnMsg.addCheckItem(scrnMsg.custCrRtgCd_P1);
            scrnMsg.addCheckItem(scrnMsg.crLimitAmt_F1);
            scrnMsg.addCheckItem(scrnMsg.crChkReqTpCd_P1);
            scrnMsg.addCheckItem(scrnMsg.crRiskClsCd_P1);
            // START 2018/01/25 [QC#22095, ADD]
            scrnMsg.addCheckItem(scrnMsg.contrCrRiskClsCd_P1);
            // END   2018/01/25 [QC#22095, ADD]
            scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscCd_P1);
            scrnMsg.addCheckItem(scrnMsg.ovrdPmtTermFlg_F1);
            scrnMsg.addCheckItem(scrnMsg.cashOrCcReqFlg_F1);
            // QC#4324
            scrnMsg.addCheckItem(scrnMsg.custHardHldFlg_F1);
            scrnMsg.addCheckItem(scrnMsg.remId_F1);
            scrnMsg.addCheckItem(scrnMsg.arStmtFlg_F1);
            scrnMsg.addCheckItem(scrnMsg.arStmtIssCycleCd_P1);
            // START 2018/01/16 [QC#21734, DEL]
            // scrnMsg.addCheckItem(scrnMsg.dunFlg_F1);
            // END   2018/01/16 [QC#21734, DEL]
            scrnMsg.addCheckItem(scrnMsg.cltCustTpCd_F1);
            scrnMsg.addCheckItem(scrnMsg.cltPtfoCd_F1);
            scrnMsg.addCheckItem(scrnMsg.dsCltAcctStsCd_P1);
            scrnMsg.addCheckItem(scrnMsg.lateFeeFlg_F1);
            scrnMsg.addCheckItem(scrnMsg.lateFeeAmt_F1);
            // Start 2023/2/02 S.Nakatani [QC#60811, ADD]
            scrnMsg.addCheckItem(scrnMsg.mlyLateFeeRate_F1);
            // End 2023/2/02 S.Nakatani [QC#60811, ADD]
            // Del Start 2018/08/01 QC#27222
//            scrnMsg.addCheckItem(scrnMsg.dsCustTaxCd_F1);
//            scrnMsg.addCheckItem(scrnMsg.dsCustTaxCalcCd_P1);
            // Mod Start 2018/08/21 QC#27222-2 Uncomment
            scrnMsg.addCheckItem(scrnMsg.dsTaxExemFlg_F1);
            scrnMsg.addCheckItem(scrnMsg.dsExemExprDt_F1);
            // Mod End 2018/08/21 QC#27222-2
            // Del End 2018/08/01 QC#27222
            scrnMsg.addCheckItem(scrnMsg.dsTaxGrpExemCd_P1);
            scrnMsg.addCheckItem(scrnMsg.dsTaxPrntTpCd_P1);

   //     } else {
            scrnMsg.addCheckItem(scrnMsg.defBaseTpCd_P1);
            scrnMsg.addCheckItem(scrnMsg.defBaseCycleCd_P1);
            scrnMsg.addCheckItem(scrnMsg.defUsgTpCd_P1);
            scrnMsg.addCheckItem(scrnMsg.defUsgCycleCd_P1);
            scrnMsg.addCheckItem(scrnMsg.dsBillTgtrFlg_I1);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NMAL6730_ABMsg gbMsg = scrnMsg.A.no(i);
                scrnMsg.addCheckItem(gbMsg.custInvSrcCd_P1);
                scrnMsg.addCheckItem(gbMsg.custBllgTpCd_P1);
                scrnMsg.addCheckItem(gbMsg.custConslTermCd_P1);
                scrnMsg.addCheckItem(gbMsg.custBllgVcleCd_P1);
                // QC#7781
                scrnMsg.addCheckItem(gbMsg.xxGenlFldAreaTxt_A1);
                scrnMsg.addCheckItem(gbMsg.xxGenlFldAreaTxt_A2);
                scrnMsg.addCheckItem(gbMsg.custEmlMsgTxt_A1);
                scrnMsg.addCheckItem(gbMsg.defInvGrpCd_P1);
                scrnMsg.addCheckItem(gbMsg.invGrpNum_A1);
            }

    //    }
        scrnMsg.putErrorScreen();
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
    private void errCheck(NMAL6730BMsg scrnMsg) {

        // Del Start #4332 2016/02/23
        //if (TAB_FINANCIAL.equals(scrnMsg.xxDplyTab.getValue())) {
        // Del End #4332 2016/02/23

        // Add Start #4332 2016/02/23
        boolean financialErrFlg = false;
        // Add End #4332 2016/02/23
            // Del Start #QC1785 2016/02/24
            //if (ZYPCommonFunc.hasValue(scrnMsg.coaChCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd_H1)) {
            //    scrnMsg.coaAfflCd_H1.setErrorInfo(1, NMAM0049E, new String[] {scrnMsg.coaChCd_H1.getNameForMessage(), scrnMsg.coaAfflCd_H1.getNameForMessage()});
            //    financialErrFlg = true;
            //}
            //if (!ZYPCommonFunc.hasValue(scrnMsg.coaChCd_H1) && ZYPCommonFunc.hasValue(scrnMsg.coaAfflCd_H1)) {
            //    scrnMsg.coaChCd_H1.setErrorInfo(1, NMAM0049E, new String[] {scrnMsg.coaAfflCd_H1.getNameForMessage(), scrnMsg.coaChCd_H1.getNameForMessage()});
            //    financialErrFlg = true;
            //}
            // Del End #QC1785 2016/02/24
            if (ZYPCommonFunc.hasValue(scrnMsg.ccyCd_P1)
                    || ZYPCommonFunc.hasValue(scrnMsg.crLimitAmt_F1)
                    || ZYPCommonFunc.hasValue(scrnMsg.crChkReqTpCd_P1)
                    || ZYPCommonFunc.hasValue(scrnMsg.crRiskClsCd_P1)
                    // Add Start #22095 2018/01/25
                    || ZYPCommonFunc.hasValue(scrnMsg.contrCrRiskClsCd_P1)
                    // Add End   #22095 2018/01/25
                    || ZYPCommonFunc.hasValue(scrnMsg.pmtTermCashDiscCd_P1)) {

                if (!ZYPCommonFunc.hasValue(scrnMsg.ccyCd_P1)) {
                    scrnMsg.ccyCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.ccyCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
                //QC#17252 del Start
//                if (!ZYPCommonFunc.hasValue(scrnMsg.crLimitAmt_F1)) {
//                    scrnMsg.crLimitAmt_F1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.crLimitAmt_F1.getNameForMessage(), MSG_CR_PRFL_INFO});
//                    financialErrFlg = true;
//
//                }
                //QC#17252 del End
                if (!ZYPCommonFunc.hasValue(scrnMsg.crChkReqTpCd_P1)) {
                    scrnMsg.crChkReqTpCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.crChkReqTpCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.crRiskClsCd_P1)) {
                    scrnMsg.crRiskClsCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.crRiskClsCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
                // Add Start #22095 2018/01/25
                if (!ZYPCommonFunc.hasValue(scrnMsg.contrCrRiskClsCd_P1)) {
                    scrnMsg.contrCrRiskClsCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.contrCrRiskClsCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
                // Add END   #22095 2018/01/25
                if (!ZYPCommonFunc.hasValue(scrnMsg.pmtTermCashDiscCd_P1)) {
                    scrnMsg.pmtTermCashDiscCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.pmtTermCashDiscCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.ovrdPmtTermFlg_F1) || ZYPCommonFunc.hasValue(scrnMsg.cashOrCcReqFlg_F1)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.ccyCd_P1)) {
                    scrnMsg.ccyCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.ccyCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
                //QC#17252 del Start
//                if (!ZYPCommonFunc.hasValue(scrnMsg.crLimitAmt_F1)) {
//                    scrnMsg.crLimitAmt_F1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.crLimitAmt_F1.getNameForMessage(), MSG_CR_PRFL_INFO});
//                    financialErrFlg = true;
//
//                }
                //QC#17252 del End
                if (!ZYPCommonFunc.hasValue(scrnMsg.crChkReqTpCd_P1)) {
                    scrnMsg.crChkReqTpCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.crChkReqTpCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.crRiskClsCd_P1)) {
                    scrnMsg.crRiskClsCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.crRiskClsCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
                // Add Start #22095 2018/01/25
                if (!ZYPCommonFunc.hasValue(scrnMsg.contrCrRiskClsCd_P1)) {
                    scrnMsg.contrCrRiskClsCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.contrCrRiskClsCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;
                }
                // Add End   #22095 2018/01/25
                if (!ZYPCommonFunc.hasValue(scrnMsg.pmtTermCashDiscCd_P1)) {
                    scrnMsg.pmtTermCashDiscCd_P1.setErrorInfo(1, NAMM0016E, new String[] {scrnMsg.pmtTermCashDiscCd_P1.getNameForMessage(), MSG_CR_PRFL_INFO});
                    financialErrFlg = true;

                }
            }
            
            // QC#6918
            if (!ZYPCommonFunc.hasValue(scrnMsg.remId_F1)) {
                scrnMsg.remId_F1.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.remId_F1.getNameForMessage()});
                financialErrFlg = true;
            }
            // Add Start #4332 2016/02/23
            if (financialErrFlg) {
                scrnMsg.xxDplyTab.setValue(TAB_FINANCIAL);
                scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);
                // QC#9448
                // scrnMsg.addCheckItem(scrnMsg.coaAfflCd_H1);
                scrnMsg.addCheckItem(scrnMsg.ccyCd_P1);
                scrnMsg.addCheckItem(scrnMsg.crLimitAmt_F1);
                scrnMsg.addCheckItem(scrnMsg.crChkReqTpCd_P1);
                scrnMsg.addCheckItem(scrnMsg.crRiskClsCd_P1);
                // Add Start #22095 2018/01/25
                scrnMsg.addCheckItem(scrnMsg.contrCrRiskClsCd_P1);
                // Add End   #22095 2018/01/25
                scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscCd_P1);
                scrnMsg.addCheckItem(scrnMsg.remId_F1);
                return;
            }
            // Add End #4332 2016/02/23

        // Mod Start #4332 2016/02/23
        // } else {
        // for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        // scrnMsg.addCheckItem(scrnMsg.A.no(i).custInvSrcCd_P1);
        // scrnMsg.addCheckItem(scrnMsg.A.no(i).custBllgTpCd_P1);
        // scrnMsg.addCheckItem(scrnMsg.A.no(i).custBllgVcleCd_P1);
        // }
        // }
        boolean invVillgErrFlg = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).custInvSrcCd_P1)) {
                scrnMsg.A.no(i).custInvSrcCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.A.no(i).custInvSrcCd_P1.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).custInvSrcCd_P1);
                invVillgErrFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).custBllgTpCd_P1)) {
                scrnMsg.A.no(i).custBllgTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.A.no(i).custBllgTpCd_P1.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).custBllgTpCd_P1);
                invVillgErrFlg = true;

            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).custBllgVcleCd_P1)) {
                scrnMsg.A.no(i).custBllgVcleCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.A.no(i).custBllgVcleCd_P1.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).custBllgVcleCd_P1);
                invVillgErrFlg = true;
            }
        }
        for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
            NMAL6730_KBMsg kbMsg = scrnMsg.K.no(i);
            if (!ZYPCommonFunc.hasValue(kbMsg.bllgAttrbNm_K1)) {
                kbMsg.bllgAttrbNm_K1.setErrorInfo(1, "ZZM9000E", new String[] {kbMsg.bllgAttrbNm_K1.getNameForMessage() });
                scrnMsg.addCheckItem(kbMsg.bllgAttrbNm_K1);
                invVillgErrFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(kbMsg.custEffLvlCd_K3)) {
                kbMsg.custEffLvlCd_K3.setErrorInfo(1, "ZZM9000E", new String[] {kbMsg.custEffLvlCd_K3.getNameForMessage() });
                scrnMsg.addCheckItem(kbMsg.custEffLvlCd_K3);
                invVillgErrFlg = true;
            }
        }
        if (invVillgErrFlg) {
            scrnMsg.xxDplyTab.setValue(TAB_INV_BLLG);
            return;
        }
        // NMAL6730CommonLogic.setBgColor(scrnMsg); //Add QC#16521 2016/12/12
        // Mod End #4332 2016/02/23
        // scrnMsg.putErrorScreen();

    }
}
