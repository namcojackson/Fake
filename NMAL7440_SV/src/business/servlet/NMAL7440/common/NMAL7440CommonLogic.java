package business.servlet.NMAL7440.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import business.servlet.NMAL7440.NMAL7440BMsg;

import static business.servlet.NMAL7440.constant.NMAL7440Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.BTN_CMN_CLOSE;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.COMMA;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_EFF_FROM_DT_H1;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_EFF_FROM_DT_H2;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_EFF_THRU_DT_H1;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_EFF_THRU_DT_H2;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_PRC_GRP_NM;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_PRC_GRP_PK;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_PRC_GRP_PRCD_NUM;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_PRC_GRP_TP_CD;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_PRC_GRP_TRGT_ATTRB_CD;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_PRC_GRP_TRGT_TP_CD;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TF;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TT;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.DS_ACCT_NM;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.MAX_INPUT_DATA_CNT;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.MAX_VALUE_ITEM_LENGTH;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.NMAM0836E;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.NMAM8579E;
import static business.servlet.NMAL7440.constant.NMAL7440Constant.NMAM8696E;
/** 
 *<pre>
 * NMAL7440CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7440CommonLogic {

    /**
     * Initial Common Button properties
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * Set Screen Control
     * @param scrnMsg NMAL7440BMsg
     */
    public static void setScrnCtrl(NMAL7440BMsg scrnMsg) {
        scrnMsg.prcGrpPk.setInputProtected(true);
        scrnMsg.prcGrpNm.setInputProtected(true);

        scrnMsg.prcGrpPrcdNum.setAppFracDigit(0);
    }

    /**
     * Clear Screen Control
     * @param scrnMsg NMAL7440BMsg
     */
    public static void clearScrnCtrl(NMAL7440BMsg scrnMsg) {
        scrnMsg.prcGrpTpCd.clear();
        scrnMsg.prcGrpTrgtTpCd.clear();
        scrnMsg.prcGrpTrgtTpCd.clear();
        scrnMsg.prcGrpTrgtAttrbCd.clear();
        scrnMsg.xxPrcQlfyValSrchTxt_TF.clear();
        // 2023/04/20 QC#61200 Add Start
        scrnMsg.dsAcctNm.clear();
        // 2023/04/20 QC#61200 Add End
        scrnMsg.xxPrcQlfyValSrchTxt_TT.clear();
        scrnMsg.prcGrpPrcdNum.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effFromDt_H2.clear();
        scrnMsg.effThruDt_H1.clear();
        scrnMsg.effThruDt_H2.clear();
    }

    /**
     * Set Input Parameter
     * @param scrnMsg NMAL7440BMsg
     * @param arg Object[]
     */
    public static void setInputParam(NMAL7440BMsg scrnMsg, Object[] arg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpPk, getBigDecimal(arg[FILTER_PRC_GRP_PK]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpNm, getString(arg[FILTER_PRC_GRP_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpTpCd, getString(arg[FILTER_PRC_GRP_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpTrgtTpCd, getString(arg[FILTER_PRC_GRP_TRGT_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpTrgtAttrbCd, getString(arg[FILTER_PRC_GRP_TRGT_ATTRB_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcQlfyValSrchTxt_TF, getString(arg[FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TF]));
        // 2023/04/20 QC#61200 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm, getString(arg[DS_ACCT_NM]));
        // 2023/04/20 QC#61200 Add End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcQlfyValSrchTxt_TT, getString(arg[FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TT]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpPrcdNum, getBigDecimal(arg[FILTER_PRC_GRP_PRCD_NUM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_H1, getDtString(arg[FILTER_EFF_FROM_DT_H1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_H2, getDtString(arg[FILTER_EFF_FROM_DT_H2]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_H1, getDtString(arg[FILTER_EFF_THRU_DT_H1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_H2, getDtString(arg[FILTER_EFF_THRU_DT_H2]));
    }

    /**
     * Set Output Parameter
     * @param scrnMsg NMAL7440BMsg
     * @param arg Object[]
     */
    public static void setOutputParam(NMAL7440BMsg scrnMsg, Object[] arg) {

        setParamValue(arg[FILTER_PRC_GRP_TP_CD], scrnMsg.prcGrpTpCd);
        setParamValue(arg[FILTER_PRC_GRP_TRGT_TP_CD], scrnMsg.prcGrpTrgtTpCd);
        setParamValue(arg[FILTER_PRC_GRP_TRGT_ATTRB_CD], scrnMsg.prcGrpTrgtAttrbCd);
        setParamValue(arg[FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TF], scrnMsg.xxPrcQlfyValSrchTxt_TF);
        // 2023/04/20 QC#61200 Add Start
        if (!PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(scrnMsg.prcGrpTrgtAttrbCd.getValue())) {
            scrnMsg.dsAcctNm.clear();
        }
        setParamValue(arg[DS_ACCT_NM], scrnMsg.dsAcctNm);
        // 2023/04/20 QC#61200 Add Start
        setParamValue(arg[FILTER_XX_PRC_QLFY_VAL_SRCH_TXT_TT], scrnMsg.xxPrcQlfyValSrchTxt_TT);
        setParamValue(arg[FILTER_PRC_GRP_PRCD_NUM], scrnMsg.prcGrpPrcdNum);
        setParamValue(arg[FILTER_EFF_FROM_DT_H1], scrnMsg.effFromDt_H1);
        setParamValue(arg[FILTER_EFF_FROM_DT_H2], scrnMsg.effFromDt_H2);
        setParamValue(arg[FILTER_EFF_THRU_DT_H1], scrnMsg.effThruDt_H1);
        setParamValue(arg[FILTER_EFF_THRU_DT_H2], scrnMsg.effThruDt_H2);
    }

    /**
     * Check Item
     * @param scrnMsg NMAL7440BMsg
     * @param arg Object[]
     */
    public static void checkItem(NMAL7440BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_TF) || ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_TT)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtTpCd) || !ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtAttrbCd)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtTpCd)) {
                    scrnMsg.prcGrpTrgtTpCd.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.prcGrpTrgtTpCd.getNameForMessage()});
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtAttrbCd)) {
                    scrnMsg.prcGrpTrgtAttrbCd.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.prcGrpTrgtAttrbCd.getNameForMessage()});
                }
                return;
            }
        }
        checkCSVValue(scrnMsg.xxPrcQlfyValSrchTxt_TF);
        checkCSVValue(scrnMsg.xxPrcQlfyValSrchTxt_TT);
    }

    private static void checkCSVValue(EZDBStringItem valueItem) {
        if (ZYPCommonFunc.hasValue(valueItem)) {
            String[] strArray = S21StringUtil.toStringArray(valueItem.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                valueItem.setErrorInfo(1, NMAM8696E, new String[] {valueItem.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT)});
                return;
            }
            Iterator<String> i = Arrays.asList(strArray).iterator();
            while(i.hasNext()){
                if (i.next().length() > MAX_VALUE_ITEM_LENGTH) {
                    valueItem.setErrorInfo(1, NMAM8579E, new String[] {valueItem.getNameForMessage()});
                    return;
                }
            }
        }
    }

    private static String getString(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBStringItem) obj).getValue();
    }

    private static BigDecimal getBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBBigDecimalItem) obj).getValue();
    }

    private static String getDtString(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBDateItem) obj).getValue();
    }

    private static void setParamValue(Object obj, EZDBStringItem val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) obj, val.getValue());
    }

    private static void setParamValue(Object obj, EZDBBigDecimalItem val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) obj, val.getValue());
    }

    private static void setParamValue(Object obj, EZDBDateItem val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBDateItem) obj, val.getValue());
    }
}
