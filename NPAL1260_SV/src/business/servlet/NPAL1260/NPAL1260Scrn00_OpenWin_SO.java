/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/25/2017   CITS       K.Ogino              Create          QC#21908
 *</pre>
 */
public class NPAL1260Scrn00_OpenWin_SO extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        // QC#21908
        if (PRCH_REQ_LINE_TP.INSOURCED_PO.equals(scrnMsg.A.no(selectIdx).prchReqLineTpCd_D1.getValue())) {
            Object[] params = new Object[4];
            setResult("PO");
            params[0] = scrnMsg.A.no(selectIdx).poOrdNum_D1;
            params[1] = PO_ORD_SRC.INSOURCING;
            params[2] = scrnMsg.A.no(selectIdx).prchReqNum_D1;
            params[3] = scrnMsg.A.no(selectIdx).vndSoNum_D1;
            setArgForSubScreen(params);
            openMultiModeScreen();
        } else {
            Object[] params = new Object[6];
            setResult("SO");
            params[1] = scrnMsg.A.no(selectIdx).vndSoNum_D1;
            setArgForSubScreen(params);
        }
    }
}
