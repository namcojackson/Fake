/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.convTimeforScreen;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.initCommonButton;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setAppFracDigit;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setDelyIstlDispFlg;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setNameForMessageDeryDisp;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setScrnTm;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setTabProtect;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BUSINESS_ID;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0014E;
import parts.common.EZDBMsg;
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
public class NWAL2240Scrn00_Search_Order extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.ordSrcRefNum_H0)) {
            scrnMsg.ordSrcRefNum_H0.setErrorInfo(1, NWAM0014E);
        }

        scrnMsg.addCheckItem(scrnMsg.ordSrcRefNum_H0);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        NWAL2240CMsg bizMsg = new NWAL2240CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;
        NWAL2240CMsg bizMsg = (NWAL2240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

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
