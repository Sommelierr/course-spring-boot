package by.s0mmelier.controllers;

import by.s0mmelier.Dto.CollectionsListDto;
import by.s0mmelier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/{id}/status")
	public boolean isBlocked(@PathVariable("id") Long id){
		return userService.getUserById(id).isBlocked();
	}

	@GetMapping("/{id}")
	public CollectionsListDto userCollections(@PathVariable("id") Long id) {
		CollectionsListDto collectionsListDto = new CollectionsListDto();
		collectionsListDto.setAlcoholCollections(userService.getUserById(id).getAlcoholCollections());
		collectionsListDto.setBookCollections(userService.getUserById(id).getBookCollections());
		collectionsListDto.setBlocked(userService.getUserById(id).isBlocked());
		return collectionsListDto;
	}
}
