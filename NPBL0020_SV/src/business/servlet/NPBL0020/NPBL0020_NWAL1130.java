/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Return Action from NWAL1130
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/16/2016   CITS            Makoto Okigami  Create          N/A
 * 04/06/2016   CITS            K.Ogino         Update          N/A
 * 06/10/2016   CSAI            D.Fukaya        Update          QC#9048
 * 03/27/2018   CITS            T.Wada          Update          QC#22517
 *</pre>
 */
public class NPBL0020_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            String scrEventNm = scrnMsg.xxScrEventNm.getValue();

            if ("OpenWin_SupplierSite".equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dplyVndNm, scrnMsg.R.no(2).xxComnScrColValTxt);

                NPBL0020CMsg bizMsg = new NPBL0020CMsg();
                bizMsg.setBusinessID(BIZ_APP_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
            
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_SupplierSite".equals(scrEventNm)) {
            NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;
            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
                    scrnMsg.addCheckItem(scrnMsg.vndCd);
                    scrnMsg.putErrorScreen();
                } else {
                    scrnMsg.setFocusItem(scrnMsg.vndCd);
                }
            }
        } else if ("OpenWin_Requester".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm, scrnMsg.R.no(1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.fullPsnNm);
        // QC#22517 Add Start
        } else if ("OpenWin_PostCd".equals(scrEventNm) ) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToPostCd);
        } else if ("OpenWin_City".equals(scrEventNm) ) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToCtyAddr);
        } else if ("OpenWin_Cnty".equals(scrEventNm) ) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToCntyNm);
        } else if ("OpenWin_ST".equals(scrEventNm) ) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToStCd);
        } else if ("OpenWin_CTRY".equals(scrEventNm) ) {
            // set
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtryCd, scrnMsg.R.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctryNm, scrnMsg.R.no(3).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.shipToStCd);
        // QC#22517 Add End
        }
    }
    private void setResultForAddressPopup(NPBL0020BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtyAddr, scrnMsg.R.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd, scrnMsg.R.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToPostCd, scrnMsg.R.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCntyNm, scrnMsg.R.no(3).xxComnScrColValTxt);
    }

}
