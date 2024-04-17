/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import static business.servlet.NPAL1130.constant.NPAL1130Constant.*;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/01   CITS            K.Masaki        Update          CSA Project
 *</pre>
 */
public class NPAL1130Scrn00_OpenWinLocation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        scrnMsg.P.no(0).xxPopPrm.clear();
        scrnMsg.P.no(1).xxPopPrm.clear();
        scrnMsg.P.no(2).xxPopPrm.clear();
        scrnMsg.P.no(3).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(4).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(5).xxPopPrm.clear();
        scrnMsg.P.no(6).xxPopPrm.setValue(scrnMsg.rtlWhCd.getValue());
        scrnMsg.P.no(7).xxPopPrm.clear();
        scrnMsg.P.no(8).xxPopPrm.setValue(scrnMsg.rtlSwhCd.getValue());
        scrnMsg.P.no(9).xxPopPrm.clear();
        scrnMsg.P.no(10).xxPopPrm.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(11).xxPopPrm.clear();

        Object[] param = new Object[12];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;
        param[11] = scrnMsg.P.no(11).xxPopPrm;

        setArgForSubScreen(param);
    }
}
