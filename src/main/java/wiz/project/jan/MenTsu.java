/**
 * MenTsu.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.List;

import wiz.project.jan.util.JanPaiUtil;



/**
 * 面子 (不変オブジェクト)
 */
public final class MenTsu implements Comparable<MenTsu> {
    
    /**
     * コンストラクタ
     * 
     * @param source 牌リスト。
     */
    public MenTsu(final List<JanPai> source) {
        this(source, MenTsuType.UNKNOWN);
    }
    
    /**
     * コンストラクタ
     * 
     * @param source 牌リスト。
     * @param type 面子タイプ。
     */
    public MenTsu(final List<JanPai> source, final MenTsuType type) {
        setSource(source);
        setMenTsuType(type);
        
        final int sourceSize = _sourceList.size();
        if (sourceSize < 3) {
            throw new IllegalArgumentException("Invalid source size - " + sourceSize);
        }
        setJanPaiType(_sourceList.get(0).getType());
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public MenTsu(final MenTsu source) {
        if (source != null) {
            _sourceList = deepCopyList(source._sourceList);
            _menTsuType = source._menTsuType;
            _janPaiType = source._janPaiType;
        }
    }
    
    
    
    /**
     * 面子を比較
     * 
     * @param mentsu 比較対象。
     * @return 比較結果。
     */
    public int compareTo(MenTsu mentsu) {
        final JanPai comparePai = mentsu.getSource().get(0);
        final JanPai pai = getSource().get(0);
        
        if (pai.compareTo(comparePai) < 0) {
            return -1;
        }
        else if (pai.compareTo(comparePai) > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
    
    /**
     * 等価なオブジェクトか
     * 
     * @param target 比較対象。
     * @return 比較結果。
     */
    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (target == null) {
            return false;
        }
        if (!(target instanceof TenpaiPattern)) {
            return false;
        }
        
        final MenTsu targetMenTsu = (MenTsu)target;
        return _sourceList.equals(targetMenTsu._sourceList) &&
               (_menTsuType == targetMenTsu._menTsuType) &&
               (_janPaiType == targetMenTsu._janPaiType);
    }
    
    /**
     * 先頭の牌を取得
     * 
     * @return 先頭の牌。
     */
    public JanPai getHead() {
        return getSource().get(0);
    }
    
    /**
     * 指定牌の所持数を取得
     * 
     * @param pai 検索対象。
     * @return 所持数。
     */
    public int getJanPaiCount(final JanPai pai) {
        int count = 0;
        for (final JanPai source : _sourceList) {
            if (source == pai) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * 牌タイプを取得
     * 
     * @return 牌タイプ。
     */
    public JanPaiType getJanPaiType() {
        return _janPaiType;
    }
    
    /**
     * 面子タイプを取得
     * 
     * @return 面子タイプ。
     */
    public MenTsuType getMenTsuType() {
        return _menTsuType;
    }
    
    /**
     * 中央の牌を取得
     * 
     * @return 中央の牌。
     */
    public JanPai getMiddle() {
        return getSource().get(1);
    }
    
    /**
     * 牌枚数を取得
     * 
     * @return 牌枚数。
     */
    public int getSize() {
        return _sourceList.size();
    }
    
    /**
     * 牌リストを取得
     * 
     * @return 牌リスト。
     */
    public List<JanPai> getSource() {
        return deepCopyList(_sourceList);
    }
    
    /**
     * 末尾の牌を取得
     * 
     * @return 末尾の牌。
     */
    public JanPai getTail() {
        return getSource().get(2);
    }
    
    /**
     * ハッシュコードを取得
     * 
     * @return ハッシュコード。
     */
    @Override
    public int hashCode() {
        return _sourceList.hashCode() + _menTsuType.hashCode() + _janPaiType.hashCode();
    }
    
    /**
     * 5を持っているか
     * 
     * @return 判定結果。
     */
    public boolean hasFive() {
        for (final JanPai pai : getSource()) {
            if (pai.isFive()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 指定牌を持っているか
     * 
     * @param target 検索対象。
     * @return 判定結果。
     */
    public boolean hasJanPai(final JanPai target) {
        return _sourceList.contains(target);
    }
    
    /**
     * 幺九牌を持っているか
     * 
     * @return 判定結果。
     */
    public boolean hasYao() {
        for (final JanPai pai : getSource()) {
            if (pai.isYao()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 偶数の刻子か
     * 
     * @return 判定結果。
     */
    public boolean isEvenKohTsu() {
        if (isShunTsu()) {
            return false;
        }
        final JanPai pai = getHead();
        
        if (pai.isEven()) {
            return true;
        }
        return false;
    }
    
    /**
     * 順子か
     * 
     * @return 判定結果。
     */
    public boolean isShunTsu() {
        final JanPai head = getHead();
        final JanPai middle = getMiddle();
        final JanPai tail = getTail();
        
        return JanPaiUtil.isShunTsu(head, middle, tail);
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        return _sourceList + " (" + _menTsuType + ")";
    }
    
    
    
    /**
     * リストをディープコピー
     * 
     * @param sourceList 複製元リスト。
     * @return 複製結果。
     */
    private <E> List<E> deepCopyList(final List<E> sourceList) {
        return new ArrayList<E>(sourceList);
    }
    
    /**
     * 牌リストを設定
     * 
     * @param source 牌リスト。
     */
    private void setSource(final List<JanPai> source) {
        if (source != null) {
            _sourceList = deepCopyList(source);
        }
        else {
            _sourceList.clear();
        }
    }
    
    /**
     * 牌タイプを設定
     * 
     * @param type 牌タイプ。
     */
    private void setJanPaiType(final JanPaiType type) {
        if (type != null) {
            _janPaiType = type;
        }
        else {
            _janPaiType = JanPaiType.JI_DORAGON;
        }
    }
    
    /**
     * 面子タイプを設定
     * 
     * @param type 面子タイプ。
     */
    private void setMenTsuType(final MenTsuType type) {
        if (type != null) {
            _menTsuType = type;
        }
        else {
            _menTsuType = MenTsuType.UNKNOWN;
        }
    }
    
    
    
    /**
     * 牌リスト
     */
    private List<JanPai> _sourceList = new ArrayList<JanPai>();
    
    /**
     * 面子タイプ
     */
    private MenTsuType _menTsuType = MenTsuType.UNKNOWN;
    
    /**
     * 牌タイプ
     */
    private JanPaiType _janPaiType = JanPaiType.JI_DORAGON;
    
}

