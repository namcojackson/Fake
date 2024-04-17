/**
 * <pre>Copyright(c)2010 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC035001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.PROD_CTRLTMsg;
import business.db.PROD_CTRLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 * NLXC0350:Product Line List Creater
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/16/2010   Fujitsu         S.Yoshida       Create          N/A
 * 05/17/2010   Fujitsu         M.Yamada        Update          Search PL List from PLG List.
 *</pre>
 */
public class NLXProdLineListCreater extends S21ApiCommonBase {

    /** Find Product : Product Group */
    private static final int FIND_PROD_PG  = 1;
    /** Find Product : Product Line */
    private static final int FIND_PROD_PL  = 2;
    /** Use User Profile (PCS) : Layer 1 */
    private static final int USE_LAYER_1   = 3;
    /** Use User Profile (PCS) : Layer 2 */
    private static final int USE_LAYER_2   = 4;

    /** SSM Batch Client */
    private static S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * <pre>
     * Execute Product Line List Creater.
     * </pre>
     * @param glblCmpyCd        Global Company Code
     * @param pcsList           User Profile (PCS)
     * @param valueItemAry      Product list (value)
     * @param dispItemAry       Product list (value + name)
     * @param mdseStruElmntTpCd Merchandise Structure Element Type Code
     * @return
     */
    public static String exec(String glblCmpyCd, List<S21DataSecurityAttributeData> pcsList,
            EZDCStringItemArray valueItemAry, EZDCStringItemArray dispItemAry, EZDCStringItem mdseStruElmntTpCd) {

        // Check input parameters.
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return "NLXM1023E";
        }
        if (pcsList == null
                || pcsList.isEmpty()) {
            return "NLXM1025E";
        }

        // Set up a list of each layer.
        List<String> layer1 = new ArrayList<String>();
        List<String> layer2 = new ArrayList<String>();
        setupLayerList(pcsList, layer1, layer2);

        // User Profile (PCS) is incorrect. 
        if (layer1.isEmpty() && layer2.isEmpty()) {
            return "NLXM1028E";
        }

        PROD_CTRLTMsgArray prodCtrlMsgAry = null;
        ResultSet rs = null;

        try {

            // Create Product List.
            switch (checkLayerList(layer1, layer2)) {

                case FIND_PROD_PG:
                    // find DB 'PLG'
                    rs = findProdNameFromPLG(glblCmpyCd, null);
                    return createListFromRs(rs, valueItemAry, dispItemAry, mdseStruElmntTpCd);

                case FIND_PROD_PL:
                    // find DB 'PL'
                    prodCtrlMsgAry = findProd(glblCmpyCd, MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
                    return createListFromMsg(prodCtrlMsgAry, valueItemAry, dispItemAry, mdseStruElmntTpCd);

                case USE_LAYER_1:
                    // layer1
                    rs = findProdNameFromPLG(glblCmpyCd, layer1);
                    return createListFromRs(rs, valueItemAry, dispItemAry, mdseStruElmntTpCd);

                case USE_LAYER_2:
                    // layer2
                    rs = findProdName(glblCmpyCd, MDSE_STRU_ELMNT_TP.PRODUCT_LINE, layer2);
                    return createListFromRs(rs, valueItemAry, dispItemAry, mdseStruElmntTpCd);
            }

        } catch (SQLException e) {
            throw new EZDDBRetryRequestException(e);
        }

        // Execute Product Line Check.
        return null;
    }

    /**
     * <pre>
     * Setup Layer List.
     * </pre>
     * @param pcsList User Profile (PCS)
     * @param layer1  Layer1 List
     * @param layer2  Layer2 List
     */
    private static void setupLayerList(List<S21DataSecurityAttributeData> pcsList,
            List<String> layer1, List<String> layer2) {

        // Get Layer values.
        S21DataSecurityAttributeData secAttrbData = null;
        String value = null;
        for (int i = 0; i < pcsList.size(); i++) {
            secAttrbData = pcsList.get(i);
            value = secAttrbData.getValue();
            switch (secAttrbData.getLayer()) {
                case 1:
                    layer1.add(value);
                    break;
                case 2:
                    layer2.add(value);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * <pre>
     * Check layer list.
     * The information layer, as shown below checks.
     * 
     *                      |Layer#1|Layer#2|
     * ---------------------+-------+-------|
     * Search Product Group |   *   |   *   |
     *                      |   *   | null  |
     * ---------------------+-------+-------|
     * Search Product Line  | null  |   *   |
     * ---------------------+-------+-------|
     * Use Layer1 List      | other |   *   |
     *                      | other | null  |
     * ---------------------+-------+-------|
     * Use Layer2 List      |   *   | other |
     *                      | other | other |
     *                      | null  | other |
     * ---------------------+-------+-------|
     * Error                | null  | null  |
     * 
     * </pre>
     * @param mdseTMsg Merchandise TMsg
     * @param layer1   Layer#1 (User's Product Group)
     * @param layer2   Layer#2 (User's Product Line)
     * @return Check result message code.
     */
    private static int checkLayerList(List<String> layer1, List<String> layer2) {

        boolean astExstFlgL1 = layer1.contains("*");
        boolean astExstFlgL2 = layer2.contains("*");

        if (astExstFlgL1 && astExstFlgL2) {
            return FIND_PROD_PG;

        } else if (astExstFlgL1 && !astExstFlgL2) {
            if (!layer2.isEmpty()) {
                return USE_LAYER_2;
            } else {
                return FIND_PROD_PG;
            }

        } else if (!astExstFlgL1 && astExstFlgL2) {
            if (!layer1.isEmpty()) {
                return USE_LAYER_1;
            } else {
                return FIND_PROD_PL;
            }

        } else {
            if (!layer2.isEmpty()) {
                return USE_LAYER_2;
            } else {
                return USE_LAYER_1;
            }
        }
    }

    /**
     * <pre>
     * Find Products.
     * </pre>
     * @param glblCmpyCd        Global Company Code
     * @param mdseStruElmntTpCd Merchandise Structure Element Type Code
     * @return Serch results.
     */
    private static PROD_CTRLTMsgArray findProd(String glblCmpyCd, String mdseStruElmntTpCd) {

        PROD_CTRLTMsg prodCtrlMsg = new PROD_CTRLTMsg();
        prodCtrlMsg.setSQLID("008");
        prodCtrlMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prodCtrlMsg.setConditionValue("mdseStruElmntTpCd01", mdseStruElmntTpCd);

        return (PROD_CTRLTMsgArray) EZDTBLAccessor.findByCondition(prodCtrlMsg);
    }

    /**
     * <pre>
     * Create list from PROD_CTRLTMsg.
     * </pre>
     * @param prodCtrlMsgAry Serch result from PROD_CTRL
     * @param valueItemAry   Product list (value)
     * @param dispItemAry    Product list (value + name)
     * @return result (null:Normal end)
     */
    private static String createListFromMsg(PROD_CTRLTMsgArray prodCtrlMsgAry,
            EZDCStringItemArray valueItemAry, EZDCStringItemArray dispItemAry, EZDCStringItem mdseStruElmntTpCd) {

        if (prodCtrlMsgAry.getValidCount() == 0) {
            // error
            return "NLXM1031E";
        }

        PROD_CTRLTMsg prodCtrlMsg = null;
        String code = null;
        String name = null;
        for (int i = 0; i < prodCtrlMsgAry.getValidCount(); i++) {
            prodCtrlMsg = (PROD_CTRLTMsg) prodCtrlMsgAry.get(i);
            code = prodCtrlMsg.prodCtrlCd.getValue();
            name = prodCtrlMsg.prodCtrlNm.getValue();
            valueItemAry.no(i).setValue(code);
            dispItemAry.no(i).setValue(ZYPCommonFunc.concatString(code, ":", name));
        }
        mdseStruElmntTpCd.setValue(prodCtrlMsg.mdseStruElmntTpCd.getValue());

        return null;
    }

    /**
     * <pre>
     * Find product name.
     * </pre>
     * @param glblCmpyCd        Global Company Code
     * @param mdseStruElmntTpCd Merchandise Structure Element Type Code
     * @param layerList         Layer list
     * @return Serch results.
     * @throws SQLException
     */
    private static ResultSet findProdName(String glblCmpyCd, String mdseStruElmntTpCd, List<String> layerList) throws SQLException {

        // Initialization of SQL Accessor
        if (ssmLLClient == null) {
            ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLXProdLineListCreater.class);
        }

        // Execute search for delete query.
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("mdseStruElmntTpCd", mdseStruElmntTpCd);
        paramMap.put("layerList", layerList);

        PreparedStatement prdStmt = ssmLLClient.createPreparedStatement("getProduct", paramMap);
        return prdStmt.executeQuery();
    }

    /**
     * <pre>
     * Find product name.
     * </pre>
     * @param glblCmpyCd        Global Company Code
     * @param mdseStruElmntTpCd Merchandise Structure Element Type Code
     * @param layerList         Layer list
     * @return Serch results.
     * @throws SQLException
     */
    private static ResultSet findProdNameFromPLG(String glblCmpyCd, List<String> layerList) throws SQLException {

        // Initialization of SQL Accessor
        if (ssmLLClient == null) {
            ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLXProdLineListCreater.class);
        }

        // Execute search for delete query.
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("mdseStruElmntTpCd", MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
        paramMap.put("layerList", layerList);

        PreparedStatement prdStmt = ssmLLClient.createPreparedStatement("getProductFromPLG", paramMap);
        return prdStmt.executeQuery();
    }

    /**
     * <pre>
     * Create list from Result set.
     * </pre>
     * @param rs             Serch result from PROD_CTRL
     * @param valueItemAry   Product list (value)
     * @param dispItemAry    Product list (value + name)
     * @return @return result (null:Normal end)
     * @throws SQLException
     */
    private static String createListFromRs(ResultSet rs, EZDCStringItemArray valueItemAry,
            EZDCStringItemArray dispItemAry, EZDCStringItem mdseStruElmntTpCd) throws SQLException {

        if (!rs.next()) {
            // error
            return "NLXM1031E";
        }
        mdseStruElmntTpCd.setValue(rs.getString("MDSE_STRU_ELMNT_TP_CD"));
        rs.previous();

        for (int i = 0; rs.next(); i++) {
            valueItemAry.no(i).setValue(rs.getString("PROD_CTRL_CD"));
            dispItemAry.no(i).setValue(rs.getString("PROD_CTRL"));
        }

        return null;
    }
}
