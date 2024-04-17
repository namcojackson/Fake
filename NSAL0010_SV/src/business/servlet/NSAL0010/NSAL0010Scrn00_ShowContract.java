/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2013/08/27   CUSA            Hitachi         Update          QC1850
 *</pre>
 */
public class NSAL0010Scrn00_ShowContract extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        // START 2013/08/27 T.Aoyagi [QC1850,ADD]
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        setValue(scrnMsg.dsContrPk_P, scrnMsg.dsContrPk);
//        setValue(scrnMsg.xxPopPrm_01, scrnMsg.xxViewNm_C1);
//        setValue(scrnMsg.xxPopPrm_02, scrnMsg.xxViewNm_C2);
//
//        Serializable[] params = new Serializable[3];
//        params[0] = scrnMsg.dsContrPk_P;
//        params[1] = scrnMsg.xxPopPrm_01;
//        params[2] = scrnMsg.xxPopPrm_02;
//        setArgForSubScreen(params);
//        // END 2013/08/27 T.Aoyagi [QC1850,ADD]
    }
}
