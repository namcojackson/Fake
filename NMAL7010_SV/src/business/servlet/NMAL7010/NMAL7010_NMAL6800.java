/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL7010.NMAL7010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010_NMAL6800
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/10   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7010_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        if (scrnMsg.xxCellIdx.getValueInt() < 0) {
            return null;
        }


        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        if (scrnMsg.xxCellIdx.getValueInt() < 0) {
            scrnMsg.xxScrEventNm_DH.clear();
            scrnMsg.xxCellIdx.clear();
            return;
        }

        NMAL7010CMsg bizMsg  = (NMAL7010CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).prcQlfyValTxt_PA);

        } else if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if ("OpenWin_Mdse".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_PB);

            } else if ("OpenWin_PrcListMdse".equals(scrnMsg.xxScrEventNm_DH.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).prcListMdseCd_PB);
            }
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_PC);

        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.D.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_PD);

        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.E.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_PE);
        }

        scrnMsg.xxScrEventNm_DH.clear();
        scrnMsg.xxCellIdx.clear();
    }
}
