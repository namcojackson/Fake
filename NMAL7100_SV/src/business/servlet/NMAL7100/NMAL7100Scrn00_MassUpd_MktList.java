/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 * 2016/11/10   Fujitsu         W.Honda         Update          QC#15842
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100Scrn00_MassUpd_MktList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.effThruDt_DH)) {
            scrnMsg.effThruDt_DH.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.effThruDt_DH.getNameForMessage()});
        }

        scrnMsg.addCheckItem(scrnMsg.effThruDt_DH);

        scrnMsg.putErrorScreen();

        // START QC#15842 11/10/2016 DEL
//        NMAL7100CommonLogic.chkSelectPricListDetail(scrnMsg);
        // END QC#15842 11/10/2016 DEL
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START QC#15842 11/10/2016 MOD
//        return null;

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        NMAL7100CMsg bizMsg = new NMAL7100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
      // END QC#15842 11/10/2016 MOD
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        // START QC#15842 11/10/2016 MOD
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;
//        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_DA", ZYPConstant.FLG_ON_Y);
//
//        for (int idx : selectRows) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).effThruDt_DA, scrnMsg.effThruDt_DH);
//        }

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011
        // END QC#15842 11/10/2016 MOD

    }
}
