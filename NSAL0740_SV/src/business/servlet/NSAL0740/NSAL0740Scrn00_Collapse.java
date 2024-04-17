/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.common.NSAL0740CommonLogic.*;
import static business.servlet.NSAL0740.constant.NSAL0740Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/17   Hitachi         M.Gotou         Update          QC#4472
 *</pre>
 */
public class NSAL0740Scrn00_Collapse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        int eventLine = getButtonSelectNumber();

        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // set selected row number to bMsg
        setValue(bizMsg.xxRowNum, new BigDecimal(eventLine));

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        NSAL0740CMsg bizMsg = (NSAL0740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // mod start 2016/05/17 CSA Defect#4472
        controlScreenFields(this, scrnMsg);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftPrcMethCd_D3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftBasePrcUpRatio_D1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1);
        }
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // mod end 2016/05/17 CSA Defect#4472
    }
}
