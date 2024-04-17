/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3170.NFCL3170CMsg;
//import business.servlet.NFCL3170.constant.NFCL3170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2023/02/20   Hitachi         R.Takau         Update          QC#55645
 *</pre>
 */
public class NFCL3170Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        //NFCL3170CMsg bizMsg = new NFCL3170CMsg();
        //bizMsg.setBusinessID("NFCL3170");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //START 2023/02/07 R.Takau  [QC#55645,ADD]
        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 3) {
           if (scrnMsg.A.getValidCount() > 0) {
               BigDecimal dsCustBankAcctRelnPk = scrnMsg.A.no(0).dsCustBankAcctRelnPk_A1.getValue();
               if (ZYPCommonFunc.hasValue(dsCustBankAcctRelnPk)) {
                   ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[2], dsCustBankAcctRelnPk);
               }
           }
        }
        //END 2023/02/07 R.Takau  [QC#55645,ADD]
    }
}
