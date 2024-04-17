/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_BILL_TO_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;
import business.servlet.NMAL6720.constant.NMAL6720Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/29   SRAA            Y.Chen          Create          QC#6559
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_UploadContact extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        scrnMsg.P.clear();
        scrnMsg.xxScrEventNm_P.setValue(EVENT_BILL_TO_SEARCH);
        scrnMsg.P.no(0).xxPopPrm_P.setValue(NMAL6720Constant.CSV_UPD_ID_CTAC);
        Object[] params = NMAL6720CommonLogic.toArray_popup(scrnMsg.P, 1);
        setArgForSubScreen(params);
    }
}
