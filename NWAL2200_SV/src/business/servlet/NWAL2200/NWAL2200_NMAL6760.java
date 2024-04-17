/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2200.NWAL2200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2017/09/15   Fujitsu         R.Nakamura      Update          QC#21118
 *</pre>
 */
public class NWAL2200_NMAL6760 extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int slctLine = scrnMsg.xxCellIdx.getValueInt();

        List<String> targetEvent = new ArrayList<String>(0);
        targetEvent.add("OpenWin_ShipTo");
        targetEvent.add("OnBlur_DeriveFromShipToAccount");
        targetEvent.add("OnBlur_DeriveFromShipToLocation");
        targetEvent.add("OnBlur_DeriveFromShipToName");

        if (targetEvent.contains(scrEventNm)) {
            // Mod Start 2017/09/15 QC#21118
            if (slctLine == -1) {
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
            } else {
                String dplyTab = scrnMsg.xxDplyTab.getValue();
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToFirstRefCmntTxt_LC, scrnMsg.firstRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToScdRefCmntTxt_LC, scrnMsg.scdRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToFirstLineAddr_LC, scrnMsg.firstLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToScdLineAddr_LC, scrnMsg.scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToThirdLineAddr_LC, scrnMsg.thirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToFrthLineAddr_LC, scrnMsg.frthLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToCtyAddr_LC, scrnMsg.ctyAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToCntyNm_LC, scrnMsg.cntyNm);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToProvNm_LC, scrnMsg.provNm);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToStCd_LC, scrnMsg.stCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToPostCd_LC, scrnMsg.postCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(slctLine).shipToCtryCd_LC, scrnMsg.ctryCd);
                } else if (TAB_RMA.equals(dplyTab)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToFirstRefCmntTxt_RC, scrnMsg.firstRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToScdRefCmntTxt_RC, scrnMsg.scdRefCmntTxt);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToFirstLineAddr_RC, scrnMsg.firstLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToScdLineAddr_RC, scrnMsg.scdLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToThirdLineAddr_RC, scrnMsg.thirdLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToFrthLineAddr_RC, scrnMsg.frthLineAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToCtyAddr_RC, scrnMsg.ctyAddr);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToCntyNm_RC, scrnMsg.cntyNm);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToProvNm_RC, scrnMsg.provNm);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToStCd_RC, scrnMsg.stCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToPostCd_RC, scrnMsg.postCd);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(slctLine).shipToCtryCd_RC, scrnMsg.ctryCd);
                }
            }
            // Mod End 2017/09/15 QC#21118
        }

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        // Set Focus
        if ("OpenWin_BillTo".equals(scrEventNm)) {
            if (slctLine == -1) {
                scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(slctLine).billToCustLocCd_LC);
                } else {
                    scrnMsg.setFocusItem(scrnMsg.C.no(slctLine).billToCustLocCd_RC);
                }
            }
        } else if ("OnBlur_DeriveFromBillToName".equals(scrEventNm) || "OnBlur_DeriveFromBillToAccount".equals(scrEventNm) || "OnBlur_DeriveFromBillToLocation".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
        } else if ("OpenWin_ShipTo".equals(scrEventNm)) {
            if (slctLine == -1) {
                scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    scrnMsg.setFocusItem(scrnMsg.A.no(slctLine).shipToCustLocCd_LC);
                } else {
                    scrnMsg.setFocusItem(scrnMsg.C.no(slctLine).shipToCustLocCd_RC);
                }
            }
        } else if ("OnBlur_DeriveFromShipToName".equals(scrEventNm) || "OnBlur_DeriveFromShipToAccount".equals(scrEventNm) || "OnBlur_DeriveFromShipToLocation".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
        } else {
            scrnMsg.setFocusItem(scrnMsg.negoDealAmt);
        }
    }
}
