/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0040.common;

import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_APL;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_APR;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_CLR;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_DEL;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_DWL;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_RJT;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_RST;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_RTN;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_SAV;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.BTN_CMN_SUB;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.NWWM0005E;
import static business.servlet.NWWL0040.constant.NWWL0040Constant.SCRN_ID_00;
import business.servlet.NWWL0040.NWWL0040BMsg;
import business.servlet.NWWL0040.NWWL0040_ABMsg;
import business.servlet.NWWL0040.NWWL0040_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWWL0040CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0040CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * controllScreen
     * @param scrnMsg NWWL0040BMsg
     */
    public static void controllScreen(NWWL0040BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWWL0040_ABMsg scrnAMsg = scrnMsg.A.no(i);
            scrnAMsg.ntfyDistListNm_A.setInputProtected(true);
            scrnAMsg.ntfyDistListDescTxt_A.setInputProtected(true);
            scrnAMsg.ntfyBizAreaTpDescTxt_A.setInputProtected(true);
            scrnAMsg.ntfySubAreaTpDescTxt_A.setInputProtected(true);
        }
    }

    /**
     * checkHdr
     * @param scrnMsg NWWL0040BMsg
     */
    public static void checkHdr(NWWL0040BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.ntfyDistListNm);
        scrnMsg.addCheckItem(scrnMsg.ntfyDistListDescTxt);
        scrnMsg.addCheckItem(scrnMsg.ntfyBizAreaTpCd_D);
        scrnMsg.addCheckItem(scrnMsg.ntfySubAreaTpCd_D);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_D);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_D);
        scrnMsg.addCheckItem(scrnMsg.ntfyDistListActvFlg);
        scrnMsg.addCheckItem(scrnMsg.ntfyHdrDescTxt);
        scrnMsg.addCheckItem(scrnMsg.ntfyBizAreaTpCd_N);
        scrnMsg.addCheckItem(scrnMsg.ntfySubAreaTpCd_N);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_N);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_N);
        scrnMsg.addCheckItem(scrnMsg.ntfyHdrActvFlg);

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_D) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_D)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt_D.getValue(), scrnMsg.effFromDt_D.getValue()) < 0) {
                scrnMsg.effFromDt_D.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt_D.getNameForMessage(), scrnMsg.effFromDt_D.getNameForMessage() });
                scrnMsg.effThruDt_D.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt_D.getNameForMessage(), scrnMsg.effFromDt_D.getNameForMessage() });
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_N) && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_N)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt_N.getValue(), scrnMsg.effFromDt_N.getValue()) < 0) {
                scrnMsg.effFromDt_N.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt_N.getNameForMessage(), scrnMsg.effFromDt_N.getNameForMessage() });
                scrnMsg.effThruDt_N.setErrorInfo(1, NWWM0005E, // 
                        new String[] {scrnMsg.effThruDt_N.getNameForMessage(), scrnMsg.effFromDt_N.getNameForMessage() });
            }
        }
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0040BMsg
     * @param scrnAMsgAry NWWL0040_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWWL0040BMsg scrnMsg, NWWL0040_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWWL0040BMsg
     * @param scrnAMsgAry NWWL0040_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWWL0040BMsg scrnMsg, NWWL0040_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWWL0040BMsg
     * @param scrnAMsgAry NWWL0040_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWWL0040BMsg scrnMsg, NWWL0040_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }
}
