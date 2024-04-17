/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.NWCM0100E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0050.NWCL0050CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/03/07   Fujitsu         K.Ishizuka      Insert          QC#13856
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 *</pre>
 */
public class NWCL0050Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.invNum);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.conslBillNum);
        scrnMsg.addCheckItem(scrnMsg.billToDsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.billToDsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.billToLocNum);
        scrnMsg.addCheckItem(scrnMsg.xxSerNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.invAvgGrpNum);
        scrnMsg.addCheckItem(scrnMsg.xxUrnNum);
        scrnMsg.addCheckItem(scrnMsg.conslBillInvDt_FR);
        scrnMsg.addCheckItem(scrnMsg.conslBillInvDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_TO);
        scrnMsg.putErrorScreen();

        // checkstyle error ignore
        if (!ZYPCommonFunc.hasValue(scrnMsg.invNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.conslBillNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.billToDsAcctNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.billToDsAcctNm) 
                && !ZYPCommonFunc.hasValue(scrnMsg.billToLocNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxSerNumSrchTxt) 
                && !ZYPCommonFunc.hasValue(scrnMsg.dsContrNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.invAvgGrpNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxUrnNum) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invPrtBatTpCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invPrtBrCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invProcTpCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.ordClsCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.invSmryLineTpCd) 
                && !ZYPCommonFunc.hasValue(scrnMsg.conslBillInvDt_FR) 
                && !ZYPCommonFunc.hasValue(scrnMsg.conslBillInvDt_TO)
                // START 2018/05/28 Y.Matsui [QC#26342,ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.custIssPoNum)
                // END   2018/05/28 Y.Matsui [QC#26342,ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_FR) 
                && !ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_TO)) {

            scrnMsg.setMessageInfo(NWCM0100E);
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;

        NWCL0050CMsg bizMsg = new NWCL0050CMsg();
        bizMsg.setBusinessID("NWCL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0050BMsg scrnMsg = (NWCL0050BMsg) bMsg;
        NWCL0050CMsg bizMsg  = (NWCL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        
        String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
        String excelFileFullPath = ZYPExcelUtil.csvFileToExcel(tempFilePath);
        executeDownload(excelFileFullPath, true, ZYPCSVOutFile.getDialogFileName(excelFileFullPath));

    }
}
