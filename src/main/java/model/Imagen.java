package model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "imagenes")
public class Imagen {
	
	    @Id
	    private String id;
	    
	    private String title;
	        
	    private Binary image;
	

}
