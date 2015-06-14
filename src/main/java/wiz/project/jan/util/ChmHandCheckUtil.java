/**
 * ChmHandCheckUtil.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wiz.project.jan.ChmCompleteInfo;
import wiz.project.jan.ChmYaku;
import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.Kumiairyu;
import wiz.project.jan.KumiairyuType;
import wiz.project.jan.TenpaiPattern;
import wiz.project.jan.Wind;



/**
 * 手牌確認ユーティリティ (中国麻雀)
 */
public final class ChmHandCheckUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private ChmHandCheckUtil() {}
    
    
    
    /**
     * 待ち牌を取得 (中国麻雀)
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
     * @param hand 手牌。
     * @param completePai 和了牌。
     * @param playerWind 自風。
     * @param fieldWind 場風。
     * @return 和了情報。
     */
    public static ChmCompleteInfo getCompleteInfo(final Hand hand, final CompleteJanPai completePai, final Wind playerWind, final Wind fieldWind) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        if (completePai == null) {
            throw new NullPointerException("Complete jan pai is null.");
        }
        if (playerWind == null) {
            throw new NullPointerException("Player wind is null.");
        }
        if (fieldWind == null) {
            throw new NullPointerException("Field wind is null.");
        }
        
        final Map<JanPai, Integer> allPaiMap = hand.getAllJanPaiMap();
        JanPaiUtil.addJanPai(allPaiMap, completePai.getJanPai(), 1);
        JanPaiUtil.cleanJanPaiMap(allPaiMap);
        
        List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        
        if (isCompleteZenhukou(allPaiMap)) {
            if (ChmYakuCheckUtil.isGreaterHonorsAndKnittedTiles(allPaiMap)) {
                yakuList.add(ChmYaku.GREATER_HONORS_AND_KNITTED_TILES);
            }
            else {
                yakuList.add(ChmYaku.LESSER_HONORS_AND_KNITTED_TILES);
                
                final ArrayList<JanPai> paiList = new ArrayList<JanPai>(allPaiMap.keySet());
                
                if (Kumiairyu.isKumiairyu(paiList)) {
                    yakuList.add(ChmYaku.KNITTED_STRAIGHT);
                }
            }
            addCompleteJanPaiYaku(yakuList, completePai);
            
            return new ChmCompleteInfo(yakuList, completePai.getType());
        }
        
        if (isCompleteKokushi(allPaiMap)) {
            yakuList.add(ChmYaku.THIRTEEN_ORPHANS);
            addCompleteJanPaiYaku(yakuList, completePai);
            
            return new ChmCompleteInfo(yakuList, completePai.getType());
        }
        
        if (isCompleteNanatsui(allPaiMap) && hand.getFixedMenTsuCount() == 0) {
            if (ChmYakuCheckUtil.isSevenShiftedPairs(allPaiMap)) {
                yakuList.add(ChmYaku.SEVEN_SHIFTED_PAIRS);
            }
            else {
                yakuList.add(ChmYaku.SEVEN_PAIRS);
            }
        }
        else {
            // TODO 数牌系列、刻子系列の役
        }
        
        if (ChmYakuCheckUtil.isAllGreen(allPaiMap)) {
            yakuList.add(ChmYaku.ALL_GREEN);
        }
        
        if (ChmYakuCheckUtil.isUpperTiles(allPaiMap)) {
            yakuList.add(ChmYaku.UPPER_TILES);
        }
        else if (ChmYakuCheckUtil.isMiddleTiles(allPaiMap)) {
            yakuList.add(ChmYaku.MIDDLE_TILES);
        }
        else if (ChmYakuCheckUtil.isLowerTiles(allPaiMap)) {
            yakuList.add(ChmYaku.LOWER_TILES);
        }
        else if (ChmYakuCheckUtil.isUpperFour(allPaiMap)) {
            yakuList.add(ChmYaku.UPPER_FOUR);
        }
        else if (ChmYakuCheckUtil.isLowerFour(allPaiMap)) {
            yakuList.add(ChmYaku.LOWER_FOUR);
        }
        else if (ChmYakuCheckUtil.isAllSimples(allPaiMap)) {
            yakuList.add(ChmYaku.ALL_SIMPLES);
        }
        
        if (ChmYakuCheckUtil.isBigFourWinds(hand, allPaiMap)) {
            yakuList.add(ChmYaku.BIG_FOUR_WINDS);
        }
        else if (ChmYakuCheckUtil.isBigThreeDragons(hand, allPaiMap)) {
            yakuList.add(ChmYaku.BIG_THREE_DRAGONS);
            
            if (ChmYakuCheckUtil.isPrevalentWind(hand, allPaiMap, fieldWind)) {
                yakuList.add(ChmYaku.PREVALENT_WIND);
            }
            
            if (ChmYakuCheckUtil.isSeatWind(hand, allPaiMap, playerWind)) {
                yakuList.add(ChmYaku.SEAT_WIND);
            }
        }
        else if (ChmYakuCheckUtil.isBigThreeWinds(hand, allPaiMap)) {
            yakuList.add(ChmYaku.BIG_THREE_WINDS);
            
            if (ChmYakuCheckUtil.isDragonPung(hand, allPaiMap)) {
                yakuList.add(ChmYaku.DRAGON_PUNG);
            }
            
            if (ChmYakuCheckUtil.isPrevalentWind(hand, allPaiMap, fieldWind)) {
                yakuList.add(ChmYaku.PREVALENT_WIND);
            }
            
            if (ChmYakuCheckUtil.isSeatWind(hand, allPaiMap, playerWind)) {
                yakuList.add(ChmYaku.SEAT_WIND);
            }
        }
        else if (ChmYakuCheckUtil.isTwoDragonPungs(hand, allPaiMap)) {
            yakuList.add(ChmYaku.TWO_DRAGON_PUNGS);
            
            if (ChmYakuCheckUtil.isPrevalentWind(hand, allPaiMap, fieldWind)) {
                yakuList.add(ChmYaku.PREVALENT_WIND);
            }
            
            if (ChmYakuCheckUtil.isSeatWind(hand, allPaiMap, playerWind)) {
                yakuList.add(ChmYaku.SEAT_WIND);
            }
        }
        else {
            if (ChmYakuCheckUtil.isDragonPung(hand, allPaiMap)) {
                yakuList.add(ChmYaku.DRAGON_PUNG);
            }
            
            if (ChmYakuCheckUtil.isPrevalentWind(hand, allPaiMap, fieldWind)) {
                yakuList.add(ChmYaku.PREVALENT_WIND);
            }
            
            if (ChmYakuCheckUtil.isSeatWind(hand, allPaiMap, playerWind)) {
                yakuList.add(ChmYaku.SEAT_WIND);
            }
        }
        
        if (ChmYakuCheckUtil.isAllTerminals(allPaiMap)) {
            yakuList.add(ChmYaku.ALL_TERMINALS);
        }
        else if (ChmYakuCheckUtil.isAllHonors(allPaiMap)) {
            yakuList.add(ChmYaku.ALL_HONORS);
        }
        else if (ChmYakuCheckUtil.isAllTerminalsAndHonors(allPaiMap)) {
            yakuList.add(ChmYaku.ALL_TERMINALS_AND_HONORS);
        }
        
        if (ChmYakuCheckUtil.isFullFlush(allPaiMap)) {
            if (!yakuList.contains(ChmYaku.SEVEN_SHIFTED_PAIRS)) {
                yakuList.add(ChmYaku.FULL_FLUSH);
            }
        }
        else if (ChmYakuCheckUtil.isReversibleTiles(allPaiMap)) {
            yakuList.add(ChmYaku.REVERSIBLE_TILES);
        }
        else if (ChmYakuCheckUtil.isHalfFlush(allPaiMap)) {
            yakuList.add(ChmYaku.HALF_FLUSH);
        }
        else if (ChmYakuCheckUtil.isAllTypes(allPaiMap)) {
            yakuList.add(ChmYaku.ALL_TYPES);
        }
        else if (ChmYakuCheckUtil.isOneVoidedSuit(allPaiMap)) {
            yakuList.add(ChmYaku.ONE_VOIDED_SUIT);
        }
        
        for (int count = 0; count < ChmYakuCheckUtil.getTileHogCount(hand, allPaiMap); count++) {
            yakuList.add(ChmYaku.TILE_HOG);
        }
        addCompleteJanPaiYaku(yakuList, completePai);
        
        return new ChmCompleteInfo(yakuList, completePai.getType());
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
     * 和了判定 (中国麻雀)
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
            
            if (isCompleteZenhukou(hand)) {
                return true;
            }
            return false;
        }
        
        for (final Map<JanPai, Integer> pattern : excludeHeadPattern) {
            if (pattern.isEmpty()) {
            // 裸単騎状態で和了
                return true;
            }
            
            final Map<JanPai, Integer> copy1 = deepCopyMap(pattern);
            
            // 順子優先パターン
            removeShunTsu(copy1);
            removeKohTsu(copy1);
            if (copy1.isEmpty()) {
                return true;
            }
            
            final Map<JanPai, Integer> copy2 = deepCopyMap(pattern);
            
            // 刻子優先パターン
            removeKohTsu(copy2);
            removeShunTsu(copy2);
            if (copy2.isEmpty()) {
                return true;
            }
            
            final List<JanPai> paiList = new ArrayList<JanPai>(pattern.keySet());
            final KumiairyuType kumiairyuType = Kumiairyu.getKumiairyuType(paiList);
            
            if (kumiairyuType == KumiairyuType.NONE) {
                continue;
            }
            
            // 組合竜優先パターン
            Kumiairyu.removeKumiairyu(pattern, kumiairyuType);
            
            final Map<JanPai, Integer> copy3 = deepCopyMap(pattern);
            
            removeShunTsu(copy3);
            if (copy3.isEmpty()) {
                return true;
            }
            
            removeKohTsu(pattern);
            if (pattern.isEmpty()) {
                return true;
            }
        }
        
        // 七対は後で判定 (二盃口対策)
        if (isCompleteNanatsui(hand)) {
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
     * 役リストに和了牌の役を追加
     * 
     * @param yakuList 役リスト。
     * @param completePai 和了牌。
     */
    private static void addCompleteJanPaiYaku(final List<ChmYaku> yakuList, final CompleteJanPai completePai) {
        
        if (completePai.isLast()) {
            yakuList.add(ChmYaku.LAST_TILE);
        }
        
        switch (completePai.getType()) {
        case RON_MENZEN:
            if (isConcealedHand(yakuList)) {
                yakuList.add(ChmYaku.CONCEALED_HAND);
            }
            break;
        case RON_MENZEN_HO_TEI:
            yakuList.add(ChmYaku.LAST_TILE_CLAIM);
            if (isConcealedHand(yakuList)) {
                yakuList.add(ChmYaku.CONCEALED_HAND);
            }
            break;
        case RON_NOT_MENZEN_HO_TEI:
            yakuList.add(ChmYaku.LAST_TILE_CLAIM);
            break;
        case TSUMO_MENZEN:
            yakuList.add(ChmYaku.FULLY_CONCEALED);
            break;
        case TSUMO_NOT_MENZEN:
            yakuList.add(ChmYaku.SELF_DRAWN);
            break;
        case TSUMO_MENZEN_HAI_TEI:
            yakuList.add(ChmYaku.LAST_TILE_DRAW);
            yakuList.add(ChmYaku.FULLY_CONCEALED);
            break;
        case TSUMO_NOT_MENZEN_HAI_TEI:
            yakuList.add(ChmYaku.LAST_TILE_DRAW);
            break;
        case TSUMO_MENZEN_RIN_SYAN:
            yakuList.add(ChmYaku.OUT_WITH_REPLACEMENT_TILE);
            yakuList.add(ChmYaku.FULLY_CONCEALED);
            break;
        case TSUMO_NOT_MENZEN_RIN_SYAN:
            yakuList.add(ChmYaku.OUT_WITH_REPLACEMENT_TILE);
            break;
        default:
            break;
        }
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
     * マップをディープコピー
     * 
     * @param source 複製元。
     * @return 複製結果。
     */
    private static <S, T> Map<S, T> deepCopyMap(final Map<S, T> source) {
        return new TreeMap<S, T>(source);
    }
    
    /**
     * 國士無双(=十三ヤオ)和了か
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
     * 七対和了か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    private static boolean isCompleteNanatsui(final Map<JanPai, Integer> hand) {
        int typeCount = 0;
        for (final Map.Entry<JanPai, Integer> entry : hand.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                return false;
            }
            typeCount += entry.getValue() / 2;
        }
        return typeCount == 7;
    }
    
    /**
     * 全不靠和了か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    private static boolean isCompleteZenhukou(final Map<JanPai, Integer> hand) {
        final Map<JanPai, Integer> copyHand = deepCopyMap(hand);
        removeJi(copyHand);
        final List<JanPai> paiList = new ArrayList<JanPai>(copyHand.keySet());
        switch (copyHand.size()) {
        case 7:
            for (final JanPai pai1 : JanPai.values()) {
                if (pai1.isJi() || paiList.contains(pai1)) {
                    // 組合竜の判定のため、字牌や既にある牌は判定せず次へ
                    continue;
                }
                // 既に手牌で4枚使っていても判定自体は行う
                final List<JanPai> pattern1 = deepCopyList(paiList);
                pattern1.add(pai1);
                for (final JanPai pai2 : JanPai.values()) {
                    if (pai2.isJi() || paiList.contains(pai2)) {
                        // 組合竜の判定のため、字牌や既にある牌は判定せず次へ
                        continue;
                    }
                    // 既に手牌で4枚使っていても判定自体は行う
                    final List<JanPai> pattern2 = deepCopyList(pattern1);
                    pattern2.add(pai2);
                    if (Kumiairyu.isKumiairyu(pattern2)) {
                        return true;
                    }
                }
            }
            return false;
        case 8:
            for (final JanPai pai : JanPai.values()) {
                if (pai.isJi() || paiList.contains(pai)) {
                    // 組合竜の判定のため、字牌や既にある牌は判定せず次へ
                    continue;
                }
                // 既に手牌で4枚使っていても判定自体は行う
                final List<JanPai> pattern = deepCopyList(paiList);
                pattern.add(pai);
                if (Kumiairyu.isKumiairyu(pattern)) {
                    return true;
                }
            }
            return false;
        case 9:
            return Kumiairyu.isKumiairyu(paiList);
        default:
            return false;
        }
    }
    
    /**
     * 門前清か
     * 
     * @param yakuList 役リスト。
     * @return 判定結果。
     */
    private static boolean isConcealedHand(final List<ChmYaku> yakuList) {
        for (final ChmYaku yaku : yakuList) {
            if (yaku.isMenZenOnly()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 字牌を1枚ずつ削除
     * 
     * @param source 削除元の牌マップ。
     */
    private static void removeJi(final Map<JanPai, Integer> source) {
        final List<JanPai> paiList = new ArrayList<JanPai>(source.keySet());
        for (final JanPai entry : paiList) {
            if (JanPaiUtil.JI_LIST.contains(entry)) {
                JanPaiUtil.removeJanPai(source, entry, 1);
            }
        }
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

