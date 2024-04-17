/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1370;

import static business.servlet.NPAL1370.constant.NPAL1370Constant.BIZ_APP_ID;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_FRM_SWH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_FRM_WH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_TO_SWH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.DISPLAY_NM_CPY_TO_WH;
import static business.servlet.NPAL1370.constant.NPAL1370Constant.FUNCTION_CD_SEARCH;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1370.NPAL1370CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1370 Min Max Planning Copy Popup
 * Function Name : Common Close
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2015   CITS       Takeshi Tokutomi     Create          N/A
 *</pre>
 */
public class NPAL1370Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_FR) && ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_FR) && ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_TO) && ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_TO)) {
            String locationFrom = scrnMsg.rtlWhCd_FR.getValue() + scrnMsg.rtlSwhCd_FR.getValue();
            String locationTo = scrnMsg.rtlWhCd_TO.getValue() + scrnMsg.rtlSwhCd_TO.getValue();
            if (locationFrom.equals(locationTo)) {
                scrnMsg.rtlWhCd_FR.setErrorInfo(1, "NPAM1543E");
                scrnMsg.rtlSwhCd_FR.setErrorInfo(1, "NPAM1543E");
                scrnMsg.rtlWhCd_TO.setErrorInfo(1, "NPAM1543E");
                scrnMsg.rtlSwhCd_TO.setErrorInfo(1, "NPAM1543E");
            }
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_FR) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_FR) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_TO) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_TO)) {
            // do nothing
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_FR)) {
                scrnMsg.rtlWhCd_FR.setErrorInfo(1, "NPAM1536E", new String[] {DISPLAY_NM_CPY_FRM_WH});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_FR)) {
                scrnMsg.rtlSwhCd_FR.setErrorInfo(1, "NPAM1536E", new String[] {DISPLAY_NM_CPY_FRM_SWH});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_TO)) {
                scrnMsg.rtlWhCd_TO.setErrorInfo(1, "NPAM1536E", new String[] {DISPLAY_NM_CPY_TO_WH});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_TO)) {
                scrnMsg.rtlSwhCd_TO.setErrorInfo(1, "NPAM1536E", new String[] {DISPLAY_NM_CPY_TO_SWH});
            }
        }
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_FR);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_FR);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_TO);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_TO);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_FR) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_FR) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_TO) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_TO)) {
            return null;
        }

        NPAL1370CMsg bizMsg = new NPAL1370CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1370BMsg scrnMsg = (NPAL1370BMsg) bMsg;
        NPAL1370CMsg bizMsg = (NPAL1370CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_FR) && ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_FR) && ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_TO) && ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_TO)) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_FR);
            scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_FR);
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_TO);
            scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_TO);

            scrnMsg.putErrorScreen();

        }

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            // Copy from WH
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(param0, scrnMsg.rtlWhCd_FR);
            // Copy from SWH
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            ZYPEZDItemValueSetter.setValue(param1, scrnMsg.rtlSwhCd_FR);
            // Copy to WH
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            ZYPEZDItemValueSetter.setValue(param2, scrnMsg.rtlWhCd_TO);
            // Copy to SWH
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            ZYPEZDItemValueSetter.setValue(param3, scrnMsg.rtlSwhCd_TO);
            // Copy to Enabled Item Only
            EZDBStringItem param4 = (EZDBStringItem) params[4];
            if (ZYPCommonFunc.hasValue(scrnMsg.mrpEnblFlg)) {
                ZYPEZDItemValueSetter.setValue(param4, scrnMsg.mrpEnblFlg);
            }
        }
    }
}
