/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.CSV_FILE_NAME;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_ADDLINE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TMPL_FILE_EXTENSION;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/16   Hitachi         T.Kuroda        Create          N/A
 *</pre>
 */
public class NPAL1080Scrn00_OnClick_TemplateDownload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg  = (NPAL1080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1080CommonLogic.commonInit(scrnMsg);
        NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_ADDLINE, funcList);

        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();

        executeDownload(tempFilePath, true, ZYPCommonFunc.concatString(CSV_FILE_NAME, "", TMPL_FILE_EXTENSION));
        if ("E".equals(cMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

    }
}
