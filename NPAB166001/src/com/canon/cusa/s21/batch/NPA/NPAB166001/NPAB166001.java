/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB166001;

import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.BATCH_PROGRAM_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.DS_COND_CONST;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.DT_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.DT_FORMAT_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.ETA_DATE_FORMT;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.LAST_DATE_SHIPPED_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_CHARSET;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_ITEM_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_ITEM_ERR_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_ITEM_ERR_MSG;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAIL_TEMPLATE_ID_01;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MAX_COUNT_NUMBER;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MNX;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.MSG_TXT_SALES_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.NPAM0020E;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.NPAM1481E;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.NPZM0001E;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.NPZM0125E;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.NPZM0128E;
import static com.canon.cusa.s21.batch.NPA.NPAB166001.constant.NPAB166001Constant.REPLACE_CHAR;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.PRCH_REQTMsg;
import business.db.TECH_RQST_LIST_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TECH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSACarrierReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequest;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestDetails;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestDetailsReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestLineStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestShipment;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestShipmentReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestShipment.TrackingDetails;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequestShipment.TrackingDetails.TrackingDetails1;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;

/**
 * <pre>
 * Send Open Tech Request  to Click Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/07/2016   Hitachi         T.Iwamoto       Create          NA
 * 01/26/2016   Hitachi         T.Iwamoto       Update          QC#2991
 * 06/29/2016   Hitachi         T.Iwamoto       Update          QC#10113,10114
 * 09/05/2016   Hitachi         K.Yamada        Update          QC#13891
 * 2016/11/08   Hitachi         K.Kojima        Update          QC#14445
 * 2016/11/14   Hitachi         K.Kojima        Update          QC#14445
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 01/11/2017   Hitachi         K.Kitachi       Update          QC#17024
 * 2017/02/24   Hitachi         K.Kojima        Update          QC#17111
 * 2017/02/28   Hitachi         K.Kojima        Update          QC#17111
 * 2017/03/03   Hitachi         K.Kojima        Update          QC#17111
 * 2017/12/08   CITS            M.Naito         Update          QC#18572
 * 2017/12/19   CITS            M.Naito         Update          QC#18572
 * 2018/02/27   CITS            T.Hakodate      Update          QC#21913
 * 2018/04/09   Hitachi         T.Tomita        Update          QC#23804
 * 2018/04/19   Hitachi         T.Tomita        Update          QC#25719
 * 2018/06/01   Fujitsu         R.Nakamura      Update          QC#26112
 * 2019/05/20   CITS            M.Naito         Update          QC#50372
 * 2019/05/21   CITS            K.Ogino         Update          QC#50360
 * 2020/03/02   CITS            T.Wada          Update          QC#55750
 * 2020/11/17   CITS            K.Ogino         Update          QC#57659
 * 2021/02/04   CITS            T.Wada          Update          QC#58372
 * 2021/03/03   CITS            T.Wada          Update          QC#58372-2
 * 2022/10/19   Hitachi         K.Kishimoto     Update          QC#60712
 * </pre>
 */
public class NPAB166001 extends S21BatchMain {

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    
    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Total Normal Count */
    private int normalCount = 0;

    /** Total Error Count */
    private int errorCount = 0;

    // START 2017/12/08 M.Naito [QC#18572,ADD]
    /** Total Count */
    private int totalCount = 0;
    // END 2017/12/08 M.Naito [QC#18572,ADD]

    /** Commit Number Count */
    private int commitNumber = 0;

    /** LAST_DATE_SHIPPED_FORMAT */
    private String lastDateShippedFormat = null;

    /** ETA_DATE_FORMT */
    private String etaDateFormat = null;

    /** Error Messages */
    private String errorMessages = null;

    /** clicksoft object factory */
    private ObjectFactory objFactory = new ObjectFactory();

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private  boolean ofsMultiByteConvertFlg = false;
    // END   2022/10/19 [QC#60712, ADD]

    @Override
    protected void initRoutine() {

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NPZM0001E);
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NPAM0020E, new String[] {MSG_TXT_SALES_DATE });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COUNT_NUMBER) {
            this.commitNumber = MAX_COUNT_NUMBER;
        }

        // Get Const Data
        this.lastDateShippedFormat = getConstValue(LAST_DATE_SHIPPED_FORMAT);
        if (!ZYPCommonFunc.hasValue(this.lastDateShippedFormat)) {
            throw new S21AbendException(NPAM0020E, new String[] {DS_COND_CONST });
        }
        this.etaDateFormat = getConstValue(ETA_DATE_FORMT);
        if (!ZYPCommonFunc.hasValue(this.etaDateFormat)) {
            throw new S21AbendException(NPAM0020E, new String[] {DS_COND_CONST });
        }

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // START 2022/10/19 [QC#60712, ADD]
        String strOFSMultiByteConvertFlg = ZYPCodeDataUtil.getVarCharConstValue("OFS_MULTI_BYTE_CONVERT_FLG", this.glblCmpyCd);
        if (ZYPConstant.FLG_ON_Y.equals(strOFSMultiByteConvertFlg)) {
            ofsMultiByteConvertFlg = true;
        }
        // END   2022/10/19 [QC#60712, ADD]
    }

    @Override
    protected void mainRoutine() {

        callWebService();

        if (errorMessages == null) {
            updateTechInvtyAgingWrk(true);
        } else {
//          // START 2019/05/20 M.Naito [QC#50372, DEL]
            // Send mail if error.
//            sendErrorMail();
//          // END 2019/05/20 M.Naito [QC#50372, DEL]

            // error status update
            updateTechInvtyAgingWrk(false);
        }
    }

    private void callWebService() {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("techProcStsW", TECH_PROC_STS.WAITING_FOR_PROCESS);
        // Del Start 2018/04/09 QC#23804
//        // START 2017/01/11 K.Kitachi [QC#17024, ADD]
//        param.put("asterisk", "*");
//        // END 2017/01/11 K.Kitachi [QC#17024, ADD]
        // Del End 2018/04/09 QC#23804
        try {
            // Add Start 2018/06/01 QC#26112 Delete either
            // For Performance
            S21StopWatch sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/01 QC#26112 Delete either

            // Mod Start 2018/04/09 QC#23804
//            stmt = this.ssmLLClient.createPreparedStatement("getTechnicianRequestInfo", param, setExecParam());
            stmt = this.ssmLLClient.createPreparedStatement("getTargetReqList", param, setExecParam());
            rs = stmt.executeQuery();

            // Add Start 2018/06/01 QC#26112 Delete either
            // For Performance
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:getTargetReqList]End [%s]", sw.getElapsedMilliSec()));

            sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/01 QC#26112 Delete either

//            sendTechInvtyAgingWrkInfo(rs);
            while (rs.next()) {
                sendTechInvtyAgingWrk(rs);
            }
            // Mod End 2018/04/09 QC#23804

            // Add Start 2018/06/01 QC#26112 Delete either
            // For Performance
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_API:sendTechInvtyAgingWrk]End [%s]", sw.getElapsedMilliSec()));
            // Add End 2018/06/01 QC#26112 Delete either

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private String getConstValue(String searchStr) {

        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setConditionValue("glblCmpyCd", glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId", BATCH_PROGRAM_ID);
        inMsg.setConditionValue("dsCondConstCd", searchStr);

        DS_COND_CONSTTMsg outMsg = (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return null;
        }
        return outMsg.dsCondConstValTxt_01.getValue();
    }

    // Add Start 2018/04/09 QC#23804
    private void sendTechInvtyAgingWrk(ResultSet rs) throws SQLException{
        StandardOperations standardOperations = objFactory.createStandardOperations();
        String prchReqNum = rs.getString("PRCH_REQ_NUM");
        List<Map<String, Object>> prtReqDtlList = getPrtReqDtlList(prchReqNum);

        List<StandardOperation> prtRqstShipList = new ArrayList<StandardOperation>();
        StandardOperation prtRqstShip;
        List<StandardOperation> prtRqstDtlList = new ArrayList<StandardOperation>();
        StandardOperation prtRqstDtl;

        // Add Start 2018/06/01 QC#26112 Delete either
        // For Performance
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        // Add End 2018/06/01 QC#26112 Delete either

        // QC#58372-2 Add Start
        int loopCnt = 0;
        TECH_RQST_LIST_WRKTMsg prevTechRqstListWrk = new TECH_RQST_LIST_WRKTMsg();
        List<Map<String, Object>> trackingList = new ArrayList<Map<String,Object>>();
        for (Map<String, Object> prtReqDtl : prtReqDtlList) {
            BigDecimal techRqstListWrkPk = (BigDecimal) prtReqDtl.get("TECH_RQST_LIST_WRK_PK");
            TECH_RQST_LIST_WRKTMsg curTechRqstListWrk = getTechRqstListWrk(techRqstListWrkPk);
            if (curTechRqstListWrk == null) {
                continue;
            }
            if (loopCnt == 0) {
                Map<String, Object> trackingInfo = setTrackingInfo(curTechRqstListWrk);
                trackingList.add(trackingInfo);
                if (ZYPCommonFunc.hasValue(curTechRqstListWrk.mdseCd)) {
                    EZDMsg.copy(curTechRqstListWrk, null, prevTechRqstListWrk, null);
                }
                loopCnt++;
                continue;
            }
            
            if (isSameShipOrd(curTechRqstListWrk, prevTechRqstListWrk)) {
                Map<String, Object> trackingInfo = setTrackingInfo(curTechRqstListWrk);
                trackingList.add(trackingInfo);

                if (ZYPCommonFunc.hasValue(curTechRqstListWrk.mdseCd)) {
                    EZDMsg.copy(curTechRqstListWrk, null, prevTechRqstListWrk, null);
                }
                loopCnt++;
                continue;
            }        
            else {
                // CSA_PartsRequestShipment
                prtRqstShip = setCSAPartsRequestShipment(prevTechRqstListWrk, trackingList);
                if (prtRqstShip != null) {
                    prtRqstShipList.add(prtRqstShip);
                }

                trackingList = new ArrayList<Map<String,Object>>();
                Map<String, Object> trackingInfo = setTrackingInfo(curTechRqstListWrk);
                trackingList.add(trackingInfo);
                EZDMsg.copy(curTechRqstListWrk, null, prevTechRqstListWrk, null);
                loopCnt++;
            }        
        }
        // this is Final Record
        prtRqstShip = setCSAPartsRequestShipment(prevTechRqstListWrk, trackingList);
        if (prtRqstShip != null) {
            prtRqstShipList.add(prtRqstShip);
        }
        // QC#58372-2 Add End

        // QC#58372-2 Del Start
//        for (Map<String, Object> prtReqDtl : prtReqDtlList) {
//            BigDecimal techRqstListWrkPk = (BigDecimal) prtReqDtl.get("TECH_RQST_LIST_WRK_PK");
//            TECH_RQST_LIST_WRKTMsg tMsg = getTechRqstListWrk(techRqstListWrkPk);
//            if (tMsg == null) {
//                continue;
//            }
//
//            // CSA_PartsRequestShipment
//            prtRqstShip = setCSAPartsRequestShipment(tMsg);
//            if (prtRqstShip != null) {
//                prtRqstShipList.add(prtRqstShip);
//            }
//
//            // QC#58372 Del Start
//            // CSA_PartsRequestDetails
////            prtRqstDtl = setCSAPartsRequestDetails(tMsg);
////            if (prtRqstDtl != null) {
////                prtRqstDtlList.add(prtRqstDtl);
////            }
//            // QC#58372 Del End
//
//        }
        // QC#58372-2 Del End


        // QC#58372 Add Start
        // CSA_PartsRequestDetails
        prevTechRqstListWrk = new TECH_RQST_LIST_WRKTMsg();
        List<String> soList = new ArrayList<String>();
        loopCnt = 0;
        for (Map<String, Object> prtReqDtl : prtReqDtlList) {
            BigDecimal techRqstListWrkPk = (BigDecimal) prtReqDtl.get("TECH_RQST_LIST_WRK_PK");
            TECH_RQST_LIST_WRKTMsg curTechRqstListWrk = getTechRqstListWrk(techRqstListWrkPk);
    
            // this is First Record
            if (loopCnt == 0) {
                if (ZYPCommonFunc.hasValue(curTechRqstListWrk.techRqstSoNum)) {
                    soList.add(curTechRqstListWrk.techRqstSoNum.getValue());
                }
                if (ZYPCommonFunc.hasValue(curTechRqstListWrk.mdseCd)) {
                    EZDMsg.copy(curTechRqstListWrk, null, prevTechRqstListWrk, null);
                }
                loopCnt++;
                continue;
            }

            // Key Duplicate Check
            if (isDuplicate(prevTechRqstListWrk, curTechRqstListWrk)){
                // Duplicate !

                // TECH_RQST_LIST_WRKTMsg Rebuild (1.0 Prev Qty backup)
                BigDecimal sumPrchReqQty = prevTechRqstListWrk.prchReqQty.getValue();
                BigDecimal sumShipQty = prevTechRqstListWrk.shipQty.getValue();
                BigDecimal sumRwsPutAwayQty = prevTechRqstListWrk.rwsPutAwayQty.getValue();
                BigDecimal sumBackToTechQty = prevTechRqstListWrk.backToTechQty.getValue();

                // TECH_RQST_LIST_WRKTMsg Rebuild (2.0 Prev Status backup)
                String prevPrchReqLineStsDescTxt = "";
                if (ZYPCommonFunc.hasValue(prevTechRqstListWrk.prchReqLineStsDescTxt)) {
                    prevPrchReqLineStsDescTxt = prevTechRqstListWrk.prchReqLineStsDescTxt.getValue();
                }

                // TECH_RQST_LIST_WRKTMsg Rebuild (3. from current to prev)
                boolean processed = false;
                if (ZYPCommonFunc.hasValue(curTechRqstListWrk.techRqstSoNum)) {
                    for(String so : soList) {
                        if (so.equals(curTechRqstListWrk.techRqstSoNum.getValue())) {
                            processed = true;
                            break;
                        }
                    }
                    if (processed) {
                        loopCnt++;
                        continue;
                    }
                    soList.add(curTechRqstListWrk.techRqstSoNum.getValue());
                }
                EZDMsg.copy(curTechRqstListWrk, null, prevTechRqstListWrk, null);

                // TECH_RQST_LIST_WRKTMsg Rebuild (1.1 Qty Merge & Update )
                ZYPEZDItemValueSetter.setValue(prevTechRqstListWrk.prchReqQty,    sumPrchReqQty.add(curTechRqstListWrk.prchReqQty.getValue()));
                ZYPEZDItemValueSetter.setValue(prevTechRqstListWrk.shipQty,       sumShipQty.add(curTechRqstListWrk.shipQty.getValue()));
                ZYPEZDItemValueSetter.setValue(prevTechRqstListWrk.rwsPutAwayQty, sumRwsPutAwayQty.add(curTechRqstListWrk.rwsPutAwayQty.getValue()));
                ZYPEZDItemValueSetter.setValue(prevTechRqstListWrk.backToTechQty, sumBackToTechQty.add(curTechRqstListWrk.backToTechQty.getValue()));

                // TECH_RQST_LIST_WRKTMsg Rebuild (2.1. Status Diff Check)
                String currPrchReqLineStsDescTxt = "";
                if (ZYPCommonFunc.hasValue(curTechRqstListWrk.prchReqLineStsDescTxt)) {
                    currPrchReqLineStsDescTxt = curTechRqstListWrk.prchReqLineStsDescTxt.getValue();
                }
                if (!prevPrchReqLineStsDescTxt.equals(currPrchReqLineStsDescTxt)) {
                	// Status Diff ! -> getPrLineStatus
                    String prchReqLineSts = getPrchReqLineSts(prevTechRqstListWrk.prchReqNum.getValue(),prevTechRqstListWrk.prchReqLineNum.getValue());
                    if (ZYPCommonFunc.hasValue(prchReqLineSts)) {
                        ZYPEZDItemValueSetter.setValue(prevTechRqstListWrk.prchReqLineStsDescTxt, prchReqLineSts);
                    }
                }
                loopCnt++;
                continue;
            } else {
                // Not Duplicate
                prtRqstDtl = setCSAPartsRequestDetails(prevTechRqstListWrk, soList);
                if (prtRqstDtl != null) {
                    prtRqstDtlList.add(prtRqstDtl);
                }
                soList = new ArrayList<String>();
                if (ZYPCommonFunc.hasValue(curTechRqstListWrk.techRqstSoNum)) {
                    soList.add(curTechRqstListWrk.techRqstSoNum.getValue());
                }
                // from current to prev
                EZDMsg.copy(curTechRqstListWrk, null, prevTechRqstListWrk, null);
                loopCnt++;
            }
        }

        // this is Final Record
        prtRqstDtl = setCSAPartsRequestDetails(prevTechRqstListWrk, soList);
        if (prtRqstDtl != null) {
            prtRqstDtlList.add(prtRqstDtl);
        }
        soList = new ArrayList<String>();
        // QC#58372 Add End

        // CSA_PartsRequestShipment
        for (StandardOperation so : prtRqstShipList) {
            standardOperations.getOperation().add(so);
        }
        // CSA_PartsRequestDetails
        for (StandardOperation so : prtRqstDtlList) {
            standardOperations.getOperation().add(so);
        }

        // Add Start 2018/06/01 QC#26112 Delete either
        // For Performance
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_API:setShipmentDetailsParam]End [%s]", sw.getElapsedMilliSec()));
        // Add End 2018/06/01 QC#26112 Delete either

        // CSA_PartsRequest
        standardOperations.getOperation().add(setCSAPartsRequest(rs, prchReqNum, prtReqDtlList));
        this.normalCount++;

        callWmbApi(standardOperations);
    }

    // CSA_PartsRequestShipment
// QC#58372-2 Del Start
//    private StandardOperation setCSAPartsRequestShipment(TECH_RQST_LIST_WRKTMsg tMsg) {
//        Map<String, Object> prtReqShip = getPrtReqShipList(tMsg.techRqstListWrkPk.getValue());
//
//        CSAPartsRequestShipment partsRequestShipment = objFactory.createCSAPartsRequestShipment();
//        String prchReqNum = (String) prtReqShip.get("PRCH_REQ_NUM");
//        String techRqstSoNum = (String) prtReqShip.get("TECH_RQST_SO_NUM");
//        partsRequestShipment.setRequestNo(prchReqNum);
//        partsRequestShipment.setPartNumber((String) prtReqShip.get("MDSE_CD"));
//        partsRequestShipment.setLineNumber((String) prtReqShip.get("LINE_NUM"));
//        partsRequestShipment.setShipOrderNumber(techRqstSoNum);
//        BigDecimal shipQty = (BigDecimal) prtReqShip.get("SHIP_QTY");
//        partsRequestShipment.setShippedQuantity(shipQty.intValue());
//
//        // TrackingDetails
//        TrackingDetails trackingdetails = objFactory.createCSAPartsRequestShipmentTrackingDetails();
//        TrackingDetails1 trackingDetails1 = objFactory.createCSAPartsRequestShipmentTrackingDetailsTrackingDetails();
//        CSACarrierReference carrierReference = objFactory.createCSACarrierReference();
//        String proNum = (String) prtReqShip.get("PRO_NUM");
//        String carrTrkUrl = (String) prtReqShip.get("CARR_TRK_URL");
//        String carrTpNm = (String) prtReqShip.get("CARR_TP_NM");
//        // QC#50360
//        String etaDt = ZYPDateUtil.DateFormatter((String) prtReqShip.get("RQST_RCV_DT"), ZYPDateUtil.TYPE_YYYYMMDD, DT_FORMAT);
//        String etaTm = (String) prtReqShip.get("RQST_RCV_TM");
//        if (!ZYPCommonFunc.hasValue(etaTm)) {
//            etaTm = "000000";
//        } else if (etaTm.length() == 4) {
//            etaTm = etaTm + "00";
//        }
//        etaTm = ZYPDateUtil.DateFormatter(etaTm, "HHmmss", "HH:mm:ss");
//
//        trackingDetails1.setTrackingNo(proNum);
//
//        
//        // QC#57659-3
//        if (ZYPCommonFunc.hasValue(carrTpNm) && MNX.equals(carrTpNm) && ZYPCommonFunc.hasValue(techRqstSoNum) && ZYPCommonFunc.hasValue(proNum)) {
//            List<Map<String, Object>> proNumList = getProNumListMnx(techRqstSoNum, proNum);
//            for (Map<String, Object> proNumMap : proNumList) {
//                String carrEncTrkUrl = (String) proNumMap.get("CARR_ENC_TRK_URL");
//                if (ZYPCommonFunc.hasValue(carrEncTrkUrl)) {
//                    trackingDetails1.setTrackingURL(carrEncTrkUrl);
//                } else {
//                    trackingDetails1.setTrackingURL(editCarrTrkUrl(carrTrkUrl, (String) proNumMap.get("PRO_NUM")));
//                }
//            }
//        } else {
//            trackingDetails1.setTrackingURL(editCarrTrkUrl(carrTrkUrl, proNum));
//        }
//        if (ZYPCommonFunc.hasValue(carrTpNm)) {
//            carrierReference.getContent().add(carrTpNm);
//            trackingDetails1.setCarrier(carrierReference);
//            // QC#50360
//            trackingDetails1.setETA(etaDt + " " + etaTm);
//        }
//        trackingdetails.getTrackingDetails().add(trackingDetails1);
//        // Mod Start 2018/04/19 QC#25719
//        if (ZYPCommonFunc.hasValue(techRqstSoNum) && ZYPCommonFunc.hasValue(proNum)) {
//        // Mod End 2018/04/19 QC#25719
//            List<Map<String, Object>> proNumList = getProNumList(techRqstSoNum, proNum);
//            for (Map<String, Object> proNumMap : proNumList) {
//                TrackingDetails1 additionalTrackingDetails1 = objFactory.createCSAPartsRequestShipmentTrackingDetailsTrackingDetails();
//                // QC#57659
//                String soProNum = (String) proNumMap.get("PRO_NUM");
//                String carrEncTrkUrl = (String) proNumMap.get("CARR_ENC_TRK_URL");
//
//                additionalTrackingDetails1.setTrackingNo(soProNum);
//                if (ZYPCommonFunc.hasValue(carrEncTrkUrl)) {
//                    additionalTrackingDetails1.setTrackingURL(carrEncTrkUrl);
//                } else {
//                    additionalTrackingDetails1.setTrackingURL(editCarrTrkUrl(carrTrkUrl, (String) proNumMap.get("PRO_NUM")));
//                }
//                CSACarrierReference additionalCarrierReference = objFactory.createCSACarrierReference();
//                if (ZYPCommonFunc.hasValue(carrTpNm)) {
//                    additionalCarrierReference.getContent().add(carrTpNm);
//                    additionalTrackingDetails1.setCarrier(additionalCarrierReference);
//                    // QC#50360
//                    additionalTrackingDetails1.setETA(etaDt + " " + etaTm);
//                }
//                trackingdetails.getTrackingDetails().add(additionalTrackingDetails1);
//            }
//        }
//        partsRequestShipment.setTrackingDetails(trackingdetails);
//
//        W6RequestedProperties props = objFactory.createW6RequestedProperties();
//        StandardOperation standardOperationShip = objFactory.createStandardOperation();
//        standardOperationShip.setAction("UpdateOrCreate");
//        standardOperationShip.setRequestedProperties(props);
//        BaseObjectWrapper bowDetail = objFactory.createBaseObjectWrapper();
//        bowDetail.setObject(partsRequestShipment);
//        standardOperationShip.setObject(bowDetail);
//        return standardOperationShip;
//    }
// QC#58372-2 Del End
    
// QC#58372-2 Add Start
    private StandardOperation setCSAPartsRequestShipment(TECH_RQST_LIST_WRKTMsg tMsg, List<Map<String, Object>> trackingList) {
        Map<String, Object> prtReqShip = getPrtReqShipList(tMsg.techRqstListWrkPk.getValue());

        CSAPartsRequestShipment partsRequestShipment = objFactory.createCSAPartsRequestShipment();
        String prchReqNum = (String) prtReqShip.get("PRCH_REQ_NUM");
        String techRqstSoNum = (String) prtReqShip.get("TECH_RQST_SO_NUM");
        partsRequestShipment.setRequestNo(prchReqNum);
        partsRequestShipment.setPartNumber((String) prtReqShip.get("MDSE_CD"));
        partsRequestShipment.setLineNumber((String) prtReqShip.get("LINE_NUM"));
        partsRequestShipment.setShipOrderNumber(techRqstSoNum);
        BigDecimal shipQty = (BigDecimal) prtReqShip.get("SHIP_QTY");
        partsRequestShipment.setShippedQuantity(shipQty.intValue());

        // TrackingDetails
        TrackingDetails trackingdetails = objFactory.createCSAPartsRequestShipmentTrackingDetails();
        //CSACarrierReference carrierReference = objFactory.createCSACarrierReference();

        List<String> regProNumList = new ArrayList<String>();
        String prchReqDtlProNum = (String) prtReqShip.get("PRCH_REQ_DTL_PRO_NUM");
        
        for (Map<String, Object> trackingInfo : trackingList) {

            String proNum = (String) trackingInfo.get("PRO_NUM");
            if (ZYPCommonFunc.hasValue(prchReqDtlProNum) && prchReqDtlProNum.equals("*")) {
                proNum = (String) prtReqShip.get("PRO_NUM");
            }

            // ProNum Dupplicate Check
            boolean processed = false;
            for (String regProNum : regProNumList) {
                if (regProNum.equals(proNum)) {
                    processed = true;
                    break;
                }
            }
            if (processed) {
                continue;
            }
            String carrTrkUrl = (String) trackingInfo.get("CARR_TRK_URL");
            String carrTpNm = (String) trackingInfo.get("CARR_TP_NM");
            String etaDt = ZYPDateUtil.DateFormatter((String) trackingInfo.get("RQST_RCV_DT"), ZYPDateUtil.TYPE_YYYYMMDD, DT_FORMAT);
            String etaTm = (String) trackingInfo.get("RQST_RCV_TM");

            
            if (!ZYPCommonFunc.hasValue(etaTm)) {
                etaTm = "000000";
            } else if (etaTm.length() == 4) {
                etaTm = etaTm + "00";
            }
            etaTm = ZYPDateUtil.DateFormatter(etaTm, "HHmmss", "HH:mm:ss");

            TrackingDetails1 trackingDetails1 = objFactory.createCSAPartsRequestShipmentTrackingDetailsTrackingDetails();

            trackingDetails1.setTrackingNo(proNum);
            regProNumList.add(proNum);

            if (ZYPCommonFunc.hasValue(carrTpNm) && MNX.equals(carrTpNm) && ZYPCommonFunc.hasValue(techRqstSoNum) && ZYPCommonFunc.hasValue(proNum)) {
                List<Map<String, Object>> proNumList = getProNumListMnx(techRqstSoNum, proNum);
                for (Map<String, Object> proNumMap : proNumList) {
                    String carrEncTrkUrl = (String) proNumMap.get("CARR_ENC_TRK_URL");
                    if (ZYPCommonFunc.hasValue(carrEncTrkUrl)) {
                        trackingDetails1.setTrackingURL(carrEncTrkUrl);
                    } else {
                        trackingDetails1.setTrackingURL(editCarrTrkUrl(carrTrkUrl, (String) proNumMap.get("PRO_NUM")));
                    }
                }
            } else {
                trackingDetails1.setTrackingURL(editCarrTrkUrl(carrTrkUrl, proNum));
            }
            if (ZYPCommonFunc.hasValue(carrTpNm)) {
                CSACarrierReference additionalCarrierReference = objFactory.createCSACarrierReference();
                if (ZYPCommonFunc.hasValue(carrTpNm)) {
                    additionalCarrierReference.getContent().add(carrTpNm);
                    trackingDetails1.setCarrier(additionalCarrierReference);
                    trackingDetails1.setETA(etaDt + " " + etaTm);
                }
            }
            trackingdetails.getTrackingDetails().add(trackingDetails1);

            if (ZYPCommonFunc.hasValue(techRqstSoNum) && ZYPCommonFunc.hasValue(proNum)) {
                List<Map<String, Object>> proNumList = getProNumList(techRqstSoNum, proNum);
                for (Map<String, Object> proNumMap : proNumList) {
                    String soProNum = (String) proNumMap.get("PRO_NUM");
                    String carrEncTrkUrl = (String) proNumMap.get("CARR_ENC_TRK_URL");

                    // ProNum Dupplicate Check
                    processed = false;
                    for (String regProNum : regProNumList) {
                        if (regProNum.equals(soProNum)) {
                            processed = true;
                            break;
                        }
                    }
                    if (processed) {
                        continue;
                    }

                    TrackingDetails1 additionalTrackingDetails1 = objFactory.createCSAPartsRequestShipmentTrackingDetailsTrackingDetails();
                    additionalTrackingDetails1.setTrackingNo(soProNum);
                    regProNumList.add(soProNum);

                    if (ZYPCommonFunc.hasValue(carrEncTrkUrl)) {
                        additionalTrackingDetails1.setTrackingURL(carrEncTrkUrl);
                    } else {
                        additionalTrackingDetails1.setTrackingURL(editCarrTrkUrl(carrTrkUrl, (String) proNumMap.get("PRO_NUM")));
                    }
                    CSACarrierReference additionalCarrierReference = objFactory.createCSACarrierReference();
                    if (ZYPCommonFunc.hasValue(carrTpNm)) {
                        additionalCarrierReference.getContent().add(carrTpNm);
                        additionalTrackingDetails1.setCarrier(additionalCarrierReference);
                        additionalTrackingDetails1.setETA(etaDt + " " + etaTm);
                    }
                    trackingdetails.getTrackingDetails().add(additionalTrackingDetails1);
                }
            }
            
        }

        partsRequestShipment.setTrackingDetails(trackingdetails);

        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        StandardOperation standardOperationShip = objFactory.createStandardOperation();
        standardOperationShip.setAction("UpdateOrCreate");
        standardOperationShip.setRequestedProperties(props);
        BaseObjectWrapper bowDetail = objFactory.createBaseObjectWrapper();
        bowDetail.setObject(partsRequestShipment);
        standardOperationShip.setObject(bowDetail);
        return standardOperationShip;
    }
// QC#58372-2 Add End

    // CSA_PartsRequestDetails
    private StandardOperation setCSAPartsRequestDetails(TECH_RQST_LIST_WRKTMsg tMsg, List<String> soList) {
        int prchReqQty = tMsg.prchReqQty.getValueInt();
        int shipQty = tMsg.shipQty.getValueInt();
        int rwsPutAwayQty = tMsg.rwsPutAwayQty.getValueInt();
        int backToTechQty = tMsg.backToTechQty.getValueInt();

        CSAPartsRequestDetails partsRequestDetails = objFactory.createCSAPartsRequestDetails();
        partsRequestDetails.setPartNumber(tMsg.mdseCd.getValue());
        // START 2022/10/19 [QC#60712, MOD]
//        partsRequestDetails.setDescription(tMsg.mdseDescShortTxt.getValue());
        partsRequestDetails.setDescription(ofsMultiByteConvert(tMsg.mdseDescShortTxt.getValue()));
        // END   2022/10/19 [QC#60712, MOD]
        CSAPartsRequestLineStatusReference partsRequestLineStatusReference = objFactory.createCSAPartsRequestLineStatusReference();
        partsRequestLineStatusReference.getContent().add(tMsg.prchReqLineStsDescTxt.getValue());
        partsRequestDetails.setStatus(partsRequestLineStatusReference);
        partsRequestDetails.setInventorySource(tMsg.srcInvtyLocCd.getValue());
        partsRequestDetails.setETA(changeEtaDate(tMsg.rqstRcvDt.getValue()));

        partsRequestDetails.setOrderedQuantity(prchReqQty);
        partsRequestDetails.setShippedQuantity(shipQty);
        partsRequestDetails.setDeliveredQuantity(rwsPutAwayQty);
        partsRequestDetails.setBackOrderQuantity(backToTechQty);

        partsRequestDetails.setRequestNo(tMsg.prchReqNum.getValue());
        String lineNum = tMsg.prchReqLineNum.getValue();
        BigDecimal subLineNum = tMsg.prchReqLineSubNum.getValue();
        if (ZYPCommonFunc.hasValue(lineNum) && ZYPCommonFunc.hasValue(subLineNum)) {
            lineNum = lineNum + "." + subLineNum.toPlainString();
        } else if (!ZYPCommonFunc.hasValue(lineNum)) {
            lineNum = "";
        }
        partsRequestDetails.setLineNumber(lineNum);

        // QC#55750 Add Start
        PRCH_REQTMsg prchReq =  getPrchReq(tMsg.prchReqNum.getValue());
        if (prchReq!= null && ZYPCommonFunc.hasValue(prchReq.prchReqCloDtTmTs)) {
            partsRequestDetails.setCloseDate(changeCloseDate(prchReq.prchReqCloDtTmTs.getValue()));
        }
        // QC#55750 Add End

        CSAPartsRequestDetails.ShipmentInfo shipmentInfo = objFactory.createCSAPartsRequestDetailsShipmentInfo();
        // QC#58372 Mod Start
        if (soList.size() > 0) {
//            String prevPrchReqNum = null;
//            String prevMdseCd = null;
//            String prevLineNum = null;
//            String prevSoNum = null;
            for (String soNum : soList) {
//                if (
//                    ZYPCommonFunc.hasValue(prevPrchReqNum) && prevPrchReqNum.equals(tMsg.prchReqNum.getValue())
//                    && ZYPCommonFunc.hasValue(prevMdseCd) && prevPrchReqNum.equals(tMsg.mdseCd.getValue())
//                    && ZYPCommonFunc.hasValue(prevLineNum) && prevPrchReqNum.equals(lineNum)
//                    && ZYPCommonFunc.hasValue(prevSoNum) && prevPrchReqNum.equals(soNum)
//                ) {
//                    continue;
//                }
                CSAPartsRequestShipmentReference partsRequestShipmentRef = objFactory.createCSAPartsRequestShipmentReference();
                partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceRequestNo(tMsg.prchReqNum.getValue()));
                partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferencePartNumber(tMsg.mdseCd.getValue()));
                partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceLineNumber(lineNum));
                partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceShipOrderNumber(soNum));
                shipmentInfo.getPartsShipment().add(partsRequestShipmentRef);

//                prevPrchReqNum = tMsg.prchReqNum.getValue();
//                prevMdseCd = tMsg.mdseCd.getValue();
//                prevLineNum = lineNum;
//                prevSoNum = soNum;
            }
        }
//        String soNum = tMsg.techRqstSoNum.getValue();
//        if (ZYPCommonFunc.hasValue(soNum)) {
//            CSAPartsRequestShipmentReference partsRequestShipmentRef = objFactory.createCSAPartsRequestShipmentReference();
//            partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceRequestNo(tMsg.prchReqNum.getValue()));
//            partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferencePartNumber(tMsg.mdseCd.getValue()));
//            partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceLineNumber(lineNum));
//            partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceShipOrderNumber(soNum));
//            shipmentInfo.getPartsShipment().add(partsRequestShipmentRef);
//        }
        // QC#58372 Mod End
        partsRequestDetails.setShipmentInfo(shipmentInfo);

        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        StandardOperation standardOperationDetail = objFactory.createStandardOperation();
        standardOperationDetail.setAction("UpdateOrCreate");
        standardOperationDetail.setRequestedProperties(props);
        BaseObjectWrapper bowDetail = objFactory.createBaseObjectWrapper();
        bowDetail.setObject(partsRequestDetails);
        standardOperationDetail.setObject(bowDetail);
        return standardOperationDetail;
    }

    // CSA_PartsRequest
    private StandardOperation setCSAPartsRequest(ResultSet rs, String prchReqNum, List<Map<String, Object>> dtlList) throws SQLException{
        CSAPartsRequest partsRequest = objFactory.createCSAPartsRequest();
        partsRequest.setRequestNo(prchReqNum);
        partsRequest.setEngineer(rs.getString("TECH_TOC_CD"));
        CSAPartsRequestStatusReference partsRequestStatusReference = objFactory.createCSAPartsRequestStatusReference();
        partsRequestStatusReference.getContent().add(rs.getString("PRCH_REQ_STS_DESC_TXT"));
        partsRequest.setStatus(partsRequestStatusReference);
        partsRequest.setTaskNo(rs.getString("SVC_TASK_NUM"));
        partsRequest.setLastDateShipped(changeLastDateShipped(rs.getString("SHIP_DT_TM_TS")));
        partsRequest.setCustomer(rs.getString("DS_ACCT_NM"));
        String destination = rs.getString("CUST_ADDR");
        if (destination != null && destination.length() > 500) {
            destination = destination.substring(0, 500);
        }
        partsRequest.setDestination(destination);
        partsRequest.setWarehouse(rs.getString("RTL_WH_NM"));

        // QC#55750 Add Start
        PRCH_REQTMsg prchReq =  getPrchReq(prchReqNum);
        if (prchReq!= null && ZYPCommonFunc.hasValue(prchReq.prchReqCloDtTmTs)) {
            partsRequest.setCloseDate(changeCloseDate(prchReq.prchReqCloDtTmTs.getValue()));
        }
        // QC#55750 Add End

        com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequest.CSAPartsRequestDetails detailRef = new com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequest.CSAPartsRequestDetails();
        String lineNum;
        BigDecimal subLineNum;
        // QC#58372 Add Start
        String prevlineNum = null;
        // QC#58372 Add End
        for (Map<String, Object> dtl : dtlList) {
            CSAPartsRequestDetailsReference partsRequestDetailsRef = objFactory.createCSAPartsRequestDetailsReference();
            partsRequestDetailsRef.getContent().add(objFactory.createCSAPartsRequestDetailsReferenceRequestNo((String) dtl.get("PRCH_REQ_NUM")));
            partsRequestDetailsRef.getContent().add(objFactory.createCSAPartsRequestDetailsReferencePartNumber((String) dtl.get("MDSE_CD")));
            lineNum = (String) dtl.get("PRCH_REQ_LINE_NUM");
            subLineNum = (BigDecimal) dtl.get("PRCH_REQ_LINE_SUB_NUM");
            if (ZYPCommonFunc.hasValue(lineNum) && ZYPCommonFunc.hasValue(subLineNum)) {
                lineNum = lineNum + "." + subLineNum.toPlainString();
            } else if (!ZYPCommonFunc.hasValue(lineNum)) {
                lineNum = "";
            }
            // QC#58372 Add Start
            if (ZYPCommonFunc.hasValue(prevlineNum) && ZYPCommonFunc.hasValue(lineNum)) {
                if (lineNum.equals(prevlineNum)) {
                    continue;
                }
            }
            // QC#58372 Add End
            partsRequestDetailsRef.getContent().add(objFactory.createCSAPartsRequestDetailsReferenceLineNumber(lineNum));
            detailRef.getCSAPartsRequestDetail().add(partsRequestDetailsRef);

            // QC#58372 Add Start
            prevlineNum = lineNum;
            // QC#58372 Add End
            
        }
        partsRequest.setCSAPartsRequestDetails(detailRef);

        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        StandardOperation standardOperation = objFactory.createStandardOperation();
        standardOperation.setAction("UpdateOrCreate");
        standardOperation.setRequestedProperties(props);
        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
        bow.setObject(partsRequest);
        standardOperation.setObject(bow);
        return standardOperation;
    }

    private List<Map<String, Object>> getPrtReqDtlList(String prchReqNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prchReqNum", prchReqNum);
        param.put("techProcStsW", TECH_PROC_STS.WAITING_FOR_PROCESS);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPrtReqDtlList", param);
    }

    private Map<String, Object> getPrtReqShipList(BigDecimal techRqstListWrkPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("techRqstListWrkPk", techRqstListWrkPk);
        return (Map<String, Object>) ssmBatchClient.queryObject("getPrtReqShipList", param);
    }

    private TECH_RQST_LIST_WRKTMsg getTechRqstListWrk(BigDecimal techRqstListWrkPk) {
        TECH_RQST_LIST_WRKTMsg inMsg = new TECH_RQST_LIST_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.techRqstListWrkPk, techRqstListWrkPk);
        return (TECH_RQST_LIST_WRKTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private String editCarrTrkUrl(String url, String proNum) {
        if (!ZYPCommonFunc.hasValue(url)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(proNum)) {
            proNum = "";
        }

        Pattern pattern = Pattern.compile(REPLACE_CHAR);
        Matcher matcher = pattern.matcher(url);
        String rtnUrl = matcher.replaceAll(proNum);
        return EZDCommonFunc.toHTMLString(rtnUrl);
    }
    // Add End 2018/04/09 QC#23804

    // Del Start 2018/04/09 QC#23804
//    private void sendTechInvtyAgingWrkInfo(ResultSet rs) {
//        StandardOperations standardOperations = objFactory.createStandardOperations();
//        List<CSAPartsRequest> partsReqList = new ArrayList<CSAPartsRequest>();
//        List<CSAPartsRequestDetails> partsReqDtlList = new ArrayList<CSAPartsRequestDetails>();
//        Map<String, List<CSAPartsRequestDetailsReference>> partsReqDetailRefListMap = new TreeMap<String, List<CSAPartsRequestDetailsReference>>();
//        List<CSAPartsRequestShipment> partsReqShipList = new ArrayList<CSAPartsRequestShipment>();
//        List<CSAPartsRequestShipmentReference> partsReqShipRefList = new ArrayList<CSAPartsRequestShipmentReference>();
//        List<TrackingDetails1> trackingDetailsList = new ArrayList<TrackingDetails1>();
//        CSAPartsRequestDetails objPartsRequestDetails = objFactory.createCSAPartsRequestDetails();
//        CSAPartsRequestShipment partsRequestShipment = objFactory.createCSAPartsRequestShipment();
//        List<StandardOperation> partsReqSO = new ArrayList<StandardOperation>();
//        List<StandardOperation> partsReqDtlSO = new ArrayList<StandardOperation>();
//        List<StandardOperation> partsReqRefSO = new ArrayList<StandardOperation>();
//        List<StandardOperation> partsReqShipSO = new ArrayList<StandardOperation>();
//        BigDecimal wmbMaxCount = ZYPCodeDataUtil.getNumConstValue("NPAB166001_WMB_MAX_LENGTH", glblCmpyCd);
//        if (wmbMaxCount == null) {
//            wmbMaxCount = BigDecimal.ZERO;
//        }
//
//        try {
//
//            String techId = null;
//            String prevTechId = null;
//            String targetPrchReqNum = null;
//            String prevTargetPrchReqNum = null;
//            String targetPrchReqLineNum = null;
//            String prevTargetPrchReqLineNum = null;
//            String targetSoNum = null;
//            String preTargetSoNum = null;
//            int sumPrchReqQty = 0;
//            int sumShipQty = 0;
//            int sumRwsPutAwayQty = 0;
//            int sumBackToTechQty = 0;
//            String curSoNum = null;
//            String prevSoNum = "INIT";
//            List<String> reqNumForShipList = new ArrayList<String>();
//
//            while (rs.next()) {
//
//                techId = rs.getString("TECH_TOC_CD");
//                targetPrchReqNum = rs.getString("PRCH_REQ_NUM");
//                targetPrchReqLineNum = createTargetPrchReqLineNum(rs);
//                targetSoNum = rs.getString("TECH_RQST_SO_NUM");
//                curSoNum = targetSoNum;
//
//                if (BigDecimal.ZERO.compareTo(wmbMaxCount) < 0) {
//                    int sendTargetCnt = partsReqSO.size();
//                    if (sendTargetCnt > 0 && sendTargetCnt + partsReqList.size() > wmbMaxCount.intValue()) {
//                        for (int i = 0; i < partsReqSO.size(); i++) {
//                            standardOperations.getOperation().add(partsReqSO.get(i));
//                        }
//                        for (int i = 0; i < partsReqDtlSO.size(); i++) {
//                            standardOperations.getOperation().add(partsReqDtlSO.get(i));
//                        }
//                        for (int i = 0; i < partsReqRefSO.size(); i++) {
//                            standardOperations.getOperation().add(partsReqRefSO.get(i));
//                        }
//                        for (int i = 0; i < partsReqShipSO.size(); i++) {
//                            standardOperations.getOperation().add(partsReqShipSO.get(i));
//                        }
//                        if (standardOperations.getOperation().size() > 0) {
//                            // Call WMB API
//                            callWmbApi(standardOperations);
//                            standardOperations = objFactory.createStandardOperations();
//                        }
//                        partsReqSO = new ArrayList<StandardOperation>();
//                        partsReqDtlSO = new ArrayList<StandardOperation>();
//                        partsReqRefSO = new ArrayList<StandardOperation>();
//                        partsReqShipSO = new ArrayList<StandardOperation>();
//                    }
//                }
//
//                if ((ZYPCommonFunc.hasValue(prevTechId) && !techId.equals(prevTechId)) || (partsReqList.size() >= wmbMaxCount.intValue())) {
//                    // CSAPartsRequest
//                    for (CSAPartsRequest partsRequest : partsReqList) {
//                        W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                        StandardOperation standardOperation = objFactory.createStandardOperation();
//                        standardOperation.setAction("UpdateOrCreate");
//                        standardOperation.setRequestedProperties(props);
//                        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
//                        bow.setObject(partsRequest);
//                        standardOperation.setObject(bow);
//                        partsReqSO.add(standardOperation);
//                    }
//                    partsReqList = new ArrayList<CSAPartsRequest>();
//
//                    // CSAPartsRequestDetails
//                    for (CSAPartsRequestDetails partsRequestDetails : partsReqDtlList) {
//                        W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                        StandardOperation standardOperationDetail = objFactory.createStandardOperation();
//                        standardOperationDetail.setAction("UpdateOrCreate");
//                        standardOperationDetail.setRequestedProperties(props);
//                        BaseObjectWrapper bowDetail = objFactory.createBaseObjectWrapper();
//                        bowDetail.setObject(partsRequestDetails);
//                        standardOperationDetail.setObject(bowDetail);
//                        partsReqDtlSO.add(standardOperationDetail);
//                    }
//                    partsReqDtlList = new ArrayList<CSAPartsRequestDetails>();
//
//                    Iterator<String> ite = partsReqDetailRefListMap.keySet().iterator();
//                    while (ite.hasNext()) {
//                        String prchReqNum = ite.next();
//                        List<CSAPartsRequestDetailsReference> partsReqRefList = partsReqDetailRefListMap.get(prchReqNum);
//                        CSAPartsRequest partsRequestRef = objFactory.createCSAPartsRequest();
//                        partsRequestRef.setRequestNo(prchReqNum);
//                        com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequest.CSAPartsRequestDetails detailRef = new com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequest.CSAPartsRequestDetails();
//                        for (CSAPartsRequestDetailsReference partsReqRef : partsReqRefList) {
//                            detailRef.getCSAPartsRequestDetail().add(partsReqRef);
//                        }
//                        partsRequestRef.setCSAPartsRequestDetails(detailRef);
//                        W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                        StandardOperation standardOperationDetailRef = objFactory.createStandardOperation();
//                        standardOperationDetailRef.setAction("UpdateOrCreate");
//                        standardOperationDetailRef.setRequestedProperties(props);
//                        BaseObjectWrapper bowDetailRef = objFactory.createBaseObjectWrapper();
//                        bowDetailRef.setObject(partsRequestRef);
//                        standardOperationDetailRef.setObject(bowDetailRef);
//                        partsReqRefSO.add(standardOperationDetailRef);
//                    }
//                    partsReqDetailRefListMap = new TreeMap<String, List<CSAPartsRequestDetailsReference>>();
//
//                    // CSAPartsRequestShipment
//                    for (CSAPartsRequestShipment partsRequestShip : partsReqShipList) {
//                        W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                        StandardOperation standardOperationShip = objFactory.createStandardOperation();
//                        standardOperationShip.setAction("UpdateOrCreate");
//                        standardOperationShip.setRequestedProperties(props);
//                        BaseObjectWrapper bowDetail = objFactory.createBaseObjectWrapper();
//                        bowDetail.setObject(partsRequestShip);
//                        standardOperationShip.setObject(bowDetail);
//                        partsReqShipSO.add(standardOperationShip);
//                    }
//                    partsReqShipList = new ArrayList<CSAPartsRequestShipment>();
//                }
//
//                // CSAPartsRequest
//                if (prevTargetPrchReqNum == null || !targetPrchReqNum.equals(prevTargetPrchReqNum)) {
//                    CSAPartsRequest partsRequest = objFactory.createCSAPartsRequest();
//                    partsRequest.setRequestNo(rs.getString("PRCH_REQ_NUM"));
//                    CSAPartsRequestStatusReference partsRequestStatusReference = objFactory.createCSAPartsRequestStatusReference();
//                    partsRequestStatusReference.getContent().add(rs.getString("PRCH_REQ_STS_DESC_TXT"));
//                    partsRequest.setStatus(partsRequestStatusReference);
//                    partsRequest.setEngineer(rs.getString("TECH_TOC_CD"));
//                    partsRequest.setTaskNo(rs.getString("SVC_TASK_NUM"));
//                    partsRequest.setLastDateShipped(changeLastDateShipped(rs.getString("SHIP_DT_TM_TS")));
//                    partsRequest.setCustomer(rs.getString("DS_ACCT_NM"));
//                    String destination = rs.getString("CUST_ADDR");
//                    if (destination != null && destination.length() > 500) {
//                        destination = destination.substring(0, 500);
//                    }
//                    partsRequest.setDestination(destination);
//                    partsRequest.setWarehouse(rs.getString("RTL_WH_NM"));
//                    partsReqList.add(partsRequest);
//                }
//                prevTargetPrchReqNum = targetPrchReqNum;
//
//                if (ZYPCommonFunc.hasValue(prevTargetPrchReqLineNum) && !targetPrchReqLineNum.equals(prevTargetPrchReqLineNum)) {
//                    CSAPartsRequestDetails partsRequestDetails = objFactory.createCSAPartsRequestDetails();
//                    CSAPartsRequestDetails.ShipmentInfo shipmentInfo = objFactory.createCSAPartsRequestDetailsShipmentInfo();
//                    for (CSAPartsRequestShipmentReference partsReqShipRef : partsReqShipRefList) {
//                        shipmentInfo.getPartsShipment().add(partsReqShipRef);
//                    }
//                    partsRequestDetails = objPartsRequestDetails;
//                    partsRequestDetails.setShipmentInfo(shipmentInfo);
//                    partsReqDtlList.add(partsRequestDetails);
//
//                    partsReqShipRefList.clear();
//                    objPartsRequestDetails = objFactory.createCSAPartsRequestDetails();
//                    preTargetSoNum = null;
//
//                    sumPrchReqQty = rs.getInt("PRCH_REQ_QTY");
//                    sumShipQty = rs.getInt("SHIP_QTY");
//                    sumRwsPutAwayQty = rs.getInt("RWS_PUT_AWAY_QTY");
//                    sumBackToTechQty = rs.getInt("BACK_TO_TECH_QTY");
//                } else if (targetSoNum == null || preTargetSoNum == null || !preTargetSoNum.equals(targetSoNum)) {
//                    sumPrchReqQty = sumPrchReqQty + rs.getInt("PRCH_REQ_QTY");
//                    sumShipQty = sumShipQty + rs.getInt("SHIP_QTY");
//                    sumRwsPutAwayQty = sumRwsPutAwayQty + rs.getInt("RWS_PUT_AWAY_QTY");
//                    sumBackToTechQty = sumBackToTechQty + rs.getInt("BACK_TO_TECH_QTY");
//                }
//
//                objPartsRequestDetails.setPartNumber(rs.getString("MDSE_CD"));
//                objPartsRequestDetails.setDescription(rs.getString("MDSE_DESC_SHORT_TXT"));
//                CSAPartsRequestLineStatusReference partsRequestLineStatusReference = objFactory.createCSAPartsRequestLineStatusReference();
//                partsRequestLineStatusReference.getContent().add(rs.getString("PRCH_REQ_LINE_STS_DESC_TXT"));
//                objPartsRequestDetails.setStatus(partsRequestLineStatusReference);
//                objPartsRequestDetails.setInventorySource(rs.getString("SRC_INVTY_LOC_CD"));
//                objPartsRequestDetails.setOrderedQuantity(sumPrchReqQty);
//                objPartsRequestDetails.setShippedQuantity(sumShipQty);
//                objPartsRequestDetails.setDeliveredQuantity(sumRwsPutAwayQty);
//                objPartsRequestDetails.setBackOrderQuantity(sumBackToTechQty);
//                objPartsRequestDetails.setRequestNo(targetPrchReqNum);
//                objPartsRequestDetails.setLineNumber(createLineNumber(rs));
//
//                if (ZYPCommonFunc.hasValue(partsRequestShipment.getRequestNo()) && (!ZYPCommonFunc.hasValue(targetSoNum) || !targetSoNum.equals(preTargetSoNum))) {
//                    CSAPartsRequestShipment partsReqShip = objFactory.createCSAPartsRequestShipment();
//                    TrackingDetails trackingdetails = objFactory.createCSAPartsRequestShipmentTrackingDetails();
//                    for (TrackingDetails1 tracking : trackingDetailsList) {
//                        trackingdetails.getTrackingDetails().add(tracking);
//                    }
//                    partsReqShip = partsRequestShipment;
//                    partsReqShip.setTrackingDetails(trackingdetails);
//                    partsReqShipList.add(partsReqShip);
//
//                    partsRequestShipment = objFactory.createCSAPartsRequestShipment();
//                    trackingDetailsList.clear();
//                }
//
//                // TrackingDetails
//                TrackingDetails1 trackingDetails1 = objFactory.createCSAPartsRequestShipmentTrackingDetailsTrackingDetails();
//                trackingDetails1.setTrackingNo(rs.getString("PRO_NUM"));
//                trackingDetails1.setTrackingURL(editCarrTrkUrl(rs.getString("CARR_TRK_URL"), rs.getString("PRO_NUM")));
//                CSACarrierReference carrierReference = objFactory.createCSACarrierReference();
//                carrierReference.getContent().add(rs.getString("CARR_TP_NM"));
//                trackingDetails1.setCarrier(carrierReference);
//                String eta = ZYPDateUtil.DateFormatter(rs.getString("RQST_RCV_DT"), ZYPDateUtil.TYPE_YYYYMMDD, DT_FORMAT);
//                trackingDetails1.setETA(eta + " " + "00:00:00");
//                trackingDetailsList.add(trackingDetails1);
//
//                if (ZYPCommonFunc.hasValue(curSoNum) && !curSoNum.equals(prevSoNum)) {
//
//                    List<Map<String, Object>> proNumList = getProNumList(curSoNum);
//
//                    for (Map<String, Object> proNum : proNumList) {
//                        TrackingDetails1 additionalTrackingDetails1 = objFactory.createCSAPartsRequestShipmentTrackingDetailsTrackingDetails();
//                        additionalTrackingDetails1.setTrackingNo((String) proNum.get("PRO_NUM"));
//                        additionalTrackingDetails1.setTrackingURL(editCarrTrkUrl(rs.getString("CARR_TRK_URL"), (String) proNum.get("PRO_NUM")));
//                        CSACarrierReference additionalCarrierReference = objFactory.createCSACarrierReference();
//                        carrierReference.getContent().add(rs.getString("CARR_TP_NM"));
//                        additionalTrackingDetails1.setCarrier(additionalCarrierReference);
//                        additionalTrackingDetails1.setETA(eta + " " + "00:00:00");
//                        trackingDetailsList.add(additionalTrackingDetails1);
//                    }
//                }
//
//                if (!ZYPCommonFunc.hasValue(preTargetSoNum) || !ZYPCommonFunc.hasValue(targetSoNum) || !targetSoNum.equals(preTargetSoNum)) {
//                    // CSAPartsRequestDetailsReference
//                    CSAPartsRequestDetailsReference partsRequestDetailsRef = objFactory.createCSAPartsRequestDetailsReference();
//                    partsRequestDetailsRef.getContent().add(objFactory.createCSAPartsRequestDetailsReferenceRequestNo(targetPrchReqNum));
//                    partsRequestDetailsRef.getContent().add(objFactory.createCSAPartsRequestDetailsReferencePartNumber(rs.getString("MDSE_CD")));
//                    partsRequestDetailsRef.getContent().add(objFactory.createCSAPartsRequestDetailsReferenceLineNumber(createLineNumber(rs)));
//                    List<CSAPartsRequestDetailsReference> partsReqRefList = null;
//                    if (partsReqDetailRefListMap.containsKey(targetPrchReqNum)) {
//                        partsReqRefList = partsReqDetailRefListMap.get(targetPrchReqNum);
//                    } else {
//                        partsReqRefList = new ArrayList<CSAPartsRequestDetailsReference>();
//                    }
//                    partsReqRefList.add(partsRequestDetailsRef);
//                    partsReqDetailRefListMap.put(targetPrchReqNum, partsReqRefList);
//
//                    // CSAPartsRequestShipment
//                    partsRequestShipment.setRequestNo(targetPrchReqNum);
//                    partsRequestShipment.setPartNumber(rs.getString("MDSE_CD"));
//                    partsRequestShipment.setLineNumber(createLineNumber(rs));
//                    partsRequestShipment.setShipOrderNumber(targetSoNum);
//                    int shipQty = rs.getInt("SHIP_QTY");
//                    partsRequestShipment.setShippedQuantity(shipQty);
//
//                    // CSAPartsRequestShipmentReference
//                    CSAPartsRequestShipmentReference partsRequestShipmentRef = objFactory.createCSAPartsRequestShipmentReference();
//                    partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceRequestNo(targetPrchReqNum));
//                    partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferencePartNumber(rs.getString("MDSE_CD")));
//                    partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceLineNumber(createLineNumber(rs)));
//                    partsRequestShipmentRef.getContent().add(objFactory.createCSAPartsRequestShipmentReferenceShipOrderNumber(targetSoNum));
//                    partsReqShipRefList.add(partsRequestShipmentRef);
//                }
//
//                prevTechId = techId;
//                prevTargetPrchReqNum = targetPrchReqNum;
//                prevTargetPrchReqLineNum = targetPrchReqLineNum;
//                preTargetSoNum = targetSoNum;
//                this.normalCount++;
//
//                if (ZYPCommonFunc.hasValue(curSoNum)) {
//                    prevSoNum = curSoNum;
//                }
//            }
//
//            if (this.normalCount > 0) {
//                CSAPartsRequestShipment partsReqShip = objFactory.createCSAPartsRequestShipment();
//                TrackingDetails trackingdetails = objFactory.createCSAPartsRequestShipmentTrackingDetails();
//                for (TrackingDetails1 tracking : trackingDetailsList) {
//                    trackingdetails.getTrackingDetails().add(tracking);
//                }
//                partsReqShip = partsRequestShipment;
//                partsReqShip.setTrackingDetails(trackingdetails);
//                partsReqShipList.add(partsReqShip);
//
//                CSAPartsRequestDetails partsRequestDetails = objFactory.createCSAPartsRequestDetails();
//                CSAPartsRequestDetails.ShipmentInfo shipmentInfo = objFactory.createCSAPartsRequestDetailsShipmentInfo();
//                for (CSAPartsRequestShipmentReference partsReqShipRef : partsReqShipRefList) {
//                    shipmentInfo.getPartsShipment().add(partsReqShipRef);
//                }
//                partsRequestDetails = objPartsRequestDetails;
//                partsRequestDetails.setShipmentInfo(shipmentInfo);
//                partsReqDtlList.add(partsRequestDetails);
//            }
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        }
//
//        if (partsReqSO.size() + partsReqList.size() > wmbMaxCount.intValue()) {
//            for (StandardOperation standardOperation : partsReqSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//            for (StandardOperation standardOperation : partsReqDtlSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//            for (StandardOperation standardOperation : partsReqRefSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//            for (StandardOperation standardOperation : partsReqShipSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//            // Call WMB API
//            callWmbApi(standardOperations);
//            standardOperations = objFactory.createStandardOperations();
//            partsReqSO = new ArrayList<StandardOperation>();
//            partsReqDtlSO = new ArrayList<StandardOperation>();
//            partsReqRefSO = new ArrayList<StandardOperation>();
//            partsReqShipSO = new ArrayList<StandardOperation>();
//        }
//
//        if (partsReqSO.size() + partsReqList.size() > 0) {
//            // CSAPartsRequest
//            for (StandardOperation standardOperation : partsReqSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//            for (CSAPartsRequest partsRequest : partsReqList) {
//                W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                StandardOperation standardOperation = objFactory.createStandardOperation();
//                standardOperation.setAction("UpdateOrCreate");
//                standardOperation.setRequestedProperties(props);
//                BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
//                bow.setObject(partsRequest);
//                standardOperation.setObject(bow);
//                standardOperations.getOperation().add(standardOperation);
//            }
//
//            // CSAPartsRequestDetails
//            for (StandardOperation standardOperation : partsReqDtlSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//            for (CSAPartsRequestDetails partsRequestDetails : partsReqDtlList) {
//                W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                StandardOperation standardOperationDetail = objFactory.createStandardOperation();
//                standardOperationDetail.setAction("UpdateOrCreate");
//                standardOperationDetail.setRequestedProperties(props);
//                BaseObjectWrapper bowDetail = objFactory.createBaseObjectWrapper();
//                bowDetail.setObject(partsRequestDetails);
//                standardOperationDetail.setObject(bowDetail);
//                standardOperations.getOperation().add(standardOperationDetail);
//            }
//
//            for (StandardOperation standardOperation : partsReqRefSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//
//            Iterator<String> ite = partsReqDetailRefListMap.keySet().iterator();
//            while (ite.hasNext()) {
//                String prchReqNum = ite.next();
//                List<CSAPartsRequestDetailsReference> partsReqRefList = partsReqDetailRefListMap.get(prchReqNum);
//                CSAPartsRequest partsRequestRef = objFactory.createCSAPartsRequest();
//                partsRequestRef.setRequestNo(prchReqNum);
//                com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequest.CSAPartsRequestDetails detailRef = new com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsRequest.CSAPartsRequestDetails();
//                for (CSAPartsRequestDetailsReference partsReqRef : partsReqRefList) {
//                    detailRef.getCSAPartsRequestDetail().add(partsReqRef);
//                }
//                partsRequestRef.setCSAPartsRequestDetails(detailRef);
//                W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                StandardOperation standardOperationDetailRef = objFactory.createStandardOperation();
//                standardOperationDetailRef.setAction("UpdateOrCreate");
//                standardOperationDetailRef.setRequestedProperties(props);
//                BaseObjectWrapper bowDetailRef = objFactory.createBaseObjectWrapper();
//                bowDetailRef.setObject(partsRequestRef);
//                standardOperationDetailRef.setObject(bowDetailRef);
//                standardOperations.getOperation().add(standardOperationDetailRef);
//            }
//
//            // CSAPartsRequestShipment
//            for (StandardOperation standardOperation : partsReqShipSO) {
//                standardOperations.getOperation().add(standardOperation);
//            }
//            for (CSAPartsRequestShipment partsReqShip : partsReqShipList) {
//                W6RequestedProperties props = objFactory.createW6RequestedProperties();
//                StandardOperation standardOperationShip = objFactory.createStandardOperation();
//                standardOperationShip.setAction("UpdateOrCreate");
//                standardOperationShip.setRequestedProperties(props);
//                BaseObjectWrapper bowDetail = objFactory.createBaseObjectWrapper();
//                bowDetail.setObject(partsReqShip);
//                standardOperationShip.setObject(bowDetail);
//                standardOperations.getOperation().add(standardOperationShip);
//            }
//
//            // Call WMB API
//            callWmbApi(standardOperations);
//        }
//        // mod end 2016/09/05 CSA Defect#13891
//    }
    // Del End 2018/04/09 QC#23804

    private String changeLastDateShipped(String dateStr) {
        String changeDateStr = null;
        try {
            if (!ZYPCommonFunc.hasValue(dateStr)) {
                return changeDateStr;
            }
            SimpleDateFormat dateFmt = new SimpleDateFormat(DT_FORMAT_TS);
            Date formatDate = dateFmt.parse(dateStr);
            changeDateStr = new SimpleDateFormat(this.lastDateShippedFormat).format(formatDate);

        } catch (ParseException e) { // Impossible pattern :2016/01/26 comment add
            return null;
        }
        return changeDateStr;
    }

    private XMLGregorianCalendar changeEtaDate(String dateStr) {
        XMLGregorianCalendar xmlgc = null;
        try {
            if (!ZYPCommonFunc.hasValue(dateStr)) {
                return xmlgc;
            }
            // Date --> GregorianCalendar
            SimpleDateFormat format = new SimpleDateFormat(this.etaDateFormat);

            ParsePosition pos = new ParsePosition(0);
            Date formatDate = format.parse(dateStr, pos);
            if (formatDate == null) {
                return xmlgc;
            } else {
                GregorianCalendar calendar = new GregorianCalendar();
                DatatypeFactory factory = DatatypeFactory.newInstance();
                Calendar parsedCalendar = Calendar.getInstance();
                parsedCalendar.setTime(formatDate);

                // set date and time from time parameter and
                calendar.set(parsedCalendar.get(Calendar.YEAR), parsedCalendar.get(Calendar.MONTH), parsedCalendar.get(Calendar.DATE), parsedCalendar.get(Calendar.HOUR_OF_DAY), parsedCalendar.get(Calendar.MINUTE), parsedCalendar
                        .get(Calendar.SECOND));
                xmlgc = factory.newXMLGregorianCalendar(calendar);
            }
            return xmlgc;
        } catch (DatatypeConfigurationException re) {
            return xmlgc;
        }
    }

    private void callWmbApi(StandardOperations standardOperations) {

        // Add Start 2018/06/01 QC#26112 Delete either
        // For Performance
        S21StopWatch sw = new S21StopWatch();
        try {
            sw.start();
            // Add End 2018/06/01 QC#26112 Delete either

            // START 2017/12/08 M.Naito [QC#18572,ADD]
            this.totalCount = this.totalCount + this.normalCount;
            // END 2017/12/08 M.Naito [QC#18572,ADD]
            ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
            emo.setOneTransaction(true);
            emo.setContinueOnError(true);
            emo.setOperations(standardOperations);

            // START 2016/01/26 T.Iwamoto [QC#2991, DEL]
            // @SuppressWarnings("unused")
            ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();
            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]
            // START 2017/12/08 M.Naito [QC#18572,ADD]
            this.normalCount = 0;
            // END 2017/12/08 M.Naito [QC#18572,ADD]
        } catch (Exception e) {
            this.termCd = TERM_CD.WARNING_END;
            this.errorMessages = setErrorInfo(NPAM1481E, null);
            // START 2017/12/08 M.Naito [QC#18572,MOD]
            this.errorCount = this.normalCount;
            // END 2017/12/08 M.Naito [QC#18572,MOD]

             e.printStackTrace();
            return;
        } catch (Throwable t) {
            this.termCd = TERM_CD.WARNING_END;
            this.errorMessages = setErrorInfo(NPAM1481E, null);

             t.printStackTrace();
            // END 2016/01/26 T.Iwamoto [QC#2991, DEL]
            return;
        }
        // Add Start 2018/06/01 QC#26112 Delete either
        // For Performance
        finally {
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_API:executeMultipleOperations]End [%s]", sw.getElapsedMilliSec()));
        }
        // Add End 2018/06/01 QC#26112 Delete either
    }

    private S21SsmExecutionParameter setExecParam() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        return execParam;
    }

    private void updateTechInvtyAgingWrk(boolean normalFlg) {

        ResultSet rs = null;
        PreparedStatement stmt = null;

        List<TECH_RQST_LIST_WRKTMsg> inTMsgList = new ArrayList<TECH_RQST_LIST_WRKTMsg>();
        Map<String, String> inParam = new HashMap<String, String>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("techProcStsW", TECH_PROC_STS.WAITING_FOR_PROCESS);
        // START 2017/01/11 K.Kitachi [QC#17024, ADD]
        inParam.put("asterisk", "*");
        // END 2017/01/11 K.Kitachi [QC#17024, ADD]

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getTechnicianRequestInfo", inParam, setExecParam());
            rs = stmt.executeQuery();

            while (rs.next()) {
                TECH_RQST_LIST_WRKTMsg inTMsg = new TECH_RQST_LIST_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.techRqstListWrkPk, rs.getBigDecimal("TECH_RQST_LIST_WRK_PK"));

                TECH_RQST_LIST_WRKTMsg updateTiaw = (TECH_RQST_LIST_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);
                if (normalFlg) {
                    ZYPEZDItemValueSetter.setValue(updateTiaw.techProcStsCd, TECH_PROC_STS.PROCESSED);
                } else {
                    ZYPEZDItemValueSetter.setValue(updateTiaw.techProcStsCd, TECH_PROC_STS.ERROR);
                }
                ZYPEZDItemValueSetter.setValue(updateTiaw.techProcDtTmTs, this.currentSystemTs);
                inTMsgList.add(updateTiaw);

                if (this.commitNumber == inTMsgList.size()) {
                    executionUpdate(inTMsgList);
                    inTMsgList = new ArrayList<TECH_RQST_LIST_WRKTMsg>();
                }
            }

            if (inTMsgList.size() > 0) {
                executionUpdate(inTMsgList);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void executionUpdate(List<TECH_RQST_LIST_WRKTMsg> inTMsgList) {

        TECH_RQST_LIST_WRKTMsg[] inTMsgArray = new TECH_RQST_LIST_WRKTMsg[inTMsgList.size()];
        S21FastTBLAccessor.update(inTMsgList.toArray(inTMsgArray));
        commit();
        return;
    }

    private void sendErrorMail() {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NPZM0125E);
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NPZM0125E);
        }

        // 3. Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPZM0128E);
        }

        // 4. Create message for Body
        S21MailAddress fromAddress = addrFromList.get(0);
        S21Mail mail = new S21Mail(glblCmpyCd);

        // 5. Create Subject and Body
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(addrToList);
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT));
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, this.errorMessages);

        // 6. Call Mail API
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject(), MAIL_CHARSET);
        mail.postMail();
    }

    /**
     * set error info
     * @param msgId
     * @param apiBizId
     */
    private String setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        return S21MessageFunc.clspGetMessage(msgId, params);
    }

    @Override
    protected void termRoutine() {
        // START 2017/12/08 M.Naito [QC#18572,MOD]
        setRecordCount((totalCount - errorCount), errorCount, totalCount);
        // END 2017/12/08 M.Naito [QC#18572,MOD]
        setTermState(termCd);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NPAB166001().executeBatch(NPAB166001.class.getSimpleName());
    }

    // Del Start 2018/04/09 QC#23804
//    // START 2017/12/19 M.Naito [QC#18572,MOD]
//    // START 2016/11/08 K.Kojima [QC#14445,ADD]
//    /**
//     * createLineNumber
//     * @param rs ResultSet
//     * @return
//     */
//    private String createLineNumber(ResultSet rs) throws SQLException {
//        String lineNum = "";
//        if (ZYPCommonFunc.hasValue(rs.getString("PRCH_REQ_LINE_NUM"))) {
//            lineNum = rs.getString("PRCH_REQ_LINE_NUM");
//            if (ZYPCommonFunc.hasValue(rs.getString("PRCH_REQ_LINE_SUB_NUM"))) {
//                lineNum = lineNum + "." + rs.getString("PRCH_REQ_LINE_SUB_NUM");
//            }
//        }
//        return lineNum;
//    }
//    // END 2016/11/08 K.Kojima [QC#14445,ADD]
//
//    /**
//     * createTargetPrchReqLineNum
//     * @param rs ResultSet
//     * @return
//     */
//    private String createTargetPrchReqLineNum(ResultSet rs) throws SQLException {
//        return rs.getString("PRCH_REQ_NUM") + "." + rs.getString("PRCH_REQ_LINE_NUM");
//    }
//
//    // END 2017/12/19 M.Naito [QC#18572,MOD]
    // Del End 2018/04/09 QC#23804

    // QC#21913 T.Hakodate ADD START
    private List<Map<String, Object>> getProNumList(String soNum, String proNum) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("soNum", soNum);
        param.put("proNum", proNum);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getProNumList", param);

    }
    // QC#21913 T.Hakodate ADD END
    // QC#55750 Add Start
    private PRCH_REQTMsg getPrchReq(String prchReqNum) {

    	PRCH_REQTMsg inMsg = new PRCH_REQTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.prchReqNum, prchReqNum);
        PRCH_REQTMsg outMsg = (PRCH_REQTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }
    /**
     * changeCloseDate
     * @param dateStr
     * @return
     */
    private XMLGregorianCalendar changeCloseDate(String dateStr) {
        if (!ZYPCommonFunc.hasValue(dateStr)) {
            return null;
        }
        if (dateStr.length() < this.etaDateFormat.length()) {
            return null;
        }
        return changeEtaDate(dateStr.substring(0, this.etaDateFormat.length()));
    }

    private List<Map<String, Object>> getProNumListMnx(String soNum, String proNum) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("soNum", soNum);
        param.put("proNum", proNum);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getProNumListMnx", param);

    }
    // QC#55750 Add End

    // QC#58372 Add Start
    private boolean isDuplicate(TECH_RQST_LIST_WRKTMsg prev, TECH_RQST_LIST_WRKTMsg curr) {

    	// same PrchReqNum ?
        String prevPrchReqNum = prev.prchReqNum.getValue();
        String currPrchReqNum = curr.prchReqNum.getValue();
        if (!prevPrchReqNum.equals(currPrchReqNum)) {
            return false;
        }

    	// same PrchReqLineNum ?
        String prevPrchReqLineNum = prev.prchReqLineNum.getValue();
        String currPrchReqLineNum = curr.prchReqLineNum.getValue();
        if (!prevPrchReqLineNum.equals(currPrchReqLineNum)) {
            return false;
        }

    	// same PrchReqLineNum + PrchReqLineSubNum  ?
        if (ZYPCommonFunc.hasValue(prev.prchReqLineSubNum)) {
            prevPrchReqLineNum = prevPrchReqLineNum  + "." +  prev.prchReqLineSubNum.getValue().toPlainString();
        }
        if (ZYPCommonFunc.hasValue(curr.prchReqLineSubNum)) {
            currPrchReqLineNum = currPrchReqLineNum  + "." +  curr.prchReqLineSubNum.getValue().toPlainString();
        }
        if (prevPrchReqLineNum.equals(currPrchReqLineNum)) {
            return true;
        }
        return false;
    }

    private String getPrchReqLineSts(String prchReqNum, String prchReqLineNum) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("prchReqNum", prchReqNum);
        param.put("prchReqLineNum", prchReqLineNum);
        param.put("npab1650PrchLine", "NPAB1650_PRCH_LINE");
        return (String) this.ssmBatchClient.queryObject("getPrchReqLineSts", param);

    }
    // QC#58372 Add End
    // QC#58372-2 Add Start
    private boolean isSameShipOrd(TECH_RQST_LIST_WRKTMsg current, TECH_RQST_LIST_WRKTMsg prev) {
        if (!ZYPCommonFunc.hasValue(current.techRqstSoNum) || !ZYPCommonFunc.hasValue(prev.techRqstSoNum)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(current.prchReqLineSubNum) || !ZYPCommonFunc.hasValue(prev.prchReqLineSubNum)) {
            return false;
        }
        if (
                current.prchReqNum.getValue().equals(prev.prchReqNum.getValue())
                && current.prchReqLineNum.getValue().equals(prev.prchReqLineNum.getValue())
                && current.prchReqLineSubNum.getValue().equals(prev.prchReqLineSubNum.getValue())
                && current.techRqstSoNum.getValue().equals(prev.techRqstSoNum.getValue())
                ) {
            return true;
        }
        
        return false;
    }
    private Map<String, Object> setTrackingInfo (TECH_RQST_LIST_WRKTMsg tMsg) {
        Map<String, Object> trackingInfo = new HashMap<String, Object>();
        trackingInfo.put("PRO_NUM", "");
        if (ZYPCommonFunc.hasValue(tMsg.proNum)) {
            trackingInfo.put("PRO_NUM", tMsg.proNum.getValue());
            
        }
        trackingInfo.put("CARR_TRK_URL", "");
        if (ZYPCommonFunc.hasValue(tMsg.carrTrkUrl)) {
            trackingInfo.put("CARR_TRK_URL", tMsg.carrTrkUrl.getValue());
            
        }
        trackingInfo.put("CARR_TP_NM", "");
        if (ZYPCommonFunc.hasValue(tMsg.carrTpNm)) {
            trackingInfo.put("CARR_TP_NM", tMsg.carrTpNm.getValue());
            
        }
        trackingInfo.put("RQST_RCV_DT", "");
        if (ZYPCommonFunc.hasValue(tMsg.rqstRcvDt)) {
            trackingInfo.put("RQST_RCV_DT", tMsg.rqstRcvDt.getValue());
            
        }
        trackingInfo.put("RQST_RCV_TM", "");
        if (ZYPCommonFunc.hasValue(tMsg.rqstRcvTm)) {
            trackingInfo.put("RQST_RCV_TM", tMsg.rqstRcvTm.getValue());
            
        }
        
        return trackingInfo;
    }
    // QC#58372-2 Add End

    // START 2022/10/19 [QC#60712, ADD]
    /** OFS Multi Byte Convert Flag */
    private String ofsMultiByteConvert(String inStr) {
        if (this.ofsMultiByteConvertFlg == false) {
            return inStr;
        }
        String outStr = ZYPCharacterConversionUtil.convertCharacter(inStr);
        return outStr;
    }
    // END   2022/10/19 [QC#60712, ADD]
}
