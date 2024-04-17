/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_0;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1510.constant.NPAL1510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : NWAL1130
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2016/08/12   CITS            R.Shimamoto     Update          QC#13136
 *</pre>
 */
public class NPAL1510_NWAL1130 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;

        if ("OpenWin_Supplier".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.P.no(NPAL1510Constant.IDX_0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.P.no(NPAL1510Constant.IDX_1).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.P.no(NPAL1510Constant.IDX_2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm, scrnMsg.P.no(NPAL1510Constant.IDX_3).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
        } else if ("CUSAEventHist".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            int rowNum = new Integer(scrnMsg.xxPopPrm_P0.getValue()).intValue();
            scrnMsg.setFocusItem(scrnMsg.A.no(rowNum).vndIssPoOrdNum_A1);
        } else if ("OpenWin_Buyer".equals(scrnMsg.xxPopPrm_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.poSubmtPsnCd, scrnMsg.P.no(IDX_0).xxComnScrColValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm, scrnMsg.P.no(IDX_1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.fullPsnNm);
        }
    }
}
