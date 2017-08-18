package com.tensun.materialstepperviewdemo3;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tensun.materialstepperviewdemo3.fragment.VerticalStepperAdapterDemoFragment;
import com.tensun.materialstepperviewdemo3.fragment.VerticalStepperDemoFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;

	private Fragment mVerticalStepperDemoFragment = new VerticalStepperDemoFragment(),
			mVerticalStepperAdapterDemoFragment = new VerticalStepperAdapterDemoFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		assert actionBar != null;                                                                   // 如果它的值为false，该语句强抛出一个AssertionError对象
		actionBar.setDisplayHomeAsUpEnabled(true);                                                  // 在ActionBar左上角添加返回按钮, 点击放回上一页面
		actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);                               // 修改actionbar左上角返回按钮的图标

		mDrawerLayout = findViewById(R.id.drawer_layout);
		mNavigationView = findViewById(R.id.navigation_view);
		mNavigationView.setNavigationItemSelectedListener(this);

		replaceFragment(mVerticalStepperDemoFragment);                                              // 設置初始內容 為mVerticalStepperDemoFragment
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {                                           // 對左上角選單按鈕 進行監控
		switch (item.getItemId()) {
			case android.R.id.home:
				if (mDrawerLayout.isDrawerOpen(mNavigationView)) {                                  // 如果mNavigationView 開啟中的話
					mDrawerLayout.closeDrawer(mNavigationView);                                     // 關閉mNavigationView 的顯示
				} else {
					mDrawerLayout.openDrawer(mNavigationView);                                      // 開啟mNavigationView 的顯示
				}
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		mDrawerLayout.closeDrawer(mNavigationView);                                                 // 只要點選任何一個Item, 都會觸發此方法, 此方法是用來關閉mNavigationView
		switch (item.getItemId()) {
			case R.id.item_vertical_stepper:
				replaceFragment(mVerticalStepperDemoFragment);                                      // 設置為mVerticalStepperDemoFragment 的內容
				return true;
			case R.id.item_vertical_stepper_adapter:
				replaceFragment(mVerticalStepperAdapterDemoFragment);                               // 設置為mVerticalStepperAdapterDemoFragment 的內容
				return true;
			case R.id.action_fork_on_github:
				openWebsite("https://github.com/fython/MaterialStepperView");                       // 快速透過Chrome Custom Tabs 來打開指定的網頁
				return true;
			default:
				return false;
		}
	}

	private void replaceFragment(Fragment fragment) {                                               // 快速替換container容器的fragment顯示內容
		getSupportFragmentManager().beginTransaction().replace(                                     // 設定初始顯示的畫面
				R.id.container,                                                                       // 顯示畫面的容器
				fragment                                                                            // 構成畫面的內容
		).commit();
	}

	private void openWebsite(String url) {                                                          // 快速透過Chrome Custom Tabs 來打開指定的網頁
		CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();                          // 取得對象
		builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));                       // 設置ActionBar的顏色
		builder.build().launchUrl(this, Uri.parse(url));                                             // 想要開啟的網址
	}
}
