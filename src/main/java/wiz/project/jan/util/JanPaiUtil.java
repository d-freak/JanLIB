/**
 * JanPaiUtil.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wiz.project.jan.JanPai;
import wiz.project.jan.Wind;



/**
 * 雀牌ユーティリティ
 */
public final class JanPaiUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private JanPaiUtil() {}
    
    
    
    /**
     * 雀牌を追加
     * 
     * @param source 追加元の牌Map。
     * @param key 追加する牌。
     * @param value 追加枚数。
     */
    public static void addJanPai(final Map<JanPai, Integer> source, final JanPai key, final int value) {
        if (source == null) {
            throw new NullPointerException("Source map is null.");
        }
        if (key == null) {
            throw new NullPointerException("Target pai is null.");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Invalid value - " + value);
        }
        
        int count = 0;
        if (source.containsKey(key)) {
            count = source.get(key);
        }
        
        // 4枚以上になる場合でもチェックしない
        source.put(key, count + value);
    }
    
    /**
     * 存在しない牌を削除 (各種判定処理の高速化用)
     * 
     * @param source 削除元の牌Map。
     */
    public static void cleanJanPaiMap(final Map<JanPai, Integer> source) {
        if (source == null) {
            throw new NullPointerException("Source map is null.");
        }
        
        final List<JanPai> targetList = new ArrayList<JanPai>();
        for (final Map.Entry<JanPai, Integer> entry : source.entrySet()) {
            if (entry.getValue() <= 0) {
                targetList.add(entry.getKey());
            }
        }
        for (final JanPai target : targetList) {
            source.remove(target);
        }
    }
    
    /**
     * 牌リストを変換
     * 
     * @param sourceList 変換元リスト。
     * @return 変換結果。
     */
    public static Map<JanPai, Integer> convertJanPaiList(final List<JanPai> sourceList) {
        final Map<JanPai, Integer> result = new TreeMap<JanPai, Integer>();
        for (final JanPai pai : sourceList) {
            addJanPai(result, pai, 1);
        }
        return result;
    }
    
    /**
     * 牌マップを変換
     * 
     * @param source 変換元。
     * @return 変換結果。
     */
    public static List<JanPai> convertJanPaiMap(final Map<JanPai, Integer> source) {
        final List<JanPai> resultList = new ArrayList<JanPai>();
        for (final Map.Entry<JanPai, Integer> entry : source.entrySet()) {
            final JanPai pai = entry.getKey();
            final int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                resultList.add(pai);
            }
        }
        return resultList;
    }
    
    /**
     * 全牌リストを生成
     * 
     * @return 全牌リスト。34種×4牌。
     */
    public static List<JanPai> createAllJanPaiList() {
        final List<JanPai> resultList = new ArrayList<JanPai>();
        for (final JanPai pai : JanPai.values()) {
            for (int i = 0; i < 4; i++) {
                resultList.add(pai);
            }
        }
        return resultList;
    }
    
    /**
     * 全牌マップを生成
     * 
     * @return 全牌マップ。34種×4牌。
     */
    public static Map<JanPai, Integer> createAllJanPaiMap() {
        final Map<JanPai, Integer> result = new TreeMap<JanPai, Integer>();
        for (final JanPai pai : JanPai.values()) {
            result.put(pai, 4);
        }
        return result;
    }
    
    /**
     * 山牌を生成
     * 
     * @return 山牌マップ。
     */
    public static Map<Wind, List<JanPai>> createDeck() {
        final List<JanPai> paiList = createAllJanPaiList();
        Collections.shuffle(paiList);
        
        final Wind[] windList = Wind.values();
        final int deckSize = paiList.size() / windList.length;
        final Map<Wind, List<JanPai>> result = new TreeMap<Wind, List<JanPai>>();
        int deckCount = 0;
        for (final Wind wind : windList) {
            final List<JanPai> deck = new ArrayList<JanPai>();
            for (int j = 0; j < deckSize; j++) {
                final int index = (deckSize * deckCount) + j;
                deck.add(paiList.get(index));
            }
            result.put(wind, deck);
            deckCount++;
        }
        return result;
    }
    
    /**
     * 総枚数をカウント
     * 
     * @param source カウント元。
     * @return 総枚数。
     */
    public static int getJanPaiTotalCount(final Map<JanPai, Integer> source) {
        int total = 0;
        for (final int count : source.values()) {
            total += count;
        }
        return total;
    }
    
    /**
     * 順子か
     * 
     * @param x 先頭の牌。
     * @param y 中央の牌。
     * @param z 末尾の牌。
     * @return 判定結果。
     */
    public static boolean isShunTsu(final JanPai x, final JanPai y, final JanPai z) {
        if (x == null || y == null || z == null) {
            throw new NullPointerException("Source jan pai is null - " + x + ", " + y + ", " + z);
        }
        if (x.isJi() || y.isJi() || z.isJi()) {
            return false;
        }
        return x.getNext() == y && y.getNext() == z;
    }
    
    /**
     * 雀牌を削除
     * 
     * @param source 削除元の牌Map。
     * @param key 削除する牌。
     * @param value 削除枚数。
     */
    public static void removeJanPai(final Map<JanPai, Integer> source, final JanPai key, final int value) {
        if (source == null) {
            throw new NullPointerException("Source map is null.");
        }
        if (key == null) {
            throw new NullPointerException("Target pai is null.");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Invalid value - " + value);
        }
        
        final int count = source.get(key);
        if (count < value) {
            throw new IllegalArgumentException("Failure - " + count + " : " + value);
        }
        
        if (count == value) {
            // キーごと削除
            source.remove(key);
        }
        else {
            source.put(key, count - value);
        }
    }
    
    
    
    /**
     * ヤオ九牌リスト
     */
    public static final List<JanPai> YAO_LIST =
        Collections.unmodifiableList(Arrays.asList(JanPai.MAN_1,
                                                   JanPai.MAN_9,
                                                   JanPai.PIN_1,
                                                   JanPai.PIN_9,
                                                   JanPai.SOU_1,
                                                   JanPai.SOU_9,
                                                   JanPai.TON,
                                                   JanPai.NAN,
                                                   JanPai.SHA,
                                                   JanPai.PEI,
                                                   JanPai.HAKU,
                                                   JanPai.HATU,
                                                   JanPai.CHUN));
    
    /**
     * 字牌リスト
     */
    public static final List<JanPai> JI_LIST =
        Collections.unmodifiableList(Arrays.asList(JanPai.TON,
                                                   JanPai.NAN,
                                                   JanPai.SHA,
                                                   JanPai.PEI,
                                                   JanPai.HAKU,
                                                   JanPai.HATU,
                                                   JanPai.CHUN));
    
}

