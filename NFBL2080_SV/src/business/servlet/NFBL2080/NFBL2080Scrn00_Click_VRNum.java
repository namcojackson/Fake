/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2080.NFBL2080CMsg;
//import business.servlet.NFBL2080.constant.NFBL2080Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/05/20   Hitachi         H.Umeda         Create          N/A
 *</pre>
 */
public class NFBL2080Scrn00_Click_VRNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        //NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        //bizMsg.setBusinessID("NFBL2080");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        scrnMsg.P.clear();
        int iCnt = getButtonSelectNumber();
        String editNumber = null;

        String submitNm = getSubmitedFieldNm(ctx);
        if ("edi".equals(submitNm)) {
            editNumber = scrnMsg.B.no(iCnt).ediPoOrdNum_B1.getValue();
        } else {
            editNumber = scrnMsg.B.no(iCnt).poOrdNum_B1.getValue();
        }
        if(editNumber.length() >= 8){
            scrnMsg.P.no(0).xxPopPrm_P1.setValue(editNumber.substring(0,8));
        } else{
            scrnMsg.P.no(0).xxPopPrm_P1.setValue(editNumber);
        }
        Object[] params = new Object[1];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P1;

        setArgForSubScreen(params);

    }
}
