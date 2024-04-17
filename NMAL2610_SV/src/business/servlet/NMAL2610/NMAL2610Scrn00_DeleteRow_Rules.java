/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04   Fujitsu         M.suzuki        Update          CSA-QC#4539 
 * 2016/08/01   Fujitsu         C.Yokoi         Update          CSA-QC#10385
 * 2017/03/08   Fujitsu         R.Nakamura      Update          QC#15878
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 * 2019/12/27   Fujitsu         A.Kazuki        Update          QC#54222-1
 *</pre>
 */
public class NMAL2610Scrn00_DeleteRow_Rules extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        // Delete Start 2019/12/27 QC#54222-1
//        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, NMAL2610Constant.CHKBOX_C, ZYPConstant.CHKBOX_ON_Y);
//        if (checkList.size() <= 0) {
//            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//                scrnMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAL2610Constant.NZZM0011E);
//            }
//        }
        // Delete End   2019/12/27 QC#54222-1

     // 2016/08/01 CSA-QC#10385 Delete Start
     // for (int index : checkList) {
     //  if (ZYPCommonFunc.hasValue(scrnMsg.C.no(index).ezUpTime_X3)) {
     //         if (NMAL2610CommonLogic.checkActiveDate(scrnMsg.C.no(index).effFromDt_C1.getValue(), scrnMsg.C.no(index).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
     //             scrnMsg.C.no(index).xxChkBox_C1.setErrorInfo(1, NMAL2610Constant.NMAM8230E);
     //         } else {
     //             scrnMsg.setMessageInfo(NMAL2610Constant.NMAM8291W);
     //         }
     //     }
     // }
     //  2016/08/01 CSA-QC#10385 Delete End

        scrnMsg.xxDplyTab_BK.setValue(scrnMsg.xxDplyTab.getValue());
        NMAL2610CommonLogic.addTabRulesCheckItem(scrnMsg);
        // 2016/03/04 S21_NA#4539 Add Start --------------
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

            if (scrnMsg.C.no(i).effFromDt_C1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("effFromDt_C1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).effFromDt_C1.clearErrorInfo();
                }
            }

            if (scrnMsg.C.no(i).trtyRuleFromValTxt_C1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.C.no(i).getErrorInfo("trtyRuleFromValTxt_C1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.C.no(i).trtyRuleFromValTxt_C1.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.C.no(i).trtyRuleFromValTxt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).trtyRuleThruValTxt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C1);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C1);
        }
        // 2016/03/04 S21_NA#4539 Add Start --------------
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add Start 2017/03/08 QC#15878
        scrnMsg.clearAllGUIAttribute(NMAL2610Constant.SCREEN_ID);
        NMAL2610CommonLogic.controlScreenTrtyRuleFields(this, scrnMsg);
        // Add End 2017/03/08 QC#15878

        // Mod Start 2019/12/27 QC#54222-1
        String beforeTabName = scrnMsg.xxDplyTab.getValue();
//        // 2018/09/21 QC#27726,ADD Add Start
//        if (scrnMsg.C.getValidCount() + 1 <= scrnMsg.C.length()) {
//            setButtonEnabled(NMAL2610Constant.BTN_INSERT_ROW_RULES, true);
//        }
//        if (scrnMsg.C.getValidCount() == 0) {
//            setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RULES, false);
//        }
//        NMAL2610CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, NMAL2610Constant.SCREEN_TABLE_NAME_TERRITORY_RULE);
//        // 2018/09/21 QC#27726,ADD Add End
        if (scrnMsg.C.getValidCount() == 0) {
            setButtonEnabled(NMAL2610Constant.BTN_DELETE_ROW_RULES, false);
        }
        try {
            if (scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_ERR) || scrnMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_WARN)) {
                NMAL2610CommonLogic.addCheckItem(scrnMsg, true);
                scrnMsg.putErrorScreen();
                scrnMsg.xxDplyTab.setValue(scrnMsg.xxDplyTab_BK.getValue());
                throw new EZDFieldErrorException();
            }
        } finally {
            NMAL2610CommonLogic.setAllBGWithReset(scrnMsg, beforeTabName);
            NMAL2610CommonLogic.setAddDelButton(this, scrnMsg);
        }
        // Mod End   2019/12/27 QC#54222-1
    }
}
