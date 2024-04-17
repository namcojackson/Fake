/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_Upload_CustAudc
 * This Event is deleted by QC#8505.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/05/27   Fujitsu         W.Honda         Update          QC#8505
 *</pre>
 */
public class NMAL7010Scrn00_Upload_CustAudc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC#8505 2016/05/27 Del start
//        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
//
//        if (scrnMsg.xxFileData_HC.isUploaded()) {
//
//            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_HC, Arrays.asList(new String[] {"csv", "txt" }))) {
//
//                scrnMsg.xxFileData_HC.setErrorInfo(1, ZYEM0096E);
//            }
//
//        } else {
//
//            scrnMsg.xxFileData_HC.setErrorInfo(1, ZZM9000E, new String[] {"Upload File" });
//        }
//
//        scrnMsg.addCheckItem(scrnMsg.xxFileData_HC);
//        scrnMsg.putErrorScreen();
        // QC#8505 2016/05/27 Del end
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC#8505 2016/05/27 Mod start
//        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
//
//        scrnMsg.xxFileData_HC.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_HC, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_HC));
//
//        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
//        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
        // QC#8505 2016/05/27 Mod end
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // QC#8505 2016/05/27 Del start
//        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
//        NMAL7010CMsg bizMsg  = (NMAL7010CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        if (scrnMsg.X.getValidCount() > 0) {
//            NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg);
//        }
//
//        String msgCode = scrnMsg.getMessageCode();
//        if (msgCode.endsWith("E")) {
//            throw new EZDFieldErrorException();
//        }
        // QC#8505 2016/05/27 Del end

    }
}
