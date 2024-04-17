/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0420;

import static business.servlet.NSAL0420.constant.NSAL0420Constant.BIZ_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0420.NSAL0420CMsg;
import business.servlet.NSAL0420.common.NSAL0420CommonLogic;
import business.servlet.NSAL0420.constant.NSAL0420Constant.FUNC;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Representative Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0420_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0420BMsg scrnMsg = (NSAL0420BMsg) bMsg;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] params = (Object[]) ser;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            EZDBStringItem param4 = (EZDBStringItem) params[4];

            ZYPEZDItemValueSetter.setValue(scrnMsg.svcRgNm_H, param0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpDescTxt_H, param1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd_H, param2);
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrDescTxt_H, param3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxGenlFldAreaTxt_H, param4);
        }

        NSAL0420CMsg bizMsg = new NSAL0420CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0420BMsg scrnMsg = (NSAL0420BMsg) bMsg;
        NSAL0420CMsg bizMsg = (NSAL0420CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0420CommonLogic.initControlCommonButton(this);
        NSAL0420CommonLogic.initCommonButton(this);
        NSAL0420CommonLogic.protectFields(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0420BMsg scrnMsg = (NSAL0420BMsg) bMsg;
        scrnMsg.svcRgNm_H.setNameForMessage("Region Name");
        scrnMsg.lineBizTpDescTxt_H.setNameForMessage("LOB Name");
        scrnMsg.svcContrBrCd_H.setNameForMessage("Branch Code");
        scrnMsg.svcContrBrDescTxt_H.setNameForMessage("Branch Name");
        scrnMsg.xxGenlFldAreaTxt_H.setNameForMessage("Resource Name");
    }
}
