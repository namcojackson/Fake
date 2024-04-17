/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1250.common;

import static business.blap.NPAL1250.constant.NPAL1250Constant.NZZM0000E;
import static business.blap.NPAL1250.constant.NPAL1250Constant.NZZM0001W;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1250.NPAL1250CMsg;
import business.blap.NPAL1250.NPAL1250Query;
import business.blap.NPAL1250.NPAL1250SMsg;
import business.db.CNTYTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;


/**
 * <pre>
 * Big Deal Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1250CommonLogic {

    /**
     * Search
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     * @param glblCmpyCd String
     */
    public static void search(NPAL1250CMsg cMsg, NPAL1250SMsg sMsg) {

        S21SsmEZDResult ssmResult = NPAL1250Query.getInstance().searchAcctList(cMsg, sMsg);

        // Got Result
        if( ssmResult.isCodeNormal() ) {

            // Over Max count
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > sMsg.A.length() ) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // Copy（SMsg -> CMsg）
            int i = 0;
            for(; i < sMsg.A.getValidCount(); i++) {
                if(i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);

            }
            cMsg.A.setValidCount(i);

        // No Result
        } else {
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    /**
     * Get County Name
     * @param glblCmpyCd
     * @param cntyPk
     * @return cntyNm
     */
    public static String getCntyNm(String glblCmpyCd, BigDecimal cntyPk){

        CNTYTMsg cntyTMsg = new CNTYTMsg();
        ZYPEZDItemValueSetter.setValue(cntyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cntyTMsg.cntyPk, cntyPk);

        cntyTMsg = (CNTYTMsg) EZDTBLAccessor.findByKey(cntyTMsg);

        if (cntyTMsg == null) {
             return null;
        }

        return cntyTMsg.cntyNm.getValue();
    }
}
