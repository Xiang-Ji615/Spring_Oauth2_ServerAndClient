package main.java.spring.boot.oauth2.controller;



import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="Rest")
public class RestfulController {

	@RequestMapping(value="Test", method=RequestMethod.GET, produces=MediaType.ALL_VALUE)
	public ResponseEntity<?> test(){
		return ResponseEntity.ok().body("JJ TEST");
	}
}
