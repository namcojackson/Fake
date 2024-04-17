/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0010.NFDL0010CMsg;
import business.servlet.NFDL0010.common.NFDL0010CommonLogic;
import business.servlet.NFDL0010.constant.NFDL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/16   CITS            S.Katsuma       Create          QC#24793
 *</pre>
 */
public class NFDL0010Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.cltDispTpCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.cltPtfoNm_H) // 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.cltPsnNm_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H2) && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H)) {
            scrnMsg.setMessageInfo(NFDL0010Constant.NFDM0047E);
            throw new EZDFieldErrorException();
        }
        NFDL0010CommonLogic.checkInput_NFDL0010Scrn00_Search(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        NFDL0010CMsg bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;
        NFDL0010CMsg bizMsg  = (NFDL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // execute file download
        // START 2018/11/05 J.Kim [QC#28937, MOD]
        if (scrnMsg.A.getValidCount() != 0) {
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            if (ZYPCommonFunc.hasValue(tempFilePath)) {
                executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
            }
        }
        // END 2018/11/05 J.Kim [QC#28937, MOD]
    }
}
