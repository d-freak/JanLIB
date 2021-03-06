/**
 * JanPai.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * 牌
 */
public enum JanPai {
    // 萬子
    MAN_1,
    MAN_2,
    MAN_3,
    MAN_4,
    MAN_5,
    MAN_6,
    MAN_7,
    MAN_8,
    MAN_9,
    
    // 筒子
    PIN_1,
    PIN_2,
    PIN_3,
    PIN_4,
    PIN_5,
    PIN_6,
    PIN_7,
    PIN_8,
    PIN_9,
    
    // 索子
    SOU_1,
    SOU_2,
    SOU_3,
    SOU_4,
    SOU_5,
    SOU_6,
    SOU_7,
    SOU_8,
    SOU_9,
    
    // 風牌
    TON,
    NAN,
    SHA,
    PEI,
    
    // 三元牌
    HAKU,
    HATU,
    CHUN;
    
    
    
    /**
     * 他色の牌リストを取得
     * 
     * @return 他色の牌リスト。
     */
    public List<JanPai> getMixedJanPaiList() {
        final List<JanPai> paiList = new ArrayList<JanPai>();
        
        switch (this) {
        case MAN_1:
            paiList.addAll(Arrays.asList(PIN_1, SOU_1));
            break;
        case MAN_2:
            paiList.addAll(Arrays.asList(PIN_2, SOU_2));
            break;
        case MAN_3:
            paiList.addAll(Arrays.asList(PIN_3, SOU_3));
            break;
        case MAN_4:
            paiList.addAll(Arrays.asList(PIN_4, SOU_4));
            break;
        case MAN_5:
            paiList.addAll(Arrays.asList(PIN_5, SOU_5));
            break;
        case MAN_6:
            paiList.addAll(Arrays.asList(PIN_6, SOU_6));
            break;
        case MAN_7:
            paiList.addAll(Arrays.asList(PIN_7, SOU_7));
            break;
        case MAN_8:
            paiList.addAll(Arrays.asList(PIN_8, SOU_8));
            break;
        case MAN_9:
            paiList.addAll(Arrays.asList(PIN_9, SOU_9));
            break;
        case PIN_1:
            paiList.addAll(Arrays.asList(SOU_1, MAN_1));
            break;
        case PIN_2:
            paiList.addAll(Arrays.asList(SOU_2, MAN_2));
            break;
        case PIN_3:
            paiList.addAll(Arrays.asList(SOU_3, MAN_3));
            break;
        case PIN_4:
            paiList.addAll(Arrays.asList(SOU_4, MAN_4));
            break;
        case PIN_5:
            paiList.addAll(Arrays.asList(SOU_5, MAN_5));
            break;
        case PIN_6:
            paiList.addAll(Arrays.asList(SOU_6, MAN_6));
            break;
        case PIN_7:
            paiList.addAll(Arrays.asList(SOU_7, MAN_7));
            break;
        case PIN_8:
            paiList.addAll(Arrays.asList(SOU_8, MAN_8));
            break;
        case PIN_9:
            paiList.addAll(Arrays.asList(SOU_9, MAN_9));
            break;
        case SOU_1:
            paiList.addAll(Arrays.asList(MAN_1, PIN_1));
            break;
        case SOU_2:
            paiList.addAll(Arrays.asList(MAN_2, PIN_2));
            break;
        case SOU_3:
            paiList.addAll(Arrays.asList(MAN_3, PIN_3));
            break;
        case SOU_4:
            paiList.addAll(Arrays.asList(MAN_4, PIN_4));
            break;
        case SOU_5:
            paiList.addAll(Arrays.asList(MAN_5, PIN_5));
            break;
        case SOU_6:
            paiList.addAll(Arrays.asList(MAN_6, PIN_6));
            break;
        case SOU_7:
            paiList.addAll(Arrays.asList(MAN_7, PIN_7));
            break;
        case SOU_8:
            paiList.addAll(Arrays.asList(MAN_8, PIN_8));
            break;
        case SOU_9:
            paiList.addAll(Arrays.asList(MAN_9, PIN_9));
            break;
        default:
            break;
        }
        return paiList;
    }
    
    /**
     * 他色の次の牌リストを取得
     * 
     * @return 他色の次の牌リスト。
     */
    public List<JanPai> getMixedNextJanPaiList() {
        final List<JanPai> paiList = new ArrayList<JanPai>();
        
        switch (this) {
        case MAN_1:
            paiList.addAll(Arrays.asList(PIN_2, SOU_2));
            break;
        case MAN_2:
            paiList.addAll(Arrays.asList(PIN_3, SOU_3));
            break;
        case MAN_3:
            paiList.addAll(Arrays.asList(PIN_4, SOU_4));
            break;
        case MAN_4:
            paiList.addAll(Arrays.asList(PIN_5, SOU_5));
            break;
        case MAN_5:
            paiList.addAll(Arrays.asList(PIN_6, SOU_6));
            break;
        case MAN_6:
            paiList.addAll(Arrays.asList(PIN_7, SOU_7));
            break;
        case MAN_7:
            paiList.addAll(Arrays.asList(PIN_8, SOU_8));
            break;
        case MAN_8:
            paiList.addAll(Arrays.asList(PIN_9, SOU_9));
            break;
        case PIN_1:
            paiList.addAll(Arrays.asList(SOU_2, MAN_2));
            break;
        case PIN_2:
            paiList.addAll(Arrays.asList(SOU_3, MAN_3));
            break;
        case PIN_3:
            paiList.addAll(Arrays.asList(SOU_4, MAN_4));
            break;
        case PIN_4:
            paiList.addAll(Arrays.asList(SOU_5, MAN_5));
            break;
        case PIN_5:
            paiList.addAll(Arrays.asList(SOU_6, MAN_6));
            break;
        case PIN_6:
            paiList.addAll(Arrays.asList(SOU_7, MAN_7));
            break;
        case PIN_7:
            paiList.addAll(Arrays.asList(SOU_8, MAN_8));
            break;
        case PIN_8:
            paiList.addAll(Arrays.asList(SOU_9, MAN_9));
            break;
        case SOU_1:
            paiList.addAll(Arrays.asList(MAN_2, PIN_2));
            break;
        case SOU_2:
            paiList.addAll(Arrays.asList(MAN_3, PIN_3));
            break;
        case SOU_3:
            paiList.addAll(Arrays.asList(MAN_4, PIN_4));
            break;
        case SOU_4:
            paiList.addAll(Arrays.asList(MAN_5, PIN_5));
            break;
        case SOU_5:
            paiList.addAll(Arrays.asList(MAN_6, PIN_6));
            break;
        case SOU_6:
            paiList.addAll(Arrays.asList(MAN_7, PIN_7));
            break;
        case SOU_7:
            paiList.addAll(Arrays.asList(MAN_8, PIN_8));
            break;
        case SOU_8:
            paiList.addAll(Arrays.asList(MAN_9, PIN_9));
            break;
        default:
            break;
        }
        return paiList;
    }
    
    /**
     * 次の牌を取得
     * 
     * @return 次の牌。存在しなければnullを返す。
     */
    public JanPai getNext() {
        switch (this) {
        case MAN_1:
            return MAN_2;
        case MAN_2:
            return MAN_3;
        case MAN_3:
            return MAN_4;
        case MAN_4:
            return MAN_5;
        case MAN_5:
            return MAN_6;
        case MAN_6:
            return MAN_7;
        case MAN_7:
            return MAN_8;
        case MAN_8:
            return MAN_9;
        case MAN_9:
            return MAN_1;
        case PIN_1:
            return PIN_2;
        case PIN_2:
            return PIN_3;
        case PIN_3:
            return PIN_4;
        case PIN_4:
            return PIN_5;
        case PIN_5:
            return PIN_6;
        case PIN_6:
            return PIN_7;
        case PIN_7:
            return PIN_8;
        case PIN_8:
            return PIN_9;
        case PIN_9:
            return PIN_1;
        case SOU_1:
            return SOU_2;
        case SOU_2:
            return SOU_3;
        case SOU_3:
            return SOU_4;
        case SOU_4:
            return SOU_5;
        case SOU_5:
            return SOU_6;
        case SOU_6:
            return SOU_7;
        case SOU_7:
            return SOU_8;
        case SOU_8:
            return SOU_9;
        case SOU_9:
            return SOU_1;
        case TON:
        	return NAN;
        case NAN:
        	return SHA;
        case SHA:
        	return PEI;
        case PEI:
        	return TON;
        case HAKU:
        	return HATU;
        case HATU:
        	return CHUN;
        case CHUN:
        	return HAKU;
        default:
            throw new InternalError();
        }
    }
    
    /**
     * 前の牌を取得
     * 
     * @return 前の牌。存在しなければnullを返す。
     */
    public JanPai getPrev() {
        switch (this) {
        case MAN_1:
            return MAN_9;
        case MAN_2:
            return MAN_1;
        case MAN_3:
            return MAN_2;
        case MAN_4:
            return MAN_3;
        case MAN_5:
            return MAN_4;
        case MAN_6:
            return MAN_5;
        case MAN_7:
            return MAN_6;
        case MAN_8:
            return MAN_7;
        case MAN_9:
            return MAN_8;
        case PIN_1:
            return PIN_9;
        case PIN_2:
            return PIN_1;
        case PIN_3:
            return PIN_2;
        case PIN_4:
            return PIN_3;
        case PIN_5:
            return PIN_4;
        case PIN_6:
            return PIN_5;
        case PIN_7:
            return PIN_6;
        case PIN_8:
            return PIN_7;
        case PIN_9:
            return PIN_8;
        case SOU_1:
            return SOU_9;
        case SOU_2:
            return SOU_1;
        case SOU_3:
            return SOU_2;
        case SOU_4:
            return SOU_3;
        case SOU_5:
            return SOU_4;
        case SOU_6:
            return SOU_5;
        case SOU_7:
            return SOU_6;
        case SOU_8:
            return SOU_7;
        case SOU_9:
            return SOU_8;
        case TON:
        	return PEI;
        case NAN:
        	return TON;
        case SHA:
        	return NAN;
        case PEI:
        	return SHA;
        case HAKU:
        	return CHUN;
        case HATU:
        	return HAKU;
        case CHUN:
        	return HATU;
        default:
            throw new InternalError();
        }
    }
    
    /**
     * 牌タイプを取得
     * 
     * @return 牌タイプ
     */
    public JanPaiType getType() {
        switch (this) {
        case MAN_1:
        case MAN_2:
        case MAN_3:
        case MAN_4:
        case MAN_5:
        case MAN_6:
        case MAN_7:
        case MAN_8:
        case MAN_9:
            return JanPaiType.MAN;
        case PIN_1:
        case PIN_2:
        case PIN_3:
        case PIN_4:
        case PIN_5:
        case PIN_6:
        case PIN_7:
        case PIN_8:
        case PIN_9:
            return JanPaiType.PIN;
        case SOU_1:
        case SOU_2:
        case SOU_3:
        case SOU_4:
        case SOU_5:
        case SOU_6:
        case SOU_7:
        case SOU_8:
        case SOU_9:
            return JanPaiType.SOU;
        case TON:
        case NAN:
        case SHA:
        case PEI:
            return JanPaiType.JI_WIND;
        default:
            return JanPaiType.JI_DORAGON;
        }
    }
    
    /**
     * 坎張の牌か
     * 
     * @param shuntsu 順子。
     * @return 判定結果。
     */
    public boolean isClosed(final MenTsu shuntsu) {
        if (!shuntsu.isShunTsu()) {
            return false;
        }
        final boolean isCalled = shuntsu.getMenTsuType().isCalled();
        
        if (isCalled) {
            return false;
        }
        final JanPai middle = shuntsu.getMiddle();
        
        if (this.equals(middle)) {
            return true;
        }
        return false;
    }
    
    /**
     * 辺張の牌か
     * 
     * @param shuntsu 順子。
     * @return 判定結果。
     */
    public boolean isEdge(final MenTsu shuntsu) {
        if (!shuntsu.isShunTsu()) {
            return false;
        }
        final boolean isCalled = shuntsu.getMenTsuType().isCalled();
        
        if (isCalled) {
            return false;
        }
        final JanPai head = shuntsu.getHead();
        
        if (head.equals(MAN_1) && this.equals(MAN_3)) {
            return true;
        }
        
        if (head.equals(MAN_7) && this.equals(MAN_7)) {
            return true;
        }
        
        if (head.equals(PIN_1) && this.equals(PIN_3)) {
            return true;
        }
        
        if (head.equals(PIN_7) && this.equals(PIN_7)) {
            return true;
        }
        
        if (head.equals(SOU_1) && this.equals(SOU_3)) {
            return true;
        }
        
        if (head.equals(SOU_7) && this.equals(SOU_7)) {
            return true;
        }
        return false;
    }
    
    /**
     * 偶数か
     * 
     * @return 判定結果。
     */
    public boolean isEven() {
        switch (this) {
        case MAN_2:
        case MAN_4:
        case MAN_6:
        case MAN_8:
        case PIN_2:
        case PIN_4:
        case PIN_6:
        case PIN_8:
        case SOU_2:
        case SOU_4:
        case SOU_6:
        case SOU_8:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 5か
     * 
     * @return 判定結果。
     */
    public boolean isFive() {
        switch (this) {
        case MAN_5:
        case PIN_5:
        case SOU_5:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 字牌か
     * 
     * @return 判定結果。
     */
    public boolean isJi() {
        switch (this) {
        case TON:
        case NAN:
        case SHA:
        case PEI:
        case HAKU:
        case HATU:
        case CHUN:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 全小の牌か
     * 
     * @return 判定結果。
     */
    public boolean isLower() {
        switch (this) {
        case MAN_1:
        case MAN_2:
        case MAN_3:
        case PIN_1:
        case PIN_2:
        case PIN_3:
        case SOU_1:
        case SOU_2:
        case SOU_3:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 小于五の牌か
     * 
     * @return 判定結果。
     */
    public boolean isLowerFour() {
        switch (this) {
        case MAN_1:
        case MAN_2:
        case MAN_3:
        case MAN_4:
        case PIN_1:
        case PIN_2:
        case PIN_3:
        case PIN_4:
        case SOU_1:
        case SOU_2:
        case SOU_3:
        case SOU_4:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 全中の牌か
     * 
     * @return 判定結果。
     */
    public boolean isMiddle() {
        switch (this) {
        case MAN_4:
        case MAN_5:
        case MAN_6:
        case PIN_4:
        case PIN_5:
        case PIN_6:
        case SOU_4:
        case SOU_5:
        case SOU_6:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 全大の牌か
     * 
     * @return 判定結果。
     */
    public boolean isUpper() {
        switch (this) {
        case MAN_7:
        case MAN_8:
        case MAN_9:
        case PIN_7:
        case PIN_8:
        case PIN_9:
        case SOU_7:
        case SOU_8:
        case SOU_9:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 大于五の牌か
     * 
     * @return 判定結果。
     */
    public boolean isUpperFour() {
        switch (this) {
        case MAN_6:
        case MAN_7:
        case MAN_8:
        case MAN_9:
        case PIN_6:
        case PIN_7:
        case PIN_8:
        case PIN_9:
        case SOU_6:
        case SOU_7:
        case SOU_8:
        case SOU_9:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 幺九牌か
     * 
     * @return 判定結果。
     */
    public boolean isYao() {
        switch (this) {
        case MAN_1:
        case MAN_9:
        case PIN_1:
        case PIN_9:
        case SOU_1:
        case SOU_9:
        case TON:
        case NAN:
        case SHA:
        case PEI:
        case HAKU:
        case HATU:
        case CHUN:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        switch (this) {
        case MAN_1:
            return "[一]";
        case MAN_2:
            return "[二]";
        case MAN_3:
            return "[三]";
        case MAN_4:
            return "[四]";
        case MAN_5:
            return "[五]";
        case MAN_6:
            return "[六]";
        case MAN_7:
            return "[七]";
        case MAN_8:
            return "[八]";
        case MAN_9:
            return "[九]";
        case PIN_1:
            return "[①]";
        case PIN_2:
            return "[②]";
        case PIN_3:
            return "[③]";
        case PIN_4:
            return "[④]";
        case PIN_5:
            return "[⑤]";
        case PIN_6:
            return "[⑥]";
        case PIN_7:
            return "[⑦]";
        case PIN_8:
            return "[⑧]";
        case PIN_9:
            return "[⑨]";
        case SOU_1:
            return "[１]";
        case SOU_2:
            return "[２]";
        case SOU_3:
            return "[３]";
        case SOU_4:
            return "[４]";
        case SOU_5:
            return "[５]";
        case SOU_6:
            return "[６]";
        case SOU_7:
            return "[７]";
        case SOU_8:
            return "[８]";
        case SOU_9:
            return "[９]";
        case TON:
            return "[東]";
        case NAN:
            return "[南]";
        case SHA:
            return "[西]";
        case PEI:
            return "[北]";
        case HAKU:
            return "[白]";
        case HATU:
            return "[發]";
        case CHUN:
            return "[中]";
        default:
            throw new InternalError();
        }
    }
    
    /**
     * 括弧なしの文字列に変換
     * 
     * @return 変換結果。
     */
    public String toStringWithoutBracket() {
        switch (this) {
        case MAN_1:
            return "1m";
        case MAN_2:
            return "2m";
        case MAN_3:
            return "3m";
        case MAN_4:
            return "4m";
        case MAN_5:
            return "5m";
        case MAN_6:
            return "6m";
        case MAN_7:
            return "7m";
        case MAN_8:
            return "8m";
        case MAN_9:
            return "9m";
        case PIN_1:
            return "1p";
        case PIN_2:
            return "2p";
        case PIN_3:
            return "3p";
        case PIN_4:
            return "4p";
        case PIN_5:
            return "5p";
        case PIN_6:
            return "6p";
        case PIN_7:
            return "7p";
        case PIN_8:
            return "8p";
        case PIN_9:
            return "9p";
        case SOU_1:
            return "1s";
        case SOU_2:
            return "2s";
        case SOU_3:
            return "3s";
        case SOU_4:
            return "4s";
        case SOU_5:
            return "5s";
        case SOU_6:
            return "6s";
        case SOU_7:
            return "7s";
        case SOU_8:
            return "8s";
        case SOU_9:
            return "9s";
        case TON:
            return "ton";
        case NAN:
            return "nan";
        case SHA:
            return "sha";
        case PEI:
            return "pe";
        case HAKU:
            return "haku";
        case HATU:
            return "hatu";
        case CHUN:
            return "chun";
        default:
            throw new InternalError();
        }
    }
    
}

