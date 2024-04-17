/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;

import static business.servlet.NWAL1740.constant.NWAL1740Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1740.NWAL1740CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1740_NMAL6050
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("NWAL1740Scrn00_OpenWin_Line".equals(scrEventNm)) {

            int index = scrnMsg.xxCellIdx.getValueInt();
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(index).dsOrdLineCatgCd_B)) {
                NWAL1740CMsg bizMsg = new NWAL1740CMsg();
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;

        final String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("NWAL1740Scrn00_OpenWin_Line".equals(scrEventNm)) {
            NWAL1740CMsg bizMsg = (NWAL1740CMsg) cMsg;
            if (null != bizMsg) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }
        }

        int cellIdx = scrnMsg.xxCellIdx.getValueInt();
        if (cellIdx < 0) {
            return;
        }

        if ("NWAL1740Scrn00_OpenWin_Mdse".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.A.no(cellIdx).flPrcCalcInclFlg_A);
        } else if ("NWAL1740Scrn00_OpenWin_Line".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(cellIdx).dsOrdLineCatgCd_B)) {
                scrnMsg.setFocusItem(scrnMsg.B.no(cellIdx).actvFlg_B);
            } else {
                scrnMsg.setFocusItem(scrnMsg.B.no(cellIdx).xxBtnFlg_B);
            }
        }
    }
}
