/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB062001;

import static com.canon.cusa.s21.batch.NSA.NSAB062001.constant.NSAB062001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDTItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.MACH_STS_VLD_MAPTMsg;
import business.db.MACH_STS_VLD_MAPTMsgArray;
import business.db.MDSETMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MACH_MSTR_DEF_MAPTMsg;
import business.db.SVC_MACH_MSTR_STSTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;
import business.db.SVC_MACH_TRX_TPTMsg;
import business.db.SVC_MACH_UPLD_WRKTMsg;
import business.db.SVC_MACH_USG_STSTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.TECH_MSTRTMsg;
import business.db.UPLD_CSV_RQSTTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Machine Master Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/21   Hitachi         T.Tomita        Create          QC#6999
 * 2016/07/04   Hitachi         T.Tomita        Update          QC#11109
 * 2016/10/14   Hitachi         T.Tomita        Update          QC#14734
 * 2016/11/29   Hitachi         Y.Takeno        Update          QC#16217
 * 2017/01/25   Hitachi         K.Kitachi       Update          QC#17198
 * 2017/01/30   Hitachi         K.Ochiai        Update          QC#17296
 * 2017/02/14   Hitachi         K.Kitachi       Update          QC#17309
 * 2017/02/15   Hitachi         N.Arai          Update          QC#17301
 * 2017/07/06   Hitachi         K.Kojima        Update          QC#19654
 * 2017/07/07   Hitachi         K.Kojima        Update          QC#19660
 * 2018/01/18   Hitachi         M.Kidokoro      Update          QC#21962
 * 2018/01/22   Hitachi         M.Kidokoro      Update          QC#21975
 * 2018/04/03   Hitachi         K.Kitachi       Update          QC#17300
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#19932
 * 2018/05/29   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/06/14   Hitachi         T.Tomita        Update          QC#23428
 * 2018/06/25   CITS            M.Naito         Update          QC#20733
 * 2018/07/17   Hitachi         A.Kohinata      Update          QC#22583
 * 2018/08/22   CITS            M.Naito         Update          QC#20733
 * 2018/09/26   CITS            M.Naito         Update          QC#28493
 * 2019/08/07   Hitachi         A.Kohinata      Update          QC#52198
 * 2019/10/16   Hitachi         A.Kohinata      Update          QC#53823
 * 2021/11/15   CITS            R.Jabal         Update          QC#59104
 * 2023/07/31   Hitachi         S.Moriaia       Update          QC#61632
 * 2023/09/22   Hitachi         T.Kuroda        Update          QC#61265
 * 2023/10/03   Hitachi         S.Naya          Update          QC#54186
 * 2023/10/06   Hitachi         Y.Nagasawa      Update          QC#60082
 * 2023/12/19   Hitachi         T.Fukuta        Update          CSA-QC#61841
 * </pre>
 */

public class NSAB062001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** System Timestamp */
    private String sysTs = null;

    /** Max Processing Count */
    private int maxProcCnt = 0;

    /** SsmLLClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** BatchHelper */
    private S21RequestBatchHelper batchHelper = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    // del start 2018/07/17 QC#22583
    ///** Normal Count */
    //private int normalCount = 0;
    // del end 2018/07/17 QC#22583

    /** ErrCount */
    private int errCount = 0;

    /** Mode List for Create, Update */
    private List<String> modeListForCreateUpdate;

    /** Mode List for Terminate */
    private List<String> modeListForTerminate;

    /** Mode List */
    private List<String> modeList;

    /** Function List */
    private List<String> funcList = new ArrayList<String>();

    // START 2017/02/15 N.Arai [QC#17301, MOD]
    /** TotalErrCount */
    private int totalErrCount = 0;

    /** hashMap */
    private Map<String, Object>  sellToCustMap = new HashMap<String, Object>();

    /** hashMap */
    private Map<String, Object>  billToCustMap = new HashMap<String, Object>();

    /** hashMap */
    private Map<String, Object>  shipToCustMap = new HashMap<String, Object>();

    /** hashMap */
    private Map<String, String>  billToCustCdMap = new HashMap<String, String>();

    /** hashMap */
    private Map<String, String>  shipToCustCdMap = new HashMap<String, String>();

    /** hashMap */
    private Map<String, Object>  billEligibleMap = new HashMap<String, Object>();

    /** hashMap */
    private Map<String, Object>  shipEligibleMap = new HashMap<String, Object>();

    /** hashMap */
    private Map<String, Object>  ownrBillToMap = new HashMap<String, Object>();
    // END 2017/02/15 N.Arai [QC#17301, MOD]

    // START 2017/07/07 K.Kojima [QC#19660,ADD]
    /** hashMap */
    private Map<String, Object>  svcByLineBizTpCdMap = new HashMap<String, Object>();
    // END 2017/07/07 K.Kojima [QC#19660,ADD]

    // START 2023/10/03 S.Naya [QC#54186,ADD]
    /** hashMap */
    private Map<String, Object>  svcPrvdPtyCdMap = new HashMap<String, Object>();

    /** hashMap */
    private Map<String, Object>  svcPrvdPtyDescTxtMap = new HashMap<String, Object>();

    /** hashMap */
    private Map<String, String>  convertSvcPrvdPtyCdMap = new HashMap<String, String>();

    /** hashMap */
    private Map<String, String>  convertSvcPrvdPtyDescMap = new HashMap<String, String>();
    // END   2023/10/03 S.Naya [QC#54186,ADD]

    // add start 2018/07/17 QC#22583
    /** Insert Count */
    private int insertCount = 0;

    /** Update Count */
    private int updateCount = 0;

    /** Total Normal Count */
    private int totalNormalCount = 0;
    // add end 2018/07/17 QC#22583

    // add start 2019/08/07 QC#52198
    /** VARCHAR CONST: OWNR_RELN_CHECK_EXCL_TRX_TP_CD */
    private String[] ownrRelnCheckExclTrxTpCdList = null;

    /** VARCHAR CONST: OWNR_RELN_CHECK_EXCL_ACCT_NUM */
    private String ownrRelnCheckExclAcctNum = null;
    // add end 2019/08/07 QC#52198
    // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
    /** VARCHAR CONST: NSAB0620_END_DATE_BLANK */
    private String updateEndDateToBlank = null;

    /** VARCHAR CONST: NSAB0620_REQ_TECH_CD_BLANK */
    private String updateReqTechCdToBlank = null;
    // END 2023/10/06 Y.Nagasawa [QC#60082,ADD]

    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(this.salesDate)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date" });
        }

        // Get System Timestamp
        this.sysTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

        // Max Processing Count
        BigDecimal maxCnt = ZYPCodeDataUtil.getNumConstValue(NSAB0620_MAX_PROC_CNT, this.glblCmpyCd);
        if (hasValue(maxCnt)) {
            this.maxProcCnt = maxCnt.intValue();
        } else {
            this.maxProcCnt = DEF_MAX_COUNT;
        }

        // add start 2019/08/07 QC#52198
        String ownrRelnCheckExclTrxTpCd = ZYPCodeDataUtil.getVarCharConstValue(OWNR_RELN_CHECK_EXCL_TRX_TP_CD, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(ownrRelnCheckExclTrxTpCd)) {
           this.ownrRelnCheckExclTrxTpCdList = ownrRelnCheckExclTrxTpCd.split(",");
        }
        this.ownrRelnCheckExclAcctNum = ZYPCodeDataUtil.getVarCharConstValue(OWNR_RELN_CHECK_EXCL_ACCT_NUM, this.glblCmpyCd);
        // add end 2019/08/07 QC#52198
        // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
        this.updateEndDateToBlank = ZYPCodeDataUtil.getVarCharConstValue(UPDATE_END_DATE_TO_BLANK, this.glblCmpyCd);
        this.updateReqTechCdToBlank = ZYPCodeDataUtil.getVarCharConstValue(UPDATE_REQ_TECH_CD_TO_BLANK, this.glblCmpyCd);
        // END 2023/10/06 Y.Nagasawa [QC#60082,ADD]

        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.batchHelper = new S21RequestBatchHelper();
        this.modeListForCreateUpdate = new ArrayList<String>();
        this.modeListForCreateUpdate.add(MODE_CREATE);
        this.modeListForCreateUpdate.add(MODE_UPDATE);
        this.modeListForTerminate = new ArrayList<String>();
        this.modeListForTerminate.add(MODE_TERMINATE);
        this.modeList = new ArrayList<String>();
        this.modeList.add(MODE_CREATE);
        this.modeList.add(MODE_UPDATE);
        this.modeList.add(MODE_TERMINATE);
    }

    @Override
    protected void mainRoutine() {

        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }

        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            doProcess(request);
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            commit();
            // END 2017/02/15 N.Arai [QC#17301, ADD]
        }
    }

    @Override
    protected void termRoutine() {
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        // setTermState(this.termCd, this.normalCount, this.errCount);
        // mod start 2018/07/17 QC#22583
        //setTermState(this.termCd, this.normalCount, this.totalErrCount);
        setTermState(this.termCd, this.totalNormalCount, this.totalErrCount);
        // mod end 2018/07/17 QC#22583
        // END 2017/02/15 N.Arai [QC#17301, MOD]
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB062001().executeBatch(NSAB062001.class.getSimpleName());
    }

    private void doProcess(ART10FMsg fMsg) {

        // add start 2018/07/17 QC#22583
        this.insertCount = 0;
        this.updateCount = 0;
        // add end 2018/07/17 QC#22583
        // START 2017/02/15 N.Arai [QC#17301, ADD]
        this.errCount = 0;
        // END 2017/02/15 N.Arai [QC#17301, ADD]
        // Get Upload ID
        String upldCsvId = getUpldCsvId(fMsg);

        // Get Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(upldCsvRqstPk);
        this.messenger = new ZYPCSVUploadMessenger(upldCsvId, upldCsvRqstPk);

        // Processing Count
        int procCnt = getProcCnt(upldCsvRqstPk);
        if (procCnt > this.maxProcCnt) {
            this.errCount++;
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.totalErrCount++;
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            setWaringEndProc(fMsg, NSAM0542E, String.valueOf(this.maxProcCnt));
            return;
        }

        if (procCnt == 0) {
            this.errCount++;
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.totalErrCount++;
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            setWaringEndProc(fMsg, NSAM0543E);
            return;
        }

        List<BigDecimal> invalidRowNumList = getInvalidModeData(upldCsvRqstPk);
        if (invalidRowNumList.size() > 0) {
            for (BigDecimal invalidRowNum : invalidRowNumList) {
                this.messenger.addMessageForRecord(invalidRowNum, NSAM0040E, "Mode");
                this.errCount++;
            }
            // START 2017/02/15 N.Arai [QC#17301, DEL]
//          setWaringEndProc(fMsg, NSAM0510E);
//          return;
            // END 2017/02/15 N.Arai [QC#17301, DEL]
        }

        // Function List For Upload User
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        this.funcList = profile.getAuthorizedFunctionCodeListForUser(upldCSVRqstTMsg.ezInUserID.getValue());
        if (!isUploadAuth()) {
            this.errCount++;
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.totalErrCount++;
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            setWaringEndProc(fMsg, NSAM0544E);
            return;
        }

        // Upload for Non Configuration (Create, Update)
        uploadForNonConfig(upldCSVRqstTMsg);

        // START 2017/02/15 N.Arai [QC#17301, MOD]
        // if (this.errCount == 0) {
        // Upload for Configuration (Create, Update)
        uploadForConfig(upldCSVRqstTMsg);
        // }
        // END 2017/02/15 N.Arai [QC#17301, MOD]

        // START 2017/02/15 N.Arai [QC#17301, ADD]
        // if (this.errCount == 0) {
        // Upload for Terminate
        uploadForTerminate(upldCSVRqstTMsg);
        // }
        // END 2017/02/15 N.Arai [QC#17301, MOD]

        // START 2017/02/15 N.Arai [QC#17301, ADD]
        this.totalErrCount += this.errCount;
        // END 2017/02/15 N.Arai [QC#17301, MOD]
        if (this.errCount == 0) {
            commit();
            this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
            // add start 2018/07/17 QC#22583
            this.totalNormalCount = this.totalNormalCount + this.insertCount + this.updateCount;
            String arg = createResultMessageArg();
            this.messenger.addMessageForFile(NYXM0001I, arg);
            this.messenger.uploadMessage();
            // add end 2018/07/17 QC#22583
        } else {
            rollback();
            // mod start 2018/07/17 QC#22583
            //setWaringEndProc(fMsg, NSAM0510E);
            this.errCount = this.errCount + this.insertCount + this.updateCount;
            this.totalErrCount = this.totalErrCount + this.insertCount + this.updateCount;
            this.insertCount = 0;
            this.updateCount = 0;
            String arg = createResultMessageArg();
            setWaringEndProc(fMsg, NYXM0002E, arg);
            // mod end 2018/07/17 QC#22583
        }
    }

    private void setWaringEndProc(ART10FMsg fMsg, String msg, String arg) {
        this.termCd = TERM_CD.WARNING_END;
        this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
        this.messenger.addMessageForFile(msg, arg);
        this.messenger.uploadMessage();
    }

    private void setWaringEndProc(ART10FMsg fMsg, String msg) {
        setWaringEndProc(fMsg, msg, null);
    }

    private String getUpldCsvId(ART10FMsg fMsg) {
        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (!hasValue(uploadCsvId)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Upload CSV Request PK" });
        }
        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {
        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (!hasValue(removeDQRequestPK)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Upload CSV Request PK" });
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NSAM0040E, new String[] {"Upload CSV Request PK" });
        }
        return new BigDecimal(removeDQRequestPK);
    }

    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {
        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }
        return outTMsg;
    }

    private int getProcCnt(BigDecimal upldCsvRqstPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCsvRqstPk);
        BigDecimal cnt = (BigDecimal) this.ssmBatchClient.queryObject("getProcCnt", paramMap);
        return cnt.intValue();
    }

    private List<BigDecimal> getInvalidModeData(BigDecimal upldCsvRqstPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCsvRqstPk);
        paramMap.put("procModeCdList", this.modeList);
        List<BigDecimal> rowNumList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getInvalidModeData", paramMap);
        return rowNumList;
    }

    private boolean isUploadAuth() {
        for (String funcId : this.funcList) {
            if (FUNC_ID_UPDATE.equals(funcId)) {
                return true;
            }
            if (FUNC_ID_SER_CHANGE.equals(funcId)) {
                return true;
            }
        }
        return false;
    }

    private void uploadForNonConfig(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> paramMap = setParamMap(upldCSVRqstTMsg, this.modeListForCreateUpdate);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getSvcMachMstrForNonConfig", paramMap, setExecParam());
            rs = stmt.executeQuery();
            SVC_MACH_UPLD_WRKTMsg tMsg;
            while (rs.next()) {
                tMsg = setTMsg(rs);
                if (!validation(tMsg)) {
                    this.errCount++;
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                if (MODE_UPDATE.equals(tMsg.procModeCd.getValue())) {
                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = getTargetMach(tMsg);
                    if (svcMachMstrTMsg == null) {
                        this.errCount++;
                        // START 2017/02/15 N.Arai [QC#17301, MOD]
                        // return;
                        continue;
                        // END 2017/02/15 N.Arai [QC#17301, MOD]
                    }
                    margeSvcMachUpldWrk(tMsg, svcMachMstrTMsg);
                }

                // Call Machine Master Update API
                NSZC001001PMsg pMsg = setApiPMsg(tMsg);
                BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
                if (!callMachUpdApi(pMsg, rowNum)) {
                    this.errCount++;
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                // Create Service Memo
                if (!createSvcMemo(pMsg.svcMachMstrPk.getValue(), tMsg.svcCmntTxt.getValue(), rowNum)) {
                    this.errCount++;
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                // mod start 2018/07/17 QC#22583
                // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
                if (hasValue(tMsg.sldByLineBizTpCd) || hasValue(tMsg.reqTechCd)) {
                      if (!updateSldByLineBizTpCdAndReqTechCd(tMsg)) {
                          this.errCount++;
                          continue;
                      }
                }
                // End 2023/10/06 Y.Nagasawa [QC#60082,ADD]
                //this.normalCount++;
                if (MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
                    this.insertCount++;
                } else {
                    this.updateCount++;
                }
                // mod end 2018/07/17 QC#22583
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void uploadForConfig(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg) {
        // START 2017/07/06 K.Kojima [QC#19654,DEL]
        // PreparedStatement stmt = null;
        // ResultSet rs = null;
        // END 2017/07/06 K.Kojima [QC#19654,DEL]
        Map<String, Object> param = setParamMap(upldCSVRqstTMsg, this.modeListForCreateUpdate);
        // START 2017/07/06 K.Kojima [QC#19654,DEL]
        // try {
        // END 2017/07/06 K.Kojima [QC#19654,DEL]
            // START 2017/01/25 K.Kitachi [QC#17198, MOD]
            List<Map<String, Object>> trgtMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTergetPrntSerNum", param);
            Map<String, Object> paramMap;
            SVC_MACH_UPLD_WRKTMsg tMsg;
            List<BigDecimal> rowNumList;
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            Map<BigDecimal, String> rowNumMap;
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            Map<BigDecimal, String> configMap;
            String prntSerNum;
            // START 2018/08/22 M.Naito [QC#20733, ADD]
            String prntMdseCd;
            // END 2018/08/22 M.Naito [QC#20733, ADD]
            BigDecimal svcConfigMstrPk;
            for (Map<String, Object> trgtMap : trgtMapList) {
            // END 2017/01/25 K.Kitachi [QC#17198, MOD]
                // START 2017/01/25 K.Kitachi [QC#17198, ADD]
                prntSerNum = (String) trgtMap.get("PRNT_SER_NUM");
                // START 2018/08/22 M.Naito [QC#20733, ADD]
                prntMdseCd = (String) trgtMap.get("PRNT_MDSE_CD");
                // END 2018/08/22 M.Naito [QC#20733, ADD]
                svcConfigMstrPk = (BigDecimal) trgtMap.get("SVC_CONFIG_MSTR_PK");
                if (hasValue(svcConfigMstrPk) && !hasValue(prntSerNum)) {
                    paramMap = setParamMapForConfig(upldCSVRqstTMsg, this.modeListForCreateUpdate, svcConfigMstrPk);
                    // START 2017/07/06 K.Kojima [QC#19654,ADD]
                    PreparedStatement stmt1 = null;
                    ResultSet rs1 = null;
                    try {
                    // END 2017/07/06 K.Kojima [QC#19654,ADD]
                    stmt1 = this.ssmLLClient.createPreparedStatement("getSvcMachMstrForConfig", paramMap, setExecParam());
                    rs1 = stmt1.executeQuery();
                    while (rs1.next()) {
                        this.messenger.addMessageForRecord(rs1.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), NSAM0011E, "Configuration#");
                        this.errCount++;
                    }
                    // START 2017/07/06 K.Kojima [QC#19654,ADD]
                    } catch (SQLException se) {
                        sqlExceptionHandler(se);
                        return;
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(stmt1, rs1);
                    }
                    // END 2017/07/06 K.Kojima [QC#19654,ADD]
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }
                // END 2017/01/25 K.Kitachi [QC#17198, ADD]
                // START 2018/09/26 M.Naito [QC#28493, ADD]
                // START 2016/07/04 T.Tomita [QC#11109, ADD]
                // START 2018/08/22 M.Naito [QC#20733, MOD]
//                SVC_MACH_MSTRTMsg mainMachTMsg = getSvcMachMstr(null, prntSerNum);
//                if (!hasValue(prntSerNum) && hasValue(prntMdseCd)) {
//                    this.messenger.addMessageForRecord((BigDecimal) trgtMap.get("UPLD_CSV_RQST_ROW_NUM"), ZZM9000E, "Parent Item Code");
//                    this.errCount++;
//                    continue;
//                }
//                SVC_MACH_MSTRTMsg mainMachTMsg = new SVC_MACH_MSTRTMsg();
//                SVC_MACH_MSTRTMsgArray svcMachMstrList = getSvcMachMstrBySerNum(prntSerNum, prntMdseCd);
//                if (svcMachMstrList != null) {
//                    if (svcMachMstrList.getValidCount() == 1) {
//                        mainMachTMsg = svcMachMstrList.no(0);
//                    } else if (svcMachMstrList.getValidCount() > 0) {
//                        this.messenger.addMessageForRecord((BigDecimal) trgtMap.get("UPLD_CSV_RQST_ROW_NUM"), NSAM0018E, null);
//                        this.errCount++;
//                        continue;
//                    }
//                }
                // END 2018/08/22 M.Naito [QC#20733, MOD]
                BigDecimal prntSvcMachMstrPk = null;
                SVC_MACH_MSTRTMsg mainMachTMsg = new SVC_MACH_MSTRTMsg();
//                if (mainMachTMsg != null) {
//                    prntSvcMachMstrPk = mainMachTMsg.svcMachMstrPk.getValue();
//                }
                // END 2016/07/04 T.Tomita [QC#11109, ADD]
                // END 2018/09/26 M.Naito [QC#28493, ADD]
                paramMap = setParamMap(upldCSVRqstTMsg, this.modeListForCreateUpdate, prntSerNum);
                // START 2018/09/26 M.Naito [QC#28493, ADD]
                paramMap.put("prntMdseCd", prntMdseCd);
                // END 2018/09/26 M.Naito [QC#28493, ADD]
                // START 2017/07/06 K.Kojima [QC#19654,ADD]
                rowNumList = new ArrayList<BigDecimal>();
                rowNumMap = new HashMap<BigDecimal, String>();
                configMap = new HashMap<BigDecimal, String>();
                PreparedStatement stmt2 = null;
                ResultSet rs2 = null;
                // add start 2018/07/17 QC#22583
                int insCount = 0;
                int updCount = 0;
                // add end 2018/07/17 QC#22583
                try {
                // END 2017/07/06 K.Kojima [QC#19654,ADD]
                stmt2 = this.ssmLLClient.createPreparedStatement("getSvcMachMstrForConfig", paramMap, setExecParam());
                rs2 = stmt2.executeQuery();
                // START 2017/07/06 K.Kojima [QC#19654,DEL]
                // rowNumList = new ArrayList<BigDecimal>();
                // // START 2017/02/15 N.Arai [QC#17301, ADD]
                // rowNumMap = new HashMap<BigDecimal, String>();
                // // END 2017/02/15 N.Arai [QC#17301, ADD]
                // configMap = new HashMap<BigDecimal, String>();
                // END 2017/07/06 K.Kojima [QC#19654,DEL]
                while (rs2.next()) {
                    tMsg = setTMsg(rs2);

                    // START 2016/10/14 T.Tomita [QC#14734, ADD]
                    if (SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(tMsg.svcMachMstrStsCd.getValue())) {
                        SVC_MACH_MSTR_STSTMsg svcMachMstrStsTMsg = new SVC_MACH_MSTR_STSTMsg();
                        setValue(svcMachMstrStsTMsg.glblCmpyCd, tMsg.glblCmpyCd);
                        setValue(svcMachMstrStsTMsg.svcMachMstrStsCd, tMsg.svcMachMstrStsCd);
                        svcMachMstrStsTMsg = (SVC_MACH_MSTR_STSTMsg) S21CodeTableAccessor.findByKey(svcMachMstrStsTMsg);
                        this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0613E, svcMachMstrStsTMsg.svcMachMstrStsDescTxt.getValue());
                        this.errCount++;
                        // START 2017/02/15 N.Arai [QC#17301, MOD]
                        // return;
                        continue;
                        // END 2017/02/15 N.Arai [QC#17301, MOD]
                    }
                    // END 2016/10/14 T.Tomita [QC#14734, ADD]

                    if (!validation(tMsg)) {
                        this.errCount++;
                        // START 2017/02/15 N.Arai [QC#17301, MOD]
                        // return;
                        continue;
                        // END 2017/02/15 N.Arai [QC#17301, MOD]
                    }

                    // START 2018/09/26 M.Naito [QC#28493, ADD]
                    if (!hasValue(tMsg.prntSerNum.getValue()) && hasValue(tMsg.prntMdseCd.getValue())) {
                        this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), ZZM9000E, "Parent Serial#");
                        this.errCount++;
                        continue;
                    }
                    SVC_MACH_MSTRTMsgArray svcMachMstrList = getSvcMachMstrBySerNum(tMsg.prntSerNum.getValue(), tMsg.prntMdseCd.getValue());
                    if (svcMachMstrList != null) {
                        if (svcMachMstrList.getValidCount() == 1) {
                            mainMachTMsg = svcMachMstrList.no(0);
                        } else if (svcMachMstrList.getValidCount() > 0) {
                            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0018E, null);
                            this.errCount++;
                            continue;
                        }
                    }
                    if (mainMachTMsg != null) {
                      prntSvcMachMstrPk = mainMachTMsg.svcMachMstrPk.getValue();
                    }
                    // END 2018/09/26 M.Naito [QC#28493, ADD]

                    if (MODE_UPDATE.equals(tMsg.procModeCd.getValue())) {
                        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getTargetMach(tMsg);
                        if (svcMachMstrTMsg == null) {
                            this.errCount++;
                            // START 2017/02/15 N.Arai [QC#17301, MOD]
                            // return;
                            continue;
                            // END 2017/02/15 N.Arai [QC#17301, MOD]
                        }
                        margeSvcMachUpldWrk(tMsg, svcMachMstrTMsg);
                    }

                    // Call Machine Master Update API
                    NSZC001001PMsg pMsg = setApiPMsg(tMsg);
                    BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
                    if (!callMachUpdApi(pMsg, rowNum)) {
                        this.errCount++;
                        // START 2017/02/15 N.Arai [QC#17301, MOD]
                        // return;
                        continue;
                        // END 2017/02/15 N.Arai [QC#17301, MOD]
                    }

                    // Create Service Memo
                    if (!createSvcMemo(pMsg.svcMachMstrPk.getValue(), tMsg.svcCmntTxt.getValue(), rowNum)) {
                        this.errCount++;
                        // START 2017/02/15 N.Arai [QC#17301, MOD]
                        // return;
                        continue;
                        // END 2017/02/15 N.Arai [QC#17301, MOD]
                    }

                    // Configuration Machine List
                    if (!configMap.containsKey(pMsg.svcMachMstrPk.getValue())) {
                        configMap.put(pMsg.svcMachMstrPk.getValue(), pMsg.svcMachRmvDt.getValue());
                    }

                    // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
                    if (hasValue(tMsg.sldByLineBizTpCd) || hasValue(tMsg.reqTechCd)) {
                        if (!updateSldByLineBizTpCdAndReqTechCd(tMsg)) {
                            this.errCount++;
                            continue;
                        }
                    }
                    // End 2023/10/06 Y.Nagasawa [QC#60082,ADD]

                    rowNumList.add(tMsg.upldCsvRqstRowNum.getValue());
                    // START 2017/02/15 N.Arai [QC#17301, ADD]
                    rowNumMap.put(tMsg.upldCsvRqstRowNum.getValue(), null);
                    // END 2017/02/15 N.Arai [QC#17301, MOD]

                    // add start 2018/07/17 QC#22583
                    if (MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
                        insCount++;
                    } else {
                        updCount++;
                    }
                    // add end 2018/07/17 QC#22583
                }
                // START 2017/07/06 K.Kojima [QC#19654,ADD]
                } catch (SQLException se) {
                    sqlExceptionHandler(se);
                    return;
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(stmt2, rs2);
                }
                // END 2017/07/06 K.Kojima [QC#19654,ADD]

                if (!hasValue(prntSerNum) || configMap.isEmpty()) {
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                // Get Main Machine Data
                // START 2016/07/04 T.Tomita [QC#11109, MOD]
                mainMachTMsg = getSvcMachMstr(prntSvcMachMstrPk, prntSerNum);
                // END 2016/07/04 T.Tomita [QC#11109, MOD]
                // START 2017/07/06 K.Kojima [QC#19654,ADD]
                boolean mainMachConfigPkError = false;
                if (mainMachTMsg != null && !hasValue(mainMachTMsg.svcConfigMstrPk)) {
                    if (!configMap.containsKey(mainMachTMsg.svcMachMstrPk.getValue())) {
                        mainMachConfigPkError = true;
                    }
                }
                // END 2017/07/06 K.Kojima [QC#19654,ADD]
                // START 2017/01/25 K.Kitachi [QC#17198, ADD]
                // START 2017/07/06 K.Kojima [QC#19654,MOD]
                // if (mainMachTMsg == null) {
                if (mainMachTMsg == null || mainMachConfigPkError) {
                // END 2017/07/06 K.Kojima [QC#19654,MOD]
                    paramMap = setParamMap(upldCSVRqstTMsg, this.modeListForCreateUpdate, prntSerNum);
                    // START 2017/07/06 K.Kojima [QC#19654,ADD]
                    PreparedStatement stmt3 = null;
                    ResultSet rs3 = null;
                    try {
                    // END 2017/07/06 K.Kojima [QC#19654,ADD]
                    stmt3 = this.ssmLLClient.createPreparedStatement("getSvcMachMstrForConfig", paramMap, setExecParam());
                    rs3 = stmt3.executeQuery();
                    while (rs3.next()) {
                        // START 2017/02/15 N.Arai [QC#17301, MOD]
                        if (rowNumMap.containsKey(rs3.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"))) {
                            // START 2017/07/06 K.Kojima [QC#19654,MOD]
                            // this.messenger.addMessageForRecord(rs3.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), NSAM0011E, "Parent Serial#");
                            if (mainMachTMsg == null) {
                                this.messenger.addMessageForRecord(rs3.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), NSAM0011E, "Parent Serial#");
                            } else {
                                this.messenger.addMessageForRecord(rs3.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"), NSAM0685E, null);
                            }
                            // END 2017/07/06 K.Kojima [QC#19654,MOD]
                            this.errCount++;
                        }
                        // END 2017/02/15 N.Arai [QC#17301, MOD]
                    }
                    // START 2017/07/06 K.Kojima [QC#19654,ADD]
                    } catch (SQLException se) {
                        sqlExceptionHandler(se);
                        return;
                    } finally {
                        S21SsmLowLevelCodingClient.closeResource(stmt3, rs3);
                    }
                    // END 2017/07/06 K.Kojima [QC#19654,ADD]
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }
                // END 2017/01/25 K.Kitachi [QC#17198, ADD]
                if (!validationMainMach(mainMachTMsg, rowNumList)) {
                    this.errCount = this.errCount + rowNumList.size();
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }
                // Get Configuration Machine Data
                getConfigMach(mainMachTMsg, configMap);

                // Configuration validation
                if (!validationConfig(mainMachTMsg, configMap, rowNumList)) {
                    this.errCount = this.errCount + rowNumList.size();
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                // Configuration Call API
                NSZC001001PMsg pMsg = setApiPMsgForConfig(mainMachTMsg, configMap);
                if (!callMachUpdApiForConfig(pMsg, rowNumList)) {
                    this.errCount = this.errCount + rowNumList.size();
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                // mod start 2018/07/17 QC#22583
                //this.normalCount = this.normalCount + rowNumList.size();
                this.insertCount += insCount;
                this.updateCount += updCount;
                // mod end 2018/07/17 QC#22583
            }
        // START 2017/07/06 K.Kojima [QC#19654,DEL]
        // } catch (SQLException se) {
        //     sqlExceptionHandler(se);
        // } finally {
        //     S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        // }
        // END 2017/07/06 K.Kojima [QC#19654,DEL]
    }

    private void uploadForTerminate(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> paramMap = setParamMap(upldCSVRqstTMsg, this.modeListForTerminate);
        try {
            stmt = this.ssmLLClient.createPreparedStatement("getSvcMachMstrForTerminate", paramMap, setExecParam());
            rs = stmt.executeQuery();
            SVC_MACH_UPLD_WRKTMsg tMsg;
            while (rs.next()) {
                tMsg = setTMsg(rs);
                if (!validation(tMsg)) {
                    this.errCount++;
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                SVC_MACH_MSTRTMsg svcMachMstrTMsg = getTargetMach(tMsg);
                if (svcMachMstrTMsg == null) {
                    this.errCount++;
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }
                margeSvcMachUpldWrk(tMsg, svcMachMstrTMsg);

                // Call Machine Master Update API
                NSZC001001PMsg pMsg = setApiPMsg(tMsg);
                BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
                if (!callMachUpdApi(pMsg, rowNum)) {
                    this.errCount++;
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                // Create Service Memo
                if (!createSvcMemo(pMsg.svcMachMstrPk.getValue(), tMsg.svcCmntTxt.getValue(), rowNum)) {
                    this.errCount++;
                    // START 2017/02/15 N.Arai [QC#17301, MOD]
                    // return;
                    continue;
                    // END 2017/02/15 N.Arai [QC#17301, MOD]
                }

                // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
                if (hasValue(tMsg.sldByLineBizTpCd) || hasValue(tMsg.reqTechCd)) {
                    if (!updateSldByLineBizTpCdAndReqTechCd(tMsg)) {
                        this.errCount++;
                        continue;
                    }
                }
                // End 2023/10/06 Y.Nagasawa [QC#60082,ADD]

                // mod start 2018/07/17 QC#22583
                //this.normalCount++;
                if (MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
                    this.insertCount++;
                } else {
                    this.updateCount++;
                }
                // mod end 2018/07/17 QC#22583
            }
        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
    private boolean updateSldByLineBizTpCdAndReqTechCd(SVC_MACH_UPLD_WRKTMsg svcMachUpldWrkTMsg){
        BigDecimal rowNum = svcMachUpldWrkTMsg.upldCsvRqstRowNum.getValue();
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getTargetMach(svcMachUpldWrkTMsg);
        NSZC001001PMsg pMsg = setSldByLineBizTpCdAndReqTechCdPMsg(svcMachUpldWrkTMsg, svcMachMstrTMsg);
        if (!callMachUpdApiForSldByLineBizTpCdAndReqTechCd(pMsg, rowNum)) {
            return false;
        }
        return true;
    }
    // End 2023/10/06 Y.Nagasawa [QC#60082,ADD]

    private S21SsmExecutionParameter setExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_COMMIT_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> setParamMap(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg, List<String> procModeCdList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk);
        paramMap.put("procModeCdList", procModeCdList);
        return paramMap;
    }

    private Map<String, Object> setParamMap(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg, List<String> procModeCdList, String prntSerNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk);
        paramMap.put("procModeCdList", procModeCdList);
        paramMap.put("prntSerNum", prntSerNum);
        return paramMap;
    }

    private SVC_MACH_UPLD_WRKTMsg setTMsg(ResultSet rs) throws SQLException {
        SVC_MACH_UPLD_WRKTMsg rtnTMsg = new SVC_MACH_UPLD_WRKTMsg();
        setValue(rtnTMsg.ezTableID, rs.getString("EZTABLEID"));
        setValue(rtnTMsg.ezCancelFlag, rs.getString("EZCANCELFLAG"));
        setValue(rtnTMsg.ezInTime, rs.getString("EZINTIME"));
        setValue(rtnTMsg.ezInTimeZone, rs.getString("EZINTIMEZONE"));
        setValue(rtnTMsg.ezInCompanyCode, rs.getString("EZINCOMPANYCODE"));
        setValue(rtnTMsg.ezInUserID, rs.getString("EZINUSERID"));
        setValue(rtnTMsg.ezInAplID, rs.getString("EZINAPLID"));
        setValue(rtnTMsg.ezUpTime, rs.getString("EZUPTIME"));
        setValue(rtnTMsg.ezUpTimeZone, rs.getString("EZUPTIMEZONE"));
        setValue(rtnTMsg.ezUpCompanyCode, rs.getString("EZUPCOMPANYCODE"));
        setValue(rtnTMsg.ezUpUserID, rs.getString("EZUPUSERID"));
        setValue(rtnTMsg.ezUpAplID, rs.getString("EZUPAPLID"));
        setValue(rtnTMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
        setValue(rtnTMsg.upldCsvRqstPk, rs.getBigDecimal("UPLD_CSV_RQST_PK"));
        setValue(rtnTMsg.upldCsvRqstRowNum, rs.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        setValue(rtnTMsg.procModeCd, rs.getString("PROC_MODE_CD"));
        setValue(rtnTMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        setValue(rtnTMsg.serNum, rs.getString("SER_NUM"));
        setValue(rtnTMsg.mdseCd, rs.getString("MDSE_CD"));
        setValue(rtnTMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        setValue(rtnTMsg.prntSerNum, rs.getString("PRNT_SER_NUM"));
        // START 2018/09/26 M.Naito [QC#28493, ADD]
        setValue(rtnTMsg.prntMdseCd, rs.getString("PRNT_MDSE_CD"));
        // END 2018/09/26 M.Naito [QC#28493, ADD]
        setValue(rtnTMsg.ownrAcctNum, rs.getString("OWNR_ACCT_NUM"));
        setValue(rtnTMsg.billToAcctNum, rs.getString("BILL_TO_ACCT_NUM"));
        setValue(rtnTMsg.curLocAcctNum, rs.getString("CUR_LOC_ACCT_NUM"));
        setValue(rtnTMsg.indBillToLocNum, rs.getString("IND_BILL_TO_LOC_NUM"));
        setValue(rtnTMsg.indCurLocNum, rs.getString("IND_CUR_LOC_NUM"));
        setValue(rtnTMsg.svcMachMstrStsCd, rs.getString("SVC_MACH_MSTR_STS_CD"));
        setValue(rtnTMsg.svcMachUsgStsCd, rs.getString("SVC_MACH_USG_STS_CD"));
        setValue(rtnTMsg.svcMachTrxTpCd, rs.getString("SVC_MACH_TRX_TP_CD"));
        setValue(rtnTMsg.istlDt, rs.getString("ISTL_DT"));
        setValue(rtnTMsg.effFromDt, rs.getString("EFF_FROM_DT"));
        setValue(rtnTMsg.effThruDt, rs.getString("EFF_THRU_DT"));
        setValue(rtnTMsg.svcMachRmvDt, rs.getString("SVC_MACH_RMV_DT"));
        setValue(rtnTMsg.custMachCtrlNum, rs.getString("CUST_MACH_CTRL_NUM"));
        setValue(rtnTMsg.svcCmntTxt, rs.getString("SVC_CMNT_TXT"));
        setValue(rtnTMsg.upldCsvRqstCmntTxt, rs.getString("UPLD_CSV_RQST_CMNT_TXT"));
        // START 2017/07/07 K.Kojima [QC#19660,ADD]
        setValue(rtnTMsg.svcByLineBizTpCd, rs.getString("SVC_BY_LINE_BIZ_TP_CD"));
        // END 2017/07/07 K.Kojima [QC#19660,ADD]
        // START 2023/07/31 S.Moriai [QC#61630,ADD]
        setValue(rtnTMsg.swLicId, rs.getString("SW_LIC_ID"));
        // END 2023/07/31 S.Moriai [QC#61630,ADD]
        // START 2023/10/03 S.Naya [QC#54186,ADD]
        setValue(rtnTMsg.svcPrvdPtyDescTxt_01, rs.getString("SVC_PRVD_PTY_DESC_TXT_01"));
        setValue(rtnTMsg.svcPrvdPtyDescTxt_02, rs.getString("SVC_PRVD_PTY_DESC_TXT_02"));
        // END   2023/10/03 S.Naya [QC#54186,ADD]
        // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
        setValue(rtnTMsg.sldByLineBizTpCd, rs.getString("SLD_BY_LINE_BIZ_TP_CD"));
        setValue(rtnTMsg.reqTechCd, rs.getString("REQ_TECH_CD"));
        // END 2023/10/06 Y.Nagasawa [QC#60082,ADD]
        return rtnTMsg;
    }

    private SVC_MACH_MSTRTMsg getTargetMach(SVC_MACH_UPLD_WRKTMsg tMsg) {
        BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
        // Install Base ID & Serial#
        if (!hasValue(tMsg.svcMachMstrPk) && !hasValue(tMsg.serNum)) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, "Install Base ID or Serial#");
            return null;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(tMsg.svcMachMstrPk.getValue(), tMsg.serNum.getValue());
        if (svcMachMstrTMsg == null) {
            if (hasValue(tMsg.svcMachMstrPk)) {
                this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Install Base ID");
            } else {
                this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Serial#");
            }
            return null;
        }
        // START 2023/10/03 S.Naya [QC#54186,ADD]
        if (!existsSvcPrvdPtyCd(svcMachMstrTMsg.istlBySvcPrvdPtyCd.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "\"To Be Installed By\" of Machine master to be updated");
            return null;
        }           
        if (!existsSvcPrvdPtyCd(svcMachMstrTMsg.svcBySvcPrvdPtyCd.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "\"Service Provided By\" of Machine master to be updated");
            return null;
        }
        // END   2023/10/03 S.Naya [QC#54186,ADD]

        return svcMachMstrTMsg;
    }

    private void margeSvcMachUpldWrk(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        // SVC_MACH_MSTR_PK
        setValue(tMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);

        // SER_NUM
        // START 2016/07/04 T.Tomita [QC#11109, MOD]
        setValue(tMsg.serNum, getValue(tMsg.serNum, svcMachMstrTMsg.serNum));
        // END 2016/07/04 T.Tomita [QC#11109, MOD]

        // MDSE_CD
        setValue(tMsg.mdseCd, getValue(tMsg.mdseCd, svcMachMstrTMsg.mdseCd));

        // SVC_CONFIG_MSTR_PK
        setValue(tMsg.svcConfigMstrPk, getValue(tMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk));

        // PRNT_SER_NUM
        setValue(tMsg.prntSerNum, getValue(tMsg.prntSerNum, svcMachMstrTMsg.prntSerNum));

        // SVC_MACH_USG_STS_CD
        setValue(tMsg.svcMachUsgStsCd, getValue(tMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd));

        // SVC_MACH_TRX_TP_CD
        setValue(tMsg.svcMachTrxTpCd, getValue(tMsg.svcMachTrxTpCd, svcMachMstrTMsg.svcMachTrxTpCd));

        // ISTL_DT
        setValue(tMsg.istlDt, getValue(tMsg.istlDt, svcMachMstrTMsg.istlDt));

        // EFF_FROM_DT
        setValue(tMsg.effFromDt, getValue(tMsg.effFromDt, svcMachMstrTMsg.effFromDt));

        // SVC_MACH_RMV_DT
        setValue(tMsg.svcMachRmvDt, getValue(tMsg.svcMachRmvDt, svcMachMstrTMsg.svcMachRmvDt));

        // CUST_MACH_CTRL_NUM
        setValue(tMsg.custMachCtrlNum, getValue(tMsg.custMachCtrlNum, svcMachMstrTMsg.custMachCtrlNum));

        // START 2023/10/03 S.Naya [QC#54186,MOD]
        //// START 2017/07/07 K.Kojima [QC#19660,ADD]
        //setValue(tMsg.svcByLineBizTpCd, getValue(tMsg.svcByLineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd));
        //// END 2017/07/07 K.Kojima [QC#19660,ADD]
        setValue(tMsg.svcPrvdPtyDescTxt_01, getDescTxt(tMsg.svcPrvdPtyDescTxt_01.getValue(), svcMachMstrTMsg.istlBySvcPrvdPtyCd.getValue()));
        setValue(tMsg.svcPrvdPtyDescTxt_02, getDescTxt(tMsg.svcPrvdPtyDescTxt_02.getValue(), svcMachMstrTMsg.svcBySvcPrvdPtyCd.getValue()));
        // END   2023/10/03 S.Naya [QC#54186,MOD]
        // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
        // SLD_BY_LINE_BIZ_TP_CD
        setValue(tMsg.sldByLineBizTpCd, getValue(tMsg.sldByLineBizTpCd, svcMachMstrTMsg.sldByLineBizTpCd));

        // REQ_TECH_CD
        setValue(tMsg.reqTechCd, getValue((tMsg.reqTechCd), svcMachMstrTMsg.reqTechCd));
        // END 2023/10/06 Y.Nagasawa [QC#60082,ADD]

        if (MODE_UPDATE.equals(tMsg.procModeCd.getValue())) {
            // EFF_THRU_DT
            // START 2023/10/06 Y.Nagasawa [QC#60082,MOD]
            // setValue(tMsg.effThruDt, getValue(tMsg.effThruDt, svcMachMstrTMsg.effThruDt));
            if (this.updateEndDateToBlank.equals(tMsg.effThruDt.getValue())) {
                setValue(tMsg.effThruDt, "");
            } else {
                setValue(tMsg.effThruDt, getValue(tMsg.effThruDt, svcMachMstrTMsg.effThruDt));
            }
            // END 2023/10/06 Y.Nagasawa [QC#60082,MOD]

            // SVC_MACH_MSTR_STS_CD
            setValue(tMsg.svcMachMstrStsCd, getValue(tMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd));

            if (tMsg.svcMachMstrStsCd.getValue().equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())
                    || !isChangeLoc(tMsg)) {
                // OWNR_ACCT_NUM
                setValue(tMsg.ownrAcctNum, getValue(tMsg.ownrAcctNum, svcMachMstrTMsg.ownrAcctNum));

                // BILL_TO_ACCT_NUM
                setValue(tMsg.billToAcctNum, getValue(tMsg.billToAcctNum, svcMachMstrTMsg.billToAcctNum));

                // CUR_LOC_ACCT_NUM
                setValue(tMsg.curLocAcctNum, getValue(tMsg.curLocAcctNum, svcMachMstrTMsg.curLocAcctNum));

                // IND_BILL_TO_LOC_NUM
                setValue(tMsg.indBillToLocNum, getValue(tMsg.indBillToLocNum, svcMachMstrTMsg.indBillToLocNum));

                // IND_CUR_LOC_NUM
                setValue(tMsg.indCurLocNum, getValue(tMsg.indCurLocNum, svcMachMstrTMsg.indCurLocNum));
            }
        } else if (MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            // SVC_MACH_MSTR_STS_CD
            setValue(tMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
        }
    }

    private boolean isChangeLoc(SVC_MACH_UPLD_WRKTMsg tMsg) {
        // OWNR_ACCT_NUM
        if (hasValue(tMsg.ownrAcctNum)) {
            return true;
        }

        // BILL_TO_ACCT_NUM
        if (hasValue(tMsg.billToAcctNum)) {
            return true;
        }

        // CUR_LOC_ACCT_NUM
        if (hasValue(tMsg.curLocAcctNum)) {
            return true;
        }

        // IND_BILL_TO_LOC_NUM
        if (hasValue(tMsg.indBillToLocNum)) {
            return true;
        }

        // IND_CUR_LOC_NUM
        if (hasValue(tMsg.indCurLocNum)) {
            return true;
        }
        return false;
    }

    private boolean validation(SVC_MACH_UPLD_WRKTMsg tMsg) {
        if (MODE_CREATE.equals(tMsg.procModeCd.getValue()) && !validationMandatoryCreate(tMsg)) {
            return false;
        }

        if (MODE_TERMINATE.equals(tMsg.procModeCd.getValue()) && !validationMandatoryTerminate(tMsg)) {
            return false;
        }

        if (!validationMster(tMsg)) {
            return false;
        }

        if (!validationReln(tMsg)) {
            return false;
        }

        return true;
    }

    private boolean validationMainMach(SVC_MACH_MSTRTMsg mainMachTMsg, List<BigDecimal> rowNumList) {
        // Main Machine Configuration Check
        if (isErrMainMach(mainMachTMsg, rowNumList)) {
            return false;
        }
        return true;
    }

    private boolean validationConfig(SVC_MACH_MSTRTMsg mainMachTMsg, Map<BigDecimal, String> configMap, List<BigDecimal> rowNumList) {
        // Component Check
        SVC_MACH_MSTRTMsg configMachTMsg;
        for (Map.Entry<BigDecimal, String> entry : configMap.entrySet()) {
            BigDecimal svcMachMstrPk = entry.getKey();
            String svcMachRmvDt = entry.getValue();
            configMachTMsg = getSvcMachMstr(svcMachMstrPk, null);

            if (isErrConfigMach(mainMachTMsg, configMachTMsg, rowNumList)) {
                return false;
            }

            if (isErrRmvDt(svcMachRmvDt, rowNumList)) {
                return false;
            }
        }
        return true;
    }

    private boolean validationMandatoryCreate(SVC_MACH_UPLD_WRKTMsg tMsg) {
        BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
        // Item Code
        if (isErrForMandatory(tMsg.mdseCd, rowNum, "Item Code")) {
            return false;
        }

        // Current Loc#
        if (isErrForMandatory(tMsg.indCurLocNum, rowNum, "Current Loc#")) {
            return false;
        }

        // IB Status
        if (isErrForMandatory(tMsg.svcMachMstrStsCd, rowNum, "IB Status")) {
            return false;
        }

        // IB Usage
        if (isErrForMandatory(tMsg.svcMachUsgStsCd, rowNum, "IB Usage")) {
            return false;
        }

        // Start Date
        if (isErrForMandatory(tMsg.effFromDt, rowNum, "Start Date")) {
            return false;
        }

        // START 2023/10/03 S.Naya [QC#54186,ADD]
        // To Be Installed By
        if (isErrForMandatory(tMsg.svcPrvdPtyDescTxt_01, rowNum, "To Be Installed By")) {
            return false;
        }
        
        // Service Provided By
        if (isErrForMandatory(tMsg.svcPrvdPtyDescTxt_02, rowNum, "Service Provided By")) {
            return false;
        }
        // END   2023/10/03 S.Naya [QC#54186,ADD]

        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(tMsg.svcMachMstrStsCd.getValue())) {
            // Owner Acct#
            if (isErrForMandatory(tMsg.ownrAcctNum, rowNum, "Owner Acct#")) {
                return false;
            }

            // Bill To Acct#
            if (isErrForMandatory(tMsg.billToAcctNum, rowNum, "Bill To Acct#")) {
                return false;
            }

            // Current Loc Acct#
            if (isErrForMandatory(tMsg.curLocAcctNum, rowNum, "Current Loc Acct#")) {
                return false;
            }

            // Bill To Loc#
            if (isErrForMandatory(tMsg.indBillToLocNum, rowNum, "Bill To Loc#")) {
                return false;
            }

            // IB Trx Type
// START 2016/11/29 [QC#16217, DEL]
//            if (isErrForMandatory(tMsg.svcMachTrxTpCd, rowNum, "IB Trx Type")) {
//                return false;
//            }
// END   2016/11/29 [QC#16217, DEL]
        }

        if (SVC_MACH_MSTR_STS.INSTALLED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(tMsg.svcMachMstrStsCd.getValue())) {
            // Owner Acct#
            if (isErrForMandatory(tMsg.ownrAcctNum, rowNum, "Owner Acct#")) {
                return false;
            }

            // Bill To Acct#
            if (isErrForMandatory(tMsg.billToAcctNum, rowNum, "Bill To Acct#")) {
                return false;
            }

            // Current Loc Acct#
            if (isErrForMandatory(tMsg.curLocAcctNum, rowNum, "Current Loc Acct#")) {
                return false;
            }

            // Bill To Loc#
            if (isErrForMandatory(tMsg.indBillToLocNum, rowNum, "Bill To Loc#")) {
                return false;
            }

            // IB Trx Type
// START 2016/11/29 [QC#16217, DEL]
//            if (isErrForMandatory(tMsg.svcMachTrxTpCd, rowNum, "IB Trx Type")) {
//                return false;
//            }
// END   2016/11/29 [QC#16217, DEL]

            // Install Date
            if (isErrForMandatory(tMsg.istlDt, rowNum, "Install Date")) {
                return false;
            }
        }

        return true;
    }

    private boolean validationMandatoryTerminate(SVC_MACH_UPLD_WRKTMsg tMsg) {
        BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
        // End Date
        if (isErrForMandatory(tMsg.effThruDt, rowNum, "End Date")) {
            return false;
        }
        return true;
    }

    private boolean validationMster(SVC_MACH_UPLD_WRKTMsg tMsg) {
        BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
        if (!MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
            // Install Base ID & Serial#
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(tMsg.svcMachMstrPk.getValue(), tMsg.serNum.getValue());
            if (svcMachMstrTMsg == null) {
                if (hasValue(tMsg.svcMachMstrPk)) {
                    this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Install Base ID");
                } else {
                    this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Serial#");
                }
                return false;
            }

            // Serial# Change
            if (hasValue(tMsg.serNum) && !tMsg.serNum.getValue().equals(svcMachMstrTMsg.serNum.getValue())) {
                if (!this.funcList.contains(FUNC_ID_SER_CHANGE)) {
                    this.messenger.addMessageForRecord(rowNum, NSAM0545E, "Serial# Change");
                    return false;
                }
            }

            // Item Change
            if (hasValue(tMsg.mdseCd) && !tMsg.mdseCd.getValue().equals(svcMachMstrTMsg.mdseCd.getValue())) {
                if (!this.funcList.contains(FUNC_ID_MDSE_EDIT)) {
                    this.messenger.addMessageForRecord(rowNum, NSAM0545E, "Item Change");
                    return false;
                }
            }

            // START 2021/11/15 R.Jabal [QC#59104, ADD]
            if (hasValue(svcMachMstrTMsg.svcMachMaintAvalFlg)
                    && ZYPConstant.FLG_OFF_N.equals(svcMachMstrTMsg.svcMachMaintAvalFlg.getValue())) {
                    this.messenger.addMessageForRecord(rowNum, NSAM0758E, null);
                    return false;
            }
            // END 2021/11/15 R.Jabal [QC#59104, ADD]
        }

        // Item Code
        if (hasValue(tMsg.mdseCd) && !validationMdseMster(tMsg)) {
            return false;
        }

        // Owner Acct#
        if (hasValue(tMsg.ownrAcctNum) && !existsSellToCust(tMsg.ownrAcctNum.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Owner Acct#");
            return false;
        }

        // Bill To Acct#
        if (hasValue(tMsg.billToAcctNum) && !existsSellToCust(tMsg.billToAcctNum.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Bill To Acct#");
            return false;
        }

        // Current Loc Acct#
        if (hasValue(tMsg.curLocAcctNum) && !existsSellToCust(tMsg.curLocAcctNum.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Current Loc Acct#");
            return false;
        }

        // Bill To Loc#
        if (hasValue(tMsg.indBillToLocNum) && !existsBillToCust(tMsg.indBillToLocNum.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Bill To Loc#");
            return false;
        }

        // Current Loc#
        if (hasValue(tMsg.indCurLocNum) && !existsShipToCust(tMsg.indCurLocNum.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Current Loc#");
            return false;
        }

        // IB Status
        if (hasValue(tMsg.svcMachMstrStsCd)) {
            SVC_MACH_MSTR_STSTMsg svcMachMstrStsTMsg = (SVC_MACH_MSTR_STSTMsg) ZYPCodeDataUtil.findByCode("SVC_MACH_MSTR_STS", this.glblCmpyCd, tMsg.svcMachMstrStsCd.getValue());
            if (svcMachMstrStsTMsg == null) {
                this.messenger.addMessageForRecord(rowNum, NSAM0011E, "IB Status");
                return false;
            }
        }

        // IB Usage
        if (hasValue(tMsg.svcMachUsgStsCd)) {
            SVC_MACH_USG_STSTMsg svcMachUsgStsTMsg = (SVC_MACH_USG_STSTMsg) ZYPCodeDataUtil.findByCode("SVC_MACH_USG_STS", this.glblCmpyCd, tMsg.svcMachUsgStsCd.getValue());
            if (svcMachUsgStsTMsg == null) {
                this.messenger.addMessageForRecord(rowNum, NSAM0011E, "IB Usage");
                return false;
            }
        }

        // IB Trx Type
        if (hasValue(tMsg.svcMachTrxTpCd)) {
            SVC_MACH_TRX_TPTMsg svcMachTrxTpTMsg = (SVC_MACH_TRX_TPTMsg) ZYPCodeDataUtil.findByCode("SVC_MACH_TRX_TP", this.glblCmpyCd, tMsg.svcMachTrxTpCd.getValue());
            if (svcMachTrxTpTMsg == null) {
                this.messenger.addMessageForRecord(rowNum, NSAM0011E, "IB Trx Type");
                return false;
            }
        }

        // START 2017/07/07 K.Kojima [QC#19660,ADD]
        // Serviced by UB
        if (!MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            // START 2023/10/03 S.Naya [QC#54186,MOD]
            //if (hasValue(tMsg.svcByLineBizTpCd)) {
            //    if (!existsSvcByLineBizTpCd(tMsg.svcByLineBizTpCd.getValue())) {
            //        this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Serviced By BU");
            //        return false;
            //    }
            //}
            // To Be Installed By
            if (hasValue(tMsg.svcPrvdPtyDescTxt_01)) {
                if (!existsSvcPrvdPtyDescTxt(tMsg.svcPrvdPtyDescTxt_01.getValue())) {
                    this.messenger.addMessageForRecord(rowNum, NSAM0011E, "To Be Installed By");
                    return false;
                }
            }
            // Service Provided By
            if (hasValue(tMsg.svcPrvdPtyDescTxt_02)) {
                if (!existsSvcPrvdPtyDescTxt(tMsg.svcPrvdPtyDescTxt_02.getValue())) {
                    this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Service Provided By");
                    return false;
                }
            }
            // END   2023/10/03 S.Naya [QC#54186,MOD]
        }
        // END 2017/07/07 K.Kojima [QC#19660,ADD]
        // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
        if (hasValue(tMsg.sldByLineBizTpCd)) {
            LINE_BIZ_TPTMsg lineBizTpTMsg = (LINE_BIZ_TPTMsg) ZYPCodeDataUtil.findByCode("LINE_BIZ_TP", this.glblCmpyCd, tMsg.sldByLineBizTpCd.getValue());
            if (lineBizTpTMsg == null) {
                this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Sold By BU");
                return false;
            }
        }

        if (hasValue(tMsg.reqTechCd)) {
            if (!this.updateReqTechCdToBlank.equals(tMsg.reqTechCd.getValue())) {
                TECH_MSTRTMsg techMstrTmsgMsg = new TECH_MSTRTMsg();
                setValue(techMstrTmsgMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(techMstrTmsgMsg.techTocCd, tMsg.reqTechCd.getValue());
                techMstrTmsgMsg = (TECH_MSTRTMsg)  EZDTBLAccessor.findByKey(techMstrTmsgMsg);
                if (techMstrTmsgMsg == null) {
                    this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Requested Tech");
                    return false;
                }
            }
        }
        // END 2023/10/06 Y.Nagasawa [QC#60082,ADD]

        return true;
    }

    private boolean validationMdseMster(SVC_MACH_UPLD_WRKTMsg tMsg) {
        BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
        MDSETMsg mdse = getMdse(tMsg.mdseCd.getValue());
        if (mdse == null) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Item Code");
            return false;
        }

        MDSETMsg dsMdse = getDsMdseInfo(tMsg.mdseCd.getValue());
        if (dsMdse == null) {
            this.messenger.addMessageForRecord(rowNum, NSAM0011E, "Item Code");
            return false;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(dsMdse.instlBaseCtrlFlg.getValue())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0399E, null);
            return false;
        }

        // START 2018/04/03 K.Kitachi [QC#17300, MOD]
//        if (!ZYPConstant.FLG_ON_Y.equals(mdse.invtyCtrlFlg.getValue())) {
        if (ZYPConstant.FLG_OFF_N.equals(mdse.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.rcvSerTakeFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.shpgSerTakeFlg.getValue())) {
        // END 2018/04/03 K.Kitachi [QC#17300, MOD]
            if (hasValue(tMsg.serNum)) {
                this.messenger.addMessageForRecord(rowNum, NSAM0473E, null);
                return false;
            }
            if (hasValue(tMsg.serNum)) {
                this.messenger.addMessageForRecord(rowNum, NSAM0473E, null);
                return false;
            }
        }

        // START 2018/04/03 K.Kitachi [QC#17300, MOD]
//        if (!ZYPConstant.FLG_ON_Y.equals(mdse.rcvSerTakeFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(mdse.shpgSerTakeFlg.getValue())) {
        if (ZYPConstant.FLG_ON_Y.equals(mdse.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.rcvSerTakeFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.shpgSerTakeFlg.getValue())) {
        // END 2018/04/03 K.Kitachi [QC#17300, MOD]
            if (hasValue(tMsg.serNum)) {
                this.messenger.addMessageForRecord(rowNum, NSAM0451E, null);
                return false;
            }
            if (hasValue(tMsg.serNum)) {
                this.messenger.addMessageForRecord(rowNum, NSAM0451E, null);
                return false;
            }
        }
        return true;
    }

    private boolean validationReln(SVC_MACH_UPLD_WRKTMsg tMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(tMsg.svcMachMstrPk.getValue(), tMsg.serNum.getValue());
        // Create mode only existsSerNum
        if (isErrExistsSerNum(tMsg)) {
            return false;
        }
        // Account
        if (isErrAccountReln(tMsg, svcMachMstrTMsg)) {
            return false;
        }
        // ActiveContr
        if (isErrActiveContr(tMsg, svcMachMstrTMsg)) {
            return false;
        }
        // OpenOrder
        if (isErrOpenOrder(tMsg, svcMachMstrTMsg)) {
            return false;
        }
        // StatusRelation
        if (isErrStatusRelation(tMsg)) {
            return false;
        }
        // RtlSWH
        if (isErrRtlSWH(tMsg)) {
            return false;
        }
        // START 2018/05/28 K.Kitachi [QC#19932, ADD]
        // WH
        if (isErrWH(tMsg, svcMachMstrTMsg)) {
            return false;
        }
        // END 2018/05/28 K.Kitachi [QC#19932, ADD]
        return true;
    }

    private boolean isErrExistsSerNum(SVC_MACH_UPLD_WRKTMsg tMsg) {
        if (!MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
            return false;
        }

        if (!hasValue(tMsg.serNum)) {
            return false;
        }

        // START 2018/06/25 M.Naito [QC#20733, MOD]
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
//        inMsg.setSQLID("002");
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("serNum01", tMsg.serNum.getValue());
        inMsg.setConditionValue("mdseCd01", tMsg.mdseCd.getValue());
        // END 2018/06/25 M.Naito [QC#20733, MOD]
        SVC_MACH_MSTRTMsgArray rtnArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (rtnArray.getValidCount() > 0) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0059E, "Serial#");
            return true;
        }
        return false;
    }

    private boolean isErrAccountReln(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        String ownrAcctNum = tMsg.ownrAcctNum.getValue();
        String billToAcctNum = tMsg.billToAcctNum.getValue();
        String curLocAcctNum = tMsg.curLocAcctNum.getValue();
        String billToLocNum = tMsg.indBillToLocNum.getValue();
        String curLocNum = tMsg.indCurLocNum.getValue();
        if (MODE_UPDATE.equals(tMsg.procModeCd.getValue())) {
            if (!isChangeLoc(tMsg)) {
                return false;
            }

            if (tMsg.svcMachMstrStsCd.getValue().equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
                ownrAcctNum = getValue(tMsg.ownrAcctNum.getValue(), svcMachMstrTMsg.ownrAcctNum.getValue());
                billToAcctNum = getValue(tMsg.billToAcctNum.getValue(), svcMachMstrTMsg.billToAcctNum.getValue());
                curLocAcctNum = getValue(tMsg.curLocAcctNum.getValue(), svcMachMstrTMsg.curLocAcctNum.getValue());
                billToLocNum = getValue(tMsg.indBillToLocNum.getValue(), svcMachMstrTMsg.indBillToLocNum.getValue());
                curLocNum = getValue(tMsg.indCurLocNum.getValue(), svcMachMstrTMsg.indCurLocNum.getValue());
            }
        } else if (MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            return false;
        }
        // add start 2019/08/07 QC#52198
        String svcMachTrxTpCd = tMsg.svcMachTrxTpCd.getValue();
        // add end 2019/08/07 QC#52198

        BigDecimal rowNum = tMsg.upldCsvRqstRowNum.getValue();
        if (hasValue(billToAcctNum) && hasValue(billToLocNum) && isErrAcctBillEligible(billToAcctNum, billToLocNum)) {
            this.messenger.addMessageForRecord(rowNum, NSAM0546E, null);
            return true;
        }
        if (hasValue(curLocAcctNum) && hasValue(curLocNum) && isErrAcctShipEligible(curLocAcctNum, curLocNum)) {
            this.messenger.addMessageForRecord(rowNum, NSAM0547E, null);
            return true;
        }
        if (hasValue(billToAcctNum) && hasValue(curLocNum) && isErrAcctShipEligible(billToAcctNum, curLocNum)) {
            this.messenger.addMessageForRecord(rowNum, NSAM0548E, null);
            return true;
        }
        // mod start 2019/08/07 QC#52198
        //if (hasValue(ownrAcctNum) && hasValue(billToAcctNum) && isErrAcctOwnrBillTo(ownrAcctNum, billToAcctNum)) {
        if (hasValue(ownrAcctNum) && hasValue(billToAcctNum) && isErrAcctOwnrBillTo(ownrAcctNum, billToAcctNum, svcMachTrxTpCd)) {
        // mod end 2019/08/07 QC#52198
            this.messenger.addMessageForRecord(rowNum, NSAM0549E, null);
            return true;
        }
        return false;
    }

    private boolean isErrAcctBillEligible(String dsAcctNum, String locNum) {
        String billToCustCd = getBillToCustCd(locNum);
        if (!hasValue(billToCustCd)) {
            return true;
        }

        // START 2017/02/15 N.Arai [QC#17301, MOD]
        String keyStr = createMapKey(dsAcctNum, billToCustCd);
        if (this.billEligibleMap.containsKey(keyStr)) {
            return (Boolean) this.billEligibleMap.get(keyStr);
        }
        // END 2017/02/15 N.Arai [QC#17301, MOD]

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        setValue(apiMsg.dsAcctNum_I2, dsAcctNum);
        setValue(apiMsg.billToCustCd, billToCustCd);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.billEligibleMap.put(keyStr, true);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnBillToFlg_B.getValue())) {
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.billEligibleMap.put(keyStr, false);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return false;
        }
        // START 2017/02/15 N.Arai [QC#17301, ADD]
        this.billEligibleMap.put(keyStr, true);
        // END 2017/02/15 N.Arai [QC#17301, ADD]
        return true;
    }

    private boolean  isErrAcctShipEligible(String dsAcctNum, String locNum) {
        String shipToCustCd = getShipToCustCd(locNum);
        if (!hasValue(shipToCustCd)) {
            return true;
        }

        // START 2017/02/15 N.Arai [QC#17301, MOD]
        String keyStr = createMapKey(dsAcctNum, shipToCustCd);
        if (this.shipEligibleMap.containsKey(keyStr)) {
            return (Boolean) this.shipEligibleMap.get(keyStr);
        }
        // END 2017/02/15 N.Arai [QC#17301, MOD]

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        setValue(apiMsg.dsAcctNum_I2, dsAcctNum);
        setValue(apiMsg.shipToCustCd, shipToCustCd);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.shipEligibleMap.put(keyStr, true);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnShipToFlg_S.getValue())) {
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.shipEligibleMap.put(keyStr, false);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return false;
        }
        // START 2017/02/15 N.Arai [QC#17301, ADD]
        this.shipEligibleMap.put(keyStr, true);
        // END 2017/02/15 N.Arai [QC#17301, ADD]
        return true;
    }

    // mod start 2019/08/07 QC#52198
    //private boolean isErrAcctOwnrBillTo(String ownrAcctNum, String billToAcctNum) {
    private boolean isErrAcctOwnrBillTo(String ownrAcctNum, String billToAcctNum, String svcMachTrxTpCd) {
    // mod end 2019/08/07 QC#52198
        // START 2016/10/14 T.Tomita [QC#14734, DEL]
//        String ownrLocNum = getOwnrLocNum(ownrAcctNum);
//        if (!hasValue(ownrLocNum)) {
//            return true;
//        }
        // END 2016/10/14 T.Tomita [QC#14734, DEL]

        // add start 2019/08/07 QC#52198
        if (this.ownrRelnCheckExclTrxTpCdList != null) {
            for (String val : this.ownrRelnCheckExclTrxTpCdList) {
                if (val.equals(svcMachTrxTpCd)) {
                    return false;
                }
            }
        }
        if (ZYPCommonFunc.hasValue(this.ownrRelnCheckExclAcctNum) && this.ownrRelnCheckExclAcctNum.equals(ownrAcctNum)) {
            return false;
        }
        // add end 2019/08/07 QC#52198

        // START 2017/02/15 N.Arai [QC#17301, MOD]
        String keyStr = createMapKey(ownrAcctNum, billToAcctNum);
        if (this.ownrBillToMap.containsKey(keyStr)) {
            return (Boolean) this.ownrBillToMap.get(keyStr);
        }
        // END 2017/02/15 N.Arai [QC#17301, MOD]

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        // START 2016/10/14 T.Tomita [QC#14734, MOD]
        setValue(apiMsg.dsAcctNum_I1, ownrAcctNum);
        setValue(apiMsg.dsAcctNum_I2, billToAcctNum);
        // END 2016/10/14 T.Tomita [QC#14734, MOD]

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.ownrBillToMap.put(keyStr, true);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return true;
        }

        // START 2016/10/14 T.Tomita [QC#14734, MOD]
        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnRecipFlg.getValue())) {
        // END 2016/10/14 T.Tomita [QC#14734, MOD]
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.ownrBillToMap.put(keyStr, false);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return false;
        }
        // START 2017/02/15 N.Arai [QC#17301, ADD]
        this.ownrBillToMap.put(keyStr, true);
        // END 2017/02/15 N.Arai [QC#17301, ADD]
        return true;
    }

    // START 2016/10/14 T.Tomita [QC#14734, DEL]
//    private String getOwnrLocNum(String ownrAcctNum) {
//        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
//        setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_RELATED_BILL_SHIP);
//        setValue(apiMsg.dsAcctNum_I1, ownrAcctNum);
//        setValue(apiMsg.xxChildRelnFlg, ZYPConstant.FLG_OFF_N);
//
//        NMZC610001 api = new NMZC610001();
//        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
//        if (S21ApiUtil.isXxMsgId(apiMsg)) {
//            return null;
//        }
//
//        if (apiMsg.RelatedBillShipList.getValidCount() == 0) {
//            return null;
//        }
//        String rtnBillToCustCd = null;
//        for (int i = 0; i < apiMsg.RelatedBillShipList.getValidCount(); i++) {
//            if (!hasValue(apiMsg.RelatedBillShipList.no(i).billToCustCd)) {
//                continue;
//            }
//            rtnBillToCustCd = apiMsg.RelatedBillShipList.no(i).billToCustCd.getValue();
//            break;
//        }
//        return rtnBillToCustCd;
//    }
    // END 2016/10/14 T.Tomita [QC#14734, DEL]

    private boolean isErrActiveContr(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
            return false;
        }

        String svcMachMstrStsCd = tMsg.svcMachMstrStsCd.getValue();
        if (MODE_UPDATE.equals(tMsg.procModeCd.getValue()) && !hasValue(svcMachMstrStsCd)) {
            svcMachMstrStsCd = svcMachMstrTMsg.svcMachMstrStsCd.getValue();
        } else if (MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            svcMachMstrStsCd = SVC_MACH_MSTR_STS.TERMINATED;
        }

        if (svcMachMstrTMsg.svcMachMstrStsCd.getValue().equals(svcMachMstrStsCd)) {
            return false;
        }

        // START 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]
        // DS_CONTR_DTLTMsg contrDtl = NSXC001001GetContr.getContrDtlByMachMstrPk(this.glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), this.salesDate);
        // if (contrDtl == null) {
        //     return false;
        // }
        List<DS_CONTR_DTLTMsg> contrDtlList = NSXC001001GetContr.getContrDtlByMachMstrPkList(this.glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), this.salesDate);
        if (contrDtlList.size() == 0) {
            return false;
        }
        // END 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]

        // START 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]
        for (DS_CONTR_DTLTMsg contrDtl:contrDtlList) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrDtlPk", contrDtl.dsContrDtlPk.getValue());
            Map<String, Object> resultMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getDsContrDtlCtrlSts", param);
            if (resultMap != null) {
                String dsContrDtlCtrlSts = (String) resultMap.get("DS_CONTR_CTRL_STS_CD");
                String dsContrNum = (String) resultMap.get("DS_CONTR_NUM");
                if (DS_CONTR_CTRL_STS.ACTIVE.equals(dsContrDtlCtrlSts)) {
                    // START 2018/01/18 M.Kidokoro [QC#21962, MOD]
//                  this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0381E, "Contract#:" + dsContrNum);
//                  return true;
                    if (!SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd)
                            && !SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)
                            && !SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                        this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0381E, "Contract#:" + dsContrNum);
                        return true;
                    }
                    // END 2018/01/18 M.Kidokoro [QC#21962, MOD]
                }
            }
        }
        // END 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]
        return false;
    }

    private boolean isErrOpenOrder(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
            return false;
        }

        String svcMachMstrStsCd = tMsg.svcMachMstrStsCd.getValue();
        if (MODE_UPDATE.equals(tMsg.procModeCd.getValue()) && !hasValue(svcMachMstrStsCd)) {
            svcMachMstrStsCd = svcMachMstrTMsg.svcMachMstrStsCd.getValue();
        } else if (MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            svcMachMstrStsCd = SVC_MACH_MSTR_STS.TERMINATED;
        }

        if (svcMachMstrTMsg.svcMachMstrStsCd.getValue().equals(svcMachMstrStsCd)) {
            return false;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrTMsg.svcMachMstrPk.getValue());
        param.put("closed", ORD_LINE_STS.CLOSED);
        param.put("cancelled", ORD_LINE_STS.CANCELLED);
        String openCpoOrdNum = (String) this.ssmBatchClient.queryObject("getOpenOrder", param);
        if (hasValue(openCpoOrdNum)) {
            // START 2018/01/18 M.Kidokoro [QC#21962, MOD]
//            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0382E, "Order#:" + openCpoOrdNum);
//            return true;
            String svcIstlReqFlg = (String) this.ssmBatchClient.queryObject("getSvcIstlReqFlg", param);

            // mod start 2019/10/16 QC#53823
            //if (ZYPCommonFunc.hasValue(svcIstlReqFlg) && ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg)) {
            //    this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0382E, "Order#:" + openCpoOrdNum);
            //    return true;
            //}
            //
            //if (!chkStsForOpenOrd(svcMachMstrStsCd)) {
            //    this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0382E, "Order#:" + openCpoOrdNum);
            //    return true;
            //}
            if (ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg)) {
                if (!chkStsForOpenOrd(svcMachMstrStsCd)) {
                    this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0382E, "Order#:" + openCpoOrdNum);
                    return true;
                }
            }
            // mod end 2019/10/16 QC#53823
            // END 2018/01/18 M.Kidokoro [QC#21962, MOD]
        }
        return false;
    }

    private boolean isErrStatusRelation(SVC_MACH_UPLD_WRKTMsg tMsg) {
        String svcMachStsCd = tMsg.svcMachMstrStsCd.getValue();
        String svcMachUsgStsCd = tMsg.svcMachUsgStsCd.getValue();
        if (!hasValue(svcMachStsCd) || !hasValue(svcMachUsgStsCd)) {
            return false;
        }

        MACH_STS_VLD_MAPTMsg inMsg = new MACH_STS_VLD_MAPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrStsCd01", svcMachStsCd);
        inMsg.setConditionValue("svcMachUsgStsCd01", svcMachUsgStsCd);
        MACH_STS_VLD_MAPTMsgArray array = (MACH_STS_VLD_MAPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0 || ZYPConstant.FLG_OFF_N.equals(array.no(0).stsVldFlg.getValue())) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSZM0869E, null);
            return true;
        }
        return false;
    }

    private boolean isErrRtlSWH(SVC_MACH_UPLD_WRKTMsg tMsg) {
        String svcMachMstrStsCd = tMsg.svcMachMstrStsCd.getValue();
        String curLocNum = tMsg.indCurLocNum.getValue();
        // START 2018/01/22 M.Kidokoro [QC#21975, ADD]
        String svcMachUsgStsCd = tMsg.svcMachUsgStsCd.getValue();
        // END 2018/01/22 M.Kidokoro [QC#21975, ADD]

        if (!hasValue(svcMachMstrStsCd) || !hasValue(curLocNum)) {
            return false;
        }

        List<String> list = new ArrayList<String>();
        list.add(SVC_MACH_MSTR_STS.CREATED);
        list.add(SVC_MACH_MSTR_STS.REMOVED);
        list.add(SVC_MACH_MSTR_STS.DUPLICATE);
        // START 2018/01/22 M.Kidokoro [QC#21975, ADD]
        list.add(SVC_MACH_MSTR_STS.TERMINATED);
        // END 2018/01/22 M.Kidokoro [QC#21975, ADD]

        if (!list.contains(svcMachMstrStsCd)) {
            return false;
        }

        // START 2018/01/22 M.Kidokoro [QC#21975, ADD]
        if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd) && SVC_MACH_USG_STS.GONE.equals(svcMachUsgStsCd)) {
            return false;
        }
        // END 2018/01/22 M.Kidokoro [QC#21975, ADD]

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("invtyLocCd", curLocNum);
        param.put("slsDt", this.salesDate);
        param.put("endDt", "29991231");

        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countRtlSWH", param);
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0011E, "Current Loc#");
            return true;
        }
        return false;
    }

    private boolean isErrMainMach(SVC_MACH_MSTRTMsg mainMachTMsg, List<BigDecimal> rowNumList) {
        if (!hasValue(mainMachTMsg.svcConfigMstrPk)) {
            return false;
        }

        SVC_CONFIG_MSTRTMsg inMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcConfigMstrPk, mainMachTMsg.svcConfigMstrPk);
        SVC_CONFIG_MSTRTMsg rtnMsg = (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (rtnMsg == null) {
            setConfigErrMsg(rowNumList, NSAM0011E, "Main Machine Config PK:" + mainMachTMsg.svcConfigMstrPk.getValue());
            return true;
        }
        if (!mainMachTMsg.svcMachMstrPk.getValue().equals(rtnMsg.svcMachMstrPk.getValue())) {
            setConfigErrMsg(rowNumList, NSAM0550E, "Serial#:" + mainMachTMsg.serNum.getValue() + ", IB ID:" + mainMachTMsg.svcMachMstrPk.getValue());
            return true;
        }
        return false;
    }

    private boolean isErrConfigMach(SVC_MACH_MSTRTMsg mainMachTMsg, SVC_MACH_MSTRTMsg configMachTMsg, List<BigDecimal> rowNumList) {
        if (!hasValue(configMachTMsg.svcConfigMstrPk)) {
            return false;
        }

        SVC_CONFIG_MSTRTMsg inMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcConfigMstrPk, configMachTMsg.svcConfigMstrPk);
        SVC_CONFIG_MSTRTMsg rtnMsg = (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (rtnMsg == null) {
            setConfigErrMsg(rowNumList, NSAM0011E, "Config Machine Config PK:" + configMachTMsg.svcConfigMstrPk.getValue());
            return true;
        }
        if (!mainMachTMsg.svcMachMstrPk.getValue().equals(rtnMsg.svcMachMstrPk.getValue())) {
            setConfigErrMsg(rowNumList, NSAM0550E, "Serial#:" + configMachTMsg.serNum.getValue() + ", IB ID:" + configMachTMsg.svcMachMstrPk.getValue());
            return true;
        }
        return false;
    }

    private boolean isErrRmvDt(String svcMachRmvDt, List<BigDecimal> rowNumList) {
        if (hasValue(svcMachRmvDt) && ZYPDateUtil.compare(this.salesDate, svcMachRmvDt) < 0) {
            setConfigErrMsg(rowNumList, NSAM0040E, "Remove Date");
            return true;
        }
        return false;
    }

    private MDSETMsg getMdse(String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        // return (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
        return (MDSETMsg) S21CacheTBLAccessor.findByKey(inMsg);
        // END 2017/02/15 N.Arai [QC#17301, MOD]
    }

    private MDSETMsg getDsMdseInfo(String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        return (MDSETMsg) S21CacheTBLAccessor.findByKey(inMsg);
        // END 2017/02/15 N.Arai [QC#17301, MOD]
    }

    private boolean existsSellToCust(String dsAcctNum) {
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        if (this.sellToCustMap.containsKey(dsAcctNum)) {
            if ((Integer)this.sellToCustMap.get(dsAcctNum) == 0) {
                return false;
            }
            return true;
        }
        // END 2017/02/15 N.Arai [QC#17301, MOD]
        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        int rsCount = EZDTBLAccessor.count(inMsg);
        this.sellToCustMap.put(dsAcctNum, rsCount);
        // if (EZDTBLAccessor.count(inMsg) == 0) {
        if (rsCount == 0) {
        // END 2017/02/15 N.Arai [QC#17301, MOD]
            return false;
        }
        return true;
    }

    private boolean existsBillToCust(String locNum) {
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        if (this.billToCustMap.containsKey(locNum)) {
            if ((Integer)this.billToCustMap.get(locNum) == 0) {
                return false;
            }
            return true;
        }
        // END 2017/02/15 N.Arai [QC#17301, MOD]
        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        inMsg.setSQLID("060");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("locNum01", locNum);
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        int rsCount = EZDTBLAccessor.count(inMsg);
        this.billToCustMap.put(locNum, rsCount);
        // if (EZDTBLAccessor.count(inMsg) == 0) {
        if (rsCount == 0) {
        // END 2017/02/15 N.Arai [QC#17301, MOD]
            return false;
        }
        return true;
    }

    private boolean existsShipToCust(String locNum) {
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        if (this.shipToCustMap.containsKey(locNum)) {
            if ((Integer)this.shipToCustMap.get(locNum) == 0) {
                return false;
            }
            return true;
        }
        // END 2017/02/15 N.Arai [QC#17301, MOD]
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("048");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("locNum01", locNum);
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        int rsCount = EZDTBLAccessor.count(inMsg);
        this.shipToCustMap.put(locNum, rsCount);
        // if (EZDTBLAccessor.count(inMsg) == 0) {
        if (rsCount == 0) {
        // END 2017/02/15 N.Arai [QC#17301, MOD]
            return false;
        }
        return true;
    }

    // START 2017/07/07 K.Kojima [QC#19660,ADD]
    private boolean existsSvcByLineBizTpCd(String svcByLineBizTpCd) {
        if (this.svcByLineBizTpCdMap.containsKey(svcByLineBizTpCd)) {
            if ((Integer)this.svcByLineBizTpCdMap.get(svcByLineBizTpCd) == 0) {
                return false;
            }
            return true;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("lineBizTpCd", svcByLineBizTpCd);
        paramMap.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countSvcLineBiz", paramMap);
        this.svcByLineBizTpCdMap.put(svcByLineBizTpCd, count.intValue());
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END 2017/07/07 K.Kojima [QC#19660,ADD]
    // START 2023/10/03 S.Naya [QC#54186,ADD]
    private boolean existsSvcPrvdPtyCd(String svcPrvdPtyCd) {
        if (this.svcPrvdPtyCdMap.containsKey(svcPrvdPtyCd)) {
            if ((Integer)this.svcPrvdPtyCdMap.get(svcPrvdPtyCd) == 0) {
                return false;
            }
            return true;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcPrvdPtyCd", svcPrvdPtyCd);
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countSvcPrvdPty", paramMap);
        this.svcPrvdPtyCdMap.put(svcPrvdPtyCd, count.intValue());
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }

    private boolean existsSvcPrvdPtyDescTxt(String svcPrvdPtyDescTxt) {
        if (this.svcPrvdPtyDescTxtMap.containsKey(svcPrvdPtyDescTxt)) {
            if ((Integer)this.svcPrvdPtyDescTxtMap.get(svcPrvdPtyDescTxt) == 0) {
                return false;
            }
            return true;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcPrvdPtyDescTxt", svcPrvdPtyDescTxt);
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countSvcPrvdPty", paramMap);
        this.svcPrvdPtyDescTxtMap.put(svcPrvdPtyDescTxt, count.intValue());
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END   2023/10/03 S.Naya [QC#54186,ADD]

    private boolean isErrForMandatory(EZDTItem chkItem, BigDecimal rowNum, String itemNm) {
        if (!hasValue(chkItem)) {
            this.messenger.addMessageForRecord(rowNum, ZZM9000E, itemNm);
            return true;
        }
        return false;
    }

    private NSZC001001PMsg setApiPMsg(SVC_MACH_UPLD_WRKTMsg tMsg) {
        NSZC001001PMsg pMsg = null;
        if (MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
            // Create Mode
            pMsg = setInsertApiPMsg(tMsg);
        // Add Start 2018/06/14 QC#23428
        } else if (MODE_UPDATE.equals(tMsg.procModeCd.getValue()) && isSvcExch(tMsg)) {
            // Service Exchange
            pMsg = setExchApiPMsg(tMsg);
        // Add End 2018/06/14 QC#23428
        } else {
            // Update or Terminate Mode
            pMsg = setUpdateApiPMsg(tMsg);
        }
        return pMsg;
    }

    private NSZC001001PMsg setInsertApiPMsg(SVC_MACH_UPLD_WRKTMsg tMsg) {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        // START 2016/10/14 T.Tomita [QC#14734, MOD]
        if (SVC_MACH_MSTR_STS.CREATED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.REMOVED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.TERMINATED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.DUPLICATE.equals(tMsg.svcMachMstrStsCd.getValue())) {
        // END 2016/10/14 T.Tomita [QC#14734, MOD]
            setValue(pMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
        // START 2016/10/14 T.Tomita [QC#14734, MOD]
        } else if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.INSTALLED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(tMsg.svcMachMstrStsCd.getValue())) {
        // END 2016/10/14 T.Tomita [QC#14734, MOD]
            setValue(pMsg.xxModeCd, ProcessMode.INSERT_MACHINE_IN_FIELD.code);
        }

        setValue(pMsg.serNum, tMsg.serNum);
        setValue(pMsg.mdseCd, tMsg.mdseCd);
        setValue(pMsg.custMachCtrlNum, tMsg.custMachCtrlNum);
        setValue(pMsg.svcMachMstrStsCd, tMsg.svcMachMstrStsCd);
        setValue(pMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
        setValue(pMsg.istlDt, tMsg.istlDt);
        setValue(pMsg.effFromDt, tMsg.effFromDt);
        // START 2016/10/14 T.Tomita [QC#14734, ADD]
        setValue(pMsg.effThruDt, tMsg.effThruDt);
        // END 2016/10/14 T.Tomita [QC#14734, ADD]
        setValue(pMsg.svcMachUsgStsCd, tMsg.svcMachUsgStsCd);
        setValue(pMsg.svcMachTrxTpCd, tMsg.svcMachTrxTpCd);
        setValue(pMsg.svcMachQty, BigDecimal.ONE);
        setValue(pMsg.ownrAcctNum, tMsg.ownrAcctNum);
        setValue(pMsg.billToAcctNum, tMsg.billToAcctNum);
        setValue(pMsg.billToLocNum, getBillToCustCd(tMsg.indBillToLocNum.getValue()));
        setValue(pMsg.curLocAcctNum, tMsg.curLocAcctNum);
        setValue(pMsg.curLocNum, getShipToCustCd(tMsg.indCurLocNum.getValue()));
        // START 2017/02/14 K.Kitachi [QC#17309, ADD]
        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/02/14 K.Kitachi [QC#17309, ADD]
        // START 2017/07/07 K.Kojima [QC#19660,ADD]
        setValue(pMsg.svcByLineBizTpCd, tMsg.svcByLineBizTpCd);
        // END 2017/07/07 K.Kojima [QC#19660,ADD]
        // START 2023/10/03 S.Naya [QC#54186,ADD]
        setValue(pMsg.istlBySvcPrvdPtyCd, convertSvcPrvdPtyDescTxtToCd(tMsg.svcPrvdPtyDescTxt_01.getValue()));
        setValue(pMsg.svcBySvcPrvdPtyCd, convertSvcPrvdPtyDescTxtToCd(tMsg.svcPrvdPtyDescTxt_02.getValue()));
        // END   2023/10/03 S.Naya [QC#54186,ADD]
        // START 2023/07/31 S.Moriai [QC#61632, ADD]
        setValue(pMsg.swLicId, tMsg.swLicId);
        // END 2023/07/31 S.Moriai [QC#61632, ADD]
        setDefaultValue(pMsg, tMsg.svcMachMstrStsCd.getValue(), tMsg.svcMachTrxTpCd.getValue());

        pMsg.xxCmptMachList.setValidCount(0);

        return pMsg;
    }

    private NSZC001001PMsg setUpdateApiPMsg(SVC_MACH_UPLD_WRKTMsg tMsg) {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(tMsg.svcMachMstrPk.getValue(), tMsg.serNum.getValue());

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        // START 2023/09/22 T.Kuroda [QC#61265, ADD]
        setValue(pMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd);
        // END   2022/09/22 T.Kuroda [QC#61265, ADD]
        if (SVC_MACH_MSTR_STS.CREATED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.REMOVED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.DUPLICATE.equals(tMsg.svcMachMstrStsCd.getValue())) {
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
        // START 2016/10/14 T.Tomita [QC#14734, MOD]
        } else if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.INSTALLED.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(tMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(tMsg.svcMachMstrStsCd.getValue())) {
        // END 2016/10/14 T.Tomita [QC#14734, MOD]
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_MACHINE_IN_FIELD.code);
        } else if (SVC_MACH_MSTR_STS.TERMINATED.equals(tMsg.svcMachMstrStsCd.getValue())) {
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_TERMINATION.code);
            // START 2023/09/22 T.Kuroda [QC#61265, ADD]
            setValue(pMsg.svcMachMstrLocStsCd, LOC_STS_BLANK);
            // END   2022/09/22 T.Kuroda [QC#61265, ADD]
            // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
            setValue(pMsg.xxTrmnFlg, ZYPConstant.FLG_ON_Y);
            // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
        }

        setValue(pMsg.svcConfigMstrPk, tMsg.svcConfigMstrPk);
        setValue(pMsg.svcMachMstrPk, tMsg.svcMachMstrPk);
        setValue(pMsg.serNum, tMsg.serNum);
        setValue(pMsg.mdseCd, tMsg.mdseCd);
        setValue(pMsg.custMachCtrlNum, tMsg.custMachCtrlNum);
        setValue(pMsg.svcMachMstrStsCd, tMsg.svcMachMstrStsCd);
        setValue(pMsg.stkStsCd, svcMachMstrTMsg.stkStsCd);
        // START 2023/09/22 T.Kuroda [QC#61265, DEL]
        //setValue(pMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd);
        // END   2022/09/22 T.Kuroda [QC#61265, DEL]
        setValue(pMsg.oldSerNum, svcMachMstrTMsg.serNum);
        setValue(pMsg.istlDt, tMsg.istlDt);
        setValue(pMsg.effFromDt, tMsg.effFromDt);
        setValue(pMsg.effThruDt, tMsg.effThruDt);
        setValue(pMsg.svcMachRmvDt, tMsg.svcMachRmvDt);
        setValue(pMsg.svcMachUsgStsCd, tMsg.svcMachUsgStsCd);
        setValue(pMsg.prntSerNum, tMsg.prntSerNum);
        setValue(pMsg.svcMachTrxTpCd, tMsg.svcMachTrxTpCd);
        setValue(pMsg.svcMachQty, svcMachMstrTMsg.svcMachQty);
        setValue(pMsg.svcMachTpCd, svcMachMstrTMsg.svcMachTpCd);

        String billToLocNum = getBillToCustCd(tMsg.indBillToLocNum.getValue());
        String curLocNum = getShipToCustCd(tMsg.indCurLocNum.getValue());
        setValue(pMsg.ownrAcctNum, tMsg.ownrAcctNum);
        setValue(pMsg.billToAcctNum, tMsg.billToAcctNum);
        setValue(pMsg.billToLocNum, billToLocNum);
        setValue(pMsg.curLocAcctNum, tMsg.curLocAcctNum);
        setValue(pMsg.curLocNum, curLocNum);
        // START 2017/02/14 K.Kitachi [QC#17309, ADD]
        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/02/14 K.Kitachi [QC#17309, ADD]
        // START 2017/07/07 K.Kojima [QC#19660,ADD]
        setValue(pMsg.svcByLineBizTpCd, tMsg.svcByLineBizTpCd);
        // END 2017/07/07 K.Kojima [QC#19660,ADD]
        // START 2023/10/03 S.Naya [QC#54186,ADD]
        setValue(pMsg.istlBySvcPrvdPtyCd, convertSvcPrvdPtyDescTxtToCd(tMsg.svcPrvdPtyDescTxt_01.getValue()));
        setValue(pMsg.svcBySvcPrvdPtyCd, convertSvcPrvdPtyDescTxtToCd(tMsg.svcPrvdPtyDescTxt_02.getValue()));
        // END   2023/10/03 S.Naya [QC#54186,ADD]
        // START 2023/07/31 S.Moriai [QC#61632, ADD]
        setValue(pMsg.swLicId, tMsg.swLicId);
        // END 2023/07/31 S.Moriai [QC#61632, ADD]
        pMsg.xxCmptMachList.setValidCount(0);
        return pMsg;
    }

    private NSZC001001PMsg setApiPMsgForConfig(SVC_MACH_MSTRTMsg mainMachTMsg, Map<BigDecimal, String> configMap) {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();

        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        // START 2023/09/22 T.Kuroda [QC#61265, ADD]
        setValue(pMsg.svcMachMstrLocStsCd, mainMachTMsg.svcMachMstrLocStsCd);
        // END   2022/09/22 T.Kuroda [QC#61265, ADD]
        if (SVC_MACH_MSTR_STS.CREATED.equals(mainMachTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.REMOVED.equals(mainMachTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.DUPLICATE.equals(mainMachTMsg.svcMachMstrStsCd.getValue())) {
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
        } else if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(mainMachTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.INSTALLED.equals(mainMachTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(mainMachTMsg.svcMachMstrStsCd.getValue())) {
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_MACHINE_IN_FIELD.code);
        } else if (SVC_MACH_MSTR_STS.TERMINATED.equals(mainMachTMsg.svcMachMstrStsCd.getValue())) {
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_TERMINATION.code);
            // START 2023/09/22 T.Kuroda [QC#61265, ADD]
            setValue(pMsg.svcMachMstrLocStsCd, LOC_STS_BLANK);
            // END   2022/09/22 T.Kuroda [QC#61265, ADD]
        }
        setValue(pMsg.svcConfigMstrPk, mainMachTMsg.svcConfigMstrPk);
        setValue(pMsg.svcMachMstrPk, mainMachTMsg.svcMachMstrPk);
        setValue(pMsg.serNum, mainMachTMsg.serNum);
        setValue(pMsg.mdseCd, mainMachTMsg.mdseCd);
        setValue(pMsg.custMachCtrlNum, mainMachTMsg.custMachCtrlNum);
        setValue(pMsg.svcMachMstrStsCd, mainMachTMsg.svcMachMstrStsCd);
        setValue(pMsg.stkStsCd, mainMachTMsg.stkStsCd);
        // START 2023/09/22 T.Kuroda [QC#61265, DEL]
        //setValue(pMsg.svcMachMstrLocStsCd, mainMachTMsg.svcMachMstrLocStsCd);
        // END   2022/09/22 T.Kuroda [QC#61265, DEL]
        setValue(pMsg.oldSerNum, mainMachTMsg.serNum);
        setValue(pMsg.prntSvcMachMstrPk, mainMachTMsg.svcMachMstrPk);
        setValue(pMsg.istlDt, mainMachTMsg.istlDt);
        setValue(pMsg.effFromDt, mainMachTMsg.effFromDt);
        setValue(pMsg.effThruDt, mainMachTMsg.effThruDt);
        setValue(pMsg.svcMachUsgStsCd, mainMachTMsg.svcMachUsgStsCd);
        setValue(pMsg.prntSerNum, mainMachTMsg.serNum);
        setValue(pMsg.svcMachTrxTpCd, mainMachTMsg.svcMachTrxTpCd);
        setValue(pMsg.svcMachQty, mainMachTMsg.svcMachQty);
        setValue(pMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
        setValue(pMsg.ownrAcctNum, mainMachTMsg.ownrAcctNum);
        setValue(pMsg.billToAcctNum, mainMachTMsg.billToAcctNum);
        setValue(pMsg.billToLocNum, mainMachTMsg.billToLocNum);
        setValue(pMsg.curLocAcctNum, mainMachTMsg.curLocAcctNum);
        setValue(pMsg.curLocNum, mainMachTMsg.curLocNum);
        // START 2017/02/14 K.Kitachi [QC#17309, ADD]
        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/02/14 K.Kitachi [QC#17309, ADD]
        // START 2017/07/07 K.Kojima [QC#19660,ADD]
        setValue(pMsg.svcByLineBizTpCd, mainMachTMsg.svcByLineBizTpCd);
        // END 2017/07/07 K.Kojima [QC#19660,ADD]
        // START 2023/10/03 S.Naya [QC#54186,ADD]
        setValue(pMsg.istlBySvcPrvdPtyCd, mainMachTMsg.istlBySvcPrvdPtyCd);
        setValue(pMsg.svcBySvcPrvdPtyCd, mainMachTMsg.svcBySvcPrvdPtyCd);
        // END   2023/10/03 S.Naya [QC#54186,ADD]

        // Configuration
        int cmptCnt = 0;
        SVC_MACH_MSTRTMsg machTMsg;
        for (Map.Entry<BigDecimal, String> entry : configMap.entrySet()) {
            BigDecimal svcMachMstrPk = entry.getKey();
            String svcMachRmvDt = entry.getValue();
            machTMsg = getSvcMachMstr(svcMachMstrPk, null);

            setValue(pMsg.xxCmptMachList.no(cmptCnt).svcMachMstrPk, svcMachMstrPk);
            setValue(pMsg.xxCmptMachList.no(cmptCnt).mdseCd, machTMsg.mdseCd);
            setValue(pMsg.xxCmptMachList.no(cmptCnt).effThruDt, svcMachRmvDt);
            if (mainMachTMsg.svcMachMstrPk.getValue().compareTo(svcMachMstrPk) == 0) {
                setValue(pMsg.xxCmptMachList.no(cmptCnt).svcMachTpCd, SVC_MACH_TP.MACHINE);
            } else {
                setValue(pMsg.xxCmptMachList.no(cmptCnt).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
            }
            cmptCnt++;
        }
        pMsg.xxCmptMachList.setValidCount(cmptCnt);

        return pMsg;
    }

    // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
    private NSZC001001PMsg setSldByLineBizTpCdAndReqTechCdPMsg(SVC_MACH_UPLD_WRKTMsg svcMachUpldWrkTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg){
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(pMsg.prfTechCd, svcMachMstrTMsg.prfTechCd);
        setValue(pMsg.custIssPoNum, svcMachMstrTMsg.custIssPoNum);
        setValue(pMsg.ctrlFldTxt_01, svcMachMstrTMsg.ctrlFldTxt_01);
        setValue(pMsg.ctrlFldTxt_02, svcMachMstrTMsg.ctrlFldTxt_02);
        setValue(pMsg.ctrlFldTxt_03, svcMachMstrTMsg.ctrlFldTxt_03);
        setValue(pMsg.ctrlFldTxt_04, svcMachMstrTMsg.ctrlFldTxt_04);
        setValue(pMsg.ctrlFldTxt_05, svcMachMstrTMsg.ctrlFldTxt_05);
        setValue(pMsg.ctrlFldTxt_06, svcMachMstrTMsg.ctrlFldTxt_06);
        setValue(pMsg.prcContrNum, svcMachMstrTMsg.prcContrNum);
        setValue(pMsg.corpAdvtgStsCd, svcMachMstrTMsg.corpAdvtgStsCd);
        setValue(pMsg.dsPoExprDt, svcMachMstrTMsg.dsPoExprDt);
        setValue(pMsg.hardDriveRmvInclFlg, svcMachMstrTMsg.hardDriveRmvInclFlg);
        setValue(pMsg.hardDriveEraseInclFlg, svcMachMstrTMsg.hardDriveEraseInclFlg);
        setValue(pMsg.leaseRtrnFeeInclFlg, svcMachMstrTMsg.leaseRtrnFeeInclFlg);
        setValue(pMsg.fldSvcBrCd, svcMachMstrTMsg.fldSvcBrCd);
        setValue(pMsg.dsKeyAcctFlg, svcMachMstrTMsg.dsKeyAcctFlg);
        setValue(pMsg.svcNtwkConnStsCd, svcMachMstrTMsg.svcNtwkConnStsCd);
        setValue(pMsg.sldByLineBizTpCd, svcMachUpldWrkTMsg.sldByLineBizTpCd);
        setValue(pMsg.svcByLineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        setValue(pMsg.svcLicCnt, svcMachMstrTMsg.svcLicCnt);
        if (this.updateReqTechCdToBlank.equals(svcMachUpldWrkTMsg.reqTechCd.getValue())) {
            setValue(pMsg.reqTechCd, "");
        } else {
            setValue(pMsg.reqTechCd, svcMachUpldWrkTMsg.reqTechCd);
        }
        setValue(pMsg.finBrCd, svcMachMstrTMsg.finBrCd);
        setValue(pMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd);
        setValue(pMsg.iwrCondCd, svcMachMstrTMsg.iwrCondCd);
        setValue(pMsg.bizHrsSunFromTm, svcMachMstrTMsg.bizHrsSunFromTm);
        setValue(pMsg.bizHrsSunToTm, svcMachMstrTMsg.bizHrsSunToTm);
        setValue(pMsg.bizHrsMonFriFromTm, svcMachMstrTMsg.bizHrsMonFriFromTm);
        setValue(pMsg.bizHrsMonFriToTm, svcMachMstrTMsg.bizHrsMonFriToTm);
        setValue(pMsg.bizHrsSatFromTm, svcMachMstrTMsg.bizHrsSatFromTm);
        setValue(pMsg.bizHrsSatToTm, svcMachMstrTMsg.bizHrsSatToTm);
        setValue(pMsg.enetPlotFlg, svcMachMstrTMsg.enetPlotFlg);
        setValue(pMsg.enetCmntTxt_01, svcMachMstrTMsg.enetCmntTxt_01);
        setValue(pMsg.enetCmntTxt_02, svcMachMstrTMsg.enetCmntTxt_02);
        setValue(pMsg.xxModeCd, ProcessMode.UPDATE_ATTRIBUTE.code);
        return pMsg;
    }
    // END 2023/10/06 Y.Nagasawa [QC#60082,ADD]

    private SVC_MACH_MSTRTMsg getSvcMachMstr(BigDecimal svcMachMstrPk, String serNum) {
        SVC_MACH_MSTRTMsg rtnMsg = null;
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        if (hasValue(svcMachMstrPk)) {
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
            rtnMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        } else {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inMsg.setConditionValue("serNum01", serNum);
            SVC_MACH_MSTRTMsgArray rtnList = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
            if (rtnList.getValidCount() > 0) {
                rtnMsg = rtnList.no(0);
            }
        }
        return rtnMsg;
    }

    // START 2018/08/22 M.Naito [QC#20733, ADD]
    private SVC_MACH_MSTRTMsgArray getSvcMachMstrBySerNum(String serNum, String mdseCd) {
        SVC_MACH_MSTRTMsgArray rtnList = null;
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        if (!hasValue(serNum)) {
            return rtnList;
        }

        if (hasValue(mdseCd)) {
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inMsg.setConditionValue("serNum01", serNum);
            inMsg.setConditionValue("mdseCd01", mdseCd);
            rtnList = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        } else {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            inMsg.setConditionValue("serNum01", serNum);
            rtnList = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        }
        return rtnList;
    }
    // END 2018/08/22 M.Naito [QC#20733, ADD]

    private void getConfigMach(SVC_MACH_MSTRTMsg mainMachTMsg, Map<BigDecimal, String> configMap) {
        if (!hasValue(mainMachTMsg.svcConfigMstrPk)) {
            return;
        }

        SVC_MACH_MSTRTMsgArray machList = getConfigMachList(mainMachTMsg.svcConfigMstrPk.getValue());
        BigDecimal svcMachMstrPk;
        for (int i = 0; i < machList.getValidCount(); i++) {
            svcMachMstrPk = machList.no(i).svcMachMstrPk.getValue();
            if (!configMap.containsKey(svcMachMstrPk)) {
                configMap.put(svcMachMstrPk, null);
            }
        }
    }

    private SVC_MACH_MSTRTMsgArray getConfigMachList(BigDecimal svcConfigMstrPk) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("016");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        return (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private String getBillToCustCd(String locNum) {
        if (!hasValue(locNum)) {
            return null;
        }
        // START 2017/02/15 N.Arai [QC#17301, ADD]
        if (this.billToCustCdMap.containsKey(locNum)) {
            return this.billToCustCdMap.get(locNum);
        }
        // END 2017/02/15 N.Arai [QC#17301, ADD]
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setSQLID("060");
        billToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        billToCustTMsg.setConditionValue("locNum01", locNum);
        BILL_TO_CUSTTMsgArray billToCustTMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billToCustTMsg);
        if (billToCustTMsgArray.getValidCount() == 0) {
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.billToCustCdMap.put(locNum, null);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return null;
        }
        // START 2017/02/15 N.Arai [QC#17301, ADD]
        this.billToCustCdMap.put(locNum, billToCustTMsgArray.no(0).billToCustCd.getValue());
        // END 2017/02/15 N.Arai [QC#17301, ADD]
        return billToCustTMsgArray.no(0).billToCustCd.getValue();
    }

    private String getShipToCustCd(String locNum) {
        if (!hasValue(locNum)) {
            return null;
        }
        // START 2017/02/15 N.Arai [QC#17301, MOD]
        if (this.shipToCustCdMap.containsKey(locNum)) {
            return this.shipToCustCdMap.get(locNum);
        }
        // END 2017/02/15 N.Arai [QC#17301, MOD]
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("048");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        shipToCustTMsg.setConditionValue("locNum01", locNum);
        SHIP_TO_CUSTTMsgArray shipToCustTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
        if (shipToCustTMsgArray.getValidCount() == 0) {
            // START 2017/02/15 N.Arai [QC#17301, ADD]
            this.shipToCustCdMap.put(locNum, null);
            // END 2017/02/15 N.Arai [QC#17301, ADD]
            return null;
        }
        // START 2017/02/15 N.Arai [QC#17301, ADD]
        this.shipToCustCdMap.put(locNum, shipToCustTMsgArray.no(0).shipToCustCd.getValue());
        // END 2017/02/15 N.Arai [QC#17301, ADD]
        return shipToCustTMsgArray.no(0).shipToCustCd.getValue();
    }

    private void setDefaultValue(NSZC001001PMsg pMsg, String svcMachMstrStsCd, String svcMachTrxTpCd) {
        SVC_MACH_MSTR_DEF_MAPTMsg defTMsg = new SVC_MACH_MSTR_DEF_MAPTMsg();
        if (hasValue(svcMachTrxTpCd)) {
            defTMsg = getSvcMachMstrDefMapTMsg(svcMachMstrStsCd, svcMachTrxTpCd);
            if (defTMsg == null) {
                defTMsg = getSvcMachMstrDefMapTMsg(svcMachMstrStsCd, "*");
            }
        } else {
            defTMsg = getSvcMachMstrDefMapTMsg(svcMachMstrStsCd, "*");
        }

        if (defTMsg == null) {
            return;
        }

        // Set Values
        if (hasValue(defTMsg.stkStsCd.getValue())) {
            setValue(pMsg.stkStsCd, defTMsg.stkStsCd);
        }
        if (hasValue(defTMsg.locStsCd.getValue())) {
            setValue(pMsg.svcMachMstrLocStsCd, defTMsg.locStsCd);
        }
    }

    private SVC_MACH_MSTR_DEF_MAPTMsg getSvcMachMstrDefMapTMsg(String svcMachMstrStsCd, String svcMachTrxTpCd) {
        SVC_MACH_MSTR_DEF_MAPTMsg inMsg = new SVC_MACH_MSTR_DEF_MAPTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcMachMstrStsCd, svcMachMstrStsCd);
        setValue(inMsg.svcMachTrxTpCd, svcMachTrxTpCd);
        return (SVC_MACH_MSTR_DEF_MAPTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private boolean callMachUpdApi(NSZC001001PMsg apiPMsg, BigDecimal rowNum) {
        new NSZC001001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            String xxMsgId = S21ApiUtil.getXxMsgIdList(apiPMsg).get(0);
            // START 2017/01/30 K.Ochiai [QC#17296, MOD]
            S21ApiMessage xxMsg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String[] msgPrms = xxMsg.getXxMsgPrmArray();
            String msg = S21MessageFunc.clspGetMessage(xxMsgId, msgPrms);
            this.messenger.addMessageForRecord(rowNum, NSAM0624E, msg);
            // END 2017/01/30 K.Ochiai [QC#17296, MOD]
            return false;
        }
        // START 2023/07/31 S.Moriai [QC#61632, ADD]
        boolean swLicIdUpdateFlg = false;
        String svcMachMstrSwLicId = getSwLicId(apiPMsg.svcMachMstrPk.getValue(), apiPMsg.glblCmpyCd.getValue());
        if (apiPMsg.swLicId.getValue().equals("")) {
            if(!svcMachMstrSwLicId.equals("")){
                swLicIdUpdateFlg = true;
            }
        }else {
            if(svcMachMstrSwLicId.equals("")){
                swLicIdUpdateFlg = true;
            }else {
                if(!apiPMsg.swLicId.getValue().equals(svcMachMstrSwLicId)){
                    swLicIdUpdateFlg = true;
                }
            }
        }
        if(swLicIdUpdateFlg){
            updateSwLicId(apiPMsg.svcMachMstrPk.getValue(), apiPMsg.glblCmpyCd.getValue(), apiPMsg.swLicId.getValue());
        }
        // END 2023/07/31 S.Moriai [QC#61632, ADD]
        return true;
    }

    // START 2023/10/06 Y.Nagasawa [QC#60082,ADD]
    private boolean callMachUpdApiForSldByLineBizTpCdAndReqTechCd(NSZC001001PMsg apiPMsg, BigDecimal rowNum) {
        new NSZC001001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            String xxMsgId = S21ApiUtil.getXxMsgIdList(apiPMsg).get(0);
            S21ApiMessage xxMsg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String[] msgPrms = xxMsg.getXxMsgPrmArray();
            String msg = S21MessageFunc.clspGetMessage(xxMsgId, msgPrms);
            this.messenger.addMessageForRecord(rowNum, NSAM0624E, msg);
            return false;
        }
        return true;
    }
    // End 2023/10/06 Y.Nagasawa [QC#60082,ADD]

    // START 2023/07/31 S.Moriai [QC#61632, ADD]
    private String getSwLicId(BigDecimal svcMachMstrPk, String glblCmpyCd){
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(svcMachMstrTMsg);
        return svcMachMstrTMsg.swLicId.getValue();
    }
    // END 2023/07/31 S.Moriai [QC#61632, ADD]
    
    // START 2023/07/31 S.Moriai [QC#61632, ADD]
    private void updateSwLicId(BigDecimal svcMachMstrPk, String glblCmpyCd, String swLicId){
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(svcMachMstrTMsg);
        setValue(svcMachMstrTMsg.swLicId, swLicId);
        S21FastTBLAccessor.update(svcMachMstrTMsg);
    }
    // END 2023/07/31 S.Moriai [QC#61632, ADD]

    private boolean callMachUpdApiForConfig(NSZC001001PMsg apiPMsg, List<BigDecimal> rowNumList) {
        new NSZC001001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            String xxMsgId = S21ApiUtil.getXxMsgIdList(apiPMsg).get(0);
            // START 2017/01/30 K.Ochiai [QC#17296, MOD]
            S21ApiMessage xxMsg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String[] msgPrms = xxMsg.getXxMsgPrmArray();
            String msg = S21MessageFunc.clspGetMessage(xxMsgId, msgPrms);
            setConfigErrMsg(rowNumList, NSAM0624E, msg);
            // END 2017/01/30 K.Ochiai [QC#17296, MOD]
            return false;
        }
        return true;
    }

    private boolean createSvcMemo(BigDecimal svcMachMstrPk, String svcCmntTxt, BigDecimal rowNum) {
        if (!hasValue(svcCmntTxt)) {
            return true;
        }

        BigDecimal svcMachMstrTrxPk = createSvcMachMstrTrk(svcMachMstrPk, rowNum);
        if (!hasValue(svcMachMstrTrxPk)) {
            return false;
        }

        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(inMsg.svcMemoCatgCd, SVC_MEMO_CATG.MACHINE_MEMO);
        setValue(inMsg.svcMemoTpCd, SVC_MEMO_TP.MACHINE);
        setValue(inMsg.svcCmntTxt, svcCmntTxt);
        setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(inMsg.lastUpdUsrId, BATCH_ID);
        setValue(inMsg.lastUpdTs, this.sysTs);
        setValue(inMsg.svcMemoRsnCd, SVC_MEMO_RSN.MACHINE_MASTER_UPLOAD);
        setValue(inMsg.svcMemoTrxNum, String.valueOf(svcMachMstrTrxPk));
        setValue(inMsg.svcMemoTrxNm, "SVC_MACH_MSTR_TRK_PK");

        EZDTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            this.messenger.addMessageForRecord(rowNum, NSAM0032E, inMsg.getTableName());
            return false;
        }
        return true;
    }

    private BigDecimal createSvcMachMstrTrk(BigDecimal svcMachMstrPk, BigDecimal rowNum) {
        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(tMsg.trxRgtnDt, this.salesDate);
        setValue(tMsg.updFldTxt, "Machine Master Upload");
        setValue(tMsg.updUsrId, BATCH_ID);
        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            this.messenger.addMessageForRecord(rowNum, NSAM0032E, tMsg.getTableName());
            return null;
        }
        return tMsg.svcMachMstrTrkPk.getValue();
    }

    private void setConfigErrMsg(List<BigDecimal> rowNumList, String xxMsgId, String item) {
        for (BigDecimal rowNum : rowNumList) {
            this.messenger.addMessageForRecord(rowNum, xxMsgId, item);
        }
    }

    private EZDTItem getValue(EZDTItem val1, EZDTItem val2) {
        if (hasValue(val1)) {
            return val1;
        }
        return val2;
    }

    private String getValue(String val1, String val2) {
        if (hasValue(val1)) {
            return val1;
        }
        return val2;
    }
    // START 2023/10/03 S.Naya [QC#54186,ADD]
    private String getDescTxt(String val1, String val2) {
        if (hasValue(val1)) {
            return val1;
        }
        return convertSvcPrvdPtyCdToDescTxt(val2);
    }
    // END   2023/10/03 S.Naya [QC#54186,ADD]

    // START 2017/01/25 K.Kitachi [QC#17198, ADD]
    private Map<String, Object> setParamMapForConfig(UPLD_CSV_RQSTTMsg upldCSVRqstTMsg, List<String> procModeCdList, BigDecimal svcConfigMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("upldCsvRqstPk", upldCSVRqstTMsg.upldCsvRqstPk);
        paramMap.put("procModeCdList", procModeCdList);
        paramMap.put("svcConfigMstrPk", svcConfigMstrPk);
        return paramMap;
    }
    // END 2017/01/25 K.Kitachi [QC#17198, ADD]

    // START 2017/02/15 N.Arai [QC#17301, MOD]
    private String createMapKey(String AcctNum, String locNum) {
        StringBuilder buf = new StringBuilder();
        if (ZYPCommonFunc.hasValue(AcctNum)) {
            buf.append(AcctNum);
        }
        buf.append(":");
        if (ZYPCommonFunc.hasValue(locNum)) {
            buf.append(locNum);
        }
        return buf.toString();
    }
    // END 2017/02/15 N.Arai [QC#17301, MOD]

    // START 2018/01/18 M.Kidokoro [QC#21962, ADD]
    private boolean chkStsForOpenOrd(String svcMachMstrStsCd) {
        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd)
                || SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)) {
            return true;
        }
        return false;
    }
    // END 2018/01/18 M.Kidokoro [QC#21962, ADD]

    // START 2018/05/28 K.Kitachi [QC#19932, ADD]
    private boolean isErrWH(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
        int countFlgUpload = 0;
        int countFlgSmm = 0;
        if (ZYPCommonFunc.hasValue(tMsg.svcMachMstrStsCd)) {
            countFlgUpload = countLocStsMndFlgByStsCd(glblCmpyCd, tMsg.svcMachMstrStsCd.getValue());
        }
        if (svcMachMstrTMsg != null && ZYPCommonFunc.hasValue(svcMachMstrTMsg.svcMachMstrStsCd)) {
            countFlgSmm = countLocStsMndFlgByStsCd(glblCmpyCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
        }

        if (countFlgUpload == 0 && countFlgSmm == 0) {
            // No need to check warehouse
            return false;
        }
        // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]

        if (MODE_CREATE.equals(tMsg.procModeCd.getValue())) {
            if (!insertWarehouseValidation(tMsg)) {
                this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0722E, null);
                return true;
            }
        }
        if (MODE_UPDATE.equals(tMsg.procModeCd.getValue()) || MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            if (!updateWarehouseValidation(tMsg, svcMachMstrTMsg)) {
                this.messenger.addMessageForRecord(tMsg.upldCsvRqstRowNum.getValue(), NSAM0722E, null);
                return true;
            }
        }
        return false;
    }

    private boolean isWarehouse(String invtyLocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("slsDt", this.salesDate);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("endDt", "29991231");

        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countRtlSWH", ssmParam);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    private boolean insertWarehouseValidation(SVC_MACH_UPLD_WRKTMsg tMsg) {
        if (!isWarehouse(tMsg.indCurLocNum.getValue())) {
            return true;
        }
        SVC_MACH_MSTR_DEF_MAPTMsg defTMsg = new SVC_MACH_MSTR_DEF_MAPTMsg();
        if (hasValue(tMsg.svcMachTrxTpCd)) {
            defTMsg = getSvcMachMstrDefMapTMsg(tMsg.svcMachMstrStsCd.getValue(), tMsg.svcMachTrxTpCd.getValue());
            if (defTMsg == null) {
                defTMsg = getSvcMachMstrDefMapTMsg(tMsg.svcMachMstrStsCd.getValue(), "*");
            }
        } else {
            defTMsg = getSvcMachMstrDefMapTMsg(tMsg.svcMachMstrStsCd.getValue(), "*");
        }
        if (defTMsg == null) {
            return false;
        }

        String locStsCd = defTMsg.locStsCd.getValue();
        String stkStsCd = defTMsg.stkStsCd.getValue();
        BigDecimal svcMachMstrQty = getSvcMachMstrQty(this.glblCmpyCd, null, tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), locStsCd, stkStsCd);
        BigDecimal invtyDtlDlyQty = getInvtyDtlDlyQty(this.glblCmpyCd, null, tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), locStsCd, stkStsCd);
        if (svcMachMstrQty.compareTo(invtyDtlDlyQty) >= 0) {
            return false;
        }
        if (!hasValue(tMsg.serNum)) {
            return true;
        }
        BigDecimal qty = getInvtyDtlDlyQty(this.glblCmpyCd, tMsg.serNum.getValue(), tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), locStsCd, stkStsCd);
        if (qty.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }

    private boolean updateWarehouseValidation(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (isWarehouse(svcMachMstrTMsg.indCurLocNum.getValue())) {
            if (!tMsg.indCurLocNum.getValue().equals(svcMachMstrTMsg.indCurLocNum.getValue())) {
                return false;
            }
        }
        if (!isWarehouse(tMsg.indCurLocNum.getValue())) {
            return true;
        }
        if (!statusWarehouseValidation(tMsg, svcMachMstrTMsg)) {
            return false;
        }
        if (!terminateWarehouseValidation(tMsg, svcMachMstrTMsg)) {
            return false;
        }
        if (!serialChangeWarehouseValidation(tMsg, svcMachMstrTMsg)) {
            return false;
        }
        return true;
    }

    private boolean statusWarehouseValidation(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (!MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            if (!tMsg.svcMachMstrStsCd.getValue().equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
                return false;
            }
        }
        if (!tMsg.svcMachUsgStsCd.getValue().equals(svcMachMstrTMsg.svcMachUsgStsCd.getValue())) {
            return false;
        }
        return true;
    }

    private boolean terminateWarehouseValidation(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (!MODE_TERMINATE.equals(tMsg.procModeCd.getValue())) {
            return true;
        }
        BigDecimal svcMachMstrQty = getSvcMachMstrQty(this.glblCmpyCd, null, tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), svcMachMstrTMsg.svcMachMstrLocStsCd.getValue(), svcMachMstrTMsg.stkStsCd.getValue());
        BigDecimal invtyDtlDlyQty = getInvtyDtlDlyQty(this.glblCmpyCd, null, tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), svcMachMstrTMsg.svcMachMstrLocStsCd.getValue(), svcMachMstrTMsg.stkStsCd.getValue());
        if (svcMachMstrQty.compareTo(invtyDtlDlyQty) <= 0) {
            return false;
        }
        if (!hasValue(tMsg.serNum)) {
            return true;
        }
        BigDecimal qty = getInvtyDtlDlyQty(this.glblCmpyCd, tMsg.serNum.getValue(), tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), svcMachMstrTMsg.svcMachMstrLocStsCd.getValue(), svcMachMstrTMsg.stkStsCd.getValue());
        if (qty.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    private boolean serialChangeWarehouseValidation(SVC_MACH_UPLD_WRKTMsg tMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (tMsg.serNum.getValue().equals(svcMachMstrTMsg.serNum.getValue())) {
            return true;
        }
        BigDecimal qty = getInvtyDtlDlyQty(this.glblCmpyCd, svcMachMstrTMsg.serNum.getValue(), tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), svcMachMstrTMsg.svcMachMstrLocStsCd.getValue(), svcMachMstrTMsg.stkStsCd.getValue());
        if (qty.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        qty = getInvtyDtlDlyQty(this.glblCmpyCd, tMsg.serNum.getValue(), tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), svcMachMstrTMsg.svcMachMstrLocStsCd.getValue(), svcMachMstrTMsg.stkStsCd.getValue());
        if (qty.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        qty = getSvcMachMstrQty(this.glblCmpyCd, tMsg.serNum.getValue(), tMsg.mdseCd.getValue(), tMsg.indCurLocNum.getValue(), svcMachMstrTMsg.svcMachMstrLocStsCd.getValue(), svcMachMstrTMsg.stkStsCd.getValue());
        if (qty.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    private BigDecimal getSvcMachMstrQty(String glblCmpyCd, String serNum, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("locStsCd", locStsCd);
        ssmParam.put("stkStsCd", stkStsCd);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        ssmParam.put("svcMachMstrStsCdList", svcMachMstrStsCdList);

        return (BigDecimal) this.ssmBatchClient.queryObject("getSvcMachMstrQty", ssmParam);
    }

    private BigDecimal getInvtyDtlDlyQty(String glblCmpyCd, String serNum, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("locStsCd", locStsCd);
        ssmParam.put("stkStsCd", stkStsCd);

        return (BigDecimal) this.ssmBatchClient.queryObject("getInvtyDtlDlyQty", ssmParam);
    }
    // END 2018/05/28 K.Kitachi [QC#19932, ADD]
    // Add Start 2018/06/14 QC#23428
    private boolean isSvcExch(SVC_MACH_UPLD_WRKTMsg tMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(tMsg.svcMachMstrPk.getValue(), null);
        if (svcMachMstrTMsg == null) {
            return false;
        }
        if (hasValue(svcMachMstrTMsg.svcConfigMstrPk)) {
            return false;
        }
        if (!hasValue(svcMachMstrTMsg.svcMachTpCd)) {
            return false;
        }

        // Status Check
        if (!SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
            return false;
        }
        if (!SVC_MACH_MSTR_STS.INSTALLED.equals(tMsg.svcMachMstrStsCd.getValue())) {
            return false;
        }

        // Check SVC_CONFIG_MSTR_DTL
        SVC_CONFIG_MSTR_DTLTMsg svcConfigMstrDtlTMsg = getSvcConfigMstrDtlTMsg(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue());
        if (svcConfigMstrDtlTMsg == null) {
            return false;
        }
        if (!hasValue(svcConfigMstrDtlTMsg.svcConfigMstrPk)) {
            return false;
        }

        // Order Number Check
        if (!hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            return false;
        }
        if (!isExchOrdCatg(svcMachMstrTMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue())) {
            return false;
        }
        return true;
    }

    private boolean isExchOrdCatg(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        List<String> ordHdrStsCdList = new ArrayList<String>();
        ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
        ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET);
        ssmParam.put("contrIntfcSrcTpCd", CONTR_INTFC_SRC_TP.IB_UPDATE);
        ssmParam.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);

        BigDecimal cnt = (BigDecimal) ssmBatchClient.queryObject("cntExchOrd", ssmParam);
        if (BigDecimal.ZERO.compareTo(cnt) == 0) {
            return false;
        }
        return true;
    }

    private SVC_CONFIG_MSTR_DTLTMsg getSvcConfigMstrDtlTMsg(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg inMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_CONFIG_MSTR_DTLTMsgArray array = (SVC_CONFIG_MSTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0) {
            return null;
        }
        return array.no(0);
    }

    private NSZC001001PMsg setExchApiPMsg(SVC_MACH_UPLD_WRKTMsg tMsg) {
        SVC_MACH_MSTRTMsg machTMsg = getSvcMachMstr(tMsg.svcMachMstrPk.getValue(), null);
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        setValue(pMsg.glblCmpyCd, tMsg.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.xxModeCd, ProcessMode.INSTALLATION.code);
        setValue(pMsg.svcMachMstrPk, tMsg.svcMachMstrPk);
        setValue(pMsg.svcMachMstrStsCd, tMsg.svcMachMstrStsCd);
        setValue(pMsg.istlDt, tMsg.istlDt);
        setValue(pMsg.stkStsCd, machTMsg.stkStsCd);
        setValue(pMsg.svcMachMstrLocStsCd, machTMsg.svcMachMstrLocStsCd);
        setValue(pMsg.svcMachUsgStsCd, tMsg.svcMachUsgStsCd);
        // START 2023/07/31 S.Moriai [QC#61632, ADD]
        setValue(pMsg.swLicId, tMsg.swLicId);
        // END 2023/07/31 S.Moriai [QC#61632, ADD]
        // START 2023/10/03 S.Naya [QC#54186,ADD]
        setValue(pMsg.istlBySvcPrvdPtyCd, convertSvcPrvdPtyDescTxtToCd(tMsg.svcPrvdPtyDescTxt_01.getValue()));
        setValue(pMsg.svcBySvcPrvdPtyCd, convertSvcPrvdPtyDescTxtToCd(tMsg.svcPrvdPtyDescTxt_02.getValue()));
        // END   2023/10/03 S.Naya [QC#54186,ADD]
        return pMsg;
    }
    // Add End 2018/06/14 QC#23428

    // add start 2018/07/17 QC#22583
    private String createResultMessageArg() {
        StringBuilder builder = new StringBuilder();
        if (this.insertCount > 0) {
            builder.append(String.format(RESULT_MSG_INS, this.insertCount));
        }
        if (this.updateCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, this.updateCount));
        }
        if (this.errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, this.errCount));
        }
        return builder.toString();
    }
    // add end 2018/07/17 QC#22583
    // START 2023/10/03 S.Naya [QC#54186,ADD]
    private String convertSvcPrvdPtyCdToDescTxt(String svcPrvdPtyCd) {
        if (this.convertSvcPrvdPtyCdMap.containsKey(svcPrvdPtyCd)) {
            return this.convertSvcPrvdPtyCdMap.get(svcPrvdPtyCd);
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcPrvdPtyCd", svcPrvdPtyCd);

        Map<String, String> resultMap = (Map<String, String>) this.ssmBatchClient.queryObject("getSvdPrvdPtyCdAndDescTxt", paramMap);
        this.convertSvcPrvdPtyCdMap.put(svcPrvdPtyCd, resultMap.get("SVC_PRVD_PTY_DESC_TXT"));

        return resultMap.get("SVC_PRVD_PTY_DESC_TXT");
    }

    private String convertSvcPrvdPtyDescTxtToCd(String svcPrvdPtyDescTxt) {
        if (this.convertSvcPrvdPtyDescMap.containsKey(svcPrvdPtyDescTxt)) {
            return this.convertSvcPrvdPtyDescMap.get(svcPrvdPtyDescTxt);
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcPrvdPtyDescTxt", svcPrvdPtyDescTxt);

        List<Map<String, String>> resultList = (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getSvdPrvdPtyCdAndDescTxt", paramMap);
        this.convertSvcPrvdPtyDescMap.put(svcPrvdPtyDescTxt, resultList.get(0).get("SVC_PRVD_PTY_CD"));

        return resultList.get(0).get("SVC_PRVD_PTY_CD");
    }
    // END   2023/10/03 S.Naya [QC#54186,ADD]

    // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
    /**
     * Count locStsMndFlg by machine master status code
     * @param glblCmpyCd global company code
     * @param svcMachMstrStsCd machine master status code
     * @return locStsMndFlg count
     */
    public int countLocStsMndFlgByStsCd(String glblCmpyCd, String svcMachMstrStsCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locStsMndFlg", ZYPConstant.FLG_ON_Y);
        params.put("svcMachMstrStsCd", svcMachMstrStsCd);

        Integer resultNum = (Integer) ssmBatchClient.queryObject("countLocStsMndFlgByStsCd", params);
        return resultNum.intValue();
    }
    // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
}
