package employee.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Position {
	@Id private String id;

	@NotNull(message = "Title is required") 
	private String title; // TODO: should be an unique

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
