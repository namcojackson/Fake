/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_DATA_TP_CD_FILE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_DATA_TP_CD_THEREFORE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_DOC_TP_CD_ORDER_FOR_ORDER;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_DOC_TP_CD_QUOTE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_DOC_TP_CD_OM_CMNT;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_DOC_TP_CD_OM_CMNT_FOR_ORDER;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_ORD_BIZ_APP_NM;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_ORD_BIZ_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ATT_ORD_DATA_KEY_TXT;

import java.io.BufferedReader;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21JDBCUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.log.S21AbendException;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForAttachment;
import business.db.ATT_DATATMsg;
import business.db.ATT_DATATMsgArray;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/20   Hitachi         T.Fukuta        Create          CSA-QC#61467
 * </pre>
 */
public class NWAL1770CommonLogicForAttachment {

    /** SQL query String SQL_SELECT_FOR_UPDATE */
    private static final String SQL_SELECT_ATT_DATA_CMNT_FOR_UPDATE = 
        "SELECT ATT_DATA_CMNT_CLOD "
        + "FROM ATT_DATA "
        + "WHERE GLBL_CMPY_CD = ? "
        + "AND ATT_DATA_PK = ? "
        + "AND EZCANCELFLAG = '0' "
        + "FOR UPDATE";

    /** SQL query String UPDATE */
    private static final String SQL_UPDATE_ATT_DATA_CMNT = 
        "UPDATE ATT_DATA "
        + "SET ATT_DATA_CMNT_CLOD = empty_clob() "
        + "WHERE GLBL_CMPY_CD = ? "
        + "AND ATT_DATA_PK = ?";

    /** SQL query String SQL_SELECT */
    private static final String SQL_SELECT_ATT_DATA_CMNT = 
        "SELECT ATT_DATA_CMNT_CLOD "
        + "FROM ATT_DATA "
        + "WHERE GLBL_CMPY_CD = ? "
        + "AND ATT_DATA_PK = ? "
        + "AND EZCANCELFLAG = '0' ";

    /**
     * Copy Attachment Reference to Order Entry
     * @param bizMsg NWAL1770CMsg
     * @param usrId User ID
     */
    public static void copyAttachmentRefToOrder(NWAL1770CMsg bizMsg, String usrId) {

        if (!ZYPCommonFunc.hasValue(bizMsg.splyQuoteNum)) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum)) {
            return;
        }

        String copiedFileName;
        ATT_DATATMsgArray quoteAttDataList = findQuoteAttachment(bizMsg);
        for (int index = 0; index < quoteAttDataList.getValidCount(); index++) {
            copiedFileName = null;
            try {
                ATT_DATATMsg quoteAttData = quoteAttDataList.no(index);
                String attDataTpCd = quoteAttData.attDataTpCd.getValue();
                if (ATT_DATA_TP_CD_THEREFORE.equals(attDataTpCd)) {
                    continue;
                }
                String comment = loadComment(bizMsg, quoteAttData);
                copiedFileName = copyUploadedPhysicalFile(bizMsg, quoteAttData, usrId);
                BigDecimal orderAttDataPk = ZYPOracleSeqAccessor.getNumberBigDecimal("ATT_DATA_SQ");
                ATT_DATATMsg orderAttData = createOrderAttachment(bizMsg, quoteAttData, orderAttDataPk, copiedFileName);
                insertAttachment(orderAttData);
                saveComment(bizMsg.glblCmpyCd.getValue(), orderAttDataPk, comment);
            } catch (S21AbendException e) {
                deleteCopiedPhysicalFile(copiedFileName);
                throw e;
            } catch (Exception e) {
                deleteCopiedPhysicalFile(copiedFileName);
                throw new S21AbendException(e);
            }
        }
    }

    /**
     * Find Quote Entry Attachment 
     * @param bizMsg NWAL1770CMsg
     * @return Quote Entry Attachment
     */
    private static ATT_DATATMsgArray findQuoteAttachment(NWAL1770CMsg bizMsg) {

        ATT_DATATMsg attData = new ATT_DATATMsg();

        attData.setSQLID("001");
        attData.setMaxCount(bizMsg.A.length());
        attData.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        attData.setConditionValue("ezBusinessID01", bizMsg.getBusinessID());
        attData.setConditionValue("attDataGrpTxt01", bizMsg.splyQuoteNum.getValue());

        return (ATT_DATATMsgArray) EZDTBLAccessor.findByCondition(attData);
    }

    // Reffered to ZYP0310BL02.copyAttDataCmntToCmsg
    /**
     * Load Comment
     * @param bizMsg NWAL1770CMsg
     * @param attData ATT_DATATMsg
     * @return comment
     */
    @SuppressWarnings("unchecked")
    private static String loadComment(NWAL1770CMsg bizMsg, ATT_DATATMsg attData) {

        NWAL1770QueryForAttachment query = NWAL1770QueryForAttachment.getInstance();

        S21SsmEZDResult ssmResult = query.getComment(bizMsg, attData.attDataPk.getValue());
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        StringBuilder commentBuf = new StringBuilder();
        List<Map<String, Object>> attachmentList = (List<Map<String, Object>>) ssmResult.getResultObject();
        Map<String, Object> attachment = attachmentList.get(0);
        if (attachment.get("ATT_DATA_CMNT_CLOD") != null && attachment.get("CHUNK_SIZE") != null) {
            int chunkSize = ((BigDecimal)attachment.get("CHUNK_SIZE")).intValue();
            Clob comment = (Clob)attachment.get("ATT_DATA_CMNT_CLOD");
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(comment.getCharacterStream());

                char[] buf = new char[chunkSize];
                int len = 0;
                while ((len = reader.read(buf)) > 0) {
                    commentBuf.append(new String(buf, 0, len));
                }

            } catch (Exception e) {
                throw new S21AbendException(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return commentBuf.toString();
    }

    /**
     * Copy Upload Physical File
     * @param bizMsg NWAL1770CMsg
     * @param attData ATT_DATATMsg
     * @param usrId User ID
     * @return Copied Physical File Name
     */
    private static String copyUploadedPhysicalFile(NWAL1770CMsg bizMsg, ATT_DATATMsg attData, String usrId) {

        String attDataTpCd = attData.attDataTpCd.getValue();
        String sourceFile = attData.othSysUrl.getValue();
        if (!ATT_DATA_TP_CD_FILE.equals(attDataTpCd) ||
            S21StringUtil.isEmpty(sourceFile)) {
            return null;
        }

        String attDataNm = attData.attDataNm.getValue();
        String baseFileNm;
        String ext;
        if (!S21StringUtil.isEmpty(attDataNm)) {
            int extIndex = attDataNm.lastIndexOf(".");
            if (extIndex >= 0) {
                baseFileNm = attDataNm.substring(0, extIndex) + "_";
                ext = attDataNm.substring(extIndex);
            } else {
                baseFileNm = attDataNm + "_";
                ext = "";
            }
        } else {
            baseFileNm = "";
            ext = "";
        }
        String targetFile = baseFileNm + S21SystemDate.getCurrentSystemTime("yyyyMMddHHmmssSSS") + "_@" + usrId + ext;
        String copiedFileName = ZYPFileWriter.uploadAttachmentFileToFS(sourceFile, targetFile, ATT_ORD_BIZ_ID);

        return copiedFileName;
    }

    /**
     * Create Order Entry Attachment
     * @param bizMsg NWAL1770CMsg
     * @param quoteAttData ATT_DATATMsg of Quote Entry
     * @param orderAttDataPk ATT_DATA Primary Key for Order Entry
     * @param copiedFileName Copied Physical File Name
     * @return Created ATT_DATATMsg of Order Entry
     */
    private static ATT_DATATMsg createOrderAttachment(NWAL1770CMsg bizMsg, ATT_DATATMsg quoteAttData, BigDecimal orderAttDataPk, String copiedFileName) {

        ATT_DATATMsg orderAttData = new ATT_DATATMsg();

        orderAttData.ezInTime.setValue(quoteAttData.ezInTime.getValue());
        orderAttData.ezInTimeZone.setValue(quoteAttData.ezInTimeZone.getValue());
        orderAttData.glblCmpyCd.setValue(quoteAttData.glblCmpyCd.getValue());
        orderAttData.attDataPk.setValue(orderAttDataPk);
        orderAttData.ezBusinessID.setValue(ATT_ORD_BIZ_ID);
        orderAttData.attDataGrpTxt.setValue(bizMsg.cpoOrdNum.getValue());
        orderAttData.attDataNm.setValue(quoteAttData.attDataNm.getValue());
        orderAttData.attDataVol.setValue(quoteAttData.attDataVol.getValue());
        orderAttData.attDataDescTxt.setValue(quoteAttData.attDataDescTxt.getValue());
        orderAttData.bizAppNm.setValue(ATT_ORD_BIZ_APP_NM);
        orderAttData.attDataKeyTxt.setValue(ATT_ORD_DATA_KEY_TXT);
        orderAttData.attDataTpCd.setValue(quoteAttData.attDataTpCd.getValue());
        String attDocTypeCd = quoteAttData.attDocTpCd.getValue();
        if (ATT_DOC_TP_CD_QUOTE.equals(attDocTypeCd)) {
            orderAttData.attDocTpCd.setValue(ATT_DOC_TP_CD_ORDER_FOR_ORDER);
        } else if (ATT_DOC_TP_CD_OM_CMNT.equals(attDocTypeCd)) {
            orderAttData.attDocTpCd.setValue(ATT_DOC_TP_CD_OM_CMNT_FOR_ORDER);
        } else {
            orderAttData.attDocTpCd.setValue(attDocTypeCd);
        }
        if (!S21StringUtil.isEmpty(copiedFileName)) {
            orderAttData.othSysUrl.setValue(copiedFileName);
        } else {
            orderAttData.othSysUrl.setValue(quoteAttData.othSysUrl.getValue());
        }

        return orderAttData;
    }

    /**
     * Insert Attachment
     * @param tMsg ATT_DATATMsg for insert
     */
    private static void insertAttachment(ATT_DATATMsg tMsg) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = EZDConnectionMgr.getInstance().getConnection();

            st = conn.prepareStatement("insert into ATT_DATA" +
                    "(" +
                    "EZTABLEID,EZCANCELFLAG,EZINTIME,EZINTIMEZONE,EZINCOMPANYCODE,EZINUSERID,EZINAPLID," +
                    "EZUPTIME,EZUPTIMEZONE,EZUPCOMPANYCODE,EZUPUSERID,EZUPAPLID," +
                    "GLBL_CMPY_CD,ATT_DATA_PK,EZBUSINESSID,ATT_DATA_GRP_TXT," +
                    "ATT_DATA_NM,ATT_DATA_VOL,ATT_DATA_DESC_TXT,BIZ_APP_NM," +
                    "ATT_DATA_KEY_TXT,ATT_DATA_TP_CD,ATT_DOC_TP_CD,OTH_SYS_URL" +
                    ") values(" +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?" +
                    ")");

            String companyCode = EZDDBCICarrier.getUpCmpyCd();
            String userId = EZDDBCICarrier.getUserID();
            String pgId = EZDDBCICarrier.getUppgID();
            String nowTime = EZDDBCICarrier.getStartDateTime();
            String timeZone = EZDDBCICarrier.getUpTimeZone();

            tMsg.setDBTableID();
            tMsg.setDBCancelFlag("0");
            tMsg.setDBInCompanyCode(companyCode);
            tMsg.setDBInUserID(userId);
            tMsg.setDBInAplID(pgId);
            tMsg.setDBUpTime(nowTime);
            tMsg.setDBUpUserID(userId);
            tMsg.setDBUpAplID(pgId);
            tMsg.setDBUpTimeZone(timeZone);
            tMsg.setDBUpCompanyCode(companyCode);

            st.setString(1, tMsg.ezTableID.getValue());         // EZTABLEID         VARCHAR2(112)
            st.setString(2, tMsg.ezCancelFlag.getValue());      // EZCANCELFLAG      VARCHAR2(4) NOT NULL
            st.setString(3, tMsg.ezInTime.getValue());          // EZINTIME          VARCHAR2(68)
            st.setString(4, tMsg.ezInTimeZone.getValue());      // EZINTIMEZONE      VARCHAR2(20)
            st.setString(5, tMsg.ezInCompanyCode.getValue());   // EZINCOMPANYCODE   VARCHAR2(16)
            st.setString(6, tMsg.ezInUserID.getValue());        // EZINUSERID        VARCHAR2(64)
            st.setString(7, tMsg.ezInAplID.getValue());         // EZINAPLID         VARCHAR2(256)
            st.setString(8, tMsg.ezUpTime.getValue());          // EZUPTIME          VARCHAR2(68)
            st.setString(9, tMsg.ezUpTimeZone.getValue());      // EZUPTIMEZONE      VARCHAR2(20)
            st.setString(10, tMsg.ezUpCompanyCode.getValue());  // EZUPCOMPANYCODE   VARCHAR2(16)
            st.setString(11, tMsg.ezUpUserID.getValue());       // EZUPUSERID        VARCHAR2(64)
            st.setString(12, tMsg.ezUpAplID.getValue());        // EZUPAPLID         NVARCHAR2(64)
            st.setString(13, tMsg.glblCmpyCd.getValue());       // GLBL_CMPY_CD      VARCHAR2(16) NOT NULL
            st.setBigDecimal(14, tMsg.attDataPk.getValue());    // ATT_DATA_PK       NUMBER(28) NOT NULL
            st.setString(15, tMsg.ezBusinessID.getValue());     // EZBUSINESSID      VARCHAR2(32)
            st.setString(16, tMsg.attDataGrpTxt.getValue());    // ATT_DATA_GRP_TXT  VARCHAR2(2000)
            st.setString(17, tMsg.attDataNm.getValue());        // ATT_DATA_NM       VARCHAR2(1024)
            // ATT_DATA_BLOD                             BLOB
            st.setBigDecimal(18, tMsg.attDataVol.getValue());   // ATT_DATA_VOL      NUMBER(20)
            st.setString(19, tMsg.attDataDescTxt.getValue());   // ATT_DATA_DESC_TXT VARCHAR2(2000)
            st.setString(20, tMsg.bizAppNm.getValue());         // BIZ_APP_NM        VARCHAR2(400)
            st.setString(21, tMsg.attDataKeyTxt.getValue());    // ATT_DATA_KEY_TXT  VARCHAR2(400)
            st.setString(22, tMsg.attDataTpCd.getValue());      // ATT_DATA_TP_CD    VARCHAR2(20)
            st.setString(23, tMsg.attDocTpCd.getValue());       // ATT_DOC_TP_CD     VARCHAR2(20)
            st.setString(24, tMsg.othSysUrl.getValue());        // OTH_SYS_URL       VARCHAR2(4000)
            // ATT_DATA_CMNT_CLOD                        CLOB

            st.executeUpdate();
        } catch (SQLException e) {
            if (S21JDBCUtil.isDuplicateKey(e)) {
                tMsg.setReturnCode("2300");
            } else {
                throw new EZDDBRetryRequestException(e);
            }
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Reffered to ZYPFileUpDown.updateAttDataCmnt
    /**
     * Save Comment
     * @param glblCmpyCd Global Company Code
     * @param attDataPk Primary Key for Comment
     * @param cmnt Comment
     */
    private static void saveComment(String glblCmpyCd, BigDecimal attDataPk, String cmnt) {

        Connection con = null;
        PreparedStatement pstmt01 = null;
        PreparedStatement pstmt02 = null;
        PreparedStatement pstmt03 = null;
        ResultSet rs = null;

        try {
            // insert new record
            con = EZDConnectionMgr.getInstance().getConnection();

            // Select the text you wish to register a record. using a lock for update
            pstmt01 = con.prepareStatement(SQL_SELECT_ATT_DATA_CMNT_FOR_UPDATE);
            pstmt01.setString(1, glblCmpyCd);
            pstmt01.setBigDecimal(2, attDataPk);

            // return flag after execute
            boolean returnFlg = pstmt01.execute();

            if (returnFlg) {
                // Initialize Clob
                pstmt02 = con.prepareStatement(SQL_UPDATE_ATT_DATA_CMNT);
                pstmt02.setString(1, glblCmpyCd);
                pstmt02.setBigDecimal(2, attDataPk);
                pstmt02.executeUpdate();

                // Reload
                pstmt03 = con.prepareStatement(SQL_SELECT_ATT_DATA_CMNT);
                pstmt03.setString(1, glblCmpyCd);
                pstmt03.setBigDecimal(2, attDataPk);
                pstmt03.executeUpdate();

                // Initialize Clob
                if (!S21StringUtil.isEmpty(cmnt)) {
                    rs = pstmt03.getResultSet();
                    if (rs.next()) {
                        // Attached Data Comment Text assigned to Clob
                        Clob lob = rs.getClob("ATT_DATA_CMNT_CLOD");
                        lob.setString(1, cmnt);
                    }
                }
            }
            // Successful completion

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pstmt01 != null) {
                try {
                    pstmt01.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pstmt02 != null) {
                try {
                    pstmt02.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pstmt03 != null) {
                try {
                    pstmt03.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Delete Copied Physical File
     * @param copiedFileName Copied Physical File Name
     */
    private static void deleteCopiedPhysicalFile(String copiedFileName) {

        if (S21StringUtil.isEmpty(copiedFileName)) {
            return;
        }

        try {
            new File(copiedFileName).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
