package com.uni.brivia.game.domain;

import androidx.annotation.NonNull;

import com.uni.brivia.game.R;

/**
 * Maps the question to a locally saved Image Drawable.
 * (todo: this logic should be on the server in the future)
 */
public class GameImageMapper {

    public static Integer getDrawableForQuestion(@NonNull String questionId) {
        switch (questionId) {
            case "q-01":
                return R.drawable.image_q01;
            case "q-02":
                return R.drawable.image_q02;
            case "q-03":
                return R.drawable.image_q03;
            case "q-04":
                return R.drawable.image_q04;
            case "q-05":
                return R.drawable.image_q05;
            default:
                throw new IllegalStateException("Unexpected value: " + questionId);
        }
    }
}
