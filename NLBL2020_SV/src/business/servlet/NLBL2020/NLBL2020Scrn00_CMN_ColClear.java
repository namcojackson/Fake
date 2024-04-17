/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_UPD;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/24/2015   CITS            Hisashi         Create          N/A
 * 08/31/2016   CITS            S.Endo          Update          QC#10597
 *</pre>
 */
public class NLBL2020Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        return;
    }
}
