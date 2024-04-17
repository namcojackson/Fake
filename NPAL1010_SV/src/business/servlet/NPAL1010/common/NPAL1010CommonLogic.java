/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010.common;

import static business.servlet.NPAL1010.constant.NPAL1010Constant.LEFT_TABLE_NAME;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.RIGHT_TABLE_NAME;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.BTN_SEARCH_LOCATION;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.SCREEN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1010.NPAL1010BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * NPAL1010 Location Info Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/25   Fujitsu         S.Noguchi       Create          N/A
 * 2012/09/13   Fujitsu         Y.Kamide        Update          N/A
 * 2013/05/14   Fujitsu         H.Mizutani      Update          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 * 03/18/2016   CSAI            Y.Imazu         Update          QC#3134
 * 04/14/2016   CSAI            D.Fukaya        Update          QC#6610
 * 04/21/2016   CSAI            D.Fukaya        Update          QC#2378
 * 05/04/2016   CSAI            D.Fukaya        Update          QC#7629
 *</pre>
 */
public class NPAL1010CommonLogic {

//    /**
//     * Check Update Function is owned by login user
//     * @return boolean True:Own Update Function
//     */
//    public static boolean hasRegistFuncId() {
//        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NPAL1010Constant.BUSINESS_ID);
//        for (String func : funcList) {
//            if (NPAL1010Constant.FUNC_ID_SELECT_USER.equals(func)) {
//                return true;
//            }
//        }
//        return false;
//    }
    /**
     * clearScrnItem
     * @param scrnMsg NPAL1010BMsg
     */
    public static void clearScrnItem(NPAL1010BMsg scrnMsg) {

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRopFlg_WT.getValue())) {
            scrnMsg.rtlWhCatgCd.clear();
        }
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRopFlg_IO.getValue())) {
            scrnMsg.invtyOwnrCd.clear();
        }
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRopFlg_WO.getValue())) {
            scrnMsg.whOwnrCd.clear();
        }
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRopFlg_IA.getValue())) {
            scrnMsg.invtyAcctCd.clear();
        }
        scrnMsg.xxRtrnInvtyLocSrchTxt.clear();
        scrnMsg.rtlWhNmSrchTxt.clear();
        scrnMsg.xxRtrnInvtyLocSrchTxt_RW.clear();
        scrnMsg.rtlWhNmSrchTxt_RW.clear();
        scrnMsg.postCd.clear();
        scrnMsg.xxRtrnInvtyLocSrchTxt_SW.clear();
        scrnMsg.rtlWhNmSrchTxt_SW.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.xxChkBox.clear();
        scrnMsg.firstLineAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.locRoleTpCd.clear();
        scrnMsg.addlLocNm.clear();
        scrnMsg.whMgrPsnCd_H1.clear();
        scrnMsg.fullPsnNm_H1.clear();
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * initScreenItem
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1010BMsg
     */
    public static void initScreenItem(EZDCommonHandler handler, NPAL1010BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        handler.setButtonProperties(BTN_SEARCH_LOCATION[0], BTN_SEARCH_LOCATION[1], BTN_SEARCH_LOCATION[2], 1, null);
    }

    /**
     * setScreenList
     * @param scrnMsg NPAL1010BMsg
     */
    public static void setScreenList(NPAL1010BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(LEFT_TABLE_NAME, scrnMsg.A);
        tblColor.setAlternateRowsBG(RIGHT_TABLE_NAME, scrnMsg.A);
    }

    /**
     * setHeaderItemProtected
     * @param scrnMsg NPAL1010BMsg
     */
    public static void setHeaderItemProtected(NPAL1010BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRopFlg_WT.getValue())) {
            scrnMsg.rtlWhCatgCd.setInputProtected(true);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRopFlg_IO.getValue())) {
            scrnMsg.invtyOwnrCd.setInputProtected(true);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRopFlg_WO.getValue())) {
            scrnMsg.whOwnrCd.setInputProtected(true);
        }
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRopFlg_IA.getValue())) {
            scrnMsg.invtyAcctCd.setInputProtected(true);
        }
    }

    /**
     * setListItemProtected
     * @param scrnMsg NPAL1010BMsg
     */
    public static void setListItemProtected(NPAL1010BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {

            scrnMsg.A.no(i).rtlWhNm_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCatgNm_A.setInputProtected(true);
            scrnMsg.A.no(i).invtyOwnrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).whOwnrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).invtyAcctNm_A.setInputProtected(true);
            scrnMsg.A.no(i).whMgrPsnCd_A.setInputProtected(true);
            scrnMsg.A.no(i).fullPsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).locNum_A.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A.setInputProtected(true);
            scrnMsg.A.no(i).addlLocNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxEdtAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).postCd_A.setInputProtected(true);
        }
    }

    /**
     * Clear parameters for the transition to the NPAL6050 screen.
     * @param scrnMsg NPAL1010BMsg
     */
    public static final void clearParamNPAL6050(NPAL1010BMsg scrnMsg) {

        scrnMsg.xxTblNm.clear();
        scrnMsg.xxTblCdColNm.clear();
        scrnMsg.xxTblNmColNm.clear();
        scrnMsg.xxTblSortColNm.clear();
        scrnMsg.xxScrNm.clear();
        scrnMsg.xxHdrCdLbNm.clear();
        scrnMsg.xxHdrNmLbNm.clear();
        scrnMsg.xxDtlCdLbNm.clear();
        scrnMsg.xxDtlNmLbNm.clear();
//        scrnMsg.fullPsnNm_G1.clear();
        scrnMsg.xxPopPrm_EV.clear();
    }

    /**
     * Add check item.
     * @param scrnMsg NPAL1010BMsg
     */
    public static final void addCheckItemSearchCondition(NPAL1010BMsg scrnMsg) {

        // START 2015/04/28 H.Sugawara E850 Warehouse Setup
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd);
        // END 2015/04/28 H.Sugawara E850 Warehouse Setup
        scrnMsg.addCheckItem(scrnMsg.invtyOwnrCd);
        scrnMsg.addCheckItem(scrnMsg.whOwnrCd);
        scrnMsg.addCheckItem(scrnMsg.xxRtrnInvtyLocSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNmSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.invtyAcctCd); // 10/28/2015 add
        scrnMsg.addCheckItem(scrnMsg.xxRtrnInvtyLocSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNmSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.postCd);
        scrnMsg.addCheckItem(scrnMsg.xxRtrnInvtyLocSrchTxt_SW);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNmSrchTxt_SW);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox);
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr);
        scrnMsg.addCheckItem(scrnMsg.stCd);
        // WDS R-WH025 Inventory Transaction Mizutani Start
        scrnMsg.addCheckItem(scrnMsg.locRoleTpCd);
        // WDS R-WH025 Inventory Transaction Mizutani End
        scrnMsg.addCheckItem(scrnMsg.addlLocNm);
        scrnMsg.addCheckItem(scrnMsg.whMgrPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm_H1);
    }

    /**
     * close.
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1010BMsg
     */
    public static final void setCloseCond(S21CommonHandler handler, NPAL1010BMsg scrnMsg) {

        // START 2015/04/28 H.Sugawara E850 Warehouse Setup
        scrnMsg.rtlWhCatgCd.setInputProtected(true);
        // END 2015/04/28 H.Sugawara E850 Warehouse Setup
        scrnMsg.invtyOwnrCd.setInputProtected(true);
        scrnMsg.whOwnrCd.setInputProtected(true);
        scrnMsg.xxRtrnInvtyLocSrchTxt.setInputProtected(true);
        scrnMsg.rtlWhNmSrchTxt.setInputProtected(true);
        scrnMsg.invtyAcctCd.setInputProtected(true); // 10/28/2015 add
        scrnMsg.xxRtrnInvtyLocSrchTxt_RW.setInputProtected(true);
        scrnMsg.rtlWhNmSrchTxt_RW.setInputProtected(true);
        scrnMsg.postCd.setInputProtected(true);
        scrnMsg.xxRtrnInvtyLocSrchTxt_SW.setInputProtected(true);
        scrnMsg.rtlWhNmSrchTxt_SW.setInputProtected(true);
        scrnMsg.ctyAddr.setInputProtected(true);
        scrnMsg.xxChkBox.setInputProtected(true);
        scrnMsg.firstLineAddr.setInputProtected(true);
        scrnMsg.stCd.setInputProtected(true);
        // WDS R-WH025 Inventory Transaction Mizutani Start
        scrnMsg.locRoleTpCd.setInputProtected(true);
        // WDS R-WH025 Inventory Transaction Mizutani End
        scrnMsg.addlLocNm.setInputProtected(true);
        handler.setButtonEnabled("Search_Location", false);
    }
}
