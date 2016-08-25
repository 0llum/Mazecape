package com.ollum.mazecape.Classes;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.ollum.mazecape.Activities.MainActivity;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context ctx) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {

            if ((!MainActivity.swipe) && e.getX() > MainActivity.width * 2 / 3 && e.getY() < MainActivity.height - MainActivity.height / 8 - MainActivity.width / 3 && e.getY() > MainActivity.height - MainActivity.height / 8 - MainActivity.width * 2 / 3) {
                if (MainActivity.inverse) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
            } else if ((!MainActivity.swipe) && e.getX() < MainActivity.width / 3 && e.getY() < MainActivity.height - MainActivity.height / 8 - MainActivity.width / 3 && e.getY() > MainActivity.height - MainActivity.height / 8 - MainActivity.width * 2 / 3) {
                if (MainActivity.inverse) {
                    onSwipeLeft();
                } else {
                    onSwipeRight();
                }
            } else if ((!MainActivity.swipe) && e.getY() < MainActivity.height - MainActivity.height / 8 - MainActivity.width * 2 / 3 && e.getX() < MainActivity.width * 2 / 3 && e.getX() > MainActivity.width / 3) {
                if (MainActivity.inverse) {
                    onSwipeTop();
                } else {
                    onSwipeBottom();
                }
            } else if ((!MainActivity.swipe) && e.getY() > MainActivity.height - MainActivity.height / 8 - MainActivity.width / 3 && e.getX() < MainActivity.width * 2 / 3 && e.getX() > MainActivity.width / 3) {
                if (MainActivity.inverse) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if ((MainActivity.swipe) && Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            if (!MainActivity.inverse) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                        } else {
                            if (!MainActivity.inverse) {
                                onSwipeLeft();
                            } else {
                                onSwipeRight();
                            }
                        }
                    }
                    result = true;
                } else if ((MainActivity.swipe) && Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        if (!MainActivity.inverse) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    } else {
                        if (!MainActivity.inverse) {
                            onSwipeTop();
                        } else {
                            onSwipeBottom();
                        }
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }

        /*@Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if ((MainActivity.swipe) && Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(distanceX) > SWIPE_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                } else if ((MainActivity.swipe) && Math.abs(distanceY) > SWIPE_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }*/
    }
}