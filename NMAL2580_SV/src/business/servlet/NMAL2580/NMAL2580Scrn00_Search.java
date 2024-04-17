/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2580;

import static business.blap.NMAL2580.constant.NMAL2580Constant.NMAM0806E;
import static business.blap.NMAL2580.constant.NMAL2580Constant.VALUE_NAME_REQUEST_DATE;
import static business.servlet.NMAL2580.constant.NMAL2580Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2580.NMAL2580CMsg;
import business.servlet.NMAL2580.common.NMAL2580CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2016/08/03   Fujitsu         R.Nakamura      Update          QC#12174
 *</pre>
 */
public class NMAL2580Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.trtyUpdRqstHdrPk);
        scrnMsg.addCheckItem(scrnMsg.rqstUsrId);
        scrnMsg.addCheckItem(scrnMsg.fill103Txt);
        scrnMsg.addCheckItem(scrnMsg.rqstCratSysTpCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRsltTpCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRsltCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effToDt);

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt) //
                && ZYPCommonFunc.hasValue(scrnMsg.effToDt) //
                && scrnMsg.effFromDt.getValue().compareTo(scrnMsg.effToDt.getValue()) > 0) {
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0806E, new String[] {VALUE_NAME_REQUEST_DATE });
            scrnMsg.effToDt.setErrorInfo(1, NMAM0806E, new String[] {VALUE_NAME_REQUEST_DATE });
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;

        NMAL2580CMsg bizMsg = new NMAL2580CMsg();
        bizMsg.setBusinessID("NMAL2580");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;
        NMAL2580CMsg bizMsg = (NMAL2580CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add Start 2016/08/03 QC#12174
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // Add End 2016/08/03 QC#12174

        NMAL2580CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL2580CommonLogic.initialControlScreen(this, scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.putErrorScreen();
            return;
        }

    }
}
