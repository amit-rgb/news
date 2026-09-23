package com.bheemnagartimes.news.service;

import com.bheemnagartimes.news.model.NewsArticle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArticleGeneratorServiceTest {
    @Test
    void localFormatterCreatesStructuredArticle() {
        ArticleGeneratorService service = new ArticleGeneratorService(
                new ObjectMapper(), false, "http://localhost", "", "test");
        NewsArticle article = service.generate(
                "सरकार ने मेडिकल स्टोरों में सीसीटीवी लगाने का प्रस्ताव रखा। संबंधित पक्षों से राय मांगी गई है।",
                "22 सितंबर 2026", "भीमनगर", "");
        assertEquals("जनहित में बड़ा फैसला", article.getSection());
        assertTrue(article.getHeadline().contains("सीसीटीवी"));
        assertNotNull(article.getBody());
        assertFalse(article.getBody().isBlank());
    }
}