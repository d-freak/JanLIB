/**
 * ComplateJanPai.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;



/**
 * 和了牌 (不変オブジェクト)
 */
public final class CompleteJanPai {
    
    /**
     * コンストラクタ
     * 
     * @param pai 牌。
     * @param type 和了タイプ
     */
    public CompleteJanPai(final JanPai pai, final CompleteType type) {
        setJanPai(pai);
        setType(type);
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public CompleteJanPai(final CompleteJanPai source) {
        if (source != null) {
            _pai = source._pai;
            _type = source._type;
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
        if (!(target instanceof CompleteJanPai)) {
            return false;
        }
        
        final CompleteJanPai targetPai = (CompleteJanPai)target;
        return (_pai == targetPai._pai) &&
               (_type == targetPai._type);
    }
    
    /**
     * 牌を取得
     * 
     * @return 牌。
     */
    public JanPai getJanPai() {
        return _pai;
    }
    
    /**
     * 和了タイプを取得
     * 
     * @return 和了タイプ。
     */
    public CompleteType getType() {
        return _type;
    }
    
    /**
     * ハッシュコードを取得
     * 
     * @return ハッシュコード。
     */
    @Override
    public int hashCode() {
        return _pai.hashCode() + _type.hashCode();
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        return _pai + " (" + _type + ")";
    }
    
    
    
    /**
     * 牌を設定
     * 
     * @param pai 牌。
     */
    private void setJanPai(final JanPai pai) {
        if (pai != null) {
            _pai = pai;
        }
        else {
            _pai = JanPai.HAKU;
        }
    }
    
    /**
     * 和了タイプを設定
     * 
     * @param type 和了タイプ。
     */
    private void setType(final CompleteType type) {
        if (type != null) {
            _type = type;
        }
        else {
            _type = CompleteType.UNKNOWN;
        }
    }
    
    
    
    /**
     * 牌
     */
    private JanPai _pai = JanPai.HAKU;
    
    /**
     * 和了タイプ
     */
    private CompleteType _type = CompleteType.UNKNOWN;
    
}

