package by.s0mmelier.controllers;

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
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AlcoholController {

    @Autowired
    AlcoholService alcoholService;

    @PostMapping("alcohol/{collectionId}/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void createAlcohol(@PathVariable("collectionId") long collectionId,
                           @Valid @ModelAttribute AlcoholRequest alcoholModel) throws Exception{
        alcoholService.createAlcohol(collectionId,alcoholModel);
    }

    @GetMapping("alcohol/{alcoholId}")
    public Optional<Alcohol> getAlcohol(@PathVariable("alcoholId") long alcoholId){
        return alcoholService.getAlcohol(alcoholId);
    }

    @PostMapping("alcohol/{alcoholId}/bitMask")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void setAlcoholBitMask(@PathVariable("alcoholId") long alcoholId, @RequestParam("bitMask") long bitMask){
        Alcohol alcohol = alcoholService.getAlcohol(alcoholId).get();
        alcohol.setBitMask(bitMask);
        alcoholService.saveAlcohol(alcohol);
    }

    @RequestMapping(value = "alcohol/{collectionId}/{alcoholId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteAlcohol(@PathVariable("alcoholId") long alcoholId) {
        alcoholService.deleteAlcohol(alcoholId);
    }

    @RequestMapping(value = "alcohol/{alcoholId}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void updateAlcohol(@PathVariable("bookId") long bookId,
                                 @Valid @ModelAttribute AlcoholRequest alcoholModel) throws IOException, ParseException {
        alcoholService.updateAlcohol(bookId, alcoholModel);
    }
}
