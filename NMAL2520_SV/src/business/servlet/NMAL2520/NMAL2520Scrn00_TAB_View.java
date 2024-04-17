/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */
public class NMAL2520Scrn00_TAB_View extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // 2016/02/05 CSA-QC#2869 Mod Start
        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        // 2016/02/05 CSA-QC#2869 Mod End

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2016/02/05 CSA-QC#2869 Del Start
        // NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        // bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        // bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        // return bizMsg;
        // 2016/02/05 CSA-QC#2869 Del End
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // 2016/02/05 CSA-QC#2869 Mod Start
        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // if (scrnMsg.getMessageType() ==
        // EZDMessageInfo.MSGTYPE_ERROR || scrnMsg.getMessageType() ==
        // EZDMessageInfo.MSGTYPE_WARNING) {
        // throw new EZDFieldErrorException();
        // }
        // 2016/02/05 CSA-QC#2869 Mod End
        // 2018/09/20 QC#27724,ADD Add Start
        NMAL2520CommonLogic.clearAllRowsBG(scrnMsg);
        // 2018/09/20 QC#27724,ADD Add End
        NMAL2520CommonLogic.convertTreeInfo(scrnMsg, bizMsg.T);
        scrnMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_VIEW_HIERARCHY);
    }
}
