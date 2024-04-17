/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0950.common;

import static business.servlet.NSAL0950.constant.NSAL0950Constant.ATTR_NUM_CLMN_LIST;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.ATTR_NUM_WHERE_LIST;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.AUTH_UPDATE;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_APPLY;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_APPROVE;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_CLEAR;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_DELETE;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_DOWNROAD;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_REJECT;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_RESET;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_SAVE;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.BUSINESS_ID;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.CLIST_DSP_OBJ_NM;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.CLIST_LINK_FLG;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.CLIST_OBJ_ID;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.CLIST_OBJ_LENGTH;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.LENGTH_7;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.NSAM0457E;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.SCREEN_ID;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.VLD_CMPT_DESC_TXT_LENGTH;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.VLD_CMPT_TXT_LENGTH;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.WLIST_DSP_OBJ_NM;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.WLIST_OBJ_ID;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.WLIST_OBJ_VALUE;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.WLIST_WHERE_FLG;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBDateItem;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0950.NSAL0950BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contract Validation Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/06/28   Hitachi         Y.Tsuchimoto    Update          QC#9691
 *</pre>
 */
public class NSAL0950CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0950BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0950BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);

        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0950BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0950BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        handler.setButtonEnabled("Search", true);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonEnabled("AddLine", true);
            handler.setButtonEnabled("DeleteLine", true);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonEnabled("AddLine", false);
            handler.setButtonEnabled("DeleteLine", false);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0950BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0950BMsg scrnMsg) {
        controlScreenDetailFieldsInit(handler, scrnMsg);
        controlScreenTableFields(handler, scrnMsg);
    }
    /**
     * controlScreenDetailFieldsInit
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0950BMsg
     * @param newFlag boolean
     */
    private static final void controlScreenDetailFieldsInit(EZDCommonHandler handler, NSAL0950BMsg scrnMsg) {
        scrnMsg.dsContrVldPk_D.setInputProtected(true);
        scrnMsg.dsContrVldCatgCd_DS.setInputProtected(true);
        scrnMsg.dsContrVldNm_D.setInputProtected(true);
        scrnMsg.dsContrVldDescTxt_D.setInputProtected(true);
        scrnMsg.vldLvlContrFlg_D.setInputProtected(true);
        scrnMsg.vldLvlMachFlg_D.setInputProtected(true);
        scrnMsg.effFromDt_D.setInputProtected(true);
        scrnMsg.effToDt_D.setInputProtected(true);
        scrnMsg.vldCmptTxt_DL.setInputProtected(true);
        scrnMsg.vldCmptTxt_D.setInputProtected(true);
        scrnMsg.primVldFlg_D.setInputProtected(true);
        scrnMsg.ovrdVldRsltAvalFlg_D.setInputProtected(true);
        scrnMsg.vldRegFlg_D.setInputProtected(true);
        scrnMsg.vldFleetFlg_D.setInputProtected(true);
        scrnMsg.vldAggrFlg_D.setInputProtected(true);
        scrnMsg.vldWtyFlg_D.setInputProtected(true);
    }
    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0950BMsg
     * @param newFlag boolean
     */
    public static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0950BMsg scrnMsg, boolean newFlag) {
        if (hasUpdateFuncId()) {
            if (newFlag) {
                scrnMsg.dsContrVldPk_D.setInputProtected(false);
                scrnMsg.dsContrVldCatgCd_DS.setInputProtected(false);
                scrnMsg.dsContrVldNm_D.setInputProtected(false);
                scrnMsg.dsContrVldDescTxt_D.setInputProtected(false);
                scrnMsg.vldLvlContrFlg_D.setInputProtected(false);
                scrnMsg.vldLvlMachFlg_D.setInputProtected(false);
                scrnMsg.effFromDt_D.setInputProtected(false);
                scrnMsg.effToDt_D.setInputProtected(false);
                scrnMsg.vldCmptTxt_DL.setInputProtected(false);
                scrnMsg.vldCmptTxt_D.setInputProtected(false);
                scrnMsg.primVldFlg_D.setInputProtected(false);
                scrnMsg.ovrdVldRsltAvalFlg_D.setInputProtected(false);
                scrnMsg.vldRegFlg_D.setInputProtected(false);
                scrnMsg.vldFleetFlg_D.setInputProtected(false);
                scrnMsg.vldAggrFlg_D.setInputProtected(false);
                scrnMsg.vldWtyFlg_D.setInputProtected(false);
            } else {
                scrnMsg.dsContrVldPk_D.setInputProtected(false);
                scrnMsg.dsContrVldCatgCd_DS.setInputProtected(true);
                scrnMsg.dsContrVldNm_D.setInputProtected(true);
                scrnMsg.dsContrVldDescTxt_D.setInputProtected(false);
                scrnMsg.vldLvlContrFlg_D.setInputProtected(false);
                scrnMsg.vldLvlMachFlg_D.setInputProtected(false);
                scrnMsg.effFromDt_D.setInputProtected(false);
                scrnMsg.effToDt_D.setInputProtected(false);
                // START 2016/06/28 [QC#9691, MOD]
                scrnMsg.vldCmptTxt_DL.setInputProtected(false);
                scrnMsg.vldCmptTxt_D.setInputProtected(false);
                // END   2016/06/28 [QC#9691, MOD]
                scrnMsg.primVldFlg_D.setInputProtected(false);
                scrnMsg.ovrdVldRsltAvalFlg_D.setInputProtected(false);
                scrnMsg.vldRegFlg_D.setInputProtected(false);
                scrnMsg.vldFleetFlg_D.setInputProtected(false);
                scrnMsg.vldAggrFlg_D.setInputProtected(false);
                scrnMsg.vldWtyFlg_D.setInputProtected(false);
            }
        } else {
            scrnMsg.dsContrVldPk_D.setInputProtected(true);
            scrnMsg.dsContrVldCatgCd_DS.setInputProtected(true);
            scrnMsg.dsContrVldNm_D.setInputProtected(true);
            scrnMsg.dsContrVldDescTxt_D.setInputProtected(true);
            scrnMsg.vldLvlContrFlg_D.setInputProtected(true);
            scrnMsg.vldLvlMachFlg_D.setInputProtected(true);
            scrnMsg.effFromDt_D.setInputProtected(true);
            scrnMsg.effToDt_D.setInputProtected(true);
            scrnMsg.vldCmptTxt_DL.setInputProtected(true);
            scrnMsg.vldCmptTxt_D.setInputProtected(true);
            scrnMsg.primVldFlg_D.setInputProtected(true);
            scrnMsg.ovrdVldRsltAvalFlg_D.setInputProtected(true);
            scrnMsg.vldRegFlg_D.setInputProtected(true);
            scrnMsg.vldFleetFlg_D.setInputProtected(true);
            scrnMsg.vldAggrFlg_D.setInputProtected(true);
            scrnMsg.vldWtyFlg_D.setInputProtected(true);
        }
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Interface Maintenance (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSAL0950BMsg
     */
    private static final void controlScreenTableFields(EZDCommonHandler handler, NSAL0950BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrVldPk_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrVldCatgCd_AS.setInputProtected(true);
            scrnMsg.A.no(i).dsContrVldNm_A.setInputProtected(true);
            scrnMsg.A.no(i).vldLvlContrFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).vldLvlMachFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).vldAggrFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).vldFleetFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).vldRegFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).vldWtyFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).primVldFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).ovrdVldRsltAvalFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).effToDt_A.setInputProtected(true);
        }
    }

    /**
     * setFromToDateCheck
     * @param scrnMsg NSAL0950BMsg
     */
    public static void setFromToDateCheck(NSAL0950BMsg scrnMsg) {
        EZDBDateItem effFromDt = scrnMsg.effFromDt_D;
        EZDBDateItem effToDt = scrnMsg.effToDt_D;
        if (hasValue(effFromDt) && hasValue(effToDt)) {
            int compareRes = ZYPDateUtil.compare(effFromDt.getValue(), effToDt.getValue());
            if (compareRes == 1) {

                String[] msgParam = {effToDt.getNameForMessage(), effFromDt.getNameForMessage() };

                effFromDt.setErrorInfo(1, NSAM0457E, msgParam);
                scrnMsg.addCheckItem(effFromDt);

                effToDt.setErrorInfo(1, NSAM0457E, msgParam);
                scrnMsg.addCheckItem(effToDt);

                scrnMsg.putErrorScreen();
            }
        }
    }

    /**
     * setCommonPopUpParam
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSAL0950BMsg
     * @return Object[]
     */
    public static Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0950BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.X);
        Object[] params = new Object[LENGTH_7];

        int i = 0;
        // Return value suffix
        params[i++] = "";
        // Window title
        params[i++] = "Validation Component Search";
        // Table SQL
        params[i++] = getSqlStr(scrnMsg);
        // Where List
        params[i++] = getWhereList(scrnMsg);
        // Column List
        List<Object[]> columnList = getColumnList();
        params[i++] = columnList;
        // Sort Condition List
        params[i++] = getSortConditionList();
        // outPut List
        params[i++] = scrnMsg.X;

        return params;
    }

    private static String getSqlStr(NSAL0950BMsg scrnMsg) {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("      DCVC.GLBL_CMPY_CD");
        sql.append("     ,DCVC.EZCANCELFLAG");
        sql.append("     ,DCVC.DS_CONTR_VLD_CMPT_PK");
        sql.append("     ,DCVC.VLD_CMPT_TXT");
        sql.append("     ,DCVC.VLD_CMPT_DESC_TXT");
        sql.append(" FROM");
        sql.append("      DS_CONTR_VLD_CMPT      DCVC");
        sql.append(" WHERE");
        sql.append("         DCVC.EZCANCELFLAG        = '0'");
        sql.append("     AND DCVC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd_S.getValue()).append("' ");

        return sql.toString();
    }

    private static List<Object[]> getWhereList(NSAL0950BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Component Name";
        h0[WLIST_OBJ_ID] = "VLD_CMPT_TXT";
        h0[WLIST_OBJ_VALUE] = "";
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Component Desc";
        h1[WLIST_OBJ_ID] = "VLD_CMPT_DESC_TXT";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }

    private static List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Component Name";
        c0[CLIST_OBJ_ID] = "VLD_CMPT_TXT";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(VLD_CMPT_TXT_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Component Desc";
        c1[CLIST_OBJ_ID] = "VLD_CMPT_DESC_TXT";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(VLD_CMPT_DESC_TXT_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }

    private static List<Object[]> getSortConditionList() {
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "VLD_CMPT_TXT";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        sortConditionArray = new Object[2];
        sortConditionArray[0] = "VLD_CMPT_DESC_TXT";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        return sortConditionList;
    }
}
