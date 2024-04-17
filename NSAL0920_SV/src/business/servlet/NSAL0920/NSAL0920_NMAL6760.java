/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0920;

import static business.servlet.NSAL0920.constant.NSAL0920Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0920.NSAL0920CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Billing Search
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/01/06   Hitachi         T.Tomita        Update          QC#1029
 *</pre>
 */
public class NSAL0920_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/01/06 T.Tomita [QC#1029, ADD]
        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        NSAL0920CMsg bizMsg = new NSAL0920CMsg();
        if (!"CMN_Close".equals(getLastGuard())) {
            if (OPENWIN_ACCTCUST.equals(scrnMsg.xxScrEventNm.getValue()) || OPENWIN_ACCTCUSTNM.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.dsAcctNum, scrnMsg.xxPopPrm_00);
                setValue(scrnMsg.dsAcctNm, scrnMsg.xxPopPrm_01);
            } else if (OPENWIN_BILLTOLOC.equals(scrnMsg.xxScrEventNm.getValue()) || OPENWIN_BILLTOLOCNM.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.locNum, scrnMsg.xxPopPrm_15);
                bizMsg.setBusinessID(BIZ_ID);
                bizMsg.setFunctionCode(FUNC_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        // END 2016/01/06 T.Tomita [QC#1029, ADD]

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/01/06 T.Tomita [QC#1029, MOD]
        NSAL0920BMsg scrnMsg = (NSAL0920BMsg) bMsg;
        NSAL0920CMsg bizMsg = (NSAL0920CMsg) cMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
//            setValue(scrnMsg.dsAcctNum, scrnMsg.xxPopPrm_00);
//            setValue(scrnMsg.dsAcctNm, scrnMsg.xxPopPrm_01);
            if (OPENWIN_BILLTOLOC.equals(scrnMsg.xxScrEventNm.getValue()) || OPENWIN_BILLTOLOCNM.equals(scrnMsg.xxScrEventNm.getValue())) {
                setValue(scrnMsg.locNm, bizMsg.locNm);
            }
        }
        if (OPENWIN_ACCTCUST.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.dsAcctNum);
        } else if (OPENWIN_ACCTCUSTNM.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.dsAcctNm);
        } else if (OPENWIN_BILLTOLOC.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.locNum);
        } else if (OPENWIN_BILLTOLOCNM.equals(scrnMsg.xxScrEventNm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.locNm);
        }
        scrnMsg.xxScrEventNm.clear();
        // END 2016/01/06 T.Tomita [QC#1029, MOD]
    }
}
