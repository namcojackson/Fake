/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1570.NWAL1570CMsg;
//import business.servlet.NWAL1570.constant.NWAL1570Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1570Scrn01_TransitionByInvoiceNumber extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        //NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        //bizMsg.setBusinessID("NWAL1570");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        NWAL1570_ABMsg recordMsg = scrnMsg.A.no(getButtonSelectNumber());

        List<EZDBStringItem> param = null;
        param = createNFCL3000Parameters(recordMsg);

        setArgForSubScreen(param.toArray(new EZDBStringItem[0]));

    }

    private List<EZDBStringItem> createNFCL3000Parameters(NWAL1570_ABMsg recordMsg) {

        List<EZDBStringItem> param = new ArrayList<EZDBStringItem>();

        // [0]:Invoice Number
        param.add(recordMsg.invNum_A1);

        return param;
    }

}
