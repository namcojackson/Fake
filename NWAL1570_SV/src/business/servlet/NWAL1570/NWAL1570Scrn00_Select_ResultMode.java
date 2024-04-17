/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

//import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1570.NWAL1570CMsg;
//import business.servlet.NWAL1570.constant.NWAL1570Constant;

import business.servlet.NWAL1570.common.NWAL1570CommonLogic;
import business.servlet.NWAL1570.constant.NWAL1570Constant.RSLT_MODE;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn00_Select_ResultMode
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2021/04/21   CITS            F.Deola         Update          QC#58707
 *</pre>
 */
public class NWAL1570Scrn00_Select_ResultMode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        // 1. check EZDeveloper item attribute values.
        NWAL1570CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        //
        //NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        //bizMsg.setBusinessID(BIZ_ID);
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);
        //
        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(scrnMsg.xxRsltModeCd.getValue())) {
            // START 2021/04/21 F.Deola [QC#58707, ADD]
            NWAL1570CommonLogic.selectAllHeaderStatus(scrnMsg);
            // END 2021/04/21 F.Deola [QC#58707, ADD]
            NWAL1570CommonLogic.clearIncludeImport(scrnMsg);
        } else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(scrnMsg.xxRsltModeCd.getValue())) {
            NWAL1570CommonLogic.clearStatus(scrnMsg);
            NWAL1570CommonLogic.clearDateCriteria(scrnMsg);
        }

    }
}
