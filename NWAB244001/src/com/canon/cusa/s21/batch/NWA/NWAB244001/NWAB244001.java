package com.canon.cusa.s21.batch.NWA.NWAB244001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsgArray;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.SCHD_AGMTTMsg;
import business.db.SCHD_AGMTTMsgArray;
import business.db.SCHD_AGMT_CTAC_PSNTMsg;
import business.db.SCHD_AGMT_CTAC_PSNTMsgArray;
import business.db.SCHD_AGMT_LINETMsg;
import business.db.SCHD_AGMT_PLNTMsg;
import business.db.SCHD_AGMT_PRC_CALC_BASETMsg;
import business.db.SCHD_AGMT_PRC_CALC_BASETMsgArray;
import business.db.SCHD_AGMT_SLS_CRTMsg;
import business.db.SCHD_AGMT_SLS_CRTMsgArray;
import business.db.SHIP_FROM_LOC_LIST_VTMsg;
import business.db.SHIP_FROM_LOC_LIST_VTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.TOCTMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC192001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC192001.NWZC192001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001SendMailForErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SalesRep;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_FUFL_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** <pre>
 * Scheduling Agreement Create Sales Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   Fujitsu         Y.Taoka         Create          N/A
 * 04/22/2016   Fujitsu         Y.Kanefusa      Update          QC#7451
 * 04/26/2016   Fujitsu         Y.Kanefusa      Update          QC#7668
 * 06/13/2016   Fujitsu         Y.Kanefusa      Update          QC#7362
 * 2016/06/13   Fujitsu         Y.Kanefusa      Update          QC#6480
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/07/19   Fujitsu         T.Murai         Update          S21_NA#11793
 * 2016/08/08   Fujitsu         R.Nakamura      Update          S21_NA#11171
 * 2016/08/16   SRAA            K.Aratani       Update          S21_NA#13496
 * 2016/09/09   Fujitsu         T.Murai         Update          S21_NA#13853
 * 2016/09/29   Fujitsu         N.Sugiura       Update          S21_NA#9192
 * 2016/10/07   Fujitsu         T.Murai         Update          S21_NA#14580
 * 2016/11/10   Fujitsu         T.Murai         Update          S21_NA#15895
 * 2016/12/21   Fujitsu         S.Ohki          Update          S21_NA#16617
 * 2017/08/15   Fujitsu         N.Sugiura       Update          S21_NA#16452
 * 2017/09/26   Hitachi         K.Kim           Update          QC#18744
 * 2017/10/03   Hitachi         K.Kim           Update          QC#21525
 * 2017/10/18   Fujitsu         S.Yamamoto      Update          S21_NA#20246
 * 2017/10/24   Fujitsu         K.Ishizuka      Update          QC#20246(Sol#454)
 * 2017/12/11   Fujitsu         K.Ishizuka      Update          QC#22629
 * 2018/02/06   Fujitsu         S.Ohki          Update          QC#20173(Sol#453)
 * 2018/04/05   Fujitsu         Y.Kanefusa      Update          S21_NA#25014
 * 2018/09/14   Fujitsu         M.Ohno          Update          QC#9700
 * 2018/12/21   Fujitsu         S.Kosaka        Update          QC#29714
 * 2019/01/30   Fujitsu         C.Hara          Update          QC#30095
 * 2019/04/10   Fujitsu         T.MURAI         Update          QC#31086
 * 2019/05/29   Fujitsu         R.Nakamura      Update          S21_NA#50405
 * 2020/03/13   Fujitsu         Y.Kanefusa      Update          S21_NA#56167
 * 2023/03/31   Hitachi         T.Usui          Update          QC#61240
 * 2023/04/28   CITS            R.Kurahashi     Update          QC#61281
 * 2023/12/06   CITS            K.Ikeda         Update          QC#61281
 * </pre>
 */
public class NWAB244001 extends S21BatchMain {

    /** Program Name */
    private static final String PROGRAM_NM = "Scheduling Agreement Create Sales Order Batch";

    /** Business Id */
    private static final String BIZ_ID = "NWAB2440";

    /** Mail template ID */
    private static final String MAIL_TEMP_ID = "NWAB2440M001";
    
    // QC#56167 2020/03/13 Add Start
    /** template ID */
    public static final String MAIL_TEMPLATE_ID = "NWXC1000M002";
    
    /** mail message header */
    public static final String MAIL_MSG_HEADER = "Error Message";

    /** line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** template parameter key : batch id */
    public static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";
// QC#56167 2020/03/13 Add End
    
    /** MAX Value IN_POUND_WT */
    private static final BigDecimal IN_POUND_WT_MAX = new BigDecimal("100000000000");

    /** Initial LINE_NUM */
    private static final String LINE_NUM_INIT = "000";

    /** Default LINE_SUB_NUM */
    private static final String LINE_SUB_NUM_DEF = "001";

    /** Default DS_ORD_POSN_NUM */
    private static final String DS_ORD_POSN_NUM_DEF = "1";

    /** Length MSG_TXT_INFO_TXT */
    private static final int MSG_TXT_INFO_TXT_LEN = 65;

    /** Length DELY_ADDL_CMNT_TXT */
    private static final int DELY_ADDL_CMNT_TXT_LEN = 240;

    /** Days for Adding target RDD */
    private static final BigDecimal TARGET_RDD_ADD_DAY_DEF = new BigDecimal(3);

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** Target RDD */
    private String targetRdd = null;

    /** Drop Ship Retail WH Code */
    private String dropShipRtlWhCd = null;

    /** S21 User Profile */
    private S21UserProfileService profile = null;

    /** Normal Record Count */
    private int normalRecCnt;

    /** Error Record Count */
    private int errRecCnt;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB244001().executeBatch();
    }

    @Override
    protected void initRoutine() {

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException("ZZZM9025E", new String[]{"Global Company Code"});
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        if (null == glblCmpyTMsg) {
            throw new S21AbendException("ZZZM9026E", new String[]{"Global Company Code"});
        }

        // Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Default past days
        BigDecimal days = ZYPCodeDataUtil.getNumConstValue("NWAB2440_BEFORE_RDD_DT", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(days)) {
            days = TARGET_RDD_ADD_DAY_DEF;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date slsDt;
        try {
            slsDt = format.parse(this.salesDate);
            Calendar slsDtCal = Calendar.getInstance();
            slsDtCal.setTime(slsDt);
            slsDtCal.add(Calendar.DATE, days.intValue());
            this.targetRdd = format.format(slsDtCal.getTime());
        } catch (ParseException e) {
            // Calc Error
            throw new S21AbendException("NWAM0737E", new String[]{"NWAB2440_BEFORE_RDD_DT"});
        }

        // Drop Ship WH Code
        this.dropShipRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(dropShipRtlWhCd)) {
            throw new S21AbendException("NWAM0525E", new String[]{"VAR_CHAR_CONST", "DROP_SHIP_RTL_WH_CD"});
        }

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
    }

    @Override
    protected void mainRoutine() {

        List<SendMailInfoBean> sendMailInfoList = new ArrayList<SendMailInfoBean>();
        // Search Target Data
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rddDt", this.targetRdd);
        ssmParam.put("schdAgmtSts", SCHD_AGMT_STS.ACTIVE);
        ssmParam.put("schdAgmtCancFlg", ZYPConstant.FLG_OFF_N);
        // Add Start 2016/08/02 S21_NA#11883
        ssmParam.put("ordHdrCancSts", ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordLineCancSts", ORD_LINE_STS.CANCELLED);
        // Add End 2016/08/02 S21_NA#11883

        List<SCHD_AGMT_PLNTMsg> schdAgmtPlnList = (List<SCHD_AGMT_PLNTMsg>) ssmBatchClient.queryObjectList("getSchdAgmtPln", ssmParam);

        if (schdAgmtPlnList != null && 0 < schdAgmtPlnList.size()) {
            List<SCHD_AGMT_PLNTMsg> schdAgmtPlnListUpd = new ArrayList<SCHD_AGMT_PLNTMsg>();
            // Lock Table
            for (SCHD_AGMT_PLNTMsg schdAgmtPln : schdAgmtPlnList) {
                schdAgmtPln = (SCHD_AGMT_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdate(schdAgmtPln);
                if (schdAgmtPln != null) {
                    schdAgmtPlnListUpd.add(schdAgmtPln);
                }
            }
            // Create CPO
             createCpoFromSchdAgmt(schdAgmtPlnListUpd, sendMailInfoList);
        }
        // Update an expired scheduling agreement in SCHD_AGMT.
        updateSchdAgmtStatusForExpired();

        // Send Email
        if (0 < sendMailInfoList.size()) {
            sendMailForAdmin(sendMailInfoList);
        }
    }

    @Override
    protected void termRoutine() {

        if (errRecCnt > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.normalRecCnt + this.errRecCnt);
    }


    /**
     * Create Cpo From SchdAgmt
     * @param schdAgmtPlnList List<SCHD_AGMT_PLNTMsg>
     * @param sendMailInfoList List<SendMailInfoBean>
     */
    private void createCpoFromSchdAgmt(List<SCHD_AGMT_PLNTMsg> schdAgmtPlnList, List<SendMailInfoBean> sendMailInfoList) {
        // Grouping
        List<List<SCHD_AGMT_PLNTMsg>> cpoGroupList = getGroupingList(schdAgmtPlnList);
        // Master check and Create API Parameters
        for (List<SCHD_AGMT_PLNTMsg> schdAgmtPlns : cpoGroupList) {
            if (schdAgmtPlns == null || schdAgmtPlns.size() == 0) {
                continue;
            }
            SCHD_AGMT_PLNTMsg schdAgmtPln = schdAgmtPlns.get(0);
            // Get SCHD_AGMT
            SCHD_AGMTTMsg schdAgmt = getSchdAgmt(schdAgmtPln);
            if (schdAgmt == null) {
                // Not Found, Error
                // Mod Start 2019/04/09 QC#31086
                // sendMailInfoList.add(setSendMailInfo(schdAgmtPln.schdAgmtNum.getValue(), null, null, "NWAM0739E", new String[]{"Scheduling Agreemnt", "SCHD_AGMT", "SCHD_AGMT_NUM", schdAgmtPln.schdAgmtNum.getValue()}));
                sendMailInfoList.add(setSendMailInfo(schdAgmtPln.schdAgmtNum.getValue(), schdAgmtPln.schdAgmtLineNum.getValue(), null, "NWAM0739E", new String[] {"Scheduling Agreemnt", "SCHD_AGMT", "SCHD_AGMT_NUM", schdAgmtPln.schdAgmtNum.getValue() }));
                // Mod End 2019/04/09 QC#31086
                this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                continue;
            }

            // Get Ship To Address information
            Map<String, Object> shipToAddrInfo = null;
            if (!ZYPCommonFunc.hasValue(schdAgmt.shipToCtyAddr) || !ZYPCommonFunc.hasValue(schdAgmt.shipToStCd) || !ZYPCommonFunc.hasValue(schdAgmt.shipToCtryCd)) {
                shipToAddrInfo = getShipToAddrInfo(schdAgmt);
                if (shipToAddrInfo == null) {
                    // Not Found, Error
                    // Mod Start 2019/04/09 QC#31086
                    // sendMailInfoList.add(setSendMailInfo(schdAgmt, "NWAM0739E", new String[]{"Ship To Customer Master", "SHIP_TO_CUST", "SHIP_TO_CUST_CD", schdAgmt.shipToCustLocCd.getValue()}));
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtPln, "NWAM0739E", new String[]{"Ship To Customer Master", "SHIP_TO_CUST", "SHIP_TO_CUST_CD", schdAgmt.shipToCustLocCd.getValue()}));
                    // Mod End 2019/04/09 QC#31086
                    this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                    continue;
                }
            }
            // Get DS_ORD_TP_PROC_DEF
            DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = getDsOrdTpProcDfn(schdAgmt);
            if (dsOrdTpProcDfn == null) {
                // Not Found, Error
                // Mod Start 2019/04/09 QC#31086
                // sendMailInfoList.add(setSendMailInfo(schdAgmt, "NWAM0739E", new String[]{"DS Order Type Process Definition Master", "DS_ORD_TP_PROC_DEF", "DS_ORD_TP_CD", schdAgmt.dsOrdTpCd.getValue()}));
                sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtPln, "NWAM0739E", new String[]{"DS Order Type Process Definition Master", "DS_ORD_TP_PROC_DEF", "DS_ORD_TP_CD", schdAgmt.dsOrdTpCd.getValue()}));
                // Mod End 2019/04/09 QC#31086
                this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                continue;
            }
            // Get CPO Line Category
            DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn = getDsOrdlineProcDfn(schdAgmt);
            if (dsOrdlineProcDfn == null) {
                // Not Found, Error
                // Mod Start 2019/04/09 QC#31086
                // sendMailInfoList.add(setSendMailInfo(schdAgmt, "NWAM0739E", new String[]{"DS Order Line Process Definition Master", "DS_ORD_LINE_PROC_DFN", "DS_ORD_TP_CD", schdAgmt.dsOrdTpCd.getValue()}));
                sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtPln, "NWAM0739E", new String[]{"DS Order Line Process Definition Master", "DS_ORD_LINE_PROC_DFN", "DS_ORD_TP_CD", schdAgmt.dsOrdTpCd.getValue()}));
                // Mod End 2019/04/09 QC#31086
                this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                continue;
            }
            // Get SCHD_AGMT_CTAC_PSN
            SCHD_AGMT_CTAC_PSNTMsgArray schdAgmtCtacPsnList = getSchdAgmtCtacPsns(schdAgmt);
            // Get SCHD_AGMT_SLS_CR
            SCHD_AGMT_SLS_CRTMsgArray schdAgmtSlsCrList = getSchdAgmtSlsCrs(schdAgmt);
            if (schdAgmtSlsCrList == null) {
                // Not Found, Error
                // Mod Start 2019/04/09 QC#31086
                // sendMailInfoList.add(setSendMailInfo(schdAgmt, "NWAM0739E", new String[]{"Scheduling Agreemnt Sales Credit", "SCHD_AGMT_SLS_CR", "SCHD_AGMT_NUM", schdAgmt.schdAgmtNum.getValue()}));
                sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtPln, "NWAM0739E", new String[]{"Scheduling Agreemnt Sales Credit", "SCHD_AGMT_SLS_CR", "SCHD_AGMT_NUM", schdAgmt.schdAgmtNum.getValue()}));
                // Mod Start 2019/04/09 QC#31086
                this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                continue;
            }
            // Create Pricing API Parameter for Header
            NWZC157001PMsg pricing = new NWZC157001PMsg();
            createParamPricingAPIHeader(pricing, schdAgmt, dsOrdTpProcDfn);
            // Create DS CPO Update API Parameter for Header and Config
            NWZC150001PMsg dsCpo = new NWZC150001PMsg();
            //// For Header
            createParamDsCpoAPIHeader(dsCpo, schdAgmt, schdAgmtPlns);
            //// For Config
            createParamDsCpoAPIConfig(dsCpo, schdAgmt);
            //// For Sales Credit
            createParamDsCpoAPISlsCredit(dsCpo, schdAgmtSlsCrList);
            //// For CPO Delivery Info List
            createParamDsCpoAPIDelyInfo(dsCpo, schdAgmt);
            //// For Contact Person List
            createParamDsCpoAPICtacPsn(dsCpo, schdAgmt, schdAgmtCtacPsnList);

            // Get SCHD_AGMT_LINE
            boolean hasErrLine = false;
            String lineNum = LINE_NUM_INIT;
            // Add Start 2019/04/10 QC#31086
            // Map <OrderLineNum, SchdLineNum>
            Map<String, BigDecimal>  lineNumMap = new HashMap<String, BigDecimal>();
            // Add End 2019/04/10 QC#31086
            for (SCHD_AGMT_PLNTMsg schdAgmtPlnIn : schdAgmtPlns) {
                SCHD_AGMT_LINETMsg schdAgmtLine = getSchdAgmtLine(schdAgmtPlnIn);
                if (schdAgmtLine == null) {
                    // Not Found, Error
                    sendMailInfoList.add(
                            setSendMailInfo(schdAgmt, schdAgmtPlnIn, "NWAM0739E", new String[]{"Scheduling Agreemnt Line", "SCHD_AGMT_LINE", "SCHD_AGMT_NUM, SCHD_AGMT_LINE_NUM", schdAgmtPlnIn.schdAgmtNum.getValue() + "," + schdAgmtPlnIn.schdAgmtLineNum.getValue()}));
                    hasErrLine = true;
                    break;
                }
                // Get MDSE
                MDSETMsg mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(this.glblCmpyCd, schdAgmtLine.mdseCd.getValue());
                if (mdse == null) {
                    sendMailInfoList.add(
                            setSendMailInfo(schdAgmt, schdAgmtPlnIn, "NWAM0739E", new String[]{"MDSE", "MDSE", "MDSE_CD", schdAgmtLine.mdseCd.getValue()}));
                    hasErrLine = true;
                    break;
                }
                if (!ZYPConstant.FLG_ON_Y.equals(mdse.invtyCtrlFlg.getValue())) {
                    // It's an Intangible, error
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWAM0738E", new String[]{}));
                    hasErrLine = true;
                    break;
                }

                // START 2017/10/03 K.Kim [QC#21525, ADD]
                // START 2017/09/26 K.Kim [QC#18744, ADD]
                // Get Supply Inclusive Flg
                if (ZYPCommonFunc.hasValue(schdAgmtLine.dsContrDtlPk)) {
                    String splyInclFlg = getSplyInclFlg(schdAgmtLine.dsContrDtlPk.getValue());
                    if (ZYPConstant.FLG_ON_Y.equals(splyInclFlg)) {
                        sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NSAM0704E", new String[]{}));
                        hasErrLine = true;
                        break;
                    }
                }
                // END 2017/09/26 K.Kim [QC#18744, ADD]
                // END 2017/10/03 K.Kim [QC#21525, ADD]

                // Get Default WH (Call Default WH API[NWZC180001])
                NWZC180001PMsg defWHParam = new NWZC180001PMsg();
                String msgId = getDefaultWh(defWHParam, schdAgmt, schdAgmtPlnIn, mdse);
                if (ZYPCommonFunc.hasValue(msgId)) {
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, msgId));
                    if (msgId.endsWith("E")) {
                        // Common Function Error
                        hasErrLine = true;
                        break;
                    }
                }
                if (!ZYPCommonFunc.hasValue(defWHParam.rtlWhCd.getValue())) {
                    // Default WH(RTL_WH_CD) does not exist
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWAM0744E", new String[]{"Default WH"}));
                    hasErrLine = true;
                    break;
                }
                String invtyLocCd = null;
                if (this.dropShipRtlWhCd.equals(defWHParam.rtlWhCd.getValue())) {
                    invtyLocCd  = defWHParam.vndCd.getValue();
                } else {
                    invtyLocCd = new StringBuffer().append(defWHParam.rtlWhCd.getValue()).append(defWHParam.rtlSwhCd.getValue()).toString();
                }
                // Get Retail WH Infomation
                Map<String, Object> rtlWhInfo = getRtlWhInfo(defWHParam.rtlWhCd.getValue());
                if (rtlWhInfo == null) {
                    // Not Found, Error
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWAM0739E", new String[]{"Retail Warehouse Master", "RTL_WH", "RTL_WH_CD", defWHParam.rtlWhCd.getValue()}));
                    hasErrLine = true;
                    break;
                }

                // Get Sales Rep Info
                Map<String, Object> slsRepInfo = getSlsRepInfo(schdAgmt);

                // Set value mdse for DS CPO Update API
                String mdseCd  = null;
                String origMdseCd  = null;
                String subsItemCd  = null;
                if (ZYPCommonFunc.hasValue(schdAgmtLine.sbstMdseCd)) {
                    mdseCd = schdAgmtLine.sbstMdseCd.getValue();
                    origMdseCd = schdAgmtLine.mdseCd.getValue();
                    subsItemCd = schdAgmtLine.sbstMdseCd.getValue();
                } else if (ZYPConstant.FLG_ON_Y.equals(schdAgmtLine.supdLockFlg.getValue())) {
                    //QC#13496
                    //mdseCd = schdAgmtLine.sbstMdseCd.getValue();
                    mdseCd = schdAgmtLine.mdseCd.getValue();
                    origMdseCd = null;
                    subsItemCd = null;
                }
                // Supersede
                if (ZYPCommonFunc.hasValue(schdAgmtLine.mdseCd) && !ZYPCommonFunc.hasValue(schdAgmtLine.sbstMdseCd) && ZYPConstant.FLG_OFF_N.equals(schdAgmtLine.supdLockFlg.getValue())) {
                    // Get Supersede MDSE
                    String invtyLocTpCd = getInvtyLocTpCd(invtyLocCd);
                    if (invtyLocTpCd == null) {
                        // Not Found, Error
                        sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWAM0739E", new String[]{"Ship From Location Master", "SHIP_FROM_LOC_LIST_V", "INVTL_LOC_CD", invtyLocCd}));
                        hasErrLine = true;
                        break;
                    }
                    // 2018/02/05 S21_NA#20173 Mod Start
                    // Call Supersede API[NWZC2060], Get Supersede MDSE
//                    NWZC206001PMsg supersede = new NWZC206001PMsg();
//                    msgId = getSupersedeMdseCallAPI(supersede, invtyLocCd, invtyLocTpCd, schdAgmtLine.stkStsCd.getValue(), mdse.mdseCd.getValue());
                    NWZC192001PMsg supersede = new NWZC192001PMsg();
                    msgId = getSupersedeMdseCallAPI(supersede, invtyLocCd, invtyLocTpCd, schdAgmt, schdAgmtLine, schdAgmtPlnIn, shipToAddrInfo);
                    // 2018/02/05 S21_NA#20173 Mod End
                    if (ZYPCommonFunc.hasValue(msgId)) {
                        sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, msgId));
                        if (msgId.endsWith("E")) {
                            // Common Function Error
                            hasErrLine = true;
                            break;
                        }
                    }
                    // Get Supersede MDSE
                    // 2018/02/05 S21_NA#20173 Mod Start
//                    String supersedeMdse = getSupersedeMdse(supersede, schdAgmtPlnIn);
                    String supersedeMdse = supersede.supdToMdseCd.getValue();
                    // 2018/02/05 S21_NA#20173 Mod End
                    // QC#25014 2018/04/05 Mod Start
                    //if (supersedeMdse == null) {
                    if (!ZYPCommonFunc.hasValue(supersedeMdse)) {
                    // QC#25014 2018/04/05 Mod End
                        // Not Found Supersede

                        // Mod Start QC#11793 2016/07/19
                        // mdseCd = mdse.mdseCd.getValue();
                        mdseCd = schdAgmtLine.mdseCd.getValue();
                        // Mod End QC#11793 2016/07/19
                        origMdseCd = null;
                        subsItemCd = null;
                    } else {
                        // Found Supersede
                        mdseCd = supersedeMdse;

                        // Mod Start QC#11793 2016/07/19
                        // origMdseCd = mdse.mdseCd.getValue();
                        origMdseCd = schdAgmtLine.mdseCd.getValue();
                        // Mod End QC#11793 2016/07/19
                        subsItemCd = null;
                    }
                }
                // Get MDSE(Sencond)
                if (!schdAgmtLine.mdseCd.getValue().equals(mdseCd)) {
                    // Mod Start QC#11793 2016/07/19
                    // mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(this.glblCmpyCd, schdAgmtLine.mdseCd.getValue());
                    mdse = NWXMdseTMsgThreadLocalCache.getInstance().get(this.glblCmpyCd, mdseCd);
                    // Mod End QC#11793 2016/07/19

                    if (mdse == null) {
                        // Not Found, Error
                    	//QC#13496
                        //sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWAM0739E", new String[]{"MDSE", "MDSE", "MDSE_CD", schdAgmtLine.mdseCd.getValue()}));
                        sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWAM0739E", new String[]{"MDSE", "MDSE", "MDSE_CD", mdseCd}));
                        hasErrLine = true;
                        break;
                    }
                }
                // Get Mdse Store Package
                BigDecimal inPoundWt = getInPoundWt(schdAgmtPlnIn, mdse);
                if (inPoundWt == null)  {
                    // Cannot get a Weight
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWZM0654E"));
                    hasErrLine = true;
                    break;
                }
                // GET Priceing Calc Base
                SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPricingCalcBaseList = getSchdAgmtPricingCalcBase(schdAgmtPlnIn);
                if (schdAgmtPricingCalcBaseList == null) {
                    // Not Found, Error
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtLine, "NWAM0739E", new String[]{"Price Calculetion Base", "SCHD_AGMT_PRC_CALC_BASE", "SCHD_AGMT_NUM, SCHD_AGMT_LINE_NUM", schdAgmtPlnIn.schdAgmtNum.getValue() + "," + schdAgmtPlnIn.schdAgmtLineNum.getValue()}));
                    hasErrLine = true;
                    break;
                }
                // Get LineNum
                lineNum = getNextCpoDtlLineNum(lineNum);
                lineNumMap.put(lineNum, schdAgmtLine.schdAgmtLineNum.getValue()); // Add 2019/04/10 QC#31086 
                // Create Pricing API Parameter for detail
                // QC#25014 2018/04/05 Add Start
                // createParamPricingAPIDetail(pricing, schdAgmt, schdAgmtLine, schdAgmtPlnIn, schdAgmtPricingCalcBaseList, lineNum, mdseCd, shipToAddrInfo, slsRepInfo, rtlWhInfo, inPoundWt);
                createParamPricingAPIDetail(pricing, schdAgmt, schdAgmtLine, schdAgmtPlnIn, schdAgmtPricingCalcBaseList, lineNum, mdseCd, shipToAddrInfo, slsRepInfo, dsOrdlineProcDfn, rtlWhInfo, inPoundWt);
                // QC#25014 2018/04/05 Add End

                // Create Pricing API Parameter for Pricing
                createParamPricingAPIPriceCalcBase(pricing, schdAgmtPricingCalcBaseList);

                // Create DS CPO Update API Parameter for detaile
                createParamDsCpoAPIDetail(dsCpo, schdAgmt, schdAgmtLine, schdAgmtPlnIn, lineNum, invtyLocCd, inPoundWt, dsOrdlineProcDfn, defWHParam);

                // Update DS CPO Update API Parameter for detaile MDSE
                updateParamDsCpoAPIDetailMdse(dsCpo, mdseCd, origMdseCd, subsItemCd);

                // Set MDSE in Scheduling Agreement Plan
                ZYPEZDItemValueSetter.setValue(schdAgmtPlnIn.ordMdseCd, mdseCd);
            }
            if (hasErrLine) {
                this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                continue;
            }
            // Call Pricing API
            // Mod Start 2016/10/07 S21_NA#14580
//            String msgId = callAPIPricing(pricing);
//            if (ZYPCommonFunc.hasValue(msgId)) {
//                sendMailInfoList.add(setSendMailInfo(schdAgmt, msgId));
//                if (msgId.endsWith("E")) {
//                    // Error
//                    this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
//                    rollback();
//                    continue;
//                }
//            }
            String msgId ;
            // Mod Start 2019/04/10 QC#31086
            // if (!callAPIPricing(pricing, sendMailInfoList, schdAgmt)) {
            if (!callAPIPricing(pricing, sendMailInfoList, schdAgmt, lineNumMap)) {
                // Mod End 2019/04/10 QC#31086
                // Error
                this.errRecCnt = this.errRecCnt + schdAgmtPlns.size();
                rollback();
                continue;
                
            }
            
            

            
            
            // Update DS CPO Update API Parameter for detaile Pricing
            // 2023/12/06 QC#61281 K.Ikeda START
            // updateParamDsCpoAPIDetailPricing(dsCpo, pricing);
            updateParamDsCpoAPIDetailPricing(dsCpo, pricing, schdAgmt);
            // 2023/12/06 QC#61281 K.Ikeda END
            // Update DS CPO Update API Parameter for CCY Code
            updateParamDsCpoAPIDetailCcyCd(dsCpo, pricing);
            // Create DS CPO Update API Parameter for Price List
            createParamDsCpoAPIPriceList(dsCpo, pricing);
            // Add Start 2019/05/29 QC#50405
            // Update DS CPO Update API Sales Rep
            updateParamDsCpoAPISalesRep(dsCpo);
            // Add End 2019/05/29 QC#50405
            
       
            // Call DS CPO API
            List<NWZC150002PMsg> dsCpoDtlList1 = new ArrayList<NWZC150002PMsg>();
            List<NWZC150003PMsg> dsCpoDtlList2 = new ArrayList<NWZC150003PMsg>();
            // Mod Start 2019/04/10 QC#31086
//            msgId = callAPIDsCpoUpd(dsCpo, dsCpoDtlList1, dsCpoDtlList2);
//            if (ZYPCommonFunc.hasValue(msgId)) {
//                sendMailInfoList.add(setSendMailInfo(schdAgmt, msgId));
//                if (msgId.endsWith("E")) {
//                    // Error
//                    this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
//                    rollback();
//                    continue;
//                }
//            }
            if (!callAPIDsCpoUpd(dsCpo, dsCpoDtlList1, dsCpoDtlList2, sendMailInfoList, lineNumMap, schdAgmt)) {
                // Error
                this.errRecCnt = this.errRecCnt + schdAgmtPlns.size();
                rollback();
                continue;
            }
            // Mod End 2019/04/10 QC#31086

            //Register Invoice Comment
            boolean rc = insertInvoiceCommnet(dsCpo, schdAgmt);
            if (!rc) {
                // Error
                // Mod Start 2019/04/09 QC#31086
                // sendMailInfoList.add(setSendMailInfo(schdAgmt, "NWAM0740E", new String[]{"Invoice Comment", "MSG_TXT_DTL"}));
                sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtPln, "NWAM0740E", new String[]{"Invoice Comment", "MSG_TXT_DTL"}));
                // Mod End 2019/04/09 QC#31086
                this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                rollback();
                continue;
            }
            // Update Scheduling Agreement Plan
            // Mod Start 2019/04/09 QC#31086
            // rc = updateSchdAgmtPln(dsCpo, dsCpoDtlList1, schdAgmtPlns);
            rc = updateSchdAgmtPln(dsCpo, dsCpoDtlList1, schdAgmtPlns, sendMailInfoList, schdAgmt);
            // Mod End 2019/04/09 QC#31086
            if (!rc) {
                // Error
                // sendMailInfoList.add(setSendMailInfo(schdAgmt, "NWAM0741E", new String[]{"Scheduling Agreement Plan", "SCHD_AGMT_PLN"})); // Del 2019/04/10 QC#31086
                this.errRecCnt =  this.errRecCnt + schdAgmtPlns.size();
                rollback();
                continue;
            }
            commit();
            this.normalRecCnt = this.normalRecCnt + schdAgmtPlns.size();
        }
    }

    /**
     * getGroupingList
     * @param schdAgmtPlnList List<SCHD_AGMT_PLNTMsg>
     * @return List<List<SCHD_AGMT_PLNTMsg>>
     */
    private List<List<SCHD_AGMT_PLNTMsg>> getGroupingList(List<SCHD_AGMT_PLNTMsg> schdAgmtPlnList) {

        List<SCHD_AGMT_PLNTMsg> cpoGroup = null;
        List<List<SCHD_AGMT_PLNTMsg>> cpoGroupList = new ArrayList<List<SCHD_AGMT_PLNTMsg>>();

        String preSchdAgmtNum = "";
        String preRddDt = "";
        for (SCHD_AGMT_PLNTMsg schdAgmtPln : schdAgmtPlnList)  {
            if (!preSchdAgmtNum.equals(schdAgmtPln.schdAgmtNum.getValue()) || !preRddDt.equals(schdAgmtPln.rddDt.getValue())) {
                if (cpoGroup != null) {
                    cpoGroupList.add(cpoGroup);
                }
                cpoGroup = new ArrayList<SCHD_AGMT_PLNTMsg>();
            }
            cpoGroup.add(schdAgmtPln);
            preSchdAgmtNum = schdAgmtPln.schdAgmtNum.getValue();
            preRddDt = schdAgmtPln.rddDt.getValue();
        }
        if (cpoGroup != null) {
            cpoGroupList.add(cpoGroup);
        }
        return cpoGroupList;
    }

    /**
     * Get SchdAgmt
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @return SCHD_AGMTTMsg
     */
    private SCHD_AGMTTMsg getSchdAgmt(SCHD_AGMT_PLNTMsg schdAgmtPln) {
        // Get SCHD_AGMT
        SCHD_AGMTTMsg schdAgmt = new SCHD_AGMTTMsg();
        ZYPEZDItemValueSetter.setValue(schdAgmt.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(schdAgmt.schdAgmtNum, schdAgmtPln.schdAgmtNum);
        schdAgmt = (SCHD_AGMTTMsg) EZDTBLAccessor.findByKey(schdAgmt);
        if (schdAgmt == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmt.getReturnCode())) {
            return null;
        }
        return schdAgmt;
    }

    /**
     * Get ShipToAddrInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getShipToAddrInfo(SCHD_AGMTTMsg schdAgmt) {
        // Get ShipToAddrInfo
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("shipToCustCd", schdAgmt.shipToCustLocCd.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        Map<String, Object> shipToaddrInfo = (Map<String, Object>) ssmBatchClient.queryObject("getShipToAddrInfo", ssmParam);
        return shipToaddrInfo;
    }

    /**
     * Get DsOrdTpProcDfn
     * @param schdAgmt DS_ORD_TP_PROC_DFNTMsg
     * @return SCHD_AGMTTMsg
     */
    private DS_ORD_TP_PROC_DFNTMsg getDsOrdTpProcDfn(SCHD_AGMTTMsg schdAgmt) {
        // Get DS_ORD_TP_PROC_DFN
        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.dsOrdTpCd, schdAgmt.dsOrdTpCd);
        dsOrdTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dsOrdTpProcDfn);
        if (dsOrdTpProcDfn == null || !S21CacheTBLAccessor.RTNCD_NORMAL.equals(dsOrdTpProcDfn.getReturnCode())) {
            return null;
        }
        if (0 <= this.salesDate.compareTo(dsOrdTpProcDfn.effFromDt.getValue()) && (0 <= dsOrdTpProcDfn.effThruDt.getValue().compareTo(this.salesDate) || !ZYPCommonFunc.hasValue(dsOrdTpProcDfn.effThruDt))
                && ZYPConstant.FLG_ON_Y.equals(dsOrdTpProcDfn.actvFlg.getValue())) {
            return dsOrdTpProcDfn;
        }
        return null;
    }

    /**
     * Get DsOrdlineProcDfn
     * @param schdAgmt SCHD_AGMTTMsg
     * @return DS_ORD_LINE_PROC_DFNTMsg
     */
    private DS_ORD_LINE_PROC_DFNTMsg getDsOrdlineProcDfn(SCHD_AGMTTMsg schdAgmt) {
        // Get DS_ORD_LINE_PROC_DFN
        DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn = new DS_ORD_LINE_PROC_DFNTMsg();
        dsOrdlineProcDfn.setSQLID("001");
        dsOrdlineProcDfn.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsOrdlineProcDfn.setConditionValue("dsOrdTpCd01", schdAgmt.dsOrdTpCd.getValue());
        dsOrdlineProcDfn.setConditionValue("effFromDt01", this.salesDate);
        dsOrdlineProcDfn.setConditionValue("effThruDt01", this.salesDate);
        dsOrdlineProcDfn.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        dsOrdlineProcDfn.setConditionValue("primLineCatgFlg01", ZYPConstant.FLG_ON_Y);

        DS_ORD_LINE_PROC_DFNTMsgArray dsOrdlineProcDfnList = (DS_ORD_LINE_PROC_DFNTMsgArray) EZDTBLAccessor.findByCondition(dsOrdlineProcDfn);
        if (dsOrdlineProcDfnList == null || dsOrdlineProcDfnList.getValidCount() == 0) {
            return null;
        }
        if (dsOrdlineProcDfnList.getValidCount() == 2) {
            dsOrdlineProcDfn = getOutBoundProcDfn(dsOrdlineProcDfnList);
        } else {
            dsOrdlineProcDfn = dsOrdlineProcDfnList.no(0);
        }
        return dsOrdlineProcDfn;
    }

    private DS_ORD_LINE_PROC_DFNTMsg getOutBoundProcDfn(DS_ORD_LINE_PROC_DFNTMsgArray dsOrdlineProcDfnList) {
        DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn = new DS_ORD_LINE_PROC_DFNTMsg();
        
        for (int i = 0 ; i < dsOrdlineProcDfnList.length(); i++) {
            dsOrdlineProcDfn = dsOrdlineProcDfnList.no(i);
            
            DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd , dsOrdlineProcDfn.dsOrdLineCatgCd);
            tMsg = (DS_ORD_LINE_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);
            
            if (tMsg != null) {
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(tMsg.dsOrdLineDrctnCd.getValue())) {
                    break; 
                }
            }
        }
        return dsOrdlineProcDfn;
    }
    
    /**
     * Get SchdAgmtCtacPsns
     * @param schdAgmt SCHD_AGMTTMsg
     * @return SCHD_AGMT_CTAC_PSNTMsgArray
     */
    private SCHD_AGMT_CTAC_PSNTMsgArray getSchdAgmtCtacPsns(SCHD_AGMTTMsg schdAgmt) {
        SCHD_AGMT_CTAC_PSNTMsg schdAgmtCtacPsn = new SCHD_AGMT_CTAC_PSNTMsg();
        schdAgmtCtacPsn.setSQLID("001");
        schdAgmtCtacPsn.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        schdAgmtCtacPsn.setConditionValue("schdAgmtNum01", schdAgmt.schdAgmtNum.getValue());

        SCHD_AGMT_CTAC_PSNTMsgArray schdAgmtCtacPsnList = (SCHD_AGMT_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(schdAgmtCtacPsn);
        if (schdAgmtCtacPsnList == null || schdAgmtCtacPsnList.getValidCount() == 0) {
            return null;
        }
        return schdAgmtCtacPsnList;
    }

    /**
     * Get SchdAgmtSlsCrs
     * @param schdAgmt SCHD_AGMTTMsg
     * @return SCHD_AGMT_SLS_CRTMsgArray
     */
    private SCHD_AGMT_SLS_CRTMsgArray getSchdAgmtSlsCrs(SCHD_AGMTTMsg schdAgmt) {
        SCHD_AGMT_SLS_CRTMsg schdAgmtSlsCr = new SCHD_AGMT_SLS_CRTMsg();
        schdAgmtSlsCr.setSQLID("001");
        schdAgmtSlsCr.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        schdAgmtSlsCr.setConditionValue("schdAgmtNum01", schdAgmt.schdAgmtNum.getValue());

        SCHD_AGMT_SLS_CRTMsgArray schdAgmtSlsCrList = (SCHD_AGMT_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(schdAgmtSlsCr);
        if (schdAgmtSlsCrList == null || schdAgmtSlsCrList.getValidCount() == 0) {
            return null;
        }
        return schdAgmtSlsCrList;
    }

    /**
     * Get SchdAgmtLine
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @return SCHD_AGMT_LINETMsg
     */
    private SCHD_AGMT_LINETMsg getSchdAgmtLine(SCHD_AGMT_PLNTMsg schdAgmtPln) {
        SCHD_AGMT_LINETMsg schdAgmtLine = new SCHD_AGMT_LINETMsg();
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.schdAgmtNum, schdAgmtPln.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.schdAgmtLineNum, schdAgmtPln.schdAgmtLineNum);
        schdAgmtLine = (SCHD_AGMT_LINETMsg) EZDTBLAccessor.findByKey(schdAgmtLine);
        if (schdAgmtLine == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtLine.getReturnCode())) {
            return null;
        }
        return schdAgmtLine;
    }

    // START 2017/09/26 K.Kim [QC#18744, ADD]
    /**
     * Get SplyInclFlg
     * @param dsContrDtlPk BigDecimal
     * @return String
     */
    private String getSplyInclFlg(BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (String) this.ssmBatchClient.queryObject("getSplyInclFlg", ssmParam);
    }
    // END 2017/09/26 K.Kim [QC#18744, ADD]

    /**
     * Get DefaultWh
     * @param pmsg NWZC180001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @param mdse MDSETMsg
     * @return String
     */
    private String getDefaultWh(NWZC180001PMsg pmsg, SCHD_AGMTTMsg schdAgmt, SCHD_AGMT_PLNTMsg schdAgmtPln, MDSETMsg mdse) {

        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(pmsg.dsOrdCatgCd, schdAgmt.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pmsg.dsOrdTpCd, schdAgmt.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, mdse.mdseCd);
        ZYPEZDItemValueSetter.setValue(pmsg.postCd, schdAgmt.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(pmsg.ordQty, schdAgmtPln.ordQty);
        NWZC180001 defWHAPI = new NWZC180001();
        defWHAPI.execute(pmsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(pmsg).isEmpty()) {
            for (int j = 0; j < pmsg.xxMsgIdList.getValidCount(); j++) {
                String msgId = pmsg.xxMsgIdList.no(j).xxMsgId.getValue();
                return msgId;
            }
        }
        return null;
    }
    /**
     * Get RtlWhInfo
     * @param rtlWhCd Map<String, Object>
     * @return String
     */
    private Map<String, Object> getRtlWhInfo(String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        Map<String, Object> rtlWhInfo = (Map<String, Object>) ssmBatchClient.queryObject("getRtlWhInfo", ssmParam);

        return rtlWhInfo;
    }

    /**
     * Get SlsRepInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getSlsRepInfo(SCHD_AGMTTMsg schdAgmt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("tocCd", schdAgmt.slsRepTocCd.getValue());
        ssmParam.put("slsDt", this.salesDate);
        Map<String, Object> slsRepInfo = (Map<String, Object>) ssmBatchClient.queryObject("getSlsRepInfo", ssmParam);

        return slsRepInfo;
    }

    /**
     * Get InvtyLocTpCd
     * @param invtyLocCd String
     * @return String
     */
    private String getInvtyLocTpCd(String invtyLocCd) {

        SHIP_FROM_LOC_LIST_VTMsg shipFromLoc = new SHIP_FROM_LOC_LIST_VTMsg();
        shipFromLoc.setSQLID("001");
        shipFromLoc.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        shipFromLoc.setConditionValue("invtyLocCd01", invtyLocCd);

        SHIP_FROM_LOC_LIST_VTMsgArray shipFromLocList = (SHIP_FROM_LOC_LIST_VTMsgArray) EZDTBLAccessor.findByCondition(shipFromLoc);
        if (shipFromLocList == null || shipFromLocList.getValidCount() == 0) {
            return null;
        }
        return shipFromLocList.no(0).invtyLocTpCd.getValue();
    }

    // 2018/02/05 S21_NA#20173 Del Start
//    /**
//     * Get SupersedeMdse CallAPI
//     * @param pmsg NWZC206001PMsg
//     * @param invtyLocCd String
//     * @param invtyLocTpCd String
//     * @param stkStsCd String 
//     * @param mdse String
//     * @return String
//     */
//    private String getSupersedeMdseCallAPI(NWZC206001PMsg pmsg, String invtyLocCd, String invtyLocTpCd, String stkStsCd, String mdse) {
//
//        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pmsg.slsDt, this.salesDate);
//        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, mdse);
//
//        if (LOC_TP.WAREHOUSE.equals(invtyLocTpCd)) {
//            ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NWZC206001Constant.SUPD_LIST_MODE);
//            ZYPEZDItemValueSetter.setValue(pmsg.whCd, invtyLocCd);
//            ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, stkStsCd);
//
//        } else if (LOC_TP.VENDOR.equals(invtyLocTpCd)) {
//            ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NWZC206001Constant.SUPD_LATEST_MODE);
//
//        } else {
//            // Error
//            return "NWAM0742E";
//        }
//        ZYPEZDItemValueSetter.setValue(pmsg.xxAvalOrdFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(pmsg.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);
//
//        NWZC206001 superSedeAPI = new NWZC206001();
//        superSedeAPI.execute(pmsg, ONBATCH_TYPE.BATCH);
//
//        if (!S21ApiUtil.getXxMsgIdList(pmsg).isEmpty()) {
//            for (int j = 0; j < pmsg.xxMsgIdList.getValidCount(); j++) {
//                String msgId = pmsg.xxMsgIdList.no(j).xxMsgId.getValue();
//                return msgId;
//            }
//        }
//        return null;
//    }
//    
//    /**
//     * Get SupersedeMdse
//     * @param pmsg NWZC206001PMsg
//     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
//     * @return String
//     */
//    private String getSupersedeMdse(NWZC206001PMsg pmsg, SCHD_AGMT_PLNTMsg schdAgmtPln) {
//
//        String mdseCd = null;
//        if (NWZC206001Constant.SUPD_LIST_MODE.equals(pmsg.xxModeCd.getValue())) {
//
//            for (int i = 0; i < pmsg.A.getValidCount(); i++) {
//                NWZC206001_APMsg supd = pmsg.A.no(i);
//                if (0 <= supd.invtyAvalQty.getValue().compareTo(schdAgmtPln.ordQty.getValue())) {
//                    // Stocked In Inventory
//                    return supd.mdseCd.getValue();
//                }
//                mdseCd = supd.mdseCd.getValue();
//            }
//
//        } else if (NWZC206001Constant.SUPD_LATEST_MODE.equals(pmsg.xxModeCd.getValue())) {
//            if (0 < pmsg.A.getValidCount()) {
//                mdseCd = pmsg.A.no(0).mdseCd.getValue();
//            }
//        }
//        return mdseCd;
//    }
    // 2018/02/05 S21_NA#20173 Del End

    // 2018/02/05 S21_NA#20173 Add Start
    /**
     * Get SupersedeMdse CallAPI
     * @param pmsg NWZC192001PMsg
     * @param invtyLocCd String
     * @param invtyLocTpCd String
     * @param schdAgmt SCHD_AGMTTMsg 
     * @param schdAgmtLine SCHD_AGMT_LINETMsg
     * @param schdAgmtPlnIn SCHD_AGMT_PLNTMsg
     * @param shipToAddrInfo Map<String, Object>
     * @return String
     */
    private String getSupersedeMdseCallAPI(NWZC192001PMsg pmsg, String invtyLocCd, String invtyLocTpCd, SCHD_AGMTTMsg schdAgmt, SCHD_AGMT_LINETMsg schdAgmtLine, SCHD_AGMT_PLNTMsg schdAgmtPlnIn, Map<String, Object> shipToAddrInfo) {

        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pmsg.origMdseCd, schdAgmtLine.mdseCd);
        ZYPEZDItemValueSetter.setValue(pmsg.dsOrdCatgCd, schdAgmt.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pmsg.dsOrdTpCd, schdAgmt.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pmsg.sbstMdseCd, schdAgmtLine.sbstMdseCd);
        ZYPEZDItemValueSetter.setValue(pmsg.ordQty, schdAgmtPlnIn.ordQty);
        if (shipToAddrInfo != null) {
            ZYPEZDItemValueSetter.setValue(pmsg.shipToPostCd, (String) shipToAddrInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(pmsg.shipToLocNum, (String) shipToAddrInfo.get("LOC_NUM"));
        } else {

            SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
            condition.setSQLID("002");
            condition.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            condition.setConditionValue("shipToCustCd01", schdAgmt.shipToCustLocCd.getValue());
            condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

            if (tmsgArray.getValidCount() > 0) {
                SHIP_TO_CUSTTMsg tMsg = tmsgArray.no(0);
                ZYPEZDItemValueSetter.setValue(pmsg.shipToPostCd, tMsg.postCd);
                ZYPEZDItemValueSetter.setValue(pmsg.shipToLocNum, tMsg.locNum);
            }
        }

        if (LOC_TP.WAREHOUSE.equals(invtyLocTpCd)) {
            ZYPEZDItemValueSetter.setValue(pmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pmsg.rtlWhCd, invtyLocCd);
        } else if (LOC_TP.VENDOR.equals(invtyLocTpCd)) {
            ZYPEZDItemValueSetter.setValue(pmsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
        } else {
            // Error
            return "NWAM0742E";
        }

        NWZC192001 superSedeCommonAPI = new NWZC192001();
        superSedeCommonAPI.execute(pmsg, ONBATCH_TYPE.BATCH);

        if (!S21ApiUtil.getXxMsgIdList(pmsg).isEmpty()) {
            for (int j = 0; j < pmsg.xxMsgIdList.getValidCount(); j++) {
                String msgId = pmsg.xxMsgIdList.no(j).xxMsgId.getValue();
                return msgId;
            }
        }
        return null;
    }
    // 2018/02/05 S21_NA#20173 Del End

    /**
     * Get InPoundWt
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @param mdse MDSETMsg
     * @return BigDecimal
     */
    private BigDecimal getInPoundWt(SCHD_AGMT_PLNTMsg schdAgmtPln, MDSETMsg mdse) {

        MDSE_STORE_PKGTMsg pkg = new MDSE_STORE_PKGTMsg();
        ZYPEZDItemValueSetter.setValue(pkg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pkg.pkgUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(pkg.mdseCd, mdse.mdseCd);
        pkg = (MDSE_STORE_PKGTMsg) EZDTBLAccessor.findByKey(pkg);

        if (pkg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(pkg.getReturnCode())) {
            return null;
        }
        if (schdAgmtPln.ordQty.getValueInt() == 0 || pkg.inPoundWt.getValueInt() == 0) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal wt = pkg.inPoundWt.getValue().multiply(schdAgmtPln.ordQty.getValue());
            if (wt.compareTo(IN_POUND_WT_MAX) < 0) {
                return wt;
            } else {
                return null;
            }
        }
    }

    /**
     * Get SchdAgmtPricingCalcBase
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @return SCHD_AGMT_PRC_CALC_BASETMsgArray
     */
    private SCHD_AGMT_PRC_CALC_BASETMsgArray getSchdAgmtPricingCalcBase(SCHD_AGMT_PLNTMsg schdAgmtPln) {

        SCHD_AGMT_PRC_CALC_BASETMsg schdAgmtPrcCalcBase = new SCHD_AGMT_PRC_CALC_BASETMsg();
        schdAgmtPrcCalcBase.setSQLID("001");
        schdAgmtPrcCalcBase.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        schdAgmtPrcCalcBase.setConditionValue("schdAgmtNum01", schdAgmtPln.schdAgmtNum.getValue());
        schdAgmtPrcCalcBase.setConditionValue("schdAgmtLineNum01", schdAgmtPln.schdAgmtLineNum.getValue());

        SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPrcCalcBaseList = (SCHD_AGMT_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(schdAgmtPrcCalcBase);
        if (schdAgmtPrcCalcBaseList == null || schdAgmtPrcCalcBaseList.getValidCount() == 0) {
            return null;
        }
        return schdAgmtPrcCalcBaseList;
    }

    /**
     * Get CoaBrCd
     * @param slsRepTocCd String
     * @return String
     */
    private String getCoaBrCd(String slsRepTocCd) {
        TOCTMsg toc = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(toc.tocCd, slsRepTocCd);
        toc = (TOCTMsg) EZDTBLAccessor.findByKey(toc);
        if (toc == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(toc.getReturnCode())) {
            return null;
        }
        return toc.coaBrCd.getValue();
    }

    /**
     * Get DsBillToCust
     * @param billToCustCd String
     * @return BILL_TO_CUSTTMsg
     */
    private BILL_TO_CUSTTMsg getDsBillToCust(String billToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        BILL_TO_CUSTTMsg dsBilToCust = (BILL_TO_CUSTTMsg) ssmBatchClient.queryObject("getDsBillToCust", ssmParam);

        return dsBilToCust;
    }

    /**
     * getDsShipToCust
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg getDsShipToCust(String shipToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsg dsShipToCust = (SHIP_TO_CUSTTMsg) ssmBatchClient.queryObject("getDsShipToCust", ssmParam);

        return dsShipToCust;

    }

    /**
     * Create Param PricingAPI Header
     * @param pricing NWZC157001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     * @param dsOrdTpProcDfn DS_ORD_TP_PROC_DFNTMsg
     */
    private void createParamPricingAPIHeader(NWZC157001PMsg pricing, SCHD_AGMTTMsg schdAgmt, DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn) {

        ZYPEZDItemValueSetter.setValue(pricing.glblCmpyCd, this.glblCmpyCd);
        // QC#7451 2016/04/22 Mod Start
        //ZYPEZDItemValueSetter.setValue(pricing.xxModeCd, NWZC157001.RECALC);
        ZYPEZDItemValueSetter.setValue(pricing.xxModeCd, NWZC157001.GET_ORDER_ALL);
        // QC#7451 2016/04/22 Mod End
        // QC#9437 2016/06/21 Mod Start
        ZYPEZDItemValueSetter.setValue(pricing.prcBaseDt, salesDate);
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(pricing.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(pricing.orgRqstDelyDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pricing.dsOrdCatgCd, schdAgmt.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pricing.dsOrdTpCd, schdAgmt.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pricing.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pricing.taxExemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pricing.lineBizTpCd, dsOrdTpProcDfn.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pricing.dsAcctNum, schdAgmt.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pricing.coaBrCd, getCoaBrCd(schdAgmt.slsRepTocCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pricing.cpoSrcTpCd, CPO_SRC_TP.SCHEDULE_AGREEMENT_ENTRY);
        ZYPEZDItemValueSetter.setValue(pricing.custIssPoNum, schdAgmt.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(pricing.taxFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pricing.spclHdlgTpCd, schdAgmt.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(pricing.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

    }

    /**
     * Create Param PricingAPI Detail
     * @param pricing NWZC157001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtLine SCHD_AGMT_LINETMsg
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @param schdAgmtPricingCalcBaseList SCHD_AGMT_PRC_CALC_BASETMsgArray
     * @param lineNum String
     * @param mdseCd String
     * @param shipToAddrInfo Map<String, Object>
     * @param slsRepInfo Map<String, Object>
     * @param rtlWhInfo Map<String, Object>
     * @param inPoundWt BigDecimal
     */
    private void createParamPricingAPIDetail(
            NWZC157001PMsg pricing
            , SCHD_AGMTTMsg schdAgmt
            , SCHD_AGMT_LINETMsg schdAgmtLine
            , SCHD_AGMT_PLNTMsg schdAgmtPln
            , SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPricingCalcBaseList
            , String lineNum
            , String mdseCd
            , Map<String, Object> shipToAddrInfo
            , Map<String, Object> slsRepInfo
            , DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn // QC#25014 2018/04/05 Add
            , Map<String, Object> rtlWhInfo
            , BigDecimal inPoundWt) {

        NWZC157002PMsg dtl = pricing.NWZC157002PMsg.no(pricing.NWZC157002PMsg.getValidCount());

        ZYPEZDItemValueSetter.setValue(dtl.trxLineNum, lineNum);
        ZYPEZDItemValueSetter.setValue(dtl.trxLineSubNum, LINE_SUB_NUM_DEF);
        ZYPEZDItemValueSetter.setValue(dtl.billToCustCd, schdAgmt.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(dtl.prcBaseDt, schdAgmt.prcBaseDt); // QC#9437 2016/06/21 Add
        BILL_TO_CUSTTMsg dsBilToCust = getDsBillToCust(schdAgmt.billToCustLocCd.getValue());
        if (dsBilToCust != null) {
            ZYPEZDItemValueSetter.setValue(dtl.dsTaxGrpExemCd_B, dsBilToCust.dsTaxGrpExemCd);
            ZYPEZDItemValueSetter.setValue(dtl.dsTaxGrpExemReslFlg_B, dsBilToCust.dsTaxExemFlg);
        }
        ZYPEZDItemValueSetter.setValue(dtl.shipToCustCd, schdAgmt.shipToCustLocCd);
        SHIP_TO_CUSTTMsg dsShipToCust = getDsShipToCust(schdAgmt.shipToCustLocCd.getValue());

        // 2018/12/18 QC#29714 Mod Start
        //if (dsBilToCust != null) {
        if (dsShipToCust != null) {
        // 2018/12/18 QC#29714 Mod End
            ZYPEZDItemValueSetter.setValue(dtl.dsTaxGrpExemCd_S, dsShipToCust.dsTaxGrpExemCd);
            ZYPEZDItemValueSetter.setValue(dtl.dsTaxGrpExemReslFlg_S, dsShipToCust.dsTaxExemFlg);
        }
        ZYPEZDItemValueSetter.setValue(dtl.dsAcctNum_BL, schdAgmt.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtl.dsAcctNum_SH, schdAgmt.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtl.prcCatgCd, schdAgmt.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(dtl.ccyCd, schdAgmtLine.ccyCd);
        ZYPEZDItemValueSetter.setValue(dtl.coaBrCd, pricing.coaBrCd);
        ZYPEZDItemValueSetter.setValue(dtl.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(dtl.pkgUomCd, schdAgmtLine.pkgUomCd);
        // QC#25014 2018/04/05 Add Start
        ZYPEZDItemValueSetter.setValue(dtl.dsOrdLineCatgCd, dsOrdlineProcDfn.dsOrdLineCatgCd);
        // QC#25014 2018/04/05 Add End
        ZYPEZDItemValueSetter.setValue(dtl.ordCustUomQty, schdAgmtPln.ordQty);
        ZYPEZDItemValueSetter.setValue(dtl.ordQty, schdAgmtPln.ordQty);

        if (shipToAddrInfo != null) {
            ZYPEZDItemValueSetter.setValue(dtl.firstLineAddr_SH, (String) shipToAddrInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.scdLineAddr_SH, (String) shipToAddrInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.ctyAddr_SH, (String) shipToAddrInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.stCd_SH, (String) shipToAddrInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(dtl.cntyNm_SH, (String) shipToAddrInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(dtl.postCd_SH, (String) shipToAddrInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(dtl.ctryCd_SH, (String) shipToAddrInfo.get("CTRY_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(dtl.firstLineAddr_SH, schdAgmt.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(dtl.scdLineAddr_SH, schdAgmt.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(dtl.ctyAddr_SH, schdAgmt.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(dtl.stCd_SH, schdAgmt.shipToStCd);
            ZYPEZDItemValueSetter.setValue(dtl.cntyNm_SH, schdAgmt.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(dtl.postCd_SH, schdAgmt.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(dtl.ctryCd_SH, schdAgmt.shipToCtryCd);
        }

        if (slsRepInfo != null) {
            // ZYPEZDItemValueSetter.setValue(dtl.taxAreaId_SR, (String) slsRepInfo.get("TAX_AREA_ID")); // QC#9192 2016/09/29 Del
            ZYPEZDItemValueSetter.setValue(dtl.dsInsdCtyLimitFlg_SR, (String) slsRepInfo.get("DS_INSD_CTY_LIMIT_FLG"));
            ZYPEZDItemValueSetter.setValue(dtl.firstLineAddr_SR, (String) slsRepInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.scdLineAddr_SR, (String) slsRepInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.ctyAddr_SR, (String) slsRepInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.stCd_SR, (String) slsRepInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(dtl.cntyNm_SR, (String) slsRepInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(dtl.postCd_SR, (String) slsRepInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(dtl.ctryCd_SR, (String) slsRepInfo.get("CTRY_CD"));
        }

        if (rtlWhInfo != null) {
            // ZYPEZDItemValueSetter.setValue(dtl.taxAreaId_SF, (String) rtlWhInfo.get("TAX_AREA_ID")); // QC#9192 2016/09/29 Del
            ZYPEZDItemValueSetter.setValue(dtl.firstLineAddr_SF, (String) rtlWhInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.scdLineAddr_SF, (String) rtlWhInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.ctyAddr_SF, (String) rtlWhInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(dtl.stCd_SF, (String) rtlWhInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(dtl.cntyNm_SF, (String) rtlWhInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(dtl.postCd_SF, (String) rtlWhInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(dtl.ctryCd_SF, (String) rtlWhInfo.get("CTRY_CD"));
        }
        ZYPEZDItemValueSetter.setValue(dtl.frtCondCd, schdAgmt.frtCondCd);
        ZYPEZDItemValueSetter.setValue(dtl.unitNetWt, inPoundWt);
        ZYPEZDItemValueSetter.setValue(dtl.shpgSvcLvlCd, schdAgmt.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dtl.frtChrgToCd, schdAgmt.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(dtl.frtChrgMethCd, schdAgmt.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(dtl.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

        // QC#6480 2016/06/13 Add Start
        SCHD_AGMT_PRC_CALC_BASETMsg calcBase = null;
        BigDecimal sellPrice = BigDecimal.ZERO;
        BigDecimal basePrice = BigDecimal.ZERO;
        for(int i = 0; i < schdAgmtPricingCalcBaseList.getValidCount(); i++){
             calcBase = schdAgmtPricingCalcBaseList.no(i);
            if(PRC_COND_TP.BASE_PRICE.equals(calcBase.prcCondTpCd.getValue())){
                sellPrice = sellPrice.add(calcBase.unitPrcAmt.getValue());
                basePrice = basePrice.add(calcBase.unitPrcAmt.getValue());
            } else if(PRC_COND_TP.MANUAL_PRICE.equals(calcBase.prcCondTpCd.getValue())){
                sellPrice = sellPrice.subtract(calcBase.unitPrcAmt.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(dtl.xxUnitPrc, sellPrice);
        if(basePrice.compareTo(sellPrice) == 0){
            ZYPEZDItemValueSetter.setValue(dtl.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        }else{
            ZYPEZDItemValueSetter.setValue(dtl.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
        }
        // QC#6480 2016/06/13 Add End
        pricing.NWZC157002PMsg.setValidCount(pricing.NWZC157002PMsg.getValidCount() + 1);
    }

    /**
     * Create Param PricingAPI PriceCalcBase
     * @param pricing NWZC157001PMsg
     * @param schdAgmtPricingCalcBaseList SCHD_AGMT_PRC_CALC_BASETMsgArray
     */
    private void createParamPricingAPIPriceCalcBase(NWZC157001PMsg pricing, SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPricingCalcBaseList) {

        NWZC157002PMsg dtl = pricing.NWZC157002PMsg.no(pricing.NWZC157002PMsg.getValidCount() - 1);

        for (int i = 0; i < schdAgmtPricingCalcBaseList.getValidCount(); i++) {
            NWZC157003PMsg prcCalcBase = dtl.NWZC157003PMsg.no(dtl.NWZC157003PMsg.getValidCount());
            SCHD_AGMT_PRC_CALC_BASETMsg schdAgmtPricingCalcBase = schdAgmtPricingCalcBaseList.no(i);

            ZYPEZDItemValueSetter.setValue(prcCalcBase.trxLineNum, dtl.trxLineNum);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.trxLineSubNum, dtl.trxLineSubNum);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcDtlGrpCd, schdAgmtPricingCalcBase.prcDtlGrpCd);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcCatgCd, dtl.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcCondManEntryFlg, schdAgmtPricingCalcBase.prcCondManEntryFlg);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcCondManAddFlg, schdAgmtPricingCalcBase.prcCondManAddFlg);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcCondManDelFlg, schdAgmtPricingCalcBase.prcCondManDelFlg);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.pkgUomCd, schdAgmtPricingCalcBase.pkgUomCd);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcCondUnitCd, schdAgmtPricingCalcBase.prcCondUnitCd);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.autoPrcCcyCd, schdAgmtPricingCalcBase.autoPrcCcyCd);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.autoPrcAmtRate, schdAgmtPricingCalcBase.autoPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.maxPrcAmtRate, schdAgmtPricingCalcBase.maxPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.manPrcAmtRate, schdAgmtPricingCalcBase.manPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.calcPrcAmtRate, schdAgmtPricingCalcBase.calcPrcAmtRate);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.unitPrcAmt, schdAgmtPricingCalcBase.unitPrcAmt);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.specCondPrcPk, schdAgmtPricingCalcBase.specCondPrcPk);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcCondTpCd, schdAgmtPricingCalcBase.prcCondTpCd);
            // 2018/09/14 QC#9700 add start
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcRuleApplyFlg, schdAgmtPricingCalcBase.prcRuleApplyFlg);
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcRulePrcdPk, schdAgmtPricingCalcBase.prcRulePrcdPk);
            // 2018/09/14 QC#9700 add end

            Map<String, String> prcCondTpMap = new HashMap<String, String>();
            String prcCondTpDescTxt = null;
            if (ZYPCommonFunc.hasValue(schdAgmtPricingCalcBase.prcCondTpCd)) {
                prcCondTpDescTxt = prcCondTpMap.get(schdAgmtPricingCalcBase.prcCondTpCd.getValue());
                if (null == prcCondTpDescTxt) {
                    prcCondTpDescTxt = getPrcCondTpDescTxt(glblCmpyCd, schdAgmtPricingCalcBase.prcCondTpCd.getValue());
                    if (null != prcCondTpDescTxt) {
                        prcCondTpMap.put(schdAgmtPricingCalcBase.prcCondTpCd.getValue(), prcCondTpDescTxt);
                    } else {
                        prcCondTpMap.put(schdAgmtPricingCalcBase.prcCondTpCd.getValue(), "");
                    }
                }
            }
            ZYPEZDItemValueSetter.setValue(prcCalcBase.prcCondTpDescTxt, prcCondTpDescTxt);
            dtl.NWZC157003PMsg.setValidCount(dtl.NWZC157003PMsg.getValidCount() + 1);
        }
    }

    private static String getPrcCondTpDescTxt(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg prcCondTpTMsg = getPrcCondTp(glblCmpyCd, prcCondTpCd);
        if (null != prcCondTpTMsg) {
            return prcCondTpTMsg.prcCondTpDescTxt.getValue();
        }
        return null;
    }

    private static PRC_COND_TPTMsg getPrcCondTp(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg prcCondTpTMsgKey = new PRC_COND_TPTMsg();
        prcCondTpTMsgKey.glblCmpyCd.setValue(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCondTpTMsgKey.prcCondTpCd, prcCondTpCd);
        return (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(prcCondTpTMsgKey);
    }
    
    // Mod Start 2016/10/07 S21_NA#14580
//    /**
//     * call API Pricing
//     * @param pricing  NWZC157001PMsg
//     * @return String
//     */
//    private String callAPIPricing(NWZC157001PMsg pricing) {
//
//        // Call Pricing API [NWZC157001]
//        NWZC157001 prcAPI = new NWZC157001();
//        prcAPI.execute(pricing, ONBATCH_TYPE.BATCH);
//
//        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pricing);
//        for (S21ApiMessage msg : msgList) {
//            String msgId = msg.getXxMsgid();
//            if (ZYPCommonFunc.hasValue(msgId)) {
//                return msgId;
//            }
//        }
//
//        for (int i = 0; i < pricing.NWZC157002PMsg.getValidCount(); i++) {
//            NWZC157002PMsg prcLine = pricing.NWZC157002PMsg.no(i);
//
//            List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(prcLine);
//            for (S21ApiMessage msg : msgListIn) {
//                String msgId = msg.getXxMsgid();
//                if (ZYPCommonFunc.hasValue(msgId)) {
//                    return msgId;
//                }
//            }
//        }
//        return null;
//    }
    /**
     * call API Pricing
     * @param pricing  NWZC157001PMsg
     * @return boolean Error: false
     */
    // Mod Start 2019/04/10 QC#31086
    // private boolean callAPIPricing(NWZC157001PMsg pricing, List<SendMailInfoBean> sendMailInfoList, SCHD_AGMTTMsg schdAgmt) {
    private boolean callAPIPricing(NWZC157001PMsg pricing, List<SendMailInfoBean> sendMailInfoList, SCHD_AGMTTMsg schdAgmt, Map<String, BigDecimal> lineNumMap) {
    // Mod End2019/04/10 QC#31086

        // Call Pricing API [NWZC157001]
        NWZC157001 prcAPI = new NWZC157001();
        prcAPI.execute(pricing, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pricing)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pricing);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();

                if (ZYPCommonFunc.hasValue(msgId)) {
                    // Mod Start 2019/04/10 QC#31086
                    // sendMailInfoList.add(setSendMailInfo(schdAgmt, msgId, msgPrms));
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, lineNumMap.get("001"), msgId, msgPrms));
                    // Mod End 2019/04/10 QC#31086
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                }
            }
        }
        
        for (int i = 0; i < pricing.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLine = pricing.NWZC157002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(prcLine)) {

                List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(prcLine);
                for (S21ApiMessage msg : msgListIn) {
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();

                    if (ZYPCommonFunc.hasValue(msgId)) {
                        // Mod Start 2019/04/10 QC#31086
                        // sendMailInfoList.add(setSendMailInfo(schdAgmt, msgId, msgPrms));
                        sendMailInfoList.add(setSendMailInfo(schdAgmt, lineNumMap.get(prcLine.trxLineNum.getValue()), msgId, msgPrms));
                        // Mod End 2019/04/10 QC#31086
                        if (msgId.endsWith("E")) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    // Mod End 2016/10/07 S21_NA#14580

    /**
     * Create Param DsCpoAPI Header
     * @param dsCpo NWZC150001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtPln List<SCHD_AGMT_PLNTMsg>
     */
    private void createParamDsCpoAPIHeader(NWZC150001PMsg dsCpo, SCHD_AGMTTMsg schdAgmt, List<SCHD_AGMT_PLNTMsg> schdAgmtPln) {

        ZYPEZDItemValueSetter.setValue(dsCpo.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.xxModeCd, NWZC150001Constant.MODE_SUBMIT);
        ZYPEZDItemValueSetter.setValue(dsCpo.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(dsCpo.usrId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(dsCpo.bizAppId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(dsCpo.cpoOrdTpCd, schdAgmt.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.dsOrdCatgCd, schdAgmt.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.dsOrdTpCd, schdAgmt.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.dsOrdRsnCd, schdAgmt.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.custIssPoNum, schdAgmt.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(dsCpo.custIssPoDt, schdAgmt.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(dsCpo.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(dsCpo.cpoSrcTpCd, CPO_SRC_TP.SCHEDULE_AGREEMENT_ENTRY);
        ZYPEZDItemValueSetter.setValue(dsCpo.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
        ZYPEZDItemValueSetter.setValue(dsCpo.billToCustAcctCd, schdAgmt.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.billToCustCd, schdAgmt.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.shipToCustAcctCd, schdAgmt.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.sellToCustCd, schdAgmt.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.soldToCustLocCd, schdAgmt.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.adminPsnCd, schdAgmt.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addRddDt, schdAgmtPln.get(0).rddDt);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShpgSvcLvlCd, schdAgmt.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addFrtChrgToCd, schdAgmt.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addFrtChrgMethCd, schdAgmt.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addDropShipFlg, schdAgmt.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToCustCd, schdAgmt.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToLocNm, schdAgmt.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToAddlLocNm, schdAgmt.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToFirstLineAddr, schdAgmt.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToScdLineAddr, schdAgmt.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToThirdLineAddr, schdAgmt.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToFrthLineAddr, schdAgmt.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToCtyAddr, schdAgmt.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToStCd, schdAgmt.shipToStCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToProvNm, schdAgmt.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToPostCd, schdAgmt.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToCtryCd, schdAgmt.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipToCntyNm, schdAgmt.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipTo01RefCmntTxt, schdAgmt.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsCpo.addShipTo02RefCmntTxt, schdAgmt.shipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsCpo.addPmtTermCashDiscCd, schdAgmt.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.carrAcctNum, schdAgmt.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(dsCpo.ordSgnDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(dsCpo.slsRepCd, schdAgmt.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.prcBaseDt, schdAgmt.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(dsCpo.prcCalcDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(dsCpo.prcCatgCd, schdAgmt.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.flPrcListCd, schdAgmt.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.frtCondCd, schdAgmt.frtCondCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.carrSvcLvlCd, schdAgmt.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.spclHdlgTpCd, schdAgmt.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(dsCpo.xxValUpdFlg, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(dsCpo.ordSrcRefNum, schdAgmt.schdAgmtNum.getValue().toString());
        ZYPEZDItemValueSetter.setValue(dsCpo.ordSrcImptDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(dsCpo.sellToFirstRefCmntTxt, schdAgmt.sellToFirstRefCmntTxt);// S21_NA ADD QC#20246
    }

    /**
     * Create Param DsCpoAPI Config
     * @param dsCpo NWZC150001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     */
    private void createParamDsCpoAPIConfig(NWZC150001PMsg dsCpo, SCHD_AGMTTMsg schdAgmt) {

        NWZC150001_cpoConfigPMsg cpoConfig = dsCpo.cpoConfig.no(0);
        ZYPEZDItemValueSetter.setValue(cpoConfig.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
        ZYPEZDItemValueSetter.setValue(cpoConfig.dsOrdPosnNum, "1");
        ZYPEZDItemValueSetter.setValue(cpoConfig.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(cpoConfig.configTpCd, CONFIG_TP.NEW);
        //ZYPEZDItemValueSetter.setValue(cpoConfig.svcConfigMstrPk, schdAgmt.svcConfigMstrPk); //QC#7668 Del
        ZYPEZDItemValueSetter.setValue(cpoConfig.svcConfigMstrPk, schdAgmt.svcConfigMstrPk); // 2017/12/11 S21_NA#22629 ADD
        ZYPEZDItemValueSetter.setValue(cpoConfig.billToCustAcctCd, schdAgmt.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfig.billToCustCd, schdAgmt.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToCustAcctCd, schdAgmt.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToCustCd, schdAgmt.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(cpoConfig.dropShipFlg, schdAgmt.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToLocNm, schdAgmt.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToAddlLocNm, schdAgmt.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToFirstLineAddr, schdAgmt.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToScdLineAddr, schdAgmt.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToThirdLineAddr, schdAgmt.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToFrthLineAddr, schdAgmt.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipTo01RefCmntTxt, schdAgmt.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipTo02RefCmntTxt, schdAgmt.shipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToCtyAddr, schdAgmt.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToStCd, schdAgmt.shipToStCd);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToProvNm, schdAgmt.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToCntyNm, schdAgmt.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToPostCd, schdAgmt.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(cpoConfig.shipToCtryCd, schdAgmt.shipToCtryCd);
        dsCpo.cpoConfig.setValidCount(1);
    }


    /**
     * Create Param DsCpoAPI Detail
     * @param dsCpo NWZC150001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtLine SCHD_AGMT_LINETMsg
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @param lineNum String
     * @param invtyLocCd String
     * @param unitNetWt BigDecimal
     * @param dsOrdlineProcDfn DS_ORD_LINE_PROC_DFNTMsg
     * @param defWHParam NWZC180001PMsg
     */
    private void createParamDsCpoAPIDetail(
            NWZC150001PMsg dsCpo, SCHD_AGMTTMsg schdAgmt, SCHD_AGMT_LINETMsg schdAgmtLine, SCHD_AGMT_PLNTMsg schdAgmtPln
            , String lineNum, String invtyLocCd, BigDecimal unitNetWt
            , DS_ORD_LINE_PROC_DFNTMsg dsOrdlineProcDfn, NWZC180001PMsg defWHParam) {

        NWZC150001_APMsg dtl = dsCpo.A.no(dsCpo.A.getValidCount());
        ZYPEZDItemValueSetter.setValue(dtl.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_NEW);
        ZYPEZDItemValueSetter.setValue(dtl.cpoDtlLineNum_A1, lineNum);
        ZYPEZDItemValueSetter.setValue(dtl.cpoDtlLineSubNum_A1, LINE_SUB_NUM_DEF);
        ZYPEZDItemValueSetter.setValue(dtl.dropShipFlg_A1, schdAgmt.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dtl.shipToCustCd_A1, schdAgmt.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(dtl.shipToLocNm_A1, schdAgmt.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(dtl.shipToAddlLocNm_A1, schdAgmt.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(dtl.shipToFirstLineAddr_A1, schdAgmt.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(dtl.shipToScdLineAddr_A1, schdAgmt.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtl.shipToThirdLineAddr_A1, schdAgmt.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtl.shipToFrthLineAddr_A1, schdAgmt.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(dtl.shipToCtyAddr_A1, schdAgmt.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(dtl.shipToStCd_A1, schdAgmt.shipToStCd);
        ZYPEZDItemValueSetter.setValue(dtl.shipToProvNm_A1, schdAgmt.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(dtl.shipToPostCd_A1, schdAgmt.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(dtl.shipToCtryCd_A1, schdAgmt.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(dtl.shipToCntyNm_A1, schdAgmt.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(dtl.shipToFirstRefCmntTxt_A1, schdAgmt.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dtl.shipToScdRefCmntTxt_A1, schdAgmt.shipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dtl.ordQty_A1, schdAgmtPln.ordQty);
        ZYPEZDItemValueSetter.setValue(dtl.invtyLocCd_A1, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(dtl.ccyCd_A1, schdAgmtLine.ccyCd);
        ZYPEZDItemValueSetter.setValue(dtl.rddDt_A1, schdAgmtPln.rddDt);
        if (ZYPCommonFunc.hasValue(schdAgmtLine.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(dtl.stkStsCd_A1, schdAgmtLine.stkStsCd);
        } else {
            ZYPEZDItemValueSetter.setValue(dtl.stkStsCd_A1, STK_STS.GOOD);
        }
        ZYPEZDItemValueSetter.setValue(dtl.pmtTermCashDiscCd_A1, schdAgmt.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(dtl.frtChrgToCd_A1, schdAgmt.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(dtl.frtChrgMethCd_A1, schdAgmt.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(dtl.shpgSvcLvlCd_A1, schdAgmt.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dtl.cashDiscTermCd_A1, schdAgmt.cashDiscTermCd);
        ZYPEZDItemValueSetter.setValue(dtl.slsRepOrSlsTeamTocCd_A1, schdAgmt.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(dtl.custUomCd_A1, schdAgmtLine.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(dtl.manPrcFlg_A1, schdAgmtLine.manPrcFlg);
        ZYPEZDItemValueSetter.setValue(dtl.carrCd_A1, schdAgmt.carrCd);
        ZYPEZDItemValueSetter.setValue(dtl.carrAcctNum_A1, schdAgmt.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(dtl.setItemShipCpltFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtl.custIstlFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtl.dsOrdPosnNum_A1, DS_ORD_POSN_NUM_DEF);
        ZYPEZDItemValueSetter.setValue(dtl.unitNetWt_A1, unitNetWt);
        ZYPEZDItemValueSetter.setValue(dtl.frtCondCd_A1, schdAgmt.frtCondCd);
        ZYPEZDItemValueSetter.setValue(dtl.dsContrNum_A1, schdAgmt.dsContrNum);
        ZYPEZDItemValueSetter.setValue(dtl.dsContrSqNum_A1, schdAgmt.dsContrSqNum);
        ZYPEZDItemValueSetter.setValue(dtl.dsCpoLineNum_A1, new BigDecimal(lineNum));
        ZYPEZDItemValueSetter.setValue(dtl.dsOrdLineCatgCd_A1, dsOrdlineProcDfn.dsOrdLineCatgCd);
        ZYPEZDItemValueSetter.setValue(dtl.ordLineSrcCd_A1, defWHParam.ordLineSrcCd);
        ZYPEZDItemValueSetter.setValue(dtl.rtlWhCd_A1, defWHParam.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(dtl.rtlSwhCd_A1, defWHParam.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(dtl.flPrcListCd_A1, schdAgmt.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(dtl.dealPrcListPrcAmt_A1, schdAgmtLine.dealPrcListPrcAmt);
        ZYPEZDItemValueSetter.setValue(dtl.funcPrcListPrcAmt_A1, schdAgmtLine.funcPrcListPrcAmt);
        ZYPEZDItemValueSetter.setValue(dtl.prcBaseDt_A1, schdAgmt.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(dtl.carrSvcLvlCd_A1, schdAgmt.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dtl.supdLockFlg_A1, schdAgmtLine.supdLockFlg);
        ZYPEZDItemValueSetter.setValue(dtl.prcCatgCd_A1, schdAgmt.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(dtl.baseCmptFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtl.svcConfigMstrPk_A1, schdAgmt.svcConfigMstrPk); //QC#7362
        ZYPEZDItemValueSetter.setValue(dtl.svcMachMstrPk_A1, getSvcMachMstrPk(schdAgmt.svcConfigMstrPk.getValue())); // Add 2016/11/10 QC#15895

        dsCpo.A.setValidCount(dsCpo.A.getValidCount() + 1);

    }

    // ADD START 2016/11/10 QC#15895
    private BigDecimal getSvcMachMstrPk(BigDecimal configMstrPk) {

        SVC_CONFIG_MSTRTMsg mstrTmsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(mstrTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mstrTmsg.svcConfigMstrPk, configMstrPk);
        mstrTmsg = (SVC_CONFIG_MSTRTMsg) S21CacheTBLAccessor.findByKey(mstrTmsg);

        if (mstrTmsg == null || !S21CacheTBLAccessor.RTNCD_NORMAL.equals(mstrTmsg.getReturnCode())) {
            return null;
        }
        return mstrTmsg.svcMachMstrPk.getValue();
    }
    // ADD END   2016/11/10 QC#15895
    
    /**
     * UpdateParam DsCpoAPI  DetailMdse 
     * @param dsCpo NWZC150001PMsg
     * @param mdseCd String
     * @param origMdseCd String
     * @param sbstMdseCd String
     */
    private void updateParamDsCpoAPIDetailMdse(NWZC150001PMsg dsCpo, String mdseCd, String origMdseCd, String sbstMdseCd) {

        NWZC150001_APMsg dtl = dsCpo.A.no(dsCpo.A.getValidCount() - 1);
        ZYPEZDItemValueSetter.setValue(dtl.mdseCd_A1, mdseCd);
        ZYPEZDItemValueSetter.setValue(dtl.origMdseCd_A1, origMdseCd);
        ZYPEZDItemValueSetter.setValue(dtl.sbstMdseCd_A1, sbstMdseCd);
    }

    /**
     * Update Param DsCpoAPI Detail Pricing
     * @param dsCpo NWZC150001PMsg
     * @param pricing NWZC157001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     */
    // 2023/12/06 QC#61281 K.Ikeda START
    // private void updateParamDsCpoAPIDetailPricing(NWZC150001PMsg dsCpo, NWZC157001PMsg pricing) {
    private void updateParamDsCpoAPIDetailPricing(NWZC150001PMsg dsCpo, NWZC157001PMsg pricing, SCHD_AGMTTMsg schdAgmt) {
        BigDecimal totAmt = BigDecimal.ZERO;
        // 2023/12/06 QC#61281 K.Ikeda END
        
        for (int i = 0; i < dsCpo.A.getValidCount(); i++) {
            NWZC150001_APMsg dsCpoDtl = dsCpo.A.no(i);
            NWZC157004PMsg pricingDtl = null;
            for (int k = 0; k < pricing.NWZC157004PMsg.getValidCount(); k++) {
                pricingDtl = pricing.NWZC157004PMsg.no(k);
                if (dsCpoDtl.cpoDtlLineNum_A1.getValue().equals(pricingDtl.trxLineNum.getValue())) {
                    break;
                }
            }
            if (pricingDtl == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotBaseAmt_A1, pricingDtl.xxTotBaseAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotDiscAmt_A1, pricingDtl.xxTotDiscAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotSpclPrcAmt_A1, pricingDtl.xxTotSpclPrcAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotNetDiscAmt_A1, pricingDtl.xxTotNetDiscAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotNetPrcAmt_A1, pricingDtl.xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotFrtAmt_A1, pricingDtl.xxTotFrtAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotSpclChrgAmt_A1, pricingDtl.xxTotSpclChrgAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotTaxAmt_A1, pricingDtl.xxTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxSlsAmt_A1, pricingDtl.xxSlsAmt);
            ZYPEZDItemValueSetter.setValue(dsCpoDtl.xxTotAmt_A1, pricingDtl.xxTotAmt);
            
            // 2023/12/06 QC#61281 K.Ikeda START   
            if(ZYPCommonFunc.hasValue(pricingDtl.xxTotAmt)) {
                totAmt = totAmt.add( pricingDtl.xxTotAmt.getValue());
            }
            // 2023/12/06 QC#61281 K.Ikeda END
        }
        // 2023/12/06 QC#61281 K.Ikeda START
        if (totAmt.compareTo(BigDecimal.ZERO) == 0 && PMT_TERM_CASH_DISC.CREDIT_CARD.equals(schdAgmt.pmtTermCashDiscCd.getValue())) {
            dsCpo.dsPmtMethCd.setValue(DS_PMT_METH.NO_CREDIT_CARD);
        }
        // 2023/12/06 QC#61281 K.Ikeda END
    }

    /**
     * Update Param DsCpoAPI Detail CcyCd
     * @param dsCpo NWZC150001PMsg
     * @param pricing NWZC157001PMsg
     */
    private void updateParamDsCpoAPIDetailCcyCd(NWZC150001PMsg dsCpo, NWZC157001PMsg pricing) {
        for (int i = 0; i < dsCpo.A.getValidCount(); i++) {
            NWZC150001_APMsg dsCpoDtl = dsCpo.A.no(i);
            if (!ZYPCommonFunc.hasValue(dsCpoDtl.ccyCd_A1)) {
                NWZC157002PMsg pricingDtl = null;
                String ccyCd = null;
                for (int k = 0; k < pricing.NWZC157002PMsg.getValidCount(); k++) {
                    pricingDtl = pricing.NWZC157002PMsg.no(k);
                    if (dsCpoDtl.cpoDtlLineNum_A1.getValue().equals(pricingDtl.trxLineNum.getValue())) {
                        ccyCd = pricingDtl.ccyCd_PC.getValue();
                        break;
                    }
                }
                if (ZYPCommonFunc.hasValue(ccyCd)) {
                    ZYPEZDItemValueSetter.setValue(dsCpoDtl.ccyCd_A1, ccyCd);
                }
            }
        }
    }

    // Add Start 2019/05/29 QC#50405
    private void updateParamDsCpoAPISalesRep(NWZC150001PMsg dsCpo) {

        NWXC150001SalesRep.updateToLatestSalesRep(dsCpo);
    }
    // Add End 2019/05/29 QC#50405

    /**
     * Create Param DsCpoAPI PriceList
     * @param dsCpo NWZC150001PMsg
     * @param pricing NWZC157001PMsg
     */
    private void createParamDsCpoAPIPriceList(NWZC150001PMsg dsCpo, NWZC157001PMsg pricing) {

        for (int i = 0; i < pricing.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg pricingDtl = pricing.NWZC157002PMsg.no(i);

            for (int k = 0; k < pricingDtl.NWZC157003PMsg.getValidCount(); k++) {
                NWZC157003PMsg calcBase = pricingDtl.NWZC157003PMsg.no(k);

                NWZC150001_priceListPMsg priceList = dsCpo.priceList.no(dsCpo.priceList.getValidCount());
                ZYPEZDItemValueSetter.setValue(priceList.cpoDtlLineNum, pricingDtl.trxLineNum);
                ZYPEZDItemValueSetter.setValue(priceList.cpoDtlLineSubNum, LINE_SUB_NUM_DEF);
                ZYPEZDItemValueSetter.setValue(priceList.prcCondTpCd, calcBase.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(priceList.prcCondTpDescTxt, calcBase.prcCondTpDescTxt);
                ZYPEZDItemValueSetter.setValue(priceList.prcDtlGrpCd, calcBase.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(priceList.prcJrnlGrpCd, calcBase.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(priceList.prcCondManEntryFlg, calcBase.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(priceList.prcCondManAddFlg, calcBase.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(priceList.prcCondManDelFlg, calcBase.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(priceList.pkgUomCd, calcBase.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(priceList.prcCondUnitCd, calcBase.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(priceList.prcCalcMethCd, calcBase.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(priceList.autoPrcAmtRate, calcBase.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(priceList.maxPrcAmtRate, calcBase.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(priceList.minPrcAmtRate, calcBase.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(priceList.manPrcAmtRate, calcBase.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(priceList.calcPrcAmtRate, calcBase.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(priceList.unitPrcAmt, calcBase.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(priceList.dsMdsePrcPk, calcBase.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(priceList.specCondPrcPk, calcBase.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(priceList.frtPerWtPk, calcBase.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(priceList.autoPrcCcyCd, calcBase.autoPrcCcyCd);
                // 2018/09/14 QC#9700 add start
                ZYPEZDItemValueSetter.setValue(priceList.prcRuleApplyFlg, calcBase.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(priceList.prcRulePrcdPk, calcBase.prcRulePrcdPk);
                // 2018/09/14 QC#9700 add end

                dsCpo.priceList.setValidCount(dsCpo.priceList.getValidCount() + 1);
            }
        }
    }

    /**
     * Create Param DsCpoAPI SlsCredit
     * @param dsCpo NWZC150001PMsg
     * @param schdAgmtSlsCrList SCHD_AGMT_SLS_CRTMsgArray
     */
    private void createParamDsCpoAPISlsCredit(NWZC150001PMsg dsCpo, SCHD_AGMT_SLS_CRTMsgArray schdAgmtSlsCrList) {

        for (int i = 0; i < schdAgmtSlsCrList.getValidCount(); i++) {
            // Order Position Number is null(For Header)
            SCHD_AGMT_SLS_CRTMsg schdAgmtSlsCr = schdAgmtSlsCrList.no(i);

            NWZC150001_cpoSlsCrPMsg cpoSlsCrNull = dsCpo.cpoSlsCr.no(dsCpo.cpoSlsCr.getValidCount());
            ZYPEZDItemValueSetter.setValue(cpoSlsCrNull.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrNull.slsRepCd, schdAgmtSlsCr.slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrNull.slsRepRoleTpCd, schdAgmtSlsCr.slsRepRoleTpCd);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrNull.slsRepCrPct, schdAgmtSlsCr.slsRepCrPct);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrNull.slsCrQuotFlg, schdAgmtSlsCr.slsCrQuotFlg);
            dsCpo.cpoSlsCr.setValidCount(dsCpo.cpoSlsCr.getValidCount() + 1);

            // Order Position Number is '1'(For Config)
            NWZC150001_cpoSlsCrPMsg cpoSlsCr1 = dsCpo.cpoSlsCr.no(dsCpo.cpoSlsCr.getValidCount());
            EZDMsg.copy(cpoSlsCrNull, null, cpoSlsCr1, null);
            ZYPEZDItemValueSetter.setValue(cpoSlsCr1.dsOrdPosnNum, DS_ORD_POSN_NUM_DEF);
            ZYPEZDItemValueSetter.setValue(cpoSlsCr1.configCatgCd, CONFIG_CATG.OUTBOUND);
            dsCpo.cpoSlsCr.setValidCount(dsCpo.cpoSlsCr.getValidCount() + 1);
        }
    }

    /**
     * Create Param DsCpoAPI DelyInfo
     * @param dsCpo NWZC150001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     */
    private void createParamDsCpoAPIDelyInfo(NWZC150001PMsg dsCpo, SCHD_AGMTTMsg schdAgmt) {

        if (ZYPCommonFunc.hasValue(schdAgmt.shpgCmntTxt)) {
            // 2016/12/21 S21_NA#16617 Mod Start
//            List<String> shpgCmtList = splitByLength(schdAgmt.shpgCmntTxt.getValue(), DELY_ADDL_CMNT_TXT_LEN);
//            for (String shpgCmt : shpgCmtList) {
//                NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfo = dsCpo.cpoDlvyInfoList.no(dsCpo.cpoDlvyInfoList.getValidCount());
//                ZYPEZDItemValueSetter.setValue(cpoDlvyInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
//                ZYPEZDItemValueSetter.setValue(cpoDlvyInfo.delyAddlCmntTxt, shpgCmt);
//                dsCpo.cpoDlvyInfoList.setValidCount(dsCpo.cpoDlvyInfoList.getValidCount() + 1);
//            }

            NWZC150001_cpoDlvyInfoListPMsg cpoDlvyInfo = dsCpo.cpoDlvyInfoList.no(dsCpo.cpoDlvyInfoList.getValidCount());
            ZYPEZDItemValueSetter.setValue(cpoDlvyInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
            String shpgCmntTxt = schdAgmt.shpgCmntTxt.getValue();
            if (shpgCmntTxt.length() > DELY_ADDL_CMNT_TXT_LEN) {
                shpgCmntTxt = shpgCmntTxt.substring(0, DELY_ADDL_CMNT_TXT_LEN);
            }
            ZYPEZDItemValueSetter.setValue(cpoDlvyInfo.delyAddlCmntTxt, shpgCmntTxt);
            dsCpo.cpoDlvyInfoList.setValidCount(dsCpo.cpoDlvyInfoList.getValidCount() + 1);
            // 2016/12/21 S21_NA#16617 Mod End
        }
    }

    /**
     * Create Param DsCpoAPI CtacPsn
     * @param dsCpo NWZC150001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtCtacPsnList SCHD_AGMT_CTAC_PSNTMsgArray
     */
    private void createParamDsCpoAPICtacPsn(NWZC150001PMsg dsCpo, SCHD_AGMTTMsg schdAgmt, SCHD_AGMT_CTAC_PSNTMsgArray schdAgmtCtacPsnList) {
        if (schdAgmtCtacPsnList == null || schdAgmtCtacPsnList.getValidCount() == 0) {
            return;
        }
        for (int i = 0; i < schdAgmtCtacPsnList.getValidCount(); i++) {
            SCHD_AGMT_CTAC_PSNTMsg schdAgmtCtacPsn = schdAgmtCtacPsnList.no(i);

            NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnInfo = dsCpo.cpoCtacPsnInfoList.no(dsCpo.cpoCtacPsnInfoList.getValidCount());
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacTpCd, schdAgmtCtacPsn.ctacPsnTpCd);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.firstNm, schdAgmtCtacPsn.ctacPsnFirstNm);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.lastNm, schdAgmtCtacPsn.ctacPsnLastNm);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.emlAddr, schdAgmtCtacPsn.ctacPsnEmlAddr);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.telNum, schdAgmtCtacPsn.ctacPsnTelNum);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.faxNum, schdAgmtCtacPsn.ctacPsnFaxNum);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnExtnNum, schdAgmtCtacPsn.ctacPsnExtnNum);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacPsnPk, schdAgmtCtacPsn.ctacPsnPk); // S21_NA ADD QC#20246(Sol#454) 
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnInfo.ctacCustRefTpCd, schdAgmtCtacPsn.ctacCustRefTpCd); // S21_NA#16452 Add
            dsCpo.cpoCtacPsnInfoList.setValidCount(dsCpo.cpoCtacPsnInfoList.getValidCount() + 1);
        }
    }
    /**
     * call API DsCpoUpd
     * @param dsCpo NWZC150001PMsg
     * @param dsCpoDtlList1 List<NWZC150002PMsg>
     * @param dsCpoDtlList2 List<NWZC150003PMsg>
     * @return String
     */
    // Mod Start 2019/04/10 QC#31086
    // private String callAPIDsCpoUpd(NWZC150001PMsg dsCpo, List<NWZC150002PMsg> dsCpoDtlList1, List<NWZC150003PMsg> dsCpoDtlList2) {
    private boolean callAPIDsCpoUpd(NWZC150001PMsg dsCpo, List<NWZC150002PMsg> dsCpoDtlList1, List<NWZC150003PMsg> dsCpoDtlList2
            , List<SendMailInfoBean> sendMailInfoList, Map<String, BigDecimal> lineNumMap, SCHD_AGMTTMsg schdAgmt ) {
        
    // Mod End2019/04/10 QC#31086
        // 2023/12/06 QC#61281 K.Ikeda START
        // QC#61281 Add Start
        // if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(dsCpo.addPmtTermCashDiscCd.getValue()) && !ZYPCommonFunc.hasValue(dsCpo.dsPmtMethCd)) {
        //     sendMailInfoList.add(setSendMailInfo(schdAgmt, lineNumMap.get("001"), "NWZM2321E", null));
        //     return false;
        // }
        // QC#61281 Add End
        // 2023/12/06 QC#61281 K.Ikeda END
        
        // Call DS CPO Update API [NWZC150001]
        NWZC150001 dsCpoUpd = new NWZC150001();
        dsCpoUpd.execute(dsCpo, dsCpoDtlList1, dsCpoDtlList2, ONBATCH_TYPE.BATCH);

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(dsCpo);
        for (S21ApiMessage msg : msgList) {
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray(); // Add 2019/04/10 QC#31086
            if (ZYPCommonFunc.hasValue(msgId)) {
                // Mod Start 2019/04/10 QC#31086
                // return msgId;
                sendMailInfoList.add(setSendMailInfo(schdAgmt, lineNumMap.get("001"), msgId, msgPrms));
                if (msgId.endsWith("E")) {
                    return false;
                }
                // Mod End 2019/04/10 QC#31086
            }
        }

        for (NWZC150002PMsg dsCpoDtl : dsCpoDtlList1) {
            msgList = S21ApiUtil.getXxMsgList(dsCpoDtl);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray(); // Add 2019/04/10 QC#31086
                if (ZYPCommonFunc.hasValue(msgId)) {
                    // Mod Start 2019/04/10 QC#31086
                    // return msgId;
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, lineNumMap.get(dsCpoDtl.cpoDtlLineNum.getValue()),msgId, msgPrms));
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                    // Mod End 2019/04/10 QC#31086
                }
            }
        }
        for (NWZC150003PMsg dsCpoDtl : dsCpoDtlList2) {
            msgList = S21ApiUtil.getXxMsgList(dsCpoDtl);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray(); // Add 2019/04/10 QC#31086
                if (ZYPCommonFunc.hasValue(msgId)) {
                    // Mod Start 2019/04/10 QC#31086
                    // return msgId;
                    sendMailInfoList.add(setSendMailInfo(schdAgmt, lineNumMap.get(dsCpoDtl.cpoDtlLineNum.getValue()),msgId, msgPrms));
                    if (msgId.endsWith("E")) {
                        return false;
                    }
                    // Mod End 2019/04/10 QC#31086
                }
            }
        }
        // Mod Start 2019/04/10 QC#31086
        return true;
        //return null;
        // Mod End 2019/04/10 QC#31086
    }

    /**
     * Insert Invoice Commnet
     * @param dsCpo NWZC150001PMsg
     * @param schdAgmt SCHD_AGMTTMsg
     * @return boolean
     */
    private boolean insertInvoiceCommnet(NWZC150001PMsg dsCpo, SCHD_AGMTTMsg schdAgmt) {

        if (ZYPCommonFunc.hasValue(schdAgmt.invCmntTxt)) {
            List<String> invCmtList = splitByLength(schdAgmt.invCmntTxt.getValue(), MSG_TXT_INFO_TXT_LEN);
            int sq = 1;
            for (String invCmt : invCmtList) {
                MSG_TXT_DTLTMsg msgTxt = new MSG_TXT_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(msgTxt.glblCmpyCd, this.glblCmpyCd);
                BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MSG_TXT_DTL_SQ);
                ZYPEZDItemValueSetter.setValue(msgTxt.msgTxtDtlSq, pk);
                ZYPEZDItemValueSetter.setValue(msgTxt.cpoOrdNum, dsCpo.cpoOrdNum.getValue());
                ZYPEZDItemValueSetter.setValue(msgTxt.txtTpCd, TXT_TP.INVOICE_COMMENT);
                ZYPEZDItemValueSetter.setValue(msgTxt.txtSqNum, Integer.toString(sq));
                ZYPEZDItemValueSetter.setValue(msgTxt.msgTxtInfoTxt, invCmt);
                S21FastTBLAccessor.insert(msgTxt);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(msgTxt.getReturnCode())) {
                    return false;
                }
                sq++;
            }
        }
        return true;
    }

    /**
     * Update SchdAgmtPln
     * @param dsCpo NWZC150001PMsg
     * @param dsCpoDtlList1 List<NWZC150002PMsg>
     * @param schdAgmtPln List<SCHD_AGMT_PLNTMsg>
     * @return boolean
     */
    // Mod Start 2019/04/10 QC#31086
    // private boolean updateSchdAgmtPln(NWZC150001PMsg dsCpo, List<NWZC150002PMsg> dsCpoDtlList1, List<SCHD_AGMT_PLNTMsg> schdAgmtPln) {
    private boolean updateSchdAgmtPln(NWZC150001PMsg dsCpo, List<NWZC150002PMsg> dsCpoDtlList1, List<SCHD_AGMT_PLNTMsg> schdAgmtPln
            ,List<SendMailInfoBean> sendMailInfoList, SCHD_AGMTTMsg schdAgmt) {
    // Mod Start 2019/04/10 QC#31086
        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("HHmmssSSS");
        String cpoOrdNum = dsCpo.cpoOrdNum.getValue();
        int i = 0;
        for (SCHD_AGMT_PLNTMsg schdAgmtPlnIn : schdAgmtPln) {
            NWZC150002PMsg dsCpoDtl = dsCpoDtlList1.get(i);
            ZYPEZDItemValueSetter.setValue(schdAgmtPlnIn.cpoOrdNum, cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtPlnIn.cpoDtlLineNum, dsCpoDtl.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtPlnIn.cpoDtlLineSubNum, dsCpoDtl.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtPlnIn.dsOrdPosnNum, DS_ORD_POSN_NUM_DEF);
            ZYPEZDItemValueSetter.setValue(schdAgmtPlnIn.ordCratTs, this.salesDate + currentSystemTime);
            S21FastTBLAccessor.update(schdAgmtPlnIn);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(schdAgmtPlnIn.getReturnCode())) {
                sendMailInfoList.add(setSendMailInfo(schdAgmt, schdAgmtPlnIn, "NWAM0741E", new String[]{"Scheduling Agreement Plan", "SCHD_AGMT_PLN"})); //Add 2019/04/10 QC#31086
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * update SchdAgmt Status ForExpired
     */
    private void updateSchdAgmtStatusForExpired() {

        SCHD_AGMTTMsg schdAmgt = new SCHD_AGMTTMsg();
        // Mod Start 2016/09/09 S21_NA#13853
        schdAmgt.setSQLID("002");
        schdAmgt.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        schdAmgt.setConditionValue("schdAgmtVldThruDt01", this.salesDate);
        schdAmgt.setConditionValue("schdAgmtStsCd01", SCHD_AGMT_STS.ACTIVE);
//        schdAmgt.setSQLID("001");
//        schdAmgt.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        schdAmgt.setConditionValue("schdAgmtVldThruDt01", this.salesDate);
//        schdAmgt.setConditionValue("schdAgmtStsCd01", SCHD_AGMT_STS.ENDED);
        // Mod End 2016/09/09 S21_NA#13853
        SCHD_AGMTTMsgArray schdAmgtList = (SCHD_AGMTTMsgArray) EZDTBLAccessor.findByConditionForUpdate(schdAmgt);
        if (schdAmgtList != null && 0 < schdAmgtList.getValidCount()) {
            for (int i = 0; i < schdAmgtList.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(schdAmgtList.no(i).schdAgmtStsCd, SCHD_AGMT_STS.ENDED);
                S21FastTBLAccessor.update(schdAmgtList.no(i));
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(schdAmgtList.no(i).getReturnCode())) {
                    rollback();
                    this.errRecCnt = this.errRecCnt + 1;
                } else {
                    commit();
                    this.normalRecCnt = this.normalRecCnt + 1;
                }
            }
        }
    }

    /**
     * splitByLength
     * @param s String
     * @param length int
     * @return List<String>
     */
    public static List<String> splitByLength(String s, int length) {
        List<String> list = new ArrayList<String>();
        while (s != null && s.length() != 0) {
            int endIndex = Math.min(length, s.length());
            list.add(S21StringUtil.subStringByLength(s, 0, endIndex));
            //            s = s.substring(endIndex);
            s = S21StringUtil.subStringByLength(s, endIndex, s.length());
        }
        return list;
    }

    /**
     * Get Next CpoDtlLineNum
     * @param lineNum String
     * @return String
     */
    private String getNextCpoDtlLineNum(String lineNum) {

        char[] digit1 = S21StringUtil.subStringByLength(lineNum, 0, 1).toCharArray();
        int digit23 = Integer.parseInt(S21StringUtil.subStringByLength(lineNum, 1, 2));

        // increment line number
        digit23++;
        digit23 = digit23 % 100;
        if (digit23 == 0) {
            if (digit1[0] == 0x0039) {
                digit1[0] = 0x0041;
            } else {
                digit1[0]++;
            }
        }
        return String.valueOf(digit1) + ZYPCommonFunc.leftPad(String.valueOf(digit23), 2, "0");
    }

    /**
     * Set SendMailInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtPln SCHD_AGMT_PLNTMsg
     * @param xxMsgId String
     * @param xxMsgPrmArray String[]
     * @return SendMailInfoBean
     */
    private SendMailInfoBean setSendMailInfo(SCHD_AGMTTMsg schdAgmt, SCHD_AGMT_PLNTMsg schdAgmtPln, String xxMsgId, String[] xxMsgPrmArray) {
        String adminPsn = schdAgmt.adminPsnCd.getValue();
        if (!ZYPCommonFunc.hasValue(schdAgmt.adminPsnCd.getValue())) {
            adminPsn = schdAgmt.slsRepTocCd.getValue();
        }
        return setSendMailInfo(schdAgmt.schdAgmtNum.getValue(), schdAgmtPln.schdAgmtLineNum.getValue(), adminPsn, xxMsgId, xxMsgPrmArray);
    }

    /**
     * Set SendMailInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @param xxMsgId String
     * @param xxMsgPrmArray String[]
     * @return SendMailInfoBean
     */
    private SendMailInfoBean setSendMailInfo(SCHD_AGMTTMsg schdAgmt, String xxMsgId, String[] xxMsgPrmArray) {
        String adminPsn = schdAgmt.adminPsnCd.getValue();
        if (!ZYPCommonFunc.hasValue(schdAgmt.adminPsnCd.getValue())) {
            adminPsn = schdAgmt.slsRepTocCd.getValue();
        }
        return setSendMailInfo(schdAgmt.schdAgmtNum.getValue(), null, adminPsn, xxMsgId, xxMsgPrmArray);
    }

    /**
     * Set SendMailInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @param xxMsgId String
     * @return SendMailInfoBean
     */
    private SendMailInfoBean setSendMailInfo(SCHD_AGMTTMsg schdAgmt, String xxMsgId) {
        String adminPsn = schdAgmt.adminPsnCd.getValue();
        if (!ZYPCommonFunc.hasValue(schdAgmt.adminPsnCd.getValue())) {
            adminPsn = schdAgmt.slsRepTocCd.getValue();
        }
        return setSendMailInfo(schdAgmt.schdAgmtNum.getValue(), null, adminPsn, xxMsgId, null);
    }

    /**
     * Set SendMailInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtLine SCHD_AGMT_LINETMsg
     * @param xxMsgId String
     * @param xxMsgPrmArray String[]
     * @return SendMailInfoBean
     */
    private SendMailInfoBean setSendMailInfo(SCHD_AGMTTMsg schdAgmt, SCHD_AGMT_LINETMsg schdAgmtLine, String xxMsgId, String[] xxMsgPrmArray) {
        String adminPsn = schdAgmt.adminPsnCd.getValue();
        if (!ZYPCommonFunc.hasValue(schdAgmt.adminPsnCd.getValue())) {
            adminPsn = schdAgmt.slsRepTocCd.getValue();
        }
        return setSendMailInfo(schdAgmt.schdAgmtNum.getValue(), schdAgmtLine.schdAgmtLineNum.getValue(), adminPsn, xxMsgId, xxMsgPrmArray);
    }

    /**
     * Set SendMailInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtLine SCHD_AGMT_LINETMsg
     * @param xxMsgId String
     * @return SendMailInfoBean
     */
    private SendMailInfoBean setSendMailInfo(SCHD_AGMTTMsg schdAgmt, SCHD_AGMT_LINETMsg schdAgmtLine, String xxMsgId) {
        String adminPsn = schdAgmt.adminPsnCd.getValue();
        if (!ZYPCommonFunc.hasValue(schdAgmt.adminPsnCd.getValue())) {
            adminPsn = schdAgmt.slsRepTocCd.getValue();
        }
        return setSendMailInfo(schdAgmt.schdAgmtNum.getValue(), schdAgmtLine.schdAgmtLineNum.getValue(), adminPsn, xxMsgId, null);
    }

    // Add Start 2019/04/10 QC#31086
    /**
     * Set SendMailInfo
     * @param schdAgmt SCHD_AGMTTMsg
     * @param schdAgmtLineNum BigDecimal
     * @param xxMsgId String
     * @param xxMsgPrmArray String[]
     * @return SendMailInfoBean
     */
    private SendMailInfoBean setSendMailInfo(SCHD_AGMTTMsg schdAgmt, BigDecimal schdAgmtLineNum, String xxMsgId, String[] xxMsgPrmArray) {
        String adminPsn = schdAgmt.adminPsnCd.getValue();
        if (!ZYPCommonFunc.hasValue(schdAgmt.adminPsnCd.getValue())) {
            adminPsn = schdAgmt.slsRepTocCd.getValue();
        }
        return setSendMailInfo(schdAgmt.schdAgmtNum.getValue(), schdAgmtLineNum, adminPsn, xxMsgId, xxMsgPrmArray);
    }
    // Add Start 2019/04/10 QC#31086

    /**
     * Set SendMailInfo
     * @param schdAgmtNum String
     * @param schdAgmtLineNum BigDecimal
     * @param adminPsnCd String
     * @param xxMsgId String
     * @param xxMsgPrmArray String[]
     * @return SendMailInfoBean
     */
    private SendMailInfoBean setSendMailInfo(String schdAgmtNum, BigDecimal schdAgmtLineNum, String adminPsnCd, String xxMsgId, String[] xxMsgPrmArray) {
        SendMailInfoBean sendMailInfo = new SendMailInfoBean();
        sendMailInfo.setSchdAgmtNum(schdAgmtNum);
        if (schdAgmtLineNum != null) {
            sendMailInfo.setSchdAgmtLineNum(schdAgmtLineNum);
        }
        if (adminPsnCd != null) {
            sendMailInfo.setAdminPsnCd(adminPsnCd);
        }
        sendMailInfo.setXxMsgId(xxMsgId);
        if (xxMsgPrmArray != null) {
            sendMailInfo.setXxMsgPrmArra(xxMsgPrmArray);
        }
        return sendMailInfo;
    }

    /**
     * Send Mail For Admin
     * @param sendMailInfoList List<SendMailInfoBean>
     */
    private void sendMailForAdmin(List<SendMailInfoBean> sendMailInfoList) {
        // Sort
        Collections.sort(sendMailInfoList, new SendMailInfoBeanComparator());
        // 2019/01/30 QC#30095 Del Start
        //String preAdminCd = null;
        //StringBuffer mailTxt = new StringBuffer();
        // 2019/01/30 QC#30095 Del End
        List<SendMailInfoBean> sendMailToSys = new ArrayList<SendMailInfoBean>();

        for (SendMailInfoBean sendMailInfo : sendMailInfoList) {
            // 2019/01/30 QC#30095 Del Start
            // if (!ZYPCommonFunc.hasValue(sendMailInfo.getAdminPsnCd())) {
            // 2019/01/30 QC#30095 Del End
                sendMailToSys.add(sendMailInfo);
            // 2019/01/30 QC#30095 Del Start
            //    continue;
            //}
            //if (ZYPCommonFunc.hasValue(preAdminCd) && !preAdminCd.equals(sendMailInfo.getAdminPsnCd())) {
            //    sendMail(preAdminCd, mailTxt.toString());
            //    mailTxt = new StringBuffer();
            //}
            //mailTxt.append("  ");
            //mailTxt.append(sendMailInfo.getSchdAgmtNum());
            //mailTxt.append("                  ");
            //if (ZYPCommonFunc.hasValue(sendMailInfo.getSchdAgmtLineNum())) {
            //    mailTxt.append(sendMailInfo.getSchdAgmtLineNum().toString());
            //} else {
            //    mailTxt.append(" ");
            //}
            //mailTxt.append("                           ");
            // Mod Start 2016/08/08 QC#11171
            //String errMsgTxt = S21MessageFunc.clspGetMessage(sendMailInfo.getXxMsgId(), sendMailInfo.getXxMsgPrmArray());
            //mailTxt.append(S21MessageFunc.clspGetMessage(sendMailInfo.getXxMsgId(), sendMailInfo.getXxMsgPrmArray()));
            //mailTxt.append(errMsgTxt);
            //mailTxt.append("\r\n");
            //preAdminCd = sendMailInfo.getAdminPsnCd();

            //S21InfoLogOutput.println(errMsgTxt);
            // Add End 2016/08/08 QC#11171
            // 2019/01/30 QC#30095 Del End
        }
        // 2019/01/30 QC#30095 Del Start
        //if (ZYPCommonFunc.hasValue(mailTxt.toString())) {
        //    sendMail(preAdminCd, mailTxt.toString());
        //}
        // 2019/01/30 QC#30095 Del End
        // QC#56167 2020/03/13 Add Start
        //if (0 < sendMailToSys.size()) {
        //    sendMailToSys(sendMailToSys);
        //}
        if (0 < sendMailToSys.size()) {
            sendMail(sendMailToSys);
        }
        // QC#56167 2020/03/13 Add End
        commit();
    }

    /**
     * Send Mail
     * @param useId String
     * @param mailTxt String
     * @return boolean
     */
    private boolean sendMail(String useId, String mailTxt)  {

        List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();
        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        NWXC001001MailSubstituteString sbsStr;

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("salesDate");
        sbsStr.setSbstStr(ZYPDateUtil.formatEzd8ToDisp(this.salesDate));
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchId");
        sbsStr.setSbstStr(this.getClass().getSimpleName());
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchNm");
        sbsStr.setSbstStr(PROGRAM_NM);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("errDate");
        sbsStr.setSbstStr(ZYPDateUtil.formatEzd14ToDisp(this.salesDate + currentSystemTime));
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("bodyTxt");
        sbsStr.setSbstStr(mailTxt);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("adminPsnCd");
        sbsStr.setSbstStr(useId);
        sbsStrList.add(sbsStr);


        List<String> usrIdList = new ArrayList<String>();
        usrIdList.add(useId);
        boolean isNormalEnd = new NWXC001001Mail().postMail(this.glblCmpyCd, usrIdList, MAIL_TEMP_ID, sbsStrList, this.profile);

        return isNormalEnd;

    }

    /**
     * Send Mail To System
     * @param sendMailInfoList List<SendMailInfoBean>
     */
    private void sendMailToSys(List<SendMailInfoBean> sendMailInfoList)  {

        NWXC100001SendMailForErrorInfo errMailCtrl = new NWXC100001SendMailForErrorInfo();
        List<String> msgList = new ArrayList<String>();
        for (SendMailInfoBean sendMailInfo : sendMailInfoList) {
            // Mod Start 2016/08/08 QC#11171
//            msgList.add(S21MessageFunc.clspGetMessage(sendMailInfo.getXxMsgId(), sendMailInfo.getXxMsgPrmArray()));
            String errMsgTxt = S21MessageFunc.clspGetMessage(sendMailInfo.getXxMsgId(), sendMailInfo.getXxMsgPrmArray());
            // Mod Start 2019/04/09 QC#31086
            // msgList.add(errMsgTxt);
            // errMailCtrl.addErrMsgList(msgList);

            // S21InfoLogOutput.println(errMsgTxt);
            msgList.add(errMsgTxt + ", Schd#: " + sendMailInfo.getSchdAgmtNum() + ", Line#: " + sendMailInfo.getSchdAgmtLineNum());
            S21InfoLogOutput.println(errMsgTxt + ", Schd#: " + sendMailInfo.getSchdAgmtNum() + ", Line#: " + sendMailInfo.getSchdAgmtLineNum());
            // Mod End 2019/04/09 QC#31086
            // Mod End 2016/08/08 QC#11171
        }
        errMailCtrl.addErrMsgList(msgList); // Add 2019/04/10 QC#31086
        errMailCtrl.sendMail(this.glblCmpyCd, NWAB244001.class.getSimpleName());
    }
    // QC#56167 2020/03/13 Add Start
    private boolean sendMail(List<SendMailInfoBean> sendMailInfoList)  {

        List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();
        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL);

        NWXC001001MailSubstituteString sbsStr;

        // QC#61240 2023/03/30 Del Start
        // sbsStr = new NWXC001001MailSubstituteString();
        // sbsStr.setSbstKey(MAIL_TEMPLATE_KEY_ID);
        // sbsStr.setSbstStr(BIZ_ID);
        // sbsStrList.add(sbsStr);
        // QC#61240 2023/03/30 Del End

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey(MAIL_TEMPLATE_KEY_DATE);
        sbsStr.setSbstStr(currentTime);
        sbsStrList.add(sbsStr);

        StringBuilder msgBuf = new StringBuilder();
        // QC#61240 2023/03/30 Add Start
        List<String> msgTxtList = new ArrayList<String>();
        // QC#61240 2023/03/30 Add End
        msgBuf.append(MAIL_MSG_HEADER);
        msgBuf.append(LINE_FEED_CODE);
        for (SendMailInfoBean sendMailInfo : sendMailInfoList) {
            String errMsgTxt = S21MessageFunc.clspGetMessage(sendMailInfo.getXxMsgId(), sendMailInfo.getXxMsgPrmArray());
            // QC#61240 2023/03/30 Mod Start
            // msgBuf.append(errMsgTxt + ", Schd#: " +
            // sendMailInfo.getSchdAgmtNum() + ", Line#: " +
            // sendMailInfo.getSchdAgmtLineNum());
            // msgBuf.append(LINE_FEED_CODE);
            // S21InfoLogOutput.println(errMsgTxt + ", Schd#: " +
            // sendMailInfo.getSchdAgmtNum() + ", Line#: " +
            // sendMailInfo.getSchdAgmtLineNum());
            String msgTxt = "Schd#: " + sendMailInfo.getSchdAgmtNum() + ", Line#: " + sendMailInfo.getSchdAgmtLineNum() + " :: " + errMsgTxt;
            if (!msgTxtList.contains(msgTxt)) {
                msgBuf.append(msgTxt);
                msgBuf.append(LINE_FEED_CODE);
                S21InfoLogOutput.println(msgTxt);
                msgTxtList.add(msgTxt);
            }
            // QC#61240 2023/03/30 Mod End
        }
        String message = msgBuf.toString();

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey(MAIL_TEMPLATE_KEY_MESSAGE);
        sbsStr.setSbstStr(message);
        sbsStrList.add(sbsStr);

        // QC#61240 2023/03/30 Mod Start
        // boolean isNormalEnd = new NWXC001001Mail().postMail(this.glblCmpyCd,BIZ_ID,MAIL_TEMPLATE_ID,sbsStrList);
        boolean isNormalEnd = new NWXC001001Mail().postMail(this.glblCmpyCd, BIZ_ID, MAIL_TEMP_ID, sbsStrList);
        // QC#61240 2023/03/30 Mod End

        return isNormalEnd;
    }
    // QC#56167 2020/03/13 Add End

    /**
     * SendMailInfoBeanComparator Class
     *
     */
    private static final class SendMailInfoBeanComparator implements Comparator<SendMailInfoBean>, Serializable {

        /**
         * Serial Version UID
         */
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(SendMailInfoBean t1, SendMailInfoBean t2) {
            // Compare Admin Code
            if (!ZYPCommonFunc.hasValue(t1.getAdminPsnCd())) {
                return 1;
            }
            if (t1.getAdminPsnCd().compareTo(t2.getAdminPsnCd()) > 0) {
                return 1;
            } else if (t1.getAdminPsnCd().compareTo(t2.getAdminPsnCd()) == 0) {
                // Admin Code is equal
                // Compare Scheduling Num
                if (t1.getSchdAgmtNum().compareTo(t2.getSchdAgmtNum()) > 0) {
                    return 1;
                } else  if (t1.getSchdAgmtNum().compareTo(t2.getSchdAgmtNum()) < 0) {
                    return -1;
                } else {
                    // Scheduling Num is equal
                    // Compared Scheduling Line Num
                    if (!ZYPCommonFunc.hasValue(t1.getSchdAgmtLineNum())) {
                        return 1;
                    }
                    if (t1.getSchdAgmtLineNum().compareTo(t2.getSchdAgmtLineNum()) > 0) {
                        return 1;
                    } else  if (t1.getSchdAgmtLineNum().compareTo(t2.getSchdAgmtLineNum()) < 0) {
                        return -1;
                    }
                    return 0;
                }
            }
            // Else
            return -1;
        }
    }
}
