# JavaIina
プログラミング第2同演習 最終課題

## 備考
C#いいな!

## コーディングスタイル
- インデントにはタブではなくスペース4つを使用してください。
- クラス名は`ApplicationContext`や`BufferedOutputStream`のように、複合語の先頭を大文字で記載するアッパーキャメルケースとしてください。
- メソッド名や変数名は`getObjectAttributes`や`loggedInUser`のように、複合語の先頭を小文字で記載するローワーキャメルケースとしてください。

```
	/* お勧めの書き方 */
	public class SomeClass
	{
		public List<Integer> getSomeValues();
		public void doSomething();
	}
	
	/* 良くない書き方 */
	public class someClass
	{
		public List<Integer> get_some_values();
		public void DoSomething();
	}
```

- フィールド名は`mTextBoxUserId`や`mConnectionInfo`のように、先頭に`m`を付加してください。
- フィールドは全て`protected`または`private`として、適切なアクセッサメソッド(`getX`や`setX`)を定義してください。
- フィールドを参照するときには`this.mTextBoxUserId`のように、必ず`this`を付加してください。

```
	/* お勧めの書き方 */
	public class SomeClass
	{
		private BigInteger mValue;
		private String mUserInput;
		private int mUserId;
		
		public BigInteger setValue(BigInteger newValue)
		{
			this.mValue = newValue;
		}
		
		public String getUserInput()
		{
			return this.mUserInput;
		}
	}
	
	/* 良くない書き方 */
	public class SomeClass
	{
		private BigInteger _resultValue;
		private BigInteger resultValue_;
		private BigInteger result_value;
		private BigInteger ResultValue;
		
		public String name;
	}
```

- クラスとメソッドを囲む中括弧(`{`と`}`)は、宣言した行の次の行に記述してください(中括弧の手前で改行してください)。
- `if`文、`while`文、`switch`文などの制御構文の場合は、中括弧を宣言した行と同一の行に記述してください(中括弧の手前で改行しないでください)。
- `if`文、`while`文の中身が1行しかない場合は中括弧を省略しても大丈夫です。
- `switch`文は`case`句で新たにインデントを1つ追加してください。

```
	/* お勧めの書き方 */
	switch (condition) {
		case Case1:
			DoSomething1();
			break;
		case Case2:
			DoSomething2();
			break;
		default:
			throw new UnsupportedOperationException();
	}
	
	/* 良くない書き方 */
	switch (condition) {
	case Case1:
		DoSomething1();
		break;
	case Case2:
		DoSomething2();
		break;
	default:
		throw new UnsupportedOperationException();
	}
```

```
	/* お勧めの書き方 */
	x++;
	y++;
	
	if (satisfiesCondition)
		DoSomething();
	
	/* 良くない書き方 */
	x++; y++;
	
	if (satisfiesCondition) DoSomething();
```

```
	/* お勧めの書き方 */
	if (satisfiesCondition)
		DoSomething();
	
	if (satisfiesCondition) {
		// Some comment
		DoSomething();
	}
	
	if (satisfiesCondition)
		DoSomething();
	else
		YetAnother();
	
	if (satisfiesCondition) {
		DoSomething();
	} else {
		DoSomethingElse();
		YetAnother();
	}
	
	/* 良くない書き方 */
	if (satisfiesCondition)
	{
		DoSomething();
	}
	
	if (satisfiesCondition)
		// Some comment
		DoSomething();
	
	if (satisfiesCondition)
	{
		DoSomething();
	}
	else
	{
		YetAnother();
	}
	
	if (satisfiesCondition)
		DoSomething();
	else {
		DoSomethingElse();
		YetAnother();
	}
	
```

```
	/* お勧めの書き方 */
	if (1 == i) { ...
	
	/* 誤った書き方 */
	if (i == 1) { ...
```

- 失敗のケースを早めの段階で排除してネストを浅くしてください(覚えておくべきことが少なくて済みます)。

```
	/* 良くない書き方 */
	public boolean someMethod(String someVariable)
	{
		if (succeeded1()) {
			if (succeeded2()) {
				if (succeeded3()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/* お勧めの書き方 */
	public boolean someMethod(String someVariable)
	{
		if (failed1())
			return false;
		
		if (failed2())
			return false;
		
		if (failed3())
			return false;
		
		return true;
	}
```

- その他については某教授の某C言語本のコーディングスタイルを参照してください。

### サンプルコード
```java
/* Sample.java */

public class CustomerInfo
{
	private String mName;
	private int mAge;
	private boolean mIsRoyalCustomer;
	private int mCumulativePurchasePrice;
	private List<ProductInfo> mProductList;
	
	public String getName()
	{
		return this.mName;
	}
	
	public CustomerInfo(
		String name,
		int age,
		boolean isRoyalCustomer)
	{
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("name");
		
		if (age < 0 || age > 120)
			throw new IllegalArgumentException("age");
		
		this.mName = name;
		this.mAge = age;
		this.mIsRoyalCustomer = isRoyalCustomer;
		this.mCumulativePurchasePrice = 0;
		this.mProductList = new ArrayList<ProductInfo>();
	}
	
	public void buyProduct(ProductInfo productInfo)
	{
		this.mCulumativePurchasePrice += productInfo.getPrice();
		this.mProductList.add(productInfo);
	}
	
	public double estimateCustomerRoyality()
	{
		if (this.mCumulativePurchasePrice < 10000) {
			return (double)this.mCumulativePurchasePrice * 0.25;
		} else {
			return this.mProductList
				.stream()
				.filter(productInfo -> productInfo.getPrice() > 10000)
				.mapToDouble(productInfo -> productInfo.getPrice() * productInfo.getRate())
				.sum();
		}
	}
}
```
