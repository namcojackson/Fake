/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1390.common;

import static business.servlet.NSAL1390.constant.NSAL1390Constant.*;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_APPLY_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_APPLY_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_APPROVE_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_APPROVE_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_CLEAR_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_CLEAR_EVENT_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_CLEAR_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_DELETE_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_DELETE_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_DOWNLOAD_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_DOWNLOAD_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_REJECT_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_REJECT_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_RESET_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_RESET_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_RETURN_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_RETURN_EVENT_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_RETURN_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_SAVE_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_SAVE_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_SUBMIT_BTN_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_SUBMIT_EVENT_NM;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_CMN_SUBMIT_LABEL;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_DELETE;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_OPEN_BRANCH_LINE;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.BTN_OPEN_REGION_LINE;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.MAX_ROW;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.PERCENT;
import static business.servlet.NSAL1390.constant.NSAL1390Constant.SCREEN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1390.NSAL1390BMsg;
import business.servlet.NSAL1390.NSAL1390_ABMsg;
import business.servlet.NSAL1390.NSAL1390_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NSAL1390CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 *</pre>
 */
public class NSAL1390CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE_BTN_NM, "", BTN_CMN_SAVE_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT_BTN_NM, BTN_CMN_SUBMIT_EVENT_NM, BTN_CMN_SUBMIT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY_BTN_NM, "", BTN_CMN_APPLY_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE_BTN_NM, "", BTN_CMN_APPROVE_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT_BTN_NM, "", BTN_CMN_REJECT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD_BTN_NM, "", BTN_CMN_DOWNLOAD_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE_BTN_NM, "", BTN_CMN_DELETE_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR_BTN_NM, BTN_CMN_CLEAR_EVENT_NM, BTN_CMN_CLEAR_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RESET_BTN_NM, "", BTN_CMN_RESET_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN_BTN_NM, BTN_CMN_RETURN_EVENT_NM, BTN_CMN_RETURN_LABEL, 0, null);
    }

    /**
     * initialize
     * @param handler EZDCommonHandler
     * @param scrnMsg scrnMsg
     */
    public static void initialize(EZDCommonHandler handler, NSAL1390BMsg scrnMsg) {

        initCmnBtnProp(handler);
        screenControlByFuncId(handler, scrnMsg);
        controlInitField(handler, scrnMsg, true);
        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1390BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL1390BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NSAL1390). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (funcIdList.contains(FUNC_ID_T020)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT_BTN_NM, true);
        }
        handler.setButtonEnabled(BTN_CMN_CLEAR_BTN_NM, true);
        handler.setButtonEnabled(BTN_CMN_RETURN_BTN_NM, true);
    }

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * control All Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     * @param isActive boolean
     */
    public static void controlInitField(EZDCommonHandler scrnAppli, NSAL1390BMsg scrnMsg, boolean isActive) {

        // button
        scrnAppli.setButtonEnabled(BTN_ADD, true);
        scrnAppli.setButtonEnabled(BTN_DELETE, false);

        // field
        scrnMsg.svcRgPk_LK.setInputProtected(false);
        scrnMsg.svcRgPk_LK.setValue(BigDecimal.ONE);
        scrnMsg.svcRgDescTxt_H.setInputProtected(true);
        scrnMsg.svcContrBrCd_LK.setInputProtected(false);
        scrnMsg.svcContrBrCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.svcContrBrDescTxt_H.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1390_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxChkBox_A1.setInputProtected(!isActive);
            abMsg.lineBizTpCd_A.setInputProtected(!isActive);
            abMsg.svcRgPk_A.setInputProtected(!isActive);
            abMsg.svcRgDescTxt_A.setInputProtected(true);
            abMsg.svcContrBrCd_A.setInputProtected(!isActive);
            abMsg.svcContrBrDescTxt_A.setInputProtected(true);
            abMsg.xxChkBox_A2.setInputProtected(!isActive);
            abMsg.xxChkBox_A3.setInputProtected(!isActive);
            abMsg.xxChkBox_A4.setInputProtected(!isActive);
            scrnAppli.setButtonEnabled(BTN_OPEN_REGION_LINE, i, isActive);
            scrnAppli.setButtonEnabled(BTN_OPEN_BRANCH_LINE, i, isActive);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NSAL1390BMsg
     * @param scrnAMsgAry NSAL1390_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NSAL1390BMsg scrnMsg, NSAL1390_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, 1);
    }

    /**
     * control All Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0490BMsg
     */
    public static void controlField(EZDCommonHandler scrnAppli, NSAL1390BMsg scrnMsg) {

        // button
        if (MAX_ROW == scrnMsg.xxPageShowOfNum.getValueInt()) {
            scrnAppli.setButtonEnabled(BTN_ADD, false);
        } else {
            scrnAppli.setButtonEnabled(BTN_ADD, true);
        }
        if (0 == scrnMsg.A.getValidCount()) {
            scrnAppli.setButtonEnabled(BTN_DELETE, false);
        } else {
            scrnAppli.setButtonEnabled(BTN_DELETE, true);
        }
        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1390_ABMsg abMsg = scrnMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(abMsg.ezUpTime_A)) {
                abMsg.lineBizTpCd_A.setInputProtected(true);
                abMsg.svcRgPk_A.setInputProtected(true);
                abMsg.svcContrBrCd_A.setInputProtected(true);
                scrnAppli.setButtonEnabled(BTN_OPEN_REGION_LINE, i, false);
                scrnAppli.setButtonEnabled(BTN_OPEN_BRANCH_LINE, i, false);
            } else {
                abMsg.lineBizTpCd_A.setInputProtected(false);
                abMsg.svcRgPk_A.setInputProtected(false);
                abMsg.svcContrBrCd_A.setInputProtected(false);
                scrnAppli.setButtonEnabled(BTN_OPEN_REGION_LINE, i, true);
                scrnAppli.setButtonEnabled(BTN_OPEN_BRANCH_LINE, i, true);
            }

        }
    }

    /**
     * Add CheckItem All Detail
     * @param scrnMsg NSAL1390BMsg
     */
    public static void addCheckDetailItem(NSAL1390BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1390_ABMsg aBMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_A1);
            scrnMsg.addCheckItem(aBMsg.lineBizTpCd_A);
            scrnMsg.addCheckItem(aBMsg.svcRgPk_A);
            scrnMsg.addCheckItem(aBMsg.svcContrBrCd_A);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_A2);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_A3);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_A4);
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * Get Param NWAL1130 For Region
     * @param scrnMsg NSAL1390BMsg
     * @param svcRgPk BigDecimal
     * @return Param NWAL1130 For Region
     */
    public static Object[] getParamNWAL1130ForRegion(NSAL1390BMsg scrnMsg, BigDecimal svcRgPk) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Region Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("    SR.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,SR.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,SR.SVC_RG_PK            AS SVC_RG_PK");
        sb.append("   ,SR.SVC_RG_DESC_TXT      AS SVC_RG_DESC_TXT");
        sb.append(" FROM");
        sb.append("    SVC_RG SR ");
        sb.append(" WHERE");
        sb.append("        SR.SVC_RG_ACTV_FLG  = 'Y'");
        sb.append("    AND SR.EFF_FROM_DT   < '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (SR.EFF_THRU_DT IS NULL OR SR.EFF_THRU_DT   > '").append(scrnMsg.slsDt.getValue()).append("')");
        sb.append("    AND SR.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND SR.EZCANCELFLAG = '0'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(5);
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Region";
        whereArray0[1] = "SVC_RG_PK";

        if (ZYPCommonFunc.hasValue(svcRgPk)) {
            whereArray0[2] = String.valueOf(svcRgPk);
        } else {
            whereArray0[2] = PERCENT;
        }
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Region Name";
        whereArray1[1] = "SVC_RG_DESC_TXT";
        whereArray1[2] = "";
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(5);
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Region ";
        columnArray0[1] = "SVC_RG_PK";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Region Name";
        columnArray1[1] = "SVC_RG_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(5);
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "SVC_RG_DESC_TXT";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Branch
     * @param scrnMsg NSAL1390BMsg
     * @param svcContrBrCd String
     * @return Param NWAL1130 For Branch
     */
    public static Object[] getParamNWAL1130ForBranch(NSAL1390BMsg scrnMsg, String svcContrBrCd) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Branch Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("    SCB.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,SCB.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,SCB.SVC_CONTR_BR_CD            AS SVC_CONTR_BR_CD");
        sb.append("   ,SCB.SVC_CONTR_BR_DESC_TXT      AS SVC_CONTR_BR_DESC_TXT");
        sb.append(" FROM");
        sb.append("    SVC_CONTR_BR SCB ");
        sb.append(" WHERE");
        sb.append("        SCB.SVC_CONTR_BR_ACTV_FLG  = 'Y'");
        sb.append("    AND SCB.EFF_FROM_DT   < '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (SCB.EFF_THRU_DT IS NULL OR SCB.EFF_THRU_DT   > '").append(scrnMsg.slsDt.getValue()).append("')");
        sb.append("    AND SCB.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND SCB.EZCANCELFLAG = '0'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(5);
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Branch";
        whereArray0[1] = "SVC_CONTR_BR_CD";

        if (ZYPCommonFunc.hasValue(svcContrBrCd)) {
            whereArray0[2] = svcContrBrCd;
        } else {
            whereArray0[2] = PERCENT;
        }
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Branch Name";
        whereArray1[1] = "SVC_CONTR_BR_DESC_TXT";
        whereArray1[2] = "";
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(5);
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Branch ";
        columnArray0[1] = "SVC_CONTR_BR_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Branch Name";
        columnArray1[1] = "SVC_CONTR_BR_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(5);
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "SVC_CONTR_BR_DESC_TXT";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[6] = scrnMsg.Y;

        return params;
    }

}
