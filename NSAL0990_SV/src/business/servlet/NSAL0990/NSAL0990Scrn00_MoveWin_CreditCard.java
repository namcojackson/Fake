/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC#5790
 * 2018/08/07   Hitachi         K.Kim           Update          QC#27195
 *</pre>
 */
public class NSAL0990Scrn00_MoveWin_CreditCard extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.billToLocNum)) {
            scrnMsg.billToLocNum.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String []{"Bill To"});
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        setArgForSubScreen(getArgForSubScreen(scrnMsg));
    }

    private Serializable getArgForSubScreen(NSAL0990BMsg scrnMsg) {

        List<Object> parameters = new ArrayList<Object>();

        // START 2018/08/07 K.Kim [QC#27195, MOD]
        // scrnMsg.sellToCustCd_O.setValue(scrnMsg.ownrLocNum.getValue());
        scrnMsg.sellToCustCd_O.setValue(scrnMsg.billToAcctNum.getValue());
        // END 2018/08/07 K.Kim [QC#27195, MOD]
        scrnMsg.crCardTrxTpCd_O.setValue(CR_CARD_TRX_TP.SERVICE_REQUEST);
        scrnMsg.firstTrxInfoTxt_O.clear();
        scrnMsg.scdTrxInfoTxt_O.clear();
        scrnMsg.thirdTrxInfoTxt_O.clear();
        scrnMsg.frthTrxInfoTxt_O.clear();
        scrnMsg.fifthTrxInfoTxt_O.clear();
        scrnMsg.firstTrxInfoNum_O.clear();
        scrnMsg.scdTrxInfoNum_O.clear();
        scrnMsg.thirdTrxInfoNum_O.clear();
        scrnMsg.frthTrxInfoNum_O.clear();
        scrnMsg.fifthTrxInfoNum_O.clear();
        scrnMsg.dsCrCardPk_O.setValue(scrnMsg.dsCrCardPk.getValue());

        parameters.add(scrnMsg.sellToCustCd_O);
        parameters.add(scrnMsg.crCardTrxTpCd_O);
        parameters.add(scrnMsg.firstTrxInfoTxt_O);
        parameters.add(scrnMsg.scdTrxInfoTxt_O);
        parameters.add(scrnMsg.thirdTrxInfoTxt_O);
        parameters.add(scrnMsg.frthTrxInfoTxt_O);
        parameters.add(scrnMsg.fifthTrxInfoTxt_O);
        parameters.add(scrnMsg.firstTrxInfoNum_O);
        parameters.add(scrnMsg.scdTrxInfoNum_O);
        parameters.add(scrnMsg.thirdTrxInfoNum_O);
        parameters.add(scrnMsg.frthTrxInfoNum_O);
        parameters.add(scrnMsg.fifthTrxInfoNum_O);
        parameters.add(scrnMsg.dsCrCardPk_O);

        return parameters.toArray(new Object[parameters.size()]);
    }
}
