/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3180.common;

import parts.common.EZDBMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3180.NLBL3180BMsg;
import business.servlet.NLBL3180.constant.NLBL3180Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Ship Detail Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/06/18   CITS            J.Evangelista   Create          QC#58876
 * </pre>
 */
public final class NLBL3180CommonLogic {

    /**
     * setTableBGColor
     * @param bMsg EZDBMsg
     */
    public static void setTableBGColor(EZDBMsg bMsg) {

        NLBL3180BMsg scrnMsg = (NLBL3180BMsg) bMsg;

        S21TableColorController lineTblColors = new S21TableColorController(NLBL3180Constant.SCRN_ID, scrnMsg);
        lineTblColors.setAlternateRowsBG(NLBL3180Constant.TABLE_ID_A, scrnMsg.A);
    }

    /**
     * initDisplayInfo
     * @param handler EZDCommonHandler
     * @param bMsg EZDBMsg
     */
    public static void initDisplayInfo(EZDCommonHandler handler, EZDBMsg bMsg) {

        NLBL3180BMsg scrnMsg = (NLBL3180BMsg) bMsg;

        handler.setButtonProperties(NLBL3180Constant.BTN_CMN_CLEAR[0], NLBL3180Constant.BTN_CMN_CLEAR[1], NLBL3180Constant.BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(NLBL3180Constant.BTN_CMN_CLOSE[0], NLBL3180Constant.BTN_CMN_CLOSE[1], NLBL3180Constant.BTN_CMN_CLOSE[2], 1, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem30Txt_DA, NLBL3180Constant.UNIT_ID_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem50Txt_DA, NLBL3180Constant.FIELD_NAME_ORIGINAL_ETD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem30Txt_DB, NLBL3180Constant.UNIT_ID_B);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem50Txt_DB, NLBL3180Constant.FIELD_NAME_QTD_CHANGE);

        setScreenItemCondition(handler, bMsg);
    }

    /**
     * setScreenItemCondition
     * @param handler EZDCommonHandler
     * @param bMsg EZDBMsg
     */
    public static void setScreenItemCondition(EZDCommonHandler handler, EZDBMsg bMsg) {

        NLBL3180BMsg scrnMsg = (NLBL3180BMsg) bMsg;

        scrnMsg.soNum_H1.setInputProtected(true);
        scrnMsg.bolVchNum_H1.setInputProtected(true);
        scrnMsg.xxScrItem30Txt_DA.setInputProtected(true);
        scrnMsg.xxScrItem50Txt_DA.setInputProtected(true);
        scrnMsg.rqstQuoteDelyTxt_DA.setInputProtected(true);
        scrnMsg.xxScrItem30Txt_DB.setInputProtected(true);
        scrnMsg.xxScrItem50Txt_DB.setInputProtected(true);
        scrnMsg.quoteDelyTxt_DB.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).unitId_A1.setInputProtected(true);
            scrnMsg.A.no(i).descHistTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem30Txt_A1.setInputProtected(true);
        }
    }
}
