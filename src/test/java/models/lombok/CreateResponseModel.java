package models.lombok;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class CreateResponseModel {

    String name;
    String job;
    String id;
    String createdAt;
}
