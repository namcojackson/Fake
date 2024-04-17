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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2024/02/23   Hitachi         S.Ikariya       Update          QC#63452
 *</pre>
 */
public class NFCL3020_NFDL0170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        String cNum    = scrnMsg.M.no(0).xxPopPrm.getValue();
        String cName   = scrnMsg.M.no(1).xxPopPrm.getValue();
        String locNum = scrnMsg.M.no(2).xxPopPrm.getValue();
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        String invNum = scrnMsg.M.no(3).xxPopPrm.getValue();
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]

        if (ZYPCommonFunc.hasValue(cNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.payerCustCd_BH, cNum);
        }
        if (ZYPCommonFunc.hasValue(cName)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_BH, cName);
        }
        if (ZYPCommonFunc.hasValue(locNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_BH, locNum);
        }
        // START 2024/02/23 S.Ikariya [QC#63452, ADD]
        if (ctx.getEventName().equals(SELECT_ACCT_EVENT)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_BH, invNum);
        }
        // END 2024/02/23 S.Ikariya [QC#63452, ADD]

    }
}
