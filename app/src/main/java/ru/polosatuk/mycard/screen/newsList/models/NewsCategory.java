package ru.polosatuk.mycard.screen.newsList.models;

public enum NewsCategory {
    HOME("home"),
    OPINION("opinion"),
    NATIONAL("national"),
    POLITICS("politics"),
    UPSHOT("upshot"),
    NYREGION("nyregion"),
    BUISNESS("business"),
    TECHNOLOGY("technology"),
    SCIENCE("science"),
    HEALTH("health"),
    SPORTS("sports"),
    BOOKS("books"),
    MOVIES("movies"),
    THEATER("theater"),
    SUNDAYREVIEW("sundayreview"),
    FASHION("fashion"),
    TMAGAZINE("tmagazine"),
    FOOD("food"),
    TRAVEL("travel"),
    MAGAZINE("magazine"),
    REALESTATE("realestate"),
    AUTOMOBILES("automobiles"),
    OBITUARIES("obituaries"),
    INSIDER("insider"),
    ARTS("arts");
    private final String name;

    NewsCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}




