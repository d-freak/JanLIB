/**
 * ChmYakuCheckUtilTest.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import wiz.project.jan.ChmYaku;
import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.CompleteType;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.MenTsu;
import wiz.project.jan.MenTsuType;
import wiz.project.jan.Wind;



/**
 * ChmHandCheckUtilのテスト
 */
public final class ChmYakuCheckUtilTest {
    
    /**
     * getCompleteInfo()のテスト(平和)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllChows() {
        // あがり役：平和
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌： [4m] [5m] [4p] [5p] [6p] [7s] [8s] [9s] [9s] [9s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 3);}}, mentsuList);
        // あがり：[3m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_3, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、緑一色、混一色、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllGreen() {
        // あがり役：七対、緑一色、混一色、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_GREEN, ChmYaku.HALF_FLUSH, ChmYaku.TILE_HOG);
        // 手牌：[2s] [2s] [3s] [3s] [4s] [4s] [6s] [6s] [6s] [6s] [8s] [發] [發]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_2, 2);}
            {put(JanPai.SOU_3, 2);}
            {put(JanPai.SOU_4, 2);}
            {put(JanPai.SOU_6, 4);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.HATU, 2);}
        });
        // あがり：[8s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_8, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、字一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllHonors() {
        // あがり役：七対、字一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_HONORS);
        // 手牌：[東] [東] [南] [南] [西] [西] [北] [北] [白] [白] [發] [發] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.TON, 2);}
            {put(JanPai.NAN, 2);}
            {put(JanPai.SHA, 2);}
            {put(JanPai.PEI, 2);}
            {put(JanPai.HAKU, 2);}
            {put(JanPai.HATU, 2);}
            {put(JanPai.CHUN, 1);}
        });
        // あがり：[中]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllSimples() {
        // あがり役：七対、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_SIMPLES);
        // 手牌：[3m] [3m] [4p] [4p] [7p] [7p] [5s] [5s] [6s] [6s] [7s] [7s] [8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.PIN_4, 2);}
            {put(JanPai.PIN_7, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.SOU_7, 2);}
            {put(JanPai.SOU_8, 1);}
        });
        // あがり：[8s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_8, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、清幺九、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllTerminals() {
        // あがり役：七対、清幺九、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_TERMINALS, ChmYaku.TILE_HOG);
        // 手牌：[1m] [1m] [9m] [9m] [1p] [1p] [9p] [9p] [1s] [1s] [9s] [9s] [9s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_9, 2);}
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.PIN_9, 2);}
            {put(JanPai.SOU_1, 2);}
            {put(JanPai.SOU_9, 3);}
        });
        // あがり：[9s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、混幺九)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllTerminalsAndHonors() {
        // あがり役：七対、混幺九
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_TERMINALS_AND_HONORS);
        // 手牌：[1m] [1m] [9m] [9m] [1p] [1p] [9p] [9p] [1s] [1s] [9s] [9s] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_9, 2);}
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.PIN_9, 2);}
            {put(JanPai.SOU_1, 2);}
            {put(JanPai.SOU_9, 2);}
            {put(JanPai.CHUN, 1);}
        });
        // あがり：[中]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、五門斉)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllTypes() {
        // あがり役：七対、五門斉
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_TYPES);
        // 手牌：[3m] [3m] [4p] [4p] [7p] [7p] [5s] [5s] [6s] [6s] [東] [東] [白]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.PIN_4, 2);}
            {put(JanPai.PIN_7, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.TON, 2);}
            {put(JanPai.HAKU, 1);}
        });
        // あがり：[白]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(大四喜、字一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsBigFourWinds() {
        // あがり役：大四喜、字一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.BIG_FOUR_WINDS, ChmYaku.ALL_HONORS);
        // 手牌：[東] [東] [東] [南] [南] [南] [西] [西] [西] [北] [北] [白] [白]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.TON, 3);}
            {put(JanPai.NAN, 3);}
            {put(JanPai.SHA, 3);}
            {put(JanPai.PEI, 2);}
            {put(JanPai.HAKU, 2);}
        });
        // あがり：[北]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PEI, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(大三元、圈風刻、門風刻、字一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsBigThreeDragons() {
        // あがり役：大三元、圈風刻、門風刻、字一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.BIG_THREE_DRAGONS, ChmYaku.PREVALENT_WIND, ChmYaku.SEAT_WIND, ChmYaku.ALL_HONORS);
        // 手牌：[東] [東] [東] [南] [南] [白] [白] [白] [發] [發] [發] [中] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.TON, 3);}
            {put(JanPai.NAN, 2);}
            {put(JanPai.HAKU, 3);}
            {put(JanPai.HATU, 3);}
            {put(JanPai.CHUN, 2);}
        });
        // あがり：[中]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(三風刻、箭刻、圈風刻、門風刻、字一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsBigThreeWinds() {
        // あがり役：三風刻、箭刻、圈風刻、門風刻、字一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.BIG_THREE_WINDS, ChmYaku.DRAGON_PUNG, ChmYaku.PREVALENT_WIND, ChmYaku.SEAT_WIND, ChmYaku.ALL_HONORS);
        // 手牌：[東] [東] [東] [南] [南] [南] [西] [西] [西] [白] [白] [中] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.TON, 3);}
            {put(JanPai.NAN, 3);}
            {put(JanPai.SHA, 3);}
            {put(JanPai.HAKU, 2);}
            {put(JanPai.CHUN, 2);}
        });
        // あがり：[中]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、緑一色、断幺、清一色、四帰一、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFullFlush() {
        // あがり役：七対、緑一色、断幺、清一色、四帰一、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_GREEN, ChmYaku.ALL_SIMPLES, ChmYaku.FULL_FLUSH, ChmYaku.TILE_HOG, ChmYaku.TILE_HOG);
        // 手牌：[2s] [2s] [3s] [3s] [4s] [4s] [6s] [6s] [6s] [6s] [8s] [8s] [8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_2, 2);}
            {put(JanPai.SOU_3, 2);}
            {put(JanPai.SOU_4, 2);}
            {put(JanPai.SOU_6, 4);}
            {put(JanPai.SOU_8, 3);}
        });
        // あがり：[8s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_8, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFullyConcealed() {
        // あがり役：七対、不求人
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.FULLY_CONCEALED);
        // 手牌：[3m] [3m] [4p] [4p] [7p] [7p] [5s] [5s] [6s] [6s] [白] [白] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.PIN_4, 2);}
            {put(JanPai.PIN_7, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.HAKU, 2);}
            {put(JanPai.CHUN, 1);}
        });
        // あがり：[中]面前ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, 2, CompleteType.TSUMO_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七星不靠)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsGreaterHonorsAndKnittedTiles() {
        // あがり役：七星不靠
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.GREATER_HONORS_AND_KNITTED_TILES);
        // 手牌：[1m] [4m] [2p] [5p] [3s] [6s] [東] [南] [西] [北] [白] [發] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.TON, 1);}
            {put(JanPai.NAN, 1);}
            {put(JanPai.SHA, 1);}
            {put(JanPai.PEI, 1);}
            {put(JanPai.HAKU, 1);}
            {put(JanPai.HATU, 1);}
            {put(JanPai.CHUN, 1);}
        });
        // あがり：[7m]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、緑一色、混一色、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsHalfFlush() {
        // あがり役：七対、緑一色、混一色、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ALL_GREEN, ChmYaku.HALF_FLUSH, ChmYaku.TILE_HOG);
        // 手牌：[2s] [2s] [3s] [3s] [4s] [4s] [6s] [6s] [6s] [6s] [8s] [發] [發]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_2, 2);}
            {put(JanPai.SOU_3, 2);}
            {put(JanPai.SOU_4, 2);}
            {put(JanPai.SOU_6, 4);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.HATU, 2);}
        });
        // あがり：[8s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_8, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(全不靠)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLesserHonorsAndKnittedTiles() {
        // あがり役：全不靠
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.LESSER_HONORS_AND_KNITTED_TILES);
        // 手牌：[1s] [4s] [7s] [2m] [5m] [8m] [6p] [9p] [東] [西] [白] [發] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.TON, 1);}
            {put(JanPai.SHA, 1);}
            {put(JanPai.HAKU, 1);}
            {put(JanPai.HATU, 1);}
            {put(JanPai.CHUN, 1);}
        });
        // あがり：[南]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.NAN, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(全不靠、組合龍)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLesserHonorsAndKnittedTilesAndKnittedStraight() {
        // あがり役：全不靠、組合龍
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.LESSER_HONORS_AND_KNITTED_TILES, ChmYaku.KNITTED_STRAIGHT);
        // 手牌：[1p] [4p] [7p] [2s] [5s] [8s] [3m] [6m] [9m] [東] [南] [西] [北]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.TON, 1);}
            {put(JanPai.NAN, 1);}
            {put(JanPai.SHA, 1);}
            {put(JanPai.PEI, 1);}
        });
        // あがり：[白]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、小于五)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLowerFour() {
        // あがり役：七対、小于五
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.LOWER_FOUR);
        // 手牌：[1m] [1m] [2m] [2m] [3m] [3m] [4p] [4p] [1s] [1s] [2s] [2s] [3s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.PIN_4, 2);}
            {put(JanPai.SOU_1, 2);}
            {put(JanPai.SOU_2, 2);}
            {put(JanPai.SOU_3, 1);}
        });
        // あがり：[3s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_3, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、全小)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLowerTiles() {
        // あがり役：七対、全小
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.LOWER_TILES);
        // 手牌：[1m] [1m] [2m] [2m] [3m] [3m] [1p] [1p] [1s] [1s] [2s] [2s] [3s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.SOU_1, 2);}
            {put(JanPai.SOU_2, 2);}
            {put(JanPai.SOU_3, 1);}
        });
        // あがり：[3s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_3, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、全中)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMiddleTiles() {
        // あがり役：七対、全中
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.MIDDLE_TILES);
        // 手牌：[4m] [4m] [5m] [5m] [6m] [6m] [5p] [5p] [4s] [4s] [5s] [5s] [6s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_6, 2);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.SOU_4, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 1);}
        });
        // あがり：[6s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_6, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、缺一門)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsOneVoidedSuit1() {
        // あがり役：七対、缺一門(MAN, PIN)
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ONE_VOIDED_SUIT);
        // 手牌：[1m] [1m] [2m] [2m] [6m] [6m] [5p] [5p] [6p] [6p] [8p] [8p] [白]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_6, 2);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.PIN_8, 2);}
            {put(JanPai.HAKU, 1);}
        });
        // あがり：[白]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、缺一門)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsOneVoidedSuit2() {
        // あがり役：七対、缺一門(SOU, MAN)
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ONE_VOIDED_SUIT);
        // 手牌：[1m] [1m] [2m] [2m] [6m] [6m] [5s] [5s] [6s] [6s] [8s] [8s] [白]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_6, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.HAKU, 1);}
        });
        // あがり：[白]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、缺一門)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsOneVoidedSuit3() {
        // あがり役：七対、缺一門(PIN, SOU)
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.ONE_VOIDED_SUIT);
        // 手牌：[1p] [1p] [2p] [2p] [6p] [6p] [5s] [5s] [6s] [6s] [8s] [8s] [白]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.PIN_2, 2);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.HAKU, 1);}
        });
        // あがり：[白]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、推不倒)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsReversibleTiles() {
        // あがり役：七対、推不倒
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.REVERSIBLE_TILES);
        // 手牌：[1p] [1p] [2p] [2p] [3p] [3p] [5s] [5s] [6s] [6s] [8s] [8s] [白]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.PIN_2, 2);}
            {put(JanPai.PIN_3, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.HAKU, 1);}
        });
        // あがり：[白]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsSevenPairs() {
        // あがり役：七対
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS);
        // 手牌：[1m] [1m] [9m] [9m] [2p] [2p] [8s] [8s] [東] [東] [西] [西] [北]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_9, 2);}
            {put(JanPai.PIN_2, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.TON, 2);}
            {put(JanPai.SHA, 2);}
            {put(JanPai.PEI, 1);}
        });
        // あがり：[北]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PEI, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(連七対)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsSevenShiftedPairs() {
        // あがり役：連七対
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_SHIFTED_PAIRS);
        // 手牌：[1m] [1m] [2m] [2m] [3m] [3m] [4m] [4m] [5m] [5m] [6m] [6m] [7m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_6, 2);}
            {put(JanPai.MAN_7, 1);}
        });
        // あがり：[7m]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(十三幺、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThirteenOrpahns() {
        // あがり役：十三幺、不求人
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THIRTEEN_ORPHANS, ChmYaku.FULLY_CONCEALED);
        // 手牌：[1m] [9m] [1p] [9p] [1s] [9s] [東] [南] [西] [北] [白] [發] [中]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_9, 1);}
            {put(JanPai.TON, 1);}
            {put(JanPai.NAN, 1);}
            {put(JanPai.SHA, 1);}
            {put(JanPai.PEI, 1);}
            {put(JanPai.HAKU, 1);}
            {put(JanPai.HATU, 1);}
            {put(JanPai.CHUN, 1);}
        });
        // あがり：[9m]面前ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_9, 2, CompleteType.TSUMO_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、四帰一、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTileHong() {
        // あがり役：七対、四帰一、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.TILE_HOG, ChmYaku.TILE_HOG);
        // 手牌：[1m] [1m] [1m] [1m] [4p] [4p] [4p] [4p] [6s] [6s] [白] [中] [中] 
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 4);}
            {put(JanPai.PIN_4, 4);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.HAKU, 1);}
            {put(JanPai.CHUN, 2);}
        });
        // あがり：[白]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(双箭刻)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoDragonPungs() {
        // あがり役：双箭刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_DRAGON_PUNGS);
        // 手牌：[1m] [2m] [3m] [4s] [5s] [6s] [7p] [7p] [白] [白] [白] [發] [發]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.PIN_7, 2);}
            {put(JanPai.HAKU, 3);}
            {put(JanPai.HATU, 2);}
        });
        // あがり：[發]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HATU, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、大于五)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsUpperFour() {
        // あがり役：七対、大于五
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.UPPER_FOUR);
        // 手牌：[7m] [7m] [8m] [8m] [9m] [9m] [6p] [6p] [7s] [7s] [8s] [8s] [9s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_7, 2);}
            {put(JanPai.MAN_8, 2);}
            {put(JanPai.MAN_9, 2);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_7, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.SOU_9, 1);}
        });
        // あがり：[9s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対、全大)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsUpperTiles() {
        // あがり役：七対、全大
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.UPPER_TILES);
        // 手牌：[7m] [7m] [8m] [8m] [9m] [9m] [9p] [9p] [7s] [7s] [8s] [8s] [9s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_7, 2);}
            {put(JanPai.MAN_8, 2);}
            {put(JanPai.MAN_9, 2);}
            {put(JanPai.PIN_9, 2);}
            {put(JanPai.SOU_7, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.SOU_9, 1);}
        });
        // あがり：[9s]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
}

