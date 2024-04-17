/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2010   Fujitsu         K.Tajima        Create          N/A
 * 11/20/2013   Fujitsu         T.Yoshida       Update          DefID:2852
 *</pre>
 */
package business.blap.NWXL0010;

import static java.util.Arrays.asList;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import parts.common.EZDCMimeSourceItem;
import parts.dbcommon.EZDDBEnv;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWXL0010.constant.NWXL0010Constant;
import business.db.RPT_SQLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * CSV File Creator.
 * 
 * @author K.Tajima
 */
class CsvFileCreator extends S21SsmBooleanResultSetHandlerSupport implements NWXL0010Constant {

    private final EZDCMimeSourceItem xxFileData;
    private final RPT_SQLTMsg rptSqlTMsg;

    /**
     * Constractor.
     * 
     * @param xxFileData    EZDCMimeSourceItem
     * @param rptSqlTMsg    RPT_SQLTMsg
     */
    CsvFileCreator(EZDCMimeSourceItem xxFileData, RPT_SQLTMsg rptSqlTMsg) {
        this.xxFileData = xxFileData;
        this.rptSqlTMsg = rptSqlTMsg;
    }

    @Override
    protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

        if (!rs.next()) {
            return false;
        }

        return createCsvFile(rs);
    }

    private boolean createCsvFile(ResultSet rs) throws SQLException {

        final String rptSqlId      = rptSqlTMsg.rptSqlId.getValue();
        final String rptSqlNm      = rptSqlTMsg.rptSqlNm.getValue();
        final String rptSqlDescTxt = rptSqlTMsg.rptSqlDescTxt.getValue();

        // CSV file path.
        xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(rptSqlId), ".csv");

        try {

            final FileOutputStream strm = new FileOutputStream(xxFileData.getTempFilePath(), true);
            final BufferedWriter   bw   = new BufferedWriter(new OutputStreamWriter(strm, "UTF-8"));

            // START 2013/11/20 T.Yoshida [Defect#2852 Mod]
            // Convert
            EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
            final String repId = converter.convLabel2i18nLabel("", REPORT_ID);
            final String repName = converter.convLabel2i18nLabel("", REPORT_NAME);
            final String repDescription = converter.convLabel2i18nLabel("", REPORT_DESCRIPTION);
            final String nowTime = ZYPDateUtil.formatEzd14ToDisp(ZYPDateUtil.getCurrentSystemTime(NOW_TIME_PATTERN));

            // --------------------------------------------------
            // write CSV Header.
            // --------------------------------------------------
            writeLine(bw, asList(repId + " : ", quote(rptSqlId) + " (" + nowTime + ")"));
            writeLine(bw, asList(repName + " : ", quote(rptSqlNm)));
            writeLine(bw, asList(repDescription + " : ", quote(rptSqlDescTxt)));
            // END 2013/11/20 T.Yoshida [Defect#2852 Mod]
            
            // --------------------------------------------------
            // write CSV Record.
            // --------------------------------------------------
            final int colCnt = rs.getMetaData().getColumnCount();
            
            // CSV record header.
            final List<String> rcdHdrStrList = new ArrayList<String>();
            for (int i = 1; i <= colCnt; i++) {
                rcdHdrStrList.add(rs.getMetaData().getColumnName(i));;
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
