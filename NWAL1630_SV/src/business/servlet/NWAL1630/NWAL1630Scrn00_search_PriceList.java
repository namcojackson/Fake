/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630;

import static business.servlet.NWAL1630.constant.NWAL1630Constant.BIZ_ID;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.NMAM0207E;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1630.NWAL1630CMsg;
import business.servlet.NWAL1630.common.NWAL1630CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1630Scrn00_search_PriceList
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL1630Scrn00_search_PriceList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        NWAL1630CommonLogic.addCheckItem(scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum) && !ZYPCommonFunc.hasValue(scrnMsg.dlrRefNum)) {
            scrnMsg.setMessageInfo(NMAM0207E, new String[] {scrnMsg.csmpContrNum.getNameForMessage(), scrnMsg.dlrRefNum.getNameForMessage() });
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return null;
        }

        NWAL1630CMsg bizMsg = new NWAL1630CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        NWAL1630CMsg bizMsg = (NWAL1630CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setMessageInfo(NZZM0002I);
        scrnMsg.setFocusItem(scrnMsg.csmpPrcListCd);
    }
}
