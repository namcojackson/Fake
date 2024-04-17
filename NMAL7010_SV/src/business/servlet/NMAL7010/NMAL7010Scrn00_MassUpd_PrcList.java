/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PA;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PB;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PD;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PF;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PH;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.CHK_PI;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RATE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_LEASE_RTRN_FEE;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_QTY_DISC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_TI;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.ZZM9000E;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_MassUpd_PrcList
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2018/12/18   Fujitsu         K.Kato          Update          QC#29661
 *</pre>
 */
public class NMAL7010Scrn00_MassUpd_PrcList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.effThruDt_DH)) {
            scrnMsg.effThruDt_DH.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.effThruDt_DH.getNameForMessage()});
        }

        scrnMsg.addCheckItem(scrnMsg.effThruDt_DH);

        scrnMsg.putErrorScreen();

        NMAL7010CommonLogic.chkSelectPricListDetail(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2018/12/18 QC#29661 Mod Start
        //return null;
        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2018/12/18 QC#29661 Mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        // 2018/12/18 QC#29661 Mod Start
        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //List<Integer> selectRows = new ArrayList<Integer>();
        //if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, CHK_PA, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).effThruDt_PA, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, CHK_PB, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).effThruDt_PB, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, CHK_PC, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).effThruDt_PC, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.D, CHK_PD, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(idx).effThruDt_PD, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.E, CHK_PE, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(idx).effThruDt_PE, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_LEASE_RATE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.F, CHK_PF, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(idx).effThruDt_PF, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_LEASE_RTRN_FEE.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.G, CHK_PG, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.G.no(idx).effThruDt_PG, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_TI.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.H, CHK_PH, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(idx).effThruDt_PH, scrnMsg.effThruDt_DH);
        //    }
        //} else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
        //    selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.I, CHK_PI, ZYPConstant.FLG_ON_Y);
        //    for (int idx : selectRows) {
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.I.no(idx).effThruDt_PI, scrnMsg.effThruDt_DH);
        //    }
        //}
        // 2018/12/18 QC#29661 Mod End
    }
}
