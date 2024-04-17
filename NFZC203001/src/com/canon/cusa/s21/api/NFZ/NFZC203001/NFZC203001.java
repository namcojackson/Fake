/** <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre> */
package com.canon.cusa.s21.api.NFZ.NFZC203001;

import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_30;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_60;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_90;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_TYPE_0130;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_TYPE_3160;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_TYPE_6190;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_TYPE_CUR;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_TYPE_NUM;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_TYPE_OVER;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.AGE_TYPE_TOT;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.CLT_STMT_PHONE_LEN;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.CONTACT_INFO_BIGIN_PART;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.CTAC_PSN_NM_LEN;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.CUT_SIZE;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.EXTENSION_PDF;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.FMT_AMT;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.FMT_MMDDYYYY;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.FMT_MONTH_DD_YYYY;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.FMT_NLS_DATE_LANGUAGE;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.FMT_YYYYMMDD;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.MAX_LATE_DAYS;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.ML_FROM_ADDR_KEY;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.ML_TMPL_ID;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0001E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0002E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0003E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0004E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0005E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0006E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0010E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0013E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0014E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0015E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0016E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0017E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0018E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NFZM0020E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NZZM0003E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.NZZM0007E;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.STMT_RPT_BR_NUM_FOR_EML;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.STMT_RPT_ID;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.STR_RCPT_CHK_SEP;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.STR_SVC_CTAC_SEP;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.TYPE_ADJUSTMENT;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.TYPE_CASH;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.TYPE_CREDIT_MEMO;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.TYPE_INVOICE;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.TYPE_ON_ACCOUNT;
import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.TYPE_PAYMENT;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_STMT_CTRLTMsg;
import business.db.AR_STMT_DTL_INFOTMsg;
import business.db.AR_STMT_INFOTMsg;
import business.db.AR_STMT_INFO_WRKTMsg;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFZC203001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PAYER_CUST_MODE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21CUPSFileOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;


/**
 * <pre>
 * Create Statement API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Nakamura      Create          N/A.
 * 2016/03/08   Fujitsu         C.Naito         Update          QC#5049
 * 2016/03/15   Fujitsu         C.Naito         Update          QC#5380
 * 2016/03/15   Fujitsu         C.Naito         Update          QC#5499
 * 2016/03/16   Fujitsu         C.Naito         Update          QC#5515
 * 2016/03/16   Fujitsu         C.Naito         Update          QC#5534
 * 2016/03/16   Fujitsu         C.Naito         Update          QC#5537
 * 2016/03/16   Fujitsu         C.Naito         Update          QC#5379
 * 2016/03/24   Fujitsu         C.Naito         Update          QC#5706
 * 2016/03/30   Fujitsu         C.Naito         Update          QC#5752
 * 2016/03/31   Fujitsu         C.Naito         Update          QC#6273
 * 2016/04/01   Fujitsu         C.Naito         Update          QC#6075
 * 2016/04/01   Fujitsu         C.Naito         Update          QC#5570
 * 2016/04/06   Fujitsu         C.Naito         Update          QC#6630
 * 2016/04/06   Fujitsu         C.Naito         Update          QC#6634
 * 2016/04/06   Fujitsu         C.Naito         Update          QC#6636
 * 2016/04/11   Fujitsu         C.Naito         Update          QC#6809
 * 2016/04/12   Fujitsu         C.Naito         Update          QC#6447
 * 2016/04/14   Fujitsu         C.Naito         Update          QC#6976
 * 2016/05/09   Fujitsu         C.Naito         Update          QC#6596
 * 2016/07/21   Hitachi         K.Kojima        Update          QC#11483
 * 2017/06/09   Hitachi         J.Kim           Update          QC#18413
 * 2017/06/19   Hitachi         J.Kim           Update          QC#19258
 * 2018/04/23   Fujitsu         H.Ikeda         Update          QC#23882
 * 2018/04/23   Fujitsu         H.Ikeda         Update          QC#23882-1
 * 2018/05/15   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/07/11   Fujitsu         S.Ohki          Update          QC#27002
 * 2018/09/14   Fujitsu         H.Ikeda         Update          QC#27307
 * 2018/10/02   Fujitsu         H.Ikeda         Update          QC#25825
 * 2018/10/04   Fujitsu         H.Ikeda         Update          QC#24284
 * 2018/11/02   Fujitsu         H.Ikeda         Update          QC#25825
 * 2019/02/06   Fujitsu         H.Ikeda         Update          QC#30076
 * 2019/02/19   Fujitsu         S.Takami        Update          QC#30038
 * 2019/03/27   Fujitsu         H.Ikeda         Update          QC#30844
 * 2019/04/03   Fujitsu         H.Ikeda         Update          QC#31026
 * 2019/05/09   Fujitsu         H.Ikeda         Update          QC#50140
 * 2020/02/03   Fujitsu         Y.Matsui        Update          QC#55625
 * 2022/09/06   Hitachi         M.Hashino       Update          QC#60405
 * </pre>
 */
public class NFZC203001 extends S21ApiCommonBase {

    /** Mode : 01 - Print Date Creation */
    public static final String MODE_01_PRINT_DATA_CRAT = "01";

    /** Mode : 02 - Draft Date Creation */
    public static final String MODE_02_DRAFT_DATA_CRAT = "02";

    /** ssm Batch Client */
    private S21SsmBatchClient ssmClient;

    /** ssm LowLevel Client */
    private S21SsmLowLevelCodingClient ssmLowClient;

    /** Message Manager */
    private S21ApiMessageIdMgr msgIdMgr;

    // START 2017/06/09 J.Kim [QC#18413,ADD]
    /** Total Count */
    private int totalRecordCnt;

    /** Normal Count */
    private int normalRecordCnt;
    // END 2017/06/09 J.Kim [QC#18413,ADD]

    // START 2018/10/04 H.Ikeda [QC#24284,MOD]
    /** Commit Count */
    public static final int COMMIT_CNT = 1000;
    // END   2018/10/04 H.Ikeda [QC#24284,MOD]

    /** Constructor */
    public NFZC203001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLowClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * Create Statement API.
     * @param pMsgList List<NFZC203001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NFZC203001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NFZC203001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * Create Statement API.
     * @param pMsg NFZC203001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NFZC203001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        try {
            doProcess(pMsg, onBatchType);
        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        }
    }

    private void doProcess(NFZC203001PMsg pMsg, final ONBATCH_TYPE onBatchType) throws SQLException {

        S21EIPPrintingService service = new S21EIPPrintingService();
        String sysTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        PreparedStatement stmtBillTo = null;
        ResultSet rsltSetBillTo = null;

        try {
            this.msgIdMgr = new S21ApiMessageIdMgr();
            this.totalRecordCnt = 0;
            this.normalRecordCnt = 0;
            // START 2019/02/06 [QC#30076,ADD]
            boolean printFlg = false;
            // END   2019/02/06 [QC#30076,ADD]

            // Parameter Check.
            if (!checkParam(pMsg)) {
                return;
            }

            NFZC203001Bean bean = new NFZC203001Bean();

            bean.setArSubSysId(ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", pMsg.glblCmpyCd.getValue()));
            bean.setOfcFirstLineAddr(ZYPCodeDataUtil.getVarCharConstValue("AR_STMT_OFC_FIRST_LINE_ADDR", pMsg.glblCmpyCd.getValue()));
            bean.setOfcScdLineAddr(ZYPCodeDataUtil.getVarCharConstValue("AR_STMT_OFC_SCD_LINE_ADDR", pMsg.glblCmpyCd.getValue()));
            bean.setOfcThirdLineAddr(ZYPCodeDataUtil.getVarCharConstValue("AR_STMT_OFC_THIRD_LINE_ADDR", pMsg.glblCmpyCd.getValue()));
            bean.setPmtFirstCtacTxt(ZYPCodeDataUtil.getVarCharConstValue("AR_STMT_PMT_FIRST_CTAC_TXT", pMsg.glblCmpyCd.getValue()));
            bean.setPmtScdCtacTxt(ZYPCodeDataUtil.getVarCharConstValue("AR_STMT_PMT_SCD_CTAC_TXT", pMsg.glblCmpyCd.getValue()));
            bean.setPmtThirdCtacTxt(ZYPCodeDataUtil.getVarCharConstValue("AR_STMT_PMT_THIRD_CTAC_TXT", pMsg.glblCmpyCd.getValue()));
            bean.setPmtFrthCtacTxt(ZYPCodeDataUtil.getVarCharConstValue("AR_STMT_PMT_FRTH_CTAC_TXT", pMsg.glblCmpyCd.getValue()));
            bean.setNlsDtLang(ZYPCodeDataUtil.getVarCharConstValue("NFZC203001_NLS_DT_LANG", pMsg.glblCmpyCd.getValue()));

            // Get Account Date.
            bean.setArAcctDt(pMsg.slsDt.getValue());

            // START 2019/05/14 H.Ikeda [QC#23882, DEL]
            // Create Late Fee
//            if (MODE_01_PRINT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
//                NFZC204001PMsg nfzc2040Pmsg = new NFZC204001PMsg();
//                ZYPEZDItemValueSetter.setValue(nfzc2040Pmsg.glblCmpyCd, pMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(nfzc2040Pmsg.slsDt, pMsg.slsDt);
//                ZYPEZDItemValueSetter.setValue(nfzc2040Pmsg.arStmtDt, pMsg.arStmtDt);
//                ZYPEZDItemValueSetter.setValue(nfzc2040Pmsg.billToAcctNum, pMsg.billToAcctNum);
//                ZYPEZDItemValueSetter.setValue(nfzc2040Pmsg.billToCustCd, pMsg.billToCustCd);
//                NFZC204001 lateFeeApi = new NFZC204001();
//                lateFeeApi.execute(nfzc2040Pmsg, onBatchType);
//                if (nfzc2040Pmsg.xxMsgIdList.getValidCount() > 0) {
//                    for (int i = 0; i < nfzc2040Pmsg.xxMsgIdList.getValidCount(); i++) {
//                        this.msgIdMgr.addXxMsgId(nfzc2040Pmsg.xxMsgIdList.no(i).xxMsgId.getValue(), pMsg);
//                    }
//                    return;
//                }
//            }
            // END    2019/05/14 H.Ikeda [QC#23882, DEL]

            // Get Currency Code
            GLBL_CMPYTMsg glblCmpyTMsg = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(GLBL_CMPY.class, pMsg.glblCmpyCd.getValue(), pMsg.glblCmpyCd.getValue());
            if (glblCmpyTMsg == null) {
                this.msgIdMgr.addXxMsgId(NFZM0014E, pMsg);
                return;
            }
            bean.setCcyCd(glblCmpyTMsg.stdCcyCd.getValue());
            bean.setCmpyNm(glblCmpyTMsg.glblCmpyNm.getValue());

            // Get Currency Sign Text
            CCYTMsg ccyTMsg = (CCYTMsg) ZYPCodeDataUtil.findByCode(CCY.class, pMsg.glblCmpyCd.getValue(), bean.getCcyCd());
            if (ccyTMsg == null) {
                this.msgIdMgr.addXxMsgId(NFZM0015E, pMsg);
                return;
            }
            bean.setCcySgnTxt(ccyTMsg.ccySgnTxt.getValue());

            // [QC#5515] UPDATE start
            String arStmtIssDay = new String();

            // [QC#5379] UPDATE start
            Map<String, Object> arStmtCtrlMap = new HashMap<String, Object>();
            // [QC#5379] UPDATE end

            /** Mode:01 Daily Batch **/
            if (MODE_01_PRINT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {

                // Get Target AR_STMT_CTRL
                arStmtCtrlMap = getArStmtIssCycleCd(pMsg);
                if (arStmtCtrlMap == null) {
                    // Mode:01 -> today Not Target day.(Not Error) or Status "Skip","Printed"
                    return;
                }

                // Get AR Statement Issue Cycle Code.
                String arStmtIssCycleCd = (String) arStmtCtrlMap.get("AR_STMT_ISS_CYCLE_CD");
                if (arStmtIssCycleCd == null) {
                    this.msgIdMgr.addXxMsgId(NFZM0016E, pMsg);
                    return;
                }

                // Get AR Statement Issue Day.
                arStmtIssDay = getArStmtIssDay(pMsg, arStmtIssCycleCd);
                if (arStmtIssDay == null) {
                    this.msgIdMgr.addXxMsgId(NFZM0010E, pMsg);
                    return;
                }
            }
            // [QC#5515] UPDATE end

            stmtBillTo = getTrgtBillTo(pMsg, arStmtIssDay);
            rsltSetBillTo = stmtBillTo.executeQuery();

            BigDecimal[] ageAmtArray = new BigDecimal[AGE_TYPE_NUM];
            // START 2018/04/23 H.Ikeda [QC#23882,ADD]
            BigDecimal[] delAmtArray = new BigDecimal[AGE_TYPE_NUM];
            // END   2018/04/23 H.Ikeda [QC#23882,ADD]
            List<String> arTrxNumList = new ArrayList<String>();
            boolean isExistsInv = false;
            boolean isExistsreport = false;
            // START 2018/11/11 [QC#25825,ADD]
            List<BigDecimal> arStmtPkList = new ArrayList<BigDecimal>();
            // END   2018/11/11 [QC#25825,ADD]
            // START 2019/04/03 [QC#31026, ADD]
            String arHdrPrintAcctPbl = ZYPCodeDataUtil.getVarCharConstValue("AR_HDR_PRINT_ACCT_PBL", pMsg.glblCmpyCd.getValue());
            String arHdrPrintAttn = ZYPCodeDataUtil.getVarCharConstValue("AR_HDR_PRINT_ATTN", pMsg.glblCmpyCd.getValue());
            // END   2019/04/03 [QC#31026, ADD]

            while (rsltSetBillTo.next()) {

                for (int i = 0; i < AGE_TYPE_NUM; i++) {
                    ageAmtArray[i] = BigDecimal.ZERO;
                    // START 2018/04/23 H.Ikeda [QC#23882,ADD]
                    delAmtArray[i] = BigDecimal.ZERO;;
                    // END   2018/04/23 H.Ikeda [QC#23882,ADD]
                }
                arTrxNumList.clear();
                isExistsInv = false;

                bean.setRsltSetBillTo(rsltSetBillTo);
                String billToCustCd = rsltSetBillTo.getString("BILL_TO_CUST_CD");
                // START 2022/09/07 M.Hashino [QC#60405,ADD]
                PreparedStatement stmtSendStmtTo = null;
                ResultSet rsltSetSendStmtTo = null;
                stmtSendStmtTo = getAddrSendStmtTo(pMsg, billToCustCd);
                rsltSetSendStmtTo = stmtSendStmtTo.executeQuery();
                List<String> sendStmtEmlAddrList = new ArrayList<String>();
                while (rsltSetSendStmtTo.next()){
                    sendStmtEmlAddrList.add(rsltSetSendStmtTo.getString("CTAC_PSN_EML_ADDR"));
                }
                S21SsmLowLevelCodingClient.closeResource(stmtSendStmtTo, rsltSetSendStmtTo);
                // END 2022/09/07 M.Hashino [QC#60405,ADD]
                // START 2018/04/23 H.Ikeda [QC#23882,ADD]
                String sendCrBalStmtFlg = rsltSetBillTo.getString("SEND_CR_BAL_STMT_FLG");
                // END   2018/04/23 H.Ikeda [QC#23882,ADD]
                PreparedStatement stmtInv = null;
                ResultSet rsltSetInv = null;
                BigDecimal arStmtSq = null;
                // START 2018/04/23 H.Ikeda [QC#23882,ADD]
                int delCnt = 0;
                int allCnt = 0;
                // END   2018/04/23 H.Ikeda [QC#23882,ADD]
                // START 2018/10/04 H.Ikeda [QC#24284,ADD]
                List<AR_STMT_DTL_INFOTMsg> dtlTMsgList = new ArrayList<AR_STMT_DTL_INFOTMsg>();
                // END   2018/10/04 H.Ikeda [QC#24284,ADD]
                try {
                    stmtInv = getTrgtInv(pMsg, billToCustCd);
                    rsltSetInv = stmtInv.executeQuery();

                    arStmtSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.AR_STMT_SQ);
                    BigDecimal arStmtDtlSq = BigDecimal.ZERO;

                    bean.setRsltSetInv(rsltSetInv);

                    // [QC#6075] INSERT start
                    String prevArTrxNum = new String();
                    // [QC#6075] INSERT end
                    // START 2017/06/09 J.Kim [QC#18413,ADD]
                    rsltSetInv.last();
                    int rowCnt = rsltSetInv.getRow();
                    rsltSetInv.beforeFirst();
                    this.totalRecordCnt = this.totalRecordCnt + rowCnt;
                    ZYPEZDItemValueSetter.setValue(pMsg.batProcTotRecCnt, BigDecimal.valueOf(this.totalRecordCnt));
                    // END 2017/06/09 J.Kim [QC#18413,ADD
                    while (rsltSetInv.next()) {
                        arStmtDtlSq = arStmtDtlSq.add(BigDecimal.ONE);

                        // Create AR Statement Detail.
                        // START 2018/04/23 H.Ikeda [QC#23882,MOD]
                        //if (!createArStmtDtlInfo(pMsg, rsltSetInv, arStmtSq, arStmtDtlSq, ageAmtArray, arTrxNumList, prevArTrxNum)) {
                        // START 2018/10/04 H.Ikeda [QC#24284,MOD]
                        //if (!createArStmtDtlInfo(pMsg, rsltSetInv, arStmtSq, arStmtDtlSq, ageAmtArray, arTrxNumList, prevArTrxNum, sendCrBalStmtFlg, delAmtArray)) {
                        if (!createArStmtDtlInfo(pMsg, rsltSetInv, arStmtSq, arStmtDtlSq, ageAmtArray, arTrxNumList, prevArTrxNum, sendCrBalStmtFlg, delAmtArray, dtlTMsgList)) {
                        // END   2018/10/04 H.Ikeda [QC#24284,MOD]
                        // END   2018/04/23 H.Ikeda [QC#23882,MOD]
                            this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                            return;
                        }

                        bean.setBillToCustAcctCd(rsltSetInv.getString("BILL_TO_CUST_ACCT_CD"));
                        bean.setBillToCustCd(rsltSetInv.getString("BILL_TO_CUST_CD"));
                        bean.setRcptDt(rsltSetInv.getString("RCPT_DT"));
                        bean.setRcptChkNum(rsltSetInv.getString("RCPT_CHK_NUM"));
                        bean.setDealRcptAmt(rsltSetInv.getBigDecimal("DEAL_RCPT_AMT"));
                        bean.setDealCcyCd(rsltSetInv.getString("DEAL_CCY_CD"));
                        // [QC#6630] INSERT start
                        bean.setDealCcySgnTxt(rsltSetInv.getString("DEAL_CCY_SGN_TXT"));
                        // [QC#6630] INSERT end
                        isExistsInv = true;
                        // [QC#6075] INSERT start
                        prevArTrxNum = rsltSetInv.getString("AR_TRX_NUM");
                        // [QC#6075] INSERT end
                        // START 2017/06/09 J.Kim [QC#18413,ADD]
                        this.normalRecordCnt++;
                        // END 2017/06/09 J.Kim [QC#18413,ADD]
                        // START 2018/04/23 H.Ikeda [QC#23882,ADD]
                        if (ZYPConstant.FLG_OFF_N.equals(sendCrBalStmtFlg)) {
                            String type = null;
                            if (ZYPCommonFunc.hasValue(rsltSetInv.getString("AR_APPLY_TP_CD"))) {
                                // DTL_STMT_TRX_TP_CD : VARCHAR(2) <- AR_APPLY_TP_CD : VARCHAR(3)
                                type =cutString(rsltSetInv.getString("AR_APPLY_TP_CD"), CUT_SIZE);
                            } else {
                                type =cutString(rsltSetInv.getString("AR_TRX_TP_CD"), CUT_SIZE);
                            }
                            if (chkType(type)) {
                                delCnt++;
                                this.normalRecordCnt--;
                            }
                        }
                        allCnt++;
                        // END 2018/04/23 H.Ikeda [QC#23882,ADD]
                    }
                } finally {
                    S21SsmLowLevelCodingClient.closeResource(stmtInv, rsltSetInv);
                }
                // START 2018/10/04 H.Ikeda [QC#24284,ADD]
                if (dtlTMsgList.size() > 0) {
                    int insCnt = S21FastTBLAccessor.insert(dtlTMsgList.toArray(new AR_STMT_DTL_INFOTMsg[dtlTMsgList.size()]));
                    if (insCnt != dtlTMsgList.size()) {
                        this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                        return;
                    }
                    dtlTMsgList.clear();
                }
                // END   2018/10/04 H.Ikeda [QC#24284,ADD]

                // If there is no invoice, report output unnecessary.
                if (!isExistsInv) {
                    continue;
                }

                // START 2018/04/23 H.Ikeda [QC#23882,MOD]
                if (ZYPConstant.FLG_OFF_N.equals(sendCrBalStmtFlg)) {
                    if (allCnt == delCnt) {
                        isExistsreport = true;
                        continue;
                    }
                }
                // END   2018/04/23 H.Ikeda [QC#23882,MOD]

                // Create AR Statement Header.
                // START 2018/04/23 H.Ikeda [QC#23882,MOD]
                //if (!createArStmtInfo(pMsg, bean, arStmtSq, ageAmtArray)) {
                // START 2019/04/03 [QC#31026, MOD]
                //if (!createArStmtInfo(pMsg, bean, arStmtSq, ageAmtArray, delAmtArray)) {
                if (!createArStmtInfo(pMsg, bean, arStmtSq, ageAmtArray, delAmtArray, arHdrPrintAcctPbl)) {
                // END   2019/04/03 [QC#31026, MOD]
                // END   2018/04/23 H.Ikeda [QC#23882,MOD]
                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                    return;
                }

                // Create AR Statement Info.
                // START 2018/04/23 H.Ikeda [QC#23882,MOD]
                //if (!createArStmtInfoWrk(pMsg, arStmtSq ,rsltSetBillTo)) {
                // START 2019/04/03 [QC#31026, MOD]
                //if (!createArStmtInfoWrk(pMsg, arStmtSq ,rsltSetBillTo, sendCrBalStmtFlg)) {
                if (!createArStmtInfoWrk(pMsg, arStmtSq ,rsltSetBillTo, sendCrBalStmtFlg, arHdrPrintAttn)) {
                // END   2019/04/03 [QC#31026, MOD]
                // END   2018/04/23 H.Ikeda [QC#23882,MOD]
                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                    return;
                }

                // START 2018/05/15 [QC#24329,MOD]
                if (MODE_01_PRINT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
                    /** Mode:01 Daily Batch **/
                    // Create EIP Report Request.
                    String cltPsnEmlAddr = rsltSetBillTo.getString("CLT_PSN_EML_ADDR");
                    // START 2022/09/12 M.Hashino [QC#60405,MOD]
                    //String ctacPsnEmlAddr = rsltSetBillTo.getString("CTAC_PSN_EML_ADDR");
                    // START 2019/02/05 [QC#30076,MOD]
                    //if (ZYPCommonFunc.hasValue(ctacPsnEmlAddr)) {
                        //createEIPReportRquest(service, pMsg, arStmtSq, billToCustCd, cltPsnEmlAddr, ctacPsnEmlAddr, sysTimeStamp, arStmtPkList, false);
                    if (sendStmtEmlAddrList.size() > 0) {
                        createEIPReportRquest(service, pMsg, arStmtSq, billToCustCd, cltPsnEmlAddr, sendStmtEmlAddrList, sysTimeStamp, arStmtPkList, false);
                    // END 2022/09/12 M.Hashino [QC#60405,MOD]
                    } else {
                        // START 2018/11/02 [QC#25825,MOD]
                        //createEIPReportRquest(service, pMsg, arStmtSq, billToCustCd, cltPsnEmlAddr, ctacPsnEmlAddr, sysTimeStamp);
                        // END   2018/11/02 [QC#25825,MOD]
                        // START 2022/09/12 M.Hashino [QC#60405,MOD]
                        //createEIPReportRquest(service, pMsg, arStmtSq, billToCustCd, cltPsnEmlAddr, ctacPsnEmlAddr, sysTimeStamp, arStmtPkList, true);
                        createEIPReportRquest(service, pMsg, arStmtSq, billToCustCd, cltPsnEmlAddr, sendStmtEmlAddrList, sysTimeStamp, arStmtPkList, true);
                        // END 2022/09/12 M.Hashino [QC#60405,MOD]
                        printFlg = true;
                    }
                    // END   2019/02/05 [QC#30076,MOD]
                } else if (MODE_02_DRAFT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
                    /** Mode:02 DraftCreate Screen **/
                    // Set Output Parameter
                    if (pMsg.xxArStmtInfoList.getValidCount() == pMsg.xxArStmtInfoList.length()) {
                        this.msgIdMgr.addXxMsgId(NZZM0007E, pMsg);
                        return;
                    }
                    int idx = pMsg.xxArStmtInfoList.getValidCount();
                    pMsg.xxArStmtInfoList.no(idx).stmtSqPk.setValue(arStmtSq);
                    pMsg.xxArStmtInfoList.no(idx).billToCustCd.setValue(billToCustCd);
                    pMsg.xxArStmtInfoList.setValidCount(idx + 1);
                }
                // END 2018/05/15 [QC#24329,MOD]

                isExistsreport = true;
            }

            // [QC#5379] INSERT start
            /** Mode:01 Daily Batch **/
            if (MODE_01_PRINT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
                // Update AR_STMT_CTRL.
                if (!updateArStmtCtrl(pMsg, arStmtCtrlMap)) {
                    return;
                }
            }
            // [QC#5379] INSERT end

            if (isExistsreport) {
                // START 2018/05/15 [QC#24329,DEL]
                if (MODE_01_PRINT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {

                    // ********************************
                    // If success, activate Report Processing.(Print, e-mail)
                    // ********************************
                    long processPk = service.activateAsyncReportJob();
                    S21InfoLogOutput.println("||||||||||||| Process Pk Print or e-Mail: " + processPk + " |||||||||||||||||||");
                    // START 2019/02/06 [QC#30076,MOD]
                    if (printFlg) {
                        // START 2018/11/02 [QC#25825,ADD]
                        // update AR_STMT_INFO
                        updateArStmtInfo(pMsg, arStmtPkList, new BigDecimal(processPk));
                        // END   2018/11/02 [QC#25825,ADD]
                    } else {
                        processPk = 0;
                    }
                    // END   2019/02/06 [QC#30076,MOD]
                    // START 2018/09/28 H.Ikeda [QC#25825,ADD]
                    pMsg.eipRptRqstPk.setValue(new BigDecimal(processPk));
                    // END   2018/09/28 H.Ikeda [QC#25825,ADD]
                }
                // END   2019/05/15 [QC#24329,MOD]
            } else {
                // START 2018/05/15 [QC#24329,DEL]
//                // [QC#5570] INSERT start
//                /** Mode:02 DraftCreate Screen **/
//                if (MODE_02_DRAFT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
//                    // Target Data does not exist.
//                    this.msgIdMgr.addXxMsgId(NFCM0831E, pMsg);
//                    return;
//                }
//                // [QC#5570] INSERT end
                // END   2018/05/15 [QC#24329,DEL]
            }

            // START 2017/06/09 J.Kim [QC#18413,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.batProcNormRecCnt, BigDecimal.valueOf(this.normalRecordCnt));
            // END 2017/06/09 J.Kim [QC#18413,ADD]
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtBillTo, rsltSetBillTo);
            super.updateMessage(pMsg, this.msgIdMgr);
        }
    }


    private boolean checkParam(NFZC203001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            this.msgIdMgr.addXxMsgId(NFZM0001E, pMsg);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            this.msgIdMgr.addXxMsgId(NFZM0002E, pMsg);
            return false;
        } else {
            if (!ZYPDateUtil.isValidDate(pMsg.slsDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                msgIdMgr.addXxMsgId(NFZM0005E, pMsg);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.arStmtDt)) {
            this.msgIdMgr.addXxMsgId(NFZM0003E, pMsg);
            return false;
        } else {
            if (!ZYPDateUtil.isValidDate(pMsg.arStmtDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
                this.msgIdMgr.addXxMsgId(NFZM0006E, pMsg);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.procModeCd)) {
            this.msgIdMgr.addXxMsgId(NFZM0017E, pMsg);
            return false;
        } else {
            if (MODE_02_DRAFT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.billToAcctNum) && !ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
                    this.msgIdMgr.addXxMsgId(NFZM0004E, pMsg);
                    return false;
                }
            }
        }

        return true;
    }

    // [QC#5379] UPDATE start    Map<String, String> -> Map<String, Object>
    private Map<String, Object> getArStmtIssCycleCd(NFZC203001PMsg pMsg) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arStmtDt", pMsg.arStmtDt.getValue());
        // [QC#5534] INSERT start
        ssmParam.put("arStmtStsCd", AR_STMT_STS.PENDING);
        // [QC#5534] INSERT end

        return (Map<String, Object>) ssmClient.queryObject("getArStmtIssCycleCd", ssmParam);
    }
    // [QC#5379] UPDATE end

    private String getArStmtIssDay(NFZC203001PMsg pMsg, String arStmtIssCycleCd) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arStmtIssCycleCd", arStmtIssCycleCd);
        ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);

        return (String) ssmClient.queryObject("getArStmtIssDay", ssmParam);
    }

    private PreparedStatement getTrgtBillTo(NFZC203001PMsg pMsg, String arStmtIssDay) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("procMode", pMsg.procModeCd.getValue());
        // [QC#6273] INSERT start
        ssmParam.put("slsDt", pMsg.slsDt);
        // START 2019/04/03 H.Ikeda [QC#31026,DEL]
        //ssmParam.put("ctacTpAp", CTAC_TP.ACCOUNT_PAYABLE);
        //ssmParam.put("ctacTpBill", CTAC_TP.BILL_TO_CONTACT);
        // END   2019/04/03 H.Ikeda [QC#31026,DEL]
        ssmParam.put("ctacPntTpEmail", DS_CTAC_PNT_TP.EMAIL_WORK);
        // [QC#6273] INSERT end
        // START 2017/06/09 J.Kim [QC#18413,ADD]
        ssmParam.put("unapply", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        // END 2017/06/09 J.Kim [QC#18413,ADD]

        if (MODE_02_DRAFT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
            ssmParam.put("billToCsutCd", pMsg.billToCustCd.getValue());
            ssmParam.put("billToAcctNum", pMsg.billToAcctNum.getValue());
        } else {
            /** Mode:01 Daily Batch */
            // [QC#5049] INSERT start [QC#5537] UPDATE start
            ssmParam.put("arStmtFlg", ZYPConstant.FLG_ON_Y);
            // [QC#5049] INSERT end
            ssmParam.put("arStmtIssDay", arStmtIssDay);
            // [QC#5537] UPDATE end
            ssmParam.put("billToCsutCd", null);
            ssmParam.put("billToAcctNum", null);
        }
        // START 2017/06/09 J.Kim [QC#18413,MOD]
        // START 2019/02/19 [QC#30038,ADD]
        ssmParam.put("payerCustModeCdStmtTo", PAYER_CUST_MODE.STMT_TO);
        ssmParam.put("payerCustModeCdSoldTo", PAYER_CUST_MODE.SOLD_TO);
        // END 2019/02/19 [QC#30038,ADD]
        // START  2019/04/03 H.Ikeda [QC#31026,ADD]
        ssmParam.put("stmt", CTAC_TP.STATEMENT_CONTACT);
        // END    2019/04/03 H.Ikeda [QC#31026,ADD]
        // return ssmLowClient.createPreparedStatement("getTrgtBillTo", ssmParam);
        return ssmLowClient.createPreparedStatement("getTrgtBillTo", ssmParam, setExecParam());
        // END 2017/06/09 J.Kim [QC#18413,MOD]
    }

    // START 2022/09/12 M.Hashino [QC#60405,ADD]
    private PreparedStatement getAddrSendStmtTo(NFZC203001PMsg pMsg, String billToCustCd) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("slsDt", pMsg.slsDt);
        ssmParam.put("ctacPntTpEmail", DS_CTAC_PNT_TP.EMAIL_WORK);
        ssmParam.put("stmt", CTAC_TP.STATEMENT_CONTACT);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        return ssmLowClient.createPreparedStatement("getAddrSendStmtTo", ssmParam);
    }
    // END 2022/09/12 M.Hashino [QC#60405,ADD]

    private PreparedStatement getTrgtInv(NFZC203001PMsg pMsg, String billToCustCd) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arStmtDt", pMsg.arStmtDt.getValue());
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("arTrxTpRcpt", AR_TRX_TP.RECEIPT);
        ssmParam.put("unapply", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        ssmParam.put("rcptChkSep", STR_RCPT_CHK_SEP);
        ssmParam.put("fmtYYYYMMDD", FMT_YYYYMMDD);

        // START 2019/02/19 [QC#30038,ADD]
        ssmParam.put("payerCustModeCdStmtTo", PAYER_CUST_MODE.STMT_TO);
        ssmParam.put("payerCustModeCdSoldTo", PAYER_CUST_MODE.SOLD_TO);
        // END 2019/02/19 [QC#30038,ADD]

        // START 2019/03/27 [QC#30844,ADD]
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("inv", AR_TRX_TP.INVOICE);
        // END   2019/03/27 [QC#30844,ADD]

        return ssmLowClient.createPreparedStatement("getTrgtInv", ssmParam);
    }

    // START 2018/04/23 H.Ikeda [QC#23882,MOD]
    //private boolean createArStmtDtlInfo(NFZC203001PMsg pMsg, ResultSet resltSetInv, BigDecimal stmtSq, BigDecimal stmtDtlSq, BigDecimal[] ageAmtArray, List<String> arTrxNumList, String prevInvAmt) throws SQLException {
    // START 2018/10/04 H.Ikeda [QC#24284,MOD]
    //private boolean createArStmtDtlInfo(NFZC203001PMsg pMsg, ResultSet resltSetInv, BigDecimal stmtSq, BigDecimal stmtDtlSq, BigDecimal[] ageAmtArray, List<String> arTrxNumList, String prevInvAmt, String sendCrBalStmtFlg, BigDecimal[] delAmtArray) throws SQLException {
    private boolean createArStmtDtlInfo(NFZC203001PMsg pMsg, ResultSet resltSetInv, BigDecimal stmtSq, BigDecimal stmtDtlSq, BigDecimal[] ageAmtArray, List<String> arTrxNumList, String prevInvAmt, String sendCrBalStmtFlg, BigDecimal[] delAmtArray, List<AR_STMT_DTL_INFOTMsg> dtlTMsgList) throws SQLException {
    // END   2018/10/04 H.Ikeda [QC#24284,MOD]
    // END   2018/04/23 H.Ikeda [QC#23882,MOD]
        AR_STMT_DTL_INFOTMsg tMsg = new AR_STMT_DTL_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.stmtSqPk, stmtSq);
        ZYPEZDItemValueSetter.setValue(tMsg.arStmtDtlSq, stmtDtlSq);
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, resltSetInv.getString("AR_TRX_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.invNum, resltSetInv.getString("AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arCustRefNum, resltSetInv.getString("AR_CUST_REF_NUM"));
        // STMT_TRX_TP_CD : VARCHAR(2) <- AR_TRX_TP_CD : VARCHAR(3)
        if (ZYPCommonFunc.hasValue(resltSetInv.getString("AR_TRX_TP_CD"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.stmtTrxTpCd, cutString(resltSetInv.getString("AR_TRX_TP_CD"), tMsg.getAttr("stmtTrxTpCd").getDigit()));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, resltSetInv.getString("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, resltSetInv.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, resltSetInv.getString("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.arCashAppPk, resltSetInv.getBigDecimal("AR_CASH_APP_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.rcptNum, resltSetInv.getString("RCPT_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.arApplyTpCd, resltSetInv.getString("AR_APPLY_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.arAdjTpCd, resltSetInv.getString("AR_ADJ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.invDueDt, resltSetInv.getString("INV_DUE_DT"));
        ZYPEZDItemValueSetter.setValue(tMsg.arRcptChkDescTxt, resltSetInv.getString("AR_RCPT_CHK_DESC_TXT"));

        // [QC#6075] UPDATE start
        /** Set PMT_LATE_DAYS_AOT, STMT_INV_AMT */
        // now AR_TRX_NUM
        String nowArTrxNum = tMsg.invNum.getValue();
        // if change AR_TRX_NUM (previous != now), set now PMT_LATE_DAYS_AOT,STMT_INV_AMT
        if (!nowArTrxNum.equals(prevInvAmt)) {

            BigDecimal lateDays = resltSetInv.getBigDecimal("PMT_LATE_DAYS_AOT");
            if (lateDays.intValue() < MAX_LATE_DAYS.negate().intValue()) {
                ZYPEZDItemValueSetter.setValue(tMsg.pmtLateDaysAot, MAX_LATE_DAYS.negate());
            } else if (lateDays.intValue() > MAX_LATE_DAYS.intValue()) {
                ZYPEZDItemValueSetter.setValue(tMsg.pmtLateDaysAot, MAX_LATE_DAYS);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.pmtLateDaysAot, resltSetInv.getBigDecimal("PMT_LATE_DAYS_AOT"));
            }

            // [QC#6075] UPDATE start
            if (0 == BigDecimal.ZERO.compareTo(resltSetInv.getBigDecimal("FUNC_ORIG_GRS_AMT"))) {
                tMsg.stmtInvAmt.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.stmtInvAmt, resltSetInv.getBigDecimal("FUNC_ORIG_GRS_AMT"));
            }
            // [QC#6075] UPDATE end

        } else {
            tMsg.pmtLateDaysAot.clear();
            tMsg.stmtInvAmt.clear();
        }
        // [QC#6075] UPDATE end

        // [QC#6075] UPDATE start
        if (0 == BigDecimal.ZERO.compareTo(resltSetInv.getBigDecimal("FUNC_APPLY_AMT"))) {
            tMsg.stmtCrAmt.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.stmtCrAmt, resltSetInv.getBigDecimal("FUNC_APPLY_AMT"));
            // [QC#6636] [QC#6976] INSERT start
            if(ZYPCommonFunc.hasValue(tMsg.arApplyTpCd)){
                if(AR_APPLY_TP.CASH.equals(tMsg.arApplyTpCd.getValue()) || AR_APPLY_TP.ADJUSTMENT.equals(tMsg.arApplyTpCd.getValue())){
                    //CashApply data(CSH),Adjustment data(ADJ) for statement display -> minus'-'Amount
                    ZYPEZDItemValueSetter.setValue(tMsg.stmtCrAmt, resltSetInv.getBigDecimal("FUNC_APPLY_AMT").negate());
                }
            }
            // [QC#6636] [QC#6976] INSERT end
        }
        // [QC#6075] UPDATE end

        // [QC#5706] UPDATE start
        // ZYPEZDItemValueSetter.setValue(tMsg.stmtBalAmt, resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT"));
        tMsg.stmtBalAmt.clear();
        // [QC#5706] UPDATE end
        // [QC#5706] INSERT start
        /** Set STMT_BAL_AMT */
        // now AR_TRX_NUM
        nowArTrxNum = tMsg.invNum.getValue();
        BigDecimal nowRmngBalAmt = resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT");

        if (!resltSetInv.isLast()) {
            // get next AR_TRX_NUM
            resltSetInv.next();

            String nextArTrxNum = new String();
            if (ZYPCommonFunc.hasValue(resltSetInv.getString("AR_TRX_NUM"))) {
                nextArTrxNum = resltSetInv.getString("AR_TRX_NUM");

                // if change next AR_TRX_NUM (now != next), set now STMT_BAL_AMT
                if (!nowArTrxNum.equals(nextArTrxNum)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.stmtBalAmt, nowRmngBalAmt);
                }
            }
            // restore now resltSetInv data.
            resltSetInv.previous();

        } else {
            // if not has some AR_TRX_NUM, set STMT_BAL_AMT
            ZYPEZDItemValueSetter.setValue(tMsg.stmtBalAmt, nowRmngBalAmt);
        }
        // [QC#5706] INSERT end
        // START 2018/10/04 H.Ikeda [QC#24284,MOD]
        dtlTMsgList.add(tMsg);
        if (dtlTMsgList.size() >= COMMIT_CNT) {
            int insCnt = S21FastTBLAccessor.insert(dtlTMsgList.toArray(new AR_STMT_DTL_INFOTMsg[dtlTMsgList.size()]));
            if (insCnt != dtlTMsgList.size()) {
                return false;
            }
            dtlTMsgList.clear();
        }
//        S21ApiTBLAccessor.insert(tMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            return false;
//        }
        // END 2018/10/04 H.Ikeda [QC#24284,MOD]
        BigDecimal amt = BigDecimal.ZERO;
        if (!arTrxNumList.contains(resltSetInv.getString("AR_TRX_NUM"))) {
            arTrxNumList.add(resltSetInv.getString("AR_TRX_NUM"));
            // START 2018/04/23 H.Ikeda [QC#23882-1,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sendCrBalStmtFlg)) {
                String type = null;
                if (ZYPCommonFunc.hasValue(resltSetInv.getString("AR_APPLY_TP_CD"))) {
                    // DTL_STMT_TRX_TP_CD : VARCHAR(2) <- AR_APPLY_TP_CD : VARCHAR(3)
                    type =cutString(resltSetInv.getString("AR_APPLY_TP_CD"), tMsg.getAttr("stmtTrxTpCd").getDigit());
                } else {
                    type =cutString(resltSetInv.getString("AR_TRX_TP_CD"), tMsg.getAttr("stmtTrxTpCd").getDigit());
                }
                if (TYPE_INVOICE.equals(type)) {
                    if (resltSetInv.getBigDecimal("FUNC_ORIG_GRS_AMT").subtract(resltSetInv.getBigDecimal("FUNC_APPLY_AMT")).compareTo(resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT")) == 0) {
                        amt = resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT");
                    } else {
                        amt = resltSetInv.getBigDecimal("FUNC_ORIG_GRS_AMT");
                    }
                } else {
                    amt = resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT");
                }
            } else {
                amt = resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT");
            }

            // ageAmtArray = setAgeAmt(ageAmtArray, resltSetInv.getBigDecimal("PMT_LATE_DAYS_AOT").intValue(), resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT"));
            ageAmtArray = setAgeAmt(ageAmtArray, resltSetInv.getBigDecimal("PMT_LATE_DAYS_AOT").intValue(), amt);
            // END  2018/04/23 H.Ikeda [QC#23882-1,MOD]

            // START 2018/04/23 H.Ikeda [QC#23882,ADD]
            if (ZYPConstant.FLG_OFF_N.equals(sendCrBalStmtFlg)) {
                String type = null;
                if (ZYPCommonFunc.hasValue(resltSetInv.getString("AR_APPLY_TP_CD"))) {
                    // DTL_STMT_TRX_TP_CD : VARCHAR(2) <- AR_APPLY_TP_CD : VARCHAR(3)
                    type =cutString(resltSetInv.getString("AR_APPLY_TP_CD"), tMsg.getAttr("stmtTrxTpCd").getDigit());
                } else {
                    type =cutString(resltSetInv.getString("AR_TRX_TP_CD"), tMsg.getAttr("stmtTrxTpCd").getDigit());
                }
                if (chkType(type)) {
                    delAmtArray = setAgeAmt(delAmtArray, resltSetInv.getBigDecimal("PMT_LATE_DAYS_AOT").intValue(), resltSetInv.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT"));
                }
            }
            // DEL   2018/04/23 H.Ikeda [QC#23882,MOD]
        }
        return true;
    }

    private BigDecimal[] setAgeAmt(BigDecimal[] ageAmtArray, int pmtLateDayAot, BigDecimal rmngBalAmt) {

        if (pmtLateDayAot <= BigDecimal.ZERO.intValue()) {
            // not LateDays
            ageAmtArray[AGE_TYPE_CUR] = ageAmtArray[AGE_TYPE_CUR].add(rmngBalAmt);

        } else if (BigDecimal.ZERO.intValue() < pmtLateDayAot && pmtLateDayAot <= AGE_30) {
            // LateDays 1^30
            ageAmtArray[AGE_TYPE_0130] = ageAmtArray[AGE_TYPE_0130].add(rmngBalAmt);

        } else if (AGE_30 < pmtLateDayAot && pmtLateDayAot <= AGE_60) {
            // LateDays 31^60
            ageAmtArray[AGE_TYPE_3160] = ageAmtArray[AGE_TYPE_3160].add(rmngBalAmt);

        } else if (AGE_60 < pmtLateDayAot && pmtLateDayAot <= AGE_90) {
            // LateDays 61^90
            ageAmtArray[AGE_TYPE_6190] = ageAmtArray[AGE_TYPE_6190].add(rmngBalAmt);

        } else if (AGE_90 < pmtLateDayAot) {
            // LateDays over 91
            ageAmtArray[AGE_TYPE_OVER] = ageAmtArray[AGE_TYPE_OVER].add(rmngBalAmt);
        }

        ageAmtArray[AGE_TYPE_TOT] = ageAmtArray[AGE_TYPE_TOT].add(rmngBalAmt);

        return ageAmtArray;
    }

    // START 2018/04/23 H.Ikeda [QC#23882,MOD]
    //private boolean createArStmtInfo(NFZC203001PMsg pMsg, NFZC203001Bean bean, BigDecimal stmtSq, BigDecimal[] ageAmtArray) throws SQLException {
    // START 2019/04/03 [QC#31026, MOD]
    //private boolean createArStmtInfo(NFZC203001PMsg pMsg, NFZC203001Bean bean, BigDecimal stmtSq, BigDecimal[] ageAmtArray, BigDecimal[] delAmtArray) throws SQLException {
    private boolean createArStmtInfo(NFZC203001PMsg pMsg, NFZC203001Bean bean, BigDecimal stmtSq, BigDecimal[] ageAmtArray, BigDecimal[] delAmtArray, String arHdrPrintAcctPbl) throws SQLException {
    // END   2019/04/03 [QC#31026, MOD]
    // END   2018/04/23 H.Ikeda [QC#23882,MOD]
        AR_STMT_INFOTMsg tMsg = new AR_STMT_INFOTMsg();
        ResultSet rsltSetBillTo = bean.getRsltSetBillTo();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.stmtSqPk, stmtSq);
        ZYPEZDItemValueSetter.setValue(tMsg.stmtPrintDt, pMsg.arStmtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.cmpyNm, bean.getCmpyNm());
        ZYPEZDItemValueSetter.setValue(tMsg.ofcNm, bean.getCmpyNm());
        ZYPEZDItemValueSetter.setValue(tMsg.ofcFirstLineAddr, bean.getOfcFirstLineAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.ofcScdLineAddr, bean.getOfcScdLineAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.ofcTelNum, cutString(rsltSetBillTo.getString("CLT_STMT_PHO_NUM"), tMsg.getAttr("ofcTelNum").getDigit()));
        ZYPEZDItemValueSetter.setValue(tMsg.ofcFaxNum, cutString(rsltSetBillTo.getString("CLT_STMT_FAX_NUM"), tMsg.getAttr("ofcFaxNum").getDigit()));
        ZYPEZDItemValueSetter.setValue(tMsg.firstRemToLocNm, rsltSetBillTo.getString("FIRST_REM_TO_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.scdRemToLocNm, cutString(rsltSetBillTo.getString("SCD_REM_TO_LOC_NM"), tMsg.getAttr("scdRemToLocNm").getDigit()));
        tMsg.indMlStmtCd.clear();
        // START  2019/04/03 H.Ikeda [QC#31026,MOD]
        //ZYPEZDItemValueSetter.setValue(tMsg.firstCustNm, rsltSetBillTo.getString("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.firstCustNm, createFirstCustNm(rsltSetBillTo, tMsg.getAttr("firstCustNm").getDigit(), arHdrPrintAcctPbl));
        // END    2019/04/03 H.Ikeda [QC#31026,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.scdCustNm, rsltSetBillTo.getString("LOC_NM"));
        // [QC#5380] UPDATE start
        String firstCtacTxtFullLenTxt = createFirstCtacTxtFullLengthTxt(rsltSetBillTo);
        ZYPEZDItemValueSetter.setValue(tMsg.firstCtacTxt, (cutString(firstCtacTxtFullLenTxt, tMsg.getAttr("firstCtacTxt").getDigit())));
        // [QC#5380] UPDATE end
        ZYPEZDItemValueSetter.setValue(tMsg.custCtyAddr, rsltSetBillTo.getString("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.custStAddr, rsltSetBillTo.getString("ST_NM"));
        ZYPEZDItemValueSetter.setValue(tMsg.custStAddrCd, rsltSetBillTo.getString("ST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.custZipAddr, rsltSetBillTo.getString("POST_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.pmtDt, bean.getRcptDt());
        // START 2018/04/23 H.Ikeda [QC#23882,MOD]
        //ZYPEZDItemValueSetter.setValue(tMsg.balTotAmt, ageAmtArray[AGE_TYPE_TOT]);
        //ZYPEZDItemValueSetter.setValue(tMsg.ageCurAmt, ageAmtArray[AGE_TYPE_CUR]);
        //ZYPEZDItemValueSetter.setValue(tMsg.age0130Amt, ageAmtArray[AGE_TYPE_0130]);
        //ZYPEZDItemValueSetter.setValue(tMsg.age3160Amt, ageAmtArray[AGE_TYPE_3160]);
        //ZYPEZDItemValueSetter.setValue(tMsg.age6190Amt, ageAmtArray[AGE_TYPE_6190]);
        //ZYPEZDItemValueSetter.setValue(tMsg.ageOverAmt, ageAmtArray[AGE_TYPE_OVER]);
        ZYPEZDItemValueSetter.setValue(tMsg.balTotAmt, ageAmtArray[AGE_TYPE_TOT].subtract(delAmtArray[AGE_TYPE_TOT]));
        ZYPEZDItemValueSetter.setValue(tMsg.ageCurAmt, ageAmtArray[AGE_TYPE_CUR].subtract(delAmtArray[AGE_TYPE_CUR]));
        ZYPEZDItemValueSetter.setValue(tMsg.age0130Amt, ageAmtArray[AGE_TYPE_0130].subtract(delAmtArray[AGE_TYPE_0130]));
        ZYPEZDItemValueSetter.setValue(tMsg.age3160Amt, ageAmtArray[AGE_TYPE_3160].subtract(delAmtArray[AGE_TYPE_3160]));
        ZYPEZDItemValueSetter.setValue(tMsg.age6190Amt, ageAmtArray[AGE_TYPE_6190].subtract(delAmtArray[AGE_TYPE_6190]));
        ZYPEZDItemValueSetter.setValue(tMsg.ageOverAmt, ageAmtArray[AGE_TYPE_OVER].subtract(delAmtArray[AGE_TYPE_OVER]));
        // END   2018/04/23 H.Ikeda [QC#23882,MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.makeDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsNum, rsltSetBillTo.getString("DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, bean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, bean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(tMsg.ofcThirdLineAddr, bean.getOfcThirdLineAddr());
        ZYPEZDItemValueSetter.setValue(tMsg.custFirstLineAddr, rsltSetBillTo.getString("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tMsg.custScdLineAddr, rsltSetBillTo.getString("SCD_LINE_ADDR"));
        // [QC#6634] DELETE start
//        if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("CLT_PTFO_COR_NM"))) {
//            if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("CLT_STMT_PHO_NUM"))) {
//
//                // [QC#5499] UPDATE start
//                StringBuilder str = new StringBuilder();
//                str.append(cutString(rsltSetBillTo.getString("CLT_PTFO_COR_NM"), SVC_CTAC_COR_NM_LEN));
//                str.append(STR_SVC_CTAC_SEP);
//                str.append(cutString(rsltSetBillTo.getString("CLT_STMT_PHO_NUM"), SVC_CTAC_PHONE_LEN));
//                tMsg.firstSvcCtacTxt.setValue(cutString(str.toString(), tMsg.getAttr("firstSvcCtacTxt").getDigit()));
//
//                // ZYPEZDItemValueSetter.setValue(tMsg.firstSvcCtacTxt, (rsltSetBillTo.getString("CLT_PTFO_COR_NM").substring(0, SVC_CTAC_COR_NM_LEN)
//                // + STR_SVC_CTAC_SEP
//                // + rsltSetBillTo.getString("CLT_STMT_PHO_NUM").substring(0, SVC_CTAC_PHONE_LEN))
//                // .substring(0, tMsg.getAttr("firstSvcCtacTxt").getDigit()));
//                // [QC#5499] UPDATE end
//
//            } else {
//                ZYPEZDItemValueSetter.setValue(tMsg.firstSvcCtacTxt, cutString(rsltSetBillTo.getString("CLT_PTFO_COR_NM"), SVC_CTAC_COR_NM_LEN));
//            }
//        } else {
//            tMsg.firstSvcCtacTxt.clear();
//        }
        // [QC#6634] DELETE end
        ZYPEZDItemValueSetter.setValue(tMsg.pmtFirstCtacTxt, bean.getPmtFirstCtacTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.pmtScdCtacTxt, bean.getPmtScdCtacTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.pmtThirdCtacTxt, bean.getPmtThirdCtacTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.pmtFrthCtacTxt, bean.getPmtFrthCtacTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.pmtChkNum, bean.getRcptChkNum());
        ZYPEZDItemValueSetter.setValue(tMsg.lastPmtAmt, bean.getDealRcptAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.dealCcyCd, bean.getDealCcyCd());
        // [QC#6630] UPDATE start
        //ZYPEZDItemValueSetter.setValue(tMsg.dealCcyTxt, bean.getCcySgnTxt());
        ZYPEZDItemValueSetter.setValue(tMsg.dealCcyTxt, bean.getDealCcySgnTxt());
        // [QC#6630] UPDATE end

        // START 2020/02/03 [QC#55625,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.rptEmlOtptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.rptPrintOtptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.rptOtptCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.rptPrintRqstCpltFlg, ZYPConstant.FLG_OFF_N);
        // END 2020/02/03 [QC#55625,ADD]

        S21ApiTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }

        return true;
    }

    // START  2019/04/03 H.Ikeda [QC#31026,ADD]
    /**
     * createFirstCustNm
     * 
     * @param rsltSetBillTo     ResultSet
     * @param len               int
     * @param arHdrPrintAcctPbl String
     * @return String
     * @throws SQLException
     */
    private String createFirstCustNm(ResultSet rsltSetBillTo, int len, String arHdrPrintAcctPbl) throws SQLException {
        
        String rtnStr = null;
        String sh = rsltSetBillTo.getString("CTAC_PSN_FIRST_NM");
        String sm = rsltSetBillTo.getString("CTAC_PSN_MID_NM");
        String sl = rsltSetBillTo.getString("CTAC_PSN_LAST_NM");
        if (ZYPCommonFunc.hasValue(sm)) {
            rtnStr = cutString((chkNullStr(sh) + " " + sm + " " + chkNullStr(sl)).trim(), len);
        }
        else {
            rtnStr = cutString((chkNullStr(sh) + " " + chkNullStr(sl)).trim(), len);
        }
        if (!ZYPCommonFunc.hasValue(rtnStr)) {
            rtnStr = arHdrPrintAcctPbl;
        }
        return rtnStr;
    }
    
    private String chkNullStr(String item) {
        if (!ZYPCommonFunc.hasValue(item)) {
            item = "";
        }
        return item;
    }
    // END  2019/04/03 H.Ikeda [QC#31026,ADD]

    // [QC#5380] INSERT start
    private String createFirstCtacTxtFullLengthTxt(ResultSet rsltSetBillTo) throws SQLException {

        StringBuilder firstCtacTxtFullLengthTxt = new StringBuilder();
        if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("FIRST_LINE_ADDR"))) {
            firstCtacTxtFullLengthTxt.append(rsltSetBillTo.getString("FIRST_LINE_ADDR"));
        }
        if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("SCD_LINE_ADDR"))) {
            firstCtacTxtFullLengthTxt.append(" ");
            firstCtacTxtFullLengthTxt.append(rsltSetBillTo.getString("SCD_LINE_ADDR"));
        }
        if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("THIRD_LINE_ADDR"))) {
            firstCtacTxtFullLengthTxt.append(" ");
            firstCtacTxtFullLengthTxt.append(rsltSetBillTo.getString("THIRD_LINE_ADDR"));
        }
        if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("FRTH_LINE_ADDR"))) {
            firstCtacTxtFullLengthTxt.append(" ");
            firstCtacTxtFullLengthTxt.append(rsltSetBillTo.getString("FRTH_LINE_ADDR"));
        }
        return firstCtacTxtFullLengthTxt.toString();
    }
    // [QC#5380] INSERT end

//    private boolean checkDataCnt(NFZC203001PMsg pMsg, BigDecimal arStmtSq) throws SQLException{
//        PreparedStatement stmtInfo = null;
//        ResultSet rsltSetStmtInfo = null;
//        String type = null;
//        int cnt = 0;
//        int delCnt = 0;
//
//        try {
//            // Get Target Customer.
//            stmtInfo = getArStmt(pMsg, arStmtSq);
//            rsltSetStmtInfo = stmtInfo.executeQuery();
//
//            while (rsltSetStmtInfo.next()) {
//                if (ZYPCommonFunc.hasValue(rsltSetStmtInfo.getString("AR_APPLY_TP_CD"))) {
//                    // DTL_STMT_TRX_TP_CD : VARCHAR(2) <- AR_APPLY_TP_CD : VARCHAR(3)
//                    type =cutString(rsltSetStmtInfo.getString("AR_APPLY_TP_CD"), 2);
//                } else {
//                    type =rsltSetStmtInfo.getString("STMT_TRX_TP_CD");
//                }
//                if (chkType(type)) {
//                    delCnt++;
//                }
//                cnt++;
//            }
//            if (cnt == delCnt) {
//                return true;
//            }
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(stmtInfo, rsltSetStmtInfo);
//        }
//
//        return false;
//    }

    private boolean chkType(String type){
        
        if (TYPE_CREDIT_MEMO.equals(type) || 
            TYPE_PAYMENT.equals(type) || 
            TYPE_ON_ACCOUNT.equals(type) || 
            TYPE_CASH.equals(type) || 
            TYPE_ADJUSTMENT.equals(type)) {
            return true;
        }
        
        return false;
    }
    
    // START 2018/04/23 H.Ikeda [QC#23882,MOD]
    //private boolean createArStmtInfoWrk(NFZC203001PMsg pMsg, BigDecimal arStmtSq, ResultSet rsltSetBillTo) throws SQLException {
    // START 2019/04/03 [QC#31026, MOD]
    //private boolean createArStmtInfoWrk(NFZC203001PMsg pMsg, BigDecimal arStmtSq, ResultSet rsltSetBillTo, String sendCrBalStmtFlg) throws SQLException {
    private boolean createArStmtInfoWrk(NFZC203001PMsg pMsg, BigDecimal arStmtSq, ResultSet rsltSetBillTo, String sendCrBalStmtFlg, String arHdrPrintAttn) throws SQLException {
    // END   2019/04/03 [QC#31026, MOD]
    // END   2018/04/23 H.Ikeda [QC#23882,MOD]
        PreparedStatement stmtInfo = null;
        ResultSet rsltSetStmtInfo = null;

        try {
            // Get Target Customer.
            stmtInfo = getArStmtInfo(pMsg, arStmtSq);
            rsltSetStmtInfo = stmtInfo.executeQuery();
            // START 2016/07/21 K.Kojima [QC#11483,ADD]
            String arHdrPrintCmpyNm = ZYPCodeDataUtil.getVarCharConstValue("AR_HDR_PRINT_CMPY_NM", pMsg.glblCmpyCd.getValue());
            // END 2016/07/21 K.Kojima [QC#11483,ADD]
            // START 2018/10/04 H.Ikeda [QC#24284,ADD]
            List<AR_STMT_INFO_WRKTMsg> wrkTMsgList = new ArrayList<AR_STMT_INFO_WRKTMsg>();
            // END   2018/10/04 H.Ikeda [QC#24284,ADD]
            while (rsltSetStmtInfo.next()) {
                // [QC#6976] INSERT start
                if (!ZYPCommonFunc.hasValue(rsltSetStmtInfo.getString("STMT_INV_AMT")) 
                        && !ZYPCommonFunc.hasValue(rsltSetStmtInfo.getString("STMT_CR_AMT")) 
                        && !ZYPCommonFunc.hasValue(rsltSetStmtInfo.getString("STMT_BAL_AMT"))) {
                    continue;
                }
                // [QC#6976] INSERT end

                // START 2018/04/23 H.Ikeda [QC#23882,MOD]
                if (ZYPConstant.FLG_OFF_N.equals(sendCrBalStmtFlg)) {
                    String type = null;
                    if (ZYPCommonFunc.hasValue(rsltSetStmtInfo.getString("AR_APPLY_TP_CD"))) {
                        // DTL_STMT_TRX_TP_CD : VARCHAR(2) <- AR_APPLY_TP_CD : VARCHAR(3)
                        type = cutString(rsltSetStmtInfo.getString("AR_APPLY_TP_CD"), 2);
                    } else {
                        type =rsltSetStmtInfo.getString("STMT_TRX_TP_CD");
                    }
                    if (chkType(type)) {
                        continue;
                    }
                }
                // END   2018/04/23 H.Ikeda [QC#23882,MOD]
                AR_STMT_INFO_WRKTMsg tMsg = new AR_STMT_INFO_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.stmtSqPk, arStmtSq);
                ZYPEZDItemValueSetter.setValue(tMsg.arStmtDtlSq, rsltSetStmtInfo.getBigDecimal("AR_STMT_DTL_SQ"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrCmpyNm, rsltSetStmtInfo.getString("CMPY_NM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcNm, rsltSetStmtInfo.getString("OFC_NM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcFirstLineAddr, rsltSetStmtInfo.getString("OFC_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcScdLineAddr, rsltSetStmtInfo.getString("OFC_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrStmtPrintDtTxt, rsltSetStmtInfo.getString("STMT_PRINT_DT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrFirstRemToLocNm, rsltSetStmtInfo.getString("FIRST_REM_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrScdRemToLocNm, rsltSetStmtInfo.getString("SCD_REM_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrPmtDtTxt, rsltSetStmtInfo.getString("PMT_DT_TXT"));
                // [QC#6634] UPDATE start
                StringBuilder str = new StringBuilder();
                str.append(CONTACT_INFO_BIGIN_PART);
                if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("CLT_PSN_NM"))) {
                    str.append(cutString(rsltSetBillTo.getString("CLT_PSN_NM"), CTAC_PSN_NM_LEN));
                }
                if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("CLT_STMT_PHO_NUM"))) {
                    str.append(STR_SVC_CTAC_SEP);
                    str.append(cutString(rsltSetBillTo.getString("CLT_STMT_PHO_NUM"), CLT_STMT_PHONE_LEN));
                }
                if (str.equals(CONTACT_INFO_BIGIN_PART)) {
                    tMsg.hdrFirstCtacTxt.clear();
                } else {
                    tMsg.hdrFirstCtacTxt.setValue(cutString(str.toString(), tMsg.getAttr("hdrFirstCtacTxt").getDigit()));
                }
                // [QC#5499] UPDATE start
                // ZYPEZDItemValueSetter.setValue(tMsg.hdrFirstCtacTxt, rsltSetStmtInfo.getString("CLT_PTFO_COR_NM").substring(0, FIRST_CTAC_LEN));
                // ZYPEZDItemValueSetter.setValue(tMsg.hdrFirstCtacTxt, cutString(rsltSetStmtInfo.getString("CLT_PTFO_COR_NM"), FIRST_CTAC_LEN));
                // [QC#5499] UPDATE end
                // [QC#6634] UPDATE end
                tMsg.hdrScdCtacTxt.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.dtlInvDt, rsltSetStmtInfo.getString("INV_DT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlArCustRefNum, rsltSetStmtInfo.getString("AR_CUST_REF_NUM"));
                // [QC#6447] UPDATE start
                if (ZYPCommonFunc.hasValue(rsltSetStmtInfo.getString("AR_APPLY_TP_CD"))) {
                    // DTL_STMT_TRX_TP_CD : VARCHAR(2) <- AR_APPLY_TP_CD : VARCHAR(3)
                    ZYPEZDItemValueSetter.setValue(tMsg.dtlStmtTrxTpCd, cutString(rsltSetStmtInfo.getString("AR_APPLY_TP_CD"), tMsg.getAttr("dtlStmtTrxTpCd").getDigit()));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.dtlStmtTrxTpCd, rsltSetStmtInfo.getString("STMT_TRX_TP_CD"));
                }
                // [QC#6447] UPDATE end
                ZYPEZDItemValueSetter.setValue(tMsg.dtlInvAmt, rsltSetStmtInfo.getBigDecimal("STMT_INV_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlDueDt, rsltSetStmtInfo.getString("INV_DUE_DT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlCustIssPoNumTxt, rsltSetStmtInfo.getString("CUST_ISS_PO_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlInvNetAmt, rsltSetStmtInfo.getBigDecimal("STMT_BAL_AMT"));
                tMsg.dtlOrigInvNum.clear();
                ZYPEZDItemValueSetter.setValue(tMsg.hdrOfcThirdLineAddr, rsltSetStmtInfo.getString("OFC_THIRD_LINE_ADDR"));
                // START 2019/04/03 H.Ikeda [QC#31026,MOD]
                //ZYPEZDItemValueSetter.setValue(tMsg.hdrAddlCustNm, rsltSetStmtInfo.getString("FIRST_CUST_NM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAddlCustNm, cutString((arHdrPrintAttn + rsltSetStmtInfo.getString("FIRST_CUST_NM")), tMsg.getAttr("hdrAddlCustNm").getDigit()));
                // END   2019/04/03 H.Ikeda [QC#31026,MOD]
                ZYPEZDItemValueSetter.setValue(tMsg.hdrCustNm, rsltSetStmtInfo.getString("SCD_CUST_NM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrCustFirstLineAddr, cutString(rsltSetStmtInfo.getString("FIRST_CTAC_TXT"), tMsg.getAttr("hdrCustFirstLineAddr").getDigit()));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrCustScdLineAddr, cutString(rsltSetStmtInfo.getString("SCD_CTAC_TXT"), tMsg.getAttr("hdrCustScdLineAddr").getDigit()));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrFirstCustCd, rsltSetStmtInfo.getString("SELL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrScdCustCd, rsltSetStmtInfo.getString("BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrStmtPrintDtDispTxt, rsltSetStmtInfo.getString("STMT_PRINT_DT_DISP_TXT"));
                // [QC#6596] UPDATE start
                //ZYPEZDItemValueSetter.setValue(tMsg.hdrRemCmpyNm, rsltSetStmtInfo.getString("CMPY_NM"));
                // START 2016/07/21 K.Kojima [QC#11128,MOD]
                // ZYPEZDItemValueSetter.setValue(tMsg.hdrRemCmpyNm,
                // rsltSetBillTo.getString("HDR_REM_CMPY_NM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrRemCmpyNm, arHdrPrintCmpyNm);
                // END 2016/07/21 K.Kojima [QC#11128,MOD]
                // [QC#6596] UPDATE end
                ZYPEZDItemValueSetter.setValue(tMsg.hdrFirstSvcCtacTxt, rsltSetStmtInfo.getString("FIRST_SVC_CTAC_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrFirstPmtCtacTxt, rsltSetStmtInfo.getString("PMT_FIRST_CTAC_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrScdPmtCtacTxt, rsltSetStmtInfo.getString("PMT_SCD_CTAC_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrThirdPmtCtacTxt, rsltSetStmtInfo.getString("PMT_THIRD_CTAC_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrFrthPmtCtacTxt, rsltSetStmtInfo.getString("PMT_FRTH_CTAC_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrPmtChkNum, rsltSetStmtInfo.getString("PMT_CHK_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrPmtAmt, rsltSetStmtInfo.getBigDecimal("LAST_PMT_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrPmtAmtTxt, rsltSetStmtInfo.getString("LAST_PMT_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrPmtDealCcyCd, rsltSetStmtInfo.getString("DEAL_CCY_CD"));
                // [QC#6630] UPDATE start
                ZYPEZDItemValueSetter.setValue(tMsg.hdrDealCcyTxt, rsltSetStmtInfo.getString("DEAL_CCY_TXT"));
                // [QC#6630] UPDATE end
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeCurBcktAmt, rsltSetStmtInfo.getBigDecimal("AGE_CUR_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeFirstBcktAmt, rsltSetStmtInfo.getBigDecimal("AGE_0130_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeScdBcktAmt, rsltSetStmtInfo.getBigDecimal("AGE_3160_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeThirdBcktAmt, rsltSetStmtInfo.getBigDecimal("AGE_6190_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeOverBcktAmt, rsltSetStmtInfo.getBigDecimal("AGE_OVER_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrBalTotAmt, rsltSetStmtInfo.getBigDecimal("BAL_TOT_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeCurBcktAmtTxt, rsltSetStmtInfo.getString("AGE_CUR_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeFirstBcktAmtTxt, rsltSetStmtInfo.getString("AGE_0130_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeScdBcktAmtTxt, rsltSetStmtInfo.getString("AGE_3160_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeThirdBcktAmtTxt, rsltSetStmtInfo.getString("AGE_6190_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrAgeOverBcktAmtTxt, rsltSetStmtInfo.getString("AGE_OVER_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.hdrBalTotAmtTxt, rsltSetStmtInfo.getString("BAL_TOT_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlContrNum, rsltSetStmtInfo.getString("SRC_SYS_DOC_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlChkCmntTxt, rsltSetStmtInfo.getString("AR_RCPT_CHK_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlLateDaysAot, rsltSetStmtInfo.getBigDecimal("PMT_LATE_DAYS_AOT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlDealCcyTxt, rsltSetStmtInfo.getString("DEAL_CCY_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlCrAmt, rsltSetStmtInfo.getBigDecimal("STMT_CR_AMT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlInvAmtTxt, rsltSetStmtInfo.getString("STMT_INV_AMT_FMT_TXT"));
                ZYPEZDItemValueSetter.setValue(tMsg.dtlCrAmtTxt, rsltSetStmtInfo.getString("STMT_CR_AMT_FMT_TXT"));
                // START 2018/05/07 H.Ikeda [QC#23882-1MOD]
                if (ZYPConstant.FLG_OFF_N.equals(sendCrBalStmtFlg) && !ZYPCommonFunc.hasValue(rsltSetStmtInfo.getString("STMT_BAL_AMT_FMT_TXT"))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.dtlInvNetAmtTxt, rsltSetStmtInfo.getString("STMT_INV_AMT_FMT_TXT"));
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.dtlInvNetAmtTxt, rsltSetStmtInfo.getString("STMT_BAL_AMT_FMT_TXT"));
                }
                // END   2018/05/07 H.Ikeda [QC#23882-1,MOD]
                // START 2018/09/14 H.Ikeda [QC#27307 ADD]
                if (ZYPCommonFunc.hasValue(rsltSetBillTo.getString("CLT_PSN_EML_ADDR"))) {
                    ZYPEZDItemValueSetter.setValue(tMsg.cltPsnEmlAddr, rsltSetBillTo.getString("CLT_PSN_EML_ADDR"));
                }
                // END   2018/09/14 H.Ikeda [QC#27307 ADD]
                // START 2018/10/04 H.Ikeda [QC#24284,MOD]
                wrkTMsgList.add(tMsg);
                if (wrkTMsgList.size() >= COMMIT_CNT) {
                    int insCnt = S21FastTBLAccessor.insert(wrkTMsgList.toArray(new AR_STMT_INFO_WRKTMsg[wrkTMsgList.size()]));
                    if (insCnt != wrkTMsgList.size()) {
                        return false;
                    }
                    wrkTMsgList.clear();
                }
//                S21ApiTBLAccessor.insert(tMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//                    return false;
//                }
            }
            if (wrkTMsgList.size() > 0) {
                int insCnt = S21FastTBLAccessor.insert(wrkTMsgList.toArray(new AR_STMT_INFO_WRKTMsg[wrkTMsgList.size()]));
                if (insCnt != wrkTMsgList.size()) {
                    return false;
                }
                wrkTMsgList.clear();
            }
            // END   2018/10/04 H.Ikeda [QC#24284,MOD]
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtInfo, rsltSetStmtInfo);
        }

        return true;
    }

    private PreparedStatement getArStmtInfo(NFZC203001PMsg pMsg, BigDecimal arStmtSq) throws SQLException {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("arStmtPk", arStmtSq);
        ssmParam.put("fmtYYYYMMDD", FMT_YYYYMMDD);
        ssmParam.put("fmtMMDDYYYY", FMT_MMDDYYYY);
        ssmParam.put("fmtMonthDDYYYY", FMT_MONTH_DD_YYYY);
        ssmParam.put("fmtNLS", FMT_NLS_DATE_LANGUAGE);
        ssmParam.put("fmtAmt", FMT_AMT);
        return ssmLowClient.createPreparedStatement("getArStmtInfo", ssmParam);
    }

//    // START 2018/04/23 H.Ikeda [QC#23882,ADD]
//    private PreparedStatement getArStmt(NFZC203001PMsg pMsg, BigDecimal arStmtSq) throws SQLException {
//        final Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("arStmtPk", arStmtSq);
//        return ssmLowClient.createPreparedStatement("getArStmt", ssmParam);
//    }
//    // END   2018/04/23 H.Ikeda [QC#23882,MOD]

    // START 2019/02/06 H.ikeda [QC#30076,MOD]
//    // START 2018/11/11 H.ikeda [QC#25825,MOD]
//    //private void createEIPReportRquest(S21EIPPrintingService service, NFZC203001PMsg pMsg, BigDecimal arStmtPk, String billToCustCd, String fromAddr, String toAddr, String sysTimeStamp) {
//    private void createEIPReportRquest(S21EIPPrintingService service, NFZC203001PMsg pMsg, BigDecimal arStmtPk, String billToCustCd, String fromAddr, String toAddr, String sysTimeStamp, List<BigDecimal> arStmtPkList) {
//    // END    2018/11/11 H.ikeda [QC#25825,MOD]
//        // START 2018/10/02 H.ikeda [QC#25825,MOD]
//        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
////        String titleBase = makeTitleBase(billToCustCd, sysTimeStamp);
////
////        //********************************
////        // Set Report Basic Info
////        //********************************
////        S21ReportRequestBean request = new S21ReportRequestBean(STMT_RPT_ID);
////        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
////        request.setRptArcFlg(true);
////        String rptTtlNm = "Print Statement " + titleBase;
////        request.setRptTtlNm(rptTtlNm);
////
////        //********************************
////        // Set Report Input Parameter
////        //********************************
////        S21InputParameter inputParam = request.getInputParamBeanInstance();
////        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
////        inputParam.addReportParameter("GLBL_CMPY_CD", glblCmpyCd);
////
////        inputParam.addReportParameter("EZCANCELFLAG", "0");
////
////        /* arStmtPk = INFO_WRK.STMT_SQ_PK */
////        inputParam.addReportParameter("STMT_SQ_PK", arStmtPk);
////        request.setInputParamBean(inputParam);
//
//        S21ReportRequestBean request = createRequest(glblCmpyCd, billToCustCd, sysTimeStamp, arStmtPk);
//        // END   2018/09/28 H.ikeda [QC#25825,MOD]
//        if (MODE_01_PRINT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
//            
//            // START 2018/10/02 H.ikeda [QC#25825,MOD]
//            //********************************
//            // Set e-mail option
//            //********************************
//            S21EmailOutputParameter emailOutParam = request.getEmailOutParamInstance();
//            emailOutParam.setBranchNo(STMT_RPT_BR_NUM_FOR_EML);
//
//            // Subject & Body
//            S21MailTemplate mlTmpl = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID);
//            if (mlTmpl == null) {
//                msgIdMgr.addXxMsgId(NFZM0020E, pMsg);
//                return;
//            }
//            mlTmpl.setTemplateParameter("BillToCustCd", billToCustCd);
//            emailOutParam.setSubject(mlTmpl.getSubject());
//            emailOutParam.setBodyText(mlTmpl.getBody());
//
//            // From Address
//            if (ZYPCommonFunc.hasValue(fromAddr)) {
//                emailOutParam.setSenderAddress(fromAddr);
//            } else {
//                String defFromAddr = ZYPCodeDataUtil.getVarCharConstValue(ML_FROM_ADDR_KEY, glblCmpyCd);
//                if (!ZYPCommonFunc.hasValue(defFromAddr)) {
//                    msgIdMgr.addXxMsgId(NFZM0018E, pMsg);
//                    return;
//                }
//                // START 2017/06/19 J.Kim [QC#19258,ADD]
//                emailOutParam.setSenderAddress(defFromAddr);
//                fromAddr = defFromAddr;
//                // END 2017/06/19 J.Kim [QC#19258,ADD]
//            }
//
//            // To Address
//            if (!ZYPCommonFunc.hasValue(toAddr)) {
//                toAddr = fromAddr;
//            }
//            emailOutParam.addToAddress(toAddr);
//
//            // Attachment
//            // START 2018/07/11 [QC#27002, MOD]
////            emailOutParam.setAttachFileName("Statement_" + billToCustCd + "_" + sysTimeStamp);
//            emailOutParam.setAttachFileName("Statement_" + billToCustCd + "_" + sysTimeStamp + EXTENSION_PDF);
//            // END   2018/07/11 [QC#27002, MOD]
//            request.setEmailOutParamBean(emailOutParam);
//
//            // Create Request
//            service.createReportByAsync(request);
//
//            //********************************
//            // Set Print out option
//            //********************************
////                S21PrinterOutputParameter printOutParam = request.getPrintOutParamBeanInstance();
////                printOutParam.setBranchNo(STMT_RPT_BR_NUM_FOR_PRT);
////                String printJobName = "Statement " + titleBase;
////                printOutParam.setPrintJobName(printJobName);
////                request.setPrintOutParamBean(printOutParam);
//
//            // Set Print out option for CUPS
//            request = createRequest(glblCmpyCd, billToCustCd, sysTimeStamp, arStmtPk);
//            S21CUPSFileOutputParameter cupsFileoutParam = request.getCUPSFileOutParamBeanInstance();
//            cupsFileoutParam.setFileName(billToCustCd + "_" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//            request.setCUPSFileOutParamBean(cupsFileoutParam);
//            // END   2018/10/02 H.ikeda [QC#25825,MOD]
//        }
//
//        //********************************
//        // Create Request
//        //********************************
//        long requestPk = service.createReportByAsync(request);
//        //long requestPk = service.createReportBySync(request);
//
//        // START 2018/05/15 [QC#24329,ADD]
//        updateArStmtInfo(pMsg, arStmtPk, BigDecimal.valueOf(requestPk));
//        // END   2018/05/15 [QC#24329,ADD]
//
//        // START 2018/11/11 [QC#25825,MOD]
//        //// START 2018/10/02 H.ikeda [QC#25825,ADD]
//        //int idx = pMsg.xxArStmtList.getValidCount();
//        //pMsg.xxArStmtList.no(idx).eipRptRqstPk.setValue(new BigDecimal(requestPk));
//        //pMsg.xxArStmtList.setValidCount(idx + 1);
//        //// END   2018/10/02 H.ikeda [QC#25825,ADD]
//        arStmtPkList.add(arStmtPk);
//        // END   2018/11/11 [QC#25825,MOD]
//
//        S21InfoLogOutput.println("||||||||||||| Request PK for print & e-Mail: " + requestPk + " |||||||||||||||||||");
//    }
    /**
     * createEIPReportRquest
     * 
     * @param service      S21EIPPrintingService
     * @param pMsg         NFZC203001PMsg
     * @param arStmtPk     BigDecimal
     * @param billToCustCd String
     * @param fromAddr     String
     * @param toAddr       List<String>
     * @param sysTimeStamp String
     * @param arStmtPkList List<BigDecimal>
     * @param printFlg     boolean
     */
    private void createEIPReportRquest(S21EIPPrintingService service
                                        ,NFZC203001PMsg pMsg
                                        ,BigDecimal arStmtPk
                                        ,String billToCustCd
                                        ,String fromAddr
                                        // START 2022/09/12 M.Hashino [QC#60405,MOD]
                                        //,String toAddr
                                        ,List<String> toAddr
                                        // END 2022/09/12 M.Hashino [QC#60405,MOD]
                                        ,String sysTimeStamp
                                        ,List<BigDecimal> arStmtPkList
                                        ,boolean printFlg) {
        // get GLBL_CMPY_CD
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        // create Request
        S21ReportRequestBean request = createRequest(glblCmpyCd, billToCustCd, sysTimeStamp, arStmtPk);

        if (MODE_01_PRINT_DATA_CRAT.equals(pMsg.procModeCd.getValue())) {
            if (printFlg) {
                //********************************
                // Set Print out option for CUPS
                //********************************
                S21CUPSFileOutputParameter cupsFileoutParam = request.getCUPSFileOutParamBeanInstance();
                cupsFileoutParam.setFileName(billToCustCd + "_" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                request.setCUPSFileOutParamBean(cupsFileoutParam);
                arStmtPkList.add(arStmtPk);
            } else {
                //********************************
                // Set e-mail option
                //********************************
                S21EmailOutputParameter emailOutParam = request.getEmailOutParamInstance();
                emailOutParam.setBranchNo(STMT_RPT_BR_NUM_FOR_EML);

                // Subject & Body
                S21MailTemplate mlTmpl = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID);
                if (mlTmpl == null) {
                    msgIdMgr.addXxMsgId(NFZM0020E, pMsg);
                    return;
                }
                mlTmpl.setTemplateParameter("BillToCustCd", billToCustCd);
                emailOutParam.setSubject(mlTmpl.getSubject());
                emailOutParam.setBodyText(mlTmpl.getBody());

                // From Address
                if (ZYPCommonFunc.hasValue(fromAddr)) {
                    emailOutParam.setSenderAddress(fromAddr);
                } else {
                    String defFromAddr = ZYPCodeDataUtil.getVarCharConstValue(ML_FROM_ADDR_KEY, glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(defFromAddr)) {
                        msgIdMgr.addXxMsgId(NFZM0018E, pMsg);
                        return;
                    }
                    emailOutParam.setSenderAddress(defFromAddr);
                    fromAddr = defFromAddr;
                }

                // To Address
                // START 2022/09/12 M.Hashino [QC#60405,MOD]
                //if (!ZYPCommonFunc.hasValue(toAddr)) {
                //    toAddr = fromAddr;
                //}
                //emailOutParam.addToAddress(toAddr);
                if (toAddr.size() <= 0) {
                    toAddr.add(fromAddr);
                }
                for (String emlAddr : toAddr) {
                    emailOutParam.addToAddress(emlAddr);
                }
                // END 2022/09/12 M.Hashino [QC#60405,MOD]

                // Attachment
                emailOutParam.setAttachFileName("Statement_" + billToCustCd + "_" + sysTimeStamp + EXTENSION_PDF);
                request.setEmailOutParamBean(emailOutParam);
            }
        }

        //********************************
        // Create Report
        //********************************
        long requestPk = service.createReportByAsync(request);

        // START 2020/02/03 [QC#55625,MOD] add parameter:printFlg
        updateArStmtInfo(pMsg, arStmtPk, BigDecimal.valueOf(requestPk), printFlg);
        // END 2020/02/03 [QC#55625,MOD]

        S21InfoLogOutput.println("||||||||||||| Request PK for print or e-Mail: " + requestPk + " |||||||||||||||||||");
    }
    // END   2019/02/06 H.ikeda [QC#30076,MOD]

    // START 2018/10/02 H.ikeda [QC#25825,ADD]
    /**
     * createRequest
     * 
     * @param glblCmpyCd   String
     * @param billToCustCd String
     * @param sysTimeStamp String
     * @param arStmtPk     BigDecimal
     * @return             S21ReportRequestBean
     */
    private S21ReportRequestBean createRequest(String glblCmpyCd, String billToCustCd, String sysTimeStamp, BigDecimal arStmtPk) {

        String titleBase = makeTitleBase(billToCustCd, sysTimeStamp);

        //********************************
        // Set Report Basic Info
        //********************************
        S21ReportRequestBean request = new S21ReportRequestBean(STMT_RPT_ID);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        request.setRptArcFlg(true);
        String rptTtlNm = "Print Statement " + titleBase;
        request.setRptTtlNm(rptTtlNm);

        //********************************
        // Set Report Input Parameter
        //********************************
        S21InputParameter inputParam = request.getInputParamBeanInstance();
        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter("GLBL_CMPY_CD", glblCmpyCd);

        inputParam.addReportParameter("EZCANCELFLAG", "0");

        /* arStmtPk = INFO_WRK.STMT_SQ_PK */
        inputParam.addReportParameter("STMT_SQ_PK", arStmtPk);
        request.setInputParamBean(inputParam);

        return request;
    }
    // END   2018/10/02 H.ikeda [QC#25825,ADD]

    private String makeTitleBase(String billToCustCd, String sysTimeStamp) {
        StringBuilder rptTtl = new StringBuilder();
        rptTtl.append("BilltoCustomer ");
        rptTtl.append(billToCustCd);
        rptTtl.append(" Time ");
        rptTtl.append(sysTimeStamp);
        return rptTtl.toString();
    }

    // [QC#5379] INSERT start
    private boolean updateArStmtCtrl(NFZC203001PMsg pMsg, Map<String, Object> arStmtCtrlMap) {

        // Get update Target AR_STMT_CTRL data
        BigDecimal arStmtCtrlPk = (BigDecimal) arStmtCtrlMap.get("AR_STMT_CTRL_PK");

        AR_STMT_CTRLTMsg updTMsg = new AR_STMT_CTRLTMsg();
        updTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        updTMsg.arStmtCtrlPk.setValue(arStmtCtrlPk);
        updTMsg = (AR_STMT_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(updTMsg);
        if (updTMsg == null) {
            // error: This data has been updated by another user.
            this.msgIdMgr.addXxMsgId(NZZM0003E, pMsg);
            return false;
        }

        // Set update data
        ZYPEZDItemValueSetter.setValue(updTMsg.arStmtStsCd, AR_STMT_STS.PRINTED);
        ZYPEZDItemValueSetter.setValue(updTMsg.arStmtPrintSubmtDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(updTMsg.arStmtDataCratDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(updTMsg.arStmtPrintDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(updTMsg.arStmtFnlzFlg, ZYPConstant.FLG_ON_Y);

        // update
        EZDTBLAccessor.update(updTMsg);
        if(!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())){
            // error: DB update error.
            this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
            return false;
        }

        return true;
    }
    // [QC#5379] INSERT end

    private String cutString(String val, int len) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        if (val.length() > len) {
            return val.substring(0, len);
        }
        return val;
    }

    // START 2017/06/09 J.Kim [QC#18413,ADD]
    private S21SsmExecutionParameter setExecParam() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }
    // END 2017/06/09 J.Kim [QC#18413,ADD]

    // 2020/02/03 [QC#55625,MOD] add parameter:printFlg
    // START 2018/05/15 [QC#24329,ADD]
    private boolean updateArStmtInfo(NFZC203001PMsg pMsg, BigDecimal arStmtPk, BigDecimal eipRptRqstPk, boolean printFlg) {
        AR_STMT_INFOTMsg inMsg = new AR_STMT_INFOTMsg();
        inMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        inMsg.stmtSqPk.setValue(arStmtPk);
        AR_STMT_INFOTMsg updTMsg = (AR_STMT_INFOTMsg) S21ApiTBLAccessor.findByKey(inMsg);

        // Update EIP Request PK
        updTMsg.eipRptRqstPk.setValue(eipRptRqstPk);

        // START 2020/02/03 [QC#55625,ADD]
        if (printFlg) {
            ZYPEZDItemValueSetter.setValue(updTMsg.rptPrintOtptFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(updTMsg.rptEmlOtptFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2020/02/03 [QC#55625,ADD]

        S21ApiTBLAccessor.update(updTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            // error: DB update error.
            this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
            return false;
        }

        return true;
    }
    // END  2018/05/15 [QC#24329,ADD]

    // START 2018/11/02 [QC#25825,ADD]
    /**
     * updateArStmtInfo
     * 
     * @param pMsg         NFZC203001PMsg
     * @param arStmtPkList List<BigDecimal>
     * @param eipRptRqstPk BigDecimal
     */
    private void updateArStmtInfo(NFZC203001PMsg pMsg, List<BigDecimal> arStmtPkList, BigDecimal eipRptRqstPk) {
        List<AR_STMT_INFOTMsg> updTMsgList = new ArrayList<AR_STMT_INFOTMsg>();
        for (int i = 0; i < arStmtPkList.size() ; i++) {
            AR_STMT_INFOTMsg inMsg = new AR_STMT_INFOTMsg();
            inMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            inMsg.stmtSqPk.setValue(arStmtPkList.get(i));
            AR_STMT_INFOTMsg updTMsg = (AR_STMT_INFOTMsg) S21ApiTBLAccessor.findByKey(inMsg);

            // Update EIP Request PK
            updTMsg.eipRptProcLogPk.setValue(eipRptRqstPk);
            updTMsgList.add(updTMsg);
            if (updTMsgList.size() >= COMMIT_CNT) {
                int insCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new AR_STMT_INFOTMsg[updTMsgList.size()]));
                if (insCnt != updTMsgList.size()) {
                    this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                    return;
                }
                updTMsgList.clear();
            }
        }
        if (updTMsgList.size() > 0) {
            int insCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new AR_STMT_INFOTMsg[updTMsgList.size()]));
            if (insCnt != updTMsgList.size()) {
                this.msgIdMgr.addXxMsgId(NFZM0013E, pMsg);
                return;
            }
            updTMsgList.clear();
        }
    }
    // END   2018/11/02 [QC#25825,ADD]

}
