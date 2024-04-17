/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NMA.NMAB021001;

import java.math.BigDecimal;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NMAI1405_01TMsg;
import business.db.NMAI1405_01TMsgArray;
import business.db.AMER_MSTRTMsg;
import business.db.AMER_MSTRTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.PKG_UOMTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;


/**
 * <pre>
 *  scheduled batch
 *  NMAB021001 Mercury Merchandise data into s21 
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/02/17   SRA             G.Fu            Create          N/A
 *</pre>
 */
public class NMAB021001 extends S21BatchMain {
    
    /** message of processed record's count */
    private static final String ZZBM0009I = "ZZBM0009I";

    /** error message (Global Company Code error)  */
    private static final String GLBL_CMPY_CD_ERR = "NMAM0001E";

    /** error message (DB access error)  */
    private static final String DBACCESS_ERR = "NMAM8003E";

    /** error message (sequence error)  */
    private static final String SEQ_ERR = "NMAM8004E";

    /** error message (EXEC_STS error)  */
    private static final String EXECSTS_ERR = "NMAM8006E";

    /** warning message (registration record  exist)  */
    private static final String INS_REC_EXIST = "NMAM0253W";

    /** warning message (update record doesn't exist)  */
    private static final String UPD_REC_NOEXIST = "NMAM0120W";

    /** warning message (deletion record doesn't exist)  */
    private static final String DEL_REC_NOEXIST = "NMAM0121W";

    /** warning message (two or more records exist)  */
    private static final String MORE_REC_EXIST = "NMAM8005W";

    /** Data Finded */
    private static final String DATA_FINDED = "find";

    /** Data Created */
    private static final String DATA_INSERTED = "insert";

    /** Data Updated */
    private static final String DATA_UPDATED = "update";

    /** Data Logical Removed */
    private static final String DATA_LOGICAL_REMOVED = "logicalRemove";

    /** DB Table : INTERFACE_TRANSACTION */
    private static final String INTERFACE_TRANSACTION = "INTERFACE_TRANSACTION";

    /** DB Table : NMAI1405_01 */
    private static final String NMAI1405_01 = "NMAI1405_01";

    /** DB Table : AMER_MSTR */
    private static final String AMER_MSTR = "AMER_MSTR";

    /** DB Sequence : AMER_MSTR_SQ */
    private static final String AMER_MSTR_SQ = "AMER_MSTR_SQ";

    /** EXEC_STS(Insert) */
    private static final String EXECSTS_INS = "I";
    /** EXEC_STS(UPdate) */
    private static final String EXECSTS_UP  = "U";
    /** EXEC_STS(Delete) */
    private static final String EXECSTS_DEL = "D";

    /** interface_transaction find count */
    private int tranIdFindCount = 0;
    /** amai1405_01 find count */
    private int ifFindCount = 0;
    /** insert count */
    private int insertCount = 0;
    /** update count */
    private int updateCount = 0;
    /** logical remove count */
    private int logicalRemoveCount = 0;

    /** interface_transaction find commit count */
    private int tranIdFindCommitCount = 0;
    /** amai1405_01 find commit count */
    private int ifFindCommitCount = 0;
    /** insert commit count */
    private int insertCommitCount = 0;
    /** update commit count */
    private int updateCommitCount = 0;
    /** logical remove commit count */
    private int logicalRemoveCommitCount = 0;
    
    private int updateAmerMstr = 0;
    
    /** termineted code */
    private TERM_CD termCd; 

    /** Interface ID */
    private String interfaceId = null;    
    
    /**
     * Main method  
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NMAB021001().executeBatch(NMAB021001.class.getSimpleName());

    }

    /**
     * Initialize process
     */
    protected void initRoutine() {

        // Initialize variable
        this.termCd = TERM_CD.NORMAL_END;

        // argument check(glblCmpyCd)
        if (!ZYPCommonFunc.hasValue(this.getGlobalCompanyCode())) {
            throw new S21AbendException("NMAM8039E", new String[] {"GlobalCompanyCode"});
        }

        // get InterfaceId(NMAI1405)
        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            // INTERFACE ID is no data
            throw new S21AbendException( "NMAM8009E");
        }
        EZDDebugOutput.println(1, "----- [ DEBUG PRINT ] ----- interfaceId : " + this.interfaceId, this);

    }


    /**
     * Main process
     */
    protected void mainRoutine() {

        // Execute application process
        doProcess();

    }

    /**
     * doProcess process
     */
    private void doProcess() {
        try {

            S21TransactionTableAccessor s21tta = new S21TransactionTableAccessor();
            BigDecimal[] transactionIdArray = (BigDecimal[]) s21tta.getIntegrationRecordInitAsc(this.interfaceId);

            if (transactionIdArray.length == 0) {
                return;
            }

            if (!ZYPCommonFunc.hasValue(getGlobalCompanyCode())) {
                throw new S21AbendException(GLBL_CMPY_CD_ERR);
            }

            for (int idx = 0; idx < transactionIdArray.length; idx++) {

                this.tranIdFindCount++;

                BigDecimal transactionId = transactionIdArray[idx];
                EZDDebugOutput.println(1, String.format("TRANSACTION_ID:%s", transactionId) , this);

                doProcessEvryTransactionId(this.interfaceId, transactionId);
                // INTEGRATION_TABLE process end
                s21tta.endIntegrationProcess(this.interfaceId, transactionId);
                
            }
            setCommitCount();

        } finally {
            outputProcessCnt();

        }

    }
    
    /**
     * application process
     */
    private void doProcessEvryTransactionId(String interfaceId, BigDecimal transactionId) {

        // select transaction table
        NMAI1405_01TMsg searchMsg = new NMAI1405_01TMsg();
        searchMsg.setSQLID("001");
        searchMsg.setConditionValue("interfaceId01", interfaceId);
        searchMsg.setConditionValue("transactionId01", transactionId);
        NMAI1405_01TMsgArray ifMsgArray = (NMAI1405_01TMsgArray) EZDTBLAccessor.findByConditionForUpdate(searchMsg);

        for (int idx = 0; idx < ifMsgArray.length(); idx++) {

            this.ifFindCount++;

            NMAI1405_01TMsg ifRec = ifMsgArray.no(idx);
            // NMAL1405_01 -> AMER_MSTR
            copyNMAR0010(ifRec);
            // update mdse(upc_cd)
            // ITG373362 20120113 Start
//            updateMdse(ifRec);
//// QC1906
//            updateMdseStorePackage(ifRec);
            if(this.updateAmerMstr == 1) {
                updateMdse(ifRec);
                updateMdseStorePackage(ifRec);
            }
            // ITG373362 20120113 End            
        }

    }

    /**
     * application process
     */
    private void copyNMAR0010(final NMAI1405_01TMsg ifRec) {

        AMER_MSTRTMsg searchMsg = new AMER_MSTRTMsg();
        AMER_MSTRTMsg upRec = new AMER_MSTRTMsg();

        String execSts = ifRec.execStsCdIf.getValue();
        BigDecimal requestNbrIf  = ifRec.requestNbrIf.getValue();

        // select AMER_MSTR
        searchMsg.setSQLID("004");
        searchMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        searchMsg.setConditionValue("amerRqstNum01", requestNbrIf);
        AMER_MSTRTMsgArray mstMsgArray = (AMER_MSTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(searchMsg);
        // ITG373362 20120113 Start   
        this.updateAmerMstr = 0;
        // ITG373362 20120113 end
        if (mstMsgArray.length() == 0) {

            // EXECSTS (I or U) 
            if (EXECSTS_INS.equals(execSts) || EXECSTS_UP.equals(execSts)) {

                copyMsg(ifRec, upRec);
                insert(upRec);

                if (EXECSTS_UP.equals(execSts)) {
                    S21InfoLogOutput.printlnv(UPD_REC_NOEXIST, "Request No.:" + requestNbrIf.toString());
                }

            // EXECSTS (D) 
            } else if (EXECSTS_DEL.equals(execSts)) {

                S21InfoLogOutput.printlnv(DEL_REC_NOEXIST, "Request No.:" + requestNbrIf.toString());

            } else {

                throw new S21AbendException(EXECSTS_ERR, new String[] {"EXEC_STS_CD:" + execSts});

            }


        } else {

            for (int idx = 0; idx < mstMsgArray.length(); idx++) {

                upRec = mstMsgArray.no(idx);

                // EXECSTS (I or U) 

                if (EXECSTS_INS.equals(execSts) || EXECSTS_UP.equals(execSts)) {
                    // ITG373362 20120113 Start        
//                    copyMsg(ifRec, upRec);
//                    update(upRec);
                    if (EXECSTS_UP.equals(execSts)) {
                        copyMsg(ifRec, upRec);
                        update(upRec);
                        this.updateAmerMstr = 1;
                    }
                    // ITG373362 20120113 End 
                    if (EXECSTS_INS.equals(execSts)) {
                        S21InfoLogOutput.printlnv(INS_REC_EXIST, "Request No.:" + requestNbrIf.toString());
                    }

                // EXECSTS (D) 
                } else if (EXECSTS_DEL.equals(execSts)) {

                    logicalRemove(upRec);

                } else {

                    throw new S21AbendException(EXECSTS_ERR, new String[] {"EXEC_STS_CD:" + execSts});

                }
            }


            if (mstMsgArray.length() > 1) {

                S21InfoLogOutput.printlnv(MORE_REC_EXIST, "Request No.:" + requestNbrIf.toString());
            }
        }

    }
    
    /**
     * update Mdse talbe
     */
    private void updateMdse(final NMAI1405_01TMsg ifRec) {

        MDSETMsg mdseCond = new MDSETMsg();

        String mdseCd = ifRec.merchandiseCodeL12If.getValue();
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            mdseCond.glblCmpyCd.setValue(getGlobalCompanyCode());
            mdseCond.mdseCd.setValue(mdseCd);
            mdseCond = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdate(mdseCond);
            if (null != mdseCond) {
                mdseCond.upcCd.setValue(ifRec.codeUpcIf.getValue());
                
// QC1906
                ZYPEZDItemValueSetter.setValue(mdseCond.gtinCd, getGtinFromUpc(ifRec.codeUpcIf.getValue()));
                
                EZDTBLAccessor.update(mdseCond);
            }
        }
    }
    
// QC1906
    private void updateMdseStorePackage(final NMAI1405_01TMsg ifRec) {

        MDSE_STORE_PKGTMsg condTMsg = new MDSE_STORE_PKGTMsg();

        String mdseCd = ifRec.merchandiseCodeL12If.getValue();
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            condTMsg.setSQLID("002");
            condTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            condTMsg.setConditionValue("mdseCd01", mdseCd);
            MDSE_STORE_PKGTMsgArray tMsgArray = (MDSE_STORE_PKGTMsgArray) EZDTBLAccessor.findByConditionForUpdate(condTMsg);
            
            if (tMsgArray.length() > 0) {
                for( int i=0; i<tMsgArray.length(); i++ ){
                    MDSE_STORE_PKGTMsg tMsg = tMsgArray.no(i);
                    
                    if( PKG_UOM.EACH.equals( tMsg.pkgUomCd.getValue() ) ){
                        ZYPEZDItemValueSetter.setValue(tMsg.gtinCd, getGtinFromUpc(ifRec.codeUpcIf.getValue()));
                        // No need to update scc14Num (Should be null)
                        
                        EZDTBLAccessor.update(tMsg);
                        
                    } else {
                        String packageLevelNumber = getPackageLevelNumber(tMsg.pkgUomCd.getValue());
                        String scc14Num = getScc14NumFromUpc(packageLevelNumber, ifRec.codeUpcIf.getValue());
                        
                        if( !ZYPCommonFunc.hasValue(tMsg.ovrdUpcOrScc14Num)){
                            ZYPEZDItemValueSetter.setValue(tMsg.gtinCd, scc14Num);
                            ZYPEZDItemValueSetter.setValue(tMsg.scc14Num, scc14Num);
                        } else {
                            ZYPEZDItemValueSetter.setValue(tMsg.scc14Num, scc14Num);
                        }
                        
                        EZDTBLAccessor.update(tMsg);
                    }
                }
            }
        }
    }    

    /**
     * application process
     */
    private void copyMsg(NMAI1405_01TMsg ifRec, AMER_MSTRTMsg upRec) {

        ZYPEZDItemValueSetter.setValue(upRec.amerMdlCd, ifRec.modelCodeIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRecId, ifRec.recordIdL4If);
        ZYPEZDItemValueSetter.setValue(upRec.amerTrxNum, ifRec.transactionNbrIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRecNum, ifRec.recordNbrIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRqstCanonCmpyCd, ifRec.requestCompanyIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRqstNum, ifRec.requestNbrIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRqstTpCd, ifRec.requestTypeIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRnwTpCd, ifRec.renewedIndIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerBomItemNum, ifRec.nbrBomItemsIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRtrnCd, ifRec.returnCodeIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerErrMsgTxt, ifRec.errorMessageIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerMdseCd, ifRec.merchandiseCodeL12If);
        ZYPEZDItemValueSetter.setValue(upRec.amerNewOldTpCd, ifRec.newOldIndIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerPkgNm, ifRec.namePackageIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerMdseNm, ifRec.nameMerchandiseIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerXtrMdseNm, ifRec.fillerL22If);
        ZYPEZDItemValueSetter.setValue(upRec.amerJanEanAutoIssTpCd, ifRec.indJanEanIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerUpcAutoIssTpCd, ifRec.indUpcIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerJanEanMakerCd, ifRec.makerCodeJanEanIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerUpcMakerCd, ifRec.makerCodeUpcIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerJanEanCd, ifRec.codeJanEanIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerUpcCd, ifRec.codeUpcIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerOwnrCmpyCd, ifRec.codeOwnerCompanyIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerOwnrProdCd, ifRec.codeOwnerProductIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerConslSegCd, ifRec.segmentConsolidationIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerKitItemTpCd, ifRec.indKitItemIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerMdseClsTpCd, ifRec.classification1If);
        ZYPEZDItemValueSetter.setValue(upRec.amerXtrMdseClsTpCd, ifRec.classification2If);
        ZYPEZDItemValueSetter.setValue(upRec.amerEngNm, ifRec.nameEnglishIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerFrNm, ifRec.nameFrenchIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerSpaNm, ifRec.nameSpanishIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerOtherNm, ifRec.nameOtherIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerOrigCtryCd, ifRec.countryOriginIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerDclsDt, ifRec.dateDisclosedIf.getValue());
        ZYPEZDItemValueSetter.setValue(upRec.amerModDt, ifRec.dateModifiedL8If.getValue());
        ZYPEZDItemValueSetter.setValue(upRec.amerCloDt, ifRec.dateClosedIf.getValue());
        ZYPEZDItemValueSetter.setValue(upRec.amerOpId, ifRec.operatorIdIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerXtrMdseCd, ifRec.codeExtraL12If);
        ZYPEZDItemValueSetter.setValue(upRec.amerMainProdTpCd, ifRec.indMainProdIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerRfrbTpCd, ifRec.indRefurbishIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerOrigMdseCd, ifRec.codeOrigIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerOldItemCd, ifRec.codeOldItemIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerSendItemCd, ifRec.codeSendItemIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerSetItemTpCd, ifRec.indSetItemIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerInpSrcTxt, ifRec.inputSourceIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerInclKitTpCd, ifRec.indIncludeKitIf);
    }

    /**
     * application process
     */
    private void insert(AMER_MSTRTMsg upRec) {

        upRec.amerMstrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(AMER_MSTR_SQ));
        if (upRec.amerMstrPk.getValue() == null) {
            throw new S21AbendException(SEQ_ERR, new String[] {AMER_MSTR_SQ});
        }

        upRec.glblCmpyCd.setValue(getGlobalCompanyCode());

        EZDTBLAccessor.insert(upRec);

        this.insertCount++;

    }

    /**
     * application process
     */
    private void update(AMER_MSTRTMsg upRec) {

        EZDTBLAccessor.update(upRec);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upRec.getReturnCode())) {
            EZDDebugOutput.println(1, "SQL Error:" + upRec.getReturnCode() , this);
            throw new S21AbendException(DBACCESS_ERR, new String[] {AMER_MSTR, DATA_UPDATED});
        }

        this.updateCount++;

    }

    /**
     * application process
     */
    private void logicalRemove(AMER_MSTRTMsg upRec) {

       EZDTBLAccessor.logicalRemove(upRec);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upRec.getReturnCode())) {
            EZDDebugOutput.println(1, "SQL Error:" + upRec.getReturnCode() , this);
            throw new S21AbendException(DBACCESS_ERR, new String[] {AMER_MSTR, DATA_LOGICAL_REMOVED});
        }

        this.logicalRemoveCount++;

    }

    /**
     * application process
     */
    private void setCommitCount() {

        this.tranIdFindCommitCount = this.tranIdFindCount;
        this.ifFindCommitCount = this.ifFindCount;
        this.insertCommitCount = this.insertCount;
        this.updateCommitCount = this.updateCount;
        this.logicalRemoveCommitCount = this.logicalRemoveCount;

    }

    /**
     * application process
     */
    private void outputProcessCnt() {

        // processed record's count output
        S21InfoLogOutput.printlnv(ZZBM0009I, INTERFACE_TRANSACTION, DATA_FINDED, this.tranIdFindCommitCount);
        S21InfoLogOutput.printlnv(ZZBM0009I, NMAI1405_01, DATA_FINDED, this.ifFindCommitCount);
        S21InfoLogOutput.printlnv(ZZBM0009I, AMER_MSTR, DATA_INSERTED, this.insertCommitCount);
        S21InfoLogOutput.printlnv(ZZBM0009I, AMER_MSTR, DATA_UPDATED, this.updateCommitCount);
        S21InfoLogOutput.printlnv(ZZBM0009I, AMER_MSTR, DATA_LOGICAL_REMOVED, this.logicalRemoveCommitCount);

    }
    
// QC1906
    private String getGtinFromUpc(String upcCode){
        if( ZYPCommonFunc.hasValue(upcCode)){
            return "00" + upcCode;
        }
        return "";
    }
    
    private String getScc14NumFromUpc(String packageLevelNum, String upcCode){
        if(ZYPCommonFunc.hasValue(upcCode)){
            String upcOrScc14Num = packageLevelNum + "0" + upcCode;
            upcOrScc14Num = calcSCC14CheckDigit(upcOrScc14Num);
            return upcOrScc14Num;
        }
        return "";
    }

    private String calcSCC14CheckDigit(String upcOrScc14Num) {

        if (!ZYPCommonFunc.hasValue(upcOrScc14Num) || upcOrScc14Num.length() < 14) {
            return upcOrScc14Num;
        }

        String scc14 = upcOrScc14Num.substring(0, 13);

        int[] oddNum = new int[7];
        int[] evenNum = new int[6];
        int oddNumIdx = 0;
        int evenNumIdx = 0;
        char[] c = scc14.toCharArray();
        for (int i = 1; i <= c.length; i++) {
            if (i % 2 != 0) {
                oddNum[oddNumIdx++] = Integer.parseInt(Character.toString(c[i - 1]));
            } else {
                evenNum[evenNumIdx++] = Integer.parseInt(Character.toString(c[i - 1]));
            }
        }

        int oddSum = sum(oddNum);
        int evenSum = sum(evenNum);

// QC1906
//        BigDecimal num = new BigDecimal("10").subtract(new BigDecimal(3 * oddSum + evenSum).divide(new BigDecimal("10"), 1, RoundingMode.DOWN));
//        String numStr = num.toString();
//        if (numStr.indexOf('.') != -1) {
//            numStr = numStr.substring(numStr.indexOf('.') + 1);
//        } else {
//            numStr = "0";
//        }
        int num = (10 - ((oddSum * 3 + evenSum) % 10)) % 10;
        String numStr = Integer.toString(num);

        return scc14 + numStr;
    }
    
    private int sum(int[] val) {
        int sum = 0;
        for (int v : val) {
            sum += v;
        }
        return sum;
    }
    
    private String getPackageLevelNumber(String pkgUomCd){
        if(ZYPCommonFunc.hasValue(pkgUomCd)){
            PKG_UOMTMsg tMsg = new PKG_UOMTMsg();
            tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            tMsg.pkgUomCd.setValue(pkgUomCd);
            tMsg = (PKG_UOMTMsg)EZDTBLAccessor.findByKey(tMsg);
            if( tMsg != null ){
                return tMsg.pkgUomPkgLvlNum.getValue();
            }
        }
        return "";
    }

    /**
     * Terminate process
     */
    protected void termRoutine() {

        // termineted code set
        setTermState(this.termCd);

    }

}
