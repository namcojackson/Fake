/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_TechnicianDetail extends S21CommonHandler implements NSBL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[PARAM_5];

        params[PARAM_0] = scrnMsg.A.no(selectIdx).mdlNm_A;
        params[PARAM_1] = scrnMsg.A.no(selectIdx).mdseCd_A;
// START 2016/10/19 N.Arai [QC#13901, MOD]
//        params[PARAM_2] = scrnMsg.A.no(selectIdx).orgLayerNum_A;
        params[PARAM_2] = scrnMsg.A.no(selectIdx).svcMachMstrPk_A;
//        params[PARAM_3] = scrnMsg.A.no(selectIdx).orgCd_A;
        params[PARAM_3] = scrnMsg.A.no(selectIdx).serNum_A;
// END 2016/10/19 N.Arai [QC#13901, MOD]
        params[PARAM_4] = scrnMsg.A.no(selectIdx).techCd_A;
        setArgForSubScreen(params);
    }
}
