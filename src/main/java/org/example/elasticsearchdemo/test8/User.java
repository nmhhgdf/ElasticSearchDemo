package org.example.elasticsearchdemo.test8;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
