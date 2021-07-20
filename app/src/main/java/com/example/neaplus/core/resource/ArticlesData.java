package com.example.neaplus.core.resource;

import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.model.Source;

import java.util.ArrayList;

public class ArticlesData {

    private static Source[] articleSource = {
            new Source("associated-press", "Associated Press"),
            new Source(null, "CNET")
    };

    private static String[] articleAuthor = {
            "Geir Moulson",
            "Daniel Van Boom"
    };

    private static String[] articleTitle = {
            "Germany defends preparation for floods, considers lessons - Associated Press",
            "WWE Money in the Bank 2021: Results, John Cena return, match ratings and analysis - CNET"
    };

    private static String[] articleDescription = {
            "BERLIN (AP) — German officials are defending their preparations for flooding in the face of the raging torrents that caught many people by surprise and left over 190 people dead in Western Europe, but concede that they will need to learn lessons from the disa…",
            "John Cena returned at the end of Money in the Bank after Roman Reigns pinned Edge to retain his Universal Championship."
    };

    private static String[] articleUrl = {
            "https://apnews.com/article/europe-germany-floods-662f251959433bdbbc251631460e4314",
            "https://www.cnet.com/news/wwe-money-in-the-bank-2021-results-john-cena-return-match-ratings-and-analysis/"
    };

    private static String[] articleUrlToImage = {
            "https://storage.googleapis.com/afs-prod/media/3ca395320d00463d81967a26e25e5353/3000.jpeg",
            "https://www.cnet.com/a/img/OV0yQcSf03qakHqfaY8Io14Bl0M=/1200x630/2021/07/19/f6670186-b2df-4cdf-af42-32c1aed86dd0/e6ork1kwqaq6tcv.jpg"
    };

    private static String[] articlePublishedAt = {
            "2021-07-19T10:01:34Z",
            "2021-07-19T08:42:00Z"
    };

    private static String[] articleContent = {
            "BERLIN (AP) German officials are defending their preparations for flooding in the face of the raging torrents that caught many people by surprise and left over 190 people dead in Western Europe, but … [+4255 chars]",
            "Before the match we got a backstage segment in which Rollins blamed Edge for taking him out of the title picture, and declared he'd be next in line for whoever wins the main event. That played into t… [+2315 chars]"
    };

    public static ArrayList<Articles> getListData() {
        ArrayList<Articles> list = new ArrayList<>();
        for (int position = 0; position < articleTitle.length; position++) {
            Articles articles = new Articles();
            articles.setTitle(articleTitle[position]);
            articles.setAuthor(articleAuthor[position]);
            articles.setContent(articleContent[position]);
            articles.setDescription(articleDescription[position]);
            articles.setPublishedAt(articlePublishedAt[position]);
            articles.setSource(articleSource[position]);
            articles.setUrl(articleUrl[position]);
            articles.setUrlToImage(articleUrlToImage[position]);

            list.add(articles);
        }
        return list;
    }

    ;
}
