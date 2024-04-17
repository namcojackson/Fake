/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2340.common;

import static business.servlet.NWAL2340.constant.NWAL2340Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.BTN_CMN_CLS;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.COMMA;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.EVENT_OPEN_WIN_SHIP_TO_H;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.NMAL6760_DISPLAY_RELATED_ACCOUNTS;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.NMAL6760_SEARCH_MODE;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.NMAL6760_STATUS_CD_ACTIVE;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.SCRN_ID;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.SPACE;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2340.NWAL2340BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * SOM Address Mass Apply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/07   Fujitsu         R.Nakamura      Update          QC#20974
 * 2017/09/20   Fujitsu         R.Nakamura      Update          QC#21114
 *</pre>
 */
public class NWAL2340CommonLogic {

    /**
     * cntrlScrnItemDispInit
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2340BMsg
     */
    public static void cntrlScrnItemDispInit(EZDCommonHandler handler, NWAL2340BMsg scrnMsg) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).addrLbTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustAcctNm_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToFirstLineAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToScdLineAddr_A.setInputProtected(true);
            // Mod Start 2017/09/07 QC#20974
            scrnMsg.A.no(i).shipToThirdLineAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToFrthLineAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToCtyAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToStCd_A.setInputProtected(true);
            scrnMsg.A.no(i).shipToPostCd_A.setInputProtected(true);
//            scrnMsg.A.no(i).xxLocAddrNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A.setInputProtected(true);
            // Mod End 2017/09/07 QC#20974
        }
    }

    /**
     * Get Param NMAL6760 For Ship To
     * @param scrnMsg NWAL1500BMsg
     * @return Param NMAL6760 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL2340BMsg scrnMsg) {

        scrnMsg.P.clear();

        // Mod Start 2017/09/07 QC#20974
        //Object[] params = new Object[18];
        Object[] params = new Object[35];
        // Mod End 2017/09/07 QC#20974
        if (EVENT_OPEN_WIN_SHIP_TO_H.equals(scrnMsg.xxScrEventNm_H.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.shipToCustAcctCd_H);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(16).xxPopPrm_P, scrnMsg.shipToCustCd_H);

        } else {
            int idx = scrnMsg.xxCellIdx_H.getValueInt();
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.A.no(idx).shipToCustAcctCd_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.A.no(idx).shipToCustAcctNm_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(16).xxPopPrm_P, scrnMsg.A.no(idx).shipToCustCd_A);
        }
        params[0] = scrnMsg.P.no(0).xxPopPrm_P;
        params[1] = scrnMsg.P.no(1).xxPopPrm_P;
        params[2] = scrnMsg.P.no(2).xxPopPrm_P;
        params[3] = scrnMsg.P.no(3).xxPopPrm_P;
        params[4] = scrnMsg.P.no(4).xxPopPrm_P;
        params[5] = scrnMsg.P.no(5).xxPopPrm_P;
        params[6] = scrnMsg.P.no(6).xxPopPrm_P;
        params[7] = scrnMsg.P.no(7).xxPopPrm_P;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm_P, NMAL6760_DISPLAY_RELATED_ACCOUNTS);
        params[8] = scrnMsg.P.no(8).xxPopPrm_P;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm_P, NMAL6760_SEARCH_MODE);
        params[9] = scrnMsg.P.no(9).xxPopPrm_P;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm_P, ZYPConstant.FLG_OFF_N);
        params[10] = scrnMsg.P.no(10).xxPopPrm_P;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm_P, NMAL6760_STATUS_CD_ACTIVE);
        params[11] = scrnMsg.P.no(11).xxPopPrm_P;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm_P, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
        params[12] = scrnMsg.P.no(12).xxPopPrm_P;
        params[13] = scrnMsg.P.no(13).xxPopPrm_P;
        params[14] = scrnMsg.P.no(14).xxPopPrm_P;
        params[15] = scrnMsg.P.no(15).xxPopPrm_P;
        params[16] = scrnMsg.P.no(16).xxPopPrm_P;
        params[17] = scrnMsg.P.no(17).xxPopPrm_P;
        // Add Start 2017/09/07 QC#20974
        params[18] = scrnMsg.P.no(18).xxPopPrm_P;
        params[19] = scrnMsg.P.no(19).xxPopPrm_P;
        params[20] = scrnMsg.P.no(20).xxPopPrm_P;
        params[21] = scrnMsg.P.no(21).xxPopPrm_P;
        params[22] = scrnMsg.P.no(22).xxPopPrm_P;
        params[23] = scrnMsg.P.no(23).xxPopPrm_P;
        params[24] = scrnMsg.P.no(24).xxPopPrm_P;
        params[25] = scrnMsg.P.no(25).xxPopPrm_P;
        params[26] = scrnMsg.P.no(26).xxPopPrm_P;
        params[27] = scrnMsg.P.no(27).xxPopPrm_P;
        params[28] = scrnMsg.P.no(28).xxPopPrm_P;
        params[29] = scrnMsg.P.no(29).xxPopPrm_P;
        params[30] = scrnMsg.P.no(30).xxPopPrm_P;
        params[31] = scrnMsg.P.no(31).xxPopPrm_P;
        params[32] = scrnMsg.P.no(32).xxPopPrm_P;
        params[33] = scrnMsg.P.no(33).xxPopPrm_P;
        params[34] = scrnMsg.P.no(34).xxPopPrm_P;
        // Add End 2017/09/07 QC#20974

        return params;
    }

    // Add Start 2017/09/20 QC#21114
    /**
     * addCheckItem
     * @param scrnMsg NWAL2340BMsg
     */
    public static void addCheckItemHdr(NWAL2340BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H);

        scrnMsg.putErrorScreen();
    }
    // Add End 2017/09/20 QC#21114

    /**
     * addCheckItem
     * @param scrnMsg NWAL2340BMsg
     */
    public static void addCheckItem(NWAL2340BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToCustAcctCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToCustCd_A);
        }

        scrnMsg.putErrorScreen();
    }

    // Add Start 2017/09/07 QC#20974
    /**
     * Get Param NMAL6760 For Ship To
     * @param scrnMsg NWAL2340BMsg
     * @param idx int
     * @return String All Line Address
     */
    public static String setAllLineAddressInfo(NWAL2340BMsg scrnMsg, int idx) {

        StringBuilder addr = new StringBuilder();

        if (idx < 0) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.shipToFirstLineAddr_H)) {
                return null;
            }

            addr.append(scrnMsg.shipToFirstLineAddr_H.getValue());
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToScdLineAddr_H)) {
                addr.append(SPACE);
                addr.append(scrnMsg.shipToScdLineAddr_H.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToThirdLineAddr_H)) {
                addr.append(SPACE);
                addr.append(scrnMsg.shipToThirdLineAddr_H.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToFrthLineAddr_H)) {
                addr.append(SPACE);
                addr.append(scrnMsg.shipToFrthLineAddr_H.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToCtyAddr_H)) {
                addr.append(SPACE);
                addr.append(scrnMsg.shipToCtyAddr_H.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToStCd_H)) {
                addr.append(COMMA);
                addr.append(scrnMsg.shipToStCd_H.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToPostCd_H)) {
                addr.append(SPACE);
                addr.append(scrnMsg.shipToPostCd_H.getValue());
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).shipToFirstLineAddr_A)) {
                return null;
            }

            addr.append(scrnMsg.A.no(idx).shipToFirstLineAddr_A.getValue());
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).shipToScdLineAddr_A)) {
                addr.append(SPACE);
                addr.append(scrnMsg.A.no(idx).shipToScdLineAddr_A.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).shipToThirdLineAddr_A)) {
                addr.append(SPACE);
                addr.append(scrnMsg.A.no(idx).shipToThirdLineAddr_A.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).shipToFrthLineAddr_A)) {
                addr.append(SPACE);
                addr.append(scrnMsg.A.no(idx).shipToFrthLineAddr_A.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).shipToCtyAddr_A)) {
                addr.append(SPACE);
                addr.append(scrnMsg.A.no(idx).shipToCtyAddr_A.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).shipToStCd_A)) {
                addr.append(COMMA);
                addr.append(scrnMsg.A.no(idx).shipToStCd_A.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).shipToPostCd_A)) {
                addr.append(SPACE);
                addr.append(scrnMsg.A.no(idx).shipToPostCd_A.getValue());
            }
        }

        return addr.toString();
    }
    // Add End 2017/09/07 QC#20974
}
