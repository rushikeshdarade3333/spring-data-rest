package com.example.springdatarest.entity;



import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @NotBlank(message = "First name cannot be blank")
	private String firstName;
    
    @NotBlank(message = "Last name cannot be blank")
	private String lastName;
   
    @Email(message = "Invalid email address")
	private String email;
    


    // Example pattern for a phone number (adjust based on your needs)
    @Digits(integer = 10, fraction = 0, message = "Invalid phone number format")    
    private long phoneNumber;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private int age; 
    

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address must be less than or equal to 255 characters")
	private String address;
	
	

	 @PrePersist
	    public void validate() throws ValidationException {
	        // Create a ValidatorFactory and a Validator
	        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        Validator validator = factory.getValidator();

	        // Validate this AppUser instance
	        Set<ConstraintViolation<AppUser>> violations = validator.validate(this);

	        // If there are violations, throw a ValidationException with a proper message
	        if (!violations.isEmpty()) {
	            StringBuilder validationMessage = new StringBuilder("Validation error(s):\n");
	            for (ConstraintViolation<AppUser> violation : violations) {
	                validationMessage.append("- ").append(violation.getMessage()).append("\n");
	            }
	            throw new ValidationException(validationMessage.toString());
	        }
	    }
}

