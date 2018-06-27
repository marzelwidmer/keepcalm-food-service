package ch.keepcalm.keepcalmfood

import ch.keepcalm.keepcalmfood.food.FoodRepository
import ch.keepcalm.keepcalmfood.graphql.person.Person
import ch.keepcalm.keepcalmfood.graphql.person.PersonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean





@SpringBootApplication
class KeepcalmFoodService (val foodRepository: FoodRepository, val personRepository: PersonRepository){

    @Bean
    fun init() = CommandLineRunner {
        val count = foodRepository.count()
        println("found $count records in Food database")
       personTestDataSet(personRepository)
    }

//    @Bean
//    fun schema(): GraphQLSchema {
//        return GraphQLSchema.newSchema()
//                .query(GraphQLObjectType.newObject()
//                        .name("query")
//                        .field { field ->
//                            field
//                                    .name("person")
//                                    .type(Scalars.GraphQLString)
//                                    .dataFetcher { environment -> "response" }
//                        }
//                        .build())
//                .build()
//    }

}

fun main(args: Array<String>) {
    runApplication<KeepcalmFoodService>(*args)
}

fun personTestDataSet(personRepository: PersonRepository){
    personRepository.deleteAll()
    val persons = listOf<Person>(
            Person(lastName = "Doe", firstName = "John", age = 32),
            Person(lastName = "Doe", firstName = "Jane", age = 33)
    )
    personRepository.saveAll(persons)
}
