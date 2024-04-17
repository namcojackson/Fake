/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0060;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0060.NFDL0060CMsg;
import business.servlet.NFDL0060.common.NFDL0060CommonLogic;
import business.servlet.NFDL0060.constant.NFDL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2018/06/07   Hitachi         Y.Takeno        Update          QC#25781
 *</pre>
 */
public class NFDL0060Scrn00_Search extends S21CommonHandler implements NFDL0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;

        // START 2018/06/07 [QC#25781,ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.cltDispTpCd_H3)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H2)
                && !ZYPCommonFunc.hasValue(scrnMsg.cltPsnNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.cltPtfoNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxFromDt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxToDt_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.cltItemTpCd_H3)) {
            scrnMsg.setMessageInfo("NFDM0047E");
            throw new EZDFieldErrorException();
        }
        // END   2018/06/07 [QC#25781,ADD]

        NFDL0060CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;

        NFDL0060CMsg bizMsg = new NFDL0060CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(SEARCH_FUNCTION);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        NFDL0060CMsg bizMsg = (NFDL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0060CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();

        List<String> functionList = NFDL0060CommonLogic.getFunctionCodeList(getUserProfileService());
        NFDL0060CommonLogic.activateButtons(this, functionList);
        NFDL0060CommonLogic.controlScreenFields(scrnMsg);

        NFDL0060CommonLogic.alternateTableRowColors(scrnMsg);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }

}
