/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_CLOSE_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_OPENWIN_CTRY_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_OPEN_SUPPLIER_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.LINK_OPENWIN_CNTY_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.LINK_OPENWIN_CTY_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.LINK_OPENWIN_POSTCD_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.LINK_OPENWIN_ST_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.LINK_OPENWIN_PAYMENT_TERM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : Return Action from NWAL1130(common popup. supplier, state, country)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 09/25/2018   CITS            K.Kameoka       Create          QC#28226
 *</pre>
 */
public class NPAL1500_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            if (BTN_OPEN_SUPPLIER_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.P.no(2).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm, scrnMsg.P.no(3).xxComnScrColValTxt);
                NPAL1500CMsg bizMsg = new NPAL1500CMsg();
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            } else if (BTN_OPENWIN_CTRY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd_ST, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtryCd_ST, scrnMsg.P.no(2).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctryDescTxt_ST, scrnMsg.P.no(3).xxComnScrColValTxt);
                return null;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;

        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            if (BTN_OPEN_SUPPLIER_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                // set
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, bizMsg.prntVndCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, bizMsg.prntVndNm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, bizMsg.vndCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm, bizMsg.vndNm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.ccyCd, bizMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermDescTxt, bizMsg.vndPmtTermDescTxt);
                scrnMsg.setFocusItem(scrnMsg.prntVndCd);
            } else if (BTN_OPENWIN_CTRY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                // set
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd_ST, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtryCd_ST, scrnMsg.P.no(2).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctryDescTxt_ST, scrnMsg.P.no(3).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.ctacPsnNm);
            } else if (LINK_OPENWIN_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg);
                scrnMsg.setFocusItem(scrnMsg.shipToCtyAddr_ST);
            } else if (LINK_OPENWIN_ST_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg);
                scrnMsg.setFocusItem(scrnMsg.shipToStCd_ST);
            } else if (LINK_OPENWIN_POSTCD_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg);
                scrnMsg.setFocusItem(scrnMsg.shipToPostCd_ST);
            } else if (LINK_OPENWIN_CNTY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg);
                scrnMsg.setFocusItem(scrnMsg.shipToCntyNm_ST);
            // QC#28226 Add Start
            } else if (LINK_OPENWIN_PAYMENT_TERM.equals(scrnMsg.xxPopPrm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermCd, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermDescTxt, scrnMsg.P.no(1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.vndPmtTermDescTxt);
            }
            // QC#28226 Add End
        }
    }

    /**
     * setResultForAddressPopup
     * @param scrnMsg NPAL1500BMsg
     */
    private void setResultForAddressPopup(NPAL1500BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtyAddr_ST, scrnMsg.P.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd_ST, scrnMsg.P.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToPostCd_ST, scrnMsg.P.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCntyNm_ST, scrnMsg.P.no(3).xxComnScrColValTxt);
    }
}
