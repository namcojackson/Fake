/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB015001;

import static com.canon.cusa.s21.batch.NMA.NMAB015001.NMAB015001Constant.DEFAULT_COMMIT_UNIT;
import static com.canon.cusa.s21.batch.NMA.NMAB015001.NMAB015001Constant.MMAM0003E;
import static com.canon.cusa.s21.batch.NMA.NMAB015001.NMAB015001Constant.MMAM0004E;
import static com.canon.cusa.s21.batch.NMA.NMAB015001.NMAB015001Constant.MMAM0005E;
import static com.canon.cusa.s21.batch.NMA.NMAB015001.NMAB015001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB015001.NMAB015001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CMPSNTMsg;
import business.db.CMPSN_CHNG_REQTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * NMAB0150 Bill Of Material Effective Data Control Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Fujitsu         K.Minamide      Create          N/A
 *</pre>
 */
public class NMAB015001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** commitUnit */
    private int commitUnit = 0;

    /** process date time */
    private String procDt = null;

    /** process date time + 1 Day */
    private String procDtAddOne = null;

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
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        // value check(Global Company Code)
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }

        this.commitUnit = getCommitCount();

        if (this.commitUnit <= 0 || DEFAULT_COMMIT_UNIT < this.commitUnit) {
            this.commitUnit = DEFAULT_COMMIT_UNIT;
        }

        this.procDt = ZYPDateUtil.getBatProcDate();
        this.procDtAddOne = ZYPDateUtil.addDays(this.procDt, 1);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
    	
    	List<String> processedList = new ArrayList<String>();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("glblCmpyCd", this.glblCmpyCd);
        inputMap.put("procDt", this.procDtAddOne);
        List<Map<String, Object>> targetRequestList = this.ssmBatchClient.queryObjectList("getTargetRequestList", inputMap);
        int targetRequestCount = 0;
        for (Map<String, Object> targetRequestMap : targetRequestList) {
        	this.totalCount++;
        	//Insert New Revision CMPSN
            this.insertRecord(targetRequestMap);
            //Delete CMPSN_CHNG_REQ
            this.deleteCmpsnChngReq((BigDecimal) targetRequestMap.get("CMPSN_CHNG_REQ_PK"), 
            		(String) targetRequestMap.get("PRNT_MDSE_CD"), (BigDecimal) targetRequestMap.get("CMPSN_PK"));
            //update Effective Thru Date
            String sPreviousRevnNum = "";
            BigDecimal cmpsnRevnNum = (BigDecimal) targetRequestMap.get("CMPSN_REVN_NUM");
            if (cmpsnRevnNum != null) {
            	BigDecimal bPreviousRevnNum = ((BigDecimal) targetRequestMap.get("CMPSN_REVN_NUM")).subtract(BigDecimal.ONE);
            	sPreviousRevnNum = String.valueOf(bPreviousRevnNum);
            }
            //Not first Revision and Only one time per Parent Mdse and Previous Rivision
            if (!"0".equals(sPreviousRevnNum) 
            		&& !processedList.contains((String) targetRequestMap.get("PRNT_MDSE_CD") + "-" + sPreviousRevnNum)) {
	            //get previous revision
                Map<String, String> preRevMap = new HashMap<String, String>();
                preRevMap.put("glblCmpyCd", this.glblCmpyCd);
                preRevMap.put("prntMdseCd", (String) targetRequestMap.get("PRNT_MDSE_CD"));
                preRevMap.put("cmpsnRevnNum", sPreviousRevnNum);
                List<Map<String, Object>> previousRevisionList = this.ssmBatchClient.queryObjectList("getPreviousRevisionList", preRevMap);
                for (Map<String, Object> previousRevisionMap : previousRevisionList) {
                	//Update Previous Revision CMPSN and DS_CMPSN_INFO
                    this.updateRecord(previousRevisionMap, targetRequestMap);
                }
                processedList.add((String) targetRequestMap.get("PRNT_MDSE_CD") + "-" + sPreviousRevnNum);
            }
            targetRequestCount++;
            if (targetRequestCount == this.commitUnit) {
                super.commit();
                targetRequestCount = 0;
            }
            this.totalNmlCount++;
        }
        // not reach the max commit count in the repeat
        if (targetRequestCount > 0) {
            super.commit();
            targetRequestCount = 0;
        }
    }

    /**
     * delete CMPSN_CHNG_REQ
     * @param cmpsnChngReqPk
     * @param prntMdseCd
     * @param cmpsnPk
     */
    private void deleteCmpsnChngReq(BigDecimal cmpsnChngReqPk, String prntMdseCd, BigDecimal cmpsnPk) {
        CMPSN_CHNG_REQTMsg cmpsnChngReqtmsg = new CMPSN_CHNG_REQTMsg();
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqtmsg.cmpsnChngReqPk, cmpsnChngReqPk);
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqtmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqtmsg.prntMdseCd, prntMdseCd);
        ZYPEZDItemValueSetter.setValue(cmpsnChngReqtmsg.cmpsnPk, cmpsnPk);

        cmpsnChngReqtmsg = (CMPSN_CHNG_REQTMsg) EZDTBLAccessor.findByKeyForUpdate(cmpsnChngReqtmsg);
        if (cmpsnChngReqtmsg != null) {
            int rogicalDelCmpsnChngReqCount = S21FastTBLAccessor.removeLogical(new CMPSN_CHNG_REQTMsg[] {cmpsnChngReqtmsg });
            if (rogicalDelCmpsnChngReqCount != 1) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(MMAM0005E, new String[] {"CMPSN_CHNG_REQ" });
            }
        }
    }

    /**
     * insert CMPSN and DS_CMPSN_INFO
     * @param requestMap
     */
    private void insertRecord(Map<String, Object> targetRequestMap) {
        CMPSNTMsg insertCmpsnMsg = new CMPSNTMsg();
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.prntMdseCd, (String) targetRequestMap.get("PRNT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.cmpsnPk, (BigDecimal) targetRequestMap.get("CMPSN_PK"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.mdseCmpsnTpCd, (String) targetRequestMap.get("MDSE_CMPSN_TP_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.childMdseCd, (String) targetRequestMap.get("CHILD_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.childOrdTakeMdseCd, (String) targetRequestMap.get("CHILD_ORD_TAKE_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.childKitMatCd, (String) targetRequestMap.get("CHILD_KIT_MAT_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.childMdseQty, (BigDecimal) targetRequestMap.get("CHILD_MDSE_QTY"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.effFromDt, (String) targetRequestMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.effThruDt, (String) targetRequestMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.allocRatio, (BigDecimal) targetRequestMap.get("ALLOC_RATIO"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.childBomCmptCd, (String) targetRequestMap.get("CHILD_BOM_CMPT_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.childBomCmptNm, (String) targetRequestMap.get("CHILD_BOM_CMPT_NM"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.childBomPrcAmt, (BigDecimal) targetRequestMap.get("CHILD_BOM_PRC_AMT"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.mainCmpsnFlg, (String) targetRequestMap.get("MAIN_CMPSN_FLG"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.cusaMdseCd, (String) targetRequestMap.get("CUSA_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.baseCmptFlg, (String) targetRequestMap.get("BASE_CMPT_FLG"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.mndCmptFlg, (String) targetRequestMap.get("MND_CMPT_FLG"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.pkgUomCd, (String) targetRequestMap.get("PKG_UOM_CD"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.cmpsnRevnNum, (BigDecimal) targetRequestMap.get("CMPSN_REVN_NUM"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.actvFlg, (String) targetRequestMap.get("ACTV_FLG"));
        ZYPEZDItemValueSetter.setValue(insertCmpsnMsg.cmpsnRevnCmntTxt, (String) targetRequestMap.get("CMPSN_REVN_CMNT_TXT"));
        S21FastTBLAccessor.insert(insertCmpsnMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertCmpsnMsg.getReturnCode())) {
            this.totalErrCount++;
            super.rollback();
            throw new S21AbendException(MMAM0003E, new String[] {"CMPSN" });
        }
    }

    /**
     * update CMPSN and DS_CMPSN_INFO
     * @param requestMap
     */
    private void updateRecord(Map<String, Object> previousRevisionMap, Map<String, Object> targetRequestMap) {
        CMPSNTMsg cmpsnTmsg = new CMPSNTMsg();
        ZYPEZDItemValueSetter.setValue(cmpsnTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmpsnTmsg.prntMdseCd, (String) previousRevisionMap.get("PRNT_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(cmpsnTmsg.cmpsnPk, (BigDecimal) previousRevisionMap.get("CMPSN_PK"));
        cmpsnTmsg = (CMPSNTMsg) EZDTBLAccessor.findByKeyForUpdate(cmpsnTmsg);
        if (cmpsnTmsg != null) {
        	//New Revision Effective From Date and Effective From Date - 1 Day
            String newFromDt = (String) targetRequestMap.get("EFF_FROM_DT");
            String endDt = ZYPDateUtil.addDays(newFromDt, -1);
        	//Previous Revision Effective From Date and Thru Date
            String effFromDt = (String) previousRevisionMap.get("EFF_FROM_DT");
            String effThruDt = (String) previousRevisionMap.get("EFF_THRU_DT");
            if (!ZYPCommonFunc.hasValue(effThruDt) || (0 <= ZYPDateUtil.compare(newFromDt, effFromDt) && 0 < ZYPDateUtil.compare(effThruDt, endDt))) {
                effThruDt = endDt;
            }
            if (0 > ZYPDateUtil.compare(effThruDt, effFromDt)) {
                effThruDt = effFromDt;
            }
        	
            ZYPEZDItemValueSetter.setValue(cmpsnTmsg.effThruDt, effThruDt);
            ZYPEZDItemValueSetter.setValue(cmpsnTmsg.actvFlg, ZYPConstant.FLG_OFF_N);
            S21FastTBLAccessor.update(cmpsnTmsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cmpsnTmsg.getReturnCode())) {
                this.totalErrCount++;
                super.rollback();
                throw new S21AbendException(MMAM0004E, new String[] { "CMPSN" });
            }
        }
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
        new NMAB015001().executeBatch(NMAB015001.class.getSimpleName());
    }
}
