package io.axoniq.foodordering.command.handler.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {

	private final String timestamp;
	private final String message;
	
}
