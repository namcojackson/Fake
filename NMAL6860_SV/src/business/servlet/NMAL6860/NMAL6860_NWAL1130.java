/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.constant.NMAL6860Constant.BIZ_ID;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.LINK_OPENWIN_CTY_EVENT_NM;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.LINK_OPENWIN_INVOICE_SUPPLIER;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.LINK_OPENWIN_PAYMENT_TERM;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.LINK_OPENWIN_SUPPLIER_TYPE;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_GENERAL;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6860.NMAL6860CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   CITS            T.Gotoda        Create          N/A
 * 2016/08/09   CITS            S.Endo          Update          QC#10839
 * 2016/09/26   CITS            T.Gotoda        Update          QC#13163
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 *</pre>
 */
public class NMAL6860_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        // 2020/02/28 QC#55971 Add Start
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (LINK_OPENWIN_SUPPLIER_TYPE.equals(scrnMsg.xxPopPrm_AD.getValue())) {
                NMAL6860CMsg bizMsg = new NMAL6860CMsg();
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndTpCd, scrnMsg.Z.no(0).xxComnScrColValTxt_Z);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndTpDescTxt, scrnMsg.Z.no(1).xxComnScrColValTxt_Z);
                scrnMsg.setFocusItem(scrnMsg.prntVndTpDescTxt);
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        // 2020/02/28 QC#55971 Add End
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            return;
        }

        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        if (LINK_OPENWIN_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm_AD.getValue())) {
            if (scrnMsg.xxNum_AD.getValue() != null) {
                int idxNum = scrnMsg.xxNum_AD.getValueInt();
                setResultForAddressPopup(scrnMsg, idxNum);
                scrnMsg.setFocusItem(scrnMsg.A.no(idxNum).ctyAddr_A);
            }
        } else if (LINK_OPENWIN_INVOICE_SUPPLIER.equals(scrnMsg.xxPopPrm_AD.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.invVndCd_H2, scrnMsg.Z.no(2).xxComnScrColValTxt_Z);
            scrnMsg.setFocusItem(scrnMsg.invVndCd_H2);
        // QC#13163 Start
        } else if (LINK_OPENWIN_PAYMENT_TERM.equals(scrnMsg.xxPopPrm_AD.getValue())) {
            if (TAB_NM_GENERAL.equals(scrnMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermCd_H1, scrnMsg.Z.no(0).xxComnScrColValTxt_Z);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermDescTxt_H1, scrnMsg.Z.no(1).xxComnScrColValTxt_Z);
                scrnMsg.setFocusItem(scrnMsg.vndPmtTermDescTxt_H1);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermCd_H2, scrnMsg.Z.no(0).xxComnScrColValTxt_Z);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermDescTxt_H2, scrnMsg.Z.no(1).xxComnScrColValTxt_Z);
                scrnMsg.setFocusItem(scrnMsg.vndPmtTermDescTxt_H2);
            }
        // QC#13163 End
        }
    }

    private void setResultForAddressPopup(NMAL6860BMsg scrnMsg, int idxNum) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idxNum).ctyAddr_A, scrnMsg.P.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idxNum).stCd_A, scrnMsg.P.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idxNum).postCd_A, scrnMsg.P.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idxNum).cntyNm_A, scrnMsg.P.no(3).xxComnScrColValTxt);
    }
}
