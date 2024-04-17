/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2090;


import static business.servlet.NWAL2090.constant.NWAL2090Constant.BIZ_ID;
import static business.servlet.NWAL2090.constant.NWAL2090Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2090.constant.NWAL2090Constant.NMZM0009E;
import static business.servlet.NWAL2090.constant.NWAL2090Constant.NWAM0270E;
import static business.servlet.NWAL2090.constant.NWAL2090Constant.NWZM1205E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2090.NWAL2090CMsg;
import business.servlet.NWAL2090.common.NWAL2090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL2090_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL2090_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2090BMsg scrnMsg = (NWAL2090BMsg) bMsg;
        NWAL2090CMsg bizMsg = new NWAL2090CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 4) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.chngRsnTpCd, (EZDBStringItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt, (EZDBStringItem) params[3]);
            if (!ZYPCommonFunc.hasValue(scrnMsg.glblCmpyCd)) {
                scrnMsg.setMessageInfo(NMZM0009E);
                return null;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.setMessageInfo(NWZM1205E);
                return null;
            }
        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
            return null;
        }

        NWAL2090CommonLogic.setBackup(scrnMsg);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2090BMsg scrnMsg = (NWAL2090BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            NWAL2090CommonLogic.protectCmnBtnProp(this, scrnMsg);
            return;
        }

        NWAL2090CMsg bizMsg = (NWAL2090CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2090CommonLogic.initCmnBtnProp(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL2090BMsg scrnMsg = (NWAL2090BMsg) bMsg;

        scrnMsg.glblCmpyCd.setNameForMessage("Global Company Code");
        scrnMsg.cpoOrdNum.setNameForMessage("Cpo Order Number");
        scrnMsg.chngRsnTpCd.setNameForMessage("Change Reason");
        scrnMsg.xxComnScrColValTxt.setNameForMessage("Comment");
    }

}
