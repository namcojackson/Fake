/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 **/
package com.canon.cusa.s21.batch.NLB.NLBB002001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AREA_LEAD_TMTMsg;
import business.db.CAL_RELNTMsg;
import business.db.CPOTMsg;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.RTE_CTRLTMsg;
import business.db.RTE_GRPTMsg;
import business.db.RTE_GRP_DTLTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHPG_WTTMsg;
import business.db.TRNSP_LTTMsg;
import business.db.TRX_SRC_TPTMsg;
import business.db.WH_LEAD_TMTMsg;
import business.parts.NWZC003001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Routing Wave
 * 
 * Grouping Shipping Plan Data and calculate SDD, PSD, PDD, Shipping Mode
 * and Shipping Service Level based on the request delivery date, then create
 * Routing Group Data which will be used when Shipping Order is created.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/30/2009   Fujitsu         K.Ozasa         Create          N/A
 * 27/10/2015   CITS            M.ITO           Update          N/A
 * 02/10/2017   CITS            K.Kameoka       Update          QC#17395
 * 03/08/2017   CITS            R.Shimamoto     Update          QC#17395-1
 * 2019/03/01   Fujitsu         S.Ohki          Update          QC#30584
 *</pre>
 */
public class NLBB002001 extends S21BatchMain implements NLBB002001Constant {

    /** Commit Count */
    private int commitCountGrp = 0;

    /** Commit Count */
    private int commitCountGrpDtl = 0;

    /** Commit Count */
    private int commitCountCtrl = 0;

    /** Total Commit Count */
    private int totalCommitCountGrp = 0;

    /** Total Commit Count */
    private int totalCommitCountGrpDtl = 0;

    /** Total Commit Count */
    private int totalCommitCountCtrl = 0;

    /** Total Error Count */
    private int totalErrorCount = 0;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Process Control Number */
    private int procCtrlNum = 0;

    /** New Specification Mode */
    private String newSpecMode = null;

    /** System Time */
    private String sysTime = null;

    /** System Date */
    private String sysDate = null;

    /** Sales Date */
    private String salesDate = null;

    /** Default Configuration lead time Day number **/
    private static BigDecimal defaultConfigLtDayNum = BigDecimal.ZERO;

    /** NUM_CONST Key : Default Configuration Lead Time Day Number */
    private static final String CONST_KEY_DEFAULT_LT = "DEFAULT_CONFIG_LT_DAY_NUM";

    /** VAR_CHAR_CONST Key : CPO WH Transfer */
    private static final String CPO_DS_ORD_TP_FOR_WH_TRNSF = "CPO_DS_ORD_TP_FOR_WH_TRNSF";

    // QC#17395 Start
    /** VAR_CHAR_CONST Key : WH_OWNR_CD */
    private static final String HAZMAT_WH_OWNR_CD = "NLBB0020_HAZMAT_WH_OWNR_CD";

    // QC#17395 End

    // QC#63614 Start
    /** VAR_CHAR_CONST Key : DS_ORD_CATG_CD */
    private static final String ORD_CATG_FOR_EXCPT_SO_DIV = "ORD_CATG_FOR_EXCPT_SO_DIV";
    // QC#63614 END

    /** Mail Message Information */
    private StringBuilder mailMsgInfo = null;

    /** Cache Map for TRX_SRC_TP */
    private S21LRUMap<String, TRX_SRC_TPTMsg> trxSrcTpMap = new S21LRUMap<String, TRX_SRC_TPTMsg>(10);

    /** Cache Map for MDSE_STORE_PKG */
    private S21LRUMap<String, MDSE_STORE_PKGTMsg> mdseStorePkgMap = new S21LRUMap<String, MDSE_STORE_PKGTMsg>(100);

    /** Cache Map for SHIP_PRTY */
    private S21LRUMap<String, List<BigDecimal>> shipPrtyMap = new S21LRUMap<String, List<BigDecimal>>(30);

    /** Cache Map for SHPG_MODE_CD */
    private S21LRUMap<String, List<String>> shpgModeCdMap = new S21LRUMap<String, List<String>>(300);

    /** Cache Map for SHPG_SVC for RDD */
    private S21LRUMap<String, List<ShpgSvcInfo>> shpgSvcForRddMap = new S21LRUMap<String, List<ShpgSvcInfo>>(30);

    /** Cache Map for SHPG_SVC for RSD */
    private S21LRUMap<String, List<ShpgSvcInfo>> shpgSvcForRsdMap = new S21LRUMap<String, List<ShpgSvcInfo>>(30);

    /** Cache Map for CAL_RELN */
    private S21LRUMap<String, CAL_RELNTMsg> calRelnMap = new S21LRUMap<String, CAL_RELNTMsg>(100);

    /** Cache Map for WH_LEAD_TM */
    private S21LRUMap<String, List<WH_LEAD_TMTMsg>> whLeadTmMap = new S21LRUMap<String, List<WH_LEAD_TMTMsg>>(50);

    /** Cache Map for SHPG_SVC_LEAD_TM */
    private S21LRUMap<String, List<ShpgSvcInfo>> shpgSvcLeadTmMap = new S21LRUMap<String, List<ShpgSvcInfo>>(10);

    /** Cache Map for TRNSP_LT */
    private S21LRUMap<String, List<TRNSP_LTTMsg>> trnspLtMap = new S21LRUMap<String, List<TRNSP_LTTMsg>>(300);

    /** Cache Map for AREA_LEAD_TM */
    private S21LRUMap<String, List<AREA_LEAD_TMTMsg>> areaLeadTmMap = new S21LRUMap<String, List<AREA_LEAD_TMTMsg>>(200);

    /** Cache Map for SHPG_WT */
    private S21LRUMap<String, List<SHPG_WTTMsg>> shpgWtMap = new S21LRUMap<String, List<SHPG_WTTMsg>>(100);

    /** Cache Map for isBusinessDay Method */
    private S21LRUMap<String, Boolean> isBusinessDayMap = new S21LRUMap<String, Boolean>(300);

    /** Cache Map for addBusinessDay Method */
    private S21LRUMap<String, String> addBusinessDayMap = new S21LRUMap<String, String>(300);

    /** Cache Map for SHIP_TO_CUST */
    private S21LRUMap<String, List<SHIP_TO_CUSTTMsg>> shipToCustMap = new S21LRUMap<String, List<SHIP_TO_CUSTTMsg>>(50);

    /** Cache Map for WH */
    private S21LRUMap<String, List<DS_INVTY_LOC_VTMsg>> dsInvtyLocVMap = new S21LRUMap<String, List<DS_INVTY_LOC_VTMsg>>(50);

    /** Business Error Message per Business Error Type */
    private Map<String, String> bizErrMsgMap;

    /**
     * Init Process
     */
    protected void initRoutine() {

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        // Get target Process Control Number from the batch parameter
        try {
            this.procCtrlNum = Integer.parseInt(getUserVariable1());
        } catch (NumberFormatException e) {
            throw new S21AbendException(NLBM1062E);
        }

        // Get new specification mode
        // (Even if not WMS warehouse, do Routing in the new mode)
        this.newSpecMode = getUserVariable2();

        // Get sales date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get system date, system time based on sales date
        this.sysDate = salesDate;
        this.sysTime = salesDate + ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN).substring(8);

        defaultConfigLtDayNum = ZYPCodeDataUtil.getNumConstValue(CONST_KEY_DEFAULT_LT, glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(defaultConfigLtDayNum)) {
            defaultConfigLtDayNum = BigDecimal.ZERO;
        }
        // E N D MOD 04/16/2013 R-WH003

        bizErrMsgMap = new HashMap<String, String>();

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> glblCmpyCd:" + this.glblCmpyCd, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> procCtrlNum:" + this.procCtrlNum, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> newSpecMode:" + this.newSpecMode, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> sysTime:" + this.sysTime, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> salesDate:" + this.salesDate, this);
    }

    /**
     * Main Process
     */
    protected void mainRoutine() {

        // QC#63614 Start
        String[] ordCatgList = getConstOrdCatgCd();
        // QC#63614 End

        // Get List of target Transaction Source Type Code
        Map<String, Object> queryParamForTrxSrcTp = new HashMap<String, Object>();
        queryParamForTrxSrcTp.put("glblCmpyCd", this.glblCmpyCd);
        List<String> shpgOrdCratTpCdList = createShpgOrdCratTpCdList();
        queryParamForTrxSrcTp.put("shpgOrdCratTpCdList", shpgOrdCratTpCdList);
        List<String> trxSrcTpCdList = (List<String>) this.ssmBatchClient.queryObjectList("getTrxSrcTp", queryParamForTrxSrcTp);

        // Get Warehouse, Ship-To by the target Process Control Number
        Map<String, String> queryParamForWhShipTo = new HashMap<String, String>();
        queryParamForWhShipTo.put("glblCmpyCd", this.glblCmpyCd);
        queryParamForWhShipTo.put("procCtrlNum", String.valueOf(this.procCtrlNum));
        queryParamForWhShipTo.put("effDt", this.salesDate);
        List<WhShipToInfo> whShipToInfoList = (List<WhShipToInfo>) this.ssmBatchClient.queryObjectList("getWhShipToFromRTE_CTRL", queryParamForWhShipTo);

        // Process by each Warehouse, Ship-To
        for (int i = 0; i < whShipToInfoList.size(); i++) {

            WhShipToInfo whShipToInfo = whShipToInfoList.get(i);
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> whShipToInfo:" + whShipToInfo, this);

            // If could not get Warehouse information
            if (!ZYPCommonFunc.hasValue(whShipToInfo.getWhSysTpCd()) && !LOC_TP.TECHNICIAN.equals(whShipToInfo.getInvtyLocTpCd())) {
                String[] str = {"INVTY_LOC_CD", "WH or TECH_LOC" };
                S21InfoLogOutput.println(NLBM1002E, str);
                S21InfoLogOutput.println(" (WH Code : " + whShipToInfo.getWhCd() + ") ");
                createMailMsgInfo(" (WH Code : " + whShipToInfo.getWhCd() + ") ", NLBM1002E, str);
                this.totalErrorCount++;
                continue;
            }

            // Get target Shipping Plan
            Map<String, Object> queryParamForShpgPln = new HashMap<String, Object>();
            queryParamForShpgPln.put("glblCmpyCd", this.glblCmpyCd);
            queryParamForShpgPln.put("shpgStsCd", SHPG_STS.HARD_ALLOCATED);
            queryParamForShpgPln.put("trxSrcTpCdList", trxSrcTpCdList);
            queryParamForShpgPln.put("whCd", whShipToInfo.getWhCd());
            queryParamForShpgPln.put("shipToCustCd", whShipToInfo.getShipToCustCd());
            queryParamForShpgPln.put("dropShipFlg", ZYPConstant.FLG_ON_Y);
            List<ShpgPlnInfo> shpgPlnList = (List<ShpgPlnInfo>) this.ssmBatchClient.queryObjectList("getShpgPln", queryParamForShpgPln);

            if (shpgPlnList.isEmpty()) {
                String[] str = {"SHPG_PLN_NUM", "SHPG_PLN" };
                S21InfoLogOutput.println(NLBM1002E, str);
                S21InfoLogOutput.println(" (WH Cd:" + whShipToInfo.getWhCd() + ") (ShipToCustCd:" + whShipToInfo.getShipToCustCd() + ")");
                createMailMsgInfo(" (WH Cd:" + whShipToInfo.getWhCd() + ") (ShipToCustCd:" + whShipToInfo.getShipToCustCd() + ")", NLBM1002E, str);
                this.totalErrorCount++;
                continue;
            }

            editShpgPlnList(shpgPlnList);

            // Process target Shipping Plan
            // QC#63614 Start
            boolean errFlg = processTargetShpgPln(shpgPlnList, whShipToInfo.getWhSysTpCd(), whShipToInfo.getEndMthFlg(), ordCatgList);
            // QC#63614 End

            if (errFlg) {
                rollback();
                this.totalErrorCount++;
                this.commitCountGrp = 0;
                this.commitCountGrpDtl = 0;
                this.commitCountCtrl = 0;
            } else {

                // Update Routing Status
                updateRoutingStatus(whShipToInfo);

                // Commit if process count reaches designated commit
                // count
                if (this.commitCountCtrl >= this.getCommitCount()) {
                    commit();
                    this.totalCommitCountGrp += this.commitCountGrp;
                    this.totalCommitCountGrpDtl += this.commitCountGrpDtl;
                    this.totalCommitCountCtrl += this.commitCountCtrl;
                    this.commitCountGrp = 0;
                    this.commitCountGrpDtl = 0;
                    this.commitCountCtrl = 0;
                }
            }
        }

        // Send email for business errors
        if (!bizErrMsgMap.isEmpty()) {

            for (Map.Entry<String, String> item : bizErrMsgMap.entrySet()) {
                String bizErrMsg = item.getValue();
                sendBizErrMail(item.getKey(), bizErrMsg);
            }
        }

        // If there is any business error, send mail
        if (this.mailMsgInfo != null) {
            callPostMail();
            this.termCd = TERM_CD.WARNING_END;
        }

        // Commit remains
        commit();
        this.totalCommitCountGrp += this.commitCountGrp;
        this.totalCommitCountGrpDtl += this.commitCountGrpDtl;
        this.totalCommitCountCtrl += this.commitCountCtrl;
    }

    /**
     * End Process
     */
    protected void termRoutine() {

        // outputCacheMapSize();
        // outputProcessCnt();
        String[] strGrp = {"RTE_GRP", "created", String.valueOf(this.totalCommitCountGrp) };
        String[] strGrpDtl = {"RTE_GRP_DTL", "created", String.valueOf(this.totalCommitCountGrpDtl) };
        String[] strCtrl = {"RTE_CTRL", "updated", String.valueOf(this.totalCommitCountCtrl) };

        S21InfoLogOutput.println(ZZBM0009I, strGrp);
        S21InfoLogOutput.println(ZZBM0009I, strGrpDtl);
        S21InfoLogOutput.println(ZZBM0009I, strCtrl);

        int totalProcessCount = this.totalCommitCountCtrl + this.totalErrorCount;
        setTermState(this.termCd, this.totalCommitCountCtrl, this.totalErrorCount, totalProcessCount);
    }

    /**
     * Main
     * @param args Argument
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NLBB002001().executeBatch(NLBB002001.class.getSimpleName());
    }

    /**
     * Create list of SHPG_ORD_CRAT_TP_CD
     * @return List of SHPG_ORD_CRAT_TP_CD
     */
    private List<String> createShpgOrdCratTpCdList() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createShpgOrdCratTpCdList ] start", this);

        List<String> list = new ArrayList<String>();

        // Create list of code which are target of SO Creation
        list.add(ROUTING_SO_BY_ORDER);
        list.add(ROUTING_SO_OVER_ORDER);
        list.add(NOT_ROUTING_SO_BY_ORDER);
        list.add(NOT_ROUTING_SO_BY_SHIP_COMPLETE);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ createShpgOrdCratTpCdList ] end", this);
        return list;
    }

    /**
     * Process target Shipping Plan(Main Process)
     * 
     * QC#63614 ordCatgList add
     * @param shpgPlnList List of ShpgPlnInfo
     * @param whSysTpCd WH_SYS_TP_CD
     * @param endMthFlg END_MTH_FLG
     * @param ordCatgList List of DS_ORD_CATG_CD for Except SO Divide
     * @return Error or Not
     */
    private boolean processTargetShpgPln(List<ShpgPlnInfo> shpgPlnList, String whSysTpCd, String endMthFlg, String[] ordCatgList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ processTargetShpgPln ] start", this);

        int i = 0;
        boolean errFlg = false;
        ShpgPlnInfo shpgPlnInfoPrevious = null;
        List<ShpgPlnInfo> prospectiveGroupList = new ArrayList<ShpgPlnInfo>();

        for (i = 0; i < shpgPlnList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = shpgPlnList.get(i);
            shpgPlnInfo = removeNullFromShpgPlnInfo(shpgPlnInfo);

            if (!ZYPCommonFunc.hasValue(shpgPlnInfo.getConfigLtDayNum())) {
                if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfo.getConfigItemFlg())) {
                    shpgPlnInfo.setConfigLtDayNum(defaultConfigLtDayNum);
                } else {
                    shpgPlnInfo.setConfigLtDayNum(BigDecimal.ZERO);
                }
            }

            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> i:" + i, this);
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgPlnInfo:" + shpgPlnInfo, this);

            // Get SO create type code
            TRX_SRC_TPTMsg trxSrcTp = getTrxSrcTp(shpgPlnInfo.getTrxSrcTpCd());
            if (trxSrcTp == null) {
                // Impossible
                throw new S21AbendException(NLBM1063E);
            }
            shpgPlnInfo.setShpgOrdCratTpCd(trxSrcTp.shpgOrdCratTpCd.getValue());
            shpgPlnInfo.setCpoExstFlg(trxSrcTp.cpoExstFlg.getValue());

            // Get In pound weight
            errFlg = setInPoundWeight(shpgPlnInfo);
            if (errFlg) {
                return true;
            }

            // Get CPO order type code and trial loan reason code
            if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfo.getCpoExstFlg())) {
                CPOTMsg cpo = getCpo(shpgPlnInfo.getTrxHdrNum());
                if (cpo == null) {
                    String[] str = {"CPO_ORD_NUM", "CPO" };
                    S21InfoLogOutput.println(NLBM1002E, str);
                    String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
                    createMailMsgInfo(key, NLBM1002E, str);
                    return true;
                }
                shpgPlnInfo.setCpoOrdTpCd(cpo.cpoOrdTpCd.getValue());
            }

            // Get ST_CD, POST_CD
            if (!ZYPConstant.FLG_ON_Y.equals(shpgPlnInfo.getDropShipFlg())) {
                if (TRX_SRC_TP.WHOLE_SALES.equals(shpgPlnInfo.getTrxSrcTpCd()) || TRX_SRC_TP.RETAIL.equals(shpgPlnInfo.getTrxSrcTpCd())) {
                    SHIP_TO_CUSTTMsg shipToCust = getShipToCust(shpgPlnInfo);
                    if (shipToCust == null) {
                        return true;
                    }
                    shpgPlnInfo.setShipToStCd(shipToCust.stCd.getValue());
                    shpgPlnInfo.setShipToPostCd(shipToCust.postCd.getValue());
                } else if (TRX_SRC_TP.WAREHOUSE_TRANSFER.equals(shpgPlnInfo.getTrxSrcTpCd())) {
                    // Get ST_CD, POST_CD from WH Master
                    // WHTMsg wh = getWh(shpgPlnInfo);
                    DS_INVTY_LOC_VTMsg dsInvtyLocV = getDSInvtyLocV(shpgPlnInfo);

                    if (dsInvtyLocV == null) {
                        return true;
                    }
                    shpgPlnInfo.setShipToStCd(dsInvtyLocV.stCd.getValue());
                    shpgPlnInfo.setShipToPostCd(dsInvtyLocV.postCd.getValue());
                }
            }

            // 2nd record or over
            if (i > 0) {

                if (NOT_ROUTING_SO_BY_SHIP_COMPLETE.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                    // No Routing Mode
                    // QC#63614 Start
                    boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, false, ordCatgList);
                    // QC#63614 End
                    if (!sameGroupFlg) {

                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                        // Do Light Routing Mode
                        errFlg = doLightRoutingMode(prospectiveGroupList);
                        if (errFlg) {
                            return errFlg;
                        }

                        // Initialize
                        prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                    }

                } else if (ZYPConstant.FLG_ON_Y.equals(endMthFlg)) {

                    // No Routing Mode
                    // QC#63614 Start
                    boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, false, ordCatgList);
                    // QC#63614 End
                    if (!sameGroupFlg) {

                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                        // Do Light Routing Mode
                        errFlg = doLightRoutingMode(prospectiveGroupList);
                        if (errFlg) {
                            return errFlg;
                        }

                        // Initialize
                        prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                    }

                } else if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfoPrevious.getExptFlg())) {

                    // QC#63614 Start
                    boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, false, ordCatgList);
                    // QC#63614 End
                    if (!sameGroupFlg) {

                        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                        // Do Light Routing Mode
                        errFlg = doLightRoutingMode(prospectiveGroupList);
                        if (errFlg) {
                            return errFlg;
                        }

                        // Initialize
                        prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                    }

                } else {

                    // New Specification Mode
                    if (!ZYPCommonFunc.hasValue(this.newSpecMode) || ZYPConstant.FLG_ON_1.equals(this.newSpecMode)) {

                        // Routing Mode
                        if (ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd()) || ROUTING_SO_OVER_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                            // QC#63614 Start
                            boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, true, ordCatgList);
                            // QC#63614 End
                            if (!sameGroupFlg) {

                                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                                // Do Routing Mode
                                errFlg = doRoutingMode(prospectiveGroupList, shpgPlnInfoPrevious.getRddDt());
                                if (errFlg) {
                                    return errFlg;
                                }

                                // Initialize
                                prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                            }

                            // No Routing Mode
                        } else if (NOT_ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                            // QC#63614 Start
                            boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, false, ordCatgList);
                            // QC#63614 End
                            if (!sameGroupFlg) {

                                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                                // Do Light Routing Mode
                                errFlg = doLightRoutingMode(prospectiveGroupList);
                                if (errFlg) {
                                    return errFlg;
                                }

                                // Initialize
                                prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                            }
                        }

                        // Old Specification Mode (Leave the logic in
                        // case of the specification change)
                    } else if (ZYPConstant.FLG_OFF_0.equals(this.newSpecMode)) {

                        if (WH_SYS_TP.WMS.equals(whSysTpCd)) {

                            // Routing Mode
                            if (ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd()) || ROUTING_SO_OVER_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                                // QC#63614 Start
                                boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, true, ordCatgList);
                                // QC#63614 End
                                if (!sameGroupFlg) {

                                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                                    // Do Routing Mode
                                    errFlg = doRoutingMode(prospectiveGroupList, shpgPlnInfoPrevious.getRddDt());
                                    if (errFlg) {
                                        return errFlg;
                                    }

                                    // Initialize
                                    prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                                }

                                // No Routing Mode
                            } else if (NOT_ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                                // QC#63614 Start
                                boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, false, ordCatgList);
                                // QC#63614 End
                                if (!sameGroupFlg) {

                                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                                    // Do Light Routing Mode
                                    errFlg = doLightRoutingMode(prospectiveGroupList);
                                    if (errFlg) {
                                        return errFlg;
                                    }

                                    // Initialize
                                    prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                                }
                            }

                            // No Routing Mode
                        } else {

                            // QC#63614 Start
                            boolean sameGroupFlg = isSameProspectiveGroup(shpgPlnInfo, shpgPlnInfoPrevious, false, ordCatgList);
                            // QC#63614 End
                            if (!sameGroupFlg) {

                                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Prospective Group!", this);

                                // Do Light Routing Mode
                                errFlg = doLightRoutingMode(prospectiveGroupList);
                                if (errFlg) {
                                    return errFlg;
                                }

                                // Initialize
                                prospectiveGroupList = new ArrayList<ShpgPlnInfo>();
                            }
                        }
                    }
                }
            }

            // Add the record to list until different group record
            // appears
            prospectiveGroupList.add(shpgPlnInfo);

            // Store the record as a previous one
            shpgPlnInfoPrevious = shpgPlnInfo;
        }

        // The last record process
        if (i > 0) {

            if (NOT_ROUTING_SO_BY_SHIP_COMPLETE.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                // Do Light Routing Mode
                errFlg = doLightRoutingMode(prospectiveGroupList);
                if (errFlg) {
                    return errFlg;
                }

                // if (ZYPConstant.FLG_ON_Y.equals(endMthFlg)) {
            } else if (ZYPConstant.FLG_ON_Y.equals(endMthFlg)) {

                // Do Light Routing Mode
                errFlg = doLightRoutingMode(prospectiveGroupList);
                if (errFlg) {
                    return errFlg;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfoPrevious.getExptFlg())) {

                // Do Light Routing Mode
                errFlg = doLightRoutingMode(prospectiveGroupList);
                if (errFlg) {
                    return errFlg;
                }

            } else {
                // New Specification Mode
                if (!ZYPCommonFunc.hasValue(this.newSpecMode) || ZYPConstant.FLG_ON_1.equals(this.newSpecMode)) {

                    // Routing Mode
                    if (ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd()) || ROUTING_SO_OVER_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                        // Do Routing Mode
                        errFlg = doRoutingMode(prospectiveGroupList, shpgPlnInfoPrevious.getRddDt());
                        if (errFlg) {
                            return errFlg;
                        }

                        // No Routing Mode
                    } else if (NOT_ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                        // Do Light Routing Mode
                        errFlg = doLightRoutingMode(prospectiveGroupList);
                        if (errFlg) {
                            return errFlg;
                        }
                    }

                    // Old Specification Mode (Leave the logic in case
                    // of the specification change)
                } else if (ZYPConstant.FLG_OFF_0.equals(this.newSpecMode)) {

                    if (WH_SYS_TP.WMS.equals(whSysTpCd)) {

                        // Routing Mode
                        if (ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd()) || ROUTING_SO_OVER_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                            // Do Routing Mode
                            errFlg = doRoutingMode(prospectiveGroupList, shpgPlnInfoPrevious.getRddDt());
                            if (errFlg) {
                                return errFlg;
                            }

                            // No Routing Mode
                        } else if (NOT_ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())) {

                            // Do Light Routing Mode
                            errFlg = doLightRoutingMode(prospectiveGroupList);
                            if (errFlg) {
                                return errFlg;
                            }
                        }

                        // No Routing Mode
                    } else {
                        // Do Light Routing Mode
                        errFlg = doLightRoutingMode(prospectiveGroupList);
                        if (errFlg) {
                            return errFlg;
                        }
                    }
                }
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ processTargetShpgPln ] end", this);
        return errFlg;
    }

    /**
     * Remove null from ShpgPlnInfo (character items only)
     * @param shpgPlnInfo ShpgPlnInfo
     * @return ShpgPlnInfo
     */
    private ShpgPlnInfo removeNullFromShpgPlnInfo(ShpgPlnInfo shpgPlnInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ removeNullFromShpgPlnInfo ] start", this);

        shpgPlnInfo.setGlblCmpyCd(nullToEmpty(shpgPlnInfo.getGlblCmpyCd()));
        shpgPlnInfo.setInvtyLocCd(nullToEmpty(shpgPlnInfo.getInvtyLocCd()));
        shpgPlnInfo.setShipToCustCd(nullToEmpty(shpgPlnInfo.getShipToCustCd()));
        shpgPlnInfo.setTrxSrcTpCd(nullToEmpty(shpgPlnInfo.getTrxSrcTpCd()));
        shpgPlnInfo.setRddDt(nullToEmpty(shpgPlnInfo.getRddDt()));
        shpgPlnInfo.setRsdDt(nullToEmpty(shpgPlnInfo.getRsdDt()));
        shpgPlnInfo.setDropShipFlg(nullToEmpty(shpgPlnInfo.getDropShipFlg()));
        shpgPlnInfo.setReqFrtChrgToCd(nullToEmpty(shpgPlnInfo.getReqFrtChrgToCd()));
        shpgPlnInfo.setReqFrtChrgMethCd(nullToEmpty(shpgPlnInfo.getReqFrtChrgMethCd()));
        shpgPlnInfo.setRqstCarrCd(nullToEmpty(shpgPlnInfo.getRqstCarrCd()));
        shpgPlnInfo.setCarrAcctNum(nullToEmpty(shpgPlnInfo.getCarrAcctNum()));
        shpgPlnInfo.setShipToPostCd(nullToEmpty(shpgPlnInfo.getShipToPostCd()));
        shpgPlnInfo.setShipToFirstLineAddr(nullToEmpty(shpgPlnInfo.getShipToFirstLineAddr()));
        shpgPlnInfo.setShipToScdLineAddr(nullToEmpty(shpgPlnInfo.getShipToScdLineAddr()));
        shpgPlnInfo.setShipToThirdLineAddr(nullToEmpty(shpgPlnInfo.getShipToThirdLineAddr()));
        shpgPlnInfo.setShipToFrthLineAddr(nullToEmpty(shpgPlnInfo.getShipToFrthLineAddr()));
        shpgPlnInfo.setOrigShpgSvcLvlCd(nullToEmpty(shpgPlnInfo.getOrigShpgSvcLvlCd()));
        shpgPlnInfo.setTrxHdrNum(nullToEmpty(shpgPlnInfo.getTrxHdrNum()));
        shpgPlnInfo.setMdseCd(nullToEmpty(shpgPlnInfo.getMdseCd()));
        shpgPlnInfo.setCustUomCd(nullToEmpty(shpgPlnInfo.getCustUomCd()));
        shpgPlnInfo.setShpgPlnNum(nullToEmpty(shpgPlnInfo.getShpgPlnNum()));
        // avalSoQty is number item
        shpgPlnInfo.setTrxLineNum(nullToEmpty(shpgPlnInfo.getTrxLineNum()));
        shpgPlnInfo.setShpgStsCd(nullToEmpty(shpgPlnInfo.getShpgStsCd()));
        shpgPlnInfo.setRteStsCd(nullToEmpty(shpgPlnInfo.getRteStsCd()));
        shpgPlnInfo.setSellToCustCd(nullToEmpty(shpgPlnInfo.getSellToCustCd()));
        shpgPlnInfo.setShipToStCd(nullToEmpty(shpgPlnInfo.getShipToStCd()));
        shpgPlnInfo.setShpgOrdCratTpCd(nullToEmpty(shpgPlnInfo.getShpgOrdCratTpCd()));
        shpgPlnInfo.setCpoExstFlg(nullToEmpty(shpgPlnInfo.getCpoExstFlg()));
        // inPoundWt is number item
        shpgPlnInfo.setCpoOrdTpCd(nullToEmpty(shpgPlnInfo.getCpoOrdTpCd()));
        // shpgPlnWt is number item
        shpgPlnInfo.setShpgModeCd(nullToEmpty(shpgPlnInfo.getShpgModeCd()));
        shpgPlnInfo.setShpgSvcLvlCd(nullToEmpty(shpgPlnInfo.getShpgSvcLvlCd()));
        shpgPlnInfo.setSddDt(nullToEmpty(shpgPlnInfo.getSddDt()));
        shpgPlnInfo.setPsdDt(nullToEmpty(shpgPlnInfo.getPsdDt()));
        shpgPlnInfo.setPddDt(nullToEmpty(shpgPlnInfo.getPddDt()));
        shpgPlnInfo.setExptFlg(nullToEmpty(shpgPlnInfo.getExptFlg()));

        // shpgPlnInfo.setShipCpltCd(nullToEmpty(shpgPlnInfo.getShipCpltCd()));
        shpgPlnInfo.setDsOrdPosnNum(nullToEmpty(shpgPlnInfo.getDsOrdPosnNum()));

        // No need for configItemFlg/custExForLtCalcFlg/hazMatFlg
        // never have null value
        // QC#17395 Start
        shpgPlnInfo.setWhOwnrCd(nullToEmpty(shpgPlnInfo.getWhOwnrCd()));
        // QC#17395 End

        EZDDebugOutput.println(1, PROGRAM_ID + "[ removeNullFromShpgPlnInfo ] end", this);
        return shpgPlnInfo;
    }

    /**
     * Return If same Prospective Group or Not
     * 
     * QC#63614 ordCatgList add
     * @param shpgPlnInfo ShpgPlnInfo
     * @param shpgPlnInfoPrevious ShpgPlnInfo (previous record)
     * @param routingMode Routing Mode false=No routing True=routing
     * @param ordCatgList List of DS_ORD_CATG_CD for Except SO Divide
     * @return same prospective group or not
     */
    private boolean isSameProspectiveGroup(ShpgPlnInfo shpgPlnInfo, ShpgPlnInfo shpgPlnInfoPrevious, boolean routingMode, String[] ordCatgList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ isSameProspectiveGroup ] start", this);

        boolean sameGroupFlg = false;

        // Un-used
        if (routingMode) {

            // OCE QC#616 HazMat Mdse and non-HazMat Mdse should ship
            // separately.Except DsOrdPosnNum is the same value with
            // Previous record.
            
            // QC#17395 Start
            // SO should be saparated by hazmatFlag in case of specific WH Owner instead of Transaction Source Type 
            
//            if (!TRX_SRC_TP.WAREHOUSE_TRANSFER.equals(shpgPlnInfo.getTrxSrcTpCd())) {
//                if (!shpgPlnInfo.getHazMatFlg().equals(shpgPlnInfoPrevious.getHazMatFlg())) {
//
//                    if (("").equals(shpgPlnInfo.getDsOrdPosnNum())) {
//                        return false;
//                    }
//                    if (!shpgPlnInfo.getDsOrdPosnNum().equals(shpgPlnInfoPrevious.getDsOrdPosnNum())) {
//                        return false;
//                    }
//                }
//            }

            if (getConstWhOwnrCd() != null && getConstWhOwnrCd().length > 0) {
                for (String whOwnrCd : getConstWhOwnrCd()) {
                    if (whOwnrCd.equals(shpgPlnInfo.getWhOwnrCd())) {
                        if (!shpgPlnInfo.getDsOrdPosnNum().equals(shpgPlnInfoPrevious.getDsOrdPosnNum())) {
                            return false;
                        }
                        // QC#17395-1 Mod
                        if (!ZYPCommonFunc.hasValue(shpgPlnInfo.getSvcConfigMstrPk()) && !shpgPlnInfo.getHazMatFlg().equals(shpgPlnInfoPrevious.getHazMatFlg())) {
                            return false;
                        }
                    }
                }
            }
            // QC#17395 End
            
            // RDD designation
            if (ZYPCommonFunc.hasValue(shpgPlnInfoPrevious.getRddDt())) {

                // One time user
                if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfoPrevious.getDropShipFlg())) {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRddDt().equals(shpgPlnInfoPrevious.getRddDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum()) && shpgPlnInfo.getShipToPostCd().equals(shpgPlnInfoPrevious.getShipToPostCd())
                            && shpgPlnInfo.getShipToFirstLineAddr().equals(shpgPlnInfoPrevious.getShipToFirstLineAddr()) && shpgPlnInfo.getShipToScdLineAddr().equals(shpgPlnInfoPrevious.getShipToScdLineAddr())
                            && shpgPlnInfo.getShipToThirdLineAddr().equals(shpgPlnInfoPrevious.getShipToThirdLineAddr()) && shpgPlnInfo.getShipToFrthLineAddr().equals(shpgPlnInfoPrevious.getShipToFrthLineAddr())) {

                        sameGroupFlg = true;
                    }

                } else {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRddDt().equals(shpgPlnInfoPrevious.getRddDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum())) {

                        sameGroupFlg = true;
                    }
                }

                // RSD designation
            } else {

                // One time user
                if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfoPrevious.getDropShipFlg())) {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRsdDt().equals(shpgPlnInfoPrevious.getRsdDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum()) && shpgPlnInfo.getShipToPostCd().equals(shpgPlnInfoPrevious.getShipToPostCd())
                            && shpgPlnInfo.getShipToFirstLineAddr().equals(shpgPlnInfoPrevious.getShipToFirstLineAddr()) && shpgPlnInfo.getShipToScdLineAddr().equals(shpgPlnInfoPrevious.getShipToScdLineAddr())
                            && shpgPlnInfo.getShipToThirdLineAddr().equals(shpgPlnInfoPrevious.getShipToThirdLineAddr()) && shpgPlnInfo.getShipToFrthLineAddr().equals(shpgPlnInfoPrevious.getShipToFrthLineAddr())
                            && shpgPlnInfo.getOrigShpgSvcLvlCd().equals(shpgPlnInfoPrevious.getOrigShpgSvcLvlCd())) {

                        sameGroupFlg = true;
                    }

                } else {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRsdDt().equals(shpgPlnInfoPrevious.getRsdDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum()) && shpgPlnInfo.getOrigShpgSvcLvlCd().equals(shpgPlnInfoPrevious.getOrigShpgSvcLvlCd())) {

                        sameGroupFlg = true;
                    }
                }
            }

            // No Routing Mode
        } else {

            // OCE QC#616 HazMat Mdse and non-HazMat Mdse should ship
            // separately.Except DsOrdPosnNum is the same value with
            // Previous record.
            
            // QC#17395 Start
            // SO should be saparated by hazmatFlag in case of specific WH Owner instead of Transaction Source Type 
            
//            if (!TRX_SRC_TP.WAREHOUSE_TRANSFER.equals(shpgPlnInfo.getTrxSrcTpCd())) {
//                if (!shpgPlnInfo.getHazMatFlg().equals(shpgPlnInfoPrevious.getHazMatFlg())) {
//
//                    if (("").equals(shpgPlnInfo.getDsOrdPosnNum())) {
//                        return false;
//                    }
//                    if (!shpgPlnInfo.getDsOrdPosnNum().equals(shpgPlnInfoPrevious.getDsOrdPosnNum())) {
//                        return false;
//                    }
//
//                }
//            }
//
//            if (NOT_ROUTING_SO_BY_SHIP_COMPLETE.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())
//                    // In case of Whole Sales, SO should be separeted by Ship Complete Code.
//                    || TRX_SRC_TP.WHOLE_SALES.equals(shpgPlnInfo.getTrxSrcTpCd())
//                    ) {
//
//                if (!shpgPlnInfo.getDsOrdPosnNum().equals(shpgPlnInfoPrevious.getDsOrdPosnNum())) {
//                    return false;
//                }
//            }

            if (getConstWhOwnrCd() != null && getConstWhOwnrCd().length > 0) {
                for (String whOwnrCd : getConstWhOwnrCd()) {
                    if (whOwnrCd.equals(shpgPlnInfo.getWhOwnrCd())) {
                        if (!shpgPlnInfo.getDsOrdPosnNum().equals(shpgPlnInfoPrevious.getDsOrdPosnNum())) {
                            return false;
                        }
                        // QC#17395-1 Mod
                        if (!ZYPCommonFunc.hasValue(shpgPlnInfo.getSvcConfigMstrPk()) && !shpgPlnInfo.getHazMatFlg().equals(shpgPlnInfoPrevious.getHazMatFlg())) {
                            return false;
                        }
                    }
                }
            }

            // QC#63614 Start
            for (String ordCatgCd : ordCatgList) {
                if (!NOT_ROUTING_SO_BY_ORDER.equals(shpgPlnInfoPrevious.getShpgOrdCratTpCd())
                        || !ordCatgCd.equals(shpgPlnInfo.getDsOrdCatgCd())) {
                  if (!shpgPlnInfo.getDsOrdPosnNum().equals(shpgPlnInfoPrevious.getDsOrdPosnNum())) {
                      return false;
                  }
                }
            }
            // QC#63614 End

            // QC#17395 End

            // RDD designation
            if (ZYPCommonFunc.hasValue(shpgPlnInfoPrevious.getRddDt())) {
                // One time user
                if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfoPrevious.getDropShipFlg())) {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRddDt().equals(shpgPlnInfoPrevious.getRddDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum()) && shpgPlnInfo.getShipToPostCd().equals(shpgPlnInfoPrevious.getShipToPostCd())
                            && shpgPlnInfo.getShipToFirstLineAddr().equals(shpgPlnInfoPrevious.getShipToFirstLineAddr()) && shpgPlnInfo.getShipToScdLineAddr().equals(shpgPlnInfoPrevious.getShipToScdLineAddr())
                            && shpgPlnInfo.getShipToThirdLineAddr().equals(shpgPlnInfoPrevious.getShipToThirdLineAddr()) && shpgPlnInfo.getShipToFrthLineAddr().equals(shpgPlnInfoPrevious.getShipToFrthLineAddr())
                            && shpgPlnInfo.getOrigShpgSvcLvlCd().equals(shpgPlnInfoPrevious.getOrigShpgSvcLvlCd()) && shpgPlnInfo.getTrxHdrNum().equals(shpgPlnInfoPrevious.getTrxHdrNum())) {

                        sameGroupFlg = true;
                    }

                } else {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRddDt().equals(shpgPlnInfoPrevious.getRddDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum()) && shpgPlnInfo.getOrigShpgSvcLvlCd().equals(shpgPlnInfoPrevious.getOrigShpgSvcLvlCd())
                            && shpgPlnInfo.getTrxHdrNum().equals(shpgPlnInfoPrevious.getTrxHdrNum())) {

                        sameGroupFlg = true;
                    }
                }

                // RSD designation
            } else {

                // One time user
                if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfoPrevious.getDropShipFlg())) {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRsdDt().equals(shpgPlnInfoPrevious.getRsdDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum()) && shpgPlnInfo.getShipToPostCd().equals(shpgPlnInfoPrevious.getShipToPostCd())
                            && shpgPlnInfo.getShipToFirstLineAddr().equals(shpgPlnInfoPrevious.getShipToFirstLineAddr()) && shpgPlnInfo.getShipToScdLineAddr().equals(shpgPlnInfoPrevious.getShipToScdLineAddr())
                            && shpgPlnInfo.getShipToThirdLineAddr().equals(shpgPlnInfoPrevious.getShipToThirdLineAddr()) && shpgPlnInfo.getShipToFrthLineAddr().equals(shpgPlnInfoPrevious.getShipToFrthLineAddr())
                            && shpgPlnInfo.getOrigShpgSvcLvlCd().equals(shpgPlnInfoPrevious.getOrigShpgSvcLvlCd()) && shpgPlnInfo.getTrxHdrNum().equals(shpgPlnInfoPrevious.getTrxHdrNum())) {

                        sameGroupFlg = true;
                    }

                } else {

                    // Check if there are no differences between
                    // current record and the previous one
                    if (shpgPlnInfo.getTrxSrcTpCd().equals(shpgPlnInfoPrevious.getTrxSrcTpCd()) && shpgPlnInfo.getExptFlg().equals(shpgPlnInfoPrevious.getExptFlg()) && shpgPlnInfo.getRsdDt().equals(shpgPlnInfoPrevious.getRsdDt())
                            && shpgPlnInfo.getDropShipFlg().equals(shpgPlnInfoPrevious.getDropShipFlg()) && shpgPlnInfo.getReqFrtChrgToCd().equals(shpgPlnInfoPrevious.getReqFrtChrgToCd())
                            && shpgPlnInfo.getReqFrtChrgMethCd().equals(shpgPlnInfoPrevious.getReqFrtChrgMethCd()) && shpgPlnInfo.getRqstCarrCd().equals(shpgPlnInfoPrevious.getRqstCarrCd())
                            && shpgPlnInfo.getCarrAcctNum().equals(shpgPlnInfoPrevious.getCarrAcctNum()) && shpgPlnInfo.getOrigShpgSvcLvlCd().equals(shpgPlnInfoPrevious.getOrigShpgSvcLvlCd())
                            && shpgPlnInfo.getTrxHdrNum().equals(shpgPlnInfoPrevious.getTrxHdrNum())) {

                        sameGroupFlg = true;
                    }
                }
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ isSameProspectiveGroup ] end", this);
        return sameGroupFlg;
    }

    /**
     * Do Light Routing Mode
     * @param prospectiveGroupList List of ShpgPlnInfo
     * @return Error or Not
     */
    private boolean doLightRoutingMode(List<ShpgPlnInfo> prospectiveGroupList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ doLightMode ] start", this);

        boolean errFlg = false;
        int dtlCnt = 0;
        BigDecimal groupWt = BigDecimal.ZERO;
        List<ShpgPlnInfo> groupList = new ArrayList<ShpgPlnInfo>();

        for (int i = 0; i < prospectiveGroupList.size(); i++) {

            dtlCnt++;
            ShpgPlnInfo shpgPlnInfo = prospectiveGroupList.get(i);
            BigDecimal shpgPlnWt = getShpgPlnWt(shpgPlnInfo);
            shpgPlnInfo.setShpgPlnWt(shpgPlnWt);

            if (MAX_RTE_LINE_NUM <= dtlCnt) {

                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Detail Count Over!", this);

                // Do group process
                errFlg = doGroupProcessForNoRouting(groupList, groupWt);
                if (errFlg) {
                    return errFlg;
                }

                // Initialize
                dtlCnt = 1;
                groupWt = BigDecimal.ZERO;
                groupWt = groupWt.add(shpgPlnInfo.getShpgPlnWt());
                groupList = new ArrayList<ShpgPlnInfo>();
                groupList.add(shpgPlnInfo);

                continue;
            }

            groupWt = groupWt.add(shpgPlnInfo.getShpgPlnWt());
            groupList.add(shpgPlnInfo);
        }

        if (dtlCnt > 0) {
            // Do group process
            errFlg = doGroupProcessForNoRouting(groupList, groupWt);
            if (errFlg) {
                return errFlg;
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ doLightMode ] end", this);

        return false;
    }

    /**
     * Do Routing Mode
     * @param prospectiveGroupList List of ShpgPlnInfo
     * @param rddDt RDD_DT
     * @return Error or Not
     */
    private boolean doRoutingMode(List<ShpgPlnInfo> prospectiveGroupList, String rddDt) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ doRoutingMode ] start", this);

        boolean errFlg;

        // RDD designation
        if (ZYPCommonFunc.hasValue(rddDt)) {

            errFlg = doRoutingRdd(prospectiveGroupList);

            // RSD designation
        } else {

            errFlg = doRoutingRsd(prospectiveGroupList);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ doRoutingMode ] end", this);
        return errFlg;
    }

    /**
     * Do Routing (RDD designation)
     * @param prospectiveGroupList List of ShpgPlnInfo
     * @return Error or Not
     */
    private boolean doRoutingRdd(List<ShpgPlnInfo> prospectiveGroupList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ doRoutingRdd ] start", this);

        int i;
        boolean errFlg = false;

        for (i = 0; i < prospectiveGroupList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = prospectiveGroupList.get(i);

            // Calculate each Shipping Plan weight (IN_POUND_WT *
            // AVAL_SO_QTY)
            // BigDecimal shpgPlnWt =
            // shpgPlnInfo.getInPoundWt().multiply(shpgPlnInfo.getAvalSoQty());
            BigDecimal shpgPlnWt = getShpgPlnWt(shpgPlnInfo);
            shpgPlnInfo.setShpgPlnWt(shpgPlnWt);

            // Get available Shipping Mode
            List<String> shpgModeCdList;
            shpgModeCdList = getAvailableShpgMode(shpgPlnInfo, false, null, true);
            if (shpgModeCdList == null) {
                return true;
            }

            // Get available Shipping Service Level and Shipping Mode
            List<ShpgSvcInfo> shpgSvcList;
            shpgSvcList = getAvailableShpgSvcForRdd(shpgPlnInfo, shpgModeCdList);
            if (shpgSvcList == null) {
                return true;
            }

            // Calculate delivery date
            DeliveryDateCalcInfo calcInfo = new DeliveryDateCalcInfo();
            calcInfo.setWhCd(shpgPlnInfo.getRtlWhCd());
            calcInfo.setShipToCustCd(shpgPlnInfo.getShipToCustCd());
            calcInfo.setShpgSvcInfoList(shpgSvcList);
            calcInfo.setRddDt(shpgPlnInfo.getRddDt());
            calcInfo.setShipToStCd(shpgPlnInfo.getShipToStCd());
            calcInfo.setShipToPostCd(shpgPlnInfo.getShipToPostCd());
            calcInfo.setTrxHdrNum(shpgPlnInfo.getTrxHdrNum());
            calcInfo.setShpgPlnNum(shpgPlnInfo.getShpgPlnNum());
            calcInfo.setConfigItemFlg(shpgPlnInfo.getConfigItemFlg());
            calcInfo.setConfigLtDayNum(shpgPlnInfo.getConfigLtDayNum());
            calcInfo.setInvtyLocTpCd(shpgPlnInfo.getInvtyLocTpCd());

            errFlg = calculateDeliveryDateForRdd(calcInfo);
            if (errFlg) {
                return true;
            }
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> calcInfo(after):" + calcInfo, this);

            // Set calculation result
            shpgPlnInfo.setShpgModeCd(calcInfo.getShpgModeCd());
            shpgPlnInfo.setShpgSvcLvlCd(calcInfo.getShpgSvcLvlCd());
            shpgPlnInfo.setSddDt(calcInfo.getSddDt());
            shpgPlnInfo.setPsdDt(calcInfo.getPsdDt());
            shpgPlnInfo.setPddDt(calcInfo.getPddDt());
        }

        // Sort list by grouping items
        prospectiveGroupList = sortProspectiveGroupList(prospectiveGroupList);

        int dtlCnt = 0;
        BigDecimal groupWt = BigDecimal.ZERO;
        ShpgPlnInfo shpgPlnInfoPrevious = null;
        List<ShpgPlnInfo> groupList = new ArrayList<ShpgPlnInfo>();

        for (i = 0; i < prospectiveGroupList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = prospectiveGroupList.get(i);

            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> i:" + i, this);
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgPlnInfo(afterCalc):" + shpgPlnInfo, this);

            // 2nd record or over
            if (i > 0) {

                boolean sameGroupFlg = isSameGroup(shpgPlnInfo, shpgPlnInfoPrevious);
                if (!sameGroupFlg) {

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Group!", this);

                    // Do group process
                    errFlg = doGroupProcess(groupList, groupWt);
                    if (errFlg) {
                        return errFlg;
                    }

                    // Initialize
                    dtlCnt = 0;
                    groupWt = BigDecimal.ZERO;
                    groupList = new ArrayList<ShpgPlnInfo>();
                }

                // Get Max weight of the Shipping Mode
                SHPG_WTTMsg shpgWt = getShpgWt(shpgPlnInfo);
                if (shpgWt == null) {
                    String[] str = {"TRANS_MAX_WT", "SHPG_WT" };
                    S21InfoLogOutput.println(NLBM1002E, str);
                    String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
                    createMailMsgInfo(key, NLBM1002E, str);
                    return true;
                }
                BigDecimal transMaxWt = shpgWt.transMaxWt.getValue();

                // Max weight < Group weight + Shipping Plan Weight
                BigDecimal tmpWt = groupWt.add(shpgPlnInfo.getShpgPlnWt());
                if (transMaxWt.compareTo(tmpWt.setScale(0, BigDecimal.ROUND_HALF_UP)) < 0) {

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Weight Over! :" + tmpWt, this);

                    // Do group process
                    errFlg = doGroupProcess(groupList, groupWt);
                    if (errFlg) {
                        return errFlg;
                    }

                    // Initialize
                    dtlCnt = 0;
                    groupWt = BigDecimal.ZERO;
                    groupList = new ArrayList<ShpgPlnInfo>();
                }

                if (dtlCnt >= MAX_RTE_LINE_NUM) {

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Detail Count Over!", this);

                    // Do group process
                    errFlg = doGroupProcess(groupList, groupWt);
                    if (errFlg) {
                        return errFlg;
                    }

                    // Initialize
                    dtlCnt = 0;
                    groupWt = BigDecimal.ZERO;
                    groupList = new ArrayList<ShpgPlnInfo>();
                }
            }

            // Add each Shipping Plan weight to group weight
            groupWt = groupWt.add(shpgPlnInfo.getShpgPlnWt());

            // Add the record to list until different group record
            // appears
            groupList.add(shpgPlnInfo);

            // Store the record as a previous one
            shpgPlnInfoPrevious = shpgPlnInfo;

            dtlCnt++;
        }

        // The last record process
        if (i > 0) {

            // Do group process
            errFlg = doGroupProcess(groupList, groupWt);
            if (errFlg) {
                return errFlg;
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ doRoutingRdd ] end", this);
        return errFlg;
    }

    /**
     * Do Routing (RSD designation)
     * @param prospectiveGroupList List of ShpgPlnInfo
     * @return Error or Not
     */
    private boolean doRoutingRsd(List<ShpgPlnInfo> prospectiveGroupList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ doRoutingRsd ] start", this);

        int i;
        boolean errFlg = false;

        for (i = 0; i < prospectiveGroupList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = prospectiveGroupList.get(i);

            // Calculate each Shipping Plan weight (IN_POUND_WT *
            // AVAL_SO_QTY)
            // BigDecimal shpgPlnWt =
            // shpgPlnInfo.getInPoundWt().multiply(shpgPlnInfo.getAvalSoQty());
            BigDecimal shpgPlnWt = getShpgPlnWt(shpgPlnInfo);
            shpgPlnInfo.setShpgPlnWt(shpgPlnWt);

            // Get available Shipping Mode
            List<String> shpgModeCdList;
            shpgModeCdList = getAvailableShpgMode(shpgPlnInfo, false, null, true);
            if (shpgModeCdList == null) {
                return true;
            }

            // Get available Shipping Service Level and Shipping Mode
            List<ShpgSvcInfo> shpgSvcList = getAvailableShpgSvcForRsd(shpgPlnInfo, shpgModeCdList);
            if (shpgSvcList == null) {

                return true;
            }

            // Calculate delivery date
            DeliveryDateCalcInfo calcInfo = new DeliveryDateCalcInfo();
            calcInfo.setWhCd(shpgPlnInfo.getRtlWhCd());
            calcInfo.setShipToCustCd(shpgPlnInfo.getShipToCustCd());
            calcInfo.setShpgSvcInfoList(shpgSvcList);
            calcInfo.setRsdDt(shpgPlnInfo.getRsdDt());
            calcInfo.setShipToStCd(shpgPlnInfo.getShipToStCd());
            calcInfo.setShipToPostCd(nullToEmpty(shpgPlnInfo.getShipToPostCd()));
            calcInfo.setTrxHdrNum(shpgPlnInfo.getTrxHdrNum());
            calcInfo.setShpgPlnNum(shpgPlnInfo.getShpgPlnNum());
            calcInfo.setConfigItemFlg(shpgPlnInfo.getConfigItemFlg());
            calcInfo.setConfigLtDayNum(shpgPlnInfo.getConfigLtDayNum());
            calcInfo.setInvtyLocTpCd(shpgPlnInfo.getInvtyLocTpCd());

            errFlg = calculateDeliveryDateForRsd(calcInfo);
            if (errFlg) {
                return true;
            }
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> calcInfo(after):" + calcInfo, this);

            // Set calculation result
            shpgPlnInfo.setShpgModeCd(calcInfo.getShpgModeCd());
            shpgPlnInfo.setShpgSvcLvlCd(calcInfo.getShpgSvcLvlCd());
            shpgPlnInfo.setSddDt(calcInfo.getSddDt());
            shpgPlnInfo.setPsdDt(calcInfo.getPsdDt());
            shpgPlnInfo.setPddDt(calcInfo.getPddDt());
        }

        // Sort list by grouping items
        prospectiveGroupList = sortProspectiveGroupList(prospectiveGroupList);

        int dtlCnt = 0;
        BigDecimal groupWt = BigDecimal.ZERO;
        ShpgPlnInfo shpgPlnInfoPrevious = null;
        List<ShpgPlnInfo> groupList = new ArrayList<ShpgPlnInfo>();

        for (i = 0; i < prospectiveGroupList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = prospectiveGroupList.get(i);

            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> i:" + i, this);
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgPlnInfo(afterCalc):" + shpgPlnInfo, this);

            // 2nd record or over
            if (i > 0) {

                boolean sameGroupFlg = isSameGroup(shpgPlnInfo, shpgPlnInfoPrevious);
                if (!sameGroupFlg) {

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Other Group!", this);

                    // Do group process
                    errFlg = doGroupProcess(groupList, groupWt);
                    if (errFlg) {
                        return errFlg;
                    }

                    // Initialize
                    dtlCnt = 0;
                    groupWt = BigDecimal.ZERO;
                    groupList = new ArrayList<ShpgPlnInfo>();
                }

                // Get Max weight of the Shipping Mode
                SHPG_WTTMsg shpgWt = getShpgWt(shpgPlnInfo);
                if (shpgWt == null) {
                    String[] str = {"TRANS_MAX_WT", "SHPG_WT" };
                    S21InfoLogOutput.println(NLBM1002E, str);
                    String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
                    createMailMsgInfo(key, NLBM1002E, str);
                    return true;
                }
                BigDecimal transMaxWt = shpgWt.transMaxWt.getValue();

                // Max weight < Group weight + Shipping Plan Weight
                BigDecimal tmpWt = groupWt.add(shpgPlnInfo.getShpgPlnWt());
                if (transMaxWt.compareTo(tmpWt.setScale(0, BigDecimal.ROUND_HALF_UP)) < 0) {

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Weight Over! :" + tmpWt, this);

                    // Do group process
                    errFlg = doGroupProcess(groupList, groupWt);
                    if (errFlg) {
                        return errFlg;
                    }

                    // Initialize
                    dtlCnt = 0;
                    groupWt = BigDecimal.ZERO;
                    groupList = new ArrayList<ShpgPlnInfo>();
                }

                if (dtlCnt >= MAX_RTE_LINE_NUM) {

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Detail Count Over!", this);

                    // Do group process
                    errFlg = doGroupProcess(groupList, groupWt);
                    if (errFlg) {
                        return errFlg;
                    }

                    // Initialize
                    dtlCnt = 0;
                    groupWt = BigDecimal.ZERO;
                    groupList = new ArrayList<ShpgPlnInfo>();
                }
            }

            // Add each Shipping Plan weight to group weight
            groupWt = groupWt.add(shpgPlnInfo.getShpgPlnWt());

            // Add the record to list until different group record
            // appears
            groupList.add(shpgPlnInfo);

            // Store the record as a previous one
            shpgPlnInfoPrevious = shpgPlnInfo;

            dtlCnt++;
        }

        // The last record process
        if (i > 0) {

            // Do group process
            errFlg = doGroupProcess(groupList, groupWt);
            if (errFlg) {
                return errFlg;
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ doRoutingRsd ] end", this);
        return errFlg;
    }

    /**
     * Get SHPG_WT
     * @param shpgPlnInfo ShpgPlnInfo
     * @return SHPG_WTTMsg
     */
    private SHPG_WTTMsg getShpgWt(ShpgPlnInfo shpgPlnInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgWt ] start", this);

        SHPG_WTTMsg shpgWt = getShpgWt(shpgPlnInfo, shpgPlnInfo.getSellToCustCd(), shpgPlnInfo.getShipToCustCd());
        if (shpgWt == null) {

            shpgWt = getShpgWt(shpgPlnInfo, shpgPlnInfo.getSellToCustCd(), SHIP_TO_CUST_CD_ALL);
            if (shpgWt == null) {

                shpgWt = getShpgWt(shpgPlnInfo, SELL_TO_CUST_CD_ALL, SHIP_TO_CUST_CD_ALL);
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgWt ] end", this);
        return shpgWt;
    }

    /**
     * Get Available Shipping Mode
     * @param shpgPlnInfo ShpgPlnInfo
     * @param groupModeFlg Group mode or not
     * @param groupWt Group Weight
     * @param noLimitFlg
     * @return List of SHPG_MODE_CD
     */
    private List<String> getAvailableShpgMode(ShpgPlnInfo shpgPlnInfo, boolean groupModeFlg, BigDecimal groupWt, boolean noLimitFlg) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAvailableShpgMode ] start", this);

        List<String> shpgModeCdList = getShpgModeCd(shpgPlnInfo, groupModeFlg, groupWt, shpgPlnInfo.getSellToCustCd(), shpgPlnInfo.getShipToCustCd(), false);
        if (shpgModeCdList == null) {

            shpgModeCdList = getShpgModeCd(shpgPlnInfo, groupModeFlg, groupWt, shpgPlnInfo.getSellToCustCd(), SHIP_TO_CUST_CD_ALL, false);
            if (shpgModeCdList == null) {

                shpgModeCdList = getShpgModeCd(shpgPlnInfo, groupModeFlg, groupWt, SELL_TO_CUST_CD_ALL, SHIP_TO_CUST_CD_ALL, false);
                if (shpgModeCdList == null) {
                    if (noLimitFlg) {
                        shpgModeCdList = getShpgModeCd(shpgPlnInfo, groupModeFlg, groupWt, SELL_TO_CUST_CD_ALL, SHIP_TO_CUST_CD_ALL, true);
                        if (shpgModeCdList == null) {
                            String[] str = {"SHPG_MODE_CD", "SHPG_WT" };
                            S21InfoLogOutput.println(NLBM1002E, str);
                            String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
                            createMailMsgInfo(key, NLBM1002E, str);
                            shpgModeCdList = null;
                        }
                    } else {
                        String[] str = {"SHPG_MODE_CD", "SHPG_WT" };
                        S21InfoLogOutput.println(NLBM1002E, str);
                        String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
                        createMailMsgInfo(key, NLBM1002E, str);
                        shpgModeCdList = null;
                    }
                }
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAvailableShpgMode ] end", this);
        return shpgModeCdList;
    }

    /**
     * Get Shipping Mode Code
     * @param shpgPlnInfo ShpgPlnInfo
     * @param groupModeFlg Group mode or not
     * @param groupWt Group Weight
     * @param sellToCustCd SELL_TO_CUST_CD
     * @param shipToCustCd SHIP_TO_CUST_CD
     * @return List of SHPG_MODE_CD
     */
    private List<String> getShpgModeCd(ShpgPlnInfo shpgPlnInfo, boolean groupModeFlg, BigDecimal groupWt, String sellToCustCd, String shipToCustCd, boolean noLimitFlg) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgModeCd ] start", this);

        // Create key of cache map as search condition
        String frtChrgToCd = shpgPlnInfo.getReqFrtChrgToCd();
        String frtChrgMethCd = shpgPlnInfo.getReqFrtChrgMethCd();
        BigDecimal targetWt = shpgPlnInfo.getShpgPlnWt();
        if (groupModeFlg) {
            targetWt = groupWt;
        }
        targetWt = targetWt.setScale(0, BigDecimal.ROUND_HALF_UP);
        String searchCondition = this.glblCmpyCd + sellToCustCd + shipToCustCd //
                + frtChrgToCd + frtChrgMethCd //
                + targetWt + this.salesDate + groupModeFlg + noLimitFlg;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<String> shpgModeCdList = this.shpgModeCdMap.get(searchCondition);

        if (shpgModeCdList == null) {

            // Get Shipping Mode Code
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("sellToCustCd", sellToCustCd);
            queryParam.put("shipToCustCd", shipToCustCd);
            queryParam.put("frtChrgToCd", frtChrgToCd);
            queryParam.put("frtChrgMethCd", frtChrgMethCd);
            queryParam.put("targetWt", targetWt);
            queryParam.put("effDt", this.salesDate);
            if (noLimitFlg) {
                queryParam.put("noLimitFlg", this.glblCmpyCd);
                String shpgMode = (String) this.ssmBatchClient.queryObject("getAvailableShpgMode", queryParam);
                if (ZYPCommonFunc.hasValue(shpgMode)) {
                    shpgModeCdList = new ArrayList<String>();
                    shpgModeCdList.add(shpgMode);
                }
            } else {
                if (groupModeFlg) {
                    queryParam.put("minReqFlg", this.glblCmpyCd);
                }
                shpgModeCdList = (List<String>) this.ssmBatchClient.queryObjectList("getAvailableShpgMode", queryParam);
            }

            // Store the condition and the result in the map
            this.shpgModeCdMap.put(searchCondition, shpgModeCdList);
        }

        if (shpgModeCdList != null && shpgModeCdList.isEmpty()) {
            shpgModeCdList = null;
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgModeCd ] end", this);
        return shpgModeCdList;
    }

    /**
     * Get Available Shipping Service For RDD
     * @param shpgPlnInfo ShpgPlnInfo
     * @param shpgModeCdList List of SHPG_MODE_CD
     * @return List of ShpgSvcInfo
     */
    private List<ShpgSvcInfo> getAvailableShpgSvcForRdd(ShpgPlnInfo shpgPlnInfo, List<String> shpgModeCdList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAvailableShpgSvcForRdd ] start", this);

        // Get Max Ship Priority of designated Freight Charge and
        // Service Level
        BigDecimal shipPrty = getMaxShipPrty(shpgPlnInfo);
        if (!ZYPCommonFunc.hasValue(shipPrty)) {
            return null;
        }

        // Create key of cache map as search condition
        String frtChrgToCd = shpgPlnInfo.getReqFrtChrgToCd();
        String frtChrgMethCd = shpgPlnInfo.getReqFrtChrgMethCd();
        String searchCondition = this.glblCmpyCd + frtChrgToCd + frtChrgMethCd + shpgModeCdList + shipPrty;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<ShpgSvcInfo> shpgSvcInfoList = this.shpgSvcForRddMap.get(searchCondition);

        if (shpgSvcInfoList == null) {

            // If not stored in the map, search on the condition
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("frtChrgToCd", frtChrgToCd);
            queryParam.put("frtChrgMethCd", frtChrgMethCd);
            queryParam.put("shpgModeCdList", shpgModeCdList);
            queryParam.put("shipPrty", shipPrty);
            queryParam.put("rddFlg", this.glblCmpyCd);
            shpgSvcInfoList = (List<ShpgSvcInfo>) this.ssmBatchClient.queryObjectList("getAvailableShpgSvc", queryParam);

            // Store the condition and the result in the map
            this.shpgSvcForRddMap.put(searchCondition, shpgSvcInfoList);
        }

        if (shpgSvcInfoList.isEmpty()) {
            S21InfoLogOutput.println(NLBM1100E);
            String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
            createMailMsgInfo(key, NLBM1100E, null);
            shpgSvcInfoList = null;
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAvailableShpgSvcForRdd ] end", this);
        return shpgSvcInfoList;
    }

    /**
     * Get Max Priority of Designated Freight Charge and Service Level
     * @param shpgPlnInfo ShpgPlnInfo
     * @return SHIP_PRTY
     */
    private BigDecimal getMaxShipPrty(ShpgPlnInfo shpgPlnInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getMaxShipPrty ] start", this);

        // Create key of cache map as search condition
        String frtChrgToCd = shpgPlnInfo.getReqFrtChrgToCd();
        String frtChrgMethCd = shpgPlnInfo.getReqFrtChrgMethCd();
        String shpgSvcLvlCd = shpgPlnInfo.getOrigShpgSvcLvlCd();
        String searchCondition = this.glblCmpyCd + frtChrgToCd + frtChrgMethCd + shpgSvcLvlCd;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<BigDecimal> shipPrtyList = this.shipPrtyMap.get(searchCondition);

        if (shipPrtyList == null) {

            // If not stored in the map, search on the condition
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("frtChrgToCd", frtChrgToCd);
            queryParam.put("frtChrgMethCd", frtChrgMethCd);
            queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
            shipPrtyList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getMaxShipPrty", queryParam);

            // Store the condition and the result in the map
            this.shipPrtyMap.put(searchCondition, shipPrtyList);
        }

        BigDecimal shipPrty = shipPrtyList.get(0);

        if (!ZYPCommonFunc.hasValue(shipPrty)) {
            S21InfoLogOutput.println(NLBM1100E);
            String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
            createMailMsgInfo(key, NLBM1100E, null);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getMaxShipPrty ] end", this);
        return shipPrty;
    }

    /**
     * Get Available Shipping Service For RSD
     * @param shpgPlnInfo ShpgPlnInfo
     * @param shpgModeCdList List of SHPG_MODE_CD
     * @return List of ShpgSvcInfo
     */
    private List<ShpgSvcInfo> getAvailableShpgSvcForRsd(ShpgPlnInfo shpgPlnInfo, List<String> shpgModeCdList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAvailableShpgSvcForRsd ] start", this);

        // Create key of cache map as search condition
        String frtChrgToCd = shpgPlnInfo.getReqFrtChrgToCd();
        String frtChrgMethCd = shpgPlnInfo.getReqFrtChrgMethCd();
        String shpgSvcLvlCd = shpgPlnInfo.getOrigShpgSvcLvlCd();
        String searchCondition = this.glblCmpyCd + frtChrgToCd + frtChrgMethCd + shpgModeCdList + shpgSvcLvlCd;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<ShpgSvcInfo> shpgSvcInfoList = this.shpgSvcForRsdMap.get(searchCondition);

        if (shpgSvcInfoList == null) {

            // If not stored in the map, search on the condition
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("frtChrgToCd", frtChrgToCd);
            queryParam.put("frtChrgMethCd", frtChrgMethCd);
            queryParam.put("shpgModeCdList", shpgModeCdList);
            queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
            queryParam.put("rsdFlg", this.glblCmpyCd);
            shpgSvcInfoList = (List<ShpgSvcInfo>) this.ssmBatchClient.queryObjectList("getAvailableShpgSvc", queryParam);

            if (shpgSvcInfoList.isEmpty()) {

                queryParam.put("shpgModeCdList", null);
                shpgSvcInfoList = (List<ShpgSvcInfo>) this.ssmBatchClient.queryObjectList("getAvailableShpgSvc", queryParam);
            }

            // Store the condition and the result in the map
            this.shpgSvcForRsdMap.put(searchCondition, shpgSvcInfoList);
        }

        if (shpgSvcInfoList.isEmpty()) {
            shpgSvcInfoList = null;
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAvailableShpgSvcForRsd ] end", this);
        return shpgSvcInfoList;
    }

    /**
     * Calculate Delivery Date For RDD
     * @param calcInfo DeliveryDateCalcInfo
     * @return Error or Not
     */
    private boolean calculateDeliveryDateForRdd(DeliveryDateCalcInfo calcInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateDeliveryDateForRdd ] start", this);

        // Get parameters
        List<ShpgSvcInfo> shpgSvcInfoList = calcInfo.getShpgSvcInfoList();
        String rddDt = calcInfo.getRddDt();
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> calcInfo:" + calcInfo, this);

        // Get W/H calender code
        String whCalCd = null;
        CAL_RELNTMsg calReln = getCalReln(CAL_SUB_TP.WAREHOUSE_CALENDAR, calcInfo.getWhCd());
        if (calReln == null) {
            // If WH Calendar doesn't set up ,use CUSA Calendar.
            whCalCd = this.glblCmpyCd;
        } else {
            whCalCd = calReln.calTpCd.getValue();
        }

        boolean rddOkFlg = false;
        String shpgModeCd = null;
        String shpgSvcLvlCd = null;
        String pddDt = null;
        String psdDt = null;
        String sddDt = null;
        String msd = null;
        String carrCalCd = null;
        BigDecimal transportationLt = null;
        BigDecimal configLtDayNum = calcInfo.getConfigLtDayNum();

        // Process in Ship Priority order (low cost order)
        for (int i = 0; i < shpgSvcInfoList.size(); i++) {

            ShpgSvcInfo shpgSvcInfo = shpgSvcInfoList.get(i);
            shpgModeCd = shpgSvcInfo.getShpgModeCd();
            shpgSvcLvlCd = shpgSvcInfo.getShpgSvcLvlCd();

            // Get Carrier calender code
            calReln = getCalReln(CAL_SUB_TP.CARRIER_CALENDAR, shpgModeCd);
            if (calReln == null) {
                // If Carrier Calendar doesn't set up ,use CUSA
                // Calendar.
                carrCalCd = this.glblCmpyCd;
            } else {
                carrCalCd = calReln.calTpCd.getValue();
            }

            // Get W/H Lead Time
            String shpgCloTmTs;
            BigDecimal pickPackAot;
            if (!LOC_TP.TECHNICIAN.equals(calcInfo.getInvtyLocTpCd())) {
                WH_LEAD_TMTMsg whLeadTm = getWhLeadTm(calcInfo, shpgModeCd, shpgSvcLvlCd);
                if (whLeadTm == null) {
                    return true;
                }
                shpgCloTmTs = whLeadTm.shpgCloTmTs.getValue();
                pickPackAot = whLeadTm.pickPackAot.getValue();
            } else {
                shpgCloTmTs = "2359";
                pickPackAot = BigDecimal.ZERO;
            }
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgCloTmTs:" + shpgCloTmTs, this);
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> W/H Lead Time:" + pickPackAot, this);

            // Calculate MSD:Minimum(Shortest) shipping date.

            if (ZYPConstant.FLG_ON_Y.equals(calcInfo.getConfigItemFlg())) {
                msd = calculateAddConfigLTMsd(whCalCd, shpgCloTmTs, pickPackAot, configLtDayNum);
            } else {
                msd = calculateMsd(whCalCd, shpgCloTmTs, pickPackAot);
            }
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> MSD:" + msd, this);

            // Get Transportation Lead Time
            if (!LOC_TP.TECHNICIAN.equals(calcInfo.getInvtyLocTpCd())) {
                transportationLt = getTransportationLT(calcInfo, shpgModeCd, shpgSvcLvlCd);
                if (!ZYPCommonFunc.hasValue(transportationLt)) {
                    return true;
                }
            } else {
                transportationLt = BigDecimal.ZERO;
            }
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> transportationLt:" + transportationLt, this);

            // << Calculate delivery date based on RDD >>

            // RDD is Carrier business day
            if (isBusinessDay(carrCalCd, rddDt)) {

                // PDD: RDD
                pddDt = rddDt;

            } else {

                // PDD: RDD - 1 (Carrier business day)
                pddDt = addBusinessDay(carrCalCd, rddDt, -1);
            }

            // PSD: PDD - Transportation Lead Time (Carrier business
            // day)
            psdDt = addBusinessDay(carrCalCd, pddDt, -hourToDay(transportationLt).intValue());

            // PSD is not W/H business day
            if (!isBusinessDay(whCalCd, psdDt)) {

                // PSD: PSD - 1 (W/H business day)
                psdDt = addBusinessDay(whCalCd, psdDt, -1);
            }

            // SDD: PSD - W/H Lead Time (W/H business day)
            if (ZYPConstant.FLG_ON_Y.equals(calcInfo.getConfigItemFlg())) {
                sddDt = addBusinessDay(whCalCd, psdDt, -hourToDay(pickPackAot).intValue() - configLtDayNum.intValue());
            } else {
                sddDt = addBusinessDay(whCalCd, psdDt, -hourToDay(pickPackAot).intValue());
            }

            // MSD <= PSD, delivery date is OK
            if (ZYPDateUtil.compare(msd, psdDt) <= 0) {
                EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Can deliver in time!", this);
                rddOkFlg = true;
                break;
            }
        }

        if (rddOkFlg) {

            // << Recalculate delivery date based on calculated PSD >>

            // PSD is Carrier business day
            if (isBusinessDay(carrCalCd, psdDt)) {

                // PDD: PSD + Transportation Lead Time (Carrier
                // business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue());

            } else {

                // PDD: PSD + Transportation Lead Time (Carrier
                // business day) + 1 (Carrier business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue() + 1);
            }

        } else {

            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Cannot deliver in time..", this);

            // << Calculate delivery date based on MSD >>

            // SDD: System Date
            sddDt = this.sysDate;

            // PSD: MSD
            psdDt = msd;

            // PSD is Carrier business day
            if (isBusinessDay(carrCalCd, psdDt)) {

                // PDD: PSD + Transportation Lead Time (Carrier
                // business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue());

            } else {

                // PDD: PSD + Transportation Lead Time (Carrier
                // business day) + 1 (Carrier business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue() + 1);
            }
        }

        // Set parameters
        calcInfo.setDeliveryDateOkFlg(rddOkFlg);
        calcInfo.setShpgModeCd(shpgModeCd);
        calcInfo.setShpgSvcLvlCd(shpgSvcLvlCd);
        calcInfo.setSddDt(sddDt);
        calcInfo.setPsdDt(psdDt);
        calcInfo.setPddDt(pddDt);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateDeliveryDateForRdd ] end", this);
        return false;
    }

    /**
     * Calculate Delivery Date For RSD
     * @param calcInfo DeliveryDateCalcInfo
     * @return Error or Not
     */
    private boolean calculateDeliveryDateForRsd(DeliveryDateCalcInfo calcInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateDeliveryDateForRsd ] start", this);

        // Get parameters
        List<ShpgSvcInfo> shpgSvcInfoList = calcInfo.getShpgSvcInfoList();
        String rsdDt = calcInfo.getRsdDt();
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> calcInfo:" + calcInfo, this);

        // Get W/H calender code
        String whCalCd = null;
        CAL_RELNTMsg calReln = getCalReln(CAL_SUB_TP.WAREHOUSE_CALENDAR, calcInfo.getWhCd());
        if (calReln == null) {
            whCalCd = this.glblCmpyCd;
        } else {
            whCalCd = calReln.calTpCd.getValue();
        }

        boolean rsdOkFlg = false;
        String pddDt = null;
        String psdDt = null;
        String sddDt = null;
        String carrCalCd = null;
        BigDecimal configLtDayNum = calcInfo.getConfigLtDayNum();

        // Process minimum Ship Priority data only
        ShpgSvcInfo shpgSvcInfo = shpgSvcInfoList.get(0);
        String shpgModeCd = shpgSvcInfo.getShpgModeCd();
        String shpgSvcLvlCd = shpgSvcInfo.getShpgSvcLvlCd();

        // Get Carrier calender code
        calReln = getCalReln(CAL_SUB_TP.CARRIER_CALENDAR, shpgModeCd);
        if (calReln == null) {
            carrCalCd = this.glblCmpyCd;
        } else {
            carrCalCd = calReln.calTpCd.getValue();
        }

        // Get W/H Lead Time
        String shpgCloTmTs;
        BigDecimal pickPackAot;
        if (!LOC_TP.TECHNICIAN.equals(calcInfo.getInvtyLocTpCd())) {
            WH_LEAD_TMTMsg whLeadTm = getWhLeadTm(calcInfo, shpgModeCd, shpgSvcLvlCd);
            if (whLeadTm == null) {
                return true;
            }
            shpgCloTmTs = whLeadTm.shpgCloTmTs.getValue();
            pickPackAot = whLeadTm.pickPackAot.getValue();
        } else {
            shpgCloTmTs = "2359";
            pickPackAot = BigDecimal.ZERO;
        }
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgCloTmTs:" + shpgCloTmTs, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> W/H Lead Time:" + pickPackAot, this);

        // Calculate MSD
        String msd = null;
        if (ZYPConstant.FLG_ON_Y.equals(calcInfo.getConfigItemFlg())) {
            msd = calculateAddConfigLTMsd(whCalCd, shpgCloTmTs, pickPackAot, configLtDayNum);
        } else {
            msd = calculateMsd(whCalCd, shpgCloTmTs, pickPackAot);
        }
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> MSD:" + msd, this);

        // Get Transportation Lead Time
        BigDecimal transportationLt = null;
        if (!LOC_TP.TECHNICIAN.equals(calcInfo.getInvtyLocTpCd())) {
            transportationLt = getTransportationLT(calcInfo, shpgModeCd, shpgSvcLvlCd);
            if (!ZYPCommonFunc.hasValue(transportationLt)) {
                return true;
            }
        } else {
            transportationLt = BigDecimal.ZERO;
        }
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> transportationLt:" + transportationLt, this);

        // MSD <= RSD, delivery date is OK
        if (ZYPDateUtil.compare(msd, rsdDt) <= 0) {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Can ship in time!", this);

            rsdOkFlg = true;

            // << Calculate delivery date based on RSD >>
            // RSD is W/H business day
            if (isBusinessDay(whCalCd, rsdDt)) {
                // PSD: RSD
                psdDt = rsdDt;
            } else {
                // PSD: The last W/H business day before RSD
                psdDt = addBusinessDay(whCalCd, rsdDt, -1);
            }

            // SDD: PSD - W/H Lead Time (W/H business day)
            if (ZYPConstant.FLG_ON_Y.equals(calcInfo.getConfigItemFlg())) {
                sddDt = addBusinessDay(whCalCd, psdDt, -hourToDay(pickPackAot).intValue() - configLtDayNum.intValue());
            } else {
                sddDt = addBusinessDay(whCalCd, psdDt, -hourToDay(pickPackAot).intValue());
            }

            // PSD is Carrier business day
            if (isBusinessDay(carrCalCd, psdDt)) {
                // PDD: PSD + Transportation Lead Time (Carrier
                // business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue());
            } else {
                // PDD: PSD + Transportation Lead Time (Carrier
                // business day) + 1 (Carrier business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue() + 1);
            }
        } else {
            EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Cannot ship in time..", this);

            // << Calculate delivery date based on MSD >>

            // SDD: System Date
            sddDt = this.sysDate;

            // PSD: MSD
            psdDt = msd;

            // PSD is Carrier business day
            if (isBusinessDay(carrCalCd, psdDt)) {
                // PDD: PSD + Transportation Lead Time (Carrier
                // business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue());
            } else {
                // PDD: PSD + Transportation Lead Time (Carrier
                // business day) + 1 (Carrier business day)
                pddDt = addBusinessDay(carrCalCd, psdDt, hourToDay(transportationLt).intValue() + 1);
            }
        }

        // Set parameters
        calcInfo.setDeliveryDateOkFlg(rsdOkFlg);
        calcInfo.setShpgModeCd(shpgModeCd);
        calcInfo.setShpgSvcLvlCd(shpgSvcLvlCd);
        calcInfo.setSddDt(sddDt);
        calcInfo.setPsdDt(psdDt);
        calcInfo.setPddDt(pddDt);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateDeliveryDateForRsd ] end", this);
        return false;
    }

    /**
     * Calculate MSD:Minimum(Shortest) shipping date.
     * @param whCalTpCd CAL_TP_CD of W/H
     * @param shpgCloTmTs SHPG_CLO_TM_TS
     * @param pickPackAot PICK_PACK_AOT
     * @return MSD
     */
    private String calculateMsd(String whCalCd, String shpgCloTmTs, BigDecimal pickPackAot) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateMsd ] start", this);

        String msd;

        // Today is W/H business day
        if (isBusinessDay(whCalCd, this.sysDate)) {

            // System time < W/H Close time
            if (this.sysTime.substring(8, 12).compareTo(shpgCloTmTs) < 0) {
                // If SO is dropped now, Pick up and Pack can be done
                // today
                // System date + W/H Lead Time (W/H business day)
                msd = addBusinessDay(whCalCd, this.sysDate, hourToDay(pickPackAot).intValue());
            } else {
                // System date + W/H Lead Time (W/H business day) + 1
                // (W/H business day)
                msd = addBusinessDay(whCalCd, this.sysDate, hourToDay(pickPackAot).intValue() + 1);
            }
        } else {
            // The first W/H business day after today + W/H Lead Time
            // (W/H business day)
            msd = addBusinessDay(whCalCd, this.sysDate, 1 + hourToDay(pickPackAot).intValue());
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateMsd ] end", this);
        return msd;
    }

    /**
     * Calculate MSD:Minimum(Shortest) shipping date.
     * @param whCalTpCd CAL_TP_CD of W/H
     * @param shpgCloTmTs SHPG_CLO_TM_TS
     * @param pickPackAot PICK_PACK_AOT
     * @return MSD
     */
    private String calculateAddConfigLTMsd(String whCalCd, String shpgCloTmTs, BigDecimal pickPackAot, BigDecimal configLtDayNum) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateMsd ] start", this);

        String msd;

        // Today is W/H business day
        if (isBusinessDay(whCalCd, this.sysDate)) {

            // System time < W/H Close time
            if (this.sysTime.substring(8, 12).compareTo(shpgCloTmTs) < 0) {
                // If SO is dropped now, Pick up and Pack can be done
                // today
                // System date + W/H Lead Time (W/H business day) +
                // Config-LT-Day
                msd = addBusinessDay(whCalCd, this.sysDate, hourToDay(pickPackAot).intValue() + configLtDayNum.intValue());
            } else {
                // System date + W/H Lead Time (W/H business day) + +
                // Config-LT-Day + 1
                msd = addBusinessDay(whCalCd, this.sysDate, hourToDay(pickPackAot).intValue() + configLtDayNum.intValue() + 1);
            }
        } else {
            // The first W/H business day after today + W/H Lead Time
            // (W/H business day) + Config-LT-Day
            msd = addBusinessDay(whCalCd, this.sysDate, 1 + hourToDay(pickPackAot).intValue() + configLtDayNum.intValue());
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ calculateMsd ] end", this);
        return msd;
    }

    /**
     * Get Transportation LT
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgModeCd SHPG_MODE_CD
     * @param shpgSvcLvlCd SHPG_SVC_LVL_CD
     * @return transportationLt
     */
    private BigDecimal getTransportationLT(DeliveryDateCalcInfo calcInfo, String shpgModeCd, String shpgSvcLvlCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getTransportationLT ] start", this);

        BigDecimal transportationLt;

        // Get lead time of Guaranteed Days Delivery
        ShpgSvcInfo shpgSvcInfo = getShpgSvcLeadTm(calcInfo, shpgSvcLvlCd);
        if (shpgSvcInfo == null) {
            return null;
        }

        if (SHPG_SVC_TP.GUARANTEED_DAYS_DELIVERY.equals(shpgSvcInfo.getShpgSvcTpCd())) {

            transportationLt = shpgSvcInfo.getDelyLeadAot();

            // Get lead time of Ground Standard Delivery / Pick Up
        } else {
            TRNSP_LTTMsg trnspLt = getTrnspLt(calcInfo, shpgModeCd);
            if (trnspLt == null) {

                AREA_LEAD_TMTMsg areaLeadTm = getAreaLeadTm(calcInfo, shpgModeCd);
                if (areaLeadTm == null) {
                    return null;
                }

                transportationLt = areaLeadTm.delyLeadAot.getValue();

            } else {
                transportationLt = trnspLt.trnspLtAot.getValue();
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getTransportationLT ] end", this);
        return transportationLt;
    }

    /**
     * Sort prospective group list
     * @param shpgPlnInfoList List of ShpgPlnInfo
     * @return List of ShpgPlnInfo (after sorted)
     */
    private List<ShpgPlnInfo> sortProspectiveGroupList(List<ShpgPlnInfo> shpgPlnInfoList) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ sortProspectiveGroupList ] start", this);

        // Sort list by grouping items
        Collections.sort(shpgPlnInfoList, new Comparator<ShpgPlnInfo>() {
            public int compare(ShpgPlnInfo shpgPlnInfo1, ShpgPlnInfo shpgPlnInfo2) {
                // SHPG_MODE_CD
                String shpgModeCd1 = shpgPlnInfo1.getShpgModeCd();
                String shpgModeCd2 = shpgPlnInfo2.getShpgModeCd();

                if (shpgModeCd1.compareTo(shpgModeCd2) < 0) {
                    return -1;
                } else if (shpgModeCd1.compareTo(shpgModeCd2) > 0) {
                    return 1;
                } else {
                    // SHPG_SVC_LVL_CD
                    String shpgSvcLvlCd1 = shpgPlnInfo1.getShpgSvcLvlCd();
                    String shpgSvcLvlCd2 = shpgPlnInfo2.getShpgSvcLvlCd();
                    if (shpgSvcLvlCd1.compareTo(shpgSvcLvlCd2) < 0) {
                        return -1;
                    } else if (shpgSvcLvlCd1.compareTo(shpgSvcLvlCd2) > 0) {
                        return 1;
                    } else {
                        // PDD_DT
                        String pddDt1 = shpgPlnInfo1.getPddDt();
                        String pddDt2 = shpgPlnInfo2.getPddDt();
                        if (pddDt1.compareTo(pddDt2) < 0) {
                            return -1;
                        } else if (pddDt1.compareTo(pddDt2) > 0) {
                            return 1;
                        } else {
                            // PSD_DT
                            String psdDt1 = shpgPlnInfo1.getPsdDt();
                            String psdDt2 = shpgPlnInfo2.getPsdDt();
                            if (psdDt1.compareTo(psdDt2) < 0) {
                                return -1;
                            } else if (psdDt1.compareTo(psdDt2) > 0) {
                                return 1;
                            } else {
                                // SDD_DT
                                String sddDt1 = shpgPlnInfo1.getSddDt();
                                String sddDt2 = shpgPlnInfo2.getSddDt();
                                if (sddDt1.compareTo(sddDt2) < 0) {
                                    return -1;
                                } else if (sddDt1.compareTo(sddDt2) > 0) {
                                    return 1;
                                } else {
                                    // TRX_HDR_NUM
                                    String trxHdrNum1 = shpgPlnInfo1.getTrxHdrNum();
                                    String trxHdrNum2 = shpgPlnInfo2.getTrxHdrNum();
                                    if (trxHdrNum1.compareTo(trxHdrNum2) < 0) {
                                        return -1;
                                    } else if (trxHdrNum1.compareTo(trxHdrNum2) > 0) {
                                        return 1;
                                    } else {
                                        // Each Shipping Plan Weight
                                        BigDecimal shpgPlnWt1 = shpgPlnInfo1.getShpgPlnWt();
                                        BigDecimal shpgPlnWt2 = shpgPlnInfo2.getShpgPlnWt();
                                        if (shpgPlnWt1.compareTo(shpgPlnWt2) < 0) {
                                            return -1;
                                        } else if (shpgPlnWt1.compareTo(shpgPlnWt2) > 0) {
                                            return 1;
                                        } else {
                                            return 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        EZDDebugOutput.println(1, PROGRAM_ID + "[ sortProspectiveGroupList ] end", this);
        return shpgPlnInfoList;
    }

    /**
     * Return if same Group or Not
     * @param shpgPlnInfo ShpgPlnInfo
     * @param shpgPlnInfoPrevious ShpgPlnInfo (previous record)
     * @return Same group or not
     */
    private boolean isSameGroup(ShpgPlnInfo shpgPlnInfo, ShpgPlnInfo shpgPlnInfoPrevious) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ isSameGroup ] start", this);

        boolean sameGroupFlg = false;

        // Check if there are no differences between current record
        // and the previous one
        if (shpgPlnInfo.getShpgModeCd().equals(shpgPlnInfoPrevious.getShpgModeCd()) //
                && shpgPlnInfo.getShpgSvcLvlCd().equals(shpgPlnInfoPrevious.getShpgSvcLvlCd()) //
                && shpgPlnInfo.getPddDt().equals(shpgPlnInfoPrevious.getPddDt()) //
                && shpgPlnInfo.getPsdDt().equals(shpgPlnInfoPrevious.getPsdDt()) //
                && shpgPlnInfo.getSddDt().equals(shpgPlnInfoPrevious.getSddDt())) {

            sameGroupFlg = true;
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ isSameGroup ] end", this);
        return sameGroupFlg;
    }

    /**
     * Do Group Process
     * @param groupList List of ShpgPlnInfo
     * @param groupWt Group Weight
     * @return Error or Not
     */
    private boolean doGroupProcess(List<ShpgPlnInfo> groupList, BigDecimal groupWt) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ doGroupProcess ] start", this);

        boolean errFlg = false;
        String shpgModeCd = null;
        String rteGrpNum = null;
        String rteNum = null;
        String rteLineNum = null;
        String trxHdrNumPrevious = null;
        ShpgPlnInfo shpgPlnInfoRteHdr = null;

        BigDecimal maxConfigLtDayNum = BigDecimal.ZERO;

        if (hasConfigItemInGroup(groupList)) {
            maxConfigLtDayNum = getMaxConfigLtDayNumInGroup(groupList);

            for (int i = 0; i < groupList.size(); i++) {
                // update all config-Info in group
                // RTE_GRP is made from one record in group.
                // Update-all-record make it possible to choice any
                // record in making RTE_GRP.
                groupList.get(i).setConfigItemFlg(ZYPConstant.FLG_ON_Y);
                groupList.get(i).setConfigLtDayNum(maxConfigLtDayNum);
            }
        }

        int lineIndex = 1;

        for (int i = 0; i < groupList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = groupList.get(i);

            // 1st record
            if (i == 0) {

                // Get minimum weight of the Shipping Mode
                SHPG_WTTMsg shpgWt = getShpgWt(shpgPlnInfo);
                if (shpgWt == null) {
                    String[] str = {"TRANS_MIN_WT", "SHPG_WT" };
                    S21InfoLogOutput.println(NLBM1002E, str);
                    String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
                    createMailMsgInfo(key, NLBM1002E, str);
                    return true;
                }
                BigDecimal transMinWt = shpgWt.transMinWt.getValue();

                // Group weight < minimum weight of the Shipping Mode
                if (groupWt.setScale(0, BigDecimal.ROUND_HALF_UP).compareTo(transMinWt) < 0) {

                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> Recalculation", this);

                    // Recalculate Shipping Mode
                    List<String> shpgModeCdList = getAvailableShpgMode(shpgPlnInfo, true, groupWt, false);
                    if (shpgModeCdList == null) {
                        return true;
                    }
                    shpgModeCd = shpgModeCdList.get(0);

                    // Recalculate delivery date
                    DeliveryDateCalcInfo calcInfo = new DeliveryDateCalcInfo();
                    calcInfo.setWhCd(shpgPlnInfo.getRtlWhCd());
                    calcInfo.setShipToCustCd(shpgPlnInfo.getShipToCustCd());
                    // Create list by the recalculated Shipping Mode
                    ShpgSvcInfo shpgSvcInfo = new ShpgSvcInfo();
                    shpgSvcInfo.setShpgModeCd(shpgModeCd);
                    shpgSvcInfo.setShpgSvcLvlCd(shpgPlnInfo.getShpgSvcLvlCd());
                    List<ShpgSvcInfo> shpgSvcLvlList = new ArrayList<ShpgSvcInfo>();
                    shpgSvcLvlList.add(shpgSvcInfo);
                    calcInfo.setShpgSvcInfoList(shpgSvcLvlList);
                    calcInfo.setRddDt(shpgPlnInfo.getRddDt());
                    calcInfo.setRsdDt(shpgPlnInfo.getRsdDt());
                    calcInfo.setShipToStCd(shpgPlnInfo.getShipToStCd());
                    calcInfo.setShipToPostCd(shpgPlnInfo.getShipToPostCd());
                    calcInfo.setTrxHdrNum(shpgPlnInfo.getTrxHdrNum());
                    calcInfo.setShpgPlnNum(shpgPlnInfo.getShpgPlnNum());
                    // START ADD 04/16/2013 R-WH003
                    calcInfo.setConfigItemFlg(shpgPlnInfo.getConfigItemFlg());
                    calcInfo.setConfigLtDayNum(shpgPlnInfo.getConfigLtDayNum());
                    // E N D ADD 04/16/2013 R-WH003
                    calcInfo.setInvtyLocTpCd(shpgPlnInfo.getInvtyLocTpCd()); // ADD
                    // 04/26/2013
                    // R-WH001

                    if (ZYPCommonFunc.hasValue(shpgPlnInfo.getRddDt())) {
                        errFlg = calculateDeliveryDateForRdd(calcInfo);
                    } else {
                        errFlg = calculateDeliveryDateForRsd(calcInfo);
                    }

                    if (errFlg) {
                        return true;
                    }
                    EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> calcInfo:" + calcInfo, this);

                    // Set calculation result
                    shpgPlnInfo.setShpgModeCd(calcInfo.getShpgModeCd());
                    shpgPlnInfo.setShpgSvcLvlCd(calcInfo.getShpgSvcLvlCd());
                    shpgPlnInfo.setSddDt(calcInfo.getSddDt());
                    shpgPlnInfo.setPsdDt(calcInfo.getPsdDt());
                    shpgPlnInfo.setPddDt(calcInfo.getPddDt());
                }

                // Get Routing Group Number, Routing Number
                rteGrpNum = RTE_GRP_NUM_PREFIX + ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.RTE_GRP_NUM_SQ, 8);
                rteNum = RTE_NUM_PREFIX + ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.RTE_NUM_SQ, 8);

                // Insert into RTE_GRP
                createRteGrp(shpgPlnInfo, rteGrpNum, false);

                // Store the record as Routing Header info
                shpgPlnInfoRteHdr = shpgPlnInfo;

                // 2nd record or over
            } else {

                if (ROUTING_SO_BY_ORDER.equals(shpgPlnInfo.getShpgOrdCratTpCd())) {

                    // Transaction Header Number is changed, get new
                    // Routing Number
                    if (!trxHdrNumPrevious.equals(shpgPlnInfo.getTrxHdrNum())) {

                        rteNum = RTE_NUM_PREFIX + ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.RTE_NUM_SQ, 8);
                    }
                }
            }

            List<ShipCpltComponentBean> componentList = shpgPlnInfo.getComponentList();
            if (componentList.isEmpty()) {

                rteLineNum = ZYPCommonFunc.leftPad(String.valueOf(lineIndex++), 3, "0");

                // Insert into RTE_GRP_DTL
                createRteGrpDtl(shpgPlnInfo, rteGrpNum, rteLineNum, rteNum, shpgPlnInfo.getShpgPlnNum());

                // Call Shipping Plan Update API
                callShippingPlanUpdateApi(shpgPlnInfoRteHdr, shpgPlnInfo.getShpgPlnNum());

            } else {

                for (ShipCpltComponentBean componetInfo : componentList) {

                    rteLineNum = ZYPCommonFunc.leftPad(String.valueOf(lineIndex++), 3, "0");

                    // Insert into RTE_GRP_DTL
                    createRteGrpDtl(shpgPlnInfo, rteGrpNum, rteLineNum, rteNum, componetInfo.getShpgPlnNum());

                    // Call Shipping Plan Update API
                    callShippingPlanUpdateApi(shpgPlnInfoRteHdr, componetInfo.getShpgPlnNum());
                }
            }

            // Store the Transaction Header Number
            trxHdrNumPrevious = shpgPlnInfo.getTrxHdrNum();
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ doGroupProcess ] end", this);
        return errFlg;
    }

    /**
     * Do Group Process for no routing mode
     * @param groupList List of ShpgPlnInfo
     * @param groupWt Group Weight
     * @return Error or Not
     */
    private boolean doGroupProcessForNoRouting(List<ShpgPlnInfo> groupList, BigDecimal groupWt) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ doGroupProcessForNoRouting ] start", this);

        String rteGrpNum = null;
        String rteNum = null;
        String rteLineNum = null;
        ShpgPlnInfo shpgPlnInfoRteHdr = null;

        BigDecimal maxConfigLtDayNum = BigDecimal.ZERO;

        if (hasConfigItemInGroup(groupList)) {
            maxConfigLtDayNum = getMaxConfigLtDayNumInGroup(groupList);

            for (int i = 0; i < groupList.size(); i++) {
                // update all config-Info in group
                // RTE_GRP is made from one record in group.
                // Update-all-record make it possible to choice any
                // record in making RTE_GRP.
                groupList.get(i).setConfigItemFlg(ZYPConstant.FLG_ON_Y);
                groupList.get(i).setConfigLtDayNum(maxConfigLtDayNum);
            }
        }

        int lineIndex = 1;

        for (int i = 0; i < groupList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = groupList.get(i);

            // 1st record
            if (i == 0) {

                // Get Routing Group Number, Routing Number
                rteGrpNum = RTE_GRP_NUM_PREFIX + ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.RTE_GRP_NUM_SQ, 8);
                rteNum = RTE_NUM_PREFIX + ZYPOracleSeqAccessor.getNumberString(ZYPOracleSeqAccessor.RTE_NUM_SQ, 8);

                shpgPlnInfo.setShpgSvcLvlCd(shpgPlnInfo.getOrigShpgSvcLvlCd());
                shpgPlnInfo.setSddDt(this.sysDate);

                if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfo.getExptFlg())) {

                    shpgPlnInfo.setShpgModeCd("");
                    shpgPlnInfo.setPsdDt(this.sysDate);

                } else {

                    List<String> shpgModeCdList = getAvailableShpgMode(shpgPlnInfo, true, groupWt, true);
                    if (shpgModeCdList == null) {
                        return true;
                    }
                    shpgPlnInfo.setShpgModeCd(shpgModeCdList.get(0));

                    List<ShpgSvcInfo> shpgSvcList = new ArrayList<ShpgSvcInfo>();
                    ShpgSvcInfo ssi = new ShpgSvcInfo();
                    ssi.setShpgModeCd(shpgModeCdList.get(0));
                    ssi.setShpgSvcLvlCd(shpgPlnInfo.getShpgSvcLvlCd());
                    shpgSvcList.add(ssi);

                    DeliveryDateCalcInfo calcInfo = new DeliveryDateCalcInfo();
                    calcInfo.setWhCd(shpgPlnInfo.getRtlWhCd());
                    calcInfo.setShipToCustCd(shpgPlnInfo.getShipToCustCd());
                    calcInfo.setShpgSvcInfoList(shpgSvcList);
                    calcInfo.setShipToStCd(shpgPlnInfo.getShipToStCd());
                    calcInfo.setShipToPostCd(shpgPlnInfo.getShipToPostCd());
                    calcInfo.setTrxHdrNum(shpgPlnInfo.getTrxHdrNum());
                    calcInfo.setShpgPlnNum(shpgPlnInfo.getShpgPlnNum());
                    calcInfo.setConfigItemFlg(shpgPlnInfo.getConfigItemFlg());
                    calcInfo.setConfigLtDayNum(shpgPlnInfo.getConfigLtDayNum());
                    calcInfo.setInvtyLocTpCd(shpgPlnInfo.getInvtyLocTpCd());

                    if (ZYPCommonFunc.hasValue(shpgPlnInfo.getRddDt())) {

                        calcInfo.setRddDt(shpgPlnInfo.getRddDt());

                        if (calculateDeliveryDateForRdd(calcInfo)) {
                            return true;
                        }

                    } else {

                        calcInfo.setRsdDt(shpgPlnInfo.getRsdDt());

                        if (calculateDeliveryDateForRsd(calcInfo)) {
                            return true;
                        }
                    }

                    // Set calculation result
                    shpgPlnInfo.setPsdDt(calcInfo.getPsdDt());
                    shpgPlnInfo.setPddDt(calcInfo.getPddDt());
                }

                // Insert into RTE_GRP
                createRteGrp(shpgPlnInfo, rteGrpNum, false);

                // Store the record as Routing Header info
                shpgPlnInfoRteHdr = shpgPlnInfo;
            }

            List<ShipCpltComponentBean> componentList = shpgPlnInfo.getComponentList();

            if (componentList.isEmpty()) {

                rteLineNum = ZYPCommonFunc.leftPad(String.valueOf(lineIndex++), 3, "0");

                // Insert into RTE_GRP_DTL
                createRteGrpDtl(shpgPlnInfo, rteGrpNum, rteLineNum, rteNum, shpgPlnInfo.getShpgPlnNum());

                // Call Shipping Plan Update API
                callShippingPlanUpdateApi(shpgPlnInfoRteHdr, shpgPlnInfo.getShpgPlnNum());

            } else {

                for (ShipCpltComponentBean componetInfo : componentList) {

                    rteLineNum = ZYPCommonFunc.leftPad(String.valueOf(lineIndex++), 3, "0");

                    // Insert into RTE_GRP_DTL
                    createRteGrpDtl(shpgPlnInfo, rteGrpNum, rteLineNum, rteNum, componetInfo.getShpgPlnNum());

                    // Call Shipping Plan Update API
                    callShippingPlanUpdateApi(shpgPlnInfoRteHdr, componetInfo.getShpgPlnNum());
                }
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ doGroupProcessForNoRouting ] end", this);

        return false;
    }

    /**
     * Create RTE_GRP
     * @param shpgPlnInfo ShpgPlnInfo
     * @param rteGrpNum RTE_GRP_NUM
     * @param lightModeFlg
     */
    private void createRteGrp(ShpgPlnInfo shpgPlnInfo, String rteGrpNum, boolean lightModeFlg) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createRteGrp ] start", this);

        RTE_GRPTMsg rteGrp = new RTE_GRPTMsg();
        rteGrp.glblCmpyCd.setValue(this.glblCmpyCd);
        rteGrp.rteGrpNum.setValue(rteGrpNum);
        rteGrp.frtChrgToCd.setValue(shpgPlnInfo.getReqFrtChrgToCd());
        rteGrp.frtChrgMethCd.setValue(shpgPlnInfo.getReqFrtChrgMethCd());
        rteGrp.shpgSvcLvlCd.setValue(shpgPlnInfo.getShpgSvcLvlCd());
        rteGrp.shpgModeCd.setValue(shpgPlnInfo.getShpgModeCd());
        // SDD is tomorrow or after
        if (ZYPDateUtil.compare(this.sysDate, shpgPlnInfo.getSddDt()) < 0) {
            rteGrp.rteStsCd.setValue(RTE_STS.ROUTED);
            // SDD is today
        } else {
            rteGrp.rteStsCd.setValue(RTE_STS.SO_CREATING);
        }
        if (!lightModeFlg) {
            rteGrp.pddDt.setValue(shpgPlnInfo.getPddDt());
        }
        rteGrp.psdDt.setValue(shpgPlnInfo.getPsdDt());
        rteGrp.sddDt.setValue(shpgPlnInfo.getSddDt());
        rteGrp.rteDt.setValue(this.sysDate);
        rteGrp.whCd.setValue(shpgPlnInfo.getRtlWhCd());
        EZDTBLAccessor.create(rteGrp);

        String ret = rteGrp.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ret)) {
            String[] str = {"RTE_GRP", "CREATE", ret };
            throw new S21AbendException(NLBM1019E, str);
        }
        this.commitCountGrp++;

        EZDDebugOutput.println(1, PROGRAM_ID + "[ createRteGrp ] end", this);
    }

    /**
     * Create RTE_GRP_DTL
     * @param shpgPlnInfo ShpgPlnInfo
     * @param rteGrpNum RTE_GRP_NUM
     * @param rteLineNum RTE_LINE_NUM
     * @param rteNum RTE_NUM
     * @param shpgPlnNum SHPG_PLN_NUM
     */
    private void createRteGrpDtl(ShpgPlnInfo shpgPlnInfo, String rteGrpNum, String rteLineNum, String rteNum, String shpgPlnNum) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createRteGrpDtl ] start", this);

        RTE_GRP_DTLTMsg rteGrpDtl = new RTE_GRP_DTLTMsg();
        rteGrpDtl.glblCmpyCd.setValue(this.glblCmpyCd);
        rteGrpDtl.rteGrpNum.setValue(rteGrpNum);
        rteGrpDtl.rteLineNum.setValue(rteLineNum);
        rteGrpDtl.rteNum.setValue(rteNum);
        rteGrpDtl.shpgPlnNum.setValue(shpgPlnNum);
        rteGrpDtl.trxHdrNum.setValue(shpgPlnInfo.getTrxHdrNum());
        String sceOrdTpCd = getSceOrdTpCd(shpgPlnInfo);
        rteGrpDtl.sceOrdTpCd.setValue(sceOrdTpCd);
        EZDTBLAccessor.create(rteGrpDtl);

        String ret = rteGrpDtl.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ret)) {
            String[] str = {"RTE_GRP_DTL", "CREATE", ret };
            throw new S21AbendException(NLBM1019E, str);
        }
        this.commitCountGrpDtl++;

        EZDDebugOutput.println(1, PROGRAM_ID + "[ createRteGrpDtl ] end", this);
    }

    /**
     * Call Shipping Plan Update API
     * @param shpgPlnInfo ShpgPlnInfo
     * @param shpgPlnNum SHPG_PLN_NUM
     */
    private void callShippingPlanUpdateApi(ShpgPlnInfo shpgPlnInfo, String shpgPlnNum) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ callShippingPlanUpdateApi ] start", this);

        boolean errFlg = false;
        String shpgModeCd = null;

        // SDD is tomorrow or after
        if (ZYPDateUtil.compare(this.sysDate, shpgPlnInfo.getSddDt()) < 0) {
            shpgModeCd = SHPG_MODE_HARDALLOCATED_ROUTED;
            // SDD is today
        } else {
            shpgModeCd = SHPG_MODE_PRINTED_SOCREATING;
        }

        // Set API parameter
        NWZC003001PMsg msg = new NWZC003001PMsg();
        msg.shpgModeCd.setValue(shpgModeCd);
        msg.glblCmpyCd.setValue(this.glblCmpyCd);
        msg.shpgPlnNum.setValue(shpgPlnNum);
        msg.shpgSvcLvlCd.setValue(shpgPlnInfo.getShpgSvcLvlCd());
        msg.psdDt.setValue(shpgPlnInfo.getPsdDt());
        msg.pddDt.setValue(shpgPlnInfo.getPddDt());

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgModeCd:" + shpgModeCd, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> glblCmpyCd:" + this.glblCmpyCd, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgPlnNum:" + shpgPlnNum, this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> shpgSvcLvlCd:" + shpgPlnInfo.getShpgSvcLvlCd(), this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> psdDt:" + shpgPlnInfo.getPsdDt(), this);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> pddDt:" + shpgPlnInfo.getPddDt(), this);

        List<NWZC003001PMsg> list = new ArrayList<NWZC003001PMsg>();
        list.add(msg);

        // Execute API
        NWZC003001 api = new NWZC003001();
        api.execute(list, ONBATCH_TYPE.BATCH);

        // Get Message list
        List<String> msgList = S21ApiUtil.getXxMsgIdList(msg);
        if (!msgList.isEmpty()) {
            for (String xxMsgId : msgList) {
                S21InfoLogOutput.println(xxMsgId);
                if (xxMsgId.endsWith(MSG_TYPE_ERROR)) {
                    errFlg = true;
                    break;
                }
            }
        }

        if (errFlg) {
            throw new S21AbendException(NLBM1064E);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ callShippingPlanUpdateApi ] end", this);
    }

    /**
     * Update Routing Status
     * @param whShipToInfo WhShipToInfo
     */
    private void updateRoutingStatus(WhShipToInfo whShipToInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ updateRoutingStatus ] start", this);

        // Search RTE_CTRL
        RTE_CTRLTMsg rteCtrl = new RTE_CTRLTMsg();
        rteCtrl.glblCmpyCd.setValue(this.glblCmpyCd);
        rteCtrl.whCd.setValue(whShipToInfo.getWhCd());
        rteCtrl.shipToCustCd.setValue(whShipToInfo.getShipToCustCd());
        rteCtrl.procCtrlNum.setValue(String.valueOf(this.procCtrlNum));
        rteCtrl = (RTE_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdate(rteCtrl);
        if (rteCtrl == null) {
            // Impossible
            throw new S21AbendException(NLBM1063E);
        }

        // Update routing status
        rteCtrl.rteStsCd.setValue(RTE_STS.ROUTED);
        EZDTBLAccessor.update(rteCtrl);

        String ret = rteCtrl.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ret)) {
            String[] str = {"RTE_CTRL", "UPDATE", ret };
            throw new S21AbendException(NLBM1019E, str);
        }
        this.commitCountCtrl++;

        EZDDebugOutput.println(1, PROGRAM_ID + "[ updateRoutingStatus ] end", this);
    }

    /**
     * Get SCE_ORD_TP_CD
     * @param shpgPlnInfo ShpgPlnInfo
     * @return SCE_ORD_TP_CD
     */
    private String getSceOrdTpCd(ShpgPlnInfo shpgPlnInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getSceOrdTpCd ] start", this);

        String sceOrdTpCd = "";
        String[] whTransferOrdTpList = ZYPCodeDataUtil.getVarCharConstValue(CPO_DS_ORD_TP_FOR_WH_TRNSF, glblCmpyCd).split(",");

        if (TRX_SRC_TP.WHOLE_SALES.equals(shpgPlnInfo.getTrxSrcTpCd())) {
            if (whTransferOrdTpList != null //
                    && Arrays.asList(whTransferOrdTpList).contains(shpgPlnInfo.getDsOrdTpCd())) {
                sceOrdTpCd = SCE_ORD_TP.DC_TRANSFER;
            } else {
                sceOrdTpCd = SCE_ORD_TP.DIRECT_SALES;
            }
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> sceOrdTpCd:" + sceOrdTpCd, this);
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getSceOrdTpCd ] end", this);
        return sceOrdTpCd;
    }

    // QC#17395 Start
    /**
     * Get WH_OWNR_CD
     * @param shpgPlnInfo ShpgPlnInfo
     * @return WH_OWNR_CD
     */
    private String[] getConstWhOwnrCd() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getWhOwnrCd ] start", this);

        String[] whOwnrCdList = ZYPCodeDataUtil.getVarCharConstValue(HAZMAT_WH_OWNR_CD, glblCmpyCd).split(",");

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getWhOwnrCd ] end", this);
        return whOwnrCdList;
    }

    // QC#17395 End

    // QC#63614 Start
    /**
     * Get DS_ORD_CATG_CD for Excepting SO Divide process
     * @return OrdCatgList
     */
    private String[] getConstOrdCatgCd() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getConstOrdCatgCd ] start", this);

        String[] ordCatgList = ZYPCodeDataUtil.getVarCharConstValue(ORD_CATG_FOR_EXCPT_SO_DIV, glblCmpyCd).split(",");

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getConstOrdCatgCd ] end", this);
        return ordCatgList;
    }
    // QC#63614 End

    /**
     * Get TRX_SRC_TP
     * @param trxSrcTpcd TRX_SRC_TP_CD
     * @return TRS_SRC_TPTMsg
     */
    private TRX_SRC_TPTMsg getTrxSrcTp(String trxSrcTpCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getTrxSrcTp ] start", this);

        // Create key of cache map as search condition
        String searchCondition = this.glblCmpyCd + trxSrcTpCd;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        TRX_SRC_TPTMsg trxSrcTp = this.trxSrcTpMap.get(searchCondition);

        if (trxSrcTp == null) {

            // If not stored in the map, search on the condition
            trxSrcTp = new TRX_SRC_TPTMsg();
            trxSrcTp.glblCmpyCd.setValue(this.glblCmpyCd);
            trxSrcTp.trxSrcTpCd.setValue(trxSrcTpCd);
            trxSrcTp = (TRX_SRC_TPTMsg) EZDTBLAccessor.findByKey(trxSrcTp);

            // Store the condition and the result in the map
            this.trxSrcTpMap.put(searchCondition, trxSrcTp);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getTrxSrcTp ] end", this);
        return trxSrcTp;
    }

    /**
     * Get MDSE_STORE_PKG
     * @param mdseCd MDSE_CD
     * @return MDSE_STORE_PKGTMsg
     */
    private MDSE_STORE_PKGTMsg getMdseStorePkg(String mdseCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getMdseStorePkg ] start", this);

        // Create key of cache map as search condition
        String searchCondition = this.glblCmpyCd + mdseCd + PKG_UOM.EACH;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        MDSE_STORE_PKGTMsg mdseStorePkg = this.mdseStorePkgMap.get(searchCondition);

        if (mdseStorePkg == null) {

            // If not stored in the map, search on the condition
            mdseStorePkg = new MDSE_STORE_PKGTMsg();
            mdseStorePkg.glblCmpyCd.setValue(this.glblCmpyCd);
            mdseStorePkg.mdseCd.setValue(mdseCd);
            mdseStorePkg.pkgUomCd.setValue(PKG_UOM.EACH);
            mdseStorePkg = (MDSE_STORE_PKGTMsg) EZDTBLAccessor.findByKey(mdseStorePkg);

            // Store the condition and the result in the map
            this.mdseStorePkgMap.put(searchCondition, mdseStorePkg);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getMdseStorePkg ] end", this);
        return mdseStorePkg;
    }

    /**
     * Get CPO
     * @param cpoOrdNum CPO_ORD_NUM
     * @return CPOTMsg
     */
    private CPOTMsg getCpo(String cpoOrdNum) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getCpo ] start", this);

        // CPO is transaction table, search every time
        CPOTMsg cpo = new CPOTMsg();
        cpo.glblCmpyCd.setValue(this.glblCmpyCd);
        cpo.cpoOrdNum.setValue(cpoOrdNum);
        cpo = (CPOTMsg) EZDTBLAccessor.findByKey(cpo);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getCpo ] end", this);
        return cpo;
    }

    /**
     * Get CAL_RELN
     * @param calSubTpCd CAL_SUB_TP_CD
     * @param calMultCd CAL_MULT_CD
     * @return CAL_RELNTMsg
     */
    private CAL_RELNTMsg getCalReln(String calSubTpCd, String calMultCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getCalReln ] start", this);

        // Create key of cache map as search condition
        String searchCondition = this.glblCmpyCd + calSubTpCd + calMultCd;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        CAL_RELNTMsg calReln = this.calRelnMap.get(searchCondition);

        if (calReln == null) {

            // If not stored in the map, search on the condition
            calReln = new CAL_RELNTMsg();
            calReln.glblCmpyCd.setValue(this.glblCmpyCd);
            calReln.calSubTpCd.setValue(calSubTpCd);
            calReln.calMultCd.setValue(calMultCd);
            calReln = (CAL_RELNTMsg) EZDTBLAccessor.findByKey(calReln);

            // Store the condition and the result in the map
            this.calRelnMap.put(searchCondition, calReln);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getCalReln ] end", this);
        return calReln;
    }

    /**
     * Get WH_LEAD_TM
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgModeCd SHPG_MODE_CD
     * @param shpgSvcLvlCd SHPG_SVC_LVL_CD
     * @return WH_LEAD_TMTMsg
     */
    private WH_LEAD_TMTMsg getWhLeadTm(DeliveryDateCalcInfo calcInfo, String shpgModeCd, String shpgSvcLvlCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getWhLeadTm ] start", this);

        // Create key of cache map as search condition
        String whCd = calcInfo.getWhCd();
        String searchCondition = this.glblCmpyCd + whCd + shpgModeCd + shpgSvcLvlCd + this.salesDate;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<WH_LEAD_TMTMsg> whLeadTmList = this.whLeadTmMap.get(searchCondition);

        if (whLeadTmList == null) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("whCd", whCd);
            queryParam.put("shpgModeCd", shpgModeCd);
            queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
            queryParam.put("effDt", this.salesDate);
            whLeadTmList = (List<WH_LEAD_TMTMsg>) this.ssmBatchClient.queryObjectList("getWhLeadTm", queryParam);

            // Store the condition and the result in the map
            this.whLeadTmMap.put(searchCondition, whLeadTmList);
        }

        WH_LEAD_TMTMsg whLeadTm = null;

        if (whLeadTmList.isEmpty()) {
            String[] str = {"PICK_PACK_AOT", "WH_LEAD_TM" };
            S21InfoLogOutput.println(NLBM1002E, str);
            String key = calcInfo.getTrxHdrNum() + " " + calcInfo.getShpgPlnNum();
            createMailMsgInfo(key, NLBM1002E, str);
            whLeadTm = null;
        } else {
            whLeadTm = whLeadTmList.get(0);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getWhLeadTm ] end", this);
        return whLeadTm;
    }

    /**
     * Get SHPG_SVC_LEAD_TM
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgSvcLvlCd SHPG_SVC_LVL_CD
     * @return ShpgSvcInfo
     */
    private ShpgSvcInfo getShpgSvcLeadTm(DeliveryDateCalcInfo calcInfo, String shpgSvcLvlCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgSvcLeadTm ] start", this);

        // Create key of cache map as search condition
        String searchCondition = this.glblCmpyCd + shpgSvcLvlCd;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<ShpgSvcInfo> shpgSvcInfoList = this.shpgSvcLeadTmMap.get(searchCondition);

        if (shpgSvcInfoList == null) {

            // If not stored in map, search on this condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
            shpgSvcInfoList = (List<ShpgSvcInfo>) this.ssmBatchClient.queryObjectList("getShpgSvcLeadTm", queryParam);

            // Store the condition and the result in the map
            this.shpgSvcLeadTmMap.put(searchCondition, shpgSvcInfoList);
        }

        ShpgSvcInfo shpgSvcInfo = null;

        if (shpgSvcInfoList.isEmpty()) {
            String[] str = {"DELY_LEAD_AOT", "SHPG_SVC_LEAD_TM" };
            S21InfoLogOutput.println(NLBM1002E, str);
            String key = calcInfo.getTrxHdrNum() + " " + calcInfo.getShpgPlnNum();
            createMailMsgInfo(key, NLBM1002E, str);
            shpgSvcInfo = null;
        } else {
            shpgSvcInfo = shpgSvcInfoList.get(0);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgSvcLeadTm ] end", this);
        return shpgSvcInfo;
    }

    /**
     * Get TRNSP_LT
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgModeCd SHPG_MODE_CD
     * @return TRNSP_LTTMsg
     */
    private TRNSP_LTTMsg getTrnspLt(DeliveryDateCalcInfo calcInfo, String shpgModeCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getTrnspLt ] start", this);

        // Create key of cache map as search condition
        String whCd = calcInfo.getWhCd();
        String shipToStCd = calcInfo.getShipToStCd();
        String shipToPostCd = calcInfo.getShipToPostCd();
        String searchCondition = this.glblCmpyCd + whCd + shpgModeCd + shipToStCd + shipToPostCd + this.salesDate;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<TRNSP_LTTMsg> trnspLtList = this.trnspLtMap.get(searchCondition);

        if (trnspLtList == null) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("whCd", whCd);
            queryParam.put("shpgModeCd", shpgModeCd);
            queryParam.put("shipToStCd", shipToStCd);
            queryParam.put("shipToPostCd", shipToPostCd);
            queryParam.put("effDt", this.salesDate);
            trnspLtList = (List<TRNSP_LTTMsg>) this.ssmBatchClient.queryObjectList("getTrnspLt", queryParam);

            // Store the condition and the result in the map
            this.trnspLtMap.put(searchCondition, trnspLtList);
        }

        TRNSP_LTTMsg trnspLt = null;

        if (trnspLtList.isEmpty()) {
            trnspLt = null;
        } else {
            trnspLt = trnspLtList.get(0);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getTrnspLt ] end", this);
        return trnspLt;
    }

    /**
     * Get AREA_LEAD_TM
     * @param calcInfo DeliveryDateCalcInfo
     * @param shpgModeCd SHPG_MODE_CD
     * @return AREA_LEAD_TMTMsg
     */
    private AREA_LEAD_TMTMsg getAreaLeadTm(DeliveryDateCalcInfo calcInfo, String shpgModeCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAreaLeadTm ] start", this);

        // Create key of cache map as search condition
        String whCd = calcInfo.getWhCd();
        String shipToStCd = calcInfo.getShipToStCd();
        String searchCondition = this.glblCmpyCd + whCd + shpgModeCd + shipToStCd + this.salesDate;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<AREA_LEAD_TMTMsg> areaLeadTmList = this.areaLeadTmMap.get(searchCondition);

        if (areaLeadTmList == null) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("whCd", whCd);
            queryParam.put("shpgModeCd", shpgModeCd);
            queryParam.put("shipToStCd", shipToStCd);
            queryParam.put("effDt", this.salesDate);
            areaLeadTmList = (List<AREA_LEAD_TMTMsg>) this.ssmBatchClient.queryObjectList("getAreaLeadTm", queryParam);

            // Store the condition and the result in the map
            this.areaLeadTmMap.put(searchCondition, areaLeadTmList);
        }

        AREA_LEAD_TMTMsg areaLeadTm = null;

        if (areaLeadTmList.isEmpty()) {
            String[] str = {"DELY_LEAD_AOT", "AREA_LEAD_TM" };
            S21InfoLogOutput.println(NLBM1002E, str);
            String key = calcInfo.getTrxHdrNum() + " " + calcInfo.getShpgPlnNum();
            createMailMsgInfo(key, NLBM1002E, str);
            areaLeadTm = null;
        } else {
            areaLeadTm = areaLeadTmList.get(0);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getAreaLeadTm ] end", this);
        return areaLeadTm;
    }

    /**
     * Get SHPG_WT
     * @param shpgPlnInfo ShpgPlnInfo
     * @param sellToCustCd SELL_TO_CUST_CD
     * @param shipToCustCd SHIP_TO_CUST_CD
     * @return SHPG_WTTMsg
     */
    private SHPG_WTTMsg getShpgWt(ShpgPlnInfo shpgPlnInfo, String sellToCustCd, String shipToCustCd) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgWt ] start", this);

        // Create key of cache map as search condition
        String frtChrgToCd = shpgPlnInfo.getReqFrtChrgToCd();
        String frtChrgMethCd = shpgPlnInfo.getReqFrtChrgMethCd();
        String shpgModeCd = shpgPlnInfo.getShpgModeCd();
        String searchCondition = this.glblCmpyCd + sellToCustCd + shipToCustCd + frtChrgToCd + frtChrgMethCd + shpgModeCd + this.salesDate;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<SHPG_WTTMsg> shpgWtList = this.shpgWtMap.get(searchCondition);

        if (shpgWtList == null) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("sellToCustCd", sellToCustCd);
            queryParam.put("shipToCustCd", shipToCustCd);
            queryParam.put("frtChrgToCd", frtChrgToCd);
            queryParam.put("frtChrgMethCd", frtChrgMethCd);
            queryParam.put("shpgModeCd", shpgModeCd);
            queryParam.put("effDt", this.salesDate);
            shpgWtList = (List<SHPG_WTTMsg>) this.ssmBatchClient.queryObjectList("getShpgWt", queryParam);

            // Store the condition and the result in the map
            this.shpgWtMap.put(searchCondition, shpgWtList);
        }

        SHPG_WTTMsg shpgWt = null;

        if (shpgWtList.isEmpty()) {
            shpgWt = null;
        } else {
            shpgWt = shpgWtList.get(0);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShpgWt ] end", this);
        return shpgWt;
    }

    /**
     * Return if Business Day or Not
     * @param calCd Calendar Code
     * @param date Target Date
     * @return Business Day or Not
     */
    private boolean isBusinessDay(String calCd, String date) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ isBusinessDay ] start", this);

        // Create key of cache map as parameter
        String parameter = calCd + date;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> parameter:" + parameter, this);
        Boolean businessDayFlg = this.isBusinessDayMap.get(parameter);

        if (businessDayFlg == null) {

            // If not stored in the map, call on the parameter
            businessDayFlg = ZYPDateUtil.isBusinessDay(calCd, date);

            // Store the parameter and the result in the map
            this.isBusinessDayMap.put(parameter, businessDayFlg);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ isBusinessDay ] end", this);
        return businessDayFlg;
    }

    /**
     * Add Business Day
     * @param calCd Calendar Code
     * @param date Target Date
     * @param amount Number of Days
     * @return Business Day
     */
    private String addBusinessDay(String calCd, String date, int amount) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ addBusinessDay ] start", this);

        // Create key of cache map as parameter
        String parameter = calCd + date + String.valueOf(amount);

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> parameter:" + parameter, this);
        String businessDay = this.addBusinessDayMap.get(parameter);

        if (businessDay == null) {

            // If not stored in the map, call on the parameter
            businessDay = ZYPDateUtil.addBusinessDay(calCd, date, amount);

            // Store the parameter and the result in the map
            this.addBusinessDayMap.put(parameter, businessDay);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ addBusinessDay ] end", this);
        return businessDay;
    }

    /**
     * Get SHIP_TO_CUST
     * @param shpgPlnInfo ShpgPlnInfo
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg getShipToCust(ShpgPlnInfo shpgPlnInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShipToCust ] start", this);

        // Create key of cache map as search condition
        String shipToCustCd = shpgPlnInfo.getShipToCustCd();
        String searchCondition = this.glblCmpyCd + shipToCustCd + this.salesDate;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<SHIP_TO_CUSTTMsg> shipToCustList = this.shipToCustMap.get(searchCondition);

        if (shipToCustList == null) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("shipToCustCd", shipToCustCd);
            queryParam.put("effDt", this.salesDate);
            shipToCustList = (List<SHIP_TO_CUSTTMsg>) this.ssmBatchClient.queryObjectList("getShipToCust", queryParam);

            // Store the condition and the result in the map
            this.shipToCustMap.put(searchCondition, shipToCustList);
        }

        SHIP_TO_CUSTTMsg shipToCust = null;

        boolean shipToCustNoTExistFlg = false;
        boolean shipToCustNotEffectiveFlg = false;
        String effFromDt = "";
        String effThruDt = "";
        String rgtnStsCd = "";

        if (shipToCustList.isEmpty()) {
            shipToCustNoTExistFlg = true;

        } else {
            effFromDt = shipToCustList.get(0).effFromDt.getValue();
            effThruDt = shipToCustList.get(0).effThruDt.getValue();

            if (ZYPDateUtil.compare(this.salesDate, effFromDt) == -1 || ZYPDateUtil.compare(this.salesDate, effThruDt) == 1) {
                shipToCustNotEffectiveFlg = true;
            }

            rgtnStsCd = shipToCustList.get(0).rgtnStsCd.getValue();

            if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                shipToCustNoTExistFlg = true;
            }
        }

        if (shipToCustNoTExistFlg) {
            S21InfoLogOutput.println(NLBM1330E, new String[] {shipToCustCd });
            editBizErrMsgMapForShipToExpiration(shpgPlnInfo, effFromDt, effThruDt, NLBM1330E);

        } else if (shipToCustNotEffectiveFlg) {
            S21InfoLogOutput.println(NLBM1331E, new String[] {shipToCustCd });
            editBizErrMsgMapForShipToExpiration(shpgPlnInfo, effFromDt, effThruDt, NLBM1331E);

        } else {
            shipToCust = shipToCustList.get(0);
        }
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getShipToCust ] end", this);
        return shipToCust;
    }

    /**
     * Get DS_INVTY_LOC_V
     * @param shpgPlnInfo ShpgPlnInfo
     * @return dsInvtyLocVTmsg
     */
    private DS_INVTY_LOC_VTMsg getDSInvtyLocV(ShpgPlnInfo shpgPlnInfo) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ getDSInvtyLocV ] start", this);

        // Create key of cache map as search condition
        String invtyLocCd = shpgPlnInfo.getShipToCustCd();
        String searchCondition = this.glblCmpyCd + invtyLocCd + this.salesDate;

        // Get result by the key from the map
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> searchCondition:" + searchCondition, this);
        List<DS_INVTY_LOC_VTMsg> dsInvtyLocVtList = this.dsInvtyLocVMap.get(searchCondition);

        if (dsInvtyLocVtList == null) {

            // If not stored in the map, search on the condition
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("invtyLocCd", invtyLocCd);
            queryParam.put("effDt", this.salesDate);
            dsInvtyLocVtList = (List<DS_INVTY_LOC_VTMsg>) this.ssmBatchClient.queryObjectList("getDSInvtyLocV", queryParam);

            // Store the condition and the result in the map
            this.dsInvtyLocVMap.put(searchCondition, dsInvtyLocVtList);
        }

        DS_INVTY_LOC_VTMsg dsInvtyLocVTmsg = null;

        if (dsInvtyLocVtList.isEmpty()) {
            String[] str = {"ST_CD", "WH or TECH_LOC" };
            S21InfoLogOutput.println(NLBM1002E, str);
            String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
            createMailMsgInfo(key, NLBM1002E, str);
            dsInvtyLocVTmsg = null;
        } else {
            dsInvtyLocVTmsg = dsInvtyLocVtList.get(0);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ getDSInvtyLocV ] end", this);
        return dsInvtyLocVTmsg;
    }

    /**
     * Create Mail Message Information
     * @param key Key
     * @param xxMsgId Message ID
     * @param str List of String
     */
    private void createMailMsgInfo(String key, String xxMsgId, String[] str) {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createMailMsgInfo ] start", this);

        String message = "";

        // Get message
        if (str == null) {
            message = S21MessageFunc.clspGetMessage(xxMsgId);
        } else {
            message = S21MessageFunc.clspGetMessage(xxMsgId, str);
        }

        // Create mail message information and store them until posted
        if (this.mailMsgInfo == null) {
            this.mailMsgInfo = new StringBuilder();
            this.mailMsgInfo.append(MAIL_MSG_HEADER);
            this.mailMsgInfo.append(LINE_FEED_CODE);
        }
        this.mailMsgInfo.append(key);
        this.mailMsgInfo.append("         ");
        this.mailMsgInfo.append(message);
        this.mailMsgInfo.append(LINE_FEED_CODE);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ createMailMsgInfo ] end", this);
    }

    /**
     * Call postMail
     */
    private void callPostMail() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ postMail ] start", this);

        // Create mail
        S21Mail mail = new S21Mail(glblCmpyCd);

        // Create group
        S21MailGroup group = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID);

        // Get from-address users of the group
        group.setMailKey1(MAIL_KEY_FROM);
        List<S21MailAddress> fromAddrList = group.getMailAddress();
        if (!fromAddrList.isEmpty()) {
            // Set from-address
            mail.setFromAddress(fromAddrList.get(0));
        }

        // Get to-address users of the group
        group.setMailKey1(MAIL_KEY_TO);
        List<S21MailAddress> toAddrList = group.getMailAddress();
        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NLBM1065E);
        }

        // Set to-address
        mail.setToAddressList(toAddrList);

        // Get current system time
        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL);
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> currentTime:" + currentTime, this);

        // Create template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

        // Set template parameter
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BUSINESS_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, this.mailMsgInfo.toString());

        // Set template
        mail.setMailTemplate(template);

        // Post mail
        mail.postMail();

        // Initialize
        mailMsgInfo = null;

        EZDDebugOutput.println(1, PROGRAM_ID + "[ postMail ] end", this);
    }

    /**
     * Convert Null to Empty
     * @param str String
     * @return String (If String is null, convert it to "")
     */
    private String nullToEmpty(String str) {

        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * Convert Hour Value to Day Value
     * @param hourValue Hour Value
     * @return Day Value (If not divided, round up)
     */
    private BigDecimal hourToDay(BigDecimal hourValue) {

        BigDecimal dayValue = hourValue.divide(TWENTY_FOUR_HOURS, 0, BigDecimal.ROUND_UP);
        return dayValue;
    }

    /**
     * editShpgPlnList
     * @param shpgPlnList
     * @since 04/28/2010
     */
    private void editShpgPlnList(List<ShpgPlnInfo> shpgPlnList) {

        String cpltkey = "";
        String compareCpltkey = "";
        List<ShipCpltComponentBean> componentList = new ArrayList<ShipCpltComponentBean>();

        for (int i = 0; i < shpgPlnList.size(); i++) {

            ShpgPlnInfo shpgPlnInfo = (ShpgPlnInfo) shpgPlnList.get(i);
            cpltkey = shpgPlnInfo.getTrxHdrNum() + shpgPlnInfo.getTrxLineNum();
            if (compareCpltkey.equals(cpltkey)) {

                ShipCpltComponentBean componetInfo = new ShipCpltComponentBean();
                this.setComponetBeanFromShpgPlnInfo(componetInfo, shpgPlnInfo);
                componentList.add(componetInfo);

                shpgPlnList.remove(i);
                i--;
            } else {
                if (componentList.size() < 2) {
                    componentList.clear();
                }
                componentList = shpgPlnInfo.getComponentList();

                ShipCpltComponentBean componetInfo = new ShipCpltComponentBean();
                this.setComponetBeanFromShpgPlnInfo(componetInfo, shpgPlnInfo);
                componentList.add(componetInfo);
            }
            compareCpltkey = cpltkey;
        }
    }

    /**
     * @param componetInfo
     * @param shpgPlnInfo
     * @since 04/28/2010
     */
    private void setComponetBeanFromShpgPlnInfo(ShipCpltComponentBean componetInfo, ShpgPlnInfo shpgPlnInfo) {

        componetInfo.setAvalSoQty(shpgPlnInfo.getAvalSoQty());
        componetInfo.setMdseCd(shpgPlnInfo.getMdseCd());
        componetInfo.setShpgPlnNum(shpgPlnInfo.getShpgPlnNum());
        componetInfo.setTrxLineNum(shpgPlnInfo.getTrxLineNum());
    }

    /**
     * @param shpgPlnInfo
     * @return errFlg
     * @since 04/28/2010
     */
    private boolean setInPoundWeight(ShpgPlnInfo shpgPlnInfo) {

        List<ShipCpltComponentBean> componentList = shpgPlnInfo.getComponentList();

        if (componentList.isEmpty()) {

            // Get In pound weight
            MDSE_STORE_PKGTMsg mdseStorePkg = getMdseStorePkg(shpgPlnInfo.getMdseCd());

            if (mdseStorePkg == null) {
                String[] str = {"MDSE_CD", "MDSE_STORE_PKG" };
                S21InfoLogOutput.println(NLBM1002E, str);
                String key = shpgPlnInfo.getTrxHdrNum() + " " + shpgPlnInfo.getShpgPlnNum();
                createMailMsgInfo(key, NLBM1002E, str);
                return true;
            }

            shpgPlnInfo.setInPoundWt(mdseStorePkg.inPoundWt.getValue());
        } else {

            for (ShipCpltComponentBean componetInfo : componentList) {

                MDSE_STORE_PKGTMsg mdseStorePkg = getMdseStorePkg(componetInfo.getMdseCd());

                if (mdseStorePkg == null) {
                    String[] str = {"MDSE_CD", "MDSE_STORE_PKG" };
                    S21InfoLogOutput.println(NLBM1002E, str);
                    String key = shpgPlnInfo.getTrxHdrNum() + " " + componetInfo.getShpgPlnNum();
                    createMailMsgInfo(key, NLBM1002E, str);
                    return true;
                }

                componetInfo.setInPoundWt(mdseStorePkg.inPoundWt.getValue());
            }
        }
        return false;
    }

    /**
     * @param shpgPlnInfo
     * @return shpgPlnWt
     * @since 04/28/2010
     */
    private BigDecimal getShpgPlnWt(ShpgPlnInfo shpgPlnInfo) {

        BigDecimal ret = BigDecimal.ZERO;
        List<ShipCpltComponentBean> componentList = shpgPlnInfo.getComponentList();

        if (componentList.isEmpty()) {

            ret = shpgPlnInfo.getInPoundWt().multiply(shpgPlnInfo.getAvalSoQty());

        } else {
            BigDecimal tmp = BigDecimal.ZERO;
            for (ShipCpltComponentBean componetInfo : componentList) {
                tmp = componetInfo.getInPoundWt().multiply(componetInfo.getAvalSoQty());
                ret = ret.add(tmp);
            }
        }
        return ret;
    }

    /**
     * Routing Group has Config-Item or not.
     * @param groupList
     * @return has config Item(true) or not(false)
     */
    private boolean hasConfigItemInGroup(List<ShpgPlnInfo> groupList) {

        for (ShpgPlnInfo shpgPlnInfo : groupList) {
            if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfo.getConfigItemFlg())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Routing Group's Max ConfigLtDayNum(all zero or blank -> default
     * ConfigLt).
     * @param groupList
     * @return Routing Group's Max ConfigLtNum(all 0 -> default
     * ConfigLt)
     */
    private BigDecimal getMaxConfigLtDayNumInGroup(List<ShpgPlnInfo> groupList) {

        BigDecimal maxConfigLtDayNum = BigDecimal.ZERO;

        for (ShpgPlnInfo shpgPlnInfo : groupList) {
            if (ZYPConstant.FLG_ON_Y.equals(shpgPlnInfo.getConfigItemFlg())) {
                if (ZYPCommonFunc.hasValue(shpgPlnInfo.getConfigLtDayNum()) && maxConfigLtDayNum.compareTo(shpgPlnInfo.getConfigLtDayNum()) < 0) {
                    maxConfigLtDayNum = shpgPlnInfo.getConfigLtDayNum();
                }
            }
        }

        return maxConfigLtDayNum;
    }

    /**
     * Edit business error message map
     * @param shpgPlnInfo ShpgPlnInfo
     * @param effFromDt(yyyyMMdd) String
     * @param effThruDt(yyyyMMdd) String
     * @param errMsgCd String
     */
    private void editBizErrMsgMapForShipToExpiration(ShpgPlnInfo shpgPlnInfo, String effFromDt, String effThruDt, String errMsgCd) {
        String bizErrMsgOld = null;
        String bizErrMsgAdd = null;
        String bizErrMsgNew = null;
        String convertedEffFromDt = "";
        String convertedEffThruDt = "";

        if (ZYPCommonFunc.hasValue(effFromDt)) {
            convertedEffFromDt = ZYPDateUtil.DateFormatter(effFromDt, DT_FORMAT_YYYY_MM_DD, DT_FORMAT_MM_DD_YYYY);
        }

        if (ZYPCommonFunc.hasValue(effThruDt)) {
            convertedEffThruDt = ZYPDateUtil.DateFormatter(effThruDt, DT_FORMAT_YYYY_MM_DD, DT_FORMAT_MM_DD_YYYY);
        }

        bizErrMsgAdd = String.format(BIZ_ERR_MSG_FMT_SHIP_TO_EXPIRATION, shpgPlnInfo.getTrxHdrNum(), shpgPlnInfo.getTrxLineNum(), shpgPlnInfo.getTrxLineSubNum(), shpgPlnInfo.getShipToCustCd(), convertedEffFromDt, convertedEffThruDt,
                S21MessageFunc.clspGetMessage(errMsgCd, new String[] {shpgPlnInfo.getShipToCustCd() }));

        if (bizErrMsgMap.containsKey(BIZ_ERR_TP_SHIP_TO_EXPIRATION)) {
            bizErrMsgOld = bizErrMsgMap.get(BIZ_ERR_TP_SHIP_TO_EXPIRATION);
            bizErrMsgNew = ZYPCommonFunc.concatString(bizErrMsgOld, LINE_FEED_CODE, bizErrMsgAdd);
            bizErrMsgMap.remove(BIZ_ERR_TP_SHIP_TO_EXPIRATION);
            bizErrMsgMap.put(BIZ_ERR_TP_SHIP_TO_EXPIRATION, bizErrMsgNew);

        } else {
            bizErrMsgMap.put(BIZ_ERR_TP_SHIP_TO_EXPIRATION, bizErrMsgAdd);
        }
    }

    /**
     * Send email per business error type code
     * @param bizErrTpCd String
     * @param bizErrMsg String
     */
    private void sendBizErrMail(String bizErrTpCd, String bizErrMsg) {

        S21MailGroup mailGroupFrom = null;
        S21MailGroup mailGroupTo = null;
        S21MailAddress fromAddress = null;
        List<S21MailAddress> toAddressList = null;
        S21MailTemplate mailTemplate = null;
        S21Mail mail = null;
        String returnCode = null;

        mailGroupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID);
        mailGroupFrom.setMailKey1(MAIL_KEY_FROM);
        fromAddress = mailGroupFrom.getMailAddress().get(0);

        if (BIZ_ERR_TP_SHIP_TO_EXPIRATION.equals(bizErrTpCd)) {
            mailGroupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_SHIP_TO_EXPIRATION);
            toAddressList = mailGroupTo.getMailAddress();
            mailTemplate = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_SHIP_TO_EXPIRATION);
            mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BUSINESS_ID);
            mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL));
            mailTemplate.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, bizErrMsg);

        } else {
            S21InfoLogOutput.println(NLBM1332E, new String[] {bizErrTpCd });
            createMailMsgInfo("", NLBM1332E, new String[] {bizErrTpCd });
            return;
        }

        mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(fromAddress);
        mail.setToAddressList(toAddressList);
        mail.setMailTemplate(mailTemplate);
        // START 2019/03/01 S.Ohki [QC#30584, MOD]
//        returnCode = mail.sendMail();
        returnCode = mail.postMail();
        // END 2019/03/01 S.Ohki [QC#30584, MOD]

        if (!ZYPCommonFunc.hasValue(returnCode)) {
            S21InfoLogOutput.println(NLBM1333E);
            createMailMsgInfo("", NLBM1333E, null);
        }
    }
}
