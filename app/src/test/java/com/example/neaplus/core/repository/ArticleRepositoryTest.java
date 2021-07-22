package com.example.neaplus.core.repository;

import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.model.Source;
import com.example.neaplus.core.resource.ArticlesData;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class ArticleRepositoryTest {
    // Menguji apakah data semua article pertama dari output ArticleRepositoryMock sama dengan yang diekpetasikan
    @Test
    public void testGetAllArticle() {
        // Expected Data
        String expectedSourceId = "associated-press";
        String expectedSourceName = "Associated Press";
        String expectedTitle = "Germany defends preparation for floods, considers lessons - Associated Press";
        String expectedDescription = "BERLIN (AP) — German officials are defending their preparations for flooding in the face of the raging torrents that caught many people by surprise and left over 190 people dead in Western Europe, but concede that they will need to learn lessons from the disa…";
        String expectedUrl = "https://apnews.com/article/europe-germany-floods-662f251959433bdbbc251631460e4314";
        String expectedUrlToImage = "https://storage.googleapis.com/afs-prod/media/3ca395320d00463d81967a26e25e5353/3000.jpeg";
        String expectedPublishedAt = "2021-07-19T10:01:34Z";
        String expectedContent = "BERLIN (AP) German officials are defending their preparations for flooding in the face of the raging torrents that caught many people by surprise and left over 190 people dead in Western Europe, but … [+4255 chars]";

        ArticleRepositoryMock arm = new ArticleRepositoryMock();
        ArrayList<Articles> art = arm.getAllArticle();

        // Actual Data
        String actualSourceId = art.get(0).getSource().getId();
        String actualSourceName = art.get(0).getSource().getName();
        String actualTitle = art.get(0).getTitle();
        String actualDescription = art.get(0).getDescription();
        String actualUrl = art.get(0).getUrl();
        String actualUrlToImage = art.get(0).getUrlToImage();
        String actualPublishedAt = art.get(0).getPublishedAt();
        String actualContent = art.get(0).getContent();

        // Checking Data
        Assert.assertEquals(expectedSourceId, actualSourceId);
        Assert.assertEquals(expectedSourceName, actualSourceName);
        Assert.assertEquals(expectedTitle, actualTitle);
        Assert.assertEquals(expectedDescription, actualDescription);
        Assert.assertEquals(expectedUrl, actualUrl);
        Assert.assertEquals(expectedUrlToImage, actualUrlToImage);
        Assert.assertEquals(expectedPublishedAt, actualPublishedAt);
        Assert.assertEquals(expectedContent, actualContent);
    }

    // Menguji apakah title article yamg baru diinsert-kan sama dengan yang diekpetasikan
    @Test
    public void testInsert() {
        // Expected Data
        String expectedTitle = "New Corona Covid-19";

        ArticleRepositoryMock arm = new ArticleRepositoryMock();
        Articles a = new Articles(
                new Source("Ini Id_Source", "Ini Nama_Source"),
                "Ini Author",
                "New Corona Covid-19",
                "Ini Deskripsi",
                "Ini Url",
                "Ini urlToImage",
                "Ini publishedAt",
                "Ini content"
        );
        arm.insert(a);
        ArrayList<Articles> art = arm.getAllArticle();

        // Actual Data
        String actualTitle = art.get(art.size() - 1).getTitle();

        // Checking Data
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    // Menguji apakah title article yamg dicari berdasarkan url sama dengan yang diekpetasikan
    @Test
    public void testGetArticle() {
        // Expected Data
        String expectedTitle = "WWE Money in the Bank 2021: Results, John Cena return, match ratings and analysis - CNET";
        String expectedUrl = "https://www.cnet.com/news/wwe-money-in-the-bank-2021-results-john-cena-return-match-ratings-and-analysis/";

        ArticleRepositoryMock arm = new ArticleRepositoryMock();

        // Actual Data
        String actualTitle = arm.getArticle(expectedUrl);

        // Checking Data
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    // Menguji apakah article yamg sudah dihapus akan tetap ada ketika dicari kembali berdasarkan urlnya
    @Test
    public void testDelete() {
        // Expected Data
        String expectedDeletedTitle = "WWE Money in the Bank 2021: Results, John Cena return, match ratings and analysis - CNET";
        String expectedDeletedUrl = "https://www.cnet.com/news/wwe-money-in-the-bank-2021-results-john-cena-return-match-ratings-and-analysis/";

        ArticleRepositoryMock arm = new ArticleRepositoryMock();
        arm.delete(ArticlesData.getListData().get(1));

        // Actual Data
        String actualTitle = arm.getArticle(expectedDeletedUrl); // [NOT FOUND]

        // Checking Data
        Assert.assertNotEquals(expectedDeletedTitle, actualTitle);
    }

    // Menguji apakah semua article (2 article) yamg sudah dihapus akan tetap ada ketika dicari kembali berdasarkan urlnya
    @Test
    public void testDeleteAll() {
        // Expected Data
        String expectedDeletedTitle1 = "Germany defends preparation for floods, considers lessons - Associated Press";
        String expectedDeletedUrl1 = "https://apnews.com/article/europe-germany-floods-662f251959433bdbbc251631460e4314";
        String expectedDeletedTitle2 = "WWE Money in the Bank 2021: Results, John Cena return, match ratings and analysis - CNET";
        String expectedDeletedUrl2 = "https://www.cnet.com/news/wwe-money-in-the-bank-2021-results-john-cena-return-match-ratings-and-analysis/";

        ArticleRepositoryMock arm = new ArticleRepositoryMock();
        arm.deleteAll();

        // Actual Data
        String actualTitle1 = arm.getArticle(expectedDeletedUrl1); // [NOT FOUND]
        String actualTitle2 = arm.getArticle(expectedDeletedUrl2); // [NOT FOUND]

        // Checking Data
        Assert.assertNotEquals(expectedDeletedTitle1, actualTitle1);
        Assert.assertNotEquals(expectedDeletedTitle2, actualTitle2);
    }
}