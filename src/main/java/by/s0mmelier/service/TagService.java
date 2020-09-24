package by.s0mmelier.service;

import by.s0mmelier.Dto.CloudTagDto;
import by.s0mmelier.models.Tag;
import by.s0mmelier.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public Tag getByName(String name){
        return tagRepository.findByName(name);
    }

    public Tag saveTag(Tag tag){
        tagRepository.save(tag);
        return tag;
    }

    public List<Tag> toTagList(List<String> tagNames){
        List<Tag> tags = new ArrayList<>();
        for(String name : tagNames) tags.add(getByName(name) == null? saveTag(new Tag(name)):getByName(name));
        return tags;
    }

    public List<String> tagsToStringList(List<Tag> tags){
        List<String> tagNames = new ArrayList<>();
        for(Tag tag : tags) tagNames.add(tag.getName());
        return tagNames;
    }

    public List<Tag> getAlltags(){
        return tagRepository.findAll();
    }

    public List<CloudTagDto> toCloudTags(List<Tag> tags){
        if(tags != null){
            List<CloudTagDto> cloudTagDtos = new ArrayList<>();
            for(Tag tag : tags){
                CloudTagDto cloudTagDto = new CloudTagDto(tag);
                cloudTagDtos.add(cloudTagDto);
            }
            return cloudTagDtos;
        }
        else return null;
    }

}
