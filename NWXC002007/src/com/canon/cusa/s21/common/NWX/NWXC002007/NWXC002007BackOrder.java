/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2010   Fujitsu         K.Tajima        Update          Performance tuning (method name : isExist)
 * 2010/12/01   Fujitsu         K.Tajima        Update          645 [Performance] NWAL0010 Order Entry (case of Auto Allocation)
 * 2015/09/10   Fujitsu         S.Takami        Update          CNA(2.8 Allocation - SO/PO)
 * </pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC002007;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;
import business.db.SHPG_PLNTMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXChildMdseQtyByCpoDtlThreadLocalCache; // 2015/09/10 CNA(2.8 Allocation - SO/PO) Add
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

public class NWXC002007BackOrder {

    public static final String BACKORDER_CHECK_TYPE_NONE = "1";
    public static final String BACKORDER_CHECK_TYPE_DIST = "2";

    public static final String SET_AVAL_QTY = "setPossibleQty";
    
    private static final String SET_TRX_LINE_SUB_NUM = "000";
    private static final String Y = ZYPConstant.FLG_ON_Y;
    private static final String N = ZYPConstant.FLG_OFF_N;

    
    public static String chkAllocStartedDay(BigDecimal daysPriAllocNum, final String glblCmpyCd, final String invtyLocCd, final String slsDt, final String rddDt, final ONBATCH_TYPE onBatchType) {
        // 08/27/2010 Defect 203 --- add start ---

        int days = daysPriAllocNum.negate().intValue();
        String startDay = ZYPDateUtil.addDays(rddDt, days);
        String allocStartedDay = null;

        String calTpCd = getCalTpCd(glblCmpyCd, invtyLocCd);
        if (!hasValue(calTpCd) || calTpCd.equals("NWZM0949E") || calTpCd.equals("NWZM0673E")) {
            if (!hasValue(calTpCd)) {
                allocStartedDay = "NWZM0949E";
            } else {
                allocStartedDay = calTpCd;
            }
            return allocStartedDay;
        }

        String businessDay = getBusinessDay(glblCmpyCd, startDay, calTpCd, onBatchType);
        if (businessDay == null) {
            allocStartedDay = "NWZM0948E";
            return allocStartedDay;
        }

        if (ZYPDateUtil.compare(slsDt, businessDay) < 0) {
            // startDay > param.slsDt.getValue()
            allocStartedDay = "NWZM0950E";
        }

        return allocStartedDay;
        // 08/27/2010 Defect 203 --- add start ---
    }

    /**
     * exists BO(Back-Order)?
     * 
     * @param   param   NWXC002007BackOrderParam
     * @return  true/exists BO, false/doesn't exist BO
     */
    @SuppressWarnings("unchecked")
    public static boolean isExist(NWXC002007BackOrderParam param) {

        if (!hasValue(param.getGlblCmpyCd())) {
            return false;
        }
        if (!hasValue(param.getSlsDt())) {
            return false;
        }
        if (!hasValue(param.getTrxSrcTpCd())) {
            return false;
        }
        if (!hasValue(param.getTrxHdrNum())) {
            return false;
        }
        if (!hasValue(param.getTrxLineNum())) {
            return false;
        }
        if (!hasValue(param.getTrxLineSubNum())) {
            return false;
        }
        if (!hasValue(param.getTs())) {
            return false;
        }
        if (!hasValue(param.getMdseCd())) {
            return false;
        }
        if (!hasValue(param.getCheckTp())) {
            return false;
        }
        if (BACKORDER_CHECK_TYPE_NONE.equals(param.getCheckTp())) {
            if (!hasValue(param.getDaysPriAllocNum())) {
                return false;
            }
        }

        
        if (BACKORDER_CHECK_TYPE_NONE.equals(param.getCheckTp())) {

// NEW K.Tajima - Def# 645 [Performance] NWAL0010 Order Entry (case of Auto Allocation)
            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("param", param);
            // TRX_SRC_TP
            final Map<String, String> trxSrcTp = new HashMap<String, String>();
            trxSrcTp.put("wholeSales", TRX_SRC_TP.WHOLE_SALES);
            trxSrcTp.put("retail",     TRX_SRC_TP.RETAIL);
            ssmParam.put("trxSrcTp", trxSrcTp);
            // SHPG_STS
            final Map<String, String> shpgSts = new HashMap<String, String>();
            shpgSts.put("validated", SHPG_STS.VALIDATED);
            ssmParam.put("shpgSts", shpgSts);
            // HLD_LVL
            final Map<String, String> hldLvl = new HashMap<String, String>();
            hldLvl.put("cpoLevel",          HLD_LVL.CPO_LEVEL);
            hldLvl.put("cpoDetailLevel",    HLD_LVL.CPO_DETAIL_LEVEL);
            hldLvl.put("shippingPlanLevel", HLD_LVL.SHIPPING_PLAN_LEVEL);
            ssmParam.put("hldLvl", hldLvl);
            // DIST_TP
            final Map<String, String> distTp = new HashMap<String, String>();
            distTp.put("none",         DIST_TP.NONE);
            distTp.put("distribution", DIST_TP.DISTRIBUTION);
            ssmParam.put("distTp", distTp);
            // HARD_ALLOC_TP
            final Map<String, String> hardAllocTp = new HashMap<String, String>();
            hardAllocTp.put("auto", HARD_ALLOC_TP.AUTO);
            ssmParam.put("hardAllocTp", hardAllocTp);
            
            // exists BO?
            final List<String> backOrderShpgPlnNumList = getSsmClient().queryObjectList("getBackOrderShpgPlnNumListForNoneItem", ssmParam);
            final boolean existsBO = !backOrderShpgPlnNumList.isEmpty();
            S21InfoLogOutput.println(" #" + NWXC002007BackOrder.class.getSimpleName() + ".isExist?=[" + existsBO + "], backOrderShpgPlnNumList=" + backOrderShpgPlnNumList);
            return existsBO;

        } else if (BACKORDER_CHECK_TYPE_DIST.equals(param.getCheckTp())) {

            final Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",           param.getGlblCmpyCd());
            ssmParam.put("slsDt",                param.getSlsDt());
            ssmParam.put("mdseCd",               param.getMdseCd());
            ssmParam.put("trxSrcTpCd",           param.getTrxSrcTpCd());
            ssmParam.put("trxHdrNum",            param.getTrxHdrNum());
            ssmParam.put("trxLineNum",           param.getTrxLineNum());
            ssmParam.put("trxLineSubNum",        param.getTrxLineSubNum());
            ssmParam.put("ts",                   param.getTs());
            ssmParam.put("trxSrcTpWholeSales",   TRX_SRC_TP.WHOLE_SALES);
            ssmParam.put("Validated",            SHPG_STS.VALIDATED);
            ssmParam.put("stkStsCdGood",         STK_STS.GOOD);
            ssmParam.put("hardAllocTpAuto",      HARD_ALLOC_TP.AUTO);
            ssmParam.put("trxLineSubNumSetMdse", SET_TRX_LINE_SUB_NUM);
            ssmParam.put("hldLvlCd1",            HLD_LVL.CPO_LEVEL);
            ssmParam.put("hlbLvlCdList",         asList(HLD_LVL.CPO_DETAIL_LEVEL, HLD_LVL.SHIPPING_PLAN_LEVEL));

            if (hasValue(param.getInvtyLocCd())) {
                ssmParam.put("invtyLocCd", param.getInvtyLocCd());
            } else {
                ssmParam.put("invtyLocCd", null);
            }

            // exists BO?
            return (Boolean) getSsmClient().queryObject("existsBackOrderForDistItem", ssmParam, new S21SsmBooleanResultSetHandlerSupport() {
                @Override
                protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
                    return rs.next();
                }
            });

        } else {
            return false;
        }
    }

    
//    // Master Synchronous Check
//    public static boolean isSynchronous(final String glblCmpyCd, final String mdseCd, final String distTpCd) {
//
//        if (!hasValue(glblCmpyCd)) {
//            return false;
//        }
//        if (!hasValue(mdseCd)) {
//            return false;
//        }
//        if (!hasValue(distTpCd)) {
//            return false;
//        }
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("mdseCd", mdseCd);
//        queryParam.put("distTpCd", distTpCd);
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("trxLineSubNumSetMdse", "000");
//        queryParam.put("trxSrcTpWholeSales", TRX_SRC_TP.WHOLE_SALES);
//        queryParam.put("trxSrcTpRetail", TRX_SRC_TP.RETAIL);
//        queryParam.put("Validated", SHPG_STS.VALIDATED);
//        queryParam.put("distTpNone", DIST_TP.NONE);
//        queryParam.put("distTpDistribution", DIST_TP.DISTRIBUTION);
//        queryParam.put("stkStsCdGood", STK_STS.GOOD);
//
//        String shpgPlnNum = (String) getSsmClient().queryObject("checkMaster", queryParam);
//        return hasValue(shpgPlnNum);
//    }

    // get SetPossibleQty
    public static Map<String, Object> setPossibleQty(final String glblCmpyCd, final String trxSrcTpCd, final String trxHdrNum, final String trxLineNum, final String setShpgPlnNum) {

        Map<String, Object> compQtyList = new HashMap<String, Object>();

        if (TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
            // Transaction Check
            if (!chkRossOrderComp(glblCmpyCd, trxHdrNum, trxLineNum, setShpgPlnNum)) {
                compQtyList.put(SET_AVAL_QTY, ZERO);
                return compQtyList;
            }
        }

        List<Map<String, Object>> eachCompTotalQtyList = getEachCompTotalQty(glblCmpyCd, trxSrcTpCd, trxHdrNum, trxLineNum, setShpgPlnNum);

        if ((eachCompTotalQtyList == null || eachCompTotalQtyList.isEmpty())) {
            compQtyList.put(SET_AVAL_QTY, ZERO);
            return compQtyList;
        }

        BigDecimal setPossibleQty = null;

        for (Map<String, Object> cpoDtlComp : eachCompTotalQtyList) {

            BigDecimal totalCompQty = (BigDecimal) cpoDtlComp.get("SUM_ORD_QTY");

            if (ZERO.compareTo(totalCompQty) == 0) {
                compQtyList.put(SET_AVAL_QTY, ZERO);
                return compQtyList;
            }

            String lineSubNum = (String) cpoDtlComp.get("CPO_DTL_LINE_SUB_NUM");
            // 2015/09/10 CNA(2.8 Allocation - SO/PO) Update Start
//            String mdseCd = (String) cpoDtlComp.get("MDSE_CD");
//            String setMdseCd = (String) cpoDtlComp.get("SET_MDSE_CD");
//            String ts = (String) cpoDtlComp.get("CPO_ORD_TS");

//            BigDecimal compQty = getCompQty(glblCmpyCd, mdseCd, setMdseCd, ts);
            BigDecimal compQty = NWXChildMdseQtyByCpoDtlThreadLocalCache.getInstance().getChildMdseQtyFromCpoDtl(glblCmpyCd, trxHdrNum, trxLineNum, lineSubNum);
            // 2015/09/10 CNA(2.8 Allocation - SO/PO) Update End

            if (ZERO.compareTo(compQty) == 0) {
                compQtyList.put(SET_AVAL_QTY, ZERO);
                return compQtyList;
            }

            BigDecimal calcSetPossibleQty = totalCompQty.divide(compQty, BigDecimal.ROUND_DOWN);

            if (ZERO.compareTo(calcSetPossibleQty) > 0) {
                // calcSetPossibleQty <=0
                compQtyList.put(SET_AVAL_QTY, ZERO);
                return compQtyList;
            } else if (ZERO.compareTo(calcSetPossibleQty) == 0) {
                compQtyList.put(SET_AVAL_QTY, ZERO);
                return compQtyList;
            }

            if (setPossibleQty == null) {
                setPossibleQty = calcSetPossibleQty;

            } else if (setPossibleQty.compareTo(calcSetPossibleQty) > 0) {
                // setPossibleQty < calcSetPossibleQty
                setPossibleQty = calcSetPossibleQty;
            }

            compQtyList.put(lineSubNum, compQty);

        }

        compQtyList.put(SET_AVAL_QTY, setPossibleQty);

        return compQtyList;

    }

    /** Get CMPSN */
    private static boolean chkRossOrderComp(final String glblCmpyCd, final String trxHdrNum, final String trxLineNum, final String setShpgPlnNum) {

        // SHPG_PLN
        SHPG_PLNTMsg setShpgPlnMsg = new SHPG_PLNTMsg();
        setShpgPlnMsg.glblCmpyCd.setValue(glblCmpyCd);
        setShpgPlnMsg.shpgPlnNum.setValue(setShpgPlnNum);

        SHPG_PLNTMsg shpgPlnMsgRcd = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKey(setShpgPlnMsg);

        if (shpgPlnMsgRcd == null) {
            return false;
        }
        return true;
    }

    private static String getBusinessDay(final String glblCmpyCd, final String startDay, final String calTpCd, final ONBATCH_TYPE onBatchType) {

        String businessDay = startDay;
        boolean isBusinessDay = false;

        if (ONBATCH_TYPE.BATCH.equals(onBatchType)) {

            isBusinessDay = ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, startDay);

            if (!isBusinessDay) {
                businessDay = ZYPDateUtil.addBusinessDay(calTpCd, startDay, -1);
            }

        } else {

            if (ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, startDay)) {
                // nothing to do

            } else {
                businessDay = ZYPDateUtil.addBusinessDay(calTpCd, startDay, -1);
            }
        }

        return businessDay;
    }

    private static String getCalTpCd(final String glblCmpyCd, final String invtyLocCd) {

        CAL_RELNTMsg calRelnTMsg = null;
        String calTpCd = null;

        if (hasValue(invtyLocCd)) {
            // 1.[ Search CAL_TP_CD Table]
            calRelnTMsg = new CAL_RELNTMsg();
            calRelnTMsg.glblCmpyCd.setValue(glblCmpyCd);
            calRelnTMsg.calSubTpCd.setValue(CAL_SUB_TP.WAREHOUSE_CALENDAR);
            calRelnTMsg.calMultCd.setValue(invtyLocCd);
            calRelnTMsg = (CAL_RELNTMsg) S21CacheTBLAccessor.findByKey(calRelnTMsg);
        }

        if (calRelnTMsg != null) {
            calTpCd = calRelnTMsg.calTpCd.getValue();
        } else {

            // 2.[ Search CAL_TP_CD Table]
            CAL_RELNTMsg calRelnTKey = new CAL_RELNTMsg();
            calRelnTKey.setSQLID("001");
            calRelnTKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
            calRelnTKey.setConditionValue("calSubTpCd01", CAL_SUB_TP.COMPANY_CALENDAR);

            CAL_RELNTMsgArray calRelnTRcd = (CAL_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(calRelnTKey);

            if (calRelnTRcd == null || calRelnTRcd.length() == 0) {
                calTpCd = "NWZM0949E";

            } else if (calRelnTRcd.length() > 1) {
                // When acquire more than two cases,
                // process is finished as master setting error.
                calTpCd = "NWZM0673E";
            } else {
                calTpCd = calRelnTRcd.no(0).calTpCd.getValue();
            }
        }

        return calTpCd;

    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getEachCompTotalQty(final String glblCmpyCd, final String trxSrcTpCd, final String trxHdrNum, final String trxLineNum, final String setShpgPlnNum) {

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", glblCmpyCd);
        key.put("trxHdrNum", trxHdrNum);
        key.put("trxLineNum", trxLineNum);
        key.put("trxSrcTpCd", trxSrcTpCd);
        key.put("trxLineNumSet", "000");
        key.put("setShpgPlnNum", setShpgPlnNum);
        key.put("shpgStsCdHA", SHPG_STS.HARD_ALLOCATED);
        key.put("shpgStsCdSO", SHPG_STS.S_OR_O_CANCELLED);
        key.put("shpgStsCdPO", SHPG_STS.P_OR_O_CANCELLED);
        key.put("shpgStsCdVA", SHPG_STS.VALIDATED);
        key.put("on", Y);
        key.put("off", N);

        if (TRX_SRC_TP.RETAIL.equals(trxSrcTpCd)) {
            return null;
        } else {
            return getSsmClient().queryObjectList("queryEachCompTotalQty", key);
        }
    }

    private static S21SsmBatchClient getSsmClient() {
        return S21SsmBatchClient.getClient(NWXC002007BackOrder.class);
    }
}
