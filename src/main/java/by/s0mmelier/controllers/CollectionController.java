package by.s0mmelier.controllers;

import by.s0mmelier.models.Theme;
import by.s0mmelier.service.*;
import by.s0mmelier.Dto.CollectionBitMaskDto;
import by.s0mmelier.collections.Collection;
import by.s0mmelier.payload.request.CollectionRequest;
import by.s0mmelier.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class CollectionController {

    @Autowired
    UserService userService;

    @Autowired
    BookCollectionService bookCollectionService;

    @Autowired
    AlcoholCollectionService alcoholCollectionService;

    @Autowired
    MarkCollectionService markCollectionService;

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImageService imageService;

    @GetMapping("{id}/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean createCollection(){
        Theme themeBooks = new Theme();
        themeBooks.setName("Books");
        if(!themeRepository.existsByName("Books")) themeRepository.save(themeBooks);

        Theme themeAlcohol = new Theme();
        themeAlcohol.setName("Alcohol");
        if(!themeRepository.existsByName("Alcohol")) themeRepository.save(themeAlcohol);


        Theme themeMarks = new Theme();
        themeMarks.setName("Marks");
        if(!themeRepository.existsByName("Marks")){
            themeRepository.save(themeMarks);
        }
        return true;
    }

    @PostMapping("{id}/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void createCollection(@PathVariable("id") long id,
                                    @Valid @ModelAttribute CollectionRequest collectionRequest) throws Exception{
        if(collectionRequest.getTheme().equals("Books")){
            userService.addBookCollection(id, bookCollectionService.createCollection(collectionRequest)); }
        if(collectionRequest.getTheme().equals("Alcohol")) {
            userService.addAlcoholCollection(id, alcoholCollectionService.createCollection(collectionRequest)); }
        if(collectionRequest.getTheme().equals("Marks")){
            userService.addMarkCollection(id, markCollectionService.createCollection(collectionRequest)); }
    }

    @GetMapping("user/{userId}/{collectionType}/{collectionId}")
    public Collection getCollection(@PathVariable("userId") long userId,
                                    @PathVariable("collectionId") long collectionId,
                                    @PathVariable("collectionType") String collectionType){
        Collection collection;
        if(collectionType.equals("bc")) return bookCollectionService.getBookCollection(collectionId);
        else if(collectionType.equals("ac")) return alcoholCollectionService.getAlcoholCollection(collectionId);
        else return markCollectionService.getMarkCollection(collectionId);
    }


    @RequestMapping(value = "user/{userId}/{collectionType}/{collectionId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteCollection( @PathVariable("collectionId") long collectionId,
                                    @PathVariable("collectionType") String collectionType) {
        if(collectionType.equals("bc")) bookCollectionService.deleteBookCollection(collectionId);
        if(collectionType.equals("ac")) alcoholCollectionService.deleteAlcoholCollection(collectionId);
    }

    @RequestMapping(value = "user/{userId}/{collectionType}/{collectionId}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void updateCollection(@PathVariable("userId") long userId,
                                 @PathVariable("collectionId") long collectionId,
                                 @PathVariable("collectionType") String collectionType,
                                 @Valid @ModelAttribute CollectionRequest collectionRequest) throws IOException {
        if(collectionType.equals("bc")){ // bc = book collection type on URL
            bookCollectionService.updateCollection(collectionRequest, collectionId); }
        if(collectionType.equals("ac")) { //bc = alcohol collection type on URL
            alcoholCollectionService.updateCollection(collectionRequest, collectionId); }
        if(collectionType.equals("mc")){ //mc = mark collection type on URL
            markCollectionService.updateCollection(collectionRequest, collectionId); }
    }

    @RequestMapping(value = "user/{userId}/{collectionType}/{collectionId}/bitMask", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void setBitMask(@PathVariable("userId") long userId,
                                 @PathVariable("collectionId") long collectionId,
                                 @PathVariable("collectionType") String collectionType,
                                 @RequestParam("bitMask") long bitMask) throws IOException {
        System.out.println(bitMask);
        if(collectionType.equals("bc")){ // bc = book collection type on URL
            bookCollectionService.setBitMask(bitMask, collectionId); }
        if(collectionType.equals("ac")) { //bc = alcohol collection type on URL
            alcoholCollectionService.setBitMask(bitMask, collectionId); }
        if(collectionType.equals("mc")){ //mc = mark collection type on URL
            markCollectionService.setBitMask(bitMask, collectionId); }
    }

    @GetMapping("bookCollection/{collectionId}/bitMask")
    public CollectionBitMaskDto getBookCollectionBitMask(@PathVariable("collectionId") long collectionId){
        CollectionBitMaskDto bitMaskDto = new CollectionBitMaskDto();
        bitMaskDto.setBitMask(bookCollectionService.getBookCollection(collectionId).getBitMask());
        return bitMaskDto;
    }

    @GetMapping("alcoholCollection/{collectionId}/bitMask")
    public CollectionBitMaskDto getAlcoholCollectionBitMask(@PathVariable("collectionId") long collectionId){
        CollectionBitMaskDto bitMaskDto = new CollectionBitMaskDto();
        bitMaskDto.setBitMask(alcoholCollectionService.getAlcoholCollection(collectionId).getBitMask());
        return bitMaskDto;
    }


}
