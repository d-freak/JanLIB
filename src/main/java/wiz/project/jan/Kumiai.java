/**
 * Kumiai.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;



/**
 * 組合竜
 */
public class Kumiai {
    
    /**
     * コンストラクタ利用禁止
     */
    private Kumiai() {}
    
    
    
    /**
     * 組合竜か
     */
    public static boolean isKumiai(final List<JanPai> paiList) {
        for (final List<JanPai> entry : _kumiaiList) {
            if (paiList.containsAll(entry)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 組合竜を削除 (未実装)
     */
    public static void removeKumiai(final Map<JanPai, Integer> source) {
    }
    
    
    
    /**
     * 組合竜リスト
     */
    private static final List<List<JanPai>> _kumiaiList = (Collections.unmodifiableList(Arrays.asList(
        Collections.unmodifiableList(Arrays.asList(JanPai.MAN_1,
                                                   JanPai.MAN_4,
                                                   JanPai.MAN_7,
                                                   JanPai.PIN_2,
                                                   JanPai.PIN_5,
                                                   JanPai.PIN_8,
                                                   JanPai.SOU_3,
                                                   JanPai.SOU_6,
                                                   JanPai.SOU_9)),
        Collections.unmodifiableList(Arrays.asList(JanPai.MAN_1,
                                                   JanPai.MAN_4,
                                                   JanPai.MAN_7,
                                                   JanPai.SOU_2,
                                                   JanPai.SOU_5,
                                                   JanPai.SOU_8,
                                                   JanPai.PIN_3,
                                                   JanPai.PIN_6,
                                                   JanPai.PIN_9)),
        Collections.unmodifiableList(Arrays.asList(JanPai.PIN_1,
                                                   JanPai.PIN_4,
                                                   JanPai.PIN_7,
                                                   JanPai.SOU_2,
                                                   JanPai.SOU_5,
                                                   JanPai.SOU_8,
                                                   JanPai.MAN_3,
                                                   JanPai.MAN_6,
                                                   JanPai.MAN_9)),
        Collections.unmodifiableList(Arrays.asList(JanPai.PIN_1,
                                                   JanPai.PIN_4,
                                                   JanPai.PIN_7,
                                                   JanPai.MAN_2,
                                                   JanPai.MAN_5,
                                                   JanPai.MAN_8,
                                                   JanPai.SOU_3,
                                                   JanPai.SOU_6,
                                                   JanPai.SOU_9)),
        Collections.unmodifiableList(Arrays.asList(JanPai.SOU_1,
                                                   JanPai.SOU_4,
                                                   JanPai.SOU_7,
                                                   JanPai.MAN_2,
                                                   JanPai.MAN_5,
                                                   JanPai.MAN_8,
                                                   JanPai.PIN_3,
                                                   JanPai.PIN_6,
                                                   JanPai.PIN_9)),
        Collections.unmodifiableList(Arrays.asList(JanPai.SOU_1,
                                                   JanPai.SOU_4,
                                                   JanPai.SOU_7,
                                                   JanPai.PIN_2,
                                                   JanPai.PIN_5,
                                                   JanPai.PIN_8,
                                                   JanPai.MAN_3,
                                                   JanPai.MAN_6,
                                                   JanPai.MAN_9)))));
}

