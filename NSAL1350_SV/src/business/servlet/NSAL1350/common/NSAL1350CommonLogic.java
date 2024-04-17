/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1350.common;

import static business.servlet.NSAL1350.constant.NSAL1350Constant.BTN_CMN_CLR;
import static business.servlet.NSAL1350.constant.NSAL1350Constant.BTN_CMN_CLS;
import static business.servlet.NSAL1350.constant.NSAL1350Constant.SCRN_ID_00;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import business.servlet.NSAL1350.NSAL1350BMsg;
import business.servlet.NSAL1350.NSAL1350_ABMsg;
import business.servlet.NSAL1350.NSAL1350_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NSAL1350CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSAL1350CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

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
     * @param scrnMsg NSAL1350BMsg
     * @param scrnAMsgAry NSAL1350_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NSAL1350BMsg scrnMsg, NSAL1350_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NSAL1350BMsg
     * @param scrnAMsgAry NSAL1350_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NSAL1350BMsg scrnMsg, NSAL1350_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NSAL1350BMsg
     * @param scrnAMsgAry NSAL1350_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NSAL1350BMsg scrnMsg, NSAL1350_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1350BMsg
     * @param params    Object[]
     * </pre>
     */
    public static void getInputParam(NSAL1350BMsg scrnMsg, Object[] params) {
        int ixPrm = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_I, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shellLineNum_I, (EZDBBigDecimalItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.addAsryFlg_I, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum_I, (EZDBStringItem) params[ixPrm++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSfxKeyTxt_I, (EZDBStringItem) params[ixPrm++]);

        EZDMsgArray detailsI = (EZDMsgArray) params[ixPrm++];
        EZDMsg.copy(detailsI, scrnMsg.xxSfxKeyTxt_I.getValue(), scrnMsg.I, "I");

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSfxKeyTxt_O, (EZDBStringItem) params[ixPrm++]);

        EZDMsgArray detailsO = (EZDMsgArray) params[ixPrm++];
        EZDMsg.copy(detailsO, scrnMsg.xxSfxKeyTxt_O.getValue(), scrnMsg.O, "O");

        if (ZYPCommonFunc.hasValue(scrnMsg.addAsryFlg_I)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.addAsryFlg, scrnMsg.addAsryFlg_I);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * <pre>
     * @param handler   S21CommonHandler
     * @param scrnMsg   NSAL1350BMsg
     * </pre>
     */
    public static void initBizItemProp(S21CommonHandler handler, NSAL1350BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.addAsryFlg_I) 
                && scrnMsg.addAsryFlg_I.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            scrnMsg.mdseItemTpCd.setInputProtected(true);
            scrnMsg.mdlId.setInputProtected(true);
            scrnMsg.dsOrdPosnNum.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1350_ABMsg aScrnMsg = scrnMsg.A.no(i);
            aScrnMsg.dsOrdPosnNum_A.setInputProtected(true);
            aScrnMsg.t_MdlNm_A.setInputProtected(true);
            aScrnMsg.dplyLineNum_A.setInputProtected(true);
            aScrnMsg.mdseCd_A.setInputProtected(true);
            aScrnMsg.mdseDescShortTxt_A.setInputProtected(true);
            aScrnMsg.mdseItemTpNm_A.setInputProtected(true);
            aScrnMsg.xxGenlFldAreaTxt_A.setInputProtected(true);
        }
    }

    /**
     * <pre>
     * @param arg       Object[]
     * @param scrnMsg   NSAL1350BMsg
     * </pre>
     */
    public static void setOutputPrm(Object[] arg, NSAL1350BMsg scrnMsg) {
        int ix = 0;
        arg[ix++] = scrnMsg.cpoOrdNum_I;
        arg[ix++] = scrnMsg.shellLineNum_I;
        arg[ix++] = scrnMsg.addAsryFlg_I;
        arg[ix++] = scrnMsg.dsContrNum_I;
        arg[ix++] = scrnMsg.xxSfxKeyTxt_I;
        arg[ix++] = scrnMsg.I;
        arg[ix++] = scrnMsg.xxSfxKeyTxt_O;
        for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(i).addAsryFlg_O, scrnMsg.addAsryFlg);
        }
        EZDMsgArray outMsgAry = (EZDMsgArray) arg[ix];
        EZDMsg.copy(scrnMsg.O, "O", outMsgAry, "Q");
    }
}
