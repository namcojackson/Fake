/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1820.common;

import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_APL;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_APR;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_DEL;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_DWL;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_RJT;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_RST;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_RTN;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_SAV;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.BTN_CMN_SUB;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_0;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_1;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_10;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_12;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_2;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_20;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_3;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_30;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_4;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_5;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_6;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.IDX_7;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.NWAM0712E;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.NWAM0754E;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.PERCENT;
import static business.servlet.NWAL1820.constant.NWAL1820Constant.SCRN_ID_00;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDFieldErrorException;
import business.servlet.NWAL1820.NWAL1820BMsg;
import business.servlet.NWAL1820.NWAL1820_ABMsg;
import business.servlet.NWAL1820.NWAL1820_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1820CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/11   Fujitsu         S.Iidaka        Update          S21_NA#7414
 * 2016/05/30   Fujitsu         S.Ohki          Update          S21_NA#7861
 * 2016/08/04   Fujitsu         R.Nakamura      Update          S21_NA#9078
 * 2016/10/21   Fujitsu         S.Iidaka        Update          S21_NA#14607
 *</pre>
 */
public class NWAL1820CommonLogic {

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
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
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
     * @param scrnMsg     NWAL1820BMsg
     * @param scrnAMsgAry NWAL1820_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1820BMsg scrnMsg, NWAL1820_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1820BMsg
     * @param scrnAMsgAry NWAL1820_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1820BMsg scrnMsg, NWAL1820_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL1820BMsg
     * @param scrnAMsgAry NWAL1820_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1820BMsg scrnMsg, NWAL1820_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL1820BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctNm);
        scrnMsg.addCheckItem(scrnMsg.addShipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.aquNum);
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.coaBrDescTxt);
        // 2016/05/30 S21_NA#7861 Mod Start
//        scrnMsg.addCheckItem(scrnMsg.slsRepPsnCd);
        scrnMsg.addCheckItem(scrnMsg.psnNum);
        // 2016/05/30 S21_NA#7861 Mod End
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdRsnCd);
        scrnMsg.addCheckItem(scrnMsg.actlShipDt_FR);
        scrnMsg.addCheckItem(scrnMsg.actlShipDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_TO);

    }

    /**
     * checkInputValue
     * @param scrnMsg NWAL1820BMsg
     */
    public static void checkInputValue(NWAL1820BMsg scrnMsg) {
        if (!checkHasValue(scrnMsg)) {
            scrnMsg.setMessageInfo(NWAM0754E);
            throw new EZDFieldErrorException();
        }
        // Shipped Date
        if (ZYPCommonFunc.hasValue(scrnMsg.actlShipDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.actlShipDt_TO)) {
            // actlShipDt_FR > actlShipDt_TO
            if (0 < scrnMsg.actlShipDt_FR.getValue().compareTo(scrnMsg.actlShipDt_TO.getValue())) {
                scrnMsg.actlShipDt_FR.setErrorInfo(1, NWAM0712E, new String[]{"Shipped Date From", "Shipped Date Through"});
                scrnMsg.actlShipDt_TO.setErrorInfo(1, NWAM0712E, new String[]{"Shipped Date From", "Shipped Date Through"});
            }
        }
        // Loan Due Date
        if (ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_TO)) {
            // actlShipDt_FR > actlShipDt_TO
            if (0 < scrnMsg.xxTrxDt_FR.getValue().compareTo(scrnMsg.xxTrxDt_TO.getValue())) {
                scrnMsg.xxTrxDt_FR.setErrorInfo(1, NWAM0712E, new String[]{"Loan Due Date From", "Loan Due Date Through"});
                scrnMsg.xxTrxDt_TO.setErrorInfo(1, NWAM0712E, new String[]{"Loan Due Date From", "Loan Due Date Through"});
            }
        }
        addCheckItemBizLayerErr(scrnMsg);
    }

    /**
     * checkHasValue
     * @param scrnMsg NWAL1820BMsg
     * @return boolean
     */
    public static boolean checkHasValue(NWAL1820BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctNm)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.addShipToCustCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.aquNum)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.serNum)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.coaBrDescTxt)) {
            return true;
        // 2016/05/30 S21_NA#7861 Mod Start
//        } else if (ZYPCommonFunc.hasValue(scrnMsg.slsRepPsnCd)) {
        } else if (ZYPCommonFunc.hasValue(scrnMsg.psnNum)) {
        // 2016/05/30 S21_NA#7861 Mod End
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdRsnCd)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlShipDt_FR)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlShipDt_TO)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_FR)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_TO)) {
            return true;
        }
        return false;
    }

    /**
     * Set Control Field
     * @param scrnMsg NWAL1820BMsg
     */
    public static void setControlField(NWAL1820BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1820_ABMsg scrnMsgLine = scrnMsg.A.no(i);

            // Order# Link
            // 2016/05/11 S21_NA#7414 Mod Start
            scrnMsgLine.cpoOrdNum_LK.setInputProtected(false);
            scrnMsgLine.cpoOrdNum_LK.setValue(ZYPConstant.FLG_ON_Y);

//            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsgLine.xxExstFlg_A.getValue())) {
//                scrnMsgLine.cpoOrdNum_LK.clear();
//              scrnMsgLine.cpoOrdNum_LK.setInputProtected(true);
//            } else {
//                scrnMsgLine.cpoOrdNum_LK.setValue(ZYPConstant.FLG_ON_Y);
//            }
            // 2016/05/11 S21_NA#7414 Mod Start

            // Input Protected
            scrnMsgLine.xxCpoOrdDt_A.setInputProtected(true);
            scrnMsgLine.dsOrdCatgDescTxt_A.setInputProtected(true);
            scrnMsgLine.dsOrdTpDescTxt_A.setInputProtected(true);
            scrnMsgLine.dsOrdRsnDescTxt_A.setInputProtected(true);
            scrnMsgLine.ordHdrStsNm_A.setInputProtected(true);
            scrnMsgLine.aquNum_A.setInputProtected(true);
            scrnMsgLine.shipToCustAcctCd_A.setInputProtected(true);
            scrnMsgLine.shipToCustAcctNm_A.setInputProtected(true);
            scrnMsgLine.addShipToCustCd_A.setInputProtected(true);
            scrnMsgLine.slsRepTocNm_A.setInputProtected(true);
            scrnMsgLine.coaBrCd_A.setInputProtected(true);
            scrnMsgLine.coaBrDescTxt_A.setInputProtected(true);
            scrnMsgLine.actlShipDt_A.setInputProtected(true);
            scrnMsgLine.xxTrxDt_A.setInputProtected(true);
            scrnMsgLine.loanPerDaysAot_A.setInputProtected(true);
            scrnMsgLine.xxDtlCnt_A.setInputProtected(true);

        }
        // Background
        if (0 < scrnMsg.A.getValidCount()) {
            setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        }
        // Focus
        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).cpoOrdNum_A);
        } else {
            scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
        }
    }

    /**
     * Get Param NMAL6770 For Ship To
     * @param scrnMsg NWAL1820BMsg
     * @return Param NMAL6770 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL1820BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
        // Mod End 2016/08/04 QC#9078

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.shipToCustAcctCd);
        paramList.add(scrnMsg.shipToCustAcctNm);
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
        paramList.add(scrnMsg.addShipToCustCd);
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
     * Clear Popup Parameters
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NWAL1820BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
    }


    /**
     * Get Param NWAL1130 For Salesrep
     * @param scrnMsg NWAL1820BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NWAL1820BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "P";
        params[IDX_1] = "Sales Rep Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    T.GLBL_CMPY_CD");
        sb.append("   ,T.EZCANCELFLAG");
        sb.append("   ,SP.PSN_NUM"); // 2016/05/30 S21_NA#7861 Mod
        sb.append("   ,T.TOC_NM");
        sb.append("   ,SP.LINE_BIZ_TP_CD");
        sb.append("   ,CB.COA_BR_NM");
        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("   ,T.TOC_CD ");
        sb.append("FROM");
        sb.append("    TOC T");
        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("   ,BIZ_AREA_ORG BA");
        sb.append("   ,ORG_FUNC_ASG OFA");
        sb.append("   ,S21_PSN SP");
        sb.append("   ,COA_BR CB ");
        sb.append("   ,S21_ORG SO "); // 2016/05/30 S21_NA#7861 Add
        sb.append("WHERE");
        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND T.EZCANCELFLAG        = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        // 2016/05/30 S21_NA#7861 Add Start
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        // 2016/10/21 S21_NA#14607 Add Start
        sb.append("    AND SO.RGTN_STS_CD        != '").append(RGTN_STS.TERMINATED).append("'");
        // 2016/10/21 S21_NA#14607 Add End
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        // 2016/05/30 S21_NA#7861 Add End
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        // 2016/10/21 S21_NA#14607 Add Start
//        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
//        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.slsDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
        // 2016/05/30 S21_NA#7861 Add End
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        // 2016/05/30 S21_NA#7861 Mod Start
//        whereArray0[IDX_0] = "Employee ID";
//        whereArray0[IDX_1] = "PSN_CD";
//        whereArray0[IDX_2] = scrnMsg.slsRepPsnCd.getValue();
        whereArray0[IDX_0] = "Resource Number";
        whereArray0[IDX_1] = "PSN_NUM";
        if (ZYPCommonFunc.hasValue(scrnMsg.psnNum)) {
            whereArray0[IDX_2] = scrnMsg.psnNum.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // 2016/05/11 S21_NA#7861 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Name";
        whereArray1[IDX_1] = "TOC_NM";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        // 2016/05/30 S21_NA#7861 Mod Start
//        columnArray0[IDX_0] = "Employee ID";
//        columnArray0[IDX_1] = "PSN_CD";
//        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_0] = "Resource Number";
        columnArray0[IDX_1] = "PSN_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_12);
        // 2016/05/30 S21_NA#7861 Mod End
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Name";
        columnArray1[IDX_1] = "TOC_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Line of Bussines";
        columnArray2[IDX_1] = "LINE_BIZ_TP_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Branch";
        columnArray3[IDX_1] = "COA_BR_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Role";
        columnArray4[IDX_1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "TOC_CD";
        columnArray5[IDX_1] = "TOC_CD";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_0);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PSN_NUM"; // 2016/05/30 S21_NA#7861 Mod
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;

        return params;
    }

    /**
     * Get Param NMAL6050 For SalesBranch
     * @param scrnMsg NWAL1820BMsg
     * @return Object[]
     */
    public static Object[] getParamNMAL6050ForSalesBranch(NWAL1820BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        int i = 0;
        // [0]:TBL_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "COA_BR");
        // [1]:TBL_CD_COL_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "COA_BR_CD");
        // [2]:TBL_NM_COL_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "COA_BR_DESC_TXT");
        // [3]:TBL_SORT_COL_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "COA_BR_SORT_NUM");
        // [4]:SCR_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "Branch Search");
        // [5]:HDR_CD_LB_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "Branch Code");
        // [6]:HDR_NM_LB_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "Branch Name");
        // [7]:DTL_CD_LB_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "Branch Code");
        // [8]:DTL_NM_LB_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, "Branch Name");
        // [9]:COND_CD
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        // [10]:COND_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, scrnMsg.coaBrDescTxt);

        scrnMsg.P.setValidCount(i);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(i);
        for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
            paramList.add(scrnMsg.P.no(j).xxPopPrm_P);
        }
        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NSAL1240 For Serial
     * @param scrnMsg NWAL1820BMsg
     * @return Object[]
     */
    public static Object[] getParamNSAL1240ForSerial(NWAL1820BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.O);
        Object[] params = new Object[31];
        params[2] = scrnMsg.serNum;
        params[30] = scrnMsg.O;

        return params;
    }
}
