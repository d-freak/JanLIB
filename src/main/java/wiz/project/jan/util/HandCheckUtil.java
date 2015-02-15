/**
 * HandCheckUtil.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wiz.project.jan.CompleteInfo;
import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.PlayerStatus;
import wiz.project.jan.TenpaiPattern;
import wiz.project.jan.Wind;



/**
 * 手牌確認ユーティリティ
 */
public final class HandCheckUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private HandCheckUtil() {}
    
    
    
    /**
     * 待ち牌を取得
     * 
     * @param hand 手牌。
     * @return 待ち牌リスト。
     */
    public static List<JanPai> getCompletableJanPaiList(final Map<JanPai, Integer> hand) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        
        final List<JanPai> resultList = new ArrayList<JanPai>();
        for (final JanPai pai : JanPai.values()) {
            // 既に手牌で4枚使っていても判定自体は行う
            final Map<JanPai, Integer> pattern = deepCopyMap(hand);
            JanPaiUtil.addJanPai(pattern, pai, 1);
            if (isComplete(pattern)) {
                resultList.add(pai);
            }
        }
        return resultList;
    }
    
    /**
     * 和了情報を取得
     * 
     * @param player プレイヤー。
     * @param completePai 和了牌。
     * @param fieldWind 場風。
     * @return 和了情報。
     */
    public static CompleteInfo getCompleteInfo(final PlayerStatus player, final CompleteJanPai completePai, final Wind fieldWind) {
        if (player == null) {
            throw new NullPointerException("Player is null.");
        }
        if (completePai == null) {
            throw new NullPointerException("Complete jan pai is null.");
        }
        if (fieldWind == null) {
            throw new NullPointerException("Field wind is null.");
        }
        
        final Hand hand = player.getHand();
        final Map<JanPai, Integer> menZenMap = hand.getMenZenMap();
        JanPaiUtil.cleanJanPaiMap(menZenMap);
        
        // isComplete() で和了判定する時点で既にコストが高い。
        // 和了済みであることを前提として役判定に入った方が良いかも。
        if (!isComplete(hand.getMenZenMap())) {
            throw new IllegalArgumentException("Player is not complete.");
        }
        
        // TODO 以降未実装
        throw new UnsupportedOperationException();
    }
    
    /**
     * 雀頭除外パターンを取得
     * 
     * @param hand 手牌。
     * @return 雀頭候補の除外パターン。
     */
    public static List<Map<JanPai, Integer>> getExcludeHeadPattern(final Map<JanPai, Integer> hand) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        
        final List<Map<JanPai, Integer>> resultList = new ArrayList<Map<JanPai, Integer>>();
        for (final Map.Entry<JanPai, Integer> entry : hand.entrySet()) {
            final int count = entry.getValue();
            if (count >= 2) {
                final Map<JanPai, Integer> pattern = deepCopyMap(hand);
                JanPaiUtil.removeJanPai(pattern, entry.getKey(), 2);
                resultList.add(pattern);
            }
        }
        return resultList;
    }
    
    /**
     * 期待枚数を取得
     * 
     * @param hand 手牌。
     * @param completableList 待ち牌リスト。
     * @return 期待枚数。
     */
    public static Map<JanPai, Integer> getExpectation(final Hand hand, final List<JanPai> completableList) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        if (completableList == null) {
            throw new NullPointerException("Completable list is null.");
        }
        
        final Map<JanPai, Integer> source = hand.getAllJanPaiMap();
        final Map<JanPai, Integer> expectation = new TreeMap<JanPai, Integer>();
        for (final JanPai pai : completableList) {
            final int count = source.get(pai);
            expectation.put(pai, 4 - count);
        }
        return expectation;
    }
    
    /**
     * 雀頭候補リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 雀頭候補リスト。
     */
    public static List<List<JanPai>> getHeadList(final Map<JanPai, Integer> source) {
        final List<List<JanPai>> resultList = new ArrayList<List<JanPai>>();
        for (final JanPai pai : JanPai.values()) {
            if (!source.containsKey(pai)) {
                continue;
            }
            final int count = source.get(pai);
            if (count >= 2) {
                JanPaiUtil.removeJanPai(source, pai, 2);
                final List<JanPai> head = new ArrayList<JanPai>();
                for (int i = 0; i < 2; i++) {
                    head.add(pai);
                }
                resultList.add(head);
            }
        }
        return resultList;
    }
    
    /**
     * 聴牌パターンリストを取得
     * 
     * @param hand 手牌。
     * @return 聴牌パターンリスト。
     */
    public static List<TenpaiPattern> getTenpaiPatternList(final Hand hand) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        
        final List<TenpaiPattern> resultList = new ArrayList<TenpaiPattern>();
        final Map<JanPai, Integer> menZen = hand.getMenZenMap();
        JanPaiUtil.cleanJanPaiMap(menZen);
        for (final JanPai pai : menZen.keySet()) {
            final Map<JanPai, Integer> pattern = deepCopyMap(menZen);
            JanPaiUtil.removeJanPai(pattern, pai, 1);
            final List<JanPai> completableList = getCompletableJanPaiList(pattern);
            if (!completableList.isEmpty()) {
                final Map<JanPai, Integer> expectation = getExpectation(hand, completableList);
                final int totalCount = JanPaiUtil.getJanPaiTotalCount(expectation);
                if (totalCount != 0) {
                    // 待ちが枯れていなければ追加
                    resultList.add(new TenpaiPattern(pai, completableList, expectation));
                }
            }
        }
        return resultList;
    }
    
    /**
     * 和了判定
     *
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isComplete(final Map<JanPai, Integer> hand) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        
        final List<Map<JanPai, Integer>> excludeHeadPattern = getExcludeHeadPattern(hand);
        if (excludeHeadPattern.isEmpty()) {
            // 雀頭候補が存在しない
            return false;
        }
        
        for (final Map<JanPai, Integer> pattern : excludeHeadPattern) {
            if (pattern.isEmpty()) {
            // 裸単騎状態で和了
                return true;
            }
            
            final Map<JanPai, Integer> copy = deepCopyMap(pattern);
            
            // 順子優先パターン
            removeShunTsu(pattern);
            removeKohTsu(pattern);
            if (pattern.isEmpty()) {
                return true;
            }
            
            // 刻子優先パターン
            removeKohTsu(copy);
            removeShunTsu(copy);
            if (copy.isEmpty()) {
                return true;
            }
        }
        
        // 七対子は後で判定 (二盃口対策)
        if (isCompleteChiToi(hand)) {
            return true;
        }
        if (isCompleteKokushi(hand)) {
            return true;
        }
        return false;
    }
    
    /**
     * 聴牌判定
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isTenpai(final Map<JanPai, Integer> hand) {
        return !getCompletableJanPaiList(hand).isEmpty();
    }
    
    
    
    /**
     * マップをディープコピー
     * 
     * @param source 複製元。
     * @return 複製結果。
     */
    private static <S, T> Map<S, T> deepCopyMap(final Map<S, T> source) {
        return new TreeMap<S, T>(source);
    }
    
    /**
     * 七対子和了か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    private static boolean isCompleteChiToi(final Map<JanPai, Integer> hand) {
        int typeCount = 0;
        for (final Map.Entry<JanPai, Integer> entry : hand.entrySet()) {
            if (entry.getValue() != 2) {
                return false;
            }
            typeCount++;
        }
        return typeCount == 7;
    }
    
    /**
     * 國士無双和了か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    private static boolean isCompleteKokushi(final Map<JanPai, Integer> hand) {
        int headCount = 0;
        int typeCount = 0;
        for (final Map.Entry<JanPai, Integer> entry : hand.entrySet()) {
            if (!entry.getKey().isYao()) {
                return false;
            }
            switch (entry.getValue()) {
            case 1:
                break;
            case 2:
                headCount++;
                break;
            default:
                return false;
            }
            typeCount++;
        }
        return headCount == 1 && typeCount == 13;
    }
    
    /**
     * 刻子を削除
     * 
     * @param source 削除元の牌マップ。
     */
    private static void removeKohTsu(final Map<JanPai, Integer> source) {
        final List<JanPai> kohTsu = new ArrayList<JanPai>();
        for (final Map.Entry<JanPai, Integer> entry : source.entrySet()) {
            if (entry.getValue() >= 3) {
                kohTsu.add(entry.getKey());
            }
        }
        for (final JanPai target : kohTsu) {
            JanPaiUtil.removeJanPai(source, target, 3);
        }
    }
    
    /**
     * 順子を削除
     * 
     * @param source 削除元の牌マップ。
     */
    private static void removeShunTsu(final Map<JanPai, Integer> source) {
        boolean foundShunTsu;
        do {
            foundShunTsu = false;
            final List<JanPai> paiList = new ArrayList<JanPai>(source.keySet());
            for (int i = 2; i < paiList.size(); i++) {
                final JanPai x = paiList.get(i - 2);
                final JanPai y = paiList.get(i - 1);
                final JanPai z = paiList.get(i);
                if (JanPaiUtil.isShunTsu(x, y, z)) {
                    JanPaiUtil.removeJanPai(source, x, 1);
                    JanPaiUtil.removeJanPai(source, y, 1);
                    JanPaiUtil.removeJanPai(source, z, 1);
                    
                    // 順子が見つかった場合、リスト再構築 (一盃口対策)
                    foundShunTsu = true;
                    break;
                }
            }
        }
        while (foundShunTsu && !source.isEmpty());
    }
    
}

