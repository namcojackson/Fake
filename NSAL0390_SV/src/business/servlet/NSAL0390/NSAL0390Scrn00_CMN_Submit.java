/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0390.NSAL0390CMsg;
import business.servlet.NSAL0390.common.NSAL0390CommonLogic;
import business.servlet.NSAL0390.constant.NSAL0390Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 *</pre>
 */
public class NSAL0390Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        NSAL0390CMsg bizMsg = new NSAL0390CMsg();
        bizMsg.setBusinessID(NSAL0390Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0390Constant.FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;
        NSAL0390CMsg bizMsg = (NSAL0390CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0390CommonLogic.controlDtl(this, scrnMsg);

        // add start 2016/08/29 CSA Defect#11243
        scrnMsg.A.setCheckParam(new String[] {NSAL0390Bean.crCardCustRefNum_A1 }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
        // add end 2016/08/29 CSA Defect#11243
   }
}
