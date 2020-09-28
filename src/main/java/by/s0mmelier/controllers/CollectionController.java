package by.s0mmelier.controllers;

import by.s0mmelier.Dto.CollectionDto;
import by.s0mmelier.service.*;
import by.s0mmelier.Dto.CollectionBitMaskDto;
import by.s0mmelier.payload.request.CollectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @RequestMapping(value = "/{id}/create", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createCollection() throws IOException {
        collectionService.initThems();
    }

    @RequestMapping(value = "/{id}/create", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createCollection(@PathVariable("id") long id,
                                    @ModelAttribute CollectionRequest collectionRequest) throws Exception{
        collectionService.createCollection(id, collectionRequest);
    }

    @GetMapping("/user/{userId}/{collectionType}/{collectionId}")
    public CollectionDto getCollection(@PathVariable("userId") long userId,
                                       @PathVariable("collectionId") long collectionId,
                                       @PathVariable("collectionType") String collectionType){
        return collectionService.getCollectionDto(collectionType, collectionId);
    }


    @RequestMapping(value = "/user/{userId}/{collectionType}/{collectionId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteCollection( @PathVariable("collectionId") long collectionId,
                                    @PathVariable("collectionType") String collectionType) {
        collectionService.deleteCollection(collectionType, collectionId);
    }

    @RequestMapping(value = "/user/{userId}/{collectionType}/{collectionId}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateCollection(@PathVariable("userId") long userId,
                                 @PathVariable("collectionId") long collectionId,
                                 @PathVariable("collectionType") String collectionType,
                                 @Valid @ModelAttribute CollectionRequest collectionRequest) throws IOException {
        collectionService.updateCollection(collectionType,collectionId,collectionRequest);
    }

    @RequestMapping(value = "/user/{userId}/{collectionType}/{collectionId}/bitMask", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void setBitMask(@PathVariable("userId") long userId,
                                 @PathVariable("collectionId") long collectionId,
                                 @PathVariable("collectionType") String collectionType,
                                 @RequestParam("bitMask") long bitMask) throws IOException {
        collectionService.setBitMask(collectionType,collectionId,bitMask);
    }

    @GetMapping("/bookCollection/{collectionId}/bitMask")
    public CollectionBitMaskDto getBookCollectionBitMask(@PathVariable("collectionId") long collectionId){
        return collectionService.getBookCollectionBitMask(collectionId);
    }

    @GetMapping("/alcoholCollection/{collectionId}/bitMask")
    public CollectionBitMaskDto getAlcoholCollectionBitMask(@PathVariable("collectionId") long collectionId){
        return collectionService.getAlcoholCollectionBitMask(collectionId);
    }
}