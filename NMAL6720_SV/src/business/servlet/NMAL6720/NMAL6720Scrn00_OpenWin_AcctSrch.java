/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Create          QC#4349
 * 2016/03/14   SRAA            Y.Chen          Update          QC#4966
 * 2016/04/08   Fujitsu         C.Yoko          Update          QC#6633
 * 2016/04/13   SRAA            Y.Chen          Update          QC#6011
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_AcctSrch extends S21CommonHandler {

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

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        // QC#6011
        scrnMsg.xxScrEventNm_P.setValue(ctx.getEventName());

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx_AI, new BigDecimal(index));

        scrnMsg.P.clear();

        List<EZDBStringItem> params = new ArrayList<EZDBStringItem>();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.A.no(index).dsAcctNum_A1);
        // QC#4966
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(29).xxPopPrm_P, ZYPConstant.FLG_ON_Y); //Category: Location Status
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(30).xxPopPrm_P, ZYPConstant.FLG_ON_Y); //Category: Bill To/Ship To Status
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(33).xxPopPrm_P, ZYPConstant.FLG_OFF_N); // Category: Active

        params.add(scrnMsg.P.no(0).xxPopPrm_P);
        params.add(scrnMsg.dsAcctNm_P1); // 2016/04/08 CSA-QC#6633 Mod
        params.add(scrnMsg.P.no(2).xxPopPrm_P);
        params.add(scrnMsg.P.no(3).xxPopPrm_P);
        params.add(scrnMsg.P.no(4).xxPopPrm_P);
        params.add(scrnMsg.P.no(5).xxPopPrm_P);
        params.add(scrnMsg.P.no(6).xxPopPrm_P);
        params.add(scrnMsg.P.no(7).xxPopPrm_P);
        params.add(scrnMsg.P.no(8).xxPopPrm_P);
        params.add(scrnMsg.P.no(9).xxPopPrm_P);
        params.add(scrnMsg.P.no(10).xxPopPrm_P);
        params.add(scrnMsg.P.no(11).xxPopPrm_P);
        params.add(scrnMsg.P.no(12).xxPopPrm_P);
        params.add(scrnMsg.P.no(13).xxPopPrm_P);
        params.add(scrnMsg.P.no(14).xxPopPrm_P);
        // QC#4966
        params.add(scrnMsg.P.no(15).xxPopPrm_P);
        params.add(scrnMsg.P.no(16).xxPopPrm_P);
        params.add(scrnMsg.P.no(17).xxPopPrm_P);
        params.add(scrnMsg.P.no(18).xxPopPrm_P);
        params.add(scrnMsg.P.no(19).xxPopPrm_P);
        params.add(scrnMsg.P.no(20).xxPopPrm_P);
        params.add(scrnMsg.P.no(21).xxPopPrm_P);
        params.add(scrnMsg.P.no(22).xxPopPrm_P);
        params.add(scrnMsg.P.no(23).xxPopPrm_P);
        params.add(scrnMsg.P.no(24).xxPopPrm_P);
        params.add(scrnMsg.P.no(25).xxPopPrm_P);
        params.add(scrnMsg.P.no(26).xxPopPrm_P);
        params.add(scrnMsg.P.no(27).xxPopPrm_P);
        params.add(scrnMsg.P.no(28).xxPopPrm_P);
        params.add(scrnMsg.P.no(29).xxPopPrm_P);
        params.add(scrnMsg.P.no(30).xxPopPrm_P);
        params.add(scrnMsg.P.no(31).xxPopPrm_P);
        params.add(scrnMsg.P.no(32).xxPopPrm_P);
        params.add(scrnMsg.P.no(33).xxPopPrm_P);
        params.add(scrnMsg.P.no(34).xxPopPrm_P);

        setArgForSubScreen(params.toArray(new EZDBStringItem[0]));
    }
}
