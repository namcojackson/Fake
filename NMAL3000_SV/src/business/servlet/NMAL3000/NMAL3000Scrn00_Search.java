/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import static business.servlet.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.NMAM0185E;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL3000.common.NMAL3000CommonLogic;

import business.blap.NMAL3000.NMAL3000CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL3000Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169
 *</pre>
 */
public class NMAL3000Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {

            if (ZYPDateUtil.compare(scrnMsg.effThruDt.getValue()
                , scrnMsg.effFromDt.getValue()) < 0) {
                scrnMsg.effFromDt.setErrorInfo(1, NMAM0185E);
                scrnMsg.effThruDt.setErrorInfo(1, NMAM0185E);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.dsAcctCustNum);
        scrnMsg.addCheckItem(scrnMsg.mktMdlCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctDlrCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.slsAuthFlg_SA);
        scrnMsg.addCheckItem(scrnMsg.slsAuthFlg_SE);
        scrnMsg.addCheckItem(scrnMsg.dsAcctCustNum_CO);
        scrnMsg.addCheckItem(scrnMsg.mktMdlCd_CO);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_CO);
        NMAL3000CommonLogic.checkMandatory(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg = new NMAL3000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg  = (NMAL3000CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NMAL3000CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        NMAL3000CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A1", scrnMsg.A, 1);
        tblColor.setAlternateRowsBG("A2", scrnMsg.A, 1);
    }
}
