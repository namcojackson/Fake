/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/20   Hitachi         T.Fukuta        Create          CSA-QC#61467
 * </pre>
 */
public class NWAL1770QueryForAttachment extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForAttachment MY_INSTANCE = new NWAL1770QueryForAttachment();

    /**
     * Constructor.
     */
    private NWAL1770QueryForAttachment() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770QueryForAttachment
     */
    public static NWAL1770QueryForAttachment getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Comment
     * @param bizMsg NWAL1770CMsg
     * @param attDataPk Primary Key for Comment
     * @return Comment
     */
    public S21SsmEZDResult getComment(NWAL1770CMsg bizMsg, BigDecimal attDataPk) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("attDataPk", attDataPk);

        return getSsmEZDClient().queryObjectList("getAttCmnt", params);
    }
}
