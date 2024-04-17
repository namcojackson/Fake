/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3160.NLBL3160BMsg;
import business.servlet.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2022/10/07   Hitachi         T.NEMA          Update          QC#60607
 *</pre>
 */
public class NLBL3160CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3160BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NLBL3160BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(NLBL3160Constant.SCREEN_ID);

        controlScreenFields(scrnMsg);
        initCommonButton(handler);
        initButton(handler, scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param scrnMsg NLBL3160BMsg
     */
    public static void controlScreenFields(NLBL3160BMsg scrnMsg) {

        // Header
        if (ZYPCommonFunc.hasValue(scrnMsg.xxFuncId)) {
            scrnMsg.cpoNum.setInputProtected(false);

            if (NLBL3160Constant.FUNC_ID_COORD.equals(scrnMsg.xxFuncId.getValue())) {
                scrnMsg.xxLinkAncr_CP.setInputProtected(true);
                scrnMsg.psnCd.setInputProtected(true);
            } else {
                scrnMsg.xxLinkAncr_CP.setInputProtected(false);
                scrnMsg.psnCd.setInputProtected(false);
            }

            scrnMsg.xxLinkAncr_OG.setInputProtected(false);
            scrnMsg.dsOrdCatgDescTxt.setInputProtected(false);
            scrnMsg.schdCoordStsCd.setInputProtected(false);
            scrnMsg.svcConfigMstrPk.setInputProtected(false);
            scrnMsg.shpgSvcLvlCd.setInputProtected(false);
            scrnMsg.lineBizTpCd.setInputProtected(false);
            scrnMsg.xxChkBox_S.setInputProtected(false);
            scrnMsg.xxChkBox_DC.setInputProtected(false);
            scrnMsg.xxChkBox_NA.setInputProtected(false);
            scrnMsg.xxChkBox_F.setInputProtected(false);
            scrnMsg.xxChkBox_RS.setInputProtected(false);
            // START 2022/11/07 T.NEMA [QC#60607, ADD]
            scrnMsg.xxChkBox_I.setInputProtected(false);
            // END   2022/11/07 T.NEMA [QC#60607, ADD]
            scrnMsg.xxLinkAncr_MD.setInputProtected(false);
            scrnMsg.mdseCd.setInputProtected(false);
            scrnMsg.xxLinkAncr_AN.setInputProtected(false);
            scrnMsg.dsAcctNum.setInputProtected(false);
            scrnMsg.xxLinkAncr_ST.setInputProtected(false);
            scrnMsg.stCd.setInputProtected(false);
            scrnMsg.xxLinkAncr_BR.setInputProtected(false);
            scrnMsg.svcBrCd.setInputProtected(false);
            scrnMsg.rddDt_FR.setInputProtected(false);
            scrnMsg.rddDt_TO.setInputProtected(false);
            scrnMsg.xxLinkAncr_WR.setInputProtected(false);
            scrnMsg.rtlWhCd.setInputProtected(false);
            scrnMsg.xxLinkAncr_WS.setInputProtected(false);
            scrnMsg.rtlSwhCd.setInputProtected(false);
            scrnMsg.nextActDt_FR.setInputProtected(false);
            scrnMsg.nextActDt_TO.setInputProtected(false);
        } else {
            scrnMsg.cpoNum.setInputProtected(true);
            scrnMsg.xxLinkAncr_CP.setInputProtected(true);
            scrnMsg.psnCd.setInputProtected(true);
            scrnMsg.xxLinkAncr_OG.setInputProtected(true);
            scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
            scrnMsg.schdCoordStsCd.setInputProtected(true);
            scrnMsg.svcConfigMstrPk.setInputProtected(true);
            scrnMsg.shpgSvcLvlCd.setInputProtected(true);
            scrnMsg.lineBizTpCd.setInputProtected(true);
            scrnMsg.xxChkBox_S.setInputProtected(true);
            scrnMsg.xxChkBox_DC.setInputProtected(true);
            scrnMsg.xxChkBox_NA.setInputProtected(true);
            scrnMsg.xxChkBox_F.setInputProtected(true);
            scrnMsg.xxChkBox_RS.setInputProtected(true);
            // START 2022/11/07 T.NEMA [QC#60607, ADD]
            scrnMsg.xxChkBox_I.setInputProtected(true);
            // END   2022/11/07 T.NEMA [QC#60607, ADD]
            scrnMsg.xxLinkAncr_MD.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
            scrnMsg.xxLinkAncr_AN.setInputProtected(true);
            scrnMsg.dsAcctNum.setInputProtected(true);
            scrnMsg.xxLinkAncr_ST.setInputProtected(true);
            scrnMsg.stCd.setInputProtected(true);
            scrnMsg.xxLinkAncr_BR.setInputProtected(true);
            scrnMsg.svcBrCd.setInputProtected(true);
            scrnMsg.rddDt_FR.setInputProtected(true);
            scrnMsg.rddDt_TO.setInputProtected(true);
            scrnMsg.xxLinkAncr_WR.setInputProtected(true);
            scrnMsg.rtlWhCd.setInputProtected(true);
            scrnMsg.xxLinkAncr_WS.setInputProtected(true);
            scrnMsg.rtlSwhCd.setInputProtected(true);
            scrnMsg.nextActDt_FR.setInputProtected(true);
            scrnMsg.nextActDt_TO.setInputProtected(true);
        }

        // Common
        scrnMsg.xxPsnFirstMidLastNm.setInputProtected(true);
        scrnMsg.mdseNm.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.stNm.setInputProtected(true);
        scrnMsg.svcBrCdDescTxt.setInputProtected(true);
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.rtlSwhNm.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).cpoTotDealNetAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).mdlDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdCoordStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToCtyAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsSoLineStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcBrCdDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
        }
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     */
    private static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_SAVE[0], NLBL3160Constant.BTN_CMN_SAVE[1], NLBL3160Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_SUBMIT[0], NLBL3160Constant.BTN_CMN_SUBMIT[1], NLBL3160Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_APPLY[0], NLBL3160Constant.BTN_CMN_APPLY[1], NLBL3160Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_APPROVE[0], NLBL3160Constant.BTN_CMN_APPROVE[1], NLBL3160Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_REJECT[0], NLBL3160Constant.BTN_CMN_REJECT[1], NLBL3160Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_DOWNLOAD[0], NLBL3160Constant.BTN_CMN_DOWNLOAD[1], NLBL3160Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_DETELE[0], NLBL3160Constant.BTN_CMN_DETELE[1], NLBL3160Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_CLEAR[0], NLBL3160Constant.BTN_CMN_CLEAR[1], NLBL3160Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_RESET[0], NLBL3160Constant.BTN_CMN_RESET[1], NLBL3160Constant.BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(NLBL3160Constant.BTN_CMN_RETURN[0], NLBL3160Constant.BTN_CMN_RETURN[1], NLBL3160Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Method name: initButton <dd>The method explanation: init
     * @param handler EZ Common Handler
     * @param scrnMsg NLBL3160BMsg
     */
    private static void initButton(EZDCommonHandler handler, NLBL3160BMsg scrnMsg) {

        // Header
         if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId.getValue())) {
             handler.setButtonProperties(NLBL3160Constant.BTN_REFRESH[0],
             NLBL3160Constant.BTN_REFRESH[1],
             NLBL3160Constant.BTN_REFRESH[2], 0, null);
         } else {
             handler.setButtonProperties(NLBL3160Constant.BTN_REFRESH[0], NLBL3160Constant.BTN_REFRESH[1], NLBL3160Constant.BTN_REFRESH[2], 1, null);
         }
    }

    /**
     * addCheckItem
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NLBL3160BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.cpoNum);
        scrnMsg.addCheckItem(scrnMsg.psnCd);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.schdCoordStsCd);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.stCd);
        scrnMsg.addCheckItem(scrnMsg.svcBrCd);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.nextActDt_FR);
        scrnMsg.addCheckItem(scrnMsg.nextActDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_S);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_NA);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_F);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_DC);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RS);
        // START 2022/11/07 T.NEMA [QC#60607, ADD]
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_I);
        // END   2022/11/07 T.NEMA [QC#60607, ADD]

        scrnMsg.putErrorScreen();

    }

    /**
     * checkInputRefresh
     * 
     * @param scrnMsg Screen Msg
     */
    public static void checkInputRefresh(NLBL3160BMsg scrnMsg) {
        // Coordinator
        if (!ZYPCommonFunc.hasValue(scrnMsg.psnCd)) {
            scrnMsg.xxPsnFirstMidLastNm.clear();
        }
        // Item Number
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            scrnMsg.mdseNm.clear();
        }
        // Customer
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum)) {
            scrnMsg.dsAcctNm.clear();
        }
        // Deliver to ST
        if (!ZYPCommonFunc.hasValue(scrnMsg.stCd)) {
            scrnMsg.stNm.clear();
        }
        // Deliver to BR
        if (!ZYPCommonFunc.hasValue(scrnMsg.svcBrCd)) {
            scrnMsg.svcBrCdDescTxt.clear();
        }
        // Warehouse
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            scrnMsg.rtlWhNm.clear();
        }
        // Sub Warehouse
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)) {
            scrnMsg.rtlSwhNm.clear();
        }
        // START 2022/11/07 T.NEMA [QC#60607, ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_I)) {
            scrnMsg.xxChkBox_I.clear();
        }
        // END   2022/11/07 T.NEMA [QC#60607, ADD]

        // Check DataStatus
        if ((!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_S)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_NA)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_DC)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_F)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RS))
                || (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxChkBox_S.getValue())
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxChkBox_NA.getValue())
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxChkBox_DC.getValue())
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxChkBox_F.getValue())
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxChkBox_RS.getValue()))) {
            scrnMsg.xxChkBox_S.setErrorInfo(1, "NLBM1363E", new String[] {"Data Status"});
            scrnMsg.xxChkBox_NA.setErrorInfo(1, "NLBM1363E", new String[] {"Data Status"});
            scrnMsg.xxChkBox_DC.setErrorInfo(1, "NLBM1363E", new String[] {"Data Status"});
            scrnMsg.xxChkBox_F.setErrorInfo(1, "NLBM1363E", new String[] {"Data Status"});
            scrnMsg.xxChkBox_RS.setErrorInfo(1, "NLBM1363E", new String[] {"Data Status"});
            scrnMsg.setMessageInfo("ZZM9037E");
        }
        addCheckItem(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO)) {
            if (0 < scrnMsg.rddDt_FR.getValue().compareTo(scrnMsg.rddDt_TO.getValue())) {
                scrnMsg.rddDt_FR.setErrorInfo(1, "NLAM0028E", new String[] {scrnMsg.rddDt_FR.getNameForMessage(), scrnMsg.rddDt_TO.getNameForMessage() });
                scrnMsg.rddDt_TO.setErrorInfo(1, "NLAM0028E", new String[] {scrnMsg.rddDt_FR.getNameForMessage(), scrnMsg.rddDt_TO.getNameForMessage() });
                scrnMsg.setMessageInfo("ZZM9037E");
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.nextActDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.nextActDt_TO)) {
            if (0 < scrnMsg.nextActDt_FR.getValue().compareTo(scrnMsg.nextActDt_TO.getValue())) {
                scrnMsg.nextActDt_FR.setErrorInfo(1, "NLAM0028E", new String[] {scrnMsg.nextActDt_FR.getNameForMessage(), scrnMsg.nextActDt_TO.getNameForMessage() });
                scrnMsg.nextActDt_TO.setErrorInfo(1, "NLAM0028E", new String[] {scrnMsg.nextActDt_FR.getNameForMessage(), scrnMsg.nextActDt_TO.getNameForMessage() });
                scrnMsg.setMessageInfo("ZZM9037E");
            }
        }

        addCheckItem(scrnMsg);
    }

    /**
     * clear Popup Parameter
     * @param scrnMsg NLBL3160BMsg
     */
    public static void clearPopupParameter(NLBL3160BMsg scrnMsg) {
        scrnMsg.P.clear();
        scrnMsg.Z.clear();
    }
}
