package com.canon.cusa.s21.batch.NWA.NWAB251001;

import com.canon.cusa.s21.batch.NWA.NWAB251001.NWAB251001Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import parts.dbcommon.EZDTBLAccessor;

import business.db.GLBL_CMPYTMsg;
import business.db.PROC_LAST_EXECTMsg;
import business.db.UPD_ORD_RPSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

/**
 * <pre>
 * NMAB2510 Order Tracking Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   CSA             K.Aratani       Create          N/A
 *</pre>
 */
public class NWAB251001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

    /** process date time */
    private String procDt = null;

    /** delete date time */
    private String deleteDt = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
        	//[@] is mandatory.
            throw new S21AbendException("ZZZM9025E", new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg == null) {
        	//[@] is invalid value
            throw new S21AbendException("ZZZM9026E", new String[] {"Global Company Code" });
        }

        this.commitUnit = getCommitCount();
        if (this.commitUnit <= 0 || NWAB251001Constant.DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = NWAB251001Constant.DEFAULT_COMMIT_UNIT;
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        int iDelAot = 1;
        String strDelAot = ZYPCodeDataUtil.getVarCharConstValue("NWAB2510_DELETE_DATA_AOT", this.glblCmpyCd);
    	if (ZYPCommonFunc.hasValue(strDelAot)) {
    		iDelAot = Integer.valueOf(strDelAot);
    	}
        this.deleteDt = ZYPDateUtil.addDays(this.procDt, iDelAot);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @SuppressWarnings("unchecked")
    protected void mainRoutine() {
    	
    	//Delete Process
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("glblCmpyCd", this.glblCmpyCd);
        inputMap.put("baseDt", deleteDt);
        inputMap.put("procFlg_Y", "Y");
        List<Map<String, Object>> deleteList = this.ssmBatchClient.queryObjectList("getDeleteList", inputMap);
        for (Map<String, Object> deleteMap : deleteList) {
        	this.totalCount++;
        	BigDecimal updOrdRpstPk = (BigDecimal) deleteMap.get("UPD_ORD_RPST_PK");
        	UPD_ORD_RPSTTMsg updOrdRpstTMsg = new UPD_ORD_RPSTTMsg();
            ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.updOrdRpstPk, updOrdRpstPk);
            EZDTBLAccessor.remove(updOrdRpstTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updOrdRpstTMsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                //MMAM0005E=0,Data delete failure. (table name is [@])
                throw new S21AbendException("MMAM0005E", new String[] {"UPD_ORD_RPST" });
            }
        }
    	
    	//Insert Process
        inputMap = new HashMap<String, String>();
        inputMap.put("glblCmpyCd", this.glblCmpyCd);
        inputMap.put("procPgmId", NWAB251001Constant.BUSINESS_ID);
        List<Map<String, Object>> targetRequestList = this.ssmBatchClient.queryObjectList("getTargetOrderList", inputMap);
        int targetRequestCount = 0;
        for (Map<String, Object> targetRequestMap : targetRequestList) {
        	this.totalCount++;
        	Map<String, Object> map = chkNoExistCopOrdNum((String) targetRequestMap.get("CPO_ORD_NUM"));
            if (map != null && map.get("UPD_ORD_RPST_PK") != null) {
            	UPD_ORD_RPSTTMsg updOrdRpstTMsg = new UPD_ORD_RPSTTMsg();
                ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.updOrdRpstPk, (BigDecimal) map.get("UPD_ORD_RPST_PK"));
                updOrdRpstTMsg = (UPD_ORD_RPSTTMsg) S21CacheTBLAccessor.findByKey(updOrdRpstTMsg);
                if (updOrdRpstTMsg == null) {
    	            this.insertRecord(targetRequestMap);
                } else {
    	            this.updateRecord(updOrdRpstTMsg);
                }
            } else {
	            this.insertRecord(targetRequestMap);
        	}
            targetRequestCount++;
            if (targetRequestCount == this.commitUnit) {
                super.commit();
                targetRequestCount = 0;
            }
            this.totalNmlCount++;
        }
        PROC_LAST_EXECTMsg procLastExecTMsg = new PROC_LAST_EXECTMsg();
        procLastExecTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        procLastExecTMsg.procPgmId.setValue(NWAB251001Constant.BUSINESS_ID);
        procLastExecTMsg = (PROC_LAST_EXECTMsg) S21CacheTBLAccessor.findByKey(procLastExecTMsg);
        if (procLastExecTMsg != null) {
        	procLastExecTMsg.lastExecTs.setValue(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            S21FastTBLAccessor.update(procLastExecTMsg);
            if (!NWAB251001Constant.RETURN_CD_NORMAL.equals(procLastExecTMsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
            	//NMAM8175E=0,This data has been updated by another user. [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException("NMAM8175E", new String[] {"PROC_LAST_EXEC", "PROC_PGM_ID", NWAB251001Constant.BUSINESS_ID });
            }
            targetRequestCount++;
        } else {
        	procLastExecTMsg = new PROC_LAST_EXECTMsg();
            procLastExecTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            procLastExecTMsg.procPgmId.setValue(NWAB251001Constant.BUSINESS_ID);
        	procLastExecTMsg.lastExecTs.setValue(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            EZDTBLAccessor.create(procLastExecTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(procLastExecTMsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
            	// ZZMM0001E=0,Data insert failure.  [ TableName = @ , key = @, value = @ ]
                throw new S21AbendException("ZZMM0001E", new String[] {"PROC_LAST_EXEC", "PROC_PGM_ID", NWAB251001Constant.BUSINESS_ID });
            }
            targetRequestCount++;
        }
        
        if (targetRequestCount > 0) {
            super.commit();
            targetRequestCount = 0;
        }
    }

    /**
     * insert UPD_ORD_RPST
     * @param requestMap
     */
    private void insertRecord(Map<String, Object> targetRequestMap) {
    	UPD_ORD_RPSTTMsg updOrdRpstTMsg = new UPD_ORD_RPSTTMsg();
        ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.updOrdRpstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.UPD_ORD_RPST_SQ));
        ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.cpoOrdNum, (String) targetRequestMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.updOrdRpstCratTs, this.procDt);
        ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.procFlg, FLG_OFF_N);
        S21FastTBLAccessor.insert(updOrdRpstTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updOrdRpstTMsg.getReturnCode())) {
            this.totalErrCount++;
            super.rollback();
            //Data insert failure. (table name is [@])
            throw new S21AbendException("MMAM0003E", new String[] {"UPD_ORD_RPST" });
        }

    }
    
    /**
     * update UPD_ORD_RPST
     * @param requestMap
     */
    private void updateRecord(UPD_ORD_RPSTTMsg updOrdRpstTMsg) {
        ZYPEZDItemValueSetter.setValue(updOrdRpstTMsg.procFlg, FLG_OFF_N);
        S21FastTBLAccessor.update(updOrdRpstTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updOrdRpstTMsg.getReturnCode())) {
            this.totalErrCount++;
            super.rollback();
            //Data update failure. (table name is [@])
            throw new S21AbendException("MMAM0004E", new String[] {"UPD_ORD_RPST" });
        }

    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> chkNoExistCopOrdNum(String cpoOrdNum) {
    	
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("glblCmpyCd", this.glblCmpyCd);
        inputMap.put("cpoOrdNum", cpoOrdNum);
        Map<String, Object> map = (Map<String, Object>) this.ssmBatchClient.queryObject("chkExistCopOrdNum", inputMap);
        return map;
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);
    }

    /**
     * <pre>
     * A main method for batch application start.
     * </pre>
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NWAB251001().executeBatch(NWAB251001.class.getSimpleName());
    }
}
