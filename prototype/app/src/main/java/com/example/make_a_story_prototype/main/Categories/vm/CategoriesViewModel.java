package com.example.make_a_story_prototype.main.Categories.vm;

import android.content.Context;

import com.example.make_a_story_prototype.R;
import com.example.make_a_story_prototype.main.data.Word.DebugWordRepository;
import com.example.make_a_story_prototype.main.data.Word.Category;


import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

public class CategoriesViewModel {

    private List<CategoryCardItemViewModel> cardList = new ArrayList<>();

    public CategoriesViewModel(Context context) {
        int BackgroundColors[] = {
                ContextCompat.getColor(context, R.color.colorLightRed),
                ContextCompat.getColor(context, R.color.colorLightOrange),
                ContextCompat.getColor(context, R.color.colorLightGreen),
                ContextCompat.getColor(context, R.color.colorLightPurple),
                ContextCompat.getColor(context, R.color.colorLightBlue),
        };

        int DetailColors[] = {
                ContextCompat.getColor(context, R.color.colorContrastRed),
                ContextCompat.getColor(context, R.color.colorContrastOrange),
                ContextCompat.getColor(context, R.color.colorContrastGreen),
                ContextCompat.getColor(context, R.color.colorContrastPurple),
                ContextCompat.getColor(context, R.color.colorContrastBlue),
        };

        DebugWordRepository wordRepo = DebugWordRepository.getInstance();
        List<Category> categories = wordRepo.getCategories().blockingGet();

        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);

            cardList.add(
                    new CategoryCardItemViewModel(
                            category,
                            BackgroundColors[i % BackgroundColors.length],
                            DetailColors[i % DetailColors.length]
                    )
            );
        }
    }

    public List<CategoryCardItemViewModel> getCardList() {
        return cardList;
    }
}
