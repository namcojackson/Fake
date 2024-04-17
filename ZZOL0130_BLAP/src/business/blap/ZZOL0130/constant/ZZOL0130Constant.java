/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0130.constant;

public interface ZZOL0130Constant {

    final String GLOBAL_COMPANY_CODE = "Global Company Code";

    enum HOW_UPDATE { UPDATE, DELETE_INSERT,    };
    
    final String ROLLBACK = "0";
    final String COMMIT   = "1";
    
    final String DB_NORMAL_0000 = "0000";

}
