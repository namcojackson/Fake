package business.servlet.NMAL7140.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.CMN_PRM_WHERE_NUM;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_FLG_ITEM;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_FLG_MDL;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_FLG_NULL;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_10_CLS_NAME;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_10_CLS_GUARD;
import static business.servlet.NMAL7140.constant.NMAL7140Constant.BTN_10_CLS_LABEL;
import business.servlet.NMAL6800.constant.NMAL6800Constant;
import business.servlet.NMAL7140.NMAL7140BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/06   Fujitsu         S.Kosaka        Create          N/A
 *</pre>
 */
public class NMAL7140CommonLogic {

    /**
     * initCmnBtnProp
     * @param handler S21CommonHandler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 1, null);
    }

    /**
     * setInputParam
     * @param scrnMsg NMAL7140BMsg
     * @param arg Object[]
     */
    public static void setInputParam(NMAL7140BMsg scrnMsg, Object[] arg) {
        scrnMsg.clear();

        if (!(arg instanceof Object[])) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.prcMktPrmoCd, getString(arg[0]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_H1, getDtString(arg[1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_H2, getDtString(arg[2]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt, getString(arg[3]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyValTxt, getString(arg[4]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcPrmoQlfyTpCd, getString(arg[5]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, getString(arg[6]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prmoAmt, getBigDecimal(arg[7]));
    }

    /**
     * setOutputParam
     * @param scrnMsg NMAL7140BMsg
     * @param arg Object[]
     */
    public static void setOutputParam(NMAL7140BMsg scrnMsg, Object[] arg) {

        if (!(arg instanceof Object[])) {
            return;
        }
        setValue(arg[0], scrnMsg.prcMktPrmoCd.getValue());
        setDtValue(arg[1], scrnMsg.effFromDt_H1.getValue());
        setDtValue(arg[2], scrnMsg.effFromDt_H2.getValue());
        setValue(arg[3], scrnMsg.mdseDescShortTxt.getValue());
        setValue(arg[4], scrnMsg.prcQlfyValTxt.getValue());
        setValue(arg[5], scrnMsg.prcPrmoQlfyTpCd.getValue());
        setValue(arg[6], scrnMsg.mdseCd.getValue());
        setValue(arg[7], scrnMsg.prmoAmt.getValue());
    }

    /**
     * clearScrn
     * @param scrnMsg NMAL7140BMsg
     */
    public static void clearScrn(NMAL7140BMsg scrnMsg) {
        scrnMsg.prcMktPrmoCd.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effFromDt_H2.clear();
        scrnMsg.mdseDescShortTxt.clear();
        scrnMsg.prcQlfyValTxt.clear();
        scrnMsg.prcPrmoQlfyTpCd.clear();
        scrnMsg.mdseCd.clear();
        scrnMsg.prmoAmt.clear();
        scrnMsg.xxBtnFlg.clear();
    }

    /**
     * promQifyCatgBtnCtrl
     * @param scrnMsg NMAL7140BMsg
     */
    public static void promQifyCatgBtnCtrl(NMAL7140BMsg scrnMsg) {
        String mktPrmoAudcCatgCd = scrnMsg.prcPrmoQlfyTpCd.getValue();

        if (PRC_PRMO_QLFY_TP.ITEM_CODE.equals(mktPrmoAudcCatgCd)
                || PRC_PRMO_QLFY_TP.BUNDLE.equals(mktPrmoAudcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg, BTN_FLG_ITEM);
        } else if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(mktPrmoAudcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg, BTN_FLG_MDL);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBtnFlg, BTN_FLG_NULL);
        }
    }

    /**
     * createArgumentNWAL1130_Qlfy
     * @param scrnMsg NMAL7140BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130_Qlfy(NMAL7140BMsg scrnMsg, String glblCmpyCd) {

        String mktPrmoAudcCatgCd = scrnMsg.prcPrmoQlfyTpCd.getValue();
        Object[] args = null;

        if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForMDL(scrnMsg, glblCmpyCd);
        }

        return args;
    }

    /**
     * createArgumentNWAL1130ForMDL
     * @param scrnMsg NMAL7140BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForMDL(NMAL7140BMsg scrnMsg, String glblCmpyCd) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();

        suffixP0 = "";
        scrnNmP1 = "Service Model Popup";

        tblNmP2.append(" SELECT");
        tblNmP2.append(" MN.T_GLBL_CMPY_CD GLBL_CMPY_CD");
        tblNmP2.append(",MN.EZCANCELFLAG");
        tblNmP2.append(",TO_CHAR(MN.T_MDL_ID) T_MDL_ID");
        tblNmP2.append(",MN.T_MDL_NM");
        tblNmP2.append(" FROM MDL_NM MN");
        tblNmP2.append(" WHERE");
        tblNmP2.append(" MN.T_GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append(" AND MN.EZCANCELFLAG = '").append("0").append("'");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "Service Model ID";
        whereArray0[1] = "T_MDL_ID";
        whereArray0[2] = scrnMsg.xxPopPrm_0.getValue();
        whereArray0[3] = "Y";
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
        whereArray1[0] = "Service Model Name";
        whereArray1[1] = "T_MDL_NM";
        whereArray1[2] = scrnMsg.prcQlfyValTxt.getValue();
        whereArray1[3] = "Y";
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "Service Model ID";
        columnArray0[1] = "T_MDL_ID";
        columnArray0[2] = BigDecimal.valueOf(22);
        columnArray0[3] = "N";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "Service Model Name";
        columnArray1[1] = "T_MDL_NM";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = "Y";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "T_MDL_ID";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);
        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNMWAL6800
     * @param scrnMsg NMAL7140BMsg
     * @param mdseNmItem EZDBStringItem
     * @param mdseCdItem EZDBStringItem
     * @return Object[]
     */
    public static Object[] createArgumentNMWAL6800(NMAL7140BMsg scrnMsg, EZDBStringItem mdseNmItem, EZDBStringItem mdseCdItem) {

        Object[] param = new Object[10];
        param[0] = mdseCdItem;
        param[1] = mdseNmItem;
        param[2] = scrnMsg.xxPopPrm_0;
        param[3] = scrnMsg.xxPopPrm_0;
        param[4] = scrnMsg.xxPopPrm_0;
        param[5] = scrnMsg.xxPopPrm_0;
        param[6] = scrnMsg.xxPopPrm_0;
        param[7] = mdseNmItem;
        param[8] = scrnMsg.xxPopPrm_0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, NMAL6800Constant.XX_MODE_CD_ALL);
        param[9] = scrnMsg.xxPopPrm_1;

        return param;
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

    private static void setValue(Object obj, String val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) obj, val);
    }

    private static void setValue(Object obj, BigDecimal val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) obj, val);
    }

    private static void setDtValue(Object obj, String val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBDateItem) obj, val);
    }
}
