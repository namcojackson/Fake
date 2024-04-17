/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1850.common;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BIZ_ID_FOR_ENTRY;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_APL;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_APR;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_RST;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.DELY_HLD_CD_ALL;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.EIGHT_DIGIT_MODE;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.NWAM0712E;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.NWAM0754E;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.PERCENT;
import static business.servlet.NWAL1850.constant.NWAL1850Constant.SCRN_ID_00;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NSAL1240_MODE_02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDFieldErrorException;
import business.servlet.NWAL1850.NWAL1850BMsg;
import business.servlet.NWAL1850.NWAL1850_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1850CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/09   Fujitsu         T.Murai         Update          QC#7670
 * 2016/05/23   Fujitsu         T.Murai         Update          QC#8544
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#9078
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2022/06/02   Hitachi         K.Kitachi       Update          QC#60037
 * 2022/07/11   Hitachi         T.NEMA          Update          QC#60037
 *</pre>
 */
public class NWAL1850CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);// MOD S21_NA QC#13856
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
// START 2022/07/11 T.NEMA [QC#60037, MOD]
//        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
//        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
// END   2022/07/11 T.NEMA [QC#60037, MOD]
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * 
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
     * 
     * @param scrnMsg     NWAL1850BMsg
     * @param scrnAMsgAry NWAL1850_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1850BMsg scrnMsg, NWAL1850_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1850BMsg
     * @param scrnAMsgAry NWAL1850_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1850BMsg scrnMsg, NWAL1850_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL1850BMsg
     * @param scrnAMsgAry NWAL1850_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1850BMsg scrnMsg, NWAL1850_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL1850BMsg scrnMsg) {

        // Save Search
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        // Header
        scrnMsg.addCheckItem(scrnMsg.schdAgmtNum);
        // Add Start QC#7670
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        // Add End QC#7670
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.addCheckItem(scrnMsg.soldToCustLocCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_SO);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_SI);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.schdAgmtCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.schdAgmtCratDt_TO);
        scrnMsg.addCheckItem(scrnMsg.schdAgmtStsCd);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.adminPsnCd);
        // START 2022/06/02 K.Kitachi [QC#60037, ADD]
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem30Txt);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.schdAgmtDelyHldCd);
        // END 2022/06/02 K.Kitachi [QC#60037, ADD]

    }

    /**
     * checkInputValue
     * @param scrnMsg NWAL1850BMsg
     */
    public static void checkInputValue(NWAL1850BMsg scrnMsg) {
        if (!checkHasValue(scrnMsg)) {
            scrnMsg.setMessageInfo(NWAM0754E);
            throw new EZDFieldErrorException();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtCratDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.schdAgmtCratDt_TO)) {
            // schdAgmtCratDt_FR > schdAgmtCratDt_TO
            if (0 < scrnMsg.schdAgmtCratDt_FR.getValue().compareTo(scrnMsg.schdAgmtCratDt_TO.getValue())) {
                scrnMsg.schdAgmtCratDt_FR.setErrorInfo(1, NWAM0712E, new String[]{"Schedule Date From", "Schedule Date Through"});
                scrnMsg.schdAgmtCratDt_TO.setErrorInfo(1, NWAM0712E, new String[]{"Schedule Date From", "Schedule Date Through"});
            }
        }
        addCheckItemBizLayerErr(scrnMsg);
    }

    /**
     * checkHasValue
     * @param scrnMsg NWAL1850BMsg
     * @return boolean
     */
    private static boolean checkHasValue(NWAL1850BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtNum)) {
            return true;
            // Add Start QC#7670
        } else if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            return true;
            // Add End QC#7670
        } else if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.soldToCustLocCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_SO)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_SI)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtCratDt_FR)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtCratDt_TO)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtStsCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.custIssPoNum)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.adminPsnCd)) {
            return true;
        // START 2022/06/02 K.Kitachi [QC#60037, ADD]
        } else if (ZYPCommonFunc.hasValue(scrnMsg.serNum)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem30Txt)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsContrNum)) {
            return true;
        } else if (!DELY_HLD_CD_ALL.equals(scrnMsg.schdAgmtDelyHldCd.getValue())) {
            return true;
        // END 2022/06/02 K.Kitachi [QC#60037, ADD]
        }
        return false;
    }

    /**
     * Set Feild Control
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1850BMsg
     */
    public static void setFeildControl(S21CommonHandler handler, NWAL1850BMsg scrnMsg) {
        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        if (0 < scrnMsg.A.getValidCount()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).cpoOrdNum_A.setInputProtected(true);
                scrnMsg.A.no(i).sellToCustCd_A.setInputProtected(true);
                scrnMsg.A.no(i).soldToCustLocCd_A.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_AO.setInputProtected(true);
                scrnMsg.A.no(i).xxAllLineAddr_AO.setInputProtected(true);
                scrnMsg.A.no(i).shipToCustAcctCd_A.setInputProtected(true);
                scrnMsg.A.no(i).shipToCustCd_A.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_AH.setInputProtected(true);
                scrnMsg.A.no(i).xxAllLineAddr_AH.setInputProtected(true);
                scrnMsg.A.no(i).dsOrdCatgDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).dsOrdTpDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).schdAgmtCratDt_A.setInputProtected(true);
                scrnMsg.A.no(i).schdAgmtStsDescTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).custIssPoNum_A.setInputProtected(true);
                scrnMsg.A.no(i).xxPsnNm_A.setInputProtected(true);
                // START 2022/06/02 K.Kitachi [QC#60037, ADD]
                scrnMsg.A.no(i).serNum_A.setInputProtected(true);
                scrnMsg.A.no(i).svcConfigMstrPk_A.setInputProtected(true);
                scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
                scrnMsg.A.no(i).schdAgmtDelyHldDescTxt_A.setInputProtected(true);
                // END 2022/06/02 K.Kitachi [QC#60037, ADD]
            }
        }

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).schdAgmtNum_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.schdAgmtNum);
        }
    }


    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL1850BMsg
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdCatg(NWAL1850BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "P";
        params[1] = "Order Category Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DOC.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,DOC.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,DOC.DS_ORD_CATG_CD       AS DS_ORD_CATG_CD");
        sb.append("   ,DOC.DS_ORD_CATG_DESC_TXT AS DS_ORD_CATG_DESC_TXT");
        sb.append("   ,DOC.DS_ORD_CATG_SORT_NUM AS DS_ORD_CATG_SORT_NUM ");
        sb.append("FROM");
        sb.append("    DS_ORD_CATG DOC ");
        sb.append("WHERE");
        sb.append("    DOC.DS_ORD_CATG_CD IN (");
        sb.append("        SELECT");
        sb.append("            DOT.DS_ORD_CATG_CD AS DS_ORD_CATG_CD");
        sb.append("        FROM");
        sb.append("            AVAL_DS_ORD_TP_APP_ID AD");
        sb.append("           ,DS_ORD_TP             DOT");
        sb.append("           ,DS_ORD_TP_PROC_DFN    DOTPD"); //S21_NA#8544 Add
        sb.append("        WHERE");
        sb.append("            AD.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("            AND AD.BIZ_APP_ID    = '").append(BIZ_ID_FOR_ENTRY).append("' ");
        sb.append("            AND AD.EZCANCELFLAG  = '0'");
        sb.append("            AND AD.GLBL_CMPY_CD  = DOT.GLBL_CMPY_CD");
        sb.append("            AND AD.DS_ORD_TP_CD  = DOT.DS_ORD_TP_CD");
        sb.append("            AND DOT.EZCANCELFLAG = '0'");
        //S21_NA#8544 Add Start
        sb.append("            AND DOT.GLBL_CMPY_CD = DOTPD.GLBL_CMPY_CD");
        sb.append("            AND DOT.DS_ORD_TP_CD = DOTPD.DS_ORD_TP_CD");
        sb.append("            AND DOTPD.ACTV_FLG   = 'Y'");
        sb.append("            AND DOTPD.EZCANCELFLAG = '0'");
        //S21_NA#8544 Add End
        sb.append("        GROUP BY");
        sb.append("            DOT.DS_ORD_CATG_CD )");
        sb.append("    AND DOC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND DOC.EZCANCELFLAG = '0'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[4];
        whereArray[0] = "Order Category Name";
        whereArray[1] = "DS_ORD_CATG_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray[2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            whereArray[2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        } else {
            whereArray[2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Order Category Code";
        columnArray0[1] = "DS_ORD_CATG_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Order Category Name";
        columnArray1[1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[6] = scrnMsg.P;

        return params;
    }

    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1850BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NWAL1850BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, EIGHT_DIGIT_MODE);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.mdseCd);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_00);

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6770 For Ship To
     * @param scrnMsg NWAL1850BMsg
     * @return Param NMAL6770 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL1850BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
        // Mod End 2016/08/04 QC#9078

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.shipToCustAcctCd);
        paramList.add(scrnMsg.dsAcctNm_SI); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Active Only
        paramList.add(scrnMsg.xxPopPrm_02); // Ship To's Only
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.shipToCustCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6770 For Sold To
     * @param scrnMsg NWAL1850BMsg
     * @return Param NMAL6770 For Sold To
     */
    public static Object[] getParamNMAL6760ForSoldTo(NWAL1850BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // Mod End 2016/08/04 QC#9078

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.dsAcctNm_SO);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Active Only
        paramList.add(scrnMsg.xxPopPrm_02); // Bill To's Only
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.soldToCustLocCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used

        return paramList.toArray(new EZDBItem[0]);
    }

    // START 2022/06/02 K.Kitachi [QC#60037, ADD]
    /**
     * Get Param NSAL0110
     * @param scrnMsg NWAL1850BMsg
     * @return Param NSAL0110
     */
    public static Object[] getParamNSAL0110(NWAL1850BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        List<Object> paramList = new ArrayList<Object>(12);
        paramList.add(scrnMsg.dsContrNum); // Contract # 
        paramList.add(scrnMsg.xxPopPrm_01); // Contract Control Status Code 
        paramList.add(scrnMsg.xxPopPrm_02); // Contract Name
        paramList.add(scrnMsg.xxPopPrm_03); // Contract Category Code
        paramList.add(scrnMsg.xxPopPrm_04); // Account Code
        paramList.add(scrnMsg.xxPopPrm_05); // Contract Detail Type Code
        paramList.add(scrnMsg.xxPopPrm_06); // Contract Control Status Code (Detail)
        paramList.add(scrnMsg.xxPopPrm_07); // Serial #
        paramList.add(scrnMsg.xxPopPrm_08); // Model Name
        paramList.add(scrnMsg.xxPopPrm_09); // Display Mode
        // output
        paramList.add(scrnMsg.dsContrPk_O);    // DS Contract PK
        paramList.add(scrnMsg.dsContrDtlPk_O); // DS Contract Detail PK 

        return paramList.toArray(new Object[paramList.size()]);
    }

    /**
     * Get Param NSAL1240
     * @param scrnMsg NWAL1850BMsg
     * @return Param NSAL1240
     */
    public static Object[] getParamNSAL1240(NWAL1850BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        List<Object> parameters = new ArrayList<Object>(31);

        // [0]: CONFIG_EXST_MODE_CD
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NSAL1240_MODE_02);
        parameters.add(scrnMsg.xxPopPrm_00);

        // [1]: SVC_CONFIG_MSTR_PK
        String svcConfigMstrPkStr = scrnMsg.xxScrItem30Txt.getValue();
        if (ZYPCommonFunc.isNumberCheck(svcConfigMstrPkStr)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_I, new BigDecimal(svcConfigMstrPkStr));
        }
        parameters.add(scrnMsg.svcConfigMstrPk_I);

        // [2]: SER_NUM
        parameters.add(scrnMsg.serNum);

        // [3]: SVC_MACH_MSTR_PK
        parameters.add(scrnMsg.svcMachMstrPk_I);

        // [4]: MDSE_CD
        parameters.add(scrnMsg.xxPopPrm_04);

        // [5]: MDL_NM
        parameters.add(scrnMsg.xxPopPrm_05);

        // [6]: SHIP_FEOM_DT
        parameters.add(scrnMsg.xxPopPrm_06);

        // [7]: SHIP_THRU_DT
        parameters.add(scrnMsg.xxPopPrm_07);

        // [8]: SVC_MACH_USG_STS_CD
        parameters.add(scrnMsg.xxPopPrm_08);

        // [9]: SVC_MACH_MSTR_STS_CD, Array
        parameters.add((EZDBMsgArray) scrnMsg.Y);

        // [10]: SVC_MACH_MSTR_STS_EDIT_FLG
        parameters.add(scrnMsg.xxPopPrm_10);

        // [11]: MACH_ALLOC_MODE_CODE
        parameters.add(scrnMsg.xxPopPrm_11);

        // [12]: MAIN_UNIT_FLG
        parameters.add(scrnMsg.xxPopPrm_12);

        // [13]: STK_STS_CD
        parameters.add(scrnMsg.xxPopPrm_13);

        // [14]: WH_CD
        parameters.add(scrnMsg.xxPopPrm_14);

        // [15]: SUB_WH_CD
        parameters.add(scrnMsg.xxPopPrm_15);

        // [16]: SVC_SLN_SQ
        parameters.add(scrnMsg.xxPopPrm_16);

        // [17]: SVC_SLN_NM
        parameters.add(scrnMsg.xxPopPrm_17);

        // [18]: CONTR_NUM
        parameters.add(scrnMsg.dsContrNum);

        // [19]: DS_OWNR_ACCT_NUM
        parameters.add(scrnMsg.xxPopPrm_19);

        // [20]: OWNR_LOC_CD
        parameters.add(scrnMsg.xxPopPrm_20);

        // [21]: DS_BILL_TO_ACCT_NUM
        parameters.add(scrnMsg.xxPopPrm_21);

        // [22]: BILL_TO_LOC_CD
        parameters.add(scrnMsg.xxPopPrm_22);

        // [23]: DS_CUR_LOC_ACCT_NUM
        parameters.add(scrnMsg.xxPopPrm_23);

        // [24]: CUR_LOC_NUM
        parameters.add(scrnMsg.xxPopPrm_24);

        // Output Parameter
        parameters.add(scrnMsg.mdlId_O); // [25]: MDL_ID
        parameters.add(scrnMsg.mdlNm_O); // [26]: MDL_NM
        parameters.add(scrnMsg.contrPk_O); // [27]: CONTR_PK
        parameters.add(scrnMsg.contrNum_O); // [28]: CONTR_NUM
        parameters.add(scrnMsg.svcConfigMstrPk_O); // [29]: SVC_CONFIG_MSTR_PK
        parameters.add((EZDBMsgArray) scrnMsg.Q); // [30]:
        return parameters.toArray(new Object[parameters.size()]);
    }

    /**
     * get param by outPut param
     * @param scrnMsg NWAL1850BMsg
     */
    public static void getOutputParamNSAL1240(NWAL1850BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, scrnMsg.contrNum_O);
        if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_O)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem30Txt, String.valueOf(scrnMsg.svcConfigMstrPk_O.getValue()));
        }
        if (scrnMsg.Q.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, scrnMsg.Q.no(0).serNum_O);
        }
    }
    // END 2022/06/02 K.Kitachi [QC#60037, ADD]

    /**
     * Clear Popup Parameters
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NWAL1850BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        // START 2022/06/02 K.Kitachi [QC#60037, ADD]
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
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        scrnMsg.xxPopPrm_24.clear();
        scrnMsg.dsContrPk_O.clear();
        scrnMsg.dsContrDtlPk_O.clear();
        scrnMsg.mdlId_O.clear();
        scrnMsg.mdlNm_O.clear();
        scrnMsg.contrPk_O.clear();
        scrnMsg.contrNum_O.clear();
        scrnMsg.svcConfigMstrPk_O.clear();
        scrnMsg.svcConfigMstrPk_I.clear();
        scrnMsg.svcMachMstrPk_I.clear();
        ZYPTableUtil.clear(scrnMsg.Y);
        ZYPTableUtil.clear(scrnMsg.Q);
        // END 2022/06/02 K.Kitachi [QC#60037, ADD]
    }
}
