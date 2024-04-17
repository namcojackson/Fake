/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0170;

import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8121E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8373E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8375E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8376E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8377E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.NMAM8378E;
import static business.blap.NMAL0170.constant.NMAL0170Constant.ZZZM9004E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0170.common.NMAL0170CommonLogic;
import business.db.SUPD_RELN_STAGETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0170BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Arima          Create          N/A
 * 2015/12/11   Fujitsu         T.Arima          Update          N/A
 * 2016/01/04   Fujitsu         M.Nakamura       Update          QC#2528
 * 2016/02/23   CITS            S.Tanikawa       Update          QC#2669
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17259
 *</pre>
 */
public class NMAL0170BL06 extends S21BusinessHandler {

    @Override
    /**
     * Do Process Event.
     * @param cMsg Business Message
     * @param sMsg Global Message
     */
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;
            NMAL0170SMsg glblMsg = (NMAL0170SMsg) sMsg;

            if ("NMAL0170Scrn00_View".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_View(bizMsg, glblMsg);
            // ADD START 2016/02/24 QC#2669
            } else if ("NMAL0170Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL0170Scrn00_CMN_Submit(bizMsg, glblMsg);
            }
            // ADD END 2016/02/24 QC#2669
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * View Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NMAL0170Scrn00_View(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        int index = (ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", CHKBOX_ON_Y)).get(0);

        NMAL0170_ACMsg lineMsg = bizMsg.A.no(index);

        // ADD START 2016/01/04 QC#2528
        bizMsg.supdRelnStagePk_P.clear();
        // ADD END   2016/01/04 QC#2528

        // DELETE START 2016/02/02/23 QC#2669
        // boolean isError = false;
        // if (NMAL0170CommonLogic.findMdse(this.getGlobalCompanyCode(), lineMsg.supdFromMdseCd_A1.getValue()) == null) {
        //     lineMsg.supdFromMdseCd_A1.setErrorInfo(1, NMAM8121E, new String[] {lineMsg.supdFromMdseCd_A1.getValue() });
        //     isError = true;
        // }
        // if (NMAL0170CommonLogic.findMdse(this.getGlobalCompanyCode(), lineMsg.supdToMdseCd_A1.getValue()) == null) {
        //     lineMsg.supdToMdseCd_A1.setErrorInfo(1, NMAM8121E, new String[] {lineMsg.supdFromMdseCd_A1.getValue() });
        //     isError = true;
        // }
        // if (isError) {
        //     return;
        // }
        // DELETE END 2016/02/02/23 QC#2669

        //UPDATE START 12/11/2015
        // If blank ItemNumber or Supersedes at lineMsg, Check findMdse method.
        // so the case that "ssmResult" is null is do nothing.
        S21SsmEZDResult ssmResult = NMAL0170Query.getInstance().existSupdRelnStagePk(lineMsg);

        if (ssmResult != null && ssmResult.isCodeNormal()) {

            // DEL START 2016/01/04 QC#2528
            List<Object> resultList = (List<Object>) ssmResult.getResultObject();
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
//            // Check duplicated
//            String itemType = (String) resultMap.get("MDSE_ITEM_TP_CD");
//            String itemClass = (String) resultMap.get("MDSE_ITEM_CLS_TP_CD");
//            if (itemType.equals(lineMsg.mdseItemTpCd_A1.getValue()) == false || itemClass.equals(lineMsg.mdseItemClsTpCd_A1.getValue()) == false) {
//                lineMsg.supdToMdseCd_A1.setErrorInfo(1, NMAM0072E, new String[] {lineMsg.supdToMdseCd_A1.getValue() });
//                lineMsg.supdFromMdseCd_A1.setErrorInfo(1, NMAM0072E, new String[] {lineMsg.supdFromMdseCd_A1.getValue() });
//                return;
//            }
            // DEL END   2016/01/04 QC#2528
            // Not Error.
//          ZYPEZDItemValueSetter.setValue(bizMsg.supdRelnStagePk_P, lineMsg.supdRelnStagePk_A1);
            ZYPEZDItemValueSetter.setValue(bizMsg.supdRelnStagePk_P, (BigDecimal) resultMap.get("SUPD_RELN_STAGE_PK"));
            return;

        // UPDATE START 2016/02/23 QC#2669
        } else {
            // when ssmResult is null or primary key was not in SupdRelnStagePk,
            // 
            bizMsg.setMessageInfo(NMAM8373E, new String[] {});

            return;
        }

        // //UPDATE END 12/11/2015
        // BigDecimal seq = NMAL0170CommonLogic.getSupdRelnStageSeq();
        //
        // if (!NMAL0170CommonLogic.insertSupdRelnStage(getGlobalCompanyCode(), seq, lineMsg)) {
        // // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
        // bizMsg.setMessageInfo("ZZMM0001E", new String[] {"SUPD", "MDSE_CD", lineMsg.supdToMdseCd_A1.getValue() });
        // return;
        // }
        //
        // // DEL START 2016/01/04 QC#2528
        //// if (!NMAL0170CommonLogic.updateDsMdseInfo(getGlobalCompanyCode(), lineMsg)) {
        //// // ZZMM0015E=0,Data update failure. [ TableName = @ , key = @, value = @ ]
        //// bizMsg.setMessageInfo("ZZMM0015E", new String[] {"DS_MDSE_INFO", "MDSE_CD", lineMsg.supdToMdseCd_A1.getValue() });
        //// }
        // // DEL END   2016/01/04 QC#2528
        // // ADD START 2015/12/14
        // ZYPEZDItemValueSetter.setValue(lineMsg.supdRelnStagePk_A1, seq);
        // // ADD END 2015/12/14
        // ZYPEZDItemValueSetter.setValue(bizMsg.supdRelnStagePk_P, seq);

        // UPDATE START 2016/02/23 QC#2669
    }
    // ADD START 2016/02/24 QC#2669
    private void doProcess_NMAL0170Scrn00_CMN_Submit(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        
        // ADD START S21_NA #17259
        if (glblMsg.B.getValidCount() > 0) {
            for (int index = 0; index < glblMsg.B.getValidCount(); index++) {
                SUPD_RELN_STAGETMsg supdRelnStageTMsg = new SUPD_RELN_STAGETMsg();
                ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.supdRelnStagePk, glblMsg.B.no(index).supdRelnStagePk_A2);
                SUPD_RELN_STAGETMsg resTMsg = (SUPD_RELN_STAGETMsg) EZDTBLAccessor.findByKey(supdRelnStageTMsg);
                if (resTMsg == null) {
                    continue;
                }
                if (ZYPDateUtil.isSameTimeStamp(glblMsg.B.no(index).xxRecHistUpdTs_A2.getValue(), glblMsg.B.no(index).ezUpTimeZone_A2.getValue(),
                        resTMsg.ezUpTime.getValue(), resTMsg.ezUpTimeZone.getValue())) {
                    EZDTBLAccessor.logicalRemove(resTMsg);
                } else {
                    bizMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
            }

        }
        // ADD END S21_NA #17259

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NMAL0170_ACMsg lineMsg = bizMsg.A.no(i);

            // if this line is new
            if (lineMsg.supdRelnStagePk_A1.getValue() == null) {

                // Input Item Number Master check
                if (!NMAL0170CommonLogic.findMdse(this.getGlobalCompanyCode(), lineMsg.supdToMdseCd_A1.getValue())) {
                    lineMsg.supdToMdseCd_A1.setErrorInfo(1, NMAM8121E, new String[] {lineMsg.supdToMdseCd_A1.getValue() });
                    return;
                }

                // Input Supersede Master check
                if (!NMAL0170CommonLogic.findMdse(this.getGlobalCompanyCode(), lineMsg.supdFromMdseCd_A1.getValue())) {
                    lineMsg.supdFromMdseCd_A1.setErrorInfo(1, NMAM8121E, new String[] {lineMsg.supdFromMdseCd_A1.getValue() });
                    return;
                }

                // when supdTo and supdFrom is same
                if (lineMsg.supdToMdseCd_A1.getValue().equals(lineMsg.supdFromMdseCd_A1.getValue())) {
                    lineMsg.supdFromMdseCd_A1.setErrorInfo(1, NMAM8375E, new String[] {});
                    return;
                }

                // when Item Number and Supersedes is Exist in SUPD_RELN/SUPD_STAGE
                if (NMAL0170CommonLogic.isExistSupdReln(lineMsg)) {
                    lineMsg.supdFromMdseCd_A1.setErrorInfo(1, NMAM8376E, new String[] {});
                    lineMsg.supdToMdseCd_A1.setErrorInfo(1, NMAM8376E, new String[] {});
                    return;
                }

                // when supsersedes already has relation with other Items
                if (NMAL0170CommonLogic.isExistSupdFrom(lineMsg)) {
                    lineMsg.supdFromMdseCd_A1.setErrorInfo(1, NMAM8377E, new String[] {});
                    return;
                }

                // when Supersudes Relation is Looping
                if (NMAL0170CommonLogic.isLoopSupd(lineMsg)) {
                    lineMsg.supdToMdseCd_A1.setErrorInfo(1, NMAM8378E, new String[] {});
                    return;
                }
                // get new PK
                BigDecimal seq = NMAL0170CommonLogic.getSupdRelnStageSeq();

                if (!NMAL0170CommonLogic.insertSupdRelnStage(getGlobalCompanyCode(), seq, lineMsg)) {
                    // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                    bizMsg.setMessageInfo("ZZMM0001E", new String[] {"SUPD", "MDSE_CD", lineMsg.supdToMdseCd_A1.getValue() });
                    return;
                }
                ZYPEZDItemValueSetter.setValue(lineMsg.supdRelnStagePk_A1, seq);
            }
        }
    }
    // ADD END 2016/02/24 QC#2669
}
