/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0160.common;

import static business.servlet.NFDL0160.constant.NFDL0160Constant.ACTIVE;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BIZ_ID;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_APL;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_APR;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_CLR;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_DEL;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_DWL;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_RJT;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_RST;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_RTN;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_SAV;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_CMN_SUB;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_DEL_ROW;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_INS_ROW;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_OPENWIN_AR;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_OPENWIN_CLT;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.BTN_OPENWIN_NEXTLVL;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_0;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_1;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_10;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_15;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_2;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_20;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_28;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_3;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_30;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_4;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_5;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_50;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_6;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_61;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.IDX_7;
import static business.servlet.NFDL0160.constant.NFDL0160Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFDL0160.NFDL0160BMsg;
import business.servlet.NFDL0160.NFDL0160_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/03/29   Fujitsu         C.Naito         Update          QC#6178
 * 2016/07/19   Hitachi         T.Tsuchida      Update          QC#12055
 * 2016/08/10   Hitachi         K.Kojima        Update          QC#13129
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2017/09/06   Hitachi         T.Tsuchida      Update          QC#20970
 * 2017/09/13   Hitachi         T.Tsuchida      Update          QC#21113
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 * 2019/02/06   Hitachi         H.Umeda         Update          QC#30043
 *</pre>
 */
public class NFDL0160CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        // START 2017/09/06 T.Tsuchida [QC#20970,MOD]
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        // END 2017/09/06 T.Tsuchida [QC#20970,MOD]
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color, change the argument groupRows every alternate line groups.
     * @param scrnMsg NFDL0160BMsg
     * @param scrnAMsgAry NFDL0160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NFDL0160BMsg scrnMsg, NFDL0160_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color, change the argument groupRows every alternate line groups.
     * @param scrnMsg NFDL0160BMsg
     * @param scrnAMsgAry NFDL0160_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NFDL0160BMsg scrnMsg, NFDL0160_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Initial controlScreenFields
     * @param handler S21CommonHandler
     * @param scrnMsg NFDL0160BMsg
     */
    public static void initialControlScreenFields(S21CommonHandler handler, NFDL0160BMsg scrnMsg) {
        scrnMsg.setInputProtected(false);
        initCmnBtnProp(handler);
        //[QC#6178] UPDATE start
        // scrnMsg.cltPtfoStsFlg.setValue(ALL);
        scrnMsg.cltPtfoStsFlg.setValue(ACTIVE);
        //[QC#6178] UPDATE end
    }

    /**
     * controlScreenFields
     * @param handler S21CommonHandler
     * @param scrnMsg NFDL0160BMsg
     * @param eventName String
     */
    public static void controlScreenFields(EZDCommonHandler handler, NFDL0160BMsg scrnMsg, String eventName) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if ("Search".equals(eventName) || "CMN_Submit".equals(eventName)) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            }
            // START 2018/08/03 T.Ogura [QC#25899,ADD]
            scrnMsg.A.no(i).cltPtfoCd_A.setInputProtected(true);
            // END   2018/08/03 T.Ogura [QC#25899,ADD]
            // START 2016/08/09 K.Kojima [QC#13129,ADD]
            scrnMsg.A.no(i).cltPsnNm_A.setInputProtected(true);
            // END 2016/08/09 K.Kojima [QC#13129,ADD]
            scrnMsg.A.no(i).arAdjTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).cltCrAnlstEquipPsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).cltCrAnlstSvcPsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).cltCrAnlstSplyPsnNm_A.setInputProtected(true);
        }
        controlScreenFieldsByAuthority(handler, scrnMsg);
    }

    /**
     * inActiveForm error clear
     * @param scrnMsg NFDL0160BMsg
     */
    public static void inActiveFormErrorClear(NFDL0160BMsg scrnMsg) {
        // for inActiveForm error clear
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).arAdjTpDescTxt_A.clearErrorInfo();
            scrnMsg.A.no(i).cltCrAnlstEquipPsnNm_A.clearErrorInfo();
            scrnMsg.A.no(i).cltCrAnlstSvcPsnNm_A.clearErrorInfo();
            scrnMsg.A.no(i).cltCrAnlstSplyPsnNm_A.clearErrorInfo();
        }
    }

    /**
     * controlScreenFieldsByAuthority ScreenFields activity set by user authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NFDL0160BMsg
     */
    public static final void controlScreenFieldsByAuthority(EZDCommonHandler handler, NFDL0160BMsg scrnMsg) {

        if (!hasUpdateFuncId()) {
            // All fields inactive
            allFieldsInactive(handler, scrnMsg);
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }
        if (funcList.contains("NFDL0160T020")) {
            return true;
        }
        return false;
    }

    /**
     * allFieldsInactive
     * @param handler EZDCommonHandler
     * @param scrnMsg NFDL0160BMsg
     */
    public static final void allFieldsInactive(EZDCommonHandler handler, NFDL0160BMsg scrnMsg) {

        /** Common Button **/
        handler.setButtonEnabled(BTN_CMN_SUB[0], false);

        /** Screen field **/
        scrnMsg.A.setInputProtected(true);
        handler.setButtonEnabled(BTN_OPENWIN_CLT, false);
        handler.setButtonEnabled(BTN_OPENWIN_AR, false);
        handler.setButtonEnabled(BTN_OPENWIN_NEXTLVL, false);

        handler.setButtonEnabled(BTN_INS_ROW, false);
        handler.setButtonEnabled(BTN_DEL_ROW, false);

    }

    // *********************
    // for PopUp Parameter
    // *********************
    /**
     * Get Param NWAL1130 For OpenWinAR
     * @param scrnMsg NFDL0160BMsg
     * @return Param NWAL1130 For OpenWinAR
     */
    public static Object[] getParamNWAL1130ForOpenWinAR(NFDL0160BMsg scrnMsg) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "AR Write Off Activity Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("     ATP.AR_ADJ_TP_CD");
        sb.append("    ,ATP.AR_ADJ_TP_DESC_TXT");
        sb.append("    ,ATP.GLBL_CMPY_CD");
        sb.append("    ,ATP.EZCANCELFLAG");
        sb.append("    ,AR_ADJ_TP_SORT_NUM");
        sb.append(" FROM");
        sb.append("     AR_ADJ_TP ATP");
        sb.append(" WHERE");
        sb.append("     ATP.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        sb.append(" AND ATP.CLT_TRGT_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
        sb.append(" AND ATP.EZCANCELFLAG      = '0'");
        // START 2017/09/13 T.Tsuchida [QC#21113,MOD]
        sb.append(" AND ATP.AR_ADJ_CATG_CD    = '").append(AR_ADJ_CATG.ADJUSTMENT).append("'");
        // END 2017/09/13 T.Tsuchida [QC#21113,MOD]

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Adjustment Type Code";
        whereArray0[IDX_1] = "AR_ADJ_TP_CD";
        whereArray0[IDX_2] = scrnMsg.A.no(selectIndex).arAdjTpCd_A.getValue();
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Adjustment Type Name";
        whereArray1[IDX_1] = "AR_ADJ_TP_DESC_TXT";
        whereArray1[IDX_2] = scrnMsg.A.no(selectIndex).arAdjTpDescTxt_A.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Adjustment Type Code";
        columnArray0[IDX_1] = "AR_ADJ_TP_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Adjustment Type Name";
        columnArray1[IDX_1] = "AR_ADJ_TP_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "AR_ADJ_TP_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For OpenWinClt
     * @param scrnMsg NFDL0160BMsg
     * @return Param NWAL1130 For OpenWinClt
     */
    public static Object[] getParamNWAL1130ForOpenWinClt(NFDL0160BMsg scrnMsg) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Collector Popup";

        // START 2016/07/19 T.Tsuchida [QC#12055,MOD]
//[QC#6178] UPDATE start
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT");
//        sb.append("     PSN.PSN_CD");
//        sb.append("    ,PSN.PSN_LAST_NM || ',' || PSN_FIRST_NM AS PSN_LAST_FIRST_NM");
//        sb.append("    ,PSN.PSN_LAST_NM");
//        sb.append("    ,PSN.PSN_FIRST_NM");
//        sb.append("    ,PSN.WORK_TEL_NUM");
//        sb.append("    ,PSN.FAX_NUM");
//        sb.append("    ,PSN.GLBL_CMPY_CD");
//        sb.append("    ,PSN.EZCANCELFLAG");
//        sb.append(" FROM");
//        sb.append("     S21_PSN PSN");
//        sb.append(" WHERE");
//        sb.append("     PSN.GLBL_CMPY_CD   = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
//        sb.append(" AND PSN.EZCANCELFLAG   = '0'");
//        sb.append(" AND PSN.PSN_TP_CD      = '").append(PSN_TP.EMPLOYEE).append("'");

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("     CRA.AR_CR_APVL_USR_ID");
        // START 2018/03/29 J.Kim [QC#21733,MOD]
        // sb.append("    ,PSN.LAST_NM || ',' || FIRST_NM AS PSN_LAST_FIRST_NM");
        // START 2019/02/06 H.Umeda [QC#30043,MOD]
        // sb.append("    ,PSN.FIRST_NM || ',' || PSN.LAST_NM AS PSN_FIRST_LAST_NM");
        sb.append("    ,PSN.LAST_NM || ',' || FIRST_NM AS PSN_LAST_FIRST_NM");
        // END   2019/02/06 H.Umeda [QC#30043,MOD]
        // END 2018/03/29 J.Kim [QC#21733,MOD]
        sb.append("    ,PSN.LAST_NM");
        sb.append("    ,PSN.FIRST_NM");
        sb.append("    ,PSN.USR_NM");
        sb.append("    ,PSN.WRK_TEL_NUM");
        sb.append("    ,PSN.FAX_PHN_NUM");
        sb.append("    ,PSN.GLBL_CMPY_CD");
        sb.append("    ,PSN.EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("     AR_CR_RF_APVL_LIMIT CRA");
        sb.append("    ,AUTH_PSN PSN");
        sb.append("    ,S21_PSN SP");
        sb.append(" WHERE");
        sb.append("     CRA.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append(" AND CRA.EZCANCELFLAG = '0'");
        sb.append(" AND CRA.GLBL_CMPY_CD = PSN.GLBL_CMPY_CD");
        sb.append(" AND CRA.AR_CR_APVL_USR_ID = PSN.USR_NM");
        sb.append(" AND PSN.EZCANCELFLAG = '0'");
        sb.append(" AND CRA.GLBL_CMPY_CD = SP.GLBL_CMPY_CD");
        sb.append(" AND CRA.AR_CR_APVL_USR_ID = SP.PSN_CD");
        sb.append(" AND SP.EZCANCELFLAG = '0'");
//[QC#6178] UPDATE end

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Collector Code";
        whereArray0[IDX_1] = "USR_NM";
        whereArray0[IDX_2] = scrnMsg.A.no(selectIndex).cltPsnCd_A.getValue();
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Collector Last Name";
        whereArray1[IDX_1] = "LAST_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Collector First Name";
        whereArray2[IDX_1] = "FIRST_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Collector Code";
        columnArray0[IDX_1] = "USR_NM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        // START 2018/03/29 J.Kim [QC#21733,MOD]
        // columnArray1[IDX_0] = "Last,First Name";
        // columnArray1[IDX_1] = "PSN_LAST_FIRST_NM";
        // START 2019/02/06 H.Umeda [QC#30043,MOD]
        // columnArray1[IDX_0] = "First,Last Name";
        // columnArray1[IDX_1] = "PSN_FIRST_LAST_NM";
        columnArray1[IDX_0] = "Last,First Name";
        columnArray1[IDX_1] = "PSN_LAST_FIRST_NM";
        // END   2019/02/06 H.Umeda [QC#30043,MOD]
        // END 2018/03/298 J.Kim [QC#21733,MOD]
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Telephone Number";
        columnArray2[IDX_1] = "WRK_TEL_NUM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_15);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Fax Number";
        columnArray3[IDX_1] = "FAX_PHN_NUM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_15);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "LAST_NM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        // END 2016/07/19 T.Tsuchida [QC#12055,MOD]

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For OpenWinCR(Credit Analyst)
     * @param scrnMsg NFDL0160BMsg
     * @return Param NWAL1130 For OpenWinCR
     */
    public static Object[] getParamNWAL1130ForOpenWinCR(NFDL0160BMsg scrnMsg) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        if ("CR_Equip".equals(scrEventNm)) {
            params[IDX_1] = "Credit Analyst (Equipment)";
        } else if ("CR_Svc".equals(scrEventNm)) {
            params[IDX_1] = "Credit Analyst (Service)";
        } else if ("CR_Sply".equals(scrEventNm)) {
            params[IDX_1] = "Credit Analyst (Supplies)";
        }

        // START 2016/07/19 T.Tsuchida [QC#12055,MOD]
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("     CRA.AR_CR_APVL_USR_ID");
        sb.append("    ,PSN.LAST_NM || ',' || FIRST_NM AS PSN_LAST_FIRST_NM");
        sb.append("    ,PSN.LAST_NM");
        sb.append("    ,PSN.FIRST_NM");
        sb.append("    ,PSN.USR_NM");
        sb.append("    ,PSN.GLBL_CMPY_CD");
        sb.append("    ,PSN.EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("     AR_CR_RF_APVL_LIMIT CRA");
        sb.append("    ,AUTH_PSN PSN");
        sb.append("    ,S21_PSN SP");
        sb.append(" WHERE");
        sb.append("     CRA.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append(" AND CRA.EZCANCELFLAG = '0'");
        sb.append(" AND CRA.GLBL_CMPY_CD = PSN.GLBL_CMPY_CD");
        sb.append(" AND CRA.AR_CR_APVL_USR_ID = PSN.USR_NM");
        sb.append(" AND PSN.EZCANCELFLAG = '0'");
        sb.append(" AND CRA.GLBL_CMPY_CD = SP.GLBL_CMPY_CD");
        sb.append(" AND CRA.AR_CR_APVL_USR_ID = SP.PSN_CD");
        sb.append(" AND SP.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Credit Analyst Code";
        whereArray0[IDX_1] = "AR_CR_APVL_USR_ID";
        if ("CR_Equip".equals(scrEventNm)) {
            whereArray0[IDX_2] = scrnMsg.A.no(selectIndex).cltCrAnlstEquipPsnCd_A.getValue();
        } else if ("CR_Svc".equals(scrEventNm)) {
            whereArray0[IDX_2] = scrnMsg.A.no(selectIndex).cltCrAnlstSvcPsnCd_A.getValue();
        } else if ("CR_Sply".equals(scrEventNm)) {
            whereArray0[IDX_2] = scrnMsg.A.no(selectIndex).cltCrAnlstSplyPsnCd_A.getValue();
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Last Name";
        whereArray1[IDX_1] = "LAST_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "First Name";
        whereArray2[IDX_1] = "FIRST_NM";
        whereArray2[IDX_2] = "";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Credit Analyst Code";
        columnArray0[IDX_1] = "AR_CR_APVL_USR_ID";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Last,First Name";
        columnArray1[IDX_1] = "PSN_LAST_FIRST_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_61);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "LAST_NM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        // END 2016/07/19 T.Tsuchida [QC#12055,MOD]

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For OpenWinNextVal
     * @param scrnMsg NFDL0160BMsg
     * @return Param NWAL1130 For OpenWinNextVal
     */
    public static Object[] getParamNWAL1130ForOpenWinNextVal(NFDL0160BMsg scrnMsg) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Next Level Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("     CLT.CLT_PTFO_CD");
        sb.append("    ,CLT.CLT_PTFO_NM");
        sb.append("    ,TO_CHAR(CLT.CLT_PTFO_PK) CLT_PTFO_PK");
        sb.append("    ,CLT.CLT_PTFO_STS_FLG");
        sb.append("    ,CLT.GLBL_CMPY_CD");
        sb.append("    ,CLT.EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("     CLT_PTFO CLT");
        sb.append(" WHERE");
        sb.append("     CLT.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append(" AND CLT.EZCANCELFLAG      = '0'");
        sb.append(" AND CLT.CLT_PTFO_STS_FLG  = '").append(ZYPConstant.FLG_ON_Y).append("'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Portfolio Code";
        whereArray0[IDX_1] = "CLT_PTFO_CD";
        whereArray0[IDX_2] = "";
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Portfolio Name";
        whereArray1[IDX_1] = "CLT_PTFO_NM";
        whereArray1[IDX_2] = scrnMsg.A.no(selectIndex).cltPtfoNm_AR.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Portfolio Code";
        columnArray0[IDX_1] = "CLT_PTFO_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Portfolio Name";
        columnArray1[IDX_1] = "CLT_PTFO_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Portfolio PK";
        columnArray2[IDX_1] = "CLT_PTFO_PK";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_28);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Portfolio Status";
        columnArray3[IDX_1] = "CLT_PTFO_STS_FLG";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "CLT_PTFO_NM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

}
