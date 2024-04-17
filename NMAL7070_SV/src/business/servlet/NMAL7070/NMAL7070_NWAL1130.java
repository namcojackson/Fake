/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070;

import static business.servlet.NMAL7070.constant.NMAL7070Constant.*;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.EVNT_CSMP;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070_NWAL1130 extends S21CommonHandler {

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

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVNT_CSMP.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.csmpNum, scrnMsg.R.no(0).xxComnScrColValTxt);
            } else if (EVNT_BRANCH.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd, scrnMsg.R.no(0).xxComnScrColValTxt);
            }
        }
    }
}
