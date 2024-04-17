/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB004001;

import static com.canon.cusa.s21.batch.NLB.NLBB004001.constant.NLBB004001Constant.*;

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
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SO_SERTMsg;
import business.db.SO_SERTMsgArray;
import business.parts.NLZC060001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NLZC400001PMsg;
import business.parts.NLZC401001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NLZ.NLZC400001.NLZC400001;
import com.canon.cusa.s21.api.NLZ.NLZC401001.NLZC401001;
import com.canon.cusa.s21.api.NLZ.NLZC401001.constant.NLZC401001Constant;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SerialInfo;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SvcMachMstrCheck;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CARR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Auto Delivery Shipping Confirmation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/11/2023   Hitachi         S.Dong          Create          N/A
 *</pre>
 */
public class NLBB004001 extends S21BatchMain {

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

        new NLBB004001().executeBatch(NLBB004001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        profileService = S21UserProfileServiceFactory.getInstance().getService();
        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }
        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date" });
        }

        // Initialization of SSM
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        execParam = new S21SsmExecutionParameter();
        // Set the fetch size.
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

    }

    @Override
    protected void mainRoutine() {

        try {

            // pick
            autoPick();

            this.serNumMap.clear();

            // ship
            autoShip();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {

            if (!isEmpty(this.errorList)) {
                postErrorMail();
                this.termCd = TERM_CD.WARNING_END;
            }
        }
        commit();
    }

    @Override
    protected void termRoutine() {

        // Setting of termination code
        setTermState(termCd, successCount, errorCount, successCount + errorCount);

    }

    private ResultSet getPickList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = ssmLLClient.createPreparedStatement("getPickList", setBindParamPick(), execParam);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        return rs;
    }

    private ResultSet getShipList() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = ssmLLClient.createPreparedStatement("getPickList", setBindParamShip(), execParam);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        return rs;
    }

    private ResultSet getAsnCarrCd(String soNum, String soSlpNum) {
        PreparedStatement stmtForAsnCarrCd = null;
        ResultSet rsForAsnCarrCd = null;
        try {

            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("soNum", soNum);
            paramMap.put("soSlpNum", soSlpNum);
            stmtForAsnCarrCd = ssmLLClient.createPreparedStatement("getAsnCarrCd", paramMap, execParam);
            rsForAsnCarrCd = stmtForAsnCarrCd.executeQuery();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        return rsForAsnCarrCd;
    }

    /**
     * Pick Process
     * @throws SQLException
     */
    private void autoPick() throws SQLException {

        List<Map<String, String>> pickParamList = new ArrayList<Map<String, String>>();

        ResultSet rs = getPickList();

        // Loop by SO Line#
        while (rs.next()) {

            if (SHPG_STS.PICKED.equals(rs.getString(COL_DTL_SHPG_STS_CD))) {
                continue;
            }

            boolean errFlg = false;

            String srcOrdNum = rs.getString(COL_SRC_ORD_NUM);
            String soNum = rs.getString(COL_SO_NUM);
            String soSlpNum = rs.getString(COL_SO_SLP_NUM);

            // Pick Check
            if (!pickCheck(rs, srcOrdNum, soNum, soSlpNum)) {
                errFlg = true;
            }

            if (errFlg) {
                continue;
            }

            String serNums = this.serNumMap.get(soNum + soSlpNum);

            Map<String, String> pickParamMap = new HashMap<String, String>();
            pickParamMap.put(PICK_SO_NUM, soNum);
            pickParamMap.put(PICK_SO_SLP_NUM, soSlpNum);
            pickParamMap.put(PICK_MDSE_CD, rs.getString(COL_MDSE_CD));
            pickParamMap.put(PICK_SHPG_QTY, rs.getString(COL_SHPG_QTY));
            pickParamMap.put(PICK_SER_NUM, serNums);
            pickParamMap.put(PICK_TRX_HDR_NUM, rs.getString(COL_TRX_HDR_NUM));
            pickParamMap.put(PICK_TRX_LINE_NUM, rs.getString(COL_TRX_LINE_NUM));
            pickParamMap.put(PICK_TRX_LINE_SUB_NUM, rs.getString(COL_TRX_LINE_SUB_NUM));
            pickParamMap.put(PICK_TRX_SRC_TP_CD, rs.getString(COL_TRX_SRC_TP_CD));
            pickParamMap.put(PICK_SER_NUM_TAKE_FLG, rs.getString(COL_SER_NUM_TAKE_FLG));
            pickParamMap.put(PICK_SRC_ORD_NUM, srcOrdNum);

            pickParamList.add(pickParamMap);
        }

        // Pick Confirmation
        pickConfirmation(pickParamList);

        return;
    }

    /**
     * Ship Process
     * @throws SQLException
     */
    private void autoShip() throws SQLException {

        List<Map<String, String>> shipParamList = new ArrayList<Map<String, String>>();

        // For PI Check
        ArrayList<String> notPiSoNumList = new ArrayList<String>();
        ArrayList<String> piErrSoNumList = new ArrayList<String>();

        ResultSet rs = getShipList();

        // Loop by SO Line#
        while (rs.next()) {

            boolean errFlg = false;

            String srcOrdNum = rs.getString(COL_SRC_ORD_NUM);
            String soNum = rs.getString(COL_SO_NUM);
            String soSlpNum = rs.getString(COL_SO_SLP_NUM);

            // PI Activity Status Error
            if (piErrSoNumList.contains(soNum)) {

                setErrorList(NLBM1347E, null, srcOrdNum, soNum, soSlpNum);
                errFlg = true;

            } else if (!notPiSoNumList.contains(soNum)) {

                // PI Activity Check
                if (!chkPiActivity(soNum)) {

                    piErrSoNumList.add(soNum);
                    setErrorList(NLBM1347E, null, srcOrdNum, soNum, soSlpNum);
                    errFlg = true;

                } else {
                    notPiSoNumList.add(soNum);
                }
            }

            if (errFlg) {
                continue;
            }

            Map<String, Object> shipRecord = chkShipRecord(rs, srcOrdNum, soNum, soSlpNum);
            if (null == shipRecord) {
                errFlg = true;
            }

            if (errFlg) {
                continue;
            }

            String serNums = this.serNumMap.get(soNum + soSlpNum);
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_DS_COND_CONST_VAL_TXT_01))) {

                Map<String, String> shipParamMap = new HashMap<String, String>();
                shipParamMap.put(KEY_SO_NUM, soNum);
                shipParamMap.put(KEY_SO_SLP_NUM, soSlpNum);
                shipParamMap.put(KEY_SHIP_FROM_LOC_CD, rs.getString(COL_SHIP_FROM_LOC_CD));
                shipParamMap.put(KEY_SCE_ORD_TP_CD, rs.getString(COL_SCE_ORD_TP_CD));
                shipParamMap.put(KEY_MDSE_CD, rs.getString(COL_MDSE_CD));
                shipParamMap.put(KEY_FROM_STK_STS_CD, rs.getString(COL_FROM_STK_STS_CD));
                shipParamMap.put(KEY_SHPG_QTY, rs.getString(COL_SHPG_QTY));
                shipParamMap.put(KEY_ASN_PRO_NUM, (String) shipRecord.get("traNum"));
                shipParamMap.put(KEY_CARR_CD, (String) shipRecord.get("carrCd"));
                shipParamMap.put(KEY_SER_NUM, serNums);
                shipParamMap.put(KEY_SRC_ORD_NUM, srcOrdNum);

                shipParamList.add(shipParamMap);
            }

        }
        // Ship Confirmation
        executeShipConfirm(shipParamList);
    }

    private Map<String, Object> setBindParamPick() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        params.put("soLineOpenFlg", ZYPConstant.FLG_ON_Y);
        params.put("bizAppId", BUSINESS_ID);
        params.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID);
        String avalRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL2020_AUTO_RTL_WH_CD, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalRtlWhCd)) {
            String[] avalRtlWhCdArray = avalRtlWhCd.split(",");
            params.put("avalRtlWhCdArray", avalRtlWhCdArray);
        }
        params.put("soProcStsCdShip", SO_PROC_STS.SHIP);
        params.put("soPrintFlg", ZYPConstant.FLG_ON_Y);
        params.put("shipFlg", ZYPConstant.FLG_OFF_N);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return params;
    }

    private Map<String, Object> setBindParamShip() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        params.put("soLineOpenFlg", ZYPConstant.FLG_ON_Y);
        params.put("bizAppId", BUSINESS_ID);
        params.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID);
        String avalRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL2020_AUTO_RTL_WH_CD, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalRtlWhCd)) {
            String[] avalRtlWhCdArray = avalRtlWhCd.split(",");
            params.put("avalRtlWhCdArray", avalRtlWhCdArray);
        }
        params.put("soProcStsCdShip", SO_PROC_STS.SHIP);
        params.put("soPrintFlg", ZYPConstant.FLG_ON_Y);
        params.put("shipFlg", ZYPConstant.FLG_OFF_N);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        params.put("shpgSts", SHPG_STS.PICKED);

        return params;
    }

    private boolean pickCheck(ResultSet rs, String srcOrdNum, String soNum, String soSlpNum) throws SQLException {

        // Check Pick QTY
        if (!checkPickQty(rs, srcOrdNum, soNum, soSlpNum)) {
            return false;
        }

        // Check Serial
        if (!checkSerial(rs, srcOrdNum, soNum, soSlpNum)) {
            return false;
        }

        return true;
    }

    private boolean checkPickQty(ResultSet rs, String srcOrdNum, String soNum, String soSlpNum) throws SQLException {

        BigDecimal pickQty = rs.getBigDecimal(COL_SHPG_QTY);

        if (ZYPCommonFunc.hasValue(pickQty) && ZYPCommonFunc.hasValue(rs.getBigDecimal(COL_PICK_CONF_QTY))) {
            pickQty = pickQty.subtract(rs.getBigDecimal(COL_PICK_CONF_QTY));
        }

        if (!ZYPCommonFunc.hasValue(pickQty)) {

            setErrorList(NLBM1384E, new String[] {"Pick Quantity" }, srcOrdNum, soNum, soSlpNum);
            return false;

        } else if (pickQty.compareTo(BigDecimal.ZERO) <= 0) {

            setErrorList(NLBM1389E, new String[] {"Pick Quantity", "0" }, srcOrdNum, soNum, soSlpNum);
            return false;

        } else if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_SER_NUM_TAKE_FLG)) && pickQty.compareTo(BigDecimal.ONE) > 0) {

            setErrorList(NLBM1385E, new String[] {"Pick Quantity", "1" }, srcOrdNum, soNum, soSlpNum);
            return false;

        }
        return true;
    }

    private boolean checkSerial(ResultSet rs, String srcOrdNum, String soNum, String soSlpNum) throws SQLException {

        if (!ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_SER_NUM_TAKE_FLG))) {
            return true;
        }
        List<Map<String, String>> serNumList = getSerNumList(rs);

        if (serNumList == null || serNumList.isEmpty()) {

            serNumList = getSerNumListFromRpas(rs);
        }
        if (serNumList == null || serNumList.isEmpty()) {

            setErrorList(NLBM1384E, new String[] {"Pickup Serial Number" }, srcOrdNum, soNum, soSlpNum);
            return false;
        }

        int serNumCnt = 0;
        List<String> checkedSerNumList = new ArrayList<String>();
        this.serNumMap.clear();

        for (Map<String, String> serNum : serNumList) {

            String serNumVal = serNum.get(COL_SER_NUM);

            // Duplicate Check
            if (checkedSerNumList.contains(serNumVal)) {
                setErrorList(NLBM1313E, null, srcOrdNum, soNum, soSlpNum);

                return false;
            } else {
                // Machine Master
                NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
                serialInfo.setGlblCmpyCd(this.glblCmpyCd);
                serialInfo.setDupChkCd(ZYPCodeDataUtil.getVarCharConstValue(SER_DUP_CHK_CD, this.glblCmpyCd));
                serialInfo.setSceOrdTpCd(rs.getString(COL_SCE_ORD_TP_CD));
                serialInfo.setMdseCd(rs.getString(COL_MDSE_CD));
                serialInfo.setSerNum(serNumVal);
                serialInfo.setShipFromLocCd(rs.getString(COL_SHIP_FROM_LOC_CD));
                serialInfo.setTrxHdrNum(rs.getString(COL_TRX_HDR_NUM));
                serialInfo.setSoNum(rs.getString(COL_SO_NUM));
                serialInfo.setOrdSvcConfigMstrPk(rs.getBigDecimal(COL_PICK_SVC_CONFIG_MSTR_PK));
                serialInfo.setMdlId(rs.getBigDecimal(COL_MDL_ID));
                serialInfo.setOnBatchType(ONBATCH_TYPE.BATCH);
                serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForShipSerial(serialInfo);

                serNumCnt++;
                checkedSerNumList.add(serNumVal);
                if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

                    if (serialInfo.getErrMsgId().endsWith("E") || serialInfo.getErrMsgId().endsWith("W")) {

                        setErrorList(serialInfo.getErrMsgId(), null, srcOrdNum, soNum, soSlpNum);
                        return false;
                    }
                } else {
                    if (!isEmpty(checkedSerNumList)) {
                        this.serNumMap.put(soNum + soSlpNum, append(checkedSerNumList));
                    }
                }
            }
        }
        return true;
    }

    private boolean pickConfirmation(List<Map<String, String>> pickParamList) throws SQLException {

        for (Map<String, String> map : pickParamList) {

            // Set param for PKT Line Update API
            NLZC400001PMsg pktLineUpdPMsg = new NLZC400001PMsg();

            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.slsDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(0).soNum, map.get(PICK_SO_NUM));
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(0).soSlpNum, map.get(PICK_SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(0).dsSoLineStsCd, DS_SO_LINE_STS.PICK_CONFIRMED);
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(0).pktStsTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP_TIME)));
            ZYPEZDItemValueSetter.setValue(pktLineUpdPMsg.pktStsUpdateInfo.no(0).pktStsQty, new BigDecimal(map.get(PICK_SHPG_QTY)));
            pktLineUpdPMsg.pktStsUpdateInfo.setValidCount(1);

            // Call PKT Line Update API
            NLZC400001 pktApi = new NLZC400001();
            pktApi.execute(pktLineUpdPMsg, ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(pktLineUpdPMsg)) {

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pktLineUpdPMsg);
                if (!chkPickConfApiResult(msgList, map.get(PICK_SRC_ORD_NUM), map.get(PICK_SO_NUM), map.get(PICK_SO_SLP_NUM))) {
                    rollback();
                    continue;
                }
            }

            // Set param for SO Serial Update API
            NLZC401001PMsg soSerUpMsg = new NLZC401001PMsg();

            ZYPEZDItemValueSetter.setValue(soSerUpMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soSerUpMsg.slsDt, this.slsDt);

            if (ZYPCommonFunc.hasValue(map.get(PICK_SER_NUM))) {

                List<String> serNumList = split(map.get(PICK_SER_NUM));

                int soSerUpCnt = 0;

                for (String serNum : serNumList) {
                    // API Msg Setup
                    if (ZYPConstant.FLG_ON_Y.equals(map.get(PICK_SER_NUM_TAKE_FLG))) {

                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).xxProcTpCd, NLZC401001Constant.MODE_PICKUP_SERIAL);
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).mdseCd, map.get(PICK_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).serNum, serNum);
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soNum, map.get(PICK_SO_NUM));
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).soSlpNum, map.get(PICK_SO_SLP_NUM));
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxHdrNum, map.get(PICK_TRX_HDR_NUM));
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineNum, map.get(PICK_TRX_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxLineSubNum, map.get(PICK_TRX_LINE_SUB_NUM));
                        ZYPEZDItemValueSetter.setValue(soSerUpMsg.serInfo.no(soSerUpCnt).trxSrcTpCd, map.get(PICK_TRX_SRC_TP_CD));
                        soSerUpCnt++;
                    }
                }
                if (soSerUpCnt > 0) {
                    // Call SO Serial Update API
                    NLZC401001 soSerUpApi = new NLZC401001();
                    soSerUpMsg.serInfo.setValidCount(soSerUpCnt);

                    soSerUpApi.execute(soSerUpMsg, ONBATCH_TYPE.BATCH);
                    if (S21ApiUtil.isXxMsgId(soSerUpMsg)) {

                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(soSerUpMsg);
                        if (!chkPickConfApiResult(msgList, map.get(PICK_SRC_ORD_NUM), map.get(PICK_SO_NUM), map.get(PICK_SO_SLP_NUM))) {
                            rollback();
                            continue;
                        }
                    }
                }
            }
            commit();
        }
        return true;
    }

    private boolean chkPickConfApiResult(List<S21ApiMessage> msgList, String srcOrdNum, String soNum, String soSlpNum) {

        if (msgList.size() > 0) {
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(msgId));

                if (ZYPCommonFunc.hasValue(msgId)) {

                    setErrorList(msgId, null, srcOrdNum, soNum, soSlpNum);

                    if (msgId.endsWith("E") || msgId.endsWith("W")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean chkPiActivity(String soNum) {

        NLZC060001PMsg piActivityStsApiPMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.soNum, soNum);

        NLZC060001 piActivityStsApi = new NLZC060001();
        piActivityStsApi.execute(piActivityStsApiPMsg, ONBATCH_TYPE.BATCH);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(piActivityStsApiPMsg.xxRsltStsCd.getValue())) {

            return false;
        }

        return true;
    }

    private Map<String, Object> chkShipRecord(ResultSet rs, String srcOrdNum, String soNum, String soSlpNum) throws SQLException {

        Map<String, Object> checkedParam = new HashMap<String, Object>();

        // Not Open Status
        if (!ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_SO_LINE_OPEN_FLG)) || ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_SHIP_FLG))) {

            setErrorList(NLZM2283E, null, srcOrdNum, soNum, soSlpNum);
            return null;
        }

        String configId = rs.getString(COL_PICK_SVC_CONFIG_MSTR_PK);
        String trxHdrNum = rs.getString(COL_TRX_HDR_NUM);
        String trxLineNum = rs.getString(COL_TRX_LINE_NUM);
        String setMdseCd = rs.getString(COL_SET_MDSE_CD);

        boolean hasErr = false;

        // Ship Quantity Check
        if (!isShipQtyChk(rs, srcOrdNum, soNum, soSlpNum)) {
            hasErr = true;
        }

        if (hasErr) {
            return null;
        }

        // Carrier Check
        ResultSet rsForAsnCarrCd = getAsnCarrCd(soNum, soSlpNum);
        String carrCd = null;

        while (rsForAsnCarrCd.next()) {

            carrCd = getCarrCd(rs, rsForAsnCarrCd);
            if (carrCd == null) {
                setErrorList(NLBM1387E, new String[] {"Carrier" }, srcOrdNum, soNum, soSlpNum);
                hasErr = true;
            }
        }

        if (hasErr) {
            return null;
        }

        // Tracking Number Check
        String traNum = getTraNum(rs, rsForAsnCarrCd, carrCd);

        // Serial Check
        if (!isSerialChk(rs, srcOrdNum, soNum, soSlpNum)) {

            hasErr = true;
        }

        if (hasErr) {

            return null;
        }

        // Check Configuration Id
        if (!checkConfigId(rs, soNum, configId, trxHdrNum, trxLineNum, setMdseCd)) {

            setErrorList(NLBM1382E, new String[] {"pickSvcConfigMstrPk" }, srcOrdNum, soNum, soSlpNum);
            return null;
        }

        // Check Set Component
        if (!checkSetComponent(rs, soNum, configId, trxHdrNum, trxLineNum, setMdseCd)) {

            setErrorList(NLBM1320E, null, srcOrdNum, soNum, soSlpNum);
            return null;
        }

        // Check Carrier Request
        if (!checkCarrRqst(rs)) {

            setErrorList(NLBM1383E, null, srcOrdNum, soNum, soSlpNum);
            return null;
        }

        checkedParam.put("carrCd", carrCd);
        checkedParam.put("traNum", traNum);

        return checkedParam;
    }

    private boolean isShipQtyChk(ResultSet rs, String srcOrdNum, String soNum, String soSlpNum) throws SQLException {
        BigDecimal shipQty = BigDecimal.ZERO;
        BigDecimal uomEachQty = rs.getBigDecimal("UOM_EACH_QTY");

        shipQty = rs.getBigDecimal(COL_SHPG_QTY);
        if (!ZYPCommonFunc.hasValue(shipQty)) {

            setErrorList(ZZM9000E, new String[] {"Ship Quantity" }, srcOrdNum, soNum, soSlpNum);
            return false;

        } else if (BigDecimal.ZERO.compareTo(shipQty) >= 0) {

            setErrorList(NLBM1389E, new String[] {"Ship Quantity", "0" }, srcOrdNum, soNum, soSlpNum);
            return false;

        } else if (ZYPCommonFunc.hasValue(uomEachQty) && !BigDecimal.ZERO.equals(shipQty.remainder(uomEachQty))) {

            setErrorList(NLBM1386E, null, srcOrdNum, soNum, soSlpNum);
            return false;

        }
        return true;
    }

    private List<String> getCarrCdFromXref(ResultSet rs, ResultSet rsForAsnCarrCd) throws SQLException {
        if (!ZYPCommonFunc.hasValue(rsForAsnCarrCd.getString(COL_ASN_CARR_CD)) || !ZYPCommonFunc.hasValue(rsForAsnCarrCd.getString(COL_VND_CD))) {
            return null;
        }
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("trdPtnrCarrCd", rsForAsnCarrCd.getString(COL_ASN_CARR_CD));
        paramMap.put("vndCd", rsForAsnCarrCd.getString(COL_VND_CD));
        paramMap.put("cusaPatVndCd", "0000900335");
        paramMap.put("otherCarrCd", "*");
        paramMap.put("carrTpF", CARR_TP.FEDEX);
        paramMap.put("carrTpU", CARR_TP.UPS);
        paramMap.put("shpgSvcLvlCd", rs.getString("SHPG_SVC_LVL_CD"));

        List<String> carrCdFromXrefList = (List<String>) ssmBatchClient.queryObjectList("getCarrCdFromXref", paramMap);

        return carrCdFromXrefList;
    }

    private String getCarrCdFromOtbdCarr(ResultSet rs, ResultSet rsForAsnCarrCd) throws SQLException {
        if (!ZYPCommonFunc.hasValue(rsForAsnCarrCd.getString(COL_ASN_CARR_CD))) {
            return null;
        }
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("trdPtnrCarrCd", rsForAsnCarrCd.getString(COL_ASN_CARR_CD));

        List<String> carrCdFromOtbdCarr = (List<String>) ssmBatchClient.queryObjectList("getCarrCdFromOtbdCarr", paramMap);

        if (carrCdFromOtbdCarr != null && !carrCdFromOtbdCarr.isEmpty()) {
            return carrCdFromOtbdCarr.get(0);
        } else {
            return null;
        }
    }

    private String getCarrCdFromPoDtl(ResultSet rs, ResultSet rsForAsnCarrCd) throws SQLException {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("poOrdNum", rsForAsnCarrCd.getString(COL_PO_ORD_NUM));
        paramMap.put("poOrdDtlLineNum", rsForAsnCarrCd.getString(COL_PO_ORD_DTL_LINE_NUM));

        List<String> carrCdFromPoDtl = (List<String>) ssmBatchClient.queryObjectList("getCarrCdFromPoDtl", paramMap);

        if (carrCdFromPoDtl != null && !carrCdFromPoDtl.isEmpty()) {
            return carrCdFromPoDtl.get(0);
        } else {
            return null;
        }
    }

    private String getCarrCd(ResultSet rs, ResultSet rsForAsnCarrCd) throws SQLException {
        String carrCd = null;

        List<String> carrCdFromXrefList = getCarrCdFromXref(rs, rsForAsnCarrCd);

        if (carrCdFromXrefList != null && carrCdFromXrefList.size() >= 1) {
            for (String retCarrCd : carrCdFromXrefList) {
                if (ZYPCommonFunc.hasValue(retCarrCd) && !"*".equals(retCarrCd)) {
                    carrCd = retCarrCd;
                    break;
                } else {
                    carrCd = retCarrCd;
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(carrCd)) {
            carrCd = getCarrCdFromOtbdCarr(rs, rsForAsnCarrCd);
        }

        if (ZYPCommonFunc.hasValue(carrCd) && "*".equals(carrCd)) {
            carrCd = "";
        }

        if (!ZYPCommonFunc.hasValue(carrCd)) {
            carrCd = getCarrCdFromPoDtl(rs, rsForAsnCarrCd);
        }

        if (!ZYPCommonFunc.hasValue(carrCd)) {

            carrCd = null;

        }
        return carrCd;

    }

    private String getTraNum(ResultSet rs, ResultSet rsForAsnCarrCd, String carrCd) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String traNum = null;
        try {
            if (carrCd != null) {

                S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

                HashMap<String, Object> paramMap = new HashMap<String, Object>();

                paramMap.put("soNum", rs.getString(COL_SO_NUM));
                paramMap.put("soSlpNum", rs.getString(COL_SO_SLP_NUM));
                paramMap.put("carrCd", carrCd);

                execParam = new S21SsmExecutionParameter();

                execParam.setFetchSize(FETCH_SIZE);
                execParam.setMaxRows(0);

                preparedStatement = ssmLlcClient.createPreparedStatement("getTraNum", paramMap, execParam);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    if (ZYPCommonFunc.hasValue(resultSet.getString(COL_ASN_PRO_NUM))) {
                        traNum = resultSet.getString(COL_ASN_PRO_NUM);
                    } else if (ZYPCommonFunc.hasValue(rs.getString(COL_TRX_HDR_NUM))) {
                        traNum = rs.getString(COL_TRX_HDR_NUM);
                    } else {
                        traNum = rs.getString(COL_SO_NUM);
                    }
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
        return traNum;
    }

    private boolean isSerialChk(ResultSet rs, String srcOrdNum, String soNum, String soSlpNum) throws SQLException {

        if (!ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_SER_NUM_TAKE_FLG))) {
            return true;
        }

        // Get SO_SER
        SO_SERTMsg soSer = new SO_SERTMsg();
        soSer.setSQLID("001");
        soSer.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        soSer.setConditionValue("soNum01", rs.getString(COL_SO_NUM));
        soSer.setConditionValue("trxHdrNum01", rs.getString(COL_TRX_HDR_NUM));
        soSer.setConditionValue("trxLineNum01", rs.getString(COL_TRX_LINE_NUM));
        soSer.setConditionValue("trxLineSubNum01", rs.getString(COL_TRX_LINE_SUB_NUM));
        SO_SERTMsgArray soSerList = (SO_SERTMsgArray) EZDTBLAccessor.findByCondition(soSer);

        if (soSerList == null || soSerList.getValidCount() == 0) {

            setErrorList(NLBM1384E, new String[] {"Serial Number" }, srcOrdNum, soNum, soSlpNum);
            return false;
        }

        int serNumCnt = 0;
        List<String> checkedSerNumList = new ArrayList<String>();
        this.serNumMap.clear();

        for (int i = 0; i < soSerList.getValidCount(); i++) {

            String serNum = soSerList.no(i).serNum.getValue();

            // Duplicate Check
            if (checkedSerNumList.contains(serNum)) {
                setErrorList(NLBM1313E, null, srcOrdNum, soNum, soSlpNum);

                return false;
            } else {
                // Machine Master
                NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
                serialInfo.setGlblCmpyCd(this.glblCmpyCd);
                serialInfo.setDupChkCd(ZYPCodeDataUtil.getVarCharConstValue(SER_DUP_CHK_CD, this.glblCmpyCd));
                serialInfo.setSceOrdTpCd(rs.getString(COL_SCE_ORD_TP_CD));
                serialInfo.setMdseCd(rs.getString(COL_MDSE_CD));
                serialInfo.setSerNum(serNum);
                serialInfo.setShipFromLocCd(rs.getString(COL_SHIP_FROM_LOC_CD));
                serialInfo.setTrxHdrNum(rs.getString(COL_TRX_HDR_NUM));
                serialInfo.setSoNum(rs.getString(COL_SO_NUM));
                serialInfo.setOrdSvcConfigMstrPk(rs.getBigDecimal(COL_PICK_SVC_CONFIG_MSTR_PK));
                serialInfo.setMdlId(rs.getBigDecimal(COL_MDL_ID));
                serialInfo.setRcvRtlWhCd("");
                serialInfo.setRwsNum("");
                serialInfo.setOnBatchType(ONBATCH_TYPE.BATCH);
                serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForShipSerial(serialInfo);

                serNumCnt++;
                checkedSerNumList.add(serNum);

                if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

                    if (serialInfo.getErrMsgId().endsWith("E") || serialInfo.getErrMsgId().endsWith("W")) {

                        setErrorList(serialInfo.getErrMsgId(), null, srcOrdNum, soNum, soSlpNum);
                        return false;

                    }
                } else {
                    if (!isEmpty(checkedSerNumList)) {
                        this.serNumMap.put(soNum + soSlpNum, append(checkedSerNumList));
                    }
                }
            }
        }
        if (serNumCnt != rs.getInt(COL_SHPG_QTY)) {
            setErrorList(NLBM1355E, null, srcOrdNum, soNum, soSlpNum);

            return false;
        }
        return true;
    }

    private boolean checkConfigId(ResultSet rs, String soNum, String configId, String trxHdrNum, String trxLineNum, String setMdseCd) throws SQLException {
        if (ZYPCommonFunc.hasValue(configId)) {

            int configIdCnt = getConfigId(rs, soNum, configId, trxHdrNum, trxLineNum, setMdseCd);

            // Count SHPG_ORD_DTL
            SHPG_ORD_DTLTMsg dsShpgOrdDtl = new SHPG_ORD_DTLTMsg();
            dsShpgOrdDtl.setSQLID("502");
            dsShpgOrdDtl.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            dsShpgOrdDtl.setConditionValue("soNum01", soNum);
            dsShpgOrdDtl.setConditionValue("pickSvcConfigMstrPk01", configId);
            int count = EZDTBLAccessor.count(dsShpgOrdDtl);

            // If not display data having same Configuration Id
            if (configIdCnt != count) {

                return false;
            }
        }

        return true;
    }

    private boolean checkSetComponent(ResultSet rs, String soNum, String configId, String trxHdrNum, String trxLineNum, String setMdseCd) throws SQLException {
        if (ZYPCommonFunc.hasValue(setMdseCd)) {
            int kitSetCnt = getSetCnt(rs, soNum, configId, trxHdrNum, trxLineNum, setMdseCd);

            int count = countSetItem(trxHdrNum, trxLineNum, setMdseCd);

            if (count != kitSetCnt) {

                return false;
            }
        }
        return true;
    }

    private int countSetItem(String trxHdrNum, String trxLineNum, String setMdseCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("setMdseCd", setMdseCd);

        Integer result = (Integer) ssmBatchClient.queryObject("countSetItem", ssmParam, execParam);

        if (result != null) {

            return result;

        } else {

            return -1;
        }
    }

    private boolean checkCarrRqst(ResultSet rs) throws SQLException {
        String manSendRqstFlg = rs.getString(COL_MAN_SEND_RQST_FLG);
        String sendRqstFlg = rs.getString(COL_SEND_RQST_FLG);

        if (ZYPConstant.FLG_ON_Y.equals(manSendRqstFlg) && ZYPConstant.FLG_OFF_N.equals(sendRqstFlg)) {

            return false;
        }

        return true;
    }

    private NLZC210001PMsg setShipParam(Map<String, String> inMap) {

        NLZC210001PMsg pMsg = new NLZC210001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, inMap.get(KEY_SHIP_FROM_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.soNum, inMap.get(KEY_SO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, inMap.get(KEY_SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP_TIME)));
        ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, inMap.get(KEY_SO_SLP_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, inMap.get(KEY_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, inMap.get(KEY_FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.shipQty, new BigDecimal(inMap.get(KEY_SHPG_QTY)));
        ZYPEZDItemValueSetter.setValue(pMsg.bolNum, inMap.get(KEY_ASN_PRO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, inMap.get(KEY_CARR_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.proNum, inMap.get(KEY_ASN_PRO_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, "");
        ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, BigDecimal.ZERO);

        return pMsg;
    }

    private ArrayList<NLZC210002PMsg> setShipSerialParam(Map<String, String> inMap, List<String> serNumList) {

        ArrayList<NLZC210002PMsg> pMsgArray = new ArrayList<NLZC210002PMsg>();

        for (String serNum : serNumList) {
            NLZC210002PMsg pMsg = new NLZC210002PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.whCd, inMap.get(KEY_SHIP_FROM_LOC_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.soNum, inMap.get(KEY_SO_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, inMap.get(KEY_SCE_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP_TIME)));
            ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.SHIP);
            ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, inMap.get(KEY_SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, inMap.get(KEY_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.serNum, serNum);
            ZYPEZDItemValueSetter.setValue(pMsg.serTakeDtTmTs, pMsg.shipDtTmTs.getValue());
            pMsgArray.add(pMsg);
        }

        return pMsgArray;
    }

    private boolean chkShipConfApiResult(ArrayList<NLZC210001PMsg> shipConfApiPMsgList, ArrayList<NLZC210002PMsg> shipConfSerApiPMsgList, String srcOrdNum, String soNum) {

        if (shipConfApiPMsgList != null && !shipConfApiPMsgList.isEmpty()) {

            for (NLZC210001PMsg shipConfApiPMsg : shipConfApiPMsgList) {

                if (!chkApiResult(S21ApiUtil.getXxMsgIdList(shipConfApiPMsg), srcOrdNum, soNum)) {
                    return false;
                }
            }
        }

        if (shipConfSerApiPMsgList != null && !shipConfSerApiPMsgList.isEmpty()) {

            for (NLZC210002PMsg shipConfSerApiPMsg : shipConfSerApiPMsgList) {

                if (!chkApiResult(S21ApiUtil.getXxMsgIdList(shipConfSerApiPMsg), srcOrdNum, soNum)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean chkApiResult(List<String> apiMsgList, String srcOrdNum, String soNum) {

        if (!apiMsgList.isEmpty()) {

            for (String msgId : apiMsgList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    setErrorList(msgId, null, srcOrdNum, soNum, null);

                    if (msgId.endsWith("E") || msgId.endsWith("W")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean executeShipConfirm(List<Map<String, String>> shipParamList) {

        ArrayList<String> soNumList = new ArrayList<String>();

        for (Map<String, String> map : shipParamList) {

            if (!soNumList.isEmpty() && soNumList.contains(map.get(KEY_SO_NUM))) {
                continue;
            }

            soNumList.add(map.get(KEY_SO_NUM));

            // Get Shipping Order Information
            Map<String, Object> result = getSoSum(map);

            if (result.isEmpty()) {
                setErrorList(NLBM1388E, new String[] {"Shipping Order Data" }, map.get(KEY_SRC_ORD_NUM), map.get(KEY_SO_NUM), map.get(KEY_SO_SLP_NUM));
                errorCount++;
                continue;
            }

            ArrayList<NLZC210001PMsg> soConfPList = new ArrayList<NLZC210001PMsg>();
            ArrayList<NLZC210002PMsg> soApiSerPList = new ArrayList<NLZC210002PMsg>();

            // set param in same SO
            for (Map<String, String> inMap : shipParamList) {

                if (map.get(KEY_SO_NUM).equals(inMap.get(KEY_SO_NUM))) {

                    // so serial param set
                    if (ZYPCommonFunc.hasValue(inMap.get(KEY_SER_NUM))) {

                        List<String> serNumList = split(inMap.get(KEY_SER_NUM));
                        soApiSerPList.addAll(setShipSerialParam(inMap, serNumList));
                    }

                    // so conf param set
                    soConfPList.add(setShipParam(inMap));
                }
            }

            // Call API
            NLZC210001 soConfApi = new NLZC210001();
            soConfApi.execute(soConfPList, soApiSerPList, ONBATCH_TYPE.BATCH);

            if (!chkShipConfApiResult(soConfPList, soApiSerPList, map.get(KEY_SRC_ORD_NUM), map.get(KEY_SO_NUM))) {
                rollback();
                errorCount++;
                continue;
            }
            successCount++;
            commit();
        }
        return true;
    }

    private ArrayList<String> setSerNumList(String soNum, List<Map<String, String>> shipParamList) {

        ArrayList<String> serNumList = new ArrayList<String>();

        for (Map<String, String> map : shipParamList) {

            if (map.get(KEY_SO_NUM).equals(soNum) && ZYPCommonFunc.hasValue(map.get(KEY_SER_NUM))) {

                List<String> serNums = split(map.get(KEY_SER_NUM));
                for (String serNum : serNums) {
                    serNumList.add(serNum);
                }
            }
        }
        return serNumList;
    }

    private Map<String, Object> getSoSum(Map<String, String> map) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("soNum", map.get(KEY_SO_NUM));
        ssmParam.put("soSlpNum", map.get(KEY_SO_SLP_NUM));

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSoSum", ssmParam);

    }

    private List<Map<String, String>> getSerNumList(ResultSet rs) throws SQLException {

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put("soNum", rs.getString(COL_SO_NUM));
        bindParam.put("soSlpNum", rs.getString(COL_SO_SLP_NUM));

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getSerNumList", bindParam, execParam);
    }

    private List<Map<String, String>> getSerNumListFromRpas(ResultSet rs) throws SQLException {

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put("glblCmpyCd", this.glblCmpyCd);
        bindParam.put("mdseCd", rs.getString(COL_MDSE_CD));
        bindParam.put("soNum", rs.getString(COL_SO_NUM));
        bindParam.put("soSlipNum", rs.getString(COL_SO_SLP_NUM));

        return (List<Map<String, String>>) ssmBatchClient.queryObjectList("getSerNumListFromRpas", bindParam, execParam);
    }

    private int getConfigId(ResultSet rs, String soNum, String configId, String trxHdrNum, String trxLineNum, String setMdseCd) throws SQLException {
        int cnt = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        String avalRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL2020_AUTO_RTL_WH_CD, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalRtlWhCd)) {
            String[] avalRtlWhCdArray = avalRtlWhCd.split(",");
            params.put("avalRtlWhCdArray", avalRtlWhCdArray);
        }
        params.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        params.put("soLineOpenFlg", ZYPConstant.FLG_ON_Y);
        params.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID);
        params.put("bizAppId", BUSINESS_ID);
        params.put("soProcStsCdShip", SO_PROC_STS.SHIP);
        params.put("soNum", soNum);
        params.put("pickSvcConfigMstrPk", configId);

        preparedStatement = ssmLLClient.createPreparedStatement("getConfigIdAndSetCnt", params, execParam);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            cnt = resultSet.getInt("CNT");
        }
        return cnt;
    }

    private int getSetCnt(ResultSet rs, String soNum, String configId, String trxHdrNum, String trxLineNum, String setMdseCd) throws SQLException {
        int cnt = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", this.glblCmpyCd);
        String avalRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(NLBL2020_AUTO_RTL_WH_CD, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalRtlWhCd)) {
            String[] avalRtlWhCdArray = avalRtlWhCd.split(",");
            params.put("avalRtlWhCdArray", avalRtlWhCdArray);
        }
        params.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        params.put("soLineOpenFlg", ZYPConstant.FLG_ON_Y);
        params.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID);
        params.put("bizAppId", BUSINESS_ID);
        params.put("soProcStsCdShip", SO_PROC_STS.SHIP);
        params.put("trxHdrNum", trxHdrNum);
        params.put("trxLineNum", trxLineNum);
        params.put("setMdseCd", setMdseCd);

        preparedStatement = ssmLLClient.createPreparedStatement("getConfigIdAndSetCnt", params, execParam);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            cnt = resultSet.getInt("CNT");
        }
        return cnt;
    }

    /**
     * Set Error List.
     * @param msgId MessageId
     * @param msgParam MessageParameters
     */
    private void setErrorList(String msgId, String[] msgParam, String srcOrdNum, String soNum, String soSlpNum) {

        if (soSlpNum == null) {
            this.errorList.add("  Error MessageID : " + msgId + "(" + " SRC Ord Number = " + srcOrdNum + ", SO Number = " + soNum + " )");
        } else {
            this.errorList.add("  Error MessageID : " + msgId + "(" + " SRC Ord Number = " + srcOrdNum + ", SO Number = " + soNum + ", SO Slp Number = " + soSlpNum + " )");
        }
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

    private List<String> split(String str) {
        List<String> list = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(str)) {
            String[] serNumList = str.split(",");
            list = Arrays.asList(serNumList);
        }
        return list;
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
