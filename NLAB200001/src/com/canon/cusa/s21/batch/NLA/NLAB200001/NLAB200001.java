/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB200001;

import static com.canon.cusa.s21.batch.NLA.NLAB200001.constant.NLAB200001Constant.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC206001_RWSPutAwayListPMsg;
import business.parts.NLZC206001_RcvSerNumListPMsg;
import business.parts.NLZC207001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SerialInfo;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SvcMachMstrCheck;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Auto Receiving Orders Receipt
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2023   Hitachi         M.Nakajima      Create          N/A
 *</pre>
 */
public class NLAB200001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Error Count */
    private int errorCount = 0;

    /** The number of success */
    private int successCount = 0;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Execute Parameter */
    private S21SsmExecutionParameter execParam = null;

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;;

    /** Error List */
    private List<String> errorList = new ArrayList<String>();

    /** Serial Number Map */
    private Map<String, String> serNumMap = new HashMap<String, String>();

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        new NLAB200001().executeBatch(NLAB200001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Set the fetch size.
        this.execParam = new S21SsmExecutionParameter();
        this.execParam.setFetchSize(FETCH_SIZE);
        this.execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        this.execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        this.execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Global Company Code
        this.glblCmpyCd = profileService.getGlobalCompanyCode();

        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {

        try {

            autoReceive();

        } finally {

            if (!isEmpty(this.errorList)) {

                if (!postErrorMail()) {
                    this.termCd = TERM_CD.ABNORMAL_END;
                    throw new S21AbendException(NLAM1365E);
                }
                this.termCd = TERM_CD.WARNING_END;
            }

        }
        commit();
    }

    @Override
    protected void termRoutine() {

        String[] msg = new String[] {BATCH_ID, "executed", String.valueOf(successCount + errorCount) };

        S21InfoLogOutput.println(ZZBM0009I, msg);

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, successCount + errorCount);

    }

    private void autoReceive() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = ssmLLClient.createPreparedStatement("getRws", setBindParam(), execParam);
            rs = stmt.executeQuery();

            // Loop by RWS Line#
            while (rs.next()) {

                boolean errFlg = false;

                // Input Check
                if (!inputCheck(rs)) {
                    errorCount++;
                    continue;
                }

                // Put Away
                if (!receivePutAway(rs)) {
                    errFlg = true;
                }

                // RWS Close
                if (checkAllCloseRws(rs) || ZYPConstant.FLG_OFF_N.equals(rs.getString(COL_DS_COND_CONST_VAL_TXT_01))) {

                    if (!closeRws(rs)) {
                        errFlg = true;
                    }
                }

                if (errFlg) {
                    ++errorCount;
                    rollback();

                } else {
                    ++successCount;
                    commit();
                }
            }

        } catch (SQLException e) {

            sqlExceptionHandler(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private Map<String, Object> setBindParam() {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("inbdOtbdCd", INBD_OTBD.INBOUND);
        params.put("bizAppId", SCE_ORD_TP_APP_ID);
        params.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID);
        params.put("rowNum", ROW_NUM);

        String avalRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL2020_AUTO_RTL_WH_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalRtlWhCd)) {
            String[] avalRtlWhCdArray = avalRtlWhCd.split(",");
            params.put("avalRtlWhCdArray", avalRtlWhCdArray);
        }

        return params;

    }

    private boolean inputCheck(ResultSet rs) throws SQLException {

        // Check Receiving QTY
        if (!checkRcvQty(rs)) {
            return false;
        }

        // Check Serial
        if (!checkSerial(rs)) {
            return false;
        }

        return true;
    }

    private boolean checkRcvQty(ResultSet rs) throws SQLException {

        String rwsNum = rs.getString(COL_RWS_NUM);
        String rwsLineNum = rs.getString(COL_RWS_DTL_LINE_NUM);
        String rwsBalQty = rs.getString(COL_RWS_BAL_QTY);

        if (!ZYPCommonFunc.hasValue(rwsBalQty)) {

            setErrorList(NLAM1360E, new String[] {"Receiving Qty" }, rwsNum, rwsLineNum);
            return false;

        } else if (rwsBalQty.compareTo("0") <= 0) {

            setErrorList(NLAM1361E, new String[] {"Receiving Qty", "0" }, rwsNum, rwsLineNum);
            return false;

        } else if (rwsBalQty.compareTo(rs.getString(COL_PO_BAL_QTY)) > 0) {

            setErrorList(NLAM1362E, new String[] {"Receiving Qty", "Unstocked Qty" }, rwsNum, rwsLineNum);
            return false;

        }
        return true;
    }

    private boolean checkSerial(ResultSet rs) throws SQLException {

        if (!ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_SER_NUM_TAKE_FLG))) {
            return true;
        }

        String rwsNum = rs.getString(COL_RWS_NUM);
        String rwsLineNum = rs.getString(COL_RWS_DTL_LINE_NUM);

        List<Map<String, String>> serNumList = getSerNumList(rs);

        if (serNumList == null || serNumList.isEmpty()) {

            setErrorList(NLAM1360E, new String[] {"Serial Number" }, rwsNum, rwsLineNum);
            return false;

        } else {

            int serNumCnt = 0;
            List<String> checkedSerNumList = new ArrayList<String>();
            this.serNumMap.clear();

            for (Map<String, String> serNum : serNumList) {

                String serNumVal = serNum.get(COL_SER_NUM);

                // Duplicate Check in RWS_PUT_AWAY_SER
                if (!checkDuplicateRwsPutAwaySer(rs, serNumVal)) {

                    setErrorList(NLAM1338E, null, rwsNum, rwsLineNum);
                    return false;

                }

                // Duplicate Check in Line
                if (rs.getString(COL_RWS_BAL_QTY).compareTo("1") > 0) {

                    if (checkedSerNumList.contains(serNumVal)) {

                        setErrorList(NLAM1363E, null, rwsNum, rwsLineNum);
                        return false;

                    }
                }

                serNumCnt++;
                checkedSerNumList.add(serNumVal);
            }

            if (serNumCnt != rs.getInt(COL_RWS_BAL_QTY)) {

                setErrorList(NLAM1364E, null, rwsNum, rwsLineNum);
                return false;

            }

            if (!isEmpty(checkedSerNumList)) {

                this.serNumMap.put(rwsNum + rwsLineNum, append(checkedSerNumList));

            }

            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_INSTL_BASE_CTRL_FLG)) && ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_SER_NUM_TAKE_FLG))) {

                String errMsgId = checkMachMstr(rs);

                if (ZYPCommonFunc.hasValue(errMsgId)) {

                    setErrorList(errMsgId, null, rwsNum, rwsLineNum);
                    return false;
                }
            }
        }

        return true;
    }

    private List<Map<String, String>> getSerNumList(ResultSet rs) throws SQLException {

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put("glblCmpyCd", this.glblCmpyCd);
        bindParam.put("poOrdNum", rs.getString(COL_PO_ORD_NUM));
        bindParam.put("poOrdDtlLineNum", rs.getString(COL_PO_ORD_DTL_LINE_NUM));

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getSerNumList", bindParam, execParam);
    }

    private boolean checkDuplicateRwsPutAwaySer(ResultSet rs, String serNum) throws SQLException {

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("rwsNum", rs.getString(COL_RWS_NUM));
        param.put("mdseCd", rs.getString(COL_MDSE_CD));
        param.put("serNum", serNum);

        String result = (String) ssmBatchClient.queryObject("checkDuplicateRwsPutAwaySer", param, execParam);

        // Duplicate Error
        if (ZYPCommonFunc.hasValue(result)) {

            return false;
        }

        return true;
    }

    private String checkMachMstr(ResultSet rs) throws SQLException {

        String rwsNum = rs.getString(COL_RWS_NUM);
        String rwsLineNum = rs.getString(COL_RWS_DTL_LINE_NUM);

        String serNums = this.serNumMap.get(rwsNum + rwsLineNum);
        List<String> serNumList = split(serNums);

        List<String> errMsgIdList = new ArrayList<String>();

        if (!isEmpty(serNumList)) {

            for (String serNum : serNumList) {

                NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();

                serialInfo.setGlblCmpyCd(this.glblCmpyCd);
                serialInfo.setDupChkCd(ZYPCodeDataUtil.getVarCharConstValue(SER_DUP_CHK_CD, this.glblCmpyCd));
                serialInfo.setSceOrdTpCd(rs.getString(COL_SCE_ORD_TP_CD));
                serialInfo.setMdseCd(rs.getString(COL_SCE_ORD_TP_CD));
                serialInfo.setSerNum(serNum);
                serialInfo.setShipFromLocCd(rs.getString(COL_SHIP_FROM_LOC_CD));
                serialInfo.setRcvRtlWhCd(rs.getString(COL_RCV_RTL_WH_CD));
                serialInfo.setTrxHdrNum(rs.getString(COL_SER_ALLOC_TRX_HDR_NUM));
                serialInfo.setRwsNum(rwsNum);
                serialInfo.setOrdSvcConfigMstrPk(rs.getBigDecimal(COL_SVC_CONFIG_MSTR_PK));
                serialInfo.setMdlId(rs.getBigDecimal(COL_MDL_ID));
                serialInfo.setOnBatchType(ONBATCH_TYPE.BATCH);

                serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForRcvSerial(serialInfo);

                if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

                    if (serialInfo.getErrMsgId().endsWith("W") || serialInfo.getErrMsgId().endsWith("E")) {

                        errMsgIdList.add(serialInfo.getErrMsgId());
                    }
                }
            }
        }

        if (!isEmpty(errMsgIdList)) {

            return append(errMsgIdList);

        } else {

            return null;
        }
    }

    private boolean receivePutAway(ResultSet rs) throws SQLException {

        String rwsNum = rs.getString(COL_RWS_NUM);
        String rwsLineNum = rs.getString(COL_RWS_DTL_LINE_NUM);

        String serNums = this.serNumMap.get(rwsNum + rwsLineNum);
        List<String> serNumList = split(serNums);

        // Create Parameter
        NLZC206001PMsg pMsg = new NLZC206001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.machMstrUpdFlg, ZYPConstant.FLG_ON_Y);

        NLZC206001_RWSPutAwayListPMsg pMsgDtl = pMsg.RWSPutAwayList.no(0);
        ZYPEZDItemValueSetter.setValue(pMsgDtl.rwsNum, rwsNum);
        ZYPEZDItemValueSetter.setValue(pMsgDtl.rwsDtlLineNum, rwsLineNum);
        ZYPEZDItemValueSetter.setValue(pMsgDtl.invtyStkStsCd, rs.getString(COL_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsgDtl.sceOrdTpCd, rs.getString(COL_SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsgDtl.rwsStkDtTmTs, this.slsDt + ZYPDateUtil.getCurrentSystemTime("HHmmss"));
        ZYPEZDItemValueSetter.setValue(pMsgDtl.rwsStkQty, rs.getBigDecimal(COL_RWS_BAL_QTY));
        ZYPEZDItemValueSetter.setValue(pMsgDtl.whCd, rs.getString(COL_RCV_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsgDtl.mdseCd, rs.getString(COL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsgDtl.rwsRefNum, rs.getString(COL_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pMsgDtl.destInvtyLocCd, rs.getString(COL_RCV_RTL_WH_CD) + rs.getString(COL_RCV_RTL_SWH_CD));
        pMsg.RWSPutAwayList.setValidCount(1);

        // Create Parameter for Serial
        int serIdx = 0;
        if (!isEmpty(serNumList)) {

            for (String serNum : serNumList) {

                NLZC206001_RcvSerNumListPMsg pMsgSer = pMsg.RcvSerNumList.no(serIdx);
                ZYPEZDItemValueSetter.setValue(pMsgSer.rwsDtlLineNum, rwsLineNum);
                ZYPEZDItemValueSetter.setValue(pMsgSer.serNum, serNum);
                ZYPEZDItemValueSetter.setValue(pMsgSer.mdseCd, rs.getString(COL_MDSE_CD));
                serIdx++;
                pMsg.RcvSerNumList.setValidCount(serIdx);
            }
        }

        // Call API
        if (!callNLZC206001(pMsg, rs)) {
            rollback();
            return false;
        }

        return true;

    }

    private List<String> split(String str) {
        List<String> list = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(str)) {
            String[] serNumList = str.split(",");
            list = Arrays.asList(serNumList);
        }
        return list;
    }

    private boolean checkAllCloseRws(ResultSet rs) throws SQLException {

        // Get RWS_DTL
        RWS_DTLTMsg rwsDtl = new RWS_DTLTMsg();
        rwsDtl.setSQLID("001");
        rwsDtl.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        rwsDtl.setConditionValue("rwsNum01", rs.getString(COL_RWS_NUM));
        RWS_DTLTMsgArray rwsDtlList = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(rwsDtl);

        if (rwsDtlList == null || rwsDtlList.getValidCount() == 0) {

            return false;
        }

        boolean allCloseFlg = true;

        for (int i = 0; i < rwsDtlList.getValidCount(); i++) {

            if (!RWS_STS.CANCELED.equals(rwsDtlList.no(i).rwsStsCd.getValue()) && !RWS_STS.RECEIVED.equals(rwsDtlList.no(i).rwsStsCd.getValue())) {

                allCloseFlg = false;
                break;
            }
        }

        return allCloseFlg;
    }

    private boolean closeRws(ResultSet rs) throws SQLException {

        // Create Parameter NLZC2070 : RWS Complete from S21 DC
        NLZC207001PMsg pMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rs.getString(COL_RWS_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, rs.getString(COL_SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.rwsCloDtTmTs, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, rs.getString(COL_SHIP_TO_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rs.getString(COL_RWS_REF_NUM));

        // Call API
        if (!callNLZC207001(pMsg, rs)) {
            rollback();
            return false;
        }

        return true;

    }

    private boolean callNLZC206001(NLZC206001PMsg pMsg, ResultSet rs) throws SQLException {

        // Call API NLZC2060 : Put Away Confirmation from S21 DC API
        NLZC206001 api = new NLZC206001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        List<String> msgIdList = new ArrayList<String>();

        // Check Error in API
        if (!isEmpty(S21ApiUtil.getXxMsgIdList(pMsg))) {

            for (String msgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                if (msgId.endsWith("E")) {
                    msgIdList.add(msgId);
                }
            }
        }

        if (!isEmpty(msgIdList)) {

            setErrorList(append(msgIdList), null, rs.getString(COL_RWS_NUM), rs.getString(COL_RWS_DTL_LINE_NUM));
            return false;

        } else {

            return true;
        }

    }

    private boolean callNLZC207001(NLZC207001PMsg pMsg, ResultSet rs) throws SQLException {

        // Call API NLZC2070
        NLZC207001 api = new NLZC207001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        List<String> msgIdList = new ArrayList<String>();

        // Check Error in API
        if (!isEmpty(S21ApiUtil.getXxMsgIdList(pMsg))) {

            for (String msgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                if (msgId.endsWith("E")) {
                    msgIdList.add(msgId);
                }
            }
        }

        if (!isEmpty(msgIdList)) {

            setErrorList(append(msgIdList), null, rs.getString(COL_RWS_NUM), rs.getString(COL_RWS_DTL_LINE_NUM));
            return false;

        } else {

            return true;
        }
    }

    /**
     * Set Error List.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void setErrorList(String msgId, String[] msgParam, String rwsNum, String rwsLineNum) {

        this.errorList.add("Error MessageID : " + msgId + "(" + " RWS Number = " + rwsNum + ", RWS Line Number = " + rwsLineNum + " )");
        S21InfoLogOutput.println(msgId, msgParam);
    }

    /**
     * <pre>
     * Check empty list.
     * </pre>
     * @param list List<String>
     * @return true/empty. false/not empty.
     */
    private boolean isEmpty(List<String> list) {
        return list == null || list.isEmpty();
    }

    /**
     * <pre>
     * Check empty map.
     * </pre>
     * @param map Map<String, String>
     * @return true/empty. false/not empty.
     */
    private boolean isEmpty(Map<String, String> map) {
        return map == null || map.isEmpty();
    }

    private String append(List<String> list) {

        StringBuilder sb = new StringBuilder();

        for (String str : list) {

            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str);

        }
        return sb.toString();
    }

    private boolean postErrorMail() {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

        NWXC001001MailSubstituteString sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchId");
        sbsStr.setSbstStr(BATCH_ID);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchNm");
        sbsStr.setSbstStr(BATCH_NM);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("ErrorInfo");
        StringBuilder sb = new StringBuilder();
        for (String error : this.errorList) {
            if (sb.length() > 0) {
                sb.append("     [ ").append(error).append(" ] ").append("\r\n");
            } else {
                sb.append("[ ").append(error).append(" ] ").append("\r\n");
            }
        }
        sbsStr.setSbstStr(sb.toString());
        sbsStrList.add(sbsStr);

        // success -> true / error -> false
        return new NWXC001001Mail().postMail(this.glblCmpyCd, MAIL_GROUP_ID, MAIL_TEMPLATE_ID, sbsStrList);
    }

}
