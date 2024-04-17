/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2170;

import static business.servlet.NWAL2170.constant.NWAL2170Constant.BIZ_ID;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.BTN_STS_ACTIVE;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.ZZM9001E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDItemAttribute;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2170.NWAL2170CMsg;
import business.servlet.NWAL2170.common.NWAL2170CommonLogic;
import business.servlet.NWAL2170.constant.NWAL2170Constant.XX_PAGE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2170Scrn00_SearchMaintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2170Scrn00_SearchMaintenance extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

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

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

        NWAL2170CMsg bizMsg = new NWAL2170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        NWAL2170CMsg bizMsg = (NWAL2170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2170CommonLogic.setGuiStyle(scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem50Txt);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NWAL2170CommonLogic.initCmnBtnProp(this, scrnMsg);
        NWAL2170CommonLogic.initBizItemProp(this, scrnMsg);
        NWAL2170CommonLogic.protectLineItemForEntry(this, scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        NWAL2170CommonLogic.setBtnProp(this, BTN_CMN_SAV, BTN_STS_ACTIVE);
    }
}
