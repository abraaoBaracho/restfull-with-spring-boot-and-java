package br.com.restful.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.restful.dto.V2.PersonDtoV2;
import br.com.restful.model.Person;

@Service 
public class PersonMapper {

    public PersonDtoV2 convertToDtoV2(Person person) {
        PersonDtoV2 personDtoV2 = new PersonDtoV2();
        personDtoV2.setId(person.getId());
        personDtoV2.setFirstName(person.getFirstName());
        personDtoV2.setLastName(person.getLastName());
        personDtoV2.setBirthDay(new Date());
        personDtoV2.setAddress(person.getAddress());
        personDtoV2.setGender(person.getGender());
        
        return personDtoV2;
    }

    public Person convertToEntity(PersonDtoV2 personDtoV2) {
        Person person = new Person();
        person.setId(personDtoV2.getId());
        person.setFirstName(personDtoV2.getFirstName());
        person.setLastName(personDtoV2.getLastName());
        person.setAddress(personDtoV2.getAddress());
        person.setGender(personDtoV2.getGender());
        return person;
    }
}
