/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0610BMsg scrnMsg = (NLCL0610BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.R.no(1).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
    }
}
