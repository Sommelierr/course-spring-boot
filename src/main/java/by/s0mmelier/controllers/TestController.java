package by.s0mmelier.controllers;

import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.collections.MarkCollection;
import by.s0mmelier.models.Book;
import by.s0mmelier.repository.BookCollectionRepository;
import by.s0mmelier.service.CloudinaryService;
import by.s0mmelier.service.ImageService;
import by.s0mmelier.Dto.CollectionsListDto;
import by.s0mmelier.repository.BookRepository;
import by.s0mmelier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("/user/{id}/status")
	public boolean isBlocked(@PathVariable("id") Long id){
		System.out.println(userService.getUserById(id).isBlocked());
		return userService.getUserById(id).isBlocked();
	}

	@GetMapping("/user/{id}")
	public CollectionsListDto userAccess(@PathVariable("id") Long id) {
		CollectionsListDto collectionsListDto = new CollectionsListDto();
		collectionsListDto.setAlcoholCollections(new ArrayList<AlcoholCollection>());//userService.getUserById(id).getAlcoholCollections());
		collectionsListDto.setBookCollections(new ArrayList<BookCollection>());//userService.getUserById(id).getBookCollections());
		collectionsListDto.setMarkCollections(new ArrayList<MarkCollection>());//userService.getUserById(id).getMarkCollections());
		collectionsListDto.setBlocked(userService.getUserById(id).isBlocked());
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
