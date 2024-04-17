/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1340.common;

import static business.servlet.NSAL1340.constant.NSAL1340Constant.BLLG_MTR_LB_CD;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.BTN_CMN_CLR;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.BTN_CMN_CLS;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.MDL_ID;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.MDL_NM;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.PRC_BASE_DT;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.PRC_CATG_CD;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.PRC_MTR_PKG_PK;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.PRC_SVC_CONTR_TP_CD;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.PRC_SVC_PLN_TP_CD;
import static business.servlet.NSAL1340.constant.NSAL1340Constant.SCRN_ID_00;
import business.servlet.NSAL1340.NSAL1340BMsg;
import business.servlet.NSAL1340.NSAL1340_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NSAL1340CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1340CommonLogic {

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
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NSAL1340BMsg
     */
    public static void protectCmnBtnProp(S21CommonHandler handler, NSAL1340BMsg scrnMsg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
    }

    /**
     * Add Check Items.
     * @param scrnMsg NSAL1340BMsg
     */
    public static void addCheckItem(NSAL1340BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm);
        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
        scrnMsg.addCheckItem(scrnMsg.mtrLbNm);

        scrnMsg.putErrorScreen();
    }

    /**
     * Check input Parameter.
     * @param scrnMsg NSAL1340BMsg
     * @return String
     */
    public static String checkInputParam(NSAL1340BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgCd)) {
            return PRC_CATG_CD;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdlId)) {
            return MDL_ID;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdlNm)) {
            return MDL_NM;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgPk)) {
            return PRC_MTR_PKG_PK;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcSvcPlnTpCd)) {
            return PRC_SVC_PLN_TP_CD;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcSvcContrTpCd)) {
            return PRC_SVC_CONTR_TP_CD;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.bllgMtrLbCd)) {
            return BLLG_MTR_LB_CD;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcBaseDt)) {
            return PRC_BASE_DT;
        }
        return null;
    }

    /**
     * Set input protected.
     * @param scrnMsg NSAL1340BMsg
     */
    public static void setInputProtect(NSAL1340BMsg scrnMsg) {
        scrnMsg.prcCatgNm.setInputProtected(true);
        scrnMsg.mdlNm.setInputProtected(true);
        scrnMsg.prcMtrPkgNm.setInputProtected(true);
        scrnMsg.mtrLbNm.setInputProtected(true);
        scrnMsg.A.setInputProtected(true);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NSAL1340BMsg
     * @param scrnAMsgAry NSAL1340_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NSAL1340BMsg scrnMsg, NSAL1340_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, 1);
    }

    /**
     * <pre>
     * 20160308 add
     * Set the radio button to the first line of band code which same as parameter.
     * @param scrnMsg   NSAL1340BMsg
     * </pre>
     */
    public static void setRadioButton(NSAL1340BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcListBandDescTxt_I)) {
            return;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.prcListBandDescTxt_I.getValue().equals(scrnMsg.A.no(i).prcListBandDescTxt.getValue())) {
                scrnMsg.xxRadioBtn.setValue(i);
                return;
            }
        }
    }

}
