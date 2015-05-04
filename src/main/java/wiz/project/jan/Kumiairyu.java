/**
 * Kumiairyu.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wiz.project.jan.util.JanPaiUtil;



/**
 * 組合竜
 */
public class Kumiairyu {
    
    /**
     * コンストラクタ利用禁止
     */
    private Kumiairyu() {}
    
    
    
    /**
     * 組合竜タイプを取得
     * 
     * @param paiList 手牌。
     * @return 組合竜タイプ。
     */
    public static KumiairyuType getKumiairyuType(final List<JanPai> paiList) {
        final List<KumiairyuType> kumiairyuTypeList = new ArrayList<KumiairyuType>(_kumiairyuMap.keySet());
        
        for (final KumiairyuType kumiairyuType : kumiairyuTypeList) {
            if (paiList.containsAll(_kumiairyuMap.get(kumiairyuType))) {
                return kumiairyuType;
            }
        }
        return KumiairyuType.NONE;
    }
    
    /**
     * 組合竜か
     * 
     * @param paiList 手牌。
     * @return 判定結果。
     */
    public static boolean isKumiairyu(final List<JanPai> paiList) {
        return getKumiairyuType(paiList) != KumiairyuType.NONE;
    }
    
    /**
     * 組合竜を削除
     * 
     * @param source 削除元の牌マップ。
     * @param kumiairyuType 組合竜タイプ。
     */
    public static void removeKumiairyu(final Map<JanPai, Integer> source, final KumiairyuType kumiairyuType) {
        final List<JanPai> paiList = new ArrayList<JanPai>(source.keySet());
        final List<JanPai> kumiairyuList = new ArrayList<JanPai>(_kumiairyuMap.get(kumiairyuType));
        
        for (final JanPai entry : kumiairyuList) {
            if (paiList.contains(entry)) {
                JanPaiUtil.removeJanPai(source, entry, 1);
            }
        }
    }
    
    
    
    /**
     * 組合竜マップ
     */
    @SuppressWarnings("serial")
    private static final Map<KumiairyuType, List<JanPai>> _kumiairyuMap = new HashMap<KumiairyuType, List<JanPai>>() {
        {put(KumiairyuType.MPS, Collections.unmodifiableList(Arrays.asList(JanPai.MAN_1,
                                                                           JanPai.MAN_4,
                                                                           JanPai.MAN_7,
                                                                           JanPai.PIN_2,
                                                                           JanPai.PIN_5,
                                                                           JanPai.PIN_8,
                                                                           JanPai.SOU_3,
                                                                           JanPai.SOU_6,
                                                                           JanPai.SOU_9)));}
        {put(KumiairyuType.MSP, Collections.unmodifiableList(Arrays.asList(JanPai.MAN_1,
                                                                           JanPai.MAN_4,
                                                                           JanPai.MAN_7,
                                                                           JanPai.SOU_2,
                                                                           JanPai.SOU_5,
                                                                           JanPai.SOU_8,
                                                                           JanPai.PIN_3,
                                                                           JanPai.PIN_6,
                                                                           JanPai.PIN_9)));}
        {put(KumiairyuType.PSM, Collections.unmodifiableList(Arrays.asList(JanPai.PIN_1,
                                                                           JanPai.PIN_4,
                                                                           JanPai.PIN_7,
                                                                           JanPai.SOU_2,
                                                                           JanPai.SOU_5,
                                                                           JanPai.SOU_8,
                                                                           JanPai.MAN_3,
                                                                           JanPai.MAN_6,
                                                                           JanPai.MAN_9)));}
        {put(KumiairyuType.PMS, Collections.unmodifiableList(Arrays.asList(JanPai.PIN_1,
                                                                           JanPai.PIN_4,
                                                                           JanPai.PIN_7,
                                                                           JanPai.MAN_2,
                                                                           JanPai.MAN_5,
                                                                           JanPai.MAN_8,
                                                                           JanPai.SOU_3,
                                                                           JanPai.SOU_6,
                                                                           JanPai.SOU_9)));}
        {put(KumiairyuType.SMP, Collections.unmodifiableList(Arrays.asList(JanPai.SOU_1,
                                                                           JanPai.SOU_4,
                                                                           JanPai.SOU_7,
                                                                           JanPai.MAN_2,
                                                                           JanPai.MAN_5,
                                                                           JanPai.MAN_8,
                                                                           JanPai.PIN_3,
                                                                           JanPai.PIN_6,
                                                                           JanPai.PIN_9)));}
        {put(KumiairyuType.SPM, Collections.unmodifiableList(Arrays.asList(JanPai.SOU_1,
                                                                           JanPai.SOU_4,
                                                                           JanPai.SOU_7,
                                                                           JanPai.PIN_2,
                                                                           JanPai.PIN_5,
                                                                           JanPai.PIN_8,
                                                                           JanPai.MAN_3,
                                                                           JanPai.MAN_6,
                                                                           JanPai.MAN_9)));}
    };
    
}

