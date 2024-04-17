/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.UPLD_CSV_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7000.NMAL7000BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/14   CUSA            M.Ohno          Create          QC#57315
 *</pre>
 */
public class NMAL6710Scrn00_MoveWin_UploadDuns extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

     }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        Object[] params = new Object[1];
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm,UPLD_CSV_ID);
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        setArgForSubScreen(params);
    }
}
