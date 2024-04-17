/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920.common;

import static business.servlet.NSAL0920.constant.NSAL0920Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0920.NSAL0920BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Contract Billing Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/01/06   Hitachi         T.Tomita        Update          QC#1029
 * 2016/03/30   Hitachi         T.Aoyagi        Update          QC#1467
 * 2016/04/11   Hitachi         T.Kanasaka      Update          QC#6657
 * 2016/10/18   Hitachi         N.Arai          Update          QC#15216
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 *</pre>
 */
public class NSAL0920CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0920BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0920BMsg scrnMsg) {
        initControlCommonButton(handler);
        controlScreenFields(handler, scrnMsg);
        setBackgroundColor(handler, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsContrNum);
    }

    /**
     * <pre>
     * The search state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0920BMsg
     */
    public static final void searchControlScreen(EZDCommonHandler handler, NSAL0920BMsg scrnMsg) {
        controlScreenFields(handler, scrnMsg);
        setBackgroundColor(handler, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsContrNum);
    }

    /**
     * initControlCommonButton
     * @param handler EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0920BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0920BMsg scrnMsg) {
        scrnMsg.svcContrBrDescTxt_H2.setInputProtected(true);
        // START 2017/01/23 K.Kitachi [QC#17219, ADD]
        scrnMsg.xxAllPsnNm_H2.setInputProtected(true);
        // END 2017/01/23 K.Kitachi [QC#17219, ADD]
        controlScreenDetailFields(handler, scrnMsg);
    }

    // START 04/11/2016 T.Kanasaka [QC#6657, MOD]
    /**
     * Control Detail Field
     * @param handler
     * @param scrnMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0920BMsg scrnMsg) {

        BigDecimal preSvcContrBllgPk = BigDecimal.ZERO;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).svcContrBllgPk_A.setInputProtected(true);
            scrnMsg.A.no(i).ovrdNextBllgDt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcContrBrDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrCatgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsContrCtrlStsNm_A.setInputProtected(true);
            // START 03/30/2016 T.Aoyagi [QC#1467, MOD]
            if (hasValue(scrnMsg.A.no(i).serNum_A) && DS_CONTR_CATG.FLEET.equals(scrnMsg.A.no(i).dsContrCatgCd_A.getValue())) {
                scrnMsg.A.no(i).serNum_A.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            }
            // END 03/30/2016 T.Aoyagi [QC#1467, MOD]
            scrnMsg.A.no(i).curLocNum_A.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrBllgFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).mtrBllgThruDt_A.setInputProtected(true);
            scrnMsg.A.no(i).xsMtrFromCopyQty_A.setInputProtected(true);
            scrnMsg.A.no(i).xsMtrAmtRate_A.setInputProtected(true);
            scrnMsg.A.no(i).readMtrCnt_AS.setInputProtected(true);
            scrnMsg.A.no(i).readMtrCnt_AE.setInputProtected(true);
            scrnMsg.A.no(i).mtrChrgDealAmt_A.setInputProtected(true);
            // START 03/30/2016 T.Aoyagi [QC#1467, MOD]
            if (NTFY_YES.equals(scrnMsg.A.no(i).xxYesNoNm_A.getValue())) {
                scrnMsg.A.no(i).xxYesNoNm_A.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).xxYesNoNm_A.setInputProtected(true);
            }
            // END 03/30/2016 T.Aoyagi [QC#1467, MOD]

            if (hasValue(scrnMsg.A.no(i).svcContrBllgPk_A) && preSvcContrBllgPk.compareTo(scrnMsg.A.no(i).svcContrBllgPk_A.getValue()) == 0) {
                setVisibility(scrnMsg, i);
            }
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xsMtrAmtRate_A)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xsMtrAmtRate_A, scrnMsg.A.no(i).xsMtrAmtRate_A.getValue().setScale(1, RoundingMode.HALF_UP));
//            }
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xsMtrChrgDealAmt_A)) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xsMtrChrgDealAmt_A, scrnMsg.A.no(i).xsMtrChrgDealAmt_A.getValue().setScale(1, RoundingMode.HALF_UP));
//            }
            preSvcContrBllgPk = scrnMsg.A.no(i).svcContrBllgPk_A.getValue();
        }
    }

    private static void setVisibility(NSAL0920BMsg scrnMsg, int idx) {
        setVisibilityItem(scrnMsg, "ovrdNextBllgDt_A#" + idx);
        setVisibilityItem(scrnMsg, "svcContrBrDescTxt_A#" + idx);
        setVisibilityItem(scrnMsg, "dsContrNum_A#" + idx);
        setVisibilityItem(scrnMsg, "dsContrCatgDescTxt_A#" + idx);
        setVisibilityItem(scrnMsg, "dsContrCtrlStsNm_A#" + idx);
        // START 03/30/2016 T.Aoyagi [QC#1467, MOD]
//        setVisibilityItem(scrnMsg, "serNum_A#" + idx);
        // END 03/30/2016 T.Aoyagi [QC#1467, MOD]
        setVisibilityItem(scrnMsg, "locNm_A#" + idx);
        setVisibilityItem(scrnMsg, "mtrLbDescTxt_A#" + idx);
        setVisibilityItem(scrnMsg, "mtrBllgFromDt_A#" + idx);
        setVisibilityItem(scrnMsg, "mtrBllgThruDt_A#" + idx);
        setVisibilityItem(scrnMsg, "readMtrCnt_AS#" + idx);
        setVisibilityItem(scrnMsg, "readMtrCnt_AE#" + idx);
        setVisibilityItem(scrnMsg, "mtrChrgDealAmt_A#" + idx);

        scrnMsg.A.no(idx).serNum_A.clear();
        scrnMsg.A.no(idx).xxYesNoNm_A.clear();
    }
    // END 04/11/2016 T.Kanasaka [QC#6657, MOD]

    private static void setVisibilityItem(NSAL0920BMsg scrnMsg, String itemId) {
        EZDGUIAttribute xxItem = new EZDGUIAttribute(SCREEN_ID, itemId);
        xxItem.setVisibility(false);
        scrnMsg.addGUIAttribute(xxItem);
    }

    /**
     * clear Popup Parameter
     * @param scrnMsg NSAL0920BMsg
     */
    public static void clearPopupParameter(NSAL0920BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        // START 2016/01/06 T.Tomita [QC#1029, ADD]
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        // END 2016/01/06 T.Tomita [QC#1029, ADD]
    }

    /**
     * set Background Color
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0920BMsg
     */
    public static void setBackgroundColor(EZDCommonHandler scrnAppli, NSAL0920BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController("NSAL0920Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

// START 2016/10/18 N.Arai [QC#15216, MOD]
    /**
     * set Parameter for Branch(NWAL11300)
     * @param scrnMsg NSAL0920BMsg
     * return Object[]
     */
    public static Object[] setParamBranch(NSAL0920BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = OPENWIN_BRANCH;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("A.EZCANCELFLAG, ");
        sb.append("A.GLBL_CMPY_CD, ");
        sb.append("A.SVC_CONTR_BR_CD, ");
        sb.append("A.SVC_CONTR_BR_DESC_TXT ");
        sb.append("FROM ");
        sb.append("SVC_CONTR_BR A ");
        sb.append("WHERE ");
        sb.append("A.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND A.EZCANCELFLAG = '0' ");
        sb.append("AND A.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("AND NVL(A.EFF_THRU_DT, '").append(END_DT).append("') >= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("ORDER BY ");
        sb.append("A.SVC_CONTR_BR_CD ");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Branch Code";
        whereArray0[1] = "SVC_CONTR_BR_CD";
        whereArray0[2] = scrnMsg.svcContrBrCd_H3.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];

        whereArray1[0] = "Branch Name";
        whereArray1[1] = "SVC_CONTR_BR_DESC_TXT";
        whereArray1[2] = null;
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Branch Code";
        columnArray0[1] = "SVC_CONTR_BR_CD";
        columnArray0[2] = BigDecimal.valueOf(28);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Branch Name";
        columnArray1[1] = "SVC_CONTR_BR_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(60);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "SVC_CONTR_BR_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        scrnMsg.I.clear();

        params[6] = scrnMsg.I;
        
        return params;
    }
// END 2016/10/18 N.Arai [QC#15216, MOD]

    // START 2017/01/23 K.Kitachi [QC#17219, ADD]
    /**
     * set Parameter for Supervisor(NWAL11300)
     * @param scrnMsg NSAL0920BMsg return Object[]
     */
    public static Object[] setParamSupv(NSAL0920BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = OPENWIN_SUPV;

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCT");
        sb.append("      PSN.GLBL_CMPY_CD");
        sb.append("     ,PSN.EZCANCELFLAG");
        sb.append("     ,PSN.PSN_CD");
        sb.append("     ,TRIM(PSN.PSN_FIRST_NM || ' ' || PSN.PSN_LAST_NM) AS PSN_NM");
        sb.append(" FROM");
        sb.append("      S21_PSN           PSN");
        sb.append("     ,SVC_BR_RESRC_RELN BRR");
        sb.append(" WHERE");
        sb.append("         PSN.GLBL_CMPY_CD             = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("     AND PSN.EFF_FROM_DT             <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("     AND NVL(PSN.EFF_THRU_DT, '").append(END_DT).append("') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("     AND PSN.EZCANCELFLAG             = '0'");
        sb.append("     AND PSN.GLBL_CMPY_CD             = BRR.GLBL_CMPY_CD");
        sb.append("     AND PSN.PSN_CD                   = BRR.PSN_CD");
        sb.append("     AND BRR.SVC_ORG_FUNC_ROLE_TP_CD  = '").append(scrnMsg.svcOrgFuncRoleTpCd_VC.getValue()).append("'");
        sb.append("     AND BRR.EZCANCELFLAG             = '0'");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Resource#";
        whereArray0[1] = "PSN_CD";
        whereArray0[2] = scrnMsg.psnCd_H3.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];

        whereArray1[0] = "Resource Name";
        whereArray1[1] = "PSN_NM";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Resource#";
        columnArray0[1] = "PSN_CD";
        columnArray0[2] = BigDecimal.valueOf(8);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Resource Name";
        columnArray1[1] = "PSN_NM";
        columnArray1[2] = BigDecimal.valueOf(61);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "PSN_CD";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);

        params[5] = sortConditionList;

        scrnMsg.I.clear();

        params[6] = scrnMsg.I;

        return params;
    }
    // END 2017/01/23 K.Kitachi [QC#17219, ADD]
}
