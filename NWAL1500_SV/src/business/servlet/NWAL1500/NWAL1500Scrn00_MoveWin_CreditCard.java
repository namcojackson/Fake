/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.CR_CARD_MODE_READ_ONLY;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CLOSED;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Fujitsu         T.Ishii         Create          N/A
 * 2016/05/24   Fujitsu         S.Takami        Update          S21_NA#8210
 * 2016/06/22   Fujitsu         S.Takami        Update          S21_NA#8210-2
 * 2016/10/03   Fujitsu         S.Iidaka        Update          S21_NA#13958
 *</pre>
 */
public class NWAL1500Scrn00_MoveWin_CreditCard extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        setArgForSubScreen(getArgForSubScreen(scrnMsg));
    }

    private Serializable getArgForSubScreen(NWAL1500BMsg scrnMsg) {

        List<Object> parameters = new ArrayList<Object>();

        // 2016/05/24 S21_NA#8210 Mod Start
        // scrnMsg.sellToCustCd_O.setValue(scrnMsg.soldToCustLocCd.getValue());
        // scrnMsg.sellToCustCd_O.setValue(scrnMsg.sellToCustCd.getValue()); 2016/06/22 S21_NA#8210-2 Mod
        scrnMsg.sellToCustCd_O.setValue(scrnMsg.billToCustAcctCd.getValue()); // 2016/06/22 S21_NA#8210-2
        // 2016/05/24 S21_NA#8210 Mod Start
        scrnMsg.crCardTrxTpCd_O.setValue(CR_CARD_TRX_TP.CPO);
        scrnMsg.firstTrxInfoTxt_O.setValue(scrnMsg.cpoOrdNum.getValue());
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
        // 2016/10/03 S21_NA#13958 ADD START
        if (ZYPCommonFunc.hasValue(scrnMsg.ordHdrStsDescTxt) && HEADER_STS_NM_CLOSED.equals(scrnMsg.ordHdrStsDescTxt.getValue())) {
            scrnMsg.xxModeCd_O.setValue(CR_CARD_MODE_READ_ONLY);
        }
        // 2016/10/03 S21_NA#13958 ADD END
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
        // 2016/10/03 S21_NA#13958 ADD START
        if (ZYPCommonFunc.hasValue(scrnMsg.ordHdrStsDescTxt) && HEADER_STS_NM_CLOSED.equals(scrnMsg.ordHdrStsDescTxt.getValue())) {
            parameters.add(scrnMsg.xxModeCd_O);
        }
        // 2016/10/03 S21_NA#13958 ADD END

        return parameters.toArray(new Object[0]);
    }
}
