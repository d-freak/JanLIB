/**
 * ChmHandCheckUtil.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wiz.project.jan.ChmCompleteInfo;
import wiz.project.jan.ChmYaku;
import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.CompletePattern;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.Kumiairyu;
import wiz.project.jan.KumiairyuType;
import wiz.project.jan.MenTsu;
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
            yakuList.addAll(ChmYakuCheckUtil.getCompleteJanPaiYaku(completePai));
            removeExcludeYaku(yakuList);
            
            return new ChmCompleteInfo(yakuList, completePai.getType());
        }
        
        if (isCompleteKokushi(allPaiMap)) {
            yakuList.add(ChmYaku.THIRTEEN_ORPHANS);
            yakuList.addAll(ChmYakuCheckUtil.getCompleteJanPaiYaku(completePai));
            removeExcludeYaku(yakuList);
            
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
            yakuList.addAll(getMenTsuYaku(hand, completePai));
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
        
        for (int count = 0; count < ChmYakuCheckUtil.getTileHogCount(hand, allPaiMap); count++) {
            yakuList.add(ChmYaku.TILE_HOG);
        }
        yakuList.addAll(ChmYakuCheckUtil.getCompleteJanPaiYaku(completePai));
        removeExcludeYaku(yakuList);
        
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
            mentsuList1.addAll(HandCreateUtil.getKouTsuList(copy1));
            if (copy1.isEmpty()) {
                Collections.sort(mentsuList1);
                resultList.add(new CompletePattern(entry.getKey(), mentsuList1));
                continue;
            }
            
            final Map<JanPai, Integer> copy2 = deepCopyMap(pattern);
            List<MenTsu> mentsuList2 = deepCopyList(mentsuList);
            
            // 刻子優先パターン
            mentsuList2.addAll(HandCreateUtil.getKouTsuList(copy2));
            mentsuList2.addAll(HandCreateUtil.getShunTsuList(copy2));
            if (copy1.isEmpty()) {
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
            
            mentsuList.addAll(HandCreateUtil.getKouTsuList(pattern));
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
                    final ChmYaku twoChowsYaku = ChmYakuCheckUtil.getTwoChowsYaku(shuntsu, excludeShunTsu);
                    
                    if (!twoChowsYaku.equals(ChmYaku.FLOWER)) {
                        yakuList.add(twoChowsYaku);
                        break;
                    }
                }
                return yakuList;
            }
        }
        final List<ChmYaku> twoChowsYakuList = getTwoChowsYakuList(fourShunTsuList);
        yakuList.addAll(twoChowsYakuList);
        return yakuList;
    }
    
    /**
     * 面子役を取得
     * 
     * @param hand 手牌。
     * @param completePai 和了牌。
     * @return 面子役リスト。
     */
    private static List<ChmYaku> getMenTsuYaku(final Hand hand, final CompleteJanPai completePai) {
        List<ChmYaku> preYakuList = new ArrayList<ChmYaku>();
        int prePoint = 0;
        
        for (final CompletePattern pattern : getCompletePatternList(hand, completePai)) {
            final List<ChmYaku> newYakuList = new ArrayList<ChmYaku>();
            
            switch (pattern.getShunTsuCount()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                final List<MenTsu> threeShunTsuList = pattern.getShunTsuList();
                final List<ChmYaku> threeShunTsuYakuList = getThreeShunTsuYakuList(threeShunTsuList);
                newYakuList.addAll(threeShunTsuYakuList);
                break;
            case 4:
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
        final List<ChmYaku> twoChowsYakuList = getTwoChowsYakuList(threeShunTsuList);
        yakuList.addAll(twoChowsYakuList);
        return yakuList;
    }
    
    /**
     * 2順子役リストを取得
     * 
     * @param shuntsuList 順子リスト。
     * @return 2順子役リスト。
     */
    private static List<ChmYaku> getTwoChowsYakuList(final List<MenTsu> shuntsuList) {
        return new ArrayList<ChmYaku>();
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
     * 除外役を削除
     * 
     * @param source 削除元の役リスト。
     */
    private static void removeExcludeYaku(final List<ChmYaku> source) {
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>(source);
        
        for (final ChmYaku yaku : yakuList) {
            source.removeAll(yaku.getExcludeChmYaku());
        }
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

