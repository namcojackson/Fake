/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB033001;

import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.CARR_FRT_INFO_INTFC_WRK;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.CARR_FRT_INFO_INTFC_WRK_SQ;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.CUSA_SPLY;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.CUSA_SPLY_PRT;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.DS_BIZ_LAST_UPD_TS_DEFAULT;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.DS_BIZ_PROC_LOG;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.DS_BIZ_PROC_LOG_SQ;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.INVTY_TRX_DT_FORMAT_AF;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.INVTY_TRX_DT_FORMAT_BF;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.MAIL_KEY_FROM;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.MAIL_KEY_TO;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.MAIL_TEMPLATE_KEY_MESSAGE;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.MSG_ID_NLAM1270E;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.MSG_TXT_EQUALS;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.NLBM1065E;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NLB.NLBB033001.constant.NLBB033001Constant.TIME_STAMP_FORMAT;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CARR_FRT_INFO_INTFC_WRKTMsg;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 * Business ID : NLBB0330 Send Freight Info to CTSI (Work Table Creation) Batch
 * Function Name : NLBB033001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/31/2016   CITS            R.Shimamoto     Create          N/A
 * 04/14/2016   CITS            R.Shimamoto     Update          QC#6981
 * 04/28/2016   CITS            R.Shimamoto     Update          QC#7662
 * 04/11/2018   CITS            K.Ogino         Update          QC#25261
 * 04/26/2018   CITS            K.Ogino         Update          QC#19338
 * 07/09/2018   CITS            K.Ogino         Update          QC#27063
 * 12/12/2018   CITS            K.Ogino         Update          QC#29160
 * 04/16/2019   CITS            A.Kobayashi     Update          QC#31144
 * 02/21/2020   Fujitsu         R.Nakamura      Update          QC#55942
 * 10/06/2020   CITS            A.Marte         Update          QC#57795
 * 03/04/2022   CITS            A.Cullano       Update          QC#59776
 * 06/28/2023   CITS            D.Vy            Update          QC#61651
 *</pre>
 */
public class NLBB033001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** warning count */
    private int warningCount = 0;

    /** total count */
    private int totalCount = 0;

    /** DS_BIZ_LAST_UPD_TS */
    private String lastUpdTs = null;

    /** THIS_PROC_TS */
    private String thisProcTs = null;

    /** dsBizProcLogPk */
    private BigDecimal dsBizProcLogPk = null;

    // 2023/06/28 QC#61651 Add Start
    /** Sales Date */
    private String salesDate = null;
    // 2023/06/28 QC#61651 Add End

    /** tdsCoaProdCd */
    private String tdsCoaProdCd = null;
    /** dgCoaProdCd */
    private String dgCoaProdCd = null;
    /** lfsCoaProdCd */
    private String lfsCoaProdCd = null;
    /** ppsCoaProdCd */
    private String ppsCoaProdCd = null;
    /** essCoaProdCd */
    private String essCoaProdCd = null;
    /** orderSSO */
    private String orderSSO = null;
    /** orderSSD */
    private String orderSSD = null;
    /** orderMOS */
    private String orderMOS = null;
    /** orderRAP */
    private String orderRAP = null;
    /** orderTRF */
    private String orderTRF = null;
    /** orderRAE */
    private String orderRAE = null;
    /** orderPOP */
    private String orderPOP = null;
    /** orderPOS */
    private String orderPOS = null;
    /** orderPOE */
    private String orderPOE = null;
    /** poPartsSrcNm */
    private String poPartsSrcNm = null;
    /** poEquipSrcNm */
    private String poEquipSrcNm = null;
    // START 2020/10/06 A.Marte [QC#57795, ADD]
    /** poVndCdList */
    private List<String> poVndCdList = new ArrayList<String>();
    // END 2020/10/06 A.Marte [QC#57795, ADD]

//    /** lfsCoaProdCd */
//    private String lfsCoaCcCd = null;
//    /** lfsCoaProdCd */
//    private String ppsCoaCcCd = null;
//    /** lfsCoaProdCd */
//    private String essCoaCcCd = null;

    @Override
    protected void initRoutine() {

        // Get Global Company Code
        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        // getClient
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Set termCd
        termCd = TERM_CD.NORMAL_END;

        // Get Last Update Time
        getDsBizLastUpdTs();

        // Get This Proc Time
        // 2023/06/28 QC#61651 Update Start
        salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        // 2023/06/28 QC#61651 Update End
        // STR 04/28/2016 R.Shimamoto [QC#7662 MOD]
        // 2023/06/28 QC#61651 Update Start
        thisProcTs = ZYPCommonFunc.rightPad(ZYPDateUtil.addDays(salesDate, 1), 17, "0");
        // 2023/06/28 QC#61651 Update End
        // END 04/28/2016 R.Shimamoto [QC#7662 MOD]

        tdsCoaProdCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_TDS_COA_PROD_CD", glblCmpyCd); // W8
        if (!ZYPCommonFunc.hasValue(tdsCoaProdCd)) {
            tdsCoaProdCd = "W8";
        }
        dgCoaProdCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_DG_COA_PROD_CD", glblCmpyCd); // S8
        if (!ZYPCommonFunc.hasValue(dgCoaProdCd)) {
            dgCoaProdCd = "S8";
        }
        lfsCoaProdCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_LFS_COA_PROD_CD", glblCmpyCd); // L8
        if (!ZYPCommonFunc.hasValue(lfsCoaProdCd)) {
            lfsCoaProdCd = "L8";
        }
        ppsCoaProdCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_PPS_COA_PROD_CD", glblCmpyCd); // P8
        if (!ZYPCommonFunc.hasValue(ppsCoaProdCd)) {
            ppsCoaProdCd = "P8";
        }
        essCoaProdCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ESS_COA_PROD_CD", glblCmpyCd); // E8
        if (!ZYPCommonFunc.hasValue(essCoaProdCd)) {
            essCoaProdCd = "E8";
        }
        orderSSO = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_SSO", glblCmpyCd); // SSO
        if (!ZYPCommonFunc.hasValue(orderSSO)) {
            orderSSO = "SSO";
        }
        orderSSD = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_SSD", glblCmpyCd); // SSD
        if (!ZYPCommonFunc.hasValue(orderSSD)) {
            orderSSD = "SSD";
        }
        orderMOS = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_MOS", glblCmpyCd); // MOS
        if (!ZYPCommonFunc.hasValue(orderMOS)) {
            orderMOS = "MOS";
        }
        orderRAP = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_RAP", glblCmpyCd); // RAP
        if (!ZYPCommonFunc.hasValue(orderRAP)) {
            orderRAP = "RAP";
        }
        orderTRF = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_TRF", glblCmpyCd); // TRF
        if (!ZYPCommonFunc.hasValue(orderTRF)) {
            orderTRF = "TRF";
        }
        orderRAE = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_RAE", glblCmpyCd); // RAE
        if (!ZYPCommonFunc.hasValue(orderRAE)) {
            orderRAE = "RAE";
        }
        orderPOP = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_POP", glblCmpyCd); // POP
        if (!ZYPCommonFunc.hasValue(orderPOP)) {
            orderPOP = "POP";
        }
        orderPOS = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_POS", glblCmpyCd); // POS
        if (!ZYPCommonFunc.hasValue(orderPOS)) {
            orderPOS = "POS";
        }
        orderPOE = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ORDER_POE", glblCmpyCd); // POE
        if (!ZYPCommonFunc.hasValue(orderPOE)) {
            orderPOE = "POE";
        }
        poPartsSrcNm = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_PO_PARTS", glblCmpyCd); // PO_RECEIPT_PARTS
        if (!ZYPCommonFunc.hasValue(poPartsSrcNm)) {
            poPartsSrcNm = "PO_RECEIPT_PARTS";
        }
        poEquipSrcNm = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_PO_EQUIP", glblCmpyCd); // PO_RECEIPT_EQUIPMENT
        if (!ZYPCommonFunc.hasValue(poEquipSrcNm)) {
            poEquipSrcNm = "PO_RECEIPT_EQUIPMENT";
        }
        // START 2020/10/06 A.Marte [QC#57795, ADD]
        String poVndCdConst = ZYPCodeDataUtil.getVarCharConstValue("NLBB0330_DROP_PO_EXCL_VND", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(poVndCdConst)) {
            poVndCdList = Arrays.asList(poVndCdConst.split(","));
        }
        // END 2020/10/06 A.Marte [QC#57795, ADD]
//        lfsCoaCcCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_LFS_COA_CC_CD", glblCmpyCd); // 491299
//        if (!ZYPCommonFunc.hasValue(lfsCoaCcCd)) {
//            lfsCoaCcCd = "491299";
//        }
//        ppsCoaCcCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_PPS_COA_CC_CD", glblCmpyCd); // 491398
//        if (!ZYPCommonFunc.hasValue(ppsCoaCcCd)) {
//            ppsCoaCcCd = "491398";
//        }
//        essCoaCcCd = ZYPCodeDataUtil.getVarCharConstValue("NLBB033001_ESS_COA_CC_CD", glblCmpyCd); // 894101
//        if (!ZYPCommonFunc.hasValue(essCoaCcCd)) {
//            essCoaCcCd = "894101";
//        }
    }

    @Override
    protected void mainRoutine() {

        // QC#19338
        // Send SO Confirmation Sales Order Delivery
        sendSoConfirmationRS(false);

        // QC#31144 inform the time for processing data from last task (this message can be changed)
        S21InfoLogOutput.println("TARGET DATA : " + this.lastUpdTs);

        // Send SO Confirmation Sales Order Drop
        sendSoConfirmationRS(true);

        // Send SO Confirmation Parts Request
        sendSoConfirmationMOSorRAP(false);

        // Send SO Confirmation Parts Return Request
        sendSoConfirmationMOSorRAP(true);

        // Send SO Confirmation Warehouse Transfer
        sendSoConfirmationTRFDt();

        // Send SO Confirmation Inventory Request(not Warehouse Transfer)
        sendSoConfirmationTRFIr();

        // RWS Complete Sales Order - Pick up
        sendRwsCompleteRAE();

        // RWS Complete PO Receipt Equipment / Supply and PO Receipt Parts
        sendRwsCompletePO();

        // QC#29160
        sendWarningReport();

        // Last Proc Ts Update
        updateDsBizProcLog();

        // QC#31144 inform the time to finish and next time processing (this message can be changed)
        S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + this.thisProcTs);

        // commit
        commit();
    }

    @Override
    protected void termRoutine() {
        // QC#29160
        if (warningCount > 0) {
            termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);

    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NLBB033001().executeBatch(NLBB033001.class.getSimpleName());
    }

    /**
     * getDsBizLastUpdTs
     */
    private void getDsBizLastUpdTs() {
        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("procPgmId01", BUSINESS_ID);

        DS_BIZ_PROC_LOGTMsgArray getTMsgArray = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (getTMsgArray.getValidCount() > 0) {
            if (getTMsgArray.no(0).dsBizProcLogPk != null) {
                // QC#27063
                this.lastUpdTs = ZYPDateUtil.DateFormatter(getTMsgArray.no(0).dsBizLastUpdTs.getValue(), TIME_STAMP_FORMAT, INVTY_TRX_DT_FORMAT_BF);
                this.dsBizProcLogPk = getTMsgArray.no(0).dsBizProcLogPk.getValue();
            } else {
                this.lastUpdTs = DS_BIZ_LAST_UPD_TS_DEFAULT;
            }
        } else {
            this.lastUpdTs = DS_BIZ_LAST_UPD_TS_DEFAULT;
        }
    }

    /**
     * updateDsBizProcLog
     */
    private void updateDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg outMsg = new DS_BIZ_PROC_LOGTMsg();

        if (!ZYPCommonFunc.hasValue(this.dsBizProcLogPk)) {

            String salseDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcDt, salseDate);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, this.thisProcTs);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(DS_BIZ_PROC_LOG_SQ));

            EZDTBLAccessor.insert(outMsg);

        } else {

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizProcLogPk, this.dsBizProcLogPk);
            outMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(outMsg);

            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(outMsg.procPgmId, BUSINESS_ID);
            outMsg.dsBizProcTrxNum.clear();
            outMsg.dsBizProcTrxDtlNum.clear();
            outMsg.dsBizProcTrxSubDtlNum.clear();
            outMsg.dsBizProcRqstSlsDt.clear();
            outMsg.dsBizProcDt.clear();
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
            ZYPEZDItemValueSetter.setValue(outMsg.dsBizLastUpdTs, this.thisProcTs);

            EZDTBLAccessor.update(outMsg);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            insertErrorLogOutPut(outMsg.dsBizProcLogPk.getValue(), DS_BIZ_PROC_LOG);
            errorCount++;
        }

    }

    /**
     * insertErrorLogOutPut
     * @param carrFrtInfoIntfcWrk BigDecimal
     * @param tableId String
     * @return boolean
     */
    private boolean insertErrorLogOutPut(BigDecimal carrFrtInfoIntfcWrk, String tableId) {
        StringBuilder sbKey = new StringBuilder();

        sbKey.append(CARR_FRT_INFO_INTFC_WRK);
        sbKey.append(MSG_TXT_EQUALS);
        sbKey.append(carrFrtInfoIntfcWrk);
        S21InfoLogOutput.println(MSG_ID_NLAM1270E, new String[] {tableId, sbKey.toString() });

        return false;
    }

    // QC#19338
    /**
     * sendSoConfirmationRS
     */
    private void sendSoConfirmationRS(boolean isDropShip) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("dsBizLastUpdTs", this.lastUpdTs);
            queryParam.put("sceOrdTpCdRS", SCE_ORD_TP.DIRECT_SALES);
            queryParam.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
            queryParam.put("ntcDropFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("dummy", RTL_WH_CATG.VIRTUAL);
            queryParam.put("shpgStsCd", SHPG_STS.SHIPPED);
            queryParam.put("machine", COA_MDSE_TP.MACHINE);
            queryParam.put("tds", "TDS");
            queryParam.put("dg", "DG");
            queryParam.put("batchId", BATCH_ID);
            queryParam.put("tdsCoaProdCd", tdsCoaProdCd);
            queryParam.put("dgCoaProdCd", dgCoaProdCd);
            queryParam.put("orderSSD", orderSSD);
            queryParam.put("orderSSO", orderSSO);
            // START 2020/10/06 A.Marte [QC#57795, ADD]
            queryParam.put("poVndCdList", poVndCdList);
            // END 2020/10/06 A.Marte [QC#57795, ADD]

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            if (isDropShip) {
                stmt = this.ssmLLClient.createPreparedStatement("getSoConfirmationSSD", queryParam, ssmEexcParam);
            } else {
                stmt = this.ssmLLClient.createPreparedStatement("getSoConfirmationSSO", queryParam, ssmEexcParam);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                totalCount++;
                // Insert Work
                insertCarrFrtInfoIntfcWrk(rs);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * sendSoConfirmationMOS
     */
    private void sendSoConfirmationMOSorRAP(boolean isTechReturn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("dsBizLastUpdTs", this.lastUpdTs);
            queryParam.put("sceOrdTpCdTR", SCE_ORD_TP.TECH_REQUEST);
            queryParam.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
            queryParam.put("ntcDropFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("shpgStsCd", SHPG_STS.SHIPPED);
            queryParam.put("batchId", BATCH_ID);
            queryParam.put("lfs", LINE_BIZ_TP.LFS);
            queryParam.put("ess", LINE_BIZ_TP.ESS);
            queryParam.put("pps", LINE_BIZ_TP.PPS);
            queryParam.put("lfsCoaProdCd", lfsCoaProdCd);
            queryParam.put("essCoaProdCd", essCoaProdCd);
            queryParam.put("ppsCoaProdCd", ppsCoaProdCd);
            // 2023/06/28 QC#61651 Add Start   
            queryParam.put("salesDate", salesDate);
            // 2023/06/28 QC#61651 Add End   

            if (isTechReturn) {
                queryParam.put("locTpCd06", LOC_TP.TECHNICIAN);
            } else {
                queryParam.put("locTpCd01", LOC_TP.WAREHOUSE);
            }

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            if (isTechReturn) {
                queryParam.put("rapCtsiPrfl", "RAP_CTSI_PRFL");
                queryParam.put("orderRAP", orderRAP);
                stmt = this.ssmLLClient.createPreparedStatement("getSoConfirmationRAP", queryParam, ssmEexcParam);
            } else {
                queryParam.put("mosCtsiPrfl", "MOS_CTSI_PRFL");
                queryParam.put("orderMOS", orderMOS);
                stmt = this.ssmLLClient.createPreparedStatement("getSoConfirmationMOS", queryParam, ssmEexcParam);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                totalCount++;
                // Insert Work
                insertCarrFrtInfoIntfcWrk(rs);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * sendSoConfirmationTRFDt
     */
    private void sendSoConfirmationTRFDt() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("dsBizLastUpdTs", this.lastUpdTs);
            queryParam.put("sceOrdTpCdTR", SCE_ORD_TP.TECH_REQUEST);
            queryParam.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
            queryParam.put("ntcDropFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("shpgStsCd", SHPG_STS.SHIPPED);
            queryParam.put("batchId", BATCH_ID);
            queryParam.put("sceOrdTpCdDt", SCE_ORD_TP.DC_TRANSFER);
            queryParam.put("lfs", LINE_BIZ_TP.LFS);
            queryParam.put("ess", LINE_BIZ_TP.ESS);
            queryParam.put("pps", LINE_BIZ_TP.PPS);
            queryParam.put("trfCtsiPrfl", "TRF_CTSI_PRFL");
            queryParam.put("orderTRF", orderTRF);
            queryParam.put("CTSI_TRF_DT", "CTSI_TRF_DT");

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getSoConfirmationTRFDt", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                totalCount++;
                // Insert Work
                insertCarrFrtInfoIntfcWrk(rs);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * sendSoConfirmationTRFIr
     */
    private void sendSoConfirmationTRFIr() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("dsBizLastUpdTs", this.lastUpdTs);
            queryParam.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
            queryParam.put("ntcDropFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("shpgStsCd", SHPG_STS.SHIPPED);
            queryParam.put("batchId", BATCH_ID);
            queryParam.put("sceOrdTpCdDt", SCE_ORD_TP.DC_TRANSFER);
            queryParam.put("trxHdrNumTpCd04", "04");
            queryParam.put("orderTRF", orderTRF);
            queryParam.put("CTSI_TRF_IR", "CTSI_TRF_IR");

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getSoConfirmationTRFIr", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                totalCount++;
                // Insert Work
                insertCarrFrtInfoIntfcWrk(rs);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * sendRwsCompleteRAE
     */
    private void sendRwsCompleteRAE() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("dsBizLastUpdTs", this.lastUpdTs);
            queryParam.put("inbdOtbdCd", INBD_OTBD.INBOUND);
            queryParam.put("ntcDropFlg", ZYPConstant.FLG_ON_Y);
            // Del Start 2020/02/21 QC#55942
//            queryParam.put("rwsStsCd", RWS_STS.RECEIVED);
            // Del End 2020/02/21 QC#55942
            queryParam.put("machine", COA_MDSE_TP.MACHINE);
            queryParam.put("sceOrdTpCdRt", SCE_ORD_TP.RETURN);
            queryParam.put("tds", "TDS");
            queryParam.put("dg", "DG");
            queryParam.put("batchId", BATCH_ID);
            queryParam.put("orderRAE", orderRAE);
            queryParam.put("tdsCoaProdCd", tdsCoaProdCd);
            queryParam.put("dgCoaProdCd", dgCoaProdCd);
            // Add Start 2020/02/21 QC#55942
            String ctsiRwsStsCdList = ZYPCodeDataUtil.getVarCharConstValue("CTSI_RWS_STS_CD_LIST", this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(ctsiRwsStsCdList)) {
                String[] rwsStsCdList = ctsiRwsStsCdList.split(",");
                queryParam.put("rwsStsCdList", rwsStsCdList);
            }
            // Add End 2020/02/21 QC#55942

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getRwsCompleteRAE", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                totalCount++;
                // Insert Work
                insertCarrFrtInfoIntfcWrk(rs);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * sendRwsCompletePO
     */
    private void sendRwsCompletePO() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("dsBizLastUpdTs", this.lastUpdTs);
            queryParam.put("inbdOtbdCd", INBD_OTBD.INBOUND);
            queryParam.put("ntcDropFlg", ZYPConstant.FLG_ON_Y);
            // Del Start 2020/02/21 QC#55942
//            queryParam.put("rwsStsCd", RWS_STS.RECEIVED);
            // Del End 2020/02/21 QC#55942
            queryParam.put("mdseItemTpCdParts", MDSE_ITEM_TP.PARTS);
            queryParam.put("mdseItemTpCdSupply", MDSE_ITEM_TP.SUPPLY);
            queryParam.put("trxHdrNumTpCdPO", "01");
            queryParam.put("dummy", RTL_WH_CATG.VIRTUAL);
            queryParam.put("poPartsSrcNm", poPartsSrcNm);
            queryParam.put("poEquipSrcNm", poEquipSrcNm);
            queryParam.put("orderPOP", orderPOP);
            queryParam.put("orderPOE", orderPOE);
            queryParam.put("orderPOS", orderPOS);
            queryParam.put("poGroupId", "NLBB0330_PO");
            // START 2022/03/04 A.Cullano [QC#59776, ADD]
            queryParam.put("cusaID", CUSA_SPLY);
            queryParam.put("cusaPartsID", CUSA_SPLY_PRT);
            queryParam.put("mdseCatgCd", MDSE_CATG.PARTS);
            // END 2022/03/04 A.Cullano [QC#59776, ADD]
            // Add Start 2020/02/21 QC#55942
            String ctsiRwsStsCdList = ZYPCodeDataUtil.getVarCharConstValue("CTSI_RWS_STS_CD_LIST", this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(ctsiRwsStsCdList)) {
                String[] rwsStsCdList = ctsiRwsStsCdList.split(",");
                queryParam.put("rwsStsCdList", rwsStsCdList);
            }
            // Add End 2020/02/21 QC#55942

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getRwsCompletePO", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                totalCount++;
                // Insert Work
                insertCarrFrtInfoIntfcWrk(rs);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * sendRwsCompletePO
     */
    private void sendWarningReport() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("dsBizLastUpdTs", this.lastUpdTs);
            queryParam.put("batchId", BATCH_ID);
            queryParam.put("orderSSD", orderSSD);
            queryParam.put("orderSSO", orderSSO);
            queryParam.put("machine", COA_MDSE_TP.MACHINE);
            queryParam.put("sceOrdTpCdRS", SCE_ORD_TP.DIRECT_SALES);
            queryParam.put("sceOrdTpCdRt", SCE_ORD_TP.RETURN);
            queryParam.put("inbdCd", INBD_OTBD.INBOUND);
            queryParam.put("otbdCd", INBD_OTBD.OUTBOUND);
            queryParam.put("ntcDropFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("dummy", RTL_WH_CATG.VIRTUAL);
            queryParam.put("shpgStsCd", SHPG_STS.SHIPPED);
            queryParam.put("orderRAE", orderRAE);
            // Del Start 2020/02/21 QC#55942
//            queryParam.put("rwsStsCd", RWS_STS.RECEIVED);
            // Del End 2020/02/21 QC#55942
            queryParam.put("mdseItemTpCdParts", MDSE_ITEM_TP.PARTS);
            queryParam.put("mdseItemTpCdSupply", MDSE_ITEM_TP.SUPPLY);
            queryParam.put("trxHdrNumTpCdPO", "01");
            queryParam.put("poPartsSrcNm", poPartsSrcNm);
            queryParam.put("poEquipSrcNm", poEquipSrcNm);
            queryParam.put("orderPOP", orderPOP);
            queryParam.put("orderPOE", orderPOE);
            queryParam.put("orderPOS", orderPOS);
            queryParam.put("poGroupId", "NLBB0330_PO");
            // Add Start 2020/02/21 QC#55942
            String ctsiRwsStsCdList = ZYPCodeDataUtil.getVarCharConstValue("CTSI_RWS_STS_CD_LIST", this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(ctsiRwsStsCdList)) {
                String[] rwsStsCdList = ctsiRwsStsCdList.split(",");
                queryParam.put("rwsStsCdList", rwsStsCdList);
            }
            // Add End 2020/02/21 QC#55942

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getWarningReport", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                warningCount++;
                // Create Message
                sb.append(ZYPCommonFunc.rightPad(rs.getString("CARR_FRT_SRC_TP_NM"), 20, " "));
                sb.append("    ");
                sb.append(ZYPCommonFunc.rightPad(rs.getString("DATA_SOURCE"), 3, " "));
                sb.append("         ");
                sb.append(ZYPCommonFunc.rightPad(rs.getString("CBS_REF_NUM_01"), 8, " "));
                sb.append("            ");
                sb.append(ZYPCommonFunc.rightPad(rs.getString("ORD_TP_CD"), 4, " "));
                sb.append("(" + rs.getString("ORD_TP_NM") + ")");
                sb.append("\r\n");
            }

            if (sb.length() > 0) {
                sb.insert(0, "Source of Data          Order#      CBS Reference # 1   Order Reason or Planning Gruop\r\n");
                sendWarningReport(sb);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * insertCarrFrtInfoIntfcWrk
     * @param rs ResultSet
     */
    private void insertCarrFrtInfoIntfcWrk(ResultSet rs) throws SQLException {

        // Insert CARR_FRT_INFO_INTFC_WRK
        CARR_FRT_INFO_INTFC_WRKTMsg infoIntfcWrk = new CARR_FRT_INFO_INTFC_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.glblCmpyCd, this.glblCmpyCd);
        // Get Carrier Freight Information Interface Work Primary Key
        BigDecimal newPk = ZYPOracleSeqAccessor.getNumberBigDecimal(CARR_FRT_INFO_INTFC_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.carrFrtInfoIntfcWrkPk, newPk);
        // CARR_FRT_INFO_SRC_TP_NM
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.carrFrtSrcTpNm, rs.getString("CARR_FRT_SRC_TP_NM"));
        // CARR_FRT_PRFL_NUM
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.carrFrtPrflNum, rs.getString("CARR_FRT_PRFL_NUM"));
        // TRX_HDR_NUM
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.trxHdrNum, rs.getString("TRX_HDR_NUM"));
        // TRX_REF_NUM_01
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.trxRefNum_01, rs.getString("TRX_REF_NUM_01"));
        // TRX_REF_NUM_02
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.trxRefNum_02, rs.getString("TRX_REF_NUM_02"));
        // COA_CMPY_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaCmpyCd, rs.getString("COA_CMPY_CD"));
        // COA_BR_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaBrCd, rs.getString("COA_BR_CD"));
        // COA_CC_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaCcCd, rs.getString("COA_CC_CD"));
        // COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaAcctCd, rs.getString("COA_ACCT_CD"));
        // COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaProdCd, rs.getString("COA_PROD_CD"));
        // COA_CH_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaChCd, rs.getString("COA_CH_CD"));
        // COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaAfflCd, rs.getString("COA_AFFL_CD"));
        // COA_PROJ_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.coaProjCd, rs.getString("COA_PROJ_CD"));
        // CARR_FRT_CHRG_BU_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.carrFrtChrgBuCd, rs.getString("CARR_FRT_CHRG_BU_CD"));
        // CARR_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.carrCd, rs.getString("CARR_CD"));
        // INVTY_TRX_DT SHPG_ORD_CONF.SHIP_DT_TM_TS *YYYYMMDD
        String shipDtTmTs = rs.getString("INVTY_TRX_DT");
        if (!ZYPDateUtil.isValidDate(shipDtTmTs, INVTY_TRX_DT_FORMAT_AF)) {
            ZYPEZDItemValueSetter.setValue(infoIntfcWrk.invtyTrxDt, ZYPDateUtil.DateFormatter(shipDtTmTs, INVTY_TRX_DT_FORMAT_BF, INVTY_TRX_DT_FORMAT_AF));
        } else {
            ZYPEZDItemValueSetter.setValue(infoIntfcWrk.invtyTrxDt, shipDtTmTs);
        }
        // FROM_POST_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.fromPostCd, rs.getString("FROM_POST_CD"));
        // TO_POST_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.toPostCd, rs.getString("TO_POST_CD"));
        // CARR_FRT_INFO_INTFC_PROC_CD
        ZYPEZDItemValueSetter.setValue(infoIntfcWrk.carrFrtInfoIntfcProcCd, "0");

        EZDTBLAccessor.insert((CARR_FRT_INFO_INTFC_WRKTMsg) infoIntfcWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(infoIntfcWrk.getReturnCode())) {
            // insertErrorLogOutPut(newPk, CARR_FRT_INFO_INTFC_WRK);
            StringBuilder sbKey = new StringBuilder();
            sbKey.append(SO_NUM);
            sbKey.append(MSG_TXT_EQUALS);
            sbKey.append(rs.getString("TRX_REF_NUM_01"));
            throw new S21AbendException(MSG_ID_NLAM1270E, new String[] {CARR_FRT_INFO_INTFC_WRK, sbKey.toString() });
            // errorCount++;
        } else {
            successCount++;
        }
    }

    /**
     * sendEMailToUser send e-Mail to order manager
     */
    private void sendWarningReport(StringBuilder sb) {

        // Get From
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, BATCH_ID);
        groupFrom.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // Get To
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, BATCH_ID);
        groupTo.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NLBM1065E);
        }

        // Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (template == null) {
            throw new S21AbendException(NLBM1065E);
        }

        template.setTemplateParameter("dsBizLastUpdTs", ZYPDateUtil.formatEzd14ToDisp(this.lastUpdTs));
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, sb.toString());

        // Call Mail API
        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }
}
