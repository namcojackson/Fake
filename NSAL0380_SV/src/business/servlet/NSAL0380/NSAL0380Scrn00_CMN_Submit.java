/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0380.NSAL0380CMsg;
import business.servlet.NSAL0380.common.NSAL0380CommonLogic;
import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12199
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 * 2019/06/26   Hitachi         A.Kohinata      Update          QC#50931
 *</pre>
 */
public class NSAL0380Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        NSAL0380CommonLogic.checkItemForSubmit(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

        NSAL0380CMsg bizMsg = new NSAL0380CMsg();
        bizMsg.setBusinessID(NSAL0380Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0380Constant.FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;
        NSAL0380CMsg bizMsg = (NSAL0380CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // add start 2019/06/26 QC#50931
        NSAL0380CommonLogic.controlDtl(this, scrnMsg);
        // add end 2019/06/26 QC#50931

        // add start 2016/07/26 CSA Defect#12199
        // START 2017/12/21 M.Kidokoro [QC#22660, MOD]
//        scrnMsg.A.setCheckParam(new String[] {NSAL0380Bean.rnwPrcMethCd_A, NSAL0380Bean.basePrcUpRatio_A, NSAL0380Bean.mtrPrcUpRatio_A }, 1);
        // mod start 2019/06/26 QC#50931
        //scrnMsg.A.setCheckParam(new String[] {NSAL0380Bean.contrAutoRnwTpCd_A, NSAL0380Bean.rnwPrcMethCd_A, NSAL0380Bean.basePrcUpRatio_A, NSAL0380Bean.mtrPrcUpRatio_A }, 1);
        scrnMsg.A.setCheckParam(new String[] {NSAL0380Bean.befEndRnwDaysAot_A, NSAL0380Bean.contrAutoRnwTpCd_A, NSAL0380Bean.rnwPrcMethCd_A, NSAL0380Bean.basePrcUpRatio_A, NSAL0380Bean.mtrPrcUpRatio_A }, 1);
        // mod end 2019/06/26 QC#50931
        // END 2017/12/21 M.Kidokoro [QC#22660, MOD]
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();
        // add end 2016/07/26 CSA Defect#12199

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        // del start 2019/06/26 QC#50931
        //NSAL0380CommonLogic.controlDtl(this, scrnMsg);
        // del end 2019/06/26 QC#50931
        setButtonEnabled(NSAL0380Constant.BTN_CMN_SUBMIT_BTN_NM, false);
        scrnMsg.putErrorScreen();
    }
}
