/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import business.db.MDSETMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.servlet.NMAL7420.NMAL7420BMsg;
import business.servlet.NMAL7420.constant.NMAL7420Constant;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/09   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/11/10   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569
 * 2018/07/19   Fujitsu         W.Honda         Update          QC#20267
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 *</pre>
 */
public class NMAL7420CommonLogic implements NMAL7420Constant {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * @param scrnMsg
     */
    public static void scrnProtect(NMAL7420BMsg scrnMsg) {
        scrnMsg.prcRuleHdrPk_H1.setInputProtected(true);
        scrnMsg.prcRuleNm_H1.setInputProtected(true);
        scrnMsg.csmpNumCmntTxt_04.setInputProtected(true);
        scrnMsg.prodCtrlNm_06.setInputProtected(true);
        scrnMsg.coaMdseTpDescTxt_08.setInputProtected(true);
        scrnMsg.coaProdDescTxt_09.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_11.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt_12.setInputProtected(true);
        scrnMsg.lineBizTpDescTxt_13.setInputProtected(true);
        scrnMsg.prcGrpNm_14.setInputProtected(true);
        scrnMsg.coaChDescTxt_17.setInputProtected(true);
        scrnMsg.dsAcctClsDescTxt_18.setInputProtected(true);
        scrnMsg.svcCallTpDescTxt_20.setInputProtected(true);
        scrnMsg.dsOrdLineCatgDescTxt_25.setInputProtected(true);
        scrnMsg.mktMdlDescTxt_27.setInputProtected(true);
        scrnMsg.mktMdlSegDescTxt_28.setInputProtected(true);
        scrnMsg.cpoSrcTpDescTxt_29.setInputProtected(true);
        scrnMsg.dsPmtMethDescTxt_31.setInputProtected(true);
        scrnMsg.rtrnRsnDescTxt_38.setInputProtected(true);
        scrnMsg.prodCtrlNm_34.setInputProtected(true);
        scrnMsg.prodCtrlNm_35.setInputProtected(true);
        scrnMsg.prodCtrlNm_36.setInputProtected(true);
        scrnMsg.shpgSvcLvlDescTxt_39.setInputProtected(true);
        scrnMsg.mdlNm_40.setInputProtected(true);
        scrnMsg.prcSvcZoneDescTxt_41.setInputProtected(true);
        scrnMsg.dsAcctClsDescTxt_42.setInputProtected(true);
        scrnMsg.spclHdlgTpDescTxt_44.setInputProtected(true);
        scrnMsg.coaExtnDescTxt_46.setInputProtected(true);
        scrnMsg.frtCondDescTxt_48.setInputProtected(true);
        scrnMsg.fill40Txt_49.setInputProtected(true);
        scrnMsg.prcGrpNm_53.setInputProtected(true);
        scrnMsg.coaChDescTxt_55.setInputProtected(true);
        scrnMsg.dsAcctClsDescTxt_56.setInputProtected(true);
        scrnMsg.prcGrpNm_57.setInputProtected(true); // QC#20249 2017/11/10 Add
        // 2018/04/19 QC#22569 add start
        scrnMsg.slsMatGrpDescTxt_59.setInputProtected(true);
        scrnMsg.slsMatGrpDescTxt_60.setInputProtected(true);
        scrnMsg.slsMatGrpDescTxt_61.setInputProtected(true);
        // 2018/04/19 QC#22569 add end
        scrnMsg.prcFmlaNm.setInputProtected(true);

    }

    /**
     * @param scrnMsg
     * @param arg
     */
    public static void setInputParam(NMAL7420BMsg scrnMsg, Object[] arg) {
        scrnMsg.clear();

        if (!(arg instanceof Object[]) || arg.length < 3) {
            return;
        }

        StringBuilder sb = new StringBuilder(getString(arg[2]));
        sb.append(CMN_VISIBLE_ATTRB);
        createExVisibleAttrb(sb, "BH15");
        createExVisibleAttrb(sb, "BH21");
        createExVisibleAttrb(sb, "BH22");
        createExVisibleAttrb(sb, "BH24");
        createExVisibleAttrb(sb, "BH26");
        createExVisibleAttrb(sb, "BH30");
        createExVisibleAttrb(sb, "BH33");
        createExVisibleAttrb(sb, "BH37");
        createExVisibleAttrb(sb, "BH45");
        createExVisibleAttrb(sb, "BH58");  // QC#20249 2017/11/10 Add

        ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleHdrPk_H1, getBigDecimal(arg[0]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleNm_H1, getString(arg[1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnColOrdTxt, sb.toString());

        // QC20968 add Start
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_04, arg[FILTER_CSMP_NUM]);
        setValueFromStringParam(scrnMsg.csmpNumCmntTxt_04, arg[FILTER_CSMP_COMMENT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_05, arg[FILTER_GRP_PK]);
        setValueFromStringParam(scrnMsg.prcGrpNm_05, arg[FILTER_GRP_NM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_06, arg[FILTER_PROD_CTRL_1]);
        setValueFromStringParam(scrnMsg.prodCtrlNm_06, arg[FILTER_PROD_CTRL_1_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_07, arg[FILTER_PROD_CTRL_2]);
        setValueFromStringParam(scrnMsg.prodCtrlNm_07, arg[FILTER_PROD_CTRL_2_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_08, arg[FILTER_COA_MDSE_TP]);
        setValueFromStringParam(scrnMsg.coaMdseTpDescTxt_08, arg[FILTER_COA_MDSE_TP_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_09, arg[FILTER_COA_PROD]);
        setValueFromStringParam(scrnMsg.coaProdDescTxt_09, arg[FILTER_COA_PROD_DESC_TXT]);
        // Mod Start 2018/12/04 QC#29321
        //setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_10, arg[FILTER_MDSE_CD]);
        //setValueFromStringParam(scrnMsg.mdseDescShortTxt_10, arg[FILTER_MDSE_SHORT_TXT]);
        //setValueFromStringParam(scrnMsg.mnfItemCd_10, arg[FILTER_MNF_ITEM_CD]); // QC#20267 2018/07/20 Add
        setValueFromStringParam(scrnMsg.xxPrcQlfyValSrchTxt_J1, arg[FILTER_MDSE_CD]);
        setValueFromStringParam(scrnMsg.xxPrcQlfyValSrchTxt_J2, arg[FILTER_MDSE_SHORT_TXT]);
        setValueFromStringParam(scrnMsg.xxPrcQlfyValSrchTxt_J3, arg[FILTER_MNF_ITEM_CD]);
        // Mod End 2018/12/04 QC#29321
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_11, arg[FILTER_DS_ORD_CATG]);
        setValueFromStringParam(scrnMsg.dsOrdCatgDescTxt_11, arg[FILTER_DS_ORD_CATG_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_12, arg[FILTER_DS_ORD_TP]);
        setValueFromStringParam(scrnMsg.dsOrdTpDescTxt_12, arg[FILTER_DS_ORD_TP_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_13, arg[FILTER_LINE_BIZ_TP]);
        setValueFromStringParam(scrnMsg.lineBizTpDescTxt_13, arg[FILTER_LINE_BIZ_TP_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_14, arg[FILTER_PRC_GRP_TRT]);
        setValueFromStringParam(scrnMsg.prcGrpNm_14, arg[FILTER_PRC_GRP_TRT_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_AF, arg[FILTER_TOTAL_TRX_WEIGHT_FROM_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_AT, arg[FILTER_TOTAL_TRX_WEIGHT_FROM_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_AF, arg[FILTER_TOTAL_TRX_WEIGHT_TO_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_AT, arg[FILTER_TOTAL_TRX_WEIGHT_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_16, arg[FILTER_BILL_TO_ACCT]);
        setValueFromStringParam(scrnMsg.billToAcctNm_16, arg[FILTER_BILL_TO_ACCT_NM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_17, arg[FILTER_COA_CH]);
        setValueFromStringParam(scrnMsg.coaChDescTxt_17, arg[FILTER_COA_CH_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_18, arg[FILTER_DS_ACCT_CLS]);
        setValueFromStringParam(scrnMsg.dsAcctClsDescTxt_18, arg[FILTER_DS_ACCT_CLS_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_19, arg[FILTER_COA_BR]);
        setValueFromStringParam(scrnMsg.coaBrDescTxt_19, arg[FILTER_COA_BR_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_20, arg[FILTER_SVC_CALL_TP]);
        setValueFromStringParam(scrnMsg.svcCallTpDescTxt_20, arg[FILTER_SVC_CALL_TP_DESC_TXT]);
        setValueFromDateParam(scrnMsg.xxSvcCallDt_FF, arg[FILTER_CALL_DATE_FROM_FROM]);
        setValueFromDateParam(scrnMsg.xxSvcCallDt_FT, arg[FILTER_CALL_DATET_FROM_TO]);
        setValueFromDateParam(scrnMsg.xxSvcCallDt_TF, arg[FILTER_CALL_DATE_TO_FROM]);
        setValueFromDateParam(scrnMsg.xxSvcCallDt_TT, arg[FILTER_CALL_DATE_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_BF, arg[FILTER_CUST_PO_FROM_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_BT, arg[FILTER_CUST_PO_FROM_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_BF, arg[FILTER_CUST_PO_TO_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_BT, arg[FILTER_CUST_PO_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_CF, arg[FILTER_LINE_AMT_FROM_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_CT, arg[FILTER_LINE_AMT_FROM_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_CF, arg[FILTER_LINE_AMT_TO_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_CT, arg[FILTER_LINE_AMT_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_25, arg[FILTER_DS_ORD_LINE_CATG]);
        setValueFromStringParam(scrnMsg.dsOrdLineCatgDescTxt_25, arg[FILTER_DS_ORD_LINE_CATG_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_DF, arg[FILTER_LINE_QTY_FROM_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_DT, arg[FILTER_LINE_QTY_FROM_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_DF, arg[FILTER_LINE_QTY_TO_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_DT, arg[FILTER_LINE_QTY_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_27, arg[FILTER_MKT_MDL]);
        setValueFromStringParam(scrnMsg.mktMdlDescTxt_27, arg[FILTER_MKT_MDL_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_28, arg[FILTER_MKT_MDL_SEG]);
        setValueFromStringParam(scrnMsg.mktMdlSegDescTxt_28, arg[FILTER_MKT_MDL_SEG_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_29, arg[FILTER_CPO_SRC_TP]);
        setValueFromStringParam(scrnMsg.cpoSrcTpDescTxt_29, arg[FILTER_CPO_SRC_TP_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_EF, arg[FILTER_ORD_SUB_TOT_FROM_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_ET, arg[FILTER_ORD_SUB_TOT_FROM_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_EF, arg[FILTER_ORD_SUB_TOT_TO_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_ET, arg[FILTER_ORD_SUB_TOT_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_31, arg[FILTER_DS_PMT_METH]);
        setValueFromStringParam(scrnMsg.dsPmtMethDescTxt_31, arg[FILTER_DS_PMT_METH_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_32, arg[FILTER_PRC_LIST]);
        setValueFromStringParam(scrnMsg.prcCatgNm_32, arg[FILTER_PRC_LIST_TXT]);
        setValueFromDateParam(scrnMsg.prcDt_FF, arg[FILTER_PRC_DATE_FROM_FROM]);
        setValueFromDateParam(scrnMsg.prcDt_FT, arg[FILTER_PRC_DATE_FROM_TO]);
        setValueFromDateParam(scrnMsg.prcDt_TF, arg[FILTER_PRC_DATE_TO_FROM]);
        setValueFromDateParam(scrnMsg.prcDt_TT, arg[FILTER_PRC_DATE_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_34, arg[FILTER_PROD_CTRL_3]);
        setValueFromStringParam(scrnMsg.prodCtrlNm_34, arg[FILTER_PROD_CTRL_3_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_35, arg[FILTER_PROD_CTRL_4]);
        setValueFromStringParam(scrnMsg.prodCtrlNm_35, arg[FILTER_PROD_CTRL_4_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_36, arg[FILTER_PROD_CTRL_5]);
        setValueFromStringParam(scrnMsg.prodCtrlNm_36, arg[FILTER_PROD_CTRL_5_TXT]);
        setValueFromDateParam(scrnMsg.xxRqstDt_FF, arg[FILTER_REQ_DATE_FROM_FROM]);
        setValueFromDateParam(scrnMsg.xxRqstDt_FT, arg[FILTER_REQ_DATE_TOT_FROM_TO]);
        setValueFromDateParam(scrnMsg.xxRqstDt_TF, arg[FILTER_REQ_DATE_TO_FROM]);
        setValueFromDateParam(scrnMsg.xxRqstDt_TT, arg[FILTER_REQ_DATE_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_38, arg[FILTER_RTRN_RSN]);
        setValueFromStringParam(scrnMsg.rtrnRsnDescTxt_38, arg[FILTER_RTRN_RSN_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_39, arg[FILTER_SHPG_SVC_LVL]);
        setValueFromStringParam(scrnMsg.shpgSvcLvlDescTxt_39, arg[FILTER_SHPG_SVC_LVL_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_40, arg[FILTER_MDL]);
        setValueFromStringParam(scrnMsg.mdlNm_40, arg[FILTER_MDL_NM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_41, arg[FILTER_PRC_SVC_ZONE]);
        setValueFromStringParam(scrnMsg.prcSvcZoneDescTxt_41, arg[FILTER_PRC_SVC_ZONE_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_42, arg[FILTER_DS_ACCT_CLS_BILL]);
        setValueFromStringParam(scrnMsg.dsAcctClsDescTxt_42, arg[FILTER_DS_ACCT_CLS_BILL_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_44, arg[FILTER_SPCL_HDLG_TP]);
        setValueFromStringParam(scrnMsg.spclHdlgTpDescTxt_44, arg[FILTER_SPCL_HDLG_TP_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_FF, arg[FILTER_TOT_QTY_FROM_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_FT, arg[FILTER_TOT_QTY_FROM_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_FF, arg[FILTER_TOT_QTY_TO_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_FT, arg[FILTER_TOT_QTY_TO_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_46, arg[FILTER_COA_EXTN]);
        setValueFromStringParam(scrnMsg.coaExtnDescTxt_46, arg[FILTER_COA_EXTN_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_48, arg[FILTER_FRT_COND]);
        setValueFromStringParam(scrnMsg.frtCondDescTxt_48, arg[FILTER_FRT_COND_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_49, arg[FILTER_FREIGHT_ZONE]);
        setValueFromStringParam(scrnMsg.fill40Txt_49, arg[FILTER_FREIGHT_ZONE_TXT]);
        // Mod Start 2019/01/08 QC#29751
        //setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_53, arg[FILTER_PRC_GRP_CUST_SOLD]);
        setValueFromStringParam(scrnMsg.xxPrcQlfyValSrchTxt_53, arg[FILTER_PRC_GRP_CUST_SOLD]);
        // Mod End 2019/01/08 QC#29751
        setValueFromStringParam(scrnMsg.prcGrpNm_53, arg[FILTER_PRC_GRP_CUST_SOLD_TXT]);
        // Mod Start 2018/12/04 QC#29321
        //setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_54, arg[FILTER_DS_ACCT]);
        //setValueFromStringParam(scrnMsg.dsAcctNm_54, arg[FILTER_DS_ACCT_NM]);
        setValueFromStringParam(scrnMsg.xxPrcQlfyValSrchTxt_K1, arg[FILTER_DS_ACCT]);
        setValueFromStringParam(scrnMsg.xxPrcQlfyValSrchTxt_K2, arg[FILTER_DS_ACCT_NM]);
        // Mod End 2018/12/04 QC#29321
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_55, arg[FILTER_PRC_RULE_COND]);
        setValueFromStringParam(scrnMsg.coaChDescTxt_55, arg[FILTER_PRC_RULE_COND_FROM_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_56, arg[FILTER_ACCT_CLS]);
        setValueFromStringParam(scrnMsg.dsAcctClsDescTxt_56, arg[FILTER_ACCT_CLS_DESC_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_57, arg[FILTER_GRP_QTYBRK_PK]);
        setValueFromStringParam(scrnMsg.prcGrpNm_57, arg[FILTER_GRP_QTYBRK_NM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_GF, arg[FILTER_LINEQTY_QTYBRK_FROM_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_GT, arg[FILTER_LINEQTY_QTYBRK_FROM_TO]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_GF, arg[FILTER_LINEQTY_QTYBRK_TO_FROM]);
        setValueFromStringParam(scrnMsg.prcRuleCondThruTxt_GT, arg[FILTER_LINEQTY_QTYBRK_TO_TO]);
        // 2018/04/19 QC#22569 add start
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_59, arg[FILTER_MAT_GRP_1]);
        setValueFromStringParam(scrnMsg.slsMatGrpDescTxt_59, arg[FILTER_MAT_GRP_1_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_60, arg[FILTER_MAT_GRP_2]);
        setValueFromStringParam(scrnMsg.slsMatGrpDescTxt_60, arg[FILTER_MAT_GRP_2_TXT]);
        setValueFromStringParam(scrnMsg.prcRuleCondFromTxt_61, arg[FILTER_MAT_GRP_3]);
        setValueFromStringParam(scrnMsg.slsMatGrpDescTxt_61, arg[FILTER_MAT_GRP_3_TXT]);
        // 2018/04/19 QC#22569 add end
        setValueFromBigDecimalParam(scrnMsg.prcFmlaPk, arg[FILTER_FORMULA]);
        setValueFromBigDecimalParam(scrnMsg.prcRuleDtlRate, arg[FILTER_PERCENT]);
        setValueFromStringParam(scrnMsg.prcRuleDtlTxt, arg[FILTER_VALUE]);
        setValueFromStringParam(scrnMsg.prcFmlaNm, arg[FILTER_FORMULA_TXT]);
        setValueFromDateParam(scrnMsg.effFromDt_H1, arg[FILTER_EFFECT_DATE_FROM_FROM]);
        setValueFromDateParam(scrnMsg.effFromDt_H2, arg[FILTER_EFFECT_DATE_FROM_TO]);
        setValueFromDateParam(scrnMsg.effThruDt_H1, arg[FILTER_EFFECT_DATE_TO_FROM]);
        setValueFromDateParam(scrnMsg.effThruDt_H2, arg[FILTER_EFFECT_DATE_TO_TO]);

        setValueFromStringParam(scrnMsg.xxFullNm_H1, arg[FILTER_CREATE_BY]);
        setValueFromDateParam(scrnMsg.cratDt_H1, arg[FILTER_CREATE_DATE_FROM]);
        setValueFromDateParam(scrnMsg.cratDt_H2, arg[FILTER_CREATE_DATE_TO]);
        setValueFromStringParam(scrnMsg.xxFullNm_H2, arg[FILTER_UPDATE_BY]);
        setValueFromDateParam(scrnMsg.lastUpdDt_H1, arg[FILTER_UPDATE_DATE_FROM]);
        setValueFromDateParam(scrnMsg.lastUpdDt_H2, arg[FILTER_UPDATE_DATE_TO]);
        // QC20968 add End
    }
    // QC20968 add Start
    private static void setValueFromStringParam(EZDBStringItem targetItem, Object param) {

        if (param != null && ZYPCommonFunc.hasValue((EZDBStringItem) param)) {
            ZYPEZDItemValueSetter.setValue(targetItem, (EZDBStringItem) param);
        }
    }
    private static void setValueFromDateParam(EZDBDateItem targetItem, Object param) {

        if (param != null && ZYPCommonFunc.hasValue((EZDBDateItem) param)) {
            ZYPEZDItemValueSetter.setValue(targetItem, (EZDBDateItem) param);
        }
    }
    private static void setValueFromBigDecimalParam(EZDBBigDecimalItem targetItem, Object param) {

        if (param != null && ZYPCommonFunc.hasValue((EZDBBigDecimalItem) param)) {
            ZYPEZDItemValueSetter.setValue(targetItem, (EZDBBigDecimalItem) param);
        }
    }
    // QC20968 add End

    /**
     * @param sb
     * @param str
     */
    private static void createExVisibleAttrb(StringBuilder sb, String str) {
        if (sb.indexOf(str) != -1) {
            sb.append(":" + str + "-2");
        }
    }

    /**
     * @param obj
     * @return
     */
    private static String getString(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBStringItem) obj).getValue();
    }

    /**
     * @param obj
     * @return
     */
    private static BigDecimal getBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBBigDecimalItem) obj).getValue();
    }

    /**
     * @param obj
     * @param val
     */
    private static void setValue(Object obj, String val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) obj, val);
    }

    /**
     * @param obj
     * @param val
     */
    private static void setDecimalValue(Object obj, BigDecimal val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) obj, val);
    }

    /**
     * @param obj
     * @param val
     */
    private static void setDateValue(Object obj, String val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBDateItem) obj, val);
    }

    /**
     * @param scrnMsg
     * @param code
     * @param desc
     * @param evNm
     * @param prcRuleAttrbCd
     * @return
     */
    public static Object[] getPopParamFor6050(NMAL7420BMsg scrnMsg, EZDBStringItem code, EZDBStringItem desc, String evNm, String prcRuleAttrbCd) {

        List<EZDBStringItem> prmList = new ArrayList<EZDBStringItem>();
        for (int i = 0; i < scrnMsg.P.length(); i++) {
            prmList.add(scrnMsg.P.no(i).xxPopPrm);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, evNm);

        return (NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor6050(prcRuleAttrbCd, code, desc, prmList));
    }

    /**
     * @param scrnMsg
     * @param code
     * @param desc
     * @param evNm
     * @param prcRuleAttrbCd
     * @param glblCmpyCd
     * @return
     */
    public static Object[] getPopParamFor1130(NMAL7420BMsg scrnMsg, EZDBStringItem code, EZDBStringItem desc, String evNm, String prcRuleAttrbCd, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, evNm);
        return NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor1130(prcRuleAttrbCd, code, desc, glblCmpyCd, scrnMsg.R);
    }

    /**
     * @param scrnMsg
     * @param code
     * @param desc
     * @param evNm
     * @param prcRuleAttrbCd
     * @param glblCmpyCd
     * @return
     */
    public static Object[] getPopParamFor1130(NMAL7420BMsg scrnMsg, EZDBBigDecimalItem code, EZDBStringItem desc, String evNm, String prcRuleAttrbCd, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, evNm);
        return NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor1130(prcRuleAttrbCd, code, desc, glblCmpyCd, scrnMsg.R);
    }

    /**
     * @param scrnMsg
     * @param code
     * @param desc
     * @param prcRuleAttrbCd
     * @return
     */
    public static Object[] getPopParamFor6760(NMAL7420BMsg scrnMsg, EZDBStringItem code, EZDBStringItem desc, String prcRuleAttrbCd) {

        ZYPTableUtil.clear(scrnMsg.P);

        // Add Start 2018/12/04 QC#29321
        SELL_TO_CUSTTMsg sellToCustTmsg = new SELL_TO_CUSTTMsg();
        int sellToCustCdLength = sellToCustTmsg.getAttr("sellToCustCd").getDigit();
        // Add End 2018/12/04 QC#29321

        Object[] params = new Object[15];

        // Mod Start 2018/12/04 QC#29321
        //params[0] = code;
        //params[1] = desc;
        if (ZYPCommonFunc.hasValue(code) && //
                sellToCustCdLength < code.getValue().length()) {
            params[0] = scrnMsg.P.no(13).xxPopPrm;
        } else {
            params[0] = code;
        }

        if (ZYPCommonFunc.hasValue(desc) && //
                MAX_LENGTH_PARAMETER < desc.getValue().length()) {
            params[1] = scrnMsg.P.no(14).xxPopPrm;
        } else {
            params[1] = desc;
        }
        // Mod End 2018/12/04 QC#29321

        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, prcRuleAttrbCd);
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(0).xxPopPrm;
        params[14] = scrnMsg.P.no(1).xxPopPrm;

        return params;
    }

    /**
     * @param scrnMsg
     * @param code
     * @param desc
     * @param prcRuleAttrbCd
     * @return
     */
    public static Object[] getPopParamFor6800(NMAL7420BMsg scrnMsg, EZDBStringItem code, EZDBStringItem desc, String prcRuleAttrbCd) {

        ZYPTableUtil.clear(scrnMsg.P);

        // Add Start 2018/12/04 QC#29321
        MDSETMsg mdseTmsg = new MDSETMsg();
        int mdseCdLength = mdseTmsg.getAttr("mdseCd").getDigit();
        // Add End 2018/12/04 QC#29321

        Object[] params = new Object[10];

        // Mod Start 2018/12/04 QC#29321
        //params[0] = code;
        //params[1] = desc;
        if (ZYPCommonFunc.hasValue(code) && //
                mdseCdLength < code.getValue().length()) {
            params[0] = scrnMsg.P.no(8).xxPopPrm;
        } else {
            params[0] = code;
        }

        if (ZYPCommonFunc.hasValue(desc) && //
                MAX_LENGTH_PARAMETER < desc.getValue().length()) {
            params[1] = scrnMsg.P.no(9).xxPopPrm;
        } else {
            params[1] = desc;
        }
        // Mod End 2018/12/04 QC#29321

        params[2] = scrnMsg.P.no(0).xxPopPrm;
        params[3] = scrnMsg.P.no(1).xxPopPrm;
        params[4] = scrnMsg.P.no(2).xxPopPrm;
        params[5] = scrnMsg.P.no(3).xxPopPrm;
        params[6] = scrnMsg.P.no(4).xxPopPrm;

        // Mod Start 2018/12/04 QC#29321
        //params[7] = scrnMsg.mdseDescShortTxt_10;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_J2) && //
                MAX_LENGTH_PARAMETER < scrnMsg.xxPrcQlfyValSrchTxt_J2.getValue().length()) {
            params[7] = scrnMsg.P.no(5).xxPopPrm;
        } else {
            params[7] = scrnMsg.xxPrcQlfyValSrchTxt_J2;
        }
        // Mod End 2018/12/04 QC#29321

        params[8] = scrnMsg.P.no(6).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, prcRuleAttrbCd);
        params[9] = scrnMsg.P.no(7).xxPopPrm;

        return params;
    }

    /**
     * @param scrnMsg
     * @param arg
     */
    public static void setOutputParam(NMAL7420BMsg scrnMsg, Object[] arg) {

        setValue(arg[FILTER_CSMP_NUM], scrnMsg.prcRuleCondFromTxt_04.getValue());
        setValue(arg[FILTER_CSMP_COMMENT], scrnMsg.csmpNumCmntTxt_04.getValue());
        setValue(arg[FILTER_GRP_PK], scrnMsg.prcRuleCondFromTxt_05.getValue());
        setValue(arg[FILTER_GRP_NM], scrnMsg.prcGrpNm_05.getValue());
        setValue(arg[FILTER_PROD_CTRL_1], scrnMsg.prcRuleCondFromTxt_06.getValue());
        setValue(arg[FILTER_PROD_CTRL_1_TXT], scrnMsg.prodCtrlNm_06.getValue());
        setValue(arg[FILTER_PROD_CTRL_2], scrnMsg.prcRuleCondFromTxt_07.getValue());
        setValue(arg[FILTER_PROD_CTRL_2_TXT], scrnMsg.prodCtrlNm_07.getValue());
        setValue(arg[FILTER_COA_MDSE_TP], scrnMsg.prcRuleCondFromTxt_08.getValue());
        setValue(arg[FILTER_COA_MDSE_TP_DESC_TXT], scrnMsg.coaMdseTpDescTxt_08.getValue());
        setValue(arg[FILTER_COA_PROD], scrnMsg.prcRuleCondFromTxt_09.getValue());
        setValue(arg[FILTER_COA_PROD_DESC_TXT], scrnMsg.coaProdDescTxt_09.getValue());
        // Mod Start 2018/12/04 QC#29321
        //setValue(arg[FILTER_MDSE_CD], scrnMsg.prcRuleCondFromTxt_10.getValue());
        //setValue(arg[FILTER_MDSE_SHORT_TXT], scrnMsg.mdseDescShortTxt_10.getValue());
        //setValue(arg[FILTER_MNF_ITEM_CD], scrnMsg.mnfItemCd_10.getValue()); // QC#20267 2018/07/20 Add
        setValue(arg[FILTER_MDSE_CD], scrnMsg.xxPrcQlfyValSrchTxt_J1.getValue());
        setValue(arg[FILTER_MDSE_SHORT_TXT], scrnMsg.xxPrcQlfyValSrchTxt_J2.getValue());
        setValue(arg[FILTER_MNF_ITEM_CD], scrnMsg.xxPrcQlfyValSrchTxt_J3.getValue());
        // Mod End 2018/12/04 QC#29321
        setValue(arg[FILTER_DS_ORD_CATG], scrnMsg.prcRuleCondFromTxt_11.getValue());
        setValue(arg[FILTER_DS_ORD_CATG_DESC_TXT], scrnMsg.dsOrdCatgDescTxt_11.getValue());
        setValue(arg[FILTER_DS_ORD_TP], scrnMsg.prcRuleCondFromTxt_12.getValue());
        setValue(arg[FILTER_DS_ORD_TP_DESC_TXT], scrnMsg.dsOrdTpDescTxt_12.getValue());
        setValue(arg[FILTER_LINE_BIZ_TP], scrnMsg.prcRuleCondFromTxt_13.getValue());
        setValue(arg[FILTER_LINE_BIZ_TP_DESC_TXT], scrnMsg.lineBizTpDescTxt_13.getValue());
        setValue(arg[FILTER_PRC_GRP_TRT], scrnMsg.prcRuleCondFromTxt_14.getValue());
        setValue(arg[FILTER_PRC_GRP_TRT_TXT], scrnMsg.prcGrpNm_14.getValue());
        setValue(arg[FILTER_TOTAL_TRX_WEIGHT_FROM_FROM], scrnMsg.prcRuleCondFromTxt_AF.getValue());
        setValue(arg[FILTER_TOTAL_TRX_WEIGHT_FROM_TO], scrnMsg.prcRuleCondFromTxt_AT.getValue());
        setValue(arg[FILTER_TOTAL_TRX_WEIGHT_TO_FROM], scrnMsg.prcRuleCondThruTxt_AF.getValue());
        setValue(arg[FILTER_TOTAL_TRX_WEIGHT_TO_TO], scrnMsg.prcRuleCondThruTxt_AT.getValue());
        setValue(arg[FILTER_BILL_TO_ACCT], scrnMsg.prcRuleCondFromTxt_16.getValue());
        setValue(arg[FILTER_BILL_TO_ACCT_NM], scrnMsg.billToAcctNm_16.getValue());
        setValue(arg[FILTER_COA_CH], scrnMsg.prcRuleCondFromTxt_17.getValue());
        setValue(arg[FILTER_COA_CH_DESC_TXT], scrnMsg.coaChDescTxt_17.getValue());
        setValue(arg[FILTER_DS_ACCT_CLS], scrnMsg.prcRuleCondFromTxt_18.getValue());
        setValue(arg[FILTER_DS_ACCT_CLS_DESC_TXT], scrnMsg.dsAcctClsDescTxt_18.getValue());
        setValue(arg[FILTER_COA_BR], scrnMsg.prcRuleCondFromTxt_19.getValue());
        setValue(arg[FILTER_COA_BR_DESC_TXT], scrnMsg.coaBrDescTxt_19.getValue());
        setValue(arg[FILTER_SVC_CALL_TP], scrnMsg.prcRuleCondFromTxt_20.getValue());
        setValue(arg[FILTER_SVC_CALL_TP_DESC_TXT], scrnMsg.svcCallTpDescTxt_20.getValue());
        setDateValue(arg[FILTER_CALL_DATE_FROM_FROM], scrnMsg.xxSvcCallDt_FF.getValue());
        setDateValue(arg[FILTER_CALL_DATET_FROM_TO], scrnMsg.xxSvcCallDt_FT.getValue());
        setDateValue(arg[FILTER_CALL_DATE_TO_FROM], scrnMsg.xxSvcCallDt_TF.getValue());
        setDateValue(arg[FILTER_CALL_DATE_TO_TO], scrnMsg.xxSvcCallDt_TT.getValue());
        setValue(arg[FILTER_CUST_PO_FROM_FROM], scrnMsg.prcRuleCondFromTxt_BF.getValue());
        setValue(arg[FILTER_CUST_PO_FROM_TO], scrnMsg.prcRuleCondFromTxt_BT.getValue());
        setValue(arg[FILTER_CUST_PO_TO_FROM], scrnMsg.prcRuleCondThruTxt_BF.getValue());
        setValue(arg[FILTER_CUST_PO_TO_TO], scrnMsg.prcRuleCondThruTxt_BT.getValue());
        setValue(arg[FILTER_LINE_AMT_FROM_FROM], scrnMsg.prcRuleCondFromTxt_CF.getValue());
        setValue(arg[FILTER_LINE_AMT_FROM_TO], scrnMsg.prcRuleCondFromTxt_CT.getValue());
        setValue(arg[FILTER_LINE_AMT_TO_FROM], scrnMsg.prcRuleCondThruTxt_CF.getValue());
        setValue(arg[FILTER_LINE_AMT_TO_TO], scrnMsg.prcRuleCondThruTxt_CT.getValue());
        setValue(arg[FILTER_DS_ORD_LINE_CATG], scrnMsg.prcRuleCondFromTxt_25.getValue());
        setValue(arg[FILTER_DS_ORD_LINE_CATG_DESC_TXT], scrnMsg.dsOrdLineCatgDescTxt_25.getValue());
        setValue(arg[FILTER_LINE_QTY_FROM_FROM], scrnMsg.prcRuleCondFromTxt_DF.getValue());
        setValue(arg[FILTER_LINE_QTY_FROM_TO], scrnMsg.prcRuleCondFromTxt_DT.getValue());
        setValue(arg[FILTER_LINE_QTY_TO_FROM], scrnMsg.prcRuleCondThruTxt_DF.getValue());
        setValue(arg[FILTER_LINE_QTY_TO_TO], scrnMsg.prcRuleCondThruTxt_DT.getValue());
        setValue(arg[FILTER_MKT_MDL], scrnMsg.prcRuleCondFromTxt_27.getValue());
        setValue(arg[FILTER_MKT_MDL_DESC_TXT], scrnMsg.mktMdlDescTxt_27.getValue());
        setValue(arg[FILTER_MKT_MDL_SEG], scrnMsg.prcRuleCondFromTxt_28.getValue());
        setValue(arg[FILTER_MKT_MDL_SEG_DESC_TXT], scrnMsg.mktMdlSegDescTxt_28.getValue());
        setValue(arg[FILTER_CPO_SRC_TP], scrnMsg.prcRuleCondFromTxt_29.getValue());
        setValue(arg[FILTER_CPO_SRC_TP_DESC_TXT], scrnMsg.cpoSrcTpDescTxt_29.getValue());
        setValue(arg[FILTER_ORD_SUB_TOT_FROM_FROM], scrnMsg.prcRuleCondFromTxt_EF.getValue());
        setValue(arg[FILTER_ORD_SUB_TOT_FROM_TO], scrnMsg.prcRuleCondFromTxt_ET.getValue());
        setValue(arg[FILTER_ORD_SUB_TOT_TO_FROM], scrnMsg.prcRuleCondThruTxt_EF.getValue());
        setValue(arg[FILTER_ORD_SUB_TOT_TO_TO], scrnMsg.prcRuleCondThruTxt_ET.getValue());
        setValue(arg[FILTER_DS_PMT_METH], scrnMsg.prcRuleCondFromTxt_31.getValue());
        setValue(arg[FILTER_DS_PMT_METH_DESC_TXT], scrnMsg.dsPmtMethDescTxt_31.getValue());
        setValue(arg[FILTER_PRC_LIST], scrnMsg.prcRuleCondFromTxt_32.getValue());
        setValue(arg[FILTER_PRC_LIST_TXT], scrnMsg.prcCatgNm_32.getValue());
        setDateValue(arg[FILTER_PRC_DATE_FROM_FROM], scrnMsg.prcDt_FF.getValue());
        setDateValue(arg[FILTER_PRC_DATE_FROM_TO], scrnMsg.prcDt_FT.getValue());
        setDateValue(arg[FILTER_PRC_DATE_TO_FROM], scrnMsg.prcDt_TF.getValue());
        setDateValue(arg[FILTER_PRC_DATE_TO_TO], scrnMsg.prcDt_TT.getValue());
        setValue(arg[FILTER_PROD_CTRL_3], scrnMsg.prcRuleCondFromTxt_34.getValue());
        setValue(arg[FILTER_PROD_CTRL_3_TXT], scrnMsg.prodCtrlNm_34.getValue());
        setValue(arg[FILTER_PROD_CTRL_4], scrnMsg.prcRuleCondFromTxt_35.getValue());
        setValue(arg[FILTER_PROD_CTRL_4_TXT], scrnMsg.prodCtrlNm_35.getValue());
        setValue(arg[FILTER_PROD_CTRL_5], scrnMsg.prcRuleCondFromTxt_36.getValue());
        setValue(arg[FILTER_PROD_CTRL_5_TXT], scrnMsg.prodCtrlNm_36.getValue());
        setDateValue(arg[FILTER_REQ_DATE_FROM_FROM], scrnMsg.xxRqstDt_FF.getValue());
        setDateValue(arg[FILTER_REQ_DATE_TOT_FROM_TO], scrnMsg.xxRqstDt_FT.getValue());
        setDateValue(arg[FILTER_REQ_DATE_TO_FROM], scrnMsg.xxRqstDt_TF.getValue());
        setDateValue(arg[FILTER_REQ_DATE_TO_TO], scrnMsg.xxRqstDt_TT.getValue());
        setValue(arg[FILTER_RTRN_RSN], scrnMsg.prcRuleCondFromTxt_38.getValue());
        setValue(arg[FILTER_RTRN_RSN_DESC_TXT], scrnMsg.rtrnRsnDescTxt_38.getValue());
        setValue(arg[FILTER_SHPG_SVC_LVL], scrnMsg.prcRuleCondFromTxt_39.getValue());
        setValue(arg[FILTER_SHPG_SVC_LVL_DESC_TXT], scrnMsg.shpgSvcLvlDescTxt_39.getValue());
        setValue(arg[FILTER_MDL], scrnMsg.prcRuleCondFromTxt_40.getValue());
        setValue(arg[FILTER_MDL_NM], scrnMsg.mdlNm_40.getValue());
        setValue(arg[FILTER_PRC_SVC_ZONE], scrnMsg.prcRuleCondFromTxt_41.getValue());
        setValue(arg[FILTER_PRC_SVC_ZONE_DESC_TXT], scrnMsg.prcSvcZoneDescTxt_41.getValue());
        setValue(arg[FILTER_DS_ACCT_CLS_BILL], scrnMsg.prcRuleCondFromTxt_42.getValue());
        setValue(arg[FILTER_DS_ACCT_CLS_BILL_DESC_TXT], scrnMsg.dsAcctClsDescTxt_42.getValue());
        setValue(arg[FILTER_SPCL_HDLG_TP], scrnMsg.prcRuleCondFromTxt_44.getValue());
        setValue(arg[FILTER_SPCL_HDLG_TP_DESC_TXT], scrnMsg.spclHdlgTpDescTxt_44.getValue());
        setValue(arg[FILTER_TOT_QTY_FROM_FROM], scrnMsg.prcRuleCondFromTxt_FF.getValue());
        setValue(arg[FILTER_TOT_QTY_FROM_TO], scrnMsg.prcRuleCondFromTxt_FT.getValue());
        setValue(arg[FILTER_TOT_QTY_TO_FROM], scrnMsg.prcRuleCondThruTxt_FF.getValue());
        setValue(arg[FILTER_TOT_QTY_TO_TO], scrnMsg.prcRuleCondThruTxt_FT.getValue());
        setValue(arg[FILTER_COA_EXTN], scrnMsg.prcRuleCondFromTxt_46.getValue());
        setValue(arg[FILTER_COA_EXTN_DESC_TXT], scrnMsg.coaExtnDescTxt_46.getValue());
        setValue(arg[FILTER_FRT_COND], scrnMsg.prcRuleCondFromTxt_48.getValue());
        setValue(arg[FILTER_FRT_COND_DESC_TXT], scrnMsg.frtCondDescTxt_48.getValue());
        setValue(arg[FILTER_FREIGHT_ZONE], scrnMsg.prcRuleCondFromTxt_49.getValue());
        setValue(arg[FILTER_FREIGHT_ZONE_TXT], scrnMsg.fill40Txt_49.getValue());
        // Mod Start 2019/01/08 QC#29751
        //setValue(arg[FILTER_PRC_GRP_CUST_SOLD], scrnMsg.prcRuleCondFromTxt_53.getValue());
        setValue(arg[FILTER_PRC_GRP_CUST_SOLD], scrnMsg.xxPrcQlfyValSrchTxt_53.getValue());
        // Mod End 2019/01/08 QC#29751
        setValue(arg[FILTER_PRC_GRP_CUST_SOLD_TXT], scrnMsg.prcGrpNm_53.getValue());
        // Mod Start 2018/12/04 QC#29321
        //setValue(arg[FILTER_DS_ACCT], scrnMsg.prcRuleCondFromTxt_54.getValue());
        //setValue(arg[FILTER_DS_ACCT_NM], scrnMsg.dsAcctNm_54.getValue());
        setValue(arg[FILTER_DS_ACCT], scrnMsg.xxPrcQlfyValSrchTxt_K1.getValue());
        setValue(arg[FILTER_DS_ACCT_NM], scrnMsg.xxPrcQlfyValSrchTxt_K2.getValue());
        // Mod End 2018/12/04 QC#29321
        setValue(arg[FILTER_PRC_RULE_COND], scrnMsg.prcRuleCondFromTxt_55.getValue());
        setValue(arg[FILTER_PRC_RULE_COND_FROM_TXT], scrnMsg.coaChDescTxt_55.getValue());
        setValue(arg[FILTER_ACCT_CLS], scrnMsg.prcRuleCondFromTxt_56.getValue());
        setValue(arg[FILTER_ACCT_CLS_DESC_TXT], scrnMsg.dsAcctClsDescTxt_56.getValue());
        // QC#20249 2017/11/10 Add Start
        setValue(arg[FILTER_GRP_QTYBRK_PK], scrnMsg.prcRuleCondFromTxt_57.getValue());
        setValue(arg[FILTER_GRP_QTYBRK_NM], scrnMsg.prcGrpNm_57.getValue());
        setValue(arg[FILTER_LINEQTY_QTYBRK_FROM_FROM], scrnMsg.prcRuleCondFromTxt_GF.getValue());
        setValue(arg[FILTER_LINEQTY_QTYBRK_FROM_TO], scrnMsg.prcRuleCondFromTxt_GT.getValue());
        setValue(arg[FILTER_LINEQTY_QTYBRK_TO_FROM], scrnMsg.prcRuleCondThruTxt_GF.getValue());
        setValue(arg[FILTER_LINEQTY_QTYBRK_TO_TO], scrnMsg.prcRuleCondThruTxt_GT.getValue());
        // QC#20249 2017/11/10 Add End
        // 2018/04/19 QC#22569 add start
        setValue(arg[FILTER_MAT_GRP_1], scrnMsg.prcRuleCondFromTxt_59.getValue());
        setValue(arg[FILTER_MAT_GRP_1_TXT], scrnMsg.slsMatGrpDescTxt_59.getValue());
        setValue(arg[FILTER_MAT_GRP_2], scrnMsg.prcRuleCondFromTxt_60.getValue());
        setValue(arg[FILTER_MAT_GRP_2_TXT], scrnMsg.slsMatGrpDescTxt_60.getValue());
        setValue(arg[FILTER_MAT_GRP_3], scrnMsg.prcRuleCondFromTxt_61.getValue());
        setValue(arg[FILTER_MAT_GRP_3_TXT], scrnMsg.slsMatGrpDescTxt_61.getValue());
        // 2018/04/19 QC#22569 add end
        setDecimalValue(arg[FILTER_FORMULA], scrnMsg.prcFmlaPk.getValue());
        setDecimalValue(arg[FILTER_PERCENT], scrnMsg.prcRuleDtlRate.getValue());
        setValue(arg[FILTER_VALUE], scrnMsg.prcRuleDtlTxt.getValue());
        setValue(arg[FILTER_FORMULA_TXT], scrnMsg.prcFmlaNm.getValue());
        setDateValue(arg[FILTER_EFFECT_DATE_FROM_FROM], scrnMsg.effFromDt_H1.getValue());
        setDateValue(arg[FILTER_EFFECT_DATE_FROM_TO], scrnMsg.effFromDt_H2.getValue());
        setDateValue(arg[FILTER_EFFECT_DATE_TO_FROM], scrnMsg.effThruDt_H1.getValue());
        setDateValue(arg[FILTER_EFFECT_DATE_TO_TO], scrnMsg.effThruDt_H2.getValue());
        setValue(arg[FILTER_CREATE_BY], scrnMsg.xxFullNm_H1.getValue());
        setDateValue(arg[FILTER_CREATE_DATE_FROM], scrnMsg.cratDt_H1.getValue());
        setDateValue(arg[FILTER_CREATE_DATE_TO], scrnMsg.cratDt_H2.getValue());
        setValue(arg[FILTER_UPDATE_BY], scrnMsg.xxFullNm_H2.getValue());
        setDateValue(arg[FILTER_UPDATE_DATE_FROM], scrnMsg.lastUpdDt_H1.getValue());
        setDateValue(arg[FILTER_UPDATE_DATE_TO], scrnMsg.lastUpdDt_H2.getValue());

    }

}
