/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0200.NFAL0200CMsg;
import business.servlet.NFAL0200.common.NFAL0200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   CSAI            K.Uramori       Create          N/A
 * 2016/10/11   Hitachi         K.Kojima        Update          QC#12824
 * 2016/11/25   Hitachi         J.Kim           Update          QC#16240
 *</pre>
 */
public class NFAL0200_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFAL0200");

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        NFAL0200CMsg bizMsg = new NFAL0200CMsg();
        bizMsg.setBusinessID("NFAL0200");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;
        NFAL0200CMsg bizMsg = (NFAL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0200CommonLogic.initialize(this, scrnMsg);

        // set fixed value
        setValue(scrnMsg.coaCmpyCd, getGlobalCompanyCode());

        // set focus on COA_BR_CD
        scrnMsg.setFocusItem(scrnMsg.coaBrCd);

    }

    // START 2016/10/11 K.Kojima [QC#12824,ADD]
    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) arg0;
        scrnMsg.coaBrCd.setNameForMessage("Branch");
        scrnMsg.coaCcCd.setNameForMessage("Cost Center");
        scrnMsg.coaAcctCd.setNameForMessage("Natural Account");
        scrnMsg.coaProdCd.setNameForMessage("Product Code");
        scrnMsg.coaChCd.setNameForMessage("Sales Channel");
        // START 2016/11/25 J.Kim [QC#16240,MOD]
        // scrnMsg.coaAfflCd.setNameForMessage("Affiliate Code");
        scrnMsg.coaAfflCd.setNameForMessage("Intercompany Code");
        // END 2016/11/25 J.Kim [QC#16240,MOD] 
        scrnMsg.coaProjCd.setNameForMessage("Merchandise Type");
        scrnMsg.coaExtnCd.setNameForMessage("Business Unit");

        // START 2016/11/02 J.Kim [QC#13443,ADD]
        for (int index = 0; index < scrnMsg.C.length(); index++) {
            NFAL0200_CBMsg cbMsg = scrnMsg.C.no(index);
            cbMsg.contrCoaBrCd_C.setNameForMessage("Contract Branch");
            cbMsg.coaBrGeoTxt_C.setNameForMessage("GEO Code");
        }
        // END 2016/11/02 J.Kim [QC#13443,ADD]
    }
    // END 2016/10/11 K.Kojima [QC#12824,ADD]
}
