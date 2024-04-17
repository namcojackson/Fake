package com.canon.cusa.s21.batch.NSA.NSAB079001;

import static com.canon.cusa.s21.batch.NSA.NSAB079001.constant.NSAB079001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CTRYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.FRT_CONDTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_SPLY_ORD_TP_RELNTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.batch.NSA.NSAB079001.constant.NSAB079001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_FUFL_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Auto Ship Toner from IWR
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/14   Hitachi         U.Kim           Create          QC#18616
 * 2018/01/09   Hitachi         M.Kidokoro      Update          QC#20635
 * 2018/01/15   Hitachi         K.Kojima        Update          QC#23214
 * 2018/01/16   Hitachi         K.Kojima        Update          QC#21855
 * 2018/02/16   Hitachi         U.Kim           Update          QC#20297(Sol#379)
 * 2018/02/28   Hitachi         U.Kim           Update          QC#23296
 * 2018/05/25   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/07/05   CITS            T.Wada          Update          QC#23726
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/09/19   Fujitsu         Mz.Takahashi    Update          QC#9700
 * 2018/09/27   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/12/14   Fujitsu         M.Ohno          Update          QC#29315
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 *</pre>
 */
public class NSAB079001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Transaction Id List */
    private BigDecimal[] trxIdList;

    /** Sales Date */
    private String salesDate;

    /** ugwSplyOrdrLimitDay */
    private BigDecimal ugwSplyOrdrLimitDay;

    /** Commit Number */
    private int commitNumber;

    /** Total Count */
    private int totalCount;

    /** error Count */
    private int errorCount;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatClient = null;

    /** From Address List */
    private List<S21MailAddress> fromAddrList = null;

    /** To Address List */
    private List<S21MailAddress> addrToList = null;

    /** Mail Template : Error */
    private S21MailTemplate templateErr = null;

    /** Mail Template : Customer Mismatch or Duplicate Supply Order */
    private S21MailTemplate templateMisDup = null;

    /** Mail Template : Special Handing */
    private S21MailTemplate templateSpecHand = null;

    /** Error Message List */
    private List<String> errMsgList = null;

    /** Total Countup Flag */
    private boolean countupFlag = false;

    // START 2018/02/28 U.Kim [QC#23296, ADD]
    /** Error Message For Default API*/
    private String errMsgForDfltApi = null;

    /** Error Message For Pricing API*/
    private String errMsgForPrcApi = null;
    // END 2018/02/28 U.Kim [QC#23296, ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB079001().executeBatch(NSAB079001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Interface ID" });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(ZZZM9006E, new String[] {"SalesDate" });
        }

        // Get UGW_SPLY_ORDR_LIMIT_DAY
        this.ugwSplyOrdrLimitDay = ZYPCodeDataUtil.getNumConstValue(UGW_SPLY_ORDR_LIMIT_DAY, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.ugwSplyOrdrLimitDay)) {
            throw new S21AbendException(ZZM9000E, new String[] {"UGW_SPLY_ORDR_LIMIT_DAY" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        this.fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"From mail-address.", MAIL_GROUP_ID_FROM });
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        this.addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Error Mail Template
        this.templateErr = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_ERR);
        if (!hasValue(templateErr.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mail Template", MAIL_TEMPLATE_ID_ERR });
        }

        // Get Customer Mismatch / Duplicate Supply Order Mail
        // Template
        this.templateMisDup = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_MIS_DUP);
        if (!hasValue(templateMisDup.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mail Template", MAIL_TEMPLATE_ID_MIS_DUP });
        }

        // Get Special Handing Mail Template
        this.templateSpecHand = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_SPEC_HAND);
        if (!hasValue(templateSpecHand.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mail Template", MAIL_TEMPLATE_ID_SPEC_HAND });
        }

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxIdList = trxAccess.getIntegrationRecord(this.intfcId);
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatClient = S21SsmBatchClient.getClient(getClass());
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errorCount, this.errorCount, this.totalCount);
    }

    @Override
    protected void mainRoutine() {
        for (BigDecimal trxId : this.trxIdList) {
            processTrxData(trxId);

            // Insert Transaction data
            trxAccess.endIntegrationProcess(this.intfcId, trxId);

            commit();
        }
    }

    private void processTrxData(BigDecimal trxId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("interfaceId", this.intfcId);
        ssmParam.put("transactionId", trxId);
        ssmParam.put("flg1", ZYPConstant.FLG_ON_1);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);

        try {
            stmt = ssmLLClient.createPreparedStatement("getNSAI0240", ssmParam, execParam);
            rs = stmt.executeQuery();

            String preDvcSerNum = null;
            String preUsrCustId = null;
            List<String> imgSplyColorTpCdList = new ArrayList<String>();

            while (rs.next()) {
                String dvcSerNum = (String) rs.getString("DVC_SER_NUM");
                String usrCustId = (String) rs.getString("USR_CUST_ID");
                String imgSplyColorTpCd = (String) rs.getString("IMG_SPLY_COLOR_TP_CD");

                if (!ZYPCommonFunc.hasValue(dvcSerNum)) {
                    continue;
                }

                if ((preDvcSerNum != null && !preDvcSerNum.equals(dvcSerNum)) || (preUsrCustId != null && !preUsrCustId.equals(usrCustId))) {
                    if (!processSerialData(preDvcSerNum, preUsrCustId, imgSplyColorTpCdList)) {
                        if (this.countupFlag) {
                            this.errorCount++;
                        }
                    }
                    if (this.countupFlag) {
                        this.totalCount++;
                        commit();
                    }
                    imgSplyColorTpCdList = new ArrayList<String>();
                }

                preDvcSerNum = dvcSerNum;
                preUsrCustId = usrCustId;
                imgSplyColorTpCdList.add(imgSplyColorTpCd);
            }

            if (imgSplyColorTpCdList.size() > 0) {
                if (!processSerialData(preDvcSerNum, preUsrCustId, imgSplyColorTpCdList)) {
                    if (this.countupFlag) {
                        this.errorCount++;
                    }
                }
                if (this.countupFlag) {
                    this.totalCount++;
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private boolean processSerialData(String dvcSerNum, String usrCustId, List<String> imgSplyColorTpCdList) {
        this.countupFlag = false;
        this.errMsgList = new ArrayList<String>();

        // Search Machine Master
        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = getSvcMachMstr(dvcSerNum);
        if (svcMachMstrTMsgArray == null || svcMachMstrTMsgArray.getValidCount() == 0) {
            return true;
        }
        if (svcMachMstrTMsgArray.getValidCount() > 1) {
            return true;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = svcMachMstrTMsgArray.no(0);
        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstr(svcMachMstrTMsg.svcConfigMstrPk.getValue());

        // Search Machine Master For Accessory
        if (SVC_MACH_TP.ACCESSORY.equals(svcMachMstrTMsg.svcMachTpCd.getValue())) {
            svcMachMstrTMsg = getSvcMachMstr(svcConfigMstrTMsg.svcConfigMstrPk.getValue());
            if (svcMachMstrTMsg == null) {
                return true;
            }
        }

        // Search Contract
        Map<String, Object> contractInfo = getDsContrDtlSearch(svcMachMstrTMsg.svcMachMstrPk.getValue());
        if (contractInfo == null || contractInfo.size() == 0) {
            return true;
        }

        String dsContrCatgCd = (String) contractInfo.get("DS_CONTR_CATG_CD");
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl((BigDecimal) contractInfo.get("DS_CONTR_DTL_PK"));

        // Search Contract For Fleet
        if (ZYPCommonFunc.hasValue(dsContrCatgCd) && DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            dsContrDtlTMsg = getDsContrDtl((BigDecimal) contractInfo.get("PRNT_DS_CONTR_DTL_PK"));
        }
        if (dsContrDtlTMsg == null) {
            return true;
        }
        DS_CONTRTMsg dsContrTMsg = getDsContr(dsContrDtlTMsg.dsContrPk.getValue());

        // Check Supply Inclusive
        if (!checkIsSupplyInclusive(dsContrDtlTMsg.svcPgmMdseCd.getValue())) {
            return true;
        }

        this.countupFlag = true;

        // Search Toner
        List<Map<String, Object>> tonerInfoList = getTonerInfo(svcConfigMstrTMsg.svcConfigMstrPk.getValue());
        if (tonerInfoList == null || tonerInfoList.size() == 0) {
            sendMailForError(dvcSerNum, MSG_FAIL_SRCH_TNR);
            return false;
        }

        // Search Order Toner
        List<Map<String, Object>> tonerList = new ArrayList<Map<String, Object>>();
        for (String imgSplyColorTpCd : imgSplyColorTpCdList) {
            boolean setFlag = false;
            for (Map<String, Object> tonerInfo : tonerInfoList) {
                if (imgSplyColorTpCd.equals((String) tonerInfo.get("IMG_SPLY_COLOR_TP_CD"))) {
                    tonerList.add(tonerInfo);
                    setFlag = true;
                    break;
                }
            }
            if (!setFlag) {
                continue;
            }
        }
        if (tonerList.size() == 0) {
            return true;
        }

        // Check Customer
        if (!ZYPCommonFunc.hasValue(usrCustId) || !ZYPCommonFunc.hasValue(svcMachMstrTMsg.curLocAcctNum) || !usrCustId.equals(svcMachMstrTMsg.curLocAcctNum.getValue())) {
            for (Map<String, Object> toner : tonerList) {
                sendMailForCustMismatchOrDuplicateSupplyOrder(SEND_MAIL_MODE_CUST_MIS, dvcSerNum, null, svcMachMstrTMsg, getShipToCust(svcMachMstrTMsg.curLocNum.getValue()), (String) toner.get("MDSE_CD"), null);
            }
            return false;
        }

        // Check Duplicate Supply Order
        List<String> orderTonerList = new ArrayList<String>();
        for (Map<String, Object> toner : tonerList) {
            String tonerMdseCd = null;
            if (ZYPConstant.FLG_ON_Y.equals((String) toner.get("ORD_TAKE_MDSE_FLG"))) {
                tonerMdseCd = (String) toner.get("ORD_TAKE_MDSE_CD");
            } else {
                tonerMdseCd = (String) toner.get("MDSE_CD");
            }
            String cpoOrdNum = getSupplyOrderInfo(svcMachMstrTMsg.svcMachMstrPk.getValue(), tonerMdseCd, dsContrCatgCd, dsContrTMsg.dsContrNum.getValue(), (String) toner.get("ORD_TAKE_MDSE_FLG"));
            if (ZYPCommonFunc.hasValue(cpoOrdNum)) {
                sendMailForCustMismatchOrDuplicateSupplyOrder(SEND_MAIL_MODE_DUPL_ORD, dvcSerNum, null, svcMachMstrTMsg, getShipToCust(svcMachMstrTMsg.curLocNum.getValue()), (String) toner.get("MDSE_CD"), cpoOrdNum);
                continue;
            }
            orderTonerList.add((String) toner.get("MDSE_CD"));
        }
        if (orderTonerList.size() == 0) {
            return false;
        }

        // Check Special Handling
        BigDecimal spcHandCheck = getInstructions(svcMachMstrTMsg);
        String commitMode = null;
        if (spcHandCheck.compareTo(BigDecimal.ZERO) > 0) {
            commitMode = NWZC150001Constant.MODE_SAVE;
        } else {
            commitMode = NWZC150001Constant.MODE_SUBMIT;
        }

        // Call NMZC6110 Default Carrier API
        // 2018/12/13 QC#29315 Mod Start
//        NMZC611001PMsg carrierApiPMsg = callDefaultCarrierAPI(svcMachMstrTMsg.curLocAcctNum.getValue());
        SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust(svcMachMstrTMsg.curLocNum.getValue());
        SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg = getSvcSplyOrdTpReln(svcMachMstrTMsg.svcByLineBizTpCd.getValue(), svcMachMstrTMsg.sldByLineBizTpCd.getValue());
        NMZC611001PMsg carrierApiPMsg = callDefaultCarrierAPI(svcMachMstrTMsg.curLocAcctNum.getValue(), shipToCustTMsg.locNum.getValue(), getDsBizAreaCd(svcSplyOrdTpRelnTMsg.dsOrdCatgCd.getValue()), svcMachMstrTMsg.svcByLineBizTpCd.getValue(), svcMachMstrTMsg.sldByLineBizTpCd.getValue());
        if (S21ApiUtil.isXxMsgId(carrierApiPMsg)) {
            rollback();
            // START 2018/02/28 U.Kim [QC#23296, MOD]
            // sendMailForError(dvcSerNum, S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_DFT_API, SER_NUM_TXT, dvcSerNum }));
            setErroMsgForDfltApi(carrierApiPMsg);
            sendMailForError(dvcSerNum, S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_DFT_API, DTL_TXT, this.errMsgForDfltApi}));
            this.errMsgForDfltApi = null;
            // END 2018/02/28 U.Kim [QC#23296, MOD]
            return false;
        }

        // Call NWZC1570 Pricing API
        BILL_TO_CUSTTMsg billToCustTMsg = getBillToCust(dsContrDtlTMsg.baseBillToCustCd.getValue());
        // 2018/12/13 QC#29315 Del Start
//        SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust(svcMachMstrTMsg.curLocNum.getValue());
        // 2018/12/13 QC#29315 Del End
        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//        SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg = getSvcSplyOrdTpReln(svcMachMstrTMsg.svcByLineBizTpCd.getValue());
        // 2018/12/11 QC#29315 Del Start
//        SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg = getSvcSplyOrdTpReln(svcMachMstrTMsg.svcByLineBizTpCd.getValue(), svcMachMstrTMsg.sldByLineBizTpCd.getValue());
        // 2018/12/11 QC#29315 Del End
        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NWZC157001PMsg prcApiPMsg = calcuLineDtlForEditMode(svcMachMstrTMsg, svcConfigMstrTMsg, orderTonerList, billToCustTMsg, shipToCustTMsg, svcSplyOrdTpRelnTMsg, dsContrTMsg);
        if (prcApiPMsg == null) {
            rollback();
            // START 2018/02/28 U.Kim [QC#23296, MOD]
            // sendMailForError(dvcSerNum, S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_PRC_API, SER_NUM_TXT, dvcSerNum }));
            sendMailForError(dvcSerNum, S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_PRC_API, DTL_TXT, this.errMsgForPrcApi}));
            this.errMsgForPrcApi = null;
            // END 2018/02/28 U.Kim [QC#23296, MOD]
            return false;
        }

        // Call NWZC1500 DS CPO Update API
        NWZC150001PMsg cpoUpdApiPMsg = callDsCpoUpdateAPI(commitMode, prcApiPMsg, carrierApiPMsg, svcMachMstrTMsg, svcConfigMstrTMsg, dsContrTMsg, billToCustTMsg, shipToCustTMsg, svcSplyOrdTpRelnTMsg);
        if (this.errMsgList.size() > 0) {
            rollback();
            // START 2018/02/28 U.Kim [QC#23296, MOD]
            // sendMailForError(dvcSerNum, S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_CPO_UPDT_API, SER_NUM_TXT, dvcSerNum }));
            sendMailForError(dvcSerNum, S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_CPO_UPDT_API, DTL_TXT, this.errMsgList.get(this.errMsgList.size() - NUM_ONE)}));
            // END 2018/02/28 U.Kim [QC#23296, MOD]
            return false;
        }

        if (spcHandCheck.compareTo(BigDecimal.ZERO) > 0) {
            for (String orderToner : orderTonerList) {
                sendMailForSpecialHanding(dvcSerNum, null, svcMachMstrTMsg, getShipToCust(svcMachMstrTMsg.curLocNum.getValue()), orderToner, cpoUpdApiPMsg.cpoOrdNum.getValue());
            }
        }

        return true;
    }

    // 2018/12/13 QC#29315 Mod Start
//    private NMZC611001PMsg callDefaultCarrierAPI(String dsAcctNum) {
    private NMZC611001PMsg callDefaultCarrierAPI(String dsAcctNum, String locNum, String dsBizAreaCd, String svcByLineBizTpCd, String sldByLineBizTpCd) {
    // 2018/12/13 QC#29315 Mod End
        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, dsAcctNum);
        // 2018/12/13 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.locNum, locNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, dsBizAreaCd);
        String lineBizTpCd = convLineBizTpToSvcLineBiz(sldByLineBizTpCd);
        if (lineBizTpCd == null) {
            lineBizTpCd = svcByLineBizTpCd;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, lineBizTpCd);
        // 2018/12/13 QC#29315 Add End
        new NMZC611001().execute(pMsg, ONBATCH_TYPE.BATCH);
        return pMsg;
    }

    private NWZC157001PMsg calcuLineDtlForEditMode(SVC_MACH_MSTRTMsg svcMachMstrTMsg, SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg, List<String> orderTonerList, BILL_TO_CUSTTMsg billToCustTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg,
            SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg, DS_CONTRTMsg dsContrTMsg) {
        // call Pricing API for Get Price Mode
        NWZC157001PMsg prcApiPMsg = callPricingApiOfGetBasePriceMode(svcMachMstrTMsg, svcConfigMstrTMsg, orderTonerList, billToCustTMsg, shipToCustTMsg, svcSplyOrdTpRelnTMsg, dsContrTMsg.ccyCd.getValue());
        if (!checkPrcApiParam(prcApiPMsg)) {
            // START 2018/02/28 U.Kim [QC#23296, ADD]
            setErrMsgForPrcApi(prcApiPMsg);
            // END 2018/02/28 U.Kim [QC#23296, ADD]
            return null;
        }

        // call Pricing API for Get Order All Mode
        prcApiPMsg = callPricingApiOfGetOrderAllMode(prcApiPMsg, svcMachMstrTMsg, svcConfigMstrTMsg, orderTonerList, billToCustTMsg, shipToCustTMsg, svcSplyOrdTpRelnTMsg, dsContrTMsg);
        if (!checkPrcApiParam(prcApiPMsg)) {
            // START 2018/02/28 U.Kim [QC#23296, ADD]
            setErrMsgForPrcApi(prcApiPMsg);
            // END 2018/02/28 U.Kim [QC#23296, ADD]
            return null;
        }

        return prcApiPMsg;
    }

    /**
     * Call NWZC1570 Pricing API
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param svcConfigMstrTmsg SVC_CONFIG_MSTRTMsg
     * @param orderTonerList List<String>
     * @param billToCustTMsg BILL_TO_CUSTTMsg
     * @param shipToCustTMsg SHIP_TO_CUSTTMsg
     * @param svcSplyOrdTpRelnTmsg SVC_SPLY_ORD_TP_RELNTMsg
     * @param ccyCd String
     * @return NWZC157001PMsg
     */
    public NWZC157001PMsg callPricingApiOfGetBasePriceMode(SVC_MACH_MSTRTMsg svcMachMstrTMsg, SVC_CONFIG_MSTRTMsg svcConfigMstrTmsg, List<String> orderTonerList, BILL_TO_CUSTTMsg billToCustTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg,
            SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTmsg, String ccyCd) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, svcSplyOrdTpRelnTmsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, svcSplyOrdTpRelnTmsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, svcMachMstrTMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        prcApiPMsg.custIssPoNum.clear();
        prcApiPMsg.dsPmtMethCd.clear();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
        // START 2018/02/19 U.Kim [QC#20297(Sol#379), ADD]
        Map<String, String> frtParam = new HashMap<String, String>();
        setDefFrtCond(frtParam, svcSplyOrdTpRelnTmsg, svcMachMstrTMsg);
        // END 2018/02/19 U.Kim [QC#20297(Sol#379), ADD]

        // Detail : Supply Order Line
        for (int i = 0; i < orderTonerList.size(); i++) {
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, ZYPCommonFunc.leftPad(String.valueOf(i + 1), NSAB079001Constant.NUM_THREE, "0"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, DEF_LINE_SUB_NUM);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, billToCustTMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, shipToCustTMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, shipToCustTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, billToCustTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, svcMachMstrTMsg.finBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, ccyCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, orderTonerList.get(i));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, svcSplyOrdTpRelnTmsg.dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, svcConfigMstrTmsg.mdlId);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, shipToCustTMsg.ctryCd);
            // START 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
            // ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, FRT_COND.NO_CHARGE);
            // ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, frtParam.get("frtCondCd"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, frtParam.get("shpgSvcLvlCd"));
            // END 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            prcApiPMsg.NWZC157002PMsg.setValidCount(1);
        }

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);

        return prcApiPMsg;
    }

    private NWZC157001PMsg callPricingApiOfGetOrderAllMode(NWZC157001PMsg prcApiPMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg, List<String> orderTonerList, BILL_TO_CUSTTMsg billToCustTMsg,
            SHIP_TO_CUSTTMsg shipToCustTMsg, SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg, DS_CONTRTMsg dsContrTMsg) {
        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(NWZC157001.GET_ORDER_ALL);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, svcSplyOrdTpRelnTMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, svcSplyOrdTpRelnTMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, svcMachMstrTMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        prcApiPMsg.custIssPoNum.clear();
        prcApiPMsg.dsPmtMethCd.clear();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.negoDealAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, svcMachMstrTMsg.finBrCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // Detail : Supply Order Line
        Map<String, Object> tocMap = null;
        tocMap = (Map<String, Object>) getToc(dsContrTMsg.tocCd.getValue());
        // START 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        // FRT_CONDTMsg frtCondTMsg = getFrtCond();
        Map<String, String> frtParam = new HashMap<String, String>();
        setDefFrtCond(frtParam, svcSplyOrdTpRelnTMsg, svcMachMstrTMsg);
        FRT_CONDTMsg frtCondTMsg = getFrtCond(frtParam.get("frtCondCd"));
        // END 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        for (int i = 0; i < orderTonerList.size(); i++) {
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineNum, ZYPCommonFunc.leftPad(String.valueOf(i + 1), NSAB079001Constant.NUM_THREE, "0"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.trxLineSubNum, DEF_LINE_SUB_NUM);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.billToCustCd, billToCustTMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shipToCustCd, shipToCustTMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_SH, shipToCustTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsAcctNum_BL, billToCustTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.coaBrCd, svcMachMstrTMsg.finBrCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ccyCd, dsContrTMsg.ccyCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdseCd, orderTonerList.get(i));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.pkgUomCd, PKG_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.dsOrdLineCatgCd, svcSplyOrdTpRelnTMsg.dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ordCustUomQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.mdlId, svcConfigMstrTMsg.mdlId);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SH, shipToCustTMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SH, shipToCustTMsg.stCd);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SH, shipToCustTMsg.ctryCd);
            // START 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
            // ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, FRT_COND.NO_CHARGE);
            // ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtCondCd, frtParam.get("frtCondCd"));
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.shpgSvcLvlCd, frtParam.get("shpgSvcLvlCd"));
            // END 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            if (tocMap != null) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctyAddr_SR, (String) tocMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.stCd_SR, (String) tocMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.ctryCd_SR, (String) tocMap.get("CTRY_CD"));
            }
            if (frtCondTMsg != null) {
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgToCd, frtCondTMsg.frtChrgToCd);
                ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.frtChrgMethCd, frtCondTMsg.frtChrgMethCd);
            }
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.xxUnitPrc, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            prcApiPMsg.NWZC157002PMsg.setValidCount(i + 1);
        }

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);

        return prcApiPMsg;
    }

    private NWZC150001PMsg callDsCpoUpdateAPI(String commitMode, NWZC157001PMsg prcApiPMsg, NMZC611001PMsg carrierApiPMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, SVC_CONFIG_MSTRTMsg svcConfigMstrTmsg, DS_CONTRTMsg dsContrTMsg,
            BILL_TO_CUSTTMsg billToCustTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg, SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg) {

        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();

        // Header
        // START 2018/01/15 K.Kojima [QC#23214,MOD]
        // setCpoUpdApiReqPMsg(commitMode, cpoUpdApiReqPMsg, prcApiPMsg, carrierApiPMsg, svcMachMstrTMsg, billToCustTMsg, shipToCustTMsg, svcSplyOrdTpRelnTMsg);
        setCpoUpdApiReqPMsg(commitMode, cpoUpdApiReqPMsg, prcApiPMsg, carrierApiPMsg, svcMachMstrTMsg, billToCustTMsg, shipToCustTMsg, svcSplyOrdTpRelnTMsg, dsContrTMsg);
        // END 2018/01/15 K.Kojima [QC#23214,MOD]
        if (this.errMsgList.size() > 0) {
            return cpoUpdApiReqPMsg;
        }

        int dsOrdPosnNum = 0;
        int dsCpoLineNum = 0;

        // Line Config: Config
        dsOrdPosnNum++;
        setConfigUpdApiReqDtlPMsg(cpoUpdApiReqPMsg, svcConfigMstrTmsg, billToCustTMsg, shipToCustTMsg, dsOrdPosnNum);

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            // Line Config: Line
            dsCpoLineNum++;
            final NWZC157002PMsg lineMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            setCpoUpdApiReqDtlPMsg(cpoUpdApiReqPMsg, lineMsg, prcApiPMsg, carrierApiPMsg, svcMachMstrTMsg, dsContrTMsg, shipToCustTMsg, svcSplyOrdTpRelnTMsg, dsOrdPosnNum, dsCpoLineNum);
            // END 2018/01/15 K.Kojima [QC#23214,MOD]
            if (this.errMsgList.size() > 0) {
                return cpoUpdApiReqPMsg;
            }
            setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, prcApiPMsg, i, dsCpoLineNum);
            if (this.errMsgList.size() > 0) {
                return cpoUpdApiReqPMsg;
            }
        }

        // Delivery Info
        setCpoUpdApiReqDeliveryInfo(cpoUpdApiReqPMsg, svcMachMstrTMsg);

        boolean callApiFlg = true;
        if (cpoUpdApiReqPMsg.A.getValidCount() == 0) {
            callApiFlg = false;
        }

        // Call DS CPO Update API
        if (callApiFlg) {
            callApi(cpoUpdApiReqPMsg);
        } else {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_CPO_UPDT_API, SER_NUM_TXT, svcMachMstrTMsg.serNum.getValue() }));
        }
        return cpoUpdApiReqPMsg;
    }

    // START 2018/01/15 K.Kojima [QC#23255,MOD]
    // private void setCpoUpdApiReqPMsg(String commitMode, NWZC150001PMsg pMsg, NWZC157001PMsg prcApiPMsg, NMZC611001PMsg carrierApiPMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, BILL_TO_CUSTTMsg billToCustTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg,
    //         SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg) {
    private void setCpoUpdApiReqPMsg(String commitMode, NWZC150001PMsg pMsg, NWZC157001PMsg prcApiPMsg, NMZC611001PMsg carrierApiPMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, BILL_TO_CUSTTMsg billToCustTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg,
            SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg, DS_CONTRTMsg dsContrTMsg) {
    // END 2018/01/15 K.Kojima [QC#23255,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, commitMode);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.usrId, BATCH_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_APP_ID);

        pMsg.cpoOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, svcSplyOrdTpRelnTMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, svcSplyOrdTpRelnTMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, svcSplyOrdTpRelnTMsg.dsOrdRsnCd);
        DS_ORD_TPTMsg dsOrdTpTMsg = getDsOrdTp(svcSplyOrdTpRelnTMsg.dsOrdTpCd.getValue());
        if (dsOrdTpTMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTpCd, dsOrdTpTMsg.cpoOrdTpCd);
        }
        pMsg.custIssPoNum.clear();
        pMsg.custIssPoDt.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        ZYPEZDItemValueSetter.setValue(pMsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, billToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, billToCustTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, shipToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, billToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, billToCustTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToFirstRefCmntTxt, getDeliveryInstallPsnName(svcMachMstrTMsg.svcMachMstrPk.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, this.salesDate);
        pMsg.addRsdDt.clear();
        // START 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
        
        //FRT_CONDTMsg frtCondTMsg = getFrtCond();
        Map<String, String> frtParam = new HashMap<String, String>();
        setDefFrtCond(frtParam, svcSplyOrdTpRelnTMsg, svcMachMstrTMsg);
        ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, frtParam.get("shpgSvcLvlCd"));
        // END 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]

        FRT_CONDTMsg frtCondTMsg = getFrtCond(frtParam.get("frtCondCd"));
        // END 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        if (frtCondTMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.addFrtChrgMethCd, frtCondTMsg.frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addFrtChrgToCd, frtCondTMsg.frtChrgToCd);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, shipToCustTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, shipToCustTMsg.locNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, shipToCustTMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, shipToCustTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, shipToCustTMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, shipToCustTMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, shipToCustTMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, shipToCustTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, shipToCustTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, shipToCustTMsg.provNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, shipToCustTMsg.postCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, shipToCustTMsg.ctryCd);
        if (hasValue(shipToCustTMsg.ctryCd)) {
            CTRYTMsg ctryTMsg = getCtry(shipToCustTMsg.ctryCd.getValue());
            if (ctryTMsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, ctryTMsg.ctryNm);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, shipToCustTMsg.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, shipToCustTMsg.scdRefCmntTxt);

        pMsg.addPmtTermCashDiscCd.clear();
        // START 2018/07/04 T.Wada [QC#23726, MOD]
        //ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, carrierApiPMsg.dsAcctNum);
        if (ZYPCommonFunc.hasValue(frtCondTMsg.frtCondCd) && FRT_COND.COLLECT.equals(frtCondTMsg.frtCondCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, carrierApiPMsg.dsCarrAcctNum);
        }
        // END 2018/07/04 T.Wada [QC#23726, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.ordSgnDt, this.salesDate);
        // START 2018/01/15 K.Kojima [QC#23255,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, getSalesRep(svcMachMstrTMsg.finBrCd.getValue()));
        // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, getSalesRep(svcMachMstrTMsg.finBrCd.getValue(), dsContrTMsg.dsContrPk.getValue()));
        // START 2018/09/27 K.Kitachi [QC#26260, DEL]
//        ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, getSalesRep(svcMachMstrTMsg.finBrCd.getValue(), dsContrTMsg.dsContrPk.getValue(), svcMachMstrTMsg.svcByLineBizTpCd.getValue()));
        // END 2018/09/27 K.Kitachi [QC#26260, DEL]
        // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        // END 2018/01/15 K.Kojima [QC#23255,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, this.salesDate);
        pMsg.negoDealAmt.clear();

        if (prcApiPMsg.NWZC157002PMsg.getValidCount() != 0) {
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);
            ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, linePrcApiPMsg.prcCatgCd);
            NWZC157001PMsg flPrcApiListPMsg = callPricingApiForGetFloorPrcList(svcMachMstrTMsg, svcSplyOrdTpRelnTMsg);
            if (!checkPrcApiParam(flPrcApiListPMsg)) {
                this.errMsgList.add(S21MessageFunc.clspGetMessage(NSZM0407E, new String[] {MSG_ERR_PRC_API, SER_NUM_TXT, svcMachMstrTMsg.serNum.getValue() }));
                return;
            }
            if (flPrcApiListPMsg.xxPrcList.getValidCount() != 0) {
                NWZC157001_xxPrcListPMsg prcListPMsg = flPrcApiListPMsg.xxPrcList.no(0);
                ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, prcListPMsg.prcCatgCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, frtCondTMsg.frtCondCd);

        // START 2018/07/02 T.Wada [QC#23726, MOD]
        // pMsg.carrSvcLvlCd.clear();

        String shpgSvcLvlCd = null;
        String carrCd = null;
        String carrSvcLvlCd = null;
        if (carrierApiPMsg != null && carrierApiPMsg.vndCd_O != null) {
            carrCd = carrierApiPMsg.vndCd_O.getValue();
        }
        shpgSvcLvlCd = (String)frtParam.get("shpgSvcLvlCd");
        if (ZYPCommonFunc.hasValue(shpgSvcLvlCd) && (ZYPCommonFunc.hasValue(carrCd))) {
            carrSvcLvlCd = getCarrSvcLvl(shpgSvcLvlCd, carrCd) ;
        }
        if (ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, carrSvcLvlCd);
        } else {
            pMsg.carrSvcLvlCd.clear();
        }
        if(ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            boolean checkCustCarrSvcLvlRel = callCheckCustCarrSvcLvlRelation(svcSplyOrdTpRelnTMsg, svcMachMstrTMsg, carrSvcLvlCd, frtCondTMsg);
            if (checkCustCarrSvcLvlRel) {
                this.errMsgList.add(S21MessageFunc.clspGetMessage("NPAM1365E", new String[] {MSG_ERR_PRC_API, SER_NUM_TXT, svcMachMstrTMsg.serNum.getValue() }));
                return;
            }
        }
        // END 2018/07/02 T.Wada [QC#23726, MOD]

        pMsg.dsCrCardPk.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
    }

    private NWZC157001PMsg callPricingApiForGetFloorPrcList(SVC_MACH_MSTRTMsg svcMachMstrTMsg, SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.FLOOR_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, svcSplyOrdTpRelnTMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, svcSplyOrdTpRelnTMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, svcMachMstrTMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);
        return prcApiPMsg;
    }

    // START 2018/07/04 T.Wada [QC#23726, ADD]
    private boolean callCheckCustCarrSvcLvlRelation(SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String carrSvcLvlCd, FRT_CONDTMsg frtCondTMsg) {
        String dsOrdCatgCd = null;
        String dsAcctNum = null;
        String frtCondCd = null;
        if (ZYPCommonFunc.hasValue(svcSplyOrdTpRelnTMsg.dsOrdCatgCd)) {
            dsOrdCatgCd = svcSplyOrdTpRelnTMsg.dsOrdCatgCd.getValue();
        }
        if (ZYPCommonFunc.hasValue(svcMachMstrTMsg.curLocAcctNum)) {
            dsAcctNum = svcMachMstrTMsg.curLocAcctNum.getValue();
        }
        if (ZYPCommonFunc.hasValue(frtCondTMsg.frtCondCd)) {
            frtCondCd = frtCondTMsg.frtCondCd.getValue();
        }
        String dsBizAreaCd = getDsBizAreaCd(dsOrdCatgCd);
        return NWXC150001DsCheck.checkCustCarrSvcLvlRelation(
                this.glblCmpyCd
                ,dsBizAreaCd
                ,dsAcctNum//
                ,carrSvcLvlCd//
                ,frtCondCd );    
    }
    // END 2018/07/04 T.Wada [QC#23726, ADD]

    private boolean checkPrcApiParam(NWZC157001PMsg prcApiPMsg) {
        if (prcApiPMsg == null) {
            return true;
        }
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            return false;
        }
        // set Calc Base
        NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(0);
        if (S21ApiUtil.isXxMsgId(prcLinePMsg)) {
            return false;
        }
        // set Calc Base
        NWZC157004PMsg prcLineSumPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
        if (S21ApiUtil.isXxMsgId(prcLineSumPMsg)) {
            return false;
        }
        return true;
    }

    private void setConfigUpdApiReqDtlPMsg(NWZC150001PMsg pMsg, SVC_CONFIG_MSTRTMsg svcConfigMstrTmsg, BILL_TO_CUSTTMsg billToCustTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg, int dsOrdPosnNum) {

        final int dtlPMsgCount = pMsg.cpoConfig.getValidCount();
        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(dtlPMsgCount);

        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
        cpoConfigPMsg.dsCpoConfigPk.clear();
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, Integer.toString(dsOrdPosnNum));
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.NEW);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.svcConfigMstrPk, svcConfigMstrTmsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, svcConfigMstrTmsg.mdlId);
        DS_MDLTMsg dsMdlTMsg = getDsMdl(svcConfigMstrTmsg.mdlId.getValue());
        if (dsMdlTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, dsMdlTMsg.mdlDescTxt);
        }
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, billToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, billToCustTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, shipToCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, shipToCustTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, shipToCustTMsg.locNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, shipToCustTMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, shipToCustTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, shipToCustTMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, shipToCustTMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, shipToCustTMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, shipToCustTMsg.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, shipToCustTMsg.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, shipToCustTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, shipToCustTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, shipToCustTMsg.provNm);
        if (hasValue(shipToCustTMsg.ctryCd)) {
            CTRYTMsg ctryTMsg = getCtry(shipToCustTMsg.ctryCd.getValue());
            if (ctryTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, ctryTMsg.ctryNm);
            }
        }
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, shipToCustTMsg.postCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, shipToCustTMsg.ctryCd);

        pMsg.cpoConfig.setValidCount(dtlPMsgCount + 1);
    }

    private void setCpoUpdApiReqDtlPMsg(NWZC150001PMsg pMsg, NWZC157002PMsg lineMsg, NWZC157001PMsg prcApiPMsg, NMZC611001PMsg carrierApiPMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, DS_CONTRTMsg dsContrTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg,
            SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTMsg, int dsOrdPosnNum, int dsCpoLineNum) {

        final int dtlPMsgCount = pMsg.A.getValidCount();

        final NWZC150001_APMsg dtlPMsg = pMsg.A.no(dtlPMsgCount);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_NEW);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, ZYPCommonFunc.leftPad(String.valueOf(dtlPMsgCount + 1), NSAB079001Constant.NUM_THREE, "0"));
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, DEF_LINE_SUB_NUM);
        DS_ORD_TPTMsg dsOrdTpTMsg = getDsOrdTp(svcSplyOrdTpRelnTMsg.dsOrdTpCd.getValue());
        if (dsOrdTpTMsg != null) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoOrdTpCd_A1, dsOrdTpTMsg.cpoOrdTpCd);
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, lineMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, lineMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_A1, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, shipToCustTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_A1, shipToCustTMsg.locNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_A1, shipToCustTMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_A1, shipToCustTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_A1, shipToCustTMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_A1, shipToCustTMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_A1, shipToCustTMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_A1, shipToCustTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_A1, shipToCustTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_A1, shipToCustTMsg.provNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_A1, shipToCustTMsg.postCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_A1, shipToCustTMsg.ctryCd);
        if (hasValue(shipToCustTMsg.ctryCd)) {
            CTRYTMsg ctryTMsg = getCtry(shipToCustTMsg.ctryCd.getValue());
            if (ctryTMsg != null) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_A1, ctryTMsg.ctryNm);
            }
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, shipToCustTMsg.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_A1, shipToCustTMsg.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_A1, dsContrTMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rddDt_A1, this.salesDate);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.uomCpltFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd_A1, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtChrgMethCd_A1, pMsg.addFrtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtChrgToCd_A1, pMsg.addFrtChrgToCd);
        // START 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.shpgSvcLvlCd_A1, SHPG_SVC_LVL.GROUND_SERVICE);
        Map<String, String> frtParam = new HashMap<String, String>();
        setDefFrtCond(frtParam, svcSplyOrdTpRelnTMsg, svcMachMstrTMsg);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shpgSvcLvlCd_A1, frtParam.get("shpgSvcLvlCd"));
        // END 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        // START 2018/01/15 K.Kojima [QC#23255,MOD]
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, getSalesRep(svcMachMstrTMsg.finBrCd.getValue()));
        // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, getSalesRep(svcMachMstrTMsg.finBrCd.getValue(), dsContrTMsg.dsContrPk.getValue()));
        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, getSalesRep(svcMachMstrTMsg.finBrCd.getValue(), dsContrTMsg.dsContrPk.getValue(), svcMachMstrTMsg.svcByLineBizTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, getSalesRep(svcMachMstrTMsg.finBrCd.getValue(), dsContrTMsg.dsContrPk.getValue(), svcMachMstrTMsg.svcByLineBizTpCd.getValue(), svcMachMstrTMsg.sldByLineBizTpCd.getValue()));
        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        // END 2018/01/15 K.Kojima [QC#23255,MOD]
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.manPrcFlg_A1, ZYPConstant.FLG_OFF_N);

        // START 2018/07/02 T.Wada [QC#23726, MOD]
        //dtlPMsg.carrSvcLvlCd_A1.clear();
        String shpgSvcLvlCd = null;
        String carrCd = null;
        String carrSvcLvlCd = null;
        if (carrierApiPMsg != null && carrierApiPMsg.vndCd_O != null) {
            carrCd = carrierApiPMsg.vndCd_O.getValue();
        }
        shpgSvcLvlCd = (String)frtParam.get("shpgSvcLvlCd");
        if (ZYPCommonFunc.hasValue(shpgSvcLvlCd) && (ZYPCommonFunc.hasValue(carrCd))) {
            carrSvcLvlCd = getCarrSvcLvl(shpgSvcLvlCd, carrCd) ;
        }
        if (ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, carrSvcLvlCd);
        } else {
            dtlPMsg.carrSvcLvlCd_A1.clear();
        }
        // END 2018/07/02 T.Wada [QC#23726, MOD]
        // START 2018/07/04 T.Wada [QC#23726, MOD]
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.carrAcctNum_A1, carrierApiPMsg.dsAcctNum);
        if (FRT_COND.COLLECT.equals(frtParam.get("frtCondCd"))) {
            ZYPEZDItemValueSetter.setValue(dtlPMsg.carrAcctNum_A1, carrierApiPMsg.dsCarrAcctNum);
        }
        // END 2018/07/04 T.Wada [QC#23726, MOD]
        ZYPEZDItemValueSetter.setValue(dtlPMsg.setItemShipCpltFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_A1, svcMachMstrTMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_01);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_02);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_03);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_04);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_05);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_06);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, Integer.toString(dsOrdPosnNum));

        BigDecimal unitNetWt = getUnitNetWt(lineMsg.mdseCd.getValue());
        if (null == unitNetWt) {
            this.errMsgList.add(S21MessageFunc.clspGetMessage(ZZM9037E));
            return;
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, unitNetWt);

        // START 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, FRT_COND.NO_CHARGE);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, frtParam.get("frtCondCd"));
        // START 2018/02/19 U.Kim [QC#20297(Sol#379), MOD]
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrNum_A1, dsContrTMsg.dsContrNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineNum_A1, new BigDecimal(dsCpoLineNum));
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, svcSplyOrdTpRelnTMsg.dsOrdLineCatgCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, pMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, this.salesDate);

        if (prcApiPMsg.NWZC157002PMsg.getValidCount() != 0) {
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, linePrcApiPMsg.prcCatgCd);
        }
        // set Field Amount
        for (int k = 0; k < prcApiPMsg.NWZC157004PMsg.getValidCount(); k++) {
            NWZC157004PMsg nwzc157004PMsg = prcApiPMsg.NWZC157004PMsg.no(k);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotBaseAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotBaseAmt.getValue(), dtlPMsg.xxTotBaseAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotDiscAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotDiscAmt.getValue(), dtlPMsg.xxTotDiscAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotSpclPrcAmt.getValue(), dtlPMsg.xxTotSpclPrcAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotNetDiscAmt.getValue(), dtlPMsg.xxTotNetDiscAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetPrcAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotNetPrcAmt.getValue(), dtlPMsg.xxTotNetPrcAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotFrtAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotFrtAmt.getValue(), dtlPMsg.xxTotFrtAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotSpclChrgAmt.getValue(), dtlPMsg.xxTotSpclChrgAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotTaxAmt.getValue(), dtlPMsg.xxTotTaxAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxSlsAmt_A1, sumBigDecimal(nwzc157004PMsg.xxSlsAmt.getValue(), dtlPMsg.xxSlsAmt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotAmt.getValue(), dtlPMsg.xxTotAmt_A1.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.loanBalQty_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_A1, ZYPConstant.FLG_OFF_N);
        // START 2018/01/16 K.Kojima [QC#21855,MOD]
        // ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_A1, getSvcMachMstrPk(dsContrTMsg.dsContrCatgCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue()));
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_A1, svcMachMstrTMsg.svcMachMstrPk);
        // END 2018/01/16 K.Kojima [QC#21855,MOD]
        pMsg.A.setValidCount(dtlPMsgCount + 1);
    }

    private void setCpoUpdApiReqPriceCalcBase(NWZC150001PMsg cpoUpdApiReqPMsg, NWZC157001PMsg prcApiPMsg, int dtlIdx, int dsCpoLineNum) {

        int priceListPMsgCount = cpoUpdApiReqPMsg.priceList.getValidCount();
        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(dtlIdx);

        for (int j = 0; j < linePrcApiPMsg.NWZC157003PMsg.getValidCount() && priceListPMsgCount < cpoUpdApiReqPMsg.priceList.length(); j++) {
            NWZC150001_priceListPMsg priceListPMsg = cpoUpdApiReqPMsg.priceList.no(priceListPMsgCount);
            NWZC157003PMsg nwzc157003PMsg = linePrcApiPMsg.NWZC157003PMsg.no(j);

            if (!CONFIG_CATG.OUTBOUND.equals(nwzc157003PMsg.configCatgCd.getValue())) {
                continue;
            }

            priceListPMsg.ordPrcCalcBasePk.clear();
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(dsCpoLineNum), NSAB079001Constant.NUM_THREE, "0"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, DEF_LINE_SUB_NUM);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, nwzc157003PMsg.prcCondTpCd);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, nwzc157003PMsg.prcDtlGrpCd);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, nwzc157003PMsg.prcJrnlGrpCd);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, nwzc157003PMsg.prcCondManEntryFlg);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, nwzc157003PMsg.prcCondManAddFlg);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, nwzc157003PMsg.prcCondManDelFlg);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, nwzc157003PMsg.pkgUomCd);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, nwzc157003PMsg.prcCondUnitCd);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, nwzc157003PMsg.prcCalcMethCd);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, nwzc157003PMsg.autoPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, nwzc157003PMsg.maxPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, nwzc157003PMsg.minPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, nwzc157003PMsg.manPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, nwzc157003PMsg.calcPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, nwzc157003PMsg.unitPrcAmt);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, nwzc157003PMsg.dsMdsePrcPk);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, nwzc157003PMsg.specCondPrcPk);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, nwzc157003PMsg.frtPerWtPk);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, nwzc157003PMsg.autoPrcCcyCd);
            // START 2018/09/19  [QC#9700,ADD]
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRuleApplyFlg, nwzc157003PMsg.prcRuleApplyFlg);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRulePrcdPk, nwzc157003PMsg.prcRulePrcdPk);
            // END 2018/09/19  [QC#9700,ADD]

            priceListPMsgCount++;
        }
        cpoUpdApiReqPMsg.priceList.setValidCount(priceListPMsgCount);
    }

    private void setCpoUpdApiReqDeliveryInfo(NWZC150001PMsg cpoUpdApiReqPMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        // START 2018/02/16 U.Kim [QC#20297(Sol#379), ADD]
        String delyAddlCmntTxt = null;
        // END 2018/02/16 U.Kim [QC#20297(Sol#379), ADD]
        NWZC150001_cpoDlvyInfoListPMsg dlvyInfo = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(0);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
        // START 2018/02/16 U.Kim [QC#20297(Sol#379), MOD]
        // ZYPEZDItemValueSetter.setValue(dlvyInfo.delyAddlCmntTxt, getDeliveryInstallPsnName(svcMachMstrTMsg.svcMachMstrPk.getValue()));
        String delyAddlCmntTxtForConcat = getDeliveryInstallPsnName(svcMachMstrTMsg.svcMachMstrPk.getValue());
        String defShpgCmtForConcat = setDefShpgCmt(svcMachMstrTMsg.svcByLineBizTpCd.getValue(), cpoUpdApiReqPMsg.dsOrdCatgCd.getValue(), svcMachMstrTMsg.curLocNum.getValue());
        if (delyAddlCmntTxtForConcat != null && defShpgCmtForConcat != null) {
            delyAddlCmntTxt = ZYPCommonFunc.concatString(delyAddlCmntTxtForConcat, SPACE, defShpgCmtForConcat);
        } else if (delyAddlCmntTxtForConcat == null) {
            delyAddlCmntTxt = defShpgCmtForConcat;
        } else {
            delyAddlCmntTxt = delyAddlCmntTxtForConcat;
        }
        ZYPEZDItemValueSetter.setValue(dlvyInfo.delyAddlCmntTxt, delyAddlCmntTxt);
        // END 2018/02/16 U.Kim [QC#20297(Sol#379), MOD]
        cpoUpdApiReqPMsg.cpoDlvyInfoList.setValidCount(1);
    }

    private void callApi(NWZC150001PMsg cpoUpdApiMsg) {

        /**************************************************
         * NWZC150001 : DS CPO Update API
         **************************************************/
        final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
        final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        new NWZC150001().execute(cpoUpdApiMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03, ONBATCH_TYPE.BATCH);

        setCpoUpdateApiErrMsg(cpoUpdApiMsg);
        setCpoUpdateApiDtlErrMsg(cpoUpdApiMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03);
    }

    private void setCpoUpdateApiErrMsg(NWZC150001PMsg cpoUpdApiMsg) {
        if (S21ApiUtil.isXxMsgId(cpoUpdApiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(cpoUpdApiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                errMsgList.add(S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
            }
        }
    }

    private void setCpoUpdateApiDtlErrMsg(NWZC150001PMsg cpoUpdApiMsg, List<NWZC150002PMsg> cpoUpdApiOutMsgList, List<NWZC150003PMsg> cpoUpdApiInMsgList) {
        for (NWZC150002PMsg pMsg : cpoUpdApiOutMsgList) {
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    errMsgList.add(S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray()));
                }
            }
        }
    }
    // START 2018/02/28 U.Kim [QC#23296, ADD]
    private void setErroMsgForDfltApi(NMZC611001PMsg dfltMsg){
        if (S21ApiUtil.isXxMsgId(dfltMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(dfltMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                this.errMsgForDfltApi = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
        }
    }
    private void setErrMsgForPrcApi(NWZC157001PMsg prcApiMsg){
        if (S21ApiUtil.isXxMsgId(prcApiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                this.errMsgForPrcApi = S21MessageFunc.clspGetMessage(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
        }
    }

    // END 2018/02/28 U.Kim [QC#23296, ADD]

    /** Send Email */
    private void sendMailForError(String serNum, String message) {
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);
        String newLine = System.getProperty("line.separator");

        templateErr.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        templateErr.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        templateErr.setTemplateParameter(MAIL_TEMPLATE_KEY_SER_NUM, serNum);
        if (message == null) {
            message = createEmailParamMsg();
        }
        templateErr.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(this.fromAddrList.get(0));
        mail.setToAddressList(this.addrToList);
        mail.setMailTemplate(this.templateErr);
        mail.postMail();

        StringBuilder logBuf = new StringBuilder();
        logBuf.append(newLine);
        logBuf.append("==================== Skip or Error Data ====================");
        logBuf.append(newLine);
        // START 2018/03/01 U.Kim [QC#23296, ADD]
        logBuf.append(TM_TXT + COLON + currentTime);
        logBuf.append(newLine);
        logBuf.append(SER_NUM_TXT_LOG + COLON + serNum);
        logBuf.append(newLine);
        // END 2018/03/01 U.Kim [QC#23296, ADD]
        logBuf.append(message);
        logBuf.append(newLine);
        logBuf.append("============================================================");
        S21InfoLogOutput.println(logBuf.toString());
    }

    /**
     * Mode<br>
     * 2 : Customer Mismatch Mail<br>
     * 3 : Duplicate Supply Order Mail<br>
     */
    private void sendMailForCustMismatchOrDuplicateSupplyOrder(int sendMailMode, String serNum, String message, SVC_MACH_MSTRTMsg svcMachMstrTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg, String partInfo, String cpoOrdNum) {
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);
        String newLine = System.getProperty("line.separator");

        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);

        if (sendMailMode == SEND_MAIL_MODE_CUST_MIS) {
            templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_ERR_TP, MSG_MAIL_TITL_CUST_MIS);
            templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_MAIN_ERR_TP, MSG_MAIL_TITL_CUST_MIS);
        } else {
            templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_ERR_TP, MSG_MAIL_TITL_DUPL_ORD);
            templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_MAIN_ERR_TP, MSG_MAIL_TITL_DUPL_ORD);
            templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_ORD_NUM, cpoOrdNum);
        }

        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_CUST_NM, shipToCustTMsg.locNm.getValue());
        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_CUST_ID, shipToCustTMsg.sellToCustCd.getValue());
        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_ADDR, shipToCustTMsg.firstLineAddr.getValue() + " " + shipToCustTMsg.scdLineAddr.getValue() + " " + shipToCustTMsg.thirdLineAddr.getValue() + " "
                + shipToCustTMsg.frthLineAddr.getValue());

        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_MDL_NM, getMdse(svcMachMstrTMsg.mdseCd.getValue()).mdseFmlNm.getValue());
        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_SER_NUM, serNum);
        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_CON_NM, getMdse(partInfo).mdseNm.getValue());
        templateMisDup.setTemplateParameter(MAIL_TEMPLATE_KEY_PART_NUM, partInfo);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(this.fromAddrList.get(0));
        mail.setToAddressList(this.addrToList);
        mail.setMailTemplate(this.templateMisDup);
        mail.postMail();

        StringBuilder logBuf = new StringBuilder();
        logBuf.append(newLine);
        logBuf.append("==================== Skip or Error Data ====================");
        logBuf.append(newLine);
        logBuf.append(message);
        logBuf.append(newLine);
        logBuf.append("============================================================");
        S21InfoLogOutput.println(logBuf.toString());
    }

    /** Send Email */
    private void sendMailForSpecialHanding(String serNum, String message, SVC_MACH_MSTRTMsg svcMachMstrTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg, String partInfo, String cpoOrderNum) {
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);
        String newLine = System.getProperty("line.separator");

        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_Q_NUM, cpoOrderNum);
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_CUST_NM, shipToCustTMsg.locNm.getValue());
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_CUST_ID, shipToCustTMsg.sellToCustCd.getValue());
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_ADDR, shipToCustTMsg.firstLineAddr.getValue() + " " + shipToCustTMsg.scdLineAddr.getValue() + " " + shipToCustTMsg.thirdLineAddr.getValue() + " "
                + shipToCustTMsg.frthLineAddr.getValue());
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_MDL_NM, getMdse(svcMachMstrTMsg.mdseCd.getValue()).mdseFmlNm.getValue());
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_SER_NUM, serNum);
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_CON_NM, getMdse(partInfo).mdseNm.getValue());
        templateSpecHand.setTemplateParameter(MAIL_TEMPLATE_KEY_PART_NUM, partInfo);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(this.fromAddrList.get(0));
        mail.setToAddressList(this.addrToList);
        mail.setMailTemplate(this.templateSpecHand);
        mail.postMail();

        StringBuilder logBuf = new StringBuilder();
        logBuf.append(newLine);
        logBuf.append("==================== Skip or Error Data ====================");
        logBuf.append(newLine);
        logBuf.append(message);
        logBuf.append(newLine);
        logBuf.append("============================================================");
        S21InfoLogOutput.println(logBuf.toString());
    }

    private SVC_MACH_MSTRTMsgArray getSvcMachMstr(String serNum) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("serNum01", serNum);
        return (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    private SVC_CONFIG_MSTRTMsg getSvcConfigMstr(BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(BigDecimal svcConfigMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setSQLID("005");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        tMsg.setConditionValue("svcMachTpCd01", SVC_MACH_TP.MACHINE);
        SVC_MACH_MSTRTMsgArray list = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (list == null || list.getValidCount() != 1) {
            return null;
        }
        return list.no(0);
    }

    private Map<String, Object> getDsContrDtlSearch(BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);
        queryParam.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        queryParam.put("salesDate", salesDate);
        queryParam.put("dsContrTpWrty", DS_CONTR_TP.WARRANTY);
        // START 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        queryParam.put("svcCovFeatCd", SVC_COV_FEAT.SPLY_INCL);
        queryParam.put("defEffThruDt", DEF_EFF_THRU_DT);
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        Map<String, Object> ssmResult = (Map<String, Object>) this.ssmBatClient.queryObject("getDsContrDtl", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return null;
    }

    private Map<String, Object> getDsContrDtlFleet(BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);
        queryParam.put("dsContrDtlStsOrdr", DS_CONTR_DTL_STS.ORDER);
        queryParam.put("salesDate", salesDate);
        queryParam.put("dsContrTpWrty", DS_CONTR_TP.WARRANTY);
        Map<String, Object> ssmResult = (Map<String, Object>) this.ssmBatClient.queryObject("getDsContrDtlFleet", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return null;
    }

    private List<Map<String, Object>> getTonerInfo(BigDecimal svcConfigMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("imgSplyTpCd", IMG_SPLY_TP.TONER);
        queryParam.put("one", NSAB079001Constant.NUM_ONE);
        queryParam.put("eight", NSAB079001Constant.NUM_EIGHT);
        List<Map<String, Object>> ssmResultList = (List<Map<String, Object>>) this.ssmBatClient.queryObjectList("getTonerInfo", queryParam);
        if (ssmResultList != null && ssmResultList.size() > 0) {
            return ssmResultList;
        }
        return null;
    }

    private String getSupplyOrderInfo(BigDecimal svcMachMstrPk, String mdseCd, String dsContrCatgCd, String dsContrNum, String ordTakeFlg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("salesDate", salesDate);
        queryParam.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        queryParam.put("ugwSplyOrdrLimitDay", ugwSplyOrdrLimitDay);
        queryParam.put("one", NSAB079001Constant.NUM_ONE);
        queryParam.put("eight", NSAB079001Constant.NUM_EIGHT);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("ordTakeMdseCd", mdseCd);
        queryParam.put("dsContrCatgCd", dsContrCatgCd);
        queryParam.put("dsContrNum", dsContrNum);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);
        queryParam.put("ordTakeFlg", ordTakeFlg);
        queryParam.put("cancelled", ORD_HDR_STS.CANCELLED);
        String ssmResult = (String) this.ssmBatClient.queryObject("getSupplyOrderInfo", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return null;
    }

    private BigDecimal getInstructions(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shipToCustCd", svcMachMstrTMsg.curLocNum.getValue());
        queryParam.put("supplies", DS_BIZ_AREA.SUPPLIES);
        // add start 2023/07/18 QC#61421
        queryParam.put("all", DS_BIZ_AREA.ALL);
        // add end 2023/07/18 QC#61421
        queryParam.put("salesDate", this.salesDate);
        queryParam.put("svcByLineBizTpCd", svcMachMstrTMsg.svcByLineBizTpCd.getValue());
        BigDecimal ssmResult = (BigDecimal) this.ssmBatClient.queryObject("getInstructions", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return BigDecimal.ZERO;
    }

    private String getDeliveryInstallPsnName(BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("svcMachMstrPk", svcMachMstrPk);
        queryParam.put("svcCtacTpCd", SVC_CTAC_TP.DELIVERY_CONTACT);
        String ssmResult = (String) this.ssmBatClient.queryObject("getDeliveryInstallPsnName", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return null;
    }

    // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//    private String getSalesRep(String coaBrCd, BigDecimal dsContrPk) {
    // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//    private String getSalesRep(String coaBrCd, BigDecimal dsContrPk, String svcLineBizCd) {
    private String getSalesRep(String coaBrCd, BigDecimal dsContrPk, String svcLineBizCd, String sldByLineBizTpCd) {
    // END 2018/09/10 K.Kitachi [QC#26260, MOD]
    // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        // START 2018/01/15 K.Kojima [QC#23255,ADD]
        Map<String, Object> queryParam2 = new HashMap<String, Object>();
        queryParam2.put("glblCmpyCd", this.glblCmpyCd);
        queryParam2.put("dsContrPk", dsContrPk);
        Map<String, Object> resultMap = (Map<String, Object>) this.ssmBatClient.queryObject("getHdrRepUseFlg", queryParam2);
        if (resultMap != null) {
            String tocCd = (String) resultMap.get("TOC_CD");
            String hdrRepUseFlg = (String) resultMap.get("HDR_REP_USE_FLG");
            if (ZYPCommonFunc.hasValue(tocCd) && ZYPCommonFunc.hasValue(hdrRepUseFlg) && ZYPConstant.FLG_ON_Y.equals(hdrRepUseFlg)) {
                return tocCd;
            }
        }
        // END 2018/01/15 K.Kojima [QC#23255,ADD]
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("coaBrCd", coaBrCd);
        // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
        queryParam.put("svcLineBizCd", svcLineBizCd);
        // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
        // START 2018/09/10 K.Kitachi [QC#26260, ADD]
        queryParam.put("sldByLineBizTpCd", sldByLineBizTpCd);
        // END 2018/09/10 K.Kitachi [QC#26260, ADD]
        String ssmResult = (String) this.ssmBatClient.queryObject("getSalesRep", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return null;
    }

    private boolean checkIsSupplyInclusive(String svcPrgmMdseCd) {

        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        CovTmplInfo tmplInfo = new CovTmplInfo();
        tmplInfo.setGlblCmpyCd(glblCmpyCd);
        tmplInfo.setSlsDt(salesDate);
        tmplInfo.setSvcPgmMdseCd(svcPrgmMdseCd);
        boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);

        return isSuplIncl;
    }

    private BILL_TO_CUSTTMsg getBillToCust(String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);

        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray == null || tMsgArray.length() == 0) {
            return new BILL_TO_CUSTTMsg();
        }
        return tMsgArray.no(0);
    }

    private DS_ORD_TPTMsg getDsOrdTp(String dsOrdTpCd) {
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);
        return (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    private SHIP_TO_CUSTTMsg getShipToCust(String shipToCustCd) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0) {
            return (SHIP_TO_CUSTTMsg) tMsgArray.get(0);
        }
        return null;
    }

    // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//    private SVC_SPLY_ORD_TP_RELNTMsg getSvcSplyOrdTpReln(String svcByLineBizTpCd) {
    private SVC_SPLY_ORD_TP_RELNTMsg getSvcSplyOrdTpReln(String svcByLineBizTpCd, String sldByLineBizTpCd) {
        SVC_SPLY_ORD_TP_RELNTMsg condition = new SVC_SPLY_ORD_TP_RELNTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        condition.setConditionValue("lineBizTpCd01", svcByLineBizTpCd);
        condition.setConditionValue("splyInclFlg01", ZYPConstant.FLG_ON_Y);
        condition.setConditionValue("laserPlusFlg01", ZYPConstant.FLG_OFF_N);
//        SVC_SPLY_ORD_TP_RELNTMsgArray outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
//        if (outTMsgArray.getValidCount() > 0) {
//            return outTMsgArray.no(0);
//        } else {
//            condition.setConditionValue("lineBizTpCd01", DEF_LINE_BIZ_CD);
//            outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
//            if (outTMsgArray.getValidCount() > 0) {
//                return outTMsgArray.no(0);
//            }
//        }
        SVC_SPLY_ORD_TP_RELNTMsgArray outTMsgArray = null;
        String convSldByLineBizTpCd = convLineBizTpToSvcLineBiz(sldByLineBizTpCd);
        if (hasValue(convSldByLineBizTpCd)) {
            condition.setConditionValue("lineBizTpCd01", convSldByLineBizTpCd);
            outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
            if (outTMsgArray.getValidCount() > 0) {
                return outTMsgArray.no(0);
            }
        }
        condition.setConditionValue("lineBizTpCd01", svcByLineBizTpCd);
        outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (outTMsgArray.getValidCount() > 0) {
            return outTMsgArray.no(0);
        }
        return new SVC_SPLY_ORD_TP_RELNTMsg();
    }
    // END 2018/09/10 K.Kitachi [QC#26260, MOD]

    /**
     * Call FRT_CONDTMsg
     * @return FRT_CONDTMsg
     */
    public FRT_CONDTMsg getFrtCond(String frtCondCd) {
        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, frtCondCd);
        return (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * Call CTRYTMsg
     * @param ctryCd String
     * @return CTRYTMsg
     */
    public CTRYTMsg getCtry(String ctryCd) {
        CTRYTMsg tMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, ctryCd);
        return (CTRYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * Call DS_MDLTMsg
     * @param mdlId BigDecimal
     * @return DS_MDLTMsg
     */
    public DS_MDLTMsg getDsMdl(BigDecimal mdlId) {
        DS_MDLTMsg tMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, mdlId);
        return (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * Call DS_CONTRTMsg
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContr(BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * Call DS_CONTR_DTLTMsg
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtl(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * Call MDSETMsg
     * @param mdseCd String
     * @return MDSETMsg
     */
    public MDSETMsg getMdse(String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        return (MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private BigDecimal sumBigDecimal(BigDecimal inItem, BigDecimal outItem) {
        if (hasValue(inItem) && hasValue(outItem)) {
            return outItem.add(inItem);
        }
        return outItem;
    }

    // START 2018/01/16 K.Kojima [QC#21855,DEL]
    // private BigDecimal getSvcMachMstrPk(String dsContrCatgCd, BigDecimal svcMachMstrPk) {
    //     if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
    //         return null;
    //     }
    //     return svcMachMstrPk;
    // }
    // END 2018/01/16 K.Kojima [QC#21855,DEL]

    private BigDecimal getUnitNetWt(String mdseCd) {
        String pkgUomCd = ZYPCodeDataUtil.getVarCharConstValue(PKG_UOM_FOR_PRC, glblCmpyCd);
        if (null == pkgUomCd) {
            pkgUomCd = PKG_UOM.EACH;
        }
        MDSE_STORE_PKGTMsg strePkgTMsg = new MDSE_STORE_PKGTMsg();
        strePkgTMsg.glblCmpyCd.setValue(glblCmpyCd);
        // get MDSE info
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(this.glblCmpyCd, mdseCd);
        strePkgTMsg.mdseCd.setValue(mdseTMsg.mdseCd.getValue());
        strePkgTMsg.pkgUomCd.setValue(pkgUomCd);

        strePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(strePkgTMsg);
        if (null == strePkgTMsg) {
            return null;
        }
        BigDecimal inPoundWt = strePkgTMsg.inPoundWt.getValue();
        BigDecimal inPountNetWt = inPoundWt.multiply(BigDecimal.ONE);

        return inPountNetWt;
    }

    public Map<String, Object> getToc(String slsRepOrSlsTeamTocCd) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", this.glblCmpyCd);
        mapParam.put("prcBaseDt", this.salesDate);
        mapParam.put("slsRepOrSlsTeamTocCd", slsRepOrSlsTeamTocCd);
        List<Map<String, Object>> ssmResList = (List<Map<String, Object>>) this.ssmBatClient.queryObjectList("selectToc", mapParam);
        if (ssmResList == null || ssmResList.isEmpty()) {
            return null;
        }
        return ssmResList.get(0);
    }

    private String createEmailParamMsg() {
        StringBuilder rtnMsg = new StringBuilder();
        for (String msg : this.errMsgList) {
            if (!hasValue(msg)) {
                continue;
            }

            if (hasValue(rtnMsg.toString())) {
                rtnMsg.append(S21Mail.LINE_SEPARATOR);
            }
            rtnMsg.append(msg);
        }
        return rtnMsg.toString();
    }

    // START 2018/02/16 U.Kim [QC#20297(Sol#379), ADD]
    public boolean setDefFrtInfoFromCustInfoApi(Map<String, String> frtParam, SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTmsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, svcSplyOrdTpRelnTmsg.dsOrdCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, svcSplyOrdTpRelnTmsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, svcMachMstrTMsg.curLocNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, svcMachMstrTMsg.curLocAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }

        if (hasValue(pMsg.ShippingDefaultInfoList.no(0).frtCondCd)) {
            frtParam.put("frtCondCd", pMsg.ShippingDefaultInfoList.no(0).frtCondCd.getValue());
        } else {
            return false;
        }

        if (hasValue(pMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd)) {
            frtParam.put("shpgSvcLvlCd", pMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd.getValue());
        } else {
            return false;
        }
        return true;
    }

    private String getDsBizAreaCd(String dsOrdCatgCd) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }

    public String setDefShpgCmt(String svcByLineBizTpCd, String dsOrdCatgCd, String curLocNum) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, getDsBizAreaCd(dsOrdCatgCd));
        ZYPEZDItemValueSetter.setValue(pMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, curLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);

        new NMZC610001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return null;
        }

        int count = pMsg.InstructionList.getValidCount();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < count; i++) {
            if (hasValue(pMsg.InstructionList.no(i).dsCustMsgTxt)) {
                if (i != 0) {
                    sb.append(NEW_LINE);
                }
                sb.append(pMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
                if (sb.length() > SHPG_CMT_TXT_LIMIT_SIZE) {
                    return sb.substring(0, SHPG_CMT_TXT_LIMIT_SIZE);
                }
            }
        }
        return sb.toString();
    }

    public void setDefFrtCond(Map<String, String> frtParam, SVC_SPLY_ORD_TP_RELNTMsg svcSplyOrdTpRelnTmsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        if (setDefFrtInfoFromCustInfoApi(frtParam, svcSplyOrdTpRelnTmsg, svcMachMstrTMsg)) {
            return;
        }

        // get Freight Terms
        Map<String, Object> defaultFreightTermInfo = getDefaultFreightTermInfo(svcSplyOrdTpRelnTmsg.dsOrdTpCd.getValue(), svcSplyOrdTpRelnTmsg.lineBizTpCd.getValue());

        if (defaultFreightTermInfo != null && defaultFreightTermInfo.get("FRT_COND_CD") != null && (String) defaultFreightTermInfo.get("DEF_SHPG_SVC_LVL_CD") != null) {
            frtParam.put("frtCondCd", (String) defaultFreightTermInfo.get("FRT_COND_CD"));
            frtParam.put("shpgSvcLvlCd", (String) defaultFreightTermInfo.get("DEF_SHPG_SVC_LVL_CD"));
        } else {
            frtParam.put("frtCondCd", FRT_COND.NO_CHARGE);
            frtParam.put("shpgSvcLvlCd", SHPG_SVC_LVL.GROUND_SERVICE);
        }
    }

    private Map<String, Object> getDefaultFreightTermInfo(String dsOrdTpCd, String lineBizTpCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dsOrdTpCd", dsOrdTpCd);
        queryParam.put("lineBizTpCd", lineBizTpCd);
        Map<String, Object> ssmResult = (Map<String, Object>) this.ssmBatClient.queryObject("getDefaultFreightTermInfo", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return null;
    }
    
    // END 2018/02/16 U.Kim [QC#20297(Sol#379), ADD]
    // START 2018/07/02 T.Wada [QC#23726, ADD]
    private String getCarrSvcLvl(String shpgSvcLvlCd, String carrCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        queryParam.put("carrCd", carrCd);
        String ssmResult = (String) this.ssmBatClient.queryObject("getCarrSvcLvl", queryParam);
        if (ssmResult != null) {
            return ssmResult;
        }
        return null;
    }
    // END 2018/07/02 T.Wada [QC#23726, ADD]

    // START 2018/09/10 K.Kitachi [QC#26260, ADD]
    private String convLineBizTpToSvcLineBiz(String lineBizTpCd) {
        if (!hasValue(lineBizTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("lineBizTpCd", lineBizTpCd);
        return (String) this.ssmBatClient.queryObject("convLineBizTpToSvcLineBiz", param);
    }
    // END 2018/09/10 K.Kitachi [QC#26260, ADD]
}
