/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/07/2018   CITS            T.Tokutomi      Create          QC#18461-Sol#085
 * 03/12/2018   Fujitsu         S.Ohki          Update          QC#29461
 * 03/02/2023   Hitachi         TZ.Win          Update          QC#61160
 * </pre>
 */
public class NLAL2030Scrn00_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        // START 2023/03/02 TZ.Win [QC#61160, MOD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_AP) //
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd_AP)
                && !ZYPCommonFunc.hasValue(scrnMsg.thirdPtyDspTpCd_AP)) {
        // END 2023/03/02 TZ.Win [QC#61160, MOD]
            // START 2018/12/03 S.Ohki [QC#29461,MOD]
//            scrnMsg.rtlWhCd_AP.setErrorInfo(1, "TODO");
//            scrnMsg.thirdPtyDspTpCd_AP.setErrorInfo(1, "TODO");
            // START 2023/03/02 TZ.Win [QC#61160, MOD]
        	String fieldName = scrnMsg.rtlWhCd_AP.getNameForMessage() + " or " + scrnMsg.rtlSwhCd_AP.getNameForMessage() + " or " + scrnMsg.thirdPtyDspTpCd_AP.getNameForMessage();
        	// END 2023/03/02 TZ.Win [QC#61160, MOD]
            scrnMsg.rtlWhCd_AP.setErrorInfo(1, NLAL2030Constant.ZZM9000E, new String[]{ fieldName });
            scrnMsg.thirdPtyDspTpCd_AP.setErrorInfo(1, NLAL2030Constant.ZZM9000E, new String[]{ fieldName });
            // END 2018/12/03 S.Ohki [QC#29461,MOD]
            // START 2023/03/02 TZ.Win [QC#61160, ADD]
            scrnMsg.rtlSwhCd_AP.setErrorInfo(1, NLAL2030Constant.ZZM9000E, new String[]{ fieldName });
            // END 2023/03/02 TZ.Win [QC#61160, ADD]
        }

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_AP);
        scrnMsg.addCheckItem(scrnMsg.thirdPtyDspTpCd_AP);
        // START 2023/03/02 TZ.Win [QC#61160, ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_AP);
        // END 2023/03/02 TZ.Win [QC#61160, ADD]

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        NLAL2030CMsg bizMsg = new NLAL2030CMsg();
        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_AP);
        scrnMsg.addCheckItem(scrnMsg.thirdPtyDspTpCd_AP);
        // START 2023/03/02 TZ.Win [QC#61160, ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_AP);
        // END 2023/03/02 TZ.Win [QC#61160, ADD]

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).rtlWhCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).thirdPtyDspTpCd_B1);
            // START 2023/03/02 TZ.Win [QC#61160, ADD]
            scrnMsg.addCheckItem(scrnMsg.B.no(i).rtlSwhCd_B1);
            // END 2023/03/02 TZ.Win [QC#61160, ADD]
        }
        scrnMsg.putErrorScreen();
    }
}
