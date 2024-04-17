/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3200;

import static business.servlet.NMAL3200.constant.NMAL3200Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL3200.constant.NMAL3200Constant.TAB_UPLOAD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * Function Name : Return to PopUp
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 */
public class NMAL3200_NWAL1130 extends S21CommonHandler {

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

        NMAL3200BMsg scrnMsg = (NMAL3200BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (TAB_UPLOAD.equals(scrnMsg.xxDplyTab.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mktgFldMapNm_DB, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNmColDfnNm, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddrColDfnNm, scrnMsg.R.no(2).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddrColDfnNm, scrnMsg.R.no(3).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.stCdColDfnNm, scrnMsg.R.no(4).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.postCdColDfnNm, scrnMsg.R.no(5).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCdColDfnNm, scrnMsg.R.no(6).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.mktgFldMapNm_DB);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mktgFldMapNm_DL, scrnMsg.R.no(0).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.mktgFldMapNm_DL);
            }
        }
    }
}
