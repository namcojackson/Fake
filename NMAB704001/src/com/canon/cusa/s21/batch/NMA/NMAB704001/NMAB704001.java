package com.canon.cusa.s21.batch.NMA.NMAB704001;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDFastTBLAccessor;

import business.db.ART10FMsg;
import business.db.SOM_PRC_BOOK_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

import static com.canon.cusa.s21.batch.NMA.NMAB704001.NMAB704001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB704001.NMAB704001Constant.NWBM0097E;
import static com.canon.cusa.s21.batch.NMA.NMAB704001.NMAB704001Constant.ZZMM0001E;
import static com.canon.cusa.s21.batch.NMA.NMAB704001.NMAB704001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB704001.NMAB704001Constant.MSG_UPLD_CSV_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB704001.NMAB704001Constant.UPLD_CSV_ID;

/**
 *<pre>
 * SFDC Price Upload Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/01   Fujitsu         Mz.Takahashi    Create          SOL#093(QC#20250)
 *</pre>
 */
public class NMAB704001 extends S21BatchMain {

    private String glblCmpyCd;
    private S21RequestBatchHelper batchHelper = null;
    private int totalCnt = 0;
    private int nomalCnt = 0;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    protected void initRoutine() {
        glblCmpyCd = getGlobalCompanyCode();
        batchHelper = new S21RequestBatchHelper();
    }

    protected void mainRoutine() {

        if (!this.batchHelper.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }

        for (ART10FMsg request : this.batchHelper.getRequestList()) {
            try {
                this.doProcess(request);
            } catch (SQLException e) {
                this.termCd = TERM_CD.ABNORMAL_END;
                sqlExceptionHandler(e);
            }
        }
    }

    private void doProcess(ART10FMsg fMsg) throws SQLException{
        // Upload ID
        String upldCsvId = getUpldCsvId(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_ID" + upldCsvId, this);

        if (!UPLD_CSV_ID.equals(upldCsvId)){
            return;
        }

        // Upload Request PK
        BigDecimal upldCsvRqstPk = getUpldCsvReqPk(fMsg);
        EZDDebugOutput.println(0, "UPLD_CSV_RQST_PK" + upldCsvRqstPk, this);

        // Remove SOM_PRC_BOOK_INFO
        NMAB704001Query.getInstance().truncateSomPrcBookInfo();

        int range = this.getCommitCount();
        int startIndex = 1;
        int endIndex = range;

        while(copyWrk2SomPrcBookInfo(fMsg, upldCsvRqstPk, startIndex, endIndex)){
            startIndex += range;
            endIndex += range;
        }
    }

    
    private Boolean copyWrk2SomPrcBookInfo(ART10FMsg fMsg, BigDecimal upldCsvRqstPk, int startIndex, int endIndex){
        Boolean isContinue = false;
        Boolean isCommit = false;
        int maxSize = 0;
        int maxCount = 0;

        try{
            S21SsmEZDResult result = NMAB704001Query.getInstance().getWrk(upldCsvRqstPk, startIndex, endIndex + 1);

            if (result.isCodeNormal()) {
                List resultList = (List) result.getResultObject();
                maxSize = resultList.size();
                maxCount = maxSize;
                
                if (maxSize > (endIndex - startIndex + 1)){
                    maxCount--;
                }

                List<SOM_PRC_BOOK_INFOTMsg> tmsgList = new ArrayList<SOM_PRC_BOOK_INFOTMsg>();

                for (int index = 0; index < maxCount;index++){
                    Map map = (Map) resultList.get(index);

                    SOM_PRC_BOOK_INFOTMsg tmsg = new SOM_PRC_BOOK_INFOTMsg();

                    //
                    // SOM_PRC_BOOK_INFO
                    //

                    //SOM_PRC_BOOK_INFO_PK
                    ZYPEZDItemValueSetter.setValue(tmsg.somPrcBookInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SOM_PRC_BOOK_INFO_SQ));

                    //Global Company Code
                    ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);

                    // SOM_PRC_BOOK_NM
                    ZYPEZDItemValueSetter.setValue(tmsg.somPrcBookNm, (String)map.get("SOM_PRC_BOOK_NM"));

                    // SOM_CATG_NM
                    ZYPEZDItemValueSetter.setValue(tmsg.somCatgNm, (String)map.get("SOM_CATG_NM"));

                    // SOM_SUB_CATG_NM
                    ZYPEZDItemValueSetter.setValue(tmsg.somSubCatgNm, (String)map.get("SOM_SUB_CATG_NM"));

                    // SOM_SUB_TP_NM
                    ZYPEZDItemValueSetter.setValue(tmsg.somSubTpNm, (String)map.get("SOM_SUB_TP_NM"));

                    // SOM_ITEM_NM
                    ZYPEZDItemValueSetter.setValue(tmsg.somItemNm, (String)map.get("SOM_ITEM_NM"));

                    // SOM_PROD_DESC_TXT
                    ZYPEZDItemValueSetter.setValue(tmsg.somProdDescTxt, (String)map.get("SOM_PROD_DESC_TXT"));

                    // SOM_COST_PRC_TXT
                    ZYPEZDItemValueSetter.setValue(tmsg.somCostPrcTxt, (String)map.get("SOM_COST_PRC_TXT"));

                    // SOM_PRF_PRC_TXT
                    ZYPEZDItemValueSetter.setValue(tmsg.somPrfPrcTxt, (String)map.get("SOM_PRF_PRC_TXT"));

                    // SOM_PRC_BOOK_NOTE_TXT
                    ZYPEZDItemValueSetter.setValue(tmsg.somPrcBookNoteTxt, "N");

                    // SOM_PRC_BOOK_STS_TXT
                    ZYPEZDItemValueSetter.setValue(tmsg.somPrcBookStsTxt, "N");

                    tmsgList.add(tmsg);

                    totalCnt++;
                }

                int i = 0;
                EZDTMsg[] tmsgs = new EZDTMsg[tmsgList.size()];
                for (SOM_PRC_BOOK_INFOTMsg tmsg : tmsgList) {
                    tmsgs[i++] = tmsg;
                }

                int ret = EZDFastTBLAccessor.insert(tmsgs);

                if (ret != tmsgs.length) {
                    S21InfoLogOutput.println(ZZMM0001E, new String[]{"SOM_PRC_BOOK_INFO", "UPLD_CSV_RQST_PK", upldCsvRqstPk.toPlainString()});
                    this.termCd = TERM_CD.WARNING_END;
                    return isContinue;
                }

                isCommit = true;
                nomalCnt += maxCount;
            }
        } finally{
            if (isCommit){
                this.commit();

                if (maxSize > (endIndex - startIndex + 1)){
                    isContinue = true;
                } else {
                    this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.NOMAL_END);
                }
            } else {
                this.batchHelper.updateProcessStatus(fMsg, REQUEST_STATUS.WARING_END);
                this.rollback();
            }
        }

        return isContinue;
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV Request PK
     */
    private BigDecimal getUpldCsvReqPk(ART10FMsg fMsg) {

        // UploadCsvRequestPK
        String upldCsvReqPk = fMsg.EZREQCondition.getValue();
        String[] msg = {MSG_UPLD_CSV_RQST_PK };
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }
        if (!EZDCommonFunc.isNumeric(removeDQRequestPK)) {
            throw new S21AbendException(NWBM0097E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private String getUpldCsvId(ART10FMsg fMsg) {

        String uploadCsvId = fMsg.EZREQUserKey.getValue();
        if (S21StringUtil.isEmpty(uploadCsvId)) {
            String[] msg = {MSG_UPLD_CSV_ID };
            throw new S21AbendException(NMAM8228E, msg);
        }

        return S21RequestBatchHelper.removeDoubleQuotation(uploadCsvId);
    }

    @Override
    protected void termRoutine() {
        this.setTermState(termCd, nomalCnt, totalCnt - nomalCnt, totalCnt);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NMAB704001().executeBatch();
    }

}
