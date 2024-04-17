/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC101001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.RTL_WHTMsgArray;
import business.parts.NPZC101001PMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC101001.constant.NPZC101001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001GetMdseRelationshipData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_WRK_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WRK_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * NPZC1010:MRP Calculation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   CITS            T.Kikuhara      Create          N/A
 * 2016/08/01   CITS            Y.Nomura        Mode            QC#11764
 * 2016/11/21   CITS            Y.IWASAKI       Modify          QC#16084
 * 2016/12/02   CITS            S.Endo          Modify          QC#15429
 * 2016/12/14   CITS            T.Kikuhara      Modify          QC#14774
 * 2017/02/13   CITS            Y.Fujii         Modify          QC#17451
 * 2017/07/27   CITS            S.Katsuma       Update          QC#19656
 * 2017/08/09   CITS            S.Katsuma       Update          QC#20543
 * 2017/09/01   CITS            T.Wada          Update          QC#20833
 * 2017/10/04   CITS            S.Katsuma       Update          QC#21429
 * 2017/10/24   CITS            K.Ogino         Update          QC#21987
 * 2017/10/27   CITS            T.Tokutomi      Update          QC#22111
 * 2017/11/06   CITS            T.Tokutomi      Update          QC#18401-Sol#014
 * 2017/12/27   CITS            T.Tokutomi      Update          QC#18401-Sol#014
 * 2018/01/17   CITS            T.Tokutomi      Update          QC#20832
 * 2018/03/07   CITS            T.Tokutomi      Update          QC#24580
 * 2018/05/30   CITS            T.Tokutomi      Update          QC#24814
 * 2018/06/21   CITS            T.Tokutomi      Update          QC#26532
 * 2018/06/21   CITS            T.Tokutomi      Update          QC#26554
 * 2018/06/22   CITS            T.Tokutomi      Update          QC#26534
 * 07/09/2018   CITS            Y.Iwasaki       Update          QC#27013
 * 07/24/2018   CITS            T.Hakodate      Update          QC#27012
 * 08/02/2018   CITS            T.Tokutomi      Update          QC#27019
 * 08/02/2018   CITS            T.Tokutomi      Update          QC#27020
 * 10/17/2018   CITS            T.Tokutomi      Update          QC#28822
 * 10/25/2018   CITS            T.Tokutomi      Update          QC#28729
 * 02/18/2019   CITS            K.Ogino         Update          QC#30326
 * 02/19/2018   CITS            T.Tokutomi      Update          QC#30334
 * 04/14/2020   CITS            K.Ogino         Update          QC#56544
 * 04/17/2023   CSA             G.Quan          Insert          QC#61206
 *</pre>
 */
public class NPZC101001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap glMsgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** NPZC101001PMsg */
    private NPZC101001PMsg glWkPmsg = null;

    /** OnBatchType */
    private ONBATCH_TYPE glOnBatchType = null;

    /** DEMAND_THRU */
    private String glDmndThru = null;

    /** SPLY_THRU */
    private String glSplyThru = null;

    /** INVTY_LOC_CD */
    private String glInvtyLocCd = null;

    /** WH_CD */
    private String glWhCd = null;

    /** LOC_TP_CD */
    private String glLocTpCd = null;

    /** CUR_INVTY_QTY */
    private BigDecimal glCurInvtyQty = BigDecimal.ZERO;

    /** SCHD_INBD_QTY */
    private BigDecimal glSchdInbdQty = BigDecimal.ZERO;

    /** SCHD_OUTBD_QTY */
    private BigDecimal glSchdOtbdQty = BigDecimal.ZERO;

    /** TOTAL AVAILABLE QTY */
    private BigDecimal glTotalAvalQty = BigDecimal.ZERO;

    /** REPLENISHMENT QTY */
    private BigDecimal glRepQty = BigDecimal.ZERO;

    /** NPZC101001InternalInfoBean List */
    private List<NPZC101001InternalInfoBean> glInternalInfoList = null;

    /** STS_CD List */
    private List<Map<String, Object>> glStsCdList = null;

    /** MDSE_CD List */
    private Set<String> glMdseCdList = new HashSet<String>();

    /** ORD_TAKE_MDSE Flag */
    // START 2017/08/09 S.Katsuma QC#20543 ADD
    private boolean ordTakeMdseFlg = false;

    // END 2017/08/09 S.Katsuma QC#20543 ADD

    /**
     * Constructor
     */
    public NPZC101001() {
        super();
    }

    /**
     * MRP Calculation.
     * @param params List<NPZC101001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NPZC101001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NPZC101001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * MRP Calculation.
     * @param param NPZC101001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC101001PMsg param, final ONBATCH_TYPE onBatchType) {

        glWkPmsg = param;
        glMsgMap = new S21ApiMessageMap(param);
        glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        glOnBatchType = onBatchType;
        glInternalInfoList = new ArrayList<NPZC101001InternalInfoBean>();

        // check Parameter
        if (!checkInput()) {
            glMsgMap.flush();
            return;
        }

        // get WAREHOUSE Val
        getWhVal(glMdseCdList);

        // get TECHNICIAN Val
        getTechVal(glMdseCdList);

        // culc Quantity
        culcQty();

        // set Out Param
        setOutParam();

        glMsgMap.flush();

        return;

    }

    private boolean checkInput() {

        if (!ZYPCommonFunc.hasValue(glWkPmsg.glblCmpyCd)) {
            glMsgMap.addXxMsgId(NPZC101001Constant.NPZM0001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(glWkPmsg.xxModeCd)) {
            glMsgMap.addXxMsgId(NPZC101001Constant.NPZM0093E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(glWkPmsg.slsDt)) {
            glMsgMap.addXxMsgId(NPZC101001Constant.NPZM0180E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(glWkPmsg.mdseCd)) {
            glMsgMap.addXxMsgId(NPZC101001Constant.NPZM0020E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(glWkPmsg.invtyLocCd)) {
            glMsgMap.addXxMsgId(NPZC101001Constant.NPZM0053E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(glWkPmsg.xxModeInd)) {
            ZYPEZDItemValueSetter.setValue(glWkPmsg.xxModeInd, "0");
        }

        // Set Demand Cutoff Dat And Supply Cutoff Date
        if (ZYPCommonFunc.hasValue(glWkPmsg.dmndCtoffDt) || ZYPCommonFunc.hasValue(glWkPmsg.dmndCtoffDaysAot)) {
            String wkDmndFrom = glWkPmsg.slsDt.getValue();
            if (ZYPCommonFunc.hasValue(glWkPmsg.dmndCtoffDt)) {
                wkDmndFrom = glWkPmsg.dmndCtoffDt.getValue();
            }
            if (ZYPCommonFunc.hasValue(glWkPmsg.dmndCtoffDaysAot)) {
                glDmndThru = ZYPDateUtil.addDays(wkDmndFrom, glWkPmsg.dmndCtoffDaysAot.getValue().intValue());
            } else {
                glDmndThru = wkDmndFrom;
            }
        } else if (!ZYPCommonFunc.hasValue(glWkPmsg.dmndCtoffDt) && !ZYPCommonFunc.hasValue(glWkPmsg.dmndCtoffDaysAot)) {
            glDmndThru = NPZC101001Constant.MAX_YMD;
        }

        if (ZYPCommonFunc.hasValue(glWkPmsg.splyCtoffDt) || ZYPCommonFunc.hasValue(glWkPmsg.splyCtoffDaysAot)) {
            String wkSplyFrom = glWkPmsg.slsDt.getValue();
            if (ZYPCommonFunc.hasValue(glWkPmsg.splyCtoffDt)) {
                wkSplyFrom = glWkPmsg.splyCtoffDt.getValue();
            }
            if (ZYPCommonFunc.hasValue(glWkPmsg.splyCtoffDaysAot)) {
                glSplyThru = ZYPDateUtil.addDays(wkSplyFrom, glWkPmsg.splyCtoffDaysAot.getValue().intValue());
            } else {
                glSplyThru = wkSplyFrom;
            }
        } else if (!ZYPCommonFunc.hasValue(glWkPmsg.splyCtoffDt) && !ZYPCommonFunc.hasValue(glWkPmsg.splyCtoffDaysAot)) {
            glSplyThru = NPZC101001Constant.MAX_YMD;
        }

        // QC#16084
        // Check 8Length Item Code
        if (chkEightLengthItem() > 0) {
            getTenDigitsItem();
            // START 2017/08/09 S.Katsuma QC#20543 ADD
            ordTakeMdseFlg = true;
            // END 2017/08/09 S.Katsuma QC#20543 ADD
        } else {
            glMdseCdList.add(glWkPmsg.mdseCd.getValue());
        }

        // Query supersede items
        if (!"1".equals(glWkPmsg.xxModeCd.getValue())) {
            Set<String> mdseCdList = new HashSet<String>();
            for (String mdseCd : glMdseCdList) {
                // call supersede API
                NWZC206001PMsg supersede = new NWZC206001PMsg();

                ZYPEZDItemValueSetter.setValue(supersede.glblCmpyCd, glWkPmsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(supersede.slsDt, glWkPmsg.slsDt);
                ZYPEZDItemValueSetter.setValue(supersede.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(supersede.xxModeCd, ZYPConstant.CHKBOX_ON_1);
                ZYPEZDItemValueSetter.setValue(supersede.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
                // QC#27012 MOD START
                //ZYPEZDItemValueSetter.setValue(supersede.xxAvalPrchFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(supersede.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
                // QC#27012 MOD END
                NWZC206001 superSedeAPI = new NWZC206001();
                superSedeAPI.execute(supersede, ONBATCH_TYPE.BATCH);

                if (S21ApiUtil.getXxMsgIdList(supersede).isEmpty()) {
                    for (int i = 0; i < supersede.A.getValidCount(); i++) {
                        mdseCdList.add(supersede.A.no(i).mdseCd.getValue());
                    }
                }
            }

            // QC#56544
            for (String mdseCd : mdseCdList) {
                if (!glMdseCdList.contains(mdseCd)) {
                    glMdseCdList.add(mdseCd);
                }
            }

            // QC#26534 Add. compatible item.
            for (String mdseCd : glMdseCdList) {

                List<String> cmptItemList = getCompatibleItem(glWkPmsg.glblCmpyCd.getValue(), mdseCd);

                for (String cmptItem : cmptItemList) {
                    if (!mdseCdList.contains(cmptItem)) {
                        mdseCdList.add(cmptItem);
                    }
                }
            }

            // set superSede API output Item Code List
            for (String mdseCd : mdseCdList) {
                if (!glMdseCdList.contains(mdseCd)) {
                    glMdseCdList.add(mdseCd);
                }
            }
        }

        // get Location Info
        if (chkSwhInfo() > 0) {
            glInvtyLocCd = glWkPmsg.invtyLocCd.getValue();
        } else {
            glInvtyLocCd = glWkPmsg.invtyLocCd.getValue() + "%";
        }
        glLocTpCd = getLocTpCd();

        // get STS_CD
        glStsCdList = getStsCd();
        if (glStsCdList == null || glStsCdList.size() < 1) {
            glMsgMap.addXxMsgId(NPZC101001Constant.NPAM1310E);
            return false;
        }

        return true;
    }

    /**
     * getCompatibleItem
     * QC#26534 Add method.
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return compatible item list
     */
    private List<String> getCompatibleItem(String glblCmpyCd, String mdseCd) {

        String targetMdseRelnTpCsv = ZYPCodeDataUtil.getVarCharConstValue("NPZC1010_CMPT_MDSE_RELN_TP", glblCmpyCd);
        String[] targetMdseRelnTpList = null;
        if(targetMdseRelnTpCsv != null){
            targetMdseRelnTpList = targetMdseRelnTpCsv.split(",");
        }
        // QC#56544
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glblCmpyCd);
//        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
//        queryParam.put(NPZC101001Constant.MDSE_ITEM_RELN_TP_CD, targetMdseRelnTpList);
//
//        List<String> resultList = (List<String>) glSsmBatchClient.queryObjectList("getCompatibleItem", queryParam);
//        
//        if(resultList == null){
//            resultList = new ArrayList<String>();
//        }

        List<String> resultList = NPXC001001GetMdseRelationshipData.getMdseRelationshipData(glblCmpyCd, mdseCd, targetMdseRelnTpList);

        return resultList;
    }

    private int chkEightLengthItem() {
        ORD_TAKE_MDSETMsg inTMsg = new ORD_TAKE_MDSETMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", glWkPmsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("ordTakeMdseCd01", glWkPmsg.mdseCd.getValue());
        ORD_TAKE_MDSETMsgArray result = (ORD_TAKE_MDSETMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);
        if (result == null) {
            return 0;
        }
        return result.length();
    }

    private void getTenDigitsItem() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.MDSE_CD, glWkPmsg.mdseCd.getValue() + "%");
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getTenDigitsItem", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                glMdseCdList.add(data.get(NPZC101001Constant.MDSE_CD).toString());
            }
        }
    }

    private int chkSwhInfo() {

        RTL_SWHTMsg inTMsg = new RTL_SWHTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glWkPmsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("invtyLocCd01", glWkPmsg.invtyLocCd.getValue());
        inTMsg.setConditionValue("effFromDt01", NPZC101001Constant.MAX_YMD);
        inTMsg.setConditionValue("effThruDt01", NPZC101001Constant.MIN_YMD);
        RTL_SWHTMsgArray result = (RTL_SWHTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);
        if (result == null || result.getValidCount() == 0) {
            return 0;
        } else {
            if (ZYPCommonFunc.hasValue(result.no(0).rtlWhCd)) {
                glWhCd = result.no(0).rtlWhCd.getValue();
            }
        }
        return result.length();
    }

    private String getLocTpCd() {

        RTL_WHTMsg inTMsg = new RTL_WHTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glWkPmsg.glblCmpyCd.getValue());
        if (glWhCd != null && !glWhCd.isEmpty()) {
            inTMsg.setConditionValue("rtlWhCd01", glWhCd);
        } else {
            inTMsg.setConditionValue("rtlWhCd01", glWkPmsg.invtyLocCd.getValue());
        }
        inTMsg.setConditionValue("effFromDt01", NPZC101001Constant.MAX_YMD);
        inTMsg.setConditionValue("effThruDt01", NPZC101001Constant.MIN_YMD);
        RTL_WHTMsgArray result = (RTL_WHTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);
        if (result == null || result.getValidCount() == 0) {
            return null;
        }
        return result.no(0).locTpCd.getValue();

    }

    private List<Map<String, Object>> getStsCd() {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.BIZ_APP_ID, NPZC101001Constant.NPZC1010);

        return (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getStsCd", queryParam);
    }

    private void getWhVal(Set<String> mdseCd) {
        if (glLocTpCd != null && LOC_TP.WAREHOUSE.equals(glLocTpCd)) {
            try {
                for (Map<String, Object> stsCd : glStsCdList) {
                    // get Inventory Data
                    getInvDat(stsCd, mdseCd);
                    // get Open PO Data
                    getOpenPoDat(stsCd, mdseCd);
                    // get Open Inbound Purchase Requisition Data
                    getOpenInbPrDat(stsCd, mdseCd);
                    // get Open Inbound Inventory Request Data
                    getOpenInbInvDat(stsCd, mdseCd);
                    // get Inventory Request Data
                    getInvReqDat(stsCd, mdseCd);
                    // get Open Work Order kit Data
                    getOpenWkOrdKitDat(stsCd, mdseCd);
                    // get Saved Status Customer Purchase Order Data
                    // START 2017/10/04 S.Katsuma QC#21429 ADD
                    // getSavedCpoDat(stsCd, mdseCd);
                    // QC#26532 Update method.
                    getSavedCpoDat(stsCd, mdseCd, glWkPmsg.mdseCd.getValue(),glWkPmsg.xxModeInd.getValue());
                    // END 2017/08/09 S.Katsuma QC#21429 ADD
                    // get Validated Status Customer Purchase Order
                    // Data
                    // START 2017/08/09 S.Katsuma QC#20543 ADD
                    // getValidatedCpoDat(stsCd, mdseCd);
                    getValidatedCpoDat(stsCd, mdseCd, glWkPmsg.mdseCd.getValue());
                    // END 2017/08/09 S.Katsuma QC#20543 ADD
                    if ("0".equals(glWkPmsg.xxModeInd.getValue()) || "1".equals(glWkPmsg.xxModeInd.getValue())) {
                        // get Before Insourcing Purchase Request Data
                        getBeforeInsPrDat(stsCd, mdseCd);
                        // get Open Work Order Data
                        getOpenWorkOrderSavedDat(stsCd, mdseCd);
                        // QC#18401-SOL#014 Add get open work order
                        // Data
                        getOpenWorkOrderDat(stsCd, mdseCd);
                    } else if ("2".equals(glWkPmsg.xxModeInd.getValue())) {
                        // get After Insourcing Purchase Request Data
                        getAfterInsPrDat(stsCd, mdseCd);
                        // get Open Work Order Data
                        getOpenWorkOrderDat(stsCd, mdseCd);
                    }
                    // get Open Outbound Inventory Request Data
                    getOpenOutInvReqDat(stsCd, mdseCd);
                    // get Open Reman Order Data
                    // getOpenRemanOrderDat(stsCd, mdseCd);
                    // get Open Reman Order (planned WH)
                    getOpenRemanOrderPlannedWhDat(stsCd, mdseCd);
                }
                // get Open Reman Order Data
                getOpenRemanOrderDat(mdseCd);
            } catch (Exception e) {
                glMsgMap.addXxMsgId(NPZC101001Constant.NPZM0055E);
            }
        }
    }

    private void getInvDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.LOC_STS_CD, (String) stsCd.get(NPZC101001Constant.LOC_STS_CD));
        queryParam.put(NPZC101001Constant.STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getInvDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setInvDat(data);
            }
        }
    }

    private void setInvDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glCurInvtyQty = glCurInvtyQty.add((BigDecimal) data.get(NPZC101001Constant.INVTY_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setCurInvtyQty((BigDecimal) data.get(NPZC101001Constant.INVTY_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            // START 2017/07/26 S.Katsuma [QC#19656,ADD]
            // infoBean.setXxInbdOtbdNm((String)
            // data.get(NPZC101001Constant.INVTY_LOC_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
            // START 2017/07/26 S.Katsuma [QC#19656,ADD]
            infoBean.setEtaEtdDt(glWkPmsg.slsDt.getValue());
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_STK, glWkPmsg.glblCmpyCd.getValue()));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenPoDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.OPEN, PO_HDR_STS.OPEN);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(NPZC101001Constant.POYO_RECEIVE, INBD_VIS_EVENT.POYO_RECEIVE);
        queryParam.put(NPZC101001Constant.STOCK_IN_DC, INBD_VIS_DATA_TP.STOCK_IN_DC);
        queryParam.put(NPZC101001Constant.THRU_DT, glSplyThru);
        queryParam.put(NPZC101001Constant.DOMESTIC_PO_RECEIVE, INBD_VIS_EVENT.DOMESTIC_PO_RECEIVE);
        // QC#24580 Add
        queryParam.put(NPZC101001Constant.RWS_STS_CANCEL, RWS_STS.CANCELED);
        // QC#28729 Add
        queryParam.put(NPZC101001Constant.RWS_STS_CLOSE, RWS_STS.RECEIVED);
        // QC#30334 Add
        queryParam.put(NPZC101001Constant.EXCLUSION_PO_LINE_TYPE //
                , new ArrayList<String>(Arrays.asList(PO_LINE_TP.EXPENSE, PO_LINE_TP.EXPENSE_WITH_RECEIPT)));


        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenPoDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setOpenPoDat(data);
            }
        }
    }

    private void setOpenPoDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.VIS_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.VIS_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.VIS_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.ETA_ETD_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.ORD_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_PO, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.PO_ORD_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.PO_ORD_DTL_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.DS_PO_TP_DESC_TXT));
            infoBean.setRqstRcvDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("PO_HDR_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("PO_HDR_STS_NM"));
            infoBean.setApvlStsCd((String) data.get("PO_APVL_STS_CD"));
            infoBean.setApvlStsNm((String) data.get("PO_APVL_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("DISP_PO_DTL_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenInbPrDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.PO_REQUISITION);

        // QC#20833
        // queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD,
        // PRCH_REQ_STS.OPEN);
        List<String> prchReqStsCdList = new ArrayList<String>(2);
        prchReqStsCdList.add(PRCH_REQ_STS.OPEN);
        prchReqStsCdList.add(PRCH_REQ_STS.RELEASE_ERROR);
        queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD_LIST, prchReqStsCdList);

        queryParam.put(NPZC101001Constant.THRU_DT, glSplyThru);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);

        if (ONBATCH_TYPE.BATCH==glOnBatchType) {
            queryParam.put(NPZC101001Constant.BATCH_MODE, ZYPConstant.FLG_ON_Y);
            queryParam.put(NPZC101001Constant.PRCH_REQ_APVL_STS_CD, PRCH_REQ_APVL_STS.ENTERED);
            queryParam.put(NPZC101001Constant.PRCH_REQ_SRC_TP_CD, PRCH_REQ_SRC_TP.WH_PLANNING);
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenInbPrDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setOpenInbPrDat(data);
            }
        }
    }

    private void setOpenInbPrDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.PRCH_REQ_BAL_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.PRCH_REQ_BAL_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.DEST_INVTY_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.PRCH_REQ_CRAT_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_POREQ, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.PRCH_REQ_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.PRCH_REQ_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.PRCH_REQ_TP_DESC_TXT));
            infoBean.setRqstRcvDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("PRCH_REQ_LINE_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("PRCH_REQ_LINE_STS_NM"));
            infoBean.setApvlStsCd((String) data.get("PRCH_REQ_APVL_STS_CD"));
            infoBean.setApvlStsNm((String) data.get("PRCH_REQ_APVL_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("PRCH_REQ_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenInbInvDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);

        // QC#20833
        List<String> prchReqStsCdList = new ArrayList<String>(2);
        prchReqStsCdList.add(PRCH_REQ_STS.OPEN);
        prchReqStsCdList.add(PRCH_REQ_STS.RELEASE_ERROR);
        queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD_LIST, prchReqStsCdList);
        // queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD,
        // PRCH_REQ_STS.OPEN);

        queryParam.put(NPZC101001Constant.THRU_DT, glSplyThru);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);

        if (ONBATCH_TYPE.BATCH==glOnBatchType) {
            queryParam.put(NPZC101001Constant.BATCH_MODE, ZYPConstant.FLG_ON_Y);
            queryParam.put(NPZC101001Constant.PRCH_REQ_APVL_STS_CD, PRCH_REQ_APVL_STS.ENTERED);
            queryParam.put(NPZC101001Constant.PRCH_REQ_SRC_TP_CD, PRCH_REQ_SRC_TP.WH_PLANNING);
        }

        // QC#30326
        queryParam.put(NPZC101001Constant.PRCH_REQ_TP_EXPENCE_ORDER, PRCH_REQ_TP.EXPENSE_ORDER);
        queryParam.put(NPZC101001Constant.PRCH_REQ_TP_WH_TRANSFER, PRCH_REQ_TP.WH_TRANSFER);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenInbInvDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setOpenInbInvDat(data);
            }
        }
    }

    private void setOpenInbInvDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.DEST_INVTY_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.PRCH_REQ_CRAT_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_INVREQ, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.PRCH_REQ_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.PRCH_REQ_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.PRCH_REQ_TP_DESC_TXT));
            infoBean.setRqstRcvDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("PRCH_REQ_LINE_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("PRCH_REQ_LINE_STS_NM"));
            infoBean.setApvlStsCd((String) data.get("PRCH_REQ_APVL_STS_CD"));
            infoBean.setApvlStsNm((String) data.get("PRCH_REQ_APVL_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("PRCH_REQ_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getInvReqDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
            queryParam.put(NPZC101001Constant.PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        } else {
            queryParam.put(NPZC101001Constant.PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        }
        queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD, PRCH_REQ_STS.OPEN);
        queryParam.put(NPZC101001Constant.THRU_DT, glSplyThru);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        // QC#21987 Add
        queryParam.put(NPZC101001Constant.FROM_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.PRCH_REQ_TP_CD_KT, PRCH_REQ_TP.KITTING);
        // QC#21987 End
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(NPZC101001Constant.INBD_VIS_EVENT_CD, INBD_VIS_EVENT.DC_TRANSFER_RECEIVE);
        queryParam.put(NPZC101001Constant.INBD_VIS_DATA_TP_CD, INBD_VIS_DATA_TP.STOCK_IN_DC);

        queryParam.put(NPZC101001Constant.SHPG_STS_CD_VALIDATED, SHPG_STS.VALIDATED);

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
            resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getInvReqDatForTec", queryParam);
        } else {
            resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getInvReqDat", queryParam);
        }

        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setInvReqDat(data);
            }
        }
    }

    private void setInvReqDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.VIS_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.VIS_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.VIS_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.ETA_ETD_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.PRCH_REQ_CRAT_DT));
            // QC#30001
            String prchReqReqTpCd = (String) data.get(NPZC101001Constant.PRCH_REQ_REC_TP_CD);
            if (ZYPCommonFunc.hasValue(prchReqReqTpCd) && PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY.equals(prchReqReqTpCd)) {
                infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_TECHREQ, glWkPmsg.glblCmpyCd.getValue()));
            } else {
                infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_INVREQ, glWkPmsg.glblCmpyCd.getValue()));
            }
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.PRCH_REQ_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.PRCH_REQ_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.PRCH_REQ_TP_DESC_TXT));
            infoBean.setRqstRcvDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("PRCH_REQ_LINE_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("PRCH_REQ_LINE_STS_NM"));
            infoBean.setApvlStsCd((String) data.get("PRCH_REQ_APVL_STS_CD"));
            infoBean.setApvlStsNm((String) data.get("PRCH_REQ_APVL_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("PRCH_REQ_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenWkOrdKitDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.THRU_DT, glSplyThru);
        List<String> wrkOrdStsCdList = new ArrayList<String>();
        wrkOrdStsCdList.add(WRK_ORD_STS.SAVED);
        wrkOrdStsCdList.add(WRK_ORD_STS.VALIDATED);
        wrkOrdStsCdList.add(WRK_ORD_STS.SHIPPED);
        wrkOrdStsCdList.add(WRK_ORD_STS.RECEIVING);
        queryParam.put(NPZC101001Constant.WRK_ORD_STS_CD, wrkOrdStsCdList);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.DS_WRK_ORD_TP_CD, DS_WRK_ORD_TP.KIT);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenWkOrdKitDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setOpenWkOrdDat(data);
            }
        }
    }

    private void setOpenWkOrdDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.FNSH_GOODS_BAL_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.FNSH_GOODS_BAL_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.FNSH_GOODS_MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.WRK_ORD_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_WO, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.WRK_ORD_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.DS_WRK_ORD_TP_DESC_TXT));
            infoBean.setRqstRcvDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("WRK_ORD_STS_CD"));
            // QC#24814 Update.
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("DS_WRK_ORD_STS_NM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    // QC#18401-SOL#014 Add method
    private boolean isCalcSaveSalseOrder(String rtlWhCd, String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, rtlWhCd);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);

        String calcOrdProcCd = (String) glSsmBatchClient.queryObject("getCalcOrdProcCd", queryParam);

        if (ZYPCommonFunc.hasValue(calcOrdProcCd) && ZYPConstant.FLG_ON_1.equals(calcOrdProcCd)) {
            return true;
        }

        return false;
    }

    // QC#18410-SOL#014 Modify. select calculation save sales order.
    // QC#26532 Update method.
    private void getSavedCpoDat(Map<String, Object> stsCd, Set<String> mdseCdList, String mdseCd, String xxModeInd) {

        Set<String> targetCalculateItemList = new HashSet<String>();

        if (ordTakeMdseFlg) {
            if (isCalcSaveSalseOrder(glInvtyLocCd, mdseCd) // 
                    || NPZC101001Constant.MODE_IND_DETAIL.equals(xxModeInd)) {
                targetCalculateItemList.add(mdseCd);
            }
        } else {
            for (String itemCd : mdseCdList) {
                if (isCalcSaveSalseOrder(glInvtyLocCd, itemCd) //
                        || NPZC101001Constant.MODE_IND_DETAIL.equals(xxModeInd)) {
                    targetCalculateItemList.add(itemCd);
                }
            }
        }

        if (!targetCalculateItemList.isEmpty()) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
            // START 2017/08/09 S.Katsuma QC#21429 ADD
            // queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
            if (ordTakeMdseFlg) {
                queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd + "%");
                queryParam.put(NPZC101001Constant.ORD_TAKE_MDSE_FLG, "Y");
            } else {
                queryParam.put(NPZC101001Constant.MDSE_CD, targetCalculateItemList);
            }
            // END 2017/08/09 S.Katsuma QC#21429 ADD
            queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
            queryParam.put(NPZC101001Constant.THRU_DT, glDmndThru);
            // QC#18401-SOL#014 modify.
            queryParam.put(NPZC101001Constant.ORD_HDR_DPLY_STS_BOOK, ORD_HDR_DPLY_STS.BOOKED);
            queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getSavedCpoDat", queryParam);
            if (resultList != null && resultList.size() > 0) {
                for (Map<String, Object> data : resultList) {
                    setSavedCpoDat(data);
                }
            }
        }
    }

    private void setSavedCpoDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.ORD_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdOtbdQty((BigDecimal) data.get(NPZC101001Constant.ORD_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RSD_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.ORD_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_CPO, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.CPO_ORD_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.CPO_DTL_LINE_NUM));
            // QC# 26554 modify.
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.DS_ORD_TP_DESC_TXT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("ORD_HDR_DPLY_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("ORD_HDR_DPLY_STS_DESC_TXT"));
            infoBean.setXxDplyOrdLineNum((String) data.get("DPLY_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getValidatedCpoDat(Map<String, Object> stsCd, Set<String> mdseCdList, String mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        // START 2017/08/09 S.Katsuma QC#20543 ADD
        // queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        if (ordTakeMdseFlg) {
            queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd + "%");
            queryParam.put(NPZC101001Constant.ORD_TAKE_MDSE_FLG, "Y");
        } else {
            queryParam.put(NPZC101001Constant.MDSE_CD, mdseCdList);
        }
        // END 2017/08/09 S.Katsuma QC#20543 ADD
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.THRU_DT, glDmndThru);
        queryParam.put(NPZC101001Constant.SHPG_STS_CD_VALIDATED, SHPG_STS.VALIDATED);
        // QC#18401-SOL#014 Add param
        queryParam.put(NPZC101001Constant.SHPG_STS_CD_INSHED, SHPG_STS.INSHED);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.ORD_HDR_DPLY_STS_BOOK, ORD_HDR_DPLY_STS.BOOKED);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getValidatedCpoDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setValidatedCpoDat(data);
            }
        }
    }

    private void setValidatedCpoDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.ORD_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdOtbdQty((BigDecimal) data.get(NPZC101001Constant.ORD_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
            if (ZYPCommonFunc.hasValue((String) data.get("PKT_STS_TS"))) {
                String pktStsTs = (String) data.get("PKT_STS_TS");
                if (pktStsTs.length() > 8) {
                    pktStsTs = pktStsTs.substring(0, 8);
                }
                infoBean.setEtaEtdDt(pktStsTs);
            } else {
                infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RSD_DT));
            }
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.ORD_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_CPO, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.TRX_HDR_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.TRX_LINE_NUM));
            // QC# 26554 modify.
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.DS_ORD_TP_DESC_TXT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("ORD_HDR_DPLY_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("ORD_HDR_DPLY_STS_DESC_TXT"));
            infoBean.setShpgStsCd((String) data.get("SHPG_STS_CD"));
            infoBean.setShpgStsNm((String) data.get("SHPG_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("DPLY_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getBeforeInsPrDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD, PRCH_REQ_STS.OPEN);
        queryParam.put(NPZC101001Constant.THRU_DT, glDmndThru);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        // QC#27019 Add parameter.
        queryParam.put(NPZC101001Constant.STK_STS_GOOD, STK_STS.GOOD);

        if (ONBATCH_TYPE.BATCH==glOnBatchType) {
            queryParam.put(NPZC101001Constant.BATCH_MODE, ZYPConstant.FLG_ON_Y);
            queryParam.put(NPZC101001Constant.PRCH_REQ_APVL_STS_CD, PRCH_REQ_APVL_STS.ENTERED);
            queryParam.put(NPZC101001Constant.PRCH_REQ_SRC_TP_CD, PRCH_REQ_SRC_TP.TECH_PLANNING);
        }

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
            resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getBeforeInsPrDatForTec", queryParam);
        } else {
            resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getBeforeInsPrDat", queryParam);
        }

        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setBeforeInsPrDat(data);
            }
        }
    }

    private void setBeforeInsPrDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
                glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.PRCH_REQ_BAL_QTY));
            } else {
                glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.PRCH_REQ_BAL_QTY));
            }
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
                infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.PRCH_REQ_BAL_QTY));
                infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.DEST_INVTY_LOC_CD));
                infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            } else {
                infoBean.setSchdOtbdQty((BigDecimal) data.get(NPZC101001Constant.PRCH_REQ_BAL_QTY));
                infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.SRC_INVTY_LOC_CD));
                infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
            }
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.PRCH_REQ_CRAT_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_TECHREQ, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.PRCH_REQ_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.PRCH_REQ_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.PRCH_REQ_TP_DESC_TXT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("PRCH_REQ_LINE_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("PRCH_REQ_LINE_STS_NM"));
            infoBean.setApvlStsCd((String) data.get("PRCH_REQ_APVL_STS_CD"));
            infoBean.setApvlStsNm((String) data.get("PRCH_REQ_APVL_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("PRCH_REQ_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getAfterInsPrDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
        queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD, PRCH_REQ_STS.OPEN);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(NPZC101001Constant.THRU_DT, glDmndThru);
        queryParam.put(NPZC101001Constant.SHPG_STS_CD_VALIDATED, SHPG_STS.VALIDATED);

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
            resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getAfterInsPrDatForTec", queryParam);
        } else {
            resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getAfterInsPrDat", queryParam);
        }

        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setAfterInsPrDat(data);
            }
        }
    }

    private void setAfterInsPrDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
                glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_QTY));
            } else {
                glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_QTY));
            }
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
                infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_QTY));
                infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.DEST_INVTY_LOC_CD));
                infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            } else {
                infoBean.setSchdOtbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_QTY));
                infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.SRC_INVTY_LOC_CD));
                infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
            }
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.ETA_ETD_DT));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.PRCH_REQ_CRAT_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_TECHREQ, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.PRCH_REQ_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.PRCH_REQ_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.PRCH_REQ_TP_DESC_TXT));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenOutInvReqDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        // Target all outbound Request
        // queryParam.put(NPZC101001Constant.PRCH_REQ_REC_TP_CD,
        // PRCH_REQ_REC_TP.INVENTORY_REQUEST);
        String[] techReqList = new String[] {PRCH_REQ_TP.PREMIUM_RUSH, PRCH_REQ_TP.RUSH, PRCH_REQ_TP.STANDARD };
        queryParam.put(NPZC101001Constant.TECH_REQ, techReqList);
        queryParam.put(NPZC101001Constant.PRCH_REQ_STS_CD, PRCH_REQ_STS.OPEN);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        // queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String)
        // stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.FROM_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(NPZC101001Constant.THRU_DT, glDmndThru);
        // QC#18401 Update.
        //queryParam.put(NPZC101001Constant.PRCH_REQ_REL_STS_CD, PRCH_REQ_REL_STS.IN_COMPLETED);
        queryParam.put(NPZC101001Constant.DS_SO_LINE_STS_CD, new String[]{DS_SO_LINE_STS.SHIPPED, DS_SO_LINE_STS.CANCELLED});
        // QC#21987 Add Start
        queryParam.put(NPZC101001Constant.PRCH_REQ_TP_CD_KT, PRCH_REQ_TP.KITTING);
        // QC#21987 Add End
        queryParam.put(NPZC101001Constant.SHPG_STS_CD_VALIDATED, SHPG_STS.VALIDATED);
        // QC#27020 Add Parameter 
        queryParam.put(NPZC101001Constant.SCE_ORD_TP_TR, SCE_ORD_TP.TECH_REQUEST);
        
        if (ONBATCH_TYPE.BATCH==glOnBatchType) {
            queryParam.put(NPZC101001Constant.BATCH_MODE, ZYPConstant.FLG_ON_Y);
            queryParam.put(NPZC101001Constant.PRCH_REQ_APVL_STS_CD, PRCH_REQ_APVL_STS.ENTERED);
            queryParam.put(NPZC101001Constant.PRCH_REQ_SRC_TP_CD_LIST, Arrays.asList(new String[] {PRCH_REQ_SRC_TP.TECH_PLANNING, PRCH_REQ_SRC_TP.WH_PLANNING }));
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenOutInvReqDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setOpenOutInvReqDat(data);
            }
        }
    }

    private void setOpenOutInvReqDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdOtbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.SRC_INVTY_LOC_CD));
            if (ZYPCommonFunc.hasValue((String) data.get("PKT_STS_TS"))) {
                String pktStsTs = (String) data.get("PKT_STS_TS");
                if (pktStsTs.length() > 8) {
                    pktStsTs = pktStsTs.substring(0, 8);
                }
                infoBean.setEtaEtdDt(pktStsTs);
            } else {
                infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.ETA_ETD_DT));
            }

            // QC#30001
            String prchReqReqTpCd = (String) data.get(NPZC101001Constant.PRCH_REQ_REC_TP_CD);
            if (ZYPCommonFunc.hasValue(prchReqReqTpCd) && PRCH_REQ_SRC_TP.TECH_REQUEST_ENTRY.equals(prchReqReqTpCd)) {
                infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_TECHREQ, glWkPmsg.glblCmpyCd.getValue()));
            } else {
                infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_INVREQ, glWkPmsg.glblCmpyCd.getValue()));
            }
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.PRCH_REQ_CRAT_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.PRCH_REQ_NUM));
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.PRCH_REQ_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.PRCH_REQ_TP_DESC_TXT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("PRCH_REQ_LINE_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("PRCH_REQ_LINE_STS_NM"));
            infoBean.setShpgStsCd((String) data.get("SHPG_STS_CD"));
            infoBean.setShpgStsNm((String) data.get("SHPG_STS_NM"));
            infoBean.setApvlStsCd((String) data.get("PRCH_REQ_APVL_STS_CD"));
            infoBean.setApvlStsNm((String) data.get("PRCH_REQ_APVL_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("PRCH_REQ_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenWorkOrderSavedDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.WRK_ORD_STS_CD, WRK_ORD_STS.SAVED);
        queryParam.put(NPZC101001Constant.DS_WRK_ORD_TP_CD, DS_WRK_ORD_TP.KIT);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenWorkOrderSavedDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setOpenWorkOrderSavedDat(data);
            }
        }
    }

    private void setOpenWorkOrderSavedDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdOtbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.WRK_ORD_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_WO, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.WRK_ORD_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.DS_WRK_ORD_TP_DESC_TXT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("WRK_ORD_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("DS_WRK_ORD_STS_NM"));
            infoBean.setShpgStsCd((String) data.get("SHPG_STS_CD"));
            infoBean.setShpgStsNm((String) data.get("SHPG_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("WRK_ORD_DTL_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenWorkOrderDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.WRK_ORD_STS_CD, WRK_ORD_STS.VALIDATED);
        queryParam.put(NPZC101001Constant.DS_WRK_ORD_TP_CD, DS_WRK_ORD_TP.KIT);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String) stsCd.get(NPZC101001Constant.STK_STS_CD));
        queryParam.put(NPZC101001Constant.THRU_DT, glDmndThru);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenWorkOrderDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setOpenWorkOrderDat(data);
            }
        }
    }

    private void setOpenWorkOrderDat(Map<String, Object> data) {
        // QC#28822 Update.
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdOtbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
            if (ZYPCommonFunc.hasValue((String) data.get("PKT_STS_TS"))) {
                String pktStsTs = (String) data.get("PKT_STS_TS");
                if (pktStsTs.length() > 8) {
                    pktStsTs = pktStsTs.substring(0, 8);
                }
                infoBean.setEtaEtdDt(pktStsTs);
            } else {
                infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.RQST_RCV_DT));
            }
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.WRK_ORD_DT));
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_WO, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.WRK_ORD_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.DS_WRK_ORD_TP_DESC_TXT));
            infoBean.setOrdHdrDplyStsCd((String) data.get("WRK_ORD_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("DS_WRK_ORD_STS_NM"));
            infoBean.setShpgStsCd((String) data.get("SHPG_STS_CD"));
            infoBean.setShpgStsNm((String) data.get("SHPG_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("WRK_ORD_DTL_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getOpenRemanOrderDat(Set<String> mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.THRU_DT, glSplyThru);
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        // queryParam.put(NPZC101001Constant.TO_STK_STS_CD, (String)
        // stsCd.get(NPZC101001Constant.STK_STS_CD));

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenRemanOrderDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                setRemanOrderDat(data);
            }
        }
    }

    // QC#18401-SOL#014 Modify.
    private void getOpenRemanOrderPlannedWhDat(Map<String, Object> stsCd, Set<String> mdseCd) {
        // QC#22111 Modify. Reman Parts Order Stock Status is "GOOD"
        // only.
        if (!STK_STS.GOOD.equals((String) stsCd.get(NPZC101001Constant.STK_STS_CD))) {
            return;
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NPZC101001Constant.GLBL_CMPY_CD, glWkPmsg.glblCmpyCd.getValue());
        queryParam.put(NPZC101001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPZC101001Constant.INVTY_LOC_CD, glInvtyLocCd);
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);

        queryParam.put(NPZC101001Constant.SHPG_STS_CD_SHIPPED, SHPG_STS.SHIPPED);
        queryParam.put(NPZC101001Constant.FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        queryParam.put(NPZC101001Constant.NOT_PROCESS, ZYPConstant.FLG_OFF_0);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getOpenRemanOrderPlannedWhDat", queryParam);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> data : resultList) {
                if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
                    glSchdOtbdQty = glSchdOtbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
                } else {
                    NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
                    infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_OTBD_QTY));
                    infoBean.setMdseCd((String) data.get(NPZC101001Constant.MDSE_CD));
                    infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
                    if (ZYPCommonFunc.hasValue((String) data.get("PKT_STS_TS"))) {
                        String pktStsTs = (String) data.get("PKT_STS_TS");
                        if (pktStsTs.length() > 8) {
                            pktStsTs = pktStsTs.substring(0, 8);
                        }
                        infoBean.setEtaEtdDt(pktStsTs);
                    } else {
                        infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.ETA_ETD_DT));
                    }
                    infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_OUT, glWkPmsg.glblCmpyCd.getValue()));
                    infoBean.setOrdDt((String) data.get(NPZC101001Constant.ORD_DT));
                    infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_REMAN, glWkPmsg.glblCmpyCd.getValue()));
                    infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.RMNF_ORD_NUM));
                    infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.RMNF_PRT_REQ_LINE_NUM));
                    infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.RMNF_ORD_TP_DESC_TXT));
                    infoBean.setOrdHdrDplyStsCd((String) data.get("RMNF_ORD_STS_CD"));
                    infoBean.setOrdHdrDplyStsDescTxt((String) data.get("RMNF_ORD_STS_NM"));
                    infoBean.setShpgStsCd((String) data.get("SHPG_STS_CD"));
                    infoBean.setShpgStsNm((String) data.get("SHPG_STS_NM"));
                    infoBean.setXxDplyOrdLineNum((String) data.get("RMNF_PRT_REQ_LINE_NUM"));
                    // START 2023/04/17 G.Quan [QC#61206, ADD]
                    infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
                    infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
                    infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
                    infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
                    infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
                    // END 2023/04/17 G.Quan [QC#61206, ADD]
                    glInternalInfoList.add(infoBean);
                }
            }
        }
    }

    private void setRemanOrderDat(Map<String, Object> data) {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) {
            glSchdInbdQty = glSchdInbdQty.add((BigDecimal) data.get(NPZC101001Constant.SCHD_INBD_QTY));
        } else {
            NPZC101001InternalInfoBean infoBean = new NPZC101001InternalInfoBean();
            infoBean.setSchdInbdQty((BigDecimal) data.get(NPZC101001Constant.SCHD_INBD_QTY));
            // START 2017/07/26 S.Katsuma [QC#19656,ADD]
            // infoBean.setMdseCd((String)
            // data.get(NPZC101001Constant.MDSE_CD));
            infoBean.setMdseCd((String) data.get(NPZC101001Constant.RMNF_TO_CMPT_MDSE_CD));
            // END 2017/07/26 S.Katsuma [QC#19656,ADD]
            infoBean.setXxInvtyLocCd((String) data.get(NPZC101001Constant.INVTY_LOC_CD));
            infoBean.setEtaEtdDt((String) data.get(NPZC101001Constant.WH_IN_ETA_DT));
            infoBean.setXxInbdOtbdNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_INVTY_IN, glWkPmsg.glblCmpyCd.getValue()));
            // START 2017/07/26 S.Katsuma [QC#19656,ADD]
            // infoBean.setOrdDt((String)
            // data.get(NPZC101001Constant.WRK_ORD_DT));
            infoBean.setOrdDt((String) data.get(NPZC101001Constant.RMNF_START_DT));
            // END 2017/07/26 S.Katsuma [QC#19656,ADD]
            infoBean.setXxOrdTrxTpNm(ZYPCodeDataUtil.getVarCharConstValue(NPZC101001Constant.NPZC1010_ORDER_REMAN, glWkPmsg.glblCmpyCd.getValue()));
            infoBean.setOrigOrdNum((String) data.get(NPZC101001Constant.RMNF_ORD_NUM));
            // START 2017/07/26 S.Katsuma [QC#19656,ADD]
            infoBean.setXxOrigOrdLineNum((String) data.get(NPZC101001Constant.RMNF_ORD_DTL_LINE_NUM));
            // infoBean.setXxOrigOrdTpNm((String)
            // data.get(NPZC101001Constant.RMNF_ORD_DTL_LINE_NUM));
            infoBean.setXxOrigOrdTpNm((String) data.get(NPZC101001Constant.RMNF_ORD_TP_DESC_TXT));
            // END 2017/07/26 S.Katsuma [QC#19656,ADD]
            infoBean.setOrdHdrDplyStsCd((String) data.get("RMNF_ORD_STS_CD"));
            infoBean.setOrdHdrDplyStsDescTxt((String) data.get("RMNF_ORD_STS_NM"));
            infoBean.setXxDplyOrdLineNum((String) data.get("RMNF_ORD_DTL_LINE_NUM"));
            // START 2023/04/17 G.Quan [QC#61206, ADD]
            infoBean.setXxRecHistCratTs((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_TS));
            infoBean.setXxRecHistCratByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_CRAT_BY_NM));
            infoBean.setXxRecHistUpdTs((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_TS));
            infoBean.setXxRecHistUpdByNm((String) data.get(NPZC101001Constant.XX_REC_HIST_UPD_BY_NM));
            infoBean.setXxRecHistTblNm((String) data.get(NPZC101001Constant.XX_REC_HIST_TBL_NM));
            // END 2023/04/17 G.Quan [QC#61206, ADD]
            glInternalInfoList.add(infoBean);
        }
    }

    private void getTechVal(Set<String> mdseCd) {
        if (glLocTpCd != null && LOC_TP.TECHNICIAN.equals(glLocTpCd)) {
            for (Map<String, Object> stsCd : glStsCdList) {
                // get Inventory Data
                getInvDat(stsCd, mdseCd);
                // get Open PO Data
                getOpenPoDat(stsCd, mdseCd);
                // get Open Inbound Purchase Requisition Data
                getOpenInbPrDat(stsCd, mdseCd);
                // get Before Insourcing Purchase Request Data
                getBeforeInsPrDat(stsCd, mdseCd);
                // get After Insourcing Purchase Request Data
                getAfterInsPrDat(stsCd, mdseCd);
                // get Inventory Request Data
                getInvReqDat(stsCd, mdseCd);
            }
        }
    }

    private void culcQty() {

        glTotalAvalQty = glCurInvtyQty.add(glSchdInbdQty.subtract(glSchdOtbdQty));

        BigDecimal ropQty = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(glWkPmsg.ropQty)) {
            ropQty = glWkPmsg.ropQty.getValue();
        }

        BigDecimal maxInvtyQty = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(glWkPmsg.maxInvtyQty)) {
            maxInvtyQty = glWkPmsg.maxInvtyQty.getValue();
        }

        if (ropQty.compareTo(glTotalAvalQty) > 0) {
            glRepQty = maxInvtyQty.subtract(glTotalAvalQty);
        }
    }

    private void setOutParam() {
        if ("0".equals(glWkPmsg.xxModeInd.getValue())) { // 0 is Gross
            ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(0).curInvtyQty, glCurInvtyQty);
            ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(0).schdInbdQty, glSchdInbdQty);
            ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(0).schdOtbdQty, glSchdOtbdQty);
            ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(0).curInvtyAvalQty, glTotalAvalQty);
            ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(0).prchReqQty, glRepQty);
        } else {
            // QC#20832 Update.
            if(glWkPmsg.scheduledOrdList.length() < glInternalInfoList.size()){
                // set warning message.
                glMsgMap.addXxMsgId(NPZC101001Constant.NPAM0001W);
            }
            for (int i = 0; i < glInternalInfoList.size() && i < glWkPmsg.scheduledOrdList.length() ; i++) {
                NPZC101001InternalInfoBean info = glInternalInfoList.get(i);
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).curInvtyQty, info.getCurInvtyQty());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).schdInbdQty, info.getSchdInbdQty());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).schdOtbdQty, info.getSchdOtbdQty());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxMdseCd, info.getMdseCd());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxInvtyLocCd, info.getXxInvtyLocCd());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).etaEtdDt, info.getEtaEtdDt());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).inbdOtbdNm, info.getXxInbdOtbdNm());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).ordDt, info.getOrdDt());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).arTrxTpNm, info.getXxOrdTrxTpNm());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).origOrdNum, info.getOrigOrdNum());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).ordLineNum, info.getXxOrigOrdLineNum());
                // QC#26554 Update.
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).ordTpDescTxt, info.getXxOrigOrdTpNm());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).rqstRcvDt, info.getRqstRcvDt());

                // QC#18401-SOL#014 Add.
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).ordHdrDplyStsCd, info.getOrdHdrDplyStsCd());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).ordHdrDplyStsDescTxt, info.getOrdHdrDplyStsDescTxt());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).shpgStsCd, info.getShpgStsCd());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).shpgStsNm, info.getShpgStsNm());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).apvlStsCd, info.getApvlStsCd());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).apvlStsNm, info.getApvlStsNm());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxDplyOrdLineNum, info.getXxDplyOrdLineNum());
                // START 2023/04/17 G.Quan [QC#61206, ADD]
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxRecHistCratTs_A0, info.getXxRecHistCratTs());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxRecHistCratByNm_A0, ZYPRecHistUtil.getFullNameForRecHist(info.getXxRecHistCratByNm()));
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxRecHistUpdTs_A0, info.getXxRecHistUpdTs());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxRecHistUpdByNm_A0, ZYPRecHistUtil.getFullNameForRecHist(info.getXxRecHistUpdByNm()));
                ZYPEZDItemValueSetter.setValue(glWkPmsg.scheduledOrdList.no(i).xxRecHistTblNm_A0, info.getXxRecHistTblNm());
                // END 2023/04/17 G.Quan [QC#61206, ADD]
                glWkPmsg.scheduledOrdList.setValidCount(i + 1);
            }
        }
    }
}
