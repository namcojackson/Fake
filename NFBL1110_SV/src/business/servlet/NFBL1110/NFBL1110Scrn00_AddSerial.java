/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1110.NFBL1110CMsg;
import business.servlet.NFBL1110.common.NFBL1110CommonLogic;
import business.servlet.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   CUSA            Y.Aikawa        Create          N/A
 * 2016/08/05   Fujitsu         T.Murai         Update          QC#12692
 * </pre>
 */
public class NFBL1110Scrn00_AddSerial extends S21CommonHandler implements NFBL1110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        // Mandatory check
        scrnMsg.addCheckItem(scrnMsg.ovrdSerNum_AD);
        scrnMsg.addCheckItem(scrnMsg.serNum_AD);
        scrnMsg.addCheckItem(scrnMsg.startDt_AD);
        scrnMsg.addCheckItem(scrnMsg.endDt_AD);
        scrnMsg.addCheckItem(scrnMsg.baseAmt_AD);
        scrnMsg.putErrorScreen();

        // Add 2016/08/05 QC#12692
        scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);

        // Check serial number existence 
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNum_A1)) {
                if (scrnMsg.serNum_AD.getValue().equals(scrnMsg.A.no(i).serNum_A1.getValue())) {
                    scrnMsg.serNum_AD.setErrorInfo(1, NFAM0070E, new String[] {"Same serial number", "screen"});
                    scrnMsg.addCheckItem(scrnMsg.serNum_AD);
                }
            }
        }
        scrnMsg.addCheckItem(scrnMsg.serNum_AD);
        scrnMsg.putErrorScreen();

        // Check Maximum Row Count
        if ((scrnMsg.A.length()/6*6) == scrnMsg.A.getValidCount()) {
            scrnMsg.serNum_AD.setErrorInfo(1, NFAM0072E, new String[] {"New serial", Integer.toString(scrnMsg.A.getValidCount())});
            scrnMsg.addCheckItem(scrnMsg.serNum_AD);
        }
        scrnMsg.addCheckItem(scrnMsg.serNum_AD);
        scrnMsg.putErrorScreen();

        if (Integer.parseInt(scrnMsg.startDt_AD.getValue()) > Integer.parseInt(scrnMsg.endDt_AD.getValue())) {
            scrnMsg.startDt_AD.setErrorInfo(1, NMAM8061E, new String[] {"End Date", "Start Date"});
            scrnMsg.endDt_AD.setErrorInfo(1, NMAM8061E, new String[] {"End Date", "Start Date"});
        }
        scrnMsg.addCheckItem(scrnMsg.startDt_AD);
        scrnMsg.addCheckItem(scrnMsg.endDt_AD);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        NFBL1110CMsg bizMsg = new NFBL1110CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        NFBL1110CMsg bizMsg  = (NFBL1110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Add Start 2016/08/05 QC#12692
        scrnMsg.addCheckItem(scrnMsg.serNum_AD);
        scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);
        scrnMsg.putErrorScreen();
        // Add End 2016/08/05 QC#12692

        /** Initialize input control */ 
        NFBL1110CommonLogic.initControl(this, scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.ovrdSerNum_AD);

    }
}
