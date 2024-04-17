/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0460;


import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import static business.servlet.NSAL0460.common.NSAL0460CommonLogic.*;
import static business.servlet.NSAL0460.constant.NSAL0460Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0460.NSAL0460CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Start Read Capture
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#2886
 *</pre>
 */
public class NSAL0460_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        Object[] params = (Object[]) getArgForSubScreen();
        if (params == null || params.length == 0 || params[0] == null) {
            bMsg.setMessageInfo(NSAM0219E, new String[] {ERR_PRAM_NO_INPUT });
            bMsg.putErrorScreen();
        }
        if (params[0] instanceof EZDBMsgArray) {
            EZDBMsgArray array = (EZDBMsgArray) params[0];
            if (array.getValidCount() > PARAM_LENGTH) {
                bMsg.setMessageInfo(NSAM0219E, new String[] {ERR_PRAM_LIMIT });
                bMsg.putErrorScreen();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
            setValue(scrnMsg.P.no(0).dsContrPk_P1, (EZDBBigDecimalItem) params[0]);
            scrnMsg.P.setValidCount(1);
        }
        if (params != null && params.length > 0 && params[0] != null && params[0] instanceof EZDBMsgArray) {
            EZDMsg.copy(((EZDBMsgArray) params[0]), null, scrnMsg.P, null);
        }

        NSAL0460CMsg bizMsg = new NSAL0460CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        NSAL0460CMsg bizMsg = (NSAL0460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setValue(scrnMsg.xxHldFlg, ZYPConstant.FLG_OFF_N);
        initialControlScreen(this, scrnMsg);

        // [QC#2886,DEL]START
        //if ("E".equals(bizMsg.getMessageKind())) {
        //    throw new EZDFieldErrorException();
        //}
        // [QC#2886,DEL]END
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        scrnMsg.svcMemoRsnCd_PS.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mtrReadDt.setNameForMessage("Read Date");
            scrnMsg.A.no(i).readMtrCnt.setNameForMessage("Read");
        }
    }
}
