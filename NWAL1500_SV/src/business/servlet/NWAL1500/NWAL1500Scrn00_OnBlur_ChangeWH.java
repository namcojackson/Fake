/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/08   Fujitsu         M.Mikio         Update          QC#1615
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2018/04/10   Fujitsu         S.Takami        Update          S21_NA#25170
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_ChangeWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // 2018/04/10 S21_NA#25170 Add Start
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
        EZDBStringItem strItem = null;
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            strItem = scrnMsg.B.no(selectIndex).rtlWhNm_LL;
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            strItem = scrnMsg.D.no(selectIndex).rtlWhNm_RL;
        }
        boolean rslt = NWAL1500CommonLogic.checkSerchKey(strItem);
        if (!rslt) {
            scrnMsg.addCheckItem(strItem);
            scrnMsg.putErrorScreen();
        }
        // 2018/04/10 S21_NA#25170 Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(selectIndex);
            if (!ZYPCommonFunc.hasValue(lineMsg.rtlWhNm_LL)) {
                lineMsg.rtlWhCd_LL.clear();
                lineMsg.rtlSwhCd_LL.clear();
                lineMsg.rtlSwhNm_LL.clear();

                // Clear Warehouse For Component
                String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
                BigDecimal dsCpoLineNum = lineMsg.dsCpoLineNum_LL.getValue();
                for (int i = selectIndex + 1; i < scrnMsg.B.getValidCount(); i++) {
                    NWAL1500_BBMsg setLineMsg = scrnMsg.B.no(i);
                    if (S21StringUtil.isEquals(dsOrdPosnNum, setLineMsg.dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, setLineMsg.dsCpoLineNum_LL.getValue()) == 0) {
                        setLineMsg.rtlWhCd_LL.clear();
                        setLineMsg.rtlWhNm_LL.clear();
                        setLineMsg.rtlSwhCd_LL.clear();
                        setLineMsg.rtlSwhNm_LL.clear();
                    } else {
                        break;
                    }
                }

                return null;
            }
        } else {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(selectIndex);
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtlWhNm_RL)) {
                rmaLineMsg.rtlWhCd_RL.clear();
                rmaLineMsg.rtlSwhCd_RL.clear();
                rmaLineMsg.rtlSwhNm_RL.clear();
                rmaLineMsg.thirdPtyDspTpCd_RL.clear();

                // Clear Warehouse For Component
                String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
                BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();
                for (int i = selectIndex + 1; i < scrnMsg.D.getValidCount(); i++) {
                    NWAL1500_DBMsg setRmaLineMsg = scrnMsg.D.no(i);
                    if (S21StringUtil.isEquals(dsOrdPosnNum, setRmaLineMsg.dsOrdPosnNum_RL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, setRmaLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                        setRmaLineMsg.rtlWhCd_RL.clear();
                        setRmaLineMsg.rtlWhNm_RL.clear();
                        setRmaLineMsg.rtlSwhCd_RL.clear();
                        setRmaLineMsg.rtlSwhNm_RL.clear();
                    } else {
                        break;
                    }
                }

                return null;
            }
        }

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL)) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
                return;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).rtlWhNm_RL)) {
                // 2017/11/17 S21_NA#22252 Mod Start
                //scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).custUomCd_RL);
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).rtlSwhNm_RL);
                // 2017/11/17 S21_NA#22252 Mod End
                return;
            }
        }

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_LL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).rtlWhNm_RL);
        }
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForWh(scrnMsg, true);
            setArgForSubScreen(params);
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
        } else {
            // 2017/11/17 S21_NA#22252 Mod Start
            //scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).custUomCd_RL);
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).rtlSwhNm_RL);
            // 2017/11/17 S21_NA#22252 Mod End
        }
    }
}
