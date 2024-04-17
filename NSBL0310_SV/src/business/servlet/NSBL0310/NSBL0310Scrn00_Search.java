/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0310;

import static business.servlet.NSBL0310.constant.NSBL0310Constant.BUSINESS_ID;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.FUNC_CD_20;
import static business.servlet.NSBL0310.constant.NSBL0310Constant.NSBM0005I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0310.NSBL0310CMsg;
import business.servlet.NSBL0310.common.NSBL0310CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 *</pre>
 */
public class NSBL0310Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.orgCd_HT);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;
        NSBL0310CMsg bizMsg = new NSBL0310CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0310BMsg scrnMsg = (NSBL0310BMsg) bMsg;
        NSBL0310CMsg bizMsg = (NSBL0310CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0310CommonLogic.setProtected(scrnMsg);
        NSBL0310CommonLogic.setTableBGColor(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.orgCd_HT);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NSBM0005I);
        }
    }
}
