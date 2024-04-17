/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/05/2018   CITS            T.Tokutomi      Create          QC#21913
 * 2018/08/17   CITS            K.Ogino         Update          QC#27601
 *</pre>
 */
public class NPAL1080Scrn00_OpenWin_Tracking extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // nothing

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        int index = getButtonSelectNumber();

        // QC#27601
        Object[] params = null;
        if (PRCH_REQ_LINE_TP.INSOURCED_PO.equals(scrnMsg.A.no(index).prchReqLineTpCd_SE.getValue())) {
            params = new Object[4];
            params[0] = scrnMsg.A.no(index).vndSoNum_AC.getValue();
            params[1] = ZYPConstant.FLG_OFF_N; // No edit.
            params[2] = null;
            params[3] = "2"; // Mode ASN
        } else {
            params = new Object[2];
            params[0] = scrnMsg.A.no(index).vndSoNum_AC.getValue();
            params[1] = ZYPConstant.FLG_OFF_N; // No edit.
        }
        setArgForSubScreen(params);
    }
}
