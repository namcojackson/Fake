/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0410.common;

import static business.servlet.NSBL0410.constant.NSBL0410Constant.BIZ_ID;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_ADD;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_APPLY;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_BTN_1;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_BTN_3;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_BTN_4;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_BTN_5;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_BTN_6;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_BTN_7;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_BTN_8;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_RESET;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_RETURN;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_DEL;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_EDT_DEP;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_FILTER;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_MCN_STS;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_P;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_SLCT_ALL;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_SRL_ASSN;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.BTN_UNSLCT_ALL;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.FUNC_ID_DSPEDT;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.FUNC_ID_EDT;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.FUNC_ID_INQ;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.NSAM0040E;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.NSBM0001E;
import static business.servlet.NSBL0410.constant.NSBL0410Constant.SCREEN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0410.NSBL0410BMsg;
import business.servlet.NSBL0410.NSBL0410Bean;
import business.servlet.NSBL0410.NSBL0410_ABMsg;
import business.servlet.NSBL0410.NSBL0410_PBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Meter Reading Counter for Interface Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Hitachi         M.Gotou         Create          N/A
 * 2016/02/26   Hitachi         M.Gotou         Update          QC#4717,4724
 * 2016/03/01   Hitachi         M.Gotou         Update          QC#4709
 * 2016/04/15   Hitachi         M.Gotou         Update          QC#3425
 * 2016/07/11   Hitachi         O.Okuma         Update          QC#11645,11677,11682,11683
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11685
 * 2016/07/14   Hitachi         O.Okuma         Update          QC#11675
 * 2018/05/24   Hitachi         U.Kim           Update          QC#22393
 * 2018/07/03   Fujitsu         T.Ogura         Update          QC#27066
 * 2018/07/05   Fujitsu         T.Ogura         Update          QC#27067
 *</pre>
 */
public class NSBL0410CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0410BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0410BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        activateButtons(handler, functionList, scrnMsg);
        // mod start 2016/07/11 CSA Defect#11682
        controlScreenFields(scrnMsg, handler, true);
        // mod end 2016/07/11 CSA Defect#11682
        setRowColors(scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0410BMsg
     */
    public static final void initialControlScreen_Del(EZDCommonHandler handler, NSBL0410BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        activateButtons(handler, functionList, scrnMsg);
        // add start 2016/07/11 CSA Defect#11683
        controlScreenFields(scrnMsg, handler, false);
        // add end 2016/07/11 CSA Defect#11683
        setRowColors(scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0410BMsg
     */
    public static final void initialControlScreen_Add(EZDCommonHandler handler, NSBL0410BMsg scrnMsg) {

        activateButtons_recover(handler, scrnMsg);
        // mod start 2016/07/11 CSA Defect#11682
        controlScreenFields(scrnMsg, handler, false);
        // mod end 2016/07/11 CSA Defect#11682
        setRowColors(scrnMsg);
    }

    /**
     * activateButtons
     * @param handler S21CommonHandler
     * @param functionList  List<String>
     * @param scrnMsg NSBL0410BMsg
     */
    private static void activateButtons(EZDCommonHandler handler, List<String> functionList, NSBL0410BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 0, null);
        handler.setButtonEnabled(BTN_SRL_ASSN, false);
        handler.setButtonEnabled(BTN_MCN_STS, false);
        handler.setButtonEnabled(BTN_EDT_DEP, false);
        // add start 2016/04/15 CSA Defect#3425
        handler.setButtonEnabled(BTN_FILTER, false);
        // add end 2016/04/15 CSA Defect#3425
        handler.setButtonEnabled(BTN_APPLY, false);
        handler.setButtonEnabled(BTN_ADD, false);
        handler.setButtonEnabled(BTN_SLCT_ALL, false);
        handler.setButtonEnabled(BTN_UNSLCT_ALL, false);
        handler.setButtonEnabled(BTN_DEL, false);
        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_DSPEDT)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(BTN_SRL_ASSN, true);
            handler.setButtonEnabled(BTN_MCN_STS, true);
            handler.setButtonEnabled(BTN_EDT_DEP, true);
            // add start 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_FILTER, true);
            // add end 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_APPLY, true);
            handler.setButtonEnabled(BTN_ADD, true);
            handler.setButtonEnabled(BTN_SLCT_ALL, true);
            handler.setButtonEnabled(BTN_UNSLCT_ALL, true);
            handler.setButtonEnabled(BTN_DEL, true);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(BTN_SRL_ASSN, true);
            handler.setButtonEnabled(BTN_MCN_STS, true);
            // add start 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_FILTER, true);
            // add end 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_APPLY, true);
            handler.setButtonEnabled(BTN_ADD, true);
            handler.setButtonEnabled(BTN_SLCT_ALL, true);
            handler.setButtonEnabled(BTN_UNSLCT_ALL, true);
            handler.setButtonEnabled(BTN_DEL, true);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
            handler.setButtonEnabled(BTN_SRL_ASSN, true);
            handler.setButtonEnabled(BTN_MCN_STS, true);
            // add start 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_FILTER, true);
            // add end 2016/04/15 CSA Defect#3425
            handler.setButtonEnabled(BTN_APPLY, true);
            handler.setButtonEnabled(BTN_ADD, true);
            handler.setButtonEnabled(BTN_SLCT_ALL, true);
            handler.setButtonEnabled(BTN_UNSLCT_ALL, true);
            handler.setButtonEnabled(BTN_DEL, true);
        }
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_SRL_ASSN, false);
            handler.setButtonEnabled(BTN_MCN_STS, false);
            handler.setButtonEnabled(BTN_APPLY, false);
            handler.setButtonEnabled(BTN_DEL, false);
        }
    }

    /**
     * activateButtons_recover
     * @param handler S21CommonHandler
     * @param scrnMsg NSBL0410BMsg
     */
    public static void activateButtons_recover(EZDCommonHandler handler, NSBL0410BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() != 0) {
            handler.setButtonEnabled(BTN_APPLY, true);
            handler.setButtonEnabled(BTN_DEL, true);
        }
    }

    /**
     * activateScreenItems
     * @param scrnMsg NSBL0410BMsg
     * @param handler EZDCommonHandler
     * @param isCtrlDesc boolean
     */
    public static void controlScreenFields(NSBL0410BMsg scrnMsg, EZDCommonHandler handler, boolean isCtrlDesc) {

        // mod start 2016/07/11 CSA Defect#11682
        if (isCtrlDesc) {
            scrnMsg.svcModPlnId.setInputProtected(true);
            scrnMsg.svcModNm.setInputProtected(true);
        }
        // mod end 2016/07/11 CSA Defect#11682

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            controlScreenDetailField(scrnMsg.A.no(i), handler, i);
        }

        // add start 2016/03/28 CSA Defect#5410
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxEndDplyTmTxt, setTm(scrnMsg.xxEndDplyTmTxt.getValue()));
        // add end 2016/03/28 CSA Defect#5410
    }

    // START 2018/05/24 U.Kim [QC#22393, ADD]
    public static void controlScreenFieldsForINIT(NSBL0410BMsg scrnMsg) {
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.mktMdlCd.setInputProtected(true);
            scrnMsg.mdseItemTpCd.setInputProtected(true);
            scrnMsg.mktMdlNm.setInputProtected(true);
        } else {
            scrnMsg.mktMdlCd.setInputProtected(false);
            scrnMsg.mdseItemTpCd.setInputProtected(false);
            scrnMsg.mktMdlNm.setInputProtected(false);
        }
    }

    // END 2018/05/24 U.Kim [QC#22393, ADD]

    private static void controlScreenDetailField(NSBL0410_ABMsg abMsg, EZDCommonHandler handler, int count) {
        String svcModCancDt = abMsg.svcModCancDt_A.getValue();
        String svcModObslDt = abMsg.svcModObslDt_A.getValue();
        // add start 2016/07/14 CSA Defect#11675
        String svcModStartDt = abMsg.svcModStartDt_A.getValue();
        // add end 2016/07/14 CSA Defect#11675

        if (chkCancFutureDate(svcModCancDt) && chkObslFutureDate(svcModObslDt)) {
            abMsg.xxChkBox_A.setInputProtected(false);
            abMsg.svcMnfCd_A.setInputProtected(false);
            abMsg.svcMnfModNum_A.setInputProtected(false);
            abMsg.svcModDocNum_A.setInputProtected(false);
            abMsg.svcModPrtyDescTxt_A.setInputProtected(false);
            abMsg.svcModRptTrkFlg_A.setInputProtected(false);
            abMsg.svcModIssDt_A.setInputProtected(false);
            abMsg.svcModOnHldDt_A.setInputProtected(false);
            abMsg.svcModCancDt_A.setInputProtected(false);
            abMsg.svcModObslDt_A.setInputProtected(false);
            abMsg.svcModObslNum_A.setInputProtected(false);
            // add start 2016/07/14 CSA Defect#11675
            if (chkStartPastDate(svcModStartDt)) {
                abMsg.svcModStartDt_A.setInputProtected(true);
            } else {
                abMsg.svcModStartDt_A.setInputProtected(false);
            }
            // add end 2016/07/14 CSA Defect#11675
            if (hasValue(abMsg.svcModDtlPk_A)) {
                handler.setButtonEnabled(BTN_P, count, true);
            } else {
                handler.setButtonEnabled(BTN_P, count, false);
            }
        } else {
            abMsg.xxChkBox_A.setInputProtected(true);
            abMsg.svcMnfCd_A.setInputProtected(true);
            abMsg.svcMnfModNum_A.setInputProtected(true);
            abMsg.svcModDocNum_A.setInputProtected(true);
            abMsg.svcModPrtyDescTxt_A.setInputProtected(true);
            abMsg.svcModRptTrkFlg_A.setInputProtected(true);
            abMsg.svcModIssDt_A.setInputProtected(true);
            abMsg.svcModOnHldDt_A.setInputProtected(true);
            abMsg.svcModCancDt_A.setInputProtected(true);
            abMsg.svcModObslDt_A.setInputProtected(true);
            abMsg.svcModObslNum_A.setInputProtected(true);
            // add start 2016/07/14 CSA Defect#11675
            abMsg.svcModStartDt_A.setInputProtected(true);
            // add end 2016/07/14 CSA Defect#11675
            handler.setButtonEnabled(BTN_P, count, false);
        }

        // del start 2016/07/14 CSA Defect#11675
        // String svcModStartDt = abMsg.svcModStartDt_A.getValue();
        // if (chkCancFutureDate(svcModCancDt) && chkObslFutureDate(svcModObslDt)
        //         && chkStartPastDate(svcModStartDt)) {
        //     abMsg.svcModStartDt_A.setInputProtected(false);
        // } else {
        //     abMsg.svcModStartDt_A.setInputProtected(true);
        // }
        // del end 2016/07/14 CSA Defect#11675
    }

     private static boolean chkCancFutureDate(String svcModCancDt) {
        if ("".equals(svcModCancDt)) {
            return true;
        }
        return (ZYPDateUtil.isFutureDate(svcModCancDt));
    }

    private static boolean chkObslFutureDate(String svcModObslDt) {
        if ("".equals(svcModObslDt)) {
            return true;
        }
        return (ZYPDateUtil.isFutureDate(svcModObslDt));
    }

    private static boolean chkStartPastDate(String svcModStartDt) {
        // mod start 2016/07/14 CSA Defect#11675
        if ("".equals(svcModStartDt)) {
            return false;
        } else if (ZYPDateUtil.isToday(svcModStartDt)) {
            return true;
        }
        // mod end 2016/07/14 CSA Defect#11675
        return (ZYPDateUtil.isPastDate(svcModStartDt));
    }

    /**
     * addCheckItem
     * @param scrnMsg NSBL0410BMsg
     */
    public static void addCheckItem(NSBL0410BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcModCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.xxEndDplyTmTxt);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.setCheckParam(new String[] {NSBL0410Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModRptTrkFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A);
        }
    }

    /**
     * addCheckItem_submit
     * @param scrnMsg NSBL0410BMsg
     */
    public static void addCheckItem_submit(NSBL0410BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcModNm);
        scrnMsg.addCheckItem(scrnMsg.svcModCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.xxEndDplyTmTxt);
        // START 2018/07/05 T.Ogura [QC#27067,MOD]
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            scrnMsg.A.setCheckParam(new String[] {NSBL0410Bean.xxChkBox_A }, 1);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcMnfModNum_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModDocNum_A);
//            // START 2018/07/03 T.Ogura [QC#27066,ADD]
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModPrtyDescTxt_A);
//            // END   2018/07/03 T.Ogura [QC#27066,ADD]
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModRptTrkFlg_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModIssDt_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModStartDt_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModOnHldDt_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModCancDt_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModObslDt_A);
//            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModObslNum_A);
//            scrnMsg.addCheckItem(scrnMsg.A);
//        }
        addCheckItem_detail(scrnMsg);
        // END   2018/07/05 T.Ogura [QC#27067,MOD]
    }

    // START 2018/07/05 T.Ogura [QC#27067,ADD]
    /**
     * addCheckItem_detail
     * @param scrnMsg NSBL0410BMsg
     */
    public static void addCheckItem_detail(NSBL0410BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.setCheckParam(new String[] {NSBL0410Bean.xxChkBox_A }, 1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcMnfModNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModDocNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModPrtyDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModRptTrkFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModIssDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModStartDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModOnHldDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModCancDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModObslDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcModObslNum_A);
            scrnMsg.addCheckItem(scrnMsg.A);
        }
    }
    // END   2018/07/05 T.Ogura [QC#27067,ADD]

    /**
     * addCheckItem_Add
     * @param scrnMsg NSBL0410BMsg
     */
    public static void addCheckItem_Add(NSBL0410BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcMnfCd);
        scrnMsg.addCheckItem(scrnMsg.svcMnfModNum);
        scrnMsg.addCheckItem(scrnMsg.svcModDocNum);
        scrnMsg.addCheckItem(scrnMsg.svcModPrtyCd);
        scrnMsg.addCheckItem(scrnMsg.svcModRptTrkFlg);
        scrnMsg.addCheckItem(scrnMsg.svcModIssDt);
        scrnMsg.addCheckItem(scrnMsg.svcModStartDt);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        // START 2018/05/31 U.Kim [QC#22393, ADD]
        scrnMsg.addCheckItem(scrnMsg.mktMdlCd);
        scrnMsg.addCheckItem(scrnMsg.mdseItemTpCd);
        // END 2018/05/31 U.Kim [QC#22393, ADD]
    }

    /**
     * addCheckItem_DetailHead
     * @param scrnMsg NSBL0410BMsg
     */
    public static void addCheckItem_DetailHead(NSBL0410BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.svcMnfCd);
        scrnMsg.addCheckItem(scrnMsg.svcMnfModNum);
        scrnMsg.addCheckItem(scrnMsg.svcModDocNum);
        scrnMsg.addCheckItem(scrnMsg.svcModPrtyCd);
        scrnMsg.addCheckItem(scrnMsg.svcModRptTrkFlg);
        scrnMsg.addCheckItem(scrnMsg.svcModIssDt);
        scrnMsg.addCheckItem(scrnMsg.svcModStartDt);
    }

   /**
    * checkDate
    * @param scrnMsg NSBL0410BMsg
    */
      public static void checkDate(NSBL0410BMsg scrnMsg) {
          String regex = "([0-1][0-9]|[2][0-3])([0-5][0-9])";
          Pattern p = Pattern.compile(regex);

          if (ZYPCommonFunc.hasValue(scrnMsg.xxEndDplyTmTxt)) {
              // mod start 2016/03/28 CSA Defect#5410
              String estLborTm = getTm(scrnMsg.xxEndDplyTmTxt.getValue());
              Matcher m = p.matcher(estLborTm);
              // mod start 2016/07/11 CSA Defect#11677
              if (!m.find()) {
              // mod end 2016/07/11 CSA Defect#11677
                  scrnMsg.xxEndDplyTmTxt.setErrorInfo(1, NSAM0040E, new String[] {"Estimated Labor" });
                  scrnMsg.addCheckItem(scrnMsg.xxEndDplyTmTxt);
                  scrnMsg.setMessageInfo(NSAM0040E);
              }
              // mod end 2016/03/28 CSA Defect#5410
          } else {
              scrnMsg.xxEndDplyTmTxt.clear();
          }
       }

/**
    * setRowColors
    * @param scrnMsg NSBL0410BMsg
    */
   public static void setRowColors(NSBL0410BMsg scrnMsg) {

       S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
       try {
           EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
           tblColor.setAlternateRowsBG("A", table);
       } catch (Throwable e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
   }

   // add start 2016/03/28 CSA Defect#5410
   /**
    * get Time
    * @param tm time
    * @return time
    */
   public static String getTm(String tm) {
       if (ZYPCommonFunc.hasValue(tm)) {
           return tm.replace(":", "");
       }
       return tm;
   }

   /**
    * set Time
    * @param tm time
    * @return time
    */
   public static String setTm(String tm) {
       // mod start 2016/07/11 CSA Defect#11645
       if (ZYPCommonFunc.hasValue(tm) && tm.indexOf(":") == -1) {
       // mod end 2016/07/11 CSA Defect#11645
           StringBuffer strBuf = new StringBuffer();
           strBuf.append(tm);
           if (strBuf.length() > 2) {
               strBuf.insert(2, ":");
           }
           return strBuf.toString();
       }
       return tm;
   }
   // add end 2016/03/28 CSA Defect#5410

   // add start 2016/07/13 CSA Defect#11685
   /**
    * setFocus
    * @param scrnMsg NSBL0410BMsg
    */
   public static void setFocus(NSBL0410BMsg scrnMsg) {

       if (scrnMsg.A.getValidCount() > 0) {
           scrnMsg.setFocusItem(scrnMsg.A.no(0).svcMnfCd_A);
       } else {
           scrnMsg.setFocusItem(scrnMsg.mdseCd);
       }
   }
   // add end 2016/07/13 CSA Defect#11685
   
   // START 2018/05/24 U.Kim [QC#22393, ADD]
   public static Object[] toArray_popup(NSBL0410_PBMsgArray p, int size) {
       Object[] param = new Object[size];
       for (int i = 0; i < size; i++) {
           param[i] = p.no(i).xxPopPrm;
       }
       return param;
   }

   public static void inputCheckForAdd(NSBL0410BMsg scrnMsg){
       boolean isError = true;
       if (hasValue(scrnMsg.mdseItemTpCd)) {
           isError = false;
       } else if (hasValue(scrnMsg.mktMdlCd)) {
           isError = false;
       } else if (hasValue(scrnMsg.mdseCd)) {
           isError = false;
       }

       if (isError) {
           scrnMsg.mdseItemTpCd.setErrorInfo(1, NSBM0001E);
           scrnMsg.mktMdlCd.setErrorInfo(1, NSBM0001E);
           scrnMsg.mdseCd.setErrorInfo(1, NSBM0001E);

           addCheckItem_Add(scrnMsg);
       }
   }
   
   // END 2018/05/24 U.Kim [QC#22393, ADD]
}
