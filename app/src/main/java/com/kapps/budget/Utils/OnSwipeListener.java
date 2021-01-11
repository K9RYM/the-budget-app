package com.kapps.budget.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class OnSwipeListener implements View.OnTouchListener {


    private boolean exitScreenOnSwipe = false;
    private boolean dragHorizontal = false;
    private boolean dragVertical = false;
    private boolean dragSnapBack = false;
    private boolean animated = true;
    private boolean onlyright = false;
    private boolean transitionAlpha = true;
    private boolean customDraggedView = false;
    private long animationDelay = 500;
    private float dragSnapThreshold = 200;
    private float swipeDistanceThreshold = 100;
    private float swipeVelocityThreshold = 100;
    private GestureDetector gestureDetector = null;
    private float dragPrevX;
    private float dragPrevY;
    private Impl swiper = null;
    private View draggedView = null;
    private View parentView = null;
    private Context context;
    float OriginalX = 0;
    private float screenwidth;
    private Canvas RecbinCanvas;


    public OnSwipeListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
        if (context instanceof Impl) {
            swiper = (Impl) context;
        }
        this.context = context;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenwidth = displayMetrics.widthPixels;

    }

    public void onSwipeLeft(float distance) {
        if (swiper != null) {
            swiper.onSwipeLeft(distance);
        }
    }

    public void onSwipeRight(float distance) {
        if (swiper != null) {
            swiper.onSwipeRight(distance);
        }
        setExitScreenOnSwipe(distance);
    }

    public void onSwipeUp(float distance) {
        if (swiper != null) {
            swiper.onSwipeUp(distance);
        }
    }

    public void onSwipeDown(float distance) {
        if (swiper != null) {
            swiper.onSwipeDown(distance);
        }
    }



/*

    public class RecycleBin extends ImageView {

        public RecycleBin(Context context, AttributeSet attrs) { super(context, attrs); }

        Bitmap RecycleBin;
        Bitmap Arrow;

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int arrow = R.drawable.ic_menu_send;
            Arrow = BitmapFactory.decodeResource(getResources(),R.drawable.ic_menu_send);
            canvas.drawBitmap()
        }
    }
*/

    /**
     * Internal method used to implement mouse touch events. Not to be called directly by clients.
     */
    @Override
    public final boolean onTouch(View view, MotionEvent event) {
        if (!customDraggedView) {
            if (view != null) {
                draggedView = view;
                if (OriginalX == 0)
                    OriginalX = draggedView.getX();
            }
        } else {
            if (view != draggedView) {
                parentView = view;
                view = draggedView;
                if (OriginalX == 0)
                    OriginalX = draggedView.getX();
            }
        }


        boolean gesture = gestureDetector.onTouchEvent(event);
        float alpha = 1;
        int action = event.getAction();
        if (dragHorizontal || dragVertical) {
            if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {

            } else if (action == MotionEvent.ACTION_MOVE) {
                float dragCurrX = event.getRawX();
                float dragCurrY = event.getRawY();
                if (dragHorizontal) {
                    if (onlyright) {
                        if (view.getX() >= OriginalX) {
                            view.setTranslationX(view.getTranslationX() + dragCurrX - dragPrevX);
                            if (transitionAlpha) { // set alpha based on dX times the scale of full screenwidth to screenwidth minus originalX all to a scale on full screenwidth
                                alpha = (1 - (((view.getX() - OriginalX) * (screenwidth / (screenwidth - OriginalX))) / screenwidth));

                                view.setAlpha(alpha);
                            }
                        }
                    } else {
                        view.setTranslationX(view.getTranslationX() + dragCurrX - dragPrevX);
                        if (transitionAlpha) {
                            alpha = (1 - (((view.getX() - OriginalX) * (screenwidth / (screenwidth - OriginalX))) / screenwidth));
                            view.setAlpha(alpha);
                        }
                    }
                }
                if (dragVertical) {
                    view.setTranslationY(view.getTranslationY() + dragCurrY - dragPrevY);
                }
            } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_POINTER_UP) {
                if (dragSnapBack) {
                    float dx = event.getRawX() - dragPrevX;
                    float dy = event.getRawY() - dragPrevY;
                    boolean shouldDoX = Math.abs(dx) <= dragSnapThreshold || dragSnapThreshold <= 0;
                    boolean shouldDoY = Math.abs(dy) <= dragSnapThreshold || dragSnapThreshold <= 0;
                    if (animated) {
                        ViewPropertyAnimator anim = view.animate();
                        if (shouldDoX) {
                            anim.translationX(0);
                            view.setAlpha(1);
                        }
                        if (shouldDoY) {
                            anim.translationY(0);
                            view.setAlpha(1);
                        }
                        anim.setDuration(animationDelay);
                        anim.start();
                    } else {
                        if (shouldDoX) {
                            view.setTranslationX(0);
                            view.setAlpha(1);
                        }
                        if (shouldDoY) {
                            view.setTranslationY(0);
                            view.setAlpha(1);
                        }
                    }
                }
            }
            dragPrevX = event.getRawX();
            dragPrevY = event.getRawY();
        }
        return gesture;
    }

    /**
     * Sets the number of pixels before the listener considers the user to have swiped.
     */
    public OnSwipeListener setDistanceThreshold(float px) {
        swipeDistanceThreshold = px;
        return this;
    }

    public OnSwipeListener setVelocityThreshold(float px) {
        swipeVelocityThreshold = px;
        return this;
    }

    public OnSwipeListener setCustomDraggedView(boolean customDraggedView, View newDraggedView) {
        this.customDraggedView = customDraggedView;
        this.draggedView = newDraggedView;
        return this;
    }

    public OnSwipeListener setDragSnapThreshold(float px) {
        dragSnapThreshold = px;
        if (dragSnapThreshold > 0) {
            setDragSnapBack(true);
        }
        return this;
    }

    /**
     * Sets the number of milliseconds long that each drag/snap animation will take.
     */
    public OnSwipeListener setAnimationDelay(long ms) {
        animationDelay = ms;
        setAnimated(animationDelay > 0);
        return this;
    }

    public boolean isExitScreenOnSwipe() {
        return exitScreenOnSwipe;
    }

    /**
     * view exits screen method
     **/
    public OnSwipeListener setExitScreenOnSwipe(float distance) {
        if (distance > screenwidth - screenwidth / 3)
            exitScreenOnSwipe = true;
        else exitScreenOnSwipe = false;
        return this;
    }

    public OnSwipeListener setDragHorizontal(boolean drag) {
        dragHorizontal = drag;
        return this;
    }

    public OnSwipeListener setDragVertical(boolean drag) {
        dragVertical = drag;
        return this;
    }

    /**
     * Sets whether the view should snap back into position when the user stops dragging it.
     */
    public OnSwipeListener setDragSnapBack(boolean snap) {
        dragSnapBack = snap;
        return this;
    }

    public OnSwipeListener setAnimated(boolean anim) {
        animated = anim;
        return this;
    }


    public OnSwipeListener setOnlyRight(boolean onlyRight) {
        onlyright = onlyRight;
        return this;
    }

    public OnSwipeListener setTransitionAlpha(boolean isTransitionAlpha) {
        transitionAlpha = isTransitionAlpha;
        return this;
    }


    /**
     * Internal class to implement finger gesture tracking.
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            /**code to implement default click tap actions on dragged view and parent view if differs*/
            if (customDraggedView) {
                parentView.performClick();
                if (parentView instanceof EditText)/**handle editexts focus and soft input*/ {
                    parentView.requestFocus();
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(parentView, InputMethodManager.SHOW_IMPLICIT);
                }
            } else {
                parentView.performClick();
                if (parentView instanceof EditText) {
                    parentView.requestFocus();
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(parentView, InputMethodManager.SHOW_IMPLICIT);
                }
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) {
            float dx = e2.getRawX() - e1.getRawX();
            float dy = e2.getRawY() - e1.getRawY();
            Configuration config = draggedView.getContext().getApplicationContext().getResources().getConfiguration();
            int screenWidth = config.screenWidthDp;
            int screenHeight = config.screenHeightDp;
            if (Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > swipeDistanceThreshold && Math.abs(vx) > swipeVelocityThreshold) {
                if (dx > 0) {
                    onSwipeRight(dx);
                    dragEdgeHelper(screenWidth * 2, true, 0, false);
                } else {
                    onSwipeLeft(-dx);
                    dragEdgeHelper(-screenWidth, true, 0, false);
                }
                return true;
            } else if (Math.abs(dy) > Math.abs(dx) && Math.abs(dy) > swipeDistanceThreshold
                    && Math.abs(vy) > swipeVelocityThreshold) {
                if (dy > 0) {
                    onSwipeDown(dy);
                    dragEdgeHelper(0, false, screenHeight * 2, true);
                } else {
                    onSwipeUp(-dy);
                    dragEdgeHelper(0, false, -screenHeight, true);
                }
                return true;
            }
            return false;
        }

        private void dragEdgeHelper(float tx, boolean useTX, float ty, boolean useTY) {
            if (exitScreenOnSwipe && draggedView != null) {
                if (animated) {
                    ViewPropertyAnimator anim = draggedView.animate().setDuration(animationDelay);
                    if (useTX) {
                        anim.translationX(tx);
                    }
                    if (useTY) {
                        anim.translationY(ty);
                    }
                    anim.start();
                } else {
                    ((ViewGroup) draggedView.getParent()).removeView(draggedView);
                }
            }
        }

    }

    public static interface Impl {
        void onSwipeLeft(float distance);

        void onSwipeRight(float distance);

        void onSwipeUp(float distance);

        void onSwipeDown(float distance);
    }
}
