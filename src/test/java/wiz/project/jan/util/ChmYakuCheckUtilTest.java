/**
 * ChmYakuCheckUtilTest.java
 *
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.CompleteType;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.MenTsu;
import wiz.project.jan.MenTsuType;
import wiz.project.jan.Wind;
import wiz.project.jan.yaku.ChmYaku;



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
        // 手牌：[4m] [5m] [4p] [5p] [6p] [7s] [8s] [9s] [9s] [9s]  [2m][3m][4m]
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(全双刻、双同刻、双同刻)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllEvenPungs() {
        // あがり役：全双刻、双同刻、双同刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_EVEN_PUNGS, ChmYaku.DOUBLE_PUNG, ChmYaku.DOUBLE_PUNG);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_6, JanPai.MAN_6, JanPai.MAN_6), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_2, JanPai.PIN_2, JanPai.PIN_2), MenTsuType.PON));
        // 手牌：[6p] [6p] [8s] [8s]  [2m][2m][2m] [6m][6m][6m] [2p][2p][2p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(全帯五)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllFives() {
        // あがり役：全帯五
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_FIVES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_4, JanPai.MAN_5), MenTsuType.CHI));
        // 手牌：[5m] [6m] [4p] [5p] [5p] [5p] [6p] [5s] [5s] [5s]  [3m][4m][5m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.SOU_5, 3);}}, mentsuList);
        // あがり：[7m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(碰碰和、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsAllPungs() {
        // あがり役：碰碰和、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        // 手牌：[6p] [6p] [8s] [8s]  [2m][2m][2m] [3m][3m][3m] [5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.TON, JanPai.TON, JanPai.TON), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.NAN, JanPai.NAN, JanPai.NAN), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SHA, JanPai.SHA, JanPai.SHA), MenTsuType.PON));
        // 手牌：[北] [北] [白] [白]  [東][東][東] [南][南][南] [西][西][西]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PEI, 2);}
            {put(JanPai.HAKU, 2);}}, mentsuList);
        // あがり：[北]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PEI, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.TON, JanPai.TON, JanPai.TON), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.HAKU, JanPai.HAKU, JanPai.HAKU), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.HATU, JanPai.HATU, JanPai.HATU), MenTsuType.PON));
        // 手牌：[南] [南] [中] [中]  [東][東][東] [白][白][白] [發][發][發]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.NAN, 2);}
            {put(JanPai.CHUN, 2);}}, mentsuList);
        // あがり：[中]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三風刻、箭刻、圈風刻、門風刻、字一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsBigThreeWinds() {
        // あがり役：三風刻、箭刻、圈風刻、門風刻、字一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.BIG_THREE_WINDS, ChmYaku.PREVALENT_WIND, ChmYaku.SEAT_WIND, ChmYaku.DRAGON_PUNG, ChmYaku.ALL_HONORS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.TON, JanPai.TON, JanPai.TON), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.NAN, JanPai.NAN, JanPai.NAN), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SHA, JanPai.SHA, JanPai.SHA), MenTsuType.PON));
        // 手牌：[白] [白] [中] [中]  [東][東][東] [南][南][南] [西][西][西]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.HAKU, 2);}
            {put(JanPai.CHUN, 2);}}, mentsuList);
        // あがり：[中]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(無番和)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsChickenHand() {
        // あがり役：無番和
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.CHICKEN_HAND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_4, JanPai.MAN_5), MenTsuType.CHI));
        // 手牌：[1m] [2m] [3m] [5s] [6s] [7s] [7p] [8p] [發] [發]  [3m][4m][5m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[9p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_9, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(坎張)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsClosedWait() {
        // あがり役：坎張
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.CLOSED_WAIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_4, JanPai.MAN_5), MenTsuType.CHI));
        // 手牌：[1m] [2m] [3m] [5s] [6s] [7s] [7p] [9p] [發] [發]  [3m][4m][5m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[8p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_8, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(暗槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsConcealedKong1() {
        // あがり役：暗槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.CONCEALED_KONG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        // 手牌：[6p] [6p] [2s] [3s]  [■][2m][2m][■] [3m][3m][3m] [5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(碰碰和、暗槓、単調将、断幺) ※ 全求人を含まないことを確認するテスト
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsConcealedKong2() {
        // あがり役：碰碰和、暗槓、単調将、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_PUNGS, ChmYaku.CONCEALED_KONG, ChmYaku.SINGLE_WAIT, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_6, JanPai.PIN_6, JanPai.PIN_6), MenTsuType.PON));
        // 手牌：[4s]  [■][2m][2m][■] [3m][3m][3m] [5p][5p][5p] [6p][6p][6p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_4, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(暗槓、四暗刻、断幺、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsConcealedKongAndFourConcealedPungs() {
        // あがり役：暗槓、四暗刻、断幺、不求人
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.CONCEALED_KONG, ChmYaku.FOUR_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.FULLY_CONCEALED);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        // 手牌：[3m] [3m] [3m] [5p] [5p] [5p] [6p] [6p] [8s] [8s]  [■][2m][2m][■]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 3);}
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]門前ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.TSUMO_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(暗槓、三暗刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsConcealedKongAndThreeConcealedPungs() {
        // あがり役：暗槓、三暗刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.CONCEALED_KONG, ChmYaku.THREE_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        // 手牌：[3m] [3m] [3m] [5p] [5p] [5p] [6p] [6p] [2s] [3s]  [■][2m][2m][■]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 3);}
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双同刻)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsDoublePung1() {
        // あがり役：双同刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.DOUBLE_PUNG);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_5, JanPai.MAN_5, JanPai.MAN_5), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_2, JanPai.SOU_2, JanPai.SOU_2), MenTsuType.PON));
        // 手牌：[5p] [5p] [5p] [3s] [4s] [發] [發]  [5m][5m][5m] [2s][2s][2s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[5s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_5, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双同刻、幺九刻、幺九刻)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsDoublePung2() {
        // あがり役：双同刻、幺九刻、幺九刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.DOUBLE_PUNG, ChmYaku.PUNG_OF_TERMINALS_OR_HONORS, ChmYaku.PUNG_OF_TERMINALS_OR_HONORS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_9, JanPai.MAN_9, JanPai.MAN_9), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_2, JanPai.SOU_2, JanPai.SOU_2), MenTsuType.PON));
        // 手牌：[9p] [9p] [9p] [3s] [4s] [發] [發]  [9m][9m][9m] [2s][2s][2s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_9, 3);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[5s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_5, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(暗槓、双暗刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsConcealedKongAndTwoConcealedPungs() {
        // あがり役：暗槓、双暗刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.CONCEALED_KONG, ChmYaku.TWO_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        // 手牌：[5p] [5p] [5p] [6p] [6p] [2s] [3s]  [■][2m][2m][■] [3m][3m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(辺張)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsEdgeWait() {
        // あがり役：辺張
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.EDGE_WAIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_4, JanPai.MAN_5), MenTsuType.CHI));
        // 手牌：[1m] [2m] [3m] [5s] [6s] [7s] [8p] [9p] [發] [發]  [3m][4m][5m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[7p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_7, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(四暗刻、断幺、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourConcealedPungs() {
        // あがり役：四暗刻、断幺、不求人
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.FULLY_CONCEALED);
        // 手牌：[2m] [2m] [2m] [3m] [3m] [3m] [5p] [5p] [5p] [6p] [6p] [8s] [8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 3);}
            {put(JanPai.MAN_3, 3);}
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}
        });
        // あがり：[6p]門前ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.TSUMO_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(四槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourKongs() {
        // あがり役：四槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_KONGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8), MenTsuType.KAN_LIGHT));
        // 手牌：[6p]  [2m][2m][2m][2m] [3m][3m][3m][3m] [5p][5p][5p][5p] [8s][8s][8s][8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 1);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(四槓、暗槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourKongsAndConcealedKong() {
        // あがり役：四槓、暗槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_KONGS, ChmYaku.CONCEALED_KONG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8), MenTsuType.KAN_LIGHT));
        // 手牌：[6p]  [■][2m][2m][■] [3m][3m][3m][3m] [5p][5p][5p][5p] [8s][8s][8s][8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 1);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(四槓、四暗刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourKongsAndFourConcealedPungs() {
        // あがり役：四槓、四暗刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_KONGS, ChmYaku.FOUR_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8), MenTsuType.KAN_DARK));
        // 手牌：[6p]  [■][2m][2m][■] [■][3m][3m][■] [■][5p][5p][■] [■][8s][8s][■]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 1);}}, mentsuList);
        // あがり：[6p]門前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(四槓、三暗刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourKongsAndThreeConcealedPungs() {
        // あがり役：四槓、三暗刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_KONGS, ChmYaku.THREE_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8), MenTsuType.KAN_LIGHT));
        // 手牌：[6p]  [■][2m][2m][■] [■][3m][3m][■] [■][5p][5p][■] [8s][8s][8s][8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 1);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(四槓、双暗槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourKongsAndTwoConcealedKongs() {
        // あがり役：四槓、双暗槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_KONGS, ChmYaku.TWO_CONCEALED_KONGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8), MenTsuType.KAN_LIGHT));
        // 手牌：[6p]  [■][2m][2m][■] [■][3m][3m][■] [5p][5p][5p][5p] [8s][8s][8s][8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 1);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色四節高、混一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourPureShiftedPungs() {
        // あがり役：一色四節高、混一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_PURE_SHIFTED_PUNGS, ChmYaku.HALF_FLUSH);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_5, JanPai.MAN_5, JanPai.MAN_5), MenTsuType.PON));
        // 手牌：[4m] [4m] [白] [白]  [2m][2m][2m] [3m][3m][3m] [5m][5m][5m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.HAKU, 2);}}, mentsuList);
        // あがり：[4m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_4, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色四歩高、平和、缺一門)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourShiftedChows1() {
        // あがり役：一色四歩高、平和、缺一門
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_SHIFTED_CHOWS, ChmYaku.ALL_CHOWS, ChmYaku.ONE_VOIDED_SUIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [3m] [3m] [4m] [4m] [5m] [5m] [6m] [7s] [7s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.SOU_7, 2);}}, mentsuList);
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色四歩高、平和、清一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFourShiftedChows2() {
        // あがり役：一色四歩高、平和、清一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.FOUR_SHIFTED_CHOWS, ChmYaku.ALL_CHOWS, ChmYaku.FULL_FLUSH);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_7, JanPai.SOU_8, JanPai.SOU_9), MenTsuType.CHI));
        // 手牌：[2s] [3s] [3s] [4s] [5s] [5s] [6s] [7s] [9s] [9s]  [7s][8s][9s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 2);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_9, 2);}}, mentsuList);
        // あがり：[1s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(組合龍)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKnittedStraight1() {
        // あがり役：組合龍、箭刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.KNITTED_STRAIGHT, ChmYaku.DRAGON_PUNG);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.HAKU, JanPai.HAKU, JanPai.HAKU), MenTsuType.PON));
        // 手牌：[1m] [4m] [2p] [5p] [8p] [3s] [6s] [9s] [9s] [9s]  [白][白][白]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_9, 3);}}, mentsuList);
        // あがり：[7m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(組合龍)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKnittedStraight2() {
        // あがり役：組合龍
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.KNITTED_STRAIGHT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_7, JanPai.MAN_8, JanPai.MAN_9), MenTsuType.CHI));
        // 手牌：[1m] [4m] [2p] [5p] [8p] [3s] [6s] [9s] [白] [白]  [7m][8m][9m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_9, 1);}
            {put(JanPai.HAKU, 2);}}, mentsuList);
        // あがり：[7m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(組合龍)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKnittedStraight3() {
        // あがり役：組合龍
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.KNITTED_STRAIGHT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_6, JanPai.MAN_7, JanPai.MAN_8), MenTsuType.CHI));
        // 手牌：[1m] [4m] [2p] [5p] [8p] [3s] [6s] [9s] [白] [白]  [6m][7m][8m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_9, 1);}
            {put(JanPai.HAKU, 2);}}, mentsuList);
        // あがり：[7m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(組合龍、平和)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKnittedStraightAndAllChows() {
        // あがり役：組合龍、平和
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.KNITTED_STRAIGHT, ChmYaku.ALL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [5m] [3p] [6p] [9p] [1s] [4s] [7s] [7s] [7s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_7, 3);}}, mentsuList);
        // あがり：[8m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_8, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(組合龍、坎張、面前清)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKnittedStraightAndClosedWait() {
        // あがり役：組合龍、平和、坎張、面前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.KNITTED_STRAIGHT, ChmYaku.ALL_CHOWS, ChmYaku.CLOSED_WAIT, ChmYaku.CONCEALED_HAND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        // 手牌：[2m] [5m] [7m] [8m] [9m] [3p] [6p] [9p] [1s] [4s] [7s] [7s] [7s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_7, 3);}}, mentsuList);
        // あがり：[8m]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_8, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(組合龍、辺張、面前清)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKnittedStraightAndEdgeWait() {
        // あがり役：組合龍、平和、辺張、面前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.KNITTED_STRAIGHT, ChmYaku.ALL_CHOWS, ChmYaku.EDGE_WAIT, ChmYaku.CONCEALED_HAND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        // 手牌：[2m] [5m] [8m] [1p] [2p] [3p] [6p] [9p] [1s] [4s] [7s] [7s] [7s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_7, 3);}}, mentsuList);
        // あがり：[3p]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_3, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(平和、断幺、和絶張)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLastTile1() {
        // あがり役：平和、断幺、和絶張
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_CHOWS, ChmYaku.ALL_SIMPLES, ChmYaku.LAST_TILE);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[4m] [5m] [4p] [5p] [6p] [6s] [6s] [6s] [7s] [8s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.SOU_6, 3);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}}, mentsuList);
        // あがり：[3m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_3, 0, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双同刻、幺九刻、幺九刻、単調将 ※ 和絶張を含まないことを確認するテスト)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLastTile2() {
        // あがり役：双同刻、幺九刻、幺九刻、単調将
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.DOUBLE_PUNG, ChmYaku.PUNG_OF_TERMINALS_OR_HONORS, ChmYaku.PUNG_OF_TERMINALS_OR_HONORS, ChmYaku.SINGLE_WAIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_9, JanPai.MAN_9, JanPai.MAN_9), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_2, JanPai.SOU_2, JanPai.SOU_2), MenTsuType.PON));
        // 手牌：[9p] [9p] [9p] [3s] [4s] [5s] [發]  [9m][9m][9m] [2s][2s][2s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_9, 3);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.HATU, 1);}}, mentsuList);
        // あがり：[發]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HATU, 0, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(小四喜、箭刻、圈風刻、門風刻、字一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLittleFourWinds() {
        // あがり役：小四喜、箭刻、圈風刻、門風刻、字一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.LITTLE_FOUR_WINDS, ChmYaku.PREVALENT_WIND, ChmYaku.SEAT_WIND, ChmYaku.DRAGON_PUNG, ChmYaku.ALL_HONORS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.TON, JanPai.TON, JanPai.TON), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.NAN, JanPai.NAN, JanPai.NAN), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SHA, JanPai.SHA, JanPai.SHA), MenTsuType.PON));
        // 手牌：[北] [北] [白] [白]  [東][東][東] [南][南][南] [西][西][西]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PEI, 2);}
            {put(JanPai.HAKU, 2);}}, mentsuList);
        // あがり：[白]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(小三元、圈風刻、門風刻、字一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsLittleThreeDragons() {
        // あがり役：小三元、圈風刻、門風刻、字一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.LITTLE_THREE_DRAGONS, ChmYaku.PREVALENT_WIND, ChmYaku.SEAT_WIND, ChmYaku.ALL_HONORS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.TON, JanPai.TON, JanPai.TON), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.HAKU, JanPai.HAKU, JanPai.HAKU), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.HATU, JanPai.HATU, JanPai.HATU), MenTsuType.PON));
        // 手牌：[南] [南] [中] [中]  [東][東][東] [白][白][白] [發][發][發]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.NAN, 2);}
            {put(JanPai.CHUN, 2);}}, mentsuList);
        // あがり：[南]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.NAN, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(全求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMeldedHand() {
        // あがり役：全求人
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MELDED_HAND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_1, JanPai.MAN_2, JanPai.MAN_3), MenTsuType.CHI));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_7, JanPai.MAN_7, JanPai.MAN_7), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_2, JanPai.PIN_3, JanPai.PIN_4), MenTsuType.CHI));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_4, JanPai.SOU_5, JanPai.SOU_6), MenTsuType.CHI));
        // 手牌：[發]  [1m][2m][3m] [7m][7m][7m] [2p][3p][4p] [4s][5s][6s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.HATU, 1);}}, mentsuList);
        // あがり：[發]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HATU, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(明槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMeldedKong() {
        // あがり役：明槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MELDED_KONG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        // 手牌：[6p] [6p] [2s] [3s]  [2m][2m][2m][2m] [3m][3m][3m] [5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(喜相逢)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMixedDoubleChow() {
        // あがり役：喜相逢
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MIXED_DOUBLE_CHOW);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_5, JanPai.MAN_6, JanPai.MAN_7), MenTsuType.CHI));
        // 手牌：[5p] [6p] [7p] [2s] [3s] [5s] [5s] [5s] [發] [發]  [5m][6m][7m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_5, 3);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三色三歩高)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMixedShiftedChows() {
        // あがり役：三色三歩高
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MIXED_SHIFTED_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[6m] [7m] [8m] [2s] [3s] [3p] [4p] [5p] [東] [東]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.TON, 2);}}, mentsuList);
        // あがり：[1s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三色三節高、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMixedShiftedPungs1() {
        // あがり役：三色三節高、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MIXED_SHIFTED_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_3, JanPai.PIN_3, JanPai.PIN_3), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_4, JanPai.SOU_4, JanPai.SOU_4), MenTsuType.PON));
        // 手牌：[3m] [4m] [8s] [8s]  [2m][2m][2m] [3p][3p][3p] [4s][4s][4s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[5m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_5, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三色三節高、幺九刻、無字)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMixedShiftedPungs2() {
        // あがり役：三色三節高、幺九刻、無字
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MIXED_SHIFTED_PUNGS, ChmYaku.PUNG_OF_TERMINALS_OR_HONORS, ChmYaku.NO_HONORS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_7, JanPai.MAN_7, JanPai.MAN_7), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_8, JanPai.PIN_8, JanPai.PIN_8), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_9, JanPai.SOU_9, JanPai.SOU_9), MenTsuType.PON));
        // 手牌：[3m] [4m] [8s] [8s]  [7m][7m][7m] [8p][8p][8p] [9s][9s][9s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[5m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_5, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(花龍)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMixedStraight() {
        // あがり役：花龍
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MIXED_STRAIGHT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [3m] [4p] [5p] [6p] [7s] [8s] [9s] [東] [東]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 1);}
            {put(JanPai.TON, 2);}}, mentsuList);
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三色三同順)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsMixedTripleChow() {
        // あがり役：三色三同順
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.MIXED_TRIPLE_CHOW);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [3m] [2p] [3p] [4p] [2s] [3s] [4s] [東] [東]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.TON, 2);}}, mentsuList);
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(清龍、九連宝燈、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsNineGates() {
        // あがり役：清龍、九連宝燈、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_STRAIGHT, ChmYaku.NINE_GATES, ChmYaku.TILE_HOG);
        // 手牌：[1m] [1m] [1m] [2m] [3m] [4m] [5m] [6m] [7m] [8m] [9m] [9m] [9m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 3);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 3);}
        });
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(無字)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsNoHonors() {
        // あがり役：無字
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.NO_HONORS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [3m] [4p] [5p] [6p] [7s] [7s] [7s] [8s] [8s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.SOU_7, 3);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(老少副、喜相逢、喜相逢、全帯幺、缺一門)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsOutSideHand() {
        // あがり役：老少副、喜相逢、喜相逢、全帯幺、缺一門
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_TERMINAL_CHOWS, ChmYaku.MIXED_DOUBLE_CHOW, ChmYaku.MIXED_DOUBLE_CHOW, ChmYaku.OUTSIDE_HAND, ChmYaku.ONE_VOIDED_SUIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_1, JanPai.MAN_2, JanPai.MAN_3), MenTsuType.CHI));
        // 手牌：[7m] [8m] [9m] [1s] [2s] [3s]  [7s] [8s] [發] [發]  [1m][2m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[9s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(圈風刻、門風刻、幺九刻)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPrevalentWindAndSeatWindAndPungOfTerminalsOrHonors1() {
        // あがり役：圈風刻、門風刻、幺九刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PUNG_OF_TERMINALS_OR_HONORS, ChmYaku.PREVALENT_WIND, ChmYaku.SEAT_WIND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.TON, JanPai.TON, JanPai.TON), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.NAN, JanPai.NAN, JanPai.NAN), MenTsuType.PON));
        // 手牌：[5p] [5p] [7s][8s]  [2m][2m][2m] [東][東][東] [南][南][南]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}}, mentsuList);
        // あがり：[9s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(圈風刻、門風刻、幺九刻)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPrevalentWindAndSeatWindAndPungOfTerminalsOrHonors2() {
        // あがり役：圈風刻、門風刻、幺九刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PUNG_OF_TERMINALS_OR_HONORS, ChmYaku.PREVALENT_WIND, ChmYaku.SEAT_WIND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_1, JanPai.MAN_1, JanPai.MAN_1), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.TON, JanPai.TON, JanPai.TON), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.NAN, JanPai.NAN, JanPai.NAN), MenTsuType.PON));
        // 手牌：[5p] [5p] [7s][8s]  [1m][1m][1m] [東][東][東] [南][南][南]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}}, mentsuList);
        // あがり：[9s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：南
        final Wind fieldWind = Wind.NAN;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(幺九刻)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPungOfTerminalsOrHonors() {
        // あがり役：幺九刻
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PUNG_OF_TERMINALS_OR_HONORS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_5, JanPai.MAN_6, JanPai.MAN_7), MenTsuType.CHI));
        // 手牌：[4m] [5m] [6m] [1p] [1p] [1p] [2s] [3s] [發] [發]  [5m][6m][7m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.PIN_1, 3);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(幺九刻、混一色、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPungOfTerminalsOrHonorsAndTileHog() {
        // あがり役：幺九刻、混一色、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PUNG_OF_TERMINALS_OR_HONORS, ChmYaku.HALF_FLUSH, ChmYaku.TILE_HOG);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PEI, JanPai.PEI, JanPai.PEI), MenTsuType.PON));
        // 手牌：[1s] [1s] [2s] [3s] [3s] [3s] [3s] [4s] [5s] [6s]  [北][北][北]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_1, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 4);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一般高)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureDoubleChow() {
        // あがり役：一般高
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_DOUBLE_CHOW);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_5, JanPai.MAN_6, JanPai.MAN_7), MenTsuType.CHI));
        // 手牌：[5m] [6m] [7m] [2p] [2p] [2p] [2s] [3s] [發] [發]  [5m][6m][7m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.PIN_2, 3);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一般高、喜相逢)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureDoubleChowAndMixedDoubleChow1() {
        // あがり役：一般高、喜相逢
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_DOUBLE_CHOW, ChmYaku.MIXED_DOUBLE_CHOW);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_5, JanPai.MAN_6, JanPai.MAN_7), MenTsuType.CHI));
        // 手牌：[5m] [6m] [7m] [2p] [3p] [4p] [5s] [6s] [發] [發]  [5m][6m][7m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[7s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_7, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一般高、喜相逢)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureDoubleChowAndMixedDoubleChow2() {
        // あがり役：一般高、喜相逢
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_DOUBLE_CHOW, ChmYaku.MIXED_DOUBLE_CHOW);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_5, JanPai.SOU_6, JanPai.SOU_7), MenTsuType.CHI));
        // 手牌：[5m] [6m] [7m] [2p] [3p] [4p] [5s] [6s] [發] [發]  [5s][6s][7s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[7s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_7, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色三歩高、平和)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureShiftedChows1() {
        // あがり役：一色三歩高、平和
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_SHIFTED_CHOWS, ChmYaku.ALL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [3m] [3m] [4m] [5m] [5p] [6p] [7p] [7s] [7s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.SOU_7, 2);}}, mentsuList);
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色三歩高、缺一門)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureShiftedChows2() {
        // あがり役：一色三歩高、缺一門
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_SHIFTED_CHOWS, ChmYaku.ONE_VOIDED_SUIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_5, JanPai.SOU_6, JanPai.SOU_7), MenTsuType.CHI));
        // 手牌：[6p] [7p] [8p] [2s] [3s] [3s] [4s] [5s] [白] [白]  [5s][6s][7s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 2);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.HAKU, 2);}}, mentsuList);
        // あがり：[1s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(碰碰和、一色三節高、双同刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureShiftedPungs1() {
        // あがり役：碰碰和、一色三節高、双同刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_PUNGS, ChmYaku.PURE_SHIFTED_PUNGS, ChmYaku.DOUBLE_PUNG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_2, JanPai.PIN_2, JanPai.PIN_2), MenTsuType.PON));
        // 手牌：[4m] [4m] [8s] [8s]  [2m][2m][2m] [3m][3m][3m] [2p][2p][2p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[4m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_4, 1, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(碰碰和、一色三節高、双同刻、断幺、面前清) ※ 一色三同順と判定するバグ
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureShiftedPungs2() {
        // あがり役：碰碰和、一色三節高、双同刻、断幺、面前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_PUNGS, ChmYaku.PURE_SHIFTED_PUNGS, ChmYaku.DOUBLE_PUNG, ChmYaku.ALL_SIMPLES, ChmYaku.CONCEALED_HAND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        // 手牌：[2m] [2m] [2m] [3m] [3m] [3m] [4m] [4m] [2p] [2p] [2p] [8s] [8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 3);}
            {put(JanPai.MAN_3, 3);}
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.PIN_2, 3);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[4m]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_4, 1, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(清龍、平和)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureStraight() {
        // あがり役：清龍、平和
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_STRAIGHT, ChmYaku.ALL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[1p] [1p] [2s] [3s] [4s] [5s] [6s] [7s] [8s] [9s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 1);}}, mentsuList);
        // あがり：[1s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_1, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色双龍会)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureTerminalChows() {
        // あがり役：一色双龍会
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_TERMINAL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_1, JanPai.MAN_2, JanPai.MAN_3), MenTsuType.CHI));
        // 手牌：[2m] [3m] [5m] [5m] [7m] [7m] [8m] [8m] [9m] [9m]  [1m][2m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_7, 2);}
            {put(JanPai.MAN_8, 2);}
            {put(JanPai.MAN_9, 2);}}, mentsuList);
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色双龍会、坎張)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureTerminalChowsAndClosedWait() {
        // あがり役：一色双龍会、坎張
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_TERMINAL_CHOWS, ChmYaku.CLOSED_WAIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_1, JanPai.MAN_2, JanPai.MAN_3), MenTsuType.CHI));
        // 手牌：[1m] [3m] [5m] [5m] [7m] [7m] [8m] [8m] [9m] [9m]  [1m][2m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_7, 2);}
            {put(JanPai.MAN_8, 2);}
            {put(JanPai.MAN_9, 2);}}, mentsuList);
        // あがり：[2m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_2, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色双龍会、門前清)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureTerminalChowsAndConcealedHand() {
        // あがり役：一色双龍会、門前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_TERMINAL_CHOWS, ChmYaku.CONCEALED_HAND);
        // 手牌：[1m] [2m] [2m] [3m] [3m] [5m] [5m] [7m] [7m] [8m] [8m] [9m] [9m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_7, 2);}
            {put(JanPai.MAN_8, 2);}
            {put(JanPai.MAN_9, 2);}
        });
        // あがり：[1m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_1, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色三同順、平和)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsPureTripleChow() {
        // あがり役：一色三同順、平和
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.PURE_TRIPLE_CHOW, ChmYaku.ALL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [2m] [3m] [3m] [4m] [4m] [1p] [1p] [7s] [8s]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}}, mentsuList);
        // あがり：[9s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色四同順、混一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsQuadrupleChow() {
        // あがり役：一色四同順、混一色
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.QUADRUPLE_CHOW, ChmYaku.HALF_FLUSH);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_3, JanPai.MAN_4), MenTsuType.CHI));
        // 手牌：[2m] [2m] [3m] [3m] [3m] [4m] [4m] [4m] [東] [東]  [2m][3m][4m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 3);}
            {put(JanPai.MAN_4, 3);}
            {put(JanPai.TON, 2);}}, mentsuList);
        // あがり：[2m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_2, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(一色四同順、混一色、門前清)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsQuadrupleChowAndConcealedHand() {
        // あがり役：一色四同順、混一色、門前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.QUADRUPLE_CHOW, ChmYaku.HALF_FLUSH, ChmYaku.CONCEALED_HAND);
        // 手牌：[2m] [2m] [2m] [3m] [3m] [3m] [3m] [4m] [4m] [4m] [4m] [東] [東]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 3);}
            {put(JanPai.MAN_3, 4);}
            {put(JanPai.MAN_4, 4);}
            {put(JanPai.TON, 2);}
        });
        // あがり：[2m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_2, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(七対、推不倒、清一色)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsReversibleTilesAndFullFlush() {
        // あがり役：七対、清一色、推不倒
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.FULL_FLUSH, ChmYaku.REVERSIBLE_TILES);
        // 手牌：[1p] [1p] [2p] [2p] [3p] [3p] [4p] [4p] [5p] [5p] [8p] [8p] [9p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.PIN_2, 2);}
            {put(JanPai.PIN_3, 2);}
            {put(JanPai.PIN_4, 2);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.PIN_8, 2);}
            {put(JanPai.PIN_9, 1);}
        });
        // あがり：[9p]面前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_9, 2, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(連六)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsShortStraight() {
        // あがり役：連六
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SHORT_STRAIGHT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_4, JanPai.MAN_5, JanPai.MAN_6), MenTsuType.CHI));
        // 手牌：[1m] [2m] [3m] [5s] [6s] [7s] [7p] [8p] [發] [發]  [4m][5m][6m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[9p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_9, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(単調将)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsSingleWait() {
        // あがり役：単調将
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SINGLE_WAIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_4, JanPai.MAN_5), MenTsuType.CHI));
        // 手牌：[1m] [2m] [3m] [5s] [6s] [7s] [7p] [8p] [9p] [發]  [3m][4m][5m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.HATU, 1);}}, mentsuList);
        // あがり：[發]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.HATU, 2, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三暗刻、断幺、門前清)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeConcealedPungs() {
        // あがり役：三暗刻、断幺、門前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.CONCEALED_HAND);
        // 手牌：[2m] [2m] [2m] [3m] [3m] [3m] [5p] [5p] [5p] [6p] [6p] [2s] [3s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 3);}
            {put(JanPai.MAN_3, 3);}
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
        });
        // あがり：[4s]門前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeKongs() {
        // あがり役：三槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_KONGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        // 手牌：[6p] [6p] [2s] [3s]  [2m][2m][2m][2m] [3m][3m][3m][3m] [5p][5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三槓、暗槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeKongsAndConcealedKong() {
        // あがり役：三槓、暗槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_KONGS, ChmYaku.CONCEALED_KONG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        // 手牌：[6p] [6p] [2s] [3s]  [■][2m][2m][■] [3m][3m][3m][3m] [5p][5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(碰碰和、三槓、暗槓、双暗刻、断幺、自摸)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeKongsAndConcealedKongAndTwoConcealedPungs() {
        // あがり役：碰碰和、三槓、暗槓、双暗刻、断幺、自摸
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_PUNGS, ChmYaku.THREE_KONGS, ChmYaku.CONCEALED_KONG, ChmYaku.TWO_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.SELF_DRAWN);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        // 手牌：[6p] [6p] [8s] [8s]  [■][2m][2m][■] [3m][3m][3m][3m] [5p][5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.TSUMO_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三槓、四暗刻、断幺、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeKongsAndFourConcealedPungs() {
        // あがり役：三槓、四暗刻、断幺、不求人
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_KONGS, ChmYaku.FOUR_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.FULLY_CONCEALED);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_DARK));
        // 手牌：[6p] [6p] [8s] [8s]  [■][2m][2m][■] [■][3m][3m][■] [■][5p][5p][■]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]門前ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.TSUMO_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三槓、三暗刻、断幺、門前清)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeKongsAndThreeConcealedPungs() {
        // あがり役：三槓、三暗刻、断幺、門前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_KONGS, ChmYaku.THREE_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.CONCEALED_HAND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_DARK));
        // 手牌：[6p] [6p] [2s] [3s]  [■][2m][2m][■] [■][3m][3m][■] [■][5p][5p][■]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]門前ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三槓、双暗槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeKongsAndTwoConcealedKongs() {
        // あがり役：三槓、双暗槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_KONGS, ChmYaku.TWO_CONCEALED_KONGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        // 手牌：[6p] [6p] [2s] [3s]  [■][2m][2m][■] [■][3m][3m][■] [5p][5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(碰碰和、三槓、双暗槓、三暗刻、断幺、自摸)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeKongsAndTwoConcealedKongsAndThreeConcealedPungs() {
        // あがり役：碰碰和、三槓、双暗槓、三暗刻、断幺、自摸
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_PUNGS, ChmYaku.THREE_KONGS, ChmYaku.TWO_CONCEALED_KONGS, ChmYaku.THREE_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.SELF_DRAWN);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.KAN_LIGHT));
        // 手牌：[6p] [6p] [8s] [8s]  [■][2m][2m][■] [■][3m][3m][■] [5p][5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.TSUMO_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三色双龍会)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeSuitedTerminalChows() {
        // あがり役：三色双龍会
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_SUITED_TERMINAL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_1, JanPai.MAN_2, JanPai.MAN_3), MenTsuType.CHI));
        // 手牌：[7m] [8m] [9m] [5p] [5p] [1s] [2s] [3s] [7s] [8s]  [1m][2m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}}, mentsuList);
        // あがり：[9s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_9, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三色双龍会、辺張)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsThreeSuitedTerminalChowsAndEdgeWait() {
        // あがり役：三色双龍会、辺張
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THREE_SUITED_TERMINAL_CHOWS, ChmYaku.EDGE_WAIT);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_1, JanPai.MAN_2, JanPai.MAN_3), MenTsuType.CHI));
        // 手牌：[7m] [8m] [9m] [5p] [5p] [1s] [2s] [3s] [8s] [9s]  [1m][2m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 1);}}, mentsuList);
        // あがり：[7s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_7, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(七対、四帰一、四帰一)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTileHog() {
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三同刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTriplePung1() {
        // あがり役：三同刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TRIPLE_PUNG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_2, JanPai.PIN_2, JanPai.PIN_2), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_2, JanPai.SOU_2, JanPai.SOU_2), MenTsuType.PON));
        // 手牌：[3m] [4m] [8s] [8s]  [2m][2m][2m] [2p][2p][2p] [2s][2s][2s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[5m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_5, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(三同刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTriplePung2() {
        // あがり役：三同刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TRIPLE_PUNG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_8, JanPai.MAN_8, JanPai.MAN_8), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_8, JanPai.PIN_8, JanPai.PIN_8), MenTsuType.PON));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.SOU_8, JanPai.SOU_8, JanPai.SOU_8), MenTsuType.PON));
        // 手牌：[3m] [4m] [6s] [6s]  [8m][8m][8m] [8p][8p][8p] [8s][8s][8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.SOU_6, 2);}}, mentsuList);
        // あがり：[5m]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_5, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双暗槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoConcealedKongs() {
        // あがり役：双暗槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_CONCEALED_KONGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        // 手牌：[6p] [6p] [2s] [3s]  [■][2m][2m][■] [■][3m][3m][■] [5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双暗槓、四暗刻、断幺、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoConcealedKongsAndFourConcealedPungs() {
        // あがり役：双暗槓、四暗刻、断幺、不求人
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_CONCEALED_KONGS, ChmYaku.FOUR_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.FULLY_CONCEALED);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        // 手牌：[5p] [5p] [5p] [6p] [6p] [8s] [8s]  [■][2m][2m][■] [■][3m][3m][■]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]門前ツモ
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.TSUMO_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双暗槓、三暗刻、断幺、門前清)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoConcealedKongsAndThreeConcealedPungs() {
        // あがり役：双暗槓、三暗刻、断幺、門前清
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_CONCEALED_KONGS, ChmYaku.THREE_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.CONCEALED_HAND);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_DARK));
        // 手牌：[5p] [5p] [5p] [6p] [6p] [2s] [3s]  [■][2m][2m][■] [■][3m][3m][■]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双暗刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoConcealedPungs1() {
        // あがり役：双暗刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.PON));
        // 手牌：[2m] [2m] [2m] [5p] [5p] [5p] [6p] [6p] [2s] [3s]  [3m][3m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_2, 3);}
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双暗刻、断幺、清一色、四帰一、四帰一) ※ 双暗刻が判定されないバグ
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoConcealedPungs2() {
        // あがり役：双暗刻、断幺、清一色、四帰一、四帰一
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_CONCEALED_KONGS, ChmYaku.ALL_SIMPLES, ChmYaku.FULL_FLUSH, ChmYaku.TILE_HOG, ChmYaku.TILE_HOG);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        // 手牌：[2p] [2p] [6p] [7p] [7p] [7p] [7p] [8p] [8p] [8p]  [5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_2, 2);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_7, 4);}
            {put(JanPai.PIN_8, 3);}}, mentsuList);
        // あがり：[8p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_8, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双明槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoMeldedKongs() {
        // あがり役：双明槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_MELDED_KONGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        // 手牌：[6p] [6p] [2s] [3s]  [2m][2m][2m][2m] [3m][3m][3m][3m] [5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双明槓、暗槓、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoMeldedKongsAndConcealedKong() {
        // あがり役：双明槓、暗槓、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_MELDED_KONGS, ChmYaku.CONCEALED_KONG, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.PIN_5, JanPai.PIN_5, JanPai.PIN_5), MenTsuType.PON));
        // 手牌：[6p] [6p] [2s] [3s]  [■][2m][2m][■] [3m][3m][3m][3m] [5p][5p][5p]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(碰碰和、双明槓、暗槓、三暗刻、断幺、自摸)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoMeldedKongsAndConcealedKongAndThreeConcealedPungs() {
        // あがり役：碰碰和、双明槓、暗槓、三暗刻、断幺、自摸
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.ALL_PUNGS, ChmYaku.TWO_MELDED_KONGS, ChmYaku.CONCEALED_KONG, ChmYaku.THREE_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES, ChmYaku.SELF_DRAWN);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        // 手牌：[5p] [5p] [5p] [6p] [6p] [8s] [8s]  [■][2m][2m][■] [3m][3m][3m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_8, 2);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 1, CompleteType.TSUMO_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(双明槓、暗槓、双暗刻、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoMeldedKongsAndConcealedKongAndTwoConcealedPungs() {
        // あがり役：双明槓、暗槓、双暗刻、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_MELDED_KONGS, ChmYaku.CONCEALED_KONG, ChmYaku.TWO_CONCEALED_PUNGS, ChmYaku.ALL_SIMPLES);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2, JanPai.MAN_2), MenTsuType.KAN_DARK));
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3, JanPai.MAN_3), MenTsuType.KAN_LIGHT));
        // 手牌：[5p] [5p] [5p] [6p] [6p] [2s] [3s]  [■][2m][2m][■] [3m][3m][3m][3m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_5, 3);}
            {put(JanPai.PIN_6, 2);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}}, mentsuList);
        // あがり：[4s]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.SOU_4, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(老少副)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsTwoTerminalChows() {
        // あがり役：老少副
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.TWO_TERMINAL_CHOWS);
        final List<MenTsu> mentsuList = new ArrayList<MenTsu>();
        mentsuList.add(new MenTsu(Arrays.asList(JanPai.MAN_7, JanPai.MAN_8, JanPai.MAN_9), MenTsuType.CHI));
        // 手牌：[1m] [2m] [3m] [4s] [5s] [6s] [7p] [8p] [發] [發]  [7m][8m][9m]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.HATU, 2);}}, mentsuList);
        // あがり：[6p]ロン
        final CompleteJanPai pai = new CompleteJanPai(JanPai.PIN_6, 3, CompleteType.RON_NOT_MENZEN);
        // 自風：東
        final Wind playerWind = Wind.TON;
        // 場風：東
        final Wind fieldWind = Wind.TON;
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

    /**
     * getCompleteInfo()のテスト(七対、大于五、断幺)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsUpperFourAndAllSimples() {
        // あがり役：七対、大于五、断幺
        final List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.UPPER_FOUR, ChmYaku.ALL_SIMPLES);
        // 手牌：[6m] [6m] [7m] [7m] [8m] [8m] [6p] [6p] [6s] [6s] [7s] [7s] [8s]
        final Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_6, 2);}
            {put(JanPai.MAN_7, 2);}
            {put(JanPai.MAN_8, 2);}
            {put(JanPai.PIN_6, 2);}
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
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
        final List<ChmYaku> resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }

}

