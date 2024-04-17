/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BTN_CMN_CLOSE_EVENT_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_0;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_1;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_2;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_3;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BTN_OPENWIN_CTRY_EVENT_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.LINK_OPENWIN_CNTY_EVENT_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.LINK_OPENWIN_CTY_EVENT_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.LINK_OPENWIN_POSTCD_EVENT_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.LINK_OPENWIN_ST_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1280.NPAL1280CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : NWAL1130
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 03/01/2016   CITS            K.Ogino          Update          QC#4643
 * 07/20/2018   CITS            K.Kameoka        Update          QC#26990
 */
public class NPAL1280_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        if ("OpenWin_Supplier".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            int lineNum = Integer.valueOf(scrnMsg.xxLineNum.getValue());
            if (lineNum >= 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).prntVndCd_A1, scrnMsg.P.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).prntVndNm_A1, scrnMsg.P.no(IDX_1).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).vndCd_A1, scrnMsg.P.no(IDX_2).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).locNm_A1, scrnMsg.P.no(IDX_3).xxComnScrColValTxt.getValue());

                NPAL1280CMsg bizMsg = new NPAL1280CMsg();
                bizMsg.setBusinessID(BUSINESS_APPL_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        if ("OpenWin_Requester".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqRqstByPsnCd, scrnMsg.P.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm, scrnMsg.P.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.fullPsnNm);
        } else if ("OpenWin_Supplier".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            int lineNum = Integer.valueOf(scrnMsg.xxLineNum.getValue());
            if (lineNum < 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.P.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.P.no(IDX_1).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.P.no(IDX_2).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNm, scrnMsg.P.no(IDX_3).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.vndCd);
            } else {
                NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
                scrnMsg.setFocusItem(scrnMsg.A.no(lineNum).locNm_A1);
            }
        } else if ("OpenWin_Carrier".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_HF, scrnMsg.P.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_HF, scrnMsg.P.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.locNm_HF);
        //QC#26990 Add Start
        } else if (BTN_OPENWIN_CTRY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
            // set
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd_HS, scrnMsg.P.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtryCd_HS, scrnMsg.P.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.ctryNm_HS, scrnMsg.P.no(3).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.ctacPsnNm_HS);
        } else if (LINK_OPENWIN_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToCtyAddr_HS);
        } else if (LINK_OPENWIN_ST_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToStCd_HS);
        } else if (LINK_OPENWIN_POSTCD_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToPostCd_HS);
        } else if (LINK_OPENWIN_CNTY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
            setResultForAddressPopup(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.shipToCntyNm_HS);
        }
    }
    /**
     * setResultForAddressPopup
     * @param scrnMsg NPAL1500BMsg
     */
    private void setResultForAddressPopup(NPAL1280BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCtyAddr_HS, scrnMsg.P.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToStCd_HS, scrnMsg.P.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToPostCd_HS, scrnMsg.P.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCntyNm_HS, scrnMsg.P.no(3).xxComnScrColValTxt);
    }
    //QC#26990 Add End

}
