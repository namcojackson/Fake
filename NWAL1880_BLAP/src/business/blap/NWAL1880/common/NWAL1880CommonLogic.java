/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1880.common;

import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

import business.blap.NWAL1880.NWAL1880CMsg;
import business.blap.NWAL1880.NWAL1880Query;
import static business.blap.NWAL1880.constant.NWAL1880Constant.BIZ_ID;;

/**
 *<pre>
 * NWAL1880CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/26   Fujitsu         K.Sato          Create          N/A
 *</pre>
 */
public class NWAL1880CommonLogic {

    /**
     * Set Authority
     * @param bizMsg NWAL1880CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1880CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
//        String[] array = {"NWAL1880T010","NWAL1880T020","NWAL1880T030"};
//        List<String> functionIds = java.util.Arrays.asList(array);

        if (functionIds == null || functionIds.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfileService.getContextUserInfo().getUserId()});
        }

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.H.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.H.setValidCount(funcIdCnt);
    }

    /**
     * getOrdTeamZoneByUser
     * @param bizMsg NWAL1880CMsg
     * @param srchOptUsrId user id
     */
    public static void getOrdTeamZoneByUser(NWAL1880CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NWAL1880Query.getInstance().getOrdTeamZoneByUser(bizMsg, srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.xxOrdTeamSrchTxt.setValue((String) resultMap.get("ORD_TEAM_MSTR_NM"));
            bizMsg.xxOrdZnSrchTxt.setValue((String) resultMap.get("ORD_ZN_DESC_TXT"));
        }

    }

}
