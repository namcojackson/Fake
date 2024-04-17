/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BIZ_ID;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.INOUT_SFX;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NWAM0660E;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.MODE_REFERENCE;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1600.NWAL1600CMsg;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 *</pre>
 */
public class NWAL1600Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;

        if (S21StringUtil.isEquals(scrnMsg.xxModeCd.getValue(), MODE_REFERENCE)) {
            return;
        }

        NWAL1600CommonLogic.addCheckItems(scrnMsg);
        NWAL1600CommonLogic.checkMandantoryItem(scrnMsg);
        scrnMsg.putErrorScreen();

        scrnMsg.xxDealSlsPct.setValue(NWAL1600CommonLogic.calcTotalPercent(scrnMsg));
        if (BigDecimal.valueOf(100).compareTo(scrnMsg.xxDealSlsPct.getValue()) != 0
                && BigDecimal.ZERO.compareTo(scrnMsg.xxDealSlsPct.getValue()) != 0) {
            scrnMsg.setMessageInfo(NWAM0660E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;

        if (S21StringUtil.isEquals(scrnMsg.xxModeCd.getValue(), MODE_REFERENCE)) {
            return null;
        }

        NWAL1600CMsg bizMsg = new NWAL1600CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NWAL1600CommonLogic.addCheckSlsRepItems(scrnMsg);
            NWAL1600CommonLogic.addCheckNonQuoteItems(scrnMsg);
            scrnMsg.putErrorScreen();

            NWAL1600CommonLogic.copyScrnToParam(scrnMsg);

            Object[] arg = (Object[]) getArgForSubScreen();
            if (arg instanceof Object[]) {
                Object[] params = (Object[]) arg;

                EZDMsgArray result = (EZDMsgArray) params[4];
                if (result instanceof EZDMsgArray) {
                    EZDMsg.copy(scrnMsg.O, INOUT_SFX, result, scrnMsg.xxSfxKeyTxt.getValue());
                }

                // Set Writer Person Code
                EZDBStringItem writerPsnCd = NWAL1600CommonLogic.getWriterPsnNum(scrnMsg);// 2016/05/12 S21_NA#7861 Mod getWriterPsnCd -> getWriterPsnNum
                if (params.length > 5 && ZYPCommonFunc.hasValue(writerPsnCd)) {
                    ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[5], writerPsnCd);
                }
            }
        }
    }
}
