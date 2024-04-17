/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8899;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0002E;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.common.NYX.NYXC889010.NYEL8890TokenBizFactory;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import business.blap.NYEL8899.common.NYEL8899CommonLogic;
import business.blap.NYEL8899.constant.NYEL8899Constant;

/**
 *<pre>
 * NYEL8899BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8899CMsg bizMsg = (NYEL8899CMsg) cMsg;

            if ("NYEL8899_INIT".equals(screenAplID)) {
                doProcess_NYEL8899_INIT(bizMsg, sMsg);

            } else if ("NYEL8899Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NYEL8899Scrn00_CMN_Reset(bizMsg, sMsg);

            } else if ("NYEL8899Scrn00_Search".equals(screenAplID)) {
                doProcess_NYEL8899Scrn00_Search(bizMsg, sMsg);

            } else if ("NYEL8899Scrn00_Init".equals(screenAplID)) {
                doProcess_NYEL8899Scrn00_Init(bizMsg, sMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8899_INIT(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {
        bizMsg.xxFilePathTxt.setValue(NYEL8899Constant.INIT_PROPERTIES);

        if (ZYPCommonFunc.hasValue(bizMsg.wfProcNm)) {
            doProcess_NYEL8899Scrn00_Search(bizMsg, glblMsg);
        } else {
            NYEL8899CommonLogic.readSetting(bizMsg);
        }

    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8899Scrn00_CMN_Reset(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {
        doProcess_NYEL8899_INIT(bizMsg, glblMsg);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NYEL8899Scrn00_Search(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {
        S21SsmEZDResult result = NYEL8899Query.getInstance().search(bizMsg, glblMsg);

        if (result.isCodeNotFound()) {
            bizMsg.setMessageInfo(NYEM0002E);
        }
    }

    private void doProcess_NYEL8899Scrn00_Init(NYEL8899CMsg bizMsg, EZDSMsg glblMsg) {
        String factoryClazz = bizMsg.wfClsNm.getValue();
        String pattern = bizMsg.wfTestPtrnNm.getValue();

        try {
            Class<NYEL8890TokenBizFactory> clazz = (Class<NYEL8890TokenBizFactory>) Class.forName(factoryClazz);

            if (clazz == null) {
                bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
                return;
            }

            S21NwfUtilTokenObj tokenObj = clazz.newInstance().crateTokenBizObject(pattern);

            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_01, tokenObj.getAttribute1());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_02, tokenObj.getAttribute2());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_03, tokenObj.getAttribute3());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_04, tokenObj.getAttribute4());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbTxt_05, tokenObj.getAttribute5());

            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_01, tokenObj.getAttribute1Lbl());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_02, tokenObj.getAttribute2Lbl());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_03, tokenObj.getAttribute3Lbl());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_04, tokenObj.getAttribute4Lbl());
            ZYPEZDItemValueSetter.setValue(bizMsg.wfBizAttrbLbTxt_05, tokenObj.getAttribute5Lbl());

            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_01, tokenObj.getCondStr1());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_02, tokenObj.getCondStr2());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_03, tokenObj.getCondStr3());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_04, tokenObj.getCondStr4());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_05, tokenObj.getCondStr5());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_06, tokenObj.getCondStr6());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_07, tokenObj.getCondStr7());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_08, tokenObj.getCondStr8());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_09, tokenObj.getCondStr9());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValTxt_10, tokenObj.getCondStr10());

            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_01, tokenObj.getCondNum1());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_02, tokenObj.getCondNum2());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_03, tokenObj.getCondNum3());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_04, tokenObj.getCondNum4());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_05, tokenObj.getCondNum5());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_06, tokenObj.getCondNum6());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_07, tokenObj.getCondNum7());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_08, tokenObj.getCondNum8());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_09, tokenObj.getCondNum9());
            ZYPEZDItemValueSetter.setValue(bizMsg.condValNum_10, tokenObj.getCondNum10());

        } catch (ClassNotFoundException e) {
            bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
            e.printStackTrace();
        } catch (InstantiationException e) {
            bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            bizMsg.setMessageInfo(NYEL8899Constant.NFCM0501E, new String[] {factoryClazz });
            e.printStackTrace();
        }
    }

}
