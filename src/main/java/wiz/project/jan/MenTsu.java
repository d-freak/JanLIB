/**
 * MenTsu.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.List;



/**
 * 面子 (不変オブジェクト)
 */
public final class MenTsu {
    
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
     * ハッシュコードを取得
     * 
     * @return ハッシュコード。
     */
    @Override
    public int hashCode() {
        return _sourceList.hashCode() + _menTsuType.hashCode() + _janPaiType.hashCode();
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
            _janPaiType = JanPaiType.JI;
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
    private JanPaiType _janPaiType = JanPaiType.JI;
    
}

