/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import static business.servlet.NLCL0280.constant.NLCL0280Constant.BUSINESS_ID;
import static business.servlet.NLCL0280.constant.NLCL0280Constant.FUNC_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0280.NLCL0280CMsg;

import business.servlet.NLCL0280.common.NLCL0280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Inventory Transaction Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // Error check method
        NLCL0280CommonLogic.chkSearchItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        NLCL0280CMsg bizMsg = new NLCL0280CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;
        NLCL0280CMsg bizMsg = (NLCL0280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0280CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);

        if (!"E".equals(bizMsg.getMessageKind())) {

            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));

        } else {

            // Error
            NLCL0280CommonLogic.addCheckAllHeaderItem(scrnMsg);
            scrnMsg.putErrorScreen();
        }
    }
}