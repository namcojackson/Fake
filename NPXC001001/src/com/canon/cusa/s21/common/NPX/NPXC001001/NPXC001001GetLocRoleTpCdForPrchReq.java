/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INVTY_LOC_VTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * GetLocRoleTpCdForPrchReq
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/01/07   Hitachi         T.Aoyagi        Create          N/A
 * 2013/06/26   Hitachi         T.Tomita        Update          QC1325
 * 2013/07/17   Hitachi         T.Tomita        Update          QC1325
 *</pre>
 */
public class NPXC001001GetLocRoleTpCdForPrchReq {

    /** S21SsmBatchClient */
    private static S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NPXC001001GetLocRoleTpCdForPrchReq.class);

    /**
     * Get LocRoleTpCd
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return NPXC001001GetLocRoleTpCdForPrchReqBean
     */
    public static NPXC001001GetLocRoleTpCdForPrchReqBean getLocRoleTpCd(String glblCmpyCd, String invtyLocCd) {

        String locRoleTpCd = null;

        locRoleTpCd = selectLocUsg(glblCmpyCd, invtyLocCd);

        DS_INVTY_LOC_VTMsg dsInvtyLocVTMsg = selectDsInvtyLocV(glblCmpyCd, invtyLocCd);

        return returnParam(locRoleTpCd, dsInvtyLocVTMsg);
    }

    @SuppressWarnings("unchecked")
    private static String selectLocUsg(String glblCmpyCd, String invtyLocCd) {

        String locRoleTpCd = null;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ptyCd", invtyLocCd);
        List<String> resultList = ssmClient.queryObjectList("getLocRoleTp", ssmParam);

        if (resultList.size() > 0) {
            locRoleTpCd = resultList.get(0);
        }
        return locRoleTpCd;
    }

    private static DS_INVTY_LOC_VTMsg selectDsInvtyLocV(String glblCmpyCd, String invtyLocCd) {
        DS_INVTY_LOC_VTMsg inMsg = new DS_INVTY_LOC_VTMsg();

        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("invtyLocCd01", invtyLocCd);
        DS_INVTY_LOC_VTMsgArray outMsgArray = (DS_INVTY_LOC_VTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        DS_INVTY_LOC_VTMsg result = null;
        if (outMsgArray.getValidCount() > 0) {
            result = outMsgArray.no(0);
        }
        return result;
    }

    private static NPXC001001GetLocRoleTpCdForPrchReqBean returnParam(String locRoleTpCd, DS_INVTY_LOC_VTMsg dsInvtyLocVTMsg) {
        NPXC001001GetLocRoleTpCdForPrchReqBean rtnBean = null;
        if (dsInvtyLocVTMsg != null) {
            rtnBean = new NPXC001001GetLocRoleTpCdForPrchReqBean();
            // Set Location Type Code
            rtnBean.setLocTpCd(dsInvtyLocVTMsg.invtyLocTpCd.getValue());
            // Set Location Role Type Code
            if (ZYPCommonFunc.hasValue(locRoleTpCd)) {
                rtnBean.setLocRoleTpCd(locRoleTpCd);
            } else {
                rtnBean.setLocRoleTpCd(dsInvtyLocVTMsg.locRoleTpCd.getValue());
            }
        }

        return rtnBean;
    }
}
