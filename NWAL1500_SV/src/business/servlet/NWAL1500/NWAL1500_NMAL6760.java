/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BILL_EVENT_LIST;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.SHIP_EVENT_LIST;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.SOLD_EVENT_LIST;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
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
 * 2016/02/12   Fujitsu         T.Yoshida       Create          N/A
 * 2016/03/02   Fujitsu         Y.Taoka         Update          S21_NA#1694
 *</pre>
 */
public class NWAL1500_NMAL6760 extends S21CommonHandler {

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
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int slctLine = scrnMsg.xxCellIdx.getValueInt();

        if (BILL_EVENT_LIST.contains(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                NWAL1500_ABMsg confMsg = scrnMsg.A.no(slctLine);
                ZYPEZDItemValueSetter.setValue(confMsg.billToCustAcctCd_LC, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(confMsg.billToCustCd_LC, scrnMsg.xxPopPrm_P7);
            } else if (TAB_RMA.equals(dplyTab)) {
                NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(slctLine);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustAcctCd_RC, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustCd_RC, scrnMsg.xxPopPrm_P7);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd, scrnMsg.xxPopPrm_P7);
            }

        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                NWAL1500_ABMsg confMsg = scrnMsg.A.no(slctLine);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToCustAcctCd_LC, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToCustCd_LC, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToFirstLineAddr_LC, scrnMsg.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToScdLineAddr_LC, scrnMsg.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToThirdLineAddr_LC, scrnMsg.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToFrthLineAddr_LC, scrnMsg.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToCtyAddr_LC, scrnMsg.ctyAddr);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToCntyNm_LC, scrnMsg.cntyNm);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToProvNm_LC, scrnMsg.provNm);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToStCd_LC, scrnMsg.stCd);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToPostCd_LC, scrnMsg.postCd);
                ZYPEZDItemValueSetter.setValue(confMsg.shipToCtryCd_LC, scrnMsg.ctryCd);
            } else if (TAB_RMA.equals(dplyTab)) {
                NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(slctLine);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustAcctCd_RC, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCustCd_RC, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFirstLineAddr_RC, scrnMsg.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToScdLineAddr_RC, scrnMsg.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToThirdLineAddr_RC, scrnMsg.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToFrthLineAddr_RC, scrnMsg.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCtyAddr_RC, scrnMsg.ctyAddr);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCntyNm_RC, scrnMsg.cntyNm);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToProvNm_RC, scrnMsg.provNm);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToStCd_RC, scrnMsg.stCd);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToPostCd_RC, scrnMsg.postCd);
                ZYPEZDItemValueSetter.setValue(rmaConfMsg.shipToCtryCd_RC, scrnMsg.ctryCd);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustAcctCd, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.xxPopPrm_P7);
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
        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd, scrnMsg.xxPopPrm_P6);
            ZYPEZDItemValueSetter.setValue(scrnMsg.soldToCustLocCd, scrnMsg.xxPopPrm_P7);
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

        // Set Focus & Item Protect
        if (BILL_EVENT_LIST.contains(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(slctLine).dropShipFlg_LC);
            } else if (TAB_RMA.equals(dplyTab)) {
                scrnMsg.setFocusItem(scrnMsg.C.no(slctLine).dropShipFlg_RC);
            } else {
                NWAL1500CommonLogicForScrnFields.setProtectByBillTo(this, scrnMsg); // QC#17474 2017/02/21 Add
                scrnMsg.setFocusItem(scrnMsg.shipToCustAcctNm);
            }
        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(slctLine).billToCustAcctCd_LC);
            } else if (TAB_RMA.equals(dplyTab)) {
                scrnMsg.setFocusItem(scrnMsg.C.no(slctLine).billToCustAcctCd_RC);
            } else {
                scrnMsg.setFocusItem(scrnMsg.soldToCustAcctNm);
                NWAL1500CommonLogicForScrnFields.setProtectByBillTo(this, scrnMsg); // QC#17474 2017/02/21 Add
            }
        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.negoDealAmt);
        }
    }
}
