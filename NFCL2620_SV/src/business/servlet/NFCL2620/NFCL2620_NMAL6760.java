/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2620;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFCL2620_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {

            setValue(scrnMsg.billToCustAcctCd_H, scrnMsg.xxPopPrm_P0.getValue());
            setValue(scrnMsg.dsAcctNm_H, scrnMsg.xxPopPrm_P1.getValue());

            if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
                throw new EZDFieldErrorException();
            }
        }
        scrnMsg.xxScrEventNm.clear();
    }
}
