/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/10   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2016/03/04   Fujitsu         C.Yokoi         Update          S21_NA#5945
 * 2017/06/14   Hitachi         J.Kim           Update          QC#18924
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#27724
 *</pre>
 */
public class NMAL2520Scrn00_Delete_Row_Resrc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // START 2017/06/14 J.Kim [QC#18924,DEL]
//        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, NMAL2520Constant.CHKBOX_C, ZYPConstant.CHKBOX_ON_Y);
//        if (checkList.size() <= 0) {
//            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//                scrnMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NZZM0011E);
//            }
//        }
//
//            for (int index : checkList) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.C.no(index).gnrnTpCd_C1)) {
//                // 2016/03/30 CSA-QC#5945 Delete Start
////                if (!GNRN_TP.FUTURE.equals(scrnMsg.C.no(index).gnrnTpCd_C1.getValue())) {
////                    scrnMsg.C.no(index).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8230E);
////                } else {
////                    scrnMsg.setMessageInfo(NMAL2520Constant.NMAM8291W);
////                }
//                // 2016/03/30 CSA-QC#5945 Delete End
//                // 2016/03/30 CSA-QC#5945 Add Start
//                // can not delete data which already deleted or expired
//                if (GNRN_TP.PAST.equals(scrnMsg.C.no(index).gnrnTpCd_C1.getValue())) {
//                    scrnMsg.C.no(index).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8230E);
//                }
//                // 2016/03/30 CSA-QC#5945 Add End
//            }
//        }
        // END 2017/06/14 J.Kim [QC#18924,DEL]

        // 2016/03/04 S21_NA#4539 Mod Start --------------
        //NMAL2520CommonLogic.addTabResrcCheckItem(scrnMsg);
        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        // 2016/03/04 S21_NA#4539 Mod Start --------------
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/06/14 J.Kim [QC#18924,ADD]
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageCode().endsWith(NMAL2520Constant.MESSAGE_KIND_ERR)) {
            throw new EZDFieldErrorException();
        }

        NMAL2520CommonLogic.controlInsertDelete(this, scrnMsg);
        // END 2017/06/14 J.Kim [QC#18924,ADD]

        // 2018/09/20 QC#27724,ADD Add Start
        if (scrnMsg.C.getValidCount() + 1 <= scrnMsg.C.length()) {
            setButtonEnabled(NMAL2520Constant.BTN_INSERT_RESRC, true);
        }
        if (scrnMsg.C.getValidCount() == 0) {
            setButtonEnabled(NMAL2520Constant.BTN_DELETE_RESRC, false);
        }
        NMAL2520CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, NMAL2520Constant.SCREEN_TABLE_NAME_RESRC);
        // 2018/09/20 QC#27724,ADD Add End
    }
}
