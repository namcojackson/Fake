/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500;

import java.io.Serializable;
import java.util.Comparator;

/**
 * <pre>
 * Compare NWAL1500_DSMsg with xxLineNum_RL
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         Y.Kanefusa      Create          N/A
 * </pre>
 */
public class NWAL1500CompareDSMsg implements Comparator<NWAL1500_DSMsg>, Serializable {

    /** Default serial Version */
    private static final long serialVersionUID = 1L;

    /**
     * Compare NWAL1500_DSMsg Object with xxLineNum_RL
     * @param o1 NWAL1500_DSMsg
     * @param o2 NWAL1500_DSMsg
     * @return compare result
     */
    @Override
    public int compare(NWAL1500_DSMsg o1, NWAL1500_DSMsg o2) {

        String configLineNum1 = o1.xxLineNum_RL.getValue();
        String[] configLineNum1Array = configLineNum1.split("\\.");
        Integer pos1 = Integer.valueOf(configLineNum1Array[0]);
        Integer line1 = Integer.valueOf(configLineNum1Array[1]);

        String configLineNum2 = o2.xxLineNum_RL.getValue();
        String[] configLineNum2Array = configLineNum2.split("\\.");
        Integer pos2 = Integer.valueOf(configLineNum2Array[0]);
        Integer line2 = Integer.valueOf(configLineNum2Array[1]);

        if (pos1.compareTo(pos2) == 0) {
            return line1.compareTo(line2);
        } else {
            return pos1.compareTo(pos2);
        }
    }
}
