/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NWXL0010.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDCStringItem;
import business.blap.NWXL0010.NWXL0010Query;
import business.blap.NWXL0010.constant.NWXL0010Constant;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class NWXL0010CommonLogic implements NWXL0010Constant {

    public static boolean checkSqlStatement(EZDCStringItem xxRptSqlTxt) {

        final String sqlIgnoredMsg = getQuery().checkSqlStatement(xxRptSqlTxt.getValue());

        if (hasValue(sqlIgnoredMsg)) {
            xxRptSqlTxt.setErrorInfo(1, MessageId.NWXM0001E.toString(), new String[] {sqlIgnoredMsg });
            return false;
        }

        return true;
    }

    public static NWXL0010Query getQuery() {
        return NWXL0010Query.getInstance();
    }

    public static S21UserProfileService getUserProfService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    public static BigDecimal toBigDecimal(int intValue) {
        return BigDecimal.valueOf(intValue);
    }

}
