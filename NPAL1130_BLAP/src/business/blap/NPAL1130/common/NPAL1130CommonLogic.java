/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1130.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1130.NPAL1130CMsg;
import business.blap.NPAL1130.NPAL1130Query;
import business.blap.NPAL1130.NPAL1130SMsg;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.AVAL_INVTY_APP_IDTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.SUPD_RELNTMsg;
import business.db.SUPD_RELNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   Hitachi         A.Kohinata      Create          N/A
 * 2013/06/20   Hitachi         T.Kawazu        Update          CSA-339
 * 2016/03/22   CITS            Keiichi Masaki  Update          CSA Project
 * 2017/11/13   CITS            S.Katsuma       Update          QC#22230
 * 2018/06/22   CITS            T.Tokutomi      Update          QC#26534
 *</pre>
 */
public class NPAL1130CommonLogic {

    /**
     * It copy 'NPAL1130CMsg.A' to 'NPAL1130SMsg.A' .
     * @param cMsg biz NPAL1130CMsg
     * @param sMsg global NPAL1130CMsg
     */
    public static void saveCurrentPageToSMsg(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        int fromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * It copy 'NPAL1130SMsg.A' to 'NPAL1130CMsg.A' .
     * @param cMsg NPAL1130CMsg
     * @param sMsg NPAL1130SMsg
     * @param fromIdx from Index
     */
    public static void pagenation(NPAL1130CMsg cMsg, NPAL1130SMsg sMsg, int fromIdx) {

        int i = fromIdx;
        for (; i < fromIdx + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - fromIdx), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - fromIdx);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(fromIdx + 1);
        cMsg.xxPageShowToNum.setValue(fromIdx + cMsg.A.getValidCount());
    }

    /**
     * @param cMsg NPAL1130CMsg
     * @return String
     */
    public static String getMdseName(NPAL1130CMsg cMsg) {

        ALL_MDSE_VTMsg inMsg = new ALL_MDSE_VTMsg();
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("mdseCd01", cMsg.mdseCd.getValue());
        inMsg.setMaxCount(0);
        inMsg.setSQLID("003");

        ALL_MDSE_VTMsgArray outMsgArray = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.length() >= 1) {
            return outMsgArray.no(0).mdseDescShortTxt.getValue();
        } else {
            return null;
        }
    }

    /**
     * get WH Name
     * @param cMsg NPAL1130CMsg
     * @return String
     */
    public static String getWhName(NPAL1130CMsg cMsg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            return existTMsg.rtlWhNm.getValue();
        } else {
            return null;
        }
    }

    /**
     * get SWH Name
     * @param cMsg NPAL1130CMsg
     * @return String
     */
    public static String getSwhName(NPAL1130CMsg cMsg) {

        RTL_SWHTMsg tMsg = new RTL_SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());
        tMsg.rtlSwhCd.setValue(cMsg.rtlSwhCd.getValue());

        RTL_SWHTMsg existTMsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            return existTMsg.rtlSwhNm.getValue();
        } else {
            return null;
        }
    }

    /**
     * chkOrdTakeMdseCd
     * @param glblCmpyCd String
     * @param ordTakeMdseCd String
     * @return boolean
     */
    private static boolean chkOrdTakeMdseCd(String glblCmpyCd, String ordTakeMdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, ordTakeMdseCd);

        ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeMdseTMsg);

        if (ordTakeMdseTMsg != null) {

            return true;
        }

        return false;
    }

    /**
     * chkOrdTakeMdseCd
     * QC#26534 Add method
     * @param glblCmpyCd String
     * @param ordTakeMdseCd String
     * @return boolean
     */
    public static boolean chkOrdTakeMdseCd(String glblCmpyCd, String ordTakeMdseCd, String targetMdseCd) {

        if (ordTakeMdseCd.length() > 8) {
            return false;
        }

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, ordTakeMdseCd);

        ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeMdseTMsg);

        if (ordTakeMdseTMsg == null) {
            return false;
        } else {
            if (targetMdseCd != null //
                    && targetMdseCd.startsWith(ordTakeMdseCd)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * getSupdFlg
     * @param glblCmpyCd String
     * @param mdseCd String
     */
    public static String getSupdFlg(NPAL1130CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.mdseCd) && cMsg.mdseCd.getValue().length() >= 8) {

            // Check Order Take Merchandise
            if (chkOrdTakeMdseCd(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue().substring(0, 8))) {

                return ZYPConstant.FLG_ON_Y;
            }
        }

        if (checkSupersedeRelation(cMsg)) {

            return ZYPConstant.FLG_ON_Y;
        }

        // QC#29214 Update.
        if (checkCompatibleRelation(cMsg)) {
            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * check Supersede Relation
     * @param cMsg NPAL1130CMsg
     */
    public static boolean checkSupersedeRelation(NPAL1130CMsg cMsg) {

        SUPD_RELNTMsg inMsg = new SUPD_RELNTMsg();

        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("supdFromMdseCd01", cMsg.mdseCd.getValue());
        // QC#29214 Update (QC#22230 Return update)
        // START 2017/11/13 S.Katsuma [QC#22230,ADD]
        inMsg.setConditionValue("supdToMdseCd01", cMsg.mdseCd.getValue());
        inMsg.setSQLID("003");
//        inMsg.setSQLID("002");
        // END 2017/11/13 S.Katsuma [QC#22230,ADD]

        SUPD_RELNTMsgArray existTMsg = (SUPD_RELNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (existTMsg != null && 0 < existTMsg.getValidCount()) {
            return true;
        }
        return false;
    }

    /**
     * get Stock Status
     * @param cMsg NPAL1130CMsg
     */
    public static void getStockStatus(NPAL1130CMsg cMsg) {

        AVAL_INVTY_APP_IDTMsg inMsg = new AVAL_INVTY_APP_IDTMsg();

        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("bizAppId01", cMsg.mdseCd.getValue());
        inMsg.setSQLID("003");

        AVAL_INVTY_APP_IDTMsgArray existTMsg = (AVAL_INVTY_APP_IDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        int i = 0;
        ZYPTableUtil.clear(cMsg.S);
        for (; i < existTMsg.getValidCount(); i++) {
            if (i <= cMsg.S.length()) {
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).stkStsCd_S0, existTMsg.no(i).stkStsCd);
            }
        }
    }
    
    /**
     * checkCompatibleRelation
     * @since QC#29214
     * @param cMsg NPAL1130CMsg
     * @return true(include compatible Item)
     */
    public static boolean checkCompatibleRelation(NPAL1130CMsg cMsg) {

        String targetMdseRelnTpCsv = ZYPCodeDataUtil.getVarCharConstValue("NPZC1010_CMPT_MDSE_RELN_TP", cMsg.glblCmpyCd.getValue());
        List<String> targetMdseRelnTpList = null;
        if (targetMdseRelnTpCsv != null) {
            targetMdseRelnTpList = Arrays.asList(targetMdseRelnTpCsv.split(","));
        }

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", cMsg.glblCmpyCd.getValue());
        queryParam.put("MDSE_CD", cMsg.mdseCd.getValue());
        queryParam.put("MDSE_ITEM_RELN_TP_CD", targetMdseRelnTpList);

        S21SsmEZDResult rs = NPAL1130Query.getInstance().getCompatibleItem(queryParam);

        if (rs.isCodeNormal()) {
            List<String> cmpItemList = (List<String>) rs.getResultObject();

            if (cmpItemList != null && !cmpItemList.isEmpty()) {
                return true;
            }
        }

        return false;
    }
}
