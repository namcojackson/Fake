/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.printing.S21PrintingBusinessApInOutEJBLocal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/11   Fujitsu         T.Aoi           Create          S21_NA#22139
 *</pre>
 */
public class NWAL1500_NWAL2420 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRqstFlg_ML.getValue()) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRqstFlg_PR.getValue())) {
            return null;
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRqstFlg_ML.getValue()) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxRqstFlg_PR.getValue())) {
            return;
        }

        NWAL1500CMsg bizMsg  = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_PR.getValue())) {
            // Call BLAP For Create PDF
            bizMsg = (NWAL1500CMsg) new S21PrintingBusinessApInOutEJBLocal().executeBlap(ctx, bizMsg, BIZ_ID, "70");
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            String tempFilePath = bizMsg.xxFileData.getTempFilePath();
            executeDownloadPdf(tempFilePath, true);
            return;
        }
    }
}
