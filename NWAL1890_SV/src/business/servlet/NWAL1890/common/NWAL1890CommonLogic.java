/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1890.common;

import static business.servlet.NWAL1890.constant.NWAL1890Constant.NWAM0938E;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.BTN_08_CLR_GUARD;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.BTN_08_CLR_LABEL;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.BTN_08_CLR_NAME;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.BTN_10_CLS_GUARD;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.BTN_10_CLS_LABEL;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.BTN_10_CLS_NAME;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.CANCELLED;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.CLOSED;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_0;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_1;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_2;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_3;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_4;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_5;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_6;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_7;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_10;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.IDX_30;
import static business.servlet.NWAL1890.constant.NWAL1890Constant.LINE_CONFIG_MODE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBStringItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import business.servlet.NWAL1890.NWAL1890BMsg;

/**
 *<pre>
 * NWAL1890CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/09   Fujitsu         T.Aoi           Create          N/A
 * 2017/08/24   Fujitsu         M.Yamada        Update          QC#19931(L3)
 *</pre>
 */
public class NWAL1890CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_08_CLR_NAME, BTN_08_CLR_GUARD, BTN_08_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_CLS_NAME, BTN_10_CLS_GUARD, BTN_10_CLS_LABEL, 1, null);
    }

    /**
     * Set Control Fields For Link
     * @param scrnMsg NWAL1890BMsg
     */
    public static void setControlFieldsForLink(NWAL1890BMsg scrnMsg) {

        // Config Level
        scrnMsg.xxConfigIdSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxMdlSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxBillToAcctNmSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxBillToAcctCdSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxBillToLocCdSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxShipToAcctNmSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxShipToAcctCdSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxShipToLocCdSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxSoldToAcctNmSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxSoldToAcctCdSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxSoldToLocCdSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            // Line Level
            scrnMsg.xxLineStsSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxOrdItemSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxRtlWhSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxRtlSwhSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxCpoSrcTpSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxOrdSrcRefNumSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxSbstItemSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxSerNumSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        } else {
            // RMA Line Level
            scrnMsg.xxLineStsSrchTxt_RL.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxOrdItemSrchTxt_RL.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxRtrnRsnSrchTxt_RL.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxRtlWhSrchTxt_RL.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxRtlSwhSrchTxt_RL.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxOrdSrcRefNumSrchTxt_RL.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxSerNumSrchTxt_RL.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * Control Detail Screen fields
     * @param scrnMsg NWAL1890BMsg
     */
    public static void controlDetailScreenFields(NWAL1890BMsg scrnMsg) {

        scrnMsg.cpoOrdNum.setInputProtected(true);

        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.xxLineNum_R.setInputProtected(true);
            scrnMsg.xxLineStsSrchTxt_RL.setInputProtected(true);
            scrnMsg.xxLineStsSrchTxt_R.setInputProtected(true);
            scrnMsg.xxOrdItemSrchTxt_RL.setInputProtected(true);
            scrnMsg.xxOrdItemSrchTxt_R.setInputProtected(true);
            scrnMsg.xxRtrnRsnSrchTxt_RL.setInputProtected(true);
            scrnMsg.xxRtrnRsnSrchTxt_R.setInputProtected(true);
            scrnMsg.xxRtlWhSrchTxt_RL.setInputProtected(true);
            scrnMsg.xxRtlWhSrchTxt_R.setInputProtected(true);
            scrnMsg.xxRtlSwhSrchTxt_RL.setInputProtected(true);
            scrnMsg.xxRtlSwhSrchTxt_R.setInputProtected(true);
            scrnMsg.xxOrdSrcRefNumSrchTxt_RL.setInputProtected(true);
            scrnMsg.xxOrdSrcRefNumSrchTxt_R.setInputProtected(true);
            scrnMsg.xxSerNumSrchTxt_RL.setInputProtected(true);
            scrnMsg.xxSerNumSrchTxt_R.setInputProtected(true);
            scrnMsg.xxShowInclCloLineFlg_R.setInputProtected(true);
            scrnMsg.xxShowInclCancLineFlg_R.setInputProtected(true);
        } else {
            scrnMsg.xxLineNum.setInputProtected(true);
            scrnMsg.xxLineStsSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxLineStsSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdItemSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdItemSrchTxt.setInputProtected(true);
            scrnMsg.xxRtlWhSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxRtlWhSrchTxt.setInputProtected(true);
            scrnMsg.xxRtlSwhSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxRtlSwhSrchTxt.setInputProtected(true);
            scrnMsg.xxCpoSrcTpSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxCpoSrcTpSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdSrcRefNumSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdSrcRefNumSrchTxt.setInputProtected(true);
            scrnMsg.xxSbstItemSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxSbstItemSrchTxt.setInputProtected(true);
            scrnMsg.xxSerNumSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxSerNumSrchTxt.setInputProtected(true);
            scrnMsg.xxShowInclCloLineFlg.setInputProtected(true);
            scrnMsg.xxShowInclCancLineFlg.setInputProtected(true);
        }
    }

    /**
     * Set Output Parameter.
     * @param scrnMsg NWAL1890BMsg
     * @param arg Object[]
     */
    public static void setOutputParameter(NWAL1890BMsg scrnMsg, Serializable arg) {

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.xxModeCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[1], scrnMsg.cpoOrdNum);
            // Config Level
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[3], scrnMsg.xxConfigIdSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[4], scrnMsg.xxMdlSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[5], scrnMsg.xxBillToAcctNmSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[6], scrnMsg.xxBillToAcctCdSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[7], scrnMsg.xxBillToLocCdSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[8], scrnMsg.xxShipToAcctNmSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[9], scrnMsg.xxShipToAcctCdSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[10], scrnMsg.xxShipToLocCdSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[11], scrnMsg.xxSoldToAcctNmSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[12], scrnMsg.xxSoldToAcctCdSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[13], scrnMsg.xxSoldToLocCdSrchTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[14], scrnMsg.xxShowInclLineFlg);
            if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                // Line Level
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[15], scrnMsg.xxLineNum);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[16], scrnMsg.xxLineStsSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[17], scrnMsg.xxOrdItemSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[18], scrnMsg.xxRtlWhSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[19], scrnMsg.xxRtlSwhSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[20], scrnMsg.xxCpoSrcTpSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[21], scrnMsg.xxOrdSrcRefNumSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[22], scrnMsg.xxSbstItemSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[23], scrnMsg.xxSerNumSrchTxt);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[24], scrnMsg.xxShowInclCloLineFlg);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[25], scrnMsg.xxShowInclCancLineFlg);
            } else {
                // RMA
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[26], scrnMsg.xxLineNum_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[27], scrnMsg.xxLineStsSrchTxt_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[28], scrnMsg.xxOrdItemSrchTxt_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[29], scrnMsg.xxRtrnRsnSrchTxt_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[30], scrnMsg.xxRtlWhSrchTxt_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[31], scrnMsg.xxRtlSwhSrchTxt_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[32], scrnMsg.xxOrdSrcRefNumSrchTxt_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[33], scrnMsg.xxSerNumSrchTxt_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[34], scrnMsg.xxShowInclCloLineFlg_R);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[35], scrnMsg.xxShowInclCancLineFlg_R);
            }
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NWAL1890BMsg
     */
    public static void addChkItem(NWAL1890BMsg scrnMsg) {
        // Config Level
        scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxConfigIdSrchTxt) && !ZYPCommonFunc.isNumeric(scrnMsg.xxConfigIdSrchTxt.getValue())) {
            scrnMsg.xxConfigIdSrchTxt.setErrorInfo(1, NWAM0938E, new String[] {"Config Number" });
        }
        scrnMsg.addCheckItem(scrnMsg.xxConfigIdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxMdlSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxBillToAcctNmSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxBillToAcctCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxBillToLocCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxShipToAcctNmSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxShipToAcctCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxShipToLocCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSoldToAcctNmSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSoldToAcctCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSoldToLocCdSrchTxt);
        // Line Level
        scrnMsg.addCheckItem(scrnMsg.xxLineNum);
        scrnMsg.addCheckItem(scrnMsg.xxLineStsSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxOrdItemSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxRtlWhSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxRtlSwhSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCpoSrcTpSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxOrdSrcRefNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSbstItemSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSerNumSrchTxt);
        // RMA Line Level
        scrnMsg.addCheckItem(scrnMsg.xxLineNum_R);
        scrnMsg.addCheckItem(scrnMsg.xxLineStsSrchTxt_R);
        scrnMsg.addCheckItem(scrnMsg.xxOrdItemSrchTxt_R);
        scrnMsg.addCheckItem(scrnMsg.xxRtrnRsnSrchTxt_R);
        scrnMsg.addCheckItem(scrnMsg.xxRtlWhSrchTxt_R);
        scrnMsg.addCheckItem(scrnMsg.xxRtlSwhSrchTxt_R);
        scrnMsg.addCheckItem(scrnMsg.xxOrdSrcRefNumSrchTxt_R);
        scrnMsg.addCheckItem(scrnMsg.xxSerNumSrchTxt_R);

        scrnMsg.putErrorScreen();
    }

    /**
     * Get Param NWAL1890 For Config ID
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Config ID
     */
    public static Object[] getParamForConfigID(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Config ID Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.SVC_CONFIG_MSTR_PK");
        sb.append("   ,DCC.MDL_ID");
        sb.append("   ,DCC.MDL_DESC_TXT");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCC.SVC_CONFIG_MSTR_PK IS NOT NULL");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Config ID";
        whereArray0[IDX_1] = "SVC_CONFIG_MSTR_PK";
        if (hasValue(scrnMsg.xxConfigIdSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxConfigIdSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Model ID";
        whereArray1[IDX_1] = "MDL_ID";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Model Name";
        whereArray2[IDX_1] = "MDL_DESC_TXT";
        whereArray2[IDX_2] = null;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Config ID";
        columnArray0[IDX_1] = "SVC_CONFIG_MSTR_PK";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Model ID";
        columnArray1[IDX_1] = "MDL_ID";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Model Name";
        columnArray2[IDX_1] = "MDL_DESC_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SVC_CONFIG_MSTR_PK";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Model
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Model
     */
    public static Object[] getParamForModel(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Model Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.MDL_ID");
        sb.append("   , MN.T_MDL_NM"); // 2017/08/24 mod
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG DCC");
        sb.append("    ,MDL_NM       MN");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.MDL_ID IS NOT NULL");
        sb.append("    AND DCC.EZCANCELFLAG = '0'");
        // 2017/08/24 add
        sb.append("    AND DCC.GLBL_CMPY_CD = MN.T_GLBL_CMPY_CD");
        sb.append("    AND DCC.MDL_ID       = MN.T_MDL_ID");
        sb.append("    AND MN.EZCANCELFLAG  = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Model Name";
        whereArray0[IDX_1] = "T_MDL_NM"; // 2017/08/24 mod
        if (hasValue(scrnMsg.xxMdlSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxMdlSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Model ID";
        columnArray0[IDX_1] = "MDL_ID";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Model Name";
        columnArray1[IDX_1] = "T_MDL_NM"; // 2017/08/24 mod
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "MDL_ID";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Bill To Cust Name
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Bill To Cust Name
     */
    public static Object[] getParamForBillToCustName(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Bill To Cust Name Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.BILL_TO_CUST_ACCT_CD");
        sb.append("   ,DCC.BILL_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Account Code";
        whereArray0[IDX_1] = "BILL_TO_CUST_ACCT_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Account Name";
        whereArray1[IDX_1] = "BILL_TO_CUST_ACCT_NM";
        if (hasValue(scrnMsg.xxBillToAcctNmSrchTxt.getValue())) {
            whereArray1[IDX_2] = scrnMsg.xxBillToAcctNmSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Account Code";
        columnArray0[IDX_1] = "BILL_TO_CUST_ACCT_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Account Name";
        columnArray1[IDX_1] = "BILL_TO_CUST_ACCT_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "BILL_TO_CUST_ACCT_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Bill To Cust Code
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Bill To Cust Code
     */
    public static Object[] getParamForBillToCustCode(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Bill To Cust Code Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.BILL_TO_CUST_ACCT_CD");
        sb.append("   ,DCC.BILL_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Account Code";
        whereArray0[IDX_1] = "BILL_TO_CUST_ACCT_CD";
        if (hasValue(scrnMsg.xxBillToAcctCdSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxBillToAcctCdSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Account Name";
        whereArray1[IDX_1] = "BILL_TO_CUST_ACCT_NM";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Account Code";
        columnArray0[IDX_1] = "BILL_TO_CUST_ACCT_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Account Name";
        columnArray1[IDX_1] = "BILL_TO_CUST_ACCT_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "BILL_TO_CUST_ACCT_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Bill To Location Code
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Bill To Location Code
     */
    public static Object[] getParamForBillToLocCode(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Bill To Location Code Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.BILL_TO_CUST_LOC_CD");
        sb.append("   ,DCC.BILL_TO_CUST_LOC_ADDR");
        sb.append("   ,DCC.BILL_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Location Code";
        whereArray0[IDX_1] = "BILL_TO_CUST_LOC_CD";
        if (hasValue(scrnMsg.xxBillToLocCdSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxBillToLocCdSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Address";
        whereArray1[IDX_1] = "BILL_TO_CUST_LOC_ADDR";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Account Name";
        whereArray2[IDX_1] = "BILL_TO_CUST_ACCT_NM";
        whereArray2[IDX_2] = null;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Location Code";
        columnArray0[IDX_1] = "BILL_TO_CUST_LOC_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Address";
        columnArray1[IDX_1] = "BILL_TO_CUST_LOC_ADDR";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Account Name";
        columnArray2[IDX_1] = "BILL_TO_CUST_ACCT_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "BILL_TO_CUST_LOC_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Ship To Cust Name
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Ship To Cust Name
     */
    public static Object[] getParamForShipToCustName(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Ship To Cust Name Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.SHIP_TO_CUST_ACCT_CD");
        sb.append("   ,DCC.SHIP_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Account Code";
        whereArray0[IDX_1] = "SHIP_TO_CUST_ACCT_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Account Name";
        whereArray1[IDX_1] = "SHIP_TO_CUST_ACCT_NM";
        if (hasValue(scrnMsg.xxShipToAcctNmSrchTxt.getValue())) {
            whereArray1[IDX_2] = scrnMsg.xxShipToAcctNmSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Account Code";
        columnArray0[IDX_1] = "SHIP_TO_CUST_ACCT_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Account Name";
        columnArray1[IDX_1] = "SHIP_TO_CUST_ACCT_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SHIP_TO_CUST_ACCT_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Ship To Cust Code
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Ship To Cust Code
     */
    public static Object[] getParamForShipToCustCode(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Ship To Cust Code Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.SHIP_TO_CUST_ACCT_CD");
        sb.append("   ,DCC.SHIP_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Account Code";
        whereArray0[IDX_1] = "SHIP_TO_CUST_ACCT_CD";
        if (hasValue(scrnMsg.xxShipToAcctCdSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxShipToAcctCdSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Account Name";
        whereArray1[IDX_1] = "SHIP_TO_CUST_ACCT_NM";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Account Code";
        columnArray0[IDX_1] = "SHIP_TO_CUST_ACCT_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Account Name";
        columnArray1[IDX_1] = "SHIP_TO_CUST_ACCT_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SHIP_TO_CUST_ACCT_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Ship To Location Code
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Ship To Location Code
     */
    public static Object[] getParamForShipToLocCode(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Ship To Location Code Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.SHIP_TO_CUST_LOC_CD");
        sb.append("   ,DCC.SHIP_TO_CUST_LOC_ADDR");
        sb.append("   ,DCC.SHIP_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Location Code";
        whereArray0[IDX_1] = "SHIP_TO_CUST_LOC_CD";
        if (hasValue(scrnMsg.xxShipToLocCdSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxShipToLocCdSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Address";
        whereArray1[IDX_1] = "SHIP_TO_CUST_LOC_ADDR";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Account Name";
        whereArray2[IDX_1] = "SHIP_TO_CUST_ACCT_NM";
        whereArray2[IDX_2] = null;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Location Code";
        columnArray0[IDX_1] = "SHIP_TO_CUST_LOC_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Address";
        columnArray1[IDX_1] = "SHIP_TO_CUST_LOC_ADDR";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Account Name";
        columnArray2[IDX_1] = "SHIP_TO_CUST_ACCT_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SHIP_TO_CUST_LOC_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Sold To Cust Name
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Sold To Cust Name
     */
    public static Object[] getParamForSoldToCustName(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sold To Cust Name Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.SELL_TO_CUST_CD");
        sb.append("   ,DCC.SOLD_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Account Code";
        whereArray0[IDX_1] = "SELL_TO_CUST_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Account Name";
        whereArray1[IDX_1] = "SOLD_TO_CUST_ACCT_NM";
        if (hasValue(scrnMsg.xxSoldToAcctNmSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxSoldToAcctNmSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Account Code";
        columnArray0[IDX_1] = "SELL_TO_CUST_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Account Name";
        columnArray1[IDX_1] = "SOLD_TO_CUST_ACCT_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SELL_TO_CUST_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Sold To Cust Code
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Sold To Cust Code
     */
    public static Object[] getParamForSoldToCustCode(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sold To Cust Code Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.SELL_TO_CUST_CD");
        sb.append("   ,DCC.SOLD_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Account Code";
        whereArray0[IDX_1] = "SELL_TO_CUST_CD";
        if (hasValue(scrnMsg.xxSoldToAcctCdSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxSoldToAcctCdSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Account Name";
        whereArray1[IDX_1] = "SOLD_TO_CUST_ACCT_NM";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Account Code";
        columnArray0[IDX_1] = "SELL_TO_CUST_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Account Name";
        columnArray1[IDX_1] = "SOLD_TO_CUST_ACCT_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SELL_TO_CUST_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For SOLD To Location Code
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Sold To Location Code
     */
    public static Object[] getParamForSoldToLocCode(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sold To Location Code Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCC.GLBL_CMPY_CD");
        sb.append("   ,DCC.EZCANCELFLAG");
        sb.append("   ,DCC.SOLD_TO_CUST_LOC_CD");
        sb.append("   ,DCC.SOLD_TO_CUST_LOC_ADDR");
        sb.append("   ,DCC.SOLD_TO_CUST_ACCT_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_CONFIG_V DCC");
        sb.append(" WHERE");
        sb.append("        DCC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCC.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        if (LINE_CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.OUTBOUND).append("'");
        } else {
            sb.append("    AND DCC.CONFIG_CATG_CD = '").append(CONFIG_CATG.INBOUND).append("'");
        }
        sb.append("    AND DCC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Location Code";
        whereArray0[IDX_1] = "SOLD_TO_CUST_LOC_CD";
        if (hasValue(scrnMsg.xxSoldToLocCdSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxSoldToLocCdSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Address";
        whereArray1[IDX_1] = "SOLD_TO_CUST_LOC_ADDR";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Account Name";
        whereArray2[IDX_1] = "SOLD_TO_CUST_ACCT_NM";
        whereArray2[IDX_2] = null;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Location Code";
        columnArray0[IDX_1] = "SOLD_TO_CUST_LOC_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Address";
        columnArray1[IDX_1] = "SOLD_TO_CUST_LOC_ADDR";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Account Name";
        columnArray2[IDX_1] = "SOLD_TO_CUST_ACCT_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SOLD_TO_CUST_LOC_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Line Status
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Line Status
     */
    public static Object[] getParamForLineStatus(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Line Status Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DOS.GLBL_CMPY_CD");
        sb.append("   ,DOS.EZCANCELFLAG");
        sb.append("   ,OLDS.ORD_LINE_DPLY_STS_CD");
        sb.append("   ,OLDS.ORD_LINE_DPLY_STS_DESC_TXT");
        sb.append(" FROM");
        sb.append("    DS_ORD_STS_V DOS");
        sb.append("   ,ORD_LINE_DPLY_STS OLDS");
        sb.append(" WHERE");
        sb.append("        DOS.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DOS.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DOS.CPO_DTL_LINE_NUM IS NOT NULL");
        sb.append("    AND DOS.ORD_LINE_STS_NM NOT IN ('").append(CANCELLED).append("','").append(CLOSED).append("')");
        sb.append("    AND DOS.EZCANCELFLAG = '0'");
        sb.append("    AND OLDS.GLBL_CMPY_CD = DOS.GLBL_CMPY_CD");
        sb.append("    AND OLDS.ORD_LINE_DPLY_STS_DESC_TXT = DOS.ORD_LINE_STS_NM");
        sb.append("    AND OLDS.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Line Status";
        whereArray0[IDX_1] = "ORD_LINE_DPLY_STS_DESC_TXT";
        if (hasValue(scrnMsg.xxLineStsSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxLineStsSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Line Status";
        columnArray0[IDX_1] = "ORD_LINE_DPLY_STS_DESC_TXT";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "ORD_LINE_DPLY_STS_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Ordered Item
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Ordered Item
     */
    public static Object[] getParamForOrderedItem(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Ordered Item Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DECODE(DCV.SBST_MDSE_CD, NULL, DCV.MDSE_CD, DCV.ORIG_MDSE_CD) MDSE_CD");
        sb.append("   ,DECODE(DCV.SBST_MDSE_CD, NULL, DCV.MDSE_NM, AMV.MDSE_NM) MDSE_NM");
        sb.append(" FROM");
        sb.append("    CPO_DTL_V DCV");
        sb.append("   ,ALL_MDSE_V AMV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND AMV.GLBL_CMPY_CD = DCV.GLBL_CMPY_CD(+)");
        sb.append("    AND AMV.MDSE_CD      = DCV.ORIG_MDSE_CD(+)");
        sb.append("    AND AMV.EZCANCELFLAG(+) = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Item Code";
        whereArray0[IDX_1] = "MDSE_CD";
        if (hasValue(scrnMsg.xxOrdItemSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxOrdItemSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Item Name";
        whereArray1[IDX_1] = "MDSE_NM";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Item Code";
        columnArray0[IDX_1] = "MDSE_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Item Name";
        columnArray1[IDX_1] = "MDSE_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "MDSE_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Warehouse
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Warehouse
     */
    public static Object[] getParamForWarehouse(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Warehouse Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.RTL_WH_CD");
        sb.append("   ,DCV.RTL_WH_NM");
        sb.append(" FROM");
        sb.append("    CPO_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.RTL_WH_CD IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "WH Code";
        whereArray0[IDX_1] = "RTL_WH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "WH Name";
        whereArray1[IDX_1] = "RTL_WH_NM";
        if (hasValue(scrnMsg.xxRtlWhSrchTxt.getValue())) {
            whereArray1[IDX_2] = scrnMsg.xxRtlWhSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "WH Code";
        columnArray0[IDX_1] = "RTL_WH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "WH Name";
        columnArray1[IDX_1] = "RTL_WH_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTL_WH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Sub Warehouse
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Sub Warehouse
     */
    public static Object[] getParamForSubWarehouse(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sub Warehouse Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.RTL_SWH_CD");
        sb.append("   ,DCV.RTL_SWH_NM");
        sb.append(" FROM");
        sb.append("    CPO_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.RTL_SWH_CD IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "SWH Code";
        whereArray0[IDX_1] = "RTL_SWH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "SWH Name";
        whereArray1[IDX_1] = "RTL_SWH_NM";
        if (hasValue(scrnMsg.xxRtlSwhSrchTxt.getValue())) {
            whereArray1[IDX_2] = scrnMsg.xxRtlSwhSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "SWH Code";
        columnArray0[IDX_1] = "RTL_SWH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "SWH Name";
        columnArray1[IDX_1] = "RTL_SWH_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTL_SWH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Source Type
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Source Type
     */
    public static Object[] getParamForSourceType(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Source Type Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,OLS.ORD_LINE_SRC_NM");
        sb.append(" FROM");
        sb.append("    CPO_DTL_V DCV");
        sb.append("   ,ORD_LINE_SRC OLS");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.ORD_LINE_SRC_CD IS NOT NULL");
        sb.append("    AND OLS.GLBL_CMPY_CD = DCV.GLBL_CMPY_CD");
        sb.append("    AND OLS.ORD_LINE_SRC_CD = DCV.ORD_LINE_SRC_CD");
        sb.append("    AND OLS.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Source Type";
        whereArray0[IDX_1] = "ORD_LINE_SRC_NM";
        if (hasValue(scrnMsg.xxCpoSrcTpSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxCpoSrcTpSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Source Type";
        columnArray0[IDX_1] = "ORD_LINE_SRC_NM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "ORD_LINE_SRC_NM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Line Source Reference Number
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Line Source Reference Number
     */
    public static Object[] getParamForLineSourceRef(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Line Source Reference# Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.LINE_SRC_REF_NUM");
        sb.append(" FROM");
        sb.append("    CPO_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.LINE_SRC_REF_NUM IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Reference#";
        whereArray0[IDX_1] = "LINE_SRC_REF_NUM";
        if (hasValue(scrnMsg.xxOrdSrcRefNumSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxOrdSrcRefNumSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Reference#";
        columnArray0[IDX_1] = "LINE_SRC_REF_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "LINE_SRC_REF_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Substitute Item
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Substitute
     */
    public static Object[] getParamForSubstituteItem(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Substitute Item Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.SBST_MDSE_CD SBST_MDSE_CD");
        sb.append("   ,AMV.MDSE_NM SBST_MDSE_NM");
        sb.append(" FROM");
        sb.append("    CPO_DTL_V  DCV");
        sb.append("   ,ALL_MDSE_V AMV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.SBST_MDSE_CD IS NOT NULL");
        sb.append("    AND AMV.GLBL_CMPY_CD = DCV.GLBL_CMPY_CD");
        sb.append("    AND AMV.MDSE_CD      = DCV.SBST_MDSE_CD");
        sb.append("    AND AMV.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Substitute Item Code";
        whereArray0[IDX_1] = "SBST_MDSE_CD";
        if (hasValue(scrnMsg.xxSbstItemSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxSbstItemSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Substitute Item Name";
        whereArray1[IDX_1] = "SBST_MDSE_NM";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Substitute Item Code";
        columnArray0[IDX_1] = "SBST_MDSE_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Substitute Item Name";
        columnArray1[IDX_1] = "SBST_MDSE_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SBST_MDSE_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For Serial Number
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For Serial Number
     */
    public static Object[] getParamForSerialNumber(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Serial Number Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.SER_NUM");
        sb.append("   ,DECODE(DCV.SBST_MDSE_CD, NULL, DCV.MDSE_CD, DCV.ORIG_MDSE_CD) MDSE_CD");
        sb.append("   ,DECODE(DCV.SBST_MDSE_CD, NULL, DCV.MDSE_NM, DCV.ORIG_MDSE_NM) MDSE_NM");
        sb.append(" FROM");
        sb.append("    CPO_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.SER_NUM IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Serial Number";
        whereArray0[IDX_1] = "SER_NUM";
        if (hasValue(scrnMsg.xxSerNumSrchTxt.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxSerNumSrchTxt.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Item Code";
        whereArray1[IDX_1] = "MDSE_CD";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Item Name";
        whereArray2[IDX_1] = "MDSE_NM";
        whereArray2[IDX_2] = null;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Serial Number";
        columnArray0[IDX_1] = "SER_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Item Code";
        columnArray1[IDX_1] = "MDSE_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Item Name";
        columnArray2[IDX_1] = "MDSE_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SER_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For RMA Line Status
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For RMA Line Status
     */
    public static Object[] getParamForRMALineStatus(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Line Status Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DOS.GLBL_CMPY_CD");
        sb.append("   ,DOS.EZCANCELFLAG");
        sb.append("   ,RLDS.RTRN_LINE_DPLY_STS_CD");
        sb.append("   ,RLDS.RTRN_LINE_DPLY_STS_DESC_TXT");
        sb.append(" FROM");
        sb.append("    DS_ORD_STS_V DOS");
        sb.append("   ,RTRN_LINE_DPLY_STS RLDS");
        sb.append(" WHERE");
        sb.append("        DOS.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DOS.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DOS.DS_CPO_RTRN_LINE_NUM IS NOT NULL");
        sb.append("    AND DOS.ORD_LINE_STS_NM NOT IN ('").append(CANCELLED).append("','").append(CLOSED).append("')");
        sb.append("    AND DOS.EZCANCELFLAG = '0'");
        sb.append("    AND RLDS.GLBL_CMPY_CD = DOS.GLBL_CMPY_CD");
        sb.append("    AND RLDS.RTRN_LINE_DPLY_STS_DESC_TXT = DOS.ORD_LINE_STS_NM");
        sb.append("    AND RLDS.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Line Status";
        whereArray0[IDX_1] = "RTRN_LINE_DPLY_STS_DESC_TXT";
        if (hasValue(scrnMsg.xxLineStsSrchTxt_R.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxLineStsSrchTxt_R.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Line Status";
        columnArray0[IDX_1] = "RTRN_LINE_DPLY_STS_DESC_TXT";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTRN_LINE_DPLY_STS_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For RMA Ordered Item
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For RMA Ordered Item
     */
    public static Object[] getParamForRMAOrderedItem(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Ordered Item Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.MDSE_CD");
        sb.append("   ,DCV.MDSE_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_RTRN_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Item Code";
        whereArray0[IDX_1] = "MDSE_CD";
        if (hasValue(scrnMsg.xxOrdItemSrchTxt_R.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxOrdItemSrchTxt_R.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Item Name";
        whereArray1[IDX_1] = "MDSE_NM";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Item Code";
        columnArray0[IDX_1] = "MDSE_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Item Name";
        columnArray1[IDX_1] = "MDSE_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "MDSE_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For RMA Return Reason
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For RMA Return Reason
     */
    public static Object[] getParamForRMAReturnReason(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Return Reason Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.RTRN_RSN_CD");
        sb.append("   ,RR.RTRN_RSN_DESC_TXT");
        sb.append(" FROM");
        sb.append("    DS_CPO_RTRN_DTL_V DCV");
        sb.append("   ,RTRN_RSN RR");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.GLBL_CMPY_CD = RR.GLBL_CMPY_CD");
        sb.append("    AND DCV.RTRN_RSN_CD = RR.RTRN_RSN_CD");
        sb.append("    AND RR.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Return Reason Code";
        whereArray0[IDX_1] = "RTRN_RSN_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Return Reason Name";
        whereArray1[IDX_1] = "RTRN_RSN_DESC_TXT";
        if (hasValue(scrnMsg.xxRtrnRsnSrchTxt_R.getValue())) {
            whereArray1[IDX_2] = scrnMsg.xxRtrnRsnSrchTxt_R.getValue();
        } else {
            whereArray1[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Return Reason Code";
        columnArray0[IDX_1] = "RTRN_RSN_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Return Reason Name";
        columnArray1[IDX_1] = "RTRN_RSN_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTRN_RSN_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For RMA Warehouse
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For RMA Warehouse
     */
    public static Object[] getParamForRMAWarehouse(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Warehouse Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.RTL_WH_CD");
        sb.append("   ,DCV.RTL_WH_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_RTRN_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.RTL_WH_CD IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "WH Code";
        whereArray0[IDX_1] = "RTL_WH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "WH Name";
        whereArray1[IDX_1] = "RTL_WH_NM";
        if (hasValue(scrnMsg.xxRtlWhSrchTxt_R.getValue())) {
            whereArray1[IDX_2] = scrnMsg.xxRtlWhSrchTxt_R.getValue();
        } else {
            whereArray1[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "WH Code";
        columnArray0[IDX_1] = "RTL_WH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "WH Name";
        columnArray1[IDX_1] = "RTL_WH_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTL_WH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For RMA Sub Warehouse
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For RMA Sub Warehouse
     */
    public static Object[] getParamForRMASubWarehouse(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sub Warehouse Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.RTL_SWH_CD");
        sb.append("   ,DCV.RTL_SWH_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_RTRN_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.RTL_SWH_CD IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "SWH Code";
        whereArray0[IDX_1] = "RTL_SWH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "SWH Name";
        whereArray1[IDX_1] = "RTL_SWH_NM";
        if (hasValue(scrnMsg.xxRtlSwhSrchTxt_R.getValue())) {
            whereArray1[IDX_2] = scrnMsg.xxRtlSwhSrchTxt_R.getValue();
        } else {
            whereArray1[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "SWH Code";
        columnArray0[IDX_1] = "RTL_SWH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "SWH Name";
        columnArray1[IDX_1] = "RTL_SWH_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTL_SWH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For RMA Line Source Reference Number
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For RMA Line Source Reference Number
     */
    public static Object[] getParamForRMALineSourceRef(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Line Source Reference# Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.LINE_SRC_REF_NUM");
        sb.append(" FROM");
        sb.append("    DS_CPO_RTRN_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.LINE_SRC_REF_NUM IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Reference#";
        whereArray0[IDX_1] = "LINE_SRC_REF_NUM";
        if (hasValue(scrnMsg.xxOrdSrcRefNumSrchTxt_R.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxOrdSrcRefNumSrchTxt_R.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Reference#";
        columnArray0[IDX_1] = "LINE_SRC_REF_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "LINE_SRC_REF_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1890 For RMA Serial Number
     * @param scrnMsg NWAL1890BMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Param NWAL1130 For RMA Serial Number
     */
    public static Object[] getParamForRMASerialNumber(NWAL1890BMsg scrnMsg, String glblCmpyCd, String cpoOrdNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Serial Number Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    DCV.GLBL_CMPY_CD");
        sb.append("   ,DCV.EZCANCELFLAG");
        sb.append("   ,DCV.SER_NUM");
        sb.append("   ,DCV.MDSE_CD");
        sb.append("   ,DCV.MDSE_NM");
        sb.append(" FROM");
        sb.append("    DS_CPO_RTRN_DTL_V DCV");
        sb.append(" WHERE");
        sb.append("        DCV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("    AND DCV.CPO_ORD_NUM = '").append(cpoOrdNum).append("'");
        sb.append("    AND DCV.EZCANCELFLAG = '0'");
        sb.append("    AND DCV.SER_NUM IS NOT NULL");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Serial Number";
        whereArray0[IDX_1] = "SER_NUM";
        if (hasValue(scrnMsg.xxSerNumSrchTxt_R.getValue())) {
            whereArray0[IDX_2] = scrnMsg.xxSerNumSrchTxt_R.getValue();
        } else {
            whereArray0[IDX_2] = "%"; // 2017/08/24 mod
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Item Code";
        whereArray1[IDX_1] = "MDSE_CD";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Item Name";
        whereArray2[IDX_1] = "MDSE_NM";
        whereArray2[IDX_2] = null;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Serial Number";
        columnArray0[IDX_1] = "SER_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Item Code";
        columnArray1[IDX_1] = "MDSE_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Item Name";
        columnArray2[IDX_1] = "MDSE_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SER_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }
}
