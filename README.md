# MaterialStepperViewDemo3

簡介
==================================
逐層展開式的View 的實現                                         
取材自MaterialStepperView, 修改而成

MaterialStepperView                                     
https://github.com/fython/MaterialStepperView

預覽
--------
![image](http://i.imgur.com/7ebm0hA.jpg)  

常見問題
--------
Q1:
```
Error:(30, 13) Failed to resolve: moe.feng:MaterialStepperView:latest-version
```
A1:
一般來說, 都可以從原作者提供的資訊上找到最新版本號碼
![image](http://i.imgur.com/QRYLPhx.jpg)  
```
compile 'moe.feng:MaterialStepperView:0.2.1'
```
      
Q2:
```
Error:Failed to resolve: com.android.support:recyclerview-v7:26.0.0
```
A2:
因為RecyclerView 從26.0.0版本開始不再通過AndroidSDK管理獲取, 所以要在Project的build.gradle添加以下
```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://maven.google.com' }                                                    // RecyclerView 從26.0.0版本開始不再通過 Android SDK管理獲取
        maven { url "https://jitpack.io" }
    }
}
```
Q3:
```
java.lang.IllegalStateException: This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.
```
A3:
因為RecyclerView 從26.0.0版本開始不再通過AndroidSDK管理獲取, 所以要在Project的build.gradle添加以下
```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://maven.google.com' }                                                    // RecyclerView 從26.0.0版本開始不再通過 Android SDK管理獲取
        maven { url "https://jitpack.io" }
    }
}
```
