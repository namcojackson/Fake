/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1630;

import static business.blap.NWAL1630.constant.NWAL1630Constant.NWAM0945E;
import static business.blap.NWAL1630.constant.NWAL1630Constant.TERM_COND_CAP_BW_ORG_ATTRB_NM;
import static business.blap.NWAL1630.constant.NWAL1630Constant.TERM_COND_CAP_CLR_ORG_ATTRB_NM;
import static business.blap.NWAL1630.constant.NWAL1630Constant.TERM_COND_CAP_TOT_ORG_ATTRB_NM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1630.common.NWAL1630CommonLogic;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1630BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 * 2019/01/24   Fujitsu         K.ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1630CMsg bizMsg = (NWAL1630CMsg) cMsg;

            // 2017/09/22 QC#18859 Add Start
            if ("NWAL1630_INIT".equals(screenAplID)) {
                doProcess_NWAL1630_INIT(bizMsg);
                // 2017/09/22 QC#18859 Add End
            } else if ("NWAL1630Scrn00_search_PriceList".equals(screenAplID)) {
                doProcess_NWAL1630Scrn00_search_PriceList(bizMsg);
            } else if ("NWAL1630Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NWAL1630Scrn00_CMN_Close(bizMsg);
            // 2019/01/24 S21_NA#29446 Add Start
            } else if ("NWAL1630Scrn00_OnBlur_SerNum".equals(screenAplID)) {
                doProcess_NWAL1630Scrn00_OnBlur_SerNum(bizMsg);
            } else if ("NWAL1630Scrn00_OnBlur_ContrNum".equals(screenAplID)) {
                doProcess_NWAL1630Scrn00_OnBlur_ContrNum(bizMsg);
            } else if ("NWAL1630_NSAL0110".equals(screenAplID)) {
                doProcess_NWAL1630_NSAL0110(bizMsg);
            } else if ("NWAL1630_NSAL1240".equals(screenAplID)) {
                doProcess_NWAL1630_NSAL1240(bizMsg);
            // 2019/01/24 S21_NA#29446 Add End
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // 2017/09/22 QC#18859 Add Start
    /**
     * Init Event
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NWAL1630_INIT(NWAL1630CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, bizMsg.svcMachMstrPk.getValue());
            tMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (null != tMsg) {
                ZYPEZDItemValueSetter.setValue(bizMsg.serNum, tMsg.serNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.serNum_BK, tMsg.serNum);
            }
        }
        // 2019/01/23 S21_NA#29446 Add Start
        NWAL1630CommonLogic.driveContrCapInfo(bizMsg, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (null != tMsg) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, tMsg.splyAbuseNodeUsgFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.splyAbuseNodePrflCd, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.splyAbuseNodePrflCd, ZYPConstant.FLG_OFF_N);
                }
            }
        }
        // 2019/01/23 S21_NA#29446 Add End
    }
    // 2017/09/22 QC#18859 Add End

    /**
     * search_PriceList Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1630Scrn00_search_PriceList(NWAL1630CMsg bizMsg) {
        NWAL1630CommonLogic.callPricingAPI(bizMsg, getGlobalCompanyCode());
    }

    /**
     * doProcess_NWAL1630Scrn00_CMN_Close Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1630Scrn00_CMN_Close(NWAL1630CMsg bizMsg) {
        // NWAL1630CommonLogic.callPricingAPI(bizMsg, getGlobalCompanyCode()); 2016/08/26 S21_NA#9806 Del

        // 2017/09/22 QC#18859 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.serNum) || ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            S21SsmEZDResult ssmResult = NWAL1630Query.getInstance().getSvcMachMstrPk(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.serNum.setErrorInfo(1, NWAM0945E);
                bizMsg.dsContrNum.setErrorInfo(1, NWAM0945E);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.svcMachMstrPk, (BigDecimal) ssmResult.getResultObject());
            }
        }
        // 2017/09/22 QC#18859 Add End
    }
    
    // 2019/01/24 S21_NA#29446 Add Start
    private void doProcess_NWAL1630Scrn00_OnBlur_SerNum(NWAL1630CMsg bizMsg) {
        NWAL1630CommonLogic.initClear(bizMsg);
        if (!ZYPCommonFunc.hasValue(bizMsg.serNum)) {
            return;
        }
        NWAL1630CommonLogic.deriveSvcMachMstrPk(bizMsg);
        NWAL1630CommonLogic.driveContrCapInfo(bizMsg, getGlobalCompanyCode());
    }
    
    private void doProcess_NWAL1630Scrn00_OnBlur_ContrNum(NWAL1630CMsg bizMsg) {
        NWAL1630CommonLogic.initClear(bizMsg);
        if (!ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            return;
        }
        NWAL1630CommonLogic.deriveSvcMachMstrPk(bizMsg);
        NWAL1630CommonLogic.driveContrCapInfo(bizMsg, getGlobalCompanyCode());
    }
    
    private void doProcess_NWAL1630_NSAL0110(NWAL1630CMsg bizMsg) {
        NWAL1630CommonLogic.deriveSvcMachMstrPk(bizMsg);
        NWAL1630CommonLogic.driveContrCapInfo(bizMsg, getGlobalCompanyCode());
    }

    private void doProcess_NWAL1630_NSAL1240(NWAL1630CMsg bizMsg) {
        NWAL1630CommonLogic.deriveSvcMachMstrPk(bizMsg);
        NWAL1630CommonLogic.driveContrCapInfo(bizMsg, getGlobalCompanyCode());
    }
    // 2019/01/24 S21_NA#29446 Add End
}
