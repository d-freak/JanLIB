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
import wiz.project.jan.Wind;



/**
 * ChmHandCheckUtilのテスト
 */
public final class ChmYakuCheckUtilTest {
    
    /**
     * getCompleteInfo()のテスト(七対、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsFullyConcealed() {
        // あがり役：七対、不求人
        List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS, ChmYaku.FULLY_CONCEALED);
        // 手牌：[3m] [3m] [4p] [4p] [7p] [7p] [5s] [5s] [6s] [6s] [白] [白] [中]
        Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.PIN_4, 2);}
            {put(JanPai.PIN_7, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_6, 2);}
            {put(JanPai.HAKU, 2);}
            {put(JanPai.CHUN, 1);}
        });
        // あがり：[中]面前ツモ
        CompleteJanPai pai = new CompleteJanPai(JanPai.CHUN, CompleteType.TSUMO_MENZEN);
        // 自風：東
        Wind playerWind = Wind.TON;
        // 場風：東
        Wind fieldWind = Wind.TON;
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
        List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.GREATER_HONORS_AND_KNITTED_TILES);
        // 手牌：[1m] [4m] [2p] [5p] [3s] [6s] [東] [南] [西] [北] [白] [發] [中]
        Hand hand = new Hand(new HashMap<JanPai, Integer>() {
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
        CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, CompleteType.RON_MENZEN);
        // 自風：東
        Wind playerWind = Wind.TON;
        // 場風：東
        Wind fieldWind = Wind.TON;
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
        List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.LESSER_HONORS_AND_KNITTED_TILES);
        // 手牌：[1s] [4s] [7s] [2m] [5m] [8m] [6p] [9p] [東] [西] [白] [發] [中]
        Hand hand = new Hand(new HashMap<JanPai, Integer>() {
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
        CompleteJanPai pai = new CompleteJanPai(JanPai.NAN, CompleteType.RON_MENZEN);
        // 自風：東
        Wind playerWind = Wind.TON;
        // 場風：東
        Wind fieldWind = Wind.TON;
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
        List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.LESSER_HONORS_AND_KNITTED_TILES, ChmYaku.KNITTED_STRAIGHT);
        // 手牌：[1p] [4p] [7p] [2s] [5s] [8s] [3m] [6m] [9m] [東] [南] [西] [北]
        Hand hand = new Hand(new HashMap<JanPai, Integer>() {
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
        CompleteJanPai pai = new CompleteJanPai(JanPai.HAKU, CompleteType.RON_MENZEN);
        // 自風：東
        Wind playerWind = Wind.TON;
        // 場風：東
        Wind fieldWind = Wind.TON;
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
        List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_PAIRS);
        // 手牌：[1m] [1m] [9m] [9m] [2p] [2p] [8s] [8s] [東] [東] [西] [西] [北]
        Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_9, 2);}
            {put(JanPai.PIN_2, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.TON, 2);}
            {put(JanPai.SHA, 2);}
            {put(JanPai.PEI, 1);}
        });
        // あがり：[北]面前ロン
        CompleteJanPai pai = new CompleteJanPai(JanPai.PEI, CompleteType.RON_MENZEN);
        // 自風：東
        Wind playerWind = Wind.TON;
        // 場風：東
        Wind fieldWind = Wind.TON;
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
        List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.SEVEN_SHIFTED_PAIRS);
        // 手牌：[1m] [1m] [2m] [2m] [3m] [3m] [4m] [4m] [5m] [5m] [6m] [6m] [7m]
        Hand hand = new Hand(new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.MAN_4, 2);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_6, 2);}
            {put(JanPai.MAN_7, 1);}
        });
        // あがり：[7m]面前ロン
        CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_7, CompleteType.RON_MENZEN);
        // 自風：東
        Wind playerWind = Wind.TON;
        // 場風：東
        Wind fieldWind = Wind.TON;
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
        List<ChmYaku> expectedResultList = Arrays.asList(ChmYaku.THIRTEEN_ORPHANS, ChmYaku.FULLY_CONCEALED);
        // 手牌：[1m] [9m] [1p] [9p] [1s] [9s] [東] [南] [西] [北] [白] [發] [中]
        Hand hand = new Hand(new HashMap<JanPai, Integer>() {
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
        CompleteJanPai pai = new CompleteJanPai(JanPai.MAN_9, CompleteType.TSUMO_MENZEN);
        // 自風：東
        Wind playerWind = Wind.TON;
        // 場風：東
        Wind fieldWind = Wind.TON;
        List<ChmYaku> resultList = new ArrayList<ChmYaku>();
        resultList = ChmHandCheckUtil.getCompleteInfo(hand, pai, playerWind, fieldWind).getYakuList();
        assertTrue(resultList.equals(expectedResultList));
    }
    
}

