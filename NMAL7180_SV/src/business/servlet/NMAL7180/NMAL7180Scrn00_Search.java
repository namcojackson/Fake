/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7180;

import static business.servlet.NMAL7180.constant.NMAL7180Constant.BIZ_ID;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.NMAM0185E;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.NMAM0192E;
import static business.servlet.NMAL7180.constant.NMAL7180Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7180.NMAL7180CMsg;
import business.servlet.NMAL7180.common.NMAL7180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7180Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#7512
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 *</pre>
 */
public class NMAL7180Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;

        NMAL7180CommonLogic.addCheckHeaderItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpDescTxt)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpTpCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtTpCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrgtAttrbCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpFromTxt)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpThruTxt)
                && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.effThruDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpTrxTpCd)) {
            scrnMsg.prcGrpNm.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpDescTxt.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpTpCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpTrgtTpCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpTrgtAttrbCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpFromTxt.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpThruTxt.setErrorInfo(1, NMAM0192E);
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.effThruDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcGrpTrxTpCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
            scrnMsg.addCheckItem(scrnMsg.prcGrpDescTxt);
            scrnMsg.addCheckItem(scrnMsg.prcGrpTpCd);
            scrnMsg.addCheckItem(scrnMsg.prcGrpTrgtTpCd);
            scrnMsg.addCheckItem(scrnMsg.prcGrpTrgtAttrbCd);
            scrnMsg.addCheckItem(scrnMsg.prcGrpFromTxt);
            scrnMsg.addCheckItem(scrnMsg.prcGrpThruTxt);
            scrnMsg.addCheckItem(scrnMsg.effFromDt);
            scrnMsg.addCheckItem(scrnMsg.effThruDt);
            scrnMsg.addCheckItem(scrnMsg.prcGrpTrxTpCd);
            scrnMsg.putErrorScreen();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)
                && ZYPDateUtil.compare(scrnMsg.effFromDt.getValue(), scrnMsg.effThruDt.getValue()) > 0) {
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0185E);
            scrnMsg.effThruDt.setErrorInfo(1, NMAM0185E);
            scrnMsg.addCheckItem(scrnMsg.effFromDt);
            scrnMsg.addCheckItem(scrnMsg.effThruDt);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;

        NMAL7180CMsg bizMsg = new NMAL7180CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;
        NMAL7180CMsg bizMsg  = (NMAL7180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // QC#7512 2016/04/26 Mod start
        NMAL7180CommonLogic.controlScreen(this, scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7180CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

//        NMAL7180CommonLogic.controlScreen(this, scrnMsg);
        // QC#7512 2016/04/26 Mod end

        scrnMsg.setFocusItem(scrnMsg.prcGrpNm);
        scrnMsg.xxRadioBtn.setValue(0);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        scrnMsg.xxSortTblNm.clear();
        scrnMsg.xxSortItemNm.clear();
        scrnMsg.xxSortOrdByTxt.clear();
    }
}
