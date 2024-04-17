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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/23   Hitachi         Y.Takeno        Create          QC#23505
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 *</pre>
 */
public class NFBL2040Scrn00_Correction extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.poNum.clearErrorInfo();
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.putErrorScreen();
        // END QC#25902,QC#25190,QC#25141
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2020/02/17 [QC#53413, MOD]
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        //return null;
        // END   2020/02/17 [QC#53413, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
//        // START 2020/02/17 [QC#53413, MOD]
//        // START QC#25902,QC#25190,QC#25141
//        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
//        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
//        scrnMsg.xxBtnFlg_CR.setValue(ZYPConstant.FLG_ON_Y);
//        scrnMsg.xxChkBox_CR.setValue(ZYPConstant.FLG_ON_Y);
//
//        Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForPurchaseOrder(scrnMsg, this.getGlobalCompanyCode(), true);
//
//        setArgForSubScreen(params);
//        // END QC#25902,QC#25190,QC#25141

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
        if (ZYPCommonFunc.hasValue(scrnMsg.poNum_HT)) {
            scrnMsg.setFocusItem(scrnMsg.poNum_HT);
        }

        NFBL2040CommonLogic.setFocusRule(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END   2020/02/17 [QC#53413, MOD]
    }
}
