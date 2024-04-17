/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_D;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_H;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_REASON_D;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_REASON_H;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_0;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.IDX_1;
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
public class NWCL0140_NWAL1130 extends S21CommonHandler {

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
            if (EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_H.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.dsOrdCatgDescTxt);
            } else if (EVENT_NM_NWCL0140_OPEN_WIN_ORD_CATG_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                int eventRowIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(eventRowIndex).dsOrdCatgCd_EX, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(eventRowIndex).dsOrdCatgDescTxt_EX, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.B.no(eventRowIndex).dsOrdCatgDescTxt_EX);
            } else if (EVENT_NM_NWCL0140_OPEN_WIN_REASON_H.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdTpDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.dsOrdTpDescTxt);
            } else if (EVENT_NM_NWCL0140_OPEN_WIN_REASON_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                int eventRowIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(eventRowIndex).dsOrdTpCd_EX, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(eventRowIndex).dsOrdTpDescTxt_EX, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.B.no(eventRowIndex).dsOrdTpDescTxt_EX);
            }
        }
    }
}
