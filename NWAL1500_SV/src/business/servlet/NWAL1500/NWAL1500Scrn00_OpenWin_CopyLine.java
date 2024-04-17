/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0504E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0683E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   CUSA            Y.Kanefusa      Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_CopyLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        List<Integer> slctConfList = null;
        List<Integer> slctLineList = null;
        scrnMsg.xxCellIdx.clear();
        scrnMsg.xxCellIdx_BB.clear();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            slctLineList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        } else {
            slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            slctLineList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        }

        if (slctConfList.size() == 0 && slctLineList.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0504E, null);
            throw new EZDFieldErrorException();
        } else if (slctConfList.size() > 0) {
            if (slctLineList.size() > 0 || slctConfList.size() != 1) {
                scrnMsg.setMessageInfo(NWAM0683E, null);
                throw new EZDFieldErrorException();
            }
        } else if (slctLineList.size() != 1) {
            scrnMsg.setMessageInfo(NWAM0683E, null);
            throw new EZDFieldErrorException();
        }

        if (slctConfList.size() > 0) {
            scrnMsg.xxCellIdx.setValue(slctConfList.get(0)); // Config Mode
        } else {
            scrnMsg.xxCellIdx_BB.setValue(slctLineList.get(0)); // Line Mode
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/02/10 S21_NA#3398 Add Start
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        List<Integer> slctConfList = null;
        int slctConfIdx = -1;
        boolean callBlap = false;
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            if (slctConfList.size() > 0) {
                slctConfIdx = slctConfList.get(0);
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(slctConfIdx).svcConfigMstrPk_LC)) {
                    callBlap = true;
                }
            }
        } else {
            slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            if (slctConfList.size() > 0) {
                slctConfIdx = slctConfList.get(0);
                if (ZYPCommonFunc.hasValue(scrnMsg.C.no(slctConfIdx).svcConfigMstrPk_RC)) {
                    callBlap = true;
                }
            }
        }
        if (slctConfIdx >= 0 && callBlap) {
            scrnMsg.xxCellIdx.setValue(slctConfIdx);
            NWAL1500CMsg bizMsg = new NWAL1500CMsg();

            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
        // 2016/02/10 S21_NA#3398 Add End
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        // 2016/02/10 S21_NA#3398 Add Start
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
                }
            } else {
                for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_RC);
                }
            }
            scrnMsg.putErrorScreen();
        }
        // 2016/02/10 S21_NA#3398 Add End

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = NWAL1500CommonLogic.getParamNWAL1620ForDetail(scrnMsg);
        setArgForSubScreen(params);
    }
}
