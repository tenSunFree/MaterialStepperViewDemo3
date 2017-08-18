package com.tensun.materialstepperviewdemo3.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tensun.materialstepperviewdemo3.R;

import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;


public class VerticalStepperAdapterDemoFragment extends Fragment implements IStepperAdapter {

	private VerticalStepperView mVerticalStepperView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_vertical_stepper_adapter, parent, false);               // 畫面的父容器
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mVerticalStepperView = view.findViewById(R.id.vertical_stepper_view);
		mVerticalStepperView.setStepperAdapter(this);                                               // 因為已經實現了IStepperAdapter, 所以可以直接設置this, 也就是設置內容
	}

	@Override
	public @NonNull
    String getTitle(int index) {                                                                    // 設置每個index 的標題名稱
		switch (index) {
			case 0:
				return "魔戒首部曲：魔戒現身";
			case 1:
				return "魔戒二部曲：雙城奇謀";
			case 2:
				return "魔戒三部曲：王者再臨";
			default:
				return null;
		}
	}

	@Override
	public @Nullable
    String getSummary(int index) {                                                                  // 設置每個index, 縮合時 顯示的摘要
		switch (index) {
			case 0:
				return "比爾博好友甘道夫經過探查後發現那就是失落的至尊魔戒...";
			case 1:
				return "甘道夫在摩瑞亞的橋上與炎魔對峙...";
			case 2:
				return "史麥戈殺死德戈並奪取魔戒...";
			default:
				return null;
		}
	}

	@Override
	public int size() {                                                                             // 設置想要產生幾個index
		return 3;
	}

	@Override
	public View onCreateCustomView(
			final int index, Context context, VerticalStepperItemView parent) {
		View inflateView = LayoutInflater.from(context).inflate(                                     // Item的完整View畫面
				R.layout.vertical_stepper_sample_item,                                                   // Item對應的layout
				parent,                                                                             // Iten是來自VerticalStepperItemView
				false
		);
		TextView contentView = inflateView.findViewById(R.id.item_content);
		contentView.setText(                                                                         // 設置每一個Item的標題
				index == 0 ?
						R.string.content_step_0 : (
								index == 1 ? R.string.content_step_1 : R.string.content_step_2
				)
		);
		Button nextButton = inflateView.findViewById(R.id.button_next);
		nextButton.setText(                                                                         // 設置每一個Item 第一個Button　的顯示名稱
				index == size() - 1 ? "返回首頁" : getString(R.string.next)
		);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!mVerticalStepperView.nextStep()) {                                             // 會先觸發nextStep(), 然後如果還有下一個Item 會優先觸發其子方法canNext() 並返回true, 因為返回true 所以if()內的程式不會被觸發, 可是假如沒有下一個Item 則會返回flase, if()內的程式會被啟動
					Snackbar.make(                                                                  // 用Snackbar 顯示訊息
							mVerticalStepperView, "返回首頁ing..", Snackbar.LENGTH_LONG).show();
				}
			}
		});
		Button prevButton = inflateView.findViewById(R.id.button_prev);
		prevButton.setText(index == 0 ? R.string.prev_home : R.string.prev);                         // 設置每一個Item 第二個Button　的顯示名稱
		inflateView.findViewById(R.id.button_prev).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {                                                        // 只要index不為0, 都可以返回上一個Item, 如果index為0, 則執行xxx, xxx看不懂是什麼
				if (index != 0) {
					mVerticalStepperView.prevStep();
				} else {
					mVerticalStepperView                                                            // what?
							.setAnimationEnabled(!mVerticalStepperView.isAnimationEnabled());
				}
			}
		});
		return inflateView;                                                                         // 返回顯示每一個Item的畫面
	}

	@Override
	public void onShow(int index) {
		Log.v("more", "onShow()");
	}

	@Override
	public void onHide(int index) {
		Log.v("more", "onHide()");
	}
}
