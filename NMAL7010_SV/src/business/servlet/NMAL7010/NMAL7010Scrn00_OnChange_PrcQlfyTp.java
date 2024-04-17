/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_EQUIP;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.TAB_PRC_LIST_VAL_QTY_DISC;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;
import business.servlet.NMAL7010.constant.NMAL7010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_OnChange_PrcQlfyTp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/24   Fujitsu         M.Nakamura      Create          N/A
 * 2016/03/22   Fujitsu         Y.Kanefusa      Update          QC#4767
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2017/02/24   Fujitsu         R.Nakamura      Update          QC#16011
 * 2018/04/06   Fujitsu         R.Nakamura      Update          QC#22556
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 *</pre>
 */
public class NMAL7010Scrn00_OnChange_PrcQlfyTp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Mod Start 2018/04/09 QC#22556
//        return null;

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // Mod End 2018/04/09 QC#22556
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        // Add Start 2018/04/09 QC#22556
        NMAL7010CMsg bizMsg  = (NMAL7010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add End 2018/04/09 QC#22556

        int selectIdx = getButtonSelectNumber();
        // Add Start 2017/02/24 QC#16011
        boolean updateAuthFlg = NMAL7010CommonLogic.updateAuthority(getUserProfileService());
        // Add End 2017/02/24 QC#16011
        if (TAB_PRC_LIST_VAL_EQUIP.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (selectIdx < 0) {
                // Mod Start 2017/02/24 QC#16011
//                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.prcQlfyTpCd_D1.getValue(), scrnMsg);
                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.prcQlfyTpCd_D1.getValue(), scrnMsg, updateAuthFlg);
                // Mod End 2017/02/24 QC#16011
            } else {
                scrnMsg.A.no(selectIdx).prcQlfyValTxt_PA.clear();
                // Mod Start 2018/07/17 QC#20267
                scrnMsg.A.no(selectIdx).mnfItemCd_PA.clear();
                // Mod Start 2018/07/17 QC#20267
                scrnMsg.A.no(selectIdx).mdseDescShortTxt_PA.clear();
                // Mod Start 2016/10/17 QC#15193
//                scrnMsg.A.no(selectIdx).coaMdseTpNm_PA.clear();
                scrnMsg.A.no(selectIdx).coaProjNm_PA.clear();
                // Mod End 2016/10/17 QC#15193
                scrnMsg.A.no(selectIdx).mdseItemTpNm_PA.clear();
                scrnMsg.A.no(selectIdx).coaProdNm_PA.clear();
                scrnMsg.A.no(selectIdx).t_MdlNm_PA.clear();
                // Add #4767 2016/03/22 Start
                scrnMsg.A.no(selectIdx).xxScrItem61Txt_P0.clear();
                scrnMsg.A.no(selectIdx).xxScrItem61Txt_P1.clear();
                scrnMsg.A.no(selectIdx).xxScrItem61Txt_P2.clear();
                scrnMsg.A.no(selectIdx).xxScrItem61Txt_P3.clear();
                scrnMsg.A.no(selectIdx).xxScrItem61Txt_P4.clear();
                // Add #4767 2016/03/22 End

                // Mod Start 2017/02/24 QC#16011
//                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.A.no(selectIdx).prcQlfyTpCd_PA.getValue(), scrnMsg);
                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.A.no(selectIdx).prcQlfyTpCd_PA.getValue(), scrnMsg, updateAuthFlg);
                // Mod End 2017/02/24 QC#16011

                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).prcQlfyValTxt_PA);
            }
        } else if (TAB_PRC_LIST_VAL_QTY_DISC.equals(scrnMsg.xxDplyTab_D1.getValue())) {
            if (selectIdx < 0) {
                // Mod Start 2017/02/24 QC#16011
//                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.prcQlfyTpCd_D2.getValue(), scrnMsg);
                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.prcQlfyTpCd_D2.getValue(), scrnMsg, updateAuthFlg);
                // Mod End 2017/02/24 QC#16011
            } else {
                scrnMsg.I.no(selectIdx).prcQlfyValTxt_PI.clear();
                scrnMsg.I.no(selectIdx).prodCtrlNm_PI.clear();
                // Mod Start 2016/10/17 QC#15193
//                scrnMsg.I.no(selectIdx).coaMdseTpNm_PI.clear();
                scrnMsg.I.no(selectIdx).coaProjNm_PI.clear();
              // Mod End 2016/10/17 QC#15193
                scrnMsg.I.no(selectIdx).mdseItemTpNm_PI.clear();
                scrnMsg.I.no(selectIdx).coaProdNm_PI.clear();
                scrnMsg.I.no(selectIdx).t_MdlNm_PI.clear();

                // Mod Start 2017/02/24 QC#16011
//                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.I.no(selectIdx).prcQlfyTpCd_PI.getValue(), scrnMsg);
                NMAL7010CommonLogic.prcListQlfyTpCtrl(this, NMAL7010Constant.BTN_PRC_QLFY_VAL, selectIdx, scrnMsg.I.no(selectIdx).prcQlfyTpCd_PI.getValue(), scrnMsg, updateAuthFlg);
                // Mod End 2017/02/24 QC#16011
            }
        }
    }
}
