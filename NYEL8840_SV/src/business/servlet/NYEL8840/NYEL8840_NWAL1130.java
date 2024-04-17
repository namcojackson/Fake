/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8840;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NYEL8840_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/02   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NYEL8840_NWAL1130 extends S21CommonHandler {

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

        NYEL8840BMsg scrnMsg = (NYEL8840BMsg) bMsg;

        scrnMsg.setFocusItem(scrnMsg.xxWfAsgCd);
        if (!"CMN_Close".equals(getLastGuard())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgCd, scrnMsg.P.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgNm //
                    , S21StringUtil.concatStrings(//
                            scrnMsg.P.no(1).xxComnScrColValTxt.getValue() //
                            , " " //
                            , scrnMsg.P.no(2).xxComnScrColValTxt.getValue()));
            scrnMsg.setFocusItem(scrnMsg.effFromDt);
        }
    }
}
