/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0150;

import static business.blap.NSAL0150.constant.NSAL0150Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0150.common.NSAL0150CommonLogic;
import business.db.DS_CONTR_CATGTMsg;
import business.db.DS_INV_TGTR_TPTMsg;
import business.db.DS_MTR_READ_TPTMsg;
import business.db.DS_MTR_READ_TPTMsgArray;
import business.db.DS_WIN_DAYSTMsg;
import business.db.MTR_READ_METHTMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_COV_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/11/26   HITACHI         K.Kasai         Update          Unit Test #71
 * 2016/03/31   Hitachi         T.Tomita        Update          QC#4394
 * 2016/04/21   Hitachi         T.Tomita        Update          QC#4394
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/10/03   Hitachi         k.Kishimoto     Update          QC#12274
 * 2016/11/24   Hitachi         Y.Takeno        Update          QC#14992
 * 2016/12/06   Hitachi         Y.Takeno        Update          QC#14992-1
 * 2016/12/06   Hitachi         Y.Takeno        Update          QC#15200
 * 2017/09/07   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/09/15   Hitachi         K.Kojima        Update          QC#21125
 * 2017/09/26   Hitachi         K.Kim           Update          QC#18744
 * 2017/10/05   CITS            M.Naito         Update          QC#20440
 * 2017/12/13   Hitachi         M.Kidokoro      Update          QC#21681
 * 2018/03/08   Hitachi         K.Kojima        Update          QC#24507
 * 2018/05/10   Hitachi         K.Kojima        Update          QC#24817
 * 2018/05/25   CITS            M.Naito         Update          QC#15410
 * 2018/06/11   Hitachi         U.Kim           Update          QC#22480
 * 2018/06/25   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/06   Hitachi         T.Tomita        Update          QC#26972
 * 2018/07/09   Hitachi         A.Kohinata      Update          QC#26923
 * 2018/07/17   Hitachi         K.Kitachi       Update          QC#26764
 * 2018/07/24   Hitachi         K.Kojima        Update          QC#26791
 * 2018/09/26   Hitachi         K.Kojima        Update          QC#28388
 * 2018/10/15   Hitachi         K.Kitachi       Update          QC#28652
 * 2018/10/16   Hitachi         K.Kojima        Update          QC#28392-1
 * 2018/12/13   Hitachi         S.Kitamura      Update          QC#28860
 * 2019/03/25   Hitachi         T.Tomita        Update          QC#30791
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31089
 * 2020/02/07   Hitachi         A.Kohinata      Update          QC#55812
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/06/13   CITS            R.jin           Update          QC#60122
 * 2023/05/09   Hitachi         K.Kitachi       Update          QC#61469
 * 2023/05/16   CITS            T.Aizawa        Update          QC#58675
 * 2023/11/24   CITS            R.Jin           Update          QC#62422
 * 2024/01/25   Hitachi         T.Kawasue       Update          QC#63467
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 * 2024/04/01   Hitachi         T.Fukuta        Update          QC#63548
 * 2024/04/08   Hitachi         S.Moriai        Update          QC#63540
 *</pre>
 */
public final class NSAL0150Query extends S21SsmEZDQuerySupport {

    private static final NSAL0150Query MY_INSTANCE = new NSAL0150Query();

    private NSAL0150Query() {
        super();
    }

    public static NSAL0150Query getInstance() {
        return MY_INSTANCE;
    }

    public SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk, String serNum, String mdseCd) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            Map<String, Object> ssmPrm = new HashMap<String, Object>();
            ssmPrm.put("glblCmpyCd", glblCmpyCd);
            ssmPrm.put("serNum", serNum);
            ssmPrm.put("mdseCd", mdseCd);
            S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getSvcMachMstrBySerNum", ssmPrm);
            if (rslt != null && rslt.isCodeNormal()) {
                svcMachMstrPk = (BigDecimal) rslt.getResultObject();
            } else {
                return null;
            }
        }
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    public S21SsmEZDResult getSvcPhysMtr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return getSsmEZDClient().queryObjectList("getSvcPhysMtr", ssmPrm);
    }

    public S21SsmEZDResult getSvcPhysMtrRead(String glblCmpyCd, BigDecimal svcMachMstrPk, int rowNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("dsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        // START 2018/10/16 K.Kojima [QC#28392-1,DEL]
        // ssmPrm.put("rowNum", rowNum);
        // END 2018/10/16 K.Kojima [QC#28392-1,DEL]
        // Add Start 2016/11/24 <QC#14992>
        BigDecimal setDays = ZYPCodeDataUtil.getNumConstValue(MTR_HIST_AVG_DAYS, glblCmpyCd);
        if (setDays == null) {
            setDays = DEF_MTR_HIST_AVG_DAYS;
        }
        ssmPrm.put("setDays", setDays);
        // Add End   2016/11/24 <QC#14992>
        // START 2018/07/17 K.Kitachi [QC#26764, ADD]
        ssmPrm.put("slsDt", NSAL0150CommonLogic.getBizIdSlsDt());
        ssmPrm.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        ssmPrm.put("months", BLLG_CYCLE_UOM.MONTHS);
        ssmPrm.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        // END 2018/07/17 K.Kitachi [QC#26764, ADD]
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21FastTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        ssmPrm.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        ssmPrm.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        ssmPrm.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]
        return getSsmEZDClient().queryObjectList("getSvcPhysMtrRead", ssmPrm);
    }

    public S21SsmEZDResult getDsMtrReadTpGrp(String glblCmpyCd, List<String> dsMtrReadTpGrpCdList) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        ssmPrm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        return getSsmEZDClient().queryObjectList("getDsMtrReadTpGrp", ssmPrm);
    }

    public S21SsmEZDResult getLastUgwSvcPhysMtrRead(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        // TODO
        ssmPrm.put("dsMtrReadTpCd", DS_MTR_READ_TP.PERIODIC_METER_READING);
        return getSsmEZDClient().queryObjectList("getLastUgwSvcPhysMtrRead", ssmPrm);
    }

    // START 2018/05/10 K.Kojima [QC#24817,MOD]
    // public S21SsmEZDResult getSvcPhysMtrReadTmpl(String glblCmpyCd, BigDecimal svcMachMstrPk, String dsMtrReadTpCd) {
    // START 2018/07/24 K.Kojima [QC#26791,MOD]
    // public S21SsmEZDResult getSvcPhysMtrReadTmpl(String glblCmpyCd, BigDecimal svcMachMstrPk, String dsMtrReadTpCd, List<String> targetMtrLbCdList) {
    public S21SsmEZDResult getSvcPhysMtrReadTmpl(String glblCmpyCd, BigDecimal svcMachMstrPk, String dsMtrReadTpCd, List<String> targetMtrLbCdList, boolean billableReadsFlg) {
    // END 2018/07/24 K.Kojima [QC#26791,MOD]
    // END 2018/05/10 K.Kojima [QC#24817,MOD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("dsMtrReadTpCd", dsMtrReadTpCd);
        // START 2018/05/10 K.Kojima [QC#24817,ADD]
        if (targetMtrLbCdList.size() > 0) {
            ssmPrm.put("targetMtrLbCdList", targetMtrLbCdList);
        }
        // END 2018/05/10 K.Kojima [QC#24817,ADD]
        // START 2018/07/24 K.Kojima [QC#26791,ADD]
        if (billableReadsFlg) {
            ssmPrm.put("billableReadsFlg", ZYPConstant.FLG_ON_Y);
        } else {
            ssmPrm.put("billableReadsFlg", ZYPConstant.FLG_OFF_N);
        }
        // END 2018/07/24 K.Kojima [QC#26791,ADD]
        return getSsmEZDClient().queryObjectList("getSvcPhysMtrReadTmpl", ssmPrm);
    }

    public SVC_PHYS_MTR_READTMsg getSvcPhysMtrRead(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        SVC_PHYS_MTR_READTMsg prmTMsg = new SVC_PHYS_MTR_READTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcPhysMtrReadPk, svcPhysMtrReadPk);
        return (SVC_PHYS_MTR_READTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    public S21SsmEZDResult getMachDtl(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        ssmPrm.put("svcCtacTp1", SVC_CTAC_TP.METER_READ);
        ssmPrm.put("svcCtacTp2", SVC_CTAC_TP.FAX);
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        // START 2019/04/09 K.Kitachi [QC#31089, ADD]
        ssmPrm.put("svcMemoCatgCd", SVC_MEMO_CATG.METER_ENTRY_MEMO);
        // END 2019/04/09 K.Kitachi [QC#31089, ADD]
        // START 2023/11/24 R.Jin [QC#62422, ADD]
        ssmPrm.put("schdAgmtStsActive", SCHD_AGMT_STS.ACTIVE);
        ssmPrm.put("schdAgmtStsCancel", SCHD_AGMT_STS.CANCELLED);
        // END 2023/11/24 R.Jin [QC#62422, ADD]
        return getSsmEZDClient().queryObject("getMachDtl", ssmPrm);
    }

    // ADD START 2015/11/26 K.Kasai [Unit Test #71]
    // START 2019/04/09 K.Kitachi [QC#31089, DEL]
//    public static SVC_MEMOTMsgArray getSvcMemo(String glblCmpyCd, BigDecimal svcMachMstrPk) {
//        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
//        inMsg.setSQLID("006");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
//        inMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.SERIAL_NUMBER);
//        return (SVC_MEMOTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
    // END 2019/04/09 K.Kitachi [QC#31089, DEL]

    // START 2019/04/09 K.Kitachi [QC#31089, ADD]
    public SVC_MEMOTMsg getSvcMemoForUpdateNoWait(String glblCmpyCd, BigDecimal svcMemoPk) {
        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcMemoPk, svcMemoPk);
        return (SVC_MEMOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
    }

    public BigDecimal getSvcMemoCnt(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcMachMstrPk", svcMachMstrPk);
        params.put("svcMemoCatgCd", SVC_MEMO_CATG.METER_ENTRY_MEMO);
        return (BigDecimal) getSsmEZDClient().queryObject("getSvcMemoCnt", params).getResultObject();
    }
    // END 2019/04/09 K.Kitachi [QC#31089, ADD]

    @SuppressWarnings("unchecked")
    public Map<String, Object> getIwrRgtnCond(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getIwrRgtnCond", ssmPrm);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rslt != null && rslt.isCodeNormal()) {
            return rsltList.get(0);
        } else {
            return null;
        }
    }

    // START 2016/06/10 [QC#9591, ADD]
    public List<Map<String, Object>> getReadMtrCntList(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getReadMtrCnt", ssmPrm);
        return (List<Map<String, Object>>) rslt.getResultObject();
    }
    // END   2016/06/10 [QC#9591, ADD]

    // START 2017/10/05 M.Naito [QC#20440, DEL]
//    public static BRTMsgArray getBr(String glblCmpyCd, String brCd) {
//        BRTMsg inMsg = new BRTMsg();
//        inMsg.setSQLID("001");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("brCd01", brCd);
//        return (BRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
    // END 2017/10/05 M.Naito [QC#20440, DEL]
    // START 2017/10/05 M.Naito [QC#20440, ADD]
    public static SVC_CONTR_BRTMsg getSvcContrBr(String glblCmpyCd, String svcContrBrCd) {
        SVC_CONTR_BRTMsg inMsg = new SVC_CONTR_BRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcContrBrCd, svcContrBrCd);
        return (SVC_CONTR_BRTMsg)EZDFastTBLAccessor.findByKey(inMsg);
    }
    // END 2017/10/05 M.Naito [QC#20440, ADD]

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSubContr(String glblCmpyCd, BigDecimal dsContrDtlPK, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPK);
        ssmPrm.put("slsDt", slsDt);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getSubContr", ssmPrm);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rslt != null && rslt.isCodeNormal()) {
            return rsltList.get(0);
        } else {
            return null;
        }
    }

    public static DS_CONTR_CATGTMsg getDsContrCatg(String glblCmpyCd, String dsContrCatg) {
        DS_CONTR_CATGTMsg inMsg = new DS_CONTR_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrCatgCd, dsContrCatg);
        return (DS_CONTR_CATGTMsg)EZDFastTBLAccessor.findByKey(inMsg);
    }

    public static SVC_COV_TPTMsg getSvcCovTp(String glblCmpyCd, String svcCovTp) {
        SVC_COV_TPTMsg inMsg = new SVC_COV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcCovTpCd, svcCovTp);
        return (SVC_COV_TPTMsg)EZDFastTBLAccessor.findByKey(inMsg);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getDsContrBllgSchdInv(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("invTpCd", INV_TP.INVOICE);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getDsContrBllgSchdInv", ssmPrm);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rslt != null && rslt.isCodeNormal()) {
            return rsltList.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getBllgCycle(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getBllgCycle", ssmPrm);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rslt != null && rslt.isCodeNormal()) {
            return rsltList.get(0);
        } else {
            return null;
        }
    }

    // START 2018/03/08 K.Kojima [QC#24507,DEL]
    // public boolean existStaplesIncl(String glblCmpyCd, BigDecimal dsContrDtlPk, String svcTermCondAttrbNm) {
    //     Map<String, Object> ssmPrm = new HashMap<String, Object>();
    //     ssmPrm.put("glblCmpyCd", glblCmpyCd);
    //     ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
    //     ssmPrm.put("svcTermCondAttrbNm", svcTermCondAttrbNm);
    //     S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("existStaplesIncl", ssmPrm);
    //     if (rslt != null && rslt.isCodeNormal()) {
    //         List<BigDecimal> cnt = (List<BigDecimal>) rslt.getResultObject();
    //         if (cnt.get(0) == BigDecimal.ZERO) {
    //             return false;
    //         } else {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    // END 2018/03/08 K.Kojima [QC#24507,DEL]
    // ADD END 2015/11/26 K.Kasai [Unit Test #71]

    public S21SsmEZDResult getDsContrBllgSchd(String glblCmpyCd, BigDecimal svcPhysMtrPk, String fromDt, String thruDt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("fromDt", fromDt);
        prm.put("thruDt", thruDt);
        prm.put("dsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        return getSsmEZDClient().queryObjectList("getDsContrBllgSchd", prm);
    }

    @SuppressWarnings("unchecked")
    public SvcPhysMtrReadInfoBean getLtstMtrRead(String glblCmpyCd, BigDecimal svcPhysMtrPk) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObject("getLtstMtrRead", prm);
        if (ssmRslt != null && ssmRslt.isCodeNormal()) {
            Map<String, Object> rsltMap = (Map<String, Object>) ssmRslt.getResultObject();
            if (rsltMap == null) {
                return null;
            } else {
                return toBean(rsltMap);
            }
        } else {
            return null;
        }
    }

    private static SvcPhysMtrReadInfoBean toBean(Map<String, Object> rsltMap) {
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        return bean;
    }

    // START 2016/03/31 T.Tomita [QC#4394, ADD]
    // START 2018/05/25 M.Naito [QC#15410, MOD]
//    public String getDefaultMeterReadDate(String glblCmpyCd, DS_CONTR_DTLTMsg dsContrDtl, String slsDt) {
    public String getDefaultMeterReadDate(String glblCmpyCd, List<BigDecimal> dsContrDtlPkList, String slsDt) {
    // END 2018/05/25 M.Naito [QC#15410, MOD]
        if (dsContrDtlPkList == null || dsContrDtlPkList.size() == 0) {
            return slsDt;
        }
        // mod start 2016/04/21 CSA Defect#4394
        BigDecimal nextSchdDtWindowDays = ZYPCodeDataUtil.getNumConstValue(NEXT_SCHD_DT_WINDOW_DAYS, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(nextSchdDtWindowDays)) {
            nextSchdDtWindowDays = DEF_NEXT_SCHD_DT_WINDOW_DAYS;
        }

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        // START 2018/05/25 M.Naito [QC#15410, MOD]
//        ssmPrm.put("dsContrDtlPk", dsContrDtl.dsContrDtlPk.getValue());
        ssmPrm.put("dsContrDtlPkList", dsContrDtlPkList);
        // END 2018/05/25 M.Naito [QC#15410, MOD]
        ssmPrm.put("dsBllgSchdStsCd", DS_BLLG_SCHD_STS.OPEN);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("dateFormat", "yyyymmdd");
        ssmPrm.put("windowDays", nextSchdDtWindowDays);
        // mod end 2016/04/21 CSA Defect#4394
        String mtrReadDt = slsDt;
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getDefaultMeterReadDate", ssmPrm);
        if (rslt != null && rslt.isCodeNormal()) {
            mtrReadDt = (String) rslt.getResultObject();
        }
        return mtrReadDt;
    }
    // END 2016/03/31 T.Tomita [QC#4394, ADD]
    // Add Start 2016/10/03 <QC#12271>
    public Map<String, Object> getFsrVisitInfo(String glblCmpyCd, String svcTaskNum, BigDecimal svcMachMstrPk) {

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcTaskNum", svcTaskNum);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getFsrVisitInfo", ssmPrm);
        if (rslt != null && rslt.isCodeNormal()) {
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            if (rsltMap == null) {
                return null;
            } else {
                return rsltMap;
            }
        } else {
            return null;
        }
    }
    // Add End   2016/10/03 <QC#12271>
    // Add Start 2016/12/06 <QC#14992-1>
    public static MTR_READ_METHTMsg getMtrReadMeth(String glblCmpyCd, String mtrReadMethCd) {
        MTR_READ_METHTMsg inMsg = new MTR_READ_METHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mtrReadMethCd, mtrReadMethCd);
        return (MTR_READ_METHTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // Add End   2016/12/06 <QC#14992-1>

    // START 2018/05/25 M.Naito [QC#15410, MOD]
    // START 2016/12/06 [QC#15200, ADD]
//    public Map<String, Object> getContrDtlKeysByMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
    public List<Map<String, Object>> getContrDtlKeysByMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        ssmParam.put("contrCloDt", trxDt);
        ssmParam.put("warranty", DS_CONTR_CATG.WARRANTY);
        ssmParam.put("dsContrDtlStsCanc", DS_CONTR_DTL_STS.CANCELLED);
        ssmParam.put("isEffective", ZYPConstant.FLG_ON_Y);
        // Add Start 2019/03/25 QC#30791
        ssmParam.put("slsDt", trxDt);
        // Add End 2019/03/25 QC#30791
        // Add Start 2022/06/13 QC#60122
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED_HOLD);
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        ssmParam.put("dsContrDtlStsActv", DS_CONTR_DTL_STS.ACTIVE);
        // Add Start 2022/06/13 QC#60122
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        ssmParam.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]


//        S21SsmEZDResult result = getSsmEZDClient().queryObject("getContrDtlKeysByMachMstrPk", ssmParam);
//        if (result != null && result.isCodeNormal()) {
//            return (Map<String, Object>) result.getResultObject();
//        }
//
//        return null;
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getContrDtlKeysByMachMstrPk", ssmParam).getResultObject();
    }
    // END 2018/05/25 M.Naito [QC#15410, MOD]

    public Map<String, Object> getContrDtlKeysByMachMstrPkForOrderHistory(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        ssmParam.put("contrCloDt", trxDt);
        ssmParam.put("warranty", DS_CONTR_CATG.WARRANTY);
        ssmParam.put("dsContrDtlStsCanc", DS_CONTR_DTL_STS.CANCELLED);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getContrDtlKeysByMachMstrPk", ssmParam);
        if (result != null && result.isCodeNormal()) {
            return (Map<String, Object>) result.getResultObject();
        }

        return null;
    }

    // END   2016/12/06 [QC#15200, ADD]

    // add start 2017/09/07 QC#15134
    public DS_MTR_READ_TPTMsgArray getDsMtrReadTpList(String glblCmpyCd) {
        DS_MTR_READ_TPTMsg inMsg = new DS_MTR_READ_TPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrEntryScrDplyFlg01", ZYPConstant.FLG_ON_Y);
        return (DS_MTR_READ_TPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // add end 2017/09/07 QC#15134

    // START 2017/09/15 K.Kojima [QC#21125,ADD]
    public BigDecimal countInvalidCheckTarget(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcPhysMtrReadPk", svcPhysMtrReadPk);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("countInvalidCheckTarget", ssmParam);
        if (result != null && result.isCodeNormal()) {
            return (BigDecimal) result.getResultObject();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal countDsContrBllgShedForInvalidCheck(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcPhysMtrReadPk", svcPhysMtrReadPk);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("countDsContrBllgShedForInvalidCheck", ssmParam);
        if (result != null && result.isCodeNormal()) {
            return (BigDecimal) result.getResultObject();
        }
        return BigDecimal.ZERO;
    }
    // END 2017/09/15 K.Kojima [QC#21125,ADD]

    // START 2017/09/26 K.Kim [QC#18744, ADD]
    public String getSplyInclFlg(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getSplyInclFlg", ssmParam);
        return (String) result.getResultObject();
    }
    // END 2017/09/26 K.Kim [QC#18744, ADD]
    // START 2017/12/13 M.Kidokoro [QC#21681, ADD]
    public String getInvalidMtrFlg(String glblCmpyCd, BigDecimal svcMachMstrPk, String mtrReadDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("mtrReadDt", mtrReadDt);
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getInvalidMtrFlg", ssmParam);
        return (String) result.getResultObject();
    }
    // END 2017/12/13 M.Kidokoro [QC#21681, ADD]

    // START 2018/03/08 K.Kojima [QC#24507,ADD]
    /**
     * get SvcTermCondDataDispTxt
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPk BigDecimal
     * @param attrbNm String
     * @param dsContrCatgCd String
     * @return String
     */
    public String getSvcTermCondDataDispTxt(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrPk, String attrbNm, String dsContrCatgCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("svcTermCondAttrbNm", attrbNm);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrPk", dsContrPk);
        String fltFlg = ZYPConstant.FLG_OFF_N;
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            fltFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmPrm.put("fltFlg", fltFlg);
        return (String) getSsmEZDClient().queryObject("getSvcTermCondDataDispTxt", ssmPrm).getResultObject();
    }
    // END 2018/03/08 K.Kojima [QC#24507,ADD]

    // START 2018/05/10 K.Kojima [QC#24817,ADD]
    /**
     * countMeterEntryContr
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getMeterEntryContrDtlPk(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.DRAFT);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ENTERED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.SINGED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
        ssmPrm.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        ssmPrm.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getMeterEntryContrDtlPk", ssmPrm).getResultObject();
    }

    /**
     * getBilliableMeterNonMtrFmla
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param dsContrDtlPkList List<String>
     * @return List<String>
     */
    public List<String> getBilliableMeterNonMtrFmla(String glblCmpyCd, BigDecimal svcMachMstrPk, List<BigDecimal> dsContrDtlPkList) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("dsContrDtlPkList", dsContrDtlPkList);
        return (List<String>) getSsmEZDClient().queryObjectList("getBilliableMeterNonMtrFmla", ssmPrm).getResultObject();
    }
    // END 2018/05/10 K.Kojima [QC#24817,ADD]
    // START 2018/06/11 U.Kim [QC#22480, ADD]
    public String getTelNum(String glblCmpyCd, String locNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("locNum", locNum);
        ssmPrm.put("ctacTpCd", CTAC_TP.SUPPLIER_CONTACT);
        ssmPrm.put("ctacPsnActvFlg", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("dsCtacPntTp",DS_CTAC_PNT_TP.PHONE_WORK);
        return (String) getSsmEZDClient().queryObject("getTelNum", ssmPrm).getResultObject();
    }
    // END 2018/06/11 U.Kim [QC#22480, ADD]

    // START 2018/06/25 T.Ogura [QC#26336,ADD]
    /**
     * createCSV
     * @param cMsg NSAL0150CMsg
     * @param sMsg NSAL0150SMsg
     */
    public void createCSV(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("svcMachMstrPk", cMsg.svcMachMstrPk.getValue());
        // START 2023/05/16 t.aizawa [QC#58675, ADD]
        params.put("invTpCd", INV_TP.CREDIT_MEMO);
        // END 2023/05/16 t.aizawa [QC#58675, ADD]
        params.put("rowNum", sMsg.F.length());
        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        params.put("effFromDt", cMsg.effFromDt.getValue());
        params.put("effThruDt", cMsg.effThruDt.getValue());
        // END 2024/04/08 S.Moriai [QC#63540, ADD]
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID);
            cMsg.xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            stmtSelect = ssmLLClient.createPreparedStatement("getDownloadList", params, execParam);
            rs = stmtSelect.executeQuery();

            NSAL0150CommonLogic.writeCsvFile(cMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }
    // END   2018/06/25 T.Ogura [QC#26336,ADD]
    // Add Start 2018/07/06 QC#26972
    public List<Map<String, Object>> getTermCondForMach(String glblCmpyCd, BigDecimal dsContrDtlPk, List<BigDecimal> attrbPkList) {
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("svcTermCondAttrbList", attrbPkList);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTermCondForMach", params).getResultObject();
    }

    public List<Map<String, Object>> getTermCondForContr(String glblCmpyCd, BigDecimal dsContrPk, List<BigDecimal> attrbPkList) {
        if (!ZYPCommonFunc.hasValue(dsContrPk)) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        params.put("svcTermCondAttrbList", attrbPkList);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTermCondForContr", params).getResultObject();
    }

    public BigDecimal getMachineSupplyOrderCnt(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return BigDecimal.ZERO;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcMachMstrPk", svcMachMstrPk);
        params.put("cpoHdrStsCd", ORD_HDR_STS.VALIDATED);
        return (BigDecimal) getSsmEZDClient().queryObject("getMachineSupplyOrderCnt", params).getResultObject();
    }

    public BigDecimal getFleetSupplyOrderCnt(String glblCmpyCd, String dsContrNum) {
        if (!ZYPCommonFunc.hasValue(dsContrNum)) {
            return BigDecimal.ZERO;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrNum", dsContrNum);
        params.put("cpoHdrStsCd", ORD_HDR_STS.VALIDATED);
        return (BigDecimal) getSsmEZDClient().queryObject("getFleetSupplyOrderCnt", params).getResultObject();
    }
    // Add End 2018/07/06 QC#26972

    // add start 2018/07/09 QC#26923
    public BigDecimal getSplyReadExclCustCnt(String glblCmpyCd, String slsDt, BigDecimal svcMachMstrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", slsDt);
        params.put("svcMachMstrPk", svcMachMstrPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getSplyReadExclCustCnt", params).getResultObject();
    }
    // add end 2018/07/09 QC#26923

    // START 2018/09/26 K.Kojima [QC#28388,ADD]
    public BigDecimal getRollOverExchReadCnt(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcMachMstrPk", svcMachMstrPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getRollOverExchReadCnt", params).getResultObject();
    }
    // END 2018/09/26 K.Kojima [QC#28388,ADD]

    // START 2018/10/15 K.Kitachi [QC#28652, ADD]
    public String convLineBizTpToSvcLineBiz(String glblCmpyCd, String lineBizTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("lineBizTpCd", lineBizTpCd);
        return (String) getSsmEZDClient().queryObject("convLineBizTpToSvcLineBiz", params).getResultObject();
    }
    // END 2018/10/15 K.Kitachi [QC#28652, ADD]

    // START 2018/10/16 K.Kojima [QC#28392-1,ADD]
    public List<Map<String, Object>> getStartReadInvoiceThruDt(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcMachMstrPk", svcMachMstrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getStartReadInvoiceThruDt", params).getResultObject();
    }
    // END 2018/10/16 K.Kojima [QC#28392-1,ADD]
    // START 2018/12/13 S.Kitamura [QC#28860,ADD]
    public Map<String, Object> getPrimaryMeterEntryContr(String glblCmpyCd, List<BigDecimal> dsContrDtlPkList, String slsDt) {
        if (dsContrDtlPkList == null || dsContrDtlPkList.size() == 0) {
            return null;
        }
        BigDecimal nextSchdDtWindowDays = ZYPCodeDataUtil.getNumConstValue(NEXT_SCHD_DT_WINDOW_DAYS, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(nextSchdDtWindowDays)) {
            nextSchdDtWindowDays = DEF_NEXT_SCHD_DT_WINDOW_DAYS;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPkList", dsContrDtlPkList);
        ssmPrm.put("dsBllgSchdStsCd", DS_BLLG_SCHD_STS.OPEN);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("dateFormat", "yyyymmdd");
        ssmPrm.put("windowDays", nextSchdDtWindowDays);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getPrimaryMeterEntryContr", ssmPrm);
        Map<String, Object> res = null;
        if (rslt != null && rslt.isCodeNormal()) {
            res = ((List<Map<String, Object>>) rslt.getResultObject()).get(0);
        } else {
            ssmPrm.put("glblCmpyCd", glblCmpyCd);
            ssmPrm.put("dsContrDtlPkList", dsContrDtlPkList);
            rslt = getSsmEZDClient().queryObject("getMinDsContrNumContr", ssmPrm);
            if (rslt != null && rslt.isCodeNormal()) {
                res = (Map<String, Object>) rslt.getResultObject();
            }
        }
        return res;
    }
    // END 2018/12/13 S.Kitamura [QC#28860,ADD]

    // add start 2020/02/07 QC#55812
    public SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrReadByGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        return (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // add end 2020/02/07 QC#55812

    // START 2020/03/03 [QC#56123, ADD]
    public Map<String, Object> getMaxUpTm(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        return (Map<String, Object>)getSsmEZDClient().queryObject("getMaxUpTm", ssmPrm).getResultObject();
    }

    public String getMaxInvThruDt(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        Map<String, Object> retMap = (Map<String, Object>)getSsmEZDClient().queryObject("getMaxInvThruDt", ssmPrm).getResultObject();
        return (String)retMap.get("MAX_INV_THRU_DT");
    }

    public DS_MTR_READ_TPTMsg getDsMtrReadTpTMsg(String glblCmpyCd, String dsMtrReadTpCd) {
        DS_MTR_READ_TPTMsg inMsg = new DS_MTR_READ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsMtrReadTpCd, dsMtrReadTpCd);
        return (DS_MTR_READ_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END  2020/03/03 [QC#56123, ADD]

    // START 2022/03/22 [QC#59683, ADD]
    public DS_INV_TGTR_TPTMsg getDsInvTgtrTp(String glblCmpyCd, String dsInvTgtrTp) {
        DS_INV_TGTR_TPTMsg inMsg = new DS_INV_TGTR_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsInvTgtrTpCd, dsInvTgtrTp);
        return (DS_INV_TGTR_TPTMsg) EZDFastTBLAccessor.findByKey(inMsg);
    }
    // END   2022/03/22 [QC#59683, ADD]

    // START 2024/01/25 T.Kawasue [QC#63467, ADD]
    public String getDsContrCtrlStsNm(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk){
        Map<String, Object> params = new HashMap<String, Object>();
        String activePendingPOContrStsNm = ZYPCodeDataUtil.getVarCharConstValue("ACTV_PENDING_PO_CONTR_STS_NM", glblCmpyCd);
        String activeRenewalHoldContrStsNm = ZYPCodeDataUtil.getVarCharConstValue("ACTV_RENEWAL_HOLD_CONTR_STS_NM", glblCmpyCd);
        String allPerTrmnContrStsNm = ZYPCodeDataUtil.getVarCharConstValue("ALL_PER_TRMN_CONTR_STS_NM", glblCmpyCd);
        params.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        params.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        params.put("dsContrCtrlStsCdActive", DS_CONTR_CTRL_STS.ACTIVE);
        params.put("dsContrCtrlStsCdRenewalHoldForPO", DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO);
        params.put("activePendingPO", activePendingPOContrStsNm);
        params.put("dsContrCtrlStsCdActive", DS_CONTR_CTRL_STS.ACTIVE);
        params.put("dsContrCtrlStsCdRenewalHold", DS_CONTR_CTRL_STS.RENEWAL_HOLD);
        params.put("activeRenewalHold", DS_CONTR_CTRL_STS.RENEWAL_HOLD);
        params.put("activeRenewalHoldContrStsNm", activeRenewalHoldContrStsNm);
        params.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        params.put("allPeriodTermination", allPerTrmnContrStsNm);
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        params.put("svcMachMstrPk", svcMachMstrPk);
        return (String) getSsmEZDClient().queryObject("getDsContrCtrlStsNm", params).getResultObject();
    }
    // END 2024/01/25 T.Kawasue [QC#63467, ADD]

    // START 2024/03/26 K.Watanabe [QC#63549, ADD]
    /**
     * getFinalReadPeriod
     * @param glblCmpyCd String 
     * @param svcMachMstrPk BigDecimal
     * @param mtrReadDt String
     * @return Integer
     */
    public Integer getFinalReadPeriod(String glblCmpyCd, BigDecimal svcMachMstrPk, String mtrReadDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        ssmPrm.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, DS_WIN_DAYS_TRGT_PER_TXT);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.svcLineBizCd, DS_WIN_DAYS_SVC_LINE_BIZ_CD_ALL);
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21ApiTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        ssmPrm.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        ssmPrm.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        ssmPrm.put("months", BLLG_CYCLE_UOM.MONTHS);
        ssmPrm.put("usgChrgFlg", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("invFlg", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("dsBllgSchdStsCd", DS_BLLG_SCHD_STS.CLOSE);
        ssmPrm.put("skipRecovTp", SKIP_RECOV_TP.SKIP);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("svcLineBizCd", DS_WIN_DAYS_SVC_LINE_BIZ_CD_ALL);
        ssmPrm.put("mtrReadDt", mtrReadDt);
        return (Integer) getSsmEZDClient().queryObject("getFinalReadPeriod", ssmPrm).getResultObject();
    }
    // END   2024/03/26 K.Watanabe [QC#63549, ADD]
}
