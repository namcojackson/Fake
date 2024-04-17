/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/06/18   CITS            J.Evangelista   Create          QC#58876
 * </pre>
 */
public class NPAL1080Scrn00_OpenWin_ShipDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg)  {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        int index = getButtonSelectNumber();

        Object[] params = new Object[6];
        params[0] = scrnMsg.destRtlWhCd_H1.getValue();
        params[1] = scrnMsg.A.no(index).prchReqNum_D1.getValue();
        params[2] = scrnMsg.A.no(index).prchReqLineNum_D1.getValue();
        params[3] = scrnMsg.A.no(index).prchReqLineSubNum_D1.getValue();
        params[4] = scrnMsg.A.no(index).trxRefNum_D1.getValue();
        params[5] = scrnMsg.A.no(index).vndSoNum_AC.getValue();
        setArgForSubScreen(params);
    }
}
