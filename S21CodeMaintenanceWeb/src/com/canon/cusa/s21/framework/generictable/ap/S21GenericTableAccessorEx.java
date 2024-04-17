/**
 * 
 */
package com.canon.cusa.s21.framework.generictable.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDLog;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgConst;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDMonitorTBLAccessor;

import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableTBLAccessorEx;

/**
 * @author q02638
 *
 */
public class S21GenericTableAccessorEx extends S21CodeTableTBLAccessorEx {

//    protected void standardLogicalRemoveAll(EZDTMsg inMsg) {
//
//        EZDLog.println(EZDLog.LEVEL_MSG_DUMP, "CALL: S21CodeTableTBLAccessorEx#standardLogicalRemoveAll");
//        checkPrimaryKey(inMsg);
//
//        String nowTime = getUpdateDateTime();
//        String nowTimeZone = getUpdateTimeZone(nowTime);
//        String tblName = inMsg.getTableName();
//        inMsg.setDBTableID();
//        inMsg.setDBCancelFlag(TABLE_DATA_DEATH);
//        inMsg.setDBUpTime(nowTime);
//        inMsg.setDBUpUserID(EZDDBCICarrier.getUserID());
//        inMsg.setDBUpAplID(EZDDBCICarrier.getUppgID());
//        inMsg.setDBUpTimeZone(nowTimeZone);
//        inMsg.setDBUpCompanyCode(EZDDBCICarrier.getUpCmpyCd());
//
//        // List[] keyList = inMsg.getKeyColumnList();
//        List[] keyList = createCancelColumnEx(inMsg);
//
//        ArrayList scmList = new ArrayList(7);
//        scmList.add(EZDTMsgConst.EZTABLEID_CLM);
//        scmList.add(EZDTMsgConst.EZCANCELFLAG_CLM);
//        scmList.add(EZDTMsgConst.EZUPTIME_CLM);
//        scmList.add(EZDTMsgConst.EZUPUSERID_CLM);
//        scmList.add(EZDTMsgConst.EZUPAPLID_CLM);
//
//        scmList.add(EZDTMsgConst.EZUPTIMEZONE_CLM);
//        scmList.add(EZDTMsgConst.EZUPCOMPANYCODE_CLM);
//
//        List[] updtList = makeDBColumnList(scmList, inMsg.getExceptedKeyColumnList());
//
//        String sql = constructUpdateSQLEx(tblName, keyList[1], updtList[1]);
//
//        Connection cnct = null;
//        PreparedStatement pstmt = null;
//        try {
//            int id = getDataSourceID(tblName);
//            cnct = getConnection(id);
//            pstmt = cnct.prepareStatement(sql);
//
//            setParameters(pstmt, 0, updtList[0], inMsg, id);
//            inMsg.setDBCancelFlag(TABLE_DATA_LIVE);
//            setParameters(pstmt, updtList[0].size(), keyList[0], inMsg, id);
//            int cnt = pstmt.executeUpdate();
//
//            if (cnt == 0) {
//                inMsg.setReturnCode(RTNCD_NOT_FOUND);
//            } else {
//                inMsg.setReturnCode(RTNCD_NORMAL);
//                EZDMonitorTBLAccessor.getInstance().logicalRemove(inMsg);
//            }
//            return;
//
//        } catch (SQLException e) {
//            throw new EZDDBRetryRequestException(e);
//
//        } finally {
//            closeResource(cnct, pstmt, null);
//        }
//    }
}
