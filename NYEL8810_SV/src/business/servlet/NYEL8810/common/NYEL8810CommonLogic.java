/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810.common;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_SAV;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_SUB;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_APL;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_APR;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_RJT;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_DWL;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_DEL;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_CLR;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_RST;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BTN_CMN_RTN;
import parts.common.EZDGUIAttribute;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.SCRN_ID_00;
import business.servlet.NYEL8810.NYEL8810BMsg;
import business.servlet.NYEL8810.NYEL8810_ABMsgArray;
import business.servlet.NYEL8810.constant.NYEL8810Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenSerializer;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NYEL8810CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         Q09079          Create          N/A
 * 2017/11/01   Fujitsu         Q10814          Update          QC#21078
 *</pre>
 */
public class NYEL8810CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NYEL8810BMsg scrnMsg) {
        initCmnBtnProp(handler, scrnMsg, true);
    }

    /**
     * Initial Common Button properties.
     * @param handler  Event Handler
     * @param scrnMsg
     * @param clearAttr
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NYEL8810BMsg scrnMsg, boolean clearAttr) {
        int aprflg = 0;
        int fyiflg = 0;
        int asgflg = 0;
        int canflg = 0;
        int max = scrnMsg.A.getValidCount();

        if (clearAttr){
            scrnMsg.clearAllGUIAttribute(NYEL8810Constant.SCRN_ID_00);
        }

        for (int cnt = 0; cnt < max; cnt++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(cnt).xxWfAprChkVisFlg_A.getValue())) {
                aprflg = 1;
                break;
            }
        }

        for (int cnt = 0; cnt < max; cnt++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(cnt).xxWfFyiChkVisFlg_A.getValue())) {
                fyiflg = 1;
                break;
            }
        }

        for (int cnt = 0; cnt < max; cnt++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(cnt).xxWfRasgChkVisFlg_A.getValue())) {
                asgflg = 1;
                break;
            }
        }

        for (int cnt = 0; cnt < max; cnt++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(cnt).cancFlg_VF.getValue())) {
                canflg = 1;
                break;
            }
        }

        // QC#23516 MOD START 2018/04/24
//        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], aprflg, null);
        // QC#23516 MOD END 2018/04/24
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], fyiflg, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], aprflg, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], aprflg, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], canflg, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        if (asgflg > 0) {
            handler.setButtonEnabled("ReAssign", true);
        } else {
            handler.setButtonEnabled("ReAssign", false);
        }

        if (max > 0){
            handler.setButtonEnabled("MoveWin_ProcessStatus", true);
            handler.setButtonEnabled("MoveWin_Detail", true);
        } else {
            handler.setButtonEnabled("MoveWin_ProcessStatus", false);
            handler.setButtonEnabled("MoveWin_Detail", false);
        }

        scrnMsg.usrNm.setInputProtected(true);
        scrnMsg.xxDtlNm_F.setInputProtected(true);
        scrnMsg.xxDtlNm_T.setInputProtected(true);
        scrnMsg.xxWfAsgNm.setInputProtected(true);
    }

    /**
     * Initial Control properties.
     * @param handler Event Handler
     * @param scrnMsg Screen Message(BMsg)
     */
    public static void initRowCtrlProp(S21CommonHandler handler, NYEL8810BMsg scrnMsg) {
        initRowCtrlProp(handler, scrnMsg, true);
    }

    /**
     * Initial Control properties.
     * @param handler  Event Handler
     * @param scrnMsg Screen Message(BMsg)
     * @param clearAttr Clear Flag
     */
    public static void initRowCtrlProp(S21CommonHandler handler, NYEL8810BMsg scrnMsg, boolean clearAttr) {

        if (clearAttr){
            scrnMsg.clearAllGUIAttribute(NYEL8810Constant.SCRN_ID_00);
        }

        int max = scrnMsg.A.getValidCount();

        for (int cnt = 0; cnt < max; cnt++) {

            // Type
            scrnMsg.A.no(cnt).xxWfPsblActNm_A.setInputProtected(true);

            // Workflow ID
            scrnMsg.A.no(cnt).wfProcPk_A.setInputProtected(true);

            // Document ID
            scrnMsg.A.no(cnt).wfProcDocId_A.setInputProtected(true);

            // Process Name
            scrnMsg.A.no(cnt).wfProcNm_A.setInputProtected(true);

            // Task Status
            scrnMsg.A.no(cnt).wfWrkItemNm_LK.clear();

            if (S21NwfWorkItem.STATUS.RUN.getCode().equals(scrnMsg.A.no(cnt).wfWrkItemStsCd_A.getValue())) {
                scrnMsg.A.no(cnt).wfWrkItemNm_LK.setValue(ZYPConstant.FLG_ON_Y);
            }

            // Attr1-5
            scrnMsg.A.no(cnt).xxScrItem130Txt_V1.setInputProtected(true);
            scrnMsg.A.no(cnt).xxScrItem130Txt_V2.setInputProtected(true);
            scrnMsg.A.no(cnt).xxScrItem130Txt_V3.setInputProtected(true);
            scrnMsg.A.no(cnt).xxScrItem130Txt_V4.setInputProtected(true);
            scrnMsg.A.no(cnt).xxScrItem130Txt_V5.setInputProtected(true);

            // From
            scrnMsg.A.no(cnt).xxWfAsgFromNm_A.setInputProtected(true);

            // To
            scrnMsg.A.no(cnt).xxWfAsgToNm_A.setInputProtected(true);

            // Last Actin By
            scrnMsg.A.no(cnt).xxWfActOpNm_A.setInputProtected(true);

            // Comment
            scrnMsg.A.no(cnt).wfCmntTxt_A.setInputProtected(true);

        }
    }

    private static void setVisibility(NYEL8810BMsg scrnMsg, String id, boolean isVisible) {
        EZDGUIAttribute ctrl = new EZDGUIAttribute(NYEL8810Constant.SCRN_ID_00, id);
        ctrl.setVisibility(isVisible);
        scrnMsg.addGUIAttribute(ctrl);
    }

    private static void setDisabled(NYEL8810BMsg scrnMsg, String id) {
        EZDGUIAttribute ctrl = new EZDGUIAttribute(NYEL8810Constant.SCRN_ID_00, id);
        ctrl.setAttribute("disabled", "disabled");
        scrnMsg.addGUIAttribute(ctrl);
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
     * @param scrnMsg NYEL8810BMsg
     * @param scrnAMsgAry NYEL8810_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NYEL8810BMsg scrnMsg, NYEL8810_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, 1);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NYEL8810BMsg
     * @param scrnAMsgAry NYEL8810_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NYEL8810BMsg scrnMsg, NYEL8810_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Get move window parameter
     * @param handler
     * @param scrnMsg
     * @return move window parameter
     */
    public static Object[] getMoveWinParam(S21CommonHandler handler, NYEL8810BMsg scrnMsg){
        // Argument
        int selectedIndex = handler.getButtonSelectNumber();
        S21NwfTokenSerializer ser = new S21NwfTokenSerializer();
        S21NwfTokenObj tokenBiz = null;
        try {
            String wfProcStsCd = scrnMsg.A.no(selectedIndex).wfProcStsCd_A.getValue();
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

// 2017/11/01  Add start QC#21078
    /**
     * Get move window parameter (Subject)
     * @param handler
     * @param scrnMsg
     * @param sbjNum
     * @return move window parameter
     */
    public static Object[] getMoveWinParamSbj(S21CommonHandler handler, NYEL8810BMsg scrnMsg, int sbjNum){
        // Argument
        int selectedIndex = handler.getButtonSelectNumber();
        S21NwfTokenSerializer ser = new S21NwfTokenSerializer();
        S21NwfTokenObj tokenBiz = null;
        try {
            String wfProcStsCd = scrnMsg.A.no(selectedIndex).wfProcStsCd_A.getValue();
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
            switch(sbjNum) {
                case 1:
                    params = tokenBiz.getBizScreenParamsSbj1();
                    break;
                case 2:
                    params = tokenBiz.getBizScreenParamsSbj2();
                    break;
                case 3:
                    params = tokenBiz.getBizScreenParamsSbj3();
                    break;
                case 4:
                    params = tokenBiz.getBizScreenParamsSbj4();
                    break;
                case 5:
                    params = tokenBiz.getBizScreenParamsSbj5();
                    break;
            }
        }

        return params;
    }
// 2017/11/01 Add end QC#21078
    
    
    /**
     * commonAddCheckItem
     * @param scrnMsg NMAL0100BMsg
     */
    public static void commonAddCheckItem(NYEL8810BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);

        scrnMsg.usrId.setIndispensable(true);
        scrnMsg.addCheckItem(scrnMsg.usrId);
        scrnMsg.addCheckItem(scrnMsg.wfProcPk);
        scrnMsg.addCheckItem(scrnMsg.procStsCd);
        scrnMsg.addCheckItem(scrnMsg.wfProcNm);
        scrnMsg.addCheckItem(scrnMsg.wfWrkItemNm);
        scrnMsg.addCheckItem(scrnMsg.wfProcDocId);
        scrnMsg.addCheckItem(scrnMsg.wfUsrGrpNm_F);
        scrnMsg.addCheckItem(scrnMsg.xxDtlNm_F);
        scrnMsg.addCheckItem(scrnMsg.wfUsrGrpNm_T);
        scrnMsg.addCheckItem(scrnMsg.xxDtlNm_T);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_SS);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_SE);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_DS);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_DE);
        scrnMsg.addCheckItem(scrnMsg.xxWfAprChkFlg);
        scrnMsg.addCheckItem(scrnMsg.xxWfFyiChkFlg);
        scrnMsg.addCheckItem(scrnMsg.xxWfConfFlg);
        scrnMsg.addCheckItem(scrnMsg.wfUsrGrpNm_F);
        scrnMsg.addCheckItem(scrnMsg.wfUsrGrpNm_T);
    }
}
