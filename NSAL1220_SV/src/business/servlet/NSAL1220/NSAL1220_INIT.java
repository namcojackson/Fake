/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1220;

import static business.servlet.NSAL1220.common.NSAL1220CommonLogic.addCheckItem;
import static business.servlet.NSAL1220.common.NSAL1220CommonLogic.initialControlScreen;
import static business.servlet.NSAL1220.constant.NSAL1220Constant.*;

import business.blap.NSAL1220.NSAL1220CMsg;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Contract Branch Revenue Distribution
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL1220_INIT extends S21INITCommonHandler {

    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk, (EZDBBigDecimalItem) params[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk, (EZDBBigDecimalItem) params[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcInvChrgTpCd, (EZDBStringItem) params[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd, (EZDBStringItem) params[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[index++]);

        NSAL1220CMsg bizMsg = new NSAL1220CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;
        NSAL1220CMsg bizMsg  = (NSAL1220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
    }
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1220BMsg scrnMsg = (NSAL1220BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).coaBrCd_A.setNameForMessage("Branch");
            scrnMsg.A.no(i).prcAllocRate_A.setNameForMessage("Percent");
        }
        scrnMsg.prcAllocRate_T.setNameForMessage("Total");
    }
}
