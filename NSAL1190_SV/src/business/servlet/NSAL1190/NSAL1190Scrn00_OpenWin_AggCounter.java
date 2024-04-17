/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1190;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1190.common.NSAL1190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_IDX;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counters Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Hitachi         O.Okuma         Create          N/A
 * 2016/08/23   Hitachi         T.Tomita        Update          QC#8878
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193
 *</pre>
 */
public class NSAL1190Scrn00_OpenWin_AggCounter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/08/23 T.Tomita [QC#8878, ADD]
        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(selectIdx).mtrLbTpCd_A);
        scrnMsg.putErrorScreen();
        // END 2016/08/23 T.Tomita [QC#8878, ADD]
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//        NSAL1190BMsg scrnMsg = (NSAL1190BMsg) bMsg;
//        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
//        NSAL1190CommonLogic.clearPopupParameter(scrnMsg);
//        Object[] params = NSAL1190CommonLogic.createPopupParameter(scrnMsg, MTR_IDX.AGGREGATE, getButtonSelectNumber());
//        setArgForSubScreen(params);
        // END 2017/08/03 T.Kanasaka [QC#18193,DEL]
    }
}
