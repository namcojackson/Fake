/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0640;

import static business.servlet.NLCL0640.constant.NLCL0640Constant.BIZ_APP_ID;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLCL0640.constant.NLCL0640Constant.TEMPLATE_FILE_NAME;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0640.NLCL0640CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2018   CITS            Y.Iwasaki       Update          QC#10572
 *</pre>
 */
public class NLCL0640Scrn00_TemplateFileForUpload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;

        NLCL0640CMsg bizMsg = new NLCL0640CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        //return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0640BMsg scrnMsg = (NLCL0640BMsg) bMsg;
        NLCL0640CMsg bizMsg = (NLCL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String tempFilePath = scrnMsg.xxFileData_UP.getTempFilePath();
        executeDownload(tempFilePath, true, TEMPLATE_FILE_NAME);
        if ("E".equals(cMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
