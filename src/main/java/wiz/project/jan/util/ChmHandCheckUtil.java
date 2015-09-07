/**
 * ChmHandCheckUtil.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wiz.project.jan.ChmCompleteInfo;
import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.CompletePattern;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.MenTsu;
import wiz.project.jan.MenTsuType;
import wiz.project.jan.TenpaiPattern;
import wiz.project.jan.Wind;
import wiz.project.jan.yaku.ChmYaku;
import wiz.project.jan.yaku.Kumiairyu;
import wiz.project.jan.yaku.KumiairyuType;



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
        final boolean isDouble = playerWind.equals(fieldWind);
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
            yakuList.addAll(ChmYakuCheckUtil.getCompleteJanPaiYaku(completePai));
            removeExcludeYaku(yakuList, isDouble);
            
            return new ChmCompleteInfo(yakuList, completePai.getType());
        }
        
        if (isCompleteKokushi(allPaiMap)) {
            yakuList.add(ChmYaku.THIRTEEN_ORPHANS);
            yakuList.addAll(ChmYakuCheckUtil.getCompleteJanPaiYaku(completePai));
            removeExcludeYaku(yakuList, isDouble);
            
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
            yakuList.addAll(getMenTsuYakuList(hand, completePai));
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
        
        if (ChmYakuCheckUtil.isAllSimples(allPaiMap)) {
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
            yakuList.add(ChmYaku.FULL_FLUSH);
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
        
        if (ChmYakuCheckUtil.isNoHonors(allPaiMap)) {
            yakuList.add(ChmYaku.NO_HONORS);
        }
        
        for (int count = 0; count < ChmYakuCheckUtil.getTileHogCount(hand, allPaiMap); count++) {
            yakuList.add(ChmYaku.TILE_HOG);
        }
        
        if (ChmYakuCheckUtil.isMeldedHand(hand, completePai)) {
            yakuList.add(ChmYaku.MELDED_HAND);
        }
        yakuList.addAll(ChmYakuCheckUtil.getCompleteJanPaiYaku(completePai));
        removeExcludeYaku(yakuList, isDouble);
        
        if (yakuList.isEmpty()) {
            yakuList.add(ChmYaku.CHICKEN_HAND);
        }
        return new ChmCompleteInfo(yakuList, completePai.getType());
    }
    
    /**
     * 和了パターンリストを取得
     * 
     * @param hand 手牌。
     * @param completePai 和了牌。
     * @return 和了パターンリスト。
     */
    public static List<CompletePattern> getCompletePatternList(final Hand hand, final CompleteJanPai completePai) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        if (completePai == null) {
            throw new NullPointerException("Complete jan pai is null.");
        }
        final Map<JanPai, Integer> handWithCompleteJanPai = hand.getCleanMenZenMap(completePai.getJanPai());
        final Map<JanPai, Map<JanPai, Integer>> excludeHeadPattern = getExcludeHeadPattern(handWithCompleteJanPai);
        final List<CompletePattern> resultList = new ArrayList<CompletePattern>();
        
        if (excludeHeadPattern.isEmpty()) {
            // 雀頭候補が存在しない
            return resultList;
        }
        
        for (final Map.Entry<JanPai, Map<JanPai, Integer>> entry : excludeHeadPattern.entrySet()) {
            final Map<JanPai, Integer> pattern = entry.getValue();
            final List<MenTsu> mentsuList = hand.getFixedMenTsuList();
            
            if (pattern.isEmpty()) {
                // 裸単騎状態で和了
                Collections.sort(mentsuList);
                resultList.add(new CompletePattern(entry.getKey(), mentsuList));
                continue;
            }
            
            final Map<JanPai, Integer> copy1 = deepCopyMap(pattern);
            List<MenTsu> mentsuList1 = deepCopyList(mentsuList);
            
            // 順子優先パターン
            mentsuList1.addAll(HandCreateUtil.getShunTsuList(copy1));
            mentsuList1.addAll(HandCreateUtil.getKohTsuList(copy1, completePai));
            if (copy1.isEmpty()) {
                Collections.sort(mentsuList1);
                resultList.add(new CompletePattern(entry.getKey(), mentsuList1));
                continue;
            }
            
            final Map<JanPai, Integer> copy2 = deepCopyMap(pattern);
            List<MenTsu> mentsuList2 = deepCopyList(mentsuList);
            
            // 刻子優先パターン
            mentsuList2.addAll(HandCreateUtil.getKohTsuList(copy2, completePai));
            mentsuList2.addAll(HandCreateUtil.getShunTsuList(copy2));
            if (copy2.isEmpty()) {
                Collections.sort(mentsuList2);
                resultList.add(new CompletePattern(entry.getKey(), mentsuList2));
                continue;
            }
            
            final List<JanPai> paiList = new ArrayList<JanPai>(pattern.keySet());
            final KumiairyuType kumiairyuType = Kumiairyu.getKumiairyuType(paiList);
            
            if (kumiairyuType == KumiairyuType.NONE) {
                continue;
            }
            
            // 組合竜優先パターン
            Kumiairyu.removeKumiairyu(pattern, kumiairyuType);
            mentsuList.addAll(Kumiairyu.getKumiairyuShunTsuList(kumiairyuType));
            
            final Map<JanPai, Integer> copy3 = deepCopyMap(pattern);
            List<MenTsu> mentsuList3 = deepCopyList(mentsuList);
            
            mentsuList3.addAll(HandCreateUtil.getShunTsuList(copy3));
            if (copy3.isEmpty()) {
                Collections.sort(mentsuList3);
                resultList.add(new CompletePattern(entry.getKey(), mentsuList3));
                continue;
            }
            
            mentsuList.addAll(HandCreateUtil.getKohTsuList(pattern, completePai));
            if (pattern.isEmpty()) {
                Collections.sort(mentsuList);
                resultList.add(new CompletePattern(entry.getKey(), mentsuList));
                continue;
            }
        }
        return resultList;
    }
    
    /**
     * 雀頭除外パターンを取得
     * 
     * @param hand 手牌。
     * @return 雀頭候補の除外パターン。
     */
    public static Map<JanPai, Map<JanPai, Integer>> getExcludeHeadPattern(final Map<JanPai, Integer> hand) {
        if (hand == null) {
            throw new NullPointerException("Hand is null.");
        }
        
        final Map<JanPai, Map<JanPai, Integer>> resultMap = new TreeMap<JanPai, Map<JanPai, Integer>>();
        for (final Map.Entry<JanPai, Integer> entry : hand.entrySet()) {
            final int count = entry.getValue();
            if (count >= 2) {
            	final JanPai head = entry.getKey();
                final Map<JanPai, Integer> pattern = deepCopyMap(hand);
                JanPaiUtil.removeJanPai(pattern, head, 2);
                resultMap.put(head, pattern);
            }
        }
        return resultMap;
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
        
        final Map<JanPai, Map<JanPai, Integer>> excludeHeadPattern = getExcludeHeadPattern(hand);
        if (excludeHeadPattern.isEmpty()) {
            // 雀頭候補が存在しない
            
            if (isCompleteZenhukou(hand)) {
                return true;
            }
            return false;
        }
        
        for (final Map<JanPai, Integer> pattern : excludeHeadPattern.values()) {
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
     * 暗槓数の役を取得
     * 
     * @param kohtsuList 刻子リスト。
     * @return 暗槓数の役。
     */
    private static ChmYaku getAnkanYaku(final List<MenTsu> kohtsuList) {
        int ankanCount = 0;
        
        for (final MenTsu kohtsu : kohtsuList) {
            final MenTsuType type = kohtsu.getMenTsuType();
            
            if (type.equals(MenTsuType.KAN_DARK)) {
                ankanCount++;
            }
        }
        
        switch (ankanCount) {
        case 2:
            return ChmYaku.TWO_CONCEALED_KONGS;
        case 1:
            return ChmYaku.CONCEALED_KONG;
        default:
            break;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 暗刻数の役を取得
     * 
     * @param kohtsuList 刻子リスト。
     * @return 暗刻数の役。
     */
    private static ChmYaku getAnkoYaku(final List<MenTsu> kohtsuList) {
        int ankoCount = 0;
        
        for (final MenTsu kohtsu : kohtsuList) {
            final MenTsuType type = kohtsu.getMenTsuType();
            
            if (type.equals(MenTsuType.KAN_DARK) || type.equals(MenTsuType.STANDARD_KOU_TSU)) {
                ankoCount++;
            }
        }
        
        switch (ankoCount) {
        case 4:
            return ChmYaku.FOUR_CONCEALED_PUNGS;
        case 3:
            return ChmYaku.THREE_CONCEALED_PUNGS;
        case 2:
            return ChmYaku.TWO_CONCEALED_PUNGS;
        default:
            break;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 槓子数の役を取得
     * 
     * @param kohtsuList 刻子リスト。
     * @return 槓子数の役。
     */
    private static ChmYaku getKanTsuYaku(final List<MenTsu> kohtsuList) {
        int kantsuCount = 0;
        
        for (final MenTsu kohtsu : kohtsuList) {
            final MenTsuType type = kohtsu.getMenTsuType();
            
            if (type.equals(MenTsuType.KAN_DARK) || type.equals(MenTsuType.KAN_LIGHT)) {
                kantsuCount++;
            }
        }
        
        switch (kantsuCount) {
        case 4:
            return ChmYaku.FOUR_KONGS;
        case 3:
            return ChmYaku.THREE_KONGS;
        case 2:
            return ChmYaku.TWO_MELDED_KONGS;
        case 1:
            return ChmYaku.MELDED_KONG;
        default:
            break;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 4刻子の役リストを取得
     * 
     * @param fourKohTsuList 刻子リスト。
     * @return 4刻子の役リスト。
     */
    private static List<ChmYaku> getFourKohTsuYakuList(final List<MenTsu> fourKohTsuList) {
        if (fourKohTsuList.size() != 4) {
            throw new IllegalArgumentException("Invalid MenTsu size" + fourKohTsuList.size());
        }
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        final ChmYaku fourPungsYaku = ChmYakuCheckUtil.getFourPungsYaku(fourKohTsuList);
        
        if (!fourPungsYaku.equals(ChmYaku.FLOWER)) {
            yakuList.add(fourPungsYaku);
            return yakuList;
        }
        
        for (final MenTsu excludeKohTsu : fourKohTsuList) {
            final List<MenTsu> threeKohTsuList = deepCopyList(fourKohTsuList);
            threeKohTsuList.remove(excludeKohTsu);
            final ChmYaku threePungsYaku = ChmYakuCheckUtil.getThreePungsYaku(threeKohTsuList);
            
            if (!threePungsYaku.equals(ChmYaku.FLOWER)) {
                yakuList.add(threePungsYaku);
                
                for (final MenTsu kohtsu : threeKohTsuList) {
                    final List<MenTsu> kohtsuList = new ArrayList<MenTsu>(Arrays.asList(kohtsu, excludeKohTsu));
                    Collections.sort(kohtsuList);
                    final ChmYaku twoPungsYaku = ChmYakuCheckUtil.getTwoPungsYaku(kohtsuList);
                    
                    if (!twoPungsYaku.equals(ChmYaku.FLOWER)) {
                        yakuList.add(twoPungsYaku);
                        break;
                    }
                }
                return yakuList;
            }
        }
        final List<ChmYaku> twoPungsYakuList = getTwoKohTsuYakuList(fourKohTsuList, 2);
        yakuList.addAll(twoPungsYakuList);
        return yakuList;
    }
    
    /**
     * 4順子の役リストを取得
     * 
     * @param fourShunTsuList 順子リスト。
     * @return 4順子の役リスト。
     */
    private static List<ChmYaku> getFourShunTsuYakuList(final List<MenTsu> fourShunTsuList) {
        if (fourShunTsuList.size() != 4) {
            throw new IllegalArgumentException("Invalid MenTsu size" + fourShunTsuList.size());
        }
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        final ChmYaku fourChowsYaku = ChmYakuCheckUtil.getFourChowsYaku(fourShunTsuList);
        
        if (!fourChowsYaku.equals(ChmYaku.FLOWER)) {
            yakuList.add(fourChowsYaku);
            return yakuList;
        }
        
        for (final MenTsu excludeShunTsu : fourShunTsuList) {
            final List<MenTsu> threeShunTsuList = deepCopyList(fourShunTsuList);
            threeShunTsuList.remove(excludeShunTsu);
            final ChmYaku threeChowsYaku = ChmYakuCheckUtil.getThreeChowsYaku(threeShunTsuList);
            
            if (!threeChowsYaku.equals(ChmYaku.FLOWER)) {
                yakuList.add(threeChowsYaku);
                
                for (final MenTsu shuntsu : threeShunTsuList) {
                    final List<MenTsu> shuntsuList = new ArrayList<MenTsu>(Arrays.asList(shuntsu, excludeShunTsu));
                    Collections.sort(shuntsuList);
                    final ChmYaku twoChowsYaku = ChmYakuCheckUtil.getTwoChowsYaku(shuntsuList);
                    
                    if (!twoChowsYaku.equals(ChmYaku.FLOWER)) {
                        yakuList.add(twoChowsYaku);
                        break;
                    }
                }
                return yakuList;
            }
        }
        final List<ChmYaku> twoChowsYakuList = getTwoShunTsuYakuList(fourShunTsuList, 3);
        yakuList.addAll(twoChowsYakuList);
        return yakuList;
    }
    
    /**
     * 面子役リストを取得
     * 
     * @param hand 手牌。
     * @param completePai 和了牌。
     * @return 面子役リスト。
     */
    private static List<ChmYaku> getMenTsuYakuList(final Hand hand, final CompleteJanPai completePai) {
        List<ChmYaku> preYakuList = new ArrayList<ChmYaku>();
        int prePoint = 0;
        
        for (final CompletePattern pattern : getCompletePatternList(hand, completePai)) {
            final List<ChmYaku> newYakuList = new ArrayList<ChmYaku>();
            
            switch (pattern.getShunTsuCount()) {
            case 0:
                if (ChmYakuCheckUtil.isAllEvenPungs(pattern)) {
                    newYakuList.add(ChmYaku.ALL_EVEN_PUNGS);
                }
                else {
                    newYakuList.add(ChmYaku.ALL_PUNGS);
                }
                final List<MenTsu> fourKohTsuList = pattern.getKohTsuList();
                final List<ChmYaku> fourKohTsuYakuList = getFourKohTsuYakuList(fourKohTsuList);
                newYakuList.addAll(fourKohTsuYakuList);
                break;
            case 1:
                final List<MenTsu> threeKohTsuList = pattern.getKohTsuList();
                final List<ChmYaku> threeKohTsuYakuList = getThreeKohTsuYakuList(threeKohTsuList);
                newYakuList.addAll(threeKohTsuYakuList);
                break;
            case 2:
                final List<MenTsu> twoKohTsuList = pattern.getKohTsuList();
                final List<ChmYaku> twoPungsYakuList = getTwoKohTsuYakuList(twoKohTsuList, 1);
                newYakuList.addAll(twoPungsYakuList);
                
                final List<MenTsu> twoShunTsuList = pattern.getShunTsuList();
                final List<ChmYaku> twoChowsYakuList = getTwoShunTsuYakuList(twoShunTsuList, 1);
                newYakuList.addAll(twoChowsYakuList);
                break;
            case 3:
                final List<MenTsu> threeShunTsuList = pattern.getShunTsuList();
                final List<ChmYaku> threeShunTsuYakuList = getThreeShunTsuYakuList(threeShunTsuList);
                newYakuList.addAll(threeShunTsuYakuList);
                break;
            case 4:
                final Map<JanPai, Integer> allPaiMap = hand.getAllJanPaiMap();
                JanPaiUtil.addJanPai(allPaiMap, completePai.getJanPai(), 1);
                JanPaiUtil.cleanJanPaiMap(allPaiMap);
                
                final ChmYaku terminalChows = ChmYakuCheckUtil.getTerminalChows(allPaiMap);
                
                if (terminalChows != ChmYaku.FLOWER) {
                    newYakuList.add(terminalChows);
                    break;
                }
                final List<MenTsu> fourShunTsuList = pattern.getShunTsuList();
                final List<ChmYaku> fourShunTsuYakuList = getFourShunTsuYakuList(fourShunTsuList);
                newYakuList.addAll(fourShunTsuYakuList);
                
                if (ChmYakuCheckUtil.isAllChows(pattern)) {
                    newYakuList.add(ChmYaku.ALL_CHOWS);
                }
                break;
            default:
                break;
            }
            final ChmYaku kantsuYaku = getKanTsuYaku(pattern.getKohTsuList());
            
            if (!kantsuYaku .equals(ChmYaku.FLOWER)) {
                newYakuList.add(kantsuYaku);
            }
            final ChmYaku ankanYaku = getAnkanYaku(pattern.getKohTsuList());
            
            if (!ankanYaku .equals(ChmYaku.FLOWER)) {
                newYakuList.add(ankanYaku);
            }
            final ChmYaku ankoYaku = getAnkoYaku(pattern.getKohTsuList());
            
            if (!ankoYaku .equals(ChmYaku.FLOWER)) {
                newYakuList.add(ankoYaku);
            }
            final List<MenTsu> kohtsuList = pattern.getKohTsuList();
            final int PungOfTerminalsOrHonorsCount = ChmYakuCheckUtil.getPungOfTerminalsOrHonorsCount(kohtsuList);
            
            for (int count = 0; count < PungOfTerminalsOrHonorsCount; count++) {
                newYakuList.add(ChmYaku.PUNG_OF_TERMINALS_OR_HONORS);
            }
            
            if (ChmYakuCheckUtil.isAllFives(pattern)) {
                newYakuList.add(ChmYaku.ALL_FIVES);
            }
            else if (ChmYakuCheckUtil.isOutsideHand(pattern)) {
                newYakuList.add(ChmYaku.OUTSIDE_HAND);
            }
            final Map<JanPai, Integer> menzenMap = hand.getMenZenMap();
            JanPaiUtil.cleanJanPaiMap(menzenMap);
            
            final List<JanPai> completablePaiList = getCompletableJanPaiList(menzenMap);
            final ChmYaku waitYaku = ChmYakuCheckUtil.getWaitYaku(pattern, completePai, completablePaiList);
            
            if (!waitYaku.equals(ChmYaku.FLOWER)) {
                newYakuList.add(waitYaku);
            }
            int newPoint = 0;
            
            for (final ChmYaku yaku : newYakuList) {
                newPoint += yaku.getPoint();
            }
            
            if (newPoint > prePoint) {
                preYakuList = newYakuList;
                prePoint = newPoint;
            }
        }
        return preYakuList;
    }
    
    /**
     * 3刻子の役リストを取得
     * 
     * @param threeKohTsuList 刻子リスト。
     * @return 3刻子の役リスト。
     */
    private static List<ChmYaku> getThreeKohTsuYakuList(final List<MenTsu> threeKohTsuList) {
        if (threeKohTsuList.size() != 3) {
            throw new IllegalArgumentException("Invalid MenTsu size" + threeKohTsuList.size());
        }
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        final ChmYaku threePungsYaku = ChmYakuCheckUtil.getThreePungsYaku(threeKohTsuList);
        
        if (!threePungsYaku.equals(ChmYaku.FLOWER)) {
            yakuList.add(threePungsYaku);
            return yakuList;
        }
        final List<ChmYaku> twoPungsYakuList = getTwoKohTsuYakuList(threeKohTsuList, 1);
        yakuList.addAll(twoPungsYakuList);
        return yakuList;
    }
    
    /**
     * 3順子の役リストを取得
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 3順子の役リスト。
     */
    private static List<ChmYaku> getThreeShunTsuYakuList(final List<MenTsu> threeShunTsuList) {
        if (threeShunTsuList.size() != 3) {
            throw new IllegalArgumentException("Invalid MenTsu size" + threeShunTsuList.size());
        }
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        final ChmYaku threeChowsYaku = ChmYakuCheckUtil.getThreeChowsYaku(threeShunTsuList);
        
        if (!threeChowsYaku.equals(ChmYaku.FLOWER)) {
            yakuList.add(threeChowsYaku);
            return yakuList;
        }
        final List<ChmYaku> twoChowsYakuList = getTwoShunTsuYakuList(threeShunTsuList, 2);
        yakuList.addAll(twoChowsYakuList);
        return yakuList;
    }
    
    /**
     * 2刻子役リストを取得
     * 
     * @param kohtsuList 刻子リスト。
     * @param maxSize 2刻子役リストの最大サイズ。
     * @return 2刻子役リスト。
     */
    private static List<ChmYaku> getTwoKohTsuYakuList(final List<MenTsu> kohtsuList, final int maxSize) {
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        final List<MenTsu> excludedList = deepCopyList(kohtsuList);
        
        for (final MenTsu first : kohtsuList) {
            excludedList.remove(first);
            
            for (final MenTsu second : excludedList) {
                final List<MenTsu> twoKohTsuList = new ArrayList<MenTsu>(Arrays.asList(first, second));
                final ChmYaku twoPungsYaku = ChmYakuCheckUtil.getTwoPungsYaku(twoKohTsuList);
                
                if (!twoPungsYaku.equals(ChmYaku.FLOWER)) {
                    yakuList.add(twoPungsYaku);
                    
                    if (yakuList.size() == maxSize) {
                        return yakuList;
                    }
                }
            }
        }
        return yakuList;
    }
    
    /**
     * 2順子役リストを取得
     * 
     * @param shuntsuList 順子リスト。
     * @param maxSize 2順子役リストの最大サイズ。
     * @return 2順子役リスト。
     */
    private static List<ChmYaku> getTwoShunTsuYakuList(final List<MenTsu> shuntsuList, final int maxSize) {
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        final List<MenTsu> targetList = deepCopyList(shuntsuList);
        
        if (removeIpeikou(targetList)) {
            yakuList.add(ChmYaku.PURE_DOUBLE_CHOW);
        }
        final List<MenTsu> excludedList = deepCopyList(targetList);
        
        for (final MenTsu first : targetList) {
            excludedList.remove(first);
            
            for (final MenTsu second : excludedList) {
                final List<MenTsu> twoShunTsuList = new ArrayList<MenTsu>(Arrays.asList(first, second));
                final ChmYaku twoChowsYaku = ChmYakuCheckUtil.getTwoChowsYaku(twoShunTsuList);
                
                if (!twoChowsYaku.equals(ChmYaku.FLOWER)) {
                    yakuList.add(twoChowsYaku);
                    
                    if (yakuList.size() == maxSize) {
                        return yakuList;
                    }
                }
            }
        }
        return yakuList;
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
        final int removeCount = removeJi(copyHand);
        
        if (removeCount < 5) {
            return false;
        }
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
     * 除外役を削除
     * 
     * @param source 削除元の役リスト。
     * @param isDouble 場風と自風が同じか。
     */
    private static void removeExcludeYaku(final List<ChmYaku> source, final boolean isDouble) {
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>(source);
        
        for (final ChmYaku yaku : yakuList) {
            if (yaku.equals(ChmYaku.PREVALENT_WIND) && isDouble) {
                continue;
            }
            final List<ChmYaku> excludeYakuList = yaku.getExcludeChmYaku();
            
            for (final ChmYaku excludeYaku : excludeYakuList) {
                source.remove(excludeYaku);
            }
        }
    }
    
    /**
     * 一盃口を削除
     * 
     * @param source 削除元の面子リスト。
     * @return 削除したか。
     */
    private static boolean removeIpeikou(final List<MenTsu> source) {
        final List<MenTsu> targetList = deepCopyList(source);
        final List<MenTsu> excludedList = deepCopyList(targetList);
        
        for (final MenTsu first : targetList) {
            excludedList.remove(first);
            
            for (final MenTsu second : excludedList) {
                final List<MenTsu> twoShunTsuList = new ArrayList<MenTsu>(Arrays.asList(first, second));
                
                if (ChmYakuCheckUtil.isPureDoubleChow(twoShunTsuList)) {
                    source.remove(first);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 字牌を1枚ずつ削除
     * 
     * @param source 削除元の牌マップ。
     * @return 削除した字牌の種類の数。
     */
    private static int removeJi(final Map<JanPai, Integer> source) {
        final List<JanPai> paiList = new ArrayList<JanPai>(source.keySet());
        int removeCount = 0;
        
        for (final JanPai entry : paiList) {
            if (JanPaiUtil.JI_LIST.contains(entry)) {
                JanPaiUtil.removeJanPai(source, entry, 1);
                removeCount++;
            }
        }
        return removeCount;
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

