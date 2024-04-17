/**
 *  <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC002001;

import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.addDays;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static parts.common.EZDDebugOutput.isDebug;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AREA_LEAD_TMTMsg;
import business.db.CAL_RELNTMsg;
import business.db.CAL_RELNTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.SHIP_FROM_LOC_LIST_VTMsgArray;
import business.db.SHPG_SVC_LEAD_TMTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.TRNSP_LTTMsg;
import business.db.WH_LEAD_TMTMsg;
import business.db.WH_LEAD_TMTMsgArray;
import business.parts.NWZC002001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.AddBizDayCache;
import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.CalRelnCache;
import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.DataCacheForSSM;
import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.FindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.ShipFromLocListViewCache;
import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.ShpgSvcLeadTmCache;
import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.VarcharConstCache;
import com.canon.cusa.s21.api.NWZ.NWZC002001.cache.WhLeadTmCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXOrdTakeMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_MODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Leadtime Calculation API
 * 
 * Calculate Work Start Date(WSD),Planned Ship Date(PSD),Planned Delivery Date(PDD) 
 * from Requsted Delivery Date(RDD),Requested Shipping Date(RSD),Requested Work Start Date(RWSD)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name        Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/06/24   Fujitsu         H.Kato      Create          N/A
 * 2009/09/03   Fujitsu         H.Kato      Update          N/A
 * 2009/11/12   Fujitsu         K.Kato      Update          1760
 * 2009/11/13   Fujitsu         T.Kaneda    Update          N/A
 * 2009/11/16   Fujitsu         T.knaeda    Update          N/A
 * 2010/01/04   Fujitsu         K.Tajima    Update          N/A (refactoring : to get high-performance)
 * 2010/02/19   Fujitsu         K.Kato      Update          3896
 * 2010/08/26   Fujitsu         K.Tajima    Update          203
 * 2013/04/03   Fujitsu         F.Saito     Update          WDS#R-OM005-001
 * 2016/05/11   Fujitsu         T.Yoshida   Update          S21_NA#8166 (For Performance)
 * 2016/07/27   Fujitsu         T.Mizuki    Update          S21_NA#5823
 * 2018/06/15   Fujitsu         K.Ishizuka  Update          S21_NA#24294
 *</pre>
 */
public class NWZC002001 extends S21ApiCommonBase implements SHPG_SVC_TP {

    /** Data Company Code is not entered. */
    public static final String NWZM0001E = "NWZM0001E";
    /** Merchandise Code is not entered. */
    public static final String NWZM0035E = "NWZM0035E";
    /** Quantity is not entered. */
    public static final String NWZM0041E = "NWZM0041E";
    /** "Shipping Service Level" is not entered. */
    public static final String NWZM0323E = "NWZM0323E";
    /** "Freight Charge Method Code" is not entered. */
    public static final String NWZM0324E = "NWZM0324E";
    /** "Freight Charge To Code" is not entered. */
    public static final String NWZM0325E = "NWZM0325E";
    /** 'Requested Delivery Date', 'Requested Shipping Date' or 'Requested Work Start Date' must be entered. */
    public static final String NWZM0326E = "NWZM0326E";
    /** Warehouse Code is not entered. */
    public static final String NWZM0327E = "NWZM0327E";
    /** "Ship to Zip Code" is not entered. */
    public static final String NWZM0328E = "NWZM0328E";
    /** "Ship to Customer Code" is not entered. */
    public static final String NWZM0329E = "NWZM0329E";
    /** "Sell to Customer Code" is not entered. */
    public static final String NWZM0330E = "NWZM0330E";
    /** "Ship to State Code" is not entered. */
    public static final String NWZM0331E = "NWZM0331E";
    /** UOM Code is not entered. */
    public static final String NWZM0237E = "NWZM0237E";
    /** Sales Date is not entered. */
    public static final String NWZM0668E = "NWZM0668E";
    /** For the specified Merchandise, weight information is not registered. */
    public static final String NWZM0654E = "NWZM0654E";
    /** The entered Merchandise Code does not exist in Master. */
    public static final String NWZM0036E = "NWZM0036E";
    /** Weight setting is invalid. */
    public static final String NWZM0660E = "NWZM0660E";
    /** Registered Service Type is out of scope. */
    public static final String NWZM0661E = "NWZM0661E";
    /** Multiple dates cannot be selected as a Parameter. */
    public static final String NWZM0665E = "NWZM0665E";
    /** "Lead time master" is not set. */
    public static final String NWZM0667E = "NWZM0667E";
    /** Shipping Mode cannot be obtained. */
    public static final String NWZM0670E = "NWZM0670E";
    /** Multiple Shipping Modes are obtained.  Please check Master data. */
    public static final String NWZM0671E = "NWZM0671E";
    /** Shipping Service Type is not registered. */
    public static final String NWZM0672E = "NWZM0672E";
    /** Multiple Warehouse Calendars are obtained.  Please check Master data. */
    public static final String NWZM0673E = "NWZM0673E";
    /** Cannot retrieve Config Lead Time. */
    public static final String NWZM1246E = "NWZM1246E";

    /** Flag (N) */
    private static final String N = ZYPConstant.FLG_OFF_N;

    /** Day Of Hour (24H) */
    private static final int DAY_OF_HOUR = 24;
    /** NUM_CONST Key : Default Configuration Lead Time Day Number */
    private static final String CONST_KEY_DEFAULT_LT = "DEFAULT_CONFIG_LT_DAY_NUM";

    /** DB Accessor */
    private final S21SsmBatchClient ssmClient;

    /** Constructor */
    public NWZC002001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * Leadtime Calculation API
     * 
     * <pre>
     * Calculate Leadtime main
     * </pre>
     * 
     * @param param NWZC002001PMsg Interfsce
     * @param onBatchType division of online or batch
     */
    public void execute(final NWZC002001PMsg param, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execute";
        debugMethodStart(getClass(), methodNm);

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        try {

            /**
             * main process
             */
            doProcess(msgMap);

        } finally {
            msgMap.flush();
            debugMethodEnd(getClass(), methodNm);
        }
    }

    /**
     * main process.
     * 
     * @param msgMap    S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        final NWZC002001PMsg param = (NWZC002001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0001E);
            return;
        }

        // Merchandise Code
        if (!hasValue(param.mdseCd)) {
            msgMap.addXxMsgId(NWZM0035E);
            return;
        }

        // WRSD, RSD, RDD
        if (!hasValue(param.xxRddDt) && !hasValue(param.xxRsdDt) && !hasValue(param.xxRwsdDt)) {
            msgMap.addXxMsgId(NWZM0326E);
            return;
        }

        // WRSD, RSD, RDD
        if (hasValue(param.xxRddDt) && (hasValue(param.xxRsdDt) || hasValue(param.xxRwsdDt))) {
            msgMap.addXxMsgId(NWZM0665E);
            return;
        }

        // WRSD, RSD, RDD
        if (hasValue(param.xxRsdDt) && (hasValue(param.xxRddDt) || hasValue(param.xxRwsdDt))) {
            msgMap.addXxMsgId(NWZM0665E);
            return;
        }

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String mdseCd     = param.mdseCd.getValue();

        final ORD_TAKE_MDSETMsg ordTakeMdseTMsg = getOrdTakeMdse(glblCmpyCd, mdseCd);
        if (ordTakeMdseTMsg != null) {
            mdseCd = ordTakeMdseTMsg.mdseCd.getValue();
        }

        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            msgMap.addXxMsgId(NWZM0036E);
            return;
        }

        if (N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
            setDefaultPlanedDate(param);
            return;
        }

        // Vendor Drop
        final SHIP_FROM_LOC_LIST_VTMsgArray shipFromLocListVTMsgArray = getShipFromLocListV(msgMap);
        if (shipFromLocListVTMsgArray.getValidCount() > 0) {
            setDefaultPlanedDate(param);
            return;
        }

        // Shipping Service Level Code
        if (!hasValue(param.shpgSvcLvlCd)) {
            msgMap.addXxMsgId(NWZM0323E);
            return;
        }

        // Inventory Location Code
        if (!hasValue(param.invtyLocCd)) {
            msgMap.addXxMsgId(NWZM0327E);
            return;
        }

        String dummyWhCd = VarcharConstCache.getInstance().getVarCharConst(glblCmpyCd, "CR_AND_BILL_ONLY_DUMMY_WH_CD");
        if (ZYPCommonFunc.hasValue(dummyWhCd)) {
            List<String> dummyWhCdList = S21StringUtil.toList(dummyWhCd);
            if (dummyWhCdList.contains(param.invtyLocCd.getValue())) {
                setDefaultPlanedDate(param);
                return;
            } 
        }

        // Sales Date
        if (!hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0668E);
            return;
        }

        // get [SHPG_SVC_LVL]
        final String shpgSvcLvlCd = param.shpgSvcLvlCd.getValue();

        SHPG_SVC_LVLTMsg shpgSvcLvlTMsg = new SHPG_SVC_LVLTMsg();
        setValue(shpgSvcLvlTMsg.glblCmpyCd,   glblCmpyCd);
        setValue(shpgSvcLvlTMsg.shpgSvcLvlCd, shpgSvcLvlCd);

        shpgSvcLvlTMsg = (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(shpgSvcLvlTMsg);

        if (shpgSvcLvlTMsg == null) {
            msgMap.addXxMsgId(NWZM0672E);
            return;
        }

        final String shpgSvcTpCd = shpgSvcLvlTMsg.shpgSvcTpCd.getValue();

        // [calcualte transportation leadtime]
        BigDecimal trnspLtAot = null;
        String     shpgModeCd = null;

        /**********************************************************************
         * SHPG_SVC_TP_CD = [2]:Guaranteed Days Delivery
         **********************************************************************/
        if (asList(GUARANTEED_DAYS_DELIVERY).contains(shpgSvcTpCd)) {

            SHPG_SVC_LEAD_TMTMsg shpgSvcLeadTmTMsg = ShpgSvcLeadTmCache.getInstance().getTMsgByKey(glblCmpyCd, shpgSvcLvlCd);

            if (shpgSvcLeadTmTMsg == null) {
                msgMap.addXxMsgId(NWZM0667E);
                return;
            }

            trnspLtAot = shpgSvcLeadTmTMsg.delyLeadAot.getValue();
            shpgModeCd = SHPG_MODE.PARCEL;

        /**********************************************************************
         * SHPG_SVC_TP_CD = [0]:Ground Standard Delivery, [1]:Pick Up
         **********************************************************************/
        } else if (asList(GROUND_STANDARD_DELIVERY, PICK_UP).contains(shpgSvcTpCd)) {

            // Ship To State Code
            if (!hasValue(param.shipToStCd)) {
                msgMap.addXxMsgId(NWZM0331E);
                return;
            }

            // get 'longest transportation lead time within the SSL'.
            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd",   glblCmpyCd);
            ssmParam.put("slsDt",        param.slsDt.getValue());
            ssmParam.put("invtyLocCd",   param.invtyLocCd.getValue());
            ssmParam.put("shipToStCd",   param.shipToStCd.getValue());
            if (hasValue(param.shipToPostCd)) {
                ssmParam.put("shipToPostCd", param.shipToPostCd.getValue());
            }

            // TRNSP_LT
            TRNSP_LTTMsg trnspLtTMsg = DataCacheForSSM.getInstance().getLongestTrnspLeadTm(ssmParam, ssmClient);

            // AREA_LEAD_TM
            AREA_LEAD_TMTMsg areaLeadTmTMsg = DataCacheForSSM.getInstance().getLongestAreaLeadTm(ssmParam, ssmClient);

            if (trnspLtTMsg == null && areaLeadTmTMsg == null) {
                msgMap.addXxMsgId(NWZM0667E);
                return;
            }

            // post code override.
            if (hasValue(param.shipToPostCd)) {
                if (trnspLtTMsg != null) {
                    trnspLtAot = trnspLtTMsg.trnspLtAot.getValue();
                    shpgModeCd = trnspLtTMsg.shpgModeCd.getValue();
                }
            }

            if (trnspLtAot == null) {

                if (trnspLtTMsg != null && areaLeadTmTMsg != null) {
                    // TRNSP_LT >= AREA_LEAD_TM
                    if (nullToZero(trnspLtTMsg.trnspLtAot.getValue()).compareTo(nullToZero(areaLeadTmTMsg.delyLeadAot.getValue())) >= 0) {
                        trnspLtAot = trnspLtTMsg.trnspLtAot.getValue();
                        shpgModeCd = trnspLtTMsg.shpgModeCd.getValue();
                    // TRNSP_LT < AREA_LEAD_TM
                    } else {
                        trnspLtAot = areaLeadTmTMsg.delyLeadAot.getValue();
                        shpgModeCd = areaLeadTmTMsg.shpgModeCd.getValue();
                    }

                } else if (trnspLtTMsg != null) {
                    trnspLtAot = trnspLtTMsg.trnspLtAot.getValue();
                    shpgModeCd = trnspLtTMsg.shpgModeCd.getValue();

                } else if (areaLeadTmTMsg != null) {
                    trnspLtAot = areaLeadTmTMsg.delyLeadAot.getValue();
                    shpgModeCd = areaLeadTmTMsg.shpgModeCd.getValue();
                }
            }

        /**********************************************************************
         * SHPG_SVC_TP_CD = Illegal
         **********************************************************************/
        } else {
            msgMap.addXxMsgId(NWZM0661E);
            return;
        }

        // Get Retail Warehouse Code from Inventory location Code
        String rtlWhCd = getRtlWhCdByInvtyLocCd(param.glblCmpyCd.getValue(), param.invtyLocCd.getValue());

        // get [WH_LEAD_TM]
        final WH_LEAD_TMTMsg whLeadTmTMsg = getWhLeadTm(param, nullToEmpty(shpgModeCd), rtlWhCd);
        if (whLeadTmTMsg == null) {
            msgMap.addXxMsgId(NWZM0667E);
            return;
        }

        // calculate WSD, PSD, PDD
        execDatePlanning(
                msgMap,
                whLeadTmTMsg,
                nullToZero(trnspLtAot).intValue(),
                rtlWhCd
        );
    }

    /**
     * execute Date Planning. (WSD, PSD and PDD)
     * 
     * @param msgMap            S21ApiMessageMap
     * @param whLeadTmTMsg      WH_LEAD_TMTMsg
     * @param trnspLtAot        transportation lead time
     * @param rtlWhCd           String
     */
    private void execDatePlanning(S21ApiMessageMap msgMap, WH_LEAD_TMTMsg whLeadTmTMsg, int trnspLtAot, String rtlWhCd) {
        final String methodNm = "execDatePlanning";
        debugMethodStart(getClass(), methodNm);

        try {

            NWZC002001PMsg param = (NWZC002001PMsg) msgMap.getPmsg();
            final String glblCmpyCd = param.glblCmpyCd.getValue();

            // Business Calendar Type Code
            final String bizCalTpCd = getBizCalTpCd(msgMap, rtlWhCd);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // --------------------------------------------------------------------------------
            // start Date Planning (WSD, PSD, PDD)
            // --------------------------------------------------------------------------------
            final int whLtAot = getWhLtAot(whLeadTmTMsg, param);
            if (whLtAot < 0) {
                msgMap.addXxMsgId(NWZM1246E);
                return;
            }

            String xxWsdDt = null;
            String xxPsdDt = null;
            String xxPddDt = null;

            switch (getCalcPtn(param)) {

                /**************************************************
                 * Pattern - 1
                 **************************************************/
                case PTN_1:

                    final String sysTime = getSysTime();
                    String xxRwsdDt = param.xxRwsdDt.getValue();

                    // [WSD]
                    if (isBizDay(glblCmpyCd, bizCalTpCd, xxRwsdDt)) {
                        xxWsdDt = param.xxRwsdDt.getValue();

                        if (ZYPDateUtil.compare(sysTime, xxWsdDt) == 0) {

                            // In the case of the day, consider closing time and the case after time the next day.
                            final int shpgAbleDays;
                            if (getSysTimeHHmm(sysTime).compareTo(whLeadTmTMsg.shpgCloTmTs.getValue()) > 0) {
                                shpgAbleDays = 1;
                            } else {
                                shpgAbleDays = 0;
                            }
                            xxWsdDt = addDays(xxWsdDt, shpgAbleDays);

                            if (!isBizDay(glblCmpyCd, bizCalTpCd, xxWsdDt)) {
                                xxWsdDt = addBizDay(glblCmpyCd, bizCalTpCd, xxWsdDt, 1);
                            }
                        }

                    } else {
                        // When there is not xxRwsdDt by business day
                        String prevBizDay = addBizDay(glblCmpyCd, bizCalTpCd, xxRwsdDt, -1);
                        String nextBizDay = addBizDay(glblCmpyCd, bizCalTpCd, xxRwsdDt, 1);
                        if (ZYPDateUtil.compare(prevBizDay, sysTime) <= 0) {
                            xxWsdDt = nextBizDay;
                        } else {
                            xxWsdDt = prevBizDay;
                        }
                    }

                    // [PSD]
                    xxPsdDt = addBizDay(glblCmpyCd, bizCalTpCd, xxWsdDt, toDays(whLtAot));

                    // [PDD]
                    xxPddDt = addDays(xxPsdDt, toDays(trnspLtAot));
                    break;

                /**************************************************
                 * Pattern - 2
                 **************************************************/
                case PTN_2:

                    // [PSD]
                    String xxRsdDt = param.xxRsdDt.getValue();
                    if (isBizDay(glblCmpyCd, bizCalTpCd, xxRsdDt)) {
                        xxPsdDt = xxRsdDt;
                    } else {
                        xxPsdDt = addBizDay(glblCmpyCd, bizCalTpCd, xxRsdDt, 1);
                    }

                    // [WSD]
                    xxWsdDt = addBizDay(glblCmpyCd, bizCalTpCd, xxPsdDt, -1 * toDays(whLtAot));

                    // [PDD]
                    xxPddDt = addDays(xxPsdDt, toDays(trnspLtAot));
                    break;

                /**************************************************
                 * Pattern - 3
                 **************************************************/
                case PTN_3:

                    // [PDD]
                    xxPddDt = param.xxRddDt.getValue();

                    // [PSD]
                    xxPsdDt = addDays(xxPddDt, -1 * toDays(trnspLtAot));
                    if (!isBizDay(glblCmpyCd, bizCalTpCd, xxPsdDt)) {
                        xxPsdDt = addBizDay(glblCmpyCd, bizCalTpCd, xxPsdDt, -1);
                    }

                    // [WS]
                    xxWsdDt = addBizDay(glblCmpyCd, bizCalTpCd, xxPsdDt, -1 * toDays(whLtAot));
                    break;

                default:
                    break;
            }

            // set WSD/PSD/PDD
            setValue(param.xxWsdDt, xxWsdDt);
            setValue(param.xxPsdDt, xxPsdDt);
            setValue(param.xxPddDt, xxPddDt);

            if (isDebug()) {
                outInfoLog(getClass(), "### SHPG_SVC_LVL_CD:[", param.shpgSvcLvlCd.getValue(), "], MDSE_CD:[", param.mdseCd.getValue(), "], INVTY_LOC_CD:[", param.invtyLocCd.getValue(), "], SHIP_TO_ST_CD:[", param.shipToStCd.getValue(), "], SHIP_TO_POST_CD:[", param.shipToPostCd.getValue(), "]");
                outInfoLog(getClass(), "### whLtAot:[", whLtAot, "], trnspLtAot:[", trnspLtAot, "]");
                outInfoLog(getClass(), "    + RWSD:[", param.xxRwsdDt.getValue(), "] => WSD:[", xxWsdDt, "]");
                outInfoLog(getClass(), "    +  RSD:[", param.xxRsdDt.getValue(),  "] => PSD:[", xxPsdDt, "]");
                outInfoLog(getClass(), "    +  RDD:[", param.xxRddDt.getValue(),  "] => PDD:[", xxPddDt, "]");
            }

        } finally {
            debugMethodEnd(getClass(), methodNm);
        }
    }

    private static void debug(Class caller, Object... debugInfos) {
        if (isDebug()) {
            final StringBuilder sb = new StringBuilder(128);
            sb.append("<<").append(caller.getName()).append(">> ");
            for (Object debugInfo : debugInfos) {
                sb.append(debugInfo);
            }
            EZDDebugOutput.println(1, sb.toString(), null);
        }
    }

    private static void debugMethodEnd(Class caller, String methodNm) {
        debug(caller, new StringBuilder().append("[E N D]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static void debugMethodStart(Class caller, String methodNm) {
        debug(caller, new StringBuilder().append("[START]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static String getBizCalTpCd(S21ApiMessageMap msgMap, String rtlWhCd) {

        NWZC002001PMsg param = (NWZC002001PMsg) msgMap.getPmsg();

        CAL_RELNTMsg calReln = CalRelnCache.getInstance().getTMsgByKey(param.glblCmpyCd.getValue(), CAL_SUB_TP.WAREHOUSE_CALENDAR, rtlWhCd);

        final String calTpCd;

        if (calReln != null) {
            calTpCd = calReln.calTpCd.getValue();

        } else {

            final FindCondition condition = new FindCondition("001");
            condition.addCondition("glblCmpyCd01", param.glblCmpyCd.getValue());
            condition.addCondition("calSubTpCd01", CAL_SUB_TP.COMPANY_CALENDAR);
            final CAL_RELNTMsgArray calRelnArray = CalRelnCache.getInstance().getTMsgArray(condition);

            if (calRelnArray.getValidCount() != 1) {
                // When acquire more than two cases, process is finished as master setting error.
                msgMap.addXxMsgId(NWZM0673E);
                return null;
            }

            calTpCd = calRelnArray.no(0).calTpCd.getValue();
        }

        return calTpCd;
    }

    private static CalcPtn getCalcPtn(NWZC002001PMsg param) {

        if (hasValue(param.xxRwsdDt)) {
            return CalcPtn.PTN_1;

        } else if (hasValue(param.xxRsdDt)) {
            return CalcPtn.PTN_2;

        } else if (hasValue(param.xxRddDt)) {
            return CalcPtn.PTN_3;

        } else {
            return CalcPtn.ILLEGAL;
        }
    }

    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }


    private static ORD_TAKE_MDSETMsg getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        return NWXOrdTakeMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    private static SHIP_FROM_LOC_LIST_VTMsgArray getShipFromLocListV(S21ApiMessageMap msgMap) {

        NWZC002001PMsg param = (NWZC002001PMsg) msgMap.getPmsg();

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String invtyLocCd = param.invtyLocCd.getValue();

        final FindCondition condition = new FindCondition("002");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("invtyLocCd01", invtyLocCd);
        condition.addCondition("invtyLocTpCd01", LOC_TP.VENDOR);

        return ShipFromLocListViewCache.getInstance().getTMsgArray(condition);
    }

    private static String getSysTime() {

        return ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
    }

    private static String getSysTimeHHmm(String sysTime) {

        if (sysTime == null || sysTime.length() < 12) {
            sysTime = getSysTime();
        }

        return sysTime.substring(8, 12);
    }

    private static WH_LEAD_TMTMsg getWhLeadTm(NWZC002001PMsg param, String shpgModeCd, String rtlWhCd) {

        String glblCmpyCd   = param.glblCmpyCd.getValue();
        String shpgSvcLvlCd = param.shpgSvcLvlCd.getValue();
        String slsDt        = param.slsDt.getValue();

        final FindCondition condition = new FindCondition("001");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("whCd01", rtlWhCd);
        condition.addCondition("shpgSvcLvlCd01", shpgSvcLvlCd);
        condition.addCondition("shpgModeCd01", shpgModeCd);
        condition.addCondition("effFromDt01", slsDt);
        condition.addCondition("effThruDt01", slsDt);

        final WH_LEAD_TMTMsgArray whLeadTmTMsgArray =  WhLeadTmCache.getInstance().getTMsgArray(condition);

        if (whLeadTmTMsgArray.getValidCount() > 0) {
            return whLeadTmTMsgArray.no(0);
        } else {
            return null;
        }
    }

    private static BigDecimal nullToZero(BigDecimal bc) {

        if (bc == null) {
            return ZERO;
        }
        return bc;
    }

    private static String nullToEmpty(String str) {

        if (str == null) {
            return "";
        }
        return str;
    }

    private static void setDefaultPlanedDate(NWZC002001PMsg param) {

        switch (getCalcPtn(param)) {

            case PTN_1:
                final String rwsd = param.xxRwsdDt.getValue();
                setValue(param.xxWsdDt, rwsd);
                setValue(param.xxPsdDt, rwsd);
                setValue(param.xxPddDt, rwsd);
                break;

            case PTN_2:
                final String rsd = param.xxRsdDt.getValue();
                setValue(param.xxWsdDt, rsd);
                setValue(param.xxPsdDt, rsd);
                setValue(param.xxPddDt, rsd);
                break;

            case PTN_3:
                final String rdd = param.xxRddDt.getValue();
                setValue(param.xxWsdDt, rdd);
                setValue(param.xxPsdDt, rdd);
                setValue(param.xxPddDt, rdd);
                break;

            default:
                break;
        }
    }

    private static int toDays(int ltAot) {

        return ltAot / DAY_OF_HOUR;
    }

    private static void outInfoLog(Class< ? extends NWZC002001> clazz, Object... debugInfos) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<<").append(clazz.getName()).append(">>");
        for (Object debugInfo : debugInfos) {
            sb.append(debugInfo);
        }
        S21InfoLogOutput.println(sb.toString());
    }

    private static boolean isBizDay(String glblCmpyCd, String calTpCd, String yyyyMMdd) {

        return CalRelnCache.getInstance().isBizDay(glblCmpyCd, calTpCd, yyyyMMdd);
    }

    private static String addBizDay(String glblCmpyCd, String calTpCd, String yyyyMMdd, int amount) {

        try {
            // 2018/06/15 S21_NA#24294 Mod Start
            // return ZYPDateUtil.addBusinessDayEx(glblCmpyCd, calTpCd, yyyyMMdd, amount);
            return AddBizDayCache.getInstance().addBizDay(glblCmpyCd, calTpCd, yyyyMMdd, amount);
            // 2018/06/15 S21_NA#24294 Mod End
        } catch (S21AbendException e) {
            return yyyyMMdd;
        }
    }

    /**
     * Calculation Pattern.
     */
    private static enum CalcPtn {

        /** requested by 'RWSD' */
        PTN_1,

        /** requested by 'RSD' */
        PTN_2,

        /** requested by 'RDD' */
        PTN_3,

        /** ILLEGAL */
        ILLEGAL,
    }

    /**
     * Return Warehouse Lead Time
     * @param whLeadTmTMsg WH_LEAD_TMTMsg
     * @param param API Parameter
     * @return Warehouse Lead Time(Error : -1)
     */
    private int getWhLtAot(WH_LEAD_TMTMsg whLeadTmTMsg, NWZC002001PMsg param) {
        final int pickPackAot = nullToZero(whLeadTmTMsg.pickPackAot.getValue()).intValue();
        if (!ZYPCommonFunc.hasValue(param.configFlg)
                || !ZYPConstant.FLG_ON_Y.equals(param.configFlg.getValue())) {
            return pickPackAot;
        }

        // Get MDSE's CONFIG_LT_DAY
        final MDSETMsg prmMdse = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(prmMdse.mdseCd, param.mdseCd);
        ZYPEZDItemValueSetter.setValue(prmMdse.glblCmpyCd, param.glblCmpyCd);
        final MDSETMsg mdse = (MDSETMsg) S21FastTBLAccessor.findByKey(prmMdse);

        final BigDecimal configLtDay;
        if (mdse != null && ZYPCommonFunc.hasValue(mdse.configLtDayNum)) {
            // Get DS_MDSE_INFO.CONFIG_LT_DAY_NUM
            configLtDay = mdse.configLtDayNum.getValue();
        } else {
            // Get Default Value
            configLtDay = ZYPCodeDataUtil.getNumConstValue(CONST_KEY_DEFAULT_LT, param.glblCmpyCd.getValue());
            if (configLtDay == null) {
                return -1;
            }
        }

        // Day -> Time
        return (pickPackAot + configLtDay.intValue() * DAY_OF_HOUR);
    }

    /**
     * Get Retail Warehouse Code from Inventory Location Code
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return rtlWhCd String
     */
    private String getRtlWhCdByInvtyLocCd(String glblCmpyCd, String invtyLocCd) {
        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",   glblCmpyCd);
        ssmParam.put("invtyLocCd",   invtyLocCd);

        return DataCacheForSSM.getInstance().getRtlWhCdByInvtyLocCd(ssmParam, ssmClient);
    }
}
