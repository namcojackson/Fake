/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC013001;

import com.canon.cusa.s21.common.NLX.NLXC013001.constant.NLXMerchandiseCheckConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/**
 * <pre>
 * Merchandise Check for following parameters.
 *      Merchandise Type
 *      Inventory Control Flag
 *      Registration Status
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2009   Fujitsu         FAP)D.Ushida    Create          N/A
 * 01/08/2010   Fujitsu         M.Yamada        Update          RQ0826
 * </pre>
 */
public class NLXMerchandiseCheck extends S21ApiCommonBase implements NLXMerchandiseCheckConstant {

    /**
     * @param coaProdCd String coaProdCd
     * @param mdseTpCd String mdseTpCd
     * @param invtyCtrlFlg String invtyCtrlFlg
     * @param firstProdCtrlCd String firstProdCtrlCd
     * @param rgtnStsCd String rgtnStsCd
     * @return error:MssageCode,normal:null
     */
    public static String exec(String coaProdCd, String mdseTpCd, String invtyCtrlFlg, String firstProdCtrlCd, String rgtnStsCd) {

        return exec(mdseTpCd, invtyCtrlFlg, rgtnStsCd);

    }

    /**
     * @param mdseTpCd String mdseTpCd
     * @param invtyCtrlFlg String invtyCtrlFlg
     * @param rgtnStsCd String rgtnStsCd
     * @return error:MssageCode,normal:null
     */
    public static String exec(String mdseTpCd, String invtyCtrlFlg, String rgtnStsCd) {

        if (!(MDSE_TP.REGULAR.equals(mdseTpCd) || MDSE_TP.KIT.equals(mdseTpCd) || MDSE_TP.REFURBISHED.equals(mdseTpCd))) {

            return MSG_ID_GENERAL_ERROR;
        }

        if (RGTN_STS.PENDING_COMPLETION.equals(rgtnStsCd) || RGTN_STS.TERMINATED.equals(rgtnStsCd)) {

            return MSG_ID_GENERAL_ERROR;
        }

        if (!ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {

            return MSG_ID_GENERAL_ERROR;
        }

        return null;

    }
}
