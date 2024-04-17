/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2340;

import static business.servlet.NWAL2340.constant.NWAL2340Constant.BIZ_APP_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2340.NWAL2340CMsg;
import business.servlet.NWAL2340.common.NWAL2340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * SOM Address Mass Apply Mass Apply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 * 2017/09/07   Fujitsu         R.Nakamura      Update          QC#20974
 * 2017/09/20   Fujitsu         R.Nakamura      Update          QC#21114
 *</pre>
 */
public class NWAL2340Scrn00_MassApply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Add Start 2017/09/20 QC#21114
        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;
        NWAL2340CommonLogic.addCheckItemHdr(scrnMsg);
        // Add End 2017/09/20 QC#21114
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Mod Start 2017/09/20 QC#21114
        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;
        NWAL2340CMsg bizMsg = new NWAL2340CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

//        return null;
        return bizMsg;
        // Mod End 2017/09/20 QC#21114
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;
        // Add Start 2017/09/20 QC#21114
        NWAL2340CMsg bizMsg = (NWAL2340CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2340CommonLogic.addCheckItemHdr(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        String allLineAddr = NWAL2340CommonLogic.setAllLineAddressInfo(scrnMsg, -1);
        // Add End 2017/09/20 QC#21114
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCustAcctNm_A, scrnMsg.shipToCustAcctNm_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCustAcctCd_A, scrnMsg.shipToCustAcctCd_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCustCd_A, scrnMsg.shipToCustCd_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToFirstLineAddr_A, scrnMsg.shipToFirstLineAddr_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToScdLineAddr_A, scrnMsg.shipToScdLineAddr_H);
                // Mod Start 2017/09/07 QC#20974
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToThirdLineAddr_A, scrnMsg.shipToThirdLineAddr_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToFrthLineAddr_A, scrnMsg.shipToFrthLineAddr_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCtyAddr_A, scrnMsg.shipToCtyAddr_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToStCd_A, scrnMsg.shipToStCd_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToPostCd_A, scrnMsg.shipToPostCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxLocAddrNm_A, scrnMsg.xxLocAddrNm_H);
                // Mod Start 2017/09/20 QC#21114
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxAllLineAddr_A, scrnMsg.xxAllLineAddr_H);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxAllLineAddr_A, allLineAddr);
                // Mod End 2017/09/20 QC#21114
                // Mod End 2017/09/07 QC#20974
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_A, ZYPConstant.FLG_OFF_N);
            }
        }
    }
}
