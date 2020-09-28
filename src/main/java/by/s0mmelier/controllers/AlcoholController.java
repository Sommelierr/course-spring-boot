package by.s0mmelier.controllers;

import by.s0mmelier.Dto.AlcoholDto;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.payload.request.AlcoholRequest;
import by.s0mmelier.service.AlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AlcoholController {

    @Autowired
    AlcoholService alcoholService;

    @PostMapping("alcohol/{collectionId}/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createAlcohol(@PathVariable("collectionId") long collectionId,
                           @Valid @ModelAttribute AlcoholRequest alcoholModel) throws Exception{
        alcoholService.createAlcohol(collectionId,alcoholModel);
    }

    @GetMapping("alcohol/{alcoholId}")
    public AlcoholDto getAlcohol(@PathVariable("alcoholId") long alcoholId){
        return alcoholService.getAlcoholDto(alcoholId);
    }

    @PostMapping("alcohol/{alcoholId}/bitMask")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void setAlcoholBitMask(@PathVariable("alcoholId") long alcoholId, @RequestParam("bitMask") long bitMask){
        if(alcoholService.getAlcohol(alcoholId) != null) {
            Alcohol alcohol = alcoholService.getAlcohol(alcoholId).get();
            alcohol.setBitMask(bitMask);
            alcoholService.saveAlcohol(alcohol);
        }
    }

    @RequestMapping(value = "alcohol/{alcoholId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteAlcohol(@PathVariable("alcoholId") long alcoholId) {
        if(alcoholService.getAlcohol(alcoholId) != null) {
            alcoholService.deleteAlcohol(alcoholId);
        }
    }

    @RequestMapping(value = "alcohol/{alcoholId}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateAlcohol(@PathVariable("alcoholId") long alcoholId,
                                 @Valid @ModelAttribute AlcoholRequest alcoholModel) throws IOException, ParseException {
        alcoholService.updateAlcohol(alcoholId, alcoholModel);
    }
}
