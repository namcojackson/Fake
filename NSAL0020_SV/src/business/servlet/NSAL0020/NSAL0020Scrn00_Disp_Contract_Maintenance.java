/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *This class no use.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   Hitachi        T.Yonekura         Create          N/A
 * 2013/08/27   Hitachi        Y.Igarashi         Update          QC1851
 * 2015/10/19   Hitachi        Y.Tsuchimoto       Update          N/A
 *</pre>
 */
public class NSAL0020Scrn00_Disp_Contract_Maintenance extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // QC1851 Add Start
        NSAL0020BMsg scrnMsg = (NSAL0020BMsg) bMsg;

//        int index = scrnMsg.xxRadioBtn.getValueInt();
//        if (!hasValue(scrnMsg.A.no(index).dsContrPk_A0)) {
//            return;
//        }
//
//        Object[] params = new Object[ARGS_CONTRACT_MNT_COUNT];
//        params[0] = null;
//        params[0] = scrnMsg.A.no(index).dsContrPk_A0;
//        params[1] = scrnMsg.A.no(index).dsContrNum_A0;
//        params[2] = scrnMsg.A.no(index).dsContrSqNum_A0;

//        setArgForSubScreen(params);
        // QC1851 Add End
    }
}
