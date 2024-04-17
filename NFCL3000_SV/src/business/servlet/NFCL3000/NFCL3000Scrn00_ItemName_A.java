/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         T.Tanaka        Create          N/A
 * 2017/12/25   Hitachi         E.Kameishi      Update          QC#20312
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 *</pre>
 */
public class NFCL3000Scrn00_ItemName_A extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        if(!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).mdseCd_A1)) {
            scrnMsg.A.no(idx).mdseNm_A1.clear();
            return;
        }

        scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_A1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        int idx = scrnMsg.xxCellIdx.getValueInt();
        if(!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).mdseCd_A1)) {
            return null;
        }

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        int idx = scrnMsg.xxCellIdx.getValueInt();
        if(!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).mdseCd_A1)) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/12/25 E.Kameishi [QC#20312,ADD]
        NFCL3000CommonLogic.taxAdjustmentProtect(scrnMsg);
        // END   2017/12/25 E.Kameishi [QC#20312,ADD]

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        NFCL3000CommonLogic.setQtyIndispensable(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]

        scrnMsg.A.setCheckParam(new String[] {"mdseCd_A1"}, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();

    }
}
