/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550;

import static business.servlet.NSAL0550.constant.NSAL0550Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0550.NSAL0550CMsg;
import business.servlet.NSAL0550.common.NSAL0550CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         Y.Takeno        Create          N/A
 * 2018/06/11   Fujitsu         M.Ohno          Update          QC#22381
 * 2018/09/27   Hitachi         T.Tomita        Update          QC#28530
 *</pre>
 */
public class NSAL0550Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.invTpCd_H3);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.bllgPerFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.bllgPerToDt_H1);
        // START 2018/06/11 M.Ohno [QC#22381, ADD]
        scrnMsg.addCheckItem(scrnMsg.svcInvNum_H1);
        scrnMsg.addCheckItem(scrnMsg.invDt_H1);
        scrnMsg.addCheckItem(scrnMsg.custCareTktNum_H1);
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsContrNum_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.svcInvNum_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.custCareTktNum_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgPerFromDt_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.bllgPerToDt_H1) //
                && !ZYPCommonFunc.hasValue(scrnMsg.invDt_H1)) {
            scrnMsg.dsContrNum_H1.setErrorInfo(1, NSAM0017E);
            scrnMsg.svcInvNum_H1.setErrorInfo(1, NSAM0017E);
            scrnMsg.custCareTktNum_H1.setErrorInfo(1, NSAM0017E);
            scrnMsg.bllgPerFromDt_H1.setErrorInfo(1, NSAM0017E);
            scrnMsg.bllgPerToDt_H1.setErrorInfo(1, NSAM0017E);
            scrnMsg.invDt_H1.setErrorInfo(1, NSAM0017E);
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H2) //
                && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_H2.getValue()) //
                && !ZYPCommonFunc.hasValue(scrnMsg.svcInvNum_H1)) {
            scrnMsg.svcInvNum_H1.setErrorInfo(1, NSAM0736E, new String[] {"Invoice#", "Related Invoice#" });
        }
        // END 2018/06/11 M.Ohno [QC#22381, ADD]
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;
        NSAL0550CMsg bizMsg = new NSAL0550CMsg();

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;
        NSAL0550CMsg bizMsg = (NSAL0550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add Start 2018/09/27 QC#28530
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // Add End 2018/09/27 QC#28530

        // START 2018/06/11 M.Ohno [QC#22381, ADD]
        NSAL0550CommonLogic.alternateTableRowColor(scrnMsg);
        // END 2018/06/11 M.Ohno [QC#22381, ADD]
    }
}
