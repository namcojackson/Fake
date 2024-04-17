/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7200;

import static business.servlet.NMAL7200.constant.NMAL7200Constant.BIZ_ID;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.NMAM0192E;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.MESSAGE_KIND_ERROR;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7200.NMAL7200CMsg;
import business.servlet.NMAL7200.common.NMAL7200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7200Scrn00_Search_PrcGrpUge
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Fujitsu         M.Suzuki        Create          N/A
 * 2016/09/05   Fujitsu         R.Nakamura      Update          QC#8222
 *</pre>
 */
public class NMAL7200Scrn00_Search_PrcGrpUge extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxScrItem29Txt) && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpNm) && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpDescTxt)) {
            scrnMsg.setMessageInfo(NMAM0192E);
        }
        scrnMsg.addCheckItem(scrnMsg.prcGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem29Txt);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;
        NMAL7200CMsg bizMsg = new NMAL7200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;
        NMAL7200CMsg bizMsg  = (NMAL7200CMsg) cMsg;
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return;
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add Start 2016/09/05 QC#8222
        NMAL7200CommonLogic.initDownloadBtnProp(this, scrnMsg);
        // Add End 2016/09/05 QC#8222
        scrnMsg.setFocusItem(scrnMsg.xxScrItem29Txt);

    }
}
