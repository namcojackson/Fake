/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BIZ_ID;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.NZZM0002I;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_INIT_PRC_PNT_SUM;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_TRX_CHRG;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.ZZM9000E;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7130.NMAL7130CMsg;
import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CONTR_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7130Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        boolean isError = false;

        scrnMsg.addCheckItem(scrnMsg.prcContrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrCustBidNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrShortDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrVrsnNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrRnwDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrTermMthNum_H1);

        // Mandatory Check.
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcContrNm_H1)) {
            scrnMsg.prcContrNm_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcContrNm_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcContrNm_H1);
            isError = true;
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcContrNum_H1)) {
            scrnMsg.prcContrNum_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcContrNum_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcContrNum_H1);
            isError = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.prcContrRnwDt_H1)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
                scrnMsg.effFromDt_H1.setErrorInfo(1, NMAM8178E, new String[] {scrnMsg.prcContrRnwDt_H1.getNameForMessage(), scrnMsg.effFromDt_H1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
                isError = true;
            } else if (ZYPDateUtil.compare(scrnMsg.effFromDt_H1.getValue(), scrnMsg.prcContrRnwDt_H1.getValue()) >= 0) {
                scrnMsg.prcContrRnwDt_H1.setErrorInfo(1, NMAM8342E, new String[] {scrnMsg.prcContrRnwDt_H1.getNameForMessage(), scrnMsg.effFromDt_H1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.prcContrRnwDt_H1);
                isError = true;
            }
        }
        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            NMAL7130CommonLogic.setCmnBtnProp(this, BTN_CMN_DWL, 0);
            NMAL7130CommonLogic.setRowsBGWithClear(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        scrnMsg.addCheckItem(scrnMsg.initFdRate_C1);
        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_INIT_PRC_PNT_SUM);
            NMAL7130CommonLogic.setCmnBtnProp(this, BTN_CMN_DWL, 0);
            NMAL7130CommonLogic.setRowsBGWithClear(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseCd_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).prcContrTrxChrgPct_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).prcContrTrxChrgAmt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).prcContrTrxChrgNm_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxRecNmTxt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsAcctNum_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effThruDt_D1);

            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(i).mdseCd_D1)) {
                scrnMsg.D.no(i).mdseCd_D1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.D.no(i).mdseCd_D1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseCd_D1);
                isError = true;
            }

            if (PRC_CONTR_CHRG_TP.REBATE.equals(scrnMsg.D.no(i).prcContrChrgTpCd_D1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(i).prcContrTrxChrgPct_D1)) {
                    scrnMsg.D.no(i).prcContrTrxChrgPct_D1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.D.no(i).prcContrTrxChrgPct_D1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.D.no(i).prcContrTrxChrgPct_D1);
                    isError = true;
                }
            } else if (PRC_CONTR_CHRG_TP.ADMIN_FEE.equals(scrnMsg.D.no(i).prcContrChrgTpCd_D1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(i).prcContrTrxChrgAmt_D1)) {
                    scrnMsg.D.no(i).prcContrTrxChrgAmt_D1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.D.no(i).prcContrTrxChrgAmt_D1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.D.no(i).prcContrTrxChrgAmt_D1);
                    isError = true;
                }
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(i).effFromDt_D1)) {
                scrnMsg.D.no(i).effFromDt_D1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.D.no(i).effFromDt_D1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_D1);
                isError = true;
            }
        }

        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_TRX_CHRG);
            NMAL7130CommonLogic.setRowsBGWithClear(scrnMsg);
            NMAL7130CommonLogic.setCmnBtnProp(this, BTN_CMN_DWL, 1);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        scrnMsg.addCheckItem(scrnMsg.ordTrxFlexPct_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerAlwncPct_E1);
        scrnMsg.addCheckItem(scrnMsg.nonStdStartTm_E1);
        scrnMsg.addCheckItem(scrnMsg.maxDownTmDaysAot_E1);
        scrnMsg.addCheckItem(scrnMsg.lflReplTermNum_E1);
        scrnMsg.addCheckItem(scrnMsg.tmAndMatUplftTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.docReqFrmAgmtNm_E1);
        scrnMsg.addCheckItem(scrnMsg.mstrAgmtDocNm_E1);
        scrnMsg.addCheckItem(scrnMsg.supplTermCmpyStdFrmTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.aftHourBillRate_E1);
        scrnMsg.addCheckItem(scrnMsg.rspTmComitTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.svcEtaCallReqHrsNum_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerTpNm_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerYieldCnt_E1);
        scrnMsg.addCheckItem(scrnMsg.maxRnwIncrAmtRate_E1);
        scrnMsg.addCheckItem(scrnMsg.maxStdAnnIncrPct_E1);
        scrnMsg.addCheckItem(scrnMsg.upTmGtdPct_E1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        NMAL7130CMsg bizMsg = new NMAL7130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        NMAL7130CMsg bizMsg = (NMAL7130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7130CommonLogic.setRowsBGWithClear(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.prcContrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_C1); // QC#11221 2016/07/01 Add
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseCd_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxRecNmTxt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dsAcctNum_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_D1);
        }
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

        NMAL7130CommonLogic.setTermCondProtect(this, scrnMsg);
        NMAL7130CommonLogic.setAttProtect(this, scrnMsg);
    }
}
