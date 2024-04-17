/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 * 2016/09/08   CSAI            K.Lee           Update          QC#14398
 *</pre>
 */
package business.blap.NLCL0290.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0290.NLCL0290CMsg;
import business.blap.NLCL0290.NLCL0290Query;
import business.blap.NLCL0290.NLCL0290SMsg;
import business.blap.NLCL0290.NLCL0290_ACMsg;
import business.blap.NLCL0290.NLCL0290_ASMsg;
import business.blap.NLCL0290.constant.NLCL0290Constant;
import business.db.ADJ_TRX_TPTMsg;
import business.db.INVTYTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_DTLTMsg;
import business.db.RWS_DTLTMsgArray;
import business.db.RWS_HDRTMsg;
import business.db.RWS_SERTMsg;
import business.db.RWS_SERTMsgArray;
import business.db.SCE_ORD_TPTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NLXC023001PMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC004001PMsg;
import business.parts.NLZC060001PMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC206001PMsg;
import business.parts.NLZC207001PMsg;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.constant.NLZC004001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC206001.NLZC206001;
import com.canon.cusa.s21.api.NLZ.NLZC207001.NLZC207001;
import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC023001.NLXC023001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2016/11/18   CITS            K.Kameoka       Update          QC#15705
 * 2017/01/27   CITS            R.Shimamoto     Update          QC#17190
 * 2017/01/10   CITS            Y.Fujii         Update          QC#16674-2
 * 2017/12/25   CITS            T.Hakodate      Update          QC#23229
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472/18490
 * 2018/11/30   CITS            T.Hakodate      Update          QC#29172
 * 2018/12/18   CITS            M.Naito         Update          QC#28769
 * 2019/02/04   CITS            T.Hakodate      Update          QC#30125
 * 2019/05/09   CITS            T.Tokutomi      Update          QC#50008
 * 2019/05/09   CITS            T.Tokutomi      Update          QC#50439
 * 2019/06/10   CITS            M.Naito         Update          QC#50751
 * 2020/06/04   CITS            M.Furugoori     Update          QC#56720
 * 2020/07/10   CITS            K.Ogino         Update          QC#57258
 * 2021/03/08   CITS            A.Marte         Update          QC#58503
 * 2022/10/21   HITACHI         N.Takatsu       Update          QC#60603
 *</pre>
 */
public class NLCL0290CommonLogic implements NLCL0290Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    public static NLCL0290Query getQuery() {
        return NLCL0290Query.getInstance();
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NLCL0290CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NLCL0290CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;

        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
        }
        shareMsgAry.setValidCount(bizMsg.xxPageShowOfNum_A.getValueInt());
    }

    /**
     * Previous Page
     * @param bizMsg NLCL0290CMsg
     * @param globalMsg NLCL0290SMsg
     */
    public static void prevPage(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Next Page
     * @param bizMsg NLCL0290CMsg
     * @param globalMsg NLCL0290SMsg
     */
    public static void nextPage(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NLCL0290CMsg
     * @param globalMsg NLCL0290SMsg
     */
    public static void firstErrorPage(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, int errNum) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        BigDecimal errPageFromNum = getErrorPageFromNum(bizMsg, globalMsg, errNum);
        bizMsg.xxPageShowFromNum_A.setValue(errPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NLCL0290CMsg
     * @param globalMsg NLCL0290SMsg
     */
    public static void lastPage(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum_A.setValue(lastPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NLCL0290CMsg
     * @param globalMsg NLCL0290SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
        int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NLCL0290CMsg
     * @param globalMsg NLCL0290SMsg
     * @return BigDecimal
     */
    public static BigDecimal getErrorPageFromNum(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, int errNum) {
        errNum++;
        int pageCnt = errNum / bizMsg.A.length();
        int errPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (errNum % bizMsg.A.length() == 0) {
            errPageFromNum = errPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(errPageFromNum);
    }

    public static String getRtlWhNm(String glblCmpyCd, String rtlWhCd) {
        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return null;
        }
        RTL_WHTMsg inMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg != null) {
            return outMsg.rtlWhNm.getValue();
        }
        return null;
    }

    /**
     * setPulldownAdjTrxTypeList
     * @param cMsg NLCL0290CMsg
     */
    public static void setPulldownAdjTrxTypeList(String glblCmpyCd, NLCL0290CMsg bizMsg) {

        bizMsg.adjTrxTpCd_LC.clear();
        bizMsg.adjTrxTpDescTxt_LD.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getAdjTrxTypeList(param);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ZYPEZDItemValueSetter.setValue(bizMsg.adjTrxTpCd_LC.no(i), (String) resultMap.get(i).get("ADJ_TRX_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.adjTrxTpDescTxt_LD.no(i), (String) resultMap.get(i).get("ADJ_TRX_TP_DESC_TXT"));
            }
        }
    }

    
    // QC:18472
    /**
     * setPulldownLocStsList
     * @param cMsg NLCL0290CMsg
     */
//    public static void setPulldownLocStsList(String glblCmpyCd, NLCL0290CMsg bizMsg) {
//
//        bizMsg.locStsCd_LC.clear();
//        bizMsg.locStsDescTxt_LD.clear();
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("bizAppId", "NLCL0290");
//
//        // Execute
//        S21SsmEZDResult result = NLCL0290Query.getInstance().getLocStsList(param);
//
//        if (result.isCodeNormal()) {
//
//            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();
//
//            for (int i = 0; i < resultMap.size(); i++) {
//
//                ZYPEZDItemValueSetter.setValue(bizMsg.locStsCd_LC.no(i), (String) resultMap.get(i).get("LOC_STS_CD"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.locStsDescTxt_LD.no(i), (String) resultMap.get(i).get("LOC_STS_DESC_TXT"));
//            }
//        }
//    }

    /**
     * setPulldownStkStsList
     * @param cMsg NLCL0290CMsg
     */
    public static void setPulldownStkStsList(String glblCmpyCd, NLCL0290CMsg bizMsg) {

        bizMsg.stkStsCd_LC.clear();
        bizMsg.stkStsDescTxt_LD.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("bizAppId", "NLCL0290");

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getStkStsList(param);

        if (result.isCodeNormal()) {

            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();

            for (int i = 0; i < resultMap.size(); i++) {

                ZYPEZDItemValueSetter.setValue(bizMsg.stkStsCd_LC.no(i), (String) resultMap.get(i).get("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.stkStsDescTxt_LD.no(i), (String) resultMap.get(i).get("STK_STS_DESC_TXT"));
            }
        }
    }

 // QC:18472
    /**
     * setAdjAcctAliasList
     * @param cMsg NLCL0290CMsg
     */
//    public static void setAdjAcctAliasList(String glblCmpyCd, String rtlWhCd, NLCL0290CMsg bizMsg) {
//
//        bizMsg.adjAcctAliasNm_LC.clear();
//        bizMsg.adjAcctAliasNm_LD.clear();
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("rtlWhCd", rtlWhCd);
//        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
//
//        // Execute
//        S21SsmEZDResult result = NLCL0290Query.getInstance().getAdjAcctAliasList(param);
//
//        if (result.isCodeNormal()) {
//            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();
//            for (int i = 0; i < resultMap.size(); i++) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.adjAcctAliasNm_LC.no(i), (String) resultMap.get(i).get("ADJ_ACCT_ALIAS_NM"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.adjAcctAliasNm_LD.no(i), (String) resultMap.get(i).get("ADJ_ACCT_ALIAS_NM"));
//            }
//        }
//    }

    // QC:18472
    /**
     * getAdjAcctAliasMap
     * @param cMsg NLCL0290CMsg
     */
//    public static Map<String, Object> getAdjAcctAliasMap(String glblCmpyCd, NLCL0290CMsg bizMsg) {
//
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("rtlWhCd", bizMsg.rtlWhCd_H.getValue());
//        param.put("adjAcctAliasNm", bizMsg.adjAcctAliasNm_H.getValue());
//        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
//
//        // Execute
//        S21SsmEZDResult result = NLCL0290Query.getInstance().getAdjAcctAliasMap(param);
//        Map<String, Object> AdjAcctAliasMap = null;
//        if (result.isCodeNormal()) {
//            AdjAcctAliasMap = (Map<String, Object>) result.getResultObject();
//        }
//        return AdjAcctAliasMap;
//    }

    /**
     * setPulldownRtlSwhList
     * @param cMsg NLCL0290CMsg
     */
    public static void setPulldownRtlSwhList(String glblCmpyCd, NLCL0290CMsg bizMsg) {

        bizMsg.rtlSwhCd_LC.clear();
        bizMsg.rtlSwhCd_LD.clear();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", bizMsg.rtlWhCd_H.getValue());
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getRtlSwhList(param);

        if (result.isCodeNormal()) {
            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd_LC.no(i), (String) resultMap.get(i).get("RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd_LD.no(i), (String) resultMap.get(i).get("RTL_SWH_CD"));
            }
        }
    }

    /**
     * getRtlWhMap
     * @param cMsg NLCL0290CMsg
     */
    public static Map<String, Object> getRtlWhMap(String glblCmpyCd, String rtlWhNm) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhNm", rtlWhNm);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getRtlWhMap(param);

        Map<String, Object> resultMap = null;
        if (result.isCodeNormal()) {
            resultMap = (Map<String, Object>) result.getResultObject();
        }
        return resultMap;
    }

    /**
     * getRtlWhMap
     * @param cMsg NLCL0290CMsg
     */
    public static Map<String, Object> getRtlWhMap(String glblCmpyCd, String rltWhCd, String rtlWhNm) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(rltWhCd)) {
            param.put("rtlWhCd", rltWhCd);
        } else if (ZYPCommonFunc.hasValue(rtlWhNm)) {
            param.put("rtlWhNm", rtlWhNm);
        }
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getRtlWhMap(param);

        Map<String, Object> resultMap = null;
        if (result.isCodeNormal()) {
            resultMap = (Map<String, Object>) result.getResultObject();
        }
        return resultMap;
    }

    /**
     * getAdjTrxTpTMsg
     * @param cMsg NLCL0290CMsg
     */
    public static ADJ_TRX_TPTMsg getAdjTrxTpTMsg(String glblCmpyCd, String adjTrxTpCd) {

        ADJ_TRX_TPTMsg inMsg = new ADJ_TRX_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.adjTrxTpCd, adjTrxTpCd);
        ADJ_TRX_TPTMsg outMsg = (ADJ_TRX_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * getRTL_SWHTMsg
     * @param cMsg NLCL0290CMsg
     */
    public static RTL_SWHTMsg getRTL_SWHTMsg(String glblCmpyCd, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg inMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, rtlSwhCd);
        RTL_SWHTMsg outMsg = (RTL_SWHTMsg) S21CacheTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * getMdseMap
     * @param cMsg NLCL0290CMsg
     */
    public static Map<String, Object> getMdseMap(String glblCmpyCd, String mdseCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getMdseMap(param);

        Map<String, Object> resultMap = null;
        if (result.isCodeNormal()) {
            resultMap = (Map<String, Object>) result.getResultObject();
        }
        return resultMap;
    }

    /**
     * getIbMap
     * @param cMsg NLCL0290CMsg
     */
    public static Map<String, Object> getIbMap(String glblCmpyCd, String mdseCd, String serNum, String adjTrxTp , String stkStsCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("serNum", serNum);

        // QC#29172 add
        if (ZYPCommonFunc.hasValue(adjTrxTp) && ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(adjTrxTp) && ZYPCommonFunc.hasValue(stkStsCd)) {
            param.put("stkStsCd", stkStsCd);
        }

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getIbMap(param);

        Map<String, Object> resultMap = null;
        if (result.isCodeNormal()) {
            resultMap = (Map<String, Object>) result.getResultObject();
        }
        return resultMap;

    }

    /**
     * getAdjTrxTpCd
     * @param cMsg NLCL0290CMsg
     */
    public static String getAdjTrxTpCd(String glblCmpyCd, String adjTrxTpDescTxt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("adjTrxTpDescTxt", adjTrxTpDescTxt);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getAdjTrxTpCd(param);

        String adjTrxTpCd = null;
        if (result.isCodeNormal()) {
            adjTrxTpCd = (String) result.getResultObject();
        }
        return adjTrxTpCd;
    }

    /**
     * getLocStsCd
     * @param cMsg NLCL0290CMsg
     */
    public static String getLocStsCd(String glblCmpyCd, String locStsDescTxt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locStsDescTxt", locStsDescTxt);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getLocStsCd(param);

        String locStsCd = null;
        if (result.isCodeNormal()) {
            locStsCd = (String) result.getResultObject();
        }
        return locStsCd;
    }

    /**
     * getStkStsCd
     * @param cMsg NLCL0290CMsg
     */
    public static String getStkStsCd(String glblCmpyCd, String stkStsDescTxt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("stkStsDescTxt", stkStsDescTxt);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getStkStsCd(param);

        String stkStsCd = null;
        if (result.isCodeNormal()) {
            stkStsCd = (String) result.getResultObject();
        }
        return stkStsCd;
    }

    /**
     * getAdjCatgCd
     * @param cMsg NLCL0290CMsg
     */
    public static String getAdjCatgCd(String glblCmpyCd, String adjCatgDescTxt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("adjCatgDescTxt", adjCatgDescTxt);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getAdjCatgCd(param);

        String adjCatgCd = null;
        if (result.isCodeNormal()) {
            adjCatgCd = (String) result.getResultObject();
        }
        return adjCatgCd;
    }

    /**
     * getInvtyTMsg
     * @param cMsg NLCL0290CMsg
     */
    public static INVTYTMsg getInvtyTMsg(String glblCmpyCd, String invtyLocCd, String mdseCd, String stkStsCd, String locStsCd) {

        INVTYTMsg inMsg = new INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.invtyLocCd, invtyLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.stkStsCd, stkStsCd);
        ZYPEZDItemValueSetter.setValue(inMsg.locStsCd, locStsCd);
        INVTYTMsg outMsg = (INVTYTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    public static List<NLZC004001PMsg> getAdjustmentApiPMsgDetailList(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {

        // QC#29172 add start
        boolean isConfig = false;
        String changedStockStatus = null;
        // QC#29172 add end
        
        // QC#50008
        boolean isTechOrCustomerAdjust = isTechOrCustomer(glblCmpyCd, bizMsg.rtlWhCd_H.getValue());

        List<NLZC004001PMsg> list = new ArrayList<NLZC004001PMsg>();

        NLZC004001PMsg hdrPMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(hdrPMsg.xxModeCd, NLZC004001Constant.MODE_NO_WARNING);
        // QC:18472 Start
        // QC#29172 add start: QC#50008 Update. Add condition.
        if (isTechOrCustomerAdjust) {
            ZYPEZDItemValueSetter.setValue(hdrPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
        } else if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {
            ZYPEZDItemValueSetter.setValue(hdrPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
        } else {
            ZYPEZDItemValueSetter.setValue(hdrPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT);
        }
        // QC#29172 add end
        ZYPEZDItemValueSetter.setValue(hdrPMsg.svcConfigMstrPk, bizMsg.svcConfigMstrPk);
        // QC:18472 End
        ZYPEZDItemValueSetter.setValue(hdrPMsg.xxDtTpCd, NLZC004001Constant.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyOrdTpCd, bizMsg.invtyOrdTpCd_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyLocCd, bizMsg.rtlWhCd_H);
        // QC:18472 Start
        ZYPEZDItemValueSetter.setValue(hdrPMsg.locStsCd, LOC_STS.DC_STOCK);
        // QC:18472 End
        ZYPEZDItemValueSetter.setValue(hdrPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.adjTrxTpCd, bizMsg.adjTrxTpCd_H);
        list.add(hdrPMsg);

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            // QC:18490 Start
            if (i == 0 && ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {
                // QC#29172 add start
                isConfig = true;
                changedStockStatus = globalMsg.A.no(i).stkStsCd_A.getValue();
                // QC#29172 add end
                i++;
            }
            // QC:18490 End

            NLZC004001PMsg dtlPMsg = new NLZC004001PMsg();

            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyOrdNum_T, hdrPMsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyOrdLineNum_T, ZYPCommonFunc.leftPad(String.valueOf(i + 1), 3, "0"));
            // QC:18472 Start
            if (0 < (globalMsg.A.no(i).ordQty_A.getValueInt())) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_IN);
            } else if (0 > (globalMsg.A.no(i).ordQty_A.getValueInt())) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_OUT);
            }
            // QC:18472 End
            ZYPEZDItemValueSetter.setValue(hdrPMsg.xxModeCd, NLZC004001Constant.MODE_NO_WARNING);
            // QC:18472 Start
            // QC#29172 add start: QC#50008 Update. Add condition.
            if (isTechOrCustomerAdjust) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
            } else if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
            } else {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT);
            }
            // QC#29172 add end
            // QC:18472 End
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxDtTpCd, NLZC004001Constant.DT_TP_DTL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdNum, globalMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdTpCd, bizMsg.invtyOrdTpCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd, globalMsg.A.no(i).fromInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_D1, globalMsg.A.no(i).toInvtyLocCd_A);
            if (!ZYPCommonFunc.hasValue(dtlPMsg.invtyLocCd_D1)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_D1, globalMsg.A.no(i).fromInvtyLocCd_A);
            }
            // QC:18472 Start
            ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk, globalMsg.A.no(i).svcMachMstrPk_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd_D1, LOC_STS.DC_STOCK);
            // QC:18472 End

            ZYPEZDItemValueSetter.setValue(dtlPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);

            ZYPEZDItemValueSetter.setValue(dtlPMsg.adjTrxTpCd, bizMsg.adjTrxTpCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd, globalMsg.A.no(i).mdseCd_A);
            // QC:18472
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd, bizMsg.stkStsCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty, globalMsg.A.no(i).ordQty_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.adjCatgCd, globalMsg.A.no(i).adjCatgCd_A);
            // START 2020/06/04 [QC#56720,ADD]
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cycleCntRsnCd, globalMsg.A.no(i).adjCatgCd_A);
            // END 2020/06/04 [QC#56720,ADD]
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineCmntTxt, globalMsg.A.no(i).invtyOrdLineCmntTxt_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineCostAmt, globalMsg.A.no(i).invtyOrdLineCostAmt_A);
            // QC:18472
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.adjAcctAliasNm, bizMsg.adjAcctAliasNm_H);
            // QC:18472 Start
            // QC#29172 add start
            if (isConfig) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.toStkStsCd, changedStockStatus);
            } else {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.toStkStsCd, globalMsg.A.no(i).stkStsCd_A);
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd, globalMsg.A.no(i).stkStsCd_A);
            // QC#29172 add end
            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaAcctCd, globalMsg.A.no(i).xxScrItem130Txt_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaCmpyCd, globalMsg.A.no(i).coaCmpyCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaAfflCd, globalMsg.A.no(i).coaAfflCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaBrCd, globalMsg.A.no(i).coaBrCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaCcCd, globalMsg.A.no(i).coaCcCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaProdCd, globalMsg.A.no(i).coaProdCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaChCd, globalMsg.A.no(i).coaChCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaProjCd, globalMsg.A.no(i).coaProjCd_A);
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.coaExtnCd, globalMsg.A.no(i).coaExtnCd_A);
            // QC:18472 End
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk, globalMsg.A.no(i).svcConfigMstrPk_A);
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdNum, globalMsg.A.no(i).invtyOrdNum_T);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).serNum, globalMsg.A.no(i).serNum_A);
                dtlPMsg.serialInfoList.setValidCount(1);
            }
            list.add(dtlPMsg);
        }
        return list;
    }

    public static NLZC003001PMsg getInventoryOrderApiPMsgHeader(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        NLZC003001PMsg hdrPMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(hdrPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyOrdTpCd, bizMsg.invtyOrdTpCd_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyLocCd, bizMsg.rtlWhCd_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.locStsCd, bizMsg.locStsCd_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.trxCd, bizMsg.trxCd_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.trxRsnCd, bizMsg.trxRsnCd_H);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(hdrPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        // QC:18472 Start
        // Finalized = Awaiting Approval
        ZYPEZDItemValueSetter.setValue(hdrPMsg.invtyOrdStsCd, INVTY_ORD_STS.FINALIZED);
        // QC:18472 End
        ZYPEZDItemValueSetter.setValue(hdrPMsg.adjTrxTpCd, bizMsg.adjTrxTpCd_H);

        // QC#23229 T.Hakodate ADD START
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).svcConfigMstrPk_A)) {
                ZYPEZDItemValueSetter.setValue(hdrPMsg.svcConfigMstrPk, globalMsg.A.no(i).svcConfigMstrPk_A);
                break;
            }
        }
        // QC#23229 T.Hakodate ADD END

        return hdrPMsg;
    }

    public static List<NLZC003001PMsg> getInventoryOrderApiPMsgDetailList(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, NLZC003001PMsg hdrPMsg) {

        List<NLZC003001PMsg> list = new ArrayList<NLZC003001PMsg>();

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            NLZC003001PMsg dtlPMsg = new NLZC003001PMsg();

            // QC:18490 Start
            if (i == 0 && ZYPConstant.FLG_OFF_0.equals(globalMsg.A.no(i).xxConfigFlg_A.getValue())) {
                i++;
            }
            // QC:18490 End

            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyOrdNum_T, hdrPMsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyOrdLineNum_T, ZYPCommonFunc.leftPad(String.valueOf(i + 1), 3, "0"));

            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdNum, globalMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdTpCd, bizMsg.invtyOrdTpCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd, globalMsg.A.no(i).fromInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_D1, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd, bizMsg.locStsCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.locStsCd_D1, bizMsg.locStsCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.adjTrxTpCd, bizMsg.adjTrxTpCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd, globalMsg.A.no(i).mdseCd_A);
            // QC:18472 Start
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd, bizMsg.stkStsCd_H);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty, globalMsg.A.no(i).ordQty_A.getValue());
            // QC:18472 End
            ZYPEZDItemValueSetter.setValue(dtlPMsg.adjCatgCd, globalMsg.A.no(i).adjCatgCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineCmntTxt, globalMsg.A.no(i).invtyOrdLineCmntTxt_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdLineCostAmt, globalMsg.A.no(i).invtyOrdLineCostAmt_A);
            // QC:18472
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.adjAcctAliasNm, bizMsg.adjAcctAliasNm_H);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaCmpyCd, globalMsg.A.no(i).coaCmpyCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaAfflCd, globalMsg.A.no(i).coaAfflCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaBrCd, globalMsg.A.no(i).coaBrCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaCcCd, globalMsg.A.no(i).coaCcCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaAcctCd, globalMsg.A.no(i).coaAcctCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaProdCd, globalMsg.A.no(i).coaProdCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaChCd, globalMsg.A.no(i).coaChCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaProjCd, globalMsg.A.no(i).coaProjCd_A);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.coaExtnCd, globalMsg.A.no(i).coaExtnCd_A);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rmvConfigFlg, ZYPConstant.FLG_OFF_N);
            // QC:18472 Start
            // Finalized = Awaiting Approval
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.FINALIZED);
            // QC:18472 End
            ZYPEZDItemValueSetter.setValue(dtlPMsg.adjTrxTpCd, bizMsg.adjTrxTpCd_H);
            // QC#23229 T.Hakodate ADD START
            ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk, globalMsg.A.no(i).svcConfigMstrPk_A);
            // QC#23229 T.Hakodate ADD END
            
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdNum, globalMsg.A.no(i).invtyOrdNum_T);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).invtyOrdLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serialInfoList.no(0).serNum, globalMsg.A.no(i).serNum_A);
                dtlPMsg.serialInfoList.setValidCount(1);
            }

            list.add(dtlPMsg);
        }
        return list;
    }

    public static List<NSZC001001PMsg> getSvcMachMstrApiPMsgListForUpdateInventory(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        List<NSZC001001PMsg> list = new ArrayList<NSZC001001PMsg>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).svcMachMstrPk_A)) {
                NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, globalMsg.A.no(i).svcMachMstrPk_A);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                // QC:18472
                //ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, bizMsg.stkStsCd_H);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrLocStsCd, bizMsg.locStsCd_H);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, globalMsg.A.no(i).toInvtyLocCd_A);
                list.add(svcMachMstrApiPMsg);

                svcMachMstrApiPMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, globalMsg.A.no(i).svcMachMstrPk_A);
                list.add(svcMachMstrApiPMsg);
            }
        }
        return list;
    }

    public static List<NSZC001001PMsg> getSvcMachMstrApiPMsgListForAllocation(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        List<NSZC001001PMsg> list = new ArrayList<NSZC001001PMsg>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).svcMachMstrPk_A)) {
                NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, globalMsg.A.no(i).svcMachMstrPk_A);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, globalMsg.A.no(i).invtyOrdNum_T);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineSubNum, "001");
                list.add(svcMachMstrApiPMsg);
            }
        }
        return list;
    }

    public static List<NWZC107001PMsg> getAllocNonCpoApiPMsgList(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        List<NWZC107001PMsg> list = new ArrayList<NWZC107001PMsg>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            NWZC107001PMsg nwzc107001PMsg = new NWZC107001PMsg();
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxCd, bizMsg.trxCd_H);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxRsnCd, bizMsg.trxRsnCd_H);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxHdrNum, globalMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.trxLineSubNum, "001");
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.mdseCd, globalMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.invtyLocCd, globalMsg.A.no(i).fromInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.locStsCd, bizMsg.locStsCd_H);
            // QC:18472
            //ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.stkStsCd, bizMsg.stkStsCd_H);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxRqstQty, globalMsg.A.no(i).ordQty_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxUnitPrc, globalMsg.A.no(i).unitPrcAmt_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.xxSlsAmt, globalMsg.A.no(i).invtyOrdLineCostAmt_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.billToCustCd, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.sellToCustCd, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.shipToCustCd, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(nwzc107001PMsg.rsdDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            list.add(nwzc107001PMsg);
        }

        return list;
    }

    public static List<NWZC003001PMsg> getShippingPlanUpdateApiPMsgList(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        List<NWZC003001PMsg> list = new ArrayList<NWZC003001PMsg>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            NWZC003001PMsg shpgPlnApiPMsg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxHdrNum, globalMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxLineSubNum, "001");
            if (globalMsg.A.no(i).ordQty_A.getValue().compareTo(globalMsg.A.no(i).invtyAvalQty_A.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.avalSoQty, globalMsg.A.no(i).invtyAvalQty_A);
            } else {
                ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.avalSoQty, globalMsg.A.no(i).ordQty_A);
            }

            ZYPEZDItemValueSetter.setValue(shpgPlnApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            list.add(shpgPlnApiPMsg);
        }
        return list;
    }

    public static List<NLZC205001PMsg> getSoApiPMsgList(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        List<NLZC205001PMsg> list = new ArrayList<NLZC205001PMsg>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            NLXC023001PMsg nlxc023001PMsg = new NLXC023001PMsg();
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxHdrNum, globalMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
            NLXC023001 nlxc023001 = new NLXC023001();
            nlxc023001.execute(nlxc023001PMsg, ONBATCH_TYPE.ONLINE);

            NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.sceOrdTpCd, SCE_ORD_TP.SUB_WH_CHANGE);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgPlnNum, nlxc023001PMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_NEW);
            list.add(soApiPMsg);
        }
        return list;
    }

    // 2017/01/27 QC#17190 Mod.
    public static NLZC200001PMsg getRwsApiPMsg(List<NLZC205001PMsg> soApiPMsgList) {
//        List<NLZC200001PMsg> list = new ArrayList<NLZC200001PMsg>();
//        for (NLZC205001PMsg soApiPMsg : soApiPMsgList) {
//            NLZC200001PMsg rwsApiPMsg = new NLZC200001PMsg();
//            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.glblCmpyCd, soApiPMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
//            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
//            ZYPEZDItemValueSetter.setValue(rwsApiPMsg.soNum, soApiPMsg.soNum);
//            list.add(rwsApiPMsg);
//        }
        NLZC200001PMsg rwsApiPMsg = new NLZC200001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.glblCmpyCd, soApiPMsgList.get(0).glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.soNum, soApiPMsgList.get(0).soNum);

        return rwsApiPMsg;
    }

    public static NLZC304001PMsg getRwsSerApiPMsg(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, NLZC200001PMsg rwsApiPMsg) {
        NLZC304001PMsg rwsSerApi = new NLZC304001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsSerApi.glblCmpyCd, glblCmpyCd);
        // 2017/01/27 QC#17190 Mod.
//        ZYPEZDItemValueSetter.setValue(rwsSerApi.rwsNum, rwsApiPMsgList.get(0).RWSNumList.no(0).rwsNum);
        ZYPEZDItemValueSetter.setValue(rwsSerApi.rwsNum, rwsApiPMsg.RWSNumList.no(0).rwsNum);
        int SerialListIdx = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).rwsDtlLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
                ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).serNum, globalMsg.A.no(i).serNum_A);
                ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).mdseCd, globalMsg.A.no(i).mdseCd_A);
                ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).putAwayChkStsCd, PUT_AWAY_CHK_STS.NO_NEED);
                ZYPEZDItemValueSetter.setValue(rwsSerApi.SerialList.no(SerialListIdx).serNumSendFlg, ZYPConstant.FLG_OFF_N);
                SerialListIdx++;
            }
        }
        rwsSerApi.SerialList.setValidCount(SerialListIdx);

        return rwsSerApi;
    }

    public static List<NLZC002001PMsg> getInventoryUpdateApiPMsgList(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        SCE_ORD_TPTMsg sceOrdTpInMsg = new SCE_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(sceOrdTpInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sceOrdTpInMsg.inbdOtbdCd, INBD_OTBD.INBOUND);
        ZYPEZDItemValueSetter.setValue(sceOrdTpInMsg.sceOrdTpCd, SCE_ORD_TP.SUB_WH_CHANGE);
        SCE_ORD_TPTMsg sceOrdTpOutMsg = (SCE_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(sceOrdTpInMsg);

        if (sceOrdTpOutMsg == null) {
            return null;
        }

        List<NLZC002001PMsg> list = new ArrayList<NLZC002001PMsg>();
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // Out Bound
            NLZC002001PMsg invtyUpdApiPMsg = new NLZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.trxCd, bizMsg.trxCd_H);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.trxRsnCd, bizMsg.trxRsnCd_H);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.mdseCd, globalMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyLocCd, globalMsg.A.no(i).fromInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.locStsCd, bizMsg.locStsCd_H);
            // QC:18472
            //ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.stkStsCd, bizMsg.stkStsCd_H);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstQty, globalMsg.A.no(i).ordQty_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyTrxDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxSysTp, NLZC002001.TRX_DTL_DOM);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyOrdNum, globalMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyOrdLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.shipToCustCd, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.uomCd, PKG_UOM.EACH);
            list.add(invtyUpdApiPMsg);

            // Inbound
            invtyUpdApiPMsg = new NLZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.trxCd, sceOrdTpOutMsg.trxCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.trxRsnCd, sceOrdTpOutMsg.trxRsnCd);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.mdseCd, globalMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyLocCd, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.locStsCd, bizMsg.locStsCd_H);
            // QC:18472
            //ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.stkStsCd, bizMsg.stkStsCd_H);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxRqstQty, globalMsg.A.no(i).ordQty_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyTrxDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.xxSysTp, NLZC002001.TRX_DTL_DOM);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyOrdNum, globalMsg.A.no(i).invtyOrdNum_T);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.invtyOrdLineNum, globalMsg.A.no(i).invtyOrdLineNum_T);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.shipFromLocCustCd, globalMsg.A.no(i).fromInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.shipToCustCd, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(invtyUpdApiPMsg.uomCd, PKG_UOM.EACH);
            list.add(invtyUpdApiPMsg);
        }
        return list;
    }

    public static NLZC302001PMsg getSerialUpdateApiPMsg(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        NLZC302001PMsg serialUpdateApiPMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.glblCmpyCd, glblCmpyCd);
        int serialApiPMsgIdx = 0;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serNum, globalMsg.A.no(i).serNum_A);
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).mdseCd, globalMsg.A.no(i).mdseCd_A);
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxTs, getSystemDateTime());
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxEventCd, SER_TRX_EVENT.SUB_WAREHOUSE_CHANGE);
                // QC:18472
                //ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).toStkStsCd, bizMsg.stkStsCd_H);
                ZYPEZDItemValueSetter.setValue(serialUpdateApiPMsg.UpdateDetailList.no(serialApiPMsgIdx).manCratFlg, ZYPConstant.FLG_ON_Y);
                serialApiPMsgIdx++;
            }
            serialUpdateApiPMsg.UpdateDetailList.setValidCount(serialApiPMsgIdx);
        }
        return serialUpdateApiPMsg;
    }

    /**
     * @param glblCmpyCd String
     * @param rwsHdrTMsg RWS_HDRTMsg
     * @param rwsDtlTMsgList ArrayList<RWS_DTLTMsg>
     * @param dsRwsDtlTMsgList ArrayList<RWS_DTLTMsg>
     * @param sMsgALine NLBL2020_ASMsg
     * @return NLZC206001PMsg
     */
    public static NLZC206001PMsg getRwsPutAwayApiPMsg(String glblCmpyCd, RWS_HDRTMsg rwsHdrTMsg) {

        String rwsNum = rwsHdrTMsg.rwsNum.getValue();

        RWS_DTLTMsg rwsDtlInMsg = new RWS_DTLTMsg();
        rwsDtlInMsg.setSQLID("002");
        rwsDtlInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsDtlInMsg.setConditionValue("rwsNum01", rwsNum);
        RWS_DTLTMsgArray rwsDtlArray = (RWS_DTLTMsgArray) EZDTBLAccessor.findByCondition(rwsDtlInMsg);

        RWS_SERTMsg rwsSerInMsg = new RWS_SERTMsg();
        rwsSerInMsg.setSQLID("002");
        rwsSerInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rwsSerInMsg.setConditionValue("rwsNum01", rwsNum);
        RWS_SERTMsgArray rwsSerArray = (RWS_SERTMsgArray) EZDTBLAccessor.findByCondition(rwsSerInMsg);

        NLZC206001PMsg putAwayS21DcApiPMsg = new NLZC206001PMsg();

        // Set Parameter
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));

        int rwsDtlCnt = 0;
        int rwsSerCnt = 0;

        for (; rwsDtlCnt < rwsDtlArray.getValidCount(); rwsDtlCnt++) {

            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsNum, rwsDtlArray.no(rwsDtlCnt).rwsNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsDtlLineNum, rwsDtlArray.no(rwsDtlCnt).rwsDtlLineNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).invtyStkStsCd, rwsDtlArray.no(rwsDtlCnt).invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkQty, rwsDtlArray.no(rwsDtlCnt).rwsQty.getValue().abs());
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).whCd, rwsDtlArray.no(rwsDtlCnt).invtyLocCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).mdseCd, rwsDtlArray.no(rwsDtlCnt).mdseCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsRefNum, rwsHdrTMsg.rwsRefNum);
        }

        // Set Serial
        for (; rwsSerCnt < rwsSerArray.getValidCount(); rwsSerCnt++) {
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).rwsDtlLineNum, rwsSerArray.no(rwsSerCnt).rwsLineNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).serNum, rwsSerArray.no(rwsSerCnt).serNum);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).mdseCd, rwsSerArray.no(rwsSerCnt).mdseCd);
        }
        putAwayS21DcApiPMsg.RWSPutAwayList.setValidCount(rwsDtlCnt);
        putAwayS21DcApiPMsg.RcvSerNumList.setValidCount(rwsSerCnt);

        return putAwayS21DcApiPMsg;
    }

    /**
     * setSoCloseParam
     * @param cMsg NLBL2020CMsg
     * @param rwsHdrMap Map<String, Object>
     * @return NLZC207001PMsg
     */
    public static NLZC207001PMsg getRwsCpltApiPMsg(String glblCmpyCd, RWS_HDRTMsg rwsHdrTMsg) {

        NLZC207001PMsg pMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsHdrTMsg.rwsNum);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsCloDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, rwsHdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rwsHdrTMsg.rwsRefNum);

        return pMsg;
    }

    private static String getSystemDateTime() {

        String yyyymmdd = ZYPDateUtil.getSalesDate();
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");

        return yyyymmdd + hhmmss;
    }

    public static boolean isAutoTransRetailWh(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", rtlWhCd);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().countSubWarehouseTransferControl(param);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return true;
        }
        return false;
    }

    // QC:18472 Start
    public static boolean checkAllowAccount(String glblCmpyCd, String adjTrxTpCd, String coaAcctCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("resrcObjNm", BUSINESS_ID + adjTrxTpCd);
        param.put("coaAcctCd", coaAcctCd);
        param.put("enblFlg", ZYPConstant.FLG_ON_Y);

        // Execute SQL
        S21SsmEZDResult result = NLCL0290Query.getInstance().checkAllowAccount(param);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return true;
        }
        return false;
    }

    public static boolean checkConfig(String glblCmpyCd, String mdseCd, String serNum) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("serNum", serNum);

        // Execute SQL
        S21SsmEZDResult result = NLCL0290Query.getInstance().checkConfig(param);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return true;
        }
        return false;
    }
    // QC:18472 End

    // QC:18472
    /**
     * Validate Segment string for Header.
     * @param glblCmpyCd String
     * @param cMsg NLCL0290CMsg
     * @param xxScrItem130Txt String
     * @return boolean
     */
//    public static boolean validateSegmentStringForHeader(String glblCmpyCd, NLCL0290CMsg cMsg, NLCL0290SMsg sMsg, String xxScrItem130Txt, boolean winFlg) {
//
//        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);
//
//        if (!hasValue(xxScrItem130Txt)) {
//            return true;
//        }
//        String[] list = xxScrItem130Txt.split(Pattern.quote(delimiter), -1);
//        List<String> tokenList = new ArrayList<String>();
//        for (String val : list) {
//            tokenList.add(val);
//        }
//
//        // validate check.
//        if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, sMsg, tokenList, 0, false, false, winFlg)) {
//            return false;
//        }
//
//        if (!winFlg) {
//
//            // GL Code Combination Check API NFZC102001
//            NFZC102001 afzc102001 = new NFZC102001();
//            NFZC102001PMsg paramMsg = new NFZC102001PMsg();
//
//            paramMsg.glblCmpyCd.setValue(glblCmpyCd);
//            paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
//            paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
//            paramMsg.xxArcsApiChkFlg.setValue("");
//            paramMsg.coaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//            paramMsg.coaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//            paramMsg.coaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//            paramMsg.coaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//            paramMsg.coaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//            paramMsg.coaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//            paramMsg.coaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//            paramMsg.coaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//            paramMsg.coaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//            paramMsg.resrcObjNm.setValue(BUSINESS_ID);
//
//            afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);
//
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                if (msgPrms != null && msgPrms.length > 0) {
//                    // QC#15705 Start
//                    // ZYPEZDItemValueSetter.setValue(cMsg.xxMsgPrmTxt,
//                    // msgPrms[0]);
//                    // }
//                    // cMsg.setMessageInfo(msgId, msgPrms);
//                    // QC#15705 End
//
//                    if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
//                        cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
//                    }
//                    // QC#15705 Start
//                } else {
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, msgId, null);
//                }
//                // QC#15705 End
//
//                return false;
//            }
//        }
//
//        ZYPEZDItemValueSetter.setValue(cMsg.coaCmpyCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaBrCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaCcCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaAcctCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaChCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//        ZYPEZDItemValueSetter.setValue(cMsg.coaExtnCd_H, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//
//        return true;
//    }

    /**
     * Validate Segment string for Detail.
     * @param glblCmpyCd
     * @param cMsg
     * @param xxScrItem130Txt
     * @return
     */
    public static boolean validateSegmentStringForDetail(String glblCmpyCd, NLCL0290CMsg cMsg, NLCL0290SMsg sMsg, boolean submitFlg, boolean winFlg) {

        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);
        int index = cMsg.xxNum.getValueInt();

        if (!submitFlg) {

            NLCL0290_ACMsg ACMsg = cMsg.A.no(index);

            String xxScrItem130Txt = ACMsg.xxScrItem130Txt_A.getValue();

            if (!hasValue(xxScrItem130Txt)) {
                return true;
            }
            String[] list = xxScrItem130Txt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // QC:18472
            // validate check.
//            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, sMsg, tokenList, index, true, false, winFlg)) {
//                return false;
//            }

            if (!winFlg) {
                // GL Code Combination Check API NFZC102001
                NFZC102001 afzc102001 = new NFZC102001();
                NFZC102001PMsg paramMsg = new NFZC102001PMsg();

                paramMsg.glblCmpyCd.setValue(glblCmpyCd);
                paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxArcsApiChkFlg.setValue("");
                paramMsg.coaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                paramMsg.coaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                paramMsg.coaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                paramMsg.coaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                paramMsg.coaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                paramMsg.coaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                paramMsg.coaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                paramMsg.coaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                paramMsg.coaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
                paramMsg.resrcObjNm.setValue(BUSINESS_ID);

                afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    if (msgPrms != null && msgPrms.length > 0) {

                    	cMsg.setMessageInfo(msgId, msgPrms);
                        if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
                            ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
                        }

                    } else {
                    	 ACMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, null);
                    }

                    return false;
                }
            }

            ZYPEZDItemValueSetter.setValue(ACMsg.coaCmpyCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaAfflCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaBrCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaCcCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaAcctCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaProdCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaChCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaProjCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(ACMsg.coaExtnCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

        } else {

            NLCL0290_ASMsg ASMsg = sMsg.A.no(index);

            String xxScrItem130Txt = ASMsg.xxScrItem130Txt_A.getValue();

            if (!hasValue(xxScrItem130Txt)) {
                return true;
            }
            String[] list = xxScrItem130Txt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // QC:18472
            // validate check.
//            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, sMsg, tokenList, index, true, true, winFlg)) {
//                return false;
//            }

            if (!winFlg) {
                // GL Code Combination Check API NFZC102001
                NFZC102001 afzc102001 = new NFZC102001();
                NFZC102001PMsg paramMsg = new NFZC102001PMsg();

                paramMsg.glblCmpyCd.setValue(glblCmpyCd);
                paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                paramMsg.xxArcsApiChkFlg.setValue("");
                paramMsg.coaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
                paramMsg.coaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
                paramMsg.coaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
                paramMsg.coaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
                paramMsg.coaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
                paramMsg.coaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
                paramMsg.coaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
                paramMsg.coaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
                paramMsg.coaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
                paramMsg.resrcObjNm.setValue(BUSINESS_ID);

                afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    if (msgPrms != null && msgPrms.length > 0) {

                    	cMsg.setMessageInfo(msgId, msgPrms);
                        if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
                        }
                        if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
                        }

                    } else {
                    	ASMsg.xxScrItem130Txt_A.setErrorInfo(1, msgId, null);
                    }

                    return false;
                }
            }

            ZYPEZDItemValueSetter.setValue(ASMsg.coaCmpyCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaAfflCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaBrCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaCcCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaAcctCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaProdCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaChCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaProjCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(ASMsg.coaExtnCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));

        }

        return true;
    }

    // QC:18472
    /**
     * Validate Segment string token list.
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NLCL0290CMsg
     * @param tokenList tokenList
     * @return true/false
     */
//    public static boolean validateSegmentStringTokenList(String glblCmpyCd, NLCL0290CMsg cMsg, NLCL0290SMsg sMsg, List<String> tokenList, int index, boolean detailFlg, boolean submitFlg, boolean winFlg) {
//
//        if (!detailFlg) {
//
//            if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
//                cMsg.setMessageInfo(NLCM0065E);
//                cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
//                return false;
//            }
//
//            // Header Account
//            if (!winFlg) {
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CMPY });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_EXTN });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CC });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_ACCT });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROJ });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROD });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_AFFL });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CH });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_BR });
//                    return false;
//                }
//
//                // default check
//                // QC#15705 Start
//                if (ZYPConstant.FLG_ON_Y.equals(cMsg.coaEnblFlg_H.getValue())) {
//                    // QC#15705 End
//                    if (ZYPCommonFunc.hasValue(cMsg.coaCmpyCd_HG)) {
//                        if (!cMsg.coaCmpyCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CMPY });
//                            return false;
//                        }
//                    }
//                    if (ZYPCommonFunc.hasValue(cMsg.coaExtnCd_HG)) {
//                        if (!cMsg.coaExtnCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_EXTN });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(cMsg.coaCcCd_HG)) {
//                        if (!cMsg.coaCcCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CC });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(cMsg.coaAcctCd_HG)) {
//                        if (!cMsg.coaAcctCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_ACCT });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(cMsg.coaProjCd_HG)) {
//                        if (!cMsg.coaProjCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_PROJ });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(cMsg.coaProdCd_HG)) {
//                        if (!cMsg.coaProdCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_PROD });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(cMsg.coaAfflCd_HG)) {
//                        if (!cMsg.coaAfflCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_AFFL });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(cMsg.coaChCd_HG)) {
//                        if (!cMsg.coaChCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CH });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(cMsg.coaBrCd_HG)) {
//                        if (!cMsg.coaBrCd_HG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            cMsg.xxScrItem130Txt_H.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_BR });
//                            return false;
//                        }
//                    }
//                    // QC#15705 Start
//                }
//                // QC#15705 End
//
//            } else {
//
//                if (ZYPCommonFunc.hasValue(cMsg.coaCmpyCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, cMsg.coaCmpyCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaExtnCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, cMsg.coaExtnCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaCcCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, cMsg.coaCcCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaAcctCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, cMsg.coaAcctCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaProjCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, cMsg.coaProjCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaProdCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, cMsg.coaProdCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaAfflCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, cMsg.coaAfflCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaChCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, cMsg.coaChCd_HG.getValue());
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.coaBrCd_HG)) {
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, cMsg.coaBrCd_HG.getValue());
//                }
//
//                // window PopUp
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH));
//                tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR));
//
//            }
//
//        } else {
//            // Detail Account
//            if (!submitFlg) {
//
//                NLCL0290_ACMsg ACMsg = cMsg.A.no(index);
//
//                if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
//                    return false;
//                }
//
//                if (!winFlg) {
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CMPY });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_EXTN });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CC });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_ACCT });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROJ });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROD });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_AFFL });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CH });
//                        return false;
//                    }
//
//                    if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
//                        cMsg.setMessageInfo(NLCM0065E);
//                        ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_BR });
//                        return false;
//                    }
//
//                    // default check
//                    // QC#15705 Start
//                    if (ZYPConstant.FLG_ON_Y.equals(ACMsg.coaEnblFlg_A.getValue())) {
//                        // QC#15705 End
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaCmpyCd_AG)) {
//                            if (!ACMsg.coaCmpyCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CMPY });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaExtnCd_AG)) {
//                            if (!ACMsg.coaExtnCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_EXTN });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaCcCd_AG)) {
//                            if (!ACMsg.coaCcCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CC });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaAcctCd_AG)) {
//                            if (!ACMsg.coaAcctCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_ACCT });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaProjCd_AG)) {
//                            if (!ACMsg.coaProjCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_PROJ });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaProdCd_AG)) {
//                            if (!ACMsg.coaProdCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_PROD });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaAfflCd_AG)) {
//                            if (!ACMsg.coaAfflCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_AFFL });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaChCd_AG)) {
//                            if (!ACMsg.coaChCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CH });
//                                return false;
//                            }
//                        }
//
//                        if (ZYPCommonFunc.hasValue(ACMsg.coaBrCd_AG)) {
//                            if (!ACMsg.coaBrCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
//                                cMsg.setMessageInfo(NLCM0065E);
//                                ACMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_BR });
//                                return false;
//                            }
//                        }
//                        // QC#15705 Start
//                    }
//                    // QC#15705 End
//
//                } else {
//
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaCmpyCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, ACMsg.coaCmpyCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaExtnCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, ACMsg.coaExtnCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaCcCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, ACMsg.coaCcCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaAcctCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, ACMsg.coaAcctCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaProjCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, ACMsg.coaProjCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaProdCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, ACMsg.coaProdCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaAfflCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, ACMsg.coaAfflCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaChCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, ACMsg.coaChCd_AG.getValue());
//                    }
//                    if (ZYPCommonFunc.hasValue(ACMsg.coaBrCd_AG)) {
//                        tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, ACMsg.coaBrCd_AG.getValue());
//                    }
//
//                    // window PopUp
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH));
//                    tokenList.set(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD, splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR));
//
//                }
//
//            } else {
//
//                NLCL0290_ASMsg ASMsg = sMsg.A.no(index);
//
//                if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CMPY });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CMPY });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CC });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_ACCT });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROJ });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROD });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_AFFL });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CH });
//                    return false;
//                }
//
//                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
//                    cMsg.setMessageInfo(NLCM0065E);
//                    ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_BR });
//                    return false;
//                }
//
//                // default check
//                // QC#15705 Start
//                if (ZYPConstant.FLG_ON_Y.equals(ASMsg.coaEnblFlg_A.getValue())) {
//                    // QC#15705 End
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaCmpyCd_AG)) {
//                        if (!ASMsg.coaCmpyCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CMPY });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaExtnCd_AG)) {
//                        if (!ASMsg.coaExtnCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ;
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_EXTN });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaCcCd_AG)) {
//                        if (!ASMsg.coaCcCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CC });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaAcctCd_AG)) {
//                        if (!ASMsg.coaAcctCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_ACCT });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaProjCd_AG)) {
//                        if (!ASMsg.coaProjCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_PROJ });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaProdCd_AG)) {
//                        if (!ASMsg.coaProdCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_PROD });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaAfflCd_AG)) {
//                        if (!ASMsg.coaAfflCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_AFFL });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaChCd_AG)) {
//                        if (!ASMsg.coaChCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_CH });
//                            return false;
//                        }
//                    }
//
//                    if (ZYPCommonFunc.hasValue(ASMsg.coaBrCd_AG)) {
//                        if (!ASMsg.coaBrCd_AG.getValue().equals(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
//                            cMsg.setMessageInfo(NLCM0065E);
//                            ASMsg.xxScrItem130Txt_A.setErrorInfo(1, NLCM0065E, new String[] {MSG_PARAM_BR });
//                            return false;
//                        }
//                    }
//                    // QC#15705 Start
//                }
//                // QC#15705 End
//
//            }
//        }
//
//        return true;
//    }

    private static boolean validateSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return true;
        }
        if (element.length() > len) {
            return false;
        }
        return true;
    }

    private static String splitSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return null;
        }
        if (element.length() > len) {
            element = element.substring(0, len);
        }
        return element;
    }

    /**
     * getAdjAcctAliasNm
     * @param glblCmpyCd        String
     * @param rtlWhCd           String
     * @param adjAcctAliasNm    String
     * @return AdjAcctAliasMap  Map<String, Object>
     */
    public static Map<String, Object> getAdjAcctAliasNm(String glblCmpyCd, String rtlWhCd, String adjAcctAliasNm) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", rtlWhCd);
        param.put("adjAcctAliasNm", adjAcctAliasNm);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getAdjAcctAliasNm(param);
        Map<String, Object> AdjAcctAliasMap = null;
        if (result.isCodeNormal()) {
            AdjAcctAliasMap = (Map<String, Object>) result.getResultObject();
        }
        return AdjAcctAliasMap;
    }

    /**
     * getRtlSwhCdList
     * @param glblCmpyCd        String
     * @param rtlWhCd           String
     * @return rtlSwhCdList     List<String>
     */
    public static List<String> getRtlSwhCdList(String glblCmpyCd, String rtlWhCd) {

        List<String> rtlSwhCdList = new ArrayList<String>();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", rtlWhCd);
        param.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().getRtlSwhList(param);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> resultMap = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                rtlSwhCdList.add((String) resultMap.get(i).get("RTL_SWH_CD"));
            }
        }
        return rtlSwhCdList;
    }

    
    
    // QC#29172 add start
    /**
     * execInventoryOrder
     * @param glblCmpyCd
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static List<NLZC003001PMsg>  execInventoryOrder(String glblCmpyCd, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {

        boolean isConfigSpec = false;
        String changedStockStatus = null;

        List<NLZC003001PMsg> invtyOrdUpdApiPMsgList = new ArrayList<NLZC003001PMsg>();

        NLZC003001PMsg pMsg4Hdr = new NLZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.xxDtTpCd, NLZC003001.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.invtyOrdTpCd, INVTY_ORD_TP.STOCK_STATUS_CHANGE);

        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.invtyLocCd, bizMsg.rtlWhCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.trxRsnCd, TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pMsg4Hdr.adjTrxTpCd, ADJ_TRX_TP.STOCK_STATUS_CHANGE);
        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg4Hdr.svcConfigMstrPk, bizMsg.svcConfigMstrPk);
        }

        invtyOrdUpdApiPMsgList.add(pMsg4Hdr);
        int lineNum = 1;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            if (i == 0 && ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {
                isConfigSpec = true;
                changedStockStatus = globalMsg.A.no(i).toStkStsCd_A.getValue();
                i++;
            }
            NLZC003001PMsg pMsg4Dtl = new NLZC003001PMsg();

            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.xxDtTpCd, NLZC003001.DT_TP_DTL);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyOrdTpCd, INVTY_ORD_TP.STOCK_STATUS_CHANGE);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyOrdLineNum, ZYPCommonFunc.leftPad(String.valueOf(lineNum), 3, "0"));
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyLocCd, globalMsg.A.no(i).fromInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.mdseCd, globalMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.stkStsCd, globalMsg.A.no(i).stkStsCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyLocCd_D1, globalMsg.A.no(i).toInvtyLocCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.locStsCd_D1, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.ordQty, globalMsg.A.no(i).ordQty_A);
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);

            if (isConfigSpec) {
                ZYPEZDItemValueSetter.setValue(pMsg4Dtl.svcConfigMstrPk, globalMsg.A.no(i).svcConfigMstrPk_A);
                ZYPEZDItemValueSetter.setValue(pMsg4Dtl.toStkStsCd, changedStockStatus);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg4Dtl.toStkStsCd, globalMsg.A.no(i).toStkStsCd_A);
            }
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg4Dtl.serialInfoList.no(0).invtyOrdLineNum, ZYPCommonFunc.leftPad(String.valueOf(lineNum), 3, "0"));
                ZYPEZDItemValueSetter.setValue(pMsg4Dtl.serialInfoList.no(0).serNum, globalMsg.A.no(i).serNum_A);
                pMsg4Dtl.serialInfoList.setValidCount(1);
            }
            
             // QC#30125 add start
            ZYPEZDItemValueSetter.setValue(pMsg4Dtl.invtyOrdLineCmntTxt, globalMsg.A.no(i).invtyOrdLineCmntTxt_A);
            // QC#30125 add end
            
            invtyOrdUpdApiPMsgList.add(pMsg4Dtl);
            lineNum++;
        }

        return invtyOrdUpdApiPMsgList;

    }
    
    
    /**
     * getInvtyOrdInfo
     * @param glblCmpyCd
     * @param invtyOrdNum
     * @return
     */
    public static List<Map<String, Object>> getInvtyOrdInfoList(String glblCmpyCd, String invtyOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyOrdNum", invtyOrdNum);

        S21SsmEZDResult result = NLCL0290Query.getInstance().getInvtyOrdInfoList(queryParam);

        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * execDivAllocation
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @return
     */
    public static NWZC107001PMsg execDivAllocation(String glblCmpyCd, Map<String, Object> invtyOrdInfo) {

        NWZC107001PMsg allocApiPMsg = new NWZC107001PMsg();

        ZYPEZDItemValueSetter.setValue(allocApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxRsnCd, TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineSubNum, "001");
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.invtyLocCd, (String) invtyOrdInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.stkStsCd, (String) invtyOrdInfo.get("FROM_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxOrdTs,  ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxHdrNum, (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.trxLineNum, (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.mdseCd, (String) invtyOrdInfo.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxRqstQty, (BigDecimal) invtyOrdInfo.get("ORD_QTY"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.rsdDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxUnitPrc, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.xxSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.billToCustCd, (String) invtyOrdInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.sellToCustCd, (String) invtyOrdInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.shipToCustCd, (String) invtyOrdInfo.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(allocApiPMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);

        return allocApiPMsg;
    }
    
    /**
     * execSvcMachMstrAllocApi. Mod QC#57258
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @return
     */
    public static List<NSZC001001PMsg> execSvcMachMstrAllocApi(NLCL0290CMsg bizMsg, String glblCmpyCd, Map<String, Object> invtyOrdInfo) {

        String isSerializedItem = (String) invtyOrdInfo.get("SHPG_SER_TAKE_FLG");
        String mdseCd = (String) invtyOrdInfo.get("MDSE_CD");
        String invtyLocCd = (String) invtyOrdInfo.get("INVTY_LOC_CD");
        String fromStkStsCd = (String) invtyOrdInfo.get("FROM_STK_STS_CD");
        String invtyOrdNum = (String) invtyOrdInfo.get("INVTY_ORD_NUM");
        String invtyOrdLineNum = (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM");
        BigDecimal svcConfigMstrPk = (BigDecimal) invtyOrdInfo.get("SVC_CONFIG_MSTR_PK");
        BigDecimal ordQty = (BigDecimal) invtyOrdInfo.get("ORD_QTY");

        List<NSZC001001PMsg> machMstrInfoList = new ArrayList<NSZC001001PMsg>();
        List<Map<String, Object>> machMstrInfoListExeList = new ArrayList<Map<String, Object>>();

        // Serialized Item
        if (ZYPConstant.FLG_ON_Y.equals(isSerializedItem)) {

            List<Map<String, Object>> serNumList = getSerNumList(glblCmpyCd, invtyOrdInfo);

            for (Map<String, Object> serNum : serNumList) {

                Map<String, Object> machMstrInfoMap = getSvcMachMstrInfo(glblCmpyCd, (String) serNum.get("SER_NUM"), mdseCd);

                if (machMstrInfoMap != null) {

                    // Validation Check
                    if (!ZYPCommonFunc.hasValue(checkSerial(machMstrInfoMap, mdseCd, invtyLocCd, fromStkStsCd))) {
                        machMstrInfoMap.put("TRX_HDR_NUM", invtyOrdNum);
                        machMstrInfoMap.put("TRX_LINE_NUM", invtyOrdLineNum);
                        machMstrInfoListExeList.add(machMstrInfoMap);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            // non Serialized Item
        } else {

            // Config Item
            if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                machMstrInfoListExeList = searchConfig(glblCmpyCd, invtyOrdInfo);
            } else {
                machMstrInfoListExeList = searchNonConfig(glblCmpyCd, invtyOrdInfo);
                // QC#57258 Add
                if (machMstrInfoListExeList == null) {
                    bizMsg.setMessageInfo("NLCM0240E", new String[]{(String) invtyOrdInfo.get("INVTY_LOC_CD"), (String) invtyOrdInfo.get("MDSE_CD"), ZYPCodeDataUtil.getName(STK_STS.class, glblCmpyCd, (String) invtyOrdInfo.get("FROM_STK_STS_CD")) });
                    return null;
                }
                
            }
            // QC#57258 Mod Start
            if (machMstrInfoListExeList != null && machMstrInfoListExeList.size() > 0) {
                // ord qty is greater than s21 machine qty.
                if (machMstrInfoListExeList.size() < ordQty.intValue()) {
                    // QC#57258 Add
                    bizMsg.setMessageInfo("NLCM0228E");
                    return null;
                }
            } else {
                // QC#57258 Add
                bizMsg.setMessageInfo("NLCM0228E");
                return null;
            }
            // QC#57258 Mod End

        }
        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();

        for (Map<String, Object> machMstrInfoListExe : machMstrInfoListExeList) {
            svcMachMstrApiPMsg = executeSvcMachMstrAllocApi(glblCmpyCd, invtyOrdInfo, machMstrInfoListExe);
            machMstrInfoList.add(svcMachMstrApiPMsg);
        }

        return machMstrInfoList;
    }

    /**
     * executeSvcMachMstrAllocApi
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @param machMstrInfo
     * @return
     * @throws SQLException
     */
    public static NSZC001001PMsg executeSvcMachMstrAllocApi(String glblCmpyCd, Map<String, Object> invtyOrdInfo, Map<String, Object> machMstrInfo) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, (BigDecimal) machMstrInfo.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));

        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.ONLINE);

        return svcMachMstrApiPMsg;

    }

    /**
     * execShippingPlanUpdate
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @return
     */
    public static List<NWZC003001PMsg> execShippingPlanUpdate(String glblCmpyCd, Map<String, Object> invtyOrdInfo) {

        List<NWZC003001PMsg> shpgPlnUpdApiPMsgList = new ArrayList<NWZC003001PMsg>();
        NWZC003001PMsg shpgPlnUpdApiPMsg = new NWZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxLineSubNum, "001");
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxHdrNum, (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.trxLineNum, (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(shpgPlnUpdApiPMsg.avalSoQty, (BigDecimal) invtyOrdInfo.get("ORD_QTY"));
        shpgPlnUpdApiPMsgList.add(shpgPlnUpdApiPMsg);

        NWZC003001 shpgPlnUpdApi = new NWZC003001();
        shpgPlnUpdApi.execute(shpgPlnUpdApiPMsgList, ONBATCH_TYPE.ONLINE);

        return shpgPlnUpdApiPMsgList;
    }


    /**
     * execShippingOrder
     * @param glblCmpyCd
     * @param invtyOrdInfoList
     * @return
     */
    public static List<NLZC205001PMsg> execShippingOrder(String glblCmpyCd, List<Map<String, Object>> invtyOrdInfoList) {

        List<NLZC205001PMsg> soApiPMsgList = new ArrayList<NLZC205001PMsg>();

        for (Map<String, Object> invtyOrdInfo : invtyOrdInfoList) {

            NLXC023001PMsg nlxc023001PMsg = new NLXC023001PMsg();
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxHdrNum, (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxLineNum, (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(nlxc023001PMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);

            NLXC023001 nlxc023001 = new NLXC023001();
            nlxc023001.execute(nlxc023001PMsg, ONBATCH_TYPE.ONLINE);

            NLZC205001PMsg soApiPMsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(soApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.sceOrdTpCd, NLXSceConst.SCE_ORD_TP_CD_SC);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgPlnNum, nlxc023001PMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(soApiPMsg.xxModeCd, NLZC205001.MODE_NEW);
            soApiPMsgList.add(soApiPMsg);
        }

        NLZC205001 soApi = new NLZC205001();
        soApi.execute(soApiPMsgList, ONBATCH_TYPE.ONLINE);
        return soApiPMsgList;
    }

    /**
     * getShpgPlnNum
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @return
     */
    public static NLXC023001PMsg getShpgPlnNum(String glblCmpyCd, Map<String, Object> invtyOrdInfo) {

        List<NLXC023001PMsg> getShpgPlnNumPMsgList = new ArrayList<NLXC023001PMsg>();

        NLXC023001PMsg getShpgPlnNumPMsg = new NLXC023001PMsg();

        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxHdrNum, (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxLineNum, (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(getShpgPlnNumPMsg.trxLineSubNum, "001");
        getShpgPlnNumPMsgList.add(getShpgPlnNumPMsg);
        NLXC023001 getShpgPlnNumApi = new NLXC023001();
        getShpgPlnNumApi.execute(getShpgPlnNumPMsg, ONBATCH_TYPE.ONLINE);

        return getShpgPlnNumPMsg;

    }

    /**
     * execRWSCreation
     * @param glblCmpyCd
     * @param soNum
     * @return
     */
    public static NLZC200001PMsg execRWSCreation(String glblCmpyCd, String soNum) {

        NLZC200001PMsg rwsApiPMsg = new NLZC200001PMsg();

        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.inbdSrcTpCd, INBD_SRC_TP.SO);
        ZYPEZDItemValueSetter.setValue(rwsApiPMsg.soNum, soNum);

        NLZC200001 rwsApi = new NLZC200001();
        rwsApi.execute(rwsApiPMsg, ONBATCH_TYPE.ONLINE);

        return rwsApiPMsg;
    }


    /**
     * execRwsSerCreation
     * @param glblCmpyCd
     * @param rwsNum
     * @param invtyOrdInfoList
     * @return
     */
    public static NLZC304001PMsg execRwsSerCreation(String glblCmpyCd, String rwsNum, List<Map<String, Object>> invtyOrdInfoList) {

        NLZC304001PMsg rwsSerApiPMsg = new NLZC304001PMsg();
        List<Map<String, Object>> serNumListFromInvtyOrd = new ArrayList<Map<String, Object>>();

        String invtyOrdNum = (String) invtyOrdInfoList.get(0).get("INVTY_ORD_NUM");

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyOrdNum", invtyOrdNum);

        S21SsmEZDResult rwsDetailResultResult = NLCL0290Query.getInstance().getSerNumListFromInvtyOrd(queryParam);
        if (rwsDetailResultResult.isCodeNormal()) {
            serNumListFromInvtyOrd = (List<Map<String, Object>>) rwsDetailResultResult.getResultObject();
        } else {
            return rwsSerApiPMsg;
        }

        NLZC304001PMsg pMsg = new NLZC304001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsNum);

        int i = 0;
        for (Map<String, Object> serNumMap : serNumListFromInvtyOrd) {
            ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).rwsDtlLineNum, (String) serNumMap.get("RWS_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNum, (String) serNumMap.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).mdseCd, (String) serNumMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
            ZYPEZDItemValueSetter.setValue(pMsg.SerialList.no(i).serNumSendFlg, ZYPConstant.FLG_OFF_N);
            i++;
        }
        pMsg.SerialList.setValidCount(i);

        NLZC304001 serCreationApi = new NLZC304001();

        serCreationApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

        return rwsSerApiPMsg;

    }

    /**
     * execRwsPutAwayConf
     * @param rwsHdrTMsg
     * @param invtyOrdInfo
     * @return
     */
    public static NLZC206001PMsg execRwsPutAwayConf(RWS_HDRTMsg rwsHdrTMsg) {

        NLZC206001PMsg putAwayS21DcApiPMsg = new NLZC206001PMsg();
        List<Map<String, Object>> rwsDetailList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rwsSerNumList = new ArrayList<Map<String, Object>>();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", rwsHdrTMsg.glblCmpyCd.getValue());
        queryParam.put("rwsNum", rwsHdrTMsg.rwsNum.getValue());

        // get RWS Detail
        S21SsmEZDResult rwsDetailResult = NLCL0290Query.getInstance().getRwsDetailList(queryParam);
        if (rwsDetailResult.isCodeNormal()) {
            rwsDetailList = (List<Map<String, Object>>) rwsDetailResult.getResultObject();
        }

        // get RWS Serial
        S21SsmEZDResult rwsSerNumResult = NLCL0290Query.getInstance().getRwsSerNumList(queryParam);
        if (rwsSerNumResult.isCodeNormal()) {
            rwsSerNumList = (List<Map<String, Object>>) rwsSerNumResult.getResultObject();
        }

        // Set Param
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.glblCmpyCd, rwsHdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.slsDt, ZYPDateUtil.getSalesDate(rwsHdrTMsg.glblCmpyCd.getValue()));

        int rwsDtlCnt = 0;
        int rwsSerCnt = 0;

        for (Map<String, Object> rwsDetail : rwsDetailList) {

            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsNum, (String) rwsDetail.get("RWS_NUM"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsDtlLineNum, (String) rwsDetail.get("RWS_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).invtyStkStsCd, (String) rwsDetail.get("INVTY_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsStkQty, (BigDecimal) rwsDetail.get("RWS_QTY"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).whCd, (String) rwsDetail.get("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).mdseCd, (String) rwsDetail.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RWSPutAwayList.no(rwsDtlCnt).rwsRefNum, rwsHdrTMsg.rwsRefNum);
            rwsDtlCnt++;
        }

        // Set Serial
        for (Map<String, Object> rwsSerNum : rwsSerNumList) {
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).rwsDtlLineNum, (String) rwsSerNum.get("RWS_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).serNum, (String) rwsSerNum.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(putAwayS21DcApiPMsg.RcvSerNumList.no(rwsSerCnt).mdseCd, (String) rwsSerNum.get("MDSE_CD"));
            rwsSerCnt++;
        }

        putAwayS21DcApiPMsg.RWSPutAwayList.setValidCount(rwsDtlCnt);
        putAwayS21DcApiPMsg.RcvSerNumList.setValidCount(rwsSerCnt);

        NLZC206001 putAwayApi = new NLZC206001();
        putAwayApi.execute(putAwayS21DcApiPMsg, ONBATCH_TYPE.ONLINE);

        return putAwayS21DcApiPMsg;

    }

    /**
     * execRwsCompletion
     * @param rwsHdrTMsg
     * @param invtyOrdInfo
     * @return
     */
    public static NLZC207001PMsg execRwsCompletion(RWS_HDRTMsg rwsHdrTMsg) {

        NLZC207001PMsg pMsg = new NLZC207001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, rwsHdrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(rwsHdrTMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.rwsNum, rwsHdrTMsg.rwsNum);
        ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, rwsHdrTMsg.sceOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsCloDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
        ZYPEZDItemValueSetter.setValue(pMsg.whCd, rwsHdrTMsg.whCd);
        ZYPEZDItemValueSetter.setValue(pMsg.rwsRefNum, rwsHdrTMsg.rwsRefNum);

        NLZC207001 rwsApi = new NLZC207001();
        rwsApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;

    }

    /**
     * execSerialTransaction
     * @param rwsHdrTMsg
     * @param invtyOrdInfoList
     * @param soNum
     * @return
     */
    public static NLZC302001PMsg execSerialTransaction(RWS_HDRTMsg rwsHdrTMsg, List<Map<String, Object>> invtyOrdInfoList, String soNum) {

        NLZC302001PMsg serTrxApipMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(serTrxApipMsg.glblCmpyCd, rwsHdrTMsg.glblCmpyCd);
        int serialApiPMsgIdx = 0;

        for (Map<String, Object> invtyOrdInfo : invtyOrdInfoList) {

            if (ZYPConstant.FLG_ON_Y.equals((String) invtyOrdInfo.get("SHPG_SER_TAKE_FLG"))) {
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).serNum, (String) invtyOrdInfo.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).mdseCd, (String) invtyOrdInfo.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxSrcTpCd, SER_TRX_SRC_TP.SO);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxSrcHdrNum, soNum);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxSrcLineNum, "001");
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxRefNum, (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).fromLocCd, (String) invtyOrdInfo.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).toLocCd, (String) invtyOrdInfo.get("INVTY_LOC_CD"));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).manCratFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).locStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).serTrxEventCd, SER_TRX_EVENT.STOCK_STATUS_CHANGE);
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).toStkStsCd, (String) invtyOrdInfo.get("TO_STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(serTrxApipMsg.UpdateDetailList.no(serialApiPMsgIdx).fromStkStsCd, (String) invtyOrdInfo.get("FROM_STK_STS_CD"));
                serialApiPMsgIdx++;

            }
            serTrxApipMsg.UpdateDetailList.setValidCount(serialApiPMsgIdx);
        }

        NLZC302001 serTranApi = new NLZC302001();
        serTranApi.execute(serTrxApipMsg, ONBATCH_TYPE.ONLINE);

        return serTrxApipMsg;
    }

    /**
     * execSvcMachMstrAllocationOff
     * @param glblCmpyCd
     * @param invtyOrdNum
     * @param machMstrInfo
     * @return
     */
    public static NSZC001001PMsg execSvcMachMstrAllocationOff(String glblCmpyCd, NSZC001001PMsg machMstrInfo) {

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, machMstrInfo.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.ONLINE);

        return svcMachMstrApiPMsg;

    }

    /**
     * execSvcMachMstrUpdateInventry
     * @param invtyOrdInfo
     * @param glblCmpyCd
     * @param machMstrInfo
     * @return
     */
    public static NSZC001001PMsg execSvcMachMstrUpdateInventry(List<Map<String, Object>> invtyOrdInfoList, String glblCmpyCd, NSZC001001PMsg machMstrInfo) {

        Map<String, Object> invtyOrdInfo = invtyOrdInfoList.get(0);

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyOrdNum", (String) machMstrInfo.trxHdrNum.getValue());
        queryParam.put("invtyOrdLineNum", (String) machMstrInfo.trxLineNum.getValue());

        S21SsmEZDResult result = NLCL0290Query.getInstance().getToStkStsCd(queryParam);

        Map<String, Object> map = (Map<String, Object>) result.getResultObject();

        // START 2021/03/08 A.Marte [QC#58503, ADD]
        // Get INVTY_LOC_CD from INVTY_ORD_DTL
        S21SsmEZDResult ssmResult = NLCL0290Query.getInstance().getInvtyLocCd(queryParam);
        Map<String, Object> curLocMap = (Map<String, Object>) ssmResult.getResultObject();
        // END 2021/03/08 A.Marte [QC#58503, ADD]

        NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, machMstrInfo.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcConfigMstrPk, machMstrInfo.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
        // START 2021/03/08 A.Marte [QC#58503, MOD]
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.curLocNum, (String) curLocMap.get("INVTY_LOC_CD"));
        // END 2021/03/08 A.Marte [QC#58503, MOD]
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.stkStsCd, (String) map.get("TO_STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, machMstrInfo.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, machMstrInfo.trxLineNum);

        NSZC001001 api = new NSZC001001();
        api.execute(svcMachMstrApiPMsg, ONBATCH_TYPE.BATCH);

        return svcMachMstrApiPMsg;

    }

    /**
     * getSerNumList
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @return
     */
    public static List<Map<String, Object>> getSerNumList(String glblCmpyCd, Map<String, Object> invtyOrdInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyOrdNum", (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        queryParam.put("invtyOrdLineNum", (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));

        S21SsmEZDResult result = NLCL0290Query.getInstance().getSerNumList(queryParam);

        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }

    
    
    /**
     * searchConfig
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @return
     */
    public static List<Map<String, Object>> searchConfig(String glblCmpyCd, Map<String, Object> invtyOrdInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("configId", (BigDecimal) invtyOrdInfo.get("SVC_CONFIG_MSTR_PK"));
        queryParam.put("svcMachMstrStsCrat", SVC_MACH_MSTR_STS.CREATED);
        queryParam.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        queryParam.put("invtyLocCd", (String) invtyOrdInfo.get("INVTY_LOC_CD"));
        queryParam.put("fromStkStsCd", (String) invtyOrdInfo.get("FROM_STK_STS_CD"));
        queryParam.put("mdseCd", (String) invtyOrdInfo.get("MDSE_CD"));
        queryParam.put("rowNum", (BigDecimal) invtyOrdInfo.get("ORD_QTY"));
        queryParam.put("invtyOrdNum", (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        queryParam.put("invtyOrdLineNum", (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));

        S21SsmEZDResult result = NLCL0290Query.getInstance().searchConfig(queryParam);

        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * searchNonConfig
     * @param glblCmpyCd
     * @param invtyOrdInfo
     * @return
     */
    public static List<Map<String, Object>> searchNonConfig(String glblCmpyCd, Map<String, Object> invtyOrdInfo) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", (String) invtyOrdInfo.get("MDSE_CD"));
        queryParam.put("svcMachMstrStsCrat", SVC_MACH_MSTR_STS.CREATED);
        queryParam.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        queryParam.put("invtyLocCd", (String) invtyOrdInfo.get("INVTY_LOC_CD"));
        queryParam.put("wmsOldStkStsCd", (String) invtyOrdInfo.get("FROM_STK_STS_CD"));
        queryParam.put("rowNum", (BigDecimal) invtyOrdInfo.get("ORD_QTY"));
        queryParam.put("invtyOrdNum", (String) invtyOrdInfo.get("INVTY_ORD_NUM"));
        queryParam.put("invtyOrdLineNum", (String) invtyOrdInfo.get("INVTY_ORD_LINE_NUM"));

        S21SsmEZDResult result = NLCL0290Query.getInstance().searchNonConfig(queryParam);

        if (result.isCodeNormal()) {
            return (List<Map<String, Object>>) result.getResultObject();
        } else {
            return null;
        }
    }
    
    /**
     * getSvcMachMstrInfo
     * @param glblCmpyCd
     * @param serNum
     * @param mdseCd
     * @return
     */
    public static Map<String, Object> getSvcMachMstrInfo(String glblCmpyCd, String serNum, String mdseCd) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("serNum", serNum);
        queryParam.put("mdseCd", mdseCd);

        S21SsmEZDResult result = NLCL0290Query.getInstance().getSvcMachMstrInfo(queryParam);

        if (result.isCodeNormal()) {
            return (Map<String, Object>) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * getRwsHdr
     * @param glblCmpyCd
     * @param rwsNum
     * @return
     */
    public static RWS_HDRTMsg getRwsHdr(String glblCmpyCd, String rwsNum) {
        RWS_HDRTMsg inMsg = new RWS_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rwsNum, rwsNum);
        RWS_HDRTMsg outMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    /**
     * checkSerial
     * @param machMstrInfoMap
     * @param mdseCd
     * @param invtyLocCd
     * @param stsStsCd
     * @return
     */
    public static String checkSerial(Map<String, Object> machMstrInfoMap, String mdseCd, String invtyLocCd, String stsStsCd) {

        // Allocated by other order
        if (ZYPCommonFunc.hasValue((String) machMstrInfoMap.get("TRX_HDR_NUM"))) {
            return NLZM2409E;
        }
        if (ZYPConstant.FLG_OFF_N.equals((String) machMstrInfoMap.get("SVC_MACH_MAINT_AVAL_FLG"))) {
            return NLZM2409E;
        }
        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            return NLBM1337E;
        }
        if (!invtyLocCd.equals((String) machMstrInfoMap.get("CUR_LOC_NUM"))) {
            return NLBM1337E;
        }
        if (!ZYPCommonFunc.hasValue(stsStsCd)) {
            return NLZM2414E;
        }
        if (!stsStsCd.equals((String) machMstrInfoMap.get("STK_STS_CD"))) {
            return NLZM2414E;
        }
        if (!SVC_MACH_MSTR_STS.CREATED.equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_STS_CD")) && !SVC_MACH_MSTR_STS.REMOVED.equals((String) machMstrInfoMap.get("SVC_MACH_MSTR_STS_CD"))) {
            return NLZM2410E;
        }
        return null;
    }
    // QC#29172 add end

    // START 2018/12/18 M.Naito [QC#28769,ADD]
    /**
     * checkOpenedPhysInvty
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return String
     */
    public static String checkOpenedPhysInvty(String glblCmpyCd, String invtyLocCd) {

        NLZC060001PMsg pMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, invtyLocCd);
        NLZC060001 nlzc060001 = new NLZC060001();
        nlzc060001.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(pMsg.xxRsltStsCd.getValue())) {
            return NLZC060001Constant.RETURN_CODE_ERROR;
        }
        return NLZC060001Constant.RETURN_CODE_NORMAL;
    }
    // END 2018/12/18 M.Naito [QC#28769,ADD]

    // QC#50008 Add method.
    /**
     * <pre>isTechOrCustomer</pre>
     * @param glblCmpyCd String(Not Null)
     * @param rtlWhCd String(Not Null)
     * @return boolean (true:Tech or Customer)
     */
    public static boolean isTechOrCustomer(String glblCmpyCd,  String rtlWhCd){
        
        int cnt = NLCL0290Query.getInstance().cntTechOrCustomersite(glblCmpyCd, rtlWhCd);
        
        if(cnt == 0 ){
            // Not Tech or Customer
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * Common send mail method.
     * </pre>
     * @param glblCmpyCd String(Not null)
     * @param toEmlAddrList List<S21MailAddress> (Not null.And Not
     * Empty List)
     * @param ccEmlAddrList List<S21MailAddress> (null able)
     * @param fromEmlAddr List<S21MailAddress> (Not null.And Not Empty
     * List)
     * @param template S21MailTemplate(Not null)
     */
    public static void sendMail(String glblCmpyCd//
            , List<S21MailAddress> toEmlAddrList//
            , List<S21MailAddress> ccEmlAddrList//
            , S21MailAddress fromEmlAddr//
            , S21MailTemplate template) {

        S21Mail mail = new S21Mail(glblCmpyCd);

        mail.setToAddressList(toEmlAddrList);
        if (ccEmlAddrList != null && !ccEmlAddrList.isEmpty()) {
            mail.setCcAddressList(ccEmlAddrList);
        }
        mail.setFromAddress(fromEmlAddr);
        mail.setMailTemplate(template);

        // Request send mail data.
        // sendMail method is force send mail.
        mail.postMail();
    }

    /**
     * <pre>
     *  get FSR Manager Address list.
     *  1 mail address only.
     * </pre>
     * @param glblCmpyCd String
     * @return FSREmlAddressList List<S21MailAddress> (Not null. empty
     * able)
     */
    public static List<S21MailAddress> getFSMEmailAddress(String glblCmpyCd, String rtlWhCd) {

        List<S21MailAddress> eMlAddrList = new ArrayList<S21MailAddress>();

        String fsrEmailAddress = NLCL0290Query.getInstance().getFsrEmailAddress(glblCmpyCd, rtlWhCd);

        if (ZYPCommonFunc.hasValue(fsrEmailAddress)) {
            eMlAddrList.add(new S21MailAddress(glblCmpyCd, fsrEmailAddress));
        }

        return eMlAddrList;
    }

    /**
     * <pre>
     *  get Tech and Alternate Owner Address list.
     * </pre>
     * @param glblCmpyCd String
     * @return FSREmlAddressList List<S21MailAddress> (Not null. empty
     * able)
     */
    public static List<S21MailAddress> getWhOwnrEmailAddress(String glblCmpyCd, String rtlWhCd) {

        List<S21MailAddress> eMlAddrList = new ArrayList<S21MailAddress>();

        // FSR
        String fsrEmailAddress = NLCL0290Query.getInstance().getFsrEmailAddress(glblCmpyCd, rtlWhCd);

        String techEmalAddress = NLCL0290Query.getInstance().getTechEmailAddress(glblCmpyCd, rtlWhCd);
        if (ZYPCommonFunc.hasValue(techEmalAddress)//
                && !techEmalAddress.equals(fsrEmailAddress)) {
            eMlAddrList.add(new S21MailAddress(glblCmpyCd, techEmalAddress));
        }

        String altEmalAddress = NLCL0290Query.getInstance().getAltEmailAddress(glblCmpyCd, rtlWhCd);
        if (ZYPCommonFunc.hasValue(altEmalAddress) //
                && !altEmalAddress.equals(fsrEmailAddress) //
                && !altEmalAddress.equals(techEmalAddress)) {
            eMlAddrList.add(new S21MailAddress(glblCmpyCd, altEmalAddress));
        }

        return eMlAddrList;
    }

    /**
     * <pre>
     *  get CSA System email Address list.
     *  1 mail address only.
     * </pre>
     * @param glblCmpyCd String
     * @return systemEmlAddressList List<S21MailAddress> (Null able)
     */
    public static List<S21MailAddress> getSystemEmailAddress(String glblCmpyCd) {

        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, "NLCL0290");
        fromGrp.setMailKey1("From");
        fromGrp.getMailAddress();

        return fromGrp.getMailAddress();
    }

    /**
     * <pre>
     * set Mail Template TechWH Notification Mail.
     * </pre>
     * @param glblCmpyCd String(not null)
     * @param psnCd String
     * @param psnNm String
     * @param cMsg NLCL0290CMsg(not null)
     * @param sMsg NLCL0290SMsg(not null)
     * @return mailTemplate S21MailTemplate
     */
    public static S21MailTemplate setMailTemplateOfSendTechWhNotif(String glblCmpyCd, String psnCd, String psnNm, NLCL0290CMsg cMsg, NLCL0290SMsg sMsg) {

        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, "NLCL0290M001");

        // Set FSR Name
        String fsrNm = NLCL0290Query.getInstance().getFsrPersonName(glblCmpyCd, cMsg.rtlWhCd_H.getValue());
        template.setTemplateParameter("MGR_PSN_NM", fsrNm);

        // Set Warehouse Name
        template.setTemplateParameter("RTL_WH_CD", cMsg.rtlWhCd_H.getValue());
        template.setTemplateParameter("RTL_WH_NM", cMsg.rtlWhNm_H.getValue());

        // Set Operator & Operation Time
        template.setTemplateParameter("OPR_PSN_CD", psnCd);
        template.setTemplateParameter("OPR_PSN_NM", psnNm);

        String currentTs = ZYPDateUtil.getUserCurrentSystemTime("yyyyMMddHHmmssSSS");
        String currentDate = ZYPDateUtil.DateFormatter(currentTs, "yyyyMMddHHmmssSSS", "MM/dd/yyyy HH:mm:ss");
        template.setTemplateParameter("OPR_DT_TM", currentDate);

        // Set Detail
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (i == 0) {
                // First Line
                sb.append(mlTmpLineSeparator);
                sb.append(mlTmpLineBreak);
            }

            // Set Item
            sb.append("    Item: " + sMsg.A.no(i).mdseCd_A.getValue() + "  " + sMsg.A.no(i).mdseDescShortTxt_A.getValue());
            sb.append(mlTmpLineBreak);

            // Set Serial
            if(ZYPCommonFunc.hasValue(sMsg.A.no(i).serNum_A.getValue())){
                sb.append("  Serial: " + sMsg.A.no(i).serNum_A.getValue());
                sb.append(mlTmpLineBreak);
            }

            // Set SWH
            sb.append("     SWH: " + sMsg.A.no(i).fromRtlSwhCd_A.getValue());
            sb.append(mlTmpLineBreak);

            // Set TO SWH
            if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(cMsg.adjTrxTpCd_H.getValue())) {
                sb.append("Dest SWH: " + sMsg.A.no(i).toRtlSwhCd_A.getValue());
                sb.append(mlTmpLineBreak);
            }

            // Set Stock Status
            if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(cMsg.adjTrxTpCd_H.getValue())) {
                
                String fromStkStsCd = sMsg.A.no(i).stkStsCd_A.getValue();
                for (int j = 0; j < cMsg.stkStsCd_LC.length(); j++) {
                    if (ZYPCommonFunc.hasValue(cMsg.stkStsCd_LC.no(j).getValue()) //
                            && cMsg.stkStsCd_LC.no(j).getValue().equals(fromStkStsCd)) {
                        sb.append("From S/S: " + fromStkStsCd+":"+cMsg.stkStsDescTxt_LD.no(j).getValue());
                        break;
                    }
                }
                sb.append(mlTmpLineBreak);

                String toStkStsCd = sMsg.A.no(i).toStkStsCd_A.getValue();
                for (int j = 0; j < cMsg.stkStsCd_LC.length(); j++) {
                    if (ZYPCommonFunc.hasValue(cMsg.stkStsCd_LC.no(j).getValue()) //
                            && cMsg.stkStsCd_LC.no(j).getValue().equals(toStkStsCd)) {
                        sb.append("  To S/S: " + toStkStsCd+":"+cMsg.stkStsDescTxt_LD.no(j).getValue());
                        break;
                    }
                }
                sb.append(mlTmpLineBreak);
            }

            // Set Qty & Uom
            sb.append("     Qty: " + sMsg.A.no(i).ordQty_A.getValueInt());

            String mdsePkgUomCd = NLCL0290Query.getInstance().getMdsePrimPkgUomCd(glblCmpyCd, sMsg.A.no(i).mdseCd_A.getValue());
            sb.append(" " + mdsePkgUomCd);
            sb.append(mlTmpLineBreak);

            // Set Reason
            sb.append("  Reason: ");
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).adjCatgCd_A)) {
                String adjCatgCd = sMsg.A.no(i).adjCatgCd_A.getValue();

                for (int j = 0; j < cMsg.adjCatgCd_LC.length(); j++) {
                    if (adjCatgCd.equals(cMsg.adjCatgCd_LC.no(j).getValue())) {
                        if (ADJ_CATG.OTHER.equals(adjCatgCd)) {
                            sb.append(cMsg.adjCatgDescTxt_LD.no(j).getValue());
                            sb.append("(" + sMsg.A.no(i).invtyOrdLineCmntTxt_A.getValue() + ")");
                        } else {
                            sb.append(cMsg.adjCatgDescTxt_LD.no(j).getValue());
                        }
                        break;
                    }
                }
            } else {
                // Not Null
                String adjTrxTpCd = cMsg.adjTrxTpCd_H.getValue();

                for (int j = 0; j < cMsg.adjTrxTpCd_LC.length(); j++) {
                    if (adjTrxTpCd.equals(cMsg.adjTrxTpCd_LC.no(j).getValue())) {
                        sb.append(cMsg.adjTrxTpDescTxt_LD.no(j).getValue());
                        break;
                    }
                }
            }

            sb.append(mlTmpLineBreak);

            // Set Separator (End line)
            sb.append(mlTmpLineSeparator);
            sb.append(mlTmpLineBreak);
        }

        template.setTemplateParameter("LINE_DATA", sb.toString());

        return template;
    }

    // START 2019/06/10 M.Naito [QC#50751,ADD]
    /**
     *  isExistInvtyDtlDly
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param invtyLocCd String
     * @param locStsCd String
     * @param stkStsCd String
     * @param qty int
     * @return boolean
     */
    public static boolean isExistInvtyDtlDly(String glblCmpyCd, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd, int qty) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        param.put("invtyLocCd", invtyLocCd);
        param.put("locStsCd", locStsCd);
        param.put("stkStsCd", stkStsCd);

        S21SsmEZDResult result = NLCL0290Query.getInstance().cntInvtyDtlDly(param);

        Integer cnt = 0;
        if (result.isCodeNormal()) {
            cnt = (Integer) result.getResultObject();
        }
        if (cnt != null && (cnt + qty) >= 0) {
            return true;
        }
        return false;
    }
    // END 2019/06/10 M.Naito [QC#50751,ADD]
    // START 2022/10/21 N.Takatsu[QC#60603, ADD]
    public static boolean isAutoTransRetailWhCheck(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rtlWhCd", rtlWhCd);

        // Execute
        S21SsmEZDResult result = NLCL0290Query.getInstance().countSubWarehouseTransferControlList(param);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return true;
        }
        return false;
    }
    // END 2022/10/21 N.Takatsu[QC#60603, ADD]
}
