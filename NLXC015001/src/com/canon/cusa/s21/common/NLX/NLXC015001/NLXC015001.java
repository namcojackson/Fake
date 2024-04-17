/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.log.S21AbendException;

import parts.common.EZDItemAttrDefines;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDValidatorException;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/04/2009   Fujitsu         H.Haga          Create          N/A
 *</pre>
 */
public final class NLXC015001 {

    /* */
    public static final String SORT_ASC = "0";

    /* */
    public static final String SORT_DESC = "1";

    /**
     */
    private NLXC015001() {
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public static List sortListMap(List array, String[] sortKey, String[] orderFlg) {

        if (array != null && sortKey != null && orderFlg != null) {
            if (1 < array.size()) {
                NLXC015008 sortDefine = new NLXC015008();
                sortDefine.setSortKeyOrder(sortKey, orderFlg);
                Collections.sort(array, sortDefine);
            }
        }
        return array;
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public static List sortListMap(List array, String[] sortKey, String[] orderFlg,
            String[] orderType) {

        if (array != null && sortKey != null && orderFlg != null && orderType != null) {
            if (1 < array.size()) {
                NLXC015008 sortDefine = new NLXC015008();
                sortDefine.setSortKeyOrder(sortKey, orderFlg, orderType);
                Collections.sort(array, sortDefine);
            }
        }
        return array;
    }

    /**
     * 
     * @param list List
     * @return boolean
     */
    public static boolean hasValue(List list) {
        return list != null && !list.isEmpty() && !(list.size() == 1 && list.get(0) == null);
    }

    /**
     * 
     * <pre>
     * <ul>
     * <li>EZDItemAttrDefines.TYPE_SEISU_SYOSU (I/O item type is "10" = Numerical value with decimal)
     * </li>
     * <li>EZDItemAttrDefines.TYPE_HANKAKUSUJI (I/O item type is "34" =  Normal-width figure)
     * </li>
     *      <ul>
     *      </ul>
     * </li>
     * 
     * <pre>
     *
     * S21SortTarget sortTarget = new S21SortTarget( scrnMsg.A, scrnMsg.A.no( 0 ).getBaseContents() );
     * 
     *
     * S21SortKey sortKey = new S21SortKey();
     * sortKey.add( "acctCd", S21SortKey.DESC );
     * sortKey.add( "invQty", S21SortKey.ASC );
     * 
     *
     * S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, scrnMsg.A.getValidCount() );
     * </pre>
     * 
     * @throws NoSuchFieldException NoSuchFieldException
     * @throws IllegalAccessException IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public static void sort(NLXC015002 sortTarget, NLXC015003 sortKey, int sortStartPosition, int sortEndPosition) throws IllegalAccessException, NoSuchFieldException {

        List sortList = new ArrayList();
        for (int i = sortStartPosition; i < sortEndPosition; i++) {
            NLXC015004 sRcd = new NLXC015004();
            for (int j = 0; j < sortKey.size(); j++) {
                String key = sortKey.getKey(j);
                sRcd.add(key, sortKey.getOrderBy(j), sortTarget.getEZDItem(key, i), sortTarget.getItemType(key, i));
            }
            sortList.add(sRcd);
        }

        Map sortDataMap = createSortDataMap(sortList, sortTarget, sortStartPosition, sortEndPosition);

        Collections.sort(sortList, new NLXC015006());

        reflection(sortTarget.getEzdMsgArray(), sortList, sortDataMap, sortStartPosition);

    }

    @SuppressWarnings("unchecked")
    private static Map createSortDataMap(List sortList, NLXC015002 sortTarget, int sortStartPosition, int sortEndPosition) {
        Map sortDataMap = new HashMap();
        for (int i = sortStartPosition; i < sortEndPosition; i++) {
            Map rowDataMap = new HashMap<String, String>();
            Iterator itemNmIt = sortTarget.getTargetItemNmSet().iterator();
            while (itemNmIt.hasNext()) {
                String itemNm = (String) itemNmIt.next();
                rowDataMap.put(itemNm, sortTarget.getValueString(itemNm, i));
            }
            sortDataMap.put(sortList.get(i - sortStartPosition), rowDataMap);
        }
        return sortDataMap;
    }

    private static void reflection(EZDMsgArray ezdMsgArray, List sortList, Map sortDataMap, int sortStartPosition) {
        Iterator it = sortList.iterator();

        while (it.hasNext()) {
            HashMap rowDataMap = (HashMap) sortDataMap.get(it.next());
            if (rowDataMap != null) {
                Iterator itemNmIt = rowDataMap.entrySet().iterator();
                while (itemNmIt.hasNext()) {

                    EZDMsg ezdMsg = ezdMsgArray.get(sortStartPosition);

                    Map.Entry itemEntry = (Map.Entry) itemNmIt.next();
                    String itemNm = (String) itemEntry.getKey();
                    String newValue = (String) itemEntry.getValue();

                    switch (NLXC015005.getItemJavaType(ezdMsg, itemNm)) {

                        // BigDecimal
                        case EZDItemAttrDefines.JT_BIGDECIMAL:
                            BigDecimal bdValue = null;
                            bdValue = new BigDecimal(newValue);
                            ezdMsg.setValue(itemNm, -1, bdValue);

                            break;

                        // String
                        case EZDItemAttrDefines.JT_STRING:
                            try {
                                ezdMsg.setValueString(itemNm, -1, newValue);
                            } catch (EZDValidatorException e1) {
                                throw new S21AbendException(e1);
                            }
                            break;

                        default:
                            break;
                    }
                }
                sortStartPosition++;
            }
        }
    }
}
