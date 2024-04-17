/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_EMERGENCY_LOCATION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION_SW;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION_SW;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_ST_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_POSTCD_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_CTY_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_CNTY_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_ST_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_POSTCD_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_CTY_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_CNTY_EVENT_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SUPPLIER_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6820.NMAL6820CMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Return Action from NWAL1130(common popup. supplier)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2016   CSAI            D.Fukaya        Create          QC#6406
 * 2016/08/02   CITS            S.Endo          Update          QC#10838
 * 08/31/2016   CITS            Hisashi         update          QC#14018
 *</pre>
 */
public class NMAL6820_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_SD, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_SD, scrnMsg.P.no(3).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.prntVndNm_SD);

            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_EMERGENCY_LOCATION.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_SE, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_SE, scrnMsg.P.no(3).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.prntVndNm_SE);

            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_SR, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm_SR, scrnMsg.P.no(3).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.prntVndNm_SR);

            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_SOURCE_LOCATION_SW.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                int selectIdx = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).prntVndNm_AS, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).vndNm_AS, scrnMsg.P.no(3).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).prntVndNm_AS);

            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_LOCATION_SW.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                int selectIdx = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).prntVndNm_AR, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectIdx).vndNm_AR, scrnMsg.P.no(3).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).prntVndNm_AR);
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (LINK_OPENWIN_SHIPTO_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_S1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_SHIPTO_ST_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_S1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_SHIPTO_POSTCD_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_S1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_SHIPTO_CNTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_S1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_RETURNTO_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToStCd_R1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_R1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_RETURNTO_ST_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToStCd_R1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_R1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_RETURNTO_POSTCD_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToStCd_R1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_R1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_RETURNTO_CNTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToStCd_R1, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_R1, scrnMsg.P.no(3).xxComnScrColValTxt);
            } else if (LINK_OPENWIN_SUPPLIER_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.P.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.P.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.P.no(2).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm, scrnMsg.P.no(3).xxComnScrColValTxt);
            }

            NMAL6820CMsg bizMsg = new NMAL6820CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (LINK_OPENWIN_SHIPTO_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupShipTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.ctyAddr_S1);
            } else if (LINK_OPENWIN_SHIPTO_ST_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupShipTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.stCd_S1);
            } else if (LINK_OPENWIN_SHIPTO_POSTCD_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupShipTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.postCd_S1);
            } else if (LINK_OPENWIN_SHIPTO_CNTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupShipTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.cntyNm_S1);
            } else if (LINK_OPENWIN_RETURNTO_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupReturnTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.rtrnToCtyAddr_R1);
            } else if (LINK_OPENWIN_RETURNTO_ST_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupReturnTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.rtrnToStCd_R1);
            } else if (LINK_OPENWIN_RETURNTO_POSTCD_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupReturnTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.rtrnToPostCd_R1);
            } else if (LINK_OPENWIN_RETURNTO_CNTY_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                setResultForAddressPopupReturnTo(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.cntyNm_R1);
            } else if (LINK_OPENWIN_SUPPLIER_EVENT_NM.equals(scrnMsg.xxPopPrm_21.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, bizMsg.prntVndCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, bizMsg.prntVndNm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, bizMsg.vndCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm, bizMsg.vndNm);
                scrnMsg.setFocusItem(scrnMsg.prntVndCd);
            }//aaaa
        }
    }

    private void setResultForAddressPopupShipTo(NMAL6820BMsg scrnMsg, NMAL6820CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_S1, scrnMsg.P.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, scrnMsg.P.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_S1, scrnMsg.P.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cntyPk_S1, bizMsg.cntyPk_S1);
    }

    private void setResultForAddressPopupReturnTo(NMAL6820BMsg scrnMsg, NMAL6820CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToCtyAddr_R1, scrnMsg.P.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToStCd_R1, scrnMsg.P.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToPostCd_R1, scrnMsg.P.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cntyPk_R1, bizMsg.cntyPk_R1);
    }
}
