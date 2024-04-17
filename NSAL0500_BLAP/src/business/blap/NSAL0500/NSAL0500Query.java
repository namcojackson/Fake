/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0500;

import static business.blap.NSAL0500.constant.NSAL0500Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0500.constant.NSAL0500Constant;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_SUB_CONTRTMsg;
import business.db.SVC_MEMOTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/06   Hitachi         T.Tsuchida      Update          N/A
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2016/01/13   Hitachi         T.Tsuchida      Update          QC#2820
 * 2016/02/18   Hitachi         K.Kasai         Update          QC#3706
 * 2016/03/03   Hitachi         T.Tomita        Update          QC#3710
 * 2016/03/28   Hitachi         K.Kasai         Update          QC#4576
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 * 2017/03/01   Hitachi         T.Mizuki        Update          QC#17575
 * 2018/12/10   Hitachi         K.Fujimoto      Update          QC#29079
 *</pre>
 */
public final class NSAL0500Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0500Query INSTANCE = new NSAL0500Query();

    /**
     * Constructor.
     */
    private NSAL0500Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0500Query
     */
    public static NSAL0500Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get a recent Sub Contract PK
     * @param cMsg NSAL0500CMsg
     * @param salesDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRecentSubContrPk(NSAL0500CMsg cMsg, String salesDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("salesDt", salesDt);
        ssmParam.put("rowNum", 1);
        return getSsmEZDClient().queryEZDMsg("getRecentSubContrPk", ssmParam, cMsg);
    }

    /**
     * Get a Contract Info
     * @param cMsg NSAL0500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(NSAL0500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        // START 2016/03/03 T.Tomita [QC#3710, DEL]
        // ssmParam.put("vndTpCdIsSvcDealer", VND_TP.SERVICE_DEALER);
        // END 2016/03/03 T.Tomita [QC#3710, DEL]
        return getSsmEZDClient().queryEZDMsg("getContrInfo", ssmParam, cMsg);
    }

    /**
     * Get a Contract Detail Info
     * @param cMsg NSAL0500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlInfo(NSAL0500CMsg cMsg) {
        return getSsmEZDClient().queryObjectList("getContrDtlInfo", cMsg);
    }

    /**
     * Get a Customer Billing History Info
     * @param cMsg NSAL0500CMsg
     * @param sMsg NSAL0500SMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCustHistInfo(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg, BigDecimal dsContrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("dsContrPk", dsContrPk);
        return getSsmEZDClient().queryEZDMsgArray("getCustHistInfo", ssmParam, sMsg.A);
    }

    /**
     * Get a Meter Billing History Info
     * @param cMsg NSAL0500CMsg
     * @param sMsg NSAL0500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrHistInfo(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("svcInvChrgTpCdIsMeter", SVC_INV_CHRG_TP.METER_CHARGE);
        return getSsmEZDClient().queryEZDMsgArray("getMtrHistInfo", ssmParam, sMsg.B);
    }

    /**
     * Get a Dealer Billing History Info
     * @param cMsg NSAL0500CMsg
     * @param sMsg NSAL0500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDealHistInfo(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getDealHistInfo", cMsg, sMsg.C);
    }

    /**
     * getSvcMemoTxt
     * @param cMsg NSAL0500CMsg
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    public List<String> getSvcCmntTxt(NSAL0500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("svcMemoCatgCd", SVC_MEMO_CATG.CONTRACT_MEMO);
        ssmParam.put("svcMemoTpList", getSvcMemoTpList());
        return (List<String>) getSsmEZDClient().queryObjectList("getSvcCmntTxt", ssmParam).getResultObject();
    }

    /**
     * Get a VND
     * @param cMsg NSAL0500CMsg
     * @return String
     */
    public String getVnd(NSAL0500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("rgtnStsCdIsReadyForOrder", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        // START 2016/03/03 T.Tomita [QC#3710, DEL]
        // ssmParam.put("vndTpCdIsSvcDealer", VND_TP.SERVICE_DEALER);
        // END 2016/03/03 T.Tomita [QC#3710, DEL]
        @SuppressWarnings("unchecked")
        List<String> vndList = (List<String>) getSsmEZDClient().queryObjectList("getVndNm", ssmParam).getResultObject();
        if (vndList.size() <= 0) {
          return null;
        }
        return vndList.get(0);
    }

    /**
     * Get a Default Tech
     * @param cMsg NSAL0500CMsg
     * @return String
     */
    public String getDefTech(NSAL0500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("maxThruDt", NSAL0500Constant.MAX_THRU_DT);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        // add start 2016/02/18 CSA Defect#3706
        ssmParam.put("ctacPsnActvFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("delFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("rowNum", BigDecimal.ONE);
        // add end 2016/02/18 CSA Defect#3706
        @SuppressWarnings("unchecked")
        List<String> techList = (List<String>) getSsmEZDClient().queryObjectList("getDefTech", ssmParam).getResultObject();
        if (techList.size() <= 0) {
          return null;
      }
      return techList.get(0);
    }

    // START 2016/12/14 K.Kojima [QC#15653,MOD]
    // /**
    //  * Get a Tech
    //  * @param cMsg NSAL0500CMsg
    //  * @return TECH_MSTRTMsg
    //  */
    // public TECH_MSTRTMsg getTech(NSAL0500CMsg cMsg) {
    // 
    //     TECH_MSTRTMsg inMsg = new TECH_MSTRTMsg();
    //     setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
    //     setValue(inMsg.techTocCd, cMsg.techTocCd);
    //     return (TECH_MSTRTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    // }
    /**
     * Get a Tech
     * @param glblCmpyCd String
     * @param techTocCd String
     * @param slsDt String
     * @return String Tech Code
     */
    public String getTechMstr(String glblCmpyCd, String techTocCd, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("techTocCd", techTocCd);
        ssmParam.put("maxDt", MAX_THRU_DT);
        ssmParam.put("slsDt", slsDt);
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        S21SsmEZDResult res = getSsmEZDClient().queryObject("getTechMstr", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return null;
        }
        return (String) res.getResultObject();
    }
    // END 2016/12/14 K.Kojima [QC#15653,MOD]

    /**
     * Count duplicate contract
     * @param cMsg NSAL0500CMsg
     * @return int
     */
    public int countDuplicateContr(NSAL0500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        return (Integer) getSsmEZDClient().queryObject("countDuplicateContr", ssmParam).getResultObject();
    }

    /**
     * Get DS_SUB_CONTRTMsg for update
     * @param cMsg NSAL0500CMsg
     * @return DS_SUB_CONTRTMsg
     */
    public DS_SUB_CONTRTMsg getDsSubContrForUpdate(NSAL0500CMsg cMsg) {
        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.dsSubContrPk, cMsg.dsSubContrPk);
        try {
            return (DS_SUB_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NZZM0003E);
            return null;
        }
    }

    /**
     * Get latest Svc Memo
     * @param cMsg NSAL0500CMsg
     * @return SVC_MEMOTMsg
     */
    public SVC_MEMOTMsg getLtstSvcMemo(NSAL0500CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("svcMemoCatgCd", SVC_MEMO_CATG.CONTRACT_MEMO);
        ssmParam.put("svcMemoTpList", getSvcMemoTpList());
        ssmParam.put("rowNum", 1);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getLtstSvcMemoPk", ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        BigDecimal svcMemoPk = (BigDecimal) ssmResult.getResultObject();
        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.svcMemoPk, svcMemoPk);

        return (SVC_MEMOTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    private List<String> getSvcMemoTpList() {

        List<String> svcMemoTpList = new ArrayList<String>();
        // mod start 2016/03/28 CSA Defect#4576
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_71);
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_72);
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_73);
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_74);
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_75);
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_76);
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_77);
//        svcMemoTpList.add(SVC_MEMO_TP_SEQ_78);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT1);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT2);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT3);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT4);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT5);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT6);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT7);
        svcMemoTpList.add(SVC_MEMO_TP.UPDATE_SUB_CONTRACT8);
        // mod end 2016/03/28 CSA Defect#4576
        return svcMemoTpList;
    }

    // mod start 2017/03/01 CSA QC#17575
    /**
     * getThirdPartyTech
     * @param glblCmpyCd String
     * @param vndCd String
     * @param techTocCd String
     * @param slsDt String
     * @return String Psn Code
     */
    public String getThirdPartyTech(String glblCmpyCd, String vndCd, String techTocCd, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndCd", vndCd);
        ssmParam.put("techTocCd", techTocCd);
        ssmParam.put("maxDt", MAX_THRU_DT);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("psnTpCd", PSN_TP.EMPLOYEE);
        S21SsmEZDResult res = getSsmEZDClient().queryObject("get3rdPartyTech", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return null;
        }
        return (String) res.getResultObject();
    }
    // mod end 2017/03/01 CSA QC#17575
    
    // Add Start 2018/12/10 K.Fujimoto QC#29079
    public S21SsmEZDResult getSubContrMtrInfo(NSAL0500CMsg cMsg, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("cancelledSts", DS_CONTR_CTRL_STS.CANCELLED);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryEZDMsgArray("getSubContrMtrInfo", ssmParam, cMsg.E);
    }
    

    public S21SsmEZDResult getDsSubContrMtrForDelete(NSAL0500CMsg cMsg, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("cancelledSts", DS_CONTR_CTRL_STS.CANCELLED);
        if (dsContrDtlPk != null) {
            ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        } else {
            ssmParam.put("dsContrDtlPk", cMsg.dsContrDtlPk.getValue());
        }
        return getSsmEZDClient().queryObjectList("getDsSubContrMtrForDelete", ssmParam);
    }

    /**
     * getLineDsContrDtlPk
     * @param cMsg NSAL0500CMsg
     * @param dsContrDtlTpCd String
     * @return BigDecimal Ds Contract Detail Primary key
     */
    public BigDecimal getLineDsContrDtlPk(NSAL0500CMsg cMsg, String dsContrDtlTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("dsContrDtlTpCd", dsContrDtlTpCd);
        S21SsmEZDResult res =  getSsmEZDClient().queryObject("getDsContrDtlLine", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return null;
        }
        return (BigDecimal) res.getResultObject();
    }
    

    /**
     * getContrDtl
     * @param cMsg NSAL0500CMsg
     * @return DS_CONTR_DTLTMsg Ds Contract Detail TMessage
     */
    public DS_CONTR_DTLTMsg getContrDtl(NSAL0500CMsg cMsg) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrDtlPk, cMsg.dsContrDtlPk.getValue());
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    
    // Add End   2018/12/10 K.Fujimoto QC#29079
}
