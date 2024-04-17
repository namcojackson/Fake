/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8880.common;

//import static business.blap.NYEL8880.constant.NYEL8880Constant.ADMIN_GRP;
import static business.blap.NYEL8880.constant.NYEL8880Constant.NYEM0002E;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001;
import com.canon.cusa.s21.common.NYX.NYXC880001.NYXC880001Query;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;


import business.blap.NYEL8880.NYEL8880CMsg;
import business.blap.NYEL8880.NYEL8880SMsg;
import business.blap.NYEL8880.constant.NYEL8880Constant;

/**
 *<pre>
 * NYEL8850CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         Q10627          Create          N/A
 *</pre>
 */
public class NYEL8880CommonLogic {

    /**
     * search
     * @param bizMsg Business Message
     * @param sMsg Server Message
     * @param usrId user Id
     */
    public static void search(NYEL8880CMsg bizMsg, NYEL8880SMsg sMsg, String myUser) {

        boolean isAdmin = NYXC880002.isAdministrator(myUser);

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult result = null;

        String wfBizAppId = bizMsg.wfBizAppId.getValue();
        String usrNm = bizMsg.usrNm.getValue();
        String lastNm = bizMsg.lastNm.getValue();
        String firstNm = bizMsg.firstNm.getValue();
        int rowNum = sMsg.A.length();

        result = NYXC880001.getAssigners(wfBizAppId, myUser, isAdmin, usrNm, lastNm, firstNm, rowNum + 1);

        if (result.isCodeNormal()) {

            List resultList = (List) result.getResultObject();
            int queryResCnt= result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                bizMsg.setMessageInfo("NZZM0001W" );
                queryResCnt = sMsg.A.length();
            }

            for (int i = 0; i < queryResCnt; i++) {
                Map map = (Map) resultList.get(i);
                usrNm = (String) map.get("USR_NM");
                lastNm = (String) map.get("LAST_NM");
                firstNm = (String) map.get("FIRST_NM");
                if (ZYPCommonFunc.hasValue(usrNm)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).usrNm_A, usrNm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).lastNm_A, lastNm);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).firstNm_A, firstNm);
                }
            }
            sMsg.A.setValidCount(queryResCnt);

            int i = 0;
            for( ; i < sMsg.A.getValidCount(); i++ ) {
                if( i == bizMsg.A.length() ) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
        }
        else {
            bizMsg.setMessageInfo(NYEM0002E);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }

        return;
    }
}
