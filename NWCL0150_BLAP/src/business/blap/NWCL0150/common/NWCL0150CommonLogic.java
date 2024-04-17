/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0150.common;

import static business.blap.NWCL0150.constant.NWCL0150Constant.CFS_LEASE_ATTRB;
import static business.blap.NWCL0150.constant.NWCL0150Constant.CFS_LEASE_PKG_HLD_FLG;
import static business.blap.NWCL0150.constant.NWCL0150Constant.CFS_LEASE_PKG_PO_HDR_PK;
import static business.blap.NWCL0150.constant.NWCL0150Constant.CFS_MAN_HLD_ACT_TP_CD;
import static business.blap.NWCL0150.constant.NWCL0150Constant.CFS_PO_AMT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.CFS_PO_NUM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.CPO_ORD_NUM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.CR_REBIL_HLD_FLG;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DB_PARAM_ATTRB_KEY_NM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DB_PARAM_CFS_LEASE_PKG_HLD_FLG;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DB_PARAM_CPO_ORD_NUM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DB_PARAM_INV_NUM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DB_PARAM_LEASE_PKG_CRAT_FLG;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DB_PARAM_ROWNUM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DS_ACCT_NM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.DS_ACCT_NUM;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EVENT_NM_NWCL0150_CMN_SUBMIT;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EZUPTIME;
import static business.blap.NWCL0150.constant.NWCL0150Constant.EZUPTIMEZONE;
import static business.blap.NWCL0150.constant.NWCL0150Constant.FETCH_SIZE;
import static business.blap.NWCL0150.constant.NWCL0150Constant.INV_CPLT_AMT_RATE;
import static business.blap.NWCL0150.constant.NWCL0150Constant.INV_THRHD;
import static business.blap.NWCL0150.constant.NWCL0150Constant.LEASE_PKG_CRAT_FLG;
import static business.blap.NWCL0150.constant.NWCL0150Constant.NWCM0003I;
import static business.blap.NWCL0150.constant.NWCL0150Constant.NWCM0135I;
import static business.blap.NWCL0150.constant.NWCL0150Constant.NWCM0142E;
import static business.blap.NWCL0150.constant.NWCL0150Constant.PO_INFO_PROC_FLG;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWCL0150.NWCL0150CMsg;
import business.blap.NWCL0150.NWCL0150Query;
import business.blap.NWCL0150.NWCL0150SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 * 09/21/2017   Fujitsu         H.Ikeda         Update          QC#20381
 * 01/04/2018   Fujitsu         W.Honda         Update          QC#21706
 *</pre>
 */
public class NWCL0150CommonLogic {

    /**
     * findCfsLeasePkgPo
     * @param glblCmpyCd String
     * @param cMsg NWCL0150CMsg
     * @param sMsg NWCL0150SMsg
     */
    public static void findCfsLeasePkgPo(String glblCmpyCd, NWCL0150CMsg cMsg, NWCL0150SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);
        if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum)) {
            ssmParam.put(DB_PARAM_CPO_ORD_NUM, cMsg.cpoOrdNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.invNum)) {
            ssmParam.put(DB_PARAM_INV_NUM, cMsg.invNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.cfsLeasePkgHldFlg_SL)) {
            ssmParam.put(DB_PARAM_CFS_LEASE_PKG_HLD_FLG, cMsg.cfsLeasePkgHldFlg_SL.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.leasePkgCratFlg_SL)) {
            ssmParam.put(DB_PARAM_LEASE_PKG_CRAT_FLG, cMsg.leasePkgCratFlg_SL.getValue());
        }

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NWCL0150Query.getInstance().getClass());

            ps = ssmLLClient.createPreparedStatement("findCfsLeasePkgPo", ssmParam, execParam);
            rs = ps.executeQuery();

            if (!rs.next()) {
                if (!EVENT_NM_NWCL0150_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                    cMsg.setMessageInfo(NWCM0003I);
                }
                cMsg.xxPageShowFromNum.clear();
                cMsg.xxPageShowToNum.clear();
                cMsg.xxPageShowOfNum.clear();
                return;
            }

            int rowCnt = 0;
            int queryResCnt = 0;
            do {
                // Max Recode Over
                queryResCnt = rs.getRow();
                if (queryResCnt > sMsg.A.length()) {
                    if (!EVENT_NM_NWCL0150_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                        cMsg.setMessageInfo(NWCM0135I);
                    }
                    break;
                }
                // QC#20381 2017/09/21 Add Start
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).cfsPoNum_A1, rs.getString(CFS_PO_NUM));
                // QC#20381 2017/09/21 Add End
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).cpoOrdNum_A1, rs.getString(CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).dsAcctNum_A1, rs.getString(DS_ACCT_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).dsAcctNm_A1, rs.getString(DS_ACCT_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).cfsPoAmt_A1, rs.getBigDecimal(CFS_PO_AMT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).invCpltAmtRate_A1, rs.getBigDecimal(INV_CPLT_AMT_RATE));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).crRebilHldFlg_A1, rs.getString(CR_REBIL_HLD_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).cfsLeasePkgHldFlg_A1, rs.getString(CFS_LEASE_PKG_HLD_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).leasePkgCratFlg_A1, rs.getString(LEASE_PKG_CRAT_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).cfsManHldActTpCd_A1, rs.getString(CFS_MAN_HLD_ACT_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).cfsLeasePkgPoHdrPk_A1, rs.getBigDecimal(CFS_LEASE_PKG_PO_HDR_PK));
                // QC#21706 2018/01/04 Add Start
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).poInfoProcFlg_A1, rs.getString(PO_INFO_PROC_FLG));
                // QC#20381 2018/01/04 Add End
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).ezUpTime_A1, rs.getString(EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(rowCnt).ezUpTimeZone_A1, rs.getString(EZUPTIMEZONE));

                rowCnt++;
            } while (rs.next());

            sMsg.A.setValidCount(rowCnt);

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(rowCnt);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * findCfsLeaseAttrb
     * @param glblCmpyCd String
     * @param cMsg NWCL0150CMsg
     * @param sMsg NWCL0150SMsg
     */
    public static void findCfsLeaseAttrb(String glblCmpyCd, NWCL0150CMsg cMsg, NWCL0150SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ATTRB_KEY_NM, INV_THRHD);

        S21SsmEZDResult result = NWCL0150Query.getInstance().findSingleRecord("findCfsLeaseAttrb", ssmParam);

        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(cMsg.attrbValNum, (BigDecimal) result.getResultObject());
        } else {
            if (!EVENT_NM_NWCL0150_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                cMsg.setMessageInfo(NWCM0142E, new String[] {CFS_LEASE_ATTRB, INV_THRHD });
            }
        }
    }

    /**
     * Update the global Message.
     * @param bizMsg NWCL0150CMsg
     * @param glblMsg NWCL0150SMsg
     */
    public static void updateGlblMsg(NWCL0150CMsg bizMsg, NWCL0150SMsg glblMsg) {
        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * loadOnePageToCMsg
     * @param bizMsg NWCL0150CMsg
     * @param cMsgArray EZDCMsgArray
     * @param glblMsg NWCL0150SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NWCL0150CMsg bizMsg, EZDCMsgArray cMsgArray, NWCL0150SMsg glblMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }
}
