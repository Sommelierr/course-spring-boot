package by.s0mmelier.controllers;

import by.s0mmelier.Dto.LikeStatusDto;
import by.s0mmelier.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/{userId}/{collectionType}/{itemId}")
public class LikeController {
    @Autowired
    LikeService likeService;

    @PostMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> like(@PathVariable("userId") long userId,
                                  @PathVariable("collectionType") String collectionType,
                                  @PathVariable("itemId") long itemId){
        if(collectionType.equals("bc")) likeService.likeBook(itemId, userId);
        if(collectionType.equals("ac")) likeService.likeAlcohol(itemId,userId);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public LikeStatusDto getLikeStatus(@PathVariable("userId") long userId,
                                       @PathVariable("collectionType") String collectionType,
                                       @PathVariable("itemId") long itemId) {
        if(collectionType.equals("bc")) return likeService.getBookLikeStatus(itemId, userId);
        else  return likeService.getAlcoholLikeStatus(itemId, userId);
    }

    @PostMapping("/unlike")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void unlike(@PathVariable("userId") long userId,
                       @PathVariable("collectionType") String collectionType,
                       @PathVariable("itemId") long itemId){
        if(collectionType.equals("bc")) likeService.unlikeBook(itemId,userId);
        if(collectionType.equals("ac")) likeService.unlikeAlcohol(itemId,userId);
    }
}
