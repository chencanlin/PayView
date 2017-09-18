# PayView
仿支付宝支付密码输入控件，包含数字密码输入键盘和密码显示控件两部分


[![](https://jitpack.io/v/chencanlin/PayView.svg)](https://jitpack.io/#chencanlin/PayView)

**STEP1**  Add it in your root build.gradle at the end of repositories:

		allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

**STEP2**   Add the dependency

		dependencies {
	        compile 'com.github.chencanlin:PayView:1.2'
	}




**密码输入控件，效果演示：**

![](https://i.imgur.com/8CRuUjl.gif)

	
**使用：**

xml 声明：

	<com.ccl.perfectisshit.payview.widget.KeyboardView
        android:id="@+id/kbv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:background="@android:color/white"/>


    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_above="@+id/kbv"
        android:background="@android:color/white"
        android:paddingBottom="30dp"
        android:paddingLeft="10dp"
        android:paddingRight="10dp"
        android:paddingTop="30dp">

        <com.ccl.perfectisshit.payview.widget.PayView
            android:id="@+id/pv"

            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="@android:color/white"
            android:hint="edittext"
            android:inputType="text"
            app:passwordMode="cipherTextMode"// 设置密码输入模式：明文与暗文
            />
    </RelativeLayout>

代码：
	
	mPayView = findViewById(R.id.pv);
    mKeyBoardView = findViewById(R.id.kbv);
    mPayView.setKeyBoardView(mKeyBoardView);

设置输入模式：

	mPayView.setPasswordMode(); // MODE_PLAIN_TEXT   MODE_CIPHER_TEXT 

获取密码：
	
	mPayView.getPassword()
