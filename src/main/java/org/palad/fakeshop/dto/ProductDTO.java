package org.palad.fakeshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.palad.fakeshop.domain.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pid;

    @NotBlank
    private String title;

    @NotBlank
    private Long price;

    private String description;

    @NotBlank
    private String category;


    private String image;

    @JsonIgnore
    private Double rate;

    @JsonIgnore
    private Long count;


    //TODO : rate, count null 처리
    public Map<String, Number> getRating() {
        Map<String, Number> map = new HashMap<>();
        map.put("rate", rate);
        map.put("count", count);
        return map;
    }

}
