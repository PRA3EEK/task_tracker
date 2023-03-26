package com.task_tracker.requests;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class CommentRequest {
    
	@NonNull
	private Long userId;
	@NonNull
	@NotBlank
	private String comment;
	
}
