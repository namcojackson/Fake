/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3140;

import static business.servlet.NLBL3140.constant.NLBL3140Constant.ASTERISK;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BIZ_ID;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_LINE_CANCEL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_SELECT_ALL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.BTN_UN_SELECT_ALL;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3140.NLBL3140CMsg;
import business.servlet.NLBL3140.common.NLBL3140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2023/07/06   Hitachi         G.Quan          Update          QC#61543
 *</pre>
 */
public class NLBL3140Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.hardAllocTpCd);
        // START 2023/07/06 G.Quan [QC#61543, ADD]
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        // End 2023/07/06 G.Quan [QC#61543, ADD]
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;
        NLBL3140CMsg bizMsg = new NLBL3140CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd) && ASTERISK.equals(scrnMsg.dsOrdCatgDescTxt.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, ASTERISK);
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;
        NLBL3140CMsg bizMsg = (NLBL3140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.A.getValidCount() == 0) {
            this.setButtonEnabled(BTN_SELECT_ALL, false);
            this.setButtonEnabled(BTN_UN_SELECT_ALL, false);
            this.setButtonEnabled(BTN_LINE_CANCEL, false);
        } else {
            NLBL3140CommonLogic.setProtected(this, scrnMsg);
            this.setButtonEnabled(BTN_SELECT_ALL, true);
            this.setButtonEnabled(BTN_UN_SELECT_ALL, true);
            this.setButtonEnabled(BTN_LINE_CANCEL, true);
        }

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
    }

}
