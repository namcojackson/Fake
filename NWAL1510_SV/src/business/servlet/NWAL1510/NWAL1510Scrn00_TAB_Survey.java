/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.getScrnTm;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setTabProtect;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_SURVEY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1510.NWAL1510CMsg;
import business.servlet.NWAL1510.common.NWAL1510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   CUSA            S.Ohki          Create          N/A
 * 2018/07/17   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 *</pre>
 */
public class NWAL1510Scrn00_TAB_Survey extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2018/07/17 S21_NA#26188 Mod Start
        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        getScrnTm(scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        // return null;
        // 2018/07/17 S21_NA#26188 Mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        // 2018/07/20 S21_NA#26188 Add Start
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        NWAL1510CommonLogic.setScrnTm(scrnMsg);
        NWAL1510CommonLogic.setDelyIstlDispFlg(scrnMsg);
        // 2018/07/20 S21_NA#26188 Add End
        scrnMsg.xxDplyTab.setValue(TAB_SURVEY);
        setTabProtect(this, scrnMsg);
    }
}
