/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NMA.NMAB022001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NMAI1415_01TMsg;
import business.db.NMAI1415_01TMsgArray;
import business.db.AMER_CMPSNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 *  scheduled batch
 *  NMAB022001 Interface legacy data info S21(Composition)
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/02/21   SRA             G.Fu            Create          N/A
 * 2013/10/23   Fujitsu         A.Wada          Update          Def#349
 * 2019/02/26   Fujitsu         S.Kosaka        Update          QC#30533
 *</pre>
 */
public class NMAB022001 extends S21BatchMain {
    
    /** message of processed record's count */
    private static final String ZZBM0009I = "ZZBM0009I";

    /** error message (Global Company Code error)  */
    private static final String GLBL_CMPY_CD_ERR = "NMAM0001E";

    /** error message (DB access error)  */
    private static final String DBACCESS_ERR = "NMAM8003E";

    /** error message (EXEC_STS error)  */
    private static final String EXECSTS_ERR = "NMAM8006E";

    /** warning message (registration record  exist)  */
    private static final String INS_REC_EXIST = "NMAM0253W";

    /** warning message (update record doesn't exist)  */
    private static final String UPD_REC_NOEXIST = "NMAM0120W";

    /** warning message (deletion record doesn't exist)  */
    private static final String DEL_REC_NOEXIST = "NMAM0121W";

    /** Data Finded */
    private static final String DATA_FINDED = "find";

    /** Data Created */
    private static final String DATA_CREATED = "create";

    /** Data Updated */
    private static final String DATA_UPDATED = "update";

    /** Data Logical Removed */
    private static final String DATA_LOGICAL_REMOVED = "logicalRemove";

    /** DB Table : INTERFACE_TRANSACTION */
    private static final String INTERFACE_TRANSACTION = "INTERFACE_TRANSACTION";

    /** DB Table : NMAI1415_01 */
    private static final String NMAI1415_01 = "NMAI1415_01";

    /** DB Table : AMER_CMPSN */
    private static final String AMER_CMPSN = "AMER_CMPSN";

    /** EXEC_STS(Create) */
    private static final String EXECSTS_INS = "I";
    /** EXEC_STS(UPdate) */
    private static final String EXECSTS_UP  = "U";
    /** EXEC_STS(Delete) */
    private static final String EXECSTS_DEL = "D";

    /** AMER_CMPSN.DELETE_FLAG default */
    private static final String DEFAULT_FLAG = "N";


    /** interface_transaction find count */
    private int tranIdFindCount = 0;
    /** amai1415_01 find count */
    private int ifFindCount = 0;
    /** create count */
    private int createCount = 0;
    /** update count */
    private int updateCount = 0;
    /** logical remove count */
    private int logicalRemoveCount = 0;

    /** interface_transaction find commit count */
    private int tranIdFindCommitCount = 0;
    /** amai1415_01 find commit count */
    private int ifFindCommitCount = 0;
    /** create commit count */
    private int createCommitCount = 0;
    /** update commit count */
    private int updateCommitCount = 0;
    /** logical remove commit count */
    private int logicalRemoveCommitCount = 0;

//  QC1880
    private static final String MAIL_FROM_ADDR_GRP          = "System common";
    private static final String MAIL_FROM_ADDR_GRP_ID       = "FROM0002";
    // START 2013/10/23 A.Wada [Def#349, MOD]
    //private static final String MAIL_FROM_ADDR_KEY          = "AM";
    private static final String MAIL_FROM_ADDR_KEY          = "NM";
    // END   2013/10/23 A.Wada [Def#349, MOD]
    private static final String MAIL_ADDR_NONE_ERR          = "NMAM8028E";
    private static final String MAIL_ADDR_VALID_ERR             = "NMAM8029E";
    private static final String MAIL_ADDR_TO_GRP                = "NMAR0010";
    private static final String MAIL_TEMP_ID                    = "NMAR0010M001";

    
    /** termineted code */
    private TERM_CD termCd; 

    /** Interface ID */
    private String interfaceId = null;    
    
    /**
     * Main method  
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NMAB022001().executeBatch(NMAB022001.class.getSimpleName());

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

        // get InterfaceId(NMAI1415)
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
                doProcessEvryTransactionId(interfaceId, transactionId);
                s21tta.endIntegrationProcess(interfaceId, transactionId);
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

        // select interface table
        NMAI1415_01TMsg searchMsg = new NMAI1415_01TMsg();
        searchMsg.setSQLID("001");
        searchMsg.setConditionValue("interfaceId01", interfaceId);
        searchMsg.setConditionValue("transactionId01", transactionId);
        NMAI1415_01TMsgArray ifMsgArray = (NMAI1415_01TMsgArray) EZDTBLAccessor.findByConditionForUpdate(searchMsg);

        for (int idx = 0; idx < ifMsgArray.length(); idx++) {

            this.ifFindCount++;

            NMAI1415_01TMsg ifRec = ifMsgArray.no(idx);
            copyNMAR0030(ifRec);

        }

    }

    /**
     * application process
     */
    private void copyNMAR0030(final NMAI1415_01TMsg ifRec) {

        AMER_CMPSNTMsg searchRec = new AMER_CMPSNTMsg();
        AMER_CMPSNTMsg upRec = null;
        // 20120223 modify because WMB Sequential is fixed
//// Defect 1125. Ignore the "D" record for NOT Sequential I/F by WMB, Start. 2011/01/19
//        if ( EXECSTS_DEL.equals(ifRec.execStsCdIf.getValue()) ) {
//            return;
//        }
//// Defect 1125. Ignore the "D" record for NOT Sequential I/F by WMB, End. 2011/01/19
        
        String execSts = ifRec.execStsCdIf.getValue();
        String rqstNo  = ifRec.requestNbrIf.getValue().toString();
        String cmpsnNo  = ifRec.compositionNbrIf.getValue().toString();
        String cmpsnSfxNo  = ifRec.compositionSfx1If.getValue().toString();
        String xtrCmpsnSfxNo  = ifRec.compositionSfx2If.getValue().toString();

        // select AMER_CMPSN
        ZYPEZDItemValueSetter.setValue(searchRec.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(searchRec.amerRqstNum, ifRec.requestNbrIf);
        ZYPEZDItemValueSetter.setValue(searchRec.amerCmpsnNum, ifRec.compositionNbrIf);
        ZYPEZDItemValueSetter.setValue(searchRec.amerCmpsnSfxNum, ifRec.compositionSfx1If);
        ZYPEZDItemValueSetter.setValue(searchRec.amerXtrCmpsnSfxNum, ifRec.compositionSfx2If);
        upRec = (AMER_CMPSNTMsg) EZDTBLAccessor.findByKeyForUpdate(searchRec);

        if (upRec == null) {

            // EXECSTS (I or U) 
            if (EXECSTS_INS.equals(execSts) || EXECSTS_UP.equals(execSts)) {

                copyMsg(ifRec, searchRec);
                create(searchRec);

                if (EXECSTS_UP.equals(execSts)) {

                    S21InfoLogOutput.printlnv(UPD_REC_NOEXIST, "REQ_NO:" + rqstNo + " CMPSN_NO:" + cmpsnNo + " SFX_NO1:" + cmpsnSfxNo + " SFX_NO2:" + xtrCmpsnSfxNo);
                }

            // EXECSTS (D) 
            } else if (EXECSTS_DEL.equals(execSts)) {

                S21InfoLogOutput.printlnv(DEL_REC_NOEXIST, "REQ_NO:" + rqstNo + " CMPSN_NO:" + cmpsnNo + " SFX_NO1:" + cmpsnSfxNo + " SFX_NO2:" + xtrCmpsnSfxNo);

            } else {

                throw new S21AbendException(EXECSTS_ERR, new String[] {"EXEC_STS_CD:" + execSts});

            }

        } else {

                // EXECSTS (I or U) 
                if (EXECSTS_INS.equals(execSts) || EXECSTS_UP.equals(execSts)) {
                    copyMsg(ifRec, upRec);
                    update(upRec);             
                    if (EXECSTS_INS.equals(execSts)) {

                        S21InfoLogOutput.printlnv(INS_REC_EXIST, "REQ_NO:" + rqstNo + " CMPSN_NO:" + cmpsnNo + " SFX_NO1:" + cmpsnSfxNo + " SFX_NO2:" + xtrCmpsnSfxNo);
                    }

                // EXECSTS (D) 
                } else if (EXECSTS_DEL.equals(execSts)) {

                    logicalRemove(upRec);

                } else {

                    throw new S21AbendException(EXECSTS_ERR, new String[] {"EXEC_STS_CD:" + execSts});

            }

        }

    }

    /**
     * application process
     */
    private void copyMsg(NMAI1415_01TMsg ifRec, AMER_CMPSNTMsg upRec) {

        ZYPEZDItemValueSetter.setValue(upRec.amerRqstNum, ifRec.requestNbrIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerCmpsnNum, ifRec.compositionNbrIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerCmpsnSfxNum, ifRec.compositionSfx1If);
        ZYPEZDItemValueSetter.setValue(upRec.amerXtrCmpsnSfxNum, ifRec.compositionSfx2If);
        ZYPEZDItemValueSetter.setValue(upRec.amerMdseCd, ifRec.merchandiseCodeL12If);
        ZYPEZDItemValueSetter.setValue(upRec.amerCmpsnItemCd, ifRec.compositionItemCodeIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerCmpsnItemNm, ifRec.compositionItemDescIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerCmpsnItemTpCd, ifRec.compositionItemTypeIf);
        ZYPEZDItemValueSetter.setValue(upRec.amerCmpsnItemQty, ifRec.nbrOfCompositionItemsIf);
        if (!ZYPCommonFunc.hasValue(ifRec.deleteFlagIf.getValue())) {
            ZYPEZDItemValueSetter.setValue(upRec.amerDelFlg, DEFAULT_FLAG);
        } else {
            ZYPEZDItemValueSetter.setValue(upRec.amerDelFlg, ifRec.deleteFlagIf);
        }

    }

    /**
     * application process
     */
    private void create(AMER_CMPSNTMsg upRec) {

        upRec.glblCmpyCd.setValue(getGlobalCompanyCode());

        EZDTBLAccessor.create(upRec);
        
        // does not need to check the duplicate error.
        // because Mercury I/F send the three same date at exactly same time.
        // so 1 of 3 data is success to update but 
        // 2 of 3 data might be fail because of duplication.
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upRec.getReturnCode())) {
//            EZDDebugOutput.println(1, "SQL Error:" + upRec.getReturnCode() , this);
//            throw new S21AbendException(DBACCESS_ERR, new String[] {AMER_CMPSN, DATA_CREATED});
//        }
        
// QC1880
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upRec.getReturnCode())) {
            S21InfoLogOutput.println("Environment DB Access Error >> insert. " + upRec.toString() );
            handleMQInboundDuplicateError(NMAI1415_01, upRec);  
        }
        
        this.createCount++;

    }

    /**
     * application process
     */
    private void update(AMER_CMPSNTMsg upRec) {

        EZDTBLAccessor.update(upRec);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upRec.getReturnCode())) {
            EZDDebugOutput.println(1, "SQL Error:" + upRec.getReturnCode() , this);
            throw new S21AbendException(DBACCESS_ERR, new String[] {AMER_CMPSN, DATA_UPDATED});
        }

        this.updateCount++;

    }

    /**
     * application process
     */
   private void logicalRemove(AMER_CMPSNTMsg upRec) {

        EZDTBLAccessor.logicalRemove(upRec);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upRec.getReturnCode())) {
            EZDDebugOutput.println(1, "SQL Error:" + upRec.getReturnCode() , this);
            throw new S21AbendException(DBACCESS_ERR, new String[] {AMER_CMPSN, DATA_LOGICAL_REMOVED});
        }

        this.logicalRemoveCount++;

    }

   /**
    * application process
    */
   private void setCommitCount() {

       this.tranIdFindCommitCount = this.tranIdFindCount;
       this.ifFindCommitCount = this.ifFindCount;
       this.createCommitCount = this.createCount;
       this.updateCommitCount = this.updateCount;
       this.logicalRemoveCommitCount = this.logicalRemoveCount;

   }

   /**
    * application process
    */
   private void outputProcessCnt() {

       // processed record's count output
       S21InfoLogOutput.printlnv(ZZBM0009I, INTERFACE_TRANSACTION, DATA_FINDED, this.tranIdFindCommitCount);
       S21InfoLogOutput.printlnv(ZZBM0009I, NMAI1415_01, DATA_FINDED, this.ifFindCommitCount);
       S21InfoLogOutput.printlnv(ZZBM0009I, AMER_CMPSN, DATA_CREATED, this.createCommitCount);
       S21InfoLogOutput.printlnv(ZZBM0009I, AMER_CMPSN, DATA_UPDATED, this.updateCommitCount);
       S21InfoLogOutput.printlnv(ZZBM0009I, AMER_CMPSN, DATA_LOGICAL_REMOVED, this.logicalRemoveCommitCount);

   }
   

// QC1880
    private void handleMQInboundDuplicateError(String ifId, EZDTMsg tMsg) {

        List<S21MailAddress> addrToList = getMailAddressToGroup();
        if( addrToList.size() > 0 ){
            S21MailTemplate mailTemplate = createMailTemplate(ifId, tMsg);
            S21MailAddress addrFrom = getMailAddressFrom();

            S21Mail mail = new S21Mail(this.getGlobalCompanyCode());
            mail.setFromAddress(addrFrom);
            mail.setToAddressList(addrToList);
            mail.setMailTemplate(mailTemplate);

            // 2019/02/26 QC#30533 Mod Start
//            String mailEventID = mail.sendMail();
//
//            if( !ZYPCommonFunc.hasValue(mailEventID)){
//                throw new S21AbendException("NMAM0166E");
//            }
            mail.postMail();
            // 2019/02/26 QC#30533 Mod End
        }
    }

    private S21MailAddress getMailAddressFrom() {
        S21MailGroup groupFrom = new S21MailGroup(this.getGlobalCompanyCode(), MAIL_FROM_ADDR_GRP_ID);
        groupFrom.setMailKey1(MAIL_FROM_ADDR_KEY);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if( addrFromList.isEmpty() ){
            throw new S21AbendException(MAIL_ADDR_NONE_ERR, new String[] {MAIL_FROM_ADDR_GRP, MAIL_FROM_ADDR_GRP_ID, MAIL_FROM_ADDR_KEY });
        }
        if (!ZYPCommonFunc.hasValue(addrFromList.get(0).getAddress())) {
            throw new S21AbendException(MAIL_ADDR_VALID_ERR, new String[] {MAIL_FROM_ADDR_GRP, MAIL_FROM_ADDR_GRP_ID, MAIL_FROM_ADDR_KEY });
        }

        return addrFromList.get(0);
    }

    private List<S21MailAddress> getMailAddressToGroup() {
        S21MailGroup groupTo = new S21MailGroup(this.getGlobalCompanyCode(), MAIL_ADDR_TO_GRP);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        List<S21MailAddress> addrToListChecked = new ArrayList<S21MailAddress>();

        if (addrToList.size() == 0) {
            S21InfoLogOutput.println(MAIL_ADDR_NONE_ERR, new String[] {MAIL_ADDR_TO_GRP, "", "" });
            return new ArrayList<S21MailAddress>();
        }

        int cnt = 0;
        for (int i = 0; i < addrToList.size(); i++) {
            if (ZYPCommonFunc.hasValue(addrToList.get(i).getAddress())) {
                addrToListChecked.add(addrToList.get(i));
                cnt++;
            }
        }

        // valid mail address (TO) doesn't exist
        if (cnt == 0) {
            S21InfoLogOutput.println(MAIL_ADDR_VALID_ERR, new String[] {MAIL_ADDR_TO_GRP, "", "" });
            return new ArrayList<S21MailAddress>();
        }

        return addrToListChecked;
    }

    private S21MailTemplate createMailTemplate(String ifId, EZDTMsg tMsg){
        S21MailTemplate mailTemplate = new S21MailTemplate(this.getGlobalCompanyCode(), MAIL_TEMP_ID);

        mailTemplate.setTemplateParameter("ifId", ifId);
        mailTemplate.setTemplateParameter("dataDetail", tMsg.toString());
        mailTemplate.setTemplateParameter("date", ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm"));

        return mailTemplate;
    }

    /**
     * Terminate process
     */
    protected void termRoutine() {

        // termineted code set
        setTermState(this.termCd);

    }

}
