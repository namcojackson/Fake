/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_PRNT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_UPDT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1770_NWAL1790 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_Confirmation".equals(scrEventNm)) {
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRqstFlg_ML.getValue()) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRqstFlg_PR.getValue())) {
                return null;
            }
        } else {
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRqstFlg_ML.getValue())) {
                return null;
            }
        }

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        String rqstFlgMail = scrnMsg.xxRqstFlg_ML.getValue();
        String rqstFlgPrint = scrnMsg.xxRqstFlg_PR.getValue();

        if ("OpenWin_Confirmation".equals(scrEventNm)) {
            if (ZYPConstant.FLG_OFF_N.equals(rqstFlgMail) && ZYPConstant.FLG_OFF_N.equals(rqstFlgPrint)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).ctacPsnTpCd_A);
                return;
            }
        } else {
            if (ZYPConstant.FLG_OFF_N.equals(rqstFlgMail)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).ctacPsnTpCd_A);
                return;
            }
        }

        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if ("OpenWin_Confirmation".equals(scrEventNm) && ZYPConstant.FLG_ON_Y.equals(rqstFlgPrint)) {
            // Call BLAP For Create PDF
            bizMsg = (NWAL1770CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_ID, FUNC_CD_PRNT);
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            String tempFilePath = bizMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
            return;
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(0).ctacPsnTpCd_A);
    }
}
