package ch.keepcalm.keepcalmfood.graphql.person

 import org.springframework.stereotype.Service

@Service
class PersonService(var personRepository: PersonRepository) {

    fun getAllPersons() = personRepository.findAll()

    fun savePerson(person : Person) = personRepository.save(person)


}