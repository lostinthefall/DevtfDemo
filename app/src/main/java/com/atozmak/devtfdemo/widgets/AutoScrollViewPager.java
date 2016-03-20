package com.atozmak.devtfdemo.widgets;

import android.content.Context;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by Mak on 2016/3/19.
 */
public class AutoScrollViewPager extends ViewPager {

    public static final int DEFAULT_INTERVAL = 1500;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public static final int SLIDE_BORDER_MODE_NONE = 0;
    public static final int SLIDE_BORDER_MODE_CYCLE = 1;
    public static final int SLIDE_BORDER_MODE_TO_PARENT = 2;

    private long interval = DEFAULT_INTERVAL;

    private int direction = RIGHT;

    private boolean isCycle = true;

    private boolean stopScrollWhenTouch = true;

    private int slideBorderMode = SLIDE_BORDER_MODE_NONE;

    private boolean isBorderAnimation = true;

    private double autoScrollFactor = 1.0;

    private double swipeScrollFactor = 1.0;

    private Handler handler;
    private boolean isAutoScroll = false;
    private boolean isStopByTouch = false;
    private float touchX = 0f, downX = 0f;
    private CustomDurationScroller scroller = null;

    public static final int SCROLL_WHAT = 0;

    public AutoScrollViewPager(Context context) {
        super(context);
        init();
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        handler = new MyHandler(this);
        setViewPagerScroller();
    }

    public void startAutoScroll() {
        isAutoScroll = true;
        sendScrollMessage((long) (interval + scroller.getDuration() / autoScrollFactor * swipeScrollFactor));
    }

    public void startAutoScroll(int delayTimeInMills) {
        isAutoScroll = true;
        sendScrollMessage(delayTimeInMills);
    }

    private void sendScrollMessage(long l) {
        handler.removeMessages(SCROLL_WHAT);
        handler.sendEmptyMessageDelayed(SCROLL_WHAT, l);
    }

    public void stopAutoScroll() {
        isAutoScroll = false;
        handler.removeMessages(SCROLL_WHAT);
    }

    public void setSwipeScrollDurationFactor(double scrollFactor) {
        swipeScrollFactor = scrollFactor;
    }

    public void setAutoScrollDurationFactor(double scrollFactor) {
        autoScrollFactor = scrollFactor;
    }

    private void setViewPagerScroller() {
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolatorField = ViewPager.class.getDeclaredField("sInterpolator");
            interpolatorField.setAccessible(true);

            scroller = new CustomDurationScroller(
                    getContext(),
                    (Interpolator) interpolatorField.get(null)
            );
            scrollerField.set(this, scroller);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static class MyHandler extends Handler {

        private final WeakReference<AutoScrollViewPager> autoScrollViewPagerWeakReference;


        private MyHandler(AutoScrollViewPager autoScrollViewPager) {
            autoScrollViewPagerWeakReference = new WeakReference<AutoScrollViewPager>(autoScrollViewPager);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SCROLL_WHAT:
                    AutoScrollViewPager pager = this.autoScrollViewPagerWeakReference.get();
                    if (pager != null) {
                        pager.scroller.setScrollDurationFactor(pager.autoScrollFactor);
                        pager.scrollOnce();
                        pager.scroller.setScrollDurationFactor(pager.swipeScrollFactor);
                        pager.sendScrollMessage(pager.interval + pager.scroller.getDuration());
                    }
                    break;
                default:
                    break;
            }

        }
    }

    private void scrollOnce() {
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        int totalCount;
        if (adapter == null || (totalCount = adapter.getCount()) <= 1) {
            return;
        }

        int nextItem = (direction == LEFT) ? --currentItem : ++currentItem;
        if (nextItem < 0) {
            if (isCycle) {
                setCurrentItem(totalCount - 1, isBorderAnimation);
            }
        } else if (nextItem == totalCount) {
            if (isCycle) {
                setCurrentItem(0, isBorderAnimation);
            }
        } else {
            setCurrentItem(nextItem, true);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);

        if (stopScrollWhenTouch) {
            if ((action == MotionEvent.ACTION_DOWN) && isAutoScroll) {
                isStopByTouch = true;
                stopAutoScroll();
            } else if (ev.getAction() == MotionEvent.ACTION_UP && isStopByTouch) {
                startAutoScroll();
            }
        }
        if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT
                || slideBorderMode == SLIDE_BORDER_MODE_CYCLE) {
            touchX = ev.getX();
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                downX = touchX;
            }
            int currentItem = getCurrentItem();
            PagerAdapter adapter = getAdapter();
            int pageCount = adapter == null ? 0 : adapter.getCount();
            if ((currentItem == 0 && downX <= touchX)
                    || (currentItem == pageCount - 1 && downX >= touchX)) {
                if (slideBorderMode == SLIDE_BORDER_MODE_TO_PARENT) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    if (pageCount > 1) {
                        setCurrentItem(pageCount - currentItem - 1, isBorderAnimation);
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return super.dispatchTouchEvent(ev);
            }
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isCycle() {
        return isCycle;
    }

    public void setCycle(boolean isCycle) {
        this.isCycle = isCycle;
    }

    public boolean isStopScrollWhenTouch() {
        return stopScrollWhenTouch;
    }

    public void setStopScrollWhenTouch(boolean stopScrollWhenTouch) {
        this.stopScrollWhenTouch = stopScrollWhenTouch;
    }

    public int getSliderBorderMode() {
        return slideBorderMode;
    }

    public void setSlideBorderMode(int slideBorderMode) {
        this.slideBorderMode = slideBorderMode;
    }

    public boolean isBorderAnimation() {
        return isBorderAnimation;
    }

    public void setBorderAnimation(boolean isBorderAnimation) {
        this.isBorderAnimation = isBorderAnimation;
    }


}
