package com.anikur.SpringEcom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @JsonProperty("isAvailable")
    private Boolean isAvailable;

    private Integer stockQuantity;

    private String imageType;
    private String imageName;
    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] imageData;
}
