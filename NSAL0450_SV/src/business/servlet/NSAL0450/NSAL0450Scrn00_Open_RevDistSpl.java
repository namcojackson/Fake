/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0450;

import static business.servlet.NSAL0450.constant.NSAL0450Constant.NSAL1230_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/13   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSAL0450Scrn00_Open_RevDistSpl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0450BMsg scrnMsg = (NSAL0450BMsg) bMsg;

        int i = 0;
        int index = getButtonSelectNumber();
        Object[] param = new Object[NSAL1230_PRM_LENGTH];

        param[i++] = scrnMsg.A.no(index).dsContrPk;
        param[i++] = scrnMsg.A.no(index).dsContrDtlPk;
        param[i++] = scrnMsg.A.no(index).svcInvChrgTpCd;
        param[i++] = scrnMsg.A.no(index).coaCmpyCd;
        param[i++] = scrnMsg.A.no(index).coaExtnCd;
        param[i++] = scrnMsg.A.no(index).coaBrCd;
        param[i++] = scrnMsg.A.no(index).coaCcCd;
        param[i++] = scrnMsg.A.no(index).coaAcctCd;
        param[i++] = scrnMsg.A.no(index).coaProjCd;
        param[i++] = scrnMsg.A.no(index).coaProdCd;
        param[i++] = scrnMsg.A.no(index).coaAfflCd;
        param[i++] = scrnMsg.A.no(index).coaChCd;
        param[i++] =  scrnMsg.xxModeCd;
        setArgForSubScreen(param);
    }
}
