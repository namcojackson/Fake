package com.canon.cusa.s21.batch.NFA.NFAB040001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.db.NFAI0030_01TMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch stores journal entry in S21 GL Interface table.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/11/2009   CSA             H.Naoi          Create          N/A
 * 07/29/2013   CUSA            T.Tanaka        Update          Def# ARCS
 * 10/23/2013   Fujitsu         A.Wada          Update          Def# 349
 * 02/23/2016   CSAI            K.Uramori       Update          Modification for CSA
 * 07/03/2017   CITS            Y.Iwasaki       Update          QC#19545
 * 07/07/2017   CITS            K.Ogino         Update          QC#19799
 * 2017/10/12   Fujitsu         H.Ikeda         Update          QC#21413
 * 2019/02/28   Fujitsu         S.Ohki          Update          QC#30584
 * 2019/03/22   Fujitsu         T.Ogura         Update          QC#30565
 * </pre>
 */
public class NFAB040001 extends S21BatchMain implements NFAB040001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** interface ID */
    private String interfaceId = null;

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** transaction ID */
    private BigDecimal transactionId = null;

    /** Commit Count */
    private int commitCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Array of TMsg */
    private EZDTMsg[] glMsgs;

    /** Size of Array */
    private int glMsgCount = 0;
    
    /** Global Company Code */
    private String glblCmpyCd;
    
    /** Process Date */
    private static String procDt = "";

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    // START 2017/10/12 H.ikeda [QC#21413, DEL]
    // /** MAN_JRNL_ANTRY_PK list for update*/
    // private Set<BigDecimal> pkList = new HashSet<BigDecimal> ();
    // END   2017/10/12 H.ikeda [QC#21413, DEL]

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        //  Get parameter (PROC_DT)
        procDt = S21BatchUtil.getInputParam1();
        
        new NFAB040001().executeBatch(NFAB040001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        interfaceId = getInterfaceID();
        
        if (interfaceId == null) {
            throw new S21AbendException(NFAM0039E);
        }

        this.termCd = TERM_CD.NORMAL_END;

        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // initialize
        glMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        if (procDt == null || procDt.equals(BLANK)) {
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }
        
        S21InfoLogOutput.println("initRoutine Method End");

    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        Boolean checkEntryToAjeGlIntfcProcess = doEntryToAjeGlIntfc();

        if (checkEntryToAjeGlIntfcProcess != Boolean.TRUE) {
            //S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM1, errMsg});
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM1, errMsg});
        } else {

            Boolean checkAjeIntfcCtrlUpdated = updateJrnlEntry();
            if (checkAjeIntfcCtrlUpdated != Boolean.TRUE) {
                //S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM2, errMsg});
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM1, errMsg});
            }

            // START 2017/10/12 H.ikeda [QC#21413, DEL]
//            //---- start 2016/08/01 Update MAN_JRNL_ENTRY
//            if (!updateManJrnlEntry()) {
//                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM1, errMsg});
//            }
//            //---- end 2016/08/01
            // END   2017/10/12 H.ikeda [QC#21413, DEL]

            // if above process is completed without error, commit at once.
            commit();
        }

        // check AJE Error and if error exsists, send notification email.
        checkAJEError();
        
        S21InfoLogOutput.println("mainRoutine Method End");

    }

    /**
     * <pre>
     *  Get Journal Entry List and Store them into AJE GL Interface.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private Boolean doEntryToAjeGlIntfc() {

        // ** Get Journal Entry List and Store them into AJE GL Interface. **
        Map<String, String>queryParamToGetJrnlEntry = new HashMap<String, String>();
        queryParamToGetJrnlEntry.put("glblCmpyCd", this.profile.getGlobalCompanyCode());
        queryParamToGetJrnlEntry.put("glSendCpltFlg", FLG_OFF_N);
        //---- start del 2016/02/23
        //queryParamToGetJrnlEntry.put("billToReqAcct1", BILL_TO_REQ_ACCT_CD1);
        //queryParamToGetJrnlEntry.put("billToReqAcct2", BILL_TO_REQ_ACCT_CD2);
        //---- end 2016/02/23
        
        Boolean checkEntryToAjeGlIntfc = (Boolean) ssmBatchClient.queryObject("getJrnlEntry", queryParamToGetJrnlEntry, new AjeGlIntfcEntryHandler());
        return checkEntryToAjeGlIntfc;
    }

    // START 2017/10/12 H.ikeda [QC#21413, DEL]
//    /**
//     * <pre>
//     *  Get Journal Entry List and Store them into AJE GL Interface.
//     * </pre>
//     */
//    @SuppressWarnings("unchecked")
//    //---- start add 2016/08/01
//    private Boolean updateManJrnlEntry() {
//
//        if (pkList.size() > 0) {
//            
//            for (BigDecimal pk : pkList) {
//                
//                MAN_JRNL_ENTRY_HDRTMsg tmsg = new MAN_JRNL_ENTRY_HDRTMsg();
//                
//                setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
//                setValue(tmsg.manJrnlEntryHdrPk, pk);
//                
//                tmsg = (MAN_JRNL_ENTRY_HDRTMsg) S21FastTBLAccessor.findByKey(tmsg);
//                
//                if (tmsg == null) {
//                    errMsg = "Unable to update Manual Journal Entry:" + tmsg.manJrnlEntryHdrPk.getValueInt();
//                    return false;
//                }
//            
//                setValue(tmsg.glSendCpltFlg, ZYPConstant.FLG_ON_Y);
//                
//                S21FastTBLAccessor.update(tmsg);
//            }
//            
//        }
//        return true;
//    }
    //---- end 2016/08/01
    // END 2017/10/12 H.ikeda [QC#21413, DEL]

    /**
     * <pre>
     *  Get Journal Entry List and Store them into AJE GL Interface.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private Boolean updateJrnlEntry() {

        // condition
        JRNL_ENTRYTMsg jrnlEntry = new JRNL_ENTRYTMsg();

        commonJrnlEntry.setFieldValue(jrnlEntry, "glblCmpyCd", this.profile.getGlobalCompanyCode());
        commonJrnlEntry.setFieldValue(jrnlEntry, "glSendCpltFlg", FLG_OFF_N);

        // value to be updated
        JRNL_ENTRYTMsg jrnlEntryUp = new JRNL_ENTRYTMsg();
        commonJrnlEntry.setFieldValue(jrnlEntryUp, "glSendCpltFlg", FLG_ON_Y);

        int retCnt = S21FastTBLAccessor.updateByPartialValue(jrnlEntry, new String[]{"glblCmpyCd", "glSendCpltFlg"},
                jrnlEntryUp, new String[]{"glSendCpltFlg"});

        if (retCnt == -1) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    /**
     * <pre>
     *  AJE GL Interface Entry Handler.
     *  Store journal entry into AJE GL Interface Table and update the result in AJE Interface Control Table. 
     * </pre>
     */
    private final class AjeGlIntfcEntryHandler extends S21SsmBooleanResultSetHandlerSupport {

        /** Process Date */
        private String procDt;
               
        protected Boolean doProcessQueryResult(ResultSet rsJrnlEntry) throws SQLException {
            try {
                // create transaction ID.
                S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();
                transactionId = s21TrxTblAccessor.getNextTransactionId();

                int segmentId = 1;
                int unitId = 1;
                int seqNum = 1;

                BigDecimal dealDrAmt;
                BigDecimal dealCrAmt;
                BigDecimal funcDrAmt;
                BigDecimal funcCrAmt;
                
                this.procDt = ZYPDateUtil.getBatProcDate();        
                
                while (rsJrnlEntry.next()) {

                    NFAI0030_01TMsg ajeGlIntfc = new NFAI0030_01TMsg();
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "interfaceId", interfaceId);
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "transactionId", transactionId);
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "segmentId", new BigDecimal(segmentId));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "unitId", new BigDecimal(unitId));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "seqNumber", new BigDecimal(seqNum));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraStatusL3If", ORA_STATUS_VAL);
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSobN10If", ORA_SOB_VAL);
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "dateAcctL8If", rsJrnlEntry.getString(GL_DT));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "dateCreateL8If", this.procDt);
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraCreateN10If", ORA_CREATE_VAL);
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraActualL1If", ORA_ACTL_CD_VAL);
                    // modified on 102309
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraNameSourceL25If", rsJrnlEntry.getString(JRNL_SRC_NM));
                    // modified on 102309
                    
                    // Add sys_src_cd at the end of jrnl_catg_nm
                    //String jrnlCatgNm = (rsJrnlEntry.getString(JRNL_CATG_NM) + ":" + rsJrnlEntry.getString(SYS_SRC_CD));
                    //if (jrnlCatgNm.length() > JRNL_CATG_NM_LEN) {
                    //    jrnlCatgNm = jrnlCatgNm.substring(0, JRNL_CATG_NM_LEN-1);
                    //}
                    //commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraNameCategoryL25If", jrnlCatgNm);
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraNameCategoryL25If", rsJrnlEntry.getString(JRNL_CATG_NM));
                    
                    ajeGlIntfc.oraDateCurrL8If.clear();
                    
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment1L3If", rsJrnlEntry.getString(COA_CMPY_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment2L3If", rsJrnlEntry.getString(COA_BR_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment3L6If", rsJrnlEntry.getString(COA_CC_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment4L8If", rsJrnlEntry.getString(COA_ACCT_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment5L2If", rsJrnlEntry.getString(COA_PROD_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment6L3If", rsJrnlEntry.getString(COA_CH_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment7L3If", rsJrnlEntry.getString(COA_AFFL_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment8L4If", rsJrnlEntry.getString(COA_PROJ_CD));
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraSegment9L3If", rsJrnlEntry.getString(COA_EXTN_CD));
                    //ajeGlIntfc.oraCodeCurrencyL3If.setValue(rsJrnlEntry.getString(ORCL_CCY_CD));       // It's set from Journal Entry.
                    // deal ccy code should be set 02/12/2010
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraCodeCurrencyL3If", rsJrnlEntry.getString(DEAL_CCY_CD));
                    
                    // if ccy is standard ccy code, currency type and rate no need to be set
                    if (rsJrnlEntry.getString(DEAL_CCY_CD).equals(rsJrnlEntry.getString(STD_CCY_CD))) {
                        ajeGlIntfc.oraTypeCurrL25If.clear();
                        ajeGlIntfc.amtCurrencyRateN14If.clear();
                    } else {
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraTypeCurrL25If", CCY_TYPE_VAL_USER);
                        BigDecimal exchRate = rsJrnlEntry.getBigDecimal(EXCH_RATE);
                        // if arthmetic type is "/", then rate should be recalculated.
                        exchRate = calRateByArthType(exchRate, rsJrnlEntry.getString(ACCT_ARTH_TP_CD));
                        exchRate = exchRate.setScale(5, BigDecimal.ROUND_HALF_UP);
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "amtCurrencyRateN14If", exchRate);
                    }                  
                    
                    dealDrAmt = rsJrnlEntry.getBigDecimal(JRNL_DEAL_DR_AMT);
                    dealCrAmt = rsJrnlEntry.getBigDecimal(JRNL_DEAL_CR_AMT);
                    
                    funcDrAmt = rsJrnlEntry.getBigDecimal(JRNL_FUNC_DR_AMT);
                    funcCrAmt = rsJrnlEntry.getBigDecimal(JRNL_FUNC_CR_AMT);
                    
                    // Rounded for 2 decimal places (temporary modified on 102309)
                    if (dealDrAmt != null) {
                        dealDrAmt = dealDrAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    if (dealCrAmt != null) {
                        dealCrAmt = dealCrAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    
                    if (funcDrAmt != null) {
                        funcDrAmt = funcDrAmt.setScale(2, BigDecimal.ROUND_HALF_UP);    
                    }
                    if (funcCrAmt != null) {
                        funcCrAmt = funcCrAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    
                    /*----- foreign currency ----*/
                    if (!rsJrnlEntry.getString(DEAL_CCY_CD).equals(rsJrnlEntry.getString(STD_CCY_CD))) {
                        //commonJrnlEntry.setFieldValue(ajeGlIntfc, "accountedDrN14If", funcDrAmt);
                        //commonJrnlEntry.setFieldValue(ajeGlIntfc, "accountedCrN14If", funcCrAmt);
                        
                        // func amount into accounted amount 20110302
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "accountedDrN14If", funcDrAmt);
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "accountedCrN14If", funcCrAmt);
                        
                        // deal amount into entered amount 20110302
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "enteredDrN14If", dealDrAmt);
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "enteredCrN14If", dealCrAmt);
                        
                    /*---- standard currency -----*/
                    } else {
                        // if it's standard ccy set entered null
                        ajeGlIntfc.accountedDrN14If.clear();
                        ajeGlIntfc.accountedCrN14If.clear();
                        
                        //commonJrnlEntry.setFieldValue(ajeGlIntfc, "enteredDrN14If", dealDrAmt);
                        //commonJrnlEntry.setFieldValue(ajeGlIntfc, "enteredCrN14If", dealCrAmt);
                        // in case VND return, deal amount is not set
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "enteredDrN14If", funcDrAmt);
                        commonJrnlEntry.setFieldValue(ajeGlIntfc, "enteredCrN14If", funcCrAmt);
                    }
                    // If the jrnl amount is very big and exceed size 14,2, it will raise digits error. (It happened because the size if different in JRNL_ENTRY and NFAI0030.)
                    
                    ajeGlIntfc.oraCcidL4If.clear();
                    ajeGlIntfc.oraTransDateL8If.clear();
                    
                    String ref = commonJrnlEntry.checkNull(rsJrnlEntry.getString(AJE_ID));
                    // START 2017/10/12 H.ikeda [QC#21413, DEL]
                    //+ " " + commonJrnlEntry.checkNull(rsJrnlEntry.getString("PSN_NUM"))
                    // END   2017/10/12 H.ikeda [QC#21413, DEL]

                    if (ref.length() > ORA_REF_LEN) {
                        // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                        ref = ref.substring(0, ORA_REF_LEN - 1);
                        ref = ref.substring(0, ORA_REF_LEN);
                        // END   2019/03/22 T.Ogura [QC#30565,MOD]
                    }
                    
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraReference10L20If", ref);
                    
                    // set bill_to_cust_cd
                    commonJrnlEntry.setFieldValue(ajeGlIntfc, "oraAttribute11L15If", rsJrnlEntry.getString(BILL_TO_CUST_CD));
                    
                    ajeGlIntfc.fillerL011If.clear();

                    // START 2017/10/12 H.ikeda [QC#21413, DEL]
                    //if (rsJrnlEntry.getBigDecimal("MAN_JRNL_ENTRY_HDR_PK") != null) {
                    //    pkList.add(rsJrnlEntry.getBigDecimal("MAN_JRNL_ENTRY_HDR_PK"));
                    //}
                    // END   2017/10/12 H.ikeda [QC#21413, DEL]

                    createAjeGlIntfcEntry(ajeGlIntfc);

                    unitId += 1;

                } // while (rsJrnlEntry.next())

                if (glMsgCount != 0) {
                    createAjeGlIntfcEntry(null);
                }

                // Create interface transaction record (Important required)
                if (commitCount > 0) {
                    //QC#19545
                    //Create Interface Transaction record only when process record exists.
                    s21TrxTblAccessor.createIntegrationRecordForBatch(interfaceId, transactionId);
                }
                
            } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException exSql) {
                errMsg = exSql.getMessage();
                return Boolean.FALSE;
            } catch (Exception exc) {  // to catch any other error
                errMsg = exc.getMessage();
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        }

        /**
         * @param EZDTMsg Journal Entry
         * @throws JrnlCommonException JrnlCommonException
         */
        private void createAjeGlIntfcEntry(EZDTMsg ajeGlIntfc) throws NFACommonJrnlEntry.JrnlCommonException {

            if (ajeGlIntfc != null) {
                glMsgs[glMsgCount] = ajeGlIntfc;
                glMsgCount += 1;

            } else {  // array may be not full
                glMsgs = commonJrnlEntry.changeArraySize(glMsgs, glMsgCount);
            }

            // per 10000 lines
            if (glMsgCount >= BULK_INSERT_COUNT || ajeGlIntfc == null) {

                int retCnt = S21FastTBLAccessor.insert(glMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != glMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += glMsgCount;
                // initialize
                glMsgCount = 0;
                glMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void checkAJEError() {
        
        // ** Get AJE Errors **
        Map<String, String>queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("jrnlCpltFlgN", FLG_OFF_N);

        Integer rtn = (Integer) ssmBatchClient.queryObject("getAJEErrorCount", queryParam);
                
        BigDecimal varMdse = checkInOutMDSEVariance();
        //---- start mod 2016/02/23 In out report is not created for parts.
        //BigDecimal varParts = checkInOutPartsVariance();
        BigDecimal varParts = BigDecimal.ZERO;
        //---- end 2016/02/23
        
        if (rtn.intValue() > 0 || commonJrnlEntry.checkNull(varMdse).compareTo(BigDecimal.ZERO) != 0
                || commonJrnlEntry.checkNull(varParts).compareTo(BigDecimal.ZERO) != 0) {
            // Error data is existing. Send notification email
            
            S21Mail mail = new S21Mail(this.glblCmpyCd);
            S21MailGroup grpFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_FROM_ID);

            // START 2013/10/23 A.Wada [Def#349, MOD]
            // grpFrom.setMailKey1("NFA");
            grpFrom.setMailKey1("NFA");
            // END   2013/10/23 A.Wada [Def#349, MOD]

            List<S21MailAddress> addrFromList = grpFrom.getMailAddress();
            
            S21MailAddress from = null;
            if (addrFromList.size() == 1) {
                from = addrFromList.get(0);
            } else {
                throw new S21AbendException("ZZMM0007E", new String[]{"From Address"});
            }
            mail.setFromAddress(from);
            
            S21MailGroup grpTo = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_TO_ID);
            List<S21MailAddress> addrToList = grpTo.getMailAddress();
            
            mail.setToAddressList(addrToList);
            
            S21MailTemplate tmplt = new S21MailTemplate(this.glblCmpyCd, MAIL_TMPLT_ID);
            
            tmplt.setTemplateParameter("numOfRec", rtn.intValue());
            
            List<Map> ssmRes = (List<Map>) ssmBatchClient.queryObjectList("getAJEErrors", queryParam);
            
            Set<String> setIntfcTp = new HashSet<String>();
            Set<String> setAjeId = new HashSet<String>();
            
            for (Map<String, Object> rsAJEError : ssmRes) {

                setIntfcTp.add((String) rsAJEError.get(AJE_INTFC_TP_CD));
                setAjeId.add((String) rsAJEError.get(SYS_SRC_CD) + "-" + (String) rsAJEError.get(TRX_CD) + "-" +
                        (String) rsAJEError.get(TRX_RSN_CD));

            }
            
            tmplt.setTemplateParameter("intfcTp", getString(setIntfcTp));
            tmplt.setTemplateParameter("ajeId", getString(setAjeId));
            
            tmplt.setTemplateParameter("amtMdse", varMdse);
            tmplt.setTemplateParameter("amtParts", varParts);
            
            mail.setMailTemplate(tmplt);
            
            // START 2019/02/28 S.Ohki [QC#30584, MOD]
//            String mailEventID = mail.sendMail();
            String mailEventID = mail.postMail();
            // END 2019/02/28 S.Ohki [QC#30584, MOD]
            EZDDebugOutput.println(600, mailEventID, this);
            
        }
        
    }
    
    private BigDecimal checkInOutMDSEVariance() {
        // ** Get AJE Errors **
        Map<String, String>queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("glDt", procDt);
        
        BigDecimal rtn = (BigDecimal) ssmBatchClient.queryObject("getMDSEVariance", queryParam);
        
        return rtn;
    }
    
    private BigDecimal checkInOutPartsVariance() {
        // ** Get AJE Errors **
        Map<String, String>queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("glDt", procDt);
        
        BigDecimal rtn = (BigDecimal) ssmBatchClient.queryObject("getPartsVariance", queryParam);
        
        return rtn;
    }
    
    private String getString(Set<String> set) {
        String rtn = "";
        String[] strArray = set.toArray(new String[0]);
        for (int i=0; i<strArray.length; i++) {
            if ("".equals(rtn)) {
                rtn = strArray[i];
            } else {
                rtn = rtn + ", " + strArray[i];
            }
        }
        
        return rtn;
    }
    
    /**
     * Calculate and Format Exchange Rate
     * @param echRate exchange rate
     * @param arthTp arithmetric type code
     * @return BigDecimal converted exchange rate
     */
    private BigDecimal calRateByArthType(BigDecimal exchRate, String arthTp) {
        if (exchRate == null) {
            return null;
        }
        
        // if arithmetic type of the Currency code is division, calculate exchange rate by 1 / Exchange Rate.
        if (arthTp.equals(SIMBOL_OF_DIV)
                && exchRate.compareTo(BigDecimal.valueOf(new Long(0))) != 0) {
            return BigDecimal.valueOf(new Long(1)).divide(exchRate, SCALE_VAL, BigDecimal.ROUND_HALF_UP);
        }
        return exchRate;
    }
    
    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");

    }

}
