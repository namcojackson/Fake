/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000.common;

import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_APL;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_APR;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_RST;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.CMN_PRM_WHERE_NUM;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.servlet.NMAL7000.NMAL7000BMsg;
import business.servlet.NMAL7000.NMAL7000_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7000CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 * 2016/08/01   Fujitsu         R.Nakamura      Update          QC#10928
 *</pre>
 */
public class NMAL7000CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
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
     * @param scrnMsg     NMAL7000BMsg
     * @param scrnAMsgAry NMAL7000_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7000BMsg scrnMsg, NMAL7000_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7000BMsg
     * @param scrnAMsgAry NMAL7000_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7000BMsg scrnMsg, NMAL7000_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7000BMsg
     * @param scrnAMsgAry NMAL7000_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7000BMsg scrnMsg, NMAL7000_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Set Popup Argument for NMAL6050.
     * @param scrnMsg NMAL7000BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6050(NMAL7000BMsg scrnMsg, String eventNm, int eventRow) {
        // Parameter Clear
        scrnMsg.xxPopPrm_ZA.clear();

        Object[] param = new Object[11];
        if ("OpenWin_Branch".equals(eventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "COA_BR");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "COA_BR_CD");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "COA_BR_DESC_TXT");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "COA_BR_SORT_NUM");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "Coa Branch Popup");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "Coa Branch Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "Coa Branch Name");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "Coa Branch Code");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "Coa Branch Name");

                param[0]  = scrnMsg.xxPopPrm_Z0;
                param[1]  = scrnMsg.xxPopPrm_Z1;
                param[2]  = scrnMsg.xxPopPrm_Z2;
                param[3]  = scrnMsg.xxPopPrm_Z3;
                param[4]  = scrnMsg.xxPopPrm_Z4;
                param[5]  = scrnMsg.xxPopPrm_Z5;
                param[6]  = scrnMsg.xxPopPrm_Z6;
                param[7]  = scrnMsg.xxPopPrm_Z7;
                param[8]  = scrnMsg.xxPopPrm_Z8;

                param[9]  = scrnMsg.coaBrCd_H1;
                param[10] = scrnMsg.xxPopPrm_ZA;
        }

        return param;
    }

    /**
     * setArgumentNWAL1130.
     * @param scrnMsg NMAL7000BMsg
     * @param eventNm String
     * @param eventRow int
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setArgumentNWAL1130(NMAL7000BMsg scrnMsg, String eventNm, int eventRow, String glblCmpyCd) {
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

        scrnMsg.xxPopPrm_ZA.clear();

        // Mod Start 2016/08/01 QC#10928
        if ("OpenWin_AccountNum".equals(eventNm)
                || "OpenWin_AccountName".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Account# Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" S.GLBL_CMPY_CD");
            tblNmP2.append(",S.EZCANCELFLAG");
            tblNmP2.append(",S.SELL_TO_CUST_CD");
            tblNmP2.append(",S.DS_ACCT_NM");
            tblNmP2.append(" FROM SELL_TO_CUST S");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" S.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND S.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND S.DS_ACCT_TP_CD = '").append(DS_ACCT_TP.CUSTOMER).append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Cust Account#";
            whereArray0[1] = "SELL_TO_CUST_CD";
            whereArray0[2] = scrnMsg.dsAcctNum_H1.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Cust Account Name";
            whereArray1[1] = "DS_ACCT_NM";
            whereArray1[2] = scrnMsg.dsAcctNm_H1.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Customer Account#";
            columnArray0[1] = "SELL_TO_CUST_CD";
            columnArray0[2] = BigDecimal.valueOf(28);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Customer Account Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(60);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray = new Object[2];
            sortConditionArray[0] = "SELL_TO_CUST_CD";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);

        } else if ("OpenWin_CSMPNum".equals(eventNm)) {

            suffixP0 = "";
            scrnNmP1 = "CSMP Number Popup";

            tblNmP2.append(" SELECT CSMP.GLBL_CMPY_CD");
            tblNmP2.append(",CSMP.EZCANCELFLAG");
            tblNmP2.append(",CSMP.DS_ACCT_NUM");
            tblNmP2.append(",CSMP.DS_ACCT_NM");
            tblNmP2.append(",CSMP.CSMP_NUM");
            tblNmP2.append(",CSMP.PRC_CATG_CD");
            tblNmP2.append(",CSMP.PRC_CONTR_NUM");
            tblNmP2.append(",CSMP.RNW_CSMP_NUM");
            tblNmP2.append(",CSMP.CSMP_NUM_CMNT_TXT");
            tblNmP2.append(",CSMP.CSMP_CONTR_ACTV_FLG");
            tblNmP2.append(",CSMP.EFF_FROM_DT");
            tblNmP2.append(",CSMP.EFF_THRU_DT");
            tblNmP2.append(" FROM CSMP_CONTR CSMP");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" CSMP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND CSMP.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Account Number";
            whereArray0[1] = "DS_ACCT_NUM";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Account Name";
            whereArray1[1] = "DS_ACCT_NM";
            whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "CSMP Number";
            whereArray2[1] = "CSMP_NUM";
            whereArray2[2] = scrnMsg.csmpNum_H1.getValue();
            whereArray2[3] = "N";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Price List ID";
            whereArray3[1] = "PRC_CATG_CD";
            whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray3[3] = "N";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Contract Number";
            whereArray4[1] = "PRC_CONTR_NUM";
            whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray4[3] = "N";
            whereListP3.add(whereArray4);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Account Number";
            columnArray0[1] = "DS_ACCT_NUM";
            columnArray0[2] = BigDecimal.valueOf(20);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Account Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "CSMP Number";
            columnArray2[1] = "CSMP_NUM";
            columnArray2[2] = BigDecimal.valueOf(15);
            columnArray2[3] = "Y";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Price List ID";
            columnArray3[1] = "PRC_CATG_CD";
            columnArray3[2] = BigDecimal.valueOf(10);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Price Contract Number";
            columnArray4[1] = "PRC_CONTR_NUM";
            columnArray4[2] = BigDecimal.valueOf(30);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Renew CSMP Number";
            columnArray5[1] = "RNW_CSMP_NUM";
            columnArray5[2] = BigDecimal.valueOf(15);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "CSMP Number Comment";
            columnArray6[1] = "CSMP_NUM_CMNT_TXT";
            columnArray6[2] = BigDecimal.valueOf(60);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Active Flag";
            columnArray7[1] = "CSMP_CONTR_ACTV_FLG";
            columnArray7[2] = BigDecimal.valueOf(6);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Effective Date From";
            columnArray8[1] = "EFF_FROM_DT";
            columnArray8[2] = BigDecimal.valueOf(12);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] columnArray9 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray9[0] = "Effective Date To";
            columnArray9[1] = "EFF_THRU_DT";
            columnArray9[2] = BigDecimal.valueOf(12);
            columnArray9[3] = "N";
            columnListP4.add(columnArray9);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "DS_ACCT_NUM";
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "CSMP_NUM";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_SupplyPlan".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Supply Plan Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" SAP.GLBL_CMPY_CD");
            tblNmP2.append(",SAP.EZCANCELFLAG");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_PK");
            tblNmP2.append(",SAP.ACTV_FLG");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_NM");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_SHORT_NM");
            tblNmP2.append(",SAPT.SPLY_AGMT_PLN_TP_DESC_TXT");
            tblNmP2.append(",SADT.SPLY_AGMT_DOC_TP_DESC_TXT");
            tblNmP2.append(",SAP.EFF_FROM_DT");
            tblNmP2.append(",SAP.EFF_THRU_DT");
            tblNmP2.append(" FROM SPLY_AGMT_PLN SAP");
            tblNmP2.append(",SPLY_AGMT_PLN_TP SAPT");
            tblNmP2.append(",SPLY_AGMT_DOC_TP SADT");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" SAP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND SAP.GLBL_CMPY_CD = SAPT.GLBL_CMPY_CD");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = SAPT.EZCANCELFLAG");
            tblNmP2.append(" AND SAP.SPLY_AGMT_PLN_TP_CD = SAPT.SPLY_AGMT_PLN_TP_CD");
            tblNmP2.append(" AND SAP.GLBL_CMPY_CD = SADT.GLBL_CMPY_CD");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = SADT.EZCANCELFLAG");
            tblNmP2.append(" AND SAP.SPLY_AGMT_DOC_TP_CD = SADT.SPLY_AGMT_DOC_TP_CD");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Plan ID";
            whereArray0[1] = "SPLY_AGMT_PLN_PK";
            whereArray0[2] =  scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Plan Name";
            whereArray1[1] = "SPLY_AGMT_PLN_NM";
            whereArray1[2] = scrnMsg.splyAgmtPlnNm_H1.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Plan Short Name";
            whereArray2[1] = "SPLY_AGMT_PLN_SHORT_NM";
            whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Plan Type Name";
            whereArray3[1] = "SPLY_AGMT_PLN_TP_DESC_TXT";
            whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray3[3] = "Y";
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Document Type Name";
            whereArray4[1] = "SPLY_AGMT_DOC_TP_DESC_TXT";
            whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray4[3] = "Y";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Effective Date From";
            whereArray5[1] = "EFF_FROM_DT";
            whereArray5[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray5[3] = "N";
            whereListP3.add(whereArray5);

            Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
            whereArray6[0] = "Effective Date To";
            whereArray6[1] = "EFF_THRU_DT";
            whereArray6[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray6[3] = "N";
            whereListP3.add(whereArray6);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Plan ID";
            columnArray0[1] = "SPLY_AGMT_PLN_PK";
            columnArray0[2] = BigDecimal.valueOf(28);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Active";
            columnArray1[1] = "ACTV_FLG";
            columnArray1[2] = BigDecimal.valueOf(6);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Plan Name";
            columnArray2[1] = "SPLY_AGMT_PLN_NM";
            columnArray2[2] = BigDecimal.valueOf(50);
            columnArray2[3] = "N";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Plan Short Name";
            columnArray3[1] = "SPLY_AGMT_PLN_SHORT_NM";
            columnArray3[2] = BigDecimal.valueOf(50);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Plan Type Name";
            columnArray4[1] = "SPLY_AGMT_PLN_TP_DESC_TXT";
            columnArray4[2] = BigDecimal.valueOf(50);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Document Type Name";
            columnArray5[1] = "SPLY_AGMT_DOC_TP_DESC_TXT";
            columnArray5[2] = BigDecimal.valueOf(50);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Effective Date From";
            columnArray6[1] = "EFF_FROM_DT";
            columnArray6[2] = BigDecimal.valueOf(12);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Effective Date To";
            columnArray7[1] = "EFF_THRU_DT";
            columnArray7[2] = BigDecimal.valueOf(12);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "SPLY_AGMT_PLN_PK";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_ContractName".equals(eventNm) || "OpenWin_ContractNum".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Price Contract Search Popup";

            tblNmP2.append(" ");
            tblNmP2.append("SELECT ");
            tblNmP2.append("    PC.EZCANCELFLAG ");
            tblNmP2.append("   ,PC.GLBL_CMPY_CD ");
            tblNmP2.append("   ,PC.PRC_CONTR_PK ");
            tblNmP2.append("   ,PC.PRC_CONTR_NUM ");
            tblNmP2.append("   ,PC.PRC_CONTR_NM ");
            tblNmP2.append("   ,PC.PRC_CONTR_CUST_BID_NUM ");
            tblNmP2.append("   ,PC.LINE_BIZ_TP_CD ");
            tblNmP2.append("   ,PC.PRC_CONTR_SHORT_DESC_TXT ");
            tblNmP2.append("   ,DECODE(PC.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG ");
            tblNmP2.append("   ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT, 'YYYYMMDD'), 'MM/DD/YYYY') EFF_FROM_DT ");
            tblNmP2.append("   ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT, 'YYYYMMDD'), 'MM/DD/YYYY') EFF_THRU_DT ");
            tblNmP2.append("FROM ");
            tblNmP2.append("    PRC_CONTR PC ");
            tblNmP2.append("WHERE ");
            tblNmP2.append("    PC.EZCANCELFLAG = '0' ");
            tblNmP2.append("AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

            Object[] whereArray0 = new Object[4];

            whereArray0[0] = "Price Contract#";
            whereArray0[1] = "PRC_CONTR_NUM";
            if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNum_H1)) {
                whereArray0[2] = scrnMsg.prcContrNum_H1.getValue();
            }
            whereArray0[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[4];
            whereArray1[0] = "Price Contract Name";
            whereArray1[1] = "PRC_CONTR_NM";
            if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNm_H1)) {
                whereArray1[2] = scrnMsg.prcContrNm_H1.getValue();
            }
            whereArray1[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[4];
            whereArray2[0] = "Bid Num";
            whereArray2[1] = "PRC_CONTR_CUST_BID_NUM";
            whereArray2[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[4];
            whereArray3[0] = "Line of Business";
            whereArray3[1] = "LINE_BIZ_TP_CD";
            whereArray3[3] = ZYPConstant.FLG_OFF_N;
            whereListP3.add(whereArray3);

            Object[] columnArray0 = new Object[4];
            columnArray0[0] = "Contract ID";
            columnArray0[1] = "PRC_CONTR_PK";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[4];
            columnArray1[0] = "Price Contract #";
            columnArray1[1] = "PRC_CONTR_NUM";
            columnArray1[2] = BigDecimal.valueOf(35);
            columnArray1[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[4];
            columnArray2[0] = "Price Contract Name";
            columnArray2[1] = "PRC_CONTR_NM";
            columnArray2[2] = BigDecimal.valueOf(35);
            columnArray2[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[4];
            columnArray3[0] = "Status";
            columnArray3[1] = "ACTV_FLG";
            columnArray3[2] = BigDecimal.valueOf(7);
            columnArray3[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[4];
            columnArray4[0] = "Eff From Date";
            columnArray4[1] = "EFF_FROM_DT";
            columnArray4[2] = BigDecimal.valueOf(9);
            columnArray4[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray4);

            Object[] columnArray5 = new Object[4];
            columnArray5[0] = "Eff Thru Date";
            columnArray5[1] = "EFF_THRU_DT";
            columnArray5[2] = BigDecimal.valueOf(9);
            columnArray5[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray5);

            Object[] sortCondArray0 = new Object[2];
            sortCondArray0[0] = "PRC_CONTR_NUM";
            sortCondArray0[1] = "";
            sortConditionListP5.add(sortCondArray0);

            Object[] sortCondArray1 = new Object[2];
            sortCondArray1[0] = "PRC_CONTR_NM";
            sortCondArray1[1] = "";
            sortConditionListP5.add(sortCondArray1);

        } else if ("OpenWin_ServiceModelName".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Service Model Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" MN.T_GLBL_CMPY_CD GLBL_CMPY_CD");
            tblNmP2.append(",MN.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(MN.T_MDL_ID) T_MDL_ID");
            tblNmP2.append(",MN.T_MDL_NM");
            tblNmP2.append(" FROM MDL_NM MN");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" MN.T_GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND MN.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Service Model ID";
            whereArray0[1] = "T_MDL_ID";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Service Model Name";
            whereArray1[1] = "T_MDL_NM";
            if (ZYPCommonFunc.hasValue(scrnMsg.mdlNm_H1)) {
                whereArray1[2] = scrnMsg.mdlNm_H1.getValue();
            }
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Service Model ID";
            columnArray0[1] = "T_MDL_ID";
            columnArray0[2] = BigDecimal.valueOf(22);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Service Model Name";
            columnArray1[1] = "T_MDL_NM";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = "Y";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "T_MDL_ID";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        }
        // Mod End 2016/08/01 QC#10928

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    // Add Start 2016/08/01 QC#10928
    /**
     * setArgumentNMAL6800.
     * @param scrnMsg NMAL7000BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6800(NMAL7000BMsg scrnMsg, String eventNm, int eventRow) {

        Object[] param = new Object[10];

        scrnMsg.xxPopPrm_Z0.clear();
        scrnMsg.xxPopPrm_ZA.clear();

        if ("MoveWin_ItemNumber".equals(eventNm)) {
            param[0] = scrnMsg.prcListMdseCd_H1;
        }

        param[1] = scrnMsg.xxPopPrm_ZA;
        param[2] = scrnMsg.xxPopPrm_ZA;
        param[3] = scrnMsg.xxPopPrm_ZA;
        param[4] = scrnMsg.xxPopPrm_ZA;
        param[5] = scrnMsg.xxPopPrm_ZA;
        param[6] = scrnMsg.xxPopPrm_ZA;
        if (param[7] == null) {
            param[7] = scrnMsg.xxPopPrm_ZA;
        }
        param[8] = scrnMsg.xxPopPrm_ZA;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "08");
        param[9] = scrnMsg.xxPopPrm_Z0;

        return param;
    }
    // Add End 2016/08/01 QC#10928

    /**
     * commonAddCheckItem
     * @param scrnMsg NMAL7000BMsg
     */
    public static void commonAddCheckItem(NMAL7000BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_H1);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_H1);

        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcListDplyNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcCatgDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.csmpNum_H1);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnNm_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        // Add Start 2016/08/01 QC#10928
        scrnMsg.addCheckItem(scrnMsg.prcListMdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_H1);
        // Add Start 2016/08/01 QC#10928
    }

    /**
     * btnProtect.
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7000BMsg
     */
    public static void btnProtect(S21CommonHandler handler, NMAL7000BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonEnabled("MoveWin_UploadPricing", true);
        } else {
            handler.setButtonEnabled("MoveWin_UploadPricing", false);
        }
    }
}
