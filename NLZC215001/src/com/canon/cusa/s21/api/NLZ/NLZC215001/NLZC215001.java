/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC215001;

import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.ASTERISK;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.DAY_LG;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.FIRST_DAY;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.MODE_RMA;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.MODE_VAL_SHRM;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.MTH_LG;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2079E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2087E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2088E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2259E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2260E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2261E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2262E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2263E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2264E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2265E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2267E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2268E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2269E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.NLZM2494E;
import static com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant.YR_LG;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DEF_SWH_BY_ITEMTMsg;
import business.db.DEF_SWH_BY_ITEMTMsgArray;
import business.db.DS_MDLTMsg;
import business.db.RTL_SWHTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC215001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC215001.Bean.NLZC215001Bean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/18   Fujitsu         T.Yoshida       Create          N/A
 * 2016/12/08   CITS            R.Shimamoto     Update          QC#13056
 * 2017/01/10   CITS            T.Kikuhara      Update          QC#16916
 * 2017/01/20   CITS            K.Ogino         Update          QC#17186
 * 2017/02/14   CITS            K.Ogino         Update          QC#17502
 * 2017/11/17   Hitachi         J.Kim           Update          QC#17088
 * 2018/04/18   CITS            K.Masaki        Update          QC#17857
 * 2018/05/11   CITS            K.Ogino         Update          QC#26039
 * 2018/05/16   CITS            T.Hakodate      Update          QC#25814
 *</pre>
 */
public class NLZC215001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;

    /**
     * Constructor
     */
    public NLZC215001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * execute (This can be called method from external class.)
     * @param pMsg NLZC215001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC215001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        msgMap = new S21ApiMessageMap(pMsg);

        doProcess(pMsg, onBatchType);

        msgMap.flush();
    }

    /**
     * One message in List is taken out,and executed.
     * @param pMsgList NLZC215001PMsg List
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NLZC215001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {

        for (NLZC215001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * Main process method.
     * @param pMsg NLZC215001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(NLZC215001PMsg pMsg, ONBATCH_TYPE onBatchType) {

        if (!checkInput(pMsg)) {
            return;
        }

        String procMode = pMsg.xxModeCd.getValue();
        if (MODE_RMA.equals(procMode)) {
            doProcessRmaMode(pMsg);
        } else {
            doProcessValShrmMode(pMsg);
        }
    }

    /**
     * Check Input Parameter
     * @param pMsg NLZC215001PMsg
     * @return No Error : true
     */
    private boolean checkInput(NLZC215001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NLZM2259E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NLZM2079E);
            return false;
        }

        String procMode = pMsg.xxModeCd.getValue();
        if (!ZYPCommonFunc.hasValue(procMode)) {
            msgMap.addXxMsgId(NLZM2087E);
            return false;
        } else {
            if (!MODE_RMA.equals(procMode) && !MODE_VAL_SHRM.equals(procMode)) {
                msgMap.addXxMsgId(NLZM2088E);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.mdseCd)) {
            msgMap.addXxMsgId(NLZM2260E);
            return false;
        }

        if (MODE_RMA.equals(procMode)) {
            if (!ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd)) {
                msgMap.addXxMsgId(NLZM2261E);
                return false;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(pMsg.srcRtlWhCd)) {
                msgMap.addXxMsgId(NLZM2262E);
                return false;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.destRtlWhCd)) {
            msgMap.addXxMsgId(NLZM2263E);
            return false;
        }

        return true;
    }

    /**
     * Main process for RMA Mode
     * @param pMsg NLZC215001PMsg
     */
    private void doProcessRmaMode(NLZC215001PMsg pMsg) {

        // QC#13056 Add.
        Map<String, Object> warehouseCategoryInfo = getWarehouseCategory(pMsg);
        if (warehouseCategoryInfo == null) {
            msgMap.addXxMsgId(NLZM2494E);
            return;
        }

        NLZC215001Bean dataBean = new NLZC215001Bean();
        dataBean.setInvtyOwnrCd((String) warehouseCategoryInfo.get("INVTY_OWNR_CD"));

        Map<String, String> mdseCatgInfo = getMdseCatgInfo(pMsg);
        if (mdseCatgInfo == null) {
            msgMap.addXxMsgId(NLZM2264E);
            return;
        }

        dataBean.setMdseCatgCd(mdseCatgInfo.get("MDSE_CATG_CD"));
        dataBean.setMdseItemActvDt(mdseCatgInfo.get("MDSE_ITEM_ACTV_DT"));

        // get SWH by Item
        getRtlSubWhCdByItem(pMsg, dataBean);
        if (!ZYPCommonFunc.hasValue(dataBean.getDestRtlSwhCd())) {
            // get SWH by Service Model
            getRtlSubWhCdBySvcMdl(pMsg, dataBean);
            if (!ZYPCommonFunc.hasValue(dataBean.getDestRtlSwhCd())) {
                msgMap.addXxMsgId(NLZM2267E);
                return;
            }
        }

        String destRtlSwhCd = dataBean.getDestRtlSwhCd();
        if (!checkWhMatching(pMsg, destRtlSwhCd)) {
            String[] msgParam = {pMsg.destRtlWhCd.getValue(), destRtlSwhCd };
            msgMap.addXxMsgIdWithPrm(NLZM2269E, msgParam);
            return;
        }

        // Set Out Parameter
        ZYPEZDItemValueSetter.setValue(pMsg.destRtlSwhCd, destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.thirdPtyDspTpCd, dataBean.getThirdPtyDspTpCd());
    }

    /**
     * Main process for Valuation Showroom Mode
     * @param pMsg NLZC215001PMsg
     */
    private void doProcessValShrmMode(NLZC215001PMsg pMsg) {

        String stkWhCatgCd = getRtlWhCatgCd(pMsg, pMsg.destRtlWhCd.getValue());
        if (!ZYPCommonFunc.hasValue(stkWhCatgCd)) {
            msgMap.addXxMsgId(NLZM2265E);
            return;
        }

        // START 2018/05/16 T.Hakodate[QC#25814,MOD]
//        String deryWhCatgCd = getRtlWhCatgCd(pMsg, pMsg.srcRtlWhCd.getValue());
//        if (!ZYPCommonFunc.hasValue(deryWhCatgCd)) {
//            msgMap.addXxMsgId(NLZM2266E);
        // return;
        // }
        String deryWhCatgCd = "";
        if (ZYPCommonFunc.hasValue(pMsg.srcRtlWhCd)) {
            deryWhCatgCd = getRtlWhCatgCd(pMsg, pMsg.srcRtlWhCd.getValue());
        }
        // END 2018/05/16 T.Hakodate[QC#25814,MOD]

        Map<String, String> mdseCatgInfo = getMdseCatgInfo(pMsg);
        if (mdseCatgInfo == null) {
            msgMap.addXxMsgId(NLZM2264E);
            return;
        }

        BigDecimal diffMonth = getDiffMonth(pMsg.slsDt.getValue(), mdseCatgInfo.get("MDSE_ITEM_ACTV_DT"));
        String destRtlSwhCd = getRtlSubWhCdByWhCatg(pMsg, stkWhCatgCd, deryWhCatgCd, diffMonth);
        if (!ZYPCommonFunc.hasValue(destRtlSwhCd)) {
            msgMap.addXxMsgId(NLZM2268E);
            return;
        }

        if (!checkWhMatching(pMsg, destRtlSwhCd)) {
            String[] msgParam = {pMsg.destRtlWhCd.getValue(), destRtlSwhCd };
            msgMap.addXxMsgIdWithPrm(NLZM2269E, msgParam);
            return;
        }

        // Set Out Parameter
        ZYPEZDItemValueSetter.setValue(pMsg.destRtlSwhCd, destRtlSwhCd);
    }

    /**
     * Get MDSE Category Infomation
     * @param pMsg NLZC215001PMsg
     * @return MDSE Category Infomation
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> getMdseCatgInfo(NLZC215001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());

        List<String> rgtnStsCdList = new ArrayList<String>();
        rgtnStsCdList.add(RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
        rgtnStsCdList.add(RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdList", rgtnStsCdList);

        return (Map<String, String>) ssmClient.queryObject("getMdseCatgInfo", ssmParam);
    }

    /**
     * Get Retail Sub Warehouse Code By Item
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     * @return Retail Sub Warehouse Code (By Item)
     */
    private void getRtlSubWhCdByItem(NLZC215001PMsg pMsg, NLZC215001Bean dataBean) {

        getRtlSubWhCdByMdseCatg(pMsg, dataBean);
        if (!ZYPCommonFunc.hasValue(dataBean.getDestRtlSwhCd())) {
            getRtlSubWhCdByOrdCatg(pMsg, dataBean);
            if (!ZYPCommonFunc.hasValue(dataBean.getDestRtlSwhCd())) {
                getRtlSubWhCdByMdseAndOrdCatg(pMsg, dataBean);
            }
        }
    }

    /**
     * Get Retail Sub Warehouse Code By MDSE Category
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     */
    private void getRtlSubWhCdByMdseCatg(NLZC215001PMsg pMsg, NLZC215001Bean dataBean) {

        final DEF_SWH_BY_ITEMTMsg condition = new DEF_SWH_BY_ITEMTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCatgCd01", dataBean.getMdseCatgCd());
        condition.setConditionValue("dsOrdCatgCd01", ASTERISK);
        condition.setConditionValue("effFromDt01", pMsg.slsDt.getValue());
        condition.setConditionValue("effThruDt01", pMsg.slsDt.getValue());
        DEF_SWH_BY_ITEMTMsgArray tmsgArray = (DEF_SWH_BY_ITEMTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            dataBean.setDestRtlSwhCd(tmsgArray.no(0).destRtlSwhCd.getValue());
            //QC#17857
            dataBean.setThirdPtyDspTpCd(tmsgArray.no(0).thirdPtyDspTpCd.getValue());
        }
    }

    /**
     * Get Retail Sub Warehouse Code By Order Category
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     */
    private void getRtlSubWhCdByOrdCatg(NLZC215001PMsg pMsg, NLZC215001Bean dataBean) {

        final DEF_SWH_BY_ITEMTMsg condition = new DEF_SWH_BY_ITEMTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCatgCd01", ASTERISK);
        condition.setConditionValue("dsOrdCatgCd01", pMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("effFromDt01", pMsg.slsDt.getValue());
        condition.setConditionValue("effThruDt01", pMsg.slsDt.getValue());
        DEF_SWH_BY_ITEMTMsgArray tmsgArray = (DEF_SWH_BY_ITEMTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            dataBean.setDestRtlSwhCd(tmsgArray.no(0).destRtlSwhCd.getValue());
            // START 2016/11/17 J.Kim [QC#17088,MOD]
            //// QC#13056 Add.
            // dataBean.setDestRtlSwhCd(tmsgArray.no(0).thirdPtyDspTpCd.getValue());
            dataBean.setThirdPtyDspTpCd(tmsgArray.no(0).thirdPtyDspTpCd.getValue());
            // EMD 2016/11/17 J.Kim [QC#17088,MOD]
        }
    }

    /**
     * Get Retail Sub Warehouse Code By MDSE Category And Order Category
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     */
    private void getRtlSubWhCdByMdseAndOrdCatg(NLZC215001PMsg pMsg, NLZC215001Bean dataBean) {

        final DEF_SWH_BY_ITEMTMsg condition = new DEF_SWH_BY_ITEMTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCatgCd01", dataBean.getMdseCatgCd());
        condition.setConditionValue("dsOrdCatgCd01", pMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("effFromDt01", pMsg.slsDt.getValue());
        condition.setConditionValue("effThruDt01", pMsg.slsDt.getValue());
        DEF_SWH_BY_ITEMTMsgArray tmsgArray = (DEF_SWH_BY_ITEMTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            dataBean.setDestRtlSwhCd(tmsgArray.no(0).destRtlSwhCd.getValue());
            //QC#17857
            dataBean.setThirdPtyDspTpCd(tmsgArray.no(0).thirdPtyDspTpCd.getValue());
        }
    }

    /**
     * Get Retail Sub Warehouse Code By Service Model
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     */
    private void getRtlSubWhCdBySvcMdl(NLZC215001PMsg pMsg, NLZC215001Bean dataBean) {

        //QC17857
        String istlDt = "";

        if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            //QC17857
            dataBean.setDestRtlSwhCd("U00");
            dataBean.setThirdPtyDspTpCd("");
            //getRtlSubWhCdByMdlAge(pMsg, dataBean);
            return;
        }

        // QC#17186
        SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(smmTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(smmTMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        smmTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(smmTMsg);

        if (smmTMsg != null) {
            //QC17857
            if (ZYPCommonFunc.hasValue(smmTMsg.istlDt)) {
                istlDt = smmTMsg.istlDt.getValue();
            }

            getMdlId(pMsg, dataBean, smmTMsg.svcConfigMstrPk.getValue());
            if (!ZYPCommonFunc.hasValue(dataBean.getMdlId())) {
                //QC17857
                getRtlSubWhCdByMdlAge(pMsg, dataBean, istlDt);
                //getRtlSubWhCdByMdlAge(pMsg, dataBean);
                return;
            }

            if (ZYPConstant.FLG_OFF_N.equals(getMtrReqMdlFlg(pMsg, dataBean.getMdlId()))) {
                //QC17857
                getRtlSubWhCdByMdlAge(pMsg, dataBean, istlDt);
                //getRtlSubWhCdByMdlAge(pMsg, dataBean);
                return;
            }

            getRtlSubWhCdByMtrCnt(pMsg, dataBean);
        } else {
            //QC17857
            getRtlSubWhCdByMdlAge(pMsg, dataBean, istlDt);
            //getRtlSubWhCdByMdlAge(pMsg, dataBean);
            return;
        }
    }

    /**
     * Get Retail Sub Warehouse Code By Model Age
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     */
    private void getRtlSubWhCdByMdlAge(NLZC215001PMsg pMsg, NLZC215001Bean dataBean, String istlDt) {

        //QC17857 Start
        String actvDt = null;

        if (istlDt == null) {
            actvDt = dataBean.getMdseItemActvDt();
        } else {
            actvDt = istlDt;
        }

        BigDecimal diffMonth = getDiffMonth(pMsg.slsDt.getValue(), actvDt);
        //BigDecimal diffMonth = getDiffMonth(pMsg.slsDt.getValue(), dataBean.getMdseItemActvDt());
        //QC17857 End

        getRtlSubWhCdByElpsMthAotOfMdl(pMsg, dataBean, diffMonth);
        if (!ZYPCommonFunc.hasValue(dataBean.getDestRtlSwhCd())) {
            getRtlSubWhCdByElpsMthAotOfUnidMdl(pMsg, dataBean, diffMonth);
        }
    }

    /**
     * Get Retail Sub Warehouse Code By Meter Count
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     */
    private void getRtlSubWhCdByMtrCnt(NLZC215001PMsg pMsg, NLZC215001Bean dataBean) {

        BigDecimal mtrCnt = getMtrCnt(pMsg);

        getRtlSubWhCdByMtrCntOfMdl(pMsg, dataBean, mtrCnt);
        if (!ZYPCommonFunc.hasValue(dataBean.getDestRtlSwhCd())) {
            getRtlSubWhCdByMtrCntOfUnidMdl(pMsg, dataBean, mtrCnt);
        }
    }

    /**
     * Get Meter Count
     * @param pMsg NLZC215001PMsg
     * @return Meter Count
     */
    private BigDecimal getMtrCnt(NLZC215001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        BigDecimal mtrCnt = (BigDecimal) ssmClient.queryObject("getMtrCntInfoList", ssmParam);

        if (mtrCnt == null) {
            return BigDecimal.ZERO;
        }

        return mtrCnt;
    }

    /**
     * Get Retail Sub Warehouse Code By Elapsed Monthly AOT Of Model
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     * @param diffMonth Difference between the two months specified
     */
    private void getRtlSubWhCdByElpsMthAotOfMdl(NLZC215001PMsg pMsg, NLZC215001Bean dataBean, BigDecimal diffMonth) {

        if (ZYPCommonFunc.hasValue(dataBean.getMdlId())) {
            // QC#13056 Mod.
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("mdlId", dataBean.getMdlId());
            ssmParam.put("fromElpsMthAot", diffMonth);
            ssmParam.put("toElpsMthAot", diffMonth);
            ssmParam.put("effFromDt", pMsg.slsDt.getValue());
            ssmParam.put("effThruDt", pMsg.slsDt.getValue());
            ssmParam.put("invtyOwnrCd", dataBean.getInvtyOwnrCd());

            Map<String, Object> result = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByElpsMthAotOfMdl", ssmParam);

            if (result != null) {
                dataBean.setDestRtlSwhCd((String) result.get("DEST_RTL_SWH_CD"));
                dataBean.setThirdPtyDspTpCd((String) result.get("THIRD_PTY_DSP_TP_CD"));

            } else {
                ssmParam.put("invtyOwnrCd", null);
                Map<String, Object> resultSec = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByElpsMthAotOfMdl", ssmParam);

                if (resultSec != null) {
                    dataBean.setDestRtlSwhCd((String) resultSec.get("DEST_RTL_SWH_CD"));
                    dataBean.setThirdPtyDspTpCd((String) resultSec.get("THIRD_PTY_DSP_TP_CD"));
                }
            }
        }
    }

    /**
     * Get Retail Sub Warehouse Code By Elapsed Monthly AOT Of Unidentified Model
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     * @param diffMonth Difference between the two months specified
     */
    private void getRtlSubWhCdByElpsMthAotOfUnidMdl(NLZC215001PMsg pMsg, NLZC215001Bean dataBean, BigDecimal diffMonth) {

        // QC#26039
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdlId", new BigDecimal(-1));
        ssmParam.put("fromElpsMthAot", diffMonth);
        ssmParam.put("toElpsMthAot", diffMonth);
        ssmParam.put("effFromDt", pMsg.slsDt.getValue());
        ssmParam.put("effThruDt", pMsg.slsDt.getValue());
        ssmParam.put("invtyOwnrCd", dataBean.getInvtyOwnrCd());

        Map<String, Object> result = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByElpsMthAotOfMdl", ssmParam);

        if (result != null) {
            dataBean.setDestRtlSwhCd((String) result.get("DEST_RTL_SWH_CD"));
            dataBean.setThirdPtyDspTpCd((String) result.get("THIRD_PTY_DSP_TP_CD"));

        } else {
            ssmParam.put("invtyOwnrCd", null);
            Map<String, Object> resultSec = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByElpsMthAotOfMdl", ssmParam);

            if (resultSec != null) {
                dataBean.setDestRtlSwhCd((String) resultSec.get("DEST_RTL_SWH_CD"));
                dataBean.setThirdPtyDspTpCd((String) resultSec.get("THIRD_PTY_DSP_TP_CD"));
            }
        }
    }

    /**
     * Get Retail Sub Warehouse Code By Meter Count Of Model
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     * @param mtrCnt Meter Count
     */
    private void getRtlSubWhCdByMtrCntOfMdl(NLZC215001PMsg pMsg, NLZC215001Bean dataBean, BigDecimal mtrCnt) {

        if (ZYPCommonFunc.hasValue(dataBean.getMdlId())) {
            // QC#13056 Mod.
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("mdlId", dataBean.getMdlId());
            ssmParam.put("fromMtrCnt01", mtrCnt);
            ssmParam.put("toMtrCnt01", mtrCnt);
            ssmParam.put("effFromDt", pMsg.slsDt.getValue());
            ssmParam.put("effThruDt", pMsg.slsDt.getValue());
            ssmParam.put("invtyOwnrCd", dataBean.getInvtyOwnrCd());

            Map<String, Object> result = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByMtrCntOfMdl", ssmParam);

            if (result != null) {
                dataBean.setDestRtlSwhCd((String) result.get("DEST_RTL_SWH_CD"));
                dataBean.setThirdPtyDspTpCd((String) result.get("THIRD_PTY_DSP_TP_CD"));

            } else {
                ssmParam.put("invtyOwnrCd", null);
                Map<String, Object> resultSec = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByMtrCntOfMdl", ssmParam);

                if (resultSec != null) {
                    dataBean.setDestRtlSwhCd((String) resultSec.get("DEST_RTL_SWH_CD"));
                    dataBean.setThirdPtyDspTpCd((String) resultSec.get("THIRD_PTY_DSP_TP_CD"));
                }
            }
        }
    }

    /**
     * Get Retail Sub Warehouse Code By Meter Count Of Unidentified Model
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     * @param mtrCnt Meter Count
     */
    private void getRtlSubWhCdByMtrCntOfUnidMdl(NLZC215001PMsg pMsg, NLZC215001Bean dataBean, BigDecimal mtrCnt) {

        // QC#26039
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdlId", new BigDecimal(-1));
        ssmParam.put("fromMtrCnt01", mtrCnt);
        ssmParam.put("toMtrCnt01", mtrCnt);
        ssmParam.put("effFromDt", pMsg.slsDt.getValue());
        ssmParam.put("effThruDt", pMsg.slsDt.getValue());
        ssmParam.put("invtyOwnrCd", dataBean.getInvtyOwnrCd());

        Map<String, Object> result = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByMtrCntOfMdl", ssmParam);

        if (result != null) {
            dataBean.setDestRtlSwhCd((String) result.get("DEST_RTL_SWH_CD"));
            dataBean.setThirdPtyDspTpCd((String) result.get("THIRD_PTY_DSP_TP_CD"));

        } else {
            ssmParam.put("invtyOwnrCd", null);
            Map<String, Object> resultSec = (Map<String, Object>) ssmClient.queryObject("getRtlSubWhCdByMtrCntOfMdl", ssmParam);

            if (resultSec != null) {
                dataBean.setDestRtlSwhCd((String) resultSec.get("DEST_RTL_SWH_CD"));
                dataBean.setThirdPtyDspTpCd((String) resultSec.get("THIRD_PTY_DSP_TP_CD"));
            }
        }
    }

    /**
     * Get Difference between the two months specified
     * @param slsDt Sales Date
     * @param actvDt Implementation Date
     * @return Difference between the two months specified
     */
    private BigDecimal getDiffMonth(String slsDt, String actvDt) {

        int diffCount = 0;

        if (ZYPCommonFunc.hasValue(actvDt)) {
            Calendar slsDtCal = getCalInstance(slsDt);
            Calendar mdseEzIntimeCal = getCalInstance(actvDt);

            if (slsDtCal.before(mdseEzIntimeCal)) {
                while (slsDtCal.before(mdseEzIntimeCal)) {
                    slsDtCal.add(Calendar.MONTH, 1);
                    diffCount--;
                }
            } else {
                diffCount--;
                while (!slsDtCal.before(mdseEzIntimeCal)) {
                    slsDtCal.add(Calendar.MONTH, -1);
                    diffCount++;
                }
            }

            if (diffCount != 0) {
                int slsDay = Integer.parseInt(slsDt.substring(MTH_LG, DAY_LG));
                int actvDay = Integer.parseInt(actvDt.substring(MTH_LG, DAY_LG));
                if (slsDay < actvDay) {
                    return new BigDecimal(diffCount - 1);
                }
            }
        }

        return new BigDecimal(diffCount);
    }

    /**
     * Get Calendar Instance
     * @param targetDt Target Date
     * @return Calendar Instance
     */
    private Calendar getCalInstance(String trgtDt) {

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(trgtDt.substring(0, YR_LG)), Integer.parseInt(trgtDt.substring(YR_LG, MTH_LG)), FIRST_DAY, 0, 0, 0);
        cal.set(Calendar.MILLISECOND,0);
        return cal;
    }

    /**
     * Get Model ID
     * @param pMsg NLZC215001PMsg
     * @param dataBean NLZC215001Bean
     * @param svcConfigMstrPk BigDecimal
     */
    private void getMdlId(NLZC215001PMsg pMsg, NLZC215001Bean dataBean, BigDecimal svcConfigMstrPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
            ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        } else {
            ssmParam.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        }

        dataBean.setMdlId((BigDecimal) ssmClient.queryObject("getMdlId", ssmParam));
    }

    /**
     * Get Meter Required Model Flag
     * @param pMsg NLZC215001PMsg
     * @param mdlId Model ID
     * @return Meter Required Model Flag
     */
    private String getMtrReqMdlFlg(NLZC215001PMsg pMsg, BigDecimal mdlId) {

        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
        dsMdlTMsg = (DS_MDLTMsg) S21ApiTBLAccessor.findByKey(dsMdlTMsg);

        if (dsMdlTMsg != null) {
            return dsMdlTMsg.mtrReqMdlFlg.getValue();

        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * Check WH Matching
     * @param pMsg NLZC215001PMsg
     * @param destRtlSwhCd Retail Sub Warehouse Code
     * @return No Error:true
     */
    private boolean checkWhMatching(NLZC215001PMsg pMsg, String destRtlSwhCd) {

        RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlWhCd, pMsg.destRtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtlSwhTMsg.rtlSwhCd, destRtlSwhCd);
        rtlSwhTMsg = (RTL_SWHTMsg) S21ApiTBLAccessor.findByKey(rtlSwhTMsg);

        if (rtlSwhTMsg != null) {
            return true;
        }

        return false;
    }

    /**
     * Get WH Category Code
     * @param pMsg NLZC215001PMsg
     * @param whCd WH Code
     * @return WH Category Code
     */
    private String getRtlWhCatgCd(NLZC215001PMsg pMsg, String whCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rtlWhCd", whCd);
        ssmParam.put("slsDt", pMsg.slsDt.getValue());

        return (String) ssmClient.queryObject("getRtlWhCatgCd", ssmParam);
    }

    /**
     * Get Retail Sub Warehouse Code By WH Category
     * @param pMsg NLZC215001PMsg
     * @param stkWhCatgCd Stock WH Category Code
     * @param deryWhCatgCd Delivery WH Category Code
     * @param diffMonth Difference between the two months specified
     * @return Retail Sub Warehouse Code (By WH Category)
     */
    private String getRtlSubWhCdByWhCatg(NLZC215001PMsg pMsg, String stkWhCatgCd, String deryWhCatgCd, BigDecimal diffMonth) {

       // START 2018/05/16 T.Hakodate[QC#25814,MOD]
//        final DEF_SWH_BY_WH_CATGTMsg condition = new DEF_SWH_BY_WH_CATGTMsg();
//        condition.setSQLID("001");
//        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//        condition.setConditionValue("srcRtlWhCatgCd01", deryWhCatgCd);
//        condition.setConditionValue("destRtlWhCatgCd01", stkWhCatgCd);
//        condition.setConditionValue("fromElpsMthAot01", diffMonth);
//        condition.setConditionValue("toElpsMthAot01", diffMonth);
//        condition.setConditionValue("effFromDt01", pMsg.slsDt.getValue());
//        condition.setConditionValue("effThruDt01", pMsg.slsDt.getValue());
//        DEF_SWH_BY_WH_CATGTMsgArray tmsgArray = (DEF_SWH_BY_WH_CATGTMsgArray) EZDTBLAccessor.findByCondition(condition);
//
        // if (tmsgArray.getValidCount() > 0) {
        // return tmsgArray.no(0).destRtlSwhCd.getValue();
        // }
        boolean searchWithDefault = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());

        if (ZYPCommonFunc.hasValue(deryWhCatgCd)) {
            ssmParam.put("srcRtlWhCatgCd", deryWhCatgCd);
        } else {
            ssmParam.put("srcRtlWhCatgCd", "*");
        }
        ssmParam.put("destRtlWhCatgCd", stkWhCatgCd);
        ssmParam.put("fromElpsMthAot", diffMonth);
        ssmParam.put("toElpsMthAot", diffMonth);
        ssmParam.put("effThruDt", pMsg.slsDt.getValue());
        ssmParam.put("effFromDt", pMsg.slsDt.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());

        // set mdlGrpId from pmsg.
        if (ZYPCommonFunc.hasValue(pMsg.mdlGrpId)) {
            // Search LOB
            Map<String, Object> prchGrpCd = (Map<String, Object>) ssmClient.queryObject("getPrchGrpCd", ssmParam);

            if (prchGrpCd == null || !ZYPCommonFunc.hasValue((String) prchGrpCd.get("PRCH_GRP_CD")) || LINE_BIZ_TP.ESS.equals((String) prchGrpCd.get("PRCH_GRP_CD"))) {
                // ESS
                ssmParam.put("mdlGrpId", new BigDecimal(-1));

            } else {
                // LFS PPS
                ssmParam.put("mdlGrpId", pMsg.mdlGrpId.getValue());
                searchWithDefault = true;
            }

        } else {

            ssmParam.put("mdlGrpId", new BigDecimal(-1));
        }

        // search with deryWhCatgCd
        Map<String, Object> destRtlSwh = (Map<String, Object>) ssmClient.queryObject("getDefaultSwhByCategory", ssmParam);

        if (destRtlSwh != null) {

            return (String) destRtlSwh.get("DEST_RTL_SWH_CD");

        } else {

            // search with deryWhCatgCd [*]
            ssmParam.put("srcRtlWhCatgCd", "*");
            destRtlSwh = (Map<String, Object>) ssmClient.queryObject("getDefaultSwhByCategory", ssmParam);

            if (destRtlSwh != null) {
                return (String) destRtlSwh.get("DEST_RTL_SWH_CD");
            }
        }

        // research with deryWhCatgCd & mdlGrpId [-1]
        if (ZYPCommonFunc.hasValue(deryWhCatgCd) && searchWithDefault) {

            ssmParam.put("srcRtlWhCatgCd", deryWhCatgCd);
            ssmParam.put("mdlGrpId", new BigDecimal(-1));
            Map<String, Object> destRtlSwhSec = (Map<String, Object>) ssmClient.queryObject("getDefaultSwhByCategory", ssmParam);

            if (destRtlSwhSec != null) {
                
                return (String) destRtlSwhSec.get("DEST_RTL_SWH_CD");
                
            } else {

                // search with deryWhCatgCd [*]
                ssmParam.put("srcRtlWhCatgCd", "*");
                destRtlSwh = (Map<String, Object>) ssmClient.queryObject("getDefaultSwhByCategory", ssmParam);

                if (destRtlSwh != null) {
                    return (String) destRtlSwh.get("DEST_RTL_SWH_CD");
                }
            }
        }

        return null;
        // END 2018/05/16 T.Hakodate[QC#25814,MOD]

    }

    /**
     * QC#13056 Add. Get Warehouse Category
     * @param pMsg NLZC215001PMsg
     * @return Warehouse Category Info
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getWarehouseCategory(NLZC215001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("rtlWhCd", pMsg.destRtlWhCd.getValue());

        return (Map<String, Object>) ssmClient.queryObject("getWarehouseCategory", ssmParam);
    }
}
