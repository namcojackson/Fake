/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/08/01   Fujitsu         S.Fujita        Update          QC#10148
 *</pre>
 */
public class NFCL3000_NMAL6760 extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/08/01 S.Fujita [QC#10148,DEL]
//        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
//        if(ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd_H2.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.locNum_H2.getValue())) {
//            if(!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_H2.getValue())) {
//                scrnMsg.shipToCustAcctCd_H2.setErrorInfo(1, "NFCM0029E");
//                scrnMsg.locNum_H2.setErrorInfo(1, "NFCM0029E");
//                scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H2);
//                scrnMsg.addCheckItem(scrnMsg.locNum_H2);
//            }
//        }
//        if(ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd_H3.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.locNum_H3.getValue())) {
//            if(!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_H3.getValue())) {
//                scrnMsg.billToCustAcctCd_H3.setErrorInfo(1, "NFCM0029E");
//                scrnMsg.locNum_H3.setErrorInfo(1, "NFCM0029E");
//                scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H3);
//                scrnMsg.addCheckItem(scrnMsg.locNum_H3);
//            }
//        }
//        scrnMsg.putErrorScreen();
        // END   2016/08/01 S.Fujita [QC#10148,DEL]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg = new NFCL3000CMsg();

        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (!CMN_CLOSE.equals(getLastGuard())) {
        }

    }
}
