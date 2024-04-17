/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.DISPLAY_NM_UPLOAD_FILE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_ADDLINE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.PRAM_UPLOAD_FILE_EXTENSION_CSV;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.ZZM9000E;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/16   Hitachi         T.Kuroda        Create          N/A
 *</pre>
 */
public class NPAL1080Scrn00_Upload extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData
                    , Arrays.asList(new String[] {PRAM_UPLOAD_FILE_EXTENSION_CSV}));
        } else {
            scrnMsg.xxFileData.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_UPLOAD_FILE });
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        if (scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData
                            , getUserProfileService().getContextUserInfo().getUserId())
                            , "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
        }

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

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem20Txt_AC);
        }
        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPLICATION_ID);
        NPAL1080CommonLogic.commonInit(scrnMsg);
        NPAL1080CommonLogic.control(this, scrnMsg, MODE_BTN_ADDLINE, funcList);

    }
}
