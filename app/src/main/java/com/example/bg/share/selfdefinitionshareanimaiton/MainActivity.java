package com.example.bg.share.selfdefinitionshareanimaiton;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_share;
    private ImageView iv_all;
    private static PopupWindow mPop;
    private View mPopupView;
    private PopupWindow mPopupWindow;
    private LinearLayout qzone;
    private LinearLayout friend_circle;
    private LinearLayout sina;
    private LinearLayout weixin_goodfriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEventListener();
    }

    private void initView() {
        bt_share = (Button) findViewById(R.id.bt_share);
        iv_all = (ImageView) findViewById(R.id.iv_all);

        /**
         * 分享的那个popupWindow
         */
        //获取得到PopupWindow的布局文件
        mPopupView = View.inflate(this, R.layout.layout_share_popupwindow, null);
        qzone = (LinearLayout) mPopupView.findViewById(R.id.qzone);
        friend_circle = (LinearLayout) mPopupView.findViewById(R.id.friend_circle);
        sina = (LinearLayout) mPopupView.findViewById(R.id.sina);
        weixin_goodfriend = (LinearLayout) mPopupView.findViewById(R.id.weixin_goodfriend);
    }

    private void initData() {
    }

    private void initEventListener() {
        bt_share.setOnClickListener(this);
        qzone.setOnClickListener(this);
        friend_circle.setOnClickListener(this);
        sina.setOnClickListener(this);
        weixin_goodfriend.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
           case R.id.bt_share: //分享
               propetyAnim(iv_all);
               mPopupWindow = showPopWindow(view, mPopupView, this, iv_all);
               break;
            case R.id.qzone://分享到qq空间！

               break;
            case R.id.friend_circle://分享到微信朋友圈！

               break;
            case R.id.sina://分享到新浪微博！

               break;
            case R.id.weixin_goodfriend://分享到微信好友！

               break;
           default:
               break;
        }
    }

    /**
        半透明背景出现的动画
     */
    private void propetyAnim(ImageView iv) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.2f, 0.5f, 0.7f, 0.9f, 1);
        animator.setDuration(500);
        animator.setRepeatCount(0);
        animator.start();
        iv.setVisibility(View.VISIBLE);
    }

    /**
     * 半透明背景消失的动画
     * @param iv
     */
    public static void propetyAnim2(final ImageView iv){
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"alpha",1,0.9f,0.7f,0.5f,0.2f,0);
        animator.setDuration(500);
        animator.setRepeatCount(0);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 显示PopupWindow
     * @param parent
     * @param view
     * @return
     */
    public static PopupWindow showPopWindow(View parent, View view, Context context, final ImageView iv_all) {
        mPop = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPop.setOutsideTouchable(true);
        mPop.setFocusable(true);
        mPop.setBackgroundDrawable(new ColorDrawable(0));
        mPop.setAnimationStyle(R.style.AnimBottom);
        mPop.showAtLocation(parent, Gravity.TOP, 0, 0);

        //mPop.showAsDropDown(v,0,0);
        view.setFocusable(true); // 这个很重要
        view.setFocusableInTouchMode(true);

        mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                propetyAnim2(iv_all);
                mPop = null;
            }
        });

        // 重写onKeyListener
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mPop.dismiss();
                    return true;
                }
                return false;
            }
        });

        // 点击其他地方消失
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (mPop != null && mPop.isShowing()) {
                    mPop.dismiss();
                }
                return false;
            }
        });

        return mPop;
    }



}
