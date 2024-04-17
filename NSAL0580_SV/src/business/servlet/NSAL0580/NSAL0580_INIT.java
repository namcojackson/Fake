/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0580;

import static business.servlet.NSAL0580.constant.NSAL0580Constant.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;

import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0580.NSAL0580CMsg;
import business.servlet.NSAL0580.common.NSAL0580CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         J.Kim           Create          N/A
 * 2016/02/26   Hitachi         K.Kishimoto     Update          QC#2011
 *</pre>
 */
public class NSAL0580_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;

        BigDecimal dsContrDtlPk = null;
        Serializable ser = getArgForSubScreen();

        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    dsContrDtlPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk, dsContrDtlPk);

        NSAL0580CMsg bizMsg = new NSAL0580CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;
        NSAL0580CMsg bizMsg = (NSAL0580CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        NSAL0580CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0580BMsg scrnMsg = (NSAL0580BMsg) bMsg;

        scrnMsg.dsContrNum.setNameForMessage("Contract#");
        scrnMsg.dsContrCtrlStsNm.setNameForMessage("Status");
        //Mod Start 02/26/2016 <QC#2011>
        scrnMsg.dsContrCtrlStsCd_H.setNameForMessage("New Status");
        //Mod End   02/26/2016 <QC#2011>
        scrnMsg.svcMemoRsnCd_H.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt.setNameForMessage("Notes");
    }
}
