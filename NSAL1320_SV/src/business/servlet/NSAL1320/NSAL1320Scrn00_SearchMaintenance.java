/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_CMN_SAV;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.BTN_STS_ACTIVE;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.ZZM9001E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDItemAttribute;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;
import business.servlet.NSAL1320.constant.NSAL1320Constant.XX_PAGE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_SearchMaintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1320Scrn00_SearchMaintenance extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        String itemNm = "refCpoOrdNum";
        String dspNm = "Order Number";
        if (XX_PAGE.PAGE_IMPT.getCode().equals(scrnMsg.xxPageCd.getValue())) {
            itemNm = "ordSrcRefNum";
            dspNm = "Source Reference#";
        }
        EZDItemAttribute attr = scrnMsg.getAttr(itemNm);
        if (attr != null) {
            if (scrnMsg.xxScrItem50Txt.getValue().length() > attr.getDigit()) {
                scrnMsg.xxScrItem50Txt.setErrorInfo(1, ZZM9001E, new String[] {dspNm });
            }
        }
        scrnMsg.addCheckItem(scrnMsg.xxScrItem50Txt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        NSAL1320CMsg bizMsg = new NSAL1320CMsg();
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
        NSAL1320CommonLogic.setGuiStyle(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem50Txt);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NSAL1320CommonLogic.initCmnBtnProp(this, scrnMsg);
        NSAL1320CommonLogic.initBizItemProp(this, scrnMsg);
        NSAL1320CommonLogic.protectLineItemForEntry(this, scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        NSAL1320CommonLogic.setBtnProp(this, BTN_CMN_SAV, BTN_STS_ACTIVE);
    }
}
