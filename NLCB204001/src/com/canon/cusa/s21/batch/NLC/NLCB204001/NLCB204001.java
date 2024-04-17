package com.canon.cusa.s21.batch.NLC.NLCB204001;

import static com.canon.cusa.s21.batch.NLC.NLCB204001.constant.NLCB204001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.dbcommon.EZDTBLAccessor;


import business.db.ART10FMsg;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.FLD_TRNSF_UPLD_WRKTMsg;
import business.db.INVTYTMsg;
import business.db.MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC060001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NLCB204001
 * Field Transfer Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/03/06   Hitachi         S.Moriai        Create          QC#63475
 * </pre>
 */
public class NLCB204001 extends S21BatchMain{

    /** Termination Code of Execution Process */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** TotalErrCount */
    private int totalErrCount = 0;

    /** Max Process Count */
    private int maxProcCnt = 2000;

    /** SSM-Client */
    private S21SsmBatchClient ssmClient = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessengert */
    private ZYPCSVUploadMessenger messenger = null;

    /** Function List */
    private List<String> funcList = new ArrayList<String>();

    /** From Tech WH / From Tech SWH / To Tech WH / To Tech SWH*/
    private String fromToMatch = "";

    /** Invtr Ord Line Num */
    private String invtyOrdLineNum = "";

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NLCB204001().executeBatch(NLCB204001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NLCM0246E, new String[] {"Global Company Code" });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BIZ_APP_ID);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(NLCM0246E, new String[] {"Sales Date" });
        }

        // Initialize
        termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
        this.batchHelper = new S21RequestBatchHelper();

    }

    @Override
    protected void mainRoutine() {
        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }

        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            doProcess(request);
            commit();
        }
    }

    @Override
     protected void termRoutine() {
        setTermState(this.termCd, this.totalNmlCount, this.totalErrCount);
        
    }

    private void doProcess(ART10FMsg fMsg) {

        int errCount = 0;
        int insertCount = 0;
        // Get Upload ID
        String upldCsvId = getUpldCsvId(fMsg);

        // Get Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        // Processing Count
        int procCnt = getProcCnt(upldCsvRqstPk);
        if (procCnt > this.maxProcCnt) {
            this.totalErrCount++;
            setWaringEndProc(fMsg, NLCM0248E, String.valueOf(this.maxProcCnt));
            return;
        }

        if (procCnt == 0) {
            this.totalErrCount++;
            setWaringEndProc(fMsg, NLCM0249E, null);
            return;
        }

        // Function List For Upload User
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        this.funcList = profile.getAuthorizedFunctionCodeListForUser(upldCSVRqstTMsg.ezInUserID.getValue());
        if (!isUploadAuth()) {
            this.totalErrCount++;
            setWaringEndProc(fMsg, NLCM0250E, null);
            return;
        }

        List<FLD_TRNSF_UPLD_WRKTMsg> fldTrnsfUpldWrkTMsg = getFldTrnsfUpldWrk(upldCsvRqstPk);
        List<FLD_TRNSF_UPLD_WRKTMsg> fldTrnsfUpldWrkTMsgList = new ArrayList<FLD_TRNSF_UPLD_WRKTMsg>();
        List<BigDecimal> upldCsvRqstRowNumList = new ArrayList<BigDecimal>();
        if (fldTrnsfUpldWrkTMsg.isEmpty()) {
            return;
        }

        String tmpFromLocCd = "";
        String tmpToLocCd = "";
        String tmpInvtyOrdNum = "";
        for (int i = 0; i < fldTrnsfUpldWrkTMsg.size(); i++) {
            Boolean insertFlg = false;
            BigDecimal upldCsvRqstRowNum  = fldTrnsfUpldWrkTMsg.get(i).upldCsvRqstRowNum.getValue();

            // MDSE of Check
            if (checkMdse(fldTrnsfUpldWrkTMsg.get(i), upldCsvRqstRowNum)) {
                errCount++;
                continue;
            }

            // FROM_RTL_WH_CD, FROM_RTL_SWH_CD of Exist Check
            tmpFromLocCd = checkEnableLocCd(fldTrnsfUpldWrkTMsg.get(i).fromRtlWhCd.getValue(), fldTrnsfUpldWrkTMsg.get(i).fromRtlSwhCd.getValue(), upldCsvRqstRowNum);
            if (!ZYPCommonFunc.hasValue(tmpFromLocCd)) {
                errCount++;
                continue;
            }

            // TO_RTL_WH_CD,TO_RTL_SWH_CD of Exist Check
            tmpToLocCd = checkEnableLocCd(fldTrnsfUpldWrkTMsg.get(i).toRtlWhCd.getValue(), fldTrnsfUpldWrkTMsg.get(i).toRtlSwhCd.getValue(), upldCsvRqstRowNum);
            if (!ZYPCommonFunc.hasValue(tmpToLocCd)) {
                errCount++;
                continue;
            }

            // FROM_RTL_WH_CD, TO_RTL_WH_CD of Same Check
            // FROM_RTL_SWH_CD, TO_RTL_SWH_CD of different Check
            if (isLocCorrelation(fldTrnsfUpldWrkTMsg.get(i), upldCsvRqstRowNum)){
                errCount++;
                continue;
            }

            // FROM_RTL_WH_CD, FROM_RTL_SWH_CD(TO_RTL_WH_CD, TO_RTL_SWH_CD) of Physical Inventory Check
            if (isTechPiOpenedPhysInvty(fldTrnsfUpldWrkTMsg.get(i), upldCsvRqstRowNum)){
                errCount++;
                continue;
            }

            // Allocation of Check
            if (isSSAllocationDetailCheck(fldTrnsfUpldWrkTMsg.get(i), upldCsvRqstRowNum)) {
                errCount++;
                continue;
            }

            // Inventory Check
            if (isAvailableQtyDetailCheck(fldTrnsfUpldWrkTMsg.get(i), upldCsvRqstRowNum)) {
                errCount++;
                continue;
            }

            String setMatchRtlWh = fldTrnsfUpldWrkTMsg.get(i).fromRtlWhCd.getValue()
                    + fldTrnsfUpldWrkTMsg.get(i).fromRtlSwhCd.getValue()
                    + fldTrnsfUpldWrkTMsg.get(i).toRtlWhCd.getValue()
                    + fldTrnsfUpldWrkTMsg.get(i).toRtlSwhCd.getValue();
            if (!ZYPCommonFunc.hasValue(this.fromToMatch) || !this.fromToMatch.equals(setMatchRtlWh)) {
                fldTrnsfUpldWrkTMsgList = new ArrayList<FLD_TRNSF_UPLD_WRKTMsg>();
                upldCsvRqstRowNumList = new ArrayList<BigDecimal>();
                fldTrnsfUpldWrkTMsgList.add(fldTrnsfUpldWrkTMsg.get(i));
                upldCsvRqstRowNumList.add(upldCsvRqstRowNum);
                this.fromToMatch = setMatchRtlWh;
            } else {
                fldTrnsfUpldWrkTMsgList.add(fldTrnsfUpldWrkTMsg.get(i));
                upldCsvRqstRowNumList.add(upldCsvRqstRowNum);
            }

            if (!ZYPCommonFunc.hasValue(this.fromToMatch) || fldTrnsfUpldWrkTMsg.size() == (i + 1)
                   || !this.fromToMatch.equals(fldTrnsfUpldWrkTMsg.get(i + 1).fromRtlWhCd.getValue()
                                    + fldTrnsfUpldWrkTMsg.get(i + 1).fromRtlSwhCd.getValue()
                                    + fldTrnsfUpldWrkTMsg.get(i).toRtlWhCd.getValue()
                                    + fldTrnsfUpldWrkTMsg.get(i).toRtlSwhCd.getValue())) {
                insertFlg = true;
            }

            if (insertFlg) {
                // Insert
                tmpInvtyOrdNum = successExecNLZC0030(fldTrnsfUpldWrkTMsgList, tmpFromLocCd, tmpToLocCd, upldCsvRqstRowNumList);
                if (!ZYPCommonFunc.hasValue(tmpInvtyOrdNum)) {
                    errCount++;
                    upldCsvRqstRowNumList = new ArrayList<BigDecimal>();
                    fldTrnsfUpldWrkTMsgList.add(fldTrnsfUpldWrkTMsg.get(i));
                    continue;
                }

                // Update
                if (successExecNLZC0020(fldTrnsfUpldWrkTMsgList, tmpFromLocCd, tmpToLocCd, tmpInvtyOrdNum, upldCsvRqstRowNumList)) {
                    errCount++;
                    upldCsvRqstRowNumList = new ArrayList<BigDecimal>();
                    fldTrnsfUpldWrkTMsgList.add(fldTrnsfUpldWrkTMsg.get(i));
                    continue;
                }

                // Update FLD_TRNSF_UPLD_WRKTMsg
                for (FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrk : fldTrnsfUpldWrkTMsgList) {
                    updateFldTrnsfUpldWrk(fldTrnsfUpldWrk, tmpInvtyOrdNum);
                    insertCount++;
                }
                fldTrnsfUpldWrkTMsgList = new ArrayList<FLD_TRNSF_UPLD_WRKTMsg>();
                upldCsvRqstRowNumList = new ArrayList<BigDecimal>();
            }
        }

        if (!fldTrnsfUpldWrkTMsgList.isEmpty()) {
            // Insert
            tmpInvtyOrdNum = successExecNLZC0030(fldTrnsfUpldWrkTMsgList, tmpFromLocCd, tmpToLocCd, upldCsvRqstRowNumList);
            if (!ZYPCommonFunc.hasValue(tmpInvtyOrdNum)) {
                errCount++;
            } else if (successExecNLZC0020(fldTrnsfUpldWrkTMsgList, tmpFromLocCd, tmpToLocCd, tmpInvtyOrdNum, upldCsvRqstRowNumList)) {
                errCount++;
            }

            // Update FLD_TRNSF_UPLD_WRKTMsg
            for (FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrk : fldTrnsfUpldWrkTMsgList) {
                updateFldTrnsfUpldWrk(fldTrnsfUpldWrk, tmpInvtyOrdNum);
                insertCount++;
            }
            fldTrnsfUpldWrkTMsgList = new ArrayList<FLD_TRNSF_UPLD_WRKTMsg>();
            upldCsvRqstRowNumList = new ArrayList<BigDecimal>();
        }

        if (errCount > 0) {
            this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
            this.totalErrCount = this.totalErrCount + errCount;
            if (insertCount > 0) {
                this.totalNmlCount = this.totalNmlCount + insertCount;
                String arg = createResultMessageArg(insertCount, errCount);
                this.messenger.addMessageForFile(NYXM0001I, arg);
                this.messenger.uploadMessage();
            } else {
                String arg = createResultMessageArg(0, errCount);
                this.messenger.addMessageForFile(NYXM0002E, arg);
                this.messenger.uploadMessage();
            }
        } else {
            this.totalNmlCount = this.totalNmlCount + insertCount;
            this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
            String arg = createResultMessageArg(insertCount, 0);
            this.messenger.addMessageForFile(NYXM0001I, arg);
            this.messenger.uploadMessage();
        }
    }

    /**
     * getUpldCsvId
     * @param fMsg
     * @return
     */
    private String getUpldCsvId(ART10FMsg fMsg) {
        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (!hasValue(uploadCsvId)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Upload CSV Request PK" });
        }
        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    /**
     * getUpldCsvReqPk
     * @param fMsg
     * @return
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {
        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (!hasValue(removeDQRequestPK)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Upload CSV Request PK" });
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NLCM0247E, new String[] {"Upload CSV Request PK"});
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * getUpldCSVRqst
     * @param upldCsvRqstPk
     * @return
     */
    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {
        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }
        return outTMsg;
    }

    /**
     * getProcCnt
     * @param upldCsvRqstPk
     * @return
     */
    private int getProcCnt(BigDecimal upldCsvRqstPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCsvRqstPk);
        BigDecimal cnt = (BigDecimal) this.ssmClient.queryObject("getProcCnt", paramMap);
        return cnt.intValue();
    }

    /**
     * setWaringEndProc
     * @param fMsg
     * @param msg
     * @param arg
     */
    private void setWaringEndProc(ART10FMsg fMsg, String msg, String arg) {
        this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
        this.messenger.addMessageForFile(msg, arg);
        this.messenger.uploadMessage();
    }

    /**
     * setFldTrnsfUpldWrkMsg
     * @param upldCsvRqstPk
     * @param msg
     * @param arg
     */
    private void setFldTrnsfUpldWrkMsg(BigDecimal upldCsvRqstPk, String msg, String arg) {
        this.messenger.addMessageForRecord(upldCsvRqstPk, msg, arg);
    }

    /**
     * isUploadAuth
     * @return
     */
    private boolean isUploadAuth() {
        for (String funcId : this.funcList) {
            if (FUNC_ID_UPDATE.equals(funcId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * getFldTrnsfUpldWrk
     * @param upldCsvRqstPk
     * @return
     */
    private List<FLD_TRNSF_UPLD_WRKTMsg> getFldTrnsfUpldWrk(BigDecimal upldCsvRqstPk) {
        List<FLD_TRNSF_UPLD_WRKTMsg> fldTrnsfUpldWrkList = new ArrayList<FLD_TRNSF_UPLD_WRKTMsg>();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("upldCsvRqstPk", upldCsvRqstPk);
            ps = this.ssmLLClient.createPreparedStatement("getFldTrnsfUpldWrk", paramMap);
            rs = ps.executeQuery();

            while (rs.next()) {
                FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTmsg = new FLD_TRNSF_UPLD_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.upldCsvRqstPk, rs.getBigDecimal("UPLD_CSV_RQST_PK"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.upldCsvRqstRowNum, rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.fromRtlWhCd, rs.getString("FROM_RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.fromRtlSwhCd, rs.getString("FROM_RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.toRtlWhCd, rs.getString("TO_RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.toRtlSwhCd, rs.getString("TO_RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.firstInvtyOrdCmntTxt, rs.getString("FIRST_INVTY_ORD_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.scdInvtyOrdCmntTxt, rs.getString("SCD_INVTY_ORD_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.thirdInvtyOrdCmntTxt, rs.getString("THIRD_INVTY_ORD_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.mdseCd, rs.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.stkStsCd, rs.getString("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.ordQty, rs.getBigDecimal("ORD_QTY"));
                ZYPEZDItemValueSetter.setValue(fldTrnsfUpldWrkTmsg.invtyOrdNum, rs.getString("INVTY_ORD_NUM"));

                fldTrnsfUpldWrkList.add(fldTrnsfUpldWrkTmsg);
            }
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return fldTrnsfUpldWrkList;
    }

    /**
     * checkMdse
     * @param fldTrnsfUpldWrkTMsg
     * @param upldCsvRqstRowNum
     * @return
     */
    private boolean checkMdse(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg, BigDecimal upldCsvRqstRowNum) {
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.mdseCd.setValue(fldTrnsfUpldWrkTMsg.mdseCd.getValue());

        MDSETMsg outMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0002E, null);
            return true;
        }
        return false;
      }

    /**
     * checkEnableLocCd
     * @param rtlWhCd
     * @param rtlSwhCd
     * @param upldCsvRqstRowNum
     * @return
     */
    private String checkEnableLocCd(String rtlWhCd, String rtlSwhCd, BigDecimal upldCsvRqstRowNum) {
        String strLocCd = "";
        strLocCd = getInvtyLocCdRtlSwh(this.glblCmpyCd, rtlWhCd, rtlSwhCd);

        NMXC100001EnableWHData dwxc100001EnableWHFrom = NMXC100001EnableWH.checkEnableWH(this.glblCmpyCd, strLocCd, BIZ_APP_ID, null, ZYPConstant.FLG_OFF_N);
        if (dwxc100001EnableWHFrom == null) {
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0004E, null);
            return "";
        } else if (ZYPCommonFunc.hasValue(dwxc100001EnableWHFrom.getXxMsgId())) {
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, dwxc100001EnableWHFrom.getXxMsgId(), null);
            return "";
        } 
        return strLocCd;
    }

    /**
     * getInvtyLocCdRtlSwh
     * @param glblCmpyCd
     * @param rtlWhCd
     * @param rtlSwhCd
     * @return
     */
    private String getInvtyLocCdRtlSwh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {
        RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwh.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlSwhCd, rtlSwhCd);
        rtlSwh = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwh);
        if (rtlSwh == null) {
            return "";
        } else {
            return rtlSwh.invtyLocCd.getValue();
        }
    }

    /**
     * isLocCorrelation
     * @param fldTrnsfUpldWrkTMsg
     * @param upldCsvRqstRowNum
     * @return
     */
    private boolean isLocCorrelation(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg, BigDecimal upldCsvRqstRowNum) {

        // WH Check
        if (fldTrnsfUpldWrkTMsg.fromRtlWhCd.getValue().equals(fldTrnsfUpldWrkTMsg.toRtlWhCd.getValue())) {
            String msg = S21MessageFunc.clspGetMessage(NLCM0035E, new String[] {fldTrnsfUpldWrkTMsg.fromRtlWhCd.getValue(), fldTrnsfUpldWrkTMsg.toRtlWhCd.getValue()});
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0251E, msg);
            return true;
        }
        // SWH Check
        if (!fldTrnsfUpldWrkTMsg.fromRtlSwhCd.getValue().equals(fldTrnsfUpldWrkTMsg.toRtlSwhCd.getValue())) {
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLZM2302E, null);
            return true;
        }

        return false;
    }

    /**
     * isTechPiOpenedPhysInvty
     * @param fldTrnsfUpldWrkTMsg
     * @param upldCsvRqstRowNum
     * @return
     */
    private boolean isTechPiOpenedPhysInvty(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg, BigDecimal upldCsvRqstRowNum) {

        // From Tech WH/SWH Check
        if (checkOpenedPhysInvty(this.glblCmpyCd, fldTrnsfUpldWrkTMsg.fromRtlWhCd.getValue(), fldTrnsfUpldWrkTMsg.fromRtlSwhCd.getValue())) {
            String fromTech = fldTrnsfUpldWrkTMsg.fromRtlWhCd.getValue() + "/" + fldTrnsfUpldWrkTMsg.fromRtlSwhCd.getValue();
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0232E, fromTech);
            return true;
        }

        // To Tech WH/SWH Check
        if (checkOpenedPhysInvty(this.glblCmpyCd, fldTrnsfUpldWrkTMsg.toRtlWhCd.getValue(), fldTrnsfUpldWrkTMsg.toRtlSwhCd.getValue())) {
            String toTech = fldTrnsfUpldWrkTMsg.toRtlWhCd.getValue() + "/" + fldTrnsfUpldWrkTMsg.toRtlSwhCd.getValue();
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0232E, toTech);
            return true;
        }

        return false;
    }

    /**
     * checkOpenedPhysInvty
     * @param glblCmpyCd
     * @param rtlWhCd
     * @param rtlSwhCd
     * @return
     */
    private boolean checkOpenedPhysInvty(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {

        NLZC060001PMsg pMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, rtlWhCd + rtlSwhCd);

        NLZC060001 nlzc060001 = new NLZC060001();
        nlzc060001.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) {
            return true;
        }

        return false;
    }

    /**
     * isSSAllocationDetailCheck
     * @param fldTrnsfUpldWrkTMsg
     * @param upldCsvRqstRowNum
     * @return
     */
    private boolean isSSAllocationDetailCheck(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg, BigDecimal upldCsvRqstRowNum) {

        AVAL_INVTY_APP_IDTMsg tMsg = findAvalInvtyAppIdInfo(fldTrnsfUpldWrkTMsg.stkStsCd.getValue());

        if (tMsg == null) {
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0067E, fldTrnsfUpldWrkTMsg.stkStsCd.getValue());
            return true;
        }
        return false;
    }

    /**
     * findAvalInvtyAppIdInfo
     * @param stkStsCd
     * @return
     */
    private AVAL_INVTY_APP_IDTMsg findAvalInvtyAppIdInfo(String stkStsCd) {

        AVAL_INVTY_APP_IDTMsg inMsg = new AVAL_INVTY_APP_IDTMsg();

        inMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        inMsg.bizAppId.setValue(BIZ_APP_ID);
        inMsg.locStsCd.setValue(LOC_STS_CD);
        inMsg.stkStsCd.setValue(stkStsCd);

        return (AVAL_INVTY_APP_IDTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * isAvailableQtyDetailCheck
     * @param fldTrnsfUpldWrkTMsg
     * @param upldCsvRqstRowNum
     * @return
     */
    private boolean isAvailableQtyDetailCheck(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg, BigDecimal upldCsvRqstRowNum) {

        INVTYTMsg outInvtyMsg = findInvtyInfo(fldTrnsfUpldWrkTMsg);

        BigDecimal invtyAvalQty = BigDecimal.ZERO;

        if (outInvtyMsg == null) {
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0021E, null);
            return true;
        } else {

            invtyAvalQty = outInvtyMsg.invtyAvalQty.getValue();
        }

        if (ZYPCommonFunc.hasValue(invtyAvalQty) && BigDecimal.ZERO.compareTo(invtyAvalQty) > 0) {
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0032E, null);
            return true;
        }

        if (!ZYPCommonFunc.hasValue(invtyAvalQty) || invtyAvalQty.compareTo(fldTrnsfUpldWrkTMsg.ordQty.getValue()) < 0) {
            String msg = S21MessageFunc.clspGetMessage(NLCM0016E, new String[] {fldTrnsfUpldWrkTMsg.ordQty.getValue().toString(), invtyAvalQty.toString()});
            setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0251E, msg);
            return true;
        }
        return false;
    }

    /**
     * findInvtyInfo
     * @param fldTrnsfUpldWrkTMsg
     * @return
     */
    private INVTYTMsg findInvtyInfo(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg) {

        INVTYTMsg inInvtyMsg = new INVTYTMsg();

        inInvtyMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inInvtyMsg.invtyLocCd, getInvtyLocCdFromRtlSwh(this.glblCmpyCd, fldTrnsfUpldWrkTMsg.fromRtlWhCd.getValue(), fldTrnsfUpldWrkTMsg.fromRtlSwhCd.getValue()));
        inInvtyMsg.locStsCd.setValue(LOC_STS_CD);
        inInvtyMsg.mdseCd.setValue(fldTrnsfUpldWrkTMsg.mdseCd.getValue());
        inInvtyMsg.stkStsCd.setValue(fldTrnsfUpldWrkTMsg.stkStsCd.getValue());

        return (INVTYTMsg) EZDTBLAccessor.findByKey(inInvtyMsg);
    }

    /**
     * getInvtyLocCdFromRtlSwh
     * @param glblCmpyCd
     * @param rtlWhCd
     * @param rtlSwhCd
     * @return
     */
    private String getInvtyLocCdFromRtlSwh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {
        RTL_SWHTMsg rtlSwh = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwh.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSwh.rtlSwhCd, rtlSwhCd);
        rtlSwh = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwh);
        if (rtlSwh == null) {
            return "";
        } else {
            return rtlSwh.invtyLocCd.getValue();
        }
    }

    /**
     * successExecNLZC0030
     * @param fldTrnsfUpldWrkTMsgList
     * @param tmpFromLocCd
     * @param tmpToLocCd
     * @param upldCsvRqstRowNumList
     * @return
     */
    private String successExecNLZC0030(List<FLD_TRNSF_UPLD_WRKTMsg> fldTrnsfUpldWrkTMsgList, String tmpFromLocCd, String tmpToLocCd, List<BigDecimal> upldCsvRqstRowNumList) {

        String invtyOrdNum = "";
        List<NLZC003001PMsg> dLZC0030PMsgList = getNLZC0030PMsgList(fldTrnsfUpldWrkTMsgList, tmpFromLocCd, tmpToLocCd);

        new NLZC003001().execute(dLZC0030PMsgList, ONBATCH_TYPE.ONLINE);

        if (hasErrNLZC0030(dLZC0030PMsgList, upldCsvRqstRowNumList)) {
            return invtyOrdNum;
        }

        NLZC003001PMsg dLZC003001PMsg = (NLZC003001PMsg) dLZC0030PMsgList.get(0);
        invtyOrdNum = dLZC003001PMsg.invtyOrdNum.getValue();
        return invtyOrdNum;
    }

    /**
     * hasErrNLZC0030
     * @param dlzc0030List
     * @param upldCsvRqstRowNumList 
     * @return
     */
    private boolean hasErrNLZC0030(List<NLZC003001PMsg> dlzc0030List, List<BigDecimal> upldCsvRqstRowNumList) {

        for (int i = 0; i < dlzc0030List.size(); i++) {
            NLZC003001PMsg dlzc003001PMsg = dlzc0030List.get(i);
            if (!S21ApiUtil.getXxMsgIdList(dlzc003001PMsg).isEmpty()) {

                for (int j = 0; j < dlzc003001PMsg.xxMsgIdList.length(); j++) {
                    String msgId = dlzc003001PMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    if (ZYPCommonFunc.hasValue(msgId)) {

                        if (msgId.endsWith("E")) {
                            for (BigDecimal upldCsvRqstRowNum : upldCsvRqstRowNumList) {
                                String msg = S21MessageFunc.clspGetMessage(msgId);
                                setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0251E, msg);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * getNLZC0030PMsgList
     * @param fldTrnsfUpldWrkTMsgList
     * @param tmpFromLocCd
     * @param tmpToLocCd
     * @return
     */
    private List<NLZC003001PMsg> getNLZC0030PMsgList(List<FLD_TRNSF_UPLD_WRKTMsg> fldTrnsfUpldWrkTMsgList, String tmpFromLocCd, String tmpToLocCd) {

        List<NLZC003001PMsg> dLZC0030List = new ArrayList<NLZC003001PMsg>();
        NLZC003001PMsg pMsg = new NLZC003001PMsg();

        pMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        pMsg.xxDtTpCd.setValue(NLZC003001.DT_TP_HDR);

        pMsg.invtyOrdTpCd.setValue(INVTY_ORD_TP.INTERNAL_DC_TRANSFER);

        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, fldTrnsfUpldWrkTMsgList.get(0).fromRtlWhCd.getValue());

        pMsg.locStsCd.setValue(LOC_STS_CD);
        pMsg.trxCd.setValue(TRX.MOVEMENT);
        pMsg.trxRsnCd.setValue(TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT);

        pMsg.tocCd.clear();
        pMsg.shpgSvcLvlCd.setValue(SHPG_SVC_LVL.GROUND_SERVICE);
        pMsg.dcTrnsfRddDt.clear();
        pMsg.dcTrnsfRsdDt.setValue(this.salesDate);
        pMsg.prodCtrlCd.clear();
        pMsg.dsplTpCd.clear();
        pMsg.insClmNumTxt.clear();
        pMsg.fromInvtyOrdNum.clear();

        pMsg.dmgClmCpltFlg.setValue("N");
        pMsg.invtyOrdStsCd.setValue(INVTY_ORD_STS.CLOSED);
        for (int i = 0; i < fldTrnsfUpldWrkTMsgList.size(); i++) {
            String firstInvtyOrdCmntTxt = fldTrnsfUpldWrkTMsgList.get(i).firstInvtyOrdCmntTxt.getValue();
            String scdInvtyOrdCmntTxt = fldTrnsfUpldWrkTMsgList.get(i).scdInvtyOrdCmntTxt.getValue();
            String thirdInvtyOrdCmntTxt = fldTrnsfUpldWrkTMsgList.get(i).thirdInvtyOrdCmntTxt.getValue();
            if (!ZYPCommonFunc.hasValue(pMsg.firstInvtyOrdCmntTxt) && ZYPCommonFunc.hasValue(firstInvtyOrdCmntTxt)) {
                pMsg.firstInvtyOrdCmntTxt.setValue(fldTrnsfUpldWrkTMsgList.get(i).firstInvtyOrdCmntTxt.getValue());
            }
            if (!ZYPCommonFunc.hasValue(pMsg.scdInvtyOrdCmntTxt) && ZYPCommonFunc.hasValue(scdInvtyOrdCmntTxt)) {
                pMsg.scdInvtyOrdCmntTxt.setValue(fldTrnsfUpldWrkTMsgList.get(i).scdInvtyOrdCmntTxt.getValue());
            }
            if (!ZYPCommonFunc.hasValue(pMsg.thirdInvtyOrdCmntTxt) && ZYPCommonFunc.hasValue(thirdInvtyOrdCmntTxt)) {
                pMsg.thirdInvtyOrdCmntTxt.setValue(fldTrnsfUpldWrkTMsgList.get(i).thirdInvtyOrdCmntTxt.getValue());
            }
        }
        pMsg.frthInvtyOrdCmntTxt.clear();
        pMsg.trxSrcTpCd.setValue(TRX_SRC_TP.WAREHOUSE_TRANSFER);
        pMsg.sysSrcCd.setValue(SYS_SRC.S21_LOGISTICS);
        pMsg.xxProcTpCd.setValue(NLZC003001.PROC_TP_CRAT);

        dLZC0030List.add(pMsg);

        for (int i = 0; i < fldTrnsfUpldWrkTMsgList.size(); i++) {
            pMsg = new NLZC003001PMsg();
            pMsg.glblCmpyCd.setValue(this.glblCmpyCd);
            this.invtyOrdLineNum = getLineNum(String.valueOf(i + 1));
            pMsg.invtyOrdLineNum.setValue(this.invtyOrdLineNum);
            pMsg.mdseCd.setValue(fldTrnsfUpldWrkTMsgList.get(i).mdseCd.getValue());
            pMsg.stkStsCd.setValue(fldTrnsfUpldWrkTMsgList.get(i).stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, tmpFromLocCd);
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd_D1, tmpToLocCd);
            pMsg.locStsCd_D1.setValue(LOC_STS_CD);
            pMsg.toStkStsCd.setValue(fldTrnsfUpldWrkTMsgList.get(i).stkStsCd.getValue());
            pMsg.ordQty.setValue(fldTrnsfUpldWrkTMsgList.get(i).ordQty.getValue());
            pMsg.invtyOrdDtlStsCd.setValue(INVTY_ORD_STS.CLOSED);

            pMsg.xxProcTpCd.setValue(NLZC003001.PROC_TP_CRAT);
            pMsg.xxDtTpCd.setValue(NLZC003001.DT_TP_DTL);
            pMsg.invtyOrdTpCd.setValue(INVTY_ORD_TP.INTERNAL_DC_TRANSFER);

            dLZC0030List.add(pMsg);
        }
        

        return dLZC0030List;
    }

    /**
     * getLineNum
     * @param lineParam
     * @return
     */
    private String getLineNum(String lineParam) {
        String lineNum = ZYPCommonFunc.leftPad(lineParam, MAXLENGTH, PADDING_STR);
        if (ZYPCommonFunc.hasValue(lineNum)) {
            return lineNum;
        } else {
            return "";
        }
    }

    /**
     * successExecNLZC0020
     * @param fldTrnsfUpldWrkTMsgList
     * @param tmpFromLocCd
     * @param tmpToLocCd
     * @param tmpInvtyOrdNum
     * @param upldCsvRqstRowNumList
     * @return
     */
    private boolean successExecNLZC0020(List<FLD_TRNSF_UPLD_WRKTMsg> fldTrnsfUpldWrkTMsgList, String tmpFromLocCd, String tmpToLocCd, String tmpInvtyOrdNum, List<BigDecimal> upldCsvRqstRowNumList) {

        List<NLZC002001PMsg> dLZC0020PMsgListFr = new ArrayList<NLZC002001PMsg>();
        List<NLZC002001PMsg> dLZC0020PMsgListTo = new ArrayList<NLZC002001PMsg>();

        for (int i = 0; i < fldTrnsfUpldWrkTMsgList.size(); i++) {
            dLZC0020PMsgListFr.add(getNLZC0020PMsg(fldTrnsfUpldWrkTMsgList.get(i), TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT, tmpFromLocCd, tmpToLocCd, tmpInvtyOrdNum));
            dLZC0020PMsgListTo.add(getNLZC0020PMsg(fldTrnsfUpldWrkTMsgList.get(i), TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN, tmpFromLocCd, tmpToLocCd, tmpInvtyOrdNum));
        }
        
        if (hasErrNLZC0020(dLZC0020PMsgListFr, upldCsvRqstRowNumList)) {
            return true;
        }
        if (hasErrNLZC0020(dLZC0020PMsgListTo, upldCsvRqstRowNumList)) {
            return true;
        }
        return false;
    }

    /**
     * getNLZC0020PMsg
     * @param fldTrnsfUpldWrkTMsg
     * @param trxRsnCd
     * @param tmpFromLocCd
     * @param tmpToLocCd
     * @param tmpInvtyOrdNum
     * @return
     */
    private NLZC002001PMsg getNLZC0020PMsg(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg, String trxRsnCd, String tmpFromLocCd, String tmpToLocCd, String tmpInvtyOrdNum) {

        NLZC002001PMsg pMsg = new NLZC002001PMsg();

        if (TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT.equals(trxRsnCd)) {
            // TRX_RSN: 44
            // Out bound
            // Stock Out
            pMsg.xxSysTp.setValue(NLZC002001.SYS_TP_OTBD);
            pMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_OUT);
            // From Wh
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, tmpFromLocCd);
            // Ship Conf
            pMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_SHIP_CONF);
            // Ship To
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, tmpToLocCd);
            pMsg.shipToCustNm.setValue("");
        } else {
            // TRX_RSN: 45
            // In bound
            // Stock In
            pMsg.xxSysTp.setValue(NLZC002001.SYS_TP_INBD);
            pMsg.xxRqstTpCd.setValue(NLZC002001.RQST_STOCK_IN);
            // To Wh
            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, tmpToLocCd);
            // Recive Conf
            pMsg.xxTrxDtlCd.setValue(NLZC002001.TRX_DTL_RCV_CONF);
        }

        pMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        pMsg.mdseCd.setValue(fldTrnsfUpldWrkTMsg.mdseCd.getValue());

        pMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        pMsg.stkStsCd.setValue(fldTrnsfUpldWrkTMsg.stkStsCd.getValue());
        pMsg.xxRqstQty.setValue(fldTrnsfUpldWrkTMsg.ordQty.getValue());
        pMsg.trxCd.setValue(TRX.MOVEMENT);
        pMsg.trxRsnCd.setValue(trxRsnCd);
        pMsg.invtyTrxDt.setValue(this.salesDate);
        pMsg.sysSrcCd.setValue(SYS_SRC.S21_LOGISTICS);

        pMsg.invtyOrdNum.setValue(tmpInvtyOrdNum);
        pMsg.invtyOrdLineNum.setValue(this.invtyOrdLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyTrxSlpNum, tmpInvtyOrdNum);

        ZYPEZDItemValueSetter.setValue(pMsg.shipFromLocCustCd, tmpFromLocCd);

        pMsg.uomCd.setValue(PKG_UOM.EACH);

        return pMsg;
    }

    /**
     * hasErrNLZC0020
     * @param dLZC0020PMsgList
     * @param upldCsvRqstRowNumList
     * @return
     */
    private boolean hasErrNLZC0020(List<NLZC002001PMsg> dLZC0020PMsgList, List<BigDecimal> upldCsvRqstRowNumList) {

        new NLZC002001().execute(dLZC0020PMsgList, ONBATCH_TYPE.ONLINE);
        for (int i = 0; i < dLZC0020PMsgList.size(); i++) {
            NLZC002001PMsg pMsg = (NLZC002001PMsg) dLZC0020PMsgList.get(i);
            for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {
                String msgCd = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgCd) && msgCd.endsWith("E")) {
                    // Has error
                    for (BigDecimal upldCsvRqstRowNum : upldCsvRqstRowNumList) {
                        String msg = S21MessageFunc.clspGetMessage(msgCd);
                        setFldTrnsfUpldWrkMsg(upldCsvRqstRowNum, NLCM0251E, msg);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * createResultMessageArg
     * @param insert
     * @param errCount
     * @return
     */
    private String createResultMessageArg(int insert, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (insert > 0) {
            builder.append(String.format(RESULT_MSG_INS, insert));
        }
        if (errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" NYXM0002E");
            }
            builder.append(String.format(RESULT_MSG_ERR, errCount));
        }
        return builder.toString();
    }

    /**
     * updateFldTrnsfUpldWrk
     * @param fldTrnsfUpldWrkTMsg
     * @param tmpInvtyOrdNum
     */
    private void updateFldTrnsfUpldWrk(FLD_TRNSF_UPLD_WRKTMsg fldTrnsfUpldWrkTMsg, String tmpInvtyOrdNum) {
        FLD_TRNSF_UPLD_WRKTMsg upTMsg = new FLD_TRNSF_UPLD_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(upTMsg.upldCsvRqstPk, fldTrnsfUpldWrkTMsg.upldCsvRqstPk);
        ZYPEZDItemValueSetter.setValue(upTMsg.upldCsvRqstRowNum, fldTrnsfUpldWrkTMsg.upldCsvRqstRowNum);
        ZYPEZDItemValueSetter.setValue(upTMsg.glblCmpyCd, glblCmpyCd);
        upTMsg = (FLD_TRNSF_UPLD_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(upTMsg);
        ZYPEZDItemValueSetter.setValue(upTMsg.invtyOrdNum, tmpInvtyOrdNum);
        S21FastTBLAccessor.update(upTMsg);
    }
}
