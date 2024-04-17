/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB075001;

import static com.canon.cusa.s21.batch.NSA.NSAB075001.constant.NSAB075001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.parts.NSZC052001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC052001.NSZC052001;
import com.canon.cusa.s21.batch.NSA.NSAB075001.constant.NSAB075001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
/**
 *<pre>
 * Sub Contract Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/05   Hitachi       K.Yamada             Create          N/A
 * 2017/02/23   Hitachi       Y.Takeno             Update          QC#14675-1
 * 2017/02/28   Hitachi       Y.Takeno             Update          QC#14675-2
 * 2017/03/08   Hitachi       Y.Takeno             Update          QC#14675-3
 * 2017/09/16   Hitachi       Y.Takeno             Update          QC#21153
 * 2017/10/20   Hitachi       U.Kim                Update          QC#21840
 *</pre>
 */

public class NSAB075001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Sales Date */
    private String salesDate;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** Warning Send Flag */
    private String warnSendFlg;

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0177E);
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSAM0178E);
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        doProcess();

    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB075001().executeBatch(NSAB075001.class.getSimpleName());
    }

    /**
     * doProcess
     */
    private void doProcess() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            stmt = ssmLLClient.createPreparedStatement("getTargetDataList", getTargetDataListParams(), execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // START 2017/03/08 [QC#16285-3, DEL]
                // START 2017/02/24 [QC#16285-1, ADD]
                // if (isSkipData(rs)) {
                //    continue;
                // }
                // END   2017/02/24 [QC#16285-1, ADD]
                // END   2017/03/08 [QC#16285-3, DEL]

               if (processRequestData(rs)) {
                    this.normalCount++;
                    commit();

                } else {
                    this.errorCount++;
                    rollback();
                }

            }

            if (!errMsgList.isEmpty()) {
                sendMail();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    private Map<String, Object> getTargetDataListParams() {
        Map<String, Object> prms = new HashMap<String, Object>();
        prms.put("glblCmpyCd", this.glblCmpyCd);
        prms.put("salesDate", this.salesDate);
        prms.put("subContrCrtPrcCdDflt", SUB_CONTR_CRAT_PROC_CD_DEFAULT);
        prms.put("stsCanc", DS_CONTR_CTRL_STS.CANCELLED);
        prms.put("stsExpd", DS_CONTR_CTRL_STS.EXPIRED);
        prms.put("stsTrmd", DS_CONTR_CTRL_STS.TERMINATED);
        prms.put("bwMtrType", METER_TYPE_BLACK);
        prms.put("colorMtrType", METER_TYPE_COLOR);
        prms.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // START 2017/02/24 [QC#16285-1, ADD]
        prms.put("maxDate", this.salesDate);
        // END   2017/02/24 [QC#16285-1, ADD]
        // START 2017/03/01 [QC#16285-2, ADD]
        prms.put("flgY", ZYPConstant.FLG_ON_Y);
        // END   2017/03/01 [QC#16285-2, ADD]

        return prms;
    }

    // START 2017/03/08 [QC#16285-3, DEL]
    // START 2017/02/24 [QC#16285-1, ADD]
    // private boolean isSkipData(ResultSet rs) throws SQLException {
    //     if (ZYPCommonFunc.hasValue(rs.getString("ITT_NUMBER")) && !ITT_HEADER_ITT_STATUS_CLOSED.equals(rs.getString("ITT_HEADER_ITT_STATUS"))) {
    //         return true;
    //     }
    //     return false;
    // }
    // END   2017/02/24 [QC#16285-1, ADD]
    // END   2017/03/08 [QC#16285-3, DEL]

    private boolean processRequestData(ResultSet rs) throws SQLException {

// START 2017/09/16 [QC#21153, MOD]
        return callSubContractCreation(rs);
//        if (ZYPCommonFunc.hasValue(rs.getString("ITT_NUMBER"))) {
//
//            // Create SubContract
//            return callSubContractCreation(rs);
//        }
//        return true;
//
//        } else {
//            // UpDateTBL DS_CONTR_DTL
//            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
//
//            setValue(inMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
//            setValue(inMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
//            inMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
//
//            setValue(inMsg.subContrCratProcCd, SUB_CONTR_CRAT_PROC_CD_UPDATED);
//            S21FastTBLAccessor.update(inMsg);
//
//            String rtnCd = inMsg.getReturnCode();
//            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//
//                this.errMsgList.add(S21MessageFunc.clspGetMessage("NSBM0073E", new String[] {DS_CONTR_DTL}) + "Table[" + "DS_CONTR_DTL_PK =" + rs.getString("DS_CONTR_DTL_PK") + "]");
//
//                return false;
//            }
//            return true;
//        }
// END   2017/09/16 [QC#21153, MOD]
    }


    private boolean callSubContractCreation(ResultSet rs) throws SQLException {
        NSZC052001PMsg pMsg = new NSZC052001PMsg();
        setParamForNSZC052001(pMsg, rs);

        NSZC052001 apiNSZC052001 = new NSZC052001();
        apiNSZC052001.execute(pMsg, ONBATCH_TYPE.BATCH);
        boolean normalFlag = true;
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            normalFlag = isNormalMsg(xxMsgIdList);

            List<S21ApiMessage> apiMsgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage apiMsg : apiMsgList) {
                String xxMsgId = apiMsg.getXxMsgid();
                if (xxMsgId.endsWith("W") && this.warnSendFlg.equals(ZYPConstant.FLG_OFF_N)) {
                    continue;
                }
                StringBuilder builder = new StringBuilder();
                builder.append(S21MessageFunc.clspGetMessage(xxMsgId, apiMsg.getXxMsgPrmArray()));
                builder.append("Table[" + "DS_CONTR_DTL_PK =" + rs.getString("DS_CONTR_DTL_PK") + "]");
                this.errMsgList.add(builder.toString());
            }
        }
        return normalFlag;
    }


    private void setParamForNSZC052001(NSZC052001PMsg pMsg, ResultSet rs) throws SQLException {

        if (pMsg == null || rs == null) {
            return;
        }

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.serNum, rs.getString("SER_NUM"));
        setValue(pMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        // START 2017/10/20 U.Kim [QC#21840, ADD]
        String contractType = ZYPCodeDataUtil.getVarCharConstValue(NSAB075001Constant.CANON_E580_SERVICE_PRICING_ICV, glblCmpyCd);
        String[] contractTypeList = new String[]{};
        // END 2017/10/20 U.Kim [QC#21840, ADD]

        pMsg.xxSubContrList.getValidCount();
        setValue(pMsg.xxSubContrList.no(0).vndCd, rs.getString("VND_CD"));
        setValue(pMsg.xxSubContrList.no(0).techCd, rs.getString("TECH_TOC_CD"));
        setValue(pMsg.xxSubContrList.no(0).effFromDt, rs.getString("CONTR_EFF_FROM_DT"));
        setValue(pMsg.xxSubContrList.no(0).effThruDt, addMonths(rs.getString("CONTR_EFF_FROM_DT"), getTermInt(rs)));
        setValue(pMsg.xxSubContrList.no(0).contrTrmnFlg,  ZYPConstant.FLG_OFF_N);
        setValue(pMsg.xxSubContrList.no(0).basePrcDealAmt, getBasePrcDealAmt(rs));
        setValue(pMsg.xxSubContrList.no(0).adminPrcDealAmt, BigDecimal.ZERO);
        setValue(pMsg.xxSubContrList.no(0).prepdMaintFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.xxSubContrList.no(0).bwMtrAlwncCnt, rs.getBigDecimal("BW_MTR_ALWNC_CNT"));
        setValue(pMsg.xxSubContrList.no(0).colorMtrAlwncCnt, rs.getBigDecimal("COLOR_MTR_ALWNC_CNT"));
        setValue(pMsg.xxSubContrList.no(0).bwMtrPrcAmtRate, rs.getBigDecimal("BW_MTR_PRC_AMT_RATE"));
        setValue(pMsg.xxSubContrList.no(0).colorMtrPrcAmtRate, rs.getBigDecimal("COLOR_MTR_PRC_AMT_RATE"));
        // START 2017/10/20 U.Kim [QC#21840, MOD]
        // setValue(pMsg.xxSubContrList.no(0).splyInclFlg,ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(contractType)) {
            contractTypeList = contractType.split("\\|");
        }
        if (Arrays.asList(contractTypeList).contains(rs.getString("CONTRACT_TYPE"))) {
            setValue(pMsg.xxSubContrList.no(0).splyInclFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(pMsg.xxSubContrList.no(0).splyInclFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2017/10/20 U.Kim [QC#21840, MOD]
        setValue(pMsg.xxSubContrList.no(0).dlrFleetFlg,  ZYPConstant.FLG_OFF_N);
        setValue(pMsg.xxSubContrList.no(0).bllgCycleCd, getBllgCycleCd(rs));

        pMsg.xxSubContrList.setValidCount(1);
    }

    private int getTermInt(ResultSet rs)  throws SQLException {

        int term = 0;
        if (METER_TYPE_BLACK.equals(rs.getString("BW_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("BW_TERM"))) {
            term = rs.getInt("BW_TERM");

        } else if (METER_TYPE_COLOR.equals(rs.getString("COLOR_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("COLOR_TERM"))) {
            term = rs.getInt("COLOR_TERM");
        }
        return term;
    }

    private  String addMonths(String date, int term) {
        SimpleDateFormat format = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, term);
        // START 2017/02/23 [QC#16285-1, ADD]
        cal.add(Calendar.DATE, -1);
        // END   2017/02/23 [QC#16285-1, ADD]
        return format.format(cal.getTime());
    }

    private BigDecimal getBasePrcDealAmt(ResultSet rs)  throws SQLException {

        BigDecimal basePrcDealAmt = null;
        if (METER_TYPE_BLACK.equals(rs.getString("BW_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("BW_BASE_PRC_DEAL_AMT"))) {
            basePrcDealAmt = rs.getBigDecimal("BW_BASE_PRC_DEAL_AMT");

        } else if (METER_TYPE_COLOR.equals(rs.getString("COLOR_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("COLOR_BASE_PRC_DEAL_AMT"))) {
            basePrcDealAmt = rs.getBigDecimal("COLOR_BASE_PRC_DEAL_AMT");
        }
        return basePrcDealAmt;
    }


    private String getBllgCycleCd(ResultSet rs) throws SQLException {

        String bllgCycle = null;
        if (METER_TYPE_BLACK.equals(rs.getString("BW_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("BW_BASE_BLLG_CYCLE"))) {
            bllgCycle = rs.getString("BW_BASE_BLLG_CYCLE");

        } else if (METER_TYPE_BLACK.equals(rs.getString("BW_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("BW_OVERAGE_BLLG_CYCLE"))) {
            bllgCycle = rs.getString("BW_OVERAGE_BLLG_CYCLE");

        } else if (METER_TYPE_COLOR.equals(rs.getString("COLOR_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("COLOR_BASE_BLLG_CYCLE"))) {
            bllgCycle = rs.getString("COLOR_BASE_BLLG_CYCLE");

        } else if (METER_TYPE_COLOR.equals(rs.getString("COLOR_METER_TYPE")) && ZYPCommonFunc.hasValue(rs.getString("COLOR_OVERAGE_BLLG_CYCLE"))) {
            bllgCycle = rs.getString("COLOR_OVERAGE_BLLG_CYCLE");
        }

        // START 2017/02/23 [QC#16285-1, ADD]
        if (!ZYPCommonFunc.hasValue(bllgCycle)) {
            return null;
        }
        // END   2017/02/23 [QC#16285-1, ADD]

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        return  (String) ssmBatchClient.queryObject("getbllgCycleCd", getBllgCycleCdParams(bllgCycle), execParam);
    }


    private  Map<String, Object> getBllgCycleCdParams(String bllgCycle) {
        Map<String, Object> prms = new HashMap<String, Object>();
        prms.put("glblCmpyCd", this.glblCmpyCd);
        prms.put("bllgCycle", bllgCycle);

        return  prms;
    }

    // mail
    private void sendMail() {

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!fromAddrList.isEmpty()) {
            from = fromAddrList.get(0);
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        String newLine = System.getProperty("line.separator");
        StringBuilder msgBuf = new StringBuilder();
        for (String msg : this.errMsgList) {
            msgBuf.append(msg);
            msgBuf.append(newLine);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, msgBuf.toString());

        // Call Mail API
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }


    private boolean isNormalMsg(List<String> xxMsgIdList) {
        for (String xxMsgId : xxMsgIdList) {
            if (xxMsgId.endsWith("E")) {
                return false;
            }
        }
        return true;
    }
}
