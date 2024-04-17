package business.servlet.NMAL0100.common;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.AUTH_READ;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.AUTH_TAX;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.AUTH_HEADER;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN1;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN10;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN2;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN3;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN4;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN5;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN6;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN7;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN8;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.CMN_BTN9;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID_NMAL0110;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.SCREEN_ID;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.SEARCH_BTN;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.VIEW_ITEM_BTN;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.UPLOAD_BTN;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.servlet.NMAL0100.NMAL0100BMsg;
import business.servlet.NMAL0100.NMAL0100_PBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          N/A
 * 07/06/2020   CITS            M.Furugoori     Update          QC#55448
 * 10/28/2022   Hitachi         S.Nakatani      Update          QC#60399
 *</pre>
 */
public class NMAL0100CommonLogic {

    public static void setPage(NMAL0100BMsg scrnMsg, int page) {
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_10.setValue(page);
        scrnMsg.xxPageShowToNum_10.clear();
        scrnMsg.xxPageShowOfNum_10.clear();
    }

    public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NMAL0100BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        scrnMsg.coaProjDescTxt_H1.setInputProtected(true);
        scrnMsg.coaProdNm_H1.setInputProtected(true);
        scrnMsg.mdseStruElmntTpNm_L1.setInputProtected(true);
        scrnMsg.mdseStruElmntTpNm_L2.setInputProtected(true);
        scrnMsg.mdseStruElmntTpNm_L3.setInputProtected(true);
        scrnMsg.mdseStruElmntTpNm_L4.setInputProtected(true);
        scrnMsg.mdseStruElmntTpNm_L5.setInputProtected(true);
        scrnMsg.mktMdlNm_H1.setInputProtected(true);
        scrnMsg.mktMdlSegNm_H1.setInputProtected(true);

        String user = profile.getContextUserInfo().getUserId();
        boolean authRead = profile.isFunctionGranted(user, AUTH_READ);
        //Read Authorization
        if (authRead) {
            handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
            handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
            handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
            handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
            handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
            handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
            handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
            handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
            handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
            handler.setButtonEnabled(SEARCH_BTN[1], true);
            //Has Table data
            if (scrnMsg.A.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mnfItemCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemTpNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemClsTpNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxScrItem54Txt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxScrItem61Txt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemStsNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mktMdlNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mktMdlSegCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).zerothProdCtrlNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).firstProdCtrlNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).scdProdCtrlNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).thirdProdCtrlNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).frthProdCtrlNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).slsMatGrpDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).slsMatGrpDescTxt_A2.setInputProtected(true);
                    scrnMsg.A.no(i).slsMatGrpDescTxt_A3.setInputProtected(true);
                    scrnMsg.A.no(i).dsCmsnGrpDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemMnfNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).upcCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseItemActvDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).prchGrpNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdsePrcGrpNm_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseDescLongTxt_A1.setInputProtected(true);
                }
                handler.setButtonEnabled(VIEW_ITEM_BTN[1], true);
                // START 2020/07/06 [QC#55448,ADD]
                handler.setButtonEnabled(UPLOAD_BTN[1], true);
                // END 2020/07/06 [QC#55448,ADD]
                //Not has table data
            } else {
                handler.setButtonEnabled(VIEW_ITEM_BTN[1], false);
                // START 2020/07/06 [QC#55448,ADD]
                handler.setButtonEnabled(UPLOAD_BTN[1], false);
                // END 2020/07/06 [QC#55448,ADD]
            }
            //Not Read Authorization
        } else {
            handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
            handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
            handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
            handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
            handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
            handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
            handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);
            handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
            handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
            handler.setButtonEnabled(SEARCH_BTN[1], false);
            handler.setButtonEnabled(VIEW_ITEM_BTN[1], false);
            // START 2020/07/06 [QC#55448,ADD]
            handler.setButtonEnabled(UPLOAD_BTN[1], false);
            // END 2020/07/06 [QC#55448,ADD]
        }

        // START 2020/07/10 [QC#55448,ADD]
        boolean authTax = profile.isFunctionGranted(user, AUTH_TAX);
        // START 2022/10/28 [QC#60399,ADD]
        boolean authHeader = profile.isFunctionGranted(user, AUTH_HEADER);
        //Tax or Header Authorization
        if (authTax || authHeader) {
        // END 2022/10/28 [QC#60399,ADD]
            handler.setButtonEnabled(UPLOAD_BTN[1], true);
        } else {
            handler.setButtonEnabled(UPLOAD_BTN[1], false);
        }
        // END 2020/07/10 [QC#55448,ADD]
    }

    public static Object[] toArray_popup(NMAL0100_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }

    public static Object[] setParamForNMAL6040(NMAL0100BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.clear();
        scrnMsg.P.no(2).xxPopPrm.clear();
        scrnMsg.P.no(4).xxPopPrm.clear();
        scrnMsg.P.no(6).xxPopPrm.clear();
        scrnMsg.P.no(8).xxPopPrm.clear();
        scrnMsg.P.no(10).xxPopPrm.clear();
        scrnMsg.P.no(12).xxPopPrm.clear();
        Object[] params = toArray_popup(scrnMsg.P, 28);
        return params;

    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NMAL0100BMsg
     */
    public static void commonAddCheckItem(NMAL0100BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);

        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mnfItemCd_H1);
        scrnMsg.addCheckItem(scrnMsg.upcCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseDescLongTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemActvDt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemActvDt_H2);
        scrnMsg.addCheckItem(scrnMsg.mdseCratTmplNm_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mktMdlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mktMdlSegCd_H1);
        scrnMsg.addCheckItem(scrnMsg.zerothProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.firstProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.scdProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.thirdProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.frthProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpDescTxt_01);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpDescTxt_02);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpDescTxt_03);
        scrnMsg.addCheckItem(scrnMsg.dsCmsnGrpDescTxt_H1);
    }

    public static String setSelectForMaterialGroup(NMAL0100BMsg scrnMsg, String glblCmpyCd, String slsMatGrpSqNum) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    MG.EZCANCELFLAG ");
        sb.append("   ,MG.GLBL_CMPY_CD ");
        sb.append("   ,MG.SLS_MAT_GRP_CD ");
        sb.append("   ,MG.SLS_MAT_GRP_DESC_TXT ");
        sb.append("   ,MG.SLS_MAT_GRP_SORT_NUM ");
        sb.append("FROM ");
        sb.append("    SLS_MAT_GRP MG ");
        sb.append("WHERE ");
        sb.append("    MG.EZCANCELFLAG = '0' ");
        sb.append("AND MG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND MG.SLS_MAT_GRP_SQ_NUM = ").append(slsMatGrpSqNum).append(" ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForMaterialGroup(String slsMatGrpCd, String slsMatGrpDesctxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Material Group";
        whereArray0[1] = "SLS_MAT_GRP_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "SLS_MAT_GRP_DESC_TXT";
        if (ZYPCommonFunc.hasValue(slsMatGrpDesctxt)) {
            whereArray1[2] = slsMatGrpDesctxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForMaterialGroup(NMAL0100BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Material Group";
        columnArray0[1] = "SLS_MAT_GRP_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "SLS_MAT_GRP_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForMaterialGroup(NMAL0100BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "SLS_MAT_GRP_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForCommisionGroup(NMAL0100BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    DCG.EZCANCELFLAG ");
        sb.append("   ,DCG.GLBL_CMPY_CD ");
        sb.append("   ,DCG.DS_CMSN_GRP_CD ");
        sb.append("   ,DCG.DS_CMSN_GRP_DESC_TXT ");
        sb.append("   ,DCG.DS_CMSN_GRP_SORT_NUM ");
        sb.append("FROM ");
        sb.append("    DS_CMSN_GRP DCG ");
        sb.append("WHERE ");
        sb.append("    DCG.EZCANCELFLAG = '0' ");
        sb.append("AND DCG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForCommisionGroup(String dsCmsnGrpCd, String dsCmsnGrpDesctxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Commision Group";
        whereArray0[1] = "DS_CMSN_GRP_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "DS_CMSN_GRP_DESC_TXT";
        if (ZYPCommonFunc.hasValue(dsCmsnGrpDesctxt)) {
            whereArray1[2] = dsCmsnGrpDesctxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForCommisionGroup(NMAL0100BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Commision Group";
        columnArray0[1] = "DS_CMSN_GRP_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "DS_CMSN_GRP_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }
    
    public static List<Object[]> setSortListForCommisionGroup(NMAL0100BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "DS_CMSN_GRP_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForZerothProdHerch(NMAL0100BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS ZEROTH_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS ZEROTH_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS ZEROTH_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP).append("' ");

        return sb.toString();
    }

    public static List<Object[]> setWhereListForZerothProdHerch(String zerothProdHrchCd, String zerothProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 1";
        whereArray0[1] = "ZEROTH_PROD_HRCH_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "ZEROTH_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(zerothProdHrchDescTxt)) {
            whereArray1[2] = zerothProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForZerothProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 1";
        columnArray0[1] = "ZEROTH_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "ZEROTH_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForZerothProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "ZEROTH_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForFirstProdHerch(NMAL0100BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS FIRST_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS FIRST_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS FIRST_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForFirstProdHerch(String firstProdHrchCd, String firstProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 2";
        whereArray0[1] = "FIRST_PROD_HRCH_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "FIRST_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(firstProdHrchDescTxt)) {
            whereArray1[2] = firstProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForFirstProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 2";
        columnArray0[1] = "FIRST_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "FIRST_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForFirstProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "FIRST_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForScdProdHerch(NMAL0100BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS SCD_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS SCD_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS SCD_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForScdProdHerch(String scdProdHrchCd, String scdProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 3";
        whereArray0[1] = "SCD_PROD_HRCH_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "SCD_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scdProdHrchDescTxt)) {
            whereArray1[2] = scdProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForScdProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 3";
        columnArray0[1] = "SCD_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "SCD_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForScdProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "SCD_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForThirdProdHerch(NMAL0100BMsg scrnMsg, String glblCmpyCd, String scdProdHrchCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS THIRD_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS THIRD_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS THIRD_PROD_HRCH_SORT_NUM");
        sb.append("     ,PC.SCD_PROD_HRCH_CD");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForThirdProdHerch(String thirdProdHrchCd, String thirdProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 4";
        whereArray0[1] = "THIRD_PROD_HRCH_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "THIRD_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(thirdProdHrchDescTxt)) {
            whereArray1[2] = thirdProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];

        whereArray2[0] = "Product Level 3";
        whereArray2[1] = "SCD_PROD_HRCH_CD";
        whereArray2[2] = "";
        whereArray2[3] = FLG_OFF_N;
        whereList.add(whereArray2);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForThirdProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 4";
        columnArray0[1] = "THIRD_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "THIRD_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForThirdProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "THIRD_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }    
    
    public static String setSelectForFrthProdHerch(NMAL0100BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS FRTH_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS FRTH_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS FRTH_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForFrthProdHerch(String frthProdHrchCd, String frthProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 5";
        whereArray0[1] = "FRTH_PROD_HRCH_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "FRTH_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(frthProdHrchDescTxt)) {
            whereArray1[2] = frthProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForFrthProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 5";
        columnArray0[1] = "FRTH_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "FRTH_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForFrthProdHerch(NMAL0100BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "FRTH_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }

    // START 2020/07/07 [QC#55448,ADD]
    /**
     * isUploadUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isTaxUploadUser(S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID_NMAL0110);

        if (functionIds.contains(AUTH_TAX)) {
            return true;
        } else {
            return false;
        }
    }
    // END 2020/07/07 [QC#55448,ADD]

    // START 2022/10/28 [QC#60399,ADD]
    /**
     * isUploadUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isHeaderUploadUser(S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID_NMAL0110);

        if (functionIds.contains(AUTH_HEADER)) {
            return true;
        } else {
            return false;
        }
    }
    // END 2022/10/28 [QC#60399,ADD]
}
