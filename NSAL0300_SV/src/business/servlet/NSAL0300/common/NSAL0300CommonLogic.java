/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300.common;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.CCYTMsg;
import business.servlet.NSAL0300.NSAL0300BMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/11/06   Hitachi         T.Kanasaka      Update          QC464
 * 2015/11/09   Hitachi         T.Kanasaka      Update          QC493
 * 2015/11/13   Hitachi         T.Kanasaka      Update          QC663
 * 2015/11/25   Hitachi         A.Kohinata      Update          QC977
 * 2015/12/04   Hitachi         A.Kohinata      Update          QC1414,1534
 * 2015/12/10   Hitachi         K.Yamada        Update          CSA QC#1765
 * 2015/12/14   Hitachi         T.Kanasaka      Update          QC1950
 * 2016/01/07   Hitachi         T.Tomita        Update          QC#1029
 * 2016/01/15   Hitachi         T.Tomita        Update          QC#3016
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2016/02/09   Hitachi         T.Kanasaka      Update          QC3273
 * 2016/02/09   Hitachi         T.Aoyagi        Update          QC4081
 * 2016/02/10   Hitachi         T.Tomita        Update          QC3887, 3981
 * 2016/02/10   Hitachi         T.Kanasaka      Update          QC3058
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3847
 * 2016/02/17   Hitachi         T.Aoyagi        Update          QC2954
 * 2016/02/17   Hitachi         T.Kanasaka      Update          QC2879
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3700
 * 2016/02/18   Hitachi         T.Kanasaka      Update          QC3023,QC3270
 * 2016/02/19   Hitachi         T.Tomita        Update          QC#3985
 * 2016/02/22   Hitachi         T.Tomita        Update          QC#4094
 * 2016/02/23   Hitachi         T.Kanasaka      Update          QC3885,QC3188
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/02/24   Hitachi         A.Kohinata      Update          QC3697
 * 2016/02/26   Hitachi         T.Kanasaka      Update          QC4092
 * 2016/03/08   Hitachi         T.Kanasaka      Update          QC5139,QC5140
 * 2016/04/07   Hitachi         M.Gotou         Update          QC5312,5313
 * 2016/04/13   Hitachi         T.Kanasaka      Update          QC4957
 * 2016/04/20   Hitachi         K.Kishimoto     Update          QC5130
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#4668
 * 2016/05/10   Hitachi         T.Kanasaka      Update          QC#6798
 * 2016/05/16   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/06/21   Hitachi         M.Gotou         Update          QC#6923
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/12   Hitachi         M.Gotou         Update          QC#11525
 * 2016/07/28   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/09/05   Hitachi         T.Kanasaka      Update          QC#14208
 * 2016/09/27   Hitachi         Y.Zhang         Update          QC#12582
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/26   Hitachi         T.Mizuki        Update          QC#3581
 * 2016/11/22   Hitachi         A.Kohinata      Update          QC#16114
 * 2016/12/20   Hitachi         K.Kishimoto     Update          QC#16647
 * 2017/01/12   Hitachi         T.Mizuki        Update          QC#16792
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 * 2017/01/27   Hitachi         Y.Takeno        Update          QC#17278
 * 2017/02/15   Hitachi         K.Kitachi       Update          QC#17304
 * 2017/02/23   Hitachi         A.Kohinata      Update          QC#15112
 * 2017/07/05   Hitachi         K.Kim           Update          QC#18110
 * 2017/07/26   Hitachi         M.Kidokoro      Update          QC#18122
 * 2017/07/27   Hitachi         K.Kim           Update          QC#16889
 * 2017/07/31   Hitachi         M.Kidokoro      Update          QC#20116
 * 2017/08/31   Hitachi         K.Kojima        Update          QC#20858
 * 2017/09/13   Hitachi         K.Kim           Update          QC#19938
 * 2017/09/21   Hitachi         K.Kitachi       Update          QC#21222
 * 2017/09/26   Hitachi         K.Kitachi       Update          QC#21222
 * 2017/11/06   Hitachi         T.Tomita        Update          QC#18552
 * 2017/12/21   Hitachi         T.Tomita        Update          QC#18779
 * 2018/01/30   CITS            M.Naito         Update          QC#21349
 * 2018/02/19   Hitachi         M.Kidokoro      Update          QC#23629
 * 2018/04/26   Hitachi         K.Kojima        Update          QC#23630
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/05/15   Hitachi         K.Kitachi       Update          QC#24265
 * 2018/05/30   CITS            M.Naito         Update          QC#22887
 * 2018/06/05   Hitachi         K.Kojima        Update          QC#21974
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/23   Hitachi         K.Kim           Update          QC#26831
 * 2018/07/24   Hitachi         K.Kim           Update          QC#26768
 * 2018/07/27   Hitachi         K.Kishimoto     Update          QC#24795
 * 2018/07/30   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2018/08/23   Hitachi         A.Kohinata      Update          QC#27790
 * 2018/09/27   Hitachi         K.Kim           Update          QC#27777
 * 2018/10/24   Hitachi         K.Kitachi       Update          QC#28889
 * 2018/10/24   Hitachi         K.Fujimoto      Update          QC#28627
 * 2019/01/09   Hitachi         K.Kitachi       Update          QC#26928
 * 2019/01/17   CITS            M.Naito         Update          QC#29083
 * 2019/01/21   CITS            T.Wada          Update          QC#29371
 * 2019/02/15   Hitachi         T.Tomita        Update          QC#30295
 * 2019/11/27   Hitachi         E.Kameishi      Update          QC#54594
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2023/03/03   Hitachi         R.Takau         Update          QC#55645
 * 2023/08/18   CITS            T.Kojima        Update          QC#60846
 * 2024/03/22   Hitachi         Y.Tamai         Update          QC#63463
 *</pre>
 */
public class NSAL0300CommonLogic {

    /**
     * addCheckItemForEndCustomer
     * @param scrnMsg NSAL0300BMsg
     */
    public static void addCheckItem(NSAL0300BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.dsContrCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsContrClsCd);
        scrnMsg.addCheckItem(scrnMsg.contrDurnAot);
        scrnMsg.addCheckItem(scrnMsg.xxDplyByCdNmCnctTxt);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm);
        scrnMsg.addCheckItem(scrnMsg.tocNm);
        scrnMsg.addCheckItem(scrnMsg.contrVrsnEffFromDt);
        scrnMsg.addCheckItem(scrnMsg.contrVrsnEffThruDt);
        scrnMsg.addCheckItem(scrnMsg.bllgCycleUomCd);
        scrnMsg.addCheckItem(scrnMsg.dsContrEdiCd);
        // add start 2018/11/07 K.Fujimoto QC#28627
        scrnMsg.addCheckItem(scrnMsg.contrLinkNum);
        // add end   2018/11/07 K.Fujimoto QC#28627
        // START 2017/01/27 [QC#17278, MOD]
        // scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpNum);
        scrnMsg.addCheckItem(scrnMsg.dsContrRptGrpDescTxt);
        // END   2017/01/27 [QC#17278, MOD]
        scrnMsg.addCheckItem(scrnMsg.dsContrNm);
        scrnMsg.addCheckItem(scrnMsg.contrInvCmntTxt);

        addCheckItemForEndCustomer(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.xxCondCd_1V);
        scrnMsg.addCheckItem(scrnMsg.xxCondCd_2V);
        scrnMsg.addCheckItem(scrnMsg.condSqlTxt);

        setIndispensable(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrEffThruDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).baseBllgCycleCd_A);
            if (ZYPCommonFunc.hasValue(scrnMsg.dsContrStsCd)) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).basePrcDealAmt_A);
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mtrReadMethCd_A);
            // add start 2016/11/22 CSA Defect#16114
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            // add end 2016/11/22 CSA Defect#16114
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).contrEffFromDt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).contrEffThruDt_B);

            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            scrnMsg.addCheckItem(scrnMsg.B.no(i).shipToCustCd_B);
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

            addCheckItemForBase(scrnMsg, i);
            addCheckItemForOverage(scrnMsg, i);
            addCheckItemForBillingCounter(scrnMsg, i);
        }
        // START 2018/07/24 K.Kim [QC#26768, ADD]
        expandBillingCounter(scrnMsg);
        // END 2018/07/24 K.Kim [QC#26768, ADD]
    }

    /**
     * addCheckItemForEndCustomer
     * @param scrnMsg NSAL0300BMsg
     */
    public static void addCheckItemForEndCustomer(NSAL0300BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.altPayerCustCd);
        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_CP);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm_CP);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm_CP);
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
        scrnMsg.addCheckItem(scrnMsg.leaseCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.baseChrgToLeaseCmpyFlg);
        scrnMsg.addCheckItem(scrnMsg.usgChrgToLeaseCmpyFlg);
        // START 2018/05/15 K.Kitachi [QC#24265, ADD]
        scrnMsg.addCheckItem(scrnMsg.cfsLeaseNumCmntTxt);
        // END 2018/05/15 K.Kitachi [QC#24265, ADD]
        scrnMsg.addCheckItem(scrnMsg.custPoNum);
        // START 2019/01/09 K.Kitachi [QC#26928, ADD]
        scrnMsg.addCheckItem(scrnMsg.poFromDt);
        // END 2019/01/09 K.Kitachi [QC#26928, ADD]
        scrnMsg.addCheckItem(scrnMsg.poDt);
        scrnMsg.addCheckItem(scrnMsg.crCardCustRefNum);
        scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscCd);

        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_SP);
        scrnMsg.addCheckItem(scrnMsg.baseBllgCycleCd);
        scrnMsg.addCheckItem(scrnMsg.mtrBllgCycleCd);
        scrnMsg.addCheckItem(scrnMsg.mtrEstTpCd);
        // START 2022/03/22 [QC#59683, DEL]
//        scrnMsg.addCheckItem(scrnMsg.xxSelFlg_UT);
        // END   2022/03/22 [QC#59683, DEL]
        // START 2022/03/22 [QC#59683, ADD]
        scrnMsg.addCheckItem(scrnMsg.dsInvTgtrTpCd);
        // END   2022/03/22 [QC#59683, ADD]
        scrnMsg.addCheckItem(scrnMsg.prcAllocByMachQtyFlg);

        scrnMsg.addCheckItem(scrnMsg.contrAutoRnwTpCd);
        scrnMsg.addCheckItem(scrnMsg.rnwPrcMethCd);
        scrnMsg.addCheckItem(scrnMsg.basePrcUpRatio);
        scrnMsg.addCheckItem(scrnMsg.mtrPrcUpRatio);
        scrnMsg.addCheckItem(scrnMsg.befEndRnwDaysAot);

        scrnMsg.addCheckItem(scrnMsg.contrUplftTpCd);
        scrnMsg.addCheckItem(scrnMsg.uplftPrcMethCd);
        scrnMsg.addCheckItem(scrnMsg.uplftBasePrcUpRatio);
        scrnMsg.addCheckItem(scrnMsg.uplftMtrPrcUpRatio);

        // START 2023/03/03 R.Takau [QC#55645, ADD]
        scrnMsg.addCheckItem(scrnMsg.bankRteNum);
        scrnMsg.addCheckItem(scrnMsg.dsBankAcctNum);
        // END 2023/03/03 R.Takau [QC#55645, ADD]
    }

    /**
     * addCheckItemForFleetAggregateLine
     * @param scrnMsg NSAL0300BMsg
     */
    public static void addCheckItemForFleetAggregateLine(NSAL0300BMsg scrnMsg) {
        setIndispensable(scrnMsg);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            String dsContrDtlTpCd = scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue();
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).contrEffFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).contrEffThruDt_B);

                // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
                scrnMsg.addCheckItem(scrnMsg.B.no(i).shipToCustCd_B);
                // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

                addCheckItemForBase(scrnMsg, i);
                addCheckItemForOverage(scrnMsg, i);
                addCheckItemForBillingCounter(scrnMsg, i);
            }
        }
    }

    /**
     * addCheckItemForLine
     * @param scrnMsg NSAL0300BMsg
     * @param dsContrDtlPk BigDecimal
     */
    public static void addCheckItemForLine(NSAL0300BMsg scrnMsg, BigDecimal dsContrDtlPk) {
        setIndispensable(scrnMsg);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (isEqualNum(scrnMsg.B.no(i).dsContrDtlPk_B.getValue(), dsContrDtlPk)) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).contrEffFromDt_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).contrEffThruDt_B);

                addCheckItemForBase(scrnMsg, i);
                addCheckItemForOverage(scrnMsg, i);
                addCheckItemForBillingCounter(scrnMsg, i);
            }
        }
    }

    /**
     * addCheckItemForBase
     * @param scrnMsg NSAL0300BMsg
     * @param dsContrDtlPk BigDecimal
     */
    public static void addCheckItemForBase(NSAL0300BMsg scrnMsg, BigDecimal dsContrDtlPk) {
        setIndispensable(scrnMsg);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (isEqualNum(scrnMsg.B.no(i).dsContrDtlPk_B.getValue(), dsContrDtlPk)) {
                addCheckItemForBase(scrnMsg, i);
            }
        }
    }

    /**
     * addCheckItemForBase
     * @param scrnMsg NSAL0300BMsg
     * @param i index of B
     */
    private static void addCheckItemForBase(NSAL0300BMsg scrnMsg, int i) {
        scrnMsg.addCheckItem(scrnMsg.B.no(i).baseBillToCustCd_B);
        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        scrnMsg.addCheckItem(scrnMsg.B.no(i).xxPsnNm_BB);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).ctacPsnFirstNm_BB);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).ctacPsnLastNm_BB);
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
        // Mod Start 2018/01/11 QC#18552
        scrnMsg.addCheckItem(scrnMsg.B.no(i).svcPgmMdseCd_B);
//        scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseDescShortTxt_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).basePrcDealAmt_B);
        // Mod End 2018/01/11 QC#18552
        scrnMsg.addCheckItem(scrnMsg.B.no(i).basePrcTermDealAmtRate_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).baseBllgCycleCd_B);
        // Add Start 2017/12/21 QC#18779
        scrnMsg.addCheckItem(scrnMsg.B.no(i).baseBllgTmgCd_B);
        // Add End 2017/12/21 QC#18779
        scrnMsg.addCheckItem(scrnMsg.B.no(i).baseDplyPerEndDay_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).contrBllgDay_B);
        // START 2018/07/24 K.Kim [QC#26768, ADD]
        String dsContrDtlTpCd = scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue();
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && scrnMsg.A.getValidCount() > 0) {
           if (scrnMsg.B.no(i).basePrcDealAmt_B.getErrorCode() != 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_FA, IMG_OPEN_ARROW);
            }
        }
        // END 2018/07/24 K.Kim [QC#26768, ADD]
    }

    /**
     * addCheckItemForOverage
     * @param scrnMsg NSAL0300BMsg
     * @param dsContrDtlPk BigDecimal
     */
    public static void addCheckItemForOverage(NSAL0300BMsg scrnMsg, BigDecimal dsContrDtlPk) {
        setIndispensable(scrnMsg);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (isEqualNum(scrnMsg.B.no(i).dsContrDtlPk_B.getValue(), dsContrDtlPk)) {
                addCheckItemForOverage(scrnMsg, i);
            }
        }
    }

    /**
     * addCheckItemForOverage
     * @param scrnMsg NSAL0300BMsg
     * @param i index of B
     */
    private static void addCheckItemForOverage(NSAL0300BMsg scrnMsg, int i) {
        scrnMsg.addCheckItem(scrnMsg.B.no(i).mtrDplyPerEndDay_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).mtrBllgDay_B);

        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgMtrBllgCycleCd_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).xsChrgTpCd_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).xsMtrCopyQty_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).xsMtrAmtRate_B);
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsContrBllgMtrPk_B)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgMtrBillToCustCd_B);
            // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxPsnNm_BM);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).ctacPsnFirstNm_BM);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).ctacPsnLastNm_BM);
            // END 2018/06/18 K.Kitachi [QC#18591, MOD]
            // START 2019/01/17 M.Naito [QC#29083,ADD]
            scrnMsg.addCheckItem(scrnMsg.B.no(i).cumCopyCnt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).cumCopyFreqMthAot_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).cumCopyStartDt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).cumCopyEndDt_B);
            // END 2019/01/17 M.Naito [QC#29083,ADD]
        }
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgFreeCopyCnt_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgMinCnt_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgMinAmtRate_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgRollOverRatio_B);
    }

    /**
     * addCheckItemForBillingCounter
     * @param scrnMsg NSAL0300BMsg
     * @param dsContrBllgMtrPk BigDecimal
     */
    public static void addCheckItemForBillingCounter(NSAL0300BMsg scrnMsg, BigDecimal dsContrBllgMtrPk) {
        setIndispensable(scrnMsg);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (isEqualNum(scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue(), dsContrBllgMtrPk)) {
                addCheckItemForBillingCounter(scrnMsg, i);
            }
        }
    }

    /**
     * addCheckItemForBillingCounter
     * @param scrnMsg NSAL0300BMsg
     * @param i index of B
     */
    private static void addCheckItemForBillingCounter(NSAL0300BMsg scrnMsg, int i) {
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgFreeCopyCnt_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgMinCnt_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgMinAmtRate_B);
        scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgRollOverRatio_B);
    }

    /**
     * Dynamically change the indispensability of the input fields
     * @param scrnMsg
     */
    public static void setIndispensable(NSAL0300BMsg scrnMsg) {
        // START 2017/07/27 [QC#16889, MOD]
        // String preSerNum = INVLD_SER_NUM;
        // String preMdseCd = INVLD_MDSE_CD;
        BigDecimal preDsContrDtlPk = BigDecimal.ONE.negate();
        // END 2017/07/27 [QC#16889, MOD]
        BigDecimal preDsContrBllgMtrPk = BigDecimal.ONE.negate();
        // Reset
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).contrEffFromDt_B.setIndispensable(false);
            scrnMsg.B.no(i).contrEffThruDt_B.setIndispensable(false);
            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            scrnMsg.B.no(i).shipToCustCd_B.setIndispensable(false);
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
            scrnMsg.B.no(i).baseBillToCustCd_B.setIndispensable(false);
            // del start 2016/09/23 CSA Defect#13686
            //scrnMsg.B.no(i).xxPsnNm_BB.setIndispensable(false);
            // del end 2016/09/23 CSA Defect#13686
            // Add Start 2017/12/21 QC#18779
            scrnMsg.B.no(i).baseBllgTmgCd_B.setIndispensable(false);
            // Add End 2017/12/21 QC#18779
            // START 2016/05/16 T.Kanasaka [QC#2184, MOD]
            scrnMsg.B.no(i).baseDplyPerEndDay_B.setIndispensable(false);
            // END 2016/05/16 T.Kanasaka [QC#2184, MOD]
            scrnMsg.B.no(i).contrBllgDay_B.setIndispensable(false);
            scrnMsg.B.no(i).svcPgmMdseCd_B.setIndispensable(false);
            // add start 2016/07/01 CSA Defect#11261
            scrnMsg.B.no(i).mdseDescShortTxt_B.setIndispensable(false);
            // add end 2016/07/01 CSA Defect#11261
            scrnMsg.B.no(i).baseBllgCycleCd_B.setIndispensable(false);
            // START 2016/04/13 T.Kanasaka [QC4957, ADD]
            scrnMsg.B.no(i).basePrcDealAmt_B.setIndispensable(false);
            // END 2016/04/13 T.Kanasaka [QC4957, ADD]

            // START 2016/05/16 T.Kanasaka [QC#2184, MOD]
            scrnMsg.B.no(i).mtrDplyPerEndDay_B.setIndispensable(false);
            // END 2016/05/16 T.Kanasaka [QC#2184, MOD]
            scrnMsg.B.no(i).mtrBllgDay_B.setIndispensable(false);
            scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setIndispensable(false);
            // del start 2016/09/23 CSA Defect#13686
            //scrnMsg.B.no(i).xxPsnNm_BM.setIndispensable(false);
            // del end 2016/09/23 CSA Defect#13686
            // START 2016/02/26 T.Kanasaka [QC4092, ADD]
            scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setIndispensable(false);
            scrnMsg.B.no(i).xsChrgTpCd_B.setIndispensable(false);
            // END 2016/02/26 T.Kanasaka [QC4092, ADD]

            scrnMsg.B.no(i).xsMtrCopyQty_B.setIndispensable(false);
            scrnMsg.B.no(i).xsMtrAmtRate_B.setIndispensable(false);
        }
        // Add Start 2019/02/15 QC#30295
        BigDecimal nextDsContrBllgMtrPk = BigDecimal.ONE.negate();
        int nextIdx = 0;
        // Add End 2019/02/15 QC#30295
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // START 2017/07/27 [QC#16889, MOD]
            // String curSerNum = scrnMsg.B.no(i).serNum_B.getValue();
            // String curMdseCd = scrnMsg.B.no(i).mdseCd_B.getValue();
            BigDecimal curDsContrDtlPk = scrnMsg.B.no(i).dsContrDtlPk_B.getValue();
            // END 2017/07/27 [QC#16889, MOD]
            BigDecimal curDsContrBllgMtrPk = scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
            String dsContrDtlTpCd = scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue();
            // START 2017/07/27 [QC#16889, MOD]
            // if (!isEqualMach(preSerNum, preMdseCd, curSerNum, curMdseCd)) {
            if (!isEqualNum(preDsContrDtlPk, curDsContrDtlPk)) {
                // preSerNum = curSerNum;
                // preMdseCd = curMdseCd;
                preDsContrDtlPk =  curDsContrDtlPk;
                // END 2017/07/27 [QC#16889, MOD]
                preDsContrBllgMtrPk = BigDecimal.ONE.negate();

                if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd)) {
                    scrnMsg.B.no(i).contrEffFromDt_B.setIndispensable(true);
                    scrnMsg.B.no(i).contrEffThruDt_B.setIndispensable(true);
                    scrnMsg.B.no(i).baseBillToCustCd_B.setIndispensable(true);
                    // del start 2016/09/23 CSA Defect#13686
                    //scrnMsg.B.no(i).xxPsnNm_BB.setIndispensable(true);
                    // del end 2016/09/23 CSA Defect#13686
                    // Add Start 2017/12/21 QC#18779
                    scrnMsg.B.no(i).baseBllgTmgCd_B.setIndispensable(true);
                    // Add End 2017/12/21 QC#18779
                    // START 2016/05/16 T.Kanasaka [QC#2184, MOD]
                    scrnMsg.B.no(i).baseDplyPerEndDay_B.setIndispensable(true);
                    // END 2016/05/16 T.Kanasaka [QC#2184, MOD]
                    scrnMsg.B.no(i).contrBllgDay_B.setIndispensable(true);
                    // Mod Start 2018/01/11 QC#18552
                    scrnMsg.B.no(i).svcPgmMdseCd_B.setIndispensable(true);
                    // add start 2016/07/01 CSA Defect#11261
//                    scrnMsg.B.no(i).mdseDescShortTxt_B.setIndispensable(true);
                    // add end 2016/07/01 CSA Defect#11261
                    scrnMsg.B.no(i).baseBllgCycleCd_B.setIndispensable(true);
//                    // START 2016/04/13 T.Kanasaka [QC4957, ADD]
//                    if (!(DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && scrnMsg.A.getValidCount() == 0)) {
//                        scrnMsg.B.no(i).basePrcDealAmt_B.setIndispensable(true);
//                    }
                    if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && scrnMsg.A.getValidCount() > 0) {
                        scrnMsg.B.no(i).basePrcDealAmt_B.setIndispensable(true);
                    }
//                    // END 2016/04/13 T.Kanasaka [QC4957, ADD]
                    // Mod End 2018/01/11 QC#18552
                }

                // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
                if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
                    scrnMsg.B.no(i).shipToCustCd_B.setIndispensable(true);
                }
                // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                    // START 2016/05/16 T.Kanasaka [QC#2184, MOD]
                    scrnMsg.B.no(i).mtrDplyPerEndDay_B.setIndispensable(true);
                    // END 2016/05/16 T.Kanasaka [QC#2184, MOD]
                    scrnMsg.B.no(i).mtrBllgDay_B.setIndispensable(true);
                }
            }
            if (!isEqualNum(preDsContrBllgMtrPk, curDsContrBllgMtrPk)) {
                preDsContrBllgMtrPk = curDsContrBllgMtrPk;
                // START 2016/09/05 T.Kanasaka [QC#14208, MOD]
//                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(curDsContrBllgMtrPk)) {
                // END 2016/09/05 T.Kanasaka [QC#14208, MOD]
                    // Del Start 2019/02/15 QC#30295
                    // scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setIndispensable(true);
                    // Del End 2019/02/15 QC#30295
                    // del start 2016/09/23 CSA Defect#13686
                    //scrnMsg.B.no(i).xxPsnNm_BM.setIndispensable(true);
                    // del end 2016/09/23 CSA Defect#13686
                    // START 2016/02/19 T.Tomita [QC#3985, ADD]
                    scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setIndispensable(true);
                    // END 2016/02/19 T.Tomita [QC#3985, ADD]
                    // START 2016/02/23 T.Kanasaka [QC3188, ADD]
                    scrnMsg.B.no(i).xsChrgTpCd_B.setIndispensable(true);
                    // END 2016/02/23 T.Kanasaka [QC3188, ADD]
                }
            }
            // Add Start 2019/02/15 QC#30295
            nextIdx = i + 1;
            if (nextIdx < scrnMsg.B.getValidCount()) {
                nextDsContrBllgMtrPk = scrnMsg.B.no(nextIdx).dsContrBllgMtrPk_B.getValue();
            } else if (nextIdx == scrnMsg.B.getValidCount()) {
                nextDsContrBllgMtrPk = null;
            }
            if (!isEqualNum(curDsContrBllgMtrPk, nextDsContrBllgMtrPk)) {
                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(curDsContrBllgMtrPk)) {
                    scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setIndispensable(true);
                }
            }
            // Add End 2019/02/15 QC#30295
            // check excess copy in NSAL0300BL06
        }
    }

    public static void setupScreenItems(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
        List<String> functionList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        reset(handler, scrnMsg);
        protectReadOnlyItems(handler, scrnMsg);
        protectItemBasedOnContractCategory(handler, scrnMsg);
        // START 2017/09/13 K.Kim [QC#19938, ADD]
        protectBilledCloseItems(handler, scrnMsg);
        // END START 2017/09/13 K.Kim [QC#19938, ADD]
        // START 2017/07/05 K.Kim [QC#18110, DEL]
//        protectItemForAccessory(handler, scrnMsg);
        // END 2017/07/05 K.Kim [QC#18110, DEL]
        // START 2016/05/10 T.Kanasaka [QC#6798, ADD]
        protectItemForRenewUplift(handler, scrnMsg);
        // END 2016/05/10 T.Kanasaka [QC#6798, ADD]
        // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
        protectItemForPerEndDay(handler, scrnMsg);
        // END 2016/05/17 T.Kanasaka [QC#2184, ADD]
        protectItemBasedOnContractStatus(handler, scrnMsg);
        protectItemBasedOnPrivilege(handler, functionList, scrnMsg);
        setAppFracDigit(scrnMsg);
        // Add Start 12/20/2016 <QC#16647>
        protectNoMtrLines(handler, scrnMsg);
        // Add Ende  12/20/2016 <QC#16647>
        alternateTableRowColor(scrnMsg);
        // START 2016/02/18 T.Aoyagi [QC3700, ADD]
//        changeSummaryDetailBtnNm(handler, scrnMsg);
        // END 2016/02/18 T.Aoyagi [QC3700, ADD]
        controllError(scrnMsg);
        setMachineListAllIcon(scrnMsg);
    }

    private static void reset(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
        scrnMsg.setInputProtected(false);
        handler.setButtonEnabledAll();
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 1, null);
        //Mod Start 04/20/2016 <QC#5130>
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        //Mod End   04/20/2016 <QC#5130>
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // START 2018/06/18 K.Kitachi [QC#18591, ADD]
        scrnMsg.ctacPsnFirstNm_PL.setValue(ZYPConstant.FLG_ON_Y);
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).ctacPsnFirstNm_BL.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.B.no(i).ctacPsnFirstNm_ML.setValue(ZYPConstant.FLG_ON_Y);
        }
        // END 2018/06/18 K.Kitachi [QC#18591, ADD]
    }

    private static void protectReadOnlyItems(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
        scrnMsg.dsContrCatgAbbrNm.setInputProtected(true);
        scrnMsg.svcContrBrDescTxt.setInputProtected(true);
        // START 2016/01/21 T.Tomita [QC#2182, DEL]
//        scrnMsg.contrAdminPsnCd.setInputProtected(true);
//        scrnMsg.xxPsnNm.setInputProtected(true);
        // END 2016/01/21 T.Tomita [QC#2182, DEL]
        scrnMsg.dsContrStsDescTxt.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.billToLocNm.setInputProtected(true);
        scrnMsg.dsAcctNm_L.setInputProtected(true);
        scrnMsg.contrRnwTotCnt.setInputProtected(true);
        scrnMsg.xxMthDt_CC.setInputProtected(true);
        scrnMsg.xxYrDt_CC.setInputProtected(true);
        // START 2016/02/09 T.Aoyagi [QC4081, ADD]
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
        // END 2016/02/09 T.Aoyagi [QC4081, ADD]
        // START 2019/11/27 E.Kameishi [QC#54594, ADD]
        scrnMsg.xxScrItem10Txt.setInputProtected(true);
        // END 2019/11/27 E.Kameishi [QC#54594, ADD]
        // START 2016/02/10 T.Tomita [QC3887, ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.leaseCmpyCd)) {
            scrnMsg.baseChrgToLeaseCmpyFlg.setInputProtected(true);
            scrnMsg.usgChrgToLeaseCmpyFlg.setInputProtected(true);
        }
        // END 2016/02/10 T.Tomita [QC3887, ADD]
        // START 2018/05/15 K.Kitachi [QC#24265, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.baseChrgToLeaseCmpyFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.usgChrgToLeaseCmpyFlg.getValue())) {
            scrnMsg.cfsLeaseNumCmntTxt.setInputProtected(true);
        }
        // END 2018/05/15 K.Kitachi [QC#24265, ADD]
        // START 2018/01/30 M.Naito [QC#21349, MOD]
        // START 2016/02/10 T.Tomita [QC3981, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.dsContrCatgCd) && DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
//            scrnMsg.xxSelFlg_UT.setInputProtected(true);
            scrnMsg.prcAllocByMachQtyFlg.setInputProtected(false);
        } else {
//            scrnMsg.xxSelFlg_UT.setInputProtected(false);
            scrnMsg.prcAllocByMachQtyFlg.setInputProtected(true);
        }
        // START 2022/03/22 [QC#59683, DEL]
//        scrnMsg.xxSelFlg_UT.setInputProtected(false);
        // END   2022/03/22 [QC#59683, DEL]
        // END 2016/02/10 T.Tomita [QC3981, ADD]
        // END 2018/01/30 M.Naito [QC#21349, MOD]
        // START 2022/03/22 [QC#59683, ADD]
        scrnMsg.dsInvTgtrTpCd.setInputProtected(false);
        // END   2022/03/22 [QC#59683, ADD]

        scrnMsg.dsAcctNm_EC.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_EC.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).sqId_A.setInputProtected(true);
            // Mod Start 2018/01/11 QC#18552
//            scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
//            scrnMsg.A.no(i).mdlNm_A.setInputProtected(true);
            // Mod End 2018/01/11 QC#18552
            // Add Start 2017/11/06 QC#18552
            scrnMsg.A.no(i).dsContrDtlStsCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrDtlStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcPgmMdseCd_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_AS.setInputProtected(true);
            scrnMsg.A.no(i).prcMtrPkgNm_A.setInputProtected(true);
            // Add End 2017/11/06 QC#18552
            scrnMsg.A.no(i).billToCustLocAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).rnwEffFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).contrRnwTotCnt_A.setInputProtected(true);
            scrnMsg.A.no(i).contrCloDt_A.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // Add Start 2017/11/06 QC#18552
            scrnMsg.B.no(i).xxListNum_B.setInputProtected(true);
            // Add End 2017/11/06 QC#18552
            scrnMsg.B.no(i).svcMachMstrPk_B.setInputProtected(true);
            scrnMsg.B.no(i).serNum_B.setInputProtected(true);
            scrnMsg.B.no(i).mdseCd_B.setInputProtected(true);
            // Add Start 2017/11/06 QC#18552
            scrnMsg.B.no(i).mdseDescShortTxt_BI.setInputProtected(true);
            scrnMsg.B.no(i).mdseDescShortTxt_B.setInputProtected(true);
            // Add End 2017/11/06 QC#18552
            scrnMsg.B.no(i).mdlNm_B.setInputProtected(true);
            scrnMsg.B.no(i).nextBllgDt_BB.setInputProtected(true);
            scrnMsg.B.no(i).nextBllgDt_BU.setInputProtected(true);
            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            // Mod Start 2017/11/06 QC#18552
            scrnMsg.B.no(i).shipToCustCd_B.setInputProtected(true);
            // START 2018/07/27 [QC#24795, ADD]
            handler.setButtonEnabled(BTN_OPENWIN_SHIP_TO, i, false);
            // END   2018/07/27 [QC#24795, ADD]
            scrnMsg.B.no(i).shipToLocNm_B.setInputProtected(true);
            scrnMsg.B.no(i).shipToCustLocAddr_B.setInputProtected(true);
            scrnMsg.B.no(i).svcBrCd_B.setInputProtected(true);
            scrnMsg.B.no(i).svcBrCdDescTxt_B.setInputProtected(true);
            // Mod End 2017/11/06 QC#18552
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
            scrnMsg.B.no(i).billToLocNm_BB.setInputProtected(true);
            scrnMsg.B.no(i).billToLocNm_BM.setInputProtected(true);
            scrnMsg.B.no(i).billToCustLocAddr_BM.setInputProtected(true);
            scrnMsg.B.no(i).mtrLbDescTxt_B.setInputProtected(true);
            // START 2016/02/09 T.Kanasaka [QC3273, ADD]
            // START 2018/02/19 M.Kidokoro [QC#23629, DEL]
//            if (ZYPCommonFunc.hasValue((scrnMsg.B.no(i).bllgFreeCopyCnt_B)) && ZYPCommonFunc.hasValue((scrnMsg.B.no(i).bllgRollOverRatio_B))) {
//                scrnMsg.B.no(i).bllgRollOverRatio_B.setInputProtected(true);
//            }
            // END 2018/02/19 M.Kidokoro [QC#23629, DEL]
            // END 2016/02/09 T.Kanasaka [QC3273, ADD]
            // START 2016/02/10 T.Kanasaka [QC30558, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSelFlg_UT.getValue())) {
                // START 2016/05/17 T.Kanasaka [QC#2184, MOD]
                scrnMsg.B.no(i).mtrDplyPerEndDay_B.setInputProtected(true);
                // END 2016/05/17 T.Kanasaka [QC#2184, MOD]
                scrnMsg.B.no(i).mtrBllgDay_B.setInputProtected(true);
            }
            // END 2016/02/10 T.Kanasaka [QC30558, ADD]
            // START 2016/02/18 T.Kanasaka [QC3270, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.baseChrgToLeaseCmpyFlg.getValue())) {
                scrnMsg.B.no(i).baseBillToCustCd_B.setInputProtected(true);
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setInputProtected(true);
            }
            // END 2016/02/18 T.Kanasaka [QC3270, ADD]

            scrnMsg.B.no(i).rnwEffFromDt_B.setInputProtected(true);
            scrnMsg.B.no(i).contrRnwTotCnt_B.setInputProtected(true);
            scrnMsg.B.no(i).contrCloDt_B.setInputProtected(true);
            scrnMsg.B.no(i).billToCustLocAddr_BB.setInputProtected(true);

            scrnMsg.B.no(i).mtrLbDescTxt_BX.setInputProtected(true);
            scrnMsg.B.no(i).xxComnScrColValTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).contrMtrMultRate_B.setInputProtected(true);

            // START 2017/09/21 K.Kitachi [QC#21222, ADD]
            scrnMsg.B.no(i).basePrcTermDealAmtRate_B.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B9.getValue())) {
                scrnMsg.B.no(i).basePrcDealAmt_B.setInputProtected(true);
                scrnMsg.B.no(i).baseBllgCycleCd_B.setInputProtected(true);
                // START 2017/09/26 K.Kitachi [QC#21222, ADD]
                for (int j = 0; j < scrnMsg.A.getValidCount(); j++) {
                    if (scrnMsg.B.no(i).dsContrDtlPk_B.getValue().compareTo(scrnMsg.A.no(j).dsContrDtlPk_A.getValue()) == 0) {
                        scrnMsg.A.no(j).basePrcDealAmt_A.setInputProtected(true);
                        scrnMsg.A.no(j).baseBllgCycleCd_A.setInputProtected(true);
                        break;
                    }
                }
                // END 2017/09/26 K.Kitachi [QC#21222, ADD]
            }
            // END 2017/09/21 K.Kitachi [QC#21222, ADD]
            // START 2023/08/18 T.Kojima [QC#60846, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_BC.getValue())) {
                scrnMsg.B.no(i).xsMtrCopyQty_B.setInputProtected(true);
                scrnMsg.B.no(i).xsMtrAmtRate_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setInputProtected(true);
            }
            // END 2023/08/18 T.Kojima [QC#60846, ADD]
            // Start 2019/01/21 T.Wada [QC#29371] 
            if (!DS_CONTR_DTL_TP.ACCESSORIES.equals(scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                scrnMsg.B.no(i).xxTpCd_B.setInputProtected(true);
            }
            // End 2019/01/21 T.Wada [QC#29371]

        }
        // START 2023/03/03 R.Takau [QC#55645, ADD]
        scrnMsg.bankRteNum.setInputProtected(true);
        scrnMsg.dsBankAcctNum.setInputProtected(true);
        // END 2023/03/03 R.Takau [QC#55645, ADD]
    }

    private static void protectItemBasedOnContractCategory(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {

        String dsContrCatgCd = scrnMsg.dsContrCatgCd.getValue();

        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {

//            scrnMsg.prcAllocByMachQtyFlg.setInputProtected(true);

        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {

            scrnMsg.prcAllocByMachQtyFlg.setInputProtected(true);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).baseBllgCycleCd_A.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAmt_A.setInputProtected(true);
            }

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                String dsContrDtlTpCd = scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue();
                if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
                } else {
                    handler.setButtonEnabled(BTN_OPENWIN_ADDITIONALCHARGE, i, false);
                    handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_BASE, i, false);
                    scrnMsg.B.no(i).baseBillToCustCd_B.setInputProtected(true);
                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                    handler.setButtonEnabled(BTN_OPENWIN_CONTACT_BASE, i, false);
//                    scrnMsg.B.no(i).xxPsnNm_BB.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_BL.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_BL.clear();
                    scrnMsg.B.no(i).ctacPsnFirstNm_BB.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnLastNm_BB.setInputProtected(true);
                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    // Add Start 2017/12/21 QC#18779
                    scrnMsg.B.no(i).baseBllgTmgCd_B.setInputProtected(true);
                    // Add End 2017/12/21 QC#18779
                    scrnMsg.B.no(i).baseDplyPerEndDay_B.setInputProtected(true);
                    scrnMsg.B.no(i).contrBllgDay_B.setInputProtected(true);
                    scrnMsg.B.no(i).svcPgmMdseCd_B.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM_BASE, i, false);
                    scrnMsg.B.no(i).mdseDescShortTxt_B.setInputProtected(true);
                    scrnMsg.B.no(i).baseBllgCycleCd_B.setInputProtected(true);
                    scrnMsg.B.no(i).basePrcDealAmt_B.setInputProtected(true);
                    scrnMsg.B.no(i).basePrcTermDealAmtRate_B.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_BASE, i, false);
                    handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_BASE, i, false);

                    scrnMsg.B.no(i).mtrDplyPerEndDay_B.setInputProtected(true);
                    scrnMsg.B.no(i).mtrBllgDay_B.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPENWIN_BILLING_COUNTERS, i, false);

                    handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_METER, i, false);
                    scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setInputProtected(true);
                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                    handler.setButtonEnabled(BTN_OPENWIN_CONTACT_METER, i, false);
//                    scrnMsg.B.no(i).xxPsnNm_BM.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_ML.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_ML.clear();
                    scrnMsg.B.no(i).ctacPsnFirstNm_BM.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnLastNm_BM.setInputProtected(true);
                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setInputProtected(true);
                    scrnMsg.B.no(i).xsChrgTpCd_B.setInputProtected(true);
                    scrnMsg.B.no(i).bllgMinCnt_B.setInputProtected(true);
                    scrnMsg.B.no(i).bllgMinAmtRate_B.setInputProtected(true);
                    scrnMsg.B.no(i).bllgRollOverRatio_B.setInputProtected(true);
                    scrnMsg.B.no(i).bllgFreeCopyCnt_B.setInputProtected(true);
                    // START 2019/01/17 M.Naito [QC#29083,ADD]
                    scrnMsg.B.no(i).cumCopyCnt_B.setInputProtected(true);
                    scrnMsg.B.no(i).cumCopyFreqMthAot_B.setInputProtected(true);
                    scrnMsg.B.no(i).cumCopyStartDt_B.setInputProtected(true);
                    scrnMsg.B.no(i).cumCopyEndDt_B.setInputProtected(true);
                    // END 2019/01/17 M.Naito [QC#29083,ADD]

                    scrnMsg.B.no(i).xsMtrCopyQty_B.setInputProtected(true);
                    scrnMsg.B.no(i).xsMtrAmtRate_B.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_METER, i, false);
                    handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_USAGE, i, false);
                }
            }

        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).baseBllgCycleCd_A.setInputProtected(true);
            }

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                String dsContrDtlTpCd = scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue();
                if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                    handler.setButtonEnabled(BTN_OPENWIN_ADDITIONALCHARGE, i, false);
                    scrnMsg.B.no(i).basePrcDealAmt_B.setInputProtected(true);
                    scrnMsg.B.no(i).bllgMinCnt_B.setInputProtected(true);
                    scrnMsg.B.no(i).bllgMinAmtRate_B.setInputProtected(true);
                    // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//                    scrnMsg.B.no(i).bllgRollOverRatio_B.setInputProtected(true);
//                    scrnMsg.B.no(i).bllgFreeCopyCnt_B.setInputProtected(true);
                    // END 2018/05/14 K.Kitachi [QC#23541, DEL]
                    scrnMsg.B.no(i).xsMtrCopyQty_B.setInputProtected(true);
                } else {
                    // START 2018/10/24 K.Kitachi [QC#28889, DEL]
//                    handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_BASE, i, false);
//                    scrnMsg.B.no(i).baseBillToCustCd_B.setInputProtected(true);
//                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
////                    handler.setButtonEnabled(BTN_OPENWIN_CONTACT_BASE, i, false);
////                    scrnMsg.B.no(i).xxPsnNm_BB.setInputProtected(true);
//                    scrnMsg.B.no(i).ctacPsnFirstNm_BL.setInputProtected(true);
//                    scrnMsg.B.no(i).ctacPsnFirstNm_BL.clear();
//                    scrnMsg.B.no(i).ctacPsnFirstNm_BB.setInputProtected(true);
//                    scrnMsg.B.no(i).ctacPsnLastNm_BB.setInputProtected(true);
//                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    // END 2018/10/24 K.Kitachi [QC#28889, DEL]
                    // Add Start 2017/12/21 QC#18779
                    scrnMsg.B.no(i).baseBllgTmgCd_B.setInputProtected(true);
                    // Add End 2017/12/21 QC#18779
                    scrnMsg.B.no(i).baseDplyPerEndDay_B.setInputProtected(true);
                    scrnMsg.B.no(i).contrBllgDay_B.setInputProtected(true);
                    // START 2018/09/27 [QC#27777, DEL]
                    // scrnMsg.B.no(i).svcPgmMdseCd_B.setInputProtected(true);
                    // handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM_BASE, i, false);
                    // END 2018/09/27 [QC#27777, DEL]
                    scrnMsg.B.no(i).mdseDescShortTxt_B.setInputProtected(true);
                    scrnMsg.B.no(i).baseBllgCycleCd_B.setInputProtected(true);

                    scrnMsg.B.no(i).mtrDplyPerEndDay_B.setInputProtected(true);
                    scrnMsg.B.no(i).mtrBllgDay_B.setInputProtected(true);
                    handler.setButtonEnabled(BTN_OPENWIN_BILLING_COUNTERS, i, false);

                    handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_METER, i, false);
                    scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setInputProtected(true);
                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                    handler.setButtonEnabled(BTN_OPENWIN_CONTACT_METER, i, false);
//                    scrnMsg.B.no(i).xxPsnNm_BM.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_ML.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_ML.clear();
                    scrnMsg.B.no(i).ctacPsnFirstNm_BM.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnLastNm_BM.setInputProtected(true);
                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setInputProtected(true);
                    scrnMsg.B.no(i).xsChrgTpCd_B.setInputProtected(true);
                    // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//                    scrnMsg.B.no(i).bllgMinCnt_B.setInputProtected(true);
//                    scrnMsg.B.no(i).bllgMinAmtRate_B.setInputProtected(true);
                    // END 2018/05/14 K.Kitachi [QC#23541, DEL]
                    scrnMsg.B.no(i).bllgRollOverRatio_B.setInputProtected(true);
                    scrnMsg.B.no(i).bllgFreeCopyCnt_B.setInputProtected(true);

                    scrnMsg.B.no(i).xsMtrAmtRate_B.setInputProtected(true);
                    scrnMsg.B.no(i).xxLinkProt_BA.setInputProtected(true);
                    scrnMsg.B.no(i).xxLinkProt_BD.setInputProtected(true);
                    // START 2019/01/17 M.Naito [QC#29083,ADD]
                    scrnMsg.B.no(i).cumCopyCnt_B.setInputProtected(true);
                    scrnMsg.B.no(i).cumCopyFreqMthAot_B.setInputProtected(true);
                    scrnMsg.B.no(i).cumCopyStartDt_B.setInputProtected(true);
                    scrnMsg.B.no(i).cumCopyEndDt_B.setInputProtected(true);
                    // END 2019/01/17 M.Naito [QC#29083,ADD]
                }
            }
        } else if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            scrnMsg.dsContrClsCd.setInputProtected(true);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrClsCd, scrnMsg.dsContrClsCd_WT);

            handler.setButtonEnabled(BTN_OPENWIN_LEASECOMPANY, false);
            scrnMsg.leaseCmpyCd.setInputProtected(true);
            scrnMsg.leaseCmpyCd.clear();
            scrnMsg.dsAcctNm_L.clear();
            scrnMsg.baseChrgToLeaseCmpyFlg.setInputProtected(true);
            scrnMsg.usgChrgToLeaseCmpyFlg.setInputProtected(true);
            // START 2018/05/15 K.Kitachi [QC#24265, ADD]
            scrnMsg.cfsLeaseNumCmntTxt.setInputProtected(true);
            // END 2018/05/15 K.Kitachi [QC#24265, ADD]
            scrnMsg.custPoNum.setInputProtected(true);
            // START 2019/01/09 K.Kitachi [QC#26928, ADD]
            scrnMsg.poFromDt.setInputProtected(true);
            // END 2019/01/09 K.Kitachi [QC#26928, ADD]
            scrnMsg.poDt.setInputProtected(true);
            handler.setButtonEnabled(BTN_OPENWIN_CREDIT_CARD, false);
            scrnMsg.crCardCustRefNum.setInputProtected(true);
            handler.setButtonEnabled(BTN_OPENWIN_PAYMENT_TERM, false);
            scrnMsg.pmtTermCashDiscCd.setInputProtected(true);

            // START 2017/02/15 K.Kitachi [QC#17304, MOD]
            scrnMsg.contrVrsnEffFromDt.setInputProtected(false);
//            scrnMsg.contrDurnAot.setInputProtected(true);
            scrnMsg.contrVrsnEffThruDt.setInputProtected(false);
//            scrnMsg.bllgCycleUomCd.setInputProtected(true);
            // add start 2018/11/07 K.Fujimoto QC#28627
            scrnMsg.contrLinkNum.setInputProtected(true);
            // add end   2018/11/07 K.Fujimoto QC#28627
            scrnMsg.mtrEstTpCd.setInputProtected(true);
            // START 2022/03/22 [QC#59683, DEL]
//            scrnMsg.xxSelFlg_UT.setInputProtected(true);
            // END   2022/03/22 [QC#59683, DEL]
            // START 2022/03/22 [QC#59683, ADD]
            scrnMsg.dsInvTgtrTpCd.setInputProtected(true);
            // END   2022/03/22 [QC#59683, ADD]
            scrnMsg.prcAllocByMachQtyFlg.setInputProtected(true);
            scrnMsg.svcPgmMdseCd.setInputProtected(true);
//            handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM, false);
//            scrnMsg.mdseDescShortTxt_SP.setInputProtected(true);
            scrnMsg.baseBllgCycleCd.setInputProtected(true);
            scrnMsg.mtrBllgCycleCd.setInputProtected(true);
            // END 2017/02/15 K.Kitachi [QC#17304, MOD]

            scrnMsg.contrAutoRnwTpCd.setInputProtected(true);
            scrnMsg.befEndRnwDaysAot.setInputProtected(true);
            scrnMsg.rnwPrcMethCd.setInputProtected(true);
            scrnMsg.basePrcUpRatio.setInputProtected(true);
            scrnMsg.mtrPrcUpRatio.setInputProtected(true);

            scrnMsg.contrUplftTpCd.setInputProtected(true);
            scrnMsg.uplftPrcMethCd.setInputProtected(true);
            scrnMsg.uplftBasePrcUpRatio.setInputProtected(true);
            scrnMsg.uplftMtrPrcUpRatio.setInputProtected(true);

            // Add Start 2018/01/14 QC#18552
            handler.setButtonEnabled(BTN_OPENWIN_CREDITCARDPO, false);
            handler.setButtonEnabled(BTN_OPENWIN_UPLIFTDETAIL, false);
            handler.setButtonEnabled(BTN_OPENWIN_ESCALATION, false);
            // Add End 2018/01/14 QC#18552

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).baseBllgCycleCd_A.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAmt_A.setInputProtected(true);
                scrnMsg.A.no(i).mtrReadMethCd_A.setInputProtected(true);
            }

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                handler.setButtonEnabled(BTN_OPENWIN_ADDITIONALCHARGE, i, false);

                // Add Start 2017/12/21 QC#18779
                scrnMsg.B.no(i).baseBllgTmgCd_B.setInputProtected(true);
                // Add End 2017/12/21 QC#18779
                scrnMsg.B.no(i).baseDplyPerEndDay_B.setInputProtected(true);
                scrnMsg.B.no(i).contrBllgDay_B.setInputProtected(true);
                scrnMsg.B.no(i).baseBllgCycleCd_B.setInputProtected(true);
                scrnMsg.B.no(i).basePrcDealAmt_B.setInputProtected(true);
                scrnMsg.B.no(i).basePrcTermDealAmtRate_B.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_BASE, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_BASE, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM_BASE, i, true);

                scrnMsg.B.no(i).mtrDplyPerEndDay_B.setInputProtected(true);
                scrnMsg.B.no(i).mtrBllgDay_B.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_BILLING_COUNTERS, i, false);

                handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_METER, i, false);
                scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setInputProtected(true);
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                handler.setButtonEnabled(BTN_OPENWIN_CONTACT_METER, i, false);
//                scrnMsg.B.no(i).xxPsnNm_BM.setInputProtected(true);
                scrnMsg.B.no(i).ctacPsnFirstNm_ML.setInputProtected(true);
                scrnMsg.B.no(i).ctacPsnFirstNm_ML.clear();
                scrnMsg.B.no(i).ctacPsnFirstNm_BM.setInputProtected(true);
                scrnMsg.B.no(i).ctacPsnLastNm_BM.setInputProtected(true);
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setInputProtected(true);
                scrnMsg.B.no(i).xsChrgTpCd_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgMinCnt_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgMinAmtRate_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgRollOverRatio_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgFreeCopyCnt_B.setInputProtected(true);

                scrnMsg.B.no(i).xsMtrCopyQty_B.setInputProtected(true);
                scrnMsg.B.no(i).xsMtrAmtRate_B.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_METER, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_USAGE, i, false);
                scrnMsg.B.no(i).xxLinkProt_BA.setInputProtected(true);
                scrnMsg.B.no(i).xxLinkProt_BD.setInputProtected(true);
                // START 2019/01/17 M.Naito [QC#29083,ADD]
                scrnMsg.B.no(i).cumCopyCnt_B.setInputProtected(true);
                scrnMsg.B.no(i).cumCopyFreqMthAot_B.setInputProtected(true);
                scrnMsg.B.no(i).cumCopyStartDt_B.setInputProtected(true);
                scrnMsg.B.no(i).cumCopyEndDt_B.setInputProtected(true);
                // END 2019/01/17 M.Naito [QC#29083,ADD]
            }
        }
    }

    // START 2017/09/13 K.Kim [QC#19938, ADD]
    private static void protectBilledCloseItems(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
        Map<BigDecimal, Boolean> billedMap = new HashMap<BigDecimal, Boolean>();

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // Mod Start 2018/08/24 QC#26687
            // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B8.getValue())) {
            // Mod End 2018/08/24 QC#26687
            // Base
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B6.getValue())) {
                scrnMsg.B.no(i).basePrcDealAmt_B.setInputProtected(true);
                scrnMsg.B.no(i).baseBllgCycleCd_B.setInputProtected(true);
                scrnMsg.B.no(i).basePrcTermDealAmtRate_B.setInputProtected(true);
                // Add Start 2017/12/21 QC#18779
                scrnMsg.B.no(i).baseBllgTmgCd_B.setInputProtected(true);
                // Add End 2017/12/21 QC#18779
                scrnMsg.B.no(i).baseDplyPerEndDay_B.setInputProtected(true);
                scrnMsg.B.no(i).contrBllgDay_B.setInputProtected(true);
            }
            // Usage
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B7.getValue())) {
                scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setInputProtected(true);
                scrnMsg.B.no(i).xsChrgTpCd_B.setInputProtected(true);
                scrnMsg.B.no(i).xsMtrCopyQty_B.setInputProtected(true);
                scrnMsg.B.no(i).xsMtrAmtRate_B.setInputProtected(true);
            }

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B6.getValue())
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B7.getValue())) {
                scrnMsg.B.no(i).contrEffFromDt_B.setInputProtected(true);
                scrnMsg.B.no(i).contrEffThruDt_B.setInputProtected(true);
                billedMap.put(scrnMsg.B.no(i).dsContrDtlPk_B.getValue(), true);
            } else {
                billedMap.put(scrnMsg.B.no(i).dsContrDtlPk_B.getValue(), false);
            }
            // Mod Start 2018/08/24 QC#26687
            // } else {
            //     billedMap.put(scrnMsg.B.no(i).dsContrDtlPk_B.getValue(), false);
            // }
            // Mod End 2018/08/24 QC#26687
        }

        // Machine
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (billedMap.get(scrnMsg.A.no(i).dsContrDtlPk_A.getValue()) != null 
                    && billedMap.get(scrnMsg.A.no(i).dsContrDtlPk_A.getValue())) {

                scrnMsg.A.no(i).contrEffFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).contrEffThruDt_A.setInputProtected(true);
                scrnMsg.A.no(i).baseBllgCycleCd_A.setInputProtected(true);
                scrnMsg.A.no(i).basePrcDealAmt_A.setInputProtected(true);
            }
        }
    }
    // END 2017/09/13 K.Kim [QC#19938, ADD]

    // START 2017/07/05 K.Kim [QC#18110, DEL]
//    private static void protectItemForAccessory(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
//        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//            if (DS_CONTR_DTL_TP.ACCESSORIES.equals(scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
//                scrnMsg.B.no(i).contrEffFromDt_B.setInputProtected(true);
//                scrnMsg.B.no(i).contrEffThruDt_B.setInputProtected(true);
//            }
//        }
//    }
    // END 2017/07/05 K.Kim [QC#18110, DEL]

    // START 2016/05/10 T.Kanasaka [QC#6798, ADD]
    private static void protectItemForRenewUplift(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwBaseFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwUsgFlg.getValue())) {
            scrnMsg.befEndRnwDaysAot.setInputProtected(true);
            scrnMsg.rnwPrcMethCd.setInputProtected(true);
            scrnMsg.basePrcUpRatio.setInputProtected(true);
            scrnMsg.mtrPrcUpRatio.setInputProtected(true);
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.rnwPrcMethCd) || RNW_PRC_METH.MODEL_PERCENT.equals(scrnMsg.rnwPrcMethCd.getValue())) {
            scrnMsg.basePrcUpRatio.setInputProtected(true);
            scrnMsg.mtrPrcUpRatio.setInputProtected(true);
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwBaseFlg.getValue())) {
            scrnMsg.basePrcUpRatio.setInputProtected(true);
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.rnwUsgFlg.getValue())) {
            scrnMsg.mtrPrcUpRatio.setInputProtected(true);
        }

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg.getValue())) {
            scrnMsg.uplftPrcMethCd.setInputProtected(true);
            scrnMsg.uplftBasePrcUpRatio.setInputProtected(true);
            scrnMsg.uplftMtrPrcUpRatio.setInputProtected(true);
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.uplftPrcMethCd) || UPLFT_PRC_METH.MODEL_PERCENT.equals(scrnMsg.uplftPrcMethCd.getValue())) {
            scrnMsg.uplftBasePrcUpRatio.setInputProtected(true);
            scrnMsg.uplftMtrPrcUpRatio.setInputProtected(true);
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftBaseFlg.getValue())) {
            scrnMsg.uplftBasePrcUpRatio.setInputProtected(true);
        } else if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.uplftUsgFlg.getValue())) {
            scrnMsg.uplftMtrPrcUpRatio.setInputProtected(true);
        }
    }
    // END 2016/05/10 T.Kanasaka [QC#6798, ADD]

    // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
    private static void protectItemForPerEndDay(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
        List<BigDecimal> mtrProtectedDsContrDtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).bllgCycleStartMth_BB)) {
                scrnMsg.B.no(i).baseDplyPerEndDay_B.setInputProtected(true);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).bllgCycleStartMth_BU)) {
                if (!mtrProtectedDsContrDtlPkList.contains(scrnMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                    mtrProtectedDsContrDtlPkList.add(scrnMsg.B.no(i).dsContrDtlPk_B.getValue());
                }
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsContrDtlPk_B) && mtrProtectedDsContrDtlPkList.contains(scrnMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                scrnMsg.B.no(i).mtrDplyPerEndDay_B.setInputProtected(true);
            }
        }
    }
    // END 2016/05/17 T.Kanasaka [QC#2184, ADD]

    private static void protectItemBasedOnContractStatus(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {

        String dsContrStsCd = scrnMsg.dsContrStsCd.getValue();

        if (ZYPCommonFunc.hasValue(dsContrStsCd)) {
            scrnMsg.dsContrNum.setInputProtected(true);
            scrnMsg.dsContrCatgCd.setInputProtected(true);

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B2.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_BASE, i, false);
                }
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B3.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_BASE, i, false);
                }
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B4.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_METER, i, false);
                }
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_B5.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_USAGE, i, false);
                }
                // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_BA.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_SPECIAL_INSTRUCTION_BASE, i, false);
                }
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_BB.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_SPECIAL_INSTRUCTION_METER, i, false);
                }
                // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            }

            if (DISPLAY_MODE_FREEZE.equals(scrnMsg.xxModeCd_FU.getValue())) {
                handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);

                // START 2024/03/22 Y.Tamai [QC#63463, ADD]
                handler.setButtonEnabled(BTN_REVERT, false);
                // END 2024/03/22 Y.Tamai [QC#63463, ADD]
                handler.setButtonEnabled(BTN_OPENWIN_BRANCH, false);
                scrnMsg.svcLineBizCd.setInputProtected(true);
                scrnMsg.svcContrBrCd.setInputProtected(true);
                scrnMsg.contrAdminPsnCd.setInputProtected(true);
                scrnMsg.xxPsnNm.setInputProtected(true);
                scrnMsg.xxDplyByCdNmCnctTxt.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_REP, false);
                scrnMsg.dsContrCatgCd.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_REPORT_GRP, false);
                // START 2017/01/27 [QC#17278, MOD]
                // scrnMsg.dsContrRptGrpNum.setInputProtected(true);
                scrnMsg.dsContrRptGrpDescTxt.setInputProtected(true);
                // END   2017/01/27 [QC#17278, MOD]
                handler.setButtonEnabled(BTN_OPENWIN_NEW_REPORT_GRP, false);
                scrnMsg.dsContrNm.setInputProtected(true);
                scrnMsg.dsContrClsCd.setInputProtected(true);
                scrnMsg.dsContrEdiCd.setInputProtected(true);

                scrnMsg.contrInvCmntTxt.setInputProtected(true);
                // add start 2018/11/07 K.Fujimoto QC#28627
                scrnMsg.contrLinkNum.setInputProtected(true);
                // add end   2018/11/07 K.Fujimoto QC#28627
                scrnMsg.svcContrRefCmntTxt.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_SALES_REP, false);
                scrnMsg.tocCd.setInputProtected(true);
                scrnMsg.tocNm.setInputProtected(true);
                //
                handler.setButtonEnabled(BTN_OPENWIN_CUSTOMER, false);
                scrnMsg.dsAcctNum.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_LOC, false);
                scrnMsg.altPayerCustCd.setInputProtected(true);
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_CONTACT, false);
//                scrnMsg.xxPsnNm_CP.setInputProtected(true);
                scrnMsg.ctacPsnFirstNm_PL.setInputProtected(true);
                scrnMsg.ctacPsnFirstNm_PL.clear();;
                scrnMsg.ctacPsnFirstNm_CP.setInputProtected(true);
                scrnMsg.ctacPsnLastNm_CP.setInputProtected(true);
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                handler.setButtonEnabled(BTN_OPENWIN_LEASECOMPANY, false);
                scrnMsg.leaseCmpyCd.setInputProtected(true);
                scrnMsg.baseChrgToLeaseCmpyFlg.setInputProtected(true);
                scrnMsg.usgChrgToLeaseCmpyFlg.setInputProtected(true);
                // START 2018/05/15 K.Kitachi [QC#24265, ADD]
                scrnMsg.cfsLeaseNumCmntTxt.setInputProtected(true);
                // END 2018/05/15 K.Kitachi [QC#24265, ADD]
                scrnMsg.custPoNum.setInputProtected(true);
                // START 2019/01/09 K.Kitachi [QC#26928, ADD]
                scrnMsg.poFromDt.setInputProtected(true);
                // END 2019/01/09 K.Kitachi [QC#26928, ADD]
                scrnMsg.poDt.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_CREDIT_CARD, false);
                scrnMsg.crCardCustRefNum.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_PAYMENT_TERM, false);
                scrnMsg.pmtTermCashDiscCd.setInputProtected(true);
                //
                scrnMsg.contrVrsnEffFromDt.setInputProtected(true);
                scrnMsg.contrDurnAot.setInputProtected(true);
                scrnMsg.contrVrsnEffThruDt.setInputProtected(true);
                scrnMsg.bllgCycleUomCd.setInputProtected(true);
                scrnMsg.mtrEstTpCd.setInputProtected(true);
                // START 2022/03/22 [QC#59683, DEL]
//                scrnMsg.xxSelFlg_UT.setInputProtected(true);
                // END   2022/03/22 [QC#59683, DEL]
                scrnMsg.prcAllocByMachQtyFlg.setInputProtected(true);
                scrnMsg.svcPgmMdseCd.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM, false);
                scrnMsg.mdseDescShortTxt_SP.setInputProtected(true);
                scrnMsg.baseBllgCycleCd.setInputProtected(true);
                scrnMsg.mtrBllgCycleCd.setInputProtected(true);
                // START 2022/03/22 [QC#59683, ADD]
                scrnMsg.dsInvTgtrTpCd.setInputProtected(true);
                // END   2022/03/22 [QC#59683, ADD]
                //
                scrnMsg.contrAutoRnwTpCd.setInputProtected(true);
                scrnMsg.befEndRnwDaysAot.setInputProtected(true);
                scrnMsg.rnwPrcMethCd.setInputProtected(true);
                scrnMsg.basePrcUpRatio.setInputProtected(true);
                scrnMsg.mtrPrcUpRatio.setInputProtected(true);
                //
                scrnMsg.contrUplftTpCd.setInputProtected(true);
                scrnMsg.uplftPrcMethCd.setInputProtected(true);
                scrnMsg.uplftBasePrcUpRatio.setInputProtected(true);
                scrnMsg.uplftMtrPrcUpRatio.setInputProtected(true);
                //
                scrnMsg.xxFileData.setInputProtected(true);
                scrnMsg.xxLinkProt.setInputProtected(true);
//                scrnMsg.xxLinkProt_UP.setInputProtected(true);
                handler.setButtonEnabled(BTN_OPENWIN_SERIAL, false);
                scrnMsg.serNum.setInputProtected(true);
                scrnMsg.svcMachMstrPk.setInputProtected(true);
                handler.setButtonEnabled(BTN_ADD_DETAIL, false);
                handler.setButtonEnabled(BTN_OPENWIN_ADD_DETAIL, false);
                scrnMsg.A.setInputProtected(true);
                scrnMsg.xxLinkProt_DL.setInputProtected(true);
                handler.setButtonEnabled(BTN_UPLOAD, false);
                //
                scrnMsg.B.setInputProtected(true);
                //
                handler.setButtonEnabled(BTN_OPENWIN_COMPLETECONTRACT, false);

                // START 2018/07/23 K.Kim [QC#26831, DEL]
//                if (DS_CONTR_CTRL_STS.HOLD.equals(scrnMsg.dsContrCtrlStsCd.getValue()) || DS_CONTR_CTRL_STS.BILLING_HOLD.equals(scrnMsg.dsContrCtrlStsCd.getValue())) {
//                    // START 2017/07/26 M.Kidokoro [QC#18122, DEL]
////                    handler.setButtonEnabled(BTN_OPENWIN_TERMINATE, false);
//                    // END 2017/07/26 M.Kidokoro [QC#18122, DEL]
//                    handler.setButtonEnabled(BTN_OPENWIN_RENEW, false);
//                } else if (DS_CONTR_CTRL_STS.EXPIRED.equals(scrnMsg.dsContrCtrlStsCd.getValue()) || DS_CONTR_CTRL_STS.TERMINATED.equals(scrnMsg.dsContrCtrlStsCd.getValue()) || DS_CONTR_CTRL_STS.CANCELLED.equals(scrnMsg.dsContrCtrlStsCd.getValue())) {
//                    // START 2017/07/26 M.Kidokoro [QC#18122, DEL]
////                    handler.setButtonEnabled(BTN_OPENWIN_TERMINATE, false);
//                    // END 2017/07/26 M.Kidokoro [QC#18122, DEL]
//                    handler.setButtonEnabled(BTN_OPENWIN_RENEW, false);
//                    handler.setButtonEnabled(BTN_OPENWIN_STATUS_CHANGE, false);
//                } else {
//                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_RN.getValue())) {
//                        handler.setButtonEnabled(BTN_OPENWIN_RENEW, true);
//                    } else {
//                        handler.setButtonEnabled(BTN_OPENWIN_RENEW, false);
//                    }
//                }
                // END 2018/07/23 K.Kim [QC#26831, DEL]

                // START 2018/07/23 K.Kim [QC#26831, ADD]
                if (DS_CONTR_CTRL_STS.EXPIRED.equals(scrnMsg.dsContrCtrlStsCd.getValue()) || DS_CONTR_CTRL_STS.TERMINATED.equals(scrnMsg.dsContrCtrlStsCd.getValue()) || DS_CONTR_CTRL_STS.CANCELLED.equals(scrnMsg.dsContrCtrlStsCd.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_STATUS_CHANGE, false);
                }
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.contrRnwAvalFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_RN.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_RENEW, true);
                } else {
                    handler.setButtonEnabled(BTN_OPENWIN_RENEW, false);
                }
                // END 2018/07/23 K.Kim [QC#26831, ADD]
                if (DS_CONTR_CTRL_STS.CANCELLED.equals(scrnMsg.dsContrCtrlStsCd.getValue())) {
                    handler.setButtonEnabled(BTN_OPEN_FOR_UPDATE, false);
                }
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                    scrnMsg.A.no(i).xxLinkProt_A.setInputProtected(false);
                    // Add Start 2017/11/06 QC#18552
                    scrnMsg.A.no(i).svcMachMstrPk_A.setInputProtected(false);
                    scrnMsg.A.no(i).mdlNm_A.setInputProtected(false);
                    // Add End 2017/11/06 QC#18552
                }
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    scrnMsg.B.no(i).xxLinkProt_BB.setInputProtected(false);
                    scrnMsg.B.no(i).xxLinkProt_BM.setInputProtected(false);
                    scrnMsg.B.no(i).xxLinkProt_BC.setInputProtected(false);
                    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
                    handler.setButtonEnabled(BTN_OPENWIN_SHIP_TO, i, false);
                    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
                    handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_BASE, i, false);
                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                    handler.setButtonEnabled(BTN_OPENWIN_CONTACT_BASE, i, false);
                    scrnMsg.B.no(i).ctacPsnFirstNm_BL.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_BL.clear();
                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM_BASE, i, false);
                    handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_METER, i, false);
                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                    handler.setButtonEnabled(BTN_OPENWIN_CONTACT_METER, i, false);
                    scrnMsg.B.no(i).ctacPsnFirstNm_ML.setInputProtected(true);
                    scrnMsg.B.no(i).ctacPsnFirstNm_ML.clear();
                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    // START 2018/04/26 K.Kojima [QC#23630,ADD]
                    // START 2018/06/05 K.Kojima [QC#21974,MOD]
                    // if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
                    //     handler.setButtonEnabled(BTN_OPENWIN_FREECOPY_ROLLOVER_HISTORY, i, false);
                    // }
                    if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
                        if (!DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                            handler.setButtonEnabled(BTN_OPENWIN_FREECOPY_ROLLOVER_HISTORY, i, false);
                        }
                    }
                    // END 2018/06/05 K.Kojima [QC#21974,MOD]
                    // END 2018/04/26 K.Kojima [QC#23630,ADD]
                }
            } else {
                // START 2017/07/26 M.Kidokoro [QC#18122, DEL]
//                handler.setButtonEnabled(BTN_OPENWIN_TERMINATE, false);
                // END 2017/07/26 M.Kidokoro [QC#18122, DEL]
                handler.setButtonEnabled(BTN_OPENWIN_RENEW, false);
                handler.setButtonEnabled(BTN_OPEN_FOR_UPDATE, false);
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                if (DS_CONTR_CTRL_STS.DRAFT.equals(scrnMsg.dsContrCtrlStsCd.getValue()) || DS_CONTR_CTRL_STS.QA_HOLD.equals(scrnMsg.dsContrCtrlStsCd.getValue())) {
                String dsContrCtrlStsCd = scrnMsg.dsContrCtrlStsCd.getValue();
                // START 2017/08/31 K.Kojima [QC#20817,MOD]
                // if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd)) {
                if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd)) {
                // END 2017/08/31 K.Kojima [QC#20817,MOD]
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                    handler.setButtonEnabled(BTN_OPENWIN_STATUS_CHANGE, false);
                }
                // START 2024/03/22 Y.Tamai [QC#63463, ADD]
                if (!DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd)) {
                    handler.setButtonEnabled(BTN_REVERT, false);
                }
                // END 2024/03/22 Y.Tamai [QC#63463, ADD]
                // START 2018/04/26 K.Kojima [QC#23630,ADD]
                if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
                    for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                        // START 2018/06/05 K.Kojima [QC#21974,MOD]
                        // handler.setButtonEnabled(BTN_OPENWIN_FREECOPY_ROLLOVER_HISTORY, i, false);
                        if (!DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                            handler.setButtonEnabled(BTN_OPENWIN_FREECOPY_ROLLOVER_HISTORY, i, false);
                        }
                        // END 2018/06/05 K.Kojima [QC#21974,MOD]
                    }
                } else if (DS_CONTR_CTRL_STS.DRAFT.equals(scrnMsg.dsContrCtrlStsCd.getValue()) || DS_CONTR_CTRL_STS.ENTERED.equals(scrnMsg.dsContrCtrlStsCd.getValue())) {
                    for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                        handler.setButtonEnabled(BTN_OPENWIN_FREECOPY_ROLLOVER_HISTORY, i, false);
                    }
                }
                // END 2018/04/26 K.Kojima [QC#23630,ADD]
            }
            // START 2017/07/26 M.Kidokoro [QC#18122, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.contrTrmnAvalFlg.getValue())) {
                handler.setButtonEnabled(BTN_OPENWIN_TERMINATE, false);
            }
            // END 2017/07/26 M.Kidokoro [QC#18122, ADD]
            // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_SI.getValue())) {
                handler.setButtonEnabled(BTN_OPENWIN_SPECIAL_INSTRUCTION, false);
            }
            // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
        } else {
            handler.setButtonEnabled(BTN_OPENWIN_TERMINATE, false);
            handler.setButtonEnabled(BTN_OPENWIN_RENEW, false);
            handler.setButtonEnabled(BTN_OPENWIN_STATUS_CHANGE, false);
            handler.setButtonEnabled(BTN_OPEN_FOR_UPDATE, false);
            // START 2024/03/22 Y.Tamai [QC#63463, ADD]
            handler.setButtonEnabled(BTN_REVERT, false);
            // END 2024/03/22 Y.Tamai [QC#63463, ADD]
            // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_SI.getValue())) {
                handler.setButtonEnabled(BTN_OPENWIN_SPECIAL_INSTRUCTION, false);
            }
            // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            //
            scrnMsg.contrAutoRnwTpCd.setInputProtected(true);
            scrnMsg.befEndRnwDaysAot.setInputProtected(true);
            scrnMsg.rnwPrcMethCd.setInputProtected(true);
            scrnMsg.basePrcUpRatio.setInputProtected(true);
            scrnMsg.mtrPrcUpRatio.setInputProtected(true);
            //
            scrnMsg.contrUplftTpCd.setInputProtected(true);
            scrnMsg.uplftPrcMethCd.setInputProtected(true);
            scrnMsg.uplftBasePrcUpRatio.setInputProtected(true);
            scrnMsg.uplftMtrPrcUpRatio.setInputProtected(true);
            //
            scrnMsg.xxFileData.setInputProtected(true);
            scrnMsg.A.setInputProtected(true);
            scrnMsg.xxLinkProt_DL.setInputProtected(true);
            handler.setButtonEnabled(BTN_OPENWIN_TERMS, false);
            handler.setButtonEnabled(BTN_OPENWIN_SUB_CONTRACT, false);
            //
            scrnMsg.B.setInputProtected(true);
            //
            handler.setButtonEnabled(BTN_OPENWIN_CONTRACT_STATUS_HISTORY, false);
            handler.setButtonEnabled(BTN_OPENWIN_ADD_NOTES, false);
            handler.setButtonEnabled(BTN_OPENWIN_COMPLETECONTRACT, false);
            handler.setButtonEnabled(BTN_GO, false);
            // Add Start 2018/01/14 QC#18552
            handler.setButtonEnabled(BTN_OPENWIN_CREDITCARDPO, false);
            handler.setButtonEnabled(BTN_OPENWIN_UPLIFTDETAIL, false);
            handler.setButtonEnabled(BTN_OPENWIN_ESCALATION, false);
            // Add End 2018/01/14 QC#18552
            // mod start 2016/10/26 CSA QC#3581
            handler.setButtonEnabled(BTN_OPENWIN_ATTACHMENTS, false);
            // mod end 2016/10/26 CSA QC#3581

            scrnMsg.dsContrActCd.setInputProtected(true);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
    }

    private static void protectItemBasedOnPrivilege(S21CommonHandler handler, List<String> functionList, NSAL0300BMsg scrnMsg) {
        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

            scrnMsg.dsContrNum.setInputProtected(true);

            // mod start 2017/01/11 CSA QC#16792
            protectButton(handler, scrnMsg, functionList, false);
            // mod end 2017/01/11 CSA QC#16792
        } else if (functionList.contains(FUNC_ID_UPD)) {
            // START 2018/07/27 [QC#24795, ADD]
            if (!DISPLAY_MODE_FREEZE.equals(scrnMsg.xxModeCd_FU.getValue())) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    scrnMsg.B.no(i).shipToCustCd_B.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPENWIN_SHIP_TO, i, true);
                }
            }
            // END   2018/07/27 [QC#24795, ADD]
        // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
        } else if (functionList.contains(FUNC_ID_SHIP_TO)) {
            // Only Ship To Update
            boolean shipToCustCdEnabled = false;
            // START 2018/07/27 [QC#24795, MOD]
            if (scrnMsg.B.getValidCount() > 0 && !DISPLAY_MODE_FREEZE.equals(scrnMsg.xxModeCd_FU.getValue())) {
                shipToCustCdEnabled = true;
            }
            // END   2018/07/27 [QC#24795, MOD]
            // mod start 2017/01/11 CSA QC#16792
            protectButton(handler, scrnMsg, functionList, false);
            // mod end 2017/01/11 CSA QC#16792
            if (!DISPLAY_MODE_FREEZE.equals(scrnMsg.xxModeCd_FU.getValue())) {
                scrnMsg.svcLineBizCd.setInputProtected(true);
                scrnMsg.svcContrBrCd.setInputProtected(true);
                scrnMsg.contrAdminPsnCd.setInputProtected(true);
                scrnMsg.xxPsnNm.setInputProtected(true);
                scrnMsg.xxDplyByCdNmCnctTxt.setInputProtected(true);
                scrnMsg.dsContrCatgCd.setInputProtected(true);
                // START 2017/01/27 [QC#17278, MOD]
                // scrnMsg.dsContrRptGrpNum.setInputProtected(true);
                scrnMsg.dsContrRptGrpDescTxt.setInputProtected(true);
                // END   2017/01/27 [QC#17278, MOD]
                scrnMsg.dsContrNm.setInputProtected(true);
                scrnMsg.dsContrClsCd.setInputProtected(true);
                scrnMsg.dsContrEdiCd.setInputProtected(true);

                scrnMsg.contrInvCmntTxt.setInputProtected(true);
                scrnMsg.svcContrRefCmntTxt.setInputProtected(true);
                scrnMsg.tocCd.setInputProtected(true);
                scrnMsg.tocNm.setInputProtected(true);
                //
                scrnMsg.dsAcctNum.setInputProtected(true);
                scrnMsg.altPayerCustCd.setInputProtected(true);
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                scrnMsg.xxPsnNm_CP.setInputProtected(true);
                scrnMsg.ctacPsnFirstNm_PL.setInputProtected(true);
                scrnMsg.ctacPsnFirstNm_PL.clear();
                scrnMsg.ctacPsnFirstNm_CP.setInputProtected(true);
                scrnMsg.ctacPsnLastNm_CP.setInputProtected(true);
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                scrnMsg.leaseCmpyCd.setInputProtected(true);
                scrnMsg.baseChrgToLeaseCmpyFlg.setInputProtected(true);
                scrnMsg.usgChrgToLeaseCmpyFlg.setInputProtected(true);
                // START 2018/05/15 K.Kitachi [QC#24265, ADD]
                scrnMsg.cfsLeaseNumCmntTxt.setInputProtected(true);
                // END 2018/05/15 K.Kitachi [QC#24265, ADD]
                scrnMsg.custPoNum.setInputProtected(true);
                // START 2019/01/09 K.Kitachi [QC#26928, ADD]
                scrnMsg.poFromDt.setInputProtected(true);
                // END 2019/01/09 K.Kitachi [QC#26928, ADD]
                scrnMsg.poDt.setInputProtected(true);
                scrnMsg.crCardCustRefNum.setInputProtected(true);
                scrnMsg.pmtTermCashDiscCd.setInputProtected(true);
                //
                scrnMsg.contrVrsnEffFromDt.setInputProtected(true);
                scrnMsg.contrDurnAot.setInputProtected(true);
                scrnMsg.contrVrsnEffThruDt.setInputProtected(true);
                scrnMsg.bllgCycleUomCd.setInputProtected(true);
                scrnMsg.mtrEstTpCd.setInputProtected(true);
                // START 2022/03/22 [QC#59683, DEL]
//                scrnMsg.xxSelFlg_UT.setInputProtected(true);
                // END   2022/03/22 [QC#59683, DEL]
                // START 2022/03/22 [QC#59683, ADD]
                scrnMsg.dsInvTgtrTpCd.setInputProtected(true);
                // END   2022/03/22 [QC#59683, ADD]
                scrnMsg.prcAllocByMachQtyFlg.setInputProtected(true);
                scrnMsg.svcPgmMdseCd.setInputProtected(true);
                scrnMsg.mdseDescShortTxt_SP.setInputProtected(true);
                scrnMsg.baseBllgCycleCd.setInputProtected(true);
                scrnMsg.mtrBllgCycleCd.setInputProtected(true);
                //
                scrnMsg.contrAutoRnwTpCd.setInputProtected(true);
                scrnMsg.befEndRnwDaysAot.setInputProtected(true);
                scrnMsg.rnwPrcMethCd.setInputProtected(true);
                scrnMsg.basePrcUpRatio.setInputProtected(true);
                scrnMsg.mtrPrcUpRatio.setInputProtected(true);
                //
                scrnMsg.contrUplftTpCd.setInputProtected(true);
                scrnMsg.uplftPrcMethCd.setInputProtected(true);
                scrnMsg.uplftBasePrcUpRatio.setInputProtected(true);
                scrnMsg.uplftMtrPrcUpRatio.setInputProtected(true);
                //
                scrnMsg.xxFileData.setInputProtected(true);
                scrnMsg.xxLinkProt.setInputProtected(true);
                scrnMsg.serNum.setInputProtected(true);
                scrnMsg.svcMachMstrPk.setInputProtected(true);
                scrnMsg.A.setInputProtected(true);
                scrnMsg.xxLinkProt_DL.setInputProtected(true);
                //
                scrnMsg.B.setInputProtected(true);
                //

                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).xxLinkProt_A.setInputProtected(false);
                }
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    scrnMsg.B.no(i).xxLinkProt_BB.setInputProtected(false);
                    scrnMsg.B.no(i).xxLinkProt_BM.setInputProtected(false);
                    scrnMsg.B.no(i).xxLinkProt_BC.setInputProtected(false);
                    handler.setButtonEnabled(BTN_OPENWIN_SHIP_TO, i, true);
                }
            }

            if (shipToCustCdEnabled) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    scrnMsg.B.no(i).shipToCustCd_B.setInputProtected(false);
                    // START 2018/07/27 [QC#24795, ADD]
                    handler.setButtonEnabled(BTN_OPENWIN_SHIP_TO, i, true);
                    // END   2018/07/27 [QC#24795, ADD]
                }
            }
        // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
        } else if (functionList.contains(FUNC_ID_INQ)) {
            // Inquiry
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            // mod start 2017/01/11 CSA QC#16792
            protectButton(handler, scrnMsg, functionList, true);
            // mod end 2017/01/11 CSA QC#16792
        }
    }

    // START 2016/02/26 T.Kanasaka [QC4092, MOD]
    // mod start 2017/01/11 CSA QC#16792
    private static void protectButton(S21CommonHandler handler, NSAL0300BMsg scrnMsg, List<String> functionList, boolean funcIdInq) {

        if (!funcIdInq || (funcIdInq && !ZYPCommonFunc.hasValue(scrnMsg.dsContrStsCd))) {
            handler.setButtonEnabled(BTN_OPENWIN_CONTRACT_STATUS_HISTORY, false);
            handler.setButtonEnabled(BTN_OPENWIN_ADD_NOTES, false);
            handler.setButtonEnabled(BTN_OPENWIN_TERMS, false);
            handler.setButtonEnabled(BTN_OPENWIN_SUB_CONTRACT, false);
            handler.setButtonEnabled(BTN_GO, false);
            // Add Start 2018/01/14 QC#18552
            handler.setButtonEnabled(BTN_OPENWIN_CREDITCARDPO, false);
            handler.setButtonEnabled(BTN_OPENWIN_UPLIFTDETAIL, false);
            handler.setButtonEnabled(BTN_OPENWIN_ESCALATION, false);
            // Add End 2018/01/14 QC#18552
            // mod start 2016/10/26 CSA QC#3581
            handler.setButtonEnabled(BTN_OPENWIN_ATTACHMENTS, false);
            // mod end 2016/10/26 CSA QC#3581
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                handler.setButtonEnabled(BTN_OPENWIN_ADDITIONALCHARGE, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_BASE, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_BASE, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_BILLING_COUNTERS, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_PRICING_EFFECTIVITY_METER, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_SCHEDULE_USAGE, i, false);
                // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_BA.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_SPECIAL_INSTRUCTION_BASE, i, false);
                }
                if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxDplyCtrlFlg_BB.getValue())) {
                    handler.setButtonEnabled(BTN_OPENWIN_SPECIAL_INSTRUCTION_METER, i, false);
                }
                // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            }
        }
        // mod end 2017/01/11 CSA QC#16792
        handler.setButtonEnabled(BTN_OPENWIN_TERMINATE, false);
        handler.setButtonEnabled(BTN_OPENWIN_RENEW, false);
        handler.setButtonEnabled(BTN_OPENWIN_STATUS_CHANGE, false);
        handler.setButtonEnabled(BTN_OPEN_FOR_UPDATE, false);
        // START 2024/03/22 Y.Tamai [QC#63463, ADD]
        handler.setButtonEnabled(BTN_REVERT, false);
        // END 2024/03/22 Y.Tamai [QC#63463, ADD]
        handler.setButtonEnabled(BTN_OPENWIN_BRANCH, false);
        handler.setButtonEnabled(BTN_OPENWIN_REP, false);
        handler.setButtonEnabled(BTN_OPENWIN_SALES_REP, false);
        handler.setButtonEnabled(BTN_OPENWIN_REPORT_GRP, false);
        handler.setButtonEnabled(BTN_OPENWIN_NEW_REPORT_GRP, false);
        handler.setButtonEnabled(BTN_OPENWIN_CUSTOMER, false);
        handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_LOC, false);
        // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_SI.getValue())) {
            handler.setButtonEnabled(BTN_OPENWIN_SPECIAL_INSTRUCTION, false);
        }
        // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
        // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//        handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_CONTACT, false);
        // END 2018/06/18 K.Kitachi [QC#18591, DEL]
        handler.setButtonEnabled(BTN_OPENWIN_LEASECOMPANY, false);
        handler.setButtonEnabled(BTN_OPENWIN_CREDIT_CARD, false);
        handler.setButtonEnabled(BTN_OPENWIN_PAYMENT_TERM, false);
        handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM, false);
        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonEnabled(BTN_OPENWIN_CONTRACT_NUM, false);
            handler.setButtonEnabled(BTN_DOWNLOAD_MACHINE, false);
            handler.setButtonEnabled(BTN_FILTER, false);
        }
        handler.setButtonEnabled(BTN_OPENWIN_SERIAL, false);
        handler.setButtonEnabled(BTN_ADD_DETAIL, false);
        handler.setButtonEnabled(BTN_OPENWIN_ADD_DETAIL, false);
        // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
        handler.setButtonEnabled(BTN_UPLOAD, false);
        // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            handler.setButtonEnabled(BTN_OPENWIN_SHIP_TO, i, false);
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
            handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_BASE, i, false);
            // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//            handler.setButtonEnabled(BTN_OPENWIN_CONTACT_BASE, i, false);
            // END 2018/06/18 K.Kitachi [QC#18591, DEL]
            handler.setButtonEnabled(BTN_OPENWIN_SERVICE_PROGRAM_BASE, i, false);
            handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_METER, i, false);
            // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//            handler.setButtonEnabled(BTN_OPENWIN_CONTACT_METER, i, false);
            // END 2018/06/18 K.Kitachi [QC#18591, DEL]
            // START 2018/04/26 K.Kojima [QC#23630,ADD]
            handler.setButtonEnabled(BTN_OPENWIN_FREECOPY_ROLLOVER_HISTORY, i, false);
            // END 2018/04/26 K.Kojima [QC#23630,ADD]
        }

        handler.setButtonEnabled(BTN_OPENWIN_COMPLETECONTRACT, false);
    }
    // END 2016/02/26 T.Kanasaka [QC4092, MOD]

    // Add Start 12/20/2016 <QC#16647>
    private static void protectNoMtrLines(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dsContrBllgMtrPk_B)) {
                handler.setButtonEnabled(BTN_OPENWIN_BILL_TO_METER, i, false);
                // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//                handler.setButtonEnabled(BTN_OPENWIN_CONTACT_METER, i, false);
                // END 2018/06/18 K.Kitachi [QC#18591, DEL]
                scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.setInputProtected(true);
                scrnMsg.B.no(i).xsChrgTpCd_B.setInputProtected(true);
                scrnMsg.B.no(i).xsMtrCopyQty_B.setInputProtected(true);
                scrnMsg.B.no(i).xsMtrAmtRate_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgMtrBillToCustCd_B.setInputProtected(true);
                scrnMsg.B.no(i).billToLocNm_BM.setInputProtected(true);
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                scrnMsg.B.no(i).xxPsnNm_BM.setInputProtected(true);
                scrnMsg.B.no(i).ctacPsnFirstNm_ML.setInputProtected(true);
                scrnMsg.B.no(i).ctacPsnFirstNm_ML.clear();
                scrnMsg.B.no(i).ctacPsnFirstNm_BM.setInputProtected(true);
                scrnMsg.B.no(i).ctacPsnLastNm_BM.setInputProtected(true);
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                scrnMsg.B.no(i).bllgMinCnt_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgMinAmtRate_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgRollOverRatio_B.setInputProtected(true);
                scrnMsg.B.no(i).bllgFreeCopyCnt_B.setInputProtected(true);
                // START 2019/01/17 M.Naito [QC#29083,ADD]
                scrnMsg.B.no(i).cumCopyCnt_B.setInputProtected(true);
                scrnMsg.B.no(i).cumCopyFreqMthAot_B.setInputProtected(true);
                scrnMsg.B.no(i).cumCopyStartDt_B.setInputProtected(true);
                scrnMsg.B.no(i).cumCopyEndDt_B.setInputProtected(true);
                // END 2019/01/17 M.Naito [QC#29083,ADD]
            }
        }
    }
    // Add End   12/20/2016 <QC#16647>

    /**
     * Set fraction digit
     * @param scrnMsg NSAL0300BMsg
     */
    private static void setAppFracDigit(NSAL0300BMsg scrnMsg) {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        int digit = DEF_FRAC_DIGIT_NUM;
        if (ZYPCommonFunc.hasValue(scrnMsg.ccyCd)) {
            String ccyCd = scrnMsg.ccyCd.getValue();
            CCYTMsg dealCcyTMsg = (CCYTMsg) ZYPCodeDataUtil.findByCode(CCY.class, glblCmpyCd, ccyCd);
            if (dealCcyTMsg != null) {
                digit = dealCcyTMsg.aftDeclPntDigitNum.getValueInt();
                EZDDebugOutput.println(3, String.format("glblCmpyCd=%1$s, dealCcyCd=%2$s, aftDeclPntDigitNum=%3$d", glblCmpyCd, ccyCd, digit), NSAL0300CommonLogic.class);
            }
        }

        // START 2018/05/30 M.Naito [QC#22887, MOD]
//        scrnMsg.basePrcUpRatio.setAppFracDigit(0);
//        scrnMsg.mtrPrcUpRatio.setAppFracDigit(0);
//        scrnMsg.uplftBasePrcUpRatio.setAppFracDigit(0);
//        scrnMsg.uplftMtrPrcUpRatio.setAppFracDigit(0);
        scrnMsg.basePrcUpRatio.setAppFracDigit(2);
        scrnMsg.mtrPrcUpRatio.setAppFracDigit(2);
        scrnMsg.uplftBasePrcUpRatio.setAppFracDigit(2);
        scrnMsg.uplftMtrPrcUpRatio.setAppFracDigit(2);
        // END 2018/05/30 M.Naito [QC#22887, MOD]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).basePrcDealAmt_A.setAppFracDigit(digit);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).basePrcDealAmt_B.setAppFracDigit(digit);
            scrnMsg.B.no(i).basePrcTermDealAmtRate_B.setAppFracDigit(digit);
            scrnMsg.B.no(i).bllgMinCnt_B.setAppFracDigit(0);
            scrnMsg.B.no(i).bllgMinAmtRate_B.setAppFracDigit(digit);
            scrnMsg.B.no(i).bllgRollOverRatio_B.setAppFracDigit(0);
            scrnMsg.B.no(i).bllgFreeCopyCnt_B.setAppFracDigit(0);
            scrnMsg.B.no(i).contrMtrMultRate_B.setAppFracDigit(2);
            // START 2019/01/17 M.Naito [QC#29083,ADD]
            scrnMsg.B.no(i).cumCopyCnt_B.setAppFracDigit(0);
            // END 2019/01/17 M.Naito [QC#29083,ADD]
        }
    }

    private static void alternateTableRowColor(NSAL0300BMsg scrnMsg) {
//        S21TableColorController control = new S21TableColorController(SCR_ID_00, scrnMsg);
//        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    public static boolean isEqualStr(String a, String b) {
        if (!ZYPCommonFunc.hasValue(a) && !ZYPCommonFunc.hasValue(b)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(a) && ZYPCommonFunc.hasValue(b) && ZYPCommonFunc.trimTail(a).equals(ZYPCommonFunc.trimTail(b))) {
            return true;
        }
        return false;
    }

    public static boolean isEqualMach(String serNum1, String mdseCd1, String serNum2, String mdseCd2) {
        if (isEqualStr(serNum1, serNum2) && isEqualStr(mdseCd1, mdseCd2)) {
            return true;
        }
        return false;
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    public static void focusSerNum(NSAL0300BMsg scrnMsg, int a) {
        if (a > 0) {
            String aSerNum = scrnMsg.A.no(a).serNum_A.getValue();
            for (int b = 0; b < scrnMsg.B.getValidCount(); b++) {
                String bSerNum = scrnMsg.B.no(b).serNum_B.getValue();
                if (isEqualStr(aSerNum, bSerNum)) {
                    scrnMsg.setFocusItem(scrnMsg.B.no(b).contrEffFromDt_B);
                    return;
                }
            }
        } else {
            if (scrnMsg.B.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.B.no(0).contrEffFromDt_B);
            }
        }
    }

    public static void clearPopupParam(NSAL0300BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        // START 2016/01/07 T.Tomita [QC#1029, ADD]
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        // END 2016/01/07 T.Tomita [QC#1029, ADD]
        scrnMsg.ctacPsnPk_15.clear();
        scrnMsg.ctacPsnPk_16.clear();
        scrnMsg.ctacPsnPk_17.clear();
        scrnMsg.xxScrEventNm.clear();
        scrnMsg.dsContrDtlPk_P.clear();
        scrnMsg.befEndRnwDaysAot_03.clear();
        scrnMsg.basePrcUpRatio_04.clear();
        scrnMsg.mtrPrcUpRatio_05.clear();
        scrnMsg.uplftBasePrcUpRatio_08.clear();
        scrnMsg.uplftMtrPrcUpRatio_09.clear();
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.R);
        // START 2016/02/24 [QC3697, ADD]
        ZYPTableUtil.clear(scrnMsg.Q);
        // END 2016/02/24 [QC3697, ADD]
        // Add Start 2017/11/06 QC#18552
        scrnMsg.svcMachMstrPk_PP.clear();
        // Add End 2017/11/06 QC#18552
    }

    /**
     * clearPopupParam
     * @param psnNmItem (i) First Name + " " + Last Name
     * @param firstNmItem (o) First Name
     * @param lastNmItem (o) Last Name
     */
    // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//    public static void setContactName(EZDBStringItem psnNmItem, EZDBStringItem firstNmItem, EZDBStringItem lastNmItem) {
//        if (ZYPCommonFunc.hasValue(psnNmItem)) {
//            String psnNm = psnNmItem.getValue();
//            int index = psnNm.indexOf(" ");
//            if (index == -1) {
//                ZYPEZDItemValueSetter.setValue(firstNmItem, psnNm);
//            } else {
//                ZYPEZDItemValueSetter.setValue(firstNmItem, psnNm.substring(0, index));
//                ZYPEZDItemValueSetter.setValue(lastNmItem, psnNm.substring(index + 1));
//            }
//        }
//    }
    // END 2018/06/18 K.Kitachi [QC#18591, DEL]

    // START 2016/02/17 T.Aoyagi [QC2954, ADD]
    /**
     * @param scrnMsg NSAL0300BMsg
     * @param i int
     * @return boolean
     */
    public static boolean isAggMachine(NSAL0300BMsg scrnMsg, int i) {

        if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals(scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END 2016/02/17 T.Aoyagi [QC2954, ADD]

    // START 2016/02/18 T.Aoyagi [QC3700, ADD]
    /**
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0300BMsg
     */
    public static void changeSummaryDetailBtnNm(S21CommonHandler handler, NSAL0300BMsg scrnMsg) {
//
//        if (SUMMARY_MODE.equals(scrnMsg.xxModeCd_SD.getValue())) {
//            handler.setButtonGuardLabel(BTN_DETAIL[0], BTN_DETAIL[1], BTN_DETAIL[2]);
//        } else {
//            handler.setButtonGuardLabel(BTN_SUMMARY[0], BTN_SUMMARY[1], BTN_SUMMARY[2]);
//        }
    }
    // END 2016/02/18 T.Aoyagi [QC3700, ADD]

    // add start 2016/04/07 CSA Defect#5312,5313
    /**
     * getSearchConditionSetting
     * @param scrnMsg NSAL0300BMsg
     * @return List
     */
    public static List<Object> getSearchConditionSetting(NSAL0300BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Person Code",       "PSN_CD",                scrnMsg.contrAdminPsnCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Person Name",       "PSN_NM",                scrnMsg.xxPsnNm_PP.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"Line Of Business",  "SVC_LINE_BIZ_CD",       scrnMsg.svcLineBizCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj4 = {"Branch Code",       "SVC_CONTR_BR_CD",       scrnMsg.svcContrBrCd.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj5 = {"Branch Name",       "SVC_CONTR_BR_DESC_TXT", scrnMsg.svcContrBrDescTxt.getValue(), ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);
        whereList.add(whereObj5);
        return whereList;
    }

    /**
     * getDisplayColumnSetting
     * @param scrnMsg NSAL0300BMsg
     * @return List
     */
    public static List<Object> getDisplayColumnSetting(NSAL0300BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Person Code", "PSN_CD", new BigDecimal("9"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Person Name", "PSN_NM", new BigDecimal("42"), ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {"Branch Code", "SVC_CONTR_BR_CD", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {"Branch Name", "SVC_CONTR_BR_DESC_TXT", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        return colList;
    }

    /**
     * getSortSetting
     * @param scrnMsg NSAL0300BMsg
     * @return List
     */
    public static List<Object> getSortSetting(NSAL0300BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"PSN_CD", "ASC" };
        Object[] sortObj2 = {"SVC_LINE_BIZ_CD", "ASC" };
        Object[] sortObj3 = {"SVC_CONTR_BR_CD", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);
        sortList.add(sortObj3);
        return sortList;
    }

    /**
     * getSelectSQL
     * @param scrnMsg NSAL0300BMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param psnTpCd String
     * @return String
     */
    // START 2017/01/24 N.Arai [QC#17228, MOD]
    // public static String getSelectSQL(NSAL0300BMsg scrnMsg, String glblCmpyCd, String slsDt) {
    public static String getSelectSQL(NSAL0300BMsg scrnMsg, String glblCmpyCd, String slsDt, String psnTpCd) {
    // END 2017/01/24 N.Arai [QC#17228, MOD]

        String firstOrgCd = ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, glblCmpyCd);

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT");
        sb.append("      A.GLBL_CMPY_CD                         AS GLBL_CMPY_CD");
        sb.append("     ,A.EZCANCELFLAG                         AS EZCANCELFLAG");
        sb.append("     ,A.PSN_CD                               AS PSN_CD");
        sb.append("     ,A.PSN_FIRST_NM || ' ' || A.PSN_LAST_NM AS PSN_NM");
        sb.append("     ,E.SVC_LINE_BIZ_CD                      AS SVC_LINE_BIZ_CD");
        sb.append("     ,C.SVC_CONTR_BR_CD                      AS SVC_CONTR_BR_CD");
        sb.append("     ,C.SVC_CONTR_BR_DESC_TXT                AS SVC_CONTR_BR_DESC_TXT");
        sb.append(" FROM");
        sb.append("      S21_PSN           A");
        sb.append("     ,(");
        sb.append("         SELECT");
        sb.append("              B1.GLBL_CMPY_CD");
        sb.append("             ,B1.PSN_CD");
        sb.append("             ,B1.SVC_CONTR_BR_CD");
        sb.append("             ,B1.EZCANCELFLAG");
        sb.append("             ,B1.EFF_FROM_DT");
        sb.append("             ,NVL(B1.EFF_THRU_DT, #maxDt#)  AS EFF_THRU_DT");
        // add start 2017/02/23 CSA Defect#15112
        sb.append("             ,B1.SVC_ORG_FUNC_ROLE_TP_CD");
        // add end 2017/02/23 CSA Defect#15112
        sb.append("         FROM");
        sb.append("             SVC_BR_RESRC_RELN B1");
        sb.append("         WHERE");
        sb.append("             B1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append("         AND B1.EZCANCELFLAG          = '0'");
        sb.append("         AND B1.SVC_BR_RESRC_ACTV_FLG = 'Y'");
        sb.append("      ) B");
        sb.append("     ,(");
        sb.append("         SELECT");
        sb.append("              C1.GLBL_CMPY_CD");
        sb.append("             ,C1.SVC_CONTR_BR_CD");
        sb.append("             ,C1.SVC_CONTR_BR_DESC_TXT");
        sb.append("             ,C1.EZCANCELFLAG");
        sb.append("             ,C1.EFF_FROM_DT");
        sb.append("             ,NVL(C1.EFF_THRU_DT, '#maxDt#')  AS EFF_THRU_DT");
        sb.append("         FROM");
        sb.append("             SVC_CONTR_BR C1");
        sb.append("         WHERE");
        sb.append("             C1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append("         AND C1.EZCANCELFLAG          = '0'");
        sb.append("         AND C1.SVC_CONTR_BR_ACTV_FLG = 'Y'");
        sb.append("      ) C");
        sb.append("     ,SVC_RG_BR_RELN D");
        sb.append("     ,(");
        sb.append("         SELECT");
        sb.append("              E1.GLBL_CMPY_CD");
        sb.append("             ,E1.SVC_RG_PK");
        sb.append("             ,E1.SVC_LINE_BIZ_CD");
        sb.append("             ,E1.EZCANCELFLAG");
        sb.append("             ,E1.EFF_FROM_DT");
        sb.append("             ,NVL(E1.EFF_THRU_DT, '#maxDt#')  AS EFF_THRU_DT");
        sb.append("         FROM");
        sb.append("             SVC_RG E1");
        sb.append("         WHERE");
        sb.append("             E1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append("         AND E1.EZCANCELFLAG          = '0'");
        sb.append("         AND E1.SVC_RG_ACTV_FLG       = 'Y'");
        sb.append("      ) E");
        // add start 2017/02/23 CSA Defect#15112
        sb.append("     ,ORG_FUNC_ROLE_TP F");
        // add end 2017/02/23 CSA Defect#15112
        sb.append(" WHERE");
        sb.append("     A.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append(" AND A.EZCANCELFLAG          = '0'");
        // START 2017/01/24 N.Arai [QC#17228, MOD]
        sb.append(" AND A.PSN_TP_CD             = '#psnTpCd#'");
        // END 2017/01/24 N.Arai [QC#17228, MOD]
        sb.append(" AND A.GLBL_CMPY_CD          = B.GLBL_CMPY_CD     (+)");
        sb.append(" AND A.PSN_CD                = B.PSN_CD           (+)");
        sb.append(" AND B.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND B.EFF_FROM_DT       (+) <= '#slsDt#'");
        sb.append(" AND B.EFF_THRU_DT       (+) >= '#slsDt#'");
        sb.append(" AND B.GLBL_CMPY_CD          = C.GLBL_CMPY_CD     (+)");
        sb.append(" AND B.SVC_CONTR_BR_CD       = C.SVC_CONTR_BR_CD  (+)");
        sb.append(" AND C.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND C.EFF_FROM_DT       (+) <= '#slsDt#'");
        sb.append(" AND C.EFF_THRU_DT       (+) >= '#slsDt#'");
        sb.append(" AND C.GLBL_CMPY_CD          = D.GLBL_CMPY_CD     (+)");
        sb.append(" AND C.SVC_CONTR_BR_CD       = D.SVC_CONTR_BR_CD  (+)");
        sb.append(" AND D.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND D.GLBL_CMPY_CD          = E.GLBL_CMPY_CD     (+)");
        sb.append(" AND D.SVC_RG_PK             = E.SVC_RG_PK        (+)");
        sb.append(" AND E.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND E.EFF_FROM_DT       (+) <= '#slsDt#'");
        sb.append(" AND E.EFF_THRU_DT       (+) >= '#slsDt#'");
        // add start 2017/02/23 CSA Defect#15112
        sb.append(" AND B.GLBL_CMPY_CD              = F.GLBL_CMPY_CD");
        sb.append(" AND B.SVC_ORG_FUNC_ROLE_TP_CD   = F.ORG_FUNC_ROLE_TP_CD");
        sb.append(" AND F.EZCANCELFLAG              = '0'");
        sb.append(" AND F.ASG_CONTR_FLG             = 'Y'");
        sb.append(" AND F.FIRST_ORG_CD              = '#firstOrgCd#'");
        // add end 2017/02/23 CSA Defect#15112

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        sql = sql.replaceAll("#maxDt#", DEF_EFF_THRU_DT);
        sql = sql.replaceAll("#slsDt#", slsDt);
        // START 2017/01/24 N.Arai [QC#17228, MOD]
        sql = sql.replaceAll("#psnTpCd#", psnTpCd);
        // END 2017/01/24 N.Arai [QC#17228, MOD]
        sql = sql.replaceAll("#firstOrgCd#", firstOrgCd);
        return sql;
    }
    // add end 2016/04/07 CSA Defect#5312,5313

    // START 2016/04/26 T.Tomita [QC#4668, ADD]
    /**
     * setSalesRepCommonPopUpParam
     * @param scrnMsg NSAL0300BMsg
     * @param glblCmpyCd String
     * @param tocNm String
     * @return Object[]
     */
    public static Object[] setSalesRepCommonPopUpParam(NSAL0300BMsg scrnMsg, String glblCmpyCd, String tocNm) {
        scrnMsg.xxScrEventNm.setValue("OpenWin_SalesRep");
        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = "Sales Rep Popup";
        // Table SQL
        params[i++] = getSalesRepSelectSQL(scrnMsg, glblCmpyCd);
        // Where List
        params[i++] = getSalesRepWhereList(scrnMsg, tocNm);
        // Column List
        List<Object[]> columnList = getSalesRepColumnList();
        params[i++] = columnList;
        // Sort Condition List
        params[i++] = getSalesRepSortConditionList();
        // outPut List
        params[i++] = scrnMsg.R;

        return params;
    }

    private static String getSalesRepSelectSQL(NSAL0300BMsg scrnMsg, String glblCmpyCd) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append("      T.GLBL_CMPY_CD");
        sb.append("     ,T.EZCANCELFLAG");
        sb.append("     ,SP.PSN_CD");
        sb.append("     ,T.TOC_CD");
        sb.append("     ,T.TOC_NM");
        sb.append("     ,OFRT.ORG_FUNC_ROLE_TP_DESC_TXT");
        sb.append(" FROM");
        sb.append("      TOC              T");
        sb.append("     ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("     ,BIZ_AREA_ORG     BAO");
        sb.append("     ,ORG_FUNC_ASG     OFA");
        sb.append("     ,S21_PSN          SP");
        sb.append(" WHERE");
        sb.append("         T.GLBL_CMPY_CD         = '#glblCmpyCd#'");
        sb.append("     AND T.EZCANCELFLAG         = '0'");
        sb.append("     AND T.GLBL_CMPY_CD         = OFRT.GLBL_CMPY_CD");
        sb.append("     AND T.ORG_FUNC_ROLE_TP_CD  = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("     AND OFRT.SLS_REP_FLG       = 'Y'");
        sb.append("     AND OFRT.EZCANCELFLAG      = '0'");
        sb.append("     AND OFRT.GLBL_CMPY_CD      = BAO.GLBL_CMPY_CD");
        sb.append("     AND OFRT.FIRST_ORG_CD      = BAO.BIZ_AREA_ORG_CD");
        sb.append("     AND BAO.SLS_FLG            = 'Y'");
        sb.append("     AND BAO.ORG_STRU_TP_CD     = '#orgStruTpCd#'");
        sb.append("     AND T.GLBL_CMPY_CD         = OFA.GLBL_CMPY_CD");
        sb.append("     AND T.TOC_CD               = OFA.TOC_CD");
        sb.append("     AND OFA.EZCANCELFLAG       = '0'");
        sb.append("     AND OFA.GLBL_CMPY_CD       = SP.GLBL_CMPY_CD");
        sb.append("     AND OFA.PSN_CD             = SP.PSN_CD");
        sb.append("     AND SP.EFF_FROM_DT        <= '#slsDt#'");
        sb.append("     AND (");
        sb.append("             SP.EFF_THRU_DT    >= '#slsDt#'");
        sb.append("         OR  SP.EFF_THRU_DT    IS NULL");
        sb.append("     )");
        sb.append("     AND SP.EZCANCELFLAG        = '0'");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        sql = sql.replaceAll("#orgStruTpCd#", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        sql = sql.replaceAll("#slsDt#", ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID));
        return sql;
    }

    private static List<Object[]> getSalesRepWhereList(NSAL0300BMsg scrnMsg, String tocNm) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Employee ID";
        h0[WLIST_OBJ_ID] = "PSN_CD";
        h0[WLIST_OBJ_VALUE] = null;
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Name";
        h1[WLIST_OBJ_ID] = "TOC_NM";
        h1[WLIST_OBJ_VALUE] = tocNm;
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private static List<Object[]> getSalesRepColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Employee ID";
        c0[CLIST_OBJ_ID] = "PSN_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(PSN_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Sales Rep Code";
        c1[CLIST_OBJ_ID] = "TOC_CD";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(TOC_CD_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        c2[CLIST_DSP_OBJ_NM] = "Name";
        c2[CLIST_OBJ_ID] = "TOC_NM";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(TOC_NM_LENGTH);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c2);

        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
        c3[CLIST_DSP_OBJ_NM] = "Role";
        c3[CLIST_OBJ_ID] = "ORG_FUNC_ROLE_TP_DESC_TXT";
        c3[CLIST_OBJ_LENGTH] = new BigDecimal(ORG_FUNC_ROLE_TP_DESC_TXT_LENGTH);
        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c3);

        return columnList;
    }

    private static List<Object[]> getSalesRepSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "PSN_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
    // END 2016/04/26 T.Tomita [QC#4668, ADD]
    // add start 2016/07/01 CSA Defect#11261
    // Mod Start 2018/01/11 QC#18552
    /**
     * setSvcPgmCommonPopUpParam
     * @param scrnMsg NSAL0300BMsg
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param mdseNm String
     * @return Object[]
     */
    public static Object[] setSvcPgmCommonPopUpParam(NSAL0300BMsg scrnMsg, String glblCmpyCd, String mdseCd, String mdseNm) {
        scrnMsg.xxScrEventNm.setValue("OpenWin_ServiceProgram");
        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        params[i++] = "Service Program Item Popup";
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        // Table SQL
        params[i++] = getSvcPgmSelectSQL(scrnMsg, glblCmpyCd);
        // Where List
        params[i++] = getSvcPgmWhereList(scrnMsg, mdseCd, mdseNm);
        // Column List
        params[i++] = getSvcPgmColumnList();
        // Sort Condition List
        params[i++] = getSvcPgmSortConditionList();
        // outPut List
        params[i++] = scrnMsg.R;

        return params;
    }
    // Mod End 2018/01/11 QC#18552

    private static String getSvcPgmSelectSQL(NSAL0300BMsg scrnMsg, String glblCmpyCd) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT");
        sb.append("      A.GLBL_CMPY_CD");
        sb.append("     ,A.EZCANCELFLAG");
        sb.append("     ,A.MDSE_CD");
        sb.append("     ,A.MDSE_DESC_SHORT_TXT");
        sb.append("     ,B.SVC_COV_TMPL_TP_SORT_NUM");
        sb.append(" FROM");
        sb.append("      MDSE             A");
        sb.append("     ,SVC_COV_TMPL_TP  B");
        // add start 2018/08/23 QC#27790
        sb.append("     ,ITEM_TP_VAL_SET  C");
        // add end 2018/08/23 QC#27790
        sb.append(" WHERE");
        sb.append("         A.GLBL_CMPY_CD       = '#glblCmpyCd#'");
        // add start 2018/08/23 QC#27790
        sb.append("     AND A.RGTN_STS_CD        = '#rgtnStsCd#'");
        // add end 2018/08/23 QC#27790
        sb.append("     AND A.EZCANCELFLAG       = '0'");
        sb.append("     AND A.GLBL_CMPY_CD       = B.GLBL_CMPY_CD");
        sb.append("     AND A.SVC_COV_TMPL_TP_CD = B.SVC_COV_TMPL_TP_CD");
        sb.append("     AND B.EZCANCELFLAG       = '0'");
        // add start 2018/08/23 QC#27790
        sb.append("     AND A.GLBL_CMPY_CD       = C.GLBL_CMPY_CD");
        sb.append("     AND A.MDSE_ITEM_TP_CD    = C.MDSE_ITEM_TP_CD");
        sb.append("     AND C.ITEM_TP_CTX_TP_CD  = '#itemTpCtxTpCd#'");
        sb.append("     AND C.EZCANCELFLAG       = '0'");
        // add end 2018/08/23 QC#27790

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        // add start 2018/08/23 QC#27790
        sql = sql.replaceAll("#rgtnStsCd#", RGTN_STS.READY_FOR_ORDER_TAKING);
        sql = sql.replaceAll("#itemTpCtxTpCd#", "CPO_SVC_MDSE_QLFY_ITEM_TP");
        // add end 2018/08/23 QC#27790
        return sql;
    }

    // Mod Start 2018/01/11 QC#18552
    private static List<Object[]> getSvcPgmWhereList(NSAL0300BMsg scrnMsg, String mdseCd, String mdseNm) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        h0[WLIST_DSP_OBJ_NM] = "Svc Program Item Cd";
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        h0[WLIST_OBJ_ID] = "MDSE_CD";
        h0[WLIST_OBJ_VALUE] = mdseCd;
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        h1[WLIST_DSP_OBJ_NM] = "Svc Program Item Nm";
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        h1[WLIST_OBJ_ID] = "MDSE_DESC_SHORT_TXT";
        h1[WLIST_OBJ_VALUE] = mdseNm;
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }
    // Mod End 2018/01/11 QC#18552

    private static List<Object[]> getSvcPgmColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        c0[CLIST_DSP_OBJ_NM] = "Svc Program Item Cd";
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        c0[CLIST_OBJ_ID] = "MDSE_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(LEN_16);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        c1[CLIST_DSP_OBJ_NM] = "Svc Program Item Nm";
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        c1[CLIST_OBJ_ID] = "MDSE_DESC_SHORT_TXT";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(LEN_50);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private static List<Object[]> getSvcPgmSortConditionList() {

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortObj1 = {"SVC_COV_TMPL_TP_SORT_NUM", "ASC" };
        Object[] sortObj2 = {"MDSE_CD", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);
        return sortList;
    }
    // add end 2016/07/01 CSA Defect#11261

    // START 2017/01/27 [QC#17278, ADD]
    /**
     * setReportGrpCommonPopUpParam
     * @param scrnMsg NSAL0300BMsg
     * @return Object[]
     */
    public static Object[] setReportGrpCommonPopUpParam(NSAL0300BMsg scrnMsg) {
        scrnMsg.xxScrEventNm.setValue("OpenWin_ReportGrp");
        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = "Report Group Search Popup";
        // Table SQL
        params[i++] = "DS_CONTR_RPT_GRP";
        // Where List
        params[i++] = getReportGrpWhereList(scrnMsg);
        // Column List
        List<Object[]> columnList = getReportGrpColumnList();
        params[i++] = columnList;
        // Sort Condition List
        params[i++] = getReportGrpSortConditionList();
        // outPut List
        params[i++] = scrnMsg.R;

        return params;
    }

    /**
     * getReportGrpWhereList
     * @param scrnMsg NSAL0300BMsg
     * @return List<Object[]>
     */
    public static List<Object[]> getReportGrpWhereList(NSAL0300BMsg scrnMsg) {
        // START 2017/01/27 [QC#17278, MOD]
        // String dsContrRptGrpNum = scrnMsg.dsContrRptGrpNum.getValue();
        String dsContrRptGrpDescTxt = scrnMsg.dsContrRptGrpDescTxt.getValue();
        // END   2017/01/27 [QC#17278, MOD]

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Report Group#";
        h0[WLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_NUM";
        // START 2017/01/27 [QC#17278, MOD]
        // h0[WLIST_OBJ_VALUE] = dsContrRptGrpNum;
        h0[WLIST_OBJ_VALUE] = "";
        // END   2017/01/27 [QC#17278, MOD]
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Description";
        h1[WLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_DESC_TXT";
        // START 2017/01/27 [QC#17278, MOD]
        // h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_OBJ_VALUE] = dsContrRptGrpDescTxt;
        // END   2017/01/27 [QC#17278, MOD]
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    /**
     * getReportGrpColumnList
     * @return List<Object[]>
     */
    public static List<Object[]> getReportGrpColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Report Group#";
        c0[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_NUM";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_NUM_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Description";
        c1[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_DESC_TXT";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_DESC_TXT_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        c2[CLIST_DSP_OBJ_NM] = "Start Date";
        c2[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_START_DT";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_START_DT_LENGTH);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c2);

        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
        c3[CLIST_DSP_OBJ_NM] = "End Date";
        c3[CLIST_OBJ_ID] = "DS_CONTR_RPT_GRP_END_DT";
        c3[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_RPT_GRP_END_DT_LENGTH);
        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c3);

        return columnList;
    }

    /**
     * getReportGrpSortConditionList
     * @return List<Object[]>
     */
    public static List<Object[]> getReportGrpSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "DS_CONTR_RPT_GRP_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
    // END   2017/01/27 [QC#17278, ADD]

    /**
     * isOpenB
     * @param scrnMsg NSAL0300BMsg
     * @param indexB index of scrnMsg.B
     * @return true:Machine List Open
     */
    public static boolean isOpenB(NSAL0300BMsg scrnMsg, int indexB) {
        String dsContrDtlTpCd = scrnMsg.B.no(indexB).dsContrDtlTpCd_B.getValue();
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            return true;
        }

        // START 2017/07/27 [QC#16889, MOD]
        // String serNum_B = scrnMsg.B.no(indexB).serNum_B.getValue();
        // String mdseCd_B = scrnMsg.B.no(indexB).mdseCd_B.getValue();
        BigDecimal dsContrDtlPk_B = scrnMsg.B.no(indexB).dsContrDtlPk_B.getValue();
        // END 2017/07/27 [QC#16889, MOD]

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2017/07/27 [QC#16889, MOD]
            // if (NSAL0300CommonLogic.isEqualMach(scrnMsg.A.no(i).serNum_A.getValue(), scrnMsg.A.no(i).mdseCd_A.getValue(), serNum_B, mdseCd_B)) {
            if (NSAL0300CommonLogic.isEqualNum(scrnMsg.A.no(i).dsContrDtlPk_A.getValue(), dsContrDtlPk_B)) {
            // END 2017/07/27 [QC#16889, MOD]
                String icon = scrnMsg.A.no(i).xxFilePathTxt_A.getValue();
                if (IMG_OPEN_MACHINE_RED.equals(icon) || IMG_OPEN_MACHINE_GREEN.equals(icon)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * setMachineListAllIcon
     * @param scrnMsg NSAL0300BMsg
     */
    public static void setMachineListAllIcon(NSAL0300BMsg scrnMsg) {
        int openCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (IMG_OPEN_MACHINE_RED.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue()) || IMG_OPEN_MACHINE_GREEN.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                openCnt++;
            }
        }
        if (scrnMsg.A.getValidCount() == 0 || DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            scrnMsg.xxFilePathTxt_M.clear();
        } else if (openCnt == scrnMsg.A.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_M, IMG_OPEN_MACHINE_ALL);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_M, IMG_CLOSE_MACHINE_ALL);
        }
    }

    /**
     * getTierCnt
     * @param scrnMsg NSAL0300BMsg
     * @param dsContrBllgMtrPk BigDecimal
     */
    public static int getTierCnt(NSAL0300BMsg scrnMsg, BigDecimal dsContrBllgMtrPk) {
        if (dsContrBllgMtrPk == null) {
            return 1;
        }

        int tierCnt = 0;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (isEqualNum(scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue(), dsContrBllgMtrPk)) {
                tierCnt++;
            }
        }
        return tierCnt;
    }

    private static void controllError(NSAL0300BMsg scrnMsg) {
        int errCode = 0;

        // EndCustomer
        errCode += scrnMsg.dsAcctNum.getErrorCode();
        errCode += scrnMsg.altPayerCustCd.getErrorCode();
        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        errCode += scrnMsg.xxPsnNm_CP.getErrorCode();
        errCode += scrnMsg.ctacPsnFirstNm_CP.getErrorCode();
        errCode += scrnMsg.ctacPsnLastNm_CP.getErrorCode();
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
        errCode += scrnMsg.leaseCmpyCd.getErrorCode();
        errCode += scrnMsg.baseChrgToLeaseCmpyFlg.getErrorCode();
        errCode += scrnMsg.usgChrgToLeaseCmpyFlg.getErrorCode();
        // START 2018/05/15 K.Kitachi [QC#24265, ADD]
        errCode += scrnMsg.cfsLeaseNumCmntTxt.getErrorCode();
        // END 2018/05/15 K.Kitachi [QC#24265, ADD]
        errCode += scrnMsg.custPoNum.getErrorCode();
        // START 2019/01/09 K.Kitachi [QC#26928, ADD]
        errCode += scrnMsg.poFromDt.getErrorCode();
        // END 2019/01/09 K.Kitachi [QC#26928, ADD]
        errCode += scrnMsg.poDt.getErrorCode();
        errCode += scrnMsg.crCardCustRefNum.getErrorCode();
        errCode += scrnMsg.pmtTermCashDiscCd.getErrorCode();
        errCode += scrnMsg.mdseDescShortTxt_SP.getErrorCode();
        errCode += scrnMsg.baseBllgCycleCd.getErrorCode();
        errCode += scrnMsg.mtrBllgCycleCd.getErrorCode();
        errCode += scrnMsg.mtrEstTpCd.getErrorCode();
        // START 2022/03/22 [QC#59683, DEL]
//        errCode += scrnMsg.xxSelFlg_UT.getErrorCode();
        // END   2022/03/22 [QC#59683, DEL]
        // START 2022/03/22 [QC#59683, ADD]
        errCode += scrnMsg.dsInvTgtrTpCd.getErrorCode();
        // END   2022/03/22 [QC#59683, ADD]
        errCode += scrnMsg.prcAllocByMachQtyFlg.getErrorCode();
        errCode += scrnMsg.contrAutoRnwTpCd.getErrorCode();
        errCode += scrnMsg.rnwPrcMethCd.getErrorCode();
        errCode += scrnMsg.basePrcUpRatio.getErrorCode();
        errCode += scrnMsg.mtrPrcUpRatio.getErrorCode();
        errCode += scrnMsg.befEndRnwDaysAot.getErrorCode();
        errCode += scrnMsg.contrUplftTpCd.getErrorCode();
        errCode += scrnMsg.uplftPrcMethCd.getErrorCode();
        errCode += scrnMsg.uplftBasePrcUpRatio.getErrorCode();
        errCode += scrnMsg.uplftMtrPrcUpRatio.getErrorCode();
        if (errCode != 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_EC, IMG_OPEN_ARROW);
        }

        List<BigDecimal> errEffDtDsContrDtlPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> errBaseDsContrDtlPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> errOverageDsContrDtlPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> errDsContrBllgMtrPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> errMtrDsContrDtlPkListForA = new ArrayList<BigDecimal>();
        BigDecimal dsContrDtlPk = null;
        BigDecimal dsContrBllgMtrPk = null;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            dsContrDtlPk = scrnMsg.B.no(i).dsContrDtlPk_B.getValue();
            dsContrBllgMtrPk = scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue();

            // Start Date, End Date, Fleet Ship To
            errCode = 0;
            errCode += scrnMsg.B.no(i).contrEffFromDt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).contrEffThruDt_B.getErrorCode();
            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            errCode += scrnMsg.B.no(i).shipToCustCd_B.getErrorCode();
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
            if (errCode != 0 && !errEffDtDsContrDtlPkList.contains(dsContrDtlPk)) {
                errEffDtDsContrDtlPkList.add(dsContrDtlPk);
            }

            // Base
            errCode = 0;
            errCode += scrnMsg.B.no(i).baseBillToCustCd_B.getErrorCode();
            // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//            errCode += scrnMsg.B.no(i).xxPsnNm_BB.getErrorCode();
            errCode += scrnMsg.B.no(i).ctacPsnFirstNm_BB.getErrorCode();
            errCode += scrnMsg.B.no(i).ctacPsnLastNm_BB.getErrorCode();
            // END 2018/06/18 K.Kitachi [QC#18591, MOD]
            // Add Start 2018/01/11 QC#18552
            errCode += scrnMsg.B.no(i).svcPgmMdseCd_B.getErrorCode();
            // Add End 2018/01/11 QC#18552
            errCode += scrnMsg.B.no(i).mdseDescShortTxt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).basePrcDealAmt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).basePrcTermDealAmtRate_B.getErrorCode();
            errCode += scrnMsg.B.no(i).baseBllgCycleCd_B.getErrorCode();
            // Del Start 2017/12/21 QC#18552
//            errCode += scrnMsg.B.no(i).baseBllgTmgCd_B.getErrorCode();
//            errCode += scrnMsg.B.no(i).baseDplyPerEndDay_B.getErrorCode();
//            errCode += scrnMsg.B.no(i).contrBllgDay_B.getErrorCode();
            // Del End 2017/12/21 QC#18552
            if (errCode != 0 && !errBaseDsContrDtlPkList.contains(dsContrDtlPk)) {
                errBaseDsContrDtlPkList.add(dsContrDtlPk);
            }

            // Overage
            errCode = 0;
            // Del Start 2017/12/21 QC#18552
//            errCode += scrnMsg.B.no(i).mtrDplyPerEndDay_B.getErrorCode();
//            errCode += scrnMsg.B.no(i).mtrBllgDay_B.getErrorCode();
            // Del End 2017/12/21 QC#18552
            errCode += scrnMsg.B.no(i).bllgMtrBllgCycleCd_B.getErrorCode();
            errCode += scrnMsg.B.no(i).xsChrgTpCd_B.getErrorCode();
            errCode += scrnMsg.B.no(i).xsMtrCopyQty_B.getErrorCode();
            errCode += scrnMsg.B.no(i).xsMtrAmtRate_B.getErrorCode();
            errCode += scrnMsg.B.no(i).bllgMtrBillToCustCd_B.getErrorCode();
            // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//            errCode += scrnMsg.B.no(i).xxPsnNm_BM.getErrorCode();
            errCode += scrnMsg.B.no(i).ctacPsnFirstNm_BM.getErrorCode();
            errCode += scrnMsg.B.no(i).ctacPsnLastNm_BM.getErrorCode();
            // END 2018/06/18 K.Kitachi [QC#18591, MOD]
            errCode += scrnMsg.B.no(i).bllgFreeCopyCnt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).bllgMinCnt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).bllgMinAmtRate_B.getErrorCode();
            errCode += scrnMsg.B.no(i).bllgRollOverRatio_B.getErrorCode();
            // START 2019/01/17 M.Naito [QC#29083,ADD]
            errCode += scrnMsg.B.no(i).cumCopyCnt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).cumCopyFreqMthAot_B.getErrorCode();
            errCode += scrnMsg.B.no(i).cumCopyStartDt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).cumCopyEndDt_B.getErrorCode();
            // END 2019/01/17 M.Naito [QC#29083,ADD]
            if (errCode != 0 && !errOverageDsContrDtlPkList.contains(dsContrDtlPk)) {
                errOverageDsContrDtlPkList.add(dsContrDtlPk);
            }

            // Billing Counter
            errCode = 0;
            errCode += scrnMsg.B.no(i).bllgFreeCopyCnt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).bllgMinCnt_B.getErrorCode();
            errCode += scrnMsg.B.no(i).bllgMinAmtRate_B.getErrorCode();
            errCode += scrnMsg.B.no(i).bllgRollOverRatio_B.getErrorCode();
            if (errCode != 0 && !errDsContrBllgMtrPkList.contains(dsContrBllgMtrPk)) {
                errDsContrBllgMtrPkList.add(dsContrBllgMtrPk);
                if (!errMtrDsContrDtlPkListForA.contains(dsContrDtlPk)) {
                    errMtrDsContrDtlPkListForA.add(dsContrDtlPk);
                }
            }
        }

        String dsContrDtlTpCd = null;
        boolean fleetAggregateOpenFlg = false;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            dsContrDtlPk = scrnMsg.B.no(i).dsContrDtlPk_B.getValue();
            dsContrBllgMtrPk = scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
            dsContrDtlTpCd = scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue();

            // Base Open
            if (errBaseDsContrDtlPkList.contains(dsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BB, IMG_OPEN_ARROW);
            }

            // Overage Open
            if (errOverageDsContrDtlPkList.contains(dsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BM, IMG_OPEN_ARROW);
            }

            // Billing Counter Open
            // Mod Start 12/20/2016 <QC#16647>
            if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                if (errDsContrBllgMtrPkList.contains(dsContrBllgMtrPk)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BC, IMG_OPEN_ARROW);
                }
            } else {
                scrnMsg.B.no(i).xxFilePathTxt_BC.clear();
            }
            // Mod End   12/20/2016 <QC#16647>

            if (!fleetAggregateOpenFlg && (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd))) {
                if (errEffDtDsContrDtlPkList.contains(dsContrDtlPk) || errBaseDsContrDtlPkList.contains(dsContrDtlPk) || errOverageDsContrDtlPkList.contains(dsContrDtlPk) || errDsContrBllgMtrPkList.contains(dsContrBllgMtrPk)) {
                    fleetAggregateOpenFlg = true;
                }
            }
        }

        // Fleet/Aggregate Line Open
        if (fleetAggregateOpenFlg) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_FA, IMG_OPEN_ARROW);
        }

        // Machine List Open
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            dsContrDtlPk = scrnMsg.A.no(i).dsContrDtlPk_A.getValue();
            if (errEffDtDsContrDtlPkList.contains(dsContrDtlPk) || errBaseDsContrDtlPkList.contains(dsContrDtlPk) || errOverageDsContrDtlPkList.contains(dsContrDtlPk) || errMtrDsContrDtlPkListForA.contains(dsContrDtlPk)) {
                if (IMG_CLOSE_MACHINE_RED.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_RED);
                } else if (IMG_CLOSE_MACHINE_GREEN.equals(scrnMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_GREEN);
                }
            }
        }
    }

    /**
     * getBillingMeterCount
     * @param scrnMsg NSAL0300BMsg
     * @param index index of scrnMsg.B
     */
    public static int getBillingMeterCount(NSAL0300BMsg scrnMsg, int index) {
        BigDecimal dsContrDtlPk = scrnMsg.B.no(index).dsContrDtlPk_B.getValue();
        BigDecimal preDsContrBllgMtrPk = null;
        BigDecimal curDsContrBllgMtrPk = null;

        int cnt = 0;
        for (int i = index; i < scrnMsg.B.getValidCount(); i++) {
            if (isEqualNum(scrnMsg.B.no(i).dsContrDtlPk_B.getValue(), dsContrDtlPk)) {
                 curDsContrBllgMtrPk = scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
                if (!isEqualNum(curDsContrBllgMtrPk, preDsContrBllgMtrPk)) {
                    cnt++;
                    preDsContrBllgMtrPk = curDsContrBllgMtrPk;
                }
            }
        }
        return cnt;
    }
    // START 2018/07/24 K.Kim [QC#26768, ADD]
    /**
     * expandBillingCounter
     * @param scrnMsg NSAL0300BMsg
     */
    public static void expandBillingCounter(NSAL0300BMsg scrnMsg) {
        boolean fleetAggregateOpenFlg = false;

        // Billing Counter Open
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            BigDecimal dsContrBllgMtrPk = scrnMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
            if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                if (scrnMsg.B.no(i).bllgMtrBillToCustCd_B.getErrorCode() != 0) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxFilePathTxt_BC, IMG_OPEN_ARROW);
                    fleetAggregateOpenFlg = true;
                }
            }
        }

        // Fleet/Aggregate Line Open
        if (fleetAggregateOpenFlg) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                String dsContrDtlTpCd = scrnMsg.B.no(i).dsContrDtlTpCd_B.getValue();
                if ((DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && scrnMsg.A.getValidCount() > 0) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_FA, IMG_OPEN_ARROW);
                }
            }
        }
    }
    // END 2018/07/24 K.Kim [QC#26768, ADD]
    // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
    /**
     * Get Param NMAL3300
     * @param scrnMsg NSAL0300BMsg
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return Param NMAL3300
     */
    public static Object[] getParamNMAL3300(NSAL0300BMsg scrnMsg, String glblCmpyCd, String billToCustCd) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, FUNC_CATG_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, DS_TRX_RULE_TP.CONTRACT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, "");

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.xxPopPrm_01);
        paramList.add(scrnMsg.xxPopPrm_02);
        paramList.add(scrnMsg.xxPopPrm_03);
        paramList.add(scrnMsg.xxPopPrm_04);
        paramList.add("V"); // Suffix

        // Customer Special Instruction Line
        scrnMsg.V.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(0).dsAcctNum_V, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(0).billToCustCd_V, billToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(0).shipToCustCd_V, "");
        paramList.add(scrnMsg.V);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, scrnMsg.svcLineBizCd);
        paramList.add(scrnMsg.xxPopPrm_05);

        return paramList.toArray(new Object[0]);
    }
    // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
}
