/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0690.common;

import static business.blap.NSAL0690.constant.NSAL0690Constant.LVL_NUM_1;
import static business.blap.NSAL0690.constant.NSAL0690Constant.LVL_NUM_2;
import static business.blap.NSAL0690.constant.NSAL0690Constant.LVL_NUM_3;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0690.NSAL0690CMsg;
import business.blap.NSAL0690.NSAL0690Query;
import business.blap.NSAL0690.NSAL0690_ACMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.BLLG_CYCLETMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_CHNG_RECTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC046001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2016/04/08   Hitachi         T.Tomita        Update          QC#6729
 * 2017/10/30   Hitachi         K.Kojima        Update          QC#21859
 * 2018/08/17   Hitachi         K.Kojima        Update          QC#27295
 *</pre>
 */
public class NSAL0690CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0690CMsg
     */
    public static void createPullDown(NSAL0690CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
        createBllgCyclePullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0690CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.RENEW_CONTRACT_OR_MACHINE);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
    }

    private static void createBllgCyclePullDown(NSAL0690CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE_UOM.class, cMsg.bllgCycleUomCd_H1, cMsg.bllgCycleUomDescTxt_H2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * Get Billing Cycle Data
     * @param glblCmpyCd String
     * @param bllgCycleUomCd String
     * @return BLLG_CYCLETMsgArray
     */
    public static BLLG_CYCLETMsgArray getBllgCycleData(String glblCmpyCd, String bllgCycleUomCd) {
        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("bllgCycleUomCd01", bllgCycleUomCd);
        return (BLLG_CYCLETMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param cMsg NSAL0690CMsg
     * @param aCMsg NSAL0690_ACMsg
     * @param baseUsgNm String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getPrcUpRatio(NSAL0690CMsg cMsg, NSAL0690_ACMsg aCMsg, String baseUsgNm) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // START 2015/11/26 T.Tsuchida [QC#1225,MOD]
        //queryMap.put("dsContrPk", aCMsg.dsContrPk_A1);
        queryMap.put("dsContrPk", aCMsg.dsContrPk_A1.getValue());
        // END 2015/11/26 T.Tsuchida [QC#1225,MOD]
        if (DS_CONTR_CATG.FLEET.equals(aCMsg.dsContrCatgCd_A1.getValue())) {
            queryMap.put("dsContrDtlTp", DS_CONTR_DTL_TP.FLEET);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(aCMsg.dsContrCatgCd_A1.getValue())) {
            queryMap.put("dsContrDtlTp", DS_CONTR_DTL_TP.AGGREGATE);
        }
        queryMap.put("usgNm", baseUsgNm);
        queryMap.put("MachLvlNum3", LVL_NUM_3);
        queryMap.put("MachLvlNum2", LVL_NUM_2);
        queryMap.put("MachLvlNum1", LVL_NUM_1);

        return (Map<String, Object>) NSAL0690Query.getInstance().getPrcUpRatio(queryMap);
    }

    // START 2017/10/30 K.Kojima [QC#21859,ADD]
    public static int calcBllgCycleCntFromDuration(String glblCmpyCd, String contrEffFromDt, String contrEffThruDt, String baseBllgCycleCd) {
        // calculate duration
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(contrEffFromDt);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = contrEffThruDt;
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return 0;
        }

        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        ZYPEZDItemValueSetter.setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bcTMsg.bllgCycleCd, baseBllgCycleCd);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (bcTMsg == null || durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }
    // END 2017/10/30 K.Kojima [QC#21859,ADD]

    // START 2018/08/17 K.Kojima [QC#27295,ADD]
    public static boolean createDsContrPrcChrgRec(String glblCmpyCd, BigDecimal dsContrPk, NSZC046001PMsg inPMsg) {
        BigDecimal count = NSAL0690Query.getInstance().getRnwLtrCtrlInfo(glblCmpyCd, dsContrPk);
        if (count == null || count.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        // insert DS_CONTR_PRC_CHNG_REC
        DS_CONTR_PRC_CHNG_RECTMsg tMsg = new DS_CONTR_PRC_CHNG_RECTMsg();

        for (int i = 0; i < inPMsg.xxContrDtlList.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtl = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrDtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtl.dsContrDtlPk, inPMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue());
            dsContrDtl = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(dsContrDtl);
            if (dsContrDtl == null || !ZYPCommonFunc.hasValue(dsContrDtl.rnwEffFromDt)) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcChngRecPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_CHNG_REC_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, inPMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(tMsg.rnwEffFromDt, dsContrDtl.rnwEffFromDt);
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }
    // END 2018/08/17 K.Kojima [QC#27295,ADD]
}
