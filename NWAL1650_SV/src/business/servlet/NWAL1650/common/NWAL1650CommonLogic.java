package business.servlet.NWAL1650.common;

import static business.servlet.NWAL1650.constant.NWAL1650Constant.BTN_CMN_CLEAR;
import static business.servlet.NWAL1650.constant.NWAL1650Constant.BTN_CMN_CLOSE;
import static business.servlet.NWAL1650.constant.NWAL1650Constant.SCRN_ID;
import static business.servlet.NWAL1650.constant.NWAL1650Constant.ZZM9000E;
import static business.servlet.NWAL1650.constant.NWAL1650Constant.MODE_REFERENCE;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1650.NWAL1650BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 * <pre>
 * NWAL1650:Control Fields PopUp
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         M.Yamada        Update          N/A
 * 2015/12/03   Fujitsu         Y.Kanefusa      Update          #1309
 * 2015/02/23   Fujitsu         M.suzuki        Update          #2140
 *</pre>
 */
public class NWAL1650CommonLogic {

    // MOD START 2016/02/03 #2140
    /**
     * <pre>
     * @param handler   EZDCommonHandler
     * </pre>
     */
    public static void initCommonButton(EZDCommonHandler handler, NWAL1650BMsg scrnMsg) {
        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            // 2016/03/03 S21_NA#2140 Mod Start ---------
            handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
            // 2016/03/03 S21_NA#2140 Mod End ----------
        } else {
            // MOD START 2015/12/03 #1309
            //handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            // MOD END 2015/12/03 #1309
            handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
        }
    }
     // MOD End 2016/02/03 #2140
     
    /**
     * <pre>
     * @param scrnMsg   NWAL1650BMsg
     * </pre>
     */
    public static void protectedInput(NWAL1650BMsg scrnMsg) {
        // MOD START 2016/02/23 #2140
        // MOD START 2015/12/03 #1309
        if (MODE_REFERENCE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.bllgAttrbValTxt_1.setInputProtected(true);
            scrnMsg.bllgAttrbValTxt_2.setInputProtected(true);
            scrnMsg.bllgAttrbValTxt_3.setInputProtected(true);
            scrnMsg.bllgAttrbValTxt_4.setInputProtected(true);
            scrnMsg.bllgAttrbValTxt_5.setInputProtected(true);
            scrnMsg.bllgAttrbValTxt_6.setInputProtected(true); 
        } else {
            scrnMsg.bllgAttrbValTxt_1.setInputProtected(false);
            scrnMsg.bllgAttrbValTxt_2.setInputProtected(false);
            scrnMsg.bllgAttrbValTxt_3.setInputProtected(false);
            scrnMsg.bllgAttrbValTxt_4.setInputProtected(false);
            scrnMsg.bllgAttrbValTxt_5.setInputProtected(false);
            scrnMsg.bllgAttrbValTxt_6.setInputProtected(false);
        }
        // MOD END 2015/12/03 #1309
        // MOD END 2016/02/23 #2140
    }
    

    /**
     * <pre>
     * @param scrnMsg   NWAL1650BMsg
     * @param params    Object[]
     * </pre>
     */
    public static void getInputParam(NWAL1650BMsg scrnMsg, Object[] params) {
        int ixPrm = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_P, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_P, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum_P, (EZDBBigDecimalItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineSubNum_P, (EZDBBigDecimalItem) params[ixPrm++]);

        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_P, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_P, (EZDBStringItem) params[ixPrm++]);

        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbNm_P1, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbValTxt_P1, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbNm_P2, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbValTxt_P2, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbNm_P3, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbValTxt_P3, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbNm_P4, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbValTxt_P4, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbNm_P5, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbValTxt_P5, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbNm_P6, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.bllgAttrbValTxt_P6, (EZDBStringItem) params[ixPrm++]);
        // 2016/02/22 S21_NA#2140 Add Start ------------
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[ixPrm++]);
        // 2016/02/22 S21_NA#2140 Add End --------------

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_P)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Line#(config)" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsCpoLineNum_P)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"Line#" });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_P)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {"shipToCustCd_P" });
            return;
        }
    }

    /**
     * <pre>
     * set column width
     * @param scrnMsg NWAL1650BMsg
     * </pre>
     */
    public static void setColumnWidth(NWAL1650BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbNm_1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbValTxt_1)) {
            setHideColumn(scrnMsg, "bllgAttrb_1");
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbNm_2) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbValTxt_2)) {
            setHideColumn(scrnMsg, "bllgAttrb_2");
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbNm_3) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbValTxt_3)) {
            setHideColumn(scrnMsg, "bllgAttrb_3");
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbNm_4) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbValTxt_4)) {
            setHideColumn(scrnMsg, "bllgAttrb_4");
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbNm_5) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbValTxt_5)) {
            setHideColumn(scrnMsg, "bllgAttrb_5");
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbNm_6) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgAttrbValTxt_6)) {
            setHideColumn(scrnMsg, "bllgAttrb_6");
        }
    }

    /**
     * <pre>
     * @param scrnMsg   scrnMsg
     * @param id        table column's id of html.
     * </pre>
     */
    private static void setHideColumn(NWAL1650BMsg scrnMsg, String id) {
        EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCRN_ID, id);
        guiAttr.setStyleAttribute("display", "none");
        scrnMsg.addGUIAttribute(guiAttr);
    }


}
