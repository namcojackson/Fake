/**
 * <pre>
 * CPO Update API Set Default Data
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2019/09/27   Fujitsu         R.Matsuki       Update          QC#53593
 * 2021/02/04   CITS            K.Ogino         Update          QC#58230
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import business.db.CAL_RELNTMsg;
import business.db.MDSETMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.parts.NWZC002001PMsg;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC002001.NWZC002001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouBizDayCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouExistsCdInDbCheck;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouValidCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

public class NWZC150001CpouSetExpdShipDt {

    /** Clas Name */
    private static final String CLASS_NM = "NWZC150001CpouSetExpdShipDt";
    /**
     * Data setting After check
     * 
     * <pre>
     * The setting of necessary data is executed beforehand.
     * </pre>
     * @param cpo NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     */
    // 2019/09/27 QC#53593 MOD START
//    public static void setExpdShipDt(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache, ONBATCH_TYPE onBatchType) {
    public static void setExpdShipDt(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, NWZC150001CpouLocalCache localCache, ONBATCH_TYPE onBatchType, String slsDt) {
    // 2019/09/27 QC#53593 MOD END
        final String methodNm = "setExpdShipDt";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(CLASS_NM, methodNm);

        try {

            // Map<"CPO_DTL_LINE_NUM" of Parent Line, List<Component
            // CpoDtlBeans>>
            final Map<String, List<NWZC150001CpouDetailBean>> cmptCpoDtlListMap = new HashMap<String, List<NWZC150001CpouDetailBean>>();

            final ListIterator<NWZC150001CpouDetailBean> it = cpouBean.getDtlBeanList().listIterator();
            while (it.hasNext()) {

                final NWZC150001CpouDetailBean cpoDtl = it.next();

                // 'Cancel' requested line
                if (ZYPConstant.FLG_ON_Y.equals(cpoDtl.getCpoDtlCancFlg())) {
                    continue;
                }
                // Fixed status line
                if (asList(ORD_LINE_STS.SHIPPED, ORD_LINE_STS.CLOSED, ORD_LINE_STS.CANCELLED).contains(cpoDtl.getOrdLineStsCd())) {
                    continue;
                }
                // Intangible, Vendor Drop
                if (ZYPConstant.FLG_ON_Y.equals(cpoDtl.getIntgFlg()) || ZYPConstant.FLG_ON_Y.equals(cpoDtl.getThirdPtyVndDropYFlg())) {
                    continue;
                }

                // RSD/ESD/RDD
                String rsd = cpoDtl.getRsdDt();
                String esd = cpoDtl.getExpdShipDt();
                final String rdd = cpoDtl.getRddDt();

                // inputed 'RSD' is Business Day?
                if (hasValue(rsd)) {
                    final String bizRsd = adjustBizDay(cpoDtl, rsd, localCache);
                    if (!bizRsd.equals(rsd)) {
                        rsd = bizRsd;
                        cpoDtl.setRsdDt(rsd);
                        if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtl.getCpoDtlLineSubNum())) {
                            int previousCnt = 0;
                            while (it.hasNext()) {
                                final NWZC150001CpouDetailBean cmptCpoDtl = it.next();
                                if (cmptCpoDtl.getCpoDtlLineNum().equals(cpoDtl.getCpoDtlLineNum())) {
                                    cmptCpoDtl.setRsdDt(rsd);
                                    previousCnt++;
                                } else {
                                    // Def#2016 add start -->
                                    previousCnt++;
                                    // Def#2016 add end <--
                                    break;
                                }
                            }
                            while (previousCnt-- > 0) {
                                it.previous();
                            }
                        }
                    }
                }

                // Flg : call [NWZC002001] : Lead Time Calculation API
                boolean callCalcLtAPI = false;
                if (!NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtl.getCpoDtlLineSubNum())) {
                    if (hasValue(rsd) && hasValue(rdd)) {
                        callCalcLtAPI = true;
                    } else if (hasValue(rdd)) {
                        callCalcLtAPI = true;
                    } else if (hasValue(rsd)) {
                        esd = rsd;
                    }
                }

                if (callCalcLtAPI) {

                    // isn't 'US'
                    if (NWZC150001CpouExistsCdInDbCheck.isExportForCtry(cpouBean.getGlblCmpyCd(), cpoDtl.getShipToCtryCd(), localCache)) {

                        // [SHPG_SVC_LVL]
                        SHPG_SVC_LVLTMsg shpgSvcLvlTMsg = new SHPG_SVC_LVLTMsg();
                        setValue(shpgSvcLvlTMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                        setValue(shpgSvcLvlTMsg.shpgSvcLvlCd, cpoDtl.getShpgSvcLvlCd());
                        shpgSvcLvlTMsg = (SHPG_SVC_LVLTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(shpgSvcLvlTMsg);

                        // needs shipToStCd? (0:Ground Standard
                        // Delivery, 1:Pick Up)
                        boolean needsShipToStCd = false;
                        if (shpgSvcLvlTMsg != null) {
                            needsShipToStCd = asList(SHPG_SVC_TP.GROUND_STANDARD_DELIVERY, SHPG_SVC_TP.PICK_UP).contains(shpgSvcLvlTMsg.shpgSvcTpCd.getValue());
                        }

                        if (needsShipToStCd) {

                            // State
                            final String shipToStCd = cpoDtl.getShipToStCd();
                            if (!hasValue(shipToStCd)) {
                                callCalcLtAPI = false;
                            } else if (!NWZC150001CpouExistsCdInDbCheck.existsShipToSt(cpouBean.getGlblCmpyCd(), shipToStCd)) {
                                callCalcLtAPI = false;
                            }

                            if (!callCalcLtAPI) {
                                // RSD
                                if (!hasValue(rsd)) {
                                    rsd = cpouBean.getSlsDt();
                                }
                                esd = rsd;
                            }
                        }
                    }
                }

                // --------------------------------------------------
                // [NWZC002001] : Lead Time Calculation API
                // --------------------------------------------------
                boolean hasError = false;

                // QC#58230
                String prodCondCd = cpoDtl.getProdCondCd();
                String cpoSrcTpCd = cpouBean.getCpoSrcTpCd();
                if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd) && ZYPCommonFunc.hasValue(prodCondCd) && !ZYPCommonFunc.hasValue(cpoDtl.getInvtyLocCd())) {
                    callCalcLtAPI = false;
                }
                if (callCalcLtAPI) {

                    final NWZC002001PMsg apiPMsg = new NWZC002001PMsg();
                    setValue(apiPMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                    setValue(apiPMsg.mdseCd, cpoDtl.getMdseCd());
                    setValue(apiPMsg.ordQty, cpoDtl.getOrdQty());
                    setValue(apiPMsg.shpgSvcLvlCd, cpoDtl.getShpgSvcLvlCd());
                    setValue(apiPMsg.frtChrgToCd, cpoDtl.getFrtChrgToCd());
                    setValue(apiPMsg.frtChrgMethCd, cpoDtl.getFrtChrgMethCd());
                    setValue(apiPMsg.xxRddDt, rdd);
                    setValue(apiPMsg.invtyLocCd, cpoDtl.getInvtyLocCd());
                    setValue(apiPMsg.shipToPostCd, cpoDtl.getShipToPostCd());
                    setValue(apiPMsg.shipToCustCd, cpoDtl.getShipToCustCd());
                    setValue(apiPMsg.shipToStCd, cpoDtl.getShipToStCd());
                    setValue(apiPMsg.sellToCustCd, cpouBean.getSellToCustCd());
                    setValue(apiPMsg.uomCd, cpoDtl.getCustUomCd());
                    // 2019/09/27 QC#53593 MOD START
//                    setValue(apiPMsg.slsDt, cpouBean.getSlsDt());
                    setValue(apiPMsg.slsDt, slsDt);
                    // 2019/09/27 QC#53593 MOD END

                    new NWZC002001().execute(apiPMsg, onBatchType);

                    // has Error.
                    if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                        hasError = true;
                        final int cpoDtlIndex = it.nextIndex() - 1;
                        for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                            NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue(), resPMsgList, cpoDtlIndex);
                        }
                    } else {
                        // ESD = RDD - a longest transportation lead
                        // time within the SSL
                        esd = apiPMsg.xxPsdDt.getValue();
                        // RSD = RDD - a longest transportation lead
                        // time within the SSL -
                        // mercahndise.allocation lead time
                        rsd = apiPMsg.xxPsdDt.getValue();
                        final MDSETMsg mdse = NWZC150001CpouExistsCdInDbCheck.getMdse(apiPMsg.glblCmpyCd.getValue(), apiPMsg.mdseCd.getValue());
                        if (mdse != null) {
                            int daysPriAllocNum = NWZC150001Common.nullToZero(mdse.daysPriAllocNum.getValue()).intValue();
                            if (daysPriAllocNum > 0) {
                                rsd = ZYPDateUtil.addDays(esd, -1 * daysPriAllocNum);
                                rsd = adjustBizDay(cpoDtl, rsd, localCache);
                            }
                        }
                    }
                }

                // set RSD/ESD/RDD
                if (!hasError) {

                    // RSD/ESD/RDD
                    cpoDtl.setRsdDt(rsd);
                    cpoDtl.setExpdShipDt(esd);
                    cpoDtl.setRddDt(rdd);

                    NWZC150001CpouLogWriter.writePerformanceProfilingLog(CLASS_NM, " # CPO_ORD_NUM=[" + cpoDtl.getCpoOrdNum() + "] CPO_DTL_LINE_NUM=[" + cpoDtl.getCpoDtlLineNum() + "] CPO_DTL_LINE_SUB_NUM=[" + cpoDtl.getCpoDtlLineSubNum() + "] : RSD=["
                            + cpoDtl.getRsdDt() + "], EXPD_SHIP_DT=[" + cpoDtl.getExpdShipDt() + "], RDD=[" + cpoDtl.getRddDt() + "]");

                    // Component
                    if (hasValue(cpoDtl.getSetMdseCd())) {
                        final String key = cpoDtl.getCpoDtlLineNum();
                        if (!cmptCpoDtlListMap.containsKey(key)) {
                            cmptCpoDtlListMap.put(key, new ArrayList<NWZC150001CpouDetailBean>());
                        }
                        cmptCpoDtlListMap.get(key).add(cpoDtl);
                    }
                }
            }

            // RSD/ESD : Component => Parent
            for (Entry<String, List<NWZC150001CpouDetailBean>> entry : cmptCpoDtlListMap.entrySet()) {

                // find the firstest 'EXPD_SHIP_DT' within the
                // Components.
                final List<NWZC150001CpouDetailBean> cmptCpoDtlList = new ArrayList<NWZC150001CpouDetailBean>(entry.getValue());
                if (!cmptCpoDtlList.isEmpty()) {

                    Collections.sort(cmptCpoDtlList, new Comparator<NWZC150001CpouDetailBean>() {
                        public int compare(NWZC150001CpouDetailBean cpoDtl1, NWZC150001CpouDetailBean cpoDtl2) {
                            return NWZC150001Common.nullToEmpty(cpoDtl1.getExpdShipDt()).compareTo(NWZC150001Common.nullToEmpty(cpoDtl2.getExpdShipDt()));
                        }
                    });
                    final NWZC150001CpouDetailBean cmptCpoDtl = cmptCpoDtlList.get(0);

                    // set RSD/ESD to Parent.
                    for (NWZC150001CpouDetailBean cpoDtl : cpouBean.getDtlBeanList()) {
                        if (cmptCpoDtl.getCpoDtlLineNum().equals(cpoDtl.getCpoDtlLineNum())) {
                            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpoDtl.getCpoDtlLineSubNum())) {
                                cpoDtl.setRsdDt(cmptCpoDtl.getRsdDt());
                                cpoDtl.setExpdShipDt(cmptCpoDtl.getExpdShipDt());
                                break;
                            }
                        }
                    }
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(CLASS_NM, methodNm);
        }
    }

    private static String adjustBizDay(NWZC150001CpouDetailBean cpoDtlBean, String yyyyMMdd, NWZC150001CpouLocalCache localCache) {

        final String glblCmpyCd = cpoDtlBean.getGlblCmpyCd();

        // Illegal date.
        if (!hasValue(yyyyMMdd)) {
            return yyyyMMdd;
        }

        // // WAREHOUSE_CALENDAR
        // CAL_RELNTMsg calReln = new CAL_RELNTMsg();
        // setValue(calReln.glblCmpyCd, glblCmpyCd);
        // setValue(calReln.calSubTpCd,
        // CAL_SUB_TP.WAREHOUSE_CALENDAR);
        // setValue(calReln.calMultCd, cpoDtlBean.getInvtyLocCd());
        // calReln = (CAL_RELNTMsg) findByKeyWithCache(calReln);
        //
        // String calTpCd = null;
        //
        // if (calReln != null) {
        // calTpCd = calReln.calTpCd.getValue();
        //
        // } else {
        //
        // // COMPANY_CALENDAR
        // calReln = new CAL_RELNTMsg();
        // calReln.setSQLID("001");
        // calReln.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // calReln.setConditionValue("calSubTpCd01",
        // CAL_SUB_TP.COMPANY_CALENDAR);
        // calReln.setMaxCount(1);
        //
        // final CAL_RELNTMsgArray calRelnArray = (CAL_RELNTMsgArray)
        // findByCondition(calReln);
        //
        // if (calRelnArray.getValidCount() > 0) {
        // calTpCd = calRelnArray.no(0).calTpCd.getValue();
        // }
        // }
        //
        // if (hasValue(calTpCd)) {
        //
        // boolean isBizDay = false;
        // try {
        // isBizDay = ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd,
        // yyyyMMdd);
        // } catch (S21AbendException e) {
        // // nothing to do.
        // }
        //
        // if (!isBizDay) {
        // try {
        // yyyyMMdd = ZYPDateUtil.addBusinessDayEx(glblCmpyCd,
        // calTpCd, yyyyMMdd, -1);
        // } catch (S21AbendException e) {
        // // nothing to do.
        // }
        // }
        // }
        //
        // return yyyyMMdd;

        final NWZC150001CpouBizDayCache bizDayCache = localCache.bizDayCache;

        // WAREHOUSE_CALENDAR
        CAL_RELNTMsg calReln = bizDayCache.getCalRelnTMsg(glblCmpyCd, CAL_SUB_TP.WAREHOUSE_CALENDAR, cpoDtlBean.getInvtyLocCd());
        if (calReln == null) {
            // COMPANY_CALENDAR
            calReln = bizDayCache.getCalRelnTMsg(glblCmpyCd, CAL_SUB_TP.COMPANY_CALENDAR);
        }

        if (calReln == null) {
            return yyyyMMdd;
        }

        final String calTpCd = calReln.calTpCd.getValue();

        if (bizDayCache.isBizDay(glblCmpyCd, calTpCd, yyyyMMdd)) {
            return yyyyMMdd;
        } else {
            return bizDayCache.getBizDay(glblCmpyCd, calTpCd, yyyyMMdd);
        }
    }
}
