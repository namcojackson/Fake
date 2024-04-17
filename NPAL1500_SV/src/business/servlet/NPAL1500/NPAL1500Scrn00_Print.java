/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_PRINT;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1182E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1253E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1646E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 11/11/2019   Fujitsu         T.Ogura         Update          QC#54588
 *</pre>
 */
public class NPAL1500Scrn00_Print extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.trsmtMethTpCd)) {
            scrnMsg.trsmtMethTpCd.setErrorInfo(1, NPAM1182E, new String[] {"Transmit Method Type" });
            scrnMsg.addCheckItem(scrnMsg.trsmtMethTpCd);
        }

        // START 2019/11/11 T.Ogura [QC#54588,ADD]
        if (TRSMT_METH_TP.EMAIL_PDF.equals(scrnMsg.trsmtMethTpCd.getValue())) {
            StringBuilder workEmlAddr = new StringBuilder();
            for (String emlAddr : scrnMsg.sendPoEmlAddr.getValue().split(",")) {
                if (!ZYPCommonFunc.hasValue(emlAddr)) {
                    continue;
                }
                if (NMXC106001CommonCheckUtil.checkEmailFormat(emlAddr)) {
                    if (workEmlAddr.length() != 0) {
                        workEmlAddr.append(",");
                    }
                    workEmlAddr.append(emlAddr);
                } else {
                    // Email format is incorrect.
                    scrnMsg.sendPoEmlAddr.setErrorInfo(1, NPAM1646E, new String[] {emlAddr });
                    scrnMsg.addCheckItem(scrnMsg.sendPoEmlAddr);
                    break;
                }
            }
            if (!scrnMsg.sendPoEmlAddr.isError()) {
                if (workEmlAddr.length() == 0) {
                    // Email Address is not entered.
                    scrnMsg.sendPoEmlAddr.setErrorInfo(1, NPAM1253E);
                    scrnMsg.addCheckItem(scrnMsg.sendPoEmlAddr);
                } else {
                    scrnMsg.sendPoEmlAddr.setValue(workEmlAddr.toString());
                }
            }
        }
        // END   2019/11/11 T.Ogura [QC#54588,ADD]

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            NPAL1500CommonLogic.moveErrorTab(scrnMsg);
            NPAL1500CommonLogic.addCheckItem(scrnMsg);
        } else {
            if (TRSMT_METH_TP.PDF_DOWNLOAD.equals(bizMsg.trsmtMethTpCd.getValue())) {
                bizMsg = (NPAL1500CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_ID, FUNCTION_CD_PRINT);

                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                if (!"E".equals(bizMsg.getMessageKind())) {
                    // execute file download
                    String tempFilePath = bizMsg.xxFileData.getTempFilePath();
                    executeDownloadPdf(tempFilePath, true);
                }
            }
        }

        NPAL1500CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.poNum);
    }
}
