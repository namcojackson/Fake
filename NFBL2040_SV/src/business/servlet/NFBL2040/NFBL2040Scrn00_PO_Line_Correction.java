/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/21   CITS            K.Ogino         Create          QC#25141
 *</pre>
 */
public class NFBL2040Scrn00_PO_Line_Correction extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        int addRecCnt = 0;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                if (XX_LINE_TP_CD_VALID.equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                    addRecCnt++;
                }
            }
        }
        if (addRecCnt == 0) {
            scrnMsg.setMessageInfo(NFAM0075E, null);
            throw new EZDFieldErrorException();
        }
        addRecCnt = addRecCnt * 2;
        if (scrnMsg.A.getValidCount() + addRecCnt > scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NFAM0072E, new String[] {"Record", Integer.toString(scrnMsg.A.length()) });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }
        scrnMsg.putErrorScreen();

        /** Initialize button control */
        NFBL2040CommonLogic.initControl(this, scrnMsg);

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);

        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);

        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.poNum_HT);

        NFBL2040CommonLogic.setFocusRule(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
    }
}
