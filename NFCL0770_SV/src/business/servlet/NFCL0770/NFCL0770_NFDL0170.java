/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 *</pre>
 */
public class NFCL0770_NFDL0170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        String payerCustCd    = scrnMsg.M.no(0).xxPopPrm.getValue();
        String dsAcctNm   = scrnMsg.M.no(1).xxPopPrm.getValue();

        if (ZYPCommonFunc.hasValue(payerCustCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.payerCustCd, payerCustCd);
            NFCL0770CommonLogic.protectCustomer(scrnMsg, this);
        }
        if (ZYPCommonFunc.hasValue(dsAcctNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm, dsAcctNm);
        }
    }
}
