/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.MODE_REFERENCE;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0666E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0667E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_AddlLineDetails
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2019/01/23   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_AddlLineDetails extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        List<Integer> checkList = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        } else {
            checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        }

        if (checkList.size() == 1) {
            scrnMsg.xxCellIdx.setValue(checkList.get(0));
        } else if (checkList.size() == 0) {
            scrnMsg.setMessageInfo(NWAM0667E, new String[] {"Component Line" });
            throw new EZDFieldErrorException();
        } else if (checkList.size() > 1) {
            scrnMsg.setMessageInfo(NWAM0666E, new String[] {"Component Line" });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        Object[] params = getParamNWAL1630(scrnMsg);
        setArgForSubScreen(params);
    }

    /**
     * Get Param NWAL1630
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL1630
     */
    public static Object[] getParamNWAL1630(NWAL2200BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int slctConfNum = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, lineMsg.csmpPrcListCd_LL);
        } else {
            NWAL2200_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, rmaLineMsg.csmpPrcListCd_RL);
        }

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.cpoOrdNum); // [0]
        paramList.add(scrnMsg.sellToCustCd); // S21_NA#2557 // [1]
        paramList.add(scrnMsg.dsOrdCatgCd); // [2]
        paramList.add(scrnMsg.dsOrdTpCd); // [3]
        paramList.add(scrnMsg.lineBizTpCd); // [4]
        paramList.add(scrnMsg.xxCratDt); // [5]
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            paramList.add(lineMsg.dsOrdPosnNum_LL); // [6]
            paramList.add(lineMsg.dsCpoLineNum_LL); // [7]
            paramList.add(lineMsg.dsCpoLineSubNum_LL); // [8]
            paramList.add(scrnMsg.csmpContrNum); // [9]
            paramList.add(scrnMsg.dlrRefNum); // [10]
            paramList.add(lineMsg.csmpPrcListCd_LL); // [11]
        } else {
            NWAL2200_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            paramList.add(rmaLineMsg.dsOrdPosnNum_RL); // [6]
            paramList.add(rmaLineMsg.dsCpoLineNum_RL); // [7]
            paramList.add(rmaLineMsg.dsCpoLineSubNum_RL); // [8]
            paramList.add(scrnMsg.csmpContrNum); // [9]
            paramList.add(scrnMsg.dlrRefNum); // [10]
            paramList.add(rmaLineMsg.csmpPrcListCd_RL); // [11]
        }

        paramList.add(scrnMsg.P.no(10).xxPopPrm); // splyAbuseNodePrflCd  // [12]
        paramList.add(scrnMsg.P.no(10).xxPopPrm); // serNum  // [13]
        // 2019/01/23 S21_NA#29446 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            paramList.add(lineMsg.dsContrNum_LL);         // [14]dsContrNum
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            paramList.add(rmaLineMsg.dsContrNum_RL);      // [14]dsContrNum
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, MODE_REFERENCE);
        paramList.add(scrnMsg.P.no(9).xxPopPrm); // [15]
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            paramList.add(lineMsg.svcMachMstrPk_LL);         // [15]svcMachMstrPk
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            paramList.add(rmaLineMsg.svcMachMstrPk_RL);      // [15]svcMachMstrPk
        }
        // 2019/01/23 S21_NA#29446 Add End
        // 2019/01/23 S21_NA#29446 Del Start
//        paramList.add(scrnMsg.P.no(10).xxPopPrm); // dsContrNum
//        paramList.add(scrnMsg.P.no(10).xxPopPrm); // dlrFleetNum
//        paramList.add(scrnMsg.P.no(10).xxPopPrm); // splyCdTxt
//        paramList.add(scrnMsg.annTermCapNum_BW); // annTermCapNum_BW
//        paramList.add(scrnMsg.annTermCapNum_CL); // annTermCapNum_CL
//        if (TAB_LINE_CONFIG.equals(dplyTab)) {
//            NWAL2200_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
//            paramList.add(lineMsg.rntlTrmnDt_LL); // rntlTrmnDt
//        } else {
//            NWAL2200_DBMsg lineMsg = scrnMsg.D.no(slctConfNum);
//            // paramList.add(lineMsg.rntlTrmnDt_RL); // rntlTrmnDt
//        }
        // 2019/01/23 S21_NA#29446 Del End

        return paramList.toArray(new EZDBItem[0]);
    }

}
