/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3130;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3130.NFCL3130CMsg;
import business.servlet.NFCL3130.constant.NFCL3130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Canon           Y.Miyauchi      New
 *</pre>
 */
public class NFCL3130_NWAL1130 extends S21CommonHandler implements NFCL3130Constant  {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

    	NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

        NFCL3130CMsg bizMsg = new NFCL3130CMsg();
        bizMsg.setBusinessID("NFCL3130");
        bizMsg.setFunctionCode("20");
        
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("Click_rcptScr".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.Q.no(0).xxComnScrColValTxt.getValue())) {
                     scrnMsg.arRcptSrcCd.setValue(scrnMsg.Q.no(0).xxComnScrColValTxt.getValue());
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue())) {
                    scrnMsg.arRcptSrcNm.setValue(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue());
               }
            } else if ("Click_bankAcct".equals(scrEventNm)) {
               if (ZYPCommonFunc.hasValue(scrnMsg.Q.no(2).xxComnScrColValTxt.getValue())) {
                    scrnMsg.dsBankAcctPk.setValue(new BigDecimal(scrnMsg.Q.no(2).xxComnScrColValTxt.getValue()));
               }
               if (ZYPCommonFunc.hasValue(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue())) {
                   scrnMsg.dsBankAcctNm.setValue(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue());
              }
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
        NFCL3130CMsg bizMsg  = (NFCL3130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
