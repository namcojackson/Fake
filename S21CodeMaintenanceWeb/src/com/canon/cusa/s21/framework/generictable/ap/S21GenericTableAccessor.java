package com.canon.cusa.s21.framework.generictable.ap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfoContainer;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableDBAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableDBAccessorImpl;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

public class S21GenericTableAccessor {
		
	private static S21CodeTableDBAccessor dbAccessor = new S21CodeTableDBAccessorImpl();
	
	protected static List<S21CodeTableColumnInfo> getColumnInfoAll(String codeTableName) {

        S21CodeTableColumnInfoContainer infoCont = S21MsgAccessor.getInstance().getColumnInfoCont(codeTableName);

        if (infoCont == null) {
            throw new IllegalArgumentException();
        }

        return Arrays.asList(infoCont.getAll());
    }
    
	protected static S21CodeTableColumnInfo getColumnInfo(String codeTableName, String columnName) {

        S21CodeTableColumnInfo info = S21MsgAccessor.getInstance().getColumnInfo(codeTableName, columnName);

        if (info == null) {
            throw new IllegalArgumentException();
        }

        return info;
    }

    protected static int count(String tableName) {
        return dbAccessor.count(tableName);
    }
    
    protected static int count(EZDTMsg msg) {
        return dbAccessor.count(msg);
    }
    
    protected static EZDTMsgArray findAll(String tableName) {
        return dbAccessor.readAll(tableName);
	}
	
    protected static EZDTMsgArray findAll(EZDTMsg msg) {
        return findAll(msg.getTableName());
    }
	
    protected static EZDTMsgArray findAllRowNum(String codeTableName, int start, int len) {
        return dbAccessor.readRowNum(codeTableName, start, len);
    }
    
    protected static EZDTMsgArray findByConditionRowNum(EZDTMsg msg, int start, int len) {
        return dbAccessor.readByCondition(msg, start, len);
    }
	    
    protected static EZDTMsg findByKey(EZDTMsg msg) {
        // get by direct DB access
        return dbAccessor.readByKey(msg);
    }
    
    protected static EZDTMsgArray findByCondition(String codeTableName, Map<String, String> key) {
    	return dbAccessor.readByCondition(S21CMCodetableReflectionInvoker.invokeMap2msg4cache(codeTableName, key));
    }
    
    protected static void create(EZDTMsg msg) {
    	dbAccessor.create(msg);
    }
    
    protected static void update(EZDTMsg msg) {
    	dbAccessor.update(msg);
    }
    
    protected static void delete(EZDTMsg msg) {
//    	dbAccessor.delete(msg);
    	EZDTBLAccessor.remove(msg);
    }
}
