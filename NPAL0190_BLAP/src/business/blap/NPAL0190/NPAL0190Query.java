/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0190;

import static business.blap.NPAL0190.constant.NPAL0190Constant.BIND_GLBL_CMPY_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.BIND_MDSE_ITEM_TP_CD_TEXT_ITEM;
import static business.blap.NPAL0190.constant.NPAL0190Constant.BIND_PO_DISP_LINE_NUM;
import static business.blap.NPAL0190.constant.NPAL0190Constant.BIND_PO_ORD_NUM;
import static business.blap.NPAL0190.constant.NPAL0190Constant.BIND_ROW_NUM;
import static business.blap.NPAL0190.constant.NPAL0190Constant.NZZM0001W;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_ACRL_COA_XXXX_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_ASL_MDSE_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_CHRG_COA_XXXX_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_DEST_RTL_SWH_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_DEST_RTL_WH_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_DISP_PO_DTL_LINE_NUM;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_ENT_DEAL_NET_UNIT_PRC_AMT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_ENT_PO_DTL_DEAL_NET_AMT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_EVENT_ID;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_FROM_STK_STS_DESC_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_MDSE_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_MDSE_DESC_SHORT_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PKG_UOM_CLS_DESC_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_APVL_STS_DESC_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_BIZ_PROC_LOG_UPD_TS;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_CANC_QTY;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_DISP_QTY;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_HDR_STS_DESC_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_INV_QTY;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_LINE_STS_DESC_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_MATCH_TP_DESC_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_ORD_DTL_CMNT_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_RCV_QTY;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_PO_SUBMT_PSN_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_RQST_RCV_DT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_SER_NUM_LIST_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_SHIP_TO_CUST_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_SHIP_TO_LOC_NM;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_SRC_RTL_SWH_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_SRC_RTL_WH_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_TO_STK_STS_DESC_TXT;
import static business.blap.NPAL0190.constant.NPAL0190Constant.RS_VAR_COA_XXXX_CD;
import static business.blap.NPAL0190.constant.NPAL0190Constant.SQL_GET_BIZ_PROC_LOG;
import static business.blap.NPAL0190.constant.NPAL0190Constant.ZZZM9005W;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2012   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 * 01/25/2016   CITS            K.Ogino         Update          CSA
 * 03/08/2016   CITS            K.Ogino         Update          QC#4976
 * 12/11/2017   CITS            S.Katsuma       Update          SOL#060(QC#14858)
 * 01/30/2018   CITS            K.Ogino         Update          QC#23616
 * 02/25/2020   Fujitsu         T.Ogura         Update          QC#55898
 *</pre>
 */
public final class NPAL0190Query extends S21SsmEZDQuerySupport {

    /**
     * Instance
     */
    private static final NPAL0190Query INSTANCE = new NPAL0190Query();

    /**
     * Private constructor
     */
    private NPAL0190Query() {
    }

    /**
     * Get instance
     * @return NPAL0190Query
     */
    public static NPAL0190Query getInstance() {
        return INSTANCE;
    }

    /**
     * get S21SsmBatchClient for Init
     * @return S21SsmBatchClient
     */
    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Init Search
     * @param poOrdNum String
     * @param poDispLineNum String
     * @param cMsg NPAL0190CMsg
     * @param sMsg NPAL0190SMsg
     * @return
     */
    boolean getBizProcLog(String poOrdNum, String poDispLineNum, NPAL0190CMsg cMsg, NPAL0190SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(BIND_PO_ORD_NUM, poOrdNum);
        ssmParam.put(BIND_PO_DISP_LINE_NUM, poDispLineNum);
        ssmParam.put(BIND_ROW_NUM, sMsg.A.length() + 1);
        // START 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        // QC#23616
        ssmParam.put(BIND_MDSE_ITEM_TP_CD_TEXT_ITEM, MDSE_ITEM_TP.TEXT_ITEM);
        // END 2017/12/08 S.Katsuma [SOL#060(QC#14858),ADD]
        return (Boolean) getSsmBatchClient().queryObject(SQL_GET_BIZ_PROC_LOG, ssmParam, new SetSearchResult(cMsg, sMsg));
    }

    /**
     * ResultSet
     */
    private static class SetSearchResult extends S21SsmBooleanResultSetHandlerSupport {

        /** */
        private NPAL0190SMsg sMsg;

        /** */
        private NPAL0190CMsg cMsg;

        public SetSearchResult(NPAL0190CMsg cMsg, NPAL0190SMsg sMsg) {
            this.sMsg = sMsg;
            this.cMsg = cMsg;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.last();
            int cnt = rs.getRow();

            if (cnt == 0) {
                cMsg.setMessageInfo(ZZZM9005W);
                return false;
            }

            rs.first();
            int rowCount = 0;
            do {

                // START 02/25/2020 T.Ogura [QC#55898,ADD]
                if (rowCount + 1 > sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                // END   02/25/2020 T.Ogura [QC#55898,ADD]

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).eventId, rs.getString(RS_EVENT_ID));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).xxScrItem19Txt, rs.getString(RS_PO_BIZ_PROC_LOG_UPD_TS));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poSubmtPsnCd, rs.getString(RS_PO_SUBMT_PSN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poHdrStsDescTxt, rs.getString(RS_PO_HDR_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poApvlStsDescTxt, rs.getString(RS_PO_APVL_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).dispPoDtlLineNum, rs.getString(RS_DISP_PO_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poLineStsDescTxt, rs.getString(RS_PO_LINE_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).mdseCd, rs.getString(RS_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).aslMdseCd, rs.getString(RS_ASL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).mdseDescShortTxt, rs.getString(RS_MDSE_DESC_SHORT_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).entDealNetUnitPrcAmt, rs.getBigDecimal(RS_ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poDispQty, rs.getBigDecimal(RS_PO_DISP_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).pkgUomClsDescTxt, rs.getString(RS_PKG_UOM_CLS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).entPoDtlDealNetAmt, rs.getBigDecimal(RS_ENT_PO_DTL_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).rqstRcvDt, rs.getString(RS_RQST_RCV_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).srcRtlWhCd, rs.getString(RS_SRC_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).srcRtlSwhCd, rs.getString(RS_SRC_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).destRtlWhCd, rs.getString(RS_DEST_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).destRtlSwhCd, rs.getString(RS_DEST_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).shipToCustCd, rs.getString(RS_SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).shipToLocNm, rs.getString(RS_SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poMatchTpDescTxt, rs.getString(RS_PO_MATCH_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poRcvQty, rs.getBigDecimal(RS_PO_RCV_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poCancQty, rs.getBigDecimal(RS_PO_CANC_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poInvQty, rs.getBigDecimal(RS_PO_INV_QTY));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).stkStsDescTxt_FR, rs.getString(RS_FROM_STK_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).stkStsDescTxt_TO, rs.getString(RS_TO_STK_STS_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).serNumListTxt, rs.getString(RS_SER_NUM_LIST_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).xxScrItem130Txt_CH, rs.getString(RS_CHRG_COA_XXXX_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).xxScrItem130Txt_AC, rs.getString(RS_ACRL_COA_XXXX_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).xxScrItem130Txt_VA, rs.getString(RS_VAR_COA_XXXX_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCount).poOrdDtlCmntTxt, rs.getString(RS_PO_ORD_DTL_CMNT_TXT));
                rowCount++;
                // START 02/25/2020 T.Ogura [QC#55898,DEL]
//                if (rowCount > sMsg.A.length()) {
//                    cMsg.setMessageInfo(NZZM0001W);
//                    break;
//                }
                // END   02/25/2020 T.Ogura [QC#55898,DEL]
            } while (rs.next());
            sMsg.A.setValidCount(rowCount);
            return true;
        }
    }
}
