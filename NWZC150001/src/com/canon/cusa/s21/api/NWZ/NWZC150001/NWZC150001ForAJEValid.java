/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2287E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2288E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsg;
import business.db.DS_ORD_LINE_VLD_RULETMsg;
import business.db.DS_ORD_VLD_CLT_TPTMsg;
import business.db.DS_ORD_VLD_INCL_TPTMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.RTL_WHTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.derive.NWZC150001ForDefaultValueSetter;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_VLD_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_VLD_INCL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_VLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TRX_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/23   Fujitsu         S.Takami        Update          S21_NA#24745
 * 2018/10/18   Fujitsu         S.Takami        Update          S21_NA#26229
 * 2020/04/13   Fujitsu         C.Hara          Update          QC#56374
 * 2022/10/13   CITS            K.Ogino         Update          QC#58110
 * 2023/02/13   CITS            A.Cullano       Update          QC#61158
 * 2024/03/14   CITS            J.Cho           Update          QC#63527
 * </pre>
 */

public class NWZC150001ForAJEValid extends S21ApiCommonBase {

    /** My Instance Key in TreadLocal */
    private static final String INSTANCE_KEY = NWZC150001ForAJEValid.class.getName();

    /** DS_ORD_LINE_VLD_RULETMsg List Cache */
    private final S21LRUMap<Map<String, String>, List<DS_ORD_LINE_VLD_RULETMsg>> ordLineVldRuleCache = new S21LRUMap<Map<String, String>, List<DS_ORD_LINE_VLD_RULETMsg>>();

    /** MDSE Type Value Set result Cache */
    private final S21LRUMap<Map<String, String>, BigDecimal> mdseTpValSetCache = new S21LRUMap<Map<String, String>, BigDecimal>();

    /** Inventory Owner Type Value Set result Cache */
    private final S21LRUMap<Map<String, String>, BigDecimal> invtyOwnrTpValSetCache = new S21LRUMap<Map<String, String>, BigDecimal>();

    /** Mdse Value Set result Cache */
    private final S21LRUMap<Map<String, String>, BigDecimal> mdseValSetCache = new S21LRUMap<Map<String, String>, BigDecimal>();

    // START 2023/02/13 A.Cullano [QC#61158, ADD]
    /** MDSE Code Set result Cache */
    private final S21LRUMap<Map<String, String>, String> mdseCdSetCache = new S21LRUMap<Map<String, String>, String>();
    // END 2023/02/13 A.Cullano [QC#61158, ADD]

    /** SSM Client */
    private S21SsmBatchClient ssmClient = null;

    /** Constructor */
    private NWZC150001ForAJEValid() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    private ONBATCH_TYPE onBatchType = null;
    /**
     * Get Singleton Instance
     * @return DataCacheForSSM
     */
    public static NWZC150001ForAJEValid getInstance() {

        NWZC150001ForAJEValid myInstance = (NWZC150001ForAJEValid) S21ApplicationCacheHolder.get(INSTANCE_KEY);
        if (myInstance == null) {
            myInstance = new NWZC150001ForAJEValid();
            S21ApplicationCacheHolder.put(INSTANCE_KEY, myInstance);
        }
        return myInstance;
    }

    /**
     * <pre>
     * DS CPO Update API for AJE Validation.
     * @param inPMsg        NWZC150001PMsg
     * @param resPMsg2List  List<NWZC150002PMsg>
     * @param resPMsg3List  List<NWZC150003PMsg>
     * @param prmOnBatchType ONBATCH_TYPE
     * </pre>
     */
    public void execute(final NWZC150001PMsg inPMsg //
            , final List<NWZC150002PMsg> resPMsg2List //
            , final List<NWZC150003PMsg> resPMsg3List //
            , final ONBATCH_TYPE prmOnBatchType) {

        this.onBatchType = prmOnBatchType;

        // QC#58110 Mod Start
        // Validation for Outbound
        // QC#63527 2024/03/14 Start
        if (!ZYPCommonFunc.hasValue(inPMsg.xxValUpdFlg_I) || ZYPConstant.FLG_ON_Y.equals(inPMsg.xxValUpdFlg_I.getValue()) || ZYPConstant.FLG_ON_Y.equals(inPMsg.xxValUpdFlg.getValue())) {
        // QC#63527 2024/03/14 End
            for (int i = 0; i < inPMsg.A.getValidCount(); i++) {
                validateOutboundLine(inPMsg, inPMsg.A.no(i), resPMsg2List);
            }
        }

        // Validation for Inbound
        // QC#63527 2024/03/14 Start
        if (!ZYPCommonFunc.hasValue(inPMsg.xxValUpdFlg_O) || ZYPConstant.FLG_ON_Y.equals(inPMsg.xxValUpdFlg_O.getValue()) || ZYPConstant.FLG_ON_Y.equals(inPMsg.xxValUpdFlg.getValue())) {
        // QC#63527 2024/03/14 End
            for (int i = 0; i < inPMsg.rtnDtl.getValidCount(); i++) {
                validateInboundLine(inPMsg, inPMsg.rtnDtl.no(i), resPMsg3List);
            }
        }
        // QC#58110 Mod End
        return;
    }

    private void validateOutboundLine(final NWZC150001PMsg pMsg //
            , final NWZC150001_APMsg linePMsg
            , final List<NWZC150002PMsg> resPMsg2List) {

        List<DS_ORD_LINE_VLD_RULETMsg> ordLineVldRuleList = getOrdLineVldRule(pMsg, linePMsg.dsOrdLineCatgCd_A1.getValue());
        if (ordLineVldRuleList == null || ordLineVldRuleList.isEmpty()) {
            return;
        }

        for (DS_ORD_LINE_VLD_RULETMsg ordLineVldRule : ordLineVldRuleList) {
            boolean isValid = isValidValueSet(pMsg.glblCmpyCd.getValue(), ordLineVldRule, linePMsg);
            if (!isValid) {
                if (S21StringUtil.isEquals(DS_ORD_VLD_ACT_TP.FLIP_TR_RSN, ordLineVldRule.dsOrdVldActTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(ordLineVldRule.dsOrdAltVldTrxCd) //
                            && ZYPCommonFunc.hasValue(ordLineVldRule.dsOrdAltVldTrxRsnCd)) {
                        linePMsg.trxCd_A1.setValue(ordLineVldRule.dsOrdAltVldTrxCd.getValue());
                        linePMsg.trxRsnCd_A1.setValue(ordLineVldRule.dsOrdAltVldTrxRsnCd.getValue());
                    }
                } else if (S21StringUtil.isEquals(DS_ORD_VLD_ACT_TP.VALID_ERR, ordLineVldRule.dsOrdVldActTpCd.getValue())) {
                    String msgId = ordLineVldRule.dsOrdVldErrMsgCd.getValue();
                    NWZC150001Common.addMsgId2List(resPMsg2List, linePMsg, msgId);
                }
            }
        }
    }

    private void validateInboundLine(final NWZC150001PMsg pMsg //
            , final NWZC150001_rtnDtlPMsg rtnDtlPMsg
            , final List<NWZC150003PMsg> resPMsg3List) {

        // 2018/10/18 S21_NA#26229 Add Start
        if (rmaProcValidation(pMsg, rtnDtlPMsg, resPMsg3List)) {
            return;
        }
        // 2018/10/18 S21_NA#26229 Add End

        List<DS_ORD_LINE_VLD_RULETMsg> ordLineVldRuleList = getOrdLineVldRule(pMsg, rtnDtlPMsg.dsOrdLineCatgCd_B1.getValue());
        if (ordLineVldRuleList == null || ordLineVldRuleList.isEmpty()) {
            return;
        }

        // QC#63527 2024/03/14 Start
        String beforeOwnCd = NWZC150001Query.getInstance().getCpoDetailInvLocCd(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.origCpoOrdNum_B1.getValue(), rtnDtlPMsg.origCpoDtlLineNum_B1.getValue(), rtnDtlPMsg.origCpoDtlLineSubNum_B1.getValue());
        if (beforeOwnCd != null) {
            String afterOwnCd =  NWZC150001Query.getInstance().getInvtyOwnrCd(rtnDtlPMsg.rtlWhCd_B1.getValue(), pMsg.glblCmpyCd.getValue());
            
            if (!beforeOwnCd.equals(afterOwnCd)) {
                if (INVTY_OWNR.CSA.equals(beforeOwnCd)) {
                    NWZC150001Common.addMsgId3List(resPMsg3List, rtnDtlPMsg, "NWZM2278E");
                } else {
                    NWZC150001Common.addMsgId3List(resPMsg3List, rtnDtlPMsg, "NWZM2280E");
                }
            }            
        }
        // QC#63527 2024/03/14 End

        for (DS_ORD_LINE_VLD_RULETMsg ordLineVldRule : ordLineVldRuleList) {
            boolean isValid = isValidValueSet(pMsg.glblCmpyCd.getValue(), ordLineVldRule, rtnDtlPMsg);
            if (!isValid) {
                if (S21StringUtil.isEquals(DS_ORD_VLD_ACT_TP.FLIP_TR_RSN, ordLineVldRule.dsOrdVldActTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(ordLineVldRule.dsOrdAltVldTrxCd) //
                            && ZYPCommonFunc.hasValue(ordLineVldRule.dsOrdAltVldTrxRsnCd)) {
                        rtnDtlPMsg.trxCd_B1.setValue(ordLineVldRule.dsOrdAltVldTrxCd.getValue());
                        rtnDtlPMsg.trxRsnCd_B1.setValue(ordLineVldRule.dsOrdAltVldTrxRsnCd.getValue());
                    }
                } else if (S21StringUtil.isEquals(DS_ORD_VLD_ACT_TP.VALID_ERR, ordLineVldRule.dsOrdVldActTpCd.getValue())) {
                    String msgId = ordLineVldRule.dsOrdVldErrMsgCd.getValue();
                    NWZC150001Common.addMsgId3List(resPMsg3List, rtnDtlPMsg, msgId);
                }
            }
        }
    }
    private List<DS_ORD_LINE_VLD_RULETMsg> getOrdLineVldRule(NWZC150001PMsg pMsg, String dsOrdLineCatgCd) {

        Map<String, String> queryParam = new HashMap<String, String>(0);
        queryParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        queryParam.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
        queryParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);

        List<DS_ORD_LINE_VLD_RULETMsg> rsltList = ordLineVldRuleCache.get(queryParam);
        if (rsltList == null) {
            rsltList = (List<DS_ORD_LINE_VLD_RULETMsg>) this.ssmClient.queryObjectList("getOrdLineVldRule", queryParam);
            this.ordLineVldRuleCache.put(queryParam, rsltList);
        }
        return rsltList;
    }

    private String getMdseTpCdFromLineMsg(String glblCmpyCd, EZDPMsg lineMsg) {

        String coaMdseTpCd = null;

        if (lineMsg instanceof NWZC150001_APMsg) {
            NWZC150001_APMsg linePMsg = (NWZC150001_APMsg) lineMsg;
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, linePMsg.mdseCd_A1.getValue());
            if (mdseTMsg != null) {
                coaMdseTpCd = mdseTMsg.coaMdseTpCd.getValue();
            }
        } else if (lineMsg instanceof NWZC150001_rtnDtlPMsg) {
            NWZC150001_rtnDtlPMsg linePMsg = (NWZC150001_rtnDtlPMsg) lineMsg;
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, linePMsg.mdseCd_B1.getValue());
            if (mdseTMsg != null) {
                coaMdseTpCd = mdseTMsg.coaMdseTpCd.getValue();
            }
        }
        return coaMdseTpCd;
    }

    private String getInvtyOwnrCdFromLineMsg(String glblCmpyCd, EZDPMsg lineMsg) {

        String invtyOwnrCd = "";

        if (lineMsg instanceof NWZC150001_APMsg) {
            NWZC150001_APMsg linePMsg = (NWZC150001_APMsg) lineMsg;
            RTL_WHTMsg rtlWhTMsg = getRtlWhTMsg(glblCmpyCd, linePMsg.rtlWhCd_A1.getValue());
            if (rtlWhTMsg != null) {
                invtyOwnrCd = rtlWhTMsg.invtyOwnrCd.getValue();
            }
        } else if (lineMsg instanceof NWZC150001_rtnDtlPMsg) {
            NWZC150001_rtnDtlPMsg linePMsg = (NWZC150001_rtnDtlPMsg) lineMsg;
            RTL_WHTMsg rtlWhTMsg = getRtlWhTMsg(glblCmpyCd, linePMsg.rtlWhCd_B1.getValue());
            if (rtlWhTMsg != null) {
                invtyOwnrCd = rtlWhTMsg.invtyOwnrCd.getValue();
            }
        }
        return invtyOwnrCd;
    }

    private DS_ORD_VLD_CLT_TPTMsg getDsOrdVldCltTpTMsg(String glblCmpyCd, String dsOrdVldCltTpCd) {

        DS_ORD_VLD_CLT_TPTMsg dsOrdVldCltTpTMsg = new DS_ORD_VLD_CLT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdVldCltTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdVldCltTpTMsg.dsOrdVldCltTpCd, dsOrdVldCltTpCd);

        return (DS_ORD_VLD_CLT_TPTMsg) S21CacheTBLAccessor.findByKey(dsOrdVldCltTpTMsg);
    }

    private DS_ORD_VLD_INCL_TPTMsg getDsOrdVldInclTpTMsg(String glblCmpyCd, String dsOrdVldInclTpCd) {

        DS_ORD_VLD_INCL_TPTMsg dsOrdVldInclTpTMsg = new DS_ORD_VLD_INCL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdVldInclTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdVldInclTpTMsg.dsOrdVldInclTpCd, dsOrdVldInclTpCd);

        return (DS_ORD_VLD_INCL_TPTMsg) S21CacheTBLAccessor.findByKey(dsOrdVldInclTpTMsg);
    }

    private RTL_WHTMsg getRtlWhTMsg(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);

        return (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(rtlWhTMsg);
    }


    private boolean isValidValueSet(String glblCmpyCd, DS_ORD_LINE_VLD_RULETMsg dsOrdLineVldRuleTMsg, EZDPMsg lineMsg) {

        String dsOrdVldTpCd = dsOrdLineVldRuleTMsg.dsOrdVldTpCd.getValue();
        if (S21StringUtil.isEquals(DS_ORD_VLD_TP.MDSE_TYPE, dsOrdVldTpCd)) {
            return isValidMdseType(glblCmpyCd, lineMsg, dsOrdLineVldRuleTMsg);
        } else if (S21StringUtil.isEquals(DS_ORD_VLD_TP.INVENTORY_OWNER, dsOrdVldTpCd)) {
            return isValidInvtyOwnrType(glblCmpyCd, lineMsg, dsOrdLineVldRuleTMsg);
        } else if (S21StringUtil.isEquals(DS_ORD_VLD_TP.MDSE_CODE, dsOrdVldTpCd)) {
            return isValidMdseCode(glblCmpyCd, lineMsg, dsOrdLineVldRuleTMsg);
        // START 2023/02/13 A.Cullano [QC#61158, ADD]
        } else if (S21StringUtil.isEquals(DS_ORD_VLD_TP.MDSE_INSTALL_BASE, dsOrdVldTpCd)) {
            return isIbTrackable(glblCmpyCd, lineMsg, dsOrdLineVldRuleTMsg);
        // END 2023/02/13 A.Cullano [QC#61158, ADD]
        } else {
            return true;
        }
    }

    private boolean isValidMdseType(String glblCmpyCd, EZDPMsg lineMsg, DS_ORD_LINE_VLD_RULETMsg dsOrdLineVldRuleTMsg) {

        if (!hasMdseCd(lineMsg)) {
            return false;
        }
        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", glblCmpyCd);

        DS_ORD_VLD_CLT_TPTMsg dsOrdVldCltTpTMsg = getDsOrdVldCltTpTMsg(glblCmpyCd, dsOrdLineVldRuleTMsg.dsOrdVldCltTpCd.getValue());
        if (dsOrdVldCltTpTMsg == null) {
            return true;
        }
        queryKey.put("mdseTpCtxTpCd", dsOrdVldCltTpTMsg.dsOrdVldCltTpNm.getValue());

        String coaMdseTpCd = getMdseTpCdFromLineMsg(glblCmpyCd, lineMsg);
        if (!ZYPCommonFunc.hasValue(coaMdseTpCd)) {
            return false;
        }

        queryKey.put("coaMdseTpCd", coaMdseTpCd);

        BigDecimal rslt = this.mdseTpValSetCache.get(queryKey);
        if (rslt == null) {
            rslt = (BigDecimal) this.ssmClient.queryObject("getMdseTpValueSetCount", queryKey);
            if (rslt == null) {
                rslt = new BigDecimal(-1);
            }
            this.mdseTpValSetCache.put(queryKey, rslt);
        }
        boolean isExists = false;
        try {
            if (-1 == rslt.intValue()) {
                isExists = false;
            } else {
                isExists = true;
            }
        } catch (Exception ex) {
            isExists = true;
        }

        if (S21StringUtil.isEquals(DS_ORD_VLD_INCL_TP.INCLU, dsOrdLineVldRuleTMsg.dsOrdVldInclTpCd.getValue())) {
            if (isExists) {
                return true;
            } else {
                return false;
            }
        } else {
            if (isExists) {
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean isValidInvtyOwnrType(String glblCmpyCd, EZDPMsg lineMsg, DS_ORD_LINE_VLD_RULETMsg dsOrdLineVldRuleTMsg) {

        // Intangible item doesn't have warehouse.
        if (!hasRtlWhCd(lineMsg)) {
            return true;
        }

        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", glblCmpyCd);

        DS_ORD_VLD_CLT_TPTMsg dsOrdVldCltTpTMsg = getDsOrdVldCltTpTMsg(glblCmpyCd, dsOrdLineVldRuleTMsg.dsOrdVldCltTpCd.getValue());
        if (dsOrdVldCltTpTMsg == null) {
            return true;
        }
        queryKey.put("invtyOwnrCtxTpCd", dsOrdVldCltTpTMsg.dsOrdVldCltTpNm.getValue());

        String invtyOwnrCd = getInvtyOwnrCdFromLineMsg(glblCmpyCd, lineMsg);
        if (!ZYPCommonFunc.hasValue(invtyOwnrCd)) {
            return false;
        }

        queryKey.put("invtyOwnrCd", invtyOwnrCd);

        BigDecimal rslt = this.invtyOwnrTpValSetCache.get(queryKey);
        if (rslt == null) {
            rslt = (BigDecimal) this.ssmClient.queryObject("getInvtyOwnrTpTpValueSetCount", queryKey);
            if (rslt == null) {
                rslt = new BigDecimal(-1);
            }
            this.invtyOwnrTpValSetCache.put(queryKey, rslt);
        }
        boolean isExists = false;
        try {
            if (-1 == rslt.intValue()) {
                isExists = false;
            } else {
                isExists = true;
            }
        } catch (Exception ex) {
            isExists = true;
        }

        if (S21StringUtil.isEquals(DS_ORD_VLD_INCL_TP.INCLU, dsOrdLineVldRuleTMsg.dsOrdVldInclTpCd.getValue())) {
            if (isExists) {
                return true;
            } else {
                return false;
            }
        } else {
            if (isExists) {
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean isValidMdseCode(String glblCmpyCd, EZDPMsg lineMsg, DS_ORD_LINE_VLD_RULETMsg dsOrdLineVldRuleTMsg) {

        if (!hasMdseCd(lineMsg)) {
            return false;
        }
        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", glblCmpyCd);

        DS_ORD_VLD_CLT_TPTMsg dsOrdVldCltTpTMsg = getDsOrdVldCltTpTMsg(glblCmpyCd, dsOrdLineVldRuleTMsg.dsOrdVldCltTpCd.getValue());
        if (dsOrdVldCltTpTMsg == null) {
            return true;
        }
        queryKey.put("mdseCtxTpCd", dsOrdVldCltTpTMsg.dsOrdVldCltTpNm.getValue());

        String reqMdseCd = null;
        if (lineMsg instanceof NWZC150001_APMsg) {
            reqMdseCd = ((NWZC150001_APMsg) lineMsg).mdseCd_A1.getValue();
        } else if (lineMsg instanceof NWZC150001_rtnDtlPMsg) {
            reqMdseCd = ((NWZC150001_rtnDtlPMsg) lineMsg).mdseCd_B1.getValue();
        } else {
            return false;
        }

        String ordTakeMdseCd = null;
        String mdseCd = null;
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        int ordTakeMdseLen = ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();
        if (reqMdseCd.length() > ordTakeMdseLen) {
            ordTakeMdseTMsg.glblCmpyCd.setValue(glblCmpyCd);
            ordTakeMdseTMsg.ordTakeMdseCd.setValue(reqMdseCd.substring(0, ordTakeMdseLen));
            ORD_TAKE_MDSETMsg rsltOrdTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);
            if (rsltOrdTakeMdseTMsg != null) {
                ordTakeMdseCd = rsltOrdTakeMdseTMsg.ordTakeMdseCd.getValue();
                mdseCd = reqMdseCd;
            } else {
                ordTakeMdseCd = reqMdseCd;
                mdseCd = reqMdseCd;
            }
        } else {
            ordTakeMdseTMsg.glblCmpyCd.setValue(glblCmpyCd);
            ordTakeMdseTMsg.ordTakeMdseCd.setValue(reqMdseCd);
            ORD_TAKE_MDSETMsg rsltOrdTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);
            if (rsltOrdTakeMdseTMsg != null) {
                ordTakeMdseCd = reqMdseCd;
                mdseCd = rsltOrdTakeMdseTMsg.mdseCd.getValue();
            } else {
                ordTakeMdseCd = reqMdseCd;
                mdseCd = reqMdseCd;
            }
        }

        queryKey.put("mdseCd", mdseCd);
        queryKey.put("ordTakeMdseCd", ordTakeMdseCd);

        BigDecimal rslt = this.mdseValSetCache.get(queryKey);
        if (rslt == null) {
            rslt = (BigDecimal) this.ssmClient.queryObject("getMdseValueSetCount", queryKey);
            if (rslt == null) {
                rslt = new BigDecimal(-1);
            }
            this.mdseValSetCache.put(queryKey, rslt);
        }
        boolean isExists = false;
        try {
            if (-1 == rslt.intValue()) {
                isExists = false;
            } else {
                isExists = true;
            }
        } catch (Exception ex) {
            isExists = true;
        }

        if (S21StringUtil.isEquals(DS_ORD_VLD_INCL_TP.INCLU, dsOrdLineVldRuleTMsg.dsOrdVldInclTpCd.getValue())) {
            if (isExists) {
                return true;
            } else {
                return false;
            }
        } else {
            if (isExists) {
                return false;
            } else {
                return true;
            }
        }
    }

    // START 2023/02/13 A.Cullano [QC#61158, ADD]
    /**
     * <pre>
     * isIbTrackable
     * @param glblCmpyCd            String
     * @param lineMsg               EZDPMsg
     * @param dsOrdLineVldRuleTMsg  DS_ORD_LINE_VLD_RULETMsg
     * </pre>
     */
    private boolean isIbTrackable(String glblCmpyCd, EZDPMsg lineMsg, DS_ORD_LINE_VLD_RULETMsg dsOrdLineVldRuleTMsg) {

        if (!hasMdseCd(lineMsg)) {
            return false;
        }
        Map<String, String> queryKey = new HashMap<String, String>();
        queryKey.put("glblCmpyCd", glblCmpyCd);

        MDSETMsg mdseTMsg = null;
        if (lineMsg instanceof NWZC150001_APMsg) {
            NWZC150001_APMsg linePMsg = (NWZC150001_APMsg) lineMsg;
            mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, linePMsg.mdseCd_A1.getValue());
            if (mdseTMsg == null) {
                return false;
            }
        } else {
            return false;
        }

        queryKey.put("mdseCd", mdseTMsg.mdseCd.getValue());

        String rslt = this.mdseCdSetCache.get(queryKey);
        if (rslt == null) {
            rslt = mdseTMsg.instlBaseCtrlFlg.getValue();
            if (rslt == null) {
                rslt = "";
            }
            this.mdseCdSetCache.put(queryKey, rslt);
        }
        boolean isTrackable = false;
        if (S21StringUtil.isEquals(ZYPConstant.CHKBOX_ON_Y, rslt)) {
            isTrackable = true;
        }

        if (S21StringUtil.isEquals(DS_ORD_VLD_INCL_TP.INCLU, dsOrdLineVldRuleTMsg.dsOrdVldInclTpCd.getValue())) {
            if (isTrackable) {
                return true;
            } else {
                return false;
            }
        } else {
            if (isTrackable) {
                return false;
            } else {
                return true;
            }
        }
    }
    // END 2023/02/13 A.Cullano [QC#61158, ADD]

    private boolean hasRtlWhCd(EZDPMsg lineMsg) {
        if (lineMsg instanceof NWZC150001_APMsg) {
            return ZYPCommonFunc.hasValue(((NWZC150001_APMsg) lineMsg).rtlWhCd_A1);
        } else if (lineMsg instanceof NWZC150001_rtnDtlPMsg) {
            return ZYPCommonFunc.hasValue(((NWZC150001_rtnDtlPMsg) lineMsg).rtlWhCd_B1);
        }
        return false;
    }

    private boolean hasMdseCd(EZDPMsg lineMsg) {
        if (lineMsg instanceof NWZC150001_APMsg) {
            return ZYPCommonFunc.hasValue(((NWZC150001_APMsg) lineMsg).mdseCd_A1);
        } else if (lineMsg instanceof NWZC150001_rtnDtlPMsg) {
            return ZYPCommonFunc.hasValue(((NWZC150001_rtnDtlPMsg) lineMsg).mdseCd_B1);
        }
        return false;
    }

    // 2018/10/18 S21_NA#26229 Add Start
    private boolean rmaProcValidation(final NWZC150001PMsg pMsg, //
            final NWZC150001_rtnDtlPMsg rtnDtlPMsg, //
            final List<NWZC150003PMsg> resPMsg3List) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
        if (ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1)) {
            svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.svcMachMstrPk_B1.getValue());
        } else if (ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1)) {
            SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = NWZC150001ForDefaultValueSetter.getSvcMachMstrTMsg(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.serNum_B1.getValue(), rtnDtlPMsg.mdseCd_B1.getValue());
            if (svcMachMstrTMsgArray != null && svcMachMstrTMsgArray.getValidCount() > 0) {
                svcMachMstrTMsg = svcMachMstrTMsgArray.no(0);
            }
        }
        if (svcMachMstrTMsg == null) {
            return false;
        }
        String svcMachTrxTpCd = svcMachMstrTMsg.svcMachTrxTpCd.getValue();

        String errMsg = null;
        if (S21StringUtil.isEquals(SVC_MACH_TRX_TP.RENTAL_CSA, svcMachTrxTpCd)) {
            if (!isLineProcRentalReturn(pMsg, rtnDtlPMsg)) {
                errMsg = NWZM2287E;
            }
        } else if (S21StringUtil.isEquals(SVC_MACH_TRX_TP.LOAN_CSA, svcMachTrxTpCd)) {
            // 2020/04/13 QC#56374 Add Start
            if (ZYPCommonFunc.hasValue(rtnDtlPMsg.origCpoOrdNum_B1) && ZYPCommonFunc.hasValue(rtnDtlPMsg.origCpoDtlLineNum_B1) && ZYPCommonFunc.hasValue(rtnDtlPMsg.origCpoDtlLineSubNum_B1)) {
                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, rtnDtlPMsg.origCpoOrdNum_B1);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, rtnDtlPMsg.origCpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, rtnDtlPMsg.origCpoDtlLineSubNum_B1);
                cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);
                if (!DS_ORD_LINE_CATG.LOAN_ORDERS.equals(cpoDtlTMsg.dsOrdLineCatgCd.getValue())) { 
                    return false;
                }
            }
            // 2020/04/13 QC#56374 Add End
            if (!isLineProcLoanReturn(pMsg, rtnDtlPMsg)) {
                errMsg = NWZM2288E;
            }
        }
        if (ZYPCommonFunc.hasValue(errMsg)) {
            NWZC150001Common.addMsgId3List(resPMsg3List, rtnDtlPMsg, errMsg);
            return true;
        }
        return false;
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg findKey = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(findKey.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(findKey.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(findKey);
    }

    private boolean isLineProcRentalReturn(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg) {

        DS_ORD_LINE_PROC_DFNTMsg dsOrdLineProcDfnTMsg = getOrdLineProcDfn(pMsg, rtnDtlPMsg);
        if (dsOrdLineProcDfnTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(ORD_PROC_TP.RENTAL_RETURN, dsOrdLineProcDfnTMsg.ordProcTpCd.getValue());
    }

    private boolean isLineProcLoanReturn(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg) {

        DS_ORD_LINE_PROC_DFNTMsg dsOrdLineProcDfnTMsg = getOrdLineProcDfn(pMsg, rtnDtlPMsg);
        if (dsOrdLineProcDfnTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(ORD_PROC_TP.LOAN_RETURN, dsOrdLineProcDfnTMsg.ordProcTpCd.getValue());
    }

    private DS_ORD_LINE_PROC_DFNTMsg getOrdLineProcDfn(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg) {

        DS_ORD_LINE_PROC_DFNTMsg findKey = new DS_ORD_LINE_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(findKey.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(findKey.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(findKey.dsOrdLineCatgCd, rtnDtlPMsg.dsOrdLineCatgCd_B1);

        return (DS_ORD_LINE_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(findKey);
    }
    // 2018/10/18 S21_NA#26229 Add End
}
