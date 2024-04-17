/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import static business.servlet.NMAL6700.constant.NMAL6700Constant.STATUS_CD_ACTIVE;

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
 * 2015/12/04   Fujitsu         N.Sugiura       Create          CSA
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_AcctSrch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        scrnMsg.P.clear();
        scrnMsg.dsAcctNm_P.clear();
        int selectIdx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(selectIdx);
        NMAL6700_CBMsg gBMsg = scrnMsg.C.no(selectIdx);

        List<EZDBStringItem> params = new ArrayList<EZDBStringItem>();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, gBMsg.dsAcctNum_C1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(26).xxPopPrm, ZYPConstant.FLG_OFF_N); //inactive
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(29).xxPopPrm, ZYPConstant.FLG_ON_Y); //Category: Location Status
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(30).xxPopPrm, ZYPConstant.FLG_ON_Y); //Category: Bill To/Ship To Status
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(33).xxPopPrm, ZYPConstant.FLG_ON_Y); //Category: Active

        params.add(scrnMsg.P.no(0).xxPopPrm);
        params.add(scrnMsg.dsAcctNm_P);
        params.add(scrnMsg.P.no(2).xxPopPrm);
        params.add(scrnMsg.P.no(3).xxPopPrm);
        params.add(scrnMsg.P.no(4).xxPopPrm);
        params.add(scrnMsg.P.no(5).xxPopPrm);
        params.add(scrnMsg.P.no(6).xxPopPrm);
        params.add(scrnMsg.P.no(7).xxPopPrm);
        params.add(scrnMsg.P.no(8).xxPopPrm);
        params.add(scrnMsg.P.no(9).xxPopPrm);
        params.add(scrnMsg.P.no(10).xxPopPrm);
        params.add(scrnMsg.P.no(11).xxPopPrm);
        params.add(scrnMsg.P.no(12).xxPopPrm);
        params.add(scrnMsg.P.no(13).xxPopPrm);
        params.add(scrnMsg.P.no(14).xxPopPrm);
        params.add(scrnMsg.P.no(15).xxPopPrm);
        params.add(scrnMsg.P.no(16).xxPopPrm);
        params.add(scrnMsg.P.no(17).xxPopPrm);
        params.add(scrnMsg.P.no(18).xxPopPrm);
        params.add(scrnMsg.P.no(19).xxPopPrm);
        params.add(scrnMsg.P.no(20).xxPopPrm);
        params.add(scrnMsg.P.no(21).xxPopPrm);
        params.add(scrnMsg.P.no(22).xxPopPrm);
        params.add(scrnMsg.P.no(23).xxPopPrm);
        params.add(scrnMsg.P.no(24).xxPopPrm);
        params.add(scrnMsg.P.no(25).xxPopPrm);
        params.add(scrnMsg.P.no(26).xxPopPrm);
        params.add(scrnMsg.P.no(27).xxPopPrm);
        params.add(scrnMsg.P.no(28).xxPopPrm);
        params.add(scrnMsg.P.no(29).xxPopPrm);
        params.add(scrnMsg.P.no(30).xxPopPrm);
        params.add(scrnMsg.P.no(31).xxPopPrm);
        params.add(scrnMsg.P.no(32).xxPopPrm);
        params.add(scrnMsg.P.no(33).xxPopPrm);
        params.add(scrnMsg.P.no(34).xxPopPrm);
        setArgForSubScreen(params.toArray(new EZDBStringItem[0]));

    }
}
