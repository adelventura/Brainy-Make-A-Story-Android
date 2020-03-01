package com.example.make_a_story_prototype.main.StoryTemplate.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.make_a_story_prototype.R;
import com.example.make_a_story_prototype.main.Categories.view.CategoriesActivity;
import com.example.make_a_story_prototype.main.Home.view.HomeActivity;
import com.example.make_a_story_prototype.main.StoryTemplate.vm.Story;
import com.example.make_a_story_prototype.main.StoryTemplate.vm.StoryBlank;
import com.example.make_a_story_prototype.main.StoryTemplate.vm.StoryPage;
import com.example.make_a_story_prototype.main.StoryTemplate.vm.StorySegment;
import com.example.make_a_story_prototype.main.StoryTemplate.vm.StoryTextViewModel;
import com.example.make_a_story_prototype.main.StoryTemplate.vm.TemplateViewModel;
import com.example.make_a_story_prototype.main.Util.Util;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StoryTemplateActivity extends AppCompatActivity {

    private TemplateViewModel vm;
    private StoryTextViewModel stvm;
    private Story story;
    private ImageView storyImage;
    private String storyTitle;
    private TextView storyTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_template);

        View view = findViewById(R.id.constraint_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Util.themeStatusBar(this, true);
        Util.addBackArrow(this);

        storyTitle = getIntent().getStringExtra("source");
        TextView screenTitle = toolbar.findViewById(R.id.toolbar_title);
        screenTitle.setText(storyTitle);
        vm = new TemplateViewModel(storyTitle);

        stvm = new StoryTextViewModel();
        story = stvm.getStory();

        storyImage = findViewById(R.id.story_image);
        storyImage.setImageResource(vm.getStoryImage());

        storyTextView = findViewById(R.id.story_text);
        setStoryTextView(0);

        Toolbar controlsbar = findViewById(R.id.controls_bar);
    }

    // home icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
                return true;
            case R.id.home_menu_icon:
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setStoryTextView(int pageNum) {
        if (pageNum >= story.getPages().size()) {
            Log.d("Tag", "setTextView --> requested page number > # of pages");
            return;
        }

        StoryPage currentPage = story.getPages().get(pageNum);
        List<StorySegment> segments = currentPage.getSegments();
        List<StoryBlank> blanks = story.getBlanks();

        String text = "";

        int i = 0;
        for (StorySegment s: segments) {
            text += s.toString();
            StoryBlank currBlank = blanks.get(i);
            text += currBlank.toString();
            i++;
        }


        List<Integer[]> clickableBounds = new ArrayList<>();
        for (int j = 0; j < text.length() - 4; j++) {
            if (text.charAt(j) == 'B') {
                if (text.charAt(j + 4) == 'K') {
                    Integer[] arr = { j, j + 5};
                    clickableBounds.add(arr);
                }
            }
        }

        storyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        storyTextView.setText(text, TextView.BufferType.SPANNABLE);
        Spannable spannableTextView = (Spannable) storyTextView.getText();

        for (Integer[] arr: clickableBounds) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    Log.d("Tag", "Clicked: blank");
                    startActivity(new Intent(getApplicationContext(), CategoriesActivity.class));
                }
            };
            spannableTextView.setSpan(clickableSpan, arr[0], arr[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

    }
}


