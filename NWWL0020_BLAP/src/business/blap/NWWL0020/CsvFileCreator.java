/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0020;

import static business.blap.NWWL0020.constant.NWWL0020Constant.CSV_EXT;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NOW_TIME_PATTERN;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NTFY_DESCRIPTION;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NTFY_IF;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NTFY_NAME;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import parts.common.EZDCMimeSourceItem;
import parts.dbcommon.EZDDBEnv;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.db.NTFY_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * CsvFileCreator
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
class CsvFileCreator extends S21SsmBooleanResultSetHandlerSupport {

    /** File Data */
    private final EZDCMimeSourceItem xxFileData;

    /** Notification Header Message */
    private final NTFY_HDRTMsg ntfyHdrTMsg;

    /**
     * Constractor.
     * @param xxFileData EZDCMimeSourceItem
     * @param ntfyHdrTMsg NTFY_HDRTMsg
     */
    CsvFileCreator(EZDCMimeSourceItem xxFileData, NTFY_HDRTMsg ntfyHdrTMsg) {
        this.xxFileData = xxFileData;
        this.ntfyHdrTMsg = ntfyHdrTMsg;
    }

    @Override
    protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

        if (!rs.next()) {
            return false;
        }

        return createCsvFile(rs);
    }

    private boolean createCsvFile(ResultSet rs) throws SQLException {

        final String ntfySqlId = ntfyHdrTMsg.ntfyHdrId.getValue();
        final String ntfySqlNm = ntfyHdrTMsg.ntfyHdrNm.getValue();
        final String ntfySqlDescTxt = ntfyHdrTMsg.ntfyHdrDescTxt.getValue();

        // CSV file path.
        xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(ntfySqlId), CSV_EXT);

        try {

            final FileOutputStream strm = new FileOutputStream(xxFileData.getTempFilePath(), true);
            final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(strm, "UTF-8"));

            // Convert
            EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
            final String repId = converter.convLabel2i18nLabel("", NTFY_IF);
            final String repName = converter.convLabel2i18nLabel("", NTFY_NAME);
            final String repDescription = converter.convLabel2i18nLabel("", NTFY_DESCRIPTION);
            final String nowTime = ZYPDateUtil.formatEzd14ToDisp(ZYPDateUtil.getCurrentSystemTime(NOW_TIME_PATTERN));

            // --------------------------------------------------
            // write CSV Header.
            // --------------------------------------------------
            writeLine(bw, Arrays.asList(repId + " : ", quote(ntfySqlId) + " (" + nowTime + ")"));
            writeLine(bw, Arrays.asList(repName + " : ", quote(ntfySqlNm)));
            writeLine(bw, Arrays.asList(repDescription + " : ", quote(ntfySqlDescTxt)));

            // --------------------------------------------------
            // write CSV Record.
            // --------------------------------------------------
            final int colCnt = rs.getMetaData().getColumnCount();

            // CSV record header.
            final List<String> rcdHdrStrList = new ArrayList<String>();
            for (int i = 1; i <= colCnt; i++) {
                rcdHdrStrList.add(rs.getMetaData().getColumnName(i));
            }
            writeLine(bw, rcdHdrStrList);

            // CSV records.
            do {
                final List<String> rcdStrList = new ArrayList<String>();
                for (int i = 1; i <= colCnt; i++) {
                    final String value;
                    Object obj = rs.getObject(i);
                    if (obj == null) {
                        value = "";
                    } else {
                        value = String.valueOf(obj);
                    }
                    rcdStrList.add(quote(value));
                }
                writeLine(bw, rcdStrList);
            } while (rs.next());

            // close Stream.
            strm.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            throw new S21AbendException(e);
        } catch (UnsupportedEncodingException e) {
            throw new S21AbendException(e);
        } catch (IOException e) {
            throw new S21AbendException(e);
        }

        return true;
    }

    private static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    private static String quote(String str) {
        str = nullToEmpty(str);
        if (str.contains(",")) {
            str = "\"" + str + "\"";
        }
        return str;
    }

    private static void writeLine(BufferedWriter bw, List<String> rcdStrList) throws IOException {

        // write a line.
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> it = rcdStrList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        bw.write(sb.toString());

        // break a line.
        final String nlCd = EZDDBEnv.getString("ezd.file.nl_code");
        if ("LF".equals(nlCd)) {
            bw.write("\n");
        } else if ("CRLF".equals(nlCd)) {
            bw.write("\r\n");
        } else {
            bw.newLine();
        }
    }
}
