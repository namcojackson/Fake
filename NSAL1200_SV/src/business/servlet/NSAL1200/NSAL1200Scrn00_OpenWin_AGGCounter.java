/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1200.common.NSAL1200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_IDX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193
 *</pre>
 */
public class NSAL1200Scrn00_OpenWin_AGGCounter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
//        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
//        int rowIndex = getButtonSelectNumber();
//        String mtrLbCd = scrnMsg.A.no(rowIndex).aggrMtrLbCd_A.getValue();
//        String mtrLbDescTxt = scrnMsg.A.no(rowIndex).mtrLbDescTxt_AG.getValue();
//        setArgForSubScreen(NSAL1200CommonLogic.createMtrLbListPopupParameter(scrnMsg, MTR_LB_TP.REGULAR_METER, MTR_IDX.AGGREGATE, mtrLbCd, mtrLbDescTxt));
        // END 2017/08/03 T.Kanasaka [QC#18193,DEL]
    }
}
