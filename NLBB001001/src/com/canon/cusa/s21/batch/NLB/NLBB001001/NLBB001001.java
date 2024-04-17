package com.canon.cusa.s21.batch.NLB.NLBB001001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;

import business.db.RTE_CTRLTMsg;
import business.db.RTE_GRPTMsg;
import business.db.RTE_GRP_DTLTMsg;
import business.db.WH_END_MTHTMsg;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmListResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;;

/**
 * <pre>
 * Routing Dispatcher
 * 
 * Create RTE_CTRL data which defines target data of Routing Wave process. 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/14/2009   Fujitsu         K.Ozasa         Create          N/A
 * 07/07/2010   CSAI            D.Fukaya        Update          7474
 * 04/26/2013   Fujitsu         Y.Taoka         Update          OCE WDS R-WH001
 *</pre>
 */
public class NLBB001001 extends S21BatchMain {

    /** Program ID for Log */
    private static final String PROGRAM_ID = " ## NLBB001001 ## ";

    /** The data specified does not exist. */
    private static final String NLBM1020E = "NLBM1120E";

    /** The process abended. */
    private static final String NLBM1019E = "NLBM1019E";

    /** The value you entered is incorrect. */
    private static final String NLBM1062E = "NLBM1062E";

    /** The  (@)  was (@) . ResultCount = (@)  */
    private static final String ZZBM0009I = "ZZBM0009I";

    /** Data Created */
    private static final String DATA_CREATED = "created";

    /** DB item : RTL_WH_CD */
    private static final String RTL_WH_CD = "RTL_WH_CD";

    /** DB item : SHIP_TO_CUST_CD */
    private static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB item : RTE_GRP_NUM */
    private static final String RTE_GRP_NUM = "RTE_GRP_NUM";

    /** RTE_CTRL */
    private static final String RTE_CTRL = "RTE_CTRL";

    /** RTE_GRP */
    private static final String RTE_GRP = "RTE_GRP";

    /** RTE_GRP_DTL */
    private static final String RTE_GRP_DTL = "RTE_GRP_DTL";

    /** LOGICAL_REMOVE */
    private static final String LOGICAL_REMOVE = "LOGICAL_REMOVE";

    /** CREATE */
    private static final String CREATE = "CREATE";

    /** DB Access Component Return CD: Normal End */
    private static final String RETURN_CD_NORMAL_END = "0000";

    /** DB Access Component Return CD: No Data */
    private static final String RETURN_CD_NO_DATA = "2000";

    /** SHPG_ORD_CRAT_TP_CD：Do Routing (SO Creation By Order Entry) */
    private static final String SHPG_ORD_CRAT_TP_CD_ROUTING_SO_BY_ORDER = "1";

    /** SHPG_ORD_CRAT_TP_CD：Do Routing (SO Creation Over Order Entry) */
    private static final String SHPG_ORD_CRAT_TP_CD_ROUTING_SO_OVER_ORDER = "2";

    /** SHPG_ORD_CRAT_TP_CD：Not Do Routing (SO Creation by Order Entry) */
    private static final String SHPG_ORD_CRAT_TP_CD_NOT_ROUTING_SO_BY_ORDER = "3";

    // 07/07/2010 D.Fukaya append start
    /** SHPG_ORD_CRAT_TP_CD：Not Do Routing (SO Creation by Ship Complete) */
    private static final String SHPG_ORD_CRAT_TP_CD_NOT_ROUTING_SO_BY_SHIP_COMPLETE = "4";
    // 07/07/2010 D.Fukaya append end

    /** Date Time Pattern */
    private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /** Zero for padding */
    private static final String PADDING_ZERO = "0";

    /** First Day */
    private static final String FIRST_DAY = "01";

    /** Sales Date */
    private String salesDate;

    /** Default Calender Code */
    private String defaultCalCd;

    /** Normal Date for not Exist Default Calender Code */
    private String[] normalDate;

    /** Commit Count */
    private int commitCount;

    /** Total Commit Count */
    private int totalCommitCount;

    /** Term Code */
    private TERM_CD termCd;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Multiplicity of Routing Wave */
    private int multiplicity = 0;

    /**
     * Init Process
     */
    protected void initRoutine() {

        this.commitCount = 0;
        this.totalCommitCount = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> glblCmpyCd:" + this.glblCmpyCd, this);

        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get multiplicity of Routing Wave from the batch parameter
        try {
            this.multiplicity = Integer.parseInt(getUserVariable1());
        } catch (NumberFormatException e) {
            throw new S21AbendException(NLBM1062E);
        }
        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> multiplicity:" + this.multiplicity, this);
    }

    /**
     * Main Process
     */
    protected void mainRoutine() {

        // Clear all data of RTE_CTRL
        clearRteCtrl();

        // Get system time based on sales date
        String sysTime = salesDate + ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN).substring(8);

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> sysTime:" + sysTime, this);

        // Get end of month warehouse list
        List<String> endOfMonthWhList = getEndOfMonthWhList();

        // If at least one W/H
        if (endOfMonthWhList.isEmpty()) {
            endOfMonthWhList = null;
        } else {
            doProccessOfWHList(endOfMonthWhList, ZYPConstant.FLG_ON_Y);
        }

        // Get target W/H
        Map<String, Object> queryParamForWh = new HashMap<String, Object>();
        queryParamForWh.put("glblCmpyCd", this.glblCmpyCd);
        queryParamForWh.put("whDropTmTs", sysTime.substring(8, 12));
        queryParamForWh.put("endOfMonthWhList", endOfMonthWhList);
        List<String> whCdList = (List<String>) this.ssmBatchClient.queryObjectList("getWhDropTm", queryParamForWh);

        // If at least one W/H
        if (!whCdList.isEmpty()) {
            doProccessOfWHList(whCdList, ZYPConstant.FLG_OFF_N);
        }

        //ADD 04/26/2013 R-WH001
        doProccessOfTechList(ZYPConstant.FLG_OFF_N);

        // Commit all at once
        commit();
        this.totalCommitCount += this.commitCount;

    }

    /**
     * End Process
     */
    protected void termRoutine() {

        outputProcessCnt();
        setTermState(this.termCd, this.totalCommitCount, 0, this.totalCommitCount);
    }

    /**
     * Main
     * @param args Argument
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NLBB001001().executeBatch(NLBB001001.class.getSimpleName());
    }

    /**
     * Output Process Count
     */
    private void outputProcessCnt() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ outputProcessCnt ] start", this);

        String[] str = {RTE_CTRL, DATA_CREATED, String.valueOf(this.totalCommitCount) };
        S21InfoLogOutput.println(ZZBM0009I, str);

        EZDDebugOutput.println(1, PROGRAM_ID + "[ outputProcessCnt ] end", this);
    }

    /**
     * Clear All Data of RTE_CTRL
     */
    private void clearRteCtrl() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ clearRteCtrl ] start", this);

        // Delete all RTE_CTRL data logically
        RTE_CTRLTMsg outMsg = new RTE_CTRLTMsg();
        outMsg.glblCmpyCd.setValue(glblCmpyCd);
        EZDTBLAccessor.logicalRemoveByPartialKey(outMsg);
        String ret = outMsg.getReturnCode();
        if (!RETURN_CD_NORMAL_END.equals(ret) && !RETURN_CD_NO_DATA.equals(ret)) {
            String[] str = {RTE_CTRL, LOGICAL_REMOVE, ret };
            throw new S21AbendException(NLBM1019E, str);
        }

        EZDDebugOutput.println(1, PROGRAM_ID + "[ clearRteCtrl ] end", this);
    }

    /**
     * Do process of WH List
     */
    private void doProccessOfWHList(List<String> whCdList, String endMonthFlg) {
        // Get target Shipping Plan data, do dispatcher
        Map<String, Object> queryParamForShpgPln = new HashMap<String, Object>();
        queryParamForShpgPln.put("glblCmpyCd", this.glblCmpyCd);
        queryParamForShpgPln.put("shpgStsCd", SHPG_STS.HARD_ALLOCATED);
        queryParamForShpgPln.put("whCdList", whCdList);
        List<String> shpgOrdCratTpCdList = createShpgOrdCratTpCdList();
        queryParamForShpgPln.put("shpgOrdCratTpCdList", shpgOrdCratTpCdList);
        queryParamForShpgPln.put("locGrpCd", LOC_GRP.CUSA);
        this.ssmBatchClient.queryObject("getShpgPln", queryParamForShpgPln, new DoDispatcher(endMonthFlg));

        // Clear Routing Information
        Map<String, Object> queryParamForRteGrp = new HashMap<String, Object>();
        queryParamForRteGrp.put("glblCmpyCd", this.glblCmpyCd);
        queryParamForRteGrp.put("rteStsCd", RTE_STS.ROUTED);
        queryParamForRteGrp.put("whCdList", whCdList);
        this.ssmBatchClient.queryObject("getRteGrp", queryParamForRteGrp, new ClearRoutingInfo());
    }

    //START ADD 04/26/2013 R-WH001
    /**
     * Do process of Technician
     */
    private void doProccessOfTechList(String endMonthFlg) {

        // Get target Shipping Plan data, do dispatcher
        Map<String, Object> queryParamForShpgPln = new HashMap<String, Object>();
        queryParamForShpgPln.put("glblCmpyCd", this.glblCmpyCd);
        queryParamForShpgPln.put("shpgStsCd", SHPG_STS.HARD_ALLOCATED);
        List<String> shpgOrdCratTpCdList = createShpgOrdCratTpCdList();
        queryParamForShpgPln.put("shpgOrdCratTpCdList", shpgOrdCratTpCdList);
        queryParamForShpgPln.put("ivtyLocTpCd", LOC_TP.TECHNICIAN);
        queryParamForShpgPln.put("locGrpCd", LOC_GRP.CUSA);
        List<String> whCdList = (List<String>) this.ssmBatchClient.queryObjectList("getShpgPlnForTech", queryParamForShpgPln, new DoDispatcher(endMonthFlg));

        if (!whCdList.isEmpty()) {
            // Clear Routing Information
            Map<String, Object> queryParamForRteGrp = new HashMap<String, Object>();
            queryParamForRteGrp.put("glblCmpyCd", this.glblCmpyCd);
            queryParamForRteGrp.put("rteStsCd", RTE_STS.ROUTED);
            queryParamForRteGrp.put("whCdList", whCdList);
            this.ssmBatchClient.queryObject("getRteGrp", queryParamForRteGrp, new ClearRoutingInfo());
        }
    }
    //END ADD 04/26/2013 R-WH001

    //START MOD 04/26/2013 R-WH001
    /**
     * Get Target Shipping Plan Data, Do Dispatcher
     */
    private class DoDispatcher extends S21SsmListResultSetHandlerSupport {

        /** End of month flg */
        private String endMonthFlg;

        public DoDispatcher(String endMonthFlg) {
            this.endMonthFlg = endMonthFlg;
        }

        public List<String> doProcessQueryResult(ResultSet rs) throws SQLException {
            EZDDebugOutput.println(1, PROGRAM_ID + "[ DoDispatcher ] start", this);

            //WH List
            List<String> whCdList = new ArrayList<String>(); //ADD 04/26/2013 R-WH001
            int procCtrlNum = 1;

            // Define process ID in order
            // (For example, if multiplicity is 3, process ID is 1,2,3,1,2,3,1,..)
            while (rs.next()) {

                String whCd = nullToEmpty(rs.getString(RTL_WH_CD));
                String shipToCustCd = nullToEmpty(rs.getString(SHIP_TO_CUST_CD));

                // Create RTE_CTRL data
                RTE_CTRLTMsg outMsg = new RTE_CTRLTMsg();
                outMsg.glblCmpyCd.setValue(glblCmpyCd);
                outMsg.whCd.setValue(whCd);
                outMsg.shipToCustCd.setValue(shipToCustCd);
                outMsg.procCtrlNum.setValue(String.valueOf(procCtrlNum));
                outMsg.rteStsCd.setValue(RTE_STS.UN_ROUTED);
                outMsg.endMthFlg.setValue(this.endMonthFlg);
                EZDTBLAccessor.create(outMsg);
                String ret = outMsg.getReturnCode();
                if (!RETURN_CD_NORMAL_END.equals(ret)) {
                    String[] str = {RTE_CTRL, CREATE, ret };
                    throw new S21AbendException(NLBM1019E, str);
                }
                commitCount++;
                procCtrlNum++;

                // Initialize ID when it reaches multiplicity
                if (procCtrlNum > multiplicity) {
                    procCtrlNum = 1;
                }

                //START ADD 04/26/2013 R-WH001
                if (!whCdList.contains(whCd)) {
                    whCdList.add(whCd);
                }
                //END ADD 04/26/2013 R-WH001
            }
            EZDDebugOutput.println(1, PROGRAM_ID + "[ DoDispatcher ] end", this);
            return whCdList; //ADD 04/26/2013 R-WH001
        }

    }
    //END MOD 04/26/2013 R-WH001

    /**
     * Clear Routing Information of target W/H
     */
    private class ClearRoutingInfo extends S21SsmVoidResultSetHandlerSupport {

        public void doProcessQueryResult(ResultSet rs) throws SQLException {
            EZDDebugOutput.println(1, PROGRAM_ID + "[ ClearRoutingInfo ] start", this);

            while (rs.next()) {

                String rteGrpNum = nullToEmpty(rs.getString(RTE_GRP_NUM));

                // Delete RTE_GRP_DTL logically
                RTE_GRP_DTLTMsg outMsgDtl = new RTE_GRP_DTLTMsg();
                outMsgDtl.glblCmpyCd.setValue(glblCmpyCd);
                outMsgDtl.rteGrpNum.setValue(rteGrpNum);
                EZDTBLAccessor.logicalRemoveByPartialKey(outMsgDtl);
                String ret = outMsgDtl.getReturnCode();
                if (!RETURN_CD_NORMAL_END.equals(ret) && !RETURN_CD_NO_DATA.equals(ret)) {
                    String[] str = {RTE_GRP_DTL, LOGICAL_REMOVE, ret };
                    throw new S21AbendException(NLBM1019E, str);
                }

                // Delete RTE_GRP logically
                RTE_GRPTMsg outMsg = new RTE_GRPTMsg();
                outMsg.glblCmpyCd.setValue(glblCmpyCd);
                outMsg.rteGrpNum.setValue(rteGrpNum);
                EZDTBLAccessor.logicalRemove(outMsg);
                ret = outMsg.getReturnCode();
                if (!RETURN_CD_NORMAL_END.equals(ret) && !RETURN_CD_NO_DATA.equals(ret)) {
                    String[] str = {RTE_GRP, LOGICAL_REMOVE, ret };
                    throw new S21AbendException(NLBM1019E, str);
                }

            }
            EZDDebugOutput.println(1, PROGRAM_ID + "[ ClearRoutingInfo ] end", this);
        }

    }

    /**
     * Create List of SHPG_ORD_CRAT_TP_CD
     * @return List of SHPG_ORD_CRAT_TP_CD
     */
    private List<String> createShpgOrdCratTpCdList() {
        EZDDebugOutput.println(1, PROGRAM_ID + "[ createShpgOrdCratTpCdList ] start", this);

        List<String> list = new ArrayList<String>();

        // Create list of code which are target of SO Creation
        list.add(SHPG_ORD_CRAT_TP_CD_ROUTING_SO_BY_ORDER);
        list.add(SHPG_ORD_CRAT_TP_CD_ROUTING_SO_OVER_ORDER);
        list.add(SHPG_ORD_CRAT_TP_CD_NOT_ROUTING_SO_BY_ORDER);
        // 07/07/2010 D.Fukaya append start
        list.add(SHPG_ORD_CRAT_TP_CD_NOT_ROUTING_SO_BY_SHIP_COMPLETE);
        // 07/07/2010 D.Fukaya append end

        EZDDebugOutput.println(1, PROGRAM_ID + "[ createShpgOrdCratTpCdList ] end", this);
        return list;

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
     * Get End Of Month Warehouse Code List
     * @param salesDate String
     * @return String
     */
    private List<String> getEndOfMonthWhList() {
        List<String> targetWhList = new ArrayList<String>();

        String salesYear = salesDate.substring(0, 4);
        String salesMonth = salesDate.substring(4, 6);

        // Get target WH_END_MTH Infomation
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        List<WH_END_MTHTMsg> whEndMthList = (List<WH_END_MTHTMsg>) this.ssmBatchClient.queryObjectList("getWhEndMth", queryParam);

        for (int i = 0; i < whEndMthList.size(); i++) {
            WH_END_MTHTMsg tMsg = (WH_END_MTHTMsg) whEndMthList.get(i);
            String whCd = tMsg.whCd.getValue();
            int bizDays = tMsg.endMthBizDaysAot.getValueInt();

            if (bizDays > 0) {

                String calCd = getCalCd(CAL_SUB_TP.WAREHOUSE_CALENDAR, whCd, salesDate.substring(0, 6));
                String startDate = getStartDate(calCd, salesYear, salesMonth, bizDays);
                if (startDate != null && (salesDate.substring(0, 8)).compareTo(startDate) >= 0) {
                    targetWhList.add(whCd);
                }
            }
        }
        return targetWhList;
    }

    /**
     * Get warehouse calender code
     * @param calSubTpCd CAL_SUB_TP_CD
     * @param calMultCd CAL_MULT_CD
     * @param date YYYY or YYYYMM or YYYYMMDD
     * @return String
     */
    private String getCalCd(String calSubTpCd, String calMultCd, String date) {

        String calTpCd = searchCalCd(calSubTpCd, calMultCd, date);

        if (ZYPCommonFunc.hasValue(calTpCd)) {
            return calTpCd;
        } else {
            return getDefaultCalCd(date);
        }
    }

    /**
     * Get default calender code
     * @return String
     */
    private String getDefaultCalCd(String date) {

        if (defaultCalCd == null) {

            String calTpCd = searchCalCd(CAL_SUB_TP.COMPANY_CALENDAR, glblCmpyCd, date);

            if (ZYPCommonFunc.hasValue(calTpCd)) {
                defaultCalCd = calTpCd;
            } else {
                EZDDebugOutput.println(1, PROGRAM_ID + "Calender master of company is not exist.", this);
                throw new S21AbendException(NLBM1020E);
            }
        }
        return defaultCalCd;
    }

    /**
     * Search CAL table and CAL_RELN table
     * @return String
     */
    private String searchCalCd(String calSubTpCd, String calMultCd, String date) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("calSubTpCd", calSubTpCd);
        queryParam.put("calMultCd", calMultCd);
        queryParam.put("dtAttrbCd", DT_ATTRB.BUSINESS);
        queryParam.put("calDt", date + "%");
        return (String) this.ssmBatchClient.queryObject("getCalTpCd", queryParam);
    }

    /**
     * Get start date of end of month
     * @param calCd String
     * @param year String
     * @param month String
     * @param bizDays int
     * @return String
     */
    private String getStartDate(String calCd, String year, String month, int bizDays) {

        String startDate = "";

        String[] bizDate = ZYPDateUtil.getBusinessDaysEx(glblCmpyCd, calCd, year, month);

        if (bizDate != null) {
            if (bizDays > bizDate.length) {
                startDate = ZYPCommonFunc.concatString(year, month, FIRST_DAY);
            } else {
                startDate = bizDate[bizDate.length - bizDays];
            }
        }

        return startDate;
    }
}
