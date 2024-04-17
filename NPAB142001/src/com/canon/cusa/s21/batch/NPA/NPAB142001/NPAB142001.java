/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB142001;

import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.ASTERISK;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.DB_SEQ_CVI_ORD_STS_INFO_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.ERR_TBL_VAR_CHAR_CONST;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.FORMAT_YYYYMMDD;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.FORMAT_YYYYMMDDHHMMSSSSS;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.LEN_PO_ORD_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MAIL_DATE_TIME_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MAIL_FIELD_BATCH_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MAIL_FIELD_ERR_DATE;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MAIL_FIELD_MESSAGE;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MAIL_GROUP_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MAIL_TEMPLATE_NPAB1420M001;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MSG_TXT_CVI_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MSG_TXT_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.MSG_TXT_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAB1420_CVI_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_BL_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_DELY_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_DELY_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_ETD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_ORD_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_ORD_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_ORD_SHIP_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_RSD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_SHIP_METH_DESC_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_SHIP_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_SHIP_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_SHPG_STS_DESC_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_CVI_VND_CPO_REF_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_PO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_SPLY_ITEM_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_VND_ISS_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAI222001_VND_ISS_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAM1265E;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAM1266E;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAM1300E;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.NPAM1337W;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.PROC_STS_CD_IN_COMPLETED;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.VAL_ZERO;
import static com.canon.cusa.s21.batch.NPA.NPAB142001.constant.NPAB142001Constant.ZZXM0020E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CVI_ORD_STS_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAB142001 : CVI Sales Order Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2016   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NPAB142001 extends S21BatchMain {

    /** Global Company Code. */
    private String glblCmpyCd = null;

    /** User Profile Service. */
    private S21UserProfileService profileService = null;

    /** Interface ID. */
    private String interfaceId = null;

    /** Vendor Code. */
    private String cviVndCd = null;

    /** TableAccessor. */
    private S21TransactionTableAccessor accessor = null;

    /** Termination code. */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total count. */
    private int totalCount = 0;

    /** Success count. */
    private int successCount = 0;

    /** Error count. */
    private int errorCount = 0;

    /** Mail Error Message. */
    private String mailMessage = null;

    /** SSM Batch Client. */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Execution Parameter. */
    private S21SsmExecutionParameter ssmEexcParam = null;

    /** Lead Time Days. */
    private int leadTimeDays = 0;

    /** Vendor Order Number. */
    private String vndIssOrdNum = null;

    /** Vendor PO Number. */
    private String vndIssPoOrdNum = null;

    @Override
    protected void initRoutine() {

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        glblCmpyCd = profileService.getGlobalCompanyCode();

        /** Parameter Check : Global Company Code */
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZXM0020E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        /** Parameter Check : Interface ID */
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(ZZXM0020E, new String[] {MSG_TXT_INTERFACE_ID });
        }

        /** Search Key Check : VAR_CHAR_CONST */
        cviVndCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB1420_CVI_VND_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cviVndCd)) {
            throw new S21AbendException(NPAM1337W, new String[] {MSG_TXT_CVI_VND_CD, ERR_TBL_VAR_CHAR_CONST, NPAB1420_CVI_VND_CD });
        }

        // Initialization of SSM
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        ssmEexcParam = new S21SsmExecutionParameter();
        ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmEexcParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

    }

    @Override
    protected void mainRoutine() {

        accessor = new S21TransactionTableAccessor();
        BigDecimal[] transactionIdList = accessor.getIntegrationRecord(this.interfaceId);

        // Import
        if (transactionIdList.length > 0) {
            importOrderStatusInfo(transactionIdList);
        }

        if (ZYPCommonFunc.hasValue(this.mailMessage)) {
            sendErrorMail();
            commit();
        }

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {

        setTermState(termCd, successCount, errorCount);
    }

    /**
     * main : initialize
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NPAB142001().executeBatch(NPAB142001.class.getSimpleName());
    }

    /**
     * importOrderStatusInfo : main logic
     * @param transactionIdList
     */
    private void importOrderStatusInfo(BigDecimal[] transactionIdList) {

        for (BigDecimal tranId : transactionIdList) {

            // Select
            List<Map<String, Object>> receivingOrderStatusList = selectOrderStatusInfo(tranId);

            // Insert
            if (!importOrderStatusInfo(receivingOrderStatusList)) {
                rollback();
                this.errorCount = this.errorCount + receivingOrderStatusList.size();
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            accessor.endIntegrationProcess(this.interfaceId, tranId);
            commit();
        }
    }

    /**
     * selectOrderStatusInfo : sub program
     * @param tranId
     * @return
     */
    private List<Map<String, Object>> selectOrderStatusInfo(BigDecimal tranId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.interfaceId);
        ssmParam.put("transactionId", tranId);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("vndCd", this.cviVndCd);

        List<Map<String, Object>> inventoryList = this.ssmBatchClient.queryObjectList("getReceivingOrderStatus", ssmParam, ssmEexcParam);

        return inventoryList;
    }

    /**
     * importOrderStatusInfo : sub program
     * @param receivingOrderStatusList
     * @return
     */
    private boolean importOrderStatusInfo(List<Map<String, Object>> receivingOrderStatusList) {

        this.totalCount = this.totalCount + receivingOrderStatusList.size();

        for (Map<String, Object> orderStatusList : receivingOrderStatusList) {

            // get lead time
            String poOrdDtlLineNum = (String) orderStatusList.get(NPAI222001_PO_DTL_LINE_NUM);
            poOrdDtlLineNum = ZYPCommonFunc.leftPad(poOrdDtlLineNum, LEN_PO_ORD_DTL_LINE_NUM, VAL_ZERO);

            BigDecimal delyLeadAot = getLeadTime((String) orderStatusList.get(NPAI222001_PO_ORD_NUM), poOrdDtlLineNum, (String) orderStatusList.get(NPAI222001_CVI_SHIP_METH_DESC_TXT));
            this.leadTimeDays = Integer.parseInt(delyLeadAot.toPlainString());

            this.vndIssOrdNum = (String) orderStatusList.get(NPAI222001_VND_ISS_ORD_NUM);
            this.vndIssPoOrdNum = (String) orderStatusList.get(NPAI222001_VND_ISS_PO_ORD_NUM);

            // Insert
            if (!createCVIOrderStatusInfo(orderStatusList)) {
                this.mailMessage = S21MessageFunc.clspGetMessage(NPAM1300E);
                return false;
            }
        }

        this.successCount = this.successCount + receivingOrderStatusList.size();
        return true;
    }

    /**
     * createCVIOrderStatusInfo : sub program
     * @param orderStatusList
     * @param vndIssOrdNum
     * @param vndIssPoOrdNum
     * @return
     */
    private boolean createCVIOrderStatusInfo(Map<String, Object> orderStatusList) {

        // Insert.
        CVI_ORD_STS_INFOTMsg createData = new CVI_ORD_STS_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(createData.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(createData.cviOrdStsInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(DB_SEQ_CVI_ORD_STS_INFO_SQ));
        ZYPEZDItemValueSetter.setValue(createData.cviOrdNum, (String) orderStatusList.get(NPAI222001_CVI_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(createData.cviOrdLineNum, (String) orderStatusList.get(NPAI222001_CVI_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(createData.cviOrdShipNum, (String) orderStatusList.get(NPAI222001_CVI_ORD_SHIP_NUM));
        ZYPEZDItemValueSetter.setValue(createData.cviVndCpoRefNum, (String) orderStatusList.get(NPAI222001_CVI_VND_CPO_REF_NUM));
        ZYPEZDItemValueSetter.setValue(createData.vndIssOrdNum, this.vndIssOrdNum);
        ZYPEZDItemValueSetter.setValue(createData.vndIssPoOrdNum, this.vndIssPoOrdNum);
        ZYPEZDItemValueSetter.setValue(createData.poOrdNum, (String) orderStatusList.get(NPAI222001_PO_ORD_NUM));

        if (ZYPCommonFunc.hasValue((String) orderStatusList.get(NPAI222001_PO_DTL_LINE_NUM))) {
            ZYPEZDItemValueSetter.setValue(createData.poDtlLineNum, new BigDecimal(orderStatusList.get(NPAI222001_PO_DTL_LINE_NUM).toString()));
        }

        ZYPEZDItemValueSetter.setValue(createData.mdseCd, (String) orderStatusList.get(NPAI222001_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(createData.splyItemNum, (String) orderStatusList.get(NPAI222001_SPLY_ITEM_NUM));
        ZYPEZDItemValueSetter.setValue(createData.cviOrdQty, (BigDecimal) orderStatusList.get(NPAI222001_CVI_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(createData.cviShipQty, (BigDecimal) orderStatusList.get(NPAI222001_CVI_SHIP_QTY));

        String etaDt = null;
        String cviRsdTs = (String) orderStatusList.get(NPAI222001_CVI_RSD_TS);
        String cviShipTs = (String) orderStatusList.get(NPAI222001_CVI_SHIP_TS);
        String cviEtdTs = (String) orderStatusList.get(NPAI222001_CVI_ETD_TS);

        if (ZYPCommonFunc.hasValue(cviShipTs)) {
            String cviShipDt = ZYPDateUtil.DateFormatter(cviShipTs, FORMAT_YYYYMMDDHHMMSSSSS, FORMAT_YYYYMMDD);
            etaDt = ZYPDateUtil.addDays(cviShipDt, leadTimeDays);
        } else if (ZYPCommonFunc.hasValue((cviEtdTs))) {
            String cviEtdDt = ZYPDateUtil.DateFormatter(cviEtdTs, FORMAT_YYYYMMDDHHMMSSSSS, FORMAT_YYYYMMDD);
            etaDt = ZYPDateUtil.addDays(cviEtdDt, leadTimeDays);
        }

        ZYPEZDItemValueSetter.setValue(createData.etaDt, etaDt);
        ZYPEZDItemValueSetter.setValue(createData.cviRsdTs, cviRsdTs);
        ZYPEZDItemValueSetter.setValue(createData.cviEtdTs, cviEtdTs);
        ZYPEZDItemValueSetter.setValue(createData.cviShipTs, cviShipTs);
        ZYPEZDItemValueSetter.setValue(createData.cviShpgStsDescTxt, (String) orderStatusList.get(NPAI222001_CVI_SHPG_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(createData.cviBlNum, (String) orderStatusList.get(NPAI222001_CVI_BL_NUM));
        ZYPEZDItemValueSetter.setValue(createData.cviDelyNum, (String) orderStatusList.get(NPAI222001_CVI_DELY_NUM));
        ZYPEZDItemValueSetter.setValue(createData.cviDelyLineNum, (String) orderStatusList.get(NPAI222001_CVI_DELY_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(createData.cviShipMethDescTxt, (String) orderStatusList.get(NPAI222001_CVI_SHIP_METH_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(createData.procStsCd, PROC_STS_CD_IN_COMPLETED);

        EZDTBLAccessor.create(createData);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(createData.getReturnCode())) {
            return false;
        }

        return true;
    }

    /**
     * sendErrorMail
     */
    private void sendErrorMail() {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);

        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID);

        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.isEmpty()) {
            throw new S21AbendException(NPAM1265E);
        }

        // Get Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_NPAB1420M001);

        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAM1266E, new String[] {MAIL_TEMPLATE_NPAB1420M001 });
        }

        template.setTemplateParameter(MAIL_FIELD_BATCH_ID, PROGRAM_ID);
        template.setTemplateParameter(MAIL_FIELD_ERR_DATE, ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT));
        template.setTemplateParameter(MAIL_FIELD_MESSAGE, this.mailMessage);

        // Send Mail.
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    /**
    * getLeadTime
    * @param poOrdNum String
    * @param poOrdDtlLineNum String
    * @param asnShipMethTxt String
    * @return BigDecimal VND_SHIP_LT.DELY_LEAD_AOT
    * @throws SQLException 
    */
    private BigDecimal getLeadTime(String poOrdNum, String poOrdDtlLineNum, String asnShipMethTxt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        BigDecimal delyLeadAot = BigDecimal.ZERO;

        // get SHIP_TO_ST_CD,SHPG_SVC_LVL_CD from PO
        List<Map<String, Object>> poInfo = new ArrayList<Map<String,Object>>();
        if (ZYPCommonFunc.hasValue(poOrdNum) //
                && ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
            Map<String, Object> ssmParamPoInfo = new HashMap<String, Object>();
            ssmParamPoInfo.put("glblCmpyCd", this.glblCmpyCd);
            ssmParamPoInfo.put("poOrdNum", poOrdNum);
            ssmParamPoInfo.put("poOrdDtlLineNum", poOrdDtlLineNum);
            poInfo = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPoInfo", ssmParamPoInfo);
        }

        String stCd = null;
        String vndShipMethCd = null;
        if (poInfo != null && !poInfo.isEmpty()) {
            stCd = (String) poInfo.get(0).get("SHIP_TO_ST_CD");
            vndShipMethCd = (String) poInfo.get(0).get("SHPG_SVC_LVL_CD");
        }
        if (!ZYPCommonFunc.hasValue(stCd)) {
            stCd = ASTERISK;
        }
        if (!ZYPCommonFunc.hasValue(vndShipMethCd)) {
            vndShipMethCd = ASTERISK;
        }

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("vndCd", this.cviVndCd);
        queryParam.put("destStCd", stCd);
        queryParam.put("vndShipMethCd", vndShipMethCd);
        queryParam.put("ediAsnShipMethTxt", asnShipMethTxt);
        queryParam.put("ast", ASTERISK);
        BigDecimal azertyLtInfo = (BigDecimal) this.ssmBatchClient.queryObject("getPoAsnLeadTime", queryParam);
        if (azertyLtInfo != null) {
            delyLeadAot = azertyLtInfo;
        }

        return delyLeadAot;
    }
}
