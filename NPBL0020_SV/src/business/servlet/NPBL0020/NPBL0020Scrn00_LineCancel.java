/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM0049E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPBM0009E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Line Cancel
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/19/2016   CITS            Makoto Okigami  Create          N/A
 * 04/22/2016   CITS            K.Ogino         Update          QC#7045
 * 08/10/2016   CITS            K.Ogino         Update          QC#9058
 * 01/11/2016   CITS            K.Ogino         Update          QC#15034
 * 06/01/2018   CITS            S.Katsuma       Update          QC#26234
 * 11/06/2018   CITS            M.Naito         Update          QC#28698
 * 05/18/2020   CITS            K.Ogino         Update          QC#56867
 *</pre>
 */
public class NPBL0020Scrn00_LineCancel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int count = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (isChecked(scrnMsg.A.no(i))) {
                count++;
            }
        }

        if (count == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM0049E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
        }

        // START 2018/06/01 S.Katsuma [QC#26234,MOD]
        Map<String, LinkedList<NPBL0020_ABMsg>> map = new HashMap<String, LinkedList<NPBL0020_ABMsg>>();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String prchReqLineNum = scrnMsg.A.no(i).prchReqLineNum_A1.getValue();
            LinkedList<NPBL0020_ABMsg> list = map.get(prchReqLineNum);
            if (list == null) {
                list = new LinkedList<NPBL0020_ABMsg>();
            }

            list.add(scrnMsg.A.no(i));
            map.put(prchReqLineNum, list);
        }

        // START 2018/11/06 M.Naito [QC#28698,MOD]
        // QC#56867 Mod Start
        boolean isConfigFlg = false;
        boolean cancConfigLineFlg = false;
        if (PRCH_REQ_TP.WH_TRANSFER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.EXPENSE_ORDER.equals(scrnMsg.prchReqTpCd_SL.getValue()) || PRCH_REQ_TP.DISPOSAL.equals(scrnMsg.prchReqTpCd_SL.getValue())) {
            isConfigFlg = true;
        }
        // QC#56867 End
        for (String key : map.keySet()) {
            List<NPBL0020_ABMsg> list = map.get(key);
            if (list.size() > 1) {
                int cancLine = 0;
                int cancLineConfigTpExisting = 0;
                for (NPBL0020_ABMsg abMsg : list) {
                    if (isConfigParentDetail(abMsg)) {
                        if (isChecked(abMsg)) {
                            cancConfigLineFlg = true;
                            break;
                        }
                    } else {
                        if (isChecked(abMsg)) {
                            cancLine++;
                            if (isConfigTpExisting(abMsg)) {
                                cancLineConfigTpExisting++;
                            }
                        }
                    }
                }

                if (cancLine != 0 && cancLine == list.size() - 1) {
                    for (int j = 1; j < list.size(); j++) {
                        list.get(j).xxChkBox_A1.setErrorInfo(1, NPBM0009E);
                        scrnMsg.addCheckItem(list.get(j).xxChkBox_A1);
                    }
                } else if (isConfigFlg && !cancConfigLineFlg && cancLineConfigTpExisting != 0) {
                    for (int j = 1; j < list.size(); j++) {
                        list.get(j).xxChkBox_A1.setErrorInfo(1, NPBM0009E);
                        scrnMsg.addCheckItem(list.get(j).xxChkBox_A1);
                    }
                }
            }
        }
        // END 2018/11/06 M.Naito [QC#28698,MOD]
        // END 2018/06/01 S.Katsuma [QC#26234,MOD]
        // QC#56867 Mod
        // Status Check
//        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.prchReqSavedFlg.getValue())) {
//            scrnMsg.setMessageInfo(NPBM0011E);
//        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum, scrnMsg.prchReqNum_HD);

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPBL0020CommonLogic.setCtrlScrnItemDispAfterSearch(this, scrnMsg);

        // QC#56867 Add Start
        if (!NPBL0020CommonLogic.postInputCheckForSaveAndSubmit(scrnMsg, bizMsg)) {
            return;
        }

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // QC#56867 Add End
        // set focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);

    }

    private static boolean isConfigParentDetail(NPBL0020_ABMsg aMsg) {
        if (BigDecimal.ZERO.equals(aMsg.prchReqLineSubNum_A1.getValue()) && ZYPCommonFunc.hasValue(aMsg.configTpCd_A1)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isChecked(NPBL0020_ABMsg asMsg) {
        return ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue());
    }

// START 2018/11/06 M.Naito [QC#28698,ADD]
    private static boolean isConfigTpExisting(NPBL0020_ABMsg asMsg) {
        return CONFIG_TP.EXISTING.equals(asMsg.configTpCd_A1.getValue());
    }
// END 2018/11/06 M.Naito [QC#28698,ADD]
}
