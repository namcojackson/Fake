package business.parts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import parts.common.EZDPMsg;

import com.canon.cusa.s21.framework.online.join.S21JoinAccessor;
import com.canon.cusa.s21.framework.online.join.S21JoinUtil;
import com.canon.cusa.s21.framework.online.join.S21QueryMsg;

/**
 * 「業務ID:XXXL9900　BLAP Appliation」から利用されるJoin Query Component。
 * 
 * <pre>
 * AOM02.EZBUSINESSIDでAOM03と外部結合し、重複を除いたレコードを取得する。
 * 尚、EZDPMsg.ezBusinessIDの値が設定されている場合は、その値と一致するAOM02.EZBUSINESSIDのレコードを取得する。
 * 
 * <dt>取得項目</dt>
 * <dd>AOM02.EZBUSINESSID</dd>
 * <dd>AOM02.EZCANCELFLAG</dd>
 * <dd>AOM02.EZONLSTARTTIME</dd>
 * <dd>AOM02.EZBUSINESSID</dd>
 * <dd>AOM02.EZONLSTOPFLAG</dd>
 * 
 * <dt>ソート条件</dt>
 * <dd>AOM02.EZBUSINESSID ASC</dd>
 * </pre>
 * 
 * @author $Author: Taiki Tsuji $
 * @version $Revision: 1.1.2.1 $ $Date: 2009/01/14 15:56:01 $
 */
public class ZZZQ910000Join extends S21JoinAccessor {

	private static ZZZQ910000Join myInstance = new ZZZQ910000Join();
	
	private ZZZQ910000Join() {
	}
	
	/**
	 * singleton instance getter
	 * @return	XXXQ990000Join
	 */
	public static ZZZQ910000Join getInstance() {
		return myInstance;
	}

	@Override
	protected Connection getJdbcConnection() throws SQLException {
		return getConnection();
	}
	
	@Override
	protected S21QueryMsg createQuery(EZDPMsg pMsg) {
		
		ZZZQ910000PMsg joinMsg = (ZZZQ910000PMsg)pMsg;
		S21QueryMsg    query   = new S21QueryMsg();
	
		// SELECT
		query.appendQuery( "SELECT" );
		query.appendQuery( "        DISTINCT( AOM02.EZBUSINESSID )" );
		query.appendQuery( "    ,   AOM02.EZCANCELFLAG" );
		query.appendQuery( "    ,   AOM02.EZONLSTARTTIME" );
		query.appendQuery( "    ,   AOM02.EZONLENDTIME" );
		query.appendQuery( "    ,   AOM02.EZONLSTOPFLAG" );
		query.appendQuery( "    ,   AOM02.EZCOMPANYCODE" );
	
		// FROM
		query.appendQuery( "FROM" );
		query.appendQuery( "        AOM02" );
		query.appendQuery( "    ,   AOM03" );
	
		// WHERE
		query.appendQuery( "WHERE" );
		query.appendQuery( "        AOM02.EZBUSINESSID = AOM03.EZBUSINESSID (+)" );
		if( !joinMsg.ezBusinessID.isClear() ) {
			query.appendQuery( "        AND AOM02.EZBUSINESSID = ?" );
			query.addParam( joinMsg.ezBusinessID.getValue() );
		}
		
		// ORDER BY
		query.appendQuery( "ORDER BY" );
		query.appendQuery( "        AOM02.EZBUSINESSID ASC" );
		
		return query;
	}

	@Override
	protected void mappingResultData(ResultSet res, int resRows, EZDPMsg pMsg) throws SQLException {
		
		ZZZQ910000PMsg joinMsg = (ZZZQ910000PMsg)pMsg;
		S21JoinUtil    jUtil   = getJoinUtil();
		
		int rowIndex = 0;
		do {
			jUtil.setValue( joinMsg.A.no( rowIndex ).ezBusinessID_A1,   jUtil.getString( res, "EZBUSINESSID" ) );
			jUtil.setValue( joinMsg.A.no( rowIndex ).ezCancelFlag_A1,   jUtil.getString( res, "EZCANCELFLAG" ) );
			jUtil.setValue( joinMsg.A.no( rowIndex ).ezOnlStartTime_A1, jUtil.getString( res, "EZONLSTARTTIME" ) );
			jUtil.setValue( joinMsg.A.no( rowIndex ).ezOnlEndTime_A1,   jUtil.getString( res, "EZONLENDTIME" ) );
			jUtil.setValue( joinMsg.A.no( rowIndex ).ezOnlStopFlag_A1,  jUtil.getString( res, "EZONLSTOPFLAG" ) );
			jUtil.setValue( joinMsg.A.no( rowIndex ).ezCompanyCode_A1,  jUtil.getString( res, "EZCOMPANYCODE" ) );
			rowIndex++;
			
			// PMsg.Aの配列の要素数に到達した場合、ループ処理から抜ける
			if( rowIndex == joinMsg.A.length() ) {
				break;
			}
		} while( res.next() );
		joinMsg.A.setValidCount( rowIndex );
		
		// [Warning]検索結果が多過ぎる
		if( resRows > joinMsg.A.length() ) {
			joinMsg.setReturnCode( RTNCD_RESULT_MAX_OVER );
		}
		
	}

}