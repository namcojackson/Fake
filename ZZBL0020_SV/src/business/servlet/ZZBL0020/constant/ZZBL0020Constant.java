/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/26/2009   Fujitsu         Yamaguchi       Create          N/A
 *</Pre>
 */package business.servlet.ZZBL0020.constant;

import com.canon.cusa.s21.common.ZZX.ZZXC001001.ZZXC001002;

public interface ZZBL0020Constant  extends ZZXC001002 {

    /** 
     * ADDED mark for ACTV_FLG
     */
    public static final String ADDED = "0";
    
    /** 
     * EXIST from begining mark for ACTV_FLG 
     */
    public static final String EXIST = "1";
    
    /** 
     * EDITED mark for ACTV_FLG 
     */
    public static final String EDITED = "2";
    
    /** 
     * DELETED mark for ACTV_FLG 
     */
    public static final String DELETED = "3";    
    
    /** Max Row Number of Search Result */
	public final int MAX_ROW=200;
    
    public static final String BUSINESS_ID = "ZZBL0020";
		
}
