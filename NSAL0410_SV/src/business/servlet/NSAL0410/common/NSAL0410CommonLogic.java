/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0410.common;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0410.NSAL0410BMsg;
import business.servlet.NSAL0410.NSAL0410Bean;
import business.servlet.NSAL0410.constant.NSAL0410Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Additional Charge
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 * 2016/01/07   Hitachi         T.Tomita        Update          CSA QC#2813
 * 2016/02/16   Hitachi         K.Kasai         Update          QC#2811
 * 2016/02/24   Hitachi         K.Kasai         Update          QC#2570
 * 2016/12/06   Hitachi         K.Ochiai        Update          QC#14204
 * 2017/12/26   Hitachi         K.Kojima        Update          QC#18562
 * 2018/05/24   Hitachi         K.Kitachi       Update          QC#26223
 * 2019/02/01   Hitachi         K.Kitachi       Update          QC#29949
 *</pre>
 */
public class NSAL0410CommonLogic {

    /**
     * initialControlScreen The initial state of the screen item is set.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0410BMsg
     * @param userId User ID
     * @param glblCmpyCd String
     * @param expireDataDisable boolean
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0410BMsg scrnMsg, String userId, String glblCmpyCd, boolean expireDataDisable) {

        // clear AllProtected
        scrnMsg.setInputProtected(false);

        // CommonButton
        initCommonButton(handler, scrnMsg);

        // TableColor
        setTblBackColor(scrnMsg);

        // Screen Field Set
        controlScreenFields(handler, scrnMsg, glblCmpyCd, expireDataDisable);
        controlScreenFieldsByAuthority(handler, scrnMsg);
    }

    /**
     * addCheckItemForCheckInput Check item for checkInput
     * @param scrnMsg NSAL0410BMsg
     */
    public static final void addCheckItemForCheckInput(NSAL0410BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NSAL0410Bean.trmnDt_A, NSAL0410Bean.effFromDt_A, NSAL0410Bean.effThruDt_A, NSAL0410Bean.addlChrgAplcPct_A, NSAL0410Bean.addlChrgFlatDealPrcAmt_A }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItemForCheckInput Check item for checkInput
     * @param scrnMsg NSAL0410BMsg
     */
    public static final void dateRelationCheck(NSAL0410BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Start <= End
            if (0 < ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A.getValue(), scrnMsg.A.no(i).effThruDt_A.getValue())) {
                // Date Relation Error
                scrnMsg.A.no(i).effFromDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0062E);
                scrnMsg.A.no(i).effThruDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0062E);
            }
            // Start <= Terminate
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).trmnDt_A)) {
                if (0 < ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A.getValue(), scrnMsg.A.no(i).trmnDt_A.getValue())) {
                    // Date compare Error
                    scrnMsg.A.no(i).trmnDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0346E, new String[] {"Terminate Date", "Start Date" });
                }
            }
            // Terminate <= End
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).trmnDt_A)) {
                if (0 < ZYPDateUtil.compare(scrnMsg.A.no(i).trmnDt_A.getValue(), scrnMsg.A.no(i).effThruDt_A.getValue())) {
                    // Date compare Error
                    scrnMsg.A.no(i).trmnDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0347E, new String[] {"Terminate Date", "End Date" });
                }
            }
            // START 2016/01/07 T.Tomita [QC#2813, ADD]
            // Check [Flat Price][Applicable%]
            if (!isZero(scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A) && !isZero(scrnMsg.A.no(i).addlChrgAplcPct_A)) {
                scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setErrorInfo(1, NSAL0410Constant.NSAM0407E);
                scrnMsg.A.no(i).addlChrgAplcPct_A.setErrorInfo(1, NSAL0410Constant.NSAM0407E);
            }
            // END 2016/01/07 T.Tomita [QC#2813, ADD]
        }
        // START 2016/01/07 T.Tomita [QC#2813, MOD]
        scrnMsg.A.setCheckParam(new String[] {NSAL0410Bean.effFromDt_A, NSAL0410Bean.effThruDt_A, NSAL0410Bean.trmnDt_A, NSAL0410Bean.addlChrgFlatDealPrcAmt_A, NSAL0410Bean.addlChrgAplcPct_A }, 1);
        // END 2016/01/07 T.Tomita [QC#2813, MOD]
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItemForDoProcess Check item for doProcess
     * @param scrnMsg NSAL0410BMsg
     */
    public static final void addCheckItemForDoProcess(NSAL0410BMsg scrnMsg) {
        // START 2016/12/06 K.Ochiai [QC#14204, ADD]
        // START 2017/12/26 K.Kojima [QC#18562,MOD]
        // scrnMsg.A.setCheckParam(new String[] {NSAL0410Bean.xxChkBox_A, NSAL0410Bean.bllgCycleCd_SE, NSAL0410Bean.effFromDt_A, NSAL0410Bean.effThruDt_A, NSAL0410Bean.trmnDt_A, NSAL0410Bean.addlChrgFlatDealPrcAmt_A,
        //         NSAL0410Bean.addlChrgAplcPct_A, NSAL0410Bean.svcBillByTpCd_SE}, 1);
        scrnMsg.A.setCheckParam(new String[] {NSAL0410Bean.xxChkBox_A, NSAL0410Bean.effFromDt_A, NSAL0410Bean.effThruDt_A, NSAL0410Bean.trmnDt_A, NSAL0410Bean.addlChrgFlatDealPrcAmt_A,
                NSAL0410Bean.addlChrgAplcPct_A, NSAL0410Bean.svcBillByTpCd_SE}, 1);
        // END 2017/12/26 K.Kojima [QC#18562,MOD]
        // END 2016/12/06 K.Ochiai [QC#14204, ADD]
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
    }

    /**
     * controlScreenFields ScreenFields activity set
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0410BMsg
     * @param glblCmpyCd String
     * @param expireDataDisable boolean
     * @param glblCmpyCd Global Company Code
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0410BMsg scrnMsg, String glblCmpyCd, boolean expireDataDisable) {

        scrnMsg.dsContrNum.setInputProtected(true);
        scrnMsg.dsContrCtrlStsNm.setInputProtected(true);
        scrnMsg.svcContrBrDescTxt.setInputProtected(true);
        scrnMsg.dsContrNm.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.dsAcctNum.setInputProtected(true);

        // Sales Date
        String salesDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            /** expired DS_CONTR_ADDL_CHRG data **/
            // DS_CONTR_ADD_CHRG TrmnDt, EndDt arrived
            // (not have error check)
            if (expireDataDisable) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsContrAddlChrgPk_A) 
                        && !scrnMsg.A.no(i).xxChkBox_A.isError() 
                        && !scrnMsg.A.no(i).dsContrDtlPk_A.isError() 
                        && !scrnMsg.A.no(i).addlChrgTpCd_SE.isError()
                        && !scrnMsg.A.no(i).effFromDt_A.isError() 
                        && !scrnMsg.A.no(i).effThruDt_A.isError() 
                        && !scrnMsg.A.no(i).trmnDt_A.isError() 
                        // START 2017/12/26 K.Kojima [QC#18562,DEL]
                        // && !scrnMsg.A.no(i).bllgCycleCd_SE.isError() 
                        // END 2017/12/26 K.Kojima [QC#18562,DEL]
                        && !scrnMsg.A.no(i).svcBillByTpCd_SE.isError()
                        && !scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.isError() 
                        && !scrnMsg.A.no(i).addlChrgAplcPct_A.isError() 
                        && !scrnMsg.A.no(i).addlChrgInvTpCd_SE.isError() 
                        && !scrnMsg.A.no(i).printDtlFlg_A.isError()) {
                    if ((ZYPCommonFunc.hasValue(scrnMsg.A.no(i).trmnDt_A) && scrnMsg.A.no(i).trmnDt_A.getValue().compareTo(salesDt) < 0)
                            || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A) && scrnMsg.A.no(i).effThruDt_A.getValue().compareTo(salesDt) < 0)) {
                        scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                        scrnMsg.A.no(i).dsContrDtlPk_A.setInputProtected(true);
                        scrnMsg.A.no(i).addlChrgTpCd_SE.setInputProtected(true);
                        scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                        scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
                        scrnMsg.A.no(i).trmnDt_A.setInputProtected(true);
                        // START 2017/12/26 K.Kojima [QC#18562,DEL]
                        // scrnMsg.A.no(i).bllgCycleCd_SE.setInputProtected(true);
                        // END 2017/12/26 K.Kojima [QC#18562,DEL]
                        scrnMsg.A.no(i).svcBillByTpCd_SE.setInputProtected(true);
                        scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setInputProtected(true);
                        scrnMsg.A.no(i).addlChrgAplcPct_A.setInputProtected(true);
                        scrnMsg.A.no(i).addlChrgInvTpCd_SE.setInputProtected(true);
                        scrnMsg.A.no(i).printDtlFlg_A.setInputProtected(true);
                        // START 2019/02/01 K.Kitachi [QC#29949, ADD]
                        handler.setButtonEnabled(NSAL0410Constant.BTN_SER, i, false);
                        // END 2019/02/01 K.Kitachi [QC#29949, ADD]
                        continue;
                    }
                }
            }

            // CONTR_DTL_STS = IACTV
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).contrMachAvalFlg_A) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).contrMachAvalFlg_A.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
                    scrnMsg.A.no(i).dsContrDtlPk_A.setInputProtected(true);
                    scrnMsg.A.no(i).addlChrgTpCd_SE.setInputProtected(true);
                    scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                    scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
                    scrnMsg.A.no(i).trmnDt_A.setInputProtected(true);
                    // START 2017/12/26 K.Kojima [QC#18562,DEL]
                    // scrnMsg.A.no(i).bllgCycleCd_SE.setInputProtected(true);
                    // END 2017/12/26 K.Kojima [QC#18562,DEL]
                    scrnMsg.A.no(i).svcBillByTpCd_SE.setInputProtected(true);
                    scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setInputProtected(true);
                    scrnMsg.A.no(i).addlChrgAplcPct_A.setInputProtected(true);
                    scrnMsg.A.no(i).addlChrgInvTpCd_SE.setInputProtected(true);
                    scrnMsg.A.no(i).printDtlFlg_A.setInputProtected(true);
                    // START 2019/02/01 K.Kitachi [QC#29949, ADD]
                    handler.setButtonEnabled(NSAL0410Constant.BTN_SER, i, false);
                    // END 2019/02/01 K.Kitachi [QC#29949, ADD]
                    continue;
            }

            /** NOTexpire DS_CONTR_ADDL_CHRG data **/

            // InvoicedData can not Delete
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).addlChrgInvdFlg_A.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            }

            // RegistedData can not Change
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsContrAddlChrgPk_A.getValue())) {
                scrnMsg.A.no(i).dsContrDtlPk_A.setInputProtected(true);
                // START 2018/05/23 K.Kitachi [QC#26223, MOD]
//                scrnMsg.A.no(i).addlChrgTpCd_SE.setInputProtected(true);
//                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
//                scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
                if (scrnMsg.A.no(i).invFlg_A.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    scrnMsg.A.no(i).addlChrgTpCd_SE.setInputProtected(true);
                    scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                    scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
                    // START 2019/02/01 K.Kitachi [QC#29949, ADD]
                    handler.setButtonEnabled(NSAL0410Constant.BTN_SER, i, false);
                    // END 2019/02/01 K.Kitachi [QC#29949, ADD]
                }
                // END 2018/05/23 K.Kitachi [QC#26223, MOD]
                // START 2017/12/26 K.Kojima [QC#18562,DEL]
                // scrnMsg.A.no(i).bllgCycleCd_SE.setInputProtected(true);
                // END 2017/12/26 K.Kojima [QC#18562,DEL]
            }
            // Set protect FlatPrice ,Appl%
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcBillByTpCd_SE.getValue())) {
                String selectBillByTp = scrnMsg.A.no(i).svcBillByTpCd_SE.getValue();
                controlActvfieldByBillByTp(scrnMsg, selectBillByTp, i);
            }
        }

        // START 2017/12/26 K.Kojima [QC#18562,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).bllgCycleCd_SE.setInputProtected(true);
            // START 2019/02/01 K.Kitachi [QC#29949, ADD]
            scrnMsg.A.no(i).xxScrItem34Txt_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
            // END 2019/02/01 K.Kitachi [QC#29949, ADD]
        }
        // END 2017/12/26 K.Kojima [QC#18562,ADD]

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // mod start 2016/02/24 CSA Defect#2570
            scrnMsg.B.no(i).xxScrItem34Txt_B.setInputProtected(true);
            // mod end 2016/02/24 CSA Defect#2570
            scrnMsg.B.no(i).addlChrgTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).svcBillByTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).addlChrgInvTpDescTxt_B.setInputProtected(true);
        }
    }

    /**
     * Set protect FlatPrice ,Appl%
     * @param scrnMsg NSAL0410BMsg
     * @param selectBillByTp String
     * @param i int
     */
    public static void controlActvfieldByBillByTp(NSAL0410BMsg scrnMsg, String selectBillByTp, int i) {

        scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setInputProtected(false);
        scrnMsg.A.no(i).addlChrgAplcPct_A.setInputProtected(false);

        for (int t = 0; t < scrnMsg.T.getValidCount(); t++) {
            if (selectBillByTp.equals(scrnMsg.T.no(t).svcBillByTpCd_T.getValue())) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.T.no(t).flatPrcAvalFlg_T.getValue())) {
                    scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.setInputProtected(true);
                    scrnMsg.A.no(i).addlChrgFlatDealPrcAmt_A.clear();
                } else if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.T.no(t).pctPrcAvalFlg_T.getValue())) {
                    scrnMsg.A.no(i).addlChrgAplcPct_A.setInputProtected(true);
                    scrnMsg.A.no(i).addlChrgAplcPct_A.clear();
                }
                break;
            }
        }
    }

    /**
     * controlScreenFieldsByAuthority ScreenFields activity set by user authority
     * @param handler EZDCommonHandler
     * @param scrnMsg DWCL0120BMsg
     */
    public static final void controlScreenFieldsByAuthority(EZDCommonHandler handler, NSAL0410BMsg scrnMsg) {

        if (!hasUpdateFuncId()) {
            // All fields inactive
            allFieldsInactive(handler, scrnMsg);
        }
    }

    /**
     * allFieldsInactive
     * @param handler EZDCommonHandler
     * @param scrnMsg DWCL0120BMsg
     */
    public static final void allFieldsInactive(EZDCommonHandler handler, NSAL0410BMsg scrnMsg) {

        /** Common Button **/
        handler.setButtonEnabled(NSAL0410Constant.BTN_CMN_SUBMIT[0], false);
        handler.setButtonEnabled(NSAL0410Constant.BTN_CMN_RESET[0], false);

        /** Screen field **/
        handler.setButtonEnabled(NSAL0410Constant.BTN_DEL, false);
        handler.setButtonEnabled(NSAL0410Constant.BTN_ADD, false);
        scrnMsg.dsContrNum.setInputProtected(true);
        scrnMsg.dsContrCtrlStsNm.setInputProtected(true);
        scrnMsg.svcContrBrDescTxt.setInputProtected(true);
        scrnMsg.dsContrNm.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.dsAcctNum.setInputProtected(true);

        scrnMsg.A.setInputProtected(true);

    }

    /**
     * hasRegistFuncId
     * @return boolean false:reference true:upedate
     */
    public static boolean hasUpdateFuncId() {
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NSAL0410Constant.BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Additional Charge(" + NSAL0410Constant.BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        for (String func : funcList) {
            if (NSAL0410Constant.FUNC_ID_UPDATE.equals(func)) {
                return true;
            }
        }
        return false;
    }

    /**
     * initCommonButton
     * @param scrnMsg NSAL0410BMsg
     * @param handler S21CommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler, NSAL0410BMsg scrnMsg) {

        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_BTN_1[0], NSAL0410Constant.BTN_CMN_BTN_1[1], NSAL0410Constant.BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_SUBMIT[0], NSAL0410Constant.BTN_CMN_SUBMIT[1], NSAL0410Constant.BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_BTN_3[0], NSAL0410Constant.BTN_CMN_BTN_3[1], NSAL0410Constant.BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_BTN_4[0], NSAL0410Constant.BTN_CMN_BTN_4[1], NSAL0410Constant.BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_BTN_5[0], NSAL0410Constant.BTN_CMN_BTN_5[1], NSAL0410Constant.BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_BTN_6[0], NSAL0410Constant.BTN_CMN_BTN_6[1], NSAL0410Constant.BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_BTN_7[0], NSAL0410Constant.BTN_CMN_BTN_7[1], NSAL0410Constant.BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_BTN_8[0], NSAL0410Constant.BTN_CMN_BTN_8[1], NSAL0410Constant.BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_RESET[0], NSAL0410Constant.BTN_CMN_RESET[1], NSAL0410Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NSAL0410Constant.BTN_CMN_RETURN[0], NSAL0410Constant.BTN_CMN_RETURN[1], NSAL0410Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * setBgColor
     * @param scrnMsg NSAL0410BMsg
     */
    public static void setTblBackColor(NSAL0410BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(NSAL0410Constant.BIZ_ID + "Scrn00", scrnMsg);
        // Color Clear
        tblColor.clearRowsBG(NSAL0410Bean.A, scrnMsg.A);
        // Color on
        tblColor.setAlternateRowsBG(NSAL0410Bean.A, scrnMsg.A);
    }

    /**
     * guiAttributeClear
     * @param scrnMsg NSAL0410BMsg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NSAL0410BMsg scrnMsg, String[][] baseContents) {
        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG(NSAL0410Constant.BIZ_ID + "Scrn00", scrnMsg, baseContents);
        // clear GUI Attribute
        scrnMsg.clearAllGUIAttribute(NSAL0410Constant.BIZ_ID + "Scrn00");
    }

    /**
     * set input parameter
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL0410BMsg
     * @param arg Object[]
     */
    public static void setInputParam(EZDCommonHandler handler, NSAL0410BMsg scrnMsg, Object[] arg) {
        clearInputParam(scrnMsg);

        // no input parameter
        if (arg.length <= 0) {
            // Error
            scrnMsg.setMessageInfo(NSAL0410Constant.NSAM0219E);
            allFieldsInactive(handler, scrnMsg);
            return;
        }

        // Check Mandatory inputParam -DS_CONTR_PK
        if (arg.length > 0 && !ZYPCommonFunc.hasValue((EZDBBigDecimalItem) arg[0])) {
            // Error
            scrnMsg.setMessageInfo(NSAL0410Constant.NSAM0219E);
            allFieldsInactive(handler, scrnMsg);
            return;
        }
        // set InputParam -DS_CONTR_PK
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk_P, (EZDBBigDecimalItem) arg[0]);

        // set InputParam -DS_CONTR_DTL_PK
        // del start 2016/02/12 CSA Defect#2811
//        if (arg.length > 1 && ZYPCommonFunc.hasValue((EZDBBigDecimalItem) arg[1])) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_P, (EZDBBigDecimalItem) arg[1]);
//        }
        // del end 2016/02/12 CSA Defect#2811

        // Check Mandatory inputParam -XX_MODE_CD
        if (arg.length > 2 && !ZYPCommonFunc.hasValue((EZDBStringItem) arg[2])) {
            // Error
            scrnMsg.setMessageInfo(NSAL0410Constant.NSAM0219E);
            allFieldsInactive(handler, scrnMsg);
            return;
        }
        // set InputParam -XX_MODE_CD
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_P, (EZDBStringItem) arg[2]);
    }

    private static void clearInputParam(NSAL0410BMsg scrnMsg) {
        scrnMsg.dsContrPk_P.clear();
        scrnMsg.dsContrDtlPk_P.clear();
        scrnMsg.xxModeCd_P.clear();
    }

    // START 2016/01/07 T.Tomita [QC#2813, ADD]
    private static boolean isZero(EZDBBigDecimalItem val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return true;
        }

        if (val.getValue().compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }

        return false;
    }
    // END 2016/01/07 T.Tomita [QC#2813, ADD]
}
