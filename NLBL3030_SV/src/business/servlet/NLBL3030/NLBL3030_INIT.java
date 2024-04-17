/*
 * <Pre> Copyright (c) 2013 Canon USA Inc. All rights reserved. </Pre>
 */
package business.servlet.NLBL3030;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3030.NLBL3030CMsg;
import business.servlet.NLBL3030.common.NLBL3030CommonLogic;
import business.servlet.NLBL3030.constant.NLBL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Message Entry PopUo
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/16   Fujitsu         C.Naito         Create          (From NATL6050)
 * </pre>
 */
public class NLBL3030_INIT extends S21INITCommonHandler implements NLBL3030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLBL3030BMsg scrnMsg = (NLBL3030BMsg) bMsg;
        Serializable arg = getArgForSubScreen();

        if (arg != null && arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            scrnMsg.xxOpsTp.setValue(((EZDBStringItem) params[0]).getValue());

            if (EDIT_MODE.equals(scrnMsg.xxOpsTp.getValue()) || INQUIRY_MODE.equals(scrnMsg.xxOpsTp.getValue())) {
                if (params.length > 1) {
                    scrnMsg.A.no(0).msgCtrlTpCd.setValue(((EZDBStringItem) params[1]).getValue());
                }
                if (params.length > 2) {
                    scrnMsg.A.no(0).xxDsMsgEntryTxt.setValue(((EZDBStringItem) params[2]).getValue());
                }
                scrnMsg.A.setValidCount(1);
            } else if (MULTI_INQUIRY_MODE.equals(scrnMsg.xxOpsTp.getValue())) {
                scrnMsg.msgCtrlTpDescTxt.setValue(((EZDBStringItem) params[1]).getValue());
                int countItem = 0;
                for (int i = 2; i + 1 < params.length; i += 2) {
                    scrnMsg.A.no(countItem).msgCtrlTpCd.setValue(((EZDBStringItem) params[i]).getValue());
                    scrnMsg.A.no(countItem).xxDsMsgEntryTxt.setValue(((EZDBStringItem) params[i + 1]).getValue());
                    countItem++;
                }
                scrnMsg.A.setValidCount(countItem);
            } else {
                scrnMsg.setMessageInfo(NWCM0070E);
            }
        } else {
            scrnMsg.setMessageInfo(NWCM0070E);
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if (ZYPCommonFunc.hasValue(bMsg.getMessageCode())) {
            return null;
        }
        NLBL3030BMsg scrnMsg = (NLBL3030BMsg) bMsg;

        NLBL3030CMsg bizMsg = new NLBL3030CMsg();
        bizMsg.setBusinessID("NLBL3030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3030BMsg scrnMsg = (NLBL3030BMsg) bMsg;
        NLBL3030CMsg bizMsg = (NLBL3030CMsg) cMsg;
        if (bizMsg == null || "E".equals(bizMsg.getMessageKind())) {
            NLBL3030CommonLogic.initCommonButton(this, INIT_ERR);
            NLBL3030CommonLogic.initButton(this, INIT_ERR);
            NLBL3030CommonLogic.initScrn(this, scrnMsg, INIT_ERR);
            return;
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLBL3030CommonLogic.initCommonButton(this, scrnMsg.xxOpsTp.getValue());
        NLBL3030CommonLogic.initButton(this, scrnMsg.xxOpsTp.getValue());
        NLBL3030CommonLogic.initScrn(this, scrnMsg, scrnMsg.xxOpsTp.getValue());
        setNameForMessage(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLBL3030BMsg scrnMsg = (NLBL3030BMsg) bMsg;

        scrnMsg.xxDsMultMsgDplyTxt.setNameForMessage(scrnMsg.msgCtrlTpDescTxt.getValue());

    }

}
