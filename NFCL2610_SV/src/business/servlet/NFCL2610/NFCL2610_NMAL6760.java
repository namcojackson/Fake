/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2610.NFCL2610CMsg;
import business.servlet.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2017/11/09   Fujitsu         M.Ohno          Update          QC#20587
 *</pre>
 */
public class NFCL2610_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_Customer".equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.billToCustAcctCd, scrnMsg.xxPopPrm_P0.getValue());
                setValue(scrnMsg.dsAcctNm, scrnMsg.xxPopPrm_P1.getValue());
            } else if ("OpenWin_BillToLoc".equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.billToCustLocCd, scrnMsg.xxPopPrm_PF.getValue());
                // 2017/11/09 QC#20587 add start
                setValue(scrnMsg.billToCustAcctCd, scrnMsg.xxPopPrm_P0.getValue());
                setValue(scrnMsg.dsAcctNm, scrnMsg.xxPopPrm_P1.getValue());
                // 2017/11/09 QC#20587 add start
            }

            NFCL2610CMsg bizMsg = NFCL2610CommonLogic.setRequestData_SearchCommon();
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {

            NFCL2610CMsg bizMsg = (NFCL2610CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd);
            scrnMsg.addCheckItem(scrnMsg.billToCustLocCd);
            scrnMsg.putErrorScreen();

            if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
                throw new EZDFieldErrorException();
            }
        }
        scrnMsg.xxScrEventNm.clear();
    }
}
