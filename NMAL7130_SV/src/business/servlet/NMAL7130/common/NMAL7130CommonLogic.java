/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130.common;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_ATT;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_APL;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_APR;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_RST;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CRAT_NEW_VRSN;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.SCRN_ID_00;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_INIT_PRC_PNT_SUM;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_REGD_ACCT;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_RELN_PRC_LIST;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_TERM_COND;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_TRX_CHRG;
import parts.common.EZDBMsgArray;
import business.servlet.NMAL7130.NMAL7130BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CONTR_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TERM_COND_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7130CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2015/11/30   Fujitsu         W.Honda         Create          QC#3633
 *</pre>
 */
public class NMAL7130CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param submitFlg boolean
     */
    public static void initCmnBtnProp(S21CommonHandler handler, boolean submitFlg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        if (submitFlg) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * initScrnProtect.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7130BMsg
     */
    public static void initScrnProtect(S21CommonHandler handler, NMAL7130BMsg scrnMsg) {
        scrnMsg.dsAcctNm_C1.setInputProtected(true); // QC#11221 2016/07/01 Add
        scrnMsg.equipRevSumAmt_C1.setInputProtected(true);
        scrnMsg.mainUnitSumCnt_C1.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcListTpDescTxt_A1.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).dsAcctNm_D1.setInputProtected(true);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.prcTermCondVrsnNum_E1)) {
            handler.setButtonEnabled(BTN_CRAT_NEW_VRSN, true);
        } else {
            handler.setButtonEnabled(BTN_CRAT_NEW_VRSN, false);
        }

        scrnMsg.xxFullNm_H1.setInputProtected(true);
        scrnMsg.cratDt_H1.setInputProtected(true);
        scrnMsg.xxFullNm_H2.setInputProtected(true);
        scrnMsg.lastUpdDt_H1.setInputProtected(true);
    }

    /**
     * setAttProtect.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7130BMsg
     */
    public static void setAttProtect(S21CommonHandler handler, NMAL7130BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.prcContrPk_BK)) {
            handler.setButtonEnabled(BTN_ATT, true);
        } else {
            handler.setButtonEnabled(BTN_ATT, false);
        }

    }

    /**
     * setTermCondProtect.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7130BMsg
     */
    public static void setTermCondProtect(S21CommonHandler handler, NMAL7130BMsg scrnMsg) {
        boolean btnEanabled = true;
        boolean inputProtected = false;

        if (ZYPCommonFunc.hasValue(scrnMsg.prcTermCondVrsnNum_E1)) {
            if (scrnMsg.prcTermCondVrsnNum_E1.getValue().equals(scrnMsg.prcTermCondVrsnNum_L1.no(0).getValue())) {
                btnEanabled = true;
                inputProtected = false;
            } else {
                btnEanabled = false;
                inputProtected = true;
            }

            if (PRC_TERM_COND_STS.FINAL_APPROVED_BY_DOC_REVIEW.equals(scrnMsg.prcTermCondStsCd_E1.getValue())) {
            // Del Start 2016/02/09 CSA-QC#3633
//                btnEanabled = false;
            // Del End 2016/02/09 CSA-QC#3633
                inputProtected = true;
            }
        } else {
            btnEanabled = false;
            inputProtected = false;
        }

        handler.setButtonEnabled(BTN_CRAT_NEW_VRSN, btnEanabled);

        scrnMsg.prcTermCondStsCd_E1.setInputProtected(inputProtected);
        scrnMsg.ordTrxFlexPct_E1.setInputProtected(inputProtected);
        scrnMsg.allwDclnMaintFlg_E1.setInputProtected(inputProtected);
        scrnMsg.mustUseEquipPrcFlg_E1.setInputProtected(inputProtected);
        scrnMsg.leaseRtrnInclInPrcFlg_E1.setInputProtected(inputProtected);
        scrnMsg.ovrdSysTonerTpFlg_E1.setInputProtected(inputProtected);
        scrnMsg.billTonerFrtChrgFlg_E1.setInputProtected(inputProtected);
        scrnMsg.tonerAlwncPct_E1.setInputProtected(inputProtected);
        scrnMsg.nonStdStartTm_E1.setInputProtected(inputProtected);
        scrnMsg.lnrEttlFlg_E1.setInputProtected(inputProtected);
        scrnMsg.maxDownTmDaysAot_E1.setInputProtected(inputProtected);
        scrnMsg.lflReplOptFlg_E1.setInputProtected(inputProtected);
        scrnMsg.lflReplTermNum_E1.setInputProtected(inputProtected);
        scrnMsg.unltdTngReqFlg_E1.setInputProtected(inputProtected);
        scrnMsg.custPrvtyFlg_E1.setInputProtected(inputProtected);
        scrnMsg.hddSvcInclFlg_E1.setInputProtected(inputProtected);
        scrnMsg.hddCleanPrcGtdFlg_E1.setInputProtected(inputProtected);
        scrnMsg.tmAndMatUplftTxt_E1.setInputProtected(inputProtected);
        scrnMsg.docReqFrmAgmtNm_E1.setInputProtected(inputProtected);
        scrnMsg.mstrAgmtDocNm_E1.setInputProtected(inputProtected);
        scrnMsg.mstrReplAquFlg_E1.setInputProtected(inputProtected);
        scrnMsg.mstrReplCmbnPrchFlg_E1.setInputProtected(inputProtected);
        scrnMsg.mstrReplLeaseFlg_E1.setInputProtected(inputProtected);
        scrnMsg.leaseTrxAllwFlg_E1.setInputProtected(inputProtected);
        scrnMsg.supplTermCmpyStdFrmTxt_E1.setInputProtected(inputProtected);
        scrnMsg.aftHourBillRate_E1.setInputProtected(inputProtected);
        scrnMsg.rspTmMeasPerCd_E1.setInputProtected(inputProtected);
        scrnMsg.rspTmComitTxt_E1.setInputProtected(inputProtected);
        scrnMsg.svcEtaCallReqHrsNum_E1.setInputProtected(inputProtected);
        scrnMsg.tonerTpNm_E1.setInputProtected(inputProtected);
        scrnMsg.tonerYieldCnt_E1.setInputProtected(inputProtected);
        scrnMsg.stplInclSvcFlg_E1.setInputProtected(inputProtected);
        scrnMsg.prcContrPrcTpCd_E1.setInputProtected(inputProtected);
        scrnMsg.dlyFirstCallGtdFlg_E1.setInputProtected(inputProtected);
        scrnMsg.onSiteTechInclFlg_E1.setInputProtected(inputProtected);
        scrnMsg.primTechInclFlg_E1.setInputProtected(inputProtected);
        scrnMsg.iwrEsclFlg_E1.setInputProtected(inputProtected);
        scrnMsg.maxRnwIncrAmtRate_E1.setInputProtected(inputProtected);
        scrnMsg.maxStdAnnIncrPct_E1.setInputProtected(inputProtected);
        scrnMsg.erlTrmnOptFlg_E1.setInputProtected(inputProtected);
        scrnMsg.upTmGtdPct_E1.setInputProtected(inputProtected);
        scrnMsg.fleetContrAllwFlg_E1.setInputProtected(inputProtected);
        scrnMsg.aggrContrAllwFlg_E1.setInputProtected(inputProtected);
        scrnMsg.custQtlyBizRvwReqFlg_E1.setInputProtected(inputProtected);
        scrnMsg.stdQtlyBizRvwReqFlg_E1.setInputProtected(inputProtected);
        scrnMsg.reqRptIntvlCd_E1.setInputProtected(inputProtected);
    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7130BMsg
     */
    public static void setRowsBGWithClear(NMAL7130BMsg scrnMsg) {

        if (TAB_REGD_ACCT.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
            clearRowsBG(scrnMsg, scrnMsg.B, "B");
            clearRowsBG(scrnMsg, scrnMsg.D, "D1");
            clearRowsBG(scrnMsg, scrnMsg.D, "D2");
        } else if (TAB_RELN_PRC_LIST.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.A, "A");
            setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
            clearRowsBG(scrnMsg, scrnMsg.D, "D1");
            clearRowsBG(scrnMsg, scrnMsg.D, "D2");
        } else if (TAB_INIT_PRC_PNT_SUM.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.A, "A");
            clearRowsBG(scrnMsg, scrnMsg.B, "B");
            clearRowsBG(scrnMsg, scrnMsg.D, "D1");
            clearRowsBG(scrnMsg, scrnMsg.D, "D2");
        } else if (TAB_TRX_CHRG.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.A, "A");
            clearRowsBG(scrnMsg, scrnMsg.B, "B");
            setRowsBGWithClear(scrnMsg, scrnMsg.D, "D1");
            setRowsBGWithClear(scrnMsg, scrnMsg.D, "D2");
        } else if (TAB_TERM_COND.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            clearRowsBG(scrnMsg, scrnMsg.A, "A");
            clearRowsBG(scrnMsg, scrnMsg.B, "B");
            clearRowsBG(scrnMsg, scrnMsg.D, "D1");
            clearRowsBG(scrnMsg, scrnMsg.D, "D2");
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7130BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7130BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7130BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7130BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7130BMsg
     * @param scrnAMsgAry EZDBMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7130BMsg scrnMsg, EZDBMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Set Protect Rebate or Admin.
     * @param scrnMsg NMAL7130BMsg
     * @param idx int
     */
    public static void setProtectRebateAdmin(NMAL7130BMsg scrnMsg, int idx) {
        if (PRC_CONTR_CHRG_TP.REBATE.equals(scrnMsg.D.no(idx).prcContrChrgTpCd_D1.getValue())) {
            scrnMsg.D.no(idx).prcContrTrxChrgAmt_D1.clear();
            scrnMsg.D.no(idx).prcContrTrxChrgPct_D1.setInputProtected(false);
            scrnMsg.D.no(idx).prcContrTrxChrgAmt_D1.setInputProtected(true);
        } else if (PRC_CONTR_CHRG_TP.ADMIN_FEE.equals(scrnMsg.D.no(idx).prcContrChrgTpCd_D1.getValue())) {
            scrnMsg.D.no(idx).prcContrTrxChrgPct_D1.clear();
            scrnMsg.D.no(idx).prcContrTrxChrgPct_D1.setInputProtected(true);
            scrnMsg.D.no(idx).prcContrTrxChrgAmt_D1.setInputProtected(false);
        } else {
            scrnMsg.D.no(idx).prcContrTrxChrgPct_D1.setInputProtected(false);
            scrnMsg.D.no(idx).prcContrTrxChrgAmt_D1.setInputProtected(false);
        }
        scrnMsg.D.no(idx).xxFullNm_D1.setInputProtected(true);
        scrnMsg.D.no(idx).cratDt_D1.setInputProtected(true);
        scrnMsg.D.no(idx).xxFullNm_D2.setInputProtected(true);
        scrnMsg.D.no(idx).lastUpdDt_D1.setInputProtected(true);
    }

    // START 2016/02/22 W.Honda [S21 CSA QC#1130 ADD]
    /**
     * Sscreen item add check.
     * @param scrnMsg NMAL7130BMsg
     */
    public static void addCheckScreenItem(NMAL7130BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrVrsnNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrRnwDt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrTermMthNum_H1);

        // Init Prc Pnt Sum
        scrnMsg.addCheckItem(scrnMsg.initFdRate_C1);

        // Trx Chrg
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).prcContrChrgTpCd_D1)) {
                if (PRC_CONTR_CHRG_TP.REBATE.equals(scrnMsg.D.no(i).prcContrChrgTpCd_D1.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.D.no(i).prcContrTrxChrgPct_D1);
                } else if (PRC_CONTR_CHRG_TP.ADMIN_FEE.equals(scrnMsg.D.no(i).prcContrChrgTpCd_D1.getValue())) {
                    scrnMsg.addCheckItem(scrnMsg.D.no(i).prcContrTrxChrgAmt_D1);
                }
            }
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effThruDt_D1);
        }

        // Term Cond
        scrnMsg.addCheckItem(scrnMsg.ordTrxFlexPct_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerAlwncPct_E1);
        scrnMsg.addCheckItem(scrnMsg.nonStdStartTm_E1);
        scrnMsg.addCheckItem(scrnMsg.maxDownTmDaysAot_E1);
        scrnMsg.addCheckItem(scrnMsg.lflReplTermNum_E1);
        scrnMsg.addCheckItem(scrnMsg.aftHourBillRate_E1);
        scrnMsg.addCheckItem(scrnMsg.svcEtaCallReqHrsNum_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerYieldCnt_E1);
        scrnMsg.addCheckItem(scrnMsg.maxRnwIncrAmtRate_E1);
        scrnMsg.addCheckItem(scrnMsg.maxStdAnnIncrPct_E1);
        scrnMsg.addCheckItem(scrnMsg.upTmGtdPct_E1);
    }
    // END 2016/02/22 W.Honda [S21 CSA QC#1130 ADD]

}
