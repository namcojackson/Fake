/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/09   Hitachi         T.Kanasaka      Update          N/A
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3847
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/02/25   Hitachi         T.Kanasaka      Update          QC4088
 * 2016/04/07   Hitachi         M.Gotou         Update          QC5312,5313
 * 2016/04/15   Hitachi         T.Tomita        Update          QC#4085
 * 2016/05/16   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2017/01/27   Hitachi         Y.Takeno        Update          QC#17278
 * 2017/12/21   Hitachi         T.Tomita        Update          QC#18779
 * 2018/05/15   Hitachi         K.Kitachi       Update          QC#24265
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/04   Hitachi         K.Kitachi       Update          QC#26891
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26946
 * 2019/01/09   Hitachi         K.Kitachi       Update          QC#26928
 * 2019/01/17   CITS            M.Naito         Update          QC#29083
 * 2019/01/17   CITS            T.Wada          Update          QC#29371
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0300_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0300Constant.BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        BigDecimal dsContrPk = null;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    dsContrPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
                // START 2018/07/04 K.Kitachi [QC#26891, ADD]
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof String) {
                    dsContrPk = new BigDecimal((String) prm[0]);
                }
                // END 2018/07/04 K.Kitachi [QC#26891, ADD]
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk, dsContrPk);

        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.dsContrNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        scrnMsg.xxPageShowCurNum_A.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        scrnMsg.dsContrNum.setNameForMessage("Contract #");
        scrnMsg.dsContrCatgCd.setNameForMessage("Contract Type");
        scrnMsg.dsContrClsCd.setNameForMessage("Contract Category");
        scrnMsg.contrDurnAot.setNameForMessage("Duration");
        scrnMsg.xxDplyByCdNmCnctTxt.setNameForMessage("Branch");
        scrnMsg.xxPsnNm.setNameForMessage("Branch Rep");
        scrnMsg.tocNm.setNameForMessage("Sales Rep Name");
        scrnMsg.contrVrsnEffFromDt.setNameForMessage("Start Date");
        scrnMsg.contrVrsnEffThruDt.setNameForMessage("End Date");
        scrnMsg.bllgCycleUomCd.setNameForMessage("Period");
        scrnMsg.svcContrRefCmntTxt.setNameForMessage("Source Ref#");
        scrnMsg.dsContrEdiCd.setNameForMessage("EDI");
        // START 2017/01/27 [QC#17278, MOD]
        //scrnMsg.dsContrRptGrpNum.setNameForMessage("Report Grp#");
        scrnMsg.dsContrRptGrpDescTxt.setNameForMessage("Report Group");
        // END   2017/01/27 [QC#17278, MOD]
        scrnMsg.dsContrNm.setNameForMessage("Description");
        scrnMsg.contrInvCmntTxt.setNameForMessage("Inv. Comments");

        scrnMsg.dsAcctNum.setNameForMessage("Customer #");
        scrnMsg.altPayerCustCd.setNameForMessage("Bill To Location");
        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        scrnMsg.xxPsnNm_CP.setNameForMessage("Bill To Contact");
        scrnMsg.ctacPsnFirstNm_CP.setNameForMessage("Contact First Name");
        scrnMsg.ctacPsnLastNm_CP.setNameForMessage("Contact Last Name");
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
        scrnMsg.leaseCmpyCd.setNameForMessage("Lease");
        // START 2018/05/15 K.Kitachi [QC#24265, ADD]
        scrnMsg.cfsLeaseNumCmntTxt.setNameForMessage("Lease #");
        // END 2018/05/15 K.Kitachi [QC#24265, ADD]
        scrnMsg.custPoNum.setNameForMessage("PO Number");
        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        scrnMsg.poDt.setNameForMessage("PO Expiration Date");
        scrnMsg.poFromDt.setNameForMessage("PO Date (From)");
        scrnMsg.poDt.setNameForMessage("PO Date (Thru)");
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]
        scrnMsg.crCardCustRefNum.setNameForMessage("Credit Card #");
        scrnMsg.pmtTermCashDiscCd.setNameForMessage("Payment Term");

        scrnMsg.mdseDescShortTxt_SP.setNameForMessage("Service Program");
        scrnMsg.baseBllgCycleCd.setNameForMessage("Base Frequency");
        scrnMsg.mtrBllgCycleCd.setNameForMessage("Overage Frequency");
        scrnMsg.mtrEstTpCd.setNameForMessage("Auto Estimation");
        // START 2022/03/22 [QC#59683, ADD]
        scrnMsg.dsInvTgtrTpCd.setNameForMessage("Invoicing Option");
        // END   2022/03/22 [QC#59683, ADD]

        scrnMsg.contrAutoRnwTpCd.setNameForMessage("Renewal Type");
        scrnMsg.rnwPrcMethCd.setNameForMessage("Renewal Method");
        scrnMsg.basePrcUpRatio.setNameForMessage("Renewal Pricing Up Rate(%) Base");
        scrnMsg.mtrPrcUpRatio.setNameForMessage("Renewal Pricing Up Rate(%) Overage");
        scrnMsg.befEndRnwDaysAot.setNameForMessage("# of Days Before");

        scrnMsg.contrUplftTpCd.setNameForMessage("Upliftment Type");
        scrnMsg.uplftPrcMethCd.setNameForMessage("Upliftment Method");
        scrnMsg.uplftBasePrcUpRatio.setNameForMessage("Upliftment Pricing Up Rate(%) Base");
        scrnMsg.uplftMtrPrcUpRatio.setNameForMessage("Upliftment Pricing Up Rate(%) Overage");

        scrnMsg.serNum.setNameForMessage("Serial#");
        scrnMsg.xxCondCd_1V.setNameForMessage("Filter By (Item)");
        scrnMsg.xxCondCd_2V.setNameForMessage("Filter By (Condition)");
        scrnMsg.condSqlTxt.setNameForMessage("Filter By (Value)");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).contrEffFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrEffThruDt_A.setNameForMessage("End Date");
            scrnMsg.A.no(i).basePrcDealAmt_A.setNameForMessage("Base Charge");
            scrnMsg.A.no(i).mtrReadMethCd_A.setNameForMessage("Read Method");
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).contrEffFromDt_B.setNameForMessage("Start Date");
            scrnMsg.B.no(i).contrEffThruDt_B.setNameForMessage("End Date");

            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            scrnMsg.B.no(i).shipToCustCd_B.setNameForMessage("Ship To Location");
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

            scrnMsg.B.no(i).baseBillToCustCd_B.setNameForMessage("Bill To");
            // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//            scrnMsg.B.no(i).xxPsnNm_BB.setNameForMessage("Contact");
            scrnMsg.B.no(i).ctacPsnFirstNm_BB.setNameForMessage("Contact First Name");
            scrnMsg.B.no(i).ctacPsnLastNm_BB.setNameForMessage("Contact Last Name");
            // END 2018/06/18 K.Kitachi [QC#18591, MOD]
            // Add Start 2018/08/20 QC#26946
            scrnMsg.B.no(i).svcPgmMdseCd_B.setNameForMessage("Service Program");
            // Add End 2018/08/20 QC#26946
            scrnMsg.B.no(i).mdseDescShortTxt_B.setNameForMessage("Service Program");
            scrnMsg.B.no(i).basePrcDealAmt_B.setNameForMessage("Base Charge");
            scrnMsg.B.no(i).basePrcTermDealAmtRate_B.setNameForMessage("Term Amount");
            scrnMsg.B.no(i).baseBllgCycleCd_B.setNameForMessage("Frequency");
            // Add Start 2017/12/21 QC#18779
            scrnMsg.B.no(i).baseBllgTmgCd_B.setNameForMessage("Billing Timing");
            // Add End 2017/12/21 QC#18779
            scrnMsg.B.no(i).baseDplyPerEndDay_B.setNameForMessage("Period End Date");
            scrnMsg.B.no(i).contrBllgDay_B.setNameForMessage("Invoice Date");

            scrnMsg.B.no(i).mtrDplyPerEndDay_B.setNameForMessage("Period End Date");
            scrnMsg.B.no(i).mtrBllgDay_B.setNameForMessage("Invoice Date");

            scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setNameForMessage("Frequency");
            scrnMsg.B.no(i).xsChrgTpCd_B.setNameForMessage("Type");
            scrnMsg.B.no(i).xsMtrCopyQty_B.setNameForMessage("Allowance");
            scrnMsg.B.no(i).xsMtrAmtRate_B.setNameForMessage("Price");
            scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setNameForMessage("Bill To");
            // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//            scrnMsg.B.no(i).xxPsnNm_BM.setNameForMessage("Contact");
            scrnMsg.B.no(i).ctacPsnFirstNm_BM.setNameForMessage("Contact First Name");
            scrnMsg.B.no(i).ctacPsnLastNm_BM.setNameForMessage("Contact Last Name");
            // END 2018/06/18 K.Kitachi [QC#18591, MOD]
            // START 2019/01/17 M.Naito [QC#29083,ADD]
            scrnMsg.B.no(i).cumCopyCnt_B.setNameForMessage("Cumulative Copy Allowance");
            scrnMsg.B.no(i).cumCopyFreqMthAot_B.setNameForMessage("Cumulative Copy Frequency");
            scrnMsg.B.no(i).cumCopyStartDt_B.setNameForMessage("Cumulative Copy Start Date");
            scrnMsg.B.no(i).cumCopyEndDt_B.setNameForMessage("Cumulative Copy End Date");
            // END 2019/01/17 M.Naito [QC#29083,ADD]

            scrnMsg.B.no(i).bllgFreeCopyCnt_B.setNameForMessage("Free Copies");
            scrnMsg.B.no(i).bllgMinCnt_B.setNameForMessage("Min. Vol");
            scrnMsg.B.no(i).bllgMinAmtRate_B.setNameForMessage("Min. Amt");
            scrnMsg.B.no(i).bllgRollOverRatio_B.setNameForMessage("Roll Over %");

            // Start 2019/01/21 T.Wada [QC#29371]
            scrnMsg.B.no(i).xxTpCd_B.setNameForMessage("Print Option");
            // End 2019/01/21 T.Wada [QC#29371]

        }
    }
}
