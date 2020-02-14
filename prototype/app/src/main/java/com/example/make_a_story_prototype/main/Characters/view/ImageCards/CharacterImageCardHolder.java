package com.example.make_a_story_prototype.main.Characters.view.ImageCards;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.make_a_story_prototype.R;
import com.example.make_a_story_prototype.main.Characters.vm.CharacterCardItemViewModel;
import com.example.make_a_story_prototype.main.Util;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterImageCardHolder extends RecyclerView.ViewHolder {

    public interface CharacterImageCallback {
        void imageTappedOn(CharacterCardItemViewModel vm);
    }

    public CharacterImageCallback callback;

    private ImageView characterImage;
    private RelativeLayout parentLayout;
    private CharacterCardItemViewModel vm;
    private LinearLayout shadow;


    public CharacterImageCardHolder(@NonNull View itemView) {
        super(itemView);
        this.characterImage = itemView.findViewById(R.id.characterImage);
        this.parentLayout = itemView.findViewById(R.id.parentLayout);
        this.shadow = itemView.findViewById(R.id.shadow);

        parentLayout.setOnClickListener(v -> {
            if (callback != null && vm != null ) {
                callback.imageTappedOn(vm);
            }
        });
    }

    public void setViewModel(CharacterCardItemViewModel vm) {
        this.vm = vm;

        characterImage.setImageResource(vm.cardItem.getImageResource());

        Drawable imageBackground = parentLayout.getBackground();
        Util.changeDrawableColor(imageBackground, vm.backgroundColor);

        CardView cardView = itemView.findViewById(R.id.card_view);
        Drawable imageBorder = cardView.getBackground();

        Util.changeDrawableColor(imageBorder, vm.contrastColor);
        cardView.setRadius(23);
    }

    public void setShadow(boolean includeShadow) {
        if (includeShadow) {
            shadow.setBackgroundResource(R.drawable.shadow);
        } else {
            shadow.setBackgroundResource(0);
        }
    }
}
