/**
 * <pre>
 * Other Check as CPO Update API
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/05   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/11/01   Fujitsu         S.Takami        Update          S21_NA#22140
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/05/14   Fujitsu         S.Takami        Update          S21_NA#25488
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151
 * 2018/07/23   Fujitsu         S.Takami        Update          S21_NA#24745
 * 2018/10/31   Fujitsu         K.Kato          Update          S21_NA#28921
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.S21_ORGTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.TOCTMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouCpoDtlCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * NWZC150001Common
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * N/A          N/A             N/A             Create          N/A
 * 2018/12/13   Fujitsu         S.Kosaka        Update          QC#29315
 * 2019/04/17   Fujitsu         K.Ishizuka      Update          QC#31184
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * </pre>
 */
public class NWZC150001Common {


    /**
     * <pre>
     * Numberring CPO_ORD_NUM.
     * If API parameter doesn't have CPO_ORD_NUM, this method derives new CPO_ORD_NUM.
     * </pre>
     * @param cpouBean CpouBean
     * @param pMsg NWZC150001 API Parameter
     */
    public static void numberingCpoOrdNum(NWZC150001CpouBean cpouBean, NWZC150001PMsg pMsg) {
        final String methodNm = "numberingCpoOrdNum";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart("NWZC150001CpouCommon", methodNm);

        try {
            final boolean isSaveRqst = NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd());
            final boolean isSubmitRqst = NWZC150001CpouConstant.CPO_SUBMIT.equals(cpouBean.getRqstTpCd());

            // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
            if (S21StringUtil.isEquals(NWZC150001Constant.MODE_VALID, pMsg.xxModeCd.getValue())) {
                return;
            }
            // 2017/04/26 S21_NA#Review structure Lv.2 Add End
            if (isSaveRqst || isSubmitRqst) {

                // TODO CSA(not firm) S.Yamamoto
                if (!hasValue(cpouBean.getCpoOrdNum())) {
                    // 2016/02/15 S21_NA#1618 Mod Start
                    // final String newCpoOrdNum = ZYPNumbering.getUniqueID(cpoBean.getGlblCmpyCd(), "CPO#");
                    final String newCpoOrdNum = ZYPMaxTenDigitsNumbering.getUniqueID("CPO_ORD_NUM");
                    // 2016/02/15 S21_NA#1618 Mod End
                    cpouBean.setReNumCpoOrdNum(newCpoOrdNum);
//                pMsg.cpoOrdNum_A1.setValue(newCpoOrdNum);
                    cpouBean.setCpoOrdNum_A1(newCpoOrdNum);
                } else {
                    cpouBean.setReNumCpoOrdNum(pMsg.cpoOrdNum.getValue()); // 2015/12/03 S21_NA#1396
//                pMsg.cpoOrdNum_A1.setValue(pMsg.cpoOrdNum.getValue());
                    cpouBean.setCpoOrdNum_A1(pMsg.cpoOrdNum.getValue()); // 2015/12/03 S21_NA#1396
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd("NWZC150001CpouCommon", methodNm);
        }
    }

    /**
     * Order Number acquisition
     * 
     * <pre>
     * The one after it numbers it is returned when there is the one after it numbers it.
     * The one before it numbers it is returned when there is no one after it numbers it.
     * </pre>
     * @param cpouBean NWZC150001CpouBean
     * @return Order Number
     */
    public static String getCpoOrdNumFromBean(NWZC150001CpouBean cpouBean) {
        if (hasValue(cpouBean.getReNumCpoOrdNum())) {
            return cpouBean.getReNumCpoOrdNum();
        } else if (hasValue(cpouBean.getCpoOrdNum())) {
            return cpouBean.getCpoOrdNum();
        } else {
            return cpouBean.getCpoOrdNum_A1();
        }
    }


    /**
     * Order Line Number acquisition
     * 
     * <pre>
     * The one after it numbers it is returned when there is the one after it numbers it.
     * The one before it numbers it is returned when there is no one after it numbers it.
     * </pre>
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @return Order Line Number
     */
    public static String getCpoDtlLineNumFromBean(NWZC150001CpouDetailBean cpoDtlBean) {
        if (hasValue(cpoDtlBean.getReNumCpoDtlLineNum())) {
            return cpoDtlBean.getReNumCpoDtlLineNum();
        } else {
            return cpoDtlBean.getCpoDtlLineNum();
        }
    }

    /**
     * Order Line Sub Number acquisition
     * 
     * <pre>
     * The one after it numbers it is returned when there is the one after it numbers it.
     * The one before it numbers it is returned when there is no one after it numbers it.
     * </pre>
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @return Order Line Sub Number
     */
    public static String getCpoDtlLineSubNumFromBean(NWZC150001CpouDetailBean cpoDtlBean) {
        if (hasValue(cpoDtlBean.getReNumCpoDtlLineSubNum())) {
            return cpoDtlBean.getReNumCpoDtlLineSubNum();
        } else {
            return cpoDtlBean.getCpoDtlLineSubNum();
        }
    }

    /**
     * isBomCmpLine
     * @param targetDtlBean Target Line cpoDtlBean
     * @param cpoBean cpoBean
     * @return true: BOM line, false not BOM Line
     */
    public static boolean isBomCmptLine(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean targetDtlBean) {

        if (targetDtlBean.getBomHeaderFlg() == ZYPConstant.FLG_ON_Y) {
            return false;
        }

        String targetPosnNum = targetDtlBean.getDsOrdPosnNum();

        if (!hasValue(targetPosnNum)) {
            return false;
        }

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (targetPosnNum.equals(cpoDtlBean.getCpoDtlLineNum() + cpoDtlBean.getCpoDtlLineSubNum())) {

                if (cpoDtlBean.getBomHeaderFlg() == ZYPConstant.FLG_ON_Y) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Whether error information is set in EZDPMsg is judged.
     * @param resPMsgList List<NWZC150002PMsg>
     * @param msgIdMgr S21ApiMessageIdManager
     * @return boolean (true : There is an error. / false : There is
     * no error. )
     */
    public static boolean hasError(List<NWZC150002PMsg> resPMsgList, S21ApiMessageIdMgr msgIdMgr) {

        if (msgIdMgr.getXxMsgIdListSize() > 0) {
            return true;
        }

        for (NWZC150002PMsg resPMsg : resPMsgList) {
            if (resPMsg.xxMsgIdList.getValidCount() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether error information is set in EZDPMsg is judged.
     * @param resPMsg3List List<NWZC150003PMsg>
     * @param msgIdMgr S21ApiMessageIdManager
     * @return boolean (true : There is an error. / false : There is
     * no error. )
     */
    public static boolean hasReturnDtlError(List<NWZC150003PMsg> resPMsg3List, S21ApiMessageIdMgr msgIdMgr) {

        if (msgIdMgr.getXxMsgIdListSize() > 0) {
            return true;
        }

        for (NWZC150003PMsg resPMsg : resPMsg3List) {
            if (resPMsg.xxMsgIdList.getValidCount() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * return String object "" if string object is null
     * </pre>
     * @param str check target String Object.
     * @return "" if null, other case returns String object same as parameter.
     */
    public static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * <pre>
     * If parameter decimal object is null, this method returns ZERO
     * </pre>
     * @param decimal BigDecimal Object
     * @return ZERO if paramter BigDecimal Object is null, other case returns 
     * BigDecimal object same as parameter
     */
    public static BigDecimal nullToZero(BigDecimal decimal) {
        if (hasValue(decimal)) {
            return decimal;
        }
        return ZERO;
    }

    /**
     * <pre>
     * return sum bc1, bc2, if bc1 or bc2 are Null, add ZERO.
     * </pre>
     * @param bc1 BigDecimal data
     * @param bc2 BigDecimal data
     * @return result of sub bc1 and bc2
     */
    public static BigDecimal add(BigDecimal bc1, BigDecimal bc2) {
        if (!hasValue(bc1)) {
            bc1 = ZERO;
        }
        if (!hasValue(bc2)) {
            bc2 = ZERO;
        }
        return bc1.add(bc2);
    }

    /**
     * Subtraction
     * @param bc1 BigDecimal
     * @param bc2 BigDecimal
     * @return Subtraction result
     */
    public static BigDecimal subtract(BigDecimal bc1, BigDecimal bc2) {
        if (!hasValue(bc1)) {
            bc1 = ZERO;
        }
        if (!hasValue(bc2)) {
            bc2 = ZERO;
        }
        return bc1.subtract(bc2);
    }


    public static String getCoaAcctCdForPrc(NWZC150001CpouBean cpoBean, NWZC150001_priceListPMsg priceData) {

        String prcCondTpCd = priceData.prcCondTpCd.getValue();
        if (!hasValue(prcCondTpCd)) {
            return null;
        }

        PRC_COND_TPTMsg tMsg = new PRC_COND_TPTMsg();
        tMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        tMsg.prcCondTpCd.setValue(prcCondTpCd);

        tMsg = (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.coaAcctCd.getValue();
        }
        return null;
    }


    public static boolean isCancelled(NWZC150001CpouDetailBean cpoDtlBean) {

        final String ordLineStsCd = cpoDtlBean.getOrdLineStsCd();

        return ORD_LINE_STS.CLOSED.equals(ordLineStsCd) || ORD_LINE_STS.CANCELLED.equals(ordLineStsCd);
    }


    public static boolean isSaveMode(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            return true;
        }
        return (ORD_HDR_STS.SAVED.equals(cpoTMsg.ordHdrStsCd.getValue()));
    }

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    /**
     * <pre>
     * @param pMsg2List     pMsg2List
     * @param aPMsg         aPMsg
     * @param msgId         msgId
     * </pre>
     */
    public static void addMsgId2List(List<NWZC150002PMsg> pMsg2List, NWZC150001_APMsg aPMsg, String msgId) {
        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
        setMsgId2(pMsg2, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
        pMsg2List.add(pMsg2);
    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2018/07/23 S21_NA#24745 Add Start
    /**
     * <pre>
     * @param pMsg3List     pMsg3List
     * @param rtnDtlPMsg    rtnDtlPMsg
     * @param msgId         msgId
     * </pre>
     */
    public static void addMsgId3List(List<NWZC150003PMsg> pMsg3List, NWZC150001_rtnDtlPMsg rtnDtlPMsg, String msgId) {
        NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
        setMsgId3(pMsg3, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
        pMsg3List.add(pMsg3);
    }
    // 2018/07/23 S21_NA#24745 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    /**
     * <pre>
     * Set the message id.
     * @param pMsg2 Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    public static void setMsgId2(NWZC150002PMsg pMsg2, String val) {
        int ix = pMsg2.xxMsgIdList.getValidCount();
        if (ix >= pMsg2.xxMsgIdList.length()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, val);
        pMsg2.xxMsgIdList.setValidCount(ix + 1);
    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    // 2016/10/13 S21_NA#7924-2 Add Start
    public static void addMsgId2List(List<NWZC150002PMsg> pMsg2List, String dsOrdPosnNum, String msgId) {
        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
        NWZC150001Common.setMsgId2(pMsg2, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, dsOrdPosnNum);
        pMsg2List.add(pMsg2);
    }
    // 2016/10/13 S21_NA#7924-2 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    // 2016/10/13 S21_NA#7924-2 Add Start
    public static void addMsgId3List(List<NWZC150003PMsg> pMsg3List, String dsOrdPosnNum, String msgId) {
        NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
        setMsgId3(pMsg3, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg3.dsOrdPosnNum, dsOrdPosnNum);
        pMsg3List.add(pMsg3);
    }
    // 2016/10/13 S21_NA#7924-2 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    /**
     * <pre>
     * Set the message id.
     * @param pMsg3 Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    public static void setMsgId3(NWZC150003PMsg pMsg3, String val) {
        int ix = pMsg3.xxMsgIdList.getValidCount();
        if (ix >= pMsg3.xxMsgIdList.length()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgId, val);
        pMsg3.xxMsgIdList.setValidCount(ix + 1);
    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2018/10/31 S21_NA#28921 Add Start
    /**
     * <pre>
     * Set the message id.
     * @param pMsg2 Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    public static void setMsgId4(NWZC150002PMsg pMsg2, String msgId, String[] msgParam) {
        int ix = pMsg2.xxMsgIdList.getValidCount();
        if (ix >= pMsg2.xxMsgIdList.length()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, msgId);
        int msgParamLen = msgParam.length;
        switch (msgParamLen) {
            case 5:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_4, msgParam[4]);
            case 4:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_3, msgParam[3]);
            case 3:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_2, msgParam[2]);
            case 2:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_1, msgParam[1]);
            case 1:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_0, msgParam[0]);
                break;
            default:
                break;
        }
        pMsg2.xxMsgIdList.setValidCount(ix + 1);
    }
    // 2018/10/31 S21_NA#28921 Add End

    // Add Start 2018/02/26 QC#22967
    /**
     * @param configPMsg NWZC150001_cpoConfigPMsg
     * @param pMsg2 NWZC150002PMsg
     * @param pMsg3 NWZC150003PMsg
     * @param msgId String
     */
    public static void setMsgIdForConfig(NWZC150001_cpoConfigPMsg configPMsg, NWZC150002PMsg pMsg2, NWZC150003PMsg pMsg3, String msgId) {
        String configCatgCd = configPMsg.configCatgCd.getValue();

        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
            setMsgId2(pMsg2, msgId);
        } else if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
            setMsgId3(pMsg3, msgId);
        }
    }
    // Add End 2018/02/26 QC#22967

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    public static String getCoaBrCd(String glblCmpyCd, String slsRepCd, NWZC150001CpouLocalCache cache) {
        TOCTMsg tMsg = new TOCTMsg();

        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, slsRepCd);
//
//        tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        tMsg = cache.tocCache.getTMsgByKey(glblCmpyCd, slsRepCd);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End

        if (tMsg == null) {
            return "";
        }
        return tMsg.coaBrCd.getValue();
    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2018/05/20 S21_NA#25604 Del Start
//    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
//    // For Performance QC#8166 Add Start
//    public static boolean isExistOrdCatgFromCache(NWZC150001PMsg pMsg, String ordCatgCtxTpCd, NWZC150001DsCpoCommonBean commonBean) {
//
////        // Mapkey
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
//        params.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
//        params.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
//        params.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());
//
//        if (commonBean.getOrdCatgCacheMap().containsKey(params)) {
//            return commonBean.getOrdCatgCacheMap().get(params);
//        }
//
//        boolean isExistOrdCatg = NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ordCatgCtxTpCd);
//        commonBean.getOrdCatgCacheMap().put(params, isExistOrdCatg);
//
//        return isExistOrdCatg;
//    }
//    // For Performance QC#8166 Add End
//    // 2017/05/15 S21_NA#Review structure Lv.2 Add End
    // 2018/05/20 S21_NA#25604 Del End

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    /**
     * <pre>
     * Set the message id.
     * @param msgIdMgr S21ApiMessageIdMgr.
     * @param pMsg Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    public static void setMsgId(S21ApiMessageIdMgr msgIdMgr, NWZC150001PMsg pMsg, String val) {
        msgIdMgr.addXxMsgId(val, pMsg);
    }
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2017/05/15 S21_NA#Review structure Lv.2 Add Start
    // 2016/10/13 S21_NA#7924-2 Add Start
    public static boolean isAvalableTocCd(String glblCmpyCd, String tocCd) {

        S21_ORGTMsg s21OrgMsg = new S21_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(s21OrgMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(s21OrgMsg.tocCd, tocCd);
        s21OrgMsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgMsg);
        if (s21OrgMsg == null) {
            return false;
        }
        return true;
    }
    // 2016/10/13 S21_NA#7924-2 Add End
    // 2017/05/15 S21_NA#Review structure Lv.2 Add End

    // 2017/11/01 S21_NA#22140 Add Start
    /**
     * <pre>
     * Check the config has Credit details
     * </pre>
     * @param configPMsg DS CPO Config Parameter
     * @param pMsg NWZC150001 API Parameter
     * @return true: detected Config has Credit(s) false: no Credit Detail
     */
    public static boolean isCreditConfig(NWZC150001_cpoConfigPMsg configPMsg, NWZC150001PMsg pMsg) {

        String configCatgCd = configPMsg.configCatgCd.getValue();
        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
            for (int n = 0; n < pMsg.A.getValidCount(); n++) {
                NWZC150001_APMsg aPmsg = pMsg.A.no(n);
                boolean isCreditDtl = S21StringUtil.isEquals(CR_REBIL.CREDIT, aPmsg.crRebilCd_A1.getValue());
                if (S21StringUtil.isEquals(dsOrdPosnNum, aPmsg.dsOrdPosnNum_A1.getValue()) //
                        && isCreditDtl) {
                    return true;
                }
            }
        } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) {
            for (int n = 0; n < pMsg.rtnDtl.getValidCount(); n++) {
                NWZC150001_rtnDtlPMsg rtnDtlPmsg = pMsg.rtnDtl.no(n);
                boolean isCreditDtl = S21StringUtil.isEquals(CR_REBIL.CREDIT, rtnDtlPmsg.crRebilCd_B1.getValue());
                if (S21StringUtil.isEquals(dsOrdPosnNum, rtnDtlPmsg.dsOrdPosnNum_B1.getValue()) //
                        && isCreditDtl) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     * Check all details are rebill details which are related detected config.
     * </pre>
     * @param configPMsg DS CPO Config Parameter
     * @param pMsg NWZC150001 API Parameter
     * @return true: All details are Rebill Detail in detected Config false: there are one or more Rebill Details
     */
    public static boolean isAllDetailRebillConfig(NWZC150001_cpoConfigPMsg configPMsg, NWZC150001PMsg pMsg) {

        String configCatgCd = configPMsg.configCatgCd.getValue();
        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
            for (int n = 0; n < pMsg.A.getValidCount(); n++) {
                NWZC150001_APMsg aPmsg = pMsg.A.no(n);
                boolean isRebillDtl = S21StringUtil.isEquals(CR_REBIL.REBILL,  aPmsg.crRebilCd_A1.getValue());
                if (S21StringUtil.isEquals(dsOrdPosnNum, aPmsg.dsOrdPosnNum_A1.getValue()) //
                        && !isRebillDtl) {
                    return false;
                }
            }
        } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) {
            for (int n = 0; n < pMsg.rtnDtl.getValidCount(); n++) {
                NWZC150001_rtnDtlPMsg rtnDtlPmsg = pMsg.rtnDtl.no(n);
                boolean isRebillDtl = S21StringUtil.isEquals(CR_REBIL.REBILL, rtnDtlPmsg.crRebilCd_B1.getValue());
                if (S21StringUtil.isEquals(dsOrdPosnNum, rtnDtlPmsg.dsOrdPosnNum_B1.getValue()) //
                        && !isRebillDtl) {
                    return false;
                }
            }
        }
        return true;
    }
    // 2017/11/01 S21_NA#22140 Add End

    // 2018/05/14 S21_NA#25488 Add Start
    /**
     * <pre>
     * referenced CPO_DTL is closed or not
     * @param pMsg CPO Update API Parameter
     * @param linePMsg line CPO Update API Parameter
     * @return true: referenced CPO_DTL is closed, false: not closed
     */
    public static boolean isReferencedDtlIsClosed(NWZC150001PMsg pMsg, NWZC150001_APMsg linePMsg) {

        // 2018/06/05 S21_NA#25151 Del Start
//        if (!ZYPCommonFunc.hasValue(linePMsg.refCpoDtlLineNum_A1) //
//                && !ZYPCommonFunc.hasValue(linePMsg.refCpoDtlLineSubNum_A1)) {
//            return true;
//        }
        // 2018/06/05 S21_NA#25151 Del End
        CPO_DTLTMsg cpoDtlTMsg = getReferencedDtl(pMsg, linePMsg);
        if (cpoDtlTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(ORD_LINE_STS.CLOSED, cpoDtlTMsg.ordLineStsCd.getValue());
    }

    /**
     * <pre>
     * get cpo_dtl recode by pMsg.cpoOrdNum, linePMsg.refCpoDtlLineNum, linePMsg.refCpoDtlLineSubNum
     * @param pMsg CPO Update API Parameter
     * @param linePMsg line CPO Update API Parameter
     * @return CPO_DTLTMsg (referenced line)
     */
    public static CPO_DTLTMsg getReferencedDtl(NWZC150001PMsg pMsg, NWZC150001_APMsg linePMsg) {

        // 2018/06/05 S21_NA#25151 Rewrite this Logic without any comments.
        if (ZYPCommonFunc.hasValue(linePMsg.refCpoDtlLineNum_A1) //
                && ZYPCommonFunc.hasValue(linePMsg.refCpoDtlLineSubNum_A1)) {
            CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, pMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, linePMsg.refCpoDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, linePMsg.refCpoDtlLineSubNum_A1);

            return (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);
        } else {
            String dplyLineRefNum = linePMsg.dplyLineRefNum_A1.getValue();
            if (!ZYPCommonFunc.hasValue(dplyLineRefNum)) {
                return null;
            }
            String numArray[] = dplyLineRefNum.split("\\.");
            NWZC150001CpouFindCondition cpoDtlFindCond = null;
            // 2019/04/17 S21_NA#31184 Mod Start
            if (numArray.length < 2) {
                return null;
            } else if (numArray.length == 2) {
            // if (numArray.length == 2) {
            // 2019/04/17 S21_NA#31184 Mod End
                cpoDtlFindCond = new NWZC150001CpouFindCondition("021");
            } else {
                cpoDtlFindCond = new NWZC150001CpouFindCondition("022");
            }
            cpoDtlFindCond.addCondition("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            cpoDtlFindCond.addCondition("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
            cpoDtlFindCond.addCondition("dsOrdPosnNum01", numArray[0]);
            cpoDtlFindCond.addCondition("dsCpoLineNum01", numArray[1]);
            if (numArray.length > 2) {
                cpoDtlFindCond.addCondition("dsCpoLineSubNum01", numArray[2]);
            }
            CPO_DTLTMsgArray cpoDtlTMsgArray = NWZC150001CpouCpoDtlCache.getInstance().getTMsgArray(cpoDtlFindCond);
            if (cpoDtlTMsgArray == null || cpoDtlTMsgArray.getValidCount() == 0) {
                return null;
            }
            CPO_DTLTMsg rsltCpoDtlTMsg = cpoDtlTMsgArray.no(0);
            ZYPEZDItemValueSetter.setValue(linePMsg.refCpoDtlLineNum_A1, rsltCpoDtlTMsg.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(linePMsg.refCpoDtlLineSubNum_A1, rsltCpoDtlTMsg.cpoDtlLineSubNum);

            return rsltCpoDtlTMsg;
        }

    }
    // 2018/05/14 S21_NA#25488 Add End
    // 2018/06/05 S21_NA#25151 Add Start
    /**
     * <pre>
     * Compare BigDecimal object, if both object have same value, this method return true.
     * if both object are null, this method return true.
     * if one object is null and other object has value, this method return false.
     * @param cmp1 BigDecimal object for comparing.
     * @param cmp2 BigDecimal object for comparing.
     * @return true: same, false: different
     * </pre>
     */
    public static boolean isEqualsBigDecimal(BigDecimal cmp1, BigDecimal cmp2) {

        if (cmp1 == null && cmp2 == null) {
            return true;
        }
        if (cmp1 == null && cmp2 != null) {
            return false;
        }
        if (cmp1 != null && cmp2 == null) {
            return false;
        }
        if (cmp1.compareTo(cmp2) == 0) {
            return true;
        } else {
            return false;
        }
    }
    // 2018/06/05 S21_NA#25151 Add End

    /**
     * autoCast
     * @param <T> T
     * @param fromObj Object
     * @return T
     */
    public static <T> T autoCast(Object fromObj) {
        T toObj = (T) fromObj;
        return toObj;
    }
    
    // 2018/12/13 QC#29315 Add Start
    /**
     * getShipToLocNum
     * @param pMsg NWZC150001PMsg
     * @param localCache NWZC150001CpouLocalCache
     * @return ShipToLocNum
     */
    public static String getShipToLocNum(NWZC150001PMsg pMsg, NWZC150001CpouLocalCache localCache) {
        String shipToLocNum = null;
        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("004");
        fc.addCondition("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        fc.addCondition("shipToCustCd01", pMsg.addShipToCustCd.getValue());
        final SHIP_TO_CUSTTMsgArray resShipToCustTMsgArray = localCache.shipToCustCache.getTMsgArray(fc);
        if (resShipToCustTMsgArray.getValidCount() > 0) {
            SHIP_TO_CUSTTMsg resShipToCustTMsg = (SHIP_TO_CUSTTMsg) resShipToCustTMsgArray.get(0);
            shipToLocNum = resShipToCustTMsg.locNum.getValue();
        }
        return shipToLocNum;
    }
    // 2018/12/13 QC#29315 Add End

    // 2019/11/27 QC#52339 Add Start 
    /**
     * isIntangibleItem
     * @param pglblCmpyCdMsg String
     * @param mdseCd String
     * @return boolean Intangible Item:True
     */
    public static boolean isIntangibleItem(String glblCmpyCd, String mdseCd) {
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);

        return ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue());
    }

    public static MDSETMsg getMdse(String  glblCmpyCd, String mdseCd) {
        MDSETMsg mdseTMsg = new MDSETMsg();

        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();

            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd);
            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(ordTakeMdseTMsg);

            if (!(ordTakeMdseTMsg == null)) {
                mdseTMsg = new MDSETMsg();

                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, ordTakeMdseTMsg.mdseCd.getValue());
                mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
            }
        }

        return mdseTMsg;
    }
    // 2019/11/27 QC#52339 Add End 
}
