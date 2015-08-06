/**
 * KumiairyuType.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



/**
 * 組合竜タイプ
 */
public enum KumiairyuType {
    
    MPS(Collections.unmodifiableList(Arrays.asList(JanPai.MAN_1,
                                                   JanPai.MAN_4,
                                                   JanPai.MAN_7,
                                                   JanPai.PIN_2,
                                                   JanPai.PIN_5,
                                                   JanPai.PIN_8,
                                                   JanPai.SOU_3,
                                                   JanPai.SOU_6,
                                                   JanPai.SOU_9))),
    MSP(Collections.unmodifiableList(Arrays.asList(JanPai.MAN_1,
                                                   JanPai.MAN_4,
                                                   JanPai.MAN_7,
                                                   JanPai.SOU_2,
                                                   JanPai.SOU_5,
                                                   JanPai.SOU_8,
                                                   JanPai.PIN_3,
                                                   JanPai.PIN_6,
                                                   JanPai.PIN_9))),
    PSM(Collections.unmodifiableList(Arrays.asList(JanPai.PIN_1,
                                                   JanPai.PIN_4,
                                                   JanPai.PIN_7,
                                                   JanPai.SOU_2,
                                                   JanPai.SOU_5,
                                                   JanPai.SOU_8,
                                                   JanPai.MAN_3,
                                                   JanPai.MAN_6,
                                                   JanPai.MAN_9))),
    PMS(Collections.unmodifiableList(Arrays.asList(JanPai.PIN_1,
                                                   JanPai.PIN_4,
                                                   JanPai.PIN_7,
                                                   JanPai.MAN_2,
                                                   JanPai.MAN_5,
                                                   JanPai.MAN_8,
                                                   JanPai.SOU_3,
                                                   JanPai.SOU_6,
                                                   JanPai.SOU_9))),
    SMP(Collections.unmodifiableList(Arrays.asList(JanPai.SOU_1,
                                                   JanPai.SOU_4,
                                                   JanPai.SOU_7,
                                                   JanPai.MAN_2,
                                                   JanPai.MAN_5,
                                                   JanPai.MAN_8,
                                                   JanPai.PIN_3,
                                                   JanPai.PIN_6,
                                                   JanPai.PIN_9))),
    SPM(Collections.unmodifiableList(Arrays.asList(JanPai.SOU_1,
                                                   JanPai.SOU_4,
                                                   JanPai.SOU_7,
                                                   JanPai.PIN_2,
                                                   JanPai.PIN_5,
                                                   JanPai.PIN_8,
                                                   JanPai.MAN_3,
                                                   JanPai.MAN_6,
                                                   JanPai.MAN_9))),
    NONE(new ArrayList<JanPai>());
    
    
    
    /**
     * コンストラクタ
     * 
     * @param paiList 牌リスト。
     */
    private KumiairyuType(final List<JanPai> paiList) {
        _kumiaiPaiList = paiList;
    }
    
    
    
    /**
     * 組合竜の牌リストを取得
     * 
     * @return 組合竜の牌リスト。
     */
    public List<JanPai> getKumiaiPaiList() {
        return deepCopyList(_kumiaiPaiList);
    }
    
    
    
    /**
     * リストをディープコピー
     * 
     * @param source 複製元。
     * @return 複製結果。
     */
    private static <S> List<S> deepCopyList(final List<S> source) {
        return new ArrayList<S>(source);
    }
    
    
    
    /**
     * 組合竜の牌リスト
     */
    private final List<JanPai> _kumiaiPaiList;
    
}

