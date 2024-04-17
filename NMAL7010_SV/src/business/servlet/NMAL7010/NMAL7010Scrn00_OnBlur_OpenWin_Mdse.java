/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SPLY_PGM;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_SPEC_CHRG;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_SVC_TIER;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/25   Fujitsu         R.Nakamura      Create          QC#3934
 *</pre>
 */
public class NMAL7010Scrn00_OnBlur_OpenWin_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        int idx = getButtonSelectNumber();
        EZDBStringItem ezdStrItemMdse = null;

        if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.B.no(idx).mdseCd_PB;
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.C.no(idx).mdseCd_PC;
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.D.no(idx).mdseCd_PD;
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.E.no(idx).mdseCd_PE;
        }

        if (null != ezdStrItemMdse) {
            scrnMsg.addCheckItem(ezdStrItemMdse);

            if (!ZYPCommonFunc.hasValue(ezdStrItemMdse)) {
                ezdStrItemMdse.setErrorInfo(1, ZZM9000E, new String[] {ezdStrItemMdse.getNameForMessage() });
                scrnMsg.addCheckItem(ezdStrItemMdse);
            }

            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID("NMAL7010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setResult(ZYPConstant.FLG_OFF_N);

        int idx = getButtonSelectNumber();
        EZDBStringItem ezdStrItemMdse = null;
        EZDBStringItem ezdStrItemFocusStr = null;
        EZDBBigDecimalItem ezdStrItemFocusBD = null;
        EZDBDateItem ezdStrItemFocusDt = null;

        if (TAB_PRC_LIST_VAL_SVC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.B.no(idx).mdseCd_PB;
            ezdStrItemFocusBD = scrnMsg.B.no(idx).quotRevAmt_PB;
        } else if (TAB_PRC_LIST_VAL_SVC_TIER.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.C.no(idx).mdseCd_PC;
            ezdStrItemFocusDt = scrnMsg.C.no(idx).effFromDt_PC;
        } else if (TAB_PRC_LIST_VAL_SVC_SPEC_CHRG.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.D.no(idx).mdseCd_PD;
            ezdStrItemFocusStr = scrnMsg.D.no(idx).prcAddlChrgTpCd_PD;
        } else if (TAB_PRC_LIST_VAL_SPLY_PGM.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            ezdStrItemMdse = scrnMsg.E.no(idx).mdseCd_PE;
            ezdStrItemFocusStr = scrnMsg.E.no(idx).prcSvcZoneCd_PE;
        }

        if (null != ezdStrItemMdse) {
            scrnMsg.addCheckItem(ezdStrItemMdse);
            scrnMsg.putErrorScreen();

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg_H1.getValue())) {
                setResult(ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_Mdse");
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

                setArgForSubScreen(NMAL7010CommonLogic.setArgumentNMAL6800(scrnMsg, scrnMsg.xxScrEventNm_DH.getValue(), idx));

                return;
            }

            if (null != ezdStrItemFocusStr) {
                scrnMsg.setFocusItem(ezdStrItemFocusStr);
            } else if (null != ezdStrItemFocusBD) {
                scrnMsg.setFocusItem(ezdStrItemFocusBD);
            } else if (null != ezdStrItemFocusDt) {
                scrnMsg.setFocusItem(ezdStrItemFocusDt);
            }
            scrnMsg.xxScrEventNm_DH.clear();
            scrnMsg.xxCellIdx.clear();
        }

    }
}
