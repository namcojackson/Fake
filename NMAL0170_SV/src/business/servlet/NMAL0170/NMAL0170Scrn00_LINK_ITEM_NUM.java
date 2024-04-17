/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

/**
 *<pre>
 * NMAL0170Scrn00_LINK_ITEM_NUM
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         q10004          Create          N/A
 * 2016/03/09   CITS            S.Tanikawa      UPDATE          QC#2669
 *</pre>
 */
// DELETE ALL 2016/02/25 QC#2669
// This code is unnecessary.
/*
 * public class NMAL0170Scrn00_LINK_ITEM_NUM extends S21CommonHandler
 * {
 * @Override protected void checkInput(EZDApplicationContext ctx,
 * EZDBMsg bMsg) { // do Nothing. }
 * @Override protected EZDCMsg setRequestData(EZDApplicationContext
 * ctx, EZDBMsg bMsg) { // do Nothing. return null; }
 * @Override protected void doProcess(EZDApplicationContext ctx,
 * EZDBMsg bMsg, EZDCMsg cMsg) { NMAL0170BMsg scrnMsg = (NMAL0170BMsg)
 * bMsg; } private List<Object> getSearchConditionSetting(NMAL0170BMsg
 * scrnMsg) { List<Object> whereList = new ArrayList<Object>(); return
 * whereList; } private List<Object>
 * getDisplayColumnSetting(NMAL0170BMsg scrnMsg) { List<Object>
 * colList = new ArrayList<Object>(); return colList; } private
 * List<Object> getSortSetting(NMAL0170BMsg scrnMsg) { List<Object>
 * sortList = new ArrayList<Object>(); return sortList; } private
 * String getSelectSQL(String itemNumber) { StringBuffer sb = new
 * StringBuffer(); sb.append(" SELECT ");
 * sb.append("      SU.SUPD_TO_MDSE_CD ");
 * sb.append("    , DS.MDSE_DESC_SHORT_TXT "); sb.append(" FROM ");
 * sb.append("      SUPD_RELN_STAGE SU ");
 * sb.append("    , DS_MDSE_INFO DS "); sb.append(" WHERE  ");
 * sb.append("       SU.EZCANCELFLAG = '0' ");
 * sb.append("   AND SU.GLBL_CMPY_CD = '#glblCmpyCd#' ");
 * sb.append("   AND SU.SUPD_TO_MDSE_CD = '#itemNumber#' ");
 * sb.append("   AND DS.EZCANCELFLAG = SU.EZCANCELFLAG ");
 * sb.append("   AND DS.GLBL_CMPY_CD = SU.GLBL_CMPY_CD ");
 * sb.append("   AND DS.MDSE_CD      = SU.SUPD_TO_MDSE_CD  "); String
 * sql = sb.toString(); sql = sql.replaceAll("#glblCmpyCd#",
 * getGlobalCompanyCode()); sql = sql.replaceAll("#itemNumber#",
 * itemNumber); return sql; } }
 */
