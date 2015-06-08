/**
 * ChmHandCheckUtilTest.java
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
import java.util.Map;

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
public final class ChmHandCheckUtilTest {
    
    /**
     * isComplete()のテスト(組合龍、余剰面子が順子)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKumiairyu1() {
        // あがり牌：[1s] [4s]
        List<JanPai> expectedResultList = Arrays.asList(JanPai.SOU_1, JanPai.SOU_4);
        // 手牌：[1s] [2s] [3s] [4s] [7s] [2p] [5p] [8p] [3m] [6m] [9m] [西] [西]
        Map<JanPai, Integer> hand = new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.SHA, 2);}
        };
        List<JanPai> resultList = new ArrayList<JanPai>();
        resultList = ChmHandCheckUtil.getCompletableJanPaiList(hand);
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * isComplete()のテスト(組合龍、余剰面子が刻子)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsKumiairyu2() {
        // あがり牌：[白] [中]
        List<JanPai> expectedResultList = Arrays.asList(JanPai.HAKU, JanPai.CHUN);
        // 手牌：[1p] [4p] [7p] [2m] [5m] [8m] [3s] [6s] [9s] [白] [白] [中] [中]
        Map<JanPai, Integer> hand = new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_9, 1);}
            {put(JanPai.HAKU, 2);}
            {put(JanPai.CHUN, 2);}
        };
        List<JanPai> resultList = new ArrayList<JanPai>();
        resultList = ChmHandCheckUtil.getCompletableJanPaiList(hand);
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * isZenhukou()のテスト(数牌6枚=七星不靠確定)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsZenhukouSuPai6() {
        // あがり牌：[7m] [8p] [9s]
        List<JanPai> expectedResultList = Arrays.asList(JanPai.MAN_7, JanPai.PIN_8, JanPai.SOU_9);
        // 手牌：[1m] [4m] [2p] [5p] [3s] [6s] [東] [南] [西] [北] [白] [發] [中]
        Map<JanPai, Integer> hand = new HashMap<JanPai, Integer>() {
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
        };
        List<JanPai> resultList = new ArrayList<JanPai>();
        resultList = ChmHandCheckUtil.getCompletableJanPaiList(hand);
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * isZenhukou()のテスト(数牌7枚)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsZenhukouSuPai7() {
        // あがり牌：[4m] [5s] [東]
        List<JanPai> expectedResultList = Arrays.asList(JanPai.MAN_4, JanPai.SOU_5, JanPai.TON);
        // 手牌：[1m] [7m] [2s] [8s] [3p] [6p] [9p] [南] [西] [北] [白] [發] [中]
        Map<JanPai, Integer> hand = new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.NAN, 1);}
            {put(JanPai.SHA, 1);}
            {put(JanPai.PEI, 1);}
            {put(JanPai.HAKU, 1);}
            {put(JanPai.HATU, 1);}
            {put(JanPai.CHUN, 1);}
        };
        List<JanPai> resultList = new ArrayList<JanPai>();
        resultList = ChmHandCheckUtil.getCompletableJanPaiList(hand);
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * isZenhukou()のテスト(数牌8枚)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsZenhukouSuPai8() {
        // あがり牌：[3p] [南] [北]
        List<JanPai> expectedResultList = Arrays.asList(JanPai.PIN_3, JanPai.NAN, JanPai.PEI);
        // 手牌：[1s] [4s] [7s] [2m] [5m] [8m] [6p] [9p] [東] [西] [白] [發] [中]
        Map<JanPai, Integer> hand = new HashMap<JanPai, Integer>() {
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
        };
        List<JanPai> resultList = new ArrayList<JanPai>();
        resultList = ChmHandCheckUtil.getCompletableJanPaiList(hand);
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * isZenhukou()のテスト(数牌9枚=組合龍複合確定)
     */
    @SuppressWarnings("serial")
    @Test
    public void testIsZenhukouSuPai9() {
        // あがり牌：[白] [發] [中]
        List<JanPai> expectedResultList = Arrays.asList(JanPai.HAKU, JanPai.HATU, JanPai.CHUN);
        // 手牌：[1p] [4p] [7p] [2s] [5s] [8s] [3m] [6m] [9m] [東] [南] [西] [北]
        Map<JanPai, Integer> hand = new HashMap<JanPai, Integer>() {
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
        };
        List<JanPai> resultList = new ArrayList<JanPai>();
        resultList = ChmHandCheckUtil.getCompletableJanPaiList(hand);
        assertTrue(resultList.equals(expectedResultList));
    }
    
    /**
     * getCompleteInfo()のテスト(七対)
     */
    @SuppressWarnings("serial")
    @Test
    public void testChmYakuNanatsui1() {
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
     * getCompleteInfo()のテスト(七対、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testChmYakuNanatsui2() {
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
     * getCompleteInfo()のテスト(連七対)
     */
    @SuppressWarnings("serial")
    @Test
    public void testChmYakuNanatsui3() {
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
     * getCompleteInfo()のテスト(七星不靠)
     */
    @SuppressWarnings("serial")
    @Test
    public void testChmYakuZenhukouSupai6() {
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
    public void testChmYakuZenhukouSupai8() {
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
    public void testChmYakuZenhukouSupai9() {
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
     * getCompleteInfo()のテスト(十三幺、不求人)
     */
    @SuppressWarnings("serial")
    @Test
    public void testChmYakuKokushi() {
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

