package com.example.urlparser;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlBrandPair {
    private String url;
    private String brand;
}
