/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC119001;

import static com.canon.cusa.s21.api.NSZ.NSZC119001.constant.NSZC119001Constant.CALL_PRC_CANON_E193_INSERT_DATA_MGMT_STG;
import static com.canon.cusa.s21.api.NSZ.NSZC119001.constant.NSZC119001Constant.CANON_E193_DATAMGMT_PKG_STS_ERROR;
import static com.canon.cusa.s21.api.NSZ.NSZC119001.constant.NSZC119001Constant.INTFC_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NSZC119001PMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * CSA Website Ticket Creation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/09   CITS            M.Naito         Create          QC#23377
 * 2018/06/06   Hitachi         T.Iwamoto       Update          QC#26460
 *</pre>
 */
public class NSZC119001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC119001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC119001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC119001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC119001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute CSA Website Ticket Creation API.
     * @param param NSZC119001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC119001PMsg param, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        createTicket(msgMap);

    }

    private void createTicket(S21ApiMessageMap msgMap) {
        NSZC119001PMsg pMsg = (NSZC119001PMsg) msgMap.getPmsg();

        // Upload attachment file to S21 file system.
        String filePath = uploadAttachmentFileToFS(pMsg.mstrAttDataDescTxt.getValue(), pMsg.xxAttNmTxt.getValue(), pMsg.xxAttTpTxt.getValue());

        CallableStatement callStmt = null;
        OracleConnection oraConnection = null;

        try {
            oraConnection = (OracleConnection) EZDConnectionMgr.getInstance().getConnection().getMetaData().getConnection();
            callStmt = oraConnection.prepareCall(CALL_PRC_CANON_E193_INSERT_DATA_MGMT_STG);

            // Register IN parameter
            callStmt.setString(1, pMsg.actDescTxt_H1.getValue());
            callStmt.setString(2, pMsg.actDescTxt_H2.getValue());
            callStmt.setString(3, pMsg.xxCratByTxt.getValue());
            callStmt.setString(4, pMsg.xxLastUpdByTxt.getValue());
            callStmt.setString(5, pMsg.xxEmpNmTxt.getValue());
            callStmt.setString(6, pMsg.xxEmpTelNum.getValue());
            callStmt.setString(7, pMsg.xxSubCmpyNmTxt.getValue());
            callStmt.setString(8, pMsg.xxDeptNmTxt.getValue());
            callStmt.setString(9, pMsg.xxCustNum.getValue());
            callStmt.setString(10, pMsg.xxCustNmTxt.getValue());
            callStmt.setString(11, pMsg.xxCustCtacNmTxt.getValue());
            callStmt.setString(12, pMsg.xxCustCtacTelNum.getValue());
            callStmt.setString(13, pMsg.dtlNoteTxt.getValue());
            callStmt.setString(14, pMsg.descValTxt_H1.getValue());
            callStmt.setString(15, pMsg.descValTxt_H2.getValue());
            callStmt.setString(16, pMsg.descValTxt_H3.getValue());
            callStmt.setString(17, pMsg.descValTxt_H4.getValue());
            callStmt.setString(18, pMsg.descValTxt_H5.getValue());
            callStmt.setString(19, pMsg.descValTxt_H6.getValue());
            callStmt.setString(20, pMsg.descValTxt_H7.getValue());
            callStmt.setString(21, pMsg.descValTxt_H8.getValue());
            callStmt.setString(22, pMsg.descValTxt_H9.getValue());
            callStmt.setString(23, pMsg.descValTxt_HA.getValue());
            callStmt.setString(24, pMsg.xxAttTpTxt.getValue());
            callStmt.setString(25, pMsg.xxAttNmTxt.getValue());
            callStmt.setString(26, filePath);

            // Register OUT parameter
            callStmt.registerOutParameter(27, OracleTypes.NUMBER);
            callStmt.registerOutParameter(28, OracleTypes.VARCHAR);
            callStmt.registerOutParameter(29, OracleTypes.VARCHAR);

            // Execute the CallableStatement
            callStmt.execute();

            // Retrieve the value from the OUT parameter
            if (CANON_E193_DATAMGMT_PKG_STS_ERROR.equals(callStmt.getString(28))) {
                // Output error log
                debugPrint(callStmt.getString(29), pMsg);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Mandatory, Close CallableStatement object
            if (callStmt != null) {
                try {
                    callStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String uploadAttachmentFileToFS(String encodeFile, String fileName, String fileType) {

        String uploadedFilePath = null;
        String interfaceID = INTFC_ID;

        if (hasValue(encodeFile) && hasValue(fileName)) {

            // Decode the encodedStringOfAttachFile
            byte[] byteArrayOfFileAfterDecodedBase64 = decodeBase64String(encodeFile);

            // Mod Start 2018/06/06 QC#26460
//            if (!hasValue(fileType)) {
//                fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
//            }
            String uniqueFileNm = ZYPFileNameUtil.createUniqueFileNm("", fileName);
//            uniqueFileNm += "." + fileType.toLowerCase();
            // Mod End 2018/06/06 QC#26460
            uploadedFilePath = ZYPFileWriter.uploadAttachmentFileToFS(byteArrayOfFileAfterDecodedBase64, uniqueFileNm, interfaceID);
        }

        return uploadedFilePath;
    }

    private static byte[] decodeBase64String(String encodedString) {
        return DatatypeConverter.parseBase64Binary(encodedString);
    }

    private static void debugPrint(String msg, NSZC119001PMsg pMsg) throws SQLException {
        System.out.println("******** " + msg);
        System.out.println("action: " + pMsg.actDescTxt_H1.getValue());
        System.out.println("sub-Action: " + pMsg.actDescTxt_H2.getValue());
        System.out.println("Customer Number: " + pMsg.xxCustNum.getValue());
        System.out.println("Customer Name: " + pMsg.xxCustNmTxt.getValue());
    }

}
