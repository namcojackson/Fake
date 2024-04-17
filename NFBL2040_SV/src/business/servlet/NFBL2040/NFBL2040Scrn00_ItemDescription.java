/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/11/29   Hitachi         Y.Tsuchimoto    Update          QC#16268
 * 2017/11/15   CITS            T.Wada          Update          QC#21727
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 *</pre>
 */
public class NFBL2040Scrn00_ItemDescription extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        if (scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.isClear()) {
            // START 2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
            //scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Merchandise Code" });
            scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item Code" });
            scrnMsg.addCheckItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1);
            scrnMsg.putErrorScreen();
            // END   2016/11/28 Y.Tsuchimoto [QC#16268,MOD]
        }
        // QC#21727
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1);
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.offHoldReleaseCheck(scrnMsg);
        // END   2020/03/16 [QC#55993, ADD]
        scrnMsg.putErrorScreen();        

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
    }
}
