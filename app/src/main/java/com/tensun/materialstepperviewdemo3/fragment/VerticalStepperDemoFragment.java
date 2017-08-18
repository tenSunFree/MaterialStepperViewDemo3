package com.tensun.materialstepperviewdemo3.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tensun.materialstepperviewdemo3.R;

import moe.feng.common.stepperview.VerticalStepperItemView;


public class VerticalStepperDemoFragment extends Fragment {

	private VerticalStepperItemView mSteppers[] = new VerticalStepperItemView[3];                   // 建立VerticalStepperItemView 數組
	private Button mNextBtn0, mNextBtn1, mPrevBtn1, mNextBtn2, mPrevBtn2;

	private int mActivatedColorRes = R.color.material_blue_500;
	private int mDoneIconRes = R.drawable.ic_done_white_16dp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_vertical_stepper, parent, false);                      // 設置畫面
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		/** 對於nextStep() 的前後順序 */
		mSteppers[0] = view.findViewById(R.id.stepper_0);
		mSteppers[1] = view.findViewById(R.id.stepper_1);
		mSteppers[2] = view.findViewById(R.id.stepper_2);

		VerticalStepperItemView.bindSteppers(mSteppers);

		/** Step1 的部分 */
		mNextBtn0 = view.findViewById(R.id.button_next_0);
		mNextBtn0.setOnClickListener(new View.OnClickListener() {                                   // 對Step1 確定選項, 進行監聽
			@Override
			public void onClick(View view) {
				mSteppers[0].nextStep();                                                            // 如果mSteppers[] 下一個ItemView不為空, 縮和目前ItemView, 展開下一個ItemView
			}
		});

		/** Step2 的部分 */
		mPrevBtn1 = view.findViewById(R.id.button_prev_1);
		mPrevBtn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSteppers[1].prevStep();                                                            // 如果mSteppers[] 上一個ItemView不為空, 縮和目前ItemView, 展開上一個ItemView
			}
		});
		mNextBtn1 = view.findViewById(R.id.button_next_1);
		mNextBtn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSteppers[1].nextStep();                                                            // 如果mSteppers[] 下一個ItemView不為空, 縮和目前ItemView, 展開下一個ItemView
			}
		});

		/** Step3 的部分 */
		mPrevBtn2 = view.findViewById(R.id.button_prev_2);
		mPrevBtn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSteppers[2].prevStep();                                                            // 如果mSteppers[] 上一個ItemView不為空, 縮和目前ItemView, 展開上一個ItemView
			}
		});
		mNextBtn2 = view.findViewById(R.id.button_next_2);
		mNextBtn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "返回首頁ing..", Snackbar.LENGTH_LONG).show();                     // 用Snackbar 顯示Finish的訊息
			}
		});
	}
}
