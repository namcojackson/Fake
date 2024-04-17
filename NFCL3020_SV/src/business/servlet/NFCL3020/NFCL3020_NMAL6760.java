/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3020.NFCL3020CMsg;
//import business.servlet.NFCL3020.constant.NFCL3020Constant;
import static business.servlet.NFCL3020.constant.NFCL3020Constant.SELECT_ACCT_EVENT;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/16   Fujitsu         H.Ikeda         Create          QC#22759
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3020_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2024/02/23 S.Ikariya [QC#63452, MOD]
        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        // END 2024/02/23 S.Ikariya [QC#63452, MOD]
        //NFCL3020CMsg bizMsg  = (NFCL3020CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        if (ctx.getEventName().equals(SELECT_ACCT_EVENT)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_BH, "");
        }
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]

    }
}
