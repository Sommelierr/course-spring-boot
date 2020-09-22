package by.s0mmelier.controllers;

import by.s0mmelier.Dto.HomeCollectionDto;
import by.s0mmelier.service.*;
import by.s0mmelier.Dto.CollectionBitMaskDto;
import by.s0mmelier.payload.request.CollectionRequest;
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
    CollectionService collectionService;

    @GetMapping("{id}/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createCollection() throws IOException {
        collectionService.initThems();
    }

    @PostMapping("{id}/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createCollection(@PathVariable("id") long id,
                                    @Valid @ModelAttribute CollectionRequest collectionRequest) throws Exception{
        if(collectionRequest.getTheme().equals("Books")){
            userService.addBookCollection(id, bookCollectionService.createCollection(collectionRequest)); }
        if(collectionRequest.getTheme().equals("Alcohol")) {
            userService.addAlcoholCollection(id, alcoholCollectionService.createCollection(collectionRequest)); }
    }

    @GetMapping("user/{userId}/{collectionType}/{collectionId}")
    public HomeCollectionDto getCollection(@PathVariable("userId") long userId,
                                           @PathVariable("collectionId") long collectionId,
                                           @PathVariable("collectionType") String collectionType){
        if(collectionType.equals("bc")) return bookCollectionService.getBookCollectionDto(collectionId);
        if(collectionType.equals("ac")) return alcoholCollectionService.getAlcoholCollectionDto(collectionId);
        else return null;
    }


    @RequestMapping(value = "user/{userId}/{collectionType}/{collectionId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteCollection( @PathVariable("collectionId") long collectionId,
                                    @PathVariable("collectionType") String collectionType) {
        if(collectionType.equals("bc")) bookCollectionService.deleteBookCollection(collectionId);
        if(collectionType.equals("ac")) alcoholCollectionService.deleteAlcoholCollection(collectionId);
    }

    @RequestMapping(value = "user/{userId}/{collectionType}/{collectionId}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateCollection(@PathVariable("userId") long userId,
                                 @PathVariable("collectionId") long collectionId,
                                 @PathVariable("collectionType") String collectionType,
                                 @Valid @ModelAttribute CollectionRequest collectionRequest) throws IOException {
        if(collectionType.equals("bc")){ // bc = book collection type on URL
            bookCollectionService.updateCollection(collectionRequest, collectionId); }
        if(collectionType.equals("ac")) { //bc = alcohol collection type on URL
            alcoholCollectionService.updateCollection(collectionRequest, collectionId); }
    }

    @RequestMapping(value = "user/{userId}/{collectionType}/{collectionId}/bitMask", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void setBitMask(@PathVariable("userId") long userId,
                                 @PathVariable("collectionId") long collectionId,
                                 @PathVariable("collectionType") String collectionType,
                                 @RequestParam("bitMask") long bitMask) throws IOException {
        if(collectionType.equals("bc")){ // bc = book collection type on URL
            bookCollectionService.setBitMask(bitMask, collectionId); }
        if(collectionType.equals("ac")) { //bc = alcohol collection type on URL
            alcoholCollectionService.setBitMask(bitMask, collectionId); }
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