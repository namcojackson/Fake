/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_WH;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.ZZM9000E;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;

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
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Fujitsu         T.Yoshida       Create          N/A
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2018/04/10   Fujitsu         S.Takami        Update          S21_NA#25170
 * 2018/04/12   Fujitsu         K.Ishizuka      Update          S21_NA#23704
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_ChangeSubWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        // 2017/11/17 S21_NA#22252 Mod Start
        //scrnMsg.B.no(selectIndex).rtlWhNm_LL.clearErrorInfo();
        //if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL)) {
        //    scrnMsg.B.no(selectIndex).rtlSwhNm_LL.clear();
        //    scrnMsg.B.no(selectIndex).rtlWhNm_LL.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_WH });
        //    scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_LL);
        //    scrnMsg.putErrorScreen();
        //}
        // 2018/04/10 S21_NA#25170 Add Start
        EZDBStringItem strItem = null;
        // 2018/04/10 S21_NA#25170 Add End
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.B.no(selectIndex).rtlWhNm_LL.clearErrorInfo();
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlWhNm_LL)) {
                scrnMsg.B.no(selectIndex).rtlSwhNm_LL.clear();
                scrnMsg.B.no(selectIndex).rtlWhNm_LL.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_WH });
                scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_LL);
                scrnMsg.putErrorScreen();
            }
            // 2018/04/10 S21_NA#25170 Add Start
            strItem = scrnMsg.B.no(selectIndex).rtlSwhNm_LL;
            // 2018/04/10 S21_NA#25170 Add End
        } else {
            scrnMsg.D.no(selectIndex).rtlWhNm_RL.clearErrorInfo();
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).rtlWhNm_RL)) {
                scrnMsg.D.no(selectIndex).rtlSwhNm_RL.clear();
                scrnMsg.D.no(selectIndex).rtlWhNm_RL.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_WH });
                scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).rtlWhNm_RL);
                scrnMsg.putErrorScreen();
            }
            // 2018/04/10 S21_NA#25170 Add Start
            strItem = scrnMsg.D.no(selectIndex).rtlSwhNm_RL;
            // 2018/04/10 S21_NA#25170 Add End
        }
        // 2017/11/17 S21_NA#22252 Mod End
        // 2018/04/10 S21_NA#25170 Add Start
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
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        // 2017/11/17 S21_NA#22252 Mod Start
        //NWAL1500_BBMsg lineMsg = scrnMsg.B.no(selectIndex);
        //if (!ZYPCommonFunc.hasValue(lineMsg.rtlSwhNm_LL)) {
        //    lineMsg.rtlSwhCd_LL.clear();

        //    // Set Warehouse For Component
        //    String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
        //    BigDecimal dsCpoLineNum = lineMsg.dsCpoLineNum_LL.getValue();
        //    for (int i = selectIndex + 1; i < scrnMsg.B.getValidCount(); i++) {
        //        NWAL1500_BBMsg setLineMsg = scrnMsg.B.no(i);
        //        if (S21StringUtil.isEquals(dsOrdPosnNum, setLineMsg.dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, setLineMsg.dsCpoLineNum_LL.getValue()) == 0) {
        //            setLineMsg.rtlSwhCd_LL.clear();
        //            setLineMsg.rtlSwhNm_LL.clear();
        //        } else {
        //            break;
        //        }
        //    }

        //    return null;
        //}
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(selectIndex);
            if (!ZYPCommonFunc.hasValue(lineMsg.rtlSwhNm_LL)) {
                lineMsg.rtlSwhCd_LL.clear();

                // Set Warehouse For Component
                String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
                BigDecimal dsCpoLineNum = lineMsg.dsCpoLineNum_LL.getValue();
                for (int i = selectIndex + 1; i < scrnMsg.B.getValidCount(); i++) {
                    NWAL1500_BBMsg setLineMsg = scrnMsg.B.no(i);
                    if (S21StringUtil.isEquals(dsOrdPosnNum, setLineMsg.dsOrdPosnNum_LL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, setLineMsg.dsCpoLineNum_LL.getValue()) == 0) {
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
            if (!ZYPCommonFunc.hasValue(rmaLineMsg.rtlSwhNm_RL)) {
                rmaLineMsg.rtlSwhCd_RL.clear();

                // Set Warehouse For Component
                String dsOrdPosnNum = rmaLineMsg.dsOrdPosnNum_RL.getValue();
                BigDecimal dsCpoLineNum = rmaLineMsg.dsCpoLineNum_RL.getValue();
                for (int i = selectIndex + 1; i < scrnMsg.D.getValidCount(); i++) {
                    NWAL1500_DBMsg setLineMsg = scrnMsg.D.no(i);
                    if (S21StringUtil.isEquals(dsOrdPosnNum, setLineMsg.dsOrdPosnNum_RL.getValue()) && NWAL1500CommonLogic.compareBigDecimal(dsCpoLineNum, setLineMsg.dsCpoLineNum_RL.getValue()) == 0) {
                        setLineMsg.rtlSwhCd_RL.clear();
                        setLineMsg.rtlSwhNm_RL.clear();
                    } else {
                        break;
                    }
                }

                return null;
            }
        }
        // 2017/11/17 S21_NA#22252 Mod End

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

        // 2017/11/17 S21_NA#22252 Add Start
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        // 2017/11/17 S21_NA#22252 Add End
        int selectIndex = scrnMsg.xxCellIdx.getValueInt();

        // 2017/11/17 S21_NA#22252 Mod Start
        //if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlSwhNm_LL)) {
        //    scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordLineSrcCd_LL);
        //    return;
        //}
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIndex).rtlSwhNm_LL)) {
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordLineSrcCd_LL);
                return;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIndex).rtlSwhNm_RL)) {
                scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).custUomCd_RL);
                return;
            }
        }
        // 2017/11/17 S21_NA#22252 Mod End

        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2017/11/17 S21_NA#22252 Mod Start
        //scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_LL);
        //scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlWhNm_LL);
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).rtlSwhNm_LL);
        } else {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).rtlWhNm_RL);
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).rtlSwhNm_RL);
        }
        // 2017/11/17 S21_NA#22252 Mod End
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForWh(scrnMsg, false);
            setArgForSubScreen(params);
            return;
        }
        NWAL1500CommonLogicForScrnFields.setProtectByRtlSwhCd(this, scrnMsg); // 2018/04/12 S21_NA#23704 Add

        // 2017/11/17 S21_NA#22252 Mod Start
        //scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordLineSrcCd_LL);
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).ordLineSrcCd_LL);
        } else {
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).custUomCd_RL);
        }
         // 2017/11/17 S21_NA#22252 Mod End
    }
}
