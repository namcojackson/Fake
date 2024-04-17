/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.convTimeforScreen;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.initCommonButton;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.initControlFields;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setAppFracDigit;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setDelyIstlDispFlg;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setNameForMessageDeryDisp;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setScrnTm;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setTabProtect;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BUSINESS_ID;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0662E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_0;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_1;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_2;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TAB_INSTALL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2240.NWAL2240CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL2240Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        scrnMsg.clear();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.ordSrcRefNum_H0, (EZDBStringItem) params[POP_PAR_0]);
            setValue(scrnMsg.dsOrdPosnNum_H0, (EZDBStringItem) params[POP_PAR_1]);
            setValue(scrnMsg.configCatgCd_H0, (EZDBStringItem) params[POP_PAR_2]);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_H0)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd_H0)) {
                scrnMsg.setMessageInfo(NWAM0662E);
            }
        }

        NWAL2240CMsg bizMsg = new NWAL2240CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        initControlFields(this, scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;
        NWAL2240CMsg bizMsg = (NWAL2240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_INSTALL);

        setDelyIstlDispFlg(scrnMsg);
        setNameForMessageDeryDisp(scrnMsg);
        initCommonButton(this);
        setTabProtect(this, scrnMsg);
        setAppFracDigit(scrnMsg);
        convTimeforScreen(scrnMsg, bizMsg);
        setScrnTm(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ordSrcRefNum_H0);
    }
}
