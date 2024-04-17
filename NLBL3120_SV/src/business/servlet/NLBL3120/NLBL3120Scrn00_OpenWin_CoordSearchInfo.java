/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_COORD_SEARCH_INFO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         S.Yoshida       Create          N/A
 * 2017/06/28   CITS            T.Kikuhara      Create          QC#18993
 *</pre>
 */
public class NLBL3120Scrn00_OpenWin_CoordSearchInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        NLBL3120_ABMsg bMsgALine = scrnMsg.A.no(selectIdx);

        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, bMsgALine.rtlWhCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, bMsgALine.schdCoordPsnCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.mgrPsnCd_LG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.supvPsnCd_LG);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, bMsgALine.carrCd_A1);
        //QC#18993 MOD START
        //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, bMsgALine.fromLocStCd_A1);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        //QC#18993 MOD END
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[10];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, EVENT_NM_OPENWIN_COORD_SEARCH_INFO);
        setArgForSubScreen(params);
    }
}
