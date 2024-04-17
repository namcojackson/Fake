/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */
public class NMAL2520Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            setParams(params, scrnMsg);
        }

        if (NMAL2520Constant.NMAL2500_EVENT_NAME_ADD_PARENT.equals(ctx.getEventName())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2520Constant.ADD_CHILD_FROM_NMAL2500);
        }

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        // 2018/09/20 QC#27724,ADD Add Start
        String beforeTabName = scrnMsg.xxDplyTab.getValue();
        // 2018/09/20 QC#27724,ADD Add End

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/09/20 QC#27724,ADD Add Start
        NMAL2520CommonLogic.setAllBGWithReset(scrnMsg, beforeTabName);
        NMAL2520CommonLogic.setAddDelButton(this, scrnMsg);
        // 2018/09/20 QC#27724,ADD Add End

        NMAL2520CommonLogic.initialControlScreen(this, scrnMsg);
    }

    private void setParams(Object[] params, NMAL2520BMsg scrnMsg) {

        // Input Parameter.
        // Organization Name
        EZDBStringItem param0 = (EZDBStringItem) params[0];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, param0);
        }

        // Business Area
        EZDBStringItem param1 = (EZDBStringItem) params[1];
        if (ZYPCommonFunc.hasValue(param1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.bizAreaOrgCd_P1, param1);
        }

        // Line Of Business
        EZDBStringItem param2 = (EZDBStringItem) params[2];
        if (ZYPCommonFunc.hasValue(param2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd_P1, param2);
        }
    }

}
