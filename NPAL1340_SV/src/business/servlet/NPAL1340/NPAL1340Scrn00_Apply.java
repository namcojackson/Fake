/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1340;

import static business.servlet.NPAL1340.constant.NPAL1340Constant.NMZM0159E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1340.NPAL1340CMsg;
import business.servlet.NPAL1340.common.NPAL1340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/26/2017   CITS            T.Tokutomi      Create          QC#21191
 *</pre>
 */
public class NPAL1340Scrn00_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.proNum_H2) //
                && !ZYPCommonFunc.hasValue(scrnMsg.carrNm_H2)) {
            scrnMsg.proNum_H2.setErrorInfo(1, NMZM0159E, new String[]{"Tracking#", "Carrier"});
            scrnMsg.carrNm_H2.setErrorInfo(1, NMZM0159E, new String[]{"Tracking#", "Carrier"});
        }

        scrnMsg.addCheckItem(scrnMsg.proNum_H2);
        scrnMsg.addCheckItem(scrnMsg.carrNm_H2);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        NPAL1340CMsg bizMsg = new NPAL1340CMsg();
        bizMsg.setBusinessID("NPAL1340");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        NPAL1340CMsg bizMsg = (NPAL1340CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1340CommonLogic.initialize(this, scrnMsg);

        //Error check
        if(scrnMsg.carrNm_H2.isError()){
            scrnMsg.addCheckItem(scrnMsg.carrNm_H2);
        }

        scrnMsg.putErrorScreen();
    }
}
