/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL4500;

import static business.servlet.NMAL4500.constant.NMAL4500Constant.BIZ_ID;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.BTN_CMN_CLOSE_EVENT_NM;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.LINK_OPENWIN_CNTY_EVENT_NM;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.LINK_OPENWIN_CTY_EVENT_NM;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.LINK_OPENWIN_POSTCD_EVENT_NM;
import static business.servlet.NMAL4500.constant.NMAL4500Constant.LINK_OPENWIN_ST_EVENT_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL4500.NMAL4500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Endo          Create          QC#10840
 * 2016/08/31   CITS            S.Endo          Update          QC#10840
 *</pre>
 */
public class NMAL4500_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_01, scrnMsg.P.no(1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_02.no(0), scrnMsg.P.no(3).xxComnScrColValTxt);
            NMAL4500CMsg bizMsg = new NMAL4500CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        NMAL4500CMsg bizMsg = (NMAL4500CMsg) cMsg;
        if (!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard())) {
            if (LINK_OPENWIN_CTY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.ctyAddr_01);
            } else if (LINK_OPENWIN_ST_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.stCd_01);
            } else if (LINK_OPENWIN_POSTCD_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.postCd_01);
            } else if (LINK_OPENWIN_CNTY_EVENT_NM.equals(scrnMsg.xxPopPrm.getValue())) {
                setResultForAddressPopup(scrnMsg, bizMsg);
                scrnMsg.setFocusItem(scrnMsg.cntyPk_03);
            }
        }

    }

    /**
     * setResultForAddressPopup
     * @param scrnMsg NPAL4500BMsg
     */
    private void setResultForAddressPopup(NMAL4500BMsg scrnMsg, NMAL4500CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_01, scrnMsg.P.no(0).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_01, scrnMsg.P.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_01, scrnMsg.P.no(2).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cntyPk_03, bizMsg.cntyPk_03);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cntyPk_01.no(0), bizMsg.cntyPk_03);
    }
}
