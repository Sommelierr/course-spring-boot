package by.s0mmelier.service;

import by.s0mmelier.Dto.AlcoholCollectionDto;
import by.s0mmelier.Dto.AlcoholDto;
import by.s0mmelier.Dto.BookCollectionDto;
import by.s0mmelier.Dto.HomeAlcoholDto;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.payload.request.AlcoholRequest;
import by.s0mmelier.utils.UtilService;
import by.s0mmelier.repository.AlcoholRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
public class AlcoholService {
    @Autowired
    AlcoholRepository alcoholRepository;

    @Autowired
    TagService tagService;

    @Autowired
    UtilService utilService;

    @Autowired
    AlcoholCollectionService alcoholCollectionService;

    public Optional<Alcohol> getAlcohol(long id){
        return alcoholRepository.findById(id);
    }

    public boolean createAlcohol(long collectionId, AlcoholRequest alcoholModel) throws ParseException {
        Alcohol alcohol = new Alcohol();
        alcohol.setName(alcoholModel.getName());
        alcohol.setTags(tagService.toTagList(alcoholModel.getTags()));
        alcohol.setCost(alcoholModel.getCost());
        alcohol.setPercent(alcoholModel.getPercent());
        alcohol.setVolume(alcoholModel.getVolume());
        alcohol.setManufacturer(alcoholModel.getManufacturer());
        alcohol.setGrade(alcoholModel.getGrade());
        alcohol.setManufactureCountry(alcoholModel.getManufactureCountry());
        alcohol.setHasOneLiter(alcoholModel.isHasOneLiter());
        alcohol.setHasTwoLiters(alcoholModel.isHasTwoLiters());
        alcohol.setHasFiveLiters(alcoholModel.isHasFiveLiters());
        alcohol.setComment(alcoholModel.getComment());
        alcohol.setHistory(alcoholModel.getHistory());
        alcohol.setRecommendation(alcoholModel.getRecommendation());
        alcohol.setManufactureDate(utilService.stringToDate(alcoholModel.getManufactureDate()));
        alcohol.setDevelopmentDate((utilService.stringToDate(alcoholModel.getDevelopmentDate())));
        alcohol.setManufactureDateInBelarus(utilService.stringToDate(alcoholModel.getManufactureDateInBelarus()));
        alcohol.setBitMask(alcoholCollectionService.getAlcoholCollection(collectionId).getBitMask());
        alcoholRepository.save(alcohol);
        alcoholCollectionService.addAlcohol(collectionId, alcohol);
        return true;
    }

    public boolean updateAlcohol(long alcoholId, AlcoholRequest alcoholModel) throws ParseException {
        Optional<Alcohol> alcohol = alcoholRepository.findById(alcoholId);
        alcohol.get().setName(alcoholModel.getName());
        alcohol.get().setTags(tagService.toTagList(alcoholModel.getTags()));
        alcohol.get().setCost(alcoholModel.getCost());
        alcohol.get().setPercent(alcoholModel.getPercent());
        alcohol.get().setVolume(alcoholModel.getVolume());
        alcohol.get().setManufacturer(alcoholModel.getManufacturer());
        alcohol.get().setGrade(alcoholModel.getGrade());
        alcohol.get().setManufactureCountry(alcoholModel.getManufactureCountry());
        alcohol.get().setHasOneLiter(alcoholModel.isHasOneLiter());
        alcohol.get().setHasTwoLiters(alcoholModel.isHasTwoLiters());
        alcohol.get().setHasFiveLiters(alcoholModel.isHasFiveLiters());
        alcohol.get().setComment(alcoholModel.getComment());
        alcohol.get().setHistory(alcoholModel.getHistory());
        alcohol.get().setRecommendation(alcoholModel.getRecommendation());
        alcohol.get().setManufactureDate(utilService.stringToDate(alcoholModel.getManufactureDate()));
        alcohol.get().setDevelopmentDate((utilService.stringToDate(alcoholModel.getDevelopmentDate())));
        alcohol.get().setManufactureDateInBelarus(utilService.stringToDate(alcoholModel.getManufactureDateInBelarus()));
        alcoholRepository.save(alcohol.get());
        return true;
    }

    public AlcoholDto getAlcoholDto(long id){
        Optional<Alcohol> alcohol = alcoholRepository.findById(id);
        AlcoholDto alcoholDto = new AlcoholDto();
        alcoholDto.setName(alcohol.get().getName());
        alcoholDto.setTags(tagService.tagsToStringList(alcohol.get().getTags()));
        alcoholDto.setCost(alcohol.get().getCost());
        alcoholDto.setPercent(alcohol.get().getPercent());
        alcoholDto.setVolume(alcohol.get().getVolume());
        alcoholDto.setManufacturer(alcohol.get().getManufacturer());
        alcoholDto.setGrade(alcohol.get().getGrade());
        alcoholDto.setManufactureCountry(alcohol.get().getManufactureCountry());
        alcoholDto.setHasOneLiter(alcohol.get().isHasOneLiter());
        alcoholDto.setHasTwoLiters(alcohol.get().isHasTwoLiters());
        alcoholDto.setHasFiveLiters(alcohol.get().isHasFiveLiters());
        alcoholDto.setComment(alcohol.get().getComment());
        alcoholDto.setHistory(alcohol.get().getHistory());
        alcoholDto.setRecommendation(alcohol.get().getRecommendation());
        alcoholDto.setManufactureDate(alcohol.get().getManufactureDate());
        alcoholDto.setDevelopmentDate(alcohol.get().getDevelopmentDate());
        alcoholDto.setManufactureDateInBelarus(alcohol.get().getManufactureDateInBelarus());
        alcoholDto.setBitMask(alcohol.get().getBitMask());
        return alcoholDto;
    }

    public void saveAlcohol(Alcohol alcohol){
        alcoholRepository.save(alcohol);
    }

    public void deleteAlcohol(long alcoholId){
        AlcoholCollection collection = alcoholCollectionService.getAlcoholCollection(alcoholRepository.findById(alcoholId)
                                                                .get().getAlcoholCollection().getId());
        collection.setCountOfAlcohols(collection.getCountOfAlcohols()-1);
        alcoholCollectionService.saveCollection(collection);
        alcoholRepository.deleteById(alcoholId);
    }

    public HomeAlcoholDto getLast(){
        if(alcoholRepository.findTopByOrderByIdDesc() != null) {
            HomeAlcoholDto alcoholDto = new HomeAlcoholDto();
            Alcohol alcohol = alcoholRepository.findTopByOrderByIdDesc();
            alcoholDto.setId(alcohol.getId());
            alcoholDto.setName(alcohol.getName());
            return alcoholDto;
        }
        else return null;
    }
}
