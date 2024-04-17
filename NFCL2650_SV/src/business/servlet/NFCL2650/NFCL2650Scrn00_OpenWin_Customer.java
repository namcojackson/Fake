/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650;

import static business.servlet.NFCL2650.constant.NFCL2650Constant.LOCATION;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.FROM_CD;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.FROM_NM;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.TO_CD;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.TO_NM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 *</pre>
 */
public class NFCL2650Scrn00_OpenWin_Customer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        String eventNm = getSubmitedFieldNm(ctx);
        if ((FROM_CD).equals(eventNm)) {
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_FR);
        } else if ((TO_CD).equals(eventNm)) {
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum_TO);
        } else if ((FROM_NM).equals(eventNm)) {
            scrnMsg.addCheckItem(scrnMsg.dsAcctNm_F2);
        } else if ((TO_NM).equals(eventNm)) {
            scrnMsg.addCheckItem(scrnMsg.dsAcctNm_T2);
        } else if ((LOCATION).equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.locNum); // MOD 2016/11/25 T.Murai [QC#13259] billToCustCd -> locNum
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        String acctCd = null;
        String acctNm = null;
        String location = null;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getSubmitedFieldNm(ctx));
        if ((FROM_CD).equals(scrnMsg.xxScrEventNm.getValue())) {
            acctCd = scrnMsg.dsAcctNum_FR.getValue();
        } else if ((TO_CD).equals(scrnMsg.xxScrEventNm.getValue())) {
            acctCd = scrnMsg.dsAcctNum_TO.getValue();
        } else if ((FROM_NM).equals(scrnMsg.xxScrEventNm.getValue())) {
            acctNm = scrnMsg.dsAcctNm_F2.getValue();
        } else if ((TO_NM).equals(scrnMsg.xxScrEventNm.getValue())) {
            acctNm = scrnMsg.dsAcctNm_T2.getValue();
        } else if ((LOCATION).equals(scrnMsg.xxScrEventNm.getValue())) {
            location = scrnMsg.locNum.getValue(); // MOD 2016/11/25 T.Murai [QC#13259] billToCustCd -> locNum
        }
        int i = 0;
        if (acctCd != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, acctCd);
        } else {
            scrnMsg.P.no(i++).xxPopPrm_P.clear();
        }

        if (acctNm != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, acctNm);
        } else {
            scrnMsg.P.no(i++).xxPopPrm_P.clear();
        }

        // START 2016/11/25 [QC#13259, MOD]
        // scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 2
        if (location != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, location); // i = 2
        } else {
            scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 2
        }
        // END   2016/11/25 [QC#13259, MOD]
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 3
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 4
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 5
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 6
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 7
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 8
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 9
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 10
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 11
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, DISP_HRCH_ACCT_CD_BILL); // i = 12
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 13
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 14
        // START 2016/11/25 [QC#13259, MOD]
        // if (billToCust != null) {
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, billToCust);
        // } else {
        // scrnMsg.P.no(i++).xxPopPrm_P.clear();
        // }
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 15
        // END   2016/11/25 [QC#13259, MOD]
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 16
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 17
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 18
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 19
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 20
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 21
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 22
        scrnMsg.P.no(i++).xxPopPrm_P.clear(); // i = 23

        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[24];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm_P;
        }
        setArgForSubScreen(params);
    }
}
