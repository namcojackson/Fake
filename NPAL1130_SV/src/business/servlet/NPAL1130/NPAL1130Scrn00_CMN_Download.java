/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUSINESS_ID;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.EVENT_ID_SEARCH;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.FUNCTION_CD_SEARCH;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1130.NPAL1130CMsg;
import business.servlet.NPAL1130.common.NPAL1130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1130 Replenishment Plan Inquiry(Detail)
 * Function Name : Common Download
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/04/2016   CITS            Keiichi Masaki  Create          N/A
 * 11/07/2017   CITS            S.Katsuma       Update          SOL#014(QC#18401)
 *</pre>
 */
public class NPAL1130Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd)) {
            scrnMsg.mdseCd.setErrorInfo(1, "NMAM0288E");
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
            scrnMsg.rtlWhCd.setErrorInfo(1, "NMAM0288E");
        }

        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        NPAL1130CMsg bizMsg = new NPAL1130CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;
        NPAL1130CMsg bizMsg = (NPAL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        // execute file down load
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
        if (ZYPCommonFunc.hasValue(tempFilePath)) {
            executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        }
        // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

        NPAL1130CommonLogic.setScreenControl(EVENT_ID_SEARCH, this, scrnMsg);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }
}
