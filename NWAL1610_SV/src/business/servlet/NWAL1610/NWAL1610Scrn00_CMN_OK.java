/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.BIZ_ID;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1610.NWAL1610CMsg;
import business.servlet.NWAL1610.common.NWAL1610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610Scrn00_CMN_OK
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         Yokoi           Create          N/A
 * 2016/02/20   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 2016/06/01   Fujitsu         T.Murai         Update          S21 CSA QC#9189
 *</pre>
 */
public class NWAL1610Scrn00_CMN_OK extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;
        NWAL1610CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;

        NWAL1610CMsg bizMsg = new NWAL1610CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1610BMsg scrnMsg = (NWAL1610BMsg) bMsg;
        NWAL1610CMsg bizMsg = (NWAL1610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Set Parameter
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.xxModeCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[1], scrnMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[3], scrnMsg.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[4], scrnMsg.ordQty);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[5], scrnMsg.dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[6], scrnMsg.ordLineSrcCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[7], scrnMsg.rtlWhCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[8], scrnMsg.rtlSwhCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[9], scrnMsg.rtrnRsnCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[10], scrnMsg.xxScrItem20Txt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[11], scrnMsg.prcCatgNm);
            // Mod Start #1130 02/20/2016
            // ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[12], scrnMsg.flPrcListDescTxt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[12], scrnMsg.flPrcListNm);
            // Mod End #1130 02/20/2016
            ZYPEZDItemValueSetter.setValue((EZDBDateItem) params[13], scrnMsg.prcBaseDt);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[14], scrnMsg.hddRmvCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[15], scrnMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[16], scrnMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[17], scrnMsg.dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[18], scrnMsg.dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[19], scrnMsg.dsOrdRsnCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[20], scrnMsg.cpoOrdTs);
            ZYPEZDItemValueSetter.setValue((EZDBDateItem) params[21], scrnMsg.rddDt);
            // Add End #2096 02/28/2016
            // Add Start #9189 06/01/2016
            ZYPEZDItemValueSetter.setValue((EZDBDateItem) params[22], scrnMsg.rqstPickUpDt);
            // Add End #9189 06/01/2016
            // 2016/08/30 S21_NA#9806 Add Start
            if (params.length >= 27) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[25], scrnMsg.csmpContrNum);
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[26], scrnMsg.dlrRefNum);
            }
            // 2016/08/30 S21_NA#9806 Add End
        }
    }
}
