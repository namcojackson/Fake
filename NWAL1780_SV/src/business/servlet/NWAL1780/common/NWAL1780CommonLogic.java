/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1780.common;

import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.EIGHT_DIGIT_MODE;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_0;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_1;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_13;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_2;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_3;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_4;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_5;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_50;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_6;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.IDX_7;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.PERCENT;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.QUOTE_BIZ_ID;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1780.NWAL1780BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Fujitsu         T.Murai         Create          N/A
 * 2016/05/23   Fujitsu         T.Murai         Update          S21_NA#8544
 * 2016/08/04   Fujitsu         R.Nakamura      Update          S21_NA#9078
 * 2016/09/14   Hitachi         T.Mizuki        Update          S21_NA#14249
 * 2018/03/02   Fujitsu         K.Ishizuka      Update          S21_NA#22956
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 * </pre>
 */
public class NWAL1780CommonLogic {

    /**
     * Initial Common Button
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        // mod start 2016/09/14 CSA QC#14249
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null);
        // mod end 2016/09/14 CSA QC#14249
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 0, null);
//        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);
    }

    /**
     * set init protect
     * @param scrnMsg NWAL1780BMsg
     */
    public static void initProtect(NWAL1780BMsg scrnMsg) {

        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).cpoOrdNum_A.setInputProtected(true);
            scrnMsg.A.no(i).sellToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(i).soldToCustLocCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_AO.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_SO.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustAcctCd_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_AI.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_SI.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdCatgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).splyQuoteDt_A.setInputProtected(true);
            scrnMsg.A.no(i).splyQuoteStsDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).custIssPoNum_A.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).splyQuoteNm_A.setInputProtected(true); // 2018/03/02 S21_NA#22956 Add
        }
    }

    /**
     * Search Validation check
     * @param scrnMsg NWAL1780BMsg
     */
    public static void searchValidCheck(NWAL1780BMsg scrnMsg) {

        // Save Search
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);

        // Header
        scrnMsg.addCheckItem(scrnMsg.splyQuoteNum);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.addCheckItem(scrnMsg.soldToCustLocCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_SO);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_SI);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.splyQuoteDt_FR);
        scrnMsg.addCheckItem(scrnMsg.splyQuoteDt_TO);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.adminPsnCd);
    }

    /**
     * Clear Popup Parameters
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NWAL1780BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
    }

    /**
     * Get Param NMAL6770 For Ship To
     * @param scrnMsg NWAL1780BMsg
     * @return Param NMAL6770 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL1780BMsg scrnMsg) {

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
     * @param scrnMsg NWAL1780BMsg
     * @return Param NMAL6770 For Sold To
     */
    public static Object[] getParamNMAL6760ForSoldTo(NWAL1780BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // Mod Eand 2016/08/04 QC#9078

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

    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL1780BMsg
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdCatg(NWAL1780BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "P";
        params[IDX_1] = "Order Category Search";

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
        sb.append("           ,DS_ORD_TP_PROC_DFN    DOTPD"); // S21_NA#8544 Add
        sb.append("        WHERE");
        sb.append("            AD.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("            AND AD.BIZ_APP_ID    = '").append(QUOTE_BIZ_ID).append("' ");
        sb.append("            AND AD.EZCANCELFLAG  = '0'");
        sb.append("            AND AD.GLBL_CMPY_CD  = DOT.GLBL_CMPY_CD");
        sb.append("            AND AD.DS_ORD_TP_CD  = DOT.DS_ORD_TP_CD");
        sb.append("            AND DOT.EZCANCELFLAG = '0'");
        //  S21_NA#8544 Add Start
        sb.append("            AND DOT.GLBL_CMPY_CD = DOTPD.GLBL_CMPY_CD");
        sb.append("            AND DOT.DS_ORD_TP_CD = DOTPD.DS_ORD_TP_CD");
        sb.append("            AND DOTPD.ACTV_FLG   = 'Y'");
        sb.append("            AND DOTPD.EZCANCELFLAG = '0'");
        //  S21_NA#8544 Add End
        sb.append("        GROUP BY");
        sb.append("            DOT.DS_ORD_CATG_CD )");
        sb.append("    AND DOC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND DOC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Order Category Name";
        whereArray[IDX_1] = "DS_ORD_CATG_DESC_TXT";

        // S21_NA#8393 Mod Start
//        whereArray[IDX_2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            whereArray[IDX_2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End

        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Category Code";
        columnArray0[IDX_1] = "DS_ORD_CATG_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Category Name";
        columnArray1[IDX_1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1780BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NWAL1780BMsg scrnMsg) {

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
}
