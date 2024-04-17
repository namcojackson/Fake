/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.blap.NLBL3060.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3060.NLBL3060CMsg;
import business.blap.NLBL3060.NLBL3060Query;
import business.blap.NLBL3060.NLBL3060SMsg;
import business.blap.NLBL3060.constant.NLBL3060Constant;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2016/11/15   CITS            K.Kameoka       Update          QC#15574
 * 2018/11/21   Fujitsu         T.Ogura         Update          QC#29258
 * 2023/04/18   Hitachi         T.Kuroda        Update          QC#61166
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060CommonLogic implements NLBL3060Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    public static NLBL3060Query getQuery() {
        return NLBL3060Query.getInstance();
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NLBL3060CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum.setValue(shareMsgAry.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NLBL3060CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        int startIndex = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NLBL3060CMsg
     * @param globalMsg NLBL3060SMsg
     */
    public static void prevPage(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Next Page
     * @param bizMsg NLBL3060CMsg
     * @param globalMsg NLBL3060SMsg
     */
    public static void nextPage(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() + bizMsgAry.length());
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NLBL3060CMsg
     * @param globalMsg NLBL3060SMsg
     */
    public static void firstErrorPage(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg, int errNum) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        BigDecimal errPageFromNum = getErrorPageFromNum(bizMsg, globalMsg, errNum);
        bizMsg.xxPageShowFromNum.setValue(errPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Last Page
     * @param bizMsg NLBL3060CMsg
     * @param globalMsg NLBL3060SMsg
     */
    public static void lastPage(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        bizMsgAry = bizMsg.A;
        shareMsgAry = globalMsg.A;
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum.setValue(lastPageFromNum);
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NLBL3060CMsg
     * @param globalMsg NLBL3060SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg) {
        int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
        int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NLBL3060CMsg
     * @param globalMsg NLBL3060SMsg
     * @return BigDecimal
     */
    public static BigDecimal getErrorPageFromNum(NLBL3060CMsg bizMsg, NLBL3060SMsg globalMsg, int errNum) {
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
        // QC#15574 Start
        RTL_WHTMsg outMsg = new RTL_WHTMsg();
        if ("*".equals(rtlWhCd)) {
            ZYPEZDItemValueSetter.setValue(outMsg.rtlWhNm, "All Warehouse");
        } else {
            RTL_WHTMsg inMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
            //RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);
            outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);

        }

        if (outMsg != null) {
            return outMsg.rtlWhNm.getValue();
        }
        // QC#15574 End
        return null;
    }

    public static String getPersonNm(String glblCmpyCd, String psnCd) {
        String psnNm = null;
        if (!ZYPCommonFunc.hasValue(psnCd)) {
            return null;
        }
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("psnCd", psnCd);
        S21SsmEZDResult ssmResult = getQuery().getPersonNm(prmMap);
        if (ssmResult.isCodeNormal()) {
            psnNm = (String) ssmResult.getResultObject();
        }
        return psnNm;
    }

    public static boolean isUpdatedLine(NLBL3060SMsg globalMsg, int index) {
        if (!globalMsg.A.no(index).effThruDt_A1.getValue().equals(globalMsg.B.no(index).effThruDt_B1.getValue())) {
            return true;
        }
        return false;
    }

    // START 2023/10/18 Y.Ogura [QC#61793, MOD]
    public static boolean isDuplicate(String glblCmpyCd, String rtlWhCatgCd, String physWhCd, String rtlWhCd, String schdCoordPsnCd, String effFromDt, String effThruDt) {
//    public static boolean isDuplicate(String glblCmpyCd, String rtlWhCd, String schdCoordPsnCd, String effFromDt, String effThruDt) {
        // END 2023/10/18 Y.Ogura [QC#61793, MOD]
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        if (ZYPCommonFunc.hasValue(rtlWhCatgCd)) {
            prmMap.put("rtlWhCatgCd", rtlWhCatgCd);
        }
        if (ZYPCommonFunc.hasValue(physWhCd)) {
            prmMap.put("physWhCd", physWhCd);
        }
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
        // START 2023/10/18 Y.Ogura [QC#61793, MOD]
        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            prmMap.put("rtlWhCd", rtlWhCd);
        }
//        prmMap.put("rtlWhCd", rtlWhCd);
        // END 2023/10/18 Y.Ogura [QC#61793, MOD]
        prmMap.put("schdCoordPsnCd", schdCoordPsnCd);
        prmMap.put("effFromDt", effFromDt);
        prmMap.put("effThruDt", effThruDt);
        S21SsmEZDResult ssmResult = getQuery().countDuplicate(prmMap);
        if (ssmResult.isCodeNormal()) {
            BigDecimal count = (BigDecimal) ssmResult.getResultObject();
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
        }
        return false;
    }

    // START 2023/04/18 T.Kuroda [QC#61166, ADD]
    public static boolean isLogicalRemoveLine(NLBL3060SMsg globalMsg, int index) {
        String rtlWhCd = globalMsg.B.no(index).rtlWhCd_B1.getValue();
        String schdCoordPsnCd = globalMsg.B.no(index).schdCoordPsnCd_B1.getValue();
        String effFromDt = globalMsg.B.no(index).effFromDt_B1.getValue();

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (globalMsg.A.no(i).rtlWhCd_A1.getValue().equals(rtlWhCd)
                    && globalMsg.A.no(i).schdCoordPsnCd_A1.getValue().equals(schdCoordPsnCd)
                    && globalMsg.A.no(i).effFromDt_A1.getValue().equals(effFromDt)) {
                return false;
            }
        }
        return true;
    }

    public static EZDSDateItem getEffThruDtBySchdCoordWhPmsnKey(NLBL3060SMsg globalMsg, int index) {
        String rtlWhCd = globalMsg.A.no(index).rtlWhCd_A1.getValue();
        String schdCoordPsnCd = globalMsg.A.no(index).schdCoordPsnCd_A1.getValue();
        String effFromDt = globalMsg.A.no(index).effFromDt_A1.getValue();
        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        String rtlWhCatgCd = globalMsg.A.no(index).rtlWhCatgCd_A1.getValue();
        String physWhCd = globalMsg.A.no(index).physWhCd_A1.getValue();
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]

        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            if (globalMsg.B.no(i).rtlWhCd_B1.getValue().equals(rtlWhCd)
                    && globalMsg.B.no(i).schdCoordPsnCd_B1.getValue().equals(schdCoordPsnCd)
                    && globalMsg.B.no(i).effFromDt_B1.getValue().equals(effFromDt)
                    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                    && globalMsg.B.no(i).rtlWhCatgCd_B1.getValue().equals(rtlWhCatgCd)
                    && globalMsg.B.no(i).physWhCd_B1.getValue().equals(physWhCd)
                    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
            ) {
                return globalMsg.B.no(i).effThruDt_B1;
            }
        }
        return null;
    }
    // END   2023/04/18 T.Kuroda [QC#61166, ADD]

    public static boolean hasLineError(String glblCmpyCd, NLBL3060SMsg globalMsg, int i) {
        boolean errFlg = false;

        if (globalMsg.A.no(i).effFromDt_A1.getValue().compareTo(globalMsg.A.no(i).effThruDt_A1.getValue()) > 0) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).ezUpTime_A1)) {
                globalMsg.A.no(i).effFromDt_A1.setErrorInfo(1, MESSAGE_ID.NWDM0099E.toString(), new String[] {});
            }
            globalMsg.A.no(i).effThruDt_A1.setErrorInfo(1, MESSAGE_ID.NWDM0099E.toString(), new String[] {});
            errFlg = true;
        }

        // START 2018/11/21 T.Ogura [QC#29258,MOD]
//        if (globalMsg.A.no(i).effFromDt_A1.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
        if (globalMsg.A.no(i).effFromDt_A1.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0 && !ZYPCommonFunc.hasValue(globalMsg.A.no(i).ezUpTime_A1)) {
        // END   2018/11/21 T.Ogura [QC#29258,MOD]
            globalMsg.A.no(i).effFromDt_A1.setErrorInfo(1, MESSAGE_ID.NWDM0134E.toString(), new String[] {});
            errFlg = true;
        }

        if (globalMsg.A.no(i).effThruDt_A1.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
            globalMsg.A.no(i).effThruDt_A1.setErrorInfo(1, MESSAGE_ID.NWDM0223E.toString(), new String[] {});
            errFlg = true;
        }

        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        if(ZYPCommonFunc.hasValue(globalMsg.A.no(i).physWhCd_A1.getValue())) {
            String physWhCd = NLBL3060CommonLogic.getGroupName(glblCmpyCd, globalMsg.A.no(i).physWhCd_A1.getValue());
            if (!ZYPCommonFunc.hasValue(physWhCd)) {
                globalMsg.A.no(i).physWhCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Group Name" });
                errFlg = true;
            }
        }
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]

        // START 2023/10/18 Y.Ogura [QC#61793, MOD]
//      String rtlWhNm = NLBL3060CommonLogic.getRtlWhNm(glblCmpyCd, globalMsg.A.no(i).rtlWhCd_A1.getValue());
//      if (!ZYPCommonFunc.hasValue(rtlWhNm)) {
//          globalMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Retail Warehouse Code" });
//          errFlg = true;
//      }
        if(ZYPCommonFunc.hasValue(globalMsg.A.no(i).rtlWhCd_A1.getValue())) {
            String rtlWhNm = NLBL3060CommonLogic.getRtlWhNm(glblCmpyCd, globalMsg.A.no(i).rtlWhCd_A1.getValue());
            if (!ZYPCommonFunc.hasValue(rtlWhNm)) {
                globalMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Retail Warehouse Code" });
                errFlg = true;
            }
        }
        // END 2023/10/18 Y.Ogura [QC#61793, MOD]

        String psnNm = NLBL3060CommonLogic.getPersonNm(glblCmpyCd, globalMsg.A.no(i).schdCoordPsnCd_A1.getValue());
        if (!ZYPCommonFunc.hasValue(psnNm)) {
            globalMsg.A.no(i).schdCoordPsnCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0009E.toString(), new String[] {"Person Code" });
            errFlg = true;
        }

        return errFlg;
    }
    
    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
    public static String getGroupName(String glblCmpyCd, String physWhCd) {
        String physWh = null;
        if (!ZYPCommonFunc.hasValue(physWhCd)) {
            return null;
        }
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("physWhCd", physWhCd);
        S21SsmEZDResult ssmResult = getQuery().getGroupName(prmMap);
        if (ssmResult.isCodeNormal()) {
            physWh = (String) ssmResult.getResultObject();
        }
        return physWh;
    }

    public static boolean isDuplicateForUpdate(String glblCmpyCd, String  schdCoordWhPmsnPk, String rtlWhCatgCd, String physWhCd, String rtlWhCd, String schdCoordPsnCd, String effFromDt, String effThruDt) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("schdCoordWhPmsnPk", schdCoordWhPmsnPk);
        
        if (ZYPCommonFunc.hasValue(rtlWhCatgCd)) {
            prmMap.put("rtlWhCatgCd", rtlWhCatgCd);
        }
        if (ZYPCommonFunc.hasValue(physWhCd)) {
            prmMap.put("physWhCd", physWhCd);
        }
        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            prmMap.put("rtlWhCd", rtlWhCd);
        }
        prmMap.put("schdCoordPsnCd", schdCoordPsnCd);
        prmMap.put("effFromDt", effFromDt);
        prmMap.put("effThruDt", effThruDt);
        S21SsmEZDResult ssmResult = getQuery().countDuplicateForUpdate(prmMap);
        if (ssmResult.isCodeNormal()) {
            BigDecimal count = (BigDecimal) ssmResult.getResultObject();
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
        }
        return false;
    }
    
    public static String getSchdCoordWhPmsnPk(String glblCmpyCd, String rtlWhCatgCd, String physWhCd, String rtlWhCd, String effFromDt, String schdCoordPsnCd) {
        String schdCoordWhPmsnPk = null;
        
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(rtlWhCatgCd)) {
            prmMap.put("rtlWhCatgCd", rtlWhCatgCd);
        }
        if (ZYPCommonFunc.hasValue(physWhCd)) {
            prmMap.put("physWhCd", physWhCd);
        }
        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            prmMap.put("rtlWhCd", rtlWhCd);
        }
        prmMap.put("effFromDt", effFromDt);
        prmMap.put("schdCoordPsnCd", schdCoordPsnCd);

        S21SsmEZDResult ssmResult = getQuery().getSchdCoordWhPmsnPk(prmMap);
        if (ssmResult.isCodeNormal()) {
            schdCoordWhPmsnPk = (String) ssmResult.getResultObject();
        }
        return schdCoordWhPmsnPk;
    }
    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
}
