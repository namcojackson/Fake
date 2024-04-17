/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3150.common;

import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_ADD_NEW_ROW;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_LINE_CANCEL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_SELECT_ALL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.BTN_UN_SELECT_ALL;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.EDT_AUTH;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_0;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_1;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_10;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_11;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_2;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_3;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_4;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_5;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_6;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_7;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_8;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.IDX_9;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.LBL_ITEM;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.LBL_ITEM_DESC;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.LBL_SWH;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.LBL_WH;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.REF_AUTH;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.SCREEN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3150.NLBL3150BMsg;
import business.servlet.NLBL3150.NLBL3150Bean;
import business.servlet.NLBL3150.NLBL3150_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * </pre>
 */
public class NLBL3150CommonLogic {

    /**
     * Set Name for Message.
     * @param scrnMsg NLBL3150BMsg //
     */
    public static void setNameForMessage(NLBL3150BMsg scrnMsg) {

        scrnMsg.mdseCd.setNameForMessage(LBL_ITEM);
        scrnMsg.rtlWhCd.setNameForMessage(LBL_WH);
        scrnMsg.rtlSwhCd.setNameForMessage(LBL_SWH);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NLBL3150_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.mdseCd_A.setNameForMessage(LBL_ITEM);
            lineMsg.mdseDescShortTxt_A.setNameForMessage(LBL_ITEM_DESC);
            // 2017/08/14 QC#20556 MOD BEGIN
//            lineMsg.rtlWhNm_A.setNameForMessage(LBL_WH);
//            lineMsg.rtlSwhNm_A.setNameForMessage(LBL_SWH);
            lineMsg.rtlWhCd_A.setNameForMessage(LBL_WH);
            lineMsg.rtlSwhCd_A.setNameForMessage(LBL_SWH);
            // 2017/08/14 QC#20556 MOD END
        }
    }

    /**
     * Initial Common Button
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);
    }

    /**
     * Set Control button by Function ID
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3150BMsg
     */
    public static void setAuthority(EZDCommonHandler handler, NLBL3150BMsg scrnMsg) {
        handler.setButtonEnabled(BTN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_UN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_LINE_CANCEL, false);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            String funcId = scrnMsg.Z.no(i).xxFuncId_Z.getValue();

            if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(funcId)) {
                handler.setButtonEnabled(BTN_ADD_NEW_ROW, false);
                handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
            } else if (EDT_AUTH.equals(funcId)) {

                break;
            }
        }
    }

    /**
     * Change Table Back Ground Color
     * @param scrnMsg
     */
    public static void changeTableBackGroundColor(NLBL3150BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLBL3150Bean.A, scrnMsg.A);
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NLBL3150BMsg
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NLBL3150BMsg scrnMsg) {

        scrnMsg.Y.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_4).xxPopPrm, ZYPConstant.FLG_OFF_N);

        // 2017/08/14 QC#20556 MOD BEGIN
//        if ("OpenWin_SearcWh_Link".equals(scrnMsg.xxScrEventNm.getValue())) {
        if ("OpenWin_SearchWh_Link".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_6).xxPopPrm, scrnMsg.rtlWhCd);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, scrnMsg.rtlWhNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_8).xxPopPrm, scrnMsg.rtlSwhCd);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, scrnMsg.rtlSwhNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, "");
//        } else if ("OpenWin_SearchWh".equals(scrnMsg.xxScrEventNm.getValue())) {
        } else if ("OpenWin_SearchWh".equals(scrnMsg.xxScrEventNm.getValue())
                || "OpenWin_SearchSwh".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_6).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhCd_A);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhNm_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_7).xxPopPrm, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_8).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlSwhCd_A);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).rtlWhNm_A);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, "");
            // 2017/08/14 QC#20556 MOD END
        }

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.Y.no(IDX_0).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_1).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_2).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_3).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_4).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_5).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_6).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_7).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_8).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_9).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_10).xxPopPrm;

        return params;
    }

    /**
     * SetItem Popup param
     * @param scrnMsg NLBL3150BMsg
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForItemPopup(NLBL3150BMsg scrnMsg) {

        scrnMsg.Y.clear();

        if ("OpenWin_SearchItemNum_Link".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_0).xxPopPrm, scrnMsg.mdseCd);
        } else if ("OpenWin_SearchItemNum".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_0).xxPopPrm, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_A);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.Y.no(IDX_9).xxPopPrm, "AL");

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.Y.no(IDX_0).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_1).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_2).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_3).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_4).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_5).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_6).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_7).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_8).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_9).xxPopPrm;
        params[paramCount++] = scrnMsg.Y.no(IDX_10).xxPopPrm;

        return params;
    }

    /**
     * Set Control Protected Input Form
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3150BMsg
     */
    public static void setProtected(EZDCommonHandler handler, NLBL3150BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // 2017/08/10 QC#20556 ADD BEGIN
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
            // 2017/08/10 QC#20556 ADD END
            scrnMsg.A.no(i).rtlWhNm_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A.setInputProtected(true);
        }
    }

}
