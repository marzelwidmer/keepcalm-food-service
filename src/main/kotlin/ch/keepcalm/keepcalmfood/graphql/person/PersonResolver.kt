package ch.keepcalm.keepcalmfood.graphql.person

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Service

@Service
class PersonResolver(var personRepository: PersonRepository) : GraphQLResolver<Person> {

    fun getAllPersons() = personRepository.findAll()

    fun savePerson(person: Person) = personRepository.save(person)


}