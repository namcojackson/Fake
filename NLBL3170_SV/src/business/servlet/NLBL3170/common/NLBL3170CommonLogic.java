/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */

package business.servlet.NLBL3170.common;

import parts.common.EZDBMsg;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3170.NLBL3170BMsg;
import business.servlet.NLBL3170.constant.NLBL3170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 * 2020/02/18   Fujitsu         R.Nakamura      Update          QC#55897
 * 2020/10/02   CITS            R.Mercado       Update          QC#57754
 *</pre>
 */

public class NLBL3170CommonLogic {

    public static void setOutputParam(Object[] arg, EZDBMsg bMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;

        int t = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).trxLineNum_A1) //
                    && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).proSendFlg_A1) //
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).proNum_A1)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(t).mstrProNum_T1, scrnMsg.A.no(i).mstrProNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.T.no(t).proNum_T1, scrnMsg.A.no(i).proNum_A1.getValue());
                t++;
                scrnMsg.T.setValidCount(t);
            }
        }

        Object[] params = (Object[]) arg;

        for (int i = 0; i < params.length; i++) {

            if (i == 0) {

                // [0] : TRX Header Number
                // Mod Start 2020/02/18 QC#55897
//                params[0] =  scrnMsg.trxHdrNum.getValue();
                params[0] =  scrnMsg.xxDplyOrdNum_TH.getValue();
                // Mod End 2020/02/18 QC#55897

            } else if (i == 1) {

                // [1] : Edit Flag
                params[1] =  scrnMsg.xxValUpdFlg.getValue();

            } else if (i == 2) {

                // [2] : Tracking Number
                EZDMsg.copy(scrnMsg.T, "T1", (EZDMsgArray) arg[2], "");

            }

        }

    }

    public static void setTableBGColor(EZDBMsg bMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;

        S21TableColorController lineTblColorS = new S21TableColorController(NLBL3170Constant.SCRN_ID, scrnMsg);
        lineTblColorS.setAlternateRowsBG(NLBL3170Constant.TABLE_ID_A, scrnMsg.A);

    }

    public static void initDisplayInfo(EZDCommonHandler handler, EZDBMsg bMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;

        // Initial Common Button
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxValUpdFlg.getValue())) {
            handler.setButtonProperties(NLBL3170Constant.BTN_CMN_CLEAR[0], NLBL3170Constant.BTN_CMN_CLEAR[1], NLBL3170Constant.BTN_CMN_CLEAR[2], 1, null);
        } else {
            handler.setButtonProperties(NLBL3170Constant.BTN_CMN_CLEAR[0], NLBL3170Constant.BTN_CMN_CLEAR[1], NLBL3170Constant.BTN_CMN_CLEAR[2], 0, null);
        }
        handler.setButtonProperties(NLBL3170Constant.BTN_CMN_RESET[0], NLBL3170Constant.BTN_CMN_RESET[1], NLBL3170Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NLBL3170Constant.BTN_CMN_CLOSE[0], NLBL3170Constant.BTN_CMN_CLOSE[1], NLBL3170Constant.BTN_CMN_CLOSE[2], 1, null);

        // Initial Other display Info
        setScreenItemCondition(handler, scrnMsg);
    }

    private static void setScreenItemCondition(EZDCommonHandler handler, EZDBMsg bMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;

        // Mod Start 2020/02/18 QC#55897
//        scrnMsg.trxHdrNum.setInputProtected(true);
        scrnMsg.xxDplyOrdNum_TH.setInputProtected(true);
        scrnMsg.wmsCarrCd.setInputProtected(true);
        scrnMsg.xxMsgTxt.setInputProtected(true);
//        scrnMsg.soNum.setInputProtected(true);
        scrnMsg.xxDplyOrdNum_SN.setInputProtected(true);
        // Mod End 2020/02/18 QC#55897

        if (scrnMsg.A.length() == 0) {
            handler.setButtonEnabled(NLBL3170Constant.BTN_ASSIGN, true);
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxValUpdFlg.getValue())) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.A.no(i).trxLineNum_A1.setInputProtected(true);

                // Tracking number from WMS does not edit.
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).proSendFlg_A1.getValue())) {
                    scrnMsg.A.no(i).mstrProNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).proNum_A1.setInputProtected(true);
                } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).trxLineNum_A1)) {
                    scrnMsg.A.no(i).mstrProNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).proNum_A1.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).mstrProNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).proNum_A1.setInputProtected(false);
                }
            }

        } else {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).trxLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).mstrProNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).proNum_A1.setInputProtected(true);

                // Mod Start 2020/10/02 QC#57754
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mstrProNum_A1)) {
                    EZDGUIAttribute link1 = new EZDGUIAttribute("NLBL3170Scrn00", "carrTrk1" + i);
                    link1.setVisibility(false);
                    scrnMsg.addGUIAttribute(link1);
                }

                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).proNum_A1)) {
                    EZDGUIAttribute link2 = new EZDGUIAttribute("NLBL3170Scrn00", "carrTrk2" + i);
                    link2.setVisibility(false);
                    scrnMsg.addGUIAttribute(link2);
                }
                // Mod End 2020/10/02 QC#57754
            }

            handler.setButtonEnabled(NLBL3170Constant.BTN_ASSIGN, false);
        }
    }
}
