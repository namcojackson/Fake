/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1620;

//import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0871E;
import static business.blap.NWAL1620.constant.NWAL1620Constant.COPY_FROM_MODE;
import static business.blap.NWAL1620.constant.NWAL1620Constant.COPY_MODE;
import static business.blap.NWAL1620.constant.NWAL1620Constant.HEADER_MODE;
import static business.blap.NWAL1620.constant.NWAL1620Constant.LINE_MODE;
import static business.blap.NWAL1620.constant.NWAL1620Constant.NWAM0694E;
import static business.blap.NWAL1620.constant.NWAL1620Constant.NWAM0696E;
import static business.blap.NWAL1620.constant.NWAL1620Constant.ZZM9037E;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1620.common.NWAL1620CommonLogic;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1620BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 * 2017/09/21   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1620BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1620CMsg bizMsg = (NWAL1620CMsg) cMsg;

            if ("NWAL1620_INIT".equals(screenAplID)) {
                doProcess_NWAL1620_INIT(bizMsg);
            } else if ("NWAL1620Scrn00_CMN_OK".equals(screenAplID)) {
                doProcess_NWAL1620Scrn00_CMN_OK(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * NWAL1620_INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1620_INIT(NWAL1620CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(CONFIG_CATG.class, bizMsg.configCatgCd_CD, bizMsg.configCatgDescTxt_DI);
        NWAL1620CommonLogic.getConfigCatg(bizMsg);
    }

    /**
     * CMN_OK Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL1620Scrn00_CMN_OK(NWAL1620CMsg bizMsg) {
        // mod start 2016/07/29 CSA S21_NA#12607
        // check Credit Rebill Order
        S21SsmEZDResult ssmRslt = NWAL1620Query.getInstance().cntCreditRebillOrder(bizMsg);
        if (ssmRslt.isCodeNormal()) {
            Map<String, BigDecimal> rsltMap = (Map<String, BigDecimal>) ssmRslt.getResultObject();
            // check CreditRebill order
            if (rsltMap.get("CNT").compareTo(BigDecimal.ZERO) > 0) {
                bizMsg.setMessageInfo("NWAM0871E");
                return;
            }
        }
        // mod end 2016/07/29 CSA S21_NA#12607
        if (COPY_MODE.equals(bizMsg.xxModeCd_CP.getValue())) {
            if (HEADER_MODE.equals(bizMsg.xxModeCd.getValue())) {
                // Add Start 2017/09/20 S21_NA#18859
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_R.getValue())) {
                    CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                    cpoDtlTMsg.setSQLID("001");
                    cpoDtlTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
                    cpoDtlTMsg.setConditionValue("cpoOrdNum01", bizMsg.cpoOrdNum.getValue());
                    CPO_DTLTMsgArray tMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(cpoDtlTMsg);

                    if (tMsgArray.getValidCount() <= 0) {
                        bizMsg.setMessageInfo(NWAM0694E);
                        return;
                    }
                }
                // Add End 2017/09/20 S21_NA#18859
                return;
            }

            // Copy : Config Mode and Line Mode
            if (!NWAL1620CommonLogic.checkNumOfCopy(bizMsg)) {
                bizMsg.setMessageInfo(ZZM9037E);
            }

            return;
        }

        if (COPY_FROM_MODE.equals(bizMsg.xxModeCd_CP.getValue())) {
            // Copy From : Header Mode
            if (HEADER_MODE.equals(bizMsg.xxModeCd.getValue())) {
                CPOTMsg cpoTMsg = new CPOTMsg();

                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);

                CPOTMsg tMsgResult = (CPOTMsg) S21CacheTBLAccessor.findByKey(cpoTMsg);
                // has no result
                if (tMsgResult == null) {
                    bizMsg.setMessageInfo(NWAM0696E);
                }
                return;
            }

            // Copy From : Config Mode and Line Mode
            if (!NWAL1620CommonLogic.existCpoConfigLine(bizMsg)) {
                bizMsg.setMessageInfo(NWAM0694E);
                return;
            }

            // Copy From : Line Mode
            if (LINE_MODE.equals(bizMsg.xxModeCd.getValue())) {
                if (!NWAL1620CommonLogic.checkNumOfCopy(bizMsg)) {
                    bizMsg.setMessageInfo(ZZM9037E);
                }
            }
        }
    }
}
