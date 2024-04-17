/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2015/11/20   Hitachi         T.Tomita        Update          QC#959
 * 2015/11/24   Hitachi         T.Tomita        Update          QC#948
 * 2016/01/04   Hitachi         T.Tomita        Update          QC#1029
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 *</pre>
 */
public class NSAL0010_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/01/04 T.Tomita [QC#1029, MOD]
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
//        if (!"CMN_Close".equals(getLastGuard())) {
//            // START 2015/11/20 T.Tomita [QC#959, MOD]
//            setValue(scrnMsg.billToAcctNum_M2, scrnMsg.xxPopPrm_00);
//            // END 2015/11/20 T.Tomita [QC#959, MOD]
//            // START 2015/11/24 T.Tomita [QC#948, MOD]
//            setValue(scrnMsg.dsAcctNm_M2, scrnMsg.xxPopPrm_01);
//            // END 2015/11/24 T.Tomita [QC#948, MOD]
//        }
//
//        bizMsg.setBusinessID(BUSINESS_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        // END 2016/01/04 T.Tomita [QC#1029, MOD]
        // START 2016/01/04 T.Tomita [QC#1029, ADD]
        return null;
        // END 2016/01/04 T.Tomita [QC#1029, ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        // START 2016/01/04 T.Tomita [QC#1029, DEL]
//        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;
//
//        if (bizMsg != null) {
//            EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        }
        // END 2016/01/04 T.Tomita [QC#1029, DEL]
        // START 2016/01/04 T.Tomita [QC#1029, ADD]
        getArgForSubScreen();
        if (!"CMN_Close".equals(getLastGuard())) {
            String address = createAddress(scrnMsg);
            // START 2016/05/16 T.Tomita [QC#4578, MOD]
            if (OPENWIN_OWNERACCT.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.ownrAcctNum_M1, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm_M1, scrnMsg.xxPopPrm_01);
//                setValue(scrnMsg.ownrLocNum_M1, scrnMsg.xxPopPrm_15);
//                setValue(scrnMsg.xxComnScrColValTxt_M1, address);
            } else if (OPENWIN_BILLTOCUST.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.billToAcctNum_M2, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm_M2, scrnMsg.xxPopPrm_01);
                setValue(scrnMsg.indBillToLocNum_M2, scrnMsg.xxPopPrm_02);
                setValue(scrnMsg.xxComnScrColValTxt_M2, address);
            } else if (OPENWIN_CURLOC.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.curLocAcctNum_M3, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm_M3, scrnMsg.xxPopPrm_01);
                setValue(scrnMsg.indCurLocNum_M3, scrnMsg.xxPopPrm_02);
                setValue(scrnMsg.xxComnScrColValTxt_M3, address);
            }
            // END 2016/05/16 T.Tomita [QC#4578, MOD]
            scrnMsg.xxScrEventNm.clear();
        }
        // END 2016/01/04 T.Tomita [QC#1029, ADD]
    }

    // START 2016/01/04 T.Tomita [QC#1029, ADD]
    private String createAddress(NSAL0010BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();
        appendValue(sb, scrnMsg.xxPopPrm_04, null);
        appendValue(sb, scrnMsg.xxPopPrm_17, SPACE);
        appendValue(sb, scrnMsg.xxPopPrm_18, SPACE);
        appendValue(sb, scrnMsg.xxPopPrm_19, SPACE);
        appendValue(sb, scrnMsg.xxPopPrm_05, COMMA);
        appendValue(sb, scrnMsg.xxPopPrm_06, COMMA);
        if (hasValue(scrnMsg.xxPopPrm_06)) {
            appendValue(sb, scrnMsg.xxPopPrm_07, null);
        } else {
            appendValue(sb, scrnMsg.xxPopPrm_07, COMMA);
        }
        return sb.toString();
    }

    private void appendValue(StringBuffer sb, EZDBStringItem val, String split) {
        if (hasValue(val)) {
            if (split != null && sb.length() > 0) {
                sb.append(split);
            }
            sb.append(val.getValue());
        }
    }
    // END 2016/01/04 T.Tomita [QC#1029, ADD]
}
