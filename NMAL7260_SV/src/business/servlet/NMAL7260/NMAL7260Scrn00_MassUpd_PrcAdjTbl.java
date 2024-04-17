/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.NMAM8190E;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.ZZM9000E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/06   Fujitsu         H.Nagashima     Create          QC#22595
 * 2018/12/18   Fujitsu         Hd.Sugawara     Update          QC#29661
 *</pre>
 */
public class NMAL7260Scrn00_MassUpd_PrcAdjTbl extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.effThruDt_MU)) {
            scrnMsg.effThruDt_MU.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.effThruDt_MU.getNameForMessage()});
        }

        scrnMsg.addCheckItem(scrnMsg.effThruDt_MU);
        scrnMsg.putErrorScreen();

        // Del Start 2018/12/18 QC#29661
        //List<Integer> selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, NMAL7260Constant.CHK_B, ZYPConstant.FLG_ON_Y);
        // Del End 2018/12/18 QC#29661
        int rownum = scrnMsg.B.getValidCount();

        if (rownum == 0) {
            scrnMsg.setMessageInfo(NMAM8190E);
            throw new EZDFieldErrorException();
        }
        // Del Start 2018/12/18 QC#29661
        //if (selectRows.isEmpty()) {
        //    scrnMsg.setMessageInfo(NMAM8054E);
        //    throw new EZDFieldErrorException();
        //}
        // Del End 2018/12/18 QC#29661

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Mod Start 2018/12/18 QC#29661
        //return null;
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // Mod End 2018/12/18 QC#29661
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        // Mod Start 2018/12/18 QC#29661
        //List<Integer> selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, NMAL7260Constant.CHK_B, ZYPConstant.FLG_ON_Y);
        //for (int idx : selectRows) {
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).effThruDt_B1, scrnMsg.effThruDt_MU);
        //}
        NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Mod End 2018/12/18 QC#29661

    }
}
