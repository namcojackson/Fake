/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1850;

import static business.servlet.NWAL1850.constant.NWAL1850Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import business.blap.NWAL1850.NWAL1850CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1850_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1850_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, scrnMsg.P.no(0).xxComnScrColValTxt_P.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, scrnMsg.P.no(1).xxComnScrColValTxt_P.getValue());
        }

        NWAL1850CMsg bizMsg = new NWAL1850CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        NWAL1850CMsg bizMsg  = (NWAL1850CMsg) cMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("CMN_Close".equals(getLastGuard()) || bizMsg == null) {
            return;
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.dsOrdTpCd);
        }
    }
}
