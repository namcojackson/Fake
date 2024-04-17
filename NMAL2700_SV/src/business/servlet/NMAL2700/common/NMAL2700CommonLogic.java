/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700.common;

import static business.servlet.NMAL2700.constant.NMAL2700Constant.BIZ_ID;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_APL;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_APR;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_DEL;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_DWL;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_RJT;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_RST;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_RTN;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_SAV;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_CMN_SUB;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.CLMN_NAME;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.CLMN_PROFILE_ID;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.DISPLAY_NM_NAME;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.DISPLAY_NM_PROFILE_ID;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.FUNC_ID_UPDATE;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.MAX_ROW;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.NMAM0192E;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.RECORD_NOTFOUND;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2700.NMAL2700BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL2700CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 * 2018/03/28   Fujitsu         M.Ohno          Update          QC#24387
 *</pre>
 */
public class NMAL2700CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2700BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL2700BMsg scrnMsg) {
        controlScreenFields(userProfileService, handler, scrnMsg);
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2700BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL2700BMsg scrnMsg) {

        boolean updFlg = isUpdateUser(userProfileService);
        if (scrnMsg.A.getValidCount() == RECORD_NOTFOUND) {

            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        } else {
            handler.setButtonEnabled(BTN_DELETE_ROW, updFlg);
            if (updFlg) {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            }
        }

        if (MAX_ROW == scrnMsg.A.getValidCount()) {
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
        } else {
            handler.setButtonEnabled(BTN_INSERT_ROW, updFlg);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxRowId_A)) {
                scrnMsg.A.no(i).orgFuncRoleTpCd_A.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).orgFuncRoleTpCd_A.setInputProtected(false);
            }

            if (updFlg) {
                scrnMsg.A.no(i).crmSlsPrflNm_LK.setInputProtected(false);
                scrnMsg.A.no(i).crmSlsPrflNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.A.no(i).crmSlsPrflNm_LK.setInputProtected(true);
                scrnMsg.A.no(i).crmSlsPrflNm_LK.clear();
            }
        }
    }

    /**
     * checkMandator
     * @param scrnMsg NMAL2700BMsg
     */
    public static void checkMandator(NMAL2700BMsg scrnMsg) {

        boolean checkMandatoryFlag = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.firstOrgCd)) {
            checkMandatoryFlag = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgFuncRoleTpCd)) {
            checkMandatoryFlag = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgFuncRoleTpNm)) {
            checkMandatoryFlag = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgFuncRoleTpDescTxt)) {
            checkMandatoryFlag = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.mgrFlg)) {
            checkMandatoryFlag = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.spclFlg)) {
            checkMandatoryFlag = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.equipFlg)) {
            checkMandatoryFlag = true;
        }


        if (ZYPCommonFunc.hasValue(scrnMsg.cmsnFlg)) {
            checkMandatoryFlag = true;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.actvFlg)) {
            checkMandatoryFlag = true;
        }

        if (!checkMandatoryFlag) {
            scrnMsg.firstOrgCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.orgFuncRoleTpCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.orgFuncRoleTpNm.setErrorInfo(1, NMAM0192E);
            scrnMsg.orgFuncRoleTpDescTxt.setErrorInfo(1, NMAM0192E);
            scrnMsg.mgrFlg.setErrorInfo(1, NMAM0192E);
            scrnMsg.spclFlg.setErrorInfo(1, NMAM0192E);
            scrnMsg.equipFlg.setErrorInfo(1, NMAM0192E);
            scrnMsg.cmsnFlg.setErrorInfo(1, NMAM0192E);
            scrnMsg.actvFlg.setErrorInfo(1, NMAM0192E);
        }
    }

    /**
     * Method name : setRqstHstSelectStr
     * @param glblCmpyCd String
     * @return String
     */
    public static String setRqstHstSelectStr(String glblCmpyCd) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ");
        sb.append("   SF.PROFILE_ID ");
        // 2018/03/27 QC#24387 add start
        sb.append(" , 'ESS' AS LOB ");
        // 2018/03/27 QC#24387 add end
        sb.append(" , SF.NAME ");
        sb.append(" , '").append(glblCmpyCd).append("' AS GLBL_CMPY_CD ");
        sb.append(" , '0' AS EZCANCELFLAG ");
        sb.append(" FROM ");
        sb.append("   CANON_E404_SF_PROFILE_TBL SF ");
        // 2018/03/27 QC#24387 add start
        sb.append(" UNION");
        sb.append(" SELECT");
        sb.append("    SFLFS.PROFILE_ID");
        sb.append(" , 'LFS' AS LOB");
        sb.append(" , SFLFS.NAME");
        sb.append(" , '").append(glblCmpyCd).append("' AS GLBL_CMPY_CD ");
        sb.append(" , '0' AS EZCANCELFLAG ");
        sb.append(" FROM");
        sb.append("    CANON_E633_LFS_SF_PROFILE_TBL SFLFS");
        sb.append(" UNION");
        sb.append(" SELECT");
        sb.append("    SFPPS.PROFILE_ID");
        sb.append(" , 'PPS' AS LOB");
        sb.append(" , SFPPS.NAME");
        sb.append(" , '").append(glblCmpyCd).append("' AS GLBL_CMPY_CD ");
        sb.append(" , '0' AS EZCANCELFLAG ");
        sb.append(" FROM");
        sb.append("    CANON_E633_PPS_SF_PROFILE_TBL SFPPS");
        // 2018/03/27 QC#24387 add end
        sb.append(" ORDER BY ");
        sb.append("   LOB ");
        sb.append(" , NAME ");

        return sb.toString();
    }

    /**
     * setRqstHstWhereList
     * @return List<Objext[]> List
     */
    public static List<Object[]> setRqstHstWhereList(String sfdcProfileNm) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = DISPLAY_NM_PROFILE_ID;
        whereArray0[1] = CLMN_PROFILE_ID;
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = DISPLAY_NM_NAME;
        whereArray1[1] = CLMN_NAME;
        if (ZYPCommonFunc.hasValue(sfdcProfileNm)) {
            whereArray1[2] = sfdcProfileNm;
        } else {
            whereArray1[2] = null;
        }
        whereArray1[3] = ZYPConstant.FLG_ON_Y;

        whereList.add(whereArray1);
        return whereList;
    }

    /**
     * setRqstHstColumnList
     * @return List<Objext[]> columnList
     */
    public static List<Object[]> setRqstHstColumnList() {
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] setColumnArray0 = new Object[4];
        setColumnArray0[0] = DISPLAY_NM_PROFILE_ID;
        setColumnArray0[1] = CLMN_PROFILE_ID;
        setColumnArray0[2] = BigDecimal.valueOf(15);
        setColumnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(setColumnArray0);

        // 2018/03/27 QC#24387 add start
        Object[] setColumnArray1 = new Object[4];
        setColumnArray1[0] = "LOB";
        setColumnArray1[1] = "LOB";
        setColumnArray1[2] = BigDecimal.valueOf(15);
        setColumnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray1);
        // 2018/03/27 QC#24387 add start

        Object[] setColumnArray2 = new Object[4];
        setColumnArray2[0] = DISPLAY_NM_NAME;
        setColumnArray2[1] = CLMN_NAME;
        setColumnArray2[2] = BigDecimal.valueOf(35);
        setColumnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray2);

        return columnList;
    }

    /**
     * setRqstHstSortList
     * @return List<Objext[]> sortCondList
     */
    public static List<Object[]> setRqstHstSortList() {
        List<Object[]> sortCondList = new ArrayList<Object[]>();

        // 2018/03/27 QC#24387 add start
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "LOB";
        sortConditionArray0[1] = "ASC";
        sortCondList.add(sortConditionArray0);
        // 2018/03/27 QC#24387 add end

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = CLMN_NAME;
        sortConditionArray1[1] = "ASC";
        sortCondList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = CLMN_PROFILE_ID;
        sortConditionArray2[1] = "ASC";
        sortCondList.add(sortConditionArray2);

        return sortCondList;
    }
    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNC_ID_UPDATE);
    }
}
