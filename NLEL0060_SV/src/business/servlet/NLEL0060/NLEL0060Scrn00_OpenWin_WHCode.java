/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_WH_CODE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_OpenWin_WHCode
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_WHCode extends S21CommonHandler {

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

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        List<String> bizAppKeyId = null;

        String locRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(getGlobalCompanyCode(), BIZ_ID, bizAppKeyId);
        scrnMsg.P.no(0).clear();
        scrnMsg.P.no(1).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, locRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_ON_Y);
        scrnMsg.P.no(5).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.rtnWhCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.rtlWhNm_H1);
        scrnMsg.P.no(8).clear();
        scrnMsg.P.no(9).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        scrnMsg.P.no(11).clear();

        Object[] params = new Object[12];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, OPENWIN_WH_CODE);
        setArgForSubScreen(params);
    }
}
