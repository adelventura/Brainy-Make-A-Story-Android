package com.example.make_a_story_prototype.main.data.StoryTemplateSelections;

import com.example.make_a_story_prototype.main.data.StoryTemplateSelections.model.BlankSelection;

import java.util.Map;

public interface StoryTemplateSelectionsRepository {

    Map<String, BlankSelection> getSelectionsForStory(int storyId);

    void setSelectionsForStory(int storyId, Map<String, BlankSelection> selections);

    void setSelectionForStory(int storyId, String identifier, BlankSelection selection);
}
