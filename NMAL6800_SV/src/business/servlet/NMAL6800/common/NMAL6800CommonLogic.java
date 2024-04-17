package business.servlet.NMAL6800.common;

import static business.servlet.NMAL6800.constant.NMAL6800Constant.CMN_BTN10;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.CMN_BTN8;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.SCREEN_ID;
import static business.servlet.NMAL6800.constant.NMAL6800Constant.SEARCH_BTN;
import business.servlet.NMAL6800.NMAL6800BMsg;
import business.servlet.NMAL6800.NMAL6800_PBMsgArray;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

/**
 *<pre>
 * NWAL6800 Common Logic
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         E.Yoshitake     Create          N/A
 *</pre>
 */
public class NMAL6800CommonLogic {

    public static void setPage(NMAL6800BMsg scrnMsg, int page) {
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_10.setValue(page);
        scrnMsg.xxPageShowToNum_10.clear();
        scrnMsg.xxPageShowOfNum_10.clear();
    }

    public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NMAL6800BMsg scrnMsg) {

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
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);
        handler.setButtonEnabled(SEARCH_BTN[1], true);
        // Has Table data
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
                scrnMsg.A.no(i).mdseItemMnfNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).upcCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseItemActvDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).prchGrpNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdsePrcGrpNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescLongTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).slsMatGrpDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).slsMatGrpDescTxt_A2.setInputProtected(true);
                scrnMsg.A.no(i).slsMatGrpDescTxt_A3.setInputProtected(true);
                scrnMsg.A.no(i).dsCmsnGrpDescTxt_A1.setInputProtected(true);
            }
        }
    }

    public static Object[] toArray_popup(NMAL6800_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }

    public static Object[] setParamForNMAL6040(NMAL6800BMsg scrnMsg) {

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
     * @param scrnMsg NMAL6800BMsg
     */
    public static void commonAddCheckItem(NMAL6800BMsg scrnMsg) {

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

    public static String setSelectForMaterialGroup(NMAL6800BMsg scrnMsg, String glblCmpyCd, String slsMatGrpSqNum) {

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
    
    public static List<Object[]> setTblColumnListForMaterialGroup(NMAL6800BMsg scrnMsg) {

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

    public static List<Object[]> setSortListForMaterialGroup(NMAL6800BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "SLS_MAT_GRP_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForCommisionGroup(NMAL6800BMsg scrnMsg, String glblCmpyCd) {

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
    
    public static List<Object[]> setTblColumnListForCommisionGroup(NMAL6800BMsg scrnMsg) {

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
    
    public static List<Object[]> setSortListForCommisionGroup(NMAL6800BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "DS_CMSN_GRP_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }

    public static String setSelectForProdCtrl(String glblCmpyCd, String mdseStruElmntTpCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    PC.EZCANCELFLAG ");
        sb.append("   ,PC.GLBL_CMPY_CD ");
        sb.append("   ,PC.PROD_CTRL_CD ");
        sb.append("   ,PC.PROD_CTRL_DESC_TXT ");
        sb.append("   ,PC.PROD_CTRL_SORT_NUM ");
        if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
            sb.append("   ,PC.SCD_PROD_HRCH_CD ");
        }
        sb.append("FROM ");
        sb.append("    PROD_CTRL PC ");
        sb.append("WHERE ");
        sb.append("    PC.EZCANCELFLAG = '0' ");
        sb.append("AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(mdseStruElmntTpCd).append("' ");

        return sb.toString();
    }
    
    public static List<Object[]> setWhereListForProdCtrl(String prodCtrlDescTxt, String mdseStruElmntTpCd) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(mdseStruElmntTpCd)) {
            whereArray0[0] = "Product Level 1";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(mdseStruElmntTpCd)) {
            whereArray0[0] = "Product Level 2";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(mdseStruElmntTpCd)) {
            whereArray0[0] = "Product Level 3";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
            whereArray0[0] = "Product Level 4";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(mdseStruElmntTpCd)) {
            whereArray0[0] = "Product Level 5";
        }

        whereArray0[1] = "PROD_CTRL_CD";
        whereArray0[2] = "";
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "PROD_CTRL_DESC_TXT";
        if (ZYPCommonFunc.hasValue(prodCtrlDescTxt)) {
            whereArray1[2] = prodCtrlDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);


        Object[] whereArray2 = new Object[4];

        if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
            whereArray2[0] = "Product Level 3";
            whereArray2[1] = "SCD_PROD_HRCH_CD";
            whereArray2[2] = "";
            whereArray2[3] = FLG_OFF_N;
            whereList.add(whereArray2);
        }

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForProdCtrl(String mdseStruElmntTpCd) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(mdseStruElmntTpCd)) {
            columnArray0[0] = "Product Level 1";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(mdseStruElmntTpCd)) {
            columnArray0[0] = "Product Level 2";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(mdseStruElmntTpCd)) {
            columnArray0[0] = "Product Level 3";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
            columnArray0[0] = "Product Level 4";
        } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(mdseStruElmntTpCd)) {
            columnArray0[0] = "Product Level 5";
        }
        columnArray0[1] = "PROD_CTRL_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "PROD_CTRL_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForProdCtrl() {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "PROD_CTRL_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
}
