/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BILL_EVENT_LIST;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SHIP_EVENT_LIST;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SOLD_EVENT_LIST;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         T.Murai         Create          N/A
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 *</pre>
 */
public class NWAL1840_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (BILL_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd, scrnMsg.xxPopPrm_06);
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustLocCd, scrnMsg.xxPopPrm_07);
            // 2018/03/09 S21_NA#22387 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.billToLocNum, scrnMsg.xxPopPrm_08);
            // 2018/03/09 S21_NA#22387 Add End

        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctCd, scrnMsg.xxPopPrm_06);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustLocCd, scrnMsg.xxPopPrm_07);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipTo01RefCmntTxt, scrnMsg.firstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipTo02RefCmntTxt, scrnMsg.scdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToFirstLineAddr, scrnMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToScdLineAddr, scrnMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToThirdLineAddr, scrnMsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToFrthLineAddr, scrnMsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtyAddr, scrnMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCntyNm, scrnMsg.cntyNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToProvNm, scrnMsg.provNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd, scrnMsg.stCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToPostCd, scrnMsg.postCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtryCd, scrnMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNum, scrnMsg.xxPopPrm_08);
            // 2018/03/09 S21_NA#22387 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNum, scrnMsg.xxPopPrm_08);
            // 2018/03/09 S21_NA#22387 Add End
        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd, scrnMsg.xxPopPrm_06);
            ZYPEZDItemValueSetter.setValue(scrnMsg.soldToCustLocCd, scrnMsg.xxPopPrm_07);
            // 2018/03/09 S21_NA#22387 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToLocNum, scrnMsg.xxPopPrm_08);
            // 2018/03/09 S21_NA#22387 Add End
        }

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        // Set Focus
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (BILL_EVENT_LIST.contains(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.D.no(0).ctacPsnTpCd_D);
        }
    }
}
