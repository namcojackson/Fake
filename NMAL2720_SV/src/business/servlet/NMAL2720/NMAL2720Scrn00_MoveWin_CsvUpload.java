/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

//import static business.servlet.NMAL2720.constant.NMAL2720Constant.UPLOAD_CSV_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

//import business.servlet.NMAL2720.common.NMAL2720CommonLogic;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720Scrn00_MoveWin_CsvUpload
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/07/04   Fujitsu         M.Nakamura      Update          QC#11365
 *</pre>
 */
public class NMAL2720Scrn00_MoveWin_CsvUpload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

//        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
//        NMAL2720CommonLogic.addCheckItemScreen(scrnMsg, false);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        /////////////////////////////////////////////////////
        // Specification change.
        // The button on the "Upload" has been deleted.
        // This file is not used.
        /////////////////////////////////////////////////////

//        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
//        Object[] params = new Object[1];
//        ZYPTableUtil.clear(scrnMsg.P);
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, UPLOAD_CSV_ID);
//
//        params[0] = scrnMsg.P.no(0).xxPopPrm;
//        setArgForSubScreen(params);

    }
}
