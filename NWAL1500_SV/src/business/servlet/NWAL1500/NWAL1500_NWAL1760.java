/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.NO_SLCT_DTL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Fujitsu         Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NWAL1500_NWAL1760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if ("OpenWin_PriceList".equals(scrEventNm) || "OnBlur_DeriveFromPriceList".equals(scrEventNm)) {
            if (NO_SLCT_DTL == slctLine) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm, scrnMsg.xxPopPrm_P3);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(slctLine).prcCatgNm_LL, scrnMsg.xxPopPrm_P3);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(slctLine).prcCatgNm_RL, scrnMsg.xxPopPrm_P3);
                }
            }
        } else {
            if (NO_SLCT_DTL == slctLine) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListNm, scrnMsg.xxPopPrm_P3);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(slctLine).flPrcListNm_LL, scrnMsg.xxPopPrm_P3);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(slctLine).flPrcListNm_RL, scrnMsg.xxPopPrm_P3);
                }
            }
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);//add QC#3943 
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if ("OpenWin_PriceList".equals(scrEventNm) || "OnBlur_DeriveFromPriceList".equals(scrEventNm)) {
            if (NO_SLCT_DTL == slctLine) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).prcCatgNm_LL, scrnMsg.prcCatgNm);
                }
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).prcCatgNm_RL, scrnMsg.prcCatgNm);
                }
                scrnMsg.setFocusItem(scrnMsg.custIssPoNum);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).flPrcListNm_LL);
                } else {
                    scrnMsg.setFocusItem(scrnMsg.D.no(slctLine).flPrcListNm_RL);
                }
            }
        } else {
            if (NO_SLCT_DTL == slctLine) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).flPrcListNm_LL, scrnMsg.flPrcListNm);
                }
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).flPrcListNm_RL, scrnMsg.flPrcListNm);
                }
                scrnMsg.setFocusItem(scrnMsg.loanPerDaysAot);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).serNum_LL);
                } else {
                    scrnMsg.setFocusItem(scrnMsg.D.no(slctLine).serNum_RL);
                }
            }
        }
    }
}
