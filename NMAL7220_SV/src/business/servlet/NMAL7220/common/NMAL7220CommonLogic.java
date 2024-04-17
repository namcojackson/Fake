/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7220.common;

import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_APL;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_APR;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_RST;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.BTN_LIST;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.SCRN_ID_00;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.NMAM0836E;
import static business.servlet.NMAL7220.constant.NMAL7220Constant.NMAM0185E;

import business.servlet.NMAL7220.NMAL7220BMsg;
import business.servlet.NMAL7220.NMAL7220_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7220CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param authorityFlg boolean
     */
    public static void initCmnBtnProp(S21CommonHandler handler, boolean authorityFlg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        if (authorityFlg) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * scrnProtect.
     * @param scrnMsg NMAL7220BMsg
     */
    public static void scrnProtect(NMAL7220BMsg scrnMsg, boolean clearFlg) {

        String fmlaTpCd = scrnMsg.A.no(0).prcFmlaTpCd_A1.getValue();
        if (PRC_FMLA_TP.PRICE_LIST.equals(fmlaTpCd)) {
            scrnMsg.A.no(0).prcCatgNm_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFmlaOpCd_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFmlaNum_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFuncTpCd_A1.setInputProtected(true);

            if (clearFlg) {
                scrnMsg.A.no(0).prcFuncTpCd_A1.clear();
                scrnMsg.A.no(0).prcFmlaNum_A1.clear();
                scrnMsg.A.no(0).prcFmlaOpCd_A1.clear();
            }
        } else if (PRC_FMLA_TP.STANDARD_COST.equals(fmlaTpCd)) {
            scrnMsg.A.no(0).prcCatgNm_A1.setInputProtected(true);
            scrnMsg.A.no(0).prcFmlaOpCd_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFmlaNum_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFuncTpCd_A1.setInputProtected(true);

            if (clearFlg) {
                scrnMsg.A.no(0).prcFmlaOpCd_A1.clear();
                scrnMsg.A.no(0).prcFmlaNum_A1.clear();
                scrnMsg.A.no(0).prcFuncTpCd_A1.clear();
            }
        } else if (PRC_FMLA_TP.CUSTOM_PRICE.equals(fmlaTpCd)) {
            scrnMsg.A.no(0).prcCatgNm_A1.setInputProtected(true);
            scrnMsg.A.no(0).prcFmlaOpCd_A1.setInputProtected(true);
            scrnMsg.A.no(0).prcFmlaNum_A1.setInputProtected(true);
            scrnMsg.A.no(0).prcFuncTpCd_A1.setInputProtected(false);

            if (clearFlg) {
                scrnMsg.A.no(0).prcCatgNm_A1.clear();
                scrnMsg.A.no(0).prcFmlaOpCd_A1.clear();
                scrnMsg.A.no(0).prcFmlaNum_A1.clear();
            }
        } else {
            scrnMsg.A.no(0).prcCatgNm_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFmlaOpCd_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFmlaNum_A1.setInputProtected(false);
            scrnMsg.A.no(0).prcFuncTpCd_A1.setInputProtected(true);

            if (clearFlg) {
                scrnMsg.A.no(0).prcFuncTpCd_A1.clear();
                scrnMsg.A.no(0).prcFmlaNum_A1.clear();
                scrnMsg.A.no(0).prcFmlaOpCd_A1.clear();
            }
        }
    }

    /**
     * Set Button properties.
     * 
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7220BMsg
     * @param authorityFlg boolean
     */
    public static void setBtnProp(S21CommonHandler handler, NMAL7220BMsg scrnMsg, boolean authorityFlg) {
        boolean btnFlg = false;

        if (authorityFlg) {
            String fmlaTpCd = scrnMsg.A.no(0).prcFmlaTpCd_A1.getValue();
            if (PRC_FMLA_TP.PRICE_LIST.equals(fmlaTpCd)) {
                btnFlg = true;
            } else if (PRC_FMLA_TP.CUSTOM_PRICE.equals(fmlaTpCd)) {
                btnFlg = false;
            } else if (PRC_FMLA_TP.STANDARD_COST.equals(fmlaTpCd)) {
                btnFlg = false;
            } else {
                btnFlg = true;
            }
        } else {
            btnFlg = false;
        }

        handler.setButtonEnabled(BTN_LIST, btnFlg);
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
     * @param scrnMsg     NMAL7220BMsg
     * @param scrnAMsgAry NMAL7220_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7220BMsg scrnMsg, NMAL7220_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7220BMsg
     * @param scrnAMsgAry NMAL7220_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7220BMsg scrnMsg, NMAL7220_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7220BMsg
     * @param scrnAMsgAry NMAL7220_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7220BMsg scrnMsg, NMAL7220_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * searchCheck
     * 
     * @param scrnMsg   NMAL7220BMsg
     */
    public static void submitCheck(NMAL7220BMsg scrnMsg) {
        // NmCheck
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcFmlaNm_H1)) {
            scrnMsg.prcFmlaNm_H1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.prcFmlaNm_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcFmlaNm_H1);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
            scrnMsg.effFromDt_H1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.effFromDt_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        }

        scrnMsg.addCheckItem(scrnMsg.prcFmlaPk_H1);
        scrnMsg.addCheckItem(scrnMsg.prcFmlaDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.actvFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1)) {
            if (scrnMsg.effFromDt_H1.getValue().compareTo(scrnMsg.effThruDt_H1.getValue()) > 0) {
                scrnMsg.effFromDt_H1.setErrorInfo(1, NMAM0185E);
                scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
            }
        }

        String fmlaTpCd = scrnMsg.A.no(0).prcFmlaTpCd_A1.getValue();
        if (PRC_FMLA_TP.PRICE_LIST.equals(fmlaTpCd)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcCatgNm_A1)) {
                scrnMsg.A.no(0).prcCatgNm_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcCatgNm_A1.getNameForMessage()});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFmlaOpCd_A1)) {
                scrnMsg.A.no(0).prcFmlaOpCd_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFmlaOpCd_A1.getNameForMessage()});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFmlaNum_A1)) {
                scrnMsg.A.no(0).prcFmlaNum_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFmlaNum_A1.getNameForMessage()});
            }
        } else if (PRC_FMLA_TP.STANDARD_COST.equals(fmlaTpCd)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFmlaOpCd_A1)) {
                scrnMsg.A.no(0).prcFmlaOpCd_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFmlaOpCd_A1.getNameForMessage()});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFmlaOpCd_A1)) {
                scrnMsg.A.no(0).prcFmlaOpCd_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFmlaOpCd_A1.getNameForMessage()});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFmlaNum_A1)) {
                scrnMsg.A.no(0).prcFmlaNum_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFmlaNum_A1.getNameForMessage()});
            }
        } else if (PRC_FMLA_TP.CUSTOM_PRICE.equals(fmlaTpCd)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFuncTpCd_A1)) {
                scrnMsg.A.no(0).prcFuncTpCd_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFuncTpCd_A1.getNameForMessage()});
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcCatgNm_A1)) {
                scrnMsg.A.no(0).prcCatgNm_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcCatgNm_A1.getNameForMessage()});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFmlaOpCd_A1)) {
                scrnMsg.A.no(0).prcFmlaOpCd_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFmlaOpCd_A1.getNameForMessage()});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).prcFmlaNum_A1)) {
                scrnMsg.A.no(0).prcFmlaNum_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(0).prcFmlaNum_A1.getNameForMessage()});
            }
        }

        scrnMsg.addCheckItem(scrnMsg.A.no(0).prcCatgNm_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(0).prcFmlaOpCd_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(0).prcFmlaNum_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(0).prcFuncTpCd_A1);

    }

    /**
     * createAuthorityFlg
     * 
     * @param item String
     * @return boolean
     */
    public static boolean createAuthorityFlg(String item) {
        if (ZYPConstant.FLG_ON_Y.equals(item)) {
            return true;
        } else {
         return false;
        }
    }
}
