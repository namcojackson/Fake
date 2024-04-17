/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_NM_FOR_TECH;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_0;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.IDX_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request Entry
 * Function Name : Return Action from NWAL1130
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1080_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (TBL_NM_FOR_TECH.equals(scrnMsg.xxTblNm_P1.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rqstTechTocCd_H1, scrnMsg.R.no(IDX_0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_H1, scrnMsg.R.no(IDX_1).xxComnScrColValTxt.getValue());

                NPAL1080CMsg bizMsg = new NPAL1080CMsg();
                bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;

            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
            if (TBL_NM_FOR_TECH.equals(scrnMsg.xxTblNm_P1.getValue())) {
                NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_AC);
            } else {
                for (int i = 0; i < scrnMsg.R.length(); i++) {
                    if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("PRNT_VND_CD")) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).prntVndCd_D1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    } else if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("PRNT_VND_NM")) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).prntVndNm_D1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    } else if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("LOC_NM")) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(getButtonSelectNumber()).locNm_D1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    }
                }
            }
        }
    }
}
