/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520;

import static business.servlet.NWAL1520.constant.NWAL1520Constant.BIZ_ID;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1520.NWAL1520CMsg;
import business.servlet.NWAL1520.common.NWAL1520CommonLogic;
import static business.servlet.NWAL1520.constant.NWAL1520Constant.NWAM0298E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1520Scrn00_Release_Hold
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/30   Fujitsu         A.Suda          Create          N/A
 * 2017/08/21   Fujitsu         H.Sugawara      Update          QC#19952
 * 2017/09/19   Fujitsu         H.Sugawara      Update          QC#19918
 *</pre>
 */
public class NWAL1520Scrn00_Release_Hold extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.hldRelRsnDescTxt_RH);
        scrnMsg.addCheckItem(scrnMsg.relMemoTxt_RH);

        // Add Start 2017/08/21 QC#19952
        if (!hasValue(scrnMsg.hldRelRsnDescTxt_RH)) {
            scrnMsg.hldRelRsnDescTxt_RH.setErrorInfo(1, NWAM0298E, new String[] {"Released Reason" });
        }
        // Add End 2017/08/21 QC#19952

        if (!hasValue(scrnMsg.relMemoTxt_RH)) {
            scrnMsg.relMemoTxt_RH.setErrorInfo(1, NWAM0298E, new String[] {"Released Comment" });
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;

        NWAL1520CMsg bizMsg = new NWAL1520CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1520BMsg scrnMsg = (NWAL1520BMsg) bMsg;
        NWAL1520CMsg bizMsg = (NWAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NWAL1520CommonLogic.initCmnBtnProp(this);
        NWAL1520CommonLogic.setInputProtected(this, scrnMsg);
        NWAL1520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.L, "A_Right");
        NWAL1520CommonLogic.setInputProtectedforCheckBox(this, scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.hldRelRsnDescTxt_RH);
        // Add Start 2017/09/19 QC#19918
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.L.no(i).xxChkBox);
        }
        // Add End 2017/09/19 QC#19918

        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

    }
}
