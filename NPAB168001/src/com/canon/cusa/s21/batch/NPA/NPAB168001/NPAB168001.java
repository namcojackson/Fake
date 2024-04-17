/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB168001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_PRT_RTRN_RQSTTMsg;
import business.db.DS_COND_CONSTTMsg;

import com.canon.cusa.s21.batch.NPA.NPAB168001.constant.NPAB168001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.BaseObjectWrapper;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsReturn;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsReturnList;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsReturnListReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsReturnStatusReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsReturnTypeReference;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ExecuteMultipleOperationsResponse;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.ObjectFactory;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperation;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.StandardOperations;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.W6RequestedProperties;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.type.CSAPartsReturn.PartsReturnList;
import com.canon.usa.s21.integration.service.clicksoftware.optimization.wrapper.ClickSoftwareOptimizationService;


/**
 *<pre>
 *  Send Parts Return to Click Software
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   Fujitsu         S.Nakai         Create          N/A
 * 01/13/2016   Fujitsu         S.Nakai         Update          QC#2862
 * 03/30/2016   Hitachi         T.Iwamoto       Update          QC#4287
 * 10/21/2016   Hitachi         T.Iwamoto       Update          QC#15490
 * 10/25/2016   Hitachi         T.Iwamoto       Update          QC#15490
 * 11/02/2016   Hitachi         T.Iwamoto       Update          QC#15490
 * 11/08/2016   Hitachi         K.Yamada        Update          QC#15490
 * 2016/11/11   Hitachi         K.Kojima        Update          QC#15490
 * 11/16/2016   Hitachi         K.Ochiai        Update          QC#15490
 * 2016/11/21   Hitachi         K.Kojima        Update          QC#15490
 * 2016/11/21   Hitachi         K.Kojima        Update          QC#16033
 * 2016/11/24   Hitachi         K.Kojima        Update          QC#16033
 * 2016/11/29   Hitachi         K.Kojima        Update          QC#16033
 * 2016/11/30   Hitachi         K.Kojima        Update          QC#16033
 * 2016/12/01   Hitachi         K.Kojima        Update          QC#16033
 * 2016/12/05   Hitachi         K.Kojima        Update          QC#16079
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16316
 * 2017/02/23   Hitachi         K.Kojima        Update          QC#16301
 * 2017/07/13   Hitachi         K.Kojima        Update          QC#19399
 * 2018/06/01   Fujitsu         R.Nakamura      Update          QC#26114
 * 2018/07/24   Hitachi         T.Tomita        Update          QC#17087
 * 2019/09/25   Hitachi         K.Fujimoto      Update          QC#53675
 * 2020/08/04   Hitachi         K.Kitachi       Update          QC#57300
 * 2022/03/14   Hitachi         A.Kohinata      Update          QC#59780
 *</pre>
 */
public class NPAB168001 extends S21BatchMain implements NPAB168001Constant {

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** global company code */
    private String glblCmpyCd = "";

    // START 2016/11/21 K.Kojima [QC#16033,ADD]
    /** sales date */
    private String saledDate = "";
    // END 2016/11/21 K.Kojima [QC#16033,ADD]

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Error Messages */
    private Map<BigDecimal, EZDMessageInfo> errorMessages = new LinkedHashMap<BigDecimal, EZDMessageInfo>();

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** Error Key Map */
    List<Map<String, Object>> errKeyMap = null;

    /** Parts Return Request Status Map */
    private Map<String, String> prtRtrnReqStsMap = new HashMap<String, String>();

    /** clicksoft object factory */
    ObjectFactory objFactory = new ObjectFactory();

    /** clicksoft proxy */
    private ClickSoftwareOptimizationService proxy = new ClickSoftwareOptimizationService();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(this.getClass());

//    /** Process Mode */
//    private String processMode = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {

        new NPAB168001().executeBatch(NPAB168001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // blank check(Global Company Code)
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NPAM1001E, new String[] {"Global Company Code" });
        }
        // START 2016/11/21 K.Kojima [QC#16033,ADD]
        this.saledDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.saledDate)) {
            throw new S21AbendException(NPAM1001E, new String[] {"Sales Date" });
        }
        // END 2016/11/21 K.Kojima [QC#16033,ADD]
        // Initialize
        this.errKeyMap = new ArrayList<Map<String, Object>>();
    }

    @Override
    protected void mainRoutine() {

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // Field Request Information.
        sendPrtRtrn();

        // Send Mail if error or warning occurred.
        if (!errorMessages.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
            sendErrorMail();
        }
    }


    @Override
    protected void termRoutine() {

        setRecordCount(totalNmlCount, totalErrCount, (totalNmlCount + totalErrCount));
        setTermState(termCd);
    }

    /**
     * Send Task Information.
     */
    private void sendPrtRtrn() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement stmtUpdate = null;
        ResultSet rsUpdate = null;
        List<CLICK_PRT_RTRN_RQSTTMsg> inTMsgList = new ArrayList<CLICK_PRT_RTRN_RQSTTMsg>();
        try {
            setPrtRtrnReqStsMap();

            // TODO
            // Add Start 2018/06/01 QC#26114 Delete either
            S21StopWatch sw = new S21StopWatch();
            sw.start();
            // Add End 2018/05/31 QC#26114 Delete either

            // get Update Information.
            stmtUpdate = ssmLLClient.createPreparedStatement("getUpdatePrtRtrnInfo", getUpdateRqstParam());
            rsUpdate = stmtUpdate.executeQuery();

            // TODO
            // Add Start 2018/06/01 QC#26114 Delete either
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:getUpdatePrtRtrnInfo]End [%s]", sw.getElapsedMilliSec()));

            sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/01 QC#26114 Delete either

            BigDecimal prtRtrnRqstSq = BigDecimal.ZERO;
            String befPrchReqNum = "";
            while (rsUpdate.next()) {
                if (!befPrchReqNum.equals(rsUpdate.getString("PRCH_REQ_NUM"))) {
                    prtRtrnRqstSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRT_RTRN_RQST_SQ);
                }
                inTMsgList.add(setClickPartsReturnRequestCreateValue(rsUpdate, prtRtrnRqstSq));

                if (ZYPCommonFunc.hasValue(befPrchReqNum) && !befPrchReqNum.equals(rsUpdate.getString("PRCH_REQ_NUM"))) {
                    // Insert Update Data
                    insertTechnicianRequestListWork(inTMsgList);
                    inTMsgList = new ArrayList<CLICK_PRT_RTRN_RQSTTMsg>();
                }
                befPrchReqNum = rsUpdate.getString("PRCH_REQ_NUM");
            }
            if (inTMsgList.size() > 0) {
                // Insert Update Data
                insertTechnicianRequestListWork(inTMsgList);
            }

            // TODO
            // Add Start 2018/06/01 QC#26114 Delete either
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:Insert_CLICK_PRT_RTRN_RQSTTMsg]End [%s]", sw.getElapsedMilliSec()));

            sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/01 QC#26114 Delete either

            // get Task Information.
            stmt = ssmLLClient.createPreparedStatement("getPrtRtrnReqSq", getFldRqstParam());
            rs = stmt.executeQuery();

            // TODO
            // Add Start 2018/06/01 QC#26114 Delete either
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_SQL:getPrtRtrnReqSq]End [%s]", sw.getElapsedMilliSec()));

            sw = new S21StopWatch();
            sw.start();
            // Add End 2018/06/01 QC#26114 Delete either

            while (rs.next()) {

                if (sendPrtRtrnApi(rs)) {
                    totalNmlCount++;
                    commit();
                } else {
                    totalErrCount++;
                    rollback();
                }
            }

            // TODO
            // Add Start 2018/06/01 QC#26114 Delete either
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_API:sendPrtRtrnApi]End [%s]", sw.getElapsedMilliSec()));
            // Add End 2018/06/01 QC#26114 Delete either

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            S21SsmLowLevelCodingClient.closeResource(stmtUpdate, rsUpdate);
        }
    }

    /**
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private boolean sendPrtRtrnApi(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        BigDecimal prtRtrnRqstSq = rs.getBigDecimal(PRT_RTRN_RQST_SQ);
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prtRtrnRqstSq", prtRtrnRqstSq);
        params.put("inCompleted", PROC_STS.IN_COMPLETED);
        params.put("error", PROC_STS.ERROR);
        // START 2016/11/24 K.Kojima [QC#16033,ADD]
        params.put("NPZC1190_RTRN_TP", NPZC1190_RTRN_TP);
        // END 2016/11/24 K.Kojima [QC#16033,ADD]
        // START 2020/08/04 K.Kitachi [QC#57300, MOD]
//        // START 2016/11/30 K.Kojima [QC#16033,ADD]
//        params.put("prchReqStsTxtAwaitingShipment", this.prtRtrnReqStsMap.get(CONST_STS_AWAITINGSHIPMENT));
//        // END 2016/11/30 K.Kojima [QC#16033,ADD]
//        // START 2017/07/13 K.Kojima [QC#19399,ADD]
//        params.put("prchReqStsTxtShipped", this.prtRtrnReqStsMap.get(CONST_STS_SHIPPED));
//        // END 2017/07/13 K.Kojima [QC#19399,ADD]
        params.put("rwsStsCdReceived", RWS_STS.RECEIVED);
        // END 2020/08/04 K.Kitachi [QC#57300, MOD]
        // START 2016/12/01 K.Kojima [QC#16033,ADD]
        params.put("prchReqLineStsCdCancel", PRCH_REQ_LINE_STS.CANCELLED);
        // END 2016/12/01 K.Kojima [QC#16033,ADD]

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        S21StopWatch sw = new S21StopWatch();
        sw.start();
        // Add End 2018/05/31 QC#26114 Delete either

        List<Map<String, Object>> prtRtrnInfoList = ssmClient.queryObjectList("getPrtRtrnReq", params);

        if (prtRtrnInfoList == null || prtRtrnInfoList.size() == 0) {
            addMessage(prtRtrnRqstSq, NPAM1166E, new String[] {CLICK_PRT_RTRN_RQST, PRT_RTRN_RQST_SQ, prtRtrnRqstSq.toString()});
            return false;
        }
        Map<String, Object> headerInfo = prtRtrnInfoList.get(0);

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_SQL:getPrtRtrnReq]End [%s]", sw.getElapsedMilliSec()));
        // Add End 2018/06/01 QC#26114 Delete either

//        getProcessMode(getString(headerInfo, PRCH_REQ_RQST_STS_TXT));

        StandardOperation standardOperation = objFactory.createStandardOperation();
        standardOperation.setAction("UpdateOrCreate");
        W6RequestedProperties props = objFactory.createW6RequestedProperties();
        standardOperation.setRequestedProperties(props);

        CSAPartsReturn prtRtrn = objFactory.createCSAPartsReturn();

        prtRtrn.setPartReturnNo(getString(headerInfo, PRCH_REQ_NUM));

        String clickKeyTxt = getString(headerInfo, CLICK_KEY_TXT);
        if (isNumber(clickKeyTxt)) {
            prtRtrn.setKey(Integer.valueOf(clickKeyTxt));
        }

        CSAPartsReturnStatusReference prtRtrnRef = new CSAPartsReturnStatusReference();
        prtRtrnRef.getContent().add(getString(headerInfo, PRCH_REQ_STS_TXT));
        prtRtrn.setReturnStatus(prtRtrnRef);

        // START 2016/11/29 K.Kojima [QC#16033,ADD]
        String prchReqLineTpCd = getString(headerInfo, PRCH_REQ_LINE_TP_CD);
        String prchReqInvtyTpTxt = getString(headerInfo, PRCH_REQ_INVTY_TP_TXT);
        if (PRCH_REQ_LINE_TP.MISSING_PARTS.equals(prchReqLineTpCd)) {
            prchReqInvtyTpTxt = getPrchReqInvtyTpTxtForMissingLine(getString(headerInfo, PRCH_REQ_NUM), getString(headerInfo, PRCH_REQ_LINE_NUM));
        }
        // END 2016/11/29 K.Kojima [QC#16033,ADD]
        // START 2016/11/24 K.Kojima [QC#16033,ADD]
        CSAPartsReturnTypeReference prtRtrnTypeRef = new CSAPartsReturnTypeReference();
        // START 2016/11/29 K.Kojima [QC#16033,MOD]
        //prtRtrnTypeRef.getContent().add(objFactory.createAppReferenceName(getString(headerInfo, PRCH_REQ_INVTY_TP_TXT)));
        prtRtrnTypeRef.getContent().add(objFactory.createAppReferenceName(prchReqInvtyTpTxt));
        // END 2016/11/29 K.Kojima [QC#16033,MOD]
        prtRtrn.setReturnType(prtRtrnTypeRef);        
        prtRtrn.setWHCode(getString(headerInfo, SRC_RTL_WH_CD));
        // START 2016/11/29 K.Kojima [QC#16033,MOD]
        // prtRtrn.setWarehouse(getString(headerInfo, TECH_PSN_NM));
        prtRtrn.setWarehouse(getString(headerInfo, RTL_WH_NM));
        // END 2016/11/29 K.Kojima [QC#16033,MOD]
        prtRtrn.setEngineerStampID(getString(headerInfo, RQST_TECH_TOC_CD));
        // END 2016/11/24 K.Kojima [QC#16033,ADD]

        BaseObjectWrapper bow = objFactory.createBaseObjectWrapper();
        bow.setObject(prtRtrn);
        standardOperation.setObject(bow);

        StandardOperations standardOperations = objFactory.createStandardOperations();
        standardOperations.getOperation().add(standardOperation);

// mod start 2016/10/25, 2016/11/02 CSA Defect#15490
/*
        prtRtrn.setEngineer(getString(headerInfo, INVTY_LOC_CD));

//        if (this.processMode.equals(CONST_STS_AWAITINGSHIPMENT) || this.processMode.equals(CONST_STS_SHIPMENTSHORT)) {
        PartsReturnList prtRtrnList = new PartsReturnList();
        for (Map<String, Object> prtRtrnMap : prtRtrnInfoList) {
            String mdseCd = getString(prtRtrnMap, MDSE_CD);
            if (ZYPCommonFunc.hasValue(mdseCd)) {
                CSAPartsReturnListReference csaPrtRtrnRef = new CSAPartsReturnListReference();
                csaPrtRtrnRef.getContent().add(getString(prtRtrnMap, MDSE_CD));
                // mod start 2016/10/21 CSA Defect#15490
                int prchReqQty = 0;
                if (ZYPCommonFunc.hasValue(getBigDecimal(prtRtrnMap, PRCH_REQ_QTY))) {
                    prchReqQty = getBigDecimal(prtRtrnMap, PRCH_REQ_QTY).intValue();
                }
                csaPrtRtrnRef.getContent().add(prchReqQty);
                // mod end 2016/10/21 CSA Defect#15490
                csaPrtRtrnRef.getContent().add(getString(prtRtrnMap, PRCH_REQ_LINE_CMNT_TXT));
                prtRtrnList.getPartsReturn().add(csaPrtRtrnRef);
            }
        }
        prtRtrn.setPartsReturnList(prtRtrnList);
//        }
//        if (this.processMode.equals(CONST_STS_SHIPPED) || this.processMode.equals(CONST_STS_SHIPMENTSHORT)) {
        ShipmentInfo shipInfo = new ShipmentInfo();
        for (Map<String, Object> prtRtrnMap : prtRtrnInfoList) {
            String trkNum = getString(prtRtrnMap, CLICK_TRK_NUM);
            if (ZYPCommonFunc.hasValue(trkNum)) {
                CSAShipmentInfoReference csaShipInfoRef = new CSAShipmentInfoReference();
                csaShipInfoRef.getContent().add(getString(prtRtrnMap, CLICK_TRK_NUM));
                shipInfo.getShipment().add(csaShipInfoRef);
            }
        }
        prtRtrn.setShipmentInfo(shipInfo);
//        }
*/

        // START 2016/11/30 K.Kojima [QC#16033,ADD]
        Map<BigDecimal, String> lineNumMap = new HashMap<BigDecimal, String>();
        // END 2016/11/30 K.Kojima [QC#16033,ADD]

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        sw = new S21StopWatch();
        sw.start();
        // Add End 2018/06/01 QC#26114 Delete either

        // Parts List
        for (Map<String, Object> prtRtrnMap : prtRtrnInfoList) {
            // START 2016/11/30 K.Kojima [QC#16033,ADD]
            BigDecimal clickPrtRtrnRqstPk = (BigDecimal) prtRtrnMap.get(CLICK_PRT_RTRN_RQST_PK);
            // END 2016/11/30 K.Kojima [QC#16033,ADD]

            StandardOperation standardOperationRtnList = objFactory.createStandardOperation();
            standardOperationRtnList.setAction("UpdateOrCreate");
            W6RequestedProperties propsRtnList = objFactory.createW6RequestedProperties();
            standardOperationRtnList.setRequestedProperties(propsRtnList);

            CSAPartsReturnList prtRtrnList = objFactory.createCSAPartsReturnList();

            String clickKeyDtlTxt = getString(prtRtrnMap, CLICK_KEY_DTL_TXT);
            // DEL start 2016/11/15 CSA Defect#15490
//            if (!ZYPCommonFunc.hasValue(clickKeyDtlTxt) || !isNumber(clickKeyDtlTxt)) {
//                continue;
//            }
            // DEL END 2016/11/15 CSA Defect#15490
            prtRtrnList.setPartReturnNo(getString(prtRtrnMap, PRCH_REQ_NUM));
            // ADD START 2016/11/15 CSA Defect#15490
            if (ZYPCommonFunc.hasValue(clickKeyDtlTxt)) {
                prtRtrnList.setKey(Integer.valueOf(clickKeyDtlTxt));
            }
            // ADD END 2016/11/15 CSA Defect#15490
            String mdseCd = getString(prtRtrnMap, MDSE_CD);
            if (ZYPCommonFunc.hasValue(mdseCd)) {
                prtRtrnList.setPartNo(mdseCd);
                // START 2017/02/23 K.Kojima [QC#16301,MOD]
                // prtRtrnList.setDelivered(getBigDecimal(prtRtrnMap, "PRCH_REQ_DELY_QTY").intValue());
                // prtRtrnList.setShipped(getBigDecimal(prtRtrnMap, "PRCH_REQ_SHIP_QTY").intValue());
                // prtRtrnList.setShort(getBigDecimal(prtRtrnMap, "PRCH_REQ_SHORT_QTY").intValue());
                prtRtrnList.setDelivered(getIntegerNvl(prtRtrnMap, "PRCH_REQ_DELY_QTY"));
                prtRtrnList.setShipped(getIntegerNvl(prtRtrnMap, "PRCH_REQ_SHIP_QTY"));
                prtRtrnList.setShort(getIntegerNvl(prtRtrnMap, "PRCH_REQ_SHORT_QTY"));
                // END 2017/02/23 K.Kojima [QC#16301,MOD]
            }
            // add start 2016/11/08 CSA Defect#15490
            String lineNum = getLineNum(prtRtrnMap);
            if (ZYPCommonFunc.hasValue(lineNum)) {
                prtRtrnList.setLineNumber(lineNum);
                // START 2016/11/30 K.Kojima [QC#16033,ADD]
                lineNumMap.put(clickPrtRtrnRqstPk, lineNum);
                // END 2016/11/30 K.Kojima [QC#16033,ADD]
                // START 2017/02/23 K.Kojima [QC#16301,ADD]
            } else {
                break;
                // END 2017/02/23 K.Kojima [QC#16301,ADD]
            }
            // add end 2016/11/08 CSA Defect#15490
            // START 2016/11/24 K.Kojima [QC#16033,ADD]
            prtRtrnList.setReturnQuantity(getInteger(prtRtrnMap, PRCH_REQ_QTY));
            prtRtrnList.setDescription(getString(prtRtrnMap, MDSE_DESC_SHORT_TXT));
            // END 2016/11/24 K.Kojima [QC#16033,ADD]
            // START 2016/11/29 K.Kojima [QC#16033,ADD]
            String prchReqNum = getString(headerInfo, PRCH_REQ_NUM);
            // START 2016/12/05 K.Kojima [QC#16079,MOD]
            // String prchReqLineNum = getString(headerInfo, PRCH_REQ_LINE_NUM);
            String prchReqLineNum = getString(prtRtrnMap, PRCH_REQ_LINE_NUM);
            // END 2016/12/05 K.Kojima [QC#16079,MOD]
            prchReqLineTpCd = getString(headerInfo, PRCH_REQ_LINE_TP_CD);
            if (PRCH_REQ_LINE_TP.MISSING_PARTS.equals(prchReqLineTpCd)) {
                Map<String, Object> missingLineData = getMissingLineData(prchReqNum, prchReqLineNum);
                if (missingLineData != null) {
                    if (ZYPCommonFunc.hasValue(mdseCd)) {
                        prtRtrnList.setReturnQuantity(getInteger(missingLineData, PRCH_REQ_QTY));
                        prtRtrnList.setDelivered(getInteger(missingLineData, PRCH_REQ_DELY_QTY));
                        prtRtrnList.setShipped(getInteger(missingLineData, PRCH_REQ_SHIP_QTY));
                        prtRtrnList.setShort(getInteger(missingLineData, PRCH_REQ_SHORT_QTY));
                    }
                    lineNum = getLineNum(missingLineData);
                    if (ZYPCommonFunc.hasValue(lineNum)) {
                        prtRtrnList.setLineNumber(lineNum);
                        // START 2016/11/30 K.Kojima [QC#16033,ADD]
                        lineNumMap.put(clickPrtRtrnRqstPk, lineNum);
                        // END 2016/11/30 K.Kojima [QC#16033,ADD]
                    }
                }
            }
            // END 2016/11/29 K.Kojima [QC#16033,ADD]
            BaseObjectWrapper bowRtnList = objFactory.createBaseObjectWrapper();
            bowRtnList.setObject(prtRtrnList);
            standardOperationRtnList.setObject(bowRtnList);

            standardOperations.getOperation().add(standardOperationRtnList);
        }
// mod end 2016/10/25, 2016/11/02 CSA Defect#15490

        // add start 2016/11/08 CSA Defect#15490
        CSAPartsReturn prtRtrnForMapping = objFactory.createCSAPartsReturn();
        prtRtrnForMapping.setPartReturnNo(getString(headerInfo, PRCH_REQ_NUM));
        CSAPartsReturnStatusReference prtRtrnRefForMapping = new CSAPartsReturnStatusReference();
        prtRtrnRefForMapping.getContent().add(getString(headerInfo, PRCH_REQ_STS_TXT));
        prtRtrnForMapping.setReturnStatus(prtRtrnRefForMapping);

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_API:setPrtRtrnForMapping]End [%s]", sw.getElapsedMilliSec()));

        sw = new S21StopWatch();
        sw.start();
        // Add End 2018/05/31 QC#26114 Delete either

        PartsReturnList prtRtrnList = new PartsReturnList();
        for (Map<String, Object> prtRtrnMap : prtRtrnInfoList) {
            String lineNum = getLineNum(prtRtrnMap);
            if (!ZYPCommonFunc.hasValue(lineNum)) {
                continue;
            }
            // START 2016/11/30 K.Kojima [QC#16033,ADD]
            BigDecimal clickPrtRtrnRqstPk = (BigDecimal) prtRtrnMap.get(CLICK_PRT_RTRN_RQST_PK);
            lineNum = lineNumMap.get(clickPrtRtrnRqstPk);
            // END 2016/11/30 K.Kojima [QC#16033,ADD]

            CSAPartsReturnListReference mappingRef = objFactory.createCSAPartsReturnListReference();
            mappingRef.getContent().add(objFactory.createCSAPartsReturnListReferencePartReturnNo(getString(headerInfo, PRCH_REQ_NUM)));
            mappingRef.getContent().add(objFactory.createCSAPartsReturnListReferencePartNo(getString(prtRtrnMap, MDSE_CD)));
            mappingRef.getContent().add(objFactory.createCSAPartsReturnListReferenceLineNumber(lineNum));
            prtRtrnList.getPartsReturn().add(mappingRef);
        }
        prtRtrnForMapping.setPartsReturnList(prtRtrnList);

        if (prtRtrnForMapping.getPartsReturnList().getPartsReturn().size() > 0) {
            StandardOperation standardOperationRtnList = objFactory.createStandardOperation();
            standardOperationRtnList.setAction("UpdateOrCreate");
            W6RequestedProperties propsRtnList = objFactory.createW6RequestedProperties();
            standardOperationRtnList.setRequestedProperties(propsRtnList);
            BaseObjectWrapper bowRtnList = objFactory.createBaseObjectWrapper();
            bowRtnList.setObject(prtRtrnForMapping);
            standardOperationRtnList.setObject(bowRtnList);

            standardOperations.getOperation().add(standardOperationRtnList);
        }
        // add end 2016/11/08 CSA Defect#15490

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_API:setPartsReturnList]End [%s]", sw.getElapsedMilliSec()));
        // Add End 2018/06/01 QC#26114 Delete either

        //call WMB API
        boolean errFlg = callWmbApi(standardOperations);

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        sw = new S21StopWatch();
        sw.start();
        // Add End 2018/05/31 QC#26114 Delete either

        //update Click Tech Parts Order Request
        for (Map<String, Object> techPrdPrtMap : prtRtrnInfoList) {
            CLICK_PRT_RTRN_RQSTTMsg prmClickRtrnPrtTMsg = new CLICK_PRT_RTRN_RQSTTMsg();
            BigDecimal clickRtrnPrtPk = (BigDecimal) techPrdPrtMap.get(CLICK_PRT_RTRN_RQST_PK);

            ZYPEZDItemValueSetter.setValue(prmClickRtrnPrtTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prmClickRtrnPrtTMsg.clickPrtRtrnRqstPk, clickRtrnPrtPk);
            CLICK_PRT_RTRN_RQSTTMsg clickRtrnPrtTMsg = (CLICK_PRT_RTRN_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prmClickRtrnPrtTMsg);
            if (clickRtrnPrtTMsg == null) {
                addMessage(clickRtrnPrtPk, NPAM1003E, new String[] {CLICK_PRT_RTRN_RQST, CLICK_PRT_RTRN_RQST_PK, clickRtrnPrtPk.toString()});
                return false;
            }
            if (errFlg) {
                ZYPEZDItemValueSetter.setValue(clickRtrnPrtTMsg.procStsCd, PROC_STS.COMPLEATED);
                ZYPEZDItemValueSetter.setValue(clickRtrnPrtTMsg.prtRtrnRqstSendTs, currentSystemTs);
            } else {
                ZYPEZDItemValueSetter.setValue(clickRtrnPrtTMsg.procStsCd, PROC_STS.ERROR);
            }
            S21FastTBLAccessor.update(clickRtrnPrtTMsg);
            String returnCode = clickRtrnPrtTMsg.getReturnCode();

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(returnCode)) {
                addMessage(clickRtrnPrtPk, NPAM1003E, new String[] {CLICK_PRT_RTRN_RQST, CLICK_PRT_RTRN_RQST_PK, clickRtrnPrtPk.toString()});
                return false;
            }
        }

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        sw.stop();
        S21InfoLogOutput.println(String.format("[PFM_API:Update_CLICK_PRT_RTRN_RQSTTMsg]End [%s]", sw.getElapsedMilliSec()));
        // Add End 2018/06/01 QC#26114 Delete either

        return true;
    }
    /**
     * Add Error Message.
     * @param svcTaskNum svcTaskNum
     * @param fsrNum fsrNum
     * @param code Message Code
     * @param param Message Parameter
     */
    private void addMessage(BigDecimal clickPrtsOrdSqOrPk, String code, String... param) {
        termCd = TERM_CD.WARNING_END;
        errorMessages.put(clickPrtsOrdSqOrPk, new EZDMessageInfo(code, param));
        setErrKeyMap(clickPrtsOrdSqOrPk);

        S21InfoLogOutput.println(code, param);
    }
    /**
     * Send Error Mail.
     */
    private void sendErrorMail() {
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NPAM1478E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NPAM1478E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAM1331E, new String[] {MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        //create message
        StringBuffer msg = new StringBuffer();
        String newLine = System.getProperty("line.separator");
        for (Map<String, Object> entry : errKeyMap) {
            msg.append(entry.get(ERROR_MESSAGE).toString());
            msg.append(newLine);
        }

        // Set Parameter
        template.setTemplateParameter(MAIL_ITEM_BATCH_ID, BATCH_PROGRAM_ID);
        template.setTemplateParameter(MAIL_ITEM_ERR_DATE, errDate);
        template.setTemplateParameter(MAIL_ITEM_ERR_MSG, msg.toString());

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }
    private void setErrKeyMap(BigDecimal clickPrtRtrnPk) {

        Map<String, Object> errKey = new HashMap<String, Object>();
        errKey.put(CLICK_PRT_RTRN_RQST_PK, clickPrtRtrnPk);
        errKey.put(ERROR_MESSAGE, errorMessages.get(clickPrtRtrnPk).getMessage());
        this.errKeyMap.add(errKey);
    }
    /**
     * Get Parameter to Query Unsent Document.
     * @return Query Parameter
     */
    private Map<String, Object> getFldRqstParam() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("inCompleted", PROC_STS.IN_COMPLETED);
        params.put("error", PROC_STS.ERROR);
        // START 2016/12/01 K.Kojima [QC#14204,ADD]
        params.put("NPAB1680_STS", NPAB1680_LINE_STS);
        // END 2016/12/01 K.Kojima [QC#14204,ADD]

        return params;
    }

    /**
     * Call WMB API.
     * @return Query Parameter
     */
    private boolean callWmbApi(StandardOperations standardOperations) throws SQLException {

        ExecuteMultipleOperations emo = objFactory.createExecuteMultipleOperations();
        emo.setOneTransaction(true);
        emo.setContinueOnError(true);

        emo.setOperations(standardOperations);
//        
//        ExecuteOperation eo = objFactory.createExecuteOperation();
//        eo.setOperation(standardOperation);

        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        S21StopWatch sw = new S21StopWatch();

        try {
            sw.start();
            // Add End 2018/06/01 QC#26114 Delete either

            // START 2017/01/04 K.Kitachi [QC#16316, MOD]
//            ExecuteMultipleOperationsResponse response = this.proxy.executeMultipleOperations(emo);
            ExecuteMultipleOperationsResponse response = this.proxy.executeMultipleOperations(INTERFACE_ID, emo);
            // END 2017/01/04 K.Kitachi [QC#16316, MOD]

//            ExecuteOperationResponse response = proxy.executeOperation(eo);
        } catch (Exception e) {
            this.termCd = TERM_CD.WARNING_END;
            e.printStackTrace();
            this.totalErrCount++;
            return false;
        } catch (Throwable t) {
            this.termCd = TERM_CD.WARNING_END;
            t.printStackTrace();
            this.totalErrCount++;
            return false;
        }
        // TODO
        // Add Start 2018/06/01 QC#26114 Delete either
        finally {
            sw.stop();
            S21InfoLogOutput.println(String.format("[PFM_API:executeMultipleOperations]End [%s]", sw.getElapsedMilliSec()));
        }
        // Add End 2018/06/01 QC#26114 Delete either
        return true;
    }
    /**
     * Get String Value from Map. (With Conversion from Null to "")
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return String
     */
    private String getString(Map<String, Object> map, String key) {
        String ret = (String) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return "";
    }

    // START 2017/02/23 K.Kojima [QC#16301,DEL]
    // /**
    //  * Get String Value from Map. (With Conversion from Null to "")
    //  * @param map Map&lt;Object, Object&gt;
    //  * @param key String
    //  * @return String
    //  */
    // private BigDecimal getBigDecimal(Map<String, Object> map, String key) {
    //     BigDecimal ret = (BigDecimal) map.get(key);
    //     if (ZYPCommonFunc.hasValue(ret)) {
    //         return ret;
    //     }
    //     return null;
    // }
    // END 2017/02/23 K.Kojima [QC#16301,DEL]

    // START 2016/11/24 K.Kojima [QC#16168,ADD]
    /**
     * Get Integer Value from Map. (With Conversion from Null to "")
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return Integer
     */
    private Integer getInteger(Map<String, Object> map, String key) {
        BigDecimal ret = (BigDecimal) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return new Integer(ret.intValue());
        }
        return null;
    }

    // END 2016/11/24 K.Kojima [QC#16168,ADD]

    // START 2017/02/23 K.Kojima [QC#16600,ADD]
    /**
     * Get Integer Value from Map. (With Conversion from Null to "")
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return Integer
     */
    private Integer getIntegerNvl(Map<String, Object> map, String key) {
        BigDecimal ret = (BigDecimal) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return new Integer(ret.intValue());
        }
        return 0;
    }
    // END 2017/02/23 K.Kojima [QC#16600,ADD]

    private void setPrtRtrnReqStsMap() {
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(this.glblCmpyCd, CONST_GRP_ID_NPAB1680, CONST_PRT_RTRN_REQ_STS_KEY);
        if (dsCondConstTMsg != null) {
            this.prtRtrnReqStsMap.put(CONST_STS_AWAITINGSHIPMENT, dsCondConstTMsg.dsCondConstValTxt_01.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_SHIPMENTSHORT, dsCondConstTMsg.dsCondConstValTxt_02.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_SHIPPED, dsCondConstTMsg.dsCondConstValTxt_03.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_CLOSE, dsCondConstTMsg.dsCondConstValTxt_04.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_SUCCESS, dsCondConstTMsg.dsCondConstValTxt_05.getValue());
            this.prtRtrnReqStsMap.put(CONST_STS_ERROR, dsCondConstTMsg.dsCondConstValTxt_06.getValue());
        }
    }

//    private void getProcessMode(String prchReqStsNm) {
//        if (prchReqStsNm.equals(this.prtRtrnReqStsMap.get(CONST_STS_AWAITINGSHIPMENT))) {
//            this.processMode = CONST_STS_AWAITINGSHIPMENT;
//        } else if (prchReqStsNm.equals(this.prtRtrnReqStsMap.get(CONST_STS_SHIPPED))) {
//            this.processMode = CONST_STS_SHIPPED;
//        } else if (prchReqStsNm.equals(this.prtRtrnReqStsMap.get(CONST_STS_SHIPMENTSHORT))) {
//            this.processMode = CONST_STS_SHIPMENTSHORT;
//        }
//    }
    private DS_COND_CONSTTMsg getDsCondConstTMsg(String gcc, String grpId, String constCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.glblCmpyCd, gcc);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstGrpId, grpId);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstCd, constCd);
        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(dsCondConstTMsg);
    }
    private boolean isNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Map<String, Object> getUpdateRqstParam() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("procStsComp", PROC_STS.COMPLEATED);
        params.put("prchReqRqstStstxt", this.prtRtrnReqStsMap.get(CONST_STS_CLOSE));
        params.put("prchReqRecTpTech", PRCH_REQ_REC_TP.TECH_RETURN);
        params.put("NPAB1680_STS", NPAB1680_LINE_STS);
        // START 2016/11/21 K.Kojima [QC#16033,ADD]
        params.put("saledDate", this.saledDate);
        // END 2016/11/21 K.Kojima [QC#16033,ADD]
        // START 2016/11/24 K.Kojima [QC#16168,ADD]
        params.put("prchReqLineStsReceived", PRCH_REQ_LINE_STS.RECEIVED);
        params.put("prchReqStsClosed", PRCH_REQ_STS.CLOSED);
        params.put("prchReqStsCancelled", PRCH_REQ_STS.CANCELLED);
        // END 2016/11/24 K.Kojima [QC#16168,ADD]
        // Add Start 2018/07/24 QC#17087
        params.put("lineTpMissParts", PRCH_REQ_LINE_TP.MISSING_PARTS);
        // Add End 2018/07/24 QC#17087
        // Add Start 2019/09/25 K.Fujimoto QC#53675
        params.put("prchReqSrcTpCd", PRCH_REQ_SRC_TP.PHONE);
        // Add End 2019/09/25 K.Fujimoto QC#53675
        return params;
    }

    private CLICK_PRT_RTRN_RQSTTMsg setClickPartsReturnRequestCreateValue(ResultSet rs, BigDecimal prtRtrnRqstSq) {
        CLICK_PRT_RTRN_RQSTTMsg inParam = new CLICK_PRT_RTRN_RQSTTMsg();

        try {
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.clickPrtRtrnRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CLICK_PRT_RTRN_RQST_SQ));
            ZYPEZDItemValueSetter.setValue(inParam.prtRtrnRqstSq, prtRtrnRqstSq);
            ZYPEZDItemValueSetter.setValue(inParam.prchReqNum, rs.getString("PRCH_REQ_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqRqstStsTxt, rs.getString("PRCH_REQ_RQST_STS_TXT"));
            // START 2016/11/11 K.Kojima [QC#15490,ADD]
            ZYPEZDItemValueSetter.setValue(inParam.prchReqStsTxt, rs.getString("PRCH_REQ_RQST_STS_TXT"));
            // END 2016/11/11 K.Kojima [QC#15490,ADD]
            // START 2016/11/21 K.Kojima [QC#15490,ADD]
            ZYPEZDItemValueSetter.setValue(inParam.rqstTechTocCd, rs.getString("RQST_TECH_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.srcRtlWhCd, rs.getString("SRC_RTL_WH_CD"));
            // END 2016/11/21 K.Kojima [QC#15490,ADD]
            ZYPEZDItemValueSetter.setValue(inParam.invtyLocCd, rs.getString("INVTY_LOC_CD"));
            // START 2016/11/21 K.Kojima [QC#15490,ADD]
            ZYPEZDItemValueSetter.setValue(inParam.prchReqInvtyTpTxt, rs.getString("PRCH_REQ_INVTY_TP_TXT"));
            // END 2016/11/21 K.Kojima [QC#15490,ADD]
            ZYPEZDItemValueSetter.setValue(inParam.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqQty, rs.getBigDecimal("PRCH_REQ_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqShortQty, rs.getBigDecimal("BACK_TO_TECH_QTY"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqLineCmntTxt, rs.getString("PRCH_REQ_LINE_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(inParam.procStsCd, PROC_STS.IN_COMPLETED);
            ZYPEZDItemValueSetter.setValue(inParam.clickTrkNum, rs.getString("CLICK_TRK_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.clickKeyTxt, rs.getString("CLICK_KEY_TXT"));
            // START 2016/11/21 K.Kojima [QC#15490,ADD]
            ZYPEZDItemValueSetter.setValue(inParam.clickKeyDtlTxt, rs.getString("CLICK_KEY_DTL_TXT"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqLineNum, rs.getString("PRCH_REQ_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.prchReqLineSubNum, rs.getBigDecimal("PRCH_REQ_LINE_SUB_NUM"));
            // END 2016/11/21 K.Kojima [QC#15490,ADD]
            // add start 2022/03/14 QC#59780
            ZYPEZDItemValueSetter.setValue(inParam.origClickTrkNum, rs.getString("ORIG_CLICK_TRK_NUM"));
            ZYPEZDItemValueSetter.setValue(inParam.clickToWmsTrkNum, rs.getString("CLICK_TO_WMS_TRK_NUM"));
            // add end 2022/03/14 QC#59780

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private void insertTechnicianRequestListWork(List<CLICK_PRT_RTRN_RQSTTMsg> inMsgLst) {
        CLICK_PRT_RTRN_RQSTTMsg[] inMsgArray;
        inMsgArray = new CLICK_PRT_RTRN_RQSTTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            this.totalErrCount += inMsgArray.length - insertCount;
            rollback();
            return;
        }
        commit();
    }

    private String getLineNum(Map<String, Object> prtRtrnMap) {
        String prReqLineNum = (String) prtRtrnMap.get("PRCH_REQ_LINE_NUM");
        BigDecimal prReqLineSubNum = (BigDecimal) prtRtrnMap.get("PRCH_REQ_LINE_SUB_NUM");
        if (ZYPCommonFunc.hasValue(prReqLineNum) && ZYPCommonFunc.hasValue(prReqLineSubNum)) {
            return prReqLineNum + "." + prReqLineSubNum.toString();
        }
        return null;
    }

    // START 2016/11/29 K.Kojima [QC#16033,ADD]
    /**
     * getPrchReqInvtyTpTxtForMissingLine
     * @param prchReqNum String
     * @param prchReqLineNum String
     * @return String prchReqInvtyTpTxt
     */
    private String getPrchReqInvtyTpTxtForMissingLine(String prchReqNum, String prchReqLineNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prchReqLineNum);
        params.put("prchReqLineStsCdCancel", PRCH_REQ_LINE_STS.CANCELLED);
        params.put("NPZC1190_RTRN_TP", NPZC1190_RTRN_TP);
        return (String) ssmClient.queryObject("getPrchReqInvtyTpTxtForMissingLine", params);
    }

    /**
     * getMissingLineData
     * @param prchReqNum String
     * @param prchReqLineNum String
     * @return String prchReqInvtyTpTxt
     */
    private Map<String, Object> getMissingLineData(String prchReqNum, String prchReqLineNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        params.put("prchReqLineNum", prchReqLineNum);
        params.put("prchReqLineStsCdCancel", PRCH_REQ_LINE_STS.CANCELLED);
        // START 2016/11/30 K.Kojima [QC#16033,ADD]
        params.put("prchReqStsTxtAwaitingShipment", this.prtRtrnReqStsMap.get(CONST_STS_AWAITINGSHIPMENT));
        params.put("NPAB1680_STS", NPAB1680_LINE_STS);
        // END 2016/11/30 K.Kojima [QC#16033,ADD]
        return (Map<String, Object>) ssmClient.queryObject("getMissingLineData", params);
    }
    // END 2016/11/29 K.Kojima [QC#16033,ADD]

}
