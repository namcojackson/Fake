/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.DISP_RELN_ACCT_CD_ACCT;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.SEARCH_MODE_CD_HRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/06/2016   SRAA            Y.Chen          Create          QC#11456
 *</pre>
 */
public class NMAL6710Scrn00_OnChange_SearchMode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        if (SEARCH_MODE_CD_HRCH.equals(scrnMsg.xxAcctSrchModeCd_D1.getValue())) {
            scrnMsg.xxAcctSrchDplyRelnCd_D4.setValue(DISP_RELN_ACCT_CD_ACCT);
        } else {
            scrnMsg.xxAcctSrchDplyRelnCd_D4.clear();
        }
    }
}
