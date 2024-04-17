/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530.common;

import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_1;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_10;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_2;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_3;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_4;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_5;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_6;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_7;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_8;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_CMN_BTN_9;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_SET_SUB_WAREHOUSENAME;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.BTN_SET_WAREHOUSENAME;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_DMND_CTOFF_DAYS_AOT;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_DMND_CTOFF_DT;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_NM_MRP_PLN_NM;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_SPLY_CTOFF_DAYS_AOT;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_SPLY_CTOFF_DT;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.FUNC_EDIT;

import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1530.NPAL1530BMsg;
/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Common (SV)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 * 01/18/2017   CITS            T.Kikuhara      Update          QC#16256
 * 01/16/2018   CITS            T.Tokutomi      Update          QC#21879
 * 04/04/2018   CITS            T.Kikuhara      Update          QC#24933
 *</pre>
 */
public class NPAL1530CommonLogic {
    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1530BMsg
     * @param functionList List<String>S
     */
    public static void ctrlScrnItemDispInit(EZDCommonHandler handler, NPAL1530BMsg scrnMsg, List<String> functionList) {
        // set protect based on authority
        setAuthorityProtect(handler, scrnMsg, functionList);
    }
    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NPAL1530BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NPAL1530BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            // column input protection
            // true : block entry
            // false : input possible
            // Search Condition
            scrnMsg.xxCmntTxt_CD.setInputProtected(false);
            scrnMsg.xxCmntTxt_NM.setInputProtected(false);
            scrnMsg.xxCmntTxt_SL.setInputProtected(false);
            scrnMsg.mrpPlnNm.setInputProtected(false);
            // QC#21879 Add.
            scrnMsg.xxLinkAncr_PN.setInputProtected(false);
            scrnMsg.rtlWhCd.setInputProtected(false);
            scrnMsg.rtlWhNm.setInputProtected(true);
            scrnMsg.rtlSwhCd.setInputProtected(false);
            scrnMsg.rtlSwhNm.setInputProtected(true);
            scrnMsg.dmndCtoffDt.setInputProtected(false);
            scrnMsg.dmndCtoffDaysAot.setInputProtected(false);
            scrnMsg.splyCtoffDt.setInputProtected(false);
            scrnMsg.splyCtoffDaysAot.setInputProtected(false);
            scrnMsg.cratPrchReqFlg.setInputProtected(false);
            scrnMsg.printItemDescFlg.setInputProtected(false);
            scrnMsg.splyCtoffDaysAot.setInputProtected(false);

            // QC#24933 ADD START
            // All CheckBox are checked.
            ZYPEZDItemValueSetter.setValue(scrnMsg.cratPrchReqFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.printItemStsFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.printItemDescFlg, ZYPConstant.FLG_ON_Y);
            // QC#24933 ADD END

            // Button activation
            handler.setButtonEnabled(BTN_SET_WAREHOUSENAME, true);
            handler.setButtonEnabled(BTN_SET_SUB_WAREHOUSENAME, true);

            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        // QC#16256 add start
        } else {
            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    private static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the name for the error message.
     * @param scrnMsg scrnMsg (BMsg)
     */
    public static void setNameForMessage(NPAL1530BMsg scrnMsg) {
        scrnMsg.mrpPlnNm.setNameForMessage(DISPLAY_NM_MRP_PLN_NM);
        scrnMsg.rtlWhCd.setNameForMessage(DISPLAY_NM_RTL_WH_CD);
        scrnMsg.rtlSwhCd.setNameForMessage(DISPLAY_NM_RTL_SWH_CD);
        scrnMsg.dmndCtoffDaysAot.setNameForMessage(DISPLAY_DMND_CTOFF_DAYS_AOT);
        scrnMsg.splyCtoffDaysAot.setNameForMessage(DISPLAY_SPLY_CTOFF_DAYS_AOT);
        scrnMsg.dmndCtoffDt.setNameForMessage(DISPLAY_DMND_CTOFF_DT);
        scrnMsg.splyCtoffDt.setNameForMessage(DISPLAY_SPLY_CTOFF_DT);
    }
}
