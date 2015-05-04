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

import wiz.project.jan.JanPai;



/**
 * ChmHandCheckUtilのテスト
 */
public final class ChmHandCheckUtilTest {
    
    /**
     * isComplete()のテスト(組合竜、余剰面子が順子)
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
     * isComplete()のテスト(組合竜、余剰面子が刻子)
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
     * isZenhukou()のテスト(数牌9枚=組合竜複合確定)
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
    
}

