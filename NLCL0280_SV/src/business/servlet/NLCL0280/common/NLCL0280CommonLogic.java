/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280.common;

import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_APPLY;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_APPROVE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_CLEAR;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_DETELE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_REJECT;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_RESET;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_RETURN;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_SAVE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_CMN_SUBMIT;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_DELETE_SEARCH;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_OPEN_JRNL_ENTRY;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_SAVE_SEARCH;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_SEARCH_PROD_INFO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_SEARCH_RTL_SWH_INFO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_SEARCH_RTL_WH_INFO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_SEARCH_SHIP_FROM_INFO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_SEARCH_SHIP_TO_INFO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BTN_SEARCH_VND_INFO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.BUSINESS_ID;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_CTY_ADDR;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_LINE_ADDR;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_LOC_CD_FROM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_LOC_CD_TO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_LOC_NM_FROM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_LOC_NM_TO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_LOC_TP_NM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_POST_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_CD_FOR_ST_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_CD_FOR_CTY_ADDR;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_CD_FOR_LINE_ADDR;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_CD_FOR_LOC_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_CD_FOR_LOC_NM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_CD_FOR_LOC_TP_NM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_CD_FOR_POST_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_CD_FOR_ST_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_CD_FOR_CTY_ADDR;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_CD_FOR_LINE_ADDR;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_CD_FOR_LOC_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_CD_FOR_LOC_NM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_CD_FOR_LOC_TP_NM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_CD_FOR_POST_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_CD_FOR_ST_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.DTL_CD_LABEL_COA_PROD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.DTL_NM_LABEL_COA_PROD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.FUNCTION_UPDATE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.HDR_CD_LABEL_COA_PROD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.HDR_NM_LABEL_COA_PROD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.MODE_CODE_ALL_ITEM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.NLCM0136E;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.NLZM2277E;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SCRN_ID;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SCRN_NM_SHIP_FROM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SCR_NM_COA_PROD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SCR_NM_VND;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SORT_COLUMN_NAME_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SORT_CONDITION_FOR_SUPPLIER_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SORT_CONDITION_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SORT_NAME_FOR_LOC_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.SORT_VAL_FOR_LOC_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.TBL_CD_COLUMN_CD_COA_PROD_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.TBL_CD_COLUMN_NM_COA_PROD_DESC_TXT;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.TBL_NM_COA_PROD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.TBL_NM_VND;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.TBL_SORT_NUM_COLUMN_NM_COA_PROD_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_CD_FOR_LOC_CD_FROM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_CD_FOR_LOC_CD_TO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_CD_FOR_LOC_NM_FROM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_CD_FOR_LOC_NM_TO;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_NM_FOR_LOC_CD;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_DISP_NM_NM_FOR_LOC_NM;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_SQL_NM_FOR_SUPPLIER_SITE_CODE;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.WHERE_SQL_NM_FOR_SUPPLIER_SITE_NAME;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0280.NLCL0280BMsg;
import business.servlet.NLCL0280.NLCL0280Bean;
import business.servlet.NLCL0280.constant.NLCL0280Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 * 2017/01/17   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NLCL0280CommonLogic {

    /**
     * setInitParam
     * @param scrnMsg NLCL0280BMsg
     * @param params Object[]
     */
    public static void setInitParam(NLCL0280BMsg scrnMsg, Object[] params) {

        int paramCnt = params.length;

        // Transaction Date From
        if (paramCnt > 0) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.trxDt_H1, (EZDBDateItem) params[0]);
        }

        // Transaction Date To
        if (paramCnt > 1) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.trxDt_H2, (EZDBDateItem) params[1]);
        }

        // Transaction ID
        if (paramCnt > 2) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.invtyTrxPk_H1, (EZDBBigDecimalItem) params[2]);
        }

        // Transaction Type
        if (paramCnt > 3) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.invtyTrxTpCd_PS, (EZDBStringItem) params[3]);
        }

        // Transaction
        if (paramCnt > 4) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.trxCd_PS, (EZDBStringItem) params[4]);
        }

        // Transaction Reason
        if (paramCnt > 5) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.trxRsnCd_PS, (EZDBStringItem) params[5]);
        }

        // Source Document Number
        if (paramCnt > 6) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.invtyTrxSlpNum_H1, (EZDBStringItem) params[6]);
        }

        // RWS Number
        if (paramCnt > 7) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_H1, (EZDBStringItem) params[7]);
        }

        // Shipping Order Number
        if (paramCnt > 8) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_H1, (EZDBStringItem) params[8]);
        }

        // Merchandise Code
        if (paramCnt > 9) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_H1, (EZDBStringItem) params[9]);
        }

        // Merchandise Name
        if (paramCnt > 10) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_H1, (EZDBStringItem) params[10]);
        }

        // Item Type
        if (paramCnt > 11) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseItemTpCd_PS, (EZDBStringItem) params[11]);
        }

        // Merchandise Type
        if (paramCnt > 12) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjCd_PS, (EZDBStringItem) params[12]);
        }

        // Product Code
        if (paramCnt > 13) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_H1, (EZDBStringItem) params[13]);
        }

        // Cross Reference Type
        if (paramCnt > 14) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseItemRelnTpCd_PS, (EZDBStringItem) params[14]);
        }

        // Cross Reference Item
        if (paramCnt > 15) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.relnMdseCd_H1, (EZDBStringItem) params[15]);
        }

        // Serial Number
        if (paramCnt > 16) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_H1, (EZDBStringItem) params[16]);
        }

        // Supplier Item
        if (paramCnt > 17) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.splyItemNum_H1, (EZDBStringItem) params[17]);
        }

        // Manufacturer Item
        if (paramCnt > 18) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.mnfItemCd_H1, (EZDBStringItem) params[18]);
        }

        // Retail Warehouse Code
        if (paramCnt > 19) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, (EZDBStringItem) params[19]);
        }

        // Sub Warehouse Code
        if (paramCnt > 20) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_H1, (EZDBStringItem) params[20]);
        }

        // Supplier Code
        if (paramCnt > 21) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd_H1, (EZDBStringItem) params[21]);
        }

        // Ship From Customer Code
        if (paramCnt > 22) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.shipFromLocCustCd_H1, (EZDBStringItem) params[22]);
        }

        // Ship To Customer Code
        if (paramCnt > 23) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocCustCd_H1, (EZDBStringItem) params[23]);
        }
    }

    /**
     * chkInitParam
     * @param scrnMsg NLCL0280BMsg
     * @return boolean
     */
    public static boolean chkInitParam(NLCL0280BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.trxDt_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.trxDt_H2)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.invtyTrxPk_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.invtyTrxTpCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.trxCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.trxRsnCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.invtyTrxSlpNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rwsNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.soNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.mdseDescShortTxt_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemTpCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.coaProjCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.coaProdCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemRelnTpCd_PS)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.relnMdseCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.serNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.splyItemNum_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.mnfItemCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.vndCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipFromLocCustCd_H1)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToLocCustCd_H1)) {

            return true;
        }

        return false;
    }

    /**
     * chkSearchItem
     * @param scrnMsg NLCL0280BMsg
     */
    public static void chkSearchItem(NLCL0280BMsg scrnMsg) {

        // Cross Reference Type
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemRelnTpCd_PS)) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.relnMdseCd_H1)) {

                scrnMsg.relnMdseCd_H1.setErrorInfo(1, NLCM0136E, new String[] {"Cross Refference Type", "Cross Refference Item" });
                scrnMsg.setMessageInfo(ZZM9037E);
            }
        }

        // Date check
        if (ZYPCommonFunc.hasValue(scrnMsg.trxDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.trxDt_H2)) {

            // Transaction From Date > Transaction To Date
            if (ZYPDateUtil.compare(scrnMsg.trxDt_H1.getValue(), scrnMsg.trxDt_H2.getValue()) > 0) {

                scrnMsg.trxDt_H1.setErrorInfo(1, NLZM2277E, new String[] {"Transaction Date To", "Transaction Date From" });
                scrnMsg.trxDt_H2.setErrorInfo(1, NLZM2277E, new String[] {"Transaction Date To", "Transaction Date From" });
                scrnMsg.setMessageInfo(ZZM9037E);
            }
        }

        // Input param check
        if (!ZYPCommonFunc.hasValue(scrnMsg.trxDt_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.trxDt_H2) //
                && !ZYPCommonFunc.hasValue(scrnMsg.invtyTrxPk_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.invtyTrxTpCd_PS) //
                && !ZYPCommonFunc.hasValue(scrnMsg.trxCd_PS) //
                && !ZYPCommonFunc.hasValue(scrnMsg.invtyTrxSlpNum_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.rwsNum_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.soNum_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.serNum_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.vndCd_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.shipToLocCustCd_H1) //
        ) {

            scrnMsg.trxDt_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.trxDt_H2.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.invtyTrxPk_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.invtyTrxTpCd_PS.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.trxCd_PS.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.invtyTrxSlpNum_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.rwsNum_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.soNum_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.serNum_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.mdseCd_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.rtlWhCd_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.vndCd_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.shipToLocCustCd_H1.setErrorInfo(1, NLZM2276E, null);
            scrnMsg.setMessageInfo(ZZM9037E);

        }

        // addCheck
        addCheckAllHeaderItem(scrnMsg);
    }

    /**
     * addCheckAllHeaderItem
     * @param scrnMsg NLCL0280BMsg
     */
    public static void addCheckAllHeaderItem(NLCL0280BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.trxDt_H1);
        scrnMsg.addCheckItem(scrnMsg.trxDt_H2);
        scrnMsg.addCheckItem(scrnMsg.invtyTrxPk_H1);
        scrnMsg.addCheckItem(scrnMsg.invtyTrxTpCd_PS);
        scrnMsg.addCheckItem(scrnMsg.trxCd_PS);
        scrnMsg.addCheckItem(scrnMsg.trxRsnCd_PS);
        scrnMsg.addCheckItem(scrnMsg.invtyTrxSlpNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rwsNum_H1);
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.serNum_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemTpCd_PS);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd_PS);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemRelnTpCd_PS);
        scrnMsg.addCheckItem(scrnMsg.relnMdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.splyItemNum_H1);
        scrnMsg.addCheckItem(scrnMsg.mnfItemCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.vndCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipFromLocCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToLocCustCd_H1);

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.L.no(i).xxChkBox_LS);
        }
    }

    /**
     * ctrlScreenItemProtection
     * @param scrnMsg NLCL0280BMsg
     * @param handler EZDCommonHandler
     */
    public static void ctrlScrnItemProtection(NLCL0280BMsg scrnMsg, EZDCommonHandler handler) {

        // column input protection
        // true : block entry
        // false : input possible

        // Search Option
        scrnMsg.srchOptPk_PD.setInputProtected(false);
        scrnMsg.srchOptNm_PD.setInputProtected(false);
        scrnMsg.srchOptNm_H1.setInputProtected(false);

        // Header
        scrnMsg.trxDt_H1.setInputProtected(false);
        scrnMsg.trxDt_H2.setInputProtected(false);
        scrnMsg.invtyTrxPk_H1.setInputProtected(false);
        scrnMsg.invtyTrxTpCd_PD.setInputProtected(false);
        scrnMsg.invtyTrxTpDescTxt_PD.setInputProtected(false);
        scrnMsg.trxCd_PD.setInputProtected(false);
        scrnMsg.xxScrItem61Txt_TX.setInputProtected(false);

        if (ZYPCommonFunc.hasValue(scrnMsg.invtyTrxTpCd_PS) || ZYPCommonFunc.hasValue(scrnMsg.trxCd_PS)) {

            scrnMsg.trxRsnCd_PS.setInputProtected(false);
            scrnMsg.trxRsnCd_PD.setInputProtected(false);
            scrnMsg.xxScrItem61Txt_TR.setInputProtected(false);

        } else {

            scrnMsg.trxRsnCd_PS.setInputProtected(true);
            scrnMsg.trxRsnCd_PD.setInputProtected(true);
            scrnMsg.xxScrItem61Txt_TR.setInputProtected(true);
        }

        scrnMsg.invtyTrxSlpNum_H1.setInputProtected(false);
        scrnMsg.rwsNum_H1.setInputProtected(false);
        scrnMsg.soNum_H1.setInputProtected(false);
        scrnMsg.mdseCd_H1.setInputProtected(false);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(false);
        scrnMsg.mdseItemTpCd_PD.setInputProtected(false);
        scrnMsg.xxScrItem61Txt_MT.setInputProtected(false);
        scrnMsg.coaProjCd_PD.setInputProtected(false);
        scrnMsg.xxScrItem61Txt_PC.setInputProtected(false);
        scrnMsg.coaProdCd_H1.setInputProtected(false);
        scrnMsg.coaProdDescTxt_H1.setInputProtected(true);
        scrnMsg.mdseItemRelnTpCd_PD.setInputProtected(false);
        scrnMsg.mdseItemRelnTpDescTxt_PD.setInputProtected(false);

        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemRelnTpCd_PS)) {

            scrnMsg.relnMdseCd_H1.setInputProtected(false);

        } else {

            scrnMsg.relnMdseCd_H1.setInputProtected(true);
        }

        scrnMsg.serNum_H1.setInputProtected(false);
        scrnMsg.splyItemNum_H1.setInputProtected(false);
        scrnMsg.mnfItemCd_H1.setInputProtected(false);
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlWhNm_H1.setInputProtected(true);
        scrnMsg.rtlSwhCd_H1.setInputProtected(false);
        scrnMsg.rtlSwhNm_H1.setInputProtected(true);
        scrnMsg.vndCd_H1.setInputProtected(false);
        scrnMsg.locNm_H1.setInputProtected(true);
        scrnMsg.shipFromLocCustCd_H1.setInputProtected(false);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        scrnMsg.shipToLocCustCd_H1.setInputProtected(false);
        scrnMsg.dsAcctNm_H2.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(BTN_SEARCH_PROD_INFO, true);
        handler.setButtonEnabled(BTN_SEARCH_RTL_WH_INFO, true);
        handler.setButtonEnabled(BTN_SEARCH_RTL_SWH_INFO, true);
        handler.setButtonEnabled(BTN_SEARCH_VND_INFO, true);
        handler.setButtonEnabled(BTN_SEARCH_SHIP_FROM_INFO, true);
        handler.setButtonEnabled(BTN_SEARCH_SHIP_TO_INFO, true);

        // Page nation
        scrnMsg.xxPageShowCurNum.setInputProtected(false);
        scrnMsg.xxPageShowFromNum.setInputProtected(false);
        scrnMsg.xxPageShowOfNum.setInputProtected(false);
        scrnMsg.xxPageShowToNum.setInputProtected(false);
        scrnMsg.xxPageShowTotNum.setInputProtected(false);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).ajeLinkFlg_A1.getValue())) {

                handler.setButtonEnabled(BTN_OPEN_JRNL_ENTRY, i, true);

            } else {

                handler.setButtonEnabled(BTN_OPEN_JRNL_ENTRY, i, false);
            }

            scrnMsg.A.no(i).rtlWhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_PJ.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_MD.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_PD.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxRelnTrgtFlg_A1.getValue())) {
                EZDGUIAttribute serNum = new EZDGUIAttribute(NLCL0280Constant.SCRN_ID, "xxLinkAncr_FI" + i);
                serNum.setVisibility(true);
                scrnMsg.addGUIAttribute(serNum);
                scrnMsg.A.no(i).xxLinkAncr_FI.setInputProtected(false);
            } else {
                EZDGUIAttribute serNum = new EZDGUIAttribute(NLCL0280Constant.SCRN_ID, "xxLinkAncr_FI" + i);
                serNum.setVisibility(false);
                scrnMsg.addGUIAttribute(serNum);
                scrnMsg.A.no(i).xxLinkAncr_FI.setInputProtected(true);
            }
            scrnMsg.A.no(i).xxRelnTrgtFlg_A1.setInputProtected(true);
            scrnMsg.A.no(i).splyItemNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).mnfItemCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).serNumFlg_A1.getValue())) {
                EZDGUIAttribute serNum = new EZDGUIAttribute(NLCL0280Constant.SCRN_ID, "xxLinkAncr_SE" + i);
                serNum.setVisibility(true);
                scrnMsg.addGUIAttribute(serNum);
                scrnMsg.A.no(i).xxLinkAncr_SE.setInputProtected(false);
            } else {
                EZDGUIAttribute serNum = new EZDGUIAttribute(NLCL0280Constant.SCRN_ID, "xxLinkAncr_SE" + i);
                serNum.setVisibility(false);
                scrnMsg.addGUIAttribute(serNum);
                scrnMsg.A.no(i).xxLinkAncr_SE.setInputProtected(true);
            }
            scrnMsg.A.no(i).serNumFlg_A1.setInputProtected(true);
            scrnMsg.A.no(i).invtyTrxPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).invtyTrxTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_AX.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_AR.setInputProtected(true);
            scrnMsg.A.no(i).invtyTrxQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_SS.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem61Txt_AS.setInputProtected(true);
            scrnMsg.A.no(i).invtyTrxSlpNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).soNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxTsDsp19Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToLocCustNm_A1.setInputProtected(true);
        }

        // Table color
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLCL0280Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NLCL0280Bean.L, scrnMsg.L);

        // common button protection
        // 0 : inactive
        // 1 : active
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: Clear GUI Attribute.
     * @param scrnMsg NLCL0280BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NLCL0280BMsg scrnMsg, String[][] baseContents) {

        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
    }

    /**
     * setMdseInfoParam
     * @param scrnMsg NLCL0280BMsg
     * @return Item Master Search Popup Parameter
     */
    public static Object[] setMdseInfoParam(NLCL0280BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.P.clear();

        // Parameter Copy
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.mdseDescShortTxt_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, MODE_CODE_ALL_ITEM);

        // Paramter Set
        Object[] param = new Object[10];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;

        return param;
    }

    /**
     * setProdInfoParam
     * @param scrnMsg NLCL0280BMsg
     * @return Common Code Pop Up Parameter (Product Code)
     */
    public static Object[] setProdInfoParam(NLCL0280BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.P.clear();

        // Parameter Copy
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, TBL_NM_COA_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, TBL_CD_COLUMN_CD_COA_PROD_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, TBL_CD_COLUMN_NM_COA_PROD_DESC_TXT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, TBL_SORT_NUM_COLUMN_NM_COA_PROD_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, SCR_NM_COA_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, HDR_CD_LABEL_COA_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, HDR_NM_LABEL_COA_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, DTL_CD_LABEL_COA_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, DTL_NM_LABEL_COA_PROD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.coaProdCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, scrnMsg.coaProdDescTxt_H1);

        // Paramter Set
        Object[] param = new Object[11];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;

        return param;
    }

    /**
     * setSupplierInfoParam
     * @param scrnMsg NLCL0280BMsg
     * @return Common Code Pop Up Parameter (Supplier Code)
     */
    public static Object[] setSupplierInfoParam(NLCL0280BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.R.clear();
        scrnMsg.P.clear();

        // Ctrl Data
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, SCR_NM_VND);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = SCR_NM_VND;

        param[2] = TBL_NM_VND;
        param[3] = getSearchConditionForSupplier(scrnMsg);
        param[4] = getDisplayColumnForSupplier(scrnMsg);
        param[5] = getSortForSupplier(scrnMsg);
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * getSearchConditionForSupplier
     * @param scrnMsg NLCL0280BMsg
     * @return Condition SQL
     */
    private static List<Object> getSearchConditionForSupplier(NLCL0280BMsg scrnMsg) {

        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_FOR_SUPPLIER_SITE_CODE, WHERE_SQL_NM_FOR_SUPPLIER_SITE_CODE, scrnMsg.vndCd_H1.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_FOR_SUPPLIER_SITE_NAME, WHERE_SQL_NM_FOR_SUPPLIER_SITE_NAME, null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnForSupplier
     * @param scrnMsg NLCL0280BMsg
     * @return Column SQL
     */
    private static List<Object> getDisplayColumnForSupplier(NLCL0280BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_FOR_SUPPLIER_CODE, COLUMN_SQL_NM_FOR_SUPPLIER_CODE, COLUMN_WIDTH_FOR_SUPPLIER_CODE, ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {COLUMN_DISP_NM_FOR_SUPPLIER_SITE_CODE, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_CODE, COLUMN_WIDTH_FOR_SUPPLIER_SITE_CODE, ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {COLUMN_DISP_NM_FOR_SUPPLIER_SITE_NAME, COLUMN_SQL_NM_FOR_SUPPLIER_SITE_NAME, COLUMN_WIDTH_FOR_SUPPLIER_SITE_NAME, ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);

        return colList;
    }

    /**
     * getSortForSupplier
     * @param scrnMsg NLCL0280BMsg
     * @return Sort SQL
     */
    private static List<Object> getSortForSupplier(NLCL0280BMsg scrnMsg) {

        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_COLUMN_NAME_FOR_SUPPLIER_CODE, SORT_CONDITION_FOR_SUPPLIER_CODE };
        Object[] sortObj2 = {SORT_COLUMN_NAME_FOR_SUPPLIER_SITE_CODE, SORT_CONDITION_FOR_SUPPLIER_SITE_CODE };

        sortList.add(sortObj1);
        sortList.add(sortObj2);

        return sortList;
    }

    /**
     * setLocationInfoParam
     * @param scrnMsg NLCL0280BMsg
     * @return Location Info Parameter
     */
    public static Object[] setLocationInfoParam(NLCL0280BMsg scrnMsg) {

        // Parameter Clear
        scrnMsg.P.clear();

        // Parameter Copy
        // Data Security Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        // Virtual WH Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.rtlSwhCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.rtlSwhNm_H1);
        // Only RTL_WH Flag
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_OFF_N);

        // Paramter Set
        Object[] param = new Object[11];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;

        return param;
    }

    /**
     * setShipLocCodeParam
     * @param scrnMsg NLCL0280BMsg
     * @param glblCmpyCd String
     * @param pupupScrnNm String
     * @return Ship From / To Location Parameter
     */
    public static Object[] setShipLocCodeParam(NLCL0280BMsg scrnMsg, String glblCmpyCd, String pupupScrnNm) {

        // Parameter Clear
        scrnMsg.R.clear();
        scrnMsg.P.clear();

        // Ctrl Data
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, pupupScrnNm);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = pupupScrnNm;

        if (SCRN_NM_SHIP_FROM.equals(pupupScrnNm)) {

            param[2] = getSelectSQL(scrnMsg, glblCmpyCd);
            param[3] = getSearchConditionSettingShipFrom(scrnMsg, scrnMsg.shipFromLocCustCd_H1.getValue());
            param[4] = getDisplayColumnSettingShipFrom(scrnMsg);

        } else {

            param[2] = getSelectSQL(scrnMsg, glblCmpyCd);
            param[3] = getSearchConditionSettingShipTo(scrnMsg, scrnMsg.shipToLocCustCd_H1.getValue());
            param[4] = getDisplayColumnSettingShipTo(scrnMsg);
        }

        param[5] = getSortSetting(scrnMsg);
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * getSelectSQL
     * @param scrnMsg NLCL0280BMsg
     * @param glblCmpyCd String
     * @return String
     */
    private static String getSelectSQL(NLCL0280BMsg scrnMsg, String glblCmpyCd) {

        StringBuffer sb = new StringBuffer();

        // Warehouse
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD AS LOC_CD").append("\n");
        sb.append(",RW.RTL_WH_NM AS LOC_NM").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",TRIM(RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR) AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD").append("\n");
        sb.append(",RW.POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH  RW").append("\n");
        sb.append(",RTL_SWH RSW").append("\n");
        sb.append(",WH      W").append("\n");
        sb.append(",LOC_TP  LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    RW.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("AND RW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND RSW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND RSW.RTL_WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND RSW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND W.GLBL_CMPY_CD = RSW.GLBL_CMPY_CD").append("\n");
        sb.append("AND W.WH_CD = RSW.INVTY_LOC_CD").append("\n");
        sb.append("AND W.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = W.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = W.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = W.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Warehouse (DUMMY)
        sb.append("UNION").append("\n");
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD AS LOC_CD").append("\n");
        sb.append(",RW.RTL_WH_NM AS LOC_NM").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",TRIM(RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR) AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD").append("\n");
        sb.append(",RW.POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH RW").append("\n");
        sb.append(",WH     W").append("\n");
        sb.append(",LOC_TP LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    RW.GLBL_CMPY_CD= '#GLBL_CMPY_CD#'").append("\n");
        sb.append("AND RW.EZCANCELFLAG= '0'").append("\n");
        sb.append("AND W.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND W.WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND W.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD= W.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = W.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD= W.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Technician
        sb.append("UNION").append("\n");
        sb.append("SELECT DISTINCT").append("\n");
        sb.append(" RW.RTL_WH_CD AS LOC_CD").append("\n");
        sb.append(",RW.RTL_WH_NM AS LOC_NM").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",TRIM(RW.FIRST_LINE_ADDR || ' ' || RW.SCD_LINE_ADDR) AS LINE_ADDR").append("\n");
        sb.append(",RW.CTY_ADDR").append("\n");
        sb.append(",RW.ST_CD").append("\n");
        sb.append(",RW.POST_CD").append("\n");
        sb.append(",RW.GLBL_CMPY_CD").append("\n");
        sb.append(",RW.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" RTL_WH   RW").append("\n");
        sb.append(",RTL_SWH  RSW").append("\n");
        sb.append(",TECH_LOC TL").append("\n");
        sb.append(",LOC_TP   LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    RW.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("AND RW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND RSW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD").append("\n");
        sb.append("AND RSW.RTL_WH_CD = RW.RTL_WH_CD").append("\n");
        sb.append("AND RSW.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND TL.GLBL_CMPY_CD = RSW.GLBL_CMPY_CD").append("\n");
        sb.append("AND TL.TECH_LOC_CD  = RSW.INVTY_LOC_CD").append("\n");
        sb.append("AND TL.EZCANCELFLAG  = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = TL.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = TL.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = TL.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");

        // Vendor
        sb.append("UNION").append("\n");
        sb.append("SELECT").append("\n");
        sb.append(" V.VND_CD        AS LOC_CD").append("\n");
        sb.append(",PVV.DPLY_VND_NM AS LOC_NM").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",TRIM(V.FIRST_LINE_ADDR || ' ' || V.SCD_LINE_ADDR) AS LINE_ADDR").append("\n");
        sb.append(",V.CTY_ADDR").append("\n");
        sb.append(",V.ST_CD").append("\n");
        sb.append(",V.POST_CD").append("\n");
        sb.append(",V.GLBL_CMPY_CD").append("\n");
        sb.append(",V.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" VND      V").append("\n");
        sb.append(",LOC_TP   LT").append("\n");
        sb.append(",PO_VND_V PVV").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    V.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("AND V.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND V.GLBL_CMPY_CD = LT.GLBL_CMPY_CD").append("\n");
        sb.append("AND V.LOC_GRP_CD = LT.LOC_GRP_CD").append("\n");
        sb.append("AND V.LOC_ROLE_TP_CD = LT.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND V.GLBL_CMPY_CD = PVV.GLBL_CMPY_CD").append("\n");
        sb.append("AND V.VND_CD = PVV.VND_CD").append("\n");
        sb.append("AND PVV.EZCANCELFLAG = '0'").append("\n");

        // Ship To Customer
        sb.append("UNION").append("\n");
        sb.append("SELECT").append("\n");
        sb.append(" ST.SHIP_TO_CUST_CD AS LOC_CD").append("\n");
        sb.append(",ST.DS_ACCT_NM      AS LOC_NM").append("\n");
        sb.append(",LT.LOC_TP_NM").append("\n");
        sb.append(",TRIM(ST.FIRST_LINE_ADDR || ' ' || ST.SCD_LINE_ADDR) AS LINE_ADDR").append("\n");
        sb.append(",ST.CTY_ADDR").append("\n");
        sb.append(",ST.ST_CD").append("\n");
        sb.append(",ST.POST_CD").append("\n");
        sb.append(",ST.GLBL_CMPY_CD").append("\n");
        sb.append(",ST.EZCANCELFLAG").append("\n");

        sb.append("FROM").append("\n");
        sb.append(" SHIP_TO_CUST ST").append("\n");
        sb.append(",LOC_TP       LT").append("\n");

        sb.append("WHERE").append("\n");
        sb.append("    ST.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("AND ST.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND LT.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND LT.LOC_GRP_CD = ST.LOC_GRP_CD").append("\n");
        sb.append("AND LT.LOC_ROLE_TP_CD = ST.LOC_ROLE_TP_CD").append("\n");
        sb.append("AND LT.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    RTL_WH NR").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NR.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NR.RTL_WH_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NR.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    WH NW").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NW.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NW.WH_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NW.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    TECH_LOC NT").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NT.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NT.TECH_LOC_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NT.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    VND NV").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    NV.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND NV.VND_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND NV.EZCANCELFLAG = '0')").append("\n");
        sb.append("AND NOT EXISTS (SELECT 1").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    BR B").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    B.GLBL_CMPY_CD = ST.GLBL_CMPY_CD").append("\n");
        sb.append("AND B.BR_CD = ST.SHIP_TO_CUST_CD").append("\n");
        sb.append("AND B.EZCANCELFLAG = '0')").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", glblCmpyCd);

        return sql;
    }

    /**
     * getSearchConditionSettingShipFrom
     * @param scrnMsg NLCL0280BMsg
     * @param ShipLocCd String
     * @return List<Object>
     */
    private static List<Object> getSearchConditionSettingShipFrom(NLCL0280BMsg scrnMsg, String ShipLocCd) {

        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_CD_FOR_LOC_CD_FROM, WHERE_DISP_NM_NM_FOR_LOC_CD, ShipLocCd, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_CD_FOR_LOC_NM_FROM, WHERE_DISP_NM_NM_FOR_LOC_NM, null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getSearchConditionSettingShipTo
     * @param scrnMsg NLCL0280BMsg
     * @param ShipLocCd String
     * @return List<Object>
     */
    private static List<Object> getSearchConditionSettingShipTo(NLCL0280BMsg scrnMsg, String ShipLocCd) {

        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {WHERE_DISP_NM_CD_FOR_LOC_CD_TO, WHERE_DISP_NM_NM_FOR_LOC_CD, ShipLocCd, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {WHERE_DISP_NM_CD_FOR_LOC_NM_TO, WHERE_DISP_NM_NM_FOR_LOC_NM, null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnSettingShipFrom
     * @param scrnMsg NLCL0280BMsg
     * @return List<Object>
     */
    private static List<Object> getDisplayColumnSettingShipFrom(NLCL0280BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_CD_FOR_LOC_CD_FROM, COLUMN_SQL_NM_CD_FOR_LOC_CD, COLUMN_WIDTH_CD_FOR_LOC_CD, ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {COLUMN_DISP_NM_CD_FOR_LOC_NM_FROM, COLUMN_SQL_NM_CD_FOR_LOC_NM, COLUMN_WIDTH_CD_FOR_LOC_NM, ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {COLUMN_DISP_NM_CD_FOR_LOC_TP_NM, COLUMN_SQL_NM_CD_FOR_LOC_TP_NM, COLUMN_WIDTH_CD_FOR_LOC_TP_NM, ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {COLUMN_DISP_NM_CD_FOR_LINE_ADDR, COLUMN_SQL_NM_CD_FOR_LINE_ADDR, COLUMN_WIDTH_CD_FOR_LINE_ADDR, ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {COLUMN_DISP_NM_CD_FOR_CTY_ADDR, COLUMN_SQL_NM_CD_FOR_CTY_ADDR, COLUMN_WIDTH_CD_FOR_CTY_ADDR, ZYPConstant.FLG_OFF_N };
        Object[] colObj6 = {COLUMN_DISP_NM_CD_FOR_ST_CD, COLUMN_SQL_NM_CD_FOR_ST_CD, COLUMN_WIDTH_CD_FOR_ST_CD, ZYPConstant.FLG_OFF_N };
        Object[] colObj7 = {COLUMN_DISP_NM_CD_FOR_POST_CD, COLUMN_SQL_NM_CD_FOR_POST_CD, COLUMN_WIDTH_CD_FOR_POST_CD, ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);
        colList.add(colObj6);
        colList.add(colObj7);

        return colList;
    }

    /**
     * getDisplayColumnSettingShipTo
     * @param scrnMsg NLCL0280BMsg
     * @return List<Object>
     */
    private static List<Object> getDisplayColumnSettingShipTo(NLCL0280BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {COLUMN_DISP_NM_CD_FOR_LOC_CD_TO, COLUMN_SQL_NM_CD_FOR_LOC_CD, COLUMN_WIDTH_CD_FOR_LOC_CD, ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {COLUMN_DISP_NM_CD_FOR_LOC_NM_TO, COLUMN_SQL_NM_CD_FOR_LOC_NM, COLUMN_WIDTH_CD_FOR_LOC_NM, ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {COLUMN_DISP_NM_CD_FOR_LOC_TP_NM, COLUMN_SQL_NM_CD_FOR_LOC_TP_NM, COLUMN_WIDTH_CD_FOR_LOC_TP_NM, ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {COLUMN_DISP_NM_CD_FOR_LINE_ADDR, COLUMN_SQL_NM_CD_FOR_LINE_ADDR, COLUMN_WIDTH_CD_FOR_LINE_ADDR, ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {COLUMN_DISP_NM_CD_FOR_CTY_ADDR, COLUMN_SQL_NM_CD_FOR_CTY_ADDR, COLUMN_WIDTH_CD_FOR_CTY_ADDR, ZYPConstant.FLG_OFF_N };
        Object[] colObj6 = {COLUMN_DISP_NM_CD_FOR_ST_CD, COLUMN_SQL_NM_CD_FOR_ST_CD, COLUMN_WIDTH_CD_FOR_ST_CD, ZYPConstant.FLG_OFF_N };
        Object[] colObj7 = {COLUMN_DISP_NM_CD_FOR_POST_CD, COLUMN_SQL_NM_CD_FOR_POST_CD, COLUMN_WIDTH_CD_FOR_POST_CD, ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);
        colList.add(colObj6);
        colList.add(colObj7);

        return colList;
    }

    /**
     * getSortSetting
     * @param scrnMsg NLCL0280BMsg
     * @return List<Object>
     */
    private static List<Object> getSortSetting(NLCL0280BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {SORT_NAME_FOR_LOC_CD, SORT_VAL_FOR_LOC_CD };

        sortList.add(sortObj1);

        return sortList;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

    /**
     * Set Cross Refference Item Popup Parameters
     * @param scrnMsg NLCL0280BMsg
     * @param glblCmpyCd String
     * @param index int
     * @return Common Code Pop Up Parameter (Cross Refference Item Popup)
     */
    public static Object[] setCrossReffItemPopupParam(NLCL0280BMsg scrnMsg, String glblCmpyCd, int index) {

        // Parameter Clear
        scrnMsg.R.clear();
        scrnMsg.P.clear();

        // Ctrl Data
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, SCR_NM_CROSS_REF);

        // Paramter Set
        Object[] param = new Object[7];
        param[0] = "";
        param[1] = SCR_NM_CROSS_REF;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT").append("\n");
        sb.append("     MIFS.MDSE_CD").append("\n");
        sb.append("    ,MIFS.RELN_MDSE_CD").append("\n");
        sb.append("    ,MIRT.MDSE_ITEM_RELN_TP_DESC_TXT").append("\n");
        sb.append("    ,MIFS.GLBL_CMPY_CD").append("\n");
        sb.append("    ,MIFS.EZCANCELFLAG").append("\n");
        sb.append("FROM").append("\n");
        sb.append("     MDSE_ITEM_FLIP_SET MIFS").append("\n");
        sb.append("    ,MDSE_ITEM_RELN_TP MIRT").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    MIFS.GLBL_CMPY_CD = '" + glblCmpyCd  + "'").append("\n");
        sb.append("AND MIFS.EZCANCELFLAG = '0'").append("\n");
        sb.append("AND MIFS.GLBL_CMPY_CD = MIRT.GLBL_CMPY_CD").append("\n");
        sb.append("AND MIFS.MDSE_ITEM_RELN_TP_CD = MIRT.MDSE_ITEM_RELN_TP_CD").append("\n");
        sb.append("AND MIRT.EZCANCELFLAG = '0'").append("\n");
        sb.append("ORDER BY MIFS.MDSE_CD ,MIFS.MDSE_ITEM_RELN_TP_CD ,MIFS.RELN_MDSE_CD");
        param[2] = sb.toString();

        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Item Number", "MDSE_CD", scrnMsg.A.no(index).mdseCd_A1.getValue() , ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        param[3] = whereList;

        List<Object> columnList = new ArrayList<Object>();
        Object[] columnObj1 = {"Item Number", "MDSE_CD", BigDecimal.valueOf(16) , ZYPConstant.FLG_OFF_N };
        Object[] columnObj2 = {"Cross Refference Item", "RELN_MDSE_CD", BigDecimal.valueOf(16) , ZYPConstant.FLG_OFF_N };
        Object[] columnObj3 = {"Cross Refference Type", "MDSE_ITEM_RELN_TP_DESC_TXT", BigDecimal.valueOf(50) , ZYPConstant.FLG_OFF_N };
        columnList.add(columnObj1);
        columnList.add(columnObj2);
        columnList.add(columnObj3);
        param[4] = columnList;

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"MDSE_CD", "ASC" };
        sortList.add(sortObj1);
        param[5] = sortList;

        param[6] = scrnMsg.R;

        return param;
    }
}
