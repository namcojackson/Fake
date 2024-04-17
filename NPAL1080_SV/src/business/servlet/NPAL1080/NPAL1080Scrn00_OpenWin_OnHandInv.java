/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.NMAM8098E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM0049E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1649E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
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
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 *</pre>
 */
public class NPAL1080Scrn00_OpenWin_OnHandInv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int chkCount = 0;
        int cancelCount = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                chkCount++;
                if (PRCH_REQ_LINE_STS.CANCELLED.equals(scrnMsg.A.no(i).prchReqLineStsCd_D1.getValue())) {
                    ++cancelCount;
                }
            }
        }

        if (chkCount == 0) {
            scrnMsg.setMessageInfo(NPAM0049E);
            throw new EZDFieldErrorException();
        }
        if (1 < chkCount) {
            scrnMsg.setMessageInfo(NMAM8098E);
            throw new EZDFieldErrorException();
        }
        if (cancelCount > 0) {
            scrnMsg.setMessageInfo(NPAM1649E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        int selectIdx = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                selectIdx = i;
                break;
            }
        }
        Object[] params = new Object[3];
        params[0] = scrnMsg.A.no(selectIdx).mdseCd_D1;
        params[1] = scrnMsg.destRtlWhCd_H1;
        params[2] = scrnMsg.destRtlSwhCd_H1;
        setArgForSubScreen(params);

        // Multi Screen Open
        openMultiModeScreen();
    }
}
