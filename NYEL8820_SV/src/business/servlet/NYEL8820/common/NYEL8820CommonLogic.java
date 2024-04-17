/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8820.common;

import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_SAV;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_SUB;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_APL;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_APR;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_RJT;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_DWL;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_DEL;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_CLR;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_RST;
import static business.servlet.NYEL8820.constant.NYEL8820Constant.BTN_CMN_RTN;

import parts.common.EZDMessageInfo;
import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.ZZZM9006E;
import business.servlet.NYEL8820.NYEL8820BMsg;

import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_COND_STS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.model.S21NwfWorkItem;
import com.canon.cusa.s21.framework.nwf.core.model.impl.S21NwfWorkItemImpl;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenSerializer;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.process.S21NwfUtilProcessFactory;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8820CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/17   Fujitsu         Q09079          Create          N/A
 * 2018/04/24   Fujitsu         Q10814          Update          QC#23516
 *</pre>
 */
public class NYEL8820CommonLogic {

    /**
     * @param scrnMsg
     * @param handler
     */
    public static void init(NYEL8820BMsg scrnMsg, S21CommonHandler handler, String glblCmpyCd) {
        // button
        initCmnBtnProp(handler);

        // input text protect
        inputProtect(scrnMsg);

        // approve,reject,Comment control
        initCtrl(scrnMsg, handler, glblCmpyCd);

        // link
        //cmnLinkProp(scrnMsg);

        // Continue,Back
        btnCtrl(scrnMsg, handler);
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    private static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * @param scrnMsg
     * @param handler
     */
    private static void initCtrl(NYEL8820BMsg scrnMsg, S21CommonHandler handler, String glblCmpyCd) {
        int wfaccessApp = 0;
        boolean isApproval = false;
        int wfrejectApp = 0;
        boolean wfaccessApr = false;
        boolean wfaccessRej = false;

        wfaccessApr = NYXC880002.isPossibleBtn(scrnMsg.wfProcNm_T.getValue(), BTN_CMN_APR[0], glblCmpyCd);
        wfaccessRej = NYXC880002.isPossibleBtn(scrnMsg.wfProcNm_T.getValue(), BTN_CMN_RJT[0], glblCmpyCd);

        if ((wfaccessApr == false) && (wfaccessRej == false)) {
            return;
        }

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfProcess process = null;
        int index = scrnMsg.xxCellIdx.getValue().intValue();

        try {
            S21NwfContext context = factory.getContex();
            context.setProcessFactory(S21NwfUtilProcessFactory.getInstance());
            process = context.getProcess(scrnMsg.P.no(index).wfProcPk_P.getValue());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            scrnMsg.setMessageInfo(ZZZM9006E, new String[] {scrnMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            return;
        } catch (S21NwfSystemException e) {
            e.printStackTrace();
            EZDMessageInfo info = e.getMessageInfo();

            if (info != null) {
                scrnMsg.setMessageInfo(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            } else {
                scrnMsg.setMessageInfo(ZZZM9006E, new String[] {scrnMsg.P.no(index).wfProcPk_P.getValue().toPlainString() });
            }
            return;
        }

        if (process != null) {
            S21NwfToken token = process.getToken();

            if (process.isActive()) {
                S21NwfWorkItemImpl activeTask = token.getActiveWorkItem();

                if ((activeTask != null) && (activeTask.getWorkItemUId().equals(scrnMsg.P.no(index).wfWrkItemPk_P.getValue()))) {
                    if (token.getActiveWorkItem().isApprovable()) {
                        wfaccessApp = 1;
                        if (wfaccessApr) {
                            isApproval = true;
                        }
                    }

                    if (token.getActiveWorkItem().isRejectable()) {
                        wfrejectApp = 1;
                        if (wfaccessRej) {
                            isApproval = true;
                        }
                    }
                }
            }

            // QC#23516 ADD START 2018/04/24
            if (isApproval) {
                handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
            }
            // QC#23516 ADD END 2018/04/24
            scrnMsg.wfCmntTxt.setInputProtected(!isApproval);
            if (wfaccessApr) {
                handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], wfaccessApp, null);
            }

            if (wfaccessRej) {
                handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], wfrejectApp, null);
            }
        }
    }

    /**
     * Input Protect.
     * @param scrnMsg
     */
    private static void inputProtect(NYEL8820BMsg scrnMsg) {

        scrnMsg.wfProcNm.setInputProtected(true);
        scrnMsg.xxWfProcStsNm.setInputProtected(true);
        scrnMsg.wfWrkItemNm.setInputProtected(true);
        scrnMsg.xxWfWrkItemStsNm.setInputProtected(true);
        scrnMsg.xxDtTm_T.setInputProtected(true);
        scrnMsg.wfCmntTxt_T.setInputProtected(true);
        scrnMsg.wfBizAttrbLbTxt_01.setInputProtected(true);
        scrnMsg.wfBizAttrbLbTxt_02.setInputProtected(true);
        scrnMsg.wfBizAttrbLbTxt_03.setInputProtected(true);
        scrnMsg.wfBizAttrbLbTxt_04.setInputProtected(true);
        scrnMsg.wfBizAttrbLbTxt_05.setInputProtected(true);
        scrnMsg.wfBizAttrbTxt_01.setInputProtected(true);
        scrnMsg.wfBizAttrbTxt_02.setInputProtected(true);
        scrnMsg.wfBizAttrbTxt_03.setInputProtected(true);
        scrnMsg.wfBizAttrbTxt_04.setInputProtected(true);
        scrnMsg.wfBizAttrbTxt_05.setInputProtected(true);
        scrnMsg.xxWfAsgFromNm.setInputProtected(true);
        scrnMsg.xxWfAsgToNm.setInputProtected(true);
        scrnMsg.xxWfActOpNm.setInputProtected(true);
        scrnMsg.actWfCondNm.setInputProtected(true);
    }

    /**
     * Common Link properties.
     * @param scrnMsg NYEL8830BMsg
     */
    private static void cmnLinkProp(NYEL8820BMsg scrnMsg) {

        if ((S21NwfWorkItem.STATUS.RUN.getCode().equals(scrnMsg.wfWrkItemStsCd.getValue())) && 
// 2018/05/16 MOD START
//                (S21StringUtil.isNotEmpty(scrnMsg.bizAppId.getValue()))){
                (S21StringUtil.isNotEmpty(scrnMsg.wfBizScrId.getValue()))){
// 2018/05/16 MOD END
            scrnMsg.wfWrkItemNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.wfWrkItemNm_LK.clear();
        }
    }
    
    
    /**
     * Get move window parameter
     * @param handler
     * @param scrnMsg
     * @return move window parameter
     */
    public static Object[] getMoveWinParam(NYEL8820BMsg scrnMsg){

        // Argument
        S21NwfTokenSerializer ser = new S21NwfTokenSerializer();
        S21NwfTokenObj tokenBiz = null;
        try {
            String wfProcStsCd = scrnMsg.wfProcStsCd.getValue();
            if (WF_PROC_COND_STS.COMPLETED.equals(wfProcStsCd)) {
                tokenBiz = ser.findCpltByKey(scrnMsg.wfTokenBizPk.getValue());
            }
            else {
                tokenBiz = ser.findByKey(scrnMsg.wfTokenBizPk.getValue());
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

    public static void btnCtrl(NYEL8820BMsg scrnMsg, S21CommonHandler handler){
        int max = scrnMsg.P.getValidCount();
        int index = scrnMsg.xxCellIdx.getValueInt();

        if (max <= 1){
            handler.setButtonEnabled("Back", false);
            handler.setButtonEnabled("Continue", false);
        } else if (index == 0){
            handler.setButtonEnabled("Back", false);
            handler.setButtonEnabled("Continue", true);
        } else if ((index + 1) >= max){
            handler.setButtonEnabled("Back", true);
            handler.setButtonEnabled("Continue", false);
        } else{
            handler.setButtonEnabled("Back", true);
            handler.setButtonEnabled("Continue", true);
        }
    }
}
