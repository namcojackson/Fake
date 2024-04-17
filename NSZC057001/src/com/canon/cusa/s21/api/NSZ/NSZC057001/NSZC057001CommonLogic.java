/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import parts.dbcommon.EZDTBLAccessor;

import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;
import business.db.DS_CONTR_CLSTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsgArray;
import business.db.DS_CONTR_PRC_ALLOCTMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.MTR_LBTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NMZC610001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_TRANSACTION;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2015/12/02   Hitachi         A.Kohinata      Update          QC1275
 * 2015/12/07   Hitachi         K.Yamada        Update          CSA QC#1417
 * 2016/01/06   Hitachi         T.Tomita        Update          QC2781
 * 2016/02/12   Hitachi         T.Aoyagi        Update          QC3274
 * 2016/04/28   Hitachi         M.Gotou         Update          QC#706
 * 2016/05/25   Hitachi         Y.Takeno        Update          QC#447
 * 2016/06/07   Hitachi         K.Yamada        Update          QC#3051
 * 2016/06/28   Hitachi         K.Kasai         Update          QC#10900
 * 2016/06/30   Hitachi         K.Yamada        Update          QC#3051
 * 2016/10/25   Hitachi         T.Tomita        Update          QC#3051
 * 2016/11/25   Hitachi         K.Kojima        Update          QC#16129
 * 2017/02/08   Hitachi         Y.Takeno        Update          QC#17275
 * 2017/02/20   Hitachi         T.Kanasaka      Update          QC#17614
 * 2017/04/24   Hitachi         K.Kitachi       Update          QC#17554
 * 2017/06/22   Hitachi         K.Kitachi       Update          QC#19340
 * 2017/07/27   Hitachi         T.Tomita        Update          QC#20226
 * 2017/07/31   Hitachi         T.Tomita        Update          QC#18764
 * 2017/08/09   Hitachi         K.Kojima        Update          QC#20527
 * 2017/08/09   Hitachi         K.Kitachi       Update          QC#19122
 * 2017/08/16   Hitachi         T.Tomita        Update          QC#18761
 * 2017/08/10   Hitachi         T.Kanasaka      Update          QC#18195
 * 2017/09/14   CITS            M.Naito         Update          QC#18534
 * 2018/02/02   Hitachi         U.Kim           Update          QC#23687
 * 2018/02/14   Hitachi         K.Kojima        Update          QC#24145
 * 2018/03/14   Hitachi         K.Kojima        Update          QC#24238
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22611
 * 2018/04/13   Hitachi         K.Kojima        Update          QC#24237
 * 2018/04/17   CITS            T.Wada          Update          QC#23378
 * 2018/05/11   CITS            T.Wada          Create          QC#24989
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/05/21   Hitachi         K.Kitachi       Update          QC#26217
 * 2018/06/13   Hitachi         A.Kohinata      Update          QC#26028
 * 2018/06/19   CITS            T.Wada          Update          QC#26658
 * 2018/07/03   Hitachi         K.Kishimoto     Update          QC#15410
 * 2018/07/09   Hitachi         K.Kitachi       Update          QC#26834
 * 2018/08/03   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2018/12/10   Hitachi         K.Fujimoto      Update          QC#29079
 * 2019/01/11   Hitachi         K.Morita        Update          QC#26928
 * 2019/03/25   Hitachi         A.Kohinata      Update          QC#30813
 * 2020/03/09   CITS            T.Wada          Update          QC#55830
 * 2020/03/24   Hitachi         A.Kohinata      Update          QC#54318
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/06/23   Hitachi         T.Usui          Update          QC#61408
 * 2023/07/27   CITS            R.Kurahashi     Update          QC#61710
 * 2023/11/08   CITS            R.Kurahashi     Update          QC#61568
 * 2023/11/13   Hitachi         S.Moriai        Update          QC#61756
 * </pre>
 */
public class NSZC057001CommonLogic {

    /**
     * setValueTMsg
     * @param tmsg DS_CONTR_VLD_RSLT_WRKTMsg
     * @param msgTxt String
     * @param errTrxNum String
     * @param errTrxNm BigDecimal
     */
    public static void setValueTMsg(DS_CONTR_VLD_RSLT_WRKTMsg tmsg, String msgTxt, String errTrxNum, BigDecimal errTrxNm) {
        // START 2016/02/12 T.Aoyagi [QC3274, ADD]
        if (hasValue(msgTxt)) {
            // delete message id
            Pattern p = Pattern.compile(REGEX_MSG_ID + " ");
            Matcher m = p.matcher(msgTxt);
            msgTxt = m.replaceFirst("");
        }
        // END 2016/02/12 T.Aoyagi [QC3274, ADD]
        // mod start 2018/06/13 QC#26028
        if (hasValue(msgTxt) && msgTxt.length() > tmsg.getAttr("dsContrVldRsltMsgTxt").getDigit()) {
            msgTxt = msgTxt.substring(0, tmsg.getAttr("dsContrVldRsltMsgTxt").getDigit());
        }
        // mod end 2018/06/13 QC#26028
        // START 2018/03/15 K.Kojima [QC#22611,ADD]
        // Tentatively set Error. Set up Error or Warning from the definition in subsequent processing.
        // END 2018/03/15 K.Kojima [QC#22611,ADD]
        setValue(tmsg.dsContrVldStsCd, DS_CONTR_VLD_STS.ERROR);
        setValue(tmsg.dsContrVldRsltMsgTxt, msgTxt);
        setValue(tmsg.dsContrVldErrTrxNm, errTrxNum);
        // START 2016/06/30 K.Yamada [QC#3051, MOD]
        if (hasValue(errTrxNm)) {
            setValue(tmsg.dsContrVldErrTrxNum, String.valueOf(errTrxNm));
        }
        // END 2016/06/30 K.Yamada [QC#3051, MOD]
    }

    /**
     * getSerNum
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return String
     */
    public static String getSerNum(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);

        return (String) mainClass.ssmBatchClient.queryObject("getSerNum", ssmPrm);
    }

    /**
     * getDsContrCtrlStsCd
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return String
     */
    public static String getDsContrCtrlStsCd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTL_STS_VTMsg dsContrDtlStsVTMsg = new DS_CONTR_DTL_STS_VTMsg();
        dsContrDtlStsVTMsg.setSQLID("002");
        dsContrDtlStsVTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrDtlStsVTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        DS_CONTR_DTL_STS_VTMsgArray dsContrDtlStsVTMsgArray = (DS_CONTR_DTL_STS_VTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrDtlStsVTMsg);
        if (dsContrDtlStsVTMsgArray == null || dsContrDtlStsVTMsgArray.getValidCount() == 0) {
            return null;
        }
        return dsContrDtlStsVTMsgArray.no(0).dsContrCtrlStsCd.getValue();
    }

    /**
     * getDsContrBllgMtr
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_BLLG_MTRTMsgArray
     */
    public static DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
        dsContrBllgMtrTMsg.setSQLID("001");
        dsContrBllgMtrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrBllgMtrTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(dsContrBllgMtrTMsg);
        if (dsContrBllgMtrTMsgArray == null || dsContrBllgMtrTMsgArray.getValidCount() == 0) {
            return null;
        }
        return dsContrBllgMtrTMsgArray;
    }

    /**
     * getDsContrBllgSchd
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrPk BigDecimal
     * @return DS_CONTR_BLLG_SCHDTMsgArray
     */
    public static DS_CONTR_BLLG_SCHDTMsgArray getDsContrBllgSchd(NSZC057001 mainClass, String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        DS_CONTR_BLLG_SCHDTMsgArray dsContrBllgSchdTMsgArray = new DS_CONTR_BLLG_SCHDTMsgArray();
        dsContrBllgSchdTMsgArray.setMsgList(new DS_CONTR_BLLG_SCHDTMsg[MAX_SIZE]);
        mainClass.ssmEzdClient.queryEZDMsgArray("getDsContrBllgSchd", ssmPrm, dsContrBllgSchdTMsgArray);
        return dsContrBllgSchdTMsgArray;
    }

    /**
     * getContrXsCopy
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return CONTR_XS_COPYTMsgArray
     */
    public static CONTR_XS_COPYTMsgArray getContrXsCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg contrXsCopyTMsg = new CONTR_XS_COPYTMsg();
        contrXsCopyTMsg.setSQLID("002");
        contrXsCopyTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        contrXsCopyTMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        CONTR_XS_COPYTMsgArray dsContrTMsgArray = (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByCondition(contrXsCopyTMsg);
        if (dsContrTMsgArray == null || dsContrTMsgArray.getValidCount() == 0) {
            return null;
        }
        return dsContrTMsgArray;
    }

    /**
     * getMachAndContrInfo
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return Map<String, Object>
     */
    public static Map<String, Object> getMachAndContrInfo(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        // START 2018/02/02 U.Kim [QC#23687,ADD]
        Map<String, Object> machAndContrInfo = mainClass.machAndContrInfoCache.get(dsContrDtlPk);
        if (mainClass.machAndContrInfoCache.contains(dsContrDtlPk)) {
            return machAndContrInfo;
        }
        // END 2018/02/02 U.Kim [QC#23687,ADD]

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);

        @SuppressWarnings("unchecked")
        Map<String, Object> dsContrInfoVld = (Map<String, Object>) mainClass.ssmBatchClient.queryObject("getMachAndContrInfo", ssmPrm);
        // START 2018/02/02 U.Kim [QC#23687,ADD]
        mainClass.machAndContrInfoCache.put(dsContrDtlPk, dsContrInfoVld);
        // END 2018/02/02 U.Kim [QC#23687,ADD]
        return dsContrInfoVld;
    }

    /**
     * existSvcPhysMtrRead
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param befMtrReadDt String
     * @param aftMtrReadDt String
     * @return boolean
     */
    public static boolean existSvcPhysMtrRead(String glblCmpyCd, BigDecimal svcMachMstrPk, String befMtrReadDt, String aftMtrReadDt) {
        SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = new SVC_PHYS_MTR_READTMsg();
        svcPhysMtrReadTMsg.setSQLID("008");
        svcPhysMtrReadTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcPhysMtrReadTMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        svcPhysMtrReadTMsg.setConditionValue("mtrReadDt01", befMtrReadDt);
        svcPhysMtrReadTMsg.setConditionValue("mtrReadDt02", aftMtrReadDt);
        SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(svcPhysMtrReadTMsg);
        return svcPhysMtrReadTMsgArray.getValidCount() > 0;
    }

    /**
     * existFmContrSlsRepReln
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param slsRepTocCd String
     * @param dsAcctNum String
     * @param slsDt String
     * @return boolean
     */
    // START 2016/11/25 K.Kojima [QC#16129,MOD]
    // public static boolean existFmContrSlsRepReln(NSZC057001 mainClass, String glblCmpyCd, String slsRepTocCd, String shipToCustCd, String slsDt) {
    public static boolean existFmContrSlsRepReln(NSZC057001 mainClass, String glblCmpyCd, String slsRepTocCd, String dsAcctNum, String slsDt) {
    // END 2016/11/25 K.Kojima [QC#16129,MOD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsRepTocCd", slsRepTocCd);
        // START 2016/11/25 K.Kojima [QC#16129,MOD]
        // ssmPrm.put("shipToCustCd", shipToCustCd);
        ssmPrm.put("dsAcctNum", dsAcctNum);
        // END 2016/11/25 K.Kojima [QC#16129,MOD]
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countFmContrSlsRepReln", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    // START 2016/11/25 K.Kojima [QC#16129,DEL]
    // /**
    // * getShipToCustCd
    // * @param glblCmpyCd String
    // * @param svcMachMstrPk BigDecimal
    // * @return String
    // */
    // public static String getShipToCustCd(String glblCmpyCd,
    // BigDecimal svcMachMstrPk) {
    // SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
    // ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd,
    // glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk,
    // svcMachMstrPk);
    // svcMachMstrTMsg = (SVC_MACH_MSTRTMsg)
    // S21ApiTBLAccessor.findByKey(svcMachMstrTMsg);
    // if (svcMachMstrTMsg == null) {
    // return null;
    // }
    // return svcMachMstrTMsg.shipToCustCd.getValue();
    // }
    // END 2016/11/25 K.Kojima [QC#16129,DEL]

    /**
     * getSvcPhysMtrRead
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcMachMstrPk BigDecimal
     * @return SVC_PHYS_MTR_READTMsg
     */
    public static SVC_PHYS_MTR_READTMsg getSvcPhysMtrRead(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk) {
        SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = new SVC_PHYS_MTR_READTMsg();
        svcPhysMtrReadTMsg.setSQLID("009");
        svcPhysMtrReadTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcPhysMtrReadTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        svcPhysMtrReadTMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadTMsgArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(svcPhysMtrReadTMsg);
        if (svcPhysMtrReadTMsgArray == null || svcPhysMtrReadTMsgArray.getValidCount() == 0) {
            return null;
        }
        return svcPhysMtrReadTMsgArray.no(0);
    }

    /**
     * getSvcTermAttrbMapValCd
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcTermCondAttrbNm String
     * @return String
     */
    public static String getSvcTermAttrbMapValCd(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String svcTermCondAttrbNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("svcTermCondAttrbNm", svcTermCondAttrbNm);

        return (String) mainClass.ssmBatchClient.queryObject("getSvcTermAttrbMapValCd", ssmPrm);
    }

    /**
     * getBaseChrgAmt
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return BigDecimal
     */
    public static BigDecimal getBaseChrgAmt(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getBaseChrgAmt", ssmPrm);
    }

    /**
     * getUsgChrgInfo
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUsgChrgInfo(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> usgChrgInfo = (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getUsgChrgInfo", ssmPrm);
        return usgChrgInfo;
    }

    /**
     * getBaseChrgFmContrMdlPrc
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param slsDt String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getBaseChrgFmContrMdlPrc(NSZC057001 mainClass, String glblCmpyCd, BigDecimal svcMachMstrPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        @SuppressWarnings("unchecked")
        Map<String, Object> baseChrgFmContrMdlPrc = (Map<String, Object>) mainClass.ssmBatchClient.queryObject("getBaseChrgFmContrMdlPrc", ssmPrm);
        return baseChrgFmContrMdlPrc;
    }

    /**
     * getUsgChrgFmContrMdlPrc
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param slsDt String
     * @param mtrLbCd String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getUsgChrgFmContrMdlPrc(NSZC057001 mainClass, String glblCmpyCd, BigDecimal svcMachMstrPk, String slsDt, String mtrLbCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);
        ssmPrm.put("mtrLbCd", mtrLbCd);

        @SuppressWarnings("unchecked")
        Map<String, Object> usgChrgFmContrMdlPrc = (Map<String, Object>) mainClass.ssmBatchClient.queryObject("getUsgChrgFmContrMdlPrc", ssmPrm);
        return usgChrgFmContrMdlPrc;
    }

    /**
     * getDsContrCrCardPo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTR_CR_CARD_POTMsgArray
     */
    public static DS_CONTR_CR_CARD_POTMsgArray getDsContrCrCardPo(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTMsg = new DS_CONTR_CR_CARD_POTMsg();
        // START 2018/04/13 K.Kojima [QC#24237,MOD]
        // dsContrCrCardPoTMsg.setSQLID("001");
        // START 2019/01/16 K.Morita [QC#26928,MOD]
        //dsContrCrCardPoTMsg.setSQLID("004");
        dsContrCrCardPoTMsg.setSQLID("205");
        // END 2018/01/16 K.Morita [QC#26928,MOD]
        // END 2018/04/13 K.Kojima [QC#24237,MOD]
        dsContrCrCardPoTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrCrCardPoTMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) EZDTBLAccessor.findByCondition(dsContrCrCardPoTMsg);
        // START 2016/01/06 T.Tomita [QC2781, DEL]
//        if (dsContrCrCardPoTMsgArray == null || dsContrCrCardPoTMsgArray.getValidCount() == 0) {
//            return null;
//        }
        // END 2016/01/06 T.Tomita [QC2781, DEL]
        return dsContrCrCardPoTMsgArray;
    }

    /**
     * existDsContrPrcAlloc
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return boolean
     */
    public static boolean existDsContrPrcAlloc(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_PRC_ALLOCTMsg dsContrPrcAllocTMsg = new DS_CONTR_PRC_ALLOCTMsg();
        dsContrPrcAllocTMsg.setSQLID("001");
        dsContrPrcAllocTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrPrcAllocTMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_PRC_ALLOCTMsgArray dsContrPrcAllocTMsgArray = (DS_CONTR_PRC_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(dsContrPrcAllocTMsg);
        return dsContrPrcAllocTMsgArray.getValidCount() > 0;
    }

    /**
     * isUnderFleet
     * @param dsContrTMsg DS_CONTRTMsg
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     * @return true: dsContrDtlTMsg is a machine or an accessory under fleet contract. false:otherwise
     */
    // add start 2015/12/07 CSA Defect#1417
    public static boolean isUnderFleet(DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            //not fleet contract
            return false;
        }
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            //fleet line
            return false;
        }
        return true;
    }
    // add end 2015/12/07 CSA Defect#1417

    // add start 2016/04/28 CSA Defect#706
    /**
     * existDsContrCls
     * @param glblCmpyCd String
     * @param dsContrClsCd String
     * @return boolean
     */
    public static boolean existDsContrCls(String glblCmpyCd, String dsContrClsCd) {
        DS_CONTR_CLSTMsg dsContrClsTMsg = new DS_CONTR_CLSTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrClsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrClsTMsg.dsContrClsCd, dsContrClsCd);
        dsContrClsTMsg = (DS_CONTR_CLSTMsg) S21ApiTBLAccessor.findByKey(dsContrClsTMsg);
        if (dsContrClsTMsg == null) {
            return false;
        }
        if (!EMSD_CHARGEBACK.equals(dsContrClsTMsg.fmTrxCd.getValue())) {
            return false;
        }
        if (!FM_TRX_RSN_CD_01.equals(dsContrClsTMsg.fmTrxRsnCd_01.getValue())) {
            return false;
        }
        if (!FM_TRX_RSN_CD_02.equals(dsContrClsTMsg.fmTrxRsnCd_02.getValue())) {
            return false;
        }
        return true;
    }
    // add end 2016/04/28 CSA Defect#706

    // START 05/25/2016 [QC#447, ADD]
    // del start 2016/06/28 CSA Defect#10900
//    /**
//     * getAddrChrgContr
//     * 
//     * @param mainClass NSZC057001
//     * @param glblCmpyCd glblCmpyCd
//     * @param dsContrPk dsContrPk
//     * @return List<Map<String, Object>>
//     */
//    public static List<Map<String, Object>> getAddrChrgContr(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
//        Map<String, Object> ssmPrm = new HashMap<String, Object>();
//        ssmPrm.put("glblCmpyCd", glblCmpyCd);
//        ssmPrm.put("dsContrPk", dsContrPk);
//
//        List<Map<String, Object>> addrChrg = (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getAddrChrgContr", ssmPrm);
//        return addrChrg;
//    }
    // del end 2016/06/28 CSA Defect#10900

    /**
     * getAddrChrgContrDtl
     * 
     * @param mainClass NSZC057001
     * @param glblCmpyCd glblCmpyCd
     * @param dsContrDtlPk dsContrDtlPk
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getAddrChrgContrDtl(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);

        List<Map<String, Object>> addrChrg = (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getAddrChrgContrDtl", ssmPrm);
        return addrChrg;
    }

    /**
     * getTermCond
     * 
     * @param mainClass NSZC057001
     * @param glblCmpyCd glblCmpyCd
     * @param dsContrDtlPk dsContrDtlPk
     * @param slaDt slaDt
     * @param termCondAttrNm termCondAttrNm
     * @return Map<String, Object>
     */
    public static Map<String, Object> getTermCond(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String slaDt, String termCondAttrNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slaDt", slaDt);
        ssmPrm.put("termCondAttrNm", termCondAttrNm);

        Map<String, Object> termCond = (Map<String, Object>) mainClass.ssmBatchClient.queryObject("getTermCond", ssmPrm);
        return termCond;
    }
    // END   05/25/2016 [QC#447, ADD]

    // START 2016/06/07 K.Yamada [QC#3051, ADD]
    /**
     * existCfsLeaseContrForCheckDates
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param slsDt String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckDates(NSZC057001 mainClass, String glblCmpyCd, String serNum, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckDates", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * getDsContrPrcEff
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getDsContrPrcEff(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);

        return (Map<String, Object>) mainClass.ssmBatchClient.queryObject("getDsContrPrcEff", ssmPrm);
    }

    /**
     * getSumBasePerCycleDealAmtForCheckInsource
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @return BigDecimal
     */
    public static BigDecimal getSumBasePerCycleDealAmtForCheckInsource(NSZC057001 mainClass, String glblCmpyCd, String serNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getSumBasePerCycleDealAmtForCheckInsource", ssmPrm);
    }

    /**
     * existCfsLeaseContrForCheckCfsDealerCodeDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param leaseDlrCd String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsDealerCodeDiff(NSZC057001 mainClass, String glblCmpyCd, String serNum, String leaseDlrCd) {
        if (!hasValue(leaseDlrCd)) {
            return false;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("leaseDlrCd", leaseDlrCd);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsDealerCodeDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * existCfsLeaseContrForCheckCfsNonFltMachineDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsNonFltMachineDiff(NSZC057001 mainClass, String glblCmpyCd, String serNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsNonFltMachineDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * existCfsLeaseContrForCheckCfsNonFltBaseCycleDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param baseBllgCycleCd String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsNonFltBaseCycleDiff(NSZC057001 mainClass, String glblCmpyCd, String serNum, String baseBllgCycleCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("baseBllgCycleCd", baseBllgCycleCd);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsNonFltBaseCycleDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * existCfsLeaseContrForCheckCfsNonFltUsageCycleDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param usgBllgCycleCd String
     * @param bllgMtrLbCd String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsNonFltUsageCycleDiff(NSZC057001 mainClass, String glblCmpyCd, String serNum, String usgBllgCycleCd, String bllgMtrLbCd) {
        String usgTpBillCd = convertMtrLbCd(glblCmpyCd, bllgMtrLbCd);
        if (!hasValue(usgTpBillCd)) {
            return false;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("usgBllgCycleCd", usgBllgCycleCd);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsNonFltUsageCycleDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    // START 2016/06/30 K.Yamada [QC#3051, MOD]
    /**
     * existCfsLeaseContrForCheckCfsNonFltUsageTierRateDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param usgTpBillCd String
     * @param firstXsMtrRate BigDecimal
     * @param scdXsMtrRate BigDecimal
     * @param thirdXsMtrRate BigDecimal
     * @param frthXsMtrRate BigDecimal
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsNonFltUsageTierRateDiff(NSZC057001 mainClass, String glblCmpyCd, String serNum, String usgTpBillCd, BigDecimal firstXsMtrRate, BigDecimal scdXsMtrRate, BigDecimal thirdXsMtrRate, BigDecimal frthXsMtrRate) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);
        ssmPrm.put("firstXsMtrRate", firstXsMtrRate);
        ssmPrm.put("scdXsMtrRate", scdXsMtrRate);
        ssmPrm.put("thirdXsMtrRate", thirdXsMtrRate);
        ssmPrm.put("frthXsMtrRate", frthXsMtrRate);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsNonFltUsageTierRateDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    // Mod Start 2017/08/16 QC#187611
    /**
     * existCfsLeaseContrForCheckCfsNonFltUsageTierAllowanceDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param usgTpBillCd String
     * @param firstXsMtrCopyDplyQty BigDecimal
     * @param scdXsMtrCopyDplyQty BigDecimal
     * @param thirdXsMtrCopyDplyQty BigDecimal
     * @param frthXsMtrCopyDplyQty BigDecimal
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsNonFltUsageTierAllowanceDiff(NSZC057001 mainClass, String glblCmpyCd, String serNum, String usgTpBillCd, BigDecimal firstXsMtrCopyDplyQty, BigDecimal scdXsMtrCopyDplyQty, BigDecimal thirdXsMtrCopyDplyQty, BigDecimal frthXsMtrCopyDplyQty) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);
        ssmPrm.put("firstXsMtrCopyDplyQty", firstXsMtrCopyDplyQty);
        ssmPrm.put("scdXsMtrCopyDplyQty", scdXsMtrCopyDplyQty);
        ssmPrm.put("thirdXsMtrCopyDplyQty", thirdXsMtrCopyDplyQty);
        ssmPrm.put("frthXsMtrCopyDplyQty", frthXsMtrCopyDplyQty);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsNonFltUsageTierAllowanceDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // Mod End 2017/08/16 QC#18761
    // END 2016/06/30 K.Yamada [QC#3051, MOD]

    /**
     * getUsgChrgInfoForCheckCfsUsageTierRateDiff
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param slsDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUsgChrgInfoForCheckCfsUsageTierRateDiff(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        return (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getUsgChrgInfoForCheckCfsUsageTierRateDiff", ssmPrm);
    }

    /**
     * getMachAndContrInfoForFleetLine
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return Map<String, Object>
     */
    public static Map<String, Object> getMachAndContrInfoForFleetLine(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);

        return (Map<String, Object>) mainClass.ssmBatchClient.queryObject("getMachAndContrInfoForFleetLine", ssmPrm);
    }

    /**
     * existCfsLeaseContrForCheckCfsFltBaseCycleDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param baseBllgCycleCd String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsFltBaseCycleDiff(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, String baseBllgCycleCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("baseBllgCycleCd", baseBllgCycleCd);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsFltBaseCycleDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    // START 2016/06/30 K.Yamada [QC#3051, MOD]
    /**
     * existCfsLeaseContrForCheckCfsFltUsageCycleDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param usgBllgCycleCd String
     * @param usgTpBillCd String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsFltUsageCycleDiff(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, String usgBllgCycleCd, String usgTpBillCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("usgBllgCycleCd", usgBllgCycleCd);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsFltUsageCycleDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // END 2016/06/30 K.Yamada [QC#3051, MOD]

    /**
     * existCfsLeaseContrForCheckCfsFltFtrDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param cfsFtrFlg String
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsFltFtrDiff(NSZC057001 mainClass, String glblCmpyCd, String serNum, String cfsFtrFlg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("cfsFtrFlg", cfsFtrFlg);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsFltFtrDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * getSumBasePerCycleDealAmtForCheckCfsFltBaseChargeDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal getSumBasePerCycleDealAmtForCheckCfsFltBaseChargeDiff(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getSumBasePerCycleDealAmtForCheckCfsFltBaseChargeDiff", ssmPrm);
    }

    // START 2016/06/30 K.Yamada [QC#3051, MOD]
    /**
     * existCfsLeaseContrForCheckCfsFltUsageTierRateDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param usgTpBillCd String
     * @param firstXsMtrRate BigDecimal
     * @param scdXsMtrRate BigDecimal
     * @param thirdXsMtrRate BigDecimal
     * @param frthXsMtrRate BigDecimal
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsFltUsageTierRateDiff(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, String usgTpBillCd, BigDecimal firstXsMtrRate, BigDecimal scdXsMtrRate, BigDecimal thirdXsMtrRate, BigDecimal frthXsMtrRate) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);
        ssmPrm.put("firstXsMtrRate", firstXsMtrRate);
        ssmPrm.put("scdXsMtrRate", scdXsMtrRate);
        ssmPrm.put("thirdXsMtrRate", thirdXsMtrRate);
        ssmPrm.put("frthXsMtrRate", frthXsMtrRate);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsFltUsageTierRateDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    // Mod Start 2017/08/16 QC#187611
    /**
     * existCfsLeaseContrForCheckCfsFltUsageTierAllowanceDiff
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param usgTpBillCd String
     * @param firstXsMtrCopyDplyQty BigDecimal
     * @param scdXsMtrCopyDplyQty BigDecimal
     * @param thirdXsMtrCopyDplyQty BigDecimal
     * @param frthXsMtrCopyDplyQty BigDecimal
     * @return boolean
     */
    public static boolean existCfsLeaseContrForCheckCfsFltUsageTierAllowanceDiff(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, String usgTpBillCd, BigDecimal firstXsMtrCopyDplyQty, BigDecimal scdXsMtrCopyDplyQty, BigDecimal thirdXsMtrCopyDplyQty, BigDecimal frthXsMtrCopyDplyQty) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);
        ssmPrm.put("firstXsMtrCopyDplyQty", firstXsMtrCopyDplyQty);
        ssmPrm.put("scdXsMtrCopyDplyQty", scdXsMtrCopyDplyQty);
        ssmPrm.put("thirdXsMtrCopyDplyQty", thirdXsMtrCopyDplyQty);
        ssmPrm.put("frthXsMtrCopyDplyQty", frthXsMtrCopyDplyQty);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContrForCheckCfsFltUsageTierAllowanceDiff", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // Mod End 2017/08/16 QC#18761
    // END 2016/06/30 K.Yamada [QC#3051, MOD]

    /**
     * convertMtrLbCd
     * @param glblCmpyCd String
     * @param mtrLbCd String
     * @return String
     */
    public static String convertMtrLbCd(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg tMsg = new MTR_LBTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.mtrLbCd, mtrLbCd);
        tMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(tMsg);
        String bwMtrFlg = tMsg.bwMtrFlg.getValue();
        String colorMtrFlg = tMsg.colorMtrFlg.getValue();
        if (!hasValue(bwMtrFlg) || !hasValue(bwMtrFlg)) {
            return null;
        }
        if (ZYPConstant.FLG_ON_Y.equals(bwMtrFlg) && ZYPConstant.FLG_OFF_N.equals(colorMtrFlg)) {
            return USG_TP_BILL_CD_BW;
        }
        if (ZYPConstant.FLG_OFF_N.equals(bwMtrFlg) && ZYPConstant.FLG_ON_Y.equals(colorMtrFlg)) {
            return USG_TP_BILL_CD_CL;
        }
        if (ZYPConstant.FLG_OFF_N.equals(bwMtrFlg) && ZYPConstant.FLG_OFF_N.equals(colorMtrFlg)) {
            return USG_TP_BILL_CD_SM;
        }
        return null;
    }

    /**
     * convertBllgCycleCd
     * @param glblCmpyCd String
     * @param bllgCycleCd String
     * @return String
     */
    public static String convertBllgCycleCd(String glblCmpyCd, String bllgCycleCd) {
        BLLG_CYCLETMsg tMsg = new BLLG_CYCLETMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.bllgCycleCd, bllgCycleCd);
        tMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(tMsg);
        return tMsg.cfsBllgCycleCd.getValue();
    }
    // END 2016/06/07 K.Yamada [QC#3051, ADD]

    // START 2016/06/30 K.Yamada [QC#3051, ADD]
    /**
     * existCfsLeaseContr
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param leaseDlrCd String
     * @param baseBillFlg String
     * @param usgBillFlg String
     * @param usgTpBillCd String
     * @param dsContrPk BigDecimal
     * @return boolean
     */
    public static boolean existCfsLeaseContr(NSZC057001 mainClass, String glblCmpyCd, String serNum, String leaseDlrCd, String baseBillFlg, String usgBillFlg, String usgTpBillCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("leaseDlrCd", leaseDlrCd);
        ssmPrm.put("baseBillFlg", baseBillFlg);
        ssmPrm.put("usgBillFlg", usgBillFlg);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);
        ssmPrm.put("dsContrPk", dsContrPk);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countCfsLeaseContr", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * getTierCount
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param leaseDlrCd String
     * @param baseBillFlg String
     * @param usgBillFlg String
     * @param usgTpBillCd String
     * @param dsContrPk BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal getTierCount(NSZC057001 mainClass, String glblCmpyCd, String serNum, String leaseDlrCd, String baseBillFlg, String usgBillFlg, String usgTpBillCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("leaseDlrCd", leaseDlrCd);
        ssmPrm.put("baseBillFlg", baseBillFlg);
        ssmPrm.put("usgBillFlg", usgBillFlg);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);
        ssmPrm.put("dsContrPk", dsContrPk);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getTierCount", ssmPrm);
    }

    /**
     * getDsContrPrcEffPk
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return BigDecimal
     */
    public static BigDecimal getDsContrPrcEffPk(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getDsContrPrcEffPk", ssmPrm);
    }
    // END 2016/06/30 K.Yamada [QC#3051, ADD]

    // START 2016/10/25 T.Tomita [QC#3051, ADD]
    // mod start 2018/06/13 QC#26028
//    /**
//     * getEndOfLife
//     * @param glblCmpyCd String
//     * @param svcMachMstrPk BigDecimal
//     * @param contrEffThruDt String
//     * @return List<Map<String, Object>>
//     */
//    public static List<Map<String, Object>> getEndOfLife(NSZC057001 mainClass, String glblCmpyCd, BigDecimal svcMachMstrPk, String contrEffThruDt) {
//        Map<String, Object> ssmPrm = new HashMap<String, Object>();
//        ssmPrm.put("glblCmpyCd", glblCmpyCd);
//        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
//        ssmPrm.put("dsMdlEolStsCd", DS_MDL_EOL_STS.EOL_NO_CONTRACT);
//        ssmPrm.put("contrEffThruDt", contrEffThruDt);
//
//        return (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getEndOfLife", ssmPrm);
//    }
    /**
     * getEndOfLife
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param targetDt String
     * @param dsMdlEolStsCd String
     * @return List<Map<String, Object>>
     */
    public static Map<String, Object> getEndOfLife(NSZC057001 mainClass, String glblCmpyCd, BigDecimal svcMachMstrPk, String targetDt, String dsMdlEolStsCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("targetDt", targetDt);
        ssmPrm.put("dsMdlEolStsCd", dsMdlEolStsCd);

        return (Map<String, Object>) mainClass.ssmBatchClient.queryObject("getEndOfLife", ssmPrm);
    }
    // mod end 2018/06/13 QC#26028

    /**
     * existDsContrTrk
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return boolean
     */
    public static boolean existDsContrTrk(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.ACTIVE);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countDsContrTrk", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // END 2016/10/25 T.Tomita [QC#3051, ADD]

    // START 2017/02/08 [QC#17275, ADD]
    /**
     * getContrPhysBllgMtrReln
     *
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getContrPhysBllgMtrReln(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>)mainClass.ssmBatchClient.queryObjectList("getContrPhysBllgMtrReln", ssmPrm);
    }

    // START 2017/08/10 T.Kanasaka [QC#18195,MOD]
    /**
     * getPrntBllgMtr
     * 
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param mdlMtrLbCd String
     * @param bllgMtrLbCd String
     * @param slsDt String
     * @param svcMachMstrPk BigDecimal
     * @return List<String>
     */
//    public static List<String> getPrntBllgMtr(NSZC057001 mainClass, String glblCmpyCd, String mdlMtrLbCd, String bllgMtrLbCd, String slsDt) {
    public static List<String> getPrntBllgMtr(NSZC057001 mainClass, String glblCmpyCd, String mdlMtrLbCd, String bllgMtrLbCd, String slsDt, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlMtrLbCd", mdlMtrLbCd);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return (List<String>)mainClass.ssmBatchClient.queryObjectList("getPrntBllgMtr", ssmPrm);
    }
    // END 2017/08/10 T.Kanasaka [QC#18195,MOD]

    /**
     * getMtrGrpPrcVldFlg
     * 
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return String
     */
    public static String getMtrGrpPrcVldFlg(NSZC057001 mainClass, String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
        return (String)mainClass.ssmBatchClient.queryObject("getMtrGrpPrcVldFlg", ssmPrm);
    }
    // END   2017/08/08 [QC#17275, ADD]

    // START 2017/02/20 T.Kanasaka [QC#17614, ADD]
    /**
     * existSlsRep
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param tocCd String
     * @param slsDt String
     * @return boolean
     */
    public static boolean existSlsRep(NSZC057001 mainClass, String glblCmpyCd, String tocCd, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("tocCd", tocCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);
        ssmPrm.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countSlsRep", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // END 2017/02/20 T.Kanasaka [QC#17614, ADD]

    // START 2017/04/24 K.Kitachi [QC#17554, ADD]
    /**
     * getSumBasePrcDealAmtOfAcc
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return BigDecimal
     */
    public static BigDecimal getSumBasePrcDealAmtOfAcc(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED };
        ssmPrm.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getSumBasePrcDealAmtOfAcc", ssmPrm);
    }
    // END 2017/04/24 K.Kitachi [QC#17554, ADD]

    // START 2017/06/22 K.Kitachi [QC#19340, ADD]
    /**
     * isLeaseCompany
     * @param dsContrTMsg DS_CONTRTMsg
     * @return boolean
     */
    // START 2018/02/02 U.Kim [QC#23687,MOD]
    // public static boolean isLeaseCompany(DS_CONTRTMsg dsContrTMsg) {
    public static boolean isLeaseCompany(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg) {
    // END 2018/02/02 U.Kim [QC#23687,MOD]
        if (dsContrTMsg == null) {
            return false;
        }
        // START 2018/02/02 U.Kim [QC#23687,ADD]
        Boolean cache = mainClass.isLeaseCompanyCache.get(dsContrTMsg.dsContrPk.getValue());
        if (cache != null) {
            return cache.booleanValue();
        }
        // END 2018/02/02 U.Kim [QC#23687,ADD]
        if (!hasValue(dsContrTMsg.leaseCmpyCd)) {
            // START 2018/02/02 U.Kim [QC#23687,ADD]
            mainClass.isLeaseCompanyCache.put(dsContrTMsg.dsContrPk.getValue(), false);
            // END 2018/02/02 U.Kim [QC#23687,ADD]
            return false;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(dsContrTMsg.baseChrgToLeaseCmpyFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(dsContrTMsg.usgChrgToLeaseCmpyFlg.getValue())) {
            // START 2018/02/02 U.Kim [QC#23687,ADD]
            mainClass.isLeaseCompanyCache.put(dsContrTMsg.dsContrPk.getValue(), false);
            // END 2018/02/02 U.Kim [QC#23687,ADD]
            return false;
        }
        // START 2017/08/09 K.Kojima [QC#20527,ADD]
        if (!ZYPCommonFunc.hasValue(dsContrTMsg.dsContrEdiCd) || !dsContrTMsg.dsContrEdiCd.getValue().equals(DS_CONTR_EDI.CFS)) {
            // START 2018/02/02 U.Kim [QC#23687,ADD]
            mainClass.isLeaseCompanyCache.put(dsContrTMsg.dsContrPk.getValue(), false);
            // END 2018/02/02 U.Kim [QC#23687,ADD]
            return false;
        }
        // END 2017/08/09 K.Kojima [QC#20527,ADD]
        if (!NSXC001001ContrValidation.isLeaseCompany(dsContrTMsg.glblCmpyCd.getValue(), dsContrTMsg.leaseCmpyCd.getValue())) {
            // START 2018/02/02 U.Kim [QC#23687,ADD]
            mainClass.isLeaseCompanyCache.put(dsContrTMsg.dsContrPk.getValue(), false);
            // END 2018/02/02 U.Kim [QC#23687,ADD]
            return false;
        }
        // START 2018/02/02 U.Kim [QC#23687,ADD]
        mainClass.isLeaseCompanyCache.put(dsContrTMsg.dsContrPk.getValue(), true);
        // END 2018/02/02 U.Kim [QC#23687,ADD]
        return true;
    }
    // END 2017/06/22 K.Kitachi [QC#19340, ADD]

    // START 2018/02/02 U.Kim [QC#23687,ADD]
    public static boolean isLeaseCompanyForBllgMtr(NSZC057001 mainClass, String glblCmpyCd, String billToCustCd) {
        if (billToCustCd == null) {
            return false;
        }
        Boolean cache = mainClass.isLeaseCompanyForBllgMtrCache.get(billToCustCd);
        if (cache != null) {
            return cache.booleanValue();
        }
        if (!NSXC001001ContrValidation.isLeaseCompany(glblCmpyCd, billToCustCd)) {
            mainClass.isLeaseCompanyForBllgMtrCache.put(billToCustCd, false);
            return false;
        }
        mainClass.isLeaseCompanyForBllgMtrCache.put(billToCustCd, true);
        return true;
    }

    // END 2018/02/02 U.Kim [QC#23687,ADD]

    // Add Start 2017/07/27 QC#20226
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getDsContrDtlForPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param slsDt String
     * @param dsContrTMsg DS_CONTRTMsg
     * @return String
     */
    public static String getProcDt(String slsDt, DS_CONTRTMsg dsContrTMsg) {
        if (dsContrTMsg == null) {
            return slsDt;
        }

        if (ZYPDateUtil.compare(slsDt, dsContrTMsg.contrVrsnEffFromDt.getValue()) < 0) {
            return dsContrTMsg.contrVrsnEffFromDt.getValue();
        } else if (ZYPDateUtil.compare(slsDt, dsContrTMsg.contrVrsnEffThruDt.getValue()) > 0) {
            return dsContrTMsg.contrVrsnEffThruDt.getValue();
        }
        return slsDt;
    }

    /**
     * @param slsDt String
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     * @return String
     */
    public static String getProcDt(String slsDt, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        if (dsContrDtlTMsg == null) {
            return slsDt;
        }

        if (ZYPDateUtil.compare(slsDt, dsContrDtlTMsg.contrEffFromDt.getValue()) < 0) {
            return dsContrDtlTMsg.contrEffFromDt.getValue();
        } else if (ZYPDateUtil.compare(slsDt, dsContrDtlTMsg.contrEffThruDt.getValue()) > 0) {
            return dsContrDtlTMsg.contrEffThruDt.getValue();
        }
        return slsDt;
    }
    // Add End 2017/07/27 QC#20226
    // Add Start 2017/07/31 QC#18764
    /**
     * getAggXsMtrCopyQty
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param serNum String
     * @param usgTpBillCd String
     * @return Map<String, BigDecimal>
     */
    public static Map<String, BigDecimal> getAggXsMtrCopyQty(NSZC057001 mainClass, String glblCmpyCd, String serNum, String usgTpBillCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("serNum", serNum);
        ssmPrm.put("usgTpBillCd", usgTpBillCd);
        return (Map<String, BigDecimal>)mainClass.ssmBatchClient.queryObject("getAggXsMtrCopyQty", ssmPrm);
    }

    // Add End 2017/07/31 QC#18764

    // START 2017/08/09 K.Kitachi [QC#19122, ADD]
    /**
     * existDsContrBllgMtr
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return boolean
     */
    public static boolean existDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        DS_CONTR_BLLG_MTRTMsgArray outMsgArray = (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return outMsgArray.getValidCount() > 0;
    }

    /**
     * existContrPhysBllgMtrReln
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return boolean
     */
    public static boolean existContrPhysBllgMtrReln(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray outMsgArray = (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return outMsgArray.getValidCount() > 0;
    }
    // END 2017/08/09 K.Kitachi [QC#19122, ADD]

    // START 2017/09/14 M.Naito [QC#18534, ADD]
    /**
     * getSvcPgmTpCd
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return String
     */
    public static String getSvcPgmTpCd(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        return (String) mainClass.ssmBatchClient.queryObject("getSvcPgmTpCd", ssmPrm);
    }
    // END 2017/09/14 M.Naito [QC#18534, ADD]

    // START 2018/02/14 K.Kojima [QC#24145,ADD]
    /**
     * existCfsLeaseContrForBeforeCheck
     * @param dsContrTMsg DS_CONTRTMsg
     * @return boolean
     */
    public static boolean existCfsLeaseContrForBeforeCheck(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg) {
        if (dsContrTMsg == null) {
            return false;
        }
        Boolean cache = mainClass.existCfsLeaseContr.get(dsContrTMsg.dsContrPk.getValue());
        if (cache != null) {
            return cache.booleanValue();
        }
        boolean result = existCfsLeaseContr(mainClass, dsContrTMsg.glblCmpyCd.getValue(), null, null, null, null, null, dsContrTMsg.dsContrPk.getValue());
        mainClass.existCfsLeaseContr.put(dsContrTMsg.dsContrPk.getValue(), result);
        return result;
    }

    /**
     * existCfsLeaseContrForBeforeCheck
     * @param dsContrTMsg DS_CONTRTMsg
     * @return boolean
     */
    public static boolean existCfsLeaseContrForBeforeCheck(NSZC057001 mainClass, String glblCmpyCd, String serNum) {
        if (serNum == null) {
            return true;
        }
        Boolean cache = mainClass.existCfsLeaseContrForSerial.get(serNum);
        if (cache != null) {
            return cache.booleanValue();
        }
        boolean result = existCfsLeaseContr(mainClass, glblCmpyCd, serNum, null, null, null, null, null);
        mainClass.existCfsLeaseContrForSerial.put(serNum, result);
        return result;
    }
    // END 2018/02/14 K.Kojima [QC#24145,ADD]

    // START 2018/03/14 K.Kojima [QC#24238,ADD]
    /**
     * getSvcPgmTpCd
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return String
     */
    public static BigDecimal getNotesCount(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("svcMemoCatgCd", SVC_MEMO_CATG.CONTRACT_MEMO);
        ssmPrm.put("dsContrTrkTpCd", DS_CONTR_TRK_TP.CONTRACT_HEADER);
        ssmPrm.put("dsContrToStsCd", DS_CONTR_CTRL_STS.QA_HOLD);
        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getNotesCount", ssmPrm);
    }
    // END 2018/03/14 K.Kojima [QC#24238,ADD]
    // START 2018/04/17 T.Wada [QC#23378,ADD]
    /**
     * getBasePrcDealAmt
     * @param mainClass
     * @param glblCmpyCd
     * @param dsContrDtlPk
     * @param slsDt
     * @return
     */
    public static List<Map<String, Object>> getBasePrcDealAmt(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> contrPrcEffInfo = (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getBasePrcDealAmt", ssmPrm);
        return contrPrcEffInfo;
    }
    /**
     * getTotalPrcAllocAmt
     * @param mainClass
     * @param glblCmpyCd
     * @param dsContrPk
     * @param dsContrDtlPk
     * @param effFromDt
     * @param effThruDt
     * @return
     */
    public static BigDecimal getTotalPrcAllocAmt(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getTotalPrcAllocAmt", ssmPrm);
    }
    // END 2018/04/17 T.Wada [QC#23378,ADD]
    // START 2018/05/11 T.Wada [QC#24989,ADD]
    public static BigDecimal countInstalledMachines(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);

//        ssmPrm.put("qahd", DS_CONTR_CTRL_STS.QA_HOLD);
//        ssmPrm.put("entr", DS_CONTR_CTRL_STS.ENTERED);
//        ssmPrm.put("sigd", DS_CONTR_CTRL_STS.SINGED);
//        ssmPrm.put("actv", DS_CONTR_CTRL_STS.ACTIVE);

        ssmPrm.put("canc", DS_CONTR_DTL_STS.CANCELLED);
        ssmPrm.put("expd", DS_CONTR_DTL_STS.EXPIRED);
        ssmPrm.put("ordr", DS_CONTR_DTL_STS.ORDER);
        ssmPrm.put("trmd", DS_CONTR_DTL_STS.TERMINATED);
        ssmPrm.put("w4fb", DS_CONTR_DTL_STS.WAITING_FOR_FINAL_BILL);

        ssmPrm.put("instl", SVC_MACH_MSTR_STS.INSTALLED);
        ssmPrm.put("w4r", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("countInstalledMachines", ssmPrm);    
    }
    // END 2018/05/11 T.Wada [QC#24989,ADD]

    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
    /**
     * getBllgMtrInfo
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getBllgMtrInfo(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.ORDER };
        ssmPrm.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getBllgMtrInfo", ssmPrm);
    }
    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

    // START 2018/05/21 K.Kitachi [QC#26217, ADD]
    /**
     * getSumAddlChrgFlatDealPrcAmt
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return BigDecimal
     */
    public static BigDecimal getSumAddlChrgFlatDealPrcAmt(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", slsDt);
        String[] svcBillByTpCdList = new String[] {SVC_BILL_BY_TP.MACHINE, SVC_BILL_BY_TP.BASE };
        ssmPrm.put("svcBillByTpCdList", svcBillByTpCdList);

        return (BigDecimal) mainClass.ssmBatchClient.queryObject("getSumAddlChrgFlatDealPrcAmt", ssmPrm);
    }
    // END 2018/05/21 K.Kitachi [QC#26217, ADD]

    // START 2018/06/19 T.Wada [QC#26658, ADD]
    public static boolean isExistInvoicedBllg(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
            Map<String, Object> ssmPrm = new HashMap<String, Object>();
            ssmPrm.put("glblCmpyCd", glblCmpyCd);
            ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
            ssmPrm.put("flgY", ZYPConstant.FLG_ON_Y);

            BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("isExistInvoicedBllg", ssmPrm);
            return count.compareTo(BigDecimal.ZERO) > 0;
        }
    // END 2018/06/19 T.Wada [QC#26658, ADD]
    // QC#15410 Add Start
    public static boolean existSamePhysMtrByOthrContr(NSZC057001 mainClass, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", dsContrDtlTMsg.glblCmpyCd.getValue());
        ssmPrm.put("dsContrDtlPk", dsContrDtlTMsg.dsContrDtlPk.getValue());
        ssmPrm.put("svcMachMstrPk", dsContrDtlTMsg.svcMachMstrPk.getValue());
        ssmPrm.put("warranty", DS_CONTR_CATG.WARRANTY);
        ssmPrm.put("fromDate", dsContrDtlTMsg.contrEffFromDt.getValue());
        ssmPrm.put("thruDate", dsContrDtlTMsg.contrEffThruDt.getValue());
        ssmPrm.put("dsContrDtlStsCd_Cancelled", DS_CONTR_DTL_STS.CANCELLED);
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        ssmPrm.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        BigDecimal cnt = (BigDecimal) mainClass.ssmBatchClient.queryObject("countSamePhysMtrByOthrContr", ssmPrm);
        if (BigDecimal.ZERO.compareTo(cnt) == 0) {
            // Not Exists
            return false;
        }
        // Exists
        return true;
    }
    // QC#15410 Add Start

    // START 2018/07/09 K.Kitachi [QC#26834, ADD]
    /**
     * existLineMtrNotExstMachMtr
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return boolean
     */
    public static boolean existLineMtrNotExstMachMtr(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);

        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("countLineMtrNotExstMachMtr", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // END 2018/07/09 K.Kitachi [QC#26834, ADD]

    // START 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]
    /**
     * existCustSpclInstructionCheck
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @param svcLineBizCd String
     * @return boolean
     */
    public static boolean existCustSpclInstructionCheck(NSZC057001 mainClass, String glblCmpyCd, String billToCustCd, String svcLineBizCd) {
        Boolean cache = mainClass.existCustSpclInstruction.get(billToCustCd);
        if (cache != null) {
            return cache.booleanValue();
        }
        // mod start 2019/03/25 QC#30813
        boolean result = NWXC150001DsCheck.spclInstnDisplayDetermination(glblCmpyCd, DS_TRX_RULE_TP.CONTRACT, null, null, billToCustCd, null, BIZ_ID_NSAL0300, FUNC_CATG_ID, svcLineBizCd);
        // mod end 2019/03/25 QC#30813
        mainClass.existCustSpclInstruction.put(billToCustCd, result);
        return result;
    }
    // END 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]

    // START 2018/11/02 [QC#28627,ADD]
    /**
     * getParentDsContr
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @return DS_CONTRTMsg
     */
    public static DS_CONTRTMsg getParentDsContr(String glblCmpyCd, String dsContrNum) {
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
    // END 2018/11/02 [QC#28627,ADD]
    // Add Start 2018/12/10 K.Fujimoto QC#29079
    public static boolean existMismatchSubContrAndBllgMtr(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrDtlLinePk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        if (dsContrDtlLinePk != null) {
          ssmPrm.put("dsContrDtlLinePk", dsContrDtlLinePk);
        }
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("cancelledSts", DS_CONTR_CTRL_STS.CANCELLED);
        ssmPrm.put("contrTrmnFlg", ZYPConstant.FLG_OFF_N);
        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("existMismatchSubContrAndBllgMtr", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // Add End   2018/12/10 K.Fujimoto QC#29079

    // QC#55830 Add Start
    public static boolean existSkipSchedule(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("flgN", ZYPConstant.FLG_OFF_N);
        BigDecimal count = (BigDecimal) mainClass.ssmBatchClient.queryObject("existSkipSchedule", ssmPrm);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // QC#55830 Add End

    // add start 2020/03/24 QC#54318
    public static boolean existNotSetUplftPcyDtForContr(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("doNotUplift", CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        ssmPrm.put("lvlNum1", "1");
        ssmPrm.put("shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        ssmPrm.put("ordr", DS_CONTR_DTL_STS.ORDER);
        BigDecimal contrPk = (BigDecimal) mainClass.ssmBatchClient.queryObject("getNotSetUplftPcyDtForContr", ssmPrm);
        return hasValue(contrPk);
    }
    // START 2023/10/18 S.Moriai [QC#61952, MOD]
    //public static boolean existNotSetUplftPcyDtForContrDtl(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
    public static boolean existNotSetUplftPcyDtForContrDtlLvTwo(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
    // END   2023/10/18 S.Moriai [QC#61952, MOD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("doNotUplift", CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        // START 2023/10/18 S.Moriai [QC#61952, ADD]
        ssmPrm.put("shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        ssmPrm.put("ordr", DS_CONTR_DTL_STS.ORDER);
        // END   2023/10/18 S.Moriai [QC#61952, ADD]
        // START 2023/10/18 S.Moriai [QC#61952, DEL]
        //ssmPrm.put("lvlNum1", "1");
        // END   2023/10/18 S.Moriai [QC#61952, DEL]
        ssmPrm.put("lvlNum2", "2");
        // START 2023/10/18 S.Moriai [QC#61952, DEL]
        // ssmPrm.put("lvlNum3", "3");
        // END   2023/10/18 S.Moriai [QC#61952, DEL]
        // START 2023/10/18 S.Moriai [QC#61952, MOD]
        //BigDecimal contrDtlPk = (BigDecimal) mainClass.ssmBatchClient.queryObject("getNotSetUplftPcyDtForContrDtl", ssmPrm);
        BigDecimal contrDtlPk = (BigDecimal) mainClass.ssmBatchClient.queryObject("getNotSetUplftPcyDtForContrDtlLVTwo", ssmPrm);
        // END   2023/10/18 S.Moriai [QC#61952, MOD]
        return hasValue(contrDtlPk);
    }
    // add end 2020/03/24 QC#54318
    // START 2023/10/18 S.Moriai [QC#61952, ADD]
    public static boolean existNotSetUplftPcyDtForContrDtlLvThree(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("doNotUplift", CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        ssmPrm.put("shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        ssmPrm.put("ordr", DS_CONTR_DTL_STS.ORDER);
        ssmPrm.put("lvlNum3", "3");
        BigDecimal contrDtlPk = (BigDecimal) mainClass.ssmBatchClient.queryObject("getNotSetUplftPcyDtForContrDtlLVThree", ssmPrm);
        return hasValue(contrDtlPk);
    }
    // END   2023/10/18 S.Moriai [QC#61952, ADD]
    // 2022/11/25 QC#60398 Add Start
    public static boolean existHardCopyReqCheck(String glblCmpyCd, String slsDt, String billToCustCd) {

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiMsg.xxModeCd, PROCESS_MODE_TRANSACTION);
        setValue(apiMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.CONTRACT);
        setValue(apiMsg.billToCustCd, billToCustCd);
        setValue(apiMsg.slsDt, slsDt);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.TransactionRuleList.no(0).hardCopyReqFlg.getValue())) {
            return true;
        }
        return false;
    }

    // 2022/11/25 QC#60398 Add Start

    // QC#61408 2023/06/23 Add Start
    /**
     * getAnnualEscalationList
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @param slsdt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getAnnualEscalationList(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("slsDt", slsDt);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNmBase", DS_CONTR_BASE_USG_NM_B);
        paramMap.put("dsContrBaseUsgNmUsage", DS_CONTR_BASE_USG_NM_U);
        paramMap.put("dsContrCatgReg", DS_CONTR_CATG.REGULAR);
        paramMap.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        paramMap.put("dsContrCatgAgg", DS_CONTR_CATG.AGGREGATE);
        paramMap.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        paramMap.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        paramMap.put("flgOffN", ZYPConstant.FLG_OFF_N);
        String[] dsContrCatgList = new String[] {DS_CONTR_CATG.FLEET, DS_CONTR_CATG.AGGREGATE };
        paramMap.put("dsContrCatgList", dsContrCatgList);
        paramMap.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        paramMap.put("doNotUplift", CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        return (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getAnnualEscalationList", paramMap);
    }

    public static boolean existsUnmatchPcyDateAndBllgschd(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk, String pcyDt, String sqlId) {
        if (!hasValue(pcyDt)) {
            return false;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("pcyDt", pcyDt);
        paramMap.put("invTpInvoice", INV_TP.INVOICE);

        BigDecimal unmatchCnt = (BigDecimal) mainClass.ssmBatchClient.queryObject(sqlId, paramMap);
        if (BigDecimal.ZERO.compareTo(unmatchCnt) == 0) {
            return true;
        }
        return false;
    }
    // QC#61408 2023/06/23 Add End

    // START 2023/07/27 R.Kurahashi [QC#61710, ADD]
    /**
     * getContrAutoRnwTpCd
     * @param mainClass NSZC057001
     * @param dsContrTMsg DS_CONTRTMsg
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     * @return boolean
     */
    public static String getContrAutoRnwTpCd(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", dsContrTMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", dsContrTMsg.dsContrPk.getValue());

        if (dsContrDtlTMsg != null) {
            paramMap.put("dsContrDtlPk", dsContrDtlTMsg.dsContrDtlPk.getValue());
        } else {
            paramMap.put("dsContrMachLvlNum", DS_CONTR_MACH_LVL_NUM_1);
        }
        return (String) mainClass.ssmBatchClient.queryObject("getContrAutoRnwTpCd", paramMap);
    }

    /**
     * getContrAutoRnwTpCd
     * @param mainClass NSZC057001
     * @param dsContrTMsg DS_CONTRTMsg
     * @return boolean
     */
    public static String getContrAutoRnwTpCd(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg) {
        return getContrAutoRnwTpCd(mainClass, dsContrTMsg, null);
    }

    /**
     * getDoNotRenewCd
     * @param mainClass NSZC057001
     * @param dsContrTMsg DS_CONTRTMsg
     * @return boolean
     */
    public static String getDoNotRenewCd(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", dsContrTMsg.glblCmpyCd.getValue());
        paramMap.put("rewUsgFlg", ZYPConstant.FLG_OFF_N);
        paramMap.put("rnwBaseFlg", ZYPConstant.FLG_OFF_N);

        return (String) mainClass.ssmBatchClient.queryObject("getDoNotRenewCd", paramMap);
    }

    /**
     * isEndDateCheck
     * @param mainClass NSZC057001
     * @param dsContrTMsg DS_CONTRTMsg
     * @param dsContrDtlTMsg DS_CONTR_DTLTMsg
     * @param aggDsContrDtlTMsg DS_CONTR_DTLTMsg
     * @param doNotRenewCd String
     * @return boolean
     */
    public static boolean isEndDateCheck(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_DTLTMsg aggDsContrDtlTMsg, String doNotRenewCd) {

        // Machine Check
        String contrAutoRnwTpCd = getContrAutoRnwTpCd(mainClass, dsContrTMsg, dsContrDtlTMsg);
        if (!hasValue(contrAutoRnwTpCd)) {

            // Aggregate Check
            contrAutoRnwTpCd = getContrAutoRnwTpCd(mainClass, dsContrTMsg, aggDsContrDtlTMsg);
            if (!hasValue(contrAutoRnwTpCd)) {

                // Contract Check
                contrAutoRnwTpCd = getContrAutoRnwTpCd(mainClass, dsContrTMsg);
            }
        }
        return !doNotRenewCd.equals(contrAutoRnwTpCd);
    }
    // END 2023/07/27 R.Kurahashi [QC#61710, ADD]
    
    // START 2023/11/08 R.Kurahashi [QC#61568, ADD]
    /**
     * isBaseBillingCycleError
     * @param errDsContrDtlPkList ArrayList<BigDecimal>
     * @param dsContrDtlPk BigDecimal
     * @return boolean
     */
    public static boolean isBaseBillingCycleError(ArrayList<BigDecimal> errDsContrDtlPkList, BigDecimal dsContrDtlPk) {
        if (errDsContrDtlPkList.size() > 0) {
            for (BigDecimal errDsContrDtlPk : errDsContrDtlPkList) {
                if (errDsContrDtlPk.compareTo(dsContrDtlPk) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * getBillingCycleMatchErrList
     * @param mainClass NSZC057001
     * @param dsContrTMsg DS_CONTRTMsg
     * @return ArrayList<BigDecimal>
     */
    public static ArrayList<BigDecimal> getBaseBillingCycleMatchErrList(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg) {
        ArrayList<BigDecimal> billingCycleMatchErrList = new ArrayList<BigDecimal>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", dsContrTMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", dsContrTMsg.dsContrPk.getValue());
        paramMap.put("open", DS_BLLG_SCHD_STS.OPEN);
        paramMap.put("skip", SKIP_RECOV_TP.SKIP);

        ArrayList<String> dsContrDtlTpCdList = new ArrayList<String>();
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.BASE_AND_USAGE);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.BASE_ONLY);
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.ACCESSORIES);
            
        paramMap.put("dsContrDtlTpCdList", dsContrDtlTpCdList);

        ArrayList<String> dsContrDtlStsCdList = new ArrayList<String>();
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.ORDER);
        paramMap.put("dsContrDtlStsCdList", dsContrDtlStsCdList);

        paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getBaseBillingCycleMatchErrList", paramMap);
        for (Map<String, Object> result : resultList) {
            billingCycleMatchErrList.add((BigDecimal) result.get("DS_CONTR_DTL_PK"));
        }

        return billingCycleMatchErrList;
    }
    
    /**
     * getUsageBillingCycleMatchErrList
     * @param mainClass NSZC057001
     * @param dsContrTMsg DS_CONTRTMsg
     * @return HashMap<BigDecimal, HashMap<String, String>>
     */
    public static HashMap<BigDecimal, HashMap<String, String>> getUsageBillingCycleMatchErrList(NSZC057001 mainClass, DS_CONTRTMsg dsContrTMsg) {
        HashMap<BigDecimal, HashMap<String, String>> billingCycleMatchErrList = new HashMap<BigDecimal, HashMap<String, String>>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", dsContrTMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", dsContrTMsg.dsContrPk.getValue());
        paramMap.put("open", DS_BLLG_SCHD_STS.OPEN);
        paramMap.put("skip", SKIP_RECOV_TP.SKIP);
        paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);

        ArrayList<String> dsContrDtlTpCdList = new ArrayList<String>();
        dsContrDtlTpCdList.add(DS_CONTR_DTL_TP.BASE_AND_USAGE);

        paramMap.put("dsContrDtlTpCdList", dsContrDtlTpCdList);

        ArrayList<String> dsContrDtlStsCdList = new ArrayList<String>();
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.EXPIRED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.TERMINATED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        dsContrDtlStsCdList.add(DS_CONTR_DTL_STS.ORDER);
        paramMap.put("dsContrDtlStsCdList", dsContrDtlStsCdList);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) mainClass.ssmBatchClient.queryObjectList("getUsageBillingCycleMatchErrList", paramMap);

        BigDecimal dsContrDtlPk = null;
        String bllgMtrBllgCycleCd = null;
        String mtrLbDescTxt = null;
        HashMap<String, String> setMap;
        for (Map<String, Object> result : resultList) {
            dsContrDtlPk = (BigDecimal) result.get("DS_CONTR_DTL_PK");
            bllgMtrBllgCycleCd = (String) result.get("BLLG_MTR_BLLG_CYCLE_CD");
            mtrLbDescTxt = (String) result.get("MTR_LB_DESC_TXT");

            if (billingCycleMatchErrList.get(dsContrDtlPk) == null) {
                setMap = new HashMap<String, String>();
                setMap.put(mtrLbDescTxt, bllgMtrBllgCycleCd);
                billingCycleMatchErrList.put(dsContrDtlPk, setMap);
            } else {
                billingCycleMatchErrList.get(dsContrDtlPk).put(mtrLbDescTxt, bllgMtrBllgCycleCd);
            }
        }
        return billingCycleMatchErrList;
    }
    // END 2023/11/08 R.Kurahashi [QC#61568, ADD]
    
    // START 2023/11/13 S.Moriai [QC#61756, ADD]
    /**
     * getDsContactPersonEmail
     * @param mainClass NSZC057001
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return boolean
     */
    public static boolean getDsContactPersonEmail(NSZC057001 mainClass, String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContactPersonType", DS_CTAC_PNT_TP.EMAIL_WORK);
        paramMap.put("meterReadMethod", MTR_READ_METH.EMAIL);
        paramMap.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
        paramMap.put("dsContactDetailType",DS_CONTR_DTL_TP.BASE_AND_USAGE);
        paramMap.put("serviceContactType",SVC_CTAC_TP.METER_READ);
        paramMap.put("actvFlg",ZYPConstant.FLG_ON_Y);
        String DSContantPointValueText = (String) mainClass.ssmBatchClient.queryObject("getDsContactPersonEmail", paramMap);
        
        return hasValue(DSContantPointValueText);
    }
    // END 2023/11/13 S.Moriai [QC#61756, ADD]
}
