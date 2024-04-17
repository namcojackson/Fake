/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1320Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = new NSAL1320CMsg();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem50Txt, scrnMsg.xxScrItem50Txt_P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.refCpoOrdNum, scrnMsg.xxScrItem50Txt_P);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2018/05/07 QC#22482 Add Start
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NSAL1320CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Add End
        NSAL1320CommonLogic.setGuiStyle(scrnMsg);

        NSAL1320CommonLogic.initCmnBtnProp(this, scrnMsg);
        if (bizMsg.getMessageKind().equals(MESSAGE_KIND_ERROR)) {
            NSAL1320CommonLogic.protectLineItem(this, scrnMsg.A.no(0));
        } else {
            NSAL1320CommonLogic.initBizItemProp(this, scrnMsg);
        }
    }
}
