/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0300;

import static business.blap.NSAL0300.constant.NSAL0300Constant.ACTV_PENDING_PO_CONTR_STS_NM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ACTV_RENEWAL_HOLD_CONTR_STS_NM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ALL_PER_TRMN_CONTR_STS_NM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_EFF_THRU_DT;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FETCH_SIZE_MAX;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LVL_NUM_1;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LVL_NUM_2;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LVL_NUM_3;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MACH_LVL_NUM_1;
import static business.blap.NSAL0300.constant.NSAL0300Constant.PERIOD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.PND_ISTL_CONTR_STS_NM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.TBL_ALIAS;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0300.constant.NSAL0300Constant;
import business.db.AGGR_USG_RECALTMsg;
import business.db.AGGR_USG_RECAL_DTLTMsg;
import business.db.AGGR_USG_RECAL_XS_MTRTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_COV_SVCTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_UPLFT_TPTMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.CR_CARD_TRXTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.DS_CONTR_ACTTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsgArray;
import business.db.DS_CONTR_PRC_ALLOCTMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsgArray;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.db.DS_CONTR_STS_VTMsg;
import business.db.DS_CONTR_STS_VTMsgArray;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.db.DS_INV_TGTR_TPTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTRTMsgArray;
import business.db.DS_SUB_CONTR_MTRTMsg;
import business.db.DS_SUB_CONTR_MTRTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.MTR_LBTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.S21_PSNTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMOTMsgArray;
import business.db.SVC_TERM_CONDTMsg;
import business.db.SVC_TERM_CONDTMsgArray;
import business.db.SVC_TERM_COND_ATTRBTMsg;
import business.db.SVC_TERM_COND_ATTRBTMsgArray;
import business.db.TOCTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC501
 * 2015/12/16   Hitachi         T.Kanasaka      Update          QC2069
 * 2015/12/25   Hitachi         T.Tomita        Update          QC2133
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2016/02/16   Hitachi         T.Aoyagi        Update          QC2947
 * 2016/02/17   Hitachi         T.Kanasaka      Update          QC3023
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/02/26   Hitachi         T.Kanasaka      Update          QC4092
 * 2016/04/07   Hitachi         M.Gotou         Update          QC#5312,5113
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#3886, QC#4668
 * 2016/05/17   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/05/20   Hitachi         T.Kanasaka      Update          QC#5942
 * 2016/05/27   Hitachi         T.Tomita        Update          QC#9125
 * 2016/05/30   Hitachi         K.Kasai         Update          QC#447
 * 2016/06/20   Hitachi         T.Kanasaka      Update          QC#9019
 * 2016/06/21   Hitachi         M.Gotou         Update          QC#6923
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/06   Hitachi         T.Kanasaka      Update          QC#9019
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/09/06   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/11/22   Hitachi         A.Kohinata      Update          QC#16114
 * 2017/01/27   Hitachi         Y.Takeno        Update          QC#17278
 * 2017/02/06   Hitachi         Y.Takeno        Update          QC#17275
 * 2017/02/10   Hitachi         Y.Takeno        Update          QC#17494
 * 2017/02/14   Hitachi         Y.Takeno        Update          QC#17275-1
 * 2017/04/27   Hitachi         Y.Takeno        Update          RS#7224
 * 2017/06/06   Hitachi         T.Mizuki        Update          QC#18845
 * 2017/06/17   Hitachi         K.Kojima        Update          QC#19256
 * 2017/06/22   Hitachi         Y.Osawa         Update          QC#17496
 * 2017/07/20   Hitachi         M.Kidokoro      Update          QC#4468
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/08/25   Hitachi         U.Kim           Update          QC#20728
 * 2017/08/31   Hitachi         K.Kojima        Update          QC#18355
 * 2017/09/11   Hitachi         K.Kojima        Update          QC#18435
 * 2017/09/13   Hitachi         K.Kim           Update          QC#19938
 * 2017/09/19   Hitachi         K.Kitachi       Update          QC#21149
 * 2017/09/21   Hitachi         K.Kitachi       Update          QC#21222
 * 2017/09/26   Hitachi         K.Kojima        Update          QC#21221
 * 2017/10/05   Hitachi         K.Kojima        Update          QC#20523
 * 2017/10/18   Hitachi         K.Kitachi       Update          QC#21222
 * 2017/11/27   Hitachi         T.Tomita        Update          QC#21724
 * 2017/12/21   Hitachi         T.Tomita        Update          QC#18779
 * 2018/01/12   Hitachi         T.Tomita        Update          QC#18552
 * 2018/02/19   Hitachi         K.Kojima        Update          QC#24105
 * 2018/04/04   Hitachi         U.Kim           Update          QC#23559(Sol358)
 * 2018/04/03   Hitachi         K.Kojima        Update          QC#21056
 * 2018/06/05   Hitachi         K.Kim           Update          QC#24851
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/02   Hitachi         K.Kitachi       Update          QC#26765
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26946
 * 2018/08/23   Hitachi         A.Kohinata      Update          QC#27790
 * 2018/09/20   Hitachi         K.Kitachi       Update          QC#28328
 * 2018/11/07   Hitachi         K.Fujimoto      Update          QC#28627
 * 2018/12/13   Hitachi         K.Kim           Update          QC#29079
 * 2019/01/09   Hitachi         K.Kitachi       Update          QC#26928
 * 2019/01/17   Hitachi         K.Kim           Update          QC#29782
 * 2019/01/17   CITS            M.Naito         Update          QC#29083
 * 2019/01/28   Hitachi         T.Tomita        Update          QC#29083
 * 2019/02/12   Hitachi         S.Kitamura      Update          QC#30264
 * 2020/06/25   CITS            T.Wada          Update          QC#55590
 * 2021/10/01   CITS            T.Wada          Update          QC#59240
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/03/24   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/06/17   Hitachi         K.Kitachi       Update          QC#60197
 * 2023/02/03   Hitachi         R.Takau         Update          QC#55645
 * 2023/04/14   CITS            L.Borrega       Update          QC#61370
 * 2023/08/18   CITS            T.Kojima        Update          QC#60846
 * 2023/10/05   Hitachi         H.Iinuma        Update          QC#61675
 * 2023/12/21   CITS            T.Aizawa        Update          QC#63377
 * 2024/03/22   Hitachi         Y.Tamai         Update          QC#63463
 * </pre>
 */
public class NSAL0300Query extends S21SsmEZDQuerySupport {

    private static final NSAL0300Query INSTANCE = new NSAL0300Query();

    private NSAL0300Query() {
    }

    public static NSAL0300Query getInstance() {
        return INSTANCE;
    }

    public S21SsmEZDResult getDsContrPk(String glblCmpyCd, String dsContrNum, String dsContrSqNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrNum", dsContrNum);
        ssmPrm.put("dsContrSts", DS_CONTR_STS.ORDER);
        ssmPrm.put("dsContrSqNum", dsContrSqNum);
        return getSsmEZDClient().queryObject("getDsContrPk", ssmPrm);
    }

    public S21SsmEZDResult getDsContrInfo(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        // START 2016/04/26 T.Tomita [QC#4668, ADD]
        ssmPrm.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        // END 2016/04/26 T.Tomita [QC#4668, ADD]
        ssmPrm.put("dsContrSts", DS_CONTR_STS.ORDER);
        // START 2017/06/17 K.Kojima [QC#19256,ADD]
        ssmPrm.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/17 K.Kojima [QC#19256,ADD]
        // START 2019/01/09 K.Kitachi [QC#26928, ADD]
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        // END 2019/01/09 K.Kitachi [QC#26928, ADD]
        return getSsmEZDClient().queryObject("getDsContr", ssmPrm);
    }

    public S21SsmEZDResult getDsContrDtlInfo1(String glblCmpyCd, BigDecimal dsContrPk, String dsContrCatgCd, int rowNum) {
        // START 2018/02/19 K.Kojima [QC#24145,ADD]
        String activePendingPOContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_PENDING_PO_CONTR_STS_NM, glblCmpyCd);
        String activeRenewalHoldContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_RENEWAL_HOLD_CONTR_STS_NM, glblCmpyCd);
        String pndIstlContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(PND_ISTL_CONTR_STS_NM, glblCmpyCd);
        // END 2018/02/19 K.Kojima [QC#24145,ADD]
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        String allPerTrmnContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ALL_PER_TRMN_CONTR_STS_NM, glblCmpyCd);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("rowNum", rowNum);
        ssmPrm.put("dsContrDtlTpCd_Accessory", DS_CONTR_DTL_TP.ACCESSORIES);
        ssmPrm.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        // START 2023/12/21 t.aizawa [QC#63377,ADD]
        ssmPrm.put("skip", SKIP_RECOV_TP.SKIP);
        // END   2023/12/21 t.aizawa [QC#63377,ADD]
        // START 2017/09/26 K.Kojima [QC#21221,ADD]
        ssmPrm.put("dsBllgSchdTpRegular", DS_BLLG_SCHD_TP.REGULAR);
        // END 2017/09/26 K.Kojima [QC#21221,ADD]
        // START 2018/02/19 K.Kojima [QC#24105,ADD]
        ssmPrm.put("activePendingPO", activePendingPOContrStsNm);
        ssmPrm.put("activeRenewalHold", activeRenewalHoldContrStsNm);
        ssmPrm.put("dsContrCtrlStsCdActive", DS_CONTR_CTRL_STS.ACTIVE);
        ssmPrm.put("dsContrCtrlStsCdRenewalHold", DS_CONTR_CTRL_STS.RENEWAL_HOLD);
        ssmPrm.put("dsContrCtrlStsCdRenewalHoldForPO", DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO);
        ssmPrm.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
        ssmPrm.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        ssmPrm.put("pendingInstallation", pndIstlContrStsNm);
        ssmPrm.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        // END 2018/02/19 K.Kojima [QC#24105,ADD]
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        ssmPrm.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        ssmPrm.put("allPeriodTermination", allPerTrmnContrStsNm);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        // START 2019/01/17 [QC#29782, ADD]
        ssmPrm.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END 2019/01/17 [QC#29782, ADD]
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            return getSsmEZDClient().queryObjectList("getDsContrDtl1_Reg", ssmPrm);
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
            return getSsmEZDClient().queryObjectList("getDsContrDtl1_Flt", ssmPrm);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
            return getSsmEZDClient().queryObjectList("getDsContrDtl1_Aggr", ssmPrm);
        } else if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            // reuse regular contract logic
            return getSsmEZDClient().queryObjectList("getDsContrDtl1_Reg", ssmPrm);
        } else {
            return null;
        }
    }

    // START 2016/02/17 T.Kanasaka [QC3023, MOD]
    // START 2017/09/26 K.Kojima [QC#21221,MOD]
    // public S21SsmEZDResult getDsContrDtlInfo2(String glblCmpyCd, BigDecimal dsContrPk, String dsContrCatgCd, String dsContrStsCd, int rowNum) {
    public S21SsmEZDResult getDsContrDtlInfo2(String glblCmpyCd, BigDecimal dsContrPk, String dsContrCatgCd, String dsContrStsCd, int rowNum, String forScheduleFlag) {
    // END 2017/09/26 K.Kojima [QC#21221,MOD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("rowNum", rowNum);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("dsContrPrcEffStsCd_Cancelled", DS_CONTR_DTL_STS.CANCELLED);
        if (DS_CONTR_STS.CANCELLED.equals(dsContrStsCd)) {
            ssmPrm.put("CancelledFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmPrm.put("CancelledFlg", ZYPConstant.FLG_OFF_N);
        }
        ssmPrm.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        // START 2017/06/17 K.Kojima [QC#19256,ADD]
        ssmPrm.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/17 K.Kojima [QC#19256,ADD]
        // START 2017/08/31 K.Kojima [QC#18355,ADD]
        ssmPrm.put("dsBllgSchdTpRegular", DS_BLLG_SCHD_TP.REGULAR);
        // END 2017/08/31 K.Kojima [QC#18355,ADD]
        // START 2017/09/13 K.Kim [QC#19938,ADD]
        ssmPrm.put("dsBllgSchdStsCdCl", DS_BLLG_SCHD_STS.CLOSE);
        // END 2017/09/13 K.Kim [QC#19938,ADD]
        // START 2017/09/21 K.Kitachi [QC#21222, ADD]
        // START 2023/08/18 T.Kojima [QC#60846, ADD]
        ssmPrm.put("dsContrPrcEffStsCdExpd", DS_CONTR_DTL_STS.EXPIRED);
        String[] usageDsContrPrcEffStsCdList = new String[] {DS_CONTR_DTL_STS.TERMINATED, DS_CONTR_DTL_STS.CANCELLED };
        ssmPrm.put("usageDsContrPrcEffStsCdList", usageDsContrPrcEffStsCdList);
        // END 2023/08/18 T.Kojima [QC#60846, ADD]
        String[] dsContrPrcEffStsCdList = new String[] {DS_CONTR_DTL_STS.TERMINATED, DS_CONTR_DTL_STS.EXPIRED, DS_CONTR_DTL_STS.CANCELLED };
        ssmPrm.put("dsContrPrcEffStsCdList", dsContrPrcEffStsCdList);
        // END 2017/09/21 K.Kitachi [QC#21222, ADD]
        // START 2017/09/26 K.Kojima [QC#21221,ADD]
        ssmPrm.put("forScheduleFlag", forScheduleFlag);
        // END 2017/09/26 K.Kojima [QC#21221,ADD]
        // START 2022/03/24 K.Kitachi [QC#59684, ADD]
        ssmPrm.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2022/03/24 K.Kitachi [QC#59684, ADD]
        // START 2019/01/17 [QC#29782, ADD]
        ssmPrm.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END 2019/01/17 [QC#29782, ADD]
        // START 2023/04/14 L.Borrega [QC#61370, ADD]
        ssmPrm.put("skipRecovTpNone", SKIP_RECOV_TP.NONE);
        // END 2023/04/14 L.Borrega [QC#61370, ADD]
        // START 2023/10/05 H.Inuma [QC#61675, ADD]
        ssmPrm.put("dsContrDtlStsTrmd", DS_CONTR_DTL_STS.TERMINATED);
        // END 2023/10/05 H.Inuma [QC#61675, ADD]
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            return getSsmEZDClient().queryObjectList("getDsContrDtl2_Reg", ssmPrm);
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
            return getSsmEZDClient().queryObjectList("getDsContrDtl2_Flt", ssmPrm);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
            return getSsmEZDClient().queryObjectList("getDsContrDtl2_Aggr", ssmPrm);
        } else if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            // reuse regular contract logic
            return getSsmEZDClient().queryObjectList("getDsContrDtl2_Reg", ssmPrm);
        } else {
            return null;
        }
    }
    // END 2016/02/17 T.Kanasaka [QC3023, MOD]

    public S21SsmEZDResult getContrPhysBllgMtrReln(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        // START 2017/06/17 K.Kojima [QC#19256,ADD]
        ssmPrm.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/17 K.Kojima [QC#19256,ADD]
        return getSsmEZDClient().queryObjectList("getContrPhysBllgMtrReln", ssmPrm);
    }

    public DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_CR_CARD_POTMsg getDsContrCrCardPo(String glblCmpyCd, BigDecimal dsContrCrCardPoPk) {
        DS_CONTR_CR_CARD_POTMsg prmTMsg = new DS_CONTR_CR_CARD_POTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
        return (DS_CONTR_CR_CARD_POTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_RNW_ESCLTMsg getDsContrRnwEscl(String glblCmpyCd, BigDecimal dsContrRnwEsclPk) {
        DS_CONTR_RNW_ESCLTMsg prmTMsg = new DS_CONTR_RNW_ESCLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
        return (DS_CONTR_RNW_ESCLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_DTLTMsg getDsContrDtlForRead(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    public CONTR_COV_SVCTMsg getContrCovSvc(String glblCmpyCd, BigDecimal contrCovSvcPk) {
        CONTR_COV_SVCTMsg prmTMsg = new CONTR_COV_SVCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.contrCovSvcPk, contrCovSvcPk);
        return (CONTR_COV_SVCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public CONTR_XS_COPYTMsgArray getContrXsCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg prmTMsg = new CONTR_XS_COPYTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    public S21SsmEZDResult getMdseBySerNum(String glblCmpyCd, String serNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        String[] svcMachMstrStsCdList = new String[] {SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION, SVC_MACH_MSTR_STS.INSTALLED };
        ssmPrm.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        return getSsmEZDClient().queryObjectList("getMdseBySerNum", ssmPrm);
    }

    public S21SsmEZDResult getDsContrDtlTmpl1(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return getSsmEZDClient().queryObject("getDsContrDtlTmpl1", ssmPrm);
    }

    public SELL_TO_CUSTTMsg getSellToCust(String glblCmpyCd, String sellToCustCd) {
        SELL_TO_CUSTTMsg prmTMsg = new SELL_TO_CUSTTMsg();
        prmTMsg.setSQLID("100");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    public BILL_TO_CUSTTMsg getBillToCust(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("047");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    public SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

    public GLBL_CMPYTMsg getGlblCmpy(String glblCmpyCd) {
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        return (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    public TOCTMsg getToc(String glblCmpyCd, String tocCd) {
        TOCTMsg tMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, tocCd);
        return (TOCTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    public SVC_CONTR_BRTMsg getSvcContrBr(String glblCmpyCd, String svcContrBrCd) {
        SVC_CONTR_BRTMsg tMsg = new SVC_CONTR_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, svcContrBrCd);
        return (SVC_CONTR_BRTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    // Mod Start 2018/01/12 QC#18552
    public S21SsmEZDResult getPgmMdseCdPullDownList(String glblCmpyCd, String mdseCd, String mdseDescShortTxt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        // add start 2016/07/01 CSA Defect#11261
        ssmPrm.put("mdseDescShortTxt", mdseDescShortTxt);
        // add end 2016/07/01 CSA Defect#11261
        ssmPrm.put("mdseCd", mdseCd);
        // add start 2018/08/23 QC#27790
        ssmPrm.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmPrm.put("itemTpCtxTpCd", "CPO_SVC_MDSE_QLFY_ITEM_TP");
        // add end 2018/08/23 QC#27790
        return getSsmEZDClient().queryObjectList("getPgmMdseCdPullDownList", ssmPrm);
    }
    // Mod End 2018/01/12 QC#18552

    // add start 2016/07/01 CSA Defect#11261
    public S21SsmEZDResult getSvcPgmMdseInfo(String glblCmpyCd, String svcPgmMdseCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcPgmMdseCd", svcPgmMdseCd);
        // add start 2018/08/23 QC#27790
        ssmPrm.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmPrm.put("itemTpCtxTpCd", "CPO_SVC_MDSE_QLFY_ITEM_TP");
        // add end 2018/08/23 QC#27790
        return getSsmEZDClient().queryObjectList("getSvcPgmMdseInfo", ssmPrm);
    }
    // add end 2016/07/01 CSA Defect#11261

    public DS_CONTR_DTLTMsgArray getDsContrDtlList(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        return (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // START 2018/04/05 K.Kojima [QC#21056,ADD]
    public DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrListFindByCondition(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }
    // END 2018/04/05 K.Kojima [QC#21056,ADD]

    public DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffListForBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        prmTMsg.setSQLID("004");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("dsContrPrcEffStsCd01", DS_CONTR_DTL_STS.EXPIRED);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    public DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffListForUsage(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        prmTMsg.setSQLID("005");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("dsContrPrcEffStsCd01", DS_CONTR_DTL_STS.EXPIRED);
        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//    public S21SsmEZDResult getCtacPsnPk(String glblCmpyCd, String psnNm) {
//        Map<String, Object> ssmPrm = new HashMap<String, Object>();
//        ssmPrm.put("glblCmpyCd", glblCmpyCd);
//        // mod start 2016/05/27 CSA Defect#9125
//        ssmPrm.put("ctacPsnNm", psnNm);
//        // mod end 2016/05/27 CSA Defect#9125
//        return getSsmEZDClient().queryObjectList("getCtacPsnPk", ssmPrm);
//    }
    // END 2018/06/18 K.Kitachi [QC#18591, DEL]

    public DS_CR_CARDTMsgArray getCrCardList(String glblCmpyCd, String crCardCustRefNum) {
        DS_CR_CARDTMsg prmTMsg = new DS_CR_CARDTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("crCardCustRefNum01", crCardCustRefNum);
        return (DS_CR_CARDTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }

    public DS_CR_CARDTMsg getDsCrCard(String glblCmpyCd, BigDecimal dsCrCardPk) {
        DS_CR_CARDTMsg prmTMsg = new DS_CR_CARDTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsCrCardPk, dsCrCardPk);
        return (DS_CR_CARDTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    public DS_CONTR_ACTTMsg getDsContrAct(String glblCmpyCd, String dsContrActCd) {
        DS_CONTR_ACTTMsg prmTMsg = new DS_CONTR_ACTTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrActCd, dsContrActCd);
        return (DS_CONTR_ACTTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    public DS_CONTR_STS_VTMsg getDsContrStsV(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_STS_VTMsg prmTMsg = new DS_CONTR_STS_VTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_STS_VTMsgArray tMsgArray = (DS_CONTR_STS_VTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    public SELL_TO_CUSTTMsgArray getSellToCustList(String glblCmpyCd, String dsAcctNum) {
        SELL_TO_CUSTTMsg prmTMsg = new SELL_TO_CUSTTMsg();
        prmTMsg.setSQLID("100");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }

    // START 2016/01/21 T.Tomita [QC#2182, ADD]
    public S21SsmEZDResult getBranch(String glblCmpyCd, String svcContrBrCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcContrBrCd", svcContrBrCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("defEffThruDt", DEF_EFF_THRU_DT);
        return getSsmEZDClient().queryObjectList("getBranch", ssmPrm);
    }

    public S21SsmEZDResult getContrAdminPsnCd(String glblCmpyCd, String xxPsnNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("xxPsnNm", xxPsnNm);
        return getSsmEZDClient().queryObjectList("getContrAdminPsnCd", ssmPrm);
    }

    public S21SsmEZDResult countSvcBrResrcReln(String glblCmpyCd, String svcContrBrCd, String psnCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcContrBrCd", svcContrBrCd);
        ssmPrm.put("psnCd", psnCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("defEffThruDt", DEF_EFF_THRU_DT);
        return getSsmEZDClient().queryObject("countSvcBrResrcReln", ssmPrm);
    }
    // START 2016/01/21 T.Tomita [QC#2182, ADD]

    public S21SsmEZDResult getRepInfo(String glblCmpyCd, String dsAcctNum, String svcLineBizCd, String svcContrBrCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsAcctNum", dsAcctNum);
        ssmPrm.put("svcLineBizCd", svcLineBizCd);
        ssmPrm.put("svcContrBrCd", svcContrBrCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("defEffThruDt", DEF_EFF_THRU_DT);
        ssmPrm.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        return getSsmEZDClient().queryObjectList("getRepInfo", ssmPrm);
    }

    public S21SsmEZDResult getSalesRepInfo(String glblCmpyCd, String tocCd, String tocNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("tocCd", tocCd);
        ssmPrm.put("tocNm", tocNm);
        ssmPrm.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return getSsmEZDClient().queryObjectList("getSalesRepInfo", ssmPrm);
    }

    public S21SsmEZDResult getCtacPsnReln(String glblCmpyCd, BigDecimal ctacPsnPk, String billToCustCd) {
        // START 2018/04/04 U.Kim [QC#23559(Sol358), ADD]
        String [] ctacTpList = {CTAC_TP.BILL_TO_CONTACT, CTAC_TP.BILL_TO_CONTACT_CONTRACTS};
        // END 2018/04/04 U.Kim [QC#23559(Sol358), ADD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("ctacPsnPk", ctacPsnPk);
        ssmPrm.put("billToCustCd", billToCustCd);
        // START 2018/04/04 U.Kim [QC#23559(Sol358), MOD]
        // ssmPrm.put("ctacTpCd", CTAC_TP.BILL_TO_CONTACT);
        ssmPrm.put("ctacTpList", ctacTpList);
        // END 2018/04/04 U.Kim [QC#23559(Sol358), MOD]
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("defEffThruDt", DEF_EFF_THRU_DT);
        return getSsmEZDClient().queryObjectList("getCtacPsnReln", ssmPrm);
    }

    public S21SsmEZDResult getInvoicedContrInfo(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getInvoicedContrInfo", ssmPrm);
    }

    public CONTR_UPLFT_TPTMsg getUplftTp(String glblCmpyCd, String contrUplftTpCd) {
        CONTR_UPLFT_TPTMsg prmTMsg = new CONTR_UPLFT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.contrUplftTpCd, contrUplftTpCd);
        return (CONTR_UPLFT_TPTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    public SVC_MEMOTMsg getSvcMemoForQAHld(String glblCmpyCd, BigDecimal dsContrPk, String lastUpdTs) {
        SVC_MEMOTMsg prmTMsg = new SVC_MEMOTMsg();
        prmTMsg.setSQLID("005");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        prmTMsg.setConditionValue("svcMemoCatgCd01", SVC_MEMO_CATG.CONTRACT_MEMO);
        prmTMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.CONTRACT_NOTE);
        prmTMsg.setConditionValue("lastUpdTs01", lastUpdTs);
        SVC_MEMOTMsgArray tMsgArray = (SVC_MEMOTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    // START 2015/12/25 T.Tomita [QC#2133, ADD]
    public PMT_TERM_CASH_DISCTMsg getPmtTermCashDisc(String glblCmpyCd, String pmtTermCashDiscCd) {
        PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
        return (PMT_TERM_CASH_DISCTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }
    // END 2015/12/25 T.Tomita [QC#2133, ADD]

    // START 2016/02/16 T.Aoyagi [QC2947, ADD]
    public S21SsmEZDResult getCustPmtTerm(String glblCmpyCd, String billToCustCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryObject("getCustPmtTerm", ssmPrm);
    }

    public S21SsmEZDResult getAcctPmtTerm(String glblCmpyCd, String billToCustCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryObject("getAcctPmtTerm", ssmPrm);
    }
    // END 2016/02/16 T.Aoyagi [QC2947, ADD]

    // START 2016/02/26 T.Kanasaka [QC4092, ADD]
    public S21_PSNTMsg getS21Psn(String glblCmpyCd, String psnCd) {
        S21_PSNTMsg prmTMsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.psnCd, psnCd);
        return (S21_PSNTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // END 2016/02/26 T.Kanasaka [QC4092, ADD]

    // add start 2016/04/07 Defect#5312,5313
    /**
     * Find Branch Name Info By Service Contract Branch Code
     * @param glblCmpyCd String
     * @param lob String
     * @param branchName String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findBranchNameInfo(String glblCmpyCd, String lob, String branchName) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("lob", lob);
        ssmPrm.put("branchName", branchName);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        return getSsmEZDClient().queryObjectList("findBranchNameInfo", ssmPrm);
    }

    /**
     * Find Representative Info By PSN Code
     * @param glblCmpyCd String
     * @param firstNm String
     * @param lastNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRepNameInfo(String glblCmpyCd, String firstNm, String lastNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("firstName", firstNm);
        ssmPrm.put("lastName", lastNm);
        // START 2019/02/12 S.Kitamura [QC#30264, DEL]
        // ssmPrm.put("psnTpCd", PSN_TP.EMPLOYEE);
        // END 2019/02/12 S.Kitamura [QC#30264, DEL]
        return getSsmEZDClient().queryObjectList("findRepNameInfo", ssmPrm);
    }
    // add end 2016/04/07 Defect#5312,5313
    // START 2016/04/26 T.Tomita [QC#3886, ADD]
    /**
     * Find Sell To Customer
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSellToCust(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("findSellToCust", ssmPrm);
    }

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    /**
     * Find Ship To Customer
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findShipToCust(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("shipToCustCd", shipToCustCd);
        return getSsmEZDClient().queryObjectList("findShipToCust", ssmPrm);
    }
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

    /**
     * Find Bill To Customer
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findBillToCust(String glblCmpyCd, String billToCustCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryObjectList("findBillToCust", ssmPrm);
    }
    // END 2016/04/26 T.Tomita [QC#3886, ADD]
    // START 2016/04/26 T.Tomita [QC#4668, ADD]
    /**
     * Find TOC Info
     * @param glblCmpyCd String
     * @param tocNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findTocInfo(String glblCmpyCd, String tocNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("tocNm", tocNm);
        ssmPrm.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        return getSsmEZDClient().queryObjectList("findTocInfo", ssmPrm);
    }

    // START 2018/06/18 K.Kim [QC#25195, MOD]
    /**
     * Get TOC Info
     * @param glblCmpyCd String
     * @param tocCd String
     * @param tocNm String
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult getTocInfo(String glblCmpyCd, String tocCd) {
    public S21SsmEZDResult getTocInfo(String glblCmpyCd, String tocCd, String tocNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("tocCd", tocCd);
        ssmPrm.put("tocNm", tocNm);
        ssmPrm.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        return getSsmEZDClient().queryObjectList("getTocInfo", ssmPrm);
    }
    // END 2016/04/26 T.Tomita [QC#4668, ADD]
    // END 2018/06/18 K.Kim [QC#25195, MOD]

    // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
    /**
     * Get BLLG_CYCLE
     * @param glblCmpyCd String
     * @param bllgCycleCd String
     * @return BLLG_CYCLETMsg
     */
    public BLLG_CYCLETMsg getBllgCycle(String glblCmpyCd, String bllgCycleCd) {
        BLLG_CYCLETMsg prmTMsg = new BLLG_CYCLETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.bllgCycleCd, bllgCycleCd);
        return (BLLG_CYCLETMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // END 2016/05/17 T.Kanasaka [QC#2184, ADD]

    // START 2016/05/20 T.Kanasaka [QC#5942, ADD]
    /**
     * Get Fleet Machine (No Schedule)
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFleetMachineNoSchedule(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrStsCd_Cancel", DS_CONTR_DTL_STS.CANCELLED);
        ssmPrm.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        // START 2017/06/17 K.Kojima [QC#19256,ADD]
        ssmPrm.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/17 K.Kojima [QC#19256,ADD]
        ssmPrm.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        return getSsmEZDClient().queryObjectList("getFleetMachineNoSchedule", ssmPrm);
    }
    // END 2016/05/20 T.Kanasaka [QC#5942, ADD]

    // START 2017/09/19 K.Kitachi [QC#21149, ADD]
    /**
     * Get Aggregate Machine (No Schedule)
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAggregateMachineNoSchedule(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrStsCd_Cancel", DS_CONTR_DTL_STS.CANCELLED);
        ssmPrm.put("dsContrDtlTpCd_Agg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmPrm.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        ssmPrm.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        return getSsmEZDClient().queryObjectList("getAggregateMachineNoSchedule", ssmPrm);
    }
    // END 2017/09/19 K.Kitachi [QC#21149, ADD]

    // START 2016/05/30 K.Kasai [QC#447, ADD]
    /**
     * Get Term and Condition Update Target
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTermCondUpdTrgt (String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        String[] dsContrDtlTpList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE };
        ssmPrm.put("dsContrDtlTpList", dsContrDtlTpList);
        String[] dsContrCtrlStsCd = new String[] {DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.EXPIRED };
        ssmPrm.put("dsContrCtrlStsList", dsContrCtrlStsCd);
        return getSsmEZDClient().queryObjectList("getTermCondUpdTrgt", ssmPrm);
    }

    public SVC_TERM_COND_ATTRBTMsgArray getSvcTermCondAttrb(String glblCmpyCd, String svcTermCondAttrbNm) {
        SVC_TERM_COND_ATTRBTMsg prmTMsg = new SVC_TERM_COND_ATTRBTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("svcTermCondAttrbNm01", svcTermCondAttrbNm);
        return (SVC_TERM_COND_ATTRBTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }

    public SVC_TERM_CONDTMsgArray getSvcTermCond(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal svcTermCondAttrbPk) {
        SVC_TERM_CONDTMsg prmTMsg = new SVC_TERM_CONDTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("svcTermCondAttrbPk01", svcTermCondAttrbPk);
        return (SVC_TERM_CONDTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }
    // END 2016/05/30 K.Kasai [QC#447, ADD]

    // START 2016/06/20 T.Kanasaka [QC#9019, ADD]
    /**
     * Get CONTR_PHYS_BLLG_MTR_RELN
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return CONTR_PHYS_BLLG_MTR_RELNTMsgArray
     */
    public CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrRelnByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg prmTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * Get DS_CONTR_ADDL_CHRG
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_ADDL_CHRGTMsgArray
     */
    public DS_CONTR_ADDL_CHRGTMsgArray getDsContrAddlChrgByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_ADDL_CHRGTMsg prmTMsg = new DS_CONTR_ADDL_CHRGTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_ADDL_CHRGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * Get DS_CONTR_BR_ALLOC
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_BR_ALLOCTMsgArray
     */
    public DS_CONTR_BR_ALLOCTMsgArray getDsContrBrAllocByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BR_ALLOCTMsg prmTMsg = new DS_CONTR_BR_ALLOCTMsg();
        prmTMsg.setSQLID("004");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * Get DS_CONTR_SEG_ALLOC
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_SEG_ALLOCTMsgArray
     */
    public DS_CONTR_SEG_ALLOCTMsgArray getDsContrSegAllocByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_SEG_ALLOCTMsg prmTMsg = new DS_CONTR_SEG_ALLOCTMsg();
        prmTMsg.setSQLID("004");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_SEG_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * Get DS_CONTR_PRC_ALLOC
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_PRC_ALLOCTMsgArray
     */
    public DS_CONTR_PRC_ALLOCTMsgArray getDsContrPrcAllocByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_ALLOCTMsg prmTMsg = new DS_CONTR_PRC_ALLOCTMsg();
        prmTMsg.setSQLID("005");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_PRC_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * Get DS_CONTR_CR_CARD_PO
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_CR_CARD_POTMsgArray
     */
    public DS_CONTR_CR_CARD_POTMsgArray getDsContrCrCardPoByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_CR_CARD_POTMsg prmTMsg = new DS_CONTR_CR_CARD_POTMsg();
        prmTMsg.setSQLID("003");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_CR_CARD_POTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * Get DS_CONTR_RNW_ESCL
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_RNW_ESCLTMsgArray
     */
    public DS_CONTR_RNW_ESCLTMsgArray getDsContrRnwEsclByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_RNW_ESCLTMsg prmTMsg = new DS_CONTR_RNW_ESCLTMsg();
        prmTMsg.setSQLID("003");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_RNW_ESCLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * Get DS_SUB_CONTR
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_SUB_CONTRTMsgArray
     */
    public DS_SUB_CONTRTMsgArray getDsSubContrByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_SUB_CONTRTMsg prmTMsg = new DS_SUB_CONTRTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_SUB_CONTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // START 2018/12/13 [QC#29079, ADD]
    /**
     * Get DS_SUB_CONTR_MTR
     * @param glblCmpyCd String
     * @param dsSubContrPk BigDecimal
     * @return DS_SUB_CONTR_MTRTMsgArray
     */
    public DS_SUB_CONTR_MTRTMsgArray getDsSubContrMtrByDsSubContrPk(String glblCmpyCd, BigDecimal dsSubContrPk) {
        DS_SUB_CONTR_MTRTMsg prmTMsg = new DS_SUB_CONTR_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsSubContrPk01", dsSubContrPk);
        return (DS_SUB_CONTR_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }
    // END 2018/12/13 [QC#29079, ADD]

    /**
     * Get SVC_TERM_COND
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return SVC_TERM_CONDTMsgArray
     */
    public SVC_TERM_CONDTMsgArray getSvcTermCondByDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        SVC_TERM_CONDTMsg prmTMsg = new SVC_TERM_CONDTMsg();
        prmTMsg.setSQLID("005");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (SVC_TERM_CONDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }
    // END 2016/06/20 T.Kanasaka [QC#9019, ADD]

    // add start 2016/06/21 CSA Defect#6923
    /**
     * Get Contr Auto Rnw Tp Cd
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrAutoRnwTpCd(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        // START 2017/06/17 K.Kojima [QC#19256,ADD]
        ssmPrm.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/17 K.Kojima [QC#19256,ADD]
        return getSsmEZDClient().queryObjectList("getContrAutoRnwTpCd", ssmPrm);
    }
    // add end 2016/06/21 CSA Defect#6923

    // START 2016/07/06 T.Kanasaka [QC#9019, ADD]
    /**
     * Get DS_CONTR_CR_CARD_PO
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_CR_CARD_POTMsgArray
     */
    public DS_CONTR_CR_CARD_POTMsgArray getDsContrCrCardPoForMtr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_CR_CARD_POTMsg prmTMsg = new DS_CONTR_CR_CARD_POTMsg();
        prmTMsg.setSQLID("005");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        return (DS_CONTR_CR_CARD_POTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }
    // END 2016/07/07 T.Kanasaka [QC#9019, ADD]

    // Add Start 08/02/2016 <QC#7402>
    /**
     * Get DS_CONTR_CR_CARD_PO
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getInvoicedBaseTermAmtRate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("closeSts", DS_BLLG_SCHD_STS.CLOSE);
        // Add Start 2017/11/27 QC#21724
        ssmPrm.put("invoice", INV_TP.INVOICE);
        ssmPrm.put("creditMemo", INV_TP.CREDIT_MEMO);
        // Add End 2017/11/27 QC#21724
        BigDecimal invoicedBaseTermAmtRate = (BigDecimal)getSsmEZDClient().queryObject("getInvoicedBaseTermAmtRate", ssmPrm).getResultObject();
        if (invoicedBaseTermAmtRate == null) {
            invoicedBaseTermAmtRate = BigDecimal.ZERO;
        }
        return invoicedBaseTermAmtRate;
    }
    // Add End   08/02/2016 <QC#7402>

    // add start 2016/09/06 CSA Defect#11243
    /**
     * getDsCrCard
     * @param glblCmpyCd String
     * @param crCardCustRefNum String
     * @param sellToCustCd String
     * @return DS_CR_CARDTMsg
     */
    public DS_CR_CARDTMsg getDsCrCard(String glblCmpyCd, String crCardCustRefNum, String sellToCustCd) {
        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
        dsCrCardTMsg.setSQLID("002");
        dsCrCardTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCrCardTMsg.setConditionValue("crCardCustRefNum01", crCardCustRefNum);
        dsCrCardTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        DS_CR_CARDTMsgArray dsCrCardResult = (DS_CR_CARDTMsgArray) EZDTBLAccessor.findByCondition(dsCrCardTMsg);

        if (dsCrCardResult == null || dsCrCardResult.length() == 0) {
            return null;
        }
        return dsCrCardResult.no(0);
    }

    /**
     * getCrCardDiffData
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getCrCardDiffData(String glblCmpyCd, BigDecimal dsContrPk) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("crCardTrxTp", CR_CARD_TRX_TP.CONTRACT_HEADER);
        params.put("firstTrxInfoNum", dsContrPk);
        params.put("crCardAuthStsSaved", CR_CARD_AUTH_STS.SAVED);
        params.put("dsContrMachLvlNum", MACH_LVL_NUM_1);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getCrCardDiffData", params).getResultObject();
    }

    /**
     * getCrCardTrxPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getCrCardTrxPk(String glblCmpyCd, BigDecimal dsContrPk) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("crCardTrxTp", CR_CARD_TRX_TP.CONTRACT_HEADER);
        params.put("firstTrxInfoNum", dsContrPk);
        params.put("crCardAuthStsSaved", CR_CARD_AUTH_STS.SAVED);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getCrCardTrxPk", params).getResultObject();
    }

    /**
     * getCrCardTrx
     * @param glblCmpyCd String
     * @param crCardTrxPk BigDecimal
     * @return DS_CR_CARDTMsg
     */
    public CR_CARD_TRXTMsg getCrCardTrx(String glblCmpyCd, BigDecimal crCardTrxPk) {
        CR_CARD_TRXTMsg prmTMsg = new CR_CARD_TRXTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.crCardTrxPk, crCardTrxPk);
        return (CR_CARD_TRXTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // add end 2016/09/06 CSA Defect#11243

    // add start 2016/11/22 CSA Defect#16114
    /**
     * getBllgSchdCloseCount
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getBllgSchdCloseCount(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsBllgSchdStsClose", DS_BLLG_SCHD_STS.CLOSE);
        return (BigDecimal) getSsmEZDClient().queryObject("getBllgSchdCloseCount", ssmPrm).getResultObject();
    }
    // add end 2016/11/22 CSA Defect#16114

 // START 2017/01/24 N.Arai [QC#17228, MOD]
    /**
     * findRepNameInfoFromPsnCd
     * @param glblCmpyCd String
     * @param psnCd String
     * @return String
     */
    public String findRepNameInfoFromPsnCd(String glblCmpyCd, String psnCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("psnCd", psnCd);
        // START 2019/02/12 S.Kitamura [QC#30264, DEL]
        // ssmPrm.put("psnTpCd", PSN_TP.EMPLOYEE);
        // END 2019/02/12 S.Kitamura [QC#30264, DEL]
        return (String) getSsmEZDClient().queryObject("findRepNameInfoFromPsnCd", ssmPrm).getResultObject();
    }
 // END 2017/01/24 N.Arai [QC#17228, MOD]

    // START 2018/06/18 K.Kim [QC#25195, MOD]
    // START 2017/01/27 [QC#17278, ADD]
    /**
     * findDsContrRptGrp
     * @param glblCmpyCd String
     * @param dsContrRptGrpDescTxt String
     * @param dsContrRptGrpNum String
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult findDsContrRptGrp(String glblCmpyCd, String dsContrRptGrpDescTxt) {
    public S21SsmEZDResult findDsContrRptGrp(String glblCmpyCd, String dsContrRptGrpDescTxt, String dsContrRptGrpNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrRptGrpDescTxt", dsContrRptGrpDescTxt);
        ssmPrm.put("dsContrRptGrpNum", dsContrRptGrpNum);
        return getSsmEZDClient().queryObjectList("findDsContrRptGrp", ssmPrm);
    }
    // END   2017/01/27 [QC#17278, ADD]
    // END 2018/06/18 K.Kim [QC#25195, ADD]

 // START 2017/02/02 N.Arai [QC#17197, MOD]
    public ResultSet getDsContrDtlInfo4Download(PreparedStatement stmtSelect, String glblCmpyCd, BigDecimal dsContrPk, String dsContrCatgCd, int rowNum) throws SQLException {
        // START 2018/02/19 K.Kojima [QC#24145,ADD]
        String activePendingPOContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_PENDING_PO_CONTR_STS_NM, glblCmpyCd);
        String activeRenewalHoldContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_RENEWAL_HOLD_CONTR_STS_NM, glblCmpyCd);
        String pndIstlContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(PND_ISTL_CONTR_STS_NM, glblCmpyCd);
        // END 2018/02/19 K.Kojima [QC#24145,ADD]
        // START 2022/06/17 K.Kitachi [QC#60197, ADD]
        String allPerTrmnContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ALL_PER_TRMN_CONTR_STS_NM, glblCmpyCd);
        // END 2022/06/17 K.Kitachi [QC#60197, ADD]

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("rowNum", rowNum);
        ssmPrm.put("dsContrDtlTpCd_Accessory", DS_CONTR_DTL_TP.ACCESSORIES);
        // add start 2017/06/06 CSA Defect#18845
        ssmPrm.put("svcPrcFlg", ZYPConstant.FLG_OFF_N);
        // add end 2017/06/06 CSA Defect#18845
        // START 2023/12/21 t.aizawa [QC#63377,ADD]
        ssmPrm.put("skip", SKIP_RECOV_TP.SKIP);
        // END   2023/12/21 t.aizawa [QC#63377,ADD]
        // START 2017/09/26 K.Kojima [QC#21221,ADD]
        ssmPrm.put("dsBllgSchdTpRegular", DS_BLLG_SCHD_TP.REGULAR);
        // END 2017/09/26 K.Kojima [QC#21221,ADD]
        // START 2018/02/19 K.Kojima [QC#24105,ADD]
        ssmPrm.put("activePendingPO", activePendingPOContrStsNm);
        ssmPrm.put("activeRenewalHold", activeRenewalHoldContrStsNm);
        ssmPrm.put("dsContrCtrlStsCdActive", DS_CONTR_CTRL_STS.ACTIVE);
        ssmPrm.put("dsContrCtrlStsCdRenewalHold", DS_CONTR_CTRL_STS.RENEWAL_HOLD);
        ssmPrm.put("dsContrCtrlStsCdRenewalHoldForPO", DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO);
        ssmPrm.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
        ssmPrm.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        ssmPrm.put("pendingInstallation", pndIstlContrStsNm);
        ssmPrm.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        // END 2018/02/19 K.Kojima [QC#24105,ADD]
        // START 2022/06/17 K.Kitachi [QC#60197, ADD]
        ssmPrm.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        ssmPrm.put("allPeriodTermination", allPerTrmnContrStsNm);
        // END 2022/06/17 K.Kitachi [QC#60197, ADD]
        // START 2019/01/17 [QC#29782, ADD]
        ssmPrm.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // END 2019/01/17 [QC#29782, ADD]
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            stmtSelect = ssmLLClient.createPreparedStatement("getDsContrDtl1_Reg", ssmPrm, execParam);
            return stmtSelect.executeQuery();
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
            stmtSelect = ssmLLClient.createPreparedStatement("getDsContrDtl1_Flt", ssmPrm, execParam);
            return stmtSelect.executeQuery();
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            ssmPrm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
            stmtSelect = ssmLLClient.createPreparedStatement("getDsContrDtl1_Aggr", ssmPrm, execParam);
            return stmtSelect.executeQuery();
        } else if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            // reuse regular contract logic
            stmtSelect = ssmLLClient.createPreparedStatement("getDsContrDtl1_Reg", ssmPrm, execParam);
            return stmtSelect.executeQuery();
        } else {
            return null;
        }
    }
 // END 2017/02/02 N.Arai [QC#17197, MOD]

    // START 2017/02/06 [QC#17275, ADD]
    /**
     * getDsContrBllgMtrTmpl
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrBllgMtrTmpl(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("mtrEntryMndFlg", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("maxDt", DEF_EFF_THRU_DT);
        ssmPrm.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getDsContrBllgMtrTmpl", ssmPrm);
    }

    /**
     * getSvcPhysMtrList
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return S21SsmEZDResult
     */
    // START 2017/02/14 [QC#17275-1, MOD]
    // public S21SsmEZDResult getSvcPhysMtrList(String glblCmpyCd, BigDecimal svcMachMstrPk, String bllgMtrLbCd) {
    public S21SsmEZDResult getSvcPhysMtrList(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
    // END   2017/02/14 [QC#17275-1, MOD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("maxDt", DEF_EFF_THRU_DT);
        ssmPrm.put("actvFlg", ZYPConstant.FLG_ON_Y);
        // START 2017/08/25 U.Kim [QC#20728, ADD]
        ssmPrm.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        // END 2017/08/25 U.Kim [QC#20728, ADD]

        return getSsmEZDClient().queryObjectList("getSvcPhysMtrList", ssmPrm);
    }

    /**
     * getContrPhysBllgMtrRelnArray
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return SVC_PHYS_MTRTMsgArray
     */
    public CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrRelnArray(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * getMtrLbTMsg
     * @param glblCmpyCd String
     * @param mtrLbCd String
     * @return MTR_LBTMsg
     */
    public MTR_LBTMsg getMtrLbTMsg(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mtrLbCd, mtrLbCd);
        return (MTR_LBTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END   2017/02/06 [QC#17275, ADD]

    // START 2017/02/10 [QC#17494, ADD]
    /**
     * getContrPhysBllgMtrRelnTmpl
     * 
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrPhysBllgMtrRelnTmpl(String glblCmpyCd, BigDecimal svcMachMstrPk, String bllgMtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("maxDt", DEF_EFF_THRU_DT);
        ssmPrm.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getContrPhysBllgMtrRelnTmpl", ssmPrm);
    }
    // END   2017/02/10 [QC#17494, ADD]

    // START 2017/06/22 [QC#17496, ADD]
    /**
     * getSellToCustFromBillToCustCd
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSellToCustFromBillToCustCd(String glblCmpyCd, String billToCustCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("billToCustCd", billToCustCd);
        ssmPrm.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return getSsmEZDClient().queryObjectList("getSellToCustFromBillToCustCd", ssmPrm);
    }
    // END  2017/06/22 [QC#17496, ADD]

    // START 2017/07/20 M.Kidokoro [QC#4468, ADD]
    /**
     * getDsContrActCd
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsContrActCd(String glblCmpyCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        return getSsmEZDClient().queryObjectList("getDsContrActCd", ssmPrm);
    }
    // END 2017/07/20 M.Kidokoro [QC#4468, ADD]

    // add start 2017/08/08 QC#18799
    /**
     * getDefRule
     * @param glblCmpyCd String
     * @param dsContrClsCd String
     * @param svcLineBizCd String
     * @return DS_CONTR_INTFC_DEF_RULETMsg
     */
    public DS_CONTR_INTFC_DEF_RULETMsg getDsContrIntfcDefRule(String glblCmpyCd, String dsContrClsCd, String svcLineBizCd) {
        DS_CONTR_INTFC_DEF_RULETMsg param = new DS_CONTR_INTFC_DEF_RULETMsg();
        param.setSQLID("202");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("contrIntfcSrcTpCd01", DS_CONTR_SRC_TP.CONTRACT_MAINTENACE);
        param.setConditionValue("dsContrClsCd01", dsContrClsCd);
        param.setConditionValue("svcLineBizCd01", svcLineBizCd);
        param.setConditionValue("effFromDt01", ZYPDateUtil.getSalesDate(glblCmpyCd));
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        DS_CONTR_INTFC_DEF_RULETMsgArray list = (DS_CONTR_INTFC_DEF_RULETMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() == 0) {
            return null;
        }
        return (DS_CONTR_INTFC_DEF_RULETMsg) list.get(0);
    }
    // add end 2017/08/08 QC#18799

    // START 2017/09/11 K.Kojima [QC#18435,ADD]
    /**
     * getDsContrPrcEffForTracking
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @param dsContrBllgMtrPk
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDsContrPrcEffForTracking(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsContrPrcEffForTracking", param).getResultObject();
    }
    // END 2017/09/11 K.Kojima [QC#18435,ADD]

    // START 2017/09/21 K.Kitachi [QC#21222, ADD]
    /**
     * getMinUnbilledFromDt
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @return String
     */
    public String getMinUnbilledFromDt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getMinUnbilledFromDt", param).getResultObject();
    }
    // END 2017/09/21 K.Kitachi [QC#21222, ADD]

    // START 2017/10/05 K.Kojima [QC#20523,ADD]
    /**
     * getRenewalHoldforPoReleaseDsContrPrcEffPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRenewalHoldforPoReleaseDsContrPrcEffPk(String glblCmpyCd, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("renewalHoldForPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        params.put("dsContrMachLvlNum1", LVL_NUM_1);
        params.put("dsContrMachLvlNum2", LVL_NUM_2);
        params.put("dsContrMachLvlNum3", LVL_NUM_3);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getRenewalHoldforPoReleaseDsContrPrcEffPk", params);
    }

    /**
     * getRenewalHoldforPoReleaseDsContrDtlPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRenewalHoldforPoReleaseDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("renewalHoldForPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        return getSsmEZDClient().queryObjectList("getRenewalHoldforPoReleaseDsContrDtlPk", params);
    }
    // END 2017/10/05 K.Kojima [QC#20523,ADD]

    // START 2017/10/18 K.Kitachi [QC#21222, ADD]
    /**
     * getInvoicedTermAmountForBase
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getInvoicedTermAmountForBase(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getInvoicedTermAmountForBase", param).getResultObject();
    }
    // END 2017/10/18 K.Kitachi [QC#21222, ADD]
    // Add Start 2017/12/21 QC#18779
    
    /**
     * getBllgDayTp
     * @param glblCmpyCd String
     * @param baseChrgFlg String
     * @param usgChrgFlg String
     * @param bllgTmgTpCd String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getBllgDayTp(String glblCmpyCd, String baseChrgFlg, String usgChrgFlg, String bllgTmgTpCd) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("baseChrgFlg", baseChrgFlg);
        params.put("usgChrgFlg", usgChrgFlg);
        params.put("bllgTmgTpCd", bllgTmgTpCd);
        params.put("lastDaysCd", NSAL0300Constant.LAST_DAY_OF_A_MONTH);
        params.put("lastDaysNm", NSAL0300Constant.LAST_DAY);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgDayTp", params).getResultObject();
    }
    // Add End 2017/12/21 QC#18779

    // START 2018/04/03 K.Kojima [QC#21056,ADD]
    /**
     * countBllgMtr
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countBllgMtr", param).getResultObject();
    }

    /**
     * getInsertBllgMtrInfo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param dsContrDtlTp String
     * @param svcMachMstrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getInsertBllgMtrInfo(String glblCmpyCd, BigDecimal dsContrPk, String dsContrDtlTp, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTp", dsContrDtlTp);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        param.put("maxDt", DEF_EFF_THRU_DT);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getInsertBllgMtrInfo", param).getResultObject();
    }

    /**
     * getInsertRelnInfo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param bllgMtrLbCd String
     * @param mdlMtrLbCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getInsertRelnInfo(String glblCmpyCd, BigDecimal dsContrPk, String bllgMtrLbCd, String mdlMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("bllgMtrLbCd", bllgMtrLbCd);
        param.put("mdlMtrLbCd", mdlMtrLbCd);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getInsertRelnInfo", param).getResultObject();
    }

    /**
     * getPhysMtrNoReln
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getPhysMtrNoReln(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getPhysMtrNoReln", param).getResultObject();
    }
    // END 2018/04/03 K.Kojima [QC#21056,ADD]

    // START 2018/06/05 K.Kim [QC#24851, ADD]
    /**
     * countDsContrPrcEff
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countDsContrPrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);
        List<String> dsContrDtlStsCdList = new ArrayList<String>();
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        ssmPrm.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return (BigDecimal) getSsmEZDClient().queryObject("countDsContrPrcEff", ssmPrm).getResultObject();
    }
    // END 2018/06/05 K.Kim [QC#24851, ADD]

    // START 2018/07/02 K.Kitachi [QC#26765, ADD]
    /**
     * countLeaseDlrMap
     * @param glblCmpyCd String
     * @param leaseCmpyCd String
     * @return BigDecimal
     */
    public BigDecimal countLeaseDlrMap(String glblCmpyCd, String leaseCmpyCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("leaseCmpyCd", leaseCmpyCd);
        return (BigDecimal) getSsmEZDClient().queryObject("countLeaseDlrMap", ssmPrm).getResultObject();
    }

    /**
     * countLeaseCmpy
     * @param glblCmpyCd String
     * @param leaseCmpyCd String
     * @return BigDecimal
     */
    public BigDecimal countLeaseCmpy(String glblCmpyCd, String leaseCmpyCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("leaseCmpyCd", leaseCmpyCd);
        return (BigDecimal) getSsmEZDClient().queryObject("countLeaseCmpy", ssmPrm).getResultObject();
    }
    // END 2018/07/02 K.Kitachi [QC#26765, ADD]
    // Add Start 2018/08/20 QC#26946
    /**
     * getMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    public MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        return (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
    }
    // Add End 2018/08/20 QC#26946

    // START 2018/09/20 K.Kitachi [QC#28328, ADD]
    /**
     * getMainUnitDtlTpList
     * @param glblCmpyCd String
     * @return List<String>
     */
    public List<String> getMainUnitDtlTpList(String glblCmpyCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        return (List<String>) getSsmEZDClient().queryObjectList("getMainUnitDtlTpList", ssmPrm).getResultObject();
    }
    // END 2018/09/20 K.Kitachi [QC#28328, ADD]
    

    // add start 2018/11/07 K.Fujimoto QC#28627
    /**
     * getDsContr
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContr(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrNum01", dsContrNum);
        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (dsContrTMsgArray == null || dsContrTMsgArray.getValidCount() == 0) {
            return null;
        }
        return dsContrTMsgArray.no(0);
    }
    
    /**
     * countChildDsContr
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @param contrLinkNum String
     * @return BigDecimal
     */
    public BigDecimal countChildDsContr(String glblCmpyCd, String dsContrNum,String contrLinkNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("contrLinkNum", contrLinkNum);
        ssmPrm.put("dsContrNum", dsContrNum);
        return (BigDecimal) getSsmEZDClient().queryObject("countChildDsContr", ssmPrm).getResultObject();
    }
    // add end   2018/11/07 K.Fujimoto QC#28627

    // START 2019/01/17 [QC#29782, ADD]
    /**
     * getPrntSvcPgmMdseCd
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @return String
     */
    public String getPrntSvcPgmMdseCd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (String) getSsmEZDClient().queryObject("getPrntSvcPgmMdseCd", ssmPrm).getResultObject();
    }
    // END 2019/01/17 [QC#29782, ADD]

    // START 2019/01/17 M.Naito [QC#29083,ADD]
//    /**
//     * isExistInvoicedBllg
//     * @param glblCmpyCd String
//     * @param dsContrBllgMtrPk dsContrBllgMtrPk
//     * @return BigDecimal
//     */
//    public BigDecimal isExistInvoicedBllg(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
//        Map<String, Object> ssmPrm = new HashMap<String, Object>();
//        ssmPrm.put("glblCmpyCd", glblCmpyCd);
//        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
//        return (BigDecimal) getSsmEZDClient().queryObject("isExistInvoicedBllg", ssmPrm).getResultObject();
//    }
    /**
     * getUninvicedSchedule
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk dsContrBllgMtrPk
     * @return Map<String, Object>
     */
    public Map<String, Object> getUninvicedSchedule(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getUninvicedSchedule", ssmPrm).getResultObject();
    }
    // END 2019/01/17 M.Naito [QC#29083,ADD]

    // QC#55590 Add Start
    /**
     * getDsPoReqFlg
     */
    public String getDsPoReqFlg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsTrxRuleTpCd", DS_TRX_RULE_TP.CONTRACT);
        return (String) getSsmEZDClient().queryObject("getDsPoReqFlg", ssmPrm).getResultObject();
    }
    /**
     * getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo
     * @param glblCmpyCd
     * @param dsContrPk
     * @return
     */
    public S21SsmEZDResult getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo(String glblCmpyCd, BigDecimal dsContrPk) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("renewalHoldForPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
        return getSsmEZDClient().queryObjectList("getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo", params);
    }
    // QC#55590 Add End
    
    // QC#59240 Start
    public String getDtAfterMaxInvThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getDtAfterMaxInvThruDt", param).getResultObject();
    }
    public BigDecimal getTotalAmtOfUninvBefFinInv(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("none", SKIP_RECOV_TP.NONE);
        return (BigDecimal) getSsmEZDClient().queryObject("getTotalAmtOfUninvBefFinInv", param).getResultObject();
    }
    // QC#59240 End

    // START 2022/03/22 [QC#59683, ADD]
    public DS_INV_TGTR_TPTMsg getDsInvTgtrTp(String glblCmpyCd, String dsInvTgtrTpCd) {
        DS_INV_TGTR_TPTMsg tMsg = new DS_INV_TGTR_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTgtrTpCd, dsInvTgtrTpCd);
        tMsg = (DS_INV_TGTR_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        return tMsg;
    }
    // END   2022/03/22 [QC#59683, ADD]

    // START 2022/02/03 R.Takau [QC#55645]
    public Map<String, Object> getBankInfo(String glblCmpyCd ,String dsCustBankAcctRelnPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsCustBankAcctRelnPk", dsCustBankAcctRelnPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getBankInfo", param).getResultObject();
    }

    public  Map<String, Object> getRelation(String glblCmpyCd ,String dsCustBankAcctRelnPk ,String sellToCustCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsCustBankAcctRelnPk", dsCustBankAcctRelnPk);
        param.put("sellToCustCd", sellToCustCd);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getRelation", param).getResultObject();
    }
    // END 2023/02/03 R.Takau [QC#55645]
    // START 2024/03/22 Y.Tamai [QC#63463 ADD]
    /**
     * getActiveContrBakSmryPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param actvFlg String
     * @return Map<String, Object>
     */
    public Map<String, Object> getActiveContrBakSmryPk(String glblCmpyCd, BigDecimal dsContrPk, String actvFlg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table", "CONTR_BAK_SMRY");
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("actvFlg", actvFlg);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getActiveContrBakSmryPk", param).getResultObject();
    }
    /**
     * getActiveContrBakSmryPk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param contrBakSmryPk BigDecimal
     * @param bakCratTs String
     * @return BigDecimal
     */
    public BigDecimal getExistBllgHldCnt(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal contrBakSmryPk, String bakCratTs) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("contrBakSmryPk", contrBakSmryPk);
        param.put("bakCratTs", bakCratTs);
        return (BigDecimal) getSsmEZDClient().queryObject("getExistBllgHldCnt", param).getResultObject();
    }
    /**
     * getDsContrDtlPkList
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDsContrDtlPkList(BigDecimal dsContrPk, String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrDtlPkList", param).getResultObject();
    }
    /**
     * getDsContrBllgMtrPkList
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDsContrBllgMtrPkList(BigDecimal dsContrPk, String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrBllgMtrPkList", param).getResultObject();
    }
    /**
     * getDsSubContrPkList
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDsSubContrPkList(BigDecimal dsContrPk, String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsSubContrPkList", param).getResultObject();
    }
    /**
     * getActiveTblPkList
     * @param tblName String
     * @param tblPk String
     * @param contrBakSmryPk BigDecimal
     * @param glblCmpyCd String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getActiveTblPkList(String tblName, String tblPk, BigDecimal contrBakSmryPk, String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("tblPk", TBL_ALIAS + PERIOD + tblPk);
        param.put("contrBakSmryPk", contrBakSmryPk);
        param.put("glblCmpyCd", glblCmpyCd);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getActiveTblPkList", param).getResultObject();
    }
    /**
     * getSinceActiveForMtrTblPkList
     * @param tblName String
     * @param tblPk String
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @param bakCratTs String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getSinceActiveForMtrTblPkList(String tblName, String tblPk, BigDecimal dsContrPk, String glblCmpyCd, String bakCratTs) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("tblPk", TBL_ALIAS + PERIOD + tblPk);
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("bakCratTs", bakCratTs);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSinceActiveForMtrTblPkList", param).getResultObject();
    }
    /**
     * getSinceActiveForDsSubTblPkList
     * @param tblName String
     * @param tblPk String
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @param bakCratTs String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getSinceActiveForDsSubTblPkList(String tblName, String tblPk, BigDecimal dsContrPk, String glblCmpyCd, String bakCratTs) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("tblPk", TBL_ALIAS + PERIOD + tblPk);
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("bakCratTs", bakCratTs);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSinceActiveForDsSubTblPkList", param).getResultObject();
    }
    /**
     * getSinceActiveForDsContrDtlTblPkList
     * @param tblName String
     * @param tblPk String
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @param bakCratTs String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getSinceActiveForDsContrDtlTblPkList(String tblName, String tblPk, BigDecimal dsContrPk, String glblCmpyCd, String bakCratTs) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("tblPk", TBL_ALIAS + PERIOD + tblPk);
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("bakCratTs", bakCratTs);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSinceActiveForDsContrDtlTblPkList", param).getResultObject();
    }
    /**
     * getSinceActiveForDsContrTblPkList
     * @param tblName String
     * @param tblPk String
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @param bakCratTs String
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getSinceActiveForDsContrTblPkList(String tblName, String tblPk, BigDecimal dsContrPk, String glblCmpyCd, String bakCratTs) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("tblPk", TBL_ALIAS + PERIOD + tblPk);
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("bakCratTs", bakCratTs);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSinceActiveForDsContrTblPkList", param).getResultObject();
    }
    /**
     * getRemovePkList
     * @param tblName String
     * @param tblPk String
     * @param glblCmpyCd String
     * @param dsContrSmryPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getRemovePkList(String tblName, String tblPk, String glblCmpyCd, BigDecimal dsContrSmryPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("tblPk", TBL_ALIAS + PERIOD + tblPk);
        param.put("dsContrSmryPk", dsContrSmryPk);
        param.put("glblCmpyCd", glblCmpyCd);

        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getRemovePkList", param).getResultObject();
    }
    /**
     * getBackupSourcePkList
     * @param stmtSelect PreparedStatement
     * @param tblName String
     * @param pkInfo Bigdecimal
     * @param glblCmpyCd String
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet getBackupSourcePkList(PreparedStatement stmtSelect, String tblName, BigDecimal pkInfo, String glblCmpyCd) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("pkInfo", pkInfo);
        param.put("glblCmpyCd", glblCmpyCd);
        stmtSelect = ssmLLClient.createPreparedStatement("getBackupSourcePkList", param, execParam);

        return stmtSelect.executeQuery();
    }
    /**
     * getBackupSourcePkList
     * @param stmtSelect PreparedStatement
     * @param tblName String
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet getBackupSourcePkListUsingDsContrDtl(PreparedStatement stmtSelect, String tblName, BigDecimal dsContrPk, String glblCmpyCd) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        stmtSelect = ssmLLClient.createPreparedStatement("getBackupSourcePkListUsingContrDtl", param, execParam);

        return stmtSelect.executeQuery();
    }
    /**
     * getBackupSourcePkListUsingBllgMtr
     * @param stmtSelect PreparedStatement
     * @param tblName String
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet getBackupSourcePkListUsingBllgMtr(PreparedStatement stmtSelect, String tblName, BigDecimal dsContrPk, String glblCmpyCd) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        stmtSelect = ssmLLClient.createPreparedStatement("getBackupSourcePkListUsingBllgMtr", param, execParam);

        return stmtSelect.executeQuery();
    }
    /**
     * getBackupSourcePkListUsingDsSubContr
     * @param stmtSelect PreparedStatement
     * @param tblName String
     * @param dsContrPk BigDecimal
     * @param glblCmpyCd String
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet getBackupSourcePkListUsingDsSubContr(PreparedStatement stmtSelect, String tblName, BigDecimal dsContrPk, String glblCmpyCd) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("dsContrPk", dsContrPk);
        param.put("glblCmpyCd", glblCmpyCd);
        stmtSelect = ssmLLClient.createPreparedStatement("getBackupSourcePkListUsingDsSubContr", param, execParam);

        return stmtSelect.executeQuery();
    }
    /**
     * getBackupSourcePkListUsingDsSubContr
     * @param stmtSelect PreparedStatement
     * @param  tblName String
     * @param pkInfo Bigdecimal
     * @param glblCmpyCd String
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet getBackupPkList(PreparedStatement stmtSelect, String tblName, BigDecimal pkInfo, String glblCmpyCd) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tblName", tblName);
        param.put("pkInfo", pkInfo);
        param.put("glblCmpyCd", glblCmpyCd);
        stmtSelect = ssmLLClient.createPreparedStatement("getBackupPkList", param, execParam);

        return stmtSelect.executeQuery();
    }
    /**
     * backupDsCntrBllgSchd
     * @param glblCmpyCd String
     * @param  dsContrPk  BigDecimal
     * @param contrBakSmrySq Bigdecimal
     * @param 
     * @return boolean
     * @throws SQLException SQLException
     */
    public boolean backupDsCntrBllgSchd(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal contrBakSmrySq) throws SQLException {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dsContrPk", dsContrPk);
        param.put("contrBakSmryPk", contrBakSmrySq);
        param.put("glblCmpyCd", glblCmpyCd);
        PreparedStatement stmt = null;
        try {
            stmt = ssmLLClient.createPreparedStatement("insertContrBllgSchdBak", param, execParam);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return true;
    }

    public List<BigDecimal> getResetBllgSchdPkList(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getResetBllgSchdPk", ssmParam).getResultObject();
    }

    public Map<String, Object> getResetMtrEntryInfo(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getResetMtrEntryInfo", ssmParam).getResultObject();
    }

    public S21SsmEZDResult getSvcContrBllg(BigDecimal dsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsContrDtlPk", dsContrDtlPk);
        queryParam.put("invTpCd", INV_TP.CREDIT_MEMO);
        return getSsmEZDClient().queryObjectList("getSvcContrBllg", queryParam);
    }

    public SVC_CONTR_BASE_BLLGTMsgArray getSvcContrBaseBllgForUpdateNoWait(BigDecimal svcContrBllgPk) {
        SVC_CONTR_BASE_BLLGTMsg inMsg = new SVC_CONTR_BASE_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_BASE_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public SVC_CONTR_MTR_BLLGTMsgArray getSvcContrMtrBllgForUpdateNoWait(BigDecimal svcContrBllgPk) {
        SVC_CONTR_MTR_BLLGTMsg inMsg = new SVC_CONTR_MTR_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_MTR_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public SVC_CONTR_BLLG_ALLOCTMsgArray getSvcContrBllgAllocForUpdateNoWait(BigDecimal svcContrBllgPk) {
        SVC_CONTR_BLLG_ALLOCTMsg inMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_BLLG_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public SVC_CONTR_XS_MTR_BLLGTMsgArray getSvcContrXsMtrBllgForUpdateNoWait(BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_XS_MTR_BLLGTMsg inMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        return (SVC_CONTR_XS_MTR_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public SVC_CONTR_ADDL_CHRG_BLLGTMsgArray getSvcContrAddlChrgBllgForUpdateNoWait(BigDecimal svcContrBllgPk) {
        SVC_CONTR_ADDL_CHRG_BLLGTMsg inMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        return (SVC_CONTR_ADDL_CHRG_BLLGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(inMsg);
    }

    public DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdForUpdateNoWait(BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }

    /**
     * @param glblCmpyCd String 
     * @param svcConrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBllgAllocPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBllgAllocPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeSvcContrBllgAlloc(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_BLLG_ALLOCTMsg inTMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBllgAllocPk, pk);
            EZDTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String 
     * @param svcConrBllgPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getSvcContrBllgGrpSq(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcContrBllgGrpSq", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcContrBllgGrpSq BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getAggrUsgRecalPk(String glblCmpyCd, BigDecimal svcContrBllgGrpSq) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgGrpSq", svcContrBllgGrpSq);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAggrUsgRecalPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getAggrUsgRecalDtlPk(String glblCmpyCd, BigDecimal aggrUsgRecalPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("aggrUsgRecalPk", aggrUsgRecalPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAggrUsgRecalDtlPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public List<BigDecimal> getAggrUsgRecalXsMtrPk(String glblCmpyCd, BigDecimal aggrUsgRecalDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("aggrUsgRecalDtlPk", aggrUsgRecalDtlPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAggrUsgRecalXsMtrPk", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalDtlPk BigDecimal
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeAggrUsgRecalXsMtr(String glblCmpyCd, BigDecimal aggrUsgRecalDtlPk, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECAL_XS_MTRTMsg inTMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalDtlPk, aggrUsgRecalDtlPk);
            setValue(inTMsg.aggrUsgRecalXsMtrPk, pk);
            EZDTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param aggrUsgRecalPk BigDecimal
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeAggrUsgRecalDtl(String glblCmpyCd, BigDecimal aggrUsgRecalPk, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECAL_DTLTMsg inTMsg = new AGGR_USG_RECAL_DTLTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalPk, aggrUsgRecalPk);
            setValue(inTMsg.aggrUsgRecalDtlPk, pk);
            EZDTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param pkList List<BigDecimal>
     * @return boolean
     */
    public boolean removeAggrUsgRecal(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECALTMsg inTMsg = new AGGR_USG_RECALTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalPk, pk);
            EZDTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }
    // END 2024/03/22 Y.Tamai [QC#63463 ADD]
}
