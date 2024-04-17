/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1300.common;

import static business.servlet.NSAL1300.constant.NSAL1300Constant.BIZ_ID;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_BTN_1;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_BTN_3;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_BTN_4;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_BTN_5;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_BTN_6;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_BTN_7;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_BTN_8;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_RESET;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_RETURN;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.FUNC_ID_EDT;
import static business.servlet.NSAL1300.constant.NSAL1300Constant.FUNC_ID_INQ;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1300.NSAL1300BMsg;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;


/**
 *<pre>
 * Counters Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 * 2016/10/27   Hitachi         N.Arai          Update          QC#10729
 *</pre>
 */
public class NSAL1300CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1300BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1300BMsg scrnMsg) {

        List<String> functionList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);        
        activateButtons(handler, functionList);
        activateScreenFields(handler, functionList, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * activateButtons
     * @param handler EZDCommonHandler
     * @param functionList  List<String>
     */
    private static void activateButtons(EZDCommonHandler handler, List<String> functionList) {

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

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
    }

    /**
     * activateScreenFields
     * @param handler EZDCommonHandler
     * @param functionList  List<String>
     * @param scrnMsg  NSAL1300BMsg
     */
    private static void activateScreenFields(EZDCommonHandler handler, List<String> functionList, NSAL1300BMsg scrnMsg) {

// START 2016/10/27 N.Arai [QC#10729, MOD]
//        if (functionList == null || functionList.isEmpty() || functionList.contains(FUNC_ID_INQ)) {
        if (functionList == null || functionList.isEmpty() || !functionList.contains(FUNC_ID_EDT)) {
// END 2016/10/27 N.Arai [QC#10729, MOD]
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).mtrEntryCmntTxt_BN.setInputProtected(true);
                scrnMsg.B.no(i).readMtrCnt_BN.setInputProtected(true);
            }
        }
    }

    /**
     * checkItemForFilter
     * @param scrnMsg NSAL1300BMsg
     */
    public static void checkItemForFilter(NSAL1300BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.mtrLbDescTxt_FN);
        scrnMsg.addCheckItem(scrnMsg.mtrLbDescTxt_FT);
        scrnMsg.addCheckItem(scrnMsg.serNum_F);

    }

    /**
     * addCheckItem
     * @param scrnMsg NSAL1300BMsg
     */
    public static void addCheckItem(NSAL1300BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.B.no(i).readMtrCnt_BN);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mtrEntryCmntTxt_BN);

        }
    }

    /**
     * controlScreenFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1300BMsg
     */
    public static void controlScreenFields(EZDCommonHandler handler, NSAL1300BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mtrLbDescTxt_A.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).dsContrNum_B.setInputProtected(true);
            scrnMsg.B.no(i).serNum_B.setInputProtected(true);
            scrnMsg.B.no(i).dsMtrReadTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).mtrLbDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).mtrReadSrcTpDescTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).mtrEntryCmntTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).dsContrNum_B.setInputProtected(true);
            scrnMsg.B.no(i).dsContrNum_B.setInputProtected(true);
        }

    }

 }
