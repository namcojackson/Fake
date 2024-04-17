/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6720.constant.NMAL6720Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2016/10/20   Fujitsu         C.Yokoi         Update          QC#12060
 *</pre>
 */
public class NMAL6720Scrn00_CMN_Return extends S21CommonHandler {

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
        // 2016/10/20 CSA-QC#12060 Add Start
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        if (NMAL6720Constant.SCREEN_ID_NMAL6700.equals(scrnMsg.xxScrId.getValue())) {

            Object[] arg = (Object[]) getArgForSubScreen();
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;

                if (params != null && params.length > 4) {
                    EZDBStringItem param04 = (EZDBStringItem) params[4];
                    ZYPEZDItemValueSetter.setValue(param04, scrnMsg.xxValUpdFlg);
                }
            }
        }
        // 2016/10/20 CSA-QC#12060 Add End
    }
}
