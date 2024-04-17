package business.blap.ZZIL0020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDFMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTItem;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZIL0020.constant.ZZIL0020Constant;
import business.db.INTERFACE_MQCONFIGTMsg;
import business.db.INTERFACE_MQCONFIG_JNDITMsg;
import business.db.INTERFACE_RECEIVERTMsg;
import business.db.INTERFACE_SENDERTMsg;
import business.db.INTERFACE_SETTINGTMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0020BL06 extends S21BusinessHandler implements ZZIL0020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0020Scrn00_Upload".equals(screenAplID)) {
                doProcess_ZZIL0020Scrn00_Upload((ZZIL0020CMsg) cMsg, (ZZIL0020SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    /** input file */
    private EZDCSVInFile inCSVInFile = null;
    
    /** input file record */
    private EZDFMsg inCsvRec = null;

    /** error message storage list */
    private List<EZDSMsg> errMsgLs = Collections.synchronizedList( new ArrayList<EZDSMsg>() );

    /** error message storage size */
    private int MAX_MSG_SZ = 0;

    /** storage area for duplication check */
    private Set<String> keyIdLs = Collections.synchronizedSet( new HashSet<String>() );
    private Set<HashSet<String>> keyIdLsParts = Collections.synchronizedSet( new HashSet<HashSet<String>>() );
    
    /**
     * upload processing
     * @param cMsg ZZIL0020CMsg
     * @param cMsg ZZIL0020SMsg
     */
    private void doProcess_ZZIL0020Scrn00_Upload(ZZIL0020CMsg cMsg, ZZIL0020SMsg sMsg) {

        // error infomation Initialization
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        this.MAX_MSG_SZ = sMsg.X.length();
            
        // get parameter
        TBL selTbl = TBL.valueOf( cMsg.xxTblNm.getValue() );

        // get Temp Filepath
        String filePath = cMsg.xxFileData.getTempFilePath();
        if ( filePath.length() == 0 ) {
            cMsg.setMessageInfo("ZYEM0004E");
            return;
        }
        
    	//[ADD] FWDEF273 - C.Kim EXCEL Convert
    	String csvFilePath = ZYPExcelUtil.excelToCsvFile(filePath);
        //[ADD] FWDEF273 - C.Kim EXCEL Convert
        
        // get EZDFMsg
        this.inCsvRec = TBL.getEZDFMsg( selTbl );

        // get CSV data & check
        List<EZDTMsg> result = null;
        try {
            // CSV file opening for input
            //[MOD] FWDEF273 - C.Kim EXCEL Convert
            // this.inCSVInFile = new EZDCSVInFile(filePath, this.inCsvRec, EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK);
            this.inCSVInFile = new EZDCSVInFile(csvFilePath, this.inCsvRec, EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK);
            //[MOD] FWDEF273 - C.Kim EXCEL Convert
            result = this.getCsvData(selTbl);
        } finally {
            if ( this.inCSVInFile != null ) {
                this.inCSVInFile.close();
            }
            cMsg.xxFileData.deleteTempFile();
            //[ADD] FWDEF273 - C.Kim EXCEL Convert
            ZYPExcelUtil.deleteFile(csvFilePath);
            //[ADD] FWDEF273 - C.Kim EXCEL Convert
        }

        // error check
        if (!setErrInfo(cMsg, sMsg)) {
            return;
        }
        // upload file empty 
        if ( result.size() == 0 ) {
            cMsg.setMessageInfo("ZYEM0004E");
            return;
        }

        // storage business table
        regIfTbl(cMsg, result, selTbl);
    }


    /**
     * get the data of the CSV file
     * @param  selTbl        The table of the registration object
     * @return List<EZDTMsg> The data of the table of the origin of copy
     */
    private List<EZDTMsg> getCsvData(TBL selTbl) {

        BigDecimal rowNum = BigDecimal.ZERO;
        List<EZDTMsg> list = new ArrayList<EZDTMsg>();

        // CSV file reading for input (Header Part)
        if ( this.inCSVInFile.read() == FILE_END ) {
            return list;
        }
        rowNum = rowNum.add(BigDecimal.ONE);

        // CSV file reading for input (Data Part)
        int retCd = 0;
        EZDTMsg tMsg = null;
        while ( (retCd = this.inCSVInFile.read()) != FILE_END ) {
            rowNum = rowNum.add(BigDecimal.ONE);

            // csv file read & csv format check
            if (retCd != 0) {
                String msgArg = "";
                if ( retCd == 1000 ) {
                    msgArg = ERR_1000;
                } else if ( retCd > 1000 && retCd < 2000 ) {
                    msgArg = ERR_1999.replace( ERR_SRC, Integer.toString(retCd - 1000) );
                } else if ( retCd >= 2000 ) {
                    msgArg = ERR_2999.replace( ERR_SRC, Integer.toString(retCd - 2000) );
                }
                this.dspAddMessge(rowNum, "ZYEM0006E", msgArg);
                continue;
            }
            
            switch ( selTbl ) {
                case INTERFACE_SETTING:         tMsg = chkIfSetting(rowNum);      break;
                case INTERFACE_SENDER:          tMsg = chkIfSender(rowNum);       break;
                case INTERFACE_RECEIVER:        tMsg = chkIfReceiver(rowNum);     break;
                case INTERFACE_MQCONFIG:        tMsg = chkIfMqconfig(rowNum);     break;
                case INTERFACE_MQCONFIG_JNDI:   tMsg = chkIfMqconfigJndi(rowNum); break;
            }
            list.add(tMsg);
        }
        return list;
    }


    /**
     * error information setting
     * @param cMsg ZZIL0020CMsg
     * @param cMsg ZZIL0020SMsg
     * @return boolean
     */
    private boolean setErrInfo(ZZIL0020CMsg cMsg, ZZIL0020SMsg sMsg) {

        // error nothing
        if ( this.errMsgLs.size() == 0 ) {
            return true;
        }
        
        int idx = 0;
        
        // Copy List to EZDSMsg
        for (EZDSMsg msg : this.errMsgLs ) {
            if ( idx >= sMsg.X.length() ) {
                break;
            }
            EZDMsg.copy(msg, null, sMsg.X.no(idx), null);
            idx++;
        }
        sMsg.X.setValidCount(idx);
        
        // Copy EZDSMsg to EZDCMsg
        for (idx = 0; idx < sMsg.X.getValidCount(); idx++) {
            if ( idx >= cMsg.X.length() ) {
                break;
            }
            EZDMsg.copy(sMsg.X.no(idx), null, cMsg.X.no(idx), null);
        }
        cMsg.X.setValidCount(idx);
        
        // The setting of the value to the turning a page item
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.X.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.X.getValidCount());

        // information message
        cMsg.setMessageInfo("ZZZM9032I");

        return false;
    }
    
    
    /**
     *  A record registers CSV data with a interface table
     * @param result    List<EZDTMsg> The data of the table of the origin of copy
     * @param selTbl    TBL           The table of the registration object
     */
    private void regIfTbl(ZZIL0020CMsg cMsg, List<EZDTMsg> result, TBL selTbl) {
        
        EZDTMsg tMsg = TBL.getEZDTMsg( selTbl );
        
        // search
        tMsg.setSQLID("999");
        EZDTMsgArray array = (EZDTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(tMsg);
        tMsg.clear();
        
        // recode delete
        for ( int i = 0; i < array.getValidCount(); i++ ) {

            tMsg = (EZDTMsg)array.get(i);
            EZDTBLAccessor.logicalRemove(tMsg);
            if ( !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()) ) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] { tMsg.getReturnCode() });
                return;
            }
        }
        
        // recode insert
        for (EZDTMsg regMsg : result) {
            
            EZDTMsg.copy(regMsg, null, tMsg, null);
            EZDTBLAccessor.create(tMsg);
            if ( !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()) ) {
                cMsg.setMessageInfo("ZZZM9012E", new String[] { tMsg.getReturnCode() });
                return;
            }
        }
        
        // information message
        cMsg.setMessageInfo("ZZZM9003I", new String[] { "Upload" });
    }
    

    /**
     * check data of the INTERFACE_SETTING
     * @param rowNum    row number of csv file
     * @return EZDTMsg  INTERFACE_SETTING
     */
    private EZDTMsg chkIfSetting(BigDecimal rowNum) {
        
        boolean errFlg, errFlgKey = false;
        String drctnTp = null;
        String commTool = null;

        // Copy EZDFMsg to EZDTMsg
        INTERFACE_SETTINGTMsg tMsg = new INTERFACE_SETTINGTMsg();
        EZDMsg.copy(this.inCsvRec, "A", tMsg, "");
        
        // required check
        errFlgKey = chkReqiredItem(rowNum, tMsg.interfaceId, "INTERFACE_ID");
        errFlg    = chkReqiredItem(rowNum, tMsg.drctnTp,     "DRCTN_TP");
        if ( !errFlgKey && !errFlg ) {
            //this.chkDuplicateForParts(rowNum, tMsg.interfaceId, tMsg.drctnTp, "INTERFACE_ID, DRCTN_TP");//For PARTS only
            this.chkDuplicate(rowNum, tMsg.interfaceId,  "INTERFACE_ID");
        }
        
        // DRCTN_TP & COMM_TOOL combination check
        if ( !errFlg ) {
            
            drctnTp = tMsg.drctnTp.getValue();
            commTool = tMsg.commTool.getValue();
            if ( DRCTN_TP_INBOUND.equals( drctnTp ) ) {

                if (COMM_TOOL_FTP.equals(commTool)) {
                    chkReqiredItem(rowNum, tMsg.intfcSendId,    "INTFC_SEND_ID");
                    chkReqiredItem(rowNum, tMsg.mqConfigId,     "MQ_CONFIG_ID");
                } else if (COMM_TOOL_MQ.equals(commTool)) {
                    chkReqiredItem(rowNum, tMsg.intfcRcvId,     "INTFC_RCV_ID");
                    chkReqiredItem(rowNum, tMsg.mqConfigJndiId, "MQ_CONFIG_JNDI_ID");
                } else {
                    this.dspAddMessge(rowNum, "ZZZM9026E", "COMM_TOOL");
                }
            } else if ( DRCTN_TP_OUTBOUND.equals( drctnTp ) ) {

                if ( !COMM_TOOL_FTP.equals(commTool) && !COMM_TOOL_MQ.equals(commTool) ) {
                    this.dspAddMessge(rowNum, "ZZZM9026E", "COMM_TOOL");
                }
                chkReqiredItem(rowNum, tMsg.intfcSendId,    "INTFC_SEND_ID");
                chkReqiredItem(rowNum, tMsg.mqConfigId,     "MQ_CONFIG_ID");

            } else if ( DRCTN_TP_PROVIDER.equals( drctnTp ) ) {

                if ( !COMM_TOOL_MQ.equals(commTool) ) {
                    this.dspAddMessge(rowNum, "ZZZM9026E", "COMM_TOOL");
                }
                chkReqiredItem(rowNum, tMsg.mqConfigJndiId, "MQ_CONFIG_JNDI_ID");

            } else if ( DRCTN_TP_COMMON.equals( drctnTp ) ) {

                //if ( !COMM_TOOL_NONE.equals(commTool) ) {
                //    this.dspAddMessge(rowNum, "ZZZM9026E", "COMM_TOOL");
                //}
                //chkReqiredItem(rowNum, tMsg.mqConfigJndiId, "MQ_CONFIG_JNDI_ID");
            } else {
                this.dspAddMessge(rowNum, "ZZZM9026E", "DRCTN_TP");
            }
        }
        
        // id existence check
        S21SsmEZDResult ssmResult = null;

        // OUTBOUND existence check
        if ( tMsg.intfcSendId.getValue().length() > 0 ) {
            
            ssmResult = ZZIL0020Query.getInstance().getIntfcId("INTFC_SEND_ID", tMsg.intfcSendId.getValue());
            if ( ssmResult.getQueryResultCount() == 0) {
                this.dspAddMessge(rowNum, "ZZZM9029E");
            }
        }

        // INBOUND existence check
        if ( tMsg.intfcRcvId.getValue().length() > 0 ) {
            
            ssmResult = ZZIL0020Query.getInstance().getIntfcId("INTFC_RCV_ID", tMsg.intfcRcvId.getValue());
            if ( ssmResult.getQueryResultCount() == 0) {
                this.dspAddMessge(rowNum, "ZZZM9027E");
            }
        }
        
        // OUTBOUND existence check
        if ( tMsg.mqConfigId.getValue().length() > 0 ) {
            
            ssmResult = ZZIL0020Query.getInstance().getIntfcId("MQ_CONFIG_ID", tMsg.mqConfigId.getValue());
            if ( ssmResult.getQueryResultCount() == 0) {
                this.dspAddMessge(rowNum, "ZZZM9030E");
            }
        }
        
        // INBOUND & COMMON & PROVIDER existence check
        if ( tMsg.mqConfigJndiId.getValue().length() > 0 ) {

            ssmResult = ZZIL0020Query.getInstance().getIntfcId("MQ_CONFIG_JNDI_ID", tMsg.mqConfigJndiId.getValue());
            if ( ssmResult.getQueryResultCount() == 0) {
                this.dspAddMessge(rowNum, "ZZZM9028E");
            }
        }
        
        return tMsg;
    }
    

    /**
     * check data of the INTERFACE_SENDER
     * @param rowNum    row number of csv file
     * @return EZDTMsg  INTERFACE_SENDER
     */
    private EZDTMsg chkIfSender(BigDecimal rowNum) {
        
        boolean errFlg = false;
        String clsPath = null;
        int timeOut = 0;

        // Copy EZDFMsg to EZDTMsg
        INTERFACE_SENDERTMsg tMsg = new INTERFACE_SENDERTMsg();
        EZDMsg.copy(this.inCsvRec, "B", tMsg, "");

        // required check
        errFlg = chkReqiredItem(rowNum, tMsg.intfcSendId,  "INTFC_SEND_ID");
        if ( !errFlg ) {
            this.chkDuplicate(rowNum, tMsg.intfcSendId,  "INTFC_SEND_ID");
        }
        
        // message send classpath check
        errFlg = chkReqiredItem(rowNum, tMsg.msgSendCls,   "MSG_SEND_CLS");
        if ( !errFlg ) {
            clsPath = tMsg.msgSendCls.getValue();
            if ( !MSG_SEND_CLS_2WAY.equals(clsPath) && !MSG_SEND_CLS_1WAY.equals(clsPath) && !MSG_SEND_CLS_TEST.equals(clsPath) ) {
                this.dspAddMessge(rowNum, "ZZZM9026E", "MSG_SEND_CLS");
            }
        }
        
        // timeout check
        errFlg = chkReqiredItem(rowNum, tMsg.msgTimeout, "MSG_TIMEOUT");
        if ( !errFlg ) {
            timeOut = tMsg.msgTimeout.getValueInt();
            if ( timeOut <= 0 ) {
                this.dspAddMessge(rowNum, "ZZZM9026E", "MSG_TIMEOUT");
            }
        }
        
        return tMsg;
    }
    

    /**
     * check data of the INTERFACE_RECEIVER
     * @param rowNum    row number of csv file
     * @return EZDTMsg  INTERFACE_RECEIVER
     */
    private EZDTMsg chkIfReceiver(BigDecimal rowNum) {
        
        // Copy EZDFMsg to EZDTMsg
        INTERFACE_RECEIVERTMsg tMsg = new INTERFACE_RECEIVERTMsg();
        EZDMsg.copy(this.inCsvRec, "C", tMsg, "");

        // required check
        boolean errFlg = chkReqiredItem(rowNum, tMsg.intfcRcvId, "INTFC_RCV_ID");
        if ( !errFlg ) {
            this.chkDuplicate(rowNum, tMsg.intfcRcvId, "INTFC_RCV_ID");
        }
        
        chkReqiredItem(rowNum, tMsg.ezBusinessID,     "EZBUSINESSID");
        chkReqiredItem(rowNum, tMsg.ezFunctionCode,   "EZFUNCTIONCODE");
        chkReqiredItem(rowNum, tMsg.ezUserID,     "EZUSERID");
        chkReqiredItem(rowNum, tMsg.ezPassword,   "EZPASSWORD");
        chkReqiredItem(rowNum, tMsg.secConfigNm,  "SEC_CONFIG_NM");

        // password encode
        tMsg.ezPassword.setValue(Integutil.encodeString(tMsg.ezPassword.getValue()));

        return tMsg;
    }
    
    /**
     * check data of the INTERFACE_MQCONFIG
     * @param rowNum    row number of csv file
     * @return EZDTMsg  INTERFACE_MQCONFIG
     */
    private EZDTMsg chkIfMqconfig(BigDecimal rowNum) {
        
        boolean errFlg = false;
        String transpTp = null;

        // Copy EZDFMsg to EZDTMsg
        INTERFACE_MQCONFIGTMsg tMsg = new INTERFACE_MQCONFIGTMsg();
        EZDMsg.copy(this.inCsvRec, "D", tMsg, "");
        
        // required check
        errFlg = chkReqiredItem(rowNum, tMsg.mqConfigId, "MQ_CONFIG_ID");
        if ( !errFlg ) {
            this.chkDuplicate(rowNum, tMsg.mqConfigId, "MQ_CONFIG_ID");
        }

        // transport type check
        errFlg = chkReqiredItem(rowNum, tMsg.trnspTp,   "TRNSP_TP");
        if ( !errFlg ) {
            transpTp = tMsg.trnspTp.getValue();
            if ( TRNSP_TP_CLIENT.equalsIgnoreCase(transpTp) ) {
                chkReqiredItem(rowNum, tMsg.hostNm,     "HOST_NM");
                chkReqiredItem(rowNum, tMsg.chNm,       "CH_NM");
                chkReqiredItem(rowNum, tMsg.portNum,    "PORT_NUM");
                
            } else if ( TRNSP_TP_CHN_TBL.equalsIgnoreCase(transpTp) ) {
                // nothing
            } else if ( TRNSP_TP_BINDING.equalsIgnoreCase(transpTp) ) {
                // nothing
            } else {
                this.dspAddMessge(rowNum, "ZZZM9026E", "TRNSP_TP");
            }
        }
        
        chkReqiredItem(rowNum, tMsg.queueMgr,       "QUEUE_MGR");
        //chkReqiredItem(rowNum, tMsg.queueNm,        "QUEUE_NM");
        //chkReqiredItem(rowNum, tMsg.replyQueueNm,   "REPLY_QUEUE_NM");
        //chkReqiredItem(rowNum, tMsg.failQueueNm,    "FAIL_QUEUE_NM");

        // password encode
        tMsg.queueConnectPassword.setValue(Integutil.encodeString(tMsg.queueConnectPassword.getValue()));

        return tMsg;
    }
    
    
    /**
     * check data of the INTERFACE_MQCONFIG_JNDI
     * @param rowNum    row number of csv file
     * @return EZDTMsg  INTERFACE_MQCONFIG_JNDITMsg
     */
    private EZDTMsg chkIfMqconfigJndi(BigDecimal rowNum) {

        // Copy EZDFMsg to EZDTMsg
        INTERFACE_MQCONFIG_JNDITMsg tMsg = new INTERFACE_MQCONFIG_JNDITMsg();
        EZDMsg.copy(this.inCsvRec, "E", tMsg, "");
            
        // required check
        boolean errFlg = chkReqiredItem(rowNum, tMsg.mqConfigJndiId, "MQ_CONFIG_JNDI_ID");
        if ( !errFlg ) {
            this.chkDuplicate(rowNum, tMsg.mqConfigJndiId, "MQ_CONFIG_JNDI_ID");
        }
        
        chkReqiredItem(rowNum, tMsg.queueFactoryJndiNm, "QUEUE_FACTORY_JNDI_NM");

        return tMsg;
    }

    /**
     * perform a blank check for a required item
     * @param rowNum   csv data row number
     * @param tItem    check item
     * @param msgArg   message argument
     * @return boolean
     */
    private boolean chkReqiredItem(BigDecimal rowNum, EZDTItem tItem, String msgArg) {
        
        boolean chkFlg = false;
        String tval = null;

        if ( tItem instanceof EZDTStringItem ) {
            tval = ( (EZDTStringItem) tItem ).getValue();
        } else if (tItem instanceof EZDTBigDecimalItem) {
            BigDecimal cDec = ((EZDTBigDecimalItem) tItem).getValue();
            if (cDec == null) {
                tval = "";
            } else {
                tval = cDec.toString();
            }
        }

        if ( tval.length() == 0 ) {
            this.dspAddMessge(rowNum, "ZZZM9025E", msgArg);
            chkFlg = true;
        }
        
        return chkFlg;
    }

    /**
     * duplication check for a required item
     * @param rowNum   csv data row number
     * @param tItem    check item
     * @param msgArg   message argument
     * @return boolean
     */
    private boolean chkDuplicate(BigDecimal rowNum, EZDTItem tItem, String msgArg) {
        
        String val = ( (EZDTStringItem) tItem ).getValue();
        if ( this.keyIdLs.contains( val ) ) {
            this.dspAddMessge(rowNum, "ZYPM0119E", msgArg);
            return false;
        }
        this.keyIdLs.add( val );
        
        return true;
    }

    /**
     * duplication check for a required item (For PARTS)
     * @param rowNum   csv data row number
     * @param tItem    check item
     * @param msgArg   message argument
     * @return boolean
     */
    private boolean chkDuplicateForParts(BigDecimal rowNum, EZDTItem fItem, EZDTItem sItem, String msgArg) {
        
        String fVal = ( (EZDTStringItem) fItem ).getValue();
        String sVal = ( (EZDTStringItem) sItem ).getValue();
        
        HashSet<String> keySet = new HashSet<String>();
        keySet.add(fVal);
        keySet.add(sVal);
        
        if ( this.keyIdLsParts.contains( keySet ) ) {
            this.dspAddMessge(rowNum, "ZYPM0119E", msgArg);
            return false;
        }
        this.keyIdLsParts.add( keySet );
        
        return true;
    }

    /**
     * It stores a message of the error line
     * @param rowNum    row number
     * @param msgId     message code to add
     * @param msgArg    message argument (When unnecessary, set null)
     */
    private void dspAddMessge(BigDecimal rowNum, String msgId, String[] msgArgs) {

        if ( this.errMsgLs.size() > this.MAX_MSG_SZ ) {
            return;
        }
        
        ZZIL0020_XSMsg sMsg = new ZZIL0020_XSMsg();
        sMsg.xxNum_X.setValue(this.errMsgLs.size() + 1);
        sMsg.xxRowNum_X.setValue(rowNum);
        sMsg.upldCsvMsgTxt_X.setValue(S21MessageFunc.clspGetMessage(msgId, msgArgs));
        this.errMsgLs.add(sMsg);

    }
    
    /**
     * It stores a message of the error line
     * @param rowNum    row number
     * @param msgId     message code to add
     * @param msgArg    message argument (When unnecessary, set null)
     */
    private void dspAddMessge(BigDecimal rowNum, String msgId, String msgArg) {
        
        dspAddMessge(rowNum, msgId, new String[] { msgArg } );
    }

    
    /**
     * It stores a message of the error line
     * @param rowNum    row number
     * @param msgId     message code to add
     * @param msgArg    message argument (When unnecessary, set null)
     */
    private void dspAddMessge(BigDecimal rowNum, String msgId) {
        
        dspAddMessge(rowNum, msgId, new String[] { null } );
    }

}
