/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1210;

import static business.servlet.NPAL1210.constant.NPAL1210Constant.BIZ_APP_ID;
import static business.servlet.NPAL1210.constant.NPAL1210Constant.FUNCTION_CD_SEARCH;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1210.NPAL1210CMsg;
import business.servlet.NPAL1210.common.NPAL1210CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * PO/Inventory Approval History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/20/2016   CITS            R Shimamoto     Create          N/A
 *</pre>
 */
public class NPAL1210_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1210BMsg scrnMsg = (NPAL1210BMsg) bMsg;
        NPAL1210CMsg bizMsg = new NPAL1210CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            EZDBStringItem param0 = (EZDBStringItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];

            ZYPEZDItemValueSetter.setValue(scrnMsg.apvlHistSrcTpCd, param0.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.trxRefNum, param1.getValue());

            ZYPTableUtil.clear(scrnMsg.A);

        }

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1210BMsg scrnMsg = (NPAL1210BMsg) bMsg;
        NPAL1210CMsg bizMsg = (NPAL1210CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1210CommonLogic.cntrlScrnItemDispInit(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NPAL1210CommonLogic.setNameForMessage((NPAL1210BMsg) arg0);
    }
}
