/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BIZ_ID;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1600.NWAL1600CMsg;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1600Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/14   Fujitsu         C.Yokoi         Create          N/A
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 *</pre>
 */
public class NWAL1600Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, scrnMsg.cpoOrdNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, scrnMsg.dsOrdPosnNum_BK);
        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColValTxt, scrnMsg.xxComnScrColValTxt_BK);
        // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        NWAL1600CommonLogic.copyParamToScrn(scrnMsg);

        NWAL1600CMsg bizMsg = new NWAL1600CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDealSlsPct.setValue(NWAL1600CommonLogic.calcTotalPercent(scrnMsg));
        scrnMsg.xxDealSlsPct.setAppFracDigit(2);

        NWAL1600CommonLogic.setRowsBGWithClear(scrnMsg);
        NWAL1600CommonLogic.protectInputForInit(this, scrnMsg);
        NWAL1600CommonLogic.protectInput(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return;
        }
    }
}
