package by.s0mmelier.controllers;

import by.s0mmelier.repository.BookCollectionRepository;
import by.s0mmelier.service.CloudinaryService;
import by.s0mmelier.service.ImageService;
import by.s0mmelier.Dto.CollectionsListDto;
import by.s0mmelier.repository.BookRepository;
import by.s0mmelier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	UserService userService;

	@Autowired
    BookCollectionRepository bookCollectionRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
    CloudinaryService cloudinaryService;

	@Autowired
    ImageService imageService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user/{id}")
	public CollectionsListDto userAccess(@PathVariable("id") Long id) {
		CollectionsListDto collectionsListDto = new CollectionsListDto();
		collectionsListDto.setAlcoholCollections(userService.getUserById(id).getAlcoholCollections());
		collectionsListDto.setBookCollections(userService.getUserById(id).getBookCollections());
		collectionsListDto.setMarkCollections(userService.getUserById(id).getMarkCollections());
		return collectionsListDto;
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
