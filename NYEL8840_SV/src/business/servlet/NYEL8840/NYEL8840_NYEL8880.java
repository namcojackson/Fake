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
 * 2016/09/09   Fujitsu         M.Ugaki        Create          N/A
 *</pre>
 */
public class NYEL8840_NYEL8880 extends S21CommonHandler {

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

        scrnMsg.setFocusItem(scrnMsg.xxWfAsgCd_F);
        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgCd_F, scrnMsg.usrNm_FR.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgNm_F //
                    , S21StringUtil.concatStrings(//
                            scrnMsg.firstNm_FR.getValue() //
                            , " " //
                            , scrnMsg.lastNm_FR.getValue()));
            scrnMsg.setFocusItem(scrnMsg.xxWfAsgCd);
        }
    }
}
