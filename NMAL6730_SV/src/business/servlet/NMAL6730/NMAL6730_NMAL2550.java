/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.FUNC_ID1;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.FUNC_ID2;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6730.NMAL6730CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 *</pre>
 */
public class NMAL6730_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        scrnMsg.coaCmpyCd_H1.setValue(scrnMsg.xxPopPrm_P1.getValue());
        scrnMsg.coaAfflCd_H1.setValue(scrnMsg.xxPopPrm_P2.getValue());
        scrnMsg.coaBrCd_H1.setValue(scrnMsg.xxPopPrm_P3.getValue());
        scrnMsg.coaCcCd_H1.setValue(scrnMsg.xxPopPrm_P4.getValue());
        scrnMsg.coaAcctCd_H1.setValue(scrnMsg.xxPopPrm_P5.getValue());
        scrnMsg.coaProdCd_H1.setValue(scrnMsg.xxPopPrm_P6.getValue());
        scrnMsg.coaChCd_H1.setValue(scrnMsg.xxPopPrm_P7.getValue());
        scrnMsg.coaProjCd_H1.setValue(scrnMsg.xxPopPrm_P8.getValue());
        scrnMsg.coaExtnCd_H1.setValue(scrnMsg.xxPopPrm_P9.getValue());
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        bizMsg.setBusinessID("NMAL6730");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
        NMAL6730CMsg bizMsg  = (NMAL6730CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if(FUNC_ID1.equals(scrnMsg.xxPopPrm_P0.getValue())){
            scrnMsg.setFocusItem(scrnMsg.coaChCd_H1);
        } else if(FUNC_ID2.equals(scrnMsg.xxPopPrm_P0.getValue())){
            // QC#9448
            // scrnMsg.setFocusItem(scrnMsg.coaAfflCd_H1);
        }
    }
}
