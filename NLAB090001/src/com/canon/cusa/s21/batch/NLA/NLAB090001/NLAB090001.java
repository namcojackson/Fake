/*
 * <Pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLA.NLAB090001;

import static com.canon.cusa.s21.batch.NLA.NLAB090001.constant.NLAB090001Constant.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.NLAI2410_01TMsg;
import business.db.NLAI2410_02TMsg;
import business.db.NLAI2430_01TMsg;
import business.db.NLAI2430_02TMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_MSGTMsg;
import business.db.RWS_MSGTMsgArray;
import business.db.WMS_INBD_ITEM_SER_WRKTMsg;
import business.db.WMS_INBD_ITEM_UPC_WRKTMsg;
import business.db.WMS_INBD_ITEM_WRKTMsg;
import business.db.WMS_INBD_MDSETMsg;
import business.db.WMS_INBD_MDSE_SERTMsg;
import business.db.WMS_INBD_MDSE_UPCTMsg;
import business.db.WMS_INBD_PO_DTLTMsg;
import business.db.WMS_INBD_PO_HDRTMsg;
import business.db.WMS_INBD_PO_VNDTMsg;
import business.db.WMS_MDSE_LISTTMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NLG.NLGC002001.NLGC002001;
import com.canon.cusa.s21.api.NLG.NLGC002001.NLGC002001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetMdseRelationshipData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ITEM_DNLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2410.data.NglASNRequestType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2410.data.NglASNResponse;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2410.data.OrderItemType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2410.data.OrderItemsType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2410.data.StatusType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2410.wrapper.NglASNServiceProxy;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.AlternatePartType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.AlternatePartsType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.NglPartMasterRequestType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.NglPartMasterResponse;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.PartMasterItemType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.PartMasterItemsType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.PartType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.PartsType;
import com.canon.usa.s21.integration.service.mnx.integration.nlai2430.wrapper.NglPartMasterServiceProxy;


/**
 * <pre>
 * ASN (Advanced Ship Notification) to MNX.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/05/2020   CITS            R.Mercado       Create          QC#57659
 * 12/21/2020   CITS            K.Ogino         Update          QC#58137
 * 01/26/2021   CITS            A.Marte         Update          QC#58137
 * 2021/04/16   CITS            K.Ogino         Update          QC#58256
 * 2023/03/03   Hitachi         T.Kuroda        Update          QC#61044
 * 
 *</pre>
 */

public class NLAB090001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd;

    /** RWS Interface ID */
    private String interfaceId01;

    /** MDSE Interface ID */
    private String interfaceId02;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList = new ArrayList<Map<String, String>>();

    /** Termination code */
    private TERM_CD termCd;

    /** The number of success */
    private int successCount;

    /** The number of error */
    private int errorCount;

    /** Commit Count */
    private int commitCount = 0;

    /** WH Owner */
    private String whOwnrCd;

    /** Transaction Id for PO IF Table */
    private BigDecimal poTrxId = null;

    /** Transaction Id for MDSE IF Table */
    private BigDecimal mdseTrxId = null;

    /** Unit Id for PO IF Table */
    private BigDecimal poUnitId = BigDecimal.ONE;

    /** Unit Id for MDSE IF Table */
    private BigDecimal mdseUnitId = BigDecimal.ONE;

    /** data cache for Supersede Item */
    private Map<String, ArrayList<String>> supersedeItemMap = null;

    /** Sales Date */
    private String salesDate = null;

    // QC#58137 Add Start
    private boolean isAbend = false;

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList4User = new ArrayList<Map<String, String>>();

    /** Error Massage List */
    private ArrayList<Map<String, String>> errMsgList4It = new ArrayList<Map<String, String>>();
    // QC#58137 Add End

    // START 2020/01/26 A.Marte [QC#58281, ADD]
    /** Error Items List */
    private ArrayList<Map<String, String>> errItemList = new ArrayList<Map<String, String>>();
    // END 2020/01/26 A.Marte [QC#58281, END]

    // QC#58256
    private String otherCarrNm = null;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLAB090001().executeBatch(NLAB090001.class.getSimpleName());
    }

    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of variable
        successCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();

        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAM1480E);
        }

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        this.whOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_TARGET_ORDER, this.glblCmpyCd);

        // QC#58137
        String constVal = ZYPCodeDataUtil.getVarCharConstValue("NLAB0900_SYSERR_ABEND_FLG", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constVal) && ZYPConstant.FLG_ON_Y.equals(constVal)) {
            this.isAbend = true;
        }
        // QC#58256
        constVal = ZYPCodeDataUtil.getVarCharConstValue("NLAB0900_OTHER_CARR_NM", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constVal)) {
            this.otherCarrNm = constVal;
        } else {
            this.otherCarrNm = "OTHER";
        }

        // Check on input parameter
        checkParameter();
    }

    protected void mainRoutine() {

        try {

            // Create RWS Interface data.
            createRwsInterface();

            // Create MNX order requests.
            createMnxRequests(interfaceId02);
            createMnxRequests(interfaceId01);

            commit();

        } catch (S21AbendException e) {

            rollback();
            throw e;

        } finally {
            if (errMsgList.size() > 0) {
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList);
                commit();
            }
            // QC#58137
            if (errMsgList4It.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList4It, new String[]{"MNXAPIM001"});
                commit();
            }
            if (errMsgList4User.size() > 0) {
                termCd = TERM_CD.WARNING_END;
                NLXMailSend mail = new NLXMailSend(glblCmpyCd);
                mail.send(BUSINESS_ID, errMsgList4User, new String[]{"MNXAPIM002"});
                commit();
            }
        }
    }

    protected void termRoutine() {

        // The number of total
        int totalCount = successCount + errorCount;
        S21InfoLogOutput.println(ZZBM0009I, new String[] {interfaceId01, "Insert", Integer.toString(totalCount) });

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, totalCount);

    }

    private void createMnxRequests(String ifId) {

        // Get transaction ID
        BigDecimal[] transactionIdList = trxAccess.getIntegrationRecord(ifId);

        for (BigDecimal transactionId : transactionIdList) {
            createMnxOrderForTransactionId(transactionId, ifId);
            trxAccess.endIntegrationProcess(ifId, transactionId);
        }

        commit();
    }

    private void createMnxOrderForTransactionId(BigDecimal transactionId, String ifId) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(FETCH_SIZE);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(COL_INTERFACE_ID, ifId);
        queryParam.put(COL_TRANSACTION_ID, transactionId);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            if (ifId.equals(IF_NLAI2410)) {
                // Get Interface Data
                prdStmt = ssmLLClient.createPreparedStatement("getIfDataNlai2410", queryParam, execParam);
                rs = prdStmt.executeQuery();
                processAsnRequest(transactionId, rs);
            } else if (ifId.equals(IF_NLAI2430)) {
                // Get Interface Data
                prdStmt = ssmLLClient.createPreparedStatement("getIfDataNlai2430", queryParam, execParam);
                rs = prdStmt.executeQuery();
                processPartMasterRequest(transactionId, rs);
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        // QC#58137
        } catch (S21AbendException e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            // QC#58137            
            if (isAbend) {
                throw new S21AbendException(e.getMessage());
            } else {
                // START 2021/01/26 A.Marte [QC#58281, MOD]
                outputMnxErrKeyIt("NLBM1257E", e.getMessage(), transactionId, ifId, errItemList);
                // END 2021/01/26 A.Marte [QC#58281, MOD]
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    private void processAsnRequest(BigDecimal transactionId, ResultSet rs) throws Throwable {
        // Create Order Request
        NglASNResponse response = null;
        NglASNRequestType request = createAsnRequest(rs);

        // Send Order Request
        response = sendAsnRequest(request);

        StatusType stType = response.getStatus();
        // QC#58137 Start
        if (VAL_MNX_RESPONSE_TYPE_ERROR.equals(stType.getResponseType())) {
            List<OrderItemType> itemList = request.getOrderItems().getOrderItem();
            String errCd = stType.getCode();
            String msgWithRefNum = null;

            for (OrderItemType item : itemList) {
                msgWithRefNum = stType.getMessage() + " TransactionId:[" + transactionId + "]" + " Ref#:[" + item.getRef() + "]";
                if (ZYPCommonFunc.hasValue(errCd)) {
                    Map<String, Object> errMap = getMnxErrCond(glblCmpyCd, errCd);
                    if (errMap != null && !errMap.isEmpty()) {

                        String flAbend = (String) errMap.get("FL_ABEND");
                        String mlTp = (String) errMap.get("ML_TP");

                        if (ZYPCommonFunc.hasValue(flAbend) && ZYPConstant.FLG_ON_Y.equals(flAbend)) {
                            S21InfoLogOutput.println(errCd + " " + msgWithRefNum);
                            throw new S21AbendException(errCd + " : " + msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "0".equals(mlTp)){
                            outputMnxErrIt(errCd, msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "1".equals(mlTp)){
                            outputMnxErrUser(errCd, msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "2".equals(mlTp)){
                            outputMnxErrIt(errCd, msgWithRefNum);
                            outputMnxErrUser(errCd, msgWithRefNum);
                        } else {
                            outputMnxErrIt(errCd, msgWithRefNum);
                        }

                    } else {
                        outputMnxErrIt(errCd, msgWithRefNum);
                    }

                } else {
                    outputMnxErrIt("UNKNOWN", msgWithRefNum);
                }
            }
        }
        // QC#58137 End
    }

    private NglASNRequestType createAsnRequest(ResultSet rs) throws SQLException {

        // nglcreateOrderRequest
        NglASNRequestType request = new NglASNRequestType();

        OrderItemsType orderItems = new OrderItemsType();
        List<OrderItemType> orderList = orderItems.getOrderItem();

        while (rs.next()) {
            // Order Information
            OrderItemType orderItem = new OrderItemType();

            orderItem.setFslId(rs.getString(COL_TPL_LOC_TXT));
            if (ZYPCommonFunc.hasValue(rs.getString(COL_QTY_ORD_TXT))) {
                orderItem.setPartQuantity(Integer.parseInt(rs.getString(COL_QTY_ORD_TXT)));
            }
            orderItem.setField1(rs.getString(COL_ITEM_CD_TXT));
            orderItem.setDeliveryDate(rs.getString(COL_RQST_SHIP_DT_TM_TS_TXT));
            orderItem.setDeliveryTime(rs.getString(COL_REQ_DT_TM_TS_TXT));

            orderItem.setTrackingNum(rs.getString(COL_IMPT_INV_BOL_NUM));
            orderItem.setRef(rs.getString(COL_ORD_ID_TXT));
            orderItem.setCarrierName(rs.getString(COL_CARR_NM));

            orderList.add(orderItem);

            // START 2021/01/26 A.Marte [QC#58281, ADD]
            // Save Segment and Order for Error Info
            Map<String, String> orderParam = new HashMap<String, String>();
            orderParam.put(COL_SEGMENT_ID, rs.getString(COL_SEGMENT_ID));
            orderParam.put(COL_ORD_ID_TXT, rs.getString(COL_ORD_ID_TXT));
            errItemList.add(orderParam);
            // END 2021/01/26 A.Marte [QC#58281, ADD]
        }

        // Set request parameters.
        request.setOrderItems(orderItems);

        return request;
    }

    private NglASNResponse sendAsnRequest(NglASNRequestType request) throws Throwable {

        NglASNResponse response = null;

        try {
            String invokeMode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_MNX_INVOKE_MODE, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(invokeMode) && VAL_MNX_INVOKE_MODE_STUB_SUCCESS.equals(invokeMode)) {
                response = stub_stiShippingOrder_Success(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && VAL_MNX_INVOKE_MODE_STUB_ERROR.equals(invokeMode)) {
                response = stub_stiShippingOrder_ApplicationError(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && VAL_MNX_INVOKE_MODE_STUB_TIMEOUT.equals(invokeMode)) {
                throw new Exception("Timeout expired. The timeout period elapsed prior to completion of the operation or the server is not responding.");
            } else {
                NglASNServiceProxy soServiceProxy = new NglASNServiceProxy();
                response = soServiceProxy.nglASN(request);
            }
        } catch (Throwable t) {
            throw t;
        }

        return response;
    }

    // Stub method for local test
    private static NglASNResponse stub_stiShippingOrder_Success(NglASNRequestType request) {

        StatusType stType = new StatusType();
        stType.setResponseType(VAL_MNX_RESPONSE_TYPE_SUCCESS);
        stType.setCode("");
        stType.setMessage("");

        NglASNResponse response = new NglASNResponse();
        response.setStatus(stType);

        return response;
    }

    // Stub method for local test
    private static NglASNResponse stub_stiShippingOrder_ApplicationError(NglASNRequestType request) {

        StatusType stType = new StatusType();
        stType.setResponseType(VAL_MNX_RESPONSE_TYPE_ERROR);
        stType.setCode("NE0063");
        stType.setMessage("Value of 'FslId' must be between 1 and 10 characters.");

        NglASNResponse response = new NglASNResponse();
        response.setStatus(stType);

        return response;
    }

    private void processPartMasterRequest(BigDecimal transactionId, ResultSet rs) throws Throwable {
        // Create Order Request
        NglPartMasterResponse response = null;
        NglPartMasterRequestType request = createPartMasterRequest(rs);

        // Send Order Request
        response = sendPartMasterRequest(request);

        com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.StatusType stType = response.getStatus();
        if (VAL_MNX_RESPONSE_TYPE_ERROR.equals(stType.getResponseType())) {
            List<PartType> partList = request.getParts().getPart();
            // QC#58137 Start
            String errCd = stType.getCode();
            String msgWithRefNum = null;

            for (PartType part : partList) {
                msgWithRefNum = stType.getMessage() + " TransactionId:[" + transactionId + "]" + " Field1#:[" + part.getField1() + "]";
                if (ZYPCommonFunc.hasValue(errCd)) {
                    Map<String, Object> errMap = getMnxErrCond(glblCmpyCd, errCd);
                    if (errMap != null && !errMap.isEmpty()) {

                        String flAbend = (String) errMap.get("FL_ABEND");
                        String mlTp = (String) errMap.get("ML_TP");

                        if (ZYPCommonFunc.hasValue(flAbend) && ZYPConstant.FLG_ON_Y.equals(flAbend)) {
                            S21InfoLogOutput.println(errCd + " " + msgWithRefNum);
                            throw new S21AbendException(errCd + " : " + msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "0".equals(mlTp)){
                            outputMnxErrIt(errCd, msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "1".equals(mlTp)){
                            outputMnxErrUser(errCd, msgWithRefNum);
                        } else if (ZYPCommonFunc.hasValue(mlTp) && "2".equals(mlTp)){
                            outputMnxErrIt(errCd, msgWithRefNum);
                            outputMnxErrUser(errCd, msgWithRefNum);
                        } else {
                            outputMnxErrIt(errCd, msgWithRefNum);
                        }

                    } else {
                        outputMnxErrIt(errCd, msgWithRefNum);
                    }

                } else {
                    outputMnxErrIt("UNKNOWN", msgWithRefNum);
                }
            }
            // QC#58137 End
        }
        
    }

    private NglPartMasterRequestType createPartMasterRequest(ResultSet rs) throws SQLException {

        // nglcreateOrderRequest
        NglPartMasterRequestType request = new NglPartMasterRequestType();

        PartsType parts = new PartsType();
        AlternatePartsType altPartsType = new AlternatePartsType();
        List<PartType> partList = parts.getPart();

        if (rs.next()) {
            PartType part = new PartType();
            part.setField1(rs.getString(COL_MDSE_CD));
            part.setDATE(rs.getString(COL_MDSE_ITEM_ACTV_DT_TXT));
            part.setDescription(rs.getString(COL_MDSE_NM));
            part.setOnHandType(rs.getString(COL_SER_TAKE_FLG));
            part.setWeight(Integer.parseInt(rs.getString(COL_IN_POUND_WT)));
            part.setWgtUnit(rs.getString(COL_WT_UOM_CD));
            part.setLength(Integer.parseInt(rs.getString(COL_IN_INCH_LG)));
            part.setWidth(Integer.parseInt(rs.getString(COL_IN_INCH_WDT)));
            part.setHeight(Integer.parseInt(rs.getString(COL_IN_INCH_HGT)));
            part.setDimsUnit(rs.getString(COL_DIMS_UOM_CD));
            part.setStandardCost(new BigDecimal(rs.getString(COL_RQST_TOT_STD_COST_AMT)));
            part.setHazardousMaterial(rs.getString(COL_HAZ_MAT_FLG));
            // START 2023/03/23 T.Kuroda [QC#61044, ADD]
            part.setHazardousInstruction(rs.getString(COL_HAZ_CLS_NM));
            // END   2023/03/23 T.Kuroda [QC#61044, ADD]
            part.setCustomsCommodityCode(rs.getString(COL_TRF_CD));
            part.setExpirationValue(Integer.parseInt(rs.getString(COL_MDSE_EXPR_FLG)));

            // START 2021/01/26 A.Marte [QC#58281, ADD]
            //Save Segment and Mdse# for Error Info
            Map<String, String> partsParam = new HashMap<String, String>();
            partsParam.put(COL_SEGMENT_ID, rs.getString(COL_SEGMENT_ID));
            partsParam.put(COL_MDSE_CD, rs.getString(COL_MDSE_CD));
            errItemList.add(partsParam);
            // START 2021/01/26 A.Marte [QC#58281, ADD]

            if (ZYPCommonFunc.hasValue(rs.getString(COL_MADE_IN_CTRY_CD)) && CTRY.UNITED_STATES_OF_AMERICA.equals(rs.getString(COL_MADE_IN_CTRY_CD))) {
                part.setCountryofOrigin("USA");
            } else {
                part.setCountryofOrigin(rs.getString(COL_MADE_IN_CTRY_CD));
            }

            if (ZYPCommonFunc.hasValue(rs.getString(COL_ALT_PRT_LT_TXT))) {
                part.setAlternatePart(ZYPConstant.FLG_ON_Y);
                List<AlternatePartType> altPartTypeList = altPartsType.getAlternatePart();

                while (rs.next()) {
                    AlternatePartType altPartType = new AlternatePartType();
                    altPartType.setPartField1(part.getField1());
                    List<String> altPartList = altPartType.getAltPartsField1();
                    altPartList.add(rs.getString(COL_ALT_PRT_LT_TXT));
                    altPartTypeList.add(altPartType);
                }

                request.setAlternateParts(altPartsType);
            }

            partList.add(part);
            
            

        }

        // Set request parameters.
        request.setParts(parts);

        return request;
    }

    private NglPartMasterResponse sendPartMasterRequest(NglPartMasterRequestType request) throws Throwable {

        NglPartMasterResponse response = null;

        try {
            String invokeMode = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_MNX_INVOKE_MODE, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(invokeMode) && VAL_MNX_INVOKE_MODE_STUB_SUCCESS.equals(invokeMode)) {
                response = stub_stiShippingOrder_Success(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && VAL_MNX_INVOKE_MODE_STUB_ERROR.equals(invokeMode)) {
                response = stub_stiShippingOrder_ApplicationError(request);
            } else if (ZYPCommonFunc.hasValue(invokeMode) && VAL_MNX_INVOKE_MODE_STUB_TIMEOUT.equals(invokeMode)) {
                throw new Exception("Timeout expired. The timeout period elapsed prior to completion of the operation or the server is not responding.");
            } else {
                NglPartMasterServiceProxy soServiceProxy = new NglPartMasterServiceProxy();
                response = soServiceProxy.nglPartMaster(request);
            }
        } catch (Throwable t) {
            throw t;
        }

        return response;
    }

    // Stub method for local test
    private static NglPartMasterResponse stub_stiShippingOrder_Success(NglPartMasterRequestType request) {

        com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.StatusType stType = new com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.StatusType();
        stType.setResponseType(VAL_MNX_RESPONSE_TYPE_SUCCESS);
        stType.setCode("");
        stType.setMessage("");

        PartMasterItemsType parts = new PartMasterItemsType();
        List<PartMasterItemType> partList = parts.getPartMasterItem();

        for (PartType part : request.getParts().getPart()) {
            PartMasterItemType partItem = new PartMasterItemType();

            partItem.setField1(part.getField1());
            partList.add(partItem);
        }

        NglPartMasterResponse response = new NglPartMasterResponse();
        response.setStatus(stType);

        response.setPartMasterItems(parts);

        return response;
    }

    // Stub method for local test
    private static NglPartMasterResponse stub_stiShippingOrder_ApplicationError(NglPartMasterRequestType request) {

        com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.StatusType stType = new com.canon.usa.s21.integration.service.mnx.integration.nlai2430.data.StatusType();
        stType.setResponseType(VAL_MNX_RESPONSE_TYPE_ERROR);
        stType.setCode("NE0063");
        stType.setMessage("Value of 'FslId' must be between 1 and 10 characters.");

        PartMasterItemsType parts = new PartMasterItemsType();
        List<PartMasterItemType> partList = parts.getPartMasterItem();

        for (PartType part : request.getParts().getPart()) {
            PartMasterItemType partItem = new PartMasterItemType();

            partItem.setField1(part.getField1());
            partList.add(partItem);
        }

        NglPartMasterResponse response = new NglPartMasterResponse();
        response.setStatus(stType);

        response.setPartMasterItems(parts);

        return response;
    }

    private void outputMnxErr(String errCd, String errMsg) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
        mailParam.put(NLXMailSend.KEY_MESSAGE, errMsg);
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(errCd + " " + errMsg);
    }

    /**
     * checkParameter
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_GLBL_CMPY_CD });
        }

        interfaceId01 = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(interfaceId01) || !interfaceId01.equals(IF_NLAI2410)) {

            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_VAR_USER1 });
        }

        interfaceId02 = getUserVariable2();
        if (!ZYPCommonFunc.hasValue(interfaceId02) || !interfaceId02.equals(IF_NLAI2430)) {

            throw new S21AbendException(ZZM9000E, new String[] {PARAM_NM_VAR_USER2 });
        }
    }

    /**
     * Create WMS_INBD_MDSE
     * @param wmsInbdItemWrkT WMS_INBD_ITEM_WRKTMsg
     * @return WMS_INBD_MDSETMsg
     */
    private WMS_INBD_MDSETMsg createWmsInbdMdse(WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT) {

        WMS_INBD_MDSETMsg wmsInbdMdseT = new WMS_INBD_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.glblCmpyCd, wmsInbdItemWrkT.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.whCd, wmsInbdItemWrkT.whCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsSqNum, wmsInbdItemWrkT.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.intfcTpId, wmsInbdItemWrkT.intfcTpId);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.intfcRecTpId, wmsInbdItemWrkT.intfcRecTpId);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.intfcRecActCd, wmsInbdItemWrkT.intfcRecActCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsCmpyCd, wmsInbdItemWrkT.wmsCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseCd, wmsInbdItemWrkT.wmsItemCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsProdCd, wmsInbdItemWrkT.wmsProdCd);

        MDSETMsg tmsg = new MDSETMsg();
        tmsg.glblCmpyCd.setValue("ADB");
        tmsg.mdseCd.setValue(wmsInbdItemWrkT.wmsItemCd.getValue());
        tmsg = (MDSETMsg) EZDTBLAccessor.findByKey(tmsg);

        if (tmsg != null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseDescTxt_01, tmsg.mdseNm.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseDescTxt_01, wmsInbdItemWrkT.wmsMdseDescTxt_01);
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.vndCd, wmsInbdItemWrkT.vndCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsFrtClsCd, wmsInbdItemWrkT.wmsFrtClsCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMnfClsNum, wmsInbdItemWrkT.wmsMnfClsNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseNum_01, wmsInbdItemWrkT.mnfItemNum_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseDescTxt_01, wmsInbdItemWrkT.mnfItemDescTxt_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseNum_02, wmsInbdItemWrkT.mnfItemNum_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfMdseDescTxt_02, wmsInbdItemWrkT.mnfItemDescTxt_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.unitCrtnQty, wmsInbdItemWrkT.unitCrtnQty);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.unitPltQty, wmsInbdItemWrkT.unitPltQty);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.serNumFlg, wmsInbdItemWrkT.serNumFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.serLgNum, wmsInbdItemWrkT.serLgNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsResrcTxt, VAL_WMS_RESRC_TXT_DEF);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.curStdCostNum, wmsInbdItemWrkT.curStdCostNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.futStdCostNum, wmsInbdItemWrkT.futStdCostNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsSerTpCd, wmsInbdItemWrkT.wmsSerTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_01, wmsInbdItemWrkT.wmsClsCd_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_02, wmsInbdItemWrkT.wmsClsCd_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_03, wmsInbdItemWrkT.wmsClsCd_03);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_04, wmsInbdItemWrkT.wmsClsCd_04);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_05, wmsInbdItemWrkT.wmsClsCd_05);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsClsCd_06, wmsInbdItemWrkT.wmsClsCd_06);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsCntyOrgCd, wmsInbdItemWrkT.wmsCntyOrgCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.invtyCtrlCd, wmsInbdItemWrkT.invtyCtrlCd);
        if (ZYPCommonFunc.hasValue(wmsInbdItemWrkT.invtyCatgCountCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.invtyCatgCountCd, wmsInbdItemWrkT.invtyCatgCountCd);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.invtyCatgCountCd, VAL_INVTY_CATG_COUNT_CD_DEF);
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.spclHdlgCd, wmsInbdItemWrkT.spclHdlgCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseDescTxt_02, wmsInbdItemWrkT.wmsMdseDescTxt_02);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsMdseTpCd, wmsInbdItemWrkT.wmsItemTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mdseTpDescTxt, wmsInbdItemWrkT.mdseTpDescTxt);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsTrfNum, wmsInbdItemWrkT.wmsTrfNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.wmsHazMatFlg, wmsInbdItemWrkT.wmsHazMatFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.whIntfcTxt, wmsInbdItemWrkT.whIntfcTxt);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.batCptrReqFlg, wmsInbdItemWrkT.batCptrReqFlg);

        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.hazMatCd, wmsInbdItemWrkT.hazMatCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.hazClsNm, wmsInbdItemWrkT.hazClsNm);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.hazIdNum, wmsInbdItemWrkT.hazIdNum);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.lotCtrlFlg, wmsInbdItemWrkT.lotCtrlFlg);

        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mnfItemCd, wmsInbdItemWrkT.mnfItemCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mdseItemTpCd, wmsInbdItemWrkT.mdseItemTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.swLicCtrlTpCd, wmsInbdItemWrkT.swLicCtrlTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mtrMachFlg, wmsInbdItemWrkT.mtrMachFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.serNumReqTpCd, wmsInbdItemWrkT.serNumReqTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.rtrnReqPrtFlg, wmsInbdItemWrkT.rtrnReqPrtFlg);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.mdseItemMnfTxt, wmsInbdItemWrkT.mdseItemMnfTxt);

        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.backOrdImpctTpCd, wmsInbdItemWrkT.backOrdImpctTpCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.lbFmtTpTxt, wmsInbdItemWrkT.lbFmtTpTxt);
        ZYPEZDItemValueSetter.setValue(wmsInbdMdseT.cycleCntFreqNum, wmsInbdItemWrkT.cycleCntFreqNum);

        EZDTBLAccessor.insert(wmsInbdMdseT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdMdseT.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_MDSE, TBL_WMS_INBD_ITEM_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_WRK_PK, COL_WMS_MDSE_CD) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, NLXC014001.nullToZero(wmsInbdItemWrkT.wmsInbdItemWrkPk.getValue()).toString(), wmsInbdMdseT.wmsMdseCd.getValue()) });
        }
        return wmsInbdMdseT;
    }

    /**
     * Create WMS_INBD_MDSE_UPC
     * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRKTMsg List
     * @return List<WMS_INBD_MDSE_UPCTMsg>
     */
    private List<WMS_INBD_MDSE_UPCTMsg> createWmsInbdMdseUpc(List wmsInbdItemUpcWrkList) {

        List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList = new ArrayList<WMS_INBD_MDSE_UPCTMsg>();

        for (int i = 0; i < wmsInbdItemUpcWrkList.size(); i++) {
            WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT = (WMS_INBD_ITEM_UPC_WRKTMsg) wmsInbdItemUpcWrkList.get(i);
            WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcT = new WMS_INBD_MDSE_UPCTMsg();

            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.glblCmpyCd, wmsInbdItemUpcWrkT.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.whCd, wmsInbdItemUpcWrkT.whCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsSqNum, wmsInbdItemUpcWrkT.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsUomCd, wmsInbdItemUpcWrkT.wmsUomCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.intfcTpId, wmsInbdItemUpcWrkT.intfcTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.intfcRecTpId, wmsInbdItemUpcWrkT.intfcRecTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.intfcRecActCd, wmsInbdItemUpcWrkT.intfcRecActCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsCmpyCd, wmsInbdItemUpcWrkT.wmsCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseCd, wmsInbdItemUpcWrkT.wmsItemCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsUpcCd, wmsInbdItemUpcWrkT.wmsUpcCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsBaseUomQty, wmsInbdItemUpcWrkT.wmsBaseUomQty);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseWt, wmsInbdItemUpcWrkT.wmsItemWt);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseLgNum, wmsInbdItemUpcWrkT.wmsItemLgNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseWdtNum, wmsInbdItemUpcWrkT.wmsItemWdtNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseHgtNum, wmsInbdItemUpcWrkT.wmsItemHgtNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsMdseVolNum, wmsInbdItemUpcWrkT.wmsItemVolNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsResrcTxt, VAL_WMS_RESRC_TXT_DEF);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.csePctTierNum, wmsInbdItemUpcWrkT.csePctTierNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.tierPctPltNum, wmsInbdItemUpcWrkT.tierPctPltNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseUpcT.wmsScc14Cd, wmsInbdItemUpcWrkT.wmsScc14Cd);

            EZDTBLAccessor.insert(wmsInbdMdseUpcT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdMdseUpcT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_MDSE_UPC, TBL_WMS_INBD_ITEM_UPC_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_UPC_WRK_PK, COL_WMS_MDSE_CD) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, NLXC014001.nullToZero(wmsInbdItemUpcWrkT.wmsInbdItemUpcWrkPk.getValue()).toString(), wmsInbdMdseUpcT.wmsMdseCd.getValue()) });
            }
            wmsInbdMdseUpcTList.add(wmsInbdMdseUpcT);
        }
        return wmsInbdMdseUpcTList;
    }

    /**
     * Create WMS_INBD_ITEM_MDSE_SER
     * @param wmsInbdItemSerWrkList WMS_INBD_ITEM_SER_WRKTMsg List
     * @return List<WMS_INBD_MDSE_SERTMsg>
     */
    private List<WMS_INBD_MDSE_SERTMsg> createWmsInbdMdseSer(List wmsInbdItemSerWrkList) {

        List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList = new ArrayList<WMS_INBD_MDSE_SERTMsg>();

        for (int i = 0; i < wmsInbdItemSerWrkList.size(); i++) {
            WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT = (WMS_INBD_ITEM_SER_WRKTMsg) wmsInbdItemSerWrkList.get(i);
            WMS_INBD_MDSE_SERTMsg wmsInbdMdseSerT = new WMS_INBD_MDSE_SERTMsg();

            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.glblCmpyCd, wmsInbdItemSerWrkT.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.whCd, wmsInbdItemSerWrkT.whCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsSqNum, wmsInbdItemSerWrkT.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.serLineNum, wmsInbdItemSerWrkT.serLineNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.intfcTpId, wmsInbdItemSerWrkT.intfcTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.intfcRecTpId, wmsInbdItemSerWrkT.intfcRecTpId);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.intfcRecActCd, wmsInbdItemSerWrkT.intfcRecActCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsCmpyCd, wmsInbdItemSerWrkT.wmsCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsMdseCd, wmsInbdItemSerWrkT.wmsItemCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.fromSerNum, wmsInbdItemSerWrkT.fromSerNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.toSerNum, wmsInbdItemSerWrkT.toSerNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdMdseSerT.wmsResrcTxt, VAL_WMS_RESRC_TXT_DEF);

            EZDTBLAccessor.insert(wmsInbdMdseSerT);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsInbdMdseSerT.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_MDSE_SER, TBL_WMS_INBD_ITEM_SER_WRK //
                        , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_INBD_ITEM_SER_WRK_PK, COL_WMS_MDSE_CD) //
                        , NLXCMsgHelper.toListedString(glblCmpyCd, NLXC014001.nullToZero(wmsInbdItemSerWrkT.wmsInbdItemSerWrkPk.getValue()).toString(), wmsInbdMdseSerT.wmsMdseCd.getValue()) });
            }
            wmsInbdMdseSerTList.add(wmsInbdMdseSerT);
        }
        return wmsInbdMdseSerTList;
    }

    /**
     * Set common data as WMS_SQ_NUM, WH_CD to TMsg.
     * @param wmsInbdItemWrkT WMS_INBD_ITEM_WRKTMsg
     * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRKTMsg List
     * @param wmsInbdItemSerWrkList WMS_INBD_ITEM_SER_WRKTMsg List
     * @param whCd WH_CD
     */
    private static void setWmsInbdItemData(WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT, List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList, List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList, String whCd, String invtyCatgCountCd,
            BigDecimal cycleCntFreqNum) {

        BigDecimal toWmsDataIfSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);

        // WMS_INBD_ITEM_WRK
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsSqNum, toWmsDataIfSq);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.whCd, whCd);

        // set AbcInfo
        if (ZYPCommonFunc.hasValue(invtyCatgCountCd)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.invtyCatgCountCd, invtyCatgCountCd);
        }
        if (ZYPCommonFunc.hasValue(cycleCntFreqNum)) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.cycleCntFreqNum, cycleCntFreqNum);
        }

        // WMS_INBD_ITEM_UPC_WRK
        for (WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT : wmsInbdItemUpcWrkList) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsSqNum, toWmsDataIfSq);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.whCd, whCd);
        }

        // WMS_INBD_ITEM_SER_WRK
        for (WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT : wmsInbdItemSerWrkList) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.wmsSqNum, toWmsDataIfSq);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.whCd, whCd);
        }
    }

    /**
     * convertDbsCharacter
     * @param str convertDbsCharacter
     * @return String
     */
    private String convertDbsCharacter(String str) {

        // 'É' replace to 'E'
        str = str.replaceAll(VAL_DBS_CHAR_01, VAL_DBS_CONV_CHAR_01);
        // 'é' replace to 'e'
        str = str.replaceAll(VAL_DBS_CHAR_02, VAL_DBS_CONV_CHAR_02);
        // TAB replace to ' ' (with one space)
        str = str.replaceAll(VAL_DBS_CHAR_03, VAL_DBS_CONV_CHAR_03);
        // LINE FEED(LF) replace to ' ' (with one space)
        str = str.replaceAll(VAL_DBS_CHAR_04, VAL_DBS_CONV_CHAR_03);
        // Carriage Return(CR) remove (with NULL)
        str = str.replaceAll(VAL_DBS_CHAR_05, VAL_DBS_CONV_CHAR_04);

        return str;
    }

    /**
     * Create NLAI2430_01
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseUpcTList WMS_INBD_MDSE_UPCTMsg List
     * @param mdseSeqNumber MDSE SEQ_NUMBER
     * @return BigDecimal
     */
    private BigDecimal createMdseIf01(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList, BigDecimal mdseSeqNumber, String mdseItemActvDtTxt, BigDecimal thisMthTotStdCostAmt, String trfCd) {

        // Find record has WMS_UOM_CD = "EA".
        WMS_INBD_MDSE_UPCTMsg wmsInbdMdseUpcEa = null;
        for (WMS_INBD_MDSE_UPCTMsg upcT : wmsInbdMdseUpcTList) {
            if (WMS_UOM.EACH.equals(upcT.wmsUomCd.getValue())) {
                wmsInbdMdseUpcEa = upcT;
            }
        }

        // Create NLAI2430_01 data.
        NLAI2430_01TMsg nlai243001T = new NLAI2430_01TMsg();
        ZYPEZDItemValueSetter.setValue(nlai243001T.interfaceId, interfaceId02);
        ZYPEZDItemValueSetter.setValue(nlai243001T.transactionId, mdseTrxId);
        ZYPEZDItemValueSetter.setValue(nlai243001T.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(nlai243001T.unitId, mdseUnitId);
        mdseSeqNumber = mdseSeqNumber.add(BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(nlai243001T.seqNumber, mdseSeqNumber);

        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.wmsMdseCd)) {
            ZYPEZDItemValueSetter.setValue(nlai243001T.mdseCd, convertDbsCharacter(wmsInbdMdseT.wmsMdseCd.getValue()));
        }
        mdseItemActvDtTxt = ZYPDateUtil.DateFormatter(mdseItemActvDtTxt, FMT_YYYYMMDD, FMT_MM_DD_YYYY);
        ZYPEZDItemValueSetter.setValue(nlai243001T.mdseItemActvDtTxt, mdseItemActvDtTxt);

        if (ZYPCommonFunc.hasValue(wmsInbdMdseT.wmsMdseDescTxt_01)) {
            ZYPEZDItemValueSetter.setValue(nlai243001T.mdseNm, convertDbsCharacter(wmsInbdMdseT.wmsMdseDescTxt_01.getValue()));
        }

        ZYPEZDItemValueSetter.setValue(nlai243001T.serTakeFlg, wmsInbdMdseT.serNumFlg.getValue());

        BigDecimal wmsMdseWt = BigDecimal.ZERO;
        if (wmsInbdMdseUpcEa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcEa.wmsMdseWt)) {
            wmsMdseWt = wmsInbdMdseUpcEa.wmsMdseWt.getValue();
        }
        ZYPEZDItemValueSetter.setValue(nlai243001T.inPoundWt, wmsMdseWt.setScale(0, RoundingMode.UP));

        ZYPEZDItemValueSetter.setValue(nlai243001T.wtUomCd, "LB");

        BigDecimal wmsMdseLgNum = BigDecimal.ZERO;
        if (wmsInbdMdseUpcEa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcEa.wmsMdseLgNum)) {
            wmsMdseLgNum = wmsInbdMdseUpcEa.wmsMdseLgNum.getValue();
        }
        ZYPEZDItemValueSetter.setValue(nlai243001T.inInchLg, wmsMdseLgNum.setScale(0, RoundingMode.UP));

        BigDecimal wmsMdseWdtNum = BigDecimal.ZERO;
        if (wmsInbdMdseUpcEa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcEa.wmsMdseWdtNum)) {
            wmsMdseWdtNum = wmsInbdMdseUpcEa.wmsMdseWdtNum.getValue();
        }
        ZYPEZDItemValueSetter.setValue(nlai243001T.inInchWdt, wmsMdseWdtNum.setScale(0, RoundingMode.UP));

        BigDecimal wmsMdseHgtNum = BigDecimal.ZERO;
        if (wmsInbdMdseUpcEa != null && ZYPCommonFunc.hasValue(wmsInbdMdseUpcEa.wmsMdseHgtNum)) {
            wmsMdseHgtNum = wmsInbdMdseUpcEa.wmsMdseHgtNum.getValue();
        }
        ZYPEZDItemValueSetter.setValue(nlai243001T.inInchHgt, wmsMdseHgtNum.setScale(0, RoundingMode.UP));

        ZYPEZDItemValueSetter.setValue(nlai243001T.dimsUomCd, "IN");

        ZYPEZDItemValueSetter.setValue(nlai243001T.rqstTotStdCostAmt, thisMthTotStdCostAmt);

        ZYPEZDItemValueSetter.setValue(nlai243001T.mdseExprFlg, "0");
        ZYPEZDItemValueSetter.setValue(nlai243001T.hazMatFlg, wmsInbdMdseT.wmsHazMatFlg.getValue());
        ZYPEZDItemValueSetter.setValue(nlai243001T.trfCd, trfCd);

        ZYPEZDItemValueSetter.setValue(nlai243001T.madeInCtryCd, wmsInbdMdseT.wmsCntyOrgCd.getValue());

        // START 2023/03/03 T.Kuroda [QC#61044, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(wmsInbdMdseT.wmsHazMatFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(nlai243001T.hazClsNm, wmsInbdMdseT.hazClsNm.getValue());
        }
        // END   2023/03/03 T.Kuroda [QC#61044, ADD]

        EZDTBLAccessor.insert(nlai243001T);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(nlai243001T.getReturnCode())) {
            throw new S21AbendException(NLGM0045E, new String[] {TBL_NLAI2430_01, TBL_WMS_INBD_ITEM_WRK //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_SQ_NUM, COL_WMS_ITEM_CD) //
                    , NLXCMsgHelper.toListedString(glblCmpyCd, wmsInbdMdseT.whCd.getValue(), wmsInbdMdseT.wmsSqNum.getValue().toPlainString(), wmsInbdMdseT.wmsMdseCd.getValue()) });
        }
        return mdseSeqNumber;
    }

    private ArrayList<String> getSupersedeItem(String mdseCd) {

        ArrayList<String> ret = null;

        if (supersedeItemMap.containsKey(mdseCd)) {
            ret = supersedeItemMap.get(mdseCd);

        } else {
            NWZC206001 api = new NWZC206001();
            NWZC206001PMsg param = new NWZC206001PMsg();

            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
            ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(param.xxModeCd, "1");
            ZYPEZDItemValueSetter.setValue(param.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(param.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);

            api.execute(param, ONBATCH_TYPE.BATCH);

            List<String> errList = S21ApiUtil.getXxMsgIdList(param);
            if (errList.size() > 0) {
                for (String xxMsgId : errList) {
                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(xxMsgId));
                    if (xxMsgId.endsWith("E")) {
                        return null;
                    }
                }
            }
            ret = new ArrayList<String>();

            for (int i = 0; i < param.A.getValidCount(); i++) {
                if (!mdseCd.equals(param.A.no(i).mdseCd.getValue())) {
                    ret.add(param.A.no(i).mdseCd.getValue());
                }
            }

            supersedeItemMap.put(mdseCd, ret);
        }

        return ret;
    }

    /**
     * Create NLAI2430_02
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseSerTList WMS_INBD_MDSE_SERTMsg List
     * @param mdseSeqNumber MDSE SEQ_NUMBER
     * @param wmsItemDnldIntfcId Interface Id
     * @return mdseSeqNumber
     */
    private BigDecimal createMdseIf02(String mdseCd, ArrayList<String> mdseCdList, BigDecimal mdseSeqNumber) {

        for (String altPrtLtTxt : mdseCdList) {
            if (altPrtLtTxt.equals(mdseCd)) {
                continue;
            }
            // Create NLAI2430_02 data.
            NLAI2430_02TMsg nlai243002T = new NLAI2430_02TMsg();
            ZYPEZDItemValueSetter.setValue(nlai243002T.interfaceId, interfaceId02);
            ZYPEZDItemValueSetter.setValue(nlai243002T.transactionId, mdseTrxId);
            ZYPEZDItemValueSetter.setValue(nlai243002T.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(nlai243002T.unitId, mdseUnitId);
            mdseSeqNumber = mdseSeqNumber.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(nlai243002T.seqNumber, mdseSeqNumber);
            ZYPEZDItemValueSetter.setValue(nlai243002T.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(nlai243002T.altPrtLtTxt, altPrtLtTxt);

            EZDTBLAccessor.insert(nlai243002T);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(nlai243002T.getReturnCode())) {
                throw new S21AbendException(NLGM0045E, new String[] {TBL_NLAI2430_02, TBL_WMS_INBD_ITEM_WRK, NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WMS_SQ_NUM, COL_WMS_ITEM_CD),
                        NLXCMsgHelper.toListedString(glblCmpyCd, mdseSeqNumber.toString(), mdseCd) });
            }
        }
        return mdseSeqNumber;
    }

    /**
     * Register MDSE I/F Tables
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseUpcTList WMS_INBD_MDSE_UPCTMsg List
     * @param wmsInbdMdseSerTList WMS_INBD_MDSE_SERTMsg List
     * @return isSuccess
     */
    private boolean registerMdse(WMS_INBD_MDSETMsg wmsInbdMdseT, List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList, List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList, String mdseItemActvDtTxt, BigDecimal thisMthTotStdCostAmt, String trfCd) {

        BigDecimal mdseSeqNumber = BigDecimal.ZERO;
        mdseSeqNumber = createMdseIf01(wmsInbdMdseT, wmsInbdMdseUpcTList, mdseSeqNumber, mdseItemActvDtTxt, thisMthTotStdCostAmt, trfCd);
        if (!ZYPCommonFunc.hasValue(mdseSeqNumber)) {
            return false;
        }

        return true;
    }

    /**
     * Register MDSE I/F Tables
     * @param wmsInbdMdseT WMS_INBD_MDSETMsg
     * @param wmsInbdMdseUpcTList WMS_INBD_MDSE_UPCTMsg List
     * @param wmsInbdMdseSerTList WMS_INBD_MDSE_SERTMsg List
     * @return isSuccess
     */
    private boolean registerAlternateItem(WMS_INBD_MDSETMsg wmsInbdMdseT) {

        ArrayList<String> mdseCdList = null;

        String mdseCd = wmsInbdMdseT.wmsMdseCd.getValue();
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return false;
        }

        BigDecimal mdseSeqNumber = BigDecimal.ONE;

        mdseCdList = getSupersedeItem(convertDbsCharacter(mdseCd));
        mdseSeqNumber = createMdseIf02(mdseCd, mdseCdList, mdseSeqNumber);

        mdseCdList = (ArrayList<String>) NPXC001001GetMdseRelationshipData.getMdseRelationshipData(glblCmpyCd, mdseCd, new String[] {"CP", "RF" });
        createMdseIf02(mdseCd, mdseCdList, mdseSeqNumber);

        return true;
    }

    /**
     * Create temporary data of WMS_INBD_ITEM_WRK,
     * WMS_INBD_ITEM_UPC_WRK, WMS_INBD_ITEM_SER_WRK. Create and Insert
     * WMS_INBD_MDSE, WMS_INBD_MDSE_UPC, WMS_INBD_MDSE_SER. Call MDSE
     * I/F create proc.
     * @param rs ResultSet
     * @param isFirstItemDnld Is First Item download
     * @return isSuccess
     */
    private boolean doCreateWmsInbdMdseProc(ResultSet rs, boolean isFirstItemDnld) {

        try {
            WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT = null;
            List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList = new ArrayList<WMS_INBD_ITEM_UPC_WRKTMsg>();
            List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList = new ArrayList<WMS_INBD_ITEM_SER_WRKTMsg>();

            String whCd = rs.getString(COL_WML_WH_CD);
            String mdseCd = rs.getString(COL_WML_MDSE_CD);
            String mdseItemActvDtTxt = rs.getString(COL_MDSE_ITEM_ACTV_DT);
            BigDecimal thisMthTotStdCostAmt = rs.getBigDecimal(COL_THIS_MTH_TOT_STD_COST_AMT);
            String trfCd = rs.getString(COL_TRF_CD);

            String invtyCatgCountCd = rs.getString(COL_ABC_ANLS_CLS_TAG_CD);
            BigDecimal cycleCntFreqNum = rs.getBigDecimal(COL_CYCLE_CNT_FREQ_DAYS_AOT);

            // Data check
            if (!ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_M_MDSE_CD))) {
                outputErr(NLGM0044E, new String[] {NLGC002001Constant.TBL_MDSE, NLGC002001Constant.COL_MDSE_CD, mdseCd });
                return false;
            }
            if (!ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_IPC_INTG_PROD_CATG_CD))) {
                outputErr(NLGM0044E, new String[] {NLGC002001Constant.TBL_INTG_PROD_CATG, NLGC002001Constant.COL_INTG_PROD_CATG_CD, rs.getString(NLGC002001Constant.COL_IPC_INTG_PROD_CATG_CD) });
                return false;
            }
            if (ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_M_FRT_CLS_CD)) && !ZYPCommonFunc.hasValue(rs.getString(NLGC002001Constant.COL_FC_FRT_CLS_CD))) {
                outputErr(NLGM0044E, new String[] {NLGC002001Constant.TBL_FRT_CLS, NLGC002001Constant.COL_FRT_CLS_CD, rs.getString(NLGC002001Constant.COL_M_FRT_CLS_CD) });
                return false;
            }

            // Call NLGC002001.createWmsInbdItemInfo method.
            // Then get WMS_INBD_ITEM_WRKTMsg,
            // WMS_INBD_ITEM_UPC_WRKTMsg List,
            // WMS_INBD_ITEM_SER_WRKTMsg List.
            String intfcRecActCd = NLGC002001.VAL_INTFC_REC_ACT_CD_CHNG;
            if (isFirstItemDnld) {
                intfcRecActCd = NLGC002001.VAL_INTFC_REC_ACT_CD_ADD;
            }
            NLGC002001 algc002001 = new NLGC002001(glblCmpyCd);
            wmsInbdItemWrkT = algc002001.createWmsInbdItemInfo(rs, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, intfcRecActCd);

            setWmsInbdItemData(wmsInbdItemWrkT, wmsInbdItemUpcWrkList, wmsInbdItemSerWrkList, whCd, invtyCatgCountCd, cycleCntFreqNum);

            // Create WMS_INBD_MDSE, WMS_INBD_MDSE_UPC,
            // WMS_INBD_MDSE_SER.
            WMS_INBD_MDSETMsg wmsInbdMdseT = createWmsInbdMdse(wmsInbdItemWrkT);
            if (wmsInbdMdseT == null) {
                return false;
            }
            List<WMS_INBD_MDSE_UPCTMsg> wmsInbdMdseUpcTList = createWmsInbdMdseUpc(wmsInbdItemUpcWrkList);
            if (wmsInbdMdseUpcTList == null) {
                return false;
            }
            List<WMS_INBD_MDSE_SERTMsg> wmsInbdMdseSerTList = createWmsInbdMdseSer(wmsInbdItemSerWrkList);
            if (wmsInbdMdseSerTList == null) {
                return false;
            }

            if (!registerMdse(wmsInbdMdseT, wmsInbdMdseUpcTList, wmsInbdMdseSerTList, mdseItemActvDtTxt, thisMthTotStdCostAmt, trfCd)) {
                return false;
            }

            if (!registerAlternateItem(wmsInbdMdseT)) {
                return false;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return true;
    }

    /**
     * Insert WMS_MDSE_LIST Table.
     * @param whCd WarehouseCode
     * @param mdseCd MerchandiseCode
     * @param lastIntfcMdseUpdTs LAST_INTFC_MDSE_UPD_TS
     * @return regist result
     */
    private boolean insertWmsMdseList(String mdseCd, String lastIntfcMdseUpdTs) {

        WMS_MDSE_LISTTMsg wmsMdseListT = new WMS_MDSE_LISTTMsg();
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsMdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.whCd, "MNX");

        WMS_MDSE_LISTTMsg updTMsg = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(wmsMdseListT);
        if (updTMsg != null) {
            wmsMdseListT = updTMsg;
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.lastIntfcMdseUpdTs, lastIntfcMdseUpdTs);
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsItemDnldStsCd, WMS_ITEM_DNLD_STS.COMPLEATED);
            EZDTBLAccessor.update(wmsMdseListT);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.lastIntfcMdseUpdTs, lastIntfcMdseUpdTs);
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsItemDnldStsCd, WMS_ITEM_DNLD_STS.COMPLEATED);
            EZDTBLAccessor.create(wmsMdseListT);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsMdseListT.getReturnCode())) {
            throw new S21AbendException(NLGM0007E, new String[] {TBL_WMS_MDSE_LIST //
                    , NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_WH_CD, COL_WMS_MDSE_CD) //
                    , NLXCMsgHelper.toListedString(wmsMdseListT.glblCmpyCd, wmsMdseListT.whCd, wmsMdseListT.wmsMdseCd) });
        }
        return true;
    }

    /**
     * New Item Download Process. Receive result set as parameter.
     * Call create WMS_INBD_MDSE tables process and Insert
     * WMS_MDSE_LIST.
     * @param rwsNum String
     * @param wmsWhCd String
     * @return isSuccess
     */
    private boolean doNewItemDnldProc(String rwsNum, String wmsWhCd) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            supersedeItemMap = new HashMap<String, ArrayList<String>>();

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(MAP_KEY_RWS_NUM, rwsNum);
            queryParam.put(MAP_KEY_WMS_WH_CD, wmsWhCd);
            queryParam.put(MAP_KEY_WMS_INTFC_TASK_NM, SQL_BIND_WMS_INTFC_TASK_NM_ITEM);

            stmt = ssmLLClient.createPreparedStatement(SQL_STMT_ID_GET_NEW_ITEM, queryParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                mdseTrxId = trxAccess.getNextTransactionId();

                if (!doCreateWmsInbdMdseProc(rs, true)) {
                    return false;
                }

                // Insert new data of WMS_MDSE_LIST.
                boolean insertWmsMdseListTbl = insertWmsMdseList(rs.getString(NLGC002001Constant.COL_WML_MDSE_CD), rs.getString(NLGC002001Constant.COL_WML_EZUPTIME));
                if (!insertWmsMdseListTbl) {
                    return false;
                }

                if (ZYPCommonFunc.hasValue(mdseTrxId)) {
                    trxAccess.createIntegrationRecordForBatch(interfaceId02, mdseTrxId);
                }

                mdseUnitId = mdseUnitId.add(BigDecimal.ONE);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
            return false;
        }
        return true;
    }

    /**
     * checkNewItem
     * @param rwsNum String
     * @return true / exist new item, false / not exist new item
     */
    private boolean checkNewItem(String rwsNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(MAP_KEY_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(MAP_KEY_RWS_NUM, rwsNum);
        BigDecimal ret = (BigDecimal) ssmBatchClient.queryObject(SQL_STMT_ID_IS_EXISTS_WMS_MDSE, ssmParam);

        if (ret.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * createRwsInterface
     * @param trxId
     * @param execParam
     */
    private void createRwsInterface() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(FETCH_SIZE);

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
            ssmParam.put(COL_WMS_DROP_STS_CD, VAL_WMS_DROP_STS_CD_NOT_DROP);
            ssmParam.put(COL_WH_OWNR_CD, this.whOwnrCd);
            ssmParam.put(COL_INBD_OTBD_CD, INBD_OTBD.INBOUND);
            ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
            // QC#58256
            ssmParam.put("otherVndCd", "*");
            ssmParam.put("otherVndNm", otherCarrNm);

            prdStmt = ssmLLClient.createPreparedStatement("findRws", ssmParam, execParam);
            rs = prdStmt.executeQuery();

            int processedCount = 0;
            int unitId = 0;

            while (rs.next()) {

                ++processedCount;
                ++unitId;

                Map<String, Object> rwsHdr = convertRwsHeaderToMap(rs);

                String rwsNum = (String) rwsHdr.get(COL_RWS_NUM);
                String whCd = (String) rwsHdr.get(COL_WH_CD);

                // Get Transaction ID.(RWS)
                BigDecimal trxId = trxAccess.getNextTransactionId();

                // New Item check.
                boolean isNewItem = checkNewItem(rwsNum);
                if (isNewItem) {
                    // New Item Download Process.
                    doNewItemDnldProc(rwsNum, whCd);
                }

                // RWS_MSG
                List<Map<String, Object>> rwsMsgList = getRwsMsgList(rwsNum);

                // RWS_DTL
                List<Map<String, Object>> rwsDtlList = getRwsDetailList(rwsNum);

                // Validate fetched data
                if (!validateIFData(rwsHdr, rwsDtlList)) {
                    rollback();
                    ++errorCount;
                    processedCount = 0;
                    continue;
                }

                String positionKey = NLXCMsgHelper.toListedString(COL_GLBL_CMPY_CD, COL_RWS_NUM);
                String position = NLXCMsgHelper.toListedString(this.glblCmpyCd, rwsNum);

                // Insert NLAI2410_01
                NLAI2410_01TMsg if01TMsg = createNLAI2410_01RWS(rwsHdr, trxId, unitId);
                EZDTBLAccessor.insert(if01TMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(if01TMsg.getReturnCode())) {
                    outputErr(NLGM0045E, new String[] {TBL_NLAI2410_01, TBL_RWS_HDR });
                    throw new S21AbendException(S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {TBL_NLAI2410_01, TBL_RWS_HDR, positionKey, position }));
                }

                // Insert NLAI2410_02
                int count = 0;
                List<NLAI2410_02TMsg> if02TMsgList = createNLAI2410_02RWS(rwsHdr, rwsDtlList, trxId, unitId);
                if (if02TMsgList.size() > 0) {
                    count = S21FastTBLAccessor.insert(if02TMsgList.toArray(new NLAI2410_02TMsg[if02TMsgList.size()]));
                    if (count != if02TMsgList.size()) {
                        outputErr(NLGM0045E, new String[] {TBL_NLAI2410_02, TBL_RWS_HDR });
                        throw new S21AbendException(S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {TBL_NLAI2410_02, TBL_RWS_HDR, positionKey, position }));
                    }
                }

                WMS_INBD_PO_HDRTMsg poHdrTMsg = createWmsInbdPoHdr(rwsHdr, rwsMsgList);
                EZDTBLAccessor.insert(poHdrTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poHdrTMsg.getReturnCode())) {
                    outputErr(NLGM0045E, new String[] {TBL_WMS_INBD_PO_HDR, TBL_RWS_HDR });
                    throw new S21AbendException(S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {TBL_WMS_INBD_PO_HDR, TBL_RWS_HDR, positionKey, position }));
                }

                List<WMS_INBD_PO_DTLTMsg> poDtlTMsgList = createWmsInbdPoDtlList(poHdrTMsg, rwsHdr, rwsDtlList);
                if (poDtlTMsgList.size() > 0) {
                    count = S21FastTBLAccessor.insert(poDtlTMsgList.toArray(new WMS_INBD_PO_DTLTMsg[poDtlTMsgList.size()]));
                    if (count != poDtlTMsgList.size()) {
                        outputErr(NLGM0045E, new String[] {TBL_WMS_INBD_PO_DTL, TBL_RWS_HDR, positionKey, position });

                        throw new S21AbendException(NLGM0045E, new String[] {TBL_WMS_INBD_PO_DTL, TBL_RWS_HDR, positionKey, position });
                    }
                }

                WMS_INBD_PO_VNDTMsg poVndTMsg = createWmsInbdPoVnd(poHdrTMsg, rwsHdr);
                EZDTBLAccessor.insert(poVndTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poVndTMsg.getReturnCode())) {
                    outputErr(NLGM0045E, new String[] {TBL_WMS_INBD_PO_VND, TBL_RWS_HDR });
                    throw new S21AbendException(S21MessageFunc.clspGetMessage(NLGM0045E, new String[] {TBL_WMS_INBD_PO_VND, TBL_RWS_HDR, positionKey, position }));
                }

                // Create Transaction table.
                trxAccess.createIntegrationRecordForBatch(interfaceId01, trxId);

                updateRwsHdr(rwsNum, VAL_WMS_DROP_STS_CD_DROP);

                ++successCount;

                if (processedCount >= commitCount) {
                    commit();
                    processedCount = 0;
                }

            }

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }

    }

    private Map<String, Object> convertRwsHeaderToMap(ResultSet rs) throws SQLException {
        HashMap<String, Object> rwsHdr = new HashMap<String, Object>();

        rwsHdr.put(COL_RWS_REF_NUM, rs.getString(COL_RWS_REF_NUM));
        rwsHdr.put(COL_WH_IN_ETA_DT, rs.getString(COL_WH_IN_ETA_DT));
        rwsHdr.put(COL_TPL_LOC_NM, rs.getString(COL_TPL_LOC_NM));
        rwsHdr.put(COL_ORIG_ORD_QTY, rs.getBigDecimal(COL_ORIG_ORD_QTY));
        rwsHdr.put(COL_GLBL_CMPY_CD, rs.getString(COL_GLBL_CMPY_CD));
        rwsHdr.put(COL_WH_CD, rs.getString(COL_WH_CD));
        rwsHdr.put(COL_S80_CMPY_CD, rs.getString(COL_S80_CMPY_CD));
        rwsHdr.put(COL_FROM_LOC_CD, rs.getString(COL_FROM_LOC_CD));
        rwsHdr.put(COL_S80_ORD_TP_CD, rs.getString(COL_S80_ORD_TP_CD));
        rwsHdr.put(COL_S80_TRX_CD, rs.getString(COL_S80_TRX_CD));
        rwsHdr.put(COL_WH_IN_ETA_DT, rs.getString(COL_WH_IN_ETA_DT));
        rwsHdr.put(COL_IMPT_INV_VESL_NM, rs.getString(COL_IMPT_INV_VESL_NM));
        rwsHdr.put(COL_IMPT_INV_BOL_NUM, rs.getString(COL_IMPT_INV_BOL_NUM));
        rwsHdr.put(COL_WH_CD, rs.getString(COL_WH_CD));
        rwsHdr.put(COL_RWS_NUM, rs.getString(COL_RWS_NUM));
        rwsHdr.put(COL_SVC_CONFIG_MSTR_PK, rs.getBigDecimal(COL_SVC_CONFIG_MSTR_PK));
        rwsHdr.put(COL_STAGE_ACT_CD, rs.getString(COL_STAGE_ACT_CD));
        rwsHdr.put(COL_FROM_LOC_NM_01, rs.getString(COL_FROM_LOC_NM_01));
        rwsHdr.put(COL_FROM_LOC_NM_02, rs.getString(COL_FROM_LOC_NM_02));
        rwsHdr.put(COL_FROM_LOC_ADDR_01, rs.getString(COL_FROM_LOC_ADDR_01));
        rwsHdr.put(COL_FROM_LOC_ADDR_02, rs.getString(COL_FROM_LOC_ADDR_02));
        rwsHdr.put(COL_FROM_LOC_ADDR_03, rs.getString(COL_FROM_LOC_ADDR_03));
        rwsHdr.put(COL_FROM_LOC_ADDR_04, rs.getString(COL_FROM_LOC_ADDR_04));
        rwsHdr.put(COL_FROM_LOC_CTY_ADDR, rs.getString(COL_FROM_LOC_CTY_ADDR));
        rwsHdr.put(COL_FROM_LOC_ST_CD, rs.getString(COL_FROM_LOC_ST_CD));
        rwsHdr.put(COL_FROM_LOC_POST_CD, rs.getString(COL_FROM_LOC_POST_CD));
        rwsHdr.put(COL_FROM_LOC_CTRY_CD, rs.getString(COL_FROM_LOC_CTRY_CD));
        rwsHdr.put(COL_FROM_LOC_PSN_NM, rs.getString(COL_FROM_LOC_PSN_NM));
        rwsHdr.put(COL_FROM_LOC_TEL_NUM, rs.getString(COL_FROM_LOC_TEL_NUM));
        rwsHdr.put(COL_LOC_NM, rs.getString(COL_LOC_NM));

        return rwsHdr;
    }

    /**
     * @param rwsNum
     * @return
     */
    private List<Map<String, Object>> getRwsDetailList(String rwsNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(COL_RWS_NUM, rwsNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> rwsDtlList = ssmBatchClient.queryObjectList("getRwsDetail", ssmParam);

        return rwsDtlList;
    }

    private List<Map<String, Object>> getRwsMsgList(String rwsNum) {

        ArrayList<Map<String, Object>> rwsMsgList = new ArrayList<Map<String, Object>>();

        RWS_MSGTMsg tMsg = new RWS_MSGTMsg();
        tMsg.setSQLID("001");
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, rwsNum);

        RWS_MSGTMsgArray tMsgArray = (RWS_MSGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        for (int n = 0; n < tMsgArray.getValidCount(); ++n) {
            tMsg = tMsgArray.no(n);

            HashMap<String, Object> rwsMsg = new HashMap<String, Object>();
            rwsMsg.put(COL_GLBL_CMPY_CD, tMsg.glblCmpyCd.getValue());
            rwsMsg.put(COL_RWS_NUM, tMsg.rwsNum.getValue());
            rwsMsg.put(COL_RWS_MSG_SQ_NUM, tMsg.rwsMsgSqNum.getValue());
            rwsMsg.put(COL_RWS_MSG_TXT, tMsg.rwsMsgTxt.getValue());
            rwsMsgList.add(rwsMsg);
        }

        return rwsMsgList;
    }

    private boolean validateIFData(Map<String, Object> rwsHdr, List<Map<String, Object>> rwsDtlList) {
        // MANDATORY CHECK
        String rwsNum = (String) rwsHdr.get(COL_RWS_NUM);

        if (!ZYPCommonFunc.hasValue((String) rwsHdr.get(COL_WH_CD))) {
            outputErr(NLGM0041E, new String[] {COL_WH_CD, TBL_RWS_HDR, rwsNum });
            return false;
        }
        if (!ZYPCommonFunc.hasValue((String) rwsHdr.get(COL_TPL_LOC_NM))) {
            outputErr(NLGM0041E, new String[] {COL_TPL_LOC_NM, TBL_TPL_LOC, rwsNum });
            return false;
        }

        return true;
    }

    /**
     * createNLAI2410_01RWS
     * @param rwsHeader
     * @param unitId
     * @param rwsTrxId
     */
    private NLAI2410_01TMsg createNLAI2410_01RWS(Map<String, Object> rwsHdr, BigDecimal trxId, int unitId) {

        NLAI2410_01TMsg tMsg = new NLAI2410_01TMsg();

        // BAT_PROC_DT
        String batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        batProcDt += ZYPDateUtil.getCurrentSystemTime(FMT_HHMMSSSSS);

        // WH_IN_ETA_DT
        String whInEtaDt = (String) rwsHdr.get(COL_WH_IN_ETA_DT);
        if (ZYPCommonFunc.hasValue(whInEtaDt)) {
            whInEtaDt = ZYPDateUtil.DateFormatter(whInEtaDt, FMT_YYYYMMDD, FMT_MM_DD_YYYY);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.interfaceId01);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, trxId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, new BigDecimal(unitId));
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, VAL_WMS_REC_ID_01);
        ZYPEZDItemValueSetter.setValue(tMsg.tplFromPtnrId, VAL_TPL_FROM_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.tplToPtnrId, VAL_TPL_TO_PTNR_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.reqDtTmTsTxt, batProcDt);
        ZYPEZDItemValueSetter.setValue(tMsg.ordIdTxt, (String) rwsHdr.get(COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.rqstShipDtTmTsTxt, whInEtaDt);
        ZYPEZDItemValueSetter.setValue(tMsg.tplAcctTxt, VAL_TPL_ACCT_TXT);
        ZYPEZDItemValueSetter.setValue(tMsg.tplLocTxt, (String) rwsHdr.get(COL_TPL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.origOrdQtyTxt, "");

        return tMsg;
    }

    /**
     * createNLAI2410_02RWS
     * @param rwsDtlList
     * @param unitId
     * @param trxId
     */
    private List<NLAI2410_02TMsg> createNLAI2410_02RWS(Map<String, Object> rwsHdr, List<Map<String, Object>> rwsDtlList, BigDecimal trxId, int unitId) {
        ArrayList<NLAI2410_02TMsg> tMsgList = new ArrayList<NLAI2410_02TMsg>();

        int seqNo = 1;

        for (Map<String, Object> rwsDtl : rwsDtlList) {

            NLAI2410_02TMsg tMsg = new NLAI2410_02TMsg();

            seqNo++;

            // RWS_QTY
            String qtyOrdTxt = null;
            BigDecimal rwsQty = (BigDecimal) rwsDtl.get(COL_RWS_QTY);
            if (rwsQty != null) {
                qtyOrdTxt = rwsQty.toPlainString();
            }

            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, this.interfaceId01);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, trxId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, new BigDecimal(unitId));
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, new BigDecimal(seqNo));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsRecId, VAL_WMS_REC_ID_02);
            ZYPEZDItemValueSetter.setValue(tMsg.qtyOrdTxt, qtyOrdTxt);
            ZYPEZDItemValueSetter.setValue(tMsg.itemCdTxt, (String) rwsDtl.get(COL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.tplVndItemCd, "");
            ZYPEZDItemValueSetter.setValue(tMsg.carrNm, (String) rwsHdr.get(COL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(tMsg.imptInvBolNum, (String) rwsHdr.get(COL_IMPT_INV_BOL_NUM));

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    private WMS_INBD_PO_HDRTMsg createWmsInbdPoHdr(Map<String, Object> rwsHdr, List<Map<String, Object>> rwsMsgList) {
        WMS_INBD_PO_HDRTMsg tMsg = new WMS_INBD_PO_HDRTMsg();

        // WMS_SQ_NUM
        BigDecimal wmsSqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TO_WMS_DATA_IF_SQ);
        // WH_IN_ETA_DT
        String whInEtaDt = (String) rwsHdr.get(COL_WH_IN_ETA_DT);
        if (ZYPCommonFunc.hasValue(whInEtaDt)) {
            whInEtaDt += "000000";
        }

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) rwsHdr.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) rwsHdr.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, VAL_INTFC_REC_TP_ID_HDR);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String) rwsHdr.get(COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPoId, (String) rwsHdr.get(COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, (String) rwsHdr.get(COL_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPrchTpCd, (String) rwsHdr.get(COL_S80_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsTrxCd, (String) rwsHdr.get(COL_S80_TRX_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.poFromDtTmTs, whInEtaDt);
        ZYPEZDItemValueSetter.setValue(tMsg.printSwthCd, VAL_PRINT_SWTH_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsVeslNm, (String) rwsHdr.get(COL_IMPT_INV_VESL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsBolNum, (String) rwsHdr.get(COL_IMPT_INV_BOL_NUM));

        EZDTStringItem[] fieldItems = new EZDTStringItem[] {tMsg.inbdPoMsgTxt_01, tMsg.inbdPoMsgTxt_02, tMsg.inbdPoMsgTxt_03, tMsg.inbdPoMsgTxt_04 };
        for (int n = 0; n < rwsMsgList.size() && n < fieldItems.length; ++n) {
            String text = (String) rwsMsgList.get(n).get(COL_RWS_MSG_TXT);
            if (ZYPCommonFunc.hasValue(text)) {
                ZYPEZDItemValueSetter.setValue(fieldItems[n], text);
            }
        }

        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) rwsHdr.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rwsNum, (String) rwsHdr.get(COL_RWS_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, (BigDecimal) rwsHdr.get(COL_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.stageActCd, (String) rwsHdr.get(COL_STAGE_ACT_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stageRecStsCd, VAL_STAGE_REC_STS_CD);

        return tMsg;
    }

    private List<WMS_INBD_PO_DTLTMsg> createWmsInbdPoDtlList(WMS_INBD_PO_HDRTMsg hdrTMsg, Map<String, Object> rwsHdr, List<Map<String, Object>> rwsDtlList) {
        ArrayList<WMS_INBD_PO_DTLTMsg> tMsgList = new ArrayList<WMS_INBD_PO_DTLTMsg>();

        for (Map<String, Object> rwsDtl : rwsDtlList) {
            WMS_INBD_PO_DTLTMsg tMsg = new WMS_INBD_PO_DTLTMsg();

            // WH_IN_ETA_DT
            String whInEtaDt = (String) rwsDtl.get(COL_WH_IN_ETA_DT);
            if (ZYPCommonFunc.hasValue(whInEtaDt)) {
                whInEtaDt += "000000";
            }
            // WMS_LINE_NUM
            BigDecimal wmsLineNum = null;
            String rwsDtlLineNum = (String) rwsDtl.get(COL_RWS_DTL_LINE_NUM);
            if (ZYPCommonFunc.hasValue(rwsDtlLineNum)) {
                wmsLineNum = new BigDecimal(rwsDtlLineNum);
            }

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) rwsHdr.get(COL_GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) rwsHdr.get(COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsLineNum, wmsLineNum);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, VAL_INTFC_TP_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, VAL_INTFC_REC_TP_ID_DTL);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String) rwsHdr.get(COL_S80_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsPoId, (String) rwsHdr.get(COL_RWS_REF_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsMdseCd, (String) rwsDtl.get(COL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.s80StkStsCd, (String) rwsDtl.get(COL_S80_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInvInd, VAL_WMS_INV_IND);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsOpenQty, (BigDecimal) rwsDtl.get(COL_RWS_QTY));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsEstDtTmTs, whInEtaDt);
            ZYPEZDItemValueSetter.setValue(tMsg.wmsInvId, (String) rwsDtl.get(COL_IMPT_INV_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.wmsDoId, (String) rwsDtl.get(COL_IMPT_INV_DO_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.cseToNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_TO_CSE_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.cseFromNum, (BigDecimal) rwsDtl.get(COL_OUT_PACK_FROM_CSE_NUM));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, (String) rwsHdr.get(COL_WH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, (String) rwsDtl.get(COL_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.usrCdRefTxt, (String) rwsDtl.get(COL_THIRD_PTY_DSP_TP_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.serApvlReqFlg, (String) rwsDtl.get(COL_SER_APVL_REQ_FLG));

            tMsgList.add(tMsg);
        }

        return tMsgList;
    }

    private WMS_INBD_PO_VNDTMsg createWmsInbdPoVnd(WMS_INBD_PO_HDRTMsg hdrTMsg, Map<String, Object> rwsHdr) {
        WMS_INBD_PO_VNDTMsg tMsg = new WMS_INBD_PO_VNDTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, (String) rwsHdr.get(COL_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) rwsHdr.get(COL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsSqNum, hdrTMsg.wmsSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcTpId, VAL_INTFC_TP_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcRecTpId, VAL_INTFC_REC_TP_ID_VND);
        ZYPEZDItemValueSetter.setValue(tMsg.wmsCmpyCd, (String) rwsHdr.get(COL_S80_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsPoId, (String) rwsHdr.get(COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.vndCd, (String) rwsHdr.get(COL_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsVndNm_01, adjustString((String) rwsHdr.get(COL_FROM_LOC_NM_01), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsVndNm_02, adjustString((String) rwsHdr.get(COL_FROM_LOC_NM_02), 35));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, (String) rwsHdr.get(COL_FROM_LOC_ADDR_01));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, (String) rwsHdr.get(COL_FROM_LOC_ADDR_02));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, (String) rwsHdr.get(COL_FROM_LOC_ADDR_03));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, (String) rwsHdr.get(COL_FROM_LOC_ADDR_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, (String) rwsHdr.get(COL_FROM_LOC_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, (String) rwsHdr.get(COL_FROM_LOC_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, (String) rwsHdr.get(COL_FROM_LOC_POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, (String) rwsHdr.get(COL_FROM_LOC_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.vndToCtacNm, (String) rwsHdr.get(COL_FROM_LOC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.vndToCtacNum, adjustString((String) rwsHdr.get(COL_FROM_LOC_TEL_NUM), 15));

        return tMsg;
    }

    /**
     * updateRwsHdr
     * @param gCmpyCd
     * @param rwsNum
     */
    private void updateRwsHdr(String rwsNum, String wmsDropStsCd) {

        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rwsNum, rwsNum);

        inMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        ZYPEZDItemValueSetter.setValue(inMsg.wmsDropStsCd, wmsDropStsCd);

        EZDTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();

            sb.append(inMsg.getTableName());

            rollback();

            outputErr(NPAM1003E, new String[] {sb.toString() });

            throw new S21AbendException(S21MessageFunc.clspGetMessage(NPAM1003E, new String[] {sb.toString() }));

        }

    }

    /**
     * @param msgId
     * @param msgParam
     */
    private void outputErr(String msgId, String[] msgParam) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, msgId);
        mailParam.put(NLXMailSend.KEY_MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        errMsgList.add(mailParam);

        S21InfoLogOutput.println(msgId, msgParam);
    }

    private String adjustString(String val, int len) {
        if (val == null) {
            return null;
        }

        if (val.length() > len) {
            val = val.substring(0, len);
        }

        return val;
    }
    // QC#58137 Start
    private void outputMnxErrIt(String errCd, String errMsg) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
        mailParam.put(NLXMailSend.KEY_MESSAGE, errMsg);
        errMsgList4It.add(mailParam);

        S21InfoLogOutput.println(errCd + " " + errMsg);
    }

    private void outputMnxErrUser(String errCd, String errMsg) {

        Map<String, String> mailParam = new HashMap<String, String>();
        mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
        mailParam.put(NLXMailSend.KEY_MESSAGE, errMsg);
        errMsgList4User.add(mailParam);

        S21InfoLogOutput.println(errCd + " " + errMsg);
    }

    private Map<String, Object> getMnxErrCond(String gCmpyCd, String errCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(COL_GLBL_CMPY_CD, gCmpyCd);
        queryParam.put("DS_COND_CONST_GRP_ID", "MNX_API_ERR_CD");
        queryParam.put("DS_COND_CONST_CD", errCd);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getMnxErrCond", queryParam, execParam);
    }
    // QC#58137 End

    // START 2021/01/26 A.Marte [QC#58281, ADD]
    private void outputMnxErrKeyIt(String errCd, String errMsg, BigDecimal transactionId, String ifId, List<Map<String, String>> errItemsList) {

        if (errItemsList.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append(generateErrorInfo(errMsg, transactionId, ifId, null, null));

            Map<String, String> mailParam = new HashMap<String, String>();
            mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
            mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
            errMsgList4It.add(mailParam);

            S21InfoLogOutput.println(errCd + " " + builder.toString());


        } else {
            // Loop through all Error Items
            for (Map<String, String> orderItem : errItemsList) {
                StringBuilder builder = new StringBuilder();
                String errItemId = "";

                String errSegmentId = orderItem.get(COL_SEGMENT_ID);
                if (ifId.equals(IF_NLAI2410)) {
                    errItemId = orderItem.get(COL_ORD_ID_TXT);
                } else if (ifId.equals(IF_NLAI2430)) {
                    errItemId = orderItem.get(COL_MDSE_CD);
                }

                builder.append(generateErrorInfo(errMsg, transactionId, ifId, errSegmentId, errItemId));

                Map<String, String> mailParam = new HashMap<String, String>();
                mailParam.put(NLXMailSend.KEY_MESSAGE_ID, errCd);
                mailParam.put(NLXMailSend.KEY_MESSAGE, builder.toString());
                errMsgList4It.add(mailParam);

                S21InfoLogOutput.println(errCd + " " + builder.toString());
            }
        }
    }

    private String generateErrorInfo(String errMsg, BigDecimal transactionId, String interfaceId, String errSegmentId, String errItemId) {

        StringBuilder builder = new StringBuilder();
        builder.append(errMsg);
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Interface ID      : ");
        builder.append(interfaceId);
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Transaction ID    : ");
        if (ZYPCommonFunc.hasValue(transactionId)) {
            builder.append(transactionId.toPlainString());
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Segment ID        : ");
        if (ZYPCommonFunc.hasValue(errSegmentId)) {
            builder.append(errSegmentId);
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_BLANK_14);
        builder.append("Order #           : ");
        if (ZYPCommonFunc.hasValue(errItemId)) {
            builder.append(errItemId);
        } else {
            builder.append(VAL_BLANK);
        }
        builder.append(LINE_SEPT);
        builder.append(VAL_SEP_LINE);

        return builder.toString();
    }
    // END 2021/01/26 A.Marte [QC#58281, ADD]
}
