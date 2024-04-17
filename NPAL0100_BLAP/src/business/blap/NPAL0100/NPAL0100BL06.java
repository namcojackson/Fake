/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2011/10/27   CSAI            N.Sasaki        Create          362045
 *</pre>
 */
package business.blap.NPAL0100;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL0100.common.NPAL0100CommonLogic;
import business.blap.NPAL0100.constant.NPAL0100Constant;
import business.db.PO_SER_NUMTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NPAL0100BL06 extends S21BusinessHandler implements NPAL0100Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String scrID = cMsg.getScreenAplID();
            NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;
            NPAL0100SMsg globalMsg = (NPAL0100SMsg) sMsg;

            NPAL0100CommonLogic.setEventId(cMsg);
            if ("NPAL0100Scrn00_CMN_Submit".equals(scrID)) {
                doProcess_NPAL0100Scrn00_CMN_Submit(bizMsg, globalMsg);
            } else {
                throw new S21AbendException("Illegal ScreenAplID : " + scrID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL0100Scrn00_CMN_Submit(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {

        // Set PO_DTL info
        NPAL0100Query.getInstance().getPoDtlInfo(bizMsg);
        // Check intangible
        NPAL0100Query.getInstance().checkIntangibleItem(bizMsg);
        // Register serials
        registeSerialNumber(bizMsg, globalMsg);
        // Reset list
        NPAL0100CommonLogic.setSerialNumberList(bizMsg, globalMsg);

        setSerialNumberData(bizMsg, globalMsg);
        for (int i = 0; i < globalMsg.B.length(); i++) {
            EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
            EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
        }
    }

    private void registeSerialNumber(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {

        if (isQtyUpdated(bizMsg)) {
            return;
        }
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            String newSerNum = globalMsg.B.no(i).serNum_B1.getValue();
            // Serial# is not blank and only it's changed
            if (ZYPCommonFunc.hasValue(newSerNum)) {
                long rowNum = globalMsg.B.no(i).xxRowNum_B2.getValueInt();
                createPOSerNum(bizMsg, globalMsg, newSerNum, rowNum);
            }
        }
    }

    private static void createPOSerNum(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg, String newSerNum, long rowNum) {

        Map<String, String> ssmParam = getParamMap(bizMsg, null, Long.toString(rowNum), SORT_BY_LATEST_TS);

        S21SsmEZDResult result = NPAL0100Query.getInstance().getOriginalRecord(ssmParam);
        if (result.isCodeNormal()) {
            // Already exists
            List list = (List) result.getResultObject();
            if (list != null && list.size() > 0) {
                Map map = (Map) list.get(0);
                // Record will not be created
                // for the same Serial#
                String currentSerNum = (String) map.get("SER_NUM");
                if (currentSerNum.equalsIgnoreCase(newSerNum)) {
                    return;
                }
//                String oldSerNum = NPAL0100CommonLogic.checkNull(NPAL0100Query.getInstance().getOldSerNum(ssmParam));
                // Create as 281
                createUpdate(bizMsg, newSerNum, Long.toString(rowNum), currentSerNum, map);
            }
        } else {
            // Create as 280
            createNew(bizMsg, newSerNum, Long.toString(rowNum));
        }
    }

    private static void createNew(NPAL0100CMsg bizMsg, String serNum, String rowNum) {

        PO_SER_NUMTMsg tMsgIns = new PO_SER_NUMTMsg();

        tMsgIns.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsgIns.poSerNumPk.setValue(getPoSerNumSq());
        tMsgIns.serOwnrId.setValue(SER_OWNR_ID);
        tMsgIns.mdseCd.setValue(NPAL0100CommonLogic.checkNull(bizMsg.mdseCd.getValue()));
        tMsgIns.serNum.setValue(serNum);
        tMsgIns.serEventTs.setValue(ZYPDateUtil.getCurrentSystemTime(YYYYMMDD_HHMMSS_SSS));
        tMsgIns.serLocGrpCd.setValue(SER_LOC_GRP.DEALER_OR_RETAILER_OR_DISTRIBUTOR);
        tMsgIns.serEventCd.setValue("280");

        tMsgIns.fromLocCd.setValue(NPAL0100CommonLogic.checkNull(bizMsg.vndCd.getValue()));
        tMsgIns.fromLocNm.setValue(NPAL0100CommonLogic.checkNull(bizMsg.locNm_H1.getValue()));
        tMsgIns.toLocCd.setValue(NPAL0100CommonLogic.checkNull(bizMsg.toLocCd.getValue()));
        tMsgIns.toLocNm.setValue(NPAL0100CommonLogic.checkNull(bizMsg.toLocNm.getValue()));

        tMsgIns.oldSerNum.clear();

        tMsgIns.keyInfoCd_01.setValue(NPAL0100CommonLogic.checkNull(bizMsg.cpoOrdNum.getValue()));
        tMsgIns.keyInfoCd_02.setValue(NPAL0100CommonLogic.checkNull(bizMsg.cpoDtlLineNum.getValue()));
        tMsgIns.keyInfoCd_03.setValue(NPAL0100CommonLogic.checkNull(bizMsg.cpoDtlLineSubNum.getValue()));

        tMsgIns.keyInfoCd_04.setValue(NPAL0100CommonLogic.checkNull(bizMsg.poOrdNum.getValue()));
        tMsgIns.keyInfoCd_05.setValue(NPAL0100CommonLogic.checkNull(bizMsg.poOrdDtlLineNum.getValue()));

        tMsgIns.keyInfoCd_06.setValue(NPAL0100CommonLogic.checkNull(bizMsg.poRcvNum.getValue()));
        tMsgIns.keyInfoCd_07.setValue(NPAL0100CommonLogic.checkNull(bizMsg.poRcvLineNum.getValue()));

        if (NPAL0100CommonLogic.isIntangibleItem(bizMsg)) {
            long maxRowNum = getMaxRowNum(bizMsg, "280");
            tMsgIns.keyInfoCd_08.setValue(Long.toString(maxRowNum + 1));
        } else {
            tMsgIns.keyInfoCd_08.setValue(rowNum);
        }
        tMsgIns.keyInfoCd_09.setValue(NPAL0100CommonLogic.checkNull(bizMsg.setMdseCd.getValue()));
        tMsgIns.serNumSendFlg.setValue(ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.create(tMsgIns);
    }

    private static void createUpdate(NPAL0100CMsg bizMsg, String serNum, String rowNum, String oldSerNum, Map map) {

        PO_SER_NUMTMsg tMsgUpd = new PO_SER_NUMTMsg();

        tMsgUpd.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        tMsgUpd.poSerNumPk.setValue(getPoSerNumSq());
        tMsgUpd.serOwnrId.setValue((String) map.get("SER_OWNR_ID"));
        tMsgUpd.mdseCd.setValue((String) map.get("MDSE_CD"));
        tMsgUpd.serNum.setValue(serNum);
        tMsgUpd.serEventTs.setValue(ZYPDateUtil.getCurrentSystemTime(YYYYMMDD_HHMMSS_SSS));
        tMsgUpd.serLocGrpCd.setValue((String) map.get("SER_LOC_GRP_CD"));
        tMsgUpd.serEventCd.setValue("281");

        tMsgUpd.fromLocCd.setValue(NPAL0100CommonLogic.checkNull((String) map.get("FROM_LOC_CD")));
        tMsgUpd.fromLocNm.setValue(NPAL0100CommonLogic.checkNull((String) map.get("FROM_LOC_NM")));
        tMsgUpd.toLocCd.setValue(NPAL0100CommonLogic.checkNull((String) map.get("TO_LOC_CD")));
        tMsgUpd.toLocNm.setValue(NPAL0100CommonLogic.checkNull((String) map.get("TO_LOC_NM")));

        tMsgUpd.oldSerNum.setValue(oldSerNum);

        tMsgUpd.keyInfoCd_01.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_01")));
        tMsgUpd.keyInfoCd_02.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_02")));
        tMsgUpd.keyInfoCd_03.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_03")));

        tMsgUpd.keyInfoCd_04.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_04")));
        tMsgUpd.keyInfoCd_05.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_05")));

        tMsgUpd.keyInfoCd_06.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_06")));
        tMsgUpd.keyInfoCd_07.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_07")));

        tMsgUpd.keyInfoCd_08.setValue(rowNum);
        tMsgUpd.keyInfoCd_09.setValue(NPAL0100CommonLogic.checkNull((String) map.get("KEY_INFO_CD_09")));
        tMsgUpd.serNumSendFlg.setValue(ZYPConstant.FLG_OFF_N);

        EZDTBLAccessor.create(tMsgUpd);
    }

    private static long getMaxRowNum(NPAL0100CMsg bizMsg, String serEventCd) {
        long max = 0;
        Map<String, String> ssmParam = getParamMap(bizMsg, serEventCd, null, null);
        S21SsmEZDResult result = NPAL0100Query.getInstance().getRownums(ssmParam);
        if (result.isCodeNormal()) {
            List list = (List) result.getResultObject();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String rowNumStr = NPAL0100CommonLogic.checkNull((String) map.get("ROW_NUM"));
                    if (ZYPCommonFunc.hasValue(rowNumStr) && NPAL0100CommonLogic.isNumber(rowNumStr)) {
                        long rowNum = Long.parseLong(rowNumStr);
                        if (rowNum > max) {
                            max = rowNum;
                        }
                    }
                }
            }
        }
        return max;
    }

    private static Map<String, String> getParamMap(NPAL0100CMsg bizMsg, String serEventCd, String rowNum, String sortKey ) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        map.put("serOwnrId", SER_OWNR_ID);
        map.put("mdseCd", bizMsg.mdseCd.getValue());
        map.put("serLocGrpCd", SER_LOC_GRP.DEALER_OR_RETAILER_OR_DISTRIBUTOR);
        map.put("serEventCd", serEventCd);

        map.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        map.put("cpoDtlLineNum", bizMsg.cpoDtlLineNum.getValue());
        map.put("cpoDtlLineSubNum", bizMsg.cpoDtlLineSubNum.getValue());
        map.put("poOrdNum", bizMsg.poOrdNum.getValue());
        map.put("poOrdDtlLineNum", bizMsg.poOrdDtlLineNum.getValue());

        map.put("poRcvNum", bizMsg.poRcvNum.getValue());
        map.put("poRcvLineNum", bizMsg.poRcvLineNum.getValue());
        map.put("rowNum", rowNum);
        
        map.put("invtyCtrlFlg", bizMsg.invtyCtrlFlg.getValue());
        map.put("sortKey", sortKey);

        return map;
    }

    private static BigDecimal getPoSerNumSq() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(PO_SER_NUM_SQ);
    }

    private boolean isQtyUpdated(NPAL0100CMsg bizMsg) {
        long origPoQty = bizMsg.poQty.getValueInt();
        long latestPoQty = bizMsg.poQty_2.getValueInt();
        if (origPoQty != latestPoQty) {
            // NPAM0006E=0,This data has been updated by another user.
            bizMsg.setMessageInfo("NPAM0006E", null);
            return true;
        } else {
            return false;
        }
    }
    
    private void setSerialNumberData(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {
        // Check PageShowFromNum
        if (bizMsg.xxPageShowFromNum.getValueInt() == 0) {
            return;
        }

        int qtyNum = 0;
        for (int i = 0; i < globalMsg.B.length(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).xxRowNum_B2)) {
                qtyNum++;
            } else {
                break;
            }
        }
        int pageShowFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (PROC_FLG_UPDATE.equals(bizMsg.A.no(i).xxSetFlg_A1.getValue())) {
                if (!bizMsg.A.no(i).serNum_A1.getValue().equals(globalMsg.B.no(pageShowFrom + i).serNum_B1.getValue())) {
                    EZDMsg.copy(bizMsg.A.no(i), "A1".toString(), globalMsg.B.no(pageShowFrom + i), "B1".toString());
                    EZDMsg.copy(bizMsg.A.no(i), "A2".toString(), globalMsg.B.no(pageShowFrom + i), "B2".toString());
                }

            } else if (PROC_FLG_NEW.equals(bizMsg.A.no(i).xxSetFlg_A1.getValue()) && !ZYPCommonFunc.hasValue(globalMsg.B.no(pageShowFrom + i).serNum_B1)) {

                EZDMsg.copy(bizMsg.A.no(i), "A1".toString(), globalMsg.B.no(qtyNum), "B1".toString());
                EZDMsg.copy(bizMsg.A.no(i), "A2".toString(), globalMsg.B.no(qtyNum), "B2".toString());
                // rownum numbering
                globalMsg.B.no(qtyNum).xxRowNum_B1.setValue(qtyNum + 1);
                globalMsg.B.no(qtyNum).xxRowNum_B2.setValue(qtyNum + 1);

                // copy globalMsg to bizMsg, case empty record
                // include.
                EZDMsg.copy(globalMsg.B.no(pageShowFrom + i), "B1".toString(), bizMsg.A.no(i), "A1".toString());
                EZDMsg.copy(globalMsg.B.no(pageShowFrom + i), "B2".toString(), bizMsg.A.no(i), "A2".toString());

                qtyNum++;
            }
        }

        for (int i = pageShowFrom; i < pageShowFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.B.getValidCount()) {
                EZDMsg.copy(globalMsg.B.no(i), "B1".toString(), bizMsg.A.no(i - pageShowFrom), "A1".toString());
                EZDMsg.copy(globalMsg.B.no(i), "B2".toString(), bizMsg.A.no(i - pageShowFrom), "A2".toString());
            } else {
                break;
            }
        }
    }
}
