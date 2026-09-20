package br.com.restful.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restful.controllers.PersonController;
import br.com.restful.dto.V1.PersonDto;
import br.com.restful.dto.V2.PersonDtoV2;
import br.com.restful.exception.ResourceNotFoundException;
import static br.com.restful.mapper.ObjectMapper.parseListObjects;
import static br.com.restful.mapper.ObjectMapper.parseObject;
import br.com.restful.mapper.custom.PersonMapper;
import br.com.restful.model.Person;
import br.com.restful.repository.PersonRepository;

@Service
public class PersonService {

    
    private Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapper converter;
    

    

    public List<PersonDto> findAll() {
        logger.info("Finding all people!");

        
        return parseListObjects(repository.findAll(), PersonDto.class);
    }

    public PersonDto findById(Long id) {
        logger.info("Finding one person!");

       var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));

        return parseObject(entity, PersonDto.class);

    }

    public PersonDto create(PersonDto person) {
        logger.info("Creating one person!");

        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDto.class);
    }

    public PersonDtoV2 createV2(PersonDtoV2 person) {
        logger.info("Creating one person V2!");
        
        var entity = converter.convertToEntity(person);

        return converter.convertToDtoV2(repository.save(entity));
    }

    public PersonDto update(PersonDto person) {
        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

       return parseObject(repository.save(entity), PersonDto.class);

    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);

    }
}
