/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL1130.common;


import java.util.List;

import parts.common.EZDBMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFBL1130.constant.NFBL1130Constant;
import business.servlet.NFBL1130.NFBL1130BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Accrual Write-off Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#14178
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14179
 * 2016/10/06   Hitachi         K.Kojima        Update          QC#11613
 * 2016/10/10   Hitachi         J.Kim           Update          QC#5521
 * 2016/12/21   Fujitsu         H.Ikeda         Update          QC#12865
 * 2021/03/12   CITS            H.Dimay         Update          QC#57040
 * 2022/03/28   Hitachi         A.Kohinata      Update          QC#57935(56588)
 * 2022/04/05   Hitachi         R.Onozuka       Update          QC#57935
 * 2022/07/14   Hitachi         K.Kim           Update          QC#60149
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 * </pre>
 */
public class NFBL1130CommonLogic implements NFBL1130Constant {

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1130BMsg
     */
    @SuppressWarnings("unchecked")
    public static void initControl(EZDCommonHandler handler, NFBL1130BMsg scrnMsg) {

        String userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        List funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);

        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Return Profile Setup(NFBL1130). UserID is -> " + userId);
        }
        boolean protect = true;
        // Start 2016/12/21 H.Ikeda [QC#12865,MOD]
        //if (funcList.contains(FUNC_ID_NFBL1130T010)) {
        if (funcList.contains(FUNC_ID_NFBL1130T020)) {
        // End   2016/12/21 H.Ikeda [QC#12865,MOD]
            protect = false;
        }

        if (protect) {
            setInputProtectedForRef(scrnMsg);
            setButtonForRef(handler, scrnMsg);
        } else {
            setInputProtectedForEdit(scrnMsg);
            setButtonForEdit(handler, scrnMsg);
        }
        initAppFracDigit(scrnMsg);
    }

    /**
     * @param scrnMsg NFBL1130BMsg
     */
    public static void setInputProtectedForRef(NFBL1130BMsg scrnMsg) {

        // Header
        scrnMsg.poNum.setInputProtected(false);
        scrnMsg.mdseCd.setInputProtected(false);
        scrnMsg.delyOrdNum.setInputProtected(false);
        scrnMsg.invRcvQty.setInputProtected(false);
        scrnMsg.xxChkBox_WO.setInputProtected(false);
        scrnMsg.apVndInvNum.setInputProtected(false);
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // scrnMsg.apVndCd.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        scrnMsg.prntVndNm.setInputProtected(true);
        scrnMsg.invQty.setInputProtected(false);
        scrnMsg.xxChkBox_PM.setInputProtected(false);
        scrnMsg.acrlCoaAcctCd_S.setInputProtected(false);
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.rwsNum.setInputProtected(false);
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        // add start 2022/08/05 QC#59245
        scrnMsg.xxChkBox_CV.setInputProtected(false);
        // add end 2022/08/05 QC#59245
        scrnMsg.invDt_FR.setInputProtected(false);
        scrnMsg.invDt_TO.setInputProtected(false);
        scrnMsg.stkInDt_FR.setInputProtected(false);
        scrnMsg.stkInDt_TO.setInputProtected(false);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).delyOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).stkInDt_A1.setInputProtected(true);
            // START 2016/09/29 K.Kojima [QC#14178,ADD]
            scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
            // END 2016/09/29 K.Kojima [QC#14178,ADD]
            // START 2016/09/30 K.Kojima [QC#14179,ADD]
            scrnMsg.A.no(i).prntVndCd_A1.setInputProtected(true);
            // END 2016/09/30 K.Kojima [QC#14179,ADD]
            scrnMsg.A.no(i).apVndCd_A1.setInputProtected(true);
            // START 2016/09/30 K.Kojima [QC#14179,MOD]
            // scrnMsg.A.no(i).prntVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
            // END 2016/09/30 K.Kojima [QC#14179,MOD]
            // START 2018/11/13 J.Kim [QC#23037, MOD]
            // scrnMsg.A.no(i).apVndInvSqNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(true);
            // END 2018/11/13 J.Kim [QC#23037, MOD]
            scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).thisMthFobCostAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaAcctCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            // START 2016/10/06 K.Kojima [QC#11613,MOD]
            // scrnMsg.A.no(i).mdseNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            // END 2016/10/06 K.Kojima [QC#11613,MOD]
            // add start 2022/03/28 QC#57935(56588)
            scrnMsg.A.no(i).mdseCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A2.setInputProtected(true);
            // add end 2022/03/28 QC#57935(56588)
            scrnMsg.A.no(i).apVndInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).invDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).invQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A2.setInputProtected(true);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A3.setInputProtected(true);

            scrnMsg.A.no(i).wrtOffFlg_A1.setInputProtected(true);
            scrnMsg.A.no(i).wrtOffDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).acrlWrtOffRsnCd_S.setInputProtected(true);
            scrnMsg.A.no(i).acrlWrtOffCmntTxt_A1.setInputProtected(true);
            // START 2022/04/05 R.Onozuka [QC#57935, ADD]
            scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
            // END 2022/04/05 R.Onozuka [QC#57935, ADD]
        }
    }

    /**
     * @param scrnMsg NFBL1130BMsg
     */
    public static void setInputProtectedForEdit(NFBL1130BMsg scrnMsg) {

        // Header
        scrnMsg.poNum.setInputProtected(false);
        scrnMsg.mdseCd.setInputProtected(false);
        scrnMsg.delyOrdNum.setInputProtected(false);
        scrnMsg.invRcvQty.setInputProtected(false);
        scrnMsg.xxChkBox_WO.setInputProtected(false);
        scrnMsg.apVndInvNum.setInputProtected(false);
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // scrnMsg.apVndCd.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        scrnMsg.prntVndNm.setInputProtected(true);
        scrnMsg.invQty.setInputProtected(false);
        scrnMsg.xxChkBox_PM.setInputProtected(false);
        scrnMsg.acrlCoaAcctCd_S.setInputProtected(false);
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        scrnMsg.rwsNum.setInputProtected(false);
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        // add start 2022/08/05 QC#59245
        scrnMsg.xxChkBox_CV.setInputProtected(false);
        // add end 2022/08/05 QC#59245
        scrnMsg.invDt_FR.setInputProtected(false);
        scrnMsg.invDt_TO.setInputProtected(false);
        scrnMsg.stkInDt_FR.setInputProtected(false);
        scrnMsg.stkInDt_TO.setInputProtected(false);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).delyOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).stkInDt_A1.setInputProtected(true);
            // START 2016/09/29 K.Kojima [QC#14178,ADD]
            scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
            // END 2016/09/29 K.Kojima [QC#14178,ADD]
            // START 2016/09/30 K.Kojima [QC#14179,ADD]
            scrnMsg.A.no(i).prntVndCd_A1.setInputProtected(true);
            // END 2016/09/30 K.Kojima [QC#14179,ADD]
            scrnMsg.A.no(i).apVndCd_A1.setInputProtected(true);
            // START 2016/09/30 K.Kojima [QC#14179,MOD]
            // scrnMsg.A.no(i).prntVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
            // END 2016/09/30 K.Kojima [QC#14179,MOD]
            // START 2018/11/13 J.Kim [QC#23037, MOD]
            // scrnMsg.A.no(i).apVndInvSqNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(true);
            // END 2018/11/13 J.Kim [QC#23037, MOD]
            scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).thisMthFobCostAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaAcctCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            // START 2016/10/06 K.Kojima [QC#11613,MOD]
            // scrnMsg.A.no(i).mdseNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            // END 2016/10/06 K.Kojima [QC#11613,MOD]
            // add start 2022/03/28 QC#57935(56588)
            scrnMsg.A.no(i).mdseCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A2.setInputProtected(true);
            // add end 2022/03/28 QC#57935(56588)
            scrnMsg.A.no(i).apVndInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).invDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).invQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A2.setInputProtected(true);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A3.setInputProtected(true);

            // START 2021/03/12 H.Dimay [QC#57040, MOD]
            //if (scrnMsg.A.no(i).invRcvQty_A1.getValue().compareTo(scrnMsg.A.no(i).invQty_A1.getValue()) == 0
            //||  scrnMsg.A.no(i).wrtOffFlg_BF.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            // START 2022/04/05 R.Onozuka [QC#57935, MOD]
            // mod start 2022/08/05 QC#59245
            //if (scrnMsg.A.no(i).thisMthFobCostAmt_A1.getValue().compareTo(scrnMsg.A.no(i).acInvJrnlCostAmt_A2.getValue()) != 0) {
            // Estimated Cost != Stock-In Amount
            if (scrnMsg.A.no(i).acInvJrnlCostAmt_A3.getValue().compareTo(scrnMsg.A.no(i).acInvJrnlCostAmt_A1.getValue()) != 0) {
            // mod end 2022/08/05 QC#59245
                scrnMsg.A.no(i).wrtOffFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).wrtOffDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).acrlWrtOffRsnCd_S.setInputProtected(false);
                scrnMsg.A.no(i).acrlWrtOffCmntTxt_A1.setInputProtected(false);
                // START 2022/07/14 [QC#60149, MOD]
                // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).wrtOffFlg_A1.getValue())) {
                //     scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
                // } else {
                //     scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(false);
                // }
                scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(false);
                // END 2022/07/14 [QC#60149, MOD]
            } else if (scrnMsg.A.no(i).invRcvQty_A1.getValue().compareTo(scrnMsg.A.no(i).invQty_A1.getValue()) == 0) {
            // END 2022/04/05 R.Onozuka [QC#57935, MOD]
            // END 2021/03/12 H.Dimay [QC#57040, MOD]
                scrnMsg.A.no(i).wrtOffFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).wrtOffDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).acrlWrtOffRsnCd_S.setInputProtected(true);
                scrnMsg.A.no(i).acrlWrtOffCmntTxt_A1.setInputProtected(true);
                // START 2022/04/05 R.Onozuka [QC#57935, ADD]
                scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
                // END 2022/04/05 R.Onozuka [QC#57935, ADD]

            } else {
                scrnMsg.A.no(i).wrtOffFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).wrtOffDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).acrlWrtOffRsnCd_S.setInputProtected(false);
                scrnMsg.A.no(i).acrlWrtOffCmntTxt_A1.setInputProtected(false);
                // START 2022/07/14 [QC#60149, MOD]
                // // START 2022/04/05 R.Onozuka [QC#57935, ADD]
                // if(ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).wrtOffFlg_A1.getValue())){
                //     scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
                // } else {
                //     scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(false);
                // }
                scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(false);
                // // END 2022/04/05 R.Onozuka [QC#57935, ADD]
                // END 2022/07/14 [QC#60149, MOD]
            }
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1130BMsg
     */
    public static void setButtonForRef(EZDCommonHandler handler, NFBL1130BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, true);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK4[0], BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        // START 2016/10/10 J.Kim [QC#5521,MOD]
        // handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        // END 2016/10/10 J.Kim [QC#5521,MOD] 
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL1130BMsg
     */
    public static void setButtonForEdit(EZDCommonHandler handler, NFBL1130BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, true);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK4[0], BTN_CMN_BLANK4[1], BTN_CMN_BLANK4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        // START 2016/10/10 J.Kim [QC#5521,MOD]
        // handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        // END 2016/10/10 J.Kim [QC#5521,MOD]
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Method name: clearRowsBG_A
     * <dd>The method explanation: Clear alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void clearRowsBG_A(EZDBMsg bMsg) {
        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        // START 2016/10/10 J.Kim [QC#5521,MOD]
        // tblColor.clearRowsBG(TABLE_A1, scrnMsg.A);
        // tblColor.clearRowsBG(TABLE_A2, scrnMsg.A);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        // END 2016/10/10 J.Kim [QC#5521,MOD]
    }

    /**
     * Method name: setAlternateRowsBG_A
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBG_A(EZDBMsg bMsg) {
        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        // START 2016/10/10 J.Kim [QC#5521,MOD]
        // tblColor.setAlternateRowsBG(TABLE_A1, scrnMsg.A); // Left table
        // tblColor.setAlternateRowsBG(TABLE_A2, scrnMsg.A); // Right table
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        // END 2016/10/10 J.Kim [QC#5521,MOD] 
    }

    /**
     * Method name: setAlternateRowsBGCommon
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBGCommon(EZDBMsg bMsg) {
        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        // LINES tab
        clearRowsBG_A(scrnMsg);
        setAlternateRowsBG_A(scrnMsg);
    }

    /**
     * Method name: setButtonConfirmMsg
     * <dd>The method explanation: Set Button Confirm Message.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static void setButtonConfirmMsg(EZDCommonHandler handler) {
        // Button Confirm Message Settings
        handler.setButtonConfirmMsg(BTN_CMN_SUBMIT[1], ZZM8101I, new String[] {BTN_CMN_SUBMIT[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_CLEAR[1], ZZM8101I, new String[] {BTN_CMN_CLEAR[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], ZZM8101I, new String[] {BTN_CMN_RETURN[2] }, 0);
    }

    /**
     * Get Param NWAL1130 For Vendor Location
     * @param scrnMsg NFBL1130BMsg
     * @return Param NWAL1130 For Warehouse
     */
//    public static Object[] getParamNWAL1130ForVndLocLink(NFBL1130BMsg scrnMsg, String glblCmpyCd) {
//
//        Object[] params = new Object[IDX_7];
//        params[IDX_0] = EMPTY_STRING;
//        params[IDX_1] = "Vendor Location Search";
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT ");
//        sb.append("  V.EZCANCELFLAG        AS EZCANCELFLAG ");
//        sb.append(", V.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
//        sb.append(", V.LOC_NUM             AS LOC_NUM ");
//        sb.append(", V.VND_CD              AS VND_CD ");
//        sb.append(", V.LOC_NM              AS LOC_NM ");
//        sb.append("FROM ");
//        sb.append("  VND V ");
//        sb.append("WHERE ");
//        sb.append("    V.EZCANCELFLAG   = '0' ");
//        sb.append("AND V.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");
//
//        params[IDX_2] = sb.toString();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[IDX_4];
//        whereArray0[IDX_0] = "Location Number";
//        whereArray0[IDX_1] = "UPPER(LOC_NUM)";
//        whereArray0[IDX_2] = scrnMsg.locNum.getValue();
//        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Vendor Number";
//        whereArray1[IDX_1] = "UPPER(VND_CD)";
//        whereArray1[IDX_2] = scrnMsg.apVndCd.getValue();
//        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        Object[] whereArray2 = new Object[IDX_4];
//        whereArray2[IDX_0] = "Vendor Name";
//        whereArray2[IDX_1] = "UPPER(LOC_NM)";
//        whereArray2[IDX_2] = scrnMsg.locNm.getValue();
//        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray2);
//
//        params[IDX_3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[IDX_4];
//        columnArray0[IDX_0] = "Location Number";
//        columnArray0[IDX_1] = "LOC_NUM";
//        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
//        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[IDX_4];
//        columnArray1[IDX_0] = "Vendor Number";
//        columnArray1[IDX_1] = "VND_CD";
//        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
//        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray1);
//
//        Object[] columnArray2 = new Object[IDX_4];
//        columnArray2[IDX_0] = "Vendor Name";
//        columnArray2[IDX_1] = "LOC_NM";
//        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_60);
//        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray2);
//
//        params[IDX_4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray0 = new Object[IDX_2];
//        sortConditionArray0[IDX_0] = "LOC_NUM";
//        sortConditionArray0[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray0);
//
//        Object[] sortConditionArray1 = new Object[IDX_2];
//        sortConditionArray1[IDX_0] = "VND_CD";
//        sortConditionArray1[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        Object[] sortConditionArray2 = new Object[IDX_2];
//        sortConditionArray2[IDX_0] = "LOC_NM";
//        sortConditionArray2[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray2);
//
//        params[IDX_5] = sortConditionList;
//
//        ZYPTableUtil.clear(scrnMsg.Z);
//        params[IDX_6] = scrnMsg.Z;
//
//        return params;
//    }

    /**
     * Get Param NWAL1130 For Merchandise
     * @param scrnMsg NFBL1130BMsg
     * @return Param NWAL1130 For Merchandise
     */
//    public static Object[] getParamNWAL1130ForMdseButton(NFBL1130BMsg scrnMsg, String glblCmpyCd) {
//
//        Object[] params = new Object[IDX_7];
//        params[IDX_0] = EMPTY_STRING;
//        params[IDX_1] = "Merchandise Search";
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT ");
//        sb.append("  M.EZCANCELFLAG        AS EZCANCELFLAG ");
//        sb.append(", M.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
//        sb.append(", M.MDSE_CD             AS MDSE_CD ");
//        sb.append(", M.MDSE_NM             AS MDSE_NM ");
//        sb.append("FROM ");
//        sb.append("  MDSE M ");
//        sb.append("WHERE ");
//        sb.append("    M.EZCANCELFLAG   = '0' ");
//        sb.append("AND M.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");
//
//        params[IDX_2] = sb.toString();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[IDX_4];
//        whereArray0[IDX_0] = "Merchandise Code";
//        whereArray0[IDX_1] = "UPPER(MDSE_CD)";
//        whereArray0[IDX_2] = scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.getValue();
//        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Merchandise Name";
//        whereArray1[IDX_1] = "UPPER(MDSE_NM)";
//        whereArray1[IDX_2] = scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseNm_A1.getValue();
//        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        params[IDX_3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[IDX_4];
//        columnArray0[IDX_0] = "Merchandise Code";
//        columnArray0[IDX_1] = "MDSE_CD";
//        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_16);
//        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[IDX_4];
//        columnArray1[IDX_0] = "Merchandise Name";
//        columnArray1[IDX_1] = "MDSE_NM";
//        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
//        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray1);
//
//        params[IDX_4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray0 = new Object[IDX_2];
//        sortConditionArray0[IDX_0] = "MDSE_CD";
//        sortConditionArray0[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray0);
//
//        Object[] sortConditionArray1 = new Object[IDX_2];
//        sortConditionArray1[IDX_0] = "MDSE_NM";
//        sortConditionArray1[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        params[IDX_5] = sortConditionList;
//
//        ZYPTableUtil.clear(scrnMsg.Z);
//        params[IDX_6] = scrnMsg.Z;
//
//        return params;
//    }

    /**
     * Method name: initAppFracDigit
     * <dd>The method explanation: Init App Frac Digit.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initAppFracDigit(EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).thisMthFobCostAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A2.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.A.no(i).acInvJrnlCostAmt_A3.setAppFracDigit(FRAC_DIGIT_2);
        }
    }

}
