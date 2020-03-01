package com.example.make_a_story_prototype.main.StoryTemplate.vm;

public class StoryBlankIdentifier implements StorySegment {
    private String identifier;

    public String get() {
        return identifier;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    public StoryBlankIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
