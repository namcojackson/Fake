/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600.common;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_ADD_NON_QUOTE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_ADD_QUOTE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_DEL_NON_QUOTE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_DEL_QUOTE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_OPEN_NUQUOTE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.BTN_OPEN_SLS_REP;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.COA_BR_NM_COLUMN;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.COA_BR_NM_LB;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.DEL_SFX;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.INOUT_SFX;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.LINE_BIZ_TP_CD_COLUMN;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.LINE_BIZ_TP_CD_LB;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.MODE_EDIT_ALL;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.MODE_QUOTE_ONLY;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.MODE_REFERENCE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NONQUOTE_SFX;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NWAM0370E;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NWZM0688E;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.ORG_FUNC_ROLE_TP_NM_COLUMN;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.ORG_FUNC_ROLE_TP_NM_LB;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.PERCENT;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.PSN_NUM_COLUMN;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.PSN_NUM_LB;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.QUOTE_SFX;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.REQ_DEL;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.SCRN_ID_00;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.SLSREP_NM_COLUMN;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.SLSREP_NM_LB;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.TBL_ID_NONQUOTE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.TBL_ID_QUOTE;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.TOC_CD_COLUMN;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.TOC_CD_LB;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import business.servlet.NWAL1600.NWAL1600BMsg;
import business.servlet.NWAL1600.NWAL1600Bean;
import business.servlet.NWAL1600.NWAL1600_ABMsg;
import business.servlet.NWAL1600.NWAL1600_BBMsg;
import business.servlet.NWAL1600.NWAL1600_OBMsg;
import business.servlet.NWAL1600.NWAL1600_PBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1600CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2015/11/19   Fujitsu         T.Ishii         Update          S21_NA#823
 * 2016/02/19   Fujitsu         T.Ishii         Update          S21_NA#4338
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/30   Fujitsu         T.Murai         Update          S21_NA#8393
 * 2016/09/27   Fujitsu         T.Murai         Update          S21_NA#13037
 * 2017/01/18   Fujitsu         W.Honda         Update          S21_NA#16750
 * 2017/11/01   Fujitsu         K.Ishizuka      Update          S21_NA#20416
 * 2020/04/02   Fujitsu         C.Hara          Update          QC#56425
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 *</pre>
 */
public class NWAL1600CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWAL1600BMsg
     */
    public static void setRowsBGWithClear(NWAL1600BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        tblColor.clearRowsBG(TBL_ID_QUOTE, scrnMsg.A);
        tblColor.setAlternateRowsBG(TBL_ID_QUOTE, scrnMsg.A);
        tblColor.clearRowsBG(TBL_ID_NONQUOTE, scrnMsg.B);
        tblColor.setAlternateRowsBG(TBL_ID_NONQUOTE, scrnMsg.B);
    }

    /**
     * makeBackUp
     * @param scrnMsg NWAL1600BMsg
     */
    public static void makeBackUp(NWAL1600BMsg scrnMsg) {
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).lineBizRoleTpCd_A1, scrnMsg.A.no(idx).lineBizRoleTpCd_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).slsRepCrPct_A1, scrnMsg.A.no(idx).slsRepCrPct_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).slsRepTocCd_A1, scrnMsg.A.no(idx).slsRepTocCd_A);
        }
        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).lineBizRoleTpCd_B1, scrnMsg.B.no(idx).lineBizRoleTpCd_B);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).slsRepTocCd_B1, scrnMsg.B.no(idx).slsRepTocCd_B);
        }
    }

    /**
     * copyParamToScrn
     * @param scrnMsg NWAL1600BMsg
     */
    public static void copyParamToScrn(NWAL1600BMsg scrnMsg) {
        int idxDelete = 0;
        int idxQuote = 0;
        int idxNonQuote = 0;

        for (int idx = 0; idx < scrnMsg.O.getValidCount(); idx++) {
            NWAL1600_OBMsg obMsg = scrnMsg.O.no(idx);

            // Copy To Delete Msg
            if (REQ_DEL.equals(obMsg.xxRqstTpCd_O.getValue())) {
                EZDMsg.copy(obMsg, INOUT_SFX, scrnMsg.C.no(idxDelete), DEL_SFX);
                idxDelete++;

            } else {
                // Copy To Sales Credit Msg
                if (ZYPConstant.FLG_ON_Y.equals(obMsg.slsCrQuotFlg_O.getValue())) {
                    EZDMsg.copy(obMsg, INOUT_SFX, scrnMsg.A.no(idxQuote), QUOTE_SFX);

                    // make Backup
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idxQuote).lineBizRoleTpCd_A1, scrnMsg.A.no(idxQuote).lineBizRoleTpCd_A);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idxQuote).slsRepCrPct_A1, scrnMsg.A.no(idxQuote).slsRepCrPct_A);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idxQuote).slsRepTocCd_A1, scrnMsg.A.no(idxQuote).slsRepTocCd_A);
                    idxQuote++;

                    // Copy To Non Quote Msg
                } else {
                    EZDMsg.copy(obMsg, INOUT_SFX, scrnMsg.B.no(idxNonQuote), NONQUOTE_SFX);

                    // make Backup
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idxNonQuote).lineBizRoleTpCd_B1, scrnMsg.B.no(idxNonQuote).lineBizRoleTpCd_B);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idxNonQuote).slsRepTocCd_B1, scrnMsg.B.no(idxNonQuote).slsRepTocCd_B);
                    idxNonQuote++;
                }
            }
        }

        scrnMsg.C.setValidCount(idxDelete);
        scrnMsg.A.setValidCount(idxQuote);
        scrnMsg.B.setValidCount(idxNonQuote);
    }

    /**
     * copyScrnToParam
     * @param scrnMsg NWAL1600BMsg
     */
    public static void copyScrnToParam(NWAL1600BMsg scrnMsg) {
        int idxOuput = 0;
        ZYPTableUtil.clear(scrnMsg.O);

        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            EZDMsg.copy(scrnMsg.A.no(idx), QUOTE_SFX, scrnMsg.O.no(idxOuput), INOUT_SFX);
            idxOuput++;
        }
        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            EZDMsg.copy(scrnMsg.B.no(idx), NONQUOTE_SFX, scrnMsg.O.no(idxOuput), INOUT_SFX);
            scrnMsg.O.no(idxOuput).slsRepCrPct_O.setValue(BigDecimal.ZERO);
            idxOuput++;
        }
        for (int idx = 0; idx < scrnMsg.C.getValidCount(); idx++) {
            EZDMsg.copy(scrnMsg.C.no(idx), DEL_SFX, scrnMsg.O.no(idxOuput), INOUT_SFX);
            idxOuput++;
        }

        scrnMsg.O.setValidCount(idxOuput);
    }

    // 2016/05/12 S21_NA#7861 Mod Start 
//    /**
//     * Get Writer Person Code
//     * @param scrnMsg NWAL1600BMsg
//     * @retrun Get Writer Person Code
//     */
//    public static EZDBStringItem getWriterPsnCd(NWAL1600BMsg scrnMsg) {
//
//        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
//            String lineBizRoleTp = scrnMsg.A.no(idx).lineBizRoleTpCd_A.getValue();
//            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTp)) {
//                return scrnMsg.A.no(idx).psnCd_A;
//            }
//        }
//        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
//            String lineBizRoleTp = scrnMsg.B.no(idx).lineBizRoleTpCd_B.getValue();
//            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTp)) {
//                return scrnMsg.B.no(idx).psnCd_B;
//            }
//        }
//
//        return null;
//    }

    /**
     * Get Writer Person Number
     * @param scrnMsg NWAL1600BMsg
     * @retrun Get Writer Person Number
     */
    public static EZDBStringItem getWriterPsnNum(NWAL1600BMsg scrnMsg) {

        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            String lineBizRoleTp = scrnMsg.A.no(idx).lineBizRoleTpCd_A.getValue();
            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTp) //
                    || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(lineBizRoleTp)) {//S21_NA ADD QC#20416
                return scrnMsg.A.no(idx).psnNum_A;
            }
        }
        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            String lineBizRoleTp = scrnMsg.B.no(idx).lineBizRoleTpCd_B.getValue();
            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTp) //
                    || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTp) || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(lineBizRoleTp)) {//S21_NA ADD QC#20416
                return scrnMsg.B.no(idx).psnNum_B;
            }
        }

        return null;
    }
    // 2016/05/12 S21_NA#7861 Mod End 

    /**
     * checkMandantory
     * @param scrnMsg NWAL1600BMsg
     * @return boolean
     */
    public static boolean checkMandantory(NWAL1600BMsg scrnMsg) {
        for (int idx = 0; idx < scrnMsg.O.getValidCount(); idx++) {
            NWAL1600_OBMsg obMsg = scrnMsg.O.no(idx);

            if (ZYPCommonFunc.hasValue(obMsg.dsCpoSlsCrPk_O)) {
                if (!ZYPCommonFunc.hasValue(obMsg.slsRepTocCd_O)) {
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(obMsg.slsCrQuotFlg_O)) {
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(obMsg.slsRepCrPct_O)) {
                    return false;
                }
                if (!ZYPCommonFunc.hasValue(obMsg.lineBizRoleTpCd_O)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * protectInputForInit
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1600BMsg
     */
    public static void protectAllInput(S21CommonHandler handler, NWAL1600BMsg scrnMsg) {
        // boolean hasUpdateAuth = hasUpdateFuncId();
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        // handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1],
        // BTN_CMN_CLS[2], 0, null);
        scrnMsg.cpoOrdNum.setInputProtected(true);
        handler.setButtonEnabled(BTN_ADD_QUOTE, false);
        handler.setButtonEnabled(BTN_DEL_QUOTE, false);
        handler.setButtonEnabled(BTN_ADD_NON_QUOTE, false);
        handler.setButtonEnabled(BTN_DEL_NON_QUOTE, false);
        handler.setButtonEnabled(BTN_OPEN_SLS_REP, false);
        handler.setButtonEnabled(BTN_OPEN_NUQUOTE, false);
        scrnMsg.A.setInputProtected(true);
        scrnMsg.B.setInputProtected(true);
    }

    /**
     * protectInputForInit
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1600BMsg
     */
    public static void protectInputForInit(S21CommonHandler handler, NWAL1600BMsg scrnMsg) {
        // boolean hasUpdateAuth = hasUpdateFuncId();
        boolean hasUpdateAuth = true;

        if (!hasUpdateAuth) {
            scrnMsg.cpoOrdNum.setInputProtected(true);
            handler.setButtonEnabled(BTN_ADD_QUOTE, false);
            handler.setButtonEnabled(BTN_DEL_QUOTE, false);
            for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
                scrnMsg.A.no(idx).lineBizRoleTpCd_A.setInputProtected(true);
                scrnMsg.A.no(idx).slsRepTocCd_A.setInputProtected(true);
                scrnMsg.A.no(idx).slsRepCrPct_A.setInputProtected(true);
            }
            for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
                scrnMsg.B.no(idx).lineBizRoleTpCd_B.setInputProtected(true);
                scrnMsg.B.no(idx).slsRepTocCd_B.setInputProtected(true);
            }
        }

        if (!hasUpdateAuth || MODE_QUOTE_ONLY.equals(scrnMsg.xxModeCd.getValue())) {
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // scrnMsg.dsOrdPosnNum.setInputProtected(true);
            scrnMsg.xxComnScrColValTxt.setInputProtected(true);
            // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            handler.setButtonEnabled(BTN_ADD_NON_QUOTE, false);
            handler.setButtonEnabled(BTN_DEL_NON_QUOTE, false);
        }
    }

    /**
     * protectInput
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1600BMsg
     */
    public static void protectInput(S21CommonHandler handler, NWAL1600BMsg scrnMsg) {
        if (1 > scrnMsg.A.getValidCount()) {
            handler.setButtonEnabled(BTN_DEL_QUOTE, false);
        } else {
            handler.setButtonEnabled(BTN_DEL_QUOTE, true);
        }
        if (1 > scrnMsg.B.getValidCount() || MODE_QUOTE_ONLY.equals(scrnMsg.xxModeCd.getValue())) {
            handler.setButtonEnabled(BTN_DEL_NON_QUOTE, false);
        } else {
            handler.setButtonEnabled(BTN_DEL_NON_QUOTE, true);
        }
    }

    /**
     * calcTotalPercent
     * @param scrnMsg NWAL1600BMsg
     * @return BigDecimal
     */
    public static BigDecimal calcTotalPercent(NWAL1600BMsg scrnMsg) {
        BigDecimal totalpercent = BigDecimal.ZERO;

        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).slsRepCrPct_A)) {
                totalpercent = totalpercent.add(scrnMsg.A.no(idx).slsRepCrPct_A.getValue());
            }
        }

        return totalpercent;
    }

    /**
     * Method name : isUnderMaxLineCnt
     * @param scrnMsg NWAL1600BMsg
     * @return boolean
     */
    public static boolean isUnderMaxLineCnt(NWAL1600BMsg scrnMsg) {
        int totalRowCnt = scrnMsg.A.getValidCount() + scrnMsg.B.getValidCount() + scrnMsg.C.getValidCount();
        if (200 < totalRowCnt) {
            return false;
        }
        return true;
    }

    /**
     * Method name : addCheckItems
     * @param scrnMsg NWAL1600BMsg
     */
    public static void addCheckItems(NWAL1600BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        // scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt);
        // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        addCheckSlsRepItems(scrnMsg);
        addCheckNonQuoteItems(scrnMsg);
    }

    /**
     * Method name : addCheckSlsRepItems
     * @param scrnMsg NWAL1600BMsg
     */
    public static void addCheckSlsRepItems(NWAL1600BMsg scrnMsg) {
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxChkBox_A);// 2017/01/18 S21_NA#16750 Add
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).lineBizRoleTpCd_A);
            // scrnMsg.addCheckItem(scrnMsg.A.no(idx).slsRepTocCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).slsRepCrPct_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).psnNum_A);// 2016/05/12 S21_NA#7861 Mod psnCd_A -> psnNum_A
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).tocNm_A);
        }
    }

    /**
     * Method name : addCheckNonQuoteItems
     * @param scrnMsg NWAL1600BMsg
     */
    public static void addCheckNonQuoteItems(NWAL1600BMsg scrnMsg) {
        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(idx).xxChkBox_B);// 2017/01/18 S21_NA#16750 Add
            scrnMsg.addCheckItem(scrnMsg.B.no(idx).lineBizRoleTpCd_B);
            // scrnMsg.addCheckItem(scrnMsg.B.no(idx).slsRepTocCd_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(idx).psnNum_B); // 2016/05/12 S21_NA#7861 Mod psnCd_B -> psnNum_B
            scrnMsg.addCheckItem(scrnMsg.B.no(idx).tocNm_B);
        }
    }

    /**
     * Method name : checkMandantoryItem
     * @param scrnMsg NWAL1600BMsg
     */
    public static void checkMandantoryItem(NWAL1600BMsg scrnMsg) {
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).slsRepTocCd_A)) {
                scrnMsg.A.no(idx).slsRepTocCd_A.setErrorInfo(1, NWZM0688E);
            }

            // 2016/05/12 S21_NA#7861 Mod Start 
            // if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).psnCd_A)) {
            // scrnMsg.A.no(idx).psnCd_A.setErrorInfo(1, NWZM0688E);
            // }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).psnNum_A)) {
                scrnMsg.A.no(idx).psnNum_A.setErrorInfo(1, NWZM0688E);
            }
            // 2016/05/12 S21_NA#7861 Mod End
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).tocNm_A)) {
                scrnMsg.A.no(idx).tocNm_A.setErrorInfo(1, NWZM0688E);
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).slsRepCrPct_A)) {
                scrnMsg.A.no(idx).slsRepCrPct_A.setErrorInfo(1, NWAM0370E);
            } else if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(idx).slsRepCrPct_A.getValue()) >= 0) {
                scrnMsg.A.no(idx).slsRepCrPct_A.setErrorInfo(1, NWAM0370E);
            }
        }
        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).slsRepTocCd_B)) {
                scrnMsg.B.no(idx).slsRepTocCd_B.setErrorInfo(1, NWZM0688E);
                scrnMsg.B.no(idx).psnNum_B.setErrorInfo(1, NWZM0688E); // 2016/05/12 S21_NA#7861 Mod psnCd_B -> psnNum_B
                scrnMsg.B.no(idx).tocNm_B.setErrorInfo(1, NWZM0688E);
            }
        }
    }

    /**
     * Method name : setSlsrepSelectStr
     * @param glblCmpyCd String
     * @param index int
     * @return String
     */
    public static String setSlsrepSelectStr(String glblCmpyCd, int index) {

        // S21_NA#4338 modify start
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("     T.GLBL_CMPY_CD");
        sb.append("    ,T.EZCANCELFLAG");
        sb.append("    ,SP.PSN_NUM");  // 2016/05/12 S21_NA#7861 Mod PSN_CD -> PSN_NUM
        sb.append("    ,T.TOC_NM");
        sb.append("    ,SP.LINE_BIZ_TP_CD");
        sb.append("    ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("    ,CB.COA_BR_NM");
        sb.append("    ,T.TOC_CD");
        sb.append(" FROM ");
        sb.append("     TOC T");
        sb.append("    ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("    ,BIZ_AREA_ORG BA");
        sb.append("    ,ORG_FUNC_ASG OFA");
        sb.append("    ,S21_PSN SP");
        sb.append("    ,COA_BR CB");
        sb.append("    ,S21_ORG SO "); // S21_NA#13037 Add
        sb.append(" WHERE ");
        sb.append("        T.GLBL_CMPY_CD        = '").append(glblCmpyCd).append("'");
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
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        sb.append("    AND OFA.TOC_CD            =  T.TOC_CD");
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        // 2020/04/02 QC#56425 Add Start
        sb.append("    AND OFA.EFF_FROM_DT       <= '").append(ZYPDateUtil.getSalesDate()).append("'");
        sb.append("    AND (OFA.EFF_THRU_DT      >= '").append(ZYPDateUtil.getSalesDate()).append("' OR OFA.EFF_THRU_DT IS NULL)");
        sb.append("    AND SO.RGTN_STS_CD        != '").append(RGTN_STS.TERMINATED).append("'");
        // 2020/04/02 QC#56425 Add End
        // S21_NA#13037 Add Start
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        // S21_NA#13037 Add End
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND SP.PSN_CD             = OFA.PSN_CD");
        sb.append("    AND SP.EFF_FROM_DT       <= '").append(ZYPDateUtil.getSalesDate()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT      >= '").append(ZYPDateUtil.getSalesDate()).append("' OR SP.EFF_THRU_DT IS NULL)");
        // 2020/04/02 QC#56425 Mod Start
        // sb.append("    AND CB.GLBL_CMPY_CD       = T.GLBL_CMPY_CD");
        // sb.append("    AND CB.EZCANCELFLAG       = '0'");
        // sb.append("    AND CB.COA_BR_CD          = T.COA_BR_CD");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD(+)");
        sb.append("    AND CB.EZCANCELFLAG(+)    = '0'");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD(+)");
        // 2020/04/02 QC#56425 Mod End
        // S21_NA#4338 modify end

        return sb.toString();
    }

    /**
     * setSlsrepWhereList
     * @param scrnMsg NWAL1600BMsg
     * @param psnCd String
     * @param tocNm String
     * @return List<Objext[]> List
     */
    public static List<Object[]> setSlsrepWhereList(NWAL1600BMsg scrnMsg, String psnCd, String tocNm) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        // 2016/05/12 S21_NA#7861 Mod Start
        // Object[] whereArray0 = new Object[4];
        // whereArray0[0] = PSN_CD_LB;
        // whereArray0[1] = PSN_CD_COLUMN;
        // whereArray0[2] = psnCd;
        // whereArray0[3] = ZYPConstant.FLG_ON_Y;
        // whereList.add(whereArray0);
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = PSN_NUM_LB;
        whereArray0[1] = PSN_NUM_COLUMN;

        // S21_NA#8393 Mod Start
        // whereArray0[2] = psnCd;
        if (ZYPCommonFunc.hasValue(psnCd) || ZYPCommonFunc.hasValue(tocNm)) {
            whereArray0[2] = psnCd;
        } else {
            whereArray0[2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);
        // 2016/05/12 S21_NA#7861 Mod End

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = SLSREP_NM_LB;
        whereArray1[1] = SLSREP_NM_COLUMN;
        whereArray1[2] = tocNm;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }

    /**
     * setSlsrepTblColumnList
     * @param scrnMsg NWAL1600BMsg
     * @return List<Objext[]> columnList
     */
    public static List<Object[]> setSlsrepTblColumnList(NWAL1600BMsg scrnMsg) {
        List<Object[]> columnList = new ArrayList<Object[]>();

        // 2016/05/12 S21_NA#7861 Mod Start
        // Object[] setColumnArray0 = new Object[4];
        // setColumnArray0[0] = PSN_CD_LB;
        // setColumnArray0[1] = PSN_CD_COLUMN;
        // setColumnArray0[2] = BigDecimal.valueOf(10);
        // setColumnArray0[3] = ZYPConstant.FLG_ON_Y;
        // columnList.add(setColumnArray0);
        Object[] setColumnArray0 = new Object[4];
        setColumnArray0[0] = PSN_NUM_LB;
        setColumnArray0[1] = PSN_NUM_COLUMN;
        setColumnArray0[2] = BigDecimal.valueOf(12);
        setColumnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(setColumnArray0);
        // 2016/05/12 S21_NA#7861 Mod End

        Object[] setColumnArray1 = new Object[4];
        setColumnArray1[0] = SLSREP_NM_LB;
        setColumnArray1[1] = SLSREP_NM_COLUMN;
        setColumnArray1[2] = BigDecimal.valueOf(30);
        setColumnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray1);

        Object[] setColumnArray2 = new Object[4];
        setColumnArray2[0] = LINE_BIZ_TP_CD_LB;
        setColumnArray2[1] = LINE_BIZ_TP_CD_COLUMN;
        setColumnArray2[2] = BigDecimal.valueOf(10);
        setColumnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray2);

        Object[] setColumnArray3 = new Object[4];
        setColumnArray3[0] = COA_BR_NM_LB;
        setColumnArray3[1] = COA_BR_NM_COLUMN;
        setColumnArray3[2] = BigDecimal.valueOf(20);
        setColumnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray3);

        Object[] setColumnArray4 = new Object[4];
        setColumnArray4[0] = ORG_FUNC_ROLE_TP_NM_LB;
        setColumnArray4[1] = ORG_FUNC_ROLE_TP_NM_COLUMN;
        setColumnArray4[2] = BigDecimal.valueOf(20);
        setColumnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray4);

        Object[] setColumnArray5 = new Object[4];
        setColumnArray5[0] = TOC_CD_LB;
        setColumnArray5[1] = TOC_CD_COLUMN;
        setColumnArray5[2] = BigDecimal.valueOf(0);
        setColumnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray5);

        return columnList;
    }

    /**
     * pop up array.
     * @param p NWAL1600_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popup(NWAL1600_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_P;
        }
        return param;
    }

    /**
     * setSlsrepSortList
     * @param scrnMsg NWAL1600BMsg
     * @return List<Objext[]> sortCondList
     */
    public static List<Object[]> setSlsrepSortList(NWAL1600BMsg scrnMsg) {
        List<Object[]> sortCondList = new ArrayList<Object[]>();

        // S21_NA#823 modify start
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = PSN_NUM_COLUMN; // 2016/05/12 S21_NA#7861 Mod PSN_CD_COLUMN -> PSN_NUM_COLUMN
        sortConditionArray0[1] = "ASC";
        sortCondList.add(sortConditionArray0);
        // S21_NA#823 modify end

        return sortCondList;
    }

    /**
     * ezItemValidationAllItem
     * @param scrnMsg NWAL1600BMsg
     * @param neesMandatoryCheck boolean
     */
    public static void ezItemValidationAllItem(NWAL1600BMsg scrnMsg, boolean neesMandatoryCheck) {

        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {

            return;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWAL1600_ABMsg line = scrnMsg.A.no(i);
            if (neesMandatoryCheck | ZYPCommonFunc.hasValue(line.tocNm_A)) {

                scrnMsg.addCheckItem(line.tocNm_A);
            }
            // 2016/05/12 S21_NA#7861 Mod Start
            // if (neesMandatoryCheck | ZYPCommonFunc.hasValue(line.psnCd_A)) {
            //
            // scrnMsg.addCheckItem(line.psnCd_A);
            // }
            if (neesMandatoryCheck | ZYPCommonFunc.hasValue(line.psnNum_A)) {

                scrnMsg.addCheckItem(line.psnNum_A);
            }
            // 2016/05/12 S21_NA#7861 Mod End
            if (neesMandatoryCheck | ZYPCommonFunc.hasValue(line.slsRepCrPct_A)) {

                scrnMsg.addCheckItem(line.slsRepCrPct_A);
            }
        }

        if (MODE_EDIT_ALL.equals(scrnMsg.xxModeCd.getValue())) {

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                NWAL1600_BBMsg line = scrnMsg.B.no(i);
                if (neesMandatoryCheck | ZYPCommonFunc.hasValue(line.tocNm_B)) {

                    scrnMsg.addCheckItem(line.tocNm_B);
                }
                // 2016/05/12 S21_NA#7861 Mod Start
                // if (neesMandatoryCheck | ZYPCommonFunc.hasValue(line.psnCd_B)) {
                //
                // scrnMsg.addCheckItem(line.psnCd_B);
                // }
                if (neesMandatoryCheck | ZYPCommonFunc.hasValue(line.psnNum_B)) {

                    scrnMsg.addCheckItem(line.psnNum_B);
                }
                // 2016/05/12 S21_NA#7861 Mod End
            }
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * setLabelItem
     * @param scrnMsg NWAL1600BMsg
     */
    public static void setLabelItem(NWAL1600BMsg scrnMsg) {

        scrnMsg.cpoOrdNum.setInputProtected(true);
        // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        // scrnMsg.dsOrdPosnNum.setInputProtected(true);
        scrnMsg.xxComnScrColValTxt.setInputProtected(true);
        // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            NWAL1600_ABMsg line = scrnMsg.A.no(i);
            line.coaBrNm_A.setInputProtected(true);
            line.coaCcNm_A.setInputProtected(true);
            line.coaExtnNm_A.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {

            NWAL1600_BBMsg line = scrnMsg.B.no(i);
            line.coaBrNm_B.setInputProtected(true);
            line.coaCcNm_B.setInputProtected(true);
            line.coaExtnNm_B.setInputProtected(true);
        }
    }

    /**
     * setDefaultForcus
     * @param scrnMsg NWAL1600BMsg
     */
    public static void setDefaultForcus(NWAL1600BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() > 0) {

            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NWAL1600Bean.xxChkBox_A, ZYPConstant.FLG_ON_Y);
            if (selectedRows.size() > 0) {

                scrnMsg.setFocusItem(scrnMsg.A.no(selectedRows.get(0)).xxChkBox_A);
            } else {

                scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A);
            }
        } else {

            scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
        }
    }
}
