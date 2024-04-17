/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0060;

import static business.servlet.NWCL0060.constant.NWCL0060Constant.FROM_EML_ADDR_DEF;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/05   Fujitsu         H.Nagashima     Create          QC#10673
 * 2022/03/31   CITS            A.Cullano       Update          QC#59828
 *</pre>
 */
public class NWCL0060Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0060BMsg scrnMsg = (NWCL0060BMsg) bMsg;
        // START 2022/03/31 A.Cullano [QC#59828, ADD]
        scrnMsg.xxRadioBtn_H1.setValue(FROM_EML_ADDR_DEF);
        // END 2022/03/31 A.Cullano [QC#59828, ADD]
        scrnMsg.emlAddr_H1.clear();
        scrnMsg.xxMlCmntTxt_H1.clear();

    }
}
