/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.NO_SLCT_DTL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500_NMAL6780 extends S21CommonHandler {

    // TODO
    // UnUsed T.Yoshida
    // NMAL6780 -> NMAL6760

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

        List<String> targetEvent = new ArrayList<String>(0);
        targetEvent.add("OpenWin_ShipTo");
        targetEvent.add("OnBlur_DeriveFromShipToAccount");
        targetEvent.add("OnBlur_DeriveFromShipToLocation");
        targetEvent.add("OnBlur_DeriveFromShipToName");

        if (targetEvent.contains(scrEventNm)) {
            if (NO_SLCT_DTL == slctLine) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToFirstRefCmntTxt, scrnMsg.firstRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToScdRefCmntTxt, scrnMsg.scdRefCmntTxt);
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

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        // Set Focus
        if ("OpenWin_BillTo".equals(scrEventNm)) {
            if (NO_SLCT_DTL == slctLine) {
                scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(slctLine).billToCustCd_LC);
                } else {
                    scrnMsg.setFocusItem(scrnMsg.C.no(slctLine).billToCustCd_RC);
                }
            }
        } else if ("OnBlur_DeriveFromBillToName".equals(scrEventNm) || "OnBlur_DeriveFromBillToAccount".equals(scrEventNm) || "OnBlur_DeriveFromBillToLocation".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
        } else if ("OpenWin_ShipTo".equals(scrEventNm)) {
            if (NO_SLCT_DTL == slctLine) {
                scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(slctLine).shipToCustCd_LC);
                } else {
                    scrnMsg.setFocusItem(scrnMsg.C.no(slctLine).shipToCustCd_RC);
                }
            }
        } else if ("OnBlur_DeriveFromShipToName".equals(scrEventNm) || "OnBlur_DeriveFromShipToAccount".equals(scrEventNm) || "OnBlur_DeriveFromShipToLocation".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
        } else {
            scrnMsg.setFocusItem(scrnMsg.negoDealAmt);
        }
    }
}
