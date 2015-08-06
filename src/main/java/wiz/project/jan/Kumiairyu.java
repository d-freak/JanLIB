/**
 * Kumiairyu.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan;

import java.util.ArrayList;
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
     * 組合竜の順子リストを取得
     * 
     * @param kumiairyuType 組合竜タイプ。
     * @return 組合竜の順子リスト。
     */
    public static List<MenTsu> getKumiairyuShunTsuList(final KumiairyuType kumiairyuType) {
        final List<MenTsu> shuntsuList = new ArrayList<MenTsu>();
        final List<JanPai> paiList = new ArrayList<JanPai>();
        int count = 0;
        
        for (final JanPai pai : kumiairyuType.getKumiaiPaiList()) {
            paiList.add(pai);
            count++;
            
            if (count % 3 == 0) {
                MenTsu shuntsu = new MenTsu(paiList, MenTsuType.STANDARD_SHUN_TSU);
                shuntsuList.add(shuntsu);
                paiList.clear();
            }
        }
        return shuntsuList;
    }
    
    /**
     * 組合竜タイプを取得
     * 
     * @param paiList 手牌。
     * @return 組合竜タイプ。
     */
    public static KumiairyuType getKumiairyuType(final List<JanPai> paiList) {
        for (final KumiairyuType kumiairyuType : KumiairyuType.values()) {
            if (paiList.containsAll(kumiairyuType.getKumiaiPaiList())) {
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
        
        for (final JanPai entry : kumiairyuType.getKumiaiPaiList()) {
            if (paiList.contains(entry)) {
                JanPaiUtil.removeJanPai(source, entry, 1);
            }
        }
    }
    
}

