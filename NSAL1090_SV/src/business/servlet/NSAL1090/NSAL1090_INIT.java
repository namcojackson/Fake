/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1090;

import static business.servlet.NSAL1090.constant.NSAL1090Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1090.NSAL1090CMsg;
import business.servlet.NSAL1090.common.NSAL1090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Credit Rebill Detail Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Hitachi         A.Kohinata      Create          N/A
 * 2017/11/07   Hitachi         K.Kojima        Update          QC#22331
 * 2021/01/12   CITS            R.Shimamoto     Update          QC#58177
 *</pre>
 */
public class NSAL1090_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        // START 2021/01/12 [QC#58177] ADD
        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        scrnMsg.xxWrnSkipFlg.clear();
        // END 2021/01/12 [QC#58177] ADD

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        NSAL1090CMsg bizMsg = new NSAL1090CMsg();

        scrnMsg.custIncdtId_H.clear();
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length > 0) {
                // START 2017/11/07 K.Kojima [QC#22224,MOD]
                // setValue(scrnMsg.custIncdtId_H, (EZDBStringItem) params[0]);
                if (params[0] instanceof EZDBStringItem) {
                    setValue(scrnMsg.custIncdtId_H, (EZDBStringItem) params[0]);
                } else if (params[0] instanceof String) {
                    setValue(scrnMsg.custIncdtId_H, (String) params[0]);
                }
                // END 2017/11/07 K.Kojima [QC#22224,MOD]
            }
        }

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1090BMsg scrnMsg = (NSAL1090BMsg) bMsg;
        NSAL1090CMsg bizMsg = (NSAL1090CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1090CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.svcCrRebilProcCd_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
    }
}
