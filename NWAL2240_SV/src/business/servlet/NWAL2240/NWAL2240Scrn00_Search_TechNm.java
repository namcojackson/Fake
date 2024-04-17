/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.constant.NWAL2240Constant.BUSINESS_ID;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0663E;
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
public class NWAL2240Scrn00_Search_TechNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.istlTechCd_DI)) {
            scrnMsg.istlTechCd_DI.setErrorInfo(1, NWAM0663E);
        }

        scrnMsg.addCheckItem(scrnMsg.istlTechCd_DI);
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

        scrnMsg.addCheckItem(scrnMsg.istlTechCd_DI);
        scrnMsg.putErrorScreen();
    }
}
