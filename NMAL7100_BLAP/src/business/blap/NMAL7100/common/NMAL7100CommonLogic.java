/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7100.common;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.blap.NMAL7100.NMAL7100Query;
import business.blap.NMAL7100.NMAL7100SMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM0163E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.YYYYMMDD_LENGTH;
import static business.blap.NMAL7100.constant.NMAL7100Constant.HIGH_VAL_DT;
import static business.blap.NMAL7100.constant.NMAL7100Constant.DATE_FORMAT_PADDING_ZERO;
import static business.blap.NMAL7100.constant.NMAL7100Constant.SLASH;


/**
 *<pre>
 * NMAL7100CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   Fujitsu         M.Hara          Create          CSA
 * 01/19/2016   Fujitsu         M.Hara          Update          QC#3089
 * 04/08/2016   Fujitsu         M.Ohno          Update          QC#5944
 * 2016/11/10   Fujitsu         W.Honda         Update          QC#15842
 * 2019/03/13   Fujitsu         S.Kosaka        Update          QC#30725
 * 2019/12/06   Fujitsu         S.Kosaka        Update          QC#54215
 *</pre>
 */
public class NMAL7100CommonLogic {

    // START QC#3089 01/19/2016 ADD
    /**
     * changeApplyBtnFlg
     * @param bizMsg NMAL7100CMsg
     * @param chkFlg String
     */
    public static void changeApplyBtnFlg(NMAL7100CMsg bizMsg, String chkFlg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_DH, chkFlg);
    }
    // END QC#3089 01/19/2016 ADD

    /**
     * selectUnselect
     * @param bizMsg NMAL7100CMsg
     * @param chkFlg String
     */
    public static void selectUnselect(NMAL7100CMsg bizMsg, String chkFlg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_DA, chkFlg);
            } else {
                bizMsg.A.no(i).xxChkBox_DA.clear();
            }
        }
    }

    /**
     * toHighValDate.
     * @param dateVal String
     * @return String
     */
    public static String convertDateTo(String dateVal) {
        if (ZYPCommonFunc.hasValue(dateVal)) {
            return dateVal;
        }
        return HIGH_VAL_DT;
    }

    /**
     * Convert To BigDecimal
     * @param val Object
     * @return BigDecimal
     */
    public static BigDecimal convToBigDecimal(Object val) {
        if (null == val) {
            return BigDecimal.ZERO;
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else if (val instanceof EZDBStringItem) {
            return convToBigDecimal(((EZDBStringItem) val).getValue());
        } else if (val instanceof EZDBBigDecimalItem) {
            return ((EZDBBigDecimalItem) val).getValue();
        } else if (val instanceof EZDCStringItem) {
            return convToBigDecimal(((EZDCStringItem) val).getValue());
        } else if (val instanceof EZDCBigDecimalItem) {
            return ((EZDCBigDecimalItem) val).getValue();
        } else {
            return new BigDecimal(val.toString());
        }
    }

    // START QC#5944 04/08/2016 ADD
    /**
     * formatDt
     * @param dt String
     * @return String formated
     */
    public static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }
    // END QC#5944 04/08/2016 END

    // START QC#6154 04/15/2016 ADD
    /**
     * searchSvrModel
     * @param checkCdFiled EZDCStringItem
     * @param glblCmpyCd String
     * @param searchFlg boolean
     * @return boolean
     */
//    public static boolean searchSvrModel(EZDCStringItem targetCdFiled, String glblCmpyCd, boolean searchFlg) {// QC#15842 11/10/2016 MOD
    public static boolean searchSvrModel(EZDSStringItem targetCdFiled, String glblCmpyCd, boolean searchFlg) {// QC#15842 11/10/2016 MOD
        boolean isSuccess = true;

        S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getMdlId(targetCdFiled.getValue());

        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            targetCdFiled.setErrorInfo(1, NMAM0163E, new String[] {targetCdFiled.getValue(), "Model Name"});
            isSuccess = false;
        }else{
            BigDecimal mdlId = (BigDecimal) ssmResult.getResultObject();
            if(mdlId != null){
                if (searchFlg) {
                    ZYPEZDItemValueSetter.setValue(targetCdFiled, mdlId.toString());
                }
            }
        }

        return isSuccess;
    }
    // END QC#6154 04/15/2016 ADD

    // START QC#15842 11/10/2016 ADD
    /**
     * Update the global Message.
     * @param bizMsg NMAL7100CMsg
     * @param glblMsg NMAL7100SMsg
     */
    public static void updateGlblMsg(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg) {
        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg EZDSMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsg sMsg, EZDSMsgArray sMsgArray) {

        NMAL7100CMsg bizMsg = (NMAL7100CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }
    // END QC#15842 11/10/2016 ADD

    // 2019/03/13 QC#30725 Add Start
    /**
     * formatDateStr
     * @param baseStr String of format -M/-D/YYYY
     * @return String of formated MM/DD/YYYY
     */
    public static String formatDateStr(String baseStr) {
        String[] devStr = baseStr.split(SLASH);
        StringBuilder sb = new StringBuilder();

        // Illegal case
        if (devStr.length != 3 || devStr[0] == null || devStr[1] == null || devStr[2] == null || devStr[0].length() < 1 || devStr[1].length() < 1 || devStr[2].length() != 4) {
            return baseStr;
        } else {
            if (devStr[0].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[0]);
            sb.append(SLASH);
            if (devStr[1].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[1]);
            sb.append(SLASH);
            sb.append(devStr[2]);
            return sb.toString();
        }
    }

    /**
     * checkItemCnt
     * @param item String
     * @param itemCnt integer
     * @return boolean
     */
    public static boolean checkItemCnt(String item, int itemCnt) {
        if (itemCnt < item.length()) {
            return true;
        } else {
            return false;
        }
    }
    // 2019/03/13 QC#30725 Add End
    // 2019/12/06 QC#54215 Add Start
    public static void clearFilter(NMAL7100CMsg bizMsg) {
        bizMsg.effFromDt_E1.clear();
        bizMsg.effFromDt_E2.clear();
        bizMsg.mdseCd_E1.clear();
        bizMsg.mdseDescShortTxt_E1.clear();
        bizMsg.prcMktPrmoCd_E1.clear();
        bizMsg.prcPrmoQlfyTpCd_E1.clear();
        bizMsg.prcQlfyValTxt_E1.clear();
        bizMsg.prmoAmt_E1.clear();
    }
    // 2019/12/06 QC#54215 Add End
}
