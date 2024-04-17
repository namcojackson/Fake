/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2010.common;

import static business.servlet.NWAL2010.constant.NWAL2010Constant.AUTH_REFERENCE;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.AUTH_UPDATE;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BIZ_ID;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_APL;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_APR;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_RST;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_CMN_SUB;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.BTN_SEARCH;
import static business.servlet.NWAL2010.constant.NWAL2010Constant.SCRN_ID_00;

import java.util.List;

import business.servlet.NWAL2010.NWAL2010BMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL2010CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2016/09/23   Fujitsu         R.Nakamura      Update          QC#13958
 * 2022/12/14   Hitachi         R.Takau         Update          QC#60823
 * 2024/03/11   CITS            M.Kobayashi     Update          QC#63757
 * 2024/04/10   CITS            M.Okamura       Update          QC#63757
 *</pre>
 */
public class NWAL2010CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Protect Common Button properties.
     * @param handler Event Handler
     */
    public static void protectCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Common Button properties.
     * @param scrnMsg NWAL2010BMsg
     */
    // Start 2022/12/14 R.Takau [QC#60823,MOD]
    // public static void setCmnBtnProp(NWAL2010BMsg scrnMsg ) {
    public static void setCmnBtnProp(NWAL2010BMsg scrnMsg, S21CommonHandler handler ) {
    // END 2022/12/14 R.Takau [QC#60823,MOD]
        scrnMsg.crCardTrxTpCd.setInputProtected(true);
        scrnMsg.firstTrxInfoTxt.setInputProtected(true);
        scrnMsg.scdTrxInfoTxt.setInputProtected(true);
        scrnMsg.thirdTrxInfoTxt.setInputProtected(true);
        scrnMsg.frthTrxInfoTxt.setInputProtected(true);
        scrnMsg.fifthTrxInfoTxt.setInputProtected(true);
        scrnMsg.firstTrxInfoNum.setInputProtected(true);
        scrnMsg.scdTrxInfoNum.setInputProtected(true);
        scrnMsg.thirdTrxInfoNum.setInputProtected(true);
        scrnMsg.frthTrxInfoNum.setInputProtected(true);
        scrnMsg.fifthTrxInfoNum.setInputProtected(true);
        
        // Start 2022/12/14 R.Takau [QC#60823,MOD]
        // Mod Start 2016/09/23 QC#13958
//        if (MODE_READ_ONLY.equals(scrnMsg.xxModeCd_LK.getValue())) {
//            scrnMsg.xxRadioBtn_A.setInputProtected(true);
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctCdFlg_LK.getValue())) {
//        } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctCdFlg_LK.getValue())) {
//            scrnMsg.sellToCustCd.setInputProtected(true);
//        } else {
//            scrnMsg.sellToCustCd.setInputProtected(false);
//            scrnMsg.xxRadioBtn_A.setInputProtected(true);
//        }
        
        // Mod End 2016/09/23 QC#13958
        
        List<String> funcList = getFunctionList();
        if (funcList.contains(AUTH_UPDATE)) {
            scrnMsg.xxRadioBtn_A.setInputProtected(false);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 1, null);
            }
        } else if (funcList.contains(AUTH_REFERENCE)) {
            scrnMsg.xxRadioBtn_A.setInputProtected(true);          
        }
        
        // END 2022/12/14 R.Takau [QC#60823,MOD]
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).crCardHldNm_A.setInputProtected(true);
            scrnMsg.A.no(i).firstLineAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).scdLineAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A.setInputProtected(true);
        }
    }

    // Start 2024/04/10 M.Okamura [QC#63757,DEL]
//    /**
//     * Set Credit Card Area properties.
//     * @param handler Event Handler
//     * @param scrnMsg NWAL2010BMsg
//     */
//    public static void setCrCardProp(S21CommonHandler handler, NWAL2010BMsg scrnMsg) {
//
//        List<String> funcList = getFunctionList();
//
//        // Mod Start 2016/09/23 QC#13958
//        if (MODE_READ_ONLY.equals(scrnMsg.xxModeCd_LK.getValue())) {
//            scrnMsg.xxChkBox_C.setInputProtected(true);
//            handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, false);
//
////        if (funcList.contains(AUTH_UPDATE)) {
//        } else if (funcList.contains(AUTH_UPDATE)) {
//            scrnMsg.xxChkBox_C.setInputProtected(false);
//
//            if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_C)) {
//                handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, true);
//            } else {
//                handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, false);
//            }
//
//        } else if (funcList.contains(AUTH_REFERENCE)) {
//            scrnMsg.xxChkBox_C.setInputProtected(true);
//            handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, false);
//        }
//        // Mod End 2016/09/23 QC#13958
//    }
    // End 2024/04/10 M.Okamura [QC#63757,DEL]
    
    /**
     * Table has an id attribute of the argument row background color, change the argument groupRows
     * every alternate line groups.
     * @param scrnMsg NWAL2010BMsg
     */
    public static void setBgColor(NWAL2010BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    // Start 2024/04/10 M.Okamura [QC#63757,DEL]
//    /**
//     * validateCreditCard
//     * @param scrnMsg Screen Msg
//     */
//    public static void validateCreditCard(NWAL2010BMsg scrnMsg) {
//
//        scrnMsg.addCheckItem(scrnMsg.crCardCustRefNum_C);
//        scrnMsg.addCheckItem(scrnMsg.crCardExprYrMth_C);
//        scrnMsg.addCheckItem(scrnMsg.crCardHldNm_C);
//        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_C);
//        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_C);
//        scrnMsg.addCheckItem(scrnMsg.ctyAddr_C);
//        scrnMsg.addCheckItem(scrnMsg.postCd_C);
//        scrnMsg.addCheckItem(scrnMsg.stCd_C);
//        scrnMsg.addCheckItem(scrnMsg.ctryCd_C);
//
//        scrnMsg.putErrorScreen();
//    }
    // End 2024/04/10 M.Okamura [QC#63757,DEL]

    /**
     * Set Error Field
     * @param handler Event Handler
     * @param scrnMsg Screen Msg
     */
    public static void setErrorField(S21CommonHandler handler, NWAL2010BMsg scrnMsg) {

        NWAL2010CommonLogic.protectCmnBtnProp(handler);
        handler.setButtonEnabled(BTN_SEARCH, false);
        // Start 2024/04/10 M.Okamura [QC#63757,DEL]
//        handler.setButtonEnabled(BTN_INSERT_NEW_CARDS, false);
//        scrnMsg.xxChkBox_C.setInputProtected(true);
        // End 2024/04/10 M.Okamura [QC#63757,DEL]
    }

    /**
     * get Function List
     * @return Function List
     */
    private static List<String> getFunctionList() {

//        List<String> funcList = new ArrayList<String>();
//        funcList.add(AUTH_UPDATE);
//        funcList.add(AUTH_REFERENCE);
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate this screen(" + BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        return funcList;
    }

    // START 2024/04/09 M.Okamura [QC#63757,DEL]
    // START 2024/03/11 M.Kobayashi [QC#63757,ADD]
    /**
     * createCreditCardField
     * @param handler S21CommonHandler
     * @param ctx EZDApplicationContext
     * @param bMsg EZDBMsg
     */
//    public static void createCreditCardField(S21CommonHandler handler, EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;

//        if (ZYPCommonFunc.hasValue(scrnMsg.xxRqstNumSrchTxt_T)) {
//            scrnMsg.setMessageInfo("ZZSM0001E", new String[] {scrnMsg.xxRqstNumSrchTxt_T.getValue() });
//       }
//        scrnMsg.xxRqstNumSrchTxt_T.clear();

//        NWAL2010TceppsRequestResponce tr = new NWAL2010TceppsRequestResponce();
//        tr.loadRequestParams(scrnMsg, ctx.getHttpServletRequest().getRequestURL().toString());

//        setCrCardProp(handler, scrnMsg);
//    }
    // END 2024/03/11 M.Kobayashi [QC#63757,ADD]
    // END 2024/04/09 M.Okamura [QC#63757,DEL]
}
