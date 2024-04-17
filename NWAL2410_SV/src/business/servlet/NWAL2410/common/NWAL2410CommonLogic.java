/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410.common;

import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_APR_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_APR_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_APR_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_DELETE_ROW;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.BTN_INSERT_ROW;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.FUNC_FULL_USE;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.FUNC_UPDATE;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.MAX_RECORD_COUNT;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2410.NWAL2410BMsg;
import business.servlet.NWAL2410.NWAL2410Bean;
import business.servlet.NWAL2410.NWAL2410_ABMsg;
import business.servlet.NWAL2410.NWAL2410_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL2410CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 *</pre>
 */
public class NWAL2410CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APR_BTN_NM, BTN_CMN_APR_EVENT_NM, BTN_CMN_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);

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
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL2410BMsg
     * @param scrnAMsgAry NWAL2410_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL2410BMsg scrnMsg, NWAL2410_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL2410BMsg
     * @param scrnAMsgAry NWAL2410_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL2410BMsg scrnMsg, NWAL2410_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWAL2410BMsg
     * @param scrnAMsgAry NWAL2410_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL2410BMsg scrnMsg, NWAL2410_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * <pre>
     * The screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2410BMsg
     * @param userId String
     */
    public static final void setControlScreen(EZDCommonHandler handler, NWAL2410BMsg scrnMsg, String userId) {

        setTblBackColor(scrnMsg);
        controlScreenFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, userId, scrnMsg);

    }

    /**
     * Set table's back color
     * @param scrnMsg NWAL2410BMsg
     */
    public static final void setTblBackColor(NWAL2410BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG(NWAL2410Bean.A, scrnMsg.A);

    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2410BMsg
     * @param userId String
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NWAL2410BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NWAL2410BMsg scrnMsg, String userId) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2410_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                if (ZYPCommonFunc.hasValue(detail.docMgtOrgCd_A) && ZYPCommonFunc.hasValue(detail.ezUpTime_A)) {
                    detail.docMgtOrgCd_A.setInputProtected(true);
                    detail.docMgtScanBrCd_A.setInputProtected(true);
                    if (hasDeleteAuthority(userId)) {
                        detail.xxChkBox_A.setInputProtected(false);
                    } else {
                        detail.xxChkBox_A.setInputProtected(true);
                    }
                } else {
                    detail.docMgtOrgCd_A.setInputProtected(false);
                    detail.docMgtScanBrCd_A.setInputProtected(false);
                }
            } else {
                detail.xxChkBox_A.setInputProtected(true);
                detail.docMgtOrgCd_A.setInputProtected(true);
                detail.docMgtScanBrCd_A.setInputProtected(true);
                detail.docMgtScanBrNm_A.setInputProtected(true);
                detail.docMgtScanRgNm_A.setInputProtected(true);
                detail.docMgtScanZnNm_A.setInputProtected(true);

                detail.defOrdProcPsnCd_A.setInputProtected(true);
                detail.defBrAdminPsnCd_A.setInputProtected(true);
                detail.leaseCmpyProcPsnCd_A.setInputProtected(true);
                detail.poPendEmlAddr_A.setInputProtected(true);
                detail.poIssEmlAddr_A.setInputProtected(true);
                detail.invPkgPendEmlAddr_A.setInputProtected(true);
                detail.actvFlg_A.setInputProtected(true);

            }
        }
    }

    /**
     * Return true if userId have update authority.
     * @param userId String
     * @return boolean
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE })) {
            return true;
        }
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_FULL_USE })) {
            return true;
        }
        return false;
    }

    /**
     * Return true if userId have delete authority.
     * @param userId String
     * @return boolean
     */
    public static final boolean hasDeleteAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_FULL_USE })) {
            return true;
        }
        return false;
    }

    /**
     * Buttons's activity set by user authority
     * @param handler EZDCommonHandler
     * @param userId String
     * @param scrnMsg NWAL2410BMsg
     */
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NWAL2410BMsg scrnMsg) {

        if (!hasUpdateAuthority(userId)) {
            handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 1, null);
            if (scrnMsg.A.getValidCount() == 0) {
                handler.setButtonEnabled(BTN_DELETE_ROW, false);
                handler.setButtonEnabled(BTN_INSERT_ROW, true);
            } else {
                handler.setButtonEnabled(BTN_DELETE_ROW, true);

                if (scrnMsg.xxPageShowOfNum.getValueInt() < MAX_RECORD_COUNT) {
                    handler.setButtonEnabled(BTN_INSERT_ROW, true);
                } else {
                    handler.setButtonEnabled(BTN_INSERT_ROW, false);
                }
            }
        }
    }

    /**
     * addCheckDetailsItem
     * @param scrnMsg NWAL2410BMsg
     */
    public static void addCheckDetailsItem(NWAL2410BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).docMgtOrgCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).docMgtScanBrCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).docMgtScanBrNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).docMgtScanRgNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).docMgtScanZnNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).defOrdProcPsnCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).defBrAdminPsnCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).leaseCmpyProcPsnCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poPendEmlAddr_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poIssEmlAddr_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invPkgPendEmlAddr_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).actvFlg_A);
        }
    }

    /**
     * setBranchPopupParam
     * @param scrnMsg NWAL2410BMsg
     * @param glblCmpyCd glblCmpyCd
     * @param selectRowNum int
     * @return Object[]
     */
    public static Object[] setBranchPopupParam(NWAL2410BMsg scrnMsg, String glblCmpyCd, int selectRowNum) {
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
        // 6:Result(BMsg.R)
        scrnMsg.R.clear();

        suffixP0 = "";

        scrnNmP1 = "Default Branch Admin Search";

        tblNmP2.append(" SELECT");
        tblNmP2.append("    CB.GLBL_CMPY_CD");
        tblNmP2.append("    ,CB.EZCANCELFLAG");
        tblNmP2.append("    ,CB.COA_BR_CD");
        tblNmP2.append("    ,CB.COA_BR_DESC_TXT");
        tblNmP2.append("    ,CBR.COA_BR_RG_DESC_TXT");
        tblNmP2.append("    ,CBZ.COA_BR_ZN_DESC_TXT");
        tblNmP2.append(" FROM");
        tblNmP2.append("    COA_BR CB");
        tblNmP2.append("    ,COA_BR_RG CBR");
        tblNmP2.append("    ,COA_BR_ZN CBZ");
        tblNmP2.append(" WHERE");
        tblNmP2.append("        CB.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append("    AND CB.EZCANCELFLAG = '0'");
        tblNmP2.append("    AND CB.GLBL_CMPY_CD = CBR.GLBL_CMPY_CD (+)");
        tblNmP2.append("    AND CB.EZCANCELFLAG = CBR.EZCANCELFLAG (+)");
        tblNmP2.append("    AND CB.COA_BR_RG_CD = CBR.COA_BR_RG_CD (+)");
        tblNmP2.append("    AND CB.GLBL_CMPY_CD = CBZ.GLBL_CMPY_CD (+)");
        tblNmP2.append("    AND CB.EZCANCELFLAG = CBZ.EZCANCELFLAG (+)");
        tblNmP2.append("    AND CB.COA_BR_ZN_CD = CBZ.COA_BR_ZN_CD (+)");

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Branch Code";
        whereArray0[1] = "COA_BR_CD";
        whereArray0[2] = scrnMsg.A.no(selectRowNum).docMgtScanBrCd_A.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Branch Name";
        whereArray1[1] = "COA_BR_DESC_TXT";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Branch Code";
        columnArray0[1] = "COA_BR_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Branch Name";
        columnArray1[1] = "COA_BR_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Region";
        columnArray2[1] = "COA_BR_RG_DESC_TXT";
        columnArray2[2] = BigDecimal.valueOf(25);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Zone";
        columnArray3[1] = "COA_BR_ZN_DESC_TXT";
        columnArray3[2] = BigDecimal.valueOf(25);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray3);

        Object[] sortConditionArray0 = new Object[4];
        sortConditionArray0[0] = "COA_BR_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionListP5.add(sortConditionArray0);

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
     * setBrPsnPopupParam
     * @param scrnMsg NWAL2410BMsg
     * @param glblCmpyCd String
     * @param psnCd String
     * @return Object[]
     */
    public static Object[] setAuthPsnPopupParam(NWAL2410BMsg scrnMsg, String glblCmpyCd, String psnCd) {
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
        // 6:Result(BMsg.R)
        scrnMsg.R.clear();

        suffixP0 = "";

        scrnNmP1 = "Default Branch Admin Search";

        tblNmP2.append(" SELECT");
        tblNmP2.append("     AP.GLBL_CMPY_CD");
        tblNmP2.append("     ,AP.EZCANCELFLAG");
        tblNmP2.append("     ,AP.USR_NM");
        tblNmP2.append("     ,AP.FIRST_NM || ' ' || AP.LAST_NM AS PSN_NM");
        tblNmP2.append(" FROM");
        tblNmP2.append("     AUTH_PSN AP");
        tblNmP2.append(" WHERE");
        tblNmP2.append("         AP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append("     AND AP.EZCANCELFLAG = '0'");
        tblNmP2.append("     AND AP.ACTV_FLG     = 'Y'");

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Psn Code";
        whereArray0[1] = "USR_NM";
        whereArray0[2] = psnCd;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Psn Name";
        whereArray1[1] = "PSN_NM";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Psn Code";
        columnArray0[1] = "USR_NM";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Psn Name";
        columnArray1[1] = "PSN_NM";
        columnArray1[2] = BigDecimal.valueOf(80);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray1);

        Object[] sortConditionArray0 = new Object[4];
        sortConditionArray0[0] = "USR_NM";
        sortConditionArray0[1] = "ASC";
        sortConditionListP5.add(sortConditionArray0);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }
}
