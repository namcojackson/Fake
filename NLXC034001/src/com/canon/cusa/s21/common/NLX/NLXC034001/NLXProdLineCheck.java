package com.canon.cusa.s21.common.NLX.NLXC034001;

import java.util.ArrayList;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;

import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 * NLXC0340:Product Line Check
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/12/2010   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLXProdLineCheck extends S21ApiCommonBase {

    /**
     * <pre>
     * Execute Product Line Check.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param mdseCd     Merchandise Code
     * @param pcsList    User Profile (PCS)
     * @return
     */
    public static String exec(String glblCmpyCd, String mdseCd, List<S21DataSecurityAttributeData> pcsList) {

        // Check input parameters.
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return "NLXM1023E";
        }
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return "NLXM1024E";
        }

        // Find Merchandise Master.
        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(glblCmpyCd);
        mdseTMsg.mdseCd.setValue(mdseCd);
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
        if (mdseTMsg == null) {
            return "NLXM1027E";
        }

        // Execute Product Line Check.
        return exec(mdseTMsg, pcsList);
    }

    /**
     * <pre>
     * Execute Product Line Check.
     * </pre>
     * @param mdseTMsg Merchandise TMsg
     * @param pcsList  User Profile (PCS)
     * @return
     */
    public static String exec(MDSETMsg mdseTMsg, List<S21DataSecurityAttributeData> pcsList) {

        // Check input parameters.
        if (mdseTMsg == null) {
            return "NLXM1026E";
        }
        if (pcsList == null
                || pcsList.isEmpty()) {
            return "NLXM1025E";
        }

        // Get Layer values.
        S21DataSecurityAttributeData secAttrbData = null;
        List<String> layer1 = new ArrayList<String>();
        List<String> layer2 = new ArrayList<String>();
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

        // Check products.
        return checkProducts(mdseTMsg, layer1, layer2);
    }

    /**
     * <pre>
     * Check products.
     * The information layer, as shown below checks.
     * ('*' can use all merchandise)
     * 
     *                     |Layer#1|Layer#2|
     * --------------------+-------+-------|
     * No check            |   *   |   *   |
     *                     |   *   | other |
     * --------------------+-------+-------|
     * Check Layer#1       | other |   *   |
     * --------------------+-------+-------|
     * Check Layer#1 or #2 | other | other |
     * 
     * </pre>
     * @param mdseTMsg Merchandise TMsg
     * @param layer1   Layer#1 (User's Product Group)
     * @param layer2   Layer#2 (User's Product Line)
     * @return Check result message code.
     */
    private static String checkProducts(MDSETMsg mdseTMsg, List<String> layer1, List<String> layer2) {

        // User Profile (PCS) is incorrect. 
        if (layer1.isEmpty() && layer2.isEmpty()) {
            return "NLXM1028E";
        }

        boolean astExstFlgL1 = layer1.contains("*");
        boolean astExstFlgL2 = layer2.contains("*");

        // Normal Check.
        if (!astExstFlgL1 && !astExstFlgL2) {

            // Check Product Line.
            if (!layer2.isEmpty()) {
                return compareProducts(layer2, mdseTMsg.firstProdCtrlCd.getValue());
            }

            // Check Product Group.
            if (!layer1.isEmpty()) {
                return compareProducts(layer1, mdseTMsg.zerothProdCtrlCd.getValue());
            }

        // Check Layer1.
        } else if (!astExstFlgL1 && astExstFlgL2) {

            // Check Product Group.
            if (!layer1.isEmpty()) {
                return compareProducts(layer1, mdseTMsg.zerothProdCtrlCd.getValue());
            }
        }

        return null;
    }

    /**
     * <pre>
     * Compare Products.
     * </pre>
     * @param prodList
     * @param prodCd
     * @return
     */
    private static String compareProducts(List<String> prodList, String prodCd) {

        if (!ZYPCommonFunc.hasValue(prodCd)) {
            return "NLXM1029E";
        }

        if (prodList.contains(prodCd)) {
            // Normal end.
            return null;
        }

        return "NLXM1030E";
    }
}
