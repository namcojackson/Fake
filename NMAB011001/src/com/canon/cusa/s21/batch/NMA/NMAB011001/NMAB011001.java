/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NMA.NMAB011001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_HIST_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 *  scheduled batch
 *  NMAB0110 Cost Shift Batch Process

 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/06/17   SRA             Nishiyama       Create          N/A
 * 2013/10/10   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2015/10/27   SRA             K.Aratani       Update          For CSA
 * 2016/12/15   Fujitsu         N.Sugiura       Update          QC#16026
 * 2017/08/21   CITS            R.Shimamoto     Update          QC#19940
 * 2018/01/19   Hitachi         E.Kameishi      Update          QC#19940-1
 * 2019/02/08   Hitachi         E.Kameishi      Update          QC#30158
 *</pre>
 */

public class NMAB011001 extends S21BatchMain {

    /** Data Updated */
    private static final String DATA_UPDATED = "update";
    
    /** DB Table : MDSE */
    private static final String MDSE = "MDSE";
    
    /** DB Table : MDSE_CST_UPD_HIST_HDR */
    private static final String MDSE_CST_UPD_HIST_HDR = "MDSE_CST_UPD_HIST_HDR";
    
    /** DB Table : MDSE_CST_UPD_HIST_DTL */
    private static final String MDSE_CST_UPD_HIST_DTL = "MDSE_CST_UPD_HIST_DTL";
    
    /** error message DB access error <data to update by Pkey not found>  */
    private static final String DB_UPDATE_ERR = "NMAM8000E";

    /** termineted code */
    private TERM_CD termCd;

    /** update count */
    private int updateCount = 0;
    
    /** error count */
    private int errorCount = 0;
    
    /** **/
    private S21SsmBatchClient ssmBatchClient = null;

    /** QC#19940 Add. Process Date */
    private static String procDt = "";

    /**
     * Main method  
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NMAB011001().executeBatch(NMAB011001.class.getSimpleName());

    }

    /**
     * Initialize process
     */
    protected void initRoutine() {

        // Initialize variable
        this.updateCount = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // 2017/08/21 QC#19940 Add.
        procDt = ZYPDateUtil.getBatProcDate();

    }

    /**
     * Main process
     */
    protected void mainRoutine() {

        // Execute application process
        doProcess();
        
    }

    /**
     * Application process
     */
    @SuppressWarnings("unchecked")
    private void doProcess() {

        EZDDebugOutput.println(1,"glblCmpyCd01=" + this.getGlobalCompanyCode(), this);
        
        String salesDate = ZYPDateUtil.getBatProcDate();
        String nextSalesDate = ZYPDateUtil.addDays(salesDate, 1);
        
        try {
            
            //For CSA
            //Standard Cost
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.getGlobalCompanyCode());
            param.put("mdseCstUpdStsCd", MDSE_CST_UPD_STS.APPROVED);  //Approve
            param.put("nextSalesDate", nextSalesDate);  //Next Date
            param.put("stdCostRelnFlg", "Y"); //Standard Cost
            param.put("rgtnStsCdP10", RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);  //P10
            param.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);  //P20
            List<Map<String, Object>> costShiftTargetSCMdseList = (List<Map<String, Object>>)ssmBatchClient.queryObjectList("getCostShiftTargetSCMdse", param);
            if (!costShiftTargetSCMdseList.isEmpty()) {
                BigDecimal nextMthTotStdCostAmt = null;
                // 12/15/2016 QC#16026 Add Start 
                String prevMdseCd = "";
                String curMdseCd = "";
                // 12/15/2016 QC#16026 Add End 
                for (Map<String, Object> map : costShiftTargetSCMdseList) {
                    //Update MDSE
                    curMdseCd = (String) map.get("MDSE_CD");
                    if (!curMdseCd.equals(prevMdseCd)) { // 12/15/2016 QC#16026 Add 
                        MDSETMsg getRec = new MDSETMsg();
                        ZYPEZDItemValueSetter.setValue(getRec.glblCmpyCd, this.getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(getRec.mdseCd, curMdseCd);
                        getRec = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdateWait(getRec);
                        if (getRec != null) {
                            ZYPEZDItemValueSetter.setValue(getRec.lastMthTotStdCostAmt, getRec.thisMthTotStdCostAmt);
                            ZYPEZDItemValueSetter.setValue(getRec.thisMthTotStdCostAmt, getRec.nextMthTotStdCostAmt);
                            ZYPEZDItemValueSetter.setValue(getRec.thisMthFobAmt,        getRec.nextMthFobAmt);
                            ZYPEZDItemValueSetter.setValue(getRec.thisMthInlndFrtAmt,   getRec.nextMthInlndFrtAmt);
                            ZYPEZDItemValueSetter.setValue(getRec.thisMthIntlFrtAmt,    getRec.nextMthIntlFrtAmt);
                            ZYPEZDItemValueSetter.setValue(getRec.thisMthImptDtyAmt,    getRec.nextMthImptDtyAmt);
                            ZYPEZDItemValueSetter.setValue(getRec.thisMthInsAmt,        getRec.nextMthInsAmt);
                            nextMthTotStdCostAmt = getRec.nextMthTotStdCostAmt.getValue();
                            ZYPEZDItemValueSetter.setValue(getRec.nextMthTotStdCostAmt, BigDecimal.ZERO);
                            ZYPEZDItemValueSetter.setValue(getRec.nextMthFobAmt, BigDecimal.ZERO);
                            ZYPEZDItemValueSetter.setValue(getRec.nextMthInlndFrtAmt, BigDecimal.ZERO);
                            ZYPEZDItemValueSetter.setValue(getRec.nextMthIntlFrtAmt, BigDecimal.ZERO);
                            ZYPEZDItemValueSetter.setValue(getRec.nextMthImptDtyAmt, BigDecimal.ZERO);
                            ZYPEZDItemValueSetter.setValue(getRec.nextMthInsAmt, BigDecimal.ZERO);
                            // START 2019/02/08 E.Kameishi [QC#30158,MOD]
                            //ZYPEZDItemValueSetter.setValue(getRec.mdseCstUpdDt, getRec.mdseCstUpdEffFromDt);
                            ZYPEZDItemValueSetter.setValue(getRec.mdseCstUpdDt, nextSalesDate);
                            // END 2019/02/08 E.Kameishi [QC#30158,MOD]
                            getRec.origFobAmt.setValue(nextMthTotStdCostAmt);
                            ZYPEZDItemValueSetter.setValue(getRec.origFobAmtUpdDt, getRec.mdseCstUpdEffFromDt);
                            getRec.mdseCstUpdEffFromDt.clear();

                            S21FastTBLAccessor.update(getRec);
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(getRec.getReturnCode())) {
                                throw new S21AbendException(DB_UPDATE_ERR, new String[] {MDSE, DATA_UPDATED});
                            }
                        }

                        //Update MDSE_CST_UPD_HIST_DTL
                        //Active --> Archive
                        param = new HashMap<String, Object>();
                        param.put("glblCmpyCd", this.getGlobalCompanyCode());
                        param.put("mdseCstUpdStsCd", MDSE_CST_UPD_STS.ACTIVE);
                        param.put("mdseCstUpdTpCd", MDSE_CST_UPD_TP.STANDARD_COST);
                        param.put("mdseCd", (String) curMdseCd);
                        param.put("nextSalesDate", nextSalesDate);
                        updateFromActiveToArchive(param, (String) curMdseCd);

                        //Update MDSE_CST_UPD_HIST_DTL, MDSE_CST_UPD_HIST_HDR
                        //Approve --> Active
                        updateFromApproveToActive((BigDecimal) map.get("MDSE_CST_UPD_HIST_HDR_PK"), (String) curMdseCd, MDSE_CST_UPD_TP.STANDARD_COST, MDSE_CST_UPD_STS.ACTIVE);

                    } else {
                        //Update MDSE_CST_UPD_HIST_DTL, MDSE_CST_UPD_HIST_HDR
                        //Approve --> Archive
                        updateFromApproveToActive((BigDecimal) map.get("MDSE_CST_UPD_HIST_HDR_PK"), (String) curMdseCd, MDSE_CST_UPD_TP.STANDARD_COST, MDSE_CST_UPD_STS.ARCHIVED);

                    }

                    this.updateCount++;
                    prevMdseCd = curMdseCd;
                }
            }
            
            //Average Cost
            param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.getGlobalCompanyCode());
            param.put("mdseCstUpdStsCd", MDSE_CST_UPD_STS.APPROVED);
            param.put("nextSalesDate", nextSalesDate);
            param.put("stdCostRelnFlg", "N"); //Not Standard Cost
            param.put("rgtnStsCdP10", RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
            param.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
            List<Map<String, Object>> costShiftTargetARMdseList = (List<Map<String, Object>>)ssmBatchClient.queryObjectList("getCostShiftTargetARMdse", param);
            if (!costShiftTargetARMdseList.isEmpty()) {
                for (Map<String, Object> map : costShiftTargetARMdseList) {
                    //Update DS_MDSE_INFO
                    MDSETMsg dsMdseInfoTMsg = new MDSETMsg();
                    ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.glblCmpyCd, this.getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.mdseCd, (String) map.get("MDSE_CD"));
                    dsMdseInfoTMsg = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdateWait(dsMdseInfoTMsg);
                    if (dsMdseInfoTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.prevAssetRecovCostAmt, dsMdseInfoTMsg.assetRecovCostAmt);
                        ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.assetRecovCostAmt, dsMdseInfoTMsg.nextAssetRecovCostAmt);
                        dsMdseInfoTMsg.nextAssetRecovCostAmt.clear();
                           ZYPEZDItemValueSetter.setValue(dsMdseInfoTMsg.assetRecovCostAmtUpdDt, dsMdseInfoTMsg.assetRecovCstEffFromDt);
                        dsMdseInfoTMsg.assetRecovCstEffFromDt.clear();
                        S21FastTBLAccessor.update(dsMdseInfoTMsg);
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsMdseInfoTMsg.getReturnCode())) {
                            throw new S21AbendException(DB_UPDATE_ERR, new String[] {MDSE, DATA_UPDATED});
                        }
                    }

                    //Update MDSE_CST_UPD_HIST_DTL
                    //Active --> Archive
                    param = new HashMap<String, Object>();
                    param.put("glblCmpyCd", this.getGlobalCompanyCode());
                    param.put("mdseCstUpdStsCd", MDSE_CST_UPD_STS.ACTIVE);
                    param.put("mdseCstUpdTpCd", MDSE_CST_UPD_TP.ARV_COST);
                    param.put("mdseCd", (String) map.get("MDSE_CD"));
                    param.put("nextSalesDate", nextSalesDate);
                    updateFromActiveToArchive(param, (String) map.get("MDSE_CD"));

                    //Update MDSE_CST_UPD_HIST_DTL, MDSE_CST_UPD_HIST_HDR
                    //Approve --> Active
                    updateFromApproveToActive((BigDecimal) map.get("MDSE_CST_UPD_HIST_HDR_PK"), (String) map.get("MDSE_CD"), MDSE_CST_UPD_TP.ARV_COST, MDSE_CST_UPD_STS.ACTIVE);
                    this.updateCount++;
                }
            }

            
            commit();

        } finally {
            

        }
    }

    //Update status from Active to Archive
    @SuppressWarnings("unchecked")
    private boolean updateFromActiveToArchive(Map<String, Object> param, String mdseCd) {
    	boolean toAchiveFlg = false;
        List<Map<String, Object>> costUpdateHistDataList = (List<Map<String, Object>>)ssmBatchClient.queryObjectList("getCostUpdateHistData", param);
        if (!costUpdateHistDataList.isEmpty()) {
            for (Map<String, Object> mapCostUpdateHistData : costUpdateHistDataList) {
                MDSE_CST_UPD_HIST_DTLTMsg mdseCstUpdHistDtlTMsg = new MDSE_CST_UPD_HIST_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.glblCmpyCd, this.getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdHistHdrPk, (BigDecimal) mapCostUpdateHistData.get("MDSE_CST_UPD_HIST_HDR_PK"));
                ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCd, mdseCd);
                mdseCstUpdHistDtlTMsg = (MDSE_CST_UPD_HIST_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(mdseCstUpdHistDtlTMsg);
                if (mdseCstUpdHistDtlTMsg != null) {
                       ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdStsCd, MDSE_CST_UPD_STS.ARCHIVED);  //to Archive
                    S21FastTBLAccessor.update(mdseCstUpdHistDtlTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(mdseCstUpdHistDtlTMsg.getReturnCode())) {
                        throw new S21AbendException(DB_UPDATE_ERR, new String[] {MDSE_CST_UPD_HIST_DTL, DATA_UPDATED});
                    }
                    toAchiveFlg = true;
                }
            }
        }
    	return toAchiveFlg;
    }
    
    //Update status from Approve to Active
    private void updateFromApproveToActive(BigDecimal mdseCstUpdHistHdrPk, String mdseCd, String mdseCstUpdTp, String mdseCstUpdSts) {
        MDSE_CST_UPD_HIST_DTLTMsg mdseCstUpdHistDtlTMsg = new MDSE_CST_UPD_HIST_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.glblCmpyCd, this.getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCd, mdseCd);
        mdseCstUpdHistDtlTMsg = (MDSE_CST_UPD_HIST_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(mdseCstUpdHistDtlTMsg);
        if (mdseCstUpdHistDtlTMsg != null) {
               ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdStsCd, mdseCstUpdSts);  //to Active or Archive
               // START 2017/08/21 QC#19940 Add.
               // START 2018/01/19 E.Kameishi [QC#19940-1,MOD]
               //if (MDSE_CST_UPD_STS.ACTIVE.equals(mdseCstUpdSts) && procDt.compareTo(mdseCstUpdHistDtlTMsg.mdseCstUpdEffFromDt.getValue()) >= 1) {
               if (MDSE_CST_UPD_STS.ACTIVE.equals(mdseCstUpdSts) && procDt.compareTo(mdseCstUpdHistDtlTMsg.mdseCstUpdEffFromDt.getValue()) >= 0) {
               // END 2018/01/19 E.Kameishi [QC#19940-1,MOD]
            	   ZYPEZDItemValueSetter.setValue(mdseCstUpdHistDtlTMsg.mdseCstUpdEffFromDt, ZYPDateUtil.addDays(this.procDt, 1));
               }
               // END 2017/08/21 QC#19940 Add.

            S21FastTBLAccessor.update(mdseCstUpdHistDtlTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(mdseCstUpdHistDtlTMsg.getReturnCode())) {
                throw new S21AbendException(DB_UPDATE_ERR, new String[] {MDSE_CST_UPD_HIST_DTL, DATA_UPDATED});
            }
        }
        MDSE_CST_UPD_HIST_HDRTMsg mdseCstUpdHistHdrTMsg = new MDSE_CST_UPD_HIST_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.glblCmpyCd, this.getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
        mdseCstUpdHistHdrTMsg = (MDSE_CST_UPD_HIST_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(mdseCstUpdHistHdrTMsg);
        if (mdseCstUpdHistHdrTMsg != null) {
               ZYPEZDItemValueSetter.setValue(mdseCstUpdHistHdrTMsg.mdseCstUpdTpCd, mdseCstUpdTp);  //to Standard Cost or to AVG Cost
            S21FastTBLAccessor.update(mdseCstUpdHistHdrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(mdseCstUpdHistHdrTMsg.getReturnCode())) {
                throw new S21AbendException(DB_UPDATE_ERR, new String[] {MDSE_CST_UPD_HIST_HDR, DATA_UPDATED});
            }
        }

    }

    /**
     * Terminate process
     */
    protected void termRoutine() {

        // termineted code set
        setTermState(this.termCd, this.updateCount, this.errorCount, this.updateCount + this.errorCount);

    }

}
