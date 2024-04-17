/**
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB082001;

import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.NSAM0004E;
import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.NSAM0177E;
import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.NSAM0178E;
import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.NSAM0469E;
import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.NSAM0470E;
import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.SPACE;
import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.TERM_COND_UPD_STS_DELETED;
import static com.canon.cusa.s21.batch.NSA.NSAB082001.constant.NSAB082001Constant.TERM_COND_UPD_STS_UPDATED;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_TERM_CONDTMsg;

import com.canon.cusa.s21.common.NSX.NSXC002001.DefSvcTermCondInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcTermCond;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * Reset SLA Time by Additional Charge Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/10   Hitachi         K.Kitachi       Create          QC#28646
 * 2019/01/16   Fujitsu         R.Nakamura      Update          QC#29814
 * 2019/07/01   Hitachi         K.Fujimoto      Update          QC#50967
 * </pre>
 */
public class NSAB082001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Division Number */
    private String divisionNum = null;

    /** Residue */
    private String residue = null;

    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0177E);
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(NSAM0178E);
        }

        // Get Division Number
        this.divisionNum = getUserVariable1();

        // Get Residue
        this.residue = getUserVariable2();

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB082001().executeBatch(NSAB082001.class.getSimpleName());
    }

    private void doProcess() {
        Map<String, Object> paramGetMainMachContrDtlPk = createParamGetMainMachContrDtlPk();
        List<BigDecimal> mainMachContrDtlPkList = getMainMachContrDtlPkList(paramGetMainMachContrDtlPk);
        for (BigDecimal mainMachContrDtlPk : mainMachContrDtlPkList) {
            if (!deleteProcess(mainMachContrDtlPk)) {
                rollback();
                this.errorCount++;
                continue;
            }
            if (!resetProcess(mainMachContrDtlPk)) {
                rollback();
                this.errorCount++;
                continue;
            }
            commit();
            this.normalCount++;
        }
        for (String errMsg : this.errMsgList) {
            S21InfoLogOutput.println(errMsg);
        }
    }

    private boolean deleteProcess(BigDecimal mainMachContrDtlPk) {
        Map<String, Object> paramGetAddlChrgContrDtlPk = createParamGetAddlChrgContrDtlPk(mainMachContrDtlPk);
        // Mod Start 2019/07/01 QC#50967
        //List<BigDecimal> addlChrgContrDtlPkList = getAddlChrgContrDtlPkList(paramGetAddlChrgContrDtlPk);
        List<Map<String, Object>> addlChrgContrDtlPkList = getAddlChrgContrDtlPkList(paramGetAddlChrgContrDtlPk);
        for (Map<String, Object> addlChrgContrDtlPkMap : addlChrgContrDtlPkList) {
        //for (BigDecimal addlChrgContrDtlPk : addlChrgContrDtlPkList) {
            BigDecimal addlChrgContrDtlPk = (BigDecimal) addlChrgContrDtlPkMap.get("DS_CONTR_DTL_PK");
            String trmdFlg = (String) addlChrgContrDtlPkMap.get("TRMD_FLG");
            Map<String, Object> paramGetAddlChrgTermCondList = createParamGetAddlChrgTermCond(addlChrgContrDtlPk);
            List<Map<String, Object>> addlChrgTermCondList = getAddlChrgTermCondList(paramGetAddlChrgTermCondList);
            if (!clearTermCondProcess(mainMachContrDtlPk, addlChrgTermCondList)) {
                return false;
            }
            if (trmdFlg.equals(ZYPConstant.FLG_ON_Y)) {
                if (!updateDsContrDtl(addlChrgContrDtlPk, TERM_COND_UPD_STS_DELETED)) {
                    return false;
                }
            } else {
                if (!updateDsContrDtl(addlChrgContrDtlPk, null)) {
                    return false;
                }
            }
        }
        // Mod Start 2019/07/01 QC#50967
        return true;
    }

    private boolean clearTermCondProcess(BigDecimal mainMachContrDtlPk, List<Map<String, Object>> termCondList) {
        for (Map<String, Object> termCond : termCondList) {
            BigDecimal svcTermCondPk = (BigDecimal) termCond.get("SVC_TERM_COND_PK");
            if (!hasValue(svcTermCondPk)) {
                continue;
            }
            Map<String, Object> paramCountOtherAddlChrgTermCond = createParamCountOtherAddlChrgTermCond(mainMachContrDtlPk, (BigDecimal) termCond.get("SVC_TERM_COND_ATTRB_PK"));
            BigDecimal countOtherAddlChrgTermCond = countOtherAddlChrgTermCond(paramCountOtherAddlChrgTermCond);
            if (countOtherAddlChrgTermCond.compareTo(BigDecimal.ZERO) > 0) {
                continue;
            }
            // Add Start 2019/01/16 QC#29814
            String svcTermAttrbMapValCd = null;
            SVC_TERM_CONDTMsg svcTermCondTMsg = getSvcTermCondForUpdate(svcTermCondPk);
            DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl(mainMachContrDtlPk);
            if (svcTermCondTMsg == null || dsContrDtlTMsg == null) {
                return false;
            }
            if (hasValue(dsContrDtlTMsg.svcPgmMdseCd)) {
                List<DefSvcTermCondInfoBean> defSvcTermCondList = NSXC002001SvcTermCond.getTermCondInfoForMachLvl(this.glblCmpyCd, this.salesDate, dsContrDtlTMsg.svcPgmMdseCd.getValue());
                for (DefSvcTermCondInfoBean defSvcTermCond : defSvcTermCondList) {
                    if (svcTermCondTMsg.svcTermCondAttrbPk.getValue().equals(defSvcTermCond.getSvcTermCondAttrbPk())) {
                        svcTermAttrbMapValCd = defSvcTermCond.getSvcTermAttrbMapValCd();
                        break;
                    }
                }
            }
            // Add End 2019/01/16 QC#29814
            // Mod Start 2019/01/16 QC#29814
//            if (!updateSvcTermCond(svcTermCondPk, null)) {
            if (!updateSvcTermCond(svcTermCondPk, svcTermAttrbMapValCd)) {
                return false;
            }
            // Mod End 2019/01/16 QC#29814
        }
        return true;
    }

    private boolean resetProcess(BigDecimal mainMachContrDtlPk) {
        List<BigDecimal> updateContrDtlPkList = new ArrayList<BigDecimal>();
        Map<String, Object> paramGetResetAddlChrgContrDtlPk = createParamGetResetAddlChrgContrDtlPk(mainMachContrDtlPk);
        List<BigDecimal> resetAddlChrgContrDtlPkList = getResetAddlChrgContrDtlPkList(paramGetResetAddlChrgContrDtlPk);
        if (resetAddlChrgContrDtlPkList.size() == 0) {
            return true;
        }
        Map<String, Object> paramGetResetMainMachTermCondList = createParamGetResetMainMachTermCond(resetAddlChrgContrDtlPkList);
        List<Map<String, Object>> resetMainMachTermCondList = getResetMainMachTermCondList(paramGetResetMainMachTermCondList);
        if (!resetTermCondProcess(resetMainMachTermCondList, updateContrDtlPkList)) {
            return false;
        }
        Map<String, Object> paramGetResetAddlChrgTermCondList = createParamGetResetAddlChrgTermCond(resetAddlChrgContrDtlPkList);
        List<Map<String, Object>> resetAddlChrgTermCondList = getResetAddlChrgTermCondList(paramGetResetAddlChrgTermCondList);
        if (!resetTermCondProcess(resetAddlChrgTermCondList, updateContrDtlPkList)) {
            return false;
        }
        for (BigDecimal updateContrDtlPk : updateContrDtlPkList) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl(updateContrDtlPk);
            if (dsContrDtlTMsg == null) {
                return false;
            }
            if (hasValue(dsContrDtlTMsg.termCondUpdStsCd)) {
                continue;
            }
            if (!updateDsContrDtl(updateContrDtlPk, TERM_COND_UPD_STS_UPDATED)) {
                return false;
            }
        }
        return true;
    }

    private boolean resetTermCondProcess(List<Map<String, Object>> termCondList, List<BigDecimal> updateContrDtlPkList) {
        for (Map<String, Object> termCond : termCondList) {
            BigDecimal svcTermCondPk = (BigDecimal) termCond.get("SVC_TERM_COND_PK");
            String termCondOptValTxt = (String) termCond.get("TERM_COND_OPT_VAL_TXT");
            if (hasValue(svcTermCondPk)) {
                if (!updateSvcTermCond(svcTermCondPk, termCondOptValTxt)) {
                    return false;
                }
            } else {
                if (!insertSvcTermCond(termCond)) {
                    return false;
                }
            }
            BigDecimal addlDsContrDtlPk = (BigDecimal) termCond.get("ADDL_DS_CONTR_DTL_PK");
            if (!updateContrDtlPkList.contains(addlDsContrDtlPk)) {
                updateContrDtlPkList.add(addlDsContrDtlPk);
            }
        }
        return true;
    }

    private Map<String, Object> createParamGetMainMachContrDtlPk() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("divisionNum", this.divisionNum);
        paramMap.put("residue", this.residue);
        paramMap.put("termCondUpdStsUpdated", TERM_COND_UPD_STS_UPDATED);
        return paramMap;
    }

    private Map<String, Object> createParamGetAddlChrgContrDtlPk(BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("termCondUpdStsUpdated", TERM_COND_UPD_STS_UPDATED);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        return paramMap;
    }

    private Map<String, Object> createParamGetAddlChrgTermCond(BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        return paramMap;
    }

    private Map<String, Object> createParamCountOtherAddlChrgTermCond(BigDecimal dsContrDtlPk, BigDecimal svcTermCondAttrbPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("termCondUpdStsUpdated", TERM_COND_UPD_STS_UPDATED);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("svcTermCondAttrbPk", svcTermCondAttrbPk);
        return paramMap;
    }

    private Map<String, Object> createParamGetResetAddlChrgContrDtlPk(BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("termCondUpdStsUpdated", TERM_COND_UPD_STS_UPDATED);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        return paramMap;
    }

    private Map<String, Object> createParamGetResetMainMachTermCond(List<BigDecimal> dsContrDtlPkList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("termCondUpdStsUpdated", TERM_COND_UPD_STS_UPDATED);
        paramMap.put("space", SPACE);
        paramMap.put("dsContrDtlPkList", dsContrDtlPkList);
        return paramMap;
    }

    private Map<String, Object> createParamGetResetAddlChrgTermCond(List<BigDecimal> dsContrDtlPkList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("termCondUpdStsUpdated", TERM_COND_UPD_STS_UPDATED);
        paramMap.put("space", SPACE);
        paramMap.put("dsContrDtlPkList", dsContrDtlPkList);
        return paramMap;
    }

    private List<BigDecimal> getMainMachContrDtlPkList(Map<String, Object> paramMap) {
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getMainMachContrDtlPkList", paramMap);
    }
    // Mod Start 2019/07/01 QC#50967
//    private List<BigDecimal> getAddlChrgContrDtlPkList(Map<String, Object> paramMap) {
//        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getAddlChrgContrDtlPkList", paramMap);
//    }
    private List<Map<String, Object>> getAddlChrgContrDtlPkList(Map<String, Object> paramMap) {
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAddlChrgContrDtlPkList", paramMap);
    }
    // Mod End   2019/07/01 QC#50967

    private List<Map<String, Object>> getAddlChrgTermCondList(Map<String, Object> paramMap) {
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getAddlChrgTermCondList", paramMap);
    }

    private BigDecimal countOtherAddlChrgTermCond(Map<String, Object> paramMap) {
        return (BigDecimal) this.ssmBatchClient.queryObject("countOtherAddlChrgTermCond", paramMap);
    }

    private List<BigDecimal> getResetAddlChrgContrDtlPkList(Map<String, Object> paramMap) {
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getResetAddlChrgContrDtlPkList", paramMap);
    }

    private List<Map<String, Object>> getResetMainMachTermCondList(Map<String, Object> paramMap) {
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getResetMainMachTermCondList", paramMap);
    }

    private List<Map<String, Object>> getResetAddlChrgTermCondList(Map<String, Object> paramMap) {
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getResetAddlChrgTermCondList", paramMap);
    }

    private DS_CONTR_DTLTMsg getDsContrDtl(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        DS_CONTR_DTLTMsg rtnTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
        if (rtnTMsg == null) {
            String key = S21StringUtil.concatStrings("DS_CONTR_DTL_PK=", dsContrDtlPk);
            addErrMsg(NSAM0004E, new String[] {prmTMsg.getTableName(), key });
        }
        return rtnTMsg;
    }

    private DS_CONTR_DTLTMsg getDsContrDtlForUpdate(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        DS_CONTR_DTLTMsg rtnTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
        if (rtnTMsg == null) {
            String key = S21StringUtil.concatStrings("DS_CONTR_DTL_PK=", dsContrDtlPk);
            addErrMsg(NSAM0004E, new String[] {prmTMsg.getTableName(), key });
        }
        return rtnTMsg;
    }

    private SVC_TERM_CONDTMsg getSvcTermCondForUpdate(BigDecimal svcTermCondPk) {
        SVC_TERM_CONDTMsg prmTMsg = new SVC_TERM_CONDTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.svcTermCondPk, svcTermCondPk);
        SVC_TERM_CONDTMsg rtnTMsg = (SVC_TERM_CONDTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
        if (rtnTMsg == null) {
            String key = S21StringUtil.concatStrings("SVC_TERM_COND_PK=", svcTermCondPk);
            addErrMsg(NSAM0004E, new String[] {prmTMsg.getTableName(), key });
        }
        return rtnTMsg;
    }

    private boolean updateDsContrDtl(BigDecimal dsContrDtlPk, String termCondUpdStsCd) {
        DS_CONTR_DTLTMsg updTMsg = getDsContrDtlForUpdate(dsContrDtlPk);
        if (updTMsg == null) {
            return false;
        }
        setValue(updTMsg.termCondUpdStsCd, termCondUpdStsCd);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            String key = S21StringUtil.concatStrings("DS_CONTR_DTL_PK=", dsContrDtlPk);
            addErrMsg(NSAM0470E, new String[] {updTMsg.getTableName(), key });
            return false;
        }
        return true;
    }

    private boolean updateSvcTermCond(BigDecimal svcTermCondPk, String svcTermAttrbMapValCd) {
        SVC_TERM_CONDTMsg updTMsg = getSvcTermCondForUpdate(svcTermCondPk);
        if (updTMsg == null) {
            return false;
        }
        updTMsg.svcTermAttrbMapValCd.clear();
        if (hasValue(svcTermAttrbMapValCd)) {
            setValue(updTMsg.svcTermAttrbMapValCd, svcTermAttrbMapValCd);
        }
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            String key = S21StringUtil.concatStrings("SVC_TERM_COND_PK=", svcTermCondPk);
            addErrMsg(NSAM0470E, new String[] {updTMsg.getTableName(), key });
            return false;
        }
        return true;
    }

    private boolean insertSvcTermCond(Map<String, Object> svcTermCondMap) {
        SVC_TERM_CONDTMsg insTMsg = new SVC_TERM_CONDTMsg();
        setValue(insTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(insTMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
        setValue(insTMsg.dsContrPk, (BigDecimal) svcTermCondMap.get("DS_CONTR_PK"));
        setValue(insTMsg.dsContrDtlPk, (BigDecimal) svcTermCondMap.get("DS_CONTR_DTL_PK"));
        setValue(insTMsg.svcTermCondAttrbPk, (BigDecimal) svcTermCondMap.get("SVC_TERM_COND_ATTRB_PK"));
        setValue(insTMsg.svcTermAttrbMapValCd, (String) svcTermCondMap.get("TERM_COND_OPT_VAL_TXT"));
        S21FastTBLAccessor.insert(insTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            String key = S21StringUtil.concatStrings("DS_CONTR_PK=", (BigDecimal) svcTermCondMap.get("DS_CONTR_PK"), " DS_CONTR_DTL_PK=", (BigDecimal) svcTermCondMap.get("DS_CONTR_DTL_PK"));
            addErrMsg(NSAM0469E, new String[] {insTMsg.getTableName(), key });
            return false;
        }
        return true;
    }

    private void addErrMsg(String msgId, String... param) {
        String errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        if (!this.errMsgList.contains(errMsg)) {
            this.errMsgList.add(errMsg);
        }
    }
}
