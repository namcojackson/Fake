/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.BIZ_ID;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_APLY_COORD_SEARCH;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_COORD_SEARCH_CD;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.EVENT_NM_OPENWIN_COORD_SEARCH_INFO;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3120.NLBL3120CMsg;
import business.servlet.NLBL3120.common.NLBL3120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120_NLBL3100 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return null;
        }

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        String eventNm = scrnMsg.xxMntEventNm.getValue();

        if (!EVENT_NM_OPENWIN_COORD_SEARCH_INFO.equals(eventNm)) {
            return null;
        }

        int selectIdx = getButtonSelectNumber();
        NLBL3120_ABMsg bMsgALine = scrnMsg.A.no(selectIdx);

        if (!ZYPCommonFunc.hasValue(bMsgALine.rtlWhCd_A1)) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(bMsgALine.schdCoordPsnCd_A1, scrnMsg.P.no(1).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(bMsgALine.xxPsnFirstMidLastNm_A1, scrnMsg.P.no(6).xxPopPrm);
        scrnMsg.xxNum_EV.setValue(selectIdx);

        NLBL3120CMsg bizMsg = new NLBL3120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        String eventNm = scrnMsg.xxMntEventNm.getValue();
        if (EVENT_NM_OPENWIN_COORD_SEARCH_CD.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdCoordPsnCd, scrnMsg.P.no(1).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mgrPsnCd, scrnMsg.P.no(2).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.supvPsnCd, scrnMsg.P.no(3).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdCoordPsnNm, scrnMsg.P.no(6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnFirstMidLastNm_MG, scrnMsg.P.no(7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnFirstMidLastNm_SV, scrnMsg.P.no(8).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.schdCoordPsnCd);

        } else if (EVENT_NM_OPENWIN_COORD_SEARCH_INFO.equals(eventNm)) {

            NLBL3120CommonLogic.addCheckItemSchd(scrnMsg);
            scrnMsg.putErrorScreen();

            int selectIdx = getButtonSelectNumber();
            NLBL3120_ABMsg bMsgALine = scrnMsg.A.no(selectIdx);
            scrnMsg.setFocusItem(bMsgALine.schdCoordPsnCd_A1);

        } else if (EVENT_NM_OPENWIN_APLY_COORD_SEARCH.equals(eventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdCoordPsnCd_BT, scrnMsg.P.no(1).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdCoordPsnNm_BT, scrnMsg.P.no(6).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.schdCoordPsnCd_BT);
        }

    }
}
