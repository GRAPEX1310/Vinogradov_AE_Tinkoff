package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("Test HackerNews")
    void testHackerNews() {
        long[] resultArray = HackerNews.hackerNewsTopStories();

        assertThat(resultArray).isNotEmpty();

        for (int index = 0; index < 5 && index < resultArray.length; index++) {
            assertThat(HackerNews.news(resultArray[index])).isNotNull();
        }
    }

    @Test
    @DisplayName("HackerNews news test")
    void testNews() {

        String result = HackerNews.news(37570037);

        assertThat(result).isEqualTo("JDK 21 Release Notes");
    }
}
