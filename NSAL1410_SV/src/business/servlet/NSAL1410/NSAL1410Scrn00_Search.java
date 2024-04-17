/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410;

import static business.servlet.NSAL1410.common.NSAL1410CommonLogic.*;
import static business.servlet.NSAL1410.constant.NSAL1410Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1410.NSAL1410CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;
        addCheckItemHeader(scrnMsg);
        if (!hasValue(scrnMsg.dsContrNum_H) && !hasValue(scrnMsg.svcContrBrCd_H) && !hasValue(scrnMsg.svcContrBrDescTxt_H) && !hasValue(scrnMsg.psnCd_H) && !hasValue(scrnMsg.xxPsnNm_H) && !hasValue(scrnMsg.billToCustCd_H)
                && !hasValue(scrnMsg.locNm_H)) {

            scrnMsg.dsContrNum_H.setErrorInfo(1, NSAM0017E);
            scrnMsg.svcContrBrCd_H.setErrorInfo(1, NSAM0017E);
            scrnMsg.svcContrBrDescTxt_H.setErrorInfo(1, NSAM0017E);
            scrnMsg.psnCd_H.setErrorInfo(1, NSAM0017E);
            scrnMsg.xxPsnNm_H.setErrorInfo(1, NSAM0017E);
            scrnMsg.billToCustCd_H.setErrorInfo(1, NSAM0017E);
            scrnMsg.locNm_H.setErrorInfo(1, NSAM0017E);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;
        scrnMsg.xxModeCd.clear();

        NSAL1410CMsg bizMsg = new NSAL1410CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;
        NSAL1410CMsg bizMsg = (NSAL1410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        initialControlScreen(this, scrnMsg);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}