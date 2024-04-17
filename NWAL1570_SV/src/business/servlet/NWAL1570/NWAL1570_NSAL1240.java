/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570_NSAL1240
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1570_NSAL1240 extends S21CommonHandler {

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

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_ContractNumber".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.xxDsContrNumSrchTxt);
        } else if ("OpenWin_ConfigNumber".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk.getValue())) {
                scrnMsg.xxSvcConfigMstrSrchTxt.setValue(scrnMsg.svcConfigMstrPk.getValue().toString());
            } else {
                scrnMsg.xxSvcConfigMstrSrchTxt.clear();
            }
            scrnMsg.setFocusItem(scrnMsg.xxSvcConfigMstrSrchTxt);
        }
    }
}
