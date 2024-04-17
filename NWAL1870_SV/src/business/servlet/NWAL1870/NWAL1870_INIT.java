/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1870;
import static business.servlet.NWAL1870.constant.NWAL1870Constant.BIZ_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1870.NWAL1870CMsg;
import business.servlet.NWAL1870.common.NWAL1870CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1870_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Fujitsu         M.Suzuki        Create          N/A
 * 2016/09/08   Fujitsu         R.Nakamura      Update          QC#11614
 *</pre>
 */
public class NWAL1870_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //doNothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1870BMsg scrnMsg = (NWAL1870BMsg) bMsg;
        // Add Start 2016/09/08 QC#11614
        NWAL1870CMsg bizMsg = new NWAL1870CMsg();
        // Add End 2016/09/08 QC#11614
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params[0] != null && params[0] instanceof EZDBStringItem) {
                EZDBStringItem param00 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_I, param00);
            }

            // Del Start 2016/09/08 QC#11614
//            if (params[1] != null && params[1] instanceof EZDBStringItem) {
//                EZDBStringItem param00 = (EZDBStringItem) params[1];
//                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseNm_I, param00);
//            }
            // Del End 2016/09/08 QC#11614

            if (params[2] != null && params[2] instanceof EZDBStringItem) {
                EZDBStringItem param00 = (EZDBStringItem) params[2];
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_S, param00);
            }

            // Del Start 2016/09/08 QC#11614
//            if (params[3] != null && params[3] instanceof EZDBStringItem) {
//                EZDBStringItem param00 = (EZDBStringItem) params[3];
//                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseNm_S, param00);
//            }
            // Del End 2016/09/08 QC#11614

            // Add Start 2016/09/08 QC#11614
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            // Add End 2016/09/08 QC#11614
        }
//        return null;
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1870BMsg scrnMsg = (NWAL1870BMsg) bMsg;
        NWAL1870CMsg bizMsg = (NWAL1870CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1870CommonLogic.initCmnBtnProp(this);
        scrnMsg.mdseCd_I.setInputProtected(true);
        // Mod Start 2016/09/08 QC#11614
//        scrnMsg.mdseNm_I.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_I.setInputProtected(true);
        // Mod End 2016/09/08 QC#11614
        scrnMsg.mdseCd_S.setInputProtected(true);
        // Mod Start 2016/09/08 QC#11614
//        scrnMsg.mdseNm_S.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_S.setInputProtected(true);
        // Mod End 2016/09/08 QC#11614
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        //doNothing
    }
}
