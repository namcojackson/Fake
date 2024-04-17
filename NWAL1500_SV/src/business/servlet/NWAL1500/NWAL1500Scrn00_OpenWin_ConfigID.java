/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Fujitsu         T.Ishii         Create          N/A
 * 2016/01/21   Fujitsu         S.Takami        Update          Update
 * 2016/03/03   Fujitsu         S.Ohki          Update          S21_NA#5000
 * 2018/03/28   Fujitsu         S.Takami        Update          S21_NA#25102
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_ConfigID extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        clearPopupParm(scrnMsg);
        int selectedIndex = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, BigDecimal.valueOf(selectedIndex));
        // S21_NA#955 move to BLAP
        // if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
        // NWAL1500_ABMsg config = scrnMsg.A.no(selectedIndex);
        // scrnMsg.addCheckItem(config.xxChkBox_LC);
        // String configTp[] = {CONFIG_TP.ADD_TO_CONFIG,
        // CONFIG_TP.EXISTING };
        // if
        // (!Arrays.asList(configTp).contains(config.configTpCd_LC.getValue()))
        // {
        // config.xxChkBox_LC.setErrorInfo(1, NWAM0682E);
        // }
        // } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
        // NWAL1500_CBMsg config = scrnMsg.C.no(selectedIndex);
        // scrnMsg.addCheckItem(config.xxChkBox_RC);
        // String configTp[] = {CONFIG_TP.RETURN_EXISTING_IB };
        // if
        // (!Arrays.asList(configTp).contains(config.configTpCd_RC.getValue()))
        // {
        // config.xxChkBox_RC.setErrorInfo(1, NWAM0682E);
        // }
        // } else {
        // scrnMsg.setMessageInfo(NWAM0682E);
        // throw new EZDFieldErrorException();
        // }
        // scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        EZDMsg.copy(cMsg, null, bMsg, null);

        int selectedIndex = this.getButtonSelectNumber();
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            NWAL1500_ABMsg config = scrnMsg.A.no(selectedIndex);
            scrnMsg.addCheckItem(config.configTpCd_LC); // 2016/03/03 S21_NA#5000 Mod
            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            setArgForSubScreen(getParamArray(scrnMsg));
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            NWAL1500_CBMsg config = scrnMsg.C.no(selectedIndex);
            scrnMsg.addCheckItem(config.configTpCd_RC); // 2016/03/03 S21_NA#5000 Mod
            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            setArgForSubScreen(getParamArray(scrnMsg));
        }
    }

    private void clearPopupParm(NWAL1500BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.Z.clear();
        scrnMsg.mdlId_R.clear();
        scrnMsg.mdlNm_R.clear();
        scrnMsg.serNum_R.clear();
        scrnMsg.contrPk_R.clear();
        scrnMsg.contrNum_R.clear();
        scrnMsg.svcConfigMstrPk_R.clear();
        scrnMsg.mdseCd_R.clear();
        scrnMsg.curLocNum_R.clear();
        scrnMsg.locNm_R.clear();
        scrnMsg.svcMachUsgStsCd_R.clear();
        scrnMsg.svcMachMstrPk_R.clear();
        scrnMsg.xxEdtModeFlg_R1.clear();
        scrnMsg.xxEdtModeFlg_R2.clear();
//        scrnMsg.xxEdtModeFlg_R3.clear();
//        scrnMsg.xxEdtModeFlg_R4.clear();
//        scrnMsg.xxEdtModeFlg_R5.clear();
        scrnMsg.mdlId_RO.clear();
        scrnMsg.mdlNm_RO.clear();
        scrnMsg.contrPk_RO.clear();
        scrnMsg.contrNum_RO.clear();
        scrnMsg.svcConfigMstrPk_RO.clear();
//        scrnMsg.xxSfxKeyTxt_RO.clear();
        scrnMsg.R.clear();
    }

    private Object[] getParamArray(NWAL1500BMsg scrnMsg) {

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(scrnMsg.xxPopPrm_P0); //[0]: CONFIG_EXST_MODE_CD
        parameters.add(scrnMsg.svcConfigMstrPk_R); //[1]: SVC_CONFIG_MSTR_PK
        parameters.add(scrnMsg.serNum_R); //[2]: SER_NUM
        parameters.add(scrnMsg.svcMachMstrPk_R); //[3]: SVC_MACH_MSTR_PK
        parameters.add(scrnMsg.mdseCd_R); //[4]: MDSE_CD
        parameters.add(scrnMsg.mdlNm_R); //[5]: MDL_NM
        parameters.add(scrnMsg.xxPopPrm_P1); //[6]: SHIP_FEOM_DT
        parameters.add(scrnMsg.xxPopPrm_P2); //[7]: SHIP_THRU_DT
        parameters.add(scrnMsg.svcMachUsgStsCd_R);//[8]: SVC_MACH_USG_STS_CD
        parameters.add((EZDBMsgArray) scrnMsg.Z);//[9]: SVC_MACH_MSTR_STS_CD, Array
        parameters.add(scrnMsg.xxEdtModeFlg_R1);//[10]: SVC_MACH_MSTR_STS_EDIT_FLG
        parameters.add(scrnMsg.xxPopPrm_P3); //[11]: MACH_ALLOC_MODE_CODE
        parameters.add(scrnMsg.xxEdtModeFlg_R2); //[12]: MAIN_UNIT_FLG
        parameters.add(scrnMsg.xxPopPrm_P4);//[13]: STK_STS_CD
        parameters.add(scrnMsg.xxPopPrm_P5);//[14]: WH_CD
        parameters.add(scrnMsg.xxPopPrm_P6);//[15]: SUB_WH_CD
        parameters.add(scrnMsg.xxPopPrm_P7);//[16]: SVC_SLN_SQ
        parameters.add(scrnMsg.xxPopPrm_P8);//[17]: SVC_SLN_NM
        parameters.add(scrnMsg.xxPopPrm_P9);//[18]: CONTR_NUM
        parameters.add(scrnMsg.xxPopPrm_PA);//[19]: DS_OWNR_ACCT_NUM
        parameters.add(scrnMsg.xxPopPrm_PB);//[20]: OWNR_LOC_CD
        parameters.add(scrnMsg.xxPopPrm_PC);//[21]: DS_BILL_TO_ACCT_NUM
        parameters.add(scrnMsg.xxPopPrm_PD);//[22]: BILL_TO_LOC_CD
        parameters.add(scrnMsg.xxPopPrm_PE);//[23]: DS_CUR_LOC_ACCT_NUM
        parameters.add(scrnMsg.curLocNum_R);//[24]: CUR_LOC_NUM

//        parameters.add(scrnMsg.mdlId_R);
//        parameters.add(scrnMsg.contrPk_R);
//        parameters.add(scrnMsg.contrNum_R);
//        parameters.add(scrnMsg.curLocNum_R);
//        parameters.add(scrnMsg.locNm_R);
//        parameters.add(scrnMsg.xxEdtModeFlg_R3);
//        parameters.add(scrnMsg.xxEdtModeFlg_R4);
//        parameters.add(scrnMsg.xxEdtModeFlg_R5);

        // Output Parameter
        parameters.add(scrnMsg.mdlId_RO);//[25]: MDL_ID
        parameters.add(scrnMsg.mdlNm_RO);//[26]: MDL_NM
        parameters.add(scrnMsg.contrPk_RO);//[27]: CONTR_PK
        parameters.add(scrnMsg.contrNum_RO);//[28]: CONTR_NUM
        parameters.add(scrnMsg.svcConfigMstrPk_RO);//[29]: SVC_CONFIG_MSTR_PK
//        parameters.add(scrnMsg.xxSfxKeyTxt_RO);
        parameters.add((EZDBMsgArray) scrnMsg.R);//[30]: 
        // 2017/07/26 S21_NA#20004 Add Start
        parameters.add((EZDBMsgArray) scrnMsg.R);//[31]: 
        List<BigDecimal> configList = new ArrayList<BigDecimal>(0);
        // 2018/03/28 S21_NA#25102 Del Start
//        String currentTab = scrnMsg.xxDplyTab.getValue();
//        if (S21StringUtil.isEquals(TAB_RMA, currentTab)) {
//            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).svcConfigMstrPk_RC)) {
//                    configList.add(scrnMsg.C.no(i).svcConfigMstrPk_RC.getValue());
//                }
//            }
//        } else if (S21StringUtil.isEquals(TAB_LINE_CONFIG, currentTab)) {
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcConfigMstrPk_LC)) {
//                    configList.add(scrnMsg.A.no(i).svcConfigMstrPk_LC.getValue());
//                }
//            }
//        }
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.E.no(i).svcConfigMstrPk_AW)) {
                configList.add(scrnMsg.E.no(i).svcConfigMstrPk_AW.getValue());
            }
        }
        // 2018/03/28 S21_NA#25102 Del End
        parameters.add(configList);//[32]: 
        // 2017/07/26 S21_NA#20004 Add Start
        return parameters.toArray(new Object[0]);
    }
}
