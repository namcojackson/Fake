/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0040.ZZIL0040CMsg;
import business.servlet.ZZIL0040.common.ZZIL0040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ZZIL0040Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.intfcId_H);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_SF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_S1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_ST);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_S2);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_EF);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_E1);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_ET);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_E2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;

        String regDtFrom = scrnMsg.xxFromDt_SF.getValue();
        String regDtTo = scrnMsg.xxToDt_ST.getValue();
        String updDtFrom = scrnMsg.xxFromDt_EF.getValue();
        String updDtTo = scrnMsg.xxToDt_ET.getValue();

        String regTmFrom = scrnMsg.xxHrs_S1.getValue();
        String regTmTo = scrnMsg.xxHrs_S2.getValue();
        String updTmFrom = scrnMsg.xxHrs_E1.getValue();
        String updTmTo = scrnMsg.xxHrs_E2.getValue();

        // single category check
        if (regDtFrom.length() > 0 && regTmFrom.length() == 0) {
            scrnMsg.xxHrs_S1.setErrorInfo(1, "ZZM9032E", new String[] {"Started Time From" });
        } else if (regDtFrom.length() == 0 && regTmFrom.length() > 0) {
            scrnMsg.xxFromDt_SF.setErrorInfo(1, "ZZM9032E", new String[] {"Started Date From" });
        }

        if (regDtTo.length() > 0 && regTmTo.length() == 0) {
            scrnMsg.xxHrs_S2.setErrorInfo(1, "ZZM9032E", new String[] {"Started Time To" });
        } else if (regDtTo.length() == 0 && regTmTo.length() > 0) {
            scrnMsg.xxToDt_ST.setErrorInfo(1, "ZZM9032E", new String[] {"Started Date To" });
        }

        if (updDtFrom.length() > 0 && updTmFrom.length() == 0) {
            scrnMsg.xxHrs_E1.setErrorInfo(1, "ZZM9032E", new String[] {"End Time From" });
        } else if (updDtFrom.length() == 0 && updTmFrom.length() > 0) {
            scrnMsg.xxFromDt_EF.setErrorInfo(1, "ZZM9032E", new String[] {"End Date From" });
        }

        if (updDtTo.length() > 0 && updTmTo.length() == 0) {
            scrnMsg.xxHrs_E2.setErrorInfo(1, "ZZM9032E", new String[] {"End Time To" });
        } else if (updDtTo.length() == 0 && updTmTo.length() > 0) {
            scrnMsg.xxToDt_ET.setErrorInfo(1, "ZZM9032E", new String[] {"End Date To" });
        }

        // correlative check
        String strFrom = regDtFrom + regTmFrom;
        String strTo = regDtTo + regTmTo;

        if (regDtTo.length() > 0 && regTmTo.length() > 0) {
            int comp = strFrom.compareTo(strTo);

            if (comp > 0) {
                scrnMsg.xxToDt_ST.setErrorInfo(1, "ZZZM9010E", new String[] {"Started Date From", "Started Date To" });
            } else if (comp == 0) {
                scrnMsg.xxHrs_S2.setErrorInfo(1, "ZZZM9010E", new String[] {"Started Time From", "Started Time To" });
            }
        }

        strFrom = updDtFrom + updTmFrom;
        strTo = updDtTo + updTmTo;

        if (updDtTo.length() > 0 && updTmTo.length() > 0) {

            int comp = strFrom.compareTo(strTo);
            if (comp > 0) {
                scrnMsg.xxToDt_ET.setErrorInfo(1, "ZZZM9010E", new String[] {"End Date From", "End Date To" });
            } else if (comp == 0) {
                scrnMsg.xxHrs_E2.setErrorInfo(1, "ZZZM9010E", new String[] {"End Time From", "End Time To" });
            }
        }

        boolean errFlg = false;
        errFlg |= scrnMsg.xxHrs_S1.isError();
        errFlg |= scrnMsg.xxHrs_S2.isError();
        errFlg |= scrnMsg.xxHrs_E1.isError();
        errFlg |= scrnMsg.xxHrs_E2.isError();
        errFlg |= scrnMsg.xxFromDt_SF.isError();
        errFlg |= scrnMsg.xxToDt_ST.isError();
        errFlg |= scrnMsg.xxFromDt_EF.isError();
        errFlg |= scrnMsg.xxToDt_ET.isError();

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        if (errFlg) {
            return null;
        }

        ZZIL0040CMsg bizMsg = new ZZIL0040CMsg();
        bizMsg.setBusinessID("ZZIL0040");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;
        ZZIL0040CMsg bizMsg = (ZZIL0040CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        ZZIL0040CommonLogic.setDispDate00(scrnMsg, bizMsg);
        ZZIL0040CommonLogic.dspScrn00(scrnMsg, this);

        scrnMsg.addCheckItem(scrnMsg.xxFromDt_SF);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_ST);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_EF);
        scrnMsg.addCheckItem(scrnMsg.xxToDt_ET);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_S1);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_S2);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_E1);
        scrnMsg.addCheckItem(scrnMsg.xxHrs_E2);
        scrnMsg.putErrorScreen();
    }
}
