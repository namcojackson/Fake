/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

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
public class NPAL1260Scrn00_OpenWin_Tracking extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

        int index = getButtonSelectNumber();

        // QC#27601
        Object[] params = null;
        if (PRCH_REQ_LINE_TP.INSOURCED_PO.equals(scrnMsg.A.no(index).prchReqLineTpCd_D1.getValue())) {
            params = new Object[4];
            params[0] = scrnMsg.A.no(index).vndSoNum_D1.getValue();
            params[1] = ZYPConstant.FLG_OFF_N; // No edit.
            params[2] = null;
            params[3] = "2"; // Mode ASN
        } else {
            params = new Object[2];
            params[0] = scrnMsg.A.no(index).vndSoNum_D1.getValue();
            params[1] = ZYPConstant.FLG_OFF_N; // No edit.
        }
        setArgForSubScreen(params);
    }
}
