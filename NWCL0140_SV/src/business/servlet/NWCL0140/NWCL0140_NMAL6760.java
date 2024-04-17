/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_ACCT_D;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_ACCT_H;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_LOC_D;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_LOC_H;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_0;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_15;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : NWCL0140_NMAL6760
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0140_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_NM_NWCL0140_OPEN_WIN_ACCT_H.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd, scrnMsg.P.no(IDX_0).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd, scrnMsg.P.no(IDX_15).xxPopPrm_P);
                scrnMsg.setFocusItem(scrnMsg.billToCustAcctCd);
            } else if (EVENT_NM_NWCL0140_OPEN_WIN_ACCT_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                int eventRowIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).billToCustAcctCd_EL, scrnMsg.P.no(IDX_0).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).billToCustCd_EL, scrnMsg.P.no(IDX_15).xxPopPrm_P);
                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).billToCustAcctCd_EL);
            } else if (EVENT_NM_NWCL0140_OPEN_WIN_LOC_H.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd, scrnMsg.P.no(IDX_0).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd, scrnMsg.P.no(IDX_15).xxPopPrm_P);
                scrnMsg.setFocusItem(scrnMsg.billToCustCd);
            } else if (EVENT_NM_NWCL0140_OPEN_WIN_LOC_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                int eventRowIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).billToCustAcctCd_EL, scrnMsg.P.no(IDX_0).xxPopPrm_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).billToCustCd_EL, scrnMsg.P.no(IDX_15).xxPopPrm_P);
                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).billToCustCd_EL);
            }
        }
    }
}
