/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6880.common;

import static business.servlet.NMAL6880.constant.NMAL6880Constant.BIZ_APP_ID;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_1;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_10;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_2;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_3;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_4;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_5;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_6;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_7;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_8;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.BTN_CMN_BTN_9;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.CSA_WH;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.CSA_WH_NM;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.EFF_FROM;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.EFF_THRU;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.FUNCTION_UPDATE;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.GMD;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.IDX_18;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.OPEN_WIN_FROM_SUB_WH_D;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.OPEN_WIN_FROM_WH_D;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.OPEN_WIN_FROM_WH_H;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.ROSS_LOC;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.SUB_WH;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6880.NMAL6880BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 * 05/23/2016   CITS            K.Ogino         Create          QC#8435
 * 05/30/2016   CITS            K.Ogino         Create          QC#8578
 * 07/12/2017   CITS            T.Kikuhara      Update          QC#19862
 *</pre>
 */
public class NMAL6880CommonLogic {

    /**
     * Set Common Button for Initialized screen
     * @param handler EZDCommonHandler
     */
    public static void setCommonButtonInit(EZDCommonHandler handler) {
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

    }

    /**
     * Set Common Button for Initialized screen
     * @param scrnMsg NMAL6880BMsg
     */
    public static void setTableColumnAttr(NMAL6880BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).vndShipToCustCd_A1.setIndispensable(true);
            scrnMsg.A.no(i).rtlWhCd_A1.setIndispensable(true);
            scrnMsg.A.no(i).rtlSwhCd_A1.setIndispensable(true);
            scrnMsg.A.no(i).effFromDt_A1.setIndispensable(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A1.setInputProtected(true);
        }
    }

    /**
     * Set Common Button for Initialized screen
     * @param scrnMsg NMAL6880BMsg
     */
    public static void chkErrorInfo(NMAL6880BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vndShipToCustCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxAllLineAddr_A1);
        }
    }

    /**
     * set parameter to call NPAL1010 popup.
     * @param scrnMsg NMAL6880BMsg
     * @param eventNm String
     * @param index int
     * @return Object[]
     */
    public static Object[] setParamForWarehousePopup(NMAL6880BMsg scrnMsg, String eventNm, int index) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();

        Object[] params = new Object[IDX_18];
        int paraCnt = 0;
        params[paraCnt++] = scrnMsg.xxPopPrm_P0;
        params[paraCnt++] = scrnMsg.xxPopPrm_P1;
        params[paraCnt++] = scrnMsg.xxPopPrm_P2;
        scrnMsg.xxPopPrm_P3.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.xxPopPrm_P4.setValue(ZYPConstant.FLG_OFF_N);
        params[paraCnt++] = scrnMsg.xxPopPrm_P3;
        params[paraCnt++] = scrnMsg.xxPopPrm_P4;
        params[paraCnt++] = scrnMsg.xxPopPrm_P5;
        if (OPEN_WIN_FROM_WH_H.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.rtlWhCd);
            params[paraCnt++] = scrnMsg.xxPopPrm_P6;
        } else if (OPEN_WIN_FROM_WH_D.equals(eventNm) || OPEN_WIN_FROM_SUB_WH_D.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(index).rtlWhCd_A1);
            params[paraCnt++] = scrnMsg.xxPopPrm_P6;
        } else {
            scrnMsg.xxPopPrm_P6.clear();
            params[paraCnt++] = scrnMsg.xxPopPrm_P6;
        }

        if (OPEN_WIN_FROM_WH_H.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.rtlWhNm);
            params[paraCnt++] = scrnMsg.xxPopPrm_P7;
        } else {
            scrnMsg.xxPopPrm_P7.clear();
            params[paraCnt++] = scrnMsg.xxPopPrm_P7;
        }
        if (OPEN_WIN_FROM_WH_D.equals(eventNm) || OPEN_WIN_FROM_SUB_WH_D.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(index).rtlSwhCd_A1);
            params[paraCnt++] = scrnMsg.xxPopPrm_P8;
        } else {
            scrnMsg.xxPopPrm_P8.clear();
            params[paraCnt++] = scrnMsg.xxPopPrm_P8;
        }
        scrnMsg.xxPopPrm_P9.clear();
        params[paraCnt++] = scrnMsg.xxPopPrm_P9;
        if (index > -1) {
            scrnMsg.xxPopPrm_PA.setValue(ZYPConstant.FLG_OFF_N);
            params[paraCnt++] = scrnMsg.xxPopPrm_PA;
        } else {
            scrnMsg.xxPopPrm_PA.setValue(ZYPConstant.FLG_ON_Y);
            params[paraCnt++] = scrnMsg.xxPopPrm_PA;
        }
        scrnMsg.xxPopPrm_PB.clear();
        params[paraCnt++] = scrnMsg.xxPopPrm_PB;
        scrnMsg.xxPopPrm_PC.setValue(GMD);
        params[paraCnt++] = scrnMsg.xxPopPrm_PC;
        params[paraCnt++] = scrnMsg.xxPopPrm_PD;
        params[paraCnt++] = scrnMsg.xxPopPrm_PE;
        params[paraCnt++] = scrnMsg.xxPopPrm_PF;
        params[paraCnt++] = scrnMsg.xxPopPrm_PG;
        scrnMsg.xxPopPrm_PH.setValue(ZYPConstant.FLG_ON_Y);
        params[paraCnt++] = scrnMsg.xxPopPrm_PH;

        scrnMsg.xxScrEventNm.setValue(eventNm);

        return params;
    }

    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_UPDATE);
    }

    /**
     * Function List
     * @return List<String> Function List
     */
    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList;
    }

    /**
     * <p>
     * Sets the name for the error message.
     * </p>
     * @param scrnMsg NMAL6880BMsg
     */
    public static void setNameForMessage(NMAL6880BMsg scrnMsg) {
        scrnMsg.vndShipToCustCd.setNameForMessage(ROSS_LOC);
        scrnMsg.rtlWhCd.setNameForMessage(CSA_WH);
        scrnMsg.rtlWhNm.setNameForMessage(CSA_WH_NM);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).vndShipToCustCd_A1.setNameForMessage(ROSS_LOC);
            scrnMsg.A.no(i).rtlWhCd_A1.setNameForMessage(CSA_WH);
            scrnMsg.A.no(i).rtlSwhCd_A1.setNameForMessage(SUB_WH);
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage(EFF_FROM);
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage(EFF_THRU);
        }
    }
}
