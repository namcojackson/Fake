/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2500.NMAL2500CMsg;
import business.servlet.NMAL2500.common.NMAL2500CommonLogic;
import business.servlet.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */
public class NMAL2500Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        NMAL2500CommonLogic.checkDate(scrnMsg); // ADD S21_NA QC#16481
        NMAL2500CommonLogic.checkInput(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        NMAL2500CMsg bizMsg = new NMAL2500CMsg();
        bizMsg.setBusinessID(NMAL2500Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2500Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
        NMAL2500CMsg bizMsg = (NMAL2500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2500CommonLogic.convertTreeInfo(scrnMsg, bizMsg.T);

        if (scrnMsg.T.getValidCount() > 0) {
            NMAL2500CommonLogic.enableLinkButton(this);
        } else {
            NMAL2500CommonLogic.disableLinkButton(this);
        }

        if (NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY.equals(scrnMsg.xxModeCd_P1.getValue())) {
            scrnMsg.xxDplyTab.setValue(NMAL2500Constant.SHOW_HIERARCHY);
        } else if (NMAL2500Constant.SEARCH_MODE_QUICK_RESOURCE_LOOK_UP.equals(scrnMsg.xxModeCd_P1.getValue())) {
            scrnMsg.xxDplyTab.setValue(NMAL2500Constant.QUICK_RESOURCE_LOOK_UP);
        }

    }
}
