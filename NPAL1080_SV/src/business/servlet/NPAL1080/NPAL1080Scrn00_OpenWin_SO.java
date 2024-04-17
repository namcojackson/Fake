/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 * 12/25/2017   CITS       K.Ogino              Update          QC#21908
 *</pre>
 */
public class NPAL1080Scrn00_OpenWin_SO extends S21CommonHandler {

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
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        // QC#21908
        if (PRCH_REQ_LINE_TP.INSOURCED_PO.equals(scrnMsg.A.no(selectIdx).prchReqLineTpCd_SE.getValue())) {
            Object[] params = new Object[4];
            setResult("PO");
            params[0] = scrnMsg.A.no(selectIdx).poOrdNum_AC;
            params[1] = PO_ORD_SRC.INSOURCING;
            params[2] = scrnMsg.A.no(selectIdx).prchReqNum_D1;
            params[3] = scrnMsg.A.no(selectIdx).vndSoNum_AC;
            setArgForSubScreen(params);
            openMultiModeScreen();
        } else {
            Object[] params = new Object[6];
            setResult("SO");
            params[1] = scrnMsg.A.no(selectIdx).vndSoNum_AC;
            setArgForSubScreen(params);
        }
    }
}
