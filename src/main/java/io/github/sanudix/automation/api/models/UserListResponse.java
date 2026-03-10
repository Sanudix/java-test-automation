package io.github.sanudix.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponse {
    private int page;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("per_page")
    private int perPage;
    private List<User> data;
}
