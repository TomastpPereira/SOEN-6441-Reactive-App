package models;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.SubmissionSentiment.singleSentiment;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SubmissionSentimentTest {

    SubmissionSentiment sentimentAnalyzer;
    Set<String> happySet;
    Set<String> sadSet;


    @Before
    public void init(){
        sentimentAnalyzer = new SubmissionSentiment();
        happySet = new HashSet<>(Arrays.asList("Happy", "Smile", "Joy"));
        sadSet = new HashSet<>(Arrays.asList("Unhappy", "Sad", "Cry", "Tears"));
    }

    @Test
    public void testSadSentiment(){
        String description = "Unhappy Unhappy Unhappy Unhappy Sad Sad Sad";

        String result = singleSentiment(description, happySet, sadSet);
        assertEquals (":-(", result, "Description should result in a Sad sentiment");
    }

    @Test
    public void testHappySentiment(){
        String description = "Smile Smile Smile Joy Joy Joy";

        String result = singleSentiment(description, happySet, sadSet);
        assertEquals (":-)", result, "Description should result in a Happy sentiment");
    }

    @Test
    public void testNeutralSentiment(){
        String description = "Smile Smile Smile Sad Sad Sad";

        String result = singleSentiment(description, happySet, sadSet);
        assertEquals (":-|", result, "Description should result in a Neutral sentiment");
    }

    @Test
    public void testEmptyDescription(){
        String description = "";

        String result = singleSentiment(description, happySet, sadSet);
        assertEquals (":-|", result, "Empty Description should result in a Neutral sentiment");
    }

    @Test
    public void testLoadSentimentFileSuccess(){
        String filePath = "test/models/happyTestFile.txt";

        Set<String> testSet = sentimentAnalyzer.loadSentimentFile(filePath);
        assertNotNull(testSet, "The set should not be null for a valid Sentiment File");
        assertEquals(Set.of("Happy", "Smile", "Joy"), testSet, "Words from loaded files do not match expected");
    }

    @Test
    public void testLoadSentimentFileFail(){
        String filePath = "test/models/fakeTestFile.txt";

        Set<String> testSet = sentimentAnalyzer.loadSentimentFile(filePath);
        assertNull(testSet);
    }

    @Test
    public void testDetermineSentimentHappy(){
        Video video1 = new Video("vidID1", "title1", "channelID1", "channelTitle1", "Happy Happy Happy", "thumb1");
        Video video2 = new Video("vidID2", "title2", "channelID2", "channelTitle2", "Joy Joy Joy", "thumb2");

        LinkedList<Video> vidList = new LinkedList<>();
        vidList.addFirst(video1);
        vidList.addFirst(video2);

        assertEquals(sentimentAnalyzer.determineSentiment(vidList), ":-)");
    }

    @After
    public void teardown(){
        sentimentAnalyzer = null;
        happySet = null;
        sadSet = null;
    }

}
