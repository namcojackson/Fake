/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
import business.blap.NMAL6700.NMAL6700CMsg;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         N.Sugiura       Create          CSA
 * 2016/04/06   Fujitsu         N.Sugiura       Update          QC#6633
 * 2016/04/13   SRAA            Y.Chen          Update          QC#6011
 * 2016/09/06   Fujitsu         N.Sugiura       Update          QC#6248
 * 2017/09/11   Hitachi         J.Kim           Update          QC#20359
 *</pre>
 */
public class NMAL6700_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (NMAL6700Constant.BTN_OPENWIN_ADDR_SRCH[0].equals(scrEventNm)) {
            NMAL6700CMsg bizMsg = new NMAL6700CMsg();
            bizMsg.setBusinessID("NMAL6700");
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int index = getButtonSelectNumber();

        if ("OpenWin_BillTo".equals(scrEventNm)) {
            // START 2017/09/11 J.Kim [QC#20359,MOD]
            if (!"CMN_Close".equals(getLastGuard())) {
                scrnMsg.F.no(index).dsDefBillToCd_F1.setValue(scrnMsg.P.no(15).xxPopPrm.getValue());
            }
            // END 2017/09/11 J.Kim [QC#20359,MOD]
            scrnMsg.setFocusItem(scrnMsg.F.no(index).dsDefBillToCd_F1);

        } else if ("OpenWin_ShipTo".equals(scrEventNm)) {
            // START 2017/09/11 J.Kim [QC#20359,MOD]
            if (!"CMN_Close".equals(getLastGuard())) {
                scrnMsg.F.no(index).dsDefShipToCd_F1.setValue(scrnMsg.P.no(16).xxPopPrm.getValue());
            }
            // END 2017/09/11 J.Kim [QC#20359,MOD]
            scrnMsg.setFocusItem(scrnMsg.F.no(index).dsDefShipToCd_F1);

        } else if (NMAL6700Constant.BTN_OPENWIN_ADDR_SRCH[0].equals(scrEventNm)) {
            NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NMAL6700CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
            NMAL6700CommonLogic.setBgColor(scrnMsg);
        } else {
            int selectIdx = getButtonSelectNumber();
            scrnMsg.xxCellIdx.setValue(selectIdx);
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(0).xxPopPrm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(selectIdx).dsAcctNum_C1, scrnMsg.P.no(0).xxPopPrm);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_P)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(selectIdx).dsAcctNm_C1, scrnMsg.dsAcctNm_P.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.C.no(selectIdx).dsAcctNum_C1);
        }
    }
}
