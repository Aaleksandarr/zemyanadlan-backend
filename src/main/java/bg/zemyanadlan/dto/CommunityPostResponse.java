package bg.zemyanadlan.dto;

import java.time.LocalDate;

public class CommunityPostResponse {
    private Long id;
    private String title;
    private String summary;
    private String imageUrl;
    private LocalDate publishedAt;
    private String category;

    public CommunityPostResponse(Long id, String title, String summary, String imageUrl, LocalDate publishedAt, String category) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public String getCategory() {
        return category;
    }
}
