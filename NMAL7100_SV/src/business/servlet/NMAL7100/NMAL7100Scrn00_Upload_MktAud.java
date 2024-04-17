/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.ZYEM0096E;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.ZZM9000E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/11/22   Fujitsu         R.Nakamura      Update          QC#16082
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100Scrn00_Upload_MktAud extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        if (scrnMsg.xxFileData_HC.isUploaded()) {

            // Mod Start 2016/11/22 QC#16082
//            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_HC, Arrays.asList(new String[] {"csv", "txt" }))) {
            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_HC, Arrays.asList(new String[] {"txt", "csv", "xlsx", "xls" }))) {
            // Mod End 2016/11/22 QC#16082
                scrnMsg.xxFileData_HC.setErrorInfo(1, ZYEM0096E);
            }
        } else {
            scrnMsg.xxFileData_HC.setErrorInfo(1, ZZM9000E, new String[] {"Upload File" });
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_HC);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        scrnMsg.xxFileData_HC.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_HC, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_HC));

        NMAL7100CMsg bizMsg = new NMAL7100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.X.getValidCount() > 0) {
            // Mod Start 2017/02/27 QC#16011
//            NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg);
            NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
            // Mod End 2017/02/27 QC#16011
        }

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            return;
        }
    }
}
