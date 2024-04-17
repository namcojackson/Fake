/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6880;

import static business.servlet.NMAL6880.constant.NMAL6880Constant.BIZ_APP_ID;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.OPEN_WIN_FROM_SUB_WH_D;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.OPEN_WIN_FROM_WH_D;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.OPEN_WIN_FROM_WH_H;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6880.NMAL6880CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * Function Name : NPAL1010 Return
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 * 07/12/2017   CITS            T.Kikuhara      Update          QC#19864
 *</pre>
 */
public class NMAL6880_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;

        NMAL6880CMsg bizMsg = new NMAL6880CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        int eventRowIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlWhCd_A1, scrnMsg.xxPopPrm_P6);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, Integer.toString(eventRowIndex));
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;
        NMAL6880CMsg bizMsg = (NMAL6880CMsg) cMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (OPEN_WIN_FROM_WH_H.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.xxPopPrm_P7);
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
            } else if (OPEN_WIN_FROM_WH_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                int eventRowIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlWhCd_A1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlWhNm_A1, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlSwhCd_A1, scrnMsg.xxPopPrm_P8);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).xxAllLineAddr_A1, bizMsg.A.no(eventRowIndex).xxAllLineAddr_A1);
                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtlWhCd_A1);
            } else if (OPEN_WIN_FROM_SUB_WH_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                int eventRowIndex = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlWhCd_A1, scrnMsg.xxPopPrm_P6);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlWhNm_A1, scrnMsg.xxPopPrm_P7);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).rtlSwhCd_A1, scrnMsg.xxPopPrm_P8);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).xxAllLineAddr_A1, bizMsg.A.no(eventRowIndex).xxAllLineAddr_A1);
                scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtlSwhCd_A1);
            }
        }
    }
}
