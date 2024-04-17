/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.LINK_SVC_PRC_LIST;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0655E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_SP;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Service_Price_List_Service_Pricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        ZYPTableUtil.clear(scrnMsg.P);

        int index = getButtonSelectNumber();
        scrnMsg.xxNum_A.setValue(index);

        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(index);
        if (aScrnMsg.prcCatgNm_A.isInputProtected()) {
            scrnMsg.setMessageInfo(NSAM0655E, new String[] {LINK_SVC_PRC_LIST, LINK_SVC_PRC_LIST });
            throw new EZDFieldErrorException();
        }
        Object[] params = NSAL1330CommonLogic.getNWAL1760Prm(scrnMsg, POP_UP_SVC_PRC_SP, PRC_CTX_TP.SERVICE_PRICING, null);
        setArgForSubScreen(params);

    }

}
