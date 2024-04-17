/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB201001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDAbendException;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.COA_ACCTTMsg;
import business.db.NMAI0020_01TMsg;
import business.db.COA_CMPYTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_CHTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_PROJTMsg;
import business.db.COA_EXTNTMsg;
import business.db.PRNT_COA_ACCTTMsg;
import business.db.PRNT_COA_AFFLTMsg;
import business.db.PRNT_COA_BRTMsg;
import business.db.PRNT_COA_CCTMsg;
import business.db.PRNT_COA_CHTMsg;
import business.db.PRNT_COA_CMPYTMsg;
import business.db.PRNT_COA_EXTNTMsg;
import business.db.PRNT_COA_PRODTMsg;
import business.db.PRNT_COA_PROJTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Actual Invoice Interface Import
 * Insert records from interface table(NFBI0110_01) into CM_IF_MSTR_INV table.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/11/2010   CUSA            N.Sasaki        Create          N/A
 * 03/29/2016   CSAI            K.Uramori       Update          CSA Modification. Not to delete, insert every time. Add COA_ACCT.
 * 06/10/2016   CSAI            K.Uramori       Update          QC#7153 add missing fields and handle summary values
 * </pre>
 */
public class NMAB201001 extends S21BatchMain implements NMAB201001Constant {

    /** Error Message (Unique Constraint Violation) */
    public static final String ZZBM0074E = "ZZBM0074E";

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Array of TMsg */
    private EZDTMsg[] tMsgCreate;

    /** Size of Array */
    private int tMsgCnt = 0;

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        new NMAB201001().executeBatch(NMAB201001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        S21TransactionTableAccessor tranAccessor = new S21TransactionTableAccessor();

        // Get the Oldest transaction ID.
        BigDecimal oldestTrx = tranAccessor.searchIntegrationRecordOldest(INTFC_ID);
        // Get the Latest transaction ID.
        BigDecimal latestTrx = tranAccessor.searchIntegrationRecordLatest(INTFC_ID);

        // Normal End if no integration data is found.
        if (oldestTrx == null || latestTrx == null) {
            EZDDebugOutput.println(1, "Not found integration data.", this);
            return;
        }

        while (oldestTrx != null) {

            // Get the record from latest transaction
            if (oldestTrx.equals(latestTrx)) {
                if (!procCOACmpy(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOACmpy(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                
                if (!procCOABr(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOABr(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                if (!procCOACc(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOACc(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAAcct(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAAcct(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAProd(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAProd(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                if (!procCOACh(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOACh(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAAffl(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAAffl(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAProj(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAProj(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAExtn(oldestTrx, ZYPConstant.FLG_OFF_N)) {
                    errorProc();
                    return;
                }
                
                if (!procCOAExtn(oldestTrx, ZYPConstant.FLG_ON_Y)) {
                    errorProc();
                    return;
                }
            }

            S21InfoLogOutput.printlnv(ZZIM0009I, INTFC_ID, oldestTrx);
            
            // Update the transaction ID to PROCESSED_FLAG = '1'
            tranAccessor.endIntegrationProcess(INTFC_ID, oldestTrx);

            // Get the Oldest transaction ID to PROCESSED_FLAG = '0'
            oldestTrx = tranAccessor.searchIntegrationRecordOldest(INTFC_ID);
        }

        // Clear the Interface Table if no error
        if (clearInferfaceTable()) {
            commit();
        } else {
            rollback();
        }
        S21InfoLogOutput.println("mainRoutine Method End");
    }

    private void errorProc() {
        
        commitCount = 0;
        rollback();
        
        throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        
        
    }

    /*
    private boolean updateCodeTables(String coaFlexValSetId, BigDecimal transactionId) {
        
        String defaultCode = getDefaultCode(coaFlexValSetId);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaFlexValSetId", coaFlexValSetId);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);

        return (Boolean) this.ssmBatchClient.queryObject("getRecordFromInterfaceTable", queryParam, new UpdateCodeTables(coaFlexValSetId));
    }
    */
    
    private boolean procCOACmpy (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_CMPYTMsg> listTmsg = getNewCoaCmpy(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_CMPYTMsg tmsg : listTmsg) {
                int c = tmsg.coaCmpyCd.getValue().charAt(0);                
                setValue(tmsg.coaCmpySortNum, new BigDecimal(c));
            }
            
            int rtn;
            
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
            
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_CMPYTMsg[listTmsg.size()]));
            
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_CMPY";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaCmpy(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_CMPYTMsg tmsg : listTmsg) {
                
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    
                    PRNT_COA_CMPYTMsg pTmsg = new PRNT_COA_CMPYTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
                
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaCmpy(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_CMPYTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_CMPY";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_CMPYTMsg> getNewCoaCmpy(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_CMPY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CMPY);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_CMPY_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_CMPY_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CMPYTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaCmpy", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_CMPYTMsg> getReactivateCoaCmpy(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_CMPY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CMPY);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_CMPY_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_CMPY_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CMPYTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaCmpy", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_CMPYTMsg> getDelCoaCmpy(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CMPY);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CMPYTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaCmpy", queryParam);
    }

    private boolean procCOABr (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_BRTMsg> listTmsg = getNewCoaBr(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_BRTMsg tmsg : listTmsg) {
                int c = tmsg.coaBrCd.getValue().charAt(0);                
                setValue(tmsg.coaBrSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_BRTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_BR";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaBr(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_BRTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    //summary
                    PRNT_COA_BRTMsg pTmsg = new PRNT_COA_BRTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                 // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
                
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaBr(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_BRTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_BR";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_BRTMsg> getNewCoaBr(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_BR);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_BR);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_BR_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_BR_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_BRTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaBr", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_BRTMsg> getReactivateCoaBr(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_BR);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_BR);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_BR_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_BR_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);
        
        return (List<COA_BRTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaBr", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_BRTMsg> getDelCoaBr(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_BR);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);
        
        return (List<COA_BRTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaBr", queryParam);
    }
    
    private boolean procCOACc (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_CCTMsg> listTmsg = getNewCoaCc(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_CCTMsg tmsg : listTmsg) {
                int c = tmsg.coaCcCd.getValue().charAt(0);                
                setValue(tmsg.coaCcSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_CCTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_CC";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaCc(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_CCTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    // summary
                    PRNT_COA_CCTMsg pTmsg = new PRNT_COA_CCTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
                
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaCc(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_CCTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_CC";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_CCTMsg> getNewCoaCc(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_CC);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CC);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_CC_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_CC_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CCTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaCc", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_CCTMsg> getReactivateCoaCc(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_CC);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CC);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_CC_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_CC_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CCTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaCc", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_CCTMsg> getDelCoaCc(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CC);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CCTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaCc", queryParam);
    }
    
    private boolean procCOAAcct (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_ACCTTMsg> listTmsg = getNewCoaAcct(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_ACCTTMsg tmsg : listTmsg) {
                int c = tmsg.coaAcctCd.getValue().charAt(0);                
                setValue(tmsg.coaAcctSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_ACCTTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_ACCT";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaAcct(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_ACCTTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    // summary
                    PRNT_COA_ACCTTMsg pTmsg = new PRNT_COA_ACCTTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
                
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaAcct(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_ACCTTMsg[listTmsg.size()]));
            } else {
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_ACCT";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        // get update list (Update TRIAL_BAL_TP_CD from COA_GL_CMBN)
        listTmsg = getUpdCoaAcct(transactionId);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            COA_ACCTTMsg[] tmsgs = new COA_ACCTTMsg[listTmsg.size()];
            
            int i = 0;
            for (COA_ACCTTMsg tmsg : listTmsg) {
                
                String trialBalCd = tmsg.trialBalTpCd.getValue();
                
                COA_ACCTTMsg tmsgUpd = (COA_ACCTTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);
                
                if (tmsgUpd == null) {
                    errMsg = "Unable to retrieve COA_ACCT for update.";
                    return false;
                }
                
                setValue(tmsgUpd.trialBalTpCd, trialBalCd);
                
                tmsgs[i] = tmsgUpd;
                
                i++;
            }
            
            int rtn = S21FastTBLAccessor.update(tmsgs);
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to update COA_ACCT";
                return false;
            }
            
            commitCount = commitCount + listTmsg.size();
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_ACCTTMsg> getNewCoaAcct(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_ACCT);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_ACCT);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_ACCT_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_ACCT_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_ACCTTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaAcct", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_ACCTTMsg> getReactivateCoaAcct(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_ACCT);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_ACCT);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_ACCT_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_ACCT_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_ACCTTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaAcct", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_ACCTTMsg> getDelCoaAcct(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_ACCT);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_ACCTTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaAcct", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_ACCTTMsg> getUpdCoaAcct(BigDecimal transactionId) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);

        return (List<COA_ACCTTMsg>) this.ssmBatchClient.queryObjectList("getAcctToBeUpdated", queryParam);
    }
    
    private boolean procCOAProd (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_PRODTMsg> listTmsg = getNewCoaProd(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_PRODTMsg tmsg : listTmsg) {
                int c = tmsg.coaProdCd.getValue().charAt(0);                
                setValue(tmsg.coaProdSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_PRODTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_PROD";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaProd(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_PRODTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    // summary
                    PRNT_COA_PRODTMsg pTmsg = new PRNT_COA_PRODTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
                
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaProd(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_PRODTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_PROD";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_PRODTMsg> getNewCoaProd(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_PROD);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_PROD);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_PROD_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_PROD_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_PRODTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaProd", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_PRODTMsg> getReactivateCoaProd(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_PROD);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_PROD);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_PROD_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_PROD_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_PRODTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaProd", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_PRODTMsg> getDelCoaProd(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_PROD);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_PRODTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaProd", queryParam);
    }
    
    private boolean procCOACh (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_CHTMsg> listTmsg = getNewCoaCh(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_CHTMsg tmsg : listTmsg) {
                int c = tmsg.coaChCd.getValue().charAt(0);                
                setValue(tmsg.coaChSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_CHTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_CH";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaCh(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_CHTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    // summary
                    PRNT_COA_CHTMsg pTmsg = new PRNT_COA_CHTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
                
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaCh(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_CHTMsg[listTmsg.size()]));
            } else {
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_CH";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_CHTMsg> getNewCoaCh(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_CH);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CH);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_CH_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_CH_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CHTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaCh", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_CHTMsg> getReactivateCoaCh(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_CH);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CH);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_CH_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_CH_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CHTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaCh", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_CHTMsg> getDelCoaCh(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_CH);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_CHTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaCh", queryParam);
    }
    
    private boolean procCOAAffl (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_AFFLTMsg> listTmsg = getNewCoaAffl(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_AFFLTMsg tmsg : listTmsg) {
                int c = tmsg.coaAfflCd.getValue().charAt(0);                
                setValue(tmsg.coaAfflSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_AFFLTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_AFFL";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaAffl(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_AFFLTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    // summary
                    PRNT_COA_AFFLTMsg pTmsg = new PRNT_COA_AFFLTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
                
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaAffl(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_AFFLTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_AFFL";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_AFFLTMsg> getNewCoaAffl(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_AFFL);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_AFFL);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_AFFL_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_AFFL_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_AFFLTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaAffl", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_AFFLTMsg> getReactivateCoaAffl(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_AFFL);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_AFFL);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_AFFL_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_AFFL_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_AFFLTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaAffl", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_AFFLTMsg> getDelCoaAffl(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_AFFL);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_AFFLTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaAffl", queryParam);
    }
    
    private boolean procCOAProj (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_PROJTMsg> listTmsg = getNewCoaProj(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_PROJTMsg tmsg : listTmsg) {
                int c = tmsg.coaProjCd.getValue().charAt(0);                
                setValue(tmsg.coaProjSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_PROJTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_PROJ";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaProj(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_PROJTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    // summary
                    PRNT_COA_PROJTMsg pTmsg = new PRNT_COA_PROJTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaProj(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_PROJTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_PROJ";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_PROJTMsg> getNewCoaProj(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_PROJ);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_PROJ);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_PROJ_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_PROJ_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_PROJTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaProj", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_PROJTMsg> getReactivateCoaProj(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_PROJ);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_PROJ);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_PROJ_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_PROJ_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_PROJTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaProj", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_PROJTMsg> getDelCoaProj(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_PROJ);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_PROJTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaProj", queryParam);
    }
    
    private boolean procCOAExtn (BigDecimal transactionId, String smryFlg) {
        
        // get insert list
        List<COA_EXTNTMsg> listTmsg = getNewCoaExtn(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_EXTNTMsg tmsg : listTmsg) {
                int c = tmsg.coaExtnCd.getValue().charAt(0);                
                setValue(tmsg.coaExtnSortNum, new BigDecimal(c));
            }
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.insert(listTmsg.toArray(new COA_EXTNTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.insert(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to insert COA_EXTN";
                return false;
            }
            commitCount = commitCount + rtn;
        }
       
        
        // get reactivate list
        listTmsg = getReactivateCoaExtn(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            for (COA_EXTNTMsg tmsg : listTmsg) {
                if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(tmsg);
                } else {
                    // summary
                    PRNT_COA_EXTNTMsg pTmsg = new PRNT_COA_EXTNTMsg();
                    EZDTMsg.copy(tmsg, null, pTmsg, null);
                    
                    // update ezcancelflag to '0'
                    EZDTBLAccessor.create(pTmsg);
                }
            }
            
            commitCount = commitCount + listTmsg.size();
        }
        
        // get delete list
        listTmsg = getDelCoaExtn(transactionId, smryFlg);
        
        if (listTmsg != null && listTmsg.size() > 0) {
            
            int rtn;
            if (ZYPConstant.FLG_OFF_N.equals(smryFlg)) {
                rtn = S21FastTBLAccessor.removeLogical(listTmsg.toArray(new COA_EXTNTMsg[listTmsg.size()]));
            } else {
                // summary
                rtn = S21FastTBLAccessor.removeLogical(copyToPrnt(listTmsg));
            }
            
            if (rtn != listTmsg.size()) {
                errMsg = "Unable to delete COA_EXTN";
                return false;
            }
            commitCount = commitCount + rtn;
        }
        
        return true;
    }

    @SuppressWarnings("unchecked")
    private List<COA_EXTNTMsg> getNewCoaExtn(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_EXTN);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_EXTN);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_EXTN_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_EXTN_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_EXTNTMsg>) this.ssmBatchClient.queryObjectList("getNewCoaExtn", queryParam);
    }

    @SuppressWarnings("unchecked")
    private List<COA_EXTNTMsg> getReactivateCoaExtn(BigDecimal transactionId, String smryFlg) {
        
        String defaultCode = getDefaultCode(COA_SEG_NM_COA_EXTN);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_EXTN);
        queryParam.put("yOrN", YES);
        queryParam.put("defaultCode", defaultCode);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("maxLenNm", MAX_LENGTH_COA_EXTN_NM);
        queryParam.put("maxLenDesc", MAX_LENGTH_COA_EXTN_DESC_TXT);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_EXTNTMsg>) this.ssmBatchClient.queryObjectList("getReactivateCoaExtn", queryParam);
    }
    
    @SuppressWarnings("unchecked")
    private List<COA_EXTNTMsg> getDelCoaExtn(BigDecimal transactionId, String smryFlg) {
        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("transactionId", transactionId);
        queryParam.put("coaSegNm", COA_SEG_NM_COA_EXTN);
        queryParam.put("yOrN", YES);
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("smryFlg", smryFlg);

        return (List<COA_EXTNTMsg>) this.ssmBatchClient.queryObjectList("getDelCoaExtn", queryParam);
    }
    
    /** Update Code Tables */
    /*
    private final class UpdateCodeTables extends S21SsmBooleanResultSetHandlerSupport {

        // Open/End Status
        private String coaFlexValSetId = null;

        private UpdateCodeTables(String coaFlexValSetId) {
            this.coaFlexValSetId = coaFlexValSetId;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            int resultCount = 0;
            try {
                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

                // Clear code table
                clearCodeTables(this.coaFlexValSetId);

                while (rs.next()) {

                    String coaFlexValCd = checkNull(rs.getString(COA_FLEX_VAL_CD));
                    String coaDescTxt = checkNull(rs.getString(COA_DESC_TXT));
                    BigDecimal coaFlexValSetId = checkNull(rs.getBigDecimal(COA_FLEX_VAL_SET_ID));

                    String coaFlexValSetIdStr = coaFlexValSetId.toString();
                    int maxDescLength = getMaxDescLength(coaFlexValSetIdStr);
                    coaDescTxt = checkLength(coaDescTxt, maxDescLength);

                    if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_CMPY)) {
                        // COA Company
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_CMPY, coaFlexValCd)) {
                            errMsg = "Invalid Length for coaCmpyCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_CMPYTMsg tMsg = new COA_CMPYTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaCmpyCd.setValue(coaFlexValCd);
                        tMsg.coaCmpyNm.setValue(coaDescTxt);
                        tMsg.coaCmpySortNum.setValue(resultCount + 1);
                        tMsg.coaCmpyDescTxt.clear();

                        createRecord(tMsg);
                    } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_BR)) {
                        // COA Branch
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_BR, coaFlexValCd)) {
                            errMsg = "Invalid Length for coaBrCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_BRTMsg tMsg = new COA_BRTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaBrCd.setValue(coaFlexValCd);
                        tMsg.coaBrNm.setValue(coaDescTxt);
                        tMsg.coaBrSortNum.setValue(resultCount + 1);
                        tMsg.coaBrDescTxt.clear();

                        createRecord(tMsg);
                    } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_CC)) {
                        // COA Cost Center
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_CC, coaFlexValCd)) {
                            errMsg = "Invalid Length for coaCcCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_CCTMsg tMsg = new COA_CCTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaCcCd.setValue(coaFlexValCd);
                        tMsg.coaCcNm.setValue(coaDescTxt);
                        tMsg.coaCcSortNum.setValue(resultCount + 1);
                        tMsg.coaCcDescTxt.clear();

                        createRecord(tMsg);
                    } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_PROD)) {
                        // COA Product
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_PROD, coaFlexValCd)) {
                            errMsg = "Invalid Length for coaProdCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_PRODTMsg tMsg = new COA_PRODTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaProdCd.setValue(coaFlexValCd);
                        tMsg.coaProdNm.setValue(coaDescTxt);
                        tMsg.coaProdSortNum.setValue(resultCount + 1);
                        tMsg.coaProdDescTxt.clear();

                        createRecord(tMsg);
                    } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_CH)) {
                        // COA Channel
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_CH, coaFlexValCd)) {
                            errMsg = "Invalid Length for coaChCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_CHTMsg tMsg = new COA_CHTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaChCd.setValue(coaFlexValCd);
                        tMsg.coaChNm.setValue(coaDescTxt);
                        tMsg.coaChSortNum.setValue(resultCount + 1);
                        tMsg.coaChDescTxt.clear();

                        createRecord(tMsg);
                    } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_AFFL)) {
                        // COA Affiliate
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_AFFL, coaFlexValCd)) {
                            errMsg = "Invalid Length for coaAfflCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_AFFLTMsg tMsg = new COA_AFFLTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaAfflCd.setValue(coaFlexValCd);
                        tMsg.coaAfflNm.setValue(coaDescTxt);
                        tMsg.coaAfflSortNum.setValue(resultCount + 1);
                        tMsg.coaAfflDescTxt.clear();

                        createRecord(tMsg);
                    } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_PROJ)) {
                        // COA Project
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_PROJ, coaFlexValCd)) {
                            errMsg = "Invalid Length for coaProjCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_PROJTMsg tMsg = new COA_PROJTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaProjCd.setValue(coaFlexValCd);
                        tMsg.coaProjNm.setValue(coaDescTxt);
                        tMsg.coaProjSortNum.setValue(resultCount + 1);
                        tMsg.coaProjDescTxt.clear();

                        createRecord(tMsg);
                    } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_EXTN)) {
                        // COA Extension
                        if (!isValidCodeLength(COA_FLEX_VAL_SET_ID_COA_EXTN, coaFlexValCd)) {
                            errMsg = "Invalid Length: coaExtnCd: " + coaFlexValCd;
                            return Boolean.FALSE;
                        }

                        COA_EXTNTMsg tMsg = new COA_EXTNTMsg();
                        tMsg.glblCmpyCd.setValue(glblCmpyCd);
                        tMsg.coaExtnCd.setValue(coaFlexValCd);
                        tMsg.coaExtnNm.setValue(coaDescTxt);
                        tMsg.coaExtnSortNum.setValue(resultCount + 1);
                        tMsg.coaExtnDescTxt.clear();

                        createRecord(tMsg);
                    }
                    resultCount++;
                }
                if (tMsgCnt != 0) {
                    createRecord(null);
                }

            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }
            if (resultCount == 0) {
                errMsg = "No Result for Code " + this.coaFlexValSetId.toString();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        private boolean isValidCodeLength(String coaFlexValSetIdStr, String coaFlexValCd) {
            if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_CMPY)) {
                // COA Company
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_CMPY) {
                    return false;
                } else {
                    return true;
                }
            } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_BR)) {
                // COA Branch
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_BR) {
                    return false;
                } else {
                    return true;
                }
            } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_CC)) {
                // COA Cost Center
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_CC) {
                    return false;
                } else {
                    return true;
                }
            } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_PROD)) {
                // COA Product
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_PROD) {
                    return false;
                } else {
                    return true;
                }
            } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_CH)) {
                // COA Channel
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_CH) {
                    return false;
                } else {
                    return true;
                }
            } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_AFFL)) {
                // COA Affiliate
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_AFFL) {
                    return false;
                } else {
                    return true;
                }
            } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_PROJ)) {
                // COA Project
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_PROJ) {
                    return false;
                } else {
                    return true;
                }
            } else if (coaFlexValSetIdStr.equals(COA_FLEX_VAL_SET_ID_COA_EXTN)) {
                // COA Extension
                if (coaFlexValCd.length() > COA_FLEX_VAL_CD_LENGTH_COA_EXTN) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }

        
        private void clearCodeTables(String coaFlexValSetId) {

            if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_CMPY)) {
                // COA Company
                COA_CMPYTMsg tMsg = new COA_CMPYTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_BR)) {
                // COA Branch
                COA_BRTMsg tMsg = new COA_BRTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_CC)) {
                // COA Cost Center
                COA_CCTMsg tMsg = new COA_CCTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_PROD)) {
                // COA Product
                COA_PRODTMsg tMsg = new COA_PRODTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_CH)) {
                // COA Channel
                COA_CHTMsg tMsg = new COA_CHTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_AFFL)) {
                // COA Affiliate
                COA_AFFLTMsg tMsg = new COA_AFFLTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_PROJ)) {
                // COA Project
                COA_PROJTMsg tMsg = new COA_PROJTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_EXTN)) {
                // COA Extension
                COA_EXTNTMsg tMsg = new COA_EXTNTMsg();
                tMsg.glblCmpyCd.setValue(glblCmpyCd);
                S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"glblCmpyCd" });
            }
        }

        private int getMaxDescLength(String coaFlexValSetId) {

            if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_CMPY)) {
                // COA Company
                return MAX_LENGTH_COA_CMPY_NM;
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_BR)) {
                // COA Branch
                return MAX_LENGTH_COA_BR_NM;
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_CC)) {
                // COA Cost Center
                return MAX_LENGTH_COA_CC_NM;
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_PROD)) {
                // COA Product
                return MAX_LENGTH_COA_PROD_NM;
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_CH)) {
                // COA Channel
                return MAX_LENGTH_COA_CH_NM;
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_AFFL)) {
                // COA Affiliate
                return MAX_LENGTH_COA_AFFL_NM;
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_PROJ)) {
                // COA Project
                return MAX_LENGTH_COA_PROJ_NM;
            } else if (coaFlexValSetId.equals(COA_FLEX_VAL_SET_ID_COA_EXTN)) {
                // COA Extension
                return MAX_LENGTH_COA_EXTN_NM;
            } else {
                return 0;
            }
        }

        private void createRecord(EZDTMsg tMsg) {

            if (tMsg != null) {
                tMsgCreate[tMsgCnt] = tMsg;
                tMsgCnt += 1;

            } else { // array may be not full
                tMsgCreate = changeArraySize(tMsgCreate, tMsgCnt);
            }

            // per 10000 lines
            if (tMsgCnt >= BULK_INSERT_COUNT || tMsg == null) {

                int retCnt = S21FastTBLAccessor.insert(tMsgCreate);

                // if passed records' count and return count don't
                // match, error
                if (retCnt != tMsgCnt) {
                    throw new S21AbendException(ZZBM0074E);
                }
                commitCount += tMsgCnt;
                // initialize
                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }

        private EZDTMsg[] changeArraySize(EZDTMsg[] arrayRec, int newsize) {

            EZDTMsg[] copyMsgs = arrayRec.clone();
            arrayRec = new EZDTMsg[newsize];
            // copy data
            for (int i = 0; i < newsize; i++) {
                arrayRec[i] = copyMsgs[i];
            }
            return arrayRec;
        }

    }// class UpdateCodeTables
*/
    
    private boolean clearInferfaceTable() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        return (Boolean) this.ssmBatchClient.queryObject("getTransactionIDFromInterfaceTable", queryParam, new ClearInferfaceTable());
    }

    /** Clear Inferface Table */
    private final class ClearInferfaceTable extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                while (rs.next()) {
                    BigDecimal transactionId = checkNull(rs.getBigDecimal(TRANSACTION_ID));

                    NMAI0020_01TMsg tMsg = new NMAI0020_01TMsg();
                    tMsg.transactionId.setValue(transactionId);
                    S21FastTBLAccessor.removeByPartialValue(tMsg, new String[] {"transactionId" });
                }
            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
    }// class ClearInferfaceTable
    
    
    /** In some table, default value will be excluded */
    private String getDefaultCode(String coaFlexValSetId) {
// memo maybe not required.
        if (coaFlexValSetId.equals(COA_SEG_NM_COA_CMPY)) {
            // COA Company
            return null;
        } else if (coaFlexValSetId.equals(COA_SEG_NM_COA_BR)) {
            // COA Branch
            //return "00";
            return null;
        } else if (coaFlexValSetId.equals(COA_SEG_NM_COA_CC)) {
            // COA Cost Center
            //return "000000";
            return null;
        } else if (coaFlexValSetId.equals(COA_SEG_NM_COA_PROD)) {
            // COA Product
            //return "00";
            return null;
        } else if (coaFlexValSetId.equals(COA_SEG_NM_COA_CH)) {
            // COA Channel
            return null;
        } else if (coaFlexValSetId.equals(COA_SEG_NM_COA_AFFL)) {
            // COA Affiliate
            return null;
        } else if (coaFlexValSetId.equals(COA_SEG_NM_COA_PROJ)) {
            // COA Project
            return null;
        } else if (coaFlexValSetId.equals(COA_SEG_NM_COA_EXTN)) {
            // COA Extension
            return null;
        } else {
            return null;
        }
    }

    private BigDecimal checkNull(BigDecimal input) {

        BigDecimal num = new BigDecimal("0");
        if (input == null) {
            return num;
        } else {
            return input;
        }
    }

    private String checkNull(String val) {
        if (val == null) {
            return BLANK;
        } else {
            return val;
        }
    }

    private String checkLength(String val, int maxLength) {
        if (val == null) {
            return BLANK;
        } else {
            if (val.length() > maxLength) {
                return val.substring(0, maxLength);
            } else {
                return val;
            }
        }
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, 0);
        System.out.println("=========errMsg : " + errMsg);

        S21InfoLogOutput.println("termRoutine Method End");
    }
    
    private PRNT_COA_CMPYTMsg[] copyToPrnt(List<COA_CMPYTMsg> listTmsg) {
        List<PRNT_COA_CMPYTMsg> pListTmsg = new ArrayList<PRNT_COA_CMPYTMsg> ();
        
        for (COA_CMPYTMsg tmsg : listTmsg) {
            PRNT_COA_CMPYTMsg pTmsg = new PRNT_COA_CMPYTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_CMPYTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_BRTMsg[] copyToPrnt(List<COA_BRTMsg> listTmsg) {
        List<PRNT_COA_BRTMsg> pListTmsg = new ArrayList<PRNT_COA_BRTMsg> ();
        
        for (COA_BRTMsg tmsg : listTmsg) {
            PRNT_COA_BRTMsg pTmsg = new PRNT_COA_BRTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_BRTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_CCTMsg[] copyToPrnt(List<COA_CCTMsg> listTmsg) {
        List<PRNT_COA_CCTMsg> pListTmsg = new ArrayList<PRNT_COA_CCTMsg> ();
        
        for (COA_CCTMsg tmsg : listTmsg) {
            PRNT_COA_CCTMsg pTmsg = new PRNT_COA_CCTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_CCTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_ACCTTMsg[] copyToPrnt(List<COA_ACCTTMsg> listTmsg) {
        List<PRNT_COA_ACCTTMsg> pListTmsg = new ArrayList<PRNT_COA_ACCTTMsg> ();
        
        for (COA_ACCTTMsg tmsg : listTmsg) {
            PRNT_COA_ACCTTMsg pTmsg = new PRNT_COA_ACCTTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_ACCTTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_PRODTMsg[] copyToPrnt(List<COA_PRODTMsg> listTmsg) {
        List<PRNT_COA_PRODTMsg> pListTmsg = new ArrayList<PRNT_COA_PRODTMsg> ();
        
        for (COA_PRODTMsg tmsg : listTmsg) {
            PRNT_COA_PRODTMsg pTmsg = new PRNT_COA_PRODTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_PRODTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_CHTMsg[] copyToPrnt(List<COA_CHTMsg> listTmsg) {
        List<PRNT_COA_CHTMsg> pListTmsg = new ArrayList<PRNT_COA_CHTMsg> ();
        
        for (COA_CHTMsg tmsg : listTmsg) {
            PRNT_COA_CHTMsg pTmsg = new PRNT_COA_CHTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_CHTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_AFFLTMsg[] copyToPrnt(List<COA_AFFLTMsg> listTmsg) {
        List<PRNT_COA_AFFLTMsg> pListTmsg = new ArrayList<PRNT_COA_AFFLTMsg> ();
        
        for (COA_AFFLTMsg tmsg : listTmsg) {
            PRNT_COA_AFFLTMsg pTmsg = new PRNT_COA_AFFLTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_AFFLTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_PROJTMsg[] copyToPrnt(List<COA_PROJTMsg> listTmsg) {
        List<PRNT_COA_PROJTMsg> pListTmsg = new ArrayList<PRNT_COA_PROJTMsg> ();
        
        for (COA_PROJTMsg tmsg : listTmsg) {
            PRNT_COA_PROJTMsg pTmsg = new PRNT_COA_PROJTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_PROJTMsg[pListTmsg.size()]);
    }
    
    private PRNT_COA_EXTNTMsg[] copyToPrnt(List<COA_EXTNTMsg> listTmsg) {
        List<PRNT_COA_EXTNTMsg> pListTmsg = new ArrayList<PRNT_COA_EXTNTMsg> ();
        
        for (COA_EXTNTMsg tmsg : listTmsg) {
            PRNT_COA_EXTNTMsg pTmsg = new PRNT_COA_EXTNTMsg();
            
            EZDTMsg.copy(tmsg, null, pTmsg, null);
            
            pListTmsg.add(pTmsg);
        }
        
        return pListTmsg.toArray(new PRNT_COA_EXTNTMsg[pListTmsg.size()]);
    }
}
