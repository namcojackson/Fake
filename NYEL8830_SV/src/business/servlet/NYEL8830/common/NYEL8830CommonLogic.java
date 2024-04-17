/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8830.common;

import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_APL;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_APR;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_CLR;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_DEL;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_DWL;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_RJT;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_RST;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_RTN;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_SAV;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.BTN_CMN_SUB;
import static business.servlet.NYEL8830.constant.NYEL8830Constant.SCRN_ID_00;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.VIEW_BTN_DETAIL;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.VIEW_BTN_DETAIL_HISTORY;

import parts.common.EZDGUIAttribute;
import business.servlet.NYEL8830.NYEL8830BMsg;
import business.servlet.NYEL8830.NYEL8830_ABMsgArray;
import business.servlet.NYEL8830.NYEL8830_BBMsgArray;
import business.servlet.NYEL8830.constant.NYEL8830Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess.STATUS;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenSerializer;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NYEL8830CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NYEL8830CommonLogic {

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
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * @param scrnMsg NYEL8830BMsg
     */
    public static void initRowCtrlProp(NYEL8830BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(NYEL8830Constant.SCRN_ID_00);

        inputProtect(scrnMsg);

        // BizLink,Detail Button
        cmnLinkProp(scrnMsg);
    }

    /**
     * Common Link properties.
     * @param scrnMsg NYEL8830BMsg
     */
    private static void cmnLinkProp(NYEL8830BMsg scrnMsg) {
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {

            //scrnMsg.A.no(idx).wfWrkItemNm_AL.clear();

            if (S21NwfWorkItem.STATUS.RUN.getCode().equals(scrnMsg.A.no(idx).wfWrkItemStsCd_A.getValue())) {
                /*
                if (S21StringUtil.isNotEmpty(scrnMsg.A.no(idx).bizAppId_A.getValue())){
                    scrnMsg.A.no(idx).wfWrkItemNm_AL.setValue(ZYPConstant.FLG_ON_Y);
                }
                */

                if (ZYPConstant.FLG_ON_1.equals(scrnMsg.A.no(idx).xxWfAprChkVisFlg_A.getValue())) {
                    continue;
                }
            } else if ((S21NwfWorkItem.STATUS.CLOSE.getCode().equals(scrnMsg.A.no(idx).wfWrkItemStsCd_A.getValue())) || (S21NwfWorkItem.STATUS.CANCEL.getCode().equals(scrnMsg.A.no(idx).wfWrkItemStsCd_A.getValue()))){
                // nothing
                continue;
            }

            setDisabled(scrnMsg, VIEW_BTN_DETAIL + "#" + Integer.toString(idx));
        }
    }

    /**
     * Input Protect.
     * @param scrnMsg NYEL8830BMsg
     */
    private static void inputProtect(NYEL8830BMsg scrnMsg) {

        scrnMsg.wfProcNm.setInputProtected(true);
        scrnMsg.xxWfProcStsNm.setInputProtected(true);
        scrnMsg.xxDtTm_PE.setInputProtected(true);
        scrnMsg.wfDescTxt_PR.setInputProtected(true);
        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            scrnMsg.B.no(idx).xxRowNum_B.setInputProtected(true);

            if (ZYPConstant.FLG_OFF_0.equals(scrnMsg.B.no(idx).xxWfAprChkVisFlg_B.getValue())) {
                setDisabled(scrnMsg, VIEW_BTN_DETAIL_HISTORY + "#" + Integer.toString(idx));

            }

            scrnMsg.B.no(idx).wfWrkItemId_B.setInputProtected(true);
            scrnMsg.B.no(idx).wfWrkItemNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).actWfCondNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).xxWfActOpNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).wfCmntTxt_B.setInputProtected(true);
            scrnMsg.B.no(idx).xxWfAsgFromNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).xxWfAsgToNm_B.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B1.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B2.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B3.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B4.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbTxt_B5.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B1.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B2.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B3.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B4.setInputProtected(true);
            scrnMsg.B.no(idx).wfBizAttrbLbTxt_B5.setInputProtected(true);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8830BMsg
     * @param scrnAMsgAry NYEL8830_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClearForSts(NYEL8830BMsg scrnMsg, NYEL8830_ABMsgArray scrnAMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NYEL8830BMsg
     * @param scrnBMsgAry NYEL8830_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClearForHist(NYEL8830BMsg scrnMsg, NYEL8830_BBMsgArray scrnBMsgAry, String tblId) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnBMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnBMsgAry, 1);
    }

    private static void setDisabled(NYEL8830BMsg scrnMsg, String id) {
        EZDGUIAttribute ctrl = new EZDGUIAttribute(NYEL8830Constant.SCRN_ID_00, id);
        ctrl.setAttribute("disabled", "disabled");
        scrnMsg.addGUIAttribute(ctrl);
    }

    /**
     * Get move window parameter
     * @param handler
     * @param scrnMsg
     * @return move window parameter
     */
    public static Object[] getMoveWinParam(S21CommonHandler handler, NYEL8830BMsg scrnMsg){

        // Argument
        int selectedIndex = handler.getButtonSelectNumber();
        S21NwfTokenSerializer ser = new S21NwfTokenSerializer();
        S21NwfTokenObj tokenBiz = null;
        try {
            String wfProcStsCd = scrnMsg.wfProcStsCd.getValue();
            if (S21NwfProcess.STATUS.CLOSE.getCode().equals(wfProcStsCd) ||
                S21NwfProcess.STATUS.CANCEL.getCode().equals(wfProcStsCd) ||
                S21NwfProcess.STATUS.ABORT.getCode().equals(wfProcStsCd)) {
                tokenBiz = ser.findCpltByKey(scrnMsg.A.no(selectedIndex).wfTokenBizPk_A.getValue());
            }
            else {
                tokenBiz = ser.findByKey(scrnMsg.A.no(selectedIndex).wfTokenBizPk_A.getValue());
            }
        } catch (S21NwfSystemException e) {
            e.printStackTrace();
        }

        Object[] params = null;

        if (tokenBiz != null) {
            params = tokenBiz.getBizScreenParams();
        }

        return params;
    }
}
