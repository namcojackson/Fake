/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/28   CITS            R.Shimamoto     Create          QC#18669
 *</pre>
 */
public class NLAL1100Scrn00_OpenWin_NtfyGrp_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    	// no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "RTRN_TRK_NTFY_GRP");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "RTRN_TRK_NTFY_GRP_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "RTRN_TRK_NTFY_GRP_DESC_TXT");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "RTRN_TRK_NTFY_GRP_SORT_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Assigned to Group Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Assigned to Group Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Assigned to Group Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Assigned to Group Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Assigned to Group Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.rtrnTrkNtfyGrpCd_G);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[11];

        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, NLAL1100Constant.EVENT_NM_OPENWIN_NTFY_GRP_APPLY);
        setArgForSubScreen(params);
    }
}
