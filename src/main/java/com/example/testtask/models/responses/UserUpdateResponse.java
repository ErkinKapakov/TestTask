package com.example.testtask.models.responses;

import com.example.testtask.models.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserUpdateResponse {
    Long id;
    Status status;
    Status previousStatus;
}
