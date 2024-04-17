package business.blap.NLBL3150.common;

/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */

import static business.blap.NLBL3150.constant.NLBL3150Constant.ASTERISK;
import static business.blap.NLBL3150.constant.NLBL3150Constant.BIZ_ID;
import static business.blap.NLBL3150.constant.NLBL3150Constant.LBL_MDSE;
import static business.blap.NLBL3150.constant.NLBL3150Constant.LBL_SWH;
import static business.blap.NLBL3150.constant.NLBL3150Constant.LBL_WH;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLAM0240E;
import static business.blap.NLBL3150.constant.NLBL3150Constant.NLZM2278E;
import static business.blap.NLBL3150.constant.NLBL3150Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3150.NLBL3150CMsg;
import business.blap.NLBL3150.NLBL3150Query;
import business.blap.NLBL3150.NLBL3150SMsg;
import business.blap.NLBL3150.NLBL3150_ASMsg;
import business.blap.NLBL3150.NLBL3150_CSMsg;
import business.db.MDSETMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NLBL3150CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/12   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/08/17   Fujitsu         M.Yamada        Update          QC#20556
 * 2017/08/25   Fujitsu         M.Ohno          Update          QC#20772
 * 2017/09/08   CITS            T.Hakodate      Update          QC#20772
 *</pre>
 */
public class NLBL3150CommonLogic {

    /**
     * update S message
     * @param cMsg NLBL3150CMsg
     * @param sMsg NLBL3150SMsg
     */
    public static void updateSMsg(NLBL3150CMsg cMsg, NLBL3150SMsg sMsg) {
        int ixG = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(ixG + i), null);
        }
    }

    /**
     * control pagenation
     * @param cMsg NLBL3150CMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg NLBL3150SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NLBL3150CMsg cMsg, EZDCMsgArray cMsgArray, NLBL3150SMsg sMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (cMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }
        if (i == 0) {
            cMsgArray.setValidCount(i);
        } else {
            cMsgArray.setValidCount(i - startIndex);
        }

        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * check Merchandise Code on Merchandise Table
     * @param glblCmpyCd Global Company Code
     * @param sMsg NLBL3150_ASMsg
     * @return boolean
     */
    public static boolean checkItem(String glblCmpyCd, NLBL3150_ASMsg sMsg) {
        boolean result = true;

        MDSETMsg mdseTmsg = (MDSETMsg) NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, sMsg.mdseCd_A.getValue());

        if (mdseTmsg == null) {
            // 2017/09/08 CITS T.Hakodate Mod QC#20772 START
            sMsg.mdseCd_A.setErrorInfo(1, NLZM2278E, new String[] {LBL_MDSE });
            // 2017/09/08 CITS T.Hakodate Mod QC#20772 END
            result = false;
        }

        return result;
    }

    /**
     * check Retail Warehouse Sub Warehouse Set
     * @param globalCompanyCode
     * @param acMsg
     * @return
     */
    public static boolean checkWhSwhCd(String glblCmpyCd, NLBL3150_ASMsg acMsg) {

        if (ASTERISK.equals(acMsg.rtlSwhCd_A.getValue()) || !ZYPCommonFunc.hasValue(acMsg.rtlSwhCd_A)) {
            for (String str : getNotAllocWhCd(glblCmpyCd).split(",")) {
                if (str.equals(acMsg.rtlWhCd_A.getValue())) {
                    // 2017/09/08 CITS T.Hakodate Mod QC#20772 START
                    acMsg.rtlWhCd_A.setErrorInfo(1, ZZZM9026E, new String[] {LBL_WH });
                    // 2017/09/08 CITS T.Hakodate Mod QC#20772 END
                    return false;
                }
            }
            if (!checkWhCd(acMsg.rtlWhCd_A.getValue(), glblCmpyCd)) {
                // 2017/09/08 CITS T.Hakodate Mod QC#20772 START
                acMsg.rtlWhCd_A.setErrorInfo(1, NLZM2278E, new String[] {LBL_WH });
                // 2017/09/08 CITS T.Hakodate Mod QC#20772 END
                return false;
            }

            ZYPEZDItemValueSetter.setValue(acMsg.rtlSwhCd_A, ASTERISK);
            return true;
        }

        RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
        rtlSwhTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rtlSwhTMsg.setConditionValue("rtlWhCd01", acMsg.rtlWhCd_A.getValue());
        rtlSwhTMsg.setConditionValue("rtlSwhCd01", acMsg.rtlSwhCd_A.getValue());
        rtlSwhTMsg.setSQLID("004");
        RTL_SWHTMsgArray result = (RTL_SWHTMsgArray) EZDTBLAccessor.findByCondition(rtlSwhTMsg);
        if (result.length() != 0) {
            return true;
        }
        // 2017/09/08 CITS T.Hakodate Mod QC#20772 START
        acMsg.rtlWhCd_A.setErrorInfo(1, NLZM2278E, new String[] {LBL_WH });
        acMsg.rtlSwhCd_A.setErrorInfo(1, NLZM2278E, new String[] {LBL_SWH });
        // 2017/09/08 CITS T.Hakodate Mod QC#20772 END
        return false;
    }

    /**
     * get not allocation Code From var char const
     * @param glblCmpyCd
     * @return
     */
    private static String getNotAllocWhCd(String glblCmpyCd) {
        VAR_CHAR_CONSTTMsg varCharTMsg = new VAR_CHAR_CONSTTMsg();
        varCharTMsg.glblCmpyCd.setValue(glblCmpyCd);
        varCharTMsg.varCharConstNm.setValue("NOT_HARD_ALLOC_WH_CD");

        VAR_CHAR_CONSTTMsg resTMsg = (VAR_CHAR_CONSTTMsg) S21CodeTableAccessor.findByKey(varCharTMsg);

        if (resTMsg == null) {
        }
        return resTMsg.varCharConstVal.getValue();

    }

    /**
     * check Retail Warehouse
     * @param whCd
     * @param glblCupyCd
     * @return
     */
    private static boolean checkWhCd(String whCd, String glblCupyCd) {
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCupyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, whCd);
        rtlWhTMsg = (RTL_WHTMsg) S21FastTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * DB access to check registered items
     * @param acMsg NLBL3150_ASMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean checkRegisteredRule(NLBL3150_ASMsg acMsg) {

        S21SsmEZDResult ssmResult = NLBL3150Query.getInstance().checkRegisteredRule(acMsg);
        if (ssmResult.isCodeNormal()) {
            // 2017/08/17 QC#20556 MOD BEGIN
            if ((((Map<String, BigDecimal>) ssmResult.getResultObject()).get("CN").compareTo(BigDecimal.ZERO)) != 0) {
                acMsg.rtlWhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Entered record" });
                acMsg.rtlSwhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Entered record" });
                acMsg.mdseCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Entered record" });
                return true;
            }
            // 2017/08/17 QC#20556 MOD END
        }
        return false;
    }

    /**
     * set Function Id
     * @param bizMsg NLBL3150CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NLBL3150CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.Z.no(funcIdCnt++).xxFuncId_Z.setValue(functionId);
        }
        bizMsg.Z.setValidCount(funcIdCnt);
    }

    /**
     * check difference
     * @param asMsg NLBL3150SMsg
     * @param csMsg NLBL3150_CSMsg
     * @return boolean
     */
    public static boolean checkDifference(NLBL3150_ASMsg asMsg, NLBL3150_CSMsg csMsg) {

        if (!ZYPCommonFunc.hasValue(asMsg.mdseWhCondPk_A)) {
            return true;
        }

        if (asMsg.mdseWhCondPk_A.getValue().equals(csMsg.mdseWhCondPk_C.getValue())) {
            if (!asMsg.mdseCd_A.getValue().equals(csMsg.mdseCd_C.getValue()) || //
                    !asMsg.rtlWhCd_A.getValue().equals(csMsg.rtlWhCd_C.getValue()) || // 
                    !asMsg.rtlSwhCd_A.getValue().equals(csMsg.rtlSwhCd_C.getValue()))
                return true;
        }
        return false;
    }

    /**
     * check Duplicate
     * @param sMsg NLBL3150SMsg
     * @return
     */
    public static boolean checkDuplicate(NLBL3150SMsg sMsg, int num) {
        NLBL3150_ASMsg asMsg = sMsg.A.no(num);
        int dupNum = 0;
        String mdseCd_A = asMsg.mdseCd_A.getValue();
        // 2017/08/14 QC#20556 DEL BEGIN
//        // 2017/08/10 QC#20556 MOD BEGIN
////        if (mdseCd_A.length() == 10) {
//        if (!ZYPCommonFunc.hasValue(asMsg.mdseWhCondPk_A)) {
//            if (mdseCd_A.length() == 10) {
//                dupNum++;
//            }
//            // 2017/08/10 QC#20556 MOD END
//            mdseCd_A = mdseCd_A.substring(0, 8);
//        }
        // 2017/08/14 QC#20556 DEL BEGIN
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            String mdseCd_D = sMsg.D.no(i).mdseCd_D.getValue();
            // 2017/08/10 QC#20556 DEL BEGIN
//            if (sMsg.D.no(i).mdseCd_D.getValue().length() == 10) {
//                mdseCd_D = mdseCd_D.substring(0, 8);
//            }
            // 2017/08/10 QC#20556 DEL END
            // 2017/08/14 QC#20556 MOD BEGIN
            // 2017/08/25 QC#20772 MOD BEGIN
//            boolean mdseCdDuplicated = mdseCd_A.equals(mdseCd_D) || mdseCd_A.substring(0, 8).equals(mdseCd_D);
            boolean mdseCdDuplicated = mdseCd_A.equals(mdseCd_D);
            if (mdseCd_A.length() > 8) {
                mdseCdDuplicated = mdseCdDuplicated || mdseCd_A.substring(0, 8).equals(mdseCd_D);
            }
            // 2017/08/25 QC#20772 MOD END
            boolean rtlWhCdDuplicated = asMsg.rtlWhCd_A.getValue().equals(sMsg.D.no(i).rtlWhCd_D.getValue());
            boolean rtlSwhCdDuplicated = asMsg.rtlSwhCd_A.getValue().equals(sMsg.D.no(i).rtlSwhCd_D.getValue()) || "*".equals(sMsg.D.no(i).rtlSwhCd_D.getValue());
//            if (mdseCd_A.equals(mdseCd_D) && // 
//                    asMsg.rtlWhCd_A.getValue().equals(sMsg.D.no(i).rtlWhCd_D.getValue()) && // 
//                    asMsg.rtlSwhCd_A.getValue().equals(sMsg.D.no(i).rtlSwhCd_D.getValue())) {
            if (mdseCdDuplicated &&
                    rtlWhCdDuplicated &&
                    rtlSwhCdDuplicated) {
            // 2017/08/14 QC#20556 MOD END
                dupNum++;
                if (dupNum == 2) {
                    asMsg.rtlWhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
                    asMsg.rtlSwhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
                    asMsg.mdseCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
                    return true;
                }
            }
        }
        return false;
    }

}
