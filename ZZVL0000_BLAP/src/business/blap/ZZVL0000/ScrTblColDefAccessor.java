package business.blap.ZZVL0000;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// START 01/27/17 C.Ogaki [Add] Release 2000 byte length limit
import java.util.ArrayList;
// END   01/27/17 C.Ogaki [Add] Release 2000 byte length limit
import java.util.Iterator;
import java.util.List;

import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDSQLAccessor;

import business.blap.ZZVL0000.constant.ZZVL0000Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;

/** 
 *<pre>
 * ScrTblColDefAccessor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 * 2017/01/27   Fujitsu         C.Ogaki         Update          ---
 *</pre>
 */
public class ScrTblColDefAccessor extends EZDSQLAccessor {

    /** SCR_TBL_COL_DEF_CLOD */
    private static final String SCR_TBL_COL_DEF_CLOD = "SCR_TBL_COL_DEF_CLOD";

    /** SCR_TBL_COL_DEF_NM */
    private static final String SCR_TBL_COL_DEF_NM_COL = "SCR_TBL_COL_DEF_NM";

    /** SCR_TBL_COL_DEF */
    private static final String TBL_NM = "SCR_TBL_COL_DEF";

    // START 01/27/17 C.Ogaki [Add] Release 2000 byte length limit
    /** SCR_TBL_NM */
    private static final String SCR_TBL_NM = "SCR_TBL_NM";

    /** SCR_APP_ID */
    private static final String SCR_APP_ID = "SCR_APP_ID";

    /** SCR_APP_ID */
    private static final String BIZ_APP_NM = "BIZ_APP_NM";
    // END   01/27/17 C.Ogaki [Add] Release 2000 byte length limit

    /** Index for view setting name in CSV data */
    private static final int INDEX_USR_DEF_NM = 3;

    /** REG_COMMA "," */
    private static final String REG_COMMA = ",";

    /** Index for user default setting column in CSV data */
    private static final int INDEX_USR_DEF_FLG = 6;

    /** extColumns */
    private static final int INDEX_EXT_COLUMNS = 10;

    // START 01/27/17 C.Ogaki [Add] Release 2000 byte length limit
    /** orgUsr */
    private static final int INDEX_ORG_USR_ID = 11;
    // END   01/27/17 C.Ogaki [Add] Release 2000 byte length limit

    /** FLAG_DEFAULT_ON "true" */
    private static final String FLAG_DEFAULT_ON = "true";

    /** FLAG_DEFAULT_OFF "false" */
    private static final String FLAG_DEFAULT_OFF = "false";

    /**
     * SCR_TBL_COL_DEF Create.
     * @param   glblCmpyCd String
     * @param   roleNm String
     * @param   scrAppId String
     * @param   scrTblNm String
     * @param   scrTblColDefNm String
     * @param   newTblColDefNm String
     * @param   orgUserNm String
     * @return int
     */
    protected int create(String glblCmpyCd, String roleNm, String scrAppId, String scrTblNm, String scrTblColDefNm, String newTblColDefNm, String orgUserNm) {
        Connection con = null;
        PreparedStatement pstmt01 = null;
        PreparedStatement pstmt02 = null;
        PreparedStatement pstmt03 = null;
        ResultSet rs = null;
        int returnCode = -1;

        String orgData = getScrTblColDef(glblCmpyCd, orgUserNm, scrAppId, scrTblNm, scrTblColDefNm, newTblColDefNm);

        if (orgData.isEmpty()) {
            returnCode = ZZVL0000Constant.SETTING_NOT_FOUND;
            return returnCode;
        }

        // To insert new records.
        try {
            con = getConnection();
            //con.setAutoCommit(false);

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT SCR_TBL_COL_DEF_CLOD ");
            sb.append("FROM SCR_TBL_COL_DEF ");
            sb.append("WHERE GLBL_CMPY_CD = ? ");
            sb.append("AND USR_ID = ? ");
            sb.append("AND SCR_APP_ID = ? ");
            sb.append("AND SCR_TBL_NM = ? ");
            sb.append("AND SCR_TBL_COL_DEF_NM = ? ");
            sb.append("FOR UPDATE ");
            String sql = sb.toString();

            // Select the text you wish to register a record. using a lock for update
            pstmt01 = con.prepareStatement(sql);
            pstmt01.setString(1, glblCmpyCd);
            pstmt01.setString(2, roleNm);
            pstmt01.setString(3, scrAppId);
            pstmt01.setString(4, scrTblNm);
            pstmt01.setString(5, newTblColDefNm);

            boolean returnFlag = pstmt01.execute();

            if (returnFlag) {
                rs = pstmt01.getResultSet();
                if (!rs.next()) {
                    sb = new StringBuilder();
                    sb.append("insert into SCR_TBL_COL_DEF values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    sql = sb.toString();
                    pstmt02 = con.prepareStatement(sql);
                    pstmt02.setString(1, TBL_NM);
                    pstmt02.setString(2, "0");
                    pstmt02.setString(3, EZDDBCICarrier.getStartDateTime());
                    pstmt02.setString(4, EZDDBCICarrier.getUpTimeZone());
                    pstmt02.setString(5, EZDDBCICarrier.getUpCmpyCd());
                    pstmt02.setString(6, EZDDBCICarrier.getUserID());
                    pstmt02.setString(7, scrAppId);
                    pstmt02.setString(8, EZDDBCICarrier.getStartDateTime());
                    pstmt02.setString(9, EZDDBCICarrier.getUpTimeZone());
                    pstmt02.setString(10, EZDDBCICarrier.getUpCmpyCd());
                    pstmt02.setString(11, EZDDBCICarrier.getUserID());
                    pstmt02.setString(12, scrAppId);
                    pstmt02.setString(13, glblCmpyCd);
                    pstmt02.setString(14, roleNm);
                    pstmt02.setString(15, scrAppId);
                    pstmt02.setString(16, scrTblNm);
                    pstmt02.setString(17, newTblColDefNm);
                    pstmt02.setString(18, orgData);
                    returnCode = pstmt02.executeUpdate();
                } else {
                    sb = new StringBuilder();
                    sb.append("UPDATE SCR_TBL_COL_DEF ");
                    sb.append("SET SCR_TBL_COL_DEF_CLOD = empty_clob() ");
                    sb.append(", EZCANCELFLAG = '0' ");
                    sb.append(", EZUPTIME = ? ");
                    sb.append("WHERE GLBL_CMPY_CD = ? ");
                    sb.append("AND USR_ID = ? ");
                    sb.append("AND SCR_APP_ID = ? ");
                    sb.append("AND SCR_TBL_NM = ? ");
                    sb.append("AND SCR_TBL_COL_DEF_NM = ? ");
                    sql = sb.toString();

                    // Initialize Clob
                    pstmt02 = con.prepareStatement(sql);
                    pstmt02.setString(1, EZDDBCICarrier.getStartDateTime());
                    pstmt02.setString(2, glblCmpyCd);
                    pstmt02.setString(3, roleNm);
                    pstmt02.setString(4, scrAppId);
                    pstmt02.setString(5, scrTblNm);
                    pstmt02.setString(6, newTblColDefNm);
                    returnCode = pstmt02.executeUpdate();

                    // Reload
                    sb = new StringBuilder();
                    sb.append("SELECT SCR_TBL_COL_DEF_CLOD ");
                    sb.append("FROM SCR_TBL_COL_DEF ");
                    sb.append("WHERE GLBL_CMPY_CD = ? ");
                    sb.append("AND USR_ID = ? ");
                    sb.append("AND SCR_APP_ID = ? ");
                    sb.append("AND SCR_TBL_NM = ? ");
                    sb.append("AND SCR_TBL_COL_DEF_NM = ? ");
                    sb.append("AND EZCANCELFLAG = '0' ");
                    sql = sb.toString();

                    pstmt03 = con.prepareStatement(sql);
                    pstmt03.setString(1, glblCmpyCd);
                    pstmt03.setString(2, roleNm);
                    pstmt03.setString(3, scrAppId);
                    pstmt03.setString(4, scrTblNm);
                    pstmt03.setString(5, newTblColDefNm);
                    returnCode = pstmt03.executeUpdate();

                    // Initialize Blob
                    if (!"".equals(orgData)) {
                        rs = pstmt03.getResultSet();
                        if (rs.next()) {
                            // Def Data Text assigned to Clob
                            Clob lob = rs.getClob(SCR_TBL_COL_DEF_CLOD);
                            lob.setString(1, orgData);
                        }
                    }
                }
            }
            // Successful completion
            returnCode = 0;
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt01 != null) {
                    pstmt01.close();
                }
                if (pstmt02 != null) {
                    pstmt02.close();
                }
                if (pstmt03 != null) {
                    pstmt03.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new S21AbendException(e);
            }
        }
        return returnCode;
    }

    private String getScrTblColDef(String glblCmpyCd, String userId, String scrAppId, String scrTblNm, String scrTblColDefNm, String newTblColDefNm) {
        Connection con = null;
        PreparedStatement pstmt01 = null;
        ResultSet rs = null;
        String orgData = "";

        // To insert new records.
        try {
            con = getConnection();
            con.setAutoCommit(false);

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT SCR_TBL_COL_DEF_CLOD ");
            sb.append("FROM SCR_TBL_COL_DEF ");
            sb.append("WHERE GLBL_CMPY_CD = ? ");
            sb.append("AND USR_ID = ? ");
            sb.append("AND SCR_APP_ID = ? ");
            sb.append("AND SCR_TBL_NM = ? ");
            sb.append("AND SCR_TBL_COL_DEF_NM = ? ");
            String sql = sb.toString();

            // Select the text you wish to register a record. using a lock for update
            pstmt01 = con.prepareStatement(sql);
            pstmt01.setString(1, glblCmpyCd);
            pstmt01.setString(2, userId);
            pstmt01.setString(3, scrAppId);
            pstmt01.setString(4, scrTblNm);
            pstmt01.setString(5, scrTblColDefNm);

            rs = pstmt01.executeQuery();

            if (rs.next()) {
                Clob lob = rs.getClob(SCR_TBL_COL_DEF_CLOD);
                int size = (int) lob.length();
                orgData = lob.getSubString(1, size);
            } else {
                return orgData;
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt01 != null) {
                    pstmt01.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new S21AbendException(e);
            }
        }

        // Set user default flag false
        StringBuffer initialData = new StringBuffer();
        String[] orgDataArray = orgData.split(",");
        for (int i = 0; i < orgDataArray.length; i++) {
            if (i == INDEX_USR_DEF_FLG) {
                initialData.append(FLAG_DEFAULT_OFF);
            } else if (i == INDEX_USR_DEF_NM) {
                initialData.append(newTblColDefNm);
            } else {
                initialData.append(orgDataArray[i]);
            }
            if (i != INDEX_EXT_COLUMNS) {
                initialData.append(",");
            }
        }
        initialData.append(",");
        initialData.append(userId);
        return initialData.toString();
    }

    /**
     * updateDefault
     * @param   glblCmpyCd String
     * @param   roleNm String
     * @param   bizAppId String
     * @param   tblNm String
     * @param   scrDefNm String
     * @param   orgUsrNm String
     */
    protected void updateDefault(String glblCmpyCd, String roleNm, String bizAppId, String tblNm, String scrDefNm, String orgUsrNm) {
        Connection con = null;
        PreparedStatement pstmt01 = null;
        PreparedStatement pstmt02 = null;
        PreparedStatement pstmt03 = null;
        ResultSet rs = null;
        ResultSet updateRs = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT SCR_TBL_COL_DEF_NM,SCR_TBL_COL_DEF_CLOD ");
            sb.append("FROM SCR_TBL_COL_DEF ");
            sb.append("WHERE GLBL_CMPY_CD = ? ");
            sb.append("AND USR_ID = ? ");
            sb.append("AND SCR_APP_ID = ? ");
            sb.append("AND SCR_TBL_NM = ? ");
            sb.append("AND EZCANCELFLAG = '0' ");
            sb.append("FOR UPDATE ");
            String sql = sb.toString();

            // Select the text you wish to register a record. using a lock for update
            pstmt01 = con.prepareStatement(sql);
            pstmt01.setString(1, glblCmpyCd);
            pstmt01.setString(2, roleNm);
            pstmt01.setString(3, bizAppId);
            pstmt01.setString(4, tblNm);

            pstmt01.execute();
            rs = pstmt01.getResultSet();

            while (rs.next()) {
                Clob lob = rs.getClob(SCR_TBL_COL_DEF_CLOD);
                int size = (int) lob.length();

                String orgData = lob.getSubString(1, size);
                String[] viewData = orgData.split(REG_COMMA);

                if (rs.getString(SCR_TBL_COL_DEF_NM_COL).equals(scrDefNm)) {
                    viewData[INDEX_USR_DEF_FLG] = FLAG_DEFAULT_ON;
                } else {
                    viewData[INDEX_USR_DEF_FLG] = FLAG_DEFAULT_OFF;
                }

                StringBuilder newColBuf = new StringBuilder();
                for (int i = 0; i < viewData.length; i++) {
                    newColBuf.append(viewData[i]);
                    if (i < viewData.length - 1) {
                        newColBuf.append(REG_COMMA);
                    }
                }
                String newColData = newColBuf.toString();

                StringBuilder updateSb = new StringBuilder();

                updateSb.append("UPDATE SCR_TBL_COL_DEF ");
                updateSb.append("SET SCR_TBL_COL_DEF_CLOD = empty_clob() ");
                updateSb.append(", EZUPTIME = ? ");
                updateSb.append("WHERE GLBL_CMPY_CD = ? ");
                updateSb.append("AND USR_ID = ? ");
                updateSb.append("AND SCR_APP_ID = ? ");
                updateSb.append("AND SCR_TBL_NM = ? ");
                updateSb.append("AND SCR_TBL_COL_DEF_NM = ? ");
                String updateSql = updateSb.toString();

                // Initialize Clob
                pstmt02 = con.prepareStatement(updateSql);
                pstmt02.setString(1, EZDDBCICarrier.getStartDateTime());
                pstmt02.setString(2, glblCmpyCd);
                pstmt02.setString(3, roleNm);
                pstmt02.setString(4, bizAppId);
                pstmt02.setString(5, tblNm);
                pstmt02.setString(6, rs.getString(SCR_TBL_COL_DEF_NM_COL));
                pstmt02.executeUpdate();

                // Reload
                StringBuilder reloadSb = new StringBuilder();
                reloadSb.append("SELECT SCR_TBL_COL_DEF_CLOD ");
                reloadSb.append("FROM SCR_TBL_COL_DEF ");
                reloadSb.append("WHERE GLBL_CMPY_CD = ? ");
                reloadSb.append("AND USR_ID = ? ");
                reloadSb.append("AND SCR_APP_ID = ? ");
                reloadSb.append("AND SCR_TBL_NM = ? ");
                reloadSb.append("AND SCR_TBL_COL_DEF_NM = ? ");
                reloadSb.append("AND EZCANCELFLAG = '0' ");
                String reloadSql = reloadSb.toString();

                pstmt03 = con.prepareStatement(reloadSql);
                pstmt03.setString(1, glblCmpyCd);
                pstmt03.setString(2, roleNm);
                pstmt03.setString(3, bizAppId);
                pstmt03.setString(4, tblNm);
                pstmt03.setString(5, rs.getString(SCR_TBL_COL_DEF_NM_COL));
                pstmt03.executeUpdate();

                updateRs = pstmt03.getResultSet();
                if (updateRs.next()) {
                    // Def Data Text assigned to Clob
                    Clob updateLob = updateRs.getClob(SCR_TBL_COL_DEF_CLOD);
                    updateLob.setString(1, newColData);
                }
            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new S21AbendException(e1);
            }
            throw new S21AbendException(e);
        } finally {
            try {
                con.commit();
                if (pstmt01 != null) {
                    pstmt01.close();
                }
                if (pstmt02 != null) {
                    pstmt02.close();
                }
                if (pstmt03 != null) {
                    pstmt03.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new S21AbendException(e);
            }
        }
    }

    /**
     * SCR_TBL_COL_DEF Logical Remove.
     * @param deleteList List<String[]>
     * @return int
     */
    protected int logicalRemove(List<String[]> deleteList) {

        Connection con = null;
        PreparedStatement pstmt01 = null;
        PreparedStatement pstmt02 = null;
        int returnCode = -1;
        int deleteCount = 0;

        try {
            con = getConnection();
            con.setAutoCommit(false);

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT SCR_TBL_COL_DEF_CLOD ");
            sb.append("FROM SCR_TBL_COL_DEF ");
            sb.append("WHERE GLBL_CMPY_CD = ? ");
            sb.append("AND USR_ID = ? ");
            sb.append("AND SCR_APP_ID = ? ");
            sb.append("AND SCR_TBL_NM = ? ");
            sb.append("AND SCR_TBL_COL_DEF_NM = ? ");
            sb.append("FOR UPDATE ");
            String sql = sb.toString();

            Iterator<String[]> it = deleteList.iterator();
            while (it.hasNext()) {
                String[] deleteInfo = (String[]) it.next();

                // Select the text you wish to register a record. using a lock for update
                pstmt01 = con.prepareStatement(sql);
                pstmt01.setString(1, deleteInfo[0]);
                pstmt01.setString(2, deleteInfo[1]);
                pstmt01.setString(3, deleteInfo[2]);
                pstmt01.setString(4, deleteInfo[3]);
                pstmt01.setString(5, deleteInfo[4]);

                boolean returnFlag = pstmt01.execute();

                if (returnFlag) {
                    sb = new StringBuilder();
                    sb.append("UPDATE SCR_TBL_COL_DEF ");
                    sb.append("SET EZCANCELFLAG = '1' ");
                    sb.append("WHERE GLBL_CMPY_CD = ? ");
                    sb.append("AND USR_ID = ? ");
                    sb.append("AND SCR_APP_ID = ? ");
                    sb.append("AND SCR_TBL_NM = ? ");
                    sb.append("AND SCR_TBL_COL_DEF_NM = ? ");
                    sb.append("AND EZCANCELFLAG = '0' ");
                    String sql2 = sb.toString();

                    pstmt02 = con.prepareStatement(sql2);
                    pstmt02.setString(1, deleteInfo[0]);
                    pstmt02.setString(2, deleteInfo[1]);
                    pstmt02.setString(3, deleteInfo[2]);
                    pstmt02.setString(4, deleteInfo[3]);
                    pstmt02.setString(5, deleteInfo[4]);
                    returnCode = pstmt02.executeUpdate();
                    if (returnCode == 1) {
                        deleteCount++;
                    }
                }
            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new S21AbendException(e1);
            }
            throw new S21AbendException(e);
        } finally {
            try {
                con.commit();
                if (pstmt01 != null) {
                    pstmt01.close();
                }
                if (pstmt02 != null) {
                    pstmt02.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new S21AbendException(e);
            }
        }

        return deleteCount;
    }

    // START 01/27/17 C.Ogaki [Add] Release 2000 byte length limit
    /**
     * getPreferredViewList
     * @param   cMsg ZZVL0000CMsg
     * @param   length int
     * @return  List<String[]>
     */
    protected List<String[]> getPreferredViewList(String glblCmpyCd, String roleNm, int length) {

        List<String[]> ret = new ArrayList<String[]>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean returnCode;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SCR_APP_ID, ");
        sb.append("SCR_TBL_NM, ");
        sb.append("SCR_TBL_COL_DEF_NM, ");
        sb.append("SCR_TBL_COL_DEF_CLOD, ");
        sb.append("BIZ_APP_NM ");
        sb.append("FROM (SELECT STCD.SCR_APP_ID AS SCR_APP_ID, ");
        sb.append("STCD.SCR_TBL_NM AS SCR_TBL_NM, ");
        sb.append("STCD.SCR_TBL_COL_DEF_NM AS SCR_TBL_COL_DEF_NM, ");
        sb.append("STCD.SCR_TBL_COL_DEF_CLOD AS SCR_TBL_COL_DEF_CLOD, ");
        sb.append("TAB.BIZ_APP_NM AS BIZ_APP_NM ");
        sb.append("FROM SCR_TBL_COL_DEF STCD, ");
        sb.append("( SELECT TAB.BIZ_APP_ID AS BIZ_APP_ID, MIN(TAB.BIZ_APP_NM) AS BIZ_APP_NM ");
        sb.append("FROM UP_TAB TAB ");
        sb.append("WHERE TAB.GLBL_CMPY_CD = ? ");
        sb.append("AND TAB.EZCANCELFLAG = '0' ");
        sb.append("GROUP BY TAB.BIZ_APP_ID) TAB ");
        sb.append("WHERE STCD.GLBL_CMPY_CD = ? ");
        sb.append("AND STCD.USR_ID = ? ");
        sb.append("AND STCD.SCR_APP_ID = TAB.BIZ_APP_ID(+) ");
        sb.append("AND STCD.EZCANCELFLAG = '0' ");
        sb.append("ORDER BY STCD.SCR_APP_ID, STCD.SCR_TBL_NM, STCD.SCR_TBL_COL_DEF_NM ) ");
        sb.append("WHERE ROWNUM <= ? ");
        String sql = sb.toString();

        try {
            con = getConnection();
            con.setAutoCommit(false);

            // get Data
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, glblCmpyCd);
            pstmt.setString(2, glblCmpyCd);
            pstmt.setString(3, roleNm);
            pstmt.setInt(4, length);

            returnCode = pstmt.execute();

            if (!returnCode) {
                return ret;
            }

            // Successful search process
            rs = pstmt.getResultSet();
            while (rs.next()) {
                // The Text
                String defNm = rs.getString(SCR_TBL_COL_DEF_NM_COL);
                String scrAppId = rs.getString(SCR_APP_ID);
                String scrTblNm = rs.getString(SCR_TBL_NM);
                String bizAppNm = rs.getString(BIZ_APP_NM);
                String usrDefFlg = "";
                String orgOwner = "";
                Clob lob = rs.getClob(SCR_TBL_COL_DEF_CLOD);
                if (lob != null) {
                    int size = (int) lob.length();
                    String text = lob.getSubString(1, size);
                    if (text != null) {
                        String[] vals = text.split(",");
                        usrDefFlg = vals[INDEX_USR_DEF_FLG];
                        if (vals.length > INDEX_ORG_USR_ID) {
                            orgOwner = vals[INDEX_ORG_USR_ID];
                        }
                    }
                }

                String[] array = {defNm, scrAppId, scrTblNm, bizAppNm, usrDefFlg, orgOwner};
                ret.add(array);
            }

            return ret;
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            closeResource(con, pstmt, rs);
        }

    }
    // END   01/27/17 C.Ogaki [Add] Release 2000 byte length limit
}
