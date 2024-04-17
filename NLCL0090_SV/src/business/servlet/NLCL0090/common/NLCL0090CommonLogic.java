/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLCL0090.common;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0090.NLCL0090BMsg;
import business.servlet.NLCL0090.NLCL0090Bean;
import business.servlet.NLCL0090.NLCL0090_ABMsgArray;
import business.servlet.NLCL0090.NLCL0090_BBMsgArray;
import business.servlet.NLCL0090.constant.NLCL0090Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/06/30   Fujitsu         FXS)KF.Qian     Create          N/A
 * 2010/03/26   Fujitsu         S.Yoshida       Update          Def.854
 * 2010/04/15   Fujitsu         S.Yoshida       Update          Def.5017
 * 2013/05/23   Fujitsu         F.Saito         Update          R-OM025 Inventory Transaction
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLCL0090CommonLogic implements NLCL0090Constant {

    /**
     * initialControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0090BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NLCL0090BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        initBtnControl(handler, scrnMsg);
        initCommonBtnControl(handler, scrnMsg);
        initScrnItemControl(scrnMsg);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxOrdDplyFlg.getValue())) {

            controlScreenAfterSearch(handler, scrnMsg);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.submtFlg.getValue())) {

            btnAndscrnItemControlAfterSubmit(handler, scrnMsg);
        }
    }

    /**
     * initBtnControl
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL0090BMsg
     */
    private static void initBtnControl(EZDCommonHandler scrnAppli, NLCL0090BMsg scrnMsg) {

        scrnAppli.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        scrnAppli.setButtonProperties(BTN_SEARCH_MDSE_FROM[0], BTN_SEARCH_MDSE_FROM[1], BTN_SEARCH_MDSE_FROM[2], 1, null);
        scrnAppli.setButtonProperties(BTN_SEARCH_MDSE_TO[0], BTN_SEARCH_MDSE_TO[1], BTN_SEARCH_MDSE_TO[2], 1, null);
        scrnAppli.setButtonProperties(BTN_ADD_DTL_FROM[0], BTN_ADD_DTL_FROM[1], BTN_ADD_DTL_FROM[2], 1, null);
        scrnAppli.setButtonProperties(BTN_ADD_DTL_TO[0], BTN_ADD_DTL_TO[1], BTN_ADD_DTL_TO[2], 1, null);

        if (scrnMsg.A.getValidCount() > 0) {

            scrnAppli.setButtonProperties(BTN_DEL_DTL_FROM[0], BTN_DEL_DTL_FROM[1], BTN_DEL_DTL_FROM[2], 1, null);

        } else {

            scrnAppli.setButtonProperties(BTN_DEL_DTL_FROM[0], BTN_DEL_DTL_FROM[1], BTN_DEL_DTL_FROM[2], 0, null);
        }

        if (scrnMsg.B.getValidCount() > 0) {

            scrnAppli.setButtonProperties(BTN_DEL_DTL_TO[0], BTN_DEL_DTL_TO[1], BTN_DEL_DTL_TO[2], 1, null);

        } else {

            scrnAppli.setButtonProperties(BTN_DEL_DTL_TO[0], BTN_DEL_DTL_TO[1], BTN_DEL_DTL_TO[2], 0, null);
        }
    }

    /**
     * initCommonBtnControl
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL0090BMsg
     */
    private static void initCommonBtnControl(EZDCommonHandler scrnAppli, NLCL0090BMsg scrnMsg) {

        scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        if (scrnMsg.A.getValidCount() > 0 && scrnMsg.B.getValidCount() > 0) {

            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);

        } else {

            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
    }

    /**
     * initScrnItemControl
     * @param scrnMsg NLCL0090BMsg
     */
    private static void initScrnItemControl(NLCL0090BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhCd.setInputProtected(false);
        scrnMsg.rtlSwhCd.setInputProtected(false);
        scrnMsg.xxLinkProt_P1.setInputProtected(false);
        scrnMsg.xxLinkProt_P2.setInputProtected(false);
        scrnMsg.locStsCd.setInputProtected(false);
        scrnMsg.stkStsCd.setInputProtected(false);
        scrnMsg.firstInvtyOrdCmntTxt.setInputProtected(false);
        scrnMsg.scdInvtyOrdCmntTxt.setInputProtected(false);
        scrnMsg.thirdInvtyOrdCmntTxt.setInputProtected(false);
        scrnMsg.invtyOrdNum.setInputProtected(false);
        scrnMsg.locNm_P1.setInputProtected(true);
        scrnMsg.locNm_P2.setInputProtected(true);
        scrnMsg.soNum.setInputProtected(true);
        scrnMsg.invtyOrdStsDescTxt.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_SB.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_CL.setInputProtected(true);

        // From Header
        scrnMsg.mdseCd_AF.setInputProtected(false);
        scrnMsg.mdseCd_HF.setInputProtected(false);
        scrnMsg.invtyQty_HF.setInputProtected(false);
        scrnMsg.mdseDescShortTxt_HF.setInputProtected(true);
        scrnMsg.invtyAvalQty_HF.setInputProtected(true);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.shpgSerTakeFlg_HF.getValue())) {

            scrnMsg.serNum_HF.setInputProtected(false);

        } else {

            scrnMsg.serNum_HF.setInputProtected(true);
        }

        // To Header
        scrnMsg.mdseCd_AT.setInputProtected(false);
        scrnMsg.mdseCd_HT.setInputProtected(false);
        scrnMsg.invtyQty_HT.setInputProtected(false);
        scrnMsg.mdseDescShortTxt_HT.setInputProtected(true);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.shpgSerTakeFlg_HT.getValue())) {

            scrnMsg.serNum_HT.setInputProtected(false);

        } else {

            scrnMsg.serNum_HT.setInputProtected(true);
        }

        // From Detail
        if (scrnMsg.A.getValidCount() > 0) {

            S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLCL0090Bean.A, scrnMsg.A);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.A.no(i).xxChkBox_DF.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_DF.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_DF.setInputProtected(true);
                scrnMsg.A.no(i).invtyAvalQty_DF.setInputProtected(true);

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).shpgSerTakeFlg_DF.getValue())) {

                    scrnMsg.A.no(i).invtyQty_DF.setInputProtected(true);
                    scrnMsg.A.no(i).serNum_DF.setInputProtected(false);

                } else {

                    scrnMsg.A.no(i).invtyQty_DF.setInputProtected(false);
                    scrnMsg.A.no(i).serNum_DF.setInputProtected(true);
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).invtyAvalQty_DF)) {

                    String current = scrnMsg.A.no(i).invtyAvalQty_DF.getValue().toString();

                    if (current.length() > LENGTH_OF_INVTYAVALQTY) {

                        scrnMsg.A.no(i).invtyAvalQty_DF.clear();
                        scrnMsg.A.no(i).invtyAvalQty_DF.setErrorInfo(1, NLCM0085W);
                        scrnMsg.setMessageInfo(NLCM0085W);
                    }
                }
            }
        }

        // To Detail
        if (scrnMsg.B.getValidCount() > 0) {

            S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLCL0090Bean.B, scrnMsg.B);

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                scrnMsg.B.no(i).xxChkBox_DT.setInputProtected(false);
                scrnMsg.B.no(i).mdseCd_DT.setInputProtected(true);
                scrnMsg.B.no(i).mdseDescShortTxt_DT.setInputProtected(true);

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).shpgSerTakeFlg_DT.getValue())) {

                    scrnMsg.B.no(i).invtyQty_DT.setInputProtected(true);
                    scrnMsg.B.no(i).serNum_DT.setInputProtected(false);

                } else {

                    scrnMsg.B.no(i).invtyQty_DT.setInputProtected(false);
                    scrnMsg.B.no(i).serNum_DT.setInputProtected(true);
                }
            }
        }
    }

    /**
     * initScrnControlErr
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL0090BMsg
     */
    public static void initScrnControlErr(EZDCommonHandler scrnAppli, NLCL0090BMsg scrnMsg) {

        scrnAppli.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 0, null);
        scrnAppli.setButtonProperties(BTN_SEARCH_MDSE_FROM[0], BTN_SEARCH_MDSE_FROM[1], BTN_SEARCH_MDSE_FROM[2], 0, null);
        scrnAppli.setButtonProperties(BTN_SEARCH_MDSE_TO[0], BTN_SEARCH_MDSE_TO[1], BTN_SEARCH_MDSE_TO[2], 0, null);
        scrnAppli.setButtonProperties(BTN_ADD_DTL_FROM[0], BTN_ADD_DTL_FROM[1], BTN_ADD_DTL_FROM[2], 0, null);
        scrnAppli.setButtonProperties(BTN_ADD_DTL_TO[0], BTN_ADD_DTL_TO[1], BTN_ADD_DTL_TO[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);

        scrnMsg.mdseCd_AF.setInputProtected(true);
        scrnMsg.mdseCd_AT.setInputProtected(true);
        scrnMsg.serNum_HF.setInputProtected(true);
        scrnMsg.serNum_HT.setInputProtected(true);
        scrnMsg.invtyOrdNum.setInputProtected(true);
    }

    /**
     * setScrnItemValueClear
     * @param scrnMsg NLCL0090BMsg
     */
    public static void setScrnItemValueClear(NLCL0090BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhCd.clear();
        scrnMsg.rtlSwhCd.clear();
        scrnMsg.locNm_P1.clear();
        scrnMsg.locNm_P2.clear();
        scrnMsg.invtyOrdNum.clear();
        scrnMsg.soNum.clear();
        scrnMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        scrnMsg.stkStsCd.setValue(STK_STS.GOOD);
        scrnMsg.firstInvtyOrdCmntTxt.clear();
        scrnMsg.scdInvtyOrdCmntTxt.clear();
        scrnMsg.thirdInvtyOrdCmntTxt.clear();
        scrnMsg.invtyOrdStsDescTxt.clear();
        scrnMsg.xxTsDsp19Txt_SB.clear();
        scrnMsg.xxTsDsp19Txt_CL.clear();

        // From
        scrnMsg.mdseCd_HF.clear();
        scrnMsg.mdseDescShortTxt_HF.clear();
        scrnMsg.invtyQty_HF.clear();
        scrnMsg.invtyAvalQty_HF.clear();
        scrnMsg.serNum_HF.clear();
        scrnMsg.shpgSerTakeFlg_HF.clear();
        scrnMsg.instlBaseCtrlFlg_HF.clear();
        scrnMsg.svcMachMstrPk_HF.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        // To
        scrnMsg.mdseCd_HT.clear();
        scrnMsg.mdseDescShortTxt_HT.clear();
        scrnMsg.invtyQty_HT.clear();
        scrnMsg.serNum_HT.clear();
        scrnMsg.shpgSerTakeFlg_HT.clear();
        scrnMsg.instlBaseCtrlFlg_HT.clear();
        scrnMsg.svcMachMstrPk_HT.clear();
        ZYPTableUtil.clear(scrnMsg.B);

        // Hidden
        scrnMsg.xxScrEventNm.clear();
        scrnMsg.mdseTpCd_HF.clear();
        scrnMsg.invtyCtrlFlg_HF.clear();
        scrnMsg.invtyValFlg_HF.clear();
        scrnMsg.firstProdCtrlCd_HF.clear();
        scrnMsg.rgtnStsCd_HF.clear();
        scrnMsg.mdseTpCd_HT.clear();
        scrnMsg.invtyCtrlFlg_HT.clear();
        scrnMsg.invtyValFlg_HT.clear();
        scrnMsg.firstProdCtrlCd_HT.clear();
        scrnMsg.rgtnStsCd_HT.clear();
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdDplyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.submtFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * checkInputAddDetailFrom
     * @param scrnMsg NLCL0090BMsg
     */
    public static void checkInputAddDetailFrom(NLCL0090BMsg scrnMsg) {

        clearDetailTblErrInfo(scrnMsg);

        if ((!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_HF)) || (scrnMsg.mdseCd_HF.isError())) {

            scrnMsg.mdseDescShortTxt_HF.clear();
        }

        if (MAXLINE <= scrnMsg.A.getValidCount()) {

            scrnMsg.mdseCd_HF.setErrorInfo(1, NLCM0025E, new String[]{MAXLENGTH});

        } else {

            if (!ZYPCommonFunc.hasValue(scrnMsg.invtyQty_HF) || BigDecimal.ZERO.compareTo(scrnMsg.invtyQty_HF.getValue()) >= 0) {

                scrnMsg.invtyQty_HF.setErrorInfo(1, NLZM2277E, new String[]{"Quantity", "0"});
            }

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                if (scrnMsg.mdseCd_HF.getValue().equals(scrnMsg.A.no(i).mdseCd_DF.getValue())) {

                    scrnMsg.mdseCd_HF.setErrorInfo(1, NLCM0019E);
                    scrnMsg.A.no(i).mdseCd_DF.setErrorInfo(1, NLCM0019E);
                }
            }

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                if (scrnMsg.mdseCd_HF.getValue().equals(scrnMsg.B.no(i).mdseCd_DT.getValue())) {

                    scrnMsg.mdseCd_HF.setErrorInfo(1, NLCM0019E);
                    scrnMsg.B.no(i).mdseCd_DT.setErrorInfo(1, NLCM0019E);
                }
            }
        }

        addCheckItemAddDetailFrom(scrnMsg);
    }

    /**
     * checkInputAddDetailTo
     * @param scrnMsg NLCL0090BMsg
     */
    public static void checkInputAddDetailTo(NLCL0090BMsg scrnMsg) {

        clearDetailTblErrInfo(scrnMsg);

        if ((!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_HT)) || (scrnMsg.mdseCd_HT.isError())) {

            scrnMsg.mdseDescShortTxt_HT.clear();
        }

        if (MAXLINE <= scrnMsg.B.getValidCount()) {

            scrnMsg.mdseCd_HT.setErrorInfo(1, NLCM0025E, new String[]{MAXLENGTH});

        } else {

            if (!ZYPCommonFunc.hasValue(scrnMsg.invtyQty_HT) || BigDecimal.ZERO.compareTo(scrnMsg.invtyQty_HT.getValue()) >= 0) {

                scrnMsg.invtyQty_HT.setErrorInfo(1, NLZM2277E, new String[]{"Quantity", "0"});
            }

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                if (scrnMsg.mdseCd_HT.getValue().equals(scrnMsg.A.no(i).mdseCd_DF.getValue())) {

                    scrnMsg.mdseCd_HT.setErrorInfo(1, NLCM0019E);
                    scrnMsg.A.no(i).mdseCd_DF.setErrorInfo(1, NLCM0019E);
                }
            }

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                if (scrnMsg.mdseCd_HT.getValue().equals(scrnMsg.B.no(i).mdseCd_DT.getValue())) {

                    scrnMsg.mdseCd_HT.setErrorInfo(1, NLCM0019E);
                    scrnMsg.B.no(i).mdseCd_DT.setErrorInfo(1, NLCM0019E);
                }
            }
        }

        addCheckItemAddDetailTo(scrnMsg);
    }

    /**
     * addCheckItemAddDetailFrom
     * @param scrnMsg NLCL0090BMsg
     */
    public static void addCheckItemAddDetailFrom(NLCL0090BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.locStsCd);
        scrnMsg.addCheckItem(scrnMsg.stkStsCd);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_HF);
        scrnMsg.addCheckItem(scrnMsg.invtyQty_HF);
        scrnMsg.addCheckItem(scrnMsg.serNum_HF);
    }

    /**
     * scrnItemValueClearAfterAddDetailFrom
     * @param scrnMsg NLCL0090BMsg
     */
    public static void scrnItemValueClearAfterAddDetailFrom(NLCL0090BMsg scrnMsg) {

        scrnMsg.mdseCd_HF.clear();
        scrnMsg.mdseDescShortTxt_HF.clear();
        scrnMsg.serNum_HF.clear();
        scrnMsg.invtyQty_HF.clear();
        scrnMsg.invtyAvalQty_HF.clear();
        scrnMsg.mdseTpCd_HF.clear();
        scrnMsg.invtyCtrlFlg_HF.clear();
        scrnMsg.invtyValFlg_HF.clear();
        scrnMsg.firstProdCtrlCd_HF.clear();
        scrnMsg.rgtnStsCd_HF.clear();
        scrnMsg.shpgSerTakeFlg_HF.clear();
        scrnMsg.instlBaseCtrlFlg_HF.clear();
        scrnMsg.svcMachMstrPk_HF.clear();
    }

    /**
     * addCheckItemAddDetailTo
     * @param scrnMsg NLCL0090BMsg
     */
    public static void addCheckItemAddDetailTo(NLCL0090BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.locStsCd);
        scrnMsg.addCheckItem(scrnMsg.stkStsCd);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_HT);
        scrnMsg.addCheckItem(scrnMsg.invtyQty_HT);
        scrnMsg.addCheckItem(scrnMsg.serNum_HT);
    }

    /**
     * scrnItemValueClearAfterAddDetailTo
     * @param scrnMsg NLCL0090BMsg
     */
    public static void scrnItemValueClearAfterAddDetailTo(NLCL0090BMsg scrnMsg) {

        scrnMsg.mdseCd_HT.clear();
        scrnMsg.mdseDescShortTxt_HT.clear();
        scrnMsg.invtyQty_HT.clear();
        scrnMsg.serNum_HT.clear();
        scrnMsg.mdseTpCd_HT.clear();
        scrnMsg.invtyCtrlFlg_HT.clear();
        scrnMsg.invtyValFlg_HT.clear();
        scrnMsg.firstProdCtrlCd_HT.clear();
        scrnMsg.rgtnStsCd_HT.clear();
        scrnMsg.shpgSerTakeFlg_HT.clear();
        scrnMsg.instlBaseCtrlFlg_HT.clear();
        scrnMsg.svcMachMstrPk_HT.clear();
    }

    /**
     * clearDetailTblErrInfo
     * @param scrnMsg NLCL0090BMsg
     */
    private static void clearDetailTblErrInfo(NLCL0090BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).mdseCd_DF.clearErrorInfo();
            scrnMsg.A.no(i).mdseDescShortTxt_DF.clearErrorInfo();
            scrnMsg.A.no(i).invtyQty_DF.clearErrorInfo();
            scrnMsg.A.no(i).serNum_DF.clearErrorInfo();
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.B.no(i).mdseCd_DT.clearErrorInfo();
            scrnMsg.B.no(i).mdseDescShortTxt_DT.clearErrorInfo();
            scrnMsg.B.no(i).invtyQty_DT.clearErrorInfo();
            scrnMsg.B.no(i).serNum_DT.clearErrorInfo();
        }
    }

    /**
     * checkInputSubmit
     * @param scrnMsg NLCL0090BMsg
     */
    public static void checkInputSubmit(NLCL0090BMsg scrnMsg) {

        clearDetailTblErrInfo(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_DF.getValue())) {

                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).shpgSerTakeFlg_DF.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNum_DF)) {

                scrnMsg.A.no(i).serNum_DF.setErrorInfo(1, ZZM9000E, new String[] {"Serial" });
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNum_DF)) {

                chkDuplicateSerial(scrnMsg.A, i);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_DT.getValue())) {

                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).shpgSerTakeFlg_DT.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.B.no(i).serNum_DT)) {

                scrnMsg.B.no(i).serNum_DT.setErrorInfo(1, ZZM9000E, new String[] {"Serial" });
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).serNum_DT)) {

                chkDuplicateSerial(scrnMsg.B, i);
            }
        }

        addCheckItemSubmit(scrnMsg);
    }

    /**
     * chkDuplicateSerial
     * @param scrnTblA NLCL0090_ABMsgArray
     * @param index int
     */
    private static void chkDuplicateSerial(NLCL0090_ABMsgArray scrnTblA, int index) {

        String mdseCd = scrnTblA.no(index).mdseCd_DF.getValue();
        String serNum = scrnTblA.no(index).serNum_DF.getValue();

        for (int i = 0; i < scrnTblA.getValidCount(); i++) {

            if (i != index && mdseCd.equals(scrnTblA.no(i).mdseCd_DF.getValue()) && serNum.equals(scrnTblA.no(i).serNum_DF.getValue())) {

                scrnTblA.no(index).serNum_DF.setErrorInfo(1, NLBM1266E);
                scrnTblA.no(i).serNum_DF.setErrorInfo(1, NLBM1266E);
            }
        }
    }

    /**
     * chkDuplicateSerial
     * @param scrnTblB NLCL0090_BBMsgArray
     * @param index int
     */
    private static void chkDuplicateSerial(NLCL0090_BBMsgArray scrnTblB, int index) {

        String mdseCd = scrnTblB.no(index).mdseCd_DT.getValue();
        String serNum = scrnTblB.no(index).serNum_DT.getValue();

        for (int i = 0; i < scrnTblB.getValidCount(); i++) {

            if (i != index && mdseCd.equals(scrnTblB.no(i).mdseCd_DT.getValue()) && serNum.equals(scrnTblB.no(i).serNum_DT.getValue())) {

                scrnTblB.no(index).serNum_DT.setErrorInfo(1, NLBM1266E);
                scrnTblB.no(i).serNum_DT.setErrorInfo(1, NLBM1266E);
            }
        }
    }

    /**
     * addCheckItemSubmit
     * @param scrnMsg NLCL0090BMsg
     */
    public static void addCheckItemSubmit(NLCL0090BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.locStsCd);
        scrnMsg.addCheckItem(scrnMsg.stkStsCd);
        scrnMsg.addCheckItem(scrnMsg.firstInvtyOrdCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.scdInvtyOrdCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.thirdInvtyOrdCmntTxt);

        addCheckItemDetailFrom(scrnMsg);
        addCheckItemDetailTo(scrnMsg);
    }

    /**
     * addCheckItemDetailFrom
     * @param scrnMsg NLCL0090BMsg
     */
    public static void addCheckItemDetailFrom(NLCL0090BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_DF.getValue())) {

                continue;
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_DF);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseDescShortTxt_DF);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invtyQty_DF);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_DF);
        }
    }

    /**
     * addCheckItemDetailTo
     * @param scrnMsg NLCL0090BMsg
     */
    public static void addCheckItemDetailTo(NLCL0090BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxChkBox_DT.getValue())) {

                continue;
            }

            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_DT);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseDescShortTxt_DT);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).invtyQty_DT);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).serNum_DT);
        }
    }

    /**
     * addCheckItemDetailMdseCd
     * @param scrnMsg NLCL0090BMsg
     */
    public static void addCheckItemDetailMdseCd(NLCL0090BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_DF);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_DT);
        }
    }

    /**
     * submitControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0090BMsg
     */
    private static void controlScreenAfterSearch(EZDCommonHandler handler, NLCL0090BMsg scrnMsg) {

        commonBtnControlAfterSearch(handler);
        btnControlAfterSearch(handler);
        scrnItemControlAfterSearch(scrnMsg);
    }

    /**
     * commonBtnControlAfterSearch
     * @param scrnAppli EZDCommonHandler
     */
    private static void commonBtnControlAfterSearch(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * btnControlAfterSearch
     * @param scrnAppli EZDCommonHandler
     */
    private static void btnControlAfterSearch(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties(BTN_SEARCH_MDSE_FROM[0], BTN_SEARCH_MDSE_FROM[1], BTN_SEARCH_MDSE_FROM[2], 0, null);
        scrnAppli.setButtonProperties(BTN_SEARCH_MDSE_TO[0], BTN_SEARCH_MDSE_TO[1], BTN_SEARCH_MDSE_TO[2], 0, null);
        scrnAppli.setButtonProperties(BTN_ADD_DTL_FROM[0], BTN_ADD_DTL_FROM[1], BTN_ADD_DTL_FROM[2], 0, null);
        scrnAppli.setButtonProperties(BTN_ADD_DTL_TO[0], BTN_ADD_DTL_TO[1], BTN_ADD_DTL_TO[2], 0, null);
        scrnAppli.setButtonProperties(BTN_DEL_DTL_FROM[0], BTN_DEL_DTL_FROM[1], BTN_DEL_DTL_FROM[2], 0, null);
        scrnAppli.setButtonProperties(BTN_DEL_DTL_TO[0], BTN_DEL_DTL_TO[1], BTN_DEL_DTL_TO[2], 0, null);
    }

    /**
     * scrnItemControlAfterSearch
     * @param scrnMsg NLCL0090BMsg
     */
    private static void scrnItemControlAfterSearch(NLCL0090BMsg scrnMsg) {

        scrnMsg.rtlWhCd.setInputProtected(true);
        scrnMsg.rtlSwhCd.setInputProtected(true);
        scrnMsg.locNm_P1.setInputProtected(true);
        scrnMsg.locNm_P2.setInputProtected(true);
        scrnMsg.xxLinkProt_P1.setInputProtected(true);
        scrnMsg.xxLinkProt_P2.setInputProtected(true);
        scrnMsg.soNum.setInputProtected(true);
        scrnMsg.locStsCd.setInputProtected(true);
        scrnMsg.stkStsCd.setInputProtected(true);
        scrnMsg.firstInvtyOrdCmntTxt.setInputProtected(true);
        scrnMsg.scdInvtyOrdCmntTxt.setInputProtected(true);
        scrnMsg.thirdInvtyOrdCmntTxt.setInputProtected(true);
        scrnMsg.invtyOrdStsDescTxt.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_SB.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_CL.setInputProtected(true);
        scrnMsg.mdseCd_AF.setInputProtected(true);
        scrnMsg.mdseCd_HF.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_HF.setInputProtected(true);
        scrnMsg.invtyQty_HF.setInputProtected(true);
        scrnMsg.invtyAvalQty_HF.setInputProtected(true);
        scrnMsg.serNum_HF.setInputProtected(true);
        scrnMsg.mdseCd_AT.setInputProtected(true);
        scrnMsg.mdseCd_HT.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_HT.setInputProtected(true);
        scrnMsg.invtyQty_HT.setInputProtected(true);
        scrnMsg.serNum_HT.setInputProtected(true);
        scrnMsg.A.setInputProtected(true);
        scrnMsg.B.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            BigDecimal currentQty = scrnMsg.A.no(i).invtyAvalQty_DF.getValue();

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).invtyAvalQty_DF)) {

                String current = currentQty.toString();

                if (current.length() > LENGTH_OF_INVTYAVALQTY) {

                    scrnMsg.A.no(i).invtyAvalQty_DF.clear();
                }
            }
        }
    }

    /**
     * btnAndscrnItemControlAfterSubmit
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLCL0090BMsg
     */
    private static void btnAndscrnItemControlAfterSubmit(EZDCommonHandler scrnAppli, NLCL0090BMsg scrnMsg) {

        scrnMsg.invtyOrdNum.setInputProtected(true);
        scrnAppli.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 0, null);
    }

    /**
     * delTable
     * @param scrnMsg NLCL0090BMsg
     */
    public static void delTable(NLCL0090BMsg scrnMsg) {

        delTableFrom(scrnMsg);
        delTableTo(scrnMsg);
    }

    /**
     * delTableFrom
     * @param scrnMsg NLCL0090BMsg
     */
    public static void delTableFrom(NLCL0090BMsg scrnMsg) {

        List<Integer> selectedRowsA = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHKBOX_DF, ZYPConstant.CHKBOX_ON_Y);

        if (selectedRowsA.isEmpty()) {

            return;
        }

        ZYPTableUtil.deleteRows(scrnMsg.A, selectedRowsA);

        int lineA = 0;
        int startSameMdseLineIndex = 0;
        int chgMdseLineIndex = 0;
        int sameMdseLineCnt = 0;
        String prevMdseCd = null;
        String prevshpgSerTakeFlg = null;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(prevMdseCd) || !prevMdseCd.equals(scrnMsg.A.no(i).mdseCd_DF.getValue())) {

                chgMdseLineIndex = i;

                if (ZYPConstant.FLG_ON_Y.equals(prevshpgSerTakeFlg)) {

                    for (; startSameMdseLineIndex < chgMdseLineIndex; startSameMdseLineIndex++) {

                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(startSameMdseLineIndex).invtyQty_DF, new BigDecimal(sameMdseLineCnt));
                    }
                }

                lineA++;
                sameMdseLineCnt = 0;
                startSameMdseLineIndex = i;
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).dispFlg_DF, ZYPConstant.FLG_ON_Y);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).dispFlg_DF, ZYPConstant.FLG_OFF_N);
            }

            scrnMsg.A.no(i).invtyOrdLineNum_DF.setValue(Integer.toString(lineA));

            sameMdseLineCnt++;
            prevMdseCd = scrnMsg.A.no(i).mdseCd_DF.getValue();
            prevshpgSerTakeFlg = scrnMsg.A.no(i).shpgSerTakeFlg_DF.getValue();
        }

        chgMdseLineIndex = scrnMsg.A.getValidCount();

        if (ZYPConstant.FLG_ON_Y.equals(prevshpgSerTakeFlg)) {

            for (; startSameMdseLineIndex < chgMdseLineIndex; startSameMdseLineIndex++) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(startSameMdseLineIndex).invtyQty_DF, new BigDecimal(sameMdseLineCnt));
            }
        }

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLCL0090Bean.A, scrnMsg.A);
    }

    /**
     * delTableTo
     * @param scrnMsg NLCL0090BMsg
     */
    public static void delTableTo(NLCL0090BMsg scrnMsg) {

        List<Integer> selectedRowsB = ZYPTableUtil.getSelectedRows(scrnMsg.B, CHKBOX_DT, ZYPConstant.CHKBOX_ON_Y);

        if (selectedRowsB.isEmpty()) {

            return;
        }

        ZYPTableUtil.deleteRows(scrnMsg.B, selectedRowsB);

        int lineB = 0;
        int startSameMdseLineIndex = 0;
        int chgMdseLineIndex = 0;
        int sameMdseLineCnt = 0;
        String prevMdseCd = null;
        String prevshpgSerTakeFlg = null;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(prevMdseCd) || !prevMdseCd.equals(scrnMsg.B.no(i).mdseCd_DT.getValue())) {

                chgMdseLineIndex = i;

                if (ZYPConstant.FLG_ON_Y.equals(prevshpgSerTakeFlg)) {

                    for (; startSameMdseLineIndex < chgMdseLineIndex; startSameMdseLineIndex++) {

                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(startSameMdseLineIndex).invtyQty_DT, new BigDecimal(sameMdseLineCnt));
                    }
                }

                lineB++;
                sameMdseLineCnt = 0;
                startSameMdseLineIndex = i;
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).dispFlg_DT, ZYPConstant.FLG_ON_Y);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).dispFlg_DT, ZYPConstant.FLG_OFF_N);
            }

            scrnMsg.B.no(i).invtyOrdLineNum_DT.setValue(Integer.toString(lineB));

            sameMdseLineCnt++;
            prevMdseCd = scrnMsg.B.no(i).mdseCd_DT.getValue();
            prevshpgSerTakeFlg = scrnMsg.B.no(i).shpgSerTakeFlg_DT.getValue();
        }

        chgMdseLineIndex = scrnMsg.B.getValidCount();

        if (ZYPConstant.FLG_ON_Y.equals(prevshpgSerTakeFlg)) {

            for (; startSameMdseLineIndex < chgMdseLineIndex; startSameMdseLineIndex++) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(startSameMdseLineIndex).invtyQty_DT, new BigDecimal(sameMdseLineCnt));
            }
        }

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLCL0090Bean.B, scrnMsg.B);
    }

    /**
     * chkTableIntegrity
     * @param scrnMsg NLCL0090BMsg
     */
    public static void chkTableIntegrity(NLCL0090BMsg scrnMsg) {

        int fromLength = scrnMsg.A.getValidCount();
        int toLength = scrnMsg.B.getValidCount();

        if (fromLength == 0 || toLength == 0) {

            if (fromLength == 0) {

                scrnMsg.mdseCd_HF.clearErrorInfo();
                scrnMsg.mdseCd_HF.setErrorInfo(1, NLCM0043E);
                scrnMsg.addCheckItem(scrnMsg.mdseCd_HF);
            }

            if (toLength == 0) {

                scrnMsg.mdseCd_HT.clearErrorInfo();
                scrnMsg.mdseCd_HT.setErrorInfo(1, NLCM0043E);
                scrnMsg.addCheckItem(scrnMsg.mdseCd_HT);
            }

            scrnMsg.putErrorScreen();
        }

        fromLength = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).dispFlg_DF.getValue())) {

                fromLength++;
            }
        }

        toLength = 0;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).dispFlg_DT.getValue())) {

                toLength++;
            }
        }

        if (fromLength >= 2 && toLength >= 2) {

            scrnMsg.mdseCd_HF.clearErrorInfo();
            scrnMsg.mdseCd_HF.setErrorInfo(1, NLCM0043E);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_HF);
            scrnMsg.mdseCd_HT.clearErrorInfo();
            scrnMsg.mdseCd_HT.setErrorInfo(1, NLCM0043E);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_HT);
            scrnMsg.putErrorScreen();
        }
    }

    /**
     * setErrMsgIdBeforeSubmit
     * @param scrnMsg NLCL0090BMsg
     */
    public static void setErrMsgIdBeforeSubmit(NLCL0090BMsg scrnMsg) {

        setErrMsgIdForStringItem(scrnMsg, NLCL0090Bean.mdseCd_HF, scrnMsg.mdseCd_HF);
        setErrMsgIdForStringItem(scrnMsg, NLCL0090Bean.mdseCd_HT, scrnMsg.mdseCd_HT);
        setErrMsgIdForBigDecimalItem(scrnMsg, NLCL0090Bean.invtyQty_HF, scrnMsg.invtyQty_HF);
        setErrMsgIdForStringItem(scrnMsg, NLCL0090Bean.serNum_HF, scrnMsg.serNum_HF);
        setErrMsgIdForStringItem(scrnMsg, NLCL0090Bean.serNum_HT, scrnMsg.serNum_HT);
        setErrMsgIdForBigDecimalItem(scrnMsg, NLCL0090Bean.invtyQty_HT, scrnMsg.invtyQty_HT);
    }

    /**
     * setErrMsgIdForStringItem
     * @param scrnMsg NLCL0090BMsg
     * @param scrnItemVal String
     * @param scrnStringItem EZDBStringItem
     */
    private static void setErrMsgIdForStringItem(NLCL0090BMsg scrnMsg, String scrnItemVal, EZDBStringItem scrnStringItem) {

        EZDMessageInfo errMsg = scrnMsg.getErrorInfo(scrnItemVal);

        if (errMsg != null && ZZM9000E.equals(errMsg.getCode())) {

            scrnStringItem.clearErrorInfo();

        } else if (errMsg != null && errMsg.getErrorKbn() != 0) {

            scrnStringItem.clearErrorInfo();

            scrnStringItem.setErrorInfo(1, NLCM0015E);
            scrnMsg.addCheckItem(scrnStringItem);
        }

        if (ZYPCommonFunc.hasValue(scrnStringItem)) {

            scrnStringItem.setErrorInfo(1, NLCM0015E);
            scrnMsg.addCheckItem(scrnStringItem);
        }
    }

    /**
     * setErrMsgIdForBigDecimalItem
     * @param scrnMsg NLCL0090BMsg
     * @param scrnItemVal String
     * @param scrnBigDecimalItem EZDBBigDecimalItem
     */
    private static void setErrMsgIdForBigDecimalItem(NLCL0090BMsg scrnMsg, String scrnItemVal, EZDBBigDecimalItem scrnBigDecimalItem) {

        EZDMessageInfo errMsg = scrnMsg.getErrorInfo(scrnItemVal);

        if (errMsg != null && ZZM9000E.equals(errMsg.getCode())) {

            scrnBigDecimalItem.clearErrorInfo();

        } else if (errMsg != null && errMsg.getErrorKbn() != 0) {

            scrnBigDecimalItem.clearErrorInfo();

            scrnBigDecimalItem.setErrorInfo(1, NLCM0015E);
            scrnMsg.addCheckItem(scrnBigDecimalItem);
        }

        if (ZYPCommonFunc.hasValue(scrnBigDecimalItem)) {

            scrnBigDecimalItem.setErrorInfo(1, NLCM0015E);
            scrnMsg.addCheckItem(scrnBigDecimalItem);
        }
    }

    /**
     * setParamForNPAL1010
     * @param scrnMsg NLCL0090BMsg
     * @return params
     */
    public static Object[] setParamForNPAL1010(NLCL0090BMsg scrnMsg) {

        int i = 0;

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.xxLocRoleTpCdListTxt_P2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.locRoleTpCd_P2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.rtlWhCd);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.rtlSwhCd);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[i];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        return params;
    }

    /**
     * setParamForNMAL6800
     * @param scrnMsg NLCL0090BMsg
     * @param mdseCdItem EZDBStringItem
     * @return params
     */
    public static Object[] setParamForNMAL6800(NLCL0090BMsg scrnMsg, EZDBStringItem mdseCdItem) {

        int i = 0;

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, mdseCdItem);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "10");
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[i];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        return params;
    }

    /**
     * toUpperCase
     * @param arg String
     * @return String
     */
    public static String toUpperCase(String arg) {

        if (ZYPCommonFunc.hasValue(arg)) {

            return arg.toUpperCase();
        }

        return arg;
    }
}
